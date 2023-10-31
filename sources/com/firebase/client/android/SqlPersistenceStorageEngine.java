package com.firebase.client.android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.firebase.client.core.CompoundWrite;
import com.firebase.client.core.Path;
import com.firebase.client.core.UserWriteRecord;
import com.firebase.client.core.persistence.PersistenceStorageEngine;
import com.firebase.client.core.persistence.PruneForest;
import com.firebase.client.core.persistence.TrackedQuery;
import com.firebase.client.core.utilities.ImmutableTree;
import com.firebase.client.core.view.QuerySpec;
import com.firebase.client.snapshot.ChildKey;
import com.firebase.client.snapshot.ChildrenNode;
import com.firebase.client.snapshot.EmptyNode;
import com.firebase.client.snapshot.NamedNode;
import com.firebase.client.snapshot.Node;
import com.firebase.client.snapshot.NodeUtilities;
import com.firebase.client.utilities.LogWrapper;
import com.firebase.client.utilities.NodeSizeEstimator;
import com.firebase.client.utilities.Pair;
import com.firebase.client.utilities.Utilities;
import com.shaded.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.shaded.apache.http.cookie.ClientCookie;

public class SqlPersistenceStorageEngine implements PersistenceStorageEngine {
    static final /* synthetic */ boolean $assertionsDisabled = (!SqlPersistenceStorageEngine.class.desiredAssertionStatus());
    private static final int CHILDREN_NODE_SPLIT_SIZE_THRESHOLD = 16384;
    private static final String FIRST_PART_KEY = ".part-0000";
    private static final String LOGGER_COMPONENT = "Persistence";
    private static final String PART_KEY_FORMAT = ".part-%04d";
    private static final String PART_KEY_PREFIX = ".part-";
    private static final String PATH_COLUMN_NAME = "path";
    private static final String ROW_ID_COLUMN_NAME = "rowid";
    private static final int ROW_SPLIT_SIZE = 262144;
    private static final String SERVER_CACHE_TABLE = "serverCache";
    private static final String TRACKED_KEYS_ID_COLUMN_NAME = "id";
    private static final String TRACKED_KEYS_KEY_COLUMN_NAME = "key";
    private static final String TRACKED_KEYS_TABLE = "trackedKeys";
    private static final String TRACKED_QUERY_ACTIVE_COLUMN_NAME = "active";
    private static final String TRACKED_QUERY_COMPLETE_COLUMN_NAME = "complete";
    private static final String TRACKED_QUERY_ID_COLUMN_NAME = "id";
    private static final String TRACKED_QUERY_LAST_USE_COLUMN_NAME = "lastUse";
    private static final String TRACKED_QUERY_PARAMS_COLUMN_NAME = "queryParams";
    private static final String TRACKED_QUERY_PATH_COLUMN_NAME = "path";
    private static final String TRACKED_QUERY_TABLE = "trackedQueries";
    private static final String VALUE_COLUMN_NAME = "value";
    private static final String WRITES_TABLE = "writes";
    private static final String WRITE_ID_COLUMN_NAME = "id";
    private static final String WRITE_NODE_COLUMN_NAME = "node";
    private static final String WRITE_PART_COLUMN_NAME = "part";
    private static final String WRITE_TYPE_COLUMN_NAME = "type";
    private static final String WRITE_TYPE_MERGE = "m";
    private static final String WRITE_TYPE_OVERWRITE = "o";
    private static final String createServerCache = "CREATE TABLE serverCache (path TEXT PRIMARY KEY, value BLOB);";
    private static final String createTrackedKeys = "CREATE TABLE trackedKeys (id INTEGER, key TEXT);";
    private static final String createTrackedQueries = "CREATE TABLE trackedQueries (id INTEGER PRIMARY KEY, path TEXT, queryParams TEXT, lastUse INTEGER, complete INTEGER, active INTEGER);";
    private static final String createWrites = "CREATE TABLE writes (id INTEGER, path TEXT, type TEXT, part INTEGER, node BLOB, UNIQUE (id, part));";
    private final SQLiteDatabase database;
    private boolean insideTransaction;
    private final ObjectMapper jsonMapper;
    private final LogWrapper logger;
    private long transactionStart = 0;

    private static class PersistentCacheOpenHelper extends SQLiteOpenHelper {
        static final /* synthetic */ boolean $assertionsDisabled = (!SqlPersistenceStorageEngine.class.desiredAssertionStatus());
        private static final int DATABASE_VERSION = 2;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public PersistentCacheOpenHelper(Context context, String cacheId) {
            super(context, cacheId, (SQLiteDatabase.CursorFactory) null, 2);
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            SQLiteDatabase db = sQLiteDatabase;
            db.execSQL(SqlPersistenceStorageEngine.createServerCache);
            db.execSQL(SqlPersistenceStorageEngine.createWrites);
            db.execSQL(SqlPersistenceStorageEngine.createTrackedQueries);
            db.execSQL(SqlPersistenceStorageEngine.createTrackedKeys);
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            Throwable th;
            StringBuilder sb;
            Throwable th2;
            SQLiteDatabase db = sQLiteDatabase;
            int oldVersion = i;
            int newVersion = i2;
            if (!$assertionsDisabled && newVersion != 2) {
                Throwable th3 = th2;
                new AssertionError("Why is onUpgrade() called with a different version?");
                throw th3;
            } else if (oldVersion <= 1) {
                dropTable(db, SqlPersistenceStorageEngine.SERVER_CACHE_TABLE);
                db.execSQL(SqlPersistenceStorageEngine.createServerCache);
                dropTable(db, SqlPersistenceStorageEngine.TRACKED_QUERY_COMPLETE_COLUMN_NAME);
                db.execSQL(SqlPersistenceStorageEngine.createTrackedKeys);
                db.execSQL(SqlPersistenceStorageEngine.createTrackedQueries);
            } else {
                Throwable th4 = th;
                new StringBuilder();
                new AssertionError(sb.append("We don't handle upgrading to ").append(newVersion).toString());
                throw th4;
            }
        }

        private void dropTable(SQLiteDatabase db, String table) {
            StringBuilder sb;
            new StringBuilder();
            db.execSQL(sb.append("DROP TABLE IF EXISTS ").append(table).toString());
        }
    }

    public SqlPersistenceStorageEngine(Context context, com.firebase.client.core.Context context2, String cacheId) {
        Throwable th;
        PersistentCacheOpenHelper helper;
        ObjectMapper objectMapper;
        Context context3 = context;
        com.firebase.client.core.Context firebaseContext = context2;
        try {
            new PersistentCacheOpenHelper(context3, URLEncoder.encode(cacheId, "utf-8"));
            this.database = helper.getWritableDatabase();
            new ObjectMapper();
            this.jsonMapper = objectMapper;
            this.logger = firebaseContext.getLogger(LOGGER_COMPONENT);
        } catch (IOException e) {
            IOException e2 = e;
            Throwable th2 = th;
            new RuntimeException(e2);
            throw th2;
        }
    }

    public void saveUserOverwrite(Path path, Node node, long writeId) {
        verifyInsideTransaction();
        long start = System.currentTimeMillis();
        saveWrite(path, writeId, WRITE_TYPE_OVERWRITE, serializeObject(node.getValue(true)));
        long duration = System.currentTimeMillis() - start;
        if (this.logger.logsDebug()) {
            this.logger.debug(String.format("Persisted user overwrite in %dms", new Object[]{Long.valueOf(duration)}));
        }
    }

    public void saveUserMerge(Path path, CompoundWrite children, long writeId) {
        verifyInsideTransaction();
        long start = System.currentTimeMillis();
        saveWrite(path, writeId, WRITE_TYPE_MERGE, serializeObject(children.getValue(true)));
        long duration = System.currentTimeMillis() - start;
        if (this.logger.logsDebug()) {
            this.logger.debug(String.format("Persisted user merge in %dms", new Object[]{Long.valueOf(duration)}));
        }
    }

    public void removeUserWrite(long j) {
        long writeId = j;
        verifyInsideTransaction();
        long start = System.currentTimeMillis();
        int count = this.database.delete(WRITES_TABLE, "id = ?", new String[]{String.valueOf(writeId)});
        long duration = System.currentTimeMillis() - start;
        if (this.logger.logsDebug()) {
            LogWrapper logWrapper = this.logger;
            Object[] objArr = new Object[3];
            objArr[0] = Integer.valueOf(count);
            Object[] objArr2 = objArr;
            objArr2[1] = Long.valueOf(writeId);
            Object[] objArr3 = objArr2;
            objArr3[2] = Long.valueOf(duration);
            logWrapper.debug(String.format("Deleted %d write(s) with writeId %d in %dms", objArr3));
        }
    }

    public List<UserWriteRecord> loadUserWrites() {
        List<UserWriteRecord> list;
        Throwable th;
        Path path;
        List list2;
        byte[] serialized;
        Throwable th2;
        StringBuilder sb;
        Object obj;
        Object obj2;
        Object obj3;
        String[] strArr = new String[5];
        strArr[0] = "id";
        String[] strArr2 = strArr;
        strArr2[1] = ClientCookie.PATH_ATTR;
        String[] strArr3 = strArr2;
        strArr3[2] = WRITE_TYPE_COLUMN_NAME;
        String[] strArr4 = strArr3;
        strArr4[3] = WRITE_PART_COLUMN_NAME;
        String[] columns = strArr4;
        columns[4] = WRITE_NODE_COLUMN_NAME;
        long start = System.currentTimeMillis();
        Cursor cursor = this.database.query(WRITES_TABLE, columns, (String) null, (String[]) null, (String) null, (String) null, "id, part");
        new ArrayList();
        List<UserWriteRecord> writes = list;
        while (cursor.moveToNext()) {
            try {
                long writeId = cursor.getLong(0);
                new Path(cursor.getString(1));
                Path path2 = path;
                String type = cursor.getString(2);
                if (cursor.isNull(3)) {
                    serialized = cursor.getBlob(4);
                } else {
                    new ArrayList();
                    List list3 = list2;
                    do {
                        boolean add = list3.add(cursor.getBlob(4));
                        if (!cursor.moveToNext() || cursor.getLong(0) != writeId) {
                            boolean moveToPrevious = cursor.moveToPrevious();
                            serialized = joinBytes(list3);
                        }
                        boolean add2 = list3.add(cursor.getBlob(4));
                        break;
                    } while (cursor.getLong(0) != writeId);
                    boolean moveToPrevious2 = cursor.moveToPrevious();
                    serialized = joinBytes(list3);
                }
                Object writeValue = this.jsonMapper.readValue(serialized, Object.class);
                if (WRITE_TYPE_OVERWRITE.equals(type)) {
                    new UserWriteRecord(writeId, path2, NodeUtilities.NodeFromJSON(writeValue), true);
                    obj2 = obj3;
                } else if (WRITE_TYPE_MERGE.equals(type)) {
                    new UserWriteRecord(writeId, path2, CompoundWrite.fromValue((Map) writeValue));
                    obj2 = obj;
                } else {
                    Throwable th3 = th2;
                    new StringBuilder();
                    new IllegalStateException(sb.append("Got invalid write type: ").append(type).toString());
                    throw th3;
                }
                boolean add3 = writes.add(obj2);
            } catch (IOException e) {
                IOException e2 = e;
                Throwable th4 = th;
                new RuntimeException("Failed to load writes", e2);
                throw th4;
            } catch (Throwable th5) {
                Throwable th6 = th5;
                cursor.close();
                throw th6;
            }
        }
        long duration = System.currentTimeMillis() - start;
        if (this.logger.logsDebug()) {
            LogWrapper logWrapper = this.logger;
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(writes.size());
            Object[] objArr2 = objArr;
            objArr2[1] = Long.valueOf(duration);
            logWrapper.debug(String.format("Loaded %d writes in %dms", objArr2));
        }
        List<UserWriteRecord> list4 = writes;
        cursor.close();
        return list4;
    }

    private void saveWrite(Path path, long j, String str, byte[] bArr) {
        ContentValues contentValues;
        ContentValues contentValues2;
        Path path2 = path;
        long writeId = j;
        String type = str;
        byte[] serializedWrite = bArr;
        verifyInsideTransaction();
        int delete = this.database.delete(WRITES_TABLE, "id = ?", new String[]{String.valueOf(writeId)});
        if (serializedWrite.length >= 262144) {
            List<byte[]> parts = splitBytes(serializedWrite, 262144);
            for (int i = 0; i < parts.size(); i++) {
                new ContentValues();
                ContentValues values = contentValues2;
                values.put("id", Long.valueOf(writeId));
                values.put(ClientCookie.PATH_ATTR, pathToKey(path2));
                values.put(WRITE_TYPE_COLUMN_NAME, type);
                values.put(WRITE_PART_COLUMN_NAME, Integer.valueOf(i));
                values.put(WRITE_NODE_COLUMN_NAME, parts.get(i));
                long insertWithOnConflict = this.database.insertWithOnConflict(WRITES_TABLE, (String) null, values, 5);
            }
            return;
        }
        new ContentValues();
        ContentValues values2 = contentValues;
        values2.put("id", Long.valueOf(writeId));
        values2.put(ClientCookie.PATH_ATTR, pathToKey(path2));
        values2.put(WRITE_TYPE_COLUMN_NAME, type);
        values2.put(WRITE_PART_COLUMN_NAME, (Integer) null);
        values2.put(WRITE_NODE_COLUMN_NAME, serializedWrite);
        long insertWithOnConflict2 = this.database.insertWithOnConflict(WRITES_TABLE, (String) null, values2, 5);
    }

    public Node serverCache(Path path) {
        return loadNested(path);
    }

    public void overwriteServerCache(Path path, Node node) {
        verifyInsideTransaction();
        updateServerCache(path, node, false);
    }

    public void mergeIntoServerCache(Path path, Node node) {
        verifyInsideTransaction();
        updateServerCache(path, node, true);
    }

    private void updateServerCache(Path path, Node node, boolean merge) {
        int removedRows;
        int savedRows;
        Path path2 = path;
        Node<NamedNode> node2 = node;
        long start = System.currentTimeMillis();
        if (!merge) {
            removedRows = removeNested(SERVER_CACHE_TABLE, path2);
            savedRows = saveNested(path2, node2);
        } else {
            removedRows = 0;
            savedRows = 0;
            for (NamedNode child : node2) {
                removedRows += removeNested(SERVER_CACHE_TABLE, path2.child(child.getName()));
                savedRows += saveNested(path2.child(child.getName()), child.getNode());
            }
        }
        long duration = System.currentTimeMillis() - start;
        if (this.logger.logsDebug()) {
            LogWrapper logWrapper = this.logger;
            Object[] objArr = new Object[4];
            objArr[0] = Integer.valueOf(savedRows);
            Object[] objArr2 = objArr;
            objArr2[1] = Integer.valueOf(removedRows);
            Object[] objArr3 = objArr2;
            objArr3[2] = path2.toString();
            Object[] objArr4 = objArr3;
            objArr4[3] = Long.valueOf(duration);
            logWrapper.debug(String.format("Persisted a total of %d rows and deleted %d rows for a set at %s in %dms", objArr4));
        }
    }

    public void mergeIntoServerCache(Path path, CompoundWrite children) {
        Path path2 = path;
        verifyInsideTransaction();
        long start = System.currentTimeMillis();
        int savedRows = 0;
        int removedRows = 0;
        Iterator i$ = children.iterator();
        while (i$.hasNext()) {
            Map.Entry<Path, Node> entry = i$.next();
            removedRows += removeNested(SERVER_CACHE_TABLE, path2.child(entry.getKey()));
            savedRows += saveNested(path2.child(entry.getKey()), entry.getValue());
        }
        long duration = System.currentTimeMillis() - start;
        if (this.logger.logsDebug()) {
            LogWrapper logWrapper = this.logger;
            Object[] objArr = new Object[4];
            objArr[0] = Integer.valueOf(savedRows);
            Object[] objArr2 = objArr;
            objArr2[1] = Integer.valueOf(removedRows);
            Object[] objArr3 = objArr2;
            objArr3[2] = path2.toString();
            Object[] objArr4 = objArr3;
            objArr4[3] = Long.valueOf(duration);
            logWrapper.debug(String.format("Persisted a total of %d rows and deleted %d rows for a merge at %s in %dms", objArr4));
        }
    }

    /* JADX INFO: finally extract failed */
    public long serverCacheEstimatedSizeInBytes() {
        Throwable th;
        Object[] objArr = new Object[3];
        objArr[0] = VALUE_COLUMN_NAME;
        Object[] objArr2 = objArr;
        objArr2[1] = ClientCookie.PATH_ATTR;
        Object[] objArr3 = objArr2;
        objArr3[2] = SERVER_CACHE_TABLE;
        String query = String.format("SELECT sum(length(%s) + length(%s)) FROM %s", objArr3);
        Cursor cursor = this.database.rawQuery(query, (String[]) null);
        try {
            if (cursor.moveToFirst()) {
                long j = cursor.getLong(0);
                cursor.close();
                return j;
            }
            Throwable th2 = th;
            new IllegalStateException("Couldn't read database result!");
            throw th2;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            cursor.close();
            throw th4;
        }
    }

    public void saveTrackedQuery(TrackedQuery trackedQuery) {
        ContentValues contentValues;
        TrackedQuery trackedQuery2 = trackedQuery;
        verifyInsideTransaction();
        long start = System.currentTimeMillis();
        new ContentValues();
        ContentValues values = contentValues;
        values.put("id", Long.valueOf(trackedQuery2.f274id));
        values.put(ClientCookie.PATH_ATTR, pathToKey(trackedQuery2.querySpec.getPath()));
        values.put(TRACKED_QUERY_PARAMS_COLUMN_NAME, trackedQuery2.querySpec.getParams().toJSON());
        values.put(TRACKED_QUERY_LAST_USE_COLUMN_NAME, Long.valueOf(trackedQuery2.lastUse));
        values.put(TRACKED_QUERY_COMPLETE_COLUMN_NAME, Boolean.valueOf(trackedQuery2.complete));
        values.put(TRACKED_QUERY_ACTIVE_COLUMN_NAME, Boolean.valueOf(trackedQuery2.active));
        long insertWithOnConflict = this.database.insertWithOnConflict(TRACKED_QUERY_TABLE, (String) null, values, 5);
        long duration = System.currentTimeMillis() - start;
        if (this.logger.logsDebug()) {
            this.logger.debug(String.format("Saved new tracked query in %dms", new Object[]{Long.valueOf(duration)}));
        }
    }

    /*  JADX ERROR: NullPointerException in pass: CodeShrinkVisitor
        java.lang.NullPointerException
        */
    public void deleteTrackedQuery(long r16) {
        /*
            r15 = this;
            r0 = r15
            r1 = r16
            r6 = r0
            r6.verifyInsideTransaction()
            r6 = r1
            java.lang.String r6 = java.lang.String.valueOf(r6)
            r3 = r6
            java.lang.String r6 = "id = ?"
            r4 = r6
            r6 = r0
            android.database.sqlite.SQLiteDatabase r6 = r6.database
            java.lang.String r7 = "trackedQueries"
            r8 = r4
            r9 = 1
            java.lang.String[] r9 = new java.lang.String[r9]
            r13 = r9
            r9 = r13
            r10 = r13
            r11 = 0
            r12 = r3
            r10[r11] = r12
            int r6 = r6.delete(r7, r8, r9)
            java.lang.String r6 = "id = ?"
            r5 = r6
            r6 = r0
            android.database.sqlite.SQLiteDatabase r6 = r6.database
            java.lang.String r7 = "trackedKeys"
            r8 = r5
            r9 = 1
            java.lang.String[] r9 = new java.lang.String[r9]
            r13 = r9
            r9 = r13
            r10 = r13
            r11 = 0
            r12 = r3
            r10[r11] = r12
            int r6 = r6.delete(r7, r8, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.firebase.client.android.SqlPersistenceStorageEngine.deleteTrackedQuery(long):void");
    }

    public List<TrackedQuery> loadTrackedQueries() {
        List<TrackedQuery> list;
        Path path;
        Throwable th;
        Object obj;
        String[] strArr = new String[6];
        strArr[0] = "id";
        String[] strArr2 = strArr;
        strArr2[1] = ClientCookie.PATH_ATTR;
        String[] strArr3 = strArr2;
        strArr3[2] = TRACKED_QUERY_PARAMS_COLUMN_NAME;
        String[] strArr4 = strArr3;
        strArr4[3] = TRACKED_QUERY_LAST_USE_COLUMN_NAME;
        String[] strArr5 = strArr4;
        strArr5[4] = TRACKED_QUERY_COMPLETE_COLUMN_NAME;
        String[] columns = strArr5;
        columns[5] = TRACKED_QUERY_ACTIVE_COLUMN_NAME;
        long start = System.currentTimeMillis();
        Cursor cursor = this.database.query(TRACKED_QUERY_TABLE, columns, (String) null, (String[]) null, (String) null, (String) null, "id");
        new ArrayList();
        List<TrackedQuery> queries = list;
        while (cursor.moveToNext()) {
            try {
                long id = cursor.getLong(0);
                new Path(cursor.getString(1));
                new TrackedQuery(id, QuerySpec.fromPathAndQueryObject(path, (Map) this.jsonMapper.readValue(cursor.getString(2), Object.class)), cursor.getLong(3), cursor.getInt(4) != 0, cursor.getInt(5) != 0);
                boolean add = queries.add(obj);
            } catch (IOException e) {
                IOException e2 = e;
                Throwable th2 = th;
                new RuntimeException(e2);
                throw th2;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                cursor.close();
                throw th4;
            }
        }
        long duration = System.currentTimeMillis() - start;
        if (this.logger.logsDebug()) {
            LogWrapper logWrapper = this.logger;
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(queries.size());
            Object[] objArr2 = objArr;
            objArr2[1] = Long.valueOf(duration);
            logWrapper.debug(String.format("Loaded %d tracked queries in %dms", objArr2));
        }
        List<TrackedQuery> list2 = queries;
        cursor.close();
        return list2;
    }

    /*  JADX ERROR: NullPointerException in pass: CodeShrinkVisitor
        java.lang.NullPointerException
        */
    public void resetPreviouslyActiveTrackedQueries(long r20) {
        /*
            r19 = this;
            r1 = r19
            r2 = r20
            r10 = r1
            r10.verifyInsideTransaction()
            long r10 = java.lang.System.currentTimeMillis()
            r4 = r10
            java.lang.String r10 = "active = 1"
            r6 = r10
            android.content.ContentValues r10 = new android.content.ContentValues
            r17 = r10
            r10 = r17
            r11 = r17
            r11.<init>()
            r7 = r10
            r10 = r7
            java.lang.String r11 = "active"
            r12 = 0
            java.lang.Boolean r12 = java.lang.Boolean.valueOf(r12)
            r10.put(r11, r12)
            r10 = r7
            java.lang.String r11 = "lastUse"
            r12 = r2
            java.lang.Long r12 = java.lang.Long.valueOf(r12)
            r10.put(r11, r12)
            r10 = r1
            android.database.sqlite.SQLiteDatabase r10 = r10.database
            java.lang.String r11 = "trackedQueries"
            r12 = r7
            r13 = r6
            r14 = 0
            java.lang.String[] r14 = new java.lang.String[r14]
            r15 = 5
            int r10 = r10.updateWithOnConflict(r11, r12, r13, r14, r15)
            long r10 = java.lang.System.currentTimeMillis()
            r12 = r4
            long r10 = r10 - r12
            r8 = r10
            r10 = r1
            com.firebase.client.utilities.LogWrapper r10 = r10.logger
            boolean r10 = r10.logsDebug()
            if (r10 == 0) goto L_0x0073
            r10 = r1
            com.firebase.client.utilities.LogWrapper r10 = r10.logger
            java.lang.String r11 = "Reset active tracked queries in %dms"
            r12 = 1
            java.lang.Object[] r12 = new java.lang.Object[r12]
            r17 = r12
            r12 = r17
            r13 = r17
            r14 = 0
            r15 = r8
            java.lang.Long r15 = java.lang.Long.valueOf(r15)
            r13[r14] = r15
            java.lang.String r11 = java.lang.String.format(r11, r12)
            r10.debug(r11)
        L_0x0073:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.firebase.client.android.SqlPersistenceStorageEngine.resetPreviouslyActiveTrackedQueries(long):void");
    }

    /*  JADX ERROR: NullPointerException in pass: CodeShrinkVisitor
        java.lang.NullPointerException
        */
    public void saveTrackedQueryKeys(long r24, java.util.Set<com.firebase.client.snapshot.ChildKey> r26) {
        /*
            r23 = this;
            r1 = r23
            r2 = r24
            r4 = r26
            r14 = r1
            r14.verifyInsideTransaction()
            long r14 = java.lang.System.currentTimeMillis()
            r5 = r14
            r14 = r2
            java.lang.String r14 = java.lang.String.valueOf(r14)
            r7 = r14
            java.lang.String r14 = "id = ?"
            r8 = r14
            r14 = r1
            android.database.sqlite.SQLiteDatabase r14 = r14.database
            java.lang.String r15 = "trackedKeys"
            r16 = r8
            r17 = 1
            r0 = r17
            java.lang.String[] r0 = new java.lang.String[r0]
            r17 = r0
            r21 = r17
            r17 = r21
            r18 = r21
            r19 = 0
            r20 = r7
            r18[r19] = r20
            int r14 = r14.delete(r15, r16, r17)
            r14 = r4
            java.util.Iterator r14 = r14.iterator()
            r9 = r14
        L_0x003f:
            r14 = r9
            boolean r14 = r14.hasNext()
            if (r14 == 0) goto L_0x0085
            r14 = r9
            java.lang.Object r14 = r14.next()
            com.firebase.client.snapshot.ChildKey r14 = (com.firebase.client.snapshot.ChildKey) r14
            r10 = r14
            android.content.ContentValues r14 = new android.content.ContentValues
            r21 = r14
            r14 = r21
            r15 = r21
            r15.<init>()
            r11 = r14
            r14 = r11
            java.lang.String r15 = "id"
            r16 = r2
            java.lang.Long r16 = java.lang.Long.valueOf(r16)
            r14.put(r15, r16)
            r14 = r11
            java.lang.String r15 = "key"
            r16 = r10
            java.lang.String r16 = r16.asString()
            r14.put(r15, r16)
            r14 = r1
            android.database.sqlite.SQLiteDatabase r14 = r14.database
            java.lang.String r15 = "trackedKeys"
            r16 = 0
            r17 = r11
            r18 = 5
            long r14 = r14.insertWithOnConflict(r15, r16, r17, r18)
            goto L_0x003f
        L_0x0085:
            long r14 = java.lang.System.currentTimeMillis()
            r16 = r5
            long r14 = r14 - r16
            r12 = r14
            r14 = r1
            com.firebase.client.utilities.LogWrapper r14 = r14.logger
            boolean r14 = r14.logsDebug()
            if (r14 == 0) goto L_0x00e0
            r14 = r1
            com.firebase.client.utilities.LogWrapper r14 = r14.logger
            java.lang.String r15 = "Set %d tracked query keys for tracked query %d in %dms"
            r16 = 3
            r0 = r16
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r16 = r0
            r21 = r16
            r16 = r21
            r17 = r21
            r18 = 0
            r19 = r4
            int r19 = r19.size()
            java.lang.Integer r19 = java.lang.Integer.valueOf(r19)
            r17[r18] = r19
            r21 = r16
            r16 = r21
            r17 = r21
            r18 = 1
            r19 = r2
            java.lang.Long r19 = java.lang.Long.valueOf(r19)
            r17[r18] = r19
            r21 = r16
            r16 = r21
            r17 = r21
            r18 = 2
            r19 = r12
            java.lang.Long r19 = java.lang.Long.valueOf(r19)
            r17[r18] = r19
            java.lang.String r15 = java.lang.String.format(r15, r16)
            r14.debug(r15)
        L_0x00e0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.firebase.client.android.SqlPersistenceStorageEngine.saveTrackedQueryKeys(long, java.util.Set):void");
    }

    public void updateTrackedQueryKeys(long j, Set<ChildKey> set, Set<ChildKey> set2) {
        ContentValues contentValues;
        long trackedQueryId = j;
        Set<ChildKey> added = set;
        Set<ChildKey> removed = set2;
        verifyInsideTransaction();
        long start = System.currentTimeMillis();
        String whereClause = "id = ? AND key = ?";
        String trackedQueryIdStr = String.valueOf(trackedQueryId);
        for (ChildKey removedKey : removed) {
            String[] strArr = new String[2];
            strArr[0] = trackedQueryIdStr;
            String[] strArr2 = strArr;
            strArr2[1] = removedKey.asString();
            int delete = this.database.delete(TRACKED_KEYS_TABLE, whereClause, strArr2);
        }
        for (ChildKey addedKey : added) {
            new ContentValues();
            ContentValues values = contentValues;
            values.put("id", Long.valueOf(trackedQueryId));
            values.put(TRACKED_KEYS_KEY_COLUMN_NAME, addedKey.asString());
            long insertWithOnConflict = this.database.insertWithOnConflict(TRACKED_KEYS_TABLE, (String) null, values, 5);
        }
        long duration = System.currentTimeMillis() - start;
        if (this.logger.logsDebug()) {
            LogWrapper logWrapper = this.logger;
            Object[] objArr = new Object[4];
            objArr[0] = Integer.valueOf(added.size());
            Object[] objArr2 = objArr;
            objArr2[1] = Integer.valueOf(removed.size());
            Object[] objArr3 = objArr2;
            objArr3[2] = Long.valueOf(trackedQueryId);
            Object[] objArr4 = objArr3;
            objArr4[3] = Long.valueOf(duration);
            logWrapper.debug(String.format("Updated tracked query keys (%d added, %d removed) for tracked query id %d in %dms", objArr4));
        }
    }

    public Set<ChildKey> loadTrackedQueryKeys(long trackedQueryId) {
        return loadTrackedQueryKeys((Set<Long>) Collections.singleton(Long.valueOf(trackedQueryId)));
    }

    /* JADX INFO: finally extract failed */
    public Set<ChildKey> loadTrackedQueryKeys(Set<Long> set) {
        StringBuilder sb;
        Set<ChildKey> set2;
        Set<Long> trackedQueryIds = set;
        long start = System.currentTimeMillis();
        new StringBuilder();
        Cursor cursor = this.database.query(true, TRACKED_KEYS_TABLE, new String[]{TRACKED_KEYS_KEY_COLUMN_NAME}, sb.append("id IN (").append(commaSeparatedList(trackedQueryIds)).append(")").toString(), (String[]) null, (String) null, (String) null, (String) null, (String) null);
        new HashSet();
        Set<ChildKey> keys = set2;
        while (cursor.moveToNext()) {
            try {
                boolean add = keys.add(ChildKey.fromString(cursor.getString(0)));
            } catch (Throwable th) {
                Throwable th2 = th;
                cursor.close();
                throw th2;
            }
        }
        long duration = System.currentTimeMillis() - start;
        if (this.logger.logsDebug()) {
            LogWrapper logWrapper = this.logger;
            Object[] objArr = new Object[3];
            objArr[0] = Integer.valueOf(keys.size());
            Object[] objArr2 = objArr;
            objArr2[1] = trackedQueryIds.toString();
            Object[] objArr3 = objArr2;
            objArr3[2] = Long.valueOf(duration);
            logWrapper.debug(String.format("Loaded %d tracked queries keys for tracked queries %s in %dms", objArr3));
        }
        Set<ChildKey> set3 = keys;
        cursor.close();
        return set3;
    }

    public void pruneCache(Path path, PruneForest pruneForest) {
        ImmutableTree immutableTree;
        ImmutableTree immutableTree2;
        List<Pair<Path, Node>> list;
        StringBuilder sb;
        Path path2;
        StringBuilder sb2;
        StringBuilder sb3;
        Path root = path;
        PruneForest pruneForest2 = pruneForest;
        if (pruneForest2.prunesAnything()) {
            verifyInsideTransaction();
            long start = System.currentTimeMillis();
            String[] strArr = new String[2];
            strArr[0] = ROW_ID_COLUMN_NAME;
            String[] strArr2 = strArr;
            strArr2[1] = ClientCookie.PATH_ATTR;
            Cursor cursor = loadNestedQuery(root, strArr2);
            new ImmutableTree(null);
            ImmutableTree immutableTree3 = immutableTree;
            new ImmutableTree(null);
            ImmutableTree immutableTree4 = immutableTree2;
            while (cursor.moveToNext()) {
                long rowId = cursor.getLong(0);
                new Path(cursor.getString(1));
                Path rowPath = path2;
                if (!root.contains(rowPath)) {
                    new StringBuilder();
                    this.logger.warn(sb3.append("We are pruning at ").append(root).append(" but we have data stored higher up at ").append(rowPath).append(". Ignoring.").toString());
                } else {
                    Path relativePath = Path.getRelative(root, rowPath);
                    if (pruneForest2.shouldPruneUnkeptDescendants(relativePath)) {
                        immutableTree3 = immutableTree3.set(relativePath, Long.valueOf(rowId));
                    } else if (pruneForest2.shouldKeep(relativePath)) {
                        immutableTree4 = immutableTree4.set(relativePath, Long.valueOf(rowId));
                    } else {
                        new StringBuilder();
                        this.logger.warn(sb2.append("We are pruning at ").append(root).append(" and have data at ").append(rowPath).append(" that isn't marked for pruning or keeping. Ignoring.").toString());
                    }
                }
            }
            int prunedCount = 0;
            int resavedCount = 0;
            if (!immutableTree3.isEmpty()) {
                new ArrayList<>();
                List<Pair<Path, Node>> rowsToResave = list;
                pruneTreeRecursive(root, Path.getEmptyPath(), immutableTree3, immutableTree4, pruneForest2, rowsToResave);
                Collection<Long> rowIdsToDelete = immutableTree3.values();
                new StringBuilder();
                int delete = this.database.delete(SERVER_CACHE_TABLE, sb.append("rowid IN (").append(commaSeparatedList(rowIdsToDelete)).append(")").toString(), (String[]) null);
                for (Pair<Path, Node> node : rowsToResave) {
                    int saveNested = saveNested(root.child(node.getFirst()), node.getSecond());
                }
                prunedCount = rowIdsToDelete.size();
                resavedCount = rowsToResave.size();
            }
            long duration = System.currentTimeMillis() - start;
            if (this.logger.logsDebug()) {
                LogWrapper logWrapper = this.logger;
                Object[] objArr = new Object[3];
                objArr[0] = Integer.valueOf(prunedCount);
                Object[] objArr2 = objArr;
                objArr2[1] = Integer.valueOf(resavedCount);
                Object[] objArr3 = objArr2;
                objArr3[2] = Long.valueOf(duration);
                logWrapper.debug(String.format("Pruned %d rows with %d nodes resaved in %dms", objArr3));
            }
        }
    }

    private void pruneTreeRecursive(Path path, Path path2, ImmutableTree<Long> immutableTree, ImmutableTree<Long> immutableTree2, PruneForest pruneForest, List<Pair<Path, Node>> list) {
        ImmutableTree.TreeVisitor treeVisitor;
        ImmutableTree.TreeVisitor treeVisitor2;
        Path pruneRoot = path;
        Path relativePath = path2;
        ImmutableTree<Long> rowIdsToPrune = immutableTree;
        ImmutableTree<Long> rowIdsToKeep = immutableTree2;
        PruneForest pruneForest2 = pruneForest;
        List<Pair<Path, Node>> rowsToResaveAccumulator = list;
        if (rowIdsToPrune.getValue() != null) {
            final ImmutableTree<Long> immutableTree3 = rowIdsToKeep;
            new ImmutableTree.TreeVisitor<Void, Integer>(this) {
                final /* synthetic */ SqlPersistenceStorageEngine this$0;

                {
                    this.this$0 = r6;
                }

                public Integer onNodeValue(Path keepPath, Void voidR, Integer num) {
                    Void voidR2 = voidR;
                    Integer nodesToResave = num;
                    return Integer.valueOf(immutableTree3.get(keepPath) == null ? nodesToResave.intValue() + 1 : nodesToResave.intValue());
                }
            };
            int nodesToResave = ((Integer) pruneForest2.foldKeptNodes(0, treeVisitor)).intValue();
            if (nodesToResave > 0) {
                Path absolutePath = pruneRoot.child(relativePath);
                if (this.logger.logsDebug()) {
                    LogWrapper logWrapper = this.logger;
                    Object[] objArr = new Object[2];
                    objArr[0] = Integer.valueOf(nodesToResave);
                    Object[] objArr2 = objArr;
                    objArr2[1] = absolutePath;
                    logWrapper.debug(String.format("Need to rewrite %d nodes below path %s", objArr2));
                }
                final ImmutableTree<Long> immutableTree4 = rowIdsToKeep;
                final List<Pair<Path, Node>> list2 = rowsToResaveAccumulator;
                final Path path3 = relativePath;
                final Node loadNested = loadNested(absolutePath);
                new ImmutableTree.TreeVisitor<Void, Void>(this) {
                    final /* synthetic */ SqlPersistenceStorageEngine this$0;

                    {
                        this.this$0 = r9;
                    }

                    public Void onNodeValue(Path path, Void voidR, Void voidR2) {
                        Object obj;
                        Path keepPath = path;
                        Void voidR3 = voidR;
                        Void voidR4 = voidR2;
                        if (immutableTree4.get(keepPath) == null) {
                            new Pair(path3.child(keepPath), loadNested.getChild(keepPath));
                            boolean add = list2.add(obj);
                        }
                        return null;
                    }
                };
                Object foldKeptNodes = pruneForest2.foldKeptNodes(null, treeVisitor2);
                return;
            }
            return;
        }
        Iterator i$ = rowIdsToPrune.getChildren().iterator();
        while (i$.hasNext()) {
            Map.Entry<ChildKey, ImmutableTree<Long>> entry = i$.next();
            ChildKey childKey = entry.getKey();
            pruneTreeRecursive(pruneRoot, relativePath.child(childKey), entry.getValue(), rowIdsToKeep.getChild(childKey), pruneForest2.child(entry.getKey()), rowsToResaveAccumulator);
        }
    }

    public void removeAllUserWrites() {
        verifyInsideTransaction();
        long start = System.currentTimeMillis();
        int count = this.database.delete(WRITES_TABLE, (String) null, (String[]) null);
        long duration = System.currentTimeMillis() - start;
        if (this.logger.logsDebug()) {
            LogWrapper logWrapper = this.logger;
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(count);
            Object[] objArr2 = objArr;
            objArr2[1] = Long.valueOf(duration);
            logWrapper.debug(String.format("Deleted %d (all) write(s) in %dms", objArr2));
        }
    }

    public void purgeCache() {
        verifyInsideTransaction();
        int delete = this.database.delete(SERVER_CACHE_TABLE, (String) null, (String[]) null);
        int delete2 = this.database.delete(WRITES_TABLE, (String) null, (String[]) null);
        int delete3 = this.database.delete(TRACKED_QUERY_TABLE, (String) null, (String[]) null);
        int delete4 = this.database.delete(TRACKED_KEYS_TABLE, (String) null, (String[]) null);
    }

    public void beginTransaction() {
        Utilities.hardAssert(!this.insideTransaction, "runInTransaction called when an existing transaction is already in progress.");
        if (this.logger.logsDebug()) {
            this.logger.debug("Starting transaction.");
        }
        this.database.beginTransaction();
        this.insideTransaction = true;
        this.transactionStart = System.currentTimeMillis();
    }

    public void endTransaction() {
        this.database.endTransaction();
        this.insideTransaction = false;
        long elapsed = System.currentTimeMillis() - this.transactionStart;
        if (this.logger.logsDebug()) {
            this.logger.debug(String.format("Transaction completed. Elapsed: %dms", new Object[]{Long.valueOf(elapsed)}));
        }
    }

    public void setTransactionSuccessful() {
        this.database.setTransactionSuccessful();
    }

    private void verifyInsideTransaction() {
        Utilities.hardAssert(this.insideTransaction, "Transaction expected to already be in progress.");
    }

    private int saveNested(Path path, Node node) {
        Path path2 = path;
        Node<NamedNode> node2 = node;
        long estimatedSize = NodeSizeEstimator.estimateSerializedNodeSize(node2);
        if (!(node2 instanceof ChildrenNode) || estimatedSize <= 16384) {
            saveNode(path2, node2);
            return 1;
        }
        if (this.logger.logsDebug()) {
            LogWrapper logWrapper = this.logger;
            Object[] objArr = new Object[3];
            objArr[0] = path2;
            Object[] objArr2 = objArr;
            objArr2[1] = Long.valueOf(estimatedSize);
            Object[] objArr3 = objArr2;
            objArr3[2] = 16384;
            logWrapper.debug(String.format("Node estimated serialized size at path %s of %d bytes exceeds limit of %d bytes. Splitting up.", objArr3));
        }
        int sum = 0;
        for (NamedNode child : node2) {
            sum += saveNested(path2.child(child.getName()), child.getNode());
        }
        if (!node2.getPriority().isEmpty()) {
            saveNode(path2.child(ChildKey.getPriorityKey()), node2.getPriority());
            sum++;
        }
        saveNode(path2, EmptyNode.Empty());
        return sum + 1;
    }

    private String partKey(Path path, int i) {
        StringBuilder sb;
        new StringBuilder();
        return sb.append(pathToKey(path)).append(String.format(PART_KEY_FORMAT, new Object[]{Integer.valueOf(i)})).toString();
    }

    private void saveNode(Path path, Node node) {
        ContentValues contentValues;
        ContentValues contentValues2;
        StringBuilder sb;
        Path path2 = path;
        byte[] serialized = serializeObject(node.getValue(true));
        if (serialized.length >= 262144) {
            List<byte[]> parts = splitBytes(serialized, 262144);
            if (this.logger.logsDebug()) {
                LogWrapper logWrapper = this.logger;
                new StringBuilder();
                logWrapper.debug(sb.append("Saving huge leaf node with ").append(parts.size()).append(" parts.").toString());
            }
            for (int i = 0; i < parts.size(); i++) {
                new ContentValues();
                ContentValues values = contentValues2;
                values.put(ClientCookie.PATH_ATTR, partKey(path2, i));
                values.put(VALUE_COLUMN_NAME, parts.get(i));
                long insertWithOnConflict = this.database.insertWithOnConflict(SERVER_CACHE_TABLE, (String) null, values, 5);
            }
            return;
        }
        new ContentValues();
        ContentValues values2 = contentValues;
        values2.put(ClientCookie.PATH_ATTR, pathToKey(path2));
        values2.put(VALUE_COLUMN_NAME, serialized);
        long insertWithOnConflict2 = this.database.insertWithOnConflict(SERVER_CACHE_TABLE, (String) null, values2, 5);
    }

    private Node loadNested(Path path) {
        List<String> list;
        List<byte[]> list2;
        Map<Path, Node> map;
        Node savedNode;
        Path path2;
        Path savedPath;
        Throwable th;
        Path path3;
        StringBuilder sb;
        Path path4 = path;
        new ArrayList<>();
        List<String> pathStrings = list;
        new ArrayList<>();
        List<byte[]> payloads = list2;
        long queryStart = System.currentTimeMillis();
        String[] strArr = new String[2];
        strArr[0] = ClientCookie.PATH_ATTR;
        String[] strArr2 = strArr;
        strArr2[1] = VALUE_COLUMN_NAME;
        Cursor cursor = loadNestedQuery(path4, strArr2);
        long queryDuration = System.currentTimeMillis() - queryStart;
        long loadingStart = System.currentTimeMillis();
        while (cursor.moveToNext()) {
            try {
                boolean add = pathStrings.add(cursor.getString(0));
                boolean add2 = payloads.add(cursor.getBlob(1));
            } catch (Throwable th2) {
                Throwable th3 = th2;
                cursor.close();
                throw th3;
            }
        }
        cursor.close();
        long loadingDuration = System.currentTimeMillis() - loadingStart;
        long serializingStart = System.currentTimeMillis();
        Node node = EmptyNode.Empty();
        boolean sawDescendant = false;
        new HashMap<>();
        Map<Path, Node> priorities = map;
        int i = 0;
        while (i < payloads.size()) {
            if (pathStrings.get(i).endsWith(FIRST_PART_KEY)) {
                String pathString = pathStrings.get(i);
                new Path(pathString.substring(0, pathString.length() - FIRST_PART_KEY.length()));
                savedPath = path3;
                int splitNodeRunLength = splitNodeRunLength(savedPath, pathStrings, i);
                if (this.logger.logsDebug()) {
                    new StringBuilder();
                    this.logger.debug(sb.append("Loading split node with ").append(splitNodeRunLength).append(" parts.").toString());
                }
                savedNode = deserializeNode(joinBytes(payloads.subList(i, i + splitNodeRunLength)));
                i = (i + splitNodeRunLength) - 1;
            } else {
                savedNode = deserializeNode(payloads.get(i));
                new Path(pathStrings.get(i));
                savedPath = path2;
            }
            if (savedPath.getBack() != null && savedPath.getBack().isPriorityChildName()) {
                Node put = priorities.put(savedPath, savedNode);
            } else if (savedPath.contains(path4)) {
                Utilities.hardAssert(!sawDescendant, "Descendants of path must come after ancestors.");
                node = savedNode.getChild(Path.getRelative(savedPath, path4));
            } else if (path4.contains(savedPath)) {
                sawDescendant = true;
                node = node.updateChild(Path.getRelative(path4, savedPath), savedNode);
            } else {
                Throwable th4 = th;
                Object[] objArr = new Object[2];
                objArr[0] = savedPath;
                Object[] objArr2 = objArr;
                objArr2[1] = path4;
                new IllegalStateException(String.format("Loading an unrelated row with path %s for %s", objArr2));
                throw th4;
            }
            i++;
        }
        for (Map.Entry<Path, Node> entry : priorities.entrySet()) {
            node = node.updateChild(Path.getRelative(path4, entry.getKey()), entry.getValue());
        }
        long serializeDuration = System.currentTimeMillis() - serializingStart;
        long duration = System.currentTimeMillis() - queryStart;
        if (this.logger.logsDebug()) {
            LogWrapper logWrapper = this.logger;
            Object[] objArr3 = new Object[7];
            objArr3[0] = Integer.valueOf(payloads.size());
            Object[] objArr4 = objArr3;
            objArr4[1] = Integer.valueOf(NodeSizeEstimator.nodeCount(node));
            Object[] objArr5 = objArr4;
            objArr5[2] = path4;
            Object[] objArr6 = objArr5;
            objArr6[3] = Long.valueOf(duration);
            Object[] objArr7 = objArr6;
            objArr7[4] = Long.valueOf(queryDuration);
            Object[] objArr8 = objArr7;
            objArr8[5] = Long.valueOf(loadingDuration);
            Object[] objArr9 = objArr8;
            objArr9[6] = Long.valueOf(serializeDuration);
            logWrapper.debug(String.format("Loaded a total of %d rows for a total of %d nodes at %s in %dms (Query: %dms, Loading: %dms, Serializing: %dms)", objArr9));
        }
        return node;
    }

    private int splitNodeRunLength(Path path, List<String> list, int i) {
        StringBuilder sb;
        Throwable th;
        Throwable th2;
        Path path2 = path;
        List<String> pathStrings = list;
        int startPosition = i;
        int endPosition = startPosition + 1;
        String pathPrefix = pathToKey(path2);
        if (!pathStrings.get(startPosition).startsWith(pathPrefix)) {
            Throwable th3 = th2;
            new IllegalStateException("Extracting split nodes needs to start with path prefix");
            throw th3;
        }
        while (endPosition < pathStrings.size() && pathStrings.get(endPosition).equals(partKey(path2, endPosition - startPosition))) {
            endPosition++;
        }
        if (endPosition < pathStrings.size()) {
            new StringBuilder();
            if (pathStrings.get(endPosition).startsWith(sb.append(pathPrefix).append(PART_KEY_PREFIX).toString())) {
                Throwable th4 = th;
                new IllegalStateException("Run did not finish with all parts");
                throw th4;
            }
        }
        return endPosition - startPosition;
    }

    private Cursor loadNestedQuery(Path path, String[] columns) {
        StringBuilder sb;
        Path path2 = path;
        String pathPrefixStart = pathToKey(path2);
        String pathPrefixEnd = pathPrefixStartToPrefixEnd(pathPrefixStart);
        String[] arguments = new String[(path2.size() + 3)];
        String whereClause = buildAncestorWhereClause(path2, arguments);
        new StringBuilder();
        String whereClause2 = sb.append(whereClause).append(" OR (path > ? AND path < ?)").toString();
        arguments[path2.size() + 1] = pathPrefixStart;
        arguments[path2.size() + 2] = pathPrefixEnd;
        return this.database.query(SERVER_CACHE_TABLE, columns, whereClause2, arguments, (String) null, (String) null, ClientCookie.PATH_ATTR);
    }

    private static String pathToKey(Path path) {
        StringBuilder sb;
        Path path2 = path;
        if (path2.isEmpty()) {
            return "/";
        }
        new StringBuilder();
        return sb.append(path2.toString()).append("/").toString();
    }

    private static String pathPrefixStartToPrefixEnd(String str) {
        StringBuilder sb;
        Throwable th;
        String prefix = str;
        if ($assertionsDisabled || prefix.endsWith("/")) {
            new StringBuilder();
            return sb.append(prefix.substring(0, prefix.length() - 1)).append('0').toString();
        }
        Throwable th2 = th;
        new AssertionError("Path keys must end with a '/'");
        throw th2;
    }

    private static String buildAncestorWhereClause(Path path, String[] strArr) {
        StringBuilder sb;
        Throwable th;
        Path path2 = path;
        String[] arguments = strArr;
        if ($assertionsDisabled || arguments.length >= path2.size() + 1) {
            int count = 0;
            new StringBuilder("(");
            StringBuilder whereClause = sb;
            while (!path2.isEmpty()) {
                StringBuilder append = whereClause.append(ClientCookie.PATH_ATTR);
                StringBuilder append2 = whereClause.append(" = ? OR ");
                arguments[count] = pathToKey(path2);
                path2 = path2.getParent();
                count++;
            }
            StringBuilder append3 = whereClause.append(ClientCookie.PATH_ATTR);
            StringBuilder append4 = whereClause.append(" = ?)");
            arguments[count] = pathToKey(Path.getEmptyPath());
            return whereClause.toString();
        }
        Throwable th2 = th;
        new AssertionError();
        throw th2;
    }

    /*  JADX ERROR: NullPointerException in pass: CodeShrinkVisitor
        java.lang.NullPointerException
        */
    private int removeNested(java.lang.String r15, com.firebase.client.core.Path r16) {
        /*
            r14 = this;
            r0 = r14
            r1 = r15
            r2 = r16
            java.lang.String r6 = "path >= ? AND path < ?"
            r3 = r6
            r6 = r2
            java.lang.String r6 = pathToKey(r6)
            r4 = r6
            r6 = r4
            java.lang.String r6 = pathPrefixStartToPrefixEnd(r6)
            r5 = r6
            r6 = r0
            android.database.sqlite.SQLiteDatabase r6 = r6.database
            r7 = r1
            r8 = r3
            r9 = 2
            java.lang.String[] r9 = new java.lang.String[r9]
            r13 = r9
            r9 = r13
            r10 = r13
            r11 = 0
            r12 = r4
            r10[r11] = r12
            r13 = r9
            r9 = r13
            r10 = r13
            r11 = 1
            r12 = r5
            r10[r11] = r12
            int r6 = r6.delete(r7, r8, r9)
            r0 = r6
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.firebase.client.android.SqlPersistenceStorageEngine.removeNested(java.lang.String, com.firebase.client.core.Path):int");
    }

    private static List<byte[]> splitBytes(byte[] bArr, int i) {
        List<byte[]> list;
        byte[] bytes = bArr;
        int size = i;
        int parts = ((bytes.length - 1) / size) + 1;
        new ArrayList(parts);
        List<byte[]> partList = list;
        for (int i2 = 0; i2 < parts; i2++) {
            int length = Math.min(size, bytes.length - (i2 * size));
            byte[] part = new byte[length];
            System.arraycopy(bytes, i2 * size, part, 0, length);
            boolean add = partList.add(part);
        }
        return partList;
    }

    private byte[] joinBytes(List<byte[]> list) {
        List<byte[]> payloads = list;
        int totalSize = 0;
        for (byte[] payload : payloads) {
            totalSize += payload.length;
        }
        byte[] buffer = new byte[totalSize];
        int currentBytePosition = 0;
        for (byte[] payload2 : payloads) {
            System.arraycopy(payload2, 0, buffer, currentBytePosition, payload2.length);
            currentBytePosition += payload2.length;
        }
        return buffer;
    }

    private byte[] serializeObject(Object object) {
        Throwable th;
        try {
            return this.jsonMapper.writeValueAsBytes(object);
        } catch (IOException e) {
            IOException e2 = e;
            Throwable th2 = th;
            new RuntimeException("Could not serialize leaf node", e2);
            throw th2;
        }
    }

    private Node deserializeNode(byte[] bArr) {
        IOException e;
        Throwable th;
        StringBuilder sb;
        String stringValue;
        Throwable th2;
        StringBuilder sb2;
        byte[] value = bArr;
        try {
            return NodeUtilities.NodeFromJSON(this.jsonMapper.readValue(value, Object.class));
        } catch (IOException e2) {
            e = e2;
            new String(value, "UTF-8");
            Throwable th3 = th2;
            new StringBuilder();
            new RuntimeException(sb2.append("Could not deserialize node: ").append(stringValue).toString(), e);
            throw th3;
        } catch (UnsupportedEncodingException e3) {
            UnsupportedEncodingException unsupportedEncodingException = e3;
            Throwable th4 = th;
            new StringBuilder();
            new RuntimeException(sb.append("Failed to serialize values to utf-8: ").append(Arrays.toString(value)).toString(), e);
            throw th4;
        }
    }

    private String commaSeparatedList(Collection<Long> items) {
        StringBuilder sb;
        new StringBuilder();
        StringBuilder list = sb;
        boolean first = true;
        for (Long longValue : items) {
            long item = longValue.longValue();
            if (!first) {
                StringBuilder append = list.append(",");
            }
            first = false;
            StringBuilder append2 = list.append(item);
        }
        return list.toString();
    }
}

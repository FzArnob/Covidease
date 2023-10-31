package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.database.CharArrayBuffer;
import android.database.CursorIndexOutOfBoundsException;
import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.sqlite.CursorWrapper;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@KeepName
@KeepForSdk
@SafeParcelable.Class(creator = "DataHolderCreator", validate = true)
public final class DataHolder extends AbstractSafeParcelable implements Closeable {
    @KeepForSdk
    public static final Parcelable.Creator<DataHolder> CREATOR;
    private static final Builder zaly;
    private boolean mClosed;
    @SafeParcelable.VersionField(mo25283id = 1000)
    private final int zalf;
    @SafeParcelable.Field(getter = "getColumns", mo25277id = 1)
    private final String[] zalq;
    private Bundle zalr;
    @SafeParcelable.Field(getter = "getWindows", mo25277id = 2)
    private final CursorWindow[] zals;
    @SafeParcelable.Field(getter = "getStatusCode", mo25277id = 3)
    private final int zalt;
    @SafeParcelable.Field(getter = "getMetadata", mo25277id = 4)
    private final Bundle zalu;
    private int[] zalv;
    private int zalw;
    private boolean zalx;

    public static class zaa extends RuntimeException {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public zaa(String str) {
            super(str);
        }
    }

    @SafeParcelable.Constructor
    DataHolder(@SafeParcelable.Param(mo25280id = 1000) int i, @SafeParcelable.Param(mo25280id = 1) String[] strArr, @SafeParcelable.Param(mo25280id = 2) CursorWindow[] cursorWindowArr, @SafeParcelable.Param(mo25280id = 3) int i2, @SafeParcelable.Param(mo25280id = 4) Bundle bundle) {
        this.mClosed = false;
        this.zalx = true;
        this.zalf = i;
        this.zalq = strArr;
        this.zals = cursorWindowArr;
        this.zalt = i2;
        this.zalu = bundle;
    }

    @KeepForSdk
    public static class Builder {
        /* access modifiers changed from: private */
        public final String[] zalq;
        /* access modifiers changed from: private */
        public final ArrayList<HashMap<String, Object>> zalz;
        private final String zama;
        private final HashMap<Object, Integer> zamb;
        private boolean zamc;
        private String zamd;

        private Builder(String[] strArr, String str) {
            ArrayList<HashMap<String, Object>> arrayList;
            HashMap<Object, Integer> hashMap;
            this.zalq = (String[]) Preconditions.checkNotNull(strArr);
            new ArrayList<>();
            this.zalz = arrayList;
            this.zama = str;
            new HashMap<>();
            this.zamb = hashMap;
            this.zamc = false;
            this.zamd = null;
        }

        public Builder zaa(HashMap<String, Object> hashMap) {
            int intValue;
            HashMap<String, Object> hashMap2 = hashMap;
            Asserts.checkNotNull(hashMap2);
            HashMap<String, Object> hashMap3 = hashMap2;
            if (this.zama == null) {
                intValue = -1;
            } else {
                Object obj = hashMap3.get(this.zama);
                Object obj2 = obj;
                if (obj == null) {
                    intValue = -1;
                } else {
                    Integer num = this.zamb.get(obj2);
                    Integer num2 = num;
                    if (num == null) {
                        Integer put = this.zamb.put(obj2, Integer.valueOf(this.zalz.size()));
                        intValue = -1;
                    } else {
                        intValue = num2.intValue();
                    }
                }
            }
            int i = intValue;
            int i2 = i;
            if (i == -1) {
                boolean add = this.zalz.add(hashMap2);
            } else {
                HashMap<String, Object> remove = this.zalz.remove(i2);
                this.zalz.add(i2, hashMap2);
            }
            this.zamc = false;
            return this;
        }

        @KeepForSdk
        public Builder withRow(ContentValues contentValues) {
            HashMap hashMap;
            ContentValues contentValues2 = contentValues;
            Asserts.checkNotNull(contentValues2);
            new HashMap(contentValues2.size());
            HashMap hashMap2 = hashMap;
            for (Map.Entry next : contentValues2.valueSet()) {
                Object put = hashMap2.put((String) next.getKey(), next.getValue());
            }
            return zaa((HashMap<String, Object>) hashMap2);
        }

        @KeepForSdk
        public DataHolder build(int i) {
            DataHolder dataHolder;
            new DataHolder(this, i, (Bundle) null, (zab) null);
            return dataHolder;
        }

        @KeepForSdk
        public DataHolder build(int i, Bundle bundle) {
            DataHolder dataHolder;
            new DataHolder(this, i, bundle, -1, (zab) null);
            return dataHolder;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ Builder(String[] strArr, String str, zab zab) {
            this(strArr, (String) null);
            String str2 = str;
            zab zab2 = zab;
        }
    }

    @KeepForSdk
    public DataHolder(String[] strArr, CursorWindow[] cursorWindowArr, int i, Bundle bundle) {
        this.mClosed = false;
        this.zalx = true;
        this.zalf = 1;
        this.zalq = (String[]) Preconditions.checkNotNull(strArr);
        this.zals = (CursorWindow[]) Preconditions.checkNotNull(cursorWindowArr);
        this.zalt = i;
        this.zalu = bundle;
        zaca();
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private DataHolder(com.google.android.gms.common.sqlite.CursorWrapper r10, int r11, android.os.Bundle r12) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r2 = r11
            r3 = r12
            r4 = r0
            r5 = r1
            java.lang.String[] r5 = r5.getColumnNames()
            r6 = r1
            android.database.CursorWindow[] r6 = zaa(r6)
            r7 = r2
            r8 = r3
            r4.<init>((java.lang.String[]) r5, (android.database.CursorWindow[]) r6, (int) r7, (android.os.Bundle) r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.data.DataHolder.<init>(com.google.android.gms.common.sqlite.CursorWrapper, int, android.os.Bundle):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    @com.google.android.gms.common.annotation.KeepForSdk
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DataHolder(android.database.Cursor r10, int r11, android.os.Bundle r12) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r2 = r11
            r3 = r12
            r4 = r0
            com.google.android.gms.common.sqlite.CursorWrapper r5 = new com.google.android.gms.common.sqlite.CursorWrapper
            r8 = r5
            r5 = r8
            r6 = r8
            r7 = r1
            r6.<init>(r7)
            r6 = r2
            r7 = r3
            r4.<init>((com.google.android.gms.common.sqlite.CursorWrapper) r5, (int) r6, (android.os.Bundle) r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.data.DataHolder.<init>(android.database.Cursor, int, android.os.Bundle):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private DataHolder(com.google.android.gms.common.data.DataHolder.Builder r10, int r11, android.os.Bundle r12) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r2 = r11
            r3 = r12
            r4 = r0
            r5 = r1
            java.lang.String[] r5 = r5.zalq
            r6 = r1
            r7 = -1
            android.database.CursorWindow[] r6 = zaa((com.google.android.gms.common.data.DataHolder.Builder) r6, (int) r7)
            r7 = r2
            r8 = 0
            r4.<init>((java.lang.String[]) r5, (android.database.CursorWindow[]) r6, (int) r7, (android.os.Bundle) r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.data.DataHolder.<init>(com.google.android.gms.common.data.DataHolder$Builder, int, android.os.Bundle):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private DataHolder(com.google.android.gms.common.data.DataHolder.Builder r11, int r12, android.os.Bundle r13, int r14) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r2 = r12
            r3 = r13
            r4 = r14
            r5 = r0
            r6 = r1
            java.lang.String[] r6 = r6.zalq
            r7 = r1
            r8 = -1
            android.database.CursorWindow[] r7 = zaa((com.google.android.gms.common.data.DataHolder.Builder) r7, (int) r8)
            r8 = r2
            r9 = r3
            r5.<init>((java.lang.String[]) r6, (android.database.CursorWindow[]) r7, (int) r8, (android.os.Bundle) r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.data.DataHolder.<init>(com.google.android.gms.common.data.DataHolder$Builder, int, android.os.Bundle, int):void");
    }

    public final void zaca() {
        Bundle bundle;
        new Bundle();
        this.zalr = bundle;
        for (int i = 0; i < this.zalq.length; i++) {
            this.zalr.putInt(this.zalq[i], i);
        }
        this.zalv = new int[this.zals.length];
        int i2 = 0;
        for (int i3 = 0; i3 < this.zals.length; i3++) {
            this.zalv[i3] = i2;
            i2 += this.zals[i3].getNumRows() - (i2 - this.zals[i3].getStartPosition());
        }
        this.zalw = i2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int i2 = i;
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeStringArray(parcel2, 1, this.zalq, false);
        SafeParcelWriter.writeTypedArray(parcel2, 2, this.zals, i2, false);
        SafeParcelWriter.writeInt(parcel2, 3, getStatusCode());
        SafeParcelWriter.writeBundle(parcel2, 4, getMetadata(), false);
        SafeParcelWriter.writeInt(parcel2, 1000, this.zalf);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
        if ((i2 & 1) != 0) {
            close();
        }
    }

    @KeepForSdk
    public final int getStatusCode() {
        return this.zalt;
    }

    @KeepForSdk
    public final Bundle getMetadata() {
        return this.zalu;
    }

    private static CursorWindow[] zaa(CursorWrapper cursorWrapper) {
        ArrayList arrayList;
        CursorWindow cursorWindow;
        CursorWrapper cursorWrapper2 = cursorWrapper;
        new ArrayList();
        ArrayList arrayList2 = arrayList;
        try {
            int count = cursorWrapper2.getCount();
            CursorWindow window = cursorWrapper2.getWindow();
            int i = 0;
            if (window != null && window.getStartPosition() == 0) {
                window.acquireReference();
                cursorWrapper2.setWindow((CursorWindow) null);
                boolean add = arrayList2.add(window);
                i = window.getNumRows();
            }
            while (i < count && cursorWrapper2.moveToPosition(i)) {
                CursorWindow window2 = cursorWrapper2.getWindow();
                CursorWindow cursorWindow2 = window2;
                if (window2 != null) {
                    cursorWindow2.acquireReference();
                    cursorWrapper2.setWindow((CursorWindow) null);
                } else {
                    new CursorWindow(false);
                    CursorWindow cursorWindow3 = cursorWindow;
                    cursorWindow2 = cursorWindow3;
                    cursorWindow3.setStartPosition(i);
                    cursorWrapper2.fillWindow(i, cursorWindow2);
                }
                if (cursorWindow2.getNumRows() == 0) {
                    break;
                }
                boolean add2 = arrayList2.add(cursorWindow2);
                i = cursorWindow2.getStartPosition() + cursorWindow2.getNumRows();
            }
            cursorWrapper2.close();
            return (CursorWindow[]) arrayList2.toArray(new CursorWindow[arrayList2.size()]);
        } catch (Throwable th) {
            Throwable th2 = th;
            cursorWrapper2.close();
            throw th2;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v0, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v12, resolved type: android.database.CursorWindow} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v20, resolved type: android.database.CursorWindow} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v46, resolved type: android.database.CursorWindow} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v49, resolved type: android.database.CursorWindow} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v50, resolved type: android.database.CursorWindow} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v78, resolved type: android.database.CursorWindow} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v80, resolved type: android.database.CursorWindow} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v82, resolved type: android.database.CursorWindow} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v87, resolved type: android.database.CursorWindow} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v89, resolved type: android.database.CursorWindow} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v91, resolved type: android.database.CursorWindow} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v93, resolved type: android.database.CursorWindow} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v95, resolved type: android.database.CursorWindow} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v15, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v101, resolved type: android.database.CursorWindow} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v102, resolved type: android.database.CursorWindow} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v106, resolved type: android.database.CursorWindow} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v1, resolved type: java.util.ArrayList} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.database.CursorWindow[] zaa(com.google.android.gms.common.data.DataHolder.Builder r24, int r25) {
        /*
            r3 = r24
            r4 = r25
            r17 = r3
            java.lang.String[] r17 = r17.zalq
            r0 = r17
            int r0 = r0.length
            r17 = r0
            if (r17 != 0) goto L_0x001c
            r17 = 0
            r0 = r17
            android.database.CursorWindow[] r0 = new android.database.CursorWindow[r0]
            r17 = r0
            r3 = r17
        L_0x001b:
            return r3
        L_0x001c:
            r17 = r4
            if (r17 < 0) goto L_0x0032
            r17 = r4
            r18 = r3
            java.util.ArrayList r18 = r18.zalz
            int r18 = r18.size()
            r0 = r17
            r1 = r18
            if (r0 < r1) goto L_0x0136
        L_0x0032:
            r17 = r3
            java.util.ArrayList r17 = r17.zalz
            r5 = r17
        L_0x003a:
            r17 = r5
            int r17 = r17.size()
            r6 = r17
            android.database.CursorWindow r17 = new android.database.CursorWindow
            r22 = r17
            r17 = r22
            r18 = r22
            r19 = 0
            r18.<init>(r19)
            r7 = r17
            java.util.ArrayList r17 = new java.util.ArrayList
            r22 = r17
            r17 = r22
            r18 = r22
            r18.<init>()
            r22 = r17
            r17 = r22
            r18 = r22
            r8 = r18
            r18 = r7
            boolean r17 = r17.add(r18)
            r17 = r7
            r18 = r3
            java.lang.String[] r18 = r18.zalq
            r0 = r18
            int r0 = r0.length
            r18 = r0
            boolean r17 = r17.setNumColumns(r18)
            r17 = 0
            r9 = r17
            r17 = 0
            r10 = r17
        L_0x0083:
            r17 = r10
            r18 = r6
            r0 = r17
            r1 = r18
            if (r0 >= r1) goto L_0x03b4
            r17 = r7
            boolean r17 = r17.allocRow()     // Catch:{ RuntimeException -> 0x02ed }
            if (r17 != 0) goto L_0x0148
            java.lang.String r17 = "DataHolder"
            r18 = r10
            r11 = r18
            r18 = 72
            java.lang.StringBuilder r19 = new java.lang.StringBuilder     // Catch:{ RuntimeException -> 0x02ed }
            r22 = r18
            r23 = r19
            r18 = r23
            r19 = r22
            r20 = r23
            r22 = r19
            r23 = r20
            r19 = r23
            r20 = r22
            r19.<init>(r20)     // Catch:{ RuntimeException -> 0x02ed }
            java.lang.String r19 = "Allocating additional cursor window for large data set (row "
            java.lang.StringBuilder r18 = r18.append(r19)     // Catch:{ RuntimeException -> 0x02ed }
            r19 = r11
            java.lang.StringBuilder r18 = r18.append(r19)     // Catch:{ RuntimeException -> 0x02ed }
            java.lang.String r19 = ")"
            java.lang.StringBuilder r18 = r18.append(r19)     // Catch:{ RuntimeException -> 0x02ed }
            java.lang.String r18 = r18.toString()     // Catch:{ RuntimeException -> 0x02ed }
            int r17 = android.util.Log.d(r17, r18)     // Catch:{ RuntimeException -> 0x02ed }
            android.database.CursorWindow r17 = new android.database.CursorWindow     // Catch:{ RuntimeException -> 0x02ed }
            r22 = r17
            r17 = r22
            r18 = r22
            r19 = 0
            r18.<init>(r19)     // Catch:{ RuntimeException -> 0x02ed }
            r22 = r17
            r17 = r22
            r18 = r22
            r7 = r18
            r18 = r10
            r17.setStartPosition(r18)     // Catch:{ RuntimeException -> 0x02ed }
            r17 = r7
            r18 = r3
            java.lang.String[] r18 = r18.zalq     // Catch:{ RuntimeException -> 0x02ed }
            r0 = r18
            int r0 = r0.length     // Catch:{ RuntimeException -> 0x02ed }
            r18 = r0
            boolean r17 = r17.setNumColumns(r18)     // Catch:{ RuntimeException -> 0x02ed }
            r17 = r8
            r18 = r7
            boolean r17 = r17.add(r18)     // Catch:{ RuntimeException -> 0x02ed }
            r17 = r7
            boolean r17 = r17.allocRow()     // Catch:{ RuntimeException -> 0x02ed }
            if (r17 != 0) goto L_0x0148
            java.lang.String r17 = "DataHolder"
            java.lang.String r18 = "Unable to allocate row to hold data."
            int r17 = android.util.Log.e(r17, r18)     // Catch:{ RuntimeException -> 0x02ed }
            r17 = r8
            r18 = r7
            boolean r17 = r17.remove(r18)     // Catch:{ RuntimeException -> 0x02ed }
            r17 = r8
            r18 = r8
            int r18 = r18.size()     // Catch:{ RuntimeException -> 0x02ed }
            r0 = r18
            android.database.CursorWindow[] r0 = new android.database.CursorWindow[r0]     // Catch:{ RuntimeException -> 0x02ed }
            r18 = r0
            java.lang.Object[] r17 = r17.toArray(r18)     // Catch:{ RuntimeException -> 0x02ed }
            android.database.CursorWindow[] r17 = (android.database.CursorWindow[]) r17     // Catch:{ RuntimeException -> 0x02ed }
            r3 = r17
            goto L_0x001b
        L_0x0136:
            r17 = r3
            java.util.ArrayList r17 = r17.zalz
            r18 = 0
            r19 = r4
            java.util.List r17 = r17.subList(r18, r19)
            r5 = r17
            goto L_0x003a
        L_0x0148:
            r17 = r5
            r18 = r10
            java.lang.Object r17 = r17.get(r18)     // Catch:{ RuntimeException -> 0x02ed }
            java.util.Map r17 = (java.util.Map) r17     // Catch:{ RuntimeException -> 0x02ed }
            r11 = r17
            r17 = 1
            r12 = r17
            r17 = 0
            r13 = r17
        L_0x015c:
            r17 = r13
            r18 = r3
            java.lang.String[] r18 = r18.zalq     // Catch:{ RuntimeException -> 0x02ed }
            r0 = r18
            int r0 = r0.length     // Catch:{ RuntimeException -> 0x02ed }
            r18 = r0
            r0 = r17
            r1 = r18
            if (r0 >= r1) goto L_0x0316
            r17 = r12
            if (r17 == 0) goto L_0x0316
            r17 = r3
            java.lang.String[] r17 = r17.zalq     // Catch:{ RuntimeException -> 0x02ed }
            r18 = r13
            r17 = r17[r18]     // Catch:{ RuntimeException -> 0x02ed }
            r14 = r17
            r17 = r11
            r18 = r14
            java.lang.Object r17 = r17.get(r18)     // Catch:{ RuntimeException -> 0x02ed }
            r22 = r17
            r17 = r22
            r18 = r22
            r15 = r18
            if (r17 != 0) goto L_0x01a0
            r17 = r7
            r18 = r10
            r19 = r13
            boolean r17 = r17.putNull(r18, r19)     // Catch:{ RuntimeException -> 0x02ed }
            r12 = r17
        L_0x019d:
            int r13 = r13 + 1
            goto L_0x015c
        L_0x01a0:
            r17 = r15
            r0 = r17
            boolean r0 = r0 instanceof java.lang.String     // Catch:{ RuntimeException -> 0x02ed }
            r17 = r0
            if (r17 == 0) goto L_0x01bb
            r17 = r7
            r18 = r15
            java.lang.String r18 = (java.lang.String) r18     // Catch:{ RuntimeException -> 0x02ed }
            r19 = r10
            r20 = r13
            boolean r17 = r17.putString(r18, r19, r20)     // Catch:{ RuntimeException -> 0x02ed }
            r12 = r17
            goto L_0x019d
        L_0x01bb:
            r17 = r15
            r0 = r17
            boolean r0 = r0 instanceof java.lang.Long     // Catch:{ RuntimeException -> 0x02ed }
            r17 = r0
            if (r17 == 0) goto L_0x01da
            r17 = r7
            r18 = r15
            java.lang.Long r18 = (java.lang.Long) r18     // Catch:{ RuntimeException -> 0x02ed }
            long r18 = r18.longValue()     // Catch:{ RuntimeException -> 0x02ed }
            r20 = r10
            r21 = r13
            boolean r17 = r17.putLong(r18, r20, r21)     // Catch:{ RuntimeException -> 0x02ed }
            r12 = r17
            goto L_0x019d
        L_0x01da:
            r17 = r15
            r0 = r17
            boolean r0 = r0 instanceof java.lang.Integer     // Catch:{ RuntimeException -> 0x02ed }
            r17 = r0
            if (r17 == 0) goto L_0x01fe
            r17 = r7
            r18 = r15
            java.lang.Integer r18 = (java.lang.Integer) r18     // Catch:{ RuntimeException -> 0x02ed }
            int r18 = r18.intValue()     // Catch:{ RuntimeException -> 0x02ed }
            r0 = r18
            long r0 = (long) r0     // Catch:{ RuntimeException -> 0x02ed }
            r18 = r0
            r20 = r10
            r21 = r13
            boolean r17 = r17.putLong(r18, r20, r21)     // Catch:{ RuntimeException -> 0x02ed }
            r12 = r17
            goto L_0x019d
        L_0x01fe:
            r17 = r15
            r0 = r17
            boolean r0 = r0 instanceof java.lang.Boolean     // Catch:{ RuntimeException -> 0x02ed }
            r17 = r0
            if (r17 == 0) goto L_0x0229
            r17 = r15
            java.lang.Boolean r17 = (java.lang.Boolean) r17     // Catch:{ RuntimeException -> 0x02ed }
            boolean r17 = r17.booleanValue()     // Catch:{ RuntimeException -> 0x02ed }
            r16 = r17
            r17 = r7
            r18 = r16
            if (r18 == 0) goto L_0x0226
            r18 = 1
        L_0x021a:
            r20 = r10
            r21 = r13
            boolean r17 = r17.putLong(r18, r20, r21)     // Catch:{ RuntimeException -> 0x02ed }
            r12 = r17
            goto L_0x019d
        L_0x0226:
            r18 = 0
            goto L_0x021a
        L_0x0229:
            r17 = r15
            r0 = r17
            boolean r0 = r0 instanceof byte[]     // Catch:{ RuntimeException -> 0x02ed }
            r17 = r0
            if (r17 == 0) goto L_0x0245
            r17 = r7
            r18 = r15
            byte[] r18 = (byte[]) r18     // Catch:{ RuntimeException -> 0x02ed }
            r19 = r10
            r20 = r13
            boolean r17 = r17.putBlob(r18, r19, r20)     // Catch:{ RuntimeException -> 0x02ed }
            r12 = r17
            goto L_0x019d
        L_0x0245:
            r17 = r15
            r0 = r17
            boolean r0 = r0 instanceof java.lang.Double     // Catch:{ RuntimeException -> 0x02ed }
            r17 = r0
            if (r17 == 0) goto L_0x0265
            r17 = r7
            r18 = r15
            java.lang.Double r18 = (java.lang.Double) r18     // Catch:{ RuntimeException -> 0x02ed }
            double r18 = r18.doubleValue()     // Catch:{ RuntimeException -> 0x02ed }
            r20 = r10
            r21 = r13
            boolean r17 = r17.putDouble(r18, r20, r21)     // Catch:{ RuntimeException -> 0x02ed }
            r12 = r17
            goto L_0x019d
        L_0x0265:
            r17 = r15
            r0 = r17
            boolean r0 = r0 instanceof java.lang.Float     // Catch:{ RuntimeException -> 0x02ed }
            r17 = r0
            if (r17 == 0) goto L_0x028a
            r17 = r7
            r18 = r15
            java.lang.Float r18 = (java.lang.Float) r18     // Catch:{ RuntimeException -> 0x02ed }
            float r18 = r18.floatValue()     // Catch:{ RuntimeException -> 0x02ed }
            r0 = r18
            double r0 = (double) r0     // Catch:{ RuntimeException -> 0x02ed }
            r18 = r0
            r20 = r10
            r21 = r13
            boolean r17 = r17.putDouble(r18, r20, r21)     // Catch:{ RuntimeException -> 0x02ed }
            r12 = r17
            goto L_0x019d
        L_0x028a:
            java.lang.IllegalArgumentException r17 = new java.lang.IllegalArgumentException     // Catch:{ RuntimeException -> 0x02ed }
            r22 = r17
            r17 = r22
            r18 = r22
            r19 = r15
            java.lang.String r19 = java.lang.String.valueOf(r19)     // Catch:{ RuntimeException -> 0x02ed }
            r16 = r19
            r19 = 32
            r20 = r14
            java.lang.String r20 = java.lang.String.valueOf(r20)     // Catch:{ RuntimeException -> 0x02ed }
            int r20 = r20.length()     // Catch:{ RuntimeException -> 0x02ed }
            int r19 = r19 + r20
            r20 = r16
            java.lang.String r20 = java.lang.String.valueOf(r20)     // Catch:{ RuntimeException -> 0x02ed }
            int r20 = r20.length()     // Catch:{ RuntimeException -> 0x02ed }
            int r19 = r19 + r20
            java.lang.StringBuilder r20 = new java.lang.StringBuilder     // Catch:{ RuntimeException -> 0x02ed }
            r22 = r19
            r23 = r20
            r19 = r23
            r20 = r22
            r21 = r23
            r22 = r20
            r23 = r21
            r20 = r23
            r21 = r22
            r20.<init>(r21)     // Catch:{ RuntimeException -> 0x02ed }
            java.lang.String r20 = "Unsupported object for column "
            java.lang.StringBuilder r19 = r19.append(r20)     // Catch:{ RuntimeException -> 0x02ed }
            r20 = r14
            java.lang.StringBuilder r19 = r19.append(r20)     // Catch:{ RuntimeException -> 0x02ed }
            java.lang.String r20 = ": "
            java.lang.StringBuilder r19 = r19.append(r20)     // Catch:{ RuntimeException -> 0x02ed }
            r20 = r16
            java.lang.StringBuilder r19 = r19.append(r20)     // Catch:{ RuntimeException -> 0x02ed }
            java.lang.String r19 = r19.toString()     // Catch:{ RuntimeException -> 0x02ed }
            r18.<init>(r19)     // Catch:{ RuntimeException -> 0x02ed }
            throw r17     // Catch:{ RuntimeException -> 0x02ed }
        L_0x02ed:
            r17 = move-exception
            r10 = r17
            r17 = 0
            r11 = r17
            r17 = r8
            int r17 = r17.size()
            r12 = r17
        L_0x02fc:
            r17 = r11
            r18 = r12
            r0 = r17
            r1 = r18
            if (r0 >= r1) goto L_0x03cc
            r17 = r8
            r18 = r11
            java.lang.Object r17 = r17.get(r18)
            android.database.CursorWindow r17 = (android.database.CursorWindow) r17
            r17.close()
            int r11 = r11 + 1
            goto L_0x02fc
        L_0x0316:
            r17 = r12
            if (r17 != 0) goto L_0x03af
            r17 = r9
            if (r17 == 0) goto L_0x0331
            java.lang.String r17 = "Could not add the value to a new CursorWindow. The size of value may be larger than what a CursorWindow can handle."
            r13 = r17
            com.google.android.gms.common.data.DataHolder$zaa r17 = new com.google.android.gms.common.data.DataHolder$zaa     // Catch:{ RuntimeException -> 0x02ed }
            r22 = r17
            r17 = r22
            r18 = r22
            r19 = r13
            r18.<init>(r19)     // Catch:{ RuntimeException -> 0x02ed }
            throw r17     // Catch:{ RuntimeException -> 0x02ed }
        L_0x0331:
            java.lang.String r17 = "DataHolder"
            r18 = r10
            r13 = r18
            r18 = 74
            java.lang.StringBuilder r19 = new java.lang.StringBuilder     // Catch:{ RuntimeException -> 0x02ed }
            r22 = r18
            r23 = r19
            r18 = r23
            r19 = r22
            r20 = r23
            r22 = r19
            r23 = r20
            r19 = r23
            r20 = r22
            r19.<init>(r20)     // Catch:{ RuntimeException -> 0x02ed }
            java.lang.String r19 = "Couldn't populate window data for row "
            java.lang.StringBuilder r18 = r18.append(r19)     // Catch:{ RuntimeException -> 0x02ed }
            r19 = r13
            java.lang.StringBuilder r18 = r18.append(r19)     // Catch:{ RuntimeException -> 0x02ed }
            java.lang.String r19 = " - allocating new window."
            java.lang.StringBuilder r18 = r18.append(r19)     // Catch:{ RuntimeException -> 0x02ed }
            java.lang.String r18 = r18.toString()     // Catch:{ RuntimeException -> 0x02ed }
            int r17 = android.util.Log.d(r17, r18)     // Catch:{ RuntimeException -> 0x02ed }
            r17 = r7
            r17.freeLastRow()     // Catch:{ RuntimeException -> 0x02ed }
            android.database.CursorWindow r17 = new android.database.CursorWindow     // Catch:{ RuntimeException -> 0x02ed }
            r22 = r17
            r17 = r22
            r18 = r22
            r19 = 0
            r18.<init>(r19)     // Catch:{ RuntimeException -> 0x02ed }
            r22 = r17
            r17 = r22
            r18 = r22
            r7 = r18
            r18 = r10
            r17.setStartPosition(r18)     // Catch:{ RuntimeException -> 0x02ed }
            r17 = r7
            r18 = r3
            java.lang.String[] r18 = r18.zalq     // Catch:{ RuntimeException -> 0x02ed }
            r0 = r18
            int r0 = r0.length     // Catch:{ RuntimeException -> 0x02ed }
            r18 = r0
            boolean r17 = r17.setNumColumns(r18)     // Catch:{ RuntimeException -> 0x02ed }
            r17 = r8
            r18 = r7
            boolean r17 = r17.add(r18)     // Catch:{ RuntimeException -> 0x02ed }
            int r10 = r10 + -1
            r17 = 1
            r9 = r17
        L_0x03ab:
            int r10 = r10 + 1
            goto L_0x0083
        L_0x03af:
            r17 = 0
            r9 = r17
            goto L_0x03ab
        L_0x03b4:
            r17 = r8
            r18 = r8
            int r18 = r18.size()
            r0 = r18
            android.database.CursorWindow[] r0 = new android.database.CursorWindow[r0]
            r18 = r0
            java.lang.Object[] r17 = r17.toArray(r18)
            android.database.CursorWindow[] r17 = (android.database.CursorWindow[]) r17
            r3 = r17
            goto L_0x001b
        L_0x03cc:
            r17 = r10
            throw r17
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.data.DataHolder.zaa(com.google.android.gms.common.data.DataHolder$Builder, int):android.database.CursorWindow[]");
    }

    private final void zaa(String str, int i) {
        String str2;
        String str3;
        Throwable th;
        Throwable th2;
        String str4 = str;
        int i2 = i;
        if (this.zalr == null || !this.zalr.containsKey(str4)) {
            IllegalArgumentException illegalArgumentException = r8;
            String valueOf = String.valueOf(str4);
            String str5 = valueOf;
            if (valueOf.length() != 0) {
                str3 = "No such column: ".concat(str5);
            } else {
                str3 = str2;
                new String("No such column: ");
            }
            IllegalArgumentException illegalArgumentException2 = new IllegalArgumentException(str3);
            throw illegalArgumentException;
        } else if (isClosed()) {
            Throwable th3 = th2;
            new IllegalArgumentException("Buffer is closed.");
            throw th3;
        } else if (i2 < 0 || i2 >= this.zalw) {
            Throwable th4 = th;
            new CursorIndexOutOfBoundsException(i2, this.zalw);
            throw th4;
        }
    }

    @KeepForSdk
    public final boolean hasColumn(String str) {
        return this.zalr.containsKey(str);
    }

    @KeepForSdk
    public final long getLong(String str, int i, int i2) {
        String str2 = str;
        int i3 = i;
        zaa(str2, i3);
        return this.zals[i2].getLong(i3, this.zalr.getInt(str2));
    }

    @KeepForSdk
    public final int getInteger(String str, int i, int i2) {
        String str2 = str;
        int i3 = i;
        zaa(str2, i3);
        return this.zals[i2].getInt(i3, this.zalr.getInt(str2));
    }

    @KeepForSdk
    public final String getString(String str, int i, int i2) {
        String str2 = str;
        int i3 = i;
        zaa(str2, i3);
        return this.zals[i2].getString(i3, this.zalr.getInt(str2));
    }

    @KeepForSdk
    public final boolean getBoolean(String str, int i, int i2) {
        String str2 = str;
        int i3 = i;
        zaa(str2, i3);
        return Long.valueOf(this.zals[i2].getLong(i3, this.zalr.getInt(str2))).longValue() == 1;
    }

    public final float zaa(String str, int i, int i2) {
        String str2 = str;
        int i3 = i;
        zaa(str2, i3);
        return this.zals[i2].getFloat(i3, this.zalr.getInt(str2));
    }

    public final double zab(String str, int i, int i2) {
        String str2 = str;
        int i3 = i;
        zaa(str2, i3);
        return this.zals[i2].getDouble(i3, this.zalr.getInt(str2));
    }

    @KeepForSdk
    public final byte[] getByteArray(String str, int i, int i2) {
        String str2 = str;
        int i3 = i;
        zaa(str2, i3);
        return this.zals[i2].getBlob(i3, this.zalr.getInt(str2));
    }

    public final void zaa(String str, int i, int i2, CharArrayBuffer charArrayBuffer) {
        String str2 = str;
        int i3 = i;
        zaa(str2, i3);
        this.zals[i2].copyStringToBuffer(i3, this.zalr.getInt(str2), charArrayBuffer);
    }

    @KeepForSdk
    public final boolean hasNull(String str, int i, int i2) {
        String str2 = str;
        int i3 = i;
        zaa(str2, i3);
        return this.zals[i2].isNull(i3, this.zalr.getInt(str2));
    }

    @KeepForSdk
    public final int getCount() {
        return this.zalw;
    }

    @KeepForSdk
    public final int getWindowIndex(int i) {
        int i2 = i;
        Preconditions.checkState(i2 >= 0 && i2 < this.zalw);
        int i3 = 0;
        while (true) {
            if (i3 >= this.zalv.length) {
                break;
            } else if (i2 < this.zalv[i3]) {
                i3--;
                break;
            } else {
                i3++;
            }
        }
        if (i3 == this.zalv.length) {
            i3--;
        }
        return i3;
    }

    @KeepForSdk
    public final boolean isClosed() {
        synchronized (this) {
            try {
                boolean z = this.mClosed;
                return z;
            } catch (Throwable th) {
                Throwable th2 = th;
                throw th2;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    @KeepForSdk
    public final void close() {
        synchronized (this) {
            try {
                if (!this.mClosed) {
                    this.mClosed = true;
                    for (int i = 0; i < this.zals.length; i++) {
                        this.zals[i].close();
                    }
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                throw th2;
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void finalize() throws Throwable {
        StringBuilder sb;
        try {
            if (this.zalx && this.zals.length > 0 && !isClosed()) {
                close();
                String obj = toString();
                new StringBuilder(178 + String.valueOf(obj).length());
                int e = Log.e("DataBuffer", sb.append("Internal data leak within a DataBuffer object detected!  Be sure to explicitly call release() on all DataBuffer extending objects when you are done with them. (internal object: ").append(obj).append(")").toString());
            }
            super.finalize();
        } catch (Throwable th) {
            Throwable th2 = th;
            super.finalize();
            throw th2;
        }
    }

    @KeepForSdk
    public static Builder builder(String[] strArr) {
        Builder builder;
        new Builder(strArr, (String) null, (zab) null);
        return builder;
    }

    @KeepForSdk
    public static DataHolder empty(int i) {
        DataHolder dataHolder;
        new DataHolder(zaly, i, (Bundle) null);
        return dataHolder;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    /* synthetic */ DataHolder(Builder builder, int i, Bundle bundle, zab zab) {
        this(builder, i, (Bundle) null);
        Bundle bundle2 = bundle;
        zab zab2 = zab;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    /* synthetic */ DataHolder(Builder builder, int i, Bundle bundle, int i2, zab zab) {
        this(builder, i, bundle, -1);
        int i3 = i2;
        zab zab2 = zab;
    }

    static {
        Parcelable.Creator<DataHolder> creator;
        Builder builder;
        new zac();
        CREATOR = creator;
        new zab(new String[0], (String) null);
        zaly = builder;
    }
}

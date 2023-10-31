package android.support.p000v4.content;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.support.annotation.GuardedBy;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.xmlpull.v1.XmlPullParserException;

/* renamed from: android.support.v4.content.FileProvider */
public class FileProvider extends ContentProvider {
    private static final String ATTR_NAME = "name";
    private static final String ATTR_PATH = "path";
    private static final String[] COLUMNS;
    private static final File DEVICE_ROOT;
    private static final String META_DATA_FILE_PROVIDER_PATHS = "android.support.FILE_PROVIDER_PATHS";
    private static final String TAG_CACHE_PATH = "cache-path";
    private static final String TAG_EXTERNAL = "external-path";
    private static final String TAG_EXTERNAL_CACHE = "external-cache-path";
    private static final String TAG_EXTERNAL_FILES = "external-files-path";
    private static final String TAG_EXTERNAL_MEDIA = "external-media-path";
    private static final String TAG_FILES_PATH = "files-path";
    private static final String TAG_ROOT_PATH = "root-path";
    @GuardedBy("sCache")
    private static HashMap<String, PathStrategy> sCache;
    private PathStrategy mStrategy;

    /* renamed from: android.support.v4.content.FileProvider$PathStrategy */
    interface PathStrategy {
        File getFileForUri(Uri uri);

        Uri getUriForFile(File file);
    }

    public FileProvider() {
    }

    static {
        File file;
        HashMap<String, PathStrategy> hashMap;
        String[] strArr = new String[2];
        strArr[0] = "_display_name";
        String[] strArr2 = strArr;
        strArr2[1] = "_size";
        COLUMNS = strArr2;
        new File("/");
        DEVICE_ROOT = file;
        new HashMap<>();
        sCache = hashMap;
    }

    public boolean onCreate() {
        return true;
    }

    public void attachInfo(@NonNull Context context, @NonNull ProviderInfo providerInfo) {
        Throwable th;
        Throwable th2;
        Context context2 = context;
        ProviderInfo info = providerInfo;
        super.attachInfo(context2, info);
        if (info.exported) {
            Throwable th3 = th2;
            new SecurityException("Provider must not be exported");
            throw th3;
        } else if (!info.grantUriPermissions) {
            Throwable th4 = th;
            new SecurityException("Provider must grant uri permissions");
            throw th4;
        } else {
            this.mStrategy = getPathStrategy(context2, info.authority);
        }
    }

    public static Uri getUriForFile(@NonNull Context context, @NonNull String authority, @NonNull File file) {
        return getPathStrategy(context, authority).getUriForFile(file);
    }

    public Cursor query(@NonNull Uri uri, @Nullable String[] strArr, @Nullable String str, @Nullable String[] strArr2, @Nullable String str2) {
        MatrixCursor matrixCursor;
        String[] projection = strArr;
        String str3 = str;
        String[] strArr3 = strArr2;
        String str4 = str2;
        File file = this.mStrategy.getFileForUri(uri);
        if (projection == null) {
            projection = COLUMNS;
        }
        String[] cols = new String[projection.length];
        Object[] values = new Object[projection.length];
        int i = 0;
        String[] strArr4 = projection;
        int length = strArr4.length;
        for (int i2 = 0; i2 < length; i2++) {
            String col = strArr4[i2];
            if ("_display_name".equals(col)) {
                cols[i] = "_display_name";
                int i3 = i;
                i++;
                values[i3] = file.getName();
            } else if ("_size".equals(col)) {
                cols[i] = "_size";
                int i4 = i;
                i++;
                values[i4] = Long.valueOf(file.length());
            }
        }
        String[] cols2 = copyOf(cols, i);
        Object[] values2 = copyOf(values, i);
        new MatrixCursor(cols2, 1);
        MatrixCursor cursor = matrixCursor;
        cursor.addRow(values2);
        return cursor;
    }

    public String getType(@NonNull Uri uri) {
        String mime;
        File file = this.mStrategy.getFileForUri(uri);
        int lastDot = file.getName().lastIndexOf(46);
        if (lastDot < 0 || (mime = MimeTypeMap.getSingleton().getMimeTypeFromExtension(file.getName().substring(lastDot + 1))) == null) {
            return "application/octet-stream";
        }
        return mime;
    }

    public Uri insert(@NonNull Uri uri, ContentValues contentValues) {
        Throwable th;
        Uri uri2 = uri;
        ContentValues contentValues2 = contentValues;
        Throwable th2 = th;
        new UnsupportedOperationException("No external inserts");
        throw th2;
    }

    public int update(@NonNull Uri uri, ContentValues contentValues, @Nullable String str, @Nullable String[] strArr) {
        Throwable th;
        Uri uri2 = uri;
        ContentValues contentValues2 = contentValues;
        String str2 = str;
        String[] strArr2 = strArr;
        Throwable th2 = th;
        new UnsupportedOperationException("No external updates");
        throw th2;
    }

    public int delete(@NonNull Uri uri, @Nullable String str, @Nullable String[] strArr) {
        String str2 = str;
        String[] strArr2 = strArr;
        return this.mStrategy.getFileForUri(uri).delete() ? 1 : 0;
    }

    public ParcelFileDescriptor openFile(@NonNull Uri uri, @NonNull String mode) throws FileNotFoundException {
        return ParcelFileDescriptor.open(this.mStrategy.getFileForUri(uri), modeToMode(mode));
    }

    private static PathStrategy getPathStrategy(Context context, String str) {
        Throwable th;
        Throwable th2;
        Context context2 = context;
        String authority = str;
        HashMap<String, PathStrategy> hashMap = sCache;
        HashMap<String, PathStrategy> hashMap2 = hashMap;
        synchronized (hashMap) {
            try {
                PathStrategy strat = sCache.get(authority);
                if (strat == null) {
                    strat = parsePathStrategy(context2, authority);
                    PathStrategy put = sCache.put(authority, strat);
                }
                return strat;
            } catch (IOException e) {
                IOException e2 = e;
                Throwable th3 = th2;
                new IllegalArgumentException("Failed to parse android.support.FILE_PROVIDER_PATHS meta-data", e2);
                throw th3;
            } catch (XmlPullParserException e3) {
                XmlPullParserException e4 = e3;
                Throwable th4 = th;
                new IllegalArgumentException("Failed to parse android.support.FILE_PROVIDER_PATHS meta-data", e4);
                throw th4;
            } catch (Throwable th5) {
                Throwable th6 = th5;
                HashMap<String, PathStrategy> hashMap3 = hashMap2;
                throw th6;
            }
        }
    }

    private static PathStrategy parsePathStrategy(Context context, String str) throws IOException, XmlPullParserException {
        SimplePathStrategy simplePathStrategy;
        Throwable th;
        Context context2 = context;
        String authority = str;
        new SimplePathStrategy(authority);
        SimplePathStrategy strat = simplePathStrategy;
        XmlResourceParser in = context2.getPackageManager().resolveContentProvider(authority, 128).loadXmlMetaData(context2.getPackageManager(), META_DATA_FILE_PROVIDER_PATHS);
        if (in == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Missing android.support.FILE_PROVIDER_PATHS meta-data");
            throw th2;
        }
        while (true) {
            int next = in.next();
            int type = next;
            if (next == 1) {
                return strat;
            }
            if (type == 2) {
                String tag = in.getName();
                String name = in.getAttributeValue((String) null, ATTR_NAME);
                String path = in.getAttributeValue((String) null, "path");
                File target = null;
                if (TAG_ROOT_PATH.equals(tag)) {
                    target = DEVICE_ROOT;
                } else if (TAG_FILES_PATH.equals(tag)) {
                    target = context2.getFilesDir();
                } else if (TAG_CACHE_PATH.equals(tag)) {
                    target = context2.getCacheDir();
                } else if (TAG_EXTERNAL.equals(tag)) {
                    target = Environment.getExternalStorageDirectory();
                } else if (TAG_EXTERNAL_FILES.equals(tag)) {
                    File[] externalFilesDirs = ContextCompat.getExternalFilesDirs(context2, (String) null);
                    if (externalFilesDirs.length > 0) {
                        target = externalFilesDirs[0];
                    }
                } else if (TAG_EXTERNAL_CACHE.equals(tag)) {
                    File[] externalCacheDirs = ContextCompat.getExternalCacheDirs(context2);
                    if (externalCacheDirs.length > 0) {
                        target = externalCacheDirs[0];
                    }
                } else if (Build.VERSION.SDK_INT >= 21 && TAG_EXTERNAL_MEDIA.equals(tag)) {
                    File[] externalMediaDirs = context2.getExternalMediaDirs();
                    if (externalMediaDirs.length > 0) {
                        target = externalMediaDirs[0];
                    }
                }
                if (target != null) {
                    strat.addRoot(name, buildPath(target, path));
                }
            }
        }
    }

    /* renamed from: android.support.v4.content.FileProvider$SimplePathStrategy */
    static class SimplePathStrategy implements PathStrategy {
        private final String mAuthority;
        private final HashMap<String, File> mRoots;

        SimplePathStrategy(String authority) {
            HashMap<String, File> hashMap;
            new HashMap<>();
            this.mRoots = hashMap;
            this.mAuthority = authority;
        }

        /* access modifiers changed from: package-private */
        public void addRoot(String str, File file) {
            Throwable th;
            StringBuilder sb;
            Throwable th2;
            String name = str;
            File root = file;
            if (TextUtils.isEmpty(name)) {
                Throwable th3 = th2;
                new IllegalArgumentException("Name must not be empty");
                throw th3;
            }
            try {
                File put = this.mRoots.put(name, root.getCanonicalFile());
            } catch (IOException e) {
                IOException e2 = e;
                Throwable th4 = th;
                new StringBuilder();
                new IllegalArgumentException(sb.append("Failed to resolve canonical path for ").append(root).toString(), e2);
                throw th4;
            }
        }

        public Uri getUriForFile(File file) {
            Throwable th;
            StringBuilder sb;
            String path;
            StringBuilder sb2;
            Uri.Builder builder;
            Throwable th2;
            StringBuilder sb3;
            File file2 = file;
            try {
                String path2 = file2.getCanonicalPath();
                Map.Entry<String, File> mostSpecific = null;
                for (Map.Entry<String, File> root : this.mRoots.entrySet()) {
                    String rootPath = root.getValue().getPath();
                    if (path2.startsWith(rootPath) && (mostSpecific == null || rootPath.length() > mostSpecific.getValue().getPath().length())) {
                        mostSpecific = root;
                    }
                }
                if (mostSpecific == null) {
                    Throwable th3 = th2;
                    new StringBuilder();
                    new IllegalArgumentException(sb3.append("Failed to find configured root that contains ").append(path2).toString());
                    throw th3;
                }
                String rootPath2 = mostSpecific.getValue().getPath();
                if (rootPath2.endsWith("/")) {
                    path = path2.substring(rootPath2.length());
                } else {
                    path = path2.substring(rootPath2.length() + 1);
                }
                new StringBuilder();
                String path3 = sb2.append(Uri.encode(mostSpecific.getKey())).append('/').append(Uri.encode(path, "/")).toString();
                new Uri.Builder();
                return builder.scheme("content").authority(this.mAuthority).encodedPath(path3).build();
            } catch (IOException e) {
                IOException iOException = e;
                Throwable th4 = th;
                new StringBuilder();
                new IllegalArgumentException(sb.append("Failed to resolve canonical path for ").append(file2).toString());
                throw th4;
            }
        }

        public File getFileForUri(Uri uri) {
            File file;
            Throwable th;
            StringBuilder sb;
            Throwable th2;
            Throwable th3;
            StringBuilder sb2;
            Uri uri2 = uri;
            String path = uri2.getEncodedPath();
            int splitIndex = path.indexOf(47, 1);
            String tag = Uri.decode(path.substring(1, splitIndex));
            String path2 = Uri.decode(path.substring(splitIndex + 1));
            File root = this.mRoots.get(tag);
            if (root == null) {
                Throwable th4 = th3;
                new StringBuilder();
                new IllegalArgumentException(sb2.append("Unable to find configured root for ").append(uri2).toString());
                throw th4;
            }
            new File(root, path2);
            File file2 = file;
            try {
                File file3 = file2.getCanonicalFile();
                if (file3.getPath().startsWith(root.getPath())) {
                    return file3;
                }
                Throwable th5 = th2;
                new SecurityException("Resolved path jumped beyond configured root");
                throw th5;
            } catch (IOException e) {
                IOException iOException = e;
                Throwable th6 = th;
                new StringBuilder();
                new IllegalArgumentException(sb.append("Failed to resolve canonical path for ").append(file2).toString());
                throw th6;
            }
        }
    }

    private static int modeToMode(String str) {
        int modeBits;
        Throwable th;
        StringBuilder sb;
        String mode = str;
        if ("r".equals(mode)) {
            modeBits = 268435456;
        } else if ("w".equals(mode) || "wt".equals(mode)) {
            modeBits = 738197504;
        } else if ("wa".equals(mode)) {
            modeBits = 704643072;
        } else if ("rw".equals(mode)) {
            modeBits = 939524096;
        } else if ("rwt".equals(mode)) {
            modeBits = 1006632960;
        } else {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Invalid mode: ").append(mode).toString());
            throw th2;
        }
        return modeBits;
    }

    private static File buildPath(File base, String... segments) {
        File file;
        File cur = base;
        String[] strArr = segments;
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            String segment = strArr[i];
            if (segment != null) {
                new File(cur, segment);
                cur = file;
            }
        }
        return cur;
    }

    private static String[] copyOf(String[] original, int i) {
        int newLength = i;
        String[] result = new String[newLength];
        System.arraycopy(original, 0, result, 0, newLength);
        return result;
    }

    private static Object[] copyOf(Object[] original, int i) {
        int newLength = i;
        Object[] result = new Object[newLength];
        System.arraycopy(original, 0, result, 0, newLength);
        return result;
    }
}

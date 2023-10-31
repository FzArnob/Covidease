package android.support.p000v4.provider;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;

@RequiresApi(19)
/* renamed from: android.support.v4.provider.DocumentsContractApi19 */
class DocumentsContractApi19 {
    private static final int FLAG_VIRTUAL_DOCUMENT = 512;
    private static final String TAG = "DocumentFile";

    public static boolean isVirtual(Context context, Uri uri) {
        Context context2 = context;
        Uri self = uri;
        if (!DocumentsContract.isDocumentUri(context2, self)) {
            return false;
        }
        return (getFlags(context2, self) & 512) != 0;
    }

    @Nullable
    public static String getName(Context context, Uri self) {
        return queryForString(context, self, "_display_name", (String) null);
    }

    @Nullable
    private static String getRawType(Context context, Uri self) {
        return queryForString(context, self, "mime_type", (String) null);
    }

    @Nullable
    public static String getType(Context context, Uri self) {
        String rawType = getRawType(context, self);
        if ("vnd.android.document/directory".equals(rawType)) {
            return null;
        }
        return rawType;
    }

    public static long getFlags(Context context, Uri self) {
        return queryForLong(context, self, "flags", 0);
    }

    public static boolean isDirectory(Context context, Uri self) {
        return "vnd.android.document/directory".equals(getRawType(context, self));
    }

    public static boolean isFile(Context context, Uri self) {
        String type = getRawType(context, self);
        if ("vnd.android.document/directory".equals(type) || TextUtils.isEmpty(type)) {
            return false;
        }
        return true;
    }

    public static long lastModified(Context context, Uri self) {
        return queryForLong(context, self, "last_modified", 0);
    }

    public static long length(Context context, Uri self) {
        return queryForLong(context, self, "_size", 0);
    }

    public static boolean canRead(Context context, Uri uri) {
        Context context2 = context;
        Uri self = uri;
        if (context2.checkCallingOrSelfUriPermission(self, 1) != 0) {
            return false;
        }
        if (TextUtils.isEmpty(getRawType(context2, self))) {
            return false;
        }
        return true;
    }

    public static boolean canWrite(Context context, Uri uri) {
        Context context2 = context;
        Uri self = uri;
        if (context2.checkCallingOrSelfUriPermission(self, 2) != 0) {
            return false;
        }
        String type = getRawType(context2, self);
        int flags = queryForInt(context2, self, "flags", 0);
        if (TextUtils.isEmpty(type)) {
            return false;
        }
        if ((flags & 4) != 0) {
            return true;
        }
        if ("vnd.android.document/directory".equals(type) && (flags & 8) != 0) {
            return true;
        }
        if (TextUtils.isEmpty(type) || (flags & 2) == 0) {
            return false;
        }
        return true;
    }

    public static boolean exists(Context context, Uri self) {
        StringBuilder sb;
        Cursor c = null;
        try {
            c = context.getContentResolver().query(self, new String[]{"document_id"}, (String) null, (String[]) null, (String) null);
            boolean z = c.getCount() > 0;
            closeQuietly(c);
            return z;
        } catch (Exception e) {
            Exception e2 = e;
            new StringBuilder();
            int w = Log.w(TAG, sb.append("Failed query: ").append(e2).toString());
            closeQuietly(c);
            return false;
        } catch (Throwable th) {
            Throwable th2 = th;
            closeQuietly(c);
            throw th2;
        }
    }

    @Nullable
    private static String queryForString(Context context, Uri self, String str, @Nullable String str2) {
        StringBuilder sb;
        String column = str;
        String defaultValue = str2;
        try {
            Cursor c = context.getContentResolver().query(self, new String[]{column}, (String) null, (String[]) null, (String) null);
            if (!c.moveToFirst() || c.isNull(0)) {
                String str3 = defaultValue;
                closeQuietly(c);
                return str3;
            }
            String string = c.getString(0);
            closeQuietly(c);
            return string;
        } catch (Exception e) {
            Exception e2 = e;
            new StringBuilder();
            int w = Log.w(TAG, sb.append("Failed query: ").append(e2).toString());
            closeQuietly((AutoCloseable) null);
            return defaultValue;
        } catch (Throwable th) {
            closeQuietly((AutoCloseable) null);
            throw th;
        }
    }

    private static int queryForInt(Context context, Uri self, String column, int defaultValue) {
        return (int) queryForLong(context, self, column, (long) defaultValue);
    }

    private static long queryForLong(Context context, Uri self, String str, long j) {
        StringBuilder sb;
        String column = str;
        long defaultValue = j;
        try {
            Cursor c = context.getContentResolver().query(self, new String[]{column}, (String) null, (String[]) null, (String) null);
            if (!c.moveToFirst() || c.isNull(0)) {
                long j2 = defaultValue;
                closeQuietly(c);
                return j2;
            }
            long j3 = c.getLong(0);
            closeQuietly(c);
            return j3;
        } catch (Exception e) {
            Exception e2 = e;
            new StringBuilder();
            int w = Log.w(TAG, sb.append("Failed query: ").append(e2).toString());
            closeQuietly((AutoCloseable) null);
            return defaultValue;
        } catch (Throwable th) {
            closeQuietly((AutoCloseable) null);
            throw th;
        }
    }

    private static void closeQuietly(@Nullable AutoCloseable autoCloseable) {
        AutoCloseable closeable = autoCloseable;
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e2) {
                Exception exc = e2;
            }
        }
    }

    private DocumentsContractApi19() {
    }
}

package android.support.p000v4.p002os;

import android.os.Build;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.IOException;

/* renamed from: android.support.v4.os.EnvironmentCompat */
public final class EnvironmentCompat {
    public static final String MEDIA_UNKNOWN = "unknown";
    private static final String TAG = "EnvironmentCompat";

    public static String getStorageState(File file) {
        StringBuilder sb;
        File path = file;
        if (Build.VERSION.SDK_INT >= 19) {
            return Environment.getStorageState(path);
        }
        try {
            if (path.getCanonicalPath().startsWith(Environment.getExternalStorageDirectory().getCanonicalPath())) {
                return Environment.getExternalStorageState();
            }
        } catch (IOException e) {
            new StringBuilder();
            int w = Log.w(TAG, sb.append("Failed to resolve canonical path: ").append(e).toString());
        }
        return MEDIA_UNKNOWN;
    }

    private EnvironmentCompat() {
    }
}

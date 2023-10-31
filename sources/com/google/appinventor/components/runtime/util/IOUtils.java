package com.google.appinventor.components.runtime.util;

import android.util.Log;
import java.io.Closeable;
import java.io.IOException;

public final class IOUtils {
    public IOUtils() {
    }

    public static void closeQuietly(String str, Closeable closeable) {
        String str2 = str;
        Closeable closeable2 = closeable;
        if (closeable2 != null) {
            try {
                closeable2.close();
            } catch (IOException e) {
                int w = Log.w(str2, "Failed to close resource", e);
            }
        }
    }
}

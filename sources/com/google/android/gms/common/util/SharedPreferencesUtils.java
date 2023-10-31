package com.google.android.gms.common.util;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.io.File;

@KeepForSdk
public class SharedPreferencesUtils {
    private SharedPreferencesUtils() {
    }

    @KeepForSdk
    @Deprecated
    public static void publishWorldReadableSharedPreferences(Context context, SharedPreferences.Editor editor, String str) {
        File file;
        File file2;
        SharedPreferences.Editor editor2 = editor;
        String str2 = str;
        new File(context.getApplicationInfo().dataDir, "shared_prefs");
        File file3 = file;
        File file4 = file3;
        File parentFile = file3.getParentFile();
        File file5 = parentFile;
        if (parentFile != null) {
            boolean executable = file5.setExecutable(true, false);
        }
        boolean executable2 = file4.setExecutable(true, false);
        boolean commit = editor2.commit();
        new File(file4, String.valueOf(str2).concat(".xml"));
        boolean readable = file2.setReadable(true, false);
    }
}

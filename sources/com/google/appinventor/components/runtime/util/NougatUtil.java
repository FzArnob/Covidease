package com.google.appinventor.components.runtime.util;

import android.app.Activity;
import android.net.Uri;
import android.os.Build;
import android.support.p000v4.content.FileProvider;
import android.util.Log;
import com.google.appinventor.components.runtime.Form;
import java.io.File;

public final class NougatUtil {
    private static final String LOG_TAG = NougatUtil.class.getSimpleName();

    private NougatUtil() {
    }

    public static Uri getPackageUri(Form form, File file) {
        StringBuilder sb;
        Form form2 = form;
        File file2 = file;
        if (Build.VERSION.SDK_INT < 24) {
            return Uri.fromFile(file2);
        }
        String packageName = form2.$context().getPackageName();
        int d = Log.d(LOG_TAG, "packageName = ".concat(String.valueOf(packageName)));
        Activity $context = form2.$context();
        new StringBuilder();
        return FileProvider.getUriForFile($context, sb.append(packageName).append(".provider").toString(), file2);
    }
}

package com.google.android.gms.common.util;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.regex.Pattern;

@KeepForSdk
@VisibleForTesting
public class Strings {
    private static final Pattern zzhh = Pattern.compile("\\$\\{(.*?)\\}");

    private Strings() {
    }

    @Nullable
    @KeepForSdk
    public static String emptyToNull(@Nullable String str) {
        String str2 = str;
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        return str2;
    }

    @KeepForSdk
    public static boolean isEmptyOrWhitespace(@Nullable String str) {
        String str2 = str;
        return str2 == null || str2.trim().isEmpty();
    }
}

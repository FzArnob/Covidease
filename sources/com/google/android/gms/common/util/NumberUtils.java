package com.google.android.gms.common.util;

import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
@VisibleForTesting
public class NumberUtils {
    @KeepForSdk
    public static long parseHexLong(String str) {
        Throwable th;
        StringBuilder sb;
        String str2 = str;
        if (str2.length() > 16) {
            Throwable th2 = th;
            new StringBuilder(37 + String.valueOf(str2).length());
            new NumberFormatException(sb.append("Invalid input: ").append(str2).append(" exceeds 16 characters").toString());
            throw th2;
        } else if (str2.length() != 16) {
            return Long.parseLong(str2, 16);
        } else {
            return Long.parseLong(str2.substring(8), 16) | (Long.parseLong(str2.substring(0, 8), 16) << 32);
        }
    }

    private NumberUtils() {
    }
}

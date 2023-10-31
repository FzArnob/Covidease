package com.google.android.gms.common.util;

import android.util.Base64;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class Base64Utils {
    public Base64Utils() {
    }

    @KeepForSdk
    public static byte[] decode(String str) {
        String str2 = str;
        if (str2 == null) {
            return null;
        }
        return Base64.decode(str2, 0);
    }

    @KeepForSdk
    public static byte[] decodeUrlSafe(String str) {
        String str2 = str;
        if (str2 == null) {
            return null;
        }
        return Base64.decode(str2, 10);
    }

    @KeepForSdk
    public static byte[] decodeUrlSafeNoPadding(String str) {
        String str2 = str;
        if (str2 == null) {
            return null;
        }
        return Base64.decode(str2, 11);
    }

    @KeepForSdk
    public static String encode(byte[] bArr) {
        byte[] bArr2 = bArr;
        if (bArr2 == null) {
            return null;
        }
        return Base64.encodeToString(bArr2, 0);
    }

    @KeepForSdk
    public static String encodeUrlSafe(byte[] bArr) {
        byte[] bArr2 = bArr;
        if (bArr2 == null) {
            return null;
        }
        return Base64.encodeToString(bArr2, 10);
    }

    @KeepForSdk
    public static String encodeUrlSafeNoPadding(byte[] bArr) {
        byte[] bArr2 = bArr;
        if (bArr2 == null) {
            return null;
        }
        return Base64.encodeToString(bArr2, 11);
    }
}

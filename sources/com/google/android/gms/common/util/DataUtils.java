package com.google.android.gms.common.util;

import android.database.CharArrayBuffer;
import android.graphics.Bitmap;
import android.text.TextUtils;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.io.ByteArrayOutputStream;

@KeepForSdk
public final class DataUtils {
    public DataUtils() {
    }

    @KeepForSdk
    public static void copyStringToBuffer(String str, CharArrayBuffer charArrayBuffer) {
        String str2 = str;
        CharArrayBuffer charArrayBuffer2 = charArrayBuffer;
        if (TextUtils.isEmpty(str2)) {
            charArrayBuffer2.sizeCopied = 0;
        } else if (charArrayBuffer2.data == null || charArrayBuffer2.data.length < str2.length()) {
            charArrayBuffer2.data = str2.toCharArray();
        } else {
            str2.getChars(0, str2.length(), charArrayBuffer2.data, 0);
        }
        charArrayBuffer2.sizeCopied = str2.length();
    }

    @KeepForSdk
    public static byte[] loadImageBytes(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream;
        new ByteArrayOutputStream();
        ByteArrayOutputStream byteArrayOutputStream2 = byteArrayOutputStream;
        boolean compress = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream2);
        return byteArrayOutputStream2.toByteArray();
    }
}

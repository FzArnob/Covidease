package org.shaded.apache.http.util;

import java.io.UnsupportedEncodingException;

public final class EncodingUtils {
    public static String getString(byte[] bArr, int i, int i2, String str) {
        Throwable th;
        String str2;
        String str3;
        Throwable th2;
        byte[] data = bArr;
        int offset = i;
        int length = i2;
        String charset = str;
        if (data == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("Parameter may not be null");
            throw th3;
        } else if (charset == null || charset.length() == 0) {
            Throwable th4 = th;
            new IllegalArgumentException("charset may not be null or empty");
            throw th4;
        } else {
            try {
                String str4 = str3;
                new String(data, offset, length, charset);
                return str4;
            } catch (UnsupportedEncodingException e) {
                UnsupportedEncodingException unsupportedEncodingException = e;
                new String(data, offset, length);
                return str2;
            }
        }
    }

    public static String getString(byte[] bArr, String str) {
        Throwable th;
        byte[] data = bArr;
        String charset = str;
        if (data != null) {
            return getString(data, 0, data.length, charset);
        }
        Throwable th2 = th;
        new IllegalArgumentException("Parameter may not be null");
        throw th2;
    }

    public static byte[] getBytes(String str, String str2) {
        Throwable th;
        Throwable th2;
        String data = str;
        String charset = str2;
        if (data == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("data may not be null");
            throw th3;
        } else if (charset == null || charset.length() == 0) {
            Throwable th4 = th;
            new IllegalArgumentException("charset may not be null or empty");
            throw th4;
        } else {
            try {
                return data.getBytes(charset);
            } catch (UnsupportedEncodingException e) {
                UnsupportedEncodingException unsupportedEncodingException = e;
                return data.getBytes();
            }
        }
    }

    public static byte[] getAsciiBytes(String str) {
        Throwable th;
        Throwable th2;
        String data = str;
        if (data == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("Parameter may not be null");
            throw th3;
        }
        try {
            return data.getBytes("US-ASCII");
        } catch (UnsupportedEncodingException e) {
            UnsupportedEncodingException unsupportedEncodingException = e;
            Throwable th4 = th;
            new Error("HttpClient requires ASCII support");
            throw th4;
        }
    }

    public static String getAsciiString(byte[] bArr, int i, int i2) {
        Throwable th;
        String str;
        Throwable th2;
        byte[] data = bArr;
        int offset = i;
        int length = i2;
        if (data == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("Parameter may not be null");
            throw th3;
        }
        try {
            String str2 = str;
            new String(data, offset, length, "US-ASCII");
            return str2;
        } catch (UnsupportedEncodingException e) {
            UnsupportedEncodingException unsupportedEncodingException = e;
            Throwable th4 = th;
            new Error("HttpClient requires ASCII support");
            throw th4;
        }
    }

    public static String getAsciiString(byte[] bArr) {
        Throwable th;
        byte[] data = bArr;
        if (data != null) {
            return getAsciiString(data, 0, data.length);
        }
        Throwable th2 = th;
        new IllegalArgumentException("Parameter may not be null");
        throw th2;
    }

    private EncodingUtils() {
    }
}

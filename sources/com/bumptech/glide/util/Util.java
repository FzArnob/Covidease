package com.bumptech.glide.util;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Looper;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Queue;

public final class Util {
    private static final char[] HEX_CHAR_ARRAY = "0123456789abcdef".toCharArray();
    private static final char[] SHA_1_CHARS = new char[40];
    private static final char[] SHA_256_CHARS = new char[64];

    private Util() {
    }

    public static String sha256BytesToHex(byte[] bArr) {
        byte[] bytes = bArr;
        char[] cArr = SHA_256_CHARS;
        char[] cArr2 = cArr;
        synchronized (cArr) {
            try {
                String bytesToHex = bytesToHex(bytes, SHA_256_CHARS);
                return bytesToHex;
            } catch (Throwable th) {
                Throwable th2 = th;
                char[] cArr3 = cArr2;
                throw th2;
            }
        }
    }

    public static String sha1BytesToHex(byte[] bArr) {
        byte[] bytes = bArr;
        char[] cArr = SHA_1_CHARS;
        char[] cArr2 = cArr;
        synchronized (cArr) {
            try {
                String bytesToHex = bytesToHex(bytes, SHA_1_CHARS);
                return bytesToHex;
            } catch (Throwable th) {
                Throwable th2 = th;
                char[] cArr3 = cArr2;
                throw th2;
            }
        }
    }

    private static String bytesToHex(byte[] bArr, char[] cArr) {
        String str;
        byte[] bytes = bArr;
        char[] hexChars = cArr;
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 255;
            hexChars[j * 2] = HEX_CHAR_ARRAY[v >>> 4];
            hexChars[(j * 2) + 1] = HEX_CHAR_ARRAY[v & 15];
        }
        new String(hexChars);
        return str;
    }

    @Deprecated
    public static int getSize(Bitmap bitmap) {
        return getBitmapByteSize(bitmap);
    }

    @TargetApi(19)
    public static int getBitmapByteSize(Bitmap bitmap) {
        Bitmap bitmap2 = bitmap;
        if (Build.VERSION.SDK_INT >= 19) {
            try {
                return bitmap2.getAllocationByteCount();
            } catch (NullPointerException e) {
                NullPointerException nullPointerException = e;
            }
        }
        return bitmap2.getHeight() * bitmap2.getRowBytes();
    }

    public static int getBitmapByteSize(int width, int height, Bitmap.Config config) {
        return width * height * getBytesPerPixel(config);
    }

    private static int getBytesPerPixel(Bitmap.Config config) {
        int bytesPerPixel;
        Bitmap.Config config2 = config;
        if (config2 == null) {
            config2 = Bitmap.Config.ARGB_8888;
        }
        switch (C15361.$SwitchMap$android$graphics$Bitmap$Config[config2.ordinal()]) {
            case 1:
                bytesPerPixel = 1;
                break;
            case 2:
            case 3:
                bytesPerPixel = 2;
                break;
            default:
                bytesPerPixel = 4;
                break;
        }
        return bytesPerPixel;
    }

    /* renamed from: com.bumptech.glide.util.Util$1 */
    static /* synthetic */ class C15361 {
        static final /* synthetic */ int[] $SwitchMap$android$graphics$Bitmap$Config = new int[Bitmap.Config.values().length];

        static {
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.ALPHA_8.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
                NoSuchFieldError noSuchFieldError = e;
            }
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.RGB_565.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
                NoSuchFieldError noSuchFieldError2 = e2;
            }
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.ARGB_4444.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
                NoSuchFieldError noSuchFieldError3 = e3;
            }
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.ARGB_8888.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
                NoSuchFieldError noSuchFieldError4 = e4;
            }
        }
    }

    public static boolean isValidDimensions(int width, int height) {
        return isValidDimension(width) && isValidDimension(height);
    }

    private static boolean isValidDimension(int i) {
        int dimen = i;
        return dimen > 0 || dimen == Integer.MIN_VALUE;
    }

    public static void assertMainThread() {
        Throwable th;
        if (!isOnMainThread()) {
            Throwable th2 = th;
            new IllegalArgumentException("You must call this method on the main thread");
            throw th2;
        }
    }

    public static void assertBackgroundThread() {
        Throwable th;
        if (!isOnBackgroundThread()) {
            Throwable th2 = th;
            new IllegalArgumentException("YOu must call this method on a background thread");
            throw th2;
        }
    }

    public static boolean isOnMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static boolean isOnBackgroundThread() {
        return !isOnMainThread();
    }

    public static <T> Queue<T> createQueue(int size) {
        Queue<T> queue;
        new ArrayDeque(size);
        return queue;
    }

    public static <T> List<T> getSnapshot(Collection<T> collection) {
        List<T> list;
        Collection<T> other = collection;
        new ArrayList(other.size());
        List<T> result = list;
        for (T item : other) {
            boolean add = result.add(item);
        }
        return result;
    }
}

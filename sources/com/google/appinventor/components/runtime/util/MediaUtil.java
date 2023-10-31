package com.google.appinventor.components.runtime.util;

import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.VideoView;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.ReplForm;
import com.google.appinventor.components.runtime.errors.PermissionException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MediaUtil {
    private static String REPL_ASSET_DIR = null;
    private static ConcurrentHashMap<String, String> hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    /* access modifiers changed from: private */
    public static boolean ik13O8j78hPb27XeipAJHUcJmdHxYMJFw4gYbTGvLKIOs7SX1F7x61gocQ17TlNc = true;
    private static final Map<String, File> yKzj3OK1ig8r7pqFZ5OXQyoqJiWnRvwjZPZ1kORJGZPQRb8FuKJuM2qAKu5QCSLT;

    static /* synthetic */ BitmapFactory.Options hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Form form, InputStream inputStream, String str) {
        BitmapFactory.Options options;
        int width;
        int height;
        int i;
        BitmapFactory.Options options2;
        StringBuilder sb;
        String str2 = str;
        Form form2 = form;
        new BitmapFactory.Options();
        BitmapFactory.Options options3 = options;
        BitmapFactory.Options options4 = options3;
        options3.inJustDecodeBounds = true;
        Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(inputStream, options4);
        int i2 = options4.outWidth;
        int i3 = options4.outHeight;
        Display defaultDisplay = ((WindowManager) form2.getSystemService("window")).getDefaultDisplay();
        if (Form.getCompatibilityMode()) {
            width = 720;
            height = 840;
        } else {
            width = (int) (((float) defaultDisplay.getWidth()) / form2.deviceDensity());
            height = (int) (((float) defaultDisplay.getHeight()) / form2.deviceDensity());
        }
        int i4 = 1;
        while (true) {
            i = i4;
            if (i2 / i <= width || i3 / i <= height) {
                new BitmapFactory.Options();
                BitmapFactory.Options options5 = options2;
                new StringBuilder("getBitmapOptions: sampleSize = ");
                int d = Log.d("MediaUtil", sb.append(i).append(" mediaPath = ").append(str2).append(" maxWidth = ").append(width).append(" maxHeight = ").append(height).append(" display width = ").append(defaultDisplay.getWidth()).append(" display height = ").append(defaultDisplay.getHeight()).toString());
                options5.inSampleSize = i;
            } else {
                i4 = i << 1;
            }
        }
        new BitmapFactory.Options();
        BitmapFactory.Options options52 = options2;
        new StringBuilder("getBitmapOptions: sampleSize = ");
        int d2 = Log.d("MediaUtil", sb.append(i).append(" mediaPath = ").append(str2).append(" maxWidth = ").append(width).append(" maxHeight = ").append(height).append(" display width = ").append(defaultDisplay.getWidth()).append(" display height = ").append(defaultDisplay.getHeight()).toString());
        options52.inSampleSize = i;
        return options52;
    }

    /* renamed from: com.google.appinventor.components.runtime.util.MediaUtil$b */
    enum C1178b {
        ;
        
        public static final int CfJsMCmg8U782C2ivbbep8ZFrAD6R9Wq7P09zMpUKCFkpiEYodk6t8FdSxHzlHKV = 0;
        public static final int KYHvnTv0AWOO8SeFCXsiNXCxcIirISbo8kAOvMnivJLnqAuCVxfixET1OT3ZpHhw = 0;
        public static final int LYXVmrSApJtjkX58iaWYcT93zXSX0GhbrHTAKbm3TBRJ5avsjGCN1sJz61Za2zkA = 0;
        public static final int Y32BqWfeOgj0oQvRmJ9m9e2zU29bRliwner9JMtAtUwBfF75tSr3PcVpECeplsq5 = 0;
        public static final int f3jXQdr7SaO4jKCWPTlGDFsZc4anfRmkf59KPIcTiLfRAexdccxYBXXB8h0vpeF7 = 0;
        public static final int kkTI9AxohjOECYVBpzZOuVO0b9llYVM2xqggkPHvpPoNGTREwN5YZmwC10Gk8X2Q = 0;
        public static final int qGWPhAhbC1tFmaPVJJBRlTuvq3PUzuXLHqwJdn0xNpZNZK6IOnxu2nEwTjRZ3Ww = 0;
        public static final int siVQGK7skYIQ7zI3RxZVmSEN1N3qEwDlBDPORHd716EIgwqH2EWQFUJrvV0SXYUL = 0;
        public static final int zsMcUi8jymhI0yeycaZW56W8YNMDBLoH4OZHLSlj2A3S38zGlu25pkABN4p8cs = 0;

        public static int[] hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME() {
            return (int[]) vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R.clone();
        }

        static {
            CfJsMCmg8U782C2ivbbep8ZFrAD6R9Wq7P09zMpUKCFkpiEYodk6t8FdSxHzlHKV = 1;
            f3jXQdr7SaO4jKCWPTlGDFsZc4anfRmkf59KPIcTiLfRAexdccxYBXXB8h0vpeF7 = 2;
            siVQGK7skYIQ7zI3RxZVmSEN1N3qEwDlBDPORHd716EIgwqH2EWQFUJrvV0SXYUL = 3;
            KYHvnTv0AWOO8SeFCXsiNXCxcIirISbo8kAOvMnivJLnqAuCVxfixET1OT3ZpHhw = 4;
            LYXVmrSApJtjkX58iaWYcT93zXSX0GhbrHTAKbm3TBRJ5avsjGCN1sJz61Za2zkA = 5;
            kkTI9AxohjOECYVBpzZOuVO0b9llYVM2xqggkPHvpPoNGTREwN5YZmwC10Gk8X2Q = 6;
            zsMcUi8jymhI0yeycaZW56W8YNMDBLoH4OZHLSlj2A3S38zGlu25pkABN4p8cs = 7;
            Y32BqWfeOgj0oQvRmJ9m9e2zU29bRliwner9JMtAtUwBfF75tSr3PcVpECeplsq5 = 8;
            qGWPhAhbC1tFmaPVJJBRlTuvq3PUzuXLHqwJdn0xNpZNZK6IOnxu2nEwTjRZ3Ww = 9;
            vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        }
    }

    static {
        Map<String, File> map;
        ConcurrentHashMap<String, String> concurrentHashMap;
        new HashMap();
        yKzj3OK1ig8r7pqFZ5OXQyoqJiWnRvwjZPZ1kORJGZPQRb8FuKJuM2qAKu5QCSLT = map;
        new ConcurrentHashMap<>(2);
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = concurrentHashMap;
    }

    /* renamed from: com.google.appinventor.components.runtime.util.MediaUtil$c */
    static class C1179c<T> {
        String EF39AXcqvcVSzkIDlhtBxpJQpYLF2tkAZ1Yh8aWSrM6HvDBggPKm9DsfgSWrxWia;
        T vOibdtOfNxMVixWab9VDGgvD9L4Kqb2ZDQBius3PMdhiP1dPN68Z9GzWJA49TUbE;
        private volatile boolean yHAbLPerXNK4pCwn5nqbt3OeUjDvQdxh29RmVa0moB4dUtgatbeaGoP5jClPUWFb;

        private C1179c() {
            this.yHAbLPerXNK4pCwn5nqbt3OeUjDvQdxh29RmVa0moB4dUtgatbeaGoP5jClPUWFb = false;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ C1179c(byte b) {
            this();
            byte b2 = b;
        }

        public final synchronized void BG2b8dRaEUNcWY5vxXAWfSqyg8ZvS4tysLlCvHPUDp1bRFO56FHQ3a9NvDEKgkS() {
            synchronized (this) {
                while (!this.yHAbLPerXNK4pCwn5nqbt3OeUjDvQdxh29RmVa0moB4dUtgatbeaGoP5jClPUWFb) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                    }
                }
            }
        }

        public final synchronized void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(T t) {
            T t2 = t;
            synchronized (this) {
                this.yHAbLPerXNK4pCwn5nqbt3OeUjDvQdxh29RmVa0moB4dUtgatbeaGoP5jClPUWFb = true;
                this.vOibdtOfNxMVixWab9VDGgvD9L4Kqb2ZDQBius3PMdhiP1dPN68Z9GzWJA49TUbE = t2;
                notifyAll();
            }
        }

        public final synchronized void Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB(String str) {
            String str2 = str;
            synchronized (this) {
                this.yHAbLPerXNK4pCwn5nqbt3OeUjDvQdxh29RmVa0moB4dUtgatbeaGoP5jClPUWFb = true;
                this.EF39AXcqvcVSzkIDlhtBxpJQpYLF2tkAZ1Yh8aWSrM6HvDBggPKm9DsfgSWrxWia = str2;
                notifyAll();
            }
        }
    }

    private MediaUtil() {
    }

    private static String sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt(String str) {
        StringBuilder sb;
        StringBuilder sb2;
        String str2 = str;
        if (REPL_ASSET_DIR == null) {
            new StringBuilder();
            REPL_ASSET_DIR = sb2.append(Environment.getExternalStorageDirectory().getPath()).append("/Makeroid/assets/").toString();
        }
        new StringBuilder();
        return sb.append(REPL_ASSET_DIR).append(str2).toString();
    }

    private static String mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT(String str) throws IOException {
        Throwable th;
        Throwable th2;
        File file;
        URL url;
        String str2 = str;
        try {
            new URL(str2);
            new File(url.toURI());
            return file.getAbsolutePath();
        } catch (IllegalArgumentException e) {
            Throwable th3 = th2;
            new IOException("Unable to determine file path of file url ".concat(String.valueOf(str2)));
            throw th3;
        } catch (Exception e2) {
            Throwable th4 = th;
            new IOException("Unable to determine file path of file url ".concat(String.valueOf(str2)));
            throw th4;
        }
    }

    private static int hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Form form, String str) {
        StringBuilder sb;
        Form form2 = form;
        String str2 = str;
        new StringBuilder();
        if (str2.contains(sb.append(form2.getExternalFilesDir("")).toString())) {
            return C1178b.Y32BqWfeOgj0oQvRmJ9m9e2zU29bRliwner9JMtAtUwBfF75tSr3PcVpECeplsq5;
        }
        if (str2.startsWith(form2.getFilesDir().getAbsolutePath())) {
            return C1178b.qGWPhAhbC1tFmaPVJJBRlTuvq3PUzuXLHqwJdn0xNpZNZK6IOnxu2nEwTjRZ3Ww;
        }
        if (str2.startsWith("/sdcard/") || str2.startsWith(Environment.getExternalStorageDirectory().getAbsolutePath())) {
            return C1178b.siVQGK7skYIQ7zI3RxZVmSEN1N3qEwDlBDPORHd716EIgwqH2EWQFUJrvV0SXYUL;
        }
        if (str2.startsWith("content://contacts/")) {
            return C1178b.zsMcUi8jymhI0yeycaZW56W8YNMDBLoH4OZHLSlj2A3S38zGlu25pkABN4p8cs;
        }
        if (str2.startsWith("content://")) {
            return C1178b.kkTI9AxohjOECYVBpzZOuVO0b9llYVM2xqggkPHvpPoNGTREwN5YZmwC10Gk8X2Q;
        }
        try {
            new URL(str2);
            if (str2.startsWith("file:")) {
                return C1178b.KYHvnTv0AWOO8SeFCXsiNXCxcIirISbo8kAOvMnivJLnqAuCVxfixET1OT3ZpHhw;
            }
            return C1178b.LYXVmrSApJtjkX58iaWYcT93zXSX0GhbrHTAKbm3TBRJ5avsjGCN1sJz61Za2zkA;
        } catch (MalformedURLException e) {
            if (!(form2 instanceof ReplForm)) {
                return C1178b.CfJsMCmg8U782C2ivbbep8ZFrAD6R9Wq7P09zMpUKCFkpiEYodk6t8FdSxHzlHKV;
            }
            if (((ReplForm) form2).isAssetsLoaded()) {
                return C1178b.f3jXQdr7SaO4jKCWPTlGDFsZc4anfRmkf59KPIcTiLfRAexdccxYBXXB8h0vpeF7;
            }
            return C1178b.CfJsMCmg8U782C2ivbbep8ZFrAD6R9Wq7P09zMpUKCFkpiEYodk6t8FdSxHzlHKV;
        }
    }

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other method in class */
    private static String m72hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Form form, String str) throws IOException {
        String str2;
        Form form2 = form;
        String str3 = str;
        if (!hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.containsKey(str3)) {
            String str4 = str3;
            String[] list = form2.getAssets().list("");
            String[] strArr = list;
            int length = Array.getLength(list);
            int i = 0;
            while (true) {
                if (i >= length) {
                    str2 = null;
                    break;
                }
                String str5 = strArr[i];
                String str6 = str5;
                if (str5.equalsIgnoreCase(str4)) {
                    str2 = str6;
                    break;
                }
                i++;
            }
            String str7 = str2;
            String str8 = str7;
            if (str7 == null) {
                return null;
            }
            String put = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.put(str3, str8);
        }
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.get(str3);
    }

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other method in class */
    private static InputStream m70hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Form form, String str) throws IOException {
        Form form2 = form;
        String str2 = str;
        try {
            return form2.getAssets().open(str2);
        } catch (IOException e) {
            IOException iOException = e;
            String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(form2, str2);
            String str3 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2;
            if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 != null) {
                return form2.getAssets().open(str3);
            }
            throw iOException;
        }
    }

    /* renamed from: com.google.appinventor.components.runtime.util.MediaUtil$3 */
    static /* synthetic */ class C11763 {
        static final /* synthetic */ int[] hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = new int[C1178b.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME().length];

        static {
            try {
                int[] iArr = hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO;
                int i = C1178b.CfJsMCmg8U782C2ivbbep8ZFrAD6R9Wq7P09zMpUKCFkpiEYodk6t8FdSxHzlHKV;
                iArr[0] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                int[] iArr2 = hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO;
                int i2 = C1178b.qGWPhAhbC1tFmaPVJJBRlTuvq3PUzuXLHqwJdn0xNpZNZK6IOnxu2nEwTjRZ3Ww;
                iArr2[8] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                int[] iArr3 = hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO;
                int i3 = C1178b.Y32BqWfeOgj0oQvRmJ9m9e2zU29bRliwner9JMtAtUwBfF75tSr3PcVpECeplsq5;
                iArr3[7] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                int[] iArr4 = hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO;
                int i4 = C1178b.f3jXQdr7SaO4jKCWPTlGDFsZc4anfRmkf59KPIcTiLfRAexdccxYBXXB8h0vpeF7;
                iArr4[1] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                int[] iArr5 = hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO;
                int i5 = C1178b.siVQGK7skYIQ7zI3RxZVmSEN1N3qEwDlBDPORHd716EIgwqH2EWQFUJrvV0SXYUL;
                iArr5[2] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                int[] iArr6 = hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO;
                int i6 = C1178b.KYHvnTv0AWOO8SeFCXsiNXCxcIirISbo8kAOvMnivJLnqAuCVxfixET1OT3ZpHhw;
                iArr6[3] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                int[] iArr7 = hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO;
                int i7 = C1178b.LYXVmrSApJtjkX58iaWYcT93zXSX0GhbrHTAKbm3TBRJ5avsjGCN1sJz61Za2zkA;
                iArr7[4] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                int[] iArr8 = hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO;
                int i8 = C1178b.kkTI9AxohjOECYVBpzZOuVO0b9llYVM2xqggkPHvpPoNGTREwN5YZmwC10Gk8X2Q;
                iArr8[5] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                int[] iArr9 = hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO;
                int i9 = C1178b.zsMcUi8jymhI0yeycaZW56W8YNMDBLoH4OZHLSlj2A3S38zGlu25pkABN4p8cs;
                iArr9[6] = 9;
            } catch (NoSuchFieldError e9) {
            }
        }
    }

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other method in class */
    private static InputStream m71hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Form form, String str, int i) throws IOException {
        Throwable th;
        StringBuilder sb;
        URL url;
        InputStream inputStream;
        InputStream inputStream2;
        InputStream inputStream3;
        Throwable th2;
        StringBuilder sb2;
        Form form2 = form;
        String str2 = str;
        switch (C11763.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO[i - 1]) {
            case 1:
                return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(form2, str2);
            case 2:
            case 3:
                new FileInputStream(str2);
                return inputStream3;
            case 4:
                form2.assertPermission("android.permission.READ_EXTERNAL_STORAGE");
                new FileInputStream(sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt(str2));
                return inputStream2;
            case 5:
                form2.assertPermission("android.permission.READ_EXTERNAL_STORAGE");
                new FileInputStream(str2);
                return inputStream;
            case 6:
                if (isExternalFileUrl(str2)) {
                    form2.assertPermission("android.permission.READ_EXTERNAL_STORAGE");
                    break;
                }
                break;
            case 7:
                break;
            case 8:
                return form2.getContentResolver().openInputStream(Uri.parse(str2));
            case 9:
                InputStream openContactPhotoInputStreamHelper = HoneycombMR1Util.openContactPhotoInputStreamHelper(form2.getContentResolver(), Uri.parse(str2));
                InputStream inputStream4 = openContactPhotoInputStreamHelper;
                if (openContactPhotoInputStreamHelper != null) {
                    return inputStream4;
                }
                Throwable th3 = th;
                new StringBuilder("Unable to open contact photo ");
                new IOException(sb.append(str2).append(".").toString());
                throw th3;
            default:
                Throwable th4 = th2;
                new StringBuilder("Unable to open media ");
                new IOException(sb2.append(str2).append(".").toString());
                throw th4;
        }
        new URL(str2);
        return url.openStream();
    }

    public static InputStream openMedia(Form form, String str) throws IOException {
        Form form2 = form;
        String str2 = str;
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(form2, str2, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(form2, str2));
    }

    public static File copyMediaToTempFile(Form form, String str) throws IOException {
        Form form2 = form;
        String str2 = str;
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(form2, str2, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(form2, str2));
    }

    private static File hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Form form, String str, int i) throws IOException {
        InputStream hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(form, str, i);
        File file = null;
        try {
            File createTempFile = File.createTempFile("AI_Media_", (String) null);
            file = createTempFile;
            createTempFile.deleteOnExit();
            String writeStreamToFile = FileUtil.writeStreamToFile(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2, file.getAbsolutePath());
            File file2 = file;
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.close();
            if (file != null) {
                boolean delete = file.delete();
            }
            return file2;
        } catch (IOException e) {
            throw e;
        } catch (Throwable th) {
            Throwable th2 = th;
            hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.close();
            if (file != null) {
                boolean delete2 = file.delete();
            }
            throw th2;
        }
    }

    private static File B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(Form form, String str, int i) throws IOException {
        StringBuilder sb;
        StringBuilder sb2;
        Form form2 = form;
        String str2 = str;
        int i2 = i;
        File file = yKzj3OK1ig8r7pqFZ5OXQyoqJiWnRvwjZPZ1kORJGZPQRb8FuKJuM2qAKu5QCSLT.get(str2);
        File file2 = file;
        if (file == null || !file2.exists()) {
            new StringBuilder("Copying media ");
            int i3 = Log.i("MediaUtil", sb.append(str2).append(" to temp file...").toString());
            file2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(form2, str2, i2);
            new StringBuilder("Finished copying media ");
            int i4 = Log.i("MediaUtil", sb2.append(str2).append(" to temp file ").append(file2.getAbsolutePath()).toString());
            File put = yKzj3OK1ig8r7pqFZ5OXQyoqJiWnRvwjZPZ1kORJGZPQRb8FuKJuM2qAKu5QCSLT.put(str2, file2);
        }
        return file2;
    }

    public static BitmapDrawable getBitmapDrawable(Form form, String str) throws IOException {
        C1179c cVar;
        AsyncCallbackPair asyncCallbackPair;
        Throwable th;
        Throwable th2;
        Form form2 = form;
        String str2 = str;
        if (str2 == null || str2.length() == 0) {
            return null;
        }
        if (form2.highQuality) {
            return (BitmapDrawable) getHighQualityImage(form2, str2);
        }
        new C1179c((byte) 0);
        C1179c cVar2 = cVar;
        final C1179c cVar3 = cVar2;
        new AsyncCallbackPair<BitmapDrawable>() {
            public final /* synthetic */ void onSuccess(Object obj) {
                cVar3.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((BitmapDrawable) obj);
            }

            public final void onFailure(String str) {
                cVar3.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB(str);
            }
        };
        getBitmapDrawableAsync(form2, str2, asyncCallbackPair);
        cVar2.BG2b8dRaEUNcWY5vxXAWfSqyg8ZvS4tysLlCvHPUDp1bRFO56FHQ3a9NvDEKgkS();
        BitmapDrawable bitmapDrawable = (BitmapDrawable) cVar2.vOibdtOfNxMVixWab9VDGgvD9L4Kqb2ZDQBius3PMdhiP1dPN68Z9GzWJA49TUbE;
        BitmapDrawable bitmapDrawable2 = bitmapDrawable;
        if (bitmapDrawable != null) {
            return bitmapDrawable2;
        }
        String str3 = cVar2.EF39AXcqvcVSzkIDlhtBxpJQpYLF2tkAZ1Yh8aWSrM6HvDBggPKm9DsfgSWrxWia;
        String str4 = str3;
        if (str3.startsWith("PERMISSION_DENIED:")) {
            Throwable th3 = th2;
            new PermissionException(str4.split(":")[1]);
            throw th3;
        }
        Throwable th4 = th;
        new IOException(str4);
        throw th4;
    }

    public static void getBitmapDrawableAsync(Form form, String str, AsyncCallbackPair<BitmapDrawable> asyncCallbackPair) {
        Runnable runnable;
        Form form2 = form;
        String str2 = str;
        AsyncCallbackPair<BitmapDrawable> asyncCallbackPair2 = asyncCallbackPair;
        if (str2 == null || str2.length() == 0) {
            asyncCallbackPair2.onSuccess(null);
            return;
        }
        final String str3 = str2;
        final Form form3 = form2;
        final int hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(form2, str2);
        final AsyncCallbackPair<BitmapDrawable> asyncCallbackPair3 = asyncCallbackPair2;
        new Runnable() {
            public final void run() {
                StringBuilder sb;
                ByteArrayOutputStream byteArrayOutputStream;
                StringBuilder sb2;
                Object obj;
                StringBuilder sb3;
                ByteArrayInputStream byteArrayInputStream;
                StringBuilder sb4;
                BitmapDrawable bitmapDrawable;
                StringBuilder sb5;
                StringBuilder sb6;
                StringBuilder sb7;
                BitmapDrawable bitmapDrawable2;
                new StringBuilder("mediaPath = ");
                int d = Log.d("MediaUtil", sb.append(str3).toString());
                new ByteArrayOutputStream();
                ByteArrayOutputStream byteArrayOutputStream2 = byteArrayOutputStream;
                byte[] bArr = new byte[4096];
                try {
                    InputStream B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T2 = MediaUtil.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(form3, str3, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2);
                    while (true) {
                        int read = B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T2.read(bArr);
                        int i = read;
                        if (read <= 0) {
                            break;
                        }
                        byteArrayOutputStream2.write(bArr, 0, i);
                    }
                    byte[] byteArray = byteArrayOutputStream2.toByteArray();
                    if (B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T2 != null) {
                        try {
                            B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T2.close();
                        } catch (Exception e) {
                            int w = Log.w("MediaUtil", "Unexpected error on close", e);
                        }
                    }
                    try {
                        byteArrayOutputStream2.close();
                    } catch (Exception e2) {
                    }
                    new ByteArrayInputStream(byteArray);
                    ByteArrayInputStream byteArrayInputStream2 = byteArrayInputStream;
                    try {
                        byteArrayInputStream2.mark(byteArray.length);
                        BitmapFactory.Options hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = MediaUtil.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(form3, (InputStream) byteArrayInputStream2, str3);
                        byteArrayInputStream2.reset();
                        new BitmapDrawable(form3.getResources(), MediaUtil.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((InputStream) byteArrayInputStream2, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2));
                        BitmapDrawable bitmapDrawable3 = bitmapDrawable;
                        BitmapDrawable bitmapDrawable4 = bitmapDrawable3;
                        bitmapDrawable3.setTargetDensity(form3.getResources().getDisplayMetrics());
                        if (MediaUtil.ik13O8j78hPb27XeipAJHUcJmdHxYMJFw4gYbTGvLKIOs7SX1F7x61gocQ17TlNc || hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.inSampleSize != 1 || form3.deviceDensity() == 1.0f) {
                            asyncCallbackPair3.onSuccess(bitmapDrawable4);
                            try {
                                byteArrayInputStream2.close();
                            } catch (Exception e3) {
                                int w2 = Log.w("MediaUtil", "Unexpected error on close", e3);
                            }
                        } else {
                            int deviceDensity = (int) (form3.deviceDensity() * ((float) bitmapDrawable4.getIntrinsicWidth()));
                            int deviceDensity2 = (int) (form3.deviceDensity() * ((float) bitmapDrawable4.getIntrinsicHeight()));
                            new StringBuilder("form.deviceDensity() = ");
                            int d2 = Log.d("MediaUtil", sb5.append(form3.deviceDensity()).toString());
                            new StringBuilder("originalBitmapDrawable.getIntrinsicWidth() = ");
                            int d3 = Log.d("MediaUtil", sb6.append(bitmapDrawable4.getIntrinsicWidth()).toString());
                            new StringBuilder("originalBitmapDrawable.getIntrinsicHeight() = ");
                            int d4 = Log.d("MediaUtil", sb7.append(bitmapDrawable4.getIntrinsicHeight()).toString());
                            new BitmapDrawable(form3.getResources(), Bitmap.createScaledBitmap(bitmapDrawable4.getBitmap(), deviceDensity, deviceDensity2, false));
                            BitmapDrawable bitmapDrawable5 = bitmapDrawable2;
                            bitmapDrawable5.setTargetDensity(form3.getResources().getDisplayMetrics());
                            System.gc();
                            asyncCallbackPair3.onSuccess(bitmapDrawable5);
                            try {
                                byteArrayInputStream2.close();
                            } catch (Exception e4) {
                                int w3 = Log.w("MediaUtil", "Unexpected error on close", e4);
                            }
                        }
                    } catch (Exception e5) {
                        Exception exc = e5;
                        int w4 = Log.w("MediaUtil", "Exception while loading media.", exc);
                        AsyncCallbackPair asyncCallbackPair = asyncCallbackPair3;
                        new StringBuilder();
                        asyncCallbackPair.onFailure(sb4.append(exc.getMessage()).toString());
                        try {
                            byteArrayInputStream2.close();
                        } catch (Exception e6) {
                            int w5 = Log.w("MediaUtil", "Unexpected error on close", e6);
                        }
                    } catch (Throwable th) {
                        Throwable th2 = th;
                        try {
                            byteArrayInputStream2.close();
                        } catch (Exception e7) {
                            int w6 = Log.w("MediaUtil", "Unexpected error on close", e7);
                        }
                        throw th2;
                    }
                } catch (PermissionException e8) {
                    PermissionException permissionException = e8;
                    AsyncCallbackPair asyncCallbackPair2 = asyncCallbackPair3;
                    new StringBuilder("PERMISSION_DENIED:");
                    asyncCallbackPair2.onFailure(sb3.append(permissionException.getPermissionNeeded()).toString());
                    if (0 != 0) {
                        InputStream inputStream = null;
                        try {
                            inputStream.close();
                        } catch (Exception e9) {
                            int w7 = Log.w("MediaUtil", "Unexpected error on close", e9);
                        }
                    }
                    try {
                        byteArrayOutputStream2.close();
                    } catch (Exception e10) {
                    }
                } catch (Exception e11) {
                    Exception exc2 = e11;
                    if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 == C1178b.zsMcUi8jymhI0yeycaZW56W8YNMDBLoH4OZHLSlj2A3S38zGlu25pkABN4p8cs) {
                        new BitmapDrawable(form3.getResources(), BitmapFactory.decodeResource(form3.getResources(), 17301606, (BitmapFactory.Options) null));
                        asyncCallbackPair3.onSuccess(obj);
                        if (0 != 0) {
                            InputStream inputStream2 = null;
                            try {
                                inputStream2.close();
                            } catch (Exception e12) {
                                int w8 = Log.w("MediaUtil", "Unexpected error on close", e12);
                            }
                        }
                        try {
                            byteArrayOutputStream2.close();
                        } catch (Exception e13) {
                        }
                    } else {
                        int d5 = Log.d("MediaUtil", "Exception reading file.", exc2);
                        AsyncCallbackPair asyncCallbackPair3 = asyncCallbackPair3;
                        new StringBuilder();
                        asyncCallbackPair3.onFailure(sb2.append(exc2.getMessage()).toString());
                        if (0 != 0) {
                            InputStream inputStream3 = null;
                            try {
                                inputStream3.close();
                            } catch (Exception e14) {
                                int w9 = Log.w("MediaUtil", "Unexpected error on close", e14);
                            }
                        }
                        try {
                            byteArrayOutputStream2.close();
                        } catch (Exception e15) {
                        }
                    }
                } catch (Throwable th3) {
                    Throwable th4 = th3;
                    if (0 != 0) {
                        InputStream inputStream4 = null;
                        try {
                            inputStream4.close();
                        } catch (Exception e16) {
                            int w10 = Log.w("MediaUtil", "Unexpected error on close", e16);
                        }
                    }
                    try {
                        byteArrayOutputStream2.close();
                    } catch (Exception e17) {
                    }
                    throw th4;
                }
            }
        };
        AsynchUtil.runAsynchronously(runnable);
    }

    /* access modifiers changed from: private */
    public static Bitmap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(InputStream inputStream, BitmapFactory.Options options) {
        InputStream inputStream2;
        new C1177a(inputStream);
        return BitmapFactory.decodeStream(inputStream2, (Rect) null, options);
    }

    /* renamed from: com.google.appinventor.components.runtime.util.MediaUtil$a */
    static class C1177a extends FilterInputStream {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public C1177a(InputStream inputStream) {
            super(inputStream);
        }

        public final long skip(long j) throws IOException {
            long j2;
            long j3 = j;
            long j4 = 0;
            while (true) {
                j2 = j4;
                if (j2 >= j3) {
                    break;
                }
                long skip = this.in.skip(j3 - j2);
                long j5 = skip;
                if (skip == 0) {
                    if (read() < 0) {
                        break;
                    }
                    j5 = 1;
                }
                j4 = j2 + j5;
            }
            return j2;
        }
    }

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other method in class */
    private static AssetFileDescriptor m69hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Form form, String str) throws IOException {
        Form form2 = form;
        String str2 = str;
        try {
            return form2.getAssets().openFd(str2);
        } catch (IOException e) {
            IOException iOException = e;
            String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(form2, str2);
            String str3 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2;
            if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 != null) {
                return form2.getAssets().openFd(str3);
            }
            throw iOException;
        }
    }

    public static int loadSoundPool(SoundPool soundPool, Form form, String str) throws IOException {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        SoundPool soundPool2 = soundPool;
        Form form2 = form;
        String str2 = str;
        int hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(form2, str2);
        switch (C11763.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO[hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 - 1]) {
            case 1:
                return soundPool2.load(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(form2, str2), 1);
            case 4:
                form2.assertPermission("android.permission.READ_EXTERNAL_STORAGE");
                return soundPool2.load(sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt(str2), 1);
            case 5:
                form2.assertPermission("android.permission.READ_EXTERNAL_STORAGE");
                return soundPool2.load(str2, 1);
            case 6:
                if (isExternalFileUrl(str2)) {
                    form2.assertPermission("android.permission.READ_EXTERNAL_STORAGE");
                }
                return soundPool2.load(mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT(str2), 1);
            case 7:
            case 8:
                return soundPool2.load(B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(form2, str2, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2).getAbsolutePath(), 1);
            case 9:
                Throwable th3 = th;
                new StringBuilder("Unable to load audio for contact ");
                new IOException(sb.append(str2).append(".").toString());
                throw th3;
            default:
                Throwable th4 = th2;
                new StringBuilder("Unable to load audio ");
                new IOException(sb2.append(str2).append(".").toString());
                throw th4;
        }
    }

    public static void loadMediaPlayer(MediaPlayer mediaPlayer, Form form, String str) throws IOException {
        Throwable th;
        StringBuilder sb;
        FileInputStream fileInputStream;
        Throwable th2;
        StringBuilder sb2;
        MediaPlayer mediaPlayer2 = mediaPlayer;
        Form form2 = form;
        String str2 = str;
        switch (C11763.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO[hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(form2, str2) - 1]) {
            case 1:
                AssetFileDescriptor openFd = form2.getAssets().openFd(str2);
                try {
                    mediaPlayer2.setDataSource(openFd.getFileDescriptor(), openFd.getStartOffset(), openFd.getLength());
                    openFd.close();
                    return;
                } catch (Throwable th3) {
                    Throwable th4 = th3;
                    openFd.close();
                    throw th4;
                }
            case 3:
                new FileInputStream(str2);
                mediaPlayer2.setDataSource(fileInputStream.getFD());
                return;
            case 4:
                form2.assertPermission("android.permission.READ_EXTERNAL_STORAGE");
                mediaPlayer2.setDataSource(sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt(str2));
                return;
            case 5:
                form2.assertPermission("android.permission.READ_EXTERNAL_STORAGE");
                mediaPlayer2.setDataSource(str2);
                return;
            case 6:
                if (isExternalFileUrl(str2)) {
                    form2.assertPermission("android.permission.READ_EXTERNAL_STORAGE");
                }
                mediaPlayer2.setDataSource(mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT(str2));
                return;
            case 7:
                mediaPlayer2.setDataSource(str2);
                return;
            case 8:
                mediaPlayer2.setDataSource(form2, Uri.parse(str2));
                return;
            case 9:
                Throwable th5 = th;
                new StringBuilder("Unable to load audio or video for contact ");
                new IOException(sb.append(str2).append(".").toString());
                throw th5;
            default:
                Throwable th6 = th2;
                new StringBuilder("Unable to load audio or video ");
                new IOException(sb2.append(str2).append(".").toString());
                throw th6;
        }
    }

    public static void loadVideoView(VideoView videoView, Form form, String str) throws IOException {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        VideoView videoView2 = videoView;
        Form form2 = form;
        String str2 = str;
        int hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(form2, str2);
        switch (C11763.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO[hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 - 1]) {
            case 1:
                videoView2.setVideoPath(B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(form2, str2, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2).getAbsolutePath());
                return;
            case 4:
                form2.assertPermission("android.permission.READ_EXTERNAL_STORAGE");
                videoView2.setVideoPath(sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt(str2));
                return;
            case 5:
                form2.assertPermission("android.permission.READ_EXTERNAL_STORAGE");
                videoView2.setVideoPath(str2);
                return;
            case 6:
                if (isExternalFileUrl(str2)) {
                    form2.assertPermission("android.permission.READ_EXTERNAL_STORAGE");
                }
                videoView2.setVideoPath(mBLKdPlRolYfdtud5Z8giGWyhURWoCw9yHsOv08fUnIBAM4MuKAWkHVD8nEhBmIT(str2));
                return;
            case 7:
                videoView2.setVideoURI(Uri.parse(str2));
                return;
            case 8:
                videoView2.setVideoURI(Uri.parse(str2));
                return;
            case 9:
                Throwable th3 = th;
                new StringBuilder("Unable to load video for contact ");
                new IOException(sb.append(str2).append(".").toString());
                throw th3;
            default:
                Throwable th4 = th2;
                new StringBuilder("Unable to load video ");
                new IOException(sb2.append(str2).append(".").toString());
                throw th4;
        }
    }

    public static String getAssetFilePath(Form form, String str) {
        Form form2 = form;
        String str2 = str;
        String str3 = str2;
        if (!(form2 instanceof ReplForm) || !((ReplForm) form2).isAssetsLoaded()) {
            return str3;
        }
        return sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt(str2);
    }

    private static Drawable hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Form form, Drawable drawable) {
        int intrinsicHeight;
        int intrinsicWidth;
        Form form2 = form;
        try {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            BitmapDrawable bitmapDrawable2 = bitmapDrawable;
            bitmapDrawable.setTargetDensity(form2.getResources().getDisplayMetrics());
            if (Form.getCompatibilityMode()) {
                intrinsicHeight = 720;
                intrinsicWidth = 840;
            } else {
                intrinsicHeight = (int) (((float) bitmapDrawable2.getIntrinsicHeight()) * form2.deviceDensity());
                intrinsicWidth = (int) (((float) bitmapDrawable2.getIntrinsicWidth()) * form2.deviceDensity());
            }
            bitmapDrawable2.setBounds(0, 0, intrinsicHeight, intrinsicWidth);
            return bitmapDrawable2;
        } catch (Exception e) {
            int e2 = Log.e("MediaUtil", String.valueOf(e));
            return null;
        }
    }

    public static Drawable getHighQualityImage(Form form, String str) {
        URL url;
        Drawable drawable;
        Drawable drawable2;
        Drawable drawable3;
        Form form2 = form;
        String str2 = str;
        Drawable drawable4 = null;
        InputStream inputStream = null;
        int hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(form2, str2);
        int i = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2;
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 == C1178b.CfJsMCmg8U782C2ivbbep8ZFrAD6R9Wq7P09zMpUKCFkpiEYodk6t8FdSxHzlHKV) {
            try {
                InputStream open = form2.getAssets().open(getAssetFilePath(form2, str2));
                inputStream = open;
                drawable4 = Drawable.createFromStream(open, (String) null);
                drawable4 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(form2, drawable4);
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception e) {
                        int e2 = Log.e("MediaUtil", String.valueOf(e));
                    }
                }
            } catch (Exception e3) {
                int e4 = Log.e("MediaUtil", String.valueOf(e3));
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception e5) {
                        int e6 = Log.e("MediaUtil", String.valueOf(e5));
                    }
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception e7) {
                        int e8 = Log.e("MediaUtil", String.valueOf(e7));
                    }
                }
                throw th2;
            }
        } else if (i == C1178b.f3jXQdr7SaO4jKCWPTlGDFsZc4anfRmkf59KPIcTiLfRAexdccxYBXXB8h0vpeF7) {
            try {
                drawable4 = Drawable.createFromPath(getAssetFilePath(form2, str2));
                drawable4 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(form2, drawable4);
            } catch (Exception e9) {
                int e10 = Log.e("MediaUtil", String.valueOf(e9));
            }
        } else if (i == C1178b.siVQGK7skYIQ7zI3RxZVmSEN1N3qEwDlBDPORHd716EIgwqH2EWQFUJrvV0SXYUL) {
            try {
                new BitmapDrawable(BitmapFactory.decodeFile(str2));
                drawable4 = drawable3;
                drawable4 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(form2, drawable4);
            } catch (Exception e11) {
                int e12 = Log.e("MediaUtil", String.valueOf(e11));
            }
        } else if (i == C1178b.kkTI9AxohjOECYVBpzZOuVO0b9llYVM2xqggkPHvpPoNGTREwN5YZmwC10Gk8X2Q) {
            try {
                new BitmapDrawable(MediaStore.Images.Media.getBitmap(form2.getContentResolver(), Uri.parse(str2)));
                drawable4 = drawable2;
                drawable4 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(form2, drawable4);
            } catch (Exception e13) {
                int e14 = Log.e("MediaUtil", String.valueOf(e13));
            }
        } else if (i == C1178b.KYHvnTv0AWOO8SeFCXsiNXCxcIirISbo8kAOvMnivJLnqAuCVxfixET1OT3ZpHhw || i == C1178b.LYXVmrSApJtjkX58iaWYcT93zXSX0GhbrHTAKbm3TBRJ5avsjGCN1sJz61Za2zkA) {
            try {
                new URL(str2);
                InputStream inputStream2 = (InputStream) url.getContent();
                inputStream = inputStream2;
                drawable4 = Drawable.createFromStream(inputStream2, (String) null);
                drawable4 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(form2, drawable4);
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception e15) {
                        int e16 = Log.e("MediaUtil", String.valueOf(e15));
                    }
                }
            } catch (Exception e17) {
                int e18 = Log.e("MediaUtil", String.valueOf(e17));
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception e19) {
                        int e20 = Log.e("MediaUtil", String.valueOf(e19));
                    }
                }
            } catch (Throwable th3) {
                Throwable th4 = th3;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception e21) {
                        int e22 = Log.e("MediaUtil", String.valueOf(e21));
                    }
                }
                throw th4;
            }
        } else if (i == C1178b.zsMcUi8jymhI0yeycaZW56W8YNMDBLoH4OZHLSlj2A3S38zGlu25pkABN4p8cs) {
            try {
                Drawable drawable5 = drawable;
                new BitmapDrawable(form2.getResources(), BitmapFactory.decodeResource(form2.getResources(), 17301606, (BitmapFactory.Options) null));
                drawable4 = drawable5;
            } catch (Exception e23) {
                int e24 = Log.e("MediaUtil", String.valueOf(e23));
            }
        }
        return drawable4;
    }

    public static boolean isExternalFileUrl(String str) {
        StringBuilder sb;
        String str2 = str;
        if (!str2.startsWith("file:///sdcard/")) {
            new StringBuilder("file://");
            if (!str2.startsWith(sb.append(Environment.getExternalStorageDirectory().getAbsolutePath()).toString())) {
                return false;
            }
        }
        return true;
    }

    public static boolean isExternalFile(String str) {
        String str2 = str;
        if (str2.startsWith("/sdcard/") || str2.startsWith(Environment.getExternalStorageDirectory().getAbsolutePath()) || isExternalFileUrl(str2)) {
            return true;
        }
        return false;
    }
}

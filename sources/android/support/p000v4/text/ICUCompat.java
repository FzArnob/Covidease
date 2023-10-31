package android.support.p000v4.text;

import android.os.Build;
import android.support.annotation.Nullable;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

/* renamed from: android.support.v4.text.ICUCompat */
public final class ICUCompat {
    private static final String TAG = "ICUCompat";
    private static Method sAddLikelySubtagsMethod;
    private static Method sGetScriptMethod;

    static {
        Throwable th;
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                sAddLikelySubtagsMethod = Class.forName("libcore.icu.ICU").getMethod("addLikelySubtags", new Class[]{Locale.class});
            } catch (Exception e) {
                Exception e2 = e;
                Throwable th2 = th;
                new IllegalStateException(e2);
                throw th2;
            }
        } else {
            try {
                Class<?> clazz = Class.forName("libcore.icu.ICU");
                if (clazz != null) {
                    sGetScriptMethod = clazz.getMethod("getScript", new Class[]{String.class});
                    sAddLikelySubtagsMethod = clazz.getMethod("addLikelySubtags", new Class[]{String.class});
                }
            } catch (Exception e3) {
                sGetScriptMethod = null;
                sAddLikelySubtagsMethod = null;
                int w = Log.w(TAG, e3);
            }
        }
    }

    @Nullable
    public static String maximizeAndGetScript(Locale locale) {
        Locale locale2 = locale;
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                return ((Locale) sAddLikelySubtagsMethod.invoke((Object) null, new Object[]{locale2})).getScript();
            } catch (InvocationTargetException e) {
                int w = Log.w(TAG, e);
                return locale2.getScript();
            } catch (IllegalAccessException e2) {
                int w2 = Log.w(TAG, e2);
                return locale2.getScript();
            }
        } else {
            String localeWithSubtags = addLikelySubtags(locale2);
            if (localeWithSubtags != null) {
                return getScript(localeWithSubtags);
            }
            return null;
        }
    }

    private static String getScript(String str) {
        String localeStr = str;
        try {
            if (sGetScriptMethod != null) {
                return (String) sGetScriptMethod.invoke((Object) null, new Object[]{localeStr});
            }
        } catch (IllegalAccessException e) {
            int w = Log.w(TAG, e);
        } catch (InvocationTargetException e2) {
            int w2 = Log.w(TAG, e2);
        }
        return null;
    }

    private static String addLikelySubtags(Locale locale) {
        String localeStr = locale.toString();
        try {
            if (sAddLikelySubtagsMethod != null) {
                return (String) sAddLikelySubtagsMethod.invoke((Object) null, new Object[]{localeStr});
            }
        } catch (IllegalAccessException e) {
            int w = Log.w(TAG, e);
        } catch (InvocationTargetException e2) {
            int w2 = Log.w(TAG, e2);
        }
        return localeStr;
    }

    private ICUCompat() {
    }
}

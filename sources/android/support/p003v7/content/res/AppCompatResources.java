package android.support.p003v7.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.content.res.ColorStateListInflaterCompat;
import android.support.p003v7.widget.AppCompatDrawableManager;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import java.util.WeakHashMap;

/* renamed from: android.support.v7.content.res.AppCompatResources */
public final class AppCompatResources {
    private static final String LOG_TAG = "AppCompatResources";
    private static final ThreadLocal<TypedValue> TL_TYPED_VALUE;
    private static final Object sColorStateCacheLock;
    private static final WeakHashMap<Context, SparseArray<ColorStateListCacheEntry>> sColorStateCaches;

    static {
        ThreadLocal<TypedValue> threadLocal;
        WeakHashMap<Context, SparseArray<ColorStateListCacheEntry>> weakHashMap;
        Object obj;
        new ThreadLocal<>();
        TL_TYPED_VALUE = threadLocal;
        new WeakHashMap<>(0);
        sColorStateCaches = weakHashMap;
        new Object();
        sColorStateCacheLock = obj;
    }

    private AppCompatResources() {
    }

    public static ColorStateList getColorStateList(@NonNull Context context, @ColorRes int i) {
        Context context2 = context;
        int resId = i;
        if (Build.VERSION.SDK_INT >= 23) {
            return context2.getColorStateList(resId);
        }
        ColorStateList csl = getCachedColorStateList(context2, resId);
        if (csl != null) {
            return csl;
        }
        ColorStateList csl2 = inflateColorStateList(context2, resId);
        if (csl2 == null) {
            return ContextCompat.getColorStateList(context2, resId);
        }
        addColorStateListToCache(context2, resId, csl2);
        return csl2;
    }

    @Nullable
    public static Drawable getDrawable(@NonNull Context context, @DrawableRes int resId) {
        return AppCompatDrawableManager.get().getDrawable(context, resId);
    }

    @Nullable
    private static ColorStateList inflateColorStateList(Context context, int i) {
        Context context2 = context;
        int resId = i;
        if (isColorInt(context2, resId)) {
            return null;
        }
        Resources r = context2.getResources();
        try {
            return ColorStateListInflaterCompat.createFromXml(r, r.getXml(resId), context2.getTheme());
        } catch (Exception e) {
            int e2 = Log.e(LOG_TAG, "Failed to inflate ColorStateList, leaving it to the framework", e);
            return null;
        }
    }

    /* JADX INFO: finally extract failed */
    @Nullable
    private static ColorStateList getCachedColorStateList(@NonNull Context context, @ColorRes int i) {
        ColorStateListCacheEntry entry;
        Context context2 = context;
        int resId = i;
        Object obj = sColorStateCacheLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                SparseArray<ColorStateListCacheEntry> entries = sColorStateCaches.get(context2);
                if (!(entries == null || entries.size() <= 0 || (entry = entries.get(resId)) == null)) {
                    if (entry.configuration.equals(context2.getResources().getConfiguration())) {
                        ColorStateList colorStateList = entry.value;
                        return colorStateList;
                    }
                    entries.remove(resId);
                }
                return null;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    private static void addColorStateListToCache(@NonNull Context context, @ColorRes int i, @NonNull ColorStateList colorStateList) {
        Object obj;
        SparseArray sparseArray;
        Context context2 = context;
        int resId = i;
        ColorStateList value = colorStateList;
        Object obj2 = sColorStateCacheLock;
        Object obj3 = obj2;
        synchronized (obj2) {
            try {
                SparseArray sparseArray2 = sColorStateCaches.get(context2);
                if (sparseArray2 == null) {
                    new SparseArray();
                    sparseArray2 = sparseArray;
                    SparseArray<ColorStateListCacheEntry> put = sColorStateCaches.put(context2, sparseArray2);
                }
                new ColorStateListCacheEntry(value, context2.getResources().getConfiguration());
                sparseArray2.append(resId, obj);
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj4 = obj3;
                throw th2;
            }
        }
    }

    private static boolean isColorInt(@NonNull Context context, @ColorRes int resId) {
        Resources r = context.getResources();
        TypedValue value = getTypedValue();
        r.getValue(resId, value, true);
        return value.type >= 28 && value.type <= 31;
    }

    @NonNull
    private static TypedValue getTypedValue() {
        TypedValue typedValue;
        TypedValue tv = TL_TYPED_VALUE.get();
        if (tv == null) {
            new TypedValue();
            tv = typedValue;
            TL_TYPED_VALUE.set(tv);
        }
        return tv;
    }

    /* renamed from: android.support.v7.content.res.AppCompatResources$ColorStateListCacheEntry */
    private static class ColorStateListCacheEntry {
        final Configuration configuration;
        final ColorStateList value;

        ColorStateListCacheEntry(@NonNull ColorStateList value2, @NonNull Configuration configuration2) {
            this.value = value2;
            this.configuration = configuration2;
        }
    }
}

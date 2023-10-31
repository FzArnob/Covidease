package android.support.p003v7.app;

import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.util.LongSparseArray;
import java.lang.reflect.Field;
import java.util.Map;

/* renamed from: android.support.v7.app.ResourcesFlusher */
class ResourcesFlusher {
    private static final String TAG = "ResourcesFlusher";
    private static Field sDrawableCacheField;
    private static boolean sDrawableCacheFieldFetched;
    private static Field sResourcesImplField;
    private static boolean sResourcesImplFieldFetched;
    private static Class sThemedResourceCacheClazz;
    private static boolean sThemedResourceCacheClazzFetched;
    private static Field sThemedResourceCache_mUnthemedEntriesField;
    private static boolean sThemedResourceCache_mUnthemedEntriesFieldFetched;

    static void flush(@NonNull Resources resources) {
        Resources resources2 = resources;
        if (Build.VERSION.SDK_INT < 28) {
            if (Build.VERSION.SDK_INT >= 24) {
                flushNougats(resources2);
            } else if (Build.VERSION.SDK_INT >= 23) {
                flushMarshmallows(resources2);
            } else if (Build.VERSION.SDK_INT >= 21) {
                flushLollipops(resources2);
            }
        }
    }

    @RequiresApi(21)
    private static void flushLollipops(@NonNull Resources resources) {
        Resources resources2 = resources;
        if (!sDrawableCacheFieldFetched) {
            try {
                sDrawableCacheField = Resources.class.getDeclaredField("mDrawableCache");
                sDrawableCacheField.setAccessible(true);
            } catch (NoSuchFieldException e) {
                int e2 = Log.e(TAG, "Could not retrieve Resources#mDrawableCache field", e);
            }
            sDrawableCacheFieldFetched = true;
        }
        if (sDrawableCacheField != null) {
            Map drawableCache = null;
            try {
                drawableCache = (Map) sDrawableCacheField.get(resources2);
            } catch (IllegalAccessException e3) {
                int e4 = Log.e(TAG, "Could not retrieve value from Resources#mDrawableCache", e3);
            }
            if (drawableCache != null) {
                drawableCache.clear();
            }
        }
    }

    @RequiresApi(23)
    private static void flushMarshmallows(@NonNull Resources resources) {
        Resources resources2 = resources;
        if (!sDrawableCacheFieldFetched) {
            try {
                sDrawableCacheField = Resources.class.getDeclaredField("mDrawableCache");
                sDrawableCacheField.setAccessible(true);
            } catch (NoSuchFieldException e) {
                int e2 = Log.e(TAG, "Could not retrieve Resources#mDrawableCache field", e);
            }
            sDrawableCacheFieldFetched = true;
        }
        Object drawableCache = null;
        if (sDrawableCacheField != null) {
            try {
                drawableCache = sDrawableCacheField.get(resources2);
            } catch (IllegalAccessException e3) {
                int e4 = Log.e(TAG, "Could not retrieve value from Resources#mDrawableCache", e3);
            }
        }
        if (drawableCache != null) {
            flushThemedResourcesCache(drawableCache);
        }
    }

    @RequiresApi(24)
    private static void flushNougats(@NonNull Resources resources) {
        Resources resources2 = resources;
        if (!sResourcesImplFieldFetched) {
            try {
                sResourcesImplField = Resources.class.getDeclaredField("mResourcesImpl");
                sResourcesImplField.setAccessible(true);
            } catch (NoSuchFieldException e) {
                int e2 = Log.e(TAG, "Could not retrieve Resources#mResourcesImpl field", e);
            }
            sResourcesImplFieldFetched = true;
        }
        if (sResourcesImplField != null) {
            Object resourcesImpl = null;
            try {
                resourcesImpl = sResourcesImplField.get(resources2);
            } catch (IllegalAccessException e3) {
                int e4 = Log.e(TAG, "Could not retrieve value from Resources#mResourcesImpl", e3);
            }
            if (resourcesImpl != null) {
                if (!sDrawableCacheFieldFetched) {
                    try {
                        sDrawableCacheField = resourcesImpl.getClass().getDeclaredField("mDrawableCache");
                        sDrawableCacheField.setAccessible(true);
                    } catch (NoSuchFieldException e5) {
                        int e6 = Log.e(TAG, "Could not retrieve ResourcesImpl#mDrawableCache field", e5);
                    }
                    sDrawableCacheFieldFetched = true;
                }
                Object drawableCache = null;
                if (sDrawableCacheField != null) {
                    try {
                        drawableCache = sDrawableCacheField.get(resourcesImpl);
                    } catch (IllegalAccessException e7) {
                        int e8 = Log.e(TAG, "Could not retrieve value from ResourcesImpl#mDrawableCache", e7);
                    }
                }
                if (drawableCache != null) {
                    flushThemedResourcesCache(drawableCache);
                }
            }
        }
    }

    @RequiresApi(16)
    private static void flushThemedResourcesCache(@NonNull Object obj) {
        Object cache = obj;
        if (!sThemedResourceCacheClazzFetched) {
            try {
                sThemedResourceCacheClazz = Class.forName("android.content.res.ThemedResourceCache");
            } catch (ClassNotFoundException e) {
                int e2 = Log.e(TAG, "Could not find ThemedResourceCache class", e);
            }
            sThemedResourceCacheClazzFetched = true;
        }
        if (sThemedResourceCacheClazz != null) {
            if (!sThemedResourceCache_mUnthemedEntriesFieldFetched) {
                try {
                    sThemedResourceCache_mUnthemedEntriesField = sThemedResourceCacheClazz.getDeclaredField("mUnthemedEntries");
                    sThemedResourceCache_mUnthemedEntriesField.setAccessible(true);
                } catch (NoSuchFieldException e3) {
                    int e4 = Log.e(TAG, "Could not retrieve ThemedResourceCache#mUnthemedEntries field", e3);
                }
                sThemedResourceCache_mUnthemedEntriesFieldFetched = true;
            }
            if (sThemedResourceCache_mUnthemedEntriesField != null) {
                LongSparseArray unthemedEntries = null;
                try {
                    unthemedEntries = (LongSparseArray) sThemedResourceCache_mUnthemedEntriesField.get(cache);
                } catch (IllegalAccessException e5) {
                    int e6 = Log.e(TAG, "Could not retrieve value from ThemedResourceCache#mUnthemedEntries", e5);
                }
                if (unthemedEntries != null) {
                    unthemedEntries.clear();
                }
            }
        }
    }

    private ResourcesFlusher() {
    }
}

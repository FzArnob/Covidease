package com.bumptech.glide.load.engine.cache;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;

public class MemorySizeCalculator {
    static final int BITMAP_POOL_TARGET_SCREENS = 4;
    static final int BYTES_PER_ARGB_8888_PIXEL = 4;
    static final float LOW_MEMORY_MAX_SIZE_MULTIPLIER = 0.33f;
    static final float MAX_SIZE_MULTIPLIER = 0.4f;
    static final int MEMORY_CACHE_TARGET_SCREENS = 2;
    private static final String TAG = "MemorySizeCalculator";
    private final int bitmapPoolSize;
    private final Context context;
    private final int memoryCacheSize;

    interface ScreenDimensions {
        int getHeightPixels();

        int getWidthPixels();
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MemorySizeCalculator(android.content.Context r10) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r2 = r0
            r3 = r1
            r4 = r1
            java.lang.String r5 = "activity"
            java.lang.Object r4 = r4.getSystemService(r5)
            android.app.ActivityManager r4 = (android.app.ActivityManager) r4
            com.bumptech.glide.load.engine.cache.MemorySizeCalculator$DisplayMetricsScreenDimensions r5 = new com.bumptech.glide.load.engine.cache.MemorySizeCalculator$DisplayMetricsScreenDimensions
            r8 = r5
            r5 = r8
            r6 = r8
            r7 = r1
            android.content.res.Resources r7 = r7.getResources()
            android.util.DisplayMetrics r7 = r7.getDisplayMetrics()
            r6.<init>(r7)
            r2.<init>(r3, r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.engine.cache.MemorySizeCalculator.<init>(android.content.Context):void");
    }

    MemorySizeCalculator(Context context2, ActivityManager activityManager, ScreenDimensions screenDimensions) {
        StringBuilder sb;
        ActivityManager activityManager2 = activityManager;
        ScreenDimensions screenDimensions2 = screenDimensions;
        this.context = context2;
        int maxSize = getMaxSize(activityManager2);
        int screenSize = screenDimensions2.getWidthPixels() * screenDimensions2.getHeightPixels() * 4;
        int targetPoolSize = screenSize * 4;
        int targetMemoryCacheSize = screenSize * 2;
        if (targetMemoryCacheSize + targetPoolSize <= maxSize) {
            this.memoryCacheSize = targetMemoryCacheSize;
            this.bitmapPoolSize = targetPoolSize;
        } else {
            int part = Math.round(((float) maxSize) / 6.0f);
            this.memoryCacheSize = part * 2;
            this.bitmapPoolSize = part * 4;
        }
        if (Log.isLoggable(TAG, 3)) {
            new StringBuilder();
            int d = Log.d(TAG, sb.append("Calculated memory cache size: ").append(toMb(this.memoryCacheSize)).append(" pool size: ").append(toMb(this.bitmapPoolSize)).append(" memory class limited? ").append(targetMemoryCacheSize + targetPoolSize > maxSize).append(" max size: ").append(toMb(maxSize)).append(" memoryClass: ").append(activityManager2.getMemoryClass()).append(" isLowMemoryDevice: ").append(isLowMemoryDevice(activityManager2)).toString());
        }
    }

    public int getMemoryCacheSize() {
        return this.memoryCacheSize;
    }

    public int getBitmapPoolSize() {
        return this.bitmapPoolSize;
    }

    private static int getMaxSize(ActivityManager activityManager) {
        ActivityManager activityManager2 = activityManager;
        return Math.round(((float) (activityManager2.getMemoryClass() * 1024 * 1024)) * (isLowMemoryDevice(activityManager2) ? LOW_MEMORY_MAX_SIZE_MULTIPLIER : MAX_SIZE_MULTIPLIER));
    }

    private String toMb(int bytes) {
        return Formatter.formatFileSize(this.context, (long) bytes);
    }

    @TargetApi(19)
    private static boolean isLowMemoryDevice(ActivityManager activityManager) {
        ActivityManager activityManager2 = activityManager;
        if (Build.VERSION.SDK_INT >= 19) {
            return activityManager2.isLowRamDevice();
        }
        return Build.VERSION.SDK_INT < 11;
    }

    private static class DisplayMetricsScreenDimensions implements ScreenDimensions {
        private final DisplayMetrics displayMetrics;

        public DisplayMetricsScreenDimensions(DisplayMetrics displayMetrics2) {
            this.displayMetrics = displayMetrics2;
        }

        public int getWidthPixels() {
            return this.displayMetrics.widthPixels;
        }

        public int getHeightPixels() {
            return this.displayMetrics.heightPixels;
        }
    }
}

package com.bumptech.glide.load.engine.prefill;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.load.engine.prefill.PreFillType;
import com.bumptech.glide.util.Util;
import java.util.HashMap;
import java.util.Map;

public final class BitmapPreFiller {
    private final BitmapPool bitmapPool;
    private BitmapPreFillRunner current;
    private final DecodeFormat defaultFormat;
    private final Handler handler;
    private final MemoryCache memoryCache;

    public BitmapPreFiller(MemoryCache memoryCache2, BitmapPool bitmapPool2, DecodeFormat defaultFormat2) {
        Handler handler2;
        new Handler(Looper.getMainLooper());
        this.handler = handler2;
        this.memoryCache = memoryCache2;
        this.bitmapPool = bitmapPool2;
        this.defaultFormat = defaultFormat2;
    }

    public void preFill(PreFillType.Builder... builderArr) {
        BitmapPreFillRunner bitmapPreFillRunner;
        PreFillType.Builder[] bitmapAttributeBuilders = builderArr;
        if (this.current != null) {
            this.current.cancel();
        }
        PreFillType[] bitmapAttributes = new PreFillType[bitmapAttributeBuilders.length];
        for (int i = 0; i < bitmapAttributeBuilders.length; i++) {
            PreFillType.Builder builder = bitmapAttributeBuilders[i];
            if (builder.getConfig() == null) {
                PreFillType.Builder config = builder.setConfig((this.defaultFormat == DecodeFormat.ALWAYS_ARGB_8888 || this.defaultFormat == DecodeFormat.PREFER_ARGB_8888) ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
            }
            bitmapAttributes[i] = builder.build();
        }
        new BitmapPreFillRunner(this.bitmapPool, this.memoryCache, generateAllocationOrder(bitmapAttributes));
        this.current = bitmapPreFillRunner;
        boolean post = this.handler.post(this.current);
    }

    /* access modifiers changed from: package-private */
    public PreFillQueue generateAllocationOrder(PreFillType[] preFillTypeArr) {
        Map<PreFillType, Integer> map;
        PreFillQueue preFillQueue;
        PreFillType[] preFillSizes = preFillTypeArr;
        int maxSize = (this.memoryCache.getMaxSize() - this.memoryCache.getCurrentSize()) + this.bitmapPool.getMaxSize();
        int totalWeight = 0;
        PreFillType[] arr$ = preFillSizes;
        int len$ = arr$.length;
        for (int i$ = 0; i$ < len$; i$++) {
            totalWeight += arr$[i$].getWeight();
        }
        float bytesPerWeight = ((float) maxSize) / ((float) totalWeight);
        new HashMap<>();
        Map<PreFillType, Integer> attributeToCount = map;
        PreFillType[] arr$2 = preFillSizes;
        int len$2 = arr$2.length;
        for (int i$2 = 0; i$2 < len$2; i$2++) {
            PreFillType size = arr$2[i$2];
            Integer put = attributeToCount.put(size, Integer.valueOf(Math.round(bytesPerWeight * ((float) size.getWeight())) / getSizeInBytes(size)));
        }
        new PreFillQueue(attributeToCount);
        return preFillQueue;
    }

    private static int getSizeInBytes(PreFillType preFillType) {
        PreFillType size = preFillType;
        return Util.getBitmapByteSize(size.getWidth(), size.getHeight(), size.getConfig());
    }
}

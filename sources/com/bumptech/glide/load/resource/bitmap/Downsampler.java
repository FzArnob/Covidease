package com.bumptech.glide.load.resource.bitmap;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.ImageHeaderParser;
import com.bumptech.glide.util.ByteArrayPool;
import com.bumptech.glide.util.ExceptionCatchingInputStream;
import com.bumptech.glide.util.MarkEnforcingInputStream;
import com.bumptech.glide.util.Util;
import java.io.IOException;
import java.io.InputStream;
import java.util.EnumSet;
import java.util.Queue;
import java.util.Set;

public abstract class Downsampler implements BitmapDecoder<InputStream> {
    public static final Downsampler AT_LEAST;
    public static final Downsampler AT_MOST;
    private static final int MARK_POSITION = 5242880;
    public static final Downsampler NONE;
    private static final Queue<BitmapFactory.Options> OPTIONS_QUEUE = Util.createQueue(0);
    private static final String TAG = "Downsampler";
    private static final Set<ImageHeaderParser.ImageType> TYPES_THAT_USE_POOL = EnumSet.of(ImageHeaderParser.ImageType.JPEG, ImageHeaderParser.ImageType.PNG_A, ImageHeaderParser.ImageType.PNG);

    /* access modifiers changed from: protected */
    public abstract int getSampleSize(int i, int i2, int i3, int i4);

    public Downsampler() {
    }

    static {
        Downsampler downsampler;
        Downsampler downsampler2;
        Downsampler downsampler3;
        new Downsampler() {
            public /* bridge */ /* synthetic */ Bitmap decode(Object x0, BitmapPool x1, int x2, int x3, DecodeFormat x4) throws Exception {
                return Downsampler.super.decode((InputStream) x0, x1, x2, x3, x4);
            }

            /* access modifiers changed from: protected */
            public int getSampleSize(int inWidth, int inHeight, int outWidth, int outHeight) {
                return Math.min(inHeight / outHeight, inWidth / outWidth);
            }

            public String getId() {
                return "AT_LEAST.com.bumptech.glide.load.data.bitmap";
            }
        };
        AT_LEAST = downsampler;
        new Downsampler() {
            public /* bridge */ /* synthetic */ Bitmap decode(Object x0, BitmapPool x1, int x2, int x3, DecodeFormat x4) throws Exception {
                return Downsampler.super.decode((InputStream) x0, x1, x2, x3, x4);
            }

            /* access modifiers changed from: protected */
            public int getSampleSize(int inWidth, int inHeight, int outWidth, int outHeight) {
                int maxIntegerFactor = (int) Math.ceil((double) Math.max(((float) inHeight) / ((float) outHeight), ((float) inWidth) / ((float) outWidth)));
                int lesserOrEqualSampleSize = Math.max(1, Integer.highestOneBit(maxIntegerFactor));
                return lesserOrEqualSampleSize << (lesserOrEqualSampleSize < maxIntegerFactor ? 1 : 0);
            }

            public String getId() {
                return "AT_MOST.com.bumptech.glide.load.data.bitmap";
            }
        };
        AT_MOST = downsampler2;
        new Downsampler() {
            public /* bridge */ /* synthetic */ Bitmap decode(Object x0, BitmapPool x1, int x2, int x3, DecodeFormat x4) throws Exception {
                return Downsampler.super.decode((InputStream) x0, x1, x2, x3, x4);
            }

            /* access modifiers changed from: protected */
            public int getSampleSize(int i, int i2, int i3, int i4) {
                int i5 = i;
                int i6 = i2;
                int i7 = i3;
                int i8 = i4;
                return 0;
            }

            public String getId() {
                return "NONE.com.bumptech.glide.load.data.bitmap";
            }
        };
        NONE = downsampler3;
    }

    public Bitmap decode(InputStream is, BitmapPool bitmapPool, int i, int i2, DecodeFormat decodeFormat) {
        RecyclableBufferedInputStream recyclableBufferedInputStream;
        MarkEnforcingInputStream markEnforcingInputStream;
        int orientation;
        Throwable th;
        ImageHeaderParser imageHeaderParser;
        BitmapPool pool = bitmapPool;
        int outWidth = i;
        int outHeight = i2;
        DecodeFormat decodeFormat2 = decodeFormat;
        ByteArrayPool byteArrayPool = ByteArrayPool.get();
        byte[] bytesForOptions = byteArrayPool.getBytes();
        byte[] bytesForStream = byteArrayPool.getBytes();
        BitmapFactory.Options options = getDefaultOptions();
        new RecyclableBufferedInputStream(is, bytesForStream);
        RecyclableBufferedInputStream bufferedStream = recyclableBufferedInputStream;
        ExceptionCatchingInputStream exceptionStream = ExceptionCatchingInputStream.obtain(bufferedStream);
        new MarkEnforcingInputStream(exceptionStream);
        MarkEnforcingInputStream invalidatingStream = markEnforcingInputStream;
        try {
            exceptionStream.mark(MARK_POSITION);
            orientation = 0;
            try {
                new ImageHeaderParser(exceptionStream);
                orientation = imageHeaderParser.getOrientation();
                exceptionStream.reset();
            } catch (IOException e) {
                IOException e2 = e;
                if (Log.isLoggable(TAG, 5)) {
                    int w = Log.w(TAG, "Cannot determine the image orientation from header", e2);
                }
                try {
                    exceptionStream.reset();
                } catch (IOException e3) {
                    IOException e4 = e3;
                    if (Log.isLoggable(TAG, 5)) {
                        int w2 = Log.w(TAG, "Cannot reset the input stream", e4);
                    }
                }
            } catch (Throwable th2) {
                Throwable th3 = th2;
                try {
                    exceptionStream.reset();
                } catch (IOException e5) {
                    IOException e6 = e5;
                    if (Log.isLoggable(TAG, 5)) {
                        int w3 = Log.w(TAG, "Cannot reset the input stream", e6);
                    }
                }
                throw th3;
            }
        } catch (IOException e7) {
            IOException e8 = e7;
            if (Log.isLoggable(TAG, 5)) {
                int w4 = Log.w(TAG, "Cannot reset the input stream", e8);
            }
        } catch (Throwable th4) {
            Throwable th5 = th4;
            boolean releaseBytes = byteArrayPool.releaseBytes(bytesForOptions);
            boolean releaseBytes2 = byteArrayPool.releaseBytes(bytesForStream);
            exceptionStream.release();
            releaseOptions(options);
            throw th5;
        }
        options.inTempStorage = bytesForOptions;
        int[] inDimens = getDimensions(invalidatingStream, bufferedStream, options);
        int inWidth = inDimens[0];
        int inHeight = inDimens[1];
        Bitmap downsampled = downsampleWithSize(invalidatingStream, bufferedStream, options, pool, inWidth, inHeight, getRoundedSampleSize(TransformationUtils.getExifOrientationDegrees(orientation), inWidth, inHeight, outWidth, outHeight), decodeFormat2);
        Exception streamException = exceptionStream.getException();
        if (streamException != null) {
            Throwable th6 = th;
            new RuntimeException(streamException);
            throw th6;
        }
        Bitmap rotated = null;
        if (downsampled != null) {
            rotated = TransformationUtils.rotateImageExif(downsampled, pool, orientation);
            if (!downsampled.equals(rotated) && !pool.put(downsampled)) {
                downsampled.recycle();
            }
        }
        Bitmap bitmap = rotated;
        boolean releaseBytes3 = byteArrayPool.releaseBytes(bytesForOptions);
        boolean releaseBytes4 = byteArrayPool.releaseBytes(bytesForStream);
        exceptionStream.release();
        releaseOptions(options);
        return bitmap;
    }

    private int getRoundedSampleSize(int i, int i2, int i3, int i4, int i5) {
        int exactSampleSize;
        int degreesToRotate = i;
        int inWidth = i2;
        int inHeight = i3;
        int outWidth = i4;
        int outHeight = i5;
        int targetHeight = outHeight == Integer.MIN_VALUE ? inHeight : outHeight;
        int targetWidth = outWidth == Integer.MIN_VALUE ? inWidth : outWidth;
        if (degreesToRotate == 90 || degreesToRotate == 270) {
            exactSampleSize = getSampleSize(inHeight, inWidth, targetWidth, targetHeight);
        } else {
            exactSampleSize = getSampleSize(inWidth, inHeight, targetWidth, targetHeight);
        }
        return Math.max(1, exactSampleSize == 0 ? 0 : Integer.highestOneBit(exactSampleSize));
    }

    private Bitmap downsampleWithSize(MarkEnforcingInputStream markEnforcingInputStream, RecyclableBufferedInputStream recyclableBufferedInputStream, BitmapFactory.Options options, BitmapPool bitmapPool, int i, int i2, int i3, DecodeFormat decodeFormat) {
        MarkEnforcingInputStream is = markEnforcingInputStream;
        RecyclableBufferedInputStream bufferedStream = recyclableBufferedInputStream;
        BitmapFactory.Options options2 = options;
        BitmapPool pool = bitmapPool;
        int inWidth = i;
        int inHeight = i2;
        int sampleSize = i3;
        Bitmap.Config config = getConfig(is, decodeFormat);
        options2.inSampleSize = sampleSize;
        options2.inPreferredConfig = config;
        if ((options2.inSampleSize == 1 || 19 <= Build.VERSION.SDK_INT) && shouldUsePool(is)) {
            setInBitmap(options2, pool.getDirty((int) Math.ceil(((double) inWidth) / ((double) sampleSize)), (int) Math.ceil(((double) inHeight) / ((double) sampleSize)), config));
        }
        return decodeStream(is, bufferedStream, options2);
    }

    private static boolean shouldUsePool(InputStream inputStream) {
        ImageHeaderParser imageHeaderParser;
        InputStream is = inputStream;
        if (19 <= Build.VERSION.SDK_INT) {
            return true;
        }
        is.mark(1024);
        try {
            new ImageHeaderParser(is);
            boolean contains = TYPES_THAT_USE_POOL.contains(imageHeaderParser.getType());
            try {
                is.reset();
            } catch (IOException e) {
                IOException e2 = e;
                if (Log.isLoggable(TAG, 5)) {
                    int w = Log.w(TAG, "Cannot reset the input stream", e2);
                }
            }
            return contains;
        } catch (IOException e3) {
            IOException e4 = e3;
            if (Log.isLoggable(TAG, 5)) {
                int w2 = Log.w(TAG, "Cannot determine the image type from header", e4);
            }
            try {
                is.reset();
            } catch (IOException e5) {
                IOException e6 = e5;
                if (Log.isLoggable(TAG, 5)) {
                    int w3 = Log.w(TAG, "Cannot reset the input stream", e6);
                }
            }
            return false;
        } catch (Throwable th) {
            Throwable th2 = th;
            try {
                is.reset();
            } catch (IOException e7) {
                IOException e8 = e7;
                if (Log.isLoggable(TAG, 5)) {
                    int w4 = Log.w(TAG, "Cannot reset the input stream", e8);
                }
            }
            throw th2;
        }
    }

    private static Bitmap.Config getConfig(InputStream inputStream, DecodeFormat decodeFormat) {
        StringBuilder sb;
        Bitmap.Config config;
        ImageHeaderParser imageHeaderParser;
        InputStream is = inputStream;
        DecodeFormat format = decodeFormat;
        if (format == DecodeFormat.ALWAYS_ARGB_8888 || format == DecodeFormat.PREFER_ARGB_8888 || Build.VERSION.SDK_INT == 16) {
            return Bitmap.Config.ARGB_8888;
        }
        boolean hasAlpha = false;
        is.mark(1024);
        try {
            new ImageHeaderParser(is);
            hasAlpha = imageHeaderParser.hasAlpha();
            try {
                is.reset();
            } catch (IOException e) {
                IOException e2 = e;
                if (Log.isLoggable(TAG, 5)) {
                    int w = Log.w(TAG, "Cannot reset the input stream", e2);
                }
            }
        } catch (IOException e3) {
            IOException e4 = e3;
            if (Log.isLoggable(TAG, 5)) {
                new StringBuilder();
                int w2 = Log.w(TAG, sb.append("Cannot determine whether the image has alpha or not from header for format ").append(format).toString(), e4);
            }
            try {
                is.reset();
            } catch (IOException e5) {
                IOException e6 = e5;
                if (Log.isLoggable(TAG, 5)) {
                    int w3 = Log.w(TAG, "Cannot reset the input stream", e6);
                }
            }
        } catch (Throwable th) {
            Throwable th2 = th;
            try {
                is.reset();
            } catch (IOException e7) {
                IOException e8 = e7;
                if (Log.isLoggable(TAG, 5)) {
                    int w4 = Log.w(TAG, "Cannot reset the input stream", e8);
                }
            }
            throw th2;
        }
        if (hasAlpha) {
            config = Bitmap.Config.ARGB_8888;
        } else {
            config = Bitmap.Config.RGB_565;
        }
        return config;
    }

    public int[] getDimensions(MarkEnforcingInputStream is, RecyclableBufferedInputStream bufferedStream, BitmapFactory.Options options) {
        BitmapFactory.Options options2 = options;
        options2.inJustDecodeBounds = true;
        Bitmap decodeStream = decodeStream(is, bufferedStream, options2);
        options2.inJustDecodeBounds = false;
        int[] iArr = new int[2];
        iArr[0] = options2.outWidth;
        int[] iArr2 = iArr;
        iArr2[1] = options2.outHeight;
        return iArr2;
    }

    private static Bitmap decodeStream(MarkEnforcingInputStream markEnforcingInputStream, RecyclableBufferedInputStream recyclableBufferedInputStream, BitmapFactory.Options options) {
        StringBuilder sb;
        MarkEnforcingInputStream is = markEnforcingInputStream;
        RecyclableBufferedInputStream bufferedStream = recyclableBufferedInputStream;
        BitmapFactory.Options options2 = options;
        if (options2.inJustDecodeBounds) {
            is.mark(MARK_POSITION);
        } else {
            bufferedStream.fixMarkLimit();
        }
        Bitmap result = BitmapFactory.decodeStream(is, (Rect) null, options2);
        try {
            if (options2.inJustDecodeBounds) {
                is.reset();
            }
        } catch (IOException e) {
            IOException e2 = e;
            if (Log.isLoggable(TAG, 6)) {
                new StringBuilder();
                int e3 = Log.e(TAG, sb.append("Exception loading inDecodeBounds=").append(options2.inJustDecodeBounds).append(" sample=").append(options2.inSampleSize).toString(), e2);
            }
        }
        return result;
    }

    @TargetApi(11)
    private static void setInBitmap(BitmapFactory.Options options, Bitmap bitmap) {
        BitmapFactory.Options options2 = options;
        Bitmap recycled = bitmap;
        if (11 <= Build.VERSION.SDK_INT) {
            options2.inBitmap = recycled;
        }
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
        	at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:64)
        	at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:70)
        	at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:248)
        	at java.base/java.util.Objects.checkIndex(Objects.java:372)
        	at java.base/java.util.ArrayList.get(ArrayList.java:458)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:598)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    @android.annotation.TargetApi(11)
    private static synchronized android.graphics.BitmapFactory.Options getDefaultOptions() {
        /*
            java.lang.Class<com.bumptech.glide.load.resource.bitmap.Downsampler> r5 = com.bumptech.glide.load.resource.bitmap.Downsampler.class
            monitor-enter(r5)
            java.util.Queue<android.graphics.BitmapFactory$Options> r3 = OPTIONS_QUEUE     // Catch:{ all -> 0x002f }
            r6 = r3
            r3 = r6
            r4 = r6
            r1 = r4
            monitor-enter(r3)     // Catch:{ all -> 0x002f }
            java.util.Queue<android.graphics.BitmapFactory$Options> r3 = OPTIONS_QUEUE     // Catch:{ all -> 0x0029 }
            java.lang.Object r3 = r3.poll()     // Catch:{ all -> 0x0029 }
            android.graphics.BitmapFactory$Options r3 = (android.graphics.BitmapFactory.Options) r3     // Catch:{ all -> 0x0029 }
            r0 = r3
            r3 = r1
            monitor-exit(r3)     // Catch:{ all -> 0x0029 }
            r3 = r0
            if (r3 != 0) goto L_0x0025
            android.graphics.BitmapFactory$Options r3 = new android.graphics.BitmapFactory$Options     // Catch:{ all -> 0x002f }
            r6 = r3
            r3 = r6
            r4 = r6
            r4.<init>()     // Catch:{ all -> 0x002f }
            r0 = r3
            r3 = r0
            resetOptions(r3)     // Catch:{ all -> 0x002f }
        L_0x0025:
            r3 = r0
            r0 = r3
            monitor-exit(r5)
            return r0
        L_0x0029:
            r3 = move-exception
            r2 = r3
            r3 = r1
            monitor-exit(r3)     // Catch:{ all -> 0x0029 }
            r3 = r2
            throw r3     // Catch:{ all -> 0x002f }
        L_0x002f:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.resource.bitmap.Downsampler.getDefaultOptions():android.graphics.BitmapFactory$Options");
    }

    private static void releaseOptions(BitmapFactory.Options options) {
        BitmapFactory.Options decodeBitmapOptions = options;
        resetOptions(decodeBitmapOptions);
        Queue<BitmapFactory.Options> queue = OPTIONS_QUEUE;
        Queue<BitmapFactory.Options> queue2 = queue;
        synchronized (queue) {
            try {
                boolean offer = OPTIONS_QUEUE.offer(decodeBitmapOptions);
            } catch (Throwable th) {
                Throwable th2 = th;
                Queue<BitmapFactory.Options> queue3 = queue2;
                throw th2;
            }
        }
    }

    @TargetApi(11)
    private static void resetOptions(BitmapFactory.Options options) {
        BitmapFactory.Options decodeBitmapOptions = options;
        decodeBitmapOptions.inTempStorage = null;
        decodeBitmapOptions.inDither = false;
        decodeBitmapOptions.inScaled = false;
        decodeBitmapOptions.inSampleSize = 1;
        decodeBitmapOptions.inPreferredConfig = null;
        decodeBitmapOptions.inJustDecodeBounds = false;
        decodeBitmapOptions.outWidth = 0;
        decodeBitmapOptions.outHeight = 0;
        decodeBitmapOptions.outMimeType = null;
        if (11 <= Build.VERSION.SDK_INT) {
            decodeBitmapOptions.inBitmap = null;
            decodeBitmapOptions.inMutable = true;
        }
    }
}

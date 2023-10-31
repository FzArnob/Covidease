package com.google.android.gms.common.images;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.p000v4.util.C1648LruCache;
import android.util.Log;
import android.widget.ImageView;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.Constants;
import com.google.android.gms.internal.base.zak;
import com.google.android.gms.internal.base.zap;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ImageManager {
    /* access modifiers changed from: private */
    public static final Object zamh;
    /* access modifiers changed from: private */
    public static HashSet<Uri> zami;
    private static ImageManager zamj;
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public final Handler mHandler;
    /* access modifiers changed from: private */
    public final ExecutorService zamk = Executors.newFixedThreadPool(4);
    /* access modifiers changed from: private */
    public final zaa zaml = null;
    /* access modifiers changed from: private */
    public final zak zamm;
    /* access modifiers changed from: private */
    public final Map<zaa, ImageReceiver> zamn;
    /* access modifiers changed from: private */
    public final Map<Uri, ImageReceiver> zamo;
    /* access modifiers changed from: private */
    public final Map<Uri, Long> zamp;

    public interface OnImageLoadedListener {
        void onImageLoaded(Uri uri, Drawable drawable, boolean z);
    }

    public static ImageManager create(Context context) {
        ImageManager imageManager;
        Context context2 = context;
        if (zamj == null) {
            new ImageManager(context2, false);
            zamj = imageManager;
        }
        return zamj;
    }

    private static final class zaa extends C1648LruCache<zab, Bitmap> {
        /* access modifiers changed from: protected */
        public final /* synthetic */ int sizeOf(Object obj, Object obj2) {
            Object obj3 = obj;
            Bitmap bitmap = (Bitmap) obj2;
            return bitmap.getHeight() * bitmap.getRowBytes();
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ void entryRemoved(boolean z, Object obj, Object obj2, Object obj3) {
            Bitmap bitmap = (Bitmap) obj2;
            zab zab = (zab) obj;
            super.entryRemoved(z, zab, bitmap, (Bitmap) obj3);
        }
    }

    private final class zac implements Runnable {
        private final /* synthetic */ ImageManager zamr;
        private final zaa zamt;

        public zac(ImageManager imageManager, zaa zaa) {
            this.zamr = imageManager;
            this.zamt = zaa;
        }

        public final void run() {
            ImageReceiver imageReceiver;
            Asserts.checkMainThread("LoadImageRunnable must be executed on the main thread");
            ImageReceiver imageReceiver2 = (ImageReceiver) this.zamr.zamn.get(this.zamt);
            ImageReceiver imageReceiver3 = imageReceiver2;
            if (imageReceiver2 != null) {
                Object remove = this.zamr.zamn.remove(this.zamt);
                imageReceiver3.zac(this.zamt);
            }
            zab zab = this.zamt.zamv;
            zab zab2 = zab;
            if (zab.uri == null) {
                this.zamt.zaa(this.zamr.mContext, this.zamr.zamm, true);
                return;
            }
            Bitmap zaa = this.zamr.zaa(zab2);
            Bitmap bitmap = zaa;
            if (zaa != null) {
                this.zamt.zaa(this.zamr.mContext, bitmap, true);
                return;
            }
            Long l = (Long) this.zamr.zamp.get(zab2.uri);
            Long l2 = l;
            if (l != null) {
                if (SystemClock.elapsedRealtime() - l2.longValue() < 3600000) {
                    this.zamt.zaa(this.zamr.mContext, this.zamr.zamm, true);
                    return;
                }
                Object remove2 = this.zamr.zamp.remove(zab2.uri);
            }
            this.zamt.zaa(this.zamr.mContext, this.zamr.zamm);
            ImageReceiver imageReceiver4 = (ImageReceiver) this.zamr.zamo.get(zab2.uri);
            ImageReceiver imageReceiver5 = imageReceiver4;
            if (imageReceiver4 == null) {
                new ImageReceiver(this.zamr, zab2.uri);
                imageReceiver5 = imageReceiver;
                Object put = this.zamr.zamo.put(zab2.uri, imageReceiver5);
            }
            imageReceiver5.zab(this.zamt);
            if (!(this.zamt instanceof zad)) {
                Object put2 = this.zamr.zamn.put(this.zamt, imageReceiver5);
            }
            Object zacc = ImageManager.zamh;
            Object obj = zacc;
            synchronized (zacc) {
                try {
                    if (!ImageManager.zami.contains(zab2.uri)) {
                        boolean add = ImageManager.zami.add(zab2.uri);
                        imageReceiver5.zace();
                    }
                } catch (Throwable th) {
                    Throwable th2 = th;
                    Object obj2 = obj;
                    throw th2;
                }
            }
        }
    }

    private final class zab implements Runnable {
        private final Uri mUri;
        private final /* synthetic */ ImageManager zamr;
        private final ParcelFileDescriptor zams;

        public zab(ImageManager imageManager, Uri uri, ParcelFileDescriptor parcelFileDescriptor) {
            this.zamr = imageManager;
            this.mUri = uri;
            this.zams = parcelFileDescriptor;
        }

        public final void run() {
            CountDownLatch countDownLatch;
            Runnable runnable;
            StringBuilder sb;
            StringBuilder sb2;
            Asserts.checkNotMainThread("LoadBitmapFromDiskRunnable can't be executed in the main thread");
            boolean z = false;
            Bitmap bitmap = null;
            if (this.zams != null) {
                try {
                    bitmap = BitmapFactory.decodeFileDescriptor(this.zams.getFileDescriptor());
                } catch (OutOfMemoryError e) {
                    String valueOf = String.valueOf(this.mUri);
                    new StringBuilder(34 + String.valueOf(valueOf).length());
                    int e2 = Log.e("ImageManager", sb2.append("OOM while loading bitmap for uri: ").append(valueOf).toString(), e);
                    z = true;
                }
                try {
                    this.zams.close();
                } catch (IOException e3) {
                    int e4 = Log.e("ImageManager", "closed failed", e3);
                }
            }
            new CountDownLatch(1);
            CountDownLatch countDownLatch2 = countDownLatch;
            new zad(this.zamr, this.mUri, bitmap, z, countDownLatch2);
            boolean post = this.zamr.mHandler.post(runnable);
            try {
                countDownLatch2.await();
            } catch (InterruptedException e5) {
                String valueOf2 = String.valueOf(this.mUri);
                new StringBuilder(32 + String.valueOf(valueOf2).length());
                int w = Log.w("ImageManager", sb.append("Latch interrupted while posting ").append(valueOf2).toString());
            }
        }
    }

    @KeepName
    private final class ImageReceiver extends ResultReceiver {
        private final Uri mUri;
        /* access modifiers changed from: private */
        public final ArrayList<zaa> zamq;
        private final /* synthetic */ ImageManager zamr;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        ImageReceiver(com.google.android.gms.common.images.ImageManager r9, android.net.Uri r10) {
            /*
                r8 = this;
                r0 = r8
                r1 = r9
                r2 = r10
                r3 = r0
                r4 = r1
                r3.zamr = r4
                r3 = r0
                com.google.android.gms.internal.base.zap r4 = new com.google.android.gms.internal.base.zap
                r7 = r4
                r4 = r7
                r5 = r7
                android.os.Looper r6 = android.os.Looper.getMainLooper()
                r5.<init>(r6)
                r3.<init>(r4)
                r3 = r0
                r4 = r2
                r3.mUri = r4
                r3 = r0
                java.util.ArrayList r4 = new java.util.ArrayList
                r7 = r4
                r4 = r7
                r5 = r7
                r5.<init>()
                r3.zamq = r4
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.images.ImageManager.ImageReceiver.<init>(com.google.android.gms.common.images.ImageManager, android.net.Uri):void");
        }

        public final void zab(zaa zaa) {
            Asserts.checkMainThread("ImageReceiver.addImageRequest() must be called in the main thread");
            boolean add = this.zamq.add(zaa);
        }

        public final void zac(zaa zaa) {
            Asserts.checkMainThread("ImageReceiver.removeImageRequest() must be called in the main thread");
            boolean remove = this.zamq.remove(zaa);
        }

        public final void zace() {
            Intent intent;
            new Intent(Constants.ACTION_LOAD_IMAGE);
            Intent intent2 = intent;
            Intent intent3 = intent2;
            Intent putExtra = intent2.putExtra(Constants.EXTRA_URI, this.mUri);
            Intent putExtra2 = intent3.putExtra(Constants.EXTRA_RESULT_RECEIVER, this);
            Intent putExtra3 = intent3.putExtra(Constants.EXTRA_PRIORITY, 3);
            this.zamr.mContext.sendBroadcast(intent3);
        }

        public final void onReceiveResult(int i, Bundle bundle) {
            Runnable runnable;
            int i2 = i;
            new zab(this.zamr, this.mUri, (ParcelFileDescriptor) bundle.getParcelable("com.google.android.gms.extra.fileDescriptor"));
            this.zamr.zamk.execute(runnable);
        }
    }

    private final class zad implements Runnable {
        private final Bitmap mBitmap;
        private final Uri mUri;
        private final CountDownLatch zadr;
        private final /* synthetic */ ImageManager zamr;
        private boolean zamu;

        public zad(ImageManager imageManager, Uri uri, Bitmap bitmap, boolean z, CountDownLatch countDownLatch) {
            this.zamr = imageManager;
            this.mUri = uri;
            this.mBitmap = bitmap;
            this.zamu = z;
            this.zadr = countDownLatch;
        }

        public final void run() {
            Object obj;
            Asserts.checkMainThread("OnBitmapLoadedRunnable must be executed in the main thread");
            boolean z = this.mBitmap != null;
            if (this.zamr.zaml != null) {
                if (this.zamu) {
                    this.zamr.zaml.evictAll();
                    System.gc();
                    this.zamu = false;
                    boolean post = this.zamr.mHandler.post(this);
                    return;
                } else if (z) {
                    new zab(this.mUri);
                    Object put = this.zamr.zaml.put(obj, this.mBitmap);
                }
            }
            ImageReceiver imageReceiver = (ImageReceiver) this.zamr.zamo.remove(this.mUri);
            ImageReceiver imageReceiver2 = imageReceiver;
            if (imageReceiver != null) {
                boolean z2 = z;
                ArrayList zaa = imageReceiver2.zamq;
                int size = zaa.size();
                for (int i = 0; i < size; i++) {
                    zaa zaa2 = (zaa) zaa.get(i);
                    if (z2) {
                        zaa2.zaa(this.zamr.mContext, this.mBitmap, false);
                    } else {
                        Object put2 = this.zamr.zamp.put(this.mUri, Long.valueOf(SystemClock.elapsedRealtime()));
                        zaa2.zaa(this.zamr.mContext, this.zamr.zamm, false);
                    }
                    if (!(zaa2 instanceof zad)) {
                        Object remove = this.zamr.zamn.remove(zaa2);
                    }
                }
            }
            this.zadr.countDown();
            Object zacc = ImageManager.zamh;
            Object obj2 = zacc;
            synchronized (zacc) {
                try {
                    boolean remove2 = ImageManager.zami.remove(this.mUri);
                } catch (Throwable th) {
                    Throwable th2 = th;
                    Object obj3 = obj2;
                    throw th2;
                }
            }
        }
    }

    private ImageManager(Context context, boolean z) {
        Handler handler;
        zak zak;
        Map<zaa, ImageReceiver> map;
        Map<Uri, ImageReceiver> map2;
        Map<Uri, Long> map3;
        boolean z2 = z;
        this.mContext = context.getApplicationContext();
        new zap(Looper.getMainLooper());
        this.mHandler = handler;
        new zak();
        this.zamm = zak;
        new HashMap();
        this.zamn = map;
        new HashMap();
        this.zamo = map2;
        new HashMap();
        this.zamp = map3;
    }

    public final void loadImage(ImageView imageView, Uri uri) {
        zaa zaa2;
        new zac(imageView, uri);
        zaa(zaa2);
    }

    public final void loadImage(ImageView imageView, int i) {
        zaa zaa2;
        new zac(imageView, i);
        zaa(zaa2);
    }

    public final void loadImage(ImageView imageView, Uri uri, int i) {
        zaa zaa2;
        new zac(imageView, uri);
        zaa zaa3 = zaa2;
        zaa3.zamx = i;
        zaa(zaa3);
    }

    public final void loadImage(OnImageLoadedListener onImageLoadedListener, Uri uri) {
        zaa zaa2;
        new zad(onImageLoadedListener, uri);
        zaa(zaa2);
    }

    public final void loadImage(OnImageLoadedListener onImageLoadedListener, Uri uri, int i) {
        zaa zaa2;
        new zad(onImageLoadedListener, uri);
        zaa zaa3 = zaa2;
        zaa3.zamx = i;
        zaa(zaa3);
    }

    private final void zaa(zaa zaa2) {
        Runnable runnable;
        Asserts.checkMainThread("ImageManager.loadImage() must be called in the main thread");
        new zac(this, zaa2);
        runnable.run();
    }

    /* access modifiers changed from: private */
    public final Bitmap zaa(zab zab2) {
        zab zab3 = zab2;
        if (this.zaml == null) {
            return null;
        }
        return (Bitmap) this.zaml.get(zab3);
    }

    static {
        Object obj;
        HashSet<Uri> hashSet;
        new Object();
        zamh = obj;
        new HashSet<>();
        zami = hashSet;
    }
}

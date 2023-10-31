package com.google.android.gms.common.images;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.google.android.gms.common.images.ImageManager;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.Objects;
import java.lang.ref.WeakReference;

public final class zad extends zaa {
    private WeakReference<ImageManager.OnImageLoadedListener> zand;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zad(ImageManager.OnImageLoadedListener onImageLoadedListener, Uri uri) {
        super(uri, 0);
        WeakReference<ImageManager.OnImageLoadedListener> weakReference;
        ImageManager.OnImageLoadedListener onImageLoadedListener2 = onImageLoadedListener;
        Asserts.checkNotNull(onImageLoadedListener2);
        new WeakReference<>(onImageLoadedListener2);
        this.zand = weakReference;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zamv);
    }

    /* JADX WARNING: type inference failed for: r9v0, types: [java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r9) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r5 = r1
            boolean r5 = r5 instanceof com.google.android.gms.common.images.zad
            if (r5 != 0) goto L_0x000a
            r5 = 0
            r0 = r5
        L_0x0009:
            return r0
        L_0x000a:
            r5 = r0
            r6 = r1
            if (r5 != r6) goto L_0x0011
            r5 = 1
            r0 = r5
            goto L_0x0009
        L_0x0011:
            r5 = r1
            com.google.android.gms.common.images.zad r5 = (com.google.android.gms.common.images.zad) r5
            r2 = r5
            r5 = r0
            java.lang.ref.WeakReference<com.google.android.gms.common.images.ImageManager$OnImageLoadedListener> r5 = r5.zand
            java.lang.Object r5 = r5.get()
            com.google.android.gms.common.images.ImageManager$OnImageLoadedListener r5 = (com.google.android.gms.common.images.ImageManager.OnImageLoadedListener) r5
            r3 = r5
            r5 = r2
            java.lang.ref.WeakReference<com.google.android.gms.common.images.ImageManager$OnImageLoadedListener> r5 = r5.zand
            java.lang.Object r5 = r5.get()
            com.google.android.gms.common.images.ImageManager$OnImageLoadedListener r5 = (com.google.android.gms.common.images.ImageManager.OnImageLoadedListener) r5
            r7 = r5
            r5 = r7
            r6 = r7
            r4 = r6
            if (r5 == 0) goto L_0x0048
            r5 = r3
            if (r5 == 0) goto L_0x0048
            r5 = r4
            r6 = r3
            boolean r5 = com.google.android.gms.common.internal.Objects.equal(r5, r6)
            if (r5 == 0) goto L_0x0048
            r5 = r2
            com.google.android.gms.common.images.zab r5 = r5.zamv
            r6 = r0
            com.google.android.gms.common.images.zab r6 = r6.zamv
            boolean r5 = com.google.android.gms.common.internal.Objects.equal(r5, r6)
            if (r5 == 0) goto L_0x0048
            r5 = 1
            r0 = r5
            goto L_0x0009
        L_0x0048:
            r5 = 0
            r0 = r5
            goto L_0x0009
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.images.zad.equals(java.lang.Object):boolean");
    }

    /* access modifiers changed from: protected */
    public final void zaa(Drawable drawable, boolean z, boolean z2, boolean z3) {
        Drawable drawable2 = drawable;
        boolean z4 = z;
        boolean z5 = z3;
        if (!z2) {
            ImageManager.OnImageLoadedListener onImageLoadedListener = (ImageManager.OnImageLoadedListener) this.zand.get();
            ImageManager.OnImageLoadedListener onImageLoadedListener2 = onImageLoadedListener;
            if (onImageLoadedListener != null) {
                onImageLoadedListener2.onImageLoaded(this.zamv.uri, drawable2, z5);
            }
        }
    }
}

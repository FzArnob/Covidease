package com.google.android.gms.common.images;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.p003v7.widget.helper.ItemTouchHelper;
import android.widget.ImageView;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.internal.base.zae;
import com.google.android.gms.internal.base.zaj;
import java.lang.ref.WeakReference;

public final class zac extends zaa {
    private WeakReference<ImageView> zanc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zac(ImageView imageView, Uri uri) {
        super(uri, 0);
        WeakReference<ImageView> weakReference;
        ImageView imageView2 = imageView;
        Asserts.checkNotNull(imageView2);
        new WeakReference<>(imageView2);
        this.zanc = weakReference;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zac(ImageView imageView, int i) {
        super((Uri) null, i);
        WeakReference<ImageView> weakReference;
        ImageView imageView2 = imageView;
        Asserts.checkNotNull(imageView2);
        new WeakReference<>(imageView2);
        this.zanc = weakReference;
    }

    public final int hashCode() {
        return 0;
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
            boolean r5 = r5 instanceof com.google.android.gms.common.images.zac
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
            com.google.android.gms.common.images.zac r5 = (com.google.android.gms.common.images.zac) r5
            r2 = r5
            r5 = r0
            java.lang.ref.WeakReference<android.widget.ImageView> r5 = r5.zanc
            java.lang.Object r5 = r5.get()
            android.widget.ImageView r5 = (android.widget.ImageView) r5
            r3 = r5
            r5 = r2
            java.lang.ref.WeakReference<android.widget.ImageView> r5 = r5.zanc
            java.lang.Object r5 = r5.get()
            android.widget.ImageView r5 = (android.widget.ImageView) r5
            r7 = r5
            r5 = r7
            r6 = r7
            r4 = r6
            if (r5 == 0) goto L_0x003c
            r5 = r3
            if (r5 == 0) goto L_0x003c
            r5 = r4
            r6 = r3
            boolean r5 = com.google.android.gms.common.internal.Objects.equal(r5, r6)
            if (r5 == 0) goto L_0x003c
            r5 = 1
            r0 = r5
            goto L_0x0009
        L_0x003c:
            r5 = 0
            r0 = r5
            goto L_0x0009
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.images.zac.equals(java.lang.Object):boolean");
    }

    /* access modifiers changed from: protected */
    public final void zaa(Drawable drawable, boolean z, boolean z2, boolean z3) {
        Drawable drawable2;
        Drawable drawable3 = drawable;
        boolean z4 = z;
        boolean z5 = z2;
        boolean z6 = z3;
        ImageView imageView = (ImageView) this.zanc.get();
        ImageView imageView2 = imageView;
        if (imageView != null) {
            boolean z7 = z6;
            boolean z8 = z5;
            boolean z9 = z4;
            Drawable drawable4 = drawable3;
            ImageView imageView3 = imageView2;
            boolean z10 = !z8 && !z7;
            boolean z11 = z10;
            if (z10 && (imageView3 instanceof zaj)) {
                int zach = zaj.zach();
                if (this.zamx != 0 && zach == this.zamx) {
                    return;
                }
            }
            boolean zaa = zaa(z9, z8);
            Drawable drawable5 = drawable4;
            if (zaa) {
                Drawable drawable6 = drawable4;
                Drawable drawable7 = imageView3.getDrawable();
                Drawable drawable8 = null;
                if (drawable7 != null) {
                    if (drawable7 instanceof zae) {
                        drawable8 = ((zae) drawable7).zacf();
                    } else {
                        drawable8 = drawable7;
                    }
                }
                new zae(drawable8, drawable6);
                drawable5 = drawable2;
            }
            imageView3.setImageDrawable(drawable5);
            if (imageView3 instanceof zaj) {
                zaj.zaa(z7 ? this.zamv.uri : null);
                zaj.zai(z11 ? this.zamx : 0);
            }
            if (zaa) {
                ((zae) drawable5).startTransition(ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
            }
        }
    }
}

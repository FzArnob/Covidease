package com.google.android.gms.common.images;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.internal.base.zak;

public abstract class zaa {
    final zab zamv;
    private int zamw = 0;
    protected int zamx = 0;
    private boolean zamy = false;
    private boolean zamz = true;
    private boolean zana = false;
    private boolean zanb = true;

    public zaa(Uri uri, int i) {
        zab zab;
        new zab(uri);
        this.zamv = zab;
        this.zamx = i;
    }

    /* access modifiers changed from: protected */
    public abstract void zaa(Drawable drawable, boolean z, boolean z2, boolean z3);

    /* access modifiers changed from: package-private */
    public final void zaa(Context context, Bitmap bitmap, boolean z) {
        Drawable drawable;
        Bitmap bitmap2 = bitmap;
        Asserts.checkNotNull(bitmap2);
        new BitmapDrawable(context.getResources(), bitmap2);
        zaa(drawable, z, false, true);
    }

    /* access modifiers changed from: package-private */
    public final void zaa(Context context, zak zak) {
        Context context2 = context;
        zak zak2 = zak;
        if (this.zanb) {
            zaa((Drawable) null, false, true, false);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zaa(Context context, zak zak, boolean z) {
        Context context2 = context;
        zak zak2 = zak;
        boolean z2 = z;
        Drawable drawable = null;
        if (this.zamx != 0) {
            Context context3 = context2;
            Context context4 = context3;
            drawable = context3.getResources().getDrawable(this.zamx);
        }
        zaa(drawable, z2, false, false);
    }

    /* access modifiers changed from: protected */
    public final boolean zaa(boolean z, boolean z2) {
        return this.zamz && !z2 && !z;
    }
}

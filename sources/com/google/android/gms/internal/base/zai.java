package com.google.android.gms.internal.base;

import android.graphics.drawable.Drawable;

final class zai extends Drawable.ConstantState {
    int mChangingConfigurations;
    int zanw;

    zai(zai zai) {
        zai zai2 = zai;
        if (zai2 != null) {
            this.mChangingConfigurations = zai2.mChangingConfigurations;
            this.zanw = zai2.zanw;
        }
    }

    public final Drawable newDrawable() {
        Drawable drawable;
        new zae(this);
        return drawable;
    }

    public final int getChangingConfigurations() {
        return this.mChangingConfigurations;
    }
}

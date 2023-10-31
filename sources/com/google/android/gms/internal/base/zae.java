package com.google.android.gms.internal.base;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.support.p003v7.widget.helper.ItemTouchHelper;

public final class zae extends Drawable implements Drawable.Callback {
    private int mAlpha;
    private int mFrom;
    private boolean zamz;
    private int zanh;
    private long zani;
    private int zanj;
    private int zank;
    private int zanl;
    private boolean zanm;
    private zai zann;
    private Drawable zano;
    private Drawable zanp;
    private boolean zanq;
    private boolean zanr;
    private boolean zans;
    private int zant;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public zae(Drawable drawable, Drawable drawable2) {
        this((zai) null);
        zag zag = drawable;
        zag zag2 = drawable2;
        zag = zag == null ? zag.zanu : zag;
        this.zano = zag;
        zag.setCallback(this);
        this.zann.zanw |= zag.getChangingConfigurations();
        zag2 = zag2 == null ? zag.zanu : zag2;
        this.zanp = zag2;
        zag2.setCallback(this);
        this.zann.zanw |= zag2.getChangingConfigurations();
    }

    zae(zai zai) {
        zai zai2;
        this.zanh = 0;
        this.zank = 255;
        this.mAlpha = 0;
        this.zamz = true;
        new zai(zai);
        this.zann = zai2;
    }

    public final void invalidateDrawable(Drawable drawable) {
        Drawable drawable2 = drawable;
        Drawable.Callback callback = getCallback();
        Drawable.Callback callback2 = callback;
        if (callback != null) {
            callback2.invalidateDrawable(this);
        }
    }

    public final void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        Drawable drawable2 = drawable;
        Runnable runnable2 = runnable;
        long j2 = j;
        Drawable.Callback callback = getCallback();
        Drawable.Callback callback2 = callback;
        if (callback != null) {
            callback2.scheduleDrawable(this, runnable2, j2);
        }
    }

    public final void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Drawable drawable2 = drawable;
        Runnable runnable2 = runnable;
        Drawable.Callback callback = getCallback();
        Drawable.Callback callback2 = callback;
        if (callback != null) {
            callback2.unscheduleDrawable(this, runnable2);
        }
    }

    public final int getChangingConfigurations() {
        return super.getChangingConfigurations() | this.zann.mChangingConfigurations | this.zann.zanw;
    }

    public final void setAlpha(int i) {
        int i2 = i;
        if (this.mAlpha == this.zank) {
            this.mAlpha = i2;
        }
        this.zank = i2;
        invalidateSelf();
    }

    public final void setColorFilter(ColorFilter colorFilter) {
        ColorFilter colorFilter2 = colorFilter;
        this.zano.setColorFilter(colorFilter2);
        this.zanp.setColorFilter(colorFilter2);
    }

    public final int getIntrinsicWidth() {
        return Math.max(this.zano.getIntrinsicWidth(), this.zanp.getIntrinsicWidth());
    }

    public final int getIntrinsicHeight() {
        return Math.max(this.zano.getIntrinsicHeight(), this.zanp.getIntrinsicHeight());
    }

    /* access modifiers changed from: protected */
    public final void onBoundsChange(Rect rect) {
        Rect rect2 = rect;
        this.zano.setBounds(rect2);
        this.zanp.setBounds(rect2);
    }

    public final Drawable.ConstantState getConstantState() {
        if (!canConstantState()) {
            return null;
        }
        this.zann.mChangingConfigurations = getChangingConfigurations();
        return this.zann;
    }

    public final int getOpacity() {
        if (!this.zans) {
            this.zant = Drawable.resolveOpacity(this.zano.getOpacity(), this.zanp.getOpacity());
            this.zans = true;
        }
        return this.zant;
    }

    private final boolean canConstantState() {
        if (!this.zanq) {
            this.zanr = (this.zano.getConstantState() == null || this.zanp.getConstantState() == null) ? false : true;
            this.zanq = true;
        }
        return this.zanr;
    }

    public final Drawable mutate() {
        Throwable th;
        if (!this.zanm && super.mutate() == this) {
            if (!canConstantState()) {
                Throwable th2 = th;
                new IllegalStateException("One or more children of this LayerDrawable does not have constant state; this drawable cannot be mutated.");
                throw th2;
            }
            Drawable mutate = this.zano.mutate();
            Drawable mutate2 = this.zanp.mutate();
            this.zanm = true;
        }
        return this;
    }

    public final Drawable zacf() {
        return this.zanp;
    }

    public final void startTransition(int i) {
        int i2 = i;
        this.mFrom = 0;
        this.zanj = this.zank;
        this.mAlpha = 0;
        this.zanl = ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
        this.zanh = 1;
        invalidateSelf();
    }

    public final void draw(Canvas canvas) {
        Canvas canvas2 = canvas;
        boolean z = true;
        switch (this.zanh) {
            case 1:
                this.zani = SystemClock.uptimeMillis();
                z = false;
                this.zanh = 2;
                break;
            case 2:
                if (this.zani >= 0) {
                    float uptimeMillis = ((float) (SystemClock.uptimeMillis() - this.zani)) / ((float) this.zanl);
                    float f = uptimeMillis;
                    boolean z2 = uptimeMillis >= 1.0f;
                    z = z2;
                    if (z2) {
                        this.zanh = 0;
                    }
                    this.mAlpha = (int) (0.0f + (((float) this.zanj) * Math.min(f, 1.0f)));
                    break;
                }
                break;
        }
        int i = this.mAlpha;
        boolean z3 = this.zamz;
        Drawable drawable = this.zano;
        Drawable drawable2 = this.zanp;
        if (z) {
            if (!z3 || i == 0) {
                drawable.draw(canvas2);
            }
            if (i == this.zank) {
                drawable2.setAlpha(this.zank);
                drawable2.draw(canvas2);
                return;
            }
            return;
        }
        if (z3) {
            drawable.setAlpha(this.zank - i);
        }
        drawable.draw(canvas2);
        if (z3) {
            drawable.setAlpha(this.zank);
        }
        if (i > 0) {
            drawable2.setAlpha(i);
            drawable2.draw(canvas2);
            drawable2.setAlpha(this.zank);
        }
        invalidateSelf();
    }
}

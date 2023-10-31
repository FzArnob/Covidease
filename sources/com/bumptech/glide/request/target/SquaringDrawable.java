package com.bumptech.glide.request.target;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;

public class SquaringDrawable extends GlideDrawable {
    private boolean mutated;
    private State state;
    private GlideDrawable wrapped;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SquaringDrawable(com.bumptech.glide.load.resource.drawable.GlideDrawable r10, int r11) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r2 = r11
            r3 = r0
            com.bumptech.glide.request.target.SquaringDrawable$State r4 = new com.bumptech.glide.request.target.SquaringDrawable$State
            r8 = r4
            r4 = r8
            r5 = r8
            r6 = r1
            android.graphics.drawable.Drawable$ConstantState r6 = r6.getConstantState()
            r7 = r2
            r5.<init>(r6, r7)
            r5 = r1
            r6 = 0
            r3.<init>(r4, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.target.SquaringDrawable.<init>(com.bumptech.glide.load.resource.drawable.GlideDrawable, int):void");
    }

    SquaringDrawable(State state2, GlideDrawable glideDrawable, Resources resources) {
        State state3 = state2;
        GlideDrawable wrapped2 = glideDrawable;
        Resources res = resources;
        this.state = state3;
        if (wrapped2 != null) {
            this.wrapped = wrapped2;
        } else if (res != null) {
            this.wrapped = (GlideDrawable) state3.wrapped.newDrawable(res);
        } else {
            this.wrapped = (GlideDrawable) state3.wrapped.newDrawable();
        }
    }

    public void setBounds(int i, int i2, int i3, int i4) {
        int left = i;
        int top = i2;
        int right = i3;
        int bottom = i4;
        super.setBounds(left, top, right, bottom);
        this.wrapped.setBounds(left, top, right, bottom);
    }

    public void setBounds(Rect rect) {
        Rect bounds = rect;
        super.setBounds(bounds);
        this.wrapped.setBounds(bounds);
    }

    public void setChangingConfigurations(int configs) {
        this.wrapped.setChangingConfigurations(configs);
    }

    public int getChangingConfigurations() {
        return this.wrapped.getChangingConfigurations();
    }

    public void setDither(boolean dither) {
        this.wrapped.setDither(dither);
    }

    public void setFilterBitmap(boolean filter) {
        this.wrapped.setFilterBitmap(filter);
    }

    @TargetApi(11)
    public Drawable.Callback getCallback() {
        return this.wrapped.getCallback();
    }

    @TargetApi(19)
    public int getAlpha() {
        return this.wrapped.getAlpha();
    }

    public void setColorFilter(int color, PorterDuff.Mode mode) {
        this.wrapped.setColorFilter(color, mode);
    }

    public void clearColorFilter() {
        this.wrapped.clearColorFilter();
    }

    public Drawable getCurrent() {
        return this.wrapped.getCurrent();
    }

    public boolean setVisible(boolean visible, boolean restart) {
        return this.wrapped.setVisible(visible, restart);
    }

    public int getIntrinsicWidth() {
        return this.state.side;
    }

    public int getIntrinsicHeight() {
        return this.state.side;
    }

    public int getMinimumWidth() {
        return this.wrapped.getMinimumWidth();
    }

    public int getMinimumHeight() {
        return this.wrapped.getMinimumHeight();
    }

    public boolean getPadding(Rect padding) {
        return this.wrapped.getPadding(padding);
    }

    public void invalidateSelf() {
        super.invalidateSelf();
        this.wrapped.invalidateSelf();
    }

    public void unscheduleSelf(Runnable runnable) {
        Runnable what = runnable;
        super.unscheduleSelf(what);
        this.wrapped.unscheduleSelf(what);
    }

    public void scheduleSelf(Runnable runnable, long j) {
        Runnable what = runnable;
        long when = j;
        super.scheduleSelf(what, when);
        this.wrapped.scheduleSelf(what, when);
    }

    public void draw(Canvas canvas) {
        this.wrapped.draw(canvas);
    }

    public void setAlpha(int i) {
        this.wrapped.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.wrapped.setColorFilter(colorFilter);
    }

    public int getOpacity() {
        return this.wrapped.getOpacity();
    }

    public boolean isAnimated() {
        return this.wrapped.isAnimated();
    }

    public void setLoopCount(int loopCount) {
        this.wrapped.setLoopCount(loopCount);
    }

    public void start() {
        this.wrapped.start();
    }

    public void stop() {
        this.wrapped.stop();
    }

    public boolean isRunning() {
        return this.wrapped.isRunning();
    }

    public Drawable mutate() {
        State state2;
        if (!this.mutated && super.mutate() == this) {
            this.wrapped = (GlideDrawable) this.wrapped.mutate();
            new State(this.state);
            this.state = state2;
            this.mutated = true;
        }
        return this;
    }

    public Drawable.ConstantState getConstantState() {
        return this.state;
    }

    static class State extends Drawable.ConstantState {
        /* access modifiers changed from: private */
        public final int side;
        /* access modifiers changed from: private */
        public final Drawable.ConstantState wrapped;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        State(com.bumptech.glide.request.target.SquaringDrawable.State r6) {
            /*
                r5 = this;
                r0 = r5
                r1 = r6
                r2 = r0
                r3 = r1
                android.graphics.drawable.Drawable$ConstantState r3 = r3.wrapped
                r4 = r1
                int r4 = r4.side
                r2.<init>(r3, r4)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.target.SquaringDrawable.State.<init>(com.bumptech.glide.request.target.SquaringDrawable$State):void");
        }

        State(Drawable.ConstantState wrapped2, int side2) {
            this.wrapped = wrapped2;
            this.side = side2;
        }

        public Drawable newDrawable() {
            return newDrawable((Resources) null);
        }

        public Drawable newDrawable(Resources res) {
            Drawable drawable;
            new SquaringDrawable(this, (GlideDrawable) null, res);
            return drawable;
        }

        public int getChangingConfigurations() {
            return 0;
        }
    }
}

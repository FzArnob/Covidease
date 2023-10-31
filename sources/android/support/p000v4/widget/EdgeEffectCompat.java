package android.support.p000v4.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.support.annotation.NonNull;
import android.widget.EdgeEffect;

/* renamed from: android.support.v4.widget.EdgeEffectCompat */
public final class EdgeEffectCompat {
    private EdgeEffect mEdgeEffect;

    @Deprecated
    public EdgeEffectCompat(Context context) {
        EdgeEffect edgeEffect;
        new EdgeEffect(context);
        this.mEdgeEffect = edgeEffect;
    }

    @Deprecated
    public void setSize(int width, int height) {
        this.mEdgeEffect.setSize(width, height);
    }

    @Deprecated
    public boolean isFinished() {
        return this.mEdgeEffect.isFinished();
    }

    @Deprecated
    public void finish() {
        this.mEdgeEffect.finish();
    }

    @Deprecated
    public boolean onPull(float deltaDistance) {
        this.mEdgeEffect.onPull(deltaDistance);
        return true;
    }

    @Deprecated
    public boolean onPull(float deltaDistance, float displacement) {
        onPull(this.mEdgeEffect, deltaDistance, displacement);
        return true;
    }

    public static void onPull(@NonNull EdgeEffect edgeEffect, float f, float f2) {
        EdgeEffect edgeEffect2 = edgeEffect;
        float deltaDistance = f;
        float displacement = f2;
        if (Build.VERSION.SDK_INT >= 21) {
            edgeEffect2.onPull(deltaDistance, displacement);
        } else {
            edgeEffect2.onPull(deltaDistance);
        }
    }

    @Deprecated
    public boolean onRelease() {
        this.mEdgeEffect.onRelease();
        return this.mEdgeEffect.isFinished();
    }

    @Deprecated
    public boolean onAbsorb(int velocity) {
        this.mEdgeEffect.onAbsorb(velocity);
        return true;
    }

    @Deprecated
    public boolean draw(Canvas canvas) {
        return this.mEdgeEffect.draw(canvas);
    }
}

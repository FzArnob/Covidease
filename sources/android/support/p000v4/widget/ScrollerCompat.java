package android.support.p000v4.widget;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.OverScroller;

@Deprecated
/* renamed from: android.support.v4.widget.ScrollerCompat */
public final class ScrollerCompat {
    OverScroller mScroller;

    @Deprecated
    public static ScrollerCompat create(Context context) {
        return create(context, (Interpolator) null);
    }

    @Deprecated
    public static ScrollerCompat create(Context context, Interpolator interpolator) {
        ScrollerCompat scrollerCompat;
        new ScrollerCompat(context, interpolator);
        return scrollerCompat;
    }

    ScrollerCompat(Context context, Interpolator interpolator) {
        OverScroller overScroller;
        OverScroller overScroller2;
        OverScroller overScroller3;
        Context context2 = context;
        Interpolator interpolator2 = interpolator;
        if (interpolator2 != null) {
            overScroller2 = overScroller3;
            new OverScroller(context2, interpolator2);
        } else {
            overScroller2 = overScroller;
            new OverScroller(context2);
        }
        this.mScroller = overScroller2;
    }

    @Deprecated
    public boolean isFinished() {
        return this.mScroller.isFinished();
    }

    @Deprecated
    public int getCurrX() {
        return this.mScroller.getCurrX();
    }

    @Deprecated
    public int getCurrY() {
        return this.mScroller.getCurrY();
    }

    @Deprecated
    public int getFinalX() {
        return this.mScroller.getFinalX();
    }

    @Deprecated
    public int getFinalY() {
        return this.mScroller.getFinalY();
    }

    @Deprecated
    public float getCurrVelocity() {
        return this.mScroller.getCurrVelocity();
    }

    @Deprecated
    public boolean computeScrollOffset() {
        return this.mScroller.computeScrollOffset();
    }

    @Deprecated
    public void startScroll(int startX, int startY, int dx, int dy) {
        this.mScroller.startScroll(startX, startY, dx, dy);
    }

    @Deprecated
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        this.mScroller.startScroll(startX, startY, dx, dy, duration);
    }

    @Deprecated
    public void fling(int startX, int startY, int velocityX, int velocityY, int minX, int maxX, int minY, int maxY) {
        this.mScroller.fling(startX, startY, velocityX, velocityY, minX, maxX, minY, maxY);
    }

    @Deprecated
    public void fling(int startX, int startY, int velocityX, int velocityY, int minX, int maxX, int minY, int maxY, int overX, int overY) {
        this.mScroller.fling(startX, startY, velocityX, velocityY, minX, maxX, minY, maxY, overX, overY);
    }

    @Deprecated
    public boolean springBack(int startX, int startY, int minX, int maxX, int minY, int maxY) {
        return this.mScroller.springBack(startX, startY, minX, maxX, minY, maxY);
    }

    @Deprecated
    public void abortAnimation() {
        this.mScroller.abortAnimation();
    }

    @Deprecated
    public void notifyHorizontalEdgeReached(int startX, int finalX, int overX) {
        this.mScroller.notifyHorizontalEdgeReached(startX, finalX, overX);
    }

    @Deprecated
    public void notifyVerticalEdgeReached(int startY, int finalY, int overY) {
        this.mScroller.notifyVerticalEdgeReached(startY, finalY, overY);
    }

    @Deprecated
    public boolean isOverScrolled() {
        return this.mScroller.isOverScrolled();
    }
}

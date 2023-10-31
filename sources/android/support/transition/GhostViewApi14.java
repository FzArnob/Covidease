package android.support.transition;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

@SuppressLint({"ViewConstructor"})
class GhostViewApi14 extends View implements GhostViewImpl {
    Matrix mCurrentMatrix;
    private int mDeltaX;
    private int mDeltaY;
    private final Matrix mMatrix;
    private final ViewTreeObserver.OnPreDrawListener mOnPreDrawListener;
    int mReferences;
    ViewGroup mStartParent;
    View mStartView;
    final View mView;

    static GhostViewImpl addGhost(View view, ViewGroup viewGroup) {
        GhostViewApi14 ghostViewApi14;
        View view2 = view;
        ViewGroup viewGroup2 = viewGroup;
        GhostViewApi14 ghostView = getGhostView(view2);
        if (ghostView == null) {
            FrameLayout frameLayout = findFrameLayout(viewGroup2);
            if (frameLayout == null) {
                return null;
            }
            new GhostViewApi14(view2);
            ghostView = ghostViewApi14;
            frameLayout.addView(ghostView);
        }
        ghostView.mReferences++;
        return ghostView;
    }

    static void removeGhost(View view) {
        GhostViewApi14 ghostView = getGhostView(view);
        if (ghostView != null) {
            GhostViewApi14 ghostViewApi14 = ghostView;
            ghostViewApi14.mReferences--;
            if (ghostView.mReferences <= 0) {
                ViewParent parent = ghostView.getParent();
                if (parent instanceof ViewGroup) {
                    ViewGroup group = (ViewGroup) parent;
                    group.endViewTransition(ghostView);
                    group.removeView(ghostView);
                }
            }
        }
    }

    private static FrameLayout findFrameLayout(ViewGroup viewGroup) {
        ViewGroup viewGroup2 = viewGroup;
        while (!(viewGroup2 instanceof FrameLayout)) {
            ViewParent parent = viewGroup2.getParent();
            if (!(parent instanceof ViewGroup)) {
                return null;
            }
            viewGroup2 = (ViewGroup) parent;
        }
        return (FrameLayout) viewGroup2;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    GhostViewApi14(android.view.View r8) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r0
            r3 = r1
            android.content.Context r3 = r3.getContext()
            r2.<init>(r3)
            r2 = r0
            android.graphics.Matrix r3 = new android.graphics.Matrix
            r6 = r3
            r3 = r6
            r4 = r6
            r4.<init>()
            r2.mMatrix = r3
            r2 = r0
            android.support.transition.GhostViewApi14$1 r3 = new android.support.transition.GhostViewApi14$1
            r6 = r3
            r3 = r6
            r4 = r6
            r5 = r0
            r4.<init>(r5)
            r2.mOnPreDrawListener = r3
            r2 = r0
            r3 = r1
            r2.mView = r3
            r2 = r0
            r3 = 2
            r4 = 0
            r2.setLayerType(r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.transition.GhostViewApi14.<init>(android.view.View):void");
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setGhostView(this.mView, this);
        int[] location = new int[2];
        int[] viewLocation = new int[2];
        getLocationOnScreen(location);
        this.mView.getLocationOnScreen(viewLocation);
        viewLocation[0] = (int) (((float) viewLocation[0]) - this.mView.getTranslationX());
        viewLocation[1] = (int) (((float) viewLocation[1]) - this.mView.getTranslationY());
        this.mDeltaX = viewLocation[0] - location[0];
        this.mDeltaY = viewLocation[1] - location[1];
        this.mView.getViewTreeObserver().addOnPreDrawListener(this.mOnPreDrawListener);
        this.mView.setVisibility(4);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        this.mView.getViewTreeObserver().removeOnPreDrawListener(this.mOnPreDrawListener);
        this.mView.setVisibility(0);
        setGhostView(this.mView, (GhostViewApi14) null);
        super.onDetachedFromWindow();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        Canvas canvas2 = canvas;
        this.mMatrix.set(this.mCurrentMatrix);
        boolean postTranslate = this.mMatrix.postTranslate((float) this.mDeltaX, (float) this.mDeltaY);
        canvas2.setMatrix(this.mMatrix);
        this.mView.draw(canvas2);
    }

    public void setVisibility(int i) {
        int visibility = i;
        super.setVisibility(visibility);
        this.mView.setVisibility(visibility == 0 ? 4 : 0);
    }

    public void reserveEndViewTransition(ViewGroup viewGroup, View view) {
        this.mStartParent = viewGroup;
        this.mStartView = view;
    }

    private static void setGhostView(@NonNull View view, GhostViewApi14 ghostView) {
        view.setTag(C0211R.C0213id.ghost_view, ghostView);
    }

    static GhostViewApi14 getGhostView(@NonNull View view) {
        return (GhostViewApi14) view.getTag(C0211R.C0213id.ghost_view);
    }
}

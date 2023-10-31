package android.support.p000v4.app;

import android.view.View;
import android.view.ViewTreeObserver;

/* renamed from: android.support.v4.app.OneShotPreDrawListener */
class OneShotPreDrawListener implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {
    private final Runnable mRunnable;
    private final View mView;
    private ViewTreeObserver mViewTreeObserver;

    private OneShotPreDrawListener(View view, Runnable runnable) {
        View view2 = view;
        this.mView = view2;
        this.mViewTreeObserver = view2.getViewTreeObserver();
        this.mRunnable = runnable;
    }

    public static OneShotPreDrawListener add(View view, Runnable runnable) {
        OneShotPreDrawListener oneShotPreDrawListener;
        View view2 = view;
        new OneShotPreDrawListener(view2, runnable);
        OneShotPreDrawListener listener = oneShotPreDrawListener;
        view2.getViewTreeObserver().addOnPreDrawListener(listener);
        view2.addOnAttachStateChangeListener(listener);
        return listener;
    }

    public boolean onPreDraw() {
        removeListener();
        this.mRunnable.run();
        return true;
    }

    public void removeListener() {
        if (this.mViewTreeObserver.isAlive()) {
            this.mViewTreeObserver.removeOnPreDrawListener(this);
        } else {
            this.mView.getViewTreeObserver().removeOnPreDrawListener(this);
        }
        this.mView.removeOnAttachStateChangeListener(this);
    }

    public void onViewAttachedToWindow(View v) {
        ViewTreeObserver viewTreeObserver = v.getViewTreeObserver();
        this.mViewTreeObserver = viewTreeObserver;
    }

    public void onViewDetachedFromWindow(View view) {
        View view2 = view;
        removeListener();
    }
}

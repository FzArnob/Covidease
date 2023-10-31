package android.support.p003v7.view;

import android.support.annotation.RestrictTo;
import android.support.p000v4.view.ViewPropertyAnimatorCompat;
import android.support.p000v4.view.ViewPropertyAnimatorListener;
import android.support.p000v4.view.ViewPropertyAnimatorListenerAdapter;
import android.view.View;
import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.Iterator;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v7.view.ViewPropertyAnimatorCompatSet */
public class ViewPropertyAnimatorCompatSet {
    final ArrayList<ViewPropertyAnimatorCompat> mAnimators;
    private long mDuration = -1;
    private Interpolator mInterpolator;
    private boolean mIsStarted;
    ViewPropertyAnimatorListener mListener;
    private final ViewPropertyAnimatorListenerAdapter mProxyListener;

    public ViewPropertyAnimatorCompatSet() {
        ViewPropertyAnimatorListenerAdapter viewPropertyAnimatorListenerAdapter;
        ArrayList<ViewPropertyAnimatorCompat> arrayList;
        new ViewPropertyAnimatorListenerAdapter(this) {
            private int mProxyEndCount = 0;
            private boolean mProxyStarted = false;
            final /* synthetic */ ViewPropertyAnimatorCompatSet this$0;

            {
                this.this$0 = this$0;
            }

            public void onAnimationStart(View view) {
                View view2 = view;
                if (!this.mProxyStarted) {
                    this.mProxyStarted = true;
                    if (this.this$0.mListener != null) {
                        this.this$0.mListener.onAnimationStart((View) null);
                    }
                }
            }

            /* access modifiers changed from: package-private */
            public void onEnd() {
                this.mProxyEndCount = 0;
                this.mProxyStarted = false;
                this.this$0.onAnimationsEnded();
            }

            public void onAnimationEnd(View view) {
                View view2 = view;
                int i = this.mProxyEndCount + 1;
                int i2 = i;
                this.mProxyEndCount = i;
                if (i2 == this.this$0.mAnimators.size()) {
                    if (this.this$0.mListener != null) {
                        this.this$0.mListener.onAnimationEnd((View) null);
                    }
                    onEnd();
                }
            }
        };
        this.mProxyListener = viewPropertyAnimatorListenerAdapter;
        new ArrayList<>();
        this.mAnimators = arrayList;
    }

    public ViewPropertyAnimatorCompatSet play(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat) {
        ViewPropertyAnimatorCompat animator = viewPropertyAnimatorCompat;
        if (!this.mIsStarted) {
            boolean add = this.mAnimators.add(animator);
        }
        return this;
    }

    public ViewPropertyAnimatorCompatSet playSequentially(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat, ViewPropertyAnimatorCompat viewPropertyAnimatorCompat2) {
        ViewPropertyAnimatorCompat anim1 = viewPropertyAnimatorCompat;
        ViewPropertyAnimatorCompat anim2 = viewPropertyAnimatorCompat2;
        boolean add = this.mAnimators.add(anim1);
        ViewPropertyAnimatorCompat startDelay = anim2.setStartDelay(anim1.getDuration());
        boolean add2 = this.mAnimators.add(anim2);
        return this;
    }

    public void start() {
        if (!this.mIsStarted) {
            Iterator<ViewPropertyAnimatorCompat> it = this.mAnimators.iterator();
            while (it.hasNext()) {
                ViewPropertyAnimatorCompat animator = it.next();
                if (this.mDuration >= 0) {
                    ViewPropertyAnimatorCompat duration = animator.setDuration(this.mDuration);
                }
                if (this.mInterpolator != null) {
                    ViewPropertyAnimatorCompat interpolator = animator.setInterpolator(this.mInterpolator);
                }
                if (this.mListener != null) {
                    ViewPropertyAnimatorCompat listener = animator.setListener(this.mProxyListener);
                }
                animator.start();
            }
            this.mIsStarted = true;
        }
    }

    /* access modifiers changed from: package-private */
    public void onAnimationsEnded() {
        this.mIsStarted = false;
    }

    public void cancel() {
        if (this.mIsStarted) {
            Iterator<ViewPropertyAnimatorCompat> it = this.mAnimators.iterator();
            while (it.hasNext()) {
                it.next().cancel();
            }
            this.mIsStarted = false;
        }
    }

    public ViewPropertyAnimatorCompatSet setDuration(long j) {
        long duration = j;
        if (!this.mIsStarted) {
            this.mDuration = duration;
        }
        return this;
    }

    public ViewPropertyAnimatorCompatSet setInterpolator(Interpolator interpolator) {
        Interpolator interpolator2 = interpolator;
        if (!this.mIsStarted) {
            this.mInterpolator = interpolator2;
        }
        return this;
    }

    public ViewPropertyAnimatorCompatSet setListener(ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        ViewPropertyAnimatorListener listener = viewPropertyAnimatorListener;
        if (!this.mIsStarted) {
            this.mListener = listener;
        }
        return this;
    }
}

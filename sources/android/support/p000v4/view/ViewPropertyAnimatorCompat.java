package android.support.p000v4.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Paint;
import android.os.Build;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;

/* renamed from: android.support.v4.view.ViewPropertyAnimatorCompat */
public final class ViewPropertyAnimatorCompat {
    static final int LISTENER_TAG_ID = 2113929216;
    private static final String TAG = "ViewAnimatorCompat";
    Runnable mEndAction = null;
    int mOldLayerType = -1;
    Runnable mStartAction = null;
    private WeakReference<View> mView;

    ViewPropertyAnimatorCompat(View view) {
        WeakReference<View> weakReference;
        new WeakReference<>(view);
        this.mView = weakReference;
    }

    /* renamed from: android.support.v4.view.ViewPropertyAnimatorCompat$ViewPropertyAnimatorListenerApi14 */
    static class ViewPropertyAnimatorListenerApi14 implements ViewPropertyAnimatorListener {
        boolean mAnimEndCalled;
        ViewPropertyAnimatorCompat mVpa;

        ViewPropertyAnimatorListenerApi14(ViewPropertyAnimatorCompat vpa) {
            this.mVpa = vpa;
        }

        public void onAnimationStart(View view) {
            View view2 = view;
            this.mAnimEndCalled = false;
            if (this.mVpa.mOldLayerType > -1) {
                view2.setLayerType(2, (Paint) null);
            }
            if (this.mVpa.mStartAction != null) {
                Runnable startAction = this.mVpa.mStartAction;
                this.mVpa.mStartAction = null;
                startAction.run();
            }
            Object listenerTag = view2.getTag(ViewPropertyAnimatorCompat.LISTENER_TAG_ID);
            ViewPropertyAnimatorListener listener = null;
            if (listenerTag instanceof ViewPropertyAnimatorListener) {
                listener = (ViewPropertyAnimatorListener) listenerTag;
            }
            if (listener != null) {
                listener.onAnimationStart(view2);
            }
        }

        public void onAnimationEnd(View view) {
            View view2 = view;
            if (this.mVpa.mOldLayerType > -1) {
                view2.setLayerType(this.mVpa.mOldLayerType, (Paint) null);
                this.mVpa.mOldLayerType = -1;
            }
            if (Build.VERSION.SDK_INT >= 16 || !this.mAnimEndCalled) {
                if (this.mVpa.mEndAction != null) {
                    Runnable endAction = this.mVpa.mEndAction;
                    this.mVpa.mEndAction = null;
                    endAction.run();
                }
                Object listenerTag = view2.getTag(ViewPropertyAnimatorCompat.LISTENER_TAG_ID);
                ViewPropertyAnimatorListener listener = null;
                if (listenerTag instanceof ViewPropertyAnimatorListener) {
                    listener = (ViewPropertyAnimatorListener) listenerTag;
                }
                if (listener != null) {
                    listener.onAnimationEnd(view2);
                }
                this.mAnimEndCalled = true;
            }
        }

        public void onAnimationCancel(View view) {
            View view2 = view;
            Object listenerTag = view2.getTag(ViewPropertyAnimatorCompat.LISTENER_TAG_ID);
            ViewPropertyAnimatorListener listener = null;
            if (listenerTag instanceof ViewPropertyAnimatorListener) {
                listener = (ViewPropertyAnimatorListener) listenerTag;
            }
            if (listener != null) {
                listener.onAnimationCancel(view2);
            }
        }
    }

    public ViewPropertyAnimatorCompat setDuration(long j) {
        long value = j;
        View view = (View) this.mView.get();
        View view2 = view;
        if (view != null) {
            ViewPropertyAnimator duration = view2.animate().setDuration(value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat alpha(float f) {
        float value = f;
        View view = (View) this.mView.get();
        View view2 = view;
        if (view != null) {
            ViewPropertyAnimator alpha = view2.animate().alpha(value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat alphaBy(float f) {
        float value = f;
        View view = (View) this.mView.get();
        View view2 = view;
        if (view != null) {
            ViewPropertyAnimator alphaBy = view2.animate().alphaBy(value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat translationX(float f) {
        float value = f;
        View view = (View) this.mView.get();
        View view2 = view;
        if (view != null) {
            ViewPropertyAnimator translationX = view2.animate().translationX(value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat translationY(float f) {
        float value = f;
        View view = (View) this.mView.get();
        View view2 = view;
        if (view != null) {
            ViewPropertyAnimator translationY = view2.animate().translationY(value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat withEndAction(Runnable runnable) {
        ViewPropertyAnimatorListener viewPropertyAnimatorListener;
        Runnable runnable2 = runnable;
        View view = (View) this.mView.get();
        View view2 = view;
        if (view != null) {
            if (Build.VERSION.SDK_INT >= 16) {
                ViewPropertyAnimator withEndAction = view2.animate().withEndAction(runnable2);
            } else {
                new ViewPropertyAnimatorListenerApi14(this);
                setListenerInternal(view2, viewPropertyAnimatorListener);
                this.mEndAction = runnable2;
            }
        }
        return this;
    }

    public long getDuration() {
        View view = (View) this.mView.get();
        View view2 = view;
        if (view != null) {
            return view2.animate().getDuration();
        }
        return 0;
    }

    public ViewPropertyAnimatorCompat setInterpolator(Interpolator interpolator) {
        Interpolator value = interpolator;
        View view = (View) this.mView.get();
        View view2 = view;
        if (view != null) {
            ViewPropertyAnimator interpolator2 = view2.animate().setInterpolator(value);
        }
        return this;
    }

    public Interpolator getInterpolator() {
        View view = (View) this.mView.get();
        View view2 = view;
        if (view == null || Build.VERSION.SDK_INT < 18) {
            return null;
        }
        return (Interpolator) view2.animate().getInterpolator();
    }

    public ViewPropertyAnimatorCompat setStartDelay(long j) {
        long value = j;
        View view = (View) this.mView.get();
        View view2 = view;
        if (view != null) {
            ViewPropertyAnimator startDelay = view2.animate().setStartDelay(value);
        }
        return this;
    }

    public long getStartDelay() {
        View view = (View) this.mView.get();
        View view2 = view;
        if (view != null) {
            return view2.animate().getStartDelay();
        }
        return 0;
    }

    public ViewPropertyAnimatorCompat rotation(float f) {
        float value = f;
        View view = (View) this.mView.get();
        View view2 = view;
        if (view != null) {
            ViewPropertyAnimator rotation = view2.animate().rotation(value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat rotationBy(float f) {
        float value = f;
        View view = (View) this.mView.get();
        View view2 = view;
        if (view != null) {
            ViewPropertyAnimator rotationBy = view2.animate().rotationBy(value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat rotationX(float f) {
        float value = f;
        View view = (View) this.mView.get();
        View view2 = view;
        if (view != null) {
            ViewPropertyAnimator rotationX = view2.animate().rotationX(value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat rotationXBy(float f) {
        float value = f;
        View view = (View) this.mView.get();
        View view2 = view;
        if (view != null) {
            ViewPropertyAnimator rotationXBy = view2.animate().rotationXBy(value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat rotationY(float f) {
        float value = f;
        View view = (View) this.mView.get();
        View view2 = view;
        if (view != null) {
            ViewPropertyAnimator rotationY = view2.animate().rotationY(value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat rotationYBy(float f) {
        float value = f;
        View view = (View) this.mView.get();
        View view2 = view;
        if (view != null) {
            ViewPropertyAnimator rotationYBy = view2.animate().rotationYBy(value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat scaleX(float f) {
        float value = f;
        View view = (View) this.mView.get();
        View view2 = view;
        if (view != null) {
            ViewPropertyAnimator scaleX = view2.animate().scaleX(value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat scaleXBy(float f) {
        float value = f;
        View view = (View) this.mView.get();
        View view2 = view;
        if (view != null) {
            ViewPropertyAnimator scaleXBy = view2.animate().scaleXBy(value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat scaleY(float f) {
        float value = f;
        View view = (View) this.mView.get();
        View view2 = view;
        if (view != null) {
            ViewPropertyAnimator scaleY = view2.animate().scaleY(value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat scaleYBy(float f) {
        float value = f;
        View view = (View) this.mView.get();
        View view2 = view;
        if (view != null) {
            ViewPropertyAnimator scaleYBy = view2.animate().scaleYBy(value);
        }
        return this;
    }

    public void cancel() {
        View view = (View) this.mView.get();
        View view2 = view;
        if (view != null) {
            view2.animate().cancel();
        }
    }

    /* renamed from: x */
    public ViewPropertyAnimatorCompat mo5684x(float f) {
        float value = f;
        View view = (View) this.mView.get();
        View view2 = view;
        if (view != null) {
            ViewPropertyAnimator x = view2.animate().x(value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat xBy(float f) {
        float value = f;
        View view = (View) this.mView.get();
        View view2 = view;
        if (view != null) {
            ViewPropertyAnimator xBy = view2.animate().xBy(value);
        }
        return this;
    }

    /* renamed from: y */
    public ViewPropertyAnimatorCompat mo5686y(float f) {
        float value = f;
        View view = (View) this.mView.get();
        View view2 = view;
        if (view != null) {
            ViewPropertyAnimator y = view2.animate().y(value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat yBy(float f) {
        float value = f;
        View view = (View) this.mView.get();
        View view2 = view;
        if (view != null) {
            ViewPropertyAnimator yBy = view2.animate().yBy(value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat translationXBy(float f) {
        float value = f;
        View view = (View) this.mView.get();
        View view2 = view;
        if (view != null) {
            ViewPropertyAnimator translationXBy = view2.animate().translationXBy(value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat translationYBy(float f) {
        float value = f;
        View view = (View) this.mView.get();
        View view2 = view;
        if (view != null) {
            ViewPropertyAnimator translationYBy = view2.animate().translationYBy(value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat translationZBy(float f) {
        float value = f;
        View view = (View) this.mView.get();
        View view2 = view;
        if (view != null && Build.VERSION.SDK_INT >= 21) {
            ViewPropertyAnimator translationZBy = view2.animate().translationZBy(value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat translationZ(float f) {
        float value = f;
        View view = (View) this.mView.get();
        View view2 = view;
        if (view != null && Build.VERSION.SDK_INT >= 21) {
            ViewPropertyAnimator translationZ = view2.animate().translationZ(value);
        }
        return this;
    }

    /* renamed from: z */
    public ViewPropertyAnimatorCompat mo5688z(float f) {
        float value = f;
        View view = (View) this.mView.get();
        View view2 = view;
        if (view != null && Build.VERSION.SDK_INT >= 21) {
            ViewPropertyAnimator z = view2.animate().z(value);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat zBy(float f) {
        float value = f;
        View view = (View) this.mView.get();
        View view2 = view;
        if (view != null && Build.VERSION.SDK_INT >= 21) {
            ViewPropertyAnimator zBy = view2.animate().zBy(value);
        }
        return this;
    }

    public void start() {
        View view = (View) this.mView.get();
        View view2 = view;
        if (view != null) {
            view2.animate().start();
        }
    }

    public ViewPropertyAnimatorCompat withLayer() {
        ViewPropertyAnimatorListener viewPropertyAnimatorListener;
        View view = (View) this.mView.get();
        View view2 = view;
        if (view != null) {
            if (Build.VERSION.SDK_INT >= 16) {
                ViewPropertyAnimator withLayer = view2.animate().withLayer();
            } else {
                this.mOldLayerType = view2.getLayerType();
                new ViewPropertyAnimatorListenerApi14(this);
                setListenerInternal(view2, viewPropertyAnimatorListener);
            }
        }
        return this;
    }

    public ViewPropertyAnimatorCompat withStartAction(Runnable runnable) {
        ViewPropertyAnimatorListener viewPropertyAnimatorListener;
        Runnable runnable2 = runnable;
        View view = (View) this.mView.get();
        View view2 = view;
        if (view != null) {
            if (Build.VERSION.SDK_INT >= 16) {
                ViewPropertyAnimator withStartAction = view2.animate().withStartAction(runnable2);
            } else {
                new ViewPropertyAnimatorListenerApi14(this);
                setListenerInternal(view2, viewPropertyAnimatorListener);
                this.mStartAction = runnable2;
            }
        }
        return this;
    }

    public ViewPropertyAnimatorCompat setListener(ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        ViewPropertyAnimatorListener viewPropertyAnimatorListener2;
        ViewPropertyAnimatorListener listener = viewPropertyAnimatorListener;
        View view = (View) this.mView.get();
        View view2 = view;
        if (view != null) {
            if (Build.VERSION.SDK_INT >= 16) {
                setListenerInternal(view2, listener);
            } else {
                view2.setTag(LISTENER_TAG_ID, listener);
                new ViewPropertyAnimatorListenerApi14(this);
                setListenerInternal(view2, viewPropertyAnimatorListener2);
            }
        }
        return this;
    }

    private void setListenerInternal(View view, ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        Animator.AnimatorListener animatorListener;
        View view2 = view;
        ViewPropertyAnimatorListener listener = viewPropertyAnimatorListener;
        if (listener != null) {
            final ViewPropertyAnimatorListener viewPropertyAnimatorListener2 = listener;
            final View view3 = view2;
            new AnimatorListenerAdapter(this) {
                final /* synthetic */ ViewPropertyAnimatorCompat this$0;

                {
                    this.this$0 = this$0;
                }

                public void onAnimationCancel(Animator animator) {
                    Animator animator2 = animator;
                    viewPropertyAnimatorListener2.onAnimationCancel(view3);
                }

                public void onAnimationEnd(Animator animator) {
                    Animator animator2 = animator;
                    viewPropertyAnimatorListener2.onAnimationEnd(view3);
                }

                public void onAnimationStart(Animator animator) {
                    Animator animator2 = animator;
                    viewPropertyAnimatorListener2.onAnimationStart(view3);
                }
            };
            ViewPropertyAnimator listener2 = view2.animate().setListener(animatorListener);
            return;
        }
        ViewPropertyAnimator listener3 = view2.animate().setListener((Animator.AnimatorListener) null);
    }

    public ViewPropertyAnimatorCompat setUpdateListener(ViewPropertyAnimatorUpdateListener viewPropertyAnimatorUpdateListener) {
        ValueAnimator.AnimatorUpdateListener animatorUpdateListener;
        ViewPropertyAnimatorUpdateListener listener = viewPropertyAnimatorUpdateListener;
        View view = (View) this.mView.get();
        View view2 = view;
        if (view != null && Build.VERSION.SDK_INT >= 19) {
            ValueAnimator.AnimatorUpdateListener wrapped = null;
            if (listener != null) {
                final ViewPropertyAnimatorUpdateListener viewPropertyAnimatorUpdateListener2 = listener;
                final View view3 = view2;
                new ValueAnimator.AnimatorUpdateListener(this) {
                    final /* synthetic */ ViewPropertyAnimatorCompat this$0;

                    {
                        this.this$0 = this$0;
                    }

                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        ValueAnimator valueAnimator2 = valueAnimator;
                        viewPropertyAnimatorUpdateListener2.onAnimationUpdate(view3);
                    }
                };
                wrapped = animatorUpdateListener;
            }
            ViewPropertyAnimator updateListener = view2.animate().setUpdateListener(wrapped);
        }
        return this;
    }
}

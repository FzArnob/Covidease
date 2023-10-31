package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.p000v4.view.ViewCompat;
import android.support.transition.Transition;
import android.view.View;
import android.view.ViewGroup;

public class Fade extends Visibility {

    /* renamed from: IN */
    public static final int f23IN = 1;
    private static final String LOG_TAG = "Fade";
    public static final int OUT = 2;
    private static final String PROPNAME_TRANSITION_ALPHA = "android:fade:transitionAlpha";

    public Fade(int fadingMode) {
        setMode(fadingMode);
    }

    public Fade() {
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Fade(android.content.Context r11, android.util.AttributeSet r12) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r2 = r12
            r5 = r0
            r6 = r1
            r7 = r2
            r5.<init>(r6, r7)
            r5 = r1
            r6 = r2
            int[] r7 = android.support.transition.Styleable.FADE
            android.content.res.TypedArray r5 = r5.obtainStyledAttributes(r6, r7)
            r3 = r5
            r5 = r3
            r6 = r2
            android.content.res.XmlResourceParser r6 = (android.content.res.XmlResourceParser) r6
            java.lang.String r7 = "fadingMode"
            r8 = 0
            r9 = r0
            int r9 = r9.getMode()
            int r5 = android.support.p000v4.content.res.TypedArrayUtils.getNamedInt(r5, r6, r7, r8, r9)
            r4 = r5
            r5 = r0
            r6 = r4
            r5.setMode(r6)
            r5 = r3
            r5.recycle()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.transition.Fade.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        TransitionValues transitionValues2 = transitionValues;
        super.captureStartValues(transitionValues2);
        Object put = transitionValues2.values.put(PROPNAME_TRANSITION_ALPHA, Float.valueOf(ViewUtils.getTransitionAlpha(transitionValues2.view)));
    }

    private Animator createAnimation(View view, float f, float f2) {
        Animator.AnimatorListener animatorListener;
        Transition.TransitionListener transitionListener;
        View view2 = view;
        float startAlpha = f;
        float endAlpha = f2;
        if (startAlpha == endAlpha) {
            return null;
        }
        ViewUtils.setTransitionAlpha(view2, startAlpha);
        ObjectAnimator anim = ObjectAnimator.ofFloat(view2, ViewUtils.TRANSITION_ALPHA, new float[]{endAlpha});
        new FadeAnimatorListener(view2);
        anim.addListener(animatorListener);
        final View view3 = view2;
        new TransitionListenerAdapter(this) {
            final /* synthetic */ Fade this$0;

            {
                this.this$0 = this$0;
            }

            public void onTransitionEnd(@NonNull Transition transition) {
                ViewUtils.setTransitionAlpha(view3, 1.0f);
                ViewUtils.clearNonTransitionAlpha(view3);
                Transition removeListener = transition.removeListener(this);
            }
        };
        Transition addListener = addListener(transitionListener);
        return anim;
    }

    public Animator onAppear(ViewGroup viewGroup, View view, TransitionValues startValues, TransitionValues transitionValues) {
        ViewGroup viewGroup2 = viewGroup;
        View view2 = view;
        TransitionValues transitionValues2 = transitionValues;
        float startAlpha = getStartAlpha(startValues, 0.0f);
        if (startAlpha == 1.0f) {
            startAlpha = 0.0f;
        }
        return createAnimation(view2, startAlpha, 1.0f);
    }

    public Animator onDisappear(ViewGroup viewGroup, View view, TransitionValues startValues, TransitionValues transitionValues) {
        ViewGroup viewGroup2 = viewGroup;
        View view2 = view;
        TransitionValues transitionValues2 = transitionValues;
        ViewUtils.saveNonTransitionAlpha(view2);
        return createAnimation(view2, getStartAlpha(startValues, 1.0f), 0.0f);
    }

    private static float getStartAlpha(TransitionValues transitionValues, float fallbackValue) {
        Float startAlphaFloat;
        TransitionValues startValues = transitionValues;
        float startAlpha = fallbackValue;
        if (!(startValues == null || (startAlphaFloat = (Float) startValues.values.get(PROPNAME_TRANSITION_ALPHA)) == null)) {
            startAlpha = startAlphaFloat.floatValue();
        }
        return startAlpha;
    }

    private static class FadeAnimatorListener extends AnimatorListenerAdapter {
        private boolean mLayerTypeChanged = false;
        private final View mView;

        FadeAnimatorListener(View view) {
            this.mView = view;
        }

        public void onAnimationStart(Animator animator) {
            Animator animator2 = animator;
            if (ViewCompat.hasOverlappingRendering(this.mView) && this.mView.getLayerType() == 0) {
                this.mLayerTypeChanged = true;
                this.mView.setLayerType(2, (Paint) null);
            }
        }

        public void onAnimationEnd(Animator animator) {
            Animator animator2 = animator;
            ViewUtils.setTransitionAlpha(this.mView, 1.0f);
            if (this.mLayerTypeChanged) {
                this.mView.setLayerType(0, (Paint) null);
            }
        }
    }
}

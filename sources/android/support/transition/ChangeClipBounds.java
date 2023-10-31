package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.p000v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class ChangeClipBounds extends Transition {
    private static final String PROPNAME_BOUNDS = "android:clipBounds:bounds";
    private static final String PROPNAME_CLIP = "android:clipBounds:clip";
    private static final String[] sTransitionProperties = {PROPNAME_CLIP};

    public String[] getTransitionProperties() {
        return sTransitionProperties;
    }

    public ChangeClipBounds() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ChangeClipBounds(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void captureValues(TransitionValues transitionValues) {
        Object obj;
        TransitionValues values = transitionValues;
        View view = values.view;
        if (view.getVisibility() != 8) {
            Rect clip = ViewCompat.getClipBounds(view);
            Object put = values.values.put(PROPNAME_CLIP, clip);
            if (clip == null) {
                new Rect(0, 0, view.getWidth(), view.getHeight());
                Object put2 = values.values.put(PROPNAME_BOUNDS, obj);
            }
        }
    }

    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    public Animator createAnimator(@NonNull ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        TypeEvaluator typeEvaluator;
        Rect rect;
        Animator.AnimatorListener animatorListener;
        ViewGroup viewGroup2 = viewGroup;
        TransitionValues startValues = transitionValues;
        TransitionValues endValues = transitionValues2;
        if (startValues == null || endValues == null || !startValues.values.containsKey(PROPNAME_CLIP) || !endValues.values.containsKey(PROPNAME_CLIP)) {
            return null;
        }
        Rect start = (Rect) startValues.values.get(PROPNAME_CLIP);
        Rect end = (Rect) endValues.values.get(PROPNAME_CLIP);
        boolean endIsNull = end == null;
        if (start == null && end == null) {
            return null;
        }
        if (start == null) {
            start = (Rect) startValues.values.get(PROPNAME_BOUNDS);
        } else if (end == null) {
            end = (Rect) endValues.values.get(PROPNAME_BOUNDS);
        }
        if (start.equals(end)) {
            return null;
        }
        ViewCompat.setClipBounds(endValues.view, start);
        new Rect();
        new RectEvaluator(rect);
        TypeEvaluator typeEvaluator2 = typeEvaluator;
        Rect[] rectArr = new Rect[2];
        rectArr[0] = start;
        Rect[] rectArr2 = rectArr;
        rectArr2[1] = end;
        ObjectAnimator animator = ObjectAnimator.ofObject(endValues.view, ViewUtils.CLIP_BOUNDS, typeEvaluator2, rectArr2);
        if (endIsNull) {
            final View view = endValues.view;
            new AnimatorListenerAdapter(this) {
                final /* synthetic */ ChangeClipBounds this$0;

                {
                    this.this$0 = this$0;
                }

                public void onAnimationEnd(Animator animator) {
                    Animator animator2 = animator;
                    ViewCompat.setClipBounds(view, (Rect) null);
                }
            };
            animator.addListener(animatorListener);
        }
        return animator;
    }
}

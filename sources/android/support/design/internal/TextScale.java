package android.support.design.internal;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.support.annotation.RestrictTo;
import android.support.transition.Transition;
import android.support.transition.TransitionValues;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.Map;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class TextScale extends Transition {
    private static final String PROPNAME_SCALE = "android:textscale:scale";

    public TextScale() {
    }

    public void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    public void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    private void captureValues(TransitionValues transitionValues) {
        TransitionValues transitionValues2 = transitionValues;
        if (transitionValues2.view instanceof TextView) {
            Object put = transitionValues2.values.put(PROPNAME_SCALE, Float.valueOf(((TextView) transitionValues2.view).getScaleX()));
        }
    }

    public Animator createAnimator(ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        ValueAnimator.AnimatorUpdateListener animatorUpdateListener;
        ViewGroup viewGroup2 = viewGroup;
        TransitionValues startValues = transitionValues;
        TransitionValues endValues = transitionValues2;
        if (startValues == null || endValues == null || !(startValues.view instanceof TextView) || !(endValues.view instanceof TextView)) {
            return null;
        }
        TextView view = (TextView) endValues.view;
        Map<String, Object> startVals = startValues.values;
        Map<String, Object> endVals = endValues.values;
        float startSize = startVals.get(PROPNAME_SCALE) != null ? ((Float) startVals.get(PROPNAME_SCALE)).floatValue() : 1.0f;
        float endSize = endVals.get(PROPNAME_SCALE) != null ? ((Float) endVals.get(PROPNAME_SCALE)).floatValue() : 1.0f;
        if (startSize == endSize) {
            return null;
        }
        float[] fArr = new float[2];
        fArr[0] = startSize;
        float[] fArr2 = fArr;
        fArr2[1] = endSize;
        ValueAnimator animator = ValueAnimator.ofFloat(fArr2);
        final TextView textView = view;
        new ValueAnimator.AnimatorUpdateListener(this) {
            final /* synthetic */ TextScale this$0;

            {
                this.this$0 = this$0;
            }

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                textView.setScaleX(animatedValue);
                textView.setScaleY(animatedValue);
            }
        };
        animator.addUpdateListener(animatorUpdateListener);
        return animator;
    }
}

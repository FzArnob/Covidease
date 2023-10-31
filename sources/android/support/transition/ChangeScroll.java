package android.support.transition;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class ChangeScroll extends Transition {
    private static final String[] PROPERTIES;
    private static final String PROPNAME_SCROLL_X = "android:changeScroll:x";
    private static final String PROPNAME_SCROLL_Y = "android:changeScroll:y";

    static {
        String[] strArr = new String[2];
        strArr[0] = PROPNAME_SCROLL_X;
        String[] strArr2 = strArr;
        strArr2[1] = PROPNAME_SCROLL_Y;
        PROPERTIES = strArr2;
    }

    public ChangeScroll() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ChangeScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Nullable
    public String[] getTransitionProperties() {
        return PROPERTIES;
    }

    private void captureValues(TransitionValues transitionValues) {
        TransitionValues transitionValues2 = transitionValues;
        Object put = transitionValues2.values.put(PROPNAME_SCROLL_X, Integer.valueOf(transitionValues2.view.getScrollX()));
        Object put2 = transitionValues2.values.put(PROPNAME_SCROLL_Y, Integer.valueOf(transitionValues2.view.getScrollY()));
    }

    @Nullable
    public Animator createAnimator(@NonNull ViewGroup viewGroup, @Nullable TransitionValues transitionValues, @Nullable TransitionValues transitionValues2) {
        ViewGroup viewGroup2 = viewGroup;
        TransitionValues startValues = transitionValues;
        TransitionValues endValues = transitionValues2;
        if (startValues == null || endValues == null) {
            return null;
        }
        View view = endValues.view;
        int startX = ((Integer) startValues.values.get(PROPNAME_SCROLL_X)).intValue();
        int endX = ((Integer) endValues.values.get(PROPNAME_SCROLL_X)).intValue();
        int startY = ((Integer) startValues.values.get(PROPNAME_SCROLL_Y)).intValue();
        int endY = ((Integer) endValues.values.get(PROPNAME_SCROLL_Y)).intValue();
        Animator scrollXAnimator = null;
        Animator scrollYAnimator = null;
        if (startX != endX) {
            view.setScrollX(startX);
            int[] iArr = new int[2];
            iArr[0] = startX;
            int[] iArr2 = iArr;
            iArr2[1] = endX;
            scrollXAnimator = ObjectAnimator.ofInt(view, "scrollX", iArr2);
        }
        if (startY != endY) {
            view.setScrollY(startY);
            int[] iArr3 = new int[2];
            iArr3[0] = startY;
            int[] iArr4 = iArr3;
            iArr4[1] = endY;
            scrollYAnimator = ObjectAnimator.ofInt(view, "scrollY", iArr4);
        }
        return TransitionUtils.mergeAnimators(scrollXAnimator, scrollYAnimator);
    }
}

package android.support.transition;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

public class Explode extends Visibility {
    private static final String PROPNAME_SCREEN_BOUNDS = "android:explode:screenBounds";
    private static final TimeInterpolator sAccelerate;
    private static final TimeInterpolator sDecelerate;
    private int[] mTempLoc = new int[2];

    static {
        TimeInterpolator timeInterpolator;
        TimeInterpolator timeInterpolator2;
        new DecelerateInterpolator();
        sDecelerate = timeInterpolator;
        new AccelerateInterpolator();
        sAccelerate = timeInterpolator2;
    }

    public Explode() {
        TransitionPropagation transitionPropagation;
        new CircularPropagation();
        setPropagation(transitionPropagation);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Explode(Context context, AttributeSet attrs) {
        super(context, attrs);
        TransitionPropagation transitionPropagation;
        new CircularPropagation();
        setPropagation(transitionPropagation);
    }

    private void captureValues(TransitionValues transitionValues) {
        Object obj;
        TransitionValues transitionValues2 = transitionValues;
        View view = transitionValues2.view;
        view.getLocationOnScreen(this.mTempLoc);
        int left = this.mTempLoc[0];
        int top = this.mTempLoc[1];
        new Rect(left, top, left + view.getWidth(), top + view.getHeight());
        Object put = transitionValues2.values.put(PROPNAME_SCREEN_BOUNDS, obj);
    }

    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        TransitionValues transitionValues2 = transitionValues;
        super.captureStartValues(transitionValues2);
        captureValues(transitionValues2);
    }

    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        TransitionValues transitionValues2 = transitionValues;
        super.captureEndValues(transitionValues2);
        captureValues(transitionValues2);
    }

    public Animator onAppear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        ViewGroup sceneRoot = viewGroup;
        View view2 = view;
        TransitionValues transitionValues3 = transitionValues;
        TransitionValues endValues = transitionValues2;
        if (endValues == null) {
            return null;
        }
        Rect bounds = (Rect) endValues.values.get(PROPNAME_SCREEN_BOUNDS);
        float endX = view2.getTranslationX();
        float endY = view2.getTranslationY();
        calculateOut(sceneRoot, bounds, this.mTempLoc);
        return TranslationAnimationCreator.createAnimation(view2, endValues, bounds.left, bounds.top, endX + ((float) this.mTempLoc[0]), endY + ((float) this.mTempLoc[1]), endX, endY, sDecelerate);
    }

    public Animator onDisappear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        ViewGroup sceneRoot = viewGroup;
        View view2 = view;
        TransitionValues startValues = transitionValues;
        TransitionValues transitionValues3 = transitionValues2;
        if (startValues == null) {
            return null;
        }
        Rect bounds = (Rect) startValues.values.get(PROPNAME_SCREEN_BOUNDS);
        int viewPosX = bounds.left;
        int viewPosY = bounds.top;
        float startX = view2.getTranslationX();
        float startY = view2.getTranslationY();
        float endX = startX;
        float endY = startY;
        int[] interruptedPosition = (int[]) startValues.view.getTag(C0211R.C0213id.transition_position);
        if (interruptedPosition != null) {
            endX += (float) (interruptedPosition[0] - bounds.left);
            endY += (float) (interruptedPosition[1] - bounds.top);
            bounds.offsetTo(interruptedPosition[0], interruptedPosition[1]);
        }
        calculateOut(sceneRoot, bounds, this.mTempLoc);
        return TranslationAnimationCreator.createAnimation(view2, startValues, viewPosX, viewPosY, startX, startY, endX + ((float) this.mTempLoc[0]), endY + ((float) this.mTempLoc[1]), sAccelerate);
    }

    private void calculateOut(View view, Rect rect, int[] iArr) {
        int focalX;
        int focalY;
        View sceneRoot = view;
        Rect bounds = rect;
        int[] outVector = iArr;
        sceneRoot.getLocationOnScreen(this.mTempLoc);
        int sceneRootX = this.mTempLoc[0];
        int sceneRootY = this.mTempLoc[1];
        Rect epicenter = getEpicenter();
        if (epicenter == null) {
            focalX = sceneRootX + (sceneRoot.getWidth() / 2) + Math.round(sceneRoot.getTranslationX());
            focalY = sceneRootY + (sceneRoot.getHeight() / 2) + Math.round(sceneRoot.getTranslationY());
        } else {
            focalX = epicenter.centerX();
            focalY = epicenter.centerY();
        }
        int centerX = bounds.centerX();
        float xVector = (float) (centerX - focalX);
        float yVector = (float) (bounds.centerY() - focalY);
        if (xVector == 0.0f && yVector == 0.0f) {
            xVector = ((float) (Math.random() * 2.0d)) - 1.0f;
            yVector = ((float) (Math.random() * 2.0d)) - 1.0f;
        }
        float vectorSize = calculateDistance(xVector, yVector);
        float maxDistance = calculateMaxDistance(sceneRoot, focalX - sceneRootX, focalY - sceneRootY);
        outVector[0] = Math.round(maxDistance * (xVector / vectorSize));
        outVector[1] = Math.round(maxDistance * (yVector / vectorSize));
    }

    private static float calculateMaxDistance(View view, int i, int i2) {
        View sceneRoot = view;
        int focalX = i;
        int focalY = i2;
        return calculateDistance((float) Math.max(focalX, sceneRoot.getWidth() - focalX), (float) Math.max(focalY, sceneRoot.getHeight() - focalY));
    }

    private static float calculateDistance(float f, float f2) {
        float x = f;
        float y = f2;
        return (float) Math.sqrt((double) ((x * x) + (y * y)));
    }
}

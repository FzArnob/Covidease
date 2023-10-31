package android.support.transition;

import android.graphics.Rect;
import android.view.ViewGroup;

public class CircularPropagation extends VisibilityPropagation {
    private float mPropagationSpeed = 3.0f;

    public CircularPropagation() {
    }

    public void setPropagationSpeed(float f) {
        Throwable th;
        float propagationSpeed = f;
        if (propagationSpeed == 0.0f) {
            Throwable th2 = th;
            new IllegalArgumentException("propagationSpeed may not be 0");
            throw th2;
        }
        this.mPropagationSpeed = propagationSpeed;
    }

    public long getStartDelay(ViewGroup viewGroup, Transition transition, TransitionValues transitionValues, TransitionValues transitionValues2) {
        TransitionValues positionValues;
        int epicenterX;
        int epicenterY;
        ViewGroup sceneRoot = viewGroup;
        Transition transition2 = transition;
        TransitionValues startValues = transitionValues;
        TransitionValues endValues = transitionValues2;
        if (startValues == null && endValues == null) {
            return 0;
        }
        int directionMultiplier = 1;
        if (endValues == null || getViewVisibility(startValues) == 0) {
            positionValues = startValues;
            directionMultiplier = -1;
        } else {
            positionValues = endValues;
        }
        int viewCenterX = getViewX(positionValues);
        int viewCenterY = getViewY(positionValues);
        Rect epicenter = transition2.getEpicenter();
        if (epicenter != null) {
            epicenterX = epicenter.centerX();
            epicenterY = epicenter.centerY();
        } else {
            int[] loc = new int[2];
            sceneRoot.getLocationOnScreen(loc);
            epicenterX = Math.round(((float) (loc[0] + (sceneRoot.getWidth() / 2))) + sceneRoot.getTranslationX());
            epicenterY = Math.round(((float) (loc[1] + (sceneRoot.getHeight() / 2))) + sceneRoot.getTranslationY());
        }
        float distanceFraction = distance((float) viewCenterX, (float) viewCenterY, (float) epicenterX, (float) epicenterY) / distance(0.0f, 0.0f, (float) sceneRoot.getWidth(), (float) sceneRoot.getHeight());
        long duration = transition2.getDuration();
        if (duration < 0) {
            duration = 300;
        }
        return (long) Math.round((((float) (duration * ((long) directionMultiplier))) / this.mPropagationSpeed) * distanceFraction);
    }

    private static float distance(float x1, float y1, float x2, float y2) {
        float x = x2 - x1;
        float y = y2 - y1;
        return (float) Math.sqrt((double) ((x * x) + (y * y)));
    }
}

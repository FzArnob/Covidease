package android.support.transition;

import android.graphics.Rect;
import android.support.p000v4.view.GravityCompat;
import android.support.p000v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;

public class SidePropagation extends VisibilityPropagation {
    private float mPropagationSpeed = 3.0f;
    private int mSide = 80;

    public SidePropagation() {
    }

    public void setSide(int side) {
        int i = side;
        this.mSide = i;
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
        Rect epicenter = transition2.getEpicenter();
        if (endValues == null || getViewVisibility(startValues) == 0) {
            positionValues = startValues;
            directionMultiplier = -1;
        } else {
            positionValues = endValues;
        }
        int viewCenterX = getViewX(positionValues);
        int viewCenterY = getViewY(positionValues);
        int[] loc = new int[2];
        sceneRoot.getLocationOnScreen(loc);
        int left = loc[0] + Math.round(sceneRoot.getTranslationX());
        int top = loc[1] + Math.round(sceneRoot.getTranslationY());
        int right = left + sceneRoot.getWidth();
        int bottom = top + sceneRoot.getHeight();
        if (epicenter != null) {
            epicenterX = epicenter.centerX();
            epicenterY = epicenter.centerY();
        } else {
            epicenterX = (left + right) / 2;
            epicenterY = (top + bottom) / 2;
        }
        float distanceFraction = ((float) distance(sceneRoot, viewCenterX, viewCenterY, epicenterX, epicenterY, left, top, right, bottom)) / ((float) getMaxDistance(sceneRoot));
        long duration = transition2.getDuration();
        if (duration < 0) {
            duration = 300;
        }
        return (long) Math.round((((float) (duration * ((long) directionMultiplier))) / this.mPropagationSpeed) * distanceFraction);
    }

    private int distance(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        int side;
        View sceneRoot = view;
        int viewX = i;
        int viewY = i2;
        int epicenterX = i3;
        int epicenterY = i4;
        int left = i5;
        int top = i6;
        int right = i7;
        int bottom = i8;
        if (this.mSide == 8388611) {
            side = ViewCompat.getLayoutDirection(sceneRoot) == 1 ? 5 : 3;
        } else if (this.mSide == 8388613) {
            side = ViewCompat.getLayoutDirection(sceneRoot) == 1 ? 3 : 5;
        } else {
            side = this.mSide;
        }
        int distance = 0;
        switch (side) {
            case 3:
                distance = (right - viewX) + Math.abs(epicenterY - viewY);
                break;
            case 5:
                distance = (viewX - left) + Math.abs(epicenterY - viewY);
                break;
            case 48:
                distance = (bottom - viewY) + Math.abs(epicenterX - viewX);
                break;
            case 80:
                distance = (viewY - top) + Math.abs(epicenterX - viewX);
                break;
        }
        return distance;
    }

    private int getMaxDistance(ViewGroup viewGroup) {
        ViewGroup sceneRoot = viewGroup;
        switch (this.mSide) {
            case 3:
            case 5:
            case GravityCompat.START /*8388611*/:
            case GravityCompat.END /*8388613*/:
                return sceneRoot.getWidth();
            default:
                return sceneRoot.getHeight();
        }
    }
}

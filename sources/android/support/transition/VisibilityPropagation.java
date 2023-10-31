package android.support.transition;

import android.view.View;

public abstract class VisibilityPropagation extends TransitionPropagation {
    private static final String PROPNAME_VIEW_CENTER = "android:visibilityPropagation:center";
    private static final String PROPNAME_VISIBILITY = "android:visibilityPropagation:visibility";
    private static final String[] VISIBILITY_PROPAGATION_VALUES;

    public VisibilityPropagation() {
    }

    static {
        String[] strArr = new String[2];
        strArr[0] = PROPNAME_VISIBILITY;
        String[] strArr2 = strArr;
        strArr2[1] = PROPNAME_VIEW_CENTER;
        VISIBILITY_PROPAGATION_VALUES = strArr2;
    }

    public void captureValues(TransitionValues transitionValues) {
        TransitionValues values = transitionValues;
        View view = values.view;
        Integer visibility = (Integer) values.values.get("android:visibility:visibility");
        if (visibility == null) {
            visibility = Integer.valueOf(view.getVisibility());
        }
        Object put = values.values.put(PROPNAME_VISIBILITY, visibility);
        int[] loc = new int[2];
        view.getLocationOnScreen(loc);
        int[] iArr = loc;
        iArr[0] = iArr[0] + Math.round(view.getTranslationX());
        int[] iArr2 = loc;
        iArr2[0] = iArr2[0] + (view.getWidth() / 2);
        int[] iArr3 = loc;
        iArr3[1] = iArr3[1] + Math.round(view.getTranslationY());
        int[] iArr4 = loc;
        iArr4[1] = iArr4[1] + (view.getHeight() / 2);
        Object put2 = values.values.put(PROPNAME_VIEW_CENTER, loc);
    }

    public String[] getPropagationProperties() {
        return VISIBILITY_PROPAGATION_VALUES;
    }

    public int getViewVisibility(TransitionValues transitionValues) {
        TransitionValues values = transitionValues;
        if (values == null) {
            return 8;
        }
        Integer visibility = (Integer) values.values.get(PROPNAME_VISIBILITY);
        if (visibility == null) {
            return 8;
        }
        return visibility.intValue();
    }

    public int getViewX(TransitionValues values) {
        return getViewCoordinate(values, 0);
    }

    public int getViewY(TransitionValues values) {
        return getViewCoordinate(values, 1);
    }

    private static int getViewCoordinate(TransitionValues transitionValues, int i) {
        TransitionValues values = transitionValues;
        int coordinateIndex = i;
        if (values == null) {
            return -1;
        }
        int[] coordinates = (int[]) values.values.get(PROPNAME_VIEW_CENTER);
        if (coordinates == null) {
            return -1;
        }
        return coordinates[coordinateIndex];
    }
}

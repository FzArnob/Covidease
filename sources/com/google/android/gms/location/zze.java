package com.google.android.gms.location;

import java.util.Comparator;

final class zze implements Comparator<ActivityTransition> {
    zze() {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        ActivityTransition activityTransition = (ActivityTransition) obj2;
        ActivityTransition activityTransition2 = (ActivityTransition) obj;
        ActivityTransition activityTransition3 = activityTransition2;
        int activityType = activityTransition2.getActivityType();
        int activityType2 = activityTransition.getActivityType();
        if (activityType != activityType2) {
            return activityType < activityType2 ? -1 : 1;
        }
        int transitionType = activityTransition3.getTransitionType();
        int transitionType2 = activityTransition.getTransitionType();
        if (transitionType == transitionType2) {
            return 0;
        }
        return transitionType < transitionType2 ? -1 : 1;
    }
}

package com.google.android.gms.location;

import java.util.Comparator;

final class zzh implements Comparator<DetectedActivity> {
    zzh() {
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        DetectedActivity detectedActivity = (DetectedActivity) obj2;
        DetectedActivity detectedActivity2 = (DetectedActivity) obj;
        int compareTo = Integer.valueOf(detectedActivity.getConfidence()).compareTo(Integer.valueOf(detectedActivity2.getConfidence()));
        return compareTo == 0 ? Integer.valueOf(detectedActivity2.getType()).compareTo(Integer.valueOf(detectedActivity.getType())) : compareTo;
    }
}

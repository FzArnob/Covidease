package com.google.android.gms.location;

import com.google.android.gms.common.api.Status;

@Deprecated
public final class LocationStatusCodes {
    public static final int ERROR = 1;
    public static final int GEOFENCE_NOT_AVAILABLE = 1000;
    public static final int GEOFENCE_TOO_MANY_GEOFENCES = 1001;
    public static final int GEOFENCE_TOO_MANY_PENDING_INTENTS = 1002;
    public static final int SUCCESS = 0;

    private LocationStatusCodes() {
    }

    public static int zzc(int i) {
        int i2 = i;
        if ((i2 < 0 || i2 > 1) && (1000 > i2 || i2 > 1002)) {
            return 1;
        }
        return i2;
    }

    public static Status zzd(int i) {
        Status status;
        int i2 = i;
        int i3 = i2;
        switch (i2) {
            case 1:
                i3 = 13;
                break;
        }
        new Status(i3);
        return status;
    }
}

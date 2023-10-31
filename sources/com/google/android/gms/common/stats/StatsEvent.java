package com.google.android.gms.common.stats;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@KeepForSdk
public abstract class StatsEvent extends AbstractSafeParcelable implements ReflectedParcelable {

    @KeepForSdk
    public interface Types {
        @KeepForSdk
        public static final int EVENT_TYPE_ACQUIRE_WAKE_LOCK = 7;
        @KeepForSdk
        public static final int EVENT_TYPE_RELEASE_WAKE_LOCK = 8;
    }

    public StatsEvent() {
    }

    public abstract int getEventType();

    public abstract long getTimeMillis();

    public abstract long zzu();

    public abstract String zzv();

    public String toString() {
        StringBuilder sb;
        long timeMillis = getTimeMillis();
        int eventType = getEventType();
        long zzu = zzu();
        String zzv = zzv();
        new StringBuilder(53 + String.valueOf(zzv).length());
        return sb.append(timeMillis).append("\t").append(eventType).append("\t").append(zzu).append(zzv).toString();
    }
}

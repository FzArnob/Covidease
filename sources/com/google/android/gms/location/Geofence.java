package com.google.android.gms.location;

import android.os.SystemClock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.location.zzbh;

@VisibleForTesting
public interface Geofence {
    public static final int GEOFENCE_TRANSITION_DWELL = 4;
    public static final int GEOFENCE_TRANSITION_ENTER = 1;
    public static final int GEOFENCE_TRANSITION_EXIT = 2;
    public static final long NEVER_EXPIRE = -1;

    @VisibleForTesting
    public static final class Builder {
        private String zzad = null;
        private int zzae = 0;
        private long zzaf = Long.MIN_VALUE;
        private short zzag = -1;
        private double zzah;
        private double zzai;
        private float zzaj;
        private int zzak = 0;
        private int zzal = -1;

        public Builder() {
        }

        public final Geofence build() {
            Geofence geofence;
            Throwable th;
            Throwable th2;
            Throwable th3;
            Throwable th4;
            Throwable th5;
            Throwable th6;
            if (this.zzad == null) {
                Throwable th7 = th6;
                new IllegalArgumentException("Request ID not set.");
                throw th7;
            } else if (this.zzae == 0) {
                Throwable th8 = th5;
                new IllegalArgumentException("Transitions types not set.");
                throw th8;
            } else if ((this.zzae & 4) != 0 && this.zzal < 0) {
                Throwable th9 = th4;
                new IllegalArgumentException("Non-negative loitering delay needs to be set when transition types include GEOFENCE_TRANSITION_DWELLING.");
                throw th9;
            } else if (this.zzaf == Long.MIN_VALUE) {
                Throwable th10 = th3;
                new IllegalArgumentException("Expiration not set.");
                throw th10;
            } else if (this.zzag == -1) {
                Throwable th11 = th2;
                new IllegalArgumentException("Geofence region not set.");
                throw th11;
            } else if (this.zzak < 0) {
                Throwable th12 = th;
                new IllegalArgumentException("Notification responsiveness should be nonnegative.");
                throw th12;
            } else {
                new zzbh(this.zzad, this.zzae, 1, this.zzah, this.zzai, this.zzaj, this.zzaf, this.zzak, this.zzal);
                return geofence;
            }
        }

        public final Builder setCircularRegion(double d, double d2, float f) {
            this.zzag = 1;
            this.zzah = d;
            this.zzai = d2;
            this.zzaj = f;
            return this;
        }

        public final Builder setExpirationDuration(long j) {
            long j2 = j;
            if (j2 < 0) {
                this.zzaf = -1;
            } else {
                this.zzaf = SystemClock.elapsedRealtime() + j2;
            }
            return this;
        }

        public final Builder setLoiteringDelay(int i) {
            this.zzal = i;
            return this;
        }

        public final Builder setNotificationResponsiveness(int i) {
            this.zzak = i;
            return this;
        }

        public final Builder setRequestId(String str) {
            this.zzad = str;
            return this;
        }

        public final Builder setTransitionTypes(int i) {
            this.zzae = i;
            return this;
        }
    }

    String getRequestId();
}

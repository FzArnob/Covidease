package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;

@SafeParcelable.Class(creator = "LocationRequestCreator")
@SafeParcelable.Reserved({1000})
public final class LocationRequest extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<LocationRequest> CREATOR;
    public static final int PRIORITY_BALANCED_POWER_ACCURACY = 102;
    public static final int PRIORITY_HIGH_ACCURACY = 100;
    public static final int PRIORITY_LOW_POWER = 104;
    public static final int PRIORITY_NO_POWER = 105;
    @SafeParcelable.Field(defaultValueUnchecked = "LocationRequest.DEFAULT_PRIORITY", mo25277id = 1)
    private int priority;
    @SafeParcelable.Field(defaultValueUnchecked = "LocationRequest.DEFAULT_EXPIRE_AT", mo25277id = 5)
    private long zzaf;
    @SafeParcelable.Field(defaultValueUnchecked = "LocationRequest.DEFAULT_INTERVAL", mo25277id = 2)
    private long zzaw;
    @SafeParcelable.Field(defaultValueUnchecked = "LocationRequest.DEFAULT_FASTEST_INTERVAL", mo25277id = 3)
    private long zzax;
    @SafeParcelable.Field(defaultValueUnchecked = "LocationRequest.DEFAULT_EXPLICIT_FASTEST_INTERVAL", mo25277id = 4)
    private boolean zzay;
    @SafeParcelable.Field(defaultValueUnchecked = "LocationRequest.DEFAULT_SMALLEST_DISPLACEMENT", mo25277id = 7)
    private float zzaz;
    @SafeParcelable.Field(defaultValueUnchecked = "LocationRequest.DEFAULT_MAX_WAIT_TIME", mo25277id = 8)
    private long zzba;
    @SafeParcelable.Field(defaultValueUnchecked = "LocationRequest.DEFAULT_NUM_UPDATES", mo25277id = 6)
    private int zzx;

    static {
        Parcelable.Creator<LocationRequest> creator;
        new zzab();
        CREATOR = creator;
    }

    public LocationRequest() {
        this.priority = 102;
        this.zzaw = 3600000;
        this.zzax = 600000;
        this.zzay = false;
        this.zzaf = Long.MAX_VALUE;
        this.zzx = Integer.MAX_VALUE;
        this.zzaz = 0.0f;
        this.zzba = 0;
    }

    @SafeParcelable.Constructor
    LocationRequest(@SafeParcelable.Param(mo25280id = 1) int i, @SafeParcelable.Param(mo25280id = 2) long j, @SafeParcelable.Param(mo25280id = 3) long j2, @SafeParcelable.Param(mo25280id = 4) boolean z, @SafeParcelable.Param(mo25280id = 5) long j3, @SafeParcelable.Param(mo25280id = 6) int i2, @SafeParcelable.Param(mo25280id = 7) float f, @SafeParcelable.Param(mo25280id = 8) long j4) {
        this.priority = i;
        this.zzaw = j;
        this.zzax = j2;
        this.zzay = z;
        this.zzaf = j3;
        this.zzx = i2;
        this.zzaz = f;
        this.zzba = j4;
    }

    @VisibleForTesting
    public static LocationRequest create() {
        LocationRequest locationRequest;
        LocationRequest locationRequest2 = locationRequest;
        new LocationRequest();
        return locationRequest2;
    }

    private static void zza(long j) {
        Throwable th;
        StringBuilder sb;
        long j2 = j;
        if (j2 < 0) {
            Throwable th2 = th;
            new StringBuilder(38);
            new IllegalArgumentException(sb.append("invalid interval: ").append(j2).toString());
            throw th2;
        }
    }

    /* JADX WARNING: type inference failed for: r9v0, types: [java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r9) {
        /*
            r8 = this;
            r1 = r8
            r2 = r9
            r4 = r1
            r5 = r2
            if (r4 != r5) goto L_0x0009
            r4 = 1
            r1 = r4
        L_0x0008:
            return r1
        L_0x0009:
            r4 = r2
            boolean r4 = r4 instanceof com.google.android.gms.location.LocationRequest
            if (r4 != 0) goto L_0x0011
            r4 = 0
            r1 = r4
            goto L_0x0008
        L_0x0011:
            r4 = r2
            com.google.android.gms.location.LocationRequest r4 = (com.google.android.gms.location.LocationRequest) r4
            r3 = r4
            r4 = r1
            int r4 = r4.priority
            r5 = r3
            int r5 = r5.priority
            if (r4 != r5) goto L_0x0066
            r4 = r1
            long r4 = r4.zzaw
            r6 = r3
            long r6 = r6.zzaw
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 != 0) goto L_0x0066
            r4 = r1
            long r4 = r4.zzax
            r6 = r3
            long r6 = r6.zzax
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 != 0) goto L_0x0066
            r4 = r1
            boolean r4 = r4.zzay
            r5 = r3
            boolean r5 = r5.zzay
            if (r4 != r5) goto L_0x0066
            r4 = r1
            long r4 = r4.zzaf
            r6 = r3
            long r6 = r6.zzaf
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 != 0) goto L_0x0066
            r4 = r1
            int r4 = r4.zzx
            r5 = r3
            int r5 = r5.zzx
            if (r4 != r5) goto L_0x0066
            r4 = r1
            float r4 = r4.zzaz
            r5 = r3
            float r5 = r5.zzaz
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 != 0) goto L_0x0066
            r4 = r1
            long r4 = r4.getMaxWaitTime()
            r6 = r3
            long r6 = r6.getMaxWaitTime()
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 != 0) goto L_0x0066
            r4 = 1
            r1 = r4
            goto L_0x0008
        L_0x0066:
            r4 = 0
            r1 = r4
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.location.LocationRequest.equals(java.lang.Object):boolean");
    }

    public final long getExpirationTime() {
        return this.zzaf;
    }

    public final long getFastestInterval() {
        return this.zzax;
    }

    public final long getInterval() {
        return this.zzaw;
    }

    public final long getMaxWaitTime() {
        long j = this.zzba;
        long j2 = j;
        if (j < this.zzaw) {
            j2 = this.zzaw;
        }
        return j2;
    }

    public final int getNumUpdates() {
        return this.zzx;
    }

    public final int getPriority() {
        return this.priority;
    }

    public final float getSmallestDisplacement() {
        return this.zzaz;
    }

    public final int hashCode() {
        Object[] objArr = new Object[4];
        objArr[0] = Integer.valueOf(this.priority);
        Object[] objArr2 = objArr;
        objArr2[1] = Long.valueOf(this.zzaw);
        Object[] objArr3 = objArr2;
        objArr3[2] = Float.valueOf(this.zzaz);
        Object[] objArr4 = objArr3;
        objArr4[3] = Long.valueOf(this.zzba);
        return Objects.hashCode(objArr4);
    }

    public final boolean isFastestIntervalExplicitlySet() {
        return this.zzay;
    }

    public final LocationRequest setExpirationDuration(long j) {
        long j2 = j;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (j2 > Long.MAX_VALUE - elapsedRealtime) {
            this.zzaf = Long.MAX_VALUE;
        } else {
            this.zzaf = j2 + elapsedRealtime;
        }
        if (this.zzaf < 0) {
            this.zzaf = 0;
        }
        return this;
    }

    @VisibleForTesting
    public final LocationRequest setExpirationTime(long j) {
        this.zzaf = j;
        if (this.zzaf < 0) {
            this.zzaf = 0;
        }
        return this;
    }

    public final LocationRequest setFastestInterval(long j) {
        long j2 = j;
        zza(j2);
        this.zzay = true;
        this.zzax = j2;
        return this;
    }

    public final LocationRequest setInterval(long j) {
        long j2 = j;
        zza(j2);
        this.zzaw = j2;
        if (!this.zzay) {
            this.zzax = (long) (((double) this.zzaw) / 6.0d);
        }
        return this;
    }

    @VisibleForTesting
    public final LocationRequest setMaxWaitTime(long j) {
        long j2 = j;
        zza(j2);
        this.zzba = j2;
        return this;
    }

    @VisibleForTesting
    public final LocationRequest setNumUpdates(int i) {
        Throwable th;
        StringBuilder sb;
        int i2 = i;
        if (i2 <= 0) {
            Throwable th2 = th;
            new StringBuilder(31);
            new IllegalArgumentException(sb.append("invalid numUpdates: ").append(i2).toString());
            throw th2;
        }
        this.zzx = i2;
        return this;
    }

    @VisibleForTesting
    public final LocationRequest setPriority(int i) {
        Throwable th;
        StringBuilder sb;
        int i2 = i;
        switch (i2) {
            case 100:
            case 102:
            case 104:
            case 105:
                this.priority = i2;
                return this;
            default:
                Throwable th2 = th;
                new StringBuilder(28);
                new IllegalArgumentException(sb.append("invalid quality: ").append(i2).toString());
                throw th2;
        }
    }

    @VisibleForTesting
    public final LocationRequest setSmallestDisplacement(float f) {
        Throwable th;
        StringBuilder sb;
        float f2 = f;
        if (f2 < 0.0f) {
            Throwable th2 = th;
            new StringBuilder(37);
            new IllegalArgumentException(sb.append("invalid displacement: ").append(f2).toString());
            throw th2;
        }
        this.zzaz = f2;
        return this;
    }

    public final String toString() {
        StringBuilder sb;
        String str;
        new StringBuilder();
        StringBuilder sb2 = sb;
        StringBuilder sb3 = sb2;
        StringBuilder append = sb2.append("Request[");
        switch (this.priority) {
            case 100:
                str = "PRIORITY_HIGH_ACCURACY";
                break;
            case 102:
                str = "PRIORITY_BALANCED_POWER_ACCURACY";
                break;
            case 104:
                str = "PRIORITY_LOW_POWER";
                break;
            case 105:
                str = "PRIORITY_NO_POWER";
                break;
            default:
                str = "???";
                break;
        }
        StringBuilder append2 = append.append(str);
        if (this.priority != 105) {
            StringBuilder append3 = sb3.append(" requested=");
            StringBuilder append4 = sb3.append(this.zzaw).append("ms");
        }
        StringBuilder append5 = sb3.append(" fastest=");
        StringBuilder append6 = sb3.append(this.zzax).append("ms");
        if (this.zzba > this.zzaw) {
            StringBuilder append7 = sb3.append(" maxWait=");
            StringBuilder append8 = sb3.append(this.zzba).append("ms");
        }
        if (this.zzaz > 0.0f) {
            StringBuilder append9 = sb3.append(" smallestDisplacement=");
            StringBuilder append10 = sb3.append(this.zzaz).append("m");
        }
        if (this.zzaf != Long.MAX_VALUE) {
            long elapsedRealtime = this.zzaf - SystemClock.elapsedRealtime();
            StringBuilder append11 = sb3.append(" expireIn=");
            StringBuilder append12 = sb3.append(elapsedRealtime).append("ms");
        }
        if (this.zzx != Integer.MAX_VALUE) {
            StringBuilder append13 = sb3.append(" num=").append(this.zzx);
        }
        StringBuilder append14 = sb3.append(']');
        return sb3.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int i2 = i;
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeInt(parcel2, 1, this.priority);
        SafeParcelWriter.writeLong(parcel2, 2, this.zzaw);
        SafeParcelWriter.writeLong(parcel2, 3, this.zzax);
        SafeParcelWriter.writeBoolean(parcel2, 4, this.zzay);
        SafeParcelWriter.writeLong(parcel2, 5, this.zzaf);
        SafeParcelWriter.writeInt(parcel2, 6, this.zzx);
        SafeParcelWriter.writeFloat(parcel2, 7, this.zzaz);
        SafeParcelWriter.writeLong(parcel2, 8, this.zzba);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }
}

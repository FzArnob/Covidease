package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "DeviceOrientationRequestCreator")
public final class zzj extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzj> CREATOR;
    @SafeParcelable.Field(defaultValueUnchecked = "DeviceOrientationRequest.DEFAULT_SHOULD_USE_MAG", mo25277id = 1)
    private boolean zzt;
    @SafeParcelable.Field(defaultValueUnchecked = "DeviceOrientationRequest.DEFAULT_MINIMUM_SAMPLING_PERIOD_MS", mo25277id = 2)
    private long zzu;
    @SafeParcelable.Field(defaultValueUnchecked = "DeviceOrientationRequest.DEFAULT_SMALLEST_ANGLE_CHANGE_RADIANS", mo25277id = 3)
    private float zzv;
    @SafeParcelable.Field(defaultValueUnchecked = "DeviceOrientationRequest.DEFAULT_EXPIRE_AT_MS", mo25277id = 4)
    private long zzw;
    @SafeParcelable.Field(defaultValueUnchecked = "DeviceOrientationRequest.DEFAULT_NUM_UPDATES", mo25277id = 5)
    private int zzx;

    static {
        Parcelable.Creator<zzj> creator;
        new zzk();
        CREATOR = creator;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public zzj() {
        this(true, 50, 0.0f, Long.MAX_VALUE, Integer.MAX_VALUE);
    }

    @SafeParcelable.Constructor
    zzj(@SafeParcelable.Param(mo25280id = 1) boolean z, @SafeParcelable.Param(mo25280id = 2) long j, @SafeParcelable.Param(mo25280id = 3) float f, @SafeParcelable.Param(mo25280id = 4) long j2, @SafeParcelable.Param(mo25280id = 5) int i) {
        this.zzt = z;
        this.zzu = j;
        this.zzv = f;
        this.zzw = j2;
        this.zzx = i;
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
            boolean r4 = r4 instanceof com.google.android.gms.location.zzj
            if (r4 != 0) goto L_0x0011
            r4 = 0
            r1 = r4
            goto L_0x0008
        L_0x0011:
            r4 = r2
            com.google.android.gms.location.zzj r4 = (com.google.android.gms.location.zzj) r4
            r3 = r4
            r4 = r1
            boolean r4 = r4.zzt
            r5 = r3
            boolean r5 = r5.zzt
            if (r4 != r5) goto L_0x0048
            r4 = r1
            long r4 = r4.zzu
            r6 = r3
            long r6 = r6.zzu
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 != 0) goto L_0x0048
            r4 = r1
            float r4 = r4.zzv
            r5 = r3
            float r5 = r5.zzv
            int r4 = java.lang.Float.compare(r4, r5)
            if (r4 != 0) goto L_0x0048
            r4 = r1
            long r4 = r4.zzw
            r6 = r3
            long r6 = r6.zzw
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 != 0) goto L_0x0048
            r4 = r1
            int r4 = r4.zzx
            r5 = r3
            int r5 = r5.zzx
            if (r4 != r5) goto L_0x0048
            r4 = 1
            r1 = r4
            goto L_0x0008
        L_0x0048:
            r4 = 0
            r1 = r4
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.location.zzj.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        Object[] objArr = new Object[5];
        objArr[0] = Boolean.valueOf(this.zzt);
        Object[] objArr2 = objArr;
        objArr2[1] = Long.valueOf(this.zzu);
        Object[] objArr3 = objArr2;
        objArr3[2] = Float.valueOf(this.zzv);
        Object[] objArr4 = objArr3;
        objArr4[3] = Long.valueOf(this.zzw);
        Object[] objArr5 = objArr4;
        objArr5[4] = Integer.valueOf(this.zzx);
        return Objects.hashCode(objArr5);
    }

    public final String toString() {
        StringBuilder sb;
        new StringBuilder();
        StringBuilder sb2 = sb;
        StringBuilder sb3 = sb2;
        StringBuilder append = sb2.append("DeviceOrientationRequest[mShouldUseMag=").append(this.zzt);
        StringBuilder append2 = sb3.append(" mMinimumSamplingPeriodMs=").append(this.zzu);
        StringBuilder append3 = sb3.append(" mSmallestAngleChangeRadians=").append(this.zzv);
        if (this.zzw != Long.MAX_VALUE) {
            long elapsedRealtime = this.zzw - SystemClock.elapsedRealtime();
            StringBuilder append4 = sb3.append(" expireIn=");
            StringBuilder append5 = sb3.append(elapsedRealtime).append("ms");
        }
        if (this.zzx != Integer.MAX_VALUE) {
            StringBuilder append6 = sb3.append(" num=").append(this.zzx);
        }
        StringBuilder append7 = sb3.append(']');
        return sb3.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int i2 = i;
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeBoolean(parcel2, 1, this.zzt);
        SafeParcelWriter.writeLong(parcel2, 2, this.zzu);
        SafeParcelWriter.writeFloat(parcel2, 3, this.zzv);
        SafeParcelWriter.writeLong(parcel2, 4, this.zzw);
        SafeParcelWriter.writeInt(parcel2, 5, this.zzx);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }
}

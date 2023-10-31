package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "NetworkLocationStatusCreator")
public final class zzaj extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzaj> CREATOR;
    @SafeParcelable.Field(defaultValueUnchecked = "LocationAvailability.STATUS_UNKNOWN", mo25277id = 2)
    private final int zzar;
    @SafeParcelable.Field(defaultValueUnchecked = "LocationAvailability.STATUS_UNKNOWN", mo25277id = 1)
    private final int zzas;
    @SafeParcelable.Field(defaultValueUnchecked = "NetworkLocationStatus.STATUS_INVALID_TIMESTAMP", mo25277id = 4)
    private final long zzat;
    @SafeParcelable.Field(defaultValueUnchecked = "NetworkLocationStatus.STATUS_INVALID_TIMESTAMP", mo25277id = 3)
    private final long zzbt;

    static {
        Parcelable.Creator<zzaj> creator;
        new zzak();
        CREATOR = creator;
    }

    @SafeParcelable.Constructor
    zzaj(@SafeParcelable.Param(mo25280id = 1) int i, @SafeParcelable.Param(mo25280id = 2) int i2, @SafeParcelable.Param(mo25280id = 3) long j, @SafeParcelable.Param(mo25280id = 4) long j2) {
        this.zzas = i;
        this.zzar = i2;
        this.zzbt = j;
        this.zzat = j2;
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
            if (r4 == 0) goto L_0x0018
            r4 = r1
            java.lang.Class r4 = r4.getClass()
            r5 = r2
            java.lang.Class r5 = r5.getClass()
            if (r4 == r5) goto L_0x001b
        L_0x0018:
            r4 = 0
            r1 = r4
            goto L_0x0008
        L_0x001b:
            r4 = r2
            com.google.android.gms.location.zzaj r4 = (com.google.android.gms.location.zzaj) r4
            r3 = r4
            r4 = r1
            int r4 = r4.zzas
            r5 = r3
            int r5 = r5.zzas
            if (r4 != r5) goto L_0x0046
            r4 = r1
            int r4 = r4.zzar
            r5 = r3
            int r5 = r5.zzar
            if (r4 != r5) goto L_0x0046
            r4 = r1
            long r4 = r4.zzbt
            r6 = r3
            long r6 = r6.zzbt
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 != 0) goto L_0x0046
            r4 = r1
            long r4 = r4.zzat
            r6 = r3
            long r6 = r6.zzat
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 != 0) goto L_0x0046
            r4 = 1
            r1 = r4
            goto L_0x0008
        L_0x0046:
            r4 = 0
            r1 = r4
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.location.zzaj.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        Object[] objArr = new Object[4];
        objArr[0] = Integer.valueOf(this.zzar);
        Object[] objArr2 = objArr;
        objArr2[1] = Integer.valueOf(this.zzas);
        Object[] objArr3 = objArr2;
        objArr3[2] = Long.valueOf(this.zzat);
        Object[] objArr4 = objArr3;
        objArr4[3] = Long.valueOf(this.zzbt);
        return Objects.hashCode(objArr4);
    }

    public final String toString() {
        StringBuilder sb;
        new StringBuilder("NetworkLocationStatus:");
        StringBuilder sb2 = sb;
        StringBuilder sb3 = sb2;
        StringBuilder append = sb2.append(" Wifi status: ").append(this.zzas).append(" Cell status: ").append(this.zzar).append(" elapsed time NS: ").append(this.zzat).append(" system time ms: ").append(this.zzbt);
        return sb3.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int i2 = i;
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeInt(parcel2, 1, this.zzas);
        SafeParcelWriter.writeInt(parcel2, 2, this.zzar);
        SafeParcelWriter.writeLong(parcel2, 3, this.zzbt);
        SafeParcelWriter.writeLong(parcel2, 4, this.zzat);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }
}

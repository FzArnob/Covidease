package com.google.android.gms.location;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "LocationAvailabilityCreator")
@SafeParcelable.Reserved({1000})
public final class LocationAvailability extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<LocationAvailability> CREATOR;
    @SafeParcelable.Field(defaultValueUnchecked = "LocationAvailability.STATUS_UNKNOWN", mo25277id = 1)
    @Deprecated
    private int zzar;
    @SafeParcelable.Field(defaultValueUnchecked = "LocationAvailability.STATUS_UNKNOWN", mo25277id = 2)
    @Deprecated
    private int zzas;
    @SafeParcelable.Field(defaultValueUnchecked = "0", mo25277id = 3)
    private long zzat;
    @SafeParcelable.Field(defaultValueUnchecked = "LocationAvailability.STATUS_UNSUCCESSFUL", mo25277id = 4)
    private int zzau;
    @SafeParcelable.Field(mo25277id = 5)
    private zzaj[] zzav;

    static {
        Parcelable.Creator<LocationAvailability> creator;
        new zzaa();
        CREATOR = creator;
    }

    @SafeParcelable.Constructor
    LocationAvailability(@SafeParcelable.Param(mo25280id = 4) int i, @SafeParcelable.Param(mo25280id = 1) int i2, @SafeParcelable.Param(mo25280id = 2) int i3, @SafeParcelable.Param(mo25280id = 3) long j, @SafeParcelable.Param(mo25280id = 5) zzaj[] zzajArr) {
        this.zzau = i;
        this.zzar = i2;
        this.zzas = i3;
        this.zzat = j;
        this.zzav = zzajArr;
    }

    public static LocationAvailability extractLocationAvailability(Intent intent) {
        Intent intent2 = intent;
        if (!hasLocationAvailability(intent2)) {
            return null;
        }
        return (LocationAvailability) intent2.getExtras().getParcelable("com.google.android.gms.location.EXTRA_LOCATION_AVAILABILITY");
    }

    public static boolean hasLocationAvailability(Intent intent) {
        Intent intent2 = intent;
        if (intent2 == null) {
            return false;
        }
        return intent2.hasExtra("com.google.android.gms.location.EXTRA_LOCATION_AVAILABILITY");
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
            com.google.android.gms.location.LocationAvailability r4 = (com.google.android.gms.location.LocationAvailability) r4
            r3 = r4
            r4 = r1
            int r4 = r4.zzar
            r5 = r3
            int r5 = r5.zzar
            if (r4 != r5) goto L_0x0050
            r4 = r1
            int r4 = r4.zzas
            r5 = r3
            int r5 = r5.zzas
            if (r4 != r5) goto L_0x0050
            r4 = r1
            long r4 = r4.zzat
            r6 = r3
            long r6 = r6.zzat
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 != 0) goto L_0x0050
            r4 = r1
            int r4 = r4.zzau
            r5 = r3
            int r5 = r5.zzau
            if (r4 != r5) goto L_0x0050
            r4 = r1
            com.google.android.gms.location.zzaj[] r4 = r4.zzav
            r5 = r3
            com.google.android.gms.location.zzaj[] r5 = r5.zzav
            boolean r4 = java.util.Arrays.equals(r4, r5)
            if (r4 == 0) goto L_0x0050
            r4 = 1
            r1 = r4
            goto L_0x0008
        L_0x0050:
            r4 = 0
            r1 = r4
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.location.LocationAvailability.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        Object[] objArr = new Object[5];
        objArr[0] = Integer.valueOf(this.zzau);
        Object[] objArr2 = objArr;
        objArr2[1] = Integer.valueOf(this.zzar);
        Object[] objArr3 = objArr2;
        objArr3[2] = Integer.valueOf(this.zzas);
        Object[] objArr4 = objArr3;
        objArr4[3] = Long.valueOf(this.zzat);
        Object[] objArr5 = objArr4;
        objArr5[4] = this.zzav;
        return Objects.hashCode(objArr5);
    }

    public final boolean isLocationAvailable() {
        return this.zzau < 1000;
    }

    public final String toString() {
        StringBuilder sb;
        boolean isLocationAvailable = isLocationAvailable();
        new StringBuilder(48);
        return sb.append("LocationAvailability[isLocationAvailable: ").append(isLocationAvailable).append("]").toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeInt(parcel2, 1, this.zzar);
        SafeParcelWriter.writeInt(parcel2, 2, this.zzas);
        SafeParcelWriter.writeLong(parcel2, 3, this.zzat);
        SafeParcelWriter.writeInt(parcel2, 4, this.zzau);
        SafeParcelWriter.writeTypedArray(parcel2, 5, this.zzav, i, false);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }
}

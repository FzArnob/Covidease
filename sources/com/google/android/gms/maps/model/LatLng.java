package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "LatLngCreator")
@SafeParcelable.Reserved({1})
public final class LatLng extends AbstractSafeParcelable implements ReflectedParcelable {
    @KeepForSdk
    public static final Parcelable.Creator<LatLng> CREATOR;
    @SafeParcelable.Field(mo25277id = 2)
    public final double latitude;
    @SafeParcelable.Field(mo25277id = 3)
    public final double longitude;

    @SafeParcelable.Constructor
    public LatLng(@SafeParcelable.Param(mo25280id = 2) double d, @SafeParcelable.Param(mo25280id = 3) double d2) {
        double d3 = d;
        double d4 = d2;
        if (-180.0d > d4 || d4 >= 180.0d) {
            this.longitude = ((((d4 - 180.0d) % 360.0d) + 360.0d) % 360.0d) - 180.0d;
        } else {
            this.longitude = d4;
        }
        this.latitude = Math.max(-90.0d, Math.min(90.0d, d3));
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int i2 = i;
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeDouble(parcel2, 2, this.latitude);
        SafeParcelWriter.writeDouble(parcel2, 3, this.longitude);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }

    public final int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.latitude);
        int i = 31 + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
        long doubleToLongBits2 = Double.doubleToLongBits(this.longitude);
        return (i * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)));
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
            boolean r4 = r4 instanceof com.google.android.gms.maps.model.LatLng
            if (r4 != 0) goto L_0x0011
            r4 = 0
            r1 = r4
            goto L_0x0008
        L_0x0011:
            r4 = r2
            com.google.android.gms.maps.model.LatLng r4 = (com.google.android.gms.maps.model.LatLng) r4
            r3 = r4
            r4 = r1
            double r4 = r4.latitude
            long r4 = java.lang.Double.doubleToLongBits(r4)
            r6 = r3
            double r6 = r6.latitude
            long r6 = java.lang.Double.doubleToLongBits(r6)
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 != 0) goto L_0x003c
            r4 = r1
            double r4 = r4.longitude
            long r4 = java.lang.Double.doubleToLongBits(r4)
            r6 = r3
            double r6 = r6.longitude
            long r6 = java.lang.Double.doubleToLongBits(r6)
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 != 0) goto L_0x003c
            r4 = 1
            r1 = r4
            goto L_0x0008
        L_0x003c:
            r4 = 0
            r1 = r4
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.model.LatLng.equals(java.lang.Object):boolean");
    }

    public final String toString() {
        StringBuilder sb;
        double d = this.latitude;
        double d2 = this.longitude;
        new StringBuilder(60);
        return sb.append("lat/lng: (").append(d).append(",").append(d2).append(")").toString();
    }

    static {
        Parcelable.Creator<LatLng> creator;
        new zzf();
        CREATOR = creator;
    }
}

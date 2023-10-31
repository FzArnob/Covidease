package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "VisibleRegionCreator")
@SafeParcelable.Reserved({1})
public final class VisibleRegion extends AbstractSafeParcelable {
    public static final Parcelable.Creator<VisibleRegion> CREATOR;
    @SafeParcelable.Field(mo25277id = 4)
    public final LatLng farLeft;
    @SafeParcelable.Field(mo25277id = 5)
    public final LatLng farRight;
    @SafeParcelable.Field(mo25277id = 6)
    public final LatLngBounds latLngBounds;
    @SafeParcelable.Field(mo25277id = 2)
    public final LatLng nearLeft;
    @SafeParcelable.Field(mo25277id = 3)
    public final LatLng nearRight;

    @SafeParcelable.Constructor
    public VisibleRegion(@SafeParcelable.Param(mo25280id = 2) LatLng latLng, @SafeParcelable.Param(mo25280id = 3) LatLng latLng2, @SafeParcelable.Param(mo25280id = 4) LatLng latLng3, @SafeParcelable.Param(mo25280id = 5) LatLng latLng4, @SafeParcelable.Param(mo25280id = 6) LatLngBounds latLngBounds2) {
        this.nearLeft = latLng;
        this.nearRight = latLng2;
        this.farLeft = latLng3;
        this.farRight = latLng4;
        this.latLngBounds = latLngBounds2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int i2 = i;
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeParcelable(parcel2, 2, this.nearLeft, i2, false);
        SafeParcelWriter.writeParcelable(parcel2, 3, this.nearRight, i2, false);
        SafeParcelWriter.writeParcelable(parcel2, 4, this.farLeft, i2, false);
        SafeParcelWriter.writeParcelable(parcel2, 5, this.farRight, i2, false);
        SafeParcelWriter.writeParcelable(parcel2, 6, this.latLngBounds, i2, false);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }

    public final int hashCode() {
        Object[] objArr = new Object[5];
        objArr[0] = this.nearLeft;
        Object[] objArr2 = objArr;
        objArr2[1] = this.nearRight;
        Object[] objArr3 = objArr2;
        objArr3[2] = this.farLeft;
        Object[] objArr4 = objArr3;
        objArr4[3] = this.farRight;
        Object[] objArr5 = objArr4;
        objArr5[4] = this.latLngBounds;
        return Objects.hashCode(objArr5);
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r6) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r3 = r0
            r4 = r1
            if (r3 != r4) goto L_0x0009
            r3 = 1
            r0 = r3
        L_0x0008:
            return r0
        L_0x0009:
            r3 = r1
            boolean r3 = r3 instanceof com.google.android.gms.maps.model.VisibleRegion
            if (r3 != 0) goto L_0x0011
            r3 = 0
            r0 = r3
            goto L_0x0008
        L_0x0011:
            r3 = r1
            com.google.android.gms.maps.model.VisibleRegion r3 = (com.google.android.gms.maps.model.VisibleRegion) r3
            r2 = r3
            r3 = r0
            com.google.android.gms.maps.model.LatLng r3 = r3.nearLeft
            r4 = r2
            com.google.android.gms.maps.model.LatLng r4 = r4.nearLeft
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0054
            r3 = r0
            com.google.android.gms.maps.model.LatLng r3 = r3.nearRight
            r4 = r2
            com.google.android.gms.maps.model.LatLng r4 = r4.nearRight
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0054
            r3 = r0
            com.google.android.gms.maps.model.LatLng r3 = r3.farLeft
            r4 = r2
            com.google.android.gms.maps.model.LatLng r4 = r4.farLeft
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0054
            r3 = r0
            com.google.android.gms.maps.model.LatLng r3 = r3.farRight
            r4 = r2
            com.google.android.gms.maps.model.LatLng r4 = r4.farRight
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0054
            r3 = r0
            com.google.android.gms.maps.model.LatLngBounds r3 = r3.latLngBounds
            r4 = r2
            com.google.android.gms.maps.model.LatLngBounds r4 = r4.latLngBounds
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0054
            r3 = 1
            r0 = r3
            goto L_0x0008
        L_0x0054:
            r3 = 0
            r0 = r3
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.model.VisibleRegion.equals(java.lang.Object):boolean");
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("nearLeft", this.nearLeft).add("nearRight", this.nearRight).add("farLeft", this.farLeft).add("farRight", this.farRight).add("latLngBounds", this.latLngBounds).toString();
    }

    static {
        Parcelable.Creator<VisibleRegion> creator;
        new zzv();
        CREATOR = creator;
    }
}

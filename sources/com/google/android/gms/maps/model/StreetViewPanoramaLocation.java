package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "StreetViewPanoramaLocationCreator")
@SafeParcelable.Reserved({1})
public class StreetViewPanoramaLocation extends AbstractSafeParcelable {
    public static final Parcelable.Creator<StreetViewPanoramaLocation> CREATOR;
    @SafeParcelable.Field(mo25277id = 2)
    public final StreetViewPanoramaLink[] links;
    @SafeParcelable.Field(mo25277id = 4)
    public final String panoId;
    @SafeParcelable.Field(mo25277id = 3)
    public final LatLng position;

    @SafeParcelable.Constructor
    public StreetViewPanoramaLocation(@SafeParcelable.Param(mo25280id = 2) StreetViewPanoramaLink[] streetViewPanoramaLinkArr, @SafeParcelable.Param(mo25280id = 3) LatLng latLng, @SafeParcelable.Param(mo25280id = 4) String str) {
        this.links = streetViewPanoramaLinkArr;
        this.position = latLng;
        this.panoId = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = i;
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeTypedArray(parcel2, 2, this.links, i2, false);
        SafeParcelWriter.writeParcelable(parcel2, 3, this.position, i2, false);
        SafeParcelWriter.writeString(parcel2, 4, this.panoId, false);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }

    public int hashCode() {
        Object[] objArr = new Object[2];
        objArr[0] = this.position;
        Object[] objArr2 = objArr;
        objArr2[1] = this.panoId;
        return Objects.hashCode(objArr2);
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r6) {
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
            boolean r3 = r3 instanceof com.google.android.gms.maps.model.StreetViewPanoramaLocation
            if (r3 != 0) goto L_0x0011
            r3 = 0
            r0 = r3
            goto L_0x0008
        L_0x0011:
            r3 = r1
            com.google.android.gms.maps.model.StreetViewPanoramaLocation r3 = (com.google.android.gms.maps.model.StreetViewPanoramaLocation) r3
            r2 = r3
            r3 = r0
            java.lang.String r3 = r3.panoId
            r4 = r2
            java.lang.String r4 = r4.panoId
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0030
            r3 = r0
            com.google.android.gms.maps.model.LatLng r3 = r3.position
            r4 = r2
            com.google.android.gms.maps.model.LatLng r4 = r4.position
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0030
            r3 = 1
            r0 = r3
            goto L_0x0008
        L_0x0030:
            r3 = 0
            r0 = r3
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.model.StreetViewPanoramaLocation.equals(java.lang.Object):boolean");
    }

    public String toString() {
        return Objects.toStringHelper(this).add("panoId", this.panoId).add("position", this.position.toString()).toString();
    }

    static {
        Parcelable.Creator<StreetViewPanoramaLocation> creator;
        new zzo();
        CREATOR = creator;
    }
}

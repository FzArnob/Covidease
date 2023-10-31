package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "StreetViewPanoramaLinkCreator")
@SafeParcelable.Reserved({1})
public class StreetViewPanoramaLink extends AbstractSafeParcelable {
    public static final Parcelable.Creator<StreetViewPanoramaLink> CREATOR;
    @SafeParcelable.Field(mo25277id = 3)
    public final float bearing;
    @SafeParcelable.Field(mo25277id = 2)
    public final String panoId;

    @SafeParcelable.Constructor
    public StreetViewPanoramaLink(@SafeParcelable.Param(mo25280id = 2) String str, @SafeParcelable.Param(mo25280id = 3) float f) {
        float f2 = f;
        this.panoId = str;
        this.bearing = (((double) f2) <= 0.0d ? (f2 % 360.0f) + 360.0f : f2) % 360.0f;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = i;
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeString(parcel2, 2, this.panoId, false);
        SafeParcelWriter.writeFloat(parcel2, 3, this.bearing);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }

    public int hashCode() {
        Object[] objArr = new Object[2];
        objArr[0] = this.panoId;
        Object[] objArr2 = objArr;
        objArr2[1] = Float.valueOf(this.bearing);
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
            boolean r3 = r3 instanceof com.google.android.gms.maps.model.StreetViewPanoramaLink
            if (r3 != 0) goto L_0x0011
            r3 = 0
            r0 = r3
            goto L_0x0008
        L_0x0011:
            r3 = r1
            com.google.android.gms.maps.model.StreetViewPanoramaLink r3 = (com.google.android.gms.maps.model.StreetViewPanoramaLink) r3
            r2 = r3
            r3 = r0
            java.lang.String r3 = r3.panoId
            r4 = r2
            java.lang.String r4 = r4.panoId
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0034
            r3 = r0
            float r3 = r3.bearing
            int r3 = java.lang.Float.floatToIntBits(r3)
            r4 = r2
            float r4 = r4.bearing
            int r4 = java.lang.Float.floatToIntBits(r4)
            if (r3 != r4) goto L_0x0034
            r3 = 1
            r0 = r3
            goto L_0x0008
        L_0x0034:
            r3 = 0
            r0 = r3
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.model.StreetViewPanoramaLink.equals(java.lang.Object):boolean");
    }

    public String toString() {
        return Objects.toStringHelper(this).add("panoId", this.panoId).add("bearing", Float.valueOf(this.bearing)).toString();
    }

    static {
        Parcelable.Creator<StreetViewPanoramaLink> creator;
        new zzn();
        CREATOR = creator;
    }
}

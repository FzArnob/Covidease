package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "StreetViewPanoramaOrientationCreator")
@SafeParcelable.Reserved({1})
public class StreetViewPanoramaOrientation extends AbstractSafeParcelable {
    public static final Parcelable.Creator<StreetViewPanoramaOrientation> CREATOR;
    @SafeParcelable.Field(mo25277id = 3)
    public final float bearing;
    @SafeParcelable.Field(mo25277id = 2)
    public final float tilt;

    public static final class Builder {
        public float bearing;
        public float tilt;

        public Builder() {
        }

        public Builder(@NonNull StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
            StreetViewPanoramaOrientation streetViewPanoramaOrientation2 = streetViewPanoramaOrientation;
            Object checkNotNull = Preconditions.checkNotNull(streetViewPanoramaOrientation2, "StreetViewPanoramaOrientation");
            this.bearing = streetViewPanoramaOrientation2.bearing;
            this.tilt = streetViewPanoramaOrientation2.tilt;
        }

        public final Builder tilt(float f) {
            this.tilt = f;
            return this;
        }

        public final Builder bearing(float f) {
            this.bearing = f;
            return this;
        }

        public final StreetViewPanoramaOrientation build() {
            StreetViewPanoramaOrientation streetViewPanoramaOrientation;
            new StreetViewPanoramaOrientation(this.tilt, this.bearing);
            return streetViewPanoramaOrientation;
        }
    }

    @SafeParcelable.Constructor
    public StreetViewPanoramaOrientation(@SafeParcelable.Param(mo25280id = 2) float f, @SafeParcelable.Param(mo25280id = 3) float f2) {
        StringBuilder sb;
        float f3 = f;
        float f4 = f2;
        boolean z = -90.0f <= f3 && f3 <= 90.0f;
        new StringBuilder(62);
        Preconditions.checkArgument(z, sb.append("Tilt needs to be between -90 and 90 inclusive: ").append(f3).toString());
        this.tilt = f3 + 0.0f;
        this.bearing = (((double) f4) <= 0.0d ? (f4 % 360.0f) + 360.0f : f4) % 360.0f;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = i;
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeFloat(parcel2, 2, this.tilt);
        SafeParcelWriter.writeFloat(parcel2, 3, this.bearing);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }

    public static Builder builder() {
        Builder builder;
        Builder builder2 = builder;
        new Builder();
        return builder2;
    }

    public static Builder builder(@NonNull StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
        Builder builder;
        new Builder(streetViewPanoramaOrientation);
        return builder;
    }

    public int hashCode() {
        Object[] objArr = new Object[2];
        objArr[0] = Float.valueOf(this.tilt);
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
            boolean r3 = r3 instanceof com.google.android.gms.maps.model.StreetViewPanoramaOrientation
            if (r3 != 0) goto L_0x0011
            r3 = 0
            r0 = r3
            goto L_0x0008
        L_0x0011:
            r3 = r1
            com.google.android.gms.maps.model.StreetViewPanoramaOrientation r3 = (com.google.android.gms.maps.model.StreetViewPanoramaOrientation) r3
            r2 = r3
            r3 = r0
            float r3 = r3.tilt
            int r3 = java.lang.Float.floatToIntBits(r3)
            r4 = r2
            float r4 = r4.tilt
            int r4 = java.lang.Float.floatToIntBits(r4)
            if (r3 != r4) goto L_0x0038
            r3 = r0
            float r3 = r3.bearing
            int r3 = java.lang.Float.floatToIntBits(r3)
            r4 = r2
            float r4 = r4.bearing
            int r4 = java.lang.Float.floatToIntBits(r4)
            if (r3 != r4) goto L_0x0038
            r3 = 1
            r0 = r3
            goto L_0x0008
        L_0x0038:
            r3 = 0
            r0 = r3
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.model.StreetViewPanoramaOrientation.equals(java.lang.Object):boolean");
    }

    public String toString() {
        return Objects.toStringHelper(this).add("tilt", Float.valueOf(this.tilt)).add("bearing", Float.valueOf(this.bearing)).toString();
    }

    static {
        Parcelable.Creator<StreetViewPanoramaOrientation> creator;
        new zzp();
        CREATOR = creator;
    }
}

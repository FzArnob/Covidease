package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;

@SafeParcelable.Class(creator = "StreetViewPanoramaCameraCreator")
@SafeParcelable.Reserved({1})
public class StreetViewPanoramaCamera extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<StreetViewPanoramaCamera> CREATOR;
    @SafeParcelable.Field(mo25277id = 4)
    public final float bearing;
    @SafeParcelable.Field(mo25277id = 3)
    public final float tilt;
    @SafeParcelable.Field(mo25277id = 2)
    public final float zoom;
    @NonNull
    private final StreetViewPanoramaOrientation zzeg;

    public static final class Builder {
        public float bearing;
        public float tilt;
        public float zoom;

        public Builder() {
        }

        public Builder(@NonNull StreetViewPanoramaCamera streetViewPanoramaCamera) {
            StreetViewPanoramaCamera streetViewPanoramaCamera2 = streetViewPanoramaCamera;
            Object checkNotNull = Preconditions.checkNotNull(streetViewPanoramaCamera2, "StreetViewPanoramaCamera");
            this.zoom = streetViewPanoramaCamera2.zoom;
            this.bearing = streetViewPanoramaCamera2.bearing;
            this.tilt = streetViewPanoramaCamera2.tilt;
        }

        public final Builder zoom(float f) {
            this.zoom = f;
            return this;
        }

        public final Builder orientation(StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
            StreetViewPanoramaOrientation streetViewPanoramaOrientation2 = streetViewPanoramaOrientation;
            Object checkNotNull = Preconditions.checkNotNull(streetViewPanoramaOrientation2, "StreetViewPanoramaOrientation");
            this.tilt = streetViewPanoramaOrientation2.tilt;
            this.bearing = streetViewPanoramaOrientation2.bearing;
            return this;
        }

        public final Builder tilt(float f) {
            this.tilt = f;
            return this;
        }

        public final Builder bearing(float f) {
            this.bearing = f;
            return this;
        }

        public final StreetViewPanoramaCamera build() {
            StreetViewPanoramaCamera streetViewPanoramaCamera;
            new StreetViewPanoramaCamera(this.zoom, this.tilt, this.bearing);
            return streetViewPanoramaCamera;
        }
    }

    @SafeParcelable.Constructor
    public StreetViewPanoramaCamera(@SafeParcelable.Param(mo25280id = 2) float f, @SafeParcelable.Param(mo25280id = 3) float f2, @SafeParcelable.Param(mo25280id = 4) float f3) {
        StringBuilder sb;
        StreetViewPanoramaOrientation.Builder builder;
        float f4 = f;
        float f5 = f2;
        float f6 = f3;
        boolean z = -90.0f <= f5 && f5 <= 90.0f;
        new StringBuilder(62);
        Preconditions.checkArgument(z, sb.append("Tilt needs to be between -90 and 90 inclusive: ").append(f5).toString());
        this.zoom = ((double) f4) <= 0.0d ? 0.0f : f4;
        this.tilt = f5 + 0.0f;
        this.bearing = (((double) f6) <= 0.0d ? (f6 % 360.0f) + 360.0f : f6) % 360.0f;
        new StreetViewPanoramaOrientation.Builder();
        this.zzeg = builder.tilt(f5).bearing(f6).build();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = i;
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeFloat(parcel2, 2, this.zoom);
        SafeParcelWriter.writeFloat(parcel2, 3, this.tilt);
        SafeParcelWriter.writeFloat(parcel2, 4, this.bearing);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }

    public static Builder builder() {
        Builder builder;
        Builder builder2 = builder;
        new Builder();
        return builder2;
    }

    public static Builder builder(@NonNull StreetViewPanoramaCamera streetViewPanoramaCamera) {
        Builder builder;
        new Builder(streetViewPanoramaCamera);
        return builder;
    }

    @NonNull
    public StreetViewPanoramaOrientation getOrientation() {
        return this.zzeg;
    }

    public int hashCode() {
        Object[] objArr = new Object[3];
        objArr[0] = Float.valueOf(this.zoom);
        Object[] objArr2 = objArr;
        objArr2[1] = Float.valueOf(this.tilt);
        Object[] objArr3 = objArr2;
        objArr3[2] = Float.valueOf(this.bearing);
        return Objects.hashCode(objArr3);
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
            boolean r3 = r3 instanceof com.google.android.gms.maps.model.StreetViewPanoramaCamera
            if (r3 != 0) goto L_0x0011
            r3 = 0
            r0 = r3
            goto L_0x0008
        L_0x0011:
            r3 = r1
            com.google.android.gms.maps.model.StreetViewPanoramaCamera r3 = (com.google.android.gms.maps.model.StreetViewPanoramaCamera) r3
            r2 = r3
            r3 = r0
            float r3 = r3.zoom
            int r3 = java.lang.Float.floatToIntBits(r3)
            r4 = r2
            float r4 = r4.zoom
            int r4 = java.lang.Float.floatToIntBits(r4)
            if (r3 != r4) goto L_0x0048
            r3 = r0
            float r3 = r3.tilt
            int r3 = java.lang.Float.floatToIntBits(r3)
            r4 = r2
            float r4 = r4.tilt
            int r4 = java.lang.Float.floatToIntBits(r4)
            if (r3 != r4) goto L_0x0048
            r3 = r0
            float r3 = r3.bearing
            int r3 = java.lang.Float.floatToIntBits(r3)
            r4 = r2
            float r4 = r4.bearing
            int r4 = java.lang.Float.floatToIntBits(r4)
            if (r3 != r4) goto L_0x0048
            r3 = 1
            r0 = r3
            goto L_0x0008
        L_0x0048:
            r3 = 0
            r0 = r3
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.model.StreetViewPanoramaCamera.equals(java.lang.Object):boolean");
    }

    public String toString() {
        return Objects.toStringHelper(this).add("zoom", Float.valueOf(this.zoom)).add("tilt", Float.valueOf(this.tilt)).add("bearing", Float.valueOf(this.bearing)).toString();
    }

    static {
        Parcelable.Creator<StreetViewPanoramaCamera> creator;
        new zzm();
        CREATOR = creator;
    }
}

package com.google.android.gms.maps.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.GoogleMapOptions;

@SafeParcelable.Class(creator = "CameraPositionCreator")
@SafeParcelable.Reserved({1})
public final class CameraPosition extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<CameraPosition> CREATOR;
    @SafeParcelable.Field(mo25277id = 5)
    public final float bearing;
    @SafeParcelable.Field(mo25277id = 2)
    public final LatLng target;
    @SafeParcelable.Field(mo25277id = 4)
    public final float tilt;
    @SafeParcelable.Field(mo25277id = 3)
    public final float zoom;

    public static final class Builder {
        private float bearing;
        private LatLng target;
        private float tilt;
        private float zoom;

        public Builder() {
        }

        public Builder(CameraPosition cameraPosition) {
            CameraPosition cameraPosition2 = cameraPosition;
            this.target = cameraPosition2.target;
            this.zoom = cameraPosition2.zoom;
            this.tilt = cameraPosition2.tilt;
            this.bearing = cameraPosition2.bearing;
        }

        public final Builder target(LatLng latLng) {
            this.target = latLng;
            return this;
        }

        public final Builder zoom(float f) {
            this.zoom = f;
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

        public final CameraPosition build() {
            CameraPosition cameraPosition;
            new CameraPosition(this.target, this.zoom, this.tilt, this.bearing);
            return cameraPosition;
        }
    }

    @SafeParcelable.Constructor
    public CameraPosition(@SafeParcelable.Param(mo25280id = 2) LatLng latLng, @SafeParcelable.Param(mo25280id = 3) float f, @SafeParcelable.Param(mo25280id = 4) float f2, @SafeParcelable.Param(mo25280id = 5) float f3) {
        LatLng latLng2 = latLng;
        float f4 = f;
        float f5 = f2;
        float f6 = f3;
        Object checkNotNull = Preconditions.checkNotNull(latLng2, "null camera target");
        Preconditions.checkArgument(0.0f <= f5 && f5 <= 90.0f, "Tilt needs to be between 0 and 90 inclusive: %s", Float.valueOf(f5));
        this.target = latLng2;
        this.zoom = f4;
        this.tilt = f5 + 0.0f;
        this.bearing = (((double) f6) <= 0.0d ? (f6 % 360.0f) + 360.0f : f6) % 360.0f;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeParcelable(parcel2, 2, this.target, i, false);
        SafeParcelWriter.writeFloat(parcel2, 3, this.zoom);
        SafeParcelWriter.writeFloat(parcel2, 4, this.tilt);
        SafeParcelWriter.writeFloat(parcel2, 5, this.bearing);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }

    public final int hashCode() {
        Object[] objArr = new Object[4];
        objArr[0] = this.target;
        Object[] objArr2 = objArr;
        objArr2[1] = Float.valueOf(this.zoom);
        Object[] objArr3 = objArr2;
        objArr3[2] = Float.valueOf(this.tilt);
        Object[] objArr4 = objArr3;
        objArr4[3] = Float.valueOf(this.bearing);
        return Objects.hashCode(objArr4);
    }

    public static final CameraPosition fromLatLngZoom(LatLng latLng, float f) {
        CameraPosition cameraPosition;
        new CameraPosition(latLng, f, 0.0f, 0.0f);
        return cameraPosition;
    }

    public static Builder builder() {
        Builder builder;
        Builder builder2 = builder;
        new Builder();
        return builder2;
    }

    public static Builder builder(CameraPosition cameraPosition) {
        Builder builder;
        new Builder(cameraPosition);
        return builder;
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
            boolean r3 = r3 instanceof com.google.android.gms.maps.model.CameraPosition
            if (r3 != 0) goto L_0x0011
            r3 = 0
            r0 = r3
            goto L_0x0008
        L_0x0011:
            r3 = r1
            com.google.android.gms.maps.model.CameraPosition r3 = (com.google.android.gms.maps.model.CameraPosition) r3
            r2 = r3
            r3 = r0
            com.google.android.gms.maps.model.LatLng r3 = r3.target
            r4 = r2
            com.google.android.gms.maps.model.LatLng r4 = r4.target
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0054
            r3 = r0
            float r3 = r3.zoom
            int r3 = java.lang.Float.floatToIntBits(r3)
            r4 = r2
            float r4 = r4.zoom
            int r4 = java.lang.Float.floatToIntBits(r4)
            if (r3 != r4) goto L_0x0054
            r3 = r0
            float r3 = r3.tilt
            int r3 = java.lang.Float.floatToIntBits(r3)
            r4 = r2
            float r4 = r4.tilt
            int r4 = java.lang.Float.floatToIntBits(r4)
            if (r3 != r4) goto L_0x0054
            r3 = r0
            float r3 = r3.bearing
            int r3 = java.lang.Float.floatToIntBits(r3)
            r4 = r2
            float r4 = r4.bearing
            int r4 = java.lang.Float.floatToIntBits(r4)
            if (r3 != r4) goto L_0x0054
            r3 = 1
            r0 = r3
            goto L_0x0008
        L_0x0054:
            r3 = 0
            r0 = r3
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.model.CameraPosition.equals(java.lang.Object):boolean");
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("target", this.target).add("zoom", Float.valueOf(this.zoom)).add("tilt", Float.valueOf(this.tilt)).add("bearing", Float.valueOf(this.bearing)).toString();
    }

    public static CameraPosition createFromAttributes(Context context, AttributeSet attributeSet) {
        return GoogleMapOptions.zzb(context, attributeSet);
    }

    static {
        Parcelable.Creator<CameraPosition> creator;
        new zza();
        CREATOR = creator;
    }
}

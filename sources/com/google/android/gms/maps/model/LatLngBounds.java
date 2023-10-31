package com.google.android.gms.maps.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.GoogleMapOptions;

@SafeParcelable.Class(creator = "LatLngBoundsCreator")
@SafeParcelable.Reserved({1})
public final class LatLngBounds extends AbstractSafeParcelable implements ReflectedParcelable {
    @KeepForSdk
    public static final Parcelable.Creator<LatLngBounds> CREATOR;
    @SafeParcelable.Field(mo25277id = 3)
    public final LatLng northeast;
    @SafeParcelable.Field(mo25277id = 2)
    public final LatLng southwest;

    @SafeParcelable.Constructor
    public LatLngBounds(@SafeParcelable.Param(mo25280id = 2) LatLng latLng, @SafeParcelable.Param(mo25280id = 3) LatLng latLng2) {
        LatLng latLng3 = latLng;
        LatLng latLng4 = latLng2;
        Object checkNotNull = Preconditions.checkNotNull(latLng3, "null southwest");
        Object checkNotNull2 = Preconditions.checkNotNull(latLng4, "null northeast");
        boolean z = latLng4.latitude >= latLng3.latitude;
        Object[] objArr = new Object[2];
        objArr[0] = Double.valueOf(latLng3.latitude);
        Object[] objArr2 = objArr;
        objArr2[1] = Double.valueOf(latLng4.latitude);
        Preconditions.checkArgument(z, "southern latitude exceeds northern latitude (%s > %s)", objArr2);
        this.southwest = latLng3;
        this.northeast = latLng4;
    }

    public static final class Builder {
        private double zzdh = Double.POSITIVE_INFINITY;
        private double zzdi = Double.NEGATIVE_INFINITY;
        private double zzdj = Double.NaN;
        private double zzdk = Double.NaN;

        public Builder() {
        }

        public final Builder include(LatLng latLng) {
            boolean z;
            LatLng latLng2 = latLng;
            this.zzdh = Math.min(this.zzdh, latLng2.latitude);
            this.zzdi = Math.max(this.zzdi, latLng2.latitude);
            double d = latLng2.longitude;
            if (Double.isNaN(this.zzdj)) {
                this.zzdj = d;
            } else {
                if (this.zzdj <= this.zzdk) {
                    z = this.zzdj <= d && d <= this.zzdk;
                } else {
                    z = this.zzdj <= d || d <= this.zzdk;
                }
                if (!z) {
                    if (LatLngBounds.zza(this.zzdj, d) < LatLngBounds.zzb(this.zzdk, d)) {
                        this.zzdj = d;
                    }
                }
                return this;
            }
            this.zzdk = d;
            return this;
        }

        public final LatLngBounds build() {
            LatLngBounds latLngBounds;
            LatLng latLng;
            LatLng latLng2;
            Preconditions.checkState(!Double.isNaN(this.zzdj), "no included points");
            new LatLng(this.zzdh, this.zzdj);
            new LatLng(this.zzdi, this.zzdk);
            new LatLngBounds(latLng, latLng2);
            return latLngBounds;
        }
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int i2 = i;
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeParcelable(parcel2, 2, this.southwest, i2, false);
        SafeParcelWriter.writeParcelable(parcel2, 3, this.northeast, i2, false);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }

    public static Builder builder() {
        Builder builder;
        Builder builder2 = builder;
        new Builder();
        return builder2;
    }

    public final boolean contains(LatLng latLng) {
        LatLng latLng2 = latLng;
        double d = latLng2.latitude;
        return ((this.southwest.latitude > d ? 1 : (this.southwest.latitude == d ? 0 : -1)) <= 0 && (d > this.northeast.latitude ? 1 : (d == this.northeast.latitude ? 0 : -1)) <= 0) && zza(latLng2.longitude);
    }

    public final LatLngBounds including(LatLng latLng) {
        LatLngBounds latLngBounds;
        LatLng latLng2;
        LatLng latLng3;
        LatLng latLng4 = latLng;
        double min = Math.min(this.southwest.latitude, latLng4.latitude);
        double max = Math.max(this.northeast.latitude, latLng4.latitude);
        double d = this.northeast.longitude;
        double d2 = this.southwest.longitude;
        double d3 = latLng4.longitude;
        if (!zza(d3)) {
            if (zza(d2, d3) < zzb(d, d3)) {
                d2 = d3;
            } else {
                d = d3;
            }
        }
        new LatLng(min, d2);
        new LatLng(max, d);
        new LatLngBounds(latLng2, latLng3);
        return latLngBounds;
    }

    public final LatLng getCenter() {
        double d;
        LatLng latLng;
        double d2 = (this.southwest.latitude + this.northeast.latitude) / 2.0d;
        double d3 = this.northeast.longitude;
        double d4 = this.southwest.longitude;
        double d5 = d4;
        if (d4 <= d3) {
            d = (d3 + d5) / 2.0d;
        } else {
            d = ((d3 + 360.0d) + d5) / 2.0d;
        }
        new LatLng(d2, d);
        return latLng;
    }

    /* access modifiers changed from: private */
    public static double zza(double d, double d2) {
        return ((d - d2) + 360.0d) % 360.0d;
    }

    /* access modifiers changed from: private */
    public static double zzb(double d, double d2) {
        return ((d2 - d) + 360.0d) % 360.0d;
    }

    private final boolean zza(double d) {
        double d2 = d;
        return this.southwest.longitude <= this.northeast.longitude ? this.southwest.longitude <= d2 && d2 <= this.northeast.longitude : this.southwest.longitude <= d2 || d2 <= this.northeast.longitude;
    }

    public final int hashCode() {
        Object[] objArr = new Object[2];
        objArr[0] = this.southwest;
        Object[] objArr2 = objArr;
        objArr2[1] = this.northeast;
        return Objects.hashCode(objArr2);
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
            boolean r3 = r3 instanceof com.google.android.gms.maps.model.LatLngBounds
            if (r3 != 0) goto L_0x0011
            r3 = 0
            r0 = r3
            goto L_0x0008
        L_0x0011:
            r3 = r1
            com.google.android.gms.maps.model.LatLngBounds r3 = (com.google.android.gms.maps.model.LatLngBounds) r3
            r2 = r3
            r3 = r0
            com.google.android.gms.maps.model.LatLng r3 = r3.southwest
            r4 = r2
            com.google.android.gms.maps.model.LatLng r4 = r4.southwest
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0030
            r3 = r0
            com.google.android.gms.maps.model.LatLng r3 = r3.northeast
            r4 = r2
            com.google.android.gms.maps.model.LatLng r4 = r4.northeast
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.model.LatLngBounds.equals(java.lang.Object):boolean");
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("southwest", this.southwest).add("northeast", this.northeast).toString();
    }

    public static LatLngBounds createFromAttributes(Context context, AttributeSet attributeSet) {
        return GoogleMapOptions.zza(context, attributeSet);
    }

    static {
        Parcelable.Creator<LatLngBounds> creator;
        new zze();
        CREATOR = creator;
    }
}

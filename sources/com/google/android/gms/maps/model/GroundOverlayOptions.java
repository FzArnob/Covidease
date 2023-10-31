package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.dynamic.IObjectWrapper;

@SafeParcelable.Class(creator = "GroundOverlayOptionsCreator")
@SafeParcelable.Reserved({1})
public final class GroundOverlayOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<GroundOverlayOptions> CREATOR;
    public static final float NO_DIMENSION = -1.0f;
    @SafeParcelable.Field(getter = "getBearing", mo25277id = 7)
    private float bearing;
    @SafeParcelable.Field(getter = "getHeight", mo25277id = 5)
    private float height;
    @SafeParcelable.Field(getter = "getWidth", mo25277id = 4)
    private float width;
    @SafeParcelable.Field(getter = "getZIndex", mo25277id = 8)
    private float zzcs;
    @SafeParcelable.Field(getter = "isVisible", mo25277id = 9)
    private boolean zzct = true;
    @SafeParcelable.Field(getter = "isClickable", mo25277id = 13)
    private boolean zzcu = false;
    @SafeParcelable.Field(getter = "getWrappedImageDescriptorImplBinder", mo25277id = 2, type = "android.os.IBinder")
    @NonNull
    private BitmapDescriptor zzcx;
    @SafeParcelable.Field(getter = "getLocation", mo25277id = 3)
    private LatLng zzcy;
    @SafeParcelable.Field(getter = "getBounds", mo25277id = 6)
    private LatLngBounds zzcz;
    @SafeParcelable.Field(getter = "getTransparency", mo25277id = 10)
    private float zzda = 0.0f;
    @SafeParcelable.Field(getter = "getAnchorU", mo25277id = 11)
    private float zzdb = 0.5f;
    @SafeParcelable.Field(getter = "getAnchorV", mo25277id = 12)
    private float zzdc = 0.5f;

    @SafeParcelable.Constructor
    GroundOverlayOptions(@SafeParcelable.Param(mo25280id = 2) IBinder iBinder, @SafeParcelable.Param(mo25280id = 3) LatLng latLng, @SafeParcelable.Param(mo25280id = 4) float f, @SafeParcelable.Param(mo25280id = 5) float f2, @SafeParcelable.Param(mo25280id = 6) LatLngBounds latLngBounds, @SafeParcelable.Param(mo25280id = 7) float f3, @SafeParcelable.Param(mo25280id = 8) float f4, @SafeParcelable.Param(mo25280id = 9) boolean z, @SafeParcelable.Param(mo25280id = 10) float f5, @SafeParcelable.Param(mo25280id = 11) float f6, @SafeParcelable.Param(mo25280id = 12) float f7, @SafeParcelable.Param(mo25280id = 13) boolean z2) {
        BitmapDescriptor bitmapDescriptor;
        new BitmapDescriptor(IObjectWrapper.Stub.asInterface(iBinder));
        this.zzcx = bitmapDescriptor;
        this.zzcy = latLng;
        this.width = f;
        this.height = f2;
        this.zzcz = latLngBounds;
        this.bearing = f3;
        this.zzcs = f4;
        this.zzct = z;
        this.zzda = f5;
        this.zzdb = f6;
        this.zzdc = f7;
        this.zzcu = z2;
    }

    public GroundOverlayOptions() {
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int i2 = i;
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeIBinder(parcel2, 2, this.zzcx.zzb().asBinder(), false);
        SafeParcelWriter.writeParcelable(parcel2, 3, getLocation(), i2, false);
        SafeParcelWriter.writeFloat(parcel2, 4, getWidth());
        SafeParcelWriter.writeFloat(parcel2, 5, getHeight());
        SafeParcelWriter.writeParcelable(parcel2, 6, getBounds(), i2, false);
        SafeParcelWriter.writeFloat(parcel2, 7, getBearing());
        SafeParcelWriter.writeFloat(parcel2, 8, getZIndex());
        SafeParcelWriter.writeBoolean(parcel2, 9, isVisible());
        SafeParcelWriter.writeFloat(parcel2, 10, getTransparency());
        SafeParcelWriter.writeFloat(parcel2, 11, getAnchorU());
        SafeParcelWriter.writeFloat(parcel2, 12, getAnchorV());
        SafeParcelWriter.writeBoolean(parcel2, 13, isClickable());
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }

    public final GroundOverlayOptions image(@NonNull BitmapDescriptor bitmapDescriptor) {
        BitmapDescriptor bitmapDescriptor2 = bitmapDescriptor;
        Object checkNotNull = Preconditions.checkNotNull(bitmapDescriptor2, "imageDescriptor must not be null");
        this.zzcx = bitmapDescriptor2;
        return this;
    }

    public final GroundOverlayOptions anchor(float f, float f2) {
        this.zzdb = f;
        this.zzdc = f2;
        return this;
    }

    public final GroundOverlayOptions position(LatLng latLng, float f) {
        LatLng latLng2 = latLng;
        float f2 = f;
        Preconditions.checkState(this.zzcz == null, "Position has already been set using positionFromBounds");
        Preconditions.checkArgument(latLng2 != null, "Location must be specified");
        Preconditions.checkArgument(f2 >= 0.0f, "Width must be non-negative");
        return zza(latLng2, f2, -1.0f);
    }

    public final GroundOverlayOptions position(LatLng latLng, float f, float f2) {
        LatLng latLng2 = latLng;
        float f3 = f;
        float f4 = f2;
        Preconditions.checkState(this.zzcz == null, "Position has already been set using positionFromBounds");
        Preconditions.checkArgument(latLng2 != null, "Location must be specified");
        Preconditions.checkArgument(f3 >= 0.0f, "Width must be non-negative");
        Preconditions.checkArgument(f4 >= 0.0f, "Height must be non-negative");
        return zza(latLng2, f3, f4);
    }

    private final GroundOverlayOptions zza(LatLng latLng, float f, float f2) {
        this.zzcy = latLng;
        this.width = f;
        this.height = f2;
        return this;
    }

    public final GroundOverlayOptions positionFromBounds(LatLngBounds latLngBounds) {
        StringBuilder sb;
        LatLngBounds latLngBounds2 = latLngBounds;
        boolean z = this.zzcy == null;
        String valueOf = String.valueOf(this.zzcy);
        new StringBuilder(46 + String.valueOf(valueOf).length());
        Preconditions.checkState(z, sb.append("Position has already been set using position: ").append(valueOf).toString());
        this.zzcz = latLngBounds2;
        return this;
    }

    public final GroundOverlayOptions bearing(float f) {
        this.bearing = ((f % 360.0f) + 360.0f) % 360.0f;
        return this;
    }

    public final GroundOverlayOptions zIndex(float f) {
        this.zzcs = f;
        return this;
    }

    public final GroundOverlayOptions visible(boolean z) {
        this.zzct = z;
        return this;
    }

    public final GroundOverlayOptions transparency(float f) {
        float f2 = f;
        Preconditions.checkArgument(f2 >= 0.0f && f2 <= 1.0f, "Transparency must be in the range [0..1]");
        this.zzda = f2;
        return this;
    }

    public final GroundOverlayOptions clickable(boolean z) {
        this.zzcu = z;
        return this;
    }

    public final BitmapDescriptor getImage() {
        return this.zzcx;
    }

    public final LatLng getLocation() {
        return this.zzcy;
    }

    public final float getWidth() {
        return this.width;
    }

    public final float getHeight() {
        return this.height;
    }

    public final LatLngBounds getBounds() {
        return this.zzcz;
    }

    public final float getBearing() {
        return this.bearing;
    }

    public final float getZIndex() {
        return this.zzcs;
    }

    public final float getTransparency() {
        return this.zzda;
    }

    public final float getAnchorU() {
        return this.zzdb;
    }

    public final float getAnchorV() {
        return this.zzdc;
    }

    public final boolean isVisible() {
        return this.zzct;
    }

    public final boolean isClickable() {
        return this.zzcu;
    }

    static {
        Parcelable.Creator<GroundOverlayOptions> creator;
        new zzd();
        CREATOR = creator;
    }
}

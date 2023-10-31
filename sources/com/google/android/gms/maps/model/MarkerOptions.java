package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.dynamic.IObjectWrapper;

@SafeParcelable.Class(creator = "MarkerOptionsCreator")
@SafeParcelable.Reserved({1})
public final class MarkerOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<MarkerOptions> CREATOR;
    @SafeParcelable.Field(defaultValue = "1.0f", getter = "getAlpha", mo25277id = 14)
    private float alpha = 1.0f;
    @SafeParcelable.Field(getter = "getPosition", mo25277id = 2)
    private LatLng position;
    @SafeParcelable.Field(getter = "getZIndex", mo25277id = 15)
    private float zzcs;
    @SafeParcelable.Field(getter = "isVisible", mo25277id = 9)
    private boolean zzct = true;
    @SafeParcelable.Field(getter = "getAnchorU", mo25277id = 6)
    private float zzdb = 0.5f;
    @SafeParcelable.Field(getter = "getAnchorV", mo25277id = 7)
    private float zzdc = 1.0f;
    @SafeParcelable.Field(getter = "getTitle", mo25277id = 3)
    private String zzdn;
    @SafeParcelable.Field(getter = "getSnippet", mo25277id = 4)
    private String zzdo;
    @SafeParcelable.Field(getter = "getWrappedIconDescriptorImplBinder", mo25277id = 5, type = "android.os.IBinder")
    private BitmapDescriptor zzdp;
    @SafeParcelable.Field(getter = "isDraggable", mo25277id = 8)
    private boolean zzdq;
    @SafeParcelable.Field(getter = "isFlat", mo25277id = 10)
    private boolean zzdr = false;
    @SafeParcelable.Field(getter = "getRotation", mo25277id = 11)
    private float zzds = 0.0f;
    @SafeParcelable.Field(defaultValue = "0.5f", getter = "getInfoWindowAnchorU", mo25277id = 12)
    private float zzdt = 0.5f;
    @SafeParcelable.Field(getter = "getInfoWindowAnchorV", mo25277id = 13)
    private float zzdu = 0.0f;

    public MarkerOptions() {
    }

    @SafeParcelable.Constructor
    MarkerOptions(@SafeParcelable.Param(mo25280id = 2) LatLng latLng, @SafeParcelable.Param(mo25280id = 3) String str, @SafeParcelable.Param(mo25280id = 4) String str2, @SafeParcelable.Param(mo25280id = 5) IBinder iBinder, @SafeParcelable.Param(mo25280id = 6) float f, @SafeParcelable.Param(mo25280id = 7) float f2, @SafeParcelable.Param(mo25280id = 8) boolean z, @SafeParcelable.Param(mo25280id = 9) boolean z2, @SafeParcelable.Param(mo25280id = 10) boolean z3, @SafeParcelable.Param(mo25280id = 11) float f3, @SafeParcelable.Param(mo25280id = 12) float f4, @SafeParcelable.Param(mo25280id = 13) float f5, @SafeParcelable.Param(mo25280id = 14) float f6, @SafeParcelable.Param(mo25280id = 15) float f7) {
        BitmapDescriptor bitmapDescriptor;
        IBinder iBinder2 = iBinder;
        float f8 = f;
        float f9 = f2;
        boolean z4 = z;
        boolean z5 = z2;
        boolean z6 = z3;
        float f10 = f3;
        float f11 = f4;
        float f12 = f5;
        float f13 = f6;
        float f14 = f7;
        this.position = latLng;
        this.zzdn = str;
        this.zzdo = str2;
        if (iBinder2 == null) {
            this.zzdp = null;
        } else {
            new BitmapDescriptor(IObjectWrapper.Stub.asInterface(iBinder2));
            this.zzdp = bitmapDescriptor;
        }
        this.zzdb = f8;
        this.zzdc = f9;
        this.zzdq = z4;
        this.zzct = z5;
        this.zzdr = z6;
        this.zzds = f10;
        this.zzdt = f11;
        this.zzdu = f12;
        this.alpha = f13;
        this.zzcs = f14;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        IBinder asBinder;
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeParcelable(parcel2, 2, getPosition(), i, false);
        SafeParcelWriter.writeString(parcel2, 3, getTitle(), false);
        SafeParcelWriter.writeString(parcel2, 4, getSnippet(), false);
        Parcel parcel3 = parcel2;
        if (this.zzdp == null) {
            asBinder = null;
        } else {
            asBinder = this.zzdp.zzb().asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel3, 5, asBinder, false);
        SafeParcelWriter.writeFloat(parcel2, 6, getAnchorU());
        SafeParcelWriter.writeFloat(parcel2, 7, getAnchorV());
        SafeParcelWriter.writeBoolean(parcel2, 8, isDraggable());
        SafeParcelWriter.writeBoolean(parcel2, 9, isVisible());
        SafeParcelWriter.writeBoolean(parcel2, 10, isFlat());
        SafeParcelWriter.writeFloat(parcel2, 11, getRotation());
        SafeParcelWriter.writeFloat(parcel2, 12, getInfoWindowAnchorU());
        SafeParcelWriter.writeFloat(parcel2, 13, getInfoWindowAnchorV());
        SafeParcelWriter.writeFloat(parcel2, 14, getAlpha());
        SafeParcelWriter.writeFloat(parcel2, 15, getZIndex());
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }

    public final MarkerOptions position(@NonNull LatLng latLng) {
        Throwable th;
        LatLng latLng2 = latLng;
        if (latLng2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("latlng cannot be null - a position is required.");
            throw th2;
        }
        this.position = latLng2;
        return this;
    }

    public final MarkerOptions zIndex(float f) {
        this.zzcs = f;
        return this;
    }

    public final MarkerOptions icon(@Nullable BitmapDescriptor bitmapDescriptor) {
        this.zzdp = bitmapDescriptor;
        return this;
    }

    public final MarkerOptions anchor(float f, float f2) {
        this.zzdb = f;
        this.zzdc = f2;
        return this;
    }

    public final MarkerOptions infoWindowAnchor(float f, float f2) {
        this.zzdt = f;
        this.zzdu = f2;
        return this;
    }

    public final MarkerOptions title(@Nullable String str) {
        this.zzdn = str;
        return this;
    }

    public final MarkerOptions snippet(@Nullable String str) {
        this.zzdo = str;
        return this;
    }

    public final MarkerOptions draggable(boolean z) {
        this.zzdq = z;
        return this;
    }

    public final MarkerOptions visible(boolean z) {
        this.zzct = z;
        return this;
    }

    public final MarkerOptions flat(boolean z) {
        this.zzdr = z;
        return this;
    }

    public final MarkerOptions rotation(float f) {
        this.zzds = f;
        return this;
    }

    public final MarkerOptions alpha(float f) {
        this.alpha = f;
        return this;
    }

    public final LatLng getPosition() {
        return this.position;
    }

    public final String getTitle() {
        return this.zzdn;
    }

    public final String getSnippet() {
        return this.zzdo;
    }

    public final BitmapDescriptor getIcon() {
        return this.zzdp;
    }

    public final float getAnchorU() {
        return this.zzdb;
    }

    public final float getAnchorV() {
        return this.zzdc;
    }

    public final boolean isDraggable() {
        return this.zzdq;
    }

    public final boolean isVisible() {
        return this.zzct;
    }

    public final boolean isFlat() {
        return this.zzdr;
    }

    public final float getRotation() {
        return this.zzds;
    }

    public final float getInfoWindowAnchorU() {
        return this.zzdt;
    }

    public final float getInfoWindowAnchorV() {
        return this.zzdu;
    }

    public final float getAlpha() {
        return this.alpha;
    }

    public final float getZIndex() {
        return this.zzcs;
    }

    static {
        Parcelable.Creator<MarkerOptions> creator;
        new zzh();
        CREATOR = creator;
    }
}

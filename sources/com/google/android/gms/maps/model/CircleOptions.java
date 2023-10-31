package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

@SafeParcelable.Class(creator = "CircleOptionsCreator")
@SafeParcelable.Reserved({1})
public final class CircleOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<CircleOptions> CREATOR;
    @SafeParcelable.Field(getter = "getFillColor", mo25277id = 6)
    private int fillColor = 0;
    @SafeParcelable.Field(getter = "getStrokeColor", mo25277id = 5)
    private int strokeColor = -16777216;
    @SafeParcelable.Field(getter = "getCenter", mo25277id = 2)
    private LatLng zzcp = null;
    @SafeParcelable.Field(getter = "getRadius", mo25277id = 3)
    private double zzcq = 0.0d;
    @SafeParcelable.Field(getter = "getStrokeWidth", mo25277id = 4)
    private float zzcr = 10.0f;
    @SafeParcelable.Field(getter = "getZIndex", mo25277id = 7)
    private float zzcs = 0.0f;
    @SafeParcelable.Field(getter = "isVisible", mo25277id = 8)
    private boolean zzct = true;
    @SafeParcelable.Field(getter = "isClickable", mo25277id = 9)
    private boolean zzcu = false;
    @Nullable
    @SafeParcelable.Field(getter = "getStrokePattern", mo25277id = 10)
    private List<PatternItem> zzcv = null;

    public CircleOptions() {
    }

    @SafeParcelable.Constructor
    CircleOptions(@SafeParcelable.Param(mo25280id = 2) LatLng latLng, @SafeParcelable.Param(mo25280id = 3) double d, @SafeParcelable.Param(mo25280id = 4) float f, @SafeParcelable.Param(mo25280id = 5) int i, @SafeParcelable.Param(mo25280id = 6) int i2, @SafeParcelable.Param(mo25280id = 7) float f2, @SafeParcelable.Param(mo25280id = 8) boolean z, @SafeParcelable.Param(mo25280id = 9) boolean z2, @Nullable @SafeParcelable.Param(mo25280id = 10) List<PatternItem> list) {
        this.zzcp = latLng;
        this.zzcq = d;
        this.zzcr = f;
        this.strokeColor = i;
        this.fillColor = i2;
        this.zzcs = f2;
        this.zzct = z;
        this.zzcu = z2;
        this.zzcv = list;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeParcelable(parcel2, 2, getCenter(), i, false);
        SafeParcelWriter.writeDouble(parcel2, 3, getRadius());
        SafeParcelWriter.writeFloat(parcel2, 4, getStrokeWidth());
        SafeParcelWriter.writeInt(parcel2, 5, getStrokeColor());
        SafeParcelWriter.writeInt(parcel2, 6, getFillColor());
        SafeParcelWriter.writeFloat(parcel2, 7, getZIndex());
        SafeParcelWriter.writeBoolean(parcel2, 8, isVisible());
        SafeParcelWriter.writeBoolean(parcel2, 9, isClickable());
        SafeParcelWriter.writeTypedList(parcel2, 10, getStrokePattern(), false);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }

    public final CircleOptions center(LatLng latLng) {
        this.zzcp = latLng;
        return this;
    }

    public final CircleOptions radius(double d) {
        this.zzcq = d;
        return this;
    }

    public final CircleOptions strokeWidth(float f) {
        this.zzcr = f;
        return this;
    }

    public final CircleOptions strokeColor(int i) {
        this.strokeColor = i;
        return this;
    }

    public final CircleOptions strokePattern(@Nullable List<PatternItem> list) {
        this.zzcv = list;
        return this;
    }

    public final CircleOptions fillColor(int i) {
        this.fillColor = i;
        return this;
    }

    public final CircleOptions zIndex(float f) {
        this.zzcs = f;
        return this;
    }

    public final CircleOptions visible(boolean z) {
        this.zzct = z;
        return this;
    }

    public final CircleOptions clickable(boolean z) {
        this.zzcu = z;
        return this;
    }

    public final LatLng getCenter() {
        return this.zzcp;
    }

    public final double getRadius() {
        return this.zzcq;
    }

    public final float getStrokeWidth() {
        return this.zzcr;
    }

    public final int getStrokeColor() {
        return this.strokeColor;
    }

    @Nullable
    public final List<PatternItem> getStrokePattern() {
        return this.zzcv;
    }

    public final int getFillColor() {
        return this.fillColor;
    }

    public final float getZIndex() {
        return this.zzcs;
    }

    public final boolean isVisible() {
        return this.zzct;
    }

    public final boolean isClickable() {
        return this.zzcu;
    }

    static {
        Parcelable.Creator<CircleOptions> creator;
        new zzc();
        CREATOR = creator;
    }
}

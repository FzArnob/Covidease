package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SafeParcelable.Class(creator = "PolygonOptionsCreator")
@SafeParcelable.Reserved({1})
public final class PolygonOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<PolygonOptions> CREATOR;
    @SafeParcelable.Field(getter = "getFillColor", mo25277id = 6)
    private int fillColor = 0;
    @SafeParcelable.Field(getter = "getStrokeColor", mo25277id = 5)
    private int strokeColor = -16777216;
    @SafeParcelable.Field(getter = "getStrokeWidth", mo25277id = 4)
    private float zzcr = 10.0f;
    @SafeParcelable.Field(getter = "getZIndex", mo25277id = 7)
    private float zzcs = 0.0f;
    @SafeParcelable.Field(getter = "isVisible", mo25277id = 8)
    private boolean zzct = true;
    @SafeParcelable.Field(getter = "isClickable", mo25277id = 10)
    private boolean zzcu = false;
    @Nullable
    @SafeParcelable.Field(getter = "getStrokePattern", mo25277id = 12)
    private List<PatternItem> zzcv = null;
    @SafeParcelable.Field(getter = "getPoints", mo25277id = 2)
    private final List<LatLng> zzdx;
    @SafeParcelable.Field(getter = "getHolesForParcel", mo25277id = 3, type = "java.util.List")
    private final List<List<LatLng>> zzdy;
    @SafeParcelable.Field(getter = "isGeodesic", mo25277id = 9)
    private boolean zzdz = false;
    @SafeParcelable.Field(getter = "getStrokeJointType", mo25277id = 11)
    private int zzea = 0;

    public PolygonOptions() {
        List<LatLng> list;
        List<List<LatLng>> list2;
        new ArrayList();
        this.zzdx = list;
        new ArrayList();
        this.zzdy = list2;
    }

    @SafeParcelable.Constructor
    PolygonOptions(@SafeParcelable.Param(mo25280id = 2) List<LatLng> list, @SafeParcelable.Param(mo25280id = 3) List list2, @SafeParcelable.Param(mo25280id = 4) float f, @SafeParcelable.Param(mo25280id = 5) int i, @SafeParcelable.Param(mo25280id = 6) int i2, @SafeParcelable.Param(mo25280id = 7) float f2, @SafeParcelable.Param(mo25280id = 8) boolean z, @SafeParcelable.Param(mo25280id = 9) boolean z2, @SafeParcelable.Param(mo25280id = 10) boolean z3, @SafeParcelable.Param(mo25280id = 11) int i3, @Nullable @SafeParcelable.Param(mo25280id = 12) List<PatternItem> list3) {
        this.zzdx = list;
        this.zzdy = list2;
        this.zzcr = f;
        this.strokeColor = i;
        this.fillColor = i2;
        this.zzcs = f2;
        this.zzct = z;
        this.zzdz = z2;
        this.zzcu = z3;
        this.zzea = i3;
        this.zzcv = list3;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int i2 = i;
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeTypedList(parcel2, 2, getPoints(), false);
        SafeParcelWriter.writeList(parcel2, 3, this.zzdy, false);
        SafeParcelWriter.writeFloat(parcel2, 4, getStrokeWidth());
        SafeParcelWriter.writeInt(parcel2, 5, getStrokeColor());
        SafeParcelWriter.writeInt(parcel2, 6, getFillColor());
        SafeParcelWriter.writeFloat(parcel2, 7, getZIndex());
        SafeParcelWriter.writeBoolean(parcel2, 8, isVisible());
        SafeParcelWriter.writeBoolean(parcel2, 9, isGeodesic());
        SafeParcelWriter.writeBoolean(parcel2, 10, isClickable());
        SafeParcelWriter.writeInt(parcel2, 11, getStrokeJointType());
        SafeParcelWriter.writeTypedList(parcel2, 12, getStrokePattern(), false);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }

    public final PolygonOptions add(LatLng latLng) {
        boolean add = this.zzdx.add(latLng);
        return this;
    }

    public final PolygonOptions add(LatLng... latLngArr) {
        boolean addAll = this.zzdx.addAll(Arrays.asList(latLngArr));
        return this;
    }

    public final PolygonOptions addAll(Iterable<LatLng> iterable) {
        for (LatLng add : iterable) {
            boolean add2 = this.zzdx.add(add);
        }
        return this;
    }

    public final PolygonOptions addHole(Iterable<LatLng> iterable) {
        ArrayList arrayList;
        new ArrayList();
        ArrayList arrayList2 = arrayList;
        for (LatLng add : iterable) {
            boolean add2 = arrayList2.add(add);
        }
        boolean add3 = this.zzdy.add(arrayList2);
        return this;
    }

    public final PolygonOptions strokeWidth(float f) {
        this.zzcr = f;
        return this;
    }

    public final PolygonOptions strokeColor(int i) {
        this.strokeColor = i;
        return this;
    }

    public final PolygonOptions strokeJointType(int i) {
        this.zzea = i;
        return this;
    }

    public final PolygonOptions strokePattern(@Nullable List<PatternItem> list) {
        this.zzcv = list;
        return this;
    }

    public final PolygonOptions fillColor(int i) {
        this.fillColor = i;
        return this;
    }

    public final PolygonOptions zIndex(float f) {
        this.zzcs = f;
        return this;
    }

    public final PolygonOptions visible(boolean z) {
        this.zzct = z;
        return this;
    }

    public final PolygonOptions geodesic(boolean z) {
        this.zzdz = z;
        return this;
    }

    public final PolygonOptions clickable(boolean z) {
        this.zzcu = z;
        return this;
    }

    public final List<LatLng> getPoints() {
        return this.zzdx;
    }

    public final List<List<LatLng>> getHoles() {
        return this.zzdy;
    }

    public final float getStrokeWidth() {
        return this.zzcr;
    }

    public final int getStrokeColor() {
        return this.strokeColor;
    }

    public final int getStrokeJointType() {
        return this.zzea;
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

    public final boolean isGeodesic() {
        return this.zzdz;
    }

    public final boolean isClickable() {
        return this.zzcu;
    }

    static {
        Parcelable.Creator<PolygonOptions> creator;
        new zzk();
        CREATOR = creator;
    }
}

package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SafeParcelable.Class(creator = "PolylineOptionsCreator")
@SafeParcelable.Reserved({1})
public final class PolylineOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<PolylineOptions> CREATOR;
    @SafeParcelable.Field(getter = "getColor", mo25277id = 4)
    private int color = -16777216;
    @SafeParcelable.Field(getter = "getWidth", mo25277id = 3)
    private float width = 10.0f;
    @SafeParcelable.Field(getter = "getZIndex", mo25277id = 5)
    private float zzcs = 0.0f;
    @SafeParcelable.Field(getter = "isVisible", mo25277id = 6)
    private boolean zzct = true;
    @SafeParcelable.Field(getter = "isClickable", mo25277id = 8)
    private boolean zzcu = false;
    @SafeParcelable.Field(getter = "getPoints", mo25277id = 2)
    private final List<LatLng> zzdx;
    @SafeParcelable.Field(getter = "isGeodesic", mo25277id = 7)
    private boolean zzdz = false;
    @SafeParcelable.Field(getter = "getStartCap", mo25277id = 9)
    @NonNull
    private Cap zzec;
    @SafeParcelable.Field(getter = "getEndCap", mo25277id = 10)
    @NonNull
    private Cap zzed;
    @SafeParcelable.Field(getter = "getJointType", mo25277id = 11)
    private int zzee;
    @Nullable
    @SafeParcelable.Field(getter = "getPattern", mo25277id = 12)
    private List<PatternItem> zzef;

    public PolylineOptions() {
        Cap cap;
        Cap cap2;
        List<LatLng> list;
        new ButtCap();
        this.zzec = cap;
        new ButtCap();
        this.zzed = cap2;
        this.zzee = 0;
        this.zzef = null;
        new ArrayList();
        this.zzdx = list;
    }

    @SafeParcelable.Constructor
    PolylineOptions(@SafeParcelable.Param(mo25280id = 2) List list, @SafeParcelable.Param(mo25280id = 3) float f, @SafeParcelable.Param(mo25280id = 4) int i, @SafeParcelable.Param(mo25280id = 5) float f2, @SafeParcelable.Param(mo25280id = 6) boolean z, @SafeParcelable.Param(mo25280id = 7) boolean z2, @SafeParcelable.Param(mo25280id = 8) boolean z3, @Nullable @SafeParcelable.Param(mo25280id = 9) Cap cap, @Nullable @SafeParcelable.Param(mo25280id = 10) Cap cap2, @SafeParcelable.Param(mo25280id = 11) int i2, @Nullable @SafeParcelable.Param(mo25280id = 12) List<PatternItem> list2) {
        Cap cap3;
        Cap cap4;
        Cap cap5 = cap;
        Cap cap6 = cap2;
        int i3 = i2;
        List<PatternItem> list3 = list2;
        new ButtCap();
        this.zzec = cap3;
        new ButtCap();
        this.zzed = cap4;
        this.zzee = 0;
        this.zzef = null;
        this.zzdx = list;
        this.width = f;
        this.color = i;
        this.zzcs = f2;
        this.zzct = z;
        this.zzdz = z2;
        this.zzcu = z3;
        if (cap5 != null) {
            this.zzec = cap5;
        }
        if (cap6 != null) {
            this.zzed = cap6;
        }
        this.zzee = i3;
        this.zzef = list3;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int i2 = i;
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeTypedList(parcel2, 2, getPoints(), false);
        SafeParcelWriter.writeFloat(parcel2, 3, getWidth());
        SafeParcelWriter.writeInt(parcel2, 4, getColor());
        SafeParcelWriter.writeFloat(parcel2, 5, getZIndex());
        SafeParcelWriter.writeBoolean(parcel2, 6, isVisible());
        SafeParcelWriter.writeBoolean(parcel2, 7, isGeodesic());
        SafeParcelWriter.writeBoolean(parcel2, 8, isClickable());
        SafeParcelWriter.writeParcelable(parcel2, 9, getStartCap(), i2, false);
        SafeParcelWriter.writeParcelable(parcel2, 10, getEndCap(), i2, false);
        SafeParcelWriter.writeInt(parcel2, 11, getJointType());
        SafeParcelWriter.writeTypedList(parcel2, 12, getPattern(), false);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }

    public final PolylineOptions add(LatLng latLng) {
        boolean add = this.zzdx.add(latLng);
        return this;
    }

    public final PolylineOptions add(LatLng... latLngArr) {
        boolean addAll = this.zzdx.addAll(Arrays.asList(latLngArr));
        return this;
    }

    public final PolylineOptions addAll(Iterable<LatLng> iterable) {
        for (LatLng add : iterable) {
            boolean add2 = this.zzdx.add(add);
        }
        return this;
    }

    public final PolylineOptions width(float f) {
        this.width = f;
        return this;
    }

    public final PolylineOptions color(int i) {
        this.color = i;
        return this;
    }

    public final PolylineOptions startCap(@NonNull Cap cap) {
        this.zzec = (Cap) Preconditions.checkNotNull(cap, "startCap must not be null");
        return this;
    }

    public final PolylineOptions endCap(@NonNull Cap cap) {
        this.zzed = (Cap) Preconditions.checkNotNull(cap, "endCap must not be null");
        return this;
    }

    public final PolylineOptions jointType(int i) {
        this.zzee = i;
        return this;
    }

    public final PolylineOptions pattern(@Nullable List<PatternItem> list) {
        this.zzef = list;
        return this;
    }

    public final PolylineOptions zIndex(float f) {
        this.zzcs = f;
        return this;
    }

    public final PolylineOptions visible(boolean z) {
        this.zzct = z;
        return this;
    }

    public final PolylineOptions geodesic(boolean z) {
        this.zzdz = z;
        return this;
    }

    public final PolylineOptions clickable(boolean z) {
        this.zzcu = z;
        return this;
    }

    public final List<LatLng> getPoints() {
        return this.zzdx;
    }

    public final float getWidth() {
        return this.width;
    }

    public final int getColor() {
        return this.color;
    }

    @NonNull
    public final Cap getStartCap() {
        return this.zzec;
    }

    @NonNull
    public final Cap getEndCap() {
        return this.zzed;
    }

    public final int getJointType() {
        return this.zzee;
    }

    @Nullable
    public final List<PatternItem> getPattern() {
        return this.zzef;
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
        Parcelable.Creator<PolylineOptions> creator;
        new zzl();
        CREATOR = creator;
    }
}

package com.google.android.gms.location;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;

@SafeParcelable.Class(creator = "LocationSettingsStatesCreator")
@SafeParcelable.Reserved({1000})
public final class LocationSettingsStates extends AbstractSafeParcelable {
    public static final Parcelable.Creator<LocationSettingsStates> CREATOR;
    @SafeParcelable.Field(getter = "isGpsUsable", mo25277id = 1)
    private final boolean zzbn;
    @SafeParcelable.Field(getter = "isNetworkLocationUsable", mo25277id = 2)
    private final boolean zzbo;
    @SafeParcelable.Field(getter = "isBleUsable", mo25277id = 3)
    private final boolean zzbp;
    @SafeParcelable.Field(getter = "isGpsPresent", mo25277id = 4)
    private final boolean zzbq;
    @SafeParcelable.Field(getter = "isNetworkLocationPresent", mo25277id = 5)
    private final boolean zzbr;
    @SafeParcelable.Field(getter = "isBlePresent", mo25277id = 6)
    private final boolean zzbs;

    static {
        Parcelable.Creator<LocationSettingsStates> creator;
        new zzai();
        CREATOR = creator;
    }

    @SafeParcelable.Constructor
    public LocationSettingsStates(@SafeParcelable.Param(mo25280id = 1) boolean z, @SafeParcelable.Param(mo25280id = 2) boolean z2, @SafeParcelable.Param(mo25280id = 3) boolean z3, @SafeParcelable.Param(mo25280id = 4) boolean z4, @SafeParcelable.Param(mo25280id = 5) boolean z5, @SafeParcelable.Param(mo25280id = 6) boolean z6) {
        this.zzbn = z;
        this.zzbo = z2;
        this.zzbp = z3;
        this.zzbq = z4;
        this.zzbr = z5;
        this.zzbs = z6;
    }

    public static LocationSettingsStates fromIntent(Intent intent) {
        return (LocationSettingsStates) SafeParcelableSerializer.deserializeFromIntentExtra(intent, "com.google.android.gms.location.LOCATION_SETTINGS_STATES", CREATOR);
    }

    public final boolean isBlePresent() {
        return this.zzbs;
    }

    public final boolean isBleUsable() {
        return this.zzbp;
    }

    public final boolean isGpsPresent() {
        return this.zzbq;
    }

    public final boolean isGpsUsable() {
        return this.zzbn;
    }

    public final boolean isLocationPresent() {
        return this.zzbq || this.zzbr;
    }

    public final boolean isLocationUsable() {
        return this.zzbn || this.zzbo;
    }

    public final boolean isNetworkLocationPresent() {
        return this.zzbr;
    }

    public final boolean isNetworkLocationUsable() {
        return this.zzbo;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int i2 = i;
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeBoolean(parcel2, 1, isGpsUsable());
        SafeParcelWriter.writeBoolean(parcel2, 2, isNetworkLocationUsable());
        SafeParcelWriter.writeBoolean(parcel2, 3, isBleUsable());
        SafeParcelWriter.writeBoolean(parcel2, 4, isGpsPresent());
        SafeParcelWriter.writeBoolean(parcel2, 5, isNetworkLocationPresent());
        SafeParcelWriter.writeBoolean(parcel2, 6, isBlePresent());
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }
}

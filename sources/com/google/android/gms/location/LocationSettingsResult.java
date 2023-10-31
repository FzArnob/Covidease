package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "LocationSettingsResultCreator")
@SafeParcelable.Reserved({1000})
public final class LocationSettingsResult extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator<LocationSettingsResult> CREATOR;
    @SafeParcelable.Field(getter = "getStatus", mo25277id = 1)
    private final Status zzbl;
    @SafeParcelable.Field(getter = "getLocationSettingsStates", mo25277id = 2)
    private final LocationSettingsStates zzbm;

    static {
        Parcelable.Creator<LocationSettingsResult> creator;
        new zzah();
        CREATOR = creator;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public LocationSettingsResult(Status status) {
        this(status, (LocationSettingsStates) null);
    }

    @SafeParcelable.Constructor
    public LocationSettingsResult(@SafeParcelable.Param(mo25280id = 1) Status status, @SafeParcelable.Param(mo25280id = 2) LocationSettingsStates locationSettingsStates) {
        this.zzbl = status;
        this.zzbm = locationSettingsStates;
    }

    public final LocationSettingsStates getLocationSettingsStates() {
        return this.zzbm;
    }

    public final Status getStatus() {
        return this.zzbl;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int i2 = i;
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeParcelable(parcel2, 1, getStatus(), i2, false);
        SafeParcelWriter.writeParcelable(parcel2, 2, getLocationSettingsStates(), i2, false);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }
}

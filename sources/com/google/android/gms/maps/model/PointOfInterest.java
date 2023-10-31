package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "PointOfInterestCreator")
@SafeParcelable.Reserved({1})
public final class PointOfInterest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<PointOfInterest> CREATOR;
    @SafeParcelable.Field(mo25277id = 2)
    public final LatLng latLng;
    @SafeParcelable.Field(mo25277id = 4)
    public final String name;
    @SafeParcelable.Field(mo25277id = 3)
    public final String placeId;

    @SafeParcelable.Constructor
    public PointOfInterest(@SafeParcelable.Param(mo25280id = 2) LatLng latLng2, @SafeParcelable.Param(mo25280id = 3) String str, @SafeParcelable.Param(mo25280id = 4) String str2) {
        this.latLng = latLng2;
        this.placeId = str;
        this.name = str2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeParcelable(parcel2, 2, this.latLng, i, false);
        SafeParcelWriter.writeString(parcel2, 3, this.placeId, false);
        SafeParcelWriter.writeString(parcel2, 4, this.name, false);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }

    static {
        Parcelable.Creator<PointOfInterest> creator;
        new zzj();
        CREATOR = creator;
    }
}

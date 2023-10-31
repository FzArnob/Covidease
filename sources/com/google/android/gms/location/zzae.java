package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "LocationSettingsConfigurationCreator")
@SafeParcelable.Reserved({3, 4, 1000})
public final class zzae extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzae> CREATOR;
    @SafeParcelable.Field(defaultValue = "", getter = "getJustificationText", mo25277id = 1)
    private final String zzbd;
    @SafeParcelable.Field(defaultValue = "", getter = "getExperimentId", mo25277id = 2)
    private final String zzbe;
    @SafeParcelable.Field(defaultValue = "", getter = "getTitleText", mo25277id = 5)
    private final String zzbf;

    static {
        Parcelable.Creator<zzae> creator;
        new zzaf();
        CREATOR = creator;
    }

    @SafeParcelable.Constructor
    zzae(@SafeParcelable.Param(mo25280id = 5) String str, @SafeParcelable.Param(mo25280id = 1) String str2, @SafeParcelable.Param(mo25280id = 2) String str3) {
        this.zzbf = str;
        this.zzbd = str2;
        this.zzbe = str3;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int i2 = i;
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeString(parcel2, 1, this.zzbd, false);
        SafeParcelWriter.writeString(parcel2, 2, this.zzbe, false);
        SafeParcelWriter.writeString(parcel2, 5, this.zzbf, false);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }
}

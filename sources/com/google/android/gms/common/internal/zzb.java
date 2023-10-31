package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "ConnectionInfoCreator")
public final class zzb extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzb> CREATOR;
    @SafeParcelable.Field(mo25277id = 1)
    Bundle zzda;
    @SafeParcelable.Field(mo25277id = 2)
    Feature[] zzdb;

    @SafeParcelable.Constructor
    zzb(@SafeParcelable.Param(mo25280id = 1) Bundle bundle, @SafeParcelable.Param(mo25280id = 2) Feature[] featureArr) {
        this.zzda = bundle;
        this.zzdb = featureArr;
    }

    public zzb() {
    }

    public final void writeToParcel(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeBundle(parcel2, 1, this.zzda, false);
        SafeParcelWriter.writeTypedArray(parcel2, 2, this.zzdb, i, false);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }

    static {
        Parcelable.Creator<zzb> creator;
        new zzc();
        CREATOR = creator;
    }
}

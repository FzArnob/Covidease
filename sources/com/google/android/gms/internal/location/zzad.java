package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "FusedLocationProviderResultCreator")
@SafeParcelable.Reserved({1000})
public final class zzad extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator<zzad> CREATOR;
    private static final zzad zzcr;
    @SafeParcelable.Field(getter = "getStatus", mo25277id = 1)
    private final Status zzbl;

    static {
        zzad zzad;
        Parcelable.Creator<zzad> creator;
        new zzad(Status.RESULT_SUCCESS);
        zzcr = zzad;
        new zzae();
        CREATOR = creator;
    }

    @SafeParcelable.Constructor
    public zzad(@SafeParcelable.Param(mo25280id = 1) Status status) {
        this.zzbl = status;
    }

    public final Status getStatus() {
        return this.zzbl;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeParcelable(parcel2, 1, getStatus(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }
}

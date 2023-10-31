package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "SignInRequestCreator")
public final class zah extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zah> CREATOR;
    @SafeParcelable.VersionField(mo25283id = 1)
    private final int zalf;
    @SafeParcelable.Field(getter = "getResolveAccountRequest", mo25277id = 2)
    private final ResolveAccountRequest zasd;

    @SafeParcelable.Constructor
    zah(@SafeParcelable.Param(mo25280id = 1) int i, @SafeParcelable.Param(mo25280id = 2) ResolveAccountRequest resolveAccountRequest) {
        this.zalf = i;
        this.zasd = resolveAccountRequest;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public zah(ResolveAccountRequest resolveAccountRequest) {
        this(1, resolveAccountRequest);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeInt(parcel2, 1, this.zalf);
        SafeParcelWriter.writeParcelable(parcel2, 2, this.zasd, i, false);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }

    static {
        Parcelable.Creator<zah> creator;
        new zai();
        CREATOR = creator;
    }
}

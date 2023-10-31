package com.google.android.gms.signin.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "AuthAccountResultCreator")
public final class zaa extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator<zaa> CREATOR;
    @SafeParcelable.VersionField(mo25283id = 1)
    private final int zalf;
    @SafeParcelable.Field(getter = "getConnectionResultCode", mo25277id = 2)
    private int zarz;
    @SafeParcelable.Field(getter = "getRawAuthResolutionIntent", mo25277id = 3)
    private Intent zasa;

    @SafeParcelable.Constructor
    zaa(@SafeParcelable.Param(mo25280id = 1) int i, @SafeParcelable.Param(mo25280id = 2) int i2, @SafeParcelable.Param(mo25280id = 3) Intent intent) {
        this.zalf = i;
        this.zarz = i2;
        this.zasa = intent;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public zaa() {
        this(0, (Intent) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    private zaa(int i, Intent intent) {
        this(2, 0, (Intent) null);
        int i2 = i;
        Intent intent2 = intent;
    }

    public final Status getStatus() {
        if (this.zarz == 0) {
            return Status.RESULT_SUCCESS;
        }
        return Status.RESULT_CANCELED;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeInt(parcel2, 1, this.zalf);
        SafeParcelWriter.writeInt(parcel2, 2, this.zarz);
        SafeParcelWriter.writeParcelable(parcel2, 3, this.zasa, i, false);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }

    static {
        Parcelable.Creator<zaa> creator;
        new zab();
        CREATOR = creator;
    }
}

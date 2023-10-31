package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "SignInResponseCreator")
public final class zaj extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zaj> CREATOR;
    @SafeParcelable.Field(getter = "getConnectionResult", mo25277id = 2)
    private final ConnectionResult zadi;
    @SafeParcelable.VersionField(mo25283id = 1)
    private final int zalf;
    @SafeParcelable.Field(getter = "getResolveAccountResponse", mo25277id = 3)
    private final ResolveAccountResponse zase;

    @SafeParcelable.Constructor
    zaj(@SafeParcelable.Param(mo25280id = 1) int i, @SafeParcelable.Param(mo25280id = 2) ConnectionResult connectionResult, @SafeParcelable.Param(mo25280id = 3) ResolveAccountResponse resolveAccountResponse) {
        this.zalf = i;
        this.zadi = connectionResult;
        this.zase = resolveAccountResponse;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zaj(int r9) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r0
            com.google.android.gms.common.ConnectionResult r3 = new com.google.android.gms.common.ConnectionResult
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = 8
            r6 = 0
            r4.<init>(r5, r6)
            r4 = 0
            r2.<init>(r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.signin.internal.zaj.<init>(int):void");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    private zaj(ConnectionResult connectionResult, ResolveAccountResponse resolveAccountResponse) {
        this(1, connectionResult, (ResolveAccountResponse) null);
        ResolveAccountResponse resolveAccountResponse2 = resolveAccountResponse;
    }

    public final ConnectionResult getConnectionResult() {
        return this.zadi;
    }

    public final ResolveAccountResponse zacx() {
        return this.zase;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int i2 = i;
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeInt(parcel2, 1, this.zalf);
        SafeParcelWriter.writeParcelable(parcel2, 2, this.zadi, i2, false);
        SafeParcelWriter.writeParcelable(parcel2, 3, this.zase, i2, false);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }

    static {
        Parcelable.Creator<zaj> creator;
        new zak();
        CREATOR = creator;
    }
}

package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "ResolveAccountResponseCreator")
public class ResolveAccountResponse extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ResolveAccountResponse> CREATOR;
    @SafeParcelable.Field(getter = "getConnectionResult", mo25277id = 3)
    private ConnectionResult zadi;
    @SafeParcelable.Field(getter = "getSaveDefaultAccount", mo25277id = 4)
    private boolean zagg;
    @SafeParcelable.VersionField(mo25283id = 1)
    private final int zalf;
    @SafeParcelable.Field(mo25277id = 2)
    private IBinder zanx;
    @SafeParcelable.Field(getter = "isFromCrossClientAuth", mo25277id = 5)
    private boolean zapc;

    @SafeParcelable.Constructor
    ResolveAccountResponse(@SafeParcelable.Param(mo25280id = 1) int i, @SafeParcelable.Param(mo25280id = 2) IBinder iBinder, @SafeParcelable.Param(mo25280id = 3) ConnectionResult connectionResult, @SafeParcelable.Param(mo25280id = 4) boolean z, @SafeParcelable.Param(mo25280id = 5) boolean z2) {
        this.zalf = i;
        this.zanx = iBinder;
        this.zadi = connectionResult;
        this.zagg = z;
        this.zapc = z2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ResolveAccountResponse(ConnectionResult connectionResult) {
        this(1, (IBinder) null, connectionResult, false, false);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ResolveAccountResponse(int r9) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r0
            com.google.android.gms.common.ConnectionResult r3 = new com.google.android.gms.common.ConnectionResult
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r1
            r6 = 0
            r4.<init>(r5, r6)
            r2.<init>((com.google.android.gms.common.ConnectionResult) r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.ResolveAccountResponse.<init>(int):void");
    }

    public IAccountAccessor getAccountAccessor() {
        return IAccountAccessor.Stub.asInterface(this.zanx);
    }

    public ResolveAccountResponse setAccountAccessor(IAccountAccessor iAccountAccessor) {
        IAccountAccessor iAccountAccessor2 = iAccountAccessor;
        this.zanx = iAccountAccessor2 == null ? null : iAccountAccessor2.asBinder();
        return this;
    }

    public ConnectionResult getConnectionResult() {
        return this.zadi;
    }

    public boolean getSaveDefaultAccount() {
        return this.zagg;
    }

    public ResolveAccountResponse setSaveDefaultAccount(boolean z) {
        this.zagg = z;
        return this;
    }

    public boolean isFromCrossClientAuth() {
        return this.zapc;
    }

    public ResolveAccountResponse setIsFromCrossClientAuth(boolean z) {
        this.zapc = z;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeInt(parcel2, 1, this.zalf);
        SafeParcelWriter.writeIBinder(parcel2, 2, this.zanx, false);
        SafeParcelWriter.writeParcelable(parcel2, 3, getConnectionResult(), i, false);
        SafeParcelWriter.writeBoolean(parcel2, 4, getSaveDefaultAccount());
        SafeParcelWriter.writeBoolean(parcel2, 5, isFromCrossClientAuth());
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r6) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r3 = r0
            r4 = r1
            if (r3 != r4) goto L_0x0009
            r3 = 1
            r0 = r3
        L_0x0008:
            return r0
        L_0x0009:
            r3 = r1
            boolean r3 = r3 instanceof com.google.android.gms.common.internal.ResolveAccountResponse
            if (r3 != 0) goto L_0x0011
            r3 = 0
            r0 = r3
            goto L_0x0008
        L_0x0011:
            r3 = r1
            com.google.android.gms.common.internal.ResolveAccountResponse r3 = (com.google.android.gms.common.internal.ResolveAccountResponse) r3
            r2 = r3
            r3 = r0
            com.google.android.gms.common.ConnectionResult r3 = r3.zadi
            r4 = r2
            com.google.android.gms.common.ConnectionResult r4 = r4.zadi
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0034
            r3 = r0
            com.google.android.gms.common.internal.IAccountAccessor r3 = r3.getAccountAccessor()
            r4 = r2
            com.google.android.gms.common.internal.IAccountAccessor r4 = r4.getAccountAccessor()
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0034
            r3 = 1
            r0 = r3
            goto L_0x0008
        L_0x0034:
            r3 = 0
            r0 = r3
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.ResolveAccountResponse.equals(java.lang.Object):boolean");
    }

    static {
        Parcelable.Creator<ResolveAccountResponse> creator;
        new zan();
        CREATOR = creator;
    }
}

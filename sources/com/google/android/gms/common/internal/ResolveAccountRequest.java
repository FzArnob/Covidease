package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class(creator = "ResolveAccountRequestCreator")
public class ResolveAccountRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ResolveAccountRequest> CREATOR;
    @SafeParcelable.VersionField(mo25283id = 1)
    private final int zalf;
    @SafeParcelable.Field(getter = "getSessionId", mo25277id = 3)
    private final int zapa;
    @SafeParcelable.Field(getter = "getSignInAccountHint", mo25277id = 4)
    private final GoogleSignInAccount zapb;
    @SafeParcelable.Field(getter = "getAccount", mo25277id = 2)
    private final Account zax;

    @SafeParcelable.Constructor
    ResolveAccountRequest(@SafeParcelable.Param(mo25280id = 1) int i, @SafeParcelable.Param(mo25280id = 2) Account account, @SafeParcelable.Param(mo25280id = 3) int i2, @SafeParcelable.Param(mo25280id = 4) GoogleSignInAccount googleSignInAccount) {
        this.zalf = i;
        this.zax = account;
        this.zapa = i2;
        this.zapb = googleSignInAccount;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ResolveAccountRequest(Account account, int i, GoogleSignInAccount googleSignInAccount) {
        this(2, account, i, googleSignInAccount);
    }

    public Account getAccount() {
        return this.zax;
    }

    public int getSessionId() {
        return this.zapa;
    }

    @Nullable
    public GoogleSignInAccount getSignInAccountHint() {
        return this.zapb;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = i;
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeInt(parcel2, 1, this.zalf);
        SafeParcelWriter.writeParcelable(parcel2, 2, getAccount(), i2, false);
        SafeParcelWriter.writeInt(parcel2, 3, getSessionId());
        SafeParcelWriter.writeParcelable(parcel2, 4, getSignInAccountHint(), i2, false);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }

    static {
        Parcelable.Creator<ResolveAccountRequest> creator;
        new zam();
        CREATOR = creator;
    }
}

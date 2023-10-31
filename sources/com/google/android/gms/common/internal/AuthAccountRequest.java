package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nullable;

@SafeParcelable.Class(creator = "AuthAccountRequestCreator")
public class AuthAccountRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<AuthAccountRequest> CREATOR;
    @SafeParcelable.VersionField(mo25283id = 1)
    private final int zalf;
    @SafeParcelable.Field(mo25277id = 2)
    @Deprecated
    private final IBinder zanx;
    @SafeParcelable.Field(mo25277id = 3)
    private final Scope[] zany;
    @SafeParcelable.Field(mo25277id = 4)
    private Integer zanz;
    @SafeParcelable.Field(mo25277id = 5)
    private Integer zaoa;
    @SafeParcelable.Field(mo25277id = 6, type = "android.accounts.Account")
    private Account zax;

    @SafeParcelable.Constructor
    AuthAccountRequest(@SafeParcelable.Param(mo25280id = 1) int i, @SafeParcelable.Param(mo25280id = 2) IBinder iBinder, @SafeParcelable.Param(mo25280id = 3) Scope[] scopeArr, @SafeParcelable.Param(mo25280id = 4) Integer num, @SafeParcelable.Param(mo25280id = 5) Integer num2, @SafeParcelable.Param(mo25280id = 6) Account account) {
        this.zalf = i;
        this.zanx = iBinder;
        this.zany = scopeArr;
        this.zanz = num;
        this.zaoa = num2;
        this.zax = account;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AuthAccountRequest(com.google.android.gms.common.internal.IAccountAccessor r11, java.util.Set<com.google.android.gms.common.api.Scope> r12) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r2 = r12
            r3 = r0
            r4 = 3
            r5 = r1
            android.os.IBinder r5 = r5.asBinder()
            r6 = r2
            r7 = r2
            int r7 = r7.size()
            com.google.android.gms.common.api.Scope[] r7 = new com.google.android.gms.common.api.Scope[r7]
            java.lang.Object[] r6 = r6.toArray(r7)
            com.google.android.gms.common.api.Scope[] r6 = (com.google.android.gms.common.api.Scope[]) r6
            r7 = 0
            r8 = 0
            r9 = 0
            r3.<init>(r4, r5, r6, r7, r8, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.AuthAccountRequest.<init>(com.google.android.gms.common.internal.IAccountAccessor, java.util.Set):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AuthAccountRequest(android.accounts.Account r11, java.util.Set<com.google.android.gms.common.api.Scope> r12) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r2 = r12
            r3 = r0
            r4 = 3
            r5 = 0
            r6 = r2
            r7 = r2
            int r7 = r7.size()
            com.google.android.gms.common.api.Scope[] r7 = new com.google.android.gms.common.api.Scope[r7]
            java.lang.Object[] r6 = r6.toArray(r7)
            com.google.android.gms.common.api.Scope[] r6 = (com.google.android.gms.common.api.Scope[]) r6
            r7 = 0
            r8 = 0
            r9 = r1
            java.lang.Object r9 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r9)
            android.accounts.Account r9 = (android.accounts.Account) r9
            r3.<init>(r4, r5, r6, r7, r8, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.AuthAccountRequest.<init>(android.accounts.Account, java.util.Set):void");
    }

    public Account getAccount() {
        Account account = null;
        if (this.zax != null) {
            account = this.zax;
        } else if (this.zanx != null) {
            account = AccountAccessor.getAccountBinderSafe(IAccountAccessor.Stub.asInterface(this.zanx));
        }
        return account;
    }

    public Set<Scope> getScopes() {
        Set<Scope> set;
        new HashSet(Arrays.asList(this.zany));
        return set;
    }

    public AuthAccountRequest setOauthPolicy(@Nullable Integer num) {
        this.zanz = num;
        return this;
    }

    @Nullable
    public Integer getOauthPolicy() {
        return this.zanz;
    }

    public AuthAccountRequest setPolicyAction(@Nullable Integer num) {
        this.zaoa = num;
        return this;
    }

    @Nullable
    public Integer getPolicyAction() {
        return this.zaoa;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = i;
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeInt(parcel2, 1, this.zalf);
        SafeParcelWriter.writeIBinder(parcel2, 2, this.zanx, false);
        SafeParcelWriter.writeTypedArray(parcel2, 3, this.zany, i2, false);
        SafeParcelWriter.writeIntegerObject(parcel2, 4, this.zanz, false);
        SafeParcelWriter.writeIntegerObject(parcel2, 5, this.zaoa, false);
        SafeParcelWriter.writeParcelable(parcel2, 6, this.zax, i2, false);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }

    static {
        Parcelable.Creator<AuthAccountRequest> creator;
        new zaa();
        CREATOR = creator;
    }
}

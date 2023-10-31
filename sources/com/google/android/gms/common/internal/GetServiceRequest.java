package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@KeepForSdk
@SafeParcelable.Class(creator = "GetServiceRequestCreator")
@SafeParcelable.Reserved({9})
public class GetServiceRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<GetServiceRequest> CREATOR;
    @SafeParcelable.VersionField(mo25283id = 1)
    private final int version;
    @SafeParcelable.Field(mo25277id = 2)
    private final int zzdg;
    @SafeParcelable.Field(mo25277id = 3)
    private int zzdh;
    @SafeParcelable.Field(mo25277id = 5)
    IBinder zzdi;
    @SafeParcelable.Field(mo25277id = 6)
    Scope[] zzdj;
    @SafeParcelable.Field(mo25277id = 7)
    Bundle zzdk;
    @SafeParcelable.Field(mo25277id = 8)
    Account zzdl;
    @SafeParcelable.Field(mo25277id = 10)
    Feature[] zzdm;
    @SafeParcelable.Field(mo25277id = 11)
    Feature[] zzdn;
    @SafeParcelable.Field(mo25277id = 12)
    private boolean zzdo;
    @SafeParcelable.Field(mo25277id = 4)
    String zzy;

    public GetServiceRequest(int i) {
        this.version = 4;
        this.zzdh = GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
        this.zzdg = i;
        this.zzdo = true;
    }

    @SafeParcelable.Constructor
    GetServiceRequest(@SafeParcelable.Param(mo25280id = 1) int i, @SafeParcelable.Param(mo25280id = 2) int i2, @SafeParcelable.Param(mo25280id = 3) int i3, @SafeParcelable.Param(mo25280id = 4) String str, @SafeParcelable.Param(mo25280id = 5) IBinder iBinder, @SafeParcelable.Param(mo25280id = 6) Scope[] scopeArr, @SafeParcelable.Param(mo25280id = 7) Bundle bundle, @SafeParcelable.Param(mo25280id = 8) Account account, @SafeParcelable.Param(mo25280id = 10) Feature[] featureArr, @SafeParcelable.Param(mo25280id = 11) Feature[] featureArr2, @SafeParcelable.Param(mo25280id = 12) boolean z) {
        int i4 = i;
        String str2 = str;
        IBinder iBinder2 = iBinder;
        Scope[] scopeArr2 = scopeArr;
        Bundle bundle2 = bundle;
        Account account2 = account;
        Feature[] featureArr3 = featureArr;
        Feature[] featureArr4 = featureArr2;
        boolean z2 = z;
        this.version = i4;
        this.zzdg = i2;
        this.zzdh = i3;
        if ("com.google.android.gms".equals(str2)) {
            this.zzy = "com.google.android.gms";
        } else {
            this.zzy = str2;
        }
        if (i4 < 2) {
            IBinder iBinder3 = iBinder2;
            this.zzdl = iBinder3 != null ? AccountAccessor.getAccountBinderSafe(IAccountAccessor.Stub.asInterface(iBinder3)) : null;
        } else {
            this.zzdi = iBinder2;
            this.zzdl = account2;
        }
        this.zzdj = scopeArr2;
        this.zzdk = bundle2;
        this.zzdm = featureArr3;
        this.zzdn = featureArr4;
        this.zzdo = z2;
    }

    @KeepForSdk
    public Bundle getExtraArgs() {
        return this.zzdk;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = i;
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeInt(parcel2, 1, this.version);
        SafeParcelWriter.writeInt(parcel2, 2, this.zzdg);
        SafeParcelWriter.writeInt(parcel2, 3, this.zzdh);
        SafeParcelWriter.writeString(parcel2, 4, this.zzy, false);
        SafeParcelWriter.writeIBinder(parcel2, 5, this.zzdi, false);
        SafeParcelWriter.writeTypedArray(parcel2, 6, this.zzdj, i2, false);
        SafeParcelWriter.writeBundle(parcel2, 7, this.zzdk, false);
        SafeParcelWriter.writeParcelable(parcel2, 8, this.zzdl, i2, false);
        SafeParcelWriter.writeTypedArray(parcel2, 10, this.zzdm, i2, false);
        SafeParcelWriter.writeTypedArray(parcel2, 11, this.zzdn, i2, false);
        SafeParcelWriter.writeBoolean(parcel2, 12, this.zzdo);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }

    static {
        Parcelable.Creator<GetServiceRequest> creator;
        new zzd();
        CREATOR = creator;
    }
}

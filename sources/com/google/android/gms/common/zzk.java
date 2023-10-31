package com.google.android.gms.common;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzj;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import javax.annotation.Nullable;

@SafeParcelable.Class(creator = "GoogleCertificatesQueryCreator")
public final class zzk extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzk> CREATOR;
    @SafeParcelable.Field(getter = "getAllowTestKeys", mo25277id = 3)
    private final boolean zzaa;
    @SafeParcelable.Field(defaultValue = "false", getter = "getForbidTestKeys", mo25277id = 4)
    private final boolean zzab;
    @SafeParcelable.Field(getter = "getCallingPackage", mo25277id = 1)
    private final String zzy;
    @SafeParcelable.Field(getter = "getCallingCertificateBinder", mo25277id = 2, type = "android.os.IBinder")
    @Nullable
    private final zze zzz;

    @SafeParcelable.Constructor
    zzk(@SafeParcelable.Param(mo25280id = 1) String str, @SafeParcelable.Param(mo25280id = 2) @Nullable IBinder iBinder, @SafeParcelable.Param(mo25280id = 3) boolean z, @SafeParcelable.Param(mo25280id = 4) boolean z2) {
        this.zzy = str;
        this.zzz = zza(iBinder);
        this.zzaa = z;
        this.zzab = z2;
    }

    zzk(String str, @Nullable zze zze, boolean z, boolean z2) {
        this.zzy = str;
        this.zzz = zze;
        this.zzaa = z;
        this.zzab = z2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        IBinder asBinder;
        int i2 = i;
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeString(parcel2, 1, this.zzy, false);
        Parcel parcel3 = parcel2;
        if (this.zzz == null) {
            int w = Log.w("GoogleCertificatesQuery", "certificate binder is null");
            asBinder = null;
        } else {
            asBinder = this.zzz.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel3, 2, asBinder, false);
        SafeParcelWriter.writeBoolean(parcel2, 3, this.zzaa);
        SafeParcelWriter.writeBoolean(parcel2, 4, this.zzab);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }

    @Nullable
    private static zze zza(@Nullable IBinder iBinder) {
        zze zze;
        IBinder iBinder2 = iBinder;
        if (iBinder2 == null) {
            return null;
        }
        zze zze2 = null;
        try {
            IObjectWrapper zzb = zzj.zzb(iBinder2).zzb();
            byte[] bArr = zzb == null ? null : (byte[]) ObjectWrapper.unwrap(zzb);
            byte[] bArr2 = bArr;
            if (bArr != null) {
                new zzf(bArr2);
                zze2 = zze;
            } else {
                int e = Log.e("GoogleCertificatesQuery", "Could not unwrap certificate");
            }
            return zze2;
        } catch (RemoteException e2) {
            int e3 = Log.e("GoogleCertificatesQuery", "Could not unwrap certificate", e2);
            return null;
        }
    }

    static {
        Parcelable.Creator<zzk> creator;
        new zzl();
        CREATOR = creator;
    }
}

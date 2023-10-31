package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.location.zzu;
import com.google.android.gms.location.zzv;
import com.google.android.gms.location.zzx;
import com.google.android.gms.location.zzy;

@SafeParcelable.Class(creator = "LocationRequestUpdateDataCreator")
@SafeParcelable.Reserved({1000})
public final class zzbf extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbf> CREATOR;
    @SafeParcelable.Field(defaultValueUnchecked = "null", mo25277id = 4)
    private PendingIntent zzbv;
    @SafeParcelable.Field(defaultValueUnchecked = "LocationRequestUpdateData.OPERATION_ADD", mo25277id = 1)
    private int zzcg;
    @SafeParcelable.Field(defaultValueUnchecked = "null", getter = "getFusedLocationProviderCallbackBinder", mo25277id = 6, type = "android.os.IBinder")
    private zzaj zzcj;
    @SafeParcelable.Field(defaultValueUnchecked = "null", mo25277id = 2)
    private zzbd zzdl;
    @SafeParcelable.Field(defaultValueUnchecked = "null", getter = "getLocationListenerBinder", mo25277id = 3, type = "android.os.IBinder")
    private zzx zzdm;
    @SafeParcelable.Field(defaultValueUnchecked = "null", getter = "getLocationCallbackBinder", mo25277id = 5, type = "android.os.IBinder")
    private zzu zzdn;

    static {
        Parcelable.Creator<zzbf> creator;
        new zzbg();
        CREATOR = creator;
    }

    @SafeParcelable.Constructor
    zzbf(@SafeParcelable.Param(mo25280id = 1) int i, @SafeParcelable.Param(mo25280id = 2) zzbd zzbd, @SafeParcelable.Param(mo25280id = 3) IBinder iBinder, @SafeParcelable.Param(mo25280id = 4) PendingIntent pendingIntent, @SafeParcelable.Param(mo25280id = 5) IBinder iBinder2, @SafeParcelable.Param(mo25280id = 6) IBinder iBinder3) {
        zzaj zzaj;
        zzaj zzaj2;
        IBinder iBinder4 = iBinder;
        PendingIntent pendingIntent2 = pendingIntent;
        IBinder iBinder5 = iBinder2;
        IBinder iBinder6 = iBinder3;
        this.zzcg = i;
        this.zzdl = zzbd;
        this.zzdm = iBinder4 == null ? null : zzy.zzc(iBinder4);
        this.zzbv = pendingIntent2;
        this.zzdn = iBinder5 == null ? null : zzv.zzb(iBinder5);
        if (iBinder6 == null) {
            zzaj2 = null;
        } else {
            IBinder iBinder7 = iBinder6;
            IBinder iBinder8 = iBinder7;
            if (iBinder7 == null) {
                zzaj2 = null;
            } else {
                IInterface queryLocalInterface = iBinder8.queryLocalInterface("com.google.android.gms.location.internal.IFusedLocationProviderCallback");
                IInterface iInterface = queryLocalInterface;
                if (queryLocalInterface instanceof zzaj) {
                    zzaj2 = (zzaj) iInterface;
                } else {
                    zzaj2 = zzaj;
                    new zzal(iBinder8);
                }
            }
        }
        this.zzcj = zzaj2;
    }

    public static zzbf zza(zzu zzu, @Nullable zzaj zzaj) {
        zzaj zzaj2 = zzaj;
        zzbf zzbf = r10;
        zzbf zzbf2 = new zzbf(2, (zzbd) null, (IBinder) null, (PendingIntent) null, zzu.asBinder(), zzaj2 != null ? zzaj2.asBinder() : null);
        return zzbf;
    }

    public static zzbf zza(zzx zzx, @Nullable zzaj zzaj) {
        zzaj zzaj2 = zzaj;
        zzbf zzbf = r10;
        zzbf zzbf2 = new zzbf(2, (zzbd) null, zzx.asBinder(), (PendingIntent) null, (IBinder) null, zzaj2 != null ? zzaj2.asBinder() : null);
        return zzbf;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int i2 = i;
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeInt(parcel2, 1, this.zzcg);
        SafeParcelWriter.writeParcelable(parcel2, 2, this.zzdl, i2, false);
        SafeParcelWriter.writeIBinder(parcel2, 3, this.zzdm == null ? null : this.zzdm.asBinder(), false);
        SafeParcelWriter.writeParcelable(parcel2, 4, this.zzbv, i2, false);
        SafeParcelWriter.writeIBinder(parcel2, 5, this.zzdn == null ? null : this.zzdn.asBinder(), false);
        SafeParcelWriter.writeIBinder(parcel2, 6, this.zzcj == null ? null : this.zzcj.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }
}

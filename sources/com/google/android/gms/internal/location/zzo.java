package com.google.android.gms.internal.location;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.location.zzr;
import com.google.android.gms.location.zzs;

@SafeParcelable.Class(creator = "DeviceOrientationRequestUpdateDataCreator")
public final class zzo extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzo> CREATOR;
    @SafeParcelable.Field(defaultValueUnchecked = "DeviceOrientationRequestUpdateData.OPERATION_ADD", mo25277id = 1)
    private int zzcg;
    @SafeParcelable.Field(defaultValueUnchecked = "null", mo25277id = 2)
    private zzm zzch;
    @SafeParcelable.Field(defaultValueUnchecked = "null", getter = "getDeviceOrientationListenerBinder", mo25277id = 3, type = "android.os.IBinder")
    private zzr zzci;
    @SafeParcelable.Field(defaultValueUnchecked = "null", getter = "getFusedLocationProviderCallbackBinder", mo25277id = 4, type = "android.os.IBinder")
    private zzaj zzcj;

    static {
        Parcelable.Creator<zzo> creator;
        new zzp();
        CREATOR = creator;
    }

    @SafeParcelable.Constructor
    zzo(@SafeParcelable.Param(mo25280id = 1) int i, @SafeParcelable.Param(mo25280id = 2) zzm zzm, @SafeParcelable.Param(mo25280id = 3) IBinder iBinder, @SafeParcelable.Param(mo25280id = 4) IBinder iBinder2) {
        zzaj zzaj;
        zzaj zzaj2;
        IBinder iBinder3 = iBinder;
        IBinder iBinder4 = iBinder2;
        this.zzcg = i;
        this.zzch = zzm;
        this.zzci = iBinder3 == null ? null : zzs.zza(iBinder3);
        if (iBinder4 == null) {
            zzaj2 = null;
        } else {
            IBinder iBinder5 = iBinder4;
            IBinder iBinder6 = iBinder5;
            if (iBinder5 == null) {
                zzaj2 = null;
            } else {
                IInterface queryLocalInterface = iBinder6.queryLocalInterface("com.google.android.gms.location.internal.IFusedLocationProviderCallback");
                IInterface iInterface = queryLocalInterface;
                if (queryLocalInterface instanceof zzaj) {
                    zzaj2 = (zzaj) iInterface;
                } else {
                    zzaj2 = zzaj;
                    new zzal(iBinder6);
                }
            }
        }
        this.zzcj = zzaj2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        Parcel parcel2 = parcel;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel2);
        SafeParcelWriter.writeInt(parcel2, 1, this.zzcg);
        SafeParcelWriter.writeParcelable(parcel2, 2, this.zzch, i, false);
        SafeParcelWriter.writeIBinder(parcel2, 3, this.zzci == null ? null : this.zzci.asBinder(), false);
        SafeParcelWriter.writeIBinder(parcel2, 4, this.zzcj == null ? null : this.zzcj.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel2, beginObjectHeader);
    }
}

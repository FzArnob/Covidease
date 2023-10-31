package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.annotation.KeepName;

@KeepName
@KeepForSdk
public final class BinderWrapper implements Parcelable {
    public static final Parcelable.Creator<BinderWrapper> CREATOR;
    private IBinder zzcz;

    public BinderWrapper() {
        this.zzcz = null;
    }

    @KeepForSdk
    public BinderWrapper(IBinder iBinder) {
        this.zzcz = null;
        this.zzcz = iBinder;
    }

    private BinderWrapper(Parcel parcel) {
        this.zzcz = null;
        this.zzcz = parcel.readStrongBinder();
    }

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int i2 = i;
        parcel.writeStrongBinder(this.zzcz);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    /* synthetic */ BinderWrapper(Parcel parcel, zza zza) {
        this(parcel);
        zza zza2 = zza;
    }

    static {
        Parcelable.Creator<BinderWrapper> creator;
        new zza();
        CREATOR = creator;
    }
}

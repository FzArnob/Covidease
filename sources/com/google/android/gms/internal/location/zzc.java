package com.google.android.gms.internal.location;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;

public class zzc {
    private static final ClassLoader zzd = zzc.class.getClassLoader();

    private zzc() {
    }

    public static <T extends Parcelable> T zza(Parcel parcel, Parcelable.Creator<T> creator) {
        Parcel parcel2 = parcel;
        Parcelable.Creator<T> creator2 = creator;
        if (parcel2.readInt() == 0) {
            return null;
        }
        return (Parcelable) creator2.createFromParcel(parcel2);
    }

    public static void zza(Parcel parcel, IInterface iInterface) {
        Parcel parcel2 = parcel;
        IInterface iInterface2 = iInterface;
        if (iInterface2 == null) {
            parcel2.writeStrongBinder((IBinder) null);
        } else {
            parcel2.writeStrongBinder(iInterface2.asBinder());
        }
    }

    public static void zza(Parcel parcel, Parcelable parcelable) {
        Parcel parcel2 = parcel;
        Parcelable parcelable2 = parcelable;
        if (parcelable2 == null) {
            parcel2.writeInt(0);
            return;
        }
        parcel2.writeInt(1);
        parcelable2.writeToParcel(parcel2, 0);
    }

    public static void zza(Parcel parcel, boolean z) {
        parcel.writeInt(z ? 1 : 0);
    }
}

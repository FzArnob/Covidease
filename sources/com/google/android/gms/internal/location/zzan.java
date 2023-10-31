package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzan extends zzb implements zzam {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzan() {
        super("com.google.android.gms.location.internal.IGeofencerCallbacks");
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        Parcel parcel3 = parcel;
        Parcel parcel4 = parcel2;
        int i3 = i2;
        switch (i) {
            case 1:
                zza(parcel3.readInt(), parcel3.createStringArray());
                break;
            case 2:
                zzb(parcel3.readInt(), parcel3.createStringArray());
                break;
            case 3:
                zza(parcel3.readInt(), (PendingIntent) zzc.zza(parcel3, PendingIntent.CREATOR));
                break;
            default:
                return false;
        }
        return true;
    }
}

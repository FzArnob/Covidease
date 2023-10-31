package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.maps.model.Tile;

public final class zzah extends zza implements zzaf {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzah(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.model.internal.ITileProviderDelegate");
    }

    public final Tile getTile(int i, int i2, int i3) throws RemoteException {
        Parcel zza = zza();
        Parcel parcel = zza;
        zza.writeInt(i);
        parcel.writeInt(i2);
        parcel.writeInt(i3);
        Parcel zza2 = zza(1, parcel);
        Tile tile = (Tile) zzc.zza(zza2, Tile.CREATOR);
        zza2.recycle();
        return tile;
    }
}

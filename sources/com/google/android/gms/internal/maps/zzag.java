package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.maps.model.Tile;

public abstract class zzag extends zzb implements zzaf {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzag() {
        super("com.google.android.gms.maps.model.internal.ITileProviderDelegate");
    }

    public static zzaf zzk(IBinder iBinder) {
        zzaf zzaf;
        IBinder iBinder2 = iBinder;
        if (iBinder2 == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder2.queryLocalInterface("com.google.android.gms.maps.model.internal.ITileProviderDelegate");
        IInterface iInterface = queryLocalInterface;
        if (queryLocalInterface instanceof zzaf) {
            return (zzaf) iInterface;
        }
        new zzah(iBinder2);
        return zzaf;
    }

    /* access modifiers changed from: protected */
    public final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        Parcel parcel3 = parcel;
        Parcel parcel4 = parcel2;
        int i3 = i2;
        if (i != 1) {
            return false;
        }
        Tile tile = getTile(parcel3.readInt(), parcel3.readInt(), parcel3.readInt());
        parcel4.writeNoException();
        zzc.zzb(parcel4, tile);
        return true;
    }
}

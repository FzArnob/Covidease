package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.zzaq;

final class zzac extends zzaq {
    private final /* synthetic */ OnMapReadyCallback zzbc;

    zzac(MapView.zza zza, OnMapReadyCallback onMapReadyCallback) {
        MapView.zza zza2 = zza;
        this.zzbc = onMapReadyCallback;
    }

    public final void zza(IGoogleMapDelegate iGoogleMapDelegate) throws RemoteException {
        GoogleMap googleMap;
        new GoogleMap(iGoogleMapDelegate);
        this.zzbc.onMapReady(googleMap);
    }
}

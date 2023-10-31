package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.zzaq;

final class zzak extends zzaq {
    private final /* synthetic */ OnMapReadyCallback zzbc;

    zzak(SupportMapFragment.zza zza, OnMapReadyCallback onMapReadyCallback) {
        SupportMapFragment.zza zza2 = zza;
        this.zzbc = onMapReadyCallback;
    }

    public final void zza(IGoogleMapDelegate iGoogleMapDelegate) throws RemoteException {
        GoogleMap googleMap;
        new GoogleMap(iGoogleMapDelegate);
        this.zzbc.onMapReady(googleMap);
    }
}

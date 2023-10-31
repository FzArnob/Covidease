package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate;
import com.google.android.gms.maps.internal.zzbq;

final class zzah extends zzbq {
    private final /* synthetic */ OnStreetViewPanoramaReadyCallback zzbv;

    zzah(StreetViewPanoramaFragment.zza zza, OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
        StreetViewPanoramaFragment.zza zza2 = zza;
        this.zzbv = onStreetViewPanoramaReadyCallback;
    }

    public final void zza(IStreetViewPanoramaDelegate iStreetViewPanoramaDelegate) throws RemoteException {
        StreetViewPanorama streetViewPanorama;
        new StreetViewPanorama(iStreetViewPanoramaDelegate);
        this.zzbv.onStreetViewPanoramaReady(streetViewPanorama);
    }
}

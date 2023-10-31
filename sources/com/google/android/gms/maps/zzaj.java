package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.StreetViewPanoramaView;
import com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate;
import com.google.android.gms.maps.internal.zzbq;

final class zzaj extends zzbq {
    private final /* synthetic */ OnStreetViewPanoramaReadyCallback zzbv;

    zzaj(StreetViewPanoramaView.zza zza, OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
        StreetViewPanoramaView.zza zza2 = zza;
        this.zzbv = onStreetViewPanoramaReadyCallback;
    }

    public final void zza(IStreetViewPanoramaDelegate iStreetViewPanoramaDelegate) throws RemoteException {
        StreetViewPanorama streetViewPanorama;
        new StreetViewPanorama(iStreetViewPanoramaDelegate);
        this.zzbv.onStreetViewPanoramaReady(streetViewPanorama);
    }
}

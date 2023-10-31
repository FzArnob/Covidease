package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzam;

final class zzk extends zzam {
    private final /* synthetic */ GoogleMap.OnMapLoadedCallback zzs;

    zzk(GoogleMap googleMap, GoogleMap.OnMapLoadedCallback onMapLoadedCallback) {
        GoogleMap googleMap2 = googleMap;
        this.zzs = onMapLoadedCallback;
    }

    public final void onMapLoaded() throws RemoteException {
        this.zzs.onMapLoaded();
    }
}

package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzn;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzaa;
import com.google.android.gms.maps.model.IndoorBuilding;

final class zza extends zzaa {
    private final /* synthetic */ GoogleMap.OnIndoorStateChangeListener zzi;

    zza(GoogleMap googleMap, GoogleMap.OnIndoorStateChangeListener onIndoorStateChangeListener) {
        GoogleMap googleMap2 = googleMap;
        this.zzi = onIndoorStateChangeListener;
    }

    public final void onIndoorBuildingFocused() {
        this.zzi.onIndoorBuildingFocused();
    }

    public final void zza(zzn zzn) {
        IndoorBuilding indoorBuilding;
        new IndoorBuilding(zzn);
        this.zzi.onIndoorLevelActivated(indoorBuilding);
    }
}

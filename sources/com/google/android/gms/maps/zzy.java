package com.google.android.gms.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzak;
import com.google.android.gms.maps.model.LatLng;

final class zzy extends zzak {
    private final /* synthetic */ GoogleMap.OnMapClickListener zzag;

    zzy(GoogleMap googleMap, GoogleMap.OnMapClickListener onMapClickListener) {
        GoogleMap googleMap2 = googleMap;
        this.zzag = onMapClickListener;
    }

    public final void onMapClick(LatLng latLng) {
        this.zzag.onMapClick(latLng);
    }
}

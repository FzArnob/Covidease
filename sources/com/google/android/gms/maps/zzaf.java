package com.google.android.gms.maps;

import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.internal.zzbm;
import com.google.android.gms.maps.model.StreetViewPanoramaOrientation;

final class zzaf extends zzbm {
    private final /* synthetic */ StreetViewPanorama.OnStreetViewPanoramaClickListener zzbr;

    zzaf(StreetViewPanorama streetViewPanorama, StreetViewPanorama.OnStreetViewPanoramaClickListener onStreetViewPanoramaClickListener) {
        StreetViewPanorama streetViewPanorama2 = streetViewPanorama;
        this.zzbr = onStreetViewPanoramaClickListener;
    }

    public final void onStreetViewPanoramaClick(StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
        this.zzbr.onStreetViewPanoramaClick(streetViewPanoramaOrientation);
    }
}

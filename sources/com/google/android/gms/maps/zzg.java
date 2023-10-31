package com.google.android.gms.maps;

import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.maps.zzt;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzi;
import com.google.android.gms.maps.model.Marker;

final class zzg extends zzi {
    private final /* synthetic */ GoogleMap.InfoWindowAdapter zzo;

    zzg(GoogleMap googleMap, GoogleMap.InfoWindowAdapter infoWindowAdapter) {
        GoogleMap googleMap2 = googleMap;
        this.zzo = infoWindowAdapter;
    }

    public final IObjectWrapper zzh(zzt zzt) {
        Marker marker;
        new Marker(zzt);
        return ObjectWrapper.wrap(this.zzo.getInfoWindow(marker));
    }

    public final IObjectWrapper zzi(zzt zzt) {
        Marker marker;
        new Marker(zzt);
        return ObjectWrapper.wrap(this.zzo.getInfoContents(marker));
    }
}

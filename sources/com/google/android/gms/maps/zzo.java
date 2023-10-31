package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzh;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.internal.zzw;
import com.google.android.gms.maps.model.Circle;

final class zzo extends zzw {
    private final /* synthetic */ GoogleMap.OnCircleClickListener zzw;

    zzo(GoogleMap googleMap, GoogleMap.OnCircleClickListener onCircleClickListener) {
        GoogleMap googleMap2 = googleMap;
        this.zzw = onCircleClickListener;
    }

    public final void zza(zzh zzh) {
        Circle circle;
        new Circle(zzh);
        this.zzw.onCircleClick(circle);
    }
}

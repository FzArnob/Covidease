package com.google.android.gms.maps;

import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.internal.ILocationSourceDelegate;
import com.google.android.gms.maps.internal.zzah;

final class zzl extends ILocationSourceDelegate.zza {
    private final /* synthetic */ LocationSource zzt;

    zzl(GoogleMap googleMap, LocationSource locationSource) {
        GoogleMap googleMap2 = googleMap;
        this.zzt = locationSource;
    }

    public final void activate(zzah zzah) {
        LocationSource.OnLocationChangedListener onLocationChangedListener;
        new zzm(this, zzah);
        this.zzt.activate(onLocationChangedListener);
    }

    public final void deactivate() {
        this.zzt.deactivate();
    }
}

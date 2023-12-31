package com.google.android.gms.maps.model;

import com.google.android.gms.internal.maps.zzag;

final class zzt extends zzag {
    private final /* synthetic */ TileProvider zzen;

    zzt(TileOverlayOptions tileOverlayOptions, TileProvider tileProvider) {
        TileOverlayOptions tileOverlayOptions2 = tileOverlayOptions;
        this.zzen = tileProvider;
    }

    public final Tile getTile(int i, int i2, int i3) {
        return this.zzen.getTile(i, i2, i3);
    }
}

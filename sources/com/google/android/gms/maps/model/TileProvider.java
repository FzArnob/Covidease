package com.google.android.gms.maps.model;

public interface TileProvider {
    public static final Tile NO_TILE;

    static {
        Tile tile;
        new Tile(-1, -1, (byte[]) null);
        NO_TILE = tile;
    }

    Tile getTile(int i, int i2, int i3);
}

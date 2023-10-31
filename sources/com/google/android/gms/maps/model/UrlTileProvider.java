package com.google.android.gms.maps.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public abstract class UrlTileProvider implements TileProvider {
    private final int height;
    private final int width;

    public UrlTileProvider(int i, int i2) {
        this.width = i;
        this.height = i2;
    }

    public abstract URL getTileUrl(int i, int i2, int i3);

    public final Tile getTile(int i, int i2, int i3) {
        Tile tile;
        Tile tile2;
        ByteArrayOutputStream byteArrayOutputStream;
        URL tileUrl = getTileUrl(i, i2, i3);
        URL url = tileUrl;
        if (tileUrl == null) {
            return NO_TILE;
        }
        try {
            Tile tile3 = tile2;
            int i4 = this.width;
            int i5 = this.height;
            InputStream openStream = url.openStream();
            new ByteArrayOutputStream();
            ByteArrayOutputStream byteArrayOutputStream2 = byteArrayOutputStream;
            ByteArrayOutputStream byteArrayOutputStream3 = byteArrayOutputStream2;
            InputStream inputStream = openStream;
            byte[] bArr = new byte[4096];
            while (true) {
                int read = inputStream.read(bArr);
                int i6 = read;
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream3.write(bArr, 0, i6);
            }
            new Tile(i4, i5, byteArrayOutputStream2.toByteArray());
            tile = tile3;
        } catch (IOException e) {
            tile = null;
        }
        return tile;
    }
}

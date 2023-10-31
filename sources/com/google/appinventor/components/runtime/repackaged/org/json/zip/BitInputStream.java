package com.google.appinventor.components.runtime.repackaged.org.json.zip;

import java.io.IOException;
import java.io.InputStream;

public class BitInputStream implements BitReader {
    static final int[] mask = {0, 1, 3, 7, 15, 31, 63, 127, 255};
    private int available = 0;

    /* renamed from: in */
    private InputStream f323in;
    private long nrBits = 0;
    private int unread = 0;

    public BitInputStream(InputStream in) {
        this.f323in = in;
    }

    public BitInputStream(InputStream in, int firstByte) {
        this.f323in = in;
        this.unread = firstByte;
        this.available = 8;
    }

    public boolean bit() throws IOException {
        return read(1) != 0;
    }

    public long nrBits() {
        return this.nrBits;
    }

    public boolean pad(int i) throws IOException {
        int factor = i;
        int padding = factor - ((int) (this.nrBits % ((long) factor)));
        boolean result = true;
        for (int i2 = 0; i2 < padding; i2++) {
            if (bit()) {
                result = false;
            }
        }
        return result;
    }

    public int read(int i) throws IOException {
        Throwable th;
        Throwable th2;
        int width = i;
        if (width == 0) {
            return 0;
        }
        if (width < 0 || width > 32) {
            Throwable th3 = th;
            new IOException("Bad read width.");
            throw th3;
        }
        int result = 0;
        while (width > 0) {
            if (this.available == 0) {
                this.unread = this.f323in.read();
                if (this.unread < 0) {
                    Throwable th4 = th2;
                    new IOException("Attempt to read past end.");
                    throw th4;
                }
                this.available = 8;
            }
            int take = width;
            if (take > this.available) {
                take = this.available;
            }
            result |= ((this.unread >>> (this.available - take)) & mask[take]) << (width - take);
            this.nrBits += (long) take;
            this.available -= take;
            width -= take;
        }
        return result;
    }
}

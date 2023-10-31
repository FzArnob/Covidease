package com.google.appinventor.components.runtime.repackaged.org.json.zip;

import java.io.IOException;
import java.io.OutputStream;

public class BitOutputStream implements BitWriter {
    private long nrBits = 0;
    private OutputStream out;
    private int unwritten;
    private int vacant = 8;

    public BitOutputStream(OutputStream out2) {
        this.out = out2;
    }

    public long nrBits() {
        return this.nrBits;
    }

    public void one() throws IOException {
        write(1, 1);
    }

    public void pad(int i) throws IOException {
        int factor = i;
        int padding = factor - ((int) (this.nrBits % ((long) factor)));
        int excess = padding & 7;
        if (excess > 0) {
            write(0, excess);
            padding -= excess;
        }
        while (padding > 0) {
            write(0, 8);
            padding -= 8;
        }
        this.out.flush();
    }

    public void write(int i, int i2) throws IOException {
        Throwable th;
        int bits = i;
        int width = i2;
        if (bits != 0 || width != 0) {
            if (width <= 0 || width > 32) {
                Throwable th2 = th;
                new IOException("Bad write width.");
                throw th2;
            }
            while (width > 0) {
                int actual = width;
                if (actual > this.vacant) {
                    actual = this.vacant;
                }
                this.unwritten |= ((bits >>> (width - actual)) & BitInputStream.mask[actual]) << (this.vacant - actual);
                width -= actual;
                this.nrBits += (long) actual;
                this.vacant -= actual;
                if (this.vacant == 0) {
                    this.out.write(this.unwritten);
                    this.unwritten = 0;
                    this.vacant = 8;
                }
            }
        }
    }

    public void zero() throws IOException {
        write(0, 1);
    }
}

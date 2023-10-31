package com.bumptech.glide.gifencoder;

import com.google.appinventor.components.runtime.util.Ev3Constants;
import java.io.IOException;
import java.io.OutputStream;

class LZWEncoder {
    static final int BITS = 12;
    private static final int EOF = -1;
    static final int HSIZE = 5003;
    int ClearCode;
    int EOFCode;
    int a_count;
    byte[] accum = new byte[256];
    boolean clear_flg = false;
    int[] codetab = new int[HSIZE];
    private int curPixel;
    int cur_accum = 0;
    int cur_bits = 0;
    int free_ent = 0;
    int g_init_bits;
    int hsize = HSIZE;
    int[] htab = new int[HSIZE];
    private int imgH;
    private int imgW;
    private int initCodeSize;
    int[] masks = {0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535};
    int maxbits = 12;
    int maxcode;
    int maxmaxcode = 4096;
    int n_bits;
    private byte[] pixAry;
    private int remaining;

    LZWEncoder(int width, int height, byte[] pixels, int color_depth) {
        this.imgW = width;
        this.imgH = height;
        this.pixAry = pixels;
        this.initCodeSize = Math.max(2, color_depth);
    }

    /* access modifiers changed from: package-private */
    public void char_out(byte c, OutputStream outputStream) throws IOException {
        OutputStream outs = outputStream;
        byte[] bArr = this.accum;
        int i = this.a_count;
        int i2 = i + 1;
        this.a_count = i2;
        bArr[i] = c;
        if (this.a_count >= 254) {
            flush_char(outs);
        }
    }

    /* access modifiers changed from: package-private */
    public void cl_block(OutputStream outs) throws IOException {
        cl_hash(this.hsize);
        this.free_ent = this.ClearCode + 2;
        this.clear_flg = true;
        output(this.ClearCode, outs);
    }

    /* access modifiers changed from: package-private */
    public void cl_hash(int i) {
        int hsize2 = i;
        for (int i2 = 0; i2 < hsize2; i2++) {
            this.htab[i2] = -1;
        }
    }

    /* access modifiers changed from: package-private */
    public void compress(int i, OutputStream outputStream) throws IOException {
        int init_bits = i;
        OutputStream outs = outputStream;
        this.g_init_bits = init_bits;
        this.clear_flg = false;
        this.n_bits = this.g_init_bits;
        this.maxcode = MAXCODE(this.n_bits);
        this.ClearCode = 1 << (init_bits - 1);
        this.EOFCode = this.ClearCode + 1;
        this.free_ent = this.ClearCode + 2;
        this.a_count = 0;
        int ent = nextPixel();
        int hshift = 0;
        int i2 = this.hsize;
        while (true) {
            int fcode = i2;
            if (fcode >= 65536) {
                break;
            }
            hshift++;
            i2 = fcode * 2;
        }
        int hshift2 = 8 - hshift;
        int hsize_reg = this.hsize;
        cl_hash(hsize_reg);
        output(this.ClearCode, outs);
        while (true) {
            int nextPixel = nextPixel();
            int c = nextPixel;
            if (nextPixel != -1) {
                int fcode2 = (c << this.maxbits) + ent;
                int i3 = (c << hshift2) ^ ent;
                if (this.htab[i3] == fcode2) {
                    ent = this.codetab[i3];
                } else if (this.htab[i3] >= 0) {
                    int disp = hsize_reg - i3;
                    if (i3 == 0) {
                        disp = 1;
                    }
                    while (true) {
                        int i4 = i3 - disp;
                        i3 = i4;
                        if (i4 < 0) {
                            i3 += hsize_reg;
                        }
                        if (this.htab[i3] != fcode2) {
                            if (this.htab[i3] < 0) {
                                break;
                            }
                        } else {
                            ent = this.codetab[i3];
                            break;
                        }
                    }
                } else {
                    output(ent, outs);
                    ent = c;
                    if (this.free_ent < this.maxmaxcode) {
                        int i5 = this.free_ent;
                        int i6 = i5 + 1;
                        this.free_ent = i6;
                        this.codetab[i3] = i5;
                        this.htab[i3] = fcode2;
                    } else {
                        cl_block(outs);
                    }
                }
            } else {
                output(ent, outs);
                output(this.EOFCode, outs);
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void encode(OutputStream outputStream) throws IOException {
        OutputStream os = outputStream;
        os.write(this.initCodeSize);
        this.remaining = this.imgW * this.imgH;
        this.curPixel = 0;
        compress(this.initCodeSize + 1, os);
        os.write(0);
    }

    /* access modifiers changed from: package-private */
    public void flush_char(OutputStream outputStream) throws IOException {
        OutputStream outs = outputStream;
        if (this.a_count > 0) {
            outs.write(this.a_count);
            outs.write(this.accum, 0, this.a_count);
            this.a_count = 0;
        }
    }

    /* access modifiers changed from: package-private */
    public final int MAXCODE(int n_bits2) {
        return (1 << n_bits2) - 1;
    }

    private int nextPixel() {
        if (this.remaining == 0) {
            return -1;
        }
        this.remaining--;
        byte[] bArr = this.pixAry;
        int i = this.curPixel;
        int i2 = i + 1;
        this.curPixel = i2;
        return bArr[i] & Ev3Constants.Opcode.TST;
    }

    /* access modifiers changed from: package-private */
    public void output(int i, OutputStream outputStream) throws IOException {
        int code = i;
        OutputStream outs = outputStream;
        this.cur_accum &= this.masks[this.cur_bits];
        if (this.cur_bits > 0) {
            this.cur_accum |= code << this.cur_bits;
        } else {
            this.cur_accum = code;
        }
        this.cur_bits += this.n_bits;
        while (this.cur_bits >= 8) {
            char_out((byte) (this.cur_accum & 255), outs);
            this.cur_accum >>= 8;
            this.cur_bits -= 8;
        }
        if (this.free_ent > this.maxcode || this.clear_flg) {
            if (this.clear_flg) {
                int i2 = this.g_init_bits;
                int i3 = i2;
                this.n_bits = i3;
                this.maxcode = MAXCODE(i2);
                this.clear_flg = false;
            } else {
                this.n_bits++;
                if (this.n_bits == this.maxbits) {
                    this.maxcode = this.maxmaxcode;
                } else {
                    this.maxcode = MAXCODE(this.n_bits);
                }
            }
        }
        if (code == this.EOFCode) {
            while (this.cur_bits > 0) {
                char_out((byte) (this.cur_accum & 255), outs);
                this.cur_accum >>= 8;
                this.cur_bits -= 8;
            }
            flush_char(outs);
        }
    }
}

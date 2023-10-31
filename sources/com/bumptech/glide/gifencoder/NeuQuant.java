package com.bumptech.glide.gifencoder;

import com.firebase.client.core.ValidationPath;
import com.google.appinventor.components.runtime.util.Ev3Constants;

class NeuQuant {
    protected static final int alphabiasshift = 10;
    protected static final int alpharadbias = 262144;
    protected static final int alpharadbshift = 18;
    protected static final int beta = 64;
    protected static final int betagamma = 65536;
    protected static final int betashift = 10;
    protected static final int gamma = 1024;
    protected static final int gammashift = 10;
    protected static final int initalpha = 1024;
    protected static final int initrad = 32;
    protected static final int initradius = 2048;
    protected static final int intbias = 65536;
    protected static final int intbiasshift = 16;
    protected static final int maxnetpos = 255;
    protected static final int minpicturebytes = 1509;
    protected static final int ncycles = 100;
    protected static final int netbiasshift = 4;
    protected static final int netsize = 256;
    protected static final int prime1 = 499;
    protected static final int prime2 = 491;
    protected static final int prime3 = 487;
    protected static final int prime4 = 503;
    protected static final int radbias = 256;
    protected static final int radbiasshift = 8;
    protected static final int radiusbias = 64;
    protected static final int radiusbiasshift = 6;
    protected static final int radiusdec = 30;
    protected int alphadec;
    protected int[] bias = new int[256];
    protected int[] freq = new int[256];
    protected int lengthcount;
    protected int[] netindex = new int[256];
    protected int[][] network;
    protected int[] radpower = new int[32];
    protected int samplefac;
    protected byte[] thepicture;

    public NeuQuant(byte[] thepic, int len, int sample) {
        this.thepicture = thepic;
        this.lengthcount = len;
        this.samplefac = sample;
        this.network = new int[256][];
        for (int i = 0; i < 256; i++) {
            this.network[i] = new int[4];
            int[] p = this.network[i];
            int i2 = (i << 12) / 256;
            p[2] = i2;
            int i3 = i2;
            p[1] = i3;
            p[0] = i3;
            this.freq[i] = 256;
            this.bias[i] = 0;
        }
    }

    public byte[] colorMap() {
        byte[] map = new byte[ValidationPath.MAX_PATH_LENGTH_BYTES];
        int[] index = new int[256];
        for (int i = 0; i < 256; i++) {
            index[this.network[i][3]] = i;
        }
        int k = 0;
        for (int i2 = 0; i2 < 256; i2++) {
            int j = index[i2];
            int i3 = k;
            int k2 = k + 1;
            map[i3] = (byte) this.network[j][0];
            int i4 = k2;
            int k3 = k2 + 1;
            map[i4] = (byte) this.network[j][1];
            int i5 = k3;
            k = k3 + 1;
            map[i5] = (byte) this.network[j][2];
        }
        return map;
    }

    public void inxbuild() {
        int previouscol = 0;
        int startpos = 0;
        for (int i = 0; i < 256; i++) {
            int[] p = this.network[i];
            int smallpos = i;
            int smallval = p[1];
            for (int j = i + 1; j < 256; j++) {
                int[] q = this.network[j];
                if (q[1] < smallval) {
                    smallpos = j;
                    smallval = q[1];
                }
            }
            int[] q2 = this.network[smallpos];
            if (i != smallpos) {
                int j2 = q2[0];
                q2[0] = p[0];
                p[0] = j2;
                int j3 = q2[1];
                q2[1] = p[1];
                p[1] = j3;
                int j4 = q2[2];
                q2[2] = p[2];
                p[2] = j4;
                int j5 = q2[3];
                q2[3] = p[3];
                p[3] = j5;
            }
            if (smallval != previouscol) {
                this.netindex[previouscol] = (startpos + i) >> 1;
                for (int j6 = previouscol + 1; j6 < smallval; j6++) {
                    this.netindex[j6] = i;
                }
                previouscol = smallval;
                startpos = i;
            }
        }
        this.netindex[previouscol] = (startpos + 255) >> 1;
        for (int j7 = previouscol + 1; j7 < 256; j7++) {
            this.netindex[j7] = 255;
        }
    }

    public void learn() {
        int rad;
        int step;
        if (this.lengthcount < minpicturebytes) {
            this.samplefac = 1;
        }
        this.alphadec = 30 + ((this.samplefac - 1) / 3);
        byte[] p = this.thepicture;
        int pix = 0;
        int lim = this.lengthcount;
        int samplepixels = this.lengthcount / (3 * this.samplefac);
        int delta = samplepixels / 100;
        int alpha = 1024;
        int radius = 2048;
        int rad2 = 2048 >> 6;
        if (rad2 <= 1) {
            rad2 = 0;
        }
        for (int i = 0; i < rad; i++) {
            this.radpower[i] = 1024 * ((((rad * rad) - (i * i)) * 256) / (rad * rad));
        }
        if (this.lengthcount < minpicturebytes) {
            step = 3;
        } else {
            if (this.lengthcount % prime1 != 0) {
                step = 1497;
            } else {
                if (this.lengthcount % prime2 != 0) {
                    step = 1473;
                } else {
                    step = this.lengthcount % prime3 != 0 ? 1461 : minpicturebytes;
                }
            }
        }
        int i2 = 0;
        while (i2 < samplepixels) {
            int b = (p[pix + 0] & Ev3Constants.Opcode.TST) << 4;
            int g = (p[pix + 1] & Ev3Constants.Opcode.TST) << 4;
            int r = (p[pix + 2] & Ev3Constants.Opcode.TST) << 4;
            int j = contest(b, g, r);
            altersingle(alpha, j, b, g, r);
            if (rad != 0) {
                alterneigh(rad, j, b, g, r);
            }
            pix += step;
            if (pix >= lim) {
                pix -= this.lengthcount;
            }
            i2++;
            if (delta == 0) {
                delta = 1;
            }
            if (i2 % delta == 0) {
                alpha -= alpha / this.alphadec;
                radius -= radius / 30;
                rad = radius >> 6;
                if (rad <= 1) {
                    rad = 0;
                }
                for (int j2 = 0; j2 < rad; j2++) {
                    this.radpower[j2] = alpha * ((((rad * rad) - (j2 * j2)) * 256) / (rad * rad));
                }
            }
        }
    }

    public int map(int i, int i2, int i3) {
        int b = i;
        int g = i2;
        int r = i3;
        int bestd = 1000;
        int best = -1;
        int i4 = this.netindex[g];
        int j = i4 - 1;
        while (true) {
            if (i4 >= 256 && j < 0) {
                return best;
            }
            if (i4 < 256) {
                int[] p = this.network[i4];
                int dist = p[1] - g;
                if (dist >= bestd) {
                    i4 = 256;
                } else {
                    i4++;
                    if (dist < 0) {
                        dist = -dist;
                    }
                    int a = p[0] - b;
                    if (a < 0) {
                        a = -a;
                    }
                    int dist2 = dist + a;
                    if (dist2 < bestd) {
                        int a2 = p[2] - r;
                        if (a2 < 0) {
                            a2 = -a2;
                        }
                        int dist3 = dist2 + a2;
                        if (dist3 < bestd) {
                            bestd = dist3;
                            best = p[3];
                        }
                    }
                }
            }
            if (j >= 0) {
                int[] p2 = this.network[j];
                int dist4 = g - p2[1];
                if (dist4 >= bestd) {
                    j = -1;
                } else {
                    j--;
                    if (dist4 < 0) {
                        dist4 = -dist4;
                    }
                    int a3 = p2[0] - b;
                    if (a3 < 0) {
                        a3 = -a3;
                    }
                    int dist5 = dist4 + a3;
                    if (dist5 < bestd) {
                        int a4 = p2[2] - r;
                        if (a4 < 0) {
                            a4 = -a4;
                        }
                        int dist6 = dist5 + a4;
                        if (dist6 < bestd) {
                            bestd = dist6;
                            best = p2[3];
                        }
                    }
                }
            }
        }
    }

    public byte[] process() {
        learn();
        unbiasnet();
        inxbuild();
        return colorMap();
    }

    public void unbiasnet() {
        for (int i = 0; i < 256; i++) {
            int[] iArr = this.network[i];
            iArr[0] = iArr[0] >> 4;
            int[] iArr2 = this.network[i];
            iArr2[1] = iArr2[1] >> 4;
            int[] iArr3 = this.network[i];
            iArr3[2] = iArr3[2] >> 4;
            this.network[i][3] = i;
        }
    }

    /* access modifiers changed from: protected */
    public void alterneigh(int i, int i2, int i3, int i4, int i5) {
        int rad = i;
        int i6 = i2;
        int b = i3;
        int g = i4;
        int r = i5;
        int lo = i6 - rad;
        if (lo < -1) {
            lo = -1;
        }
        int hi = i6 + rad;
        if (hi > 256) {
            hi = 256;
        }
        int j = i6 + 1;
        int k = i6 - 1;
        int m = 1;
        while (true) {
            if (j < hi || k > lo) {
                int i7 = m;
                m++;
                int a = this.radpower[i7];
                if (j < hi) {
                    int i8 = j;
                    j++;
                    int[] p = this.network[i8];
                    int[] iArr = p;
                    try {
                        iArr[0] = iArr[0] - ((a * (p[0] - b)) / 262144);
                        int[] iArr2 = p;
                        iArr2[1] = iArr2[1] - ((a * (p[1] - g)) / 262144);
                        int[] iArr3 = p;
                        iArr3[2] = iArr3[2] - ((a * (p[2] - r)) / 262144);
                    } catch (Exception e) {
                        Exception exc = e;
                    }
                }
                if (k > lo) {
                    int i9 = k;
                    k--;
                    int[] p2 = this.network[i9];
                    int[] iArr4 = p2;
                    try {
                        iArr4[0] = iArr4[0] - ((a * (p2[0] - b)) / 262144);
                        int[] iArr5 = p2;
                        iArr5[1] = iArr5[1] - ((a * (p2[1] - g)) / 262144);
                        int[] iArr6 = p2;
                        iArr6[2] = iArr6[2] - ((a * (p2[2] - r)) / 262144);
                    } catch (Exception e2) {
                        Exception exc2 = e2;
                    }
                }
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void altersingle(int i, int i2, int b, int g, int r) {
        int alpha = i;
        int[] n = this.network[i2];
        int[] iArr = n;
        iArr[0] = iArr[0] - ((alpha * (n[0] - b)) / 1024);
        int[] iArr2 = n;
        iArr2[1] = iArr2[1] - ((alpha * (n[1] - g)) / 1024);
        int[] iArr3 = n;
        iArr3[2] = iArr3[2] - ((alpha * (n[2] - r)) / 1024);
    }

    /* access modifiers changed from: protected */
    public int contest(int i, int i2, int i3) {
        int b = i;
        int g = i2;
        int r = i3;
        int bestd = Integer.MAX_VALUE;
        int bestbiasd = Integer.MAX_VALUE;
        int bestpos = -1;
        int bestbiaspos = -1;
        for (int i4 = 0; i4 < 256; i4++) {
            int[] n = this.network[i4];
            int dist = n[0] - b;
            if (dist < 0) {
                dist = -dist;
            }
            int a = n[1] - g;
            if (a < 0) {
                a = -a;
            }
            int dist2 = dist + a;
            int a2 = n[2] - r;
            if (a2 < 0) {
                a2 = -a2;
            }
            int dist3 = dist2 + a2;
            if (dist3 < bestd) {
                bestd = dist3;
                bestpos = i4;
            }
            int biasdist = dist3 - (this.bias[i4] >> 12);
            if (biasdist < bestbiasd) {
                bestbiasd = biasdist;
                bestbiaspos = i4;
            }
            int betafreq = this.freq[i4] >> 10;
            int[] iArr = this.freq;
            int i5 = i4;
            iArr[i5] = iArr[i5] - betafreq;
            int[] iArr2 = this.bias;
            int i6 = i4;
            iArr2[i6] = iArr2[i6] + (betafreq << 10);
        }
        int[] iArr3 = this.freq;
        int i7 = bestpos;
        iArr3[i7] = iArr3[i7] + 64;
        int[] iArr4 = this.bias;
        int i8 = bestpos;
        iArr4[i8] = iArr4[i8] - 65536;
        return bestbiaspos;
    }
}

package com.bumptech.glide.gifencoder;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import com.google.appinventor.components.runtime.util.ScreenDensityUtil;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class AnimatedGifEncoder {
    private static final double MIN_TRANSPARENT_PERCENTAGE = 4.0d;
    private static final String TAG = "AnimatedGifEncoder";
    private boolean closeStream = false;
    private int colorDepth;
    private byte[] colorTab;
    private int delay = 0;
    private int dispose = -1;
    private boolean firstFrame = true;
    private boolean hasTransparentPixels;
    private int height;
    private Bitmap image;
    private byte[] indexedPixels;
    private OutputStream out;
    private int palSize = 7;
    private byte[] pixels;
    private int repeat = -1;
    private int sample = 10;
    private boolean sizeSet = false;
    private boolean started = false;
    private int transIndex;
    private Integer transparent = null;
    private boolean[] usedEntry = new boolean[256];
    private int width;

    public AnimatedGifEncoder() {
    }

    public void setDelay(int ms) {
        int round = Math.round(((float) ms) / 10.0f);
        this.delay = round;
    }

    public void setDispose(int i) {
        int code = i;
        if (code >= 0) {
            this.dispose = code;
        }
    }

    public void setRepeat(int i) {
        int iter = i;
        if (iter >= 0) {
            this.repeat = iter;
        }
    }

    public void setTransparent(int color) {
        Integer valueOf = Integer.valueOf(color);
        this.transparent = valueOf;
    }

    public boolean addFrame(Bitmap bitmap) {
        Bitmap im = bitmap;
        if (im == null || !this.started) {
            return false;
        }
        boolean ok = true;
        try {
            if (!this.sizeSet) {
                setSize(im.getWidth(), im.getHeight());
            }
            this.image = im;
            getImagePixels();
            analyzePixels();
            if (this.firstFrame) {
                writeLSD();
                writePalette();
                if (this.repeat >= 0) {
                    writeNetscapeExt();
                }
            }
            writeGraphicCtrlExt();
            writeImageDesc();
            if (!this.firstFrame) {
                writePalette();
            }
            writePixels();
            this.firstFrame = false;
        } catch (IOException e) {
            IOException iOException = e;
            ok = false;
        }
        return ok;
    }

    public boolean finish() {
        if (!this.started) {
            return false;
        }
        boolean ok = true;
        this.started = false;
        try {
            this.out.write(59);
            this.out.flush();
            if (this.closeStream) {
                this.out.close();
            }
        } catch (IOException e) {
            IOException iOException = e;
            ok = false;
        }
        this.transIndex = 0;
        this.out = null;
        this.image = null;
        this.pixels = null;
        this.indexedPixels = null;
        this.colorTab = null;
        this.closeStream = false;
        this.firstFrame = true;
        return ok;
    }

    public void setFrameRate(float f) {
        float fps = f;
        if (fps != 0.0f) {
            this.delay = Math.round(100.0f / fps);
        }
    }

    public void setQuality(int i) {
        int quality = i;
        if (quality < 1) {
            quality = 1;
        }
        this.sample = quality;
    }

    public void setSize(int i, int i2) {
        int w = i;
        int h = i2;
        if (!this.started || this.firstFrame) {
            this.width = w;
            this.height = h;
            if (this.width < 1) {
                this.width = ScreenDensityUtil.DEFAULT_NORMAL_SHORT_DIMENSION;
            }
            if (this.height < 1) {
                this.height = 240;
            }
            this.sizeSet = true;
        }
    }

    public boolean start(OutputStream outputStream) {
        OutputStream os = outputStream;
        if (os == null) {
            return false;
        }
        boolean ok = true;
        this.closeStream = false;
        this.out = os;
        try {
            writeString("GIF89a");
        } catch (IOException e) {
            IOException iOException = e;
            ok = false;
        }
        boolean z = ok;
        boolean z2 = z;
        this.started = z2;
        return z;
    }

    public boolean start(String file) {
        boolean ok;
        OutputStream outputStream;
        OutputStream outputStream2;
        try {
            new FileOutputStream(file);
            new BufferedOutputStream(outputStream2);
            this.out = outputStream;
            ok = start(this.out);
            this.closeStream = true;
        } catch (IOException e) {
            IOException iOException = e;
            ok = false;
        }
        boolean z = ok;
        boolean z2 = z;
        this.started = z2;
        return z;
    }

    private void analyzePixels() {
        NeuQuant neuQuant;
        int len = this.pixels.length;
        int nPix = len / 3;
        this.indexedPixels = new byte[nPix];
        new NeuQuant(this.pixels, len, this.sample);
        NeuQuant nq = neuQuant;
        this.colorTab = nq.process();
        for (int i = 0; i < this.colorTab.length; i += 3) {
            byte temp = this.colorTab[i];
            this.colorTab[i] = this.colorTab[i + 2];
            this.colorTab[i + 2] = temp;
            this.usedEntry[i / 3] = false;
        }
        int k = 0;
        for (int i2 = 0; i2 < nPix; i2++) {
            int i3 = k;
            int k2 = k + 1;
            int i4 = k2;
            int k3 = k2 + 1;
            int i5 = k3;
            k = k3 + 1;
            int index = nq.map(this.pixels[i3] & Ev3Constants.Opcode.TST, this.pixels[i4] & Ev3Constants.Opcode.TST, this.pixels[i5] & Ev3Constants.Opcode.TST);
            this.usedEntry[index] = true;
            this.indexedPixels[i2] = (byte) index;
        }
        this.pixels = null;
        this.colorDepth = 8;
        this.palSize = 7;
        if (this.transparent != null) {
            this.transIndex = findClosest(this.transparent.intValue());
        } else if (this.hasTransparentPixels) {
            this.transIndex = findClosest(0);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v16, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v18, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v20, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int findClosest(int r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r14 = r0
            byte[] r14 = r14.colorTab
            if (r14 != 0) goto L_0x000c
            r14 = -1
            r0 = r14
        L_0x000b:
            return r0
        L_0x000c:
            r14 = r1
            int r14 = android.graphics.Color.red(r14)
            r2 = r14
            r14 = r1
            int r14 = android.graphics.Color.green(r14)
            r3 = r14
            r14 = r1
            int r14 = android.graphics.Color.blue(r14)
            r4 = r14
            r14 = 0
            r5 = r14
            r14 = 16777216(0x1000000, float:2.3509887E-38)
            r6 = r14
            r14 = r0
            byte[] r14 = r14.colorTab
            int r14 = r14.length
            r7 = r14
            r14 = 0
            r8 = r14
        L_0x002a:
            r14 = r8
            r15 = r7
            if (r14 >= r15) goto L_0x0084
            r14 = r2
            r15 = r0
            byte[] r15 = r15.colorTab
            r16 = r8
            int r8 = r8 + 1
            byte r15 = r15[r16]
            r16 = 255(0xff, float:3.57E-43)
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r14 = r14 - r15
            r9 = r14
            r14 = r3
            r15 = r0
            byte[] r15 = r15.colorTab
            r16 = r8
            int r8 = r8 + 1
            byte r15 = r15[r16]
            r16 = 255(0xff, float:3.57E-43)
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r14 = r14 - r15
            r10 = r14
            r14 = r4
            r15 = r0
            byte[] r15 = r15.colorTab
            r16 = r8
            byte r15 = r15[r16]
            r16 = 255(0xff, float:3.57E-43)
            r15 = r15 & 255(0xff, float:3.57E-43)
            int r14 = r14 - r15
            r11 = r14
            r14 = r9
            r15 = r9
            int r14 = r14 * r15
            r15 = r10
            r16 = r10
            int r15 = r15 * r16
            int r14 = r14 + r15
            r15 = r11
            r16 = r11
            int r15 = r15 * r16
            int r14 = r14 + r15
            r12 = r14
            r14 = r8
            r15 = 3
            int r14 = r14 / 3
            r13 = r14
            r14 = r0
            boolean[] r14 = r14.usedEntry
            r15 = r13
            boolean r14 = r14[r15]
            if (r14 == 0) goto L_0x0081
            r14 = r12
            r15 = r6
            if (r14 >= r15) goto L_0x0081
            r14 = r12
            r6 = r14
            r14 = r13
            r5 = r14
        L_0x0081:
            int r8 = r8 + 1
            goto L_0x002a
        L_0x0084:
            r14 = r5
            r0 = r14
            goto L_0x000b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.gifencoder.AnimatedGifEncoder.findClosest(int):int");
    }

    private void getImagePixels() {
        StringBuilder sb;
        Canvas canvas;
        int w = this.image.getWidth();
        int h = this.image.getHeight();
        if (!(w == this.width && h == this.height)) {
            Bitmap temp = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.ARGB_8888);
            new Canvas(temp);
            canvas.drawBitmap(temp, 0.0f, 0.0f, (Paint) null);
            this.image = temp;
        }
        int[] pixelsInt = new int[(w * h)];
        this.image.getPixels(pixelsInt, 0, w, 0, 0, w, h);
        this.pixels = new byte[(pixelsInt.length * 3)];
        int pixelsIndex = 0;
        this.hasTransparentPixels = false;
        int totalTransparentPixels = 0;
        int[] iArr = pixelsInt;
        int length = iArr.length;
        for (int i = 0; i < length; i++) {
            int pixel = iArr[i];
            if (pixel == 0) {
                totalTransparentPixels++;
            }
            int i2 = pixelsIndex;
            int pixelsIndex2 = pixelsIndex + 1;
            this.pixels[i2] = (byte) (pixel & 255);
            int i3 = pixelsIndex2;
            int pixelsIndex3 = pixelsIndex2 + 1;
            this.pixels[i3] = (byte) ((pixel >> 8) & 255);
            int i4 = pixelsIndex3;
            pixelsIndex = pixelsIndex3 + 1;
            this.pixels[i4] = (byte) ((pixel >> 16) & 255);
        }
        double transparentPercentage = ((double) (100 * totalTransparentPixels)) / ((double) pixelsInt.length);
        this.hasTransparentPixels = transparentPercentage > MIN_TRANSPARENT_PERCENTAGE;
        if (Log.isLoggable(TAG, 3)) {
            new StringBuilder();
            int d = Log.d(TAG, sb.append("got pixels for frame with ").append(transparentPercentage).append("% transparent pixels").toString());
        }
    }

    private void writeGraphicCtrlExt() throws IOException {
        int transp;
        int disp;
        this.out.write(33);
        this.out.write(249);
        this.out.write(4);
        if (this.transparent != null || this.hasTransparentPixels) {
            transp = 1;
            disp = 2;
        } else {
            transp = 0;
            disp = 0;
        }
        if (this.dispose >= 0) {
            disp = this.dispose & 7;
        }
        this.out.write(0 | (disp << 2) | 0 | transp);
        writeShort(this.delay);
        this.out.write(this.transIndex);
        this.out.write(0);
    }

    private void writeImageDesc() throws IOException {
        this.out.write(44);
        writeShort(0);
        writeShort(0);
        writeShort(this.width);
        writeShort(this.height);
        if (this.firstFrame) {
            this.out.write(0);
        } else {
            this.out.write(128 | this.palSize);
        }
    }

    private void writeLSD() throws IOException {
        writeShort(this.width);
        writeShort(this.height);
        this.out.write(240 | this.palSize);
        this.out.write(0);
        this.out.write(0);
    }

    private void writeNetscapeExt() throws IOException {
        this.out.write(33);
        this.out.write(255);
        this.out.write(11);
        writeString("NETSCAPE2.0");
        this.out.write(3);
        this.out.write(1);
        writeShort(this.repeat);
        this.out.write(0);
    }

    private void writePalette() throws IOException {
        this.out.write(this.colorTab, 0, this.colorTab.length);
        int n = 768 - this.colorTab.length;
        for (int i = 0; i < n; i++) {
            this.out.write(0);
        }
    }

    private void writePixels() throws IOException {
        LZWEncoder encoder;
        new LZWEncoder(this.width, this.height, this.indexedPixels, this.colorDepth);
        encoder.encode(this.out);
    }

    private void writeShort(int i) throws IOException {
        int value = i;
        this.out.write(value & 255);
        this.out.write((value >> 8) & 255);
    }

    private void writeString(String str) throws IOException {
        String s = str;
        for (int i = 0; i < s.length(); i++) {
            this.out.write((byte) s.charAt(i));
        }
    }
}

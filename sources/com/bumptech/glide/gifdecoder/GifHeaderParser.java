package com.bumptech.glide.gifdecoder;

import android.util.Log;
import java.nio.Buffer;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import kawa.Telnet;

public class GifHeaderParser {
    static final int DEFAULT_FRAME_DELAY = 10;
    private static final int MAX_BLOCK_SIZE = 256;
    static final int MIN_FRAME_DELAY = 3;
    public static final String TAG = "GifHeaderParser";
    private final byte[] block = new byte[256];
    private int blockSize = 0;
    private GifHeader header;
    private ByteBuffer rawData;

    public GifHeaderParser() {
    }

    public GifHeaderParser setData(byte[] bArr) {
        byte[] data = bArr;
        reset();
        if (data != null) {
            this.rawData = ByteBuffer.wrap(data);
            Buffer rewind = this.rawData.rewind();
            ByteBuffer order = this.rawData.order(ByteOrder.LITTLE_ENDIAN);
        } else {
            this.rawData = null;
            this.header.status = 2;
        }
        return this;
    }

    public void clear() {
        this.rawData = null;
        this.header = null;
    }

    private void reset() {
        GifHeader gifHeader;
        this.rawData = null;
        Arrays.fill(this.block, (byte) 0);
        new GifHeader();
        this.header = gifHeader;
        this.blockSize = 0;
    }

    public GifHeader parseHeader() {
        Throwable th;
        if (this.rawData == null) {
            Throwable th2 = th;
            new IllegalStateException("You must call setData() before parseHeader()");
            throw th2;
        } else if (err()) {
            return this.header;
        } else {
            readHeader();
            if (!err()) {
                readContents();
                if (this.header.frameCount < 0) {
                    this.header.status = 1;
                }
            }
            return this.header;
        }
    }

    private void readContents() {
        StringBuilder sb;
        GifFrame gifFrame;
        GifFrame gifFrame2;
        boolean done = false;
        while (!done && !err()) {
            switch (read()) {
                case 33:
                    switch (read()) {
                        case 1:
                            skip();
                            break;
                        case 249:
                            GifHeader gifHeader = this.header;
                            new GifFrame();
                            gifHeader.currentFrame = gifFrame;
                            readGraphicControlExt();
                            break;
                        case Telnet.DONT:
                            skip();
                            break;
                        case 255:
                            int readBlock = readBlock();
                            String app = "";
                            for (int i = 0; i < 11; i++) {
                                new StringBuilder();
                                app = sb.append(app).append((char) this.block[i]).toString();
                            }
                            if (!app.equals("NETSCAPE2.0")) {
                                skip();
                                break;
                            } else {
                                readNetscapeExt();
                                break;
                            }
                        default:
                            skip();
                            break;
                    }
                case 44:
                    if (this.header.currentFrame == null) {
                        GifHeader gifHeader2 = this.header;
                        new GifFrame();
                        gifHeader2.currentFrame = gifFrame2;
                    }
                    readBitmap();
                    break;
                case 59:
                    done = true;
                    break;
                default:
                    this.header.status = 1;
                    break;
            }
        }
    }

    private void readGraphicControlExt() {
        int read = read();
        int packed = read();
        this.header.currentFrame.dispose = (packed & 28) >> 2;
        if (this.header.currentFrame.dispose == 0) {
            this.header.currentFrame.dispose = 1;
        }
        this.header.currentFrame.transparency = (packed & 1) != 0;
        int delayInHundredthsOfASecond = readShort();
        if (delayInHundredthsOfASecond < 3) {
            delayInHundredthsOfASecond = 10;
        }
        this.header.currentFrame.delay = delayInHundredthsOfASecond * 10;
        this.header.currentFrame.transIndex = read();
        int read2 = read();
    }

    private void readBitmap() {
        this.header.currentFrame.f304ix = readShort();
        this.header.currentFrame.f305iy = readShort();
        this.header.currentFrame.f303iw = readShort();
        this.header.currentFrame.f302ih = readShort();
        int packed = read();
        boolean lctFlag = (packed & 128) != 0;
        int lctSize = (int) Math.pow(2.0d, (double) ((packed & 7) + 1));
        this.header.currentFrame.interlace = (packed & 64) != 0;
        if (lctFlag) {
            this.header.currentFrame.lct = readColorTable(lctSize);
        } else {
            this.header.currentFrame.lct = null;
        }
        this.header.currentFrame.bufferFrameStart = this.rawData.position();
        skipImageData();
        if (!err()) {
            this.header.frameCount++;
            boolean add = this.header.frames.add(this.header.currentFrame);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x000f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void readNetscapeExt() {
        /*
            r6 = this;
            r0 = r6
        L_0x0001:
            r3 = r0
            int r3 = r3.readBlock()
            r3 = r0
            byte[] r3 = r3.block
            r4 = 0
            byte r3 = r3[r4]
            r4 = 1
            if (r3 != r4) goto L_0x0031
            r3 = r0
            byte[] r3 = r3.block
            r4 = 1
            byte r3 = r3[r4]
            r4 = 255(0xff, float:3.57E-43)
            r3 = r3 & 255(0xff, float:3.57E-43)
            r1 = r3
            r3 = r0
            byte[] r3 = r3.block
            r4 = 2
            byte r3 = r3[r4]
            r4 = 255(0xff, float:3.57E-43)
            r3 = r3 & 255(0xff, float:3.57E-43)
            r2 = r3
            r3 = r0
            com.bumptech.glide.gifdecoder.GifHeader r3 = r3.header
            r4 = r2
            r5 = 8
            int r4 = r4 << 8
            r5 = r1
            r4 = r4 | r5
            r3.loopCount = r4
        L_0x0031:
            r3 = r0
            int r3 = r3.blockSize
            if (r3 <= 0) goto L_0x003d
            r3 = r0
            boolean r3 = r3.err()
            if (r3 == 0) goto L_0x0001
        L_0x003d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.gifdecoder.GifHeaderParser.readNetscapeExt():void");
    }

    private void readHeader() {
        StringBuilder sb;
        String id = "";
        for (int i = 0; i < 6; i++) {
            new StringBuilder();
            id = sb.append(id).append((char) read()).toString();
        }
        if (!id.startsWith("GIF")) {
            this.header.status = 1;
            return;
        }
        readLSD();
        if (this.header.gctFlag && !err()) {
            this.header.gct = readColorTable(this.header.gctSize);
            this.header.bgColor = this.header.gct[this.header.bgIndex];
        }
    }

    private void readLSD() {
        this.header.width = readShort();
        this.header.height = readShort();
        int packed = read();
        this.header.gctFlag = (packed & 128) != 0;
        this.header.gctSize = 2 << (packed & 7);
        this.header.bgIndex = read();
        this.header.pixelAspect = read();
    }

    private int[] readColorTable(int i) {
        int ncolors = i;
        int[] tab = null;
        byte[] c = new byte[(3 * ncolors)];
        try {
            ByteBuffer byteBuffer = this.rawData.get(c);
            tab = new int[256];
            int i2 = 0;
            int j = 0;
            while (i2 < ncolors) {
                int i3 = j;
                int j2 = j + 1;
                int r = c[i3] & 255;
                int i4 = j2;
                int j3 = j2 + 1;
                int g = c[i4] & 255;
                int i5 = j3;
                j = j3 + 1;
                int b = c[i5] & 255;
                int i6 = i2;
                i2++;
                tab[i6] = -16777216 | (r << 16) | (g << 8) | b;
            }
        } catch (BufferUnderflowException e) {
            BufferUnderflowException e2 = e;
            if (Log.isLoggable(TAG, 3)) {
                int d = Log.d(TAG, "Format Error Reading Color Table", e2);
            }
            this.header.status = 1;
        }
        return tab;
    }

    private void skipImageData() {
        int read = read();
        skip();
    }

    private void skip() {
        int blockSize2;
        do {
            blockSize2 = read();
            Buffer position = this.rawData.position(this.rawData.position() + blockSize2);
        } while (blockSize2 > 0);
    }

    private int readBlock() {
        StringBuilder sb;
        this.blockSize = read();
        int n = 0;
        if (this.blockSize > 0) {
            int count = 0;
            while (n < this.blockSize) {
                try {
                    count = this.blockSize - n;
                    ByteBuffer byteBuffer = this.rawData.get(this.block, n, count);
                    n += count;
                } catch (Exception e) {
                    Exception e2 = e;
                    if (Log.isLoggable(TAG, 3)) {
                        new StringBuilder();
                        int d = Log.d(TAG, sb.append("Error Reading Block n: ").append(n).append(" count: ").append(count).append(" blockSize: ").append(this.blockSize).toString(), e2);
                    }
                    this.header.status = 1;
                }
            }
        }
        return n;
    }

    private int read() {
        int curByte = 0;
        try {
            curByte = this.rawData.get() & 255;
        } catch (Exception e) {
            Exception exc = e;
            this.header.status = 1;
        }
        return curByte;
    }

    private int readShort() {
        return this.rawData.getShort();
    }

    private boolean err() {
        return this.header.status != 0;
    }
}

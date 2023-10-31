package com.bumptech.glide.gifdecoder;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Iterator;

public class GifDecoder {
    private static final Bitmap.Config BITMAP_CONFIG = Bitmap.Config.ARGB_8888;
    private static final int DISPOSAL_BACKGROUND = 2;
    private static final int DISPOSAL_NONE = 1;
    private static final int DISPOSAL_PREVIOUS = 3;
    private static final int DISPOSAL_UNSPECIFIED = 0;
    private static final int INITIAL_FRAME_POINTER = -1;
    private static final int MAX_STACK_SIZE = 4096;
    private static final int NULL_CODE = -1;
    public static final int STATUS_FORMAT_ERROR = 1;
    public static final int STATUS_OK = 0;
    public static final int STATUS_OPEN_ERROR = 2;
    public static final int STATUS_PARTIAL_DECODE = 3;
    private static final String TAG = GifDecoder.class.getSimpleName();
    public static final int TOTAL_ITERATION_COUNT_FOREVER = 0;
    private int[] act;
    private BitmapProvider bitmapProvider;
    private final byte[] block = new byte[256];
    private byte[] data;
    private int framePointer;
    private GifHeader header;
    private byte[] mainPixels;
    private int[] mainScratch;
    private GifHeaderParser parser;
    private final int[] pct = new int[256];
    private byte[] pixelStack;
    private short[] prefix;
    private Bitmap previousImage;
    private ByteBuffer rawData;
    private boolean savePrevious;
    private int status;
    private byte[] suffix;

    public interface BitmapProvider {
        Bitmap obtain(int i, int i2, Bitmap.Config config);

        void release(Bitmap bitmap);
    }

    public GifDecoder(BitmapProvider provider) {
        GifHeader gifHeader;
        this.bitmapProvider = provider;
        new GifHeader();
        this.header = gifHeader;
    }

    public int getWidth() {
        return this.header.width;
    }

    public int getHeight() {
        return this.header.height;
    }

    public byte[] getData() {
        return this.data;
    }

    public int getStatus() {
        return this.status;
    }

    public void advance() {
        this.framePointer = (this.framePointer + 1) % this.header.frameCount;
    }

    public int getDelay(int i) {
        int n = i;
        int delay = -1;
        if (n >= 0 && n < this.header.frameCount) {
            delay = this.header.frames.get(n).delay;
        }
        return delay;
    }

    public int getNextDelay() {
        if (this.header.frameCount <= 0 || this.framePointer < 0) {
            return -1;
        }
        return getDelay(this.framePointer);
    }

    public int getFrameCount() {
        return this.header.frameCount;
    }

    public int getCurrentFrameIndex() {
        return this.framePointer;
    }

    public void resetFrameIndex() {
        this.framePointer = -1;
    }

    @Deprecated
    public int getLoopCount() {
        if (this.header.loopCount == -1) {
            return 1;
        }
        return this.header.loopCount;
    }

    public int getNetscapeLoopCount() {
        return this.header.loopCount;
    }

    public int getTotalIterationCount() {
        if (this.header.loopCount == -1) {
            return 1;
        }
        if (this.header.loopCount == 0) {
            return 0;
        }
        return this.header.loopCount + 1;
    }

    public synchronized Bitmap getNextFrame() {
        Bitmap bitmap;
        StringBuilder sb;
        StringBuilder sb2;
        synchronized (this) {
            if (this.header.frameCount <= 0 || this.framePointer < 0) {
                if (Log.isLoggable(TAG, 3)) {
                    String str = TAG;
                    new StringBuilder();
                    int d = Log.d(str, sb2.append("unable to decode frame, frameCount=").append(this.header.frameCount).append(" framePointer=").append(this.framePointer).toString());
                }
                this.status = 1;
            }
            if (this.status == 1 || this.status == 2) {
                if (Log.isLoggable(TAG, 3)) {
                    String str2 = TAG;
                    new StringBuilder();
                    int d2 = Log.d(str2, sb.append("Unable to decode frame, status=").append(this.status).toString());
                }
                bitmap = null;
            } else {
                this.status = 0;
                GifFrame currentFrame = this.header.frames.get(this.framePointer);
                GifFrame previousFrame = null;
                int previousIndex = this.framePointer - 1;
                if (previousIndex >= 0) {
                    previousFrame = this.header.frames.get(previousIndex);
                }
                this.act = currentFrame.lct != null ? currentFrame.lct : this.header.gct;
                if (this.act == null) {
                    if (Log.isLoggable(TAG, 3)) {
                        int d3 = Log.d(TAG, "No Valid Color Table");
                    }
                    this.status = 1;
                    bitmap = null;
                } else {
                    if (currentFrame.transparency) {
                        System.arraycopy(this.act, 0, this.pct, 0, this.act.length);
                        this.act = this.pct;
                        this.act[currentFrame.transIndex] = 0;
                    }
                    bitmap = setPixels(currentFrame, previousFrame);
                }
            }
        }
        return bitmap;
    }

    public int read(InputStream inputStream, int i) {
        ByteArrayOutputStream byteArrayOutputStream;
        InputStream is = inputStream;
        int contentLength = i;
        if (is != null) {
            try {
                new ByteArrayOutputStream(contentLength > 0 ? contentLength + 4096 : 16384);
                ByteArrayOutputStream buffer = byteArrayOutputStream;
                byte[] data2 = new byte[16384];
                while (true) {
                    int read = is.read(data2, 0, data2.length);
                    int nRead = read;
                    if (read == -1) {
                        break;
                    }
                    buffer.write(data2, 0, nRead);
                }
                buffer.flush();
                int read2 = read(buffer.toByteArray());
            } catch (IOException e) {
                int w = Log.w(TAG, "Error reading data from stream", e);
            }
        } else {
            this.status = 2;
        }
        if (is != null) {
            try {
                is.close();
            } catch (IOException e2) {
                int w2 = Log.w(TAG, "Error closing stream", e2);
            }
        }
        return this.status;
    }

    public void clear() {
        this.header = null;
        this.data = null;
        this.mainPixels = null;
        this.mainScratch = null;
        if (this.previousImage != null) {
            this.bitmapProvider.release(this.previousImage);
        }
        this.previousImage = null;
        this.rawData = null;
    }

    public void setData(GifHeader gifHeader, byte[] bArr) {
        GifHeader header2 = gifHeader;
        byte[] data2 = bArr;
        this.header = header2;
        this.data = data2;
        this.status = 0;
        this.framePointer = -1;
        this.rawData = ByteBuffer.wrap(data2);
        Buffer rewind = this.rawData.rewind();
        ByteBuffer order = this.rawData.order(ByteOrder.LITTLE_ENDIAN);
        this.savePrevious = false;
        Iterator<GifFrame> it = header2.frames.iterator();
        while (true) {
            if (it.hasNext()) {
                if (it.next().dispose == 3) {
                    this.savePrevious = true;
                    break;
                }
            } else {
                break;
            }
        }
        this.mainPixels = new byte[(header2.width * header2.height)];
        this.mainScratch = new int[(header2.width * header2.height)];
    }

    private GifHeaderParser getHeaderParser() {
        GifHeaderParser gifHeaderParser;
        if (this.parser == null) {
            new GifHeaderParser();
            this.parser = gifHeaderParser;
        }
        return this.parser;
    }

    public int read(byte[] bArr) {
        byte[] data2 = bArr;
        this.data = data2;
        this.header = getHeaderParser().setData(data2).parseHeader();
        if (data2 != null) {
            this.rawData = ByteBuffer.wrap(data2);
            Buffer rewind = this.rawData.rewind();
            ByteBuffer order = this.rawData.order(ByteOrder.LITTLE_ENDIAN);
            this.mainPixels = new byte[(this.header.width * this.header.height)];
            this.mainScratch = new int[(this.header.width * this.header.height)];
            this.savePrevious = false;
            Iterator<GifFrame> it = this.header.frames.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().dispose == 3) {
                        this.savePrevious = true;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        return this.status;
    }

    private Bitmap setPixels(GifFrame gifFrame, GifFrame gifFrame2) {
        GifFrame currentFrame = gifFrame;
        GifFrame previousFrame = gifFrame2;
        int width = this.header.width;
        int height = this.header.height;
        int[] dest = this.mainScratch;
        if (previousFrame == null) {
            Arrays.fill(dest, 0);
        }
        if (previousFrame != null && previousFrame.dispose > 0) {
            if (previousFrame.dispose == 2) {
                int c = 0;
                if (!currentFrame.transparency) {
                    c = this.header.bgColor;
                    if (currentFrame.lct != null) {
                        if (this.header.bgIndex == currentFrame.transIndex) {
                            c = 0;
                        }
                    }
                }
                int topLeft = (previousFrame.f305iy * width) + previousFrame.f304ix;
                int bottomLeft = topLeft + (previousFrame.f302ih * width);
                int i = topLeft;
                while (true) {
                    int left = i;
                    if (left >= bottomLeft) {
                        break;
                    }
                    int right = left + previousFrame.f303iw;
                    for (int pointer = left; pointer < right; pointer++) {
                        dest[pointer] = c;
                    }
                    i = left + width;
                }
            } else if (previousFrame.dispose == 3) {
                if (this.previousImage != null) {
                    this.previousImage.getPixels(dest, 0, width, 0, 0, width, height);
                }
            }
        }
        decodeBitmapData(currentFrame);
        int pass = 1;
        int inc = 8;
        int iline = 0;
        for (int i2 = 0; i2 < currentFrame.f302ih; i2++) {
            int line = i2;
            if (currentFrame.interlace) {
                if (iline >= currentFrame.f302ih) {
                    pass++;
                    switch (pass) {
                        case 2:
                            iline = 4;
                            break;
                        case 3:
                            iline = 2;
                            inc = 4;
                            break;
                        case 4:
                            iline = 1;
                            inc = 2;
                            break;
                    }
                }
                line = iline;
                iline += inc;
            }
            int line2 = line + currentFrame.f305iy;
            if (line2 < this.header.height) {
                int k = line2 * this.header.width;
                int dx = k + currentFrame.f304ix;
                int dlim = dx + currentFrame.f303iw;
                if (k + this.header.width < dlim) {
                    dlim = k + this.header.width;
                }
                int sx = i2 * currentFrame.f303iw;
                while (dx < dlim) {
                    int i3 = sx;
                    sx++;
                    int c2 = this.act[this.mainPixels[i3] & 255];
                    if (c2 != 0) {
                        dest[dx] = c2;
                    }
                    dx++;
                }
            }
        }
        if (this.savePrevious && (currentFrame.dispose == 0 || currentFrame.dispose == 1)) {
            if (this.previousImage == null) {
                this.previousImage = getNextBitmap();
            }
            this.previousImage.setPixels(dest, 0, width, 0, 0, width, height);
        }
        Bitmap result = getNextBitmap();
        result.setPixels(dest, 0, width, 0, 0, width, height);
        return result;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v18, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v20, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v29, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v8, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v12, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v44, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v47, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v24, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v49, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v25, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v53, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v27, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v22, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v54, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v28, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v23, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v56, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v30, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v25, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v57, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v26, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v58, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v59, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v32, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v27, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v9, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v60, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v33, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v28, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v10, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v34, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v68, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v9, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v44, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v11, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v48, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v76, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v41, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v79, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v43, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v45, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v16, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v59, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v88, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r25v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v91, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v92, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v98, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v53, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r23v54, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v22, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v70, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v8, resolved type: byte} */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x005d, code lost:
        if (r2.mainPixels.length < r4) goto L_0x005f;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void decodeBitmapData(com.bumptech.glide.gifdecoder.GifFrame r28) {
        /*
            r27 = this;
            r2 = r27
            r3 = r28
            r22 = r3
            if (r22 == 0) goto L_0x001c
            r22 = r2
            r0 = r22
            java.nio.ByteBuffer r0 = r0.rawData
            r22 = r0
            r23 = r3
            r0 = r23
            int r0 = r0.bufferFrameStart
            r23 = r0
            java.nio.Buffer r22 = r22.position(r23)
        L_0x001c:
            r22 = r3
            if (r22 != 0) goto L_0x0127
            r22 = r2
            r0 = r22
            com.bumptech.glide.gifdecoder.GifHeader r0 = r0.header
            r22 = r0
            r0 = r22
            int r0 = r0.width
            r22 = r0
            r23 = r2
            r0 = r23
            com.bumptech.glide.gifdecoder.GifHeader r0 = r0.header
            r23 = r0
            r0 = r23
            int r0 = r0.height
            r23 = r0
            int r22 = r22 * r23
        L_0x003e:
            r4 = r22
            r22 = r2
            r0 = r22
            byte[] r0 = r0.mainPixels
            r22 = r0
            if (r22 == 0) goto L_0x005f
            r22 = r2
            r0 = r22
            byte[] r0 = r0.mainPixels
            r22 = r0
            r0 = r22
            int r0 = r0.length
            r22 = r0
            r23 = r4
            r0 = r22
            r1 = r23
            if (r0 >= r1) goto L_0x006f
        L_0x005f:
            r22 = r2
            r23 = r4
            r0 = r23
            byte[] r0 = new byte[r0]
            r23 = r0
            r0 = r23
            r1 = r22
            r1.mainPixels = r0
        L_0x006f:
            r22 = r2
            r0 = r22
            short[] r0 = r0.prefix
            r22 = r0
            if (r22 != 0) goto L_0x0089
            r22 = r2
            r23 = 4096(0x1000, float:5.74E-42)
            r0 = r23
            short[] r0 = new short[r0]
            r23 = r0
            r0 = r23
            r1 = r22
            r1.prefix = r0
        L_0x0089:
            r22 = r2
            r0 = r22
            byte[] r0 = r0.suffix
            r22 = r0
            if (r22 != 0) goto L_0x00a3
            r22 = r2
            r23 = 4096(0x1000, float:5.74E-42)
            r0 = r23
            byte[] r0 = new byte[r0]
            r23 = r0
            r0 = r23
            r1 = r22
            r1.suffix = r0
        L_0x00a3:
            r22 = r2
            r0 = r22
            byte[] r0 = r0.pixelStack
            r22 = r0
            if (r22 != 0) goto L_0x00bd
            r22 = r2
            r23 = 4097(0x1001, float:5.741E-42)
            r0 = r23
            byte[] r0 = new byte[r0]
            r23 = r0
            r0 = r23
            r1 = r22
            r1.pixelStack = r0
        L_0x00bd:
            r22 = r2
            int r22 = r22.read()
            r17 = r22
            r22 = 1
            r23 = r17
            int r22 = r22 << r23
            r6 = r22
            r22 = r6
            r23 = 1
            int r22 = r22 + 1
            r9 = r22
            r22 = r6
            r23 = 2
            int r22 = r22 + 2
            r5 = r22
            r22 = -1
            r11 = r22
            r22 = r17
            r23 = 1
            int r22 = r22 + 1
            r8 = r22
            r22 = 1
            r23 = r8
            int r22 = r22 << r23
            r23 = 1
            int r22 = r22 + -1
            r7 = r22
            r22 = 0
            r13 = r22
        L_0x00f9:
            r22 = r13
            r23 = r6
            r0 = r22
            r1 = r23
            if (r0 >= r1) goto L_0x013b
            r22 = r2
            r0 = r22
            short[] r0 = r0.prefix
            r22 = r0
            r23 = r13
            r24 = 0
            r22[r23] = r24
            r22 = r2
            r0 = r22
            byte[] r0 = r0.suffix
            r22 = r0
            r23 = r13
            r24 = r13
            r0 = r24
            byte r0 = (byte) r0
            r24 = r0
            r22[r23] = r24
            int r13 = r13 + 1
            goto L_0x00f9
        L_0x0127:
            r22 = r3
            r0 = r22
            int r0 = r0.f303iw
            r22 = r0
            r23 = r3
            r0 = r23
            int r0 = r0.f302ih
            r23 = r0
            int r22 = r22 * r23
            goto L_0x003e
        L_0x013b:
            r22 = 0
            r26 = r22
            r22 = r26
            r23 = r26
            r20 = r23
            r26 = r22
            r22 = r26
            r23 = r26
            r21 = r23
            r26 = r22
            r22 = r26
            r23 = r26
            r19 = r23
            r26 = r22
            r22 = r26
            r23 = r26
            r18 = r23
            r26 = r22
            r22 = r26
            r23 = r26
            r14 = r23
            r26 = r22
            r22 = r26
            r23 = r26
            r12 = r23
            r16 = r22
            r22 = 0
            r15 = r22
        L_0x0173:
            r22 = r15
            r23 = r4
            r0 = r22
            r1 = r23
            if (r0 >= r1) goto L_0x0197
            r22 = r14
            if (r22 != 0) goto L_0x01ba
            r22 = r2
            int r22 = r22.readBlock()
            r14 = r22
            r22 = r14
            if (r22 > 0) goto L_0x01b6
            r22 = r2
            r23 = 3
            r0 = r23
            r1 = r22
            r1.status = r0
        L_0x0197:
            r22 = r21
            r15 = r22
        L_0x019b:
            r22 = r15
            r23 = r4
            r0 = r22
            r1 = r23
            if (r0 >= r1) goto L_0x0375
            r22 = r2
            r0 = r22
            byte[] r0 = r0.mainPixels
            r22 = r0
            r23 = r15
            r24 = 0
            r22[r23] = r24
            int r15 = r15 + 1
            goto L_0x019b
        L_0x01b6:
            r22 = 0
            r20 = r22
        L_0x01ba:
            r22 = r16
            r23 = r2
            r0 = r23
            byte[] r0 = r0.block
            r23 = r0
            r24 = r20
            byte r23 = r23[r24]
            r24 = 255(0xff, float:3.57E-43)
            r0 = r23
            r0 = r0 & 255(0xff, float:3.57E-43)
            r23 = r0
            r24 = r12
            int r23 = r23 << r24
            int r22 = r22 + r23
            r16 = r22
            int r12 = r12 + 8
            int r20 = r20 + 1
            int r14 = r14 + -1
        L_0x01de:
            r22 = r12
            r23 = r8
            r0 = r22
            r1 = r23
            if (r0 < r1) goto L_0x0173
            r22 = r16
            r23 = r7
            r22 = r22 & r23
            r13 = r22
            r22 = r16
            r23 = r8
            int r22 = r22 >> r23
            r16 = r22
            r22 = r12
            r23 = r8
            int r22 = r22 - r23
            r12 = r22
            r22 = r13
            r23 = r6
            r0 = r22
            r1 = r23
            if (r0 != r1) goto L_0x022b
            r22 = r17
            r23 = 1
            int r22 = r22 + 1
            r8 = r22
            r22 = 1
            r23 = r8
            int r22 = r22 << r23
            r23 = 1
            int r22 = r22 + -1
            r7 = r22
            r22 = r6
            r23 = 2
            int r22 = r22 + 2
            r5 = r22
            r22 = -1
            r11 = r22
            goto L_0x01de
        L_0x022b:
            r22 = r13
            r23 = r5
            r0 = r22
            r1 = r23
            if (r0 <= r1) goto L_0x0241
            r22 = r2
            r23 = 3
            r0 = r23
            r1 = r22
            r1.status = r0
            goto L_0x0173
        L_0x0241:
            r22 = r13
            r23 = r9
            r0 = r22
            r1 = r23
            if (r0 != r1) goto L_0x024d
            goto L_0x0173
        L_0x024d:
            r22 = r11
            r23 = -1
            r0 = r22
            r1 = r23
            if (r0 != r1) goto L_0x027b
            r22 = r2
            r0 = r22
            byte[] r0 = r0.pixelStack
            r22 = r0
            r23 = r19
            int r19 = r19 + 1
            r24 = r2
            r0 = r24
            byte[] r0 = r0.suffix
            r24 = r0
            r25 = r13
            byte r24 = r24[r25]
            r22[r23] = r24
            r22 = r13
            r11 = r22
            r22 = r13
            r18 = r22
            goto L_0x01de
        L_0x027b:
            r22 = r13
            r10 = r22
            r22 = r13
            r23 = r5
            r0 = r22
            r1 = r23
            if (r0 < r1) goto L_0x02a2
            r22 = r2
            r0 = r22
            byte[] r0 = r0.pixelStack
            r22 = r0
            r23 = r19
            int r19 = r19 + 1
            r24 = r18
            r0 = r24
            byte r0 = (byte) r0
            r24 = r0
            r22[r23] = r24
            r22 = r11
            r13 = r22
        L_0x02a2:
            r22 = r13
            r23 = r6
            r0 = r22
            r1 = r23
            if (r0 < r1) goto L_0x02d5
            r22 = r2
            r0 = r22
            byte[] r0 = r0.pixelStack
            r22 = r0
            r23 = r19
            int r19 = r19 + 1
            r24 = r2
            r0 = r24
            byte[] r0 = r0.suffix
            r24 = r0
            r25 = r13
            byte r24 = r24[r25]
            r22[r23] = r24
            r22 = r2
            r0 = r22
            short[] r0 = r0.prefix
            r22 = r0
            r23 = r13
            short r22 = r22[r23]
            r13 = r22
            goto L_0x02a2
        L_0x02d5:
            r22 = r2
            r0 = r22
            byte[] r0 = r0.suffix
            r22 = r0
            r23 = r13
            byte r22 = r22[r23]
            r23 = 255(0xff, float:3.57E-43)
            r0 = r22
            r0 = r0 & 255(0xff, float:3.57E-43)
            r22 = r0
            r18 = r22
            r22 = r2
            r0 = r22
            byte[] r0 = r0.pixelStack
            r22 = r0
            r23 = r19
            int r19 = r19 + 1
            r24 = r18
            r0 = r24
            byte r0 = (byte) r0
            r24 = r0
            r22[r23] = r24
            r22 = r5
            r23 = 4096(0x1000, float:5.74E-42)
            r0 = r22
            r1 = r23
            if (r0 >= r1) goto L_0x034e
            r22 = r2
            r0 = r22
            short[] r0 = r0.prefix
            r22 = r0
            r23 = r5
            r24 = r11
            r0 = r24
            short r0 = (short) r0
            r24 = r0
            r22[r23] = r24
            r22 = r2
            r0 = r22
            byte[] r0 = r0.suffix
            r22 = r0
            r23 = r5
            r24 = r18
            r0 = r24
            byte r0 = (byte) r0
            r24 = r0
            r22[r23] = r24
            int r5 = r5 + 1
            r22 = r5
            r23 = r7
            r22 = r22 & r23
            if (r22 != 0) goto L_0x034e
            r22 = r5
            r23 = 4096(0x1000, float:5.74E-42)
            r0 = r22
            r1 = r23
            if (r0 >= r1) goto L_0x034e
            int r8 = r8 + 1
            r22 = r7
            r23 = r5
            int r22 = r22 + r23
            r7 = r22
        L_0x034e:
            r22 = r10
            r11 = r22
        L_0x0352:
            r22 = r19
            if (r22 <= 0) goto L_0x01de
            int r19 = r19 + -1
            r22 = r2
            r0 = r22
            byte[] r0 = r0.mainPixels
            r22 = r0
            r23 = r21
            int r21 = r21 + 1
            r24 = r2
            r0 = r24
            byte[] r0 = r0.pixelStack
            r24 = r0
            r25 = r19
            byte r24 = r24[r25]
            r22[r23] = r24
            int r15 = r15 + 1
            goto L_0x0352
        L_0x0375:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.gifdecoder.GifDecoder.decodeBitmapData(com.bumptech.glide.gifdecoder.GifFrame):void");
    }

    private int read() {
        int curByte = 0;
        try {
            curByte = this.rawData.get() & 255;
        } catch (Exception e) {
            Exception exc = e;
            this.status = 1;
        }
        return curByte;
    }

    private int readBlock() {
        int blockSize = read();
        int n = 0;
        if (blockSize > 0) {
            while (n < blockSize) {
                int count = blockSize - n;
                try {
                    ByteBuffer byteBuffer = this.rawData.get(this.block, n, count);
                    n += count;
                } catch (Exception e) {
                    int w = Log.w(TAG, "Error Reading Block", e);
                    this.status = 1;
                }
            }
        }
        return n;
    }

    private Bitmap getNextBitmap() {
        Bitmap result = this.bitmapProvider.obtain(this.header.width, this.header.height, BITMAP_CONFIG);
        if (result == null) {
            result = Bitmap.createBitmap(this.header.width, this.header.height, BITMAP_CONFIG);
        }
        setAlpha(result);
        return result;
    }

    @TargetApi(12)
    private static void setAlpha(Bitmap bitmap) {
        Bitmap bitmap2 = bitmap;
        if (Build.VERSION.SDK_INT >= 12) {
            bitmap2.setHasAlpha(true);
        }
    }
}

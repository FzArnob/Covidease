package com.shaded.fasterxml.jackson.core.p005io;

import com.shaded.fasterxml.jackson.core.JsonEncoding;
import com.shaded.fasterxml.jackson.core.util.BufferRecycler;
import com.shaded.fasterxml.jackson.core.util.TextBuffer;

/* renamed from: com.shaded.fasterxml.jackson.core.io.IOContext */
public final class IOContext {
    protected byte[] _base64Buffer = null;
    protected final BufferRecycler _bufferRecycler;
    protected char[] _concatCBuffer = null;
    protected JsonEncoding _encoding;
    protected final boolean _managedResource;
    protected char[] _nameCopyBuffer = null;
    protected byte[] _readIOBuffer = null;
    protected final Object _sourceRef;
    protected char[] _tokenCBuffer = null;
    protected byte[] _writeEncodingBuffer = null;

    public IOContext(BufferRecycler bufferRecycler, Object obj, boolean z) {
        this._bufferRecycler = bufferRecycler;
        this._sourceRef = obj;
        this._managedResource = z;
    }

    public void setEncoding(JsonEncoding jsonEncoding) {
        JsonEncoding jsonEncoding2 = jsonEncoding;
        this._encoding = jsonEncoding2;
    }

    public Object getSourceReference() {
        return this._sourceRef;
    }

    public JsonEncoding getEncoding() {
        return this._encoding;
    }

    public boolean isResourceManaged() {
        return this._managedResource;
    }

    public TextBuffer constructTextBuffer() {
        TextBuffer textBuffer;
        new TextBuffer(this._bufferRecycler);
        return textBuffer;
    }

    public byte[] allocReadIOBuffer() {
        _verifyAlloc(this._readIOBuffer);
        byte[] allocByteBuffer = this._bufferRecycler.allocByteBuffer(BufferRecycler.ByteBufferType.READ_IO_BUFFER);
        byte[] bArr = allocByteBuffer;
        this._readIOBuffer = bArr;
        return allocByteBuffer;
    }

    public byte[] allocWriteEncodingBuffer() {
        _verifyAlloc(this._writeEncodingBuffer);
        byte[] allocByteBuffer = this._bufferRecycler.allocByteBuffer(BufferRecycler.ByteBufferType.WRITE_ENCODING_BUFFER);
        byte[] bArr = allocByteBuffer;
        this._writeEncodingBuffer = bArr;
        return allocByteBuffer;
    }

    public byte[] allocBase64Buffer() {
        _verifyAlloc(this._base64Buffer);
        byte[] allocByteBuffer = this._bufferRecycler.allocByteBuffer(BufferRecycler.ByteBufferType.BASE64_CODEC_BUFFER);
        byte[] bArr = allocByteBuffer;
        this._base64Buffer = bArr;
        return allocByteBuffer;
    }

    public char[] allocTokenBuffer() {
        _verifyAlloc(this._tokenCBuffer);
        char[] allocCharBuffer = this._bufferRecycler.allocCharBuffer(BufferRecycler.CharBufferType.TOKEN_BUFFER);
        char[] cArr = allocCharBuffer;
        this._tokenCBuffer = cArr;
        return allocCharBuffer;
    }

    public char[] allocConcatBuffer() {
        _verifyAlloc(this._concatCBuffer);
        char[] allocCharBuffer = this._bufferRecycler.allocCharBuffer(BufferRecycler.CharBufferType.CONCAT_BUFFER);
        char[] cArr = allocCharBuffer;
        this._concatCBuffer = cArr;
        return allocCharBuffer;
    }

    public char[] allocNameCopyBuffer(int i) {
        _verifyAlloc(this._nameCopyBuffer);
        char[] allocCharBuffer = this._bufferRecycler.allocCharBuffer(BufferRecycler.CharBufferType.NAME_COPY_BUFFER, i);
        char[] cArr = allocCharBuffer;
        this._nameCopyBuffer = cArr;
        return allocCharBuffer;
    }

    public void releaseReadIOBuffer(byte[] bArr) {
        byte[] bArr2 = bArr;
        if (bArr2 != null) {
            _verifyRelease(bArr2, this._readIOBuffer);
            this._readIOBuffer = null;
            this._bufferRecycler.releaseByteBuffer(BufferRecycler.ByteBufferType.READ_IO_BUFFER, bArr2);
        }
    }

    public void releaseWriteEncodingBuffer(byte[] bArr) {
        byte[] bArr2 = bArr;
        if (bArr2 != null) {
            _verifyRelease(bArr2, this._writeEncodingBuffer);
            this._writeEncodingBuffer = null;
            this._bufferRecycler.releaseByteBuffer(BufferRecycler.ByteBufferType.WRITE_ENCODING_BUFFER, bArr2);
        }
    }

    public void releaseBase64Buffer(byte[] bArr) {
        byte[] bArr2 = bArr;
        if (bArr2 != null) {
            _verifyRelease(bArr2, this._base64Buffer);
            this._base64Buffer = null;
            this._bufferRecycler.releaseByteBuffer(BufferRecycler.ByteBufferType.BASE64_CODEC_BUFFER, bArr2);
        }
    }

    public void releaseTokenBuffer(char[] cArr) {
        char[] cArr2 = cArr;
        if (cArr2 != null) {
            _verifyRelease(cArr2, this._tokenCBuffer);
            this._tokenCBuffer = null;
            this._bufferRecycler.releaseCharBuffer(BufferRecycler.CharBufferType.TOKEN_BUFFER, cArr2);
        }
    }

    public void releaseConcatBuffer(char[] cArr) {
        char[] cArr2 = cArr;
        if (cArr2 != null) {
            _verifyRelease(cArr2, this._concatCBuffer);
            this._concatCBuffer = null;
            this._bufferRecycler.releaseCharBuffer(BufferRecycler.CharBufferType.CONCAT_BUFFER, cArr2);
        }
    }

    public void releaseNameCopyBuffer(char[] cArr) {
        char[] cArr2 = cArr;
        if (cArr2 != null) {
            _verifyRelease(cArr2, this._nameCopyBuffer);
            this._nameCopyBuffer = null;
            this._bufferRecycler.releaseCharBuffer(BufferRecycler.CharBufferType.NAME_COPY_BUFFER, cArr2);
        }
    }

    private final void _verifyAlloc(Object obj) {
        Throwable th;
        if (obj != null) {
            Throwable th2 = th;
            new IllegalStateException("Trying to call same allocXxx() method second time");
            throw th2;
        }
    }

    private final void _verifyRelease(Object obj, Object obj2) {
        Throwable th;
        if (obj != obj2) {
            Throwable th2 = th;
            new IllegalArgumentException("Trying to release buffer not owned by the context");
            throw th2;
        }
    }
}

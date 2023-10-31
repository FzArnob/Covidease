package com.shaded.fasterxml.jackson.core.p005io;

import com.shaded.fasterxml.jackson.core.SerializableString;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;

/* renamed from: com.shaded.fasterxml.jackson.core.io.SerializedString */
public class SerializedString implements SerializableString, Serializable {
    protected transient String _jdkSerializeValue;
    protected char[] _quotedChars;
    protected byte[] _quotedUTF8Ref;
    protected byte[] _unquotedUTF8Ref;
    protected final String _value;

    public SerializedString(String str) {
        Throwable th;
        String str2 = str;
        if (str2 == null) {
            Throwable th2 = th;
            new IllegalStateException("Null String illegal for SerializedString");
            throw th2;
        }
        this._value = str2;
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException {
        String readUTF = objectInputStream.readUTF();
        this._jdkSerializeValue = readUTF;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeUTF(this._value);
    }

    /* access modifiers changed from: protected */
    public Object readResolve() {
        Object obj;
        new SerializedString(this._jdkSerializeValue);
        return obj;
    }

    public final String getValue() {
        return this._value;
    }

    public final int charLength() {
        return this._value.length();
    }

    public final char[] asQuotedChars() {
        char[] cArr = this._quotedChars;
        if (cArr == null) {
            cArr = JsonStringEncoder.getInstance().quoteAsString(this._value);
            this._quotedChars = cArr;
        }
        return cArr;
    }

    public final byte[] asUnquotedUTF8() {
        byte[] bArr = this._unquotedUTF8Ref;
        if (bArr == null) {
            bArr = JsonStringEncoder.getInstance().encodeAsUTF8(this._value);
            this._unquotedUTF8Ref = bArr;
        }
        return bArr;
    }

    public final byte[] asQuotedUTF8() {
        byte[] bArr = this._quotedUTF8Ref;
        if (bArr == null) {
            bArr = JsonStringEncoder.getInstance().quoteAsUTF8(this._value);
            this._quotedUTF8Ref = bArr;
        }
        return bArr;
    }

    public int appendQuotedUTF8(byte[] bArr, int i) {
        byte[] bArr2 = bArr;
        int i2 = i;
        byte[] bArr3 = this._quotedUTF8Ref;
        if (bArr3 == null) {
            bArr3 = JsonStringEncoder.getInstance().quoteAsUTF8(this._value);
            this._quotedUTF8Ref = bArr3;
        }
        int length = bArr3.length;
        if (i2 + length > bArr2.length) {
            return -1;
        }
        System.arraycopy(bArr3, 0, bArr2, i2, length);
        return length;
    }

    public int appendQuoted(char[] cArr, int i) {
        char[] cArr2 = cArr;
        int i2 = i;
        char[] cArr3 = this._quotedChars;
        if (cArr3 == null) {
            cArr3 = JsonStringEncoder.getInstance().quoteAsString(this._value);
            this._quotedChars = cArr3;
        }
        int length = cArr3.length;
        if (i2 + length > cArr2.length) {
            return -1;
        }
        System.arraycopy(cArr3, 0, cArr2, i2, length);
        return length;
    }

    public int appendUnquotedUTF8(byte[] bArr, int i) {
        byte[] bArr2 = bArr;
        int i2 = i;
        byte[] bArr3 = this._unquotedUTF8Ref;
        if (bArr3 == null) {
            bArr3 = JsonStringEncoder.getInstance().encodeAsUTF8(this._value);
            this._unquotedUTF8Ref = bArr3;
        }
        int length = bArr3.length;
        if (i2 + length > bArr2.length) {
            return -1;
        }
        System.arraycopy(bArr3, 0, bArr2, i2, length);
        return length;
    }

    public int appendUnquoted(char[] cArr, int i) {
        char[] cArr2 = cArr;
        int i2 = i;
        String str = this._value;
        int length = str.length();
        if (i2 + length > cArr2.length) {
            return -1;
        }
        str.getChars(0, length, cArr2, i2);
        return length;
    }

    public int writeQuotedUTF8(OutputStream outputStream) throws IOException {
        OutputStream outputStream2 = outputStream;
        byte[] bArr = this._quotedUTF8Ref;
        if (bArr == null) {
            bArr = JsonStringEncoder.getInstance().quoteAsUTF8(this._value);
            this._quotedUTF8Ref = bArr;
        }
        int length = bArr.length;
        outputStream2.write(bArr, 0, length);
        return length;
    }

    public int writeUnquotedUTF8(OutputStream outputStream) throws IOException {
        OutputStream outputStream2 = outputStream;
        byte[] bArr = this._unquotedUTF8Ref;
        if (bArr == null) {
            bArr = JsonStringEncoder.getInstance().encodeAsUTF8(this._value);
            this._unquotedUTF8Ref = bArr;
        }
        int length = bArr.length;
        outputStream2.write(bArr, 0, length);
        return length;
    }

    public int putQuotedUTF8(ByteBuffer byteBuffer) {
        ByteBuffer byteBuffer2 = byteBuffer;
        byte[] bArr = this._quotedUTF8Ref;
        if (bArr == null) {
            bArr = JsonStringEncoder.getInstance().quoteAsUTF8(this._value);
            this._quotedUTF8Ref = bArr;
        }
        int length = bArr.length;
        if (length > byteBuffer2.remaining()) {
            return -1;
        }
        ByteBuffer put = byteBuffer2.put(bArr, 0, length);
        return length;
    }

    public int putUnquotedUTF8(ByteBuffer byteBuffer) {
        ByteBuffer byteBuffer2 = byteBuffer;
        byte[] bArr = this._unquotedUTF8Ref;
        if (bArr == null) {
            bArr = JsonStringEncoder.getInstance().encodeAsUTF8(this._value);
            this._unquotedUTF8Ref = bArr;
        }
        int length = bArr.length;
        if (length > byteBuffer2.remaining()) {
            return -1;
        }
        ByteBuffer put = byteBuffer2.put(bArr, 0, length);
        return length;
    }

    public final String toString() {
        return this._value;
    }

    public final int hashCode() {
        return this._value.hashCode();
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r6) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r3 = r1
            r4 = r0
            if (r3 != r4) goto L_0x0009
            r3 = 1
            r0 = r3
        L_0x0008:
            return r0
        L_0x0009:
            r3 = r1
            if (r3 == 0) goto L_0x0018
            r3 = r1
            java.lang.Class r3 = r3.getClass()
            r4 = r0
            java.lang.Class r4 = r4.getClass()
            if (r3 == r4) goto L_0x001b
        L_0x0018:
            r3 = 0
            r0 = r3
            goto L_0x0008
        L_0x001b:
            r3 = r1
            com.shaded.fasterxml.jackson.core.io.SerializedString r3 = (com.shaded.fasterxml.jackson.core.p005io.SerializedString) r3
            r2 = r3
            r3 = r0
            java.lang.String r3 = r3._value
            r4 = r2
            java.lang.String r4 = r4._value
            boolean r3 = r3.equals(r4)
            r0 = r3
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.core.p005io.SerializedString.equals(java.lang.Object):boolean");
    }
}

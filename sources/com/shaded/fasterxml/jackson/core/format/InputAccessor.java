package com.shaded.fasterxml.jackson.core.format;

import com.shaded.fasterxml.jackson.core.JsonFactory;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public interface InputAccessor {
    boolean hasMoreBytes() throws IOException;

    byte nextByte() throws IOException;

    void reset();

    public static class Std implements InputAccessor {
        protected final byte[] _buffer;
        protected int _bufferedEnd;
        protected final int _bufferedStart;
        protected final InputStream _in;
        protected int _ptr;

        public Std(InputStream inputStream, byte[] bArr) {
            this._in = inputStream;
            this._buffer = bArr;
            this._bufferedStart = 0;
            this._ptr = 0;
            this._bufferedEnd = 0;
        }

        public Std(byte[] bArr) {
            byte[] bArr2 = bArr;
            this._in = null;
            this._buffer = bArr2;
            this._bufferedStart = 0;
            this._bufferedEnd = bArr2.length;
        }

        public Std(byte[] bArr, int i, int i2) {
            int i3 = i;
            this._in = null;
            this._buffer = bArr;
            this._ptr = i3;
            this._bufferedStart = i3;
            this._bufferedEnd = i3 + i2;
        }

        public boolean hasMoreBytes() throws IOException {
            if (this._ptr < this._bufferedEnd) {
                return true;
            }
            if (this._in == null) {
                return false;
            }
            int length = this._buffer.length - this._ptr;
            if (length < 1) {
                return false;
            }
            int read = this._in.read(this._buffer, this._ptr, length);
            if (read <= 0) {
                return false;
            }
            this._bufferedEnd += read;
            return true;
        }

        public byte nextByte() throws IOException {
            Throwable th;
            StringBuilder sb;
            if (this._ptr < this._bufferedEnd || hasMoreBytes()) {
                byte[] bArr = this._buffer;
                int i = this._ptr;
                int i2 = i + 1;
                this._ptr = i2;
                return bArr[i];
            }
            Throwable th2 = th;
            new StringBuilder();
            new EOFException(sb.append("Failed auto-detect: could not read more than ").append(this._ptr).append(" bytes (max buffer size: ").append(this._buffer.length).append(")").toString());
            throw th2;
        }

        public void reset() {
            this._ptr = this._bufferedStart;
        }

        public DataFormatMatcher createMatcher(JsonFactory jsonFactory, MatchStrength matchStrength) {
            DataFormatMatcher dataFormatMatcher;
            new DataFormatMatcher(this._in, this._buffer, this._bufferedStart, this._bufferedEnd - this._bufferedStart, jsonFactory, matchStrength);
            return dataFormatMatcher;
        }
    }
}

package com.shaded.fasterxml.jackson.databind.deser;

import com.shaded.fasterxml.jackson.core.JsonFactory;
import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.format.InputAccessor;
import com.shaded.fasterxml.jackson.core.format.MatchStrength;
import com.shaded.fasterxml.jackson.core.p005io.IOContext;
import com.shaded.fasterxml.jackson.core.p005io.MergedStream;
import com.shaded.fasterxml.jackson.databind.DeserializationConfig;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.ObjectReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DataFormatReaders {
    public static final int DEFAULT_MAX_INPUT_LOOKAHEAD = 64;
    protected final int _maxInputLookahead;
    protected final MatchStrength _minimalMatch;
    protected final MatchStrength _optimalMatch;
    protected final ObjectReader[] _readers;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public DataFormatReaders(ObjectReader... objectReaderArr) {
        this(objectReaderArr, MatchStrength.SOLID_MATCH, MatchStrength.WEAK_MATCH, 64);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DataFormatReaders(java.util.Collection<com.shaded.fasterxml.jackson.databind.ObjectReader> r6) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r2 = r0
            r3 = r1
            r4 = r1
            int r4 = r4.size()
            com.shaded.fasterxml.jackson.databind.ObjectReader[] r4 = new com.shaded.fasterxml.jackson.databind.ObjectReader[r4]
            java.lang.Object[] r3 = r3.toArray(r4)
            com.shaded.fasterxml.jackson.databind.ObjectReader[] r3 = (com.shaded.fasterxml.jackson.databind.ObjectReader[]) r3
            r2.<init>((com.shaded.fasterxml.jackson.databind.ObjectReader[]) r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.DataFormatReaders.<init>(java.util.Collection):void");
    }

    private DataFormatReaders(ObjectReader[] objectReaderArr, MatchStrength matchStrength, MatchStrength matchStrength2, int i) {
        this._readers = objectReaderArr;
        this._optimalMatch = matchStrength;
        this._minimalMatch = matchStrength2;
        this._maxInputLookahead = i;
    }

    public DataFormatReaders withOptimalMatch(MatchStrength matchStrength) {
        DataFormatReaders dataFormatReaders;
        MatchStrength matchStrength2 = matchStrength;
        if (matchStrength2 == this._optimalMatch) {
            return this;
        }
        new DataFormatReaders(this._readers, matchStrength2, this._minimalMatch, this._maxInputLookahead);
        return dataFormatReaders;
    }

    public DataFormatReaders withMinimalMatch(MatchStrength matchStrength) {
        DataFormatReaders dataFormatReaders;
        MatchStrength matchStrength2 = matchStrength;
        if (matchStrength2 == this._minimalMatch) {
            return this;
        }
        new DataFormatReaders(this._readers, this._optimalMatch, matchStrength2, this._maxInputLookahead);
        return dataFormatReaders;
    }

    public DataFormatReaders with(ObjectReader[] objectReaderArr) {
        DataFormatReaders dataFormatReaders;
        new DataFormatReaders(objectReaderArr, this._optimalMatch, this._minimalMatch, this._maxInputLookahead);
        return dataFormatReaders;
    }

    public DataFormatReaders withMaxInputLookahead(int i) {
        DataFormatReaders dataFormatReaders;
        int i2 = i;
        if (i2 == this._maxInputLookahead) {
            return this;
        }
        new DataFormatReaders(this._readers, this._optimalMatch, this._minimalMatch, i2);
        return dataFormatReaders;
    }

    public DataFormatReaders with(DeserializationConfig deserializationConfig) {
        DataFormatReaders dataFormatReaders;
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        int length = this._readers.length;
        ObjectReader[] objectReaderArr = new ObjectReader[length];
        for (int i = 0; i < length; i++) {
            objectReaderArr[i] = this._readers[i].with(deserializationConfig2);
        }
        new DataFormatReaders(objectReaderArr, this._optimalMatch, this._minimalMatch, this._maxInputLookahead);
        return dataFormatReaders;
    }

    public DataFormatReaders withType(JavaType javaType) {
        DataFormatReaders dataFormatReaders;
        JavaType javaType2 = javaType;
        int length = this._readers.length;
        ObjectReader[] objectReaderArr = new ObjectReader[length];
        for (int i = 0; i < length; i++) {
            objectReaderArr[i] = this._readers[i].withType(javaType2);
        }
        new DataFormatReaders(objectReaderArr, this._optimalMatch, this._minimalMatch, this._maxInputLookahead);
        return dataFormatReaders;
    }

    public Match findFormat(InputStream inputStream) throws IOException {
        AccessorForReader accessorForReader;
        new AccessorForReader(this, inputStream, new byte[this._maxInputLookahead]);
        return _findFormat(accessorForReader);
    }

    public Match findFormat(byte[] bArr) throws IOException {
        AccessorForReader accessorForReader;
        new AccessorForReader(this, bArr);
        return _findFormat(accessorForReader);
    }

    public Match findFormat(byte[] bArr, int i, int i2) throws IOException {
        AccessorForReader accessorForReader;
        new AccessorForReader(this, bArr, i, i2);
        return _findFormat(accessorForReader);
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        StringBuilder sb2 = sb;
        StringBuilder append = sb2.append('[');
        int length = this._readers.length;
        if (length > 0) {
            StringBuilder append2 = sb2.append(this._readers[0].getFactory().getFormatName());
            for (int i = 1; i < length; i++) {
                StringBuilder append3 = sb2.append(", ");
                StringBuilder append4 = sb2.append(this._readers[i].getFactory().getFormatName());
            }
        }
        StringBuilder append5 = sb2.append(']');
        return sb2.toString();
    }

    private Match _findFormat(AccessorForReader accessorForReader) throws IOException {
        AccessorForReader accessorForReader2 = accessorForReader;
        ObjectReader objectReader = null;
        MatchStrength matchStrength = null;
        ObjectReader[] objectReaderArr = this._readers;
        int length = objectReaderArr.length;
        for (int i = 0; i < length; i++) {
            ObjectReader objectReader2 = objectReaderArr[i];
            accessorForReader2.reset();
            MatchStrength hasFormat = objectReader2.getFactory().hasFormat(accessorForReader2);
            if (hasFormat != null && hasFormat.ordinal() >= this._minimalMatch.ordinal() && (objectReader == null || matchStrength.ordinal() < hasFormat.ordinal())) {
                objectReader = objectReader2;
                matchStrength = hasFormat;
                if (hasFormat.ordinal() >= this._optimalMatch.ordinal()) {
                    break;
                }
            }
        }
        return accessorForReader2.createMatcher(objectReader, matchStrength);
    }

    protected class AccessorForReader extends InputAccessor.Std {
        final /* synthetic */ DataFormatReaders this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public AccessorForReader(DataFormatReaders dataFormatReaders, InputStream inputStream, byte[] bArr) {
            super(inputStream, bArr);
            this.this$0 = dataFormatReaders;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public AccessorForReader(DataFormatReaders dataFormatReaders, byte[] bArr) {
            super(bArr);
            this.this$0 = dataFormatReaders;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public AccessorForReader(DataFormatReaders dataFormatReaders, byte[] bArr, int i, int i2) {
            super(bArr, i, i2);
            this.this$0 = dataFormatReaders;
        }

        public Match createMatcher(ObjectReader objectReader, MatchStrength matchStrength) {
            Match match;
            new Match(this._in, this._buffer, this._bufferedStart, this._bufferedEnd - this._bufferedStart, objectReader, matchStrength);
            return match;
        }
    }

    public static class Match {
        protected final byte[] _bufferedData;
        protected final int _bufferedLength;
        protected final int _bufferedStart;
        protected final ObjectReader _match;
        protected final MatchStrength _matchStrength;
        protected final InputStream _originalStream;

        protected Match(InputStream inputStream, byte[] bArr, int i, int i2, ObjectReader objectReader, MatchStrength matchStrength) {
            this._originalStream = inputStream;
            this._bufferedData = bArr;
            this._bufferedStart = i;
            this._bufferedLength = i2;
            this._match = objectReader;
            this._matchStrength = matchStrength;
        }

        public boolean hasMatch() {
            return this._match != null;
        }

        public MatchStrength getMatchStrength() {
            return this._matchStrength == null ? MatchStrength.INCONCLUSIVE : this._matchStrength;
        }

        public ObjectReader getReader() {
            return this._match;
        }

        public String getMatchedFormatName() {
            return this._match.getFactory().getFormatName();
        }

        public JsonParser createParserWithMatch() throws IOException {
            if (this._match == null) {
                return null;
            }
            JsonFactory factory = this._match.getFactory();
            if (this._originalStream == null) {
                return factory.createParser(this._bufferedData, this._bufferedStart, this._bufferedLength);
            }
            return factory.createParser(getDataStream());
        }

        public InputStream getDataStream() {
            InputStream inputStream;
            InputStream inputStream2;
            if (this._originalStream == null) {
                new ByteArrayInputStream(this._bufferedData, this._bufferedStart, this._bufferedLength);
                return inputStream2;
            }
            new MergedStream((IOContext) null, this._originalStream, this._bufferedData, this._bufferedStart, this._bufferedLength);
            return inputStream;
        }
    }
}

package com.shaded.fasterxml.jackson.core.format;

import com.shaded.fasterxml.jackson.core.JsonFactory;
import com.shaded.fasterxml.jackson.core.format.InputAccessor;
import java.io.IOException;
import java.io.InputStream;

public class DataFormatDetector {
    public static final int DEFAULT_MAX_INPUT_LOOKAHEAD = 64;
    protected final JsonFactory[] _detectors;
    protected final int _maxInputLookahead;
    protected final MatchStrength _minimalMatch;
    protected final MatchStrength _optimalMatch;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public DataFormatDetector(JsonFactory... jsonFactoryArr) {
        this(jsonFactoryArr, MatchStrength.SOLID_MATCH, MatchStrength.WEAK_MATCH, 64);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DataFormatDetector(java.util.Collection<com.shaded.fasterxml.jackson.core.JsonFactory> r6) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r2 = r0
            r3 = r1
            r4 = r1
            int r4 = r4.size()
            com.shaded.fasterxml.jackson.core.JsonFactory[] r4 = new com.shaded.fasterxml.jackson.core.JsonFactory[r4]
            java.lang.Object[] r3 = r3.toArray(r4)
            com.shaded.fasterxml.jackson.core.JsonFactory[] r3 = (com.shaded.fasterxml.jackson.core.JsonFactory[]) r3
            r2.<init>((com.shaded.fasterxml.jackson.core.JsonFactory[]) r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.core.format.DataFormatDetector.<init>(java.util.Collection):void");
    }

    public DataFormatDetector withOptimalMatch(MatchStrength matchStrength) {
        DataFormatDetector dataFormatDetector;
        MatchStrength matchStrength2 = matchStrength;
        if (matchStrength2 == this._optimalMatch) {
            return this;
        }
        new DataFormatDetector(this._detectors, matchStrength2, this._minimalMatch, this._maxInputLookahead);
        return dataFormatDetector;
    }

    public DataFormatDetector withMinimalMatch(MatchStrength matchStrength) {
        DataFormatDetector dataFormatDetector;
        MatchStrength matchStrength2 = matchStrength;
        if (matchStrength2 == this._minimalMatch) {
            return this;
        }
        new DataFormatDetector(this._detectors, this._optimalMatch, matchStrength2, this._maxInputLookahead);
        return dataFormatDetector;
    }

    public DataFormatDetector withMaxInputLookahead(int i) {
        DataFormatDetector dataFormatDetector;
        int i2 = i;
        if (i2 == this._maxInputLookahead) {
            return this;
        }
        new DataFormatDetector(this._detectors, this._optimalMatch, this._minimalMatch, i2);
        return dataFormatDetector;
    }

    private DataFormatDetector(JsonFactory[] jsonFactoryArr, MatchStrength matchStrength, MatchStrength matchStrength2, int i) {
        this._detectors = jsonFactoryArr;
        this._optimalMatch = matchStrength;
        this._minimalMatch = matchStrength2;
        this._maxInputLookahead = i;
    }

    public DataFormatMatcher findFormat(InputStream inputStream) throws IOException {
        InputAccessor.Std std;
        new InputAccessor.Std(inputStream, new byte[this._maxInputLookahead]);
        return _findFormat(std);
    }

    public DataFormatMatcher findFormat(byte[] bArr) throws IOException {
        InputAccessor.Std std;
        new InputAccessor.Std(bArr);
        return _findFormat(std);
    }

    public DataFormatMatcher findFormat(byte[] bArr, int i, int i2) throws IOException {
        InputAccessor.Std std;
        new InputAccessor.Std(bArr, i, i2);
        return _findFormat(std);
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        StringBuilder sb2 = sb;
        StringBuilder append = sb2.append('[');
        int length = this._detectors.length;
        if (length > 0) {
            StringBuilder append2 = sb2.append(this._detectors[0].getFormatName());
            for (int i = 1; i < length; i++) {
                StringBuilder append3 = sb2.append(", ");
                StringBuilder append4 = sb2.append(this._detectors[i].getFormatName());
            }
        }
        StringBuilder append5 = sb2.append(']');
        return sb2.toString();
    }

    private DataFormatMatcher _findFormat(InputAccessor.Std std) throws IOException {
        InputAccessor.Std std2 = std;
        JsonFactory jsonFactory = null;
        MatchStrength matchStrength = null;
        JsonFactory[] jsonFactoryArr = this._detectors;
        int length = jsonFactoryArr.length;
        for (int i = 0; i < length; i++) {
            JsonFactory jsonFactory2 = jsonFactoryArr[i];
            std2.reset();
            MatchStrength hasFormat = jsonFactory2.hasFormat(std2);
            if (hasFormat != null && hasFormat.ordinal() >= this._minimalMatch.ordinal() && (jsonFactory == null || matchStrength.ordinal() < hasFormat.ordinal())) {
                jsonFactory = jsonFactory2;
                matchStrength = hasFormat;
                if (hasFormat.ordinal() >= this._optimalMatch.ordinal()) {
                    break;
                }
            }
        }
        return std2.createMatcher(jsonFactory, matchStrength);
    }
}

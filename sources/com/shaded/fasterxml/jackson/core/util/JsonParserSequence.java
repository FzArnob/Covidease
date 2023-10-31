package com.shaded.fasterxml.jackson.core.util;

import com.shaded.fasterxml.jackson.core.JsonParseException;
import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonParserSequence extends JsonParserDelegate {
    protected int _nextParser = 1;
    protected final JsonParser[] _parsers;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected JsonParserSequence(com.shaded.fasterxml.jackson.core.JsonParser[] r6) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r2 = r0
            r3 = r1
            r4 = 0
            r3 = r3[r4]
            r2.<init>(r3)
            r2 = r0
            r3 = r1
            r2._parsers = r3
            r2 = r0
            r3 = 1
            r2._nextParser = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.core.util.JsonParserSequence.<init>(com.shaded.fasterxml.jackson.core.JsonParser[]):void");
    }

    public static JsonParserSequence createFlattened(JsonParser jsonParser, JsonParser jsonParser2) {
        ArrayList arrayList;
        JsonParserSequence jsonParserSequence;
        JsonParserSequence jsonParserSequence2;
        JsonParser jsonParser3 = jsonParser;
        JsonParser jsonParser4 = jsonParser2;
        if ((jsonParser3 instanceof JsonParserSequence) || (jsonParser4 instanceof JsonParserSequence)) {
            new ArrayList();
            ArrayList arrayList2 = arrayList;
            if (jsonParser3 instanceof JsonParserSequence) {
                ((JsonParserSequence) jsonParser3).addFlattenedActiveParsers(arrayList2);
            } else {
                boolean add = arrayList2.add(jsonParser3);
            }
            if (jsonParser4 instanceof JsonParserSequence) {
                ((JsonParserSequence) jsonParser4).addFlattenedActiveParsers(arrayList2);
            } else {
                boolean add2 = arrayList2.add(jsonParser4);
            }
            new JsonParserSequence((JsonParser[]) arrayList2.toArray(new JsonParser[arrayList2.size()]));
            return jsonParserSequence;
        }
        JsonParserSequence jsonParserSequence3 = jsonParserSequence2;
        JsonParser[] jsonParserArr = new JsonParser[2];
        jsonParserArr[0] = jsonParser3;
        JsonParser[] jsonParserArr2 = jsonParserArr;
        jsonParserArr2[1] = jsonParser4;
        new JsonParserSequence(jsonParserArr2);
        return jsonParserSequence3;
    }

    /* access modifiers changed from: protected */
    public void addFlattenedActiveParsers(List<JsonParser> list) {
        List<JsonParser> list2 = list;
        int length = this._parsers.length;
        for (int i = this._nextParser - 1; i < length; i++) {
            JsonParser jsonParser = this._parsers[i];
            if (jsonParser instanceof JsonParserSequence) {
                ((JsonParserSequence) jsonParser).addFlattenedActiveParsers(list2);
            } else {
                boolean add = list2.add(jsonParser);
            }
        }
    }

    public void close() throws IOException {
        do {
            this.delegate.close();
        } while (switchToNext());
    }

    public JsonToken nextToken() throws IOException, JsonParseException {
        JsonToken nextToken = this.delegate.nextToken();
        if (nextToken != null) {
            return nextToken;
        }
        while (switchToNext()) {
            JsonToken nextToken2 = this.delegate.nextToken();
            if (nextToken2 != null) {
                return nextToken2;
            }
        }
        return null;
    }

    public int containedParsersCount() {
        return this._parsers.length;
    }

    /* access modifiers changed from: protected */
    public boolean switchToNext() {
        if (this._nextParser >= this._parsers.length) {
            return false;
        }
        JsonParser[] jsonParserArr = this._parsers;
        int i = this._nextParser;
        int i2 = i + 1;
        this._nextParser = i2;
        this.delegate = jsonParserArr[i];
        return true;
    }
}

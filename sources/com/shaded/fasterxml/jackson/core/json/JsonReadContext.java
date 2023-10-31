package com.shaded.fasterxml.jackson.core.json;

import com.shaded.fasterxml.jackson.core.JsonLocation;
import com.shaded.fasterxml.jackson.core.JsonStreamContext;
import com.shaded.fasterxml.jackson.core.p005io.CharTypes;

public final class JsonReadContext extends JsonStreamContext {
    protected JsonReadContext _child = null;
    protected int _columnNr;
    protected String _currentName;
    protected int _lineNr;
    protected final JsonReadContext _parent;

    public JsonReadContext(JsonReadContext jsonReadContext, int i, int i2, int i3) {
        this._type = i;
        this._parent = jsonReadContext;
        this._lineNr = i2;
        this._columnNr = i3;
        this._index = -1;
    }

    /* access modifiers changed from: protected */
    public void reset(int i, int i2, int i3) {
        this._type = i;
        this._index = -1;
        this._lineNr = i2;
        this._columnNr = i3;
        this._currentName = null;
    }

    public static JsonReadContext createRootContext(int i, int i2) {
        JsonReadContext jsonReadContext;
        new JsonReadContext((JsonReadContext) null, 0, i, i2);
        return jsonReadContext;
    }

    public static JsonReadContext createRootContext() {
        JsonReadContext jsonReadContext;
        JsonReadContext jsonReadContext2 = jsonReadContext;
        new JsonReadContext((JsonReadContext) null, 0, 1, 0);
        return jsonReadContext2;
    }

    public JsonReadContext createChildArrayContext(int i, int i2) {
        JsonReadContext jsonReadContext;
        int i3 = i;
        int i4 = i2;
        JsonReadContext jsonReadContext2 = this._child;
        if (jsonReadContext2 == null) {
            new JsonReadContext(this, 1, i3, i4);
            JsonReadContext jsonReadContext3 = jsonReadContext;
            this._child = jsonReadContext3;
            return jsonReadContext3;
        }
        jsonReadContext2.reset(1, i3, i4);
        return jsonReadContext2;
    }

    public JsonReadContext createChildObjectContext(int i, int i2) {
        JsonReadContext jsonReadContext;
        int i3 = i;
        int i4 = i2;
        JsonReadContext jsonReadContext2 = this._child;
        if (jsonReadContext2 == null) {
            new JsonReadContext(this, 2, i3, i4);
            JsonReadContext jsonReadContext3 = jsonReadContext;
            this._child = jsonReadContext3;
            return jsonReadContext3;
        }
        jsonReadContext2.reset(2, i3, i4);
        return jsonReadContext2;
    }

    public String getCurrentName() {
        return this._currentName;
    }

    public JsonReadContext getParent() {
        return this._parent;
    }

    public JsonLocation getStartLocation(Object obj) {
        JsonLocation jsonLocation;
        new JsonLocation(obj, -1, this._lineNr, this._columnNr);
        return jsonLocation;
    }

    public boolean expectComma() {
        int i = this._index + 1;
        int i2 = i;
        this._index = i2;
        return this._type != 0 && i > 0;
    }

    public void setCurrentName(String str) {
        String str2 = str;
        this._currentName = str2;
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder(64);
        StringBuilder sb2 = sb;
        switch (this._type) {
            case 0:
                StringBuilder append = sb2.append("/");
                break;
            case 1:
                StringBuilder append2 = sb2.append('[');
                StringBuilder append3 = sb2.append(getCurrentIndex());
                StringBuilder append4 = sb2.append(']');
                break;
            case 2:
                StringBuilder append5 = sb2.append('{');
                if (this._currentName != null) {
                    StringBuilder append6 = sb2.append('\"');
                    CharTypes.appendQuoted(sb2, this._currentName);
                    StringBuilder append7 = sb2.append('\"');
                } else {
                    StringBuilder append8 = sb2.append('?');
                }
                StringBuilder append9 = sb2.append('}');
                break;
        }
        return sb2.toString();
    }
}

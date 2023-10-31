package com.shaded.fasterxml.jackson.core.json;

import com.shaded.fasterxml.jackson.core.JsonStreamContext;

public class JsonWriteContext extends JsonStreamContext {
    public static final int STATUS_EXPECT_NAME = 5;
    public static final int STATUS_EXPECT_VALUE = 4;
    public static final int STATUS_OK_AFTER_COLON = 2;
    public static final int STATUS_OK_AFTER_COMMA = 1;
    public static final int STATUS_OK_AFTER_SPACE = 3;
    public static final int STATUS_OK_AS_IS = 0;
    protected JsonWriteContext _child = null;
    protected String _currentName;
    protected final JsonWriteContext _parent;

    protected JsonWriteContext(int i, JsonWriteContext jsonWriteContext) {
        this._type = i;
        this._parent = jsonWriteContext;
        this._index = -1;
    }

    public static JsonWriteContext createRootContext() {
        JsonWriteContext jsonWriteContext;
        JsonWriteContext jsonWriteContext2 = jsonWriteContext;
        new JsonWriteContext(0, (JsonWriteContext) null);
        return jsonWriteContext2;
    }

    private JsonWriteContext reset(int i) {
        this._type = i;
        this._index = -1;
        this._currentName = null;
        return this;
    }

    public final JsonWriteContext createChildArrayContext() {
        JsonWriteContext jsonWriteContext;
        JsonWriteContext jsonWriteContext2 = this._child;
        if (jsonWriteContext2 != null) {
            return jsonWriteContext2.reset(1);
        }
        new JsonWriteContext(1, this);
        JsonWriteContext jsonWriteContext3 = jsonWriteContext;
        this._child = jsonWriteContext3;
        return jsonWriteContext3;
    }

    public final JsonWriteContext createChildObjectContext() {
        JsonWriteContext jsonWriteContext;
        JsonWriteContext jsonWriteContext2 = this._child;
        if (jsonWriteContext2 != null) {
            return jsonWriteContext2.reset(2);
        }
        new JsonWriteContext(2, this);
        JsonWriteContext jsonWriteContext3 = jsonWriteContext;
        this._child = jsonWriteContext3;
        return jsonWriteContext3;
    }

    public final JsonWriteContext getParent() {
        return this._parent;
    }

    public final String getCurrentName() {
        return this._currentName;
    }

    public final int writeFieldName(String str) {
        String str2 = str;
        if (this._type != 2) {
            return 4;
        }
        if (this._currentName != null) {
            return 4;
        }
        this._currentName = str2;
        return this._index < 0 ? 0 : 1;
    }

    public final int writeValue() {
        if (this._type == 2) {
            if (this._currentName == null) {
                return 5;
            }
            this._currentName = null;
            this._index++;
            return 2;
        } else if (this._type == 1) {
            int i = this._index;
            this._index++;
            return i < 0 ? 0 : 1;
        } else {
            this._index++;
            return this._index == 0 ? 0 : 3;
        }
    }

    /* access modifiers changed from: protected */
    public final void appendDesc(StringBuilder sb) {
        StringBuilder sb2 = sb;
        if (this._type == 2) {
            StringBuilder append = sb2.append('{');
            if (this._currentName != null) {
                StringBuilder append2 = sb2.append('\"');
                StringBuilder append3 = sb2.append(this._currentName);
                StringBuilder append4 = sb2.append('\"');
            } else {
                StringBuilder append5 = sb2.append('?');
            }
            StringBuilder append6 = sb2.append('}');
        } else if (this._type == 1) {
            StringBuilder append7 = sb2.append('[');
            StringBuilder append8 = sb2.append(getCurrentIndex());
            StringBuilder append9 = sb2.append(']');
        } else {
            StringBuilder append10 = sb2.append("/");
        }
    }

    public final String toString() {
        StringBuilder sb;
        new StringBuilder(64);
        StringBuilder sb2 = sb;
        appendDesc(sb2);
        return sb2.toString();
    }
}

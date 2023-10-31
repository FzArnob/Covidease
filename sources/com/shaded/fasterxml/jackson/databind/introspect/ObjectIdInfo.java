package com.shaded.fasterxml.jackson.databind.introspect;

import com.shaded.fasterxml.jackson.annotation.ObjectIdGenerator;

public class ObjectIdInfo {
    protected final boolean _alwaysAsId;
    protected final Class<? extends ObjectIdGenerator<?>> _generator;
    protected final String _propertyName;
    protected final Class<?> _scope;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ObjectIdInfo(String str, Class<?> cls, Class<? extends ObjectIdGenerator<?>> cls2) {
        this(str, cls, cls2, false);
    }

    protected ObjectIdInfo(String str, Class<?> cls, Class<? extends ObjectIdGenerator<?>> cls2, boolean z) {
        this._propertyName = str;
        this._scope = cls;
        this._generator = cls2;
        this._alwaysAsId = z;
    }

    public ObjectIdInfo withAlwaysAsId(boolean z) {
        ObjectIdInfo objectIdInfo;
        boolean z2 = z;
        if (this._alwaysAsId == z2) {
            return this;
        }
        new ObjectIdInfo(this._propertyName, this._scope, this._generator, z2);
        return objectIdInfo;
    }

    public String getPropertyName() {
        return this._propertyName;
    }

    public Class<?> getScope() {
        return this._scope;
    }

    public Class<? extends ObjectIdGenerator<?>> getGeneratorType() {
        return this._generator;
    }

    public boolean getAlwaysAsId() {
        return this._alwaysAsId;
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("ObjectIdInfo: propName=").append(this._propertyName).append(", scope=").append(this._scope == null ? "null" : this._scope.getName()).append(", generatorType=").append(this._generator == null ? "null" : this._generator.getName()).append(", alwaysAsId=").append(this._alwaysAsId).toString();
    }
}

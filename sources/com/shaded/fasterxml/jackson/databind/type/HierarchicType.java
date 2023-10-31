package com.shaded.fasterxml.jackson.databind.type;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class HierarchicType {
    protected final Type _actualType;
    protected final ParameterizedType _genericType;
    protected final Class<?> _rawClass;
    protected HierarchicType _subType;
    protected HierarchicType _superType;

    public HierarchicType(Type type) {
        Throwable th;
        StringBuilder sb;
        Type type2 = type;
        this._actualType = type2;
        if (type2 instanceof Class) {
            this._rawClass = (Class) type2;
            this._genericType = null;
        } else if (type2 instanceof ParameterizedType) {
            this._genericType = (ParameterizedType) type2;
            this._rawClass = (Class) this._genericType.getRawType();
        } else {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Type ").append(type2.getClass().getName()).append(" can not be used to construct HierarchicType").toString());
            throw th2;
        }
    }

    private HierarchicType(Type type, Class<?> cls, ParameterizedType parameterizedType, HierarchicType hierarchicType, HierarchicType hierarchicType2) {
        this._actualType = type;
        this._rawClass = cls;
        this._genericType = parameterizedType;
        this._superType = hierarchicType;
        this._subType = hierarchicType2;
    }

    public HierarchicType deepCloneWithoutSubtype() {
        HierarchicType hierarchicType;
        HierarchicType deepCloneWithoutSubtype = this._superType == null ? null : this._superType.deepCloneWithoutSubtype();
        new HierarchicType(this._actualType, this._rawClass, this._genericType, deepCloneWithoutSubtype, (HierarchicType) null);
        HierarchicType hierarchicType2 = hierarchicType;
        if (deepCloneWithoutSubtype != null) {
            deepCloneWithoutSubtype.setSubType(hierarchicType2);
        }
        return hierarchicType2;
    }

    public void setSuperType(HierarchicType hierarchicType) {
        HierarchicType hierarchicType2 = hierarchicType;
        this._superType = hierarchicType2;
    }

    public final HierarchicType getSuperType() {
        return this._superType;
    }

    public void setSubType(HierarchicType hierarchicType) {
        HierarchicType hierarchicType2 = hierarchicType;
        this._subType = hierarchicType2;
    }

    public final HierarchicType getSubType() {
        return this._subType;
    }

    public final boolean isGeneric() {
        return this._genericType != null;
    }

    public final ParameterizedType asGeneric() {
        return this._genericType;
    }

    public final Class<?> getRawClass() {
        return this._rawClass;
    }

    public String toString() {
        if (this._genericType != null) {
            return this._genericType.toString();
        }
        return this._rawClass.getName();
    }
}

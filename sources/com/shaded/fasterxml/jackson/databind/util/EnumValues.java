package com.shaded.fasterxml.jackson.databind.util;

import com.shaded.fasterxml.jackson.core.p005io.SerializedString;
import com.shaded.fasterxml.jackson.databind.AnnotationIntrospector;
import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public final class EnumValues {
    private final Class<Enum<?>> _enumClass;
    private final EnumMap<?, SerializedString> _values;

    private EnumValues(Class<Enum<?>> cls, Map<Enum<?>, SerializedString> map) {
        EnumMap<?, SerializedString> enumMap;
        this._enumClass = cls;
        new EnumMap<>(map);
        this._values = enumMap;
    }

    public static EnumValues construct(Class<Enum<?>> cls, AnnotationIntrospector annotationIntrospector) {
        return constructFromName(cls, annotationIntrospector);
    }

    public static EnumValues constructFromName(Class<Enum<?>> cls, AnnotationIntrospector annotationIntrospector) {
        Throwable th;
        StringBuilder sb;
        Map map;
        EnumValues enumValues;
        Object obj;
        Class<Enum<?>> cls2 = cls;
        AnnotationIntrospector annotationIntrospector2 = annotationIntrospector;
        Enum[] enumArr = (Enum[]) ClassUtil.findEnumType((Class<?>) cls2).getEnumConstants();
        if (enumArr != null) {
            new HashMap();
            Map map2 = map;
            Enum[] enumArr2 = enumArr;
            int length = enumArr2.length;
            for (int i = 0; i < length; i++) {
                Enum enumR = enumArr2[i];
                new SerializedString(annotationIntrospector2.findEnumValue(enumR));
                Object put = map2.put(enumR, obj);
            }
            new EnumValues(cls2, map2);
            return enumValues;
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalArgumentException(sb.append("Can not determine enum constants for Class ").append(cls2.getName()).toString());
        throw th2;
    }

    public static EnumValues constructFromToString(Class<Enum<?>> cls, AnnotationIntrospector annotationIntrospector) {
        Throwable th;
        StringBuilder sb;
        Map map;
        EnumValues enumValues;
        Object obj;
        Class<Enum<?>> cls2 = cls;
        AnnotationIntrospector annotationIntrospector2 = annotationIntrospector;
        Enum[] enumArr = (Enum[]) ClassUtil.findEnumType((Class<?>) cls2).getEnumConstants();
        if (enumArr != null) {
            new HashMap();
            Map map2 = map;
            Enum[] enumArr2 = enumArr;
            int length = enumArr2.length;
            for (int i = 0; i < length; i++) {
                Enum enumR = enumArr2[i];
                new SerializedString(enumR.toString());
                Object put = map2.put(enumR, obj);
            }
            new EnumValues(cls2, map2);
            return enumValues;
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalArgumentException(sb.append("Can not determine enum constants for Class ").append(cls2.getName()).toString());
        throw th2;
    }

    public SerializedString serializedValueFor(Enum<?> enumR) {
        return this._values.get(enumR);
    }

    public Collection<SerializedString> values() {
        return this._values.values();
    }

    public EnumMap<?, SerializedString> internalMap() {
        return this._values;
    }

    public Class<Enum<?>> getEnumClass() {
        return this._enumClass;
    }
}

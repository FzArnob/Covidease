package com.shaded.fasterxml.jackson.databind.jsontype.impl;

import com.shaded.fasterxml.jackson.annotation.JsonTypeInfo;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.type.TypeFactory;
import com.shaded.fasterxml.jackson.databind.util.ClassUtil;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;

public class ClassNameIdResolver extends TypeIdResolverBase {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ClassNameIdResolver(JavaType javaType, TypeFactory typeFactory) {
        super(javaType, typeFactory);
    }

    public JsonTypeInfo.C1434Id getMechanism() {
        return JsonTypeInfo.C1434Id.CLASS;
    }

    public void registerSubtype(Class<?> cls, String str) {
    }

    public String idFromValue(Object obj) {
        Object obj2 = obj;
        return _idFrom(obj2, obj2.getClass());
    }

    public String idFromValueAndType(Object obj, Class<?> cls) {
        return _idFrom(obj, cls);
    }

    public JavaType typeFromId(String str) {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        String str2 = str;
        if (str2.indexOf(60) > 0) {
            return this._typeFactory.constructFromCanonical(str2);
        }
        try {
            return this._typeFactory.constructSpecializedType(this._baseType, ClassUtil.findClass(str2));
        } catch (ClassNotFoundException e) {
            ClassNotFoundException classNotFoundException = e;
            Throwable th3 = th2;
            new StringBuilder();
            new IllegalArgumentException(sb2.append("Invalid type id '").append(str2).append("' (for id type 'Id.class'): no such class found").toString());
            throw th3;
        } catch (Exception e2) {
            Exception exc = e2;
            Throwable th4 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Invalid type id '").append(str2).append("' (for id type 'Id.class'): ").append(exc.getMessage()).toString(), exc);
            throw th4;
        }
    }

    /* access modifiers changed from: protected */
    public final String _idFrom(Object obj, Class<?> cls) {
        Object obj2 = obj;
        Class<? super Object> cls2 = cls;
        if (Enum.class.isAssignableFrom(cls2) && !cls2.isEnum()) {
            cls2 = cls2.getSuperclass();
        }
        String name = cls2.getName();
        if (name.startsWith("java.util")) {
            if (obj2 instanceof EnumSet) {
                name = TypeFactory.defaultInstance().constructCollectionType((Class<? extends Collection>) EnumSet.class, (Class<?>) ClassUtil.findEnumType((EnumSet<?>) (EnumSet) obj2)).toCanonical();
            } else if (obj2 instanceof EnumMap) {
                name = TypeFactory.defaultInstance().constructMapType((Class<? extends Map>) EnumMap.class, (Class<?>) ClassUtil.findEnumType((EnumMap<?, ?>) (EnumMap) obj2), (Class<?>) Object.class).toCanonical();
            } else {
                String substring = name.substring(9);
                if ((substring.startsWith(".Arrays$") || substring.startsWith(".Collections$")) && name.indexOf("List") >= 0) {
                    name = "java.util.ArrayList";
                }
            }
        } else if (name.indexOf(36) >= 0 && ClassUtil.getOuterClass(cls2) != null && ClassUtil.getOuterClass(this._baseType.getRawClass()) == null) {
            name = this._baseType.getRawClass().getName();
        }
        return name;
    }
}

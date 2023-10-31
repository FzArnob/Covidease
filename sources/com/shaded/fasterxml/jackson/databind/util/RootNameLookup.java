package com.shaded.fasterxml.jackson.databind.util;

import com.shaded.fasterxml.jackson.core.p005io.SerializedString;
import com.shaded.fasterxml.jackson.databind.BeanDescription;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.PropertyName;
import com.shaded.fasterxml.jackson.databind.cfg.MapperConfig;
import com.shaded.fasterxml.jackson.databind.type.ClassKey;
import java.io.Serializable;

public class RootNameLookup implements Serializable {
    private static final long serialVersionUID = 1;
    protected LRUMap<ClassKey, SerializedString> _rootNames;

    public RootNameLookup() {
    }

    public SerializedString findRootName(JavaType javaType, MapperConfig<?> mapperConfig) {
        return findRootName(javaType.getRawClass(), mapperConfig);
    }

    public synchronized SerializedString findRootName(Class<?> cls, MapperConfig<?> mapperConfig) {
        Object obj;
        SerializedString serializedString;
        String str;
        SerializedString serializedString2;
        LRUMap<ClassKey, SerializedString> lRUMap;
        Class<?> cls2 = cls;
        MapperConfig<?> mapperConfig2 = mapperConfig;
        synchronized (this) {
            new ClassKey(cls2);
            Object obj2 = obj;
            if (this._rootNames == null) {
                new LRUMap(20, 200);
                this._rootNames = lRUMap;
            } else {
                SerializedString serializedString3 = (SerializedString) this._rootNames.get(obj2);
                if (serializedString3 != null) {
                    serializedString = serializedString3;
                }
            }
            BeanDescription introspectClassAnnotations = mapperConfig2.introspectClassAnnotations(cls2);
            PropertyName findRootName = mapperConfig2.getAnnotationIntrospector().findRootName(introspectClassAnnotations.getClassInfo());
            if (findRootName == null || !findRootName.hasSimpleName()) {
                str = cls2.getSimpleName();
            } else {
                str = findRootName.getSimpleName();
            }
            new SerializedString(str);
            SerializedString serializedString4 = serializedString2;
            Object put = this._rootNames.put(obj2, serializedString4);
            serializedString = serializedString4;
        }
        return serializedString;
    }
}

package com.shaded.fasterxml.jackson.databind.ext;

import com.shaded.fasterxml.jackson.databind.BeanDescription;
import com.shaded.fasterxml.jackson.databind.DeserializationConfig;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.JsonSerializer;
import com.shaded.fasterxml.jackson.databind.SerializationConfig;
import com.shaded.fasterxml.jackson.databind.deser.Deserializers;
import com.shaded.fasterxml.jackson.databind.ser.Serializers;
import java.io.Serializable;

public class OptionalHandlerFactory implements Serializable {
    private static final String CLASS_NAME_DOM_DOCUMENT = "org.w3c.dom.Node";
    private static final String CLASS_NAME_DOM_NODE = "org.w3c.dom.Node";
    private static final String DESERIALIZERS_FOR_JAVAX_XML = "com.shaded.fasterxml.jackson.databind.ext.CoreXMLDeserializers";
    private static final String DESERIALIZER_FOR_DOM_DOCUMENT = "com.shaded.fasterxml.jackson.databind.ext.DOMDeserializer$DocumentDeserializer";
    private static final String DESERIALIZER_FOR_DOM_NODE = "com.shaded.fasterxml.jackson.databind.ext.DOMDeserializer$NodeDeserializer";
    private static final String PACKAGE_PREFIX_JAVAX_XML = "javax.xml.";
    private static final String SERIALIZERS_FOR_JAVAX_XML = "com.shaded.fasterxml.jackson.databind.ext.CoreXMLSerializers";
    private static final String SERIALIZER_FOR_DOM_NODE = "com.shaded.fasterxml.jackson.databind.ext.DOMSerializer";
    public static final OptionalHandlerFactory instance;
    private static final long serialVersionUID = 1;

    static {
        OptionalHandlerFactory optionalHandlerFactory;
        new OptionalHandlerFactory();
        instance = optionalHandlerFactory;
    }

    protected OptionalHandlerFactory() {
    }

    public JsonSerializer<?> findSerializer(SerializationConfig serializationConfig, JavaType javaType, BeanDescription beanDescription) {
        SerializationConfig serializationConfig2 = serializationConfig;
        JavaType javaType2 = javaType;
        BeanDescription beanDescription2 = beanDescription;
        Class<?> rawClass = javaType2.getRawClass();
        if (rawClass.getName().startsWith(PACKAGE_PREFIX_JAVAX_XML) || hasSupertypeStartingWith(rawClass, PACKAGE_PREFIX_JAVAX_XML)) {
            Object instantiate = instantiate(SERIALIZERS_FOR_JAVAX_XML);
            if (instantiate == null) {
                return null;
            }
            return ((Serializers) instantiate).findSerializer(serializationConfig2, javaType2, beanDescription2);
        } else if (doesImplement(rawClass, "org.w3c.dom.Node")) {
            return (JsonSerializer) instantiate(SERIALIZER_FOR_DOM_NODE);
        } else {
            return null;
        }
    }

    public JsonDeserializer<?> findDeserializer(JavaType javaType, DeserializationConfig deserializationConfig, BeanDescription beanDescription) throws JsonMappingException {
        JavaType javaType2 = javaType;
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        BeanDescription beanDescription2 = beanDescription;
        Class<?> rawClass = javaType2.getRawClass();
        if (rawClass.getName().startsWith(PACKAGE_PREFIX_JAVAX_XML) || hasSupertypeStartingWith(rawClass, PACKAGE_PREFIX_JAVAX_XML)) {
            Object instantiate = instantiate(DESERIALIZERS_FOR_JAVAX_XML);
            if (instantiate == null) {
                return null;
            }
            return ((Deserializers) instantiate).findBeanDeserializer(javaType2, deserializationConfig2, beanDescription2);
        } else if (doesImplement(rawClass, "org.w3c.dom.Node")) {
            return (JsonDeserializer) instantiate(DESERIALIZER_FOR_DOM_DOCUMENT);
        } else {
            if (doesImplement(rawClass, "org.w3c.dom.Node")) {
                return (JsonDeserializer) instantiate(DESERIALIZER_FOR_DOM_NODE);
            }
            return null;
        }
    }

    private Object instantiate(String str) {
        try {
            return Class.forName(str).newInstance();
        } catch (LinkageError e) {
            LinkageError linkageError = e;
            return null;
        } catch (Exception e2) {
            Exception exc = e2;
            return null;
        }
    }

    private boolean doesImplement(Class<?> cls, String str) {
        String str2 = str;
        Class<?> cls2 = cls;
        while (true) {
            Class<?> cls3 = cls2;
            if (cls3 == null) {
                return false;
            }
            if (cls3.getName().equals(str2)) {
                return true;
            }
            if (hasInterface(cls3, str2)) {
                return true;
            }
            cls2 = cls3.getSuperclass();
        }
    }

    private boolean hasInterface(Class<?> cls, String str) {
        String str2 = str;
        Class[] interfaces = cls.getInterfaces();
        Class[] clsArr = interfaces;
        int length = clsArr.length;
        for (int i = 0; i < length; i++) {
            if (clsArr[i].getName().equals(str2)) {
                return true;
            }
        }
        Class[] clsArr2 = interfaces;
        int length2 = clsArr2.length;
        for (int i2 = 0; i2 < length2; i2++) {
            if (hasInterface(clsArr2[i2], str2)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasSupertypeStartingWith(Class<?> cls, String str) {
        Class<?> cls2 = cls;
        String str2 = str;
        Class<? super Object> superclass = cls2.getSuperclass();
        while (true) {
            Class<? super Object> cls3 = superclass;
            if (cls3 == null) {
                Class<?> cls4 = cls2;
                while (true) {
                    Class<?> cls5 = cls4;
                    if (cls5 == null) {
                        return false;
                    }
                    if (hasInterfaceStartingWith(cls5, str2)) {
                        return true;
                    }
                    cls4 = cls5.getSuperclass();
                }
            } else if (cls3.getName().startsWith(str2)) {
                return true;
            } else {
                superclass = cls3.getSuperclass();
            }
        }
    }

    private boolean hasInterfaceStartingWith(Class<?> cls, String str) {
        String str2 = str;
        Class[] interfaces = cls.getInterfaces();
        Class[] clsArr = interfaces;
        int length = clsArr.length;
        for (int i = 0; i < length; i++) {
            if (clsArr[i].getName().startsWith(str2)) {
                return true;
            }
        }
        Class[] clsArr2 = interfaces;
        int length2 = clsArr2.length;
        for (int i2 = 0; i2 < length2; i2++) {
            if (hasInterfaceStartingWith(clsArr2[i2], str2)) {
                return true;
            }
        }
        return false;
    }
}

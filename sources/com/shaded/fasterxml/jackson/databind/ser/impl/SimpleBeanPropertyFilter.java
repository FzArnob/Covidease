package com.shaded.fasterxml.jackson.databind.ser.impl;

import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.shaded.fasterxml.jackson.databind.node.ObjectNode;
import com.shaded.fasterxml.jackson.databind.ser.BeanPropertyFilter;
import com.shaded.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class SimpleBeanPropertyFilter implements BeanPropertyFilter {
    /* access modifiers changed from: protected */
    public abstract boolean include(BeanPropertyWriter beanPropertyWriter);

    protected SimpleBeanPropertyFilter() {
    }

    public static SimpleBeanPropertyFilter filterOutAllExcept(Set<String> set) {
        SimpleBeanPropertyFilter simpleBeanPropertyFilter;
        new FilterExceptFilter(set);
        return simpleBeanPropertyFilter;
    }

    public static SimpleBeanPropertyFilter filterOutAllExcept(String... strArr) {
        Set set;
        SimpleBeanPropertyFilter simpleBeanPropertyFilter;
        String[] strArr2 = strArr;
        new HashSet(strArr2.length);
        Set set2 = set;
        boolean addAll = Collections.addAll(set2, strArr2);
        new FilterExceptFilter(set2);
        return simpleBeanPropertyFilter;
    }

    public static SimpleBeanPropertyFilter serializeAllExcept(Set<String> set) {
        SimpleBeanPropertyFilter simpleBeanPropertyFilter;
        new SerializeExceptFilter(set);
        return simpleBeanPropertyFilter;
    }

    public static SimpleBeanPropertyFilter serializeAllExcept(String... strArr) {
        Set set;
        SimpleBeanPropertyFilter simpleBeanPropertyFilter;
        String[] strArr2 = strArr;
        new HashSet(strArr2.length);
        Set set2 = set;
        boolean addAll = Collections.addAll(set2, strArr2);
        new SerializeExceptFilter(set2);
        return simpleBeanPropertyFilter;
    }

    public void serializeAsField(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, BeanPropertyWriter beanPropertyWriter) throws Exception {
        Object obj2 = obj;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        BeanPropertyWriter beanPropertyWriter2 = beanPropertyWriter;
        if (include(beanPropertyWriter2)) {
            beanPropertyWriter2.serializeAsField(obj2, jsonGenerator2, serializerProvider2);
        }
    }

    public void depositSchemaProperty(BeanPropertyWriter beanPropertyWriter, ObjectNode objectNode, SerializerProvider serializerProvider) throws JsonMappingException {
        BeanPropertyWriter beanPropertyWriter2 = beanPropertyWriter;
        ObjectNode objectNode2 = objectNode;
        SerializerProvider serializerProvider2 = serializerProvider;
        if (include(beanPropertyWriter2)) {
            beanPropertyWriter2.depositSchemaProperty(objectNode2, serializerProvider2);
        }
    }

    public void depositSchemaProperty(BeanPropertyWriter beanPropertyWriter, JsonObjectFormatVisitor jsonObjectFormatVisitor, SerializerProvider serializerProvider) throws JsonMappingException {
        BeanPropertyWriter beanPropertyWriter2 = beanPropertyWriter;
        JsonObjectFormatVisitor jsonObjectFormatVisitor2 = jsonObjectFormatVisitor;
        SerializerProvider serializerProvider2 = serializerProvider;
        if (include(beanPropertyWriter2)) {
            beanPropertyWriter2.depositSchemaProperty(jsonObjectFormatVisitor2);
        }
    }

    public static class FilterExceptFilter extends SimpleBeanPropertyFilter implements Serializable {
        private static final long serialVersionUID = 1;
        protected final Set<String> _propertiesToInclude;

        public FilterExceptFilter(Set<String> set) {
            this._propertiesToInclude = set;
        }

        /* access modifiers changed from: protected */
        public boolean include(BeanPropertyWriter beanPropertyWriter) {
            return this._propertiesToInclude.contains(beanPropertyWriter.getName());
        }
    }

    public static class SerializeExceptFilter extends SimpleBeanPropertyFilter {
        protected final Set<String> _propertiesToExclude;

        public SerializeExceptFilter(Set<String> set) {
            this._propertiesToExclude = set;
        }

        /* access modifiers changed from: protected */
        public boolean include(BeanPropertyWriter beanPropertyWriter) {
            return !this._propertiesToExclude.contains(beanPropertyWriter.getName());
        }
    }
}

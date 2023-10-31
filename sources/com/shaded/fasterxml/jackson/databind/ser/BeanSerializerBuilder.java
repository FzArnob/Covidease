package com.shaded.fasterxml.jackson.databind.ser;

import com.shaded.fasterxml.jackson.databind.BeanDescription;
import com.shaded.fasterxml.jackson.databind.JsonSerializer;
import com.shaded.fasterxml.jackson.databind.SerializationConfig;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.shaded.fasterxml.jackson.databind.ser.impl.ObjectIdWriter;
import java.util.List;

public class BeanSerializerBuilder {
    private static final BeanPropertyWriter[] NO_PROPERTIES = new BeanPropertyWriter[0];
    protected AnyGetterWriter _anyGetter;
    protected final BeanDescription _beanDesc;
    protected SerializationConfig _config;
    protected Object _filterId;
    protected BeanPropertyWriter[] _filteredProperties;
    protected ObjectIdWriter _objectIdWriter;
    protected List<BeanPropertyWriter> _properties;
    protected AnnotatedMember _typeId;

    public BeanSerializerBuilder(BeanDescription beanDescription) {
        this._beanDesc = beanDescription;
    }

    protected BeanSerializerBuilder(BeanSerializerBuilder beanSerializerBuilder) {
        BeanSerializerBuilder beanSerializerBuilder2 = beanSerializerBuilder;
        this._beanDesc = beanSerializerBuilder2._beanDesc;
        this._properties = beanSerializerBuilder2._properties;
        this._filteredProperties = beanSerializerBuilder2._filteredProperties;
        this._anyGetter = beanSerializerBuilder2._anyGetter;
        this._filterId = beanSerializerBuilder2._filterId;
    }

    /* access modifiers changed from: protected */
    public void setConfig(SerializationConfig serializationConfig) {
        SerializationConfig serializationConfig2 = serializationConfig;
        this._config = serializationConfig2;
    }

    public void setProperties(List<BeanPropertyWriter> list) {
        List<BeanPropertyWriter> list2 = list;
        this._properties = list2;
    }

    public void setFilteredProperties(BeanPropertyWriter[] beanPropertyWriterArr) {
        BeanPropertyWriter[] beanPropertyWriterArr2 = beanPropertyWriterArr;
        this._filteredProperties = beanPropertyWriterArr2;
    }

    public void setAnyGetter(AnyGetterWriter anyGetterWriter) {
        AnyGetterWriter anyGetterWriter2 = anyGetterWriter;
        this._anyGetter = anyGetterWriter2;
    }

    public void setFilterId(Object obj) {
        Object obj2 = obj;
        this._filterId = obj2;
    }

    public void setTypeId(AnnotatedMember annotatedMember) {
        Throwable th;
        StringBuilder sb;
        AnnotatedMember annotatedMember2 = annotatedMember;
        if (this._typeId != null) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Multiple type ids specified with ").append(this._typeId).append(" and ").append(annotatedMember2).toString());
            throw th2;
        }
        this._typeId = annotatedMember2;
    }

    public void setObjectIdWriter(ObjectIdWriter objectIdWriter) {
        ObjectIdWriter objectIdWriter2 = objectIdWriter;
        this._objectIdWriter = objectIdWriter2;
    }

    public AnnotatedClass getClassInfo() {
        return this._beanDesc.getClassInfo();
    }

    public BeanDescription getBeanDescription() {
        return this._beanDesc;
    }

    public List<BeanPropertyWriter> getProperties() {
        return this._properties;
    }

    public boolean hasProperties() {
        return this._properties != null && this._properties.size() > 0;
    }

    public BeanPropertyWriter[] getFilteredProperties() {
        return this._filteredProperties;
    }

    public AnyGetterWriter getAnyGetter() {
        return this._anyGetter;
    }

    public Object getFilterId() {
        return this._filterId;
    }

    public AnnotatedMember getTypeId() {
        return this._typeId;
    }

    public ObjectIdWriter getObjectIdWriter() {
        return this._objectIdWriter;
    }

    public JsonSerializer<?> build() {
        BeanPropertyWriter[] beanPropertyWriterArr;
        JsonSerializer<?> jsonSerializer;
        if (this._properties != null && !this._properties.isEmpty()) {
            beanPropertyWriterArr = (BeanPropertyWriter[]) this._properties.toArray(new BeanPropertyWriter[this._properties.size()]);
        } else if (this._anyGetter == null) {
            return null;
        } else {
            beanPropertyWriterArr = NO_PROPERTIES;
        }
        new BeanSerializer(this._beanDesc.getType(), this, beanPropertyWriterArr, this._filteredProperties);
        return jsonSerializer;
    }

    public BeanSerializer createDummy() {
        return BeanSerializer.createDummy(this._beanDesc.getType());
    }
}

package com.shaded.fasterxml.jackson.databind.ser.std;

import com.shaded.fasterxml.jackson.annotation.JsonFormat;
import com.shaded.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.shaded.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.shaded.fasterxml.jackson.core.JsonGenerationException;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.databind.AnnotationIntrospector;
import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.JsonNode;
import com.shaded.fasterxml.jackson.databind.JsonSerializer;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import com.shaded.fasterxml.jackson.databind.introspect.Annotated;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.shaded.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.shaded.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import com.shaded.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.shaded.fasterxml.jackson.databind.node.ObjectNode;
import com.shaded.fasterxml.jackson.databind.ser.AnyGetterWriter;
import com.shaded.fasterxml.jackson.databind.ser.BeanPropertyFilter;
import com.shaded.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.shaded.fasterxml.jackson.databind.ser.BeanSerializerBuilder;
import com.shaded.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.shaded.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.shaded.fasterxml.jackson.databind.ser.FilterProvider;
import com.shaded.fasterxml.jackson.databind.ser.ResolvableSerializer;
import com.shaded.fasterxml.jackson.databind.ser.impl.ObjectIdWriter;
import com.shaded.fasterxml.jackson.databind.ser.impl.PropertyBasedObjectIdGenerator;
import com.shaded.fasterxml.jackson.databind.ser.impl.WritableObjectId;
import com.shaded.fasterxml.jackson.databind.util.Converter;
import com.shaded.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;
import java.lang.reflect.Type;

public abstract class BeanSerializerBase extends StdSerializer<Object> implements ContextualSerializer, ResolvableSerializer, JsonFormatVisitable, SchemaAware {
    protected static final BeanPropertyWriter[] NO_PROPS = new BeanPropertyWriter[0];
    protected final AnyGetterWriter _anyGetterWriter;
    protected final BeanPropertyWriter[] _filteredProps;
    protected final ObjectIdWriter _objectIdWriter;
    protected final Object _propertyFilterId;
    protected final BeanPropertyWriter[] _props;
    protected final JsonFormat.Shape _serializationShape;
    protected final AnnotatedMember _typeId;

    /* access modifiers changed from: protected */
    public abstract BeanSerializerBase asArraySerializer();

    public abstract void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException;

    /* access modifiers changed from: protected */
    public abstract BeanSerializerBase withIgnorals(String[] strArr);

    public abstract BeanSerializerBase withObjectIdWriter(ObjectIdWriter objectIdWriter);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected BeanSerializerBase(JavaType javaType, BeanSerializerBuilder beanSerializerBuilder, BeanPropertyWriter[] beanPropertyWriterArr, BeanPropertyWriter[] beanPropertyWriterArr2) {
        super(javaType);
        BeanSerializerBuilder beanSerializerBuilder2 = beanSerializerBuilder;
        this._props = beanPropertyWriterArr;
        this._filteredProps = beanPropertyWriterArr2;
        if (beanSerializerBuilder2 == null) {
            this._typeId = null;
            this._anyGetterWriter = null;
            this._propertyFilterId = null;
            this._objectIdWriter = null;
            this._serializationShape = null;
            return;
        }
        this._typeId = beanSerializerBuilder2.getTypeId();
        this._anyGetterWriter = beanSerializerBuilder2.getAnyGetter();
        this._propertyFilterId = beanSerializerBuilder2.getFilterId();
        this._objectIdWriter = beanSerializerBuilder2.getObjectIdWriter();
        JsonFormat.Value findExpectedFormat = beanSerializerBuilder2.getBeanDescription().findExpectedFormat((JsonFormat.Value) null);
        this._serializationShape = findExpectedFormat == null ? null : findExpectedFormat.getShape();
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BeanSerializerBase(com.shaded.fasterxml.jackson.databind.ser.std.BeanSerializerBase r7, com.shaded.fasterxml.jackson.databind.ser.BeanPropertyWriter[] r8, com.shaded.fasterxml.jackson.databind.ser.BeanPropertyWriter[] r9) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r8
            r3 = r9
            r4 = r0
            r5 = r1
            java.lang.Class r5 = r5._handledType
            r4.<init>(r5)
            r4 = r0
            r5 = r2
            r4._props = r5
            r4 = r0
            r5 = r3
            r4._filteredProps = r5
            r4 = r0
            r5 = r1
            com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMember r5 = r5._typeId
            r4._typeId = r5
            r4 = r0
            r5 = r1
            com.shaded.fasterxml.jackson.databind.ser.AnyGetterWriter r5 = r5._anyGetterWriter
            r4._anyGetterWriter = r5
            r4 = r0
            r5 = r1
            com.shaded.fasterxml.jackson.databind.ser.impl.ObjectIdWriter r5 = r5._objectIdWriter
            r4._objectIdWriter = r5
            r4 = r0
            r5 = r1
            java.lang.Object r5 = r5._propertyFilterId
            r4._propertyFilterId = r5
            r4 = r0
            r5 = r1
            com.shaded.fasterxml.jackson.annotation.JsonFormat$Shape r5 = r5._serializationShape
            r4._serializationShape = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.ser.std.BeanSerializerBase.<init>(com.shaded.fasterxml.jackson.databind.ser.std.BeanSerializerBase, com.shaded.fasterxml.jackson.databind.ser.BeanPropertyWriter[], com.shaded.fasterxml.jackson.databind.ser.BeanPropertyWriter[]):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected BeanSerializerBase(com.shaded.fasterxml.jackson.databind.ser.std.BeanSerializerBase r6, com.shaded.fasterxml.jackson.databind.ser.impl.ObjectIdWriter r7) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r2 = r7
            r3 = r0
            r4 = r1
            java.lang.Class r4 = r4._handledType
            r3.<init>(r4)
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.ser.BeanPropertyWriter[] r4 = r4._props
            r3._props = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.ser.BeanPropertyWriter[] r4 = r4._filteredProps
            r3._filteredProps = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMember r4 = r4._typeId
            r3._typeId = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.ser.AnyGetterWriter r4 = r4._anyGetterWriter
            r3._anyGetterWriter = r4
            r3 = r0
            r4 = r2
            r3._objectIdWriter = r4
            r3 = r0
            r4 = r1
            java.lang.Object r4 = r4._propertyFilterId
            r3._propertyFilterId = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.annotation.JsonFormat$Shape r4 = r4._serializationShape
            r3._serializationShape = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.ser.std.BeanSerializerBase.<init>(com.shaded.fasterxml.jackson.databind.ser.std.BeanSerializerBase, com.shaded.fasterxml.jackson.databind.ser.impl.ObjectIdWriter):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected BeanSerializerBase(com.shaded.fasterxml.jackson.databind.ser.std.BeanSerializerBase r16, java.lang.String[] r17) {
        /*
            r15 = this;
            r0 = r15
            r1 = r16
            r2 = r17
            r11 = r0
            r12 = r1
            java.lang.Class r12 = r12._handledType
            r11.<init>(r12)
            r11 = r2
            java.util.HashSet r11 = com.shaded.fasterxml.jackson.databind.util.ArrayBuilders.arrayToSet(r11)
            r3 = r11
            r11 = r1
            com.shaded.fasterxml.jackson.databind.ser.BeanPropertyWriter[] r11 = r11._props
            r4 = r11
            r11 = r1
            com.shaded.fasterxml.jackson.databind.ser.BeanPropertyWriter[] r11 = r11._filteredProps
            r5 = r11
            r11 = r4
            int r11 = r11.length
            r6 = r11
            java.util.ArrayList r11 = new java.util.ArrayList
            r14 = r11
            r11 = r14
            r12 = r14
            r13 = r6
            r12.<init>(r13)
            r7 = r11
            r11 = r5
            if (r11 != 0) goto L_0x0046
            r11 = 0
        L_0x002b:
            r8 = r11
            r11 = 0
            r9 = r11
        L_0x002e:
            r11 = r9
            r12 = r6
            if (r11 >= r12) goto L_0x0063
            r11 = r4
            r12 = r9
            r11 = r11[r12]
            r10 = r11
            r11 = r3
            r12 = r10
            java.lang.String r12 = r12.getName()
            boolean r11 = r11.contains(r12)
            if (r11 == 0) goto L_0x0050
        L_0x0043:
            int r9 = r9 + 1
            goto L_0x002e
        L_0x0046:
            java.util.ArrayList r11 = new java.util.ArrayList
            r14 = r11
            r11 = r14
            r12 = r14
            r13 = r6
            r12.<init>(r13)
            goto L_0x002b
        L_0x0050:
            r11 = r7
            r12 = r10
            boolean r11 = r11.add(r12)
            r11 = r5
            if (r11 == 0) goto L_0x0043
            r11 = r8
            r12 = r5
            r13 = r9
            r12 = r12[r13]
            boolean r11 = r11.add(r12)
            goto L_0x0043
        L_0x0063:
            r11 = r0
            r12 = r7
            r13 = r7
            int r13 = r13.size()
            com.shaded.fasterxml.jackson.databind.ser.BeanPropertyWriter[] r13 = new com.shaded.fasterxml.jackson.databind.ser.BeanPropertyWriter[r13]
            java.lang.Object[] r12 = r12.toArray(r13)
            com.shaded.fasterxml.jackson.databind.ser.BeanPropertyWriter[] r12 = (com.shaded.fasterxml.jackson.databind.ser.BeanPropertyWriter[]) r12
            r11._props = r12
            r11 = r0
            r12 = r8
            if (r12 != 0) goto L_0x009a
            r12 = 0
        L_0x0079:
            r11._filteredProps = r12
            r11 = r0
            r12 = r1
            com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMember r12 = r12._typeId
            r11._typeId = r12
            r11 = r0
            r12 = r1
            com.shaded.fasterxml.jackson.databind.ser.AnyGetterWriter r12 = r12._anyGetterWriter
            r11._anyGetterWriter = r12
            r11 = r0
            r12 = r1
            com.shaded.fasterxml.jackson.databind.ser.impl.ObjectIdWriter r12 = r12._objectIdWriter
            r11._objectIdWriter = r12
            r11 = r0
            r12 = r1
            java.lang.Object r12 = r12._propertyFilterId
            r11._propertyFilterId = r12
            r11 = r0
            r12 = r1
            com.shaded.fasterxml.jackson.annotation.JsonFormat$Shape r12 = r12._serializationShape
            r11._serializationShape = r12
            return
        L_0x009a:
            r12 = r8
            r13 = r8
            int r13 = r13.size()
            com.shaded.fasterxml.jackson.databind.ser.BeanPropertyWriter[] r13 = new com.shaded.fasterxml.jackson.databind.ser.BeanPropertyWriter[r13]
            java.lang.Object[] r12 = r12.toArray(r13)
            com.shaded.fasterxml.jackson.databind.ser.BeanPropertyWriter[] r12 = (com.shaded.fasterxml.jackson.databind.ser.BeanPropertyWriter[]) r12
            goto L_0x0079
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.ser.std.BeanSerializerBase.<init>(com.shaded.fasterxml.jackson.databind.ser.std.BeanSerializerBase, java.lang.String[]):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected BeanSerializerBase(com.shaded.fasterxml.jackson.databind.ser.std.BeanSerializerBase r7) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r0
            r3 = r1
            r4 = r1
            com.shaded.fasterxml.jackson.databind.ser.BeanPropertyWriter[] r4 = r4._props
            r5 = r1
            com.shaded.fasterxml.jackson.databind.ser.BeanPropertyWriter[] r5 = r5._filteredProps
            r2.<init>(r3, r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.ser.std.BeanSerializerBase.<init>(com.shaded.fasterxml.jackson.databind.ser.std.BeanSerializerBase):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected BeanSerializerBase(com.shaded.fasterxml.jackson.databind.ser.std.BeanSerializerBase r9, com.shaded.fasterxml.jackson.databind.util.NameTransformer r10) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r10
            r3 = r0
            r4 = r1
            r5 = r1
            com.shaded.fasterxml.jackson.databind.ser.BeanPropertyWriter[] r5 = r5._props
            r6 = r2
            com.shaded.fasterxml.jackson.databind.ser.BeanPropertyWriter[] r5 = rename(r5, r6)
            r6 = r1
            com.shaded.fasterxml.jackson.databind.ser.BeanPropertyWriter[] r6 = r6._filteredProps
            r7 = r2
            com.shaded.fasterxml.jackson.databind.ser.BeanPropertyWriter[] r6 = rename(r6, r7)
            r3.<init>(r4, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.ser.std.BeanSerializerBase.<init>(com.shaded.fasterxml.jackson.databind.ser.std.BeanSerializerBase, com.shaded.fasterxml.jackson.databind.util.NameTransformer):void");
    }

    private static final BeanPropertyWriter[] rename(BeanPropertyWriter[] beanPropertyWriterArr, NameTransformer nameTransformer) {
        BeanPropertyWriter[] beanPropertyWriterArr2 = beanPropertyWriterArr;
        NameTransformer nameTransformer2 = nameTransformer;
        if (beanPropertyWriterArr2 == null || beanPropertyWriterArr2.length == 0 || nameTransformer2 == null || nameTransformer2 == NameTransformer.NOP) {
            return beanPropertyWriterArr2;
        }
        int length = beanPropertyWriterArr2.length;
        BeanPropertyWriter[] beanPropertyWriterArr3 = new BeanPropertyWriter[length];
        for (int i = 0; i < length; i++) {
            BeanPropertyWriter beanPropertyWriter = beanPropertyWriterArr2[i];
            if (beanPropertyWriter != null) {
                beanPropertyWriterArr3[i] = beanPropertyWriter.rename(nameTransformer2);
            }
        }
        return beanPropertyWriterArr3;
    }

    public void resolve(SerializerProvider serializerProvider) throws JsonMappingException {
        int length;
        BeanPropertyWriter beanPropertyWriter;
        TypeSerializer typeSerializer;
        JsonSerializer<Object> findNullValueSerializer;
        BeanPropertyWriter beanPropertyWriter2;
        SerializerProvider serializerProvider2 = serializerProvider;
        if (this._filteredProps == null) {
            length = 0;
        } else {
            length = this._filteredProps.length;
        }
        int i = length;
        int length2 = this._props.length;
        for (int i2 = 0; i2 < length2; i2++) {
            BeanPropertyWriter beanPropertyWriter3 = this._props[i2];
            if (!beanPropertyWriter3.willSuppressNulls() && !beanPropertyWriter3.hasNullSerializer() && (findNullValueSerializer = serializerProvider2.findNullValueSerializer(beanPropertyWriter3)) != null) {
                beanPropertyWriter3.assignNullSerializer(findNullValueSerializer);
                if (i2 < i && (beanPropertyWriter2 = this._filteredProps[i2]) != null) {
                    beanPropertyWriter2.assignNullSerializer(findNullValueSerializer);
                }
            }
            if (!beanPropertyWriter3.hasSerializer()) {
                ContainerSerializer<?> findConvertingSerializer = findConvertingSerializer(serializerProvider2, beanPropertyWriter3);
                if (findConvertingSerializer == null) {
                    JavaType serializationType = beanPropertyWriter3.getSerializationType();
                    if (serializationType == null) {
                        serializationType = serializerProvider2.constructType(beanPropertyWriter3.getGenericPropertyType());
                        if (!serializationType.isFinal()) {
                            if (serializationType.isContainerType() || serializationType.containedTypeCount() > 0) {
                                beanPropertyWriter3.setNonTrivialBaseType(serializationType);
                            }
                        }
                    }
                    findConvertingSerializer = serializerProvider2.findValueSerializer(serializationType, (BeanProperty) beanPropertyWriter3);
                    if (serializationType.isContainerType() && (typeSerializer = (TypeSerializer) serializationType.getContentType().getTypeHandler()) != null && (findConvertingSerializer instanceof ContainerSerializer)) {
                        findConvertingSerializer = ((ContainerSerializer) findConvertingSerializer).withValueTypeSerializer(typeSerializer);
                    }
                }
                beanPropertyWriter3.assignSerializer(findConvertingSerializer);
                if (i2 < i && (beanPropertyWriter = this._filteredProps[i2]) != null) {
                    beanPropertyWriter.assignSerializer(findConvertingSerializer);
                }
            }
        }
        if (this._anyGetterWriter != null) {
            this._anyGetterWriter.resolve(serializerProvider2);
        }
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<Object> findConvertingSerializer(SerializerProvider serializerProvider, BeanPropertyWriter beanPropertyWriter) throws JsonMappingException {
        Object findSerializationConverter;
        JsonSerializer<Object> jsonSerializer;
        SerializerProvider serializerProvider2 = serializerProvider;
        BeanPropertyWriter beanPropertyWriter2 = beanPropertyWriter;
        AnnotationIntrospector annotationIntrospector = serializerProvider2.getAnnotationIntrospector();
        if (annotationIntrospector == null || (findSerializationConverter = annotationIntrospector.findSerializationConverter(beanPropertyWriter2.getMember())) == null) {
            return null;
        }
        Converter<Object, Object> converterInstance = serializerProvider2.converterInstance(beanPropertyWriter2.getMember(), findSerializationConverter);
        JavaType outputType = converterInstance.getOutputType(serializerProvider2.getTypeFactory());
        new StdDelegatingSerializer(converterInstance, outputType, serializerProvider2.findValueSerializer(outputType, (BeanProperty) beanPropertyWriter2));
        return jsonSerializer;
    }

    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        AnnotatedMember annotatedMember;
        JsonFormat.Value findFormat;
        ObjectIdWriter withSerializer;
        Throwable th;
        StringBuilder sb;
        ObjectIdGenerator objectIdGenerator;
        ObjectIdInfo objectIdInfo;
        SerializerProvider serializerProvider2 = serializerProvider;
        BeanProperty beanProperty2 = beanProperty;
        ObjectIdWriter objectIdWriter = this._objectIdWriter;
        String[] strArr = null;
        AnnotationIntrospector annotationIntrospector = serializerProvider2.getAnnotationIntrospector();
        if (beanProperty2 == null || annotationIntrospector == null) {
            annotatedMember = null;
        } else {
            annotatedMember = beanProperty2.getMember();
        }
        AnnotatedMember annotatedMember2 = annotatedMember;
        if (annotatedMember2 != null) {
            strArr = annotationIntrospector.findPropertiesToIgnore(annotatedMember2);
            ObjectIdInfo findObjectIdInfo = annotationIntrospector.findObjectIdInfo(annotatedMember2);
            if (findObjectIdInfo != null) {
                ObjectIdInfo findObjectReferenceInfo = annotationIntrospector.findObjectReferenceInfo(annotatedMember2, findObjectIdInfo);
                Class<? extends ObjectIdGenerator<?>> generatorType = findObjectReferenceInfo.getGeneratorType();
                JavaType javaType = serializerProvider2.getTypeFactory().findTypeParameters(serializerProvider2.constructType(generatorType), (Class<?>) ObjectIdGenerator.class)[0];
                if (generatorType == ObjectIdGenerators.PropertyGenerator.class) {
                    String propertyName = findObjectReferenceInfo.getPropertyName();
                    int i = 0;
                    int length = this._props.length;
                    while (i != length) {
                        BeanPropertyWriter beanPropertyWriter = this._props[i];
                        if (propertyName.equals(beanPropertyWriter.getName())) {
                            BeanPropertyWriter beanPropertyWriter2 = beanPropertyWriter;
                            if (i > 0) {
                                System.arraycopy(this._props, 0, this._props, 1, i);
                                this._props[0] = beanPropertyWriter2;
                                if (this._filteredProps != null) {
                                    BeanPropertyWriter beanPropertyWriter3 = this._filteredProps[i];
                                    System.arraycopy(this._filteredProps, 0, this._filteredProps, 1, i);
                                    this._filteredProps[0] = beanPropertyWriter3;
                                }
                            }
                            JavaType type = beanPropertyWriter2.getType();
                            new PropertyBasedObjectIdGenerator(findObjectReferenceInfo, beanPropertyWriter2);
                            objectIdWriter = ObjectIdWriter.construct(type, (String) null, objectIdGenerator, findObjectReferenceInfo.getAlwaysAsId());
                        } else {
                            i++;
                        }
                    }
                    Throwable th2 = th;
                    new StringBuilder();
                    new IllegalArgumentException(sb.append("Invalid Object Id definition for ").append(this._handledType.getName()).append(": can not find property with name '").append(propertyName).append("'").toString());
                    throw th2;
                }
                objectIdWriter = ObjectIdWriter.construct(javaType, findObjectReferenceInfo.getPropertyName(), serializerProvider2.objectIdGeneratorInstance(annotatedMember2, findObjectReferenceInfo), findObjectReferenceInfo.getAlwaysAsId());
            } else if (objectIdWriter != null) {
                new ObjectIdInfo("", (Class<?>) null, (Class<? extends ObjectIdGenerator<?>>) null);
                objectIdWriter = this._objectIdWriter.withAlwaysAsId(annotationIntrospector.findObjectReferenceInfo(annotatedMember2, objectIdInfo).getAlwaysAsId());
            }
        }
        BeanSerializerBase beanSerializerBase = this;
        if (!(objectIdWriter == null || (withSerializer = objectIdWriter.withSerializer(serializerProvider2.findValueSerializer(objectIdWriter.idType, beanProperty2))) == this._objectIdWriter)) {
            beanSerializerBase = beanSerializerBase.withObjectIdWriter(withSerializer);
        }
        if (!(strArr == null || strArr.length == 0)) {
            beanSerializerBase = beanSerializerBase.withIgnorals(strArr);
        }
        JsonFormat.Shape shape = null;
        if (!(annotatedMember2 == null || (findFormat = annotationIntrospector.findFormat((Annotated) annotatedMember2)) == null)) {
            shape = findFormat.getShape();
        }
        if (shape == null) {
            shape = this._serializationShape;
        }
        if (shape == JsonFormat.Shape.ARRAY) {
            beanSerializerBase = beanSerializerBase.asArraySerializer();
        }
        return beanSerializerBase;
    }

    public boolean usesObjectId() {
        return this._objectIdWriter != null;
    }

    public void serializeWithType(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonGenerationException {
        Object obj2 = obj;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        TypeSerializer typeSerializer2 = typeSerializer;
        if (this._objectIdWriter != null) {
            _serializeWithObjectId(obj2, jsonGenerator2, serializerProvider2, typeSerializer2);
            return;
        }
        String _customTypeId = this._typeId == null ? null : _customTypeId(obj2);
        if (_customTypeId == null) {
            typeSerializer2.writeTypePrefixForObject(obj2, jsonGenerator2);
        } else {
            typeSerializer2.writeCustomTypePrefixForObject(obj2, jsonGenerator2, _customTypeId);
        }
        if (this._propertyFilterId != null) {
            serializeFieldsFiltered(obj2, jsonGenerator2, serializerProvider2);
        } else {
            serializeFields(obj2, jsonGenerator2, serializerProvider2);
        }
        if (_customTypeId == null) {
            typeSerializer2.writeTypeSuffixForObject(obj2, jsonGenerator2);
        } else {
            typeSerializer2.writeCustomTypeSuffixForObject(obj2, jsonGenerator2, _customTypeId);
        }
    }

    /* access modifiers changed from: protected */
    public final void _serializeWithObjectId(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, boolean z) throws IOException, JsonGenerationException {
        Object obj2 = obj;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        boolean z2 = z;
        ObjectIdWriter objectIdWriter = this._objectIdWriter;
        WritableObjectId findObjectId = serializerProvider2.findObjectId(obj2, objectIdWriter.generator);
        if (!findObjectId.writeAsId(jsonGenerator2, serializerProvider2, objectIdWriter)) {
            Object generateId = findObjectId.generateId(obj2);
            if (objectIdWriter.alwaysAsId) {
                objectIdWriter.serializer.serialize(generateId, jsonGenerator2, serializerProvider2);
                return;
            }
            if (z2) {
                jsonGenerator2.writeStartObject();
            }
            findObjectId.writeAsField(jsonGenerator2, serializerProvider2, objectIdWriter);
            if (this._propertyFilterId != null) {
                serializeFieldsFiltered(obj2, jsonGenerator2, serializerProvider2);
            } else {
                serializeFields(obj2, jsonGenerator2, serializerProvider2);
            }
            if (z2) {
                jsonGenerator2.writeEndObject();
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void _serializeWithObjectId(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonGenerationException {
        Object obj2 = obj;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        TypeSerializer typeSerializer2 = typeSerializer;
        ObjectIdWriter objectIdWriter = this._objectIdWriter;
        WritableObjectId findObjectId = serializerProvider2.findObjectId(obj2, objectIdWriter.generator);
        if (!findObjectId.writeAsId(jsonGenerator2, serializerProvider2, objectIdWriter)) {
            Object generateId = findObjectId.generateId(obj2);
            if (objectIdWriter.alwaysAsId) {
                objectIdWriter.serializer.serialize(generateId, jsonGenerator2, serializerProvider2);
                return;
            }
            String _customTypeId = this._typeId == null ? null : _customTypeId(obj2);
            if (_customTypeId == null) {
                typeSerializer2.writeTypePrefixForObject(obj2, jsonGenerator2);
            } else {
                typeSerializer2.writeCustomTypePrefixForObject(obj2, jsonGenerator2, _customTypeId);
            }
            findObjectId.writeAsField(jsonGenerator2, serializerProvider2, objectIdWriter);
            if (this._propertyFilterId != null) {
                serializeFieldsFiltered(obj2, jsonGenerator2, serializerProvider2);
            } else {
                serializeFields(obj2, jsonGenerator2, serializerProvider2);
            }
            if (_customTypeId == null) {
                typeSerializer2.writeTypeSuffixForObject(obj2, jsonGenerator2);
            } else {
                typeSerializer2.writeCustomTypeSuffixForObject(obj2, jsonGenerator2, _customTypeId);
            }
        }
    }

    private final String _customTypeId(Object obj) {
        Object value = this._typeId.getValue(obj);
        if (value == null) {
            return "";
        }
        return value instanceof String ? (String) value : value.toString();
    }

    /* access modifiers changed from: protected */
    public void serializeFields(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        BeanPropertyWriter[] beanPropertyWriterArr;
        JsonMappingException jsonMappingException;
        JsonMappingException.Reference reference;
        Object obj2 = obj;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        if (this._filteredProps == null || serializerProvider2.getActiveView() == null) {
            beanPropertyWriterArr = this._props;
        } else {
            beanPropertyWriterArr = this._filteredProps;
        }
        int i = 0;
        try {
            int length = beanPropertyWriterArr.length;
            while (i < length) {
                BeanPropertyWriter beanPropertyWriter = beanPropertyWriterArr[i];
                if (beanPropertyWriter != null) {
                    beanPropertyWriter.serializeAsField(obj2, jsonGenerator2, serializerProvider2);
                }
                i++;
            }
            if (this._anyGetterWriter != null) {
                this._anyGetterWriter.getAndSerialize(obj2, jsonGenerator2, serializerProvider2);
            }
        } catch (Exception e) {
            wrapAndThrow(serializerProvider2, (Throwable) e, obj2, i == beanPropertyWriterArr.length ? "[anySetter]" : beanPropertyWriterArr[i].getName());
        } catch (StackOverflowError e2) {
            new JsonMappingException("Infinite recursion (StackOverflowError)", (Throwable) e2);
            JsonMappingException jsonMappingException2 = jsonMappingException;
            new JsonMappingException.Reference(obj2, i == beanPropertyWriterArr.length ? "[anySetter]" : beanPropertyWriterArr[i].getName());
            jsonMappingException2.prependPath(reference);
            throw jsonMappingException2;
        }
    }

    /* access modifiers changed from: protected */
    public void serializeFieldsFiltered(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        BeanPropertyWriter[] beanPropertyWriterArr;
        JsonMappingException jsonMappingException;
        JsonMappingException.Reference reference;
        Object obj2 = obj;
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializerProvider serializerProvider2 = serializerProvider;
        if (this._filteredProps == null || serializerProvider2.getActiveView() == null) {
            beanPropertyWriterArr = this._props;
        } else {
            beanPropertyWriterArr = this._filteredProps;
        }
        BeanPropertyFilter findFilter = findFilter(serializerProvider2);
        if (findFilter == null) {
            serializeFields(obj2, jsonGenerator2, serializerProvider2);
            return;
        }
        try {
            int length = beanPropertyWriterArr.length;
            for (int i = 0; i < length; i++) {
                BeanPropertyWriter beanPropertyWriter = beanPropertyWriterArr[i];
                if (beanPropertyWriter != null) {
                    findFilter.serializeAsField(obj2, jsonGenerator2, serializerProvider2, beanPropertyWriter);
                }
            }
            if (this._anyGetterWriter != null) {
                this._anyGetterWriter.getAndSerialize(obj2, jsonGenerator2, serializerProvider2);
            }
        } catch (Exception e) {
            wrapAndThrow(serializerProvider2, (Throwable) e, obj2, 0 == beanPropertyWriterArr.length ? "[anySetter]" : beanPropertyWriterArr[0].getName());
        } catch (StackOverflowError e2) {
            new JsonMappingException("Infinite recursion (StackOverflowError)", (Throwable) e2);
            JsonMappingException jsonMappingException2 = jsonMappingException;
            new JsonMappingException.Reference(obj2, 0 == beanPropertyWriterArr.length ? "[anySetter]" : beanPropertyWriterArr[0].getName());
            jsonMappingException2.prependPath(reference);
            throw jsonMappingException2;
        }
    }

    /* access modifiers changed from: protected */
    public BeanPropertyFilter findFilter(SerializerProvider serializerProvider) throws JsonMappingException {
        Throwable th;
        StringBuilder sb;
        Object obj = this._propertyFilterId;
        FilterProvider filterProvider = serializerProvider.getFilterProvider();
        if (filterProvider != null) {
            return filterProvider.findFilter(obj);
        }
        Throwable th2 = th;
        new StringBuilder();
        new JsonMappingException(sb.append("Can not resolve BeanPropertyFilter with id '").append(obj).append("'; no FilterProvider configured").toString());
        throw th2;
    }

    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) throws JsonMappingException {
        BeanPropertyFilter beanPropertyFilter;
        String id;
        SerializerProvider serializerProvider2 = serializerProvider;
        Type type2 = type;
        ObjectNode createSchemaNode = createSchemaNode("object", true);
        JsonSerializableSchema jsonSerializableSchema = (JsonSerializableSchema) this._handledType.getAnnotation(JsonSerializableSchema.class);
        if (!(jsonSerializableSchema == null || (id = jsonSerializableSchema.mo21840id()) == null || id.length() <= 0)) {
            ObjectNode put = createSchemaNode.put("id", id);
        }
        ObjectNode objectNode = createSchemaNode.objectNode();
        if (this._propertyFilterId != null) {
            beanPropertyFilter = findFilter(serializerProvider2);
        } else {
            beanPropertyFilter = null;
        }
        for (int i = 0; i < this._props.length; i++) {
            BeanPropertyWriter beanPropertyWriter = this._props[i];
            if (beanPropertyFilter == null) {
                beanPropertyWriter.depositSchemaProperty(objectNode, serializerProvider2);
            } else {
                beanPropertyFilter.depositSchemaProperty(beanPropertyWriter, objectNode, serializerProvider2);
            }
        }
        JsonNode put2 = createSchemaNode.put("properties", (JsonNode) objectNode);
        return createSchemaNode;
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        JsonFormatVisitorWrapper jsonFormatVisitorWrapper2 = jsonFormatVisitorWrapper;
        JsonObjectFormatVisitor expectObjectFormat = jsonFormatVisitorWrapper2 == null ? null : jsonFormatVisitorWrapper2.expectObjectFormat(javaType);
        if (expectObjectFormat == null) {
            return;
        }
        if (this._propertyFilterId != null) {
            BeanPropertyFilter findFilter = findFilter(jsonFormatVisitorWrapper2.getProvider());
            for (int i = 0; i < this._props.length; i++) {
                findFilter.depositSchemaProperty(this._props[i], expectObjectFormat, jsonFormatVisitorWrapper2.getProvider());
            }
            return;
        }
        for (int i2 = 0; i2 < this._props.length; i2++) {
            this._props[i2].depositSchemaProperty(expectObjectFormat);
        }
    }
}

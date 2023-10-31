package com.shaded.fasterxml.jackson.databind.deser;

import com.shaded.fasterxml.jackson.annotation.JsonFormat;
import com.shaded.fasterxml.jackson.annotation.JsonTypeInfo;
import com.shaded.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.shaded.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.core.JsonToken;
import com.shaded.fasterxml.jackson.databind.AnnotationIntrospector;
import com.shaded.fasterxml.jackson.databind.BeanProperty;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.DeserializationFeature;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.JsonMappingException;
import com.shaded.fasterxml.jackson.databind.PropertyName;
import com.shaded.fasterxml.jackson.databind.deser.impl.BeanPropertyMap;
import com.shaded.fasterxml.jackson.databind.deser.impl.ExternalTypeHandler;
import com.shaded.fasterxml.jackson.databind.deser.impl.InnerClassProperty;
import com.shaded.fasterxml.jackson.databind.deser.impl.ManagedReferenceProperty;
import com.shaded.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.shaded.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator;
import com.shaded.fasterxml.jackson.databind.deser.impl.PropertyBasedObjectIdGenerator;
import com.shaded.fasterxml.jackson.databind.deser.impl.UnwrappedPropertyHandler;
import com.shaded.fasterxml.jackson.databind.deser.impl.ValueInjector;
import com.shaded.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase;
import com.shaded.fasterxml.jackson.databind.deser.std.StdDelegatingDeserializer;
import com.shaded.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.shaded.fasterxml.jackson.databind.introspect.Annotated;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.shaded.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.shaded.fasterxml.jackson.databind.type.ClassKey;
import com.shaded.fasterxml.jackson.databind.util.Annotations;
import com.shaded.fasterxml.jackson.databind.util.ArrayBuilders;
import com.shaded.fasterxml.jackson.databind.util.ClassUtil;
import com.shaded.fasterxml.jackson.databind.util.Converter;
import com.shaded.fasterxml.jackson.databind.util.NameTransformer;
import com.shaded.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public abstract class BeanDeserializerBase extends StdDeserializer<Object> implements ContextualDeserializer, ResolvableDeserializer, Serializable {
    private static final long serialVersionUID = -2038793552422727904L;
    protected SettableAnyProperty _anySetter;
    protected final Map<String, SettableBeanProperty> _backRefs;
    protected final BeanPropertyMap _beanProperties;
    protected final JavaType _beanType;
    private final transient Annotations _classAnnotations;
    protected JsonDeserializer<Object> _delegateDeserializer;
    protected ExternalTypeHandler _externalTypeIdHandler;
    protected final HashSet<String> _ignorableProps;
    protected final boolean _ignoreAllUnknown;
    protected final ValueInjector[] _injectables;
    protected final boolean _needViewProcesing;
    protected boolean _nonStandardCreation;
    protected final ObjectIdReader _objectIdReader;
    protected PropertyBasedCreator _propertyBasedCreator;
    protected final JsonFormat.Shape _serializationShape;
    protected transient HashMap<ClassKey, JsonDeserializer<Object>> _subDeserializers;
    protected UnwrappedPropertyHandler _unwrappedPropertyHandler;
    protected final ValueInstantiator _valueInstantiator;
    protected boolean _vanillaProcessing;

    /* access modifiers changed from: protected */
    public abstract Object _deserializeUsingPropertyBased(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException;

    /* access modifiers changed from: protected */
    public abstract BeanDeserializerBase asArrayDeserializer();

    public abstract Object deserializeFromObject(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException;

    public abstract JsonDeserializer<Object> unwrappingDeserializer(NameTransformer nameTransformer);

    public abstract BeanDeserializerBase withIgnorableProperties(HashSet<String> hashSet);

    public abstract BeanDeserializerBase withObjectIdReader(ObjectIdReader objectIdReader);

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected BeanDeserializerBase(com.shaded.fasterxml.jackson.databind.deser.BeanDeserializerBuilder r15, com.shaded.fasterxml.jackson.databind.BeanDescription r16, com.shaded.fasterxml.jackson.databind.deser.impl.BeanPropertyMap r17, java.util.Map<java.lang.String, com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty> r18, java.util.HashSet<java.lang.String> r19, boolean r20, boolean r21) {
        /*
            r14 = this;
            r0 = r14
            r1 = r15
            r2 = r16
            r3 = r17
            r4 = r18
            r5 = r19
            r6 = r20
            r7 = r21
            r11 = r0
            r12 = r2
            com.shaded.fasterxml.jackson.databind.JavaType r12 = r12.getType()
            r11.<init>((com.shaded.fasterxml.jackson.databind.JavaType) r12)
            r11 = r2
            com.shaded.fasterxml.jackson.databind.introspect.AnnotatedClass r11 = r11.getClassInfo()
            r8 = r11
            r11 = r0
            r12 = r8
            com.shaded.fasterxml.jackson.databind.util.Annotations r12 = r12.getAnnotations()
            r11._classAnnotations = r12
            r11 = r0
            r12 = r2
            com.shaded.fasterxml.jackson.databind.JavaType r12 = r12.getType()
            r11._beanType = r12
            r11 = r0
            r12 = r1
            com.shaded.fasterxml.jackson.databind.deser.ValueInstantiator r12 = r12.getValueInstantiator()
            r11._valueInstantiator = r12
            r11 = r0
            r12 = r3
            r11._beanProperties = r12
            r11 = r0
            r12 = r4
            r11._backRefs = r12
            r11 = r0
            r12 = r5
            r11._ignorableProps = r12
            r11 = r0
            r12 = r6
            r11._ignoreAllUnknown = r12
            r11 = r0
            r12 = r1
            com.shaded.fasterxml.jackson.databind.deser.SettableAnyProperty r12 = r12.getAnySetter()
            r11._anySetter = r12
            r11 = r1
            java.util.List r11 = r11.getInjectables()
            r9 = r11
            r11 = r0
            r12 = r9
            if (r12 == 0) goto L_0x005e
            r12 = r9
            boolean r12 = r12.isEmpty()
            if (r12 == 0) goto L_0x00b8
        L_0x005e:
            r12 = 0
        L_0x005f:
            r11._injectables = r12
            r11 = r0
            r12 = r1
            com.shaded.fasterxml.jackson.databind.deser.impl.ObjectIdReader r12 = r12.getObjectIdReader()
            r11._objectIdReader = r12
            r11 = r0
            r12 = r0
            com.shaded.fasterxml.jackson.databind.deser.impl.UnwrappedPropertyHandler r12 = r12._unwrappedPropertyHandler
            if (r12 != 0) goto L_0x008a
            r12 = r0
            com.shaded.fasterxml.jackson.databind.deser.ValueInstantiator r12 = r12._valueInstantiator
            boolean r12 = r12.canCreateUsingDelegate()
            if (r12 != 0) goto L_0x008a
            r12 = r0
            com.shaded.fasterxml.jackson.databind.deser.ValueInstantiator r12 = r12._valueInstantiator
            boolean r12 = r12.canCreateFromObjectWith()
            if (r12 != 0) goto L_0x008a
            r12 = r0
            com.shaded.fasterxml.jackson.databind.deser.ValueInstantiator r12 = r12._valueInstantiator
            boolean r12 = r12.canCreateUsingDefault()
            if (r12 != 0) goto L_0x00c7
        L_0x008a:
            r12 = 1
        L_0x008b:
            r11._nonStandardCreation = r12
            r11 = r2
            r12 = 0
            com.shaded.fasterxml.jackson.annotation.JsonFormat$Value r11 = r11.findExpectedFormat(r12)
            r10 = r11
            r11 = r0
            r12 = r10
            if (r12 != 0) goto L_0x00c9
            r12 = 0
        L_0x0099:
            r11._serializationShape = r12
            r11 = r0
            r12 = r7
            r11._needViewProcesing = r12
            r11 = r0
            r12 = r0
            boolean r12 = r12._nonStandardCreation
            if (r12 != 0) goto L_0x00cf
            r12 = r0
            com.shaded.fasterxml.jackson.databind.deser.impl.ValueInjector[] r12 = r12._injectables
            if (r12 != 0) goto L_0x00cf
            r12 = r0
            boolean r12 = r12._needViewProcesing
            if (r12 != 0) goto L_0x00cf
            r12 = r0
            com.shaded.fasterxml.jackson.databind.deser.impl.ObjectIdReader r12 = r12._objectIdReader
            if (r12 == 0) goto L_0x00cf
            r12 = 1
        L_0x00b5:
            r11._vanillaProcessing = r12
            return
        L_0x00b8:
            r12 = r9
            r13 = r9
            int r13 = r13.size()
            com.shaded.fasterxml.jackson.databind.deser.impl.ValueInjector[] r13 = new com.shaded.fasterxml.jackson.databind.deser.impl.ValueInjector[r13]
            java.lang.Object[] r12 = r12.toArray(r13)
            com.shaded.fasterxml.jackson.databind.deser.impl.ValueInjector[] r12 = (com.shaded.fasterxml.jackson.databind.deser.impl.ValueInjector[]) r12
            goto L_0x005f
        L_0x00c7:
            r12 = 0
            goto L_0x008b
        L_0x00c9:
            r12 = r10
            com.shaded.fasterxml.jackson.annotation.JsonFormat$Shape r12 = r12.getShape()
            goto L_0x0099
        L_0x00cf:
            r12 = 0
            goto L_0x00b5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.BeanDeserializerBase.<init>(com.shaded.fasterxml.jackson.databind.deser.BeanDeserializerBuilder, com.shaded.fasterxml.jackson.databind.BeanDescription, com.shaded.fasterxml.jackson.databind.deser.impl.BeanPropertyMap, java.util.Map, java.util.HashSet, boolean, boolean):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected BeanDeserializerBase(com.shaded.fasterxml.jackson.databind.deser.BeanDeserializerBase r6) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r2 = r0
            r3 = r1
            r4 = r1
            boolean r4 = r4._ignoreAllUnknown
            r2.<init>((com.shaded.fasterxml.jackson.databind.deser.BeanDeserializerBase) r3, (boolean) r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.BeanDeserializerBase.<init>(com.shaded.fasterxml.jackson.databind.deser.BeanDeserializerBase):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected BeanDeserializerBase(com.shaded.fasterxml.jackson.databind.deser.BeanDeserializerBase r6, boolean r7) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r2 = r7
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.JavaType r4 = r4._beanType
            r3.<init>((com.shaded.fasterxml.jackson.databind.JavaType) r4)
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.util.Annotations r4 = r4._classAnnotations
            r3._classAnnotations = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.JavaType r4 = r4._beanType
            r3._beanType = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.deser.ValueInstantiator r4 = r4._valueInstantiator
            r3._valueInstantiator = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object> r4 = r4._delegateDeserializer
            r3._delegateDeserializer = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator r4 = r4._propertyBasedCreator
            r3._propertyBasedCreator = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.deser.impl.BeanPropertyMap r4 = r4._beanProperties
            r3._beanProperties = r4
            r3 = r0
            r4 = r1
            java.util.Map<java.lang.String, com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty> r4 = r4._backRefs
            r3._backRefs = r4
            r3 = r0
            r4 = r1
            java.util.HashSet<java.lang.String> r4 = r4._ignorableProps
            r3._ignorableProps = r4
            r3 = r0
            r4 = r2
            r3._ignoreAllUnknown = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.deser.SettableAnyProperty r4 = r4._anySetter
            r3._anySetter = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.deser.impl.ValueInjector[] r4 = r4._injectables
            r3._injectables = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.deser.impl.ObjectIdReader r4 = r4._objectIdReader
            r3._objectIdReader = r4
            r3 = r0
            r4 = r1
            boolean r4 = r4._nonStandardCreation
            r3._nonStandardCreation = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.deser.impl.UnwrappedPropertyHandler r4 = r4._unwrappedPropertyHandler
            r3._unwrappedPropertyHandler = r4
            r3 = r0
            r4 = r1
            boolean r4 = r4._needViewProcesing
            r3._needViewProcesing = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.annotation.JsonFormat$Shape r4 = r4._serializationShape
            r3._serializationShape = r4
            r3 = r0
            r4 = r1
            boolean r4 = r4._vanillaProcessing
            r3._vanillaProcessing = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.BeanDeserializerBase.<init>(com.shaded.fasterxml.jackson.databind.deser.BeanDeserializerBase, boolean):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected BeanDeserializerBase(com.shaded.fasterxml.jackson.databind.deser.BeanDeserializerBase r8, com.shaded.fasterxml.jackson.databind.util.NameTransformer r9) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r9
            r4 = r0
            r5 = r1
            com.shaded.fasterxml.jackson.databind.JavaType r5 = r5._beanType
            r4.<init>((com.shaded.fasterxml.jackson.databind.JavaType) r5)
            r4 = r0
            r5 = r1
            com.shaded.fasterxml.jackson.databind.util.Annotations r5 = r5._classAnnotations
            r4._classAnnotations = r5
            r4 = r0
            r5 = r1
            com.shaded.fasterxml.jackson.databind.JavaType r5 = r5._beanType
            r4._beanType = r5
            r4 = r0
            r5 = r1
            com.shaded.fasterxml.jackson.databind.deser.ValueInstantiator r5 = r5._valueInstantiator
            r4._valueInstantiator = r5
            r4 = r0
            r5 = r1
            com.shaded.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object> r5 = r5._delegateDeserializer
            r4._delegateDeserializer = r5
            r4 = r0
            r5 = r1
            com.shaded.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator r5 = r5._propertyBasedCreator
            r4._propertyBasedCreator = r5
            r4 = r0
            r5 = r1
            java.util.Map<java.lang.String, com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty> r5 = r5._backRefs
            r4._backRefs = r5
            r4 = r0
            r5 = r1
            java.util.HashSet<java.lang.String> r5 = r5._ignorableProps
            r4._ignorableProps = r5
            r4 = r0
            r5 = r2
            if (r5 != 0) goto L_0x003d
            r5 = r1
            boolean r5 = r5._ignoreAllUnknown
            if (r5 == 0) goto L_0x0089
        L_0x003d:
            r5 = 1
        L_0x003e:
            r4._ignoreAllUnknown = r5
            r4 = r0
            r5 = r1
            com.shaded.fasterxml.jackson.databind.deser.SettableAnyProperty r5 = r5._anySetter
            r4._anySetter = r5
            r4 = r0
            r5 = r1
            com.shaded.fasterxml.jackson.databind.deser.impl.ValueInjector[] r5 = r5._injectables
            r4._injectables = r5
            r4 = r0
            r5 = r1
            com.shaded.fasterxml.jackson.databind.deser.impl.ObjectIdReader r5 = r5._objectIdReader
            r4._objectIdReader = r5
            r4 = r0
            r5 = r1
            boolean r5 = r5._nonStandardCreation
            r4._nonStandardCreation = r5
            r4 = r1
            com.shaded.fasterxml.jackson.databind.deser.impl.UnwrappedPropertyHandler r4 = r4._unwrappedPropertyHandler
            r3 = r4
            r4 = r2
            if (r4 == 0) goto L_0x008b
            r4 = r3
            if (r4 == 0) goto L_0x0069
            r4 = r3
            r5 = r2
            com.shaded.fasterxml.jackson.databind.deser.impl.UnwrappedPropertyHandler r4 = r4.renameAll(r5)
            r3 = r4
        L_0x0069:
            r4 = r0
            r5 = r1
            com.shaded.fasterxml.jackson.databind.deser.impl.BeanPropertyMap r5 = r5._beanProperties
            r6 = r2
            com.shaded.fasterxml.jackson.databind.deser.impl.BeanPropertyMap r5 = r5.renameAll(r6)
            r4._beanProperties = r5
        L_0x0074:
            r4 = r0
            r5 = r3
            r4._unwrappedPropertyHandler = r5
            r4 = r0
            r5 = r1
            boolean r5 = r5._needViewProcesing
            r4._needViewProcesing = r5
            r4 = r0
            r5 = r1
            com.shaded.fasterxml.jackson.annotation.JsonFormat$Shape r5 = r5._serializationShape
            r4._serializationShape = r5
            r4 = r0
            r5 = 0
            r4._vanillaProcessing = r5
            return
        L_0x0089:
            r5 = 0
            goto L_0x003e
        L_0x008b:
            r4 = r0
            r5 = r1
            com.shaded.fasterxml.jackson.databind.deser.impl.BeanPropertyMap r5 = r5._beanProperties
            r4._beanProperties = r5
            goto L_0x0074
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.BeanDeserializerBase.<init>(com.shaded.fasterxml.jackson.databind.deser.BeanDeserializerBase, com.shaded.fasterxml.jackson.databind.util.NameTransformer):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BeanDeserializerBase(com.shaded.fasterxml.jackson.databind.deser.BeanDeserializerBase r10, com.shaded.fasterxml.jackson.databind.deser.impl.ObjectIdReader r11) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r2 = r11
            r4 = r0
            r5 = r1
            com.shaded.fasterxml.jackson.databind.JavaType r5 = r5._beanType
            r4.<init>((com.shaded.fasterxml.jackson.databind.JavaType) r5)
            r4 = r0
            r5 = r1
            com.shaded.fasterxml.jackson.databind.util.Annotations r5 = r5._classAnnotations
            r4._classAnnotations = r5
            r4 = r0
            r5 = r1
            com.shaded.fasterxml.jackson.databind.JavaType r5 = r5._beanType
            r4._beanType = r5
            r4 = r0
            r5 = r1
            com.shaded.fasterxml.jackson.databind.deser.ValueInstantiator r5 = r5._valueInstantiator
            r4._valueInstantiator = r5
            r4 = r0
            r5 = r1
            com.shaded.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object> r5 = r5._delegateDeserializer
            r4._delegateDeserializer = r5
            r4 = r0
            r5 = r1
            com.shaded.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator r5 = r5._propertyBasedCreator
            r4._propertyBasedCreator = r5
            r4 = r0
            r5 = r1
            java.util.Map<java.lang.String, com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty> r5 = r5._backRefs
            r4._backRefs = r5
            r4 = r0
            r5 = r1
            java.util.HashSet<java.lang.String> r5 = r5._ignorableProps
            r4._ignorableProps = r5
            r4 = r0
            r5 = r1
            boolean r5 = r5._ignoreAllUnknown
            r4._ignoreAllUnknown = r5
            r4 = r0
            r5 = r1
            com.shaded.fasterxml.jackson.databind.deser.SettableAnyProperty r5 = r5._anySetter
            r4._anySetter = r5
            r4 = r0
            r5 = r1
            com.shaded.fasterxml.jackson.databind.deser.impl.ValueInjector[] r5 = r5._injectables
            r4._injectables = r5
            r4 = r0
            r5 = r1
            boolean r5 = r5._nonStandardCreation
            r4._nonStandardCreation = r5
            r4 = r0
            r5 = r1
            com.shaded.fasterxml.jackson.databind.deser.impl.UnwrappedPropertyHandler r5 = r5._unwrappedPropertyHandler
            r4._unwrappedPropertyHandler = r5
            r4 = r0
            r5 = r1
            boolean r5 = r5._needViewProcesing
            r4._needViewProcesing = r5
            r4 = r0
            r5 = r1
            com.shaded.fasterxml.jackson.annotation.JsonFormat$Shape r5 = r5._serializationShape
            r4._serializationShape = r5
            r4 = r0
            r5 = r1
            boolean r5 = r5._vanillaProcessing
            r4._vanillaProcessing = r5
            r4 = r0
            r5 = r2
            r4._objectIdReader = r5
            r4 = r2
            if (r4 != 0) goto L_0x0072
            r4 = r0
            r5 = r1
            com.shaded.fasterxml.jackson.databind.deser.impl.BeanPropertyMap r5 = r5._beanProperties
            r4._beanProperties = r5
        L_0x0071:
            return
        L_0x0072:
            com.shaded.fasterxml.jackson.databind.deser.impl.ObjectIdValueProperty r4 = new com.shaded.fasterxml.jackson.databind.deser.impl.ObjectIdValueProperty
            r8 = r4
            r4 = r8
            r5 = r8
            r6 = r2
            r7 = 1
            r5.<init>((com.shaded.fasterxml.jackson.databind.deser.impl.ObjectIdReader) r6, (boolean) r7)
            r3 = r4
            r4 = r0
            r5 = r1
            com.shaded.fasterxml.jackson.databind.deser.impl.BeanPropertyMap r5 = r5._beanProperties
            r6 = r3
            com.shaded.fasterxml.jackson.databind.deser.impl.BeanPropertyMap r5 = r5.withProperty(r6)
            r4._beanProperties = r5
            goto L_0x0071
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.BeanDeserializerBase.<init>(com.shaded.fasterxml.jackson.databind.deser.BeanDeserializerBase, com.shaded.fasterxml.jackson.databind.deser.impl.ObjectIdReader):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BeanDeserializerBase(com.shaded.fasterxml.jackson.databind.deser.BeanDeserializerBase r6, java.util.HashSet<java.lang.String> r7) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r2 = r7
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.JavaType r4 = r4._beanType
            r3.<init>((com.shaded.fasterxml.jackson.databind.JavaType) r4)
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.util.Annotations r4 = r4._classAnnotations
            r3._classAnnotations = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.JavaType r4 = r4._beanType
            r3._beanType = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.deser.ValueInstantiator r4 = r4._valueInstantiator
            r3._valueInstantiator = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object> r4 = r4._delegateDeserializer
            r3._delegateDeserializer = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator r4 = r4._propertyBasedCreator
            r3._propertyBasedCreator = r4
            r3 = r0
            r4 = r1
            java.util.Map<java.lang.String, com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty> r4 = r4._backRefs
            r3._backRefs = r4
            r3 = r0
            r4 = r2
            r3._ignorableProps = r4
            r3 = r0
            r4 = r1
            boolean r4 = r4._ignoreAllUnknown
            r3._ignoreAllUnknown = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.deser.SettableAnyProperty r4 = r4._anySetter
            r3._anySetter = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.deser.impl.ValueInjector[] r4 = r4._injectables
            r3._injectables = r4
            r3 = r0
            r4 = r1
            boolean r4 = r4._nonStandardCreation
            r3._nonStandardCreation = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.deser.impl.UnwrappedPropertyHandler r4 = r4._unwrappedPropertyHandler
            r3._unwrappedPropertyHandler = r4
            r3 = r0
            r4 = r1
            boolean r4 = r4._needViewProcesing
            r3._needViewProcesing = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.annotation.JsonFormat$Shape r4 = r4._serializationShape
            r3._serializationShape = r4
            r3 = r0
            r4 = r1
            boolean r4 = r4._vanillaProcessing
            r3._vanillaProcessing = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.deser.impl.ObjectIdReader r4 = r4._objectIdReader
            r3._objectIdReader = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.deser.impl.BeanPropertyMap r4 = r4._beanProperties
            r3._beanProperties = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.BeanDeserializerBase.<init>(com.shaded.fasterxml.jackson.databind.deser.BeanDeserializerBase, java.util.HashSet):void");
    }

    public void resolve(DeserializationContext deserializationContext) throws JsonMappingException {
        BeanProperty beanProperty;
        Throwable th;
        StringBuilder sb;
        JsonDeserializer<?> createContextual;
        ExternalTypeHandler.Builder builder;
        UnwrappedPropertyHandler unwrappedPropertyHandler;
        ExternalTypeHandler.Builder builder2;
        DeserializationContext deserializationContext2 = deserializationContext;
        ExternalTypeHandler.Builder builder3 = null;
        if (this._valueInstantiator.canCreateFromObjectWith()) {
            this._propertyBasedCreator = PropertyBasedCreator.construct(deserializationContext2, this._valueInstantiator, this._valueInstantiator.getFromObjectArguments(deserializationContext2.getConfig()));
            for (SettableBeanProperty next : this._propertyBasedCreator.properties()) {
                if (next.hasValueTypeDeserializer()) {
                    TypeDeserializer valueTypeDeserializer = next.getValueTypeDeserializer();
                    if (valueTypeDeserializer.getTypeInclusion() == JsonTypeInfo.C1433As.EXTERNAL_PROPERTY) {
                        if (builder3 == null) {
                            new ExternalTypeHandler.Builder();
                            builder3 = builder2;
                        }
                        builder3.addExternal(next, valueTypeDeserializer);
                    }
                }
            }
        }
        UnwrappedPropertyHandler unwrappedPropertyHandler2 = null;
        Iterator<SettableBeanProperty> it = this._beanProperties.iterator();
        while (it.hasNext()) {
            SettableBeanProperty next2 = it.next();
            SettableBeanProperty settableBeanProperty = next2;
            if (!settableBeanProperty.hasValueDeserializer()) {
                JsonDeserializer<Object> findConvertingDeserializer = findConvertingDeserializer(deserializationContext2, settableBeanProperty);
                if (findConvertingDeserializer == null) {
                    findConvertingDeserializer = findDeserializer(deserializationContext2, settableBeanProperty.getType(), settableBeanProperty);
                }
                settableBeanProperty = settableBeanProperty.withValueDeserializer(findConvertingDeserializer);
            } else {
                JsonDeserializer<Object> valueDeserializer = settableBeanProperty.getValueDeserializer();
                if ((valueDeserializer instanceof ContextualDeserializer) && (createContextual = ((ContextualDeserializer) valueDeserializer).createContextual(deserializationContext2, settableBeanProperty)) != valueDeserializer) {
                    settableBeanProperty = settableBeanProperty.withValueDeserializer(createContextual);
                }
            }
            SettableBeanProperty _resolveManagedReferenceProperty = _resolveManagedReferenceProperty(deserializationContext2, settableBeanProperty);
            SettableBeanProperty _resolveUnwrappedProperty = _resolveUnwrappedProperty(deserializationContext2, _resolveManagedReferenceProperty);
            if (_resolveUnwrappedProperty != null) {
                SettableBeanProperty settableBeanProperty2 = _resolveUnwrappedProperty;
                if (unwrappedPropertyHandler2 == null) {
                    new UnwrappedPropertyHandler();
                    unwrappedPropertyHandler2 = unwrappedPropertyHandler;
                }
                unwrappedPropertyHandler2.addProperty(settableBeanProperty2);
            } else {
                SettableBeanProperty _resolveInnerClassValuedProperty = _resolveInnerClassValuedProperty(deserializationContext2, _resolveManagedReferenceProperty);
                if (_resolveInnerClassValuedProperty != next2) {
                    this._beanProperties.replace(_resolveInnerClassValuedProperty);
                }
                if (_resolveInnerClassValuedProperty.hasValueTypeDeserializer()) {
                    TypeDeserializer valueTypeDeserializer2 = _resolveInnerClassValuedProperty.getValueTypeDeserializer();
                    if (valueTypeDeserializer2.getTypeInclusion() == JsonTypeInfo.C1433As.EXTERNAL_PROPERTY) {
                        if (builder3 == null) {
                            new ExternalTypeHandler.Builder();
                            builder3 = builder;
                        }
                        builder3.addExternal(_resolveInnerClassValuedProperty, valueTypeDeserializer2);
                        this._beanProperties.remove(_resolveInnerClassValuedProperty);
                    }
                }
            }
        }
        if (this._anySetter != null && !this._anySetter.hasValueDeserializer()) {
            this._anySetter = this._anySetter.withValueDeserializer(findDeserializer(deserializationContext2, this._anySetter.getType(), this._anySetter.getProperty()));
        }
        if (this._valueInstantiator.canCreateUsingDelegate()) {
            JavaType delegateType = this._valueInstantiator.getDelegateType(deserializationContext2.getConfig());
            if (delegateType == null) {
                Throwable th2 = th;
                new StringBuilder();
                new IllegalArgumentException(sb.append("Invalid delegate-creator definition for ").append(this._beanType).append(": value instantiator (").append(this._valueInstantiator.getClass().getName()).append(") returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'").toString());
                throw th2;
            }
            new BeanProperty.Std((String) null, delegateType, (PropertyName) null, this._classAnnotations, this._valueInstantiator.getDelegateCreator(), false);
            this._delegateDeserializer = findDeserializer(deserializationContext2, delegateType, beanProperty);
        }
        if (builder3 != null) {
            this._externalTypeIdHandler = builder3.build();
            this._nonStandardCreation = true;
        }
        this._unwrappedPropertyHandler = unwrappedPropertyHandler2;
        if (unwrappedPropertyHandler2 != null) {
            this._nonStandardCreation = true;
        }
        this._vanillaProcessing = this._vanillaProcessing && !this._nonStandardCreation;
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<Object> findConvertingDeserializer(DeserializationContext deserializationContext, SettableBeanProperty settableBeanProperty) throws JsonMappingException {
        Object findDeserializationConverter;
        JsonDeserializer<Object> jsonDeserializer;
        DeserializationContext deserializationContext2 = deserializationContext;
        SettableBeanProperty settableBeanProperty2 = settableBeanProperty;
        AnnotationIntrospector annotationIntrospector = deserializationContext2.getAnnotationIntrospector();
        if (annotationIntrospector == null || (findDeserializationConverter = annotationIntrospector.findDeserializationConverter(settableBeanProperty2.getMember())) == null) {
            return null;
        }
        Converter<Object, Object> converterInstance = deserializationContext2.converterInstance(settableBeanProperty2.getMember(), findDeserializationConverter);
        JavaType inputType = converterInstance.getInputType(deserializationContext2.getTypeFactory());
        new StdDelegatingDeserializer(converterInstance, inputType, deserializationContext2.findContextualValueDeserializer(inputType, settableBeanProperty2));
        return jsonDeserializer;
    }

    public JsonDeserializer<?> createContextual(DeserializationContext deserializationContext, BeanProperty beanProperty) throws JsonMappingException {
        AnnotatedMember annotatedMember;
        JsonFormat.Value findFormat;
        JavaType javaType;
        SettableBeanProperty settableBeanProperty;
        ObjectIdGenerator<?> objectIdGeneratorInstance;
        ObjectIdGenerator<?> objectIdGenerator;
        Throwable th;
        StringBuilder sb;
        DeserializationContext deserializationContext2 = deserializationContext;
        BeanProperty beanProperty2 = beanProperty;
        ObjectIdReader objectIdReader = this._objectIdReader;
        String[] strArr = null;
        AnnotationIntrospector annotationIntrospector = deserializationContext2.getAnnotationIntrospector();
        if (beanProperty2 == null || annotationIntrospector == null) {
            annotatedMember = null;
        } else {
            annotatedMember = beanProperty2.getMember();
        }
        AnnotatedMember annotatedMember2 = annotatedMember;
        if (!(beanProperty2 == null || annotationIntrospector == null)) {
            strArr = annotationIntrospector.findPropertiesToIgnore(annotatedMember2);
            ObjectIdInfo findObjectIdInfo = annotationIntrospector.findObjectIdInfo(annotatedMember2);
            if (findObjectIdInfo != null) {
                ObjectIdInfo findObjectReferenceInfo = annotationIntrospector.findObjectReferenceInfo(annotatedMember2, findObjectIdInfo);
                Class<? extends ObjectIdGenerator<?>> generatorType = findObjectReferenceInfo.getGeneratorType();
                if (generatorType == ObjectIdGenerators.PropertyGenerator.class) {
                    String propertyName = findObjectReferenceInfo.getPropertyName();
                    settableBeanProperty = findProperty(propertyName);
                    if (settableBeanProperty == null) {
                        Throwable th2 = th;
                        new StringBuilder();
                        new IllegalArgumentException(sb.append("Invalid Object Id definition for ").append(getBeanClass().getName()).append(": can not find property with name '").append(propertyName).append("'").toString());
                        throw th2;
                    }
                    javaType = settableBeanProperty.getType();
                    new PropertyBasedObjectIdGenerator(findObjectReferenceInfo.getScope());
                    objectIdGeneratorInstance = objectIdGenerator;
                } else {
                    javaType = deserializationContext2.getTypeFactory().findTypeParameters(deserializationContext2.constructType(generatorType), (Class<?>) ObjectIdGenerator.class)[0];
                    settableBeanProperty = null;
                    objectIdGeneratorInstance = deserializationContext2.objectIdGeneratorInstance(annotatedMember2, findObjectReferenceInfo);
                }
                objectIdReader = ObjectIdReader.construct(javaType, findObjectReferenceInfo.getPropertyName(), objectIdGeneratorInstance, deserializationContext2.findRootValueDeserializer(javaType), settableBeanProperty);
            }
        }
        BeanDeserializerBase beanDeserializerBase = this;
        if (!(objectIdReader == null || objectIdReader == this._objectIdReader)) {
            beanDeserializerBase = beanDeserializerBase.withObjectIdReader(objectIdReader);
        }
        if (!(strArr == null || strArr.length == 0)) {
            beanDeserializerBase = beanDeserializerBase.withIgnorableProperties(ArrayBuilders.setAndArray(beanDeserializerBase._ignorableProps, strArr));
        }
        JsonFormat.Shape shape = null;
        if (!(annotatedMember2 == null || (findFormat = annotationIntrospector.findFormat((Annotated) annotatedMember2)) == null)) {
            shape = findFormat.getShape();
        }
        if (shape == null) {
            shape = this._serializationShape;
        }
        if (shape == JsonFormat.Shape.ARRAY) {
            beanDeserializerBase = beanDeserializerBase.asArrayDeserializer();
        }
        return beanDeserializerBase;
    }

    /* access modifiers changed from: protected */
    public SettableBeanProperty _resolveManagedReferenceProperty(DeserializationContext deserializationContext, SettableBeanProperty settableBeanProperty) {
        Throwable th;
        StringBuilder sb;
        SettableBeanProperty findBackReference;
        Throwable th2;
        StringBuilder sb2;
        SettableBeanProperty settableBeanProperty2;
        Throwable th3;
        StringBuilder sb3;
        Throwable th4;
        StringBuilder sb4;
        DeserializationContext deserializationContext2 = deserializationContext;
        SettableBeanProperty settableBeanProperty3 = settableBeanProperty;
        String managedReferenceName = settableBeanProperty3.getManagedReferenceName();
        if (managedReferenceName == null) {
            return settableBeanProperty3;
        }
        JsonDeserializer<Object> valueDeserializer = settableBeanProperty3.getValueDeserializer();
        boolean z = false;
        if (valueDeserializer instanceof BeanDeserializerBase) {
            findBackReference = ((BeanDeserializerBase) valueDeserializer).findBackReference(managedReferenceName);
        } else if (valueDeserializer instanceof ContainerDeserializerBase) {
            JsonDeserializer<Object> contentDeserializer = ((ContainerDeserializerBase) valueDeserializer).getContentDeserializer();
            if (!(contentDeserializer instanceof BeanDeserializerBase)) {
                String name = contentDeserializer == null ? "NULL" : contentDeserializer.getClass().getName();
                Throwable th5 = th2;
                new StringBuilder();
                new IllegalArgumentException(sb2.append("Can not handle managed/back reference '").append(managedReferenceName).append("': value deserializer is of type ContainerDeserializerBase, but content type is not handled by a BeanDeserializer ").append(" (instead it's of type ").append(name).append(")").toString());
                throw th5;
            }
            findBackReference = ((BeanDeserializerBase) contentDeserializer).findBackReference(managedReferenceName);
            z = true;
        } else if (valueDeserializer instanceof AbstractDeserializer) {
            findBackReference = ((AbstractDeserializer) valueDeserializer).findBackReference(managedReferenceName);
        } else {
            Throwable th6 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Can not handle managed/back reference '").append(managedReferenceName).append("': type for value deserializer is not BeanDeserializer or ContainerDeserializerBase, but ").append(valueDeserializer.getClass().getName()).toString());
            throw th6;
        }
        if (findBackReference == null) {
            Throwable th7 = th4;
            new StringBuilder();
            new IllegalArgumentException(sb4.append("Can not handle managed/back reference '").append(managedReferenceName).append("': no back reference property found from type ").append(settableBeanProperty3.getType()).toString());
            throw th7;
        }
        JavaType javaType = this._beanType;
        JavaType type = findBackReference.getType();
        if (!type.getRawClass().isAssignableFrom(javaType.getRawClass())) {
            Throwable th8 = th3;
            new StringBuilder();
            new IllegalArgumentException(sb3.append("Can not handle managed/back reference '").append(managedReferenceName).append("': back reference type (").append(type.getRawClass().getName()).append(") not compatible with managed type (").append(javaType.getRawClass().getName()).append(")").toString());
            throw th8;
        }
        new ManagedReferenceProperty(settableBeanProperty3, managedReferenceName, findBackReference, this._classAnnotations, z);
        return settableBeanProperty2;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x001a, code lost:
        r5 = r2.getValueDeserializer();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty _resolveUnwrappedProperty(com.shaded.fasterxml.jackson.databind.DeserializationContext r10, com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty r11) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r2 = r11
            r7 = r2
            com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMember r7 = r7.getMember()
            r3 = r7
            r7 = r3
            if (r7 == 0) goto L_0x0036
            r7 = r1
            com.shaded.fasterxml.jackson.databind.AnnotationIntrospector r7 = r7.getAnnotationIntrospector()
            r8 = r3
            com.shaded.fasterxml.jackson.databind.util.NameTransformer r7 = r7.findUnwrappingNameTransformer(r8)
            r4 = r7
            r7 = r4
            if (r7 == 0) goto L_0x0036
            r7 = r2
            com.shaded.fasterxml.jackson.databind.JsonDeserializer r7 = r7.getValueDeserializer()
            r5 = r7
            r7 = r5
            r8 = r4
            com.shaded.fasterxml.jackson.databind.JsonDeserializer r7 = r7.unwrappingDeserializer(r8)
            r6 = r7
            r7 = r6
            r8 = r5
            if (r7 == r8) goto L_0x0036
            r7 = r6
            if (r7 == 0) goto L_0x0036
            r7 = r2
            r8 = r6
            com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty r7 = r7.withValueDeserializer(r8)
            r0 = r7
        L_0x0035:
            return r0
        L_0x0036:
            r7 = 0
            r0 = r7
            goto L_0x0035
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.BeanDeserializerBase._resolveUnwrappedProperty(com.shaded.fasterxml.jackson.databind.DeserializationContext, com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty):com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty");
    }

    /* access modifiers changed from: protected */
    public SettableBeanProperty _resolveInnerClassValuedProperty(DeserializationContext deserializationContext, SettableBeanProperty settableBeanProperty) {
        Class<?> rawClass;
        Class<?> outerClass;
        SettableBeanProperty settableBeanProperty2;
        DeserializationContext deserializationContext2 = deserializationContext;
        SettableBeanProperty settableBeanProperty3 = settableBeanProperty;
        JsonDeserializer<Object> valueDeserializer = settableBeanProperty3.getValueDeserializer();
        if ((valueDeserializer instanceof BeanDeserializerBase) && !((BeanDeserializerBase) valueDeserializer).getValueInstantiator().canCreateUsingDefault() && (outerClass = ClassUtil.getOuterClass(rawClass)) != null && outerClass == this._beanType.getRawClass()) {
            Constructor[] constructors = (rawClass = settableBeanProperty3.getType().getRawClass()).getConstructors();
            int length = constructors.length;
            for (int i = 0; i < length; i++) {
                Constructor constructor = constructors[i];
                Class<?>[] parameterTypes = constructor.getParameterTypes();
                if (parameterTypes.length == 1 && parameterTypes[0] == outerClass) {
                    if (deserializationContext2.getConfig().canOverrideAccessModifiers()) {
                        ClassUtil.checkAndFixAccess(constructor);
                    }
                    new InnerClassProperty(settableBeanProperty3, (Constructor<?>) constructor);
                    return settableBeanProperty2;
                }
            }
        }
        return settableBeanProperty3;
    }

    public boolean isCachable() {
        return true;
    }

    public ObjectIdReader getObjectIdReader() {
        return this._objectIdReader;
    }

    public boolean hasProperty(String str) {
        return this._beanProperties.find(str) != null;
    }

    public boolean hasViews() {
        return this._needViewProcesing;
    }

    public int getPropertyCount() {
        return this._beanProperties.size();
    }

    public Collection<Object> getKnownPropertyNames() {
        ArrayList arrayList;
        new ArrayList();
        ArrayList arrayList2 = arrayList;
        Iterator<SettableBeanProperty> it = this._beanProperties.iterator();
        while (it.hasNext()) {
            boolean add = arrayList2.add(it.next().getName());
        }
        return arrayList2;
    }

    public final Class<?> getBeanClass() {
        return this._beanType.getRawClass();
    }

    public JavaType getValueType() {
        return this._beanType;
    }

    public Iterator<SettableBeanProperty> properties() {
        Throwable th;
        if (this._beanProperties != null) {
            return this._beanProperties.iterator();
        }
        Throwable th2 = th;
        new IllegalStateException("Can only call after BeanDeserializer has been resolved");
        throw th2;
    }

    public Iterator<SettableBeanProperty> creatorProperties() {
        if (this._propertyBasedCreator == null) {
            return Collections.emptyList().iterator();
        }
        return this._propertyBasedCreator.properties().iterator();
    }

    public SettableBeanProperty findProperty(String str) {
        String str2 = str;
        SettableBeanProperty find = this._beanProperties == null ? null : this._beanProperties.find(str2);
        if (find == null && this._propertyBasedCreator != null) {
            find = this._propertyBasedCreator.findCreatorProperty(str2);
        }
        return find;
    }

    public SettableBeanProperty findBackReference(String str) {
        String str2 = str;
        if (this._backRefs == null) {
            return null;
        }
        return this._backRefs.get(str2);
    }

    public ValueInstantiator getValueInstantiator() {
        return this._valueInstantiator;
    }

    public void replaceProperty(SettableBeanProperty settableBeanProperty, SettableBeanProperty settableBeanProperty2) {
        SettableBeanProperty settableBeanProperty3 = settableBeanProperty;
        this._beanProperties.replace(settableBeanProperty2);
    }

    public final Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        JsonToken currentToken;
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        TypeDeserializer typeDeserializer2 = typeDeserializer;
        if (this._objectIdReader == null || (currentToken = jsonParser2.getCurrentToken()) == null || !currentToken.isScalarValue()) {
            return typeDeserializer2.deserializeTypedFromObject(jsonParser2, deserializationContext2);
        }
        return deserializeFromObjectId(jsonParser2, deserializationContext2);
    }

    /* access modifiers changed from: protected */
    public Object deserializeWithObjectId(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        TokenBuffer tokenBuffer;
        TokenBuffer tokenBuffer2;
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        String str = this._objectIdReader.propertyName;
        if (str.equals(jsonParser2.getCurrentName())) {
            return deserializeFromObject(jsonParser2, deserializationContext2);
        }
        new TokenBuffer(jsonParser2.getCodec());
        TokenBuffer tokenBuffer3 = tokenBuffer;
        TokenBuffer tokenBuffer4 = null;
        while (jsonParser2.getCurrentToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser2.getCurrentName();
            if (tokenBuffer4 != null) {
                tokenBuffer4.writeFieldName(currentName);
                JsonToken nextToken = jsonParser2.nextToken();
                tokenBuffer4.copyCurrentStructure(jsonParser2);
            } else if (str.equals(currentName)) {
                new TokenBuffer(jsonParser2.getCodec());
                tokenBuffer4 = tokenBuffer2;
                tokenBuffer4.writeFieldName(currentName);
                JsonToken nextToken2 = jsonParser2.nextToken();
                tokenBuffer4.copyCurrentStructure(jsonParser2);
                TokenBuffer append = tokenBuffer4.append(tokenBuffer3);
                tokenBuffer3 = null;
            } else {
                tokenBuffer3.writeFieldName(currentName);
                JsonToken nextToken3 = jsonParser2.nextToken();
                tokenBuffer3.copyCurrentStructure(jsonParser2);
            }
            JsonToken nextToken4 = jsonParser2.nextToken();
        }
        TokenBuffer tokenBuffer5 = tokenBuffer4 == null ? tokenBuffer3 : tokenBuffer4;
        tokenBuffer5.writeEndObject();
        JsonParser asParser = tokenBuffer5.asParser();
        JsonToken nextToken5 = asParser.nextToken();
        return deserializeFromObject(asParser, deserializationContext2);
    }

    /* access modifiers changed from: protected */
    public Object deserializeFromObjectId(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Throwable th;
        StringBuilder sb;
        DeserializationContext deserializationContext2 = deserializationContext;
        Object deserialize = this._objectIdReader.deserializer.deserialize(jsonParser, deserializationContext2);
        Object obj = deserializationContext2.findObjectId(deserialize, this._objectIdReader.generator).item;
        if (obj != null) {
            return obj;
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalStateException(sb.append("Could not resolve Object Id [").append(deserialize).append("] (for ").append(this._beanType).append(") -- unresolved forward-reference?").toString());
        throw th2;
    }

    /* access modifiers changed from: protected */
    public Object deserializeFromObjectUsingNonDefault(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        StringBuilder sb;
        StringBuilder sb2;
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        if (this._delegateDeserializer != null) {
            return this._valueInstantiator.createUsingDelegate(deserializationContext2, this._delegateDeserializer.deserialize(jsonParser2, deserializationContext2));
        }
        if (this._propertyBasedCreator != null) {
            return _deserializeUsingPropertyBased(jsonParser2, deserializationContext2);
        }
        if (this._beanType.isAbstract()) {
            new StringBuilder();
            throw JsonMappingException.from(jsonParser2, sb2.append("Can not instantiate abstract type ").append(this._beanType).append(" (need to add/enable type information?)").toString());
        }
        new StringBuilder();
        throw JsonMappingException.from(jsonParser2, sb.append("No suitable constructor found for type ").append(this._beanType).append(": can not instantiate from JSON object (need to add/enable type information?)").toString());
    }

    public Object deserializeFromNumber(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        if (this._objectIdReader != null) {
            return deserializeFromObjectId(jsonParser2, deserializationContext2);
        }
        switch (jsonParser2.getNumberType()) {
            case INT:
                if (this._delegateDeserializer == null || this._valueInstantiator.canCreateFromInt()) {
                    return this._valueInstantiator.createFromInt(deserializationContext2, jsonParser2.getIntValue());
                }
                Object createUsingDelegate = this._valueInstantiator.createUsingDelegate(deserializationContext2, this._delegateDeserializer.deserialize(jsonParser2, deserializationContext2));
                if (this._injectables != null) {
                    injectValues(deserializationContext2, createUsingDelegate);
                }
                return createUsingDelegate;
            case LONG:
                if (this._delegateDeserializer == null || this._valueInstantiator.canCreateFromInt()) {
                    return this._valueInstantiator.createFromLong(deserializationContext2, jsonParser2.getLongValue());
                }
                Object createUsingDelegate2 = this._valueInstantiator.createUsingDelegate(deserializationContext2, this._delegateDeserializer.deserialize(jsonParser2, deserializationContext2));
                if (this._injectables != null) {
                    injectValues(deserializationContext2, createUsingDelegate2);
                }
                return createUsingDelegate2;
            default:
                if (this._delegateDeserializer != null) {
                    Object createUsingDelegate3 = this._valueInstantiator.createUsingDelegate(deserializationContext2, this._delegateDeserializer.deserialize(jsonParser2, deserializationContext2));
                    if (this._injectables != null) {
                        injectValues(deserializationContext2, createUsingDelegate3);
                    }
                    return createUsingDelegate3;
                }
                throw deserializationContext2.instantiationException(getBeanClass(), "no suitable creator method found to deserialize from JSON integer number");
        }
    }

    public Object deserializeFromString(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        if (this._objectIdReader != null) {
            return deserializeFromObjectId(jsonParser2, deserializationContext2);
        }
        if (this._delegateDeserializer == null || this._valueInstantiator.canCreateFromString()) {
            return this._valueInstantiator.createFromString(deserializationContext2, jsonParser2.getText());
        }
        Object createUsingDelegate = this._valueInstantiator.createUsingDelegate(deserializationContext2, this._delegateDeserializer.deserialize(jsonParser2, deserializationContext2));
        if (this._injectables != null) {
            injectValues(deserializationContext2, createUsingDelegate);
        }
        return createUsingDelegate;
    }

    public Object deserializeFromDouble(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        switch (jsonParser2.getNumberType()) {
            case FLOAT:
            case DOUBLE:
                if (this._delegateDeserializer == null || this._valueInstantiator.canCreateFromDouble()) {
                    return this._valueInstantiator.createFromDouble(deserializationContext2, jsonParser2.getDoubleValue());
                }
                Object createUsingDelegate = this._valueInstantiator.createUsingDelegate(deserializationContext2, this._delegateDeserializer.deserialize(jsonParser2, deserializationContext2));
                if (this._injectables != null) {
                    injectValues(deserializationContext2, createUsingDelegate);
                }
                return createUsingDelegate;
            default:
                if (this._delegateDeserializer != null) {
                    return this._valueInstantiator.createUsingDelegate(deserializationContext2, this._delegateDeserializer.deserialize(jsonParser2, deserializationContext2));
                }
                throw deserializationContext2.instantiationException(getBeanClass(), "no suitable creator method found to deserialize from JSON floating-point number");
        }
    }

    public Object deserializeFromBoolean(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        if (this._delegateDeserializer == null || this._valueInstantiator.canCreateFromBoolean()) {
            return this._valueInstantiator.createFromBoolean(deserializationContext2, jsonParser2.getCurrentToken() == JsonToken.VALUE_TRUE);
        }
        Object createUsingDelegate = this._valueInstantiator.createUsingDelegate(deserializationContext2, this._delegateDeserializer.deserialize(jsonParser2, deserializationContext2));
        if (this._injectables != null) {
            injectValues(deserializationContext2, createUsingDelegate);
        }
        return createUsingDelegate;
    }

    public Object deserializeFromArray(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        if (this._delegateDeserializer != null) {
            try {
                Object createUsingDelegate = this._valueInstantiator.createUsingDelegate(deserializationContext2, this._delegateDeserializer.deserialize(jsonParser2, deserializationContext2));
                if (this._injectables != null) {
                    injectValues(deserializationContext2, createUsingDelegate);
                }
                return createUsingDelegate;
            } catch (Exception e) {
                wrapInstantiationProblem(e, deserializationContext2);
            }
        }
        throw deserializationContext2.mappingException(getBeanClass());
    }

    /* access modifiers changed from: protected */
    public void injectValues(DeserializationContext deserializationContext, Object obj) throws IOException, JsonProcessingException {
        DeserializationContext deserializationContext2 = deserializationContext;
        Object obj2 = obj;
        ValueInjector[] valueInjectorArr = this._injectables;
        int length = valueInjectorArr.length;
        for (int i = 0; i < length; i++) {
            valueInjectorArr[i].inject(deserializationContext2, obj2);
        }
    }

    /* access modifiers changed from: protected */
    public void handleUnknownProperty(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, String str) throws IOException, JsonProcessingException {
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        Object obj2 = obj;
        String str2 = str;
        if (this._ignoreAllUnknown || (this._ignorableProps != null && this._ignorableProps.contains(str2))) {
            JsonParser skipChildren = jsonParser2.skipChildren();
        } else {
            super.handleUnknownProperty(jsonParser2, deserializationContext2, obj2, str2);
        }
    }

    /* access modifiers changed from: protected */
    public Object handleUnknownProperties(DeserializationContext deserializationContext, Object obj, TokenBuffer tokenBuffer) throws IOException, JsonProcessingException {
        DeserializationContext deserializationContext2 = deserializationContext;
        Object obj2 = obj;
        TokenBuffer tokenBuffer2 = tokenBuffer;
        tokenBuffer2.writeEndObject();
        JsonParser asParser = tokenBuffer2.asParser();
        while (asParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = asParser.getCurrentName();
            JsonToken nextToken = asParser.nextToken();
            handleUnknownProperty(asParser, deserializationContext2, obj2, currentName);
        }
        return obj2;
    }

    /* access modifiers changed from: protected */
    public void handleUnknownVanilla(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, String str) throws IOException, JsonProcessingException {
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        Object obj2 = obj;
        String str2 = str;
        if (this._ignorableProps != null && this._ignorableProps.contains(str2)) {
            JsonParser skipChildren = jsonParser2.skipChildren();
        } else if (this._anySetter != null) {
            try {
                this._anySetter.deserializeAndSet(jsonParser2, deserializationContext2, obj2, str2);
            } catch (Exception e) {
                wrapAndThrow((Throwable) e, obj2, str2, deserializationContext2);
            }
        } else {
            handleUnknownProperty(jsonParser2, deserializationContext2, obj2, str2);
        }
    }

    /* access modifiers changed from: protected */
    public Object handlePolymorphic(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, TokenBuffer tokenBuffer) throws IOException, JsonProcessingException {
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        Object obj2 = obj;
        TokenBuffer tokenBuffer2 = tokenBuffer;
        JsonDeserializer<Object> _findSubclassDeserializer = _findSubclassDeserializer(deserializationContext2, obj2, tokenBuffer2);
        if (_findSubclassDeserializer != null) {
            if (tokenBuffer2 != null) {
                tokenBuffer2.writeEndObject();
                JsonParser asParser = tokenBuffer2.asParser();
                JsonToken nextToken = asParser.nextToken();
                obj2 = _findSubclassDeserializer.deserialize(asParser, deserializationContext2, obj2);
            }
            if (jsonParser2 != null) {
                obj2 = _findSubclassDeserializer.deserialize(jsonParser2, deserializationContext2, obj2);
            }
            return obj2;
        }
        if (tokenBuffer2 != null) {
            obj2 = handleUnknownProperties(deserializationContext2, obj2, tokenBuffer2);
        }
        if (jsonParser2 != null) {
            obj2 = deserialize(jsonParser2, deserializationContext2, obj2);
        }
        return obj2;
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    public JsonDeserializer<Object> _findSubclassDeserializer(DeserializationContext deserializationContext, Object obj, TokenBuffer tokenBuffer) throws IOException, JsonProcessingException {
        Object obj2;
        JsonDeserializer<Object> jsonDeserializer;
        Object obj3;
        HashMap<ClassKey, JsonDeserializer<Object>> hashMap;
        DeserializationContext deserializationContext2 = deserializationContext;
        Object obj4 = obj;
        TokenBuffer tokenBuffer2 = tokenBuffer;
        synchronized (this) {
            try {
                if (this._subDeserializers == null) {
                    jsonDeserializer = null;
                } else {
                    new ClassKey(obj4.getClass());
                    jsonDeserializer = this._subDeserializers.get(obj2);
                }
                JsonDeserializer<Object> jsonDeserializer2 = jsonDeserializer;
                if (jsonDeserializer2 != null) {
                    return jsonDeserializer2;
                }
                JsonDeserializer<Object> findRootValueDeserializer = deserializationContext2.findRootValueDeserializer(deserializationContext2.constructType(obj4.getClass()));
                if (findRootValueDeserializer != null) {
                    synchronized (this) {
                        try {
                            if (this._subDeserializers == null) {
                                new HashMap();
                                this._subDeserializers = hashMap;
                            }
                            new ClassKey(obj4.getClass());
                            JsonDeserializer<Object> put = this._subDeserializers.put(obj3, findRootValueDeserializer);
                        } catch (Throwable th) {
                            Throwable th2 = th;
                            throw th2;
                        }
                    }
                }
                return findRootValueDeserializer;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                throw th4;
            }
        }
    }

    public void wrapAndThrow(Throwable th, Object obj, String str, DeserializationContext deserializationContext) throws IOException {
        throw JsonMappingException.wrapWithPath(throwOrReturnThrowable(th, deserializationContext), obj, str);
    }

    public void wrapAndThrow(Throwable th, Object obj, int i, DeserializationContext deserializationContext) throws IOException {
        throw JsonMappingException.wrapWithPath(throwOrReturnThrowable(th, deserializationContext), obj, i);
    }

    private Throwable throwOrReturnThrowable(Throwable th, DeserializationContext deserializationContext) throws IOException {
        Throwable th2 = th;
        DeserializationContext deserializationContext2 = deserializationContext;
        while ((th2 instanceof InvocationTargetException) && th2.getCause() != null) {
            th2 = th2.getCause();
        }
        if (th2 instanceof Error) {
            throw ((Error) th2);
        }
        boolean z = deserializationContext2 == null || deserializationContext2.isEnabled(DeserializationFeature.WRAP_EXCEPTIONS);
        if (th2 instanceof IOException) {
            if (!z || !(th2 instanceof JsonProcessingException)) {
                throw ((IOException) th2);
            }
        } else if (!z && (th2 instanceof RuntimeException)) {
            throw ((RuntimeException) th2);
        }
        return th2;
    }

    /* access modifiers changed from: protected */
    public void wrapInstantiationProblem(Throwable th, DeserializationContext deserializationContext) throws IOException {
        Throwable th2 = th;
        DeserializationContext deserializationContext2 = deserializationContext;
        while ((th2 instanceof InvocationTargetException) && th2.getCause() != null) {
            th2 = th2.getCause();
        }
        if (th2 instanceof Error) {
            throw ((Error) th2);
        }
        boolean z = deserializationContext2 == null || deserializationContext2.isEnabled(DeserializationFeature.WRAP_EXCEPTIONS);
        if (th2 instanceof IOException) {
            throw ((IOException) th2);
        } else if (z || !(th2 instanceof RuntimeException)) {
            throw deserializationContext2.instantiationException(this._beanType.getRawClass(), th2);
        } else {
            throw ((RuntimeException) th2);
        }
    }
}

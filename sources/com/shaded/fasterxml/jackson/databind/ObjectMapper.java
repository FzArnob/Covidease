package com.shaded.fasterxml.jackson.databind;

import com.shaded.fasterxml.jackson.annotation.JsonAutoDetect;
import com.shaded.fasterxml.jackson.annotation.JsonInclude;
import com.shaded.fasterxml.jackson.annotation.JsonTypeInfo;
import com.shaded.fasterxml.jackson.annotation.PropertyAccessor;
import com.shaded.fasterxml.jackson.core.Base64Variant;
import com.shaded.fasterxml.jackson.core.Base64Variants;
import com.shaded.fasterxml.jackson.core.FormatSchema;
import com.shaded.fasterxml.jackson.core.JsonEncoding;
import com.shaded.fasterxml.jackson.core.JsonFactory;
import com.shaded.fasterxml.jackson.core.JsonGenerationException;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.core.JsonParseException;
import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.core.JsonToken;
import com.shaded.fasterxml.jackson.core.ObjectCodec;
import com.shaded.fasterxml.jackson.core.PrettyPrinter;
import com.shaded.fasterxml.jackson.core.TreeNode;
import com.shaded.fasterxml.jackson.core.Version;
import com.shaded.fasterxml.jackson.core.Versioned;
import com.shaded.fasterxml.jackson.core.p005io.SegmentedStringWriter;
import com.shaded.fasterxml.jackson.core.type.ResolvedType;
import com.shaded.fasterxml.jackson.core.type.TypeReference;
import com.shaded.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.shaded.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.shaded.fasterxml.jackson.databind.Module;
import com.shaded.fasterxml.jackson.databind.cfg.BaseSettings;
import com.shaded.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.shaded.fasterxml.jackson.databind.cfg.MapperConfig;
import com.shaded.fasterxml.jackson.databind.cfg.PackageVersion;
import com.shaded.fasterxml.jackson.databind.deser.BeanDeserializerFactory;
import com.shaded.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.shaded.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.shaded.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.shaded.fasterxml.jackson.databind.deser.DeserializerFactory;
import com.shaded.fasterxml.jackson.databind.deser.Deserializers;
import com.shaded.fasterxml.jackson.databind.deser.KeyDeserializers;
import com.shaded.fasterxml.jackson.databind.deser.ValueInstantiators;
import com.shaded.fasterxml.jackson.databind.introspect.BasicClassIntrospector;
import com.shaded.fasterxml.jackson.databind.introspect.ClassIntrospector;
import com.shaded.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.shaded.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.shaded.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.shaded.fasterxml.jackson.databind.jsonschema.JsonSchema;
import com.shaded.fasterxml.jackson.databind.jsontype.NamedType;
import com.shaded.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.shaded.fasterxml.jackson.databind.jsontype.impl.StdSubtypeResolver;
import com.shaded.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder;
import com.shaded.fasterxml.jackson.databind.node.ArrayNode;
import com.shaded.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.shaded.fasterxml.jackson.databind.node.NullNode;
import com.shaded.fasterxml.jackson.databind.node.ObjectNode;
import com.shaded.fasterxml.jackson.databind.node.TreeTraversingParser;
import com.shaded.fasterxml.jackson.databind.ser.BeanSerializerFactory;
import com.shaded.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.shaded.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.shaded.fasterxml.jackson.databind.ser.FilterProvider;
import com.shaded.fasterxml.jackson.databind.ser.SerializerFactory;
import com.shaded.fasterxml.jackson.databind.ser.Serializers;
import com.shaded.fasterxml.jackson.databind.type.ClassKey;
import com.shaded.fasterxml.jackson.databind.type.SimpleType;
import com.shaded.fasterxml.jackson.databind.type.TypeFactory;
import com.shaded.fasterxml.jackson.databind.type.TypeModifier;
import com.shaded.fasterxml.jackson.databind.util.RootNameLookup;
import com.shaded.fasterxml.jackson.databind.util.StdDateFormat;
import com.shaded.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.lang.reflect.Type;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

public class ObjectMapper extends ObjectCodec implements Versioned, Serializable {
    protected static final AnnotationIntrospector DEFAULT_ANNOTATION_INTROSPECTOR;
    protected static final BaseSettings DEFAULT_BASE;
    protected static final ClassIntrospector DEFAULT_INTROSPECTOR = BasicClassIntrospector.instance;
    private static final JavaType JSON_NODE_TYPE = SimpleType.constructUnsafe(JsonNode.class);
    protected static final VisibilityChecker<?> STD_VISIBILITY_CHECKER = VisibilityChecker.Std.defaultInstance();
    protected static final PrettyPrinter _defaultPrettyPrinter;
    private static final long serialVersionUID = 1;
    protected DeserializationConfig _deserializationConfig;
    protected DefaultDeserializationContext _deserializationContext;
    protected InjectableValues _injectableValues;
    protected final JsonFactory _jsonFactory;
    protected final HashMap<ClassKey, Class<?>> _mixInAnnotations;
    protected final ConcurrentHashMap<JavaType, JsonDeserializer<Object>> _rootDeserializers;
    protected final RootNameLookup _rootNames;
    protected SerializationConfig _serializationConfig;
    protected SerializerFactory _serializerFactory;
    protected DefaultSerializerProvider _serializerProvider;
    protected SubtypeResolver _subtypeResolver;
    protected TypeFactory _typeFactory;

    public enum DefaultTyping {
    }

    public static class DefaultTypeResolverBuilder extends StdTypeResolverBuilder implements Serializable {
        private static final long serialVersionUID = 1;
        protected final DefaultTyping _appliesFor;

        public DefaultTypeResolverBuilder(DefaultTyping defaultTyping) {
            this._appliesFor = defaultTyping;
        }

        public TypeDeserializer buildTypeDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, Collection<NamedType> collection) {
            JavaType javaType2 = javaType;
            return useForType(javaType2) ? super.buildTypeDeserializer(deserializationConfig, javaType2, collection) : null;
        }

        public TypeSerializer buildTypeSerializer(SerializationConfig serializationConfig, JavaType javaType, Collection<NamedType> collection) {
            JavaType javaType2 = javaType;
            return useForType(javaType2) ? super.buildTypeSerializer(serializationConfig, javaType2, collection) : null;
        }

        /* JADX WARNING: Can't fix incorrect switch cases order */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0044, code lost:
            if (r1.isArrayType() == false) goto L_0x004d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0046, code lost:
            r1 = r1.getContentType();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0052, code lost:
            if (r1.isFinal() != false) goto L_0x0057;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0054, code lost:
            r2 = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x0057, code lost:
            r2 = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
            return r2;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean useForType(com.shaded.fasterxml.jackson.databind.JavaType r5) {
            /*
                r4 = this;
                r0 = r4
                r1 = r5
                int[] r2 = com.shaded.fasterxml.jackson.databind.ObjectMapper.C14432.f286x3ef634e7
                r3 = r0
                com.shaded.fasterxml.jackson.databind.ObjectMapper$DefaultTyping r3 = r3._appliesFor
                int r3 = r3.ordinal()
                r2 = r2[r3]
                switch(r2) {
                    case 1: goto L_0x001c;
                    case 2: goto L_0x002a;
                    case 3: goto L_0x003f;
                    default: goto L_0x0010;
                }
            L_0x0010:
                r2 = r1
                java.lang.Class r2 = r2.getRawClass()
                java.lang.Class<java.lang.Object> r3 = java.lang.Object.class
                if (r2 != r3) goto L_0x0059
                r2 = 1
            L_0x001a:
                r0 = r2
            L_0x001b:
                return r0
            L_0x001c:
                r2 = r1
                boolean r2 = r2.isArrayType()
                if (r2 == 0) goto L_0x002a
                r2 = r1
                com.shaded.fasterxml.jackson.databind.JavaType r2 = r2.getContentType()
                r1 = r2
                goto L_0x001c
            L_0x002a:
                r2 = r1
                java.lang.Class r2 = r2.getRawClass()
                java.lang.Class<java.lang.Object> r3 = java.lang.Object.class
                if (r2 == r3) goto L_0x003a
                r2 = r1
                boolean r2 = r2.isConcrete()
                if (r2 != 0) goto L_0x003d
            L_0x003a:
                r2 = 1
            L_0x003b:
                r0 = r2
                goto L_0x001b
            L_0x003d:
                r2 = 0
                goto L_0x003b
            L_0x003f:
                r2 = r1
                boolean r2 = r2.isArrayType()
                if (r2 == 0) goto L_0x004d
                r2 = r1
                com.shaded.fasterxml.jackson.databind.JavaType r2 = r2.getContentType()
                r1 = r2
                goto L_0x003f
            L_0x004d:
                r2 = r1
                boolean r2 = r2.isFinal()
                if (r2 != 0) goto L_0x0057
                r2 = 1
            L_0x0055:
                r0 = r2
                goto L_0x001b
            L_0x0057:
                r2 = 0
                goto L_0x0055
            L_0x0059:
                r2 = 0
                goto L_0x001a
            */
            throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.ObjectMapper.DefaultTypeResolverBuilder.useForType(com.shaded.fasterxml.jackson.databind.JavaType):boolean");
        }
    }

    static {
        AnnotationIntrospector annotationIntrospector;
        PrettyPrinter prettyPrinter;
        BaseSettings baseSettings;
        new JacksonAnnotationIntrospector();
        DEFAULT_ANNOTATION_INTROSPECTOR = annotationIntrospector;
        new DefaultPrettyPrinter();
        _defaultPrettyPrinter = prettyPrinter;
        new BaseSettings(DEFAULT_INTROSPECTOR, DEFAULT_ANNOTATION_INTROSPECTOR, STD_VISIBILITY_CHECKER, (PropertyNamingStrategy) null, TypeFactory.defaultInstance(), (TypeResolverBuilder<?>) null, StdDateFormat.instance, (HandlerInstantiator) null, Locale.getDefault(), TimeZone.getTimeZone("GMT"), Base64Variants.getDefaultVariant());
        DEFAULT_BASE = baseSettings;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ObjectMapper() {
        this((JsonFactory) null, (DefaultSerializerProvider) null, (DefaultDeserializationContext) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ObjectMapper(JsonFactory jsonFactory) {
        this(jsonFactory, (DefaultSerializerProvider) null, (DefaultDeserializationContext) null);
    }

    protected ObjectMapper(ObjectMapper objectMapper) {
        HashMap<ClassKey, Class<?>> hashMap;
        ConcurrentHashMap<JavaType, JsonDeserializer<Object>> concurrentHashMap;
        RootNameLookup rootNameLookup;
        Map map;
        SerializationConfig serializationConfig;
        DeserializationConfig deserializationConfig;
        ObjectMapper objectMapper2 = objectMapper;
        new HashMap<>();
        this._mixInAnnotations = hashMap;
        new ConcurrentHashMap<>(64, 0.6f, 2);
        this._rootDeserializers = concurrentHashMap;
        this._jsonFactory = objectMapper2._jsonFactory.copy();
        JsonFactory codec = this._jsonFactory.setCodec(this);
        this._subtypeResolver = objectMapper2._subtypeResolver;
        new RootNameLookup();
        this._rootNames = rootNameLookup;
        this._typeFactory = objectMapper2._typeFactory;
        this._serializationConfig = objectMapper2._serializationConfig;
        new HashMap(objectMapper2._mixInAnnotations);
        Map map2 = map;
        new SerializationConfig(objectMapper2._serializationConfig, (Map<ClassKey, Class<?>>) map2);
        this._serializationConfig = serializationConfig;
        new DeserializationConfig(objectMapper2._deserializationConfig, (Map<ClassKey, Class<?>>) map2);
        this._deserializationConfig = deserializationConfig;
        this._serializerProvider = objectMapper2._serializerProvider;
        this._deserializationContext = objectMapper2._deserializationContext;
        this._serializerFactory = objectMapper2._serializerFactory;
    }

    public ObjectMapper(JsonFactory jsonFactory, DefaultSerializerProvider defaultSerializerProvider, DefaultDeserializationContext defaultDeserializationContext) {
        HashMap<ClassKey, Class<?>> hashMap;
        ConcurrentHashMap<JavaType, JsonDeserializer<Object>> concurrentHashMap;
        SubtypeResolver subtypeResolver;
        RootNameLookup rootNameLookup;
        SerializationConfig serializationConfig;
        DeserializationConfig deserializationConfig;
        DefaultSerializerProvider defaultSerializerProvider2;
        DefaultDeserializationContext defaultDeserializationContext2;
        DefaultDeserializationContext defaultDeserializationContext3;
        DefaultSerializerProvider defaultSerializerProvider3;
        JsonFactory jsonFactory2;
        JsonFactory jsonFactory3 = jsonFactory;
        DefaultSerializerProvider defaultSerializerProvider4 = defaultSerializerProvider;
        DefaultDeserializationContext defaultDeserializationContext4 = defaultDeserializationContext;
        new HashMap<>();
        this._mixInAnnotations = hashMap;
        new ConcurrentHashMap<>(64, 0.6f, 2);
        this._rootDeserializers = concurrentHashMap;
        if (jsonFactory3 == null) {
            new MappingJsonFactory(this);
            this._jsonFactory = jsonFactory2;
        } else {
            this._jsonFactory = jsonFactory3;
            if (jsonFactory3.getCodec() == null) {
                JsonFactory codec = this._jsonFactory.setCodec(this);
            }
        }
        new StdSubtypeResolver();
        this._subtypeResolver = subtypeResolver;
        new RootNameLookup();
        this._rootNames = rootNameLookup;
        this._typeFactory = TypeFactory.defaultInstance();
        new SerializationConfig(DEFAULT_BASE, this._subtypeResolver, (Map<ClassKey, Class<?>>) this._mixInAnnotations);
        this._serializationConfig = serializationConfig;
        new DeserializationConfig(DEFAULT_BASE, this._subtypeResolver, (Map<ClassKey, Class<?>>) this._mixInAnnotations);
        this._deserializationConfig = deserializationConfig;
        if (defaultSerializerProvider4 == null) {
            defaultSerializerProvider2 = defaultSerializerProvider3;
            new DefaultSerializerProvider.Impl();
        } else {
            defaultSerializerProvider2 = defaultSerializerProvider4;
        }
        this._serializerProvider = defaultSerializerProvider2;
        if (defaultDeserializationContext4 == null) {
            defaultDeserializationContext2 = defaultDeserializationContext3;
            new DefaultDeserializationContext.Impl(BeanDeserializerFactory.instance);
        } else {
            defaultDeserializationContext2 = defaultDeserializationContext4;
        }
        this._deserializationContext = defaultDeserializationContext2;
        this._serializerFactory = BeanSerializerFactory.instance;
    }

    public ObjectMapper copy() {
        ObjectMapper objectMapper;
        _checkInvalidCopy(ObjectMapper.class);
        new ObjectMapper(this);
        return objectMapper;
    }

    /* access modifiers changed from: protected */
    public void _checkInvalidCopy(Class<?> cls) {
        Throwable th;
        StringBuilder sb;
        if (getClass() != cls) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("Failed copy(): ").append(getClass().getName()).append(" (version: ").append(version()).append(") does not override copy(); it has to").toString());
            throw th2;
        }
    }

    public Version version() {
        return PackageVersion.VERSION;
    }

    public ObjectMapper registerModule(Module module) {
        Module.SetupContext setupContext;
        Throwable th;
        Throwable th2;
        Module module2 = module;
        if (module2.getModuleName() == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("Module without defined name");
            throw th3;
        } else if (module2.version() == null) {
            Throwable th4 = th;
            new IllegalArgumentException("Module without defined version");
            throw th4;
        } else {
            new Module.SetupContext(this) {
                final /* synthetic */ ObjectMapper this$0;

                {
                    this.this$0 = r6;
                }

                public Version getMapperVersion() {
                    return this.this$0.version();
                }

                public <C extends ObjectCodec> C getOwner() {
                    return this;
                }

                public TypeFactory getTypeFactory() {
                    return this.this$0._typeFactory;
                }

                public boolean isEnabled(MapperFeature mapperFeature) {
                    return this.isEnabled(mapperFeature);
                }

                public boolean isEnabled(DeserializationFeature deserializationFeature) {
                    return this.isEnabled(deserializationFeature);
                }

                public boolean isEnabled(SerializationFeature serializationFeature) {
                    return this.isEnabled(serializationFeature);
                }

                public boolean isEnabled(JsonFactory.Feature feature) {
                    return this.isEnabled(feature);
                }

                public boolean isEnabled(JsonParser.Feature feature) {
                    return this.isEnabled(feature);
                }

                public boolean isEnabled(JsonGenerator.Feature feature) {
                    return this.isEnabled(feature);
                }

                public void addDeserializers(Deserializers deserializers) {
                    DeserializerFactory withAdditionalDeserializers = this._deserializationContext._factory.withAdditionalDeserializers(deserializers);
                    this._deserializationContext = this._deserializationContext.with(withAdditionalDeserializers);
                }

                public void addKeyDeserializers(KeyDeserializers keyDeserializers) {
                    DeserializerFactory withAdditionalKeyDeserializers = this._deserializationContext._factory.withAdditionalKeyDeserializers(keyDeserializers);
                    this._deserializationContext = this._deserializationContext.with(withAdditionalKeyDeserializers);
                }

                public void addBeanDeserializerModifier(BeanDeserializerModifier beanDeserializerModifier) {
                    DeserializerFactory withDeserializerModifier = this._deserializationContext._factory.withDeserializerModifier(beanDeserializerModifier);
                    this._deserializationContext = this._deserializationContext.with(withDeserializerModifier);
                }

                public void addSerializers(Serializers serializers) {
                    this._serializerFactory = this._serializerFactory.withAdditionalSerializers(serializers);
                }

                public void addKeySerializers(Serializers serializers) {
                    this._serializerFactory = this._serializerFactory.withAdditionalKeySerializers(serializers);
                }

                public void addBeanSerializerModifier(BeanSerializerModifier beanSerializerModifier) {
                    this._serializerFactory = this._serializerFactory.withSerializerModifier(beanSerializerModifier);
                }

                public void addAbstractTypeResolver(AbstractTypeResolver abstractTypeResolver) {
                    DeserializerFactory withAbstractTypeResolver = this._deserializationContext._factory.withAbstractTypeResolver(abstractTypeResolver);
                    this._deserializationContext = this._deserializationContext.with(withAbstractTypeResolver);
                }

                public void addTypeModifier(TypeModifier typeModifier) {
                    ObjectMapper typeFactory = this.setTypeFactory(this._typeFactory.withModifier(typeModifier));
                }

                public void addValueInstantiators(ValueInstantiators valueInstantiators) {
                    DeserializerFactory withValueInstantiators = this._deserializationContext._factory.withValueInstantiators(valueInstantiators);
                    this._deserializationContext = this._deserializationContext.with(withValueInstantiators);
                }

                public void setClassIntrospector(ClassIntrospector classIntrospector) {
                    ClassIntrospector classIntrospector2 = classIntrospector;
                    this._deserializationConfig = this._deserializationConfig.with(classIntrospector2);
                    this._serializationConfig = this._serializationConfig.with(classIntrospector2);
                }

                public void insertAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
                    AnnotationIntrospector annotationIntrospector2 = annotationIntrospector;
                    this._deserializationConfig = this._deserializationConfig.withInsertedAnnotationIntrospector(annotationIntrospector2);
                    this._serializationConfig = this._serializationConfig.withInsertedAnnotationIntrospector(annotationIntrospector2);
                }

                public void appendAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
                    AnnotationIntrospector annotationIntrospector2 = annotationIntrospector;
                    this._deserializationConfig = this._deserializationConfig.withAppendedAnnotationIntrospector(annotationIntrospector2);
                    this._serializationConfig = this._serializationConfig.withAppendedAnnotationIntrospector(annotationIntrospector2);
                }

                public void registerSubtypes(Class<?>... clsArr) {
                    this.registerSubtypes(clsArr);
                }

                public void registerSubtypes(NamedType... namedTypeArr) {
                    this.registerSubtypes(namedTypeArr);
                }

                public void setMixInAnnotations(Class<?> cls, Class<?> cls2) {
                    this.addMixInAnnotations(cls, cls2);
                }

                public void addDeserializationProblemHandler(DeserializationProblemHandler deserializationProblemHandler) {
                    ObjectMapper addHandler = this.addHandler(deserializationProblemHandler);
                }
            };
            module2.setupModule(setupContext);
            return this;
        }
    }

    public ObjectMapper registerModules(Module... moduleArr) {
        Module[] moduleArr2 = moduleArr;
        int length = moduleArr2.length;
        for (int i = 0; i < length; i++) {
            ObjectMapper registerModule = registerModule(moduleArr2[i]);
        }
        return this;
    }

    public ObjectMapper registerModules(Iterable<Module> iterable) {
        for (Module registerModule : iterable) {
            ObjectMapper registerModule2 = registerModule(registerModule);
        }
        return this;
    }

    public static List<Module> findModules() {
        return findModules((ClassLoader) null);
    }

    public static List<Module> findModules(ClassLoader classLoader) {
        ArrayList arrayList;
        ClassLoader classLoader2 = classLoader;
        new ArrayList();
        ArrayList arrayList2 = arrayList;
        Iterator<S> it = (classLoader2 == null ? ServiceLoader.load(Module.class) : ServiceLoader.load(Module.class, classLoader2)).iterator();
        while (it.hasNext()) {
            boolean add = arrayList2.add((Module) it.next());
        }
        return arrayList2;
    }

    public ObjectMapper findAndRegisterModules() {
        return registerModules((Iterable<Module>) findModules());
    }

    public SerializationConfig getSerializationConfig() {
        return this._serializationConfig;
    }

    public DeserializationConfig getDeserializationConfig() {
        return this._deserializationConfig;
    }

    public DeserializationContext getDeserializationContext() {
        return this._deserializationContext;
    }

    public ObjectMapper setSerializerFactory(SerializerFactory serializerFactory) {
        this._serializerFactory = serializerFactory;
        return this;
    }

    public SerializerFactory getSerializerFactory() {
        return this._serializerFactory;
    }

    public ObjectMapper setSerializerProvider(DefaultSerializerProvider defaultSerializerProvider) {
        this._serializerProvider = defaultSerializerProvider;
        return this;
    }

    public SerializerProvider getSerializerProvider() {
        return this._serializerProvider;
    }

    public final void setMixInAnnotations(Map<Class<?>, Class<?>> map) {
        Object obj;
        Map<Class<?>, Class<?>> map2 = map;
        this._mixInAnnotations.clear();
        if (map2 != null && map2.size() > 0) {
            for (Map.Entry next : map2.entrySet()) {
                new ClassKey((Class) next.getKey());
                Class<?> put = this._mixInAnnotations.put(obj, next.getValue());
            }
        }
    }

    public final void addMixInAnnotations(Class<?> cls, Class<?> cls2) {
        Object obj;
        new ClassKey(cls);
        Class<?> put = this._mixInAnnotations.put(obj, cls2);
    }

    public final Class<?> findMixInClassFor(Class<?> cls) {
        Object obj;
        Class<?> cls2;
        Class<?> cls3 = cls;
        if (this._mixInAnnotations == null) {
            cls2 = null;
        } else {
            new ClassKey(cls3);
            cls2 = this._mixInAnnotations.get(obj);
        }
        return cls2;
    }

    public final int mixInCount() {
        return this._mixInAnnotations == null ? 0 : this._mixInAnnotations.size();
    }

    public VisibilityChecker<?> getVisibilityChecker() {
        return this._serializationConfig.getDefaultVisibilityChecker();
    }

    public void setVisibilityChecker(VisibilityChecker<?> visibilityChecker) {
        VisibilityChecker<?> visibilityChecker2 = visibilityChecker;
        this._deserializationConfig = this._deserializationConfig.with(visibilityChecker2);
        this._serializationConfig = this._serializationConfig.with(visibilityChecker2);
    }

    public ObjectMapper setVisibility(PropertyAccessor propertyAccessor, JsonAutoDetect.Visibility visibility) {
        PropertyAccessor propertyAccessor2 = propertyAccessor;
        JsonAutoDetect.Visibility visibility2 = visibility;
        this._deserializationConfig = this._deserializationConfig.withVisibility(propertyAccessor2, visibility2);
        this._serializationConfig = this._serializationConfig.withVisibility(propertyAccessor2, visibility2);
        return this;
    }

    public SubtypeResolver getSubtypeResolver() {
        return this._subtypeResolver;
    }

    public ObjectMapper setSubtypeResolver(SubtypeResolver subtypeResolver) {
        SubtypeResolver subtypeResolver2 = subtypeResolver;
        this._subtypeResolver = subtypeResolver2;
        this._deserializationConfig = this._deserializationConfig.with(subtypeResolver2);
        this._serializationConfig = this._serializationConfig.with(subtypeResolver2);
        return this;
    }

    public ObjectMapper setAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
        AnnotationIntrospector annotationIntrospector2 = annotationIntrospector;
        this._serializationConfig = this._serializationConfig.with(annotationIntrospector2);
        this._deserializationConfig = this._deserializationConfig.with(annotationIntrospector2);
        return this;
    }

    public ObjectMapper setAnnotationIntrospectors(AnnotationIntrospector annotationIntrospector, AnnotationIntrospector annotationIntrospector2) {
        this._serializationConfig = this._serializationConfig.with(annotationIntrospector);
        this._deserializationConfig = this._deserializationConfig.with(annotationIntrospector2);
        return this;
    }

    public ObjectMapper setPropertyNamingStrategy(PropertyNamingStrategy propertyNamingStrategy) {
        PropertyNamingStrategy propertyNamingStrategy2 = propertyNamingStrategy;
        this._serializationConfig = this._serializationConfig.with(propertyNamingStrategy2);
        this._deserializationConfig = this._deserializationConfig.with(propertyNamingStrategy2);
        return this;
    }

    public ObjectMapper setSerializationInclusion(JsonInclude.Include include) {
        this._serializationConfig = this._serializationConfig.withSerializationInclusion(include);
        return this;
    }

    public ObjectMapper enableDefaultTyping() {
        return enableDefaultTyping(DefaultTyping.OBJECT_AND_NON_CONCRETE);
    }

    public ObjectMapper enableDefaultTyping(DefaultTyping defaultTyping) {
        return enableDefaultTyping(defaultTyping, JsonTypeInfo.C1433As.WRAPPER_ARRAY);
    }

    public ObjectMapper enableDefaultTyping(DefaultTyping defaultTyping, JsonTypeInfo.C1433As as) {
        TypeResolverBuilder typeResolverBuilder;
        new DefaultTypeResolverBuilder(defaultTyping);
        return setDefaultTyping(typeResolverBuilder.init(JsonTypeInfo.C1434Id.CLASS, (TypeIdResolver) null).inclusion(as));
    }

    public ObjectMapper enableDefaultTypingAsProperty(DefaultTyping defaultTyping, String str) {
        TypeResolverBuilder typeResolverBuilder;
        new DefaultTypeResolverBuilder(defaultTyping);
        return setDefaultTyping(typeResolverBuilder.init(JsonTypeInfo.C1434Id.CLASS, (TypeIdResolver) null).inclusion(JsonTypeInfo.C1433As.PROPERTY).typeProperty(str));
    }

    public ObjectMapper disableDefaultTyping() {
        return setDefaultTyping((TypeResolverBuilder<?>) null);
    }

    public ObjectMapper setDefaultTyping(TypeResolverBuilder<?> typeResolverBuilder) {
        TypeResolverBuilder<?> typeResolverBuilder2 = typeResolverBuilder;
        this._deserializationConfig = this._deserializationConfig.with(typeResolverBuilder2);
        this._serializationConfig = this._serializationConfig.with(typeResolverBuilder2);
        return this;
    }

    public void registerSubtypes(Class<?>... clsArr) {
        getSubtypeResolver().registerSubtypes(clsArr);
    }

    public void registerSubtypes(NamedType... namedTypeArr) {
        getSubtypeResolver().registerSubtypes(namedTypeArr);
    }

    public TypeFactory getTypeFactory() {
        return this._typeFactory;
    }

    public ObjectMapper setTypeFactory(TypeFactory typeFactory) {
        TypeFactory typeFactory2 = typeFactory;
        this._typeFactory = typeFactory2;
        this._deserializationConfig = this._deserializationConfig.with(typeFactory2);
        this._serializationConfig = this._serializationConfig.with(typeFactory2);
        return this;
    }

    public JavaType constructType(Type type) {
        return this._typeFactory.constructType(type);
    }

    public ObjectMapper setNodeFactory(JsonNodeFactory jsonNodeFactory) {
        this._deserializationConfig = this._deserializationConfig.with(jsonNodeFactory);
        return this;
    }

    public ObjectMapper addHandler(DeserializationProblemHandler deserializationProblemHandler) {
        this._deserializationConfig = this._deserializationConfig.withHandler(deserializationProblemHandler);
        return this;
    }

    public ObjectMapper clearProblemHandlers() {
        this._deserializationConfig = this._deserializationConfig.withNoProblemHandlers();
        return this;
    }

    public void setFilters(FilterProvider filterProvider) {
        this._serializationConfig = this._serializationConfig.withFilters(filterProvider);
    }

    public ObjectMapper setBase64Variant(Base64Variant base64Variant) {
        Base64Variant base64Variant2 = base64Variant;
        this._serializationConfig = this._serializationConfig.with(base64Variant2);
        this._deserializationConfig = this._deserializationConfig.with(base64Variant2);
        return this;
    }

    public JsonFactory getFactory() {
        return this._jsonFactory;
    }

    @Deprecated
    public JsonFactory getJsonFactory() {
        return this._jsonFactory;
    }

    public ObjectMapper setDateFormat(DateFormat dateFormat) {
        DateFormat dateFormat2 = dateFormat;
        this._deserializationConfig = this._deserializationConfig.with(dateFormat2);
        this._serializationConfig = this._serializationConfig.with(dateFormat2);
        return this;
    }

    public Object setHandlerInstantiator(HandlerInstantiator handlerInstantiator) {
        HandlerInstantiator handlerInstantiator2 = handlerInstantiator;
        this._deserializationConfig = this._deserializationConfig.with(handlerInstantiator2);
        this._serializationConfig = this._serializationConfig.with(handlerInstantiator2);
        return this;
    }

    public ObjectMapper setInjectableValues(InjectableValues injectableValues) {
        this._injectableValues = injectableValues;
        return this;
    }

    public ObjectMapper setLocale(Locale locale) {
        Locale locale2 = locale;
        this._deserializationConfig = this._deserializationConfig.with(locale2);
        this._serializationConfig = this._serializationConfig.with(locale2);
        return this;
    }

    public ObjectMapper setTimeZone(TimeZone timeZone) {
        TimeZone timeZone2 = timeZone;
        this._deserializationConfig = this._deserializationConfig.with(timeZone2);
        this._serializationConfig = this._serializationConfig.with(timeZone2);
        return this;
    }

    public ObjectMapper configure(MapperFeature mapperFeature, boolean z) {
        SerializationConfig without;
        DeserializationConfig without2;
        MapperFeature mapperFeature2 = mapperFeature;
        boolean z2 = z;
        if (z2) {
            without = this._serializationConfig.with(mapperFeature2);
        } else {
            without = this._serializationConfig.without(mapperFeature2);
        }
        this._serializationConfig = without;
        if (z2) {
            without2 = this._deserializationConfig.with(mapperFeature2);
        } else {
            without2 = this._deserializationConfig.without(mapperFeature2);
        }
        this._deserializationConfig = without2;
        return this;
    }

    public ObjectMapper configure(SerializationFeature serializationFeature, boolean z) {
        SerializationFeature serializationFeature2 = serializationFeature;
        this._serializationConfig = z ? this._serializationConfig.with(serializationFeature2) : this._serializationConfig.without(serializationFeature2);
        return this;
    }

    public ObjectMapper configure(DeserializationFeature deserializationFeature, boolean z) {
        DeserializationFeature deserializationFeature2 = deserializationFeature;
        this._deserializationConfig = z ? this._deserializationConfig.with(deserializationFeature2) : this._deserializationConfig.without(deserializationFeature2);
        return this;
    }

    public ObjectMapper configure(JsonParser.Feature feature, boolean z) {
        JsonFactory configure = this._jsonFactory.configure(feature, z);
        return this;
    }

    public ObjectMapper configure(JsonGenerator.Feature feature, boolean z) {
        JsonFactory configure = this._jsonFactory.configure(feature, z);
        return this;
    }

    public ObjectMapper enable(MapperFeature... mapperFeatureArr) {
        MapperFeature[] mapperFeatureArr2 = mapperFeatureArr;
        this._deserializationConfig = this._deserializationConfig.with(mapperFeatureArr2);
        this._serializationConfig = this._serializationConfig.with(mapperFeatureArr2);
        return this;
    }

    public ObjectMapper disable(MapperFeature... mapperFeatureArr) {
        MapperFeature[] mapperFeatureArr2 = mapperFeatureArr;
        this._deserializationConfig = this._deserializationConfig.without(mapperFeatureArr2);
        this._serializationConfig = this._serializationConfig.without(mapperFeatureArr2);
        return this;
    }

    public ObjectMapper enable(DeserializationFeature deserializationFeature) {
        this._deserializationConfig = this._deserializationConfig.with(deserializationFeature);
        return this;
    }

    public ObjectMapper enable(DeserializationFeature deserializationFeature, DeserializationFeature... deserializationFeatureArr) {
        this._deserializationConfig = this._deserializationConfig.with(deserializationFeature, deserializationFeatureArr);
        return this;
    }

    public ObjectMapper disable(DeserializationFeature deserializationFeature) {
        this._deserializationConfig = this._deserializationConfig.without(deserializationFeature);
        return this;
    }

    public ObjectMapper disable(DeserializationFeature deserializationFeature, DeserializationFeature... deserializationFeatureArr) {
        this._deserializationConfig = this._deserializationConfig.without(deserializationFeature, deserializationFeatureArr);
        return this;
    }

    public ObjectMapper enable(SerializationFeature serializationFeature) {
        this._serializationConfig = this._serializationConfig.with(serializationFeature);
        return this;
    }

    public ObjectMapper enable(SerializationFeature serializationFeature, SerializationFeature... serializationFeatureArr) {
        this._serializationConfig = this._serializationConfig.with(serializationFeature, serializationFeatureArr);
        return this;
    }

    public ObjectMapper disable(SerializationFeature serializationFeature) {
        this._serializationConfig = this._serializationConfig.without(serializationFeature);
        return this;
    }

    public ObjectMapper disable(SerializationFeature serializationFeature, SerializationFeature... serializationFeatureArr) {
        this._serializationConfig = this._serializationConfig.without(serializationFeature, serializationFeatureArr);
        return this;
    }

    public boolean isEnabled(MapperFeature mapperFeature) {
        return this._serializationConfig.isEnabled(mapperFeature);
    }

    public boolean isEnabled(SerializationFeature serializationFeature) {
        return this._serializationConfig.isEnabled(serializationFeature);
    }

    public boolean isEnabled(DeserializationFeature deserializationFeature) {
        return this._deserializationConfig.isEnabled(deserializationFeature);
    }

    public boolean isEnabled(JsonFactory.Feature feature) {
        return this._jsonFactory.isEnabled(feature);
    }

    public boolean isEnabled(JsonParser.Feature feature) {
        return this._jsonFactory.isEnabled(feature);
    }

    public boolean isEnabled(JsonGenerator.Feature feature) {
        return this._jsonFactory.isEnabled(feature);
    }

    public JsonNodeFactory getNodeFactory() {
        return this._deserializationConfig.getNodeFactory();
    }

    public <T> T readValue(JsonParser jsonParser, Class<T> cls) throws IOException, JsonParseException, JsonMappingException {
        return _readValue(getDeserializationConfig(), jsonParser, this._typeFactory.constructType((Type) cls));
    }

    public <T> T readValue(JsonParser jsonParser, TypeReference<?> typeReference) throws IOException, JsonParseException, JsonMappingException {
        return _readValue(getDeserializationConfig(), jsonParser, this._typeFactory.constructType(typeReference));
    }

    public final <T> T readValue(JsonParser jsonParser, ResolvedType resolvedType) throws IOException, JsonParseException, JsonMappingException {
        return _readValue(getDeserializationConfig(), jsonParser, (JavaType) resolvedType);
    }

    public <T> T readValue(JsonParser jsonParser, JavaType javaType) throws IOException, JsonParseException, JsonMappingException {
        return _readValue(getDeserializationConfig(), jsonParser, javaType);
    }

    public <T extends TreeNode> T readTree(JsonParser jsonParser) throws IOException, JsonProcessingException {
        JsonParser jsonParser2 = jsonParser;
        DeserializationConfig deserializationConfig = getDeserializationConfig();
        if (jsonParser2.getCurrentToken() == null && jsonParser2.nextToken() == null) {
            return null;
        }
        T t = (JsonNode) _readValue(deserializationConfig, jsonParser2, JSON_NODE_TYPE);
        if (t == null) {
            t = getNodeFactory().nullNode();
        }
        return t;
    }

    public <T> MappingIterator<T> readValues(JsonParser jsonParser, ResolvedType resolvedType) throws IOException, JsonProcessingException {
        return readValues(jsonParser, (JavaType) resolvedType);
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> com.shaded.fasterxml.jackson.databind.MappingIterator<T> readValues(com.shaded.fasterxml.jackson.core.JsonParser r16, com.shaded.fasterxml.jackson.databind.JavaType r17) throws java.io.IOException, com.shaded.fasterxml.jackson.core.JsonProcessingException {
        /*
            r15 = this;
            r0 = r15
            r1 = r16
            r2 = r17
            r6 = r0
            com.shaded.fasterxml.jackson.databind.DeserializationConfig r6 = r6.getDeserializationConfig()
            r3 = r6
            r6 = r0
            r7 = r1
            r8 = r3
            com.shaded.fasterxml.jackson.databind.deser.DefaultDeserializationContext r6 = r6.createDeserializationContext(r7, r8)
            r4 = r6
            r6 = r0
            r7 = r4
            r8 = r2
            com.shaded.fasterxml.jackson.databind.JsonDeserializer r6 = r6._findRootDeserializer(r7, r8)
            r5 = r6
            com.shaded.fasterxml.jackson.databind.MappingIterator r6 = new com.shaded.fasterxml.jackson.databind.MappingIterator
            r14 = r6
            r6 = r14
            r7 = r14
            r8 = r2
            r9 = r1
            r10 = r4
            r11 = r5
            r12 = 0
            r13 = 0
            r7.<init>(r8, r9, r10, r11, r12, r13)
            r0 = r6
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.ObjectMapper.readValues(com.shaded.fasterxml.jackson.core.JsonParser, com.shaded.fasterxml.jackson.databind.JavaType):com.shaded.fasterxml.jackson.databind.MappingIterator");
    }

    public <T> MappingIterator<T> readValues(JsonParser jsonParser, Class<T> cls) throws IOException, JsonProcessingException {
        return readValues(jsonParser, this._typeFactory.constructType((Type) cls));
    }

    public <T> MappingIterator<T> readValues(JsonParser jsonParser, TypeReference<?> typeReference) throws IOException, JsonProcessingException {
        return readValues(jsonParser, this._typeFactory.constructType(typeReference));
    }

    public JsonNode readTree(InputStream inputStream) throws IOException, JsonProcessingException {
        JsonNode jsonNode = (JsonNode) _readMapAndClose(this._jsonFactory.createParser(inputStream), JSON_NODE_TYPE);
        return jsonNode == null ? NullNode.instance : jsonNode;
    }

    public JsonNode readTree(Reader reader) throws IOException, JsonProcessingException {
        JsonNode jsonNode = (JsonNode) _readMapAndClose(this._jsonFactory.createParser(reader), JSON_NODE_TYPE);
        return jsonNode == null ? NullNode.instance : jsonNode;
    }

    public JsonNode readTree(String str) throws IOException, JsonProcessingException {
        JsonNode jsonNode = (JsonNode) _readMapAndClose(this._jsonFactory.createParser(str), JSON_NODE_TYPE);
        return jsonNode == null ? NullNode.instance : jsonNode;
    }

    public JsonNode readTree(byte[] bArr) throws IOException, JsonProcessingException {
        JsonNode jsonNode = (JsonNode) _readMapAndClose(this._jsonFactory.createParser(bArr), JSON_NODE_TYPE);
        return jsonNode == null ? NullNode.instance : jsonNode;
    }

    public JsonNode readTree(File file) throws IOException, JsonProcessingException {
        JsonNode jsonNode = (JsonNode) _readMapAndClose(this._jsonFactory.createParser(file), JSON_NODE_TYPE);
        return jsonNode == null ? NullNode.instance : jsonNode;
    }

    public JsonNode readTree(URL url) throws IOException, JsonProcessingException {
        JsonNode jsonNode = (JsonNode) _readMapAndClose(this._jsonFactory.createParser(url), JSON_NODE_TYPE);
        return jsonNode == null ? NullNode.instance : jsonNode;
    }

    public void writeValue(JsonGenerator jsonGenerator, Object obj) throws IOException, JsonGenerationException, JsonMappingException {
        JsonGenerator jsonGenerator2 = jsonGenerator;
        Object obj2 = obj;
        SerializationConfig serializationConfig = getSerializationConfig();
        if (serializationConfig.isEnabled(SerializationFeature.INDENT_OUTPUT)) {
            JsonGenerator useDefaultPrettyPrinter = jsonGenerator2.useDefaultPrettyPrinter();
        }
        if (!serializationConfig.isEnabled(SerializationFeature.CLOSE_CLOSEABLE) || !(obj2 instanceof Closeable)) {
            _serializerProvider(serializationConfig).serializeValue(jsonGenerator2, obj2);
            if (serializationConfig.isEnabled(SerializationFeature.FLUSH_AFTER_WRITE_VALUE)) {
                jsonGenerator2.flush();
                return;
            }
            return;
        }
        _writeCloseableValue(jsonGenerator2, obj2, serializationConfig);
    }

    public void writeTree(JsonGenerator jsonGenerator, JsonNode jsonNode) throws IOException, JsonProcessingException {
        JsonGenerator jsonGenerator2 = jsonGenerator;
        SerializationConfig serializationConfig = getSerializationConfig();
        _serializerProvider(serializationConfig).serializeValue(jsonGenerator2, jsonNode);
        if (serializationConfig.isEnabled(SerializationFeature.FLUSH_AFTER_WRITE_VALUE)) {
            jsonGenerator2.flush();
        }
    }

    public ObjectNode createObjectNode() {
        return this._deserializationConfig.getNodeFactory().objectNode();
    }

    public ArrayNode createArrayNode() {
        return this._deserializationConfig.getNodeFactory().arrayNode();
    }

    public JsonParser treeAsTokens(TreeNode treeNode) {
        JsonParser jsonParser;
        new TreeTraversingParser((JsonNode) treeNode, this);
        return jsonParser;
    }

    public <T> T treeToValue(TreeNode treeNode, Class<T> cls) throws JsonProcessingException {
        Throwable th;
        TreeNode treeNode2 = treeNode;
        Class<T> cls2 = cls;
        if (cls2 != Object.class) {
            try {
                if (cls2.isAssignableFrom(treeNode2.getClass())) {
                    return treeNode2;
                }
            } catch (JsonProcessingException e) {
                throw e;
            } catch (IOException e2) {
                IOException iOException = e2;
                Throwable th2 = th;
                new IllegalArgumentException(iOException.getMessage(), iOException);
                throw th2;
            }
        }
        return readValue(treeAsTokens(treeNode2), cls2);
    }

    public <T extends JsonNode> T valueToTree(Object obj) throws IllegalArgumentException {
        TokenBuffer tokenBuffer;
        Throwable th;
        Object obj2 = obj;
        if (obj2 == null) {
            return null;
        }
        new TokenBuffer(this);
        TokenBuffer tokenBuffer2 = tokenBuffer;
        try {
            writeValue((JsonGenerator) tokenBuffer2, obj2);
            JsonParser asParser = tokenBuffer2.asParser();
            T t = (JsonNode) readTree(asParser);
            asParser.close();
            return t;
        } catch (IOException e) {
            IOException iOException = e;
            Throwable th2 = th;
            new IllegalArgumentException(iOException.getMessage(), iOException);
            throw th2;
        }
    }

    public boolean canSerialize(Class<?> cls) {
        return _serializerProvider(getSerializationConfig()).hasSerializerFor(cls);
    }

    public boolean canDeserialize(JavaType javaType) {
        return createDeserializationContext((JsonParser) null, getDeserializationConfig()).hasValueDeserializerFor(javaType);
    }

    public <T> T readValue(File file, Class<T> cls) throws IOException, JsonParseException, JsonMappingException {
        return _readMapAndClose(this._jsonFactory.createParser(file), this._typeFactory.constructType((Type) cls));
    }

    public <T> T readValue(File file, TypeReference typeReference) throws IOException, JsonParseException, JsonMappingException {
        return _readMapAndClose(this._jsonFactory.createParser(file), this._typeFactory.constructType((TypeReference<?>) typeReference));
    }

    public <T> T readValue(File file, JavaType javaType) throws IOException, JsonParseException, JsonMappingException {
        return _readMapAndClose(this._jsonFactory.createParser(file), javaType);
    }

    public <T> T readValue(URL url, Class<T> cls) throws IOException, JsonParseException, JsonMappingException {
        return _readMapAndClose(this._jsonFactory.createParser(url), this._typeFactory.constructType((Type) cls));
    }

    public <T> T readValue(URL url, TypeReference typeReference) throws IOException, JsonParseException, JsonMappingException {
        return _readMapAndClose(this._jsonFactory.createParser(url), this._typeFactory.constructType((TypeReference<?>) typeReference));
    }

    public <T> T readValue(URL url, JavaType javaType) throws IOException, JsonParseException, JsonMappingException {
        return _readMapAndClose(this._jsonFactory.createParser(url), javaType);
    }

    public <T> T readValue(String str, Class<T> cls) throws IOException, JsonParseException, JsonMappingException {
        return _readMapAndClose(this._jsonFactory.createParser(str), this._typeFactory.constructType((Type) cls));
    }

    public <T> T readValue(String str, TypeReference typeReference) throws IOException, JsonParseException, JsonMappingException {
        return _readMapAndClose(this._jsonFactory.createParser(str), this._typeFactory.constructType((TypeReference<?>) typeReference));
    }

    public <T> T readValue(String str, JavaType javaType) throws IOException, JsonParseException, JsonMappingException {
        return _readMapAndClose(this._jsonFactory.createParser(str), javaType);
    }

    public <T> T readValue(Reader reader, Class<T> cls) throws IOException, JsonParseException, JsonMappingException {
        return _readMapAndClose(this._jsonFactory.createParser(reader), this._typeFactory.constructType((Type) cls));
    }

    public <T> T readValue(Reader reader, TypeReference typeReference) throws IOException, JsonParseException, JsonMappingException {
        return _readMapAndClose(this._jsonFactory.createParser(reader), this._typeFactory.constructType((TypeReference<?>) typeReference));
    }

    public <T> T readValue(Reader reader, JavaType javaType) throws IOException, JsonParseException, JsonMappingException {
        return _readMapAndClose(this._jsonFactory.createParser(reader), javaType);
    }

    public <T> T readValue(InputStream inputStream, Class<T> cls) throws IOException, JsonParseException, JsonMappingException {
        return _readMapAndClose(this._jsonFactory.createParser(inputStream), this._typeFactory.constructType((Type) cls));
    }

    public <T> T readValue(InputStream inputStream, TypeReference typeReference) throws IOException, JsonParseException, JsonMappingException {
        return _readMapAndClose(this._jsonFactory.createParser(inputStream), this._typeFactory.constructType((TypeReference<?>) typeReference));
    }

    public <T> T readValue(InputStream inputStream, JavaType javaType) throws IOException, JsonParseException, JsonMappingException {
        return _readMapAndClose(this._jsonFactory.createParser(inputStream), javaType);
    }

    public <T> T readValue(byte[] bArr, Class<T> cls) throws IOException, JsonParseException, JsonMappingException {
        return _readMapAndClose(this._jsonFactory.createParser(bArr), this._typeFactory.constructType((Type) cls));
    }

    public <T> T readValue(byte[] bArr, int i, int i2, Class<T> cls) throws IOException, JsonParseException, JsonMappingException {
        return _readMapAndClose(this._jsonFactory.createParser(bArr, i, i2), this._typeFactory.constructType((Type) cls));
    }

    public <T> T readValue(byte[] bArr, TypeReference typeReference) throws IOException, JsonParseException, JsonMappingException {
        return _readMapAndClose(this._jsonFactory.createParser(bArr), this._typeFactory.constructType((TypeReference<?>) typeReference));
    }

    public <T> T readValue(byte[] bArr, int i, int i2, TypeReference typeReference) throws IOException, JsonParseException, JsonMappingException {
        return _readMapAndClose(this._jsonFactory.createParser(bArr, i, i2), this._typeFactory.constructType((TypeReference<?>) typeReference));
    }

    public <T> T readValue(byte[] bArr, JavaType javaType) throws IOException, JsonParseException, JsonMappingException {
        return _readMapAndClose(this._jsonFactory.createParser(bArr), javaType);
    }

    public <T> T readValue(byte[] bArr, int i, int i2, JavaType javaType) throws IOException, JsonParseException, JsonMappingException {
        return _readMapAndClose(this._jsonFactory.createParser(bArr, i, i2), javaType);
    }

    public void writeValue(File file, Object obj) throws IOException, JsonGenerationException, JsonMappingException {
        _configAndWriteValue(this._jsonFactory.createGenerator(file, JsonEncoding.UTF8), obj);
    }

    public void writeValue(OutputStream outputStream, Object obj) throws IOException, JsonGenerationException, JsonMappingException {
        _configAndWriteValue(this._jsonFactory.createGenerator(outputStream, JsonEncoding.UTF8), obj);
    }

    public void writeValue(Writer writer, Object obj) throws IOException, JsonGenerationException, JsonMappingException {
        _configAndWriteValue(this._jsonFactory.createGenerator(writer), obj);
    }

    public String writeValueAsString(Object obj) throws JsonProcessingException {
        SegmentedStringWriter segmentedStringWriter;
        Object obj2 = obj;
        new SegmentedStringWriter(this._jsonFactory._getBufferRecycler());
        SegmentedStringWriter segmentedStringWriter2 = segmentedStringWriter;
        try {
            _configAndWriteValue(this._jsonFactory.createGenerator((Writer) segmentedStringWriter2), obj2);
            return segmentedStringWriter2.getAndClear();
        } catch (JsonProcessingException e) {
            throw e;
        } catch (IOException e2) {
            throw JsonMappingException.fromUnexpectedIOE(e2);
        }
    }

    public byte[] writeValueAsBytes(Object obj) throws JsonProcessingException {
        ByteArrayBuilder byteArrayBuilder;
        Object obj2 = obj;
        new ByteArrayBuilder(this._jsonFactory._getBufferRecycler());
        ByteArrayBuilder byteArrayBuilder2 = byteArrayBuilder;
        try {
            _configAndWriteValue(this._jsonFactory.createGenerator((OutputStream) byteArrayBuilder2, JsonEncoding.UTF8), obj2);
            byte[] byteArray = byteArrayBuilder2.toByteArray();
            byteArrayBuilder2.release();
            return byteArray;
        } catch (JsonProcessingException e) {
            throw e;
        } catch (IOException e2) {
            throw JsonMappingException.fromUnexpectedIOE(e2);
        }
    }

    public ObjectWriter writer() {
        ObjectWriter objectWriter;
        new ObjectWriter(this, getSerializationConfig());
        return objectWriter;
    }

    public ObjectWriter writer(SerializationFeature serializationFeature) {
        ObjectWriter objectWriter;
        new ObjectWriter(this, getSerializationConfig().with(serializationFeature));
        return objectWriter;
    }

    public ObjectWriter writer(SerializationFeature serializationFeature, SerializationFeature... serializationFeatureArr) {
        ObjectWriter objectWriter;
        new ObjectWriter(this, getSerializationConfig().with(serializationFeature, serializationFeatureArr));
        return objectWriter;
    }

    public ObjectWriter writer(DateFormat dateFormat) {
        ObjectWriter objectWriter;
        new ObjectWriter(this, getSerializationConfig().with(dateFormat));
        return objectWriter;
    }

    public ObjectWriter writerWithView(Class<?> cls) {
        ObjectWriter objectWriter;
        new ObjectWriter(this, getSerializationConfig().withView(cls));
        return objectWriter;
    }

    public ObjectWriter writerWithType(Class<?> cls) {
        Class<?> cls2 = cls;
        ObjectWriter objectWriter = r8;
        ObjectWriter objectWriter2 = new ObjectWriter(this, getSerializationConfig(), cls2 == null ? null : this._typeFactory.constructType((Type) cls2), (PrettyPrinter) null);
        return objectWriter;
    }

    public ObjectWriter writerWithType(TypeReference<?> typeReference) {
        TypeReference<?> typeReference2 = typeReference;
        ObjectWriter objectWriter = r8;
        ObjectWriter objectWriter2 = new ObjectWriter(this, getSerializationConfig(), typeReference2 == null ? null : this._typeFactory.constructType(typeReference2), (PrettyPrinter) null);
        return objectWriter;
    }

    public ObjectWriter writerWithType(JavaType javaType) {
        ObjectWriter objectWriter;
        new ObjectWriter(this, getSerializationConfig(), javaType, (PrettyPrinter) null);
        return objectWriter;
    }

    public ObjectWriter writer(PrettyPrinter prettyPrinter) {
        ObjectWriter objectWriter;
        PrettyPrinter prettyPrinter2 = prettyPrinter;
        if (prettyPrinter2 == null) {
            prettyPrinter2 = ObjectWriter.NULL_PRETTY_PRINTER;
        }
        new ObjectWriter(this, getSerializationConfig(), (JavaType) null, prettyPrinter2);
        return objectWriter;
    }

    public ObjectWriter writerWithDefaultPrettyPrinter() {
        ObjectWriter objectWriter;
        new ObjectWriter(this, getSerializationConfig(), (JavaType) null, _defaultPrettyPrinter());
        return objectWriter;
    }

    public ObjectWriter writer(FilterProvider filterProvider) {
        ObjectWriter objectWriter;
        new ObjectWriter(this, getSerializationConfig().withFilters(filterProvider));
        return objectWriter;
    }

    public ObjectWriter writer(FormatSchema formatSchema) {
        ObjectWriter objectWriter;
        FormatSchema formatSchema2 = formatSchema;
        _verifySchemaType(formatSchema2);
        new ObjectWriter(this, getSerializationConfig(), formatSchema2);
        return objectWriter;
    }

    public ObjectWriter writer(Base64Variant base64Variant) {
        ObjectWriter objectWriter;
        new ObjectWriter(this, getSerializationConfig().with(base64Variant));
        return objectWriter;
    }

    public ObjectReader reader() {
        ObjectReader objectReader;
        new ObjectReader(this, getDeserializationConfig());
        return objectReader.with(this._injectableValues);
    }

    public ObjectReader reader(DeserializationFeature deserializationFeature) {
        ObjectReader objectReader;
        new ObjectReader(this, getDeserializationConfig().with(deserializationFeature));
        return objectReader;
    }

    public ObjectReader reader(DeserializationFeature deserializationFeature, DeserializationFeature... deserializationFeatureArr) {
        ObjectReader objectReader;
        new ObjectReader(this, getDeserializationConfig().with(deserializationFeature, deserializationFeatureArr));
        return objectReader;
    }

    public ObjectReader readerForUpdating(Object obj) {
        ObjectReader objectReader;
        Object obj2 = obj;
        new ObjectReader(this, getDeserializationConfig(), this._typeFactory.constructType((Type) obj2.getClass()), obj2, (FormatSchema) null, this._injectableValues);
        return objectReader;
    }

    public ObjectReader reader(JavaType javaType) {
        ObjectReader objectReader;
        new ObjectReader(this, getDeserializationConfig(), javaType, (Object) null, (FormatSchema) null, this._injectableValues);
        return objectReader;
    }

    public ObjectReader reader(Class<?> cls) {
        return reader(this._typeFactory.constructType((Type) cls));
    }

    public ObjectReader reader(TypeReference<?> typeReference) {
        return reader(this._typeFactory.constructType(typeReference));
    }

    public ObjectReader reader(JsonNodeFactory jsonNodeFactory) {
        ObjectReader objectReader;
        new ObjectReader(this, getDeserializationConfig());
        return objectReader.with(jsonNodeFactory);
    }

    public ObjectReader reader(FormatSchema formatSchema) {
        ObjectReader objectReader;
        FormatSchema formatSchema2 = formatSchema;
        _verifySchemaType(formatSchema2);
        new ObjectReader(this, getDeserializationConfig(), (JavaType) null, (Object) null, formatSchema2, this._injectableValues);
        return objectReader;
    }

    public ObjectReader reader(InjectableValues injectableValues) {
        ObjectReader objectReader;
        new ObjectReader(this, getDeserializationConfig(), (JavaType) null, (Object) null, (FormatSchema) null, injectableValues);
        return objectReader;
    }

    public ObjectReader readerWithView(Class<?> cls) {
        ObjectReader objectReader;
        new ObjectReader(this, getDeserializationConfig().withView(cls));
        return objectReader;
    }

    public ObjectReader reader(Base64Variant base64Variant) {
        ObjectReader objectReader;
        new ObjectReader(this, getDeserializationConfig().with(base64Variant));
        return objectReader;
    }

    public <T> T convertValue(Object obj, Class<T> cls) throws IllegalArgumentException {
        Object obj2 = obj;
        Class<T> cls2 = cls;
        if (obj2 == null) {
            return null;
        }
        return _convert(obj2, this._typeFactory.constructType((Type) cls2));
    }

    public <T> T convertValue(Object obj, TypeReference<?> typeReference) throws IllegalArgumentException {
        return convertValue(obj, this._typeFactory.constructType(typeReference));
    }

    public <T> T convertValue(Object obj, JavaType javaType) throws IllegalArgumentException {
        Object obj2 = obj;
        JavaType javaType2 = javaType;
        if (obj2 == null) {
            return null;
        }
        return _convert(obj2, javaType2);
    }

    /* access modifiers changed from: protected */
    public Object _convert(Object obj, JavaType javaType) throws IllegalArgumentException {
        TokenBuffer tokenBuffer;
        Throwable th;
        Object obj2;
        Object obj3 = obj;
        JavaType javaType2 = javaType;
        Class<?> rawClass = javaType2.getRawClass();
        if (rawClass != Object.class && !javaType2.hasGenericTypes() && rawClass.isAssignableFrom(obj3.getClass())) {
            return obj3;
        }
        new TokenBuffer(this);
        TokenBuffer tokenBuffer2 = tokenBuffer;
        try {
            _serializerProvider(getSerializationConfig().without(SerializationFeature.WRAP_ROOT_VALUE)).serializeValue(tokenBuffer2, obj3);
            JsonParser asParser = tokenBuffer2.asParser();
            DeserializationConfig deserializationConfig = getDeserializationConfig();
            JsonToken _initForReading = _initForReading(asParser);
            if (_initForReading == JsonToken.VALUE_NULL) {
                obj2 = _findRootDeserializer(createDeserializationContext(asParser, deserializationConfig), javaType2).getNullValue();
            } else if (_initForReading == JsonToken.END_ARRAY || _initForReading == JsonToken.END_OBJECT) {
                obj2 = null;
            } else {
                DefaultDeserializationContext createDeserializationContext = createDeserializationContext(asParser, deserializationConfig);
                obj2 = _findRootDeserializer(createDeserializationContext, javaType2).deserialize(asParser, createDeserializationContext);
            }
            asParser.close();
            return obj2;
        } catch (IOException e) {
            IOException iOException = e;
            Throwable th2 = th;
            new IllegalArgumentException(iOException.getMessage(), iOException);
            throw th2;
        }
    }

    public JsonSchema generateJsonSchema(Class<?> cls) throws JsonMappingException {
        return _serializerProvider(getSerializationConfig()).generateJsonSchema(cls);
    }

    public void acceptJsonFormatVisitor(Class<?> cls, JsonFormatVisitorWrapper jsonFormatVisitorWrapper) throws JsonMappingException {
        acceptJsonFormatVisitor(this._typeFactory.constructType((Type) cls), jsonFormatVisitorWrapper);
    }

    public void acceptJsonFormatVisitor(JavaType javaType, JsonFormatVisitorWrapper jsonFormatVisitorWrapper) throws JsonMappingException {
        Throwable th;
        JavaType javaType2 = javaType;
        JsonFormatVisitorWrapper jsonFormatVisitorWrapper2 = jsonFormatVisitorWrapper;
        if (javaType2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("type must be provided");
            throw th2;
        }
        _serializerProvider(getSerializationConfig()).acceptJsonFormatVisitor(javaType2, jsonFormatVisitorWrapper2);
    }

    /* access modifiers changed from: protected */
    public DefaultSerializerProvider _serializerProvider(SerializationConfig serializationConfig) {
        return this._serializerProvider.createInstance(serializationConfig, this._serializerFactory);
    }

    /* access modifiers changed from: protected */
    public PrettyPrinter _defaultPrettyPrinter() {
        return _defaultPrettyPrinter;
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    public final void _configAndWriteValue(JsonGenerator jsonGenerator, Object obj) throws IOException, JsonGenerationException, JsonMappingException {
        JsonGenerator jsonGenerator2 = jsonGenerator;
        Object obj2 = obj;
        SerializationConfig serializationConfig = getSerializationConfig();
        if (serializationConfig.isEnabled(SerializationFeature.INDENT_OUTPUT)) {
            JsonGenerator useDefaultPrettyPrinter = jsonGenerator2.useDefaultPrettyPrinter();
        }
        if (!serializationConfig.isEnabled(SerializationFeature.CLOSE_CLOSEABLE) || !(obj2 instanceof Closeable)) {
            boolean z = false;
            try {
                _serializerProvider(serializationConfig).serializeValue(jsonGenerator2, obj2);
                z = true;
                jsonGenerator2.close();
                if (1 == 0) {
                    try {
                        jsonGenerator2.close();
                    } catch (IOException e) {
                        IOException iOException = e;
                    }
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                if (!z) {
                    try {
                        jsonGenerator2.close();
                    } catch (IOException e2) {
                        IOException iOException2 = e2;
                    }
                }
                throw th2;
            }
        } else {
            _configAndWriteCloseable(jsonGenerator2, obj2, serializationConfig);
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    public final void _configAndWriteValue(JsonGenerator jsonGenerator, Object obj, Class<?> cls) throws IOException, JsonGenerationException, JsonMappingException {
        JsonGenerator jsonGenerator2 = jsonGenerator;
        Object obj2 = obj;
        SerializationConfig withView = getSerializationConfig().withView(cls);
        if (withView.isEnabled(SerializationFeature.INDENT_OUTPUT)) {
            JsonGenerator useDefaultPrettyPrinter = jsonGenerator2.useDefaultPrettyPrinter();
        }
        if (!withView.isEnabled(SerializationFeature.CLOSE_CLOSEABLE) || !(obj2 instanceof Closeable)) {
            boolean z = false;
            try {
                _serializerProvider(withView).serializeValue(jsonGenerator2, obj2);
                z = true;
                jsonGenerator2.close();
                if (1 == 0) {
                    try {
                        jsonGenerator2.close();
                    } catch (IOException e) {
                        IOException iOException = e;
                    }
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                if (!z) {
                    try {
                        jsonGenerator2.close();
                    } catch (IOException e2) {
                        IOException iOException2 = e2;
                    }
                }
                throw th2;
            }
        } else {
            _configAndWriteCloseable(jsonGenerator2, obj2, withView);
        }
    }

    /* JADX INFO: finally extract failed */
    private final void _configAndWriteCloseable(JsonGenerator jsonGenerator, Object obj, SerializationConfig serializationConfig) throws IOException, JsonGenerationException, JsonMappingException {
        JsonGenerator jsonGenerator2 = jsonGenerator;
        Object obj2 = obj;
        Closeable closeable = (Closeable) obj2;
        try {
            _serializerProvider(serializationConfig).serializeValue(jsonGenerator2, obj2);
            jsonGenerator2 = null;
            jsonGenerator2.close();
            closeable = null;
            closeable.close();
            if (0 != 0) {
                JsonGenerator jsonGenerator3 = null;
                try {
                    jsonGenerator3.close();
                } catch (IOException e) {
                    IOException iOException = e;
                }
            }
            if (0 != 0) {
                Closeable closeable2 = null;
                try {
                    closeable2.close();
                } catch (IOException e2) {
                    IOException iOException2 = e2;
                }
            }
        } catch (Throwable th) {
            Throwable th2 = th;
            if (jsonGenerator2 != null) {
                try {
                    jsonGenerator2.close();
                } catch (IOException e3) {
                    IOException iOException3 = e3;
                }
            }
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e4) {
                    IOException iOException4 = e4;
                }
            }
            throw th2;
        }
    }

    /* JADX INFO: finally extract failed */
    private final void _writeCloseableValue(JsonGenerator jsonGenerator, Object obj, SerializationConfig serializationConfig) throws IOException, JsonGenerationException, JsonMappingException {
        JsonGenerator jsonGenerator2 = jsonGenerator;
        Object obj2 = obj;
        SerializationConfig serializationConfig2 = serializationConfig;
        Closeable closeable = (Closeable) obj2;
        try {
            _serializerProvider(serializationConfig2).serializeValue(jsonGenerator2, obj2);
            if (serializationConfig2.isEnabled(SerializationFeature.FLUSH_AFTER_WRITE_VALUE)) {
                jsonGenerator2.flush();
            }
            closeable.close();
            if (0 != 0) {
                Closeable closeable2 = null;
                try {
                    closeable2.close();
                } catch (IOException e) {
                    IOException iOException = e;
                }
            }
        } catch (Throwable th) {
            Throwable th2 = th;
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e2) {
                    IOException iOException2 = e2;
                }
            }
            throw th2;
        }
    }

    /* access modifiers changed from: protected */
    public DefaultDeserializationContext createDeserializationContext(JsonParser jsonParser, DeserializationConfig deserializationConfig) {
        return this._deserializationContext.createInstance(deserializationConfig, jsonParser, this._injectableValues);
    }

    /* access modifiers changed from: protected */
    public Object _readValue(DeserializationConfig deserializationConfig, JsonParser jsonParser, JavaType javaType) throws IOException, JsonParseException, JsonMappingException {
        Object obj;
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        JsonParser jsonParser2 = jsonParser;
        JavaType javaType2 = javaType;
        JsonToken _initForReading = _initForReading(jsonParser2);
        if (_initForReading == JsonToken.VALUE_NULL) {
            obj = _findRootDeserializer(createDeserializationContext(jsonParser2, deserializationConfig2), javaType2).getNullValue();
        } else if (_initForReading == JsonToken.END_ARRAY || _initForReading == JsonToken.END_OBJECT) {
            obj = null;
        } else {
            DefaultDeserializationContext createDeserializationContext = createDeserializationContext(jsonParser2, deserializationConfig2);
            JsonDeserializer<Object> _findRootDeserializer = _findRootDeserializer(createDeserializationContext, javaType2);
            if (deserializationConfig2.useRootWrapping()) {
                obj = _unwrapAndDeserialize(jsonParser2, createDeserializationContext, deserializationConfig2, javaType2, _findRootDeserializer);
            } else {
                obj = _findRootDeserializer.deserialize(jsonParser2, createDeserializationContext);
            }
        }
        jsonParser2.clearCurrentToken();
        return obj;
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    public Object _readMapAndClose(JsonParser jsonParser, JavaType javaType) throws IOException, JsonParseException, JsonMappingException {
        Object obj;
        JsonParser jsonParser2 = jsonParser;
        JavaType javaType2 = javaType;
        try {
            JsonToken _initForReading = _initForReading(jsonParser2);
            if (_initForReading == JsonToken.VALUE_NULL) {
                obj = _findRootDeserializer(createDeserializationContext(jsonParser2, getDeserializationConfig()), javaType2).getNullValue();
            } else if (_initForReading == JsonToken.END_ARRAY || _initForReading == JsonToken.END_OBJECT) {
                obj = null;
            } else {
                DeserializationConfig deserializationConfig = getDeserializationConfig();
                DefaultDeserializationContext createDeserializationContext = createDeserializationContext(jsonParser2, deserializationConfig);
                JsonDeserializer<Object> _findRootDeserializer = _findRootDeserializer(createDeserializationContext, javaType2);
                if (deserializationConfig.useRootWrapping()) {
                    obj = _unwrapAndDeserialize(jsonParser2, createDeserializationContext, deserializationConfig, javaType2, _findRootDeserializer);
                } else {
                    obj = _findRootDeserializer.deserialize(jsonParser2, createDeserializationContext);
                }
            }
            jsonParser2.clearCurrentToken();
            Object obj2 = obj;
            try {
                jsonParser2.close();
            } catch (IOException e) {
                IOException iOException = e;
            }
            return obj2;
        } catch (Throwable th) {
            Throwable th2 = th;
            try {
                jsonParser2.close();
            } catch (IOException e2) {
                IOException iOException2 = e2;
            }
            throw th2;
        }
    }

    /* access modifiers changed from: protected */
    public JsonToken _initForReading(JsonParser jsonParser) throws IOException, JsonParseException, JsonMappingException {
        JsonParser jsonParser2 = jsonParser;
        JsonToken currentToken = jsonParser2.getCurrentToken();
        if (currentToken == null) {
            currentToken = jsonParser2.nextToken();
            if (currentToken == null) {
                throw JsonMappingException.from(jsonParser2, "No content to map due to end-of-input");
            }
        }
        return currentToken;
    }

    /* access modifiers changed from: protected */
    public Object _unwrapAndDeserialize(JsonParser jsonParser, DeserializationContext deserializationContext, DeserializationConfig deserializationConfig, JavaType javaType, JsonDeserializer<Object> jsonDeserializer) throws IOException, JsonParseException, JsonMappingException {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        JsonParser jsonParser2 = jsonParser;
        DeserializationContext deserializationContext2 = deserializationContext;
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        JavaType javaType2 = javaType;
        JsonDeserializer<Object> jsonDeserializer2 = jsonDeserializer;
        String rootName = deserializationConfig2.getRootName();
        if (rootName == null) {
            rootName = this._rootNames.findRootName(javaType2, (MapperConfig<?>) deserializationConfig2).getValue();
        }
        if (jsonParser2.getCurrentToken() != JsonToken.START_OBJECT) {
            new StringBuilder();
            throw JsonMappingException.from(jsonParser2, sb4.append("Current token not START_OBJECT (needed to unwrap root name '").append(rootName).append("'), but ").append(jsonParser2.getCurrentToken()).toString());
        } else if (jsonParser2.nextToken() != JsonToken.FIELD_NAME) {
            new StringBuilder();
            throw JsonMappingException.from(jsonParser2, sb3.append("Current token not FIELD_NAME (to contain expected root name '").append(rootName).append("'), but ").append(jsonParser2.getCurrentToken()).toString());
        } else {
            String currentName = jsonParser2.getCurrentName();
            if (!rootName.equals(currentName)) {
                new StringBuilder();
                throw JsonMappingException.from(jsonParser2, sb2.append("Root name '").append(currentName).append("' does not match expected ('").append(rootName).append("') for type ").append(javaType2).toString());
            }
            JsonToken nextToken = jsonParser2.nextToken();
            Object deserialize = jsonDeserializer2.deserialize(jsonParser2, deserializationContext2);
            if (jsonParser2.nextToken() == JsonToken.END_OBJECT) {
                return deserialize;
            }
            new StringBuilder();
            throw JsonMappingException.from(jsonParser2, sb.append("Current token not END_OBJECT (to match wrapper object with root name '").append(rootName).append("'), but ").append(jsonParser2.getCurrentToken()).toString());
        }
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<Object> _findRootDeserializer(DeserializationContext deserializationContext, JavaType javaType) throws JsonMappingException {
        Throwable th;
        StringBuilder sb;
        DeserializationContext deserializationContext2 = deserializationContext;
        JavaType javaType2 = javaType;
        JsonDeserializer<Object> jsonDeserializer = this._rootDeserializers.get(javaType2);
        if (jsonDeserializer != null) {
            return jsonDeserializer;
        }
        JsonDeserializer<Object> findRootValueDeserializer = deserializationContext2.findRootValueDeserializer(javaType2);
        if (findRootValueDeserializer == null) {
            Throwable th2 = th;
            new StringBuilder();
            new JsonMappingException(sb.append("Can not find a deserializer for type ").append(javaType2).toString());
            throw th2;
        }
        JsonDeserializer<Object> put = this._rootDeserializers.put(javaType2, findRootValueDeserializer);
        return findRootValueDeserializer;
    }

    /* access modifiers changed from: protected */
    public void _verifySchemaType(FormatSchema formatSchema) {
        Throwable th;
        StringBuilder sb;
        FormatSchema formatSchema2 = formatSchema;
        if (formatSchema2 != null && !this._jsonFactory.canUseSchema(formatSchema2)) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Can not use FormatSchema of type ").append(formatSchema2.getClass().getName()).append(" for format ").append(this._jsonFactory.getFormatName()).toString());
            throw th2;
        }
    }
}

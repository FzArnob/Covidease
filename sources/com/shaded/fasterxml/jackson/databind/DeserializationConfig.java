package com.shaded.fasterxml.jackson.databind;

import com.shaded.fasterxml.jackson.annotation.JsonAutoDetect;
import com.shaded.fasterxml.jackson.annotation.PropertyAccessor;
import com.shaded.fasterxml.jackson.core.Base64Variant;
import com.shaded.fasterxml.jackson.databind.cfg.BaseSettings;
import com.shaded.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.shaded.fasterxml.jackson.databind.cfg.MapperConfigBase;
import com.shaded.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.shaded.fasterxml.jackson.databind.introspect.ClassIntrospector;
import com.shaded.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;
import com.shaded.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.shaded.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.shaded.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.shaded.fasterxml.jackson.databind.type.ClassKey;
import com.shaded.fasterxml.jackson.databind.type.TypeFactory;
import com.shaded.fasterxml.jackson.databind.util.LinkedNode;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public final class DeserializationConfig extends MapperConfigBase<DeserializationFeature, DeserializationConfig> implements Serializable {
    private static final long serialVersionUID = -4227480407273773599L;
    protected final int _deserFeatures;
    protected final JsonNodeFactory _nodeFactory;
    protected final LinkedNode<DeserializationProblemHandler> _problemHandlers;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DeserializationConfig(BaseSettings baseSettings, SubtypeResolver subtypeResolver, Map<ClassKey, Class<?>> map) {
        super(baseSettings, subtypeResolver, map);
        this._deserFeatures = collectFeatureDefaults(DeserializationFeature.class);
        this._nodeFactory = JsonNodeFactory.instance;
        this._problemHandlers = null;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private DeserializationConfig(com.shaded.fasterxml.jackson.databind.DeserializationConfig r7, com.shaded.fasterxml.jackson.databind.jsontype.SubtypeResolver r8) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r8
            r3 = r0
            r4 = r1
            r5 = r2
            r3.<init>(r4, (com.shaded.fasterxml.jackson.databind.jsontype.SubtypeResolver) r5)
            r3 = r0
            r4 = r1
            int r4 = r4._deserFeatures
            r3._deserFeatures = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.node.JsonNodeFactory r4 = r4._nodeFactory
            r3._nodeFactory = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.util.LinkedNode<com.shaded.fasterxml.jackson.databind.deser.DeserializationProblemHandler> r4 = r4._problemHandlers
            r3._problemHandlers = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.DeserializationConfig.<init>(com.shaded.fasterxml.jackson.databind.DeserializationConfig, com.shaded.fasterxml.jackson.databind.jsontype.SubtypeResolver):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private DeserializationConfig(com.shaded.fasterxml.jackson.databind.DeserializationConfig r8, int r9, int r10) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r9
            r3 = r10
            r4 = r0
            r5 = r1
            r6 = r2
            r4.<init>(r5, (int) r6)
            r4 = r0
            r5 = r3
            r4._deserFeatures = r5
            r4 = r0
            r5 = r1
            com.shaded.fasterxml.jackson.databind.node.JsonNodeFactory r5 = r5._nodeFactory
            r4._nodeFactory = r5
            r4 = r0
            r5 = r1
            com.shaded.fasterxml.jackson.databind.util.LinkedNode<com.shaded.fasterxml.jackson.databind.deser.DeserializationProblemHandler> r5 = r5._problemHandlers
            r4._problemHandlers = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.DeserializationConfig.<init>(com.shaded.fasterxml.jackson.databind.DeserializationConfig, int, int):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private DeserializationConfig(com.shaded.fasterxml.jackson.databind.DeserializationConfig r7, com.shaded.fasterxml.jackson.databind.cfg.BaseSettings r8) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r8
            r3 = r0
            r4 = r1
            r5 = r2
            r3.<init>(r4, (com.shaded.fasterxml.jackson.databind.cfg.BaseSettings) r5)
            r3 = r0
            r4 = r1
            int r4 = r4._deserFeatures
            r3._deserFeatures = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.node.JsonNodeFactory r4 = r4._nodeFactory
            r3._nodeFactory = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.util.LinkedNode<com.shaded.fasterxml.jackson.databind.deser.DeserializationProblemHandler> r4 = r4._problemHandlers
            r3._problemHandlers = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.DeserializationConfig.<init>(com.shaded.fasterxml.jackson.databind.DeserializationConfig, com.shaded.fasterxml.jackson.databind.cfg.BaseSettings):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private DeserializationConfig(com.shaded.fasterxml.jackson.databind.DeserializationConfig r6, com.shaded.fasterxml.jackson.databind.node.JsonNodeFactory r7) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r2 = r7
            r3 = r0
            r4 = r1
            r3.<init>(r4)
            r3 = r0
            r4 = r1
            int r4 = r4._deserFeatures
            r3._deserFeatures = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.util.LinkedNode<com.shaded.fasterxml.jackson.databind.deser.DeserializationProblemHandler> r4 = r4._problemHandlers
            r3._problemHandlers = r4
            r3 = r0
            r4 = r2
            r3._nodeFactory = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.DeserializationConfig.<init>(com.shaded.fasterxml.jackson.databind.DeserializationConfig, com.shaded.fasterxml.jackson.databind.node.JsonNodeFactory):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private DeserializationConfig(com.shaded.fasterxml.jackson.databind.DeserializationConfig r6, com.shaded.fasterxml.jackson.databind.util.LinkedNode<com.shaded.fasterxml.jackson.databind.deser.DeserializationProblemHandler> r7) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r2 = r7
            r3 = r0
            r4 = r1
            r3.<init>(r4)
            r3 = r0
            r4 = r1
            int r4 = r4._deserFeatures
            r3._deserFeatures = r4
            r3 = r0
            r4 = r2
            r3._problemHandlers = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.node.JsonNodeFactory r4 = r4._nodeFactory
            r3._nodeFactory = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.DeserializationConfig.<init>(com.shaded.fasterxml.jackson.databind.DeserializationConfig, com.shaded.fasterxml.jackson.databind.util.LinkedNode):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private DeserializationConfig(com.shaded.fasterxml.jackson.databind.DeserializationConfig r7, java.lang.String r8) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r8
            r3 = r0
            r4 = r1
            r5 = r2
            r3.<init>(r4, (java.lang.String) r5)
            r3 = r0
            r4 = r1
            int r4 = r4._deserFeatures
            r3._deserFeatures = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.util.LinkedNode<com.shaded.fasterxml.jackson.databind.deser.DeserializationProblemHandler> r4 = r4._problemHandlers
            r3._problemHandlers = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.node.JsonNodeFactory r4 = r4._nodeFactory
            r3._nodeFactory = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.DeserializationConfig.<init>(com.shaded.fasterxml.jackson.databind.DeserializationConfig, java.lang.String):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private DeserializationConfig(com.shaded.fasterxml.jackson.databind.DeserializationConfig r7, java.lang.Class<?> r8) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r8
            r3 = r0
            r4 = r1
            r5 = r2
            r3.<init>(r4, (java.lang.Class<?>) r5)
            r3 = r0
            r4 = r1
            int r4 = r4._deserFeatures
            r3._deserFeatures = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.util.LinkedNode<com.shaded.fasterxml.jackson.databind.deser.DeserializationProblemHandler> r4 = r4._problemHandlers
            r3._problemHandlers = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.node.JsonNodeFactory r4 = r4._nodeFactory
            r3._nodeFactory = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.DeserializationConfig.<init>(com.shaded.fasterxml.jackson.databind.DeserializationConfig, java.lang.Class):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected DeserializationConfig(com.shaded.fasterxml.jackson.databind.DeserializationConfig r7, java.util.Map<com.shaded.fasterxml.jackson.databind.type.ClassKey, java.lang.Class<?>> r8) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r8
            r3 = r0
            r4 = r1
            r5 = r2
            r3.<init>(r4, (java.util.Map<com.shaded.fasterxml.jackson.databind.type.ClassKey, java.lang.Class<?>>) r5)
            r3 = r0
            r4 = r1
            int r4 = r4._deserFeatures
            r3._deserFeatures = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.util.LinkedNode<com.shaded.fasterxml.jackson.databind.deser.DeserializationProblemHandler> r4 = r4._problemHandlers
            r3._problemHandlers = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.node.JsonNodeFactory r4 = r4._nodeFactory
            r3._nodeFactory = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.DeserializationConfig.<init>(com.shaded.fasterxml.jackson.databind.DeserializationConfig, java.util.Map):void");
    }

    /* access modifiers changed from: protected */
    public BaseSettings getBaseSettings() {
        return this._base;
    }

    public DeserializationConfig with(MapperFeature... mapperFeatureArr) {
        DeserializationConfig deserializationConfig;
        DeserializationConfig deserializationConfig2;
        int i = this._mapperFeatures;
        MapperFeature[] mapperFeatureArr2 = mapperFeatureArr;
        int length = mapperFeatureArr2.length;
        for (int i2 = 0; i2 < length; i2++) {
            i |= mapperFeatureArr2[i2].getMask();
        }
        if (i == this._mapperFeatures) {
            deserializationConfig2 = this;
        } else {
            deserializationConfig2 = deserializationConfig;
            new DeserializationConfig(this, i, this._deserFeatures);
        }
        return deserializationConfig2;
    }

    public DeserializationConfig without(MapperFeature... mapperFeatureArr) {
        DeserializationConfig deserializationConfig;
        DeserializationConfig deserializationConfig2;
        int i = this._mapperFeatures;
        MapperFeature[] mapperFeatureArr2 = mapperFeatureArr;
        int length = mapperFeatureArr2.length;
        for (int i2 = 0; i2 < length; i2++) {
            i &= mapperFeatureArr2[i2].getMask() ^ -1;
        }
        if (i == this._mapperFeatures) {
            deserializationConfig2 = this;
        } else {
            deserializationConfig2 = deserializationConfig;
            new DeserializationConfig(this, i, this._deserFeatures);
        }
        return deserializationConfig2;
    }

    public DeserializationConfig with(ClassIntrospector classIntrospector) {
        return _withBase(this._base.withClassIntrospector(classIntrospector));
    }

    public DeserializationConfig with(AnnotationIntrospector annotationIntrospector) {
        return _withBase(this._base.withAnnotationIntrospector(annotationIntrospector));
    }

    public DeserializationConfig with(VisibilityChecker<?> visibilityChecker) {
        return _withBase(this._base.withVisibilityChecker(visibilityChecker));
    }

    public DeserializationConfig withVisibility(PropertyAccessor propertyAccessor, JsonAutoDetect.Visibility visibility) {
        return _withBase(this._base.withVisibility(propertyAccessor, visibility));
    }

    public DeserializationConfig with(TypeResolverBuilder<?> typeResolverBuilder) {
        return _withBase(this._base.withTypeResolverBuilder(typeResolverBuilder));
    }

    public DeserializationConfig with(SubtypeResolver subtypeResolver) {
        DeserializationConfig deserializationConfig;
        DeserializationConfig deserializationConfig2;
        SubtypeResolver subtypeResolver2 = subtypeResolver;
        if (this._subtypeResolver == subtypeResolver2) {
            deserializationConfig2 = this;
        } else {
            deserializationConfig2 = deserializationConfig;
            new DeserializationConfig(this, subtypeResolver2);
        }
        return deserializationConfig2;
    }

    public DeserializationConfig with(PropertyNamingStrategy propertyNamingStrategy) {
        return _withBase(this._base.withPropertyNamingStrategy(propertyNamingStrategy));
    }

    public DeserializationConfig withRootName(String str) {
        DeserializationConfig deserializationConfig;
        String str2 = str;
        if (str2 == null) {
            if (this._rootName == null) {
                return this;
            }
        } else if (str2.equals(this._rootName)) {
            return this;
        }
        new DeserializationConfig(this, str2);
        return deserializationConfig;
    }

    public DeserializationConfig with(TypeFactory typeFactory) {
        return _withBase(this._base.withTypeFactory(typeFactory));
    }

    public DeserializationConfig with(DateFormat dateFormat) {
        return _withBase(this._base.withDateFormat(dateFormat));
    }

    public DeserializationConfig with(HandlerInstantiator handlerInstantiator) {
        return _withBase(this._base.withHandlerInstantiator(handlerInstantiator));
    }

    public DeserializationConfig withInsertedAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
        return _withBase(this._base.withInsertedAnnotationIntrospector(annotationIntrospector));
    }

    public DeserializationConfig withAppendedAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
        return _withBase(this._base.withAppendedAnnotationIntrospector(annotationIntrospector));
    }

    public DeserializationConfig withView(Class<?> cls) {
        DeserializationConfig deserializationConfig;
        DeserializationConfig deserializationConfig2;
        Class<?> cls2 = cls;
        if (this._view == cls2) {
            deserializationConfig2 = this;
        } else {
            deserializationConfig2 = deserializationConfig;
            new DeserializationConfig(this, cls2);
        }
        return deserializationConfig2;
    }

    public DeserializationConfig with(Locale locale) {
        return _withBase(this._base.with(locale));
    }

    public DeserializationConfig with(TimeZone timeZone) {
        return _withBase(this._base.with(timeZone));
    }

    public DeserializationConfig with(Base64Variant base64Variant) {
        return _withBase(this._base.with(base64Variant));
    }

    private final DeserializationConfig _withBase(BaseSettings baseSettings) {
        DeserializationConfig deserializationConfig;
        DeserializationConfig deserializationConfig2;
        BaseSettings baseSettings2 = baseSettings;
        if (this._base == baseSettings2) {
            deserializationConfig2 = this;
        } else {
            deserializationConfig2 = deserializationConfig;
            new DeserializationConfig(this, baseSettings2);
        }
        return deserializationConfig2;
    }

    public DeserializationConfig with(JsonNodeFactory jsonNodeFactory) {
        DeserializationConfig deserializationConfig;
        JsonNodeFactory jsonNodeFactory2 = jsonNodeFactory;
        if (this._nodeFactory == jsonNodeFactory2) {
            return this;
        }
        new DeserializationConfig(this, jsonNodeFactory2);
        return deserializationConfig;
    }

    public DeserializationConfig withHandler(DeserializationProblemHandler deserializationProblemHandler) {
        DeserializationConfig deserializationConfig;
        LinkedNode linkedNode;
        DeserializationProblemHandler deserializationProblemHandler2 = deserializationProblemHandler;
        if (LinkedNode.contains(this._problemHandlers, deserializationProblemHandler2)) {
            return this;
        }
        new LinkedNode(deserializationProblemHandler2, this._problemHandlers);
        new DeserializationConfig(this, (LinkedNode<DeserializationProblemHandler>) linkedNode);
        return deserializationConfig;
    }

    public DeserializationConfig withNoProblemHandlers() {
        DeserializationConfig deserializationConfig;
        if (this._problemHandlers == null) {
            return this;
        }
        new DeserializationConfig(this, (LinkedNode<DeserializationProblemHandler>) null);
        return deserializationConfig;
    }

    public DeserializationConfig with(DeserializationFeature deserializationFeature) {
        DeserializationConfig deserializationConfig;
        DeserializationConfig deserializationConfig2;
        int mask = this._deserFeatures | deserializationFeature.getMask();
        if (mask == this._deserFeatures) {
            deserializationConfig2 = this;
        } else {
            deserializationConfig2 = deserializationConfig;
            new DeserializationConfig(this, this._mapperFeatures, mask);
        }
        return deserializationConfig2;
    }

    public DeserializationConfig with(DeserializationFeature deserializationFeature, DeserializationFeature... deserializationFeatureArr) {
        DeserializationConfig deserializationConfig;
        DeserializationConfig deserializationConfig2;
        int mask = this._deserFeatures | deserializationFeature.getMask();
        DeserializationFeature[] deserializationFeatureArr2 = deserializationFeatureArr;
        int length = deserializationFeatureArr2.length;
        for (int i = 0; i < length; i++) {
            mask |= deserializationFeatureArr2[i].getMask();
        }
        if (mask == this._deserFeatures) {
            deserializationConfig2 = this;
        } else {
            deserializationConfig2 = deserializationConfig;
            new DeserializationConfig(this, this._mapperFeatures, mask);
        }
        return deserializationConfig2;
    }

    public DeserializationConfig withFeatures(DeserializationFeature... deserializationFeatureArr) {
        DeserializationConfig deserializationConfig;
        DeserializationConfig deserializationConfig2;
        int i = this._deserFeatures;
        DeserializationFeature[] deserializationFeatureArr2 = deserializationFeatureArr;
        int length = deserializationFeatureArr2.length;
        for (int i2 = 0; i2 < length; i2++) {
            i |= deserializationFeatureArr2[i2].getMask();
        }
        if (i == this._deserFeatures) {
            deserializationConfig2 = this;
        } else {
            deserializationConfig2 = deserializationConfig;
            new DeserializationConfig(this, this._mapperFeatures, i);
        }
        return deserializationConfig2;
    }

    public DeserializationConfig without(DeserializationFeature deserializationFeature) {
        DeserializationConfig deserializationConfig;
        DeserializationConfig deserializationConfig2;
        int mask = this._deserFeatures & (deserializationFeature.getMask() ^ -1);
        if (mask == this._deserFeatures) {
            deserializationConfig2 = this;
        } else {
            deserializationConfig2 = deserializationConfig;
            new DeserializationConfig(this, this._mapperFeatures, mask);
        }
        return deserializationConfig2;
    }

    public DeserializationConfig without(DeserializationFeature deserializationFeature, DeserializationFeature... deserializationFeatureArr) {
        DeserializationConfig deserializationConfig;
        DeserializationConfig deserializationConfig2;
        int mask = this._deserFeatures & (deserializationFeature.getMask() ^ -1);
        DeserializationFeature[] deserializationFeatureArr2 = deserializationFeatureArr;
        int length = deserializationFeatureArr2.length;
        for (int i = 0; i < length; i++) {
            mask &= deserializationFeatureArr2[i].getMask() ^ -1;
        }
        if (mask == this._deserFeatures) {
            deserializationConfig2 = this;
        } else {
            deserializationConfig2 = deserializationConfig;
            new DeserializationConfig(this, this._mapperFeatures, mask);
        }
        return deserializationConfig2;
    }

    public DeserializationConfig withoutFeatures(DeserializationFeature... deserializationFeatureArr) {
        DeserializationConfig deserializationConfig;
        DeserializationConfig deserializationConfig2;
        int i = this._deserFeatures;
        DeserializationFeature[] deserializationFeatureArr2 = deserializationFeatureArr;
        int length = deserializationFeatureArr2.length;
        for (int i2 = 0; i2 < length; i2++) {
            i &= deserializationFeatureArr2[i2].getMask() ^ -1;
        }
        if (i == this._deserFeatures) {
            deserializationConfig2 = this;
        } else {
            deserializationConfig2 = deserializationConfig;
            new DeserializationConfig(this, this._mapperFeatures, i);
        }
        return deserializationConfig2;
    }

    public AnnotationIntrospector getAnnotationIntrospector() {
        if (isEnabled(MapperFeature.USE_ANNOTATIONS)) {
            return super.getAnnotationIntrospector();
        }
        return NopAnnotationIntrospector.instance;
    }

    public boolean useRootWrapping() {
        if (this._rootName == null) {
            return isEnabled(DeserializationFeature.UNWRAP_ROOT_VALUE);
        }
        return this._rootName.length() > 0;
    }

    public BeanDescription introspectClassAnnotations(JavaType javaType) {
        return getClassIntrospector().forClassAnnotations(this, javaType, this);
    }

    public BeanDescription introspectDirectClassAnnotations(JavaType javaType) {
        return getClassIntrospector().forDirectClassAnnotations(this, javaType, this);
    }

    public VisibilityChecker<?> getDefaultVisibilityChecker() {
        VisibilityChecker defaultVisibilityChecker = super.getDefaultVisibilityChecker();
        if (!isEnabled(MapperFeature.AUTO_DETECT_SETTERS)) {
            defaultVisibilityChecker = defaultVisibilityChecker.withSetterVisibility(JsonAutoDetect.Visibility.NONE);
        }
        if (!isEnabled(MapperFeature.AUTO_DETECT_CREATORS)) {
            defaultVisibilityChecker = defaultVisibilityChecker.withCreatorVisibility(JsonAutoDetect.Visibility.NONE);
        }
        if (!isEnabled(MapperFeature.AUTO_DETECT_FIELDS)) {
            defaultVisibilityChecker = defaultVisibilityChecker.withFieldVisibility(JsonAutoDetect.Visibility.NONE);
        }
        return defaultVisibilityChecker;
    }

    public final boolean isEnabled(DeserializationFeature deserializationFeature) {
        return (this._deserFeatures & deserializationFeature.getMask()) != 0;
    }

    public final int getDeserializationFeatures() {
        return this._deserFeatures;
    }

    public LinkedNode<DeserializationProblemHandler> getProblemHandlers() {
        return this._problemHandlers;
    }

    public final JsonNodeFactory getNodeFactory() {
        return this._nodeFactory;
    }

    public <T extends BeanDescription> T introspect(JavaType javaType) {
        return getClassIntrospector().forDeserialization(this, javaType, this);
    }

    public <T extends BeanDescription> T introspectForCreation(JavaType javaType) {
        return getClassIntrospector().forCreation(this, javaType, this);
    }

    public <T extends BeanDescription> T introspectForBuilder(JavaType javaType) {
        return getClassIntrospector().forDeserializationWithBuilder(this, javaType, this);
    }
}

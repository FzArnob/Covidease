package com.shaded.fasterxml.jackson.databind;

import com.shaded.fasterxml.jackson.annotation.JsonAutoDetect;
import com.shaded.fasterxml.jackson.annotation.JsonInclude;
import com.shaded.fasterxml.jackson.annotation.PropertyAccessor;
import com.shaded.fasterxml.jackson.core.Base64Variant;
import com.shaded.fasterxml.jackson.databind.cfg.BaseSettings;
import com.shaded.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.shaded.fasterxml.jackson.databind.cfg.MapperConfigBase;
import com.shaded.fasterxml.jackson.databind.introspect.ClassIntrospector;
import com.shaded.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.shaded.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.shaded.fasterxml.jackson.databind.ser.FilterProvider;
import com.shaded.fasterxml.jackson.databind.type.ClassKey;
import com.shaded.fasterxml.jackson.databind.type.TypeFactory;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public final class SerializationConfig extends MapperConfigBase<SerializationFeature, SerializationConfig> implements Serializable {
    private static final long serialVersionUID = 8849092838541724233L;
    protected final FilterProvider _filterProvider;
    protected final int _serFeatures;
    protected JsonInclude.Include _serializationInclusion;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SerializationConfig(BaseSettings baseSettings, SubtypeResolver subtypeResolver, Map<ClassKey, Class<?>> map) {
        super(baseSettings, subtypeResolver, map);
        this._serializationInclusion = null;
        this._serFeatures = collectFeatureDefaults(SerializationFeature.class);
        this._filterProvider = null;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private SerializationConfig(com.shaded.fasterxml.jackson.databind.SerializationConfig r7, com.shaded.fasterxml.jackson.databind.jsontype.SubtypeResolver r8) {
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
            r4 = 0
            r3._serializationInclusion = r4
            r3 = r0
            r4 = r1
            int r4 = r4._serFeatures
            r3._serFeatures = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.annotation.JsonInclude$Include r4 = r4._serializationInclusion
            r3._serializationInclusion = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.ser.FilterProvider r4 = r4._filterProvider
            r3._filterProvider = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.SerializationConfig.<init>(com.shaded.fasterxml.jackson.databind.SerializationConfig, com.shaded.fasterxml.jackson.databind.jsontype.SubtypeResolver):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private SerializationConfig(com.shaded.fasterxml.jackson.databind.SerializationConfig r8, int r9, int r10) {
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
            r5 = 0
            r4._serializationInclusion = r5
            r4 = r0
            r5 = r3
            r4._serFeatures = r5
            r4 = r0
            r5 = r1
            com.shaded.fasterxml.jackson.annotation.JsonInclude$Include r5 = r5._serializationInclusion
            r4._serializationInclusion = r5
            r4 = r0
            r5 = r1
            com.shaded.fasterxml.jackson.databind.ser.FilterProvider r5 = r5._filterProvider
            r4._filterProvider = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.SerializationConfig.<init>(com.shaded.fasterxml.jackson.databind.SerializationConfig, int, int):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private SerializationConfig(com.shaded.fasterxml.jackson.databind.SerializationConfig r7, com.shaded.fasterxml.jackson.databind.cfg.BaseSettings r8) {
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
            r4 = 0
            r3._serializationInclusion = r4
            r3 = r0
            r4 = r1
            int r4 = r4._serFeatures
            r3._serFeatures = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.annotation.JsonInclude$Include r4 = r4._serializationInclusion
            r3._serializationInclusion = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.ser.FilterProvider r4 = r4._filterProvider
            r3._filterProvider = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.SerializationConfig.<init>(com.shaded.fasterxml.jackson.databind.SerializationConfig, com.shaded.fasterxml.jackson.databind.cfg.BaseSettings):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private SerializationConfig(com.shaded.fasterxml.jackson.databind.SerializationConfig r6, com.shaded.fasterxml.jackson.databind.ser.FilterProvider r7) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r2 = r7
            r3 = r0
            r4 = r1
            r3.<init>(r4)
            r3 = r0
            r4 = 0
            r3._serializationInclusion = r4
            r3 = r0
            r4 = r1
            int r4 = r4._serFeatures
            r3._serFeatures = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.annotation.JsonInclude$Include r4 = r4._serializationInclusion
            r3._serializationInclusion = r4
            r3 = r0
            r4 = r2
            r3._filterProvider = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.SerializationConfig.<init>(com.shaded.fasterxml.jackson.databind.SerializationConfig, com.shaded.fasterxml.jackson.databind.ser.FilterProvider):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private SerializationConfig(com.shaded.fasterxml.jackson.databind.SerializationConfig r7, java.lang.Class<?> r8) {
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
            r4 = 0
            r3._serializationInclusion = r4
            r3 = r0
            r4 = r1
            int r4 = r4._serFeatures
            r3._serFeatures = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.annotation.JsonInclude$Include r4 = r4._serializationInclusion
            r3._serializationInclusion = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.ser.FilterProvider r4 = r4._filterProvider
            r3._filterProvider = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.SerializationConfig.<init>(com.shaded.fasterxml.jackson.databind.SerializationConfig, java.lang.Class):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private SerializationConfig(com.shaded.fasterxml.jackson.databind.SerializationConfig r6, com.shaded.fasterxml.jackson.annotation.JsonInclude.Include r7) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r2 = r7
            r3 = r0
            r4 = r1
            r3.<init>(r4)
            r3 = r0
            r4 = 0
            r3._serializationInclusion = r4
            r3 = r0
            r4 = r1
            int r4 = r4._serFeatures
            r3._serFeatures = r4
            r3 = r0
            r4 = r2
            r3._serializationInclusion = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.ser.FilterProvider r4 = r4._filterProvider
            r3._filterProvider = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.SerializationConfig.<init>(com.shaded.fasterxml.jackson.databind.SerializationConfig, com.shaded.fasterxml.jackson.annotation.JsonInclude$Include):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private SerializationConfig(com.shaded.fasterxml.jackson.databind.SerializationConfig r7, java.lang.String r8) {
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
            r4 = 0
            r3._serializationInclusion = r4
            r3 = r0
            r4 = r1
            int r4 = r4._serFeatures
            r3._serFeatures = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.annotation.JsonInclude$Include r4 = r4._serializationInclusion
            r3._serializationInclusion = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.ser.FilterProvider r4 = r4._filterProvider
            r3._filterProvider = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.SerializationConfig.<init>(com.shaded.fasterxml.jackson.databind.SerializationConfig, java.lang.String):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected SerializationConfig(com.shaded.fasterxml.jackson.databind.SerializationConfig r7, java.util.Map<com.shaded.fasterxml.jackson.databind.type.ClassKey, java.lang.Class<?>> r8) {
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
            r4 = 0
            r3._serializationInclusion = r4
            r3 = r0
            r4 = r1
            int r4 = r4._serFeatures
            r3._serFeatures = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.annotation.JsonInclude$Include r4 = r4._serializationInclusion
            r3._serializationInclusion = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.ser.FilterProvider r4 = r4._filterProvider
            r3._filterProvider = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.SerializationConfig.<init>(com.shaded.fasterxml.jackson.databind.SerializationConfig, java.util.Map):void");
    }

    public SerializationConfig with(MapperFeature... mapperFeatureArr) {
        SerializationConfig serializationConfig;
        SerializationConfig serializationConfig2;
        int i = this._mapperFeatures;
        MapperFeature[] mapperFeatureArr2 = mapperFeatureArr;
        int length = mapperFeatureArr2.length;
        for (int i2 = 0; i2 < length; i2++) {
            i |= mapperFeatureArr2[i2].getMask();
        }
        if (i == this._mapperFeatures) {
            serializationConfig2 = this;
        } else {
            serializationConfig2 = serializationConfig;
            new SerializationConfig(this, i, this._serFeatures);
        }
        return serializationConfig2;
    }

    public SerializationConfig without(MapperFeature... mapperFeatureArr) {
        SerializationConfig serializationConfig;
        SerializationConfig serializationConfig2;
        int i = this._mapperFeatures;
        MapperFeature[] mapperFeatureArr2 = mapperFeatureArr;
        int length = mapperFeatureArr2.length;
        for (int i2 = 0; i2 < length; i2++) {
            i &= mapperFeatureArr2[i2].getMask() ^ -1;
        }
        if (i == this._mapperFeatures) {
            serializationConfig2 = this;
        } else {
            serializationConfig2 = serializationConfig;
            new SerializationConfig(this, i, this._serFeatures);
        }
        return serializationConfig2;
    }

    public SerializationConfig with(AnnotationIntrospector annotationIntrospector) {
        return _withBase(this._base.withAnnotationIntrospector(annotationIntrospector));
    }

    public SerializationConfig withAppendedAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
        return _withBase(this._base.withAppendedAnnotationIntrospector(annotationIntrospector));
    }

    public SerializationConfig withInsertedAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
        return _withBase(this._base.withInsertedAnnotationIntrospector(annotationIntrospector));
    }

    public SerializationConfig with(ClassIntrospector classIntrospector) {
        return _withBase(this._base.withClassIntrospector(classIntrospector));
    }

    public SerializationConfig with(DateFormat dateFormat) {
        SerializationConfig serializationConfig;
        SerializationConfig without;
        DateFormat dateFormat2 = dateFormat;
        new SerializationConfig(this, this._base.withDateFormat(dateFormat2));
        SerializationConfig serializationConfig2 = serializationConfig;
        if (dateFormat2 == null) {
            without = serializationConfig2.with(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        } else {
            without = serializationConfig2.without(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        }
        return without;
    }

    public SerializationConfig with(HandlerInstantiator handlerInstantiator) {
        return _withBase(this._base.withHandlerInstantiator(handlerInstantiator));
    }

    public SerializationConfig with(PropertyNamingStrategy propertyNamingStrategy) {
        return _withBase(this._base.withPropertyNamingStrategy(propertyNamingStrategy));
    }

    public SerializationConfig withRootName(String str) {
        SerializationConfig serializationConfig;
        String str2 = str;
        if (str2 == null) {
            if (this._rootName == null) {
                return this;
            }
        } else if (str2.equals(this._rootName)) {
            return this;
        }
        new SerializationConfig(this, str2);
        return serializationConfig;
    }

    public SerializationConfig with(SubtypeResolver subtypeResolver) {
        SerializationConfig serializationConfig;
        SerializationConfig serializationConfig2;
        SubtypeResolver subtypeResolver2 = subtypeResolver;
        if (subtypeResolver2 == this._subtypeResolver) {
            serializationConfig2 = this;
        } else {
            serializationConfig2 = serializationConfig;
            new SerializationConfig(this, subtypeResolver2);
        }
        return serializationConfig2;
    }

    public SerializationConfig with(TypeFactory typeFactory) {
        return _withBase(this._base.withTypeFactory(typeFactory));
    }

    public SerializationConfig with(TypeResolverBuilder<?> typeResolverBuilder) {
        return _withBase(this._base.withTypeResolverBuilder(typeResolverBuilder));
    }

    public SerializationConfig withView(Class<?> cls) {
        SerializationConfig serializationConfig;
        SerializationConfig serializationConfig2;
        Class<?> cls2 = cls;
        if (this._view == cls2) {
            serializationConfig2 = this;
        } else {
            serializationConfig2 = serializationConfig;
            new SerializationConfig(this, cls2);
        }
        return serializationConfig2;
    }

    public SerializationConfig with(VisibilityChecker<?> visibilityChecker) {
        return _withBase(this._base.withVisibilityChecker(visibilityChecker));
    }

    public SerializationConfig withVisibility(PropertyAccessor propertyAccessor, JsonAutoDetect.Visibility visibility) {
        return _withBase(this._base.withVisibility(propertyAccessor, visibility));
    }

    public SerializationConfig with(Locale locale) {
        return _withBase(this._base.with(locale));
    }

    public SerializationConfig with(TimeZone timeZone) {
        return _withBase(this._base.with(timeZone));
    }

    public SerializationConfig with(Base64Variant base64Variant) {
        return _withBase(this._base.with(base64Variant));
    }

    private final SerializationConfig _withBase(BaseSettings baseSettings) {
        SerializationConfig serializationConfig;
        SerializationConfig serializationConfig2;
        BaseSettings baseSettings2 = baseSettings;
        if (this._base == baseSettings2) {
            serializationConfig2 = this;
        } else {
            serializationConfig2 = serializationConfig;
            new SerializationConfig(this, baseSettings2);
        }
        return serializationConfig2;
    }

    public SerializationConfig with(SerializationFeature serializationFeature) {
        SerializationConfig serializationConfig;
        SerializationConfig serializationConfig2;
        int mask = this._serFeatures | serializationFeature.getMask();
        if (mask == this._serFeatures) {
            serializationConfig2 = this;
        } else {
            serializationConfig2 = serializationConfig;
            new SerializationConfig(this, this._mapperFeatures, mask);
        }
        return serializationConfig2;
    }

    public SerializationConfig with(SerializationFeature serializationFeature, SerializationFeature... serializationFeatureArr) {
        SerializationConfig serializationConfig;
        SerializationConfig serializationConfig2;
        int mask = this._serFeatures | serializationFeature.getMask();
        SerializationFeature[] serializationFeatureArr2 = serializationFeatureArr;
        int length = serializationFeatureArr2.length;
        for (int i = 0; i < length; i++) {
            mask |= serializationFeatureArr2[i].getMask();
        }
        if (mask == this._serFeatures) {
            serializationConfig2 = this;
        } else {
            serializationConfig2 = serializationConfig;
            new SerializationConfig(this, this._mapperFeatures, mask);
        }
        return serializationConfig2;
    }

    public SerializationConfig withFeatures(SerializationFeature... serializationFeatureArr) {
        SerializationConfig serializationConfig;
        SerializationConfig serializationConfig2;
        int i = this._serFeatures;
        SerializationFeature[] serializationFeatureArr2 = serializationFeatureArr;
        int length = serializationFeatureArr2.length;
        for (int i2 = 0; i2 < length; i2++) {
            i |= serializationFeatureArr2[i2].getMask();
        }
        if (i == this._serFeatures) {
            serializationConfig2 = this;
        } else {
            serializationConfig2 = serializationConfig;
            new SerializationConfig(this, this._mapperFeatures, i);
        }
        return serializationConfig2;
    }

    public SerializationConfig without(SerializationFeature serializationFeature) {
        SerializationConfig serializationConfig;
        SerializationConfig serializationConfig2;
        int mask = this._serFeatures & (serializationFeature.getMask() ^ -1);
        if (mask == this._serFeatures) {
            serializationConfig2 = this;
        } else {
            serializationConfig2 = serializationConfig;
            new SerializationConfig(this, this._mapperFeatures, mask);
        }
        return serializationConfig2;
    }

    public SerializationConfig without(SerializationFeature serializationFeature, SerializationFeature... serializationFeatureArr) {
        SerializationConfig serializationConfig;
        SerializationConfig serializationConfig2;
        int mask = this._serFeatures & (serializationFeature.getMask() ^ -1);
        SerializationFeature[] serializationFeatureArr2 = serializationFeatureArr;
        int length = serializationFeatureArr2.length;
        for (int i = 0; i < length; i++) {
            mask &= serializationFeatureArr2[i].getMask() ^ -1;
        }
        if (mask == this._serFeatures) {
            serializationConfig2 = this;
        } else {
            serializationConfig2 = serializationConfig;
            new SerializationConfig(this, this._mapperFeatures, mask);
        }
        return serializationConfig2;
    }

    public SerializationConfig withoutFeatures(SerializationFeature... serializationFeatureArr) {
        SerializationConfig serializationConfig;
        SerializationConfig serializationConfig2;
        int i = this._serFeatures;
        SerializationFeature[] serializationFeatureArr2 = serializationFeatureArr;
        int length = serializationFeatureArr2.length;
        for (int i2 = 0; i2 < length; i2++) {
            i &= serializationFeatureArr2[i2].getMask() ^ -1;
        }
        if (i == this._serFeatures) {
            serializationConfig2 = this;
        } else {
            serializationConfig2 = serializationConfig;
            new SerializationConfig(this, this._mapperFeatures, i);
        }
        return serializationConfig2;
    }

    public SerializationConfig withFilters(FilterProvider filterProvider) {
        SerializationConfig serializationConfig;
        SerializationConfig serializationConfig2;
        FilterProvider filterProvider2 = filterProvider;
        if (filterProvider2 == this._filterProvider) {
            serializationConfig2 = this;
        } else {
            serializationConfig2 = serializationConfig;
            new SerializationConfig(this, filterProvider2);
        }
        return serializationConfig2;
    }

    public SerializationConfig withSerializationInclusion(JsonInclude.Include include) {
        SerializationConfig serializationConfig;
        SerializationConfig serializationConfig2;
        JsonInclude.Include include2 = include;
        if (this._serializationInclusion == include2) {
            serializationConfig2 = this;
        } else {
            serializationConfig2 = serializationConfig;
            new SerializationConfig(this, include2);
        }
        return serializationConfig2;
    }

    public boolean useRootWrapping() {
        if (this._rootName == null) {
            return isEnabled(SerializationFeature.WRAP_ROOT_VALUE);
        }
        return this._rootName.length() > 0;
    }

    public AnnotationIntrospector getAnnotationIntrospector() {
        if (isEnabled(MapperFeature.USE_ANNOTATIONS)) {
            return super.getAnnotationIntrospector();
        }
        return AnnotationIntrospector.nopInstance();
    }

    public BeanDescription introspectClassAnnotations(JavaType javaType) {
        return getClassIntrospector().forClassAnnotations(this, javaType, this);
    }

    public BeanDescription introspectDirectClassAnnotations(JavaType javaType) {
        return getClassIntrospector().forDirectClassAnnotations(this, javaType, this);
    }

    public VisibilityChecker<?> getDefaultVisibilityChecker() {
        VisibilityChecker defaultVisibilityChecker = super.getDefaultVisibilityChecker();
        if (!isEnabled(MapperFeature.AUTO_DETECT_GETTERS)) {
            defaultVisibilityChecker = defaultVisibilityChecker.withGetterVisibility(JsonAutoDetect.Visibility.NONE);
        }
        if (!isEnabled(MapperFeature.AUTO_DETECT_IS_GETTERS)) {
            defaultVisibilityChecker = defaultVisibilityChecker.withIsGetterVisibility(JsonAutoDetect.Visibility.NONE);
        }
        if (!isEnabled(MapperFeature.AUTO_DETECT_FIELDS)) {
            defaultVisibilityChecker = defaultVisibilityChecker.withFieldVisibility(JsonAutoDetect.Visibility.NONE);
        }
        return defaultVisibilityChecker;
    }

    public final boolean isEnabled(SerializationFeature serializationFeature) {
        return (this._serFeatures & serializationFeature.getMask()) != 0;
    }

    public final int getSerializationFeatures() {
        return this._serFeatures;
    }

    public JsonInclude.Include getSerializationInclusion() {
        if (this._serializationInclusion != null) {
            return this._serializationInclusion;
        }
        return JsonInclude.Include.ALWAYS;
    }

    public FilterProvider getFilterProvider() {
        return this._filterProvider;
    }

    public <T extends BeanDescription> T introspect(JavaType javaType) {
        return getClassIntrospector().forSerialization(this, javaType, this);
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("[SerializationConfig: flags=0x").append(Integer.toHexString(this._serFeatures)).append("]").toString();
    }
}

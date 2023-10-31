package com.shaded.fasterxml.jackson.databind.cfg;

import com.shaded.fasterxml.jackson.annotation.JsonAutoDetect;
import com.shaded.fasterxml.jackson.annotation.PropertyAccessor;
import com.shaded.fasterxml.jackson.core.Base64Variant;
import com.shaded.fasterxml.jackson.databind.AnnotationIntrospector;
import com.shaded.fasterxml.jackson.databind.MapperFeature;
import com.shaded.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.shaded.fasterxml.jackson.databind.cfg.ConfigFeature;
import com.shaded.fasterxml.jackson.databind.cfg.MapperConfigBase;
import com.shaded.fasterxml.jackson.databind.introspect.ClassIntrospector;
import com.shaded.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.shaded.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.shaded.fasterxml.jackson.databind.type.ClassKey;
import com.shaded.fasterxml.jackson.databind.type.TypeFactory;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public abstract class MapperConfigBase<CFG extends ConfigFeature, T extends MapperConfigBase<CFG, T>> extends MapperConfig<T> implements Serializable {
    private static final int DEFAULT_MAPPER_FEATURES = collectFeatureDefaults(MapperFeature.class);
    private static final long serialVersionUID = -8378230381628000111L;
    protected final Map<ClassKey, Class<?>> _mixInAnnotations;
    protected final String _rootName;
    protected final SubtypeResolver _subtypeResolver;
    protected final Class<?> _view;

    public abstract T with(Base64Variant base64Variant);

    public abstract T with(AnnotationIntrospector annotationIntrospector);

    public abstract T with(PropertyNamingStrategy propertyNamingStrategy);

    public abstract T with(HandlerInstantiator handlerInstantiator);

    public abstract T with(ClassIntrospector classIntrospector);

    public abstract T with(VisibilityChecker<?> visibilityChecker);

    public abstract T with(SubtypeResolver subtypeResolver);

    public abstract T with(TypeResolverBuilder<?> typeResolverBuilder);

    public abstract T with(TypeFactory typeFactory);

    public abstract T with(DateFormat dateFormat);

    public abstract T with(Locale locale);

    public abstract T with(TimeZone timeZone);

    public abstract T withAppendedAnnotationIntrospector(AnnotationIntrospector annotationIntrospector);

    public abstract T withInsertedAnnotationIntrospector(AnnotationIntrospector annotationIntrospector);

    public abstract T withRootName(String str);

    public abstract T withView(Class<?> cls);

    public abstract T withVisibility(PropertyAccessor propertyAccessor, JsonAutoDetect.Visibility visibility);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected MapperConfigBase(BaseSettings baseSettings, SubtypeResolver subtypeResolver, Map<ClassKey, Class<?>> map) {
        super(baseSettings, DEFAULT_MAPPER_FEATURES);
        this._mixInAnnotations = map;
        this._subtypeResolver = subtypeResolver;
        this._rootName = null;
        this._view = null;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected MapperConfigBase(com.shaded.fasterxml.jackson.databind.cfg.MapperConfigBase<CFG, T> r5) {
        /*
            r4 = this;
            r0 = r4
            r1 = r5
            r2 = r0
            r3 = r1
            r2.<init>(r3)
            r2 = r0
            r3 = r1
            java.util.Map<com.shaded.fasterxml.jackson.databind.type.ClassKey, java.lang.Class<?>> r3 = r3._mixInAnnotations
            r2._mixInAnnotations = r3
            r2 = r0
            r3 = r1
            com.shaded.fasterxml.jackson.databind.jsontype.SubtypeResolver r3 = r3._subtypeResolver
            r2._subtypeResolver = r3
            r2 = r0
            r3 = r1
            java.lang.String r3 = r3._rootName
            r2._rootName = r3
            r2 = r0
            r3 = r1
            java.lang.Class<?> r3 = r3._view
            r2._view = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.cfg.MapperConfigBase.<init>(com.shaded.fasterxml.jackson.databind.cfg.MapperConfigBase):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected MapperConfigBase(com.shaded.fasterxml.jackson.databind.cfg.MapperConfigBase<CFG, T> r7, com.shaded.fasterxml.jackson.databind.cfg.BaseSettings r8) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r8
            r3 = r0
            r4 = r2
            r5 = r1
            int r5 = r5._mapperFeatures
            r3.<init>(r4, r5)
            r3 = r0
            r4 = r1
            java.util.Map<com.shaded.fasterxml.jackson.databind.type.ClassKey, java.lang.Class<?>> r4 = r4._mixInAnnotations
            r3._mixInAnnotations = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.jsontype.SubtypeResolver r4 = r4._subtypeResolver
            r3._subtypeResolver = r4
            r3 = r0
            r4 = r1
            java.lang.String r4 = r4._rootName
            r3._rootName = r4
            r3 = r0
            r4 = r1
            java.lang.Class<?> r4 = r4._view
            r3._view = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.cfg.MapperConfigBase.<init>(com.shaded.fasterxml.jackson.databind.cfg.MapperConfigBase, com.shaded.fasterxml.jackson.databind.cfg.BaseSettings):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected MapperConfigBase(com.shaded.fasterxml.jackson.databind.cfg.MapperConfigBase<CFG, T> r7, int r8) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r8
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.cfg.BaseSettings r4 = r4._base
            r5 = r2
            r3.<init>(r4, r5)
            r3 = r0
            r4 = r1
            java.util.Map<com.shaded.fasterxml.jackson.databind.type.ClassKey, java.lang.Class<?>> r4 = r4._mixInAnnotations
            r3._mixInAnnotations = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.jsontype.SubtypeResolver r4 = r4._subtypeResolver
            r3._subtypeResolver = r4
            r3 = r0
            r4 = r1
            java.lang.String r4 = r4._rootName
            r3._rootName = r4
            r3 = r0
            r4 = r1
            java.lang.Class<?> r4 = r4._view
            r3._view = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.cfg.MapperConfigBase.<init>(com.shaded.fasterxml.jackson.databind.cfg.MapperConfigBase, int):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected MapperConfigBase(com.shaded.fasterxml.jackson.databind.cfg.MapperConfigBase<CFG, T> r6, com.shaded.fasterxml.jackson.databind.jsontype.SubtypeResolver r7) {
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
            java.util.Map<com.shaded.fasterxml.jackson.databind.type.ClassKey, java.lang.Class<?>> r4 = r4._mixInAnnotations
            r3._mixInAnnotations = r4
            r3 = r0
            r4 = r2
            r3._subtypeResolver = r4
            r3 = r0
            r4 = r1
            java.lang.String r4 = r4._rootName
            r3._rootName = r4
            r3 = r0
            r4 = r1
            java.lang.Class<?> r4 = r4._view
            r3._view = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.cfg.MapperConfigBase.<init>(com.shaded.fasterxml.jackson.databind.cfg.MapperConfigBase, com.shaded.fasterxml.jackson.databind.jsontype.SubtypeResolver):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected MapperConfigBase(com.shaded.fasterxml.jackson.databind.cfg.MapperConfigBase<CFG, T> r6, java.lang.String r7) {
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
            java.util.Map<com.shaded.fasterxml.jackson.databind.type.ClassKey, java.lang.Class<?>> r4 = r4._mixInAnnotations
            r3._mixInAnnotations = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.jsontype.SubtypeResolver r4 = r4._subtypeResolver
            r3._subtypeResolver = r4
            r3 = r0
            r4 = r2
            r3._rootName = r4
            r3 = r0
            r4 = r1
            java.lang.Class<?> r4 = r4._view
            r3._view = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.cfg.MapperConfigBase.<init>(com.shaded.fasterxml.jackson.databind.cfg.MapperConfigBase, java.lang.String):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected MapperConfigBase(com.shaded.fasterxml.jackson.databind.cfg.MapperConfigBase<CFG, T> r6, java.lang.Class<?> r7) {
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
            java.util.Map<com.shaded.fasterxml.jackson.databind.type.ClassKey, java.lang.Class<?>> r4 = r4._mixInAnnotations
            r3._mixInAnnotations = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.jsontype.SubtypeResolver r4 = r4._subtypeResolver
            r3._subtypeResolver = r4
            r3 = r0
            r4 = r1
            java.lang.String r4 = r4._rootName
            r3._rootName = r4
            r3 = r0
            r4 = r2
            r3._view = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.cfg.MapperConfigBase.<init>(com.shaded.fasterxml.jackson.databind.cfg.MapperConfigBase, java.lang.Class):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected MapperConfigBase(com.shaded.fasterxml.jackson.databind.cfg.MapperConfigBase<CFG, T> r6, java.util.Map<com.shaded.fasterxml.jackson.databind.type.ClassKey, java.lang.Class<?>> r7) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r2 = r7
            r3 = r0
            r4 = r1
            r3.<init>(r4)
            r3 = r0
            r4 = r2
            r3._mixInAnnotations = r4
            r3 = r0
            r4 = r1
            com.shaded.fasterxml.jackson.databind.jsontype.SubtypeResolver r4 = r4._subtypeResolver
            r3._subtypeResolver = r4
            r3 = r0
            r4 = r1
            java.lang.String r4 = r4._rootName
            r3._rootName = r4
            r3 = r0
            r4 = r1
            java.lang.Class<?> r4 = r4._view
            r3._view = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.cfg.MapperConfigBase.<init>(com.shaded.fasterxml.jackson.databind.cfg.MapperConfigBase, java.util.Map):void");
    }

    public final SubtypeResolver getSubtypeResolver() {
        return this._subtypeResolver;
    }

    public final String getRootName() {
        return this._rootName;
    }

    public final Class<?> getActiveView() {
        return this._view;
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
}

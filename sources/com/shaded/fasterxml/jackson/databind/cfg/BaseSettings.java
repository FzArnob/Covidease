package com.shaded.fasterxml.jackson.databind.cfg;

import com.shaded.fasterxml.jackson.annotation.JsonAutoDetect;
import com.shaded.fasterxml.jackson.annotation.PropertyAccessor;
import com.shaded.fasterxml.jackson.core.Base64Variant;
import com.shaded.fasterxml.jackson.databind.AnnotationIntrospector;
import com.shaded.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotationIntrospectorPair;
import com.shaded.fasterxml.jackson.databind.introspect.ClassIntrospector;
import com.shaded.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.shaded.fasterxml.jackson.databind.type.TypeFactory;
import com.shaded.fasterxml.jackson.databind.util.StdDateFormat;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Locale;
import java.util.TimeZone;

public final class BaseSettings implements Serializable {
    private static final long serialVersionUID = 4939673998947122190L;
    protected final AnnotationIntrospector _annotationIntrospector;
    protected final ClassIntrospector _classIntrospector;
    protected final DateFormat _dateFormat;
    protected final Base64Variant _defaultBase64;
    protected final HandlerInstantiator _handlerInstantiator;
    protected final Locale _locale;
    protected final PropertyNamingStrategy _propertyNamingStrategy;
    protected final TimeZone _timeZone;
    protected final TypeFactory _typeFactory;
    protected final TypeResolverBuilder<?> _typeResolverBuilder;
    protected final VisibilityChecker<?> _visibilityChecker;

    public BaseSettings(ClassIntrospector classIntrospector, AnnotationIntrospector annotationIntrospector, VisibilityChecker<?> visibilityChecker, PropertyNamingStrategy propertyNamingStrategy, TypeFactory typeFactory, TypeResolverBuilder<?> typeResolverBuilder, DateFormat dateFormat, HandlerInstantiator handlerInstantiator, Locale locale, TimeZone timeZone, Base64Variant base64Variant) {
        this._classIntrospector = classIntrospector;
        this._annotationIntrospector = annotationIntrospector;
        this._visibilityChecker = visibilityChecker;
        this._propertyNamingStrategy = propertyNamingStrategy;
        this._typeFactory = typeFactory;
        this._typeResolverBuilder = typeResolverBuilder;
        this._dateFormat = dateFormat;
        this._handlerInstantiator = handlerInstantiator;
        this._locale = locale;
        this._timeZone = timeZone;
        this._defaultBase64 = base64Variant;
    }

    public BaseSettings withClassIntrospector(ClassIntrospector classIntrospector) {
        BaseSettings baseSettings;
        ClassIntrospector classIntrospector2 = classIntrospector;
        if (this._classIntrospector == classIntrospector2) {
            return this;
        }
        new BaseSettings(classIntrospector2, this._annotationIntrospector, this._visibilityChecker, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator, this._locale, this._timeZone, this._defaultBase64);
        return baseSettings;
    }

    public BaseSettings withAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
        BaseSettings baseSettings;
        AnnotationIntrospector annotationIntrospector2 = annotationIntrospector;
        if (this._annotationIntrospector == annotationIntrospector2) {
            return this;
        }
        new BaseSettings(this._classIntrospector, annotationIntrospector2, this._visibilityChecker, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator, this._locale, this._timeZone, this._defaultBase64);
        return baseSettings;
    }

    public BaseSettings withInsertedAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
        return withAnnotationIntrospector(AnnotationIntrospectorPair.create(annotationIntrospector, this._annotationIntrospector));
    }

    public BaseSettings withAppendedAnnotationIntrospector(AnnotationIntrospector annotationIntrospector) {
        return withAnnotationIntrospector(AnnotationIntrospectorPair.create(this._annotationIntrospector, annotationIntrospector));
    }

    public BaseSettings withVisibilityChecker(VisibilityChecker<?> visibilityChecker) {
        BaseSettings baseSettings;
        VisibilityChecker<?> visibilityChecker2 = visibilityChecker;
        if (this._visibilityChecker == visibilityChecker2) {
            return this;
        }
        new BaseSettings(this._classIntrospector, this._annotationIntrospector, visibilityChecker2, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator, this._locale, this._timeZone, this._defaultBase64);
        return baseSettings;
    }

    public BaseSettings withVisibility(PropertyAccessor propertyAccessor, JsonAutoDetect.Visibility visibility) {
        BaseSettings baseSettings;
        new BaseSettings(this._classIntrospector, this._annotationIntrospector, this._visibilityChecker.withVisibility(propertyAccessor, visibility), this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator, this._locale, this._timeZone, this._defaultBase64);
        return baseSettings;
    }

    public BaseSettings withPropertyNamingStrategy(PropertyNamingStrategy propertyNamingStrategy) {
        BaseSettings baseSettings;
        PropertyNamingStrategy propertyNamingStrategy2 = propertyNamingStrategy;
        if (this._propertyNamingStrategy == propertyNamingStrategy2) {
            return this;
        }
        new BaseSettings(this._classIntrospector, this._annotationIntrospector, this._visibilityChecker, propertyNamingStrategy2, this._typeFactory, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator, this._locale, this._timeZone, this._defaultBase64);
        return baseSettings;
    }

    public BaseSettings withTypeFactory(TypeFactory typeFactory) {
        BaseSettings baseSettings;
        TypeFactory typeFactory2 = typeFactory;
        if (this._typeFactory == typeFactory2) {
            return this;
        }
        new BaseSettings(this._classIntrospector, this._annotationIntrospector, this._visibilityChecker, this._propertyNamingStrategy, typeFactory2, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator, this._locale, this._timeZone, this._defaultBase64);
        return baseSettings;
    }

    public BaseSettings withTypeResolverBuilder(TypeResolverBuilder<?> typeResolverBuilder) {
        BaseSettings baseSettings;
        TypeResolverBuilder<?> typeResolverBuilder2 = typeResolverBuilder;
        if (this._typeResolverBuilder == typeResolverBuilder2) {
            return this;
        }
        new BaseSettings(this._classIntrospector, this._annotationIntrospector, this._visibilityChecker, this._propertyNamingStrategy, this._typeFactory, typeResolverBuilder2, this._dateFormat, this._handlerInstantiator, this._locale, this._timeZone, this._defaultBase64);
        return baseSettings;
    }

    public BaseSettings withDateFormat(DateFormat dateFormat) {
        BaseSettings baseSettings;
        DateFormat dateFormat2 = dateFormat;
        if (this._dateFormat == dateFormat2) {
            return this;
        }
        new BaseSettings(this._classIntrospector, this._annotationIntrospector, this._visibilityChecker, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, dateFormat2, this._handlerInstantiator, this._locale, this._timeZone, this._defaultBase64);
        return baseSettings;
    }

    public BaseSettings withHandlerInstantiator(HandlerInstantiator handlerInstantiator) {
        BaseSettings baseSettings;
        HandlerInstantiator handlerInstantiator2 = handlerInstantiator;
        if (this._handlerInstantiator == handlerInstantiator2) {
            return this;
        }
        new BaseSettings(this._classIntrospector, this._annotationIntrospector, this._visibilityChecker, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, handlerInstantiator2, this._locale, this._timeZone, this._defaultBase64);
        return baseSettings;
    }

    public BaseSettings with(Locale locale) {
        BaseSettings baseSettings;
        Locale locale2 = locale;
        if (this._locale == locale2) {
            return this;
        }
        new BaseSettings(this._classIntrospector, this._annotationIntrospector, this._visibilityChecker, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator, locale2, this._timeZone, this._defaultBase64);
        return baseSettings;
    }

    public BaseSettings with(TimeZone timeZone) {
        StdDateFormat stdDateFormat;
        BaseSettings baseSettings;
        Throwable th;
        TimeZone timeZone2 = timeZone;
        if (timeZone2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException();
            throw th2;
        }
        DateFormat dateFormat = this._dateFormat;
        if (dateFormat instanceof StdDateFormat) {
            stdDateFormat = ((StdDateFormat) dateFormat).withTimeZone(timeZone2);
        } else {
            stdDateFormat = (DateFormat) dateFormat.clone();
            stdDateFormat.setTimeZone(timeZone2);
        }
        new BaseSettings(this._classIntrospector, this._annotationIntrospector, this._visibilityChecker, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, stdDateFormat, this._handlerInstantiator, this._locale, timeZone2, this._defaultBase64);
        return baseSettings;
    }

    public BaseSettings with(Base64Variant base64Variant) {
        BaseSettings baseSettings;
        Base64Variant base64Variant2 = base64Variant;
        if (base64Variant2 == this._defaultBase64) {
            return this;
        }
        new BaseSettings(this._classIntrospector, this._annotationIntrospector, this._visibilityChecker, this._propertyNamingStrategy, this._typeFactory, this._typeResolverBuilder, this._dateFormat, this._handlerInstantiator, this._locale, this._timeZone, base64Variant2);
        return baseSettings;
    }

    public ClassIntrospector getClassIntrospector() {
        return this._classIntrospector;
    }

    public AnnotationIntrospector getAnnotationIntrospector() {
        return this._annotationIntrospector;
    }

    public VisibilityChecker<?> getVisibilityChecker() {
        return this._visibilityChecker;
    }

    public PropertyNamingStrategy getPropertyNamingStrategy() {
        return this._propertyNamingStrategy;
    }

    public TypeFactory getTypeFactory() {
        return this._typeFactory;
    }

    public TypeResolverBuilder<?> getTypeResolverBuilder() {
        return this._typeResolverBuilder;
    }

    public DateFormat getDateFormat() {
        return this._dateFormat;
    }

    public HandlerInstantiator getHandlerInstantiator() {
        return this._handlerInstantiator;
    }

    public Locale getLocale() {
        return this._locale;
    }

    public TimeZone getTimeZone() {
        return this._timeZone;
    }

    public Base64Variant getBase64Variant() {
        return this._defaultBase64;
    }
}

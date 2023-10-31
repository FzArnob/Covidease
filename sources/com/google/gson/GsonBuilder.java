package com.google.gson;

import com.google.gson.internal.C$Gson$Preconditions;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.bind.TreeTypeAdapter;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class GsonBuilder {
    private boolean complexMapKeySerialization;
    private String datePattern;
    private int dateStyle;
    private boolean escapeHtmlChars;
    private Excluder excluder = Excluder.DEFAULT;
    private final List<TypeAdapterFactory> factories;
    private FieldNamingStrategy fieldNamingPolicy = FieldNamingPolicy.IDENTITY;
    private boolean generateNonExecutableJson;
    private final List<TypeAdapterFactory> hierarchyFactories;
    private final Map<Type, InstanceCreator<?>> instanceCreators;
    private boolean lenient;
    private LongSerializationPolicy longSerializationPolicy = LongSerializationPolicy.DEFAULT;
    private boolean prettyPrinting;
    private boolean serializeNulls;
    private boolean serializeSpecialFloatingPointValues;
    private int timeStyle;

    public GsonBuilder() {
        Map<Type, InstanceCreator<?>> map;
        List<TypeAdapterFactory> list;
        List<TypeAdapterFactory> list2;
        new HashMap();
        this.instanceCreators = map;
        new ArrayList();
        this.factories = list;
        new ArrayList();
        this.hierarchyFactories = list2;
        this.serializeNulls = false;
        this.dateStyle = 2;
        this.timeStyle = 2;
        this.complexMapKeySerialization = false;
        this.serializeSpecialFloatingPointValues = false;
        this.escapeHtmlChars = true;
        this.prettyPrinting = false;
        this.generateNonExecutableJson = false;
        this.lenient = false;
    }

    GsonBuilder(Gson gson) {
        Map<Type, InstanceCreator<?>> map;
        List<TypeAdapterFactory> list;
        List<TypeAdapterFactory> list2;
        Gson gson2 = gson;
        new HashMap();
        this.instanceCreators = map;
        new ArrayList();
        this.factories = list;
        new ArrayList();
        this.hierarchyFactories = list2;
        this.serializeNulls = false;
        this.dateStyle = 2;
        this.timeStyle = 2;
        this.complexMapKeySerialization = false;
        this.serializeSpecialFloatingPointValues = false;
        this.escapeHtmlChars = true;
        this.prettyPrinting = false;
        this.generateNonExecutableJson = false;
        this.lenient = false;
        this.excluder = gson2.excluder;
        this.fieldNamingPolicy = gson2.fieldNamingStrategy;
        this.instanceCreators.putAll(gson2.instanceCreators);
        this.serializeNulls = gson2.serializeNulls;
        this.complexMapKeySerialization = gson2.complexMapKeySerialization;
        this.generateNonExecutableJson = gson2.generateNonExecutableJson;
        this.escapeHtmlChars = gson2.htmlSafe;
        this.prettyPrinting = gson2.prettyPrinting;
        this.lenient = gson2.lenient;
        this.serializeSpecialFloatingPointValues = gson2.serializeSpecialFloatingPointValues;
        this.longSerializationPolicy = gson2.longSerializationPolicy;
        this.datePattern = gson2.datePattern;
        this.dateStyle = gson2.dateStyle;
        this.timeStyle = gson2.timeStyle;
        boolean addAll = this.factories.addAll(gson2.builderFactories);
        boolean addAll2 = this.hierarchyFactories.addAll(gson2.builderHierarchyFactories);
    }

    public GsonBuilder setVersion(double ignoreVersionsAfter) {
        this.excluder = this.excluder.withVersion(ignoreVersionsAfter);
        return this;
    }

    public GsonBuilder excludeFieldsWithModifiers(int... modifiers) {
        this.excluder = this.excluder.withModifiers(modifiers);
        return this;
    }

    public GsonBuilder generateNonExecutableJson() {
        this.generateNonExecutableJson = true;
        return this;
    }

    public GsonBuilder excludeFieldsWithoutExposeAnnotation() {
        this.excluder = this.excluder.excludeFieldsWithoutExposeAnnotation();
        return this;
    }

    public GsonBuilder serializeNulls() {
        this.serializeNulls = true;
        return this;
    }

    public GsonBuilder enableComplexMapKeySerialization() {
        this.complexMapKeySerialization = true;
        return this;
    }

    public GsonBuilder disableInnerClassSerialization() {
        this.excluder = this.excluder.disableInnerClassSerialization();
        return this;
    }

    public GsonBuilder setLongSerializationPolicy(LongSerializationPolicy serializationPolicy) {
        this.longSerializationPolicy = serializationPolicy;
        return this;
    }

    public GsonBuilder setFieldNamingPolicy(FieldNamingPolicy namingConvention) {
        this.fieldNamingPolicy = namingConvention;
        return this;
    }

    public GsonBuilder setFieldNamingStrategy(FieldNamingStrategy fieldNamingStrategy) {
        this.fieldNamingPolicy = fieldNamingStrategy;
        return this;
    }

    public GsonBuilder setExclusionStrategies(ExclusionStrategy... strategies) {
        ExclusionStrategy[] exclusionStrategyArr = strategies;
        int length = exclusionStrategyArr.length;
        for (int i = 0; i < length; i++) {
            this.excluder = this.excluder.withExclusionStrategy(exclusionStrategyArr[i], true, true);
        }
        return this;
    }

    public GsonBuilder addSerializationExclusionStrategy(ExclusionStrategy strategy) {
        this.excluder = this.excluder.withExclusionStrategy(strategy, true, false);
        return this;
    }

    public GsonBuilder addDeserializationExclusionStrategy(ExclusionStrategy strategy) {
        this.excluder = this.excluder.withExclusionStrategy(strategy, false, true);
        return this;
    }

    public GsonBuilder setPrettyPrinting() {
        this.prettyPrinting = true;
        return this;
    }

    public GsonBuilder setLenient() {
        this.lenient = true;
        return this;
    }

    public GsonBuilder disableHtmlEscaping() {
        this.escapeHtmlChars = false;
        return this;
    }

    public GsonBuilder setDateFormat(String pattern) {
        this.datePattern = pattern;
        return this;
    }

    public GsonBuilder setDateFormat(int style) {
        this.dateStyle = style;
        this.datePattern = null;
        return this;
    }

    public GsonBuilder setDateFormat(int dateStyle2, int timeStyle2) {
        this.dateStyle = dateStyle2;
        this.timeStyle = timeStyle2;
        this.datePattern = null;
        return this;
    }

    public GsonBuilder registerTypeAdapter(Type type, Object obj) {
        Type type2 = type;
        Object typeAdapter = obj;
        C$Gson$Preconditions.checkArgument((typeAdapter instanceof JsonSerializer) || (typeAdapter instanceof JsonDeserializer) || (typeAdapter instanceof InstanceCreator) || (typeAdapter instanceof TypeAdapter));
        if (typeAdapter instanceof InstanceCreator) {
            InstanceCreator<?> put = this.instanceCreators.put(type2, (InstanceCreator) typeAdapter);
        }
        if ((typeAdapter instanceof JsonSerializer) || (typeAdapter instanceof JsonDeserializer)) {
            boolean add = this.factories.add(TreeTypeAdapter.newFactoryWithMatchRawType(TypeToken.get(type2), typeAdapter));
        }
        if (typeAdapter instanceof TypeAdapter) {
            boolean add2 = this.factories.add(TypeAdapters.newFactory(TypeToken.get(type2), (TypeAdapter) typeAdapter));
        }
        return this;
    }

    public GsonBuilder registerTypeAdapterFactory(TypeAdapterFactory factory) {
        boolean add = this.factories.add(factory);
        return this;
    }

    public GsonBuilder registerTypeHierarchyAdapter(Class<?> cls, Object obj) {
        Class<?> baseType = cls;
        Object typeAdapter = obj;
        C$Gson$Preconditions.checkArgument((typeAdapter instanceof JsonSerializer) || (typeAdapter instanceof JsonDeserializer) || (typeAdapter instanceof TypeAdapter));
        if ((typeAdapter instanceof JsonDeserializer) || (typeAdapter instanceof JsonSerializer)) {
            boolean add = this.hierarchyFactories.add(TreeTypeAdapter.newTypeHierarchyFactory(baseType, typeAdapter));
        }
        if (typeAdapter instanceof TypeAdapter) {
            boolean add2 = this.factories.add(TypeAdapters.newTypeHierarchyFactory(baseType, (TypeAdapter) typeAdapter));
        }
        return this;
    }

    public GsonBuilder serializeSpecialFloatingPointValues() {
        this.serializeSpecialFloatingPointValues = true;
        return this;
    }

    public Gson create() {
        List<TypeAdapterFactory> list;
        List<TypeAdapterFactory> list2;
        Gson gson;
        new ArrayList<>(this.factories.size() + this.hierarchyFactories.size() + 3);
        List<TypeAdapterFactory> factories2 = list;
        boolean addAll = factories2.addAll(this.factories);
        Collections.reverse(factories2);
        new ArrayList<>(this.hierarchyFactories);
        List<TypeAdapterFactory> hierarchyFactories2 = list2;
        Collections.reverse(hierarchyFactories2);
        boolean addAll2 = factories2.addAll(hierarchyFactories2);
        addTypeAdaptersForDate(this.datePattern, this.dateStyle, this.timeStyle, factories2);
        new Gson(this.excluder, this.fieldNamingPolicy, this.instanceCreators, this.serializeNulls, this.complexMapKeySerialization, this.generateNonExecutableJson, this.escapeHtmlChars, this.prettyPrinting, this.lenient, this.serializeSpecialFloatingPointValues, this.longSerializationPolicy, this.datePattern, this.dateStyle, this.timeStyle, this.factories, this.hierarchyFactories, factories2);
        return gson;
    }

    private void addTypeAdaptersForDate(String str, int i, int i2, List<TypeAdapterFactory> list) {
        DefaultDateTypeAdapter defaultDateTypeAdapter;
        DefaultDateTypeAdapter dateTypeAdapter;
        TypeAdapter<Timestamp> typeAdapter;
        TypeAdapter<Timestamp> timestampTypeAdapter;
        TypeAdapter<Date> typeAdapter2;
        TypeAdapter<Date> javaSqlDateTypeAdapter;
        DefaultDateTypeAdapter defaultDateTypeAdapter2;
        TypeAdapter<Timestamp> typeAdapter3;
        TypeAdapter<Date> typeAdapter4;
        String datePattern2 = str;
        int dateStyle2 = i;
        int timeStyle2 = i2;
        List<TypeAdapterFactory> factories2 = list;
        if (datePattern2 != null && !"".equals(datePattern2.trim())) {
            new DefaultDateTypeAdapter((Class<? extends java.util.Date>) java.util.Date.class, datePattern2);
            dateTypeAdapter = defaultDateTypeAdapter2;
            new DefaultDateTypeAdapter((Class<? extends java.util.Date>) Timestamp.class, datePattern2);
            timestampTypeAdapter = typeAdapter3;
            new DefaultDateTypeAdapter((Class<? extends java.util.Date>) Date.class, datePattern2);
            javaSqlDateTypeAdapter = typeAdapter4;
        } else if (dateStyle2 != 2 && timeStyle2 != 2) {
            new DefaultDateTypeAdapter(java.util.Date.class, dateStyle2, timeStyle2);
            dateTypeAdapter = defaultDateTypeAdapter;
            new DefaultDateTypeAdapter(Timestamp.class, dateStyle2, timeStyle2);
            timestampTypeAdapter = typeAdapter;
            new DefaultDateTypeAdapter(Date.class, dateStyle2, timeStyle2);
            javaSqlDateTypeAdapter = typeAdapter2;
        } else {
            return;
        }
        boolean add = factories2.add(TypeAdapters.newFactory(java.util.Date.class, dateTypeAdapter));
        boolean add2 = factories2.add(TypeAdapters.newFactory(Timestamp.class, timestampTypeAdapter));
        boolean add3 = factories2.add(TypeAdapters.newFactory(Date.class, javaSqlDateTypeAdapter));
    }
}

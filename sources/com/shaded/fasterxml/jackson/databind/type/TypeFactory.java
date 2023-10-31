package com.shaded.fasterxml.jackson.databind.type;

import com.shaded.fasterxml.jackson.core.type.TypeReference;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.util.ArrayBuilders;
import com.shaded.fasterxml.jackson.databind.util.LRUMap;
import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class TypeFactory implements Serializable {
    protected static final SimpleType CORE_TYPE_BOOL;
    protected static final SimpleType CORE_TYPE_INT;
    protected static final SimpleType CORE_TYPE_LONG;
    protected static final SimpleType CORE_TYPE_STRING;
    private static final JavaType[] NO_TYPES = new JavaType[0];
    protected static final TypeFactory instance;
    private static final long serialVersionUID = 1;
    protected transient HierarchicType _cachedArrayListType;
    protected transient HierarchicType _cachedHashMapType;
    protected final TypeModifier[] _modifiers;
    protected final TypeParser _parser;
    protected final LRUMap<ClassKey, JavaType> _typeCache;

    static {
        TypeFactory typeFactory;
        SimpleType simpleType;
        SimpleType simpleType2;
        SimpleType simpleType3;
        SimpleType simpleType4;
        new TypeFactory();
        instance = typeFactory;
        new SimpleType(String.class);
        CORE_TYPE_STRING = simpleType;
        new SimpleType(Boolean.TYPE);
        CORE_TYPE_BOOL = simpleType2;
        new SimpleType(Integer.TYPE);
        CORE_TYPE_INT = simpleType3;
        new SimpleType(Long.TYPE);
        CORE_TYPE_LONG = simpleType4;
    }

    private TypeFactory() {
        LRUMap<ClassKey, JavaType> lRUMap;
        TypeParser typeParser;
        new LRUMap<>(16, 100);
        this._typeCache = lRUMap;
        new TypeParser(this);
        this._parser = typeParser;
        this._modifiers = null;
    }

    protected TypeFactory(TypeParser typeParser, TypeModifier[] typeModifierArr) {
        LRUMap<ClassKey, JavaType> lRUMap;
        new LRUMap<>(16, 100);
        this._typeCache = lRUMap;
        this._parser = typeParser;
        this._modifiers = typeModifierArr;
    }

    public TypeFactory withModifier(TypeModifier typeModifier) {
        TypeFactory typeFactory;
        TypeFactory typeFactory2;
        TypeModifier typeModifier2 = typeModifier;
        if (this._modifiers == null) {
            TypeFactory typeFactory3 = typeFactory2;
            new TypeFactory(this._parser, new TypeModifier[]{typeModifier2});
            return typeFactory3;
        }
        new TypeFactory(this._parser, (TypeModifier[]) ArrayBuilders.insertInListNoDup(this._modifiers, typeModifier2));
        return typeFactory;
    }

    public static TypeFactory defaultInstance() {
        return instance;
    }

    public static JavaType unknownType() {
        return defaultInstance()._unknownType();
    }

    public static Class<?> rawClass(Type type) {
        Type type2 = type;
        if (type2 instanceof Class) {
            return (Class) type2;
        }
        return defaultInstance().constructType(type2).getRawClass();
    }

    public JavaType constructSpecializedType(JavaType javaType, Class<?> cls) {
        TypeBindings typeBindings;
        Throwable th;
        StringBuilder sb;
        JavaType javaType2 = javaType;
        Class<?> cls2 = cls;
        if (!(javaType2 instanceof SimpleType) || (!cls2.isArray() && !Map.class.isAssignableFrom(cls2) && !Collection.class.isAssignableFrom(cls2))) {
            return javaType2.narrowBy(cls2);
        }
        if (!javaType2.getRawClass().isAssignableFrom(cls2)) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Class ").append(cls2.getClass().getName()).append(" not subtype of ").append(javaType2).toString());
            throw th2;
        }
        new TypeBindings(this, javaType2.getRawClass());
        JavaType _fromClass = _fromClass(cls2, typeBindings);
        Object valueHandler = javaType2.getValueHandler();
        if (valueHandler != null) {
            _fromClass = _fromClass.withValueHandler(valueHandler);
        }
        Object typeHandler = javaType2.getTypeHandler();
        if (typeHandler != null) {
            _fromClass = _fromClass.withTypeHandler(typeHandler);
        }
        return _fromClass;
    }

    public JavaType constructFromCanonical(String str) throws IllegalArgumentException {
        return this._parser.parse(str);
    }

    public JavaType[] findTypeParameters(JavaType javaType, Class<?> cls) {
        TypeBindings typeBindings;
        JavaType javaType2 = javaType;
        Class<?> cls2 = cls;
        Class<?> rawClass = javaType2.getRawClass();
        if (rawClass == cls2) {
            int containedTypeCount = javaType2.containedTypeCount();
            if (containedTypeCount == 0) {
                return null;
            }
            JavaType[] javaTypeArr = new JavaType[containedTypeCount];
            for (int i = 0; i < containedTypeCount; i++) {
                javaTypeArr[i] = javaType2.containedType(i);
            }
            return javaTypeArr;
        }
        new TypeBindings(this, javaType2);
        return findTypeParameters(rawClass, cls2, typeBindings);
    }

    public JavaType[] findTypeParameters(Class<?> cls, Class<?> cls2) {
        TypeBindings typeBindings;
        Class<?> cls3 = cls;
        new TypeBindings(this, cls3);
        return findTypeParameters(cls3, cls2, typeBindings);
    }

    public JavaType[] findTypeParameters(Class<?> cls, Class<?> cls2, TypeBindings typeBindings) {
        TypeBindings typeBindings2;
        Throwable th;
        StringBuilder sb;
        Class<?> cls3 = cls;
        Class<?> cls4 = cls2;
        TypeBindings typeBindings3 = typeBindings;
        HierarchicType _findSuperTypeChain = _findSuperTypeChain(cls3, cls4);
        if (_findSuperTypeChain == null) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Class ").append(cls3.getName()).append(" is not a subtype of ").append(cls4.getName()).toString());
            throw th2;
        }
        HierarchicType hierarchicType = _findSuperTypeChain;
        while (hierarchicType.getSuperType() != null) {
            hierarchicType = hierarchicType.getSuperType();
            Class<?> rawClass = hierarchicType.getRawClass();
            new TypeBindings(this, rawClass);
            TypeBindings typeBindings4 = typeBindings2;
            if (hierarchicType.isGeneric()) {
                Type[] actualTypeArguments = hierarchicType.asGeneric().getActualTypeArguments();
                TypeVariable[] typeParameters = rawClass.getTypeParameters();
                int length = actualTypeArguments.length;
                for (int i = 0; i < length; i++) {
                    typeBindings4.addBinding(typeParameters[i].getName(), _constructType(actualTypeArguments[i], typeBindings3));
                }
            }
            typeBindings3 = typeBindings4;
        }
        if (!hierarchicType.isGeneric()) {
            return null;
        }
        return typeBindings3.typesAsArray();
    }

    public JavaType moreSpecificType(JavaType javaType, JavaType javaType2) {
        JavaType javaType3 = javaType;
        JavaType javaType4 = javaType2;
        if (javaType3 == null) {
            return javaType4;
        }
        if (javaType4 == null) {
            return javaType3;
        }
        Class<?> rawClass = javaType3.getRawClass();
        Class<?> rawClass2 = javaType4.getRawClass();
        if (rawClass == rawClass2) {
            return javaType3;
        }
        if (rawClass.isAssignableFrom(rawClass2)) {
            return javaType4;
        }
        return javaType3;
    }

    public JavaType constructType(Type type) {
        return _constructType(type, (TypeBindings) null);
    }

    public JavaType constructType(Type type, TypeBindings typeBindings) {
        return _constructType(type, typeBindings);
    }

    public JavaType constructType(TypeReference<?> typeReference) {
        return _constructType(typeReference.getType(), (TypeBindings) null);
    }

    public JavaType constructType(Type type, Class<?> cls) {
        TypeBindings typeBindings;
        TypeBindings typeBindings2;
        Type type2 = type;
        Class<?> cls2 = cls;
        if (cls2 == null) {
            typeBindings2 = null;
        } else {
            typeBindings2 = typeBindings;
            new TypeBindings(this, cls2);
        }
        return _constructType(type2, typeBindings2);
    }

    public JavaType constructType(Type type, JavaType javaType) {
        TypeBindings typeBindings;
        TypeBindings typeBindings2;
        Type type2 = type;
        JavaType javaType2 = javaType;
        if (javaType2 == null) {
            typeBindings2 = null;
        } else {
            typeBindings2 = typeBindings;
            new TypeBindings(this, javaType2);
        }
        return _constructType(type2, typeBindings2);
    }

    /* access modifiers changed from: protected */
    public JavaType _constructType(Type type, TypeBindings typeBindings) {
        StringBuilder sb;
        JavaType _fromWildcard;
        Type type2 = type;
        TypeBindings typeBindings2 = typeBindings;
        if (type2 instanceof Class) {
            _fromWildcard = _fromClass((Class) type2, typeBindings2);
        } else if (type2 instanceof ParameterizedType) {
            _fromWildcard = _fromParamType((ParameterizedType) type2, typeBindings2);
        } else if (type2 instanceof JavaType) {
            return (JavaType) type2;
        } else {
            if (type2 instanceof GenericArrayType) {
                _fromWildcard = _fromArrayType((GenericArrayType) type2, typeBindings2);
            } else if (type2 instanceof TypeVariable) {
                _fromWildcard = _fromVariable((TypeVariable) type2, typeBindings2);
            } else if (type2 instanceof WildcardType) {
                _fromWildcard = _fromWildcard((WildcardType) type2, typeBindings2);
            } else {
                IllegalArgumentException illegalArgumentException = r13;
                new StringBuilder();
                IllegalArgumentException illegalArgumentException2 = new IllegalArgumentException(sb.append("Unrecognized Type: ").append(type2 == null ? "[null]" : type2.toString()).toString());
                throw illegalArgumentException;
            }
        }
        if (this._modifiers != null && !_fromWildcard.isContainerType()) {
            TypeModifier[] typeModifierArr = this._modifiers;
            int length = typeModifierArr.length;
            for (int i = 0; i < length; i++) {
                _fromWildcard = typeModifierArr[i].modifyType(_fromWildcard, type2, typeBindings2, this);
            }
        }
        return _fromWildcard;
    }

    public ArrayType constructArrayType(Class<?> cls) {
        return ArrayType.construct(_constructType(cls, (TypeBindings) null), (Object) null, (Object) null);
    }

    public ArrayType constructArrayType(JavaType javaType) {
        return ArrayType.construct(javaType, (Object) null, (Object) null);
    }

    public CollectionType constructCollectionType(Class<? extends Collection> cls, Class<?> cls2) {
        return CollectionType.construct(cls, constructType((Type) cls2));
    }

    public CollectionType constructCollectionType(Class<? extends Collection> cls, JavaType javaType) {
        return CollectionType.construct(cls, javaType);
    }

    public CollectionLikeType constructCollectionLikeType(Class<?> cls, Class<?> cls2) {
        return CollectionLikeType.construct(cls, constructType((Type) cls2));
    }

    public CollectionLikeType constructCollectionLikeType(Class<?> cls, JavaType javaType) {
        return CollectionLikeType.construct(cls, javaType);
    }

    public MapType constructMapType(Class<? extends Map> cls, JavaType javaType, JavaType javaType2) {
        return MapType.construct(cls, javaType, javaType2);
    }

    public MapType constructMapType(Class<? extends Map> cls, Class<?> cls2, Class<?> cls3) {
        return MapType.construct(cls, constructType((Type) cls2), constructType((Type) cls3));
    }

    public MapLikeType constructMapLikeType(Class<?> cls, JavaType javaType, JavaType javaType2) {
        return MapLikeType.construct(cls, javaType, javaType2);
    }

    public MapLikeType constructMapLikeType(Class<?> cls, Class<?> cls2, Class<?> cls3) {
        return MapType.construct(cls, constructType((Type) cls2), constructType((Type) cls3));
    }

    public JavaType constructSimpleType(Class<?> cls, JavaType[] javaTypeArr) {
        JavaType javaType;
        Throwable th;
        StringBuilder sb;
        Class<?> cls2 = cls;
        JavaType[] javaTypeArr2 = javaTypeArr;
        TypeVariable[] typeParameters = cls2.getTypeParameters();
        if (typeParameters.length != javaTypeArr2.length) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Parameter type mismatch for ").append(cls2.getName()).append(": expected ").append(typeParameters.length).append(" parameters, was given ").append(javaTypeArr2.length).toString());
            throw th2;
        }
        String[] strArr = new String[typeParameters.length];
        int length = typeParameters.length;
        for (int i = 0; i < length; i++) {
            strArr[i] = typeParameters[i].getName();
        }
        new SimpleType(cls2, strArr, javaTypeArr2, (Object) null, (Object) null, false);
        return javaType;
    }

    public JavaType uncheckedSimpleType(Class<?> cls) {
        JavaType javaType;
        new SimpleType(cls);
        return javaType;
    }

    public JavaType constructParametricType(Class<?> cls, Class<?>... clsArr) {
        Class<?> cls2 = cls;
        Class<?>[] clsArr2 = clsArr;
        int length = clsArr2.length;
        JavaType[] javaTypeArr = new JavaType[length];
        for (int i = 0; i < length; i++) {
            javaTypeArr[i] = _fromClass(clsArr2[i], (TypeBindings) null);
        }
        return constructParametricType(cls2, javaTypeArr);
    }

    public JavaType constructParametricType(Class<?> cls, JavaType... javaTypeArr) {
        ArrayType constructSimpleType;
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        Throwable th3;
        StringBuilder sb3;
        Class<?> cls2 = cls;
        JavaType[] javaTypeArr2 = javaTypeArr;
        if (cls2.isArray()) {
            if (javaTypeArr2.length != 1) {
                Throwable th4 = th3;
                new StringBuilder();
                new IllegalArgumentException(sb3.append("Need exactly 1 parameter type for arrays (").append(cls2.getName()).append(")").toString());
                throw th4;
            }
            constructSimpleType = constructArrayType(javaTypeArr2[0]);
        } else if (Map.class.isAssignableFrom(cls2)) {
            if (javaTypeArr2.length != 2) {
                Throwable th5 = th2;
                new StringBuilder();
                new IllegalArgumentException(sb2.append("Need exactly 2 parameter types for Map types (").append(cls2.getName()).append(")").toString());
                throw th5;
            }
            constructSimpleType = constructMapType((Class<? extends Map>) cls2, javaTypeArr2[0], javaTypeArr2[1]);
        } else if (!Collection.class.isAssignableFrom(cls2)) {
            constructSimpleType = constructSimpleType(cls2, javaTypeArr2);
        } else if (javaTypeArr2.length != 1) {
            Throwable th6 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Need exactly 1 parameter type for Collection types (").append(cls2.getName()).append(")").toString());
            throw th6;
        } else {
            constructSimpleType = constructCollectionType((Class<? extends Collection>) cls2, javaTypeArr2[0]);
        }
        return constructSimpleType;
    }

    public CollectionType constructRawCollectionType(Class<? extends Collection> cls) {
        return CollectionType.construct(cls, unknownType());
    }

    public CollectionLikeType constructRawCollectionLikeType(Class<?> cls) {
        return CollectionLikeType.construct(cls, unknownType());
    }

    public MapType constructRawMapType(Class<? extends Map> cls) {
        return MapType.construct(cls, unknownType(), unknownType());
    }

    public MapLikeType constructRawMapLikeType(Class<?> cls) {
        return MapLikeType.construct(cls, unknownType(), unknownType());
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    public JavaType _fromClass(Class<?> cls, TypeBindings typeBindings) {
        Object obj;
        JavaType javaType;
        ArrayType arrayType;
        JavaType javaType2;
        Class<?> cls2 = cls;
        TypeBindings typeBindings2 = typeBindings;
        if (cls2 == String.class) {
            return CORE_TYPE_STRING;
        }
        if (cls2 == Boolean.TYPE) {
            return CORE_TYPE_BOOL;
        }
        if (cls2 == Integer.TYPE) {
            return CORE_TYPE_INT;
        }
        if (cls2 == Long.TYPE) {
            return CORE_TYPE_LONG;
        }
        new ClassKey(cls2);
        Object obj2 = obj;
        LRUMap<ClassKey, JavaType> lRUMap = this._typeCache;
        LRUMap<ClassKey, JavaType> lRUMap2 = lRUMap;
        synchronized (lRUMap) {
            try {
                JavaType javaType3 = (JavaType) this._typeCache.get(obj2);
                if (javaType3 != null) {
                    return javaType3;
                }
                if (cls2.isArray()) {
                    arrayType = ArrayType.construct(_constructType(cls2.getComponentType(), (TypeBindings) null), (Object) null, (Object) null);
                } else if (cls2.isEnum()) {
                    new SimpleType(cls2);
                    arrayType = javaType2;
                } else if (Map.class.isAssignableFrom(cls2)) {
                    arrayType = _mapType(cls2);
                } else if (Collection.class.isAssignableFrom(cls2)) {
                    arrayType = _collectionType(cls2);
                } else {
                    new SimpleType(cls2);
                    arrayType = javaType;
                }
                LRUMap<ClassKey, JavaType> lRUMap3 = this._typeCache;
                LRUMap<ClassKey, JavaType> lRUMap4 = lRUMap3;
                synchronized (lRUMap3) {
                    try {
                        Object put = this._typeCache.put(obj2, arrayType);
                        return arrayType;
                    } catch (Throwable th) {
                        while (true) {
                            Throwable th2 = th;
                            LRUMap<ClassKey, JavaType> lRUMap5 = lRUMap4;
                            throw th2;
                        }
                    }
                }
            } catch (Throwable th3) {
                Throwable th4 = th3;
                LRUMap<ClassKey, JavaType> lRUMap6 = lRUMap2;
                throw th4;
            }
        }
    }

    /* access modifiers changed from: protected */
    public JavaType _fromParameterizedClass(Class<?> cls, List<JavaType> list) {
        JavaType javaType;
        JavaType javaType2;
        Class<?> cls2 = cls;
        List<JavaType> list2 = list;
        if (cls2.isArray()) {
            return ArrayType.construct(_constructType(cls2.getComponentType(), (TypeBindings) null), (Object) null, (Object) null);
        }
        if (cls2.isEnum()) {
            new SimpleType(cls2);
            return javaType2;
        } else if (Map.class.isAssignableFrom(cls2)) {
            if (list2.size() <= 0) {
                return _mapType(cls2);
            }
            return MapType.construct(cls2, list2.get(0), list2.size() >= 2 ? list2.get(1) : _unknownType());
        } else if (Collection.class.isAssignableFrom(cls2)) {
            if (list2.size() >= 1) {
                return CollectionType.construct(cls2, list2.get(0));
            }
            return _collectionType(cls2);
        } else if (list2.size() == 0) {
            new SimpleType(cls2);
            return javaType;
        } else {
            return constructSimpleType(cls2, (JavaType[]) list2.toArray(new JavaType[list2.size()]));
        }
    }

    /* access modifiers changed from: protected */
    public JavaType _fromParamType(ParameterizedType parameterizedType, TypeBindings typeBindings) {
        JavaType[] javaTypeArr;
        JavaType javaType;
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        ParameterizedType parameterizedType2 = parameterizedType;
        TypeBindings typeBindings2 = typeBindings;
        Class cls = (Class) parameterizedType2.getRawType();
        Type[] actualTypeArguments = parameterizedType2.getActualTypeArguments();
        int length = actualTypeArguments == null ? 0 : actualTypeArguments.length;
        if (length == 0) {
            javaTypeArr = NO_TYPES;
        } else {
            javaTypeArr = new JavaType[length];
            for (int i = 0; i < length; i++) {
                javaTypeArr[i] = _constructType(actualTypeArguments[i], typeBindings2);
            }
        }
        if (Map.class.isAssignableFrom(cls)) {
            JavaType[] findTypeParameters = findTypeParameters(constructSimpleType(cls, javaTypeArr), (Class<?>) Map.class);
            if (findTypeParameters.length == 2) {
                return MapType.construct(cls, findTypeParameters[0], findTypeParameters[1]);
            }
            Throwable th3 = th2;
            new StringBuilder();
            new IllegalArgumentException(sb2.append("Could not find 2 type parameters for Map class ").append(cls.getName()).append(" (found ").append(findTypeParameters.length).append(")").toString());
            throw th3;
        } else if (Collection.class.isAssignableFrom(cls)) {
            JavaType[] findTypeParameters2 = findTypeParameters(constructSimpleType(cls, javaTypeArr), (Class<?>) Collection.class);
            if (findTypeParameters2.length == 1) {
                return CollectionType.construct(cls, findTypeParameters2[0]);
            }
            Throwable th4 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Could not find 1 type parameter for Collection class ").append(cls.getName()).append(" (found ").append(findTypeParameters2.length).append(")").toString());
            throw th4;
        } else if (length != 0) {
            return constructSimpleType(cls, javaTypeArr);
        } else {
            new SimpleType(cls);
            return javaType;
        }
    }

    /* access modifiers changed from: protected */
    public JavaType _fromArrayType(GenericArrayType genericArrayType, TypeBindings typeBindings) {
        return ArrayType.construct(_constructType(genericArrayType.getGenericComponentType(), typeBindings), (Object) null, (Object) null);
    }

    /* access modifiers changed from: protected */
    public JavaType _fromVariable(TypeVariable<?> typeVariable, TypeBindings typeBindings) {
        TypeVariable<?> typeVariable2 = typeVariable;
        TypeBindings typeBindings2 = typeBindings;
        if (typeBindings2 == null) {
            return _unknownType();
        }
        String name = typeVariable2.getName();
        JavaType findType = typeBindings2.findType(name);
        if (findType != null) {
            return findType;
        }
        Type[] bounds = typeVariable2.getBounds();
        typeBindings2._addPlaceholder(name);
        return _constructType(bounds[0], typeBindings2);
    }

    /* access modifiers changed from: protected */
    public JavaType _fromWildcard(WildcardType wildcardType, TypeBindings typeBindings) {
        return _constructType(wildcardType.getUpperBounds()[0], typeBindings);
    }

    private JavaType _mapType(Class<?> cls) {
        Throwable th;
        StringBuilder sb;
        Class<?> cls2 = cls;
        JavaType[] findTypeParameters = findTypeParameters(cls2, (Class<?>) Map.class);
        if (findTypeParameters == null) {
            return MapType.construct(cls2, _unknownType(), _unknownType());
        }
        if (findTypeParameters.length == 2) {
            return MapType.construct(cls2, findTypeParameters[0], findTypeParameters[1]);
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalArgumentException(sb.append("Strange Map type ").append(cls2.getName()).append(": can not determine type parameters").toString());
        throw th2;
    }

    private JavaType _collectionType(Class<?> cls) {
        Throwable th;
        StringBuilder sb;
        Class<?> cls2 = cls;
        JavaType[] findTypeParameters = findTypeParameters(cls2, (Class<?>) Collection.class);
        if (findTypeParameters == null) {
            return CollectionType.construct(cls2, _unknownType());
        }
        if (findTypeParameters.length == 1) {
            return CollectionType.construct(cls2, findTypeParameters[0]);
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalArgumentException(sb.append("Strange Collection type ").append(cls2.getName()).append(": can not determine type parameters").toString());
        throw th2;
    }

    /* access modifiers changed from: protected */
    public JavaType _resolveVariableViaSubTypes(HierarchicType hierarchicType, String str, TypeBindings typeBindings) {
        HierarchicType hierarchicType2 = hierarchicType;
        String str2 = str;
        TypeBindings typeBindings2 = typeBindings;
        if (hierarchicType2 != null && hierarchicType2.isGeneric()) {
            TypeVariable[] typeParameters = hierarchicType2.getRawClass().getTypeParameters();
            int length = typeParameters.length;
            for (int i = 0; i < length; i++) {
                if (str2.equals(typeParameters[i].getName())) {
                    Type type = hierarchicType2.asGeneric().getActualTypeArguments()[i];
                    if (type instanceof TypeVariable) {
                        return _resolveVariableViaSubTypes(hierarchicType2.getSubType(), ((TypeVariable) type).getName(), typeBindings2);
                    }
                    return _constructType(type, typeBindings2);
                }
            }
        }
        return _unknownType();
    }

    /* access modifiers changed from: protected */
    public JavaType _unknownType() {
        JavaType javaType;
        new SimpleType(Object.class);
        return javaType;
    }

    /* access modifiers changed from: protected */
    public HierarchicType _findSuperTypeChain(Class<?> cls, Class<?> cls2) {
        Class<?> cls3 = cls;
        Class<?> cls4 = cls2;
        if (cls4.isInterface()) {
            return _findSuperInterfaceChain(cls3, cls4);
        }
        return _findSuperClassChain(cls3, cls4);
    }

    /* access modifiers changed from: protected */
    public HierarchicType _findSuperClassChain(Type type, Class<?> cls) {
        HierarchicType hierarchicType;
        HierarchicType _findSuperClassChain;
        Class<?> cls2 = cls;
        new HierarchicType(type);
        HierarchicType hierarchicType2 = hierarchicType;
        Class<?> rawClass = hierarchicType2.getRawClass();
        if (rawClass == cls2) {
            return hierarchicType2;
        }
        Type genericSuperclass = rawClass.getGenericSuperclass();
        if (genericSuperclass == null || (_findSuperClassChain = _findSuperClassChain(genericSuperclass, cls2)) == null) {
            return null;
        }
        _findSuperClassChain.setSubType(hierarchicType2);
        hierarchicType2.setSuperType(_findSuperClassChain);
        return hierarchicType2;
    }

    /* access modifiers changed from: protected */
    public HierarchicType _findSuperInterfaceChain(Type type, Class<?> cls) {
        HierarchicType hierarchicType;
        HierarchicType hierarchicType2;
        Type type2 = type;
        Class<?> cls2 = cls;
        new HierarchicType(type2);
        HierarchicType hierarchicType3 = hierarchicType;
        Class<?> rawClass = hierarchicType3.getRawClass();
        if (rawClass == cls2) {
            new HierarchicType(type2);
            return hierarchicType2;
        } else if (rawClass == HashMap.class && cls2 == Map.class) {
            return _hashMapSuperInterfaceChain(hierarchicType3);
        } else {
            if (rawClass == ArrayList.class && cls2 == List.class) {
                return _arrayListSuperInterfaceChain(hierarchicType3);
            }
            return _doFindSuperInterfaceChain(hierarchicType3, cls2);
        }
    }

    /* access modifiers changed from: protected */
    public HierarchicType _doFindSuperInterfaceChain(HierarchicType hierarchicType, Class<?> cls) {
        HierarchicType _findSuperInterfaceChain;
        HierarchicType hierarchicType2 = hierarchicType;
        Class<?> cls2 = cls;
        Class<?> rawClass = hierarchicType2.getRawClass();
        Type[] genericInterfaces = rawClass.getGenericInterfaces();
        if (genericInterfaces != null) {
            Type[] typeArr = genericInterfaces;
            int length = typeArr.length;
            for (int i = 0; i < length; i++) {
                HierarchicType _findSuperInterfaceChain2 = _findSuperInterfaceChain(typeArr[i], cls2);
                if (_findSuperInterfaceChain2 != null) {
                    _findSuperInterfaceChain2.setSubType(hierarchicType2);
                    hierarchicType2.setSuperType(_findSuperInterfaceChain2);
                    return hierarchicType2;
                }
            }
        }
        Type genericSuperclass = rawClass.getGenericSuperclass();
        if (genericSuperclass == null || (_findSuperInterfaceChain = _findSuperInterfaceChain(genericSuperclass, cls2)) == null) {
            return null;
        }
        _findSuperInterfaceChain.setSubType(hierarchicType2);
        hierarchicType2.setSuperType(_findSuperInterfaceChain);
        return hierarchicType2;
    }

    /* access modifiers changed from: protected */
    public synchronized HierarchicType _hashMapSuperInterfaceChain(HierarchicType hierarchicType) {
        HierarchicType hierarchicType2;
        HierarchicType hierarchicType3 = hierarchicType;
        synchronized (this) {
            if (this._cachedHashMapType == null) {
                HierarchicType deepCloneWithoutSubtype = hierarchicType3.deepCloneWithoutSubtype();
                HierarchicType _doFindSuperInterfaceChain = _doFindSuperInterfaceChain(deepCloneWithoutSubtype, Map.class);
                this._cachedHashMapType = deepCloneWithoutSubtype.getSuperType();
            }
            HierarchicType deepCloneWithoutSubtype2 = this._cachedHashMapType.deepCloneWithoutSubtype();
            hierarchicType3.setSuperType(deepCloneWithoutSubtype2);
            deepCloneWithoutSubtype2.setSubType(hierarchicType3);
            hierarchicType2 = hierarchicType3;
        }
        return hierarchicType2;
    }

    /* access modifiers changed from: protected */
    public synchronized HierarchicType _arrayListSuperInterfaceChain(HierarchicType hierarchicType) {
        HierarchicType hierarchicType2;
        HierarchicType hierarchicType3 = hierarchicType;
        synchronized (this) {
            if (this._cachedArrayListType == null) {
                HierarchicType deepCloneWithoutSubtype = hierarchicType3.deepCloneWithoutSubtype();
                HierarchicType _doFindSuperInterfaceChain = _doFindSuperInterfaceChain(deepCloneWithoutSubtype, List.class);
                this._cachedArrayListType = deepCloneWithoutSubtype.getSuperType();
            }
            HierarchicType deepCloneWithoutSubtype2 = this._cachedArrayListType.deepCloneWithoutSubtype();
            hierarchicType3.setSuperType(deepCloneWithoutSubtype2);
            deepCloneWithoutSubtype2.setSubType(hierarchicType3);
            hierarchicType2 = hierarchicType3;
        }
        return hierarchicType2;
    }
}

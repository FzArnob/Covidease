package com.shaded.fasterxml.jackson.databind.type;

import com.shaded.fasterxml.jackson.databind.JavaType;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

public class TypeBindings {
    private static final JavaType[] NO_TYPES = new JavaType[0];
    public static final JavaType UNBOUND;
    protected Map<String, JavaType> _bindings;
    protected final Class<?> _contextClass;
    protected final JavaType _contextType;
    private final TypeBindings _parentBindings;
    protected HashSet<String> _placeholders;
    protected final TypeFactory _typeFactory;

    static {
        JavaType javaType;
        new SimpleType(Object.class);
        UNBOUND = javaType;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public TypeBindings(TypeFactory typeFactory, Class<?> cls) {
        this(typeFactory, (TypeBindings) null, cls, (JavaType) null);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public TypeBindings(com.shaded.fasterxml.jackson.databind.type.TypeFactory r9, com.shaded.fasterxml.jackson.databind.JavaType r10) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r10
            r3 = r0
            r4 = r1
            r5 = 0
            r6 = r2
            java.lang.Class r6 = r6.getRawClass()
            r7 = r2
            r3.<init>(r4, r5, r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.type.TypeBindings.<init>(com.shaded.fasterxml.jackson.databind.type.TypeFactory, com.shaded.fasterxml.jackson.databind.JavaType):void");
    }

    public TypeBindings childInstance() {
        TypeBindings typeBindings;
        new TypeBindings(this._typeFactory, this, this._contextClass, this._contextType);
        return typeBindings;
    }

    private TypeBindings(TypeFactory typeFactory, TypeBindings typeBindings, Class<?> cls, JavaType javaType) {
        this._typeFactory = typeFactory;
        this._parentBindings = typeBindings;
        this._contextClass = cls;
        this._contextType = javaType;
    }

    public JavaType resolveType(Class<?> cls) {
        return this._typeFactory._constructType(cls, this);
    }

    public JavaType resolveType(Type type) {
        return this._typeFactory._constructType(type, this);
    }

    public int getBindingCount() {
        if (this._bindings == null) {
            _resolve();
        }
        return this._bindings.size();
    }

    public JavaType findType(String str) {
        String str2;
        Throwable th;
        StringBuilder sb;
        String str3 = str;
        if (this._bindings == null) {
            _resolve();
        }
        JavaType javaType = this._bindings.get(str3);
        if (javaType != null) {
            return javaType;
        }
        if (this._placeholders != null && this._placeholders.contains(str3)) {
            return UNBOUND;
        }
        if (this._parentBindings != null) {
            return this._parentBindings.findType(str3);
        }
        if (this._contextClass != null && this._contextClass.getEnclosingClass() != null && !Modifier.isStatic(this._contextClass.getModifiers())) {
            return UNBOUND;
        }
        if (this._contextClass != null) {
            str2 = this._contextClass.getName();
        } else if (this._contextType != null) {
            str2 = this._contextType.toString();
        } else {
            str2 = "UNKNOWN";
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalArgumentException(sb.append("Type variable '").append(str3).append("' can not be resolved (with context of class ").append(str2).append(")").toString());
        throw th2;
    }

    public void addBinding(String str, JavaType javaType) {
        Map<String, JavaType> map;
        String str2 = str;
        JavaType javaType2 = javaType;
        if (this._bindings == null || this._bindings.size() == 0) {
            new LinkedHashMap();
            this._bindings = map;
        }
        JavaType put = this._bindings.put(str2, javaType2);
    }

    public JavaType[] typesAsArray() {
        if (this._bindings == null) {
            _resolve();
        }
        if (this._bindings.size() == 0) {
            return NO_TYPES;
        }
        return (JavaType[]) this._bindings.values().toArray(new JavaType[this._bindings.size()]);
    }

    /* access modifiers changed from: protected */
    public void _resolve() {
        int containedTypeCount;
        _resolveBindings(this._contextClass);
        if (this._contextType != null && (containedTypeCount = this._contextType.containedTypeCount()) > 0) {
            for (int i = 0; i < containedTypeCount; i++) {
                addBinding(this._contextType.containedTypeName(i), this._contextType.containedType(i));
            }
        }
        if (this._bindings == null) {
            this._bindings = Collections.emptyMap();
        }
    }

    public void _addPlaceholder(String str) {
        HashSet<String> hashSet;
        String str2 = str;
        if (this._placeholders == null) {
            new HashSet<>();
            this._placeholders = hashSet;
        }
        boolean add = this._placeholders.add(str2);
    }

    /* access modifiers changed from: protected */
    public void _resolveBindings(Type type) {
        Class cls;
        Map<String, JavaType> map;
        Map<String, JavaType> map2;
        Throwable th;
        StringBuilder sb;
        Type type2 = type;
        if (type2 != null) {
            if (type2 instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type2;
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                if (actualTypeArguments != null && actualTypeArguments.length > 0) {
                    Class cls2 = (Class) parameterizedType.getRawType();
                    TypeVariable[] typeParameters = cls2.getTypeParameters();
                    if (typeParameters.length != actualTypeArguments.length) {
                        Throwable th2 = th;
                        new StringBuilder();
                        new IllegalArgumentException(sb.append("Strange parametrized type (in class ").append(cls2.getName()).append("): number of type arguments != number of type parameters (").append(actualTypeArguments.length).append(" vs ").append(typeParameters.length).append(")").toString());
                        throw th2;
                    }
                    int length = actualTypeArguments.length;
                    for (int i = 0; i < length; i++) {
                        String name = typeParameters[i].getName();
                        if (this._bindings == null) {
                            new LinkedHashMap();
                            this._bindings = map2;
                        } else if (this._bindings.containsKey(name)) {
                        }
                        _addPlaceholder(name);
                        JavaType put = this._bindings.put(name, this._typeFactory._constructType(actualTypeArguments[i], this));
                    }
                }
                cls = (Class) parameterizedType.getRawType();
            } else if (type2 instanceof Class) {
                cls = (Class) type2;
                Class<?> declaringClass = cls.getDeclaringClass();
                if (declaringClass != null && !declaringClass.isAssignableFrom(cls)) {
                    _resolveBindings(cls.getDeclaringClass());
                }
                TypeVariable[] typeParameters2 = cls.getTypeParameters();
                if (typeParameters2 != null && typeParameters2.length > 0) {
                    JavaType[] javaTypeArr = null;
                    if (this._contextType != null && cls.isAssignableFrom(this._contextType.getRawClass())) {
                        javaTypeArr = this._typeFactory.findTypeParameters(this._contextType, (Class<?>) cls);
                    }
                    for (int i2 = 0; i2 < typeParameters2.length; i2++) {
                        TypeVariable typeVariable = typeParameters2[i2];
                        String name2 = typeVariable.getName();
                        Type type3 = typeVariable.getBounds()[0];
                        if (type3 != null) {
                            if (this._bindings == null) {
                                new LinkedHashMap();
                                this._bindings = map;
                            } else if (this._bindings.containsKey(name2)) {
                            }
                            _addPlaceholder(name2);
                            if (javaTypeArr != null) {
                                JavaType put2 = this._bindings.put(name2, javaTypeArr[i2]);
                            } else {
                                JavaType put3 = this._bindings.put(name2, this._typeFactory._constructType(type3, this));
                            }
                        }
                    }
                }
            } else {
                return;
            }
            _resolveBindings(cls.getGenericSuperclass());
            Type[] genericInterfaces = cls.getGenericInterfaces();
            int length2 = genericInterfaces.length;
            for (int i3 = 0; i3 < length2; i3++) {
                _resolveBindings(genericInterfaces[i3]);
            }
        }
    }

    public String toString() {
        StringBuilder sb;
        if (this._bindings == null) {
            _resolve();
        }
        new StringBuilder("[TypeBindings for ");
        StringBuilder sb2 = sb;
        if (this._contextType != null) {
            StringBuilder append = sb2.append(this._contextType.toString());
        } else {
            StringBuilder append2 = sb2.append(this._contextClass.getName());
        }
        StringBuilder append3 = sb2.append(": ").append(this._bindings).append("]");
        return sb2.toString();
    }
}

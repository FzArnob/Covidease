package com.google.gson.internal;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;

/* renamed from: com.google.gson.internal.$Gson$Types  reason: invalid class name */
public final class C$Gson$Types {
    static final Type[] EMPTY_TYPE_ARRAY = new Type[0];

    private C$Gson$Types() {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException();
        throw th2;
    }

    public static ParameterizedType newParameterizedTypeWithOwner(Type ownerType, Type rawType, Type... typeArguments) {
        ParameterizedType parameterizedType;
        new ParameterizedTypeImpl(ownerType, rawType, typeArguments);
        return parameterizedType;
    }

    public static GenericArrayType arrayOf(Type componentType) {
        GenericArrayType genericArrayType;
        new GenericArrayTypeImpl(componentType);
        return genericArrayType;
    }

    public static WildcardType subtypeOf(Type type) {
        Type[] upperBounds;
        WildcardType wildcardType;
        Type bound = type;
        if (bound instanceof WildcardType) {
            upperBounds = ((WildcardType) bound).getUpperBounds();
        } else {
            upperBounds = new Type[]{bound};
        }
        new WildcardTypeImpl(upperBounds, EMPTY_TYPE_ARRAY);
        return wildcardType;
    }

    public static WildcardType supertypeOf(Type type) {
        Type[] lowerBounds;
        WildcardType wildcardType;
        Type bound = type;
        if (bound instanceof WildcardType) {
            lowerBounds = ((WildcardType) bound).getLowerBounds();
        } else {
            lowerBounds = new Type[]{bound};
        }
        WildcardType wildcardType2 = wildcardType;
        new WildcardTypeImpl(new Type[]{Object.class}, lowerBounds);
        return wildcardType2;
    }

    public static Type canonicalize(Type type) {
        Type type2;
        Type type3;
        Type type4;
        Class<?> type5;
        Class cls;
        Type type6 = type;
        if (type6 instanceof Class) {
            Class<?> c = (Class) type6;
            if (c.isArray()) {
                type5 = cls;
                new GenericArrayTypeImpl(canonicalize(c.getComponentType()));
            } else {
                type5 = c;
            }
            return type5;
        } else if (type6 instanceof ParameterizedType) {
            ParameterizedType p = (ParameterizedType) type6;
            new ParameterizedTypeImpl(p.getOwnerType(), p.getRawType(), p.getActualTypeArguments());
            return type4;
        } else if (type6 instanceof GenericArrayType) {
            new GenericArrayTypeImpl(((GenericArrayType) type6).getGenericComponentType());
            return type3;
        } else if (!(type6 instanceof WildcardType)) {
            return type6;
        } else {
            WildcardType w = (WildcardType) type6;
            new WildcardTypeImpl(w.getUpperBounds(), w.getLowerBounds());
            return type2;
        }
    }

    public static Class<?> getRawType(Type type) {
        Throwable th;
        StringBuilder sb;
        Type type2 = type;
        if (type2 instanceof Class) {
            return (Class) type2;
        }
        if (type2 instanceof ParameterizedType) {
            Type rawType = ((ParameterizedType) type2).getRawType();
            C$Gson$Preconditions.checkArgument(rawType instanceof Class);
            return (Class) rawType;
        } else if (type2 instanceof GenericArrayType) {
            return Array.newInstance(getRawType(((GenericArrayType) type2).getGenericComponentType()), 0).getClass();
        } else {
            if (type2 instanceof TypeVariable) {
                return Object.class;
            }
            if (type2 instanceof WildcardType) {
                return getRawType(((WildcardType) type2).getUpperBounds()[0]);
            }
            String className = type2 == null ? "null" : type2.getClass().getName();
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Expected a Class, ParameterizedType, or GenericArrayType, but <").append(type2).append("> is of type ").append(className).toString());
            throw th2;
        }
    }

    static boolean equal(Object obj, Object obj2) {
        Object a = obj;
        Object b = obj2;
        return a == b || (a != null && a.equals(b));
    }

    public static boolean equals(Type type, Type type2) {
        Type a = type;
        Type b = type2;
        if (a == b) {
            return true;
        }
        if (a instanceof Class) {
            return a.equals(b);
        }
        if (a instanceof ParameterizedType) {
            if (!(b instanceof ParameterizedType)) {
                return false;
            }
            ParameterizedType pa = (ParameterizedType) a;
            ParameterizedType pb = (ParameterizedType) b;
            return equal(pa.getOwnerType(), pb.getOwnerType()) && pa.getRawType().equals(pb.getRawType()) && Arrays.equals(pa.getActualTypeArguments(), pb.getActualTypeArguments());
        } else if (a instanceof GenericArrayType) {
            if (!(b instanceof GenericArrayType)) {
                return false;
            }
            return equals(((GenericArrayType) a).getGenericComponentType(), ((GenericArrayType) b).getGenericComponentType());
        } else if (a instanceof WildcardType) {
            if (!(b instanceof WildcardType)) {
                return false;
            }
            WildcardType wa = (WildcardType) a;
            WildcardType wb = (WildcardType) b;
            return Arrays.equals(wa.getUpperBounds(), wb.getUpperBounds()) && Arrays.equals(wa.getLowerBounds(), wb.getLowerBounds());
        } else if (!(a instanceof TypeVariable)) {
            return false;
        } else {
            if (!(b instanceof TypeVariable)) {
                return false;
            }
            TypeVariable<?> va = (TypeVariable) a;
            TypeVariable<?> vb = (TypeVariable) b;
            return va.getGenericDeclaration() == vb.getGenericDeclaration() && va.getName().equals(vb.getName());
        }
    }

    static int hashCodeOrZero(Object obj) {
        Object o = obj;
        return o != null ? o.hashCode() : 0;
    }

    public static String typeToString(Type type) {
        Type type2 = type;
        return type2 instanceof Class ? ((Class) type2).getName() : type2.toString();
    }

    static Type getGenericSupertype(Type type, Class<?> cls, Class<?> cls2) {
        Type context = type;
        Type rawType = cls;
        Type toResolve = cls2;
        if (toResolve == rawType) {
            return context;
        }
        if (toResolve.isInterface()) {
            Class<?>[] interfaces = rawType.getInterfaces();
            int length = interfaces.length;
            for (int i = 0; i < length; i++) {
                if (interfaces[i] == toResolve) {
                    return rawType.getGenericInterfaces()[i];
                }
                if (toResolve.isAssignableFrom(interfaces[i])) {
                    return getGenericSupertype(rawType.getGenericInterfaces()[i], interfaces[i], toResolve);
                }
            }
        }
        if (!rawType.isInterface()) {
            while (rawType != Object.class) {
                Class<? super Object> superclass = rawType.getSuperclass();
                if (superclass == toResolve) {
                    return rawType.getGenericSuperclass();
                }
                if (toResolve.isAssignableFrom(superclass)) {
                    return getGenericSupertype(rawType.getGenericSuperclass(), superclass, toResolve);
                }
                rawType = superclass;
            }
        }
        return toResolve;
    }

    static Type getSupertype(Type type, Class<?> cls, Class<?> cls2) {
        Type context = type;
        Class<?> contextRawType = cls;
        Class<?> supertype = cls2;
        if (context instanceof WildcardType) {
            context = ((WildcardType) context).getUpperBounds()[0];
        }
        C$Gson$Preconditions.checkArgument(supertype.isAssignableFrom(contextRawType));
        return resolve(context, contextRawType, getGenericSupertype(context, contextRawType, supertype));
    }

    public static Type getArrayComponentType(Type type) {
        Type array;
        Type array2 = type;
        if (array2 instanceof GenericArrayType) {
            array = ((GenericArrayType) array2).getGenericComponentType();
        } else {
            array = ((Class) array2).getComponentType();
        }
        return array;
    }

    public static Type getCollectionElementType(Type context, Class<?> contextRawType) {
        Type collectionType = getSupertype(context, contextRawType, Collection.class);
        if (collectionType instanceof WildcardType) {
            collectionType = ((WildcardType) collectionType).getUpperBounds()[0];
        }
        if (collectionType instanceof ParameterizedType) {
            return ((ParameterizedType) collectionType).getActualTypeArguments()[0];
        }
        return Object.class;
    }

    public static Type[] getMapKeyAndValueTypes(Type type, Class<?> cls) {
        Type context = type;
        Class<?> contextRawType = cls;
        if (context == Properties.class) {
            Type[] typeArr = new Type[2];
            typeArr[0] = String.class;
            Type[] typeArr2 = typeArr;
            typeArr2[1] = String.class;
            return typeArr2;
        }
        Type mapType = getSupertype(context, contextRawType, Map.class);
        if (mapType instanceof ParameterizedType) {
            return ((ParameterizedType) mapType).getActualTypeArguments();
        }
        Type[] typeArr3 = new Type[2];
        typeArr3[0] = Object.class;
        Type[] typeArr4 = typeArr3;
        typeArr4[1] = Object.class;
        return typeArr4;
    }

    public static Type resolve(Type context, Class<?> contextRawType, Type toResolve) {
        Collection collection;
        new HashSet();
        return resolve(context, contextRawType, toResolve, collection);
    }

    private static Type resolve(Type type, Class<?> cls, Type type2, Collection<TypeVariable> collection) {
        Type upperBound;
        Type context;
        Type context2;
        Type context3 = type;
        Class<?> contextRawType = cls;
        Type toResolve = type2;
        Collection<TypeVariable> visitedTypeVariables = collection;
        while (toResolve instanceof TypeVariable) {
            TypeVariable<?> typeVariable = (TypeVariable) toResolve;
            if (visitedTypeVariables.contains(typeVariable)) {
                return toResolve;
            }
            boolean add = visitedTypeVariables.add(typeVariable);
            toResolve = resolveTypeVariable(context3, contextRawType, typeVariable);
            if (toResolve == typeVariable) {
                return toResolve;
            }
        }
        if ((toResolve instanceof Class) && ((Class) toResolve).isArray()) {
            Class<?> original = (Class) toResolve;
            Type componentType = original.getComponentType();
            Type newComponentType = resolve(context3, contextRawType, componentType, visitedTypeVariables);
            if (componentType == newComponentType) {
                context2 = original;
            } else {
                context2 = arrayOf(newComponentType);
            }
            return context2;
        } else if (toResolve instanceof GenericArrayType) {
            Type original2 = (GenericArrayType) toResolve;
            Type componentType2 = original2.getGenericComponentType();
            Type newComponentType2 = resolve(context3, contextRawType, componentType2, visitedTypeVariables);
            if (componentType2 == newComponentType2) {
                context = original2;
            } else {
                context = arrayOf(newComponentType2);
            }
            return context;
        } else if (toResolve instanceof ParameterizedType) {
            Type original3 = (ParameterizedType) toResolve;
            Type ownerType = original3.getOwnerType();
            Type newOwnerType = resolve(context3, contextRawType, ownerType, visitedTypeVariables);
            boolean changed = newOwnerType != ownerType;
            Type[] args = original3.getActualTypeArguments();
            int length = args.length;
            for (int t = 0; t < length; t++) {
                Type resolvedTypeArgument = resolve(context3, contextRawType, args[t], visitedTypeVariables);
                if (resolvedTypeArgument != args[t]) {
                    if (!changed) {
                        args = (Type[]) args.clone();
                        changed = true;
                    }
                    args[t] = resolvedTypeArgument;
                }
            }
            return changed ? newParameterizedTypeWithOwner(newOwnerType, original3.getRawType(), args) : original3;
        } else if (!(toResolve instanceof WildcardType)) {
            return toResolve;
        } else {
            Type original4 = (WildcardType) toResolve;
            Type[] originalLowerBound = original4.getLowerBounds();
            Type[] originalUpperBound = original4.getUpperBounds();
            if (originalLowerBound.length == 1) {
                Type lowerBound = resolve(context3, contextRawType, originalLowerBound[0], visitedTypeVariables);
                if (lowerBound != originalLowerBound[0]) {
                    return supertypeOf(lowerBound);
                }
            } else if (originalUpperBound.length == 1 && (upperBound = resolve(context3, contextRawType, originalUpperBound[0], visitedTypeVariables)) != originalUpperBound[0]) {
                return subtypeOf(upperBound);
            }
            return original4;
        }
    }

    static Type resolveTypeVariable(Type type, Class<?> cls, TypeVariable<?> typeVariable) {
        Type context = type;
        Class<?> contextRawType = cls;
        Type unknown = typeVariable;
        Class<?> declaredByRaw = declaringClassOf(unknown);
        if (declaredByRaw == null) {
            return unknown;
        }
        Type declaredBy = getGenericSupertype(context, contextRawType, declaredByRaw);
        if (!(declaredBy instanceof ParameterizedType)) {
            return unknown;
        }
        return ((ParameterizedType) declaredBy).getActualTypeArguments()[indexOf(declaredByRaw.getTypeParameters(), unknown)];
    }

    private static int indexOf(Object[] objArr, Object obj) {
        Throwable th;
        Object[] array = objArr;
        Object toFind = obj;
        int length = array.length;
        for (int i = 0; i < length; i++) {
            if (toFind.equals(array[i])) {
                return i;
            }
        }
        Throwable th2 = th;
        new NoSuchElementException();
        throw th2;
    }

    private static Class<?> declaringClassOf(TypeVariable<?> typeVariable) {
        Object genericDeclaration = typeVariable.getGenericDeclaration();
        return genericDeclaration instanceof Class ? (Class) genericDeclaration : null;
    }

    static void checkNotPrimitive(Type type) {
        Type type2 = type;
        C$Gson$Preconditions.checkArgument(!(type2 instanceof Class) || !((Class) type2).isPrimitive());
    }

    /* renamed from: com.google.gson.internal.$Gson$Types$ParameterizedTypeImpl */
    /* compiled from: $Gson$Types */
    private static final class ParameterizedTypeImpl implements ParameterizedType, Serializable {
        private static final long serialVersionUID = 0;
        private final Type ownerType;
        private final Type rawType;
        private final Type[] typeArguments;

        public ParameterizedTypeImpl(Type type, Type type2, Type... typeArr) {
            Type ownerType2 = type;
            Type rawType2 = type2;
            Type[] typeArguments2 = typeArr;
            if (rawType2 instanceof Class) {
                Class<?> rawTypeAsClass = (Class) rawType2;
                C$Gson$Preconditions.checkArgument(ownerType2 != null || (Modifier.isStatic(rawTypeAsClass.getModifiers()) || rawTypeAsClass.getEnclosingClass() == null));
            }
            this.ownerType = ownerType2 == null ? null : C$Gson$Types.canonicalize(ownerType2);
            this.rawType = C$Gson$Types.canonicalize(rawType2);
            this.typeArguments = (Type[]) typeArguments2.clone();
            int length = this.typeArguments.length;
            for (int t = 0; t < length; t++) {
                Object checkNotNull = C$Gson$Preconditions.checkNotNull(this.typeArguments[t]);
                C$Gson$Types.checkNotPrimitive(this.typeArguments[t]);
                this.typeArguments[t] = C$Gson$Types.canonicalize(this.typeArguments[t]);
            }
        }

        public Type[] getActualTypeArguments() {
            return (Type[]) this.typeArguments.clone();
        }

        public Type getRawType() {
            return this.rawType;
        }

        public Type getOwnerType() {
            return this.ownerType;
        }

        public boolean equals(Object obj) {
            Object other = obj;
            return (other instanceof ParameterizedType) && C$Gson$Types.equals(this, (ParameterizedType) other);
        }

        public int hashCode() {
            return (Arrays.hashCode(this.typeArguments) ^ this.rawType.hashCode()) ^ C$Gson$Types.hashCodeOrZero(this.ownerType);
        }

        public String toString() {
            StringBuilder sb;
            int length = this.typeArguments.length;
            if (length == 0) {
                return C$Gson$Types.typeToString(this.rawType);
            }
            new StringBuilder(30 * (length + 1));
            StringBuilder stringBuilder = sb;
            StringBuilder append = stringBuilder.append(C$Gson$Types.typeToString(this.rawType)).append("<").append(C$Gson$Types.typeToString(this.typeArguments[0]));
            for (int i = 1; i < length; i++) {
                StringBuilder append2 = stringBuilder.append(", ").append(C$Gson$Types.typeToString(this.typeArguments[i]));
            }
            return stringBuilder.append(">").toString();
        }
    }

    /* renamed from: com.google.gson.internal.$Gson$Types$GenericArrayTypeImpl */
    /* compiled from: $Gson$Types */
    private static final class GenericArrayTypeImpl implements GenericArrayType, Serializable {
        private static final long serialVersionUID = 0;
        private final Type componentType;

        public GenericArrayTypeImpl(Type componentType2) {
            this.componentType = C$Gson$Types.canonicalize(componentType2);
        }

        public Type getGenericComponentType() {
            return this.componentType;
        }

        public boolean equals(Object obj) {
            Object o = obj;
            return (o instanceof GenericArrayType) && C$Gson$Types.equals(this, (GenericArrayType) o);
        }

        public int hashCode() {
            return this.componentType.hashCode();
        }

        public String toString() {
            StringBuilder sb;
            new StringBuilder();
            return sb.append(C$Gson$Types.typeToString(this.componentType)).append("[]").toString();
        }
    }

    /* renamed from: com.google.gson.internal.$Gson$Types$WildcardTypeImpl */
    /* compiled from: $Gson$Types */
    private static final class WildcardTypeImpl implements WildcardType, Serializable {
        private static final long serialVersionUID = 0;
        private final Type lowerBound;
        private final Type upperBound;

        public WildcardTypeImpl(Type[] typeArr, Type[] typeArr2) {
            Type[] upperBounds = typeArr;
            Type[] lowerBounds = typeArr2;
            C$Gson$Preconditions.checkArgument(lowerBounds.length <= 1);
            C$Gson$Preconditions.checkArgument(upperBounds.length == 1);
            if (lowerBounds.length == 1) {
                Object checkNotNull = C$Gson$Preconditions.checkNotNull(lowerBounds[0]);
                C$Gson$Types.checkNotPrimitive(lowerBounds[0]);
                C$Gson$Preconditions.checkArgument(upperBounds[0] == Object.class);
                this.lowerBound = C$Gson$Types.canonicalize(lowerBounds[0]);
                this.upperBound = Object.class;
                return;
            }
            Object checkNotNull2 = C$Gson$Preconditions.checkNotNull(upperBounds[0]);
            C$Gson$Types.checkNotPrimitive(upperBounds[0]);
            this.lowerBound = null;
            this.upperBound = C$Gson$Types.canonicalize(upperBounds[0]);
        }

        public Type[] getUpperBounds() {
            return new Type[]{this.upperBound};
        }

        public Type[] getLowerBounds() {
            Type[] typeArr;
            if (this.lowerBound != null) {
                Type[] typeArr2 = new Type[1];
                typeArr = typeArr2;
                typeArr2[0] = this.lowerBound;
            } else {
                typeArr = C$Gson$Types.EMPTY_TYPE_ARRAY;
            }
            return typeArr;
        }

        public boolean equals(Object obj) {
            Object other = obj;
            return (other instanceof WildcardType) && C$Gson$Types.equals(this, (WildcardType) other);
        }

        public int hashCode() {
            return (this.lowerBound != null ? 31 + this.lowerBound.hashCode() : 1) ^ (31 + this.upperBound.hashCode());
        }

        public String toString() {
            StringBuilder sb;
            StringBuilder sb2;
            if (this.lowerBound != null) {
                new StringBuilder();
                return sb2.append("? super ").append(C$Gson$Types.typeToString(this.lowerBound)).toString();
            } else if (this.upperBound == Object.class) {
                return "?";
            } else {
                new StringBuilder();
                return sb.append("? extends ").append(C$Gson$Types.typeToString(this.upperBound)).toString();
            }
        }
    }
}

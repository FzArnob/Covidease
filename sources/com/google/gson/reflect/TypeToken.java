package com.google.gson.reflect;

import com.google.gson.internal.C$Gson$Preconditions;
import com.google.gson.internal.C$Gson$Types;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;

public class TypeToken<T> {
    final int hashCode;
    final Class<? super T> rawType;
    final Type type;

    protected TypeToken() {
        this.type = getSuperclassTypeParameter(getClass());
        this.rawType = C$Gson$Types.getRawType(this.type);
        this.hashCode = this.type.hashCode();
    }

    TypeToken(Type type2) {
        this.type = C$Gson$Types.canonicalize((Type) C$Gson$Preconditions.checkNotNull(type2));
        this.rawType = C$Gson$Types.getRawType(this.type);
        this.hashCode = this.type.hashCode();
    }

    static Type getSuperclassTypeParameter(Class<?> subclass) {
        Throwable th;
        Type superclass = subclass.getGenericSuperclass();
        if (!(superclass instanceof Class)) {
            return C$Gson$Types.canonicalize(((ParameterizedType) superclass).getActualTypeArguments()[0]);
        }
        Throwable th2 = th;
        new RuntimeException("Missing type parameter.");
        throw th2;
    }

    public final Class<? super T> getRawType() {
        return this.rawType;
    }

    public final Type getType() {
        return this.type;
    }

    @Deprecated
    public boolean isAssignableFrom(Class<?> cls) {
        return isAssignableFrom((Type) cls);
    }

    @Deprecated
    public boolean isAssignableFrom(Type type2) {
        Map map;
        Type from = type2;
        if (from == null) {
            return false;
        }
        if (this.type.equals(from)) {
            return true;
        }
        if (this.type instanceof Class) {
            return this.rawType.isAssignableFrom(C$Gson$Types.getRawType(from));
        }
        if (this.type instanceof ParameterizedType) {
            new HashMap();
            return isAssignableFrom(from, (ParameterizedType) this.type, map);
        } else if (this.type instanceof GenericArrayType) {
            return this.rawType.isAssignableFrom(C$Gson$Types.getRawType(from)) && isAssignableFrom(from, (GenericArrayType) this.type);
        } else {
            Type type3 = this.type;
            Class[] clsArr = new Class[3];
            clsArr[0] = Class.class;
            Class[] clsArr2 = clsArr;
            clsArr2[1] = ParameterizedType.class;
            Class[] clsArr3 = clsArr2;
            clsArr3[2] = GenericArrayType.class;
            throw buildUnexpectedTypeError(type3, clsArr3);
        }
    }

    @Deprecated
    public boolean isAssignableFrom(TypeToken<?> token) {
        return isAssignableFrom(token.getType());
    }

    private static boolean isAssignableFrom(Type type2, GenericArrayType to) {
        Type classType;
        Map map;
        Type from = type2;
        Type toGenericComponentType = to.getGenericComponentType();
        if (!(toGenericComponentType instanceof ParameterizedType)) {
            return true;
        }
        Type t = from;
        if (from instanceof GenericArrayType) {
            t = ((GenericArrayType) from).getGenericComponentType();
        } else if (from instanceof Class) {
            Type type3 = (Class) from;
            while (true) {
                classType = type3;
                if (!classType.isArray()) {
                    break;
                }
                type3 = classType.getComponentType();
            }
            t = classType;
        }
        new HashMap();
        return isAssignableFrom(t, (ParameterizedType) toGenericComponentType, map);
    }

    private static boolean isAssignableFrom(Type type2, ParameterizedType parameterizedType, Map<String, Type> map) {
        Map map2;
        Map map3;
        Type from = type2;
        ParameterizedType to = parameterizedType;
        Map<String, Type> typeVarMap = map;
        if (from == null) {
            return false;
        }
        if (to.equals(from)) {
            return true;
        }
        Class<?> clazz = C$Gson$Types.getRawType(from);
        ParameterizedType ptype = null;
        if (from instanceof ParameterizedType) {
            ptype = (ParameterizedType) from;
        }
        if (ptype != null) {
            Type[] tArgs = ptype.getActualTypeArguments();
            TypeVariable[] typeParameters = clazz.getTypeParameters();
            for (int i = 0; i < tArgs.length; i++) {
                Type arg = tArgs[i];
                TypeVariable typeVariable = typeParameters[i];
                while (arg instanceof TypeVariable) {
                    arg = typeVarMap.get(((TypeVariable) arg).getName());
                }
                Type put = typeVarMap.put(typeVariable.getName(), arg);
            }
            if (typeEquals(ptype, to, typeVarMap)) {
                return true;
            }
        }
        Type[] genericInterfaces = clazz.getGenericInterfaces();
        int length = genericInterfaces.length;
        for (int i2 = 0; i2 < length; i2++) {
            new HashMap(typeVarMap);
            if (isAssignableFrom(genericInterfaces[i2], to, map3)) {
                return true;
            }
        }
        new HashMap(typeVarMap);
        return isAssignableFrom(clazz.getGenericSuperclass(), to, map2);
    }

    private static boolean typeEquals(ParameterizedType parameterizedType, ParameterizedType parameterizedType2, Map<String, Type> map) {
        ParameterizedType from = parameterizedType;
        ParameterizedType to = parameterizedType2;
        Map<String, Type> typeVarMap = map;
        if (!from.getRawType().equals(to.getRawType())) {
            return false;
        }
        Type[] fromArgs = from.getActualTypeArguments();
        Type[] toArgs = to.getActualTypeArguments();
        for (int i = 0; i < fromArgs.length; i++) {
            if (!matches(fromArgs[i], toArgs[i], typeVarMap)) {
                return false;
            }
        }
        return true;
    }

    private static AssertionError buildUnexpectedTypeError(Type type2, Class<?>... expected) {
        StringBuilder sb;
        AssertionError assertionError;
        Type token = type2;
        new StringBuilder("Unexpected type. Expected one of: ");
        StringBuilder exceptionMessage = sb;
        Class<?>[] clsArr = expected;
        int length = clsArr.length;
        for (int i = 0; i < length; i++) {
            StringBuilder append = exceptionMessage.append(clsArr[i].getName()).append(", ");
        }
        StringBuilder append2 = exceptionMessage.append("but got: ").append(token.getClass().getName()).append(", for type token: ").append(token.toString()).append('.');
        new AssertionError(exceptionMessage.toString());
        return assertionError;
    }

    private static boolean matches(Type type2, Type type3, Map<String, Type> typeMap) {
        Type from = type2;
        Type to = type3;
        return to.equals(from) || ((from instanceof TypeVariable) && to.equals(typeMap.get(((TypeVariable) from).getName())));
    }

    public final int hashCode() {
        return this.hashCode;
    }

    public final boolean equals(Object obj) {
        Object o = obj;
        return (o instanceof TypeToken) && C$Gson$Types.equals(this.type, ((TypeToken) o).type);
    }

    public final String toString() {
        return C$Gson$Types.typeToString(this.type);
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.gson.reflect.TypeToken<?> get(java.lang.reflect.Type r5) {
        /*
            r0 = r5
            com.google.gson.reflect.TypeToken r1 = new com.google.gson.reflect.TypeToken
            r4 = r1
            r1 = r4
            r2 = r4
            r3 = r0
            r2.<init>(r3)
            r0 = r1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.reflect.TypeToken.get(java.lang.reflect.Type):com.google.gson.reflect.TypeToken");
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T> com.google.gson.reflect.TypeToken<T> get(java.lang.Class<T> r5) {
        /*
            r0 = r5
            com.google.gson.reflect.TypeToken r1 = new com.google.gson.reflect.TypeToken
            r4 = r1
            r1 = r4
            r2 = r4
            r3 = r0
            r2.<init>(r3)
            r0 = r1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.reflect.TypeToken.get(java.lang.Class):com.google.gson.reflect.TypeToken");
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.gson.reflect.TypeToken<?> getParameterized(java.lang.reflect.Type r8, java.lang.reflect.Type... r9) {
        /*
            r0 = r8
            r1 = r9
            com.google.gson.reflect.TypeToken r2 = new com.google.gson.reflect.TypeToken
            r7 = r2
            r2 = r7
            r3 = r7
            r4 = 0
            r5 = r0
            r6 = r1
            java.lang.reflect.ParameterizedType r4 = com.google.gson.internal.C$Gson$Types.newParameterizedTypeWithOwner(r4, r5, r6)
            r3.<init>(r4)
            r0 = r2
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.reflect.TypeToken.getParameterized(java.lang.reflect.Type, java.lang.reflect.Type[]):com.google.gson.reflect.TypeToken");
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.gson.reflect.TypeToken<?> getArray(java.lang.reflect.Type r5) {
        /*
            r0 = r5
            com.google.gson.reflect.TypeToken r1 = new com.google.gson.reflect.TypeToken
            r4 = r1
            r1 = r4
            r2 = r4
            r3 = r0
            java.lang.reflect.GenericArrayType r3 = com.google.gson.internal.C$Gson$Types.arrayOf(r3)
            r2.<init>(r3)
            r0 = r1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.reflect.TypeToken.getArray(java.lang.reflect.Type):com.google.gson.reflect.TypeToken");
    }
}

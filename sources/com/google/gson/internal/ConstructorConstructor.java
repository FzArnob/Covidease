package com.google.gson.internal;

import com.google.gson.InstanceCreator;
import com.google.gson.JsonIOException;
import com.google.gson.internal.reflect.ReflectionAccessor;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;

public final class ConstructorConstructor {
    private final ReflectionAccessor accessor = ReflectionAccessor.getInstance();
    private final Map<Type, InstanceCreator<?>> instanceCreators;

    public ConstructorConstructor(Map<Type, InstanceCreator<?>> instanceCreators2) {
        this.instanceCreators = instanceCreators2;
    }

    public <T> ObjectConstructor<T> get(TypeToken<T> typeToken) {
        ObjectConstructor<T> objectConstructor;
        ObjectConstructor<T> objectConstructor2;
        TypeToken<T> typeToken2 = typeToken;
        Type type = typeToken2.getType();
        Class<? super T> rawType = typeToken2.getRawType();
        InstanceCreator<T> typeCreator = this.instanceCreators.get(type);
        if (typeCreator != null) {
            final InstanceCreator<T> instanceCreator = typeCreator;
            final Type type2 = type;
            new ObjectConstructor<T>(this) {
                final /* synthetic */ ConstructorConstructor this$0;

                {
                    this.this$0 = this$0;
                }

                public T construct() {
                    return instanceCreator.createInstance(type2);
                }
            };
            return objectConstructor2;
        }
        InstanceCreator<T> rawTypeCreator = this.instanceCreators.get(rawType);
        if (rawTypeCreator != null) {
            final InstanceCreator<T> instanceCreator2 = rawTypeCreator;
            final Type type3 = type;
            new ObjectConstructor<T>(this) {
                final /* synthetic */ ConstructorConstructor this$0;

                {
                    this.this$0 = this$0;
                }

                public T construct() {
                    return instanceCreator2.createInstance(type3);
                }
            };
            return objectConstructor;
        }
        ObjectConstructor<T> defaultConstructor = newDefaultConstructor(rawType);
        if (defaultConstructor != null) {
            return defaultConstructor;
        }
        ObjectConstructor<T> defaultImplementation = newDefaultImplementationConstructor(type, rawType);
        if (defaultImplementation != null) {
            return defaultImplementation;
        }
        return newUnsafeAllocator(type, rawType);
    }

    private <T> ObjectConstructor<T> newDefaultConstructor(Class<? super T> rawType) {
        ObjectConstructor<T> objectConstructor;
        try {
            Constructor<? super T> constructor = rawType.getDeclaredConstructor(new Class[0]);
            if (!constructor.isAccessible()) {
                this.accessor.makeAccessible(constructor);
            }
            ObjectConstructor<T> objectConstructor2 = objectConstructor;
            final Constructor<? super T> constructor2 = constructor;
            new ObjectConstructor<T>(this) {
                final /* synthetic */ ConstructorConstructor this$0;

                {
                    this.this$0 = this$0;
                }

                public T construct() {
                    Throwable th;
                    Throwable th2;
                    StringBuilder sb;
                    Throwable th3;
                    StringBuilder sb2;
                    try {
                        return constructor2.newInstance((Object[]) null);
                    } catch (InstantiationException e) {
                        InstantiationException e2 = e;
                        Throwable th4 = th3;
                        new StringBuilder();
                        new RuntimeException(sb2.append("Failed to invoke ").append(constructor2).append(" with no args").toString(), e2);
                        throw th4;
                    } catch (InvocationTargetException e3) {
                        InvocationTargetException e4 = e3;
                        Throwable th5 = th2;
                        new StringBuilder();
                        new RuntimeException(sb.append("Failed to invoke ").append(constructor2).append(" with no args").toString(), e4.getTargetException());
                        throw th5;
                    } catch (IllegalAccessException e5) {
                        IllegalAccessException e6 = e5;
                        Throwable th6 = th;
                        new AssertionError(e6);
                        throw th6;
                    }
                }
            };
            return objectConstructor2;
        } catch (NoSuchMethodException e) {
            NoSuchMethodException noSuchMethodException = e;
            return null;
        }
    }

    private <T> ObjectConstructor<T> newDefaultImplementationConstructor(Type type, Class<? super T> cls) {
        ObjectConstructor<T> objectConstructor;
        ObjectConstructor<T> objectConstructor2;
        ObjectConstructor<T> objectConstructor3;
        ObjectConstructor<T> objectConstructor4;
        ObjectConstructor<T> objectConstructor5;
        ObjectConstructor<T> objectConstructor6;
        ObjectConstructor<T> objectConstructor7;
        ObjectConstructor<T> objectConstructor8;
        ObjectConstructor<T> objectConstructor9;
        ObjectConstructor<T> objectConstructor10;
        Type type2 = type;
        Class<? super T> rawType = cls;
        if (Collection.class.isAssignableFrom(rawType)) {
            if (SortedSet.class.isAssignableFrom(rawType)) {
                new ObjectConstructor<T>(this) {
                    final /* synthetic */ ConstructorConstructor this$0;

                    {
                        this.this$0 = this$0;
                    }

                    /* JADX WARNING: Multi-variable type inference failed */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public T construct() {
                        /*
                            r4 = this;
                            r0 = r4
                            java.util.TreeSet r1 = new java.util.TreeSet
                            r3 = r1
                            r1 = r3
                            r2 = r3
                            r2.<init>()
                            r0 = r1
                            return r0
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.ConstructorConstructor.C15754.construct():java.lang.Object");
                    }
                };
                return objectConstructor10;
            } else if (EnumSet.class.isAssignableFrom(rawType)) {
                final Type type3 = type2;
                new ObjectConstructor<T>(this) {
                    final /* synthetic */ ConstructorConstructor this$0;

                    {
                        this.this$0 = this$0;
                    }

                    public T construct() {
                        Throwable th;
                        StringBuilder sb;
                        Throwable th2;
                        StringBuilder sb2;
                        if (type3 instanceof ParameterizedType) {
                            Type elementType = ((ParameterizedType) type3).getActualTypeArguments()[0];
                            if (elementType instanceof Class) {
                                return EnumSet.noneOf((Class) elementType);
                            }
                            Throwable th3 = th2;
                            new StringBuilder();
                            new JsonIOException(sb2.append("Invalid EnumSet type: ").append(type3.toString()).toString());
                            throw th3;
                        }
                        Throwable th4 = th;
                        new StringBuilder();
                        new JsonIOException(sb.append("Invalid EnumSet type: ").append(type3.toString()).toString());
                        throw th4;
                    }
                };
                return objectConstructor9;
            } else if (Set.class.isAssignableFrom(rawType)) {
                new ObjectConstructor<T>(this) {
                    final /* synthetic */ ConstructorConstructor this$0;

                    {
                        this.this$0 = this$0;
                    }

                    /* JADX WARNING: Multi-variable type inference failed */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public T construct() {
                        /*
                            r4 = this;
                            r0 = r4
                            java.util.LinkedHashSet r1 = new java.util.LinkedHashSet
                            r3 = r1
                            r1 = r3
                            r2 = r3
                            r2.<init>()
                            r0 = r1
                            return r0
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.ConstructorConstructor.C15776.construct():java.lang.Object");
                    }
                };
                return objectConstructor8;
            } else if (Queue.class.isAssignableFrom(rawType)) {
                new ObjectConstructor<T>(this) {
                    final /* synthetic */ ConstructorConstructor this$0;

                    {
                        this.this$0 = this$0;
                    }

                    /* JADX WARNING: Multi-variable type inference failed */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public T construct() {
                        /*
                            r4 = this;
                            r0 = r4
                            java.util.ArrayDeque r1 = new java.util.ArrayDeque
                            r3 = r1
                            r1 = r3
                            r2 = r3
                            r2.<init>()
                            r0 = r1
                            return r0
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.ConstructorConstructor.C15787.construct():java.lang.Object");
                    }
                };
                return objectConstructor7;
            } else {
                new ObjectConstructor<T>(this) {
                    final /* synthetic */ ConstructorConstructor this$0;

                    {
                        this.this$0 = this$0;
                    }

                    /* JADX WARNING: Multi-variable type inference failed */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public T construct() {
                        /*
                            r4 = this;
                            r0 = r4
                            java.util.ArrayList r1 = new java.util.ArrayList
                            r3 = r1
                            r1 = r3
                            r2 = r3
                            r2.<init>()
                            r0 = r1
                            return r0
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.ConstructorConstructor.C15798.construct():java.lang.Object");
                    }
                };
                return objectConstructor6;
            }
        } else if (!Map.class.isAssignableFrom(rawType)) {
            return null;
        } else {
            if (ConcurrentNavigableMap.class.isAssignableFrom(rawType)) {
                new ObjectConstructor<T>(this) {
                    final /* synthetic */ ConstructorConstructor this$0;

                    {
                        this.this$0 = this$0;
                    }

                    /* JADX WARNING: Multi-variable type inference failed */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public T construct() {
                        /*
                            r4 = this;
                            r0 = r4
                            java.util.concurrent.ConcurrentSkipListMap r1 = new java.util.concurrent.ConcurrentSkipListMap
                            r3 = r1
                            r1 = r3
                            r2 = r3
                            r2.<init>()
                            r0 = r1
                            return r0
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.ConstructorConstructor.C15809.construct():java.lang.Object");
                    }
                };
                return objectConstructor5;
            } else if (ConcurrentMap.class.isAssignableFrom(rawType)) {
                new ObjectConstructor<T>(this) {
                    final /* synthetic */ ConstructorConstructor this$0;

                    {
                        this.this$0 = this$0;
                    }

                    /* JADX WARNING: Multi-variable type inference failed */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public T construct() {
                        /*
                            r4 = this;
                            r0 = r4
                            java.util.concurrent.ConcurrentHashMap r1 = new java.util.concurrent.ConcurrentHashMap
                            r3 = r1
                            r1 = r3
                            r2 = r3
                            r2.<init>()
                            r0 = r1
                            return r0
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.ConstructorConstructor.C156810.construct():java.lang.Object");
                    }
                };
                return objectConstructor4;
            } else if (SortedMap.class.isAssignableFrom(rawType)) {
                new ObjectConstructor<T>(this) {
                    final /* synthetic */ ConstructorConstructor this$0;

                    {
                        this.this$0 = this$0;
                    }

                    /* JADX WARNING: Multi-variable type inference failed */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public T construct() {
                        /*
                            r4 = this;
                            r0 = r4
                            java.util.TreeMap r1 = new java.util.TreeMap
                            r3 = r1
                            r1 = r3
                            r2 = r3
                            r2.<init>()
                            r0 = r1
                            return r0
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.ConstructorConstructor.C156911.construct():java.lang.Object");
                    }
                };
                return objectConstructor3;
            } else if (!(type2 instanceof ParameterizedType) || String.class.isAssignableFrom(TypeToken.get(((ParameterizedType) type2).getActualTypeArguments()[0]).getRawType())) {
                new ObjectConstructor<T>(this) {
                    final /* synthetic */ ConstructorConstructor this$0;

                    {
                        this.this$0 = this$0;
                    }

                    /* JADX WARNING: Multi-variable type inference failed */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public T construct() {
                        /*
                            r4 = this;
                            r0 = r4
                            com.google.gson.internal.LinkedTreeMap r1 = new com.google.gson.internal.LinkedTreeMap
                            r3 = r1
                            r1 = r3
                            r2 = r3
                            r2.<init>()
                            r0 = r1
                            return r0
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.ConstructorConstructor.C157113.construct():java.lang.Object");
                    }
                };
                return objectConstructor;
            } else {
                new ObjectConstructor<T>(this) {
                    final /* synthetic */ ConstructorConstructor this$0;

                    {
                        this.this$0 = this$0;
                    }

                    /* JADX WARNING: Multi-variable type inference failed */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public T construct() {
                        /*
                            r4 = this;
                            r0 = r4
                            java.util.LinkedHashMap r1 = new java.util.LinkedHashMap
                            r3 = r1
                            r1 = r3
                            r2 = r3
                            r2.<init>()
                            r0 = r1
                            return r0
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.ConstructorConstructor.C157012.construct():java.lang.Object");
                    }
                };
                return objectConstructor2;
            }
        }
    }

    private <T> ObjectConstructor<T> newUnsafeAllocator(Type type, Class<? super T> rawType) {
        ObjectConstructor<T> objectConstructor;
        final Class<? super T> cls = rawType;
        final Type type2 = type;
        new ObjectConstructor<T>(this) {
            final /* synthetic */ ConstructorConstructor this$0;
            private final UnsafeAllocator unsafeAllocator = UnsafeAllocator.create();

            {
                this.this$0 = this$0;
            }

            public T construct() {
                Throwable th;
                StringBuilder sb;
                try {
                    return this.unsafeAllocator.newInstance(cls);
                } catch (Exception e) {
                    Exception e2 = e;
                    Throwable th2 = th;
                    new StringBuilder();
                    new RuntimeException(sb.append("Unable to invoke no-args constructor for ").append(type2).append(". Registering an InstanceCreator with Gson for this type may fix this problem.").toString(), e2);
                    throw th2;
                }
            }
        };
        return objectConstructor;
    }

    public String toString() {
        return this.instanceCreators.toString();
    }
}

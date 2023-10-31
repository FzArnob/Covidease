package android.arch.lifecycle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class Lifecycling {
    private static final int GENERATED_CALLBACK = 2;
    private static final int REFLECTIVE_CALLBACK = 1;
    private static Map<Class, Integer> sCallbackCache;
    private static Map<Class, List<Constructor<? extends GeneratedAdapter>>> sClassToAdapters;

    public Lifecycling() {
    }

    static {
        Map<Class, Integer> map;
        Map<Class, List<Constructor<? extends GeneratedAdapter>>> map2;
        new HashMap();
        sCallbackCache = map;
        new HashMap();
        sClassToAdapters = map2;
    }

    @NonNull
    static GenericLifecycleObserver getCallback(Object obj) {
        GenericLifecycleObserver genericLifecycleObserver;
        GenericLifecycleObserver genericLifecycleObserver2;
        GenericLifecycleObserver genericLifecycleObserver3;
        GenericLifecycleObserver genericLifecycleObserver4;
        Object object = obj;
        if (object instanceof FullLifecycleObserver) {
            new FullLifecycleObserverAdapter((FullLifecycleObserver) object);
            return genericLifecycleObserver4;
        } else if (object instanceof GenericLifecycleObserver) {
            return (GenericLifecycleObserver) object;
        } else {
            Class<?> klass = object.getClass();
            if (getObserverConstructorType(klass) == 2) {
                List<Constructor<? extends GeneratedAdapter>> constructors = sClassToAdapters.get(klass);
                if (constructors.size() == 1) {
                    new SingleGeneratedAdapterObserver(createGeneratedAdapter(constructors.get(0), object));
                    return genericLifecycleObserver3;
                }
                GeneratedAdapter[] adapters = new GeneratedAdapter[constructors.size()];
                for (int i = 0; i < constructors.size(); i++) {
                    adapters[i] = createGeneratedAdapter(constructors.get(i), object);
                }
                new CompositeGeneratedAdaptersObserver(adapters);
                return genericLifecycleObserver2;
            }
            new ReflectiveGenericLifecycleObserver(object);
            return genericLifecycleObserver;
        }
    }

    private static GeneratedAdapter createGeneratedAdapter(Constructor<? extends GeneratedAdapter> constructor, Object object) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        try {
            return (GeneratedAdapter) constructor.newInstance(new Object[]{object});
        } catch (IllegalAccessException e) {
            IllegalAccessException e2 = e;
            Throwable th4 = th3;
            new RuntimeException(e2);
            throw th4;
        } catch (InstantiationException e3) {
            InstantiationException e4 = e3;
            Throwable th5 = th2;
            new RuntimeException(e4);
            throw th5;
        } catch (InvocationTargetException e5) {
            InvocationTargetException e6 = e5;
            Throwable th6 = th;
            new RuntimeException(e6);
            throw th6;
        }
    }

    @Nullable
    private static Constructor<? extends GeneratedAdapter> generatedConstructor(Class<?> cls) {
        Throwable th;
        String substring;
        StringBuilder sb;
        String sb2;
        Class<?> klass = cls;
        try {
            Package aPackage = klass.getPackage();
            String name = klass.getCanonicalName();
            String fullPackage = aPackage != null ? aPackage.getName() : "";
            if (fullPackage.isEmpty()) {
                substring = name;
            } else {
                substring = name.substring(fullPackage.length() + 1);
            }
            String adapterName = getAdapterName(substring);
            if (fullPackage.isEmpty()) {
                sb2 = adapterName;
            } else {
                new StringBuilder();
                sb2 = sb.append(fullPackage).append(".").append(adapterName).toString();
            }
            Constructor<?> declaredConstructor = Class.forName(sb2).getDeclaredConstructor(new Class[]{klass});
            if (!declaredConstructor.isAccessible()) {
                declaredConstructor.setAccessible(true);
            }
            return declaredConstructor;
        } catch (ClassNotFoundException e) {
            ClassNotFoundException classNotFoundException = e;
            return null;
        } catch (NoSuchMethodException e2) {
            NoSuchMethodException e3 = e2;
            Throwable th2 = th;
            new RuntimeException(e3);
            throw th2;
        }
    }

    private static int getObserverConstructorType(Class<?> cls) {
        Class<?> klass = cls;
        if (sCallbackCache.containsKey(klass)) {
            return sCallbackCache.get(klass).intValue();
        }
        int type = resolveObserverCallbackType(klass);
        Integer put = sCallbackCache.put(klass, Integer.valueOf(type));
        return type;
    }

    private static int resolveObserverCallbackType(Class<?> cls) {
        List<Constructor<? extends GeneratedAdapter>> list;
        List<Constructor<? extends GeneratedAdapter>> list2;
        Class<?> klass = cls;
        if (klass.getCanonicalName() == null) {
            return 1;
        }
        Constructor<? extends GeneratedAdapter> constructor = generatedConstructor(klass);
        if (constructor != null) {
            List<Constructor<? extends GeneratedAdapter>> put = sClassToAdapters.put(klass, Collections.singletonList(constructor));
            return 2;
        } else if (ClassesInfoCache.sInstance.hasLifecycleMethods(klass)) {
            return 1;
        } else {
            Class<? super Object> superclass = klass.getSuperclass();
            List<Constructor<? extends GeneratedAdapter>> adapterConstructors = null;
            if (isLifecycleParent(superclass)) {
                if (getObserverConstructorType(superclass) == 1) {
                    return 1;
                }
                new ArrayList<>(sClassToAdapters.get(superclass));
                adapterConstructors = list2;
            }
            Class<?>[] interfaces = klass.getInterfaces();
            int length = interfaces.length;
            for (int i = 0; i < length; i++) {
                Class<?> intrface = interfaces[i];
                if (isLifecycleParent(intrface)) {
                    if (getObserverConstructorType(intrface) == 1) {
                        return 1;
                    }
                    if (adapterConstructors == null) {
                        new ArrayList<>();
                        adapterConstructors = list;
                    }
                    boolean addAll = adapterConstructors.addAll(sClassToAdapters.get(intrface));
                }
            }
            if (adapterConstructors == null) {
                return 1;
            }
            List<Constructor<? extends GeneratedAdapter>> put2 = sClassToAdapters.put(klass, adapterConstructors);
            return 2;
        }
    }

    private static boolean isLifecycleParent(Class<?> cls) {
        Class<?> klass = cls;
        return klass != null && LifecycleObserver.class.isAssignableFrom(klass);
    }

    public static String getAdapterName(String className) {
        StringBuilder sb;
        new StringBuilder();
        return sb.append(className.replace(".", "_")).append("_LifecycleAdapter").toString();
    }
}

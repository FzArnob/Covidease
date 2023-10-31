package android.arch.lifecycle;

import android.arch.lifecycle.Lifecycle;
import android.support.annotation.Nullable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ClassesInfoCache {
    private static final int CALL_TYPE_NO_ARG = 0;
    private static final int CALL_TYPE_PROVIDER = 1;
    private static final int CALL_TYPE_PROVIDER_WITH_EVENT = 2;
    static ClassesInfoCache sInstance;
    private final Map<Class, CallbackInfo> mCallbackMap;
    private final Map<Class, Boolean> mHasLifecycleMethods;

    ClassesInfoCache() {
        Map<Class, CallbackInfo> map;
        Map<Class, Boolean> map2;
        new HashMap();
        this.mCallbackMap = map;
        new HashMap();
        this.mHasLifecycleMethods = map2;
    }

    static {
        ClassesInfoCache classesInfoCache;
        new ClassesInfoCache();
        sInstance = classesInfoCache;
    }

    /* access modifiers changed from: package-private */
    public boolean hasLifecycleMethods(Class cls) {
        Class klass = cls;
        if (this.mHasLifecycleMethods.containsKey(klass)) {
            return this.mHasLifecycleMethods.get(klass).booleanValue();
        }
        Method[] methods = getDeclaredMethods(klass);
        Method[] methodArr = methods;
        int length = methodArr.length;
        for (int i = 0; i < length; i++) {
            if (((OnLifecycleEvent) methodArr[i].getAnnotation(OnLifecycleEvent.class)) != null) {
                CallbackInfo createInfo = createInfo(klass, methods);
                return true;
            }
        }
        Boolean put = this.mHasLifecycleMethods.put(klass, false);
        return false;
    }

    private Method[] getDeclaredMethods(Class klass) {
        Throwable th;
        try {
            return klass.getDeclaredMethods();
        } catch (NoClassDefFoundError e) {
            NoClassDefFoundError e2 = e;
            Throwable th2 = th;
            new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", e2);
            throw th2;
        }
    }

    /* access modifiers changed from: package-private */
    public CallbackInfo getInfo(Class cls) {
        Class klass = cls;
        CallbackInfo existing = this.mCallbackMap.get(klass);
        if (existing != null) {
            return existing;
        }
        return createInfo(klass, (Method[]) null);
    }

    private void verifyAndPutHandler(Map<MethodReference, Lifecycle.Event> map, MethodReference methodReference, Lifecycle.Event event, Class cls) {
        Throwable th;
        StringBuilder sb;
        Map<MethodReference, Lifecycle.Event> handlers = map;
        MethodReference newHandler = methodReference;
        Lifecycle.Event newEvent = event;
        Class klass = cls;
        Lifecycle.Event event2 = handlers.get(newHandler);
        if (event2 != null && newEvent != event2) {
            Method method = newHandler.mMethod;
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Method ").append(method.getName()).append(" in ").append(klass.getName()).append(" already declared with different @OnLifecycleEvent value: previous value ").append(event2).append(", new value ").append(newEvent).toString());
            throw th2;
        } else if (event2 == null) {
            Lifecycle.Event put = handlers.put(newHandler, newEvent);
        }
    }

    private CallbackInfo createInfo(Class cls, @Nullable Method[] methodArr) {
        Map<MethodReference, Lifecycle.Event> map;
        CallbackInfo callbackInfo;
        Throwable th;
        MethodReference methodReference;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        CallbackInfo superInfo;
        Class klass = cls;
        Method[] declaredMethods = methodArr;
        Class superclass = klass.getSuperclass();
        new HashMap<>();
        Map<MethodReference, Lifecycle.Event> handlerToEvent = map;
        if (!(superclass == null || (superInfo = getInfo(superclass)) == null)) {
            handlerToEvent.putAll(superInfo.mHandlerToEvent);
        }
        Class[] interfaces = klass.getInterfaces();
        int length = interfaces.length;
        for (int i = 0; i < length; i++) {
            for (Map.Entry<MethodReference, Lifecycle.Event> entry : getInfo(interfaces[i]).mHandlerToEvent.entrySet()) {
                verifyAndPutHandler(handlerToEvent, entry.getKey(), entry.getValue(), klass);
            }
        }
        boolean hasLifecycleMethods = false;
        Method[] declaredMethods2 = declaredMethods != null ? declaredMethods : getDeclaredMethods(klass);
        int length2 = declaredMethods2.length;
        for (int i2 = 0; i2 < length2; i2++) {
            Method method = declaredMethods2[i2];
            OnLifecycleEvent annotation = (OnLifecycleEvent) method.getAnnotation(OnLifecycleEvent.class);
            if (annotation != null) {
                hasLifecycleMethods = true;
                Class<?>[] params = method.getParameterTypes();
                int callType = 0;
                if (params.length > 0) {
                    callType = 1;
                    if (!params[0].isAssignableFrom(LifecycleOwner.class)) {
                        Throwable th5 = th4;
                        new IllegalArgumentException("invalid parameter type. Must be one and instanceof LifecycleOwner");
                        throw th5;
                    }
                }
                Lifecycle.Event event = annotation.value();
                if (params.length > 1) {
                    callType = 2;
                    if (!params[1].isAssignableFrom(Lifecycle.Event.class)) {
                        Throwable th6 = th2;
                        new IllegalArgumentException("invalid parameter type. second arg must be an event");
                        throw th6;
                    } else if (event != Lifecycle.Event.ON_ANY) {
                        Throwable th7 = th3;
                        new IllegalArgumentException("Second arg is supported only for ON_ANY value");
                        throw th7;
                    }
                }
                if (params.length > 2) {
                    Throwable th8 = th;
                    new IllegalArgumentException("cannot have more than 2 params");
                    throw th8;
                }
                new MethodReference(callType, method);
                verifyAndPutHandler(handlerToEvent, methodReference, event, klass);
            }
        }
        new CallbackInfo(handlerToEvent);
        CallbackInfo info = callbackInfo;
        CallbackInfo put = this.mCallbackMap.put(klass, info);
        Boolean put2 = this.mHasLifecycleMethods.put(klass, Boolean.valueOf(hasLifecycleMethods));
        return info;
    }

    static class CallbackInfo {
        final Map<Lifecycle.Event, List<MethodReference>> mEventToHandlers;
        final Map<MethodReference, Lifecycle.Event> mHandlerToEvent;

        CallbackInfo(Map<MethodReference, Lifecycle.Event> map) {
            Map<Lifecycle.Event, List<MethodReference>> map2;
            List<MethodReference> list;
            Map<MethodReference, Lifecycle.Event> handlerToEvent = map;
            this.mHandlerToEvent = handlerToEvent;
            new HashMap();
            this.mEventToHandlers = map2;
            for (Map.Entry<MethodReference, Lifecycle.Event> entry : handlerToEvent.entrySet()) {
                Lifecycle.Event event = entry.getValue();
                List<MethodReference> methodReferences = this.mEventToHandlers.get(event);
                if (methodReferences == null) {
                    new ArrayList<>();
                    methodReferences = list;
                    List<MethodReference> put = this.mEventToHandlers.put(event, methodReferences);
                }
                boolean add = methodReferences.add(entry.getKey());
            }
        }

        /* access modifiers changed from: package-private */
        public void invokeCallbacks(LifecycleOwner lifecycleOwner, Lifecycle.Event event, Object obj) {
            LifecycleOwner source = lifecycleOwner;
            Lifecycle.Event event2 = event;
            Object target = obj;
            invokeMethodsForEvent(this.mEventToHandlers.get(event2), source, event2, target);
            invokeMethodsForEvent(this.mEventToHandlers.get(Lifecycle.Event.ON_ANY), source, event2, target);
        }

        private static void invokeMethodsForEvent(List<MethodReference> list, LifecycleOwner lifecycleOwner, Lifecycle.Event event, Object obj) {
            List<MethodReference> handlers = list;
            LifecycleOwner source = lifecycleOwner;
            Lifecycle.Event event2 = event;
            Object mWrapped = obj;
            if (handlers != null) {
                for (int i = handlers.size() - 1; i >= 0; i--) {
                    handlers.get(i).invokeCallback(source, event2, mWrapped);
                }
            }
        }
    }

    static class MethodReference {
        final int mCallType;
        final Method mMethod;

        MethodReference(int callType, Method method) {
            this.mCallType = callType;
            this.mMethod = method;
            this.mMethod.setAccessible(true);
        }

        /* access modifiers changed from: package-private */
        public void invokeCallback(LifecycleOwner lifecycleOwner, Lifecycle.Event event, Object obj) {
            Throwable th;
            Throwable th2;
            LifecycleOwner source = lifecycleOwner;
            Lifecycle.Event event2 = event;
            Object target = obj;
            try {
                switch (this.mCallType) {
                    case 0:
                        Object invoke = this.mMethod.invoke(target, new Object[0]);
                        return;
                    case 1:
                        Object invoke2 = this.mMethod.invoke(target, new Object[]{source});
                        return;
                    case 2:
                        Object[] objArr = new Object[2];
                        objArr[0] = source;
                        Object[] objArr2 = objArr;
                        objArr2[1] = event2;
                        Object invoke3 = this.mMethod.invoke(target, objArr2);
                        return;
                    default:
                        return;
                }
            } catch (InvocationTargetException e) {
                InvocationTargetException e2 = e;
                Throwable th3 = th2;
                new RuntimeException("Failed to call observer method", e2.getCause());
                throw th3;
            } catch (IllegalAccessException e3) {
                IllegalAccessException e4 = e3;
                Throwable th4 = th;
                new RuntimeException(e4);
                throw th4;
            }
        }

        public boolean equals(Object obj) {
            Object o = obj;
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            MethodReference that = (MethodReference) o;
            return this.mCallType == that.mCallType && this.mMethod.getName().equals(that.mMethod.getName());
        }

        public int hashCode() {
            return (31 * this.mCallType) + this.mMethod.getName().hashCode();
        }
    }
}

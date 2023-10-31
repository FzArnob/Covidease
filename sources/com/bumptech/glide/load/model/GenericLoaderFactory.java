package com.bumptech.glide.load.model;

import android.content.Context;
import com.bumptech.glide.load.data.DataFetcher;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GenericLoaderFactory {
    private static final ModelLoader NULL_MODEL_LOADER;
    private final Map<Class, Map<Class, ModelLoader>> cachedModelLoaders;
    private final Context context;
    private final Map<Class, Map<Class, ModelLoaderFactory>> modelClassToResourceFactories;

    static {
        ModelLoader modelLoader;
        new ModelLoader() {
            public DataFetcher getResourceFetcher(Object obj, int i, int i2) {
                Throwable th;
                Object obj2 = obj;
                int i3 = i;
                int i4 = i2;
                Throwable th2 = th;
                new NoSuchMethodError("This should never be called!");
                throw th2;
            }

            public String toString() {
                return "NULL_MODEL_LOADER";
            }
        };
        NULL_MODEL_LOADER = modelLoader;
    }

    public GenericLoaderFactory(Context context2) {
        Map<Class, Map<Class, ModelLoaderFactory>> map;
        Map<Class, Map<Class, ModelLoader>> map2;
        new HashMap();
        this.modelClassToResourceFactories = map;
        new HashMap();
        this.cachedModelLoaders = map2;
        this.context = context2.getApplicationContext();
    }

    public synchronized <T, Y> ModelLoaderFactory<T, Y> unregister(Class<T> cls, Class<Y> cls2) {
        ModelLoaderFactory modelLoaderFactory;
        Class<T> modelClass = cls;
        Class<Y> resourceClass = cls2;
        synchronized (this) {
            this.cachedModelLoaders.clear();
            ModelLoaderFactory result = null;
            Map<Class, ModelLoaderFactory> resourceToFactories = this.modelClassToResourceFactories.get(modelClass);
            if (resourceToFactories != null) {
                result = resourceToFactories.remove(resourceClass);
            }
            modelLoaderFactory = result;
        }
        return modelLoaderFactory;
    }

    public synchronized <T, Y> ModelLoaderFactory<T, Y> register(Class<T> cls, Class<Y> cls2, ModelLoaderFactory<T, Y> modelLoaderFactory) {
        ModelLoaderFactory modelLoaderFactory2;
        Map map;
        Class<T> modelClass = cls;
        Class<Y> resourceClass = cls2;
        ModelLoaderFactory<T, Y> factory = modelLoaderFactory;
        synchronized (this) {
            this.cachedModelLoaders.clear();
            Map map2 = this.modelClassToResourceFactories.get(modelClass);
            if (map2 == null) {
                new HashMap();
                map2 = map;
                Map<Class, ModelLoaderFactory> put = this.modelClassToResourceFactories.put(modelClass, map2);
            }
            ModelLoaderFactory previous = (ModelLoaderFactory) map2.put(resourceClass, factory);
            if (previous != null) {
                Iterator i$ = this.modelClassToResourceFactories.values().iterator();
                while (true) {
                    if (i$.hasNext()) {
                        if (i$.next().containsValue(previous)) {
                            previous = null;
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
            modelLoaderFactory2 = previous;
        }
        return modelLoaderFactory2;
    }

    @Deprecated
    public synchronized <T, Y> ModelLoader<T, Y> buildModelLoader(Class<T> cls, Class<Y> cls2, Context context2) {
        ModelLoader<T, Y> buildModelLoader;
        Class<T> modelClass = cls;
        Class<Y> resourceClass = cls2;
        Context context3 = context2;
        synchronized (this) {
            buildModelLoader = buildModelLoader(modelClass, resourceClass);
        }
        return buildModelLoader;
    }

    public synchronized <T, Y> ModelLoader<T, Y> buildModelLoader(Class<T> cls, Class<Y> cls2) {
        ModelLoader<T, Y> modelLoader;
        Class<T> modelClass = cls;
        Class<Y> resourceClass = cls2;
        synchronized (this) {
            ModelLoader<T, Y> result = getCachedLoader(modelClass, resourceClass);
            if (result != null) {
                modelLoader = NULL_MODEL_LOADER.equals(result) ? null : result;
            } else {
                ModelLoaderFactory<T, Y> factory = getFactory(modelClass, resourceClass);
                if (factory != null) {
                    result = factory.build(this.context, this);
                    cacheModelLoader(modelClass, resourceClass, result);
                } else {
                    cacheNullLoader(modelClass, resourceClass);
                }
                modelLoader = result;
            }
        }
        return modelLoader;
    }

    private <T, Y> void cacheNullLoader(Class<T> modelClass, Class<Y> resourceClass) {
        cacheModelLoader(modelClass, resourceClass, NULL_MODEL_LOADER);
    }

    private <T, Y> void cacheModelLoader(Class<T> cls, Class<Y> cls2, ModelLoader<T, Y> modelLoader) {
        Map<Class, ModelLoader> map;
        Class<T> modelClass = cls;
        Class<Y> resourceClass = cls2;
        ModelLoader<T, Y> modelLoader2 = modelLoader;
        Map<Class, ModelLoader> resourceToLoaders = this.cachedModelLoaders.get(modelClass);
        if (resourceToLoaders == null) {
            new HashMap<>();
            resourceToLoaders = map;
            Map<Class, ModelLoader> put = this.cachedModelLoaders.put(modelClass, resourceToLoaders);
        }
        ModelLoader put2 = resourceToLoaders.put(resourceClass, modelLoader2);
    }

    private <T, Y> ModelLoader<T, Y> getCachedLoader(Class<T> modelClass, Class<Y> cls) {
        Class<Y> resourceClass = cls;
        Map<Class, ModelLoader> resourceToLoaders = this.cachedModelLoaders.get(modelClass);
        ModelLoader result = null;
        if (resourceToLoaders != null) {
            result = resourceToLoaders.get(resourceClass);
        }
        return result;
    }

    private <T, Y> ModelLoaderFactory<T, Y> getFactory(Class<T> cls, Class<Y> cls2) {
        Map<Class, ModelLoaderFactory> currentResourceToFactories;
        Class<T> modelClass = cls;
        Class<Y> resourceClass = cls2;
        Map<Class, ModelLoaderFactory> resourceToFactories = this.modelClassToResourceFactories.get(modelClass);
        ModelLoaderFactory result = null;
        if (resourceToFactories != null) {
            result = resourceToFactories.get(resourceClass);
        }
        if (result == null) {
            for (Class<? super T> registeredModelClass : this.modelClassToResourceFactories.keySet()) {
                if (registeredModelClass.isAssignableFrom(modelClass) && (currentResourceToFactories = this.modelClassToResourceFactories.get(registeredModelClass)) != null) {
                    result = currentResourceToFactories.get(resourceClass);
                    if (result != null) {
                        break;
                    }
                }
            }
        }
        return result;
    }
}

package android.arch.lifecycle;

import android.app.Application;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import java.lang.reflect.InvocationTargetException;

public class ViewModelProvider {
    private static final String DEFAULT_KEY = "android.arch.lifecycle.ViewModelProvider.DefaultKey";
    private final Factory mFactory;
    private final ViewModelStore mViewModelStore;

    public interface Factory {
        @NonNull
        <T extends ViewModel> T create(@NonNull Class<T> cls);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ViewModelProvider(@NonNull ViewModelStoreOwner owner, @NonNull Factory factory) {
        this(owner.getViewModelStore(), factory);
    }

    public ViewModelProvider(@NonNull ViewModelStore store, @NonNull Factory factory) {
        this.mFactory = factory;
        this.mViewModelStore = store;
    }

    @MainThread
    @NonNull
    public <T extends ViewModel> T get(@NonNull Class<T> cls) {
        StringBuilder sb;
        Throwable th;
        Class<T> modelClass = cls;
        String canonicalName = modelClass.getCanonicalName();
        if (canonicalName == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
            throw th2;
        }
        new StringBuilder();
        return get(sb.append("android.arch.lifecycle.ViewModelProvider.DefaultKey:").append(canonicalName).toString(), modelClass);
    }

    @MainThread
    @NonNull
    public <T extends ViewModel> T get(@NonNull String str, @NonNull Class<T> cls) {
        String key = str;
        Class<T> modelClass = cls;
        ViewModel viewModel = this.mViewModelStore.get(key);
        if (modelClass.isInstance(viewModel)) {
            return viewModel;
        }
        if (viewModel != null) {
        }
        ViewModel viewModel2 = this.mFactory.create(modelClass);
        this.mViewModelStore.put(key, viewModel2);
        return viewModel2;
    }

    public static class NewInstanceFactory implements Factory {
        public NewInstanceFactory() {
        }

        @NonNull
        public <T extends ViewModel> T create(@NonNull Class<T> cls) {
            Throwable th;
            StringBuilder sb;
            Throwable th2;
            StringBuilder sb2;
            Class<T> modelClass = cls;
            try {
                return (ViewModel) modelClass.newInstance();
            } catch (InstantiationException e) {
                InstantiationException e2 = e;
                Throwable th3 = th2;
                new StringBuilder();
                new RuntimeException(sb2.append("Cannot create an instance of ").append(modelClass).toString(), e2);
                throw th3;
            } catch (IllegalAccessException e3) {
                IllegalAccessException e4 = e3;
                Throwable th4 = th;
                new StringBuilder();
                new RuntimeException(sb.append("Cannot create an instance of ").append(modelClass).toString(), e4);
                throw th4;
            }
        }
    }

    public static class AndroidViewModelFactory extends NewInstanceFactory {
        private static AndroidViewModelFactory sInstance;
        private Application mApplication;

        @NonNull
        public static AndroidViewModelFactory getInstance(@NonNull Application application) {
            AndroidViewModelFactory androidViewModelFactory;
            Application application2 = application;
            if (sInstance == null) {
                new AndroidViewModelFactory(application2);
                sInstance = androidViewModelFactory;
            }
            return sInstance;
        }

        public AndroidViewModelFactory(@NonNull Application application) {
            this.mApplication = application;
        }

        @NonNull
        public <T extends ViewModel> T create(@NonNull Class<T> cls) {
            Throwable th;
            StringBuilder sb;
            Throwable th2;
            StringBuilder sb2;
            Throwable th3;
            StringBuilder sb3;
            Throwable th4;
            StringBuilder sb4;
            Class<T> modelClass = cls;
            if (!AndroidViewModel.class.isAssignableFrom(modelClass)) {
                return super.create(modelClass);
            }
            try {
                return (ViewModel) modelClass.getConstructor(new Class[]{Application.class}).newInstance(new Object[]{this.mApplication});
            } catch (NoSuchMethodException e) {
                NoSuchMethodException e2 = e;
                Throwable th5 = th4;
                new StringBuilder();
                new RuntimeException(sb4.append("Cannot create an instance of ").append(modelClass).toString(), e2);
                throw th5;
            } catch (IllegalAccessException e3) {
                IllegalAccessException e4 = e3;
                Throwable th6 = th3;
                new StringBuilder();
                new RuntimeException(sb3.append("Cannot create an instance of ").append(modelClass).toString(), e4);
                throw th6;
            } catch (InstantiationException e5) {
                InstantiationException e6 = e5;
                Throwable th7 = th2;
                new StringBuilder();
                new RuntimeException(sb2.append("Cannot create an instance of ").append(modelClass).toString(), e6);
                throw th7;
            } catch (InvocationTargetException e7) {
                InvocationTargetException e8 = e7;
                Throwable th8 = th;
                new StringBuilder();
                new RuntimeException(sb.append("Cannot create an instance of ").append(modelClass).toString(), e8);
                throw th8;
            }
        }
    }
}

package android.support.p000v4.app;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelStore;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p000v4.app.LoaderManager;
import android.support.p000v4.content.Loader;
import android.support.p000v4.util.C1651SparseArrayCompat;
import android.support.p000v4.util.DebugUtils;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;

/* renamed from: android.support.v4.app.LoaderManagerImpl */
class LoaderManagerImpl extends LoaderManager {
    static boolean DEBUG = false;
    static final String TAG = "LoaderManager";
    @NonNull
    private final LifecycleOwner mLifecycleOwner;
    @NonNull
    private final LoaderViewModel mLoaderViewModel;

    /* renamed from: android.support.v4.app.LoaderManagerImpl$LoaderInfo */
    public static class LoaderInfo<D> extends MutableLiveData<D> implements Loader.OnLoadCompleteListener<D> {
        @Nullable
        private final Bundle mArgs;
        private final int mId;
        private LifecycleOwner mLifecycleOwner;
        @NonNull
        private final Loader<D> mLoader;
        private LoaderObserver<D> mObserver;
        private Loader<D> mPriorLoader;

        LoaderInfo(int i, @Nullable Bundle args, @NonNull Loader<D> loader, @Nullable Loader<D> priorLoader) {
            int id = i;
            this.mId = id;
            this.mArgs = args;
            this.mLoader = loader;
            this.mPriorLoader = priorLoader;
            this.mLoader.registerListener(id, this);
        }

        /* access modifiers changed from: package-private */
        @NonNull
        public Loader<D> getLoader() {
            return this.mLoader;
        }

        /* access modifiers changed from: protected */
        public void onActive() {
            StringBuilder sb;
            if (LoaderManagerImpl.DEBUG) {
                new StringBuilder();
                int v = Log.v(LoaderManagerImpl.TAG, sb.append("  Starting: ").append(this).toString());
            }
            this.mLoader.startLoading();
        }

        /* access modifiers changed from: protected */
        public void onInactive() {
            StringBuilder sb;
            if (LoaderManagerImpl.DEBUG) {
                new StringBuilder();
                int v = Log.v(LoaderManagerImpl.TAG, sb.append("  Stopping: ").append(this).toString());
            }
            this.mLoader.stopLoading();
        }

        /* access modifiers changed from: package-private */
        @MainThread
        @NonNull
        public Loader<D> setCallback(@NonNull LifecycleOwner lifecycleOwner, @NonNull LoaderManager.LoaderCallbacks<D> callback) {
            LoaderObserver loaderObserver;
            LifecycleOwner owner = lifecycleOwner;
            new LoaderObserver(this.mLoader, callback);
            LoaderObserver loaderObserver2 = loaderObserver;
            observe(owner, loaderObserver2);
            if (this.mObserver != null) {
                removeObserver(this.mObserver);
            }
            this.mLifecycleOwner = owner;
            this.mObserver = loaderObserver2;
            return this.mLoader;
        }

        /* access modifiers changed from: package-private */
        public void markForRedelivery() {
            LifecycleOwner lifecycleOwner = this.mLifecycleOwner;
            LoaderObserver<D> observer = this.mObserver;
            if (lifecycleOwner != null && observer != null) {
                super.removeObserver(observer);
                observe(lifecycleOwner, observer);
            }
        }

        /* access modifiers changed from: package-private */
        public boolean isCallbackWaitingForData() {
            if (!hasActiveObservers()) {
                return false;
            }
            return this.mObserver != null && !this.mObserver.hasDeliveredData();
        }

        public void removeObserver(@NonNull Observer<? super D> observer) {
            super.removeObserver(observer);
            this.mLifecycleOwner = null;
            this.mObserver = null;
        }

        /* access modifiers changed from: package-private */
        @MainThread
        public Loader<D> destroy(boolean z) {
            StringBuilder sb;
            boolean reset = z;
            if (LoaderManagerImpl.DEBUG) {
                new StringBuilder();
                int v = Log.v(LoaderManagerImpl.TAG, sb.append("  Destroying: ").append(this).toString());
            }
            boolean cancelLoad = this.mLoader.cancelLoad();
            this.mLoader.abandon();
            LoaderObserver<D> observer = this.mObserver;
            if (observer != null) {
                removeObserver(observer);
                if (reset) {
                    observer.reset();
                }
            }
            this.mLoader.unregisterListener(this);
            if ((observer == null || observer.hasDeliveredData()) && !reset) {
                return this.mLoader;
            }
            this.mLoader.reset();
            return this.mPriorLoader;
        }

        public void onLoadComplete(@NonNull Loader<D> loader, @Nullable D d) {
            StringBuilder sb;
            Loader<D> loader2 = loader;
            D data = d;
            if (LoaderManagerImpl.DEBUG) {
                new StringBuilder();
                int v = Log.v(LoaderManagerImpl.TAG, sb.append("onLoadComplete: ").append(this).toString());
            }
            if (Looper.myLooper() == Looper.getMainLooper()) {
                setValue(data);
                return;
            }
            if (LoaderManagerImpl.DEBUG) {
                int w = Log.w(LoaderManagerImpl.TAG, "onLoadComplete was incorrectly called on a background thread");
            }
            postValue(data);
        }

        public void setValue(D value) {
            super.setValue(value);
            if (this.mPriorLoader != null) {
                this.mPriorLoader.reset();
                this.mPriorLoader = null;
            }
        }

        public String toString() {
            StringBuilder sb;
            new StringBuilder(64);
            StringBuilder sb2 = sb;
            StringBuilder append = sb2.append("LoaderInfo{");
            StringBuilder append2 = sb2.append(Integer.toHexString(System.identityHashCode(this)));
            StringBuilder append3 = sb2.append(" #");
            StringBuilder append4 = sb2.append(this.mId);
            StringBuilder append5 = sb2.append(" : ");
            DebugUtils.buildShortClassTag(this.mLoader, sb2);
            StringBuilder append6 = sb2.append("}}");
            return sb2.toString();
        }

        public void dump(String str, FileDescriptor fd, PrintWriter printWriter, String[] args) {
            StringBuilder sb;
            StringBuilder sb2;
            String prefix = str;
            PrintWriter writer = printWriter;
            writer.print(prefix);
            writer.print("mId=");
            writer.print(this.mId);
            writer.print(" mArgs=");
            writer.println(this.mArgs);
            writer.print(prefix);
            writer.print("mLoader=");
            writer.println(this.mLoader);
            Loader<D> loader = this.mLoader;
            new StringBuilder();
            loader.dump(sb.append(prefix).append("  ").toString(), fd, writer, args);
            if (this.mObserver != null) {
                writer.print(prefix);
                writer.print("mCallbacks=");
                writer.println(this.mObserver);
                LoaderObserver<D> loaderObserver = this.mObserver;
                new StringBuilder();
                loaderObserver.dump(sb2.append(prefix).append("  ").toString(), writer);
            }
            writer.print(prefix);
            writer.print("mData=");
            writer.println(getLoader().dataToString(getValue()));
            writer.print(prefix);
            writer.print("mStarted=");
            writer.println(hasActiveObservers());
        }
    }

    /* renamed from: android.support.v4.app.LoaderManagerImpl$LoaderObserver */
    static class LoaderObserver<D> implements Observer<D> {
        @NonNull
        private final LoaderManager.LoaderCallbacks<D> mCallback;
        private boolean mDeliveredData = false;
        @NonNull
        private final Loader<D> mLoader;

        LoaderObserver(@NonNull Loader<D> loader, @NonNull LoaderManager.LoaderCallbacks<D> callback) {
            this.mLoader = loader;
            this.mCallback = callback;
        }

        public void onChanged(@Nullable D d) {
            StringBuilder sb;
            D data = d;
            if (LoaderManagerImpl.DEBUG) {
                new StringBuilder();
                int v = Log.v(LoaderManagerImpl.TAG, sb.append("  onLoadFinished in ").append(this.mLoader).append(": ").append(this.mLoader.dataToString(data)).toString());
            }
            this.mCallback.onLoadFinished(this.mLoader, data);
            this.mDeliveredData = true;
        }

        /* access modifiers changed from: package-private */
        public boolean hasDeliveredData() {
            return this.mDeliveredData;
        }

        /* access modifiers changed from: package-private */
        @MainThread
        public void reset() {
            StringBuilder sb;
            if (this.mDeliveredData) {
                if (LoaderManagerImpl.DEBUG) {
                    new StringBuilder();
                    int v = Log.v(LoaderManagerImpl.TAG, sb.append("  Resetting: ").append(this.mLoader).toString());
                }
                this.mCallback.onLoaderReset(this.mLoader);
            }
        }

        public String toString() {
            return this.mCallback.toString();
        }

        public void dump(String prefix, PrintWriter printWriter) {
            PrintWriter writer = printWriter;
            writer.print(prefix);
            writer.print("mDeliveredData=");
            writer.println(this.mDeliveredData);
        }
    }

    /* renamed from: android.support.v4.app.LoaderManagerImpl$LoaderViewModel */
    static class LoaderViewModel extends ViewModel {
        private static final ViewModelProvider.Factory FACTORY;
        private boolean mCreatingLoader = false;
        private C1651SparseArrayCompat<LoaderInfo> mLoaders;

        LoaderViewModel() {
            C1651SparseArrayCompat<LoaderInfo> sparseArrayCompat;
            new C1651SparseArrayCompat<>();
            this.mLoaders = sparseArrayCompat;
        }

        static {
            ViewModelProvider.Factory factory;
            new ViewModelProvider.Factory() {
                /* JADX WARNING: Multi-variable type inference failed */
                @android.support.annotation.NonNull
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public <T extends android.arch.lifecycle.ViewModel> T create(@android.support.annotation.NonNull java.lang.Class<T> r6) {
                    /*
                        r5 = this;
                        r0 = r5
                        r1 = r6
                        android.support.v4.app.LoaderManagerImpl$LoaderViewModel r2 = new android.support.v4.app.LoaderManagerImpl$LoaderViewModel
                        r4 = r2
                        r2 = r4
                        r3 = r4
                        r3.<init>()
                        r0 = r2
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.app.LoaderManagerImpl.LoaderViewModel.C02701.create(java.lang.Class):android.arch.lifecycle.ViewModel");
                }
            };
            FACTORY = factory;
        }

        @NonNull
        static LoaderViewModel getInstance(ViewModelStore viewModelStore) {
            ViewModelProvider viewModelProvider;
            new ViewModelProvider(viewModelStore, FACTORY);
            return (LoaderViewModel) viewModelProvider.get(LoaderViewModel.class);
        }

        /* access modifiers changed from: package-private */
        public void startCreatingLoader() {
            this.mCreatingLoader = true;
        }

        /* access modifiers changed from: package-private */
        public boolean isCreatingLoader() {
            return this.mCreatingLoader;
        }

        /* access modifiers changed from: package-private */
        public void finishCreatingLoader() {
            this.mCreatingLoader = false;
        }

        /* access modifiers changed from: package-private */
        public void putLoader(int id, @NonNull LoaderInfo info) {
            this.mLoaders.put(id, info);
        }

        /* access modifiers changed from: package-private */
        public <D> LoaderInfo<D> getLoader(int id) {
            return this.mLoaders.get(id);
        }

        /* access modifiers changed from: package-private */
        public void removeLoader(int id) {
            this.mLoaders.remove(id);
        }

        /* access modifiers changed from: package-private */
        public boolean hasRunningLoaders() {
            int size = this.mLoaders.size();
            for (int index = 0; index < size; index++) {
                if (this.mLoaders.valueAt(index).isCallbackWaitingForData()) {
                    return true;
                }
            }
            return false;
        }

        /* access modifiers changed from: package-private */
        public void markForRedelivery() {
            int size = this.mLoaders.size();
            for (int index = 0; index < size; index++) {
                this.mLoaders.valueAt(index).markForRedelivery();
            }
        }

        /* access modifiers changed from: protected */
        public void onCleared() {
            super.onCleared();
            int size = this.mLoaders.size();
            for (int index = 0; index < size; index++) {
                Loader destroy = this.mLoaders.valueAt(index).destroy(true);
            }
            this.mLoaders.clear();
        }

        public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            StringBuilder sb;
            String prefix = str;
            FileDescriptor fd = fileDescriptor;
            PrintWriter writer = printWriter;
            String[] args = strArr;
            if (this.mLoaders.size() > 0) {
                writer.print(prefix);
                writer.println("Loaders:");
                new StringBuilder();
                String innerPrefix = sb.append(prefix).append("    ").toString();
                for (int i = 0; i < this.mLoaders.size(); i++) {
                    LoaderInfo info = this.mLoaders.valueAt(i);
                    writer.print(prefix);
                    writer.print("  #");
                    writer.print(this.mLoaders.keyAt(i));
                    writer.print(": ");
                    writer.println(info.toString());
                    info.dump(innerPrefix, fd, writer, args);
                }
            }
        }
    }

    LoaderManagerImpl(@NonNull LifecycleOwner lifecycleOwner, @NonNull ViewModelStore viewModelStore) {
        this.mLifecycleOwner = lifecycleOwner;
        this.mLoaderViewModel = LoaderViewModel.getInstance(viewModelStore);
    }

    @MainThread
    @NonNull
    private <D> Loader<D> createAndInstallLoader(int i, @Nullable Bundle bundle, @NonNull LoaderManager.LoaderCallbacks<D> loaderCallbacks, @Nullable Loader<D> loader) {
        LoaderInfo loaderInfo;
        StringBuilder sb;
        Throwable th;
        StringBuilder sb2;
        Throwable th2;
        int id = i;
        Bundle args = bundle;
        LoaderManager.LoaderCallbacks<D> callback = loaderCallbacks;
        Loader<D> priorLoader = loader;
        try {
            this.mLoaderViewModel.startCreatingLoader();
            Loader<D> loader2 = callback.onCreateLoader(id, args);
            if (loader2 == null) {
                Throwable th3 = th2;
                new IllegalArgumentException("Object returned from onCreateLoader must not be null");
                throw th3;
            } else if (!loader2.getClass().isMemberClass() || Modifier.isStatic(loader2.getClass().getModifiers())) {
                new LoaderInfo(id, args, loader2, priorLoader);
                LoaderInfo loaderInfo2 = loaderInfo;
                if (DEBUG) {
                    new StringBuilder();
                    int v = Log.v(TAG, sb.append("  Created new loader ").append(loaderInfo2).toString());
                }
                this.mLoaderViewModel.putLoader(id, loaderInfo2);
                this.mLoaderViewModel.finishCreatingLoader();
                return loaderInfo2.setCallback(this.mLifecycleOwner, callback);
            } else {
                Throwable th4 = th;
                new StringBuilder();
                new IllegalArgumentException(sb2.append("Object returned from onCreateLoader must not be a non-static inner member class: ").append(loader2).toString());
                throw th4;
            }
        } catch (Throwable th5) {
            Throwable th6 = th5;
            this.mLoaderViewModel.finishCreatingLoader();
            throw th6;
        }
    }

    @MainThread
    @NonNull
    public <D> Loader<D> initLoader(int i, @Nullable Bundle bundle, @NonNull LoaderManager.LoaderCallbacks<D> loaderCallbacks) {
        StringBuilder sb;
        StringBuilder sb2;
        Throwable th;
        Throwable th2;
        int id = i;
        Bundle args = bundle;
        LoaderManager.LoaderCallbacks<D> callback = loaderCallbacks;
        if (this.mLoaderViewModel.isCreatingLoader()) {
            Throwable th3 = th2;
            new IllegalStateException("Called while creating a loader");
            throw th3;
        } else if (Looper.getMainLooper() != Looper.myLooper()) {
            Throwable th4 = th;
            new IllegalStateException("initLoader must be called on the main thread");
            throw th4;
        } else {
            LoaderInfo<D> info = this.mLoaderViewModel.getLoader(id);
            if (DEBUG) {
                new StringBuilder();
                int v = Log.v(TAG, sb2.append("initLoader in ").append(this).append(": args=").append(args).toString());
            }
            if (info == null) {
                return createAndInstallLoader(id, args, callback, (Loader) null);
            }
            if (DEBUG) {
                new StringBuilder();
                int v2 = Log.v(TAG, sb.append("  Re-using existing loader ").append(info).toString());
            }
            return info.setCallback(this.mLifecycleOwner, callback);
        }
    }

    @MainThread
    @NonNull
    public <D> Loader<D> restartLoader(int i, @Nullable Bundle bundle, @NonNull LoaderManager.LoaderCallbacks<D> loaderCallbacks) {
        StringBuilder sb;
        Throwable th;
        Throwable th2;
        int id = i;
        Bundle args = bundle;
        LoaderManager.LoaderCallbacks<D> callback = loaderCallbacks;
        if (this.mLoaderViewModel.isCreatingLoader()) {
            Throwable th3 = th2;
            new IllegalStateException("Called while creating a loader");
            throw th3;
        } else if (Looper.getMainLooper() != Looper.myLooper()) {
            Throwable th4 = th;
            new IllegalStateException("restartLoader must be called on the main thread");
            throw th4;
        } else {
            if (DEBUG) {
                new StringBuilder();
                int v = Log.v(TAG, sb.append("restartLoader in ").append(this).append(": args=").append(args).toString());
            }
            LoaderInfo<D> info = this.mLoaderViewModel.getLoader(id);
            Loader<D> priorLoader = null;
            if (info != null) {
                priorLoader = info.destroy(false);
            }
            return createAndInstallLoader(id, args, callback, priorLoader);
        }
    }

    @MainThread
    public void destroyLoader(int i) {
        StringBuilder sb;
        Throwable th;
        Throwable th2;
        int id = i;
        if (this.mLoaderViewModel.isCreatingLoader()) {
            Throwable th3 = th2;
            new IllegalStateException("Called while creating a loader");
            throw th3;
        } else if (Looper.getMainLooper() != Looper.myLooper()) {
            Throwable th4 = th;
            new IllegalStateException("destroyLoader must be called on the main thread");
            throw th4;
        } else {
            if (DEBUG) {
                new StringBuilder();
                int v = Log.v(TAG, sb.append("destroyLoader in ").append(this).append(" of ").append(id).toString());
            }
            LoaderInfo info = this.mLoaderViewModel.getLoader(id);
            if (info != null) {
                Loader destroy = info.destroy(true);
                this.mLoaderViewModel.removeLoader(id);
            }
        }
    }

    @Nullable
    public <D> Loader<D> getLoader(int i) {
        Throwable th;
        int id = i;
        if (this.mLoaderViewModel.isCreatingLoader()) {
            Throwable th2 = th;
            new IllegalStateException("Called while creating a loader");
            throw th2;
        }
        LoaderInfo<D> info = this.mLoaderViewModel.getLoader(id);
        return info != null ? info.getLoader() : null;
    }

    public void markForRedelivery() {
        this.mLoaderViewModel.markForRedelivery();
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder(128);
        StringBuilder sb2 = sb;
        StringBuilder append = sb2.append("LoaderManager{");
        StringBuilder append2 = sb2.append(Integer.toHexString(System.identityHashCode(this)));
        StringBuilder append3 = sb2.append(" in ");
        DebugUtils.buildShortClassTag(this.mLifecycleOwner, sb2);
        StringBuilder append4 = sb2.append("}}");
        return sb2.toString();
    }

    @Deprecated
    public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
        this.mLoaderViewModel.dump(prefix, fd, writer, args);
    }

    public boolean hasRunningLoaders() {
        return this.mLoaderViewModel.hasRunningLoaders();
    }
}

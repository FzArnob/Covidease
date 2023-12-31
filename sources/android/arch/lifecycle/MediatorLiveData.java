package android.arch.lifecycle;

import android.arch.core.internal.SafeIterableMap;
import android.support.annotation.CallSuper;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.Iterator;
import java.util.Map;

public class MediatorLiveData<T> extends MutableLiveData<T> {
    private SafeIterableMap<LiveData<?>, Source<?>> mSources;

    public MediatorLiveData() {
        SafeIterableMap<LiveData<?>, Source<?>> safeIterableMap;
        new SafeIterableMap<>();
        this.mSources = safeIterableMap;
    }

    @MainThread
    public <S> void addSource(@NonNull LiveData<S> liveData, @NonNull Observer<S> observer) {
        Source source;
        Throwable th;
        LiveData<S> source2 = liveData;
        Observer<S> onChanged = observer;
        new Source(source2, onChanged);
        Source source3 = source;
        Source<?> existing = this.mSources.putIfAbsent(source2, source3);
        if (existing != null && existing.mObserver != onChanged) {
            Throwable th2 = th;
            new IllegalArgumentException("This source was already added with the different observer");
            throw th2;
        } else if (existing == null && hasActiveObservers()) {
            source3.plug();
        }
    }

    @MainThread
    public <S> void removeSource(@NonNull LiveData<S> toRemote) {
        Source<?> source = this.mSources.remove(toRemote);
        if (source != null) {
            source.unplug();
        }
    }

    /* access modifiers changed from: protected */
    @CallSuper
    public void onActive() {
        Iterator<Map.Entry<LiveData<?>, Source<?>>> it = this.mSources.iterator();
        while (it.hasNext()) {
            ((Source) it.next().getValue()).plug();
        }
    }

    /* access modifiers changed from: protected */
    @CallSuper
    public void onInactive() {
        Iterator<Map.Entry<LiveData<?>, Source<?>>> it = this.mSources.iterator();
        while (it.hasNext()) {
            ((Source) it.next().getValue()).unplug();
        }
    }

    private static class Source<V> implements Observer<V> {
        final LiveData<V> mLiveData;
        final Observer<V> mObserver;
        int mVersion = -1;

        Source(LiveData<V> liveData, Observer<V> observer) {
            this.mLiveData = liveData;
            this.mObserver = observer;
        }

        /* access modifiers changed from: package-private */
        public void plug() {
            this.mLiveData.observeForever(this);
        }

        /* access modifiers changed from: package-private */
        public void unplug() {
            this.mLiveData.removeObserver(this);
        }

        public void onChanged(@Nullable V v) {
            V v2 = v;
            if (this.mVersion != this.mLiveData.getVersion()) {
                this.mVersion = this.mLiveData.getVersion();
                this.mObserver.onChanged(v2);
            }
        }
    }
}

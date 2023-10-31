package android.arch.lifecycle;

import android.arch.core.executor.ArchTaskExecutor;
import android.arch.core.internal.SafeIterableMap;
import android.arch.lifecycle.Lifecycle;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.Iterator;
import java.util.Map;

public abstract class LiveData<T> {
    /* access modifiers changed from: private */
    public static final Object NOT_SET;
    static final int START_VERSION = -1;
    /* access modifiers changed from: private */
    public int mActiveCount = 0;
    private volatile Object mData = NOT_SET;
    /* access modifiers changed from: private */
    public final Object mDataLock;
    private boolean mDispatchInvalidated;
    private boolean mDispatchingValue;
    private SafeIterableMap<Observer<T>, LiveData<T>.ObserverWrapper> mObservers;
    /* access modifiers changed from: private */
    public volatile Object mPendingData = NOT_SET;
    private final Runnable mPostValueRunnable;
    private int mVersion = -1;

    public LiveData() {
        Object obj;
        SafeIterableMap<Observer<T>, LiveData<T>.ObserverWrapper> safeIterableMap;
        Runnable runnable;
        new Object();
        this.mDataLock = obj;
        new SafeIterableMap<>();
        this.mObservers = safeIterableMap;
        new Runnable(this) {
            final /* synthetic */ LiveData this$0;

            {
                this.this$0 = this$0;
            }

            /* JADX INFO: finally extract failed */
            public void run() {
                Object access$000 = this.this$0.mDataLock;
                Object obj = access$000;
                synchronized (access$000) {
                    try {
                        Object newValue = this.this$0.mPendingData;
                        Object access$102 = LiveData.access$102(this.this$0, LiveData.NOT_SET);
                        this.this$0.setValue(newValue);
                    } catch (Throwable th) {
                        while (true) {
                            Throwable th2 = th;
                            Object obj2 = obj;
                            throw th2;
                        }
                    }
                }
            }
        };
        this.mPostValueRunnable = runnable;
    }

    static /* synthetic */ Object access$102(LiveData x0, Object x1) {
        Object obj = x1;
        Object obj2 = obj;
        x0.mPendingData = obj2;
        return obj;
    }

    static /* synthetic */ int access$302(LiveData x0, int x1) {
        int i = x1;
        int i2 = i;
        x0.mActiveCount = i2;
        return i;
    }

    static {
        Object obj;
        new Object();
        NOT_SET = obj;
    }

    private void considerNotify(LiveData<T>.ObserverWrapper observerWrapper) {
        LiveData<T>.ObserverWrapper observer = observerWrapper;
        if (observer.mActive) {
            if (!observer.shouldBeActive()) {
                observer.activeStateChanged(false);
            } else if (observer.mLastVersion < this.mVersion) {
                observer.mLastVersion = this.mVersion;
                observer.mObserver.onChanged(this.mData);
            }
        }
    }

    /* access modifiers changed from: private */
    public void dispatchingValue(@Nullable LiveData<T>.ObserverWrapper observerWrapper) {
        LiveData<T>.ObserverWrapper initiator = observerWrapper;
        if (this.mDispatchingValue) {
            this.mDispatchInvalidated = true;
            return;
        }
        this.mDispatchingValue = true;
        do {
            this.mDispatchInvalidated = false;
            if (initiator == null) {
                Iterator<Map.Entry<Observer<T>, LiveData<T>.ObserverWrapper>> iterator = this.mObservers.iteratorWithAdditions();
                while (iterator.hasNext()) {
                    considerNotify((ObserverWrapper) iterator.next().getValue());
                    if (this.mDispatchInvalidated) {
                        break;
                    }
                }
            } else {
                considerNotify(initiator);
                initiator = null;
            }
        } while (this.mDispatchInvalidated);
        this.mDispatchingValue = false;
    }

    @MainThread
    public void observe(@NonNull LifecycleOwner lifecycleOwner, @NonNull Observer<T> observer) {
        LifecycleObserver lifecycleObserver;
        Throwable th;
        LifecycleOwner owner = lifecycleOwner;
        Observer<T> observer2 = observer;
        if (owner.getLifecycle().getCurrentState() != Lifecycle.State.DESTROYED) {
            new LifecycleBoundObserver(this, owner, observer2);
            LifecycleObserver lifecycleObserver2 = lifecycleObserver;
            LiveData<T>.ObserverWrapper existing = this.mObservers.putIfAbsent(observer2, lifecycleObserver2);
            if (existing != null && !existing.isAttachedTo(owner)) {
                Throwable th2 = th;
                new IllegalArgumentException("Cannot add the same observer with different lifecycles");
                throw th2;
            } else if (existing == null) {
                owner.getLifecycle().addObserver(lifecycleObserver2);
            }
        }
    }

    @MainThread
    public void observeForever(@NonNull Observer<T> observer) {
        AlwaysActiveObserver alwaysActiveObserver;
        Throwable th;
        Observer<T> observer2 = observer;
        new AlwaysActiveObserver(this, observer2);
        AlwaysActiveObserver alwaysActiveObserver2 = alwaysActiveObserver;
        LiveData<T>.ObserverWrapper existing = this.mObservers.putIfAbsent(observer2, alwaysActiveObserver2);
        if (existing != null && (existing instanceof LifecycleBoundObserver)) {
            Throwable th2 = th;
            new IllegalArgumentException("Cannot add the same observer with different lifecycles");
            throw th2;
        } else if (existing == null) {
            alwaysActiveObserver2.activeStateChanged(true);
        }
    }

    @MainThread
    public void removeObserver(@NonNull Observer<T> observer) {
        assertMainThread("removeObserver");
        LiveData<T>.ObserverWrapper removed = this.mObservers.remove(observer);
        if (removed != null) {
            removed.detachObserver();
            removed.activeStateChanged(false);
        }
    }

    @MainThread
    public void removeObservers(@NonNull LifecycleOwner lifecycleOwner) {
        LifecycleOwner owner = lifecycleOwner;
        assertMainThread("removeObservers");
        Iterator<Map.Entry<Observer<T>, LiveData<T>.ObserverWrapper>> it = this.mObservers.iterator();
        while (it.hasNext()) {
            Map.Entry<Observer<T>, LiveData<T>.ObserverWrapper> entry = it.next();
            if (entry.getValue().isAttachedTo(owner)) {
                removeObserver(entry.getKey());
            }
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    public void postValue(T t) {
        T value = t;
        Object obj = this.mDataLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                boolean postTask = this.mPendingData == NOT_SET;
                this.mPendingData = value;
                if (postTask) {
                    ArchTaskExecutor.getInstance().postToMainThread(this.mPostValueRunnable);
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    /* access modifiers changed from: protected */
    @MainThread
    public void setValue(T value) {
        assertMainThread("setValue");
        this.mVersion++;
        this.mData = value;
        dispatchingValue((LiveData<T>.ObserverWrapper) null);
    }

    @Nullable
    public T getValue() {
        Object data = this.mData;
        if (data != NOT_SET) {
            return data;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public int getVersion() {
        return this.mVersion;
    }

    /* access modifiers changed from: protected */
    public void onActive() {
    }

    /* access modifiers changed from: protected */
    public void onInactive() {
    }

    public boolean hasObservers() {
        return this.mObservers.size() > 0;
    }

    public boolean hasActiveObservers() {
        return this.mActiveCount > 0;
    }

    class LifecycleBoundObserver extends LiveData<T>.ObserverWrapper implements GenericLifecycleObserver {
        @NonNull
        final LifecycleOwner mOwner;
        final /* synthetic */ LiveData this$0;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        LifecycleBoundObserver(@android.support.annotation.NonNull android.arch.lifecycle.LiveData r8, android.arch.lifecycle.LifecycleOwner r9, android.arch.lifecycle.Observer<T> r10) {
            /*
                r7 = this;
                r0 = r7
                r1 = r8
                r2 = r9
                r3 = r10
                r4 = r0
                r5 = r1
                r4.this$0 = r5
                r4 = r0
                r5 = r1
                r6 = r3
                r4.<init>(r5, r6)
                r4 = r0
                r5 = r2
                r4.mOwner = r5
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.arch.lifecycle.LiveData.LifecycleBoundObserver.<init>(android.arch.lifecycle.LiveData, android.arch.lifecycle.LifecycleOwner, android.arch.lifecycle.Observer):void");
        }

        /* access modifiers changed from: package-private */
        public boolean shouldBeActive() {
            return this.mOwner.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED);
        }

        public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
            LifecycleOwner lifecycleOwner2 = lifecycleOwner;
            Lifecycle.Event event2 = event;
            if (this.mOwner.getLifecycle().getCurrentState() == Lifecycle.State.DESTROYED) {
                this.this$0.removeObserver(this.mObserver);
            } else {
                activeStateChanged(shouldBeActive());
            }
        }

        /* access modifiers changed from: package-private */
        public boolean isAttachedTo(LifecycleOwner owner) {
            return this.mOwner == owner;
        }

        /* access modifiers changed from: package-private */
        public void detachObserver() {
            this.mOwner.getLifecycle().removeObserver(this);
        }
    }

    private abstract class ObserverWrapper {
        boolean mActive;
        int mLastVersion = -1;
        final Observer<T> mObserver;
        final /* synthetic */ LiveData this$0;

        /* access modifiers changed from: package-private */
        public abstract boolean shouldBeActive();

        ObserverWrapper(LiveData liveData, Observer<T> observer) {
            this.this$0 = liveData;
            this.mObserver = observer;
        }

        /* access modifiers changed from: package-private */
        public boolean isAttachedTo(LifecycleOwner lifecycleOwner) {
            LifecycleOwner lifecycleOwner2 = lifecycleOwner;
            return false;
        }

        /* access modifiers changed from: package-private */
        public void detachObserver() {
        }

        /* access modifiers changed from: package-private */
        public void activeStateChanged(boolean z) {
            boolean newActive = z;
            if (newActive != this.mActive) {
                this.mActive = newActive;
                boolean wasInactive = this.this$0.mActiveCount == 0;
                LiveData liveData = this.this$0;
                int access$302 = LiveData.access$302(liveData, liveData.mActiveCount + (this.mActive ? 1 : -1));
                if (wasInactive && this.mActive) {
                    this.this$0.onActive();
                }
                if (this.this$0.mActiveCount == 0 && !this.mActive) {
                    this.this$0.onInactive();
                }
                if (this.mActive) {
                    this.this$0.dispatchingValue(this);
                }
            }
        }
    }

    private class AlwaysActiveObserver extends LiveData<T>.ObserverWrapper {
        final /* synthetic */ LiveData this$0;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        AlwaysActiveObserver(android.arch.lifecycle.LiveData r7, android.arch.lifecycle.Observer<T> r8) {
            /*
                r6 = this;
                r0 = r6
                r1 = r7
                r2 = r8
                r3 = r0
                r4 = r1
                r3.this$0 = r4
                r3 = r0
                r4 = r1
                r5 = r2
                r3.<init>(r4, r5)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.arch.lifecycle.LiveData.AlwaysActiveObserver.<init>(android.arch.lifecycle.LiveData, android.arch.lifecycle.Observer):void");
        }

        /* access modifiers changed from: package-private */
        public boolean shouldBeActive() {
            return true;
        }
    }

    private static void assertMainThread(String str) {
        Throwable th;
        StringBuilder sb;
        String methodName = str;
        if (!ArchTaskExecutor.getInstance().isMainThread()) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("Cannot invoke ").append(methodName).append(" on a background").append(" thread").toString());
            throw th2;
        }
    }
}

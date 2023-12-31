package android.arch.lifecycle;

import android.arch.lifecycle.Lifecycle;

class FullLifecycleObserverAdapter implements GenericLifecycleObserver {
    private final FullLifecycleObserver mObserver;

    FullLifecycleObserverAdapter(FullLifecycleObserver observer) {
        this.mObserver = observer;
    }

    public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        Throwable th;
        LifecycleOwner source = lifecycleOwner;
        switch (event) {
            case ON_CREATE:
                this.mObserver.onCreate(source);
                return;
            case ON_START:
                this.mObserver.onStart(source);
                return;
            case ON_RESUME:
                this.mObserver.onResume(source);
                return;
            case ON_PAUSE:
                this.mObserver.onPause(source);
                return;
            case ON_STOP:
                this.mObserver.onStop(source);
                return;
            case ON_DESTROY:
                this.mObserver.onDestroy(source);
                return;
            case ON_ANY:
                Throwable th2 = th;
                new IllegalArgumentException("ON_ANY must not been send by anybody");
                throw th2;
            default:
                return;
        }
    }
}

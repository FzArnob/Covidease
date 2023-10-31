package android.arch.lifecycle;

import android.arch.core.internal.FastSafeIterableMap;
import android.arch.lifecycle.Lifecycle;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class LifecycleRegistry extends Lifecycle {
    private static final String LOG_TAG = "LifecycleRegistry";
    private int mAddingObserverCounter = 0;
    private boolean mHandlingEvent = false;
    private final WeakReference<LifecycleOwner> mLifecycleOwner;
    private boolean mNewEventOccurred = false;
    private FastSafeIterableMap<LifecycleObserver, ObserverWithState> mObserverMap;
    private ArrayList<Lifecycle.State> mParentStates;
    private Lifecycle.State mState;

    public LifecycleRegistry(@NonNull LifecycleOwner provider) {
        FastSafeIterableMap<LifecycleObserver, ObserverWithState> fastSafeIterableMap;
        ArrayList<Lifecycle.State> arrayList;
        WeakReference<LifecycleOwner> weakReference;
        new FastSafeIterableMap<>();
        this.mObserverMap = fastSafeIterableMap;
        new ArrayList<>();
        this.mParentStates = arrayList;
        new WeakReference<>(provider);
        this.mLifecycleOwner = weakReference;
        this.mState = Lifecycle.State.INITIALIZED;
    }

    @MainThread
    public void markState(@NonNull Lifecycle.State state) {
        moveToState(state);
    }

    public void handleLifecycleEvent(@NonNull Lifecycle.Event event) {
        moveToState(getStateAfter(event));
    }

    private void moveToState(Lifecycle.State state) {
        Lifecycle.State next = state;
        if (this.mState != next) {
            this.mState = next;
            if (this.mHandlingEvent || this.mAddingObserverCounter != 0) {
                this.mNewEventOccurred = true;
                return;
            }
            this.mHandlingEvent = true;
            sync();
            this.mHandlingEvent = false;
        }
    }

    private boolean isSynced() {
        if (this.mObserverMap.size() == 0) {
            return true;
        }
        Lifecycle.State eldestObserverState = this.mObserverMap.eldest().getValue().mState;
        Lifecycle.State newestObserverState = this.mObserverMap.newest().getValue().mState;
        return eldestObserverState == newestObserverState && this.mState == newestObserverState;
    }

    private Lifecycle.State calculateTargetState(LifecycleObserver observer) {
        Map.Entry<LifecycleObserver, ObserverWithState> previous = this.mObserverMap.ceil(observer);
        return min(min(this.mState, previous != null ? previous.getValue().mState : null), !this.mParentStates.isEmpty() ? this.mParentStates.get(this.mParentStates.size() - 1) : null);
    }

    public void addObserver(@NonNull LifecycleObserver lifecycleObserver) {
        ObserverWithState observerWithState;
        LifecycleOwner lifecycleOwner;
        LifecycleObserver observer = lifecycleObserver;
        new ObserverWithState(observer, this.mState == Lifecycle.State.DESTROYED ? Lifecycle.State.DESTROYED : Lifecycle.State.INITIALIZED);
        ObserverWithState statefulObserver = observerWithState;
        if (this.mObserverMap.putIfAbsent(observer, statefulObserver) == null && (lifecycleOwner = (LifecycleOwner) this.mLifecycleOwner.get()) != null) {
            boolean isReentrance = this.mAddingObserverCounter != 0 || this.mHandlingEvent;
            this.mAddingObserverCounter++;
            for (Lifecycle.State targetState = calculateTargetState(observer); statefulObserver.mState.compareTo(targetState) < 0 && this.mObserverMap.contains(observer); targetState = calculateTargetState(observer)) {
                pushParentState(statefulObserver.mState);
                statefulObserver.dispatchEvent(lifecycleOwner, upEvent(statefulObserver.mState));
                popParentState();
            }
            if (!isReentrance) {
                sync();
            }
            this.mAddingObserverCounter--;
        }
    }

    private void popParentState() {
        Lifecycle.State remove = this.mParentStates.remove(this.mParentStates.size() - 1);
    }

    private void pushParentState(Lifecycle.State state) {
        boolean add = this.mParentStates.add(state);
    }

    public void removeObserver(@NonNull LifecycleObserver observer) {
        ObserverWithState remove = this.mObserverMap.remove(observer);
    }

    public int getObserverCount() {
        return this.mObserverMap.size();
    }

    @NonNull
    public Lifecycle.State getCurrentState() {
        return this.mState;
    }

    static Lifecycle.State getStateAfter(Lifecycle.Event event) {
        Throwable th;
        StringBuilder sb;
        Lifecycle.Event event2 = event;
        switch (event2) {
            case ON_CREATE:
            case ON_STOP:
                return Lifecycle.State.CREATED;
            case ON_START:
            case ON_PAUSE:
                return Lifecycle.State.STARTED;
            case ON_RESUME:
                return Lifecycle.State.RESUMED;
            case ON_DESTROY:
                return Lifecycle.State.DESTROYED;
            default:
                Throwable th2 = th;
                new StringBuilder();
                new IllegalArgumentException(sb.append("Unexpected event value ").append(event2).toString());
                throw th2;
        }
    }

    private static Lifecycle.Event downEvent(Lifecycle.State state) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        StringBuilder sb;
        Lifecycle.State state2 = state;
        switch (state2) {
            case INITIALIZED:
                Throwable th4 = th2;
                new IllegalArgumentException();
                throw th4;
            case CREATED:
                return Lifecycle.Event.ON_DESTROY;
            case STARTED:
                return Lifecycle.Event.ON_STOP;
            case RESUMED:
                return Lifecycle.Event.ON_PAUSE;
            case DESTROYED:
                Throwable th5 = th;
                new IllegalArgumentException();
                throw th5;
            default:
                Throwable th6 = th3;
                new StringBuilder();
                new IllegalArgumentException(sb.append("Unexpected state value ").append(state2).toString());
                throw th6;
        }
    }

    private static Lifecycle.Event upEvent(Lifecycle.State state) {
        Throwable th;
        Throwable th2;
        StringBuilder sb;
        Lifecycle.State state2 = state;
        switch (state2) {
            case INITIALIZED:
            case DESTROYED:
                return Lifecycle.Event.ON_CREATE;
            case CREATED:
                return Lifecycle.Event.ON_START;
            case STARTED:
                return Lifecycle.Event.ON_RESUME;
            case RESUMED:
                Throwable th3 = th;
                new IllegalArgumentException();
                throw th3;
            default:
                Throwable th4 = th2;
                new StringBuilder();
                new IllegalArgumentException(sb.append("Unexpected state value ").append(state2).toString());
                throw th4;
        }
    }

    private void forwardPass(LifecycleOwner lifecycleOwner) {
        LifecycleOwner lifecycleOwner2 = lifecycleOwner;
        Iterator<Map.Entry<LifecycleObserver, ObserverWithState>> ascendingIterator = this.mObserverMap.iteratorWithAdditions();
        while (ascendingIterator.hasNext() && !this.mNewEventOccurred) {
            Map.Entry<LifecycleObserver, ObserverWithState> entry = ascendingIterator.next();
            ObserverWithState observer = entry.getValue();
            while (observer.mState.compareTo(this.mState) < 0 && !this.mNewEventOccurred && this.mObserverMap.contains(entry.getKey())) {
                pushParentState(observer.mState);
                observer.dispatchEvent(lifecycleOwner2, upEvent(observer.mState));
                popParentState();
            }
        }
    }

    private void backwardPass(LifecycleOwner lifecycleOwner) {
        LifecycleOwner lifecycleOwner2 = lifecycleOwner;
        Iterator<Map.Entry<LifecycleObserver, ObserverWithState>> descendingIterator = this.mObserverMap.descendingIterator();
        while (descendingIterator.hasNext() && !this.mNewEventOccurred) {
            Map.Entry<LifecycleObserver, ObserverWithState> entry = descendingIterator.next();
            ObserverWithState observer = entry.getValue();
            while (observer.mState.compareTo(this.mState) > 0 && !this.mNewEventOccurred && this.mObserverMap.contains(entry.getKey())) {
                Lifecycle.Event event = downEvent(observer.mState);
                pushParentState(getStateAfter(event));
                observer.dispatchEvent(lifecycleOwner2, event);
                popParentState();
            }
        }
    }

    private void sync() {
        LifecycleOwner lifecycleOwner = (LifecycleOwner) this.mLifecycleOwner.get();
        if (lifecycleOwner == null) {
            int w = Log.w(LOG_TAG, "LifecycleOwner is garbage collected, you shouldn't try dispatch new events from it.");
            return;
        }
        while (!isSynced()) {
            this.mNewEventOccurred = false;
            if (this.mState.compareTo(this.mObserverMap.eldest().getValue().mState) < 0) {
                backwardPass(lifecycleOwner);
            }
            Map.Entry<LifecycleObserver, ObserverWithState> newest = this.mObserverMap.newest();
            if (!this.mNewEventOccurred && newest != null && this.mState.compareTo(newest.getValue().mState) > 0) {
                forwardPass(lifecycleOwner);
            }
        }
        this.mNewEventOccurred = false;
    }

    static Lifecycle.State min(@NonNull Lifecycle.State state, @Nullable Lifecycle.State state2) {
        Lifecycle.State state1 = state;
        Lifecycle.State state22 = state2;
        return (state22 == null || state22.compareTo(state1) >= 0) ? state1 : state22;
    }

    static class ObserverWithState {
        GenericLifecycleObserver mLifecycleObserver;
        Lifecycle.State mState;

        ObserverWithState(LifecycleObserver observer, Lifecycle.State initialState) {
            this.mLifecycleObserver = Lifecycling.getCallback(observer);
            this.mState = initialState;
        }

        /* access modifiers changed from: package-private */
        public void dispatchEvent(LifecycleOwner owner, Lifecycle.Event event) {
            Lifecycle.Event event2 = event;
            Lifecycle.State newState = LifecycleRegistry.getStateAfter(event2);
            this.mState = LifecycleRegistry.min(this.mState, newState);
            this.mLifecycleObserver.onStateChanged(owner, event2);
            this.mState = newState;
        }
    }
}

package com.firebase.client.core;

import com.firebase.client.FirebaseError;
import com.firebase.client.annotations.NotNull;
import com.firebase.client.core.view.Change;
import com.firebase.client.core.view.DataEvent;
import com.firebase.client.core.view.Event;
import com.firebase.client.core.view.QuerySpec;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class EventRegistration {
    static final /* synthetic */ boolean $assertionsDisabled = (!EventRegistration.class.desiredAssertionStatus());
    private boolean isUserInitiated = false;
    private EventRegistrationZombieListener listener;
    private AtomicBoolean zombied;

    public abstract EventRegistration clone(QuerySpec querySpec);

    public abstract DataEvent createEvent(Change change, QuerySpec querySpec);

    public abstract void fireCancelEvent(FirebaseError firebaseError);

    public abstract void fireEvent(DataEvent dataEvent);

    @NotNull
    public abstract QuerySpec getQuerySpec();

    public abstract boolean isSameListener(EventRegistration eventRegistration);

    public abstract boolean respondsTo(Event.EventType eventType);

    public EventRegistration() {
        AtomicBoolean atomicBoolean;
        new AtomicBoolean(false);
        this.zombied = atomicBoolean;
    }

    public void zombify() {
        if (this.zombied.compareAndSet(false, true) && this.listener != null) {
            this.listener.onZombied(this);
            this.listener = null;
        }
    }

    public boolean isZombied() {
        return this.zombied.get();
    }

    public void setOnZombied(EventRegistrationZombieListener eventRegistrationZombieListener) {
        Throwable th;
        Throwable th2;
        EventRegistrationZombieListener listener2 = eventRegistrationZombieListener;
        if (!$assertionsDisabled && isZombied()) {
            Throwable th3 = th2;
            new AssertionError();
            throw th3;
        } else if ($assertionsDisabled || this.listener == null) {
            this.listener = listener2;
        } else {
            Throwable th4 = th;
            new AssertionError();
            throw th4;
        }
    }

    public boolean isUserInitiated() {
        return this.isUserInitiated;
    }

    public void setIsUserInitiated(boolean isUserInitiated2) {
        boolean z = isUserInitiated2;
        this.isUserInitiated = z;
    }

    /* access modifiers changed from: package-private */
    public Repo getRepo() {
        return null;
    }
}

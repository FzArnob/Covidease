package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.zzv;

final class zzat extends zzv {
    private final ListenerHolder<LocationCallback> zzda;

    zzat(ListenerHolder<LocationCallback> listenerHolder) {
        this.zzda = listenerHolder;
    }

    public final void onLocationAvailability(LocationAvailability locationAvailability) {
        ListenerHolder.Notifier notifier;
        new zzav(this, locationAvailability);
        this.zzda.notifyListener(notifier);
    }

    public final void onLocationResult(LocationResult locationResult) {
        ListenerHolder.Notifier notifier;
        new zzau(this, locationResult);
        this.zzda.notifyListener(notifier);
    }

    public final synchronized void release() {
        synchronized (this) {
            this.zzda.clear();
        }
    }
}

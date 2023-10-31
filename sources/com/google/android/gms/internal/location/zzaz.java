package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.location.Location;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.internal.IStatusCallback;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.StatusCallback;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.location.ActivityTransitionRequest;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.zzal;
import javax.annotation.Nullable;

public final class zzaz extends zzk {
    private final zzas zzde;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzaz(android.content.Context r14, android.os.Looper r15, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks r16, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener r17, java.lang.String r18) {
        /*
            r13 = this;
            r0 = r13
            r1 = r14
            r2 = r15
            r3 = r16
            r4 = r17
            r5 = r18
            r6 = r0
            r7 = r1
            r8 = r2
            r9 = r3
            r10 = r4
            r11 = r5
            r12 = r1
            com.google.android.gms.common.internal.ClientSettings r12 = com.google.android.gms.common.internal.ClientSettings.createDefault(r12)
            r6.<init>(r7, r8, r9, r10, r11, r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.location.zzaz.<init>(android.content.Context, android.os.Looper, com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks, com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener, java.lang.String):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzaz(android.content.Context r16, android.os.Looper r17, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks r18, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener r19, java.lang.String r20, @javax.annotation.Nullable com.google.android.gms.common.internal.ClientSettings r21) {
        /*
            r15 = this;
            r0 = r15
            r1 = r16
            r2 = r17
            r3 = r18
            r4 = r19
            r5 = r20
            r6 = r21
            r7 = r0
            r8 = r1
            r9 = r2
            r10 = r3
            r11 = r4
            r12 = r5
            r13 = r6
            r7.<init>(r8, r9, r10, r11, r12, r13)
            r7 = r0
            com.google.android.gms.internal.location.zzas r8 = new com.google.android.gms.internal.location.zzas
            r14 = r8
            r8 = r14
            r9 = r14
            r10 = r1
            r11 = r0
            com.google.android.gms.internal.location.zzbj r11 = r11.zzcb
            r9.<init>(r10, r11)
            r7.zzde = r8
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.location.zzaz.<init>(android.content.Context, android.os.Looper, com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks, com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener, java.lang.String, com.google.android.gms.common.internal.ClientSettings):void");
    }

    public final void disconnect() {
        zzas zzas = this.zzde;
        zzas zzas2 = zzas;
        synchronized (zzas) {
            try {
                if (isConnected()) {
                    this.zzde.removeAllListeners();
                    this.zzde.zzb();
                }
            } catch (Exception e) {
                int e2 = Log.e("LocationClientImpl", "Client disconnected before listeners could be cleaned up", e);
            } catch (Throwable th) {
                Throwable th2 = th;
                zzas zzas3 = zzas2;
                throw th2;
            }
            super.disconnect();
        }
    }

    public final Location getLastLocation() throws RemoteException {
        return this.zzde.getLastLocation();
    }

    public final LocationAvailability zza() throws RemoteException {
        return this.zzde.zza();
    }

    public final void zza(long j, PendingIntent pendingIntent) throws RemoteException {
        long j2 = j;
        PendingIntent pendingIntent2 = pendingIntent;
        checkConnected();
        Object checkNotNull = Preconditions.checkNotNull(pendingIntent2);
        Preconditions.checkArgument(j2 >= 0, "detectionIntervalMillis must be >= 0");
        ((zzao) getService()).zza(j2, true, pendingIntent2);
    }

    public final void zza(PendingIntent pendingIntent, BaseImplementation.ResultHolder<Status> resultHolder) throws RemoteException {
        IStatusCallback iStatusCallback;
        BaseImplementation.ResultHolder<Status> resultHolder2 = resultHolder;
        checkConnected();
        Object checkNotNull = Preconditions.checkNotNull(resultHolder2, "ResultHolder not provided.");
        new StatusCallback(resultHolder2);
        ((zzao) getService()).zza(pendingIntent, iStatusCallback);
    }

    public final void zza(PendingIntent pendingIntent, zzaj zzaj) throws RemoteException {
        this.zzde.zza(pendingIntent, zzaj);
    }

    public final void zza(Location location) throws RemoteException {
        this.zzde.zza(location);
    }

    public final void zza(ListenerHolder.ListenerKey<LocationListener> listenerKey, zzaj zzaj) throws RemoteException {
        this.zzde.zza(listenerKey, zzaj);
    }

    public final void zza(zzaj zzaj) throws RemoteException {
        this.zzde.zza(zzaj);
    }

    /* JADX INFO: finally extract failed */
    public final void zza(zzbd zzbd, ListenerHolder<LocationCallback> listenerHolder, zzaj zzaj) throws RemoteException {
        zzbd zzbd2 = zzbd;
        ListenerHolder<LocationCallback> listenerHolder2 = listenerHolder;
        zzaj zzaj2 = zzaj;
        zzas zzas = this.zzde;
        zzas zzas2 = zzas;
        synchronized (zzas) {
            try {
                this.zzde.zza(zzbd2, listenerHolder2, zzaj2);
            } catch (Throwable th) {
                Throwable th2 = th;
                zzas zzas3 = zzas2;
                throw th2;
            }
        }
    }

    public final void zza(ActivityTransitionRequest activityTransitionRequest, PendingIntent pendingIntent, BaseImplementation.ResultHolder<Status> resultHolder) throws RemoteException {
        IStatusCallback iStatusCallback;
        BaseImplementation.ResultHolder<Status> resultHolder2 = resultHolder;
        checkConnected();
        Object checkNotNull = Preconditions.checkNotNull(resultHolder2, "ResultHolder not provided.");
        new StatusCallback(resultHolder2);
        ((zzao) getService()).zza(activityTransitionRequest, pendingIntent, iStatusCallback);
    }

    public final void zza(GeofencingRequest geofencingRequest, PendingIntent pendingIntent, BaseImplementation.ResultHolder<Status> resultHolder) throws RemoteException {
        zzam zzam;
        GeofencingRequest geofencingRequest2 = geofencingRequest;
        PendingIntent pendingIntent2 = pendingIntent;
        BaseImplementation.ResultHolder<Status> resultHolder2 = resultHolder;
        checkConnected();
        Object checkNotNull = Preconditions.checkNotNull(geofencingRequest2, "geofencingRequest can't be null.");
        Object checkNotNull2 = Preconditions.checkNotNull(pendingIntent2, "PendingIntent must be specified.");
        Object checkNotNull3 = Preconditions.checkNotNull(resultHolder2, "ResultHolder not provided.");
        new zzba(resultHolder2);
        ((zzao) getService()).zza(geofencingRequest2, pendingIntent2, zzam);
    }

    public final void zza(LocationRequest locationRequest, PendingIntent pendingIntent, zzaj zzaj) throws RemoteException {
        this.zzde.zza(locationRequest, pendingIntent, zzaj);
    }

    /* JADX INFO: finally extract failed */
    public final void zza(LocationRequest locationRequest, ListenerHolder<LocationListener> listenerHolder, zzaj zzaj) throws RemoteException {
        LocationRequest locationRequest2 = locationRequest;
        ListenerHolder<LocationListener> listenerHolder2 = listenerHolder;
        zzaj zzaj2 = zzaj;
        zzas zzas = this.zzde;
        zzas zzas2 = zzas;
        synchronized (zzas) {
            try {
                this.zzde.zza(locationRequest2, listenerHolder2, zzaj2);
            } catch (Throwable th) {
                Throwable th2 = th;
                zzas zzas3 = zzas2;
                throw th2;
            }
        }
    }

    public final void zza(LocationSettingsRequest locationSettingsRequest, BaseImplementation.ResultHolder<LocationSettingsResult> resultHolder, @Nullable String str) throws RemoteException {
        zzaq zzaq;
        LocationSettingsRequest locationSettingsRequest2 = locationSettingsRequest;
        BaseImplementation.ResultHolder<LocationSettingsResult> resultHolder2 = resultHolder;
        String str2 = str;
        checkConnected();
        Preconditions.checkArgument(locationSettingsRequest2 != null, "locationSettingsRequest can't be null nor empty.");
        Preconditions.checkArgument(resultHolder2 != null, "listener can't be null.");
        new zzbc(resultHolder2);
        ((zzao) getService()).zza(locationSettingsRequest2, zzaq, str2);
    }

    public final void zza(zzal zzal, BaseImplementation.ResultHolder<Status> resultHolder) throws RemoteException {
        zzam zzam;
        zzal zzal2 = zzal;
        BaseImplementation.ResultHolder<Status> resultHolder2 = resultHolder;
        checkConnected();
        Object checkNotNull = Preconditions.checkNotNull(zzal2, "removeGeofencingRequest can't be null.");
        Object checkNotNull2 = Preconditions.checkNotNull(resultHolder2, "ResultHolder not provided.");
        new zzbb(resultHolder2);
        ((zzao) getService()).zza(zzal2, zzam);
    }

    public final void zza(boolean z) throws RemoteException {
        this.zzde.zza(z);
    }

    public final void zzb(PendingIntent pendingIntent) throws RemoteException {
        PendingIntent pendingIntent2 = pendingIntent;
        checkConnected();
        Object checkNotNull = Preconditions.checkNotNull(pendingIntent2);
        ((zzao) getService()).zzb(pendingIntent2);
    }

    public final void zzb(ListenerHolder.ListenerKey<LocationCallback> listenerKey, zzaj zzaj) throws RemoteException {
        this.zzde.zzb(listenerKey, zzaj);
    }
}

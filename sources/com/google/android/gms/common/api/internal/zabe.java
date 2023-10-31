package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.zad;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import javax.annotation.concurrent.GuardedBy;

public final class zabe implements zabs, zar {
    private final Context mContext;
    private final Api.AbstractClientBuilder<? extends zad, SignInOptions> zace;
    final zaaw zaee;
    /* access modifiers changed from: private */
    public final Lock zaeo;
    private final ClientSettings zaet;
    private final Map<Api<?>, Boolean> zaew;
    private final GoogleApiAvailabilityLight zaey;
    final Map<Api.AnyClientKey<?>, Api.Client> zagz;
    private final Condition zahn;
    private final zabg zaho;
    final Map<Api.AnyClientKey<?>, ConnectionResult> zahp;
    /* access modifiers changed from: private */
    public volatile zabd zahq;
    private ConnectionResult zahr = null;
    int zahs;
    final zabt zaht;

    public zabe(Context context, zaaw zaaw, Lock lock, Looper looper, GoogleApiAvailabilityLight googleApiAvailabilityLight, Map<Api.AnyClientKey<?>, Api.Client> map, ClientSettings clientSettings, Map<Api<?>, Boolean> map2, Api.AbstractClientBuilder<? extends zad, SignInOptions> abstractClientBuilder, ArrayList<zaq> arrayList, zabt zabt) {
        Map<Api.AnyClientKey<?>, ConnectionResult> map3;
        zabg zabg;
        zabd zabd;
        Lock lock2 = lock;
        Looper looper2 = looper;
        new HashMap();
        this.zahp = map3;
        this.mContext = context;
        this.zaeo = lock2;
        this.zaey = googleApiAvailabilityLight;
        this.zagz = map;
        this.zaet = clientSettings;
        this.zaew = map2;
        this.zace = abstractClientBuilder;
        this.zaee = zaaw;
        this.zaht = zabt;
        ArrayList arrayList2 = arrayList;
        ArrayList arrayList3 = arrayList2;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            i++;
            ((zaq) arrayList3.get(i)).zaa(this);
        }
        new zabg(this, looper2);
        this.zaho = zabg;
        this.zahn = lock2.newCondition();
        new zaav(this);
        this.zahq = zabd;
    }

    @GuardedBy("mLock")
    public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(@NonNull T t) {
        T t2 = t;
        t2.zau();
        return this.zahq.enqueue(t2);
    }

    @GuardedBy("mLock")
    public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(@NonNull T t) {
        T t2 = t;
        t2.zau();
        return this.zahq.execute(t2);
    }

    @GuardedBy("mLock")
    public final void connect() {
        this.zahq.connect();
    }

    @GuardedBy("mLock")
    public final ConnectionResult blockingConnect() {
        ConnectionResult connectionResult;
        ConnectionResult connectionResult2;
        connect();
        while (isConnecting()) {
            try {
                this.zahn.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                new ConnectionResult(15, (PendingIntent) null);
                return connectionResult2;
            }
        }
        if (isConnected()) {
            return ConnectionResult.RESULT_SUCCESS;
        }
        if (this.zahr != null) {
            return this.zahr;
        }
        new ConnectionResult(13, (PendingIntent) null);
        return connectionResult;
    }

    @GuardedBy("mLock")
    public final ConnectionResult blockingConnect(long j, TimeUnit timeUnit) {
        ConnectionResult connectionResult;
        ConnectionResult connectionResult2;
        ConnectionResult connectionResult3;
        connect();
        long nanos = timeUnit.toNanos(j);
        while (true) {
            long j2 = nanos;
            if (isConnecting()) {
                if (j2 <= 0) {
                    try {
                        disconnect();
                        new ConnectionResult(14, (PendingIntent) null);
                        return connectionResult3;
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        new ConnectionResult(15, (PendingIntent) null);
                        return connectionResult2;
                    }
                } else {
                    nanos = this.zahn.awaitNanos(j2);
                }
            } else if (isConnected()) {
                return ConnectionResult.RESULT_SUCCESS;
            } else {
                if (this.zahr != null) {
                    return this.zahr;
                }
                new ConnectionResult(13, (PendingIntent) null);
                return connectionResult;
            }
        }
    }

    @GuardedBy("mLock")
    public final void disconnect() {
        if (this.zahq.disconnect()) {
            this.zahp.clear();
        }
    }

    @Nullable
    @GuardedBy("mLock")
    public final ConnectionResult getConnectionResult(@NonNull Api<?> api) {
        Api.AnyClientKey<?> clientKey = api.getClientKey();
        if (this.zagz.containsKey(clientKey)) {
            if (this.zagz.get(clientKey).isConnected()) {
                return ConnectionResult.RESULT_SUCCESS;
            }
            if (this.zahp.containsKey(clientKey)) {
                return this.zahp.get(clientKey);
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final void zaaz() {
        zabd zabd;
        this.zaeo.lock();
        try {
            new zaak(this, this.zaet, this.zaew, this.zaey, this.zace, this.zaeo, this.mContext);
            this.zahq = zabd;
            this.zahq.begin();
            this.zahn.signalAll();
            this.zaeo.unlock();
        } catch (Throwable th) {
            Throwable th2 = th;
            this.zaeo.unlock();
            throw th2;
        }
    }

    /* access modifiers changed from: package-private */
    public final void zaba() {
        zabd zabd;
        this.zaeo.lock();
        try {
            boolean zaaw = this.zaee.zaaw();
            new zaah(this);
            this.zahq = zabd;
            this.zahq.begin();
            this.zahn.signalAll();
            this.zaeo.unlock();
        } catch (Throwable th) {
            Throwable th2 = th;
            this.zaeo.unlock();
            throw th2;
        }
    }

    /* access modifiers changed from: package-private */
    public final void zaf(ConnectionResult connectionResult) {
        zabd zabd;
        this.zaeo.lock();
        try {
            this.zahr = connectionResult;
            new zaav(this);
            this.zahq = zabd;
            this.zahq.begin();
            this.zahn.signalAll();
            this.zaeo.unlock();
        } catch (Throwable th) {
            Throwable th2 = th;
            this.zaeo.unlock();
            throw th2;
        }
    }

    public final boolean isConnected() {
        return this.zahq instanceof zaah;
    }

    public final boolean isConnecting() {
        return this.zahq instanceof zaak;
    }

    public final boolean maybeSignIn(SignInConnectionListener signInConnectionListener) {
        SignInConnectionListener signInConnectionListener2 = signInConnectionListener;
        return false;
    }

    public final void maybeSignOut() {
    }

    @GuardedBy("mLock")
    public final void zaw() {
        if (isConnected()) {
            ((zaah) this.zahq).zaam();
        }
    }

    public final void zaa(@NonNull ConnectionResult connectionResult, @NonNull Api<?> api, boolean z) {
        ConnectionResult connectionResult2 = connectionResult;
        Api<?> api2 = api;
        boolean z2 = z;
        this.zaeo.lock();
        try {
            this.zahq.zaa(connectionResult2, api2, z2);
            this.zaeo.unlock();
        } catch (Throwable th) {
            Throwable th2 = th;
            this.zaeo.unlock();
            throw th2;
        }
    }

    public final void onConnected(@Nullable Bundle bundle) {
        Bundle bundle2 = bundle;
        this.zaeo.lock();
        try {
            this.zahq.onConnected(bundle2);
            this.zaeo.unlock();
        } catch (Throwable th) {
            Throwable th2 = th;
            this.zaeo.unlock();
            throw th2;
        }
    }

    public final void onConnectionSuspended(int i) {
        int i2 = i;
        this.zaeo.lock();
        try {
            this.zahq.onConnectionSuspended(i2);
            this.zaeo.unlock();
        } catch (Throwable th) {
            Throwable th2 = th;
            this.zaeo.unlock();
            throw th2;
        }
    }

    /* access modifiers changed from: package-private */
    public final void zaa(zabf zabf) {
        boolean sendMessage = this.zaho.sendMessage(this.zaho.obtainMessage(1, zabf));
    }

    /* access modifiers changed from: package-private */
    public final void zab(RuntimeException runtimeException) {
        boolean sendMessage = this.zaho.sendMessage(this.zaho.obtainMessage(2, runtimeException));
    }

    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        String str2 = str;
        FileDescriptor fileDescriptor2 = fileDescriptor;
        PrintWriter printWriter2 = printWriter;
        String[] strArr2 = strArr;
        String concat = String.valueOf(str2).concat("  ");
        printWriter2.append(str2).append("mState=").println(this.zahq);
        for (Api next : this.zaew.keySet()) {
            printWriter2.append(str2).append(next.getName()).println(":");
            this.zagz.get(next.getClientKey()).dump(concat, fileDescriptor2, printWriter2, strArr2);
        }
    }
}

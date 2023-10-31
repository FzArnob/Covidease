package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.p000v4.app.FragmentActivity;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClientEventManager;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.service.Common;
import com.google.android.gms.common.util.ClientLibraryUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.zad;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import javax.annotation.concurrent.GuardedBy;

public final class zaaw extends GoogleApiClient implements zabt {
    /* access modifiers changed from: private */
    public final Context mContext;
    private final Looper zabj;
    private final int zacb;
    private final GoogleApiAvailability zacd;
    private final Api.AbstractClientBuilder<? extends zad, SignInOptions> zace;
    private boolean zach;
    private final Lock zaeo;
    private final ClientSettings zaet;
    private final Map<Api<?>, Boolean> zaew;
    @VisibleForTesting
    final Queue<BaseImplementation.ApiMethodImpl<?, ?>> zafc;
    private final GmsClientEventManager zags;
    private zabs zagt = null;
    private volatile boolean zagu;
    private long zagv;
    private long zagw;
    private final zabb zagx;
    @VisibleForTesting
    private zabq zagy;
    final Map<Api.AnyClientKey<?>, Api.Client> zagz;
    Set<Scope> zaha;
    private final ListenerHolders zahb;
    private final ArrayList<zaq> zahc;
    private Integer zahd;
    Set<zacm> zahe;
    final zacp zahf;
    private final GmsClientEventManager.GmsClientEventState zahg;

    public zaaw(Context context, Lock lock, Looper looper, ClientSettings clientSettings, GoogleApiAvailability googleApiAvailability, Api.AbstractClientBuilder<? extends zad, SignInOptions> abstractClientBuilder, Map<Api<?>, Boolean> map, List<GoogleApiClient.ConnectionCallbacks> list, List<GoogleApiClient.OnConnectionFailedListener> list2, Map<Api.AnyClientKey<?>, Api.Client> map2, int i, int i2, ArrayList<zaq> arrayList, boolean z) {
        Queue<BaseImplementation.ApiMethodImpl<?, ?>> queue;
        Set<Scope> set;
        ListenerHolders listenerHolders;
        GmsClientEventManager.GmsClientEventState gmsClientEventState;
        GmsClientEventManager gmsClientEventManager;
        zabb zabb;
        zacp zacp;
        Context context2 = context;
        Lock lock2 = lock;
        Looper looper2 = looper;
        ClientSettings clientSettings2 = clientSettings;
        GoogleApiAvailability googleApiAvailability2 = googleApiAvailability;
        Api.AbstractClientBuilder<? extends zad, SignInOptions> abstractClientBuilder2 = abstractClientBuilder;
        Map<Api<?>, Boolean> map3 = map;
        List<GoogleApiClient.ConnectionCallbacks> list3 = list;
        List<GoogleApiClient.OnConnectionFailedListener> list4 = list2;
        Map<Api.AnyClientKey<?>, Api.Client> map4 = map2;
        int i3 = i;
        int i4 = i2;
        ArrayList<zaq> arrayList2 = arrayList;
        boolean z2 = z;
        new LinkedList();
        this.zafc = queue;
        this.zagv = ClientLibraryUtils.isPackageSide() ? 10000 : 120000;
        this.zagw = 5000;
        new HashSet();
        this.zaha = set;
        new ListenerHolders();
        this.zahb = listenerHolders;
        this.zahd = null;
        this.zahe = null;
        new zaax(this);
        this.zahg = gmsClientEventState;
        this.mContext = context2;
        this.zaeo = lock2;
        this.zach = false;
        new GmsClientEventManager(looper2, this.zahg);
        this.zags = gmsClientEventManager;
        this.zabj = looper2;
        new zabb(this, looper2);
        this.zagx = zabb;
        this.zacd = googleApiAvailability2;
        this.zacb = i3;
        if (this.zacb >= 0) {
            this.zahd = Integer.valueOf(i4);
        }
        this.zaew = map3;
        this.zagz = map4;
        this.zahc = arrayList2;
        new zacp(this.zagz);
        this.zahf = zacp;
        for (GoogleApiClient.ConnectionCallbacks registerConnectionCallbacks : list3) {
            this.zags.registerConnectionCallbacks(registerConnectionCallbacks);
        }
        for (GoogleApiClient.OnConnectionFailedListener registerConnectionFailedListener : list4) {
            this.zags.registerConnectionFailedListener(registerConnectionFailedListener);
        }
        this.zaet = clientSettings2;
        this.zace = abstractClientBuilder2;
    }

    /* JADX INFO: finally extract failed */
    public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(@NonNull T t) {
        StringBuilder sb;
        T t2 = t;
        Preconditions.checkArgument(t2.getClientKey() != null, "This task can not be enqueued (it's probably a Batch or malformed)");
        boolean containsKey = this.zagz.containsKey(t2.getClientKey());
        String name = t2.getApi() != null ? t2.getApi().getName() : "the API";
        new StringBuilder(65 + String.valueOf(name).length());
        Preconditions.checkArgument(containsKey, sb.append("GoogleApiClient is not configured to use ").append(name).append(" required for this call.").toString());
        this.zaeo.lock();
        try {
            if (this.zagt == null) {
                boolean add = this.zafc.add(t2);
                T t3 = t2;
                this.zaeo.unlock();
                return t3;
            }
            T enqueue = this.zagt.enqueue(t2);
            this.zaeo.unlock();
            return enqueue;
        } catch (Throwable th) {
            Throwable th2 = th;
            this.zaeo.unlock();
            throw th2;
        }
    }

    /* JADX INFO: finally extract failed */
    public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(@NonNull T t) {
        StringBuilder sb;
        Throwable th;
        T t2 = t;
        Preconditions.checkArgument(t2.getClientKey() != null, "This task can not be executed (it's probably a Batch or malformed)");
        boolean containsKey = this.zagz.containsKey(t2.getClientKey());
        String name = t2.getApi() != null ? t2.getApi().getName() : "the API";
        new StringBuilder(65 + String.valueOf(name).length());
        Preconditions.checkArgument(containsKey, sb.append("GoogleApiClient is not configured to use ").append(name).append(" required for this call.").toString());
        this.zaeo.lock();
        try {
            if (this.zagt == null) {
                Throwable th2 = th;
                new IllegalStateException("GoogleApiClient is not connected yet.");
                throw th2;
            } else if (this.zagu) {
                boolean add = this.zafc.add(t2);
                while (!this.zafc.isEmpty()) {
                    BaseImplementation.ApiMethodImpl remove = this.zafc.remove();
                    this.zahf.zab(remove);
                    remove.setFailedResult(Status.RESULT_INTERNAL_ERROR);
                }
                T t3 = t2;
                this.zaeo.unlock();
                return t3;
            } else {
                T execute = this.zagt.execute(t2);
                this.zaeo.unlock();
                return execute;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            this.zaeo.unlock();
            throw th4;
        }
    }

    /* JADX INFO: finally extract failed */
    public final <L> ListenerHolder<L> registerListener(@NonNull L l) {
        L l2 = l;
        this.zaeo.lock();
        try {
            L l3 = l2;
            L l4 = l3;
            ListenerHolder<L> zaa = this.zahb.zaa(l3, this.zabj, "NO_TYPE");
            this.zaeo.unlock();
            return zaa;
        } catch (Throwable th) {
            Throwable th2 = th;
            this.zaeo.unlock();
            throw th2;
        }
    }

    @NonNull
    public final <C extends Api.Client> C getClient(@NonNull Api.AnyClientKey<C> anyClientKey) {
        C c = (Api.Client) this.zagz.get(anyClientKey);
        Object checkNotNull = Preconditions.checkNotNull(c, "Appropriate Api was not requested.");
        return c;
    }

    public final boolean hasApi(@NonNull Api<?> api) {
        return this.zagz.containsKey(api.getClientKey());
    }

    public final boolean hasConnectedApi(@NonNull Api<?> api) {
        Api<?> api2 = api;
        if (!isConnected()) {
            return false;
        }
        Api.Client client = this.zagz.get(api2.getClientKey());
        return client != null && client.isConnected();
    }

    /* JADX INFO: finally extract failed */
    @NonNull
    public final ConnectionResult getConnectionResult(@NonNull Api<?> api) {
        Throwable th;
        Throwable th2;
        ConnectionResult connectionResult;
        Throwable th3;
        Api<?> api2 = api;
        this.zaeo.lock();
        try {
            if (!isConnected() && !this.zagu) {
                Throwable th4 = th3;
                new IllegalStateException("Cannot invoke getConnectionResult unless GoogleApiClient is connected");
                throw th4;
            } else if (this.zagz.containsKey(api2.getClientKey())) {
                ConnectionResult connectionResult2 = this.zagt.getConnectionResult(api2);
                ConnectionResult connectionResult3 = connectionResult2;
                if (connectionResult2 != null) {
                    ConnectionResult connectionResult4 = connectionResult3;
                    this.zaeo.unlock();
                    return connectionResult4;
                } else if (this.zagu) {
                    ConnectionResult connectionResult5 = ConnectionResult.RESULT_SUCCESS;
                    this.zaeo.unlock();
                    return connectionResult5;
                } else {
                    int w = Log.w("GoogleApiClientImpl", zaay());
                    new Exception();
                    int wtf = Log.wtf("GoogleApiClientImpl", String.valueOf(api2.getName()).concat(" requested in getConnectionResult is not connected but is not present in the failed  connections map"), th2);
                    ConnectionResult connectionResult6 = connectionResult;
                    new ConnectionResult(8, (PendingIntent) null);
                    ConnectionResult connectionResult7 = connectionResult6;
                    this.zaeo.unlock();
                    return connectionResult7;
                }
            } else {
                Throwable th5 = th;
                new IllegalArgumentException(String.valueOf(api2.getName()).concat(" was never registered with GoogleApiClient"));
                throw th5;
            }
        } catch (Throwable th6) {
            Throwable th7 = th6;
            this.zaeo.unlock();
            throw th7;
        }
    }

    public final void connect() {
        Throwable th;
        this.zaeo.lock();
        try {
            if (this.zacb >= 0) {
                Preconditions.checkState(this.zahd != null, "Sign-in mode should have been set explicitly by auto-manage.");
            } else if (this.zahd == null) {
                this.zahd = Integer.valueOf(zaa(this.zagz.values(), false));
            } else if (this.zahd.intValue() == 2) {
                Throwable th2 = th;
                new IllegalStateException("Cannot call connect() when SignInMode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
                throw th2;
            }
            connect(this.zahd.intValue());
            this.zaeo.unlock();
        } catch (Throwable th3) {
            Throwable th4 = th3;
            this.zaeo.unlock();
            throw th4;
        }
    }

    public final void connect(int i) {
        StringBuilder sb;
        int i2 = i;
        this.zaeo.lock();
        boolean z = i2 == 3 || i2 == 1 || i2 == 2;
        try {
            new StringBuilder(33);
            Preconditions.checkArgument(z, sb.append("Illegal sign-in mode: ").append(i2).toString());
            zae(i2);
            zaau();
            this.zaeo.unlock();
        } catch (Throwable th) {
            Throwable th2 = th;
            this.zaeo.unlock();
            throw th2;
        }
    }

    /* JADX INFO: finally extract failed */
    public final ConnectionResult blockingConnect() {
        Throwable th;
        Preconditions.checkState(Looper.myLooper() != Looper.getMainLooper(), "blockingConnect must not be called on the UI thread");
        this.zaeo.lock();
        try {
            if (this.zacb >= 0) {
                Preconditions.checkState(this.zahd != null, "Sign-in mode should have been set explicitly by auto-manage.");
            } else if (this.zahd == null) {
                this.zahd = Integer.valueOf(zaa(this.zagz.values(), false));
            } else if (this.zahd.intValue() == 2) {
                Throwable th2 = th;
                new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
                throw th2;
            }
            zae(this.zahd.intValue());
            this.zags.enableCallbacks();
            ConnectionResult blockingConnect = this.zagt.blockingConnect();
            this.zaeo.unlock();
            return blockingConnect;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            this.zaeo.unlock();
            throw th4;
        }
    }

    /* JADX INFO: finally extract failed */
    public final ConnectionResult blockingConnect(long j, @NonNull TimeUnit timeUnit) {
        Throwable th;
        long j2 = j;
        TimeUnit timeUnit2 = timeUnit;
        Preconditions.checkState(Looper.myLooper() != Looper.getMainLooper(), "blockingConnect must not be called on the UI thread");
        Object checkNotNull = Preconditions.checkNotNull(timeUnit2, "TimeUnit must not be null");
        this.zaeo.lock();
        try {
            if (this.zahd == null) {
                this.zahd = Integer.valueOf(zaa(this.zagz.values(), false));
            } else if (this.zahd.intValue() == 2) {
                Throwable th2 = th;
                new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
                throw th2;
            }
            zae(this.zahd.intValue());
            this.zags.enableCallbacks();
            ConnectionResult blockingConnect = this.zagt.blockingConnect(j2, timeUnit2);
            this.zaeo.unlock();
            return blockingConnect;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            this.zaeo.unlock();
            throw th4;
        }
    }

    public final void disconnect() {
        this.zaeo.lock();
        try {
            this.zahf.release();
            if (this.zagt != null) {
                this.zagt.disconnect();
            }
            this.zahb.release();
            for (BaseImplementation.ApiMethodImpl apiMethodImpl : this.zafc) {
                apiMethodImpl.zaa((zacs) null);
                apiMethodImpl.cancel();
            }
            this.zafc.clear();
            if (this.zagt == null) {
                this.zaeo.unlock();
                return;
            }
            boolean zaaw = zaaw();
            this.zags.disableCallbacks();
            this.zaeo.unlock();
        } catch (Throwable th) {
            Throwable th2 = th;
            this.zaeo.unlock();
            throw th2;
        }
    }

    public final void reconnect() {
        disconnect();
        connect();
    }

    public final PendingResult<Status> clearDefaultAccountAndReconnect() {
        StatusPendingResult statusPendingResult;
        AtomicReference atomicReference;
        GoogleApiClient.ConnectionCallbacks connectionCallbacks;
        GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener;
        GoogleApiClient.Builder builder;
        Preconditions.checkState(isConnected(), "GoogleApiClient is not connected yet.");
        Preconditions.checkState(this.zahd.intValue() != 2, "Cannot use clearDefaultAccountAndReconnect with GOOGLE_SIGN_IN_API");
        new StatusPendingResult((GoogleApiClient) this);
        StatusPendingResult statusPendingResult2 = statusPendingResult;
        if (this.zagz.containsKey(Common.CLIENT_KEY)) {
            zaa(this, statusPendingResult2, false);
        } else {
            new AtomicReference();
            AtomicReference atomicReference2 = atomicReference;
            new zaay(this, atomicReference2, statusPendingResult2);
            new zaaz(this, statusPendingResult2);
            new GoogleApiClient.Builder(this.mContext);
            GoogleApiClient build = builder.addApi(Common.API).addConnectionCallbacks(connectionCallbacks).addOnConnectionFailedListener(onConnectionFailedListener).setHandler(this.zagx).build();
            atomicReference2.set(build);
            build.connect();
        }
        return statusPendingResult2;
    }

    private final void zaa(GoogleApiClient googleApiClient, StatusPendingResult statusPendingResult, boolean z) {
        ResultCallback resultCallback;
        GoogleApiClient googleApiClient2 = googleApiClient;
        new zaba(this, statusPendingResult, z, googleApiClient2);
        Common.zapi.zaa(googleApiClient2).setResultCallback(resultCallback);
    }

    public final void stopAutoManage(@NonNull FragmentActivity fragmentActivity) {
        LifecycleActivity lifecycleActivity;
        Throwable th;
        new LifecycleActivity((Activity) fragmentActivity);
        LifecycleActivity lifecycleActivity2 = lifecycleActivity;
        if (this.zacb >= 0) {
            zaj.zaa(lifecycleActivity2).zaa(this.zacb);
            return;
        }
        Throwable th2 = th;
        new IllegalStateException("Called stopAutoManage but automatic lifecycle management is not enabled.");
        throw th2;
    }

    public final boolean isConnected() {
        return this.zagt != null && this.zagt.isConnected();
    }

    public final boolean isConnecting() {
        return this.zagt != null && this.zagt.isConnecting();
    }

    private final void zae(int i) {
        Throwable th;
        StringBuilder sb;
        zabs zabs;
        Throwable th2;
        Throwable th3;
        zabs zabs2;
        zabs zabs3;
        int i2 = i;
        if (this.zahd == null) {
            this.zahd = Integer.valueOf(i2);
        } else if (this.zahd.intValue() != i2) {
            Throwable th4 = th;
            String zaf = zaf(i2);
            String zaf2 = zaf(this.zahd.intValue());
            new StringBuilder(51 + String.valueOf(zaf).length() + String.valueOf(zaf2).length());
            new IllegalStateException(sb.append("Cannot use sign-in mode: ").append(zaf).append(". Mode was already set to ").append(zaf2).toString());
            throw th4;
        }
        if (this.zagt == null) {
            boolean z = false;
            boolean z2 = false;
            for (Api.Client next : this.zagz.values()) {
                Api.Client client = next;
                if (next.requiresSignIn()) {
                    z = true;
                }
                if (client.providesSignIn()) {
                    z2 = true;
                }
            }
            switch (this.zahd.intValue()) {
                case 1:
                    if (!z) {
                        Throwable th5 = th3;
                        new IllegalStateException("SIGN_IN_MODE_REQUIRED cannot be used on a GoogleApiClient that does not contain any authenticated APIs. Use connect() instead.");
                        throw th5;
                    } else if (z2) {
                        Throwable th6 = th2;
                        new IllegalStateException("Cannot use SIGN_IN_MODE_REQUIRED with GOOGLE_SIGN_IN_API. Use connect(SIGN_IN_MODE_OPTIONAL) instead.");
                        throw th6;
                    }
                    break;
                case 2:
                    if (z) {
                        if (this.zach) {
                            new zax(this.mContext, this.zaeo, this.zabj, this.zacd, this.zagz, this.zaet, this.zaew, this.zace, this.zahc, this, true);
                            this.zagt = zabs;
                            return;
                        }
                        this.zagt = zas.zaa(this.mContext, this, this.zaeo, this.zabj, this.zacd, this.zagz, this.zaet, this.zaew, this.zace, this.zahc);
                        return;
                    }
                    break;
            }
            if (!this.zach || z2) {
                new zabe(this.mContext, this, this.zaeo, this.zabj, this.zacd, this.zagz, this.zaet, this.zaew, this.zace, this.zahc, this);
                this.zagt = zabs2;
                return;
            }
            new zax(this.mContext, this.zaeo, this.zabj, this.zacd, this.zagz, this.zaet, this.zaew, this.zace, this.zahc, this, false);
            this.zagt = zabs3;
        }
    }

    @GuardedBy("mLock")
    private final void zaau() {
        this.zags.enableCallbacks();
        this.zagt.connect();
    }

    /* access modifiers changed from: private */
    public final void resume() {
        this.zaeo.lock();
        try {
            if (this.zagu) {
                zaau();
            }
            this.zaeo.unlock();
        } catch (Throwable th) {
            Throwable th2 = th;
            this.zaeo.unlock();
            throw th2;
        }
    }

    /* access modifiers changed from: private */
    public final void zaav() {
        this.zaeo.lock();
        try {
            if (zaaw()) {
                zaau();
            }
            this.zaeo.unlock();
        } catch (Throwable th) {
            Throwable th2 = th;
            this.zaeo.unlock();
            throw th2;
        }
    }

    /* access modifiers changed from: package-private */
    @GuardedBy("mLock")
    public final boolean zaaw() {
        if (!this.zagu) {
            return false;
        }
        this.zagu = false;
        this.zagx.removeMessages(2);
        this.zagx.removeMessages(1);
        if (this.zagy != null) {
            this.zagy.unregister();
            this.zagy = null;
        }
        return true;
    }

    public final void registerConnectionCallbacks(@NonNull GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        this.zags.registerConnectionCallbacks(connectionCallbacks);
    }

    public final boolean isConnectionCallbacksRegistered(@NonNull GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        return this.zags.isConnectionCallbacksRegistered(connectionCallbacks);
    }

    public final void unregisterConnectionCallbacks(@NonNull GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        this.zags.unregisterConnectionCallbacks(connectionCallbacks);
    }

    public final void registerConnectionFailedListener(@NonNull GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this.zags.registerConnectionFailedListener(onConnectionFailedListener);
    }

    public final boolean isConnectionFailedListenerRegistered(@NonNull GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return this.zags.isConnectionFailedListenerRegistered(onConnectionFailedListener);
    }

    public final void unregisterConnectionFailedListener(@NonNull GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this.zags.unregisterConnectionFailedListener(onConnectionFailedListener);
    }

    @GuardedBy("mLock")
    public final void zab(Bundle bundle) {
        Bundle bundle2 = bundle;
        while (!this.zafc.isEmpty()) {
            BaseImplementation.ApiMethodImpl execute = execute(this.zafc.remove());
        }
        this.zags.onConnectionSuccess(bundle2);
    }

    @GuardedBy("mLock")
    public final void zac(ConnectionResult connectionResult) {
        ConnectionResult connectionResult2 = connectionResult;
        if (!this.zacd.isPlayServicesPossiblyUpdating(this.mContext, connectionResult2.getErrorCode())) {
            boolean zaaw = zaaw();
        }
        if (!this.zagu) {
            this.zags.onConnectionFailure(connectionResult2);
            this.zags.disableCallbacks();
        }
    }

    @GuardedBy("mLock")
    public final void zab(int i, boolean z) {
        zabr zabr;
        int i2 = i;
        boolean z2 = z;
        if (i2 == 1 && !z2) {
            if (!this.zagu) {
                this.zagu = true;
                if (this.zagy == null && !ClientLibraryUtils.isPackageSide()) {
                    new zabc(this);
                    this.zagy = this.zacd.zaa(this.mContext.getApplicationContext(), zabr);
                }
                boolean sendMessageDelayed = this.zagx.sendMessageDelayed(this.zagx.obtainMessage(1), this.zagv);
                boolean sendMessageDelayed2 = this.zagx.sendMessageDelayed(this.zagx.obtainMessage(2), this.zagw);
            }
        }
        this.zahf.zabx();
        this.zags.onUnintentionalDisconnection(i2);
        this.zags.disableCallbacks();
        if (i2 == 2) {
            zaau();
        }
    }

    public final Context getContext() {
        return this.mContext;
    }

    public final Looper getLooper() {
        return this.zabj;
    }

    public final boolean maybeSignIn(SignInConnectionListener signInConnectionListener) {
        return this.zagt != null && this.zagt.maybeSignIn(signInConnectionListener);
    }

    public final void maybeSignOut() {
        if (this.zagt != null) {
            this.zagt.maybeSignOut();
        }
    }

    public final void zaa(zacm zacm) {
        Set<zacm> set;
        zacm zacm2 = zacm;
        this.zaeo.lock();
        try {
            if (this.zahe == null) {
                new HashSet();
                this.zahe = set;
            }
            boolean add = this.zahe.add(zacm2);
            this.zaeo.unlock();
        } catch (Throwable th) {
            Throwable th2 = th;
            this.zaeo.unlock();
            throw th2;
        }
    }

    public final void zab(zacm zacm) {
        Throwable th;
        Throwable th2;
        zacm zacm2 = zacm;
        this.zaeo.lock();
        try {
            if (this.zahe == null) {
                new Exception();
                int wtf = Log.wtf("GoogleApiClientImpl", "Attempted to remove pending transform when no transforms are registered.", th2);
            } else if (!this.zahe.remove(zacm2)) {
                new Exception();
                int wtf2 = Log.wtf("GoogleApiClientImpl", "Failed to remove pending transform - this may lead to memory leaks!", th);
            } else if (!zaax()) {
                this.zagt.zaw();
            }
            this.zaeo.unlock();
        } catch (Throwable th3) {
            Throwable th4 = th3;
            this.zaeo.unlock();
            throw th4;
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    public final boolean zaax() {
        this.zaeo.lock();
        try {
            if (this.zahe == null) {
                this.zaeo.unlock();
                return false;
            }
            boolean z = !this.zahe.isEmpty();
            this.zaeo.unlock();
            return z;
        } catch (Throwable th) {
            Throwable th2 = th;
            this.zaeo.unlock();
            throw th2;
        }
    }

    /* access modifiers changed from: package-private */
    public final String zaay() {
        StringWriter stringWriter;
        PrintWriter printWriter;
        new StringWriter();
        StringWriter stringWriter2 = stringWriter;
        new PrintWriter(stringWriter2);
        dump("", (FileDescriptor) null, printWriter, (String[]) null);
        return stringWriter2.toString();
    }

    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        String str2 = str;
        FileDescriptor fileDescriptor2 = fileDescriptor;
        PrintWriter printWriter2 = printWriter;
        String[] strArr2 = strArr;
        printWriter2.append(str2).append("mContext=").println(this.mContext);
        printWriter2.append(str2).append("mResuming=").print(this.zagu);
        printWriter2.append(" mWorkQueue.size()=").print(this.zafc.size());
        printWriter2.append(" mUnconsumedApiCalls.size()=").println(this.zahf.zakz.size());
        if (this.zagt != null) {
            this.zagt.dump(str2, fileDescriptor2, printWriter2, strArr2);
        }
    }

    public static int zaa(Iterable<Api.Client> iterable, boolean z) {
        boolean z2 = z;
        boolean z3 = false;
        boolean z4 = false;
        for (Api.Client next : iterable) {
            Api.Client client = next;
            if (next.requiresSignIn()) {
                z3 = true;
            }
            if (client.providesSignIn()) {
                z4 = true;
            }
        }
        if (!z3) {
            return 3;
        }
        if (!z4 || !z2) {
            return 1;
        }
        return 2;
    }

    private static String zaf(int i) {
        switch (i) {
            case 1:
                return "SIGN_IN_MODE_REQUIRED";
            case 2:
                return "SIGN_IN_MODE_OPTIONAL";
            case 3:
                return "SIGN_IN_MODE_NONE";
            default:
                return "UNKNOWN";
        }
    }

    static /* synthetic */ void zaa(zaaw zaaw, GoogleApiClient googleApiClient, StatusPendingResult statusPendingResult, boolean z) {
        boolean z2 = z;
        zaaw.zaa(googleApiClient, statusPendingResult, true);
    }
}

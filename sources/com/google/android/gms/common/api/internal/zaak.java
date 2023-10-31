package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.internal.zaj;
import com.google.android.gms.signin.zad;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import javax.annotation.concurrent.GuardedBy;

public final class zaak implements zabd {
    /* access modifiers changed from: private */
    public final Context mContext;
    private final Api.AbstractClientBuilder<? extends zad, SignInOptions> zace;
    /* access modifiers changed from: private */
    public final Lock zaeo;
    /* access modifiers changed from: private */
    public final ClientSettings zaet;
    private final Map<Api<?>, Boolean> zaew;
    /* access modifiers changed from: private */
    public final GoogleApiAvailabilityLight zaey;
    private ConnectionResult zafh;
    /* access modifiers changed from: private */
    public final zabe zaft;
    private int zafw;
    private int zafx = 0;
    private int zafy;
    private final Bundle zafz;
    private final Set<Api.AnyClientKey> zaga;
    /* access modifiers changed from: private */
    public zad zagb;
    private boolean zagc;
    /* access modifiers changed from: private */
    public boolean zagd;
    private boolean zage;
    /* access modifiers changed from: private */
    public IAccountAccessor zagf;
    private boolean zagg;
    private boolean zagh;
    private ArrayList<Future<?>> zagi;

    public zaak(zabe zabe, ClientSettings clientSettings, Map<Api<?>, Boolean> map, GoogleApiAvailabilityLight googleApiAvailabilityLight, Api.AbstractClientBuilder<? extends zad, SignInOptions> abstractClientBuilder, Lock lock, Context context) {
        Bundle bundle;
        Set<Api.AnyClientKey> set;
        ArrayList<Future<?>> arrayList;
        new Bundle();
        this.zafz = bundle;
        new HashSet();
        this.zaga = set;
        new ArrayList<>();
        this.zagi = arrayList;
        this.zaft = zabe;
        this.zaet = clientSettings;
        this.zaew = map;
        this.zaey = googleApiAvailabilityLight;
        this.zace = abstractClientBuilder;
        this.zaeo = lock;
        this.mContext = context;
    }

    /* JADX WARNING: type inference failed for: r14v0, types: [com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener] */
    /* JADX WARNING: Multi-variable type inference failed */
    @javax.annotation.concurrent.GuardedBy("mLock")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void begin() {
        /*
            r16 = this;
            r0 = r16
            r7 = r0
            com.google.android.gms.common.api.internal.zabe r7 = r7.zaft
            java.util.Map<com.google.android.gms.common.api.Api$AnyClientKey<?>, com.google.android.gms.common.ConnectionResult> r7 = r7.zahp
            r7.clear()
            r7 = r0
            r8 = 0
            r7.zagd = r8
            r7 = r0
            r8 = 0
            r7.zafh = r8
            r7 = r0
            r8 = 0
            r7.zafx = r8
            r7 = r0
            r8 = 1
            r7.zagc = r8
            r7 = r0
            r8 = 0
            r7.zage = r8
            r7 = r0
            r8 = 0
            r7.zagg = r8
            r7 = 0
            r1 = r7
            java.util.HashMap r7 = new java.util.HashMap
            r15 = r7
            r7 = r15
            r8 = r15
            r8.<init>()
            r2 = r7
            r7 = r0
            java.util.Map<com.google.android.gms.common.api.Api<?>, java.lang.Boolean> r7 = r7.zaew
            java.util.Set r7 = r7.keySet()
            java.util.Iterator r7 = r7.iterator()
            r3 = r7
        L_0x0039:
            r7 = r3
            boolean r7 = r7.hasNext()
            if (r7 == 0) goto L_0x00ab
            r7 = r3
            java.lang.Object r7 = r7.next()
            com.google.android.gms.common.api.Api r7 = (com.google.android.gms.common.api.Api) r7
            r4 = r7
            r7 = r0
            com.google.android.gms.common.api.internal.zabe r7 = r7.zaft
            java.util.Map<com.google.android.gms.common.api.Api$AnyClientKey<?>, com.google.android.gms.common.api.Api$Client> r7 = r7.zagz
            r8 = r4
            com.google.android.gms.common.api.Api$AnyClientKey r8 = r8.getClientKey()
            java.lang.Object r7 = r7.get(r8)
            com.google.android.gms.common.api.Api$Client r7 = (com.google.android.gms.common.api.Api.Client) r7
            r5 = r7
            r7 = r1
            r8 = r4
            com.google.android.gms.common.api.Api$BaseClientBuilder r8 = r8.zah()
            int r8 = r8.getPriority()
            r9 = 1
            if (r8 != r9) goto L_0x00a4
            r8 = 1
        L_0x0067:
            r7 = r7 | r8
            r1 = r7
            r7 = r0
            java.util.Map<com.google.android.gms.common.api.Api<?>, java.lang.Boolean> r7 = r7.zaew
            r8 = r4
            java.lang.Object r7 = r7.get(r8)
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            r6 = r7
            r7 = r5
            boolean r7 = r7.requiresSignIn()
            if (r7 == 0) goto L_0x0092
            r7 = r0
            r8 = 1
            r7.zagd = r8
            r7 = r6
            if (r7 == 0) goto L_0x00a6
            r7 = r0
            java.util.Set<com.google.android.gms.common.api.Api$AnyClientKey> r7 = r7.zaga
            r8 = r4
            com.google.android.gms.common.api.Api$AnyClientKey r8 = r8.getClientKey()
            boolean r7 = r7.add(r8)
        L_0x0092:
            r7 = r2
            r8 = r5
            com.google.android.gms.common.api.internal.zaam r9 = new com.google.android.gms.common.api.internal.zaam
            r15 = r9
            r9 = r15
            r10 = r15
            r11 = r0
            r12 = r4
            r13 = r6
            r10.<init>(r11, r12, r13)
            java.lang.Object r7 = r7.put(r8, r9)
            goto L_0x0039
        L_0x00a4:
            r8 = 0
            goto L_0x0067
        L_0x00a6:
            r7 = r0
            r8 = 0
            r7.zagc = r8
            goto L_0x0092
        L_0x00ab:
            r7 = r1
            if (r7 == 0) goto L_0x00b2
            r7 = r0
            r8 = 0
            r7.zagd = r8
        L_0x00b2:
            r7 = r0
            boolean r7 = r7.zagd
            if (r7 == 0) goto L_0x00f9
            r7 = r0
            com.google.android.gms.common.internal.ClientSettings r7 = r7.zaet
            r8 = r0
            com.google.android.gms.common.api.internal.zabe r8 = r8.zaft
            com.google.android.gms.common.api.internal.zaaw r8 = r8.zaee
            int r8 = java.lang.System.identityHashCode(r8)
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            r7.setClientSessionId(r8)
            com.google.android.gms.common.api.internal.zaat r7 = new com.google.android.gms.common.api.internal.zaat
            r15 = r7
            r7 = r15
            r8 = r15
            r9 = r0
            r10 = 0
            r8.<init>(r9, r10)
            r3 = r7
            r7 = r0
            r8 = r0
            com.google.android.gms.common.api.Api$AbstractClientBuilder<? extends com.google.android.gms.signin.zad, com.google.android.gms.signin.SignInOptions> r8 = r8.zace
            r9 = r0
            android.content.Context r9 = r9.mContext
            r10 = r0
            com.google.android.gms.common.api.internal.zabe r10 = r10.zaft
            com.google.android.gms.common.api.internal.zaaw r10 = r10.zaee
            android.os.Looper r10 = r10.getLooper()
            r11 = r0
            com.google.android.gms.common.internal.ClientSettings r11 = r11.zaet
            r12 = r0
            com.google.android.gms.common.internal.ClientSettings r12 = r12.zaet
            com.google.android.gms.signin.SignInOptions r12 = r12.getSignInOptions()
            r13 = r3
            r14 = r3
            com.google.android.gms.common.api.Api$Client r8 = r8.buildClient(r9, r10, r11, r12, r13, r14)
            com.google.android.gms.signin.zad r8 = (com.google.android.gms.signin.zad) r8
            r7.zagb = r8
        L_0x00f9:
            r7 = r0
            r8 = r0
            com.google.android.gms.common.api.internal.zabe r8 = r8.zaft
            java.util.Map<com.google.android.gms.common.api.Api$AnyClientKey<?>, com.google.android.gms.common.api.Api$Client> r8 = r8.zagz
            int r8 = r8.size()
            r7.zafy = r8
            r7 = r0
            java.util.ArrayList<java.util.concurrent.Future<?>> r7 = r7.zagi
            java.util.concurrent.ExecutorService r8 = com.google.android.gms.common.api.internal.zabh.zabb()
            com.google.android.gms.common.api.internal.zaan r9 = new com.google.android.gms.common.api.internal.zaan
            r15 = r9
            r9 = r15
            r10 = r15
            r11 = r0
            r12 = r2
            r10.<init>(r11, r12)
            java.util.concurrent.Future r8 = r8.submit(r9)
            boolean r7 = r7.add(r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zaak.begin():void");
    }

    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public final boolean zaao() {
        Throwable th;
        ConnectionResult connectionResult;
        this.zafy--;
        if (this.zafy > 0) {
            return false;
        }
        if (this.zafy < 0) {
            int w = Log.w("GoogleApiClientConnecting", this.zaft.zaee.zaay());
            new Exception();
            int wtf = Log.wtf("GoogleApiClientConnecting", "GoogleApiClient received too many callbacks for the given step. Clients may be in an unexpected state; GoogleApiClient will now disconnect.", th);
            new ConnectionResult(8, (PendingIntent) null);
            zae(connectionResult);
            return false;
        } else if (this.zafh == null) {
            return true;
        } else {
            this.zaft.zahs = this.zafw;
            zae(this.zafh);
            return false;
        }
    }

    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public final void zaa(zaj zaj) {
        StringBuilder sb;
        Throwable th;
        zaj zaj2 = zaj;
        if (zac(0)) {
            ConnectionResult connectionResult = zaj2.getConnectionResult();
            ConnectionResult connectionResult2 = connectionResult;
            if (connectionResult.isSuccess()) {
                ResolveAccountResponse zacx = zaj2.zacx();
                ResolveAccountResponse resolveAccountResponse = zacx;
                ConnectionResult connectionResult3 = zacx.getConnectionResult();
                ConnectionResult connectionResult4 = connectionResult3;
                if (!connectionResult3.isSuccess()) {
                    String valueOf = String.valueOf(connectionResult4);
                    new StringBuilder(48 + String.valueOf(valueOf).length());
                    new Exception();
                    int wtf = Log.wtf("GoogleApiClientConnecting", sb.append("Sign-in succeeded with resolve account failure: ").append(valueOf).toString(), th);
                    zae(connectionResult4);
                    return;
                }
                this.zage = true;
                this.zagf = resolveAccountResponse.getAccountAccessor();
                this.zagg = resolveAccountResponse.getSaveDefaultAccount();
                this.zagh = resolveAccountResponse.isFromCrossClientAuth();
                zaap();
            } else if (zad(connectionResult2)) {
                zaar();
                zaap();
            } else {
                zae(connectionResult2);
            }
        }
    }

    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public final void zaap() {
        ArrayList arrayList;
        Runnable runnable;
        if (this.zafy == 0) {
            if (!this.zagd || this.zage) {
                new ArrayList();
                ArrayList arrayList2 = arrayList;
                this.zafx = 1;
                this.zafy = this.zaft.zagz.size();
                for (Api.AnyClientKey next : this.zaft.zagz.keySet()) {
                    if (!this.zaft.zahp.containsKey(next)) {
                        boolean add = arrayList2.add(this.zaft.zagz.get(next));
                    } else if (zaao()) {
                        zaaq();
                    }
                }
                if (!arrayList2.isEmpty()) {
                    ArrayList<Future<?>> arrayList3 = this.zagi;
                    new zaaq(this, arrayList2);
                    boolean add2 = arrayList3.add(zabh.zabb().submit(runnable));
                }
            }
        }
    }

    @GuardedBy("mLock")
    public final void onConnected(Bundle bundle) {
        Bundle bundle2 = bundle;
        if (zac(1)) {
            if (bundle2 != null) {
                this.zafz.putAll(bundle2);
            }
            if (zaao()) {
                zaaq();
            }
        }
    }

    @GuardedBy("mLock")
    public final void zaa(ConnectionResult connectionResult, Api<?> api, boolean z) {
        ConnectionResult connectionResult2 = connectionResult;
        Api<?> api2 = api;
        boolean z2 = z;
        if (zac(1)) {
            zab(connectionResult2, api2, z2);
            if (zaao()) {
                zaaq();
            }
        }
    }

    @GuardedBy("mLock")
    private final void zaaq() {
        Runnable runnable;
        this.zaft.zaba();
        new zaal(this);
        zabh.zabb().execute(runnable);
        if (this.zagb != null) {
            if (this.zagg) {
                this.zagb.zaa(this.zagf, this.zagh);
            }
            zab(false);
        }
        for (Api.AnyClientKey anyClientKey : this.zaft.zahp.keySet()) {
            this.zaft.zagz.get(anyClientKey).disconnect();
        }
        this.zaft.zaht.zab(this.zafz.isEmpty() ? null : this.zafz);
    }

    public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(T t) {
        T t2 = t;
        boolean add = this.zaft.zaee.zafc.add(t2);
        return t2;
    }

    public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(T t) {
        Throwable th;
        T t2 = t;
        Throwable th2 = th;
        new IllegalStateException("GoogleApiClient is not connected yet.");
        throw th2;
    }

    public final void connect() {
    }

    @GuardedBy("mLock")
    public final boolean disconnect() {
        zaas();
        zab(true);
        this.zaft.zaf((ConnectionResult) null);
        return true;
    }

    @GuardedBy("mLock")
    public final void onConnectionSuspended(int i) {
        ConnectionResult connectionResult;
        int i2 = i;
        new ConnectionResult(8, (PendingIntent) null);
        zae(connectionResult);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0024, code lost:
        if (r10 != false) goto L_0x0026;
     */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0034  */
    @javax.annotation.concurrent.GuardedBy("mLock")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zab(com.google.android.gms.common.ConnectionResult r14, com.google.android.gms.common.api.Api<?> r15, boolean r16) {
        /*
            r13 = this;
            r0 = r13
            r1 = r14
            r2 = r15
            r3 = r16
            r10 = r2
            com.google.android.gms.common.api.Api$BaseClientBuilder r10 = r10.zah()
            int r10 = r10.getPriority()
            r4 = r10
            r10 = r0
            r11 = r3
            r12 = r1
            r7 = r12
            r6 = r11
            r5 = r10
            r10 = r6
            if (r10 == 0) goto L_0x0026
            r10 = r5
            r11 = r7
            r9 = r11
            r8 = r10
            r10 = r9
            boolean r10 = r10.hasResolution()
            if (r10 == 0) goto L_0x004c
            r10 = 1
        L_0x0024:
            if (r10 == 0) goto L_0x005e
        L_0x0026:
            r10 = r5
            com.google.android.gms.common.ConnectionResult r10 = r10.zafh
            if (r10 == 0) goto L_0x0031
            r10 = r4
            r11 = r5
            int r11 = r11.zafw
            if (r10 >= r11) goto L_0x005e
        L_0x0031:
            r10 = 1
        L_0x0032:
            if (r10 == 0) goto L_0x003c
            r10 = r0
            r11 = r1
            r10.zafh = r11
            r10 = r0
            r11 = r4
            r10.zafw = r11
        L_0x003c:
            r10 = r0
            com.google.android.gms.common.api.internal.zabe r10 = r10.zaft
            java.util.Map<com.google.android.gms.common.api.Api$AnyClientKey<?>, com.google.android.gms.common.ConnectionResult> r10 = r10.zahp
            r11 = r2
            com.google.android.gms.common.api.Api$AnyClientKey r11 = r11.getClientKey()
            r12 = r1
            java.lang.Object r10 = r10.put(r11, r12)
            return
        L_0x004c:
            r10 = r8
            com.google.android.gms.common.GoogleApiAvailabilityLight r10 = r10.zaey
            r11 = r9
            int r11 = r11.getErrorCode()
            android.content.Intent r10 = r10.getErrorResolutionIntent(r11)
            if (r10 == 0) goto L_0x005c
            r10 = 1
            goto L_0x0024
        L_0x005c:
            r10 = 0
            goto L_0x0024
        L_0x005e:
            r10 = 0
            goto L_0x0032
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zaak.zab(com.google.android.gms.common.ConnectionResult, com.google.android.gms.common.api.Api, boolean):void");
    }

    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public final void zaar() {
        Object obj;
        this.zagd = false;
        this.zaft.zaee.zaha = Collections.emptySet();
        for (Api.AnyClientKey next : this.zaga) {
            if (!this.zaft.zahp.containsKey(next)) {
                new ConnectionResult(17, (PendingIntent) null);
                ConnectionResult put = this.zaft.zahp.put(next, obj);
            }
        }
    }

    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public final boolean zad(ConnectionResult connectionResult) {
        return this.zagc && !connectionResult.hasResolution();
    }

    /* access modifiers changed from: private */
    @GuardedBy("mLock")
    public final void zae(ConnectionResult connectionResult) {
        ConnectionResult connectionResult2 = connectionResult;
        zaas();
        zab(!connectionResult2.hasResolution());
        this.zaft.zaf(connectionResult2);
        this.zaft.zaht.zac(connectionResult2);
    }

    @GuardedBy("mLock")
    private final void zab(boolean z) {
        boolean z2 = z;
        if (this.zagb != null) {
            if (this.zagb.isConnected() && z2) {
                this.zagb.zacw();
            }
            this.zagb.disconnect();
            if (this.zaet.isSignInClientDisconnectFixEnabled()) {
                this.zagb = null;
            }
            this.zagf = null;
        }
    }

    private final void zaas() {
        ArrayList arrayList = this.zagi;
        ArrayList arrayList2 = arrayList;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            i++;
            boolean cancel = ((Future) arrayList2.get(i)).cancel(true);
        }
        this.zagi.clear();
    }

    /* access modifiers changed from: private */
    public final Set<Scope> zaat() {
        Set<Scope> set;
        if (this.zaet == null) {
            return Collections.emptySet();
        }
        new HashSet(this.zaet.getRequiredScopes());
        Set<Scope> set2 = set;
        Map<Api<?>, ClientSettings.OptionalApiSettings> optionalApiSettings = this.zaet.getOptionalApiSettings();
        Map<Api<?>, ClientSettings.OptionalApiSettings> map = optionalApiSettings;
        for (Api next : optionalApiSettings.keySet()) {
            if (!this.zaft.zahp.containsKey(next.getClientKey())) {
                boolean addAll = set2.addAll(map.get(next).mScopes);
            }
        }
        return set2;
    }

    @GuardedBy("mLock")
    private final boolean zac(int i) {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        Throwable th;
        ConnectionResult connectionResult;
        int i2 = i;
        if (this.zafx == i2) {
            return true;
        }
        int w = Log.w("GoogleApiClientConnecting", this.zaft.zaee.zaay());
        String valueOf = String.valueOf(this);
        new StringBuilder(23 + String.valueOf(valueOf).length());
        int w2 = Log.w("GoogleApiClientConnecting", sb.append("Unexpected callback in ").append(valueOf).toString());
        int i3 = this.zafy;
        new StringBuilder(33);
        int w3 = Log.w("GoogleApiClientConnecting", sb2.append("mRemainingConnections=").append(i3).toString());
        String zad = zad(this.zafx);
        String zad2 = zad(i2);
        new StringBuilder(70 + String.valueOf(zad).length() + String.valueOf(zad2).length());
        new Exception();
        int wtf = Log.wtf("GoogleApiClientConnecting", sb3.append("GoogleApiClient connecting is in step ").append(zad).append(" but received callback for step ").append(zad2).toString(), th);
        new ConnectionResult(8, (PendingIntent) null);
        zae(connectionResult);
        return false;
    }

    private static String zad(int i) {
        switch (i) {
            case 0:
                return "STEP_SERVICE_BINDINGS_AND_SIGN_IN";
            case 1:
                return "STEP_GETTING_REMOTE_SERVICE";
            default:
                return "UNKNOWN";
        }
    }

    static /* synthetic */ boolean zaa(zaak zaak, int i) {
        int i2 = i;
        return zaak.zac(0);
    }
}

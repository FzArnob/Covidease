package com.google.android.gms.common.api;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p000v4.app.FragmentActivity;
import android.support.p000v4.util.C1642ArrayMap;
import android.view.View;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.internal.LifecycleActivity;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.SignInConnectionListener;
import com.google.android.gms.common.api.internal.zacm;
import com.google.android.gms.common.internal.AccountType;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.zaa;
import com.google.android.gms.signin.zad;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.GuardedBy;

@KeepForSdk
public abstract class GoogleApiClient {
    @KeepForSdk
    public static final String DEFAULT_ACCOUNT = "<<default account>>";
    public static final int SIGN_IN_MODE_OPTIONAL = 2;
    public static final int SIGN_IN_MODE_REQUIRED = 1;
    /* access modifiers changed from: private */
    @GuardedBy("sAllClients")
    public static final Set<GoogleApiClient> zabq;

    public interface ConnectionCallbacks {
        public static final int CAUSE_NETWORK_LOST = 2;
        public static final int CAUSE_SERVICE_DISCONNECTED = 1;

        void onConnected(@Nullable Bundle bundle);

        void onConnectionSuspended(int i);
    }

    public interface OnConnectionFailedListener {
        void onConnectionFailed(@NonNull ConnectionResult connectionResult);
    }

    public GoogleApiClient() {
    }

    public abstract ConnectionResult blockingConnect();

    public abstract ConnectionResult blockingConnect(long j, @NonNull TimeUnit timeUnit);

    public abstract PendingResult<Status> clearDefaultAccountAndReconnect();

    public abstract void connect();

    public abstract void disconnect();

    public abstract void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    @NonNull
    public abstract ConnectionResult getConnectionResult(@NonNull Api<?> api);

    public abstract boolean hasConnectedApi(@NonNull Api<?> api);

    public abstract boolean isConnected();

    public abstract boolean isConnecting();

    public abstract boolean isConnectionCallbacksRegistered(@NonNull ConnectionCallbacks connectionCallbacks);

    public abstract boolean isConnectionFailedListenerRegistered(@NonNull OnConnectionFailedListener onConnectionFailedListener);

    public abstract void reconnect();

    public abstract void registerConnectionCallbacks(@NonNull ConnectionCallbacks connectionCallbacks);

    public abstract void registerConnectionFailedListener(@NonNull OnConnectionFailedListener onConnectionFailedListener);

    public abstract void stopAutoManage(@NonNull FragmentActivity fragmentActivity);

    public abstract void unregisterConnectionCallbacks(@NonNull ConnectionCallbacks connectionCallbacks);

    public abstract void unregisterConnectionFailedListener(@NonNull OnConnectionFailedListener onConnectionFailedListener);

    /* JADX INFO: finally extract failed */
    public static void dumpAll(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        String str2 = str;
        FileDescriptor fileDescriptor2 = fileDescriptor;
        PrintWriter printWriter2 = printWriter;
        String[] strArr2 = strArr;
        Set<GoogleApiClient> set = zabq;
        Set<GoogleApiClient> set2 = set;
        synchronized (set) {
            int i = 0;
            try {
                String concat = String.valueOf(str2).concat("  ");
                for (GoogleApiClient next : zabq) {
                    int i2 = i;
                    i++;
                    printWriter2.append(str2).append("GoogleApiClient#").println(i2);
                    next.dump(concat, fileDescriptor2, printWriter2, strArr2);
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                Set<GoogleApiClient> set3 = set2;
                throw th2;
            }
        }
    }

    @KeepForSdk
    public static Set<GoogleApiClient> getAllClients() {
        Set<GoogleApiClient> set = zabq;
        Set<GoogleApiClient> set2 = set;
        synchronized (set) {
            try {
                Set<GoogleApiClient> set3 = zabq;
                return set3;
            } catch (Throwable th) {
                Throwable th2 = th;
                Set<GoogleApiClient> set4 = set2;
                throw th2;
            }
        }
    }

    @KeepForSdk
    public <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(@NonNull T t) {
        Throwable th;
        T t2 = t;
        Throwable th2 = th;
        new UnsupportedOperationException();
        throw th2;
    }

    @KeepForSdk
    public <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(@NonNull T t) {
        Throwable th;
        T t2 = t;
        Throwable th2 = th;
        new UnsupportedOperationException();
        throw th2;
    }

    @KeepForSdk
    public <L> ListenerHolder<L> registerListener(@NonNull L l) {
        Throwable th;
        L l2 = l;
        Throwable th2 = th;
        new UnsupportedOperationException();
        throw th2;
    }

    @KeepForSdk
    @NonNull
    public <C extends Api.Client> C getClient(@NonNull Api.AnyClientKey<C> anyClientKey) {
        Throwable th;
        Api.AnyClientKey<C> anyClientKey2 = anyClientKey;
        Throwable th2 = th;
        new UnsupportedOperationException();
        throw th2;
    }

    @KeepForSdk
    public static final class Builder {
        private final Context mContext;
        private Looper zabj;
        private final Set<Scope> zabr;
        private final Set<Scope> zabs;
        private int zabt;
        private View zabu;
        private String zabv;
        private String zabw;
        private final Map<Api<?>, ClientSettings.OptionalApiSettings> zabx;
        private boolean zaby;
        private final Map<Api<?>, Api.ApiOptions> zabz;
        private LifecycleActivity zaca;
        private int zacb;
        private OnConnectionFailedListener zacc;
        private GoogleApiAvailability zacd;
        private Api.AbstractClientBuilder<? extends zad, SignInOptions> zace;
        private final ArrayList<ConnectionCallbacks> zacf;
        private final ArrayList<OnConnectionFailedListener> zacg;
        private boolean zach;
        private Account zax;

        @KeepForSdk
        public Builder(@NonNull Context context) {
            Set<Scope> set;
            Set<Scope> set2;
            Map<Api<?>, ClientSettings.OptionalApiSettings> map;
            Map<Api<?>, Api.ApiOptions> map2;
            ArrayList<ConnectionCallbacks> arrayList;
            ArrayList<OnConnectionFailedListener> arrayList2;
            Context context2 = context;
            new HashSet();
            this.zabr = set;
            new HashSet();
            this.zabs = set2;
            new C1642ArrayMap();
            this.zabx = map;
            this.zaby = false;
            new C1642ArrayMap();
            this.zabz = map2;
            this.zacb = -1;
            this.zacd = GoogleApiAvailability.getInstance();
            this.zace = zaa.zaph;
            new ArrayList<>();
            this.zacf = arrayList;
            new ArrayList<>();
            this.zacg = arrayList2;
            this.zach = false;
            this.mContext = context2;
            this.zabj = context2.getMainLooper();
            this.zabv = context2.getPackageName();
            this.zabw = context2.getClass().getName();
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        @KeepForSdk
        public Builder(@NonNull Context context, @NonNull ConnectionCallbacks connectionCallbacks, @NonNull OnConnectionFailedListener onConnectionFailedListener) {
            this(context);
            ConnectionCallbacks connectionCallbacks2 = connectionCallbacks;
            OnConnectionFailedListener onConnectionFailedListener2 = onConnectionFailedListener;
            Object checkNotNull = Preconditions.checkNotNull(connectionCallbacks2, "Must provide a connected listener");
            boolean add = this.zacf.add(connectionCallbacks2);
            Object checkNotNull2 = Preconditions.checkNotNull(onConnectionFailedListener2, "Must provide a connection failed listener");
            boolean add2 = this.zacg.add(onConnectionFailedListener2);
        }

        public final Builder setHandler(@NonNull Handler handler) {
            Handler handler2 = handler;
            Object checkNotNull = Preconditions.checkNotNull(handler2, "Handler must not be null");
            this.zabj = handler2.getLooper();
            return this;
        }

        public final Builder addConnectionCallbacks(@NonNull ConnectionCallbacks connectionCallbacks) {
            ConnectionCallbacks connectionCallbacks2 = connectionCallbacks;
            Object checkNotNull = Preconditions.checkNotNull(connectionCallbacks2, "Listener must not be null");
            boolean add = this.zacf.add(connectionCallbacks2);
            return this;
        }

        public final Builder addOnConnectionFailedListener(@NonNull OnConnectionFailedListener onConnectionFailedListener) {
            OnConnectionFailedListener onConnectionFailedListener2 = onConnectionFailedListener;
            Object checkNotNull = Preconditions.checkNotNull(onConnectionFailedListener2, "Listener must not be null");
            boolean add = this.zacg.add(onConnectionFailedListener2);
            return this;
        }

        public final Builder setViewForPopups(@NonNull View view) {
            View view2 = view;
            Object checkNotNull = Preconditions.checkNotNull(view2, "View must not be null");
            this.zabu = view2;
            return this;
        }

        public final Builder addScope(@NonNull Scope scope) {
            Scope scope2 = scope;
            Object checkNotNull = Preconditions.checkNotNull(scope2, "Scope must not be null");
            boolean add = this.zabr.add(scope2);
            return this;
        }

        @KeepForSdk
        public final Builder addScopeNames(String[] strArr) {
            Object obj;
            String[] strArr2 = strArr;
            for (int i = 0; i < strArr2.length; i++) {
                new Scope(strArr2[i]);
                boolean add = this.zabr.add(obj);
            }
            return this;
        }

        public final Builder addApi(@NonNull Api<? extends Api.ApiOptions.NotRequiredOptions> api) {
            Api<? extends Api.ApiOptions.NotRequiredOptions> api2 = api;
            Object checkNotNull = Preconditions.checkNotNull(api2, "Api must not be null");
            Api.ApiOptions put = this.zabz.put(api2, (Object) null);
            List<Scope> impliedScopes = api2.zah().getImpliedScopes(null);
            boolean addAll = this.zabs.addAll(impliedScopes);
            boolean addAll2 = this.zabr.addAll(impliedScopes);
            return this;
        }

        public final Builder addApiIfAvailable(@NonNull Api<? extends Api.ApiOptions.NotRequiredOptions> api, Scope... scopeArr) {
            Api<? extends Api.ApiOptions.NotRequiredOptions> api2 = api;
            Object checkNotNull = Preconditions.checkNotNull(api2, "Api must not be null");
            Api.ApiOptions put = this.zabz.put(api2, (Object) null);
            zaa(api2, (Api.ApiOptions) null, scopeArr);
            return this;
        }

        public final <O extends Api.ApiOptions.HasOptions> Builder addApi(@NonNull Api<O> api, @NonNull O o) {
            Api<O> api2 = api;
            O o2 = o;
            Object checkNotNull = Preconditions.checkNotNull(api2, "Api must not be null");
            Object checkNotNull2 = Preconditions.checkNotNull(o2, "Null options are not permitted for this Api");
            Api.ApiOptions put = this.zabz.put(api2, o2);
            List<Scope> impliedScopes = api2.zah().getImpliedScopes(o2);
            boolean addAll = this.zabs.addAll(impliedScopes);
            boolean addAll2 = this.zabr.addAll(impliedScopes);
            return this;
        }

        public final <O extends Api.ApiOptions.HasOptions> Builder addApiIfAvailable(@NonNull Api<O> api, @NonNull O o, Scope... scopeArr) {
            Api<O> api2 = api;
            O o2 = o;
            Object checkNotNull = Preconditions.checkNotNull(api2, "Api must not be null");
            Object checkNotNull2 = Preconditions.checkNotNull(o2, "Null options are not permitted for this Api");
            Api.ApiOptions put = this.zabz.put(api2, o2);
            zaa(api2, o2, scopeArr);
            return this;
        }

        public final Builder setAccountName(String str) {
            Account account;
            Account account2;
            String str2 = str;
            if (str2 == null) {
                account2 = null;
            } else {
                account2 = account;
                new Account(str2, AccountType.GOOGLE);
            }
            this.zax = account2;
            return this;
        }

        public final Builder useDefaultAccount() {
            return setAccountName("<<default account>>");
        }

        public final Builder setGravityForPopups(int i) {
            this.zabt = i;
            return this;
        }

        public final Builder enableAutoManage(@NonNull FragmentActivity fragmentActivity, int i, @Nullable OnConnectionFailedListener onConnectionFailedListener) {
            LifecycleActivity lifecycleActivity;
            int i2 = i;
            new LifecycleActivity((Activity) fragmentActivity);
            OnConnectionFailedListener onConnectionFailedListener2 = onConnectionFailedListener;
            LifecycleActivity lifecycleActivity2 = lifecycleActivity;
            Preconditions.checkArgument(i2 >= 0, "clientId must be non-negative");
            this.zacb = i2;
            this.zacc = onConnectionFailedListener2;
            this.zaca = lifecycleActivity2;
            return this;
        }

        public final Builder enableAutoManage(@NonNull FragmentActivity fragmentActivity, @Nullable OnConnectionFailedListener onConnectionFailedListener) {
            return enableAutoManage(fragmentActivity, 0, onConnectionFailedListener);
        }

        @KeepForSdk
        @VisibleForTesting
        public final ClientSettings buildClientSettings() {
            ClientSettings clientSettings;
            SignInOptions signInOptions = SignInOptions.DEFAULT;
            if (this.zabz.containsKey(zaa.API)) {
                signInOptions = (SignInOptions) this.zabz.get(zaa.API);
            }
            new ClientSettings(this.zax, this.zabr, this.zabx, this.zabt, this.zabu, this.zabv, this.zabw, signInOptions, false);
            return clientSettings;
        }

        /* JADX INFO: finally extract failed */
        /* JADX WARNING: type inference failed for: r35v3, types: [com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final com.google.android.gms.common.api.GoogleApiClient build() {
            /*
                r47 = this;
                r2 = r47
                r29 = r2
                r0 = r29
                java.util.Map<com.google.android.gms.common.api.Api<?>, com.google.android.gms.common.api.Api$ApiOptions> r0 = r0.zabz
                r29 = r0
                boolean r29 = r29.isEmpty()
                if (r29 != 0) goto L_0x01a4
                r29 = 1
            L_0x0012:
                java.lang.String r30 = "must call addApi() to add at least one API"
                com.google.android.gms.common.internal.Preconditions.checkArgument(r29, r30)
                r29 = r2
                r45 = r29
                r29 = r45
                r30 = r45
                r6 = r30
                com.google.android.gms.common.internal.ClientSettings r29 = r29.buildClientSettings()
                r7 = r29
                r29 = 0
                r8 = r29
                r29 = 0
                r9 = r29
                r29 = r7
                java.util.Map r29 = r29.getOptionalApiSettings()
                r10 = r29
                android.support.v4.util.ArrayMap r29 = new android.support.v4.util.ArrayMap
                r45 = r29
                r29 = r45
                r30 = r45
                r30.<init>()
                r11 = r29
                android.support.v4.util.ArrayMap r29 = new android.support.v4.util.ArrayMap
                r45 = r29
                r29 = r45
                r30 = r45
                r30.<init>()
                r12 = r29
                java.util.ArrayList r29 = new java.util.ArrayList
                r45 = r29
                r29 = r45
                r30 = r45
                r30.<init>()
                r13 = r29
                r29 = r6
                r0 = r29
                java.util.Map<com.google.android.gms.common.api.Api<?>, com.google.android.gms.common.api.Api$ApiOptions> r0 = r0.zabz
                r29 = r0
                java.util.Set r29 = r29.keySet()
                java.util.Iterator r29 = r29.iterator()
                r14 = r29
            L_0x0071:
                r29 = r14
                boolean r29 = r29.hasNext()
                if (r29 == 0) goto L_0x01b5
                r29 = r14
                java.lang.Object r29 = r29.next()
                com.google.android.gms.common.api.Api r29 = (com.google.android.gms.common.api.Api) r29
                r15 = r29
                r29 = r6
                r0 = r29
                java.util.Map<com.google.android.gms.common.api.Api<?>, com.google.android.gms.common.api.Api$ApiOptions> r0 = r0.zabz
                r29 = r0
                r30 = r15
                java.lang.Object r29 = r29.get(r30)
                r16 = r29
                r29 = r10
                r30 = r15
                java.lang.Object r29 = r29.get(r30)
                if (r29 == 0) goto L_0x01a8
                r29 = 1
            L_0x009f:
                r17 = r29
                r29 = r11
                r30 = r15
                r31 = r17
                java.lang.Boolean r31 = java.lang.Boolean.valueOf(r31)
                java.lang.Object r29 = r29.put(r30, r31)
                com.google.android.gms.common.api.internal.zaq r29 = new com.google.android.gms.common.api.internal.zaq
                r45 = r29
                r29 = r45
                r30 = r45
                r31 = r15
                r32 = r17
                r30.<init>(r31, r32)
                r18 = r29
                r29 = r13
                r30 = r18
                boolean r29 = r29.add(r30)
                r29 = r15
                com.google.android.gms.common.api.Api$AbstractClientBuilder r29 = r29.zai()
                r45 = r29
                r29 = r45
                r30 = r45
                r21 = r30
                r20 = r29
                r29 = r21
                r30 = r16
                r31 = r6
                r0 = r31
                android.content.Context r0 = r0.mContext
                r31 = r0
                r32 = r6
                r0 = r32
                android.os.Looper r0 = r0.zabj
                r32 = r0
                r33 = r7
                r34 = r18
                r35 = r18
                r28 = r35
                r27 = r34
                r26 = r33
                r25 = r32
                r24 = r31
                r23 = r30
                r30 = r24
                r31 = r25
                r32 = r26
                r33 = r23
                r34 = r27
                r35 = r28
                com.google.android.gms.common.api.Api$Client r29 = r29.buildClient(r30, r31, r32, r33, r34, r35)
                r19 = r29
                r29 = r12
                r30 = r15
                com.google.android.gms.common.api.Api$AnyClientKey r30 = r30.getClientKey()
                r31 = r19
                java.lang.Object r29 = r29.put(r30, r31)
                r29 = r20
                int r29 = r29.getPriority()
                r30 = 1
                r0 = r29
                r1 = r30
                if (r0 != r1) goto L_0x0134
                r29 = r16
                if (r29 == 0) goto L_0x01ac
                r29 = 1
            L_0x0132:
                r9 = r29
            L_0x0134:
                r29 = r19
                boolean r29 = r29.providesSignIn()
                if (r29 == 0) goto L_0x01b3
                r29 = r8
                if (r29 == 0) goto L_0x01af
                java.lang.IllegalStateException r29 = new java.lang.IllegalStateException
                r45 = r29
                r29 = r45
                r30 = r45
                r31 = r15
                java.lang.String r31 = r31.getName()
                r21 = r31
                r31 = r8
                java.lang.String r31 = r31.getName()
                r22 = r31
                r31 = 21
                r32 = r21
                java.lang.String r32 = java.lang.String.valueOf(r32)
                int r32 = r32.length()
                int r31 = r31 + r32
                r32 = r22
                java.lang.String r32 = java.lang.String.valueOf(r32)
                int r32 = r32.length()
                int r31 = r31 + r32
                java.lang.StringBuilder r32 = new java.lang.StringBuilder
                r45 = r31
                r46 = r32
                r31 = r46
                r32 = r45
                r33 = r46
                r45 = r32
                r46 = r33
                r32 = r46
                r33 = r45
                r32.<init>(r33)
                r32 = r21
                java.lang.StringBuilder r31 = r31.append(r32)
                java.lang.String r32 = " cannot be used with "
                java.lang.StringBuilder r31 = r31.append(r32)
                r32 = r22
                java.lang.StringBuilder r31 = r31.append(r32)
                java.lang.String r31 = r31.toString()
                r30.<init>(r31)
                throw r29
            L_0x01a4:
                r29 = 0
                goto L_0x0012
            L_0x01a8:
                r29 = 0
                goto L_0x009f
            L_0x01ac:
                r29 = 0
                goto L_0x0132
            L_0x01af:
                r29 = r15
                r8 = r29
            L_0x01b3:
                goto L_0x0071
            L_0x01b5:
                r29 = r8
                if (r29 == 0) goto L_0x026a
                r29 = r9
                if (r29 == 0) goto L_0x020e
                java.lang.IllegalStateException r29 = new java.lang.IllegalStateException
                r45 = r29
                r29 = r45
                r30 = r45
                r31 = r8
                java.lang.String r31 = r31.getName()
                r14 = r31
                r31 = 82
                r32 = r14
                java.lang.String r32 = java.lang.String.valueOf(r32)
                int r32 = r32.length()
                int r31 = r31 + r32
                java.lang.StringBuilder r32 = new java.lang.StringBuilder
                r45 = r31
                r46 = r32
                r31 = r46
                r32 = r45
                r33 = r46
                r45 = r32
                r46 = r33
                r32 = r46
                r33 = r45
                r32.<init>(r33)
                java.lang.String r32 = "With using "
                java.lang.StringBuilder r31 = r31.append(r32)
                r32 = r14
                java.lang.StringBuilder r31 = r31.append(r32)
                java.lang.String r32 = ", GamesOptions can only be specified within GoogleSignInOptions.Builder"
                java.lang.StringBuilder r31 = r31.append(r32)
                java.lang.String r31 = r31.toString()
                r30.<init>(r31)
                throw r29
            L_0x020e:
                r29 = r6
                r0 = r29
                android.accounts.Account r0 = r0.zax
                r29 = r0
                if (r29 != 0) goto L_0x032a
                r29 = 1
            L_0x021a:
                java.lang.String r30 = "Must not set an account in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead"
                r31 = 1
                r0 = r31
                java.lang.Object[] r0 = new java.lang.Object[r0]
                r31 = r0
                r45 = r31
                r31 = r45
                r32 = r45
                r33 = 0
                r34 = r8
                java.lang.String r34 = r34.getName()
                r32[r33] = r34
                com.google.android.gms.common.internal.Preconditions.checkState(r29, r30, r31)
                r29 = r6
                r0 = r29
                java.util.Set<com.google.android.gms.common.api.Scope> r0 = r0.zabr
                r29 = r0
                r30 = r6
                r0 = r30
                java.util.Set<com.google.android.gms.common.api.Scope> r0 = r0.zabs
                r30 = r0
                boolean r29 = r29.equals(r30)
                java.lang.String r30 = "Must not set scopes in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead."
                r31 = 1
                r0 = r31
                java.lang.Object[] r0 = new java.lang.Object[r0]
                r31 = r0
                r45 = r31
                r31 = r45
                r32 = r45
                r33 = 0
                r34 = r8
                java.lang.String r34 = r34.getName()
                r32[r33] = r34
                com.google.android.gms.common.internal.Preconditions.checkState(r29, r30, r31)
            L_0x026a:
                r29 = r12
                java.util.Collection r29 = r29.values()
                r30 = 1
                int r29 = com.google.android.gms.common.api.internal.zaaw.zaa(r29, r30)
                r14 = r29
                com.google.android.gms.common.api.internal.zaaw r29 = new com.google.android.gms.common.api.internal.zaaw
                r45 = r29
                r29 = r45
                r30 = r45
                r31 = r6
                r0 = r31
                android.content.Context r0 = r0.mContext
                r31 = r0
                java.util.concurrent.locks.ReentrantLock r32 = new java.util.concurrent.locks.ReentrantLock
                r45 = r32
                r32 = r45
                r33 = r45
                r33.<init>()
                r33 = r6
                r0 = r33
                android.os.Looper r0 = r0.zabj
                r33 = r0
                r34 = r7
                r35 = r6
                r0 = r35
                com.google.android.gms.common.GoogleApiAvailability r0 = r0.zacd
                r35 = r0
                r36 = r6
                r0 = r36
                com.google.android.gms.common.api.Api$AbstractClientBuilder<? extends com.google.android.gms.signin.zad, com.google.android.gms.signin.SignInOptions> r0 = r0.zace
                r36 = r0
                r37 = r11
                r38 = r6
                r0 = r38
                java.util.ArrayList<com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks> r0 = r0.zacf
                r38 = r0
                r39 = r6
                r0 = r39
                java.util.ArrayList<com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener> r0 = r0.zacg
                r39 = r0
                r40 = r12
                r41 = r6
                r0 = r41
                int r0 = r0.zacb
                r41 = r0
                r42 = r14
                r43 = r13
                r44 = 0
                r30.<init>(r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44)
                r3 = r29
                java.util.Set r29 = com.google.android.gms.common.api.GoogleApiClient.zabq
                r45 = r29
                r29 = r45
                r30 = r45
                r4 = r30
                monitor-enter(r29)
                java.util.Set r29 = com.google.android.gms.common.api.GoogleApiClient.zabq     // Catch:{ all -> 0x032e }
                r30 = r3
                boolean r29 = r29.add(r30)     // Catch:{ all -> 0x032e }
                r29 = r4
                monitor-exit(r29)     // Catch:{ all -> 0x032e }
                r29 = r2
                r0 = r29
                int r0 = r0.zacb
                r29 = r0
                if (r29 < 0) goto L_0x0325
                r29 = r2
                r30 = r3
                r7 = r30
                r45 = r29
                r29 = r45
                r30 = r45
                r6 = r30
                r0 = r29
                com.google.android.gms.common.api.internal.LifecycleActivity r0 = r0.zaca
                r29 = r0
                com.google.android.gms.common.api.internal.zaj r29 = com.google.android.gms.common.api.internal.zaj.zaa((com.google.android.gms.common.api.internal.LifecycleActivity) r29)
                r30 = r6
                r0 = r30
                int r0 = r0.zacb
                r30 = r0
                r31 = r7
                r32 = r6
                r0 = r32
                com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener r0 = r0.zacc
                r32 = r0
                r29.zaa(r30, r31, r32)
            L_0x0325:
                r29 = r3
                r2 = r29
                return r2
            L_0x032a:
                r29 = 0
                goto L_0x021a
            L_0x032e:
                r29 = move-exception
                r5 = r29
                r29 = r4
                monitor-exit(r29)     // Catch:{ all -> 0x032e }
                r29 = r5
                throw r29
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.GoogleApiClient.Builder.build():com.google.android.gms.common.api.GoogleApiClient");
        }

        private final <O extends Api.ApiOptions> void zaa(Api<O> api, O o, Scope... scopeArr) {
            Set set;
            Object obj;
            Api<O> api2 = api;
            new HashSet(api2.zah().getImpliedScopes(o));
            Set set2 = set;
            Scope[] scopeArr2 = scopeArr;
            Scope[] scopeArr3 = scopeArr2;
            int length = scopeArr2.length;
            for (int i = 0; i < length; i++) {
                boolean add = set2.add(scopeArr3[i]);
            }
            new ClientSettings.OptionalApiSettings(set2);
            ClientSettings.OptionalApiSettings put = this.zabx.put(api2, obj);
        }
    }

    @KeepForSdk
    public boolean hasApi(@NonNull Api<?> api) {
        Throwable th;
        Api<?> api2 = api;
        Throwable th2 = th;
        new UnsupportedOperationException();
        throw th2;
    }

    @KeepForSdk
    public Context getContext() {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException();
        throw th2;
    }

    @KeepForSdk
    public Looper getLooper() {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException();
        throw th2;
    }

    @KeepForSdk
    public boolean maybeSignIn(SignInConnectionListener signInConnectionListener) {
        Throwable th;
        SignInConnectionListener signInConnectionListener2 = signInConnectionListener;
        Throwable th2 = th;
        new UnsupportedOperationException();
        throw th2;
    }

    @KeepForSdk
    public void maybeSignOut() {
        Throwable th;
        Throwable th2 = th;
        new UnsupportedOperationException();
        throw th2;
    }

    public void connect(int i) {
        Throwable th;
        int i2 = i;
        Throwable th2 = th;
        new UnsupportedOperationException();
        throw th2;
    }

    public void zaa(zacm zacm) {
        Throwable th;
        zacm zacm2 = zacm;
        Throwable th2 = th;
        new UnsupportedOperationException();
        throw th2;
    }

    public void zab(zacm zacm) {
        Throwable th;
        zacm zacm2 = zacm;
        Throwable th2 = th;
        new UnsupportedOperationException();
        throw th2;
    }

    static {
        Map map;
        new WeakHashMap();
        zabq = Collections.newSetFromMap(map);
    }
}

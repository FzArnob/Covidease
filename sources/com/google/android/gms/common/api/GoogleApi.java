package com.google.android.gms.common.api;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.common.api.internal.RegistrationMethods;
import com.google.android.gms.common.api.internal.StatusExceptionMapper;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;
import com.google.android.gms.common.api.internal.zaae;
import com.google.android.gms.common.api.internal.zabp;
import com.google.android.gms.common.api.internal.zace;
import com.google.android.gms.common.api.internal.zai;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

@KeepForSdk
public class GoogleApi<O extends Api.ApiOptions> {
    private final Api<O> mApi;
    private final Context mContext;
    private final int mId;
    private final O zabh;
    private final zai<O> zabi;
    private final Looper zabj;
    private final GoogleApiClient zabk;
    private final StatusExceptionMapper zabl;
    protected final GoogleApiManager zabm;

    @KeepForSdk
    protected GoogleApi(@NonNull Context context, Api<O> api, Looper looper) {
        GoogleApiClient googleApiClient;
        StatusExceptionMapper statusExceptionMapper;
        Context context2 = context;
        Api<O> api2 = api;
        Looper looper2 = looper;
        Object checkNotNull = Preconditions.checkNotNull(context2, "Null context is not permitted.");
        Object checkNotNull2 = Preconditions.checkNotNull(api2, "Api must not be null.");
        Object checkNotNull3 = Preconditions.checkNotNull(looper2, "Looper must not be null.");
        this.mContext = context2.getApplicationContext();
        this.mApi = api2;
        this.zabh = null;
        this.zabj = looper2;
        this.zabi = zai.zaa(api2);
        new zabp(this);
        this.zabk = googleApiClient;
        this.zabm = GoogleApiManager.zab(this.mContext);
        this.mId = this.zabm.zabd();
        new ApiExceptionMapper();
        this.zabl = statusExceptionMapper;
    }

    @KeepForSdk
    public static class Settings {
        @KeepForSdk
        public static final Settings DEFAULT_SETTINGS;
        public final StatusExceptionMapper zabn;
        public final Looper zabo;

        @KeepForSdk
        public static class Builder {
            private Looper zabj;
            private StatusExceptionMapper zabl;

            @KeepForSdk
            public Builder() {
            }

            @KeepForSdk
            public Builder setMapper(StatusExceptionMapper statusExceptionMapper) {
                StatusExceptionMapper statusExceptionMapper2 = statusExceptionMapper;
                Object checkNotNull = Preconditions.checkNotNull(statusExceptionMapper2, "StatusExceptionMapper must not be null.");
                this.zabl = statusExceptionMapper2;
                return this;
            }

            @KeepForSdk
            public Builder setLooper(Looper looper) {
                Looper looper2 = looper;
                Object checkNotNull = Preconditions.checkNotNull(looper2, "Looper must not be null.");
                this.zabj = looper2;
                return this;
            }

            @KeepForSdk
            public Settings build() {
                Settings settings;
                StatusExceptionMapper statusExceptionMapper;
                if (this.zabl == null) {
                    new ApiExceptionMapper();
                    this.zabl = statusExceptionMapper;
                }
                if (this.zabj == null) {
                    this.zabj = Looper.getMainLooper();
                }
                new Settings(this.zabl, (Account) null, this.zabj, (zab) null);
                return settings;
            }
        }

        @KeepForSdk
        private Settings(StatusExceptionMapper statusExceptionMapper, Account account, Looper looper) {
            Account account2 = account;
            this.zabn = statusExceptionMapper;
            this.zabo = looper;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ Settings(StatusExceptionMapper statusExceptionMapper, Account account, Looper looper, zab zab) {
            this(statusExceptionMapper, (Account) null, looper);
            Account account2 = account;
            zab zab2 = zab;
        }

        static {
            Builder builder;
            new Builder();
            DEFAULT_SETTINGS = builder.build();
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    @com.google.android.gms.common.annotation.KeepForSdk
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public GoogleApi(@android.support.annotation.NonNull android.content.Context r14, com.google.android.gms.common.api.Api<O> r15, @android.support.annotation.Nullable O r16, android.os.Looper r17, com.google.android.gms.common.api.internal.StatusExceptionMapper r18) {
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
            com.google.android.gms.common.api.GoogleApi$Settings$Builder r10 = new com.google.android.gms.common.api.GoogleApi$Settings$Builder
            r12 = r10
            r10 = r12
            r11 = r12
            r11.<init>()
            r11 = r4
            com.google.android.gms.common.api.GoogleApi$Settings$Builder r10 = r10.setLooper(r11)
            r11 = r5
            com.google.android.gms.common.api.GoogleApi$Settings$Builder r10 = r10.setMapper(r11)
            com.google.android.gms.common.api.GoogleApi$Settings r10 = r10.build()
            r6.<init>((android.content.Context) r7, r8, r9, (com.google.android.gms.common.api.GoogleApi.Settings) r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.GoogleApi.<init>(android.content.Context, com.google.android.gms.common.api.Api, com.google.android.gms.common.api.Api$ApiOptions, android.os.Looper, com.google.android.gms.common.api.internal.StatusExceptionMapper):void");
    }

    @KeepForSdk
    @MainThread
    public GoogleApi(@NonNull Activity activity, Api<O> api, @Nullable O o, Settings settings) {
        GoogleApiClient googleApiClient;
        Activity activity2 = activity;
        Api<O> api2 = api;
        Settings settings2 = settings;
        Object checkNotNull = Preconditions.checkNotNull(activity2, "Null activity is not permitted.");
        Object checkNotNull2 = Preconditions.checkNotNull(api2, "Api must not be null.");
        Object checkNotNull3 = Preconditions.checkNotNull(settings2, "Settings must not be null; use Settings.DEFAULT_SETTINGS instead.");
        this.mContext = activity2.getApplicationContext();
        this.mApi = api2;
        this.zabh = o;
        this.zabj = settings2.zabo;
        this.zabi = zai.zaa(this.mApi, this.zabh);
        new zabp(this);
        this.zabk = googleApiClient;
        this.zabm = GoogleApiManager.zab(this.mContext);
        this.mId = this.zabm.zabd();
        this.zabl = settings2.zabn;
        if (!(activity2 instanceof GoogleApiActivity)) {
            zaae.zaa(activity2, this.zabm, this.zabi);
        }
        this.zabm.zaa((GoogleApi<?>) this);
    }

    @KeepForSdk
    public GoogleApi(@NonNull Context context, Api<O> api, @Nullable O o, Settings settings) {
        GoogleApiClient googleApiClient;
        Context context2 = context;
        Api<O> api2 = api;
        Settings settings2 = settings;
        Object checkNotNull = Preconditions.checkNotNull(context2, "Null context is not permitted.");
        Object checkNotNull2 = Preconditions.checkNotNull(api2, "Api must not be null.");
        Object checkNotNull3 = Preconditions.checkNotNull(settings2, "Settings must not be null; use Settings.DEFAULT_SETTINGS instead.");
        this.mContext = context2.getApplicationContext();
        this.mApi = api2;
        this.zabh = o;
        this.zabj = settings2.zabo;
        this.zabi = zai.zaa(this.mApi, this.zabh);
        new zabp(this);
        this.zabk = googleApiClient;
        this.zabm = GoogleApiManager.zab(this.mContext);
        this.mId = this.zabm.zabd();
        this.zabl = settings2.zabn;
        this.zabm.zaa((GoogleApi<?>) this);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    @com.google.android.gms.common.annotation.KeepForSdk
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public GoogleApi(@android.support.annotation.NonNull android.app.Activity r13, com.google.android.gms.common.api.Api<O> r14, @android.support.annotation.Nullable O r15, com.google.android.gms.common.api.internal.StatusExceptionMapper r16) {
        /*
            r12 = this;
            r0 = r12
            r1 = r13
            r2 = r14
            r3 = r15
            r4 = r16
            r5 = r0
            r6 = r1
            r7 = r2
            r8 = r3
            com.google.android.gms.common.api.GoogleApi$Settings$Builder r9 = new com.google.android.gms.common.api.GoogleApi$Settings$Builder
            r11 = r9
            r9 = r11
            r10 = r11
            r10.<init>()
            r10 = r4
            com.google.android.gms.common.api.GoogleApi$Settings$Builder r9 = r9.setMapper(r10)
            r10 = r1
            android.os.Looper r10 = r10.getMainLooper()
            com.google.android.gms.common.api.GoogleApi$Settings$Builder r9 = r9.setLooper(r10)
            com.google.android.gms.common.api.GoogleApi$Settings r9 = r9.build()
            r5.<init>((android.app.Activity) r6, r7, r8, (com.google.android.gms.common.api.GoogleApi.Settings) r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.GoogleApi.<init>(android.app.Activity, com.google.android.gms.common.api.Api, com.google.android.gms.common.api.Api$ApiOptions, com.google.android.gms.common.api.internal.StatusExceptionMapper):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    @com.google.android.gms.common.annotation.KeepForSdk
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public GoogleApi(@android.support.annotation.NonNull android.content.Context r13, com.google.android.gms.common.api.Api<O> r14, @android.support.annotation.Nullable O r15, com.google.android.gms.common.api.internal.StatusExceptionMapper r16) {
        /*
            r12 = this;
            r0 = r12
            r1 = r13
            r2 = r14
            r3 = r15
            r4 = r16
            r5 = r0
            r6 = r1
            r7 = r2
            r8 = r3
            com.google.android.gms.common.api.GoogleApi$Settings$Builder r9 = new com.google.android.gms.common.api.GoogleApi$Settings$Builder
            r11 = r9
            r9 = r11
            r10 = r11
            r10.<init>()
            r10 = r4
            com.google.android.gms.common.api.GoogleApi$Settings$Builder r9 = r9.setMapper(r10)
            com.google.android.gms.common.api.GoogleApi$Settings r9 = r9.build()
            r5.<init>((android.content.Context) r6, r7, r8, (com.google.android.gms.common.api.GoogleApi.Settings) r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.GoogleApi.<init>(android.content.Context, com.google.android.gms.common.api.Api, com.google.android.gms.common.api.Api$ApiOptions, com.google.android.gms.common.api.internal.StatusExceptionMapper):void");
    }

    private final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T zaa(int i, @NonNull T t) {
        T t2 = t;
        t2.zau();
        this.zabm.zaa(this, i, (BaseImplementation.ApiMethodImpl<? extends Result, Api.AnyClient>) t2);
        return t2;
    }

    private final <TResult, A extends Api.AnyClient> Task<TResult> zaa(int i, @NonNull TaskApiCall<A, TResult> taskApiCall) {
        TaskCompletionSource taskCompletionSource;
        new TaskCompletionSource();
        TaskCompletionSource taskCompletionSource2 = taskCompletionSource;
        this.zabm.zaa(this, i, taskApiCall, taskCompletionSource2, this.zabl);
        return taskCompletionSource2.getTask();
    }

    @KeepForSdk
    public <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T doRead(@NonNull T t) {
        return zaa(0, t);
    }

    @KeepForSdk
    public <TResult, A extends Api.AnyClient> Task<TResult> doRead(TaskApiCall<A, TResult> taskApiCall) {
        return zaa(0, taskApiCall);
    }

    @KeepForSdk
    public <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T doWrite(@NonNull T t) {
        return zaa(1, t);
    }

    @KeepForSdk
    public <TResult, A extends Api.AnyClient> Task<TResult> doWrite(TaskApiCall<A, TResult> taskApiCall) {
        return zaa(1, taskApiCall);
    }

    @KeepForSdk
    public <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T doBestEffortWrite(@NonNull T t) {
        return zaa(2, t);
    }

    @KeepForSdk
    public <TResult, A extends Api.AnyClient> Task<TResult> doBestEffortWrite(TaskApiCall<A, TResult> taskApiCall) {
        return zaa(2, taskApiCall);
    }

    @KeepForSdk
    @Deprecated
    public <A extends Api.AnyClient, T extends RegisterListenerMethod<A, ?>, U extends UnregisterListenerMethod<A, ?>> Task<Void> doRegisterEventListener(@NonNull T t, U u) {
        T t2 = t;
        U u2 = u;
        Object checkNotNull = Preconditions.checkNotNull(t2);
        Object checkNotNull2 = Preconditions.checkNotNull(u2);
        Object checkNotNull3 = Preconditions.checkNotNull(t2.getListenerKey(), "Listener has already been released.");
        Object checkNotNull4 = Preconditions.checkNotNull(u2.getListenerKey(), "Listener has already been released.");
        Preconditions.checkArgument(t2.getListenerKey().equals(u2.getListenerKey()), "Listener registration and unregistration methods must be constructed with the same ListenerHolder.");
        return this.zabm.zaa(this, (RegisterListenerMethod<Api.AnyClient, ?>) t2, (UnregisterListenerMethod<Api.AnyClient, ?>) u2);
    }

    @KeepForSdk
    public <A extends Api.AnyClient> Task<Void> doRegisterEventListener(@NonNull RegistrationMethods<A, ?> registrationMethods) {
        RegistrationMethods<A, ?> registrationMethods2 = registrationMethods;
        Object checkNotNull = Preconditions.checkNotNull(registrationMethods2);
        Object checkNotNull2 = Preconditions.checkNotNull(registrationMethods2.zajz.getListenerKey(), "Listener has already been released.");
        Object checkNotNull3 = Preconditions.checkNotNull(registrationMethods2.zaka.getListenerKey(), "Listener has already been released.");
        return this.zabm.zaa(this, (RegisterListenerMethod<Api.AnyClient, ?>) registrationMethods2.zajz, (UnregisterListenerMethod<Api.AnyClient, ?>) registrationMethods2.zaka);
    }

    @KeepForSdk
    public Task<Boolean> doUnregisterEventListener(@NonNull ListenerHolder.ListenerKey<?> listenerKey) {
        ListenerHolder.ListenerKey<?> listenerKey2 = listenerKey;
        Object checkNotNull = Preconditions.checkNotNull(listenerKey2, "Listener key cannot be null.");
        return this.zabm.zaa(this, listenerKey2);
    }

    @KeepForSdk
    public <L> ListenerHolder<L> registerListener(@NonNull L l, String str) {
        return ListenerHolders.createListenerHolder(l, this.zabj, str);
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public Task<Boolean> disconnectService() {
        return this.zabm.zac((GoogleApi<?>) this);
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [com.google.android.gms.common.api.Api$Client] */
    /* JADX WARNING: Multi-variable type inference failed */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.common.api.Api.Client zaa(android.os.Looper r12, com.google.android.gms.common.api.internal.GoogleApiManager.zaa<O> r13) {
        /*
            r11 = this;
            r0 = r11
            r1 = r12
            r2 = r13
            r4 = r0
            com.google.android.gms.common.internal.ClientSettings$Builder r4 = r4.createClientSettingsBuilder()
            com.google.android.gms.common.internal.ClientSettings r4 = r4.build()
            r3 = r4
            r4 = r0
            com.google.android.gms.common.api.Api<O> r4 = r4.mApi
            com.google.android.gms.common.api.Api$AbstractClientBuilder r4 = r4.zai()
            r5 = r0
            android.content.Context r5 = r5.mContext
            r6 = r1
            r7 = r3
            r8 = r0
            O r8 = r8.zabh
            r9 = r2
            r10 = r2
            com.google.android.gms.common.api.Api$Client r4 = r4.buildClient(r5, r6, r7, r8, r9, r10)
            r0 = r4
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.GoogleApi.zaa(android.os.Looper, com.google.android.gms.common.api.internal.GoogleApiManager$zaa):com.google.android.gms.common.api.Api$Client");
    }

    public final Api<O> getApi() {
        return this.mApi;
    }

    @KeepForSdk
    public O getApiOptions() {
        return this.zabh;
    }

    public final zai<O> zak() {
        return this.zabi;
    }

    public final int getInstanceId() {
        return this.mId;
    }

    @KeepForSdk
    public GoogleApiClient asGoogleApiClient() {
        return this.zabk;
    }

    @KeepForSdk
    public Looper getLooper() {
        return this.zabj;
    }

    @KeepForSdk
    public Context getApplicationContext() {
        return this.mContext;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x0037  */
    @com.google.android.gms.common.annotation.KeepForSdk
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.common.internal.ClientSettings.Builder createClientSettingsBuilder() {
        /*
            r7 = this;
            r0 = r7
            com.google.android.gms.common.internal.ClientSettings$Builder r3 = new com.google.android.gms.common.internal.ClientSettings$Builder
            r6 = r3
            r3 = r6
            r4 = r6
            r4.<init>()
            r4 = r0
            r6 = r4
            r4 = r6
            r5 = r6
            r1 = r5
            O r4 = r4.zabh
            boolean r4 = r4 instanceof com.google.android.gms.common.api.Api.ApiOptions.HasGoogleSignInAccountOptions
            if (r4 == 0) goto L_0x006b
            r4 = r1
            O r4 = r4.zabh
            com.google.android.gms.common.api.Api$ApiOptions$HasGoogleSignInAccountOptions r4 = (com.google.android.gms.common.api.Api.ApiOptions.HasGoogleSignInAccountOptions) r4
            com.google.android.gms.auth.api.signin.GoogleSignInAccount r4 = r4.getGoogleSignInAccount()
            r6 = r4
            r4 = r6
            r5 = r6
            r2 = r5
            if (r4 == 0) goto L_0x006b
            r4 = r2
            android.accounts.Account r4 = r4.getAccount()
        L_0x0028:
            com.google.android.gms.common.internal.ClientSettings$Builder r3 = r3.setAccount(r4)
            r4 = r0
            r6 = r4
            r4 = r6
            r5 = r6
            r1 = r5
            O r4 = r4.zabh
            boolean r4 = r4 instanceof com.google.android.gms.common.api.Api.ApiOptions.HasGoogleSignInAccountOptions
            if (r4 == 0) goto L_0x007e
            r4 = r1
            O r4 = r4.zabh
            com.google.android.gms.common.api.Api$ApiOptions$HasGoogleSignInAccountOptions r4 = (com.google.android.gms.common.api.Api.ApiOptions.HasGoogleSignInAccountOptions) r4
            com.google.android.gms.auth.api.signin.GoogleSignInAccount r4 = r4.getGoogleSignInAccount()
            r6 = r4
            r4 = r6
            r5 = r6
            r2 = r5
            if (r4 == 0) goto L_0x007e
            r4 = r2
            java.util.Set r4 = r4.getRequestedScopes()
        L_0x004b:
            com.google.android.gms.common.internal.ClientSettings$Builder r3 = r3.addAllRequiredScopes(r4)
            r4 = r0
            android.content.Context r4 = r4.mContext
            java.lang.Class r4 = r4.getClass()
            java.lang.String r4 = r4.getName()
            com.google.android.gms.common.internal.ClientSettings$Builder r3 = r3.setRealClientClassName(r4)
            r4 = r0
            android.content.Context r4 = r4.mContext
            java.lang.String r4 = r4.getPackageName()
            com.google.android.gms.common.internal.ClientSettings$Builder r3 = r3.setRealClientPackageName(r4)
            r0 = r3
            return r0
        L_0x006b:
            r4 = r1
            O r4 = r4.zabh
            boolean r4 = r4 instanceof com.google.android.gms.common.api.Api.ApiOptions.HasAccountOptions
            if (r4 == 0) goto L_0x007c
            r4 = r1
            O r4 = r4.zabh
            com.google.android.gms.common.api.Api$ApiOptions$HasAccountOptions r4 = (com.google.android.gms.common.api.Api.ApiOptions.HasAccountOptions) r4
            android.accounts.Account r4 = r4.getAccount()
            goto L_0x0028
        L_0x007c:
            r4 = 0
            goto L_0x0028
        L_0x007e:
            java.util.Set r4 = java.util.Collections.emptySet()
            goto L_0x004b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.GoogleApi.createClientSettingsBuilder():com.google.android.gms.common.internal.ClientSettings$Builder");
    }

    public zace zaa(Context context, Handler handler) {
        zace zace;
        new zace(context, handler, createClientSettingsBuilder().build());
        return zace;
    }
}

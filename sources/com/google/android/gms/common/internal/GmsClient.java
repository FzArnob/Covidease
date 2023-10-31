package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Handler;
import android.os.IInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.GmsClientEventManager;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Set;

@KeepForSdk
public abstract class GmsClient<T extends IInterface> extends BaseGmsClient<T> implements Api.Client, GmsClientEventManager.GmsClientEventState {
    private final Set<Scope> mScopes;
    private final ClientSettings zaet;
    private final Account zax;

    /* JADX WARNING: Illegal instructions before constructor call */
    @com.google.android.gms.common.annotation.KeepForSdk
    @com.google.android.gms.common.util.VisibleForTesting
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected GmsClient(android.content.Context r15, android.os.Handler r16, int r17, com.google.android.gms.common.internal.ClientSettings r18) {
        /*
            r14 = this;
            r0 = r14
            r1 = r15
            r2 = r16
            r3 = r17
            r4 = r18
            r5 = r0
            r6 = r1
            r7 = r2
            r8 = r1
            com.google.android.gms.common.internal.GmsClientSupervisor r8 = com.google.android.gms.common.internal.GmsClientSupervisor.getInstance(r8)
            com.google.android.gms.common.GoogleApiAvailability r9 = com.google.android.gms.common.GoogleApiAvailability.getInstance()
            r10 = r3
            r11 = r4
            r12 = 0
            r13 = 0
            r5.<init>((android.content.Context) r6, (android.os.Handler) r7, (com.google.android.gms.common.internal.GmsClientSupervisor) r8, (com.google.android.gms.common.GoogleApiAvailability) r9, (int) r10, (com.google.android.gms.common.internal.ClientSettings) r11, (com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks) r12, (com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener) r13)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.GmsClient.<init>(android.content.Context, android.os.Handler, int, com.google.android.gms.common.internal.ClientSettings):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    @com.google.android.gms.common.annotation.KeepForSdk
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected GmsClient(android.content.Context r17, android.os.Looper r18, int r19, com.google.android.gms.common.internal.ClientSettings r20, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks r21, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener r22) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = r18
            r3 = r19
            r4 = r20
            r5 = r21
            r6 = r22
            r7 = r0
            r8 = r1
            r9 = r2
            r10 = r1
            com.google.android.gms.common.internal.GmsClientSupervisor r10 = com.google.android.gms.common.internal.GmsClientSupervisor.getInstance(r10)
            com.google.android.gms.common.GoogleApiAvailability r11 = com.google.android.gms.common.GoogleApiAvailability.getInstance()
            r12 = r3
            r13 = r4
            r14 = r5
            java.lang.Object r14 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r14)
            com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks r14 = (com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks) r14
            r15 = r6
            java.lang.Object r15 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r15)
            com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener r15 = (com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener) r15
            r7.<init>((android.content.Context) r8, (android.os.Looper) r9, (com.google.android.gms.common.internal.GmsClientSupervisor) r10, (com.google.android.gms.common.GoogleApiAvailability) r11, (int) r12, (com.google.android.gms.common.internal.ClientSettings) r13, (com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks) r14, (com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener) r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.GmsClient.<init>(android.content.Context, android.os.Looper, int, com.google.android.gms.common.internal.ClientSettings, com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks, com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    @com.google.android.gms.common.annotation.KeepForSdk
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected GmsClient(android.content.Context r15, android.os.Looper r16, int r17, com.google.android.gms.common.internal.ClientSettings r18) {
        /*
            r14 = this;
            r0 = r14
            r1 = r15
            r2 = r16
            r3 = r17
            r4 = r18
            r5 = r0
            r6 = r1
            r7 = r2
            r8 = r1
            com.google.android.gms.common.internal.GmsClientSupervisor r8 = com.google.android.gms.common.internal.GmsClientSupervisor.getInstance(r8)
            com.google.android.gms.common.GoogleApiAvailability r9 = com.google.android.gms.common.GoogleApiAvailability.getInstance()
            r10 = r3
            r11 = r4
            r12 = 0
            r13 = 0
            r5.<init>((android.content.Context) r6, (android.os.Looper) r7, (com.google.android.gms.common.internal.GmsClientSupervisor) r8, (com.google.android.gms.common.GoogleApiAvailability) r9, (int) r10, (com.google.android.gms.common.internal.ClientSettings) r11, (com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks) r12, (com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener) r13)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.GmsClient.<init>(android.content.Context, android.os.Looper, int, com.google.android.gms.common.internal.ClientSettings):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    @com.google.android.gms.common.util.VisibleForTesting
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected GmsClient(android.content.Context r19, android.os.Looper r20, com.google.android.gms.common.internal.GmsClientSupervisor r21, com.google.android.gms.common.GoogleApiAvailability r22, int r23, com.google.android.gms.common.internal.ClientSettings r24, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks r25, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener r26) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r2 = r20
            r3 = r21
            r4 = r22
            r5 = r23
            r6 = r24
            r7 = r25
            r8 = r26
            r9 = r0
            r10 = r1
            r11 = r2
            r12 = r3
            r13 = r4
            r14 = r5
            r15 = r7
            com.google.android.gms.common.internal.BaseGmsClient$BaseConnectionCallbacks r15 = zaa((com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks) r15)
            r16 = r8
            com.google.android.gms.common.internal.BaseGmsClient$BaseOnConnectionFailedListener r16 = zaa((com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener) r16)
            r17 = r6
            java.lang.String r17 = r17.getRealClientClassName()
            r9.<init>(r10, r11, r12, r13, r14, r15, r16, r17)
            r9 = r0
            r10 = r6
            r9.zaet = r10
            r9 = r0
            r10 = r6
            android.accounts.Account r10 = r10.getAccount()
            r9.zax = r10
            r9 = r0
            r10 = r0
            r11 = r6
            java.util.Set r11 = r11.getAllRequestedScopes()
            java.util.Set r10 = r10.zaa((java.util.Set<com.google.android.gms.common.api.Scope>) r11)
            r9.mScopes = r10
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.GmsClient.<init>(android.content.Context, android.os.Looper, com.google.android.gms.common.internal.GmsClientSupervisor, com.google.android.gms.common.GoogleApiAvailability, int, com.google.android.gms.common.internal.ClientSettings, com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks, com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener):void");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @VisibleForTesting
    protected GmsClient(Context context, Handler handler, GmsClientSupervisor gmsClientSupervisor, GoogleApiAvailability googleApiAvailability, int i, ClientSettings clientSettings, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, handler, gmsClientSupervisor, googleApiAvailability, i, zaa(connectionCallbacks), zaa(onConnectionFailedListener));
        ClientSettings clientSettings2 = clientSettings;
        this.zaet = (ClientSettings) Preconditions.checkNotNull(clientSettings2);
        this.zax = clientSettings2.getAccount();
        this.mScopes = zaa(clientSettings2.getAllRequestedScopes());
    }

    private final Set<Scope> zaa(@NonNull Set<Scope> set) {
        Throwable th;
        Set<Scope> set2 = set;
        Set<Scope> validateScopes = validateScopes(set2);
        Set<Scope> set3 = validateScopes;
        for (Scope contains : validateScopes) {
            if (!set2.contains(contains)) {
                Throwable th2 = th;
                new IllegalStateException("Expanding scopes is not permitted, use implied scopes instead");
                throw th2;
            }
        }
        return set3;
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    @NonNull
    public Set<Scope> validateScopes(@NonNull Set<Scope> set) {
        return set;
    }

    public final Account getAccount() {
        return this.zax;
    }

    /* access modifiers changed from: protected */
    @KeepForSdk
    public final ClientSettings getClientSettings() {
        return this.zaet;
    }

    /* access modifiers changed from: protected */
    public final Set<Scope> getScopes() {
        return this.mScopes;
    }

    @KeepForSdk
    public Feature[] getRequiredFeatures() {
        return new Feature[0];
    }

    @Nullable
    private static BaseGmsClient.BaseConnectionCallbacks zaa(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        BaseGmsClient.BaseConnectionCallbacks baseConnectionCallbacks;
        GoogleApiClient.ConnectionCallbacks connectionCallbacks2 = connectionCallbacks;
        if (connectionCallbacks2 == null) {
            return null;
        }
        new zaf(connectionCallbacks2);
        return baseConnectionCallbacks;
    }

    @Nullable
    private static BaseGmsClient.BaseOnConnectionFailedListener zaa(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        BaseGmsClient.BaseOnConnectionFailedListener baseOnConnectionFailedListener;
        GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener2 = onConnectionFailedListener;
        if (onConnectionFailedListener2 == null) {
            return null;
        }
        new zag(onConnectionFailedListener2);
        return baseOnConnectionFailedListener;
    }

    public int getMinApkVersion() {
        return super.getMinApkVersion();
    }
}

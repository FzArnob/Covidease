package com.google.android.gms.signin.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.zad;

@KeepForSdk
public class SignInClientImpl extends GmsClient<zaf> implements zad {
    private final ClientSettings zaet;
    private Integer zaoe;
    private final boolean zasb;
    private final Bundle zasc;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private SignInClientImpl(android.content.Context r16, android.os.Looper r17, boolean r18, com.google.android.gms.common.internal.ClientSettings r19, android.os.Bundle r20, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks r21, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener r22) {
        /*
            r15 = this;
            r0 = r15
            r1 = r16
            r2 = r17
            r3 = r18
            r4 = r19
            r5 = r20
            r6 = r21
            r7 = r22
            r8 = r0
            r9 = r1
            r10 = r2
            r11 = 44
            r12 = r4
            r13 = r6
            r14 = r7
            r8.<init>(r9, r10, r11, r12, r13, r14)
            r8 = r0
            r9 = 1
            r8.zasb = r9
            r8 = r0
            r9 = r4
            r8.zaet = r9
            r8 = r0
            r9 = r5
            r8.zasc = r9
            r8 = r0
            r9 = r4
            java.lang.Integer r9 = r9.getClientSessionId()
            r8.zaoe = r9
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.signin.internal.SignInClientImpl.<init>(android.content.Context, android.os.Looper, boolean, com.google.android.gms.common.internal.ClientSettings, android.os.Bundle, com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks, com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SignInClientImpl(android.content.Context r17, android.os.Looper r18, boolean r19, com.google.android.gms.common.internal.ClientSettings r20, com.google.android.gms.signin.SignInOptions r21, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks r22, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener r23) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = r18
            r3 = r19
            r4 = r20
            r5 = r21
            r6 = r22
            r7 = r23
            r8 = r0
            r9 = r1
            r10 = r2
            r11 = 1
            r12 = r4
            r13 = r4
            android.os.Bundle r13 = createBundleFromClientSettings(r13)
            r14 = r6
            r15 = r7
            r8.<init>((android.content.Context) r9, (android.os.Looper) r10, (boolean) r11, (com.google.android.gms.common.internal.ClientSettings) r12, (android.os.Bundle) r13, (com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks) r14, (com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener) r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.signin.internal.SignInClientImpl.<init>(android.content.Context, android.os.Looper, boolean, com.google.android.gms.common.internal.ClientSettings, com.google.android.gms.signin.SignInOptions, com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks, com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener):void");
    }

    public boolean requiresSignIn() {
        return this.zasb;
    }

    public final void zaa(IAccountAccessor iAccountAccessor, boolean z) {
        try {
            ((zaf) getService()).zaa(iAccountAccessor, this.zaoe.intValue(), z);
        } catch (RemoteException e) {
            int w = Log.w("SignInClientImpl", "Remote service probably died when saveDefaultAccount is called");
        }
    }

    public final void zacw() {
        try {
            ((zaf) getService()).zam(this.zaoe.intValue());
        } catch (RemoteException e) {
            int w = Log.w("SignInClientImpl", "Remote service probably died when clearAccountFromSessionStore is called");
        }
    }

    public final void zaa(zad zad) {
        zaj zaj;
        ResolveAccountRequest resolveAccountRequest;
        zah zah;
        zad zad2 = zad;
        Object checkNotNull = Preconditions.checkNotNull(zad2, "Expecting a valid ISignInCallbacks");
        try {
            Account accountOrDefault = this.zaet.getAccountOrDefault();
            GoogleSignInAccount googleSignInAccount = null;
            if ("<<default account>>".equals(accountOrDefault.name)) {
                googleSignInAccount = Storage.getInstance(getContext()).getSavedDefaultGoogleSignInAccount();
            }
            new ResolveAccountRequest(accountOrDefault, this.zaoe.intValue(), googleSignInAccount);
            new zah(resolveAccountRequest);
            ((zaf) getService()).zaa(zah, zad2);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            int w = Log.w("SignInClientImpl", "Remote service probably died when signIn is called");
            zad zad3 = zad2;
            try {
                new zaj(8);
                zad3.zab(zaj);
            } catch (RemoteException e2) {
                int wtf = Log.wtf("SignInClientImpl", "ISignInCallbacks#onSignInComplete should be executed from the same process, unexpected RemoteException.", remoteException);
            }
        }
    }

    /* access modifiers changed from: protected */
    public String getStartServiceAction() {
        return "com.google.android.gms.signin.service.START";
    }

    /* access modifiers changed from: protected */
    public String getServiceDescriptor() {
        return "com.google.android.gms.signin.internal.ISignInService";
    }

    /* access modifiers changed from: protected */
    public Bundle getGetServiceRequestExtraArgs() {
        if (!getContext().getPackageName().equals(this.zaet.getRealClientPackageName())) {
            this.zasc.putString("com.google.android.gms.signin.internal.realClientPackageName", this.zaet.getRealClientPackageName());
        }
        return this.zasc;
    }

    public final void connect() {
        BaseGmsClient.ConnectionProgressReportCallbacks connectionProgressReportCallbacks;
        new BaseGmsClient.LegacyClientCallbackAdapter(this);
        connect(connectionProgressReportCallbacks);
    }

    @KeepForSdk
    public static Bundle createBundleFromClientSettings(ClientSettings clientSettings) {
        Bundle bundle;
        ClientSettings clientSettings2 = clientSettings;
        SignInOptions signInOptions = clientSettings2.getSignInOptions();
        Integer clientSessionId = clientSettings2.getClientSessionId();
        new Bundle();
        Bundle bundle2 = bundle;
        Bundle bundle3 = bundle2;
        bundle2.putParcelable("com.google.android.gms.signin.internal.clientRequestedAccount", clientSettings2.getAccount());
        if (clientSessionId != null) {
            bundle3.putInt(ClientSettings.KEY_CLIENT_SESSION_ID, clientSessionId.intValue());
        }
        if (signInOptions != null) {
            bundle3.putBoolean("com.google.android.gms.signin.internal.offlineAccessRequested", signInOptions.isOfflineAccessRequested());
            bundle3.putBoolean("com.google.android.gms.signin.internal.idTokenRequested", signInOptions.isIdTokenRequested());
            bundle3.putString("com.google.android.gms.signin.internal.serverClientId", signInOptions.getServerClientId());
            bundle3.putBoolean("com.google.android.gms.signin.internal.usePromptModeForAuthCode", true);
            bundle3.putBoolean("com.google.android.gms.signin.internal.forceCodeForRefreshToken", signInOptions.isForceCodeForRefreshToken());
            bundle3.putString("com.google.android.gms.signin.internal.hostedDomain", signInOptions.getHostedDomain());
            bundle3.putBoolean("com.google.android.gms.signin.internal.waitForAccessTokenRefresh", signInOptions.waitForAccessTokenRefresh());
            if (signInOptions.getAuthApiSignInModuleVersion() != null) {
                bundle3.putLong("com.google.android.gms.signin.internal.authApiSignInModuleVersion", signInOptions.getAuthApiSignInModuleVersion().longValue());
            }
            if (signInOptions.getRealClientLibraryVersion() != null) {
                bundle3.putLong("com.google.android.gms.signin.internal.realClientLibraryVersion", signInOptions.getRealClientLibraryVersion().longValue());
            }
        }
        return bundle3;
    }

    public int getMinApkVersion() {
        return 12451000;
    }

    /* access modifiers changed from: protected */
    public /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        IInterface iInterface;
        IBinder iBinder2 = iBinder;
        IBinder iBinder3 = iBinder2;
        if (iBinder2 == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder3.queryLocalInterface("com.google.android.gms.signin.internal.ISignInService");
        IInterface iInterface2 = queryLocalInterface;
        if (queryLocalInterface instanceof zaf) {
            return (zaf) iInterface2;
        }
        new zag(iBinder3);
        return iInterface;
    }
}

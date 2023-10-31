package com.google.android.gms.common.internal;

import android.os.IInterface;
import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

@Deprecated
public abstract class LegacyInternalGmsClient<T extends IInterface> extends GmsClient<T> {
    private final GmsClientEventManager zags;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public LegacyInternalGmsClient(android.content.Context r13, int r14, com.google.android.gms.common.internal.ClientSettings r15, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks r16, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener r17) {
        /*
            r12 = this;
            r0 = r12
            r1 = r13
            r2 = r14
            r3 = r15
            r4 = r16
            r5 = r17
            r6 = r0
            r7 = r1
            r8 = r1
            android.os.Looper r8 = r8.getMainLooper()
            r9 = r2
            r10 = r3
            r6.<init>((android.content.Context) r7, (android.os.Looper) r8, (int) r9, (com.google.android.gms.common.internal.ClientSettings) r10)
            r6 = r0
            com.google.android.gms.common.internal.GmsClientEventManager r7 = new com.google.android.gms.common.internal.GmsClientEventManager
            r11 = r7
            r7 = r11
            r8 = r11
            r9 = r1
            android.os.Looper r9 = r9.getMainLooper()
            r10 = r0
            r8.<init>(r9, r10)
            r6.zags = r7
            r6 = r0
            com.google.android.gms.common.internal.GmsClientEventManager r6 = r6.zags
            r7 = r4
            r6.registerConnectionCallbacks(r7)
            r6 = r0
            com.google.android.gms.common.internal.GmsClientEventManager r6 = r6.zags
            r7 = r5
            r6.registerConnectionFailedListener(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.LegacyInternalGmsClient.<init>(android.content.Context, int, com.google.android.gms.common.internal.ClientSettings, com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks, com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener):void");
    }

    public void checkAvailabilityAndConnect() {
        this.zags.enableCallbacks();
        super.checkAvailabilityAndConnect();
    }

    public void disconnect() {
        this.zags.disableCallbacks();
        super.disconnect();
    }

    public void onConnectedLocked(@NonNull T t) {
        super.onConnectedLocked(t);
        this.zags.onConnectionSuccess(getConnectionHint());
    }

    public void onConnectionSuspended(int i) {
        int i2 = i;
        super.onConnectionSuspended(i2);
        this.zags.onUnintentionalDisconnection(i2);
    }

    public void onConnectionFailed(ConnectionResult connectionResult) {
        ConnectionResult connectionResult2 = connectionResult;
        super.onConnectionFailed(connectionResult2);
        this.zags.onConnectionFailure(connectionResult2);
    }

    public void registerConnectionCallbacks(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        this.zags.registerConnectionCallbacks(connectionCallbacks);
    }

    public void registerConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this.zags.registerConnectionFailedListener(onConnectionFailedListener);
    }

    public boolean isConnectionCallbacksRegistered(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        return this.zags.isConnectionCallbacksRegistered(connectionCallbacks);
    }

    public void unregisterConnectionCallbacks(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        this.zags.unregisterConnectionCallbacks(connectionCallbacks);
    }

    public boolean isConnectionFailedListenerRegistered(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return this.zags.isConnectionFailedListenerRegistered(onConnectionFailedListener);
    }

    public void unregisterConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this.zags.unregisterConnectionFailedListener(onConnectionFailedListener);
    }

    public int getMinApkVersion() {
        return super.getMinApkVersion();
    }
}

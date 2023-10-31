package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Preconditions;
import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class zaj extends zal {
    private final SparseArray<zaa> zacw;

    public static zaj zaa(LifecycleActivity lifecycleActivity) {
        zaj zaj;
        LifecycleFragment fragment = getFragment(lifecycleActivity);
        LifecycleFragment lifecycleFragment = fragment;
        zaj zaj2 = (zaj) fragment.getCallbackOrNull("AutoManageHelper", zaj.class);
        zaj zaj3 = zaj2;
        if (zaj2 != null) {
            return zaj3;
        }
        new zaj(lifecycleFragment);
        return zaj;
    }

    private class zaa implements GoogleApiClient.OnConnectionFailedListener {
        public final int zacx;
        public final GoogleApiClient zacy;
        public final GoogleApiClient.OnConnectionFailedListener zacz;
        private final /* synthetic */ zaj zada;

        public zaa(zaj zaj, int i, GoogleApiClient googleApiClient, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            GoogleApiClient googleApiClient2 = googleApiClient;
            this.zada = zaj;
            this.zacx = i;
            this.zacy = googleApiClient2;
            this.zacz = onConnectionFailedListener;
            googleApiClient2.registerConnectionFailedListener(this);
        }

        public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            StringBuilder sb;
            ConnectionResult connectionResult2 = connectionResult;
            String valueOf = String.valueOf(connectionResult2);
            new StringBuilder(27 + String.valueOf(valueOf).length());
            int d = Log.d("AutoManageHelper", sb.append("beginFailureResolution for ").append(valueOf).toString());
            this.zada.zab(connectionResult2, this.zacx);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private zaj(LifecycleFragment lifecycleFragment) {
        super(lifecycleFragment);
        SparseArray<zaa> sparseArray;
        new SparseArray<>();
        this.zacw = sparseArray;
        this.mLifecycleFragment.addCallback("AutoManageHelper", this);
    }

    public final void zaa(int i, GoogleApiClient googleApiClient, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        StringBuilder sb;
        StringBuilder sb2;
        Object obj;
        StringBuilder sb3;
        int i2 = i;
        GoogleApiClient googleApiClient2 = googleApiClient;
        GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener2 = onConnectionFailedListener;
        Object checkNotNull = Preconditions.checkNotNull(googleApiClient2, "GoogleApiClient instance cannot be null");
        boolean z = this.zacw.indexOfKey(i2) < 0;
        new StringBuilder(54);
        Preconditions.checkState(z, sb.append("Already managing a GoogleApiClient with id ").append(i2).toString());
        zam zam = (zam) this.zadf.get();
        boolean z2 = this.mStarted;
        String valueOf = String.valueOf(zam);
        new StringBuilder(49 + String.valueOf(valueOf).length());
        int d = Log.d("AutoManageHelper", sb2.append("starting AutoManage for client ").append(i2).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(z2).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(valueOf).toString());
        new zaa(this, i2, googleApiClient2, onConnectionFailedListener2);
        this.zacw.put(i2, obj);
        if (this.mStarted && zam == null) {
            String valueOf2 = String.valueOf(googleApiClient2);
            new StringBuilder(11 + String.valueOf(valueOf2).length());
            int d2 = Log.d("AutoManageHelper", sb3.append("connecting ").append(valueOf2).toString());
            googleApiClient2.connect();
        }
    }

    public final void zaa(int i) {
        int i2 = i;
        zaa zaa2 = this.zacw.get(i2);
        this.zacw.remove(i2);
        if (zaa2 != null) {
            zaa zaa3 = zaa2;
            zaa zaa4 = zaa3;
            zaa3.zacy.unregisterConnectionFailedListener(zaa4);
            zaa4.zacy.disconnect();
        }
    }

    public void onStart() {
        StringBuilder sb;
        super.onStart();
        boolean z = this.mStarted;
        String valueOf = String.valueOf(this.zacw);
        new StringBuilder(14 + String.valueOf(valueOf).length());
        int d = Log.d("AutoManageHelper", sb.append("onStart ").append(z).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(valueOf).toString());
        if (this.zadf.get() == null) {
            for (int i = 0; i < this.zacw.size(); i++) {
                zaa zab = zab(i);
                zaa zaa2 = zab;
                if (zab != null) {
                    zaa2.zacy.connect();
                }
            }
        }
    }

    public void onStop() {
        super.onStop();
        for (int i = 0; i < this.zacw.size(); i++) {
            zaa zab = zab(i);
            zaa zaa2 = zab;
            if (zab != null) {
                zaa2.zacy.disconnect();
            }
        }
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        String str2 = str;
        FileDescriptor fileDescriptor2 = fileDescriptor;
        PrintWriter printWriter2 = printWriter;
        String[] strArr2 = strArr;
        for (int i = 0; i < this.zacw.size(); i++) {
            zaa zab = zab(i);
            zaa zaa2 = zab;
            if (zab != null) {
                PrintWriter printWriter3 = printWriter2;
                String str3 = str2;
                zaa zaa3 = zaa2;
                printWriter3.append(str3).append("GoogleApiClient #").print(zaa3.zacx);
                printWriter3.println(":");
                zaa3.zacy.dump(String.valueOf(str3).concat("  "), fileDescriptor2, printWriter3, strArr2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void zaa(ConnectionResult connectionResult, int i) {
        Throwable th;
        ConnectionResult connectionResult2 = connectionResult;
        int i2 = i;
        int w = Log.w("AutoManageHelper", "Unresolved error while connecting client. Stopping auto-manage.");
        if (i2 < 0) {
            new Exception();
            int wtf = Log.wtf("AutoManageHelper", "AutoManageLifecycleHelper received onErrorResolutionFailed callback but no failing client ID is set", th);
            return;
        }
        zaa zaa2 = this.zacw.get(i2);
        zaa zaa3 = zaa2;
        if (zaa2 != null) {
            zaa(i2);
            GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener = zaa3.zacz;
            GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener2 = onConnectionFailedListener;
            if (onConnectionFailedListener != null) {
                onConnectionFailedListener2.onConnectionFailed(connectionResult2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void zao() {
        for (int i = 0; i < this.zacw.size(); i++) {
            zaa zab = zab(i);
            zaa zaa2 = zab;
            if (zab != null) {
                zaa2.zacy.connect();
            }
        }
    }

    @Nullable
    private final zaa zab(int i) {
        int i2 = i;
        if (this.zacw.size() <= i2) {
            return null;
        }
        return this.zacw.get(this.zacw.keyAt(i2));
    }
}

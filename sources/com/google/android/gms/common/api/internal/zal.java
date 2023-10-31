package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.base.zap;
import java.util.concurrent.atomic.AtomicReference;

public abstract class zal extends LifecycleCallback implements DialogInterface.OnCancelListener {
    protected volatile boolean mStarted;
    protected final GoogleApiAvailability zacd;
    protected final AtomicReference<zam> zadf;
    private final Handler zadg;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    protected zal(LifecycleFragment lifecycleFragment) {
        this(lifecycleFragment, GoogleApiAvailability.getInstance());
    }

    /* access modifiers changed from: protected */
    public abstract void zaa(ConnectionResult connectionResult, int i);

    /* access modifiers changed from: protected */
    public abstract void zao();

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @VisibleForTesting
    private zal(LifecycleFragment lifecycleFragment, GoogleApiAvailability googleApiAvailability) {
        super(lifecycleFragment);
        AtomicReference<zam> atomicReference;
        Handler handler;
        new AtomicReference<>((Object) null);
        this.zadf = atomicReference;
        new zap(Looper.getMainLooper());
        this.zadg = handler;
        this.zacd = googleApiAvailability;
    }

    public void onCancel(DialogInterface dialogInterface) {
        ConnectionResult connectionResult;
        DialogInterface dialogInterface2 = dialogInterface;
        new ConnectionResult(13, (PendingIntent) null);
        zaa(connectionResult, zaa(this.zadf.get()));
        zaq();
    }

    public void onCreate(Bundle bundle) {
        Object obj;
        ConnectionResult connectionResult;
        Object obj2;
        Bundle bundle2 = bundle;
        super.onCreate(bundle2);
        if (bundle2 != null) {
            AtomicReference<zam> atomicReference = this.zadf;
            Bundle bundle3 = bundle2;
            Bundle bundle4 = bundle3;
            if (bundle3.getBoolean("resolving_error", false)) {
                new ConnectionResult(bundle4.getInt("failed_status"), (PendingIntent) bundle4.getParcelable("failed_resolution"));
                obj = obj2;
                new zam(connectionResult, bundle4.getInt("failed_client_id", -1));
            } else {
                obj = null;
            }
            atomicReference.set(obj);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        Bundle bundle2 = bundle;
        super.onSaveInstanceState(bundle2);
        Bundle bundle3 = bundle2;
        zam zam = this.zadf.get();
        zam zam2 = zam;
        if (zam != null) {
            bundle3.putBoolean("resolving_error", true);
            bundle3.putInt("failed_client_id", zam2.zar());
            bundle3.putInt("failed_status", zam2.getConnectionResult().getErrorCode());
            bundle3.putParcelable("failed_resolution", zam2.getConnectionResult().getResolution());
        }
    }

    public void onStart() {
        super.onStart();
        this.mStarted = true;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        zam zam;
        ConnectionResult connectionResult;
        int i3 = i2;
        Intent intent2 = intent;
        boolean z = false;
        zam zam2 = this.zadf.get();
        switch (i) {
            case 1:
                if (i3 != -1) {
                    if (i3 == 0) {
                        int i4 = 13;
                        if (intent2 != null) {
                            i4 = intent2.getIntExtra("<<ResolutionFailureErrorDetail>>", 13);
                        }
                        new ConnectionResult(i4, (PendingIntent) null);
                        new zam(connectionResult, zaa(zam2));
                        zam2 = zam;
                        this.zadf.set(zam2);
                        break;
                    }
                } else {
                    z = true;
                    break;
                }
                break;
            case 2:
                int isGooglePlayServicesAvailable = this.zacd.isGooglePlayServicesAvailable(getActivity());
                int i5 = isGooglePlayServicesAvailable;
                if (isGooglePlayServicesAvailable == 0) {
                    z = true;
                }
                if (zam2 != null) {
                    if (zam2.getConnectionResult().getErrorCode() == 18 && i5 == 18) {
                        return;
                    }
                } else {
                    return;
                }
        }
        if (z) {
            zaq();
        } else if (zam2 != null) {
            zaa(zam2.getConnectionResult(), zam2.zar());
        }
    }

    public void onStop() {
        super.onStop();
        this.mStarted = false;
    }

    /* access modifiers changed from: protected */
    public final void zaq() {
        this.zadf.set((Object) null);
        zao();
    }

    public final void zab(ConnectionResult connectionResult, int i) {
        zam zam;
        Runnable runnable;
        new zam(connectionResult, i);
        zam zam2 = zam;
        if (this.zadf.compareAndSet((Object) null, zam2)) {
            new zan(this, zam2);
            boolean post = this.zadg.post(runnable);
        }
    }

    private static int zaa(@Nullable zam zam) {
        zam zam2 = zam;
        if (zam2 == null) {
            return -1;
        }
        return zam2.zar();
    }
}

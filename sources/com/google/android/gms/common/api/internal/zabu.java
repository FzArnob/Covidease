package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.app.PendingIntent;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.CancellationException;

public class zabu extends zal {
    private TaskCompletionSource<Void> zajp;

    public static zabu zac(Activity activity) {
        zabu zabu;
        TaskCompletionSource<Void> taskCompletionSource;
        LifecycleFragment fragment = getFragment(activity);
        LifecycleFragment lifecycleFragment = fragment;
        zabu zabu2 = (zabu) fragment.getCallbackOrNull("GmsAvailabilityHelper", zabu.class);
        zabu zabu3 = zabu2;
        if (zabu2 != null) {
            if (zabu3.zajp.getTask().isComplete()) {
                new TaskCompletionSource<>();
                zabu3.zajp = taskCompletionSource;
            }
            return zabu3;
        }
        new zabu(lifecycleFragment);
        return zabu;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private zabu(LifecycleFragment lifecycleFragment) {
        super(lifecycleFragment);
        TaskCompletionSource<Void> taskCompletionSource;
        new TaskCompletionSource<>();
        this.zajp = taskCompletionSource;
        this.mLifecycleFragment.addCallback("GmsAvailabilityHelper", this);
    }

    /* access modifiers changed from: protected */
    public final void zaa(ConnectionResult connectionResult, int i) {
        Status status;
        int i2 = i;
        TaskCompletionSource<Void> taskCompletionSource = this.zajp;
        ConnectionResult connectionResult2 = connectionResult;
        new Status(connectionResult2.getErrorCode(), connectionResult2.getErrorMessage(), connectionResult2.getResolution());
        taskCompletionSource.setException(ApiExceptionUtil.fromStatus(status));
    }

    /* access modifiers changed from: protected */
    public final void zao() {
        ConnectionResult connectionResult;
        int isGooglePlayServicesAvailable = this.zacd.isGooglePlayServicesAvailable(this.mLifecycleFragment.getLifecycleActivity());
        int i = isGooglePlayServicesAvailable;
        if (isGooglePlayServicesAvailable == 0) {
            this.zajp.setResult(null);
        } else if (!this.zajp.getTask().isComplete()) {
            new ConnectionResult(i, (PendingIntent) null);
            ConnectionResult connectionResult2 = connectionResult;
            ConnectionResult connectionResult3 = connectionResult2;
            zab(connectionResult2, 0);
        }
    }

    public void onDestroy() {
        Exception exc;
        super.onDestroy();
        new CancellationException("Host activity was destroyed before Google Play services could be made available.");
        boolean trySetException = this.zajp.trySetException(exc);
    }

    public final Task<Void> getTask() {
        return this.zajp.getTask();
    }
}

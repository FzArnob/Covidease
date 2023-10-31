package com.google.android.gms.common.api.internal;

import android.content.DialogInterface;
import android.support.annotation.MainThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiActivity;

final class zan implements Runnable {
    private final zam zadj;
    final /* synthetic */ zal zadk;

    zan(zal zal, zam zam) {
        this.zadk = zal;
        this.zadj = zam;
    }

    @MainThread
    public final void run() {
        zabr zabr;
        if (this.zadk.mStarted) {
            ConnectionResult connectionResult = this.zadj.getConnectionResult();
            ConnectionResult connectionResult2 = connectionResult;
            if (connectionResult.hasResolution()) {
                this.zadk.mLifecycleFragment.startActivityForResult(GoogleApiActivity.zaa(this.zadk.getActivity(), connectionResult2.getResolution(), this.zadj.zar(), false), 1);
            } else if (this.zadk.zacd.isUserResolvableError(connectionResult2.getErrorCode())) {
                boolean zaa = this.zadk.zacd.zaa(this.zadk.getActivity(), this.zadk.mLifecycleFragment, connectionResult2.getErrorCode(), 2, this.zadk);
            } else if (connectionResult2.getErrorCode() == 18) {
                new zao(this, GoogleApiAvailability.zaa(this.zadk.getActivity(), (DialogInterface.OnCancelListener) this.zadk));
                zabq zaa2 = this.zadk.zacd.zaa(this.zadk.getActivity().getApplicationContext(), zabr);
            } else {
                this.zadk.zaa(connectionResult2, this.zadj.zar());
            }
        }
    }
}

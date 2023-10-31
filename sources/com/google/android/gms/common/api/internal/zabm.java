package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.internal.BaseGmsClient;

final class zabm implements BaseGmsClient.SignOutCallbacks {
    final /* synthetic */ GoogleApiManager.zaa zaiy;

    zabm(GoogleApiManager.zaa zaa) {
        this.zaiy = zaa;
    }

    public final void onSignOutComplete() {
        Runnable runnable;
        new zabn(this);
        boolean post = this.zaiy.zaim.handler.post(runnable);
    }
}

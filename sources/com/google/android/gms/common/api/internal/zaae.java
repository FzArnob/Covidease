package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.support.p000v4.util.C1643ArraySet;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.Preconditions;

public class zaae extends zal {
    private GoogleApiManager zabm;
    private final C1643ArraySet<zai<?>> zafp;

    public static void zaa(Activity activity, GoogleApiManager googleApiManager, zai<?> zai) {
        zaae zaae;
        zai<?> zai2 = zai;
        GoogleApiManager googleApiManager2 = googleApiManager;
        LifecycleFragment fragment = getFragment(activity);
        LifecycleFragment lifecycleFragment = fragment;
        zaae zaae2 = (zaae) fragment.getCallbackOrNull("ConnectionlessLifecycleHelper", zaae.class);
        zaae zaae3 = zaae2;
        if (zaae2 == null) {
            new zaae(lifecycleFragment);
            zaae3 = zaae;
        }
        zaae3.zabm = googleApiManager2;
        zai<?> zai3 = zai2;
        Object checkNotNull = Preconditions.checkNotNull(zai3, "ApiKey cannot be null");
        boolean add = zaae3.zafp.add(zai3);
        googleApiManager2.zaa(zaae3);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private zaae(LifecycleFragment lifecycleFragment) {
        super(lifecycleFragment);
        C1643ArraySet<zai<?>> arraySet;
        new C1643ArraySet<>();
        this.zafp = arraySet;
        this.mLifecycleFragment.addCallback("ConnectionlessLifecycleHelper", this);
    }

    public void onStart() {
        super.onStart();
        zaak();
    }

    public void onResume() {
        super.onResume();
        zaak();
    }

    public void onStop() {
        super.onStop();
        this.zabm.zab(this);
    }

    /* access modifiers changed from: protected */
    public final void zaa(ConnectionResult connectionResult, int i) {
        this.zabm.zaa(connectionResult, i);
    }

    /* access modifiers changed from: protected */
    public final void zao() {
        this.zabm.zao();
    }

    /* access modifiers changed from: package-private */
    public final C1643ArraySet<zai<?>> zaaj() {
        return this.zafp;
    }

    private final void zaak() {
        if (!this.zafp.isEmpty()) {
            this.zabm.zaa(this);
        }
    }
}

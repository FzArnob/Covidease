package com.google.android.gms.common.api.internal;

import android.support.annotation.BinderThread;
import com.google.android.gms.signin.internal.zac;
import com.google.android.gms.signin.internal.zaj;
import java.lang.ref.WeakReference;

final class zaar extends zac {
    private final WeakReference<zaak> zagk;

    zaar(zaak zaak) {
        WeakReference<zaak> weakReference;
        new WeakReference<>(zaak);
        this.zagk = weakReference;
    }

    @BinderThread
    public final void zab(zaj zaj) {
        zabf zabf;
        zaj zaj2 = zaj;
        zaak zaak = (zaak) this.zagk.get();
        zaak zaak2 = zaak;
        if (zaak != null) {
            new zaas(this, zaak2, zaak2, zaj2);
            zaak2.zaft.zaa(zabf);
        }
    }
}

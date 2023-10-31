package com.google.android.gms.common.api.internal;

import android.os.IBinder;
import com.google.android.gms.common.api.zac;
import java.lang.ref.WeakReference;
import java.util.NoSuchElementException;

final class zacr implements IBinder.DeathRecipient, zacs {
    private final WeakReference<BasePendingResult<?>> zalc;
    private final WeakReference<zac> zald;
    private final WeakReference<IBinder> zale;

    private zacr(BasePendingResult<?> basePendingResult, zac zac, IBinder iBinder) {
        WeakReference<zac> weakReference;
        WeakReference<BasePendingResult<?>> weakReference2;
        WeakReference<IBinder> weakReference3;
        new WeakReference<>(zac);
        this.zald = weakReference;
        new WeakReference<>(basePendingResult);
        this.zalc = weakReference2;
        new WeakReference<>(iBinder);
        this.zale = weakReference3;
    }

    public final void zac(BasePendingResult<?> basePendingResult) {
        BasePendingResult<?> basePendingResult2 = basePendingResult;
        zaby();
    }

    public final void binderDied() {
        zaby();
    }

    private final void zaby() {
        BasePendingResult basePendingResult = (BasePendingResult) this.zalc.get();
        zac zac = (zac) this.zald.get();
        zac zac2 = zac;
        if (!(zac == null || basePendingResult == null)) {
            zac2.remove(basePendingResult.zam().intValue());
        }
        IBinder iBinder = (IBinder) this.zale.get();
        IBinder iBinder2 = iBinder;
        if (iBinder != null) {
            try {
                boolean unlinkToDeath = iBinder2.unlinkToDeath(this, 0);
            } catch (NoSuchElementException e) {
            }
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    /* synthetic */ zacr(BasePendingResult basePendingResult, zac zac, IBinder iBinder, zacq zacq) {
        this(basePendingResult, (zac) null, iBinder);
        zac zac2 = zac;
        zacq zacq2 = zacq;
    }
}

package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import android.support.p000v4.util.C1642ArrayMap;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.AvailabilityException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.Collections;
import java.util.Map;

final class zaaa implements OnCompleteListener<Map<zai<?>, String>> {
    private final /* synthetic */ zax zafi;
    private SignInConnectionListener zafj;

    zaaa(zax zax, SignInConnectionListener signInConnectionListener) {
        this.zafi = zax;
        this.zafj = signInConnectionListener;
    }

    /* access modifiers changed from: package-private */
    public final void cancel() {
        this.zafj.onComplete();
    }

    public final void onComplete(@NonNull Task<Map<zai<?>, String>> task) {
        Map map;
        Object obj;
        Map map2;
        Task<Map<zai<?>, String>> task2 = task;
        this.zafi.zaeo.lock();
        try {
            if (!this.zafi.zafd) {
                this.zafj.onComplete();
                this.zafi.zaeo.unlock();
                return;
            }
            if (task2.isSuccessful()) {
                new C1642ArrayMap(this.zafi.zaev.size());
                Map zab = zax.zab(this.zafi, map2);
                for (zaw zak : this.zafi.zaev.values()) {
                    Object put = this.zafi.zaff.put(zak.zak(), ConnectionResult.RESULT_SUCCESS);
                }
            } else if (task2.getException() instanceof AvailabilityException) {
                AvailabilityException availabilityException = (AvailabilityException) task2.getException();
                if (this.zafi.zafb) {
                    new C1642ArrayMap(this.zafi.zaev.size());
                    Map zab2 = zax.zab(this.zafi, map);
                    for (zaw zaw : this.zafi.zaev.values()) {
                        zaw zaw2 = zaw;
                        zai zak2 = zaw.zak();
                        ConnectionResult connectionResult = availabilityException.getConnectionResult(zaw2);
                        if (this.zafi.zaa((zaw<?>) zaw2, connectionResult)) {
                            new ConnectionResult(16);
                            Object put2 = this.zafi.zaff.put(zak2, obj);
                        } else {
                            Object put3 = this.zafi.zaff.put(zak2, connectionResult);
                        }
                    }
                } else {
                    Map zab3 = zax.zab(this.zafi, availabilityException.zaj());
                }
            } else {
                int e = Log.e("ConnectionlessGAC", "Unexpected availability exception", task2.getException());
                Map zab4 = zax.zab(this.zafi, Collections.emptyMap());
            }
            if (this.zafi.isConnected()) {
                this.zafi.zafe.putAll(this.zafi.zaff);
                if (this.zafi.zaaf() == null) {
                    this.zafi.zaad();
                    this.zafi.zaae();
                    this.zafi.zaez.signalAll();
                }
            }
            this.zafj.onComplete();
            this.zafi.zaeo.unlock();
        } catch (Throwable th) {
            Throwable th2 = th;
            this.zafi.zaeo.unlock();
            throw th2;
        }
    }
}

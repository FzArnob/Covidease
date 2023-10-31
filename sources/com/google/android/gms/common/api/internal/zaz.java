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

final class zaz implements OnCompleteListener<Map<zai<?>, String>> {
    private final /* synthetic */ zax zafi;

    private zaz(zax zax) {
        this.zafi = zax;
    }

    public final void onComplete(@NonNull Task<Map<zai<?>, String>> task) {
        ConnectionResult connectionResult;
        Map map;
        Object obj;
        Map map2;
        Task<Map<zai<?>, String>> task2 = task;
        this.zafi.zaeo.lock();
        try {
            if (!this.zafi.zafd) {
                this.zafi.zaeo.unlock();
                return;
            }
            if (task2.isSuccessful()) {
                new C1642ArrayMap(this.zafi.zaeu.size());
                Map zaa = zax.zaa(this.zafi, map2);
                for (zaw zak : this.zafi.zaeu.values()) {
                    Object put = this.zafi.zafe.put(zak.zak(), ConnectionResult.RESULT_SUCCESS);
                }
            } else if (task2.getException() instanceof AvailabilityException) {
                AvailabilityException availabilityException = (AvailabilityException) task2.getException();
                if (this.zafi.zafb) {
                    new C1642ArrayMap(this.zafi.zaeu.size());
                    Map zaa2 = zax.zaa(this.zafi, map);
                    for (zaw zaw : this.zafi.zaeu.values()) {
                        zaw zaw2 = zaw;
                        zai zak2 = zaw.zak();
                        ConnectionResult connectionResult2 = availabilityException.getConnectionResult(zaw2);
                        if (this.zafi.zaa((zaw<?>) zaw2, connectionResult2)) {
                            new ConnectionResult(16);
                            Object put2 = this.zafi.zafe.put(zak2, obj);
                        } else {
                            Object put3 = this.zafi.zafe.put(zak2, connectionResult2);
                        }
                    }
                } else {
                    Map zaa3 = zax.zaa(this.zafi, (Map) availabilityException.zaj());
                }
                ConnectionResult zaa4 = zax.zaa(this.zafi, this.zafi.zaaf());
            } else {
                int e = Log.e("ConnectionlessGAC", "Unexpected availability exception", task2.getException());
                Map zaa5 = zax.zaa(this.zafi, Collections.emptyMap());
                new ConnectionResult(8);
                ConnectionResult zaa6 = zax.zaa(this.zafi, connectionResult);
            }
            if (this.zafi.zaff != null) {
                this.zafi.zafe.putAll(this.zafi.zaff);
                ConnectionResult zaa7 = zax.zaa(this.zafi, this.zafi.zaaf());
            }
            if (this.zafi.zafh == null) {
                this.zafi.zaad();
                this.zafi.zaae();
            } else {
                boolean zaa8 = zax.zaa(this.zafi, false);
                this.zafi.zaex.zac(this.zafi.zafh);
            }
            this.zafi.zaez.signalAll();
            this.zafi.zaeo.unlock();
        } catch (Throwable th) {
            Throwable th2 = th;
            this.zafi.zaeo.unlock();
            throw th2;
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    /* synthetic */ zaz(zax zax, zay zay) {
        this(zax);
        zay zay2 = zay;
    }
}

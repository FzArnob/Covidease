package com.google.android.gms.common.api;

import android.support.p000v4.util.C1642ArrayMap;
import android.text.TextUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.zai;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.List;

public class AvailabilityException extends Exception {
    private final C1642ArrayMap<zai<?>, ConnectionResult> zaay;

    public AvailabilityException(C1642ArrayMap<zai<?>, ConnectionResult> arrayMap) {
        this.zaay = arrayMap;
    }

    public ConnectionResult getConnectionResult(GoogleApi<? extends Api.ApiOptions> googleApi) {
        zai<? extends Api.ApiOptions> zak = googleApi.zak();
        Preconditions.checkArgument(this.zaay.get(zak) != null, "The given API was not part of the availability request.");
        return this.zaay.get(zak);
    }

    public final C1642ArrayMap<zai<?>, ConnectionResult> zaj() {
        return this.zaay;
    }

    public String getMessage() {
        List list;
        StringBuilder sb;
        StringBuilder sb2;
        boolean z = true;
        new ArrayList();
        List list2 = list;
        for (zai next : this.zaay.keySet()) {
            ConnectionResult connectionResult = this.zaay.get(next);
            ConnectionResult connectionResult2 = connectionResult;
            if (connectionResult.isSuccess()) {
                z = false;
            }
            String zan = next.zan();
            String valueOf = String.valueOf(connectionResult2);
            new StringBuilder(2 + String.valueOf(zan).length() + String.valueOf(valueOf).length());
            boolean add = list2.add(sb2.append(zan).append(": ").append(valueOf).toString());
        }
        new StringBuilder();
        StringBuilder sb3 = sb;
        if (z) {
            StringBuilder append = sb3.append("None of the queried APIs are available. ");
        } else {
            StringBuilder append2 = sb3.append("Some of the queried APIs are unavailable. ");
        }
        StringBuilder append3 = sb3.append(TextUtils.join("; ", list2));
        return sb3.toString();
    }
}

package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.location.LocationStatusCodes;

final class zzba extends zzan {
    private BaseImplementation.ResultHolder<Status> zzdf;

    public zzba(BaseImplementation.ResultHolder<Status> resultHolder) {
        this.zzdf = resultHolder;
    }

    public final void zza(int i, PendingIntent pendingIntent) {
        int i2 = i;
        PendingIntent pendingIntent2 = pendingIntent;
        int wtf = Log.wtf("LocationClientImpl", "Unexpected call to onRemoveGeofencesByPendingIntentResult");
    }

    public final void zza(int i, String[] strArr) {
        int i2 = i;
        String[] strArr2 = strArr;
        if (this.zzdf == null) {
            int wtf = Log.wtf("LocationClientImpl", "onAddGeofenceResult called multiple times");
            return;
        }
        this.zzdf.setResult(LocationStatusCodes.zzd(LocationStatusCodes.zzc(i2)));
        this.zzdf = null;
    }

    public final void zzb(int i, String[] strArr) {
        int i2 = i;
        String[] strArr2 = strArr;
        int wtf = Log.wtf("LocationClientImpl", "Unexpected call to onRemoveGeofencesByRequestIdsResult");
    }
}

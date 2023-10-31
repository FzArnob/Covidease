package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.location.LocationStatusCodes;

final class zzbb extends zzan {
    private BaseImplementation.ResultHolder<Status> zzdf;

    public zzbb(BaseImplementation.ResultHolder<Status> resultHolder) {
        this.zzdf = resultHolder;
    }

    private final void zze(int i) {
        int i2 = i;
        if (this.zzdf == null) {
            int wtf = Log.wtf("LocationClientImpl", "onRemoveGeofencesResult called multiple times");
            return;
        }
        this.zzdf.setResult(LocationStatusCodes.zzd(LocationStatusCodes.zzc(i2)));
        this.zzdf = null;
    }

    public final void zza(int i, PendingIntent pendingIntent) {
        PendingIntent pendingIntent2 = pendingIntent;
        zze(i);
    }

    public final void zza(int i, String[] strArr) {
        int i2 = i;
        String[] strArr2 = strArr;
        int wtf = Log.wtf("LocationClientImpl", "Unexpected call to onAddGeofencesResult");
    }

    public final void zzb(int i, String[] strArr) {
        String[] strArr2 = strArr;
        zze(i);
    }
}

package com.google.android.gms.location;

import android.content.Intent;
import android.location.Location;
import com.google.android.gms.common.internal.Constants;
import com.google.android.gms.internal.location.zzbh;
import java.util.ArrayList;
import java.util.List;

public class GeofencingEvent {
    private final int errorCode;
    private final int zzam;
    private final List<Geofence> zzan;
    private final Location zzao;

    private GeofencingEvent(int i, int i2, List<Geofence> list, Location location) {
        this.errorCode = i;
        this.zzam = i2;
        this.zzan = list;
        this.zzao = location;
    }

    public static GeofencingEvent fromIntent(Intent intent) {
        ArrayList arrayList;
        ArrayList arrayList2;
        GeofencingEvent geofencingEvent;
        Intent intent2 = intent;
        if (intent2 == null) {
            return null;
        }
        int intExtra = intent2.getIntExtra(Constants.KEY_GMS_ERROR_CODE, -1);
        int intExtra2 = intent2.getIntExtra("com.google.android.location.intent.extra.transition", -1);
        int i = intExtra2;
        int i2 = (intExtra2 == -1 || !(i == 1 || i == 2 || i == 4)) ? -1 : i;
        ArrayList arrayList3 = (ArrayList) intent2.getSerializableExtra("com.google.android.location.intent.extra.geofence_list");
        ArrayList arrayList4 = arrayList3;
        if (arrayList3 == null) {
            arrayList2 = null;
        } else {
            new ArrayList(arrayList4.size());
            ArrayList arrayList5 = arrayList;
            ArrayList arrayList6 = arrayList4;
            ArrayList arrayList7 = arrayList6;
            int size = arrayList6.size();
            int i3 = 0;
            while (i3 < size) {
                i3++;
                boolean add = arrayList5.add(zzbh.zza((byte[]) arrayList7.get(i3)));
            }
            arrayList2 = arrayList5;
        }
        new GeofencingEvent(intExtra, i2, arrayList2, (Location) intent2.getParcelableExtra("com.google.android.location.intent.extra.triggering_location"));
        return geofencingEvent;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public int getGeofenceTransition() {
        return this.zzam;
    }

    public List<Geofence> getTriggeringGeofences() {
        return this.zzan;
    }

    public Location getTriggeringLocation() {
        return this.zzao;
    }

    public boolean hasError() {
        return this.errorCode != -1;
    }
}

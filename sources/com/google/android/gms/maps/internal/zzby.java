package com.google.android.gms.maps.internal;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;

public final class zzby {
    public static void zza(@Nullable Bundle bundle, @Nullable Bundle bundle2) {
        Bundle bundle3 = bundle;
        Bundle bundle4 = bundle2;
        if (bundle3 != null && bundle4 != null) {
            Parcelable zza = zza(bundle3, "MapOptions");
            Parcelable parcelable = zza;
            if (zza != null) {
                zza(bundle4, "MapOptions", parcelable);
            }
            Parcelable zza2 = zza(bundle3, "StreetViewPanoramaOptions");
            Parcelable parcelable2 = zza2;
            if (zza2 != null) {
                zza(bundle4, "StreetViewPanoramaOptions", parcelable2);
            }
            Parcelable zza3 = zza(bundle3, "camera");
            Parcelable parcelable3 = zza3;
            if (zza3 != null) {
                zza(bundle4, "camera", parcelable3);
            }
            if (bundle3.containsKey("position")) {
                bundle4.putString("position", bundle3.getString("position"));
            }
            if (bundle3.containsKey("com.google.android.wearable.compat.extra.LOWBIT_AMBIENT")) {
                bundle4.putBoolean("com.google.android.wearable.compat.extra.LOWBIT_AMBIENT", bundle3.getBoolean("com.google.android.wearable.compat.extra.LOWBIT_AMBIENT", false));
            }
        }
    }

    private static <T extends Parcelable> T zza(@Nullable Bundle bundle, String str) {
        Bundle bundle2 = bundle;
        String str2 = str;
        if (bundle2 == null) {
            return null;
        }
        bundle2.setClassLoader(zzby.class.getClassLoader());
        Bundle bundle3 = bundle2.getBundle("map_state");
        Bundle bundle4 = bundle3;
        if (bundle3 == null) {
            return null;
        }
        bundle4.setClassLoader(zzby.class.getClassLoader());
        return bundle4.getParcelable(str2);
    }

    public static void zza(Bundle bundle, String str, Parcelable parcelable) {
        Bundle bundle2;
        Bundle bundle3 = bundle;
        String str2 = str;
        Parcelable parcelable2 = parcelable;
        bundle3.setClassLoader(zzby.class.getClassLoader());
        Bundle bundle4 = bundle3.getBundle("map_state");
        Bundle bundle5 = bundle4;
        if (bundle4 == null) {
            new Bundle();
            bundle5 = bundle2;
        }
        bundle5.setClassLoader(zzby.class.getClassLoader());
        bundle5.putParcelable(str2, parcelable2);
        bundle3.putBundle("map_state", bundle5);
    }

    private zzby() {
    }
}

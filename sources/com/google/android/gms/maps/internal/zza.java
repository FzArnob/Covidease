package com.google.android.gms.maps.internal;

public final class zza {
    public static Boolean zza(byte b) {
        switch (b) {
            case 0:
                return Boolean.FALSE;
            case 1:
                return Boolean.TRUE;
            default:
                return null;
        }
    }

    public static byte zza(Boolean bool) {
        Boolean bool2 = bool;
        if (bool2 != null) {
            return bool2.booleanValue() ? (byte) 1 : 0;
        }
        return -1;
    }
}

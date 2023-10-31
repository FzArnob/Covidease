package com.google.android.gms.common.util;

import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.HashMap;

@KeepForSdk
public class MapUtils {
    public MapUtils() {
    }

    @KeepForSdk
    public static void writeStringMapToJson(StringBuilder sb, HashMap<String, String> hashMap) {
        StringBuilder sb2 = sb;
        HashMap<String, String> hashMap2 = hashMap;
        StringBuilder append = sb2.append("{");
        boolean z = true;
        for (String next : hashMap2.keySet()) {
            if (!z) {
                StringBuilder append2 = sb2.append(",");
            } else {
                z = false;
            }
            String str = hashMap2.get(next);
            StringBuilder append3 = sb2.append("\"").append(next).append("\":");
            if (str == null) {
                StringBuilder append4 = sb2.append("null");
            } else {
                StringBuilder append5 = sb2.append("\"").append(str).append("\"");
            }
        }
        StringBuilder append6 = sb2.append("}");
    }
}

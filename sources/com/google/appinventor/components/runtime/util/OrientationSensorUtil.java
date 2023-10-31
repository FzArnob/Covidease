package com.google.appinventor.components.runtime.util;

public class OrientationSensorUtil {
    private OrientationSensorUtil() {
    }

    private static float hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(float f) {
        float f2 = f;
        float f3 = f2 % 360.0f;
        float f4 = f3;
        if (f3 == 0.0f || Math.signum(f2) == Math.signum(360.0f)) {
            return f4;
        }
        return f4 + 360.0f;
    }

    public static float normalizeAzimuth(float f) {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(f);
    }

    public static float normalizePitch(float f) {
        return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(f + 180.0f) - 180.0f;
    }

    public static float normalizeRoll(float f) {
        float max = Math.max(Math.min(f, 180.0f), -180.0f);
        float f2 = max;
        if (max >= -90.0f && f2 <= 90.0f) {
            return f2;
        }
        float f3 = 180.0f - f2;
        float f4 = f3;
        if (f3 >= 270.0f) {
            f4 -= 360.0f;
        }
        return f4;
    }
}

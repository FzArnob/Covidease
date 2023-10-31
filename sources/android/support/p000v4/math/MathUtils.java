package android.support.p000v4.math;

/* renamed from: android.support.v4.math.MathUtils */
public class MathUtils {
    private MathUtils() {
    }

    public static float clamp(float f, float f2, float f3) {
        float value = f;
        float min = f2;
        float max = f3;
        if (value < min) {
            return min;
        }
        if (value > max) {
            return max;
        }
        return value;
    }

    public static double clamp(double d, double d2, double d3) {
        double value = d;
        double min = d2;
        double max = d3;
        if (value < min) {
            return min;
        }
        if (value > max) {
            return max;
        }
        return value;
    }

    public static int clamp(int i, int i2, int i3) {
        int value = i;
        int min = i2;
        int max = i3;
        if (value < min) {
            return min;
        }
        if (value > max) {
            return max;
        }
        return value;
    }
}

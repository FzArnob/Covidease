package android.support.design.widget;

public final class MathUtils {
    public static final float DEFAULT_EPSILON = 1.0E-4f;

    private MathUtils() {
    }

    public static float dist(float x1, float y1, float x2, float y2) {
        return (float) Math.hypot((double) (x2 - x1), (double) (y2 - y1));
    }

    public static float lerp(float start, float stop, float f) {
        float amount = f;
        return ((1.0f - amount) * start) + (amount * stop);
    }

    public static boolean geq(float a, float b, float epsilon) {
        return a + epsilon >= b;
    }

    public static float distanceToFurthestCorner(float f, float f2, float f3, float f4, float f5, float f6) {
        float pointX = f;
        float pointY = f2;
        float rectLeft = f3;
        float rectTop = f4;
        float rectRight = f5;
        float rectBottom = f6;
        return max(dist(pointX, pointY, rectLeft, rectTop), dist(pointX, pointY, rectRight, rectTop), dist(pointX, pointY, rectRight, rectBottom), dist(pointX, pointY, rectLeft, rectBottom));
    }

    private static float max(float f, float f2, float f3, float f4) {
        float a = f;
        float b = f2;
        float c = f3;
        float d = f4;
        return (a <= b || a <= c || a <= d) ? (b <= c || b <= d) ? c > d ? c : d : b : a;
    }
}

package android.support.transition;

import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.util.Property;

class PathProperty<T> extends Property<T, Float> {
    private float mCurrentFraction;
    private final float mPathLength;
    private final PathMeasure mPathMeasure;
    private final PointF mPointF;
    private final float[] mPosition = new float[2];
    private final Property<T, PointF> mProperty;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    PathProperty(android.util.Property<T, android.graphics.PointF> r10, android.graphics.Path r11) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r2 = r11
            r3 = r0
            java.lang.Class<java.lang.Float> r4 = java.lang.Float.class
            r5 = r1
            java.lang.String r5 = r5.getName()
            r3.<init>(r4, r5)
            r3 = r0
            r4 = 2
            float[] r4 = new float[r4]
            r3.mPosition = r4
            r3 = r0
            android.graphics.PointF r4 = new android.graphics.PointF
            r8 = r4
            r4 = r8
            r5 = r8
            r5.<init>()
            r3.mPointF = r4
            r3 = r0
            r4 = r1
            r3.mProperty = r4
            r3 = r0
            android.graphics.PathMeasure r4 = new android.graphics.PathMeasure
            r8 = r4
            r4 = r8
            r5 = r8
            r6 = r2
            r7 = 0
            r5.<init>(r6, r7)
            r3.mPathMeasure = r4
            r3 = r0
            r4 = r0
            android.graphics.PathMeasure r4 = r4.mPathMeasure
            float r4 = r4.getLength()
            r3.mPathLength = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.transition.PathProperty.<init>(android.util.Property, android.graphics.Path):void");
    }

    public Float get(T t) {
        T t2 = t;
        return Float.valueOf(this.mCurrentFraction);
    }

    public void set(T target, Float f) {
        Float fraction = f;
        this.mCurrentFraction = fraction.floatValue();
        boolean posTan = this.mPathMeasure.getPosTan(this.mPathLength * fraction.floatValue(), this.mPosition, (float[]) null);
        this.mPointF.x = this.mPosition[0];
        this.mPointF.y = this.mPosition[1];
        this.mProperty.set(target, this.mPointF);
    }
}

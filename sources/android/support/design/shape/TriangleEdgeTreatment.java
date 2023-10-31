package android.support.design.shape;

import android.support.design.internal.Experimental;

@Experimental("The shapes API is currently experimental and subject to change")
public class TriangleEdgeTreatment extends EdgeTreatment {
    private final boolean inside;
    private final float size;

    public TriangleEdgeTreatment(float size2, boolean inside2) {
        this.size = size2;
        this.inside = inside2;
    }

    public void getEdgePath(float f, float f2, ShapePath shapePath) {
        float length = f;
        float interpolation = f2;
        ShapePath shapePath2 = shapePath;
        shapePath2.lineTo((length / 2.0f) - (this.size * interpolation), 0.0f);
        shapePath2.lineTo(length / 2.0f, this.inside ? this.size * interpolation : (-this.size) * interpolation);
        shapePath2.lineTo((length / 2.0f) + (this.size * interpolation), 0.0f);
        shapePath2.lineTo(length, 0.0f);
    }
}

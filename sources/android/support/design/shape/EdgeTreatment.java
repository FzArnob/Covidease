package android.support.design.shape;

import android.support.design.internal.Experimental;

@Experimental("The shapes API is currently experimental and subject to change")
public class EdgeTreatment {
    public EdgeTreatment() {
    }

    public void getEdgePath(float length, float f, ShapePath shapePath) {
        float f2 = f;
        shapePath.lineTo(length, 0.0f);
    }
}

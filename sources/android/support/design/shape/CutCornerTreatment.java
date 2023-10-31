package android.support.design.shape;

import android.support.design.internal.Experimental;

@Experimental("The shapes API is currently experimental and subject to change")
public class CutCornerTreatment extends CornerTreatment {
    private final float size;

    public CutCornerTreatment(float size2) {
        this.size = size2;
    }

    public void getCornerPath(float f, float f2, ShapePath shapePath) {
        float angle = f;
        float interpolation = f2;
        ShapePath shapePath2 = shapePath;
        shapePath2.reset(0.0f, this.size * interpolation);
        shapePath2.lineTo((float) (Math.sin((double) angle) * ((double) this.size) * ((double) interpolation)), (float) (Math.cos((double) angle) * ((double) this.size) * ((double) interpolation)));
    }
}

package android.support.design.animation;

import android.graphics.Matrix;
import android.util.Property;
import android.widget.ImageView;

public class ImageMatrixProperty extends Property<ImageView, Matrix> {
    private final Matrix matrix;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ImageMatrixProperty() {
        super(Matrix.class, "imageMatrixProperty");
        Matrix matrix2;
        new Matrix();
        this.matrix = matrix2;
    }

    public void set(ImageView object, Matrix value) {
        object.setImageMatrix(value);
    }

    public Matrix get(ImageView object) {
        this.matrix.set(object.getImageMatrix());
        return this.matrix;
    }
}

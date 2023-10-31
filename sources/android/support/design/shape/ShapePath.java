package android.support.design.shape;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.design.internal.Experimental;
import java.util.ArrayList;
import java.util.List;

@Experimental("The shapes API is currently experimental and subject to change")
public class ShapePath {
    public float endX;
    public float endY;
    private final List<PathOperation> operations;
    public float startX;
    public float startY;

    public ShapePath() {
        List<PathOperation> list;
        new ArrayList();
        this.operations = list;
        reset(0.0f, 0.0f);
    }

    public ShapePath(float startX2, float startY2) {
        List<PathOperation> list;
        new ArrayList();
        this.operations = list;
        reset(startX2, startY2);
    }

    public void reset(float f, float f2) {
        float startX2 = f;
        float startY2 = f2;
        this.startX = startX2;
        this.startY = startY2;
        this.endX = startX2;
        this.endY = startY2;
        this.operations.clear();
    }

    public void lineTo(float f, float f2) {
        PathLineOperation pathLineOperation;
        float x = f;
        float y = f2;
        new PathLineOperation();
        PathLineOperation operation = pathLineOperation;
        float access$002 = PathLineOperation.access$002(operation, x);
        float access$102 = PathLineOperation.access$102(operation, y);
        boolean add = this.operations.add(operation);
        this.endX = x;
        this.endY = y;
    }

    public void quadToPoint(float controlX, float controlY, float f, float f2) {
        PathQuadOperation pathQuadOperation;
        float toX = f;
        float toY = f2;
        new PathQuadOperation();
        PathQuadOperation operation = pathQuadOperation;
        operation.controlX = controlX;
        operation.controlY = controlY;
        operation.endX = toX;
        operation.endY = toY;
        boolean add = this.operations.add(operation);
        this.endX = toX;
        this.endY = toY;
    }

    public void addArc(float f, float f2, float f3, float f4, float f5, float f6) {
        PathArcOperation pathArcOperation;
        float left = f;
        float top = f2;
        float right = f3;
        float bottom = f4;
        float startAngle = f5;
        float sweepAngle = f6;
        new PathArcOperation(left, top, right, bottom);
        PathArcOperation operation = pathArcOperation;
        operation.startAngle = startAngle;
        operation.sweepAngle = sweepAngle;
        boolean add = this.operations.add(operation);
        this.endX = ((left + right) * 0.5f) + (((right - left) / 2.0f) * ((float) Math.cos(Math.toRadians((double) (startAngle + sweepAngle)))));
        this.endY = ((top + bottom) * 0.5f) + (((bottom - top) / 2.0f) * ((float) Math.sin(Math.toRadians((double) (startAngle + sweepAngle)))));
    }

    public void applyToPath(Matrix matrix, Path path) {
        Matrix transform = matrix;
        Path path2 = path;
        int size = this.operations.size();
        for (int i = 0; i < size; i++) {
            this.operations.get(i).applyToPath(transform, path2);
        }
    }

    public static abstract class PathOperation {
        protected final Matrix matrix;

        public abstract void applyToPath(Matrix matrix2, Path path);

        public PathOperation() {
            Matrix matrix2;
            new Matrix();
            this.matrix = matrix2;
        }
    }

    public static class PathLineOperation extends PathOperation {

        /* renamed from: x */
        private float f19x;

        /* renamed from: y */
        private float f20y;

        public PathLineOperation() {
        }

        static /* synthetic */ float access$002(PathLineOperation x0, float x1) {
            float f = x1;
            float f2 = f;
            x0.f19x = f2;
            return f;
        }

        static /* synthetic */ float access$102(PathLineOperation x0, float x1) {
            float f = x1;
            float f2 = f;
            x0.f20y = f2;
            return f;
        }

        public void applyToPath(Matrix matrix, Path path) {
            Matrix transform = matrix;
            Path path2 = path;
            Matrix inverse = this.matrix;
            boolean invert = transform.invert(inverse);
            path2.transform(inverse);
            path2.lineTo(this.f19x, this.f20y);
            path2.transform(transform);
        }
    }

    public static class PathQuadOperation extends PathOperation {
        public float controlX;
        public float controlY;
        public float endX;
        public float endY;

        public PathQuadOperation() {
        }

        public void applyToPath(Matrix matrix, Path path) {
            Matrix transform = matrix;
            Path path2 = path;
            Matrix inverse = this.matrix;
            boolean invert = transform.invert(inverse);
            path2.transform(inverse);
            path2.quadTo(this.controlX, this.controlY, this.endX, this.endY);
            path2.transform(transform);
        }
    }

    public static class PathArcOperation extends PathOperation {
        private static final RectF rectF;
        public float bottom;
        public float left;
        public float right;
        public float startAngle;
        public float sweepAngle;
        public float top;

        static {
            RectF rectF2;
            new RectF();
            rectF = rectF2;
        }

        public PathArcOperation(float left2, float top2, float right2, float bottom2) {
            this.left = left2;
            this.top = top2;
            this.right = right2;
            this.bottom = bottom2;
        }

        public void applyToPath(Matrix matrix, Path path) {
            Matrix transform = matrix;
            Path path2 = path;
            Matrix inverse = this.matrix;
            boolean invert = transform.invert(inverse);
            path2.transform(inverse);
            rectF.set(this.left, this.top, this.right, this.bottom);
            path2.arcTo(rectF, this.startAngle, this.sweepAngle, false);
            path2.transform(transform);
        }
    }
}

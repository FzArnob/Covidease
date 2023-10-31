package android.support.design.shape;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.support.design.internal.Experimental;
import android.support.p000v4.graphics.drawable.TintAwareDrawable;

@Experimental("The shapes API is currently experimental and subject to change")
public class MaterialShapeDrawable extends Drawable implements TintAwareDrawable {
    private int alpha;
    private final ShapePath[] cornerPaths;
    private final Matrix[] cornerTransforms;
    private final Matrix[] edgeTransforms;
    private float interpolation;
    private final Matrix matrix;
    private final Paint paint;
    private Paint.Style paintStyle;
    private final Path path;
    private final PointF pointF;
    private float scale;
    private final float[] scratch;
    private final float[] scratch2;
    private final Region scratchRegion;
    private int shadowColor;
    private int shadowElevation;
    private boolean shadowEnabled;
    private int shadowRadius;
    private final ShapePath shapePath;
    @Nullable
    private ShapePathModel shapedViewModel;
    private float strokeWidth;
    @Nullable
    private PorterDuffColorFilter tintFilter;
    private ColorStateList tintList;
    private PorterDuff.Mode tintMode;
    private final Region transparentRegion;
    private boolean useTintColorForShadow;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public MaterialShapeDrawable() {
        this((ShapePathModel) null);
    }

    public MaterialShapeDrawable(@Nullable ShapePathModel shapePathModel) {
        Paint paint2;
        Matrix matrix2;
        Path path2;
        PointF pointF2;
        ShapePath shapePath2;
        Region region;
        Region region2;
        Matrix matrix3;
        Matrix matrix4;
        ShapePath shapePath3;
        new Paint();
        this.paint = paint2;
        this.cornerTransforms = new Matrix[4];
        this.edgeTransforms = new Matrix[4];
        this.cornerPaths = new ShapePath[4];
        new Matrix();
        this.matrix = matrix2;
        new Path();
        this.path = path2;
        new PointF();
        this.pointF = pointF2;
        new ShapePath();
        this.shapePath = shapePath2;
        new Region();
        this.transparentRegion = region;
        new Region();
        this.scratchRegion = region2;
        this.scratch = new float[2];
        this.scratch2 = new float[2];
        this.shapedViewModel = null;
        this.shadowEnabled = false;
        this.useTintColorForShadow = false;
        this.interpolation = 1.0f;
        this.shadowColor = -16777216;
        this.shadowElevation = 5;
        this.shadowRadius = 10;
        this.alpha = 255;
        this.scale = 1.0f;
        this.strokeWidth = 0.0f;
        this.paintStyle = Paint.Style.FILL_AND_STROKE;
        this.tintMode = PorterDuff.Mode.SRC_IN;
        this.tintList = null;
        this.shapedViewModel = shapePathModel;
        for (int i = 0; i < 4; i++) {
            new Matrix();
            this.cornerTransforms[i] = matrix3;
            new Matrix();
            this.edgeTransforms[i] = matrix4;
            new ShapePath();
            this.cornerPaths[i] = shapePath3;
        }
    }

    private static int modulateAlpha(int paintAlpha, int i) {
        int alpha2 = i;
        return (paintAlpha * (alpha2 + (alpha2 >>> 7))) >>> 8;
    }

    @Nullable
    public ShapePathModel getShapedViewModel() {
        return this.shapedViewModel;
    }

    public void setShapedViewModel(ShapePathModel shapedViewModel2) {
        this.shapedViewModel = shapedViewModel2;
        invalidateSelf();
    }

    public ColorStateList getTintList() {
        return this.tintList;
    }

    public void setTintList(ColorStateList tintList2) {
        this.tintList = tintList2;
        updateTintFilter();
        invalidateSelf();
    }

    public void setTintMode(PorterDuff.Mode tintMode2) {
        this.tintMode = tintMode2;
        updateTintFilter();
        invalidateSelf();
    }

    public void setTint(@ColorInt int tintColor) {
        setTintList(ColorStateList.valueOf(tintColor));
    }

    public int getOpacity() {
        return -3;
    }

    public void setAlpha(@IntRange(from = 0, mo109to = 255) int alpha2) {
        this.alpha = alpha2;
        invalidateSelf();
    }

    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        ColorFilter colorFilter2 = this.paint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public Region getTransparentRegion() {
        Rect bounds = getBounds();
        boolean z = this.transparentRegion.set(bounds);
        getPath(bounds.width(), bounds.height(), this.path);
        boolean path2 = this.scratchRegion.setPath(this.path, this.transparentRegion);
        boolean op = this.transparentRegion.op(this.scratchRegion, Region.Op.DIFFERENCE);
        return this.transparentRegion;
    }

    public boolean isPointInTransparentRegion(int x, int y) {
        return getTransparentRegion().contains(x, y);
    }

    public boolean isShadowEnabled() {
        return this.shadowEnabled;
    }

    public void setShadowEnabled(boolean shadowEnabled2) {
        this.shadowEnabled = shadowEnabled2;
        invalidateSelf();
    }

    public float getInterpolation() {
        return this.interpolation;
    }

    public void setInterpolation(float interpolation2) {
        this.interpolation = interpolation2;
        invalidateSelf();
    }

    public int getShadowElevation() {
        return this.shadowElevation;
    }

    public void setShadowElevation(int shadowElevation2) {
        this.shadowElevation = shadowElevation2;
        invalidateSelf();
    }

    public int getShadowRadius() {
        return this.shadowRadius;
    }

    public void setShadowRadius(int shadowRadius2) {
        this.shadowRadius = shadowRadius2;
        invalidateSelf();
    }

    public float getScale() {
        return this.scale;
    }

    public void setScale(float scale2) {
        this.scale = scale2;
        invalidateSelf();
    }

    public void setUseTintColorForShadow(boolean useTintColorForShadow2) {
        this.useTintColorForShadow = useTintColorForShadow2;
        invalidateSelf();
    }

    public void setShadowColor(int shadowColor2) {
        this.shadowColor = shadowColor2;
        this.useTintColorForShadow = false;
        invalidateSelf();
    }

    public Paint.Style getPaintStyle() {
        return this.paintStyle;
    }

    public void setPaintStyle(Paint.Style paintStyle2) {
        this.paintStyle = paintStyle2;
        invalidateSelf();
    }

    public float getStrokeWidth() {
        return this.strokeWidth;
    }

    public void setStrokeWidth(float strokeWidth2) {
        this.strokeWidth = strokeWidth2;
        invalidateSelf();
    }

    public void draw(Canvas canvas) {
        Canvas canvas2 = canvas;
        ColorFilter colorFilter = this.paint.setColorFilter(this.tintFilter);
        int prevAlpha = this.paint.getAlpha();
        this.paint.setAlpha(modulateAlpha(prevAlpha, this.alpha));
        this.paint.setStrokeWidth(this.strokeWidth);
        this.paint.setStyle(this.paintStyle);
        if (this.shadowElevation > 0 && this.shadowEnabled) {
            this.paint.setShadowLayer((float) this.shadowRadius, 0.0f, (float) this.shadowElevation, this.shadowColor);
        }
        if (this.shapedViewModel != null) {
            getPath(canvas2.getWidth(), canvas2.getHeight(), this.path);
            canvas2.drawPath(this.path, this.paint);
        } else {
            canvas2.drawRect(0.0f, 0.0f, (float) canvas2.getWidth(), (float) canvas2.getHeight(), this.paint);
        }
        this.paint.setAlpha(prevAlpha);
    }

    public void getPathForSize(int i, int i2, Path path2) {
        int width = i;
        int height = i2;
        Path path3 = path2;
        path3.rewind();
        if (this.shapedViewModel != null) {
            for (int index = 0; index < 4; index++) {
                setCornerPathAndTransform(index, width, height);
                setEdgeTransform(index, width, height);
            }
            for (int index2 = 0; index2 < 4; index2++) {
                appendCornerPath(index2, path3);
                appendEdgePath(index2, path3);
            }
            path3.close();
        }
    }

    private void setCornerPathAndTransform(int i, int i2, int i3) {
        int index = i;
        int width = i2;
        int height = i3;
        getCoordinatesOfCorner(index, width, height, this.pointF);
        getCornerTreatmentForIndex(index).getCornerPath(angleOfCorner(index, width, height), this.interpolation, this.cornerPaths[index]);
        float prevEdgeAngle = angleOfEdge(((index - 1) + 4) % 4, width, height) + 1.5707964f;
        this.cornerTransforms[index].reset();
        this.cornerTransforms[index].setTranslate(this.pointF.x, this.pointF.y);
        boolean preRotate = this.cornerTransforms[index].preRotate((float) Math.toDegrees((double) prevEdgeAngle));
    }

    private void setEdgeTransform(int i, int width, int height) {
        int index = i;
        this.scratch[0] = this.cornerPaths[index].endX;
        this.scratch[1] = this.cornerPaths[index].endY;
        this.cornerTransforms[index].mapPoints(this.scratch);
        float edgeAngle = angleOfEdge(index, width, height);
        this.edgeTransforms[index].reset();
        this.edgeTransforms[index].setTranslate(this.scratch[0], this.scratch[1]);
        boolean preRotate = this.edgeTransforms[index].preRotate((float) Math.toDegrees((double) edgeAngle));
    }

    private void appendCornerPath(int i, Path path2) {
        int index = i;
        Path path3 = path2;
        this.scratch[0] = this.cornerPaths[index].startX;
        this.scratch[1] = this.cornerPaths[index].startY;
        this.cornerTransforms[index].mapPoints(this.scratch);
        if (index == 0) {
            path3.moveTo(this.scratch[0], this.scratch[1]);
        } else {
            path3.lineTo(this.scratch[0], this.scratch[1]);
        }
        this.cornerPaths[index].applyToPath(this.cornerTransforms[index], path3);
    }

    private void appendEdgePath(int i, Path path2) {
        int index = i;
        int nextIndex = (index + 1) % 4;
        this.scratch[0] = this.cornerPaths[index].endX;
        this.scratch[1] = this.cornerPaths[index].endY;
        this.cornerTransforms[index].mapPoints(this.scratch);
        this.scratch2[0] = this.cornerPaths[nextIndex].startX;
        this.scratch2[1] = this.cornerPaths[nextIndex].startY;
        this.cornerTransforms[nextIndex].mapPoints(this.scratch2);
        float edgeLength = (float) Math.hypot((double) (this.scratch[0] - this.scratch2[0]), (double) (this.scratch[1] - this.scratch2[1]));
        this.shapePath.reset(0.0f, 0.0f);
        getEdgeTreatmentForIndex(index).getEdgePath(edgeLength, this.interpolation, this.shapePath);
        this.shapePath.applyToPath(this.edgeTransforms[index], path2);
    }

    private CornerTreatment getCornerTreatmentForIndex(int index) {
        switch (index) {
            case 1:
                return this.shapedViewModel.getTopRightCorner();
            case 2:
                return this.shapedViewModel.getBottomRightCorner();
            case 3:
                return this.shapedViewModel.getBottomLeftCorner();
            default:
                return this.shapedViewModel.getTopLeftCorner();
        }
    }

    private EdgeTreatment getEdgeTreatmentForIndex(int index) {
        switch (index) {
            case 1:
                return this.shapedViewModel.getRightEdge();
            case 2:
                return this.shapedViewModel.getBottomEdge();
            case 3:
                return this.shapedViewModel.getLeftEdge();
            default:
                return this.shapedViewModel.getTopEdge();
        }
    }

    private void getCoordinatesOfCorner(int index, int i, int i2, PointF pointF2) {
        int width = i;
        int height = i2;
        PointF pointF3 = pointF2;
        switch (index) {
            case 1:
                pointF3.set((float) width, 0.0f);
                return;
            case 2:
                pointF3.set((float) width, (float) height);
                return;
            case 3:
                pointF3.set(0.0f, (float) height);
                return;
            default:
                pointF3.set(0.0f, 0.0f);
                return;
        }
    }

    private float angleOfCorner(int i, int i2, int i3) {
        int index = i;
        int width = i2;
        int height = i3;
        getCoordinatesOfCorner(((index - 1) + 4) % 4, width, height, this.pointF);
        float prevCornerCoordX = this.pointF.x;
        float prevCornerCoordY = this.pointF.y;
        getCoordinatesOfCorner((index + 1) % 4, width, height, this.pointF);
        float nextCornerCoordX = this.pointF.x;
        float nextCornerCoordY = this.pointF.y;
        getCoordinatesOfCorner(index, width, height, this.pointF);
        float cornerCoordX = this.pointF.x;
        float cornerCoordY = this.pointF.y;
        float prevVectorY = prevCornerCoordY - cornerCoordY;
        float nextVectorY = nextCornerCoordY - cornerCoordY;
        float angle = ((float) Math.atan2((double) prevVectorY, (double) (prevCornerCoordX - cornerCoordX))) - ((float) Math.atan2((double) nextVectorY, (double) (nextCornerCoordX - cornerCoordX)));
        if (angle < 0.0f) {
            angle = (float) (((double) angle) + 6.283185307179586d);
        }
        return angle;
    }

    private float angleOfEdge(int i, int i2, int i3) {
        int index = i;
        int width = i2;
        int height = i3;
        getCoordinatesOfCorner(index, width, height, this.pointF);
        float startCornerCoordX = this.pointF.x;
        float startCornerCoordY = this.pointF.y;
        getCoordinatesOfCorner((index + 1) % 4, width, height, this.pointF);
        float endCornerCoordX = this.pointF.x;
        return (float) Math.atan2((double) (this.pointF.y - startCornerCoordY), (double) (endCornerCoordX - startCornerCoordX));
    }

    private void getPath(int i, int i2, Path path2) {
        int width = i;
        int height = i2;
        Path path3 = path2;
        getPathForSize(width, height, path3);
        if (this.scale != 1.0f) {
            this.matrix.reset();
            this.matrix.setScale(this.scale, this.scale, (float) (width / 2), (float) (height / 2));
            path3.transform(this.matrix);
        }
    }

    private void updateTintFilter() {
        PorterDuffColorFilter porterDuffColorFilter;
        if (this.tintList == null || this.tintMode == null) {
            this.tintFilter = null;
            return;
        }
        int color = this.tintList.getColorForState(getState(), 0);
        new PorterDuffColorFilter(color, this.tintMode);
        this.tintFilter = porterDuffColorFilter;
        if (this.useTintColorForShadow) {
            this.shadowColor = color;
        }
    }
}

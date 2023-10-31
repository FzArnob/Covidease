package android.support.design.bottomappbar;

import android.support.design.shape.EdgeTreatment;
import android.support.design.shape.ShapePath;

public class BottomAppBarTopEdgeTreatment extends EdgeTreatment {
    private static final int ANGLE_LEFT = 180;
    private static final int ANGLE_UP = 270;
    private static final int ARC_HALF = 180;
    private static final int ARC_QUARTER = 90;
    private float cradleVerticalOffset;
    private float fabDiameter;
    private float fabMargin;
    private float horizontalOffset;
    private float roundedCornerRadius;

    public BottomAppBarTopEdgeTreatment(float fabMargin2, float roundedCornerRadius2, float f) {
        Throwable th;
        float cradleVerticalOffset2 = f;
        this.fabMargin = fabMargin2;
        this.roundedCornerRadius = roundedCornerRadius2;
        this.cradleVerticalOffset = cradleVerticalOffset2;
        if (cradleVerticalOffset2 < 0.0f) {
            Throwable th2 = th;
            new IllegalArgumentException("cradleVerticalOffset must be positive.");
            throw th2;
        }
        this.horizontalOffset = 0.0f;
    }

    public void getEdgePath(float f, float f2, ShapePath shapePath) {
        float length = f;
        float interpolation = f2;
        ShapePath shapePath2 = shapePath;
        if (this.fabDiameter == 0.0f) {
            shapePath2.lineTo(length, 0.0f);
            return;
        }
        float cradleRadius = ((this.fabMargin * 2.0f) + this.fabDiameter) / 2.0f;
        float roundedCornerOffset = interpolation * this.roundedCornerRadius;
        float middle = (length / 2.0f) + this.horizontalOffset;
        float verticalOffset = (interpolation * this.cradleVerticalOffset) + ((1.0f - interpolation) * cradleRadius);
        if (verticalOffset / cradleRadius >= 1.0f) {
            shapePath2.lineTo(length, 0.0f);
            return;
        }
        float distanceBetweenCenters = cradleRadius + roundedCornerOffset;
        float distanceY = verticalOffset + roundedCornerOffset;
        float distanceX = (float) Math.sqrt((double) ((distanceBetweenCenters * distanceBetweenCenters) - (distanceY * distanceY)));
        float leftRoundedCornerCircleX = middle - distanceX;
        float rightRoundedCornerCircleX = middle + distanceX;
        float cornerRadiusArcLength = (float) Math.toDegrees(Math.atan((double) (distanceX / distanceY)));
        float cutoutArcOffset = 90.0f - cornerRadiusArcLength;
        shapePath2.lineTo(leftRoundedCornerCircleX - roundedCornerOffset, 0.0f);
        shapePath2.addArc(leftRoundedCornerCircleX - roundedCornerOffset, 0.0f, leftRoundedCornerCircleX + roundedCornerOffset, roundedCornerOffset * 2.0f, 270.0f, cornerRadiusArcLength);
        shapePath2.addArc(middle - cradleRadius, (-cradleRadius) - verticalOffset, middle + cradleRadius, cradleRadius - verticalOffset, 180.0f - cutoutArcOffset, (cutoutArcOffset * 2.0f) - 180.0f);
        shapePath2.addArc(rightRoundedCornerCircleX - roundedCornerOffset, 0.0f, rightRoundedCornerCircleX + roundedCornerOffset, roundedCornerOffset * 2.0f, 270.0f - cornerRadiusArcLength, cornerRadiusArcLength);
        shapePath2.lineTo(length, 0.0f);
    }

    /* access modifiers changed from: package-private */
    public void setHorizontalOffset(float horizontalOffset2) {
        float f = horizontalOffset2;
        this.horizontalOffset = f;
    }

    /* access modifiers changed from: package-private */
    public float getHorizontalOffset() {
        return this.horizontalOffset;
    }

    /* access modifiers changed from: package-private */
    public float getCradleVerticalOffset() {
        return this.cradleVerticalOffset;
    }

    /* access modifiers changed from: package-private */
    public void setCradleVerticalOffset(float cradleVerticalOffset2) {
        float f = cradleVerticalOffset2;
        this.cradleVerticalOffset = f;
    }

    /* access modifiers changed from: package-private */
    public float getFabDiameter() {
        return this.fabDiameter;
    }

    /* access modifiers changed from: package-private */
    public void setFabDiameter(float fabDiameter2) {
        float f = fabDiameter2;
        this.fabDiameter = f;
    }

    /* access modifiers changed from: package-private */
    public float getFabCradleMargin() {
        return this.fabMargin;
    }

    /* access modifiers changed from: package-private */
    public void setFabCradleMargin(float fabMargin2) {
        float f = fabMargin2;
        this.fabMargin = f;
    }

    /* access modifiers changed from: package-private */
    public float getFabCradleRoundedCornerRadius() {
        return this.roundedCornerRadius;
    }

    /* access modifiers changed from: package-private */
    public void setFabCradleRoundedCornerRadius(float roundedCornerRadius2) {
        float f = roundedCornerRadius2;
        this.roundedCornerRadius = f;
    }
}

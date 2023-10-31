package android.support.transition;

import android.graphics.Path;

public class ArcMotion extends PathMotion {
    private static final float DEFAULT_MAX_ANGLE_DEGREES = 70.0f;
    private static final float DEFAULT_MAX_TANGENT = ((float) Math.tan(Math.toRadians(35.0d)));
    private static final float DEFAULT_MIN_ANGLE_DEGREES = 0.0f;
    private float mMaximumAngle = DEFAULT_MAX_ANGLE_DEGREES;
    private float mMaximumTangent = DEFAULT_MAX_TANGENT;
    private float mMinimumHorizontalAngle = 0.0f;
    private float mMinimumHorizontalTangent = 0.0f;
    private float mMinimumVerticalAngle = 0.0f;
    private float mMinimumVerticalTangent = 0.0f;

    public ArcMotion() {
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ArcMotion(android.content.Context r14, android.util.AttributeSet r15) {
        /*
            r13 = this;
            r0 = r13
            r1 = r14
            r2 = r15
            r8 = r0
            r9 = r1
            r10 = r2
            r8.<init>(r9, r10)
            r8 = r0
            r9 = 0
            r8.mMinimumHorizontalAngle = r9
            r8 = r0
            r9 = 0
            r8.mMinimumVerticalAngle = r9
            r8 = r0
            r9 = 1116471296(0x428c0000, float:70.0)
            r8.mMaximumAngle = r9
            r8 = r0
            r9 = 0
            r8.mMinimumHorizontalTangent = r9
            r8 = r0
            r9 = 0
            r8.mMinimumVerticalTangent = r9
            r8 = r0
            float r9 = DEFAULT_MAX_TANGENT
            r8.mMaximumTangent = r9
            r8 = r1
            r9 = r2
            int[] r10 = android.support.transition.Styleable.ARC_MOTION
            android.content.res.TypedArray r8 = r8.obtainStyledAttributes(r9, r10)
            r3 = r8
            r8 = r2
            org.xmlpull.v1.XmlPullParser r8 = (org.xmlpull.v1.XmlPullParser) r8
            r4 = r8
            r8 = r3
            r9 = r4
            java.lang.String r10 = "minimumVerticalAngle"
            r11 = 1
            r12 = 0
            float r8 = android.support.p000v4.content.res.TypedArrayUtils.getNamedFloat(r8, r9, r10, r11, r12)
            r5 = r8
            r8 = r0
            r9 = r5
            r8.setMinimumVerticalAngle(r9)
            r8 = r3
            r9 = r4
            java.lang.String r10 = "minimumHorizontalAngle"
            r11 = 0
            r12 = 0
            float r8 = android.support.p000v4.content.res.TypedArrayUtils.getNamedFloat(r8, r9, r10, r11, r12)
            r6 = r8
            r8 = r0
            r9 = r6
            r8.setMinimumHorizontalAngle(r9)
            r8 = r3
            r9 = r4
            java.lang.String r10 = "maximumAngle"
            r11 = 2
            r12 = 1116471296(0x428c0000, float:70.0)
            float r8 = android.support.p000v4.content.res.TypedArrayUtils.getNamedFloat(r8, r9, r10, r11, r12)
            r7 = r8
            r8 = r0
            r9 = r7
            r8.setMaximumAngle(r9)
            r8 = r3
            r8.recycle()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.transition.ArcMotion.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    public void setMinimumHorizontalAngle(float f) {
        float angleInDegrees = f;
        this.mMinimumHorizontalAngle = angleInDegrees;
        this.mMinimumHorizontalTangent = toTangent(angleInDegrees);
    }

    public float getMinimumHorizontalAngle() {
        return this.mMinimumHorizontalAngle;
    }

    public void setMinimumVerticalAngle(float f) {
        float angleInDegrees = f;
        this.mMinimumVerticalAngle = angleInDegrees;
        this.mMinimumVerticalTangent = toTangent(angleInDegrees);
    }

    public float getMinimumVerticalAngle() {
        return this.mMinimumVerticalAngle;
    }

    public void setMaximumAngle(float f) {
        float angleInDegrees = f;
        this.mMaximumAngle = angleInDegrees;
        this.mMaximumTangent = toTangent(angleInDegrees);
    }

    public float getMaximumAngle() {
        return this.mMaximumAngle;
    }

    private static float toTangent(float f) {
        Throwable th;
        float arcInDegrees = f;
        if (arcInDegrees >= 0.0f && arcInDegrees <= 90.0f) {
            return (float) Math.tan(Math.toRadians((double) (arcInDegrees / 2.0f)));
        }
        Throwable th2 = th;
        new IllegalArgumentException("Arc must be between 0 and 90 degrees");
        throw th2;
    }

    public Path getPath(float f, float f2, float f3, float f4) {
        Path path;
        float ex;
        float ey;
        float ey2;
        float ex2;
        float minimumArcDist2;
        float startX = f;
        float startY = f2;
        float endX = f3;
        float endY = f4;
        new Path();
        Path path2 = path;
        path2.moveTo(startX, startY);
        float deltaX = endX - startX;
        float deltaY = endY - startY;
        float h2 = (deltaX * deltaX) + (deltaY * deltaY);
        float dx = (startX + endX) / 2.0f;
        float dy = (startY + endY) / 2.0f;
        float midDist2 = h2 * 0.25f;
        boolean isMovingUpwards = startY > endY;
        if (Math.abs(deltaX) < Math.abs(deltaY)) {
            float eDistY = Math.abs(h2 / (2.0f * deltaY));
            if (isMovingUpwards) {
                ey2 = endY + eDistY;
                ex2 = endX;
            } else {
                ey2 = startY + eDistY;
                ex2 = startX;
            }
            minimumArcDist2 = midDist2 * this.mMinimumVerticalTangent * this.mMinimumVerticalTangent;
        } else {
            float eDistX = h2 / (2.0f * deltaX);
            if (isMovingUpwards) {
                ex = startX + eDistX;
                ey = startY;
            } else {
                ex = endX - eDistX;
                ey = endY;
            }
            minimumArcDist2 = midDist2 * this.mMinimumHorizontalTangent * this.mMinimumHorizontalTangent;
        }
        float arcDistX = dx - ex2;
        float arcDistY = dy - ey2;
        float arcDist2 = (arcDistX * arcDistX) + (arcDistY * arcDistY);
        float maximumArcDist2 = midDist2 * this.mMaximumTangent * this.mMaximumTangent;
        float newArcDistance2 = 0.0f;
        if (arcDist2 < minimumArcDist2) {
            newArcDistance2 = minimumArcDist2;
        } else if (arcDist2 > maximumArcDist2) {
            newArcDistance2 = maximumArcDist2;
        }
        if (newArcDistance2 != 0.0f) {
            float ratio = (float) Math.sqrt((double) (newArcDistance2 / arcDist2));
            ex2 = dx + (ratio * (ex2 - dx));
            ey2 = dy + (ratio * (ey2 - dy));
        }
        path2.cubicTo((startX + ex2) / 2.0f, (startY + ey2) / 2.0f, (ex2 + endX) / 2.0f, (ey2 + endY) / 2.0f, endX, endY);
        return path2;
    }
}

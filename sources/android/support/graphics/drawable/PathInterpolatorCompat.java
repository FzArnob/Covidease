package android.support.graphics.drawable;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.RestrictTo;
import android.support.p000v4.content.res.TypedArrayUtils;
import android.support.p000v4.graphics.PathParser;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.animation.Interpolator;
import org.xmlpull.v1.XmlPullParser;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class PathInterpolatorCompat implements Interpolator {
    public static final double EPSILON = 1.0E-5d;
    public static final int MAX_NUM_POINTS = 3000;
    private static final float PRECISION = 0.002f;

    /* renamed from: mX */
    private float[] f21mX;

    /* renamed from: mY */
    private float[] f22mY;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public PathInterpolatorCompat(android.content.Context r10, android.util.AttributeSet r11, org.xmlpull.v1.XmlPullParser r12) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r2 = r11
            r3 = r12
            r4 = r0
            r5 = r1
            android.content.res.Resources r5 = r5.getResources()
            r6 = r1
            android.content.res.Resources$Theme r6 = r6.getTheme()
            r7 = r2
            r8 = r3
            r4.<init>(r5, r6, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.graphics.drawable.PathInterpolatorCompat.<init>(android.content.Context, android.util.AttributeSet, org.xmlpull.v1.XmlPullParser):void");
    }

    public PathInterpolatorCompat(Resources res, Resources.Theme theme, AttributeSet attrs, XmlPullParser parser) {
        TypedArray a = TypedArrayUtils.obtainAttributes(res, theme, attrs, AndroidResources.STYLEABLE_PATH_INTERPOLATOR);
        parseInterpolatorFromTypeArray(a, parser);
        a.recycle();
    }

    private void parseInterpolatorFromTypeArray(TypedArray typedArray, XmlPullParser xmlPullParser) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        StringBuilder sb;
        TypedArray a = typedArray;
        XmlPullParser parser = xmlPullParser;
        if (TypedArrayUtils.hasAttribute(parser, "pathData")) {
            String pathData = TypedArrayUtils.getNamedString(a, parser, "pathData", 4);
            Path path = PathParser.createPathFromPathData(pathData);
            if (path == null) {
                Throwable th5 = th4;
                new StringBuilder();
                new InflateException(sb.append("The path is null, which is created from ").append(pathData).toString());
                throw th5;
            }
            initPath(path);
        } else if (!TypedArrayUtils.hasAttribute(parser, "controlX1")) {
            Throwable th6 = th3;
            new InflateException("pathInterpolator requires the controlX1 attribute");
            throw th6;
        } else if (!TypedArrayUtils.hasAttribute(parser, "controlY1")) {
            Throwable th7 = th2;
            new InflateException("pathInterpolator requires the controlY1 attribute");
            throw th7;
        } else {
            float x1 = TypedArrayUtils.getNamedFloat(a, parser, "controlX1", 0, 0.0f);
            float y1 = TypedArrayUtils.getNamedFloat(a, parser, "controlY1", 1, 0.0f);
            boolean hasX2 = TypedArrayUtils.hasAttribute(parser, "controlX2");
            if (hasX2 != TypedArrayUtils.hasAttribute(parser, "controlY2")) {
                Throwable th8 = th;
                new InflateException("pathInterpolator requires both controlX2 and controlY2 for cubic Beziers.");
                throw th8;
            } else if (!hasX2) {
                initQuad(x1, y1);
            } else {
                initCubic(x1, y1, TypedArrayUtils.getNamedFloat(a, parser, "controlX2", 2, 0.0f), TypedArrayUtils.getNamedFloat(a, parser, "controlY2", 3, 0.0f));
            }
        }
    }

    private void initQuad(float controlX, float controlY) {
        Path path;
        new Path();
        Path path2 = path;
        path2.moveTo(0.0f, 0.0f);
        path2.quadTo(controlX, controlY, 1.0f, 1.0f);
        initPath(path2);
    }

    private void initCubic(float x1, float y1, float x2, float y2) {
        Path path;
        new Path();
        Path path2 = path;
        path2.moveTo(0.0f, 0.0f);
        path2.cubicTo(x1, y1, x2, y2, 1.0f, 1.0f);
        initPath(path2);
    }

    private void initPath(Path path) {
        PathMeasure pathMeasure;
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        Throwable th3;
        StringBuilder sb2;
        Throwable th4;
        StringBuilder sb3;
        new PathMeasure(path, false);
        PathMeasure pathMeasure2 = pathMeasure;
        float pathLength = pathMeasure2.getLength();
        int numPoints = Math.min(MAX_NUM_POINTS, ((int) (pathLength / PRECISION)) + 1);
        if (numPoints <= 0) {
            Throwable th5 = th4;
            new StringBuilder();
            new IllegalArgumentException(sb3.append("The Path has a invalid length ").append(pathLength).toString());
            throw th5;
        }
        this.f21mX = new float[numPoints];
        this.f22mY = new float[numPoints];
        float[] position = new float[2];
        for (int i = 0; i < numPoints; i++) {
            boolean posTan = pathMeasure2.getPosTan((((float) i) * pathLength) / ((float) (numPoints - 1)), position, (float[]) null);
            this.f21mX[i] = position[0];
            this.f22mY[i] = position[1];
        }
        if (((double) Math.abs(this.f21mX[0])) > 1.0E-5d || ((double) Math.abs(this.f22mY[0])) > 1.0E-5d || ((double) Math.abs(this.f21mX[numPoints - 1] - 1.0f)) > 1.0E-5d || ((double) Math.abs(this.f22mY[numPoints - 1] - 1.0f)) > 1.0E-5d) {
            Throwable th6 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("The Path must start at (0,0) and end at (1,1) start: ").append(this.f21mX[0]).append(",").append(this.f22mY[0]).append(" end:").append(this.f21mX[numPoints - 1]).append(",").append(this.f22mY[numPoints - 1]).toString());
            throw th6;
        }
        float prevX = 0.0f;
        int componentIndex = 0;
        for (int i2 = 0; i2 < numPoints; i2++) {
            int i3 = componentIndex;
            componentIndex++;
            float x = this.f21mX[i3];
            if (x < prevX) {
                Throwable th7 = th3;
                new StringBuilder();
                new IllegalArgumentException(sb2.append("The Path cannot loop back on itself, x :").append(x).toString());
                throw th7;
            }
            this.f21mX[i2] = x;
            prevX = x;
        }
        if (pathMeasure2.nextContour()) {
            Throwable th8 = th2;
            new IllegalArgumentException("The Path should be continuous, can't have 2+ contours");
            throw th8;
        }
    }

    public float getInterpolation(float f) {
        float t = f;
        if (t <= 0.0f) {
            return 0.0f;
        }
        if (t >= 1.0f) {
            return 1.0f;
        }
        int startIndex = 0;
        int endIndex = this.f21mX.length - 1;
        while (endIndex - startIndex > 1) {
            int midIndex = (startIndex + endIndex) / 2;
            if (t < this.f21mX[midIndex]) {
                endIndex = midIndex;
            } else {
                startIndex = midIndex;
            }
        }
        float xRange = this.f21mX[endIndex] - this.f21mX[startIndex];
        if (xRange == 0.0f) {
            return this.f22mY[startIndex];
        }
        float fraction = (t - this.f21mX[startIndex]) / xRange;
        float startY = this.f22mY[startIndex];
        return startY + (fraction * (this.f22mY[endIndex] - startY));
    }
}

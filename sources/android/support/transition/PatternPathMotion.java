package android.support.transition;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.p000v4.content.res.TypedArrayUtils;
import android.support.p000v4.graphics.PathParser;
import android.util.AttributeSet;
import org.xmlpull.v1.XmlPullParser;

public class PatternPathMotion extends PathMotion {
    private Path mOriginalPatternPath;
    private final Path mPatternPath;
    private final Matrix mTempMatrix;

    public PatternPathMotion() {
        Path path;
        Matrix matrix;
        new Path();
        this.mPatternPath = path;
        new Matrix();
        this.mTempMatrix = matrix;
        this.mPatternPath.lineTo(1.0f, 0.0f);
        this.mOriginalPatternPath = this.mPatternPath;
    }

    public PatternPathMotion(Context context, AttributeSet attributeSet) {
        Path path;
        Matrix matrix;
        Throwable th;
        AttributeSet attrs = attributeSet;
        new Path();
        this.mPatternPath = path;
        new Matrix();
        this.mTempMatrix = matrix;
        TypedArray a = context.obtainStyledAttributes(attrs, Styleable.PATTERN_PATH_MOTION);
        try {
            String pathData = TypedArrayUtils.getNamedString(a, (XmlPullParser) attrs, "patternPathData", 0);
            if (pathData == null) {
                Throwable th2 = th;
                new RuntimeException("pathData must be supplied for patternPathMotion");
                throw th2;
            }
            setPatternPath(PathParser.createPathFromPathData(pathData));
            a.recycle();
        } catch (Throwable th3) {
            Throwable th4 = th3;
            a.recycle();
            throw th4;
        }
    }

    public PatternPathMotion(Path patternPath) {
        Path path;
        Matrix matrix;
        new Path();
        this.mPatternPath = path;
        new Matrix();
        this.mTempMatrix = matrix;
        setPatternPath(patternPath);
    }

    public Path getPatternPath() {
        return this.mOriginalPatternPath;
    }

    public void setPatternPath(Path path) {
        PathMeasure pathMeasure;
        Throwable th;
        Path patternPath = path;
        new PathMeasure(patternPath, false);
        PathMeasure pathMeasure2 = pathMeasure;
        float[] pos = new float[2];
        boolean posTan = pathMeasure2.getPosTan(pathMeasure2.getLength(), pos, (float[]) null);
        float endX = pos[0];
        float endY = pos[1];
        boolean posTan2 = pathMeasure2.getPosTan(0.0f, pos, (float[]) null);
        float startX = pos[0];
        float startY = pos[1];
        if (startX == endX && startY == endY) {
            Throwable th2 = th;
            new IllegalArgumentException("pattern must not end at the starting point");
            throw th2;
        }
        this.mTempMatrix.setTranslate(-startX, -startY);
        float dx = endX - startX;
        float dy = endY - startY;
        float scale = 1.0f / distance(dx, dy);
        boolean postScale = this.mTempMatrix.postScale(scale, scale);
        boolean postRotate = this.mTempMatrix.postRotate((float) Math.toDegrees(-Math.atan2((double) dy, (double) dx)));
        patternPath.transform(this.mTempMatrix, this.mPatternPath);
        this.mOriginalPatternPath = patternPath;
    }

    public Path getPath(float f, float f2, float endX, float endY) {
        Path path;
        float startX = f;
        float startY = f2;
        float dx = endX - startX;
        float dy = endY - startY;
        float length = distance(dx, dy);
        double angle = Math.atan2((double) dy, (double) dx);
        this.mTempMatrix.setScale(length, length);
        boolean postRotate = this.mTempMatrix.postRotate((float) Math.toDegrees(angle));
        boolean postTranslate = this.mTempMatrix.postTranslate(startX, startY);
        new Path();
        Path path2 = path;
        this.mPatternPath.transform(this.mTempMatrix, path2);
        return path2;
    }

    private static float distance(float f, float f2) {
        float x = f;
        float y = f2;
        return (float) Math.sqrt((double) ((x * x) + (y * y)));
    }
}

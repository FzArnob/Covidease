package android.support.p000v4.graphics;

import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* renamed from: android.support.v4.graphics.PathUtils */
public final class PathUtils {
    @RequiresApi(26)
    @NonNull
    public static Collection<PathSegment> flatten(@NonNull Path path) {
        return flatten(path, 0.5f);
    }

    @RequiresApi(26)
    @NonNull
    public static Collection<PathSegment> flatten(@NonNull Path path, @FloatRange(from = 0.0d) float error) {
        List<PathSegment> list;
        Object obj;
        PointF pointF;
        PointF pointF2;
        float[] pathData = path.approximate(error);
        int pointCount = pathData.length / 3;
        new ArrayList<>(pointCount);
        List<PathSegment> segments = list;
        for (int i = 1; i < pointCount; i++) {
            int index = i * 3;
            int prevIndex = (i - 1) * 3;
            float d = pathData[index];
            float x = pathData[index + 1];
            float y = pathData[index + 2];
            float pd = pathData[prevIndex];
            float px = pathData[prevIndex + 1];
            float py = pathData[prevIndex + 2];
            if (!(d == pd || (x == px && y == py))) {
                new PointF(px, py);
                new PointF(x, y);
                new PathSegment(pointF, pd, pointF2, d);
                boolean add = segments.add(obj);
            }
        }
        return segments;
    }

    private PathUtils() {
    }
}

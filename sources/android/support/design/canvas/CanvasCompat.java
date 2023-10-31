package android.support.design.canvas;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class CanvasCompat {
    private CanvasCompat() {
    }

    public static int saveLayerAlpha(Canvas canvas, RectF rectF, int i) {
        Canvas canvas2 = canvas;
        RectF bounds = rectF;
        int alpha = i;
        if (Build.VERSION.SDK_INT > 21) {
            return canvas2.saveLayerAlpha(bounds, alpha);
        }
        return canvas2.saveLayerAlpha(bounds, alpha, 31);
    }

    public static int saveLayerAlpha(Canvas canvas, float f, float f2, float f3, float f4, int i) {
        Canvas canvas2 = canvas;
        float left = f;
        float top = f2;
        float right = f3;
        float bottom = f4;
        int alpha = i;
        if (Build.VERSION.SDK_INT > 21) {
            return canvas2.saveLayerAlpha(left, top, right, bottom, alpha);
        }
        return canvas2.saveLayerAlpha(left, top, right, bottom, alpha, 31);
    }
}

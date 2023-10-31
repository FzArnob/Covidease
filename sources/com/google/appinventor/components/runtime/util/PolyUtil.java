package com.google.appinventor.components.runtime.util;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

public class PolyUtil {
    private final Path hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private final RectF f573hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    public PolyUtil() {
        Path path;
        RectF rectF;
        new Path();
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = path;
        new RectF();
        this.f573hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = rectF;
    }

    public void drawPolygon(@NonNull Canvas canvas, @IntRange(from = 3) int i, float f, float f2, @FloatRange(from = 0.0d, fromInclusive = false) float f3, @FloatRange(from = 0.0d) float f4, float f5, @NonNull Paint paint) {
        constructPolygonPath(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, i, f, f2, f3, f4, f5);
        canvas.drawPath(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, paint);
    }

    public void constructPolygonPath(@NonNull Path path, @IntRange(from = 3) int i, float f, float f2, @FloatRange(from = 0.0d, fromInclusive = false) float f3, @FloatRange(from = 0.0d) float f4, float f5) {
        Matrix matrix;
        Path path2 = path;
        int i2 = i;
        float f6 = f;
        float f7 = f2;
        float f8 = f3;
        float f9 = f4;
        float f10 = f5;
        path2.reset();
        float cos = (float) (((double) f8) * Math.cos(wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(180.0d / ((double) i2))));
        float f11 = cos;
        if (cos < f9) {
            path2.addCircle(f6, f7, f11, Path.Direction.CW);
            return;
        }
        if (((double) Math.abs(f9)) < 0.01d) {
            float f12 = f8;
            int i3 = i2;
            Path path3 = path2;
            for (int i4 = 0; i4 < i3; i4++) {
                double d = ((double) i4) * (360.0d / ((double) i3));
                float cos2 = (float) (((double) f6) + (((double) f12) * Math.cos(wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(d))));
                float sin = (float) (((double) f7) + (((double) f12) * Math.sin(wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(d))));
                if (i4 == 0) {
                    path3.moveTo(cos2, sin);
                } else {
                    path3.lineTo(cos2, sin);
                }
            }
            path3.close();
        } else {
            float f13 = f9;
            int i5 = i2;
            Path path4 = path2;
            double d2 = 90.0d - (180.0d / ((double) i5));
            float f14 = (float) (90.0d - d2);
            double sin2 = ((double) f8) - (((double) f13) / Math.sin(wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(d2)));
            for (int i6 = 0; i6 < i5; i6++) {
                double d3 = ((double) i6) * (360.0d / ((double) i5));
                float cos3 = (float) (((double) f6) + (sin2 * Math.cos(wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(d3))));
                float sin3 = (float) (((double) f7) + (sin2 * Math.sin(wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(d3))));
                this.f573hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.set(cos3 - f13, sin3 - f13, cos3 + f13, sin3 + f13);
                path4.arcTo(this.f573hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, (float) (d3 - ((double) f14)), f14 * 2.0f);
            }
            path4.close();
        }
        new Matrix();
        Matrix matrix2 = matrix;
        matrix2.setRotate(f10, f6, f7);
        path2.transform(matrix2);
    }

    private static double wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(double d) {
        return (d * 6.283185307179586d) / 360.0d;
    }
}

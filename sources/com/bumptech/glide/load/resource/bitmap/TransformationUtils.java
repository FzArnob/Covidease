package com.bumptech.glide.load.resource.bitmap;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.media.ExifInterface;
import android.os.Build;
import android.util.Log;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;

public final class TransformationUtils {
    public static final int PAINT_FLAGS = 6;
    private static final String TAG = "TransformationUtils";

    private TransformationUtils() {
    }

    public static Bitmap centerCrop(Bitmap bitmap, Bitmap bitmap2, int i, int i2) {
        Matrix matrix;
        float scale;
        Bitmap result;
        Canvas canvas;
        Paint paint;
        Bitmap recycled = bitmap;
        Bitmap toCrop = bitmap2;
        int width = i;
        int height = i2;
        if (toCrop == null) {
            return null;
        }
        if (toCrop.getWidth() == width && toCrop.getHeight() == height) {
            return toCrop;
        }
        float dx = 0.0f;
        float dy = 0.0f;
        new Matrix();
        Matrix m = matrix;
        if (toCrop.getWidth() * height > width * toCrop.getHeight()) {
            scale = ((float) height) / ((float) toCrop.getHeight());
            dx = (((float) width) - (((float) toCrop.getWidth()) * scale)) * 0.5f;
        } else {
            scale = ((float) width) / ((float) toCrop.getWidth());
            dy = (((float) height) - (((float) toCrop.getHeight()) * scale)) * 0.5f;
        }
        m.setScale(scale, scale);
        boolean postTranslate = m.postTranslate((float) ((int) (dx + 0.5f)), (float) ((int) (dy + 0.5f)));
        if (recycled != null) {
            result = recycled;
        } else {
            result = Bitmap.createBitmap(width, height, getSafeConfig(toCrop));
        }
        setAlpha(toCrop, result);
        new Canvas(result);
        new Paint(6);
        canvas.drawBitmap(toCrop, m, paint);
        return result;
    }

    public static Bitmap fitCenter(Bitmap bitmap, BitmapPool bitmapPool, int i, int i2) {
        Canvas canvas;
        Matrix matrix;
        Paint paint;
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        Bitmap toFit = bitmap;
        BitmapPool pool = bitmapPool;
        int width = i;
        int height = i2;
        if (toFit.getWidth() == width && toFit.getHeight() == height) {
            if (Log.isLoggable(TAG, 2)) {
                int v = Log.v(TAG, "requested target size matches input, returning input");
            }
            return toFit;
        }
        float minPercentage = Math.min(((float) width) / ((float) toFit.getWidth()), ((float) height) / ((float) toFit.getHeight()));
        int targetWidth = (int) (minPercentage * ((float) toFit.getWidth()));
        int targetHeight = (int) (minPercentage * ((float) toFit.getHeight()));
        if (toFit.getWidth() == targetWidth && toFit.getHeight() == targetHeight) {
            if (Log.isLoggable(TAG, 2)) {
                int v2 = Log.v(TAG, "adjusted target size matches input, returning input");
            }
            return toFit;
        }
        Bitmap.Config config = getSafeConfig(toFit);
        Bitmap toReuse = pool.get(targetWidth, targetHeight, config);
        if (toReuse == null) {
            toReuse = Bitmap.createBitmap(targetWidth, targetHeight, config);
        }
        setAlpha(toFit, toReuse);
        if (Log.isLoggable(TAG, 2)) {
            new StringBuilder();
            int v3 = Log.v(TAG, sb.append("request: ").append(width).append("x").append(height).toString());
            new StringBuilder();
            int v4 = Log.v(TAG, sb2.append("toFit:   ").append(toFit.getWidth()).append("x").append(toFit.getHeight()).toString());
            new StringBuilder();
            int v5 = Log.v(TAG, sb3.append("toReuse: ").append(toReuse.getWidth()).append("x").append(toReuse.getHeight()).toString());
            new StringBuilder();
            int v6 = Log.v(TAG, sb4.append("minPct:   ").append(minPercentage).toString());
        }
        new Canvas(toReuse);
        new Matrix();
        Matrix matrix2 = matrix;
        matrix2.setScale(minPercentage, minPercentage);
        new Paint(6);
        canvas.drawBitmap(toFit, matrix2, paint);
        return toReuse;
    }

    @TargetApi(12)
    public static void setAlpha(Bitmap bitmap, Bitmap bitmap2) {
        Bitmap toTransform = bitmap;
        Bitmap outBitmap = bitmap2;
        if (Build.VERSION.SDK_INT >= 12 && outBitmap != null) {
            outBitmap.setHasAlpha(toTransform.hasAlpha());
        }
    }

    @TargetApi(5)
    @Deprecated
    public static int getOrientation(String str) {
        StringBuilder sb;
        ExifInterface exif;
        String pathToOriginal = str;
        try {
            new ExifInterface(pathToOriginal);
            return getExifOrientationDegrees(exif.getAttributeInt("Orientation", 0));
        } catch (Exception e) {
            Exception e2 = e;
            if (Log.isLoggable(TAG, 6)) {
                new StringBuilder();
                int e3 = Log.e(TAG, sb.append("Unable to get orientation for image with path=").append(pathToOriginal).toString(), e2);
            }
            return 0;
        }
    }

    @Deprecated
    public static Bitmap orientImage(String pathToOriginal, Bitmap imageToOrient) {
        return rotateImage(imageToOrient, getOrientation(pathToOriginal));
    }

    public static Bitmap rotateImage(Bitmap bitmap, int i) {
        Matrix matrix;
        Bitmap imageToOrient = bitmap;
        int degreesToRotate = i;
        Bitmap result = imageToOrient;
        if (degreesToRotate != 0) {
            try {
                new Matrix();
                Matrix matrix2 = matrix;
                matrix2.setRotate((float) degreesToRotate);
                result = Bitmap.createBitmap(imageToOrient, 0, 0, imageToOrient.getWidth(), imageToOrient.getHeight(), matrix2, true);
            } catch (Exception e) {
                Exception e2 = e;
                if (Log.isLoggable(TAG, 6)) {
                    int e3 = Log.e(TAG, "Exception when trying to orient image", e2);
                }
            }
        }
        return result;
    }

    public static int getExifOrientationDegrees(int exifOrientation) {
        int degreesToRotate;
        switch (exifOrientation) {
            case 3:
            case 4:
                degreesToRotate = 180;
                break;
            case 5:
            case 6:
                degreesToRotate = 90;
                break;
            case 7:
            case 8:
                degreesToRotate = 270;
                break;
            default:
                degreesToRotate = 0;
                break;
        }
        return degreesToRotate;
    }

    public static Bitmap rotateImageExif(Bitmap bitmap, BitmapPool bitmapPool, int exifOrientation) {
        Matrix matrix;
        RectF rectF;
        Canvas canvas;
        Paint paint;
        Bitmap toOrient = bitmap;
        BitmapPool pool = bitmapPool;
        new Matrix();
        Matrix matrix2 = matrix;
        initializeMatrixForRotation(exifOrientation, matrix2);
        if (matrix2.isIdentity()) {
            return toOrient;
        }
        new RectF(0.0f, 0.0f, (float) toOrient.getWidth(), (float) toOrient.getHeight());
        RectF newRect = rectF;
        boolean mapRect = matrix2.mapRect(newRect);
        int newWidth = Math.round(newRect.width());
        int newHeight = Math.round(newRect.height());
        Bitmap.Config config = getSafeConfig(toOrient);
        Bitmap result = pool.get(newWidth, newHeight, config);
        if (result == null) {
            result = Bitmap.createBitmap(newWidth, newHeight, config);
        }
        boolean postTranslate = matrix2.postTranslate(-newRect.left, -newRect.top);
        new Canvas(result);
        new Paint(6);
        canvas.drawBitmap(toOrient, matrix2, paint);
        return result;
    }

    private static Bitmap.Config getSafeConfig(Bitmap bitmap) {
        Bitmap bitmap2 = bitmap;
        return bitmap2.getConfig() != null ? bitmap2.getConfig() : Bitmap.Config.ARGB_8888;
    }

    static void initializeMatrixForRotation(int exifOrientation, Matrix matrix) {
        Matrix matrix2 = matrix;
        switch (exifOrientation) {
            case 2:
                matrix2.setScale(-1.0f, 1.0f);
                return;
            case 3:
                matrix2.setRotate(180.0f);
                return;
            case 4:
                matrix2.setRotate(180.0f);
                boolean postScale = matrix2.postScale(-1.0f, 1.0f);
                return;
            case 5:
                matrix2.setRotate(90.0f);
                boolean postScale2 = matrix2.postScale(-1.0f, 1.0f);
                return;
            case 6:
                matrix2.setRotate(90.0f);
                return;
            case 7:
                matrix2.setRotate(-90.0f);
                boolean postScale3 = matrix2.postScale(-1.0f, 1.0f);
                return;
            case 8:
                matrix2.setRotate(-90.0f);
                return;
            default:
                return;
        }
    }
}

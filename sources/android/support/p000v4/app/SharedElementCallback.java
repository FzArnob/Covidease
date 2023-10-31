package android.support.p000v4.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import java.util.List;
import java.util.Map;

/* renamed from: android.support.v4.app.SharedElementCallback */
public abstract class SharedElementCallback {
    private static final String BUNDLE_SNAPSHOT_BITMAP = "sharedElement:snapshot:bitmap";
    private static final String BUNDLE_SNAPSHOT_IMAGE_MATRIX = "sharedElement:snapshot:imageMatrix";
    private static final String BUNDLE_SNAPSHOT_IMAGE_SCALETYPE = "sharedElement:snapshot:imageScaleType";
    private static final int MAX_IMAGE_SIZE = 1048576;
    private Matrix mTempMatrix;

    /* renamed from: android.support.v4.app.SharedElementCallback$OnSharedElementsReadyListener */
    public interface OnSharedElementsReadyListener {
        void onSharedElementsReady();
    }

    public SharedElementCallback() {
    }

    public void onSharedElementStart(List<String> list, List<View> list2, List<View> list3) {
    }

    public void onSharedElementEnd(List<String> list, List<View> list2, List<View> list3) {
    }

    public void onRejectSharedElements(List<View> list) {
    }

    public void onMapSharedElements(List<String> list, Map<String, View> map) {
    }

    public Parcelable onCaptureSharedElementSnapshot(View view, Matrix matrix, RectF rectF) {
        Canvas canvas;
        Matrix matrix2;
        Bitmap bitmap;
        Bundle bundle;
        View sharedElement = view;
        Matrix viewToGlobalMatrix = matrix;
        RectF screenBounds = rectF;
        if (sharedElement instanceof ImageView) {
            ImageView imageView = (ImageView) sharedElement;
            Drawable d = imageView.getDrawable();
            Drawable bg = imageView.getBackground();
            if (!(d == null || bg != null || (bitmap = createDrawableBitmap(d)) == null)) {
                new Bundle();
                Bundle bundle2 = bundle;
                bundle2.putParcelable(BUNDLE_SNAPSHOT_BITMAP, bitmap);
                bundle2.putString(BUNDLE_SNAPSHOT_IMAGE_SCALETYPE, imageView.getScaleType().toString());
                if (imageView.getScaleType() == ImageView.ScaleType.MATRIX) {
                    float[] values = new float[9];
                    imageView.getImageMatrix().getValues(values);
                    bundle2.putFloatArray(BUNDLE_SNAPSHOT_IMAGE_MATRIX, values);
                }
                return bundle2;
            }
        }
        int bitmapWidth = Math.round(screenBounds.width());
        int bitmapHeight = Math.round(screenBounds.height());
        Bitmap bitmap2 = null;
        if (bitmapWidth > 0 && bitmapHeight > 0) {
            float scale = Math.min(1.0f, 1048576.0f / ((float) (bitmapWidth * bitmapHeight)));
            int bitmapWidth2 = (int) (((float) bitmapWidth) * scale);
            int bitmapHeight2 = (int) (((float) bitmapHeight) * scale);
            if (this.mTempMatrix == null) {
                new Matrix();
                this.mTempMatrix = matrix2;
            }
            this.mTempMatrix.set(viewToGlobalMatrix);
            boolean postTranslate = this.mTempMatrix.postTranslate(-screenBounds.left, -screenBounds.top);
            boolean postScale = this.mTempMatrix.postScale(scale, scale);
            bitmap2 = Bitmap.createBitmap(bitmapWidth2, bitmapHeight2, Bitmap.Config.ARGB_8888);
            new Canvas(bitmap2);
            Canvas canvas2 = canvas;
            canvas2.concat(this.mTempMatrix);
            sharedElement.draw(canvas2);
        }
        return bitmap2;
    }

    private static Bitmap createDrawableBitmap(Drawable drawable) {
        Canvas canvas;
        Drawable drawable2 = drawable;
        int width = drawable2.getIntrinsicWidth();
        int height = drawable2.getIntrinsicHeight();
        if (width <= 0 || height <= 0) {
            return null;
        }
        float scale = Math.min(1.0f, 1048576.0f / ((float) (width * height)));
        if ((drawable2 instanceof BitmapDrawable) && scale == 1.0f) {
            return ((BitmapDrawable) drawable2).getBitmap();
        }
        int bitmapWidth = (int) (((float) width) * scale);
        int bitmapHeight = (int) (((float) height) * scale);
        Bitmap bitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888);
        new Canvas(bitmap);
        Rect existingBounds = drawable2.getBounds();
        int left = existingBounds.left;
        int top = existingBounds.top;
        int right = existingBounds.right;
        int bottom = existingBounds.bottom;
        drawable2.setBounds(0, 0, bitmapWidth, bitmapHeight);
        drawable2.draw(canvas);
        drawable2.setBounds(left, top, right, bottom);
        return bitmap;
    }

    public View onCreateSnapshotView(Context context, Parcelable parcelable) {
        ImageView imageView;
        ImageView imageView2;
        Matrix matrix;
        Context context2 = context;
        Parcelable snapshot = parcelable;
        ImageView view = null;
        if (snapshot instanceof Bundle) {
            Bundle bundle = (Bundle) snapshot;
            Bitmap bitmap = (Bitmap) bundle.getParcelable(BUNDLE_SNAPSHOT_BITMAP);
            if (bitmap == null) {
                return null;
            }
            new ImageView(context2);
            ImageView imageView3 = imageView2;
            view = imageView3;
            imageView3.setImageBitmap(bitmap);
            imageView3.setScaleType(ImageView.ScaleType.valueOf(bundle.getString(BUNDLE_SNAPSHOT_IMAGE_SCALETYPE)));
            if (imageView3.getScaleType() == ImageView.ScaleType.MATRIX) {
                float[] values = bundle.getFloatArray(BUNDLE_SNAPSHOT_IMAGE_MATRIX);
                new Matrix();
                Matrix matrix2 = matrix;
                matrix2.setValues(values);
                imageView3.setImageMatrix(matrix2);
            }
        } else if (snapshot instanceof Bitmap) {
            new ImageView(context2);
            view = imageView;
            view.setImageBitmap((Bitmap) snapshot);
        }
        return view;
    }

    public void onSharedElementsArrived(List<String> list, List<View> list2, OnSharedElementsReadyListener listener) {
        List<String> list3 = list;
        List<View> list4 = list2;
        listener.onSharedElementsReady();
    }
}

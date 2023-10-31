package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.TypeEvaluator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Picture;
import android.graphics.RectF;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import gnu.expr.Declaration;

class TransitionUtils {
    private static final boolean HAS_IS_ATTACHED_TO_WINDOW = (Build.VERSION.SDK_INT >= 19);
    private static final boolean HAS_OVERLAY = (Build.VERSION.SDK_INT >= 18);
    private static final boolean HAS_PICTURE_BITMAP = (Build.VERSION.SDK_INT >= 28);
    private static final int MAX_IMAGE_SIZE = 1048576;

    static View copyViewImage(ViewGroup viewGroup, View view, View view2) {
        Matrix matrix;
        RectF rectF;
        ImageView imageView;
        ViewGroup sceneRoot = viewGroup;
        View view3 = view;
        View parent = view2;
        new Matrix();
        Matrix matrix2 = matrix;
        matrix2.setTranslate((float) (-parent.getScrollX()), (float) (-parent.getScrollY()));
        ViewUtils.transformMatrixToGlobal(view3, matrix2);
        ViewUtils.transformMatrixToLocal(sceneRoot, matrix2);
        new RectF(0.0f, 0.0f, (float) view3.getWidth(), (float) view3.getHeight());
        RectF bounds = rectF;
        boolean mapRect = matrix2.mapRect(bounds);
        int left = Math.round(bounds.left);
        int top = Math.round(bounds.top);
        int right = Math.round(bounds.right);
        int bottom = Math.round(bounds.bottom);
        new ImageView(view3.getContext());
        ImageView copy = imageView;
        copy.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Bitmap bitmap = createViewBitmap(view3, matrix2, bounds, sceneRoot);
        if (bitmap != null) {
            copy.setImageBitmap(bitmap);
        }
        copy.measure(View.MeasureSpec.makeMeasureSpec(right - left, Declaration.MODULE_REFERENCE), View.MeasureSpec.makeMeasureSpec(bottom - top, Declaration.MODULE_REFERENCE));
        copy.layout(left, top, right, bottom);
        return copy;
    }

    private static Bitmap createViewBitmap(View view, Matrix matrix, RectF rectF, ViewGroup viewGroup) {
        boolean addToOverlay;
        boolean sceneRootIsAttached;
        Canvas canvas;
        Picture picture;
        View view2 = view;
        Matrix matrix2 = matrix;
        RectF bounds = rectF;
        ViewGroup sceneRoot = viewGroup;
        if (HAS_IS_ATTACHED_TO_WINDOW) {
            addToOverlay = !view2.isAttachedToWindow();
            sceneRootIsAttached = sceneRoot == null ? false : sceneRoot.isAttachedToWindow();
        } else {
            addToOverlay = false;
            sceneRootIsAttached = false;
        }
        ViewGroup parent = null;
        int indexInParent = 0;
        if (HAS_OVERLAY && addToOverlay) {
            if (!sceneRootIsAttached) {
                return null;
            }
            parent = (ViewGroup) view2.getParent();
            indexInParent = parent.indexOfChild(view2);
            sceneRoot.getOverlay().add(view2);
        }
        Bitmap bitmap = null;
        int bitmapWidth = Math.round(bounds.width());
        int bitmapHeight = Math.round(bounds.height());
        if (bitmapWidth > 0 && bitmapHeight > 0) {
            float scale = Math.min(1.0f, 1048576.0f / ((float) (bitmapWidth * bitmapHeight)));
            int bitmapWidth2 = Math.round(((float) bitmapWidth) * scale);
            int bitmapHeight2 = Math.round(((float) bitmapHeight) * scale);
            boolean postTranslate = matrix2.postTranslate(-bounds.left, -bounds.top);
            boolean postScale = matrix2.postScale(scale, scale);
            if (HAS_PICTURE_BITMAP) {
                new Picture();
                Picture picture2 = picture;
                Canvas canvas2 = picture2.beginRecording(bitmapWidth2, bitmapHeight2);
                canvas2.concat(matrix2);
                view2.draw(canvas2);
                picture2.endRecording();
                bitmap = Bitmap.createBitmap(picture2);
            } else {
                bitmap = Bitmap.createBitmap(bitmapWidth2, bitmapHeight2, Bitmap.Config.ARGB_8888);
                new Canvas(bitmap);
                Canvas canvas3 = canvas;
                canvas3.concat(matrix2);
                view2.draw(canvas3);
            }
        }
        if (HAS_OVERLAY && addToOverlay) {
            sceneRoot.getOverlay().remove(view2);
            parent.addView(view2, indexInParent);
        }
        return bitmap;
    }

    static Animator mergeAnimators(Animator animator, Animator animator2) {
        Animator animator3;
        Animator animator1 = animator;
        Animator animator22 = animator2;
        if (animator1 == null) {
            return animator22;
        }
        if (animator22 == null) {
            return animator1;
        }
        new AnimatorSet();
        Animator animatorSet = animator3;
        Animator[] animatorArr = new Animator[2];
        animatorArr[0] = animator1;
        Animator[] animatorArr2 = animatorArr;
        animatorArr2[1] = animator22;
        animatorSet.playTogether(animatorArr2);
        return animatorSet;
    }

    static class MatrixEvaluator implements TypeEvaluator<Matrix> {
        final float[] mTempEndValues = new float[9];
        final Matrix mTempMatrix;
        final float[] mTempStartValues = new float[9];

        MatrixEvaluator() {
            Matrix matrix;
            new Matrix();
            this.mTempMatrix = matrix;
        }

        public Matrix evaluate(float f, Matrix startValue, Matrix endValue) {
            float fraction = f;
            startValue.getValues(this.mTempStartValues);
            endValue.getValues(this.mTempEndValues);
            for (int i = 0; i < 9; i++) {
                this.mTempEndValues[i] = this.mTempStartValues[i] + (fraction * (this.mTempEndValues[i] - this.mTempStartValues[i]));
            }
            this.mTempMatrix.setValues(this.mTempEndValues);
            return this.mTempMatrix;
        }
    }

    private TransitionUtils() {
    }
}

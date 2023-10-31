package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Matrix;
import android.os.Build;
import android.util.Log;
import android.widget.ImageView;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class ImageViewUtils {
    private static final String TAG = "ImageViewUtils";
    private static Method sAnimateTransformMethod;
    private static boolean sAnimateTransformMethodFetched;

    static void startAnimateTransform(ImageView imageView) {
        ImageView view = imageView;
        if (Build.VERSION.SDK_INT < 21) {
            ImageView.ScaleType scaleType = view.getScaleType();
            view.setTag(C0211R.C0213id.save_scale_type, scaleType);
            if (scaleType == ImageView.ScaleType.MATRIX) {
                view.setTag(C0211R.C0213id.save_image_matrix, view.getImageMatrix());
            } else {
                view.setScaleType(ImageView.ScaleType.MATRIX);
            }
            view.setImageMatrix(MatrixUtils.IDENTITY_MATRIX);
        }
    }

    static void animateTransform(ImageView imageView, Matrix matrix) {
        Throwable th;
        ImageView view = imageView;
        Matrix matrix2 = matrix;
        if (Build.VERSION.SDK_INT < 21) {
            view.setImageMatrix(matrix2);
            return;
        }
        fetchAnimateTransformMethod();
        if (sAnimateTransformMethod != null) {
            try {
                Object invoke = sAnimateTransformMethod.invoke(view, new Object[]{matrix2});
            } catch (IllegalAccessException e) {
                IllegalAccessException illegalAccessException = e;
            } catch (InvocationTargetException e2) {
                InvocationTargetException e3 = e2;
                Throwable th2 = th;
                new RuntimeException(e3.getCause());
                throw th2;
            }
        }
    }

    private static void fetchAnimateTransformMethod() {
        if (!sAnimateTransformMethodFetched) {
            try {
                sAnimateTransformMethod = ImageView.class.getDeclaredMethod("animateTransform", new Class[]{Matrix.class});
                sAnimateTransformMethod.setAccessible(true);
            } catch (NoSuchMethodException e) {
                int i = Log.i(TAG, "Failed to retrieve animateTransform method", e);
            }
            sAnimateTransformMethodFetched = true;
        }
    }

    static void reserveEndAnimateTransform(ImageView imageView, Animator animator) {
        Animator.AnimatorListener animatorListener;
        ImageView view = imageView;
        Animator animator2 = animator;
        if (Build.VERSION.SDK_INT < 21) {
            final ImageView imageView2 = view;
            new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    Animator animation = animator;
                    ImageView.ScaleType scaleType = (ImageView.ScaleType) imageView2.getTag(C0211R.C0213id.save_scale_type);
                    imageView2.setScaleType(scaleType);
                    imageView2.setTag(C0211R.C0213id.save_scale_type, (Object) null);
                    if (scaleType == ImageView.ScaleType.MATRIX) {
                        imageView2.setImageMatrix((Matrix) imageView2.getTag(C0211R.C0213id.save_image_matrix));
                        imageView2.setTag(C0211R.C0213id.save_image_matrix, (Object) null);
                    }
                    animation.removeListener(this);
                }
            };
            animator2.addListener(animatorListener);
        }
    }

    private ImageViewUtils() {
    }
}

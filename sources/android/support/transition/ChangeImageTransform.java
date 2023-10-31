package android.support.transition;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.transition.TransitionUtils;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.Map;

public class ChangeImageTransform extends Transition {
    private static final Property<ImageView, Matrix> ANIMATED_TRANSFORM_PROPERTY;
    private static final TypeEvaluator<Matrix> NULL_MATRIX_EVALUATOR;
    private static final String PROPNAME_BOUNDS = "android:changeImageTransform:bounds";
    private static final String PROPNAME_MATRIX = "android:changeImageTransform:matrix";
    private static final String[] sTransitionProperties;

    static {
        TypeEvaluator<Matrix> typeEvaluator;
        Property<ImageView, Matrix> property;
        String[] strArr = new String[2];
        strArr[0] = PROPNAME_MATRIX;
        String[] strArr2 = strArr;
        strArr2[1] = PROPNAME_BOUNDS;
        sTransitionProperties = strArr2;
        new TypeEvaluator<Matrix>() {
            public Matrix evaluate(float f, Matrix matrix, Matrix matrix2) {
                float f2 = f;
                Matrix matrix3 = matrix;
                Matrix matrix4 = matrix2;
                return null;
            }
        };
        NULL_MATRIX_EVALUATOR = typeEvaluator;
        new Property<ImageView, Matrix>(Matrix.class, "animatedTransform") {
            public void set(ImageView view, Matrix matrix) {
                ImageViewUtils.animateTransform(view, matrix);
            }

            public Matrix get(ImageView imageView) {
                ImageView imageView2 = imageView;
                return null;
            }
        };
        ANIMATED_TRANSFORM_PROPERTY = property;
    }

    public ChangeImageTransform() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ChangeImageTransform(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void captureValues(TransitionValues transitionValues) {
        Object obj;
        TransitionValues transitionValues2 = transitionValues;
        View view = transitionValues2.view;
        if ((view instanceof ImageView) && view.getVisibility() == 0) {
            ImageView imageView = (ImageView) view;
            if (imageView.getDrawable() != null) {
                Map<String, Object> values = transitionValues2.values;
                new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
                Object put = values.put(PROPNAME_BOUNDS, obj);
                Object put2 = values.put(PROPNAME_MATRIX, copyImageMatrix(imageView));
            }
        }
    }

    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    public String[] getTransitionProperties() {
        return sTransitionProperties;
    }

    public Animator createAnimator(@NonNull ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        ObjectAnimator animator;
        ViewGroup viewGroup2 = viewGroup;
        TransitionValues startValues = transitionValues;
        TransitionValues endValues = transitionValues2;
        if (startValues == null || endValues == null) {
            return null;
        }
        Rect startBounds = (Rect) startValues.values.get(PROPNAME_BOUNDS);
        Rect endBounds = (Rect) endValues.values.get(PROPNAME_BOUNDS);
        if (startBounds == null || endBounds == null) {
            return null;
        }
        Matrix startMatrix = (Matrix) startValues.values.get(PROPNAME_MATRIX);
        Matrix endMatrix = (Matrix) endValues.values.get(PROPNAME_MATRIX);
        boolean matricesEqual = (startMatrix == null && endMatrix == null) || (startMatrix != null && startMatrix.equals(endMatrix));
        if (startBounds.equals(endBounds) && matricesEqual) {
            return null;
        }
        ImageView imageView = (ImageView) endValues.view;
        Drawable drawable = imageView.getDrawable();
        int drawableWidth = drawable.getIntrinsicWidth();
        int drawableHeight = drawable.getIntrinsicHeight();
        ImageViewUtils.startAnimateTransform(imageView);
        if (drawableWidth == 0 || drawableHeight == 0) {
            animator = createNullAnimator(imageView);
        } else {
            if (startMatrix == null) {
                startMatrix = MatrixUtils.IDENTITY_MATRIX;
            }
            if (endMatrix == null) {
                endMatrix = MatrixUtils.IDENTITY_MATRIX;
            }
            ANIMATED_TRANSFORM_PROPERTY.set(imageView, startMatrix);
            animator = createMatrixAnimator(imageView, startMatrix, endMatrix);
        }
        ImageViewUtils.reserveEndAnimateTransform(imageView, animator);
        return animator;
    }

    private ObjectAnimator createNullAnimator(ImageView imageView) {
        Property<ImageView, Matrix> property = ANIMATED_TRANSFORM_PROPERTY;
        TypeEvaluator<Matrix> typeEvaluator = NULL_MATRIX_EVALUATOR;
        Matrix[] matrixArr = new Matrix[2];
        matrixArr[0] = null;
        Matrix[] matrixArr2 = matrixArr;
        matrixArr2[1] = null;
        return ObjectAnimator.ofObject(imageView, property, typeEvaluator, matrixArr2);
    }

    private ObjectAnimator createMatrixAnimator(ImageView imageView, Matrix startMatrix, Matrix endMatrix) {
        TypeEvaluator typeEvaluator;
        Property<ImageView, Matrix> property = ANIMATED_TRANSFORM_PROPERTY;
        TypeEvaluator typeEvaluator2 = typeEvaluator;
        new TransitionUtils.MatrixEvaluator();
        Matrix[] matrixArr = new Matrix[2];
        matrixArr[0] = startMatrix;
        Matrix[] matrixArr2 = matrixArr;
        matrixArr2[1] = endMatrix;
        return ObjectAnimator.ofObject(imageView, property, typeEvaluator2, matrixArr2);
    }

    /* renamed from: android.support.transition.ChangeImageTransform$3 */
    static /* synthetic */ class C01993 {
        static final /* synthetic */ int[] $SwitchMap$android$widget$ImageView$ScaleType = new int[ImageView.ScaleType.values().length];

        static {
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_XY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
                NoSuchFieldError noSuchFieldError = e;
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.CENTER_CROP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
                NoSuchFieldError noSuchFieldError2 = e2;
            }
        }
    }

    private static Matrix copyImageMatrix(ImageView imageView) {
        Matrix matrix;
        ImageView view = imageView;
        switch (C01993.$SwitchMap$android$widget$ImageView$ScaleType[view.getScaleType().ordinal()]) {
            case 1:
                return fitXYMatrix(view);
            case 2:
                return centerCropMatrix(view);
            default:
                new Matrix(view.getImageMatrix());
                return matrix;
        }
    }

    private static Matrix fitXYMatrix(ImageView imageView) {
        Matrix matrix;
        ImageView view = imageView;
        Drawable image = view.getDrawable();
        new Matrix();
        Matrix matrix2 = matrix;
        boolean postScale = matrix2.postScale(((float) view.getWidth()) / ((float) image.getIntrinsicWidth()), ((float) view.getHeight()) / ((float) image.getIntrinsicHeight()));
        return matrix2;
    }

    private static Matrix centerCropMatrix(ImageView imageView) {
        Matrix matrix;
        ImageView view = imageView;
        Drawable image = view.getDrawable();
        int imageWidth = image.getIntrinsicWidth();
        int imageViewWidth = view.getWidth();
        int imageHeight = image.getIntrinsicHeight();
        int imageViewHeight = view.getHeight();
        float maxScale = Math.max(((float) imageViewWidth) / ((float) imageWidth), ((float) imageViewHeight) / ((float) imageHeight));
        int tx = Math.round((((float) imageViewWidth) - (((float) imageWidth) * maxScale)) / 2.0f);
        int ty = Math.round((((float) imageViewHeight) - (((float) imageHeight) * maxScale)) / 2.0f);
        new Matrix();
        Matrix matrix2 = matrix;
        boolean postScale = matrix2.postScale(maxScale, maxScale);
        boolean postTranslate = matrix2.postTranslate((float) tx, (float) ty);
        return matrix2;
    }
}

package android.support.transition;

import android.graphics.Matrix;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewParent;

class ViewUtilsBase {
    private float[] mMatrixValues;

    ViewUtilsBase() {
    }

    public void setTransitionAlpha(@NonNull View view, float f) {
        View view2 = view;
        float alpha = f;
        Float savedAlpha = (Float) view2.getTag(C0211R.C0213id.save_non_transition_alpha);
        if (savedAlpha != null) {
            view2.setAlpha(savedAlpha.floatValue() * alpha);
        } else {
            view2.setAlpha(alpha);
        }
    }

    public float getTransitionAlpha(@NonNull View view) {
        View view2 = view;
        Float savedAlpha = (Float) view2.getTag(C0211R.C0213id.save_non_transition_alpha);
        if (savedAlpha != null) {
            return view2.getAlpha() / savedAlpha.floatValue();
        }
        return view2.getAlpha();
    }

    public void saveNonTransitionAlpha(@NonNull View view) {
        View view2 = view;
        if (view2.getTag(C0211R.C0213id.save_non_transition_alpha) == null) {
            view2.setTag(C0211R.C0213id.save_non_transition_alpha, Float.valueOf(view2.getAlpha()));
        }
    }

    public void clearNonTransitionAlpha(@NonNull View view) {
        View view2 = view;
        if (view2.getVisibility() == 0) {
            view2.setTag(C0211R.C0213id.save_non_transition_alpha, (Object) null);
        }
    }

    public void transformMatrixToGlobal(@NonNull View view, @NonNull Matrix matrix) {
        View view2 = view;
        Matrix matrix2 = matrix;
        ViewParent parent = view2.getParent();
        if (parent instanceof View) {
            View vp = (View) parent;
            transformMatrixToGlobal(vp, matrix2);
            boolean preTranslate = matrix2.preTranslate((float) (-vp.getScrollX()), (float) (-vp.getScrollY()));
        }
        boolean preTranslate2 = matrix2.preTranslate((float) view2.getLeft(), (float) view2.getTop());
        Matrix vm = view2.getMatrix();
        if (!vm.isIdentity()) {
            boolean preConcat = matrix2.preConcat(vm);
        }
    }

    public void transformMatrixToLocal(@NonNull View view, @NonNull Matrix matrix) {
        Matrix matrix2;
        View view2 = view;
        Matrix matrix3 = matrix;
        ViewParent parent = view2.getParent();
        if (parent instanceof View) {
            View vp = (View) parent;
            transformMatrixToLocal(vp, matrix3);
            boolean postTranslate = matrix3.postTranslate((float) vp.getScrollX(), (float) vp.getScrollY());
        }
        boolean postTranslate2 = matrix3.postTranslate((float) view2.getLeft(), (float) view2.getTop());
        Matrix vm = view2.getMatrix();
        if (!vm.isIdentity()) {
            new Matrix();
            Matrix inverted = matrix2;
            if (vm.invert(inverted)) {
                boolean postConcat = matrix3.postConcat(inverted);
            }
        }
    }

    public void setAnimationMatrix(@NonNull View view, Matrix matrix) {
        View view2 = view;
        Matrix matrix2 = matrix;
        if (matrix2 == null || matrix2.isIdentity()) {
            view2.setPivotX((float) (view2.getWidth() / 2));
            view2.setPivotY((float) (view2.getHeight() / 2));
            view2.setTranslationX(0.0f);
            view2.setTranslationY(0.0f);
            view2.setScaleX(1.0f);
            view2.setScaleY(1.0f);
            view2.setRotation(0.0f);
            return;
        }
        float[] values = this.mMatrixValues;
        if (values == null) {
            float[] fArr = new float[9];
            values = fArr;
            this.mMatrixValues = fArr;
        }
        matrix2.getValues(values);
        float sin = values[3];
        float cos = ((float) Math.sqrt((double) (1.0f - (sin * sin)))) * ((float) (values[0] < 0.0f ? -1 : 1));
        float rotation = (float) Math.toDegrees(Math.atan2((double) sin, (double) cos));
        float scaleX = values[0] / cos;
        float scaleY = values[4] / cos;
        float dx = values[2];
        float dy = values[5];
        view2.setPivotX(0.0f);
        view2.setPivotY(0.0f);
        view2.setTranslationX(dx);
        view2.setTranslationY(dy);
        view2.setRotation(rotation);
        view2.setScaleX(scaleX);
        view2.setScaleY(scaleY);
    }

    public void setLeftTopRightBottom(View view, int left, int top, int right, int bottom) {
        View v = view;
        v.setLeft(left);
        v.setTop(top);
        v.setRight(right);
        v.setBottom(bottom);
    }
}

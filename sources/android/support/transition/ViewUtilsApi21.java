package android.support.transition;

import android.graphics.Matrix;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RequiresApi(21)
class ViewUtilsApi21 extends ViewUtilsApi19 {
    private static final String TAG = "ViewUtilsApi21";
    private static Method sSetAnimationMatrixMethod;
    private static boolean sSetAnimationMatrixMethodFetched;
    private static Method sTransformMatrixToGlobalMethod;
    private static boolean sTransformMatrixToGlobalMethodFetched;
    private static Method sTransformMatrixToLocalMethod;
    private static boolean sTransformMatrixToLocalMethodFetched;

    ViewUtilsApi21() {
    }

    public void transformMatrixToGlobal(@NonNull View view, @NonNull Matrix matrix) {
        Throwable th;
        View view2 = view;
        Matrix matrix2 = matrix;
        fetchTransformMatrixToGlobalMethod();
        if (sTransformMatrixToGlobalMethod != null) {
            try {
                Object invoke = sTransformMatrixToGlobalMethod.invoke(view2, new Object[]{matrix2});
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

    public void transformMatrixToLocal(@NonNull View view, @NonNull Matrix matrix) {
        Throwable th;
        View view2 = view;
        Matrix matrix2 = matrix;
        fetchTransformMatrixToLocalMethod();
        if (sTransformMatrixToLocalMethod != null) {
            try {
                Object invoke = sTransformMatrixToLocalMethod.invoke(view2, new Object[]{matrix2});
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

    public void setAnimationMatrix(@NonNull View view, Matrix matrix) {
        Throwable th;
        View view2 = view;
        Matrix matrix2 = matrix;
        fetchSetAnimationMatrix();
        if (sSetAnimationMatrixMethod != null) {
            try {
                Object invoke = sSetAnimationMatrixMethod.invoke(view2, new Object[]{matrix2});
            } catch (InvocationTargetException e) {
                InvocationTargetException invocationTargetException = e;
            } catch (IllegalAccessException e2) {
                IllegalAccessException e3 = e2;
                Throwable th2 = th;
                new RuntimeException(e3.getCause());
                throw th2;
            }
        }
    }

    private void fetchTransformMatrixToGlobalMethod() {
        if (!sTransformMatrixToGlobalMethodFetched) {
            try {
                sTransformMatrixToGlobalMethod = View.class.getDeclaredMethod("transformMatrixToGlobal", new Class[]{Matrix.class});
                sTransformMatrixToGlobalMethod.setAccessible(true);
            } catch (NoSuchMethodException e) {
                int i = Log.i(TAG, "Failed to retrieve transformMatrixToGlobal method", e);
            }
            sTransformMatrixToGlobalMethodFetched = true;
        }
    }

    private void fetchTransformMatrixToLocalMethod() {
        if (!sTransformMatrixToLocalMethodFetched) {
            try {
                sTransformMatrixToLocalMethod = View.class.getDeclaredMethod("transformMatrixToLocal", new Class[]{Matrix.class});
                sTransformMatrixToLocalMethod.setAccessible(true);
            } catch (NoSuchMethodException e) {
                int i = Log.i(TAG, "Failed to retrieve transformMatrixToLocal method", e);
            }
            sTransformMatrixToLocalMethodFetched = true;
        }
    }

    private void fetchSetAnimationMatrix() {
        if (!sSetAnimationMatrixMethodFetched) {
            try {
                sSetAnimationMatrixMethod = View.class.getDeclaredMethod("setAnimationMatrix", new Class[]{Matrix.class});
                sSetAnimationMatrixMethod.setAccessible(true);
            } catch (NoSuchMethodException e) {
                int i = Log.i(TAG, "Failed to retrieve setAnimationMatrix method", e);
            }
            sSetAnimationMatrixMethodFetched = true;
        }
    }
}

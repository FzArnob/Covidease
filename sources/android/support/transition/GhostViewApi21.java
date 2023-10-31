package android.support.transition;

import android.graphics.Matrix;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RequiresApi(21)
class GhostViewApi21 implements GhostViewImpl {
    private static final String TAG = "GhostViewApi21";
    private static Method sAddGhostMethod;
    private static boolean sAddGhostMethodFetched;
    private static Class<?> sGhostViewClass;
    private static boolean sGhostViewClassFetched;
    private static Method sRemoveGhostMethod;
    private static boolean sRemoveGhostMethodFetched;
    private final View mGhostView;

    static GhostViewImpl addGhost(View view, ViewGroup viewGroup, Matrix matrix) {
        Throwable th;
        GhostViewImpl ghostViewImpl;
        View view2 = view;
        ViewGroup viewGroup2 = viewGroup;
        Matrix matrix2 = matrix;
        fetchAddGhostMethod();
        if (sAddGhostMethod != null) {
            try {
                GhostViewImpl ghostViewImpl2 = ghostViewImpl;
                Method method = sAddGhostMethod;
                Object[] objArr = new Object[3];
                objArr[0] = view2;
                Object[] objArr2 = objArr;
                objArr2[1] = viewGroup2;
                Object[] objArr3 = objArr2;
                objArr3[2] = matrix2;
                new GhostViewApi21((View) method.invoke((Object) null, objArr3));
                return ghostViewImpl2;
            } catch (IllegalAccessException e) {
                IllegalAccessException illegalAccessException = e;
            } catch (InvocationTargetException e2) {
                InvocationTargetException e3 = e2;
                Throwable th2 = th;
                new RuntimeException(e3.getCause());
                throw th2;
            }
        }
        return null;
    }

    static void removeGhost(View view) {
        Throwable th;
        View view2 = view;
        fetchRemoveGhostMethod();
        if (sRemoveGhostMethod != null) {
            try {
                Object invoke = sRemoveGhostMethod.invoke((Object) null, new Object[]{view2});
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

    private GhostViewApi21(@NonNull View ghostView) {
        this.mGhostView = ghostView;
    }

    public void setVisibility(int visibility) {
        this.mGhostView.setVisibility(visibility);
    }

    public void reserveEndViewTransition(ViewGroup viewGroup, View view) {
    }

    private static void fetchGhostViewClass() {
        if (!sGhostViewClassFetched) {
            try {
                sGhostViewClass = Class.forName("android.view.GhostView");
            } catch (ClassNotFoundException e) {
                int i = Log.i(TAG, "Failed to retrieve GhostView class", e);
            }
            sGhostViewClassFetched = true;
        }
    }

    private static void fetchAddGhostMethod() {
        if (!sAddGhostMethodFetched) {
            try {
                fetchGhostViewClass();
                Class<?> cls = sGhostViewClass;
                Class[] clsArr = new Class[3];
                clsArr[0] = View.class;
                Class[] clsArr2 = clsArr;
                clsArr2[1] = ViewGroup.class;
                Class[] clsArr3 = clsArr2;
                clsArr3[2] = Matrix.class;
                sAddGhostMethod = cls.getDeclaredMethod("addGhost", clsArr3);
                sAddGhostMethod.setAccessible(true);
            } catch (NoSuchMethodException e) {
                int i = Log.i(TAG, "Failed to retrieve addGhost method", e);
            }
            sAddGhostMethodFetched = true;
        }
    }

    private static void fetchRemoveGhostMethod() {
        if (!sRemoveGhostMethodFetched) {
            try {
                fetchGhostViewClass();
                sRemoveGhostMethod = sGhostViewClass.getDeclaredMethod("removeGhost", new Class[]{View.class});
                sRemoveGhostMethod.setAccessible(true);
            } catch (NoSuchMethodException e) {
                int i = Log.i(TAG, "Failed to retrieve removeGhost method", e);
            }
            sRemoveGhostMethodFetched = true;
        }
    }
}

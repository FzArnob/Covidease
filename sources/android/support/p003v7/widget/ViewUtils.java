package android.support.p003v7.widget;

import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RestrictTo;
import android.support.p000v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v7.widget.ViewUtils */
public class ViewUtils {
    private static final String TAG = "ViewUtils";
    private static Method sComputeFitSystemWindowsMethod;

    static {
        if (Build.VERSION.SDK_INT >= 18) {
            Class<View> cls = View.class;
            try {
                Class[] clsArr = new Class[2];
                clsArr[0] = Rect.class;
                Class[] clsArr2 = clsArr;
                clsArr2[1] = Rect.class;
                sComputeFitSystemWindowsMethod = cls.getDeclaredMethod("computeFitSystemWindows", clsArr2);
                if (!sComputeFitSystemWindowsMethod.isAccessible()) {
                    sComputeFitSystemWindowsMethod.setAccessible(true);
                }
            } catch (NoSuchMethodException e) {
                NoSuchMethodException noSuchMethodException = e;
                int d = Log.d(TAG, "Could not find method computeFitSystemWindows. Oh well.");
            }
        }
    }

    private ViewUtils() {
    }

    public static boolean isLayoutRtl(View view) {
        return ViewCompat.getLayoutDirection(view) == 1;
    }

    public static void computeFitSystemWindows(View view, Rect rect, Rect rect2) {
        View view2 = view;
        Rect inoutInsets = rect;
        Rect outLocalInsets = rect2;
        if (sComputeFitSystemWindowsMethod != null) {
            try {
                Object[] objArr = new Object[2];
                objArr[0] = inoutInsets;
                Object[] objArr2 = objArr;
                objArr2[1] = outLocalInsets;
                Object invoke = sComputeFitSystemWindowsMethod.invoke(view2, objArr2);
            } catch (Exception e) {
                int d = Log.d(TAG, "Could not invoke computeFitSystemWindows", e);
            }
        }
    }

    public static void makeOptionalFitsSystemWindows(View view) {
        View view2 = view;
        if (Build.VERSION.SDK_INT >= 16) {
            try {
                Method method = view2.getClass().getMethod("makeOptionalFitsSystemWindows", new Class[0]);
                if (!method.isAccessible()) {
                    method.setAccessible(true);
                }
                Object invoke = method.invoke(view2, new Object[0]);
            } catch (NoSuchMethodException e) {
                NoSuchMethodException noSuchMethodException = e;
                int d = Log.d(TAG, "Could not find method makeOptionalFitsSystemWindows. Oh well...");
            } catch (InvocationTargetException e2) {
                int d2 = Log.d(TAG, "Could not invoke makeOptionalFitsSystemWindows", e2);
            } catch (IllegalAccessException e3) {
                int d3 = Log.d(TAG, "Could not invoke makeOptionalFitsSystemWindows", e3);
            }
        }
    }
}

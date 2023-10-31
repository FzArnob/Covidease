package android.support.transition;

import android.annotation.SuppressLint;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RequiresApi(22)
class ViewUtilsApi22 extends ViewUtilsApi21 {
    private static final String TAG = "ViewUtilsApi22";
    private static Method sSetLeftTopRightBottomMethod;
    private static boolean sSetLeftTopRightBottomMethodFetched;

    ViewUtilsApi22() {
    }

    public void setLeftTopRightBottom(View view, int i, int i2, int i3, int i4) {
        Throwable th;
        View v = view;
        int left = i;
        int top = i2;
        int right = i3;
        int bottom = i4;
        fetchSetLeftTopRightBottomMethod();
        if (sSetLeftTopRightBottomMethod != null) {
            try {
                Object[] objArr = new Object[4];
                objArr[0] = Integer.valueOf(left);
                Object[] objArr2 = objArr;
                objArr2[1] = Integer.valueOf(top);
                Object[] objArr3 = objArr2;
                objArr3[2] = Integer.valueOf(right);
                Object[] objArr4 = objArr3;
                objArr4[3] = Integer.valueOf(bottom);
                Object invoke = sSetLeftTopRightBottomMethod.invoke(v, objArr4);
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

    @SuppressLint({"PrivateApi"})
    private void fetchSetLeftTopRightBottomMethod() {
        if (!sSetLeftTopRightBottomMethodFetched) {
            Class<View> cls = View.class;
            try {
                Class[] clsArr = new Class[4];
                clsArr[0] = Integer.TYPE;
                Class[] clsArr2 = clsArr;
                clsArr2[1] = Integer.TYPE;
                Class[] clsArr3 = clsArr2;
                clsArr3[2] = Integer.TYPE;
                Class[] clsArr4 = clsArr3;
                clsArr4[3] = Integer.TYPE;
                sSetLeftTopRightBottomMethod = cls.getDeclaredMethod("setLeftTopRightBottom", clsArr4);
                sSetLeftTopRightBottomMethod.setAccessible(true);
            } catch (NoSuchMethodException e) {
                int i = Log.i(TAG, "Failed to retrieve setLeftTopRightBottom method", e);
            }
            sSetLeftTopRightBottomMethodFetched = true;
        }
    }
}

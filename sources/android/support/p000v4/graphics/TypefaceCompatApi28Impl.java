package android.support.p000v4.graphics;

import android.graphics.Typeface;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RequiresApi(28)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v4.graphics.TypefaceCompatApi28Impl */
public class TypefaceCompatApi28Impl extends TypefaceCompatApi26Impl {
    private static final String CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD = "createFromFamiliesWithDefault";
    private static final String DEFAULT_FAMILY = "sans-serif";
    private static final int RESOLVE_BY_FONT_TABLE = -1;
    private static final String TAG = "TypefaceCompatApi28Impl";

    public TypefaceCompatApi28Impl() {
    }

    /* access modifiers changed from: protected */
    public Typeface createFromFamiliesWithDefault(Object obj) {
        Throwable th;
        Object family = obj;
        try {
            Object familyArray = Array.newInstance(this.mFontFamily, 1);
            Array.set(familyArray, 0, family);
            Method method = this.mCreateFromFamiliesWithDefault;
            Object[] objArr = new Object[4];
            objArr[0] = familyArray;
            Object[] objArr2 = objArr;
            objArr2[1] = DEFAULT_FAMILY;
            Object[] objArr3 = objArr2;
            objArr3[2] = -1;
            Object[] objArr4 = objArr3;
            objArr4[3] = -1;
            return (Typeface) method.invoke((Object) null, objArr4);
        } catch (IllegalAccessException | InvocationTargetException e) {
            ReflectiveOperationException e2 = e;
            Throwable th2 = th;
            new RuntimeException(e2);
            throw th2;
        }
    }

    /* access modifiers changed from: protected */
    public Method obtainCreateFromFamiliesWithDefaultMethod(Class fontFamily) throws NoSuchMethodException {
        Class[] clsArr = new Class[4];
        clsArr[0] = Array.newInstance(fontFamily, 1).getClass();
        Class[] clsArr2 = clsArr;
        clsArr2[1] = String.class;
        Class[] clsArr3 = clsArr2;
        clsArr3[2] = Integer.TYPE;
        Class[] clsArr4 = clsArr3;
        clsArr4[3] = Integer.TYPE;
        Method m = Typeface.class.getDeclaredMethod(CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD, clsArr4);
        m.setAccessible(true);
        return m;
    }
}

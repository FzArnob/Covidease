package android.support.p000v4.widget;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.RestrictTo;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

@RestrictTo({RestrictTo.Scope.LIBRARY})
/* renamed from: android.support.v4.widget.ViewGroupUtils */
public class ViewGroupUtils {
    private static final ThreadLocal<Matrix> sMatrix;
    private static final ThreadLocal<RectF> sRectF;

    static {
        ThreadLocal<Matrix> threadLocal;
        ThreadLocal<RectF> threadLocal2;
        new ThreadLocal<>();
        sMatrix = threadLocal;
        new ThreadLocal<>();
        sRectF = threadLocal2;
    }

    static void offsetDescendantRect(ViewGroup viewGroup, View view, Rect rect) {
        RectF rectF;
        Matrix matrix;
        ViewGroup parent = viewGroup;
        View descendant = view;
        Rect rect2 = rect;
        Matrix m = sMatrix.get();
        if (m == null) {
            new Matrix();
            m = matrix;
            sMatrix.set(m);
        } else {
            m.reset();
        }
        offsetDescendantMatrix(parent, descendant, m);
        RectF rectF2 = sRectF.get();
        if (rectF2 == null) {
            new RectF();
            rectF2 = rectF;
            sRectF.set(rectF2);
        }
        rectF2.set(rect2);
        boolean mapRect = m.mapRect(rectF2);
        rect2.set((int) (rectF2.left + 0.5f), (int) (rectF2.top + 0.5f), (int) (rectF2.right + 0.5f), (int) (rectF2.bottom + 0.5f));
    }

    public static void getDescendantRect(ViewGroup parent, View view, Rect rect) {
        View descendant = view;
        Rect out = rect;
        out.set(0, 0, descendant.getWidth(), descendant.getHeight());
        offsetDescendantRect(parent, descendant, out);
    }

    private static void offsetDescendantMatrix(ViewParent viewParent, View view, Matrix matrix) {
        ViewParent target = viewParent;
        View view2 = view;
        Matrix m = matrix;
        ViewParent parent = view2.getParent();
        if ((parent instanceof View) && parent != target) {
            View vp = (View) parent;
            offsetDescendantMatrix(target, vp, m);
            boolean preTranslate = m.preTranslate((float) (-vp.getScrollX()), (float) (-vp.getScrollY()));
        }
        boolean preTranslate2 = m.preTranslate((float) view2.getLeft(), (float) view2.getTop());
        if (!view2.getMatrix().isIdentity()) {
            boolean preConcat = m.preConcat(view2.getMatrix());
        }
    }

    private ViewGroupUtils() {
    }
}

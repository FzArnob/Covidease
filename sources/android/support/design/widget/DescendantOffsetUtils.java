package android.support.design.widget;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.RestrictTo;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class DescendantOffsetUtils {
    private static final ThreadLocal<Matrix> matrix;
    private static final ThreadLocal<RectF> rectF;

    public DescendantOffsetUtils() {
    }

    static {
        ThreadLocal<Matrix> threadLocal;
        ThreadLocal<RectF> threadLocal2;
        new ThreadLocal<>();
        matrix = threadLocal;
        new ThreadLocal<>();
        rectF = threadLocal2;
    }

    public static void offsetDescendantRect(ViewGroup viewGroup, View view, Rect rect) {
        RectF rectF2;
        Matrix matrix2;
        ViewGroup parent = viewGroup;
        View descendant = view;
        Rect rect2 = rect;
        Matrix m = matrix.get();
        if (m == null) {
            new Matrix();
            m = matrix2;
            matrix.set(m);
        } else {
            m.reset();
        }
        offsetDescendantMatrix(parent, descendant, m);
        RectF rectF3 = rectF.get();
        if (rectF3 == null) {
            new RectF();
            rectF3 = rectF2;
            rectF.set(rectF3);
        }
        rectF3.set(rect2);
        boolean mapRect = m.mapRect(rectF3);
        rect2.set((int) (rectF3.left + 0.5f), (int) (rectF3.top + 0.5f), (int) (rectF3.right + 0.5f), (int) (rectF3.bottom + 0.5f));
    }

    public static void getDescendantRect(ViewGroup parent, View view, Rect rect) {
        View descendant = view;
        Rect out = rect;
        out.set(0, 0, descendant.getWidth(), descendant.getHeight());
        offsetDescendantRect(parent, descendant, out);
    }

    private static void offsetDescendantMatrix(ViewParent viewParent, View view, Matrix matrix2) {
        ViewParent target = viewParent;
        View view2 = view;
        Matrix m = matrix2;
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
}

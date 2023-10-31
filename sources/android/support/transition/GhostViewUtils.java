package android.support.transition;

import android.graphics.Matrix;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;

class GhostViewUtils {
    static GhostViewImpl addGhost(View view, ViewGroup viewGroup, Matrix matrix) {
        View view2 = view;
        ViewGroup viewGroup2 = viewGroup;
        Matrix matrix2 = matrix;
        if (Build.VERSION.SDK_INT >= 21) {
            return GhostViewApi21.addGhost(view2, viewGroup2, matrix2);
        }
        return GhostViewApi14.addGhost(view2, viewGroup2);
    }

    static void removeGhost(View view) {
        View view2 = view;
        if (Build.VERSION.SDK_INT >= 21) {
            GhostViewApi21.removeGhost(view2);
        } else {
            GhostViewApi14.removeGhost(view2);
        }
    }

    private GhostViewUtils() {
    }
}

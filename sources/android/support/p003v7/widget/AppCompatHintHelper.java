package android.support.p003v7.widget;

import android.view.View;
import android.view.ViewParent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

/* renamed from: android.support.v7.widget.AppCompatHintHelper */
class AppCompatHintHelper {
    static InputConnection onCreateInputConnection(InputConnection inputConnection, EditorInfo editorInfo, View view) {
        InputConnection ic = inputConnection;
        EditorInfo outAttrs = editorInfo;
        View view2 = view;
        if (ic != null && outAttrs.hintText == null) {
            ViewParent parent = view2.getParent();
            while (true) {
                ViewParent parent2 = parent;
                if (!(parent2 instanceof View)) {
                    break;
                } else if (parent2 instanceof WithHint) {
                    outAttrs.hintText = ((WithHint) parent2).getHint();
                    break;
                } else {
                    parent = parent2.getParent();
                }
            }
        }
        return ic;
    }

    private AppCompatHintHelper() {
    }
}

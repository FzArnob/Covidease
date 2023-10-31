package android.support.p003v7.app;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.RestrictTo;
import android.support.p000v4.app.DialogFragment;

/* renamed from: android.support.v7.app.AppCompatDialogFragment */
public class AppCompatDialogFragment extends DialogFragment {
    public AppCompatDialogFragment() {
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Dialog dialog;
        Bundle bundle2 = bundle;
        new AppCompatDialog(getContext(), getTheme());
        return dialog;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setupDialog(Dialog dialog, int i) {
        Dialog dialog2 = dialog;
        int style = i;
        if (dialog2 instanceof AppCompatDialog) {
            AppCompatDialog acd = (AppCompatDialog) dialog2;
            switch (style) {
                case 1:
                case 2:
                    break;
                case 3:
                    dialog2.getWindow().addFlags(24);
                    break;
                default:
                    return;
            }
            boolean supportRequestWindowFeature = acd.supportRequestWindowFeature(1);
            return;
        }
        super.setupDialog(dialog2, style);
    }
}

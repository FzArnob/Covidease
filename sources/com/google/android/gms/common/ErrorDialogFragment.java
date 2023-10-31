package com.google.android.gms.common;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;

public class ErrorDialogFragment extends DialogFragment {
    private Dialog mDialog = null;
    private DialogInterface.OnCancelListener zaan = null;

    public ErrorDialogFragment() {
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Bundle bundle2 = bundle;
        if (this.mDialog == null) {
            setShowsDialog(false);
        }
        return this.mDialog;
    }

    public void onCancel(DialogInterface dialogInterface) {
        DialogInterface dialogInterface2 = dialogInterface;
        if (this.zaan != null) {
            this.zaan.onCancel(dialogInterface2);
        }
    }

    public static ErrorDialogFragment newInstance(Dialog dialog) {
        return newInstance(dialog, (DialogInterface.OnCancelListener) null);
    }

    public static ErrorDialogFragment newInstance(Dialog dialog, DialogInterface.OnCancelListener onCancelListener) {
        ErrorDialogFragment errorDialogFragment;
        DialogInterface.OnCancelListener onCancelListener2 = onCancelListener;
        new ErrorDialogFragment();
        ErrorDialogFragment errorDialogFragment2 = errorDialogFragment;
        Dialog dialog2 = (Dialog) Preconditions.checkNotNull(dialog, "Cannot display null dialog");
        Dialog dialog3 = dialog2;
        dialog2.setOnCancelListener((DialogInterface.OnCancelListener) null);
        dialog3.setOnDismissListener((DialogInterface.OnDismissListener) null);
        errorDialogFragment2.mDialog = dialog3;
        if (onCancelListener2 != null) {
            errorDialogFragment2.zaan = onCancelListener2;
        }
        return errorDialogFragment2;
    }

    public void show(FragmentManager fragmentManager, String str) {
        super.show(fragmentManager, str);
    }
}

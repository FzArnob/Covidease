package com.google.android.gms.common;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.p000v4.app.DialogFragment;
import android.support.p000v4.app.FragmentManager;
import com.google.android.gms.common.internal.Preconditions;

public class SupportErrorDialogFragment extends DialogFragment {
    private Dialog mDialog = null;
    private DialogInterface.OnCancelListener zaan = null;

    public SupportErrorDialogFragment() {
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

    public static SupportErrorDialogFragment newInstance(Dialog dialog) {
        return newInstance(dialog, (DialogInterface.OnCancelListener) null);
    }

    public static SupportErrorDialogFragment newInstance(Dialog dialog, DialogInterface.OnCancelListener onCancelListener) {
        SupportErrorDialogFragment supportErrorDialogFragment;
        DialogInterface.OnCancelListener onCancelListener2 = onCancelListener;
        new SupportErrorDialogFragment();
        SupportErrorDialogFragment supportErrorDialogFragment2 = supportErrorDialogFragment;
        Dialog dialog2 = (Dialog) Preconditions.checkNotNull(dialog, "Cannot display null dialog");
        Dialog dialog3 = dialog2;
        dialog2.setOnCancelListener((DialogInterface.OnCancelListener) null);
        dialog3.setOnDismissListener((DialogInterface.OnDismissListener) null);
        supportErrorDialogFragment2.mDialog = dialog3;
        if (onCancelListener2 != null) {
            supportErrorDialogFragment2.zaan = onCancelListener2;
        }
        return supportErrorDialogFragment2;
    }

    public void show(FragmentManager fragmentManager, String str) {
        super.show(fragmentManager, str);
    }
}

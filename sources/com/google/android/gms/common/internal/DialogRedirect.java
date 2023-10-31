package com.google.android.gms.common.internal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.p000v4.app.Fragment;
import android.util.Log;
import com.google.android.gms.common.api.internal.LifecycleFragment;

public abstract class DialogRedirect implements DialogInterface.OnClickListener {
    public DialogRedirect() {
    }

    /* access modifiers changed from: protected */
    public abstract void redirect();

    public static DialogRedirect getInstance(Activity activity, Intent intent, int i) {
        DialogRedirect dialogRedirect;
        new zac(intent, activity, i);
        return dialogRedirect;
    }

    public static DialogRedirect getInstance(@NonNull Fragment fragment, Intent intent, int i) {
        DialogRedirect dialogRedirect;
        new zad(intent, fragment, i);
        return dialogRedirect;
    }

    public static DialogRedirect getInstance(@NonNull LifecycleFragment lifecycleFragment, Intent intent, int i) {
        DialogRedirect dialogRedirect;
        new zae(intent, lifecycleFragment, i);
        return dialogRedirect;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        DialogInterface dialogInterface2 = dialogInterface;
        int i2 = i;
        try {
            redirect();
            dialogInterface2.dismiss();
        } catch (ActivityNotFoundException e) {
            int e2 = Log.e("DialogRedirect", "Failed to start resolution intent", e);
            dialogInterface2.dismiss();
        } catch (Throwable th) {
            Throwable th2 = th;
            dialogInterface2.dismiss();
            throw th2;
        }
    }
}

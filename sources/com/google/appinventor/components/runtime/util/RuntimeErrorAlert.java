package com.google.appinventor.components.runtime.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

public final class RuntimeErrorAlert {
    public RuntimeErrorAlert() {
    }

    public static void alert(Object obj, String str, String str2, String str3) {
        AlertDialog.Builder builder;
        DialogInterface.OnClickListener onClickListener;
        Object obj2 = obj;
        String str4 = str;
        int i = Log.i("RuntimeErrorAlert", "in alert");
        new AlertDialog.Builder((Context) obj2);
        AlertDialog create = builder.create();
        AlertDialog alertDialog = create;
        create.setTitle(str2);
        alertDialog.setMessage(str4);
        final Object obj3 = obj2;
        new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                DialogInterface dialogInterface2 = dialogInterface;
                int i2 = i;
                ((Activity) obj3).finish();
            }
        };
        alertDialog.setButton(str3, onClickListener);
        if (str4 == null) {
            int e = Log.e(RuntimeErrorAlert.class.getName(), "No error message available");
        } else {
            int e2 = Log.e(RuntimeErrorAlert.class.getName(), str4);
        }
        alertDialog.show();
    }
}

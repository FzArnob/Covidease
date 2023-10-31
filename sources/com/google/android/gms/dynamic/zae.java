package com.google.android.gms.dynamic;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

final class zae implements View.OnClickListener {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ Intent zaro;

    zae(Context context, Intent intent) {
        this.val$context = context;
        this.zaro = intent;
    }

    public final void onClick(View view) {
        View view2 = view;
        try {
            this.val$context.startActivity(this.zaro);
        } catch (ActivityNotFoundException e) {
            int e2 = Log.e("DeferredLifecycleHelper", "Failed to start resolution intent", e);
        }
    }
}

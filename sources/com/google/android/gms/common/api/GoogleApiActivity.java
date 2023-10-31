package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.util.VisibleForTesting;
import gnu.expr.Declaration;

@KeepName
public class GoogleApiActivity extends Activity implements DialogInterface.OnCancelListener {
    @VisibleForTesting
    private int zabp = 0;

    public static PendingIntent zaa(Context context, PendingIntent pendingIntent, int i) {
        Context context2 = context;
        return PendingIntent.getActivity(context2, 0, zaa(context2, pendingIntent, i, true), Declaration.PACKAGE_ACCESS);
    }

    public static Intent zaa(Context context, PendingIntent pendingIntent, int i, boolean z) {
        Intent intent;
        new Intent(context, GoogleApiActivity.class);
        Intent intent2 = intent;
        Intent intent3 = intent2;
        Intent putExtra = intent2.putExtra("pending_intent", pendingIntent);
        Intent putExtra2 = intent3.putExtra("failing_client_id", i);
        Intent putExtra3 = intent3.putExtra("notify_manager", z);
        return intent3;
    }

    public GoogleApiActivity() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        Bundle bundle2 = bundle;
        super.onCreate(bundle2);
        if (bundle2 != null) {
            this.zabp = bundle2.getInt("resolution");
        }
        if (this.zabp != 1) {
            Bundle extras = getIntent().getExtras();
            Bundle bundle3 = extras;
            if (extras == null) {
                int e = Log.e("GoogleApiActivity", "Activity started without extras");
                finish();
                return;
            }
            PendingIntent pendingIntent = (PendingIntent) bundle3.get("pending_intent");
            Integer num = (Integer) bundle3.get("error_code");
            if (pendingIntent == null && num == null) {
                int e2 = Log.e("GoogleApiActivity", "Activity started without resolution");
                finish();
            } else if (pendingIntent != null) {
                try {
                    startIntentSenderForResult(pendingIntent.getIntentSender(), 1, (Intent) null, 0, 0, 0);
                    this.zabp = 1;
                } catch (IntentSender.SendIntentException e3) {
                    int e4 = Log.e("GoogleApiActivity", "Failed to launch pendingIntent", e3);
                    finish();
                }
            } else {
                boolean showErrorDialogFragment = GoogleApiAvailability.getInstance().showErrorDialogFragment(this, num.intValue(), 2, this);
                this.zabp = 1;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        ConnectionResult connectionResult;
        int i3 = i;
        int i4 = i2;
        Intent intent2 = intent;
        super.onActivityResult(i3, i4, intent2);
        if (i3 == 1) {
            boolean booleanExtra = getIntent().getBooleanExtra("notify_manager", true);
            this.zabp = 0;
            setResult(i4, intent2);
            if (booleanExtra) {
                GoogleApiManager zab = GoogleApiManager.zab((Context) this);
                switch (i4) {
                    case -1:
                        zab.zao();
                        break;
                    case 0:
                        new ConnectionResult(13, (PendingIntent) null);
                        zab.zaa(connectionResult, getIntent().getIntExtra("failing_client_id", -1));
                        break;
                }
            }
        } else if (i3 == 2) {
            this.zabp = 0;
            setResult(i4, intent2);
        }
        finish();
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        Bundle bundle2 = bundle;
        bundle2.putInt("resolution", this.zabp);
        super.onSaveInstanceState(bundle2);
    }

    public void onCancel(DialogInterface dialogInterface) {
        DialogInterface dialogInterface2 = dialogInterface;
        this.zabp = 0;
        setResult(0);
        finish();
    }
}

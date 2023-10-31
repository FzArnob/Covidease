package com.google.appinventor.components.runtime;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.internal.AccountType;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import java.util.ArrayList;

@SimpleObject
@DesignerComponent(category = ComponentCategory.GOOGLE, description = "Component to pick a Google Account.", iconName = "images/google.png", nonVisible = true, version = 1)
public class GoogleAccountPicker extends AndroidNonvisibleComponent implements ActivityResultListener, Component {
    private ComponentContainer container;
    private Context context;
    private int requestCode = 0;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public GoogleAccountPicker(com.google.appinventor.components.runtime.ComponentContainer r5) {
        /*
            r4 = this;
            r0 = r4
            r1 = r5
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            r3 = 0
            r2.requestCode = r3
            r2 = r0
            r3 = r1
            r2.container = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            java.lang.String r2 = "GoogleAccountPicker"
            java.lang.String r3 = "GoogleAccountPicker Created"
            int r2 = android.util.Log.d(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.GoogleAccountPicker.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleFunction(description = "Provide an account picker to pick a Google account.")
    public void Pick() {
        StringBuilder sb;
        if (this.requestCode == 0) {
            this.requestCode = this.form.registerForActivityResult(this);
            new StringBuilder("Pick, requestCode: ");
            int d = Log.d("GoogleAccountPicker", sb.append(this.requestCode).toString());
        }
        try {
            String[] strArr = new String[2];
            strArr[0] = AccountType.GOOGLE;
            String[] strArr2 = strArr;
            strArr2[1] = "com.google.android.legacyimap";
            this.container.$context().startActivityForResult(AccountManager.newChooseAccountIntent((Account) null, (ArrayList) null, strArr2, false, (String) null, (String) null, (String[]) null, (Bundle) null), this.requestCode);
        } catch (ActivityNotFoundException e) {
            this.form.dispatchErrorOccurredEvent(this, "Pick", ErrorMessages.ERROR_ACTIVITY_STARTER_NO_CORRESPONDING_ACTIVITY, new Object[0]);
        }
    }

    @SimpleEvent(description = "Event raised after account has been picked.")
    public void Picked(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "Picked", str);
    }

    public void resultReturned(int i, int i2, Intent intent) {
        int i3 = i2;
        Intent intent2 = intent;
        if (this.requestCode == i && i3 == -1 && intent2 != null) {
            Picked(intent2.getStringExtra("authAccount"));
        }
    }
}

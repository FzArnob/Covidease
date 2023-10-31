package com.google.appinventor.components.runtime;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.JsonUtil;
import com.google.appinventor.components.runtime.util.KodularUtil;

@DesignerComponent(category = ComponentCategory.INTERNAL, description = "", iconName = "images/instantAccess.png", nonVisible = true, version = 1)
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET")
public class MakeroidInstantAccess extends AndroidNonvisibleComponent implements Component {
    private final String D5WW7ko68TBhY3rWGJixZsWe3BbuoJE4ehTpLkVzvzCwqdv8qeJ2dPQYU5v0Rxk = "Please check your Client ID property. Maybe it is empty?";
    private String FlE8cJN9pJT0vK7EOYVYVCxuB0sJcG6jpZ55tqfSsTRV8K3RkDe9yvLjVOLhsxDi = "";
    private final String bjRTr59rlfMAk3NoImwosXnapn2cygGkKCeZagS1kgI5blNKx2IlW2vUboyQWmzb = "Please check your user property. Maybe it is empty?";
    private Context context;
    private final String haiIuOdmUoc5XQFvR9GJKGOwB3ZezhsWV0MdG7MgpkerzHclvrTRoGLrsRSHUTL5 = "Error - no data";
    /* access modifiers changed from: private */
    public int ht6M5vNRogDvRYCDAZw5zQxzUwImwEpGaHRJyoKVPYuZoKA1X0CMwOko8kVy3tgl = 1;
    private final String l1RC65VA1OrEFGFIoMpcm9UdXKH0b4XYT6Sp5a4IfmUrLMcXzEdiTdgfLz7JJ5B0 = "Please check your Client Secret property. Maybe it is empty?";
    private String sk5rMw9RgYy71OTPvOENAp3eApfAT8tLYhxGtg9aKlffH3QIqZgsCnaQ8Le0fA = "";
    private final String t2ckruxpPDflxUi8XRIoUkd3SPCNiaP1fIl9I8fgeRcif548vLOXCzLwJMVgcQrg = "Error - no message";

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MakeroidInstantAccess(com.google.appinventor.components.runtime.ComponentContainer r5) {
        /*
            r4 = this;
            r0 = r4
            r1 = r5
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            java.lang.String r3 = ""
            r2.FlE8cJN9pJT0vK7EOYVYVCxuB0sJcG6jpZ55tqfSsTRV8K3RkDe9yvLjVOLhsxDi = r3
            r2 = r0
            java.lang.String r3 = ""
            r2.sk5rMw9RgYy71OTPvOENAp3eApfAT8tLYhxGtg9aKlffH3QIqZgsCnaQ8Le0fA = r3
            r2 = r0
            r3 = 1
            r2.ht6M5vNRogDvRYCDAZw5zQxzUwImwEpGaHRJyoKVPYuZoKA1X0CMwOko8kVy3tgl = r3
            r2 = r0
            java.lang.String r3 = "Please check your Client ID property. Maybe it is empty?"
            r2.D5WW7ko68TBhY3rWGJixZsWe3BbuoJE4ehTpLkVzvzCwqdv8qeJ2dPQYU5v0Rxk = r3
            r2 = r0
            java.lang.String r3 = "Please check your Client Secret property. Maybe it is empty?"
            r2.l1RC65VA1OrEFGFIoMpcm9UdXKH0b4XYT6Sp5a4IfmUrLMcXzEdiTdgfLz7JJ5B0 = r3
            r2 = r0
            java.lang.String r3 = "Please check your user property. Maybe it is empty?"
            r2.bjRTr59rlfMAk3NoImwosXnapn2cygGkKCeZagS1kgI5blNKx2IlW2vUboyQWmzb = r3
            r2 = r0
            java.lang.String r3 = "Error - no data"
            r2.haiIuOdmUoc5XQFvR9GJKGOwB3ZezhsWV0MdG7MgpkerzHclvrTRoGLrsRSHUTL5 = r3
            r2 = r0
            java.lang.String r3 = "Error - no message"
            r2.t2ckruxpPDflxUi8XRIoUkd3SPCNiaP1fIl9I8fgeRcif548vLOXCzLwJMVgcQrg = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            java.lang.String r2 = "Instant Access"
            java.lang.String r3 = "Instant Access Created"
            int r2 = android.util.Log.d(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.MakeroidInstantAccess.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleFunction(description = "Start a request to user with the instant access login service.")
    public void Request(String str) {
        StringBuilder sb;
        C0881a aVar;
        String str2 = str;
        new StringBuilder("https://dashboard.instantaccess.io/api/partner/authorize?client_id=");
        String sb2 = sb.append(this.FlE8cJN9pJT0vK7EOYVYVCxuB0sJcG6jpZ55tqfSsTRV8K3RkDe9yvLjVOLhsxDi).append("&client_secret=").append(this.sk5rMw9RgYy71OTPvOENAp3eApfAT8tLYhxGtg9aKlffH3QIqZgsCnaQ8Le0fA).append("&user_identifier=").append(str2).toString();
        if (this.FlE8cJN9pJT0vK7EOYVYVCxuB0sJcG6jpZ55tqfSsTRV8K3RkDe9yvLjVOLhsxDi == null || this.FlE8cJN9pJT0vK7EOYVYVCxuB0sJcG6jpZ55tqfSsTRV8K3RkDe9yvLjVOLhsxDi.isEmpty()) {
            OnRequestSent(false, "Error - no data", "Please check your Client ID property. Maybe it is empty?");
            int e = Log.e("Instant Access", "Client ID is null or empty.");
        } else if (this.sk5rMw9RgYy71OTPvOENAp3eApfAT8tLYhxGtg9aKlffH3QIqZgsCnaQ8Le0fA == null || this.sk5rMw9RgYy71OTPvOENAp3eApfAT8tLYhxGtg9aKlffH3QIqZgsCnaQ8Le0fA.isEmpty()) {
            OnRequestSent(false, "Error - no data", "Please check your Client Secret property. Maybe it is empty?");
            int e2 = Log.e("Instant Access", "Client Secret is null or empty.");
        } else if (str2 == null || str2.isEmpty()) {
            OnRequestSent(false, "Error - no data", "Please check your user property. Maybe it is empty?");
            int e3 = Log.e("Instant Access", "User is null or empty.");
        } else {
            this.ht6M5vNRogDvRYCDAZw5zQxzUwImwEpGaHRJyoKVPYuZoKA1X0CMwOko8kVy3tgl = 1;
            new C0881a(this, (byte) 0);
            AsyncTask execute = aVar.execute(new String[]{sb2});
        }
    }

    @SimpleEvent(description = "A event to detect that the login request was sent.")
    public void OnRequestSent(boolean z, String str, String str2) {
        Object[] objArr = new Object[3];
        objArr[0] = Boolean.valueOf(z);
        Object[] objArr2 = objArr;
        objArr2[1] = str;
        Object[] objArr3 = objArr2;
        objArr3[2] = str2;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "OnRequestSent", objArr3);
    }

    @SimpleFunction(description = "Check the current status with a given username.")
    public void CheckStatus(String str) {
        StringBuilder sb;
        C0881a aVar;
        String str2 = str;
        new StringBuilder("https://dashboard.instantaccess.io/api/partner/status?client_id=");
        String sb2 = sb.append(this.FlE8cJN9pJT0vK7EOYVYVCxuB0sJcG6jpZ55tqfSsTRV8K3RkDe9yvLjVOLhsxDi).append("&client_secret=").append(this.sk5rMw9RgYy71OTPvOENAp3eApfAT8tLYhxGtg9aKlffH3QIqZgsCnaQ8Le0fA).append("&user_identifier=").append(str2).toString();
        if (this.FlE8cJN9pJT0vK7EOYVYVCxuB0sJcG6jpZ55tqfSsTRV8K3RkDe9yvLjVOLhsxDi == null || this.FlE8cJN9pJT0vK7EOYVYVCxuB0sJcG6jpZ55tqfSsTRV8K3RkDe9yvLjVOLhsxDi.isEmpty()) {
            OnStatusReceived(false, "Error - no data", "Please check your Client ID property. Maybe it is empty?");
            int e = Log.e("Instant Access", "Client ID is null or empty.");
        } else if (this.sk5rMw9RgYy71OTPvOENAp3eApfAT8tLYhxGtg9aKlffH3QIqZgsCnaQ8Le0fA == null || this.sk5rMw9RgYy71OTPvOENAp3eApfAT8tLYhxGtg9aKlffH3QIqZgsCnaQ8Le0fA.isEmpty()) {
            OnStatusReceived(false, "Error - no data", "Please check your Client Secret property. Maybe it is empty?");
            int e2 = Log.e("Instant Access", "Client Secret is null or empty.");
        } else if (str2 == null || str2.isEmpty()) {
            OnStatusReceived(false, "Error - no data", "Please check your user property. Maybe it is empty?");
            int e3 = Log.e("Instant Access", "User is null or empty.");
        } else {
            this.ht6M5vNRogDvRYCDAZw5zQxzUwImwEpGaHRJyoKVPYuZoKA1X0CMwOko8kVy3tgl = 2;
            new C0881a(this, (byte) 0);
            AsyncTask execute = aVar.execute(new String[]{sb2});
        }
    }

    @SimpleEvent(description = "A event to detect that the status was received.")
    public void OnStatusReceived(boolean z, String str, String str2) {
        Object[] objArr = new Object[3];
        objArr[0] = Boolean.valueOf(z);
        Object[] objArr2 = objArr;
        objArr2[1] = str;
        Object[] objArr3 = objArr2;
        objArr3[2] = str2;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "OnStatusReceived", objArr3);
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty(userVisible = false)
    public void ClientID(String str) {
        String str2 = str;
        this.FlE8cJN9pJT0vK7EOYVYVCxuB0sJcG6jpZ55tqfSsTRV8K3RkDe9yvLjVOLhsxDi = str2;
    }

    @SimpleFunction(description = "Returns the client id.")
    public String ClientID() {
        return this.FlE8cJN9pJT0vK7EOYVYVCxuB0sJcG6jpZ55tqfSsTRV8K3RkDe9yvLjVOLhsxDi;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty(userVisible = false)
    public void ClientSecret(String str) {
        String str2 = str;
        this.sk5rMw9RgYy71OTPvOENAp3eApfAT8tLYhxGtg9aKlffH3QIqZgsCnaQ8Le0fA = str2;
    }

    @SimpleFunction(description = "Returns the client secret.")
    public String ClientSecret() {
        return this.sk5rMw9RgYy71OTPvOENAp3eApfAT8tLYhxGtg9aKlffH3QIqZgsCnaQ8Le0fA;
    }

    /* renamed from: com.google.appinventor.components.runtime.MakeroidInstantAccess$a */
    class C0881a extends AsyncTask<String, Void, String> {
        private /* synthetic */ MakeroidInstantAccess hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        private String l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j;

        private C0881a(MakeroidInstantAccess makeroidInstantAccess) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = makeroidInstantAccess;
            this.l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j = null;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ C0881a(MakeroidInstantAccess makeroidInstantAccess, byte b) {
            this(makeroidInstantAccess);
            byte b2 = b;
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            this.l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j = KodularUtil.GET_REQUEST(((String[]) objArr)[0]);
            if (!KodularUtil.REQUEST_SUCCESS) {
                if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.ht6M5vNRogDvRYCDAZw5zQxzUwImwEpGaHRJyoKVPYuZoKA1X0CMwOko8kVy3tgl == 1) {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.OnRequestSent(false, "Error - no data", KodularUtil.REQUEST_ERROR_MESSAGE);
                } else if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.ht6M5vNRogDvRYCDAZw5zQxzUwImwEpGaHRJyoKVPYuZoKA1X0CMwOko8kVy3tgl == 2) {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.OnStatusReceived(false, "Error - no data", KodularUtil.REQUEST_ERROR_MESSAGE);
                }
            }
            return this.l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j;
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            String str = (String) obj;
            String GetJsonAsString = JsonUtil.GetJsonAsString(str, "data", "Error - no data");
            String GetJsonAsString2 = JsonUtil.GetJsonAsString(str, "message", "Error - no message");
            boolean GetJsonAsBoolean = JsonUtil.GetJsonAsBoolean(str, "success");
            if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.ht6M5vNRogDvRYCDAZw5zQxzUwImwEpGaHRJyoKVPYuZoKA1X0CMwOko8kVy3tgl == 1) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.OnRequestSent(GetJsonAsBoolean, GetJsonAsString, GetJsonAsString2);
            } else if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.ht6M5vNRogDvRYCDAZw5zQxzUwImwEpGaHRJyoKVPYuZoKA1X0CMwOko8kVy3tgl == 2) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.OnStatusReceived(GetJsonAsBoolean, GetJsonAsString, GetJsonAsString2);
            }
        }
    }
}

package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.AsynchUtil;
import com.google.appinventor.components.runtime.util.YailList;
import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.shaded.apache.http.auth.AUTH;
import org.shaded.apache.http.client.methods.HttpDelete;
import org.shaded.apache.http.client.methods.HttpPost;
import org.shaded.apache.http.client.methods.HttpPut;
import org.shaded.apache.http.protocol.HTTP;

@SimpleObject
@DesignerComponent(category = ComponentCategory.STORAGE, description = "AirTable Component", iconName = "images/airtable.png", nonVisible = true, version = 1)
@UsesPermissions(permissionNames = "android.permission.INTERNET")
public class MakeroidAirtable extends AndroidNonvisibleComponent {
    private String AjvYp2jprm0xITJdrrGGtoOyEc1YvelLDxRNqR8p3eG4UJR1UusSlSmznC0GO8Nd;
    private int N4rx6qe3Dkxm9iGosdnZviEGwwAyjrMopVTdmRoB5smsVn2ef0JNliQjJW08w5OT;
    private String Ta6bjdQXoFeEb44hWQ7kTTTXw2rT1LHw6UX7lms7WU7H0AkpETH9D9EhELUfywQi = "Grid view";
    private String YY8QFJ7NsKl2krKlLP4gKRTKnpLlHQvVopkx7p60xy1hzICdxizXFIQJXbKtydSp;
    private final Activity activity;
    private String jKPsNUTq382ltO4Ct4VOTi9lUb0GK32zS6afcWmZUk1wuONzG2KH4rBMniXwxrgH;
    private String rtyU3Uj4Fd2cS2DWhNVfozs9qaFOsy3YcN33Msvg0fbnB6MZpRvgk3PrzB8p4A;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MakeroidAirtable(com.google.appinventor.components.runtime.ComponentContainer r5) {
        /*
            r4 = this;
            r0 = r4
            r1 = r5
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            java.lang.String r3 = "Grid view"
            r2.Ta6bjdQXoFeEb44hWQ7kTTTXw2rT1LHw6UX7lms7WU7H0AkpETH9D9EhELUfywQi = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.activity = r3
            java.lang.String r2 = "Makeroid Airtable"
            java.lang.String r3 = "Makeroid Airtable Created"
            int r2 = android.util.Log.d(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.MakeroidAirtable.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public String ApiKey() {
        return this.rtyU3Uj4Fd2cS2DWhNVfozs9qaFOsy3YcN33Msvg0fbnB6MZpRvgk3PrzB8p4A;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty(description = "Your apiKey")
    public void ApiKey(String str) {
        String str2 = str;
        this.rtyU3Uj4Fd2cS2DWhNVfozs9qaFOsy3YcN33Msvg0fbnB6MZpRvgk3PrzB8p4A = str2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public String BaseId() {
        return this.jKPsNUTq382ltO4Ct4VOTi9lUb0GK32zS6afcWmZUk1wuONzG2KH4rBMniXwxrgH;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty
    public void BaseId(String str) {
        String str2 = str;
        this.jKPsNUTq382ltO4Ct4VOTi9lUb0GK32zS6afcWmZUk1wuONzG2KH4rBMniXwxrgH = str2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public String TableName() {
        return this.AjvYp2jprm0xITJdrrGGtoOyEc1YvelLDxRNqR8p3eG4UJR1UusSlSmznC0GO8Nd;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty
    public void TableName(String str) {
        String str2 = str;
        this.AjvYp2jprm0xITJdrrGGtoOyEc1YvelLDxRNqR8p3eG4UJR1UusSlSmznC0GO8Nd = str2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR)
    public String ViewName() {
        return this.Ta6bjdQXoFeEb44hWQ7kTTTXw2rT1LHw6UX7lms7WU7H0AkpETH9D9EhELUfywQi;
    }

    @DesignerProperty(defaultValue = "Grid view", editorType = "string")
    @SimpleProperty
    public void ViewName(String str) {
        String str2 = str;
        this.Ta6bjdQXoFeEb44hWQ7kTTTXw2rT1LHw6UX7lms7WU7H0AkpETH9D9EhELUfywQi = str2;
    }

    public void GetAllData() throws Exception {
        StringBuilder sb;
        StringBuilder sb2;
        URL url;
        StringBuilder sb3;
        BufferedReader bufferedReader;
        Reader reader;
        StringBuilder sb4;
        JSONObject jSONObject;
        Runnable runnable;
        new StringBuilder(FusiontablesControl.AUTHORIZATION_HEADER_PREFIX);
        String sb5 = sb.append(this.rtyU3Uj4Fd2cS2DWhNVfozs9qaFOsy3YcN33Msvg0fbnB6MZpRvgk3PrzB8p4A).toString();
        try {
            new StringBuilder("https://api.airtable.com/v0/");
            new URL(sb3.append(this.jKPsNUTq382ltO4Ct4VOTi9lUb0GK32zS6afcWmZUk1wuONzG2KH4rBMniXwxrgH).append("/").append(this.AjvYp2jprm0xITJdrrGGtoOyEc1YvelLDxRNqR8p3eG4UJR1UusSlSmznC0GO8Nd).append("?&view=").append(this.Ta6bjdQXoFeEb44hWQ7kTTTXw2rT1LHw6UX7lms7WU7H0AkpETH9D9EhELUfywQi).toString().replaceAll(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "%20"));
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            HttpURLConnection httpURLConnection2 = httpURLConnection;
            httpURLConnection.setRequestProperty(AUTH.WWW_AUTH_RESP, sb5);
            httpURLConnection2.setRequestProperty(HTTP.CONTENT_TYPE, "application/json");
            int responseCode = httpURLConnection2.getResponseCode();
            new InputStreamReader(httpURLConnection2.getInputStream());
            new BufferedReader(reader);
            BufferedReader bufferedReader2 = bufferedReader;
            new StringBuilder();
            StringBuilder sb6 = sb4;
            while (true) {
                String readLine = bufferedReader2.readLine();
                String str = readLine;
                if (readLine == null) {
                    break;
                }
                StringBuilder append = sb6.append(str);
            }
            bufferedReader2.close();
            new JSONObject(sb6.toString());
            JSONObject jSONObject2 = jSONObject;
            JSONObject jSONObject3 = jSONObject2;
            JSONArray jSONArray = jSONObject2.getJSONArray("records");
            if (jSONObject3.has("offset")) {
                GetAllWithOffset(jSONObject3.getString("offset"), jSONArray);
                return;
            }
            this.YY8QFJ7NsKl2krKlLP4gKRTKnpLlHQvVopkx7p60xy1hzICdxizXFIQJXbKtydSp = jSONArray.toString();
            this.N4rx6qe3Dkxm9iGosdnZviEGwwAyjrMopVTdmRoB5smsVn2ef0JNliQjJW08w5OT = httpURLConnection2.getResponseCode();
            final JSONArray jSONArray2 = jSONArray;
            new Runnable(this) {
                private /* synthetic */ MakeroidAirtable hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
                }

                public final void run() {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.GotAllRows(MakeroidAirtable.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME), MakeroidAirtable.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME), jSONArray2.length());
                }
            };
            this.activity.runOnUiThread(runnable);
        } catch (Exception e) {
            new StringBuilder();
            int e2 = Log.e("GetAllData", sb2.append(e.getMessage()).toString());
        }
    }

    public void GetAllWithOffset(String str, JSONArray jSONArray) {
        StringBuilder sb;
        StringBuilder sb2;
        URL url;
        StringBuilder sb3;
        BufferedReader bufferedReader;
        Reader reader;
        StringBuilder sb4;
        JSONObject jSONObject;
        Runnable runnable;
        String str2 = str;
        JSONArray jSONArray2 = jSONArray;
        new StringBuilder(FusiontablesControl.AUTHORIZATION_HEADER_PREFIX);
        String sb5 = sb.append(this.rtyU3Uj4Fd2cS2DWhNVfozs9qaFOsy3YcN33Msvg0fbnB6MZpRvgk3PrzB8p4A).toString();
        try {
            new StringBuilder("https://api.airtable.com/v0/");
            new URL(sb3.append(this.jKPsNUTq382ltO4Ct4VOTi9lUb0GK32zS6afcWmZUk1wuONzG2KH4rBMniXwxrgH).append("/").append(this.AjvYp2jprm0xITJdrrGGtoOyEc1YvelLDxRNqR8p3eG4UJR1UusSlSmznC0GO8Nd).append("?&view=").append(this.Ta6bjdQXoFeEb44hWQ7kTTTXw2rT1LHw6UX7lms7WU7H0AkpETH9D9EhELUfywQi).append("&offset=").append(str2).toString().replaceAll(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "%20"));
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            HttpURLConnection httpURLConnection2 = httpURLConnection;
            httpURLConnection.setRequestProperty(AUTH.WWW_AUTH_RESP, sb5);
            httpURLConnection2.setRequestProperty(HTTP.CONTENT_TYPE, "application/json");
            int responseCode = httpURLConnection2.getResponseCode();
            new InputStreamReader(httpURLConnection2.getInputStream());
            new BufferedReader(reader);
            BufferedReader bufferedReader2 = bufferedReader;
            new StringBuilder();
            StringBuilder sb6 = sb4;
            while (true) {
                String readLine = bufferedReader2.readLine();
                String str3 = readLine;
                if (readLine == null) {
                    break;
                }
                StringBuilder append = sb6.append(str3);
            }
            bufferedReader2.close();
            new JSONObject(sb6.toString());
            JSONObject jSONObject2 = jSONObject;
            JSONObject jSONObject3 = jSONObject2;
            JSONArray jSONArray3 = jSONObject2.getJSONArray("records");
            for (int i = 0; i < jSONArray3.length(); i++) {
                JSONArray put = jSONArray2.put(jSONArray3.getJSONObject(i));
            }
            if (jSONObject3.has("offset")) {
                GetAllWithOffset(jSONObject3.getString("offset"), jSONArray2);
                return;
            }
            this.YY8QFJ7NsKl2krKlLP4gKRTKnpLlHQvVopkx7p60xy1hzICdxizXFIQJXbKtydSp = jSONArray2.toString();
            this.N4rx6qe3Dkxm9iGosdnZviEGwwAyjrMopVTdmRoB5smsVn2ef0JNliQjJW08w5OT = httpURLConnection2.getResponseCode();
            final int length = jSONArray2.length();
            new Runnable(this) {
                private /* synthetic */ MakeroidAirtable hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
                }

                public final void run() {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.GotAllRows(MakeroidAirtable.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME), MakeroidAirtable.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME), length);
                }
            };
            this.activity.runOnUiThread(runnable);
        } catch (Exception e) {
            new StringBuilder();
            int e2 = Log.e("GetAllData", sb2.append(e.getMessage()).toString());
        }
    }

    @SimpleFunction(description = "Gets cell data")
    public void GetCell(int i, String str) {
        Runnable runnable;
        final int i2 = i;
        final String str2 = str;
        new Runnable(this) {
            private /* synthetic */ MakeroidAirtable hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7;
            }

            public final void run() {
                try {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Cell(i2, str2);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        };
        AsynchUtil.runAsynchronously(runnable);
    }

    public void Cell(int i, String str) throws Exception {
        StringBuilder sb;
        URL url;
        StringBuilder sb2;
        BufferedReader bufferedReader;
        Reader reader;
        StringBuilder sb3;
        JSONObject jSONObject;
        Runnable runnable;
        int i2 = i;
        String str2 = str;
        new StringBuilder(FusiontablesControl.AUTHORIZATION_HEADER_PREFIX);
        String sb4 = sb.append(this.rtyU3Uj4Fd2cS2DWhNVfozs9qaFOsy3YcN33Msvg0fbnB6MZpRvgk3PrzB8p4A).toString();
        new StringBuilder("https://api.airtable.com/v0/");
        new URL(sb2.append(this.jKPsNUTq382ltO4Ct4VOTi9lUb0GK32zS6afcWmZUk1wuONzG2KH4rBMniXwxrgH).append("/").append(this.AjvYp2jprm0xITJdrrGGtoOyEc1YvelLDxRNqR8p3eG4UJR1UusSlSmznC0GO8Nd).append("?fields[]=").append(str2).append("&view=").append(this.Ta6bjdQXoFeEb44hWQ7kTTTXw2rT1LHw6UX7lms7WU7H0AkpETH9D9EhELUfywQi).toString().replaceAll(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "%20"));
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        HttpURLConnection httpURLConnection2 = httpURLConnection;
        httpURLConnection.setRequestProperty(AUTH.WWW_AUTH_RESP, sb4);
        httpURLConnection2.setRequestProperty(HTTP.CONTENT_TYPE, "application/json");
        int responseCode = httpURLConnection2.getResponseCode();
        new InputStreamReader(httpURLConnection2.getInputStream());
        new BufferedReader(reader);
        BufferedReader bufferedReader2 = bufferedReader;
        new StringBuilder();
        StringBuilder sb5 = sb3;
        while (true) {
            String readLine = bufferedReader2.readLine();
            String str3 = readLine;
            if (readLine == null) {
                break;
            }
            StringBuilder append = sb5.append(str3);
        }
        bufferedReader2.close();
        new JSONObject(sb5.toString());
        JSONObject jSONObject2 = jSONObject;
        JSONObject jSONObject3 = jSONObject2;
        JSONArray jSONArray = jSONObject2.getJSONArray("records");
        if (jSONObject3.has("offset")) {
            GetCellWithOffset(jSONObject3.getString("offset"), jSONArray, str2, i2);
            return;
        }
        JSONObject jSONObject4 = jSONArray.getJSONObject(i2 - 1);
        String string = jSONObject4.getString("id");
        String string2 = jSONObject4.getString("createdTime");
        final int i3 = responseCode;
        final String string3 = ((JSONObject) jSONArray.get(i2 - 1)).getJSONObject("fields").getString(str2);
        final String str4 = string;
        final String str5 = string2;
        new Runnable(this) {
            private /* synthetic */ MakeroidAirtable hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r9;
            }

            public final void run() {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.GotCell(i3, string3, str4, str5);
            }
        };
        this.activity.runOnUiThread(runnable);
    }

    public void GetCellWithOffset(String str, JSONArray jSONArray, String str2, int i) {
        StringBuilder sb;
        StringBuilder sb2;
        URL url;
        StringBuilder sb3;
        BufferedReader bufferedReader;
        Reader reader;
        StringBuilder sb4;
        JSONObject jSONObject;
        Runnable runnable;
        String str3 = str;
        JSONArray jSONArray2 = jSONArray;
        String str4 = str2;
        int i2 = i;
        new StringBuilder(FusiontablesControl.AUTHORIZATION_HEADER_PREFIX);
        String sb5 = sb.append(this.rtyU3Uj4Fd2cS2DWhNVfozs9qaFOsy3YcN33Msvg0fbnB6MZpRvgk3PrzB8p4A).toString();
        try {
            new StringBuilder("https://api.airtable.com/v0/");
            new URL(sb3.append(this.jKPsNUTq382ltO4Ct4VOTi9lUb0GK32zS6afcWmZUk1wuONzG2KH4rBMniXwxrgH).append("/").append(this.AjvYp2jprm0xITJdrrGGtoOyEc1YvelLDxRNqR8p3eG4UJR1UusSlSmznC0GO8Nd).append("?fields[]=").append(str4).append("&view=").append(this.Ta6bjdQXoFeEb44hWQ7kTTTXw2rT1LHw6UX7lms7WU7H0AkpETH9D9EhELUfywQi).append("&offset=").append(str3).toString().replaceAll(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "%20"));
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            HttpURLConnection httpURLConnection2 = httpURLConnection;
            httpURLConnection.setRequestProperty(AUTH.WWW_AUTH_RESP, sb5);
            httpURLConnection2.setRequestProperty(HTTP.CONTENT_TYPE, "application/json");
            int responseCode = httpURLConnection2.getResponseCode();
            new InputStreamReader(httpURLConnection2.getInputStream());
            new BufferedReader(reader);
            BufferedReader bufferedReader2 = bufferedReader;
            new StringBuilder();
            StringBuilder sb6 = sb4;
            while (true) {
                String readLine = bufferedReader2.readLine();
                String str5 = readLine;
                if (readLine == null) {
                    break;
                }
                StringBuilder append = sb6.append(str5);
            }
            bufferedReader2.close();
            new JSONObject(sb6.toString());
            JSONObject jSONObject2 = jSONObject;
            JSONObject jSONObject3 = jSONObject2;
            JSONArray jSONArray3 = jSONObject2.getJSONArray("records");
            for (int i3 = 0; i3 < jSONArray3.length(); i3++) {
                JSONArray put = jSONArray2.put(jSONArray3.getJSONObject(i3));
            }
            if (jSONObject3.has("offset")) {
                GetCellWithOffset(jSONObject3.getString("offset"), jSONArray2, str4, i2);
                return;
            }
            JSONObject jSONObject4 = jSONArray2.getJSONObject(i2 - 1);
            String string = jSONObject4.getString("id");
            String string2 = jSONObject4.getString("createdTime");
            final int i4 = responseCode;
            final String string3 = ((JSONObject) jSONArray2.get(i2 - 1)).getJSONObject("fields").getString(str4);
            final String str6 = string;
            final String str7 = string2;
            new Runnable(this) {
                private /* synthetic */ MakeroidAirtable hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r9;
                }

                public final void run() {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.GotCell(i4, string3, str6, str7);
                }
            };
            this.activity.runOnUiThread(runnable);
        } catch (Exception e) {
            new StringBuilder();
            int e2 = Log.e("GetCell", sb2.append(e.getMessage()).toString());
        }
    }

    @SimpleFunction(description = "Gets column data")
    public void GetColumn(String str, int i) {
        Runnable runnable;
        final String str2 = str;
        final int i2 = i;
        new Runnable(this) {
            private /* synthetic */ MakeroidAirtable hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7;
            }

            public final void run() {
                StringBuilder sb;
                try {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.Column(str2, i2);
                } catch (Throwable th) {
                    new StringBuilder();
                    int e = Log.e("GetColumn", sb.append(th.getMessage()).toString());
                }
            }
        };
        AsynchUtil.runAsynchronously(runnable);
    }

    public void Column(String str, int i) throws Exception {
        StringBuilder sb;
        List list;
        List list2;
        List list3;
        URL url;
        StringBuilder sb2;
        BufferedReader bufferedReader;
        Reader reader;
        StringBuilder sb3;
        JSONObject jSONObject;
        Runnable runnable;
        String str2 = str;
        int i2 = i;
        new StringBuilder(FusiontablesControl.AUTHORIZATION_HEADER_PREFIX);
        String sb4 = sb.append(this.rtyU3Uj4Fd2cS2DWhNVfozs9qaFOsy3YcN33Msvg0fbnB6MZpRvgk3PrzB8p4A).toString();
        new ArrayList();
        List list4 = list;
        new ArrayList();
        List list5 = list2;
        new ArrayList();
        List list6 = list3;
        new StringBuilder("https://api.airtable.com/v0/");
        new URL(sb2.append(this.jKPsNUTq382ltO4Ct4VOTi9lUb0GK32zS6afcWmZUk1wuONzG2KH4rBMniXwxrgH).append("/").append(this.AjvYp2jprm0xITJdrrGGtoOyEc1YvelLDxRNqR8p3eG4UJR1UusSlSmznC0GO8Nd).append("?fields[]=").append(str2).append("&maxRecords=").append(i2).append("&view=").append(this.Ta6bjdQXoFeEb44hWQ7kTTTXw2rT1LHw6UX7lms7WU7H0AkpETH9D9EhELUfywQi).toString().replaceAll(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "%20"));
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        HttpURLConnection httpURLConnection2 = httpURLConnection;
        httpURLConnection.setRequestProperty(AUTH.WWW_AUTH_RESP, sb4);
        httpURLConnection2.setRequestProperty(HTTP.CONTENT_TYPE, "application/json");
        int responseCode = httpURLConnection2.getResponseCode();
        new InputStreamReader(httpURLConnection2.getInputStream());
        new BufferedReader(reader);
        BufferedReader bufferedReader2 = bufferedReader;
        new StringBuilder();
        StringBuilder sb5 = sb3;
        while (true) {
            String readLine = bufferedReader2.readLine();
            String str3 = readLine;
            if (readLine == null) {
                break;
            }
            StringBuilder append = sb5.append(str3);
        }
        bufferedReader2.close();
        new JSONObject(sb5.toString());
        JSONObject jSONObject2 = jSONObject;
        JSONObject jSONObject3 = jSONObject2;
        JSONArray jSONArray = jSONObject2.getJSONArray("records");
        for (int i3 = 0; i3 < jSONArray.length(); i3++) {
            JSONObject jSONObject4 = jSONArray.getJSONObject(i3);
            JSONObject jSONObject5 = jSONObject4;
            String string = jSONObject4.getString("id");
            String string2 = jSONObject5.getString("createdTime");
            String string3 = jSONObject5.getJSONObject("fields").getString(str2);
            boolean add = list4.add(string);
            boolean add2 = list5.add(string2);
            boolean add3 = list6.add(string3);
        }
        if (jSONObject3.has("offset")) {
            ColumnWithOffset(list4, list5, list6, str2, i2, jSONObject3.getString("offset"));
            return;
        }
        final int i4 = responseCode;
        final List list7 = list6;
        final List list8 = list4;
        final List list9 = list5;
        new Runnable(this) {
            private /* synthetic */ MakeroidAirtable hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r9;
            }

            public final void run() {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.GotColumn(i4, list7, list8, list9);
            }
        };
        this.activity.runOnUiThread(runnable);
    }

    public void ColumnWithOffset(List<String> list, List<String> list2, List<String> list3, String str, int i, String str2) throws Exception {
        StringBuilder sb;
        URL url;
        StringBuilder sb2;
        BufferedReader bufferedReader;
        Reader reader;
        StringBuilder sb3;
        JSONObject jSONObject;
        Runnable runnable;
        List<String> list4 = list;
        List<String> list5 = list2;
        List<String> list6 = list3;
        String str3 = str;
        int i2 = i;
        new StringBuilder(FusiontablesControl.AUTHORIZATION_HEADER_PREFIX);
        String sb4 = sb.append(this.rtyU3Uj4Fd2cS2DWhNVfozs9qaFOsy3YcN33Msvg0fbnB6MZpRvgk3PrzB8p4A).toString();
        new StringBuilder("https://api.airtable.com/v0/");
        new URL(sb2.append(this.jKPsNUTq382ltO4Ct4VOTi9lUb0GK32zS6afcWmZUk1wuONzG2KH4rBMniXwxrgH).append("/").append(this.AjvYp2jprm0xITJdrrGGtoOyEc1YvelLDxRNqR8p3eG4UJR1UusSlSmznC0GO8Nd).append("?fields[]=").append(str3).append("&maxRecords=").append(i2).append("&view=").append(this.Ta6bjdQXoFeEb44hWQ7kTTTXw2rT1LHw6UX7lms7WU7H0AkpETH9D9EhELUfywQi).append("&offset=").append(str2).toString().replaceAll(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "%20"));
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        HttpURLConnection httpURLConnection2 = httpURLConnection;
        httpURLConnection.setRequestProperty(AUTH.WWW_AUTH_RESP, sb4);
        httpURLConnection2.setRequestProperty(HTTP.CONTENT_TYPE, "application/json");
        int responseCode = httpURLConnection2.getResponseCode();
        new InputStreamReader(httpURLConnection2.getInputStream());
        new BufferedReader(reader);
        BufferedReader bufferedReader2 = bufferedReader;
        new StringBuilder();
        StringBuilder sb5 = sb3;
        while (true) {
            String readLine = bufferedReader2.readLine();
            String str4 = readLine;
            if (readLine == null) {
                break;
            }
            StringBuilder append = sb5.append(str4);
        }
        bufferedReader2.close();
        new JSONObject(sb5.toString());
        JSONObject jSONObject2 = jSONObject;
        JSONObject jSONObject3 = jSONObject2;
        JSONArray jSONArray = jSONObject2.getJSONArray("records");
        for (int i3 = 0; i3 < jSONArray.length(); i3++) {
            JSONObject jSONObject4 = jSONArray.getJSONObject(i3);
            JSONObject jSONObject5 = jSONObject4;
            String string = jSONObject4.getString("id");
            String string2 = jSONObject5.getString("createdTime");
            String string3 = jSONObject5.getJSONObject("fields").getString(str3);
            boolean add = list4.add(string);
            boolean add2 = list5.add(string2);
            boolean add3 = list6.add(string3);
        }
        if (jSONObject3.has("offset")) {
            ColumnWithOffset(list4, list5, list6, str3, i2, jSONObject3.getString("offset"));
            return;
        }
        final int i4 = responseCode;
        final List<String> list7 = list6;
        final List<String> list8 = list4;
        final List<String> list9 = list5;
        new Runnable(this) {
            private /* synthetic */ MakeroidAirtable hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r9;
            }

            public final void run() {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.GotColumn(i4, list7, list8, list9);
            }
        };
        this.activity.runOnUiThread(runnable);
    }

    @SimpleFunction(description = "Gets row data")
    public void GetRow(int i) {
        Runnable runnable;
        final int i2 = i;
        new Runnable(this) {
            private /* synthetic */ MakeroidAirtable hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
            }

            public final void run() {
                StringBuilder sb;
                try {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getRow(i2);
                } catch (Throwable th) {
                    new StringBuilder();
                    int e = Log.e("GetRow", sb.append(th.getMessage()).toString());
                }
            }
        };
        AsynchUtil.runAsynchronously(runnable);
    }

    public void getRow(int i) {
        StringBuilder sb;
        List list;
        StringBuilder sb2;
        URL url;
        StringBuilder sb3;
        BufferedReader bufferedReader;
        Reader reader;
        StringBuilder sb4;
        JSONObject jSONObject;
        Runnable runnable;
        int i2 = i;
        new StringBuilder(FusiontablesControl.AUTHORIZATION_HEADER_PREFIX);
        String sb5 = sb.append(this.rtyU3Uj4Fd2cS2DWhNVfozs9qaFOsy3YcN33Msvg0fbnB6MZpRvgk3PrzB8p4A).toString();
        new ArrayList();
        List list2 = list;
        try {
            new StringBuilder("https://api.airtable.com/v0/");
            new URL(sb3.append(this.jKPsNUTq382ltO4Ct4VOTi9lUb0GK32zS6afcWmZUk1wuONzG2KH4rBMniXwxrgH).append("/").append(this.AjvYp2jprm0xITJdrrGGtoOyEc1YvelLDxRNqR8p3eG4UJR1UusSlSmznC0GO8Nd).append("?&view=").append(this.Ta6bjdQXoFeEb44hWQ7kTTTXw2rT1LHw6UX7lms7WU7H0AkpETH9D9EhELUfywQi).toString().replaceAll(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "%20"));
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            HttpURLConnection httpURLConnection2 = httpURLConnection;
            httpURLConnection.setRequestProperty(AUTH.WWW_AUTH_RESP, sb5);
            httpURLConnection2.setRequestProperty(HTTP.CONTENT_TYPE, "application/json");
            int responseCode = httpURLConnection2.getResponseCode();
            new InputStreamReader(httpURLConnection2.getInputStream());
            new BufferedReader(reader);
            BufferedReader bufferedReader2 = bufferedReader;
            new StringBuilder();
            StringBuilder sb6 = sb4;
            while (true) {
                String readLine = bufferedReader2.readLine();
                String str = readLine;
                if (readLine == null) {
                    break;
                }
                StringBuilder append = sb6.append(str);
            }
            bufferedReader2.close();
            new JSONObject(sb6.toString());
            JSONObject jSONObject2 = jSONObject;
            JSONObject jSONObject3 = jSONObject2;
            JSONArray jSONArray = jSONObject2.getJSONArray("records");
            JSONArray jSONArray2 = jSONArray;
            JSONObject jSONObject4 = jSONArray.getJSONObject(i2 - 1).getJSONObject("fields");
            JSONObject jSONObject5 = jSONObject4;
            JSONArray names = jSONObject4.names();
            for (int i3 = 0; i3 < names.length(); i3++) {
                boolean add = list2.add(jSONObject5.getString(names.getString(i3)));
            }
            if (jSONObject3.has("offset")) {
                RowWithOffset(i2, jSONObject3.getString("offset"), list2, jSONArray2);
                return;
            }
            final int i4 = responseCode;
            final List list3 = list2;
            new Runnable(this) {
                private /* synthetic */ MakeroidAirtable hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7;
                }

                public final void run() {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.GotRow(i4, list3);
                }
            };
            this.activity.runOnUiThread(runnable);
        } catch (Exception e) {
            new StringBuilder();
            int e2 = Log.e("GetRow", sb2.append(e.getMessage()).toString());
        }
    }

    public void RowWithOffset(int i, String str, List<String> list, JSONArray jSONArray) {
        StringBuilder sb;
        StringBuilder sb2;
        URL url;
        StringBuilder sb3;
        BufferedReader bufferedReader;
        Reader reader;
        StringBuilder sb4;
        JSONObject jSONObject;
        Runnable runnable;
        int i2 = i;
        String str2 = str;
        List<String> list2 = list;
        JSONArray jSONArray2 = jSONArray;
        new StringBuilder(FusiontablesControl.AUTHORIZATION_HEADER_PREFIX);
        String sb5 = sb.append(this.rtyU3Uj4Fd2cS2DWhNVfozs9qaFOsy3YcN33Msvg0fbnB6MZpRvgk3PrzB8p4A).toString();
        try {
            new StringBuilder("https://api.airtable.com/v0/");
            new URL(sb3.append(this.jKPsNUTq382ltO4Ct4VOTi9lUb0GK32zS6afcWmZUk1wuONzG2KH4rBMniXwxrgH).append("/").append(this.AjvYp2jprm0xITJdrrGGtoOyEc1YvelLDxRNqR8p3eG4UJR1UusSlSmznC0GO8Nd).append("?&view=").append(this.Ta6bjdQXoFeEb44hWQ7kTTTXw2rT1LHw6UX7lms7WU7H0AkpETH9D9EhELUfywQi).append("&offset=").append(str2).toString().replaceAll(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "%20"));
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            HttpURLConnection httpURLConnection2 = httpURLConnection;
            httpURLConnection.setRequestProperty(AUTH.WWW_AUTH_RESP, sb5);
            httpURLConnection2.setRequestProperty(HTTP.CONTENT_TYPE, "application/json");
            int responseCode = httpURLConnection2.getResponseCode();
            new InputStreamReader(httpURLConnection2.getInputStream());
            new BufferedReader(reader);
            BufferedReader bufferedReader2 = bufferedReader;
            new StringBuilder();
            StringBuilder sb6 = sb4;
            while (true) {
                String readLine = bufferedReader2.readLine();
                String str3 = readLine;
                if (readLine == null) {
                    break;
                }
                StringBuilder append = sb6.append(str3);
            }
            bufferedReader2.close();
            new JSONObject(sb6.toString());
            JSONObject jSONObject2 = jSONObject;
            JSONObject jSONObject3 = jSONObject2;
            JSONArray jSONArray3 = jSONObject2.getJSONArray("records");
            for (int i3 = 0; i3 < jSONArray3.length(); i3++) {
                JSONArray put = jSONArray2.put(jSONArray3.getJSONObject(i3));
            }
            if (jSONObject3.has("offset")) {
                RowWithOffset(i2, jSONObject3.getString("offset"), list2, jSONArray2);
                return;
            }
            JSONObject jSONObject4 = jSONArray2.getJSONObject(i2 - 1).getJSONObject("fields");
            JSONObject jSONObject5 = jSONObject4;
            JSONArray names = jSONObject4.names();
            for (int i4 = 0; i4 < names.length(); i4++) {
                boolean add = list2.add(jSONObject5.getString(names.getString(i4)));
            }
            final int i5 = responseCode;
            final List<String> list3 = list2;
            new Runnable(this) {
                private /* synthetic */ MakeroidAirtable hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7;
                }

                public final void run() {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.GotRow(i5, list3);
                }
            };
            this.activity.runOnUiThread(runnable);
        } catch (Exception e) {
            new StringBuilder();
            int e2 = Log.e("GetRow", sb2.append(e.getMessage()).toString());
        }
    }

    @SimpleFunction(description = "Changes the value of a cell")
    public void SetCell(int i, String str, String str2) {
        Runnable runnable;
        final int i2 = i;
        final String str3 = str;
        final String str4 = str2;
        new Runnable(this) {
            private /* synthetic */ MakeroidAirtable hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r8;
            }

            public final void run() {
                StringBuilder sb;
                Throwable th;
                StringBuilder sb2;
                try {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setCell(i2, str3, str4);
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    new StringBuilder();
                    int e = Log.e("SetCell", sb.append(th3.getMessage()).toString());
                    Throwable th4 = th;
                    new StringBuilder();
                    new RuntimeException(sb2.append(th3.getMessage()).toString());
                    throw th4;
                }
            }
        };
        AsynchUtil.runAsynchronously(runnable);
    }

    public void setCell(int i, String str, String str2) throws Exception {
        StringBuilder sb;
        JSONArray jSONArray;
        URL url;
        StringBuilder sb2;
        StringBuilder sb3;
        DataOutputStream dataOutputStream;
        BufferedReader bufferedReader;
        Reader reader;
        Runnable runnable;
        Throwable th;
        StringBuilder sb4;
        String str3 = str;
        String str4 = str2;
        GetAllData();
        new StringBuilder(FusiontablesControl.AUTHORIZATION_HEADER_PREFIX);
        String sb5 = sb.append(this.rtyU3Uj4Fd2cS2DWhNVfozs9qaFOsy3YcN33Msvg0fbnB6MZpRvgk3PrzB8p4A).toString();
        new JSONArray(this.YY8QFJ7NsKl2krKlLP4gKRTKnpLlHQvVopkx7p60xy1hzICdxizXFIQJXbKtydSp);
        String string = jSONArray.getJSONObject(i - 1).getString("id");
        DataOutputStream dataOutputStream2 = null;
        try {
            new StringBuilder("https://api.airtable.com/v0/");
            new URL(sb2.append(this.jKPsNUTq382ltO4Ct4VOTi9lUb0GK32zS6afcWmZUk1wuONzG2KH4rBMniXwxrgH).append("/").append(this.AjvYp2jprm0xITJdrrGGtoOyEc1YvelLDxRNqR8p3eG4UJR1UusSlSmznC0GO8Nd).append("/").append(string).toString().replaceAll(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "%20"));
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            new StringBuilder("{\"fields\": {\"");
            String sb6 = sb3.append(str3).append("\": \"").append(str4).append("\"},\"typecast\": true}").toString();
            httpURLConnection.setRequestProperty(HTTP.CONTENT_TYPE, "application/json");
            httpURLConnection.addRequestProperty(AUTH.WWW_AUTH_RESP, sb5);
            httpURLConnection.setRequestMethod("PATCH");
            httpURLConnection.setDoOutput(true);
            new DataOutputStream(httpURLConnection.getOutputStream());
            DataOutputStream dataOutputStream3 = dataOutputStream;
            dataOutputStream2 = dataOutputStream3;
            dataOutputStream3.writeBytes(sb6);
            dataOutputStream2.flush();
            if (httpURLConnection.getResponseCode() != 200) {
                Throwable th2 = th;
                new StringBuilder("Failed : HTTP error code : ");
                new RuntimeException(sb4.append(httpURLConnection.getResponseCode()).toString());
                throw th2;
            }
            int responseCode = httpURLConnection.getResponseCode();
            new InputStreamReader(httpURLConnection.getInputStream());
            new BufferedReader(reader);
            BufferedReader bufferedReader2 = bufferedReader;
            while (bufferedReader2.readLine() != null) {
                final int i2 = responseCode;
                new Runnable(this) {
                    private /* synthetic */ MakeroidAirtable hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                    {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
                    }

                    public final void run() {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.CellChanged(i2);
                    }
                };
                this.activity.runOnUiThread(runnable);
            }
            httpURLConnection.disconnect();
            dataOutputStream2.close();
        } catch (MalformedURLException e) {
            int e2 = Log.e("Makeroid Airtable", String.valueOf(e));
            if (dataOutputStream2 != null) {
                dataOutputStream2.close();
            }
        } catch (Exception e3) {
            int e4 = Log.e("Makeroid Airtable", String.valueOf(e3));
            if (dataOutputStream2 != null) {
                dataOutputStream2.close();
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            if (dataOutputStream2 != null) {
                dataOutputStream2.close();
            }
            throw th4;
        }
    }

    @SimpleFunction(description = "Deletes the given row")
    public void DeleteRowNum(int i) {
        Runnable runnable;
        final int i2 = i;
        new Runnable(this) {
            private /* synthetic */ MakeroidAirtable hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
            }

            public final void run() {
                StringBuilder sb;
                try {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.deleteRowNum(i2);
                } catch (Throwable th) {
                    new StringBuilder();
                    int e = Log.e("DeleteRowNum", sb.append(th.getMessage()).toString());
                }
            }
        };
        AsynchUtil.runAsynchronously(runnable);
    }

    public void deleteRowNum(int i) throws Exception {
        StringBuilder sb;
        JSONArray jSONArray;
        URL url;
        StringBuilder sb2;
        BufferedReader bufferedReader;
        Reader reader;
        StringBuilder sb3;
        Runnable runnable;
        GetAllData();
        new StringBuilder(FusiontablesControl.AUTHORIZATION_HEADER_PREFIX);
        String sb4 = sb.append(this.rtyU3Uj4Fd2cS2DWhNVfozs9qaFOsy3YcN33Msvg0fbnB6MZpRvgk3PrzB8p4A).toString();
        new JSONArray(this.YY8QFJ7NsKl2krKlLP4gKRTKnpLlHQvVopkx7p60xy1hzICdxizXFIQJXbKtydSp);
        String string = jSONArray.getJSONObject(i - 1).getString("id");
        new StringBuilder("https://api.airtable.com/v0/");
        new URL(sb2.append(this.jKPsNUTq382ltO4Ct4VOTi9lUb0GK32zS6afcWmZUk1wuONzG2KH4rBMniXwxrgH).append("/").append(this.AjvYp2jprm0xITJdrrGGtoOyEc1YvelLDxRNqR8p3eG4UJR1UusSlSmznC0GO8Nd).append("/").append(string).toString().replaceAll(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "%20"));
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        HttpURLConnection httpURLConnection2 = httpURLConnection;
        httpURLConnection.setRequestProperty(AUTH.WWW_AUTH_RESP, sb4);
        httpURLConnection2.setRequestProperty(HTTP.CONTENT_TYPE, "application/json");
        httpURLConnection2.setRequestMethod(HttpDelete.METHOD_NAME);
        int responseCode = httpURLConnection2.getResponseCode();
        new InputStreamReader(httpURLConnection2.getInputStream());
        new BufferedReader(reader);
        BufferedReader bufferedReader2 = bufferedReader;
        new StringBuilder();
        StringBuilder sb5 = sb3;
        while (true) {
            String readLine = bufferedReader2.readLine();
            String str = readLine;
            if (readLine != null) {
                StringBuilder append = sb5.append(str);
            } else {
                bufferedReader2.close();
                final int i2 = responseCode;
                new Runnable(this) {
                    private /* synthetic */ MakeroidAirtable hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                    {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
                    }

                    public final void run() {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.DeletedRowByNumber(i2);
                    }
                };
                this.activity.runOnUiThread(runnable);
                return;
            }
        }
    }

    @SimpleFunction(description = "Creates a new row")
    public void CreateRow(YailList yailList, YailList yailList2) {
        Runnable runnable;
        final YailList yailList3 = yailList;
        final YailList yailList4 = yailList2;
        new Runnable(this) {
            private /* synthetic */ MakeroidAirtable hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7;
            }

            public final void run() {
                StringBuilder sb;
                try {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.createRow(yailList3, yailList4);
                } catch (Throwable th) {
                    new StringBuilder();
                    int e = Log.e("CreateRow", sb.append(th.getMessage()).toString());
                }
            }
        };
        AsynchUtil.runAsynchronously(runnable);
    }

    public void createRow(YailList yailList, YailList yailList2) throws Exception {
        StringBuilder sb;
        JSONObject jSONObject;
        URL url;
        StringBuilder sb2;
        StringBuilder sb3;
        DataOutputStream dataOutputStream;
        BufferedReader bufferedReader;
        Reader reader;
        Runnable runnable;
        Throwable th;
        StringBuilder sb4;
        new StringBuilder(FusiontablesControl.AUTHORIZATION_HEADER_PREFIX);
        String sb5 = sb.append(this.rtyU3Uj4Fd2cS2DWhNVfozs9qaFOsy3YcN33Msvg0fbnB6MZpRvgk3PrzB8p4A).toString();
        String[] stringArray = yailList.toStringArray();
        String[] stringArray2 = yailList2.toStringArray();
        new JSONObject();
        JSONObject jSONObject2 = jSONObject;
        for (int i = 0; i < stringArray.length; i++) {
            JSONObject put = jSONObject2.put(stringArray[i], stringArray2[i]);
        }
        String jSONObject3 = jSONObject2.toString();
        DataOutputStream dataOutputStream2 = null;
        try {
            new StringBuilder("https://api.airtable.com/v0/");
            new URL(sb2.append(this.jKPsNUTq382ltO4Ct4VOTi9lUb0GK32zS6afcWmZUk1wuONzG2KH4rBMniXwxrgH).append("/").append(this.AjvYp2jprm0xITJdrrGGtoOyEc1YvelLDxRNqR8p3eG4UJR1UusSlSmznC0GO8Nd).toString().replaceAll(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "%20"));
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            new StringBuilder("{\"fields\":");
            String sb6 = sb3.append(jSONObject3).append(",\"typecast\": true}").toString();
            httpURLConnection.setRequestProperty(HTTP.CONTENT_TYPE, "application/json");
            httpURLConnection.addRequestProperty(AUTH.WWW_AUTH_RESP, sb5);
            httpURLConnection.setRequestMethod(HttpPost.METHOD_NAME);
            httpURLConnection.setDoOutput(true);
            new DataOutputStream(httpURLConnection.getOutputStream());
            DataOutputStream dataOutputStream3 = dataOutputStream;
            dataOutputStream2 = dataOutputStream3;
            dataOutputStream3.writeBytes(sb6);
            dataOutputStream2.flush();
            if (httpURLConnection.getResponseCode() != 200) {
                Throwable th2 = th;
                new StringBuilder("Failed : HTTP error code : ");
                new RuntimeException(sb4.append(httpURLConnection.getResponseCode()).toString());
                throw th2;
            }
            int responseCode = httpURLConnection.getResponseCode();
            new InputStreamReader(httpURLConnection.getInputStream());
            new BufferedReader(reader);
            BufferedReader bufferedReader2 = bufferedReader;
            while (bufferedReader2.readLine() != null) {
                final int i2 = responseCode;
                new Runnable(this) {
                    private /* synthetic */ MakeroidAirtable hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                    {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
                    }

                    public final void run() {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.RowCreated(i2);
                    }
                };
                this.activity.runOnUiThread(runnable);
            }
            httpURLConnection.disconnect();
            dataOutputStream2.close();
        } catch (MalformedURLException e) {
            int e2 = Log.e("Makeroid Airtable", String.valueOf(e));
            if (dataOutputStream2 != null) {
                dataOutputStream2.close();
            }
        } catch (Exception e3) {
            int e4 = Log.e("Makeroid Airtable", String.valueOf(e3));
            if (dataOutputStream2 != null) {
                dataOutputStream2.close();
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            if (dataOutputStream2 != null) {
                dataOutputStream2.close();
            }
            throw th4;
        }
    }

    @SimpleFunction(description = "Updates the given row data")
    public void UpdateRowByNum(int i, YailList yailList, YailList yailList2) {
        Runnable runnable;
        final int i2 = i;
        final YailList yailList3 = yailList;
        final YailList yailList4 = yailList2;
        new Runnable(this) {
            private /* synthetic */ MakeroidAirtable hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r8;
            }

            public final void run() {
                StringBuilder sb;
                try {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.updateRowByNum(i2, yailList3, yailList4);
                } catch (Throwable th) {
                    new StringBuilder();
                    int e = Log.e("UpdateRowByNum", sb.append(th.getMessage()).toString());
                }
            }
        };
        AsynchUtil.runAsynchronously(runnable);
    }

    public void updateRowByNum(int i, YailList yailList, YailList yailList2) throws Exception {
        StringBuilder sb;
        JSONArray jSONArray;
        JSONObject jSONObject;
        URL url;
        StringBuilder sb2;
        StringBuilder sb3;
        DataOutputStream dataOutputStream;
        BufferedReader bufferedReader;
        Reader reader;
        Runnable runnable;
        Throwable th;
        StringBuilder sb4;
        GetAllData();
        new StringBuilder(FusiontablesControl.AUTHORIZATION_HEADER_PREFIX);
        String sb5 = sb.append(this.rtyU3Uj4Fd2cS2DWhNVfozs9qaFOsy3YcN33Msvg0fbnB6MZpRvgk3PrzB8p4A).toString();
        String[] stringArray = yailList.toStringArray();
        String[] stringArray2 = yailList2.toStringArray();
        new JSONArray(this.YY8QFJ7NsKl2krKlLP4gKRTKnpLlHQvVopkx7p60xy1hzICdxizXFIQJXbKtydSp);
        String string = jSONArray.getJSONObject(i - 1).getString("id");
        new JSONObject();
        JSONObject jSONObject2 = jSONObject;
        for (int i2 = 0; i2 < stringArray.length; i2++) {
            JSONObject put = jSONObject2.put(stringArray[i2], stringArray2[i2]);
        }
        String jSONObject3 = jSONObject2.toString();
        DataOutputStream dataOutputStream2 = null;
        try {
            new StringBuilder("https://api.airtable.com/v0/");
            new URL(sb2.append(this.jKPsNUTq382ltO4Ct4VOTi9lUb0GK32zS6afcWmZUk1wuONzG2KH4rBMniXwxrgH).append("/").append(this.AjvYp2jprm0xITJdrrGGtoOyEc1YvelLDxRNqR8p3eG4UJR1UusSlSmznC0GO8Nd).append("/").append(string).toString().replaceAll(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "%20"));
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            new StringBuilder("{\"fields\":");
            String sb6 = sb3.append(jSONObject3).append(",\"typecast\": true}").toString();
            httpURLConnection.setRequestProperty(HTTP.CONTENT_TYPE, "application/json");
            httpURLConnection.addRequestProperty(AUTH.WWW_AUTH_RESP, sb5);
            httpURLConnection.setRequestMethod(HttpPut.METHOD_NAME);
            httpURLConnection.setDoOutput(true);
            new DataOutputStream(httpURLConnection.getOutputStream());
            DataOutputStream dataOutputStream3 = dataOutputStream;
            dataOutputStream2 = dataOutputStream3;
            dataOutputStream3.writeBytes(sb6);
            dataOutputStream2.flush();
            if (httpURLConnection.getResponseCode() != 200) {
                Throwable th2 = th;
                new StringBuilder("Failed : HTTP error code : ");
                new RuntimeException(sb4.append(httpURLConnection.getResponseCode()).toString());
                throw th2;
            }
            new InputStreamReader(httpURLConnection.getInputStream());
            new BufferedReader(reader);
            BufferedReader bufferedReader2 = bufferedReader;
            while (bufferedReader2.readLine() != null) {
                final int responseCode = httpURLConnection.getResponseCode();
                new Runnable(this) {
                    private /* synthetic */ MakeroidAirtable hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                    {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
                    }

                    public final void run() {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.RowUpdated(responseCode);
                    }
                };
                this.activity.runOnUiThread(runnable);
            }
            httpURLConnection.disconnect();
            dataOutputStream2.close();
        } catch (Exception e) {
            int e2 = Log.e("Makeroid Airtable", String.valueOf(e));
            if (dataOutputStream2 != null) {
                dataOutputStream2.close();
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            if (dataOutputStream2 != null) {
                dataOutputStream2.close();
            }
            throw th4;
        }
    }

    @SimpleFunction(description = "Gets all rows")
    public void GetAllRows() {
        Runnable runnable;
        new Runnable(this) {
            private /* synthetic */ MakeroidAirtable hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void run() {
                StringBuilder sb;
                try {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.GetAllData();
                } catch (Throwable th) {
                    new StringBuilder();
                    int e = Log.e("GetAllRows", sb.append(th.getMessage()).toString());
                }
            }
        };
        AsynchUtil.runAsynchronously(runnable);
    }

    @SimpleEvent(description = "Triggered when receiving cell data. ResponseCode is a number, the other ones are strings")
    public void GotCell(int i, String str, String str2, String str3) {
        Object[] objArr = new Object[4];
        objArr[0] = Integer.valueOf(i);
        Object[] objArr2 = objArr;
        objArr2[1] = str;
        Object[] objArr3 = objArr2;
        objArr3[2] = str2;
        Object[] objArr4 = objArr3;
        objArr4[3] = str3;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "GotCell", objArr4);
    }

    @SimpleEvent(description = "Triggered when receiving column data. ResponseCode is a number, the other ones are lists")
    public void GotColumn(int i, List<String> list, List<String> list2, List<String> list3) {
        Object[] objArr = new Object[4];
        objArr[0] = Integer.valueOf(i);
        Object[] objArr2 = objArr;
        objArr2[1] = list;
        Object[] objArr3 = objArr2;
        objArr3[2] = list2;
        Object[] objArr4 = objArr3;
        objArr4[3] = list3;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "GotColumn", objArr4);
    }

    @SimpleEvent(description = "Triggered when receiving row data. ResponseCode is a number, Values is a list")
    public void GotRow(int i, List<String> list) {
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(i);
        Object[] objArr2 = objArr;
        objArr2[1] = list;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "GotRow", objArr2);
    }

    @SimpleEvent(description = "Triggered when changing cell data. ResponseCode is a number")
    public void CellChanged(int i) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "CellChanged", Integer.valueOf(i));
    }

    @SimpleEvent(description = "Triggered when destroying a row. ResponseCode is a number")
    public void DeletedRowByNumber(int i) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "DeletedRowByNumber", Integer.valueOf(i));
    }

    @SimpleEvent(description = "Triggered when creating a row. ResponseCode is a number")
    public void RowCreated(int i) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "RowCreated", Integer.valueOf(i));
    }

    @SimpleEvent(description = "Triggered when updating a row. ResponseCode is a number")
    public void RowUpdated(int i) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "RowUpdated", Integer.valueOf(i));
    }

    @SimpleEvent(description = "Triggered when getting all rows. ResponseCode is a number, ResponseContent is a string")
    public void GotAllRows(int i, String str, int i2) {
        Object[] objArr = new Object[3];
        objArr[0] = Integer.valueOf(i);
        Object[] objArr2 = objArr;
        objArr2[1] = str;
        Object[] objArr3 = objArr2;
        objArr3[2] = Integer.valueOf(i2);
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "GotAllRows", objArr3);
    }
}

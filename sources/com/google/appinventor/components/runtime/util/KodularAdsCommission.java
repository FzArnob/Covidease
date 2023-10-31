package com.google.appinventor.components.runtime.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.Form;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import org.shaded.apache.http.client.methods.HttpGet;
import org.shaded.apache.http.protocol.HTTP;

public class KodularAdsCommission {
    private SharedPreferences B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    private String LOG_TAG = "KodularAdsCommission";
    private Context context;
    private KodularBilling hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    public KodularAdsCommission(Context context2, Form form) {
        StringBuilder sb;
        KodularBilling kodularBilling;
        C1160a aVar;
        Context context3 = context2;
        Form form2 = form;
        this.context = context3;
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = this.context.getSharedPreferences(getKodularPackageName(), 0);
        SharedPreferences sharedPreferences = this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
        new StringBuilder();
        if (!sharedPreferences.contains(sb.append(getKodularPackageName()).append(".kcv").toString())) {
            new C1160a((byte) 0);
            AsyncTask execute = aVar.execute(new Form[]{form2});
        }
        new KodularBilling(context3, form2);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = kodularBilling;
    }

    public float getCommision(String str, String str2) {
        boolean z;
        float f;
        StringBuilder sb;
        String str3 = str;
        String str4 = str2;
        float f2 = 0.0f;
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hasCommissionRemoved()) {
            return 0.0f;
        }
        try {
            SharedPreferences sharedPreferences = this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
            new StringBuilder();
            float floatValue = Float.valueOf(sharedPreferences.getString(sb.append(getKodularPackageName()).append(".kcv").toString(), "30").trim()).floatValue();
            float f3 = floatValue;
            f2 = floatValue / 100.0f;
        } catch (Exception e) {
            int e2 = Log.e(this.LOG_TAG, String.valueOf(e));
        }
        if (this.context.getPackageName().equals(getKodularPackageName()) || this.context.getPackageName().equals(getKodularPackageName().replace("io.kodular", "com.appybuilder")) || this.context.getPackageName().equals(getKodularPackageName().replace("io.kodular", "com.thunkable"))) {
            z = false;
        } else {
            z = true;
        }
        if (z) {
            f2 += 0.04f;
        }
        String str5 = str3;
        boolean z2 = true;
        switch (str5.hashCode()) {
            case -1414265340:
                if (str5.equals("amazon")) {
                    z2 = true;
                    break;
                }
                break;
            case -1249910051:
                if (str5.equals("adcolony")) {
                    z2 = false;
                    break;
                }
                break;
            case 92668925:
                if (str5.equals("admob")) {
                    z2 = true;
                    break;
                }
                break;
            case 111433589:
                if (str5.equals("unity")) {
                    z2 = true;
                    break;
                }
                break;
            case 497130182:
                if (str5.equals("facebook")) {
                    z2 = true;
                    break;
                }
                break;
            case 1179703863:
                if (str5.equals("applovin")) {
                    z2 = true;
                    break;
                }
                break;
            case 1316799103:
                if (str5.equals(Component.COMMISSION_BANNER_ADS_NETWORK_DEFAULT)) {
                    z2 = true;
                    break;
                }
                break;
            case 1560923121:
                if (str5.equals("leadbolt")) {
                    z2 = true;
                    break;
                }
                break;
        }
        switch (z2) {
            case false:
                String str6 = str4;
                boolean z3 = true;
                switch (str6.hashCode()) {
                    case 604727084:
                        if (str6.equals("interstitial")) {
                            z3 = false;
                            break;
                        }
                        break;
                }
                switch (z3) {
                    case false:
                        f = f2 + 0.08f;
                        break;
                    default:
                        f = f2 + 0.15f;
                        break;
                }
            case true:
                String str7 = str4;
                boolean z4 = true;
                switch (str7.hashCode()) {
                    case -1396342996:
                        if (str7.equals("banner")) {
                            z4 = false;
                            break;
                        }
                        break;
                    case 112202875:
                        if (str7.equals("video")) {
                            z4 = true;
                            break;
                        }
                        break;
                    case 604727084:
                        if (str7.equals("interstitial")) {
                            z4 = true;
                            break;
                        }
                        break;
                }
                switch (z4) {
                    case false:
                        f = f2 + 0.08f;
                        break;
                    case true:
                        f = f2 + 0.11f;
                        break;
                    case true:
                        f = f2 + 0.14f;
                        break;
                    default:
                        f = f2 + 0.15f;
                        break;
                }
            case true:
                String str8 = str4;
                boolean z5 = true;
                switch (str8.hashCode()) {
                    case -1396342996:
                        if (str8.equals("banner")) {
                            z5 = false;
                            break;
                        }
                        break;
                    case 604727084:
                        if (str8.equals("interstitial")) {
                            z5 = true;
                            break;
                        }
                        break;
                }
                switch (z5) {
                    case false:
                        f = f2 + 0.06f;
                        break;
                    case true:
                        f = f2 + 0.09f;
                        break;
                    default:
                        f = f2 + 0.15f;
                        break;
                }
            case true:
                String str9 = str4;
                boolean z6 = true;
                switch (str9.hashCode()) {
                    case 604727084:
                        if (str9.equals("interstitial")) {
                            z6 = false;
                            break;
                        }
                        break;
                }
                switch (z6) {
                    case false:
                        f = f2 + 0.08f;
                        break;
                    default:
                        f = f2 + 0.15f;
                        break;
                }
            case true:
                String str10 = str4;
                boolean z7 = true;
                switch (str10.hashCode()) {
                    case -1396342996:
                        if (str10.equals("banner")) {
                            z7 = false;
                            break;
                        }
                        break;
                    case 112202875:
                        if (str10.equals("video")) {
                            z7 = true;
                            break;
                        }
                        break;
                    case 604727084:
                        if (str10.equals("interstitial")) {
                            z7 = true;
                            break;
                        }
                        break;
                }
                switch (z7) {
                    case false:
                        f = f2 + 0.06f;
                        break;
                    case true:
                        f = f2 + 0.09f;
                        break;
                    case true:
                        f = f2 + 0.12f;
                        break;
                    default:
                        f = f2 + 0.15f;
                        break;
                }
            case true:
                String str11 = str4;
                boolean z8 = true;
                switch (str11.hashCode()) {
                    case 112202875:
                        if (str11.equals("video")) {
                            z8 = true;
                            break;
                        }
                        break;
                    case 604727084:
                        if (str11.equals("interstitial")) {
                            z8 = false;
                            break;
                        }
                        break;
                }
                switch (z8) {
                    case false:
                        f = f2 + 0.09f;
                        break;
                    case true:
                        f = f2 + 0.12f;
                        break;
                    default:
                        f = f2 + 0.15f;
                        break;
                }
            case true:
                String str12 = str4;
                boolean z9 = true;
                switch (str12.hashCode()) {
                    case -1396342996:
                        if (str12.equals("banner")) {
                            z9 = false;
                            break;
                        }
                        break;
                    case 604727084:
                        if (str12.equals("interstitial")) {
                            z9 = true;
                            break;
                        }
                        break;
                }
                switch (z9) {
                    case false:
                        f = f2 + 0.05f;
                        break;
                    case true:
                        f = f2 + 0.08f;
                        break;
                    default:
                        f = f2 + 0.15f;
                        break;
                }
            case true:
                String str13 = str4;
                boolean z10 = true;
                switch (str13.hashCode()) {
                    case 604727084:
                        if (str13.equals("interstitial")) {
                            z10 = false;
                            break;
                        }
                        break;
                }
                switch (z10) {
                    case false:
                        f = f2 + 0.08f;
                        break;
                    default:
                        f = f2 + 0.15f;
                        break;
                }
            default:
                int hashCode = str4.hashCode();
                f = f2 + 0.2f;
                break;
        }
        int i = Log.i(this.LOG_TAG, "Final Commission: ".concat(String.valueOf(f)));
        return f;
    }

    private String getKodularPackageName() {
        String packageName = this.context.getPackageName();
        try {
            PackageManager packageManager = this.context.getPackageManager();
            return packageManager.resolveActivity(packageManager.getLaunchIntentForPackage(packageName), 65536).activityInfo.name.replaceAll(".Screen1", "");
        } catch (Exception e) {
            return packageName;
        }
    }

    /* renamed from: com.google.appinventor.components.runtime.util.KodularAdsCommission$a */
    static class C1160a extends AsyncTask<Form, Void, String> {
        private String LOG_TAG;
        private Form hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO;

        private C1160a() {
            this.LOG_TAG = "KodularAdsCommission";
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ C1160a(byte b) {
            this();
            byte b2 = b;
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            StringBuilder sb;
            String str = (String) obj;
            SharedPreferences sharedPreferences = this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.$context().getSharedPreferences(this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.getKodularPackageName(), 0);
            if (str != null && sharedPreferences != null) {
                SharedPreferences.Editor edit = sharedPreferences.edit();
                new StringBuilder();
                edit.putString(sb.append(this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.getKodularPackageName()).append(".kcv").toString(), str).apply();
            }
        }

        /* access modifiers changed from: private */
        /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME */
        public String doInBackground(Form... formArr) {
            StringBuilder sb;
            URL url;
            BufferedInputStream bufferedInputStream;
            ByteArrayOutputStream byteArrayOutputStream;
            JSONObject jSONObject;
            try {
                this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = formArr[0];
                ApplicationInfo applicationInfo = this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.$context().getPackageManager().getApplicationInfo(this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.getPackageName(), 128);
                ApplicationInfo applicationInfo2 = applicationInfo;
                Bundle bundle = applicationInfo.metaData;
                Bundle bundle2 = bundle;
                if (bundle == null || applicationInfo2 == null) {
                    int e = Log.e(this.LOG_TAG, "No bundle or applicationInfo!");
                    return null;
                }
                String string = bundle2.getString("Kodular_RCWTFYP-BYDNKHMWRIT-DNLATT");
                String str = string;
                if (string != null) {
                    new StringBuilder("https://api.creator.kodular.io/commission/v1?h=");
                    new URL(sb.append(str).append("&r=").append(this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.getKodularPackageName()).append("&c=").append(this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.getPackageName()).toString());
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    HttpURLConnection httpURLConnection2 = httpURLConnection;
                    httpURLConnection.addRequestProperty(HTTP.USER_AGENT, "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0");
                    httpURLConnection2.setRequestMethod(HttpGet.METHOD_NAME);
                    httpURLConnection2.connect();
                    new BufferedInputStream(httpURLConnection2.getInputStream());
                    BufferedInputStream bufferedInputStream2 = bufferedInputStream;
                    new ByteArrayOutputStream();
                    ByteArrayOutputStream byteArrayOutputStream2 = byteArrayOutputStream;
                    for (int read = bufferedInputStream2.read(); read != -1; read = bufferedInputStream2.read()) {
                        byteArrayOutputStream2.write((byte) read);
                    }
                    String byteArrayOutputStream3 = byteArrayOutputStream2.toString();
                    bufferedInputStream2.close();
                    byteArrayOutputStream2.close();
                    new JSONObject(byteArrayOutputStream3);
                    JSONObject jSONObject2 = jSONObject;
                    JSONObject jSONObject3 = jSONObject2;
                    if (jSONObject2.getBoolean("success")) {
                        return jSONObject3.getString("value");
                    }
                    int e2 = Log.e(this.LOG_TAG, "CANNOT RETREIVE COMMISSION");
                    return null;
                }
                int e3 = Log.e(this.LOG_TAG, "No commission string!");
                return "100";
            } catch (Exception e4) {
                int e5 = Log.e(this.LOG_TAG, String.valueOf(e4));
                return null;
            }
        }
    }
}

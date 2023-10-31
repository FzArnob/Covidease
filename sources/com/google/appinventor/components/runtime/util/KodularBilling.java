package com.google.appinventor.components.runtime.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import com.google.appinventor.components.runtime.Form;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import org.shaded.apache.http.client.methods.HttpGet;
import org.shaded.apache.http.protocol.HTTP;

public class KodularBilling {
    private SharedPreferences B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    private String LOG_TAG = "KodularBilling";
    private Context context;
    private Form form;

    public KodularBilling(Context context2, Form form2) {
        this.context = context2;
        this.form = form2;
        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = this.context.getSharedPreferences(this.form.getKodularPackageName(), 0);
    }

    public void update() {
        C1161a aVar;
        C1162b bVar;
        new C1161a((byte) 0);
        AsyncTask execute = aVar.execute(new Form[]{this.form});
        new C1162b((byte) 0);
        AsyncTask execute2 = bVar.execute(new Form[]{this.form});
    }

    public boolean hasBrandingRemoved() {
        StringBuilder sb;
        SharedPreferences sharedPreferences = this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
        new StringBuilder();
        return sharedPreferences.getBoolean(sb.append(this.form.getKodularPackageName()).append(".kbb").toString(), false);
    }

    public boolean hasCommissionRemoved() {
        StringBuilder sb;
        SharedPreferences sharedPreferences = this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
        new StringBuilder();
        return sharedPreferences.getBoolean(sb.append(this.form.getKodularPackageName()).append(".kbc").toString(), false);
    }

    /* renamed from: com.google.appinventor.components.runtime.util.KodularBilling$a */
    static class C1161a extends AsyncTask<Form, Void, Boolean> {
        private String LOG_TAG;
        private Form hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO;

        private C1161a() {
            this.LOG_TAG = "KodularBranding";
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ C1161a(byte b) {
            this();
            byte b2 = b;
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            StringBuilder sb;
            Boolean bool = (Boolean) obj;
            SharedPreferences sharedPreferences = this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.$context().getSharedPreferences(this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.getKodularPackageName(), 0);
            if (bool != null && sharedPreferences != null) {
                SharedPreferences.Editor edit = sharedPreferences.edit();
                new StringBuilder();
                edit.putBoolean(sb.append(this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.getKodularPackageName()).append(".kbb").toString(), bool.booleanValue()).apply();
            }
        }

        /* access modifiers changed from: private */
        /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME */
        public Boolean doInBackground(Form... formArr) {
            StringBuilder sb;
            URL url;
            BufferedInputStream bufferedInputStream;
            ByteArrayOutputStream byteArrayOutputStream;
            JSONObject jSONObject;
            try {
                this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = formArr[0];
                new StringBuilder("https://api.creator.kodular.io/billing/v1?p=branding&i=");
                new URL(sb.append(this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.getAppId()).toString());
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
                    return Boolean.valueOf(jSONObject3.getBoolean("billed"));
                }
                int e = Log.e(this.LOG_TAG, "CANNOT RETREIVE BILLED BRANDING");
                return Boolean.FALSE;
            } catch (Exception e2) {
                int e3 = Log.e(this.LOG_TAG, String.valueOf(e2));
                return Boolean.FALSE;
            }
        }
    }

    /* renamed from: com.google.appinventor.components.runtime.util.KodularBilling$b */
    static class C1162b extends AsyncTask<Form, Void, Boolean> {
        private String LOG_TAG;
        private Form hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO;

        private C1162b() {
            this.LOG_TAG = "KodularCommission";
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ C1162b(byte b) {
            this();
            byte b2 = b;
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            StringBuilder sb;
            Boolean bool = (Boolean) obj;
            SharedPreferences sharedPreferences = this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.$context().getSharedPreferences(this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.getKodularPackageName(), 0);
            if (bool != null && sharedPreferences != null) {
                SharedPreferences.Editor edit = sharedPreferences.edit();
                new StringBuilder();
                edit.putBoolean(sb.append(this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.getKodularPackageName()).append(".kbc").toString(), bool.booleanValue()).apply();
            }
        }

        /* access modifiers changed from: private */
        /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME */
        public Boolean doInBackground(Form... formArr) {
            StringBuilder sb;
            URL url;
            BufferedInputStream bufferedInputStream;
            ByteArrayOutputStream byteArrayOutputStream;
            JSONObject jSONObject;
            try {
                this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = formArr[0];
                new StringBuilder("https://api.creator.kodular.io/billing/v1?p=commission&i=");
                new URL(sb.append(this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.getAppId()).toString());
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
                    return Boolean.valueOf(jSONObject3.getBoolean("billed"));
                }
                int e = Log.e(this.LOG_TAG, "CANNOT RETREIVE BILLED COMMISSION");
                return Boolean.FALSE;
            } catch (Exception e2) {
                int e3 = Log.e(this.LOG_TAG, String.valueOf(e2));
                return Boolean.FALSE;
            }
        }
    }
}

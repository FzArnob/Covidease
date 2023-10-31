package com.google.appinventor.components.runtime.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.google.appinventor.components.runtime.Form;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import org.shaded.apache.http.client.methods.HttpGet;

public class KodularContentProtection {
    private static final String LOG_TAG = "Content Protection";
    private String CURRENT_COUNTRY = "";
    private String[] ENGLISH;
    private String[] GERMAN;
    private String[] SPANISH;
    /* access modifiers changed from: private */
    public AlertDialog alertDialog;
    private boolean canStartValidation = true;
    /* access modifiers changed from: private */
    public String failReason = "";
    /* access modifiers changed from: private */
    public boolean isAccepted = false;
    /* access modifiers changed from: private */
    public Activity kActivity;
    private String kAppId = "";
    public OnValidationResultListener kOnValidationResultListener;
    private TelephonyManager telephonyManager;

    public interface OnValidationResultListener {
        void onResult(boolean z, boolean z2, String str);
    }

    static /* synthetic */ boolean access$202(KodularContentProtection kodularContentProtection, boolean z) {
        boolean z2 = z;
        boolean z3 = z2;
        kodularContentProtection.isAccepted = z3;
        return z2;
    }

    public KodularContentProtection(Activity activity) {
        Activity activity2 = activity;
        String[] strArr = new String[4];
        strArr[0] = "Handlung erforderlich!";
        String[] strArr2 = strArr;
        strArr2[1] = "Ihre App wurde nicht für das Anzeigen von Werbung genehmigt. Bitte beantragen Sie die Aktivierung in ihren Kodular Account.";
        String[] strArr3 = strArr2;
        strArr3[2] = "Anfrage";
        String[] strArr4 = strArr3;
        strArr4[3] = "Später";
        this.GERMAN = strArr4;
        String[] strArr5 = new String[4];
        strArr5[0] = "Acción requerida";
        String[] strArr6 = strArr5;
        strArr6[1] = "Esta app no ha sido aprobada para mostrar anuncios. Por favor, ve a My Kodular y envía tu solicitud.";
        String[] strArr7 = strArr6;
        strArr7[2] = "Solicitar ahora";
        String[] strArr8 = strArr7;
        strArr8[3] = "Luego";
        this.SPANISH = strArr8;
        String[] strArr9 = new String[4];
        strArr9[0] = "Action required!";
        String[] strArr10 = strArr9;
        strArr10[1] = "Your app has not been approved to serve ads. Please, go to My Kodular and request its activation.";
        String[] strArr11 = strArr10;
        strArr11[2] = "Request now";
        String[] strArr12 = strArr11;
        strArr12[3] = "Later";
        this.ENGLISH = strArr12;
        this.kActivity = activity2;
        this.telephonyManager = (TelephonyManager) activity2.getSystemService("phone");
        if (this.telephonyManager == null || this.telephonyManager.getNetworkCountryIso() == null) {
            this.CURRENT_COUNTRY = "default";
            return;
        }
        this.CURRENT_COUNTRY = this.telephonyManager.getNetworkCountryIso();
    }

    public void setOnValidationResultListener(OnValidationResultListener onValidationResultListener) {
        OnValidationResultListener onValidationResultListener2 = onValidationResultListener;
        this.kOnValidationResultListener = onValidationResultListener2;
    }

    public void startContentValidation(String str) {
        C1165a aVar;
        this.kAppId = str;
        if (!this.canStartValidation) {
            int w = Log.w(LOG_TAG, "Can not load a new ad until the old requested one is finished loading or was shown.");
            setInterfaceHelper(false, false, "Can not load a new ad until the old requested one is finished loading or was shown.");
        } else if (isInstalledFromGooglePlay()) {
            int i = Log.i(LOG_TAG, "Application was installed from play store.");
            setInterfaceHelper(true, true, "");
        } else if (isConnected()) {
            int i2 = Log.i(LOG_TAG, "Start now application content check.");
            new C1165a(this, (byte) 0);
            AsyncTask execute = aVar.execute(new String[0]);
        } else {
            int w2 = Log.w(LOG_TAG, "There was an internal error to load the ad. No internet connection.");
            setInterfaceHelper(false, false, "There was an internal error to load the ad. No internet connection.");
        }
    }

    @SuppressLint({"StaticFieldLeak"})
    /* renamed from: com.google.appinventor.components.runtime.util.KodularContentProtection$a */
    class C1165a extends AsyncTask<String, String, Boolean> {
        private /* synthetic */ KodularContentProtection hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        /* access modifiers changed from: protected */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            Object[] objArr2 = objArr;
            if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isApproved()) {
                boolean access$202 = KodularContentProtection.access$202(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, true);
                return Boolean.TRUE;
            }
            boolean access$2022 = KodularContentProtection.access$202(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, false);
            return Boolean.FALSE;
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setInterfaceHelper(((Boolean) obj).booleanValue(), this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isAccepted, this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.failReason);
        }

        private C1165a(KodularContentProtection kodularContentProtection) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = kodularContentProtection;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ C1165a(KodularContentProtection kodularContentProtection, byte b) {
            this(kodularContentProtection);
            byte b2 = b;
        }
    }

    /* access modifiers changed from: private */
    public void setInterfaceHelper(boolean z, boolean z2, String str) {
        boolean z3 = z;
        boolean z4 = z2;
        String str2 = str;
        if (this.kOnValidationResultListener != null) {
            this.kOnValidationResultListener.onResult(z3, z4, str2);
        }
        if (str2.equals("Your app has not been approved to serve ads. Please, go to My Kodular and request its activation.")) {
            showWarningDialog();
        }
        this.canStartValidation = true;
    }

    private void showWarningDialog() {
        AlertDialog.Builder builder;
        DialogInterface.OnClickListener onClickListener;
        DialogInterface.OnClickListener onClickListener2;
        AlertDialog.Builder builder2;
        if (Build.VERSION.SDK_INT >= 21) {
            new AlertDialog.Builder(this.kActivity, 16974393);
            this.alertDialog = builder2.create();
        } else {
            new AlertDialog.Builder(this.kActivity, 16974130);
            this.alertDialog = builder.create();
        }
        this.alertDialog.setTitle(getResult(0));
        this.alertDialog.setMessage(getResult(1));
        this.alertDialog.setCancelable(false);
        new DialogInterface.OnClickListener(this) {
            private /* synthetic */ KodularContentProtection hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                Intent intent;
                DialogInterface dialogInterface2 = dialogInterface;
                int i2 = i;
                try {
                    new Intent("android.intent.action.VIEW", Uri.parse("https://my.kodular.io/"));
                    ((Form) this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.kActivity).startActivity(intent);
                } catch (Exception e) {
                    int e2 = Log.e(KodularContentProtection.LOG_TAG, String.valueOf(e));
                }
            }
        };
        this.alertDialog.setButton(-1, getResult(2), onClickListener);
        new DialogInterface.OnClickListener(this) {
            private /* synthetic */ KodularContentProtection hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                DialogInterface dialogInterface2 = dialogInterface;
                int i2 = i;
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.alertDialog.dismiss();
            }
        };
        this.alertDialog.setButton(-2, getResult(3), onClickListener2);
        this.alertDialog.show();
    }

    private String getResult(int i) {
        int i2 = i;
        String lowerCase = this.CURRENT_COUNTRY.toLowerCase();
        boolean z = true;
        switch (lowerCase.hashCode()) {
            case 3123:
                if (lowerCase.equals("at")) {
                    z = true;
                    break;
                }
                break;
            case 3173:
                if (lowerCase.equals("ch")) {
                    z = true;
                    break;
                }
                break;
            case ErrorMessages.ERROR_INDEX_MISSING_IN_LIST:
                if (lowerCase.equals("de")) {
                    z = false;
                    break;
                }
                break;
            case 3246:
                if (lowerCase.equals("es")) {
                    z = true;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
            case true:
            case true:
                return this.GERMAN[i2];
            case true:
                return this.SPANISH[i2];
            default:
                return this.ENGLISH[i2];
        }
    }

    @SuppressLint({"MissingPermission"})
    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.kActivity.getSystemService("connectivity");
        ConnectivityManager connectivityManager2 = connectivityManager;
        if (connectivityManager == null || connectivityManager2.getActiveNetworkInfo() == null || !connectivityManager2.getActiveNetworkInfo().isAvailable() || !connectivityManager2.getActiveNetworkInfo().isConnected()) {
            return false;
        }
        return true;
    }

    private boolean isInstalledFromGooglePlay() {
        String installerPackageName = this.kActivity.getPackageManager().getInstallerPackageName(this.kActivity.getPackageName());
        String str = installerPackageName;
        return installerPackageName != null && (str.equals("com.google.android.feedback") || str.equals("com.android.vending"));
    }

    /* access modifiers changed from: private */
    public boolean isApproved() {
        StringBuilder sb;
        StringBuilder sb2;
        URL url;
        BufferedReader bufferedReader;
        Reader reader;
        StringBuilder sb3;
        JSONObject jSONObject;
        new StringBuilder();
        StringBuilder sb4 = sb;
        StringBuilder sb5 = sb4;
        StringBuilder append = sb4.append("https://api.creator.kodular.io/ads/");
        StringBuilder append2 = sb5.append(this.kAppId);
        StringBuilder append3 = sb5.append("/check/v1");
        new StringBuilder("Checking for App ID ");
        int i = Log.i(LOG_TAG, sb2.append(this.kAppId).toString());
        HttpURLConnection httpURLConnection = null;
        try {
            new URL(sb5.toString());
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) url.openConnection();
            httpURLConnection = httpURLConnection2;
            httpURLConnection2.setRequestMethod(HttpGet.METHOD_NAME);
            if (httpURLConnection.getResponseCode() == 200) {
                new InputStreamReader(httpURLConnection.getInputStream());
                new BufferedReader(reader);
                BufferedReader bufferedReader2 = bufferedReader;
                new StringBuilder();
                StringBuilder sb6 = sb3;
                while (true) {
                    String readLine = bufferedReader2.readLine();
                    String str = readLine;
                    if (readLine == null) {
                        break;
                    }
                    StringBuilder append4 = sb6.append(str);
                }
                String sb7 = sb6.toString();
                new JSONObject(sb7);
                JSONObject jSONObject2 = jSONObject;
                JSONObject jSONObject3 = jSONObject2;
                if (jSONObject2.getBoolean("success") && jSONObject3.getBoolean("approved")) {
                    this.failReason = "";
                    int i2 = Log.i(LOG_TAG, "The application is ready to serve ads.");
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    return true;
                } else if (!jSONObject3.getBoolean("success") || jSONObject3.getBoolean("approved")) {
                    this.failReason = "An unexpected response from Admob Validation System was received. Please try again.";
                    int w = Log.w(LOG_TAG, "Internal error! The server response is NULL. Please try again.");
                    int i3 = Log.i(LOG_TAG, "Response from server: ".concat(String.valueOf(sb7)));
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    return false;
                } else {
                    this.failReason = "Your app has not been approved to serve ads. Please, go to My Kodular and request its activation.";
                    int i4 = Log.i(LOG_TAG, "The application is not approved to serve ads.");
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    return false;
                }
            } else {
                this.failReason = "No response got from Admob Validation System. Please try again.";
                int w2 = Log.w(LOG_TAG, "Internal error! No response was received from server.");
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return false;
            }
        } catch (Exception e) {
            int e2 = Log.e(LOG_TAG, String.valueOf(e));
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            return false;
        } catch (Throwable th) {
            Throwable th2 = th;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th2;
        }
    }
}

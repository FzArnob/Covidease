package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import com.google.api.client.extensions.android2.AndroidHttp;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.googleapis.services.GoogleKeyInitializer;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.json.JsonHttpRequestInitializer;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.fusiontables.Fusiontables;
import com.google.api.services.fusiontables.FusiontablesRequest;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.IClientLoginHelper;
import com.google.appinventor.components.runtime.util.OAuth2Helper;
import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicNameValuePair;
import org.shaded.apache.http.client.utils.URLEncodedUtils;

@DesignerComponent(category = ComponentCategory.INTERNAL, description = "<p>A non-visible component that communicates with Google Fusion Tables. Fusion Tables let you store, share, query and visualize data tables; this component lets you query, create, and modify these tables.</p> <p>This component uses the <a href=\"https://developers.google.com/fusiontables/docs/v2/getting_started\" target=\"_blank\">Fusion Tables API V2.0</a>. <p>Applications using Fusion Tables must authentication to Google's servers. There are two ways this can be done. The first way uses an API Key which you the developer obtain (see below). With this approach end-users must also login to access a Fusion Table. The second approach is to use a Service Account. With this approach you create credentials and a special \"Service Account Email Address\" which you obtain from the <a href=\"https://code.google.com/apis/console/\" target=\"_blank\">Google APIs Console</a>. You then tell the Fusion Table Control the name of the Service Account Email address and upload the secret key as an asset to your application and set the KeyFile property to point at this file. Finally you check the \"UseServiceAuthentication\" checkbox in the designer. When using a Service Account, end-users do not need to login to use Fusion Tables, your service account authenticates all access.</p> <p>To get an API key, follow these instructions.</p> <ol><li>Go to your <a href=\"https://code.google.com/apis/console/\" target=\"_blank\">Google APIs Console</a> and login if necessary.</li><li>Select the <i>Services</i> item from the menu on the left.</li><li>Choose the <i>Fusiontables</i> service from the list provided and turn it on.</li><li>Go back to the main menu and select the <i>API Access</i> item. </li></ol><p>Your API Key will be near the bottom of that pane in the section called \"Simple API Access\".You will have to provide that key as the value for the <i>ApiKey</i> property in your Fusiontables app.</p><p>Once you have an API key, set the value of the <i>Query</i> property to a valid Fusiontables SQL query and call <i>SendQuery</i> to execute the query.  App Inventor will send the query to the Fusion Tables server and the <i>GotResult</i> block will fire when a result is returned from the server.Query results will be returned in CSV format, and can be converted to list format using the \"list from csv table\" or \"list from csv row\" blocks.</p><p>Note that you do not need to worry about UTF-encoding the query. But you do need to make sure the query follows the syntax described in <a href=\"https://developers.google.com/fusiontables/docs/v2/getting_started\" target=\"_blank\">the reference manual</a>, which means that things like capitalization for names of columns matters, and that single quotes must be used around column names if there are spaces in them.</p>", iconName = "images/fusiontables.png", nonVisible = true, version = 4)
@UsesLibraries(libraries = "fusiontables.jar,google-api-client-beta.jar,google-api-client-android2-beta.jar,google-http-client-beta.jar,google-http-client-android2-beta.jar,google-http-client-android3-beta.jar,google-oauth-client-beta.jar,guava-14.0.1.jar,gson.jar")
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET,android.permission.ACCOUNT_MANAGER,android.permission.MANAGE_ACCOUNTS,android.permission.GET_ACCOUNTS,android.permission.USE_CREDENTIALS,android.permission.WRITE_EXTERNAL_STORAGE,android.permission.READ_EXTERNAL_STORAGE")
public class FusiontablesControl extends AndroidNonvisibleComponent implements Component {
    public static final String APP_NAME = "App Inventor";
    public static final String AUTHORIZATION_HEADER_PREFIX = "Bearer ";
    public static final String AUTH_TOKEN_TYPE_FUSIONTABLES = "oauth2:https://www.googleapis.com/auth/fusiontables";
    public static final String FUSIONTABLES_POST = "https://www.googleapis.com/fusiontables/v2/tables";
    /* access modifiers changed from: private */
    public String JG8vik3adkKEwGQS5cMPy19gFsDVhfFFU9AjX5Lm2B7MWziHu9erYgT687JlCqd = "Error on Fusion Tables query";
    /* access modifiers changed from: private */
    public String OAUx2n1xMKzXfjTj5DCb1XzhEYBfmj7wvFgBr5BSQUCav1CowwMbt2iKFNQwGzpG = "";

    /* renamed from: OAUx2n1xMKzXfjTj5DCb1XzhEYBfmj7wvFgBr5BSQUCav1CowwMbt2iKFNQwGzpG  reason: collision with other field name */
    private boolean f408OAUx2n1xMKzXfjTj5DCb1XzhEYBfmj7wvFgBr5BSQUCav1CowwMbt2iKFNQwGzpG = true;
    /* access modifiers changed from: private */
    public String UCfQaCFj2ASJbfyESztjl1SvQCaE7JCSj517yoZD3j82N9syinSTFfGgPUXIenmO = "https://www.googleapis.com/auth/fusiontables";
    /* access modifiers changed from: private */
    public String YPNDRC6guNtR9Y1y2H2jw2eNOGhWZENW1YRifRdfZVF6tZ3Hhm9vVsLmqobDf9o1 = "";
    private String ZYL9KAfl6ZZzM9RsykyXLexYTPR8S0eQ9Guil6cW84HmbyBTkvTBFTgEwGE4p6T = "Please wait loading...";
    private final Activity activity;
    private final Handler androidUIHandler;
    private final ComponentContainer container;
    private boolean havePermission;
    private final IClientLoginHelper hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private File f409hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
    /* access modifiers changed from: private */
    public String joZZFkzhsHdrkrd2PnThIkJfOfuAzcTTVQ9uzSCPDoVjmnvQXSliAgIhSj7yEkSN;

    /* renamed from: joZZFkzhsHdrkrd2PnThIkJfOfuAzcTTVQ9uzSCPDoVjmnvQXSliAgIhSj7yEkSN  reason: collision with other field name */
    private boolean f410joZZFkzhsHdrkrd2PnThIkJfOfuAzcTTVQ9uzSCPDoVjmnvQXSliAgIhSj7yEkSN = false;
    private String q0Zein2OQsQpQMJEzcCMbVGzaOYlGS7C9oJS7mmTl1ea6jnbwE6PEACmMQoD3fbj = AUTH_TOKEN_TYPE_FUSIONTABLES;
    /* access modifiers changed from: private */
    public String qPA23Y7cwrM2jVYdTZaCOyuoTEsak9zRoCsFocZMlDYlamZRzkT9xY09N4QNxUyM;
    private String rtyU3Uj4Fd2cS2DWhNVfozs9qaFOsy3YcN33Msvg0fbnB6MZpRvgk3PrzB8p4A;
    private String sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp;

    static /* synthetic */ String B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(FusiontablesControl fusiontablesControl, String str) {
        String str2 = str;
        String str3 = str2;
        fusiontablesControl.joZZFkzhsHdrkrd2PnThIkJfOfuAzcTTVQ9uzSCPDoVjmnvQXSliAgIhSj7yEkSN = str3;
        return str2;
    }

    static /* synthetic */ String B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(String str) {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        String str2 = str;
        int i = Log.i("FUSION", "parsetoJSonSqlCreate :".concat(String.valueOf(str2)));
        new StringBuilder();
        StringBuilder sb5 = sb;
        String trim = str2.trim();
        String str3 = trim;
        String trim2 = trim.substring(12, str3.indexOf(40)).trim();
        String str4 = str3;
        String[] split = str4.substring(str4.indexOf(40) + 1, str3.indexOf(41)).split(",");
        StringBuilder append = sb5.append("{'columns':[");
        for (int i2 = 0; i2 < split.length; i2++) {
            String[] split2 = split[i2].split(":");
            new StringBuilder("{'name': '");
            StringBuilder append2 = sb5.append(sb4.append(split2[0].trim()).append("', 'type': '").append(split2[1].trim()).append("'}").toString());
            if (i2 < split.length - 1) {
                StringBuilder append3 = sb5.append(",");
            }
        }
        StringBuilder append4 = sb5.append("],");
        StringBuilder append5 = sb5.append("'isExportable':'true',");
        new StringBuilder("'name': '");
        StringBuilder append6 = sb5.append(sb2.append(trim2).append("'").toString());
        StringBuilder append7 = sb5.append("}");
        StringBuilder insert = sb5.insert(0, "CREATE TABLE ");
        new StringBuilder("result = ");
        int i3 = Log.i("FUSION", sb3.append(sb5.toString()).toString());
        return sb5.toString();
    }

    static /* synthetic */ File hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(FusiontablesControl fusiontablesControl, File file) {
        File file2 = file;
        File file3 = file2;
        fusiontablesControl.f409hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = file3;
        return file2;
    }

    static /* synthetic */ String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(FusiontablesControl fusiontablesControl, String str) {
        String str2 = str;
        String str3 = str2;
        fusiontablesControl.qPA23Y7cwrM2jVYdTZaCOyuoTEsak9zRoCsFocZMlDYlamZRzkT9xY09N4QNxUyM = str3;
        return str2;
    }

    static /* synthetic */ HttpUriRequest hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str) throws IOException {
        HttpPost httpPost;
        ArrayList arrayList;
        Object obj;
        UrlEncodedFormEntity urlEncodedFormEntity;
        new HttpPost("http://www.google.com/fusiontables/v2/query");
        HttpPost httpPost2 = httpPost;
        new ArrayList(1);
        ArrayList arrayList2 = arrayList;
        new BasicNameValuePair("sql", str);
        boolean add = arrayList2.add(obj);
        new UrlEncodedFormEntity(arrayList2, "UTF-8");
        UrlEncodedFormEntity urlEncodedFormEntity2 = urlEncodedFormEntity;
        urlEncodedFormEntity2.setContentType(URLEncodedUtils.CONTENT_TYPE);
        httpPost2.setEntity(urlEncodedFormEntity2);
        return httpPost2;
    }

    static /* synthetic */ boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(FusiontablesControl fusiontablesControl, boolean z) {
        boolean z2 = z;
        boolean z3 = z2;
        fusiontablesControl.havePermission = z3;
        return z2;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public FusiontablesControl(com.google.appinventor.components.runtime.ComponentContainer r15) {
        /*
            r14 = this;
            r0 = r14
            r1 = r15
            r6 = r0
            r7 = r1
            com.google.appinventor.components.runtime.Form r7 = r7.$form()
            r6.<init>(r7)
            r6 = r0
            r7 = 0
            r6.f409hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7
            r6 = r0
            java.lang.String r7 = "oauth2:https://www.googleapis.com/auth/fusiontables"
            r6.q0Zein2OQsQpQMJEzcCMbVGzaOYlGS7C9oJS7mmTl1ea6jnbwE6PEACmMQoD3fbj = r7
            r6 = r0
            java.lang.String r7 = "Error on Fusion Tables query"
            r6.JG8vik3adkKEwGQS5cMPy19gFsDVhfFFU9AjX5Lm2B7MWziHu9erYgT687JlCqd = r7
            r6 = r0
            java.lang.String r7 = ""
            r6.OAUx2n1xMKzXfjTj5DCb1XzhEYBfmj7wvFgBr5BSQUCav1CowwMbt2iKFNQwGzpG = r7
            r6 = r0
            r7 = 0
            r6.f410joZZFkzhsHdrkrd2PnThIkJfOfuAzcTTVQ9uzSCPDoVjmnvQXSliAgIhSj7yEkSN = r7
            r6 = r0
            java.lang.String r7 = ""
            r6.YPNDRC6guNtR9Y1y2H2jw2eNOGhWZENW1YRifRdfZVF6tZ3Hhm9vVsLmqobDf9o1 = r7
            r6 = r0
            java.lang.String r7 = "https://www.googleapis.com/auth/fusiontables"
            r6.UCfQaCFj2ASJbfyESztjl1SvQCaE7JCSj517yoZD3j82N9syinSTFfGgPUXIenmO = r7
            r6 = r0
            java.lang.String r7 = "Please wait loading..."
            r6.ZYL9KAfl6ZZzM9RsykyXLexYTPR8S0eQ9Guil6cW84HmbyBTkvTBFTgEwGE4p6T = r7
            r6 = r0
            r7 = 1
            r6.f408OAUx2n1xMKzXfjTj5DCb1XzhEYBfmj7wvFgBr5BSQUCav1CowwMbt2iKFNQwGzpG = r7
            r6 = r0
            android.os.Handler r7 = new android.os.Handler
            r13 = r7
            r7 = r13
            r8 = r13
            r8.<init>()
            r6.androidUIHandler = r7
            r6 = r0
            r7 = 0
            r6.havePermission = r7
            r6 = r0
            r7 = r1
            r6.container = r7
            r6 = r0
            r7 = r1
            android.app.Activity r7 = r7.$context()
            r6.activity = r7
            r6 = r0
            r13 = r6
            r6 = r13
            r7 = r13
            java.lang.String r8 = "Choose an account to access FusionTables"
            java.lang.String r9 = "fusiontables"
            r3 = r9
            r2 = r8
            r1 = r7
            int r7 = android.os.Build.VERSION.SDK_INT
            r8 = 5
            if (r7 < r8) goto L_0x00e4
            org.apache.http.impl.client.DefaultHttpClient r7 = new org.apache.http.impl.client.DefaultHttpClient
            r13 = r7
            r7 = r13
            r8 = r13
            r8.<init>()
            r13 = r7
            r7 = r13
            r8 = r13
            r4 = r8
            org.apache.http.params.HttpParams r7 = r7.getParams()
            r8 = 30000(0x7530, float:4.2039E-41)
            org.apache.http.params.HttpConnectionParams.setSoTimeout(r7, r8)
            r7 = r4
            org.apache.http.params.HttpParams r7 = r7.getParams()
            r8 = 30000(0x7530, float:4.2039E-41)
            org.apache.http.params.HttpConnectionParams.setConnectionTimeout(r7, r8)
            com.google.appinventor.components.runtime.util.ClientLoginHelper r7 = new com.google.appinventor.components.runtime.util.ClientLoginHelper
            r13 = r7
            r7 = r13
            r8 = r13
            r9 = r1
            android.app.Activity r9 = r9.activity
            r10 = r3
            r11 = r2
            r12 = r4
            r8.<init>(r9, r10, r11, r12)
        L_0x0095:
            r6.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7
            r6 = r0
            java.lang.String r7 = "show tables"
            r6.sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp = r7
            int r6 = android.os.Build.VERSION.SDK_INT
            r7 = 5
            if (r6 >= r7) goto L_0x00e3
            r6 = r0
            java.lang.String r7 = "Sorry. The Fusiontables component is not compatible with this phone."
            java.lang.String r8 = "This application must exit."
            java.lang.String r9 = "Rats!"
            r4 = r9
            r3 = r8
            r2 = r7
            r1 = r6
            android.app.AlertDialog$Builder r6 = new android.app.AlertDialog$Builder
            r13 = r6
            r6 = r13
            r7 = r13
            r8 = r1
            android.app.Activity r8 = r8.activity
            r7.<init>(r8)
            android.app.AlertDialog r6 = r6.create()
            r13 = r6
            r6 = r13
            r7 = r13
            r5 = r7
            r7 = r3
            r6.setTitle(r7)
            r6 = r5
            r7 = 0
            r6.setCancelable(r7)
            r6 = r5
            r7 = r2
            r6.setMessage(r7)
            r6 = r5
            r7 = r4
            com.google.appinventor.components.runtime.FusiontablesControl$2 r8 = new com.google.appinventor.components.runtime.FusiontablesControl$2
            r13 = r8
            r8 = r13
            r9 = r13
            r10 = r1
            r9.<init>(r10)
            r6.setButton(r7, r8)
            r6 = r5
            r6.show()
        L_0x00e3:
            return
        L_0x00e4:
            r7 = 0
            goto L_0x0095
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.FusiontablesControl.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    public void Initialize() {
        PermissionResultHandler permissionResultHandler;
        new PermissionResultHandler(this) {
            private /* synthetic */ FusiontablesControl hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void HandlePermissionResponse(String str, boolean z) {
                String str2 = str;
                if (z) {
                    boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = FusiontablesControl.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, true);
                    return;
                }
                boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME3 = FusiontablesControl.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, false);
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchPermissionDeniedEvent((Component) this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "Initialize", "android.permission.GET_ACCOUNTS");
            }
        };
        this.form.askPermission("android.permission.GET_ACCOUNTS", permissionResultHandler);
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Indicates whether a service account should be used for authentication")
    public boolean UseServiceAuthentication() {
        return this.f410joZZFkzhsHdrkrd2PnThIkJfOfuAzcTTVQ9uzSCPDoVjmnvQXSliAgIhSj7yEkSN;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty
    public void UseServiceAuthentication(boolean z) {
        boolean z2 = z;
        this.f410joZZFkzhsHdrkrd2PnThIkJfOfuAzcTTVQ9uzSCPDoVjmnvQXSliAgIhSj7yEkSN = z2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The Service Account Email Address when service account authentication is in use.")
    public String ServiceAccountEmail() {
        return this.YPNDRC6guNtR9Y1y2H2jw2eNOGhWZENW1YRifRdfZVF6tZ3Hhm9vVsLmqobDf9o1;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty
    public void ServiceAccountEmail(String str) {
        String str2 = str;
        this.YPNDRC6guNtR9Y1y2H2jw2eNOGhWZENW1YRifRdfZVF6tZ3Hhm9vVsLmqobDf9o1 = str2;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty
    public void ApiKey(String str) {
        String str2 = str;
        this.rtyU3Uj4Fd2cS2DWhNVfozs9qaFOsy3YcN33Msvg0fbnB6MZpRvgk3PrzB8p4A = str2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Your Google API Key. For help, click on the questionmark (?) next to the FusiontablesControl component in the Palette. ")
    public String ApiKey() {
        return this.rtyU3Uj4Fd2cS2DWhNVfozs9qaFOsy3YcN33Msvg0fbnB6MZpRvgk3PrzB8p4A;
    }

    @DesignerProperty(defaultValue = "show tables", editorType = "string")
    @SimpleProperty
    public void Query(String str) {
        String str2 = str;
        this.sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp = str2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The query to send to the Fusion Tables API. <p>For legal query formats and examples, see the <a href=\"https://developers.google.com/fusiontables/docs/v2/getting_started\" target=\"_blank\">Fusion Tables API v2.0 reference manual</a>.</p> <p>Note that you do not need to worry about UTF-encoding the query. But you do need to make sure it follows the syntax described in the reference manual, which means that things like capitalization for names of columns matters, and that single quotes need to be used around column names if there are spaces in them.</p> ")
    public String Query() {
        return this.sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp;
    }

    @DesignerProperty(defaultValue = "", editorType = "asset")
    @SimpleProperty
    public void KeyFile(String str) {
        String str2 = str;
        if (!str2.equals(this.OAUx2n1xMKzXfjTj5DCb1XzhEYBfmj7wvFgBr5BSQUCav1CowwMbt2iKFNQwGzpG)) {
            if (this.f409hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
                boolean delete = this.f409hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.delete();
                this.f409hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = null;
            }
            this.OAUx2n1xMKzXfjTj5DCb1XzhEYBfmj7wvFgBr5BSQUCav1CowwMbt2iKFNQwGzpG = str2 == null ? "" : str2;
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Specifies the path of the private key file.  This key file is used to get access to the FusionTables API.")
    public String KeyFile() {
        return this.OAUx2n1xMKzXfjTj5DCb1XzhEYBfmj7wvFgBr5BSQUCav1CowwMbt2iKFNQwGzpG;
    }

    @SimpleFunction(description = "Send the query to the Fusiontables server.")
    public void SendQuery() {
        C0715b bVar;
        if (this.havePermission) {
            C0715b bVar2 = bVar;
            new C0715b(this, this.activity);
            AsyncTask execute = bVar2.execute(new String[]{this.sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp});
            return;
        }
        this.form.dispatchPermissionDeniedEvent((Component) this, "SendQuery", "android.permission.GET_ACCOUNTS");
    }

    @Deprecated
    @SimpleFunction(description = "DEPRECATED. This block is deprecated as of the end of 2012.  Use SendQuery.")
    public void DoQuery() {
        C0714a aVar;
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            new C0714a(this, (byte) 0);
            AsyncTask execute = aVar.execute(new String[]{this.sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp});
            return;
        }
        this.form.dispatchErrorOccurredEvent(this, "DoQuery", 3, new Object[0]);
    }

    @SimpleEvent(description = "Indicates that the Fusion Tables query has finished processing, with a result.  The result of the query will generally be returned in CSV format, and can be converted to list format using the \"list from csv table\" or \"list from csv row\" blocks.")
    public void GotResult(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "GotResult", str);
    }

    @SimpleFunction(description = "Forget end-users login credentials. Has no effect on service authentication")
    public void ForgetLogin() {
        OAuth2Helper.resetAccountCredential(this.activity);
    }

    @SimpleFunction(description = "Inserts a row into the specified fusion table. The tableId field is the id of thefusion table. The columns is a comma-separated list of the columns to insert values into. The values field specifies what values to insert into each column.")
    public void InsertRow(String str, String str2, String str3) {
        StringBuilder sb;
        C0715b bVar;
        new StringBuilder("INSERT INTO ");
        this.sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp = sb.append(str).append(" (").append(str2).append(") VALUES (").append(str3).append(")").toString();
        if (this.havePermission) {
            C0715b bVar2 = bVar;
            new C0715b(this, this.activity);
            AsyncTask execute = bVar2.execute(new String[]{this.sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp});
            return;
        }
        this.form.dispatchPermissionDeniedEvent((Component) this, "InsertRow", "android.permission.GET_ACCOUNTS");
    }

    @SimpleFunction(description = "Gets all the rows from a specified fusion table. The tableId field is the id of therequired fusion table. The columns field is a comma-separeted list of the columns to retrieve.")
    public void GetRows(String str, String str2) {
        StringBuilder sb;
        C0715b bVar;
        new StringBuilder("SELECT ");
        this.sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp = sb.append(str2).append(" FROM ").append(str).toString();
        if (this.havePermission) {
            C0715b bVar2 = bVar;
            new C0715b(this, this.activity);
            AsyncTask execute = bVar2.execute(new String[]{this.sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp});
            return;
        }
        this.form.dispatchPermissionDeniedEvent((Component) this, "GetRows", "android.permission.GET_ACCOUNTS");
    }

    @SimpleFunction(description = "Gets all the rows from a fusion table that meet certain conditions. The tableId field isthe id of the required fusion table. The columns field is a comma-separated list of the columns toretrieve. The conditions field specifies what rows to retrieve from the table, for example the rows in whicha particular column value is not null.")
    public void GetRowsWithConditions(String str, String str2, String str3) {
        StringBuilder sb;
        C0715b bVar;
        new StringBuilder("SELECT ");
        this.sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp = sb.append(str2).append(" FROM ").append(str).append(" WHERE ").append(str3).toString();
        if (this.havePermission) {
            C0715b bVar2 = bVar;
            new C0715b(this, this.activity);
            AsyncTask execute = bVar2.execute(new String[]{this.sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp});
            return;
        }
        this.form.dispatchPermissionDeniedEvent((Component) this, "GetRowsWithConditions", "android.permission.GET_ACCOUNTS");
    }

    @DesignerProperty(defaultValue = "Please wait loading...", editorType = "string")
    @SimpleProperty
    public void LoadingDialogMessage(String str) {
        String str2 = str;
        this.ZYL9KAfl6ZZzM9RsykyXLexYTPR8S0eQ9Guil6cW84HmbyBTkvTBFTgEwGE4p6T = str2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Set the loading message for the dialog.")
    public String LoadingDialogMessage() {
        return this.ZYL9KAfl6ZZzM9RsykyXLexYTPR8S0eQ9Guil6cW84HmbyBTkvTBFTgEwGE4p6T;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty
    public void ShowLoadingDialog(boolean z) {
        boolean z2 = z;
        this.f408OAUx2n1xMKzXfjTj5DCb1XzhEYBfmj7wvFgBr5BSQUCav1CowwMbt2iKFNQwGzpG = z2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether or not to show the loading dialog")
    public boolean ShowLoadingDialog() {
        return this.f408OAUx2n1xMKzXfjTj5DCb1XzhEYBfmj7wvFgBr5BSQUCav1CowwMbt2iKFNQwGzpG;
    }

    /* renamed from: com.google.appinventor.components.runtime.FusiontablesControl$a */
    class C0714a extends AsyncTask<String, Void, String> {
        private /* synthetic */ FusiontablesControl hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        private ProgressDialog progress;

        private C0714a(FusiontablesControl fusiontablesControl) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = fusiontablesControl;
            this.progress = null;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ C0714a(FusiontablesControl fusiontablesControl, byte b) {
            this(fusiontablesControl);
            byte b2 = b;
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            this.progress.dismiss();
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.GotResult((String) obj);
        }

        /* access modifiers changed from: protected */
        public final void onPreExecute() {
            this.progress = ProgressDialog.show(FusiontablesControl.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME), "Fusiontables", "processing query...", true);
        }

        /* JADX WARNING: type inference failed for: r3v20, types: [java.io.ByteArrayOutputStream] */
        /* access modifiers changed from: private */
        /* JADX WARNING: Multi-variable type inference failed */
        /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.String doInBackground(java.lang.String... r9) {
            /*
                r8 = this;
                r0 = r8
                r1 = r9
                r3 = r1
                r4 = 0
                r3 = r3[r4]     // Catch:{ IOException -> 0x0072 }
                org.apache.http.client.methods.HttpUriRequest r3 = com.google.appinventor.components.runtime.FusiontablesControl.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((java.lang.String) r3)     // Catch:{ IOException -> 0x0072 }
                r2 = r3
                java.lang.String r3 = "FUSION"
                java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0072 }
                r7 = r4
                r4 = r7
                r5 = r7
                java.lang.String r6 = "Fetching: "
                r5.<init>(r6)     // Catch:{ IOException -> 0x0072 }
                r5 = r1
                r6 = 0
                r5 = r5[r6]     // Catch:{ IOException -> 0x0072 }
                java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ IOException -> 0x0072 }
                java.lang.String r4 = r4.toString()     // Catch:{ IOException -> 0x0072 }
                int r3 = android.util.Log.d(r3, r4)     // Catch:{ IOException -> 0x0072 }
                r3 = r0
                com.google.appinventor.components.runtime.FusiontablesControl r3 = r3.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ IOException -> 0x0072 }
                com.google.appinventor.components.runtime.util.IClientLoginHelper r3 = com.google.appinventor.components.runtime.FusiontablesControl.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((com.google.appinventor.components.runtime.FusiontablesControl) r3)     // Catch:{ IOException -> 0x0072 }
                r4 = r2
                org.apache.http.HttpResponse r3 = r3.execute(r4)     // Catch:{ IOException -> 0x0072 }
                r1 = r3
                java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x0072 }
                r7 = r3
                r3 = r7
                r4 = r7
                r4.<init>()     // Catch:{ IOException -> 0x0072 }
                r2 = r3
                r3 = r1
                org.apache.http.HttpEntity r3 = r3.getEntity()     // Catch:{ IOException -> 0x0072 }
                r4 = r2
                r3.writeTo(r4)     // Catch:{ IOException -> 0x0072 }
                java.lang.String r3 = "FUSION"
                java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0072 }
                r7 = r4
                r4 = r7
                r5 = r7
                java.lang.String r6 = "Response: "
                r5.<init>(r6)     // Catch:{ IOException -> 0x0072 }
                r5 = r1
                org.apache.http.StatusLine r5 = r5.getStatusLine()     // Catch:{ IOException -> 0x0072 }
                java.lang.String r5 = r5.toString()     // Catch:{ IOException -> 0x0072 }
                java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ IOException -> 0x0072 }
                java.lang.String r4 = r4.toString()     // Catch:{ IOException -> 0x0072 }
                int r3 = android.util.Log.d(r3, r4)     // Catch:{ IOException -> 0x0072 }
                r3 = r2
                java.lang.String r3 = r3.toString()     // Catch:{ IOException -> 0x0072 }
                r0 = r3
            L_0x0071:
                return r0
            L_0x0072:
                r3 = move-exception
                r2 = r3
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r7 = r3
                r3 = r7
                r4 = r7
                r4.<init>()
                r4 = r2
                java.lang.String r4 = r4.getMessage()
                java.lang.StringBuilder r3 = r3.append(r4)
                java.lang.String r3 = r3.toString()
                r0 = r3
                goto L_0x0071
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.FusiontablesControl.C0714a.doInBackground(java.lang.String[]):java.lang.String");
        }
    }

    public HttpResponse sendQuery(String str, String str2) {
        Fusiontables.Builder builder;
        JsonFactory jsonFactory;
        HttpRequestInitializer httpRequestInitializer;
        JsonHttpRequestInitializer jsonHttpRequestInitializer;
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        StringBuilder sb5;
        StringBuilder sb6;
        String str3 = str;
        String str4 = str2;
        this.joZZFkzhsHdrkrd2PnThIkJfOfuAzcTTVQ9uzSCPDoVjmnvQXSliAgIhSj7yEkSN = this.JG8vik3adkKEwGQS5cMPy19gFsDVhfFFU9AjX5Lm2B7MWziHu9erYgT687JlCqd;
        int i = Log.i("FUSION", "executing ".concat(String.valueOf(str3)));
        HttpResponse httpResponse = null;
        new GsonFactory();
        new GoogleCredential();
        new Fusiontables.Builder(AndroidHttp.newCompatibleTransport(), jsonFactory, httpRequestInitializer);
        new GoogleKeyInitializer(ApiKey());
        try {
            Fusiontables.Query.Sql sql = builder.setApplicationName("App Inventor Fusiontables/v2.0").setJsonHttpRequestInitializer(jsonHttpRequestInitializer).build().query().sql(str3);
            Fusiontables.Query.Sql sql2 = sql;
            Object put = sql.put("alt", "csv");
            FusiontablesRequest oauthToken = sql2.setOauthToken(str4);
            httpResponse = sql2.executeUnparsed();
        } catch (PermissionException e) {
            PermissionException permissionException = e;
            this.form.dispatchPermissionDeniedEvent((Component) this, "SendQuery", permissionException);
            int e2 = Log.e("FUSION", String.valueOf(permissionException));
            new StringBuilder();
            this.joZZFkzhsHdrkrd2PnThIkJfOfuAzcTTVQ9uzSCPDoVjmnvQXSliAgIhSj7yEkSN = sb6.append(permissionException.getMessage()).toString();
        } catch (GoogleJsonResponseException e3) {
            GoogleJsonResponseException googleJsonResponseException = e3;
            new StringBuilder();
            this.joZZFkzhsHdrkrd2PnThIkJfOfuAzcTTVQ9uzSCPDoVjmnvQXSliAgIhSj7yEkSN = sb3.append(googleJsonResponseException.getMessage()).toString();
            int e4 = Log.e("FUSION", "JsonResponseException");
            new StringBuilder("e.getMessage() is ");
            int e5 = Log.e("FUSION", sb4.append(googleJsonResponseException.getMessage()).toString());
            new StringBuilder("response is ");
            int e6 = Log.e("FUSION", sb5.append((Object) null).toString());
        } catch (IOException e7) {
            IOException iOException = e7;
            this.joZZFkzhsHdrkrd2PnThIkJfOfuAzcTTVQ9uzSCPDoVjmnvQXSliAgIhSj7yEkSN = iOException.getMessage();
            int e8 = Log.e("FUSION", "IOException");
            new StringBuilder("e.getMessage() is ");
            int e9 = Log.e("FUSION", sb.append(iOException.getMessage()).toString());
            new StringBuilder("response is ");
            int e10 = Log.e("FUSION", sb2.append((Object) null).toString());
        }
        return httpResponse;
    }

    public static String httpResponseToString(HttpResponse httpResponse) {
        StringBuilder sb;
        HttpResponse httpResponse2 = httpResponse;
        String str = "";
        if (httpResponse2 != null) {
            if (httpResponse2.getStatusCode() != 200) {
                new StringBuilder();
                str = sb.append(httpResponse2.getStatusCode()).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(httpResponse2.getStatusMessage()).toString();
            } else {
                try {
                    str = parseResponse(httpResponse2.getContent());
                } catch (IOException e) {
                    int e2 = Log.e("FUSION", String.valueOf(e));
                }
            }
        }
        return str;
    }

    public static String httpApacheResponseToString(org.apache.http.HttpResponse httpResponse) {
        StringBuilder sb;
        org.apache.http.HttpResponse httpResponse2 = httpResponse;
        String str = "";
        if (httpResponse2 != null) {
            if (httpResponse2.getStatusLine().getStatusCode() != 200) {
                new StringBuilder();
                str = sb.append(httpResponse2.getStatusLine().getStatusCode()).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(httpResponse2.getStatusLine().getReasonPhrase()).toString();
            } else {
                try {
                    str = parseResponse(httpResponse2.getEntity().getContent());
                } catch (IOException e) {
                    int e2 = Log.e("FUSION", String.valueOf(e));
                }
            }
        }
        return str;
    }

    public static String parseResponse(InputStream inputStream) {
        BufferedReader bufferedReader;
        Reader reader;
        StringBuilder sb;
        StringBuilder sb2;
        String str = "";
        try {
            new InputStreamReader(inputStream);
            new BufferedReader(reader);
            BufferedReader bufferedReader2 = bufferedReader;
            new StringBuilder();
            StringBuilder sb3 = sb;
            while (true) {
                String readLine = bufferedReader2.readLine();
                String str2 = readLine;
                if (readLine == null) {
                    break;
                }
                new StringBuilder();
                StringBuilder append = sb3.append(sb2.append(str2).append("\n").toString());
            }
            str = sb3.toString();
            int i = Log.i("FUSION", "resultStr = ".concat(String.valueOf(str)));
            bufferedReader2.close();
        } catch (IOException e) {
            int e2 = Log.e("FUSION", String.valueOf(e));
        }
        return str;
    }

    public void handleOAuthError(String str) {
        String str2 = str;
        int i = Log.i("FUSION", "handleOAuthError: ".concat(String.valueOf(str2)));
        String str3 = str2;
        this.joZZFkzhsHdrkrd2PnThIkJfOfuAzcTTVQ9uzSCPDoVjmnvQXSliAgIhSj7yEkSN = str3;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(java.lang.String r12, java.lang.String r13) {
        /*
            r11 = this;
            r0 = r11
            r1 = r12
            r2 = r13
            r5 = r1
            java.lang.String r5 = r5.trim()
            r6 = 12
            java.lang.String r5 = r5.substring(r6)
            r3 = r5
            java.lang.String r5 = "FUSION"
            java.lang.String r6 = "Http Post content = "
            r7 = r3
            java.lang.String r7 = java.lang.String.valueOf(r7)
            java.lang.String r6 = r6.concat(r7)
            int r5 = android.util.Log.i(r5, r6)
            org.apache.http.client.methods.HttpPost r5 = new org.apache.http.client.methods.HttpPost
            r10 = r5
            r5 = r10
            r6 = r10
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r10 = r7
            r7 = r10
            r8 = r10
            java.lang.String r9 = "https://www.googleapis.com/fusiontables/v2/tables?key="
            r8.<init>(r9)
            r8 = r0
            java.lang.String r8 = r8.ApiKey()
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.String r7 = r7.toString()
            r6.<init>(r7)
            r4 = r5
            org.apache.http.entity.StringEntity r5 = new org.apache.http.entity.StringEntity     // Catch:{ UnsupportedEncodingException -> 0x0115 }
            r10 = r5
            r5 = r10
            r6 = r10
            r7 = r3
            r6.<init>(r7)     // Catch:{ UnsupportedEncodingException -> 0x0115 }
            r3 = r5
            r5 = r3
            java.lang.String r6 = "application/json"
            r5.setContentType(r6)
            r5 = r4
            java.lang.String r6 = "Authorization"
            java.lang.String r7 = "Bearer "
            r8 = r2
            java.lang.String r8 = java.lang.String.valueOf(r8)
            java.lang.String r7 = r7.concat(r8)
            r5.addHeader(r6, r7)
            r5 = r4
            r6 = r3
            r5.setEntity(r6)
            org.apache.http.impl.client.DefaultHttpClient r5 = new org.apache.http.impl.client.DefaultHttpClient
            r10 = r5
            r5 = r10
            r6 = r10
            r6.<init>()
            r2 = r5
            r5 = r2
            r6 = r4
            org.apache.http.HttpResponse r5 = r5.execute(r6)     // Catch:{ ClientProtocolException -> 0x013d, IOException -> 0x0165 }
            r2 = r5
            r5 = r2
            org.apache.http.StatusLine r5 = r5.getStatusLine()
            int r5 = r5.getStatusCode()
            r3 = r5
            r5 = r2
            if (r5 == 0) goto L_0x01e5
            r5 = r3
            r6 = 200(0xc8, float:2.8E-43)
            if (r5 != r6) goto L_0x01e5
            r5 = r2
            java.lang.String r5 = httpApacheResponseToString(r5)     // Catch:{ IllegalStateException -> 0x0193, JSONException -> 0x01bc }
            r3 = r5
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch:{ IllegalStateException -> 0x0193, JSONException -> 0x01bc }
            r10 = r5
            r5 = r10
            r6 = r10
            r7 = r3
            r6.<init>(r7)     // Catch:{ IllegalStateException -> 0x0193, JSONException -> 0x01bc }
            r10 = r5
            r5 = r10
            r6 = r10
            r4 = r6
            java.lang.String r6 = "tableId"
            boolean r5 = r5.has(r6)     // Catch:{ IllegalStateException -> 0x0193, JSONException -> 0x01bc }
            if (r5 == 0) goto L_0x018d
            r5 = r0
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ IllegalStateException -> 0x0193, JSONException -> 0x01bc }
            r10 = r6
            r6 = r10
            r7 = r10
            java.lang.String r8 = "tableId,"
            r7.<init>(r8)     // Catch:{ IllegalStateException -> 0x0193, JSONException -> 0x01bc }
            r7 = r4
            java.lang.String r8 = "tableId"
            java.lang.Object r7 = r7.get(r8)     // Catch:{ IllegalStateException -> 0x0193, JSONException -> 0x01bc }
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ IllegalStateException -> 0x0193, JSONException -> 0x01bc }
            java.lang.String r6 = r6.toString()     // Catch:{ IllegalStateException -> 0x0193, JSONException -> 0x01bc }
            r5.qPA23Y7cwrM2jVYdTZaCOyuoTEsak9zRoCsFocZMlDYlamZRzkT9xY09N4QNxUyM = r6     // Catch:{ IllegalStateException -> 0x0193, JSONException -> 0x01bc }
        L_0x00c8:
            java.lang.String r5 = "FUSION"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r10 = r6
            r6 = r10
            r7 = r10
            java.lang.String r8 = "Response code = "
            r7.<init>(r8)
            r7 = r2
            org.apache.http.StatusLine r7 = r7.getStatusLine()
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r6 = r6.toString()
            int r5 = android.util.Log.i(r5, r6)
            java.lang.String r5 = "FUSION"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r10 = r6
            r6 = r10
            r7 = r10
            java.lang.String r8 = "Query = "
            r7.<init>(r8)
            r7 = r1
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r7 = "\nResultStr = "
            java.lang.StringBuilder r6 = r6.append(r7)
            r7 = r0
            java.lang.String r7 = r7.qPA23Y7cwrM2jVYdTZaCOyuoTEsak9zRoCsFocZMlDYlamZRzkT9xY09N4QNxUyM
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r6 = r6.toString()
            int r5 = android.util.Log.i(r5, r6)
        L_0x0110:
            r5 = r0
            java.lang.String r5 = r5.qPA23Y7cwrM2jVYdTZaCOyuoTEsak9zRoCsFocZMlDYlamZRzkT9xY09N4QNxUyM
            r0 = r5
        L_0x0114:
            return r0
        L_0x0115:
            r5 = move-exception
            r2 = r5
            java.lang.String r5 = "FUSION"
            r6 = r2
            java.lang.String r6 = java.lang.String.valueOf(r6)
            int r5 = android.util.Log.e(r5, r6)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r10 = r5
            r5 = r10
            r6 = r10
            java.lang.String r7 = "Error: "
            r6.<init>(r7)
            r6 = r2
            java.lang.String r6 = r6.getMessage()
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            r0 = r5
            goto L_0x0114
        L_0x013d:
            r5 = move-exception
            r3 = r5
            java.lang.String r5 = "FUSION"
            r6 = r3
            java.lang.String r6 = java.lang.String.valueOf(r6)
            int r5 = android.util.Log.e(r5, r6)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r10 = r5
            r5 = r10
            r6 = r10
            java.lang.String r7 = "Error: "
            r6.<init>(r7)
            r6 = r3
            java.lang.String r6 = r6.getMessage()
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            r0 = r5
            goto L_0x0114
        L_0x0165:
            r5 = move-exception
            r3 = r5
            java.lang.String r5 = "FUSION"
            r6 = r3
            java.lang.String r6 = java.lang.String.valueOf(r6)
            int r5 = android.util.Log.e(r5, r6)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r10 = r5
            r5 = r10
            r6 = r10
            java.lang.String r7 = "Error: "
            r6.<init>(r7)
            r6 = r3
            java.lang.String r6 = r6.getMessage()
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            r0 = r5
            goto L_0x0114
        L_0x018d:
            r5 = r0
            r6 = r3
            r5.qPA23Y7cwrM2jVYdTZaCOyuoTEsak9zRoCsFocZMlDYlamZRzkT9xY09N4QNxUyM = r6     // Catch:{ IllegalStateException -> 0x0193, JSONException -> 0x01bc }
            goto L_0x00c8
        L_0x0193:
            r5 = move-exception
            r3 = r5
            java.lang.String r5 = "FUSION"
            r6 = r3
            java.lang.String r6 = java.lang.String.valueOf(r6)
            int r5 = android.util.Log.e(r5, r6)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r10 = r5
            r5 = r10
            r6 = r10
            java.lang.String r7 = "Error: "
            r6.<init>(r7)
            r6 = r3
            java.lang.String r6 = r6.getMessage()
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            r0 = r5
            goto L_0x0114
        L_0x01bc:
            r5 = move-exception
            r3 = r5
            java.lang.String r5 = "FUSION"
            r6 = r3
            java.lang.String r6 = java.lang.String.valueOf(r6)
            int r5 = android.util.Log.e(r5, r6)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r10 = r5
            r5 = r10
            r6 = r10
            java.lang.String r7 = "Error: "
            r6.<init>(r7)
            r6 = r3
            java.lang.String r6 = r6.getMessage()
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            r0 = r5
            goto L_0x0114
        L_0x01e5:
            java.lang.String r5 = "FUSION"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r10 = r6
            r6 = r10
            r7 = r10
            java.lang.String r8 = "Error: "
            r7.<init>(r8)
            r7 = r2
            org.apache.http.StatusLine r7 = r7.getStatusLine()
            java.lang.String r7 = r7.toString()
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r6 = r6.toString()
            int r5 = android.util.Log.i(r5, r6)
            r5 = r0
            r6 = r2
            org.apache.http.StatusLine r6 = r6.getStatusLine()
            java.lang.String r6 = r6.toString()
            r5.qPA23Y7cwrM2jVYdTZaCOyuoTEsak9zRoCsFocZMlDYlamZRzkT9xY09N4QNxUyM = r6
            goto L_0x0110
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.FusiontablesControl.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(java.lang.String, java.lang.String):java.lang.String");
    }

    /* renamed from: com.google.appinventor.components.runtime.FusiontablesControl$b */
    class C0715b extends AsyncTask<String, Void, String> {
        private final Activity activity;
        private final ProgressDialog hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
        private /* synthetic */ FusiontablesControl f411hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        /* access modifiers changed from: protected */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            OAuth2Helper oAuth2Helper;
            StringBuilder sb;
            StringBuilder sb2;
            String str = ((String[]) objArr)[0];
            int i = Log.i("QueryProcessorV2", "Starting doInBackground ".concat(String.valueOf(str)));
            if (FusiontablesControl.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f411hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME)) {
                return wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(str);
            }
            String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = FusiontablesControl.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f411hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "");
            new OAuth2Helper();
            String refreshedAuthToken = oAuth2Helper.getRefreshedAuthToken(this.activity, FusiontablesControl.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f411hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME));
            String str2 = refreshedAuthToken;
            if (refreshedAuthToken == null) {
                return OAuth2Helper.getErrorMessage();
            }
            if (str.toLowerCase().contains("create table")) {
                String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME3 = FusiontablesControl.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f411hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.f411hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(FusiontablesControl.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(str), str2));
                return this.f411hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.qPA23Y7cwrM2jVYdTZaCOyuoTEsak9zRoCsFocZMlDYlamZRzkT9xY09N4QNxUyM;
            }
            HttpResponse sendQuery = this.f411hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.sendQuery(str, str2);
            HttpResponse httpResponse = sendQuery;
            if (sendQuery != null) {
                String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME4 = FusiontablesControl.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f411hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, FusiontablesControl.httpResponseToString(httpResponse));
                new StringBuilder("Query = ");
                int i2 = Log.i("QueryProcessorV2", sb2.append(str).append("\nResultStr = ").append(this.f411hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.qPA23Y7cwrM2jVYdTZaCOyuoTEsak9zRoCsFocZMlDYlamZRzkT9xY09N4QNxUyM).toString());
            } else {
                String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME5 = FusiontablesControl.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f411hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this.f411hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.joZZFkzhsHdrkrd2PnThIkJfOfuAzcTTVQ9uzSCPDoVjmnvQXSliAgIhSj7yEkSN);
                new StringBuilder("Error:  ");
                int i3 = Log.i("QueryProcessorV2", sb.append(this.f411hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.joZZFkzhsHdrkrd2PnThIkJfOfuAzcTTVQ9uzSCPDoVjmnvQXSliAgIhSj7yEkSN).toString());
            }
            return this.f411hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.qPA23Y7cwrM2jVYdTZaCOyuoTEsak9zRoCsFocZMlDYlamZRzkT9xY09N4QNxUyM;
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            String str = (String) obj;
            int i = Log.i("FUSION", "Query result ".concat(String.valueOf(str)));
            if (str == null) {
                str = this.f411hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.joZZFkzhsHdrkrd2PnThIkJfOfuAzcTTVQ9uzSCPDoVjmnvQXSliAgIhSj7yEkSN;
            }
            if (!this.activity.isFinishing()) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.dismiss();
            }
            this.f411hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.GotResult(str);
        }

        C0715b(FusiontablesControl fusiontablesControl, Activity activity2) {
            ProgressDialog progressDialog;
            Activity activity3 = activity2;
            this.f411hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = fusiontablesControl;
            int i = Log.i("QueryProcessorV2", "Creating AsyncFusiontablesQuery");
            this.activity = activity3;
            new ProgressDialog(activity3);
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = progressDialog;
        }

        /* access modifiers changed from: protected */
        public final void onPreExecute() {
            if (this.f411hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.ShowLoadingDialog()) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setMessage(this.f411hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.LoadingDialogMessage());
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.show();
            }
        }

        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private java.lang.String wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(java.lang.String r12) {
            /*
                r11 = this;
                r0 = r11
                r1 = r12
                r5 = r0
                com.google.appinventor.components.runtime.FusiontablesControl r5 = r5.f411hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
                java.lang.String r6 = ""
                java.lang.String r5 = com.google.appinventor.components.runtime.FusiontablesControl.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((com.google.appinventor.components.runtime.FusiontablesControl) r5, (java.lang.String) r6)
                r5 = r0
                com.google.appinventor.components.runtime.FusiontablesControl r5 = r5.f411hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
                r6 = r0
                com.google.appinventor.components.runtime.FusiontablesControl r6 = r6.f411hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
                java.lang.String r6 = r6.JG8vik3adkKEwGQS5cMPy19gFsDVhfFFU9AjX5Lm2B7MWziHu9erYgT687JlCqd
                java.lang.String r5 = com.google.appinventor.components.runtime.FusiontablesControl.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(r5, r6)
                com.google.api.client.http.HttpTransport r5 = com.google.api.client.extensions.android2.AndroidHttp.newCompatibleTransport()
                r2 = r5
                com.google.api.client.json.gson.GsonFactory r5 = new com.google.api.client.json.gson.GsonFactory
                r10 = r5
                r5 = r10
                r6 = r10
                r6.<init>()
                r3 = r5
                java.lang.String r5 = "FUSION_SERVICE_ACCOUNT"
                java.lang.StringBuilder r6 = new java.lang.StringBuilder
                r10 = r6
                r6 = r10
                r7 = r10
                java.lang.String r8 = "keyPath "
                r7.<init>(r8)
                r7 = r0
                com.google.appinventor.components.runtime.FusiontablesControl r7 = r7.f411hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
                java.lang.String r7 = r7.OAUx2n1xMKzXfjTj5DCb1XzhEYBfmj7wvFgBr5BSQUCav1CowwMbt2iKFNQwGzpG
                java.lang.StringBuilder r6 = r6.append(r7)
                java.lang.String r6 = r6.toString()
                int r5 = android.util.Log.i(r5, r6)
                r5 = r0
                com.google.appinventor.components.runtime.FusiontablesControl r5 = r5.f411hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                java.io.File r5 = com.google.appinventor.components.runtime.FusiontablesControl.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((com.google.appinventor.components.runtime.FusiontablesControl) r5)     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                if (r5 != 0) goto L_0x006f
                r5 = r0
                com.google.appinventor.components.runtime.FusiontablesControl r5 = r5.f411hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                r6 = r0
                com.google.appinventor.components.runtime.FusiontablesControl r6 = r6.f411hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                com.google.appinventor.components.runtime.ComponentContainer r6 = com.google.appinventor.components.runtime.FusiontablesControl.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((com.google.appinventor.components.runtime.FusiontablesControl) r6)     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                com.google.appinventor.components.runtime.Form r6 = r6.$form()     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                r7 = r0
                com.google.appinventor.components.runtime.FusiontablesControl r7 = r7.f411hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                java.lang.String r7 = r7.OAUx2n1xMKzXfjTj5DCb1XzhEYBfmj7wvFgBr5BSQUCav1CowwMbt2iKFNQwGzpG     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                java.io.File r6 = com.google.appinventor.components.runtime.util.MediaUtil.copyMediaToTempFile(r6, r7)     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                java.io.File r5 = com.google.appinventor.components.runtime.FusiontablesControl.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((com.google.appinventor.components.runtime.FusiontablesControl) r5, (java.io.File) r6)     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
            L_0x006f:
                com.google.api.client.googleapis.auth.oauth2.GoogleCredential$Builder r5 = new com.google.api.client.googleapis.auth.oauth2.GoogleCredential$Builder     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                r10 = r5
                r5 = r10
                r6 = r10
                r6.<init>()     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                r6 = r2
                com.google.api.client.googleapis.auth.oauth2.GoogleCredential$Builder r5 = r5.setTransport(r6)     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                r6 = r3
                com.google.api.client.googleapis.auth.oauth2.GoogleCredential$Builder r5 = r5.setJsonFactory(r6)     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                r6 = r0
                com.google.appinventor.components.runtime.FusiontablesControl r6 = r6.f411hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                java.lang.String r6 = r6.YPNDRC6guNtR9Y1y2H2jw2eNOGhWZENW1YRifRdfZVF6tZ3Hhm9vVsLmqobDf9o1     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                com.google.api.client.googleapis.auth.oauth2.GoogleCredential$Builder r5 = r5.setServiceAccountId(r6)     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                r6 = 1
                java.lang.String[] r6 = new java.lang.String[r6]     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                r10 = r6
                r6 = r10
                r7 = r10
                r8 = 0
                r9 = r0
                com.google.appinventor.components.runtime.FusiontablesControl r9 = r9.f411hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                java.lang.String r9 = r9.UCfQaCFj2ASJbfyESztjl1SvQCaE7JCSj517yoZD3j82N9syinSTFfGgPUXIenmO     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                r7[r8] = r9     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                com.google.api.client.googleapis.auth.oauth2.GoogleCredential$Builder r5 = r5.setServiceAccountScopes(r6)     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                r6 = r0
                com.google.appinventor.components.runtime.FusiontablesControl r6 = r6.f411hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                java.io.File r6 = com.google.appinventor.components.runtime.FusiontablesControl.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((com.google.appinventor.components.runtime.FusiontablesControl) r6)     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                com.google.api.client.googleapis.auth.oauth2.GoogleCredential$Builder r5 = r5.setServiceAccountPrivateKeyFromP12File(r6)     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                com.google.api.client.googleapis.auth.oauth2.GoogleCredential r5 = r5.build()     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                r4 = r5
                com.google.api.services.fusiontables.Fusiontables$Builder r5 = new com.google.api.services.fusiontables.Fusiontables$Builder     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                r10 = r5
                r5 = r10
                r6 = r10
                r7 = r2
                r8 = r3
                r9 = r4
                r6.<init>(r7, r8, r9)     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                com.google.api.client.googleapis.services.GoogleKeyInitializer r6 = new com.google.api.client.googleapis.services.GoogleKeyInitializer     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                r10 = r6
                r6 = r10
                r7 = r10
                r8 = r0
                com.google.appinventor.components.runtime.FusiontablesControl r8 = r8.f411hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                java.lang.String r8 = r8.ApiKey()     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                r7.<init>(r8)     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                com.google.api.services.fusiontables.Fusiontables$Builder r5 = r5.setJsonHttpRequestInitializer(r6)     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                com.google.api.services.fusiontables.Fusiontables r5 = r5.build()     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                com.google.api.services.fusiontables.Fusiontables$Query r5 = r5.query()     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                r6 = r1
                com.google.api.services.fusiontables.Fusiontables$Query$Sql r5 = r5.sql(r6)     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                r10 = r5
                r5 = r10
                r6 = r10
                r2 = r6
                java.lang.String r6 = "alt"
                java.lang.String r7 = "csv"
                java.lang.Object r5 = r5.put(r6, r7)     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                r5 = 0
                r3 = r5
                r5 = r2
                com.google.api.client.http.HttpResponse r5 = r5.executeUnparsed()     // Catch:{ GoogleJsonResponseException -> 0x0161, Exception -> 0x01ae }
                r3 = r5
            L_0x00f1:
                r5 = r3
                if (r5 == 0) goto L_0x0286
                r5 = r0
                com.google.appinventor.components.runtime.FusiontablesControl r5 = r5.f411hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                r6 = r3
                java.lang.String r6 = com.google.appinventor.components.runtime.FusiontablesControl.httpResponseToString(r6)     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                java.lang.String r5 = com.google.appinventor.components.runtime.FusiontablesControl.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((com.google.appinventor.components.runtime.FusiontablesControl) r5, (java.lang.String) r6)     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                java.lang.String r5 = "FUSION_SERVICE_ACCOUNT"
                java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                r10 = r6
                r6 = r10
                r7 = r10
                java.lang.String r8 = "Query = "
                r7.<init>(r8)     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                r7 = r1
                java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                java.lang.String r7 = "\nResultStr = "
                java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                r7 = r0
                com.google.appinventor.components.runtime.FusiontablesControl r7 = r7.f411hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                java.lang.String r7 = r7.qPA23Y7cwrM2jVYdTZaCOyuoTEsak9zRoCsFocZMlDYlamZRzkT9xY09N4QNxUyM     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                java.lang.String r6 = r6.toString()     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                int r5 = android.util.Log.i(r5, r6)     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
            L_0x012d:
                java.lang.String r5 = "FUSION_SERVICE_ACCOUNT"
                java.lang.String r6 = "executed sql query"
                int r5 = android.util.Log.i(r5, r6)     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
            L_0x0137:
                java.lang.String r5 = "FUSION_SERVICE_ACCOUNT"
                java.lang.StringBuilder r6 = new java.lang.StringBuilder
                r10 = r6
                r6 = r10
                r7 = r10
                java.lang.String r8 = "returning queryResultStr = "
                r7.<init>(r8)
                r7 = r0
                com.google.appinventor.components.runtime.FusiontablesControl r7 = r7.f411hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
                java.lang.String r7 = r7.qPA23Y7cwrM2jVYdTZaCOyuoTEsak9zRoCsFocZMlDYlamZRzkT9xY09N4QNxUyM
                java.lang.StringBuilder r6 = r6.append(r7)
                java.lang.String r6 = r6.toString()
                int r5 = android.util.Log.i(r5, r6)
                r5 = r0
                com.google.appinventor.components.runtime.FusiontablesControl r5 = r5.f411hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
                java.lang.String r5 = r5.qPA23Y7cwrM2jVYdTZaCOyuoTEsak9zRoCsFocZMlDYlamZRzkT9xY09N4QNxUyM
                r0 = r5
                return r0
            L_0x0161:
                r5 = move-exception
                r2 = r5
                java.lang.String r5 = "FUSION_SERVICE_ACCOUNT"
                java.lang.String r6 = "Got a JsonResponse exception on sql.executeUnparsed"
                int r5 = android.util.Log.i(r5, r6)     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                r5 = r0
                com.google.appinventor.components.runtime.FusiontablesControl r5 = r5.f411hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                r10 = r6
                r6 = r10
                r7 = r10
                r7.<init>()     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                r7 = r2
                java.lang.String r7 = r7.getMessage()     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                java.lang.String r6 = r6.toString()     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                r2 = r6
                java.lang.String r6 = "FUSION_SERVICE_ACCOUNT"
                java.lang.String r7 = "parseJsonResponseException: "
                r8 = r2
                java.lang.String r8 = java.lang.String.valueOf(r8)     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                java.lang.String r7 = r7.concat(r8)     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                int r6 = android.util.Log.i(r6, r7)     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                r6 = r2
                java.lang.String r5 = com.google.appinventor.components.runtime.FusiontablesControl.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(r5, r6)     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                r5 = r0
                com.google.appinventor.components.runtime.FusiontablesControl r5 = r5.f411hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                r6 = r1
                r7 = r0
                com.google.appinventor.components.runtime.FusiontablesControl r7 = r7.f411hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                java.lang.String r7 = r7.joZZFkzhsHdrkrd2PnThIkJfOfuAzcTTVQ9uzSCPDoVjmnvQXSliAgIhSj7yEkSN     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                r5.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((java.lang.String) r6, (java.lang.String) r7)     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                goto L_0x00f1
            L_0x01ae:
                r5 = move-exception
                r2 = r5
                java.lang.String r5 = "FUSION_SERVICE_ACCOUNT"
                java.lang.String r6 = "Got an unanticipated exception on sql.executeUnparsed"
                int r5 = android.util.Log.i(r5, r6)     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                java.lang.String r5 = "FUSION_SERVICE_ACCOUNT"
                java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                r10 = r6
                r6 = r10
                r7 = r10
                java.lang.String r8 = "Exception class is "
                r7.<init>(r8)     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                r7 = r2
                java.lang.Class r7 = r7.getClass()     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                java.lang.String r6 = r6.toString()     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                int r5 = android.util.Log.i(r5, r6)     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                java.lang.String r5 = "FUSION_SERVICE_ACCOUNT"
                java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                r10 = r6
                r6 = r10
                r7 = r10
                java.lang.String r8 = "Exception message is "
                r7.<init>(r8)     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                r7 = r2
                java.lang.String r7 = r7.getMessage()     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                java.lang.String r6 = r6.toString()     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                int r5 = android.util.Log.i(r5, r6)     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                java.lang.String r5 = "FUSION_SERVICE_ACCOUNT"
                java.lang.String r6 = "Exception is "
                r7 = r2
                java.lang.String r7 = java.lang.String.valueOf(r7)     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                java.lang.String r6 = r6.concat(r7)     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                int r5 = android.util.Log.i(r5, r6)     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                java.lang.String r5 = "FUSION_SERVICE_ACCOUNT"
                java.lang.String r6 = "Point e"
                int r5 = android.util.Log.i(r5, r6)     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                java.lang.String r5 = "FUSION_SERVICE_ACCOUNT"
                java.lang.String r6 = "end of printing exception"
                int r5 = android.util.Log.i(r5, r6)     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                r5 = r0
                com.google.appinventor.components.runtime.FusiontablesControl r5 = r5.f411hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                r10 = r6
                r6 = r10
                r7 = r10
                r7.<init>()     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                r7 = r2
                java.lang.String r7 = r7.getMessage()     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                java.lang.String r6 = r6.toString()     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                java.lang.String r5 = com.google.appinventor.components.runtime.FusiontablesControl.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(r5, r6)     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                r5 = r0
                com.google.appinventor.components.runtime.FusiontablesControl r5 = r5.f411hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                r6 = r1
                r7 = r0
                com.google.appinventor.components.runtime.FusiontablesControl r7 = r7.f411hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                java.lang.String r7 = r7.joZZFkzhsHdrkrd2PnThIkJfOfuAzcTTVQ9uzSCPDoVjmnvQXSliAgIhSj7yEkSN     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                r5.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((java.lang.String) r6, (java.lang.String) r7)     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                goto L_0x00f1
            L_0x024b:
                r5 = move-exception
                r4 = r5
                r5 = r0
                com.google.appinventor.components.runtime.FusiontablesControl r5 = r5.f411hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
                com.google.appinventor.components.runtime.Form r5 = r5.form
                r6 = r0
                com.google.appinventor.components.runtime.FusiontablesControl r6 = r6.f411hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
                java.lang.String r7 = "UseServiceAuthentication"
                r8 = r4
                r5.dispatchPermissionDeniedEvent((com.google.appinventor.components.runtime.Component) r6, (java.lang.String) r7, (com.google.appinventor.components.runtime.errors.PermissionException) r8)
                java.lang.String r5 = "FUSION"
                r6 = r4
                java.lang.String r6 = java.lang.String.valueOf(r6)
                int r5 = android.util.Log.e(r5, r6)
                r5 = r0
                com.google.appinventor.components.runtime.FusiontablesControl r5 = r5.f411hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
                java.lang.StringBuilder r6 = new java.lang.StringBuilder
                r10 = r6
                r6 = r10
                r7 = r10
                r7.<init>()
                r7 = r4
                java.lang.String r7 = r7.getMessage()
                java.lang.StringBuilder r6 = r6.append(r7)
                java.lang.String r6 = r6.toString()
                java.lang.String r5 = com.google.appinventor.components.runtime.FusiontablesControl.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((com.google.appinventor.components.runtime.FusiontablesControl) r5, (java.lang.String) r6)
                goto L_0x0137
            L_0x0286:
                r5 = r0
                com.google.appinventor.components.runtime.FusiontablesControl r5 = r5.f411hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                r6 = r0
                com.google.appinventor.components.runtime.FusiontablesControl r6 = r6.f411hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                java.lang.String r6 = r6.joZZFkzhsHdrkrd2PnThIkJfOfuAzcTTVQ9uzSCPDoVjmnvQXSliAgIhSj7yEkSN     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                java.lang.String r5 = com.google.appinventor.components.runtime.FusiontablesControl.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((com.google.appinventor.components.runtime.FusiontablesControl) r5, (java.lang.String) r6)     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                java.lang.String r5 = "FUSION_SERVICE_ACCOUNT"
                java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                r10 = r6
                r6 = r10
                r7 = r10
                java.lang.String r8 = "Error with null response:  "
                r7.<init>(r8)     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                r7 = r0
                com.google.appinventor.components.runtime.FusiontablesControl r7 = r7.f411hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                java.lang.String r7 = r7.joZZFkzhsHdrkrd2PnThIkJfOfuAzcTTVQ9uzSCPDoVjmnvQXSliAgIhSj7yEkSN     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                java.lang.String r6 = r6.toString()     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                int r5 = android.util.Log.i(r5, r6)     // Catch:{ PermissionException -> 0x024b, Throwable -> 0x02b7 }
                goto L_0x012d
            L_0x02b7:
                r5 = move-exception
                r4 = r5
                java.lang.String r5 = "FUSION_SERVICE_ACCOUNT"
                java.lang.String r6 = "in Catch Throwable e"
                int r5 = android.util.Log.i(r5, r6)
                r5 = r0
                com.google.appinventor.components.runtime.FusiontablesControl r5 = r5.f411hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
                java.lang.StringBuilder r6 = new java.lang.StringBuilder
                r10 = r6
                r6 = r10
                r7 = r10
                r7.<init>()
                r7 = r4
                java.lang.String r7 = r7.getMessage()
                java.lang.StringBuilder r6 = r6.append(r7)
                java.lang.String r6 = r6.toString()
                java.lang.String r5 = com.google.appinventor.components.runtime.FusiontablesControl.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((com.google.appinventor.components.runtime.FusiontablesControl) r5, (java.lang.String) r6)
                goto L_0x0137
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.FusiontablesControl.C0715b.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(java.lang.String):java.lang.String");
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other method in class */
    public final void m59hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str, String str2) {
        Object[] objArr = new Object[2];
        objArr[0] = str;
        Object[] objArr2 = objArr;
        objArr2[1] = str2;
        this.form.dispatchErrorOccurredEventDialog(this, "SendQuery", ErrorMessages.FUSION_TABLES_QUERY_ERROR, objArr2);
    }
}

package com.google.appinventor.components.runtime;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONObject;
import org.shaded.apache.http.client.methods.HttpGet;

@DesignerComponent(category = ComponentCategory.MEDIA, description = "", iconName = "images/ocr.png", nonVisible = true, version = 5)
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET, android.permission.READ_EXTERNAL_STORAGE")
public class Ocr extends AndroidNonvisibleComponent implements Component {
    private String HThm2UFiN8mNIb2OEGwVoJpkwNLFwrt8FHi6kSBOC6T1EOtocK0hkK9xDo2LJOJZ = "http://2.bp.blogspot.com/-3C9EzBNi9rA/UiS93S6uqoI/AAAAAAAAEjM/fATWa50u0BY/w1200-h630-p-k-nu/Hello+world+android+app.jpg";
    /* access modifiers changed from: private */
    public String R6I3TvhVUzjImNcsZnPIarNQNa08KFL5suF8ZyHVabZqiWX3lxOTmOWImMG2ChIe = "eng";
    private boolean bg8qLM0P8YgZYqRlUjDxWnoKnWRYKRDQeEjqrx0ja4Cy7jLWl3M1lZwjImM82eG = false;
    private Context context;
    private boolean d234MfENUlM4amVCwiSVT0zMH9PGXT5eiUB6zvL6xVkA0Jl6b9GwzoZDcJDZRVrq = false;
    private Boolean hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = Boolean.FALSE;
    private boolean havePermission = false;
    private boolean repl = false;
    private String rtyU3Uj4Fd2cS2DWhNVfozs9qaFOsy3YcN33Msvg0fbnB6MZpRvgk3PrzB8p4A = "helloworld";
    private Boolean vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = Boolean.FALSE;
    private Boolean wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = Boolean.FALSE;

    static /* synthetic */ boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Ocr ocr, boolean z) {
        boolean z2 = z;
        boolean z3 = z2;
        ocr.bg8qLM0P8YgZYqRlUjDxWnoKnWRYKRDQeEjqrx0ja4Cy7jLWl3M1lZwjImM82eG = z3;
        return z2;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Ocr(com.google.appinventor.components.runtime.ComponentContainer r5) {
        /*
            r4 = this;
            r0 = r4
            r1 = r5
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            java.lang.String r3 = "helloworld"
            r2.rtyU3Uj4Fd2cS2DWhNVfozs9qaFOsy3YcN33Msvg0fbnB6MZpRvgk3PrzB8p4A = r3
            r2 = r0
            java.lang.Boolean r3 = java.lang.Boolean.FALSE
            r2.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = r3
            r2 = r0
            java.lang.Boolean r3 = java.lang.Boolean.FALSE
            r2.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = r3
            r2 = r0
            java.lang.Boolean r3 = java.lang.Boolean.FALSE
            r2.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = r3
            r2 = r0
            java.lang.String r3 = "eng"
            r2.R6I3TvhVUzjImNcsZnPIarNQNa08KFL5suF8ZyHVabZqiWX3lxOTmOWImMG2ChIe = r3
            r2 = r0
            java.lang.String r3 = "http://2.bp.blogspot.com/-3C9EzBNi9rA/UiS93S6uqoI/AAAAAAAAEjM/fATWa50u0BY/w1200-h630-p-k-nu/Hello+world+android+app.jpg"
            r2.HThm2UFiN8mNIb2OEGwVoJpkwNLFwrt8FHi6kSBOC6T1EOtocK0hkK9xDo2LJOJZ = r3
            r2 = r0
            r3 = 0
            r2.d234MfENUlM4amVCwiSVT0zMH9PGXT5eiUB6zvL6xVkA0Jl6b9GwzoZDcJDZRVrq = r3
            r2 = r0
            r3 = 0
            r2.havePermission = r3
            r2 = r0
            r3 = 0
            r2.repl = r3
            r2 = r0
            r3 = 0
            r2.bg8qLM0P8YgZYqRlUjDxWnoKnWRYKRDQeEjqrx0ja4Cy7jLWl3M1lZwjImM82eG = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            java.lang.String r2 = "Ocr"
            java.lang.String r3 = "Ocr Created"
            int r2 = android.util.Log.d(r2, r3)
            r2 = r0
            com.google.appinventor.components.runtime.Form r2 = r2.form
            boolean r2 = r2 instanceof com.google.appinventor.components.runtime.ReplForm
            if (r2 == 0) goto L_0x0059
            r2 = r0
            r3 = 1
            r2.repl = r3
        L_0x0059:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Ocr.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleFunction(description = "Get the text from a picture via the image url. Example: http://name/yourimage.jpg. Service powered by ocr.space.")
    public void GetTextFromImageUrl(String str) {
        StringBuilder sb;
        C0990b bVar;
        String str2 = str;
        String str3 = "https://api.ocr.space/parse/imageurl?apikey=";
        if (str2 == null || str2.isEmpty()) {
            str2 = this.HThm2UFiN8mNIb2OEGwVoJpkwNLFwrt8FHi6kSBOC6T1EOtocK0hkK9xDo2LJOJZ;
        }
        if (!str2.endsWith(".png") || !str2.endsWith(".PNG") || !str2.endsWith(".jpg") || !str2.endsWith(".JPG") || !str2.endsWith(".gif") || !str2.endsWith(".GIF") || !str2.endsWith(".pdf") || !str2.endsWith(".PDF")) {
            GotResponse(false, "The file must end with a valid extension like 'png'/'PNG', 'jpg'/'JPG', 'gif'/'GIF', or 'pdf'/'PDF'.");
            return;
        }
        new StringBuilder();
        String sb2 = sb.append(str3).append(this.rtyU3Uj4Fd2cS2DWhNVfozs9qaFOsy3YcN33Msvg0fbnB6MZpRvgk3PrzB8p4A).append("&url=").append(str2).append("&isOverlayRequired=").append(this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou).append("&isCreateSearchablePdf=").append(this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq).append("&language=").append(this.R6I3TvhVUzjImNcsZnPIarNQNa08KFL5suF8ZyHVabZqiWX3lxOTmOWImMG2ChIe).toString();
        new C0990b(this, (byte) 0);
        AsyncTask execute = bVar.execute(new String[]{sb2});
    }

    @SuppressLint({"StaticFieldLeak"})
    /* renamed from: com.google.appinventor.components.runtime.Ocr$b */
    class C0990b extends AsyncTask<String, Void, String> {
        private /* synthetic */ Ocr hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        /* access modifiers changed from: protected */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            return Ocr.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, ((String[]) objArr)[0]);
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            Ocr.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, (String) obj);
        }

        private C0990b(Ocr ocr) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = ocr;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ C0990b(Ocr ocr, byte b) {
            this(ocr);
            byte b2 = b;
        }
    }

    @SimpleFunction(description = "Upload your image to the server from ocr.space and then you get back the text from the picture.")
    public void UploadImage(String str) {
        C0991c cVar;
        StringBuilder sb;
        StringBuilder sb2;
        File file;
        StringBuilder sb3;
        StringBuilder sb4;
        Runnable runnable;
        String str2 = str;
        if (!this.havePermission) {
            final String str3 = str2;
            new Runnable(this) {
                final /* synthetic */ Ocr hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
                }

                public final void run() {
                    PermissionResultHandler permissionResultHandler;
                    new PermissionResultHandler(this) {
                        private /* synthetic */ C09871 hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                        {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                        }

                        public final void HandlePermissionResponse(String str, boolean z) {
                            String str2 = str;
                            if (z) {
                                boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = Ocr.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
                                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.UploadImage(str3);
                                return;
                            }
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchPermissionDeniedEvent((Component) this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "UploadImage", "android.permission.READ_EXTERNAL_STORAGE");
                        }
                    };
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.askPermission("android.permission.READ_EXTERNAL_STORAGE", permissionResultHandler);
                }
            };
            this.form.runOnUiThread(runnable);
            return;
        }
        C0991c cVar2 = cVar;
        new C0991c(this, (byte) 0);
        String[] strArr = new String[1];
        String[] strArr2 = strArr;
        String[] strArr3 = strArr;
        String str4 = str2;
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        String str5 = str4;
        if (str4.startsWith("file:///")) {
            str5 = str4.substring(7);
        } else if (str4.startsWith("file:///") || str4.startsWith("/")) {
            if (!str4.startsWith("/")) {
                new StringBuilder();
                str5 = sb.append(externalStorageDirectory).append(File.separator).append(str4).toString();
            } else if (!str4.startsWith(externalStorageDirectory.toString())) {
                new StringBuilder();
                str5 = sb2.append(externalStorageDirectory).append(str4).toString();
            }
        } else if (this.repl) {
            new StringBuilder();
            str5 = sb4.append(externalStorageDirectory).append(File.separator).append("/Makeroid/assets/").append(str4).toString();
        } else {
            new StringBuilder();
            new File(sb3.append(this.context.getCacheDir()).append("///").append(str4).toString());
            str5 = file.getPath();
        }
        strArr3[0] = str5;
        AsyncTask execute = cVar2.execute(strArr2);
    }

    @SuppressLint({"StaticFieldLeak"})
    /* renamed from: com.google.appinventor.components.runtime.Ocr$c */
    class C0991c extends AsyncTask<String, Void, String> {
        private /* synthetic */ Ocr hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            Ocr.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, (String) obj);
        }

        private C0991c(Ocr ocr) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = ocr;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ C0991c(Ocr ocr, byte b) {
            this(ocr);
            byte b2 = b;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v76, resolved type: java.lang.StringBuffer} */
        /* access modifiers changed from: private */
        /* JADX WARNING: Multi-variable type inference failed */
        /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.String doInBackground(java.lang.String... r12) {
            /*
                r11 = this;
                r0 = r11
                r1 = r12
                java.io.File r4 = new java.io.File     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                r10 = r4
                r4 = r10
                r5 = r10
                r6 = r1
                r7 = 0
                r6 = r6[r7]     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                r5.<init>(r6)     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                java.lang.String r4 = r4.getPath()     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                android.graphics.Bitmap r4 = android.graphics.BitmapFactory.decodeFile(r4)     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                r10 = r4
                r4 = r10
                r5 = r10
                r1 = r5
                if (r4 == 0) goto L_0x0041
                r4 = r1
                int r4 = r4.getWidth()     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                r5 = 100
                int r4 = r4 / 100
                r5 = 80
                int r4 = r4 * 80
                r2 = r4
                r4 = r1
                int r4 = r4.getHeight()     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                r5 = 100
                int r4 = r4 / 100
                r5 = 80
                int r4 = r4 * 80
                r3 = r4
                r4 = r1
                r5 = r2
                r6 = r3
                r7 = 0
                android.graphics.Bitmap r4 = android.graphics.Bitmap.createScaledBitmap(r4, r5, r6, r7)     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                r1 = r4
            L_0x0041:
                java.io.ByteArrayOutputStream r4 = new java.io.ByteArrayOutputStream     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                r10 = r4
                r4 = r10
                r5 = r10
                r5.<init>()     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                r2 = r4
                r4 = r1
                if (r4 == 0) goto L_0x0057
                r4 = r1
                android.graphics.Bitmap$CompressFormat r5 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                r6 = 100
                r7 = r2
                boolean r4 = r4.compress(r5, r6, r7)     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
            L_0x0057:
                r4 = r2
                byte[] r4 = r4.toByteArray()     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                r3 = r4
                java.net.URL r4 = new java.net.URL     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                r10 = r4
                r4 = r10
                r5 = r10
                java.lang.String r6 = "https://api.ocr.space/parse/image"
                r5.<init>(r6)     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                java.net.URLConnection r4 = r4.openConnection()     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                javax.net.ssl.HttpsURLConnection r4 = (javax.net.ssl.HttpsURLConnection) r4     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                r10 = r4
                r4 = r10
                r5 = r10
                r1 = r5
                java.lang.String r5 = "POST"
                r4.setRequestMethod(r5)     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                r4 = r1
                java.lang.String r5 = "User-Agent"
                java.lang.String r6 = "Mozilla/5.0"
                r4.setRequestProperty(r5, r6)     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                r4 = r1
                java.lang.String r5 = "Accept-Language"
                java.lang.String r6 = "en-US,en;q=0.5"
                r4.setRequestProperty(r5, r6)     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                r10 = r4
                r4 = r10
                r5 = r10
                r5.<init>()     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                r10 = r4
                r4 = r10
                r5 = r10
                r2 = r5
                java.lang.String r5 = "apikey"
                r6 = r0
                com.google.appinventor.components.runtime.Ocr r6 = r6.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                java.lang.String r6 = com.google.appinventor.components.runtime.Ocr.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((com.google.appinventor.components.runtime.Ocr) r6)     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                org.json.JSONObject r4 = r4.put(r5, r6)     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                r4 = r2
                java.lang.String r5 = "isOverlayRequired"
                r6 = r0
                com.google.appinventor.components.runtime.Ocr r6 = r6.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                java.lang.Boolean r6 = com.google.appinventor.components.runtime.Ocr.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((com.google.appinventor.components.runtime.Ocr) r6)     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                org.json.JSONObject r4 = r4.put(r5, r6)     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                r4 = r3
                r5 = 0
                java.lang.String r4 = android.util.Base64.encodeToString(r4, r5)     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                r3 = r4
                r4 = r2
                java.lang.String r5 = "base64image"
                java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                r10 = r6
                r6 = r10
                r7 = r10
                java.lang.String r8 = "data:image/png;base64,"
                r7.<init>(r8)     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                r7 = r3
                java.lang.String r8 = " "
                java.lang.String r9 = ""
                java.lang.String r7 = r7.replace(r8, r9)     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                java.lang.String r8 = "\n"
                java.lang.String r9 = ""
                java.lang.String r7 = r7.replace(r8, r9)     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                java.lang.String r6 = r6.toString()     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                org.json.JSONObject r4 = r4.put(r5, r6)     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                r4 = r2
                java.lang.String r5 = "language"
                r6 = r0
                com.google.appinventor.components.runtime.Ocr r6 = r6.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                java.lang.String r6 = r6.R6I3TvhVUzjImNcsZnPIarNQNa08KFL5suF8ZyHVabZqiWX3lxOTmOWImMG2ChIe     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                org.json.JSONObject r4 = r4.put(r5, r6)     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                r4 = r1
                r5 = 1
                r4.setDoOutput(r5)     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                java.io.DataOutputStream r4 = new java.io.DataOutputStream     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                r10 = r4
                r4 = r10
                r5 = r10
                r6 = r1
                java.io.OutputStream r6 = r6.getOutputStream()     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                r5.<init>(r6)     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                r3 = r4
                r4 = r2
                java.lang.String r4 = com.google.appinventor.components.runtime.Ocr.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((org.json.JSONObject) r4)     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                r10 = r4
                r4 = r10
                r5 = r10
                r2 = r5
                if (r4 == 0) goto L_0x0157
                r4 = r3
                r5 = r2
                r4.writeBytes(r5)     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                r4 = r3
                r4.flush()     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                r4 = r3
                r4.close()     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                r10 = r4
                r4 = r10
                r5 = r10
                java.io.InputStreamReader r6 = new java.io.InputStreamReader     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                r10 = r6
                r6 = r10
                r7 = r10
                r8 = r1
                java.io.InputStream r8 = r8.getInputStream()     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                r7.<init>(r8)     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                r5.<init>(r6)     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                r1 = r4
                java.lang.StringBuffer r4 = new java.lang.StringBuffer     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                r10 = r4
                r4 = r10
                r5 = r10
                r5.<init>()     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                r3 = r4
            L_0x0145:
                r4 = r1
                java.lang.String r4 = r4.readLine()     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                r10 = r4
                r4 = r10
                r5 = r10
                r2 = r5
                if (r4 == 0) goto L_0x0164
                r4 = r3
                r5 = r2
                java.lang.StringBuffer r4 = r4.append(r5)     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                goto L_0x0145
            L_0x0157:
                r4 = r0
                com.google.appinventor.components.runtime.Ocr r4 = r4.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                r5 = 0
                boolean r4 = com.google.appinventor.components.runtime.Ocr.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((com.google.appinventor.components.runtime.Ocr) r4, (boolean) r5)     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                java.lang.String r4 = "There was a problem to get a response from the ocr server."
                r0 = r4
            L_0x0163:
                return r0
            L_0x0164:
                r4 = r1
                r4.close()     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                r4 = r3
                java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                r1 = r4
                r4 = r0
                com.google.appinventor.components.runtime.Ocr r4 = r4.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
                r5 = 1
                boolean r4 = com.google.appinventor.components.runtime.Ocr.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((com.google.appinventor.components.runtime.Ocr) r4, (boolean) r5)     // Catch:{ PermissionException -> 0x0179, Exception -> 0x01a6 }
            L_0x0176:
                r4 = r1
                r0 = r4
                goto L_0x0163
            L_0x0179:
                r4 = move-exception
                r1 = r4
                java.lang.String r4 = "Ocr"
                r5 = r1
                java.lang.String r5 = java.lang.String.valueOf(r5)
                int r4 = android.util.Log.e(r4, r5)
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                r10 = r4
                r4 = r10
                r5 = r10
                r5.<init>()
                r5 = r1
                java.lang.String r5 = r5.getMessage()
                java.lang.StringBuilder r4 = r4.append(r5)
                java.lang.String r4 = r4.toString()
                r1 = r4
                r4 = r0
                com.google.appinventor.components.runtime.Ocr r4 = r4.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
                r5 = 0
                boolean r4 = com.google.appinventor.components.runtime.Ocr.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((com.google.appinventor.components.runtime.Ocr) r4, (boolean) r5)
                goto L_0x0176
            L_0x01a6:
                r4 = move-exception
                r1 = r4
                java.lang.String r4 = "Ocr"
                r5 = r1
                java.lang.String r5 = java.lang.String.valueOf(r5)
                int r4 = android.util.Log.e(r4, r5)
                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                r10 = r4
                r4 = r10
                r5 = r10
                r5.<init>()
                r5 = r1
                java.lang.String r5 = r5.getMessage()
                java.lang.StringBuilder r4 = r4.append(r5)
                java.lang.String r4 = r4.toString()
                r1 = r4
                r4 = r0
                com.google.appinventor.components.runtime.Ocr r4 = r4.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
                r5 = 0
                boolean r4 = com.google.appinventor.components.runtime.Ocr.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((com.google.appinventor.components.runtime.Ocr) r4, (boolean) r5)
                goto L_0x0176
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Ocr.C0991c.doInBackground(java.lang.String[]):java.lang.String");
        }
    }

    @SimpleEvent(description = "You will find here the success state and the response content.")
    public void GotResponse(boolean z, String str) {
        Object[] objArr = new Object[2];
        objArr[0] = Boolean.valueOf(z);
        Object[] objArr2 = objArr;
        objArr2[1] = str;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "GotResponse", objArr2);
    }

    @SimpleEvent(description = "You will find here the server status from the ocr provider. Possible results are 'UP' or 'DOWN'. 'pro Usa1' = Usa, East Coast. 'pro Usa2' = Usa, West Coast. ")
    public void GotServerStatus(String str, String str2, String str3, String str4, String str5) {
        Object[] objArr = new Object[5];
        objArr[0] = str;
        Object[] objArr2 = objArr;
        objArr2[1] = str2;
        Object[] objArr3 = objArr2;
        objArr3[2] = str3;
        Object[] objArr4 = objArr3;
        objArr4[3] = str4;
        Object[] objArr5 = objArr4;
        objArr5[4] = str5;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "GotServerStatus", objArr5);
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "Allows you to specify if the image/pdf text overlay is required. Overlay could be used to show the text over the image.")
    public void Overlayed(boolean z) {
        Boolean valueOf = Boolean.valueOf(z);
        this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = valueOf;
    }

    @SimpleProperty(description = "Return the state of overlayed property.")
    public boolean Overlayed() {
        return this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.booleanValue();
    }

    @DesignerProperty(defaultValue = "1", editorType = "ocr_language")
    @SimpleProperty(description = "Choose the language used for OCR. If no language is specified, English is taken as 'Default'.")
    public void Language(int i) {
        switch (i) {
            case 1:
                this.R6I3TvhVUzjImNcsZnPIarNQNa08KFL5suF8ZyHVabZqiWX3lxOTmOWImMG2ChIe = "eng";
                return;
            case 2:
                this.R6I3TvhVUzjImNcsZnPIarNQNa08KFL5suF8ZyHVabZqiWX3lxOTmOWImMG2ChIe = "ara";
                return;
            case 3:
                this.R6I3TvhVUzjImNcsZnPIarNQNa08KFL5suF8ZyHVabZqiWX3lxOTmOWImMG2ChIe = "bul";
                return;
            case 4:
                this.R6I3TvhVUzjImNcsZnPIarNQNa08KFL5suF8ZyHVabZqiWX3lxOTmOWImMG2ChIe = "chs";
                return;
            case 5:
                this.R6I3TvhVUzjImNcsZnPIarNQNa08KFL5suF8ZyHVabZqiWX3lxOTmOWImMG2ChIe = "cht";
                return;
            case 6:
                this.R6I3TvhVUzjImNcsZnPIarNQNa08KFL5suF8ZyHVabZqiWX3lxOTmOWImMG2ChIe = "hrv";
                return;
            case 7:
                this.R6I3TvhVUzjImNcsZnPIarNQNa08KFL5suF8ZyHVabZqiWX3lxOTmOWImMG2ChIe = "cze";
                return;
            case 8:
                this.R6I3TvhVUzjImNcsZnPIarNQNa08KFL5suF8ZyHVabZqiWX3lxOTmOWImMG2ChIe = "dan";
                return;
            case 9:
                this.R6I3TvhVUzjImNcsZnPIarNQNa08KFL5suF8ZyHVabZqiWX3lxOTmOWImMG2ChIe = "dut";
                return;
            case 10:
                this.R6I3TvhVUzjImNcsZnPIarNQNa08KFL5suF8ZyHVabZqiWX3lxOTmOWImMG2ChIe = "eng";
                return;
            case 11:
                this.R6I3TvhVUzjImNcsZnPIarNQNa08KFL5suF8ZyHVabZqiWX3lxOTmOWImMG2ChIe = "fin";
                return;
            case 12:
                this.R6I3TvhVUzjImNcsZnPIarNQNa08KFL5suF8ZyHVabZqiWX3lxOTmOWImMG2ChIe = "fre";
                return;
            case 13:
                this.R6I3TvhVUzjImNcsZnPIarNQNa08KFL5suF8ZyHVabZqiWX3lxOTmOWImMG2ChIe = "ger";
                return;
            case 14:
                this.R6I3TvhVUzjImNcsZnPIarNQNa08KFL5suF8ZyHVabZqiWX3lxOTmOWImMG2ChIe = "gre";
                return;
            case 15:
                this.R6I3TvhVUzjImNcsZnPIarNQNa08KFL5suF8ZyHVabZqiWX3lxOTmOWImMG2ChIe = "hun";
                return;
            case 16:
                this.R6I3TvhVUzjImNcsZnPIarNQNa08KFL5suF8ZyHVabZqiWX3lxOTmOWImMG2ChIe = "kor";
                return;
            case 17:
                this.R6I3TvhVUzjImNcsZnPIarNQNa08KFL5suF8ZyHVabZqiWX3lxOTmOWImMG2ChIe = "ita";
                return;
            case 18:
                this.R6I3TvhVUzjImNcsZnPIarNQNa08KFL5suF8ZyHVabZqiWX3lxOTmOWImMG2ChIe = "jpn";
                return;
            case 19:
                this.R6I3TvhVUzjImNcsZnPIarNQNa08KFL5suF8ZyHVabZqiWX3lxOTmOWImMG2ChIe = "nor";
                return;
            case 20:
                this.R6I3TvhVUzjImNcsZnPIarNQNa08KFL5suF8ZyHVabZqiWX3lxOTmOWImMG2ChIe = "pol";
                return;
            case 21:
                this.R6I3TvhVUzjImNcsZnPIarNQNa08KFL5suF8ZyHVabZqiWX3lxOTmOWImMG2ChIe = "por";
                return;
            case 22:
                this.R6I3TvhVUzjImNcsZnPIarNQNa08KFL5suF8ZyHVabZqiWX3lxOTmOWImMG2ChIe = "rus";
                return;
            case 23:
                this.R6I3TvhVUzjImNcsZnPIarNQNa08KFL5suF8ZyHVabZqiWX3lxOTmOWImMG2ChIe = "slv";
                return;
            case 24:
                this.R6I3TvhVUzjImNcsZnPIarNQNa08KFL5suF8ZyHVabZqiWX3lxOTmOWImMG2ChIe = "spa";
                return;
            case 25:
                this.R6I3TvhVUzjImNcsZnPIarNQNa08KFL5suF8ZyHVabZqiWX3lxOTmOWImMG2ChIe = "swe";
                return;
            case 26:
                this.R6I3TvhVUzjImNcsZnPIarNQNa08KFL5suF8ZyHVabZqiWX3lxOTmOWImMG2ChIe = "tur";
                return;
            default:
                this.R6I3TvhVUzjImNcsZnPIarNQNa08KFL5suF8ZyHVabZqiWX3lxOTmOWImMG2ChIe = "eng";
                return;
        }
    }

    @SimpleProperty(description = "Return the language code.")
    public String Language() {
        return this.R6I3TvhVUzjImNcsZnPIarNQNa08KFL5suF8ZyHVabZqiWX3lxOTmOWImMG2ChIe;
    }

    @DesignerProperty(defaultValue = "helloworld", editorType = "string")
    @SimpleProperty(description = "You can use the default api key: helloworld, or you can create your own api key at: https://ocr.space/ocrapi")
    public void ApiKey(String str) {
        String str2 = str;
        this.rtyU3Uj4Fd2cS2DWhNVfozs9qaFOsy3YcN33Msvg0fbnB6MZpRvgk3PrzB8p4A = str2;
    }

    @SimpleProperty(description = "Return the api key.")
    public String ApiKey() {
        return this.rtyU3Uj4Fd2cS2DWhNVfozs9qaFOsy3YcN33Msvg0fbnB6MZpRvgk3PrzB8p4A;
    }

    @SimpleProperty(description = "You can use the test image url if you have not any picture online on a server or else.")
    public String TestImageUrl() {
        return this.HThm2UFiN8mNIb2OEGwVoJpkwNLFwrt8FHi6kSBOC6T1EOtocK0hkK9xDo2LJOJZ;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "A searchable PDF file is a PDF file that includes text that can be searched upon using the standard Adobe Reader “search” functionality. In addition, the text can be selected and copied from the PDF. In this case the JSON response of the API contains a download link for the the searchable PDF. This download link is valid for one hour, than all data is removed from the OCR servers.")
    public void CreateSearchablePdf(boolean z) {
        Boolean valueOf = Boolean.valueOf(z);
        this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = valueOf;
    }

    @SimpleProperty(description = "Return the state of create searchable Pdf property.")
    public boolean CreateSearchablePdf() {
        return this.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq.booleanValue();
    }

    @SimpleProperty(description = "Set this block before you upload a image and before you try to get the response from a image url. If this property is set to true, the response content returns only the created pdf download link.")
    public void ReturnOnlyPdfLink(boolean z) {
        Boolean valueOf = Boolean.valueOf(z);
        this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = valueOf;
    }

    @SimpleProperty(description = "Set this block before you upload a image and before you try to get the response from a image url. If this property is set to true, the response content returns only the message. That means the response content will be only the scanned text in words.")
    public void ReturnOnlyMessage(boolean z) {
        boolean z2 = z;
        this.d234MfENUlM4amVCwiSVT0zMH9PGXT5eiUB6zvL6xVkA0Jl6b9GwzoZDcJDZRVrq = z2;
    }

    @SimpleFunction(description = "Get the server status from the free ocr.space server. This is helpful if you want to know if the server is online or offline. Returns true when online, else false when offline.")
    public void GetOcrServerStatus() throws Exception {
        C0989a aVar;
        new C0989a(this, (byte) 0);
        AsyncTask execute = aVar.execute(new String[]{"https://status.ocr.space"});
    }

    @SuppressLint({"StaticFieldLeak"})
    /* renamed from: com.google.appinventor.components.runtime.Ocr$a */
    class C0989a extends AsyncTask<String, String, String> {
        private /* synthetic */ Ocr hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        /* access modifiers changed from: protected */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            return Ocr.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, ((String[]) objArr)[0]);
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            Ocr.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, (String) obj);
        }

        private C0989a(Ocr ocr) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = ocr;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ C0989a(Ocr ocr, byte b) {
            this(ocr);
            byte b2 = b;
        }
    }

    private static String B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(String str, String str2) {
        StringBuilder sb;
        String str3 = str;
        String str4 = str2;
        String str5 = str3;
        try {
            String substring = str5.substring(str5.indexOf(str4) + str4.length(), str3.length());
            return substring.substring(0, substring.indexOf(" </span>"));
        } catch (Exception e) {
            Exception exc = e;
            int e2 = Log.e("Ocr", String.valueOf(exc));
            new StringBuilder();
            return sb.append(exc.getMessage()).toString();
        }
    }

    private static String wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(String str, String str2) {
        StringBuilder sb;
        JSONObject jSONObject;
        String string;
        JSONObject jSONObject2;
        String str3 = str;
        String str4 = str2;
        boolean z = true;
        try {
            switch (str4.hashCode()) {
                case 1016488190:
                    if (str4.equals("ParsedText")) {
                        z = false;
                        break;
                    }
                    break;
                case 1746365663:
                    if (str4.equals("SearchablePDFURL")) {
                        z = true;
                        break;
                    }
                    break;
            }
            switch (z) {
                case false:
                    new JSONObject(str3);
                    string = jSONObject2.getJSONArray("ParsedResults").getJSONObject(0).getString("ParsedText");
                    break;
                case true:
                    new JSONObject(str3);
                    string = jSONObject.getString("SearchablePDFURL");
                    break;
                default:
                    string = null;
                    break;
            }
            if (string != null) {
                return string;
            }
            return str3;
        } catch (Exception e) {
            Exception exc = e;
            new StringBuilder();
            return sb.append(exc.getMessage()).toString();
        }
    }

    private String hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO(String str) {
        StringBuilder sb;
        StringBuilder sb2;
        URL url;
        BufferedReader bufferedReader;
        Reader reader;
        String str2 = str;
        try {
            new StringBuilder();
            StringBuilder sb3 = sb2;
            new URL(str2);
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            HttpsURLConnection httpsURLConnection2 = httpsURLConnection;
            httpsURLConnection.setRequestMethod(HttpGet.METHOD_NAME);
            if (httpsURLConnection2.getResponseCode() == 200) {
                new InputStreamReader(httpsURLConnection2.getInputStream());
                new BufferedReader(reader);
                BufferedReader bufferedReader2 = bufferedReader;
                while (true) {
                    String readLine = bufferedReader2.readLine();
                    String str3 = readLine;
                    if (readLine != null) {
                        StringBuilder append = sb3.append(str3);
                    } else {
                        bufferedReader2.close();
                        this.bg8qLM0P8YgZYqRlUjDxWnoKnWRYKRDQeEjqrx0ja4Cy7jLWl3M1lZwjImM82eG = true;
                        return sb3.toString();
                    }
                }
            } else {
                this.bg8qLM0P8YgZYqRlUjDxWnoKnWRYKRDQeEjqrx0ja4Cy7jLWl3M1lZwjImM82eG = false;
                return "There is no internet connection. Please try again.";
            }
        } catch (Exception e) {
            Exception exc = e;
            int e2 = Log.e("Ocr", String.valueOf(exc));
            this.bg8qLM0P8YgZYqRlUjDxWnoKnWRYKRDQeEjqrx0ja4Cy7jLWl3M1lZwjImM82eG = false;
            new StringBuilder();
            return sb.append(exc.getMessage()).toString();
        }
    }

    /* access modifiers changed from: private */
    public static String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(JSONObject jSONObject) {
        StringBuilder sb;
        JSONObject jSONObject2 = jSONObject;
        try {
            new StringBuilder();
            StringBuilder sb2 = sb;
            boolean z = true;
            Iterator<String> keys = jSONObject2.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                Object obj = jSONObject2.get(next);
                if (z) {
                    z = false;
                } else {
                    StringBuilder append = sb2.append("&");
                }
                StringBuilder append2 = sb2.append(URLEncoder.encode(next, "UTF-8"));
                StringBuilder append3 = sb2.append("=");
                StringBuilder append4 = sb2.append(URLEncoder.encode(obj.toString(), "UTF-8"));
            }
            return sb2.toString();
        } catch (Exception e) {
            return null;
        }
    }

    static /* synthetic */ void B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(Ocr ocr, String str) {
        String str2 = str;
        ocr.GotServerStatus(B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(str2, "Free OCR API <span class=\"status {{ data.status }}\"> "), B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(str2, "PRO API (Endpoint #1, USA, East Coast)  <span class=\"status {{ data.status }}\"> "), B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(str2, "PRO API (Endpoint #1, USA, West Coast)  <span class=\"status {{ data.status }}\"> "), B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(str2, "PRO API (Endpoint #2, Europe)  <span class=\"status {{ data.status }}\"> "), B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(str2, "PRO API (Endpoint #3, Asia)   <span class=\"status {{ data.status }}\"> "));
    }
}

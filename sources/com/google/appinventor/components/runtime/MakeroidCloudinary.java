package com.google.appinventor.components.runtime;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
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
import com.google.appinventor.components.runtime.util.ErrorMessages;

@DesignerComponent(category = ComponentCategory.STORAGE, description = "Non-visible component that allows you to upload media to Cloudinary.", iconName = "images/cloudinary.png", nonVisible = true, version = 1)
@UsesLibraries(libraries = "cloudinary-android.jar, cloudinary-android.aar, cloudinary-core.jar, android-job.aar, android-job.jar")
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET, android.permission.READ_EXTERNAL_STORAGE")
public class MakeroidCloudinary extends AndroidNonvisibleComponent implements Component {
    /* access modifiers changed from: private */
    public String DHXdVBD10imZD254vgJdvVnoNnGi6ltYnt493xQ866MP9unX6nAi8XBtATXxBfV8;
    private String NqI9fRlBp15rsxXeNmRjOrUuQwDxdaDY0xSQj1B7T50sMWnnasnmhTtbykVZmafW;
    private final ComponentContainer container;
    private boolean havePermission = false;
    /* access modifiers changed from: private */
    public String rtyU3Uj4Fd2cS2DWhNVfozs9qaFOsy3YcN33Msvg0fbnB6MZpRvgk3PrzB8p4A;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MakeroidCloudinary(com.google.appinventor.components.runtime.ComponentContainer r5) {
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
            r2.havePermission = r3
            r2 = r0
            r3 = r1
            r2.container = r3
            java.lang.String r2 = "Cloudinary"
            java.lang.String r3 = "Cloudinary created"
            int r2 = android.util.Log.i(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.MakeroidCloudinary.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty
    public void CloudName(String str) {
        String str2 = str;
        this.NqI9fRlBp15rsxXeNmRjOrUuQwDxdaDY0xSQj1B7T50sMWnnasnmhTtbykVZmafW = str2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Your Cloudinary cloud name.")
    public String CloudName() {
        return this.NqI9fRlBp15rsxXeNmRjOrUuQwDxdaDY0xSQj1B7T50sMWnnasnmhTtbykVZmafW;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty
    public void APIKey(String str) {
        String str2 = str;
        this.rtyU3Uj4Fd2cS2DWhNVfozs9qaFOsy3YcN33Msvg0fbnB6MZpRvgk3PrzB8p4A = str2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Your Cloudinary API key.")
    public String APIKey() {
        return this.rtyU3Uj4Fd2cS2DWhNVfozs9qaFOsy3YcN33Msvg0fbnB6MZpRvgk3PrzB8p4A;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty
    public void APISecret(String str) {
        String str2 = str;
        this.DHXdVBD10imZD254vgJdvVnoNnGi6ltYnt493xQ866MP9unX6nAi8XBtATXxBfV8 = str2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Your Cloudinary API secret.")
    public String APISecret() {
        return this.DHXdVBD10imZD254vgJdvVnoNnGi6ltYnt493xQ866MP9unX6nAi8XBtATXxBfV8;
    }

    @SimpleFunction(description = "Uploads the specified media file to your Cloudinary media library.")
    public void UploadMedia(String str) {
        C0862a aVar;
        Runnable runnable;
        String str2 = str;
        if (str2 == null || str2.isEmpty()) {
            this.form.dispatchErrorOccurredEvent(this, "UploadMedia", ErrorMessages.ERROR_UNABLE_TO_LOAD_MEDIA, new Object[0]);
        } else if (!this.havePermission) {
            final String str3 = str2;
            new Runnable(this) {
                final /* synthetic */ MakeroidCloudinary hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
                }

                public final void run() {
                    PermissionResultHandler permissionResultHandler;
                    new PermissionResultHandler(this) {
                        private /* synthetic */ C08601 hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                        {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                        }

                        public final void HandlePermissionResponse(String str, boolean z) {
                            String str2 = str;
                            if (z) {
                                boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = MakeroidCloudinary.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
                                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.UploadMedia(str3);
                                return;
                            }
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchPermissionDeniedEvent((Component) this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "UploadMedia", "android.permission.READ_EXTERNAL_STORAGE");
                        }
                    };
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.askPermission("android.permission.READ_EXTERNAL_STORAGE", permissionResultHandler);
                }
            };
            this.form.runOnUiThread(runnable);
        } else {
            new C0862a(this, (byte) 0);
            AsyncTask execute = aVar.execute(new String[]{str2});
        }
    }

    @SuppressLint({"StaticFieldLeak"})
    /* renamed from: com.google.appinventor.components.runtime.MakeroidCloudinary$a */
    class C0862a extends AsyncTask<String, Void, String[]> {
        private /* synthetic */ MakeroidCloudinary hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            String[] strArr = (String[]) obj;
            if (strArr[0].equalsIgnoreCase("EXCEPTION_0")) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchPermissionDeniedEvent((Component) this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "UploadMedia", strArr[1]);
            } else if (strArr[0].equalsIgnoreCase("EXCEPTION_1")) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchErrorOccurredEvent(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "UploadMedia", Integer.valueOf(strArr[1]).intValue(), new Object[0]);
            } else if (strArr[0].equalsIgnoreCase("EXCEPTION_2")) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchErrorOccurredEvent(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "UploadMedia", ErrorMessages.ERROR_WEB_UNABLE_TO_POST_OR_PUT_FILE, new Object[0]);
            } else {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.MediaUploaded(strArr[0], strArr[1]);
            }
        }

        private C0862a(MakeroidCloudinary makeroidCloudinary) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = makeroidCloudinary;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ C0862a(MakeroidCloudinary makeroidCloudinary, byte b) {
            this(makeroidCloudinary);
            byte b2 = b;
        }

        /* access modifiers changed from: private */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x0353, code lost:
            r6 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x0354, code lost:
            r2 = r6;
            r6 = android.util.Log.e("Cloudinary", java.lang.String.valueOf(r2));
            r12 = new java.lang.String[2];
            r12[0] = "EXCEPTION_0";
            r12 = r12;
            new java.lang.StringBuilder();
            r12[1] = r12.append(r2.getMessage()).toString();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x038b, code lost:
            r6 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x038c, code lost:
            r2 = r6;
            r6 = android.util.Log.e("Cloudinary", "UploadMedia on cloudinary file exception.");
            r12 = new java.lang.String[2];
            r12[0] = "EXCEPTION_1";
            r12 = r12;
            new java.lang.StringBuilder();
            r12[1] = r12.append(r2.getErrorMessageNumber()).toString();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x03c1, code lost:
            r6 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x03c2, code lost:
            new java.lang.StringBuilder();
            r6 = android.util.Log.e("Cloudinary", r12.append(r6.getMessage()).toString());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
            return r12;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
            return r12;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
            return new java.lang.String[]{"EXCEPTION_2"};
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Removed duplicated region for block: B:26:0x0353 A[ExcHandler: PermissionException (r6v14 'e' com.google.appinventor.components.runtime.errors.PermissionException A[CUSTOM_DECLARE]), Splitter:B:1:0x0004] */
        /* JADX WARNING: Removed duplicated region for block: B:28:0x038b A[ExcHandler: FileException (r6v7 'e' com.google.appinventor.components.runtime.util.FileUtil$FileException A[CUSTOM_DECLARE]), Splitter:B:1:0x0004] */
        /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.String[] doInBackground(java.lang.String... r14) {
            /*
                r13 = this;
                r0 = r13
                r1 = r14
                r6 = r1
                r7 = 0
                r6 = r6[r7]     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r2 = r6
                r6 = r1
                r7 = 0
                r6 = r6[r7]     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                java.lang.String r7 = "file://"
                boolean r6 = r6.startsWith(r7)     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                if (r6 == 0) goto L_0x0020
                r6 = r2
                java.lang.String r7 = "file://"
                java.lang.String r8 = ""
                java.lang.String r6 = r6.replace(r7, r8)     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r2 = r6
            L_0x0020:
                r6 = r2
                r7 = 46
                int r6 = r6.lastIndexOf(r7)     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r1 = r7
                if (r6 <= 0) goto L_0x02cf
                r6 = r2
                r7 = r1
                r8 = 1
                int r7 = r7 + 1
                java.lang.String r6 = r6.substring(r7)     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
            L_0x0036:
                r1 = r6
                java.io.File r6 = new java.io.File     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = r2
                r7.<init>(r8)     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r2 = r6
                com.cloudinary.Cloudinary r6 = new com.cloudinary.Cloudinary     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 6
                java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r8
                r8 = r12
                r9 = r12
                r10 = 0
                java.lang.String r11 = "cloud_name"
                r9[r10] = r11     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r8
                r8 = r12
                r9 = r12
                r10 = 1
                r11 = r0
                com.google.appinventor.components.runtime.MakeroidCloudinary r11 = r11.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                java.lang.String r11 = com.google.appinventor.components.runtime.MakeroidCloudinary.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((com.google.appinventor.components.runtime.MakeroidCloudinary) r11)     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r9[r10] = r11     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r8
                r8 = r12
                r9 = r12
                r10 = 2
                java.lang.String r11 = "api_key"
                r9[r10] = r11     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r8
                r8 = r12
                r9 = r12
                r10 = 3
                r11 = r0
                com.google.appinventor.components.runtime.MakeroidCloudinary r11 = r11.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                java.lang.String r11 = r11.rtyU3Uj4Fd2cS2DWhNVfozs9qaFOsy3YcN33Msvg0fbnB6MZpRvgk3PrzB8p4A     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r9[r10] = r11     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r8
                r8 = r12
                r9 = r12
                r10 = 4
                java.lang.String r11 = "api_secret"
                r9[r10] = r11     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r8
                r8 = r12
                r9 = r12
                r10 = 5
                r11 = r0
                com.google.appinventor.components.runtime.MakeroidCloudinary r11 = r11.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                java.lang.String r11 = r11.DHXdVBD10imZD254vgJdvVnoNnGi6ltYnt493xQ866MP9unX6nAi8XBtATXxBfV8     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r9[r10] = r11     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                java.util.Map r8 = com.cloudinary.utils.ObjectUtils.asMap(r8)     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r7.<init>(r8)     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r3 = r6
                r6 = 30
                java.lang.String[] r6 = new java.lang.String[r6]     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 0
                java.lang.String r9 = "ai"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 1
                java.lang.String r9 = "gif"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 2
                java.lang.String r9 = "webp"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 3
                java.lang.String r9 = "bmp"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 4
                java.lang.String r9 = "djvu"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 5
                java.lang.String r9 = "ps"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 6
                java.lang.String r9 = "ept"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 7
                java.lang.String r9 = "eps"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 8
                java.lang.String r9 = "eps3"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 9
                java.lang.String r9 = "flif"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 10
                java.lang.String r9 = "heif"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 11
                java.lang.String r9 = "heic"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 12
                java.lang.String r9 = "ico"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 13
                java.lang.String r9 = "jpg"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 14
                java.lang.String r9 = "jpe"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 15
                java.lang.String r9 = "jpeg"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 16
                java.lang.String r9 = "jpc"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 17
                java.lang.String r9 = "jp2"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 18
                java.lang.String r9 = "j2k"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 19
                java.lang.String r9 = "wdp"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 20
                java.lang.String r9 = "jxr"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 21
                java.lang.String r9 = "hdp"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 22
                java.lang.String r9 = "png"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 23
                java.lang.String r9 = "psd"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 24
                java.lang.String r9 = "arw"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 25
                java.lang.String r9 = "cr2"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 26
                java.lang.String r9 = "svg"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 27
                java.lang.String r9 = "tga"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 28
                java.lang.String r9 = "tif"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 29
                java.lang.String r9 = "tiff"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r4 = r6
                r6 = 18
                java.lang.String[] r6 = new java.lang.String[r6]     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 0
                java.lang.String r9 = "mp4"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 1
                java.lang.String r9 = "webm"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 2
                java.lang.String r9 = "flv"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 3
                java.lang.String r9 = "mov"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 4
                java.lang.String r9 = "ogv"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 5
                java.lang.String r9 = "3gp"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 6
                java.lang.String r9 = "3g2"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 7
                java.lang.String r9 = "wmv"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 8
                java.lang.String r9 = "mpeg"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 9
                java.lang.String r9 = "flv"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 10
                java.lang.String r9 = "mkv"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 11
                java.lang.String r9 = "avi"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 12
                java.lang.String r9 = "mp3"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 13
                java.lang.String r9 = "wav"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 14
                java.lang.String r9 = "aac"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 15
                java.lang.String r9 = "ogg"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 16
                java.lang.String r9 = "wma"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 17
                java.lang.String r9 = "flac"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r5 = r6
                r6 = r4
                java.util.List r6 = java.util.Arrays.asList(r6)     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r7 = r1
                boolean r6 = r6.contains(r7)     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                if (r6 == 0) goto L_0x02d4
                r6 = 2
                java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 0
                java.lang.String r9 = "resource_type"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 1
                java.lang.String r9 = "image"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                java.util.Map r6 = com.cloudinary.utils.ObjectUtils.asMap(r6)     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r1 = r6
            L_0x0293:
                r6 = r3
                com.cloudinary.Uploader r6 = r6.uploader()     // Catch:{ Exception -> 0x0317, PermissionException -> 0x0353, FileException -> 0x038b }
                r7 = r2
                r8 = r1
                java.util.Map r6 = r6.upload(r7, r8)     // Catch:{ Exception -> 0x0317, PermissionException -> 0x0353, FileException -> 0x038b }
                r1 = r6
                java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0317, PermissionException -> 0x0353, FileException -> 0x038b }
                r12 = r6
                r6 = r12
                r7 = r12
                r7.<init>()     // Catch:{ Exception -> 0x0317, PermissionException -> 0x0353, FileException -> 0x038b }
                r7 = r1
                java.lang.String r8 = "secure_url"
                java.lang.Object r7 = r7.get(r8)     // Catch:{ Exception -> 0x0317, PermissionException -> 0x0353, FileException -> 0x038b }
                java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ Exception -> 0x0317, PermissionException -> 0x0353, FileException -> 0x038b }
                java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x0317, PermissionException -> 0x0353, FileException -> 0x038b }
                r2 = r6
                java.lang.String r6 = "sucessful"
                r1 = r6
                r6 = 2
                java.lang.String[] r6 = new java.lang.String[r6]     // Catch:{ Exception -> 0x0317, PermissionException -> 0x0353, FileException -> 0x038b }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 0
                r9 = r1
                r7[r8] = r9     // Catch:{ Exception -> 0x0317, PermissionException -> 0x0353, FileException -> 0x038b }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 1
                r9 = r2
                r7[r8] = r9     // Catch:{ Exception -> 0x0317, PermissionException -> 0x0353, FileException -> 0x038b }
                r0 = r6
            L_0x02ce:
                return r0
            L_0x02cf:
                java.lang.String r6 = ""
                goto L_0x0036
            L_0x02d4:
                r6 = r5
                java.util.List r6 = java.util.Arrays.asList(r6)     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r7 = r1
                boolean r6 = r6.contains(r7)     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                if (r6 == 0) goto L_0x02fb
                r6 = 2
                java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 0
                java.lang.String r9 = "resource_type"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 1
                java.lang.String r9 = "video"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                java.util.Map r6 = com.cloudinary.utils.ObjectUtils.asMap(r6)     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r1 = r6
                goto L_0x0293
            L_0x02fb:
                r6 = 2
                java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 0
                java.lang.String r9 = "resource_type"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 1
                java.lang.String r9 = "raw"
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                java.util.Map r6 = com.cloudinary.utils.ObjectUtils.asMap(r6)     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r1 = r6
                goto L_0x0293
            L_0x0317:
                r6 = move-exception
                r1 = r6
                java.lang.String r6 = "Cloudinary"
                r7 = r1
                java.lang.String r7 = java.lang.String.valueOf(r7)     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                int r6 = android.util.Log.e(r6, r7)     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r7.<init>()     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r7 = r1
                java.lang.String r7 = r7.getMessage()     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                java.lang.String r6 = r6.toString()     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r1 = r6
                java.lang.String r6 = ""
                r2 = r6
                r6 = 2
                java.lang.String[] r6 = new java.lang.String[r6]     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 0
                r9 = r1
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 1
                r9 = r2
                r7[r8] = r9     // Catch:{ PermissionException -> 0x0353, FileException -> 0x038b, Exception -> 0x03c1 }
                r0 = r6
                goto L_0x02ce
            L_0x0353:
                r6 = move-exception
                r2 = r6
                java.lang.String r6 = "Cloudinary"
                r7 = r2
                java.lang.String r7 = java.lang.String.valueOf(r7)
                int r6 = android.util.Log.e(r6, r7)
                r6 = 2
                java.lang.String[] r6 = new java.lang.String[r6]
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 0
                java.lang.String r9 = "EXCEPTION_0"
                r7[r8] = r9
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 1
                java.lang.StringBuilder r9 = new java.lang.StringBuilder
                r12 = r9
                r9 = r12
                r10 = r12
                r10.<init>()
                r10 = r2
                java.lang.String r10 = r10.getMessage()
                java.lang.StringBuilder r9 = r9.append(r10)
                java.lang.String r9 = r9.toString()
                r7[r8] = r9
                r0 = r6
                goto L_0x02ce
            L_0x038b:
                r6 = move-exception
                r2 = r6
                java.lang.String r6 = "Cloudinary"
                java.lang.String r7 = "UploadMedia on cloudinary file exception."
                int r6 = android.util.Log.e(r6, r7)
                r6 = 2
                java.lang.String[] r6 = new java.lang.String[r6]
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 0
                java.lang.String r9 = "EXCEPTION_1"
                r7[r8] = r9
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 1
                java.lang.StringBuilder r9 = new java.lang.StringBuilder
                r12 = r9
                r9 = r12
                r10 = r12
                r10.<init>()
                r10 = r2
                int r10 = r10.getErrorMessageNumber()
                java.lang.StringBuilder r9 = r9.append(r10)
                java.lang.String r9 = r9.toString()
                r7[r8] = r9
                r0 = r6
                goto L_0x02ce
            L_0x03c1:
                r6 = move-exception
                r2 = r6
                java.lang.String r6 = "Cloudinary"
                java.lang.StringBuilder r7 = new java.lang.StringBuilder
                r12 = r7
                r7 = r12
                r8 = r12
                r8.<init>()
                r8 = r2
                java.lang.String r8 = r8.getMessage()
                java.lang.StringBuilder r7 = r7.append(r8)
                java.lang.String r7 = r7.toString()
                int r6 = android.util.Log.e(r6, r7)
                r6 = 1
                java.lang.String[] r6 = new java.lang.String[r6]
                r12 = r6
                r6 = r12
                r7 = r12
                r8 = 0
                java.lang.String r9 = "EXCEPTION_2"
                r7[r8] = r9
                r0 = r6
                goto L_0x02ce
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.MakeroidCloudinary.C0862a.doInBackground(java.lang.String[]):java.lang.String[]");
        }
    }

    @SimpleEvent(description = "Event raised after the Upload Media block has been used")
    public void MediaUploaded(String str, String str2) {
        Object[] objArr = new Object[2];
        objArr[0] = str;
        Object[] objArr2 = objArr;
        objArr2[1] = str2;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "MediaUploaded", objArr2);
    }
}

package com.google.appinventor.components.runtime;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.p000v4.content.FileProvider;
import android.util.Log;
import android.webkit.MimeTypeMap;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import java.io.File;

@SimpleObject
@DesignerComponent(category = ComponentCategory.SOCIAL, description = "Sharing is a non-visible component that enables sharing files and/or messages between your app and other apps installed on a device. The component will display a list of the installed apps that can handle the information provided, and will allow the user to choose one to share the content with, for instance a mail app, a social network app, a texting app, and so on.<br>The file path can be taken directly from other components such as the Camera or the ImagePicker, but can also be specified directly to read from storage. Be aware that different devices treat storage differently, so a few things to try if, for instance, you have a file called arrow.gif in the folder <code>Appinventor/assets</code>, would be: <ul><li><code>\"file:///sdcard/Appinventor/assets/arrow.gif\"</code></li> or <li><code>\"/storage/Appinventor/assets/arrow.gif\"</code></li></ul>", iconName = "images/sharing.png", nonVisible = true, version = 2)
@UsesPermissions(permissionNames = "android.permission.READ_EXTERNAL_STORAGE, android.permission.WRITE_EXTERNAL_STORAGE")
public class Sharing extends AndroidNonvisibleComponent {
    private Context context;
    private String roiS8tAeAqybL08NoxfqzQ1mPU3hAwiV5i3h5unWOaW17ApoVrdrKjoB0Q8IrS8T = "";

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Sharing(com.google.appinventor.components.runtime.ComponentContainer r6) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            java.lang.String r3 = ""
            r2.roiS8tAeAqybL08NoxfqzQ1mPU3hAwiV5i3h5unWOaW17ApoVrdrKjoB0Q8IrS8T = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            android.os.StrictMode$VmPolicy$Builder r2 = new android.os.StrictMode$VmPolicy$Builder
            r4 = r2
            r2 = r4
            r3 = r4
            r3.<init>()
            android.os.StrictMode$VmPolicy r2 = r2.build()
            android.os.StrictMode.setVmPolicy(r2)
            r2 = r0
            java.lang.String r3 = ""
            r2.ShareDialogMessage(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Sharing.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleFunction(description = "Shares a message through any capable application installed on the phone by displaying a list of the available apps and allowing the user to choose one from the list. The selected app will open with the message inserted on it.")
    public void ShareMessage(String str) {
        Intent intent;
        new Intent("android.intent.action.SEND");
        Intent intent2 = intent;
        Intent intent3 = intent2;
        Intent putExtra = intent2.putExtra("android.intent.extra.TEXT", str);
        Intent type = intent3.setType("text/plain");
        this.form.startActivity(Intent.createChooser(intent3, this.roiS8tAeAqybL08NoxfqzQ1mPU3hAwiV5i3h5unWOaW17ApoVrdrKjoB0Q8IrS8T));
    }

    @SimpleFunction(description = "Shares a file through any capable application installed on the phone by displaying a list of the available apps and allowing the user to choose one from the list. The selected app will open with the file inserted on it.")
    public void ShareFile(String str) {
        ShareFileWithMessage(str, "");
    }

    @SimpleFunction(description = "Shares both a file and a message through any capable application installed on the phone by displaying a list of available apps and allowing the user to  choose one from the list. The selected app will open with the file and message inserted on it.")
    public void ShareFileWithMessage(String str, String str2) {
        File file;
        StringBuilder sb;
        Intent intent;
        String str3 = str;
        String str4 = str2;
        if (str3 != null && !str3.isEmpty()) {
            try {
                if (!str3.startsWith("file://")) {
                    str3 = "file://".concat(String.valueOf(str3));
                }
                Uri parse = Uri.parse(str3);
                new File(parse.getPath());
                File file2 = file;
                File file3 = file2;
                if (file2.isFile()) {
                    String str5 = str3;
                    String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(str5.substring(str5.lastIndexOf(".") + 1).toLowerCase());
                    String str6 = mimeTypeFromExtension;
                    if (mimeTypeFromExtension == null) {
                        str6 = "application/octet-stream";
                    }
                    Context context2 = this.context;
                    new StringBuilder();
                    Uri uriForFile = FileProvider.getUriForFile(context2, sb.append(this.context.getPackageName()).append(".provider").toString(), file3);
                    new Intent("android.intent.action.SEND");
                    Intent intent2 = intent;
                    Intent intent3 = intent2;
                    Intent putExtra = intent2.putExtra("android.intent.extra.STREAM", parse);
                    Intent putExtra2 = intent3.putExtra("android.intent.extra.STREAM", uriForFile);
                    Intent flags = intent3.setFlags(1);
                    Intent type = intent3.setType(str6);
                    if (str4 != null && !str4.isEmpty()) {
                        Intent putExtra3 = intent3.putExtra("android.intent.extra.TEXT", str4);
                    }
                    this.form.startActivity(Intent.createChooser(intent3, this.roiS8tAeAqybL08NoxfqzQ1mPU3hAwiV5i3h5unWOaW17ApoVrdrKjoB0Q8IrS8T));
                    return;
                }
                String str7 = "ShareFile";
                if (str4 != null && str4.isEmpty()) {
                    str7 = "ShareFileWithMessage";
                }
                this.form.dispatchErrorOccurredEvent(this, str7, ErrorMessages.ERROR_FILE_NOT_FOUND_FOR_SHARING, str3);
            } catch (PermissionException e) {
                this.form.dispatchPermissionDeniedEvent((Component) this, "ShareFileWithMessage", e);
            } catch (Exception e2) {
                int e3 = Log.e("SocialMediaSharing", String.valueOf(e2));
            }
        }
    }

    @SimpleEvent(description = "This event returns the social media name if an app is not installed. Possible names are 'Facebook Messenger', 'Facebook', 'Twitter', 'Telegram', 'Twitter', 'Snapchat', 'Google Plus' or the given custom package name.")
    public void AppNotFound(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AppNotFound", str);
    }

    @DesignerProperty(defaultValue = "Send using...", editorType = "string")
    @SimpleProperty(description = "Set the text for the sharing dialog. The default text is 'Send using...'.")
    public void ShareDialogMessage(String str) {
        String str2 = str;
        if (str2 == null || str2.isEmpty()) {
            str2 = "Send using...";
            int d = Log.d("SocialMediaSharing", "ShareDialogMessage- User forgot to add a default share dialog text. Use default text.");
        }
        this.roiS8tAeAqybL08NoxfqzQ1mPU3hAwiV5i3h5unWOaW17ApoVrdrKjoB0Q8IrS8T = str2;
    }

    @SimpleFunction(description = "Shares a message through Facebook Messenger. If Messenger is not installed, then the 'AppNotFound' event will be invoked and return the name 'Facebook Messenger'.")
    public void ShareMessageToFacebookMessenger(String str) {
        String str2 = str;
        if (str2 == null || str2.isEmpty()) {
            str2 = "Checkout www.kodular.io - Android Builder.";
            int d = Log.d("SocialMediaSharing", "ShareMessageToFacebookMessenger- User forgot to add a message. Use default message.");
        }
        ShareUtil("com.facebook.orca", str2, "Facebook Messenger", "ShareMessageToFacebookMessenger- ");
    }

    @SimpleFunction(description = "Shares a message through Facebook. If Facebook is not installed, then the 'AppNotFound' event will be invoked and return the name 'Facebook'.")
    public void ShareMessageToFacebook(String str) {
        String str2 = str;
        if (str2 == null || str2.isEmpty()) {
            str2 = "Checkout www.kodular.io - Android Builder.";
            int d = Log.d("SocialMediaSharing", "ShareMessageToFacebook- User forgot to add a message. Use default message.");
        }
        ShareUtil("com.facebook.katana", str2, "Facebook", "ShareMessageToFacebook- ");
    }

    @SimpleFunction(description = "Shares a message through Twitter. If Twitter is not installed, then the 'AppNotFound' event will be invoked and return the name 'Twitter'.")
    public void ShareMessageToTwitter(String str) {
        String str2 = str;
        if (str2 == null || str2.isEmpty()) {
            str2 = "Checkout www.kodular.io - Android Builder.";
            int d = Log.d("SocialMediaSharing", "ShareMessageToTwitter- User forgot to add a message. Use default message.");
        }
        ShareUtil("com.twitter.android", str2, "Twitter", "ShareMessageToTwitter- ");
    }

    @SimpleFunction(description = "Shares a message through Telegram. If Telegram is not installed, then the 'AppNotFound' event will be invoked and return the name 'Telegram'.")
    public void ShareMessageToTelegram(String str) {
        String str2 = str;
        if (str2 == null || str2.isEmpty()) {
            str2 = "Checkout www.kodular.io - Android Builder.";
            int d = Log.d("SocialMediaSharing", "ShareMessageToTelegram- User forgot to add a message. Use default message.");
        }
        ShareUtil("org.telegram.messenger", str2, "Telegram", "ShareMessageToTelegram- ");
    }

    @SimpleFunction(description = "Shares a message through WhatsApp. If WhatsApp is not installed, then the 'AppNotFound' event will be invoked and return the name 'WhatsApp'.")
    public void ShareMessageToWhatsApp(String str) {
        String str2 = str;
        if (str2 == null || str2.isEmpty()) {
            str2 = "Checkout www.kodular.io - Android Builder.";
            int d = Log.d("SocialMediaSharing", "ShareMessageToWhatsApp- User forgot to add a message. Use default message.");
        }
        ShareUtil("com.whatsapp", str2, "WhatsApp", "ShareMessageToWhatsApp- ");
    }

    @SimpleFunction(description = "Shares a message through Snapchat. If Snapchat is not installed, then the 'AppNotFound' event will be invoked and return the name 'Snapchat'.")
    public void ShareMessageToSnapchat(String str) {
        String str2 = str;
        if (str2 == null || str2.isEmpty()) {
            str2 = "Checkout www.kodular.io - Android Builder.";
            int d = Log.d("SocialMediaSharing", "ShareMessageToSnapchat- User forgot to add a message. Use default message.");
        }
        ShareUtil("com.snapchat.android", str2, "Snapchat", "ShareMessageToSnapchat- ");
    }

    @SimpleFunction(description = "Shares a message through Google Plus. If Google+ is not installed, then the 'AppNotFound' event will be invoked and return the name 'Google Plus'.")
    public void ShareMessageToGooglePlus(String str) {
        String str2 = str;
        if (str2 == null || str2.isEmpty()) {
            str2 = "Checkout www.kodular.io - Android Builder.";
            int d = Log.d("SocialMediaSharing", "ShareMessageToGooglePlus- User forgot to add a message. Use default message.");
        }
        ShareUtil("com.google.android.apps.plus", str2, "Google Plus", "ShareMessageToGooglePlus- ");
    }

    @SimpleFunction(description = "Shares a message through the given app. If the given app is not installed, then the 'AppNotFound' event will be invoked and return the given name.")
    public void ShareMessageTo(String str, String str2, String str3) {
        String str4 = str;
        String str5 = str2;
        String str6 = str3;
        if (str4 == null || str4.isEmpty()) {
            str4 = "Checkout www.kodular.io - Android Builder.";
            int d = Log.d("SocialMediaSharing", "ShareMessageTo- User forgot to add a message. Use default message.");
        }
        if (str5 == null || str5.isEmpty()) {
            str5 = "Custom";
            int d2 = Log.d("SocialMediaSharing", "ShareMessageTo- User forgot to add a name. Use default custom name.");
        }
        ShareUtil(str6, str4, str5, "ShareMessageTo- ");
    }

    public void ShareUtil(String str, String str2, String str3, String str4) {
        Intent intent;
        StringBuilder sb;
        String str5 = str3;
        String str6 = str4;
        new Intent("android.intent.action.SEND");
        Intent intent2 = intent;
        Intent intent3 = intent2;
        Intent putExtra = intent2.putExtra("android.intent.extra.TEXT", str2);
        Intent type = intent3.setType("text/plain");
        Intent intent4 = intent3.setPackage(str);
        try {
            this.form.startActivity(intent3);
        } catch (ActivityNotFoundException e) {
            AppNotFound(str5);
            new StringBuilder();
            int d = Log.d("SocialMediaSharing", sb.append(str6).append(e.getMessage()).toString());
        }
    }
}

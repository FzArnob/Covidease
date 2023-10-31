package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.support.p000v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.google.appinventor.components.runtime.util.AsynchUtil;
import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.shaded.apache.http.HttpHost;
import org.shaded.apache.http.client.methods.HttpHead;

@DesignerComponent(category = ComponentCategory.CONNECTIVITY, description = "The Download component is a non-visible component that allows users to download any file to the device", iconName = "images/download.png", nonVisible = true, version = 2)
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET,android.permission.WRITE_EXTERNAL_STORAGE, android.permission.DOWNLOAD_WITHOUT_NOTIFICATION")
public final class Download extends AndroidNonvisibleComponent implements Component {
    private long B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    private String BG2b8dRaEUNcWY5vxXAWfSqyg8ZvS4tysLlCvHPUDp1bRFO56FHQ3a9NvDEKgkS = "Downloading file..";
    private boolean DmQGLROFyZ9Eo0RSsJcpZNxJZjgcsPDfYPi3awNwmyyErT71sGU5mvgG4PDW3yL = true;
    /* access modifiers changed from: private */
    public boolean G9FdWvm6zShqpzDC2UOqX6MiQExLolZTefPBjzVvfkE9Kp2rmQld5rkb2wriBYfL = true;
    private boolean IhlDYVsQmgat6F3NXqRok975lHQlAvyJICX3QHDdE383xYIGTapMORiCm1KjyWCi = true;
    private String YP4juQGK8ZTsNMOy2BKg810SeLJ3amlj2BQC8tc7uqP2LdhyRsu8lUHvdJ0v9u = "NewFile.png";
    private boolean ZXVyhZW2wwbAysjXrMReFP00vcRkftFV6dFiSCOUB0OBlMJVjuhF9XlRGX7w6PdR = false;
    private Activity activity;
    private Context context;
    private String gti6bIqu0aXgALoDtkq1foMnPcdzXO0EAPAgw4lcaEF132GirFLns82VqwKc8x6R = "";
    private final BroadcastReceiver hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO;
    private DownloadManager.Request hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private DownloadManager f369hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private ConnectivityManager f370hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private String n86Bi3TT8lkq4OIAY6TQzz40yEQcROLeML3EOzGKfDOtlcMXWXcB7P2XOFzT0DDv = "Download..";
    private boolean tj0MDuMmBVyFcp8vwXpkfd0RnoyqL9aUR0zh2QG1qIbcD4cqzuxOkXiR3Ef5Sjag = false;
    /* access modifiers changed from: private */
    public boolean vB3WjEtL56PUGm0spJ96S19MI1O4vPR6yju8tUYcKrC4atk0AV5GbVcHQNB7BlIK = false;
    private final BroadcastReceiver vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq;

    static /* synthetic */ long hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Download download, long j) {
        long j2 = j;
        long j3 = j2;
        download.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = j3;
        return j2;
    }

    static /* synthetic */ DownloadManager.Request hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Download download, DownloadManager.Request request) {
        DownloadManager.Request request2 = request;
        DownloadManager.Request request3 = request2;
        download.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = request3;
        return request2;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Download(com.google.appinventor.components.runtime.ComponentContainer r9) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            java.lang.String r3 = "Downloading file.."
            r2.BG2b8dRaEUNcWY5vxXAWfSqyg8ZvS4tysLlCvHPUDp1bRFO56FHQ3a9NvDEKgkS = r3
            r2 = r0
            java.lang.String r3 = "Download.."
            r2.n86Bi3TT8lkq4OIAY6TQzz40yEQcROLeML3EOzGKfDOtlcMXWXcB7P2XOFzT0DDv = r3
            r2 = r0
            java.lang.String r3 = ""
            r2.gti6bIqu0aXgALoDtkq1foMnPcdzXO0EAPAgw4lcaEF132GirFLns82VqwKc8x6R = r3
            r2 = r0
            java.lang.String r3 = "NewFile.png"
            r2.YP4juQGK8ZTsNMOy2BKg810SeLJ3amlj2BQC8tc7uqP2LdhyRsu8lUHvdJ0v9u = r3
            r2 = r0
            r3 = 0
            r2.tj0MDuMmBVyFcp8vwXpkfd0RnoyqL9aUR0zh2QG1qIbcD4cqzuxOkXiR3Ef5Sjag = r3
            r2 = r0
            r3 = 1
            r2.G9FdWvm6zShqpzDC2UOqX6MiQExLolZTefPBjzVvfkE9Kp2rmQld5rkb2wriBYfL = r3
            r2 = r0
            r3 = 1
            r2.IhlDYVsQmgat6F3NXqRok975lHQlAvyJICX3QHDdE383xYIGTapMORiCm1KjyWCi = r3
            r2 = r0
            r3 = 1
            r2.DmQGLROFyZ9Eo0RSsJcpZNxJZjgcsPDfYPi3awNwmyyErT71sGU5mvgG4PDW3yL = r3
            r2 = r0
            r3 = 0
            r2.ZXVyhZW2wwbAysjXrMReFP00vcRkftFV6dFiSCOUB0OBlMJVjuhF9XlRGX7w6PdR = r3
            r2 = r0
            r3 = 0
            r2.vB3WjEtL56PUGm0spJ96S19MI1O4vPR6yju8tUYcKrC4atk0AV5GbVcHQNB7BlIK = r3
            r2 = r0
            com.google.appinventor.components.runtime.Download$2 r3 = new com.google.appinventor.components.runtime.Download$2
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r0
            r4.<init>(r5)
            r2.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = r3
            r2 = r0
            com.google.appinventor.components.runtime.Download$3 r3 = new com.google.appinventor.components.runtime.Download$3
            r7 = r3
            r3 = r7
            r4 = r7
            r5 = r0
            r4.<init>(r5)
            r2.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.activity = r3
            r2 = r0
            android.app.Activity r2 = r2.activity
            r3 = r0
            android.content.BroadcastReceiver r3 = r3.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq
            android.content.IntentFilter r4 = new android.content.IntentFilter
            r7 = r4
            r4 = r7
            r5 = r7
            java.lang.String r6 = "android.intent.action.DOWNLOAD_NOTIFICATION_CLICKED"
            r5.<init>(r6)
            android.content.Intent r2 = r2.registerReceiver(r3, r4)
            r2 = r0
            android.app.Activity r2 = r2.activity
            r3 = r0
            android.content.BroadcastReceiver r3 = r3.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO
            android.content.IntentFilter r4 = new android.content.IntentFilter
            r7 = r4
            r4 = r7
            r5 = r7
            java.lang.String r6 = "android.intent.action.DOWNLOAD_COMPLETE"
            r5.<init>(r6)
            android.content.Intent r2 = r2.registerReceiver(r3, r4)
            r2 = r0
            r7 = r2
            r2 = r7
            r3 = r7
            android.content.Context r3 = r3.context
            java.lang.String r4 = "download"
            java.lang.Object r3 = r3.getSystemService(r4)
            android.app.DownloadManager r3 = (android.app.DownloadManager) r3
            r2.f369hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            java.lang.String r2 = "Download"
            java.lang.String r3 = "Download Extension Created"
            int r2 = android.util.Log.d(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Download.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @Deprecated
    @SimpleProperty(description = "This function is deprecated. Do not use it anymore. We will remove it in the future. Since we support min API 14 the download manager is by default available. The download manager was added in API 9.")
    public final boolean isDownloadManagerAvailable() {
        return true;
    }

    @DesignerProperty(defaultValue = "Downloading file..", editorType = "string")
    @SimpleProperty(description = "Set the description that you will see in the download notification.")
    public final void Description(String str) {
        String str2 = str;
        int d = Log.d("Download", "Description text is: ".concat(String.valueOf(str2)));
        String str3 = str2;
        this.BG2b8dRaEUNcWY5vxXAWfSqyg8ZvS4tysLlCvHPUDp1bRFO56FHQ3a9NvDEKgkS = str3;
    }

    @DesignerProperty(defaultValue = "Download..", editorType = "string")
    @SimpleProperty(description = "Set the title that you will see in the download notification.")
    public final void Title(String str) {
        String str2 = str;
        int d = Log.d("Download", "Title text is: ".concat(String.valueOf(str2)));
        String str3 = str2;
        this.n86Bi3TT8lkq4OIAY6TQzz40yEQcROLeML3EOzGKfDOtlcMXWXcB7P2XOFzT0DDv = str3;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty(description = "Set here the url to the file that you want to download.")
    public final void DownloadUrl(String str) {
        String str2 = str;
        int d = Log.d("Download", "DownloadUrl text is: ".concat(String.valueOf(str2)));
        String str3 = str2;
        this.gti6bIqu0aXgALoDtkq1foMnPcdzXO0EAPAgw4lcaEF132GirFLns82VqwKc8x6R = str3;
    }

    @DesignerProperty(defaultValue = "NewFile.png", editorType = "string")
    @SimpleProperty(description = "Set here the new filename for the file that you want to download.")
    public final void SaveFileAs(String str) {
        String str2 = str;
        int d = Log.d("Download", "SaveFileAs text is: ".concat(String.valueOf(str2)));
        String str3 = str2;
        this.YP4juQGK8ZTsNMOy2BKg810SeLJ3amlj2BQC8tc7uqP2LdhyRsu8lUHvdJ0v9u = str3;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "Set whether this download may proceed over a roaming connection.")
    public final void AllowedOverRoaming(boolean z) {
        boolean z2 = z;
        int d = Log.d("Download", "AllowedOverRoaming: ".concat(String.valueOf(z2)));
        boolean z3 = z2;
        this.tj0MDuMmBVyFcp8vwXpkfd0RnoyqL9aUR0zh2QG1qIbcD4cqzuxOkXiR3Ef5Sjag = z3;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "If false you will see a toast message with a error message when a error is occurred.")
    public final void SuppressWarnings(boolean z) {
        boolean z2 = z;
        int d = Log.d("Download", "SuppressWarnings: ".concat(String.valueOf(z2)));
        boolean z3 = z2;
        this.G9FdWvm6zShqpzDC2UOqX6MiQExLolZTefPBjzVvfkE9Kp2rmQld5rkb2wriBYfL = z3;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "Control whether a system notification is posted by the download manager while this download is running or when it is completed.")
    public final void ShowNotification(boolean z) {
        boolean z2 = z;
        int d = Log.d("Download", "ShowNotification: ".concat(String.valueOf(z2)));
        boolean z3 = z2;
        this.IhlDYVsQmgat6F3NXqRok975lHQlAvyJICX3QHDdE383xYIGTapMORiCm1KjyWCi = z3;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "If the file to be downloaded is to be scanned by MediaScanner.")
    public final void ScanningByMediaScanner(boolean z) {
        boolean z2 = z;
        int d = Log.d("Download", "ScanningByMediaScanner: ".concat(String.valueOf(z2)));
        boolean z3 = z2;
        this.DmQGLROFyZ9Eo0RSsJcpZNxJZjgcsPDfYPi3awNwmyyErT71sGU5mvgG4PDW3yL = z3;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "Specify that to run this download, the device needs to be plugged in. Works only for devices with api >= 24.")
    public final void RequiresCharging(boolean z) {
        boolean z2 = z;
        int d = Log.d("Download", "RequiresCharging: ".concat(String.valueOf(z2)));
        boolean z3 = z2;
        this.ZXVyhZW2wwbAysjXrMReFP00vcRkftFV6dFiSCOUB0OBlMJVjuhF9XlRGX7w6PdR = z3;
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "Specify that to run, the download needs the device to be in idle mode. Idle mode is a loose definition provided by the system, which means that the device is not in use, and has not been in use for some time. Works only for devices with api >= 24.")
    public final void RequiresDeviceIdle(boolean z) {
        boolean z2 = z;
        int d = Log.d("Download", "RequiresDeviceIdle: ".concat(String.valueOf(z2)));
        boolean z3 = z2;
        this.vB3WjEtL56PUGm0spJ96S19MI1O4vPR6yju8tUYcKrC4atk0AV5GbVcHQNB7BlIK = z3;
    }

    @SimpleProperty(description = "Return the description text.")
    public final String Description() {
        return this.BG2b8dRaEUNcWY5vxXAWfSqyg8ZvS4tysLlCvHPUDp1bRFO56FHQ3a9NvDEKgkS;
    }

    @SimpleProperty(description = "Return the title text.")
    public final String Title() {
        return this.n86Bi3TT8lkq4OIAY6TQzz40yEQcROLeML3EOzGKfDOtlcMXWXcB7P2XOFzT0DDv;
    }

    @SimpleProperty(description = "Return the download url.")
    public final String DownloadUrl() {
        return this.gti6bIqu0aXgALoDtkq1foMnPcdzXO0EAPAgw4lcaEF132GirFLns82VqwKc8x6R;
    }

    @SimpleProperty(description = "Return the save file as text.")
    public final String SaveFileAs() {
        return this.YP4juQGK8ZTsNMOy2BKg810SeLJ3amlj2BQC8tc7uqP2LdhyRsu8lUHvdJ0v9u;
    }

    @SimpleProperty(description = "Return the allowed over roaming setting.")
    public final boolean AllowedOverRoaming() {
        return this.tj0MDuMmBVyFcp8vwXpkfd0RnoyqL9aUR0zh2QG1qIbcD4cqzuxOkXiR3Ef5Sjag;
    }

    @SimpleProperty(description = "Return the suppress warnings setting.")
    public final boolean SuppressWarnings() {
        return this.G9FdWvm6zShqpzDC2UOqX6MiQExLolZTefPBjzVvfkE9Kp2rmQld5rkb2wriBYfL;
    }

    @SimpleProperty(description = "Return the show notification setting.")
    public final boolean ShowNotification() {
        return this.IhlDYVsQmgat6F3NXqRok975lHQlAvyJICX3QHDdE383xYIGTapMORiCm1KjyWCi;
    }

    @SimpleProperty(description = "Return the scanning by MediaScanner setting.")
    public final boolean ScanningByMediaScanner() {
        return this.DmQGLROFyZ9Eo0RSsJcpZNxJZjgcsPDfYPi3awNwmyyErT71sGU5mvgG4PDW3yL;
    }

    @SimpleProperty(description = "Return the requires device idle setting.")
    public final boolean RequiresDeviceIdle() {
        return this.vB3WjEtL56PUGm0spJ96S19MI1O4vPR6yju8tUYcKrC4atk0AV5GbVcHQNB7BlIK;
    }

    @SimpleProperty(description = "Return the requires charging setting.")
    public final boolean RequiresCharging() {
        return this.ZXVyhZW2wwbAysjXrMReFP00vcRkftFV6dFiSCOUB0OBlMJVjuhF9XlRGX7w6PdR;
    }

    @SimpleEvent(description = "Event to detect if the download is successful finished. You can use the \"filePath\" to use the downloaded file into your app. The \"fileSize\" will be returned in bytes.")
    public final void DownloadComplete(String str, String str2, int i) {
        Object[] objArr = new Object[3];
        objArr[0] = str;
        Object[] objArr2 = objArr;
        objArr2[1] = str2;
        Object[] objArr3 = objArr2;
        objArr3[2] = Integer.valueOf(i);
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "DownloadComplete", objArr3);
    }

    @SimpleEvent(description = "Event to detect when the user clicks on a running download, either from a system notification or from the downloads UI.")
    public final void NotificationClicked() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "NotificationClicked", new Object[0]);
    }

    @SimpleEvent(description = "Event to detect file size is ready to be used.")
    public final void GotFileSize(int i) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "GotFileSize", Integer.valueOf(i));
    }

    @SimpleFunction(description = "You can open the download folder with this block.")
    public final void ShowDownload() {
        Intent intent;
        new Intent();
        Intent intent2 = intent;
        Intent action = intent2.setAction("android.intent.action.VIEW_DOWNLOADS");
        this.form.startActivity(intent2);
    }

    @SimpleFunction(description = "Get the file size (in bytes) of a file that is stored online or on your device. The block detect automatic if it is a online path or not. You will get the result in the \"Got File Size\" event.")
    public final void GetFileSize(String str) {
        C0638a aVar;
        C0639b bVar;
        String str2 = str;
        if (!str2.startsWith(HttpHost.DEFAULT_SCHEME_NAME)) {
            new C0638a(this, (byte) 0);
            AsyncTask execute = aVar.execute(new String[]{str2});
        } else if (B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T()) {
            new C0639b(this, (byte) 0);
            AsyncTask execute2 = bVar.execute(new String[]{str2});
        } else {
            GotFileSize(0);
        }
    }

    @SimpleFunction(description = "Start the download process of the given download url.")
    public final void Download() {
        PermissionResultHandler permissionResultHandler;
        if (B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T()) {
            new PermissionResultHandler(this) {
                final /* synthetic */ Download hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                }

                public final void HandlePermissionResponse(String str, boolean z) {
                    StringBuilder sb;
                    StringBuilder sb2;
                    DownloadManager.Request request;
                    Runnable runnable;
                    String str2 = str;
                    if (z) {
                        try {
                            new DownloadManager.Request(Uri.parse(Download.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME)));
                            DownloadManager.Request hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = Download.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, request);
                            DownloadManager.Request description = Download.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).setDescription(Download.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME));
                            DownloadManager.Request title = Download.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).setTitle(Download.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME));
                            DownloadManager.Request destinationInExternalPublicDir = Download.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, Download.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME));
                            DownloadManager.Request allowedOverRoaming = Download.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).setAllowedOverRoaming(Download.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME));
                            if (Download.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME)) {
                                DownloadManager.Request notificationVisibility = Download.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).setNotificationVisibility(1);
                            } else {
                                DownloadManager.Request notificationVisibility2 = Download.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).setNotificationVisibility(2);
                            }
                            if (Download.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME)) {
                                Download.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).allowScanningByMediaScanner();
                            }
                            if (Build.VERSION.SDK_INT >= 24) {
                                if (Download.vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME)) {
                                    DownloadManager.Request requiresCharging = Download.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).setRequiresCharging(true);
                                }
                                if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.vB3WjEtL56PUGm0spJ96S19MI1O4vPR6yju8tUYcKrC4atk0AV5GbVcHQNB7BlIK) {
                                    DownloadManager.Request requiresDeviceIdle = Download.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).setRequiresDeviceIdle(true);
                                }
                            }
                            long hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME3 = Download.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, Download.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).enqueue(Download.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME)));
                            new Runnable(this) {
                                final /* synthetic */ C06331 hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                                {
                                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                                }

                                public final void run() {
                                    DownloadManager.Query query;
                                    Runnable runnable;
                                    boolean z = true;
                                    while (z) {
                                        new DownloadManager.Query();
                                        DownloadManager.Query query2 = query;
                                        DownloadManager.Query filterById = query2.setFilterById(new long[]{Download.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME)});
                                        Cursor query3 = Download.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).query(query2);
                                        Cursor cursor = query3;
                                        if (query3 != null && cursor.moveToFirst()) {
                                            Cursor cursor2 = cursor;
                                            int i = cursor2.getInt(cursor2.getColumnIndex("bytes_so_far"));
                                            Cursor cursor3 = cursor;
                                            int i2 = cursor3.getInt(cursor3.getColumnIndex("total_size"));
                                            Cursor cursor4 = cursor;
                                            if (cursor4.getInt(cursor4.getColumnIndex(NotificationCompat.CATEGORY_STATUS)) == 8) {
                                                z = false;
                                            }
                                            final int i3 = (int) ((((long) i) * 100) / ((long) i2));
                                            new Runnable(this) {
                                                private /* synthetic */ C06341 hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                                                {
                                                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
                                                }

                                                public final void run() {
                                                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.DownloadProgress(i3);
                                                }
                                            };
                                            Download.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).runOnUiThread(runnable);
                                            cursor.close();
                                        }
                                    }
                                }
                            };
                            AsynchUtil.runAsynchronously(runnable);
                        } catch (PermissionException e) {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchPermissionDeniedEvent((Component) this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "Download", e);
                        } catch (IllegalStateException e2) {
                            IllegalStateException illegalStateException = e2;
                            if (!this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.G9FdWvm6zShqpzDC2UOqX6MiQExLolZTefPBjzVvfkE9Kp2rmQld5rkb2wriBYfL) {
                                new StringBuilder();
                                int d = Log.d("Download", sb.append(illegalStateException.getMessage()).toString());
                                Context hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME4 = Download.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
                                new StringBuilder();
                                Toast.makeText(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME4, sb2.append(illegalStateException.getMessage()).toString(), 1).show();
                            }
                        }
                    } else {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.dispatchPermissionDeniedEvent((Component) this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "Download", str2);
                    }
                }
            };
            this.form.askPermission("android.permission.WRITE_EXTERNAL_STORAGE", permissionResultHandler);
        }
    }

    @SimpleEvent(description = "Get the progress (in percentage) of the current download task.")
    public final void DownloadProgress(int i) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "DownloadProgress", Integer.valueOf(i));
    }

    private boolean B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T() {
        this.f370hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = (ConnectivityManager) this.context.getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = this.f370hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getActiveNetworkInfo();
        NetworkInfo networkInfo = activeNetworkInfo;
        if (activeNetworkInfo == null || !networkInfo.isConnected()) {
            int d = Log.d("Download", "Network connections is available: false");
            if (!this.G9FdWvm6zShqpzDC2UOqX6MiQExLolZTefPBjzVvfkE9Kp2rmQld5rkb2wriBYfL) {
                int d2 = Log.d("Download", "SuppressWarnings is false. Show now a toast message. Missing internet connection.");
                Toast.makeText(this.context, "Please check your internet connection", 1).show();
            }
            return false;
        }
        int d3 = Log.d("Download", "Network connections is available: true");
        return true;
    }

    /* renamed from: com.google.appinventor.components.runtime.Download$b */
    class C0639b extends AsyncTask<String, Void, Integer> {
        private /* synthetic */ Download hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        private String tj0MDuMmBVyFcp8vwXpkfd0RnoyqL9aUR0zh2QG1qIbcD4cqzuxOkXiR3Ef5Sjag;

        private C0639b(Download download) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = download;
            this.tj0MDuMmBVyFcp8vwXpkfd0RnoyqL9aUR0zh2QG1qIbcD4cqzuxOkXiR3Ef5Sjag = null;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ C0639b(Download download, byte b) {
            this(download);
            byte b2 = b;
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.GotFileSize(((Integer) obj).intValue());
        }

        /* access modifiers changed from: private */
        /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME */
        public Integer doInBackground(String... strArr) {
            URL url;
            this.tj0MDuMmBVyFcp8vwXpkfd0RnoyqL9aUR0zh2QG1qIbcD4cqzuxOkXiR3Ef5Sjag = strArr[0];
            HttpURLConnection httpURLConnection = null;
            try {
                new URL(this.tj0MDuMmBVyFcp8vwXpkfd0RnoyqL9aUR0zh2QG1qIbcD4cqzuxOkXiR3Ef5Sjag);
                HttpURLConnection httpURLConnection2 = (HttpURLConnection) url.openConnection();
                httpURLConnection = httpURLConnection2;
                httpURLConnection2.setRequestMethod(HttpHead.METHOD_NAME);
                InputStream inputStream = httpURLConnection.getInputStream();
                Integer valueOf = Integer.valueOf(httpURLConnection.getContentLength());
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return valueOf;
            } catch (Exception e) {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return null;
            } catch (Throwable th) {
                Throwable th2 = th;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th2;
            }
        }
    }

    /* renamed from: com.google.appinventor.components.runtime.Download$a */
    class C0638a extends AsyncTask<String, Void, Integer> {
        private /* synthetic */ Download hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        private C0638a(Download download) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = download;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ C0638a(Download download, byte b) {
            this(download);
            byte b2 = b;
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            File file;
            new File(((String[]) objArr)[0]);
            File file2 = file;
            File file3 = file2;
            if (file2.exists()) {
                return Integer.valueOf((int) file3.length());
            }
            return null;
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.GotFileSize(((Integer) obj).intValue());
        }
    }
}

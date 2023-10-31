package com.google.appinventor.components.runtime;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.support.p000v4.app.NotificationCompat;
import android.support.p000v4.app.NotificationManagerCompat;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.MediaUtil;
import gnu.expr.Declaration;

@SimpleObject
@DesignerComponent(category = ComponentCategory.EXPERIMENTAL, description = "A component to send a notification to the users device", iconName = "images/notification.png", nonVisible = true, version = 1)
public class MakeroidNotification extends AndroidNonvisibleComponent {
    private String P89mqD3TKqF18ygPOurbjck3EPcqxgmZ649A3XXayOkNUpvgMJ4Q1cnkfpD040KQ = "MakeroidNotification";
    private ComponentContainer container;
    private Context context;
    private int dTbjShrSvDZnDCeVo9i3X83sAePZ6DkuyHJPQ6B7WbRWcPLJlbxhZYnAC0mU9DUR;
    private NotificationManagerCompat hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private int o4oEL8NAO5pXZcOSBAknA2NvsNjJJvehWW0lJ74yBWMu5XdCwoMznIBTr1ZGCAX;
    private int xaven5ecM0Ll2H1GHPRV82tONpToW8URGRDgxbH0b2M0q5wAsNNBDrpsKvL0qvHt;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MakeroidNotification(com.google.appinventor.components.runtime.ComponentContainer r5) {
        /*
            r4 = this;
            r0 = r4
            r1 = r5
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            java.lang.String r3 = "MakeroidNotification"
            r2.P89mqD3TKqF18ygPOurbjck3EPcqxgmZ649A3XXayOkNUpvgMJ4Q1cnkfpD040KQ = r3
            r2 = r0
            r3 = r1
            r2.container = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            r2 = r0
            r3 = 1
            r2.Priority(r3)
            r2 = r0
            r3 = 1
            r2.Visiblity(r3)
            r2 = r0
            r2.vOibdtOfNxMVixWab9VDGgvD9L4Kqb2ZDQBius3PMdhiP1dPN68Z9GzWJA49TUbE()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.MakeroidNotification.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @DesignerProperty(defaultValue = "1", editorType = "notification_priority")
    @SimpleProperty(description = "Set the priority level. 1 = \"Default\", 2 = \"high\", 3 = \"low\", 4 = \"max\", 5 = \"min\"")
    public void Priority(int i) {
        switch (i) {
            case 2:
                this.o4oEL8NAO5pXZcOSBAknA2NvsNjJJvehWW0lJ74yBWMu5XdCwoMznIBTr1ZGCAX = 1;
                if (Build.VERSION.SDK_INT >= 24) {
                    this.xaven5ecM0Ll2H1GHPRV82tONpToW8URGRDgxbH0b2M0q5wAsNNBDrpsKvL0qvHt = 4;
                    break;
                }
                break;
            case 3:
                this.o4oEL8NAO5pXZcOSBAknA2NvsNjJJvehWW0lJ74yBWMu5XdCwoMznIBTr1ZGCAX = -1;
                if (Build.VERSION.SDK_INT >= 24) {
                    this.xaven5ecM0Ll2H1GHPRV82tONpToW8URGRDgxbH0b2M0q5wAsNNBDrpsKvL0qvHt = 2;
                    break;
                }
                break;
            case 4:
                this.o4oEL8NAO5pXZcOSBAknA2NvsNjJJvehWW0lJ74yBWMu5XdCwoMznIBTr1ZGCAX = 2;
                if (Build.VERSION.SDK_INT >= 24) {
                    this.xaven5ecM0Ll2H1GHPRV82tONpToW8URGRDgxbH0b2M0q5wAsNNBDrpsKvL0qvHt = 5;
                    break;
                }
                break;
            case 5:
                this.o4oEL8NAO5pXZcOSBAknA2NvsNjJJvehWW0lJ74yBWMu5XdCwoMznIBTr1ZGCAX = -2;
                if (Build.VERSION.SDK_INT >= 24) {
                    this.xaven5ecM0Ll2H1GHPRV82tONpToW8URGRDgxbH0b2M0q5wAsNNBDrpsKvL0qvHt = 1;
                    break;
                }
                break;
            default:
                this.o4oEL8NAO5pXZcOSBAknA2NvsNjJJvehWW0lJ74yBWMu5XdCwoMznIBTr1ZGCAX = 0;
                if (Build.VERSION.SDK_INT >= 24) {
                    this.xaven5ecM0Ll2H1GHPRV82tONpToW8URGRDgxbH0b2M0q5wAsNNBDrpsKvL0qvHt = 3;
                    break;
                }
                break;
        }
        int i2 = Log.i("MakeroidNotification", "Notification Priority set");
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The Priority of the notification")
    public int Priority() {
        int i = Log.i("MakeroidNotification", "Notification Priority returned");
        return this.o4oEL8NAO5pXZcOSBAknA2NvsNjJJvehWW0lJ74yBWMu5XdCwoMznIBTr1ZGCAX;
    }

    @DesignerProperty(defaultValue = "1", editorType = "notification_visibility")
    @SimpleProperty(description = "Set the visibility property. 1 = \"public\", 2 = \"private\", 3 = \"secret\".")
    public void Visiblity(int i) {
        switch (i) {
            case 2:
                this.dTbjShrSvDZnDCeVo9i3X83sAePZ6DkuyHJPQ6B7WbRWcPLJlbxhZYnAC0mU9DUR = 0;
                break;
            case 3:
                this.dTbjShrSvDZnDCeVo9i3X83sAePZ6DkuyHJPQ6B7WbRWcPLJlbxhZYnAC0mU9DUR = -1;
                break;
            default:
                this.dTbjShrSvDZnDCeVo9i3X83sAePZ6DkuyHJPQ6B7WbRWcPLJlbxhZYnAC0mU9DUR = 1;
                break;
        }
        int i2 = Log.i("MakeroidNotification", "Notification Visibility set");
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The Visibility of the notification")
    public int Visiblity() {
        int i = Log.i("MakeroidNotification", "Notification Visibility returned");
        return this.dTbjShrSvDZnDCeVo9i3X83sAePZ6DkuyHJPQ6B7WbRWcPLJlbxhZYnAC0mU9DUR;
    }

    @SimpleFunction(description = "Send a simple notification")
    public void SimpleNotification(String str, String str2) {
        NotificationCompat.Builder builder;
        NotificationCompat.BigTextStyle bigTextStyle;
        String str3 = str2;
        new NotificationCompat.Builder(this.context, this.P89mqD3TKqF18ygPOurbjck3EPcqxgmZ649A3XXayOkNUpvgMJ4Q1cnkfpD040KQ);
        NotificationCompat.Builder contentText = builder.setSmallIcon(17301659).setContentTitle(str).setContentText(str3);
        new NotificationCompat.BigTextStyle();
        NotificationCompat.Builder contentIntent = contentText.setStyle(bigTextStyle.bigText(str3)).setPriority(this.o4oEL8NAO5pXZcOSBAknA2NvsNjJJvehWW0lJ74yBWMu5XdCwoMznIBTr1ZGCAX).setVisibility(this.dTbjShrSvDZnDCeVo9i3X83sAePZ6DkuyHJPQ6B7WbRWcPLJlbxhZYnAC0mU9DUR).setAutoCancel(true).setContentIntent(PendingIntent.getActivity(this.context, 0, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(""), 0));
        vOibdtOfNxMVixWab9VDGgvD9L4Kqb2ZDQBius3PMdhiP1dPN68Z9GzWJA49TUbE();
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.notify((int) System.currentTimeMillis(), contentIntent.build());
        int i = Log.i("MakeroidNotification", "Notification Send");
    }

    @SimpleFunction(description = "Send a normal notification")
    public void NormalNotification(int i, String str, String str2, String str3, boolean z) {
        NotificationCompat.Builder builder;
        NotificationCompat.BigTextStyle bigTextStyle;
        String str4 = str2;
        new NotificationCompat.Builder(this.context, this.P89mqD3TKqF18ygPOurbjck3EPcqxgmZ649A3XXayOkNUpvgMJ4Q1cnkfpD040KQ);
        NotificationCompat.Builder contentText = builder.setSmallIcon(17301659).setContentTitle(str).setContentText(str4);
        new NotificationCompat.BigTextStyle();
        NotificationCompat.Builder contentIntent = contentText.setStyle(bigTextStyle.bigText(str4)).setPriority(this.o4oEL8NAO5pXZcOSBAknA2NvsNjJJvehWW0lJ74yBWMu5XdCwoMznIBTr1ZGCAX).setVisibility(this.dTbjShrSvDZnDCeVo9i3X83sAePZ6DkuyHJPQ6B7WbRWcPLJlbxhZYnAC0mU9DUR).setAutoCancel(z).setContentIntent(PendingIntent.getActivity(this.context, 0, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str3), 0));
        vOibdtOfNxMVixWab9VDGgvD9L4Kqb2ZDQBius3PMdhiP1dPN68Z9GzWJA49TUbE();
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.notify(i, contentIntent.build());
        int i2 = Log.i("MakeroidNotification", "Notification Send");
    }

    @SimpleFunction(description = "Send a normal notification")
    public void BigPictureNotification(int i, String str, String str2, String str3, String str4, boolean z) {
        NotificationCompat.Builder builder;
        NotificationCompat.BigPictureStyle bigPictureStyle;
        int i2 = i;
        String str5 = str;
        String str6 = str2;
        String str7 = str4;
        boolean z2 = z;
        Bitmap bitmap = null;
        try {
            bitmap = MediaUtil.getBitmapDrawable(this.container.$form(), str3).getBitmap();
        } catch (Exception e) {
            int e2 = Log.e("MakeroidNotification", String.valueOf(e));
        }
        new NotificationCompat.Builder(this.context, this.P89mqD3TKqF18ygPOurbjck3EPcqxgmZ649A3XXayOkNUpvgMJ4Q1cnkfpD040KQ);
        NotificationCompat.Builder contentText = builder.setSmallIcon(17301659).setContentTitle(str5).setContentText(str6);
        new NotificationCompat.BigPictureStyle();
        NotificationCompat.Builder contentIntent = contentText.setStyle(bigPictureStyle.bigPicture(bitmap).bigLargeIcon((Bitmap) null)).setLargeIcon(bitmap).setPriority(this.o4oEL8NAO5pXZcOSBAknA2NvsNjJJvehWW0lJ74yBWMu5XdCwoMznIBTr1ZGCAX).setVisibility(this.dTbjShrSvDZnDCeVo9i3X83sAePZ6DkuyHJPQ6B7WbRWcPLJlbxhZYnAC0mU9DUR).setAutoCancel(z2).setContentIntent(PendingIntent.getActivity(this.context, 0, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str7), 0));
        vOibdtOfNxMVixWab9VDGgvD9L4Kqb2ZDQBius3PMdhiP1dPN68Z9GzWJA49TUbE();
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.notify(i2, contentIntent.build());
        int i3 = Log.i("MakeroidNotification", "Notification Send");
    }

    public static void ScreenshotNotification(Context context2, String str, String str2, String str3, Uri uri, boolean z, boolean z2, Bitmap bitmap) {
        Intent intent;
        NotificationCompat.Builder builder;
        NotificationChannel notificationChannel;
        Intent intent2;
        NotificationCompat.BigPictureStyle bigPictureStyle;
        Context context3 = context2;
        String str4 = str3;
        Uri uri2 = uri;
        boolean z3 = z;
        Bitmap bitmap2 = bitmap;
        String str5 = "MakeroidScreenshot";
        int currentTimeMillis = (int) System.currentTimeMillis();
        new Intent();
        Intent intent3 = intent;
        Intent intent4 = intent3;
        Intent action = intent3.setAction("android.intent.action.VIEW");
        Intent dataAndType = intent4.setDataAndType(uri2, "image/*");
        new NotificationCompat.Builder(context3, str5);
        NotificationCompat.Builder autoCancel = builder.setContentTitle(str).setContentText(str2).setSmallIcon(17301567).setContentIntent(PendingIntent.getActivity(context3, 0, intent4, 0)).setPriority(0).setAutoCancel(true);
        if (z2) {
            new NotificationCompat.BigPictureStyle();
            NotificationCompat.Builder style = autoCancel.setStyle(bigPictureStyle.bigPicture(bitmap2));
        }
        if (z3) {
            new Intent();
            Intent intent5 = intent2;
            Intent intent6 = intent5;
            Intent action2 = intent5.setAction("android.intent.action.SEND");
            Intent putExtra = intent6.putExtra("android.intent.extra.STREAM", uri2);
            Intent putExtra2 = intent6.putExtra("EXTRA_DETAILS_ID", currentTimeMillis);
            Intent type = intent6.setType("image/*");
            NotificationCompat.Builder addAction = autoCancel.addAction(17301586, str4, PendingIntent.getActivity(context3, 0, intent6, Declaration.IS_DYNAMIC));
        }
        if (Build.VERSION.SDK_INT >= 26) {
            new NotificationChannel(str5, "Makeroid", 3);
            NotificationChannel notificationChannel2 = notificationChannel;
            notificationChannel2.setDescription("Makeroid Notification Channel");
            ((NotificationManager) context3.getSystemService(NotificationManager.class)).createNotificationChannel(notificationChannel2);
        }
        NotificationManagerCompat.from(context3).notify(currentTimeMillis, autoCancel.build());
        int i = Log.i("MakeroidNotification", "Notification Send");
    }

    @SimpleFunction(description = "Check wether the app can send Notifications")
    public boolean AreNotificationsEnabled() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.areNotificationsEnabled();
    }

    @SimpleFunction(description = "Cancel all Notifications")
    public void CancelAll() {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.cancelAll();
        int i = Log.i("MakeroidNotification", "All Notifications Cancelled");
    }

    @SimpleFunction(description = "Cancel a Notification with an id")
    public void CancelNotification(int i) {
        StringBuilder sb;
        int i2 = i;
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.cancel(i2);
        new StringBuilder("Notification with id ");
        int i3 = Log.i("MakeroidNotification", sb.append(i2).append(" canceled").toString());
    }

    private Intent hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str) {
        String str2 = str;
        Intent launchIntentForPackage = this.context.getPackageManager().getLaunchIntentForPackage(this.context.getPackageName());
        Intent intent = launchIntentForPackage;
        if (launchIntentForPackage != null) {
            Intent putExtra = intent.putExtra("APP_INVENTOR_START", str2);
        }
        return intent;
    }

    private void vOibdtOfNxMVixWab9VDGgvD9L4Kqb2ZDQBius3PMdhiP1dPN68Z9GzWJA49TUbE() {
        NotificationChannel notificationChannel;
        if (Build.VERSION.SDK_INT >= 26) {
            new NotificationChannel(this.P89mqD3TKqF18ygPOurbjck3EPcqxgmZ649A3XXayOkNUpvgMJ4Q1cnkfpD040KQ, "Makeroid", this.xaven5ecM0Ll2H1GHPRV82tONpToW8URGRDgxbH0b2M0q5wAsNNBDrpsKvL0qvHt);
            NotificationChannel notificationChannel2 = notificationChannel;
            notificationChannel2.setDescription("Makeroid Notification Channel");
            ((NotificationManager) this.context.getSystemService(NotificationManager.class)).createNotificationChannel(notificationChannel2);
        }
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = NotificationManagerCompat.from(this.context);
    }
}

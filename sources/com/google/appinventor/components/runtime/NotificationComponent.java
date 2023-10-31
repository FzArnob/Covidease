package com.google.appinventor.components.runtime;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
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

@SimpleObject
@DesignerComponent(category = ComponentCategory.INTERNAL, description = "A component to send a notification to the users device", iconName = "images/notification.png", nonVisible = true, version = 1)
public final class NotificationComponent extends AndroidNonvisibleComponent {
    private int YY8QFJ7NsKl2krKlLP4gKRTKnpLlHQvVopkx7p60xy1hzICdxizXFIQJXbKtydSp;
    private int ZjHRxIxmIbXMaaxTq0tXk7fANzHmvqKL8qVP5oGbbjVmTEOJkL3QkM6pcvCB7aNQ = 0;
    private ComponentContainer container;
    private Context context;
    private NotificationCompat.Builder hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private NotificationManagerCompat f511hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private int o4oEL8NAO5pXZcOSBAknA2NvsNjJJvehWW0lJ74yBWMu5XdCwoMznIBTr1ZGCAX;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public NotificationComponent(com.google.appinventor.components.runtime.ComponentContainer r6) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            r3 = 0
            r2.ZjHRxIxmIbXMaaxTq0tXk7fANzHmvqKL8qVP5oGbbjVmTEOJkL3QkM6pcvCB7aNQ = r3
            r2 = r0
            r3 = r1
            r2.container = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            r2 = r0
            r4 = r2
            r2 = r4
            r3 = r4
            android.content.Context r3 = r3.context
            android.support.v4.app.NotificationManagerCompat r3 = android.support.p000v4.app.NotificationManagerCompat.from(r3)
            r2.f511hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.NotificationComponent.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleFunction(description = "Send a notification")
    public final void SendNotification(String str, String str2) {
        NotificationCompat.Builder builder;
        new NotificationCompat.Builder(this.context, String.valueOf(System.currentTimeMillis()));
        this.f511hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.notify(0, builder.setChannelId(String.valueOf(System.currentTimeMillis())).setContentTitle(str).setContentText(str2).setSmallIcon(17301659).setPriority(this.o4oEL8NAO5pXZcOSBAknA2NvsNjJJvehWW0lJ74yBWMu5XdCwoMznIBTr1ZGCAX).setVisibility(this.YY8QFJ7NsKl2krKlLP4gKRTKnpLlHQvVopkx7p60xy1hzICdxizXFIQJXbKtydSp).setAutoCancel(true).setContentIntent(PendingIntent.getActivity(this.context, (int) System.currentTimeMillis(), hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(""), 0)).build());
        int i = Log.i("NotificationComponent", "Notification Send");
    }

    @DesignerProperty(defaultValue = "1", editorType = "notification_priority")
    @SimpleProperty(description = "Set the priority level. 1 = \"Default\", 2 = \"high\", 3 = \"low\", 4 = \"max\", 5 = \"min\"")
    public final void Priority(int i) {
        switch (i) {
            case 1:
                this.o4oEL8NAO5pXZcOSBAknA2NvsNjJJvehWW0lJ74yBWMu5XdCwoMznIBTr1ZGCAX = 0;
                break;
            case 2:
                this.o4oEL8NAO5pXZcOSBAknA2NvsNjJJvehWW0lJ74yBWMu5XdCwoMznIBTr1ZGCAX = 1;
                break;
            case 3:
                this.o4oEL8NAO5pXZcOSBAknA2NvsNjJJvehWW0lJ74yBWMu5XdCwoMznIBTr1ZGCAX = -1;
                break;
            case 4:
                this.o4oEL8NAO5pXZcOSBAknA2NvsNjJJvehWW0lJ74yBWMu5XdCwoMznIBTr1ZGCAX = 2;
                break;
            case 5:
                this.o4oEL8NAO5pXZcOSBAknA2NvsNjJJvehWW0lJ74yBWMu5XdCwoMznIBTr1ZGCAX = -2;
                break;
            default:
                this.o4oEL8NAO5pXZcOSBAknA2NvsNjJJvehWW0lJ74yBWMu5XdCwoMznIBTr1ZGCAX = 0;
                break;
        }
        int i2 = Log.i("NotificationComponent", "Notification Priority set");
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The Priority of the notification")
    public final int Priority() {
        int i = Log.i("NotificationComponent", "Notification Priority returned");
        return this.o4oEL8NAO5pXZcOSBAknA2NvsNjJJvehWW0lJ74yBWMu5XdCwoMznIBTr1ZGCAX;
    }

    @DesignerProperty(defaultValue = "1", editorType = "notification_visibility")
    @SimpleProperty(description = "Set the visibility property. 1 = \"public\", 2 = \"private\", 3 = \"secret\".")
    public final void Visiblity(int i) {
        switch (i) {
            case 1:
                this.YY8QFJ7NsKl2krKlLP4gKRTKnpLlHQvVopkx7p60xy1hzICdxizXFIQJXbKtydSp = 1;
                break;
            case 2:
                this.YY8QFJ7NsKl2krKlLP4gKRTKnpLlHQvVopkx7p60xy1hzICdxizXFIQJXbKtydSp = 0;
                break;
            case 3:
                this.YY8QFJ7NsKl2krKlLP4gKRTKnpLlHQvVopkx7p60xy1hzICdxizXFIQJXbKtydSp = -1;
                break;
            default:
                this.YY8QFJ7NsKl2krKlLP4gKRTKnpLlHQvVopkx7p60xy1hzICdxizXFIQJXbKtydSp = 1;
                break;
        }
        int i2 = Log.i("NotificationComponent", "Notification Visibity set");
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The Visiblity of the notification")
    public final int Visiblity() {
        int i = Log.i("NotificationComponent", "Notification Visibity returned");
        return this.YY8QFJ7NsKl2krKlLP4gKRTKnpLlHQvVopkx7p60xy1hzICdxizXFIQJXbKtydSp;
    }

    @SimpleFunction(description = "Send a advanced notification (Android Wear Support)")
    public final void SendAdvancedNotification(int i, String str, String str2, String str3, int i2, int i3) {
        NotificationCompat.Builder builder;
        int i4 = i2;
        int i5 = i3;
        new NotificationCompat.Builder(this.context, String.valueOf(System.currentTimeMillis()));
        this.f511hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.notify(i, builder.setContentTitle(str).setContentText(str2).setSmallIcon(17301659).setPriority(this.o4oEL8NAO5pXZcOSBAknA2NvsNjJJvehWW0lJ74yBWMu5XdCwoMznIBTr1ZGCAX).setVisibility(this.YY8QFJ7NsKl2krKlLP4gKRTKnpLlHQvVopkx7p60xy1hzICdxizXFIQJXbKtydSp).setAutoCancel(true).setContentIntent(PendingIntent.getActivity(this.context, (int) System.currentTimeMillis(), hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str3), 0)).build());
        int i6 = Log.i("NotificationComponent", "Advanced Notification Send");
    }

    @SimpleFunction(description = "Check whether the app can send Notifications")
    public final boolean AreNotificationsEnabled() {
        return this.f511hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.areNotificationsEnabled();
    }

    @SimpleFunction(description = "Cancel all Notifications")
    public final void CancelAll() {
        this.f511hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.cancelAll();
        int i = Log.i("NotificationComponent", "All Notifications Cancelled");
    }

    @SimpleFunction(description = "Cancel a Notification with an id")
    public final void CancelNotification(int i) {
        StringBuilder sb;
        int i2 = i;
        this.f511hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.cancel(i2);
        new StringBuilder("Notification with id ");
        int i3 = Log.i("NotificationComponent", sb.append(i2).append(" canceled").toString());
    }

    @SimpleFunction(description = "Start building a new notification")
    public final void NotificationBuilderStart(String str, String str2) {
        NotificationCompat.Builder builder;
        this.ZjHRxIxmIbXMaaxTq0tXk7fANzHmvqKL8qVP5oGbbjVmTEOJkL3QkM6pcvCB7aNQ = 0;
        new NotificationCompat.Builder(this.context, String.valueOf(System.currentTimeMillis()));
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = builder.setContentTitle(str).setContentText(str2).setSmallIcon(17301659).setAutoCancel(true).setContentIntent(PendingIntent.getActivity(this.context, (int) System.currentTimeMillis(), hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(""), 0));
        int i = Log.i("NotificationComponent", "Notification Building Started");
    }

    @SimpleFunction(description = "Set the notification id.")
    public final void NotificationBuilderID(int i) {
        int i2 = i;
        this.ZjHRxIxmIbXMaaxTq0tXk7fANzHmvqKL8qVP5oGbbjVmTEOJkL3QkM6pcvCB7aNQ = i2;
        int i3 = Log.i("NotificationComponent", "Notification ID Added");
    }

    @SimpleFunction(description = "Set the start value of the notification.")
    public final void NotificationBuilderStartValue(String str) {
        NotificationCompat.Builder contentIntent = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setContentIntent(PendingIntent.getActivity(this.context, (int) System.currentTimeMillis(), hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str), 0));
        int i = Log.i("NotificationComponent", "Notification Start Value Added");
    }

    @SimpleFunction(description = "Set whether the notification should me removed after the user clicks on it.")
    public final void NotificationBuilderAutoCancel(boolean z) {
        NotificationCompat.Builder autoCancel = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setAutoCancel(z);
        int i = Log.i("NotificationComponent", "Notification Auto Cancel Added");
    }

    @SimpleFunction(description = "Set the big picture for the notification")
    public final void NotificationBuilderBigPicture(String str) {
        NotificationCompat.BigPictureStyle bigPictureStyle;
        String str2 = str;
        try {
            Bitmap bitmap = MediaUtil.getBitmapDrawable(this.container.$form(), str2 == null ? "" : str2).getBitmap();
            NotificationCompat.Builder builder = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            new NotificationCompat.BigPictureStyle();
            NotificationCompat.Builder style = builder.setStyle(bigPictureStyle.bigPicture(bitmap));
            int i = Log.i("NotificationComponent", "Notifications Big Picture Added");
        } catch (Exception e) {
            int e2 = Log.e("Notification IOException", String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Set the background image for Android Wear devices")
    public final void NotificationBuilderAndroidWear(String str) {
        NotificationCompat.WearableExtender wearableExtender;
        String str2 = str;
        try {
            Bitmap bitmap = MediaUtil.getBitmapDrawable(this.container.$form(), str2 == null ? "" : str2).getBitmap();
            NotificationCompat.Builder builder = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            new NotificationCompat.WearableExtender();
            NotificationCompat.Builder extend = builder.extend(wearableExtender.setBackground(bitmap));
            int i = Log.i("NotificationComponent", "Android Wear Background Image Added");
        } catch (Exception e) {
            int e2 = Log.e("Notification IOException", String.valueOf(e));
        }
    }

    @SimpleFunction(description = "Set the accent color for Android Car devices")
    public final void NotificationBuilderAndroidCar(int i) {
        NotificationCompat.CarExtender carExtender;
        NotificationCompat.Builder builder = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        new NotificationCompat.CarExtender();
        NotificationCompat.Builder extend = builder.extend(carExtender.setColor(i));
        int i2 = Log.i("NotificationComponent", "Android Car Accent Color Added");
    }

    @SimpleFunction(description = "Set the notification builder priority. 1 = \"Default\", 2 = \"high\", 3 = \"low\", 4 = \"max\", 5 = \"min\"")
    public final void NotificationBuilderPriority(int i) {
        int i2;
        switch (i) {
            case 1:
                i2 = 0;
                break;
            case 2:
                i2 = 1;
                break;
            case 3:
                i2 = -1;
                break;
            case 4:
                i2 = 2;
                break;
            case 5:
                i2 = -2;
                break;
            default:
                i2 = 0;
                break;
        }
        NotificationCompat.Builder priority = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setPriority(i2);
        int i3 = Log.i("NotificationComponent", "Notification Priority Added");
    }

    @SimpleFunction(description = "Set the notification builder visibility. 1 = \"public\", 2 = \"private\", 3 = \"secret\".")
    public final void NotificationBuilderVisibility(int i) {
        int i2;
        switch (i) {
            case 1:
                i2 = 1;
                break;
            case 2:
                i2 = 0;
                break;
            case 3:
                i2 = -1;
                break;
            default:
                i2 = 1;
                break;
        }
        NotificationCompat.Builder visibility = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setVisibility(i2);
        int i3 = Log.i("NotificationComponent", "Notification Visiblity Added");
    }

    @SimpleFunction(description = "Send the notification that you build")
    public final void NotificationBuilderSend() {
        this.f511hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.notify(this.ZjHRxIxmIbXMaaxTq0tXk7fANzHmvqKL8qVP5oGbbjVmTEOJkL3QkM6pcvCB7aNQ, this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.build());
        int i = Log.i("NotificationComponent", "Notification Send");
    }

    private Intent hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str) {
        Intent launchIntentForPackage = this.context.getPackageManager().getLaunchIntentForPackage(this.context.getPackageName());
        Intent putExtra = launchIntentForPackage.putExtra("APP_INVENTOR_START", str);
        return launchIntentForPackage;
    }
}

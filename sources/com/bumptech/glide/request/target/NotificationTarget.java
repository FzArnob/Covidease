package com.bumptech.glide.request.target;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.RemoteViews;
import com.bumptech.glide.request.animation.GlideAnimation;

public class NotificationTarget extends SimpleTarget<Bitmap> {
    private final Context context;
    private final Notification notification;
    private final int notificationId;
    private final RemoteViews remoteViews;
    private final int viewId;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public NotificationTarget(Context context2, RemoteViews remoteViews2, int viewId2, Notification notification2, int notificationId2) {
        this(context2, remoteViews2, viewId2, Integer.MIN_VALUE, Integer.MIN_VALUE, notification2, notificationId2);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NotificationTarget(Context context2, RemoteViews remoteViews2, int i, int width, int height, Notification notification2, int i2) {
        super(width, height);
        Throwable th;
        Throwable th2;
        Throwable th3;
        Context context3 = context2;
        RemoteViews remoteViews3 = remoteViews2;
        int viewId2 = i;
        Notification notification3 = notification2;
        int notificationId2 = i2;
        if (context3 == null) {
            Throwable th4 = th3;
            new NullPointerException("Context must not be null!");
            throw th4;
        } else if (notification3 == null) {
            Throwable th5 = th2;
            new NullPointerException("Notification object can not be null!");
            throw th5;
        } else if (remoteViews3 == null) {
            Throwable th6 = th;
            new NullPointerException("RemoteViews object can not be null!");
            throw th6;
        } else {
            this.context = context3;
            this.viewId = viewId2;
            this.notification = notification3;
            this.notificationId = notificationId2;
            this.remoteViews = remoteViews3;
        }
    }

    private void update() {
        ((NotificationManager) this.context.getSystemService("notification")).notify(this.notificationId, this.notification);
    }

    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
        GlideAnimation<? super Bitmap> glideAnimation2 = glideAnimation;
        this.remoteViews.setImageViewBitmap(this.viewId, resource);
        update();
    }
}

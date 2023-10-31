package com.bumptech.glide.request.target;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.RemoteViews;
import com.bumptech.glide.request.animation.GlideAnimation;

public class AppWidgetTarget extends SimpleTarget<Bitmap> {
    private final ComponentName componentName;
    private final Context context;
    private final RemoteViews remoteViews;
    private final int viewId;
    private final int[] widgetIds;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AppWidgetTarget(Context context2, RemoteViews remoteViews2, int i, int width, int height, int... iArr) {
        super(width, height);
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Context context3 = context2;
        RemoteViews remoteViews3 = remoteViews2;
        int viewId2 = i;
        int[] widgetIds2 = iArr;
        if (context3 == null) {
            Throwable th5 = th4;
            new NullPointerException("Context can not be null!");
            throw th5;
        } else if (widgetIds2 == null) {
            Throwable th6 = th3;
            new NullPointerException("WidgetIds can not be null!");
            throw th6;
        } else if (widgetIds2.length == 0) {
            Throwable th7 = th2;
            new IllegalArgumentException("WidgetIds must have length > 0");
            throw th7;
        } else if (remoteViews3 == null) {
            Throwable th8 = th;
            new NullPointerException("RemoteViews object can not be null!");
            throw th8;
        } else {
            this.context = context3;
            this.remoteViews = remoteViews3;
            this.viewId = viewId2;
            this.widgetIds = widgetIds2;
            this.componentName = null;
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public AppWidgetTarget(Context context2, RemoteViews remoteViews2, int viewId2, int... widgetIds2) {
        this(context2, remoteViews2, viewId2, Integer.MIN_VALUE, Integer.MIN_VALUE, widgetIds2);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AppWidgetTarget(Context context2, RemoteViews remoteViews2, int i, int width, int height, ComponentName componentName2) {
        super(width, height);
        Throwable th;
        Throwable th2;
        Throwable th3;
        Context context3 = context2;
        RemoteViews remoteViews3 = remoteViews2;
        int viewId2 = i;
        ComponentName componentName3 = componentName2;
        if (context3 == null) {
            Throwable th4 = th3;
            new NullPointerException("Context can not be null!");
            throw th4;
        } else if (componentName3 == null) {
            Throwable th5 = th2;
            new NullPointerException("ComponentName can not be null!");
            throw th5;
        } else if (remoteViews3 == null) {
            Throwable th6 = th;
            new NullPointerException("RemoteViews object can not be null!");
            throw th6;
        } else {
            this.context = context3;
            this.remoteViews = remoteViews3;
            this.viewId = viewId2;
            this.componentName = componentName3;
            this.widgetIds = null;
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public AppWidgetTarget(Context context2, RemoteViews remoteViews2, int viewId2, ComponentName componentName2) {
        this(context2, remoteViews2, viewId2, Integer.MIN_VALUE, Integer.MIN_VALUE, componentName2);
    }

    private void update() {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this.context);
        if (this.componentName != null) {
            appWidgetManager.updateAppWidget(this.componentName, this.remoteViews);
        } else {
            appWidgetManager.updateAppWidget(this.widgetIds, this.remoteViews);
        }
    }

    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
        GlideAnimation<? super Bitmap> glideAnimation2 = glideAnimation;
        this.remoteViews.setImageViewBitmap(this.viewId, resource);
        update();
    }
}

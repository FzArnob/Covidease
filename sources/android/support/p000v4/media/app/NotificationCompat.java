package android.support.p000v4.media.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.media.session.MediaSession;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.mediacompat.C0173R;
import android.support.p000v4.app.BundleCompat;
import android.support.p000v4.app.NotificationBuilderWithBuilderAccessor;
import android.support.p000v4.app.NotificationCompat;
import android.support.p000v4.media.session.MediaSessionCompat;
import android.widget.RemoteViews;

/* renamed from: android.support.v4.media.app.NotificationCompat */
public class NotificationCompat {
    private NotificationCompat() {
    }

    /* renamed from: android.support.v4.media.app.NotificationCompat$MediaStyle */
    public static class MediaStyle extends NotificationCompat.Style {
        private static final int MAX_MEDIA_BUTTONS = 5;
        private static final int MAX_MEDIA_BUTTONS_IN_COMPACT = 3;
        int[] mActionsToShowInCompact = null;
        PendingIntent mCancelButtonIntent;
        boolean mShowCancelButton;
        MediaSessionCompat.Token mToken;

        public static MediaSessionCompat.Token getMediaSession(Notification notification) {
            Bundle extras = android.support.p000v4.app.NotificationCompat.getExtras(notification);
            if (extras != null) {
                if (Build.VERSION.SDK_INT >= 21) {
                    Parcelable tokenInner = extras.getParcelable(android.support.p000v4.app.NotificationCompat.EXTRA_MEDIA_SESSION);
                    if (tokenInner != null) {
                        return MediaSessionCompat.Token.fromToken(tokenInner);
                    }
                } else {
                    IBinder tokenInner2 = BundleCompat.getBinder(extras, android.support.p000v4.app.NotificationCompat.EXTRA_MEDIA_SESSION);
                    if (tokenInner2 != null) {
                        Parcel p = Parcel.obtain();
                        p.writeStrongBinder(tokenInner2);
                        p.setDataPosition(0);
                        MediaSessionCompat.Token token = MediaSessionCompat.Token.CREATOR.createFromParcel(p);
                        p.recycle();
                        return token;
                    }
                }
            }
            return null;
        }

        public MediaStyle() {
        }

        public MediaStyle(NotificationCompat.Builder builder) {
            setBuilder(builder);
        }

        public MediaStyle setShowActionsInCompactView(int... actions) {
            this.mActionsToShowInCompact = actions;
            return this;
        }

        public MediaStyle setMediaSession(MediaSessionCompat.Token token) {
            this.mToken = token;
            return this;
        }

        public MediaStyle setShowCancelButton(boolean z) {
            boolean show = z;
            if (Build.VERSION.SDK_INT < 21) {
                this.mShowCancelButton = show;
            }
            return this;
        }

        public MediaStyle setCancelButtonIntent(PendingIntent pendingIntent) {
            this.mCancelButtonIntent = pendingIntent;
            return this;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            Notification.MediaStyle mediaStyle;
            NotificationBuilderWithBuilderAccessor builder = notificationBuilderWithBuilderAccessor;
            if (Build.VERSION.SDK_INT >= 21) {
                Notification.Builder builder2 = builder.getBuilder();
                new Notification.MediaStyle();
                Notification.Builder style = builder2.setStyle(fillInMediaStyle(mediaStyle));
            } else if (this.mShowCancelButton) {
                Notification.Builder ongoing = builder.getBuilder().setOngoing(true);
            }
        }

        /* access modifiers changed from: package-private */
        @RequiresApi(21)
        public Notification.MediaStyle fillInMediaStyle(Notification.MediaStyle mediaStyle) {
            Notification.MediaStyle style = mediaStyle;
            if (this.mActionsToShowInCompact != null) {
                Notification.MediaStyle showActionsInCompactView = style.setShowActionsInCompactView(this.mActionsToShowInCompact);
            }
            if (this.mToken != null) {
                Notification.MediaStyle mediaSession = style.setMediaSession((MediaSession.Token) this.mToken.getToken());
            }
            return style;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor2 = notificationBuilderWithBuilderAccessor;
            if (Build.VERSION.SDK_INT >= 21) {
                return null;
            }
            return generateContentView();
        }

        /* access modifiers changed from: package-private */
        public RemoteViews generateContentView() {
            int min;
            Throwable th;
            RemoteViews view = applyStandardTemplate(false, getContentViewLayoutResource(), true);
            int numActions = this.mBuilder.mActions.size();
            if (this.mActionsToShowInCompact == null) {
                min = 0;
            } else {
                min = Math.min(this.mActionsToShowInCompact.length, 3);
            }
            int numActionsInCompact = min;
            view.removeAllViews(C0173R.C0175id.media_actions);
            if (numActionsInCompact > 0) {
                for (int i = 0; i < numActionsInCompact; i++) {
                    if (i >= numActions) {
                        Throwable th2 = th;
                        Object[] objArr = new Object[2];
                        objArr[0] = Integer.valueOf(i);
                        Object[] objArr2 = objArr;
                        objArr2[1] = Integer.valueOf(numActions - 1);
                        new IllegalArgumentException(String.format("setShowActionsInCompactView: action %d out of bounds (max %d)", objArr2));
                        throw th2;
                    }
                    view.addView(C0173R.C0175id.media_actions, generateMediaActionButton(this.mBuilder.mActions.get(this.mActionsToShowInCompact[i])));
                }
            }
            if (this.mShowCancelButton) {
                view.setViewVisibility(C0173R.C0175id.end_padder, 8);
                view.setViewVisibility(C0173R.C0175id.cancel_action, 0);
                view.setOnClickPendingIntent(C0173R.C0175id.cancel_action, this.mCancelButtonIntent);
                view.setInt(C0173R.C0175id.cancel_action, "setAlpha", this.mBuilder.mContext.getResources().getInteger(C0173R.integer.cancel_button_image_alpha));
            } else {
                view.setViewVisibility(C0173R.C0175id.end_padder, 0);
                view.setViewVisibility(C0173R.C0175id.cancel_action, 8);
            }
            return view;
        }

        private RemoteViews generateMediaActionButton(NotificationCompat.Action action) {
            RemoteViews remoteViews;
            NotificationCompat.Action action2 = action;
            boolean tombstone = action2.getActionIntent() == null;
            new RemoteViews(this.mBuilder.mContext.getPackageName(), C0173R.layout.notification_media_action);
            RemoteViews button = remoteViews;
            button.setImageViewResource(C0173R.C0175id.action0, action2.getIcon());
            if (!tombstone) {
                button.setOnClickPendingIntent(C0173R.C0175id.action0, action2.getActionIntent());
            }
            if (Build.VERSION.SDK_INT >= 15) {
                button.setContentDescription(C0173R.C0175id.action0, action2.getTitle());
            }
            return button;
        }

        /* access modifiers changed from: package-private */
        public int getContentViewLayoutResource() {
            return C0173R.layout.notification_template_media;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor2 = notificationBuilderWithBuilderAccessor;
            if (Build.VERSION.SDK_INT >= 21) {
                return null;
            }
            return generateBigContentView();
        }

        /* access modifiers changed from: package-private */
        public RemoteViews generateBigContentView() {
            int actionCount = Math.min(this.mBuilder.mActions.size(), 5);
            RemoteViews big = applyStandardTemplate(false, getBigContentViewLayoutResource(actionCount), false);
            big.removeAllViews(C0173R.C0175id.media_actions);
            if (actionCount > 0) {
                for (int i = 0; i < actionCount; i++) {
                    big.addView(C0173R.C0175id.media_actions, generateMediaActionButton(this.mBuilder.mActions.get(i)));
                }
            }
            if (this.mShowCancelButton) {
                big.setViewVisibility(C0173R.C0175id.cancel_action, 0);
                big.setInt(C0173R.C0175id.cancel_action, "setAlpha", this.mBuilder.mContext.getResources().getInteger(C0173R.integer.cancel_button_image_alpha));
                big.setOnClickPendingIntent(C0173R.C0175id.cancel_action, this.mCancelButtonIntent);
            } else {
                big.setViewVisibility(C0173R.C0175id.cancel_action, 8);
            }
            return big;
        }

        /* access modifiers changed from: package-private */
        public int getBigContentViewLayoutResource(int actionCount) {
            return actionCount <= 3 ? C0173R.layout.notification_template_big_media_narrow : C0173R.layout.notification_template_big_media;
        }
    }

    /* renamed from: android.support.v4.media.app.NotificationCompat$DecoratedMediaCustomViewStyle */
    public static class DecoratedMediaCustomViewStyle extends MediaStyle {
        public DecoratedMediaCustomViewStyle() {
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            Notification.MediaStyle mediaStyle;
            NotificationBuilderWithBuilderAccessor builder = notificationBuilderWithBuilderAccessor;
            if (Build.VERSION.SDK_INT >= 24) {
                Notification.Builder builder2 = builder.getBuilder();
                new Notification.DecoratedMediaCustomViewStyle();
                Notification.Builder style = builder2.setStyle(fillInMediaStyle(mediaStyle));
                return;
            }
            super.apply(builder);
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor2 = notificationBuilderWithBuilderAccessor;
            if (Build.VERSION.SDK_INT >= 24) {
                return null;
            }
            boolean hasContentView = this.mBuilder.getContentView() != null;
            if (Build.VERSION.SDK_INT >= 21) {
                if (hasContentView || this.mBuilder.getBigContentView() != null) {
                    RemoteViews contentView = generateContentView();
                    if (hasContentView) {
                        buildIntoRemoteViews(contentView, this.mBuilder.getContentView());
                    }
                    setBackgroundColor(contentView);
                    return contentView;
                }
            } else {
                RemoteViews contentView2 = generateContentView();
                if (hasContentView) {
                    buildIntoRemoteViews(contentView2, this.mBuilder.getContentView());
                    return contentView2;
                }
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        public int getContentViewLayoutResource() {
            int contentViewLayoutResource;
            if (this.mBuilder.getContentView() != null) {
                contentViewLayoutResource = C0173R.layout.notification_template_media_custom;
            } else {
                contentViewLayoutResource = super.getContentViewLayoutResource();
            }
            return contentViewLayoutResource;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            RemoteViews contentView;
            NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor2 = notificationBuilderWithBuilderAccessor;
            if (Build.VERSION.SDK_INT >= 24) {
                return null;
            }
            if (this.mBuilder.getBigContentView() != null) {
                contentView = this.mBuilder.getBigContentView();
            } else {
                contentView = this.mBuilder.getContentView();
            }
            RemoteViews innerView = contentView;
            if (innerView == null) {
                return null;
            }
            RemoteViews bigContentView = generateBigContentView();
            buildIntoRemoteViews(bigContentView, innerView);
            if (Build.VERSION.SDK_INT >= 21) {
                setBackgroundColor(bigContentView);
            }
            return bigContentView;
        }

        /* access modifiers changed from: package-private */
        public int getBigContentViewLayoutResource(int actionCount) {
            return actionCount <= 3 ? C0173R.layout.notification_template_big_media_narrow_custom : C0173R.layout.notification_template_big_media_custom;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            RemoteViews contentView;
            NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor2 = notificationBuilderWithBuilderAccessor;
            if (Build.VERSION.SDK_INT >= 24) {
                return null;
            }
            if (this.mBuilder.getHeadsUpContentView() != null) {
                contentView = this.mBuilder.getHeadsUpContentView();
            } else {
                contentView = this.mBuilder.getContentView();
            }
            RemoteViews innerView = contentView;
            if (innerView == null) {
                return null;
            }
            RemoteViews headsUpContentView = generateBigContentView();
            buildIntoRemoteViews(headsUpContentView, innerView);
            if (Build.VERSION.SDK_INT >= 21) {
                setBackgroundColor(headsUpContentView);
            }
            return headsUpContentView;
        }

        private void setBackgroundColor(RemoteViews remoteViews) {
            int color;
            RemoteViews views = remoteViews;
            if (this.mBuilder.getColor() != 0) {
                color = this.mBuilder.getColor();
            } else {
                color = this.mBuilder.mContext.getResources().getColor(C0173R.color.notification_material_background_media_default_color);
            }
            views.setInt(C0173R.C0175id.status_bar_latest_event_content, "setBackgroundColor", color);
        }
    }
}

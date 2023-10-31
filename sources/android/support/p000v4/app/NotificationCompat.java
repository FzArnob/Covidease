package android.support.p000v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.compat.C0020R;
import android.support.p000v4.app.Person;
import android.support.p000v4.text.BidiFormatter;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.SparseArray;
import android.widget.RemoteViews;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* renamed from: android.support.v4.app.NotificationCompat */
public class NotificationCompat {
    public static final int BADGE_ICON_LARGE = 2;
    public static final int BADGE_ICON_NONE = 0;
    public static final int BADGE_ICON_SMALL = 1;
    public static final String CATEGORY_ALARM = "alarm";
    public static final String CATEGORY_CALL = "call";
    public static final String CATEGORY_EMAIL = "email";
    public static final String CATEGORY_ERROR = "err";
    public static final String CATEGORY_EVENT = "event";
    public static final String CATEGORY_MESSAGE = "msg";
    public static final String CATEGORY_PROGRESS = "progress";
    public static final String CATEGORY_PROMO = "promo";
    public static final String CATEGORY_RECOMMENDATION = "recommendation";
    public static final String CATEGORY_REMINDER = "reminder";
    public static final String CATEGORY_SERVICE = "service";
    public static final String CATEGORY_SOCIAL = "social";
    public static final String CATEGORY_STATUS = "status";
    public static final String CATEGORY_SYSTEM = "sys";
    public static final String CATEGORY_TRANSPORT = "transport";
    @ColorInt
    public static final int COLOR_DEFAULT = 0;
    public static final int DEFAULT_ALL = -1;
    public static final int DEFAULT_LIGHTS = 4;
    public static final int DEFAULT_SOUND = 1;
    public static final int DEFAULT_VIBRATE = 2;
    public static final String EXTRA_AUDIO_CONTENTS_URI = "android.audioContents";
    public static final String EXTRA_BACKGROUND_IMAGE_URI = "android.backgroundImageUri";
    public static final String EXTRA_BIG_TEXT = "android.bigText";
    public static final String EXTRA_COMPACT_ACTIONS = "android.compactActions";
    public static final String EXTRA_CONVERSATION_TITLE = "android.conversationTitle";
    public static final String EXTRA_HIDDEN_CONVERSATION_TITLE = "android.hiddenConversationTitle";
    public static final String EXTRA_INFO_TEXT = "android.infoText";
    public static final String EXTRA_IS_GROUP_CONVERSATION = "android.isGroupConversation";
    public static final String EXTRA_LARGE_ICON = "android.largeIcon";
    public static final String EXTRA_LARGE_ICON_BIG = "android.largeIcon.big";
    public static final String EXTRA_MEDIA_SESSION = "android.mediaSession";
    public static final String EXTRA_MESSAGES = "android.messages";
    public static final String EXTRA_MESSAGING_STYLE_USER = "android.messagingStyleUser";
    public static final String EXTRA_PEOPLE = "android.people";
    public static final String EXTRA_PICTURE = "android.picture";
    public static final String EXTRA_PROGRESS = "android.progress";
    public static final String EXTRA_PROGRESS_INDETERMINATE = "android.progressIndeterminate";
    public static final String EXTRA_PROGRESS_MAX = "android.progressMax";
    public static final String EXTRA_REMOTE_INPUT_HISTORY = "android.remoteInputHistory";
    public static final String EXTRA_SELF_DISPLAY_NAME = "android.selfDisplayName";
    public static final String EXTRA_SHOW_CHRONOMETER = "android.showChronometer";
    public static final String EXTRA_SHOW_WHEN = "android.showWhen";
    public static final String EXTRA_SMALL_ICON = "android.icon";
    public static final String EXTRA_SUB_TEXT = "android.subText";
    public static final String EXTRA_SUMMARY_TEXT = "android.summaryText";
    public static final String EXTRA_TEMPLATE = "android.template";
    public static final String EXTRA_TEXT = "android.text";
    public static final String EXTRA_TEXT_LINES = "android.textLines";
    public static final String EXTRA_TITLE = "android.title";
    public static final String EXTRA_TITLE_BIG = "android.title.big";
    public static final int FLAG_AUTO_CANCEL = 16;
    public static final int FLAG_FOREGROUND_SERVICE = 64;
    public static final int FLAG_GROUP_SUMMARY = 512;
    @Deprecated
    public static final int FLAG_HIGH_PRIORITY = 128;
    public static final int FLAG_INSISTENT = 4;
    public static final int FLAG_LOCAL_ONLY = 256;
    public static final int FLAG_NO_CLEAR = 32;
    public static final int FLAG_ONGOING_EVENT = 2;
    public static final int FLAG_ONLY_ALERT_ONCE = 8;
    public static final int FLAG_SHOW_LIGHTS = 1;
    public static final int GROUP_ALERT_ALL = 0;
    public static final int GROUP_ALERT_CHILDREN = 2;
    public static final int GROUP_ALERT_SUMMARY = 1;
    public static final int PRIORITY_DEFAULT = 0;
    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_LOW = -1;
    public static final int PRIORITY_MAX = 2;
    public static final int PRIORITY_MIN = -2;
    public static final int STREAM_DEFAULT = -1;
    public static final int VISIBILITY_PRIVATE = 0;
    public static final int VISIBILITY_PUBLIC = 1;
    public static final int VISIBILITY_SECRET = -1;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: android.support.v4.app.NotificationCompat$BadgeIconType */
    public @interface BadgeIconType {
    }

    /* renamed from: android.support.v4.app.NotificationCompat$Extender */
    public interface Extender {
        Builder extend(Builder builder);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: android.support.v4.app.NotificationCompat$GroupAlertBehavior */
    public @interface GroupAlertBehavior {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: android.support.v4.app.NotificationCompat$NotificationVisibility */
    public @interface NotificationVisibility {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: android.support.v4.app.NotificationCompat$StreamType */
    public @interface StreamType {
    }

    /* renamed from: android.support.v4.app.NotificationCompat$Builder */
    public static class Builder {
        private static final int MAX_CHARSEQUENCE_LENGTH = 5120;
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public ArrayList<Action> mActions;
        int mBadgeIcon;
        RemoteViews mBigContentView;
        String mCategory;
        String mChannelId;
        int mColor;
        boolean mColorized;
        boolean mColorizedSet;
        CharSequence mContentInfo;
        PendingIntent mContentIntent;
        CharSequence mContentText;
        CharSequence mContentTitle;
        RemoteViews mContentView;
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Context mContext;
        Bundle mExtras;
        PendingIntent mFullScreenIntent;
        int mGroupAlertBehavior;
        String mGroupKey;
        boolean mGroupSummary;
        RemoteViews mHeadsUpContentView;
        ArrayList<Action> mInvisibleActions;
        Bitmap mLargeIcon;
        boolean mLocalOnly;
        Notification mNotification;
        int mNumber;
        @Deprecated
        public ArrayList<String> mPeople;
        int mPriority;
        int mProgress;
        boolean mProgressIndeterminate;
        int mProgressMax;
        Notification mPublicVersion;
        CharSequence[] mRemoteInputHistory;
        String mShortcutId;
        boolean mShowWhen;
        String mSortKey;
        Style mStyle;
        CharSequence mSubText;
        RemoteViews mTickerView;
        long mTimeout;
        boolean mUseChronometer;
        int mVisibility;

        public Builder(@NonNull Context context, @NonNull String channelId) {
            ArrayList<Action> arrayList;
            ArrayList<Action> arrayList2;
            Notification notification;
            ArrayList<String> arrayList3;
            new ArrayList<>();
            this.mActions = arrayList;
            new ArrayList<>();
            this.mInvisibleActions = arrayList2;
            this.mShowWhen = true;
            this.mLocalOnly = false;
            this.mColor = 0;
            this.mVisibility = 0;
            this.mBadgeIcon = 0;
            this.mGroupAlertBehavior = 0;
            new Notification();
            this.mNotification = notification;
            this.mContext = context;
            this.mChannelId = channelId;
            this.mNotification.when = System.currentTimeMillis();
            this.mNotification.audioStreamType = -1;
            this.mPriority = 0;
            new ArrayList<>();
            this.mPeople = arrayList3;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        @Deprecated
        public Builder(Context context) {
            this(context, (String) null);
        }

        public Builder setWhen(long when) {
            this.mNotification.when = when;
            return this;
        }

        public Builder setShowWhen(boolean show) {
            this.mShowWhen = show;
            return this;
        }

        public Builder setUsesChronometer(boolean b) {
            this.mUseChronometer = b;
            return this;
        }

        public Builder setSmallIcon(int icon) {
            this.mNotification.icon = icon;
            return this;
        }

        public Builder setSmallIcon(int icon, int level) {
            this.mNotification.icon = icon;
            this.mNotification.iconLevel = level;
            return this;
        }

        public Builder setContentTitle(CharSequence title) {
            this.mContentTitle = limitCharSequenceLength(title);
            return this;
        }

        public Builder setContentText(CharSequence text) {
            this.mContentText = limitCharSequenceLength(text);
            return this;
        }

        public Builder setSubText(CharSequence text) {
            this.mSubText = limitCharSequenceLength(text);
            return this;
        }

        public Builder setRemoteInputHistory(CharSequence[] text) {
            this.mRemoteInputHistory = text;
            return this;
        }

        public Builder setNumber(int number) {
            this.mNumber = number;
            return this;
        }

        public Builder setContentInfo(CharSequence info) {
            this.mContentInfo = limitCharSequenceLength(info);
            return this;
        }

        public Builder setProgress(int max, int progress, boolean indeterminate) {
            this.mProgressMax = max;
            this.mProgress = progress;
            this.mProgressIndeterminate = indeterminate;
            return this;
        }

        public Builder setContent(RemoteViews views) {
            this.mNotification.contentView = views;
            return this;
        }

        public Builder setContentIntent(PendingIntent intent) {
            this.mContentIntent = intent;
            return this;
        }

        public Builder setDeleteIntent(PendingIntent intent) {
            this.mNotification.deleteIntent = intent;
            return this;
        }

        public Builder setFullScreenIntent(PendingIntent intent, boolean highPriority) {
            this.mFullScreenIntent = intent;
            setFlag(128, highPriority);
            return this;
        }

        public Builder setTicker(CharSequence tickerText) {
            this.mNotification.tickerText = limitCharSequenceLength(tickerText);
            return this;
        }

        public Builder setTicker(CharSequence tickerText, RemoteViews views) {
            this.mNotification.tickerText = limitCharSequenceLength(tickerText);
            this.mTickerView = views;
            return this;
        }

        public Builder setLargeIcon(Bitmap icon) {
            this.mLargeIcon = reduceLargeIconSize(icon);
            return this;
        }

        private Bitmap reduceLargeIconSize(Bitmap bitmap) {
            Bitmap icon = bitmap;
            if (icon == null || Build.VERSION.SDK_INT >= 27) {
                return icon;
            }
            Resources res = this.mContext.getResources();
            int maxWidth = res.getDimensionPixelSize(C0020R.dimen.compat_notification_large_icon_max_width);
            int maxHeight = res.getDimensionPixelSize(C0020R.dimen.compat_notification_large_icon_max_height);
            if (icon.getWidth() <= maxWidth && icon.getHeight() <= maxHeight) {
                return icon;
            }
            double scale = Math.min(((double) maxWidth) / ((double) Math.max(1, icon.getWidth())), ((double) maxHeight) / ((double) Math.max(1, icon.getHeight())));
            return Bitmap.createScaledBitmap(icon, (int) Math.ceil(((double) icon.getWidth()) * scale), (int) Math.ceil(((double) icon.getHeight()) * scale), true);
        }

        public Builder setSound(Uri sound) {
            AudioAttributes.Builder builder;
            this.mNotification.sound = sound;
            this.mNotification.audioStreamType = -1;
            if (Build.VERSION.SDK_INT >= 21) {
                Notification notification = this.mNotification;
                new AudioAttributes.Builder();
                notification.audioAttributes = builder.setContentType(4).setUsage(5).build();
            }
            return this;
        }

        public Builder setSound(Uri sound, int i) {
            AudioAttributes.Builder builder;
            int streamType = i;
            this.mNotification.sound = sound;
            this.mNotification.audioStreamType = streamType;
            if (Build.VERSION.SDK_INT >= 21) {
                Notification notification = this.mNotification;
                new AudioAttributes.Builder();
                notification.audioAttributes = builder.setContentType(4).setLegacyStreamType(streamType).build();
            }
            return this;
        }

        public Builder setVibrate(long[] pattern) {
            this.mNotification.vibrate = pattern;
            return this;
        }

        public Builder setLights(@ColorInt int argb, int onMs, int offMs) {
            this.mNotification.ledARGB = argb;
            this.mNotification.ledOnMS = onMs;
            this.mNotification.ledOffMS = offMs;
            this.mNotification.flags = (this.mNotification.flags & -2) | (this.mNotification.ledOnMS != 0 && this.mNotification.ledOffMS != 0 ? 1 : 0);
            return this;
        }

        public Builder setOngoing(boolean ongoing) {
            setFlag(2, ongoing);
            return this;
        }

        public Builder setColorized(boolean colorize) {
            this.mColorized = colorize;
            this.mColorizedSet = true;
            return this;
        }

        public Builder setOnlyAlertOnce(boolean onlyAlertOnce) {
            setFlag(8, onlyAlertOnce);
            return this;
        }

        public Builder setAutoCancel(boolean autoCancel) {
            setFlag(16, autoCancel);
            return this;
        }

        public Builder setLocalOnly(boolean b) {
            this.mLocalOnly = b;
            return this;
        }

        public Builder setCategory(String category) {
            this.mCategory = category;
            return this;
        }

        public Builder setDefaults(int i) {
            int defaults = i;
            this.mNotification.defaults = defaults;
            if ((defaults & 4) != 0) {
                this.mNotification.flags |= 1;
            }
            return this;
        }

        private void setFlag(int i, boolean value) {
            int mask = i;
            if (value) {
                this.mNotification.flags |= mask;
                return;
            }
            this.mNotification.flags &= mask ^ -1;
        }

        public Builder setPriority(int pri) {
            this.mPriority = pri;
            return this;
        }

        public Builder addPerson(String uri) {
            boolean add = this.mPeople.add(uri);
            return this;
        }

        public Builder setGroup(String groupKey) {
            this.mGroupKey = groupKey;
            return this;
        }

        public Builder setGroupSummary(boolean isGroupSummary) {
            this.mGroupSummary = isGroupSummary;
            return this;
        }

        public Builder setSortKey(String sortKey) {
            this.mSortKey = sortKey;
            return this;
        }

        public Builder addExtras(Bundle bundle) {
            Bundle bundle2;
            Bundle extras = bundle;
            if (extras != null) {
                if (this.mExtras == null) {
                    new Bundle(extras);
                    this.mExtras = bundle2;
                } else {
                    this.mExtras.putAll(extras);
                }
            }
            return this;
        }

        public Builder setExtras(Bundle extras) {
            this.mExtras = extras;
            return this;
        }

        public Bundle getExtras() {
            Bundle bundle;
            if (this.mExtras == null) {
                new Bundle();
                this.mExtras = bundle;
            }
            return this.mExtras;
        }

        public Builder addAction(int icon, CharSequence title, PendingIntent intent) {
            Object obj;
            new Action(icon, title, intent);
            boolean add = this.mActions.add(obj);
            return this;
        }

        public Builder addAction(Action action) {
            boolean add = this.mActions.add(action);
            return this;
        }

        @RequiresApi(21)
        public Builder addInvisibleAction(int icon, CharSequence title, PendingIntent intent) {
            Action action;
            new Action(icon, title, intent);
            return addInvisibleAction(action);
        }

        @RequiresApi(21)
        public Builder addInvisibleAction(Action action) {
            boolean add = this.mInvisibleActions.add(action);
            return this;
        }

        public Builder setStyle(Style style) {
            Style style2 = style;
            if (this.mStyle != style2) {
                this.mStyle = style2;
                if (this.mStyle != null) {
                    this.mStyle.setBuilder(this);
                }
            }
            return this;
        }

        public Builder setColor(@ColorInt int argb) {
            this.mColor = argb;
            return this;
        }

        public Builder setVisibility(int visibility) {
            this.mVisibility = visibility;
            return this;
        }

        public Builder setPublicVersion(Notification n) {
            this.mPublicVersion = n;
            return this;
        }

        public Builder setCustomContentView(RemoteViews contentView) {
            this.mContentView = contentView;
            return this;
        }

        public Builder setCustomBigContentView(RemoteViews contentView) {
            this.mBigContentView = contentView;
            return this;
        }

        public Builder setCustomHeadsUpContentView(RemoteViews contentView) {
            this.mHeadsUpContentView = contentView;
            return this;
        }

        public Builder setChannelId(@NonNull String channelId) {
            this.mChannelId = channelId;
            return this;
        }

        public Builder setTimeoutAfter(long durationMs) {
            this.mTimeout = durationMs;
            return this;
        }

        public Builder setShortcutId(String shortcutId) {
            this.mShortcutId = shortcutId;
            return this;
        }

        public Builder setBadgeIconType(int icon) {
            this.mBadgeIcon = icon;
            return this;
        }

        public Builder setGroupAlertBehavior(int groupAlertBehavior) {
            this.mGroupAlertBehavior = groupAlertBehavior;
            return this;
        }

        public Builder extend(Extender extender) {
            Builder extend = extender.extend(this);
            return this;
        }

        @Deprecated
        public Notification getNotification() {
            return build();
        }

        public Notification build() {
            NotificationCompatBuilder notificationCompatBuilder;
            new NotificationCompatBuilder(this);
            return notificationCompatBuilder.build();
        }

        protected static CharSequence limitCharSequenceLength(CharSequence charSequence) {
            CharSequence cs = charSequence;
            if (cs == null) {
                return cs;
            }
            if (cs.length() > MAX_CHARSEQUENCE_LENGTH) {
                cs = cs.subSequence(0, MAX_CHARSEQUENCE_LENGTH);
            }
            return cs;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews getContentView() {
            return this.mContentView;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews getBigContentView() {
            return this.mBigContentView;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews getHeadsUpContentView() {
            return this.mHeadsUpContentView;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public long getWhenIfShowing() {
            return this.mShowWhen ? this.mNotification.when : 0;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public int getPriority() {
            return this.mPriority;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public int getColor() {
            return this.mColor;
        }
    }

    /* renamed from: android.support.v4.app.NotificationCompat$Style */
    public static abstract class Style {
        CharSequence mBigContentTitle;
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        protected Builder mBuilder;
        CharSequence mSummaryText;
        boolean mSummaryTextSet = false;

        public Style() {
        }

        public void setBuilder(Builder builder) {
            Builder builder2 = builder;
            if (this.mBuilder != builder2) {
                this.mBuilder = builder2;
                if (this.mBuilder != null) {
                    Builder style = this.mBuilder.setStyle(this);
                }
            }
        }

        public Notification build() {
            Notification notification = null;
            if (this.mBuilder != null) {
                notification = this.mBuilder.build();
            }
            return notification;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor builder) {
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor2 = notificationBuilderWithBuilderAccessor;
            return null;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor2 = notificationBuilderWithBuilderAccessor;
            return null;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor2 = notificationBuilderWithBuilderAccessor;
            return null;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void addCompatExtras(Bundle extras) {
        }

        /* access modifiers changed from: protected */
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void restoreFromCompatExtras(Bundle extras) {
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews applyStandardTemplate(boolean z, int resId, boolean z2) {
            RemoteViews remoteViews;
            boolean showSmallIcon = z;
            boolean fitIn1U = z2;
            Resources res = this.mBuilder.mContext.getResources();
            new RemoteViews(this.mBuilder.mContext.getPackageName(), resId);
            RemoteViews contentView = remoteViews;
            boolean showLine3 = false;
            boolean showLine2 = false;
            boolean minPriority = this.mBuilder.getPriority() < -1;
            if (Build.VERSION.SDK_INT >= 16 && Build.VERSION.SDK_INT < 21) {
                if (minPriority) {
                    contentView.setInt(C0020R.C0022id.notification_background, "setBackgroundResource", C0020R.C0021drawable.notification_bg_low);
                    contentView.setInt(C0020R.C0022id.icon, "setBackgroundResource", C0020R.C0021drawable.notification_template_icon_low_bg);
                } else {
                    contentView.setInt(C0020R.C0022id.notification_background, "setBackgroundResource", C0020R.C0021drawable.notification_bg);
                    contentView.setInt(C0020R.C0022id.icon, "setBackgroundResource", C0020R.C0021drawable.notification_template_icon_bg);
                }
            }
            if (this.mBuilder.mLargeIcon != null) {
                if (Build.VERSION.SDK_INT >= 16) {
                    contentView.setViewVisibility(C0020R.C0022id.icon, 0);
                    contentView.setImageViewBitmap(C0020R.C0022id.icon, this.mBuilder.mLargeIcon);
                } else {
                    contentView.setViewVisibility(C0020R.C0022id.icon, 8);
                }
                if (showSmallIcon && this.mBuilder.mNotification.icon != 0) {
                    int backgroundSize = res.getDimensionPixelSize(C0020R.dimen.notification_right_icon_size);
                    int iconSize = backgroundSize - (res.getDimensionPixelSize(C0020R.dimen.notification_small_icon_background_padding) * 2);
                    if (Build.VERSION.SDK_INT >= 21) {
                        contentView.setImageViewBitmap(C0020R.C0022id.right_icon, createIconWithBackground(this.mBuilder.mNotification.icon, backgroundSize, iconSize, this.mBuilder.getColor()));
                    } else {
                        contentView.setImageViewBitmap(C0020R.C0022id.right_icon, createColoredBitmap(this.mBuilder.mNotification.icon, -1));
                    }
                    contentView.setViewVisibility(C0020R.C0022id.right_icon, 0);
                }
            } else if (showSmallIcon && this.mBuilder.mNotification.icon != 0) {
                contentView.setViewVisibility(C0020R.C0022id.icon, 0);
                if (Build.VERSION.SDK_INT >= 21) {
                    contentView.setImageViewBitmap(C0020R.C0022id.icon, createIconWithBackground(this.mBuilder.mNotification.icon, res.getDimensionPixelSize(C0020R.dimen.notification_large_icon_width) - res.getDimensionPixelSize(C0020R.dimen.notification_big_circle_margin), res.getDimensionPixelSize(C0020R.dimen.notification_small_icon_size_as_large), this.mBuilder.getColor()));
                } else {
                    contentView.setImageViewBitmap(C0020R.C0022id.icon, createColoredBitmap(this.mBuilder.mNotification.icon, -1));
                }
            }
            if (this.mBuilder.mContentTitle != null) {
                contentView.setTextViewText(C0020R.C0022id.title, this.mBuilder.mContentTitle);
            }
            if (this.mBuilder.mContentText != null) {
                contentView.setTextViewText(C0020R.C0022id.text, this.mBuilder.mContentText);
                showLine3 = true;
            }
            boolean hasRightSide = Build.VERSION.SDK_INT < 21 && this.mBuilder.mLargeIcon != null;
            if (this.mBuilder.mContentInfo != null) {
                contentView.setTextViewText(C0020R.C0022id.info, this.mBuilder.mContentInfo);
                contentView.setViewVisibility(C0020R.C0022id.info, 0);
                showLine3 = true;
                hasRightSide = true;
            } else if (this.mBuilder.mNumber > 0) {
                if (this.mBuilder.mNumber > res.getInteger(C0020R.integer.status_bar_notification_info_maxnum)) {
                    contentView.setTextViewText(C0020R.C0022id.info, res.getString(C0020R.string.status_bar_notification_info_overflow));
                } else {
                    contentView.setTextViewText(C0020R.C0022id.info, NumberFormat.getIntegerInstance().format((long) this.mBuilder.mNumber));
                }
                contentView.setViewVisibility(C0020R.C0022id.info, 0);
                showLine3 = true;
                hasRightSide = true;
            } else {
                contentView.setViewVisibility(C0020R.C0022id.info, 8);
            }
            if (this.mBuilder.mSubText != null && Build.VERSION.SDK_INT >= 16) {
                contentView.setTextViewText(C0020R.C0022id.text, this.mBuilder.mSubText);
                if (this.mBuilder.mContentText != null) {
                    contentView.setTextViewText(C0020R.C0022id.text2, this.mBuilder.mContentText);
                    contentView.setViewVisibility(C0020R.C0022id.text2, 0);
                    showLine2 = true;
                } else {
                    contentView.setViewVisibility(C0020R.C0022id.text2, 8);
                }
            }
            if (showLine2 && Build.VERSION.SDK_INT >= 16) {
                if (fitIn1U) {
                    contentView.setTextViewTextSize(C0020R.C0022id.text, 0, (float) res.getDimensionPixelSize(C0020R.dimen.notification_subtext_size));
                }
                contentView.setViewPadding(C0020R.C0022id.line1, 0, 0, 0, 0);
            }
            if (this.mBuilder.getWhenIfShowing() != 0) {
                if (!this.mBuilder.mUseChronometer || Build.VERSION.SDK_INT < 16) {
                    contentView.setViewVisibility(C0020R.C0022id.time, 0);
                    contentView.setLong(C0020R.C0022id.time, "setTime", this.mBuilder.getWhenIfShowing());
                } else {
                    contentView.setViewVisibility(C0020R.C0022id.chronometer, 0);
                    contentView.setLong(C0020R.C0022id.chronometer, "setBase", this.mBuilder.getWhenIfShowing() + (SystemClock.elapsedRealtime() - System.currentTimeMillis()));
                    contentView.setBoolean(C0020R.C0022id.chronometer, "setStarted", true);
                }
                hasRightSide = true;
            }
            contentView.setViewVisibility(C0020R.C0022id.right_side, hasRightSide ? 0 : 8);
            contentView.setViewVisibility(C0020R.C0022id.line3, showLine3 ? 0 : 8);
            return contentView;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Bitmap createColoredBitmap(int iconId, int color) {
            return createColoredBitmap(iconId, color, 0);
        }

        private Bitmap createColoredBitmap(int iconId, int i, int i2) {
            Canvas canvas;
            ColorFilter colorFilter;
            int color = i;
            int size = i2;
            Drawable drawable = this.mBuilder.mContext.getResources().getDrawable(iconId);
            int width = size == 0 ? drawable.getIntrinsicWidth() : size;
            int height = size == 0 ? drawable.getIntrinsicHeight() : size;
            Bitmap resultBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            drawable.setBounds(0, 0, width, height);
            if (color != 0) {
                new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN);
                drawable.mutate().setColorFilter(colorFilter);
            }
            new Canvas(resultBitmap);
            drawable.draw(canvas);
            return resultBitmap;
        }

        private Bitmap createIconWithBackground(int i, int i2, int i3, int i4) {
            Canvas canvas;
            ColorFilter colorFilter;
            int iconId = i;
            int size = i2;
            int iconSize = i3;
            int color = i4;
            Bitmap coloredBitmap = createColoredBitmap(C0020R.C0021drawable.notification_icon_background, color == 0 ? 0 : color, size);
            new Canvas(coloredBitmap);
            Drawable icon = this.mBuilder.mContext.getResources().getDrawable(iconId).mutate();
            icon.setFilterBitmap(true);
            int inset = (size - iconSize) / 2;
            icon.setBounds(inset, inset, iconSize + inset, iconSize + inset);
            new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_ATOP);
            icon.setColorFilter(colorFilter);
            icon.draw(canvas);
            return coloredBitmap;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void buildIntoRemoteViews(RemoteViews remoteViews, RemoteViews innerView) {
            RemoteViews outerView = remoteViews;
            hideNormalContent(outerView);
            outerView.removeAllViews(C0020R.C0022id.notification_main_column);
            outerView.addView(C0020R.C0022id.notification_main_column, innerView.clone());
            outerView.setViewVisibility(C0020R.C0022id.notification_main_column, 0);
            if (Build.VERSION.SDK_INT >= 21) {
                outerView.setViewPadding(C0020R.C0022id.notification_main_column_container, 0, calculateTopPadding(), 0, 0);
            }
        }

        private void hideNormalContent(RemoteViews remoteViews) {
            RemoteViews outerView = remoteViews;
            outerView.setViewVisibility(C0020R.C0022id.title, 8);
            outerView.setViewVisibility(C0020R.C0022id.text2, 8);
            outerView.setViewVisibility(C0020R.C0022id.text, 8);
        }

        private int calculateTopPadding() {
            Resources resources = this.mBuilder.mContext.getResources();
            int padding = resources.getDimensionPixelSize(C0020R.dimen.notification_top_pad);
            int largePadding = resources.getDimensionPixelSize(C0020R.dimen.notification_top_pad_large_text);
            float largeFactor = (constrain(resources.getConfiguration().fontScale, 1.0f, 1.3f) - 1.0f) / 0.29999995f;
            return Math.round(((1.0f - largeFactor) * ((float) padding)) + (largeFactor * ((float) largePadding)));
        }

        private static float constrain(float f, float f2, float f3) {
            float amount = f;
            float low = f2;
            float high = f3;
            return amount < low ? low : amount > high ? high : amount;
        }
    }

    /* renamed from: android.support.v4.app.NotificationCompat$BigPictureStyle */
    public static class BigPictureStyle extends Style {
        private Bitmap mBigLargeIcon;
        private boolean mBigLargeIconSet;
        private Bitmap mPicture;

        public BigPictureStyle() {
        }

        public BigPictureStyle(Builder builder) {
            setBuilder(builder);
        }

        public BigPictureStyle setBigContentTitle(CharSequence title) {
            this.mBigContentTitle = Builder.limitCharSequenceLength(title);
            return this;
        }

        public BigPictureStyle setSummaryText(CharSequence cs) {
            this.mSummaryText = Builder.limitCharSequenceLength(cs);
            this.mSummaryTextSet = true;
            return this;
        }

        public BigPictureStyle bigPicture(Bitmap b) {
            this.mPicture = b;
            return this;
        }

        public BigPictureStyle bigLargeIcon(Bitmap b) {
            this.mBigLargeIcon = b;
            this.mBigLargeIconSet = true;
            return this;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            Notification.BigPictureStyle bigPictureStyle;
            NotificationBuilderWithBuilderAccessor builder = notificationBuilderWithBuilderAccessor;
            if (Build.VERSION.SDK_INT >= 16) {
                new Notification.BigPictureStyle(builder.getBuilder());
                Notification.BigPictureStyle style = bigPictureStyle.setBigContentTitle(this.mBigContentTitle).bigPicture(this.mPicture);
                if (this.mBigLargeIconSet) {
                    Notification.BigPictureStyle bigLargeIcon = style.bigLargeIcon(this.mBigLargeIcon);
                }
                if (this.mSummaryTextSet) {
                    Notification.BigPictureStyle summaryText = style.setSummaryText(this.mSummaryText);
                }
            }
        }
    }

    /* renamed from: android.support.v4.app.NotificationCompat$BigTextStyle */
    public static class BigTextStyle extends Style {
        private CharSequence mBigText;

        public BigTextStyle() {
        }

        public BigTextStyle(Builder builder) {
            setBuilder(builder);
        }

        public BigTextStyle setBigContentTitle(CharSequence title) {
            this.mBigContentTitle = Builder.limitCharSequenceLength(title);
            return this;
        }

        public BigTextStyle setSummaryText(CharSequence cs) {
            this.mSummaryText = Builder.limitCharSequenceLength(cs);
            this.mSummaryTextSet = true;
            return this;
        }

        public BigTextStyle bigText(CharSequence cs) {
            this.mBigText = Builder.limitCharSequenceLength(cs);
            return this;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            Notification.BigTextStyle bigTextStyle;
            NotificationBuilderWithBuilderAccessor builder = notificationBuilderWithBuilderAccessor;
            if (Build.VERSION.SDK_INT >= 16) {
                new Notification.BigTextStyle(builder.getBuilder());
                Notification.BigTextStyle style = bigTextStyle.setBigContentTitle(this.mBigContentTitle).bigText(this.mBigText);
                if (this.mSummaryTextSet) {
                    Notification.BigTextStyle summaryText = style.setSummaryText(this.mSummaryText);
                }
            }
        }
    }

    /* renamed from: android.support.v4.app.NotificationCompat$MessagingStyle */
    public static class MessagingStyle extends Style {
        public static final int MAXIMUM_RETAINED_MESSAGES = 25;
        @Nullable
        private CharSequence mConversationTitle;
        @Nullable
        private Boolean mIsGroupConversation;
        private final List<Message> mMessages;
        private Person mUser;

        private MessagingStyle() {
            List<Message> list;
            new ArrayList();
            this.mMessages = list;
        }

        @Deprecated
        public MessagingStyle(@NonNull CharSequence userDisplayName) {
            List<Message> list;
            Person.Builder builder;
            new ArrayList();
            this.mMessages = list;
            new Person.Builder();
            this.mUser = builder.setName(userDisplayName).build();
        }

        public MessagingStyle(@NonNull Person person) {
            List<Message> list;
            Throwable th;
            Person user = person;
            new ArrayList();
            this.mMessages = list;
            if (TextUtils.isEmpty(user.getName())) {
                Throwable th2 = th;
                new IllegalArgumentException("User's name must not be empty.");
                throw th2;
            }
            this.mUser = user;
        }

        @Deprecated
        public CharSequence getUserDisplayName() {
            return this.mUser.getName();
        }

        public Person getUser() {
            return this.mUser;
        }

        public MessagingStyle setConversationTitle(@Nullable CharSequence conversationTitle) {
            this.mConversationTitle = conversationTitle;
            return this;
        }

        @Nullable
        public CharSequence getConversationTitle() {
            return this.mConversationTitle;
        }

        @Deprecated
        public MessagingStyle addMessage(CharSequence text, long timestamp, CharSequence sender) {
            Object obj;
            Person.Builder builder;
            new Person.Builder();
            new Message(text, timestamp, builder.setName(sender).build());
            boolean add = this.mMessages.add(obj);
            if (this.mMessages.size() > 25) {
                Message remove = this.mMessages.remove(0);
            }
            return this;
        }

        public MessagingStyle addMessage(CharSequence text, long timestamp, Person person) {
            Message message;
            new Message(text, timestamp, person);
            MessagingStyle addMessage = addMessage(message);
            return this;
        }

        public MessagingStyle addMessage(Message message) {
            boolean add = this.mMessages.add(message);
            if (this.mMessages.size() > 25) {
                Message remove = this.mMessages.remove(0);
            }
            return this;
        }

        public List<Message> getMessages() {
            return this.mMessages;
        }

        public MessagingStyle setGroupConversation(boolean isGroupConversation) {
            this.mIsGroupConversation = Boolean.valueOf(isGroupConversation);
            return this;
        }

        public boolean isGroupConversation() {
            if (this.mBuilder == null || this.mBuilder.mContext.getApplicationInfo().targetSdkVersion >= 28 || this.mIsGroupConversation != null) {
                return this.mIsGroupConversation != null ? this.mIsGroupConversation.booleanValue() : false;
            }
            return this.mConversationTitle != null;
        }

        @Nullable
        public static MessagingStyle extractMessagingStyleFromNotification(Notification notification) {
            MessagingStyle messagingStyle;
            Bundle extras = NotificationCompat.getExtras(notification);
            if (extras != null && !extras.containsKey(NotificationCompat.EXTRA_SELF_DISPLAY_NAME) && !extras.containsKey(NotificationCompat.EXTRA_MESSAGING_STYLE_USER)) {
                return null;
            }
            try {
                new MessagingStyle();
                MessagingStyle style = messagingStyle;
                style.restoreFromCompatExtras(extras);
                return style;
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                return null;
            }
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            SpannableStringBuilder spannableStringBuilder;
            Notification.BigTextStyle bigTextStyle;
            CharSequence text;
            Notification.MessagingStyle messagingStyle;
            Notification.MessagingStyle frameworkStyle;
            Notification.MessagingStyle.Message message;
            Notification.MessagingStyle.Message frameworkMessage;
            android.app.Person androidPerson;
            Notification.MessagingStyle messagingStyle2;
            NotificationBuilderWithBuilderAccessor builder = notificationBuilderWithBuilderAccessor;
            MessagingStyle groupConversation = setGroupConversation(isGroupConversation());
            if (Build.VERSION.SDK_INT >= 24) {
                if (Build.VERSION.SDK_INT >= 28) {
                    new Notification.MessagingStyle(this.mUser.toAndroidPerson());
                    frameworkStyle = messagingStyle2;
                } else {
                    new Notification.MessagingStyle(this.mUser.getName());
                    frameworkStyle = messagingStyle;
                }
                if (this.mIsGroupConversation.booleanValue() || Build.VERSION.SDK_INT >= 28) {
                    Notification.MessagingStyle conversationTitle = frameworkStyle.setConversationTitle(this.mConversationTitle);
                }
                if (Build.VERSION.SDK_INT >= 28) {
                    Notification.MessagingStyle groupConversation2 = frameworkStyle.setGroupConversation(this.mIsGroupConversation.booleanValue());
                }
                for (Message compatMessage : this.mMessages) {
                    if (Build.VERSION.SDK_INT >= 28) {
                        Person compatMessagePerson = compatMessage.getPerson();
                        Notification.MessagingStyle.Message message2 = r15;
                        CharSequence text2 = compatMessage.getText();
                        long timestamp = compatMessage.getTimestamp();
                        if (compatMessagePerson == null) {
                            androidPerson = null;
                        } else {
                            androidPerson = compatMessagePerson.toAndroidPerson();
                        }
                        Notification.MessagingStyle.Message message3 = new Notification.MessagingStyle.Message(text2, timestamp, androidPerson);
                        frameworkMessage = message2;
                    } else {
                        CharSequence name = null;
                        if (compatMessage.getPerson() != null) {
                            name = compatMessage.getPerson().getName();
                        }
                        new Notification.MessagingStyle.Message(compatMessage.getText(), compatMessage.getTimestamp(), name);
                        frameworkMessage = message;
                    }
                    if (compatMessage.getDataMimeType() != null) {
                        Notification.MessagingStyle.Message data = frameworkMessage.setData(compatMessage.getDataMimeType(), compatMessage.getDataUri());
                    }
                    Notification.MessagingStyle addMessage = frameworkStyle.addMessage(frameworkMessage);
                }
                frameworkStyle.setBuilder(builder.getBuilder());
                return;
            }
            Message latestIncomingMessage = findLatestIncomingMessage();
            if (this.mConversationTitle != null && this.mIsGroupConversation.booleanValue()) {
                Notification.Builder contentTitle = builder.getBuilder().setContentTitle(this.mConversationTitle);
            } else if (latestIncomingMessage != null) {
                Notification.Builder contentTitle2 = builder.getBuilder().setContentTitle("");
                if (latestIncomingMessage.getPerson() != null) {
                    Notification.Builder contentTitle3 = builder.getBuilder().setContentTitle(latestIncomingMessage.getPerson().getName());
                }
            }
            if (latestIncomingMessage != null) {
                Notification.Builder builder2 = builder.getBuilder();
                if (this.mConversationTitle != null) {
                    text = makeMessageLine(latestIncomingMessage);
                } else {
                    text = latestIncomingMessage.getText();
                }
                Notification.Builder contentText = builder2.setContentText(text);
            }
            if (Build.VERSION.SDK_INT >= 16) {
                new SpannableStringBuilder();
                SpannableStringBuilder completeMessage = spannableStringBuilder;
                boolean showNames = this.mConversationTitle != null || hasMessagesWithoutSender();
                for (int i = this.mMessages.size() - 1; i >= 0; i--) {
                    Message message4 = this.mMessages.get(i);
                    CharSequence line = showNames ? makeMessageLine(message4) : message4.getText();
                    if (i != this.mMessages.size() - 1) {
                        SpannableStringBuilder insert = completeMessage.insert(0, "\n");
                    }
                    SpannableStringBuilder insert2 = completeMessage.insert(0, line);
                }
                new Notification.BigTextStyle(builder.getBuilder());
                Notification.BigTextStyle bigText = bigTextStyle.setBigContentTitle((CharSequence) null).bigText(completeMessage);
            }
        }

        @Nullable
        private Message findLatestIncomingMessage() {
            for (int i = this.mMessages.size() - 1; i >= 0; i--) {
                Message message = this.mMessages.get(i);
                if (message.getPerson() != null && !TextUtils.isEmpty(message.getPerson().getName())) {
                    return message;
                }
            }
            if (!this.mMessages.isEmpty()) {
                return this.mMessages.get(this.mMessages.size() - 1);
            }
            return null;
        }

        private boolean hasMessagesWithoutSender() {
            for (int i = this.mMessages.size() - 1; i >= 0; i--) {
                Message message = this.mMessages.get(i);
                if (message.getPerson() != null && message.getPerson().getName() == null) {
                    return true;
                }
            }
            return false;
        }

        private CharSequence makeMessageLine(Message message) {
            SpannableStringBuilder spannableStringBuilder;
            Message message2 = message;
            BidiFormatter bidi = BidiFormatter.getInstance();
            new SpannableStringBuilder();
            SpannableStringBuilder sb = spannableStringBuilder;
            boolean afterLollipop = Build.VERSION.SDK_INT >= 21;
            int color = afterLollipop ? -16777216 : -1;
            CharSequence replyName = message2.getPerson() == null ? "" : message2.getPerson().getName();
            if (TextUtils.isEmpty(replyName)) {
                replyName = this.mUser.getName();
                color = (!afterLollipop || this.mBuilder.getColor() == 0) ? color : this.mBuilder.getColor();
            }
            CharSequence senderText = bidi.unicodeWrap(replyName);
            SpannableStringBuilder append = sb.append(senderText);
            sb.setSpan(makeFontColorSpan(color), sb.length() - senderText.length(), sb.length(), 33);
            SpannableStringBuilder append2 = sb.append("  ").append(bidi.unicodeWrap(message2.getText() == null ? "" : message2.getText()));
            return sb;
        }

        @NonNull
        private TextAppearanceSpan makeFontColorSpan(int color) {
            TextAppearanceSpan textAppearanceSpan;
            new TextAppearanceSpan((String) null, 0, 0, ColorStateList.valueOf(color), (ColorStateList) null);
            return textAppearanceSpan;
        }

        public void addCompatExtras(Bundle bundle) {
            Bundle extras = bundle;
            super.addCompatExtras(extras);
            extras.putCharSequence(NotificationCompat.EXTRA_SELF_DISPLAY_NAME, this.mUser.getName());
            extras.putBundle(NotificationCompat.EXTRA_MESSAGING_STYLE_USER, this.mUser.toBundle());
            extras.putCharSequence(NotificationCompat.EXTRA_HIDDEN_CONVERSATION_TITLE, this.mConversationTitle);
            if (this.mConversationTitle != null && this.mIsGroupConversation.booleanValue()) {
                extras.putCharSequence(NotificationCompat.EXTRA_CONVERSATION_TITLE, this.mConversationTitle);
            }
            if (!this.mMessages.isEmpty()) {
                extras.putParcelableArray(NotificationCompat.EXTRA_MESSAGES, Message.getBundleArrayForMessages(this.mMessages));
            }
            if (this.mIsGroupConversation != null) {
                extras.putBoolean(NotificationCompat.EXTRA_IS_GROUP_CONVERSATION, this.mIsGroupConversation.booleanValue());
            }
        }

        /* access modifiers changed from: protected */
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void restoreFromCompatExtras(Bundle bundle) {
            Person.Builder builder;
            Bundle extras = bundle;
            this.mMessages.clear();
            if (extras.containsKey(NotificationCompat.EXTRA_MESSAGING_STYLE_USER)) {
                this.mUser = Person.fromBundle(extras.getBundle(NotificationCompat.EXTRA_MESSAGING_STYLE_USER));
            } else {
                new Person.Builder();
                this.mUser = builder.setName(extras.getString(NotificationCompat.EXTRA_SELF_DISPLAY_NAME)).build();
            }
            this.mConversationTitle = extras.getCharSequence(NotificationCompat.EXTRA_CONVERSATION_TITLE);
            if (this.mConversationTitle == null) {
                this.mConversationTitle = extras.getCharSequence(NotificationCompat.EXTRA_HIDDEN_CONVERSATION_TITLE);
            }
            Parcelable[] parcelables = extras.getParcelableArray(NotificationCompat.EXTRA_MESSAGES);
            if (parcelables != null) {
                boolean addAll = this.mMessages.addAll(Message.getMessagesFromBundleArray(parcelables));
            }
            if (extras.containsKey(NotificationCompat.EXTRA_IS_GROUP_CONVERSATION)) {
                this.mIsGroupConversation = Boolean.valueOf(extras.getBoolean(NotificationCompat.EXTRA_IS_GROUP_CONVERSATION));
            }
        }

        /* renamed from: android.support.v4.app.NotificationCompat$MessagingStyle$Message */
        public static final class Message {
            static final String KEY_DATA_MIME_TYPE = "type";
            static final String KEY_DATA_URI = "uri";
            static final String KEY_EXTRAS_BUNDLE = "extras";
            static final String KEY_NOTIFICATION_PERSON = "sender_person";
            static final String KEY_PERSON = "person";
            static final String KEY_SENDER = "sender";
            static final String KEY_TEXT = "text";
            static final String KEY_TIMESTAMP = "time";
            @Nullable
            private String mDataMimeType;
            @Nullable
            private Uri mDataUri;
            private Bundle mExtras;
            @Nullable
            private final Person mPerson;
            private final CharSequence mText;
            private final long mTimestamp;

            public Message(CharSequence text, long timestamp, @Nullable Person person) {
                Bundle bundle;
                new Bundle();
                this.mExtras = bundle;
                this.mText = text;
                this.mTimestamp = timestamp;
                this.mPerson = person;
            }

            /* JADX WARNING: Illegal instructions before constructor call */
            @java.lang.Deprecated
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public Message(java.lang.CharSequence r13, long r14, java.lang.CharSequence r16) {
                /*
                    r12 = this;
                    r0 = r12
                    r1 = r13
                    r2 = r14
                    r4 = r16
                    r5 = r0
                    r6 = r1
                    r7 = r2
                    android.support.v4.app.Person$Builder r9 = new android.support.v4.app.Person$Builder
                    r11 = r9
                    r9 = r11
                    r10 = r11
                    r10.<init>()
                    r10 = r4
                    android.support.v4.app.Person$Builder r9 = r9.setName(r10)
                    android.support.v4.app.Person r9 = r9.build()
                    r5.<init>((java.lang.CharSequence) r6, (long) r7, (android.support.p000v4.app.Person) r9)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.app.NotificationCompat.MessagingStyle.Message.<init>(java.lang.CharSequence, long, java.lang.CharSequence):void");
            }

            public Message setData(String dataMimeType, Uri dataUri) {
                this.mDataMimeType = dataMimeType;
                this.mDataUri = dataUri;
                return this;
            }

            @NonNull
            public CharSequence getText() {
                return this.mText;
            }

            public long getTimestamp() {
                return this.mTimestamp;
            }

            @NonNull
            public Bundle getExtras() {
                return this.mExtras;
            }

            @Nullable
            @Deprecated
            public CharSequence getSender() {
                return this.mPerson == null ? null : this.mPerson.getName();
            }

            @Nullable
            public Person getPerson() {
                return this.mPerson;
            }

            @Nullable
            public String getDataMimeType() {
                return this.mDataMimeType;
            }

            @Nullable
            public Uri getDataUri() {
                return this.mDataUri;
            }

            private Bundle toBundle() {
                Bundle bundle;
                new Bundle();
                Bundle bundle2 = bundle;
                if (this.mText != null) {
                    bundle2.putCharSequence("text", this.mText);
                }
                bundle2.putLong(KEY_TIMESTAMP, this.mTimestamp);
                if (this.mPerson != null) {
                    bundle2.putCharSequence(KEY_SENDER, this.mPerson.getName());
                    if (Build.VERSION.SDK_INT >= 28) {
                        bundle2.putParcelable(KEY_NOTIFICATION_PERSON, this.mPerson.toAndroidPerson());
                    } else {
                        bundle2.putBundle(KEY_PERSON, this.mPerson.toBundle());
                    }
                }
                if (this.mDataMimeType != null) {
                    bundle2.putString(KEY_DATA_MIME_TYPE, this.mDataMimeType);
                }
                if (this.mDataUri != null) {
                    bundle2.putParcelable(KEY_DATA_URI, this.mDataUri);
                }
                if (this.mExtras != null) {
                    bundle2.putBundle(KEY_EXTRAS_BUNDLE, this.mExtras);
                }
                return bundle2;
            }

            @NonNull
            static Bundle[] getBundleArrayForMessages(List<Message> list) {
                List<Message> messages = list;
                Bundle[] bundles = new Bundle[messages.size()];
                int N = messages.size();
                for (int i = 0; i < N; i++) {
                    bundles[i] = messages.get(i).toBundle();
                }
                return bundles;
            }

            @NonNull
            static List<Message> getMessagesFromBundleArray(Parcelable[] parcelableArr) {
                List<Message> list;
                Message message;
                Parcelable[] bundles = parcelableArr;
                new ArrayList(bundles.length);
                List<Message> messages = list;
                for (int i = 0; i < bundles.length; i++) {
                    if ((bundles[i] instanceof Bundle) && (message = getMessageFromBundle((Bundle) bundles[i])) != null) {
                        boolean add = messages.add(message);
                    }
                }
                return messages;
            }

            @Nullable
            static Message getMessageFromBundle(Bundle bundle) {
                Person.Builder builder;
                Message message;
                Bundle bundle2 = bundle;
                try {
                    if (!bundle2.containsKey("text") || !bundle2.containsKey(KEY_TIMESTAMP)) {
                        return null;
                    }
                    Person person = null;
                    if (bundle2.containsKey(KEY_PERSON)) {
                        person = Person.fromBundle(bundle2.getBundle(KEY_PERSON));
                    } else if (bundle2.containsKey(KEY_NOTIFICATION_PERSON) && Build.VERSION.SDK_INT >= 28) {
                        person = Person.fromAndroidPerson((android.app.Person) bundle2.getParcelable(KEY_NOTIFICATION_PERSON));
                    } else if (bundle2.containsKey(KEY_SENDER)) {
                        new Person.Builder();
                        person = builder.setName(bundle2.getCharSequence(KEY_SENDER)).build();
                    }
                    new Message(bundle2.getCharSequence("text"), bundle2.getLong(KEY_TIMESTAMP), person);
                    Message message2 = message;
                    if (bundle2.containsKey(KEY_DATA_MIME_TYPE) && bundle2.containsKey(KEY_DATA_URI)) {
                        Message data = message2.setData(bundle2.getString(KEY_DATA_MIME_TYPE), (Uri) bundle2.getParcelable(KEY_DATA_URI));
                    }
                    if (bundle2.containsKey(KEY_EXTRAS_BUNDLE)) {
                        message2.getExtras().putAll(bundle2.getBundle(KEY_EXTRAS_BUNDLE));
                    }
                    return message2;
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    return null;
                }
            }
        }
    }

    /* renamed from: android.support.v4.app.NotificationCompat$InboxStyle */
    public static class InboxStyle extends Style {
        private ArrayList<CharSequence> mTexts;

        public InboxStyle() {
            ArrayList<CharSequence> arrayList;
            new ArrayList<>();
            this.mTexts = arrayList;
        }

        public InboxStyle(Builder builder) {
            ArrayList<CharSequence> arrayList;
            new ArrayList<>();
            this.mTexts = arrayList;
            setBuilder(builder);
        }

        public InboxStyle setBigContentTitle(CharSequence title) {
            this.mBigContentTitle = Builder.limitCharSequenceLength(title);
            return this;
        }

        public InboxStyle setSummaryText(CharSequence cs) {
            this.mSummaryText = Builder.limitCharSequenceLength(cs);
            this.mSummaryTextSet = true;
            return this;
        }

        public InboxStyle addLine(CharSequence cs) {
            boolean add = this.mTexts.add(Builder.limitCharSequenceLength(cs));
            return this;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            Notification.InboxStyle inboxStyle;
            NotificationBuilderWithBuilderAccessor builder = notificationBuilderWithBuilderAccessor;
            if (Build.VERSION.SDK_INT >= 16) {
                new Notification.InboxStyle(builder.getBuilder());
                Notification.InboxStyle style = inboxStyle.setBigContentTitle(this.mBigContentTitle);
                if (this.mSummaryTextSet) {
                    Notification.InboxStyle summaryText = style.setSummaryText(this.mSummaryText);
                }
                Iterator<CharSequence> it = this.mTexts.iterator();
                while (it.hasNext()) {
                    Notification.InboxStyle addLine = style.addLine(it.next());
                }
            }
        }
    }

    /* renamed from: android.support.v4.app.NotificationCompat$DecoratedCustomViewStyle */
    public static class DecoratedCustomViewStyle extends Style {
        private static final int MAX_ACTION_BUTTONS = 3;

        public DecoratedCustomViewStyle() {
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void apply(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            Notification.Style style;
            NotificationBuilderWithBuilderAccessor builder = notificationBuilderWithBuilderAccessor;
            if (Build.VERSION.SDK_INT >= 24) {
                new Notification.DecoratedCustomViewStyle();
                Notification.Builder style2 = builder.getBuilder().setStyle(style);
            }
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews makeContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor2 = notificationBuilderWithBuilderAccessor;
            if (Build.VERSION.SDK_INT >= 24) {
                return null;
            }
            if (this.mBuilder.getContentView() == null) {
                return null;
            }
            return createRemoteViews(this.mBuilder.getContentView(), false);
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews makeBigContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            RemoteViews contentView;
            NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor2 = notificationBuilderWithBuilderAccessor;
            if (Build.VERSION.SDK_INT >= 24) {
                return null;
            }
            RemoteViews bigContentView = this.mBuilder.getBigContentView();
            if (bigContentView != null) {
                contentView = bigContentView;
            } else {
                contentView = this.mBuilder.getContentView();
            }
            RemoteViews innerView = contentView;
            if (innerView == null) {
                return null;
            }
            return createRemoteViews(innerView, true);
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteViews makeHeadsUpContentView(NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor2 = notificationBuilderWithBuilderAccessor;
            if (Build.VERSION.SDK_INT >= 24) {
                return null;
            }
            RemoteViews headsUp = this.mBuilder.getHeadsUpContentView();
            RemoteViews innerView = headsUp != null ? headsUp : this.mBuilder.getContentView();
            if (headsUp == null) {
                return null;
            }
            return createRemoteViews(innerView, true);
        }

        private RemoteViews createRemoteViews(RemoteViews remoteViews, boolean showActions) {
            int numActions;
            RemoteViews innerView = remoteViews;
            RemoteViews remoteViews2 = applyStandardTemplate(true, C0020R.layout.notification_template_custom_big, false);
            remoteViews2.removeAllViews(C0020R.C0022id.actions);
            boolean actionsVisible = false;
            if (showActions && this.mBuilder.mActions != null && (numActions = Math.min(this.mBuilder.mActions.size(), 3)) > 0) {
                actionsVisible = true;
                for (int i = 0; i < numActions; i++) {
                    remoteViews2.addView(C0020R.C0022id.actions, generateActionButton(this.mBuilder.mActions.get(i)));
                }
            }
            int actionVisibility = actionsVisible ? 0 : 8;
            remoteViews2.setViewVisibility(C0020R.C0022id.actions, actionVisibility);
            remoteViews2.setViewVisibility(C0020R.C0022id.action_divider, actionVisibility);
            buildIntoRemoteViews(remoteViews2, innerView);
            return remoteViews2;
        }

        private RemoteViews generateActionButton(Action action) {
            Action action2 = action;
            boolean tombstone = action2.actionIntent == null;
            RemoteViews remoteViews = r10;
            RemoteViews remoteViews2 = new RemoteViews(this.mBuilder.mContext.getPackageName(), tombstone ? C0020R.layout.notification_action_tombstone : C0020R.layout.notification_action);
            RemoteViews button = remoteViews;
            button.setImageViewBitmap(C0020R.C0022id.action_image, createColoredBitmap(action2.getIcon(), this.mBuilder.mContext.getResources().getColor(C0020R.color.notification_action_color_filter)));
            button.setTextViewText(C0020R.C0022id.action_text, action2.title);
            if (!tombstone) {
                button.setOnClickPendingIntent(C0020R.C0022id.action_container, action2.actionIntent);
            }
            if (Build.VERSION.SDK_INT >= 15) {
                button.setContentDescription(C0020R.C0022id.action_container, action2.title);
            }
            return button;
        }
    }

    /* renamed from: android.support.v4.app.NotificationCompat$Action */
    public static class Action {
        static final String EXTRA_SEMANTIC_ACTION = "android.support.action.semanticAction";
        static final String EXTRA_SHOWS_USER_INTERFACE = "android.support.action.showsUserInterface";
        public static final int SEMANTIC_ACTION_ARCHIVE = 5;
        public static final int SEMANTIC_ACTION_CALL = 10;
        public static final int SEMANTIC_ACTION_DELETE = 4;
        public static final int SEMANTIC_ACTION_MARK_AS_READ = 2;
        public static final int SEMANTIC_ACTION_MARK_AS_UNREAD = 3;
        public static final int SEMANTIC_ACTION_MUTE = 6;
        public static final int SEMANTIC_ACTION_NONE = 0;
        public static final int SEMANTIC_ACTION_REPLY = 1;
        public static final int SEMANTIC_ACTION_THUMBS_DOWN = 9;
        public static final int SEMANTIC_ACTION_THUMBS_UP = 8;
        public static final int SEMANTIC_ACTION_UNMUTE = 7;
        public PendingIntent actionIntent;
        public int icon;
        private boolean mAllowGeneratedReplies;
        private final RemoteInput[] mDataOnlyRemoteInputs;
        final Bundle mExtras;
        private final RemoteInput[] mRemoteInputs;
        private final int mSemanticAction;
        boolean mShowsUserInterface;
        public CharSequence title;

        /* renamed from: android.support.v4.app.NotificationCompat$Action$Extender */
        public interface Extender {
            Builder extend(Builder builder);
        }

        @Retention(RetentionPolicy.SOURCE)
        /* renamed from: android.support.v4.app.NotificationCompat$Action$SemanticAction */
        public @interface SemanticAction {
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public Action(int r16, java.lang.CharSequence r17, android.app.PendingIntent r18) {
            /*
                r15 = this;
                r0 = r15
                r1 = r16
                r2 = r17
                r3 = r18
                r4 = r0
                r5 = r1
                r6 = r2
                r7 = r3
                android.os.Bundle r8 = new android.os.Bundle
                r14 = r8
                r8 = r14
                r9 = r14
                r9.<init>()
                r9 = 0
                r10 = 0
                r11 = 1
                r12 = 0
                r13 = 1
                r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r13)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.app.NotificationCompat.Action.<init>(int, java.lang.CharSequence, android.app.PendingIntent):void");
        }

        Action(int icon2, CharSequence title2, PendingIntent intent, Bundle bundle, RemoteInput[] remoteInputArr, RemoteInput[] remoteInputArr2, boolean z, int i, boolean z2) {
            Bundle bundle2;
            Bundle bundle3;
            Bundle extras = bundle;
            RemoteInput[] remoteInputs = remoteInputArr;
            RemoteInput[] dataOnlyRemoteInputs = remoteInputArr2;
            boolean allowGeneratedReplies = z;
            int semanticAction = i;
            boolean showsUserInterface = z2;
            this.mShowsUserInterface = true;
            this.icon = icon2;
            this.title = Builder.limitCharSequenceLength(title2);
            this.actionIntent = intent;
            if (extras != null) {
                bundle3 = extras;
            } else {
                bundle3 = bundle2;
                new Bundle();
            }
            this.mExtras = bundle3;
            this.mRemoteInputs = remoteInputs;
            this.mDataOnlyRemoteInputs = dataOnlyRemoteInputs;
            this.mAllowGeneratedReplies = allowGeneratedReplies;
            this.mSemanticAction = semanticAction;
            this.mShowsUserInterface = showsUserInterface;
        }

        public int getIcon() {
            return this.icon;
        }

        public CharSequence getTitle() {
            return this.title;
        }

        public PendingIntent getActionIntent() {
            return this.actionIntent;
        }

        public Bundle getExtras() {
            return this.mExtras;
        }

        public boolean getAllowGeneratedReplies() {
            return this.mAllowGeneratedReplies;
        }

        public RemoteInput[] getRemoteInputs() {
            return this.mRemoteInputs;
        }

        public int getSemanticAction() {
            return this.mSemanticAction;
        }

        public RemoteInput[] getDataOnlyRemoteInputs() {
            return this.mDataOnlyRemoteInputs;
        }

        public boolean getShowsUserInterface() {
            return this.mShowsUserInterface;
        }

        /* renamed from: android.support.v4.app.NotificationCompat$Action$Builder */
        public static final class Builder {
            private boolean mAllowGeneratedReplies;
            private final Bundle mExtras;
            private final int mIcon;
            private final PendingIntent mIntent;
            private ArrayList<RemoteInput> mRemoteInputs;
            private int mSemanticAction;
            private boolean mShowsUserInterface;
            private final CharSequence mTitle;

            /* JADX WARNING: Illegal instructions before constructor call */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public Builder(int r15, java.lang.CharSequence r16, android.app.PendingIntent r17) {
                /*
                    r14 = this;
                    r0 = r14
                    r1 = r15
                    r2 = r16
                    r3 = r17
                    r4 = r0
                    r5 = r1
                    r6 = r2
                    r7 = r3
                    android.os.Bundle r8 = new android.os.Bundle
                    r13 = r8
                    r8 = r13
                    r9 = r13
                    r9.<init>()
                    r9 = 0
                    r10 = 1
                    r11 = 0
                    r12 = 1
                    r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.app.NotificationCompat.Action.Builder.<init>(int, java.lang.CharSequence, android.app.PendingIntent):void");
            }

            /* JADX WARNING: Illegal instructions before constructor call */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public Builder(android.support.p000v4.app.NotificationCompat.Action r13) {
                /*
                    r12 = this;
                    r0 = r12
                    r1 = r13
                    r2 = r0
                    r3 = r1
                    int r3 = r3.icon
                    r4 = r1
                    java.lang.CharSequence r4 = r4.title
                    r5 = r1
                    android.app.PendingIntent r5 = r5.actionIntent
                    android.os.Bundle r6 = new android.os.Bundle
                    r11 = r6
                    r6 = r11
                    r7 = r11
                    r8 = r1
                    android.os.Bundle r8 = r8.mExtras
                    r7.<init>(r8)
                    r7 = r1
                    android.support.v4.app.RemoteInput[] r7 = r7.getRemoteInputs()
                    r8 = r1
                    boolean r8 = r8.getAllowGeneratedReplies()
                    r9 = r1
                    int r9 = r9.getSemanticAction()
                    r10 = r1
                    boolean r10 = r10.mShowsUserInterface
                    r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.app.NotificationCompat.Action.Builder.<init>(android.support.v4.app.NotificationCompat$Action):void");
            }

            private Builder(int icon, CharSequence title, PendingIntent intent, Bundle extras, RemoteInput[] remoteInputArr, boolean z, int i, boolean z2) {
                ArrayList<RemoteInput> arrayList;
                ArrayList<RemoteInput> arrayList2;
                RemoteInput[] remoteInputs = remoteInputArr;
                boolean allowGeneratedReplies = z;
                int semanticAction = i;
                boolean showsUserInterface = z2;
                this.mAllowGeneratedReplies = true;
                this.mShowsUserInterface = true;
                this.mIcon = icon;
                this.mTitle = Builder.limitCharSequenceLength(title);
                this.mIntent = intent;
                this.mExtras = extras;
                if (remoteInputs == null) {
                    arrayList2 = null;
                } else {
                    arrayList2 = arrayList;
                    new ArrayList<>(Arrays.asList(remoteInputs));
                }
                this.mRemoteInputs = arrayList2;
                this.mAllowGeneratedReplies = allowGeneratedReplies;
                this.mSemanticAction = semanticAction;
                this.mShowsUserInterface = showsUserInterface;
            }

            public Builder addExtras(Bundle bundle) {
                Bundle extras = bundle;
                if (extras != null) {
                    this.mExtras.putAll(extras);
                }
                return this;
            }

            public Bundle getExtras() {
                return this.mExtras;
            }

            public Builder addRemoteInput(RemoteInput remoteInput) {
                ArrayList<RemoteInput> arrayList;
                RemoteInput remoteInput2 = remoteInput;
                if (this.mRemoteInputs == null) {
                    new ArrayList<>();
                    this.mRemoteInputs = arrayList;
                }
                boolean add = this.mRemoteInputs.add(remoteInput2);
                return this;
            }

            public Builder setAllowGeneratedReplies(boolean allowGeneratedReplies) {
                this.mAllowGeneratedReplies = allowGeneratedReplies;
                return this;
            }

            public Builder setSemanticAction(int semanticAction) {
                this.mSemanticAction = semanticAction;
                return this;
            }

            public Builder setShowsUserInterface(boolean showsUserInterface) {
                this.mShowsUserInterface = showsUserInterface;
                return this;
            }

            public Builder extend(Extender extender) {
                Builder extend = extender.extend(this);
                return this;
            }

            public Action build() {
                List<RemoteInput> list;
                List<RemoteInput> list2;
                RemoteInput[] remoteInputArr;
                RemoteInput[] textInputsArr;
                Action action;
                new ArrayList<>();
                List<RemoteInput> dataOnlyInputs = list;
                new ArrayList<>();
                List<RemoteInput> textInputs = list2;
                if (this.mRemoteInputs != null) {
                    Iterator<RemoteInput> it = this.mRemoteInputs.iterator();
                    while (it.hasNext()) {
                        RemoteInput input = it.next();
                        if (input.isDataOnly()) {
                            boolean add = dataOnlyInputs.add(input);
                        } else {
                            boolean add2 = textInputs.add(input);
                        }
                    }
                }
                if (dataOnlyInputs.isEmpty()) {
                    remoteInputArr = null;
                } else {
                    remoteInputArr = (RemoteInput[]) dataOnlyInputs.toArray(new RemoteInput[dataOnlyInputs.size()]);
                }
                RemoteInput[] dataOnlyInputsArr = remoteInputArr;
                if (textInputs.isEmpty()) {
                    textInputsArr = null;
                } else {
                    textInputsArr = (RemoteInput[]) textInputs.toArray(new RemoteInput[textInputs.size()]);
                }
                new Action(this.mIcon, this.mTitle, this.mIntent, this.mExtras, textInputsArr, dataOnlyInputsArr, this.mAllowGeneratedReplies, this.mSemanticAction, this.mShowsUserInterface);
                return action;
            }
        }

        /* renamed from: android.support.v4.app.NotificationCompat$Action$WearableExtender */
        public static final class WearableExtender implements Extender {
            private static final int DEFAULT_FLAGS = 1;
            private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
            private static final int FLAG_AVAILABLE_OFFLINE = 1;
            private static final int FLAG_HINT_DISPLAY_INLINE = 4;
            private static final int FLAG_HINT_LAUNCHES_ACTIVITY = 2;
            private static final String KEY_CANCEL_LABEL = "cancelLabel";
            private static final String KEY_CONFIRM_LABEL = "confirmLabel";
            private static final String KEY_FLAGS = "flags";
            private static final String KEY_IN_PROGRESS_LABEL = "inProgressLabel";
            private CharSequence mCancelLabel;
            private CharSequence mConfirmLabel;
            private int mFlags = 1;
            private CharSequence mInProgressLabel;

            public WearableExtender() {
            }

            public WearableExtender(Action action) {
                Bundle wearableBundle = action.getExtras().getBundle(EXTRA_WEARABLE_EXTENSIONS);
                if (wearableBundle != null) {
                    this.mFlags = wearableBundle.getInt(KEY_FLAGS, 1);
                    this.mInProgressLabel = wearableBundle.getCharSequence(KEY_IN_PROGRESS_LABEL);
                    this.mConfirmLabel = wearableBundle.getCharSequence(KEY_CONFIRM_LABEL);
                    this.mCancelLabel = wearableBundle.getCharSequence(KEY_CANCEL_LABEL);
                }
            }

            public Builder extend(Builder builder) {
                Bundle bundle;
                Builder builder2 = builder;
                new Bundle();
                Bundle wearableBundle = bundle;
                if (this.mFlags != 1) {
                    wearableBundle.putInt(KEY_FLAGS, this.mFlags);
                }
                if (this.mInProgressLabel != null) {
                    wearableBundle.putCharSequence(KEY_IN_PROGRESS_LABEL, this.mInProgressLabel);
                }
                if (this.mConfirmLabel != null) {
                    wearableBundle.putCharSequence(KEY_CONFIRM_LABEL, this.mConfirmLabel);
                }
                if (this.mCancelLabel != null) {
                    wearableBundle.putCharSequence(KEY_CANCEL_LABEL, this.mCancelLabel);
                }
                builder2.getExtras().putBundle(EXTRA_WEARABLE_EXTENSIONS, wearableBundle);
                return builder2;
            }

            public WearableExtender clone() {
                WearableExtender wearableExtender;
                new WearableExtender();
                WearableExtender that = wearableExtender;
                that.mFlags = this.mFlags;
                that.mInProgressLabel = this.mInProgressLabel;
                that.mConfirmLabel = this.mConfirmLabel;
                that.mCancelLabel = this.mCancelLabel;
                return that;
            }

            public WearableExtender setAvailableOffline(boolean availableOffline) {
                setFlag(1, availableOffline);
                return this;
            }

            public boolean isAvailableOffline() {
                return (this.mFlags & 1) != 0;
            }

            private void setFlag(int i, boolean value) {
                int mask = i;
                if (value) {
                    this.mFlags |= mask;
                    return;
                }
                this.mFlags &= mask ^ -1;
            }

            @Deprecated
            public WearableExtender setInProgressLabel(CharSequence label) {
                this.mInProgressLabel = label;
                return this;
            }

            @Deprecated
            public CharSequence getInProgressLabel() {
                return this.mInProgressLabel;
            }

            @Deprecated
            public WearableExtender setConfirmLabel(CharSequence label) {
                this.mConfirmLabel = label;
                return this;
            }

            @Deprecated
            public CharSequence getConfirmLabel() {
                return this.mConfirmLabel;
            }

            @Deprecated
            public WearableExtender setCancelLabel(CharSequence label) {
                this.mCancelLabel = label;
                return this;
            }

            @Deprecated
            public CharSequence getCancelLabel() {
                return this.mCancelLabel;
            }

            public WearableExtender setHintLaunchesActivity(boolean hintLaunchesActivity) {
                setFlag(2, hintLaunchesActivity);
                return this;
            }

            public boolean getHintLaunchesActivity() {
                return (this.mFlags & 2) != 0;
            }

            public WearableExtender setHintDisplayActionInline(boolean hintDisplayInline) {
                setFlag(4, hintDisplayInline);
                return this;
            }

            public boolean getHintDisplayActionInline() {
                return (this.mFlags & 4) != 0;
            }
        }
    }

    /* renamed from: android.support.v4.app.NotificationCompat$WearableExtender */
    public static final class WearableExtender implements Extender {
        private static final int DEFAULT_CONTENT_ICON_GRAVITY = 8388613;
        private static final int DEFAULT_FLAGS = 1;
        private static final int DEFAULT_GRAVITY = 80;
        private static final String EXTRA_WEARABLE_EXTENSIONS = "android.wearable.EXTENSIONS";
        private static final int FLAG_BIG_PICTURE_AMBIENT = 32;
        private static final int FLAG_CONTENT_INTENT_AVAILABLE_OFFLINE = 1;
        private static final int FLAG_HINT_AVOID_BACKGROUND_CLIPPING = 16;
        private static final int FLAG_HINT_CONTENT_INTENT_LAUNCHES_ACTIVITY = 64;
        private static final int FLAG_HINT_HIDE_ICON = 2;
        private static final int FLAG_HINT_SHOW_BACKGROUND_ONLY = 4;
        private static final int FLAG_START_SCROLL_BOTTOM = 8;
        private static final String KEY_ACTIONS = "actions";
        private static final String KEY_BACKGROUND = "background";
        private static final String KEY_BRIDGE_TAG = "bridgeTag";
        private static final String KEY_CONTENT_ACTION_INDEX = "contentActionIndex";
        private static final String KEY_CONTENT_ICON = "contentIcon";
        private static final String KEY_CONTENT_ICON_GRAVITY = "contentIconGravity";
        private static final String KEY_CUSTOM_CONTENT_HEIGHT = "customContentHeight";
        private static final String KEY_CUSTOM_SIZE_PRESET = "customSizePreset";
        private static final String KEY_DISMISSAL_ID = "dismissalId";
        private static final String KEY_DISPLAY_INTENT = "displayIntent";
        private static final String KEY_FLAGS = "flags";
        private static final String KEY_GRAVITY = "gravity";
        private static final String KEY_HINT_SCREEN_TIMEOUT = "hintScreenTimeout";
        private static final String KEY_PAGES = "pages";
        public static final int SCREEN_TIMEOUT_LONG = -1;
        public static final int SCREEN_TIMEOUT_SHORT = 0;
        public static final int SIZE_DEFAULT = 0;
        public static final int SIZE_FULL_SCREEN = 5;
        public static final int SIZE_LARGE = 4;
        public static final int SIZE_MEDIUM = 3;
        public static final int SIZE_SMALL = 2;
        public static final int SIZE_XSMALL = 1;
        public static final int UNSET_ACTION_INDEX = -1;
        private ArrayList<Action> mActions;
        private Bitmap mBackground;
        private String mBridgeTag;
        private int mContentActionIndex;
        private int mContentIcon;
        private int mContentIconGravity;
        private int mCustomContentHeight;
        private int mCustomSizePreset;
        private String mDismissalId;
        private PendingIntent mDisplayIntent;
        private int mFlags = 1;
        private int mGravity;
        private int mHintScreenTimeout;
        private ArrayList<Notification> mPages;

        public WearableExtender() {
            ArrayList<Action> arrayList;
            ArrayList<Notification> arrayList2;
            new ArrayList<>();
            this.mActions = arrayList;
            new ArrayList<>();
            this.mPages = arrayList2;
            this.mContentIconGravity = 8388613;
            this.mContentActionIndex = -1;
            this.mCustomSizePreset = 0;
            this.mGravity = 80;
        }

        public WearableExtender(Notification notification) {
            ArrayList<Action> arrayList;
            ArrayList<Notification> arrayList2;
            new ArrayList<>();
            this.mActions = arrayList;
            new ArrayList<>();
            this.mPages = arrayList2;
            this.mContentIconGravity = 8388613;
            this.mContentActionIndex = -1;
            this.mCustomSizePreset = 0;
            this.mGravity = 80;
            Bundle extras = NotificationCompat.getExtras(notification);
            Bundle wearableBundle = extras != null ? extras.getBundle(EXTRA_WEARABLE_EXTENSIONS) : null;
            if (wearableBundle != null) {
                ArrayList<Parcelable> parcelables = wearableBundle.getParcelableArrayList(KEY_ACTIONS);
                if (Build.VERSION.SDK_INT >= 16 && parcelables != null) {
                    Action[] actions = new Action[parcelables.size()];
                    for (int i = 0; i < actions.length; i++) {
                        if (Build.VERSION.SDK_INT >= 20) {
                            actions[i] = NotificationCompat.getActionCompatFromAction((Notification.Action) parcelables.get(i));
                        } else if (Build.VERSION.SDK_INT >= 16) {
                            actions[i] = NotificationCompatJellybean.getActionFromBundle((Bundle) parcelables.get(i));
                        }
                    }
                    boolean addAll = Collections.addAll(this.mActions, actions);
                }
                this.mFlags = wearableBundle.getInt(KEY_FLAGS, 1);
                this.mDisplayIntent = (PendingIntent) wearableBundle.getParcelable(KEY_DISPLAY_INTENT);
                Notification[] pages = NotificationCompat.getNotificationArrayFromBundle(wearableBundle, KEY_PAGES);
                if (pages != null) {
                    boolean addAll2 = Collections.addAll(this.mPages, pages);
                }
                this.mBackground = (Bitmap) wearableBundle.getParcelable(KEY_BACKGROUND);
                this.mContentIcon = wearableBundle.getInt(KEY_CONTENT_ICON);
                this.mContentIconGravity = wearableBundle.getInt(KEY_CONTENT_ICON_GRAVITY, 8388613);
                this.mContentActionIndex = wearableBundle.getInt(KEY_CONTENT_ACTION_INDEX, -1);
                this.mCustomSizePreset = wearableBundle.getInt(KEY_CUSTOM_SIZE_PRESET, 0);
                this.mCustomContentHeight = wearableBundle.getInt(KEY_CUSTOM_CONTENT_HEIGHT);
                this.mGravity = wearableBundle.getInt(KEY_GRAVITY, 80);
                this.mHintScreenTimeout = wearableBundle.getInt(KEY_HINT_SCREEN_TIMEOUT);
                this.mDismissalId = wearableBundle.getString(KEY_DISMISSAL_ID);
                this.mBridgeTag = wearableBundle.getString(KEY_BRIDGE_TAG);
            }
        }

        public Builder extend(Builder builder) {
            Bundle bundle;
            ArrayList arrayList;
            Builder builder2 = builder;
            new Bundle();
            Bundle wearableBundle = bundle;
            if (!this.mActions.isEmpty()) {
                if (Build.VERSION.SDK_INT >= 16) {
                    new ArrayList(this.mActions.size());
                    ArrayList arrayList2 = arrayList;
                    Iterator<Action> it = this.mActions.iterator();
                    while (it.hasNext()) {
                        Action action = it.next();
                        if (Build.VERSION.SDK_INT >= 20) {
                            boolean add = arrayList2.add(getActionFromActionCompat(action));
                        } else if (Build.VERSION.SDK_INT >= 16) {
                            boolean add2 = arrayList2.add(NotificationCompatJellybean.getBundleForAction(action));
                        }
                    }
                    wearableBundle.putParcelableArrayList(KEY_ACTIONS, arrayList2);
                } else {
                    wearableBundle.putParcelableArrayList(KEY_ACTIONS, (ArrayList) null);
                }
            }
            if (this.mFlags != 1) {
                wearableBundle.putInt(KEY_FLAGS, this.mFlags);
            }
            if (this.mDisplayIntent != null) {
                wearableBundle.putParcelable(KEY_DISPLAY_INTENT, this.mDisplayIntent);
            }
            if (!this.mPages.isEmpty()) {
                wearableBundle.putParcelableArray(KEY_PAGES, (Parcelable[]) this.mPages.toArray(new Notification[this.mPages.size()]));
            }
            if (this.mBackground != null) {
                wearableBundle.putParcelable(KEY_BACKGROUND, this.mBackground);
            }
            if (this.mContentIcon != 0) {
                wearableBundle.putInt(KEY_CONTENT_ICON, this.mContentIcon);
            }
            if (this.mContentIconGravity != 8388613) {
                wearableBundle.putInt(KEY_CONTENT_ICON_GRAVITY, this.mContentIconGravity);
            }
            if (this.mContentActionIndex != -1) {
                wearableBundle.putInt(KEY_CONTENT_ACTION_INDEX, this.mContentActionIndex);
            }
            if (this.mCustomSizePreset != 0) {
                wearableBundle.putInt(KEY_CUSTOM_SIZE_PRESET, this.mCustomSizePreset);
            }
            if (this.mCustomContentHeight != 0) {
                wearableBundle.putInt(KEY_CUSTOM_CONTENT_HEIGHT, this.mCustomContentHeight);
            }
            if (this.mGravity != 80) {
                wearableBundle.putInt(KEY_GRAVITY, this.mGravity);
            }
            if (this.mHintScreenTimeout != 0) {
                wearableBundle.putInt(KEY_HINT_SCREEN_TIMEOUT, this.mHintScreenTimeout);
            }
            if (this.mDismissalId != null) {
                wearableBundle.putString(KEY_DISMISSAL_ID, this.mDismissalId);
            }
            if (this.mBridgeTag != null) {
                wearableBundle.putString(KEY_BRIDGE_TAG, this.mBridgeTag);
            }
            builder2.getExtras().putBundle(EXTRA_WEARABLE_EXTENSIONS, wearableBundle);
            return builder2;
        }

        @RequiresApi(20)
        private static Notification.Action getActionFromActionCompat(Action action) {
            Notification.Action.Builder builder;
            Bundle bundle;
            Bundle actionExtras;
            Bundle bundle2;
            Action actionCompat = action;
            new Notification.Action.Builder(actionCompat.getIcon(), actionCompat.getTitle(), actionCompat.getActionIntent());
            Notification.Action.Builder actionBuilder = builder;
            if (actionCompat.getExtras() != null) {
                new Bundle(actionCompat.getExtras());
                actionExtras = bundle2;
            } else {
                new Bundle();
                actionExtras = bundle;
            }
            actionExtras.putBoolean("android.support.allowGeneratedReplies", actionCompat.getAllowGeneratedReplies());
            if (Build.VERSION.SDK_INT >= 24) {
                Notification.Action.Builder allowGeneratedReplies = actionBuilder.setAllowGeneratedReplies(actionCompat.getAllowGeneratedReplies());
            }
            Notification.Action.Builder addExtras = actionBuilder.addExtras(actionExtras);
            RemoteInput[] remoteInputCompats = actionCompat.getRemoteInputs();
            if (remoteInputCompats != null) {
                RemoteInput[] fromCompat = RemoteInput.fromCompat(remoteInputCompats);
                int length = fromCompat.length;
                for (int i = 0; i < length; i++) {
                    Notification.Action.Builder addRemoteInput = actionBuilder.addRemoteInput(fromCompat[i]);
                }
            }
            return actionBuilder.build();
        }

        public WearableExtender clone() {
            WearableExtender wearableExtender;
            ArrayList<Action> arrayList;
            ArrayList<Notification> arrayList2;
            new WearableExtender();
            WearableExtender that = wearableExtender;
            new ArrayList<>(this.mActions);
            that.mActions = arrayList;
            that.mFlags = this.mFlags;
            that.mDisplayIntent = this.mDisplayIntent;
            new ArrayList<>(this.mPages);
            that.mPages = arrayList2;
            that.mBackground = this.mBackground;
            that.mContentIcon = this.mContentIcon;
            that.mContentIconGravity = this.mContentIconGravity;
            that.mContentActionIndex = this.mContentActionIndex;
            that.mCustomSizePreset = this.mCustomSizePreset;
            that.mCustomContentHeight = this.mCustomContentHeight;
            that.mGravity = this.mGravity;
            that.mHintScreenTimeout = this.mHintScreenTimeout;
            that.mDismissalId = this.mDismissalId;
            that.mBridgeTag = this.mBridgeTag;
            return that;
        }

        public WearableExtender addAction(Action action) {
            boolean add = this.mActions.add(action);
            return this;
        }

        public WearableExtender addActions(List<Action> actions) {
            boolean addAll = this.mActions.addAll(actions);
            return this;
        }

        public WearableExtender clearActions() {
            this.mActions.clear();
            return this;
        }

        public List<Action> getActions() {
            return this.mActions;
        }

        public WearableExtender setDisplayIntent(PendingIntent intent) {
            this.mDisplayIntent = intent;
            return this;
        }

        public PendingIntent getDisplayIntent() {
            return this.mDisplayIntent;
        }

        public WearableExtender addPage(Notification page) {
            boolean add = this.mPages.add(page);
            return this;
        }

        public WearableExtender addPages(List<Notification> pages) {
            boolean addAll = this.mPages.addAll(pages);
            return this;
        }

        public WearableExtender clearPages() {
            this.mPages.clear();
            return this;
        }

        public List<Notification> getPages() {
            return this.mPages;
        }

        public WearableExtender setBackground(Bitmap background) {
            this.mBackground = background;
            return this;
        }

        public Bitmap getBackground() {
            return this.mBackground;
        }

        @Deprecated
        public WearableExtender setContentIcon(int icon) {
            this.mContentIcon = icon;
            return this;
        }

        @Deprecated
        public int getContentIcon() {
            return this.mContentIcon;
        }

        @Deprecated
        public WearableExtender setContentIconGravity(int contentIconGravity) {
            this.mContentIconGravity = contentIconGravity;
            return this;
        }

        @Deprecated
        public int getContentIconGravity() {
            return this.mContentIconGravity;
        }

        public WearableExtender setContentAction(int actionIndex) {
            this.mContentActionIndex = actionIndex;
            return this;
        }

        public int getContentAction() {
            return this.mContentActionIndex;
        }

        @Deprecated
        public WearableExtender setGravity(int gravity) {
            this.mGravity = gravity;
            return this;
        }

        @Deprecated
        public int getGravity() {
            return this.mGravity;
        }

        @Deprecated
        public WearableExtender setCustomSizePreset(int sizePreset) {
            this.mCustomSizePreset = sizePreset;
            return this;
        }

        @Deprecated
        public int getCustomSizePreset() {
            return this.mCustomSizePreset;
        }

        @Deprecated
        public WearableExtender setCustomContentHeight(int height) {
            this.mCustomContentHeight = height;
            return this;
        }

        @Deprecated
        public int getCustomContentHeight() {
            return this.mCustomContentHeight;
        }

        public WearableExtender setStartScrollBottom(boolean startScrollBottom) {
            setFlag(8, startScrollBottom);
            return this;
        }

        public boolean getStartScrollBottom() {
            return (this.mFlags & 8) != 0;
        }

        public WearableExtender setContentIntentAvailableOffline(boolean contentIntentAvailableOffline) {
            setFlag(1, contentIntentAvailableOffline);
            return this;
        }

        public boolean getContentIntentAvailableOffline() {
            return (this.mFlags & 1) != 0;
        }

        @Deprecated
        public WearableExtender setHintHideIcon(boolean hintHideIcon) {
            setFlag(2, hintHideIcon);
            return this;
        }

        @Deprecated
        public boolean getHintHideIcon() {
            return (this.mFlags & 2) != 0;
        }

        @Deprecated
        public WearableExtender setHintShowBackgroundOnly(boolean hintShowBackgroundOnly) {
            setFlag(4, hintShowBackgroundOnly);
            return this;
        }

        @Deprecated
        public boolean getHintShowBackgroundOnly() {
            return (this.mFlags & 4) != 0;
        }

        @Deprecated
        public WearableExtender setHintAvoidBackgroundClipping(boolean hintAvoidBackgroundClipping) {
            setFlag(16, hintAvoidBackgroundClipping);
            return this;
        }

        @Deprecated
        public boolean getHintAvoidBackgroundClipping() {
            return (this.mFlags & 16) != 0;
        }

        @Deprecated
        public WearableExtender setHintScreenTimeout(int timeout) {
            this.mHintScreenTimeout = timeout;
            return this;
        }

        @Deprecated
        public int getHintScreenTimeout() {
            return this.mHintScreenTimeout;
        }

        public WearableExtender setHintAmbientBigPicture(boolean hintAmbientBigPicture) {
            setFlag(32, hintAmbientBigPicture);
            return this;
        }

        public boolean getHintAmbientBigPicture() {
            return (this.mFlags & 32) != 0;
        }

        public WearableExtender setHintContentIntentLaunchesActivity(boolean hintContentIntentLaunchesActivity) {
            setFlag(64, hintContentIntentLaunchesActivity);
            return this;
        }

        public boolean getHintContentIntentLaunchesActivity() {
            return (this.mFlags & 64) != 0;
        }

        public WearableExtender setDismissalId(String dismissalId) {
            this.mDismissalId = dismissalId;
            return this;
        }

        public String getDismissalId() {
            return this.mDismissalId;
        }

        public WearableExtender setBridgeTag(String bridgeTag) {
            this.mBridgeTag = bridgeTag;
            return this;
        }

        public String getBridgeTag() {
            return this.mBridgeTag;
        }

        private void setFlag(int i, boolean value) {
            int mask = i;
            if (value) {
                this.mFlags |= mask;
                return;
            }
            this.mFlags &= mask ^ -1;
        }
    }

    /* renamed from: android.support.v4.app.NotificationCompat$CarExtender */
    public static final class CarExtender implements Extender {
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        static final String EXTRA_CAR_EXTENDER = "android.car.EXTENSIONS";
        private static final String EXTRA_COLOR = "app_color";
        private static final String EXTRA_CONVERSATION = "car_conversation";
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        static final String EXTRA_INVISIBLE_ACTIONS = "invisible_actions";
        private static final String EXTRA_LARGE_ICON = "large_icon";
        private static final String KEY_AUTHOR = "author";
        private static final String KEY_MESSAGES = "messages";
        private static final String KEY_ON_READ = "on_read";
        private static final String KEY_ON_REPLY = "on_reply";
        private static final String KEY_PARTICIPANTS = "participants";
        private static final String KEY_REMOTE_INPUT = "remote_input";
        private static final String KEY_TEXT = "text";
        private static final String KEY_TIMESTAMP = "timestamp";
        private int mColor = 0;
        private Bitmap mLargeIcon;
        private UnreadConversation mUnreadConversation;

        public CarExtender() {
        }

        public CarExtender(Notification notification) {
            Bundle bundle;
            Notification notification2 = notification;
            if (Build.VERSION.SDK_INT >= 21) {
                if (NotificationCompat.getExtras(notification2) == null) {
                    bundle = null;
                } else {
                    bundle = NotificationCompat.getExtras(notification2).getBundle(EXTRA_CAR_EXTENDER);
                }
                Bundle carBundle = bundle;
                if (carBundle != null) {
                    this.mLargeIcon = (Bitmap) carBundle.getParcelable(EXTRA_LARGE_ICON);
                    this.mColor = carBundle.getInt(EXTRA_COLOR, 0);
                    this.mUnreadConversation = getUnreadConversationFromBundle(carBundle.getBundle(EXTRA_CONVERSATION));
                }
            }
        }

        @RequiresApi(21)
        private static UnreadConversation getUnreadConversationFromBundle(@Nullable Bundle bundle) {
            RemoteInput remoteInputCompat;
            UnreadConversation unreadConversation;
            RemoteInput remoteInput;
            Bundle b = bundle;
            if (b == null) {
                return null;
            }
            Parcelable[] parcelableMessages = b.getParcelableArray(KEY_MESSAGES);
            String[] messages = null;
            if (parcelableMessages != null) {
                String[] tmp = new String[parcelableMessages.length];
                boolean success = true;
                int i = 0;
                while (true) {
                    if (i >= tmp.length) {
                        break;
                    } else if (!(parcelableMessages[i] instanceof Bundle)) {
                        success = false;
                        break;
                    } else {
                        tmp[i] = ((Bundle) parcelableMessages[i]).getString("text");
                        if (tmp[i] == null) {
                            success = false;
                            break;
                        }
                        i++;
                    }
                }
                if (!success) {
                    return null;
                }
                messages = tmp;
            }
            PendingIntent onRead = (PendingIntent) b.getParcelable(KEY_ON_READ);
            PendingIntent onReply = (PendingIntent) b.getParcelable(KEY_ON_REPLY);
            RemoteInput remoteInput2 = (RemoteInput) b.getParcelable(KEY_REMOTE_INPUT);
            String[] participants = b.getStringArray(KEY_PARTICIPANTS);
            if (participants == null || participants.length != 1) {
                return null;
            }
            if (remoteInput2 != null) {
                remoteInputCompat = remoteInput;
                new RemoteInput(remoteInput2.getResultKey(), remoteInput2.getLabel(), remoteInput2.getChoices(), remoteInput2.getAllowFreeFormInput(), remoteInput2.getExtras(), (Set<String>) null);
            } else {
                remoteInputCompat = null;
            }
            new UnreadConversation(messages, remoteInputCompat, onReply, onRead, participants, b.getLong(KEY_TIMESTAMP));
            return unreadConversation;
        }

        @RequiresApi(21)
        private static Bundle getBundleForUnreadConversation(@NonNull UnreadConversation unreadConversation) {
            Bundle bundle;
            RemoteInput.Builder builder;
            Bundle bundle2;
            UnreadConversation uc = unreadConversation;
            new Bundle();
            Bundle b = bundle;
            String author = null;
            if (uc.getParticipants() != null && uc.getParticipants().length > 1) {
                author = uc.getParticipants()[0];
            }
            Parcelable[] messages = new Parcelable[uc.getMessages().length];
            for (int i = 0; i < messages.length; i++) {
                new Bundle();
                Bundle m = bundle2;
                m.putString("text", uc.getMessages()[i]);
                m.putString(KEY_AUTHOR, author);
                messages[i] = m;
            }
            b.putParcelableArray(KEY_MESSAGES, messages);
            RemoteInput remoteInputCompat = uc.getRemoteInput();
            if (remoteInputCompat != null) {
                new RemoteInput.Builder(remoteInputCompat.getResultKey());
                b.putParcelable(KEY_REMOTE_INPUT, builder.setLabel(remoteInputCompat.getLabel()).setChoices(remoteInputCompat.getChoices()).setAllowFreeFormInput(remoteInputCompat.getAllowFreeFormInput()).addExtras(remoteInputCompat.getExtras()).build());
            }
            b.putParcelable(KEY_ON_REPLY, uc.getReplyPendingIntent());
            b.putParcelable(KEY_ON_READ, uc.getReadPendingIntent());
            b.putStringArray(KEY_PARTICIPANTS, uc.getParticipants());
            b.putLong(KEY_TIMESTAMP, uc.getLatestTimestamp());
            return b;
        }

        public Builder extend(Builder builder) {
            Bundle bundle;
            Builder builder2 = builder;
            if (Build.VERSION.SDK_INT < 21) {
                return builder2;
            }
            new Bundle();
            Bundle carExtensions = bundle;
            if (this.mLargeIcon != null) {
                carExtensions.putParcelable(EXTRA_LARGE_ICON, this.mLargeIcon);
            }
            if (this.mColor != 0) {
                carExtensions.putInt(EXTRA_COLOR, this.mColor);
            }
            if (this.mUnreadConversation != null) {
                carExtensions.putBundle(EXTRA_CONVERSATION, getBundleForUnreadConversation(this.mUnreadConversation));
            }
            builder2.getExtras().putBundle(EXTRA_CAR_EXTENDER, carExtensions);
            return builder2;
        }

        public CarExtender setColor(@ColorInt int color) {
            this.mColor = color;
            return this;
        }

        @ColorInt
        public int getColor() {
            return this.mColor;
        }

        public CarExtender setLargeIcon(Bitmap largeIcon) {
            this.mLargeIcon = largeIcon;
            return this;
        }

        public Bitmap getLargeIcon() {
            return this.mLargeIcon;
        }

        public CarExtender setUnreadConversation(UnreadConversation unreadConversation) {
            this.mUnreadConversation = unreadConversation;
            return this;
        }

        public UnreadConversation getUnreadConversation() {
            return this.mUnreadConversation;
        }

        /* renamed from: android.support.v4.app.NotificationCompat$CarExtender$UnreadConversation */
        public static class UnreadConversation {
            private final long mLatestTimestamp;
            private final String[] mMessages;
            private final String[] mParticipants;
            private final PendingIntent mReadPendingIntent;
            private final RemoteInput mRemoteInput;
            private final PendingIntent mReplyPendingIntent;

            UnreadConversation(String[] messages, RemoteInput remoteInput, PendingIntent replyPendingIntent, PendingIntent readPendingIntent, String[] participants, long latestTimestamp) {
                this.mMessages = messages;
                this.mRemoteInput = remoteInput;
                this.mReadPendingIntent = readPendingIntent;
                this.mReplyPendingIntent = replyPendingIntent;
                this.mParticipants = participants;
                this.mLatestTimestamp = latestTimestamp;
            }

            public String[] getMessages() {
                return this.mMessages;
            }

            public RemoteInput getRemoteInput() {
                return this.mRemoteInput;
            }

            public PendingIntent getReplyPendingIntent() {
                return this.mReplyPendingIntent;
            }

            public PendingIntent getReadPendingIntent() {
                return this.mReadPendingIntent;
            }

            public String[] getParticipants() {
                return this.mParticipants;
            }

            public String getParticipant() {
                return this.mParticipants.length > 0 ? this.mParticipants[0] : null;
            }

            public long getLatestTimestamp() {
                return this.mLatestTimestamp;
            }

            /* renamed from: android.support.v4.app.NotificationCompat$CarExtender$UnreadConversation$Builder */
            public static class Builder {
                private long mLatestTimestamp;
                private final List<String> mMessages;
                private final String mParticipant;
                private PendingIntent mReadPendingIntent;
                private RemoteInput mRemoteInput;
                private PendingIntent mReplyPendingIntent;

                public Builder(String name) {
                    List<String> list;
                    new ArrayList();
                    this.mMessages = list;
                    this.mParticipant = name;
                }

                public Builder addMessage(String message) {
                    boolean add = this.mMessages.add(message);
                    return this;
                }

                public Builder setReplyAction(PendingIntent pendingIntent, RemoteInput remoteInput) {
                    this.mRemoteInput = remoteInput;
                    this.mReplyPendingIntent = pendingIntent;
                    return this;
                }

                public Builder setReadPendingIntent(PendingIntent pendingIntent) {
                    this.mReadPendingIntent = pendingIntent;
                    return this;
                }

                public Builder setLatestTimestamp(long timestamp) {
                    this.mLatestTimestamp = timestamp;
                    return this;
                }

                public UnreadConversation build() {
                    UnreadConversation unreadConversation;
                    new UnreadConversation((String[]) this.mMessages.toArray(new String[this.mMessages.size()]), this.mRemoteInput, this.mReplyPendingIntent, this.mReadPendingIntent, new String[]{this.mParticipant}, this.mLatestTimestamp);
                    return unreadConversation;
                }
            }
        }
    }

    static Notification[] getNotificationArrayFromBundle(Bundle bundle, String str) {
        Bundle bundle2 = bundle;
        String key = str;
        Parcelable[] array = bundle2.getParcelableArray(key);
        if ((array instanceof Notification[]) || array == null) {
            return (Notification[]) array;
        }
        Notification[] typedArray = new Notification[array.length];
        for (int i = 0; i < array.length; i++) {
            typedArray[i] = (Notification) array[i];
        }
        bundle2.putParcelableArray(key, typedArray);
        return typedArray;
    }

    @Nullable
    public static Bundle getExtras(Notification notification) {
        Notification notification2 = notification;
        if (Build.VERSION.SDK_INT >= 19) {
            return notification2.extras;
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return NotificationCompatJellybean.getExtras(notification2);
        }
        return null;
    }

    public static int getActionCount(Notification notification) {
        Notification notification2 = notification;
        if (Build.VERSION.SDK_INT >= 19) {
            return notification2.actions != null ? notification2.actions.length : 0;
        } else if (Build.VERSION.SDK_INT >= 16) {
            return NotificationCompatJellybean.getActionCount(notification2);
        } else {
            return 0;
        }
    }

    public static Action getAction(Notification notification, int i) {
        Notification notification2 = notification;
        int actionIndex = i;
        if (Build.VERSION.SDK_INT >= 20) {
            return getActionCompatFromAction(notification2.actions[actionIndex]);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            Notification.Action action = notification2.actions[actionIndex];
            Bundle actionExtras = null;
            SparseArray<Bundle> actionExtrasMap = notification2.extras.getSparseParcelableArray(NotificationCompatExtras.EXTRA_ACTION_EXTRAS);
            if (actionExtrasMap != null) {
                actionExtras = actionExtrasMap.get(actionIndex);
            }
            return NotificationCompatJellybean.readAction(action.icon, action.title, action.actionIntent, actionExtras);
        } else if (Build.VERSION.SDK_INT >= 16) {
            return NotificationCompatJellybean.getAction(notification2, actionIndex);
        } else {
            return null;
        }
    }

    @RequiresApi(20)
    static Action getActionCompatFromAction(Notification.Action action) {
        RemoteInput[] remoteInputs;
        RemoteInput remoteInput;
        boolean allowGeneratedReplies;
        int semanticAction;
        Action action2;
        Notification.Action action3 = action;
        RemoteInput[] srcArray = action3.getRemoteInputs();
        if (srcArray == null) {
            remoteInputs = null;
        } else {
            remoteInputs = new RemoteInput[srcArray.length];
            for (int i = 0; i < srcArray.length; i++) {
                RemoteInput src = srcArray[i];
                new RemoteInput(src.getResultKey(), src.getLabel(), src.getChoices(), src.getAllowFreeFormInput(), src.getExtras(), (Set<String>) null);
                remoteInputs[i] = remoteInput;
            }
        }
        if (Build.VERSION.SDK_INT >= 24) {
            allowGeneratedReplies = action3.getExtras().getBoolean("android.support.allowGeneratedReplies") || action3.getAllowGeneratedReplies();
        } else {
            allowGeneratedReplies = action3.getExtras().getBoolean("android.support.allowGeneratedReplies");
        }
        boolean showsUserInterface = action3.getExtras().getBoolean("android.support.action.showsUserInterface", true);
        if (Build.VERSION.SDK_INT >= 28) {
            semanticAction = action3.getSemanticAction();
        } else {
            semanticAction = action3.getExtras().getInt("android.support.action.semanticAction", 0);
        }
        new Action(action3.icon, action3.title, action3.actionIntent, action3.getExtras(), remoteInputs, (RemoteInput[]) null, allowGeneratedReplies, semanticAction, showsUserInterface);
        return action2;
    }

    @RequiresApi(21)
    public static List<Action> getInvisibleActions(Notification notification) {
        ArrayList arrayList;
        new ArrayList();
        ArrayList arrayList2 = arrayList;
        Bundle carExtenderBundle = notification.extras.getBundle("android.car.EXTENSIONS");
        if (carExtenderBundle == null) {
            return arrayList2;
        }
        Bundle listBundle = carExtenderBundle.getBundle("invisible_actions");
        if (listBundle != null) {
            for (int i = 0; i < listBundle.size(); i++) {
                boolean add = arrayList2.add(NotificationCompatJellybean.getActionFromBundle(listBundle.getBundle(Integer.toString(i))));
            }
        }
        return arrayList2;
    }

    @RequiresApi(19)
    public static CharSequence getContentTitle(Notification notification) {
        return notification.extras.getCharSequence(EXTRA_TITLE);
    }

    public static String getCategory(Notification notification) {
        Notification notification2 = notification;
        if (Build.VERSION.SDK_INT >= 21) {
            return notification2.category;
        }
        return null;
    }

    public static boolean getLocalOnly(Notification notification) {
        Notification notification2 = notification;
        if (Build.VERSION.SDK_INT >= 20) {
            return (notification2.flags & 256) != 0;
        } else if (Build.VERSION.SDK_INT >= 19) {
            return notification2.extras.getBoolean(NotificationCompatExtras.EXTRA_LOCAL_ONLY);
        } else {
            if (Build.VERSION.SDK_INT >= 16) {
                return NotificationCompatJellybean.getExtras(notification2).getBoolean(NotificationCompatExtras.EXTRA_LOCAL_ONLY);
            }
            return false;
        }
    }

    public static String getGroup(Notification notification) {
        Notification notification2 = notification;
        if (Build.VERSION.SDK_INT >= 20) {
            return notification2.getGroup();
        }
        if (Build.VERSION.SDK_INT >= 19) {
            return notification2.extras.getString(NotificationCompatExtras.EXTRA_GROUP_KEY);
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return NotificationCompatJellybean.getExtras(notification2).getString(NotificationCompatExtras.EXTRA_GROUP_KEY);
        }
        return null;
    }

    public static boolean isGroupSummary(Notification notification) {
        Notification notification2 = notification;
        if (Build.VERSION.SDK_INT >= 20) {
            return (notification2.flags & 512) != 0;
        } else if (Build.VERSION.SDK_INT >= 19) {
            return notification2.extras.getBoolean(NotificationCompatExtras.EXTRA_GROUP_SUMMARY);
        } else {
            if (Build.VERSION.SDK_INT >= 16) {
                return NotificationCompatJellybean.getExtras(notification2).getBoolean(NotificationCompatExtras.EXTRA_GROUP_SUMMARY);
            }
            return false;
        }
    }

    public static String getSortKey(Notification notification) {
        Notification notification2 = notification;
        if (Build.VERSION.SDK_INT >= 20) {
            return notification2.getSortKey();
        }
        if (Build.VERSION.SDK_INT >= 19) {
            return notification2.extras.getString(NotificationCompatExtras.EXTRA_SORT_KEY);
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return NotificationCompatJellybean.getExtras(notification2).getString(NotificationCompatExtras.EXTRA_SORT_KEY);
        }
        return null;
    }

    public static String getChannelId(Notification notification) {
        Notification notification2 = notification;
        if (Build.VERSION.SDK_INT >= 26) {
            return notification2.getChannelId();
        }
        return null;
    }

    public static long getTimeoutAfter(Notification notification) {
        Notification notification2 = notification;
        if (Build.VERSION.SDK_INT >= 26) {
            return notification2.getTimeoutAfter();
        }
        return 0;
    }

    public static int getBadgeIconType(Notification notification) {
        Notification notification2 = notification;
        if (Build.VERSION.SDK_INT >= 26) {
            return notification2.getBadgeIconType();
        }
        return 0;
    }

    public static String getShortcutId(Notification notification) {
        Notification notification2 = notification;
        if (Build.VERSION.SDK_INT >= 26) {
            return notification2.getShortcutId();
        }
        return null;
    }

    public static int getGroupAlertBehavior(Notification notification) {
        Notification notification2 = notification;
        if (Build.VERSION.SDK_INT >= 26) {
            return notification2.getGroupAlertBehavior();
        }
        return 0;
    }

    @Deprecated
    public NotificationCompat() {
    }
}

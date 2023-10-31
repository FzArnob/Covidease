package android.support.p000v4.content.p001pm;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ShortcutInfo;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.p000v4.graphics.drawable.IconCompat;
import android.text.TextUtils;
import java.util.Arrays;

/* renamed from: android.support.v4.content.pm.ShortcutInfoCompat */
public class ShortcutInfoCompat {
    ComponentName mActivity;
    Context mContext;
    CharSequence mDisabledMessage;
    IconCompat mIcon;
    String mId;
    Intent[] mIntents;
    boolean mIsAlwaysBadged;
    CharSequence mLabel;
    CharSequence mLongLabel;

    ShortcutInfoCompat() {
    }

    @RequiresApi(25)
    public ShortcutInfo toShortcutInfo() {
        ShortcutInfo.Builder builder;
        new ShortcutInfo.Builder(this.mContext, this.mId);
        ShortcutInfo.Builder builder2 = builder.setShortLabel(this.mLabel).setIntents(this.mIntents);
        if (this.mIcon != null) {
            ShortcutInfo.Builder icon = builder2.setIcon(this.mIcon.toIcon());
        }
        if (!TextUtils.isEmpty(this.mLongLabel)) {
            ShortcutInfo.Builder longLabel = builder2.setLongLabel(this.mLongLabel);
        }
        if (!TextUtils.isEmpty(this.mDisabledMessage)) {
            ShortcutInfo.Builder disabledMessage = builder2.setDisabledMessage(this.mDisabledMessage);
        }
        if (this.mActivity != null) {
            ShortcutInfo.Builder activity = builder2.setActivity(this.mActivity);
        }
        return builder2.build();
    }

    /* access modifiers changed from: package-private */
    public Intent addToIntent(Intent intent) {
        Intent outIntent = intent;
        Intent putExtra = outIntent.putExtra("android.intent.extra.shortcut.INTENT", this.mIntents[this.mIntents.length - 1]).putExtra("android.intent.extra.shortcut.NAME", this.mLabel.toString());
        if (this.mIcon != null) {
            Drawable badge = null;
            if (this.mIsAlwaysBadged) {
                PackageManager pm = this.mContext.getPackageManager();
                if (this.mActivity != null) {
                    try {
                        badge = pm.getActivityIcon(this.mActivity);
                    } catch (PackageManager.NameNotFoundException e) {
                        PackageManager.NameNotFoundException nameNotFoundException = e;
                    }
                }
                if (badge == null) {
                    badge = this.mContext.getApplicationInfo().loadIcon(pm);
                }
            }
            this.mIcon.addToShortcutIntent(outIntent, badge, this.mContext);
        }
        return outIntent;
    }

    @NonNull
    public String getId() {
        return this.mId;
    }

    @Nullable
    public ComponentName getActivity() {
        return this.mActivity;
    }

    @NonNull
    public CharSequence getShortLabel() {
        return this.mLabel;
    }

    @Nullable
    public CharSequence getLongLabel() {
        return this.mLongLabel;
    }

    @Nullable
    public CharSequence getDisabledMessage() {
        return this.mDisabledMessage;
    }

    @NonNull
    public Intent getIntent() {
        return this.mIntents[this.mIntents.length - 1];
    }

    @NonNull
    public Intent[] getIntents() {
        return (Intent[]) Arrays.copyOf(this.mIntents, this.mIntents.length);
    }

    /* renamed from: android.support.v4.content.pm.ShortcutInfoCompat$Builder */
    public static class Builder {
        private final ShortcutInfoCompat mInfo;

        public Builder(@NonNull Context context, @NonNull String id) {
            ShortcutInfoCompat shortcutInfoCompat;
            new ShortcutInfoCompat();
            this.mInfo = shortcutInfoCompat;
            this.mInfo.mContext = context;
            this.mInfo.mId = id;
        }

        @NonNull
        public Builder setShortLabel(@NonNull CharSequence shortLabel) {
            this.mInfo.mLabel = shortLabel;
            return this;
        }

        @NonNull
        public Builder setLongLabel(@NonNull CharSequence longLabel) {
            this.mInfo.mLongLabel = longLabel;
            return this;
        }

        @NonNull
        public Builder setDisabledMessage(@NonNull CharSequence disabledMessage) {
            this.mInfo.mDisabledMessage = disabledMessage;
            return this;
        }

        @NonNull
        public Builder setIntent(@NonNull Intent intent) {
            return setIntents(new Intent[]{intent});
        }

        @NonNull
        public Builder setIntents(@NonNull Intent[] intents) {
            this.mInfo.mIntents = intents;
            return this;
        }

        @NonNull
        public Builder setIcon(IconCompat icon) {
            this.mInfo.mIcon = icon;
            return this;
        }

        @NonNull
        public Builder setActivity(@NonNull ComponentName activity) {
            this.mInfo.mActivity = activity;
            return this;
        }

        public Builder setAlwaysBadged() {
            this.mInfo.mIsAlwaysBadged = true;
            return this;
        }

        @NonNull
        public ShortcutInfoCompat build() {
            Throwable th;
            Throwable th2;
            if (TextUtils.isEmpty(this.mInfo.mLabel)) {
                Throwable th3 = th2;
                new IllegalArgumentException("Shortcut must have a non-empty label");
                throw th3;
            } else if (this.mInfo.mIntents != null && this.mInfo.mIntents.length != 0) {
                return this.mInfo;
            } else {
                Throwable th4 = th;
                new IllegalArgumentException("Shortcut must have an intent");
                throw th4;
            }
        }
    }
}

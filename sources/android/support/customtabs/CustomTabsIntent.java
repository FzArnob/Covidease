package android.support.customtabs;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.AnimRes;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p000v4.app.ActivityOptionsCompat;
import android.support.p000v4.app.BundleCompat;
import android.support.p000v4.content.ContextCompat;
import android.widget.RemoteViews;
import gnu.expr.Declaration;
import java.util.ArrayList;

public final class CustomTabsIntent {
    public static final String EXTRA_ACTION_BUTTON_BUNDLE = "android.support.customtabs.extra.ACTION_BUTTON_BUNDLE";
    public static final String EXTRA_CLOSE_BUTTON_ICON = "android.support.customtabs.extra.CLOSE_BUTTON_ICON";
    public static final String EXTRA_DEFAULT_SHARE_MENU_ITEM = "android.support.customtabs.extra.SHARE_MENU_ITEM";
    public static final String EXTRA_ENABLE_INSTANT_APPS = "android.support.customtabs.extra.EXTRA_ENABLE_INSTANT_APPS";
    public static final String EXTRA_ENABLE_URLBAR_HIDING = "android.support.customtabs.extra.ENABLE_URLBAR_HIDING";
    public static final String EXTRA_EXIT_ANIMATION_BUNDLE = "android.support.customtabs.extra.EXIT_ANIMATION_BUNDLE";
    public static final String EXTRA_MENU_ITEMS = "android.support.customtabs.extra.MENU_ITEMS";
    public static final String EXTRA_REMOTEVIEWS = "android.support.customtabs.extra.EXTRA_REMOTEVIEWS";
    public static final String EXTRA_REMOTEVIEWS_CLICKED_ID = "android.support.customtabs.extra.EXTRA_REMOTEVIEWS_CLICKED_ID";
    public static final String EXTRA_REMOTEVIEWS_PENDINGINTENT = "android.support.customtabs.extra.EXTRA_REMOTEVIEWS_PENDINGINTENT";
    public static final String EXTRA_REMOTEVIEWS_VIEW_IDS = "android.support.customtabs.extra.EXTRA_REMOTEVIEWS_VIEW_IDS";
    public static final String EXTRA_SECONDARY_TOOLBAR_COLOR = "android.support.customtabs.extra.SECONDARY_TOOLBAR_COLOR";
    public static final String EXTRA_SESSION = "android.support.customtabs.extra.SESSION";
    public static final String EXTRA_TINT_ACTION_BUTTON = "android.support.customtabs.extra.TINT_ACTION_BUTTON";
    public static final String EXTRA_TITLE_VISIBILITY_STATE = "android.support.customtabs.extra.TITLE_VISIBILITY";
    public static final String EXTRA_TOOLBAR_COLOR = "android.support.customtabs.extra.TOOLBAR_COLOR";
    public static final String EXTRA_TOOLBAR_ITEMS = "android.support.customtabs.extra.TOOLBAR_ITEMS";
    private static final String EXTRA_USER_OPT_OUT_FROM_CUSTOM_TABS = "android.support.customtabs.extra.user_opt_out";
    public static final String KEY_DESCRIPTION = "android.support.customtabs.customaction.DESCRIPTION";
    public static final String KEY_ICON = "android.support.customtabs.customaction.ICON";
    public static final String KEY_ID = "android.support.customtabs.customaction.ID";
    public static final String KEY_MENU_ITEM_TITLE = "android.support.customtabs.customaction.MENU_ITEM_TITLE";
    public static final String KEY_PENDING_INTENT = "android.support.customtabs.customaction.PENDING_INTENT";
    private static final int MAX_TOOLBAR_ITEMS = 5;
    public static final int NO_TITLE = 0;
    public static final int SHOW_PAGE_TITLE = 1;
    public static final int TOOLBAR_ACTION_BUTTON_ID = 0;
    @NonNull
    public final Intent intent;
    @Nullable
    public final Bundle startAnimationBundle;

    public void launchUrl(Context context, Uri url) {
        Intent data = this.intent.setData(url);
        ContextCompat.startActivity(context, this.intent, this.startAnimationBundle);
    }

    CustomTabsIntent(Intent intent2, Bundle startAnimationBundle2) {
        this.intent = intent2;
        this.startAnimationBundle = startAnimationBundle2;
    }

    public static final class Builder {
        private ArrayList<Bundle> mActionButtons;
        private boolean mInstantAppsEnabled;
        private final Intent mIntent;
        private ArrayList<Bundle> mMenuItems;
        private Bundle mStartAnimationBundle;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public Builder() {
            this((CustomTabsSession) null);
        }

        public Builder(@Nullable CustomTabsSession customTabsSession) {
            Intent intent;
            Bundle bundle;
            IBinder binder;
            CustomTabsSession session = customTabsSession;
            new Intent("android.intent.action.VIEW");
            this.mIntent = intent;
            this.mMenuItems = null;
            this.mStartAnimationBundle = null;
            this.mActionButtons = null;
            this.mInstantAppsEnabled = true;
            if (session != null) {
                Intent intent2 = this.mIntent.setPackage(session.getComponentName().getPackageName());
            }
            new Bundle();
            Bundle bundle2 = bundle;
            Bundle bundle3 = bundle2;
            if (session == null) {
                binder = null;
            } else {
                binder = session.getBinder();
            }
            BundleCompat.putBinder(bundle3, CustomTabsIntent.EXTRA_SESSION, binder);
            Intent putExtras = this.mIntent.putExtras(bundle2);
        }

        public Builder setToolbarColor(@ColorInt int color) {
            Intent putExtra = this.mIntent.putExtra(CustomTabsIntent.EXTRA_TOOLBAR_COLOR, color);
            return this;
        }

        public Builder enableUrlBarHiding() {
            Intent putExtra = this.mIntent.putExtra(CustomTabsIntent.EXTRA_ENABLE_URLBAR_HIDING, true);
            return this;
        }

        public Builder setCloseButtonIcon(@NonNull Bitmap icon) {
            Intent putExtra = this.mIntent.putExtra(CustomTabsIntent.EXTRA_CLOSE_BUTTON_ICON, icon);
            return this;
        }

        public Builder setShowTitle(boolean showTitle) {
            Intent putExtra = this.mIntent.putExtra(CustomTabsIntent.EXTRA_TITLE_VISIBILITY_STATE, showTitle ? 1 : 0);
            return this;
        }

        public Builder addMenuItem(@NonNull String str, @NonNull PendingIntent pendingIntent) {
            Bundle bundle;
            ArrayList<Bundle> arrayList;
            String label = str;
            PendingIntent pendingIntent2 = pendingIntent;
            if (this.mMenuItems == null) {
                new ArrayList<>();
                this.mMenuItems = arrayList;
            }
            new Bundle();
            Bundle bundle2 = bundle;
            bundle2.putString(CustomTabsIntent.KEY_MENU_ITEM_TITLE, label);
            bundle2.putParcelable(CustomTabsIntent.KEY_PENDING_INTENT, pendingIntent2);
            boolean add = this.mMenuItems.add(bundle2);
            return this;
        }

        public Builder addDefaultShareMenuItem() {
            Intent putExtra = this.mIntent.putExtra(CustomTabsIntent.EXTRA_DEFAULT_SHARE_MENU_ITEM, true);
            return this;
        }

        public Builder setActionButton(@NonNull Bitmap icon, @NonNull String description, @NonNull PendingIntent pendingIntent, boolean shouldTint) {
            Bundle bundle;
            new Bundle();
            Bundle bundle2 = bundle;
            bundle2.putInt(CustomTabsIntent.KEY_ID, 0);
            bundle2.putParcelable(CustomTabsIntent.KEY_ICON, icon);
            bundle2.putString(CustomTabsIntent.KEY_DESCRIPTION, description);
            bundle2.putParcelable(CustomTabsIntent.KEY_PENDING_INTENT, pendingIntent);
            Intent putExtra = this.mIntent.putExtra(CustomTabsIntent.EXTRA_ACTION_BUTTON_BUNDLE, bundle2);
            Intent putExtra2 = this.mIntent.putExtra(CustomTabsIntent.EXTRA_TINT_ACTION_BUTTON, shouldTint);
            return this;
        }

        public Builder setActionButton(@NonNull Bitmap icon, @NonNull String description, @NonNull PendingIntent pendingIntent) {
            return setActionButton(icon, description, pendingIntent, false);
        }

        @Deprecated
        public Builder addToolbarItem(int i, @NonNull Bitmap bitmap, @NonNull String str, PendingIntent pendingIntent) throws IllegalStateException {
            Bundle bundle;
            Throwable th;
            ArrayList<Bundle> arrayList;
            int id = i;
            Bitmap icon = bitmap;
            String description = str;
            PendingIntent pendingIntent2 = pendingIntent;
            if (this.mActionButtons == null) {
                new ArrayList<>();
                this.mActionButtons = arrayList;
            }
            if (this.mActionButtons.size() >= 5) {
                Throwable th2 = th;
                new IllegalStateException("Exceeded maximum toolbar item count of 5");
                throw th2;
            }
            new Bundle();
            Bundle bundle2 = bundle;
            bundle2.putInt(CustomTabsIntent.KEY_ID, id);
            bundle2.putParcelable(CustomTabsIntent.KEY_ICON, icon);
            bundle2.putString(CustomTabsIntent.KEY_DESCRIPTION, description);
            bundle2.putParcelable(CustomTabsIntent.KEY_PENDING_INTENT, pendingIntent2);
            boolean add = this.mActionButtons.add(bundle2);
            return this;
        }

        public Builder setSecondaryToolbarColor(@ColorInt int color) {
            Intent putExtra = this.mIntent.putExtra(CustomTabsIntent.EXTRA_SECONDARY_TOOLBAR_COLOR, color);
            return this;
        }

        public Builder setSecondaryToolbarViews(@NonNull RemoteViews remoteViews, @Nullable int[] clickableIDs, @Nullable PendingIntent pendingIntent) {
            Intent putExtra = this.mIntent.putExtra(CustomTabsIntent.EXTRA_REMOTEVIEWS, remoteViews);
            Intent putExtra2 = this.mIntent.putExtra(CustomTabsIntent.EXTRA_REMOTEVIEWS_VIEW_IDS, clickableIDs);
            Intent putExtra3 = this.mIntent.putExtra(CustomTabsIntent.EXTRA_REMOTEVIEWS_PENDINGINTENT, pendingIntent);
            return this;
        }

        public Builder setInstantAppsEnabled(boolean enabled) {
            this.mInstantAppsEnabled = enabled;
            return this;
        }

        public Builder setStartAnimations(@NonNull Context context, @AnimRes int enterResId, @AnimRes int exitResId) {
            this.mStartAnimationBundle = ActivityOptionsCompat.makeCustomAnimation(context, enterResId, exitResId).toBundle();
            return this;
        }

        public Builder setExitAnimations(@NonNull Context context, @AnimRes int enterResId, @AnimRes int exitResId) {
            Intent putExtra = this.mIntent.putExtra(CustomTabsIntent.EXTRA_EXIT_ANIMATION_BUNDLE, ActivityOptionsCompat.makeCustomAnimation(context, enterResId, exitResId).toBundle());
            return this;
        }

        public CustomTabsIntent build() {
            CustomTabsIntent customTabsIntent;
            if (this.mMenuItems != null) {
                Intent putParcelableArrayListExtra = this.mIntent.putParcelableArrayListExtra(CustomTabsIntent.EXTRA_MENU_ITEMS, this.mMenuItems);
            }
            if (this.mActionButtons != null) {
                Intent putParcelableArrayListExtra2 = this.mIntent.putParcelableArrayListExtra(CustomTabsIntent.EXTRA_TOOLBAR_ITEMS, this.mActionButtons);
            }
            Intent putExtra = this.mIntent.putExtra(CustomTabsIntent.EXTRA_ENABLE_INSTANT_APPS, this.mInstantAppsEnabled);
            new CustomTabsIntent(this.mIntent, this.mStartAnimationBundle);
            return customTabsIntent;
        }
    }

    public static int getMaxToolbarItems() {
        return 5;
    }

    public static Intent setAlwaysUseBrowserUI(Intent intent2) {
        Intent intent3;
        Intent intent4 = intent2;
        if (intent4 == null) {
            new Intent("android.intent.action.VIEW");
            intent4 = intent3;
        }
        Intent addFlags = intent4.addFlags(Declaration.IS_DYNAMIC);
        Intent putExtra = intent4.putExtra(EXTRA_USER_OPT_OUT_FROM_CUSTOM_TABS, true);
        return intent4;
    }

    public static boolean shouldAlwaysUseBrowserUI(Intent intent2) {
        Intent intent3 = intent2;
        return intent3.getBooleanExtra(EXTRA_USER_OPT_OUT_FROM_CUSTOM_TABS, false) && (intent3.getFlags() & Declaration.IS_DYNAMIC) != 0;
    }
}

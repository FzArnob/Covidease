package androidx.browser.browseractions;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.p000v4.content.ContextCompat;
import android.text.TextUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BrowserActionsIntent {
    public static final String ACTION_BROWSER_ACTIONS_OPEN = "androidx.browser.browseractions.browser_action_open";
    public static final String EXTRA_APP_ID = "androidx.browser.browseractions.APP_ID";
    public static final String EXTRA_MENU_ITEMS = "androidx.browser.browseractions.extra.MENU_ITEMS";
    public static final String EXTRA_SELECTED_ACTION_PENDING_INTENT = "androidx.browser.browseractions.extra.SELECTED_ACTION_PENDING_INTENT";
    public static final String EXTRA_TYPE = "androidx.browser.browseractions.extra.TYPE";
    public static final int ITEM_COPY = 3;
    public static final int ITEM_DOWNLOAD = 2;
    public static final int ITEM_INVALID_ITEM = -1;
    public static final int ITEM_OPEN_IN_INCOGNITO = 1;
    public static final int ITEM_OPEN_IN_NEW_TAB = 0;
    public static final int ITEM_SHARE = 4;
    public static final String KEY_ACTION = "androidx.browser.browseractions.ACTION";
    public static final String KEY_ICON_ID = "androidx.browser.browseractions.ICON_ID";
    public static final String KEY_TITLE = "androidx.browser.browseractions.TITLE";
    public static final int MAX_CUSTOM_ITEMS = 5;
    private static final String TAG = "BrowserActions";
    private static final String TEST_URL = "https://www.example.com";
    public static final int URL_TYPE_AUDIO = 3;
    public static final int URL_TYPE_FILE = 4;
    public static final int URL_TYPE_IMAGE = 1;
    public static final int URL_TYPE_NONE = 0;
    public static final int URL_TYPE_PLUGIN = 5;
    public static final int URL_TYPE_VIDEO = 2;
    private static BrowserActionsFallDialogListener sDialogListenter;
    @NonNull
    private final Intent mIntent;

    @VisibleForTesting
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    interface BrowserActionsFallDialogListener {
        void onDialogShown();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface BrowserActionsItemId {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface BrowserActionsUrlType {
    }

    @NonNull
    public Intent getIntent() {
        return this.mIntent;
    }

    BrowserActionsIntent(@NonNull Intent intent) {
        this.mIntent = intent;
    }

    public static final class Builder {
        private Context mContext;
        private final Intent mIntent;
        private ArrayList<Bundle> mMenuItems = null;
        private PendingIntent mOnItemSelectedPendingIntent = null;
        private int mType;
        private Uri mUri;

        public Builder(Context context, Uri uri) {
            Intent intent;
            ArrayList<Bundle> arrayList;
            new Intent(BrowserActionsIntent.ACTION_BROWSER_ACTIONS_OPEN);
            this.mIntent = intent;
            this.mContext = context;
            this.mUri = uri;
            this.mType = 0;
            new ArrayList<>();
            this.mMenuItems = arrayList;
        }

        public Builder setUrlType(int type) {
            this.mType = type;
            return this;
        }

        public Builder setCustomItems(ArrayList<BrowserActionItem> arrayList) {
            Throwable th;
            Throwable th2;
            ArrayList<BrowserActionItem> items = arrayList;
            if (items.size() > 5) {
                Throwable th3 = th2;
                new IllegalStateException("Exceeded maximum toolbar item count of 5");
                throw th3;
            }
            for (int i = 0; i < items.size(); i++) {
                if (TextUtils.isEmpty(items.get(i).getTitle()) || items.get(i).getAction() == null) {
                    Throwable th4 = th;
                    new IllegalArgumentException("Custom item should contain a non-empty title and non-null intent.");
                    throw th4;
                }
                boolean add = this.mMenuItems.add(getBundleFromItem(items.get(i)));
            }
            return this;
        }

        public Builder setCustomItems(BrowserActionItem... items) {
            ArrayList arrayList;
            new ArrayList(Arrays.asList(items));
            return setCustomItems((ArrayList<BrowserActionItem>) arrayList);
        }

        public Builder setOnItemSelectedAction(PendingIntent onItemSelectedPendingIntent) {
            this.mOnItemSelectedPendingIntent = onItemSelectedPendingIntent;
            return this;
        }

        private Bundle getBundleFromItem(BrowserActionItem browserActionItem) {
            Bundle bundle;
            BrowserActionItem item = browserActionItem;
            new Bundle();
            Bundle bundle2 = bundle;
            bundle2.putString(BrowserActionsIntent.KEY_TITLE, item.getTitle());
            bundle2.putParcelable(BrowserActionsIntent.KEY_ACTION, item.getAction());
            if (item.getIconId() != 0) {
                bundle2.putInt(BrowserActionsIntent.KEY_ICON_ID, item.getIconId());
            }
            return bundle2;
        }

        public BrowserActionsIntent build() {
            Intent intent;
            BrowserActionsIntent browserActionsIntent;
            Intent data = this.mIntent.setData(this.mUri);
            Intent putExtra = this.mIntent.putExtra(BrowserActionsIntent.EXTRA_TYPE, this.mType);
            Intent putParcelableArrayListExtra = this.mIntent.putParcelableArrayListExtra(BrowserActionsIntent.EXTRA_MENU_ITEMS, this.mMenuItems);
            new Intent();
            Intent putExtra2 = this.mIntent.putExtra(BrowserActionsIntent.EXTRA_APP_ID, PendingIntent.getActivity(this.mContext, 0, intent, 0));
            if (this.mOnItemSelectedPendingIntent != null) {
                Intent putExtra3 = this.mIntent.putExtra(BrowserActionsIntent.EXTRA_SELECTED_ACTION_PENDING_INTENT, this.mOnItemSelectedPendingIntent);
            }
            new BrowserActionsIntent(this.mIntent);
            return browserActionsIntent;
        }
    }

    public static void openBrowserAction(Context context, Uri uri) {
        Builder builder;
        Context context2 = context;
        new Builder(context2, uri);
        launchIntent(context2, builder.build().getIntent());
    }

    public static void openBrowserAction(Context context, Uri uri, int type, ArrayList<BrowserActionItem> items, PendingIntent pendingIntent) {
        Builder builder;
        Context context2 = context;
        new Builder(context2, uri);
        launchIntent(context2, builder.setUrlType(type).setCustomItems(items).setOnItemSelectedAction(pendingIntent).build().getIntent());
    }

    public static void launchIntent(Context context, Intent intent) {
        Context context2 = context;
        launchIntent(context2, intent, getBrowserActionsIntentHandlers(context2));
    }

    @VisibleForTesting
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    static void launchIntent(Context context, Intent intent, List<ResolveInfo> list) {
        Intent viewIntent;
        Context context2 = context;
        Intent intent2 = intent;
        List<ResolveInfo> handlers = list;
        if (handlers == null || handlers.size() == 0) {
            openFallbackBrowserActionsMenu(context2, intent2);
            return;
        }
        if (handlers.size() == 1) {
            Intent intent3 = intent2.setPackage(handlers.get(0).activityInfo.packageName);
        } else {
            new Intent("android.intent.action.VIEW", Uri.parse(TEST_URL));
            ResolveInfo defaultHandler = context2.getPackageManager().resolveActivity(viewIntent, 65536);
            if (defaultHandler != null) {
                String defaultPackageName = defaultHandler.activityInfo.packageName;
                int i = 0;
                while (true) {
                    if (i >= handlers.size()) {
                        break;
                    } else if (defaultPackageName.equals(handlers.get(i).activityInfo.packageName)) {
                        Intent intent4 = intent2.setPackage(defaultPackageName);
                        break;
                    } else {
                        i++;
                    }
                }
            }
        }
        ContextCompat.startActivity(context2, intent2, (Bundle) null);
    }

    private static List<ResolveInfo> getBrowserActionsIntentHandlers(Context context) {
        Intent intent;
        new Intent(ACTION_BROWSER_ACTIONS_OPEN, Uri.parse(TEST_URL));
        return context.getPackageManager().queryIntentActivities(intent, 131072);
    }

    private static void openFallbackBrowserActionsMenu(Context context, Intent intent) {
        Context context2 = context;
        Intent intent2 = intent;
        Uri uri = intent2.getData();
        int type = intent2.getIntExtra(EXTRA_TYPE, 0);
        ArrayList<Bundle> bundles = intent2.getParcelableArrayListExtra(EXTRA_MENU_ITEMS);
        openFallbackBrowserActionsMenu(context2, uri, type, bundles != null ? parseBrowserActionItems(bundles) : null);
    }

    @VisibleForTesting
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    static void setDialogShownListenter(BrowserActionsFallDialogListener dialogListener) {
        sDialogListenter = dialogListener;
    }

    private static void openFallbackBrowserActionsMenu(Context context, Uri uri, int i, List<BrowserActionItem> menuItems) {
        BrowserActionsFallbackMenuUi menuUi;
        int i2 = i;
        new BrowserActionsFallbackMenuUi(context, uri, menuItems);
        menuUi.displayMenu();
        if (sDialogListenter != null) {
            sDialogListenter.onDialogShown();
        }
    }

    public static List<BrowserActionItem> parseBrowserActionItems(ArrayList<Bundle> arrayList) {
        List<BrowserActionItem> list;
        Throwable th;
        Object obj;
        ArrayList<Bundle> bundles = arrayList;
        new ArrayList();
        List<BrowserActionItem> mActions = list;
        for (int i = 0; i < bundles.size(); i++) {
            Bundle bundle = bundles.get(i);
            String title = bundle.getString(KEY_TITLE);
            PendingIntent action = (PendingIntent) bundle.getParcelable(KEY_ACTION);
            int iconId = bundle.getInt(KEY_ICON_ID);
            if (TextUtils.isEmpty(title) || action == null) {
                Throwable th2 = th;
                new IllegalArgumentException("Custom item should contain a non-empty title and non-null intent.");
                throw th2;
            }
            new BrowserActionItem(title, action, iconId);
            boolean add = mActions.add(obj);
        }
        return mActions;
    }

    public static String getCreatorPackageName(Intent intent) {
        PendingIntent pendingIntent = (PendingIntent) intent.getParcelableExtra(EXTRA_APP_ID);
        if (pendingIntent == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= 17) {
            return pendingIntent.getCreatorPackage();
        }
        return pendingIntent.getTargetPackage();
    }
}

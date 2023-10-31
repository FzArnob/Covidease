package android.support.p003v7.widget;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.support.p000v4.view.ActionProvider;
import android.support.p003v7.appcompat.C0425R;
import android.support.p003v7.content.res.AppCompatResources;
import android.support.p003v7.widget.ActivityChooserModel;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* renamed from: android.support.v7.widget.ShareActionProvider */
public class ShareActionProvider extends ActionProvider {
    private static final int DEFAULT_INITIAL_ACTIVITY_COUNT = 4;
    public static final String DEFAULT_SHARE_HISTORY_FILE_NAME = "share_history.xml";
    final Context mContext;
    private int mMaxShownActivityCount = 4;
    private ActivityChooserModel.OnChooseActivityListener mOnChooseActivityListener;
    private final ShareMenuItemOnMenuItemClickListener mOnMenuItemClickListener;
    OnShareTargetSelectedListener mOnShareTargetSelectedListener;
    String mShareHistoryFileName;

    /* renamed from: android.support.v7.widget.ShareActionProvider$OnShareTargetSelectedListener */
    public interface OnShareTargetSelectedListener {
        boolean onShareTargetSelected(ShareActionProvider shareActionProvider, Intent intent);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ShareActionProvider(android.content.Context r8) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r0
            r3 = r1
            r2.<init>(r3)
            r2 = r0
            r3 = 4
            r2.mMaxShownActivityCount = r3
            r2 = r0
            android.support.v7.widget.ShareActionProvider$ShareMenuItemOnMenuItemClickListener r3 = new android.support.v7.widget.ShareActionProvider$ShareMenuItemOnMenuItemClickListener
            r6 = r3
            r3 = r6
            r4 = r6
            r5 = r0
            r4.<init>(r5)
            r2.mOnMenuItemClickListener = r3
            r2 = r0
            java.lang.String r3 = "share_history.xml"
            r2.mShareHistoryFileName = r3
            r2 = r0
            r3 = r1
            r2.mContext = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.widget.ShareActionProvider.<init>(android.content.Context):void");
    }

    public void setOnShareTargetSelectedListener(OnShareTargetSelectedListener listener) {
        this.mOnShareTargetSelectedListener = listener;
        setActivityChooserPolicyIfNeeded();
    }

    public View onCreateActionView() {
        ActivityChooserView activityChooserView;
        TypedValue typedValue;
        new ActivityChooserView(this.mContext);
        ActivityChooserView activityChooserView2 = activityChooserView;
        if (!activityChooserView2.isInEditMode()) {
            activityChooserView2.setActivityChooserModel(ActivityChooserModel.get(this.mContext, this.mShareHistoryFileName));
        }
        new TypedValue();
        TypedValue outTypedValue = typedValue;
        boolean resolveAttribute = this.mContext.getTheme().resolveAttribute(C0425R.attr.actionModeShareDrawable, outTypedValue, true);
        activityChooserView2.setExpandActivityOverflowButtonDrawable(AppCompatResources.getDrawable(this.mContext, outTypedValue.resourceId));
        activityChooserView2.setProvider(this);
        activityChooserView2.setDefaultActionButtonContentDescription(C0425R.string.abc_shareactionprovider_share_with_application);
        activityChooserView2.setExpandActivityOverflowButtonContentDescription(C0425R.string.abc_shareactionprovider_share_with);
        return activityChooserView2;
    }

    public boolean hasSubMenu() {
        return true;
    }

    public void onPrepareSubMenu(SubMenu subMenu) {
        SubMenu subMenu2 = subMenu;
        subMenu2.clear();
        ActivityChooserModel dataModel = ActivityChooserModel.get(this.mContext, this.mShareHistoryFileName);
        PackageManager packageManager = this.mContext.getPackageManager();
        int expandedActivityCount = dataModel.getActivityCount();
        int collapsedActivityCount = Math.min(expandedActivityCount, this.mMaxShownActivityCount);
        for (int i = 0; i < collapsedActivityCount; i++) {
            ResolveInfo activity = dataModel.getActivity(i);
            MenuItem onMenuItemClickListener = subMenu2.add(0, i, i, activity.loadLabel(packageManager)).setIcon(activity.loadIcon(packageManager)).setOnMenuItemClickListener(this.mOnMenuItemClickListener);
        }
        if (collapsedActivityCount < expandedActivityCount) {
            SubMenu expandedSubMenu = subMenu2.addSubMenu(0, collapsedActivityCount, collapsedActivityCount, this.mContext.getString(C0425R.string.abc_activity_chooser_view_see_all));
            for (int i2 = 0; i2 < expandedActivityCount; i2++) {
                ResolveInfo activity2 = dataModel.getActivity(i2);
                MenuItem onMenuItemClickListener2 = expandedSubMenu.add(0, i2, i2, activity2.loadLabel(packageManager)).setIcon(activity2.loadIcon(packageManager)).setOnMenuItemClickListener(this.mOnMenuItemClickListener);
            }
        }
    }

    public void setShareHistoryFileName(String shareHistoryFile) {
        this.mShareHistoryFileName = shareHistoryFile;
        setActivityChooserPolicyIfNeeded();
    }

    public void setShareIntent(Intent intent) {
        Intent shareIntent = intent;
        if (shareIntent != null) {
            String action = shareIntent.getAction();
            if ("android.intent.action.SEND".equals(action) || "android.intent.action.SEND_MULTIPLE".equals(action)) {
                updateIntent(shareIntent);
            }
        }
        ActivityChooserModel.get(this.mContext, this.mShareHistoryFileName).setIntent(shareIntent);
    }

    /* renamed from: android.support.v7.widget.ShareActionProvider$ShareMenuItemOnMenuItemClickListener */
    private class ShareMenuItemOnMenuItemClickListener implements MenuItem.OnMenuItemClickListener {
        final /* synthetic */ ShareActionProvider this$0;

        ShareMenuItemOnMenuItemClickListener(ShareActionProvider shareActionProvider) {
            this.this$0 = shareActionProvider;
        }

        public boolean onMenuItemClick(MenuItem item) {
            Intent launchIntent = ActivityChooserModel.get(this.this$0.mContext, this.this$0.mShareHistoryFileName).chooseActivity(item.getItemId());
            if (launchIntent != null) {
                String action = launchIntent.getAction();
                if ("android.intent.action.SEND".equals(action) || "android.intent.action.SEND_MULTIPLE".equals(action)) {
                    this.this$0.updateIntent(launchIntent);
                }
                this.this$0.mContext.startActivity(launchIntent);
            }
            return true;
        }
    }

    private void setActivityChooserPolicyIfNeeded() {
        ActivityChooserModel.OnChooseActivityListener onChooseActivityListener;
        if (this.mOnShareTargetSelectedListener != null) {
            if (this.mOnChooseActivityListener == null) {
                new ShareActivityChooserModelPolicy(this);
                this.mOnChooseActivityListener = onChooseActivityListener;
            }
            ActivityChooserModel.get(this.mContext, this.mShareHistoryFileName).setOnChooseActivityListener(this.mOnChooseActivityListener);
        }
    }

    /* renamed from: android.support.v7.widget.ShareActionProvider$ShareActivityChooserModelPolicy */
    private class ShareActivityChooserModelPolicy implements ActivityChooserModel.OnChooseActivityListener {
        final /* synthetic */ ShareActionProvider this$0;

        ShareActivityChooserModelPolicy(ShareActionProvider shareActionProvider) {
            this.this$0 = shareActionProvider;
        }

        public boolean onChooseActivity(ActivityChooserModel activityChooserModel, Intent intent) {
            ActivityChooserModel activityChooserModel2 = activityChooserModel;
            Intent intent2 = intent;
            if (this.this$0.mOnShareTargetSelectedListener != null) {
                boolean onShareTargetSelected = this.this$0.mOnShareTargetSelectedListener.onShareTargetSelected(this.this$0, intent2);
            }
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public void updateIntent(Intent intent) {
        Intent intent2 = intent;
        if (Build.VERSION.SDK_INT >= 21) {
            Intent addFlags = intent2.addFlags(134742016);
        } else {
            Intent addFlags2 = intent2.addFlags(524288);
        }
    }
}

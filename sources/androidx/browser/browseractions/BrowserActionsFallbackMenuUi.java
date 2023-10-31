package androidx.browser.browseractions;

import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.customtabs.C0056R;
import android.support.p000v4.widget.TextViewCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.List;

class BrowserActionsFallbackMenuUi implements AdapterView.OnItemClickListener {
    private static final String TAG = "BrowserActionskMenuUi";
    private BrowserActionsFallbackMenuDialog mBrowserActionsDialog;
    private final Context mContext;
    private final List<BrowserActionItem> mMenuItems;
    BrowserActionsFallMenuUiListener mMenuUiListener;
    private final Uri mUri;

    @VisibleForTesting
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    interface BrowserActionsFallMenuUiListener {
        void onMenuShown(View view);
    }

    BrowserActionsFallbackMenuUi(Context context, Uri uri, List<BrowserActionItem> menuItems) {
        this.mContext = context;
        this.mUri = uri;
        this.mMenuItems = menuItems;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setMenuUiListener(BrowserActionsFallMenuUiListener menuUiListener) {
        BrowserActionsFallMenuUiListener browserActionsFallMenuUiListener = menuUiListener;
        this.mMenuUiListener = browserActionsFallMenuUiListener;
    }

    public void displayMenu() {
        BrowserActionsFallbackMenuDialog browserActionsFallbackMenuDialog;
        DialogInterface.OnShowListener onShowListener;
        View view = LayoutInflater.from(this.mContext).inflate(C0056R.layout.browser_actions_context_menu_page, (ViewGroup) null);
        new BrowserActionsFallbackMenuDialog(this.mContext, initMenuView(view));
        this.mBrowserActionsDialog = browserActionsFallbackMenuDialog;
        this.mBrowserActionsDialog.setContentView(view);
        if (this.mMenuUiListener != null) {
            final View view2 = view;
            new DialogInterface.OnShowListener(this) {
                final /* synthetic */ BrowserActionsFallbackMenuUi this$0;

                {
                    this.this$0 = this$0;
                }

                public void onShow(DialogInterface dialogInterface) {
                    DialogInterface dialogInterface2 = dialogInterface;
                    this.this$0.mMenuUiListener.onMenuShown(view2);
                }
            };
            this.mBrowserActionsDialog.setOnShowListener(onShowListener);
        }
        this.mBrowserActionsDialog.show();
    }

    private BrowserActionsFallbackMenuView initMenuView(View view) {
        View.OnClickListener onClickListener;
        ListAdapter listAdapter;
        View view2 = view;
        BrowserActionsFallbackMenuView menuView = (BrowserActionsFallbackMenuView) view2.findViewById(C0056R.C0058id.browser_actions_menu_view);
        TextView urlTextView = (TextView) view2.findViewById(C0056R.C0058id.browser_actions_header_text);
        urlTextView.setText(this.mUri.toString());
        final TextView textView = urlTextView;
        new View.OnClickListener(this) {
            final /* synthetic */ BrowserActionsFallbackMenuUi this$0;

            {
                this.this$0 = this$0;
            }

            public void onClick(View view) {
                View view2 = view;
                if (TextViewCompat.getMaxLines(textView) == Integer.MAX_VALUE) {
                    textView.setMaxLines(1);
                    textView.setEllipsize(TextUtils.TruncateAt.END);
                    return;
                }
                textView.setMaxLines(Integer.MAX_VALUE);
                textView.setEllipsize((TextUtils.TruncateAt) null);
            }
        };
        urlTextView.setOnClickListener(onClickListener);
        ListView menuListView = (ListView) view2.findViewById(C0056R.C0058id.browser_actions_menu_items);
        new BrowserActionsFallbackMenuAdapter(this.mMenuItems, this.mContext);
        menuListView.setAdapter(listAdapter);
        menuListView.setOnItemClickListener(this);
        return menuView;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int position, long j) {
        AdapterView<?> adapterView2 = adapterView;
        View view2 = view;
        long j2 = j;
        try {
            this.mMenuItems.get(position).getAction().send();
            this.mBrowserActionsDialog.dismiss();
        } catch (PendingIntent.CanceledException e) {
            int e2 = Log.e(TAG, "Failed to send custom item action", e);
        }
    }
}

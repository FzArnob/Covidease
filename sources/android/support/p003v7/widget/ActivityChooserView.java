package android.support.p003v7.widget;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.DataSetObserver;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;
import android.support.p000v4.view.ActionProvider;
import android.support.p003v7.appcompat.C0425R;
import android.support.p003v7.widget.ActivityChooserModel;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import gnu.expr.Declaration;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v7.widget.ActivityChooserView */
public class ActivityChooserView extends ViewGroup implements ActivityChooserModel.ActivityChooserModelClient {
    private static final String LOG_TAG = "ActivityChooserView";
    private final View mActivityChooserContent;
    private final Drawable mActivityChooserContentBackground;
    final ActivityChooserViewAdapter mAdapter;
    private final Callbacks mCallbacks;
    private int mDefaultActionButtonContentDescription;
    final FrameLayout mDefaultActivityButton;
    private final ImageView mDefaultActivityButtonImage;
    final FrameLayout mExpandActivityOverflowButton;
    private final ImageView mExpandActivityOverflowButtonImage;
    int mInitialActivityCount;
    private boolean mIsAttachedToWindow;
    boolean mIsSelectingDefaultActivity;
    private final int mListPopupMaxWidth;
    private ListPopupWindow mListPopupWindow;
    final DataSetObserver mModelDataSetObserver;
    PopupWindow.OnDismissListener mOnDismissListener;
    private final ViewTreeObserver.OnGlobalLayoutListener mOnGlobalLayoutListener;
    ActionProvider mProvider;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ActivityChooserView(Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ActivityChooserView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ActivityChooserView(android.content.Context r16, android.util.AttributeSet r17, int r18) {
        /*
            r15 = this;
            r0 = r15
            r1 = r16
            r2 = r17
            r3 = r18
            r9 = r0
            r10 = r1
            r11 = r2
            r12 = r3
            r9.<init>(r10, r11, r12)
            r9 = r0
            android.support.v7.widget.ActivityChooserView$1 r10 = new android.support.v7.widget.ActivityChooserView$1
            r14 = r10
            r10 = r14
            r11 = r14
            r12 = r0
            r11.<init>(r12)
            r9.mModelDataSetObserver = r10
            r9 = r0
            android.support.v7.widget.ActivityChooserView$2 r10 = new android.support.v7.widget.ActivityChooserView$2
            r14 = r10
            r10 = r14
            r11 = r14
            r12 = r0
            r11.<init>(r12)
            r9.mOnGlobalLayoutListener = r10
            r9 = r0
            r10 = 4
            r9.mInitialActivityCount = r10
            r9 = r1
            r10 = r2
            int[] r11 = android.support.p003v7.appcompat.C0425R.styleable.ActivityChooserView
            r12 = r3
            r13 = 0
            android.content.res.TypedArray r9 = r9.obtainStyledAttributes(r10, r11, r12, r13)
            r4 = r9
            r9 = r0
            r10 = r4
            int r11 = android.support.p003v7.appcompat.C0425R.styleable.ActivityChooserView_initialActivityCount
            r12 = 4
            int r10 = r10.getInt(r11, r12)
            r9.mInitialActivityCount = r10
            r9 = r4
            int r10 = android.support.p003v7.appcompat.C0425R.styleable.ActivityChooserView_expandActivityOverflowButtonDrawable
            android.graphics.drawable.Drawable r9 = r9.getDrawable(r10)
            r5 = r9
            r9 = r4
            r9.recycle()
            r9 = r0
            android.content.Context r9 = r9.getContext()
            android.view.LayoutInflater r9 = android.view.LayoutInflater.from(r9)
            r6 = r9
            r9 = r6
            int r10 = android.support.p003v7.appcompat.C0425R.layout.abc_activity_chooser_view
            r11 = r0
            r12 = 1
            android.view.View r9 = r9.inflate(r10, r11, r12)
            r9 = r0
            android.support.v7.widget.ActivityChooserView$Callbacks r10 = new android.support.v7.widget.ActivityChooserView$Callbacks
            r14 = r10
            r10 = r14
            r11 = r14
            r12 = r0
            r11.<init>(r12)
            r9.mCallbacks = r10
            r9 = r0
            r10 = r0
            int r11 = android.support.p003v7.appcompat.C0425R.C0427id.activity_chooser_view_content
            android.view.View r10 = r10.findViewById(r11)
            r9.mActivityChooserContent = r10
            r9 = r0
            r10 = r0
            android.view.View r10 = r10.mActivityChooserContent
            android.graphics.drawable.Drawable r10 = r10.getBackground()
            r9.mActivityChooserContentBackground = r10
            r9 = r0
            r10 = r0
            int r11 = android.support.p003v7.appcompat.C0425R.C0427id.default_activity_button
            android.view.View r10 = r10.findViewById(r11)
            android.widget.FrameLayout r10 = (android.widget.FrameLayout) r10
            r9.mDefaultActivityButton = r10
            r9 = r0
            android.widget.FrameLayout r9 = r9.mDefaultActivityButton
            r10 = r0
            android.support.v7.widget.ActivityChooserView$Callbacks r10 = r10.mCallbacks
            r9.setOnClickListener(r10)
            r9 = r0
            android.widget.FrameLayout r9 = r9.mDefaultActivityButton
            r10 = r0
            android.support.v7.widget.ActivityChooserView$Callbacks r10 = r10.mCallbacks
            r9.setOnLongClickListener(r10)
            r9 = r0
            r10 = r0
            android.widget.FrameLayout r10 = r10.mDefaultActivityButton
            int r11 = android.support.p003v7.appcompat.C0425R.C0427id.image
            android.view.View r10 = r10.findViewById(r11)
            android.widget.ImageView r10 = (android.widget.ImageView) r10
            r9.mDefaultActivityButtonImage = r10
            r9 = r0
            int r10 = android.support.p003v7.appcompat.C0425R.C0427id.expand_activities_button
            android.view.View r9 = r9.findViewById(r10)
            android.widget.FrameLayout r9 = (android.widget.FrameLayout) r9
            r7 = r9
            r9 = r7
            r10 = r0
            android.support.v7.widget.ActivityChooserView$Callbacks r10 = r10.mCallbacks
            r9.setOnClickListener(r10)
            r9 = r7
            android.support.v7.widget.ActivityChooserView$3 r10 = new android.support.v7.widget.ActivityChooserView$3
            r14 = r10
            r10 = r14
            r11 = r14
            r12 = r0
            r11.<init>(r12)
            r9.setAccessibilityDelegate(r10)
            r9 = r7
            android.support.v7.widget.ActivityChooserView$4 r10 = new android.support.v7.widget.ActivityChooserView$4
            r14 = r10
            r10 = r14
            r11 = r14
            r12 = r0
            r13 = r7
            r11.<init>(r12, r13)
            r9.setOnTouchListener(r10)
            r9 = r0
            r10 = r7
            r9.mExpandActivityOverflowButton = r10
            r9 = r0
            r10 = r7
            int r11 = android.support.p003v7.appcompat.C0425R.C0427id.image
            android.view.View r10 = r10.findViewById(r11)
            android.widget.ImageView r10 = (android.widget.ImageView) r10
            r9.mExpandActivityOverflowButtonImage = r10
            r9 = r0
            android.widget.ImageView r9 = r9.mExpandActivityOverflowButtonImage
            r10 = r5
            r9.setImageDrawable(r10)
            r9 = r0
            android.support.v7.widget.ActivityChooserView$ActivityChooserViewAdapter r10 = new android.support.v7.widget.ActivityChooserView$ActivityChooserViewAdapter
            r14 = r10
            r10 = r14
            r11 = r14
            r12 = r0
            r11.<init>(r12)
            r9.mAdapter = r10
            r9 = r0
            android.support.v7.widget.ActivityChooserView$ActivityChooserViewAdapter r9 = r9.mAdapter
            android.support.v7.widget.ActivityChooserView$5 r10 = new android.support.v7.widget.ActivityChooserView$5
            r14 = r10
            r10 = r14
            r11 = r14
            r12 = r0
            r11.<init>(r12)
            r9.registerDataSetObserver(r10)
            r9 = r1
            android.content.res.Resources r9 = r9.getResources()
            r8 = r9
            r9 = r0
            r10 = r8
            android.util.DisplayMetrics r10 = r10.getDisplayMetrics()
            int r10 = r10.widthPixels
            r11 = 2
            int r10 = r10 / 2
            r11 = r8
            int r12 = android.support.p003v7.appcompat.C0425R.dimen.abc_config_prefDialogWidth
            int r11 = r11.getDimensionPixelSize(r12)
            int r10 = java.lang.Math.max(r10, r11)
            r9.mListPopupMaxWidth = r10
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.widget.ActivityChooserView.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public void setActivityChooserModel(ActivityChooserModel dataModel) {
        this.mAdapter.setDataModel(dataModel);
        if (isShowingPopup()) {
            boolean dismissPopup = dismissPopup();
            boolean showPopup = showPopup();
        }
    }

    public void setExpandActivityOverflowButtonDrawable(Drawable drawable) {
        this.mExpandActivityOverflowButtonImage.setImageDrawable(drawable);
    }

    public void setExpandActivityOverflowButtonContentDescription(int resourceId) {
        this.mExpandActivityOverflowButtonImage.setContentDescription(getContext().getString(resourceId));
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setProvider(ActionProvider provider) {
        ActionProvider actionProvider = provider;
        this.mProvider = actionProvider;
    }

    public boolean showPopup() {
        if (isShowingPopup() || !this.mIsAttachedToWindow) {
            return false;
        }
        this.mIsSelectingDefaultActivity = false;
        showPopupUnchecked(this.mInitialActivityCount);
        return true;
    }

    /* access modifiers changed from: package-private */
    public void showPopupUnchecked(int i) {
        Drawable drawable;
        Throwable th;
        int maxActivityCount = i;
        if (this.mAdapter.getDataModel() == null) {
            Throwable th2 = th;
            new IllegalStateException("No data model. Did you call #setDataModel?");
            throw th2;
        }
        getViewTreeObserver().addOnGlobalLayoutListener(this.mOnGlobalLayoutListener);
        boolean defaultActivityButtonShown = this.mDefaultActivityButton.getVisibility() == 0;
        int activityCount = this.mAdapter.getActivityCount();
        int maxActivityCountOffset = defaultActivityButtonShown ? 1 : 0;
        if (maxActivityCount == Integer.MAX_VALUE || activityCount <= maxActivityCount + maxActivityCountOffset) {
            this.mAdapter.setShowFooterView(false);
            this.mAdapter.setMaxActivityCount(maxActivityCount);
        } else {
            this.mAdapter.setShowFooterView(true);
            this.mAdapter.setMaxActivityCount(maxActivityCount - 1);
        }
        ListPopupWindow popupWindow = getListPopupWindow();
        if (!popupWindow.isShowing()) {
            if (this.mIsSelectingDefaultActivity || !defaultActivityButtonShown) {
                this.mAdapter.setShowDefaultActivity(true, defaultActivityButtonShown);
            } else {
                this.mAdapter.setShowDefaultActivity(false, false);
            }
            popupWindow.setContentWidth(Math.min(this.mAdapter.measureContentWidth(), this.mListPopupMaxWidth));
            popupWindow.show();
            if (this.mProvider != null) {
                this.mProvider.subUiVisibilityChanged(true);
            }
            popupWindow.getListView().setContentDescription(getContext().getString(C0425R.string.abc_activitychooserview_choose_application));
            new ColorDrawable(0);
            popupWindow.getListView().setSelector(drawable);
        }
    }

    public boolean dismissPopup() {
        if (isShowingPopup()) {
            getListPopupWindow().dismiss();
            ViewTreeObserver viewTreeObserver = getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.removeGlobalOnLayoutListener(this.mOnGlobalLayoutListener);
            }
        }
        return true;
    }

    public boolean isShowingPopup() {
        return getListPopupWindow().isShowing();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ActivityChooserModel dataModel = this.mAdapter.getDataModel();
        if (dataModel != null) {
            dataModel.registerObserver(this.mModelDataSetObserver);
        }
        this.mIsAttachedToWindow = true;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ActivityChooserModel dataModel = this.mAdapter.getDataModel();
        if (dataModel != null) {
            dataModel.unregisterObserver(this.mModelDataSetObserver);
        }
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.removeGlobalOnLayoutListener(this.mOnGlobalLayoutListener);
        }
        if (isShowingPopup()) {
            boolean dismissPopup = dismissPopup();
        }
        this.mIsAttachedToWindow = false;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int widthMeasureSpec = i;
        int heightMeasureSpec = i2;
        View child = this.mActivityChooserContent;
        if (this.mDefaultActivityButton.getVisibility() != 0) {
            heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(heightMeasureSpec), Declaration.MODULE_REFERENCE);
        }
        measureChild(child, widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(child.getMeasuredWidth(), child.getMeasuredHeight());
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int left, int top, int right, int bottom) {
        boolean z2 = z;
        this.mActivityChooserContent.layout(0, 0, right - left, bottom - top);
        if (!isShowingPopup()) {
            boolean dismissPopup = dismissPopup();
        }
    }

    public ActivityChooserModel getDataModel() {
        return this.mAdapter.getDataModel();
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener listener) {
        PopupWindow.OnDismissListener onDismissListener = listener;
        this.mOnDismissListener = onDismissListener;
    }

    public void setInitialActivityCount(int itemCount) {
        int i = itemCount;
        this.mInitialActivityCount = i;
    }

    public void setDefaultActionButtonContentDescription(int resourceId) {
        int i = resourceId;
        this.mDefaultActionButtonContentDescription = i;
    }

    /* access modifiers changed from: package-private */
    public ListPopupWindow getListPopupWindow() {
        ListPopupWindow listPopupWindow;
        if (this.mListPopupWindow == null) {
            new ListPopupWindow(getContext());
            this.mListPopupWindow = listPopupWindow;
            this.mListPopupWindow.setAdapter(this.mAdapter);
            this.mListPopupWindow.setAnchorView(this);
            this.mListPopupWindow.setModal(true);
            this.mListPopupWindow.setOnItemClickListener(this.mCallbacks);
            this.mListPopupWindow.setOnDismissListener(this.mCallbacks);
        }
        return this.mListPopupWindow;
    }

    /* access modifiers changed from: package-private */
    public void updateAppearance() {
        if (this.mAdapter.getCount() > 0) {
            this.mExpandActivityOverflowButton.setEnabled(true);
        } else {
            this.mExpandActivityOverflowButton.setEnabled(false);
        }
        int activityCount = this.mAdapter.getActivityCount();
        int historySize = this.mAdapter.getHistorySize();
        if (activityCount == 1 || (activityCount > 1 && historySize > 0)) {
            this.mDefaultActivityButton.setVisibility(0);
            ResolveInfo activity = this.mAdapter.getDefaultActivity();
            PackageManager packageManager = getContext().getPackageManager();
            this.mDefaultActivityButtonImage.setImageDrawable(activity.loadIcon(packageManager));
            if (this.mDefaultActionButtonContentDescription != 0) {
                this.mDefaultActivityButton.setContentDescription(getContext().getString(this.mDefaultActionButtonContentDescription, new Object[]{activity.loadLabel(packageManager)}));
            }
        } else {
            this.mDefaultActivityButton.setVisibility(8);
        }
        if (this.mDefaultActivityButton.getVisibility() == 0) {
            this.mActivityChooserContent.setBackgroundDrawable(this.mActivityChooserContentBackground);
        } else {
            this.mActivityChooserContent.setBackgroundDrawable((Drawable) null);
        }
    }

    /* renamed from: android.support.v7.widget.ActivityChooserView$Callbacks */
    private class Callbacks implements AdapterView.OnItemClickListener, View.OnClickListener, View.OnLongClickListener, PopupWindow.OnDismissListener {
        final /* synthetic */ ActivityChooserView this$0;

        Callbacks(ActivityChooserView activityChooserView) {
            this.this$0 = activityChooserView;
        }

        public void onItemClick(AdapterView<?> parent, View view, int i, long j) {
            int position;
            Throwable th;
            View view2 = view;
            int position2 = i;
            long j2 = j;
            switch (((ActivityChooserViewAdapter) parent.getAdapter()).getItemViewType(position2)) {
                case 0:
                    boolean dismissPopup = this.this$0.dismissPopup();
                    if (!this.this$0.mIsSelectingDefaultActivity) {
                        if (this.this$0.mAdapter.getShowDefaultActivity()) {
                            position = position2;
                        } else {
                            position = position2 + 1;
                        }
                        Intent launchIntent = this.this$0.mAdapter.getDataModel().chooseActivity(position);
                        if (launchIntent != null) {
                            Intent addFlags = launchIntent.addFlags(524288);
                            this.this$0.getContext().startActivity(launchIntent);
                            return;
                        }
                        return;
                    } else if (position2 > 0) {
                        this.this$0.mAdapter.getDataModel().setDefaultActivity(position2);
                        return;
                    } else {
                        return;
                    }
                case 1:
                    this.this$0.showPopupUnchecked(Integer.MAX_VALUE);
                    return;
                default:
                    Throwable th2 = th;
                    new IllegalArgumentException();
                    throw th2;
            }
        }

        public void onClick(View view) {
            Throwable th;
            View view2 = view;
            if (view2 == this.this$0.mDefaultActivityButton) {
                boolean dismissPopup = this.this$0.dismissPopup();
                Intent launchIntent = this.this$0.mAdapter.getDataModel().chooseActivity(this.this$0.mAdapter.getDataModel().getActivityIndex(this.this$0.mAdapter.getDefaultActivity()));
                if (launchIntent != null) {
                    Intent addFlags = launchIntent.addFlags(524288);
                    this.this$0.getContext().startActivity(launchIntent);
                }
            } else if (view2 == this.this$0.mExpandActivityOverflowButton) {
                this.this$0.mIsSelectingDefaultActivity = false;
                this.this$0.showPopupUnchecked(this.this$0.mInitialActivityCount);
            } else {
                Throwable th2 = th;
                new IllegalArgumentException();
                throw th2;
            }
        }

        public boolean onLongClick(View view) {
            Throwable th;
            if (view == this.this$0.mDefaultActivityButton) {
                if (this.this$0.mAdapter.getCount() > 0) {
                    this.this$0.mIsSelectingDefaultActivity = true;
                    this.this$0.showPopupUnchecked(this.this$0.mInitialActivityCount);
                }
                return true;
            }
            Throwable th2 = th;
            new IllegalArgumentException();
            throw th2;
        }

        public void onDismiss() {
            notifyOnDismissListener();
            if (this.this$0.mProvider != null) {
                this.this$0.mProvider.subUiVisibilityChanged(false);
            }
        }

        private void notifyOnDismissListener() {
            if (this.this$0.mOnDismissListener != null) {
                this.this$0.mOnDismissListener.onDismiss();
            }
        }
    }

    /* renamed from: android.support.v7.widget.ActivityChooserView$ActivityChooserViewAdapter */
    private class ActivityChooserViewAdapter extends BaseAdapter {
        private static final int ITEM_VIEW_TYPE_ACTIVITY = 0;
        private static final int ITEM_VIEW_TYPE_COUNT = 3;
        private static final int ITEM_VIEW_TYPE_FOOTER = 1;
        public static final int MAX_ACTIVITY_COUNT_DEFAULT = 4;
        public static final int MAX_ACTIVITY_COUNT_UNLIMITED = Integer.MAX_VALUE;
        private ActivityChooserModel mDataModel;
        private boolean mHighlightDefaultActivity;
        private int mMaxActivityCount = 4;
        private boolean mShowDefaultActivity;
        private boolean mShowFooterView;
        final /* synthetic */ ActivityChooserView this$0;

        ActivityChooserViewAdapter(ActivityChooserView activityChooserView) {
            this.this$0 = activityChooserView;
        }

        public void setDataModel(ActivityChooserModel activityChooserModel) {
            ActivityChooserModel dataModel = activityChooserModel;
            ActivityChooserModel oldDataModel = this.this$0.mAdapter.getDataModel();
            if (oldDataModel != null && this.this$0.isShown()) {
                oldDataModel.unregisterObserver(this.this$0.mModelDataSetObserver);
            }
            this.mDataModel = dataModel;
            if (dataModel != null && this.this$0.isShown()) {
                dataModel.registerObserver(this.this$0.mModelDataSetObserver);
            }
            notifyDataSetChanged();
        }

        public int getItemViewType(int i) {
            int position = i;
            if (!this.mShowFooterView || position != getCount() - 1) {
                return 0;
            }
            return 1;
        }

        public int getViewTypeCount() {
            return 3;
        }

        public int getCount() {
            int activityCount = this.mDataModel.getActivityCount();
            if (!this.mShowDefaultActivity && this.mDataModel.getDefaultActivity() != null) {
                activityCount--;
            }
            int count = Math.min(activityCount, this.mMaxActivityCount);
            if (this.mShowFooterView) {
                count++;
            }
            return count;
        }

        public Object getItem(int i) {
            Throwable th;
            int position = i;
            switch (getItemViewType(position)) {
                case 0:
                    if (!this.mShowDefaultActivity && this.mDataModel.getDefaultActivity() != null) {
                        position++;
                    }
                    return this.mDataModel.getActivity(position);
                case 1:
                    return null;
                default:
                    Throwable th2 = th;
                    new IllegalArgumentException();
                    throw th2;
            }
        }

        public long getItemId(int position) {
            return (long) position;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            Throwable th;
            int position = i;
            View convertView = view;
            ViewGroup parent = viewGroup;
            switch (getItemViewType(position)) {
                case 0:
                    if (convertView == null || convertView.getId() != C0425R.C0427id.list_item) {
                        convertView = LayoutInflater.from(this.this$0.getContext()).inflate(C0425R.layout.abc_activity_chooser_view_list_item, parent, false);
                    }
                    PackageManager packageManager = this.this$0.getContext().getPackageManager();
                    ImageView iconView = (ImageView) convertView.findViewById(C0425R.C0427id.icon);
                    ResolveInfo activity = (ResolveInfo) getItem(position);
                    iconView.setImageDrawable(activity.loadIcon(packageManager));
                    ((TextView) convertView.findViewById(C0425R.C0427id.title)).setText(activity.loadLabel(packageManager));
                    if (!this.mShowDefaultActivity || position != 0 || !this.mHighlightDefaultActivity) {
                        convertView.setActivated(false);
                    } else {
                        convertView.setActivated(true);
                    }
                    return convertView;
                case 1:
                    if (convertView == null || convertView.getId() != 1) {
                        convertView = LayoutInflater.from(this.this$0.getContext()).inflate(C0425R.layout.abc_activity_chooser_view_list_item, parent, false);
                        convertView.setId(1);
                        ((TextView) convertView.findViewById(C0425R.C0427id.title)).setText(this.this$0.getContext().getString(C0425R.string.abc_activity_chooser_view_see_all));
                    }
                    return convertView;
                default:
                    Throwable th2 = th;
                    new IllegalArgumentException();
                    throw th2;
            }
        }

        public int measureContentWidth() {
            int oldMaxActivityCount = this.mMaxActivityCount;
            this.mMaxActivityCount = Integer.MAX_VALUE;
            int contentWidth = 0;
            View itemView = null;
            int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
            int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
            int count = getCount();
            for (int i = 0; i < count; i++) {
                itemView = getView(i, itemView, (ViewGroup) null);
                itemView.measure(widthMeasureSpec, heightMeasureSpec);
                contentWidth = Math.max(contentWidth, itemView.getMeasuredWidth());
            }
            this.mMaxActivityCount = oldMaxActivityCount;
            return contentWidth;
        }

        public void setMaxActivityCount(int i) {
            int maxActivityCount = i;
            if (this.mMaxActivityCount != maxActivityCount) {
                this.mMaxActivityCount = maxActivityCount;
                notifyDataSetChanged();
            }
        }

        public ResolveInfo getDefaultActivity() {
            return this.mDataModel.getDefaultActivity();
        }

        public void setShowFooterView(boolean z) {
            boolean showFooterView = z;
            if (this.mShowFooterView != showFooterView) {
                this.mShowFooterView = showFooterView;
                notifyDataSetChanged();
            }
        }

        public int getActivityCount() {
            return this.mDataModel.getActivityCount();
        }

        public int getHistorySize() {
            return this.mDataModel.getHistorySize();
        }

        public ActivityChooserModel getDataModel() {
            return this.mDataModel;
        }

        public void setShowDefaultActivity(boolean z, boolean z2) {
            boolean showDefaultActivity = z;
            boolean highlightDefaultActivity = z2;
            if (this.mShowDefaultActivity != showDefaultActivity || this.mHighlightDefaultActivity != highlightDefaultActivity) {
                this.mShowDefaultActivity = showDefaultActivity;
                this.mHighlightDefaultActivity = highlightDefaultActivity;
                notifyDataSetChanged();
            }
        }

        public boolean getShowDefaultActivity() {
            return this.mShowDefaultActivity;
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* renamed from: android.support.v7.widget.ActivityChooserView$InnerLayout */
    public static class InnerLayout extends LinearLayout {
        private static final int[] TINT_ATTRS = {16842964};

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public InnerLayout(android.content.Context r8, android.util.AttributeSet r9) {
            /*
                r7 = this;
                r0 = r7
                r1 = r8
                r2 = r9
                r4 = r0
                r5 = r1
                r6 = r2
                r4.<init>(r5, r6)
                r4 = r1
                r5 = r2
                int[] r6 = TINT_ATTRS
                android.support.v7.widget.TintTypedArray r4 = android.support.p003v7.widget.TintTypedArray.obtainStyledAttributes((android.content.Context) r4, (android.util.AttributeSet) r5, (int[]) r6)
                r3 = r4
                r4 = r0
                r5 = r3
                r6 = 0
                android.graphics.drawable.Drawable r5 = r5.getDrawable(r6)
                r4.setBackgroundDrawable(r5)
                r4 = r3
                r4.recycle()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.widget.ActivityChooserView.InnerLayout.<init>(android.content.Context, android.util.AttributeSet):void");
        }
    }
}

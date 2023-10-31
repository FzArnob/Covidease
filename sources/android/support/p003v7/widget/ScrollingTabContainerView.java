package android.support.p003v7.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;
import android.support.p003v7.app.ActionBar;
import android.support.p003v7.appcompat.C0425R;
import android.support.p003v7.view.ActionBarPolicy;
import android.support.p003v7.widget.LinearLayoutCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewPropertyAnimator;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import gnu.expr.Declaration;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v7.widget.ScrollingTabContainerView */
public class ScrollingTabContainerView extends HorizontalScrollView implements AdapterView.OnItemSelectedListener {
    private static final int FADE_DURATION = 200;
    private static final String TAG = "ScrollingTabContainerView";
    private static final Interpolator sAlphaInterpolator;
    private boolean mAllowCollapse;
    private int mContentHeight;
    int mMaxTabWidth;
    private int mSelectedTabIndex;
    int mStackedTabMaxWidth;
    private TabClickListener mTabClickListener;
    LinearLayoutCompat mTabLayout = createTabLayout();
    Runnable mTabSelector;
    private Spinner mTabSpinner;
    protected final VisibilityAnimListener mVisAnimListener;
    protected ViewPropertyAnimator mVisibilityAnim;

    static {
        Interpolator interpolator;
        new DecelerateInterpolator();
        sAlphaInterpolator = interpolator;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ScrollingTabContainerView(android.content.Context r11) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r3 = r0
            r4 = r1
            r3.<init>(r4)
            r3 = r0
            android.support.v7.widget.ScrollingTabContainerView$VisibilityAnimListener r4 = new android.support.v7.widget.ScrollingTabContainerView$VisibilityAnimListener
            r9 = r4
            r4 = r9
            r5 = r9
            r6 = r0
            r5.<init>(r6)
            r3.mVisAnimListener = r4
            r3 = r0
            r4 = 0
            r3.setHorizontalScrollBarEnabled(r4)
            r3 = r1
            android.support.v7.view.ActionBarPolicy r3 = android.support.p003v7.view.ActionBarPolicy.get(r3)
            r2 = r3
            r3 = r0
            r4 = r2
            int r4 = r4.getTabContainerHeight()
            r3.setContentHeight(r4)
            r3 = r0
            r4 = r2
            int r4 = r4.getStackedTabMaxWidth()
            r3.mStackedTabMaxWidth = r4
            r3 = r0
            r4 = r0
            android.support.v7.widget.LinearLayoutCompat r4 = r4.createTabLayout()
            r3.mTabLayout = r4
            r3 = r0
            r4 = r0
            android.support.v7.widget.LinearLayoutCompat r4 = r4.mTabLayout
            android.view.ViewGroup$LayoutParams r5 = new android.view.ViewGroup$LayoutParams
            r9 = r5
            r5 = r9
            r6 = r9
            r7 = -2
            r8 = -1
            r6.<init>(r7, r8)
            r3.addView(r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.widget.ScrollingTabContainerView.<init>(android.content.Context):void");
    }

    public void onMeasure(int i, int i2) {
        int widthMeasureSpec = i;
        int i3 = i2;
        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        boolean lockedExpanded = widthMode == 1073741824;
        setFillViewport(lockedExpanded);
        int childCount = this.mTabLayout.getChildCount();
        if (childCount <= 1 || !(widthMode == 1073741824 || widthMode == Integer.MIN_VALUE)) {
            this.mMaxTabWidth = -1;
        } else {
            if (childCount > 2) {
                this.mMaxTabWidth = (int) (((float) View.MeasureSpec.getSize(widthMeasureSpec)) * 0.4f);
            } else {
                this.mMaxTabWidth = View.MeasureSpec.getSize(widthMeasureSpec) / 2;
            }
            this.mMaxTabWidth = Math.min(this.mMaxTabWidth, this.mStackedTabMaxWidth);
        }
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.mContentHeight, Declaration.MODULE_REFERENCE);
        if (!lockedExpanded && this.mAllowCollapse) {
            this.mTabLayout.measure(0, heightMeasureSpec);
            if (this.mTabLayout.getMeasuredWidth() > View.MeasureSpec.getSize(widthMeasureSpec)) {
                performCollapse();
            } else {
                boolean performExpand = performExpand();
            }
        } else {
            boolean performExpand2 = performExpand();
        }
        int oldWidth = getMeasuredWidth();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int newWidth = getMeasuredWidth();
        if (lockedExpanded && oldWidth != newWidth) {
            setTabSelected(this.mSelectedTabIndex);
        }
    }

    private boolean isCollapsed() {
        return this.mTabSpinner != null && this.mTabSpinner.getParent() == this;
    }

    public void setAllowCollapse(boolean allowCollapse) {
        boolean z = allowCollapse;
        this.mAllowCollapse = z;
    }

    private void performCollapse() {
        ViewGroup.LayoutParams layoutParams;
        SpinnerAdapter spinnerAdapter;
        if (!isCollapsed()) {
            if (this.mTabSpinner == null) {
                this.mTabSpinner = createSpinner();
            }
            removeView(this.mTabLayout);
            new ViewGroup.LayoutParams(-2, -1);
            addView(this.mTabSpinner, layoutParams);
            if (this.mTabSpinner.getAdapter() == null) {
                new TabAdapter(this);
                this.mTabSpinner.setAdapter(spinnerAdapter);
            }
            if (this.mTabSelector != null) {
                boolean removeCallbacks = removeCallbacks(this.mTabSelector);
                this.mTabSelector = null;
            }
            this.mTabSpinner.setSelection(this.mSelectedTabIndex);
        }
    }

    private boolean performExpand() {
        ViewGroup.LayoutParams layoutParams;
        if (!isCollapsed()) {
            return false;
        }
        removeView(this.mTabSpinner);
        new ViewGroup.LayoutParams(-2, -1);
        addView(this.mTabLayout, layoutParams);
        setTabSelected(this.mTabSpinner.getSelectedItemPosition());
        return false;
    }

    public void setTabSelected(int i) {
        int position = i;
        this.mSelectedTabIndex = position;
        int tabCount = this.mTabLayout.getChildCount();
        int i2 = 0;
        while (i2 < tabCount) {
            View child = this.mTabLayout.getChildAt(i2);
            boolean isSelected = i2 == position;
            child.setSelected(isSelected);
            if (isSelected) {
                animateToTab(position);
            }
            i2++;
        }
        if (this.mTabSpinner != null && position >= 0) {
            this.mTabSpinner.setSelection(position);
        }
    }

    public void setContentHeight(int contentHeight) {
        this.mContentHeight = contentHeight;
        requestLayout();
    }

    private LinearLayoutCompat createTabLayout() {
        LinearLayoutCompat linearLayoutCompat;
        ViewGroup.LayoutParams layoutParams;
        new LinearLayoutCompat(getContext(), (AttributeSet) null, C0425R.attr.actionBarTabBarStyle);
        LinearLayoutCompat tabLayout = linearLayoutCompat;
        tabLayout.setMeasureWithLargestChildEnabled(true);
        tabLayout.setGravity(17);
        new LinearLayoutCompat.LayoutParams(-2, -1);
        tabLayout.setLayoutParams(layoutParams);
        return tabLayout;
    }

    private Spinner createSpinner() {
        Spinner spinner;
        ViewGroup.LayoutParams layoutParams;
        new AppCompatSpinner(getContext(), (AttributeSet) null, C0425R.attr.actionDropDownStyle);
        Spinner spinner2 = spinner;
        new LinearLayoutCompat.LayoutParams(-2, -1);
        spinner2.setLayoutParams(layoutParams);
        spinner2.setOnItemSelectedListener(this);
        return spinner2;
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        ActionBarPolicy abp = ActionBarPolicy.get(getContext());
        setContentHeight(abp.getTabContainerHeight());
        this.mStackedTabMaxWidth = abp.getStackedTabMaxWidth();
    }

    public void animateToVisibility(int i) {
        int visibility = i;
        if (this.mVisibilityAnim != null) {
            this.mVisibilityAnim.cancel();
        }
        if (visibility == 0) {
            if (getVisibility() != 0) {
                setAlpha(0.0f);
            }
            ViewPropertyAnimator anim = animate().alpha(1.0f);
            ViewPropertyAnimator duration = anim.setDuration(200);
            ViewPropertyAnimator interpolator = anim.setInterpolator(sAlphaInterpolator);
            ViewPropertyAnimator listener = anim.setListener(this.mVisAnimListener.withFinalVisibility(anim, visibility));
            anim.start();
            return;
        }
        ViewPropertyAnimator anim2 = animate().alpha(0.0f);
        ViewPropertyAnimator duration2 = anim2.setDuration(200);
        ViewPropertyAnimator interpolator2 = anim2.setInterpolator(sAlphaInterpolator);
        ViewPropertyAnimator listener2 = anim2.setListener(this.mVisAnimListener.withFinalVisibility(anim2, visibility));
        anim2.start();
    }

    public void animateToTab(int position) {
        Runnable runnable;
        View tabView = this.mTabLayout.getChildAt(position);
        if (this.mTabSelector != null) {
            boolean removeCallbacks = removeCallbacks(this.mTabSelector);
        }
        final View view = tabView;
        new Runnable(this) {
            final /* synthetic */ ScrollingTabContainerView this$0;

            {
                this.this$0 = this$0;
            }

            public void run() {
                this.this$0.smoothScrollTo(view.getLeft() - ((this.this$0.getWidth() - view.getWidth()) / 2), 0);
                this.this$0.mTabSelector = null;
            }
        };
        this.mTabSelector = runnable;
        boolean post = post(this.mTabSelector);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mTabSelector != null) {
            boolean post = post(this.mTabSelector);
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mTabSelector != null) {
            boolean removeCallbacks = removeCallbacks(this.mTabSelector);
        }
    }

    /* access modifiers changed from: package-private */
    public TabView createTabView(ActionBar.Tab tab, boolean z) {
        TabView tabView;
        TabClickListener tabClickListener;
        ViewGroup.LayoutParams layoutParams;
        boolean forAdapter = z;
        new TabView(this, getContext(), tab, forAdapter);
        TabView tabView2 = tabView;
        if (forAdapter) {
            tabView2.setBackgroundDrawable((Drawable) null);
            new AbsListView.LayoutParams(-1, this.mContentHeight);
            tabView2.setLayoutParams(layoutParams);
        } else {
            tabView2.setFocusable(true);
            if (this.mTabClickListener == null) {
                new TabClickListener(this);
                this.mTabClickListener = tabClickListener;
            }
            tabView2.setOnClickListener(this.mTabClickListener);
        }
        return tabView2;
    }

    public void addTab(ActionBar.Tab tab, boolean z) {
        ViewGroup.LayoutParams layoutParams;
        boolean setSelected = z;
        TabView tabView = createTabView(tab, false);
        new LinearLayoutCompat.LayoutParams(0, -1, 1.0f);
        this.mTabLayout.addView(tabView, layoutParams);
        if (this.mTabSpinner != null) {
            ((TabAdapter) this.mTabSpinner.getAdapter()).notifyDataSetChanged();
        }
        if (setSelected) {
            tabView.setSelected(true);
        }
        if (this.mAllowCollapse) {
            requestLayout();
        }
    }

    public void addTab(ActionBar.Tab tab, int position, boolean z) {
        ViewGroup.LayoutParams layoutParams;
        boolean setSelected = z;
        TabView tabView = createTabView(tab, false);
        new LinearLayoutCompat.LayoutParams(0, -1, 1.0f);
        this.mTabLayout.addView(tabView, position, layoutParams);
        if (this.mTabSpinner != null) {
            ((TabAdapter) this.mTabSpinner.getAdapter()).notifyDataSetChanged();
        }
        if (setSelected) {
            tabView.setSelected(true);
        }
        if (this.mAllowCollapse) {
            requestLayout();
        }
    }

    public void updateTab(int position) {
        ((TabView) this.mTabLayout.getChildAt(position)).update();
        if (this.mTabSpinner != null) {
            ((TabAdapter) this.mTabSpinner.getAdapter()).notifyDataSetChanged();
        }
        if (this.mAllowCollapse) {
            requestLayout();
        }
    }

    public void removeTabAt(int position) {
        this.mTabLayout.removeViewAt(position);
        if (this.mTabSpinner != null) {
            ((TabAdapter) this.mTabSpinner.getAdapter()).notifyDataSetChanged();
        }
        if (this.mAllowCollapse) {
            requestLayout();
        }
    }

    public void removeAllTabs() {
        this.mTabLayout.removeAllViews();
        if (this.mTabSpinner != null) {
            ((TabAdapter) this.mTabSpinner.getAdapter()).notifyDataSetChanged();
        }
        if (this.mAllowCollapse) {
            requestLayout();
        }
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        AdapterView<?> adapterView2 = adapterView;
        int i2 = i;
        long j2 = j;
        ((TabView) view).getTab().select();
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    /* renamed from: android.support.v7.widget.ScrollingTabContainerView$TabView */
    private class TabView extends LinearLayout {
        private final int[] BG_ATTRS;
        private View mCustomView;
        private ImageView mIconView;
        private ActionBar.Tab mTab;
        private TextView mTextView;
        final /* synthetic */ ScrollingTabContainerView this$0;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public TabView(android.support.p003v7.widget.ScrollingTabContainerView r13, android.content.Context r14, android.support.p003v7.app.ActionBar.Tab r15, boolean r16) {
            /*
                r12 = this;
                r0 = r12
                r1 = r13
                r2 = r14
                r3 = r15
                r4 = r16
                r6 = r0
                r7 = r1
                r6.this$0 = r7
                r6 = r0
                r7 = r2
                r8 = 0
                int r9 = android.support.p003v7.appcompat.C0425R.attr.actionBarTabStyle
                r6.<init>(r7, r8, r9)
                r6 = r0
                r7 = 1
                int[] r7 = new int[r7]
                r11 = r7
                r7 = r11
                r8 = r11
                r9 = 0
                r10 = 16842964(0x10100d4, float:2.3694152E-38)
                r8[r9] = r10
                r6.BG_ATTRS = r7
                r6 = r0
                r7 = r3
                r6.mTab = r7
                r6 = r2
                r7 = 0
                r8 = r0
                int[] r8 = r8.BG_ATTRS
                int r9 = android.support.p003v7.appcompat.C0425R.attr.actionBarTabStyle
                r10 = 0
                android.support.v7.widget.TintTypedArray r6 = android.support.p003v7.widget.TintTypedArray.obtainStyledAttributes(r6, r7, r8, r9, r10)
                r5 = r6
                r6 = r5
                r7 = 0
                boolean r6 = r6.hasValue(r7)
                if (r6 == 0) goto L_0x0044
                r6 = r0
                r7 = r5
                r8 = 0
                android.graphics.drawable.Drawable r7 = r7.getDrawable(r8)
                r6.setBackgroundDrawable(r7)
            L_0x0044:
                r6 = r5
                r6.recycle()
                r6 = r4
                if (r6 == 0) goto L_0x0052
                r6 = r0
                r7 = 8388627(0x800013, float:1.175497E-38)
                r6.setGravity(r7)
            L_0x0052:
                r6 = r0
                r6.update()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.widget.ScrollingTabContainerView.TabView.<init>(android.support.v7.widget.ScrollingTabContainerView, android.content.Context, android.support.v7.app.ActionBar$Tab, boolean):void");
        }

        public void bindTab(ActionBar.Tab tab) {
            this.mTab = tab;
            update();
        }

        public void setSelected(boolean z) {
            boolean selected = z;
            boolean changed = isSelected() != selected;
            super.setSelected(selected);
            if (changed && selected) {
                sendAccessibilityEvent(4);
            }
        }

        public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            AccessibilityEvent event = accessibilityEvent;
            super.onInitializeAccessibilityEvent(event);
            event.setClassName(ActionBar.Tab.class.getName());
        }

        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            AccessibilityNodeInfo info = accessibilityNodeInfo;
            super.onInitializeAccessibilityNodeInfo(info);
            info.setClassName(ActionBar.Tab.class.getName());
        }

        public void onMeasure(int widthMeasureSpec, int i) {
            int heightMeasureSpec = i;
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            if (this.this$0.mMaxTabWidth > 0 && getMeasuredWidth() > this.this$0.mMaxTabWidth) {
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(this.this$0.mMaxTabWidth, Declaration.MODULE_REFERENCE), heightMeasureSpec);
            }
        }

        public void update() {
            CharSequence contentDescription;
            TextView textView;
            LinearLayout.LayoutParams layoutParams;
            ImageView imageView;
            LinearLayout.LayoutParams layoutParams2;
            ActionBar.Tab tab = this.mTab;
            View custom = tab.getCustomView();
            if (custom != null) {
                ViewParent customParent = custom.getParent();
                if (customParent != this) {
                    if (customParent != null) {
                        ((ViewGroup) customParent).removeView(custom);
                    }
                    addView(custom);
                }
                this.mCustomView = custom;
                if (this.mTextView != null) {
                    this.mTextView.setVisibility(8);
                }
                if (this.mIconView != null) {
                    this.mIconView.setVisibility(8);
                    this.mIconView.setImageDrawable((Drawable) null);
                    return;
                }
                return;
            }
            if (this.mCustomView != null) {
                removeView(this.mCustomView);
                this.mCustomView = null;
            }
            Drawable icon = tab.getIcon();
            CharSequence text = tab.getText();
            if (icon != null) {
                if (this.mIconView == null) {
                    new AppCompatImageView(getContext());
                    ImageView iconView = imageView;
                    new LinearLayout.LayoutParams(-2, -2);
                    LinearLayout.LayoutParams lp = layoutParams2;
                    lp.gravity = 16;
                    iconView.setLayoutParams(lp);
                    addView(iconView, 0);
                    this.mIconView = iconView;
                }
                this.mIconView.setImageDrawable(icon);
                this.mIconView.setVisibility(0);
            } else if (this.mIconView != null) {
                this.mIconView.setVisibility(8);
                this.mIconView.setImageDrawable((Drawable) null);
            }
            boolean hasText = !TextUtils.isEmpty(text);
            if (hasText) {
                if (this.mTextView == null) {
                    new AppCompatTextView(getContext(), (AttributeSet) null, C0425R.attr.actionBarTabTextStyle);
                    TextView textView2 = textView;
                    textView2.setEllipsize(TextUtils.TruncateAt.END);
                    new LinearLayout.LayoutParams(-2, -2);
                    LinearLayout.LayoutParams lp2 = layoutParams;
                    lp2.gravity = 16;
                    textView2.setLayoutParams(lp2);
                    addView(textView2);
                    this.mTextView = textView2;
                }
                this.mTextView.setText(text);
                this.mTextView.setVisibility(0);
            } else if (this.mTextView != null) {
                this.mTextView.setVisibility(8);
                this.mTextView.setText((CharSequence) null);
            }
            if (this.mIconView != null) {
                this.mIconView.setContentDescription(tab.getContentDescription());
            }
            if (hasText) {
                contentDescription = null;
            } else {
                contentDescription = tab.getContentDescription();
            }
            TooltipCompat.setTooltipText(this, contentDescription);
        }

        public ActionBar.Tab getTab() {
            return this.mTab;
        }
    }

    /* renamed from: android.support.v7.widget.ScrollingTabContainerView$TabAdapter */
    private class TabAdapter extends BaseAdapter {
        final /* synthetic */ ScrollingTabContainerView this$0;

        TabAdapter(ScrollingTabContainerView scrollingTabContainerView) {
            this.this$0 = scrollingTabContainerView;
        }

        public int getCount() {
            return this.this$0.mTabLayout.getChildCount();
        }

        public Object getItem(int position) {
            return ((TabView) this.this$0.mTabLayout.getChildAt(position)).getTab();
        }

        public long getItemId(int position) {
            return (long) position;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            int position = i;
            View convertView = view;
            ViewGroup viewGroup2 = viewGroup;
            if (convertView == null) {
                convertView = this.this$0.createTabView((ActionBar.Tab) getItem(position), true);
            } else {
                ((TabView) convertView).bindTab((ActionBar.Tab) getItem(position));
            }
            return convertView;
        }
    }

    /* renamed from: android.support.v7.widget.ScrollingTabContainerView$TabClickListener */
    private class TabClickListener implements View.OnClickListener {
        final /* synthetic */ ScrollingTabContainerView this$0;

        TabClickListener(ScrollingTabContainerView scrollingTabContainerView) {
            this.this$0 = scrollingTabContainerView;
        }

        public void onClick(View view) {
            View view2 = view;
            ((TabView) view2).getTab().select();
            int tabCount = this.this$0.mTabLayout.getChildCount();
            for (int i = 0; i < tabCount; i++) {
                View child = this.this$0.mTabLayout.getChildAt(i);
                child.setSelected(child == view2);
            }
        }
    }

    /* renamed from: android.support.v7.widget.ScrollingTabContainerView$VisibilityAnimListener */
    protected class VisibilityAnimListener extends AnimatorListenerAdapter {
        private boolean mCanceled = false;
        private int mFinalVisibility;
        final /* synthetic */ ScrollingTabContainerView this$0;

        protected VisibilityAnimListener(ScrollingTabContainerView this$02) {
            this.this$0 = this$02;
        }

        public VisibilityAnimListener withFinalVisibility(ViewPropertyAnimator animation, int visibility) {
            this.mFinalVisibility = visibility;
            this.this$0.mVisibilityAnim = animation;
            return this;
        }

        public void onAnimationStart(Animator animator) {
            Animator animator2 = animator;
            this.this$0.setVisibility(0);
            this.mCanceled = false;
        }

        public void onAnimationEnd(Animator animator) {
            Animator animator2 = animator;
            if (!this.mCanceled) {
                this.this$0.mVisibilityAnim = null;
                this.this$0.setVisibility(this.mFinalVisibility);
            }
        }

        public void onAnimationCancel(Animator animator) {
            Animator animator2 = animator;
            this.mCanceled = true;
        }
    }
}

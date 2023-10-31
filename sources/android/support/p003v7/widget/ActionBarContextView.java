package android.support.p003v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;
import android.support.p000v4.view.ViewCompat;
import android.support.p000v4.view.ViewPropertyAnimatorCompat;
import android.support.p003v7.appcompat.C0425R;
import android.support.p003v7.view.ActionMode;
import android.support.p003v7.view.menu.MenuBuilder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import android.widget.TextView;
import gnu.expr.Declaration;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v7.widget.ActionBarContextView */
public class ActionBarContextView extends AbsActionBarView {
    private static final String TAG = "ActionBarContextView";
    private View mClose;
    private int mCloseItemLayout;
    private View mCustomView;
    private CharSequence mSubtitle;
    private int mSubtitleStyleRes;
    private TextView mSubtitleView;
    private CharSequence mTitle;
    private LinearLayout mTitleLayout;
    private boolean mTitleOptional;
    private int mTitleStyleRes;
    private TextView mTitleView;

    public /* bridge */ /* synthetic */ void animateToVisibility(int i) {
        super.animateToVisibility(i);
    }

    public /* bridge */ /* synthetic */ boolean canShowOverflowMenu() {
        return super.canShowOverflowMenu();
    }

    public /* bridge */ /* synthetic */ void dismissPopupMenus() {
        super.dismissPopupMenus();
    }

    public /* bridge */ /* synthetic */ int getAnimatedVisibility() {
        return super.getAnimatedVisibility();
    }

    public /* bridge */ /* synthetic */ int getContentHeight() {
        return super.getContentHeight();
    }

    public /* bridge */ /* synthetic */ boolean isOverflowMenuShowPending() {
        return super.isOverflowMenuShowPending();
    }

    public /* bridge */ /* synthetic */ boolean isOverflowReserved() {
        return super.isOverflowReserved();
    }

    public /* bridge */ /* synthetic */ boolean onHoverEvent(MotionEvent motionEvent) {
        return super.onHoverEvent(motionEvent);
    }

    public /* bridge */ /* synthetic */ boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    public /* bridge */ /* synthetic */ void postShowOverflowMenu() {
        super.postShowOverflowMenu();
    }

    public /* bridge */ /* synthetic */ void setVisibility(int i) {
        super.setVisibility(i);
    }

    public /* bridge */ /* synthetic */ ViewPropertyAnimatorCompat setupAnimatorToVisibility(int i, long j) {
        return super.setupAnimatorToVisibility(i, j);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ActionBarContextView(Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ActionBarContextView(Context context, AttributeSet attrs) {
        this(context, attrs, C0425R.attr.actionModeStyle);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ActionBarContextView(android.content.Context r11, android.util.AttributeSet r12, int r13) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            r2 = r12
            r3 = r13
            r5 = r0
            r6 = r1
            r7 = r2
            r8 = r3
            r5.<init>(r6, r7, r8)
            r5 = r1
            r6 = r2
            int[] r7 = android.support.p003v7.appcompat.C0425R.styleable.ActionMode
            r8 = r3
            r9 = 0
            android.support.v7.widget.TintTypedArray r5 = android.support.p003v7.widget.TintTypedArray.obtainStyledAttributes(r5, r6, r7, r8, r9)
            r4 = r5
            r5 = r0
            r6 = r4
            int r7 = android.support.p003v7.appcompat.C0425R.styleable.ActionMode_background
            android.graphics.drawable.Drawable r6 = r6.getDrawable(r7)
            android.support.p000v4.view.ViewCompat.setBackground(r5, r6)
            r5 = r0
            r6 = r4
            int r7 = android.support.p003v7.appcompat.C0425R.styleable.ActionMode_titleTextStyle
            r8 = 0
            int r6 = r6.getResourceId(r7, r8)
            r5.mTitleStyleRes = r6
            r5 = r0
            r6 = r4
            int r7 = android.support.p003v7.appcompat.C0425R.styleable.ActionMode_subtitleTextStyle
            r8 = 0
            int r6 = r6.getResourceId(r7, r8)
            r5.mSubtitleStyleRes = r6
            r5 = r0
            r6 = r4
            int r7 = android.support.p003v7.appcompat.C0425R.styleable.ActionMode_height
            r8 = 0
            int r6 = r6.getLayoutDimension((int) r7, (int) r8)
            r5.mContentHeight = r6
            r5 = r0
            r6 = r4
            int r7 = android.support.p003v7.appcompat.C0425R.styleable.ActionMode_closeItemLayout
            int r8 = android.support.p003v7.appcompat.C0425R.layout.abc_action_mode_close_item_material
            int r6 = r6.getResourceId(r7, r8)
            r5.mCloseItemLayout = r6
            r5 = r4
            r5.recycle()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.widget.ActionBarContextView.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mActionMenuPresenter != null) {
            boolean hideOverflowMenu = this.mActionMenuPresenter.hideOverflowMenu();
            boolean hideSubMenus = this.mActionMenuPresenter.hideSubMenus();
        }
    }

    public void setContentHeight(int height) {
        int i = height;
        this.mContentHeight = i;
    }

    public void setCustomView(View view) {
        View view2 = view;
        if (this.mCustomView != null) {
            removeView(this.mCustomView);
        }
        this.mCustomView = view2;
        if (!(view2 == null || this.mTitleLayout == null)) {
            removeView(this.mTitleLayout);
            this.mTitleLayout = null;
        }
        if (view2 != null) {
            addView(view2);
        }
        requestLayout();
    }

    public void setTitle(CharSequence title) {
        this.mTitle = title;
        initTitle();
    }

    public void setSubtitle(CharSequence subtitle) {
        this.mSubtitle = subtitle;
        initTitle();
    }

    public CharSequence getTitle() {
        return this.mTitle;
    }

    public CharSequence getSubtitle() {
        return this.mSubtitle;
    }

    private void initTitle() {
        if (this.mTitleLayout == null) {
            View inflate = LayoutInflater.from(getContext()).inflate(C0425R.layout.abc_action_bar_title_item, this);
            this.mTitleLayout = (LinearLayout) getChildAt(getChildCount() - 1);
            this.mTitleView = (TextView) this.mTitleLayout.findViewById(C0425R.C0427id.action_bar_title);
            this.mSubtitleView = (TextView) this.mTitleLayout.findViewById(C0425R.C0427id.action_bar_subtitle);
            if (this.mTitleStyleRes != 0) {
                this.mTitleView.setTextAppearance(getContext(), this.mTitleStyleRes);
            }
            if (this.mSubtitleStyleRes != 0) {
                this.mSubtitleView.setTextAppearance(getContext(), this.mSubtitleStyleRes);
            }
        }
        this.mTitleView.setText(this.mTitle);
        this.mSubtitleView.setText(this.mSubtitle);
        boolean hasTitle = !TextUtils.isEmpty(this.mTitle);
        boolean hasSubtitle = !TextUtils.isEmpty(this.mSubtitle);
        this.mSubtitleView.setVisibility(hasSubtitle ? 0 : 8);
        this.mTitleLayout.setVisibility((hasTitle || hasSubtitle) ? 0 : 8);
        if (this.mTitleLayout.getParent() == null) {
            addView(this.mTitleLayout);
        }
    }

    public void initForMode(ActionMode actionMode) {
        View.OnClickListener onClickListener;
        ActionMenuPresenter actionMenuPresenter;
        ViewGroup.LayoutParams layoutParams;
        ActionMode mode = actionMode;
        if (this.mClose == null) {
            this.mClose = LayoutInflater.from(getContext()).inflate(this.mCloseItemLayout, this, false);
            addView(this.mClose);
        } else if (this.mClose.getParent() == null) {
            addView(this.mClose);
        }
        final ActionMode actionMode2 = mode;
        new View.OnClickListener(this) {
            final /* synthetic */ ActionBarContextView this$0;

            {
                this.this$0 = this$0;
            }

            public void onClick(View view) {
                View view2 = view;
                actionMode2.finish();
            }
        };
        this.mClose.findViewById(C0425R.C0427id.action_mode_close_button).setOnClickListener(onClickListener);
        MenuBuilder menu = (MenuBuilder) mode.getMenu();
        if (this.mActionMenuPresenter != null) {
            boolean dismissPopupMenus = this.mActionMenuPresenter.dismissPopupMenus();
        }
        new ActionMenuPresenter(getContext());
        this.mActionMenuPresenter = actionMenuPresenter;
        this.mActionMenuPresenter.setReserveOverflow(true);
        new ViewGroup.LayoutParams(-2, -1);
        menu.addMenuPresenter(this.mActionMenuPresenter, this.mPopupContext);
        this.mMenuView = (ActionMenuView) this.mActionMenuPresenter.getMenuView(this);
        ViewCompat.setBackground(this.mMenuView, (Drawable) null);
        addView(this.mMenuView, layoutParams);
    }

    public void closeMode() {
        if (this.mClose == null) {
            killMode();
        }
    }

    public void killMode() {
        removeAllViews();
        this.mCustomView = null;
        this.mMenuView = null;
    }

    public boolean showOverflowMenu() {
        if (this.mActionMenuPresenter != null) {
            return this.mActionMenuPresenter.showOverflowMenu();
        }
        return false;
    }

    public boolean hideOverflowMenu() {
        if (this.mActionMenuPresenter != null) {
            return this.mActionMenuPresenter.hideOverflowMenu();
        }
        return false;
    }

    public boolean isOverflowMenuShowing() {
        if (this.mActionMenuPresenter != null) {
            return this.mActionMenuPresenter.isOverflowMenuShowing();
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        ViewGroup.LayoutParams layoutParams;
        new ViewGroup.MarginLayoutParams(-1, -2);
        return layoutParams;
    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        ViewGroup.LayoutParams layoutParams;
        new ViewGroup.MarginLayoutParams(getContext(), attrs);
        return layoutParams;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int size;
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        int widthMeasureSpec = i;
        int heightMeasureSpec = i2;
        if (View.MeasureSpec.getMode(widthMeasureSpec) != 1073741824) {
            Throwable th3 = th2;
            new StringBuilder();
            new IllegalStateException(sb2.append(getClass().getSimpleName()).append(" can only be used ").append("with android:layout_width=\"match_parent\" (or fill_parent)").toString());
            throw th3;
        } else if (View.MeasureSpec.getMode(heightMeasureSpec) == 0) {
            Throwable th4 = th;
            new StringBuilder();
            new IllegalStateException(sb.append(getClass().getSimpleName()).append(" can only be used ").append("with android:layout_height=\"wrap_content\"").toString());
            throw th4;
        } else {
            int contentWidth = View.MeasureSpec.getSize(widthMeasureSpec);
            if (this.mContentHeight > 0) {
                size = this.mContentHeight;
            } else {
                size = View.MeasureSpec.getSize(heightMeasureSpec);
            }
            int maxHeight = size;
            int verticalPadding = getPaddingTop() + getPaddingBottom();
            int availableWidth = (contentWidth - getPaddingLeft()) - getPaddingRight();
            int height = maxHeight - verticalPadding;
            int childSpecHeight = View.MeasureSpec.makeMeasureSpec(height, Integer.MIN_VALUE);
            if (this.mClose != null) {
                int availableWidth2 = measureChildView(this.mClose, availableWidth, childSpecHeight, 0);
                ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) this.mClose.getLayoutParams();
                availableWidth = availableWidth2 - (lp.leftMargin + lp.rightMargin);
            }
            if (this.mMenuView != null) {
                if (this.mMenuView.getParent() == this) {
                    availableWidth = measureChildView(this.mMenuView, availableWidth, childSpecHeight, 0);
                }
            }
            if (this.mTitleLayout != null) {
                if (this.mCustomView == null) {
                    if (this.mTitleOptional) {
                        this.mTitleLayout.measure(View.MeasureSpec.makeMeasureSpec(0, 0), childSpecHeight);
                        int titleWidth = this.mTitleLayout.getMeasuredWidth();
                        boolean titleFits = titleWidth <= availableWidth;
                        if (titleFits) {
                            availableWidth -= titleWidth;
                        }
                        this.mTitleLayout.setVisibility(titleFits ? 0 : 8);
                    } else {
                        availableWidth = measureChildView(this.mTitleLayout, availableWidth, childSpecHeight, 0);
                    }
                }
            }
            if (this.mCustomView != null) {
                ViewGroup.LayoutParams lp2 = this.mCustomView.getLayoutParams();
                this.mCustomView.measure(View.MeasureSpec.makeMeasureSpec(lp2.width >= 0 ? Math.min(lp2.width, availableWidth) : availableWidth, lp2.width != -2 ? Declaration.MODULE_REFERENCE : Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(lp2.height >= 0 ? Math.min(lp2.height, height) : height, lp2.height != -2 ? Declaration.MODULE_REFERENCE : Integer.MIN_VALUE));
            }
            if (this.mContentHeight <= 0) {
                int measuredHeight = 0;
                int count = getChildCount();
                for (int i3 = 0; i3 < count; i3++) {
                    int paddedViewHeight = getChildAt(i3).getMeasuredHeight() + verticalPadding;
                    if (paddedViewHeight > measuredHeight) {
                        measuredHeight = paddedViewHeight;
                    }
                }
                setMeasuredDimension(contentWidth, measuredHeight);
                return;
            }
            setMeasuredDimension(contentWidth, maxHeight);
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int endMargin;
        boolean z2 = z;
        int l = i;
        int t = i2;
        int r = i3;
        int b = i4;
        boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
        int x = isLayoutRtl ? (r - l) - getPaddingRight() : getPaddingLeft();
        int y = getPaddingTop();
        int contentHeight = ((b - t) - getPaddingTop()) - getPaddingBottom();
        if (!(this.mClose == null || this.mClose.getVisibility() == 8)) {
            ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) this.mClose.getLayoutParams();
            int startMargin = isLayoutRtl ? lp.rightMargin : lp.leftMargin;
            if (isLayoutRtl) {
                endMargin = lp.leftMargin;
            } else {
                endMargin = lp.rightMargin;
            }
            int x2 = next(x, startMargin, isLayoutRtl);
            x = next(x2 + positionChild(this.mClose, x2, y, contentHeight, isLayoutRtl), endMargin, isLayoutRtl);
        }
        if (!(this.mTitleLayout == null || this.mCustomView != null || this.mTitleLayout.getVisibility() == 8)) {
            x += positionChild(this.mTitleLayout, x, y, contentHeight, isLayoutRtl);
        }
        if (this.mCustomView != null) {
            int x3 = x + positionChild(this.mCustomView, x, y, contentHeight, isLayoutRtl);
        }
        int x4 = isLayoutRtl ? getPaddingLeft() : (r - l) - getPaddingRight();
        if (this.mMenuView != null) {
            int x5 = x4 + positionChild(this.mMenuView, x4, y, contentHeight, !isLayoutRtl);
        }
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        AccessibilityEvent event = accessibilityEvent;
        if (event.getEventType() == 32) {
            event.setSource(this);
            event.setClassName(getClass().getName());
            event.setPackageName(getContext().getPackageName());
            event.setContentDescription(this.mTitle);
            return;
        }
        super.onInitializeAccessibilityEvent(event);
    }

    public void setTitleOptional(boolean z) {
        boolean titleOptional = z;
        if (titleOptional != this.mTitleOptional) {
            requestLayout();
        }
        this.mTitleOptional = titleOptional;
    }

    public boolean isTitleOptional() {
        return this.mTitleOptional;
    }
}

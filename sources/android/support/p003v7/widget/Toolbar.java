package android.support.p003v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.MenuRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.p000v4.view.AbsSavedState;
import android.support.p000v4.view.GravityCompat;
import android.support.p000v4.view.MarginLayoutParamsCompat;
import android.support.p000v4.view.ViewCompat;
import android.support.p003v7.app.ActionBar;
import android.support.p003v7.appcompat.C0425R;
import android.support.p003v7.content.res.AppCompatResources;
import android.support.p003v7.view.CollapsibleActionView;
import android.support.p003v7.view.SupportMenuInflater;
import android.support.p003v7.view.menu.MenuBuilder;
import android.support.p003v7.view.menu.MenuItemImpl;
import android.support.p003v7.view.menu.MenuPresenter;
import android.support.p003v7.view.menu.MenuView;
import android.support.p003v7.view.menu.SubMenuBuilder;
import android.support.p003v7.widget.ActionMenuView;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import gnu.expr.Declaration;
import java.util.ArrayList;
import java.util.List;

/* renamed from: android.support.v7.widget.Toolbar */
public class Toolbar extends ViewGroup {
    private static final String TAG = "Toolbar";
    private MenuPresenter.Callback mActionMenuPresenterCallback;
    int mButtonGravity;
    ImageButton mCollapseButtonView;
    private CharSequence mCollapseDescription;
    private Drawable mCollapseIcon;
    private boolean mCollapsible;
    private int mContentInsetEndWithActions;
    private int mContentInsetStartWithNavigation;
    private RtlSpacingHelper mContentInsets;
    private boolean mEatingHover;
    private boolean mEatingTouch;
    View mExpandedActionView;
    private ExpandedActionViewMenuPresenter mExpandedMenuPresenter;
    private int mGravity;
    private final ArrayList<View> mHiddenViews;
    private ImageView mLogoView;
    private int mMaxButtonHeight;
    private MenuBuilder.Callback mMenuBuilderCallback;
    private ActionMenuView mMenuView;
    private final ActionMenuView.OnMenuItemClickListener mMenuViewItemClickListener;
    private ImageButton mNavButtonView;
    OnMenuItemClickListener mOnMenuItemClickListener;
    private ActionMenuPresenter mOuterActionMenuPresenter;
    private Context mPopupContext;
    private int mPopupTheme;
    private final Runnable mShowOverflowMenuRunnable;
    private CharSequence mSubtitleText;
    private int mSubtitleTextAppearance;
    private int mSubtitleTextColor;
    private TextView mSubtitleTextView;
    private final int[] mTempMargins;
    private final ArrayList<View> mTempViews;
    private int mTitleMarginBottom;
    private int mTitleMarginEnd;
    private int mTitleMarginStart;
    private int mTitleMarginTop;
    private CharSequence mTitleText;
    private int mTitleTextAppearance;
    private int mTitleTextColor;
    private TextView mTitleTextView;
    private ToolbarWidgetWrapper mWrapper;

    /* renamed from: android.support.v7.widget.Toolbar$OnMenuItemClickListener */
    public interface OnMenuItemClickListener {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public Toolbar(Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public Toolbar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, C0425R.attr.toolbarStyle);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Toolbar(android.content.Context r31, @android.support.annotation.Nullable android.util.AttributeSet r32, int r33) {
        /*
            r30 = this;
            r2 = r30
            r3 = r31
            r4 = r32
            r5 = r33
            r22 = r2
            r23 = r3
            r24 = r4
            r25 = r5
            r22.<init>(r23, r24, r25)
            r22 = r2
            r23 = 8388627(0x800013, float:1.175497E-38)
            r0 = r23
            r1 = r22
            r1.mGravity = r0
            r22 = r2
            java.util.ArrayList r23 = new java.util.ArrayList
            r28 = r23
            r23 = r28
            r24 = r28
            r24.<init>()
            r0 = r23
            r1 = r22
            r1.mTempViews = r0
            r22 = r2
            java.util.ArrayList r23 = new java.util.ArrayList
            r28 = r23
            r23 = r28
            r24 = r28
            r24.<init>()
            r0 = r23
            r1 = r22
            r1.mHiddenViews = r0
            r22 = r2
            r23 = 2
            r0 = r23
            int[] r0 = new int[r0]
            r23 = r0
            r0 = r23
            r1 = r22
            r1.mTempMargins = r0
            r22 = r2
            android.support.v7.widget.Toolbar$1 r23 = new android.support.v7.widget.Toolbar$1
            r28 = r23
            r23 = r28
            r24 = r28
            r25 = r2
            r24.<init>(r25)
            r0 = r23
            r1 = r22
            r1.mMenuViewItemClickListener = r0
            r22 = r2
            android.support.v7.widget.Toolbar$2 r23 = new android.support.v7.widget.Toolbar$2
            r28 = r23
            r23 = r28
            r24 = r28
            r25 = r2
            r24.<init>(r25)
            r0 = r23
            r1 = r22
            r1.mShowOverflowMenuRunnable = r0
            r22 = r2
            android.content.Context r22 = r22.getContext()
            r23 = r4
            int[] r24 = android.support.p003v7.appcompat.C0425R.styleable.Toolbar
            r25 = r5
            r26 = 0
            android.support.v7.widget.TintTypedArray r22 = android.support.p003v7.widget.TintTypedArray.obtainStyledAttributes(r22, r23, r24, r25, r26)
            r6 = r22
            r22 = r2
            r23 = r6
            int r24 = android.support.p003v7.appcompat.C0425R.styleable.Toolbar_titleTextAppearance
            r25 = 0
            int r23 = r23.getResourceId(r24, r25)
            r0 = r23
            r1 = r22
            r1.mTitleTextAppearance = r0
            r22 = r2
            r23 = r6
            int r24 = android.support.p003v7.appcompat.C0425R.styleable.Toolbar_subtitleTextAppearance
            r25 = 0
            int r23 = r23.getResourceId(r24, r25)
            r0 = r23
            r1 = r22
            r1.mSubtitleTextAppearance = r0
            r22 = r2
            r23 = r6
            int r24 = android.support.p003v7.appcompat.C0425R.styleable.Toolbar_android_gravity
            r25 = r2
            r0 = r25
            int r0 = r0.mGravity
            r25 = r0
            int r23 = r23.getInteger(r24, r25)
            r0 = r23
            r1 = r22
            r1.mGravity = r0
            r22 = r2
            r23 = r6
            int r24 = android.support.p003v7.appcompat.C0425R.styleable.Toolbar_buttonGravity
            r25 = 48
            int r23 = r23.getInteger(r24, r25)
            r0 = r23
            r1 = r22
            r1.mButtonGravity = r0
            r22 = r6
            int r23 = android.support.p003v7.appcompat.C0425R.styleable.Toolbar_titleMargin
            r24 = 0
            int r22 = r22.getDimensionPixelOffset(r23, r24)
            r7 = r22
            r22 = r6
            int r23 = android.support.p003v7.appcompat.C0425R.styleable.Toolbar_titleMargins
            boolean r22 = r22.hasValue(r23)
            if (r22 == 0) goto L_0x0102
            r22 = r6
            int r23 = android.support.p003v7.appcompat.C0425R.styleable.Toolbar_titleMargins
            r24 = r7
            int r22 = r22.getDimensionPixelOffset(r23, r24)
            r7 = r22
        L_0x0102:
            r22 = r2
            r23 = r2
            r24 = r2
            r25 = r2
            r26 = r7
            r28 = r25
            r29 = r26
            r25 = r29
            r26 = r28
            r27 = r29
            r0 = r27
            r1 = r26
            r1.mTitleMarginBottom = r0
            r28 = r24
            r29 = r25
            r24 = r29
            r25 = r28
            r26 = r29
            r0 = r26
            r1 = r25
            r1.mTitleMarginTop = r0
            r28 = r23
            r29 = r24
            r23 = r29
            r24 = r28
            r25 = r29
            r0 = r25
            r1 = r24
            r1.mTitleMarginEnd = r0
            r0 = r23
            r1 = r22
            r1.mTitleMarginStart = r0
            r22 = r6
            int r23 = android.support.p003v7.appcompat.C0425R.styleable.Toolbar_titleMarginStart
            r24 = -1
            int r22 = r22.getDimensionPixelOffset(r23, r24)
            r8 = r22
            r22 = r8
            if (r22 < 0) goto L_0x015c
            r22 = r2
            r23 = r8
            r0 = r23
            r1 = r22
            r1.mTitleMarginStart = r0
        L_0x015c:
            r22 = r6
            int r23 = android.support.p003v7.appcompat.C0425R.styleable.Toolbar_titleMarginEnd
            r24 = -1
            int r22 = r22.getDimensionPixelOffset(r23, r24)
            r9 = r22
            r22 = r9
            if (r22 < 0) goto L_0x0176
            r22 = r2
            r23 = r9
            r0 = r23
            r1 = r22
            r1.mTitleMarginEnd = r0
        L_0x0176:
            r22 = r6
            int r23 = android.support.p003v7.appcompat.C0425R.styleable.Toolbar_titleMarginTop
            r24 = -1
            int r22 = r22.getDimensionPixelOffset(r23, r24)
            r10 = r22
            r22 = r10
            if (r22 < 0) goto L_0x0190
            r22 = r2
            r23 = r10
            r0 = r23
            r1 = r22
            r1.mTitleMarginTop = r0
        L_0x0190:
            r22 = r6
            int r23 = android.support.p003v7.appcompat.C0425R.styleable.Toolbar_titleMarginBottom
            r24 = -1
            int r22 = r22.getDimensionPixelOffset(r23, r24)
            r11 = r22
            r22 = r11
            if (r22 < 0) goto L_0x01aa
            r22 = r2
            r23 = r11
            r0 = r23
            r1 = r22
            r1.mTitleMarginBottom = r0
        L_0x01aa:
            r22 = r2
            r23 = r6
            int r24 = android.support.p003v7.appcompat.C0425R.styleable.Toolbar_maxButtonHeight
            r25 = -1
            int r23 = r23.getDimensionPixelSize(r24, r25)
            r0 = r23
            r1 = r22
            r1.mMaxButtonHeight = r0
            r22 = r6
            int r23 = android.support.p003v7.appcompat.C0425R.styleable.Toolbar_contentInsetStart
            r24 = -2147483648(0xffffffff80000000, float:-0.0)
            int r22 = r22.getDimensionPixelOffset(r23, r24)
            r12 = r22
            r22 = r6
            int r23 = android.support.p003v7.appcompat.C0425R.styleable.Toolbar_contentInsetEnd
            r24 = -2147483648(0xffffffff80000000, float:-0.0)
            int r22 = r22.getDimensionPixelOffset(r23, r24)
            r13 = r22
            r22 = r6
            int r23 = android.support.p003v7.appcompat.C0425R.styleable.Toolbar_contentInsetLeft
            r24 = 0
            int r22 = r22.getDimensionPixelSize(r23, r24)
            r14 = r22
            r22 = r6
            int r23 = android.support.p003v7.appcompat.C0425R.styleable.Toolbar_contentInsetRight
            r24 = 0
            int r22 = r22.getDimensionPixelSize(r23, r24)
            r15 = r22
            r22 = r2
            r22.ensureContentInsets()
            r22 = r2
            r0 = r22
            android.support.v7.widget.RtlSpacingHelper r0 = r0.mContentInsets
            r22 = r0
            r23 = r14
            r24 = r15
            r22.setAbsolute(r23, r24)
            r22 = r12
            r23 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r22
            r1 = r23
            if (r0 != r1) goto L_0x0214
            r22 = r13
            r23 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r22
            r1 = r23
            if (r0 == r1) goto L_0x0223
        L_0x0214:
            r22 = r2
            r0 = r22
            android.support.v7.widget.RtlSpacingHelper r0 = r0.mContentInsets
            r22 = r0
            r23 = r12
            r24 = r13
            r22.setRelative(r23, r24)
        L_0x0223:
            r22 = r2
            r23 = r6
            int r24 = android.support.p003v7.appcompat.C0425R.styleable.Toolbar_contentInsetStartWithNavigation
            r25 = -2147483648(0xffffffff80000000, float:-0.0)
            int r23 = r23.getDimensionPixelOffset(r24, r25)
            r0 = r23
            r1 = r22
            r1.mContentInsetStartWithNavigation = r0
            r22 = r2
            r23 = r6
            int r24 = android.support.p003v7.appcompat.C0425R.styleable.Toolbar_contentInsetEndWithActions
            r25 = -2147483648(0xffffffff80000000, float:-0.0)
            int r23 = r23.getDimensionPixelOffset(r24, r25)
            r0 = r23
            r1 = r22
            r1.mContentInsetEndWithActions = r0
            r22 = r2
            r23 = r6
            int r24 = android.support.p003v7.appcompat.C0425R.styleable.Toolbar_collapseIcon
            android.graphics.drawable.Drawable r23 = r23.getDrawable(r24)
            r0 = r23
            r1 = r22
            r1.mCollapseIcon = r0
            r22 = r2
            r23 = r6
            int r24 = android.support.p003v7.appcompat.C0425R.styleable.Toolbar_collapseContentDescription
            java.lang.CharSequence r23 = r23.getText(r24)
            r0 = r23
            r1 = r22
            r1.mCollapseDescription = r0
            r22 = r6
            int r23 = android.support.p003v7.appcompat.C0425R.styleable.Toolbar_title
            java.lang.CharSequence r22 = r22.getText(r23)
            r16 = r22
            r22 = r16
            boolean r22 = android.text.TextUtils.isEmpty(r22)
            if (r22 != 0) goto L_0x0280
            r22 = r2
            r23 = r16
            r22.setTitle((java.lang.CharSequence) r23)
        L_0x0280:
            r22 = r6
            int r23 = android.support.p003v7.appcompat.C0425R.styleable.Toolbar_subtitle
            java.lang.CharSequence r22 = r22.getText(r23)
            r17 = r22
            r22 = r17
            boolean r22 = android.text.TextUtils.isEmpty(r22)
            if (r22 != 0) goto L_0x0299
            r22 = r2
            r23 = r17
            r22.setSubtitle((java.lang.CharSequence) r23)
        L_0x0299:
            r22 = r2
            r23 = r2
            android.content.Context r23 = r23.getContext()
            r0 = r23
            r1 = r22
            r1.mPopupContext = r0
            r22 = r2
            r23 = r6
            int r24 = android.support.p003v7.appcompat.C0425R.styleable.Toolbar_popupTheme
            r25 = 0
            int r23 = r23.getResourceId(r24, r25)
            r22.setPopupTheme(r23)
            r22 = r6
            int r23 = android.support.p003v7.appcompat.C0425R.styleable.Toolbar_navigationIcon
            android.graphics.drawable.Drawable r22 = r22.getDrawable(r23)
            r18 = r22
            r22 = r18
            if (r22 == 0) goto L_0x02cb
            r22 = r2
            r23 = r18
            r22.setNavigationIcon((android.graphics.drawable.Drawable) r23)
        L_0x02cb:
            r22 = r6
            int r23 = android.support.p003v7.appcompat.C0425R.styleable.Toolbar_navigationContentDescription
            java.lang.CharSequence r22 = r22.getText(r23)
            r19 = r22
            r22 = r19
            boolean r22 = android.text.TextUtils.isEmpty(r22)
            if (r22 != 0) goto L_0x02e4
            r22 = r2
            r23 = r19
            r22.setNavigationContentDescription((java.lang.CharSequence) r23)
        L_0x02e4:
            r22 = r6
            int r23 = android.support.p003v7.appcompat.C0425R.styleable.Toolbar_logo
            android.graphics.drawable.Drawable r22 = r22.getDrawable(r23)
            r20 = r22
            r22 = r20
            if (r22 == 0) goto L_0x02f9
            r22 = r2
            r23 = r20
            r22.setLogo((android.graphics.drawable.Drawable) r23)
        L_0x02f9:
            r22 = r6
            int r23 = android.support.p003v7.appcompat.C0425R.styleable.Toolbar_logoDescription
            java.lang.CharSequence r22 = r22.getText(r23)
            r21 = r22
            r22 = r21
            boolean r22 = android.text.TextUtils.isEmpty(r22)
            if (r22 != 0) goto L_0x0312
            r22 = r2
            r23 = r21
            r22.setLogoDescription((java.lang.CharSequence) r23)
        L_0x0312:
            r22 = r6
            int r23 = android.support.p003v7.appcompat.C0425R.styleable.Toolbar_titleTextColor
            boolean r22 = r22.hasValue(r23)
            if (r22 == 0) goto L_0x032b
            r22 = r2
            r23 = r6
            int r24 = android.support.p003v7.appcompat.C0425R.styleable.Toolbar_titleTextColor
            r25 = -1
            int r23 = r23.getColor(r24, r25)
            r22.setTitleTextColor(r23)
        L_0x032b:
            r22 = r6
            int r23 = android.support.p003v7.appcompat.C0425R.styleable.Toolbar_subtitleTextColor
            boolean r22 = r22.hasValue(r23)
            if (r22 == 0) goto L_0x0344
            r22 = r2
            r23 = r6
            int r24 = android.support.p003v7.appcompat.C0425R.styleable.Toolbar_subtitleTextColor
            r25 = -1
            int r23 = r23.getColor(r24, r25)
            r22.setSubtitleTextColor(r23)
        L_0x0344:
            r22 = r6
            r22.recycle()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.widget.Toolbar.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public void setPopupTheme(@StyleRes int i) {
        Context context;
        int resId = i;
        if (this.mPopupTheme != resId) {
            this.mPopupTheme = resId;
            if (resId == 0) {
                this.mPopupContext = getContext();
                return;
            }
            new ContextThemeWrapper(getContext(), resId);
            this.mPopupContext = context;
        }
    }

    public int getPopupTheme() {
        return this.mPopupTheme;
    }

    public void setTitleMargin(int start, int top, int end, int bottom) {
        this.mTitleMarginStart = start;
        this.mTitleMarginTop = top;
        this.mTitleMarginEnd = end;
        this.mTitleMarginBottom = bottom;
        requestLayout();
    }

    public int getTitleMarginStart() {
        return this.mTitleMarginStart;
    }

    public void setTitleMarginStart(int margin) {
        this.mTitleMarginStart = margin;
        requestLayout();
    }

    public int getTitleMarginTop() {
        return this.mTitleMarginTop;
    }

    public void setTitleMarginTop(int margin) {
        this.mTitleMarginTop = margin;
        requestLayout();
    }

    public int getTitleMarginEnd() {
        return this.mTitleMarginEnd;
    }

    public void setTitleMarginEnd(int margin) {
        this.mTitleMarginEnd = margin;
        requestLayout();
    }

    public int getTitleMarginBottom() {
        return this.mTitleMarginBottom;
    }

    public void setTitleMarginBottom(int margin) {
        this.mTitleMarginBottom = margin;
        requestLayout();
    }

    public void onRtlPropertiesChanged(int i) {
        int layoutDirection = i;
        if (Build.VERSION.SDK_INT >= 17) {
            super.onRtlPropertiesChanged(layoutDirection);
        }
        ensureContentInsets();
        this.mContentInsets.setDirection(layoutDirection == 1);
    }

    public void setLogo(@DrawableRes int resId) {
        setLogo(AppCompatResources.getDrawable(getContext(), resId));
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean canShowOverflowMenu() {
        return getVisibility() == 0 && this.mMenuView != null && this.mMenuView.isOverflowReserved();
    }

    public boolean isOverflowMenuShowing() {
        return this.mMenuView != null && this.mMenuView.isOverflowMenuShowing();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean isOverflowMenuShowPending() {
        return this.mMenuView != null && this.mMenuView.isOverflowMenuShowPending();
    }

    public boolean showOverflowMenu() {
        return this.mMenuView != null && this.mMenuView.showOverflowMenu();
    }

    public boolean hideOverflowMenu() {
        return this.mMenuView != null && this.mMenuView.hideOverflowMenu();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setMenu(MenuBuilder menuBuilder, ActionMenuPresenter actionMenuPresenter) {
        ExpandedActionViewMenuPresenter expandedActionViewMenuPresenter;
        MenuBuilder menu = menuBuilder;
        ActionMenuPresenter outerPresenter = actionMenuPresenter;
        if (menu != null || this.mMenuView != null) {
            ensureMenuView();
            MenuBuilder oldMenu = this.mMenuView.peekMenu();
            if (oldMenu != menu) {
                if (oldMenu != null) {
                    oldMenu.removeMenuPresenter(this.mOuterActionMenuPresenter);
                    oldMenu.removeMenuPresenter(this.mExpandedMenuPresenter);
                }
                if (this.mExpandedMenuPresenter == null) {
                    new ExpandedActionViewMenuPresenter(this);
                    this.mExpandedMenuPresenter = expandedActionViewMenuPresenter;
                }
                outerPresenter.setExpandedActionViewsExclusive(true);
                if (menu != null) {
                    menu.addMenuPresenter(outerPresenter, this.mPopupContext);
                    menu.addMenuPresenter(this.mExpandedMenuPresenter, this.mPopupContext);
                } else {
                    outerPresenter.initForMenu(this.mPopupContext, (MenuBuilder) null);
                    this.mExpandedMenuPresenter.initForMenu(this.mPopupContext, (MenuBuilder) null);
                    outerPresenter.updateMenuView(true);
                    this.mExpandedMenuPresenter.updateMenuView(true);
                }
                this.mMenuView.setPopupTheme(this.mPopupTheme);
                this.mMenuView.setPresenter(outerPresenter);
                this.mOuterActionMenuPresenter = outerPresenter;
            }
        }
    }

    public void dismissPopupMenus() {
        if (this.mMenuView != null) {
            this.mMenuView.dismissPopupMenus();
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean isTitleTruncated() {
        if (this.mTitleTextView == null) {
            return false;
        }
        Layout titleLayout = this.mTitleTextView.getLayout();
        if (titleLayout == null) {
            return false;
        }
        int lineCount = titleLayout.getLineCount();
        for (int i = 0; i < lineCount; i++) {
            if (titleLayout.getEllipsisCount(i) > 0) {
                return true;
            }
        }
        return false;
    }

    public void setLogo(Drawable drawable) {
        Drawable drawable2 = drawable;
        if (drawable2 != null) {
            ensureLogoView();
            if (!isChildOrHidden(this.mLogoView)) {
                addSystemView(this.mLogoView, true);
            }
        } else if (this.mLogoView != null && isChildOrHidden(this.mLogoView)) {
            removeView(this.mLogoView);
            boolean remove = this.mHiddenViews.remove(this.mLogoView);
        }
        if (this.mLogoView != null) {
            this.mLogoView.setImageDrawable(drawable2);
        }
    }

    public Drawable getLogo() {
        return this.mLogoView != null ? this.mLogoView.getDrawable() : null;
    }

    public void setLogoDescription(@StringRes int resId) {
        setLogoDescription(getContext().getText(resId));
    }

    public void setLogoDescription(CharSequence charSequence) {
        CharSequence description = charSequence;
        if (!TextUtils.isEmpty(description)) {
            ensureLogoView();
        }
        if (this.mLogoView != null) {
            this.mLogoView.setContentDescription(description);
        }
    }

    public CharSequence getLogoDescription() {
        return this.mLogoView != null ? this.mLogoView.getContentDescription() : null;
    }

    private void ensureLogoView() {
        ImageView imageView;
        if (this.mLogoView == null) {
            new AppCompatImageView(getContext());
            this.mLogoView = imageView;
        }
    }

    public boolean hasExpandedActionView() {
        return (this.mExpandedMenuPresenter == null || this.mExpandedMenuPresenter.mCurrentExpandedItem == null) ? false : true;
    }

    public void collapseActionView() {
        MenuItemImpl item = this.mExpandedMenuPresenter == null ? null : this.mExpandedMenuPresenter.mCurrentExpandedItem;
        if (item != null) {
            boolean collapseActionView = item.collapseActionView();
        }
    }

    public CharSequence getTitle() {
        return this.mTitleText;
    }

    public void setTitle(@StringRes int resId) {
        setTitle(getContext().getText(resId));
    }

    public void setTitle(CharSequence charSequence) {
        TextView textView;
        CharSequence title = charSequence;
        if (!TextUtils.isEmpty(title)) {
            if (this.mTitleTextView == null) {
                Context context = getContext();
                new AppCompatTextView(context);
                this.mTitleTextView = textView;
                this.mTitleTextView.setSingleLine();
                this.mTitleTextView.setEllipsize(TextUtils.TruncateAt.END);
                if (this.mTitleTextAppearance != 0) {
                    this.mTitleTextView.setTextAppearance(context, this.mTitleTextAppearance);
                }
                if (this.mTitleTextColor != 0) {
                    this.mTitleTextView.setTextColor(this.mTitleTextColor);
                }
            }
            if (!isChildOrHidden(this.mTitleTextView)) {
                addSystemView(this.mTitleTextView, true);
            }
        } else if (this.mTitleTextView != null && isChildOrHidden(this.mTitleTextView)) {
            removeView(this.mTitleTextView);
            boolean remove = this.mHiddenViews.remove(this.mTitleTextView);
        }
        if (this.mTitleTextView != null) {
            this.mTitleTextView.setText(title);
        }
        this.mTitleText = title;
    }

    public CharSequence getSubtitle() {
        return this.mSubtitleText;
    }

    public void setSubtitle(@StringRes int resId) {
        setSubtitle(getContext().getText(resId));
    }

    public void setSubtitle(CharSequence charSequence) {
        TextView textView;
        CharSequence subtitle = charSequence;
        if (!TextUtils.isEmpty(subtitle)) {
            if (this.mSubtitleTextView == null) {
                Context context = getContext();
                new AppCompatTextView(context);
                this.mSubtitleTextView = textView;
                this.mSubtitleTextView.setSingleLine();
                this.mSubtitleTextView.setEllipsize(TextUtils.TruncateAt.END);
                if (this.mSubtitleTextAppearance != 0) {
                    this.mSubtitleTextView.setTextAppearance(context, this.mSubtitleTextAppearance);
                }
                if (this.mSubtitleTextColor != 0) {
                    this.mSubtitleTextView.setTextColor(this.mSubtitleTextColor);
                }
            }
            if (!isChildOrHidden(this.mSubtitleTextView)) {
                addSystemView(this.mSubtitleTextView, true);
            }
        } else if (this.mSubtitleTextView != null && isChildOrHidden(this.mSubtitleTextView)) {
            removeView(this.mSubtitleTextView);
            boolean remove = this.mHiddenViews.remove(this.mSubtitleTextView);
        }
        if (this.mSubtitleTextView != null) {
            this.mSubtitleTextView.setText(subtitle);
        }
        this.mSubtitleText = subtitle;
    }

    public void setTitleTextAppearance(Context context, @StyleRes int i) {
        Context context2 = context;
        int resId = i;
        this.mTitleTextAppearance = resId;
        if (this.mTitleTextView != null) {
            this.mTitleTextView.setTextAppearance(context2, resId);
        }
    }

    public void setSubtitleTextAppearance(Context context, @StyleRes int i) {
        Context context2 = context;
        int resId = i;
        this.mSubtitleTextAppearance = resId;
        if (this.mSubtitleTextView != null) {
            this.mSubtitleTextView.setTextAppearance(context2, resId);
        }
    }

    public void setTitleTextColor(@ColorInt int i) {
        int color = i;
        this.mTitleTextColor = color;
        if (this.mTitleTextView != null) {
            this.mTitleTextView.setTextColor(color);
        }
    }

    public void setSubtitleTextColor(@ColorInt int i) {
        int color = i;
        this.mSubtitleTextColor = color;
        if (this.mSubtitleTextView != null) {
            this.mSubtitleTextView.setTextColor(color);
        }
    }

    @Nullable
    public CharSequence getNavigationContentDescription() {
        return this.mNavButtonView != null ? this.mNavButtonView.getContentDescription() : null;
    }

    public void setNavigationContentDescription(@StringRes int i) {
        int resId = i;
        setNavigationContentDescription(resId != 0 ? getContext().getText(resId) : null);
    }

    public void setNavigationContentDescription(@Nullable CharSequence charSequence) {
        CharSequence description = charSequence;
        if (!TextUtils.isEmpty(description)) {
            ensureNavButtonView();
        }
        if (this.mNavButtonView != null) {
            this.mNavButtonView.setContentDescription(description);
        }
    }

    public void setNavigationIcon(@DrawableRes int resId) {
        setNavigationIcon(AppCompatResources.getDrawable(getContext(), resId));
    }

    public void setNavigationIcon(@Nullable Drawable drawable) {
        Drawable icon = drawable;
        if (icon != null) {
            ensureNavButtonView();
            if (!isChildOrHidden(this.mNavButtonView)) {
                addSystemView(this.mNavButtonView, true);
            }
        } else if (this.mNavButtonView != null && isChildOrHidden(this.mNavButtonView)) {
            removeView(this.mNavButtonView);
            boolean remove = this.mHiddenViews.remove(this.mNavButtonView);
        }
        if (this.mNavButtonView != null) {
            this.mNavButtonView.setImageDrawable(icon);
        }
    }

    @Nullable
    public Drawable getNavigationIcon() {
        return this.mNavButtonView != null ? this.mNavButtonView.getDrawable() : null;
    }

    public void setNavigationOnClickListener(View.OnClickListener listener) {
        ensureNavButtonView();
        this.mNavButtonView.setOnClickListener(listener);
    }

    public Menu getMenu() {
        ensureMenu();
        return this.mMenuView.getMenu();
    }

    public void setOverflowIcon(@Nullable Drawable icon) {
        ensureMenu();
        this.mMenuView.setOverflowIcon(icon);
    }

    @Nullable
    public Drawable getOverflowIcon() {
        ensureMenu();
        return this.mMenuView.getOverflowIcon();
    }

    private void ensureMenu() {
        ExpandedActionViewMenuPresenter expandedActionViewMenuPresenter;
        ensureMenuView();
        if (this.mMenuView.peekMenu() == null) {
            MenuBuilder menu = (MenuBuilder) this.mMenuView.getMenu();
            if (this.mExpandedMenuPresenter == null) {
                new ExpandedActionViewMenuPresenter(this);
                this.mExpandedMenuPresenter = expandedActionViewMenuPresenter;
            }
            this.mMenuView.setExpandedActionViewsExclusive(true);
            menu.addMenuPresenter(this.mExpandedMenuPresenter, this.mPopupContext);
        }
    }

    private void ensureMenuView() {
        ActionMenuView actionMenuView;
        if (this.mMenuView == null) {
            new ActionMenuView(getContext());
            this.mMenuView = actionMenuView;
            this.mMenuView.setPopupTheme(this.mPopupTheme);
            this.mMenuView.setOnMenuItemClickListener(this.mMenuViewItemClickListener);
            this.mMenuView.setMenuCallbacks(this.mActionMenuPresenterCallback, this.mMenuBuilderCallback);
            LayoutParams lp = generateDefaultLayoutParams();
            lp.gravity = 8388613 | (this.mButtonGravity & 112);
            this.mMenuView.setLayoutParams(lp);
            addSystemView(this.mMenuView, false);
        }
    }

    private MenuInflater getMenuInflater() {
        MenuInflater menuInflater;
        new SupportMenuInflater(getContext());
        return menuInflater;
    }

    public void inflateMenu(@MenuRes int resId) {
        getMenuInflater().inflate(resId, getMenu());
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener listener) {
        OnMenuItemClickListener onMenuItemClickListener = listener;
        this.mOnMenuItemClickListener = onMenuItemClickListener;
    }

    public void setContentInsetsRelative(int contentInsetStart, int contentInsetEnd) {
        ensureContentInsets();
        this.mContentInsets.setRelative(contentInsetStart, contentInsetEnd);
    }

    public int getContentInsetStart() {
        return this.mContentInsets != null ? this.mContentInsets.getStart() : 0;
    }

    public int getContentInsetEnd() {
        return this.mContentInsets != null ? this.mContentInsets.getEnd() : 0;
    }

    public void setContentInsetsAbsolute(int contentInsetLeft, int contentInsetRight) {
        ensureContentInsets();
        this.mContentInsets.setAbsolute(contentInsetLeft, contentInsetRight);
    }

    public int getContentInsetLeft() {
        return this.mContentInsets != null ? this.mContentInsets.getLeft() : 0;
    }

    public int getContentInsetRight() {
        return this.mContentInsets != null ? this.mContentInsets.getRight() : 0;
    }

    public int getContentInsetStartWithNavigation() {
        int contentInsetStart;
        if (this.mContentInsetStartWithNavigation != Integer.MIN_VALUE) {
            contentInsetStart = this.mContentInsetStartWithNavigation;
        } else {
            contentInsetStart = getContentInsetStart();
        }
        return contentInsetStart;
    }

    public void setContentInsetStartWithNavigation(int i) {
        int insetStartWithNavigation = i;
        if (insetStartWithNavigation < 0) {
            insetStartWithNavigation = Integer.MIN_VALUE;
        }
        if (insetStartWithNavigation != this.mContentInsetStartWithNavigation) {
            this.mContentInsetStartWithNavigation = insetStartWithNavigation;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public int getContentInsetEndWithActions() {
        int contentInsetEnd;
        if (this.mContentInsetEndWithActions != Integer.MIN_VALUE) {
            contentInsetEnd = this.mContentInsetEndWithActions;
        } else {
            contentInsetEnd = getContentInsetEnd();
        }
        return contentInsetEnd;
    }

    public void setContentInsetEndWithActions(int i) {
        int insetEndWithActions = i;
        if (insetEndWithActions < 0) {
            insetEndWithActions = Integer.MIN_VALUE;
        }
        if (insetEndWithActions != this.mContentInsetEndWithActions) {
            this.mContentInsetEndWithActions = insetEndWithActions;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public int getCurrentContentInsetStart() {
        int contentInsetStart;
        if (getNavigationIcon() != null) {
            contentInsetStart = Math.max(getContentInsetStart(), Math.max(this.mContentInsetStartWithNavigation, 0));
        } else {
            contentInsetStart = getContentInsetStart();
        }
        return contentInsetStart;
    }

    public int getCurrentContentInsetEnd() {
        int contentInsetEnd;
        boolean hasActions = false;
        if (this.mMenuView != null) {
            MenuBuilder mb = this.mMenuView.peekMenu();
            hasActions = mb != null && mb.hasVisibleItems();
        }
        if (hasActions) {
            contentInsetEnd = Math.max(getContentInsetEnd(), Math.max(this.mContentInsetEndWithActions, 0));
        } else {
            contentInsetEnd = getContentInsetEnd();
        }
        return contentInsetEnd;
    }

    public int getCurrentContentInsetLeft() {
        int currentContentInsetStart;
        if (ViewCompat.getLayoutDirection(this) == 1) {
            currentContentInsetStart = getCurrentContentInsetEnd();
        } else {
            currentContentInsetStart = getCurrentContentInsetStart();
        }
        return currentContentInsetStart;
    }

    public int getCurrentContentInsetRight() {
        int currentContentInsetEnd;
        if (ViewCompat.getLayoutDirection(this) == 1) {
            currentContentInsetEnd = getCurrentContentInsetStart();
        } else {
            currentContentInsetEnd = getCurrentContentInsetEnd();
        }
        return currentContentInsetEnd;
    }

    private void ensureNavButtonView() {
        ImageButton imageButton;
        if (this.mNavButtonView == null) {
            new AppCompatImageButton(getContext(), (AttributeSet) null, C0425R.attr.toolbarNavigationButtonStyle);
            this.mNavButtonView = imageButton;
            LayoutParams lp = generateDefaultLayoutParams();
            lp.gravity = 8388611 | (this.mButtonGravity & 112);
            this.mNavButtonView.setLayoutParams(lp);
        }
    }

    /* access modifiers changed from: package-private */
    public void ensureCollapseButtonView() {
        ImageButton imageButton;
        View.OnClickListener onClickListener;
        if (this.mCollapseButtonView == null) {
            new AppCompatImageButton(getContext(), (AttributeSet) null, C0425R.attr.toolbarNavigationButtonStyle);
            this.mCollapseButtonView = imageButton;
            this.mCollapseButtonView.setImageDrawable(this.mCollapseIcon);
            this.mCollapseButtonView.setContentDescription(this.mCollapseDescription);
            LayoutParams lp = generateDefaultLayoutParams();
            lp.gravity = 8388611 | (this.mButtonGravity & 112);
            lp.mViewType = 2;
            this.mCollapseButtonView.setLayoutParams(lp);
            new View.OnClickListener(this) {
                final /* synthetic */ Toolbar this$0;

                {
                    this.this$0 = this$0;
                }

                public void onClick(View view) {
                    View view2 = view;
                    this.this$0.collapseActionView();
                }
            };
            this.mCollapseButtonView.setOnClickListener(onClickListener);
        }
    }

    private void addSystemView(View view, boolean z) {
        LayoutParams lp;
        View v = view;
        boolean allowHide = z;
        ViewGroup.LayoutParams vlp = v.getLayoutParams();
        if (vlp == null) {
            lp = generateDefaultLayoutParams();
        } else if (!checkLayoutParams(vlp)) {
            lp = generateLayoutParams(vlp);
        } else {
            lp = (LayoutParams) vlp;
        }
        lp.mViewType = 1;
        if (!allowHide || this.mExpandedActionView == null) {
            addView(v, lp);
            return;
        }
        v.setLayoutParams(lp);
        boolean add = this.mHiddenViews.add(v);
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        SavedState savedState;
        new SavedState(super.onSaveInstanceState());
        SavedState state = savedState;
        if (!(this.mExpandedMenuPresenter == null || this.mExpandedMenuPresenter.mCurrentExpandedItem == null)) {
            state.expandedMenuItemId = this.mExpandedMenuPresenter.mCurrentExpandedItem.getItemId();
        }
        state.isOverflowOpen = isOverflowMenuShowing();
        return state;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        MenuItem item;
        Parcelable state = parcelable;
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        Menu menu = this.mMenuView != null ? this.mMenuView.peekMenu() : null;
        if (!(ss.expandedMenuItemId == 0 || this.mExpandedMenuPresenter == null || menu == null || (item = menu.findItem(ss.expandedMenuItemId)) == null)) {
            boolean expandActionView = item.expandActionView();
        }
        if (ss.isOverflowOpen) {
            postShowOverflowMenu();
        }
    }

    private void postShowOverflowMenu() {
        boolean removeCallbacks = removeCallbacks(this.mShowOverflowMenuRunnable);
        boolean post = post(this.mShowOverflowMenuRunnable);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        boolean removeCallbacks = removeCallbacks(this.mShowOverflowMenuRunnable);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        MotionEvent ev = motionEvent;
        int action = ev.getActionMasked();
        if (action == 0) {
            this.mEatingTouch = false;
        }
        if (!this.mEatingTouch) {
            boolean handled = super.onTouchEvent(ev);
            if (action == 0 && !handled) {
                this.mEatingTouch = true;
            }
        }
        if (action == 1 || action == 3) {
            this.mEatingTouch = false;
        }
        return true;
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        MotionEvent ev = motionEvent;
        int action = ev.getActionMasked();
        if (action == 9) {
            this.mEatingHover = false;
        }
        if (!this.mEatingHover) {
            boolean handled = super.onHoverEvent(ev);
            if (action == 9 && !handled) {
                this.mEatingHover = true;
            }
        }
        if (action == 10 || action == 3) {
            this.mEatingHover = false;
        }
        return true;
    }

    private void measureChildConstrained(View view, int parentWidthSpec, int widthUsed, int parentHeightSpec, int heightUsed, int i) {
        View child = view;
        int heightConstraint = i;
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) child.getLayoutParams();
        int childWidthSpec = getChildMeasureSpec(parentWidthSpec, getPaddingLeft() + getPaddingRight() + lp.leftMargin + lp.rightMargin + widthUsed, lp.width);
        int childHeightSpec = getChildMeasureSpec(parentHeightSpec, getPaddingTop() + getPaddingBottom() + lp.topMargin + lp.bottomMargin + heightUsed, lp.height);
        int childHeightMode = View.MeasureSpec.getMode(childHeightSpec);
        if (childHeightMode != 1073741824 && heightConstraint >= 0) {
            childHeightSpec = View.MeasureSpec.makeMeasureSpec(childHeightMode != 0 ? Math.min(View.MeasureSpec.getSize(childHeightSpec), heightConstraint) : heightConstraint, Declaration.MODULE_REFERENCE);
        }
        child.measure(childWidthSpec, childHeightSpec);
    }

    private int measureChildCollapseMargins(View view, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed, int[] iArr) {
        View child = view;
        int[] collapsingMargins = iArr;
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) child.getLayoutParams();
        int leftDiff = lp.leftMargin - collapsingMargins[0];
        int rightDiff = lp.rightMargin - collapsingMargins[1];
        int hMargins = Math.max(0, leftDiff) + Math.max(0, rightDiff);
        collapsingMargins[0] = Math.max(0, -leftDiff);
        collapsingMargins[1] = Math.max(0, -rightDiff);
        child.measure(getChildMeasureSpec(parentWidthMeasureSpec, getPaddingLeft() + getPaddingRight() + hMargins + widthUsed, lp.width), getChildMeasureSpec(parentHeightMeasureSpec, getPaddingTop() + getPaddingBottom() + lp.topMargin + lp.bottomMargin + heightUsed, lp.height));
        return child.getMeasuredWidth() + hMargins;
    }

    private boolean shouldCollapse() {
        if (!this.mCollapsible) {
            return false;
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (shouldLayout(child) && child.getMeasuredWidth() > 0 && child.getMeasuredHeight() > 0) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int marginStartIndex;
        int marginEndIndex;
        int widthMeasureSpec = i;
        int heightMeasureSpec = i2;
        int height = 0;
        int childState = 0;
        int[] collapsingMargins = this.mTempMargins;
        if (ViewUtils.isLayoutRtl(this)) {
            marginStartIndex = true;
            marginEndIndex = false;
        } else {
            marginStartIndex = 0;
            marginEndIndex = 1;
        }
        int navWidth = 0;
        if (shouldLayout(this.mNavButtonView)) {
            measureChildConstrained(this.mNavButtonView, widthMeasureSpec, 0, heightMeasureSpec, 0, this.mMaxButtonHeight);
            navWidth = this.mNavButtonView.getMeasuredWidth() + getHorizontalMargins(this.mNavButtonView);
            height = Math.max(0, this.mNavButtonView.getMeasuredHeight() + getVerticalMargins(this.mNavButtonView));
            childState = View.combineMeasuredStates(0, this.mNavButtonView.getMeasuredState());
        }
        if (shouldLayout(this.mCollapseButtonView)) {
            measureChildConstrained(this.mCollapseButtonView, widthMeasureSpec, 0, heightMeasureSpec, 0, this.mMaxButtonHeight);
            navWidth = this.mCollapseButtonView.getMeasuredWidth() + getHorizontalMargins(this.mCollapseButtonView);
            height = Math.max(height, this.mCollapseButtonView.getMeasuredHeight() + getVerticalMargins(this.mCollapseButtonView));
            childState = View.combineMeasuredStates(childState, this.mCollapseButtonView.getMeasuredState());
        }
        int contentInsetStart = getCurrentContentInsetStart();
        int width = 0 + Math.max(contentInsetStart, navWidth);
        collapsingMargins[marginStartIndex] = Math.max(0, contentInsetStart - navWidth);
        int menuWidth = 0;
        if (shouldLayout(this.mMenuView)) {
            measureChildConstrained(this.mMenuView, widthMeasureSpec, width, heightMeasureSpec, 0, this.mMaxButtonHeight);
            menuWidth = this.mMenuView.getMeasuredWidth() + getHorizontalMargins(this.mMenuView);
            height = Math.max(height, this.mMenuView.getMeasuredHeight() + getVerticalMargins(this.mMenuView));
            childState = View.combineMeasuredStates(childState, this.mMenuView.getMeasuredState());
        }
        int contentInsetEnd = getCurrentContentInsetEnd();
        int width2 = width + Math.max(contentInsetEnd, menuWidth);
        collapsingMargins[marginEndIndex] = Math.max(0, contentInsetEnd - menuWidth);
        if (shouldLayout(this.mExpandedActionView)) {
            width2 += measureChildCollapseMargins(this.mExpandedActionView, widthMeasureSpec, width2, heightMeasureSpec, 0, collapsingMargins);
            height = Math.max(height, this.mExpandedActionView.getMeasuredHeight() + getVerticalMargins(this.mExpandedActionView));
            childState = View.combineMeasuredStates(childState, this.mExpandedActionView.getMeasuredState());
        }
        if (shouldLayout(this.mLogoView)) {
            width2 += measureChildCollapseMargins(this.mLogoView, widthMeasureSpec, width2, heightMeasureSpec, 0, collapsingMargins);
            height = Math.max(height, this.mLogoView.getMeasuredHeight() + getVerticalMargins(this.mLogoView));
            childState = View.combineMeasuredStates(childState, this.mLogoView.getMeasuredState());
        }
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View child = getChildAt(i3);
            if (((LayoutParams) child.getLayoutParams()).mViewType == 0 && shouldLayout(child)) {
                width2 += measureChildCollapseMargins(child, widthMeasureSpec, width2, heightMeasureSpec, 0, collapsingMargins);
                height = Math.max(height, child.getMeasuredHeight() + getVerticalMargins(child));
                childState = View.combineMeasuredStates(childState, child.getMeasuredState());
            }
        }
        int titleWidth = 0;
        int titleHeight = 0;
        int titleVertMargins = this.mTitleMarginTop + this.mTitleMarginBottom;
        int titleHorizMargins = this.mTitleMarginStart + this.mTitleMarginEnd;
        if (shouldLayout(this.mTitleTextView)) {
            int titleWidth2 = measureChildCollapseMargins(this.mTitleTextView, widthMeasureSpec, width2 + titleHorizMargins, heightMeasureSpec, titleVertMargins, collapsingMargins);
            titleWidth = this.mTitleTextView.getMeasuredWidth() + getHorizontalMargins(this.mTitleTextView);
            titleHeight = this.mTitleTextView.getMeasuredHeight() + getVerticalMargins(this.mTitleTextView);
            childState = View.combineMeasuredStates(childState, this.mTitleTextView.getMeasuredState());
        }
        if (shouldLayout(this.mSubtitleTextView)) {
            titleWidth = Math.max(titleWidth, measureChildCollapseMargins(this.mSubtitleTextView, widthMeasureSpec, width2 + titleHorizMargins, heightMeasureSpec, titleHeight + titleVertMargins, collapsingMargins));
            titleHeight += this.mSubtitleTextView.getMeasuredHeight() + getVerticalMargins(this.mSubtitleTextView);
            childState = View.combineMeasuredStates(childState, this.mSubtitleTextView.getMeasuredState());
        }
        int height2 = Math.max(height, titleHeight);
        setMeasuredDimension(View.resolveSizeAndState(Math.max(width2 + titleWidth + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), widthMeasureSpec, childState & -16777216), shouldCollapse() ? 0 : View.resolveSizeAndState(Math.max(height2 + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), heightMeasureSpec, childState << 16));
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x02a4, code lost:
        if (r2.mTitleTextView.getMeasuredWidth() <= 0) goto L_0x02a6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x02b6, code lost:
        if (r2.mSubtitleTextView.getMeasuredWidth() > 0) goto L_0x02b8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x02b8, code lost:
        r37 = true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onLayout(boolean r47, int r48, int r49, int r50, int r51) {
        /*
            r46 = this;
            r2 = r46
            r3 = r47
            r4 = r48
            r5 = r49
            r6 = r50
            r7 = r51
            r37 = r2
            int r37 = android.support.p000v4.view.ViewCompat.getLayoutDirection(r37)
            r38 = 1
            r0 = r37
            r1 = r38
            if (r0 != r1) goto L_0x0492
            r37 = 1
        L_0x001c:
            r8 = r37
            r37 = r2
            int r37 = r37.getWidth()
            r9 = r37
            r37 = r2
            int r37 = r37.getHeight()
            r10 = r37
            r37 = r2
            int r37 = r37.getPaddingLeft()
            r11 = r37
            r37 = r2
            int r37 = r37.getPaddingRight()
            r12 = r37
            r37 = r2
            int r37 = r37.getPaddingTop()
            r13 = r37
            r37 = r2
            int r37 = r37.getPaddingBottom()
            r14 = r37
            r37 = r11
            r15 = r37
            r37 = r9
            r38 = r12
            int r37 = r37 - r38
            r16 = r37
            r37 = r2
            r0 = r37
            int[] r0 = r0.mTempMargins
            r37 = r0
            r17 = r37
            r37 = r17
            r38 = 0
            r39 = r17
            r40 = 1
            r41 = 0
            r43 = r39
            r44 = r40
            r45 = r41
            r39 = r45
            r40 = r43
            r41 = r44
            r42 = r45
            r40[r41] = r42
            r37[r38] = r39
            r37 = r2
            int r37 = android.support.p000v4.view.ViewCompat.getMinimumHeight(r37)
            r18 = r37
            r37 = r18
            if (r37 < 0) goto L_0x0496
            r37 = r18
            r38 = r7
            r39 = r5
            int r38 = r38 - r39
            int r37 = java.lang.Math.min(r37, r38)
        L_0x0098:
            r19 = r37
            r37 = r2
            r38 = r2
            r0 = r38
            android.widget.ImageButton r0 = r0.mNavButtonView
            r38 = r0
            boolean r37 = r37.shouldLayout(r38)
            if (r37 == 0) goto L_0x00c4
            r37 = r8
            if (r37 == 0) goto L_0x049a
            r37 = r2
            r38 = r2
            r0 = r38
            android.widget.ImageButton r0 = r0.mNavButtonView
            r38 = r0
            r39 = r16
            r40 = r17
            r41 = r19
            int r37 = r37.layoutChildRight(r38, r39, r40, r41)
            r16 = r37
        L_0x00c4:
            r37 = r2
            r38 = r2
            r0 = r38
            android.widget.ImageButton r0 = r0.mCollapseButtonView
            r38 = r0
            boolean r37 = r37.shouldLayout(r38)
            if (r37 == 0) goto L_0x00ee
            r37 = r8
            if (r37 == 0) goto L_0x04b2
            r37 = r2
            r38 = r2
            r0 = r38
            android.widget.ImageButton r0 = r0.mCollapseButtonView
            r38 = r0
            r39 = r16
            r40 = r17
            r41 = r19
            int r37 = r37.layoutChildRight(r38, r39, r40, r41)
            r16 = r37
        L_0x00ee:
            r37 = r2
            r38 = r2
            r0 = r38
            android.support.v7.widget.ActionMenuView r0 = r0.mMenuView
            r38 = r0
            boolean r37 = r37.shouldLayout(r38)
            if (r37 == 0) goto L_0x0118
            r37 = r8
            if (r37 == 0) goto L_0x04ca
            r37 = r2
            r38 = r2
            r0 = r38
            android.support.v7.widget.ActionMenuView r0 = r0.mMenuView
            r38 = r0
            r39 = r15
            r40 = r17
            r41 = r19
            int r37 = r37.layoutChildLeft(r38, r39, r40, r41)
            r15 = r37
        L_0x0118:
            r37 = r2
            int r37 = r37.getCurrentContentInsetLeft()
            r20 = r37
            r37 = r2
            int r37 = r37.getCurrentContentInsetRight()
            r21 = r37
            r37 = r17
            r38 = 0
            r39 = 0
            r40 = r20
            r41 = r15
            int r40 = r40 - r41
            int r39 = java.lang.Math.max(r39, r40)
            r37[r38] = r39
            r37 = r17
            r38 = 1
            r39 = 0
            r40 = r21
            r41 = r9
            r42 = r12
            int r41 = r41 - r42
            r42 = r16
            int r41 = r41 - r42
            int r40 = r40 - r41
            int r39 = java.lang.Math.max(r39, r40)
            r37[r38] = r39
            r37 = r15
            r38 = r20
            int r37 = java.lang.Math.max(r37, r38)
            r15 = r37
            r37 = r16
            r38 = r9
            r39 = r12
            int r38 = r38 - r39
            r39 = r21
            int r38 = r38 - r39
            int r37 = java.lang.Math.min(r37, r38)
            r16 = r37
            r37 = r2
            r38 = r2
            r0 = r38
            android.view.View r0 = r0.mExpandedActionView
            r38 = r0
            boolean r37 = r37.shouldLayout(r38)
            if (r37 == 0) goto L_0x019a
            r37 = r8
            if (r37 == 0) goto L_0x04e2
            r37 = r2
            r38 = r2
            r0 = r38
            android.view.View r0 = r0.mExpandedActionView
            r38 = r0
            r39 = r16
            r40 = r17
            r41 = r19
            int r37 = r37.layoutChildRight(r38, r39, r40, r41)
            r16 = r37
        L_0x019a:
            r37 = r2
            r38 = r2
            r0 = r38
            android.widget.ImageView r0 = r0.mLogoView
            r38 = r0
            boolean r37 = r37.shouldLayout(r38)
            if (r37 == 0) goto L_0x01c4
            r37 = r8
            if (r37 == 0) goto L_0x04fa
            r37 = r2
            r38 = r2
            r0 = r38
            android.widget.ImageView r0 = r0.mLogoView
            r38 = r0
            r39 = r16
            r40 = r17
            r41 = r19
            int r37 = r37.layoutChildRight(r38, r39, r40, r41)
            r16 = r37
        L_0x01c4:
            r37 = r2
            r38 = r2
            r0 = r38
            android.widget.TextView r0 = r0.mTitleTextView
            r38 = r0
            boolean r37 = r37.shouldLayout(r38)
            r22 = r37
            r37 = r2
            r38 = r2
            r0 = r38
            android.widget.TextView r0 = r0.mSubtitleTextView
            r38 = r0
            boolean r37 = r37.shouldLayout(r38)
            r23 = r37
            r37 = 0
            r24 = r37
            r37 = r22
            if (r37 == 0) goto L_0x0222
            r37 = r2
            r0 = r37
            android.widget.TextView r0 = r0.mTitleTextView
            r37 = r0
            android.view.ViewGroup$LayoutParams r37 = r37.getLayoutParams()
            android.support.v7.widget.Toolbar$LayoutParams r37 = (android.support.p003v7.widget.Toolbar.LayoutParams) r37
            r25 = r37
            r37 = r24
            r38 = r25
            r0 = r38
            int r0 = r0.topMargin
            r38 = r0
            r39 = r2
            r0 = r39
            android.widget.TextView r0 = r0.mTitleTextView
            r39 = r0
            int r39 = r39.getMeasuredHeight()
            int r38 = r38 + r39
            r39 = r25
            r0 = r39
            int r0 = r0.bottomMargin
            r39 = r0
            int r38 = r38 + r39
            int r37 = r37 + r38
            r24 = r37
        L_0x0222:
            r37 = r23
            if (r37 == 0) goto L_0x025c
            r37 = r2
            r0 = r37
            android.widget.TextView r0 = r0.mSubtitleTextView
            r37 = r0
            android.view.ViewGroup$LayoutParams r37 = r37.getLayoutParams()
            android.support.v7.widget.Toolbar$LayoutParams r37 = (android.support.p003v7.widget.Toolbar.LayoutParams) r37
            r25 = r37
            r37 = r24
            r38 = r25
            r0 = r38
            int r0 = r0.topMargin
            r38 = r0
            r39 = r2
            r0 = r39
            android.widget.TextView r0 = r0.mSubtitleTextView
            r39 = r0
            int r39 = r39.getMeasuredHeight()
            int r38 = r38 + r39
            r39 = r25
            r0 = r39
            int r0 = r0.bottomMargin
            r39 = r0
            int r38 = r38 + r39
            int r37 = r37 + r38
            r24 = r37
        L_0x025c:
            r37 = r22
            if (r37 != 0) goto L_0x0264
            r37 = r23
            if (r37 == 0) goto L_0x0446
        L_0x0264:
            r37 = r22
            if (r37 == 0) goto L_0x0512
            r37 = r2
            r0 = r37
            android.widget.TextView r0 = r0.mTitleTextView
            r37 = r0
        L_0x0270:
            r26 = r37
            r37 = r23
            if (r37 == 0) goto L_0x051c
            r37 = r2
            r0 = r37
            android.widget.TextView r0 = r0.mSubtitleTextView
            r37 = r0
        L_0x027e:
            r27 = r37
            r37 = r26
            android.view.ViewGroup$LayoutParams r37 = r37.getLayoutParams()
            android.support.v7.widget.Toolbar$LayoutParams r37 = (android.support.p003v7.widget.Toolbar.LayoutParams) r37
            r28 = r37
            r37 = r27
            android.view.ViewGroup$LayoutParams r37 = r37.getLayoutParams()
            android.support.v7.widget.Toolbar$LayoutParams r37 = (android.support.p003v7.widget.Toolbar.LayoutParams) r37
            r29 = r37
            r37 = r22
            if (r37 == 0) goto L_0x02a6
            r37 = r2
            r0 = r37
            android.widget.TextView r0 = r0.mTitleTextView
            r37 = r0
            int r37 = r37.getMeasuredWidth()
            if (r37 > 0) goto L_0x02b8
        L_0x02a6:
            r37 = r23
            if (r37 == 0) goto L_0x0526
            r37 = r2
            r0 = r37
            android.widget.TextView r0 = r0.mSubtitleTextView
            r37 = r0
            int r37 = r37.getMeasuredWidth()
            if (r37 <= 0) goto L_0x0526
        L_0x02b8:
            r37 = 1
        L_0x02ba:
            r30 = r37
            r37 = r2
            r0 = r37
            int r0 = r0.mGravity
            r37 = r0
            r38 = 112(0x70, float:1.57E-43)
            r37 = r37 & 112(0x70, float:1.57E-43)
            switch(r37) {
                case 48: goto L_0x052a;
                case 80: goto L_0x059a;
                default: goto L_0x02cb;
            }
        L_0x02cb:
            r37 = r10
            r38 = r13
            int r37 = r37 - r38
            r38 = r14
            int r37 = r37 - r38
            r31 = r37
            r37 = r31
            r38 = r24
            int r37 = r37 - r38
            r38 = 2
            int r37 = r37 / 2
            r32 = r37
            r37 = r32
            r38 = r28
            r0 = r38
            int r0 = r0.topMargin
            r38 = r0
            r39 = r2
            r0 = r39
            int r0 = r0.mTitleMarginTop
            r39 = r0
            int r38 = r38 + r39
            r0 = r37
            r1 = r38
            if (r0 >= r1) goto L_0x0548
            r37 = r28
            r0 = r37
            int r0 = r0.topMargin
            r37 = r0
            r38 = r2
            r0 = r38
            int r0 = r0.mTitleMarginTop
            r38 = r0
            int r37 = r37 + r38
            r32 = r37
        L_0x0311:
            r37 = r13
            r38 = r32
            int r37 = r37 + r38
            r25 = r37
        L_0x0319:
            r37 = r8
            if (r37 == 0) goto L_0x05c0
            r37 = r30
            if (r37 == 0) goto L_0x05bc
            r37 = r2
            r0 = r37
            int r0 = r0.mTitleMarginStart
            r37 = r0
        L_0x0329:
            r38 = r17
            r39 = 1
            r38 = r38[r39]
            int r37 = r37 - r38
            r31 = r37
            r37 = r16
            r38 = 0
            r39 = r31
            int r38 = java.lang.Math.max(r38, r39)
            int r37 = r37 - r38
            r16 = r37
            r37 = r17
            r38 = 1
            r39 = 0
            r40 = r31
            r0 = r40
            int r0 = -r0
            r40 = r0
            int r39 = java.lang.Math.max(r39, r40)
            r37[r38] = r39
            r37 = r16
            r32 = r37
            r37 = r16
            r33 = r37
            r37 = r22
            if (r37 == 0) goto L_0x03c3
            r37 = r2
            r0 = r37
            android.widget.TextView r0 = r0.mTitleTextView
            r37 = r0
            android.view.ViewGroup$LayoutParams r37 = r37.getLayoutParams()
            android.support.v7.widget.Toolbar$LayoutParams r37 = (android.support.p003v7.widget.Toolbar.LayoutParams) r37
            r34 = r37
            r37 = r32
            r38 = r2
            r0 = r38
            android.widget.TextView r0 = r0.mTitleTextView
            r38 = r0
            int r38 = r38.getMeasuredWidth()
            int r37 = r37 - r38
            r35 = r37
            r37 = r25
            r38 = r2
            r0 = r38
            android.widget.TextView r0 = r0.mTitleTextView
            r38 = r0
            int r38 = r38.getMeasuredHeight()
            int r37 = r37 + r38
            r36 = r37
            r37 = r2
            r0 = r37
            android.widget.TextView r0 = r0.mTitleTextView
            r37 = r0
            r38 = r35
            r39 = r25
            r40 = r32
            r41 = r36
            r37.layout(r38, r39, r40, r41)
            r37 = r35
            r38 = r2
            r0 = r38
            int r0 = r0.mTitleMarginEnd
            r38 = r0
            int r37 = r37 - r38
            r32 = r37
            r37 = r36
            r38 = r34
            r0 = r38
            int r0 = r0.bottomMargin
            r38 = r0
            int r37 = r37 + r38
            r25 = r37
        L_0x03c3:
            r37 = r23
            if (r37 == 0) goto L_0x0438
            r37 = r2
            r0 = r37
            android.widget.TextView r0 = r0.mSubtitleTextView
            r37 = r0
            android.view.ViewGroup$LayoutParams r37 = r37.getLayoutParams()
            android.support.v7.widget.Toolbar$LayoutParams r37 = (android.support.p003v7.widget.Toolbar.LayoutParams) r37
            r34 = r37
            r37 = r25
            r38 = r34
            r0 = r38
            int r0 = r0.topMargin
            r38 = r0
            int r37 = r37 + r38
            r25 = r37
            r37 = r33
            r38 = r2
            r0 = r38
            android.widget.TextView r0 = r0.mSubtitleTextView
            r38 = r0
            int r38 = r38.getMeasuredWidth()
            int r37 = r37 - r38
            r35 = r37
            r37 = r25
            r38 = r2
            r0 = r38
            android.widget.TextView r0 = r0.mSubtitleTextView
            r38 = r0
            int r38 = r38.getMeasuredHeight()
            int r37 = r37 + r38
            r36 = r37
            r37 = r2
            r0 = r37
            android.widget.TextView r0 = r0.mSubtitleTextView
            r37 = r0
            r38 = r35
            r39 = r25
            r40 = r33
            r41 = r36
            r37.layout(r38, r39, r40, r41)
            r37 = r33
            r38 = r2
            r0 = r38
            int r0 = r0.mTitleMarginEnd
            r38 = r0
            int r37 = r37 - r38
            r33 = r37
            r37 = r36
            r38 = r34
            r0 = r38
            int r0 = r0.bottomMargin
            r38 = r0
            int r37 = r37 + r38
            r25 = r37
        L_0x0438:
            r37 = r30
            if (r37 == 0) goto L_0x0446
            r37 = r32
            r38 = r33
            int r37 = java.lang.Math.min(r37, r38)
            r16 = r37
        L_0x0446:
            r37 = r2
            r38 = r2
            r0 = r38
            java.util.ArrayList<android.view.View> r0 = r0.mTempViews
            r38 = r0
            r39 = 3
            r37.addCustomViewsWithGravity(r38, r39)
            r37 = r2
            r0 = r37
            java.util.ArrayList<android.view.View> r0 = r0.mTempViews
            r37 = r0
            int r37 = r37.size()
            r25 = r37
            r37 = 0
            r26 = r37
        L_0x0467:
            r37 = r26
            r38 = r25
            r0 = r37
            r1 = r38
            if (r0 >= r1) goto L_0x06ef
            r37 = r2
            r38 = r2
            r0 = r38
            java.util.ArrayList<android.view.View> r0 = r0.mTempViews
            r38 = r0
            r39 = r26
            java.lang.Object r38 = r38.get(r39)
            android.view.View r38 = (android.view.View) r38
            r39 = r15
            r40 = r17
            r41 = r19
            int r37 = r37.layoutChildLeft(r38, r39, r40, r41)
            r15 = r37
            int r26 = r26 + 1
            goto L_0x0467
        L_0x0492:
            r37 = 0
            goto L_0x001c
        L_0x0496:
            r37 = 0
            goto L_0x0098
        L_0x049a:
            r37 = r2
            r38 = r2
            r0 = r38
            android.widget.ImageButton r0 = r0.mNavButtonView
            r38 = r0
            r39 = r15
            r40 = r17
            r41 = r19
            int r37 = r37.layoutChildLeft(r38, r39, r40, r41)
            r15 = r37
            goto L_0x00c4
        L_0x04b2:
            r37 = r2
            r38 = r2
            r0 = r38
            android.widget.ImageButton r0 = r0.mCollapseButtonView
            r38 = r0
            r39 = r15
            r40 = r17
            r41 = r19
            int r37 = r37.layoutChildLeft(r38, r39, r40, r41)
            r15 = r37
            goto L_0x00ee
        L_0x04ca:
            r37 = r2
            r38 = r2
            r0 = r38
            android.support.v7.widget.ActionMenuView r0 = r0.mMenuView
            r38 = r0
            r39 = r16
            r40 = r17
            r41 = r19
            int r37 = r37.layoutChildRight(r38, r39, r40, r41)
            r16 = r37
            goto L_0x0118
        L_0x04e2:
            r37 = r2
            r38 = r2
            r0 = r38
            android.view.View r0 = r0.mExpandedActionView
            r38 = r0
            r39 = r15
            r40 = r17
            r41 = r19
            int r37 = r37.layoutChildLeft(r38, r39, r40, r41)
            r15 = r37
            goto L_0x019a
        L_0x04fa:
            r37 = r2
            r38 = r2
            r0 = r38
            android.widget.ImageView r0 = r0.mLogoView
            r38 = r0
            r39 = r15
            r40 = r17
            r41 = r19
            int r37 = r37.layoutChildLeft(r38, r39, r40, r41)
            r15 = r37
            goto L_0x01c4
        L_0x0512:
            r37 = r2
            r0 = r37
            android.widget.TextView r0 = r0.mSubtitleTextView
            r37 = r0
            goto L_0x0270
        L_0x051c:
            r37 = r2
            r0 = r37
            android.widget.TextView r0 = r0.mTitleTextView
            r37 = r0
            goto L_0x027e
        L_0x0526:
            r37 = 0
            goto L_0x02ba
        L_0x052a:
            r37 = r2
            int r37 = r37.getPaddingTop()
            r38 = r28
            r0 = r38
            int r0 = r0.topMargin
            r38 = r0
            int r37 = r37 + r38
            r38 = r2
            r0 = r38
            int r0 = r0.mTitleMarginTop
            r38 = r0
            int r37 = r37 + r38
            r25 = r37
            goto L_0x0319
        L_0x0548:
            r37 = r10
            r38 = r14
            int r37 = r37 - r38
            r38 = r24
            int r37 = r37 - r38
            r38 = r32
            int r37 = r37 - r38
            r38 = r13
            int r37 = r37 - r38
            r33 = r37
            r37 = r33
            r38 = r28
            r0 = r38
            int r0 = r0.bottomMargin
            r38 = r0
            r39 = r2
            r0 = r39
            int r0 = r0.mTitleMarginBottom
            r39 = r0
            int r38 = r38 + r39
            r0 = r37
            r1 = r38
            if (r0 >= r1) goto L_0x0311
            r37 = 0
            r38 = r32
            r39 = r29
            r0 = r39
            int r0 = r0.bottomMargin
            r39 = r0
            r40 = r2
            r0 = r40
            int r0 = r0.mTitleMarginBottom
            r40 = r0
            int r39 = r39 + r40
            r40 = r33
            int r39 = r39 - r40
            int r38 = r38 - r39
            int r37 = java.lang.Math.max(r37, r38)
            r32 = r37
            goto L_0x0311
        L_0x059a:
            r37 = r10
            r38 = r14
            int r37 = r37 - r38
            r38 = r29
            r0 = r38
            int r0 = r0.bottomMargin
            r38 = r0
            int r37 = r37 - r38
            r38 = r2
            r0 = r38
            int r0 = r0.mTitleMarginBottom
            r38 = r0
            int r37 = r37 - r38
            r38 = r24
            int r37 = r37 - r38
            r25 = r37
            goto L_0x0319
        L_0x05bc:
            r37 = 0
            goto L_0x0329
        L_0x05c0:
            r37 = r30
            if (r37 == 0) goto L_0x06eb
            r37 = r2
            r0 = r37
            int r0 = r0.mTitleMarginStart
            r37 = r0
        L_0x05cc:
            r38 = r17
            r39 = 0
            r38 = r38[r39]
            int r37 = r37 - r38
            r31 = r37
            r37 = r15
            r38 = 0
            r39 = r31
            int r38 = java.lang.Math.max(r38, r39)
            int r37 = r37 + r38
            r15 = r37
            r37 = r17
            r38 = 0
            r39 = 0
            r40 = r31
            r0 = r40
            int r0 = -r0
            r40 = r0
            int r39 = java.lang.Math.max(r39, r40)
            r37[r38] = r39
            r37 = r15
            r32 = r37
            r37 = r15
            r33 = r37
            r37 = r22
            if (r37 == 0) goto L_0x0666
            r37 = r2
            r0 = r37
            android.widget.TextView r0 = r0.mTitleTextView
            r37 = r0
            android.view.ViewGroup$LayoutParams r37 = r37.getLayoutParams()
            android.support.v7.widget.Toolbar$LayoutParams r37 = (android.support.p003v7.widget.Toolbar.LayoutParams) r37
            r34 = r37
            r37 = r32
            r38 = r2
            r0 = r38
            android.widget.TextView r0 = r0.mTitleTextView
            r38 = r0
            int r38 = r38.getMeasuredWidth()
            int r37 = r37 + r38
            r35 = r37
            r37 = r25
            r38 = r2
            r0 = r38
            android.widget.TextView r0 = r0.mTitleTextView
            r38 = r0
            int r38 = r38.getMeasuredHeight()
            int r37 = r37 + r38
            r36 = r37
            r37 = r2
            r0 = r37
            android.widget.TextView r0 = r0.mTitleTextView
            r37 = r0
            r38 = r32
            r39 = r25
            r40 = r35
            r41 = r36
            r37.layout(r38, r39, r40, r41)
            r37 = r35
            r38 = r2
            r0 = r38
            int r0 = r0.mTitleMarginEnd
            r38 = r0
            int r37 = r37 + r38
            r32 = r37
            r37 = r36
            r38 = r34
            r0 = r38
            int r0 = r0.bottomMargin
            r38 = r0
            int r37 = r37 + r38
            r25 = r37
        L_0x0666:
            r37 = r23
            if (r37 == 0) goto L_0x06db
            r37 = r2
            r0 = r37
            android.widget.TextView r0 = r0.mSubtitleTextView
            r37 = r0
            android.view.ViewGroup$LayoutParams r37 = r37.getLayoutParams()
            android.support.v7.widget.Toolbar$LayoutParams r37 = (android.support.p003v7.widget.Toolbar.LayoutParams) r37
            r34 = r37
            r37 = r25
            r38 = r34
            r0 = r38
            int r0 = r0.topMargin
            r38 = r0
            int r37 = r37 + r38
            r25 = r37
            r37 = r33
            r38 = r2
            r0 = r38
            android.widget.TextView r0 = r0.mSubtitleTextView
            r38 = r0
            int r38 = r38.getMeasuredWidth()
            int r37 = r37 + r38
            r35 = r37
            r37 = r25
            r38 = r2
            r0 = r38
            android.widget.TextView r0 = r0.mSubtitleTextView
            r38 = r0
            int r38 = r38.getMeasuredHeight()
            int r37 = r37 + r38
            r36 = r37
            r37 = r2
            r0 = r37
            android.widget.TextView r0 = r0.mSubtitleTextView
            r37 = r0
            r38 = r33
            r39 = r25
            r40 = r35
            r41 = r36
            r37.layout(r38, r39, r40, r41)
            r37 = r35
            r38 = r2
            r0 = r38
            int r0 = r0.mTitleMarginEnd
            r38 = r0
            int r37 = r37 + r38
            r33 = r37
            r37 = r36
            r38 = r34
            r0 = r38
            int r0 = r0.bottomMargin
            r38 = r0
            int r37 = r37 + r38
            r25 = r37
        L_0x06db:
            r37 = r30
            if (r37 == 0) goto L_0x0446
            r37 = r32
            r38 = r33
            int r37 = java.lang.Math.max(r37, r38)
            r15 = r37
            goto L_0x0446
        L_0x06eb:
            r37 = 0
            goto L_0x05cc
        L_0x06ef:
            r37 = r2
            r38 = r2
            r0 = r38
            java.util.ArrayList<android.view.View> r0 = r0.mTempViews
            r38 = r0
            r39 = 5
            r37.addCustomViewsWithGravity(r38, r39)
            r37 = r2
            r0 = r37
            java.util.ArrayList<android.view.View> r0 = r0.mTempViews
            r37 = r0
            int r37 = r37.size()
            r26 = r37
            r37 = 0
            r27 = r37
        L_0x0710:
            r37 = r27
            r38 = r26
            r0 = r37
            r1 = r38
            if (r0 >= r1) goto L_0x073b
            r37 = r2
            r38 = r2
            r0 = r38
            java.util.ArrayList<android.view.View> r0 = r0.mTempViews
            r38 = r0
            r39 = r27
            java.lang.Object r38 = r38.get(r39)
            android.view.View r38 = (android.view.View) r38
            r39 = r16
            r40 = r17
            r41 = r19
            int r37 = r37.layoutChildRight(r38, r39, r40, r41)
            r16 = r37
            int r27 = r27 + 1
            goto L_0x0710
        L_0x073b:
            r37 = r2
            r38 = r2
            r0 = r38
            java.util.ArrayList<android.view.View> r0 = r0.mTempViews
            r38 = r0
            r39 = 1
            r37.addCustomViewsWithGravity(r38, r39)
            r37 = r2
            r38 = r2
            r0 = r38
            java.util.ArrayList<android.view.View> r0 = r0.mTempViews
            r38 = r0
            r39 = r17
            int r37 = r37.getViewListMeasuredWidth(r38, r39)
            r27 = r37
            r37 = r11
            r38 = r9
            r39 = r11
            int r38 = r38 - r39
            r39 = r12
            int r38 = r38 - r39
            r39 = 2
            int r38 = r38 / 2
            int r37 = r37 + r38
            r28 = r37
            r37 = r27
            r38 = 2
            int r37 = r37 / 2
            r29 = r37
            r37 = r28
            r38 = r29
            int r37 = r37 - r38
            r30 = r37
            r37 = r30
            r38 = r27
            int r37 = r37 + r38
            r31 = r37
            r37 = r30
            r38 = r15
            r0 = r37
            r1 = r38
            if (r0 >= r1) goto L_0x07d3
            r37 = r15
            r30 = r37
        L_0x0796:
            r37 = r2
            r0 = r37
            java.util.ArrayList<android.view.View> r0 = r0.mTempViews
            r37 = r0
            int r37 = r37.size()
            r32 = r37
            r37 = 0
            r33 = r37
        L_0x07a8:
            r37 = r33
            r38 = r32
            r0 = r37
            r1 = r38
            if (r0 >= r1) goto L_0x07ea
            r37 = r2
            r38 = r2
            r0 = r38
            java.util.ArrayList<android.view.View> r0 = r0.mTempViews
            r38 = r0
            r39 = r33
            java.lang.Object r38 = r38.get(r39)
            android.view.View r38 = (android.view.View) r38
            r39 = r30
            r40 = r17
            r41 = r19
            int r37 = r37.layoutChildLeft(r38, r39, r40, r41)
            r30 = r37
            int r33 = r33 + 1
            goto L_0x07a8
        L_0x07d3:
            r37 = r31
            r38 = r16
            r0 = r37
            r1 = r38
            if (r0 <= r1) goto L_0x0796
            r37 = r30
            r38 = r31
            r39 = r16
            int r38 = r38 - r39
            int r37 = r37 - r38
            r30 = r37
            goto L_0x0796
        L_0x07ea:
            r37 = r2
            r0 = r37
            java.util.ArrayList<android.view.View> r0 = r0.mTempViews
            r37 = r0
            r37.clear()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.widget.Toolbar.onLayout(boolean, int, int, int, int):void");
    }

    private int getViewListMeasuredWidth(List<View> list, int[] iArr) {
        List<View> views = list;
        int[] collapsingMargins = iArr;
        int collapseLeft = collapsingMargins[0];
        int collapseRight = collapsingMargins[1];
        int width = 0;
        int count = views.size();
        for (int i = 0; i < count; i++) {
            View v = views.get(i);
            LayoutParams lp = (LayoutParams) v.getLayoutParams();
            int l = lp.leftMargin - collapseLeft;
            int r = lp.rightMargin - collapseRight;
            int leftMargin = Math.max(0, l);
            int rightMargin = Math.max(0, r);
            collapseLeft = Math.max(0, -l);
            collapseRight = Math.max(0, -r);
            width += leftMargin + v.getMeasuredWidth() + rightMargin;
        }
        return width;
    }

    private int layoutChildLeft(View view, int left, int[] iArr, int alignmentHeight) {
        View child = view;
        int[] collapsingMargins = iArr;
        LayoutParams lp = (LayoutParams) child.getLayoutParams();
        int l = lp.leftMargin - collapsingMargins[0];
        int left2 = left + Math.max(0, l);
        collapsingMargins[0] = Math.max(0, -l);
        int top = getChildTop(child, alignmentHeight);
        int childWidth = child.getMeasuredWidth();
        child.layout(left2, top, left2 + childWidth, top + child.getMeasuredHeight());
        return left2 + childWidth + lp.rightMargin;
    }

    private int layoutChildRight(View view, int right, int[] iArr, int alignmentHeight) {
        View child = view;
        int[] collapsingMargins = iArr;
        LayoutParams lp = (LayoutParams) child.getLayoutParams();
        int r = lp.rightMargin - collapsingMargins[1];
        int right2 = right - Math.max(0, r);
        collapsingMargins[1] = Math.max(0, -r);
        int top = getChildTop(child, alignmentHeight);
        int childWidth = child.getMeasuredWidth();
        child.layout(right2 - childWidth, top, right2, top + child.getMeasuredHeight());
        return right2 - (childWidth + lp.leftMargin);
    }

    private int getChildTop(View view, int i) {
        View child = view;
        int alignmentHeight = i;
        LayoutParams lp = (LayoutParams) child.getLayoutParams();
        int childHeight = child.getMeasuredHeight();
        int alignmentOffset = alignmentHeight > 0 ? (childHeight - alignmentHeight) / 2 : 0;
        switch (getChildVerticalGravity(lp.gravity)) {
            case 48:
                return getPaddingTop() - alignmentOffset;
            case 80:
                return (((getHeight() - getPaddingBottom()) - childHeight) - lp.bottomMargin) - alignmentOffset;
            default:
                int paddingTop = getPaddingTop();
                int paddingBottom = getPaddingBottom();
                int height = getHeight();
                int spaceAbove = (((height - paddingTop) - paddingBottom) - childHeight) / 2;
                if (spaceAbove < lp.topMargin) {
                    spaceAbove = lp.topMargin;
                } else {
                    int spaceBelow = (((height - paddingBottom) - childHeight) - spaceAbove) - paddingTop;
                    if (spaceBelow < lp.bottomMargin) {
                        spaceAbove = Math.max(0, spaceAbove - (lp.bottomMargin - spaceBelow));
                    }
                }
                return paddingTop + spaceAbove;
        }
    }

    private int getChildVerticalGravity(int gravity) {
        int vgrav = gravity & 112;
        switch (vgrav) {
            case 16:
            case 48:
            case 80:
                return vgrav;
            default:
                return this.mGravity & 112;
        }
    }

    private void addCustomViewsWithGravity(List<View> list, int i) {
        List<View> views = list;
        int gravity = i;
        boolean isRtl = ViewCompat.getLayoutDirection(this) == 1;
        int childCount = getChildCount();
        int absGrav = GravityCompat.getAbsoluteGravity(gravity, ViewCompat.getLayoutDirection(this));
        views.clear();
        if (isRtl) {
            for (int i2 = childCount - 1; i2 >= 0; i2--) {
                View child = getChildAt(i2);
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                if (lp.mViewType == 0 && shouldLayout(child) && getChildHorizontalGravity(lp.gravity) == absGrav) {
                    boolean add = views.add(child);
                }
            }
            return;
        }
        for (int i3 = 0; i3 < childCount; i3++) {
            View child2 = getChildAt(i3);
            LayoutParams lp2 = (LayoutParams) child2.getLayoutParams();
            if (lp2.mViewType == 0 && shouldLayout(child2) && getChildHorizontalGravity(lp2.gravity) == absGrav) {
                boolean add2 = views.add(child2);
            }
        }
    }

    private int getChildHorizontalGravity(int gravity) {
        int ld = ViewCompat.getLayoutDirection(this);
        int hGrav = GravityCompat.getAbsoluteGravity(gravity, ld) & 7;
        switch (hGrav) {
            case 1:
            case 3:
            case 5:
                return hGrav;
            default:
                return ld == 1 ? 5 : 3;
        }
    }

    private boolean shouldLayout(View view) {
        View view2 = view;
        return (view2 == null || view2.getParent() != this || view2.getVisibility() == 8) ? false : true;
    }

    private int getHorizontalMargins(View v) {
        ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
        return MarginLayoutParamsCompat.getMarginStart(mlp) + MarginLayoutParamsCompat.getMarginEnd(mlp);
    }

    private int getVerticalMargins(View v) {
        ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
        return mlp.topMargin + mlp.bottomMargin;
    }

    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        LayoutParams layoutParams;
        new LayoutParams(getContext(), attrs);
        return layoutParams;
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        LayoutParams layoutParams2;
        LayoutParams layoutParams3;
        LayoutParams layoutParams4;
        LayoutParams layoutParams5;
        ViewGroup.LayoutParams p = layoutParams;
        if (p instanceof LayoutParams) {
            new LayoutParams((LayoutParams) p);
            return layoutParams5;
        } else if (p instanceof ActionBar.LayoutParams) {
            new LayoutParams((ActionBar.LayoutParams) p);
            return layoutParams4;
        } else if (p instanceof ViewGroup.MarginLayoutParams) {
            new LayoutParams((ViewGroup.MarginLayoutParams) p);
            return layoutParams3;
        } else {
            new LayoutParams(p);
            return layoutParams2;
        }
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateDefaultLayoutParams() {
        LayoutParams layoutParams;
        new LayoutParams(-2, -2);
        return layoutParams;
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        ViewGroup.LayoutParams p = layoutParams;
        return super.checkLayoutParams(p) && (p instanceof LayoutParams);
    }

    private static boolean isCustomView(View child) {
        return ((LayoutParams) child.getLayoutParams()).mViewType == 0;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public DecorToolbar getWrapper() {
        ToolbarWidgetWrapper toolbarWidgetWrapper;
        if (this.mWrapper == null) {
            new ToolbarWidgetWrapper(this, true);
            this.mWrapper = toolbarWidgetWrapper;
        }
        return this.mWrapper;
    }

    /* access modifiers changed from: package-private */
    public void removeChildrenForExpandedActionView() {
        for (int i = getChildCount() - 1; i >= 0; i--) {
            View child = getChildAt(i);
            if (!(((LayoutParams) child.getLayoutParams()).mViewType == 2 || child == this.mMenuView)) {
                removeViewAt(i);
                boolean add = this.mHiddenViews.add(child);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void addChildrenForExpandedActionView() {
        for (int i = this.mHiddenViews.size() - 1; i >= 0; i--) {
            addView(this.mHiddenViews.get(i));
        }
        this.mHiddenViews.clear();
    }

    private boolean isChildOrHidden(View view) {
        View child = view;
        return child.getParent() == this || this.mHiddenViews.contains(child);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setCollapsible(boolean collapsible) {
        this.mCollapsible = collapsible;
        requestLayout();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setMenuCallbacks(MenuPresenter.Callback callback, MenuBuilder.Callback callback2) {
        MenuPresenter.Callback pcb = callback;
        MenuBuilder.Callback mcb = callback2;
        this.mActionMenuPresenterCallback = pcb;
        this.mMenuBuilderCallback = mcb;
        if (this.mMenuView != null) {
            this.mMenuView.setMenuCallbacks(pcb, mcb);
        }
    }

    private void ensureContentInsets() {
        RtlSpacingHelper rtlSpacingHelper;
        if (this.mContentInsets == null) {
            new RtlSpacingHelper();
            this.mContentInsets = rtlSpacingHelper;
        }
    }

    /* access modifiers changed from: package-private */
    public ActionMenuPresenter getOuterActionMenuPresenter() {
        return this.mOuterActionMenuPresenter;
    }

    /* access modifiers changed from: package-private */
    public Context getPopupContext() {
        return this.mPopupContext;
    }

    /* renamed from: android.support.v7.widget.Toolbar$LayoutParams */
    public static class LayoutParams extends ActionBar.LayoutParams {
        static final int CUSTOM = 0;
        static final int EXPANDED = 2;
        static final int SYSTEM = 1;
        int mViewType;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(@NonNull Context c, AttributeSet attrs) {
            super(c, attrs);
            this.mViewType = 0;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(int width, int height) {
            super(width, height);
            this.mViewType = 0;
            this.gravity = 8388627;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(int width, int height, int gravity) {
            super(width, height);
            this.mViewType = 0;
            this.gravity = gravity;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public LayoutParams(int gravity) {
            this(-2, -1, gravity);
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public LayoutParams(android.support.p003v7.widget.Toolbar.LayoutParams r5) {
            /*
                r4 = this;
                r0 = r4
                r1 = r5
                r2 = r0
                r3 = r1
                r2.<init>((android.support.p003v7.app.ActionBar.LayoutParams) r3)
                r2 = r0
                r3 = 0
                r2.mViewType = r3
                r2 = r0
                r3 = r1
                int r3 = r3.mViewType
                r2.mViewType = r3
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.widget.Toolbar.LayoutParams.<init>(android.support.v7.widget.Toolbar$LayoutParams):void");
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(ActionBar.LayoutParams source) {
            super(source);
            this.mViewType = 0;
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public LayoutParams(android.view.ViewGroup.MarginLayoutParams r5) {
            /*
                r4 = this;
                r0 = r4
                r1 = r5
                r2 = r0
                r3 = r1
                r2.<init>((android.view.ViewGroup.LayoutParams) r3)
                r2 = r0
                r3 = 0
                r2.mViewType = r3
                r2 = r0
                r3 = r1
                r2.copyMarginsFromCompat(r3)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.widget.Toolbar.LayoutParams.<init>(android.view.ViewGroup$MarginLayoutParams):void");
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
            this.mViewType = 0;
        }

        /* access modifiers changed from: package-private */
        public void copyMarginsFromCompat(ViewGroup.MarginLayoutParams marginLayoutParams) {
            ViewGroup.MarginLayoutParams source = marginLayoutParams;
            this.leftMargin = source.leftMargin;
            this.topMargin = source.topMargin;
            this.rightMargin = source.rightMargin;
            this.bottomMargin = source.bottomMargin;
        }
    }

    /* renamed from: android.support.v7.widget.Toolbar$SavedState */
    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR;
        int expandedMenuItemId;
        boolean isOverflowOpen;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public SavedState(Parcel source) {
            this(source, (ClassLoader) null);
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public SavedState(android.os.Parcel r7, java.lang.ClassLoader r8) {
            /*
                r6 = this;
                r0 = r6
                r1 = r7
                r2 = r8
                r3 = r0
                r4 = r1
                r5 = r2
                r3.<init>(r4, r5)
                r3 = r0
                r4 = r1
                int r4 = r4.readInt()
                r3.expandedMenuItemId = r4
                r3 = r0
                r4 = r1
                int r4 = r4.readInt()
                if (r4 == 0) goto L_0x001d
                r4 = 1
            L_0x001a:
                r3.isOverflowOpen = r4
                return
            L_0x001d:
                r4 = 0
                goto L_0x001a
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.widget.Toolbar.SavedState.<init>(android.os.Parcel, java.lang.ClassLoader):void");
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SavedState(Parcelable superState) {
            super(superState);
        }

        public void writeToParcel(Parcel parcel, int flags) {
            Parcel out = parcel;
            super.writeToParcel(out, flags);
            out.writeInt(this.expandedMenuItemId);
            out.writeInt(this.isOverflowOpen ? 1 : 0);
        }

        static {
            Parcelable.Creator<SavedState> creator;
            new Parcelable.ClassLoaderCreator<SavedState>() {
                public SavedState createFromParcel(Parcel in, ClassLoader loader) {
                    SavedState savedState;
                    new SavedState(in, loader);
                    return savedState;
                }

                public SavedState createFromParcel(Parcel in) {
                    SavedState savedState;
                    new SavedState(in, (ClassLoader) null);
                    return savedState;
                }

                public SavedState[] newArray(int size) {
                    return new SavedState[size];
                }
            };
            CREATOR = creator;
        }
    }

    /* renamed from: android.support.v7.widget.Toolbar$ExpandedActionViewMenuPresenter */
    private class ExpandedActionViewMenuPresenter implements MenuPresenter {
        MenuItemImpl mCurrentExpandedItem;
        MenuBuilder mMenu;
        final /* synthetic */ Toolbar this$0;

        ExpandedActionViewMenuPresenter(Toolbar toolbar) {
            this.this$0 = toolbar;
        }

        public void initForMenu(Context context, MenuBuilder menuBuilder) {
            Context context2 = context;
            MenuBuilder menu = menuBuilder;
            if (!(this.mMenu == null || this.mCurrentExpandedItem == null)) {
                boolean collapseItemActionView = this.mMenu.collapseItemActionView(this.mCurrentExpandedItem);
            }
            this.mMenu = menu;
        }

        public MenuView getMenuView(ViewGroup viewGroup) {
            ViewGroup viewGroup2 = viewGroup;
            return null;
        }

        public void updateMenuView(boolean z) {
            boolean z2 = z;
            if (this.mCurrentExpandedItem != null) {
                boolean found = false;
                if (this.mMenu != null) {
                    int count = this.mMenu.size();
                    int i = 0;
                    while (true) {
                        if (i >= count) {
                            break;
                        } else if (this.mMenu.getItem(i) == this.mCurrentExpandedItem) {
                            found = true;
                            break;
                        } else {
                            i++;
                        }
                    }
                }
                if (!found) {
                    boolean collapseItemActionView = collapseItemActionView(this.mMenu, this.mCurrentExpandedItem);
                }
            }
        }

        public void setCallback(MenuPresenter.Callback cb) {
        }

        public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
            SubMenuBuilder subMenuBuilder2 = subMenuBuilder;
            return false;
        }

        public void onCloseMenu(MenuBuilder menu, boolean allMenusAreClosing) {
        }

        public boolean flagActionItems() {
            return false;
        }

        public boolean expandItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
            MenuBuilder menuBuilder2 = menuBuilder;
            MenuItemImpl item = menuItemImpl;
            this.this$0.ensureCollapseButtonView();
            ViewParent collapseButtonParent = this.this$0.mCollapseButtonView.getParent();
            if (collapseButtonParent != this.this$0) {
                if (collapseButtonParent instanceof ViewGroup) {
                    ((ViewGroup) collapseButtonParent).removeView(this.this$0.mCollapseButtonView);
                }
                this.this$0.addView(this.this$0.mCollapseButtonView);
            }
            this.this$0.mExpandedActionView = item.getActionView();
            this.mCurrentExpandedItem = item;
            ViewParent expandedActionParent = this.this$0.mExpandedActionView.getParent();
            if (expandedActionParent != this.this$0) {
                if (expandedActionParent instanceof ViewGroup) {
                    ((ViewGroup) expandedActionParent).removeView(this.this$0.mExpandedActionView);
                }
                LayoutParams lp = this.this$0.generateDefaultLayoutParams();
                lp.gravity = 8388611 | (this.this$0.mButtonGravity & 112);
                lp.mViewType = 2;
                this.this$0.mExpandedActionView.setLayoutParams(lp);
                this.this$0.addView(this.this$0.mExpandedActionView);
            }
            this.this$0.removeChildrenForExpandedActionView();
            this.this$0.requestLayout();
            item.setActionViewExpanded(true);
            if (this.this$0.mExpandedActionView instanceof CollapsibleActionView) {
                ((CollapsibleActionView) this.this$0.mExpandedActionView).onActionViewExpanded();
            }
            return true;
        }

        public boolean collapseItemActionView(MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
            MenuBuilder menuBuilder2 = menuBuilder;
            MenuItemImpl item = menuItemImpl;
            if (this.this$0.mExpandedActionView instanceof CollapsibleActionView) {
                ((CollapsibleActionView) this.this$0.mExpandedActionView).onActionViewCollapsed();
            }
            this.this$0.removeView(this.this$0.mExpandedActionView);
            this.this$0.removeView(this.this$0.mCollapseButtonView);
            this.this$0.mExpandedActionView = null;
            this.this$0.addChildrenForExpandedActionView();
            this.mCurrentExpandedItem = null;
            this.this$0.requestLayout();
            item.setActionViewExpanded(false);
            return true;
        }

        public int getId() {
            return 0;
        }

        public Parcelable onSaveInstanceState() {
            return null;
        }

        public void onRestoreInstanceState(Parcelable state) {
        }
    }
}

package android.support.design.internal;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.Dimension;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleRes;
import android.support.design.C0064R;
import android.support.p000v4.util.Pools;
import android.support.p000v4.view.ViewCompat;
import android.support.p000v4.view.animation.FastOutSlowInInterpolator;
import android.support.p003v7.appcompat.C0425R;
import android.support.p003v7.content.res.AppCompatResources;
import android.support.p003v7.view.menu.MenuBuilder;
import android.support.p003v7.view.menu.MenuItemImpl;
import android.support.p003v7.view.menu.MenuView;
import android.support.transition.AutoTransition;
import android.support.transition.Transition;
import android.support.transition.TransitionManager;
import android.support.transition.TransitionSet;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class BottomNavigationMenuView extends ViewGroup implements MenuView {
    private static final long ACTIVE_ANIMATION_DURATION_MS = 115;
    private static final int[] CHECKED_STATE_SET = {16842912};
    private static final int[] DISABLED_STATE_SET = {-16842910};
    private final int activeItemMaxWidth;
    private final int activeItemMinWidth;
    private BottomNavigationItemView[] buttons;
    private final int inactiveItemMaxWidth;
    private final int inactiveItemMinWidth;
    private Drawable itemBackground;
    private int itemBackgroundRes;
    private final int itemHeight;
    private boolean itemHorizontalTranslationEnabled;
    @Dimension
    private int itemIconSize;
    private ColorStateList itemIconTint;
    private final Pools.Pool<BottomNavigationItemView> itemPool;
    @StyleRes
    private int itemTextAppearanceActive;
    @StyleRes
    private int itemTextAppearanceInactive;
    private final ColorStateList itemTextColorDefault;
    private ColorStateList itemTextColorFromUser;
    private int labelVisibilityMode;
    /* access modifiers changed from: private */
    public MenuBuilder menu;
    private final View.OnClickListener onClickListener;
    /* access modifiers changed from: private */
    public BottomNavigationPresenter presenter;
    private int selectedItemId;
    private int selectedItemPosition;
    private final TransitionSet set;
    private int[] tempChildWidths;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public BottomNavigationMenuView(Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BottomNavigationMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Pools.Pool<BottomNavigationItemView> pool;
        TransitionSet transitionSet;
        TimeInterpolator timeInterpolator;
        Transition transition;
        View.OnClickListener onClickListener2;
        new Pools.SynchronizedPool(5);
        this.itemPool = pool;
        this.selectedItemId = 0;
        this.selectedItemPosition = 0;
        Resources res = getResources();
        this.inactiveItemMaxWidth = res.getDimensionPixelSize(C0064R.dimen.design_bottom_navigation_item_max_width);
        this.inactiveItemMinWidth = res.getDimensionPixelSize(C0064R.dimen.design_bottom_navigation_item_min_width);
        this.activeItemMaxWidth = res.getDimensionPixelSize(C0064R.dimen.design_bottom_navigation_active_item_max_width);
        this.activeItemMinWidth = res.getDimensionPixelSize(C0064R.dimen.design_bottom_navigation_active_item_min_width);
        this.itemHeight = res.getDimensionPixelSize(C0064R.dimen.design_bottom_navigation_height);
        this.itemTextColorDefault = createDefaultColorStateList(16842808);
        new AutoTransition();
        this.set = transitionSet;
        TransitionSet ordering = this.set.setOrdering(0);
        TransitionSet duration = this.set.setDuration((long) ACTIVE_ANIMATION_DURATION_MS);
        new FastOutSlowInInterpolator();
        TransitionSet interpolator = this.set.setInterpolator(timeInterpolator);
        new TextScale();
        TransitionSet addTransition = this.set.addTransition(transition);
        new View.OnClickListener(this) {
            final /* synthetic */ BottomNavigationMenuView this$0;

            {
                this.this$0 = this$0;
            }

            public void onClick(View v) {
                MenuItem item = ((BottomNavigationItemView) v).getItemData();
                if (!this.this$0.menu.performItemAction(item, this.this$0.presenter, 0)) {
                    MenuItem checked = item.setChecked(true);
                }
            }
        };
        this.onClickListener = onClickListener2;
        this.tempChildWidths = new int[5];
    }

    public void initialize(MenuBuilder menu2) {
        MenuBuilder menuBuilder = menu2;
        this.menu = menuBuilder;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x01a0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onMeasure(int r26, int r27) {
        /*
            r25 = this;
            r2 = r25
            r3 = r26
            r4 = r27
            r18 = r3
            int r18 = android.view.View.MeasureSpec.getSize(r18)
            r5 = r18
            r18 = r2
            r0 = r18
            android.support.v7.view.menu.MenuBuilder r0 = r0.menu
            r18 = r0
            java.util.ArrayList r18 = r18.getVisibleItems()
            int r18 = r18.size()
            r6 = r18
            r18 = r2
            int r18 = r18.getChildCount()
            r7 = r18
            r18 = r2
            r0 = r18
            int r0 = r0.itemHeight
            r18 = r0
            r19 = 1073741824(0x40000000, float:2.0)
            int r18 = android.view.View.MeasureSpec.makeMeasureSpec(r18, r19)
            r8 = r18
            r18 = r2
            r19 = r2
            r0 = r19
            int r0 = r0.labelVisibilityMode
            r19 = r0
            r20 = r6
            boolean r18 = r18.isShifting(r19, r20)
            if (r18 == 0) goto L_0x01bb
            r18 = r2
            r0 = r18
            boolean r0 = r0.itemHorizontalTranslationEnabled
            r18 = r0
            if (r18 == 0) goto L_0x01bb
            r18 = r2
            r19 = r2
            r0 = r19
            int r0 = r0.selectedItemPosition
            r19 = r0
            android.view.View r18 = r18.getChildAt(r19)
            r9 = r18
            r18 = r2
            r0 = r18
            int r0 = r0.activeItemMinWidth
            r18 = r0
            r10 = r18
            r18 = r9
            int r18 = r18.getVisibility()
            r19 = 8
            r0 = r18
            r1 = r19
            if (r0 == r1) goto L_0x009f
            r18 = r9
            r19 = r2
            r0 = r19
            int r0 = r0.activeItemMaxWidth
            r19 = r0
            r20 = -2147483648(0xffffffff80000000, float:-0.0)
            int r19 = android.view.View.MeasureSpec.makeMeasureSpec(r19, r20)
            r20 = r8
            r18.measure(r19, r20)
            r18 = r10
            r19 = r9
            int r19 = r19.getMeasuredWidth()
            int r18 = java.lang.Math.max(r18, r19)
            r10 = r18
        L_0x009f:
            r18 = r6
            r19 = r9
            int r19 = r19.getVisibility()
            r20 = 8
            r0 = r19
            r1 = r20
            if (r0 == r1) goto L_0x0174
            r19 = 1
        L_0x00b1:
            int r18 = r18 - r19
            r11 = r18
            r18 = r5
            r19 = r11
            r20 = r2
            r0 = r20
            int r0 = r0.inactiveItemMinWidth
            r20 = r0
            int r19 = r19 * r20
            int r18 = r18 - r19
            r12 = r18
            r18 = r12
            r19 = r10
            r20 = r2
            r0 = r20
            int r0 = r0.activeItemMaxWidth
            r20 = r0
            int r19 = java.lang.Math.min(r19, r20)
            int r18 = java.lang.Math.min(r18, r19)
            r13 = r18
            r18 = r5
            r19 = r13
            int r18 = r18 - r19
            r19 = r11
            if (r19 != 0) goto L_0x0178
            r19 = 1
        L_0x00e9:
            int r18 = r18 / r19
            r14 = r18
            r18 = r14
            r19 = r2
            r0 = r19
            int r0 = r0.inactiveItemMaxWidth
            r19 = r0
            int r18 = java.lang.Math.min(r18, r19)
            r15 = r18
            r18 = r5
            r19 = r13
            int r18 = r18 - r19
            r19 = r15
            r20 = r11
            int r19 = r19 * r20
            int r18 = r18 - r19
            r16 = r18
            r18 = 0
            r17 = r18
        L_0x0111:
            r18 = r17
            r19 = r7
            r0 = r18
            r1 = r19
            if (r0 >= r1) goto L_0x018e
            r18 = r2
            r19 = r17
            android.view.View r18 = r18.getChildAt(r19)
            int r18 = r18.getVisibility()
            r19 = 8
            r0 = r18
            r1 = r19
            if (r0 == r1) goto L_0x017f
            r18 = r2
            r0 = r18
            int[] r0 = r0.tempChildWidths
            r18 = r0
            r19 = r17
            r20 = r17
            r21 = r2
            r0 = r21
            int r0 = r0.selectedItemPosition
            r21 = r0
            r0 = r20
            r1 = r21
            if (r0 != r1) goto L_0x017c
            r20 = r13
        L_0x014b:
            r18[r19] = r20
            r18 = r16
            if (r18 <= 0) goto L_0x0171
            r18 = r2
            r0 = r18
            int[] r0 = r0.tempChildWidths
            r18 = r0
            r19 = r17
            r23 = r18
            r24 = r19
            r18 = r23
            r19 = r24
            r20 = r23
            r21 = r24
            r20 = r20[r21]
            r21 = 1
            int r20 = r20 + 1
            r18[r19] = r20
            int r16 = r16 + -1
        L_0x0171:
            int r17 = r17 + 1
            goto L_0x0111
        L_0x0174:
            r19 = 0
            goto L_0x00b1
        L_0x0178:
            r19 = r11
            goto L_0x00e9
        L_0x017c:
            r20 = r15
            goto L_0x014b
        L_0x017f:
            r18 = r2
            r0 = r18
            int[] r0 = r0.tempChildWidths
            r18 = r0
            r19 = r17
            r20 = 0
            r18[r19] = r20
            goto L_0x0171
        L_0x018e:
            r18 = 0
            r9 = r18
            r18 = 0
            r10 = r18
        L_0x0196:
            r18 = r10
            r19 = r7
            r0 = r18
            r1 = r19
            if (r0 >= r1) goto L_0x0289
            r18 = r2
            r19 = r10
            android.view.View r18 = r18.getChildAt(r19)
            r11 = r18
            r18 = r11
            int r18 = r18.getVisibility()
            r19 = 8
            r0 = r18
            r1 = r19
            if (r0 != r1) goto L_0x024c
        L_0x01b8:
            int r10 = r10 + 1
            goto L_0x0196
        L_0x01bb:
            r18 = r5
            r19 = r6
            if (r19 != 0) goto L_0x023a
            r19 = 1
        L_0x01c3:
            int r18 = r18 / r19
            r9 = r18
            r18 = r9
            r19 = r2
            r0 = r19
            int r0 = r0.activeItemMaxWidth
            r19 = r0
            int r18 = java.lang.Math.min(r18, r19)
            r10 = r18
            r18 = r5
            r19 = r10
            r20 = r6
            int r19 = r19 * r20
            int r18 = r18 - r19
            r11 = r18
            r18 = 0
            r12 = r18
        L_0x01e7:
            r18 = r12
            r19 = r7
            r0 = r18
            r1 = r19
            if (r0 >= r1) goto L_0x018e
            r18 = r2
            r19 = r12
            android.view.View r18 = r18.getChildAt(r19)
            int r18 = r18.getVisibility()
            r19 = 8
            r0 = r18
            r1 = r19
            if (r0 == r1) goto L_0x023d
            r18 = r2
            r0 = r18
            int[] r0 = r0.tempChildWidths
            r18 = r0
            r19 = r12
            r20 = r10
            r18[r19] = r20
            r18 = r11
            if (r18 <= 0) goto L_0x0237
            r18 = r2
            r0 = r18
            int[] r0 = r0.tempChildWidths
            r18 = r0
            r19 = r12
            r23 = r18
            r24 = r19
            r18 = r23
            r19 = r24
            r20 = r23
            r21 = r24
            r20 = r20[r21]
            r21 = 1
            int r20 = r20 + 1
            r18[r19] = r20
            int r11 = r11 + -1
        L_0x0237:
            int r12 = r12 + 1
            goto L_0x01e7
        L_0x023a:
            r19 = r6
            goto L_0x01c3
        L_0x023d:
            r18 = r2
            r0 = r18
            int[] r0 = r0.tempChildWidths
            r18 = r0
            r19 = r12
            r20 = 0
            r18[r19] = r20
            goto L_0x0237
        L_0x024c:
            r18 = r11
            r19 = r2
            r0 = r19
            int[] r0 = r0.tempChildWidths
            r19 = r0
            r20 = r10
            r19 = r19[r20]
            r20 = 1073741824(0x40000000, float:2.0)
            int r19 = android.view.View.MeasureSpec.makeMeasureSpec(r19, r20)
            r20 = r8
            r18.measure(r19, r20)
            r18 = r11
            android.view.ViewGroup$LayoutParams r18 = r18.getLayoutParams()
            r12 = r18
            r18 = r12
            r19 = r11
            int r19 = r19.getMeasuredWidth()
            r0 = r19
            r1 = r18
            r1.width = r0
            r18 = r9
            r19 = r11
            int r19 = r19.getMeasuredWidth()
            int r18 = r18 + r19
            r9 = r18
            goto L_0x01b8
        L_0x0289:
            r18 = r2
            r19 = r9
            r20 = r9
            r21 = 1073741824(0x40000000, float:2.0)
            int r20 = android.view.View.MeasureSpec.makeMeasureSpec(r20, r21)
            r21 = 0
            int r19 = android.view.View.resolveSizeAndState(r19, r20, r21)
            r20 = r2
            r0 = r20
            int r0 = r0.itemHeight
            r20 = r0
            r21 = r8
            r22 = 0
            int r20 = android.view.View.resolveSizeAndState(r20, r21, r22)
            r18.setMeasuredDimension(r19, r20)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.internal.BottomNavigationMenuView.onMeasure(int, int):void");
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int left, int top, int right, int bottom) {
        boolean z2 = z;
        int count = getChildCount();
        int width = right - left;
        int height = bottom - top;
        int used = 0;
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != 8) {
                if (ViewCompat.getLayoutDirection(this) == 1) {
                    child.layout((width - used) - child.getMeasuredWidth(), 0, width - used, height);
                } else {
                    child.layout(used, 0, child.getMeasuredWidth() + used, height);
                }
                used += child.getMeasuredWidth();
            }
        }
    }

    public int getWindowAnimations() {
        return 0;
    }

    public void setIconTintList(ColorStateList colorStateList) {
        ColorStateList tint = colorStateList;
        this.itemIconTint = tint;
        if (this.buttons != null) {
            BottomNavigationItemView[] bottomNavigationItemViewArr = this.buttons;
            int length = bottomNavigationItemViewArr.length;
            for (int i = 0; i < length; i++) {
                bottomNavigationItemViewArr[i].setIconTintList(tint);
            }
        }
    }

    @Nullable
    public ColorStateList getIconTintList() {
        return this.itemIconTint;
    }

    public void setItemIconSize(@Dimension int i) {
        int iconSize = i;
        this.itemIconSize = iconSize;
        if (this.buttons != null) {
            BottomNavigationItemView[] bottomNavigationItemViewArr = this.buttons;
            int length = bottomNavigationItemViewArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                bottomNavigationItemViewArr[i2].setIconSize(iconSize);
            }
        }
    }

    @Dimension
    public int getItemIconSize() {
        return this.itemIconSize;
    }

    public void setItemTextColor(ColorStateList colorStateList) {
        ColorStateList color = colorStateList;
        this.itemTextColorFromUser = color;
        if (this.buttons != null) {
            BottomNavigationItemView[] bottomNavigationItemViewArr = this.buttons;
            int length = bottomNavigationItemViewArr.length;
            for (int i = 0; i < length; i++) {
                bottomNavigationItemViewArr[i].setTextColor(color);
            }
        }
    }

    public ColorStateList getItemTextColor() {
        return this.itemTextColorFromUser;
    }

    public void setItemTextAppearanceInactive(@StyleRes int i) {
        int textAppearanceRes = i;
        this.itemTextAppearanceInactive = textAppearanceRes;
        if (this.buttons != null) {
            BottomNavigationItemView[] bottomNavigationItemViewArr = this.buttons;
            int length = bottomNavigationItemViewArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                BottomNavigationItemView item = bottomNavigationItemViewArr[i2];
                item.setTextAppearanceInactive(textAppearanceRes);
                if (this.itemTextColorFromUser != null) {
                    item.setTextColor(this.itemTextColorFromUser);
                }
            }
        }
    }

    @StyleRes
    public int getItemTextAppearanceInactive() {
        return this.itemTextAppearanceInactive;
    }

    public void setItemTextAppearanceActive(@StyleRes int i) {
        int textAppearanceRes = i;
        this.itemTextAppearanceActive = textAppearanceRes;
        if (this.buttons != null) {
            BottomNavigationItemView[] bottomNavigationItemViewArr = this.buttons;
            int length = bottomNavigationItemViewArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                BottomNavigationItemView item = bottomNavigationItemViewArr[i2];
                item.setTextAppearanceActive(textAppearanceRes);
                if (this.itemTextColorFromUser != null) {
                    item.setTextColor(this.itemTextColorFromUser);
                }
            }
        }
    }

    @StyleRes
    public int getItemTextAppearanceActive() {
        return this.itemTextAppearanceActive;
    }

    public void setItemBackgroundRes(int i) {
        int background = i;
        this.itemBackgroundRes = background;
        if (this.buttons != null) {
            BottomNavigationItemView[] bottomNavigationItemViewArr = this.buttons;
            int length = bottomNavigationItemViewArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                bottomNavigationItemViewArr[i2].setItemBackground(background);
            }
        }
    }

    @Deprecated
    public int getItemBackgroundRes() {
        return this.itemBackgroundRes;
    }

    public void setItemBackground(@Nullable Drawable drawable) {
        Drawable background = drawable;
        this.itemBackground = background;
        if (this.buttons != null) {
            BottomNavigationItemView[] bottomNavigationItemViewArr = this.buttons;
            int length = bottomNavigationItemViewArr.length;
            for (int i = 0; i < length; i++) {
                bottomNavigationItemViewArr[i].setItemBackground(background);
            }
        }
    }

    @Nullable
    public Drawable getItemBackground() {
        if (this.buttons == null || this.buttons.length <= 0) {
            return this.itemBackground;
        }
        return this.buttons[0].getBackground();
    }

    public void setLabelVisibilityMode(int labelVisibilityMode2) {
        int i = labelVisibilityMode2;
        this.labelVisibilityMode = i;
    }

    public int getLabelVisibilityMode() {
        return this.labelVisibilityMode;
    }

    public void setItemHorizontalTranslationEnabled(boolean itemHorizontalTranslationEnabled2) {
        boolean z = itemHorizontalTranslationEnabled2;
        this.itemHorizontalTranslationEnabled = z;
    }

    public boolean isItemHorizontalTranslationEnabled() {
        return this.itemHorizontalTranslationEnabled;
    }

    public ColorStateList createDefaultColorStateList(int baseColorThemeAttr) {
        TypedValue typedValue;
        ColorStateList colorStateList;
        new TypedValue();
        TypedValue value = typedValue;
        if (!getContext().getTheme().resolveAttribute(baseColorThemeAttr, value, true)) {
            return null;
        }
        ColorStateList baseColor = AppCompatResources.getColorStateList(getContext(), value.resourceId);
        if (!getContext().getTheme().resolveAttribute(C0425R.attr.colorPrimary, value, true)) {
            return null;
        }
        int colorPrimary = value.data;
        int defaultColor = baseColor.getDefaultColor();
        ColorStateList colorStateList2 = colorStateList;
        int[][] iArr = new int[3][];
        iArr[0] = DISABLED_STATE_SET;
        int[][] iArr2 = iArr;
        iArr2[1] = CHECKED_STATE_SET;
        int[][] iArr3 = iArr2;
        int[][] iArr4 = iArr3;
        iArr3[2] = EMPTY_STATE_SET;
        int[] iArr5 = new int[3];
        iArr5[0] = baseColor.getColorForState(DISABLED_STATE_SET, defaultColor);
        int[] iArr6 = iArr5;
        iArr6[1] = colorPrimary;
        int[] iArr7 = iArr6;
        iArr7[2] = defaultColor;
        new ColorStateList(iArr4, iArr7);
        return colorStateList2;
    }

    public void setPresenter(BottomNavigationPresenter presenter2) {
        BottomNavigationPresenter bottomNavigationPresenter = presenter2;
        this.presenter = bottomNavigationPresenter;
    }

    public void buildMenuView() {
        removeAllViews();
        if (this.buttons != null) {
            BottomNavigationItemView[] bottomNavigationItemViewArr = this.buttons;
            int length = bottomNavigationItemViewArr.length;
            for (int i = 0; i < length; i++) {
                BottomNavigationItemView item = bottomNavigationItemViewArr[i];
                if (item != null) {
                    boolean release = this.itemPool.release(item);
                }
            }
        }
        if (this.menu.size() == 0) {
            this.selectedItemId = 0;
            this.selectedItemPosition = 0;
            this.buttons = null;
            return;
        }
        this.buttons = new BottomNavigationItemView[this.menu.size()];
        boolean shifting = isShifting(this.labelVisibilityMode, this.menu.getVisibleItems().size());
        for (int i2 = 0; i2 < this.menu.size(); i2++) {
            this.presenter.setUpdateSuspended(true);
            MenuItem checkable = this.menu.getItem(i2).setCheckable(true);
            this.presenter.setUpdateSuspended(false);
            BottomNavigationItemView child = getNewItem();
            this.buttons[i2] = child;
            child.setIconTintList(this.itemIconTint);
            child.setIconSize(this.itemIconSize);
            child.setTextColor(this.itemTextColorDefault);
            child.setTextAppearanceInactive(this.itemTextAppearanceInactive);
            child.setTextAppearanceActive(this.itemTextAppearanceActive);
            child.setTextColor(this.itemTextColorFromUser);
            if (this.itemBackground != null) {
                child.setItemBackground(this.itemBackground);
            } else {
                child.setItemBackground(this.itemBackgroundRes);
            }
            child.setShifting(shifting);
            child.setLabelVisibilityMode(this.labelVisibilityMode);
            child.initialize((MenuItemImpl) this.menu.getItem(i2), 0);
            child.setItemPosition(i2);
            child.setOnClickListener(this.onClickListener);
            addView(child);
        }
        this.selectedItemPosition = Math.min(this.menu.size() - 1, this.selectedItemPosition);
        MenuItem checked = this.menu.getItem(this.selectedItemPosition).setChecked(true);
    }

    public void updateMenuView() {
        if (this.menu != null && this.buttons != null) {
            int menuSize = this.menu.size();
            if (menuSize != this.buttons.length) {
                buildMenuView();
                return;
            }
            int previousSelectedId = this.selectedItemId;
            for (int i = 0; i < menuSize; i++) {
                MenuItem item = this.menu.getItem(i);
                if (item.isChecked()) {
                    this.selectedItemId = item.getItemId();
                    this.selectedItemPosition = i;
                }
            }
            if (previousSelectedId != this.selectedItemId) {
                TransitionManager.beginDelayedTransition(this, this.set);
            }
            boolean shifting = isShifting(this.labelVisibilityMode, this.menu.getVisibleItems().size());
            for (int i2 = 0; i2 < menuSize; i2++) {
                this.presenter.setUpdateSuspended(true);
                this.buttons[i2].setLabelVisibilityMode(this.labelVisibilityMode);
                this.buttons[i2].setShifting(shifting);
                this.buttons[i2].initialize((MenuItemImpl) this.menu.getItem(i2), 0);
                this.presenter.setUpdateSuspended(false);
            }
        }
    }

    private BottomNavigationItemView getNewItem() {
        BottomNavigationItemView bottomNavigationItemView;
        BottomNavigationItemView item = this.itemPool.acquire();
        if (item == null) {
            new BottomNavigationItemView(getContext());
            item = bottomNavigationItemView;
        }
        return item;
    }

    public int getSelectedItemId() {
        return this.selectedItemId;
    }

    private boolean isShifting(int i, int childCount) {
        int labelVisibilityMode2 = i;
        return labelVisibilityMode2 == -1 ? childCount > 3 : labelVisibilityMode2 == 0;
    }

    /* access modifiers changed from: package-private */
    public void tryRestoreSelectedItemId(int i) {
        int itemId = i;
        int size = this.menu.size();
        for (int i2 = 0; i2 < size; i2++) {
            MenuItem item = this.menu.getItem(i2);
            if (itemId == item.getItemId()) {
                this.selectedItemId = itemId;
                this.selectedItemPosition = i2;
                MenuItem checked = item.setChecked(true);
                return;
            }
        }
    }
}

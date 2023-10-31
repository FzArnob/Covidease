package android.support.design.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DimenRes;
import android.support.annotation.Dimension;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.design.C0064R;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.internal.BottomNavigationPresenter;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.view.AbsSavedState;
import android.support.p003v7.view.SupportMenuInflater;
import android.support.p003v7.view.menu.MenuBuilder;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class BottomNavigationView extends FrameLayout {
    private static final int MENU_PRESENTER_ID = 1;
    private final MenuBuilder menu;
    private MenuInflater menuInflater;
    private final BottomNavigationMenuView menuView;
    private final BottomNavigationPresenter presenter;
    /* access modifiers changed from: private */
    public OnNavigationItemReselectedListener reselectedListener;
    /* access modifiers changed from: private */
    public OnNavigationItemSelectedListener selectedListener;

    public interface OnNavigationItemReselectedListener {
        void onNavigationItemReselected(@NonNull MenuItem menuItem);
    }

    public interface OnNavigationItemSelectedListener {
        boolean onNavigationItemSelected(@NonNull MenuItem menuItem);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public BottomNavigationView(Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public BottomNavigationView(Context context, AttributeSet attrs) {
        this(context, attrs, C0064R.attr.bottomNavigationStyle);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public BottomNavigationView(android.content.Context r18, android.util.AttributeSet r19, int r20) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r20
            r7 = r0
            r8 = r1
            r9 = r2
            r10 = r3
            r7.<init>(r8, r9, r10)
            r7 = r0
            android.support.design.internal.BottomNavigationPresenter r8 = new android.support.design.internal.BottomNavigationPresenter
            r16 = r8
            r8 = r16
            r9 = r16
            r9.<init>()
            r7.presenter = r8
            r7 = r0
            android.support.design.internal.BottomNavigationMenu r8 = new android.support.design.internal.BottomNavigationMenu
            r16 = r8
            r8 = r16
            r9 = r16
            r10 = r1
            r9.<init>(r10)
            r7.menu = r8
            r7 = r0
            android.support.design.internal.BottomNavigationMenuView r8 = new android.support.design.internal.BottomNavigationMenuView
            r16 = r8
            r8 = r16
            r9 = r16
            r10 = r1
            r9.<init>(r10)
            r7.menuView = r8
            android.widget.FrameLayout$LayoutParams r7 = new android.widget.FrameLayout$LayoutParams
            r16 = r7
            r7 = r16
            r8 = r16
            r9 = -2
            r10 = -2
            r8.<init>(r9, r10)
            r4 = r7
            r7 = r4
            r8 = 17
            r7.gravity = r8
            r7 = r0
            android.support.design.internal.BottomNavigationMenuView r7 = r7.menuView
            r8 = r4
            r7.setLayoutParams(r8)
            r7 = r0
            android.support.design.internal.BottomNavigationPresenter r7 = r7.presenter
            r8 = r0
            android.support.design.internal.BottomNavigationMenuView r8 = r8.menuView
            r7.setBottomNavigationMenuView(r8)
            r7 = r0
            android.support.design.internal.BottomNavigationPresenter r7 = r7.presenter
            r8 = 1
            r7.setId(r8)
            r7 = r0
            android.support.design.internal.BottomNavigationMenuView r7 = r7.menuView
            r8 = r0
            android.support.design.internal.BottomNavigationPresenter r8 = r8.presenter
            r7.setPresenter(r8)
            r7 = r0
            android.support.v7.view.menu.MenuBuilder r7 = r7.menu
            r8 = r0
            android.support.design.internal.BottomNavigationPresenter r8 = r8.presenter
            r7.addMenuPresenter(r8)
            r7 = r0
            android.support.design.internal.BottomNavigationPresenter r7 = r7.presenter
            r8 = r0
            android.content.Context r8 = r8.getContext()
            r9 = r0
            android.support.v7.view.menu.MenuBuilder r9 = r9.menu
            r7.initForMenu(r8, r9)
            r7 = r1
            r8 = r2
            int[] r9 = android.support.design.C0064R.styleable.BottomNavigationView
            r10 = r3
            int r11 = android.support.design.C0064R.C0068style.Widget_Design_BottomNavigationView
            r12 = 2
            int[] r12 = new int[r12]
            r16 = r12
            r12 = r16
            r13 = r16
            r14 = 0
            int r15 = android.support.design.C0064R.styleable.BottomNavigationView_itemTextAppearanceInactive
            r13[r14] = r15
            r16 = r12
            r12 = r16
            r13 = r16
            r14 = 1
            int r15 = android.support.design.C0064R.styleable.BottomNavigationView_itemTextAppearanceActive
            r13[r14] = r15
            android.support.v7.widget.TintTypedArray r7 = android.support.design.internal.ThemeEnforcement.obtainTintedStyledAttributes(r7, r8, r9, r10, r11, r12)
            r5 = r7
            r7 = r5
            int r8 = android.support.design.C0064R.styleable.BottomNavigationView_itemIconTint
            boolean r7 = r7.hasValue(r8)
            if (r7 == 0) goto L_0x0191
            r7 = r0
            android.support.design.internal.BottomNavigationMenuView r7 = r7.menuView
            r8 = r5
            int r9 = android.support.design.C0064R.styleable.BottomNavigationView_itemIconTint
            android.content.res.ColorStateList r8 = r8.getColorStateList(r9)
            r7.setIconTintList(r8)
        L_0x00c0:
            r7 = r0
            r8 = r5
            int r9 = android.support.design.C0064R.styleable.BottomNavigationView_itemIconSize
            r10 = r0
            android.content.res.Resources r10 = r10.getResources()
            int r11 = android.support.design.C0064R.dimen.design_bottom_navigation_icon_size
            int r10 = r10.getDimensionPixelSize(r11)
            int r8 = r8.getDimensionPixelSize(r9, r10)
            r7.setItemIconSize(r8)
            r7 = r5
            int r8 = android.support.design.C0064R.styleable.BottomNavigationView_itemTextAppearanceInactive
            boolean r7 = r7.hasValue(r8)
            if (r7 == 0) goto L_0x00eb
            r7 = r0
            r8 = r5
            int r9 = android.support.design.C0064R.styleable.BottomNavigationView_itemTextAppearanceInactive
            r10 = 0
            int r8 = r8.getResourceId(r9, r10)
            r7.setItemTextAppearanceInactive(r8)
        L_0x00eb:
            r7 = r5
            int r8 = android.support.design.C0064R.styleable.BottomNavigationView_itemTextAppearanceActive
            boolean r7 = r7.hasValue(r8)
            if (r7 == 0) goto L_0x0100
            r7 = r0
            r8 = r5
            int r9 = android.support.design.C0064R.styleable.BottomNavigationView_itemTextAppearanceActive
            r10 = 0
            int r8 = r8.getResourceId(r9, r10)
            r7.setItemTextAppearanceActive(r8)
        L_0x0100:
            r7 = r5
            int r8 = android.support.design.C0064R.styleable.BottomNavigationView_itemTextColor
            boolean r7 = r7.hasValue(r8)
            if (r7 == 0) goto L_0x0114
            r7 = r0
            r8 = r5
            int r9 = android.support.design.C0064R.styleable.BottomNavigationView_itemTextColor
            android.content.res.ColorStateList r8 = r8.getColorStateList(r9)
            r7.setItemTextColor(r8)
        L_0x0114:
            r7 = r5
            int r8 = android.support.design.C0064R.styleable.BottomNavigationView_elevation
            boolean r7 = r7.hasValue(r8)
            if (r7 == 0) goto L_0x012a
            r7 = r0
            r8 = r5
            int r9 = android.support.design.C0064R.styleable.BottomNavigationView_elevation
            r10 = 0
            int r8 = r8.getDimensionPixelSize(r9, r10)
            float r8 = (float) r8
            android.support.p000v4.view.ViewCompat.setElevation(r7, r8)
        L_0x012a:
            r7 = r0
            r8 = r5
            int r9 = android.support.design.C0064R.styleable.BottomNavigationView_labelVisibilityMode
            r10 = -1
            int r8 = r8.getInteger(r9, r10)
            r7.setLabelVisibilityMode(r8)
            r7 = r0
            r8 = r5
            int r9 = android.support.design.C0064R.styleable.BottomNavigationView_itemHorizontalTranslationEnabled
            r10 = 1
            boolean r8 = r8.getBoolean(r9, r10)
            r7.setItemHorizontalTranslationEnabled(r8)
            r7 = r5
            int r8 = android.support.design.C0064R.styleable.BottomNavigationView_itemBackground
            r9 = 0
            int r7 = r7.getResourceId(r8, r9)
            r6 = r7
            r7 = r0
            android.support.design.internal.BottomNavigationMenuView r7 = r7.menuView
            r8 = r6
            r7.setItemBackgroundRes(r8)
            r7 = r5
            int r8 = android.support.design.C0064R.styleable.BottomNavigationView_menu
            boolean r7 = r7.hasValue(r8)
            if (r7 == 0) goto L_0x0167
            r7 = r0
            r8 = r5
            int r9 = android.support.design.C0064R.styleable.BottomNavigationView_menu
            r10 = 0
            int r8 = r8.getResourceId(r9, r10)
            r7.inflateMenu(r8)
        L_0x0167:
            r7 = r5
            r7.recycle()
            r7 = r0
            r8 = r0
            android.support.design.internal.BottomNavigationMenuView r8 = r8.menuView
            r9 = r4
            r7.addView(r8, r9)
            int r7 = android.os.Build.VERSION.SDK_INT
            r8 = 21
            if (r7 >= r8) goto L_0x017e
            r7 = r0
            r8 = r1
            r7.addCompatibilityTopDivider(r8)
        L_0x017e:
            r7 = r0
            android.support.v7.view.menu.MenuBuilder r7 = r7.menu
            android.support.design.widget.BottomNavigationView$1 r8 = new android.support.design.widget.BottomNavigationView$1
            r16 = r8
            r8 = r16
            r9 = r16
            r10 = r0
            r9.<init>(r10)
            r7.setCallback(r8)
            return
        L_0x0191:
            r7 = r0
            android.support.design.internal.BottomNavigationMenuView r7 = r7.menuView
            r8 = r0
            android.support.design.internal.BottomNavigationMenuView r8 = r8.menuView
            r9 = 16842808(0x1010038, float:2.3693715E-38)
            android.content.res.ColorStateList r8 = r8.createDefaultColorStateList(r9)
            r7.setIconTintList(r8)
            goto L_0x00c0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.BottomNavigationView.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public void setOnNavigationItemSelectedListener(@Nullable OnNavigationItemSelectedListener listener) {
        OnNavigationItemSelectedListener onNavigationItemSelectedListener = listener;
        this.selectedListener = onNavigationItemSelectedListener;
    }

    public void setOnNavigationItemReselectedListener(@Nullable OnNavigationItemReselectedListener listener) {
        OnNavigationItemReselectedListener onNavigationItemReselectedListener = listener;
        this.reselectedListener = onNavigationItemReselectedListener;
    }

    @NonNull
    public Menu getMenu() {
        return this.menu;
    }

    public void inflateMenu(int resId) {
        this.presenter.setUpdateSuspended(true);
        getMenuInflater().inflate(resId, this.menu);
        this.presenter.setUpdateSuspended(false);
        this.presenter.updateMenuView(true);
    }

    public int getMaxItemCount() {
        return 5;
    }

    @Nullable
    public ColorStateList getItemIconTintList() {
        return this.menuView.getIconTintList();
    }

    public void setItemIconTintList(@Nullable ColorStateList tint) {
        this.menuView.setIconTintList(tint);
    }

    public void setItemIconSize(@Dimension int iconSize) {
        this.menuView.setItemIconSize(iconSize);
    }

    public void setItemIconSizeRes(@DimenRes int iconSizeRes) {
        setItemIconSize(getResources().getDimensionPixelSize(iconSizeRes));
    }

    @Dimension
    public int getItemIconSize() {
        return this.menuView.getItemIconSize();
    }

    @Nullable
    public ColorStateList getItemTextColor() {
        return this.menuView.getItemTextColor();
    }

    public void setItemTextColor(@Nullable ColorStateList textColor) {
        this.menuView.setItemTextColor(textColor);
    }

    @Deprecated
    @DrawableRes
    public int getItemBackgroundResource() {
        return this.menuView.getItemBackgroundRes();
    }

    public void setItemBackgroundResource(@DrawableRes int resId) {
        this.menuView.setItemBackgroundRes(resId);
    }

    @Nullable
    public Drawable getItemBackground() {
        return this.menuView.getItemBackground();
    }

    public void setItemBackground(@Nullable Drawable background) {
        this.menuView.setItemBackground(background);
    }

    @IdRes
    public int getSelectedItemId() {
        return this.menuView.getSelectedItemId();
    }

    public void setSelectedItemId(@IdRes int itemId) {
        MenuItem item = this.menu.findItem(itemId);
        if (item != null && !this.menu.performItemAction(item, this.presenter, 0)) {
            MenuItem checked = item.setChecked(true);
        }
    }

    public void setLabelVisibilityMode(int i) {
        int labelVisibilityMode = i;
        if (this.menuView.getLabelVisibilityMode() != labelVisibilityMode) {
            this.menuView.setLabelVisibilityMode(labelVisibilityMode);
            this.presenter.updateMenuView(false);
        }
    }

    public int getLabelVisibilityMode() {
        return this.menuView.getLabelVisibilityMode();
    }

    public void setItemTextAppearanceInactive(@StyleRes int textAppearanceRes) {
        this.menuView.setItemTextAppearanceInactive(textAppearanceRes);
    }

    @StyleRes
    public int getItemTextAppearanceInactive() {
        return this.menuView.getItemTextAppearanceInactive();
    }

    public void setItemTextAppearanceActive(@StyleRes int textAppearanceRes) {
        this.menuView.setItemTextAppearanceActive(textAppearanceRes);
    }

    @StyleRes
    public int getItemTextAppearanceActive() {
        return this.menuView.getItemTextAppearanceActive();
    }

    public void setItemHorizontalTranslationEnabled(boolean z) {
        boolean itemHorizontalTranslationEnabled = z;
        if (this.menuView.isItemHorizontalTranslationEnabled() != itemHorizontalTranslationEnabled) {
            this.menuView.setItemHorizontalTranslationEnabled(itemHorizontalTranslationEnabled);
            this.presenter.updateMenuView(false);
        }
    }

    public boolean isItemHorizontalTranslationEnabled() {
        return this.menuView.isItemHorizontalTranslationEnabled();
    }

    private void addCompatibilityTopDivider(Context context) {
        View view;
        ViewGroup.LayoutParams layoutParams;
        Context context2 = context;
        new View(context2);
        View divider = view;
        divider.setBackgroundColor(ContextCompat.getColor(context2, C0064R.color.design_bottom_navigation_shadow_color));
        new FrameLayout.LayoutParams(-1, getResources().getDimensionPixelSize(C0064R.dimen.design_bottom_navigation_shadow_height));
        divider.setLayoutParams(layoutParams);
        addView(divider);
    }

    private MenuInflater getMenuInflater() {
        MenuInflater menuInflater2;
        if (this.menuInflater == null) {
            new SupportMenuInflater(getContext());
            this.menuInflater = menuInflater2;
        }
        return this.menuInflater;
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        SavedState savedState;
        Bundle bundle;
        new SavedState(super.onSaveInstanceState());
        SavedState savedState2 = savedState;
        new Bundle();
        savedState2.menuPresenterState = bundle;
        this.menu.savePresenterStates(savedState2.menuPresenterState);
        return savedState2;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable state = parcelable;
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        SavedState savedState = (SavedState) state;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.menu.restorePresenterStates(savedState.menuPresenterState);
    }

    static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR;
        Bundle menuPresenterState;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SavedState(Parcelable superState) {
            super(superState);
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
                r5 = r2
                r3.readFromParcel(r4, r5)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.BottomNavigationView.SavedState.<init>(android.os.Parcel, java.lang.ClassLoader):void");
        }

        public void writeToParcel(@NonNull Parcel parcel, int flags) {
            Parcel out = parcel;
            super.writeToParcel(out, flags);
            out.writeBundle(this.menuPresenterState);
        }

        private void readFromParcel(Parcel in, ClassLoader loader) {
            Bundle readBundle = in.readBundle(loader);
            this.menuPresenterState = readBundle;
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
}

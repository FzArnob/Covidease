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
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleRes;
import android.support.design.C0064R;
import android.support.design.internal.NavigationMenu;
import android.support.design.internal.NavigationMenuPresenter;
import android.support.design.internal.ScrimInsetsFrameLayout;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.view.AbsSavedState;
import android.support.p000v4.view.WindowInsetsCompat;
import android.support.p003v7.appcompat.C0425R;
import android.support.p003v7.content.res.AppCompatResources;
import android.support.p003v7.view.SupportMenuInflater;
import android.support.p003v7.view.menu.MenuItemImpl;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import gnu.expr.Declaration;

public class NavigationView extends ScrimInsetsFrameLayout {
    private static final int[] CHECKED_STATE_SET = {16842912};
    private static final int[] DISABLED_STATE_SET = {-16842910};
    private static final int PRESENTER_NAVIGATION_VIEW_ID = 1;
    OnNavigationItemSelectedListener listener;
    private final int maxWidth;
    private final NavigationMenu menu;
    private MenuInflater menuInflater;
    private final NavigationMenuPresenter presenter;

    public interface OnNavigationItemSelectedListener {
        boolean onNavigationItemSelected(@NonNull MenuItem menuItem);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public NavigationView(Context context) {
        this(context, (AttributeSet) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public NavigationView(Context context, AttributeSet attrs) {
        this(context, attrs, C0064R.attr.navigationViewStyle);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public NavigationView(android.content.Context r20, android.util.AttributeSet r21, int r22) {
        /*
            r19 = this;
            r1 = r19
            r2 = r20
            r3 = r21
            r4 = r22
            r12 = r1
            r13 = r2
            r14 = r3
            r15 = r4
            r12.<init>(r13, r14, r15)
            r12 = r1
            android.support.design.internal.NavigationMenuPresenter r13 = new android.support.design.internal.NavigationMenuPresenter
            r18 = r13
            r13 = r18
            r14 = r18
            r14.<init>()
            r12.presenter = r13
            r12 = r1
            android.support.design.internal.NavigationMenu r13 = new android.support.design.internal.NavigationMenu
            r18 = r13
            r13 = r18
            r14 = r18
            r15 = r2
            r14.<init>(r15)
            r12.menu = r13
            r12 = r2
            r13 = r3
            int[] r14 = android.support.design.C0064R.styleable.NavigationView
            r15 = r4
            int r16 = android.support.design.C0064R.C0068style.Widget_Design_NavigationView
            r17 = 0
            r0 = r17
            int[] r0 = new int[r0]
            r17 = r0
            android.support.v7.widget.TintTypedArray r12 = android.support.design.internal.ThemeEnforcement.obtainTintedStyledAttributes(r12, r13, r14, r15, r16, r17)
            r5 = r12
            r12 = r1
            r13 = r5
            int r14 = android.support.design.C0064R.styleable.NavigationView_android_background
            android.graphics.drawable.Drawable r13 = r13.getDrawable(r14)
            android.support.p000v4.view.ViewCompat.setBackground(r12, r13)
            r12 = r5
            int r13 = android.support.design.C0064R.styleable.NavigationView_elevation
            boolean r12 = r12.hasValue(r13)
            if (r12 == 0) goto L_0x0061
            r12 = r1
            r13 = r5
            int r14 = android.support.design.C0064R.styleable.NavigationView_elevation
            r15 = 0
            int r13 = r13.getDimensionPixelSize(r14, r15)
            float r13 = (float) r13
            android.support.p000v4.view.ViewCompat.setElevation(r12, r13)
        L_0x0061:
            r12 = r1
            r13 = r5
            int r14 = android.support.design.C0064R.styleable.NavigationView_android_fitsSystemWindows
            r15 = 0
            boolean r13 = r13.getBoolean(r14, r15)
            android.support.p000v4.view.ViewCompat.setFitsSystemWindows(r12, r13)
            r12 = r1
            r13 = r5
            int r14 = android.support.design.C0064R.styleable.NavigationView_android_maxWidth
            r15 = 0
            int r13 = r13.getDimensionPixelSize(r14, r15)
            r12.maxWidth = r13
            r12 = r5
            int r13 = android.support.design.C0064R.styleable.NavigationView_itemIconTint
            boolean r12 = r12.hasValue(r13)
            if (r12 == 0) goto L_0x017d
            r12 = r5
            int r13 = android.support.design.C0064R.styleable.NavigationView_itemIconTint
            android.content.res.ColorStateList r12 = r12.getColorStateList(r13)
            r6 = r12
        L_0x0089:
            r12 = 0
            r7 = r12
            r12 = 0
            r8 = r12
            r12 = r5
            int r13 = android.support.design.C0064R.styleable.NavigationView_itemTextAppearance
            boolean r12 = r12.hasValue(r13)
            if (r12 == 0) goto L_0x00a1
            r12 = r5
            int r13 = android.support.design.C0064R.styleable.NavigationView_itemTextAppearance
            r14 = 0
            int r12 = r12.getResourceId(r13, r14)
            r8 = r12
            r12 = 1
            r7 = r12
        L_0x00a1:
            r12 = 0
            r9 = r12
            r12 = r5
            int r13 = android.support.design.C0064R.styleable.NavigationView_itemTextColor
            boolean r12 = r12.hasValue(r13)
            if (r12 == 0) goto L_0x00b4
            r12 = r5
            int r13 = android.support.design.C0064R.styleable.NavigationView_itemTextColor
            android.content.res.ColorStateList r12 = r12.getColorStateList(r13)
            r9 = r12
        L_0x00b4:
            r12 = r7
            if (r12 != 0) goto L_0x00c3
            r12 = r9
            if (r12 != 0) goto L_0x00c3
            r12 = r1
            r13 = 16842806(0x1010036, float:2.369371E-38)
            android.content.res.ColorStateList r12 = r12.createDefaultColorStateList(r13)
            r9 = r12
        L_0x00c3:
            r12 = r5
            int r13 = android.support.design.C0064R.styleable.NavigationView_itemBackground
            android.graphics.drawable.Drawable r12 = r12.getDrawable(r13)
            r10 = r12
            r12 = r5
            int r13 = android.support.design.C0064R.styleable.NavigationView_itemHorizontalPadding
            boolean r12 = r12.hasValue(r13)
            if (r12 == 0) goto L_0x00e4
            r12 = r5
            int r13 = android.support.design.C0064R.styleable.NavigationView_itemHorizontalPadding
            r14 = 0
            int r12 = r12.getDimensionPixelSize(r13, r14)
            r11 = r12
            r12 = r1
            android.support.design.internal.NavigationMenuPresenter r12 = r12.presenter
            r13 = r11
            r12.setItemHorizontalPadding(r13)
        L_0x00e4:
            r12 = r5
            int r13 = android.support.design.C0064R.styleable.NavigationView_itemIconPadding
            r14 = 0
            int r12 = r12.getDimensionPixelSize(r13, r14)
            r11 = r12
            r12 = r1
            android.support.design.internal.NavigationMenu r12 = r12.menu
            android.support.design.widget.NavigationView$1 r13 = new android.support.design.widget.NavigationView$1
            r18 = r13
            r13 = r18
            r14 = r18
            r15 = r1
            r14.<init>(r15)
            r12.setCallback(r13)
            r12 = r1
            android.support.design.internal.NavigationMenuPresenter r12 = r12.presenter
            r13 = 1
            r12.setId(r13)
            r12 = r1
            android.support.design.internal.NavigationMenuPresenter r12 = r12.presenter
            r13 = r2
            r14 = r1
            android.support.design.internal.NavigationMenu r14 = r14.menu
            r12.initForMenu(r13, r14)
            r12 = r1
            android.support.design.internal.NavigationMenuPresenter r12 = r12.presenter
            r13 = r6
            r12.setItemIconTintList(r13)
            r12 = r7
            if (r12 == 0) goto L_0x0121
            r12 = r1
            android.support.design.internal.NavigationMenuPresenter r12 = r12.presenter
            r13 = r8
            r12.setItemTextAppearance(r13)
        L_0x0121:
            r12 = r1
            android.support.design.internal.NavigationMenuPresenter r12 = r12.presenter
            r13 = r9
            r12.setItemTextColor(r13)
            r12 = r1
            android.support.design.internal.NavigationMenuPresenter r12 = r12.presenter
            r13 = r10
            r12.setItemBackground(r13)
            r12 = r1
            android.support.design.internal.NavigationMenuPresenter r12 = r12.presenter
            r13 = r11
            r12.setItemIconPadding(r13)
            r12 = r1
            android.support.design.internal.NavigationMenu r12 = r12.menu
            r13 = r1
            android.support.design.internal.NavigationMenuPresenter r13 = r13.presenter
            r12.addMenuPresenter(r13)
            r12 = r1
            r13 = r1
            android.support.design.internal.NavigationMenuPresenter r13 = r13.presenter
            r14 = r1
            android.support.v7.view.menu.MenuView r13 = r13.getMenuView(r14)
            android.view.View r13 = (android.view.View) r13
            r12.addView(r13)
            r12 = r5
            int r13 = android.support.design.C0064R.styleable.NavigationView_menu
            boolean r12 = r12.hasValue(r13)
            if (r12 == 0) goto L_0x0162
            r12 = r1
            r13 = r5
            int r14 = android.support.design.C0064R.styleable.NavigationView_menu
            r15 = 0
            int r13 = r13.getResourceId(r14, r15)
            r12.inflateMenu(r13)
        L_0x0162:
            r12 = r5
            int r13 = android.support.design.C0064R.styleable.NavigationView_headerLayout
            boolean r12 = r12.hasValue(r13)
            if (r12 == 0) goto L_0x0178
            r12 = r1
            r13 = r5
            int r14 = android.support.design.C0064R.styleable.NavigationView_headerLayout
            r15 = 0
            int r13 = r13.getResourceId(r14, r15)
            android.view.View r12 = r12.inflateHeaderView(r13)
        L_0x0178:
            r12 = r5
            r12.recycle()
            return
        L_0x017d:
            r12 = r1
            r13 = 16842808(0x1010038, float:2.3693715E-38)
            android.content.res.ColorStateList r12 = r12.createDefaultColorStateList(r13)
            r6 = r12
            goto L_0x0089
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.NavigationView.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        SavedState savedState;
        Bundle bundle;
        new SavedState(super.onSaveInstanceState());
        SavedState state = savedState;
        new Bundle();
        state.menuState = bundle;
        this.menu.savePresenterStates(state.menuState);
        return state;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable savedState = parcelable;
        if (!(savedState instanceof SavedState)) {
            super.onRestoreInstanceState(savedState);
            return;
        }
        SavedState state = (SavedState) savedState;
        super.onRestoreInstanceState(state.getSuperState());
        this.menu.restorePresenterStates(state.menuState);
    }

    public void setNavigationItemSelectedListener(@Nullable OnNavigationItemSelectedListener listener2) {
        OnNavigationItemSelectedListener onNavigationItemSelectedListener = listener2;
        this.listener = onNavigationItemSelectedListener;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int widthSpec = i;
        int heightSpec = i2;
        switch (View.MeasureSpec.getMode(widthSpec)) {
            case Integer.MIN_VALUE:
                widthSpec = View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(widthSpec), this.maxWidth), Declaration.MODULE_REFERENCE);
                break;
            case 0:
                widthSpec = View.MeasureSpec.makeMeasureSpec(this.maxWidth, Declaration.MODULE_REFERENCE);
                break;
        }
        super.onMeasure(widthSpec, heightSpec);
    }

    /* access modifiers changed from: protected */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void onInsetsChanged(WindowInsetsCompat insets) {
        this.presenter.dispatchApplyWindowInsets(insets);
    }

    public void inflateMenu(int resId) {
        this.presenter.setUpdateSuspended(true);
        getMenuInflater().inflate(resId, this.menu);
        this.presenter.setUpdateSuspended(false);
        this.presenter.updateMenuView(false);
    }

    public Menu getMenu() {
        return this.menu;
    }

    public View inflateHeaderView(@LayoutRes int res) {
        return this.presenter.inflateHeaderView(res);
    }

    public void addHeaderView(@NonNull View view) {
        this.presenter.addHeaderView(view);
    }

    public void removeHeaderView(@NonNull View view) {
        this.presenter.removeHeaderView(view);
    }

    public int getHeaderCount() {
        return this.presenter.getHeaderCount();
    }

    public View getHeaderView(int index) {
        return this.presenter.getHeaderView(index);
    }

    @Nullable
    public ColorStateList getItemIconTintList() {
        return this.presenter.getItemTintList();
    }

    public void setItemIconTintList(@Nullable ColorStateList tint) {
        this.presenter.setItemIconTintList(tint);
    }

    @Nullable
    public ColorStateList getItemTextColor() {
        return this.presenter.getItemTextColor();
    }

    public void setItemTextColor(@Nullable ColorStateList textColor) {
        this.presenter.setItemTextColor(textColor);
    }

    @Nullable
    public Drawable getItemBackground() {
        return this.presenter.getItemBackground();
    }

    public void setItemBackgroundResource(@DrawableRes int resId) {
        setItemBackground(ContextCompat.getDrawable(getContext(), resId));
    }

    public void setItemBackground(@Nullable Drawable itemBackground) {
        this.presenter.setItemBackground(itemBackground);
    }

    @Dimension
    public int getItemHorizontalPadding() {
        return this.presenter.getItemHorizontalPadding();
    }

    public void setItemHorizontalPadding(@Dimension int padding) {
        this.presenter.setItemHorizontalPadding(padding);
    }

    public void setItemHorizontalPaddingResource(@DimenRes int paddingResource) {
        this.presenter.setItemHorizontalPadding(getResources().getDimensionPixelSize(paddingResource));
    }

    @Dimension
    public int getItemIconPadding() {
        return this.presenter.getItemIconPadding();
    }

    public void setItemIconPadding(@Dimension int padding) {
        this.presenter.setItemIconPadding(padding);
    }

    public void setItemIconPaddingResource(int paddingResource) {
        this.presenter.setItemIconPadding(getResources().getDimensionPixelSize(paddingResource));
    }

    public void setCheckedItem(@IdRes int id) {
        MenuItem item = this.menu.findItem(id);
        if (item != null) {
            this.presenter.setCheckedItem((MenuItemImpl) item);
        }
    }

    public void setCheckedItem(@NonNull MenuItem checkedItem) {
        Throwable th;
        MenuItem item = this.menu.findItem(checkedItem.getItemId());
        if (item != null) {
            this.presenter.setCheckedItem((MenuItemImpl) item);
            return;
        }
        Throwable th2 = th;
        new IllegalArgumentException("Called setCheckedItem(MenuItem) with an item that is not in the current menu.");
        throw th2;
    }

    @Nullable
    public MenuItem getCheckedItem() {
        return this.presenter.getCheckedItem();
    }

    public void setItemTextAppearance(@StyleRes int resId) {
        this.presenter.setItemTextAppearance(resId);
    }

    private MenuInflater getMenuInflater() {
        MenuInflater menuInflater2;
        if (this.menuInflater == null) {
            new SupportMenuInflater(getContext());
            this.menuInflater = menuInflater2;
        }
        return this.menuInflater;
    }

    private ColorStateList createDefaultColorStateList(int baseColorThemeAttr) {
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

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR;
        public Bundle menuState;

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
                android.os.Bundle r4 = r4.readBundle(r5)
                r3.menuState = r4
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.NavigationView.SavedState.<init>(android.os.Parcel, java.lang.ClassLoader):void");
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SavedState(Parcelable superState) {
            super(superState);
        }

        public void writeToParcel(@NonNull Parcel parcel, int flags) {
            Parcel dest = parcel;
            super.writeToParcel(dest, flags);
            dest.writeBundle(this.menuState);
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

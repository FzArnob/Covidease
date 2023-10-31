package android.support.p003v7.app;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StringRes;
import android.support.p000v4.app.FragmentTransaction;
import android.support.p003v7.view.ActionMode;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* renamed from: android.support.v7.app.ActionBar */
public abstract class ActionBar {
    public static final int DISPLAY_HOME_AS_UP = 4;
    public static final int DISPLAY_SHOW_CUSTOM = 16;
    public static final int DISPLAY_SHOW_HOME = 2;
    public static final int DISPLAY_SHOW_TITLE = 8;
    public static final int DISPLAY_USE_LOGO = 1;
    @Deprecated
    public static final int NAVIGATION_MODE_LIST = 1;
    @Deprecated
    public static final int NAVIGATION_MODE_STANDARD = 0;
    @Deprecated
    public static final int NAVIGATION_MODE_TABS = 2;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: android.support.v7.app.ActionBar$DisplayOptions */
    public @interface DisplayOptions {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: android.support.v7.app.ActionBar$NavigationMode */
    public @interface NavigationMode {
    }

    /* renamed from: android.support.v7.app.ActionBar$OnMenuVisibilityListener */
    public interface OnMenuVisibilityListener {
        void onMenuVisibilityChanged(boolean z);
    }

    @Deprecated
    /* renamed from: android.support.v7.app.ActionBar$OnNavigationListener */
    public interface OnNavigationListener {
        boolean onNavigationItemSelected(int i, long j);
    }

    @Deprecated
    /* renamed from: android.support.v7.app.ActionBar$TabListener */
    public interface TabListener {
        void onTabReselected(Tab tab, FragmentTransaction fragmentTransaction);

        void onTabSelected(Tab tab, FragmentTransaction fragmentTransaction);

        void onTabUnselected(Tab tab, FragmentTransaction fragmentTransaction);
    }

    public abstract void addOnMenuVisibilityListener(OnMenuVisibilityListener onMenuVisibilityListener);

    @Deprecated
    public abstract void addTab(Tab tab);

    @Deprecated
    public abstract void addTab(Tab tab, int i);

    @Deprecated
    public abstract void addTab(Tab tab, int i, boolean z);

    @Deprecated
    public abstract void addTab(Tab tab, boolean z);

    public abstract View getCustomView();

    public abstract int getDisplayOptions();

    public abstract int getHeight();

    @Deprecated
    public abstract int getNavigationItemCount();

    @Deprecated
    public abstract int getNavigationMode();

    @Deprecated
    public abstract int getSelectedNavigationIndex();

    @Nullable
    @Deprecated
    public abstract Tab getSelectedTab();

    @Nullable
    public abstract CharSequence getSubtitle();

    @Deprecated
    public abstract Tab getTabAt(int i);

    @Deprecated
    public abstract int getTabCount();

    @Nullable
    public abstract CharSequence getTitle();

    public abstract void hide();

    public abstract boolean isShowing();

    @Deprecated
    public abstract Tab newTab();

    @Deprecated
    public abstract void removeAllTabs();

    public abstract void removeOnMenuVisibilityListener(OnMenuVisibilityListener onMenuVisibilityListener);

    @Deprecated
    public abstract void removeTab(Tab tab);

    @Deprecated
    public abstract void removeTabAt(int i);

    @Deprecated
    public abstract void selectTab(Tab tab);

    public abstract void setBackgroundDrawable(@Nullable Drawable drawable);

    public abstract void setCustomView(int i);

    public abstract void setCustomView(View view);

    public abstract void setCustomView(View view, LayoutParams layoutParams);

    public abstract void setDisplayHomeAsUpEnabled(boolean z);

    public abstract void setDisplayOptions(int i);

    public abstract void setDisplayOptions(int i, int i2);

    public abstract void setDisplayShowCustomEnabled(boolean z);

    public abstract void setDisplayShowHomeEnabled(boolean z);

    public abstract void setDisplayShowTitleEnabled(boolean z);

    public abstract void setDisplayUseLogoEnabled(boolean z);

    public abstract void setIcon(@DrawableRes int i);

    public abstract void setIcon(Drawable drawable);

    @Deprecated
    public abstract void setListNavigationCallbacks(SpinnerAdapter spinnerAdapter, OnNavigationListener onNavigationListener);

    public abstract void setLogo(@DrawableRes int i);

    public abstract void setLogo(Drawable drawable);

    @Deprecated
    public abstract void setNavigationMode(int i);

    @Deprecated
    public abstract void setSelectedNavigationItem(int i);

    public abstract void setSubtitle(int i);

    public abstract void setSubtitle(CharSequence charSequence);

    public abstract void setTitle(@StringRes int i);

    public abstract void setTitle(CharSequence charSequence);

    public abstract void show();

    public ActionBar() {
    }

    public void setStackedBackgroundDrawable(Drawable d) {
    }

    public void setSplitBackgroundDrawable(Drawable d) {
    }

    public void setHomeButtonEnabled(boolean enabled) {
    }

    public Context getThemedContext() {
        return null;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean isTitleTruncated() {
        return false;
    }

    public void setHomeAsUpIndicator(@Nullable Drawable indicator) {
    }

    public void setHomeAsUpIndicator(@DrawableRes int resId) {
    }

    public void setHomeActionContentDescription(@Nullable CharSequence description) {
    }

    public void setHomeActionContentDescription(@StringRes int resId) {
    }

    public void setHideOnContentScrollEnabled(boolean hideOnContentScroll) {
        Throwable th;
        if (hideOnContentScroll) {
            Throwable th2 = th;
            new UnsupportedOperationException("Hide on content scroll is not supported in this action bar configuration.");
            throw th2;
        }
    }

    public boolean isHideOnContentScrollEnabled() {
        return false;
    }

    public int getHideOffset() {
        return 0;
    }

    public void setHideOffset(int offset) {
        Throwable th;
        if (offset != 0) {
            Throwable th2 = th;
            new UnsupportedOperationException("Setting an explicit action bar hide offset is not supported in this action bar configuration.");
            throw th2;
        }
    }

    public void setElevation(float elevation) {
        Throwable th;
        if (elevation != 0.0f) {
            Throwable th2 = th;
            new UnsupportedOperationException("Setting a non-zero elevation is not supported in this action bar configuration.");
            throw th2;
        }
    }

    public float getElevation() {
        return 0.0f;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setDefaultDisplayHomeAsUpEnabled(boolean enabled) {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setShowHideAnimationEnabled(boolean enabled) {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void onConfigurationChanged(Configuration config) {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void dispatchMenuVisibilityChanged(boolean visible) {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public ActionMode startActionMode(ActionMode.Callback callback) {
        ActionMode.Callback callback2 = callback;
        return null;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean openOptionsMenu() {
        return false;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean closeOptionsMenu() {
        return false;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean invalidateOptionsMenu() {
        return false;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean onMenuKeyEvent(KeyEvent keyEvent) {
        KeyEvent keyEvent2 = keyEvent;
        return false;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean onKeyShortcut(int i, KeyEvent keyEvent) {
        int i2 = i;
        KeyEvent keyEvent2 = keyEvent;
        return false;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean collapseActionView() {
        return false;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setWindowTitle(CharSequence title) {
    }

    /* access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean requestFocus() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public void onDestroy() {
    }

    @Deprecated
    /* renamed from: android.support.v7.app.ActionBar$Tab */
    public static abstract class Tab {
        public static final int INVALID_POSITION = -1;

        public abstract CharSequence getContentDescription();

        public abstract View getCustomView();

        public abstract Drawable getIcon();

        public abstract int getPosition();

        public abstract Object getTag();

        public abstract CharSequence getText();

        public abstract void select();

        public abstract Tab setContentDescription(@StringRes int i);

        public abstract Tab setContentDescription(CharSequence charSequence);

        public abstract Tab setCustomView(int i);

        public abstract Tab setCustomView(View view);

        public abstract Tab setIcon(@DrawableRes int i);

        public abstract Tab setIcon(Drawable drawable);

        public abstract Tab setTabListener(TabListener tabListener);

        public abstract Tab setTag(Object obj);

        public abstract Tab setText(int i);

        public abstract Tab setText(CharSequence charSequence);

        public Tab() {
        }
    }

    /* renamed from: android.support.v7.app.ActionBar$LayoutParams */
    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public int gravity;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public LayoutParams(@android.support.annotation.NonNull android.content.Context r9, android.util.AttributeSet r10) {
            /*
                r8 = this;
                r0 = r8
                r1 = r9
                r2 = r10
                r4 = r0
                r5 = r1
                r6 = r2
                r4.<init>(r5, r6)
                r4 = r0
                r5 = 0
                r4.gravity = r5
                r4 = r1
                r5 = r2
                int[] r6 = android.support.p003v7.appcompat.C0425R.styleable.ActionBarLayout
                android.content.res.TypedArray r4 = r4.obtainStyledAttributes(r5, r6)
                r3 = r4
                r4 = r0
                r5 = r3
                int r6 = android.support.p003v7.appcompat.C0425R.styleable.ActionBarLayout_android_layout_gravity
                r7 = 0
                int r5 = r5.getInt(r6, r7)
                r4.gravity = r5
                r4 = r3
                r4.recycle()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.app.ActionBar.LayoutParams.<init>(android.content.Context, android.util.AttributeSet):void");
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(int width, int height) {
            super(width, height);
            this.gravity = 0;
            this.gravity = 8388627;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(int width, int height, int gravity2) {
            super(width, height);
            this.gravity = 0;
            this.gravity = gravity2;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public LayoutParams(int gravity2) {
            this(-2, -1, gravity2);
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public LayoutParams(android.support.p003v7.app.ActionBar.LayoutParams r5) {
            /*
                r4 = this;
                r0 = r4
                r1 = r5
                r2 = r0
                r3 = r1
                r2.<init>(r3)
                r2 = r0
                r3 = 0
                r2.gravity = r3
                r2 = r0
                r3 = r1
                int r3 = r3.gravity
                r2.gravity = r3
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p003v7.app.ActionBar.LayoutParams.<init>(android.support.v7.app.ActionBar$LayoutParams):void");
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
            this.gravity = 0;
        }
    }
}

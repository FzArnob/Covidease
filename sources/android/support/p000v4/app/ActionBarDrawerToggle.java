package android.support.p000v4.app;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.view.GravityCompat;
import android.support.p000v4.view.ViewCompat;
import android.support.p000v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.lang.reflect.Method;

@Deprecated
/* renamed from: android.support.v4.app.ActionBarDrawerToggle */
public class ActionBarDrawerToggle implements DrawerLayout.DrawerListener {
    private static final int ID_HOME = 16908332;
    private static final String TAG = "ActionBarDrawerToggle";
    private static final int[] THEME_ATTRS = {16843531};
    private static final float TOGGLE_DRAWABLE_OFFSET = 0.33333334f;
    final Activity mActivity;
    private final Delegate mActivityImpl;
    private final int mCloseDrawerContentDescRes;
    private Drawable mDrawerImage;
    private final int mDrawerImageResource;
    private boolean mDrawerIndicatorEnabled;
    private final DrawerLayout mDrawerLayout;
    private boolean mHasCustomUpIndicator;
    private Drawable mHomeAsUpIndicator;
    private final int mOpenDrawerContentDescRes;
    private SetIndicatorInfo mSetIndicatorInfo;
    private SlideDrawable mSlider;

    @Deprecated
    /* renamed from: android.support.v4.app.ActionBarDrawerToggle$Delegate */
    public interface Delegate {
        @Nullable
        Drawable getThemeUpIndicator();

        void setActionBarDescription(@StringRes int i);

        void setActionBarUpIndicator(Drawable drawable, @StringRes int i);
    }

    @Deprecated
    /* renamed from: android.support.v4.app.ActionBarDrawerToggle$DelegateProvider */
    public interface DelegateProvider {
        @Nullable
        Delegate getDrawerToggleDelegate();
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ActionBarDrawerToggle(android.app.Activity r14, android.support.p000v4.widget.DrawerLayout r15, @android.support.annotation.DrawableRes int r16, @android.support.annotation.StringRes int r17, @android.support.annotation.StringRes int r18) {
        /*
            r13 = this;
            r0 = r13
            r1 = r14
            r2 = r15
            r3 = r16
            r4 = r17
            r5 = r18
            r6 = r0
            r7 = r1
            r8 = r2
            r9 = r1
            boolean r9 = assumeMaterial(r9)
            if (r9 != 0) goto L_0x001b
            r9 = 1
        L_0x0014:
            r10 = r3
            r11 = r4
            r12 = r5
            r6.<init>(r7, r8, r9, r10, r11, r12)
            return
        L_0x001b:
            r9 = 0
            goto L_0x0014
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.app.ActionBarDrawerToggle.<init>(android.app.Activity, android.support.v4.widget.DrawerLayout, int, int, int):void");
    }

    private static boolean assumeMaterial(Context context) {
        return context.getApplicationInfo().targetSdkVersion >= 21 && Build.VERSION.SDK_INT >= 21;
    }

    public ActionBarDrawerToggle(Activity activity, DrawerLayout drawerLayout, boolean z, @DrawableRes int i, @StringRes int i2, @StringRes int i3) {
        SlideDrawable slideDrawable;
        Activity activity2 = activity;
        DrawerLayout drawerLayout2 = drawerLayout;
        boolean animate = z;
        int drawerImageRes = i;
        int openDrawerContentDescRes = i2;
        int closeDrawerContentDescRes = i3;
        this.mDrawerIndicatorEnabled = true;
        this.mActivity = activity2;
        if (activity2 instanceof DelegateProvider) {
            this.mActivityImpl = ((DelegateProvider) activity2).getDrawerToggleDelegate();
        } else {
            this.mActivityImpl = null;
        }
        this.mDrawerLayout = drawerLayout2;
        this.mDrawerImageResource = drawerImageRes;
        this.mOpenDrawerContentDescRes = openDrawerContentDescRes;
        this.mCloseDrawerContentDescRes = closeDrawerContentDescRes;
        this.mHomeAsUpIndicator = getThemeUpIndicator();
        this.mDrawerImage = ContextCompat.getDrawable(activity2, drawerImageRes);
        new SlideDrawable(this, this.mDrawerImage);
        this.mSlider = slideDrawable;
        this.mSlider.setOffset(animate ? TOGGLE_DRAWABLE_OFFSET : 0.0f);
    }

    public void syncState() {
        if (this.mDrawerLayout.isDrawerOpen((int) GravityCompat.START)) {
            this.mSlider.setPosition(1.0f);
        } else {
            this.mSlider.setPosition(0.0f);
        }
        if (this.mDrawerIndicatorEnabled) {
            setActionBarUpIndicator(this.mSlider, this.mDrawerLayout.isDrawerOpen((int) GravityCompat.START) ? this.mCloseDrawerContentDescRes : this.mOpenDrawerContentDescRes);
        }
    }

    public void setHomeAsUpIndicator(Drawable drawable) {
        Drawable indicator = drawable;
        if (indicator == null) {
            this.mHomeAsUpIndicator = getThemeUpIndicator();
            this.mHasCustomUpIndicator = false;
        } else {
            this.mHomeAsUpIndicator = indicator;
            this.mHasCustomUpIndicator = true;
        }
        if (!this.mDrawerIndicatorEnabled) {
            setActionBarUpIndicator(this.mHomeAsUpIndicator, 0);
        }
    }

    public void setHomeAsUpIndicator(int i) {
        int resId = i;
        Drawable indicator = null;
        if (resId != 0) {
            indicator = ContextCompat.getDrawable(this.mActivity, resId);
        }
        setHomeAsUpIndicator(indicator);
    }

    public void setDrawerIndicatorEnabled(boolean z) {
        boolean enable = z;
        if (enable != this.mDrawerIndicatorEnabled) {
            if (enable) {
                setActionBarUpIndicator(this.mSlider, this.mDrawerLayout.isDrawerOpen((int) GravityCompat.START) ? this.mCloseDrawerContentDescRes : this.mOpenDrawerContentDescRes);
            } else {
                setActionBarUpIndicator(this.mHomeAsUpIndicator, 0);
            }
            this.mDrawerIndicatorEnabled = enable;
        }
    }

    public boolean isDrawerIndicatorEnabled() {
        return this.mDrawerIndicatorEnabled;
    }

    public void onConfigurationChanged(Configuration configuration) {
        Configuration configuration2 = configuration;
        if (!this.mHasCustomUpIndicator) {
            this.mHomeAsUpIndicator = getThemeUpIndicator();
        }
        this.mDrawerImage = ContextCompat.getDrawable(this.mActivity, this.mDrawerImageResource);
        syncState();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        MenuItem item = menuItem;
        if (item == null || item.getItemId() != ID_HOME || !this.mDrawerIndicatorEnabled) {
            return false;
        }
        if (this.mDrawerLayout.isDrawerVisible((int) GravityCompat.START)) {
            this.mDrawerLayout.closeDrawer((int) GravityCompat.START);
        } else {
            this.mDrawerLayout.openDrawer((int) GravityCompat.START);
        }
        return true;
    }

    public void onDrawerSlide(View view, float f) {
        float glyphOffset;
        View view2 = view;
        float slideOffset = f;
        float glyphOffset2 = this.mSlider.getPosition();
        if (slideOffset > 0.5f) {
            glyphOffset = Math.max(glyphOffset2, Math.max(0.0f, slideOffset - 0.5f) * 2.0f);
        } else {
            glyphOffset = Math.min(glyphOffset2, slideOffset * 2.0f);
        }
        this.mSlider.setPosition(glyphOffset);
    }

    public void onDrawerOpened(View view) {
        View view2 = view;
        this.mSlider.setPosition(1.0f);
        if (this.mDrawerIndicatorEnabled) {
            setActionBarDescription(this.mCloseDrawerContentDescRes);
        }
    }

    public void onDrawerClosed(View view) {
        View view2 = view;
        this.mSlider.setPosition(0.0f);
        if (this.mDrawerIndicatorEnabled) {
            setActionBarDescription(this.mOpenDrawerContentDescRes);
        }
    }

    public void onDrawerStateChanged(int newState) {
    }

    private Drawable getThemeUpIndicator() {
        Context context;
        if (this.mActivityImpl != null) {
            return this.mActivityImpl.getThemeUpIndicator();
        }
        if (Build.VERSION.SDK_INT >= 18) {
            ActionBar actionBar = this.mActivity.getActionBar();
            if (actionBar != null) {
                context = actionBar.getThemedContext();
            } else {
                context = this.mActivity;
            }
            TypedArray a = context.obtainStyledAttributes((AttributeSet) null, THEME_ATTRS, 16843470, 0);
            Drawable result = a.getDrawable(0);
            a.recycle();
            return result;
        }
        TypedArray a2 = this.mActivity.obtainStyledAttributes(THEME_ATTRS);
        Drawable result2 = a2.getDrawable(0);
        a2.recycle();
        return result2;
    }

    private void setActionBarUpIndicator(Drawable drawable, int i) {
        SetIndicatorInfo setIndicatorInfo;
        Drawable upDrawable = drawable;
        int contentDescRes = i;
        if (this.mActivityImpl != null) {
            this.mActivityImpl.setActionBarUpIndicator(upDrawable, contentDescRes);
        } else if (Build.VERSION.SDK_INT >= 18) {
            ActionBar actionBar = this.mActivity.getActionBar();
            if (actionBar != null) {
                actionBar.setHomeAsUpIndicator(upDrawable);
                actionBar.setHomeActionContentDescription(contentDescRes);
            }
        } else {
            if (this.mSetIndicatorInfo == null) {
                new SetIndicatorInfo(this.mActivity);
                this.mSetIndicatorInfo = setIndicatorInfo;
            }
            if (this.mSetIndicatorInfo.mSetHomeAsUpIndicator != null) {
                try {
                    ActionBar actionBar2 = this.mActivity.getActionBar();
                    Object invoke = this.mSetIndicatorInfo.mSetHomeAsUpIndicator.invoke(actionBar2, new Object[]{upDrawable});
                    Object invoke2 = this.mSetIndicatorInfo.mSetHomeActionContentDescription.invoke(actionBar2, new Object[]{Integer.valueOf(contentDescRes)});
                } catch (Exception e) {
                    int w = Log.w(TAG, "Couldn't set home-as-up indicator via JB-MR2 API", e);
                }
            } else if (this.mSetIndicatorInfo.mUpIndicatorView != null) {
                this.mSetIndicatorInfo.mUpIndicatorView.setImageDrawable(upDrawable);
            } else {
                int w2 = Log.w(TAG, "Couldn't set home-as-up indicator");
            }
        }
    }

    private void setActionBarDescription(int i) {
        SetIndicatorInfo setIndicatorInfo;
        int contentDescRes = i;
        if (this.mActivityImpl != null) {
            this.mActivityImpl.setActionBarDescription(contentDescRes);
        } else if (Build.VERSION.SDK_INT >= 18) {
            ActionBar actionBar = this.mActivity.getActionBar();
            if (actionBar != null) {
                actionBar.setHomeActionContentDescription(contentDescRes);
            }
        } else {
            if (this.mSetIndicatorInfo == null) {
                new SetIndicatorInfo(this.mActivity);
                this.mSetIndicatorInfo = setIndicatorInfo;
            }
            if (this.mSetIndicatorInfo.mSetHomeAsUpIndicator != null) {
                try {
                    ActionBar actionBar2 = this.mActivity.getActionBar();
                    Object invoke = this.mSetIndicatorInfo.mSetHomeActionContentDescription.invoke(actionBar2, new Object[]{Integer.valueOf(contentDescRes)});
                    actionBar2.setSubtitle(actionBar2.getSubtitle());
                } catch (Exception e) {
                    int w = Log.w(TAG, "Couldn't set content description via JB-MR2 API", e);
                }
            }
        }
    }

    /* renamed from: android.support.v4.app.ActionBarDrawerToggle$SetIndicatorInfo */
    private static class SetIndicatorInfo {
        Method mSetHomeActionContentDescription;
        Method mSetHomeAsUpIndicator;
        ImageView mUpIndicatorView;

        SetIndicatorInfo(Activity activity) {
            Activity activity2 = activity;
            try {
                this.mSetHomeAsUpIndicator = ActionBar.class.getDeclaredMethod("setHomeAsUpIndicator", new Class[]{Drawable.class});
                this.mSetHomeActionContentDescription = ActionBar.class.getDeclaredMethod("setHomeActionContentDescription", new Class[]{Integer.TYPE});
            } catch (NoSuchMethodException e) {
                NoSuchMethodException noSuchMethodException = e;
                View home = activity2.findViewById(ActionBarDrawerToggle.ID_HOME);
                if (home != null) {
                    ViewGroup parent = (ViewGroup) home.getParent();
                    if (parent.getChildCount() == 2) {
                        View first = parent.getChildAt(0);
                        View up = first.getId() == ActionBarDrawerToggle.ID_HOME ? parent.getChildAt(1) : first;
                        if (up instanceof ImageView) {
                            this.mUpIndicatorView = (ImageView) up;
                        }
                    }
                }
            }
        }
    }

    /* renamed from: android.support.v4.app.ActionBarDrawerToggle$SlideDrawable */
    private class SlideDrawable extends InsetDrawable implements Drawable.Callback {
        private final boolean mHasMirroring;
        private float mOffset;
        private float mPosition;
        private final Rect mTmpRect;
        final /* synthetic */ ActionBarDrawerToggle this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        SlideDrawable(ActionBarDrawerToggle actionBarDrawerToggle, Drawable wrapped) {
            super(wrapped, 0);
            Rect rect;
            this.this$0 = actionBarDrawerToggle;
            this.mHasMirroring = Build.VERSION.SDK_INT > 18;
            new Rect();
            this.mTmpRect = rect;
        }

        public void setPosition(float position) {
            this.mPosition = position;
            invalidateSelf();
        }

        public float getPosition() {
            return this.mPosition;
        }

        public void setOffset(float offset) {
            this.mOffset = offset;
            invalidateSelf();
        }

        public void draw(@NonNull Canvas canvas) {
            Canvas canvas2 = canvas;
            copyBounds(this.mTmpRect);
            int save = canvas2.save();
            boolean isLayoutRTL = ViewCompat.getLayoutDirection(this.this$0.mActivity.getWindow().getDecorView()) == 1;
            int flipRtl = isLayoutRTL ? -1 : 1;
            int width = this.mTmpRect.width();
            canvas2.translate((-this.mOffset) * ((float) width) * this.mPosition * ((float) flipRtl), 0.0f);
            if (isLayoutRTL && !this.mHasMirroring) {
                canvas2.translate((float) width, 0.0f);
                canvas2.scale(-1.0f, 1.0f);
            }
            super.draw(canvas2);
            canvas2.restore();
        }
    }
}

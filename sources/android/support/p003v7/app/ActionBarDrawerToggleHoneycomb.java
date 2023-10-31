package android.support.p003v7.app;

import android.app.ActionBar;
import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.lang.reflect.Method;

/* renamed from: android.support.v7.app.ActionBarDrawerToggleHoneycomb */
class ActionBarDrawerToggleHoneycomb {
    private static final String TAG = "ActionBarDrawerToggleHC";
    private static final int[] THEME_ATTRS = {16843531};

    public static SetIndicatorInfo setActionBarUpIndicator(SetIndicatorInfo setIndicatorInfo, Activity activity, Drawable drawable, int i) {
        SetIndicatorInfo setIndicatorInfo2;
        SetIndicatorInfo setIndicatorInfo3 = setIndicatorInfo;
        Activity activity2 = activity;
        Drawable drawable2 = drawable;
        int contentDescRes = i;
        new SetIndicatorInfo(activity2);
        SetIndicatorInfo info = setIndicatorInfo2;
        if (info.setHomeAsUpIndicator != null) {
            try {
                ActionBar actionBar = activity2.getActionBar();
                Object invoke = info.setHomeAsUpIndicator.invoke(actionBar, new Object[]{drawable2});
                Object invoke2 = info.setHomeActionContentDescription.invoke(actionBar, new Object[]{Integer.valueOf(contentDescRes)});
            } catch (Exception e) {
                int w = Log.w(TAG, "Couldn't set home-as-up indicator via JB-MR2 API", e);
            }
        } else if (info.upIndicatorView != null) {
            info.upIndicatorView.setImageDrawable(drawable2);
        } else {
            int w2 = Log.w(TAG, "Couldn't set home-as-up indicator");
        }
        return info;
    }

    public static SetIndicatorInfo setActionBarDescription(SetIndicatorInfo setIndicatorInfo, Activity activity, int i) {
        SetIndicatorInfo setIndicatorInfo2;
        SetIndicatorInfo info = setIndicatorInfo;
        Activity activity2 = activity;
        int contentDescRes = i;
        if (info == null) {
            new SetIndicatorInfo(activity2);
            info = setIndicatorInfo2;
        }
        if (info.setHomeAsUpIndicator != null) {
            try {
                ActionBar actionBar = activity2.getActionBar();
                Object invoke = info.setHomeActionContentDescription.invoke(actionBar, new Object[]{Integer.valueOf(contentDescRes)});
                if (Build.VERSION.SDK_INT <= 19) {
                    actionBar.setSubtitle(actionBar.getSubtitle());
                }
            } catch (Exception e) {
                int w = Log.w(TAG, "Couldn't set content description via JB-MR2 API", e);
            }
        }
        return info;
    }

    public static Drawable getThemeUpIndicator(Activity activity) {
        TypedArray a = activity.obtainStyledAttributes(THEME_ATTRS);
        Drawable result = a.getDrawable(0);
        a.recycle();
        return result;
    }

    /* renamed from: android.support.v7.app.ActionBarDrawerToggleHoneycomb$SetIndicatorInfo */
    static class SetIndicatorInfo {
        public Method setHomeActionContentDescription;
        public Method setHomeAsUpIndicator;
        public ImageView upIndicatorView;

        SetIndicatorInfo(Activity activity) {
            Activity activity2 = activity;
            try {
                this.setHomeAsUpIndicator = ActionBar.class.getDeclaredMethod("setHomeAsUpIndicator", new Class[]{Drawable.class});
                this.setHomeActionContentDescription = ActionBar.class.getDeclaredMethod("setHomeActionContentDescription", new Class[]{Integer.TYPE});
            } catch (NoSuchMethodException e) {
                NoSuchMethodException noSuchMethodException = e;
                View home = activity2.findViewById(16908332);
                if (home != null) {
                    ViewGroup parent = (ViewGroup) home.getParent();
                    if (parent.getChildCount() == 2) {
                        View first = parent.getChildAt(0);
                        View up = first.getId() == 16908332 ? parent.getChildAt(1) : first;
                        if (up instanceof ImageView) {
                            this.upIndicatorView = (ImageView) up;
                        }
                    }
                }
            }
        }
    }

    private ActionBarDrawerToggleHoneycomb() {
    }
}

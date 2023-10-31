package android.support.p000v4.app;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.p000v4.util.Pair;
import android.view.View;

/* renamed from: android.support.v4.app.ActivityOptionsCompat */
public class ActivityOptionsCompat {
    public static final String EXTRA_USAGE_TIME_REPORT = "android.activity.usage_time";
    public static final String EXTRA_USAGE_TIME_REPORT_PACKAGES = "android.usage_time_packages";

    @NonNull
    public static ActivityOptionsCompat makeCustomAnimation(@NonNull Context context, int i, int i2) {
        ActivityOptionsCompat activityOptionsCompat;
        ActivityOptionsCompat activityOptionsCompat2;
        Context context2 = context;
        int enterResId = i;
        int exitResId = i2;
        if (Build.VERSION.SDK_INT >= 16) {
            new ActivityOptionsCompatImpl(ActivityOptions.makeCustomAnimation(context2, enterResId, exitResId));
            return activityOptionsCompat2;
        }
        new ActivityOptionsCompat();
        return activityOptionsCompat;
    }

    @NonNull
    public static ActivityOptionsCompat makeScaleUpAnimation(@NonNull View view, int i, int i2, int i3, int i4) {
        ActivityOptionsCompat activityOptionsCompat;
        ActivityOptionsCompat activityOptionsCompat2;
        View source = view;
        int startX = i;
        int startY = i2;
        int startWidth = i3;
        int startHeight = i4;
        if (Build.VERSION.SDK_INT >= 16) {
            new ActivityOptionsCompatImpl(ActivityOptions.makeScaleUpAnimation(source, startX, startY, startWidth, startHeight));
            return activityOptionsCompat2;
        }
        new ActivityOptionsCompat();
        return activityOptionsCompat;
    }

    @NonNull
    public static ActivityOptionsCompat makeClipRevealAnimation(@NonNull View view, int i, int i2, int i3, int i4) {
        ActivityOptionsCompat activityOptionsCompat;
        ActivityOptionsCompat activityOptionsCompat2;
        View source = view;
        int startX = i;
        int startY = i2;
        int width = i3;
        int height = i4;
        if (Build.VERSION.SDK_INT >= 23) {
            new ActivityOptionsCompatImpl(ActivityOptions.makeClipRevealAnimation(source, startX, startY, width, height));
            return activityOptionsCompat2;
        }
        new ActivityOptionsCompat();
        return activityOptionsCompat;
    }

    @NonNull
    public static ActivityOptionsCompat makeThumbnailScaleUpAnimation(@NonNull View view, @NonNull Bitmap bitmap, int i, int i2) {
        ActivityOptionsCompat activityOptionsCompat;
        ActivityOptionsCompat activityOptionsCompat2;
        View source = view;
        Bitmap thumbnail = bitmap;
        int startX = i;
        int startY = i2;
        if (Build.VERSION.SDK_INT >= 16) {
            new ActivityOptionsCompatImpl(ActivityOptions.makeThumbnailScaleUpAnimation(source, thumbnail, startX, startY));
            return activityOptionsCompat2;
        }
        new ActivityOptionsCompat();
        return activityOptionsCompat;
    }

    @NonNull
    public static ActivityOptionsCompat makeSceneTransitionAnimation(@NonNull Activity activity, @NonNull View view, @NonNull String str) {
        ActivityOptionsCompat activityOptionsCompat;
        ActivityOptionsCompat activityOptionsCompat2;
        Activity activity2 = activity;
        View sharedElement = view;
        String sharedElementName = str;
        if (Build.VERSION.SDK_INT >= 21) {
            new ActivityOptionsCompatImpl(ActivityOptions.makeSceneTransitionAnimation(activity2, sharedElement, sharedElementName));
            return activityOptionsCompat2;
        }
        new ActivityOptionsCompat();
        return activityOptionsCompat;
    }

    @NonNull
    public static ActivityOptionsCompat makeSceneTransitionAnimation(@NonNull Activity activity, Pair<View, String>... pairArr) {
        ActivityOptionsCompat activityOptionsCompat;
        ActivityOptionsCompat activityOptionsCompat2;
        Activity activity2 = activity;
        Pair<View, String>[] sharedElements = pairArr;
        if (Build.VERSION.SDK_INT >= 21) {
            android.util.Pair<View, String>[] pairs = null;
            if (sharedElements != null) {
                pairs = new android.util.Pair[sharedElements.length];
                for (int i = 0; i < sharedElements.length; i++) {
                    pairs[i] = android.util.Pair.create(sharedElements[i].first, sharedElements[i].second);
                }
            }
            new ActivityOptionsCompatImpl(ActivityOptions.makeSceneTransitionAnimation(activity2, pairs));
            return activityOptionsCompat2;
        }
        new ActivityOptionsCompat();
        return activityOptionsCompat;
    }

    @NonNull
    public static ActivityOptionsCompat makeTaskLaunchBehind() {
        ActivityOptionsCompat activityOptionsCompat;
        ActivityOptionsCompat activityOptionsCompat2;
        if (Build.VERSION.SDK_INT >= 21) {
            ActivityOptionsCompat activityOptionsCompat3 = activityOptionsCompat2;
            new ActivityOptionsCompatImpl(ActivityOptions.makeTaskLaunchBehind());
            return activityOptionsCompat3;
        }
        ActivityOptionsCompat activityOptionsCompat4 = activityOptionsCompat;
        new ActivityOptionsCompat();
        return activityOptionsCompat4;
    }

    @NonNull
    public static ActivityOptionsCompat makeBasic() {
        ActivityOptionsCompat activityOptionsCompat;
        ActivityOptionsCompat activityOptionsCompat2;
        if (Build.VERSION.SDK_INT >= 23) {
            ActivityOptionsCompat activityOptionsCompat3 = activityOptionsCompat2;
            new ActivityOptionsCompatImpl(ActivityOptions.makeBasic());
            return activityOptionsCompat3;
        }
        ActivityOptionsCompat activityOptionsCompat4 = activityOptionsCompat;
        new ActivityOptionsCompat();
        return activityOptionsCompat4;
    }

    @RequiresApi(16)
    /* renamed from: android.support.v4.app.ActivityOptionsCompat$ActivityOptionsCompatImpl */
    private static class ActivityOptionsCompatImpl extends ActivityOptionsCompat {
        private final ActivityOptions mActivityOptions;

        ActivityOptionsCompatImpl(ActivityOptions activityOptions) {
            this.mActivityOptions = activityOptions;
        }

        public Bundle toBundle() {
            return this.mActivityOptions.toBundle();
        }

        public void update(ActivityOptionsCompat activityOptionsCompat) {
            ActivityOptionsCompat otherOptions = activityOptionsCompat;
            if (otherOptions instanceof ActivityOptionsCompatImpl) {
                this.mActivityOptions.update(((ActivityOptionsCompatImpl) otherOptions).mActivityOptions);
            }
        }

        public void requestUsageTimeReport(PendingIntent pendingIntent) {
            PendingIntent receiver = pendingIntent;
            if (Build.VERSION.SDK_INT >= 23) {
                this.mActivityOptions.requestUsageTimeReport(receiver);
            }
        }

        public ActivityOptionsCompat setLaunchBounds(@Nullable Rect rect) {
            ActivityOptionsCompatImpl activityOptionsCompatImpl;
            Rect screenSpacePixelRect = rect;
            if (Build.VERSION.SDK_INT < 24) {
                return this;
            }
            new ActivityOptionsCompatImpl(this.mActivityOptions.setLaunchBounds(screenSpacePixelRect));
            return activityOptionsCompatImpl;
        }

        public Rect getLaunchBounds() {
            if (Build.VERSION.SDK_INT < 24) {
                return null;
            }
            return this.mActivityOptions.getLaunchBounds();
        }
    }

    protected ActivityOptionsCompat() {
    }

    @NonNull
    public ActivityOptionsCompat setLaunchBounds(@Nullable Rect rect) {
        Rect rect2 = rect;
        return this;
    }

    @Nullable
    public Rect getLaunchBounds() {
        return null;
    }

    @Nullable
    public Bundle toBundle() {
        return null;
    }

    public void update(@NonNull ActivityOptionsCompat otherOptions) {
    }

    public void requestUsageTimeReport(@NonNull PendingIntent receiver) {
    }
}

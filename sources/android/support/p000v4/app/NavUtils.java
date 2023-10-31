package android.support.p000v4.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import gnu.expr.Declaration;

/* renamed from: android.support.v4.app.NavUtils */
public final class NavUtils {
    public static final String PARENT_ACTIVITY = "android.support.PARENT_ACTIVITY";
    private static final String TAG = "NavUtils";

    public static boolean shouldUpRecreateTask(@NonNull Activity activity, @NonNull Intent intent) {
        Activity sourceActivity = activity;
        Intent targetIntent = intent;
        if (Build.VERSION.SDK_INT >= 16) {
            return sourceActivity.shouldUpRecreateTask(targetIntent);
        }
        String action = sourceActivity.getIntent().getAction();
        return action != null && !action.equals("android.intent.action.MAIN");
    }

    public static void navigateUpFromSameTask(@NonNull Activity activity) {
        Throwable th;
        StringBuilder sb;
        Activity sourceActivity = activity;
        Intent upIntent = getParentActivityIntent(sourceActivity);
        if (upIntent == null) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Activity ").append(sourceActivity.getClass().getSimpleName()).append(" does not have a parent activity name specified.").append(" (Did you forget to add the android.support.PARENT_ACTIVITY <meta-data> ").append(" element in your manifest?)").toString());
            throw th2;
        }
        navigateUpTo(sourceActivity, upIntent);
    }

    public static void navigateUpTo(@NonNull Activity activity, @NonNull Intent intent) {
        Activity sourceActivity = activity;
        Intent upIntent = intent;
        if (Build.VERSION.SDK_INT >= 16) {
            boolean navigateUpTo = sourceActivity.navigateUpTo(upIntent);
            return;
        }
        Intent addFlags = upIntent.addFlags(Declaration.PUBLIC_ACCESS);
        sourceActivity.startActivity(upIntent);
        sourceActivity.finish();
    }

    @Nullable
    public static Intent getParentActivityIntent(@NonNull Activity activity) {
        ComponentName componentName;
        StringBuilder sb;
        Intent intent;
        Intent component;
        Intent result;
        Activity sourceActivity = activity;
        if (Build.VERSION.SDK_INT >= 16 && (result = sourceActivity.getParentActivityIntent()) != null) {
            return result;
        }
        String parentName = getParentActivityName(sourceActivity);
        if (parentName == null) {
            return null;
        }
        new ComponentName(sourceActivity, parentName);
        ComponentName target = componentName;
        try {
            if (getParentActivityName(sourceActivity, target) == null) {
                component = Intent.makeMainActivity(target);
            } else {
                new Intent();
                component = intent.setComponent(target);
            }
            return component;
        } catch (PackageManager.NameNotFoundException e) {
            PackageManager.NameNotFoundException nameNotFoundException = e;
            new StringBuilder();
            int e2 = Log.e(TAG, sb.append("getParentActivityIntent: bad parentActivityName '").append(parentName).append("' in manifest").toString());
            return null;
        }
    }

    @Nullable
    public static Intent getParentActivityIntent(@NonNull Context context, @NonNull Class<?> sourceActivityClass) throws PackageManager.NameNotFoundException {
        ComponentName componentName;
        ComponentName componentName2;
        Intent intent;
        Intent parentIntent;
        Context context2 = context;
        new ComponentName(context2, sourceActivityClass);
        String parentActivity = getParentActivityName(context2, componentName);
        if (parentActivity == null) {
            return null;
        }
        new ComponentName(context2, parentActivity);
        ComponentName target = componentName2;
        if (getParentActivityName(context2, target) == null) {
            parentIntent = Intent.makeMainActivity(target);
        } else {
            new Intent();
            parentIntent = intent.setComponent(target);
        }
        return parentIntent;
    }

    @Nullable
    public static Intent getParentActivityIntent(@NonNull Context context, @NonNull ComponentName componentName) throws PackageManager.NameNotFoundException {
        ComponentName componentName2;
        Intent intent;
        Intent parentIntent;
        Context context2 = context;
        ComponentName componentName3 = componentName;
        String parentActivity = getParentActivityName(context2, componentName3);
        if (parentActivity == null) {
            return null;
        }
        new ComponentName(componentName3.getPackageName(), parentActivity);
        ComponentName target = componentName2;
        if (getParentActivityName(context2, target) == null) {
            parentIntent = Intent.makeMainActivity(target);
        } else {
            new Intent();
            parentIntent = intent.setComponent(target);
        }
        return parentIntent;
    }

    @Nullable
    public static String getParentActivityName(@NonNull Activity activity) {
        Throwable th;
        Activity sourceActivity = activity;
        try {
            return getParentActivityName(sourceActivity, sourceActivity.getComponentName());
        } catch (PackageManager.NameNotFoundException e) {
            PackageManager.NameNotFoundException e2 = e;
            Throwable th2 = th;
            new IllegalArgumentException(e2);
            throw th2;
        }
    }

    @Nullable
    public static String getParentActivityName(@NonNull Context context, @NonNull ComponentName componentName) throws PackageManager.NameNotFoundException {
        StringBuilder sb;
        String result;
        Context context2 = context;
        ActivityInfo info = context2.getPackageManager().getActivityInfo(componentName, 128);
        if (Build.VERSION.SDK_INT >= 16 && (result = info.parentActivityName) != null) {
            return result;
        }
        if (info.metaData == null) {
            return null;
        }
        String parentActivity = info.metaData.getString(PARENT_ACTIVITY);
        if (parentActivity == null) {
            return null;
        }
        if (parentActivity.charAt(0) == '.') {
            new StringBuilder();
            parentActivity = sb.append(context2.getPackageName()).append(parentActivity).toString();
        }
        return parentActivity;
    }

    private NavUtils() {
    }
}

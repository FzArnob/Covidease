package android.support.p000v4.app;

import android.app.Activity;
import android.app.SharedElementCallback;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.annotation.IdRes;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.p000v4.app.SharedElementCallback;
import android.support.p000v4.content.ContextCompat;
import android.support.v13.view.DragAndDropPermissionsCompat;
import android.view.DragEvent;
import android.view.View;
import java.util.List;
import java.util.Map;

/* renamed from: android.support.v4.app.ActivityCompat */
public class ActivityCompat extends ContextCompat {
    private static PermissionCompatDelegate sDelegate;

    /* renamed from: android.support.v4.app.ActivityCompat$OnRequestPermissionsResultCallback */
    public interface OnRequestPermissionsResultCallback {
        void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr);
    }

    /* renamed from: android.support.v4.app.ActivityCompat$PermissionCompatDelegate */
    public interface PermissionCompatDelegate {
        boolean onActivityResult(@NonNull Activity activity, @IntRange(from = 0) int i, int i2, @Nullable Intent intent);

        boolean requestPermissions(@NonNull Activity activity, @NonNull String[] strArr, @IntRange(from = 0) int i);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* renamed from: android.support.v4.app.ActivityCompat$RequestPermissionsRequestCodeValidator */
    public interface RequestPermissionsRequestCodeValidator {
        void validateRequestPermissionsRequestCode(int i);
    }

    protected ActivityCompat() {
    }

    public static void setPermissionCompatDelegate(@Nullable PermissionCompatDelegate delegate) {
        sDelegate = delegate;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static PermissionCompatDelegate getPermissionCompatDelegate() {
        return sDelegate;
    }

    @Deprecated
    public static boolean invalidateOptionsMenu(Activity activity) {
        activity.invalidateOptionsMenu();
        return true;
    }

    public static void startActivityForResult(@NonNull Activity activity, @NonNull Intent intent, int i, @Nullable Bundle bundle) {
        Activity activity2 = activity;
        Intent intent2 = intent;
        int requestCode = i;
        Bundle options = bundle;
        if (Build.VERSION.SDK_INT >= 16) {
            activity2.startActivityForResult(intent2, requestCode, options);
        } else {
            activity2.startActivityForResult(intent2, requestCode);
        }
    }

    public static void startIntentSenderForResult(@NonNull Activity activity, @NonNull IntentSender intentSender, int i, @Nullable Intent intent, int i2, int i3, int i4, @Nullable Bundle bundle) throws IntentSender.SendIntentException {
        Activity activity2 = activity;
        IntentSender intent2 = intentSender;
        int requestCode = i;
        Intent fillInIntent = intent;
        int flagsMask = i2;
        int flagsValues = i3;
        int extraFlags = i4;
        Bundle options = bundle;
        if (Build.VERSION.SDK_INT >= 16) {
            activity2.startIntentSenderForResult(intent2, requestCode, fillInIntent, flagsMask, flagsValues, extraFlags, options);
        } else {
            activity2.startIntentSenderForResult(intent2, requestCode, fillInIntent, flagsMask, flagsValues, extraFlags);
        }
    }

    public static void finishAffinity(@NonNull Activity activity) {
        Activity activity2 = activity;
        if (Build.VERSION.SDK_INT >= 16) {
            activity2.finishAffinity();
        } else {
            activity2.finish();
        }
    }

    public static void finishAfterTransition(@NonNull Activity activity) {
        Activity activity2 = activity;
        if (Build.VERSION.SDK_INT >= 21) {
            activity2.finishAfterTransition();
        } else {
            activity2.finish();
        }
    }

    @Nullable
    public static Uri getReferrer(@NonNull Activity activity) {
        Activity activity2 = activity;
        if (Build.VERSION.SDK_INT >= 22) {
            return activity2.getReferrer();
        }
        Intent intent = activity2.getIntent();
        Uri referrer = (Uri) intent.getParcelableExtra("android.intent.extra.REFERRER");
        if (referrer != null) {
            return referrer;
        }
        String referrerName = intent.getStringExtra("android.intent.extra.REFERRER_NAME");
        if (referrerName != null) {
            return Uri.parse(referrerName);
        }
        return null;
    }

    @NonNull
    public static <T extends View> T requireViewById(@NonNull Activity activity, @IdRes int i) {
        Throwable th;
        Activity activity2 = activity;
        int id = i;
        if (Build.VERSION.SDK_INT >= 28) {
            return activity2.requireViewById(id);
        }
        Activity view = activity2.findViewById(id);
        if (view != null) {
            return view;
        }
        Throwable th2 = th;
        new IllegalArgumentException("ID does not reference a View inside this Activity");
        throw th2;
    }

    public static void setEnterSharedElementCallback(@NonNull Activity activity, @Nullable SharedElementCallback sharedElementCallback) {
        SharedElementCallback frameworkCallback;
        SharedElementCallback sharedElementCallback2;
        Activity activity2 = activity;
        SharedElementCallback callback = sharedElementCallback;
        if (Build.VERSION.SDK_INT >= 21) {
            if (callback != null) {
                frameworkCallback = sharedElementCallback2;
                new SharedElementCallback21Impl(callback);
            } else {
                frameworkCallback = null;
            }
            activity2.setEnterSharedElementCallback(frameworkCallback);
        }
    }

    public static void setExitSharedElementCallback(@NonNull Activity activity, @Nullable SharedElementCallback sharedElementCallback) {
        SharedElementCallback frameworkCallback;
        SharedElementCallback sharedElementCallback2;
        Activity activity2 = activity;
        SharedElementCallback callback = sharedElementCallback;
        if (Build.VERSION.SDK_INT >= 21) {
            if (callback != null) {
                frameworkCallback = sharedElementCallback2;
                new SharedElementCallback21Impl(callback);
            } else {
                frameworkCallback = null;
            }
            activity2.setExitSharedElementCallback(frameworkCallback);
        }
    }

    public static void postponeEnterTransition(@NonNull Activity activity) {
        Activity activity2 = activity;
        if (Build.VERSION.SDK_INT >= 21) {
            activity2.postponeEnterTransition();
        }
    }

    public static void startPostponedEnterTransition(@NonNull Activity activity) {
        Activity activity2 = activity;
        if (Build.VERSION.SDK_INT >= 21) {
            activity2.startPostponedEnterTransition();
        }
    }

    public static void requestPermissions(@NonNull Activity activity, @NonNull String[] strArr, @IntRange(from = 0) int i) {
        Handler handler;
        Runnable runnable;
        Activity activity2 = activity;
        String[] permissions = strArr;
        int requestCode = i;
        if (sDelegate != null && sDelegate.requestPermissions(activity2, permissions, requestCode)) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            if (activity2 instanceof RequestPermissionsRequestCodeValidator) {
                ((RequestPermissionsRequestCodeValidator) activity2).validateRequestPermissionsRequestCode(requestCode);
            }
            activity2.requestPermissions(permissions, requestCode);
        } else if (activity2 instanceof OnRequestPermissionsResultCallback) {
            new Handler(Looper.getMainLooper());
            final String[] strArr2 = permissions;
            final Activity activity3 = activity2;
            final int i2 = requestCode;
            new Runnable() {
                public void run() {
                    int[] grantResults = new int[strArr2.length];
                    PackageManager packageManager = activity3.getPackageManager();
                    String packageName = activity3.getPackageName();
                    int permissionCount = strArr2.length;
                    for (int i = 0; i < permissionCount; i++) {
                        grantResults[i] = packageManager.checkPermission(strArr2[i], packageName);
                    }
                    ((OnRequestPermissionsResultCallback) activity3).onRequestPermissionsResult(i2, strArr2, grantResults);
                }
            };
            boolean post = handler.post(runnable);
        }
    }

    public static boolean shouldShowRequestPermissionRationale(@NonNull Activity activity, @NonNull String str) {
        Activity activity2 = activity;
        String permission = str;
        if (Build.VERSION.SDK_INT >= 23) {
            return activity2.shouldShowRequestPermissionRationale(permission);
        }
        return false;
    }

    @Nullable
    public static DragAndDropPermissionsCompat requestDragAndDropPermissions(Activity activity, DragEvent dragEvent) {
        return DragAndDropPermissionsCompat.request(activity, dragEvent);
    }

    @RequiresApi(21)
    /* renamed from: android.support.v4.app.ActivityCompat$SharedElementCallback21Impl */
    private static class SharedElementCallback21Impl extends SharedElementCallback {
        private final SharedElementCallback mCallback;

        SharedElementCallback21Impl(SharedElementCallback callback) {
            this.mCallback = callback;
        }

        public void onSharedElementStart(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
            this.mCallback.onSharedElementStart(sharedElementNames, sharedElements, sharedElementSnapshots);
        }

        public void onSharedElementEnd(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots) {
            this.mCallback.onSharedElementEnd(sharedElementNames, sharedElements, sharedElementSnapshots);
        }

        public void onRejectSharedElements(List<View> rejectedSharedElements) {
            this.mCallback.onRejectSharedElements(rejectedSharedElements);
        }

        public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
            this.mCallback.onMapSharedElements(names, sharedElements);
        }

        public Parcelable onCaptureSharedElementSnapshot(View sharedElement, Matrix viewToGlobalMatrix, RectF screenBounds) {
            return this.mCallback.onCaptureSharedElementSnapshot(sharedElement, viewToGlobalMatrix, screenBounds);
        }

        public View onCreateSnapshotView(Context context, Parcelable snapshot) {
            return this.mCallback.onCreateSnapshotView(context, snapshot);
        }

        @RequiresApi(23)
        public void onSharedElementsArrived(List<String> sharedElementNames, List<View> sharedElements, SharedElementCallback.OnSharedElementsReadyListener listener) {
            SharedElementCallback.OnSharedElementsReadyListener onSharedElementsReadyListener;
            final SharedElementCallback.OnSharedElementsReadyListener onSharedElementsReadyListener2 = listener;
            new SharedElementCallback.OnSharedElementsReadyListener(this) {
                final /* synthetic */ SharedElementCallback21Impl this$0;

                {
                    this.this$0 = this$0;
                }

                public void onSharedElementsReady() {
                    onSharedElementsReadyListener2.onSharedElementsReady();
                }
            };
            this.mCallback.onSharedElementsArrived(sharedElementNames, sharedElements, onSharedElementsReadyListener);
        }
    }
}

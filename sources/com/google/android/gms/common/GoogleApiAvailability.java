package com.google.android.gms.common;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.GuardedBy;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.p000v4.app.FragmentActivity;
import android.support.p000v4.app.NotificationCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ProgressBar;
import com.google.android.gms.base.C0551R;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.api.internal.LifecycleFragment;
import com.google.android.gms.common.api.internal.zabq;
import com.google.android.gms.common.api.internal.zabr;
import com.google.android.gms.common.api.internal.zabu;
import com.google.android.gms.common.internal.ConnectionErrorMessages;
import com.google.android.gms.common.internal.DialogRedirect;
import com.google.android.gms.common.internal.HideFirstParty;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.internal.base.zap;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GoogleApiAvailability extends GoogleApiAvailabilityLight {
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    private static final Object mLock;
    private static final GoogleApiAvailability zaao;
    @GuardedBy("mLock")
    private String zaap;

    public static GoogleApiAvailability getInstance() {
        return zaao;
    }

    @VisibleForTesting
    public GoogleApiAvailability() {
    }

    @MainThread
    public Task<Void> makeGooglePlayServicesAvailable(Activity activity) {
        ConnectionResult connectionResult;
        Activity activity2 = activity;
        Preconditions.checkMainThread("makeGooglePlayServicesAvailable must be called from the main thread");
        int isGooglePlayServicesAvailable = isGooglePlayServicesAvailable(activity2, GOOGLE_PLAY_SERVICES_VERSION_CODE);
        int i = isGooglePlayServicesAvailable;
        if (isGooglePlayServicesAvailable == 0) {
            return Tasks.forResult(null);
        }
        zabu zac = zabu.zac(activity2);
        zabu zabu = zac;
        zabu zabu2 = zac;
        new ConnectionResult(i, (PendingIntent) null);
        ConnectionResult connectionResult2 = connectionResult;
        ConnectionResult connectionResult3 = connectionResult2;
        zabu.zab(connectionResult2, 0);
        return zabu2.getTask();
    }

    @SuppressLint({"HandlerLeak"})
    private class zaa extends zap {
        private final Context zaaq;
        private final /* synthetic */ GoogleApiAvailability zaar;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public zaa(GoogleApiAvailability googleApiAvailability, Context context) {
            super(Looper.myLooper() == null ? Looper.getMainLooper() : Looper.myLooper());
            Context context2 = context;
            this.zaar = googleApiAvailability;
            this.zaaq = context2.getApplicationContext();
        }

        public final void handleMessage(Message message) {
            StringBuilder sb;
            Message message2 = message;
            switch (message2.what) {
                case 1:
                    int isGooglePlayServicesAvailable = this.zaar.isGooglePlayServicesAvailable(this.zaaq);
                    if (this.zaar.isUserResolvableError(isGooglePlayServicesAvailable)) {
                        this.zaar.showErrorNotification(this.zaaq, isGooglePlayServicesAvailable);
                        return;
                    }
                    return;
                default:
                    int i = message2.what;
                    new StringBuilder(50);
                    int w = Log.w("GoogleApiAvailability", sb.append("Don't know how to handle this message: ").append(i).toString());
                    return;
            }
        }
    }

    public Dialog getErrorDialog(Activity activity, int i, int i2) {
        return getErrorDialog(activity, i, i2, (DialogInterface.OnCancelListener) null);
    }

    public Dialog getErrorDialog(Activity activity, int i, int i2, DialogInterface.OnCancelListener onCancelListener) {
        Activity activity2 = activity;
        int i3 = i;
        return zaa((Context) activity2, i3, DialogRedirect.getInstance(activity2, getErrorResolutionIntent(activity2, i3, "d"), i2), onCancelListener);
    }

    public boolean showErrorDialogFragment(Activity activity, int i, int i2) {
        return showErrorDialogFragment(activity, i, i2, (DialogInterface.OnCancelListener) null);
    }

    public final boolean zaa(Activity activity, @NonNull LifecycleFragment lifecycleFragment, int i, int i2, DialogInterface.OnCancelListener onCancelListener) {
        Activity activity2 = activity;
        int i3 = i;
        int i4 = i2;
        DialogInterface.OnCancelListener onCancelListener2 = onCancelListener;
        Dialog zaa2 = zaa((Context) activity2, i3, DialogRedirect.getInstance(lifecycleFragment, getErrorResolutionIntent(activity2, i3, "d"), 2), onCancelListener2);
        Dialog dialog = zaa2;
        if (zaa2 == null) {
            return false;
        }
        zaa(activity2, dialog, GooglePlayServicesUtil.GMS_ERROR_DIALOG, onCancelListener2);
        return true;
    }

    public boolean showErrorDialogFragment(Activity activity, int i, int i2, DialogInterface.OnCancelListener onCancelListener) {
        Activity activity2 = activity;
        DialogInterface.OnCancelListener onCancelListener2 = onCancelListener;
        Dialog errorDialog = getErrorDialog(activity2, i, i2, onCancelListener2);
        Dialog dialog = errorDialog;
        if (errorDialog == null) {
            return false;
        }
        zaa(activity2, dialog, GooglePlayServicesUtil.GMS_ERROR_DIALOG, onCancelListener2);
        return true;
    }

    public void showErrorNotification(Context context, int i) {
        int i2 = i;
        Context context2 = context;
        zaa(context2, i2, (String) null, getErrorResolutionPendingIntent(context2, i2, 0, "n"));
    }

    public void showErrorNotification(Context context, ConnectionResult connectionResult) {
        Context context2 = context;
        ConnectionResult connectionResult2 = connectionResult;
        zaa(context2, connectionResult2.getErrorCode(), (String) null, getErrorResolutionPendingIntent(context2, connectionResult2));
    }

    public final boolean zaa(Context context, ConnectionResult connectionResult, int i) {
        Context context2 = context;
        ConnectionResult connectionResult2 = connectionResult;
        int i2 = i;
        PendingIntent errorResolutionPendingIntent = getErrorResolutionPendingIntent(context2, connectionResult2);
        PendingIntent pendingIntent = errorResolutionPendingIntent;
        if (errorResolutionPendingIntent == null) {
            return false;
        }
        zaa(context2, connectionResult2.getErrorCode(), (String) null, GoogleApiActivity.zaa(context2, pendingIntent, i2));
        return true;
    }

    public static Dialog zaa(Activity activity, DialogInterface.OnCancelListener onCancelListener) {
        ProgressBar progressBar;
        AlertDialog.Builder builder;
        Activity activity2 = activity;
        new ProgressBar(activity2, (AttributeSet) null, 16842874);
        ProgressBar progressBar2 = progressBar;
        ProgressBar progressBar3 = progressBar2;
        progressBar2.setIndeterminate(true);
        progressBar3.setVisibility(0);
        new AlertDialog.Builder(activity2);
        AlertDialog.Builder builder2 = builder;
        AlertDialog.Builder builder3 = builder2;
        AlertDialog.Builder view = builder2.setView(progressBar3);
        AlertDialog.Builder message = builder3.setMessage(ConnectionErrorMessages.getErrorMessage(activity2, 18));
        AlertDialog.Builder positiveButton = builder3.setPositiveButton("", (DialogInterface.OnClickListener) null);
        AlertDialog create = builder3.create();
        zaa(activity2, (Dialog) create, "GooglePlayServicesUpdatingDialog", onCancelListener);
        return create;
    }

    @Nullable
    public final zabq zaa(Context context, zabr zabr) {
        IntentFilter intentFilter;
        zabq zabq;
        Context context2 = context;
        zabr zabr2 = zabr;
        new IntentFilter("android.intent.action.PACKAGE_ADDED");
        IntentFilter intentFilter2 = intentFilter;
        intentFilter2.addDataScheme("package");
        new zabq(zabr2);
        zabq zabq2 = zabq;
        Intent registerReceiver = context2.registerReceiver(zabq2, intentFilter2);
        zabq2.zac(context2);
        if (isUninstalledAppPossiblyUpdating(context2, "com.google.android.gms")) {
            return zabq2;
        }
        zabr2.zas();
        zabq2.unregister();
        return null;
    }

    public Task<Void> checkApiAvailability(GoogleApi<?> googleApi, GoogleApi<?>... googleApiArr) {
        List list;
        Continuation continuation;
        GoogleApi<?>[] googleApiArr2 = googleApiArr;
        GoogleApi<?> googleApi2 = googleApi;
        GoogleApi<?> googleApi3 = googleApi2;
        Object checkNotNull = Preconditions.checkNotNull(googleApi2, "Requested API must not be null.");
        GoogleApi<?>[] googleApiArr3 = googleApiArr2;
        GoogleApi<?>[] googleApiArr4 = googleApiArr3;
        int length = googleApiArr3.length;
        for (int i = 0; i < length; i++) {
            Object checkNotNull2 = Preconditions.checkNotNull(googleApiArr4[i], "Requested API must not be null.");
        }
        new ArrayList(googleApiArr2.length + 1);
        List list2 = list;
        List list3 = list2;
        boolean add = list2.add(googleApi3);
        boolean addAll = list3.addAll(Arrays.asList(googleApiArr2));
        new zaa(this);
        return GoogleApiManager.zabc().zaa((Iterable<? extends GoogleApi<?>>) list3).continueWith(continuation);
    }

    @VisibleForTesting(otherwise = 2)
    private final String zag() {
        Object obj = mLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                String str = this.zaap;
                return str;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    @TargetApi(26)
    public void setDefaultNotificationChannelId(@NonNull Context context, @NonNull String str) {
        Context context2 = context;
        String str2 = str;
        if (PlatformVersion.isAtLeastO()) {
            Object checkNotNull = Preconditions.checkNotNull(((NotificationManager) context2.getSystemService("notification")).getNotificationChannel(str2));
        }
        Object obj = mLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                this.zaap = str2;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    @HideFirstParty
    public int isGooglePlayServicesAvailable(Context context) {
        return super.isGooglePlayServicesAvailable(context);
    }

    @ShowFirstParty
    @KeepForSdk
    public int isGooglePlayServicesAvailable(Context context, int i) {
        return super.isGooglePlayServicesAvailable(context, i);
    }

    public final boolean isUserResolvableError(int i) {
        return super.isUserResolvableError(i);
    }

    @Nullable
    @ShowFirstParty
    @KeepForSdk
    public Intent getErrorResolutionIntent(Context context, int i, @Nullable String str) {
        return super.getErrorResolutionIntent(context, i, str);
    }

    @Nullable
    public PendingIntent getErrorResolutionPendingIntent(Context context, int i, int i2) {
        return super.getErrorResolutionPendingIntent(context, i, i2);
    }

    @Nullable
    public PendingIntent getErrorResolutionPendingIntent(Context context, ConnectionResult connectionResult) {
        Context context2 = context;
        ConnectionResult connectionResult2 = connectionResult;
        if (connectionResult2.hasResolution()) {
            return connectionResult2.getResolution();
        }
        return getErrorResolutionPendingIntent(context2, connectionResult2.getErrorCode(), 0);
    }

    @ShowFirstParty
    @KeepForSdk
    public int getClientVersion(Context context) {
        return super.getClientVersion(context);
    }

    public final String getErrorString(int i) {
        return super.getErrorString(i);
    }

    static Dialog zaa(Context context, int i, DialogRedirect dialogRedirect, DialogInterface.OnCancelListener onCancelListener) {
        TypedValue typedValue;
        AlertDialog.Builder builder;
        AlertDialog.Builder builder2;
        Context context2 = context;
        int i2 = i;
        DialogRedirect dialogRedirect2 = dialogRedirect;
        DialogInterface.OnCancelListener onCancelListener2 = onCancelListener;
        if (i2 == 0) {
            return null;
        }
        AlertDialog.Builder builder3 = null;
        new TypedValue();
        TypedValue typedValue2 = typedValue;
        boolean resolveAttribute = context2.getTheme().resolveAttribute(16843529, typedValue2, true);
        if ("Theme.Dialog.Alert".equals(context2.getResources().getResourceEntryName(typedValue2.resourceId))) {
            new AlertDialog.Builder(context2, 5);
            builder3 = builder2;
        }
        if (builder3 == null) {
            new AlertDialog.Builder(context2);
            builder3 = builder;
        }
        AlertDialog.Builder message = builder3.setMessage(ConnectionErrorMessages.getErrorMessage(context2, i2));
        if (onCancelListener2 != null) {
            AlertDialog.Builder onCancelListener3 = builder3.setOnCancelListener(onCancelListener2);
        }
        String errorDialogButtonMessage = ConnectionErrorMessages.getErrorDialogButtonMessage(context2, i2);
        String str = errorDialogButtonMessage;
        if (errorDialogButtonMessage != null) {
            AlertDialog.Builder positiveButton = builder3.setPositiveButton(str, dialogRedirect2);
        }
        String errorTitle = ConnectionErrorMessages.getErrorTitle(context2, i2);
        String str2 = errorTitle;
        if (errorTitle != null) {
            AlertDialog.Builder title = builder3.setTitle(str2);
        }
        return builder3.create();
    }

    static void zaa(Activity activity, Dialog dialog, String str, DialogInterface.OnCancelListener onCancelListener) {
        Activity activity2 = activity;
        Dialog dialog2 = dialog;
        String str2 = str;
        DialogInterface.OnCancelListener onCancelListener2 = onCancelListener;
        boolean z = activity2 instanceof FragmentActivity;
        boolean z2 = z;
        if (z) {
            SupportErrorDialogFragment.newInstance(dialog2, onCancelListener2).show(((FragmentActivity) activity2).getSupportFragmentManager(), str2);
            return;
        }
        ErrorDialogFragment.newInstance(dialog2, onCancelListener2).show(activity2.getFragmentManager(), str2);
    }

    @TargetApi(20)
    private final void zaa(Context context, int i, String str, PendingIntent pendingIntent) {
        NotificationCompat.Builder builder;
        NotificationCompat.BigTextStyle bigTextStyle;
        int i2;
        NotificationChannel notificationChannel;
        Context context2 = context;
        int i3 = i;
        String str2 = str;
        PendingIntent pendingIntent2 = pendingIntent;
        if (i3 == 18) {
            zaa(context2);
        } else if (pendingIntent2 != null) {
            String errorNotificationTitle = ConnectionErrorMessages.getErrorNotificationTitle(context2, i3);
            String errorNotificationMessage = ConnectionErrorMessages.getErrorNotificationMessage(context2, i3);
            Resources resources = context2.getResources();
            NotificationManager notificationManager = (NotificationManager) context2.getSystemService("notification");
            new NotificationCompat.Builder(context2);
            NotificationCompat.Builder contentTitle = builder.setLocalOnly(true).setAutoCancel(true).setContentTitle(errorNotificationTitle);
            new NotificationCompat.BigTextStyle();
            NotificationCompat.Builder style = contentTitle.setStyle(bigTextStyle.bigText(errorNotificationMessage));
            if (DeviceProperties.isWearable(context2)) {
                Preconditions.checkState(PlatformVersion.isAtLeastKitKatWatch());
                NotificationCompat.Builder priority = style.setSmallIcon(context2.getApplicationInfo().icon).setPriority(2);
                if (DeviceProperties.isWearableWithoutPlayStore(context2)) {
                    NotificationCompat.Builder addAction = style.addAction(C0551R.C0552drawable.common_full_open_on_phone, resources.getString(C0551R.string.common_open_on_phone), pendingIntent2);
                } else {
                    NotificationCompat.Builder contentIntent = style.setContentIntent(pendingIntent2);
                }
            } else {
                NotificationCompat.Builder contentText = style.setSmallIcon(17301642).setTicker(resources.getString(C0551R.string.common_google_play_services_notification_ticker)).setWhen(System.currentTimeMillis()).setContentIntent(pendingIntent2).setContentText(errorNotificationMessage);
            }
            NotificationCompat.Builder builder2 = style;
            NotificationManager notificationManager2 = notificationManager;
            Context context3 = context2;
            if (PlatformVersion.isAtLeastO()) {
                NotificationManager notificationManager3 = notificationManager2;
                Context context4 = context3;
                Preconditions.checkState(PlatformVersion.isAtLeastO());
                String zag = zag();
                String str3 = zag;
                if (zag == null) {
                    str3 = "com.google.android.gms.availability";
                    NotificationChannel notificationChannel2 = notificationManager3.getNotificationChannel(str3);
                    String defaultNotificationChannelName = ConnectionErrorMessages.getDefaultNotificationChannelName(context4);
                    if (notificationChannel2 == null) {
                        new NotificationChannel(str3, defaultNotificationChannelName, 4);
                        notificationManager3.createNotificationChannel(notificationChannel);
                    } else if (!defaultNotificationChannelName.contentEquals(notificationChannel2.getName())) {
                        notificationChannel2.setName(defaultNotificationChannelName);
                        notificationManager3.createNotificationChannel(notificationChannel2);
                    }
                }
                NotificationCompat.Builder channelId = builder2.setChannelId(str3);
            }
            Notification build = style.build();
            switch (i3) {
                case 1:
                case 2:
                case 3:
                    i2 = 10436;
                    GooglePlayServicesUtilLight.sCanceledAvailabilityNotification.set(false);
                    break;
                default:
                    i2 = 39789;
                    break;
            }
            notificationManager.notify(i2, build);
        } else if (i3 == 6) {
            int w = Log.w("GoogleApiAvailability", "Missing resolution for ConnectionResult.RESOLUTION_REQUIRED. Call GoogleApiAvailability#showErrorNotification(Context, ConnectionResult) instead.");
        }
    }

    /* access modifiers changed from: package-private */
    public final void zaa(Context context) {
        Handler handler;
        new zaa(this, context);
        boolean sendEmptyMessageDelayed = handler.sendEmptyMessageDelayed(1, 120000);
    }

    static {
        Object obj;
        GoogleApiAvailability googleApiAvailability;
        new Object();
        mLock = obj;
        new GoogleApiAvailability();
        zaao = googleApiAvailability;
    }
}

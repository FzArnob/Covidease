package android.support.p000v4.content;

import android.accessibilityservice.AccessibilityService;
import android.accounts.AccountManager;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.AppOpsManager;
import android.app.DownloadManager;
import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.app.SearchManager;
import android.app.UiModeManager;
import android.app.WallpaperManager;
import android.app.admin.DevicePolicyManager;
import android.app.job.JobScheduler;
import android.app.usage.UsageStatsManager;
import android.appwidget.AppWidgetManager;
import android.bluetooth.BluetoothManager;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.RestrictionsManager;
import android.content.pm.LauncherApps;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.hardware.ConsumerIrManager;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraManager;
import android.hardware.display.DisplayManager;
import android.hardware.input.InputManager;
import android.hardware.usb.UsbManager;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.MediaRouter;
import android.media.projection.MediaProjectionManager;
import android.media.session.MediaSessionManager;
import android.media.tv.TvInputManager;
import android.net.ConnectivityManager;
import android.net.nsd.NsdManager;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.nfc.NfcManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.DropBoxManager;
import android.os.PowerManager;
import android.os.Process;
import android.os.UserManager;
import android.os.Vibrator;
import android.os.storage.StorageManager;
import android.print.PrintManager;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p000v4.app.NotificationCompat;
import android.telecom.TelecomManager;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.view.accessibility.CaptioningManager;
import android.view.inputmethod.InputMethodManager;
import android.view.textservice.TextServicesManager;
import java.io.File;
import java.util.HashMap;

/* renamed from: android.support.v4.content.ContextCompat */
public class ContextCompat {
    private static final String TAG = "ContextCompat";
    private static final Object sLock;
    private static TypedValue sTempValue;

    static {
        Object obj;
        new Object();
        sLock = obj;
    }

    protected ContextCompat() {
    }

    public static boolean startActivities(@NonNull Context context, @NonNull Intent[] intents) {
        return startActivities(context, intents, (Bundle) null);
    }

    public static boolean startActivities(@NonNull Context context, @NonNull Intent[] intentArr, @Nullable Bundle bundle) {
        Context context2 = context;
        Intent[] intents = intentArr;
        Bundle options = bundle;
        if (Build.VERSION.SDK_INT >= 16) {
            context2.startActivities(intents, options);
        } else {
            context2.startActivities(intents);
        }
        return true;
    }

    public static void startActivity(@NonNull Context context, @NonNull Intent intent, @Nullable Bundle bundle) {
        Context context2 = context;
        Intent intent2 = intent;
        Bundle options = bundle;
        if (Build.VERSION.SDK_INT >= 16) {
            context2.startActivity(intent2, options);
        } else {
            context2.startActivity(intent2);
        }
    }

    @Nullable
    public static File getDataDir(@NonNull Context context) {
        File file;
        File file2;
        Context context2 = context;
        if (Build.VERSION.SDK_INT >= 24) {
            return context2.getDataDir();
        }
        String dataDir = context2.getApplicationInfo().dataDir;
        if (dataDir != null) {
            file = file2;
            new File(dataDir);
        } else {
            file = null;
        }
        return file;
    }

    @NonNull
    public static File[] getObbDirs(@NonNull Context context) {
        Context context2 = context;
        if (Build.VERSION.SDK_INT >= 19) {
            return context2.getObbDirs();
        }
        return new File[]{context2.getObbDir()};
    }

    @NonNull
    public static File[] getExternalFilesDirs(@NonNull Context context, @Nullable String str) {
        Context context2 = context;
        String type = str;
        if (Build.VERSION.SDK_INT >= 19) {
            return context2.getExternalFilesDirs(type);
        }
        return new File[]{context2.getExternalFilesDir(type)};
    }

    @NonNull
    public static File[] getExternalCacheDirs(@NonNull Context context) {
        Context context2 = context;
        if (Build.VERSION.SDK_INT >= 19) {
            return context2.getExternalCacheDirs();
        }
        return new File[]{context2.getExternalCacheDir()};
    }

    private static File buildPath(File base, String... segments) {
        File file;
        File file2;
        File cur = base;
        String[] strArr = segments;
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            String segment = strArr[i];
            if (cur == null) {
                new File(segment);
                cur = file2;
            } else if (segment != null) {
                new File(cur, segment);
                cur = file;
            }
        }
        return cur;
    }

    @Nullable
    public static Drawable getDrawable(@NonNull Context context, @DrawableRes int i) {
        TypedValue typedValue;
        Context context2 = context;
        int id = i;
        if (Build.VERSION.SDK_INT >= 21) {
            return context2.getDrawable(id);
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return context2.getResources().getDrawable(id);
        }
        Object obj = sLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (sTempValue == null) {
                    new TypedValue();
                    sTempValue = typedValue;
                }
                context2.getResources().getValue(id, sTempValue, true);
                int resolvedId = sTempValue.resourceId;
                return context2.getResources().getDrawable(resolvedId);
            } catch (Throwable th) {
                while (true) {
                    Throwable th2 = th;
                    Object obj3 = obj2;
                    throw th2;
                }
            }
        }
    }

    @Nullable
    public static ColorStateList getColorStateList(@NonNull Context context, @ColorRes int i) {
        Context context2 = context;
        int id = i;
        if (Build.VERSION.SDK_INT >= 23) {
            return context2.getColorStateList(id);
        }
        return context2.getResources().getColorStateList(id);
    }

    @ColorInt
    public static int getColor(@NonNull Context context, @ColorRes int i) {
        Context context2 = context;
        int id = i;
        if (Build.VERSION.SDK_INT >= 23) {
            return context2.getColor(id);
        }
        return context2.getResources().getColor(id);
    }

    public static int checkSelfPermission(@NonNull Context context, @NonNull String str) {
        Throwable th;
        Context context2 = context;
        String permission = str;
        if (permission != null) {
            return context2.checkPermission(permission, Process.myPid(), Process.myUid());
        }
        Throwable th2 = th;
        new IllegalArgumentException("permission is null");
        throw th2;
    }

    @Nullable
    public static File getNoBackupFilesDir(@NonNull Context context) {
        File file;
        Context context2 = context;
        if (Build.VERSION.SDK_INT >= 21) {
            return context2.getNoBackupFilesDir();
        }
        new File(context2.getApplicationInfo().dataDir, "no_backup");
        return createFilesDir(file);
    }

    public static File getCodeCacheDir(@NonNull Context context) {
        File file;
        Context context2 = context;
        if (Build.VERSION.SDK_INT >= 21) {
            return context2.getCodeCacheDir();
        }
        new File(context2.getApplicationInfo().dataDir, "code_cache");
        return createFilesDir(file);
    }

    private static synchronized File createFilesDir(File file) {
        File file2;
        StringBuilder sb;
        File file3 = file;
        synchronized (ContextCompat.class) {
            if (file3.exists() || file3.mkdirs()) {
                file2 = file3;
            } else if (file3.exists()) {
                file2 = file3;
            } else {
                new StringBuilder();
                int w = Log.w(TAG, sb.append("Unable to create files subdir ").append(file3.getPath()).toString());
                file2 = null;
            }
        }
        return file2;
    }

    @Nullable
    public static Context createDeviceProtectedStorageContext(@NonNull Context context) {
        Context context2 = context;
        if (Build.VERSION.SDK_INT >= 24) {
            return context2.createDeviceProtectedStorageContext();
        }
        return null;
    }

    public static boolean isDeviceProtectedStorage(@NonNull Context context) {
        Context context2 = context;
        if (Build.VERSION.SDK_INT >= 24) {
            return context2.isDeviceProtectedStorage();
        }
        return false;
    }

    public static void startForegroundService(@NonNull Context context, @NonNull Intent intent) {
        Context context2 = context;
        Intent intent2 = intent;
        if (Build.VERSION.SDK_INT >= 26) {
            ComponentName startForegroundService = context2.startForegroundService(intent2);
        } else {
            ComponentName startService = context2.startService(intent2);
        }
    }

    @Nullable
    public static <T> T getSystemService(@NonNull Context context, @NonNull Class<T> cls) {
        Context context2 = context;
        Class<T> serviceClass = cls;
        if (Build.VERSION.SDK_INT >= 23) {
            return context2.getSystemService(serviceClass);
        }
        String serviceName = getSystemServiceName(context2, serviceClass);
        return serviceName != null ? context2.getSystemService(serviceName) : null;
    }

    @Nullable
    public static String getSystemServiceName(@NonNull Context context, @NonNull Class<?> cls) {
        Context context2 = context;
        Class<?> serviceClass = cls;
        if (Build.VERSION.SDK_INT >= 23) {
            return context2.getSystemServiceName(serviceClass);
        }
        return LegacyServiceMapHolder.SERVICES.get(serviceClass);
    }

    /* renamed from: android.support.v4.content.ContextCompat$LegacyServiceMapHolder */
    private static final class LegacyServiceMapHolder {
        static final HashMap<Class<?>, String> SERVICES;

        private LegacyServiceMapHolder() {
        }

        static {
            HashMap<Class<?>, String> hashMap;
            new HashMap<>();
            SERVICES = hashMap;
            if (Build.VERSION.SDK_INT > 22) {
                String put = SERVICES.put(SubscriptionManager.class, "telephony_subscription_service");
                String put2 = SERVICES.put(UsageStatsManager.class, "usagestats");
            }
            if (Build.VERSION.SDK_INT > 21) {
                String put3 = SERVICES.put(AppWidgetManager.class, "appwidget");
                String put4 = SERVICES.put(BatteryManager.class, "batterymanager");
                String put5 = SERVICES.put(CameraManager.class, "camera");
                String put6 = SERVICES.put(JobScheduler.class, "jobscheduler");
                String put7 = SERVICES.put(LauncherApps.class, "launcherapps");
                String put8 = SERVICES.put(MediaProjectionManager.class, "media_projection");
                String put9 = SERVICES.put(MediaSessionManager.class, "media_session");
                String put10 = SERVICES.put(RestrictionsManager.class, "restrictions");
                String put11 = SERVICES.put(TelecomManager.class, "telecom");
                String put12 = SERVICES.put(TvInputManager.class, "tv_input");
            }
            if (Build.VERSION.SDK_INT > 19) {
                String put13 = SERVICES.put(AppOpsManager.class, "appops");
                String put14 = SERVICES.put(CaptioningManager.class, "captioning");
                String put15 = SERVICES.put(ConsumerIrManager.class, "consumer_ir");
                String put16 = SERVICES.put(PrintManager.class, "print");
            }
            if (Build.VERSION.SDK_INT > 18) {
                String put17 = SERVICES.put(BluetoothManager.class, "bluetooth");
            }
            if (Build.VERSION.SDK_INT > 17) {
                String put18 = SERVICES.put(DisplayManager.class, "display");
                String put19 = SERVICES.put(UserManager.class, "user");
            }
            if (Build.VERSION.SDK_INT > 16) {
                String put20 = SERVICES.put(InputManager.class, "input");
                String put21 = SERVICES.put(MediaRouter.class, "media_router");
                String put22 = SERVICES.put(NsdManager.class, "servicediscovery");
            }
            String put23 = SERVICES.put(AccessibilityService.class, "accessibility");
            String put24 = SERVICES.put(AccountManager.class, "account");
            String put25 = SERVICES.put(ActivityManager.class, "activity");
            String put26 = SERVICES.put(AlarmManager.class, NotificationCompat.CATEGORY_ALARM);
            String put27 = SERVICES.put(AudioManager.class, "audio");
            String put28 = SERVICES.put(ClipboardManager.class, "clipboard");
            String put29 = SERVICES.put(ConnectivityManager.class, "connectivity");
            String put30 = SERVICES.put(DevicePolicyManager.class, "device_policy");
            String put31 = SERVICES.put(DownloadManager.class, "download");
            String put32 = SERVICES.put(DropBoxManager.class, "dropbox");
            String put33 = SERVICES.put(InputMethodManager.class, "input_method");
            String put34 = SERVICES.put(KeyguardManager.class, "keyguard");
            String put35 = SERVICES.put(LayoutInflater.class, "layout_inflater");
            String put36 = SERVICES.put(LocationManager.class, "location");
            String put37 = SERVICES.put(NfcManager.class, "nfc");
            String put38 = SERVICES.put(NotificationManager.class, "notification");
            String put39 = SERVICES.put(PowerManager.class, "power");
            String put40 = SERVICES.put(SearchManager.class, "search");
            String put41 = SERVICES.put(SensorManager.class, "sensor");
            String put42 = SERVICES.put(StorageManager.class, "storage");
            String put43 = SERVICES.put(TelephonyManager.class, "phone");
            String put44 = SERVICES.put(TextServicesManager.class, "textservices");
            String put45 = SERVICES.put(UiModeManager.class, "uimode");
            String put46 = SERVICES.put(UsbManager.class, "usb");
            String put47 = SERVICES.put(Vibrator.class, "vibrator");
            String put48 = SERVICES.put(WallpaperManager.class, "wallpaper");
            String put49 = SERVICES.put(WifiP2pManager.class, "wifip2p");
            String put50 = SERVICES.put(WifiManager.class, "wifi");
            String put51 = SERVICES.put(WindowManager.class, "window");
        }
    }
}

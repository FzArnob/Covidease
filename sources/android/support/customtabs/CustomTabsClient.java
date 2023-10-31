package android.support.customtabs;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.customtabs.ICustomTabsCallback;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

public class CustomTabsClient {
    private final ICustomTabsService mService;
    private final ComponentName mServiceComponentName;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    CustomTabsClient(ICustomTabsService service, ComponentName componentName) {
        this.mService = service;
        this.mServiceComponentName = componentName;
    }

    public static boolean bindCustomTabsService(Context context, String str, CustomTabsServiceConnection customTabsServiceConnection) {
        Intent intent;
        Context context2 = context;
        String packageName = str;
        CustomTabsServiceConnection connection = customTabsServiceConnection;
        new Intent(CustomTabsService.ACTION_CUSTOM_TABS_CONNECTION);
        Intent intent2 = intent;
        if (!TextUtils.isEmpty(packageName)) {
            Intent intent3 = intent2.setPackage(packageName);
        }
        return context2.bindService(intent2, connection, 33);
    }

    public static String getPackageName(Context context, @Nullable List<String> packages) {
        return getPackageName(context, packages, false);
    }

    public static String getPackageName(Context context, @Nullable List<String> list, boolean z) {
        List<String> list2;
        Intent intent;
        Intent intent2;
        ResolveInfo defaultViewHandlerInfo;
        List<String> list3;
        List<String> list4;
        List<String> packages = list;
        boolean ignoreDefault = z;
        PackageManager pm = context.getPackageManager();
        if (packages == null) {
            list2 = list4;
            new ArrayList();
        } else {
            list2 = packages;
        }
        List<String> packageNames = list2;
        new Intent("android.intent.action.VIEW", Uri.parse("http://"));
        Intent activityIntent = intent;
        if (!ignoreDefault && (defaultViewHandlerInfo = pm.resolveActivity(activityIntent, 0)) != null) {
            String packageName = defaultViewHandlerInfo.activityInfo.packageName;
            new ArrayList(packageNames.size() + 1);
            packageNames = list3;
            boolean add = packageNames.add(packageName);
            if (packages != null) {
                boolean addAll = packageNames.addAll(packages);
            }
        }
        new Intent(CustomTabsService.ACTION_CUSTOM_TABS_CONNECTION);
        Intent serviceIntent = intent2;
        for (String packageName2 : packageNames) {
            Intent intent3 = serviceIntent.setPackage(packageName2);
            if (pm.resolveService(serviceIntent, 0) != null) {
                return packageName2;
            }
        }
        return null;
    }

    public static boolean connectAndInitialize(Context context, String str) {
        CustomTabsServiceConnection connection;
        Context context2 = context;
        String packageName = str;
        if (packageName == null) {
            return false;
        }
        Context applicationContext = context2.getApplicationContext();
        final Context context3 = applicationContext;
        new CustomTabsServiceConnection() {
            public final void onCustomTabsServiceConnected(ComponentName componentName, CustomTabsClient client) {
                ComponentName componentName2 = componentName;
                boolean warmup = client.warmup(0);
                context3.unbindService(this);
            }

            public final void onServiceDisconnected(ComponentName componentName) {
            }
        };
        try {
            return bindCustomTabsService(applicationContext, packageName, connection);
        } catch (SecurityException e) {
            SecurityException securityException = e;
            return false;
        }
    }

    public boolean warmup(long flags) {
        try {
            return this.mService.warmup(flags);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            return false;
        }
    }

    public CustomTabsSession newSession(CustomTabsCallback callback) {
        ICustomTabsCallback.Stub stub;
        CustomTabsSession customTabsSession;
        final CustomTabsCallback customTabsCallback = callback;
        new ICustomTabsCallback.Stub(this) {
            private Handler mHandler;
            final /* synthetic */ CustomTabsClient this$0;

            {
                Handler handler;
                this.this$0 = this$0;
                new Handler(Looper.getMainLooper());
                this.mHandler = handler;
            }

            public void onNavigationEvent(int i, Bundle bundle) {
                Runnable runnable;
                int navigationEvent = i;
                Bundle extras = bundle;
                if (customTabsCallback != null) {
                    final int i2 = navigationEvent;
                    final Bundle bundle2 = extras;
                    new Runnable(this) {
                        final /* synthetic */ C00452 this$1;

                        {
                            this.this$1 = this$1;
                        }

                        public void run() {
                            customTabsCallback.onNavigationEvent(i2, bundle2);
                        }
                    };
                    boolean post = this.mHandler.post(runnable);
                }
            }

            public void extraCallback(String str, Bundle bundle) throws RemoteException {
                Runnable runnable;
                String callbackName = str;
                Bundle args = bundle;
                if (customTabsCallback != null) {
                    final String str2 = callbackName;
                    final Bundle bundle2 = args;
                    new Runnable(this) {
                        final /* synthetic */ C00452 this$1;

                        {
                            this.this$1 = this$1;
                        }

                        public void run() {
                            customTabsCallback.extraCallback(str2, bundle2);
                        }
                    };
                    boolean post = this.mHandler.post(runnable);
                }
            }

            public void onMessageChannelReady(Bundle bundle) throws RemoteException {
                Runnable runnable;
                Bundle extras = bundle;
                if (customTabsCallback != null) {
                    final Bundle bundle2 = extras;
                    new Runnable(this) {
                        final /* synthetic */ C00452 this$1;

                        {
                            this.this$1 = this$1;
                        }

                        public void run() {
                            customTabsCallback.onMessageChannelReady(bundle2);
                        }
                    };
                    boolean post = this.mHandler.post(runnable);
                }
            }

            public void onPostMessage(String str, Bundle bundle) throws RemoteException {
                Runnable runnable;
                String message = str;
                Bundle extras = bundle;
                if (customTabsCallback != null) {
                    final String str2 = message;
                    final Bundle bundle2 = extras;
                    new Runnable(this) {
                        final /* synthetic */ C00452 this$1;

                        {
                            this.this$1 = this$1;
                        }

                        public void run() {
                            customTabsCallback.onPostMessage(str2, bundle2);
                        }
                    };
                    boolean post = this.mHandler.post(runnable);
                }
            }

            public void onRelationshipValidationResult(int i, Uri uri, boolean z, @Nullable Bundle bundle) throws RemoteException {
                Runnable runnable;
                int relation = i;
                Uri requestedOrigin = uri;
                boolean result = z;
                Bundle extras = bundle;
                if (customTabsCallback != null) {
                    final int i2 = relation;
                    final Uri uri2 = requestedOrigin;
                    final boolean z2 = result;
                    final Bundle bundle2 = extras;
                    new Runnable(this) {
                        final /* synthetic */ C00452 this$1;

                        {
                            this.this$1 = this$1;
                        }

                        public void run() {
                            customTabsCallback.onRelationshipValidationResult(i2, uri2, z2, bundle2);
                        }
                    };
                    boolean post = this.mHandler.post(runnable);
                }
            }
        };
        ICustomTabsCallback.Stub wrapper = stub;
        try {
            if (!this.mService.newSession(wrapper)) {
                return null;
            }
            new CustomTabsSession(this.mService, wrapper, this.mServiceComponentName);
            return customTabsSession;
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            return null;
        }
    }

    public Bundle extraCommand(String commandName, Bundle args) {
        try {
            return this.mService.extraCommand(commandName, args);
        } catch (RemoteException e) {
            RemoteException remoteException = e;
            return null;
        }
    }
}

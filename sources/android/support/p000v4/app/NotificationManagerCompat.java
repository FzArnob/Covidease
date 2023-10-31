package android.support.p000v4.app;

import android.app.AppOpsManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.provider.Settings;
import android.support.annotation.GuardedBy;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p000v4.app.INotificationSideChannel;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* renamed from: android.support.v4.app.NotificationManagerCompat */
public final class NotificationManagerCompat {
    public static final String ACTION_BIND_SIDE_CHANNEL = "android.support.BIND_NOTIFICATION_SIDE_CHANNEL";
    private static final String CHECK_OP_NO_THROW = "checkOpNoThrow";
    public static final String EXTRA_USE_SIDE_CHANNEL = "android.support.useSideChannel";
    public static final int IMPORTANCE_DEFAULT = 3;
    public static final int IMPORTANCE_HIGH = 4;
    public static final int IMPORTANCE_LOW = 2;
    public static final int IMPORTANCE_MAX = 5;
    public static final int IMPORTANCE_MIN = 1;
    public static final int IMPORTANCE_NONE = 0;
    public static final int IMPORTANCE_UNSPECIFIED = -1000;
    static final int MAX_SIDE_CHANNEL_SDK_VERSION = 19;
    private static final String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";
    private static final String SETTING_ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners";
    private static final int SIDE_CHANNEL_RETRY_BASE_INTERVAL_MS = 1000;
    private static final int SIDE_CHANNEL_RETRY_MAX_COUNT = 6;
    private static final String TAG = "NotifManCompat";
    @GuardedBy("sEnabledNotificationListenersLock")
    private static Set<String> sEnabledNotificationListenerPackages;
    @GuardedBy("sEnabledNotificationListenersLock")
    private static String sEnabledNotificationListeners;
    private static final Object sEnabledNotificationListenersLock;
    private static final Object sLock;
    @GuardedBy("sLock")
    private static SideChannelManager sSideChannelManager;
    private final Context mContext;
    private final NotificationManager mNotificationManager = ((NotificationManager) this.mContext.getSystemService("notification"));

    /* renamed from: android.support.v4.app.NotificationManagerCompat$Task */
    private interface Task {
        void send(INotificationSideChannel iNotificationSideChannel) throws RemoteException;
    }

    static {
        Object obj;
        Set<String> set;
        Object obj2;
        new Object();
        sEnabledNotificationListenersLock = obj;
        new HashSet();
        sEnabledNotificationListenerPackages = set;
        new Object();
        sLock = obj2;
    }

    @NonNull
    public static NotificationManagerCompat from(@NonNull Context context) {
        NotificationManagerCompat notificationManagerCompat;
        new NotificationManagerCompat(context);
        return notificationManagerCompat;
    }

    private NotificationManagerCompat(Context context) {
        this.mContext = context;
    }

    public void cancel(int id) {
        cancel((String) null, id);
    }

    public void cancel(@Nullable String str, int i) {
        Task task;
        String tag = str;
        int id = i;
        this.mNotificationManager.cancel(tag, id);
        if (Build.VERSION.SDK_INT <= 19) {
            new CancelTask(this.mContext.getPackageName(), id, tag);
            pushSideChannelQueue(task);
        }
    }

    public void cancelAll() {
        Task task;
        this.mNotificationManager.cancelAll();
        if (Build.VERSION.SDK_INT <= 19) {
            new CancelTask(this.mContext.getPackageName());
            pushSideChannelQueue(task);
        }
    }

    public void notify(int id, @NonNull Notification notification) {
        notify((String) null, id, notification);
    }

    public void notify(@Nullable String str, int i, @NonNull Notification notification) {
        Task task;
        String tag = str;
        int id = i;
        Notification notification2 = notification;
        if (useSideChannelForNotification(notification2)) {
            new NotifyTask(this.mContext.getPackageName(), id, tag, notification2);
            pushSideChannelQueue(task);
            this.mNotificationManager.cancel(tag, id);
            return;
        }
        this.mNotificationManager.notify(tag, id, notification2);
    }

    public boolean areNotificationsEnabled() {
        if (Build.VERSION.SDK_INT >= 24) {
            return this.mNotificationManager.areNotificationsEnabled();
        }
        if (Build.VERSION.SDK_INT < 19) {
            return true;
        }
        AppOpsManager appOps = (AppOpsManager) this.mContext.getSystemService("appops");
        ApplicationInfo appInfo = this.mContext.getApplicationInfo();
        String pkg = this.mContext.getApplicationContext().getPackageName();
        int uid = appInfo.uid;
        try {
            Class<?> appOpsClass = Class.forName(AppOpsManager.class.getName());
            Class[] clsArr = new Class[3];
            clsArr[0] = Integer.TYPE;
            Class[] clsArr2 = clsArr;
            clsArr2[1] = Integer.TYPE;
            Class[] clsArr3 = clsArr2;
            clsArr3[2] = String.class;
            Method checkOpNoThrowMethod = appOpsClass.getMethod(CHECK_OP_NO_THROW, clsArr3);
            Object[] objArr = new Object[3];
            objArr[0] = Integer.valueOf(((Integer) appOpsClass.getDeclaredField(OP_POST_NOTIFICATION).get(Integer.class)).intValue());
            Object[] objArr2 = objArr;
            objArr2[1] = Integer.valueOf(uid);
            Object[] objArr3 = objArr2;
            objArr3[2] = pkg;
            return ((Integer) checkOpNoThrowMethod.invoke(appOps, objArr3)).intValue() == 0;
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException | NoSuchMethodException | RuntimeException | InvocationTargetException e) {
            Object obj = e;
            return true;
        }
    }

    public int getImportance() {
        if (Build.VERSION.SDK_INT >= 24) {
            return this.mNotificationManager.getImportance();
        }
        return -1000;
    }

    /* JADX INFO: finally extract failed */
    @NonNull
    public static Set<String> getEnabledListenerPackages(@NonNull Context context) {
        Set<String> set;
        String enabledNotificationListeners = Settings.Secure.getString(context.getContentResolver(), SETTING_ENABLED_NOTIFICATION_LISTENERS);
        Object obj = sEnabledNotificationListenersLock;
        Object obj2 = obj;
        synchronized (obj) {
            if (enabledNotificationListeners != null) {
                try {
                    if (!enabledNotificationListeners.equals(sEnabledNotificationListeners)) {
                        String[] components = enabledNotificationListeners.split(":", -1);
                        new HashSet(components.length);
                        Set<String> packageNames = set;
                        String[] strArr = components;
                        int length = strArr.length;
                        for (int i = 0; i < length; i++) {
                            ComponentName componentName = ComponentName.unflattenFromString(strArr[i]);
                            if (componentName != null) {
                                boolean add = packageNames.add(componentName.getPackageName());
                            }
                        }
                        sEnabledNotificationListenerPackages = packageNames;
                        sEnabledNotificationListeners = enabledNotificationListeners;
                    }
                } catch (Throwable th) {
                    Throwable th2 = th;
                    Object obj3 = obj2;
                    throw th2;
                }
            }
            Set<String> set2 = sEnabledNotificationListenerPackages;
            return set2;
        }
    }

    private static boolean useSideChannelForNotification(Notification notification) {
        Bundle extras = NotificationCompat.getExtras(notification);
        return extras != null && extras.getBoolean(EXTRA_USE_SIDE_CHANNEL);
    }

    private void pushSideChannelQueue(Task task) {
        SideChannelManager sideChannelManager;
        Task task2 = task;
        Object obj = sLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                if (sSideChannelManager == null) {
                    new SideChannelManager(this.mContext.getApplicationContext());
                    sSideChannelManager = sideChannelManager;
                }
                sSideChannelManager.queueTask(task2);
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj3 = obj2;
                throw th2;
            }
        }
    }

    /* renamed from: android.support.v4.app.NotificationManagerCompat$SideChannelManager */
    private static class SideChannelManager implements Handler.Callback, ServiceConnection {
        private static final int MSG_QUEUE_TASK = 0;
        private static final int MSG_RETRY_LISTENER_QUEUE = 3;
        private static final int MSG_SERVICE_CONNECTED = 1;
        private static final int MSG_SERVICE_DISCONNECTED = 2;
        private Set<String> mCachedEnabledPackages;
        private final Context mContext;
        private final Handler mHandler;
        private final HandlerThread mHandlerThread;
        private final Map<ComponentName, ListenerRecord> mRecordMap;

        SideChannelManager(Context context) {
            Map<ComponentName, ListenerRecord> map;
            Set<String> set;
            HandlerThread handlerThread;
            Handler handler;
            new HashMap();
            this.mRecordMap = map;
            new HashSet();
            this.mCachedEnabledPackages = set;
            this.mContext = context;
            new HandlerThread("NotificationManagerCompat");
            this.mHandlerThread = handlerThread;
            this.mHandlerThread.start();
            new Handler(this.mHandlerThread.getLooper(), this);
            this.mHandler = handler;
        }

        public void queueTask(Task task) {
            this.mHandler.obtainMessage(0, task).sendToTarget();
        }

        public boolean handleMessage(Message message) {
            Message msg = message;
            switch (msg.what) {
                case 0:
                    handleQueueTask((Task) msg.obj);
                    return true;
                case 1:
                    ServiceConnectedEvent event = (ServiceConnectedEvent) msg.obj;
                    handleServiceConnected(event.componentName, event.iBinder);
                    return true;
                case 2:
                    handleServiceDisconnected((ComponentName) msg.obj);
                    return true;
                case 3:
                    handleRetryListenerQueue((ComponentName) msg.obj);
                    return true;
                default:
                    return false;
            }
        }

        private void handleQueueTask(Task task) {
            Task task2 = task;
            updateListenerMap();
            for (ListenerRecord record : this.mRecordMap.values()) {
                boolean add = record.taskQueue.add(task2);
                processListenerQueue(record);
            }
        }

        private void handleServiceConnected(ComponentName componentName, IBinder iBinder) {
            IBinder iBinder2 = iBinder;
            ListenerRecord record = this.mRecordMap.get(componentName);
            if (record != null) {
                record.service = INotificationSideChannel.Stub.asInterface(iBinder2);
                record.retryCount = 0;
                processListenerQueue(record);
            }
        }

        private void handleServiceDisconnected(ComponentName componentName) {
            ListenerRecord record = this.mRecordMap.get(componentName);
            if (record != null) {
                ensureServiceUnbound(record);
            }
        }

        private void handleRetryListenerQueue(ComponentName componentName) {
            ListenerRecord record = this.mRecordMap.get(componentName);
            if (record != null) {
                processListenerQueue(record);
            }
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Object obj;
            StringBuilder sb;
            ComponentName componentName2 = componentName;
            IBinder iBinder2 = iBinder;
            if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                new StringBuilder();
                int d = Log.d(NotificationManagerCompat.TAG, sb.append("Connected to service ").append(componentName2).toString());
            }
            new ServiceConnectedEvent(componentName2, iBinder2);
            this.mHandler.obtainMessage(1, obj).sendToTarget();
        }

        public void onServiceDisconnected(ComponentName componentName) {
            StringBuilder sb;
            ComponentName componentName2 = componentName;
            if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                new StringBuilder();
                int d = Log.d(NotificationManagerCompat.TAG, sb.append("Disconnected from service ").append(componentName2).toString());
            }
            this.mHandler.obtainMessage(2, componentName2).sendToTarget();
        }

        private void updateListenerMap() {
            Intent intent;
            Set<ComponentName> set;
            StringBuilder sb;
            Object obj;
            StringBuilder sb2;
            ComponentName componentName;
            StringBuilder sb3;
            Set<String> enabledPackages = NotificationManagerCompat.getEnabledListenerPackages(this.mContext);
            if (!enabledPackages.equals(this.mCachedEnabledPackages)) {
                this.mCachedEnabledPackages = enabledPackages;
                PackageManager packageManager = this.mContext.getPackageManager();
                new Intent();
                List<ResolveInfo> resolveInfos = packageManager.queryIntentServices(intent.setAction(NotificationManagerCompat.ACTION_BIND_SIDE_CHANNEL), 0);
                new HashSet<>();
                Set<ComponentName> enabledComponents = set;
                for (ResolveInfo resolveInfo : resolveInfos) {
                    if (enabledPackages.contains(resolveInfo.serviceInfo.packageName)) {
                        new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name);
                        ComponentName componentName2 = componentName;
                        if (resolveInfo.serviceInfo.permission != null) {
                            new StringBuilder();
                            int w = Log.w(NotificationManagerCompat.TAG, sb3.append("Permission present on component ").append(componentName2).append(", not adding listener record.").toString());
                        } else {
                            boolean add = enabledComponents.add(componentName2);
                        }
                    }
                }
                for (ComponentName componentName3 : enabledComponents) {
                    if (!this.mRecordMap.containsKey(componentName3)) {
                        if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                            new StringBuilder();
                            int d = Log.d(NotificationManagerCompat.TAG, sb2.append("Adding listener record for ").append(componentName3).toString());
                        }
                        new ListenerRecord(componentName3);
                        ListenerRecord put = this.mRecordMap.put(componentName3, obj);
                    }
                }
                Iterator<Map.Entry<ComponentName, ListenerRecord>> it = this.mRecordMap.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<ComponentName, ListenerRecord> entry = it.next();
                    if (!enabledComponents.contains(entry.getKey())) {
                        if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                            new StringBuilder();
                            int d2 = Log.d(NotificationManagerCompat.TAG, sb.append("Removing listener record for ").append(entry.getKey()).toString());
                        }
                        ensureServiceUnbound(entry.getValue());
                        it.remove();
                    }
                }
            }
        }

        private boolean ensureServiceBound(ListenerRecord listenerRecord) {
            Intent intent;
            StringBuilder sb;
            ListenerRecord record = listenerRecord;
            if (record.bound) {
                return true;
            }
            new Intent(NotificationManagerCompat.ACTION_BIND_SIDE_CHANNEL);
            record.bound = this.mContext.bindService(intent.setComponent(record.componentName), this, 33);
            if (record.bound) {
                record.retryCount = 0;
            } else {
                new StringBuilder();
                int w = Log.w(NotificationManagerCompat.TAG, sb.append("Unable to bind to listener ").append(record.componentName).toString());
                this.mContext.unbindService(this);
            }
            return record.bound;
        }

        private void ensureServiceUnbound(ListenerRecord listenerRecord) {
            ListenerRecord record = listenerRecord;
            if (record.bound) {
                this.mContext.unbindService(this);
                record.bound = false;
            }
            record.service = null;
        }

        private void scheduleListenerRetry(ListenerRecord listenerRecord) {
            StringBuilder sb;
            StringBuilder sb2;
            ListenerRecord record = listenerRecord;
            if (!this.mHandler.hasMessages(3, record.componentName)) {
                record.retryCount++;
                if (record.retryCount > 6) {
                    new StringBuilder();
                    int w = Log.w(NotificationManagerCompat.TAG, sb2.append("Giving up on delivering ").append(record.taskQueue.size()).append(" tasks to ").append(record.componentName).append(" after ").append(record.retryCount).append(" retries").toString());
                    record.taskQueue.clear();
                    return;
                }
                int delayMs = 1000 * (1 << (record.retryCount - 1));
                if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                    new StringBuilder();
                    int d = Log.d(NotificationManagerCompat.TAG, sb.append("Scheduling retry for ").append(delayMs).append(" ms").toString());
                }
                boolean sendMessageDelayed = this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(3, record.componentName), (long) delayMs);
            }
        }

        private void processListenerQueue(ListenerRecord listenerRecord) {
            StringBuilder sb;
            StringBuilder sb2;
            StringBuilder sb3;
            StringBuilder sb4;
            ListenerRecord record = listenerRecord;
            if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                new StringBuilder();
                int d = Log.d(NotificationManagerCompat.TAG, sb4.append("Processing component ").append(record.componentName).append(", ").append(record.taskQueue.size()).append(" queued tasks").toString());
            }
            if (!record.taskQueue.isEmpty()) {
                if (!ensureServiceBound(record) || record.service == null) {
                    scheduleListenerRetry(record);
                    return;
                }
                while (true) {
                    Task task = record.taskQueue.peek();
                    if (task == null) {
                        break;
                    }
                    try {
                        if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                            new StringBuilder();
                            int d2 = Log.d(NotificationManagerCompat.TAG, sb3.append("Sending task ").append(task).toString());
                        }
                        task.send(record.service);
                        Task remove = record.taskQueue.remove();
                    } catch (DeadObjectException e) {
                        DeadObjectException deadObjectException = e;
                        if (Log.isLoggable(NotificationManagerCompat.TAG, 3)) {
                            new StringBuilder();
                            int d3 = Log.d(NotificationManagerCompat.TAG, sb2.append("Remote service has died: ").append(record.componentName).toString());
                        }
                    } catch (RemoteException e2) {
                        new StringBuilder();
                        int w = Log.w(NotificationManagerCompat.TAG, sb.append("RemoteException communicating with ").append(record.componentName).toString(), e2);
                    }
                }
                if (!record.taskQueue.isEmpty()) {
                    scheduleListenerRetry(record);
                }
            }
        }

        /* renamed from: android.support.v4.app.NotificationManagerCompat$SideChannelManager$ListenerRecord */
        private static class ListenerRecord {
            boolean bound = false;
            final ComponentName componentName;
            int retryCount;
            INotificationSideChannel service;
            ArrayDeque<Task> taskQueue;

            ListenerRecord(ComponentName componentName2) {
                ArrayDeque<Task> arrayDeque;
                new ArrayDeque<>();
                this.taskQueue = arrayDeque;
                this.retryCount = 0;
                this.componentName = componentName2;
            }
        }
    }

    /* renamed from: android.support.v4.app.NotificationManagerCompat$ServiceConnectedEvent */
    private static class ServiceConnectedEvent {
        final ComponentName componentName;
        final IBinder iBinder;

        ServiceConnectedEvent(ComponentName componentName2, IBinder iBinder2) {
            this.componentName = componentName2;
            this.iBinder = iBinder2;
        }
    }

    /* renamed from: android.support.v4.app.NotificationManagerCompat$NotifyTask */
    private static class NotifyTask implements Task {

        /* renamed from: id */
        final int f25id;
        final Notification notif;
        final String packageName;
        final String tag;

        NotifyTask(String packageName2, int id, String tag2, Notification notif2) {
            this.packageName = packageName2;
            this.f25id = id;
            this.tag = tag2;
            this.notif = notif2;
        }

        public void send(INotificationSideChannel service) throws RemoteException {
            service.notify(this.packageName, this.f25id, this.tag, this.notif);
        }

        public String toString() {
            StringBuilder sb;
            new StringBuilder("NotifyTask[");
            StringBuilder sb2 = sb;
            StringBuilder append = sb2.append("packageName:").append(this.packageName);
            StringBuilder append2 = sb2.append(", id:").append(this.f25id);
            StringBuilder append3 = sb2.append(", tag:").append(this.tag);
            StringBuilder append4 = sb2.append("]");
            return sb2.toString();
        }
    }

    /* renamed from: android.support.v4.app.NotificationManagerCompat$CancelTask */
    private static class CancelTask implements Task {
        final boolean all;

        /* renamed from: id */
        final int f24id;
        final String packageName;
        final String tag;

        CancelTask(String packageName2) {
            this.packageName = packageName2;
            this.f24id = 0;
            this.tag = null;
            this.all = true;
        }

        CancelTask(String packageName2, int id, String tag2) {
            this.packageName = packageName2;
            this.f24id = id;
            this.tag = tag2;
            this.all = false;
        }

        public void send(INotificationSideChannel iNotificationSideChannel) throws RemoteException {
            INotificationSideChannel service = iNotificationSideChannel;
            if (this.all) {
                service.cancelAll(this.packageName);
            } else {
                service.cancel(this.packageName, this.f24id, this.tag);
            }
        }

        public String toString() {
            StringBuilder sb;
            new StringBuilder("CancelTask[");
            StringBuilder sb2 = sb;
            StringBuilder append = sb2.append("packageName:").append(this.packageName);
            StringBuilder append2 = sb2.append(", id:").append(this.f24id);
            StringBuilder append3 = sb2.append(", tag:").append(this.tag);
            StringBuilder append4 = sb2.append(", all:").append(this.all);
            StringBuilder append5 = sb2.append("]");
            return sb2.toString();
        }
    }
}

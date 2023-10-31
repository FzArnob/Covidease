package android.support.p000v4.app;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.p000v4.app.INotificationSideChannel;

/* renamed from: android.support.v4.app.NotificationCompatSideChannelService */
public abstract class NotificationCompatSideChannelService extends Service {
    public abstract void cancel(String str, int i, String str2);

    public abstract void cancelAll(String str);

    public abstract void notify(String str, int i, String str2, Notification notification);

    public NotificationCompatSideChannelService() {
    }

    public IBinder onBind(Intent intent) {
        IBinder iBinder;
        if (!intent.getAction().equals(NotificationManagerCompat.ACTION_BIND_SIDE_CHANNEL)) {
            return null;
        }
        if (Build.VERSION.SDK_INT > 19) {
            return null;
        }
        new NotificationSideChannelStub(this);
        return iBinder;
    }

    /* renamed from: android.support.v4.app.NotificationCompatSideChannelService$NotificationSideChannelStub */
    private class NotificationSideChannelStub extends INotificationSideChannel.Stub {
        final /* synthetic */ NotificationCompatSideChannelService this$0;

        NotificationSideChannelStub(NotificationCompatSideChannelService notificationCompatSideChannelService) {
            this.this$0 = notificationCompatSideChannelService;
        }

        public void notify(String str, int i, String str2, Notification notification) throws RemoteException {
            String packageName = str;
            int id = i;
            String tag = str2;
            Notification notification2 = notification;
            this.this$0.checkPermission(getCallingUid(), packageName);
            long idToken = clearCallingIdentity();
            try {
                this.this$0.notify(packageName, id, tag, notification2);
                restoreCallingIdentity(idToken);
            } catch (Throwable th) {
                Throwable th2 = th;
                restoreCallingIdentity(idToken);
                throw th2;
            }
        }

        public void cancel(String str, int i, String str2) throws RemoteException {
            String packageName = str;
            int id = i;
            String tag = str2;
            this.this$0.checkPermission(getCallingUid(), packageName);
            long idToken = clearCallingIdentity();
            try {
                this.this$0.cancel(packageName, id, tag);
                restoreCallingIdentity(idToken);
            } catch (Throwable th) {
                Throwable th2 = th;
                restoreCallingIdentity(idToken);
                throw th2;
            }
        }

        public void cancelAll(String str) {
            String packageName = str;
            this.this$0.checkPermission(getCallingUid(), packageName);
            long idToken = clearCallingIdentity();
            try {
                this.this$0.cancelAll(packageName);
                restoreCallingIdentity(idToken);
            } catch (Throwable th) {
                Throwable th2 = th;
                restoreCallingIdentity(idToken);
                throw th2;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void checkPermission(int i, String str) {
        Throwable th;
        StringBuilder sb;
        int callingUid = i;
        String packageName = str;
        String[] packagesForUid = getPackageManager().getPackagesForUid(callingUid);
        int length = packagesForUid.length;
        int i2 = 0;
        while (i2 < length) {
            if (!packagesForUid[i2].equals(packageName)) {
                i2++;
            } else {
                return;
            }
        }
        Throwable th2 = th;
        new StringBuilder();
        new SecurityException(sb.append("NotificationSideChannelService: Uid ").append(callingUid).append(" is not authorized for package ").append(packageName).toString());
        throw th2;
    }
}

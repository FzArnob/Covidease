package android.support.p000v4.media;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.p000v4.media.MediaSessionManager;
import android.support.p000v4.util.ObjectsCompat;
import android.text.TextUtils;
import android.util.Log;

/* renamed from: android.support.v4.media.MediaSessionManagerImplBase */
class MediaSessionManagerImplBase implements MediaSessionManager.MediaSessionManagerImpl {
    private static final boolean DEBUG = MediaSessionManager.DEBUG;
    private static final String ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners";
    private static final String PERMISSION_MEDIA_CONTENT_CONTROL = "android.permission.MEDIA_CONTENT_CONTROL";
    private static final String PERMISSION_STATUS_BAR_SERVICE = "android.permission.STATUS_BAR_SERVICE";
    private static final String TAG = "MediaSessionManager";
    ContentResolver mContentResolver = this.mContext.getContentResolver();
    Context mContext;

    MediaSessionManagerImplBase(Context context) {
        this.mContext = context;
    }

    public Context getContext() {
        return this.mContext;
    }

    public boolean isTrustedForMediaControl(@NonNull MediaSessionManager.RemoteUserInfoImpl remoteUserInfoImpl) {
        StringBuilder sb;
        StringBuilder sb2;
        MediaSessionManager.RemoteUserInfoImpl userInfo = remoteUserInfoImpl;
        try {
            if (this.mContext.getPackageManager().getApplicationInfo(userInfo.getPackageName(), 0).uid != userInfo.getUid()) {
                if (DEBUG) {
                    new StringBuilder();
                    int d = Log.d(TAG, sb2.append("Package name ").append(userInfo.getPackageName()).append(" doesn't match with the uid ").append(userInfo.getUid()).toString());
                }
                return false;
            }
            return isPermissionGranted(userInfo, PERMISSION_STATUS_BAR_SERVICE) || isPermissionGranted(userInfo, PERMISSION_MEDIA_CONTENT_CONTROL) || userInfo.getUid() == 1000 || isEnabledNotificationListener(userInfo);
        } catch (PackageManager.NameNotFoundException e) {
            PackageManager.NameNotFoundException nameNotFoundException = e;
            if (DEBUG) {
                new StringBuilder();
                int d2 = Log.d(TAG, sb.append("Package ").append(userInfo.getPackageName()).append(" doesn't exist").toString());
            }
            return false;
        }
    }

    private boolean isPermissionGranted(MediaSessionManager.RemoteUserInfoImpl remoteUserInfoImpl, String str) {
        MediaSessionManager.RemoteUserInfoImpl userInfo = remoteUserInfoImpl;
        String permission = str;
        if (userInfo.getPid() < 0) {
            return this.mContext.getPackageManager().checkPermission(permission, userInfo.getPackageName()) == 0;
        }
        return this.mContext.checkPermission(permission, userInfo.getPid(), userInfo.getUid()) == 0;
    }

    /* access modifiers changed from: package-private */
    public boolean isEnabledNotificationListener(@NonNull MediaSessionManager.RemoteUserInfoImpl remoteUserInfoImpl) {
        MediaSessionManager.RemoteUserInfoImpl userInfo = remoteUserInfoImpl;
        String enabledNotifListeners = Settings.Secure.getString(this.mContentResolver, ENABLED_NOTIFICATION_LISTENERS);
        if (enabledNotifListeners != null) {
            String[] components = enabledNotifListeners.split(":");
            for (int i = 0; i < components.length; i++) {
                ComponentName component = ComponentName.unflattenFromString(components[i]);
                if (component != null && component.getPackageName().equals(userInfo.getPackageName())) {
                    return true;
                }
            }
        }
        return false;
    }

    /* renamed from: android.support.v4.media.MediaSessionManagerImplBase$RemoteUserInfoImplBase */
    static class RemoteUserInfoImplBase implements MediaSessionManager.RemoteUserInfoImpl {
        private String mPackageName;
        private int mPid;
        private int mUid;

        RemoteUserInfoImplBase(String packageName, int pid, int uid) {
            this.mPackageName = packageName;
            this.mPid = pid;
            this.mUid = uid;
        }

        public String getPackageName() {
            return this.mPackageName;
        }

        public int getPid() {
            return this.mPid;
        }

        public int getUid() {
            return this.mUid;
        }

        public boolean equals(Object obj) {
            Object obj2 = obj;
            if (this == obj2) {
                return true;
            }
            if (!(obj2 instanceof RemoteUserInfoImplBase)) {
                return false;
            }
            RemoteUserInfoImplBase otherUserInfo = (RemoteUserInfoImplBase) obj2;
            return TextUtils.equals(this.mPackageName, otherUserInfo.mPackageName) && this.mPid == otherUserInfo.mPid && this.mUid == otherUserInfo.mUid;
        }

        public int hashCode() {
            Object[] objArr = new Object[3];
            objArr[0] = this.mPackageName;
            Object[] objArr2 = objArr;
            objArr2[1] = Integer.valueOf(this.mPid);
            Object[] objArr3 = objArr2;
            objArr3[2] = Integer.valueOf(this.mUid);
            return ObjectsCompat.hash(objArr3);
        }
    }
}

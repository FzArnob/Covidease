package android.support.p000v4.media;

import android.content.Context;
import android.media.session.MediaSessionManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.p000v4.media.MediaSessionManagerImplApi28;
import android.support.p000v4.media.MediaSessionManagerImplBase;
import android.util.Log;

/* renamed from: android.support.v4.media.MediaSessionManager */
public final class MediaSessionManager {
    static final boolean DEBUG = Log.isLoggable(TAG, 3);
    static final String TAG = "MediaSessionManager";
    private static final Object sLock;
    private static volatile MediaSessionManager sSessionManager;
    MediaSessionManagerImpl mImpl;

    /* renamed from: android.support.v4.media.MediaSessionManager$MediaSessionManagerImpl */
    interface MediaSessionManagerImpl {
        Context getContext();

        boolean isTrustedForMediaControl(RemoteUserInfoImpl remoteUserInfoImpl);
    }

    /* renamed from: android.support.v4.media.MediaSessionManager$RemoteUserInfoImpl */
    interface RemoteUserInfoImpl {
        String getPackageName();

        int getPid();

        int getUid();
    }

    static {
        Object obj;
        new Object();
        sLock = obj;
    }

    @NonNull
    public static MediaSessionManager getSessionManager(@NonNull Context context) {
        MediaSessionManager mediaSessionManager;
        Context context2 = context;
        MediaSessionManager manager = sSessionManager;
        if (manager == null) {
            Object obj = sLock;
            Object obj2 = obj;
            synchronized (obj) {
                try {
                    manager = sSessionManager;
                    if (manager == null) {
                        new MediaSessionManager(context2.getApplicationContext());
                        sSessionManager = mediaSessionManager;
                        manager = sSessionManager;
                    }
                } catch (Throwable th) {
                    Throwable th2 = th;
                    Object obj3 = obj2;
                    throw th2;
                }
            }
        }
        return manager;
    }

    private MediaSessionManager(Context context) {
        MediaSessionManagerImpl mediaSessionManagerImpl;
        MediaSessionManagerImpl mediaSessionManagerImpl2;
        MediaSessionManagerImpl mediaSessionManagerImpl3;
        Context context2 = context;
        if (Build.VERSION.SDK_INT >= 28) {
            new MediaSessionManagerImplApi28(context2);
            this.mImpl = mediaSessionManagerImpl3;
        } else if (Build.VERSION.SDK_INT >= 21) {
            new MediaSessionManagerImplApi21(context2);
            this.mImpl = mediaSessionManagerImpl2;
        } else {
            new MediaSessionManagerImplBase(context2);
            this.mImpl = mediaSessionManagerImpl;
        }
    }

    public boolean isTrustedForMediaControl(@NonNull RemoteUserInfo remoteUserInfo) {
        Throwable th;
        RemoteUserInfo userInfo = remoteUserInfo;
        if (userInfo != null) {
            return this.mImpl.isTrustedForMediaControl(userInfo.mImpl);
        }
        Throwable th2 = th;
        new IllegalArgumentException("userInfo should not be null");
        throw th2;
    }

    /* access modifiers changed from: package-private */
    public Context getContext() {
        return this.mImpl.getContext();
    }

    /* renamed from: android.support.v4.media.MediaSessionManager$RemoteUserInfo */
    public static final class RemoteUserInfo {
        public static final String LEGACY_CONTROLLER = "android.media.session.MediaController";
        RemoteUserInfoImpl mImpl;

        public RemoteUserInfo(@NonNull String str, int i, int i2) {
            RemoteUserInfoImpl remoteUserInfoImpl;
            RemoteUserInfoImpl remoteUserInfoImpl2;
            String packageName = str;
            int pid = i;
            int uid = i2;
            if (Build.VERSION.SDK_INT >= 28) {
                new MediaSessionManagerImplApi28.RemoteUserInfoImplApi28(packageName, pid, uid);
                this.mImpl = remoteUserInfoImpl2;
                return;
            }
            new MediaSessionManagerImplBase.RemoteUserInfoImplBase(packageName, pid, uid);
            this.mImpl = remoteUserInfoImpl;
        }

        @RequiresApi(28)
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public RemoteUserInfo(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
            RemoteUserInfoImpl remoteUserInfoImpl;
            new MediaSessionManagerImplApi28.RemoteUserInfoImplApi28(remoteUserInfo);
            this.mImpl = remoteUserInfoImpl;
        }

        @NonNull
        public String getPackageName() {
            return this.mImpl.getPackageName();
        }

        public int getPid() {
            return this.mImpl.getPid();
        }

        public int getUid() {
            return this.mImpl.getUid();
        }

        public boolean equals(@Nullable Object obj) {
            Object obj2 = obj;
            if (this == obj2) {
                return true;
            }
            if (!(obj2 instanceof RemoteUserInfo)) {
                return false;
            }
            return this.mImpl.equals(((RemoteUserInfo) obj2).mImpl);
        }

        public int hashCode() {
            return this.mImpl.hashCode();
        }
    }
}

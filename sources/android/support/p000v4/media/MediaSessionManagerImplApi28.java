package android.support.p000v4.media;

import android.media.session.MediaSessionManager;
import android.support.annotation.RequiresApi;
import android.support.p000v4.media.MediaSessionManager;
import android.support.p000v4.util.ObjectsCompat;

@RequiresApi(28)
/* renamed from: android.support.v4.media.MediaSessionManagerImplApi28 */
class MediaSessionManagerImplApi28 extends MediaSessionManagerImplApi21 {
    MediaSessionManager mObject;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    MediaSessionManagerImplApi28(android.content.Context r6) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r2 = r0
            r3 = r1
            r2.<init>(r3)
            r2 = r0
            r3 = r1
            java.lang.String r4 = "media_session"
            java.lang.Object r3 = r3.getSystemService(r4)
            android.media.session.MediaSessionManager r3 = (android.media.session.MediaSessionManager) r3
            r2.mObject = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.media.MediaSessionManagerImplApi28.<init>(android.content.Context):void");
    }

    public boolean isTrustedForMediaControl(MediaSessionManager.RemoteUserInfoImpl remoteUserInfoImpl) {
        MediaSessionManager.RemoteUserInfoImpl userInfo = remoteUserInfoImpl;
        if (userInfo instanceof RemoteUserInfoImplApi28) {
            return this.mObject.isTrustedForMediaControl(((RemoteUserInfoImplApi28) userInfo).mObject);
        }
        return false;
    }

    /* renamed from: android.support.v4.media.MediaSessionManagerImplApi28$RemoteUserInfoImplApi28 */
    static final class RemoteUserInfoImplApi28 implements MediaSessionManager.RemoteUserInfoImpl {
        final MediaSessionManager.RemoteUserInfo mObject;

        RemoteUserInfoImplApi28(String packageName, int pid, int uid) {
            MediaSessionManager.RemoteUserInfo remoteUserInfo;
            new MediaSessionManager.RemoteUserInfo(packageName, pid, uid);
            this.mObject = remoteUserInfo;
        }

        RemoteUserInfoImplApi28(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
            this.mObject = remoteUserInfo;
        }

        public String getPackageName() {
            return this.mObject.getPackageName();
        }

        public int getPid() {
            return this.mObject.getPid();
        }

        public int getUid() {
            return this.mObject.getUid();
        }

        public int hashCode() {
            return ObjectsCompat.hash(this.mObject);
        }

        public boolean equals(Object obj) {
            Object obj2 = obj;
            if (this == obj2) {
                return true;
            }
            if (!(obj2 instanceof RemoteUserInfoImplApi28)) {
                return false;
            }
            return this.mObject.equals(((RemoteUserInfoImplApi28) obj2).mObject);
        }
    }
}

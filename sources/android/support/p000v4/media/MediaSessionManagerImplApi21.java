package android.support.p000v4.media;

import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.p000v4.media.MediaSessionManager;

@RequiresApi(21)
/* renamed from: android.support.v4.media.MediaSessionManagerImplApi21 */
class MediaSessionManagerImplApi21 extends MediaSessionManagerImplBase {
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    MediaSessionManagerImplApi21(android.content.Context r5) {
        /*
            r4 = this;
            r0 = r4
            r1 = r5
            r2 = r0
            r3 = r1
            r2.<init>(r3)
            r2 = r0
            r3 = r1
            r2.mContext = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.media.MediaSessionManagerImplApi21.<init>(android.content.Context):void");
    }

    public boolean isTrustedForMediaControl(@NonNull MediaSessionManager.RemoteUserInfoImpl remoteUserInfoImpl) {
        MediaSessionManager.RemoteUserInfoImpl userInfo = remoteUserInfoImpl;
        return hasMediaControlPermission(userInfo) || super.isTrustedForMediaControl(userInfo);
    }

    private boolean hasMediaControlPermission(@NonNull MediaSessionManager.RemoteUserInfoImpl remoteUserInfoImpl) {
        MediaSessionManager.RemoteUserInfoImpl userInfo = remoteUserInfoImpl;
        return getContext().checkPermission("android.permission.MEDIA_CONTENT_CONTROL", userInfo.getPid(), userInfo.getUid()) == 0;
    }
}

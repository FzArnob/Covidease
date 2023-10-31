package android.support.p000v4.media.session;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.p000v4.media.session.MediaSessionCompatApi21;

@RequiresApi(23)
/* renamed from: android.support.v4.media.session.MediaSessionCompatApi23 */
class MediaSessionCompatApi23 {

    /* renamed from: android.support.v4.media.session.MediaSessionCompatApi23$Callback */
    public interface Callback extends MediaSessionCompatApi21.Callback {
        void onPlayFromUri(Uri uri, Bundle bundle);
    }

    public static Object createCallback(Callback callback) {
        Object obj;
        new CallbackProxy(callback);
        return obj;
    }

    /* renamed from: android.support.v4.media.session.MediaSessionCompatApi23$CallbackProxy */
    static class CallbackProxy<T extends Callback> extends MediaSessionCompatApi21.CallbackProxy<T> {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public CallbackProxy(T callback) {
            super(callback);
        }

        public void onPlayFromUri(Uri uri, Bundle bundle) {
            Bundle extras = bundle;
            MediaSessionCompat.ensureClassLoader(extras);
            ((Callback) this.mCallback).onPlayFromUri(uri, extras);
        }
    }

    private MediaSessionCompatApi23() {
    }
}

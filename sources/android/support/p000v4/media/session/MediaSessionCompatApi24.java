package android.support.p000v4.media.session;

import android.media.session.MediaSession;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.p000v4.media.session.MediaSessionCompatApi23;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;

@RequiresApi(24)
/* renamed from: android.support.v4.media.session.MediaSessionCompatApi24 */
class MediaSessionCompatApi24 {
    private static final String TAG = "MediaSessionCompatApi24";

    /* renamed from: android.support.v4.media.session.MediaSessionCompatApi24$Callback */
    public interface Callback extends MediaSessionCompatApi23.Callback {
        void onPrepare();

        void onPrepareFromMediaId(String str, Bundle bundle);

        void onPrepareFromSearch(String str, Bundle bundle);

        void onPrepareFromUri(Uri uri, Bundle bundle);
    }

    public static Object createCallback(Callback callback) {
        Object obj;
        new CallbackProxy(callback);
        return obj;
    }

    public static String getCallingPackage(Object sessionObj) {
        MediaSession session = (MediaSession) sessionObj;
        try {
            return (String) session.getClass().getMethod("getCallingPackage", new Class[0]).invoke(session, new Object[0]);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            int e2 = Log.e(TAG, "Cannot execute MediaSession.getCallingPackage()", e);
            return null;
        }
    }

    /* renamed from: android.support.v4.media.session.MediaSessionCompatApi24$CallbackProxy */
    static class CallbackProxy<T extends Callback> extends MediaSessionCompatApi23.CallbackProxy<T> {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public CallbackProxy(T callback) {
            super(callback);
        }

        public void onPrepare() {
            ((Callback) this.mCallback).onPrepare();
        }

        public void onPrepareFromMediaId(String mediaId, Bundle bundle) {
            Bundle extras = bundle;
            MediaSessionCompat.ensureClassLoader(extras);
            ((Callback) this.mCallback).onPrepareFromMediaId(mediaId, extras);
        }

        public void onPrepareFromSearch(String query, Bundle bundle) {
            Bundle extras = bundle;
            MediaSessionCompat.ensureClassLoader(extras);
            ((Callback) this.mCallback).onPrepareFromSearch(query, extras);
        }

        public void onPrepareFromUri(Uri uri, Bundle bundle) {
            Bundle extras = bundle;
            MediaSessionCompat.ensureClassLoader(extras);
            ((Callback) this.mCallback).onPrepareFromUri(uri, extras);
        }
    }

    private MediaSessionCompatApi24() {
    }
}

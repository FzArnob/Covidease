package android.support.p000v4.media;

import android.media.browse.MediaBrowser;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.p000v4.media.MediaBrowserCompatApi21;
import android.support.p000v4.media.session.MediaSessionCompat;
import java.util.List;

@RequiresApi(26)
/* renamed from: android.support.v4.media.MediaBrowserCompatApi26 */
class MediaBrowserCompatApi26 {

    /* renamed from: android.support.v4.media.MediaBrowserCompatApi26$SubscriptionCallback */
    interface SubscriptionCallback extends MediaBrowserCompatApi21.SubscriptionCallback {
        void onChildrenLoaded(@NonNull String str, List<?> list, @NonNull Bundle bundle);

        void onError(@NonNull String str, @NonNull Bundle bundle);
    }

    static Object createSubscriptionCallback(SubscriptionCallback callback) {
        Object obj;
        new SubscriptionCallbackProxy(callback);
        return obj;
    }

    public static void subscribe(Object browserObj, String parentId, Bundle options, Object subscriptionCallbackObj) {
        ((MediaBrowser) browserObj).subscribe(parentId, options, (MediaBrowser.SubscriptionCallback) subscriptionCallbackObj);
    }

    public static void unsubscribe(Object browserObj, String parentId, Object subscriptionCallbackObj) {
        ((MediaBrowser) browserObj).unsubscribe(parentId, (MediaBrowser.SubscriptionCallback) subscriptionCallbackObj);
    }

    /* renamed from: android.support.v4.media.MediaBrowserCompatApi26$SubscriptionCallbackProxy */
    static class SubscriptionCallbackProxy<T extends SubscriptionCallback> extends MediaBrowserCompatApi21.SubscriptionCallbackProxy<T> {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        SubscriptionCallbackProxy(T callback) {
            super(callback);
        }

        public void onChildrenLoaded(@NonNull String parentId, List<MediaBrowser.MediaItem> children, @NonNull Bundle bundle) {
            Bundle options = bundle;
            MediaSessionCompat.ensureClassLoader(options);
            ((SubscriptionCallback) this.mSubscriptionCallback).onChildrenLoaded(parentId, children, options);
        }

        public void onError(@NonNull String parentId, @NonNull Bundle bundle) {
            Bundle options = bundle;
            MediaSessionCompat.ensureClassLoader(options);
            ((SubscriptionCallback) this.mSubscriptionCallback).onError(parentId, options);
        }
    }

    private MediaBrowserCompatApi26() {
    }
}

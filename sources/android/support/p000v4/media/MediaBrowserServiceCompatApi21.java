package android.support.p000v4.media;

import android.content.Context;
import android.content.Intent;
import android.media.browse.MediaBrowser;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.service.media.MediaBrowserService;
import android.support.annotation.RequiresApi;
import android.support.p000v4.media.session.MediaSessionCompat;
import java.util.ArrayList;
import java.util.List;

@RequiresApi(21)
/* renamed from: android.support.v4.media.MediaBrowserServiceCompatApi21 */
class MediaBrowserServiceCompatApi21 {

    /* renamed from: android.support.v4.media.MediaBrowserServiceCompatApi21$ServiceCompatProxy */
    public interface ServiceCompatProxy {
        BrowserRoot onGetRoot(String str, int i, Bundle bundle);

        void onLoadChildren(String str, ResultWrapper<List<Parcel>> resultWrapper);
    }

    public static Object createService(Context context, ServiceCompatProxy serviceProxy) {
        Context context2;
        new MediaBrowserServiceAdaptor(context, serviceProxy);
        return context2;
    }

    public static void onCreate(Object serviceObj) {
        ((MediaBrowserService) serviceObj).onCreate();
    }

    public static IBinder onBind(Object serviceObj, Intent intent) {
        return ((MediaBrowserService) serviceObj).onBind(intent);
    }

    public static void setSessionToken(Object serviceObj, Object token) {
        ((MediaBrowserService) serviceObj).setSessionToken((MediaSession.Token) token);
    }

    public static void notifyChildrenChanged(Object serviceObj, String parentId) {
        ((MediaBrowserService) serviceObj).notifyChildrenChanged(parentId);
    }

    /* renamed from: android.support.v4.media.MediaBrowserServiceCompatApi21$ResultWrapper */
    static class ResultWrapper<T> {
        MediaBrowserService.Result mResultObj;

        ResultWrapper(MediaBrowserService.Result result) {
            this.mResultObj = result;
        }

        public void sendResult(T t) {
            T result = t;
            if (result instanceof List) {
                this.mResultObj.sendResult(parcelListToItemList((List) result));
            } else if (result instanceof Parcel) {
                Parcel parcel = (Parcel) result;
                parcel.setDataPosition(0);
                this.mResultObj.sendResult(MediaBrowser.MediaItem.CREATOR.createFromParcel(parcel));
                parcel.recycle();
            } else {
                this.mResultObj.sendResult((Object) null);
            }
        }

        public void detach() {
            this.mResultObj.detach();
        }

        /* access modifiers changed from: package-private */
        public List<MediaBrowser.MediaItem> parcelListToItemList(List<Parcel> list) {
            List<MediaBrowser.MediaItem> list2;
            List<Parcel> parcelList = list;
            if (parcelList == null) {
                return null;
            }
            new ArrayList();
            List<MediaBrowser.MediaItem> items = list2;
            for (Parcel parcel : parcelList) {
                parcel.setDataPosition(0);
                boolean add = items.add(MediaBrowser.MediaItem.CREATOR.createFromParcel(parcel));
                parcel.recycle();
            }
            return items;
        }
    }

    /* renamed from: android.support.v4.media.MediaBrowserServiceCompatApi21$BrowserRoot */
    static class BrowserRoot {
        final Bundle mExtras;
        final String mRootId;

        BrowserRoot(String rootId, Bundle extras) {
            this.mRootId = rootId;
            this.mExtras = extras;
        }
    }

    /* renamed from: android.support.v4.media.MediaBrowserServiceCompatApi21$MediaBrowserServiceAdaptor */
    static class MediaBrowserServiceAdaptor extends MediaBrowserService {
        final ServiceCompatProxy mServiceProxy;

        MediaBrowserServiceAdaptor(Context context, ServiceCompatProxy serviceWrapper) {
            attachBaseContext(context);
            this.mServiceProxy = serviceWrapper;
        }

        public MediaBrowserService.BrowserRoot onGetRoot(String clientPackageName, int clientUid, Bundle bundle) {
            Bundle bundle2;
            Bundle bundle3;
            MediaBrowserService.BrowserRoot browserRoot;
            MediaBrowserService.BrowserRoot browserRoot2;
            Bundle rootHints = bundle;
            MediaSessionCompat.ensureClassLoader(rootHints);
            ServiceCompatProxy serviceCompatProxy = this.mServiceProxy;
            String str = clientPackageName;
            int i = clientUid;
            if (rootHints == null) {
                bundle3 = null;
            } else {
                bundle3 = bundle2;
                new Bundle(rootHints);
            }
            BrowserRoot browserRoot3 = serviceCompatProxy.onGetRoot(str, i, bundle3);
            if (browserRoot3 == null) {
                browserRoot2 = null;
            } else {
                browserRoot2 = browserRoot;
                new MediaBrowserService.BrowserRoot(browserRoot3.mRootId, browserRoot3.mExtras);
            }
            return browserRoot2;
        }

        public void onLoadChildren(String parentId, MediaBrowserService.Result<List<MediaBrowser.MediaItem>> result) {
            ResultWrapper resultWrapper;
            new ResultWrapper(result);
            this.mServiceProxy.onLoadChildren(parentId, resultWrapper);
        }
    }

    private MediaBrowserServiceCompatApi21() {
    }
}

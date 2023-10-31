package android.support.p000v4.media;

import android.content.Context;
import android.media.browse.MediaBrowser;
import android.os.Parcel;
import android.service.media.MediaBrowserService;
import android.support.annotation.RequiresApi;
import android.support.p000v4.media.MediaBrowserServiceCompatApi21;

@RequiresApi(23)
/* renamed from: android.support.v4.media.MediaBrowserServiceCompatApi23 */
class MediaBrowserServiceCompatApi23 {

    /* renamed from: android.support.v4.media.MediaBrowserServiceCompatApi23$ServiceCompatProxy */
    public interface ServiceCompatProxy extends MediaBrowserServiceCompatApi21.ServiceCompatProxy {
        void onLoadItem(String str, MediaBrowserServiceCompatApi21.ResultWrapper<Parcel> resultWrapper);
    }

    public static Object createService(Context context, ServiceCompatProxy serviceProxy) {
        Context context2;
        new MediaBrowserServiceAdaptor(context, serviceProxy);
        return context2;
    }

    /* renamed from: android.support.v4.media.MediaBrowserServiceCompatApi23$MediaBrowserServiceAdaptor */
    static class MediaBrowserServiceAdaptor extends MediaBrowserServiceCompatApi21.MediaBrowserServiceAdaptor {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        MediaBrowserServiceAdaptor(Context context, ServiceCompatProxy serviceWrapper) {
            super(context, serviceWrapper);
        }

        public void onLoadItem(String itemId, MediaBrowserService.Result<MediaBrowser.MediaItem> result) {
            MediaBrowserServiceCompatApi21.ResultWrapper resultWrapper;
            new MediaBrowserServiceCompatApi21.ResultWrapper(result);
            ((ServiceCompatProxy) this.mServiceProxy).onLoadItem(itemId, resultWrapper);
        }
    }

    private MediaBrowserServiceCompatApi23() {
    }
}

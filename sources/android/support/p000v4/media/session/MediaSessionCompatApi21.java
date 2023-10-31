package android.support.p000v4.media.session;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.MediaDescription;
import android.media.MediaMetadata;
import android.media.Rating;
import android.media.VolumeProvider;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RequiresApi(21)
/* renamed from: android.support.v4.media.session.MediaSessionCompatApi21 */
class MediaSessionCompatApi21 {
    static final String TAG = "MediaSessionCompatApi21";

    /* renamed from: android.support.v4.media.session.MediaSessionCompatApi21$Callback */
    interface Callback {
        void onCommand(String str, Bundle bundle, ResultReceiver resultReceiver);

        void onCustomAction(String str, Bundle bundle);

        void onFastForward();

        boolean onMediaButtonEvent(Intent intent);

        void onPause();

        void onPlay();

        void onPlayFromMediaId(String str, Bundle bundle);

        void onPlayFromSearch(String str, Bundle bundle);

        void onRewind();

        void onSeekTo(long j);

        void onSetRating(Object obj);

        void onSetRating(Object obj, Bundle bundle);

        void onSkipToNext();

        void onSkipToPrevious();

        void onSkipToQueueItem(long j);

        void onStop();
    }

    public static Object createSession(Context context, String tag) {
        Object obj;
        new MediaSession(context, tag);
        return obj;
    }

    public static Object verifySession(Object obj) {
        Throwable th;
        Object mediaSession = obj;
        if (mediaSession instanceof MediaSession) {
            return mediaSession;
        }
        Throwable th2 = th;
        new IllegalArgumentException("mediaSession is not a valid MediaSession object");
        throw th2;
    }

    public static Object verifyToken(Object obj) {
        Throwable th;
        Object token = obj;
        if (token instanceof MediaSession.Token) {
            return token;
        }
        Throwable th2 = th;
        new IllegalArgumentException("token is not a valid MediaSession.Token object");
        throw th2;
    }

    public static Object createCallback(Callback callback) {
        Object obj;
        new CallbackProxy(callback);
        return obj;
    }

    public static void setCallback(Object sessionObj, Object callbackObj, Handler handler) {
        ((MediaSession) sessionObj).setCallback((MediaSession.Callback) callbackObj, handler);
    }

    public static void setFlags(Object sessionObj, int flags) {
        ((MediaSession) sessionObj).setFlags(flags);
    }

    public static void setPlaybackToLocal(Object sessionObj, int stream) {
        AudioAttributes.Builder builder;
        new AudioAttributes.Builder();
        AudioAttributes.Builder bob = builder;
        AudioAttributes.Builder legacyStreamType = bob.setLegacyStreamType(stream);
        ((MediaSession) sessionObj).setPlaybackToLocal(bob.build());
    }

    public static void setPlaybackToRemote(Object sessionObj, Object volumeProviderObj) {
        ((MediaSession) sessionObj).setPlaybackToRemote((VolumeProvider) volumeProviderObj);
    }

    public static void setActive(Object sessionObj, boolean active) {
        ((MediaSession) sessionObj).setActive(active);
    }

    public static boolean isActive(Object sessionObj) {
        return ((MediaSession) sessionObj).isActive();
    }

    public static void sendSessionEvent(Object sessionObj, String event, Bundle extras) {
        ((MediaSession) sessionObj).sendSessionEvent(event, extras);
    }

    public static void release(Object sessionObj) {
        ((MediaSession) sessionObj).release();
    }

    public static Parcelable getSessionToken(Object sessionObj) {
        return ((MediaSession) sessionObj).getSessionToken();
    }

    public static void setPlaybackState(Object sessionObj, Object stateObj) {
        ((MediaSession) sessionObj).setPlaybackState((PlaybackState) stateObj);
    }

    public static void setMetadata(Object sessionObj, Object metadataObj) {
        ((MediaSession) sessionObj).setMetadata((MediaMetadata) metadataObj);
    }

    public static void setSessionActivity(Object sessionObj, PendingIntent pi) {
        ((MediaSession) sessionObj).setSessionActivity(pi);
    }

    public static void setMediaButtonReceiver(Object sessionObj, PendingIntent pi) {
        ((MediaSession) sessionObj).setMediaButtonReceiver(pi);
    }

    public static void setQueue(Object obj, List<Object> list) {
        ArrayList arrayList;
        Object sessionObj = obj;
        List<Object> queueObjs = list;
        if (queueObjs == null) {
            ((MediaSession) sessionObj).setQueue((List) null);
            return;
        }
        new ArrayList();
        ArrayList arrayList2 = arrayList;
        Iterator<Object> it = queueObjs.iterator();
        while (it.hasNext()) {
            boolean add = arrayList2.add((MediaSession.QueueItem) it.next());
        }
        ((MediaSession) sessionObj).setQueue(arrayList2);
    }

    public static void setQueueTitle(Object sessionObj, CharSequence title) {
        ((MediaSession) sessionObj).setQueueTitle(title);
    }

    public static void setExtras(Object sessionObj, Bundle extras) {
        ((MediaSession) sessionObj).setExtras(extras);
    }

    public static boolean hasCallback(Object obj) {
        Object sessionObj = obj;
        try {
            Field callbackField = sessionObj.getClass().getDeclaredField("mCallback");
            if (callbackField != null) {
                callbackField.setAccessible(true);
                return callbackField.get(sessionObj) != null;
            }
        } catch (IllegalAccessException | NoSuchFieldException e) {
            Object obj2 = e;
            int w = Log.w(TAG, "Failed to get mCallback object.");
        }
        return false;
    }

    /* renamed from: android.support.v4.media.session.MediaSessionCompatApi21$CallbackProxy */
    static class CallbackProxy<T extends Callback> extends MediaSession.Callback {
        protected final T mCallback;

        public CallbackProxy(T callback) {
            this.mCallback = callback;
        }

        public void onCommand(String command, Bundle bundle, ResultReceiver cb) {
            Bundle args = bundle;
            MediaSessionCompat.ensureClassLoader(args);
            this.mCallback.onCommand(command, args, cb);
        }

        public boolean onMediaButtonEvent(Intent intent) {
            Intent mediaButtonIntent = intent;
            return this.mCallback.onMediaButtonEvent(mediaButtonIntent) || super.onMediaButtonEvent(mediaButtonIntent);
        }

        public void onPlay() {
            this.mCallback.onPlay();
        }

        public void onPlayFromMediaId(String mediaId, Bundle bundle) {
            Bundle extras = bundle;
            MediaSessionCompat.ensureClassLoader(extras);
            this.mCallback.onPlayFromMediaId(mediaId, extras);
        }

        public void onPlayFromSearch(String search, Bundle bundle) {
            Bundle extras = bundle;
            MediaSessionCompat.ensureClassLoader(extras);
            this.mCallback.onPlayFromSearch(search, extras);
        }

        public void onSkipToQueueItem(long id) {
            this.mCallback.onSkipToQueueItem(id);
        }

        public void onPause() {
            this.mCallback.onPause();
        }

        public void onSkipToNext() {
            this.mCallback.onSkipToNext();
        }

        public void onSkipToPrevious() {
            this.mCallback.onSkipToPrevious();
        }

        public void onFastForward() {
            this.mCallback.onFastForward();
        }

        public void onRewind() {
            this.mCallback.onRewind();
        }

        public void onStop() {
            this.mCallback.onStop();
        }

        public void onSeekTo(long pos) {
            this.mCallback.onSeekTo(pos);
        }

        public void onSetRating(Rating rating) {
            this.mCallback.onSetRating(rating);
        }

        public void onCustomAction(String action, Bundle bundle) {
            Bundle extras = bundle;
            MediaSessionCompat.ensureClassLoader(extras);
            this.mCallback.onCustomAction(action, extras);
        }
    }

    /* renamed from: android.support.v4.media.session.MediaSessionCompatApi21$QueueItem */
    static class QueueItem {
        public static Object createItem(Object mediaDescription, long id) {
            Object mediaDescription2;
            new MediaSession.QueueItem((MediaDescription) mediaDescription, id);
            return mediaDescription2;
        }

        public static Object getDescription(Object queueItem) {
            return ((MediaSession.QueueItem) queueItem).getDescription();
        }

        public static long getQueueId(Object queueItem) {
            return ((MediaSession.QueueItem) queueItem).getQueueId();
        }

        private QueueItem() {
        }
    }

    private MediaSessionCompatApi21() {
    }
}

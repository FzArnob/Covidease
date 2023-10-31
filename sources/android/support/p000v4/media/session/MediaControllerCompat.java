package android.support.p000v4.media.session;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.support.annotation.GuardedBy;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.p000v4.app.BundleCompat;
import android.support.p000v4.app.SupportActivity;
import android.support.p000v4.media.MediaDescriptionCompat;
import android.support.p000v4.media.MediaMetadataCompat;
import android.support.p000v4.media.RatingCompat;
import android.support.p000v4.media.session.IMediaControllerCallback;
import android.support.p000v4.media.session.IMediaSession;
import android.support.p000v4.media.session.MediaControllerCompatApi21;
import android.support.p000v4.media.session.MediaControllerCompatApi23;
import android.support.p000v4.media.session.MediaControllerCompatApi24;
import android.support.p000v4.media.session.MediaSessionCompat;
import android.support.p000v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/* renamed from: android.support.v4.media.session.MediaControllerCompat */
public final class MediaControllerCompat {
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String COMMAND_ADD_QUEUE_ITEM = "android.support.v4.media.session.command.ADD_QUEUE_ITEM";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String COMMAND_ADD_QUEUE_ITEM_AT = "android.support.v4.media.session.command.ADD_QUEUE_ITEM_AT";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String COMMAND_ARGUMENT_INDEX = "android.support.v4.media.session.command.ARGUMENT_INDEX";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String COMMAND_ARGUMENT_MEDIA_DESCRIPTION = "android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String COMMAND_GET_EXTRA_BINDER = "android.support.v4.media.session.command.GET_EXTRA_BINDER";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String COMMAND_REMOVE_QUEUE_ITEM = "android.support.v4.media.session.command.REMOVE_QUEUE_ITEM";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String COMMAND_REMOVE_QUEUE_ITEM_AT = "android.support.v4.media.session.command.REMOVE_QUEUE_ITEM_AT";
    static final String TAG = "MediaControllerCompat";
    private final MediaControllerImpl mImpl;
    private final HashSet<Callback> mRegisteredCallbacks;
    private final MediaSessionCompat.Token mToken;

    /* renamed from: android.support.v4.media.session.MediaControllerCompat$MediaControllerImpl */
    interface MediaControllerImpl {
        void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat);

        void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat, int i);

        void adjustVolume(int i, int i2);

        boolean dispatchMediaButtonEvent(KeyEvent keyEvent);

        Bundle getExtras();

        long getFlags();

        Object getMediaController();

        MediaMetadataCompat getMetadata();

        String getPackageName();

        PlaybackInfo getPlaybackInfo();

        PlaybackStateCompat getPlaybackState();

        List<MediaSessionCompat.QueueItem> getQueue();

        CharSequence getQueueTitle();

        int getRatingType();

        int getRepeatMode();

        PendingIntent getSessionActivity();

        int getShuffleMode();

        TransportControls getTransportControls();

        boolean isCaptioningEnabled();

        boolean isSessionReady();

        void registerCallback(Callback callback, Handler handler);

        void removeQueueItem(MediaDescriptionCompat mediaDescriptionCompat);

        void sendCommand(String str, Bundle bundle, ResultReceiver resultReceiver);

        void setVolumeTo(int i, int i2);

        void unregisterCallback(Callback callback);
    }

    /* renamed from: android.support.v4.media.session.MediaControllerCompat$MediaControllerExtraData */
    private static class MediaControllerExtraData extends SupportActivity.ExtraData {
        private final MediaControllerCompat mMediaController;

        MediaControllerExtraData(MediaControllerCompat mediaController) {
            this.mMediaController = mediaController;
        }

        /* access modifiers changed from: package-private */
        public MediaControllerCompat getMediaController() {
            return this.mMediaController;
        }
    }

    public static void setMediaController(@NonNull Activity activity, MediaControllerCompat mediaControllerCompat) {
        SupportActivity.ExtraData extraData;
        Activity activity2 = activity;
        MediaControllerCompat mediaController = mediaControllerCompat;
        if (activity2 instanceof SupportActivity) {
            new MediaControllerExtraData(mediaController);
            ((SupportActivity) activity2).putExtraData(extraData);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            Object controllerObj = null;
            if (mediaController != null) {
                controllerObj = MediaControllerCompatApi21.fromToken(activity2, mediaController.getSessionToken().getToken());
            }
            MediaControllerCompatApi21.setMediaController(activity2, controllerObj);
        }
    }

    public static MediaControllerCompat getMediaController(@NonNull Activity activity) {
        MediaControllerCompat mediaControllerCompat;
        MediaControllerCompat mediaControllerCompat2;
        Activity activity2 = activity;
        if (activity2 instanceof SupportActivity) {
            MediaControllerExtraData extraData = (MediaControllerExtraData) ((SupportActivity) activity2).getExtraData(MediaControllerExtraData.class);
            if (extraData != null) {
                mediaControllerCompat2 = extraData.getMediaController();
            } else {
                mediaControllerCompat2 = null;
            }
            return mediaControllerCompat2;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            Object controllerObj = MediaControllerCompatApi21.getMediaController(activity2);
            if (controllerObj == null) {
                return null;
            }
            try {
                MediaControllerCompat mediaControllerCompat3 = mediaControllerCompat;
                new MediaControllerCompat((Context) activity2, MediaSessionCompat.Token.fromToken(MediaControllerCompatApi21.getSessionToken(controllerObj)));
                return mediaControllerCompat3;
            } catch (RemoteException e) {
                int e2 = Log.e(TAG, "Dead object in getMediaController.", e);
            }
        }
        return null;
    }

    static void validateCustomAction(String str, Bundle bundle) {
        Throwable th;
        StringBuilder sb;
        String action = str;
        Bundle args = bundle;
        if (action != null) {
            String str2 = action;
            boolean z = true;
            switch (str2.hashCode()) {
                case -1348483723:
                    if (str2.equals(MediaSessionCompat.ACTION_FOLLOW)) {
                        z = false;
                        break;
                    }
                    break;
                case 503011406:
                    if (str2.equals(MediaSessionCompat.ACTION_UNFOLLOW)) {
                        z = true;
                        break;
                    }
                    break;
            }
            switch (z) {
                case false:
                case true:
                    if (args == null || !args.containsKey(MediaSessionCompat.ARGUMENT_MEDIA_ATTRIBUTE)) {
                        Throwable th2 = th;
                        new StringBuilder();
                        new IllegalArgumentException(sb.append("An extra field android.support.v4.media.session.ARGUMENT_MEDIA_ATTRIBUTE is required for this action ").append(action).append(".").toString());
                        throw th2;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public MediaControllerCompat(Context context, @NonNull MediaSessionCompat mediaSessionCompat) {
        HashSet<Callback> hashSet;
        MediaControllerImpl mediaControllerImpl;
        MediaControllerImpl mediaControllerImpl2;
        MediaControllerImpl mediaControllerImpl3;
        MediaControllerImpl mediaControllerImpl4;
        Throwable th;
        Context context2 = context;
        MediaSessionCompat session = mediaSessionCompat;
        new HashSet<>();
        this.mRegisteredCallbacks = hashSet;
        if (session == null) {
            Throwable th2 = th;
            new IllegalArgumentException("session must not be null");
            throw th2;
        }
        this.mToken = session.getSessionToken();
        MediaControllerImpl impl = null;
        try {
            if (Build.VERSION.SDK_INT >= 24) {
                MediaControllerImpl mediaControllerImpl5 = mediaControllerImpl4;
                new MediaControllerImplApi24(context2, this.mToken);
                impl = mediaControllerImpl5;
            } else if (Build.VERSION.SDK_INT >= 23) {
                new MediaControllerImplApi23(context2, this.mToken);
                impl = mediaControllerImpl3;
            } else if (Build.VERSION.SDK_INT >= 21) {
                new MediaControllerImplApi21(context2, this.mToken);
                impl = mediaControllerImpl2;
            } else {
                MediaControllerImpl mediaControllerImpl6 = mediaControllerImpl;
                new MediaControllerImplBase(this.mToken);
                impl = mediaControllerImpl6;
            }
        } catch (RemoteException e) {
            int w = Log.w(TAG, "Failed to create MediaControllerImpl.", e);
        }
        this.mImpl = impl;
    }

    public MediaControllerCompat(Context context, @NonNull MediaSessionCompat.Token token) throws RemoteException {
        HashSet<Callback> hashSet;
        MediaControllerImpl mediaControllerImpl;
        MediaControllerImpl mediaControllerImpl2;
        MediaControllerImpl mediaControllerImpl3;
        MediaControllerImpl mediaControllerImpl4;
        Throwable th;
        Context context2 = context;
        MediaSessionCompat.Token sessionToken = token;
        new HashSet<>();
        this.mRegisteredCallbacks = hashSet;
        if (sessionToken == null) {
            Throwable th2 = th;
            new IllegalArgumentException("sessionToken must not be null");
            throw th2;
        }
        this.mToken = sessionToken;
        if (Build.VERSION.SDK_INT >= 24) {
            new MediaControllerImplApi24(context2, sessionToken);
            this.mImpl = mediaControllerImpl4;
        } else if (Build.VERSION.SDK_INT >= 23) {
            new MediaControllerImplApi23(context2, sessionToken);
            this.mImpl = mediaControllerImpl3;
        } else if (Build.VERSION.SDK_INT >= 21) {
            new MediaControllerImplApi21(context2, sessionToken);
            this.mImpl = mediaControllerImpl2;
        } else {
            new MediaControllerImplBase(sessionToken);
            this.mImpl = mediaControllerImpl;
        }
    }

    public TransportControls getTransportControls() {
        return this.mImpl.getTransportControls();
    }

    public boolean dispatchMediaButtonEvent(KeyEvent keyEvent) {
        Throwable th;
        KeyEvent keyEvent2 = keyEvent;
        if (keyEvent2 != null) {
            return this.mImpl.dispatchMediaButtonEvent(keyEvent2);
        }
        Throwable th2 = th;
        new IllegalArgumentException("KeyEvent may not be null");
        throw th2;
    }

    public PlaybackStateCompat getPlaybackState() {
        return this.mImpl.getPlaybackState();
    }

    public MediaMetadataCompat getMetadata() {
        return this.mImpl.getMetadata();
    }

    public List<MediaSessionCompat.QueueItem> getQueue() {
        return this.mImpl.getQueue();
    }

    public void addQueueItem(MediaDescriptionCompat description) {
        this.mImpl.addQueueItem(description);
    }

    public void addQueueItem(MediaDescriptionCompat description, int index) {
        this.mImpl.addQueueItem(description, index);
    }

    public void removeQueueItem(MediaDescriptionCompat description) {
        this.mImpl.removeQueueItem(description);
    }

    @Deprecated
    public void removeQueueItemAt(int i) {
        MediaSessionCompat.QueueItem item;
        int index = i;
        List<MediaSessionCompat.QueueItem> queue = getQueue();
        if (queue != null && index >= 0 && index < queue.size() && (item = queue.get(index)) != null) {
            removeQueueItem(item.getDescription());
        }
    }

    public CharSequence getQueueTitle() {
        return this.mImpl.getQueueTitle();
    }

    public Bundle getExtras() {
        return this.mImpl.getExtras();
    }

    public int getRatingType() {
        return this.mImpl.getRatingType();
    }

    public boolean isCaptioningEnabled() {
        return this.mImpl.isCaptioningEnabled();
    }

    public int getRepeatMode() {
        return this.mImpl.getRepeatMode();
    }

    public int getShuffleMode() {
        return this.mImpl.getShuffleMode();
    }

    public long getFlags() {
        return this.mImpl.getFlags();
    }

    public PlaybackInfo getPlaybackInfo() {
        return this.mImpl.getPlaybackInfo();
    }

    public PendingIntent getSessionActivity() {
        return this.mImpl.getSessionActivity();
    }

    public MediaSessionCompat.Token getSessionToken() {
        return this.mToken;
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Bundle getSessionToken2Bundle() {
        return this.mToken.getSessionToken2Bundle();
    }

    public void setVolumeTo(int value, int flags) {
        this.mImpl.setVolumeTo(value, flags);
    }

    public void adjustVolume(int direction, int flags) {
        this.mImpl.adjustVolume(direction, flags);
    }

    public void registerCallback(@NonNull Callback callback) {
        registerCallback(callback, (Handler) null);
    }

    public void registerCallback(@NonNull Callback callback, Handler handler) {
        Handler handler2;
        Throwable th;
        Callback callback2 = callback;
        Handler handler3 = handler;
        if (callback2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("callback must not be null");
            throw th2;
        }
        if (handler3 == null) {
            new Handler();
            handler3 = handler2;
        }
        callback2.setHandler(handler3);
        this.mImpl.registerCallback(callback2, handler3);
        boolean add = this.mRegisteredCallbacks.add(callback2);
    }

    public void unregisterCallback(@NonNull Callback callback) {
        Throwable th;
        Callback callback2 = callback;
        if (callback2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("callback must not be null");
            throw th2;
        }
        try {
            boolean remove = this.mRegisteredCallbacks.remove(callback2);
            this.mImpl.unregisterCallback(callback2);
            callback2.setHandler((Handler) null);
        } catch (Throwable th3) {
            Throwable th4 = th3;
            callback2.setHandler((Handler) null);
            throw th4;
        }
    }

    public void sendCommand(@NonNull String str, Bundle bundle, ResultReceiver resultReceiver) {
        Throwable th;
        String command = str;
        Bundle params = bundle;
        ResultReceiver cb = resultReceiver;
        if (TextUtils.isEmpty(command)) {
            Throwable th2 = th;
            new IllegalArgumentException("command must neither be null nor empty");
            throw th2;
        }
        this.mImpl.sendCommand(command, params, cb);
    }

    public boolean isSessionReady() {
        return this.mImpl.isSessionReady();
    }

    public String getPackageName() {
        return this.mImpl.getPackageName();
    }

    public Object getMediaController() {
        return this.mImpl.getMediaController();
    }

    /* renamed from: android.support.v4.media.session.MediaControllerCompat$Callback */
    public static abstract class Callback implements IBinder.DeathRecipient {
        final Object mCallbackObj;
        MessageHandler mHandler;
        IMediaControllerCallback mIControllerCallback;

        public Callback() {
            IMediaControllerCallback iMediaControllerCallback;
            MediaControllerCompatApi21.Callback callback;
            if (Build.VERSION.SDK_INT >= 21) {
                new StubApi21(this);
                this.mCallbackObj = MediaControllerCompatApi21.createCallback(callback);
                return;
            }
            IMediaControllerCallback iMediaControllerCallback2 = iMediaControllerCallback;
            new StubCompat(this);
            IMediaControllerCallback iMediaControllerCallback3 = iMediaControllerCallback2;
            this.mIControllerCallback = iMediaControllerCallback3;
            this.mCallbackObj = iMediaControllerCallback3;
        }

        public void onSessionReady() {
        }

        public void onSessionDestroyed() {
        }

        public void onSessionEvent(String event, Bundle extras) {
        }

        public void onPlaybackStateChanged(PlaybackStateCompat state) {
        }

        public void onMetadataChanged(MediaMetadataCompat metadata) {
        }

        public void onQueueChanged(List<MediaSessionCompat.QueueItem> list) {
        }

        public void onQueueTitleChanged(CharSequence title) {
        }

        public void onExtrasChanged(Bundle extras) {
        }

        public void onAudioInfoChanged(PlaybackInfo info) {
        }

        public void onCaptioningEnabledChanged(boolean enabled) {
        }

        public void onRepeatModeChanged(int repeatMode) {
        }

        public void onShuffleModeChanged(int shuffleMode) {
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY})
        public IMediaControllerCallback getIControllerCallback() {
            return this.mIControllerCallback;
        }

        public void binderDied() {
            postToHandler(8, (Object) null, (Bundle) null);
        }

        /* access modifiers changed from: package-private */
        public void setHandler(Handler handler) {
            MessageHandler messageHandler;
            Handler handler2 = handler;
            if (handler2 != null) {
                new MessageHandler(this, handler2.getLooper());
                this.mHandler = messageHandler;
                this.mHandler.mRegistered = true;
            } else if (this.mHandler != null) {
                this.mHandler.mRegistered = false;
                this.mHandler.removeCallbacksAndMessages((Object) null);
                this.mHandler = null;
            }
        }

        /* access modifiers changed from: package-private */
        public void postToHandler(int i, Object obj, Bundle bundle) {
            int what = i;
            Object obj2 = obj;
            Bundle data = bundle;
            if (this.mHandler != null) {
                Message msg = this.mHandler.obtainMessage(what, obj2);
                msg.setData(data);
                msg.sendToTarget();
            }
        }

        /* renamed from: android.support.v4.media.session.MediaControllerCompat$Callback$StubApi21 */
        private static class StubApi21 implements MediaControllerCompatApi21.Callback {
            private final WeakReference<Callback> mCallback;

            StubApi21(Callback callback) {
                WeakReference<Callback> weakReference;
                new WeakReference<>(callback);
                this.mCallback = weakReference;
            }

            public void onSessionDestroyed() {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.onSessionDestroyed();
                }
            }

            public void onSessionEvent(String str, Bundle bundle) {
                String event = str;
                Bundle extras = bundle;
                Callback callback = (Callback) this.mCallback.get();
                if (callback == null) {
                    return;
                }
                if (callback.mIControllerCallback == null || Build.VERSION.SDK_INT >= 23) {
                    callback.onSessionEvent(event, extras);
                }
            }

            public void onPlaybackStateChanged(Object obj) {
                Object stateObj = obj;
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null && callback.mIControllerCallback == null) {
                    callback.onPlaybackStateChanged(PlaybackStateCompat.fromPlaybackState(stateObj));
                }
            }

            public void onMetadataChanged(Object obj) {
                Object metadataObj = obj;
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.onMetadataChanged(MediaMetadataCompat.fromMediaMetadata(metadataObj));
                }
            }

            public void onQueueChanged(List<?> list) {
                List<?> queue = list;
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.onQueueChanged(MediaSessionCompat.QueueItem.fromQueueItemList(queue));
                }
            }

            public void onQueueTitleChanged(CharSequence charSequence) {
                CharSequence title = charSequence;
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.onQueueTitleChanged(title);
                }
            }

            public void onExtrasChanged(Bundle bundle) {
                Bundle extras = bundle;
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.onExtrasChanged(extras);
                }
            }

            public void onAudioInfoChanged(int i, int i2, int i3, int i4, int i5) {
                PlaybackInfo playbackInfo;
                int type = i;
                int stream = i2;
                int control = i3;
                int max = i4;
                int current = i5;
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    new PlaybackInfo(type, stream, control, max, current);
                    callback.onAudioInfoChanged(playbackInfo);
                }
            }
        }

        /* renamed from: android.support.v4.media.session.MediaControllerCompat$Callback$StubCompat */
        private static class StubCompat extends IMediaControllerCallback.Stub {
            private final WeakReference<Callback> mCallback;

            StubCompat(Callback callback) {
                WeakReference<Callback> weakReference;
                new WeakReference<>(callback);
                this.mCallback = weakReference;
            }

            public void onEvent(String str, Bundle bundle) throws RemoteException {
                String event = str;
                Bundle extras = bundle;
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(1, event, extras);
                }
            }

            public void onSessionDestroyed() throws RemoteException {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(8, (Object) null, (Bundle) null);
                }
            }

            public void onPlaybackStateChanged(PlaybackStateCompat playbackStateCompat) throws RemoteException {
                PlaybackStateCompat state = playbackStateCompat;
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(2, state, (Bundle) null);
                }
            }

            public void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat) throws RemoteException {
                MediaMetadataCompat metadata = mediaMetadataCompat;
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(3, metadata, (Bundle) null);
                }
            }

            public void onQueueChanged(List<MediaSessionCompat.QueueItem> list) throws RemoteException {
                List<MediaSessionCompat.QueueItem> queue = list;
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(5, queue, (Bundle) null);
                }
            }

            public void onQueueTitleChanged(CharSequence charSequence) throws RemoteException {
                CharSequence title = charSequence;
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(6, title, (Bundle) null);
                }
            }

            public void onCaptioningEnabledChanged(boolean z) throws RemoteException {
                boolean enabled = z;
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(11, Boolean.valueOf(enabled), (Bundle) null);
                }
            }

            public void onRepeatModeChanged(int i) throws RemoteException {
                int repeatMode = i;
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(9, Integer.valueOf(repeatMode), (Bundle) null);
                }
            }

            public void onShuffleModeChangedRemoved(boolean enabled) throws RemoteException {
            }

            public void onShuffleModeChanged(int i) throws RemoteException {
                int shuffleMode = i;
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(12, Integer.valueOf(shuffleMode), (Bundle) null);
                }
            }

            public void onExtrasChanged(Bundle bundle) throws RemoteException {
                Bundle extras = bundle;
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(7, extras, (Bundle) null);
                }
            }

            public void onVolumeInfoChanged(ParcelableVolumeInfo parcelableVolumeInfo) throws RemoteException {
                PlaybackInfo playbackInfo;
                ParcelableVolumeInfo info = parcelableVolumeInfo;
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    PlaybackInfo pi = null;
                    if (info != null) {
                        new PlaybackInfo(info.volumeType, info.audioStream, info.controlType, info.maxVolume, info.currentVolume);
                        pi = playbackInfo;
                    }
                    callback.postToHandler(4, pi, (Bundle) null);
                }
            }

            public void onSessionReady() throws RemoteException {
                Callback callback = (Callback) this.mCallback.get();
                if (callback != null) {
                    callback.postToHandler(13, (Object) null, (Bundle) null);
                }
            }
        }

        /* renamed from: android.support.v4.media.session.MediaControllerCompat$Callback$MessageHandler */
        private class MessageHandler extends Handler {
            private static final int MSG_DESTROYED = 8;
            private static final int MSG_EVENT = 1;
            private static final int MSG_SESSION_READY = 13;
            private static final int MSG_UPDATE_CAPTIONING_ENABLED = 11;
            private static final int MSG_UPDATE_EXTRAS = 7;
            private static final int MSG_UPDATE_METADATA = 3;
            private static final int MSG_UPDATE_PLAYBACK_STATE = 2;
            private static final int MSG_UPDATE_QUEUE = 5;
            private static final int MSG_UPDATE_QUEUE_TITLE = 6;
            private static final int MSG_UPDATE_REPEAT_MODE = 9;
            private static final int MSG_UPDATE_SHUFFLE_MODE = 12;
            private static final int MSG_UPDATE_VOLUME = 4;
            boolean mRegistered = false;
            final /* synthetic */ Callback this$0;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            MessageHandler(Callback callback, Looper looper) {
                super(looper);
                this.this$0 = callback;
            }

            public void handleMessage(Message message) {
                Message msg = message;
                if (this.mRegistered) {
                    switch (msg.what) {
                        case 1:
                            Bundle extras = msg.getData();
                            MediaSessionCompat.ensureClassLoader(extras);
                            this.this$0.onSessionEvent((String) msg.obj, extras);
                            return;
                        case 2:
                            this.this$0.onPlaybackStateChanged((PlaybackStateCompat) msg.obj);
                            return;
                        case 3:
                            this.this$0.onMetadataChanged((MediaMetadataCompat) msg.obj);
                            return;
                        case 4:
                            this.this$0.onAudioInfoChanged((PlaybackInfo) msg.obj);
                            return;
                        case 5:
                            this.this$0.onQueueChanged((List) msg.obj);
                            return;
                        case 6:
                            this.this$0.onQueueTitleChanged((CharSequence) msg.obj);
                            return;
                        case 7:
                            Bundle extras2 = (Bundle) msg.obj;
                            MediaSessionCompat.ensureClassLoader(extras2);
                            this.this$0.onExtrasChanged(extras2);
                            return;
                        case 8:
                            this.this$0.onSessionDestroyed();
                            return;
                        case 9:
                            this.this$0.onRepeatModeChanged(((Integer) msg.obj).intValue());
                            return;
                        case 11:
                            this.this$0.onCaptioningEnabledChanged(((Boolean) msg.obj).booleanValue());
                            return;
                        case 12:
                            this.this$0.onShuffleModeChanged(((Integer) msg.obj).intValue());
                            return;
                        case 13:
                            this.this$0.onSessionReady();
                            return;
                        default:
                            return;
                    }
                }
            }
        }
    }

    /* renamed from: android.support.v4.media.session.MediaControllerCompat$TransportControls */
    public static abstract class TransportControls {
        public static final String EXTRA_LEGACY_STREAM_TYPE = "android.media.session.extra.LEGACY_STREAM_TYPE";

        public abstract void fastForward();

        public abstract void pause();

        public abstract void play();

        public abstract void playFromMediaId(String str, Bundle bundle);

        public abstract void playFromSearch(String str, Bundle bundle);

        public abstract void playFromUri(Uri uri, Bundle bundle);

        public abstract void prepare();

        public abstract void prepareFromMediaId(String str, Bundle bundle);

        public abstract void prepareFromSearch(String str, Bundle bundle);

        public abstract void prepareFromUri(Uri uri, Bundle bundle);

        public abstract void rewind();

        public abstract void seekTo(long j);

        public abstract void sendCustomAction(PlaybackStateCompat.CustomAction customAction, Bundle bundle);

        public abstract void sendCustomAction(String str, Bundle bundle);

        public abstract void setCaptioningEnabled(boolean z);

        public abstract void setRating(RatingCompat ratingCompat);

        public abstract void setRating(RatingCompat ratingCompat, Bundle bundle);

        public abstract void setRepeatMode(int i);

        public abstract void setShuffleMode(int i);

        public abstract void skipToNext();

        public abstract void skipToPrevious();

        public abstract void skipToQueueItem(long j);

        public abstract void stop();

        TransportControls() {
        }
    }

    /* renamed from: android.support.v4.media.session.MediaControllerCompat$PlaybackInfo */
    public static final class PlaybackInfo {
        public static final int PLAYBACK_TYPE_LOCAL = 1;
        public static final int PLAYBACK_TYPE_REMOTE = 2;
        private final int mAudioStream;
        private final int mCurrentVolume;
        private final int mMaxVolume;
        private final int mPlaybackType;
        private final int mVolumeControl;

        PlaybackInfo(int type, int stream, int control, int max, int current) {
            this.mPlaybackType = type;
            this.mAudioStream = stream;
            this.mVolumeControl = control;
            this.mMaxVolume = max;
            this.mCurrentVolume = current;
        }

        public int getPlaybackType() {
            return this.mPlaybackType;
        }

        public int getAudioStream() {
            return this.mAudioStream;
        }

        public int getVolumeControl() {
            return this.mVolumeControl;
        }

        public int getMaxVolume() {
            return this.mMaxVolume;
        }

        public int getCurrentVolume() {
            return this.mCurrentVolume;
        }
    }

    /* renamed from: android.support.v4.media.session.MediaControllerCompat$MediaControllerImplBase */
    static class MediaControllerImplBase implements MediaControllerImpl {
        private IMediaSession mBinder;
        private TransportControls mTransportControls;

        public MediaControllerImplBase(MediaSessionCompat.Token token) {
            this.mBinder = IMediaSession.Stub.asInterface((IBinder) token.getToken());
        }

        public void registerCallback(Callback callback, Handler handler) {
            Throwable th;
            Callback callback2 = callback;
            Handler handler2 = handler;
            if (callback2 == null) {
                Throwable th2 = th;
                new IllegalArgumentException("callback may not be null.");
                throw th2;
            }
            try {
                this.mBinder.asBinder().linkToDeath(callback2, 0);
                this.mBinder.registerCallbackListener((IMediaControllerCallback) callback2.mCallbackObj);
                callback2.postToHandler(13, (Object) null, (Bundle) null);
            } catch (RemoteException e) {
                int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in registerCallback.", e);
                callback2.postToHandler(8, (Object) null, (Bundle) null);
            }
        }

        public void unregisterCallback(Callback callback) {
            Throwable th;
            Callback callback2 = callback;
            if (callback2 == null) {
                Throwable th2 = th;
                new IllegalArgumentException("callback may not be null.");
                throw th2;
            }
            try {
                this.mBinder.unregisterCallbackListener((IMediaControllerCallback) callback2.mCallbackObj);
                boolean unlinkToDeath = this.mBinder.asBinder().unlinkToDeath(callback2, 0);
            } catch (RemoteException e) {
                int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in unregisterCallback.", e);
            }
        }

        public boolean dispatchMediaButtonEvent(KeyEvent keyEvent) {
            Throwable th;
            KeyEvent event = keyEvent;
            if (event == null) {
                Throwable th2 = th;
                new IllegalArgumentException("event may not be null.");
                throw th2;
            }
            try {
                boolean sendMediaButton = this.mBinder.sendMediaButton(event);
            } catch (RemoteException e) {
                int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in dispatchMediaButtonEvent.", e);
            }
            return false;
        }

        public TransportControls getTransportControls() {
            TransportControls transportControls;
            if (this.mTransportControls == null) {
                new TransportControlsBase(this.mBinder);
                this.mTransportControls = transportControls;
            }
            return this.mTransportControls;
        }

        public PlaybackStateCompat getPlaybackState() {
            try {
                return this.mBinder.getPlaybackState();
            } catch (RemoteException e) {
                int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in getPlaybackState.", e);
                return null;
            }
        }

        public MediaMetadataCompat getMetadata() {
            try {
                return this.mBinder.getMetadata();
            } catch (RemoteException e) {
                int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in getMetadata.", e);
                return null;
            }
        }

        public List<MediaSessionCompat.QueueItem> getQueue() {
            try {
                return this.mBinder.getQueue();
            } catch (RemoteException e) {
                int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in getQueue.", e);
                return null;
            }
        }

        public void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
            Throwable th;
            MediaDescriptionCompat description = mediaDescriptionCompat;
            try {
                if ((this.mBinder.getFlags() & 4) == 0) {
                    Throwable th2 = th;
                    new UnsupportedOperationException("This session doesn't support queue management operations");
                    throw th2;
                }
                this.mBinder.addQueueItem(description);
            } catch (RemoteException e) {
                int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in addQueueItem.", e);
            }
        }

        public void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat, int i) {
            Throwable th;
            MediaDescriptionCompat description = mediaDescriptionCompat;
            int index = i;
            try {
                if ((this.mBinder.getFlags() & 4) == 0) {
                    Throwable th2 = th;
                    new UnsupportedOperationException("This session doesn't support queue management operations");
                    throw th2;
                }
                this.mBinder.addQueueItemAt(description, index);
            } catch (RemoteException e) {
                int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in addQueueItemAt.", e);
            }
        }

        public void removeQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
            Throwable th;
            MediaDescriptionCompat description = mediaDescriptionCompat;
            try {
                if ((this.mBinder.getFlags() & 4) == 0) {
                    Throwable th2 = th;
                    new UnsupportedOperationException("This session doesn't support queue management operations");
                    throw th2;
                }
                this.mBinder.removeQueueItem(description);
            } catch (RemoteException e) {
                int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in removeQueueItem.", e);
            }
        }

        public CharSequence getQueueTitle() {
            try {
                return this.mBinder.getQueueTitle();
            } catch (RemoteException e) {
                int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in getQueueTitle.", e);
                return null;
            }
        }

        public Bundle getExtras() {
            try {
                return this.mBinder.getExtras();
            } catch (RemoteException e) {
                int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in getExtras.", e);
                return null;
            }
        }

        public int getRatingType() {
            try {
                return this.mBinder.getRatingType();
            } catch (RemoteException e) {
                int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in getRatingType.", e);
                return 0;
            }
        }

        public boolean isCaptioningEnabled() {
            try {
                return this.mBinder.isCaptioningEnabled();
            } catch (RemoteException e) {
                int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in isCaptioningEnabled.", e);
                return false;
            }
        }

        public int getRepeatMode() {
            try {
                return this.mBinder.getRepeatMode();
            } catch (RemoteException e) {
                int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in getRepeatMode.", e);
                return -1;
            }
        }

        public int getShuffleMode() {
            try {
                return this.mBinder.getShuffleMode();
            } catch (RemoteException e) {
                int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in getShuffleMode.", e);
                return -1;
            }
        }

        public long getFlags() {
            try {
                return this.mBinder.getFlags();
            } catch (RemoteException e) {
                int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in getFlags.", e);
                return 0;
            }
        }

        public PlaybackInfo getPlaybackInfo() {
            PlaybackInfo playbackInfo;
            try {
                ParcelableVolumeInfo info = this.mBinder.getVolumeAttributes();
                PlaybackInfo pi = playbackInfo;
                new PlaybackInfo(info.volumeType, info.audioStream, info.controlType, info.maxVolume, info.currentVolume);
                return pi;
            } catch (RemoteException e) {
                int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in getPlaybackInfo.", e);
                return null;
            }
        }

        public PendingIntent getSessionActivity() {
            try {
                return this.mBinder.getLaunchPendingIntent();
            } catch (RemoteException e) {
                int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in getSessionActivity.", e);
                return null;
            }
        }

        public void setVolumeTo(int value, int flags) {
            try {
                this.mBinder.setVolumeTo(value, flags, (String) null);
            } catch (RemoteException e) {
                int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in setVolumeTo.", e);
            }
        }

        public void adjustVolume(int direction, int flags) {
            try {
                this.mBinder.adjustVolume(direction, flags, (String) null);
            } catch (RemoteException e) {
                int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in adjustVolume.", e);
            }
        }

        public void sendCommand(String str, Bundle bundle, ResultReceiver cb) {
            MediaSessionCompat.ResultReceiverWrapper resultReceiverWrapper;
            String command = str;
            Bundle params = bundle;
            try {
                new MediaSessionCompat.ResultReceiverWrapper(cb);
                this.mBinder.sendCommand(command, params, resultReceiverWrapper);
            } catch (RemoteException e) {
                int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in sendCommand.", e);
            }
        }

        public boolean isSessionReady() {
            return true;
        }

        public String getPackageName() {
            try {
                return this.mBinder.getPackageName();
            } catch (RemoteException e) {
                int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in getPackageName.", e);
                return null;
            }
        }

        public Object getMediaController() {
            return null;
        }
    }

    /* renamed from: android.support.v4.media.session.MediaControllerCompat$TransportControlsBase */
    static class TransportControlsBase extends TransportControls {
        private IMediaSession mBinder;

        public TransportControlsBase(IMediaSession binder) {
            this.mBinder = binder;
        }

        public void prepare() {
            try {
                this.mBinder.prepare();
            } catch (RemoteException e) {
                int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in prepare.", e);
            }
        }

        public void prepareFromMediaId(String mediaId, Bundle extras) {
            try {
                this.mBinder.prepareFromMediaId(mediaId, extras);
            } catch (RemoteException e) {
                int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in prepareFromMediaId.", e);
            }
        }

        public void prepareFromSearch(String query, Bundle extras) {
            try {
                this.mBinder.prepareFromSearch(query, extras);
            } catch (RemoteException e) {
                int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in prepareFromSearch.", e);
            }
        }

        public void prepareFromUri(Uri uri, Bundle extras) {
            try {
                this.mBinder.prepareFromUri(uri, extras);
            } catch (RemoteException e) {
                int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in prepareFromUri.", e);
            }
        }

        public void play() {
            try {
                this.mBinder.play();
            } catch (RemoteException e) {
                int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in play.", e);
            }
        }

        public void playFromMediaId(String mediaId, Bundle extras) {
            try {
                this.mBinder.playFromMediaId(mediaId, extras);
            } catch (RemoteException e) {
                int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in playFromMediaId.", e);
            }
        }

        public void playFromSearch(String query, Bundle extras) {
            try {
                this.mBinder.playFromSearch(query, extras);
            } catch (RemoteException e) {
                int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in playFromSearch.", e);
            }
        }

        public void playFromUri(Uri uri, Bundle extras) {
            try {
                this.mBinder.playFromUri(uri, extras);
            } catch (RemoteException e) {
                int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in playFromUri.", e);
            }
        }

        public void skipToQueueItem(long id) {
            try {
                this.mBinder.skipToQueueItem(id);
            } catch (RemoteException e) {
                int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in skipToQueueItem.", e);
            }
        }

        public void pause() {
            try {
                this.mBinder.pause();
            } catch (RemoteException e) {
                int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in pause.", e);
            }
        }

        public void stop() {
            try {
                this.mBinder.stop();
            } catch (RemoteException e) {
                int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in stop.", e);
            }
        }

        public void seekTo(long pos) {
            try {
                this.mBinder.seekTo(pos);
            } catch (RemoteException e) {
                int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in seekTo.", e);
            }
        }

        public void fastForward() {
            try {
                this.mBinder.fastForward();
            } catch (RemoteException e) {
                int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in fastForward.", e);
            }
        }

        public void skipToNext() {
            try {
                this.mBinder.next();
            } catch (RemoteException e) {
                int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in skipToNext.", e);
            }
        }

        public void rewind() {
            try {
                this.mBinder.rewind();
            } catch (RemoteException e) {
                int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in rewind.", e);
            }
        }

        public void skipToPrevious() {
            try {
                this.mBinder.previous();
            } catch (RemoteException e) {
                int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in skipToPrevious.", e);
            }
        }

        public void setRating(RatingCompat rating) {
            try {
                this.mBinder.rate(rating);
            } catch (RemoteException e) {
                int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in setRating.", e);
            }
        }

        public void setRating(RatingCompat rating, Bundle extras) {
            try {
                this.mBinder.rateWithExtras(rating, extras);
            } catch (RemoteException e) {
                int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in setRating.", e);
            }
        }

        public void setCaptioningEnabled(boolean enabled) {
            try {
                this.mBinder.setCaptioningEnabled(enabled);
            } catch (RemoteException e) {
                int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in setCaptioningEnabled.", e);
            }
        }

        public void setRepeatMode(int repeatMode) {
            try {
                this.mBinder.setRepeatMode(repeatMode);
            } catch (RemoteException e) {
                int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in setRepeatMode.", e);
            }
        }

        public void setShuffleMode(int shuffleMode) {
            try {
                this.mBinder.setShuffleMode(shuffleMode);
            } catch (RemoteException e) {
                int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in setShuffleMode.", e);
            }
        }

        public void sendCustomAction(PlaybackStateCompat.CustomAction customAction, Bundle args) {
            sendCustomAction(customAction.getAction(), args);
        }

        public void sendCustomAction(String str, Bundle bundle) {
            String action = str;
            Bundle args = bundle;
            MediaControllerCompat.validateCustomAction(action, args);
            try {
                this.mBinder.sendCustomAction(action, args);
            } catch (RemoteException e) {
                int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in sendCustomAction.", e);
            }
        }
    }

    @RequiresApi(21)
    /* renamed from: android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi21 */
    static class MediaControllerImplApi21 implements MediaControllerImpl {
        private HashMap<Callback, ExtraCallback> mCallbackMap;
        protected final Object mControllerObj;
        final Object mLock;
        @GuardedBy("mLock")
        private final List<Callback> mPendingCallbacks;
        final MediaSessionCompat.Token mSessionToken;

        public MediaControllerImplApi21(Context context, MediaSessionCompat.Token sessionToken) throws RemoteException {
            Object obj;
            List<Callback> list;
            HashMap<Callback, ExtraCallback> hashMap;
            Throwable th;
            new Object();
            this.mLock = obj;
            new ArrayList();
            this.mPendingCallbacks = list;
            new HashMap<>();
            this.mCallbackMap = hashMap;
            this.mSessionToken = sessionToken;
            this.mControllerObj = MediaControllerCompatApi21.fromToken(context, this.mSessionToken.getToken());
            if (this.mControllerObj == null) {
                Throwable th2 = th;
                new RemoteException();
                throw th2;
            } else if (this.mSessionToken.getExtraBinder() == null) {
                requestExtraBinder();
            }
        }

        /* JADX WARNING: type inference failed for: r8v7, types: [android.support.v4.media.session.IMediaControllerCallback] */
        /* JADX WARNING: type inference failed for: r8v9, types: [android.support.v4.media.session.IMediaControllerCallback] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void registerCallback(android.support.p000v4.media.session.MediaControllerCompat.Callback r13, android.os.Handler r14) {
            /*
                r12 = this;
                r0 = r12
                r1 = r13
                r2 = r14
                r7 = r0
                java.lang.Object r7 = r7.mControllerObj
                r8 = r1
                java.lang.Object r8 = r8.mCallbackObj
                r9 = r2
                android.support.p000v4.media.session.MediaControllerCompatApi21.registerCallback(r7, r8, r9)
                r7 = r0
                java.lang.Object r7 = r7.mLock
                r11 = r7
                r7 = r11
                r8 = r11
                r3 = r8
                monitor-enter(r7)
                r7 = r0
                android.support.v4.media.session.MediaSessionCompat$Token r7 = r7.mSessionToken     // Catch:{ all -> 0x0066 }
                android.support.v4.media.session.IMediaSession r7 = r7.getExtraBinder()     // Catch:{ all -> 0x0066 }
                if (r7 == 0) goto L_0x0059
                android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi21$ExtraCallback r7 = new android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi21$ExtraCallback     // Catch:{ all -> 0x0066 }
                r11 = r7
                r7 = r11
                r8 = r11
                r9 = r1
                r8.<init>(r9)     // Catch:{ all -> 0x0066 }
                r4 = r7
                r7 = r0
                java.util.HashMap<android.support.v4.media.session.MediaControllerCompat$Callback, android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi21$ExtraCallback> r7 = r7.mCallbackMap     // Catch:{ all -> 0x0066 }
                r8 = r1
                r9 = r4
                java.lang.Object r7 = r7.put(r8, r9)     // Catch:{ all -> 0x0066 }
                r7 = r1
                r8 = r4
                r7.mIControllerCallback = r8     // Catch:{ all -> 0x0066 }
                r7 = r0
                android.support.v4.media.session.MediaSessionCompat$Token r7 = r7.mSessionToken     // Catch:{ RemoteException -> 0x004b }
                android.support.v4.media.session.IMediaSession r7 = r7.getExtraBinder()     // Catch:{ RemoteException -> 0x004b }
                r8 = r4
                r7.registerCallbackListener(r8)     // Catch:{ RemoteException -> 0x004b }
                r7 = r1
                r8 = 13
                r9 = 0
                r10 = 0
                r7.postToHandler(r8, r9, r10)     // Catch:{ RemoteException -> 0x004b }
            L_0x0048:
                r7 = r3
                monitor-exit(r7)     // Catch:{ all -> 0x0066 }
                return
            L_0x004b:
                r7 = move-exception
                r5 = r7
                java.lang.String r7 = "MediaControllerCompat"
                java.lang.String r8 = "Dead object in registerCallback."
                r9 = r5
                int r7 = android.util.Log.e(r7, r8, r9)     // Catch:{ all -> 0x0066 }
                goto L_0x0048
            L_0x0059:
                r7 = r1
                r8 = 0
                r7.mIControllerCallback = r8     // Catch:{ all -> 0x0066 }
                r7 = r0
                java.util.List<android.support.v4.media.session.MediaControllerCompat$Callback> r7 = r7.mPendingCallbacks     // Catch:{ all -> 0x0066 }
                r8 = r1
                boolean r7 = r7.add(r8)     // Catch:{ all -> 0x0066 }
                goto L_0x0048
            L_0x0066:
                r7 = move-exception
                r6 = r7
                r7 = r3
                monitor-exit(r7)     // Catch:{ all -> 0x0066 }
                r7 = r6
                throw r7
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.media.session.MediaControllerCompat.MediaControllerImplApi21.registerCallback(android.support.v4.media.session.MediaControllerCompat$Callback, android.os.Handler):void");
        }

        public final void unregisterCallback(Callback callback) {
            Callback callback2 = callback;
            MediaControllerCompatApi21.unregisterCallback(this.mControllerObj, callback2.mCallbackObj);
            Object obj = this.mLock;
            Object obj2 = obj;
            synchronized (obj) {
                try {
                    if (this.mSessionToken.getExtraBinder() != null) {
                        ExtraCallback extraCallback = this.mCallbackMap.remove(callback2);
                        if (extraCallback != null) {
                            callback2.mIControllerCallback = null;
                            this.mSessionToken.getExtraBinder().unregisterCallbackListener(extraCallback);
                        }
                    } else {
                        boolean remove = this.mPendingCallbacks.remove(callback2);
                    }
                } catch (RemoteException e) {
                    int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in unregisterCallback.", e);
                } catch (Throwable th) {
                    Throwable th2 = th;
                    Object obj3 = obj2;
                    throw th2;
                }
            }
        }

        public boolean dispatchMediaButtonEvent(KeyEvent event) {
            return MediaControllerCompatApi21.dispatchMediaButtonEvent(this.mControllerObj, event);
        }

        public TransportControls getTransportControls() {
            TransportControls transportControls;
            TransportControls transportControls2;
            Object controlsObj = MediaControllerCompatApi21.getTransportControls(this.mControllerObj);
            if (controlsObj != null) {
                transportControls = transportControls2;
                new TransportControlsApi21(controlsObj);
            } else {
                transportControls = null;
            }
            return transportControls;
        }

        public PlaybackStateCompat getPlaybackState() {
            if (this.mSessionToken.getExtraBinder() != null) {
                try {
                    return this.mSessionToken.getExtraBinder().getPlaybackState();
                } catch (RemoteException e) {
                    int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in getPlaybackState.", e);
                }
            }
            Object stateObj = MediaControllerCompatApi21.getPlaybackState(this.mControllerObj);
            return stateObj != null ? PlaybackStateCompat.fromPlaybackState(stateObj) : null;
        }

        public MediaMetadataCompat getMetadata() {
            Object metadataObj = MediaControllerCompatApi21.getMetadata(this.mControllerObj);
            return metadataObj != null ? MediaMetadataCompat.fromMediaMetadata(metadataObj) : null;
        }

        public List<MediaSessionCompat.QueueItem> getQueue() {
            List<Object> queueObjs = MediaControllerCompatApi21.getQueue(this.mControllerObj);
            return queueObjs != null ? MediaSessionCompat.QueueItem.fromQueueItemList(queueObjs) : null;
        }

        public void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
            Bundle bundle;
            Throwable th;
            MediaDescriptionCompat description = mediaDescriptionCompat;
            if ((getFlags() & 4) == 0) {
                Throwable th2 = th;
                new UnsupportedOperationException("This session doesn't support queue management operations");
                throw th2;
            }
            new Bundle();
            Bundle params = bundle;
            params.putParcelable(MediaControllerCompat.COMMAND_ARGUMENT_MEDIA_DESCRIPTION, description);
            sendCommand(MediaControllerCompat.COMMAND_ADD_QUEUE_ITEM, params, (ResultReceiver) null);
        }

        public void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat, int i) {
            Bundle bundle;
            Throwable th;
            MediaDescriptionCompat description = mediaDescriptionCompat;
            int index = i;
            if ((getFlags() & 4) == 0) {
                Throwable th2 = th;
                new UnsupportedOperationException("This session doesn't support queue management operations");
                throw th2;
            }
            new Bundle();
            Bundle params = bundle;
            params.putParcelable(MediaControllerCompat.COMMAND_ARGUMENT_MEDIA_DESCRIPTION, description);
            params.putInt(MediaControllerCompat.COMMAND_ARGUMENT_INDEX, index);
            sendCommand(MediaControllerCompat.COMMAND_ADD_QUEUE_ITEM_AT, params, (ResultReceiver) null);
        }

        public void removeQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
            Bundle bundle;
            Throwable th;
            MediaDescriptionCompat description = mediaDescriptionCompat;
            if ((getFlags() & 4) == 0) {
                Throwable th2 = th;
                new UnsupportedOperationException("This session doesn't support queue management operations");
                throw th2;
            }
            new Bundle();
            Bundle params = bundle;
            params.putParcelable(MediaControllerCompat.COMMAND_ARGUMENT_MEDIA_DESCRIPTION, description);
            sendCommand(MediaControllerCompat.COMMAND_REMOVE_QUEUE_ITEM, params, (ResultReceiver) null);
        }

        public CharSequence getQueueTitle() {
            return MediaControllerCompatApi21.getQueueTitle(this.mControllerObj);
        }

        public Bundle getExtras() {
            return MediaControllerCompatApi21.getExtras(this.mControllerObj);
        }

        public int getRatingType() {
            if (Build.VERSION.SDK_INT < 22 && this.mSessionToken.getExtraBinder() != null) {
                try {
                    return this.mSessionToken.getExtraBinder().getRatingType();
                } catch (RemoteException e) {
                    int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in getRatingType.", e);
                }
            }
            return MediaControllerCompatApi21.getRatingType(this.mControllerObj);
        }

        public boolean isCaptioningEnabled() {
            if (this.mSessionToken.getExtraBinder() != null) {
                try {
                    return this.mSessionToken.getExtraBinder().isCaptioningEnabled();
                } catch (RemoteException e) {
                    int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in isCaptioningEnabled.", e);
                }
            }
            return false;
        }

        public int getRepeatMode() {
            if (this.mSessionToken.getExtraBinder() != null) {
                try {
                    return this.mSessionToken.getExtraBinder().getRepeatMode();
                } catch (RemoteException e) {
                    int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in getRepeatMode.", e);
                }
            }
            return -1;
        }

        public int getShuffleMode() {
            if (this.mSessionToken.getExtraBinder() != null) {
                try {
                    return this.mSessionToken.getExtraBinder().getShuffleMode();
                } catch (RemoteException e) {
                    int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in getShuffleMode.", e);
                }
            }
            return -1;
        }

        public long getFlags() {
            return MediaControllerCompatApi21.getFlags(this.mControllerObj);
        }

        public PlaybackInfo getPlaybackInfo() {
            PlaybackInfo playbackInfo;
            PlaybackInfo playbackInfo2;
            Object volumeInfoObj = MediaControllerCompatApi21.getPlaybackInfo(this.mControllerObj);
            if (volumeInfoObj != null) {
                playbackInfo = playbackInfo2;
                new PlaybackInfo(MediaControllerCompatApi21.PlaybackInfo.getPlaybackType(volumeInfoObj), MediaControllerCompatApi21.PlaybackInfo.getLegacyAudioStream(volumeInfoObj), MediaControllerCompatApi21.PlaybackInfo.getVolumeControl(volumeInfoObj), MediaControllerCompatApi21.PlaybackInfo.getMaxVolume(volumeInfoObj), MediaControllerCompatApi21.PlaybackInfo.getCurrentVolume(volumeInfoObj));
            } else {
                playbackInfo = null;
            }
            return playbackInfo;
        }

        public PendingIntent getSessionActivity() {
            return MediaControllerCompatApi21.getSessionActivity(this.mControllerObj);
        }

        public void setVolumeTo(int value, int flags) {
            MediaControllerCompatApi21.setVolumeTo(this.mControllerObj, value, flags);
        }

        public void adjustVolume(int direction, int flags) {
            MediaControllerCompatApi21.adjustVolume(this.mControllerObj, direction, flags);
        }

        public void sendCommand(String command, Bundle params, ResultReceiver cb) {
            MediaControllerCompatApi21.sendCommand(this.mControllerObj, command, params, cb);
        }

        public boolean isSessionReady() {
            return this.mSessionToken.getExtraBinder() != null;
        }

        public String getPackageName() {
            return MediaControllerCompatApi21.getPackageName(this.mControllerObj);
        }

        public Object getMediaController() {
            return this.mControllerObj;
        }

        private void requestExtraBinder() {
            ResultReceiver resultReceiver;
            new ExtraBinderRequestResultReceiver(this);
            sendCommand(MediaControllerCompat.COMMAND_GET_EXTRA_BINDER, (Bundle) null, resultReceiver);
        }

        /* access modifiers changed from: package-private */
        @GuardedBy("mLock")
        public void processPendingCallbacksLocked() {
            ExtraCallback extraCallback;
            if (this.mSessionToken.getExtraBinder() != null) {
                for (Callback callback : this.mPendingCallbacks) {
                    new ExtraCallback(callback);
                    ExtraCallback extraCallback2 = extraCallback;
                    ExtraCallback put = this.mCallbackMap.put(callback, extraCallback2);
                    callback.mIControllerCallback = extraCallback2;
                    try {
                        this.mSessionToken.getExtraBinder().registerCallbackListener(extraCallback2);
                        callback.postToHandler(13, (Object) null, (Bundle) null);
                    } catch (RemoteException e) {
                        int e2 = Log.e(MediaControllerCompat.TAG, "Dead object in registerCallback.", e);
                    }
                }
                this.mPendingCallbacks.clear();
            }
        }

        /* renamed from: android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver */
        private static class ExtraBinderRequestResultReceiver extends ResultReceiver {
            private WeakReference<MediaControllerImplApi21> mMediaControllerImpl;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            ExtraBinderRequestResultReceiver(MediaControllerImplApi21 mediaControllerImpl) {
                super((Handler) null);
                WeakReference<MediaControllerImplApi21> weakReference;
                new WeakReference<>(mediaControllerImpl);
                this.mMediaControllerImpl = weakReference;
            }

            /* access modifiers changed from: protected */
            public void onReceiveResult(int i, Bundle bundle) {
                int i2 = i;
                Bundle resultData = bundle;
                MediaControllerImplApi21 mediaControllerImpl = (MediaControllerImplApi21) this.mMediaControllerImpl.get();
                if (mediaControllerImpl != null && resultData != null) {
                    Object obj = mediaControllerImpl.mLock;
                    Object obj2 = obj;
                    synchronized (obj) {
                        try {
                            mediaControllerImpl.mSessionToken.setExtraBinder(IMediaSession.Stub.asInterface(BundleCompat.getBinder(resultData, MediaSessionCompat.KEY_EXTRA_BINDER)));
                            mediaControllerImpl.mSessionToken.setSessionToken2Bundle(resultData.getBundle(MediaSessionCompat.KEY_SESSION_TOKEN2_BUNDLE));
                            mediaControllerImpl.processPendingCallbacksLocked();
                        } catch (Throwable th) {
                            Throwable th2 = th;
                            Object obj3 = obj2;
                            throw th2;
                        }
                    }
                }
            }
        }

        /* renamed from: android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi21$ExtraCallback */
        private static class ExtraCallback extends Callback.StubCompat {
            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            ExtraCallback(Callback callback) {
                super(callback);
            }

            public void onSessionDestroyed() throws RemoteException {
                Throwable th;
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }

            public void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat) throws RemoteException {
                Throwable th;
                MediaMetadataCompat mediaMetadataCompat2 = mediaMetadataCompat;
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }

            public void onQueueChanged(List<MediaSessionCompat.QueueItem> list) throws RemoteException {
                Throwable th;
                List<MediaSessionCompat.QueueItem> list2 = list;
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }

            public void onQueueTitleChanged(CharSequence charSequence) throws RemoteException {
                Throwable th;
                CharSequence charSequence2 = charSequence;
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }

            public void onExtrasChanged(Bundle bundle) throws RemoteException {
                Throwable th;
                Bundle bundle2 = bundle;
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }

            public void onVolumeInfoChanged(ParcelableVolumeInfo parcelableVolumeInfo) throws RemoteException {
                Throwable th;
                ParcelableVolumeInfo parcelableVolumeInfo2 = parcelableVolumeInfo;
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }
        }
    }

    /* renamed from: android.support.v4.media.session.MediaControllerCompat$TransportControlsApi21 */
    static class TransportControlsApi21 extends TransportControls {
        protected final Object mControlsObj;

        public TransportControlsApi21(Object controlsObj) {
            this.mControlsObj = controlsObj;
        }

        public void prepare() {
            sendCustomAction(MediaSessionCompat.ACTION_PREPARE, (Bundle) null);
        }

        public void prepareFromMediaId(String mediaId, Bundle extras) {
            Bundle bundle;
            new Bundle();
            Bundle bundle2 = bundle;
            bundle2.putString(MediaSessionCompat.ACTION_ARGUMENT_MEDIA_ID, mediaId);
            bundle2.putBundle(MediaSessionCompat.ACTION_ARGUMENT_EXTRAS, extras);
            sendCustomAction(MediaSessionCompat.ACTION_PREPARE_FROM_MEDIA_ID, bundle2);
        }

        public void prepareFromSearch(String query, Bundle extras) {
            Bundle bundle;
            new Bundle();
            Bundle bundle2 = bundle;
            bundle2.putString(MediaSessionCompat.ACTION_ARGUMENT_QUERY, query);
            bundle2.putBundle(MediaSessionCompat.ACTION_ARGUMENT_EXTRAS, extras);
            sendCustomAction(MediaSessionCompat.ACTION_PREPARE_FROM_SEARCH, bundle2);
        }

        public void prepareFromUri(Uri uri, Bundle extras) {
            Bundle bundle;
            new Bundle();
            Bundle bundle2 = bundle;
            bundle2.putParcelable(MediaSessionCompat.ACTION_ARGUMENT_URI, uri);
            bundle2.putBundle(MediaSessionCompat.ACTION_ARGUMENT_EXTRAS, extras);
            sendCustomAction(MediaSessionCompat.ACTION_PREPARE_FROM_URI, bundle2);
        }

        public void play() {
            MediaControllerCompatApi21.TransportControls.play(this.mControlsObj);
        }

        public void pause() {
            MediaControllerCompatApi21.TransportControls.pause(this.mControlsObj);
        }

        public void stop() {
            MediaControllerCompatApi21.TransportControls.stop(this.mControlsObj);
        }

        public void seekTo(long pos) {
            MediaControllerCompatApi21.TransportControls.seekTo(this.mControlsObj, pos);
        }

        public void fastForward() {
            MediaControllerCompatApi21.TransportControls.fastForward(this.mControlsObj);
        }

        public void rewind() {
            MediaControllerCompatApi21.TransportControls.rewind(this.mControlsObj);
        }

        public void skipToNext() {
            MediaControllerCompatApi21.TransportControls.skipToNext(this.mControlsObj);
        }

        public void skipToPrevious() {
            MediaControllerCompatApi21.TransportControls.skipToPrevious(this.mControlsObj);
        }

        public void setRating(RatingCompat ratingCompat) {
            RatingCompat rating = ratingCompat;
            MediaControllerCompatApi21.TransportControls.setRating(this.mControlsObj, rating != null ? rating.getRating() : null);
        }

        public void setRating(RatingCompat rating, Bundle extras) {
            Bundle bundle;
            new Bundle();
            Bundle bundle2 = bundle;
            bundle2.putParcelable(MediaSessionCompat.ACTION_ARGUMENT_RATING, rating);
            bundle2.putBundle(MediaSessionCompat.ACTION_ARGUMENT_EXTRAS, extras);
            sendCustomAction(MediaSessionCompat.ACTION_SET_RATING, bundle2);
        }

        public void setCaptioningEnabled(boolean enabled) {
            Bundle bundle;
            new Bundle();
            Bundle bundle2 = bundle;
            bundle2.putBoolean(MediaSessionCompat.ACTION_ARGUMENT_CAPTIONING_ENABLED, enabled);
            sendCustomAction(MediaSessionCompat.ACTION_SET_CAPTIONING_ENABLED, bundle2);
        }

        public void setRepeatMode(int repeatMode) {
            Bundle bundle;
            new Bundle();
            Bundle bundle2 = bundle;
            bundle2.putInt(MediaSessionCompat.ACTION_ARGUMENT_REPEAT_MODE, repeatMode);
            sendCustomAction(MediaSessionCompat.ACTION_SET_REPEAT_MODE, bundle2);
        }

        public void setShuffleMode(int shuffleMode) {
            Bundle bundle;
            new Bundle();
            Bundle bundle2 = bundle;
            bundle2.putInt(MediaSessionCompat.ACTION_ARGUMENT_SHUFFLE_MODE, shuffleMode);
            sendCustomAction(MediaSessionCompat.ACTION_SET_SHUFFLE_MODE, bundle2);
        }

        public void playFromMediaId(String mediaId, Bundle extras) {
            MediaControllerCompatApi21.TransportControls.playFromMediaId(this.mControlsObj, mediaId, extras);
        }

        public void playFromSearch(String query, Bundle extras) {
            MediaControllerCompatApi21.TransportControls.playFromSearch(this.mControlsObj, query, extras);
        }

        public void playFromUri(Uri uri, Bundle bundle) {
            Throwable th;
            Bundle bundle2;
            Uri uri2 = uri;
            Bundle extras = bundle;
            if (uri2 == null || Uri.EMPTY.equals(uri2)) {
                Throwable th2 = th;
                new IllegalArgumentException("You must specify a non-empty Uri for playFromUri.");
                throw th2;
            }
            new Bundle();
            Bundle bundle3 = bundle2;
            bundle3.putParcelable(MediaSessionCompat.ACTION_ARGUMENT_URI, uri2);
            bundle3.putBundle(MediaSessionCompat.ACTION_ARGUMENT_EXTRAS, extras);
            sendCustomAction(MediaSessionCompat.ACTION_PLAY_FROM_URI, bundle3);
        }

        public void skipToQueueItem(long id) {
            MediaControllerCompatApi21.TransportControls.skipToQueueItem(this.mControlsObj, id);
        }

        public void sendCustomAction(PlaybackStateCompat.CustomAction customAction, Bundle bundle) {
            PlaybackStateCompat.CustomAction customAction2 = customAction;
            Bundle args = bundle;
            MediaControllerCompat.validateCustomAction(customAction2.getAction(), args);
            MediaControllerCompatApi21.TransportControls.sendCustomAction(this.mControlsObj, customAction2.getAction(), args);
        }

        public void sendCustomAction(String str, Bundle bundle) {
            String action = str;
            Bundle args = bundle;
            MediaControllerCompat.validateCustomAction(action, args);
            MediaControllerCompatApi21.TransportControls.sendCustomAction(this.mControlsObj, action, args);
        }
    }

    @RequiresApi(23)
    /* renamed from: android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi23 */
    static class MediaControllerImplApi23 extends MediaControllerImplApi21 {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public MediaControllerImplApi23(Context context, MediaSessionCompat.Token sessionToken) throws RemoteException {
            super(context, sessionToken);
        }

        public TransportControls getTransportControls() {
            TransportControls transportControls;
            TransportControls transportControls2;
            Object controlsObj = MediaControllerCompatApi21.getTransportControls(this.mControllerObj);
            if (controlsObj != null) {
                transportControls = transportControls2;
                new TransportControlsApi23(controlsObj);
            } else {
                transportControls = null;
            }
            return transportControls;
        }
    }

    @RequiresApi(23)
    /* renamed from: android.support.v4.media.session.MediaControllerCompat$TransportControlsApi23 */
    static class TransportControlsApi23 extends TransportControlsApi21 {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public TransportControlsApi23(Object controlsObj) {
            super(controlsObj);
        }

        public void playFromUri(Uri uri, Bundle extras) {
            MediaControllerCompatApi23.TransportControls.playFromUri(this.mControlsObj, uri, extras);
        }
    }

    @RequiresApi(24)
    /* renamed from: android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi24 */
    static class MediaControllerImplApi24 extends MediaControllerImplApi23 {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public MediaControllerImplApi24(Context context, MediaSessionCompat.Token sessionToken) throws RemoteException {
            super(context, sessionToken);
        }

        public TransportControls getTransportControls() {
            TransportControls transportControls;
            TransportControls transportControls2;
            Object controlsObj = MediaControllerCompatApi21.getTransportControls(this.mControllerObj);
            if (controlsObj != null) {
                transportControls = transportControls2;
                new TransportControlsApi24(controlsObj);
            } else {
                transportControls = null;
            }
            return transportControls;
        }
    }

    @RequiresApi(24)
    /* renamed from: android.support.v4.media.session.MediaControllerCompat$TransportControlsApi24 */
    static class TransportControlsApi24 extends TransportControlsApi23 {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public TransportControlsApi24(Object controlsObj) {
            super(controlsObj);
        }

        public void prepare() {
            MediaControllerCompatApi24.TransportControls.prepare(this.mControlsObj);
        }

        public void prepareFromMediaId(String mediaId, Bundle extras) {
            MediaControllerCompatApi24.TransportControls.prepareFromMediaId(this.mControlsObj, mediaId, extras);
        }

        public void prepareFromSearch(String query, Bundle extras) {
            MediaControllerCompatApi24.TransportControls.prepareFromSearch(this.mControlsObj, query, extras);
        }

        public void prepareFromUri(Uri uri, Bundle extras) {
            MediaControllerCompatApi24.TransportControls.prepareFromUri(this.mControlsObj, uri, extras);
        }
    }
}

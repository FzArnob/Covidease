package android.support.p000v4.media.session;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaMetadataEditor;
import android.media.Rating;
import android.media.RemoteControlClient;
import android.media.session.MediaSession;
import android.net.Uri;
import android.os.BadParcelableException;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.p000v4.app.BundleCompat;
import android.support.p000v4.media.MediaDescriptionCompat;
import android.support.p000v4.media.MediaMetadataCompat;
import android.support.p000v4.media.MediaSessionManager;
import android.support.p000v4.media.RatingCompat;
import android.support.p000v4.media.VolumeProviderCompat;
import android.support.p000v4.media.session.IMediaSession;
import android.support.p000v4.media.session.MediaSessionCompatApi21;
import android.support.p000v4.media.session.MediaSessionCompatApi23;
import android.support.p000v4.media.session.MediaSessionCompatApi24;
import android.support.p000v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.ViewConfiguration;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: android.support.v4.media.session.MediaSessionCompat */
public class MediaSessionCompat {
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_ARGUMENT_CAPTIONING_ENABLED = "android.support.v4.media.session.action.ARGUMENT_CAPTIONING_ENABLED";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_ARGUMENT_EXTRAS = "android.support.v4.media.session.action.ARGUMENT_EXTRAS";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_ARGUMENT_MEDIA_ID = "android.support.v4.media.session.action.ARGUMENT_MEDIA_ID";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_ARGUMENT_QUERY = "android.support.v4.media.session.action.ARGUMENT_QUERY";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_ARGUMENT_RATING = "android.support.v4.media.session.action.ARGUMENT_RATING";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_ARGUMENT_REPEAT_MODE = "android.support.v4.media.session.action.ARGUMENT_REPEAT_MODE";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_ARGUMENT_SHUFFLE_MODE = "android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_ARGUMENT_URI = "android.support.v4.media.session.action.ARGUMENT_URI";
    public static final String ACTION_FLAG_AS_INAPPROPRIATE = "android.support.v4.media.session.action.FLAG_AS_INAPPROPRIATE";
    public static final String ACTION_FOLLOW = "android.support.v4.media.session.action.FOLLOW";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_PLAY_FROM_URI = "android.support.v4.media.session.action.PLAY_FROM_URI";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_PREPARE = "android.support.v4.media.session.action.PREPARE";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_PREPARE_FROM_MEDIA_ID = "android.support.v4.media.session.action.PREPARE_FROM_MEDIA_ID";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_PREPARE_FROM_SEARCH = "android.support.v4.media.session.action.PREPARE_FROM_SEARCH";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_PREPARE_FROM_URI = "android.support.v4.media.session.action.PREPARE_FROM_URI";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_SET_CAPTIONING_ENABLED = "android.support.v4.media.session.action.SET_CAPTIONING_ENABLED";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_SET_RATING = "android.support.v4.media.session.action.SET_RATING";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_SET_REPEAT_MODE = "android.support.v4.media.session.action.SET_REPEAT_MODE";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String ACTION_SET_SHUFFLE_MODE = "android.support.v4.media.session.action.SET_SHUFFLE_MODE";
    public static final String ACTION_SKIP_AD = "android.support.v4.media.session.action.SKIP_AD";
    public static final String ACTION_UNFOLLOW = "android.support.v4.media.session.action.UNFOLLOW";
    public static final String ARGUMENT_MEDIA_ATTRIBUTE = "android.support.v4.media.session.ARGUMENT_MEDIA_ATTRIBUTE";
    public static final String ARGUMENT_MEDIA_ATTRIBUTE_VALUE = "android.support.v4.media.session.ARGUMENT_MEDIA_ATTRIBUTE_VALUE";
    private static final String DATA_CALLING_PACKAGE = "data_calling_pkg";
    private static final String DATA_CALLING_PID = "data_calling_pid";
    private static final String DATA_CALLING_UID = "data_calling_uid";
    private static final String DATA_EXTRAS = "data_extras";
    public static final int FLAG_HANDLES_MEDIA_BUTTONS = 1;
    public static final int FLAG_HANDLES_QUEUE_COMMANDS = 4;
    public static final int FLAG_HANDLES_TRANSPORT_CONTROLS = 2;
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String KEY_EXTRA_BINDER = "android.support.v4.media.session.EXTRA_BINDER";
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final String KEY_SESSION_TOKEN2_BUNDLE = "android.support.v4.media.session.SESSION_TOKEN2_BUNDLE";
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final String KEY_TOKEN = "android.support.v4.media.session.TOKEN";
    private static final int MAX_BITMAP_SIZE_IN_DP = 320;
    public static final int MEDIA_ATTRIBUTE_ALBUM = 1;
    public static final int MEDIA_ATTRIBUTE_ARTIST = 0;
    public static final int MEDIA_ATTRIBUTE_PLAYLIST = 2;
    static final String TAG = "MediaSessionCompat";
    static int sMaxBitmapSize;
    private final ArrayList<OnActiveChangeListener> mActiveListeners;
    private final MediaControllerCompat mController;
    private final MediaSessionImpl mImpl;

    /* renamed from: android.support.v4.media.session.MediaSessionCompat$MediaSessionImpl */
    interface MediaSessionImpl {
        String getCallingPackage();

        MediaSessionManager.RemoteUserInfo getCurrentControllerInfo();

        Object getMediaSession();

        PlaybackStateCompat getPlaybackState();

        Object getRemoteControlClient();

        Token getSessionToken();

        boolean isActive();

        void release();

        void sendSessionEvent(String str, Bundle bundle);

        void setActive(boolean z);

        void setCallback(Callback callback, Handler handler);

        void setCaptioningEnabled(boolean z);

        void setCurrentControllerInfo(MediaSessionManager.RemoteUserInfo remoteUserInfo);

        void setExtras(Bundle bundle);

        void setFlags(int i);

        void setMediaButtonReceiver(PendingIntent pendingIntent);

        void setMetadata(MediaMetadataCompat mediaMetadataCompat);

        void setPlaybackState(PlaybackStateCompat playbackStateCompat);

        void setPlaybackToLocal(int i);

        void setPlaybackToRemote(VolumeProviderCompat volumeProviderCompat);

        void setQueue(List<QueueItem> list);

        void setQueueTitle(CharSequence charSequence);

        void setRatingType(int i);

        void setRepeatMode(int i);

        void setSessionActivity(PendingIntent pendingIntent);

        void setShuffleMode(int i);
    }

    /* renamed from: android.support.v4.media.session.MediaSessionCompat$OnActiveChangeListener */
    public interface OnActiveChangeListener {
        void onActiveChanged();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: android.support.v4.media.session.MediaSessionCompat$SessionFlags */
    public @interface SessionFlags {
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public MediaSessionCompat(Context context, String tag) {
        this(context, tag, (ComponentName) null, (PendingIntent) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public MediaSessionCompat(Context context, String tag, ComponentName mbrComponent, PendingIntent mbrIntent) {
        this(context, tag, mbrComponent, mbrIntent, (Bundle) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public MediaSessionCompat(Context context, String tag, Bundle token2Bundle) {
        this(context, tag, (ComponentName) null, (PendingIntent) null, token2Bundle);
    }

    private MediaSessionCompat(Context context, String str, ComponentName componentName, PendingIntent pendingIntent, Bundle bundle) {
        ArrayList<OnActiveChangeListener> arrayList;
        MediaSessionImpl mediaSessionImpl;
        MediaSessionImpl mediaSessionImpl2;
        MediaSessionImpl mediaSessionImpl3;
        MediaSessionImpl mediaSessionImpl4;
        Callback callback;
        MediaControllerCompat mediaControllerCompat;
        MediaSessionImpl mediaSessionImpl5;
        Callback callback2;
        Intent intent;
        Throwable th;
        Throwable th2;
        Context context2 = context;
        String tag = str;
        ComponentName mbrComponent = componentName;
        PendingIntent mbrIntent = pendingIntent;
        Bundle token2Bundle = bundle;
        new ArrayList<>();
        this.mActiveListeners = arrayList;
        if (context2 == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("context must not be null");
            throw th3;
        } else if (TextUtils.isEmpty(tag)) {
            Throwable th4 = th;
            new IllegalArgumentException("tag must not be null or empty");
            throw th4;
        } else {
            if (mbrComponent == null) {
                mbrComponent = MediaButtonReceiver.getMediaButtonReceiverComponent(context2);
                if (mbrComponent == null) {
                    int w = Log.w(TAG, "Couldn't find a unique registered media button receiver in the given context.");
                }
            }
            if (mbrComponent != null && mbrIntent == null) {
                new Intent("android.intent.action.MEDIA_BUTTON");
                Intent mediaButtonIntent = intent;
                Intent component = mediaButtonIntent.setComponent(mbrComponent);
                mbrIntent = PendingIntent.getBroadcast(context2, 0, mediaButtonIntent, 0);
            }
            if (Build.VERSION.SDK_INT >= 28) {
                new MediaSessionImplApi28(context2, tag, token2Bundle);
                this.mImpl = mediaSessionImpl5;
                new Callback(this) {
                    final /* synthetic */ MediaSessionCompat this$0;

                    {
                        this.this$0 = this$0;
                    }
                };
                setCallback(callback2);
                this.mImpl.setMediaButtonReceiver(mbrIntent);
            } else if (Build.VERSION.SDK_INT >= 21) {
                new MediaSessionImplApi21(context2, tag, token2Bundle);
                this.mImpl = mediaSessionImpl4;
                new Callback(this) {
                    final /* synthetic */ MediaSessionCompat this$0;

                    {
                        this.this$0 = this$0;
                    }
                };
                setCallback(callback);
                this.mImpl.setMediaButtonReceiver(mbrIntent);
            } else if (Build.VERSION.SDK_INT >= 19) {
                new MediaSessionImplApi19(context2, tag, mbrComponent, mbrIntent);
                this.mImpl = mediaSessionImpl3;
            } else if (Build.VERSION.SDK_INT >= 18) {
                new MediaSessionImplApi18(context2, tag, mbrComponent, mbrIntent);
                this.mImpl = mediaSessionImpl2;
            } else {
                new MediaSessionImplBase(context2, tag, mbrComponent, mbrIntent);
                this.mImpl = mediaSessionImpl;
            }
            new MediaControllerCompat(context2, this);
            this.mController = mediaControllerCompat;
            if (sMaxBitmapSize == 0) {
                sMaxBitmapSize = (int) (TypedValue.applyDimension(1, 320.0f, context2.getResources().getDisplayMetrics()) + 0.5f);
            }
        }
    }

    private MediaSessionCompat(Context context, MediaSessionImpl mediaSessionImpl) {
        ArrayList<OnActiveChangeListener> arrayList;
        MediaControllerCompat mediaControllerCompat;
        Callback callback;
        Context context2 = context;
        MediaSessionImpl impl = mediaSessionImpl;
        new ArrayList<>();
        this.mActiveListeners = arrayList;
        this.mImpl = impl;
        if (Build.VERSION.SDK_INT >= 21 && !MediaSessionCompatApi21.hasCallback(impl.getMediaSession())) {
            new Callback(this) {
                final /* synthetic */ MediaSessionCompat this$0;

                {
                    this.this$0 = this$0;
                }
            };
            setCallback(callback);
        }
        new MediaControllerCompat(context2, this);
        this.mController = mediaControllerCompat;
    }

    public void setCallback(Callback callback) {
        setCallback(callback, (Handler) null);
    }

    public void setCallback(Callback callback, Handler handler) {
        Handler handler2;
        Handler handler3;
        Callback callback2 = callback;
        Handler handler4 = handler;
        if (callback2 == null) {
            this.mImpl.setCallback((Callback) null, (Handler) null);
            return;
        }
        MediaSessionImpl mediaSessionImpl = this.mImpl;
        Callback callback3 = callback2;
        if (handler4 != null) {
            handler3 = handler4;
        } else {
            handler3 = handler2;
            new Handler();
        }
        mediaSessionImpl.setCallback(callback3, handler3);
    }

    public void setSessionActivity(PendingIntent pi) {
        this.mImpl.setSessionActivity(pi);
    }

    public void setMediaButtonReceiver(PendingIntent mbr) {
        this.mImpl.setMediaButtonReceiver(mbr);
    }

    public void setFlags(int flags) {
        this.mImpl.setFlags(flags);
    }

    public void setPlaybackToLocal(int stream) {
        this.mImpl.setPlaybackToLocal(stream);
    }

    public void setPlaybackToRemote(VolumeProviderCompat volumeProviderCompat) {
        Throwable th;
        VolumeProviderCompat volumeProvider = volumeProviderCompat;
        if (volumeProvider == null) {
            Throwable th2 = th;
            new IllegalArgumentException("volumeProvider may not be null!");
            throw th2;
        }
        this.mImpl.setPlaybackToRemote(volumeProvider);
    }

    public void setActive(boolean active) {
        this.mImpl.setActive(active);
        Iterator<OnActiveChangeListener> it = this.mActiveListeners.iterator();
        while (it.hasNext()) {
            it.next().onActiveChanged();
        }
    }

    public boolean isActive() {
        return this.mImpl.isActive();
    }

    public void sendSessionEvent(String str, Bundle bundle) {
        Throwable th;
        String event = str;
        Bundle extras = bundle;
        if (TextUtils.isEmpty(event)) {
            Throwable th2 = th;
            new IllegalArgumentException("event cannot be null or empty");
            throw th2;
        }
        this.mImpl.sendSessionEvent(event, extras);
    }

    public void release() {
        this.mImpl.release();
    }

    public Token getSessionToken() {
        return this.mImpl.getSessionToken();
    }

    public MediaControllerCompat getController() {
        return this.mController;
    }

    public void setPlaybackState(PlaybackStateCompat state) {
        this.mImpl.setPlaybackState(state);
    }

    public void setMetadata(MediaMetadataCompat metadata) {
        this.mImpl.setMetadata(metadata);
    }

    public void setQueue(List<QueueItem> queue) {
        this.mImpl.setQueue(queue);
    }

    public void setQueueTitle(CharSequence title) {
        this.mImpl.setQueueTitle(title);
    }

    public void setRatingType(int type) {
        this.mImpl.setRatingType(type);
    }

    public void setCaptioningEnabled(boolean enabled) {
        this.mImpl.setCaptioningEnabled(enabled);
    }

    public void setRepeatMode(int repeatMode) {
        this.mImpl.setRepeatMode(repeatMode);
    }

    public void setShuffleMode(int shuffleMode) {
        this.mImpl.setShuffleMode(shuffleMode);
    }

    public void setExtras(Bundle extras) {
        this.mImpl.setExtras(extras);
    }

    public Object getMediaSession() {
        return this.mImpl.getMediaSession();
    }

    public Object getRemoteControlClient() {
        return this.mImpl.getRemoteControlClient();
    }

    @NonNull
    public final MediaSessionManager.RemoteUserInfo getCurrentControllerInfo() {
        return this.mImpl.getCurrentControllerInfo();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public String getCallingPackage() {
        return this.mImpl.getCallingPackage();
    }

    public void addOnActiveChangeListener(OnActiveChangeListener onActiveChangeListener) {
        Throwable th;
        OnActiveChangeListener listener = onActiveChangeListener;
        if (listener == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Listener may not be null");
            throw th2;
        }
        boolean add = this.mActiveListeners.add(listener);
    }

    public void removeOnActiveChangeListener(OnActiveChangeListener onActiveChangeListener) {
        Throwable th;
        OnActiveChangeListener listener = onActiveChangeListener;
        if (listener == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Listener may not be null");
            throw th2;
        }
        boolean remove = this.mActiveListeners.remove(listener);
    }

    public static MediaSessionCompat fromMediaSession(Context context, Object obj) {
        MediaSessionCompat mediaSessionCompat;
        MediaSessionImpl mediaSessionImpl;
        Context context2 = context;
        Object mediaSession = obj;
        if (context2 == null || mediaSession == null || Build.VERSION.SDK_INT < 21) {
            return null;
        }
        new MediaSessionImplApi21(mediaSession);
        new MediaSessionCompat(context2, mediaSessionImpl);
        return mediaSessionCompat;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static void ensureClassLoader(@Nullable Bundle bundle) {
        Bundle bundle2 = bundle;
        if (bundle2 != null) {
            bundle2.setClassLoader(MediaSessionCompat.class.getClassLoader());
        }
    }

    static PlaybackStateCompat getStateWithUpdatedPosition(PlaybackStateCompat playbackStateCompat, MediaMetadataCompat mediaMetadataCompat) {
        PlaybackStateCompat.Builder builder;
        PlaybackStateCompat state = playbackStateCompat;
        MediaMetadataCompat metadata = mediaMetadataCompat;
        if (state == null || state.getPosition() == -1) {
            return state;
        }
        if (state.getState() == 3 || state.getState() == 4 || state.getState() == 5) {
            long updateTime = state.getLastPositionUpdateTime();
            if (updateTime > 0) {
                long currentTime = SystemClock.elapsedRealtime();
                long position = ((long) (state.getPlaybackSpeed() * ((float) (currentTime - updateTime)))) + state.getPosition();
                long duration = -1;
                if (metadata != null && metadata.containsKey(MediaMetadataCompat.METADATA_KEY_DURATION)) {
                    duration = metadata.getLong(MediaMetadataCompat.METADATA_KEY_DURATION);
                }
                if (duration >= 0 && position > duration) {
                    position = duration;
                } else if (position < 0) {
                    position = 0;
                }
                new PlaybackStateCompat.Builder(state);
                return builder.setState(state.getState(), position, state.getPlaybackSpeed(), currentTime).build();
            }
        }
        return state;
    }

    /* renamed from: android.support.v4.media.session.MediaSessionCompat$Callback */
    public static abstract class Callback {
        private CallbackHandler mCallbackHandler = null;
        final Object mCallbackObj;
        private boolean mMediaPlayPauseKeyPending;
        WeakReference<MediaSessionImpl> mSessionImpl;

        public Callback() {
            MediaSessionCompatApi21.Callback callback;
            MediaSessionCompatApi23.Callback callback2;
            MediaSessionCompatApi24.Callback callback3;
            if (Build.VERSION.SDK_INT >= 24) {
                new StubApi24(this);
                this.mCallbackObj = MediaSessionCompatApi24.createCallback(callback3);
            } else if (Build.VERSION.SDK_INT >= 23) {
                new StubApi23(this);
                this.mCallbackObj = MediaSessionCompatApi23.createCallback(callback2);
            } else if (Build.VERSION.SDK_INT >= 21) {
                new StubApi21(this);
                this.mCallbackObj = MediaSessionCompatApi21.createCallback(callback);
            } else {
                this.mCallbackObj = null;
            }
        }

        /* access modifiers changed from: package-private */
        public void setSessionImpl(MediaSessionImpl impl, Handler handler) {
            WeakReference<MediaSessionImpl> weakReference;
            CallbackHandler callbackHandler;
            Handler handler2 = handler;
            new WeakReference<>(impl);
            this.mSessionImpl = weakReference;
            if (this.mCallbackHandler != null) {
                this.mCallbackHandler.removeCallbacksAndMessages((Object) null);
            }
            new CallbackHandler(this, handler2.getLooper());
            this.mCallbackHandler = callbackHandler;
        }

        public void onCommand(String command, Bundle extras, ResultReceiver cb) {
        }

        public boolean onMediaButtonEvent(Intent intent) {
            Intent mediaButtonEvent = intent;
            if (Build.VERSION.SDK_INT >= 27) {
                return false;
            }
            MediaSessionImpl impl = (MediaSessionImpl) this.mSessionImpl.get();
            if (impl == null || this.mCallbackHandler == null) {
                return false;
            }
            KeyEvent keyEvent = (KeyEvent) mediaButtonEvent.getParcelableExtra("android.intent.extra.KEY_EVENT");
            if (keyEvent == null || keyEvent.getAction() != 0) {
                return false;
            }
            MediaSessionManager.RemoteUserInfo remoteUserInfo = impl.getCurrentControllerInfo();
            switch (keyEvent.getKeyCode()) {
                case 79:
                case 85:
                    if (keyEvent.getRepeatCount() > 0) {
                        handleMediaPlayPauseKeySingleTapIfPending(remoteUserInfo);
                    } else if (this.mMediaPlayPauseKeyPending) {
                        this.mCallbackHandler.removeMessages(1);
                        this.mMediaPlayPauseKeyPending = false;
                        PlaybackStateCompat state = impl.getPlaybackState();
                        if (((state == null ? 0 : state.getActions()) & 32) != 0) {
                            onSkipToNext();
                        }
                    } else {
                        this.mMediaPlayPauseKeyPending = true;
                        boolean sendMessageDelayed = this.mCallbackHandler.sendMessageDelayed(this.mCallbackHandler.obtainMessage(1, remoteUserInfo), (long) ViewConfiguration.getDoubleTapTimeout());
                    }
                    return true;
                default:
                    handleMediaPlayPauseKeySingleTapIfPending(remoteUserInfo);
                    return false;
            }
        }

        /* access modifiers changed from: package-private */
        public void handleMediaPlayPauseKeySingleTapIfPending(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
            MediaSessionManager.RemoteUserInfo remoteUserInfo2 = remoteUserInfo;
            if (this.mMediaPlayPauseKeyPending) {
                this.mMediaPlayPauseKeyPending = false;
                this.mCallbackHandler.removeMessages(1);
                MediaSessionImpl impl = (MediaSessionImpl) this.mSessionImpl.get();
                if (impl != null) {
                    PlaybackStateCompat state = impl.getPlaybackState();
                    long validActions = state == null ? 0 : state.getActions();
                    boolean isPlaying = state != null && state.getState() == 3;
                    boolean canPlay = (validActions & 516) != 0;
                    boolean canPause = (validActions & 514) != 0;
                    impl.setCurrentControllerInfo(remoteUserInfo2);
                    if (isPlaying && canPause) {
                        onPause();
                    } else if (!isPlaying && canPlay) {
                        onPlay();
                    }
                    impl.setCurrentControllerInfo((MediaSessionManager.RemoteUserInfo) null);
                }
            }
        }

        public void onPrepare() {
        }

        public void onPrepareFromMediaId(String mediaId, Bundle extras) {
        }

        public void onPrepareFromSearch(String query, Bundle extras) {
        }

        public void onPrepareFromUri(Uri uri, Bundle extras) {
        }

        public void onPlay() {
        }

        public void onPlayFromMediaId(String mediaId, Bundle extras) {
        }

        public void onPlayFromSearch(String query, Bundle extras) {
        }

        public void onPlayFromUri(Uri uri, Bundle extras) {
        }

        public void onSkipToQueueItem(long id) {
        }

        public void onPause() {
        }

        public void onSkipToNext() {
        }

        public void onSkipToPrevious() {
        }

        public void onFastForward() {
        }

        public void onRewind() {
        }

        public void onStop() {
        }

        public void onSeekTo(long pos) {
        }

        public void onSetRating(RatingCompat rating) {
        }

        public void onSetRating(RatingCompat rating, Bundle extras) {
        }

        public void onSetCaptioningEnabled(boolean enabled) {
        }

        public void onSetRepeatMode(int repeatMode) {
        }

        public void onSetShuffleMode(int shuffleMode) {
        }

        public void onCustomAction(String action, Bundle extras) {
        }

        public void onAddQueueItem(MediaDescriptionCompat description) {
        }

        public void onAddQueueItem(MediaDescriptionCompat description, int index) {
        }

        public void onRemoveQueueItem(MediaDescriptionCompat description) {
        }

        @Deprecated
        public void onRemoveQueueItemAt(int index) {
        }

        /* renamed from: android.support.v4.media.session.MediaSessionCompat$Callback$CallbackHandler */
        private class CallbackHandler extends Handler {
            private static final int MSG_MEDIA_PLAY_PAUSE_KEY_DOUBLE_TAP_TIMEOUT = 1;
            final /* synthetic */ Callback this$0;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            CallbackHandler(Callback callback, Looper looper) {
                super(looper);
                this.this$0 = callback;
            }

            public void handleMessage(Message message) {
                Message msg = message;
                if (msg.what == 1) {
                    this.this$0.handleMediaPlayPauseKeySingleTapIfPending((MediaSessionManager.RemoteUserInfo) msg.obj);
                }
            }
        }

        @RequiresApi(21)
        /* renamed from: android.support.v4.media.session.MediaSessionCompat$Callback$StubApi21 */
        private class StubApi21 implements MediaSessionCompatApi21.Callback {
            final /* synthetic */ Callback this$0;

            StubApi21(Callback callback) {
                this.this$0 = callback;
            }

            public void onCommand(String str, Bundle bundle, ResultReceiver resultReceiver) {
                Bundle bundle2;
                IBinder asBinder;
                String command = str;
                Bundle extras = bundle;
                ResultReceiver cb = resultReceiver;
                try {
                    if (command.equals(MediaControllerCompat.COMMAND_GET_EXTRA_BINDER)) {
                        MediaSessionImplApi21 impl = (MediaSessionImplApi21) this.this$0.mSessionImpl.get();
                        if (impl != null) {
                            new Bundle();
                            Bundle result = bundle2;
                            Token token = impl.getSessionToken();
                            IMediaSession extraBinder = token.getExtraBinder();
                            Bundle bundle3 = result;
                            if (extraBinder == null) {
                                asBinder = null;
                            } else {
                                asBinder = extraBinder.asBinder();
                            }
                            BundleCompat.putBinder(bundle3, MediaSessionCompat.KEY_EXTRA_BINDER, asBinder);
                            result.putBundle(MediaSessionCompat.KEY_SESSION_TOKEN2_BUNDLE, token.getSessionToken2Bundle());
                            cb.send(0, result);
                        }
                    } else if (command.equals(MediaControllerCompat.COMMAND_ADD_QUEUE_ITEM)) {
                        this.this$0.onAddQueueItem((MediaDescriptionCompat) extras.getParcelable(MediaControllerCompat.COMMAND_ARGUMENT_MEDIA_DESCRIPTION));
                    } else if (command.equals(MediaControllerCompat.COMMAND_ADD_QUEUE_ITEM_AT)) {
                        this.this$0.onAddQueueItem((MediaDescriptionCompat) extras.getParcelable(MediaControllerCompat.COMMAND_ARGUMENT_MEDIA_DESCRIPTION), extras.getInt(MediaControllerCompat.COMMAND_ARGUMENT_INDEX));
                    } else if (command.equals(MediaControllerCompat.COMMAND_REMOVE_QUEUE_ITEM)) {
                        this.this$0.onRemoveQueueItem((MediaDescriptionCompat) extras.getParcelable(MediaControllerCompat.COMMAND_ARGUMENT_MEDIA_DESCRIPTION));
                    } else if (command.equals(MediaControllerCompat.COMMAND_REMOVE_QUEUE_ITEM_AT)) {
                        MediaSessionImplApi21 impl2 = (MediaSessionImplApi21) this.this$0.mSessionImpl.get();
                        if (!(impl2 == null || impl2.mQueue == null)) {
                            int index = extras.getInt(MediaControllerCompat.COMMAND_ARGUMENT_INDEX, -1);
                            QueueItem item = (index < 0 || index >= impl2.mQueue.size()) ? null : impl2.mQueue.get(index);
                            if (item != null) {
                                this.this$0.onRemoveQueueItem(item.getDescription());
                            }
                        }
                    } else {
                        this.this$0.onCommand(command, extras, cb);
                    }
                } catch (BadParcelableException e) {
                    BadParcelableException badParcelableException = e;
                    int e2 = Log.e(MediaSessionCompat.TAG, "Could not unparcel the extra data.");
                }
            }

            public boolean onMediaButtonEvent(Intent mediaButtonIntent) {
                return this.this$0.onMediaButtonEvent(mediaButtonIntent);
            }

            public void onPlay() {
                this.this$0.onPlay();
            }

            public void onPlayFromMediaId(String mediaId, Bundle extras) {
                this.this$0.onPlayFromMediaId(mediaId, extras);
            }

            public void onPlayFromSearch(String search, Bundle extras) {
                this.this$0.onPlayFromSearch(search, extras);
            }

            public void onSkipToQueueItem(long id) {
                this.this$0.onSkipToQueueItem(id);
            }

            public void onPause() {
                this.this$0.onPause();
            }

            public void onSkipToNext() {
                this.this$0.onSkipToNext();
            }

            public void onSkipToPrevious() {
                this.this$0.onSkipToPrevious();
            }

            public void onFastForward() {
                this.this$0.onFastForward();
            }

            public void onRewind() {
                this.this$0.onRewind();
            }

            public void onStop() {
                this.this$0.onStop();
            }

            public void onSeekTo(long pos) {
                this.this$0.onSeekTo(pos);
            }

            public void onSetRating(Object ratingObj) {
                this.this$0.onSetRating(RatingCompat.fromRating(ratingObj));
            }

            public void onSetRating(Object ratingObj, Bundle extras) {
            }

            public void onCustomAction(String str, Bundle bundle) {
                String action = str;
                Bundle extras = bundle;
                Bundle bundle2 = extras.getBundle(MediaSessionCompat.ACTION_ARGUMENT_EXTRAS);
                MediaSessionCompat.ensureClassLoader(bundle2);
                if (action.equals(MediaSessionCompat.ACTION_PLAY_FROM_URI)) {
                    this.this$0.onPlayFromUri((Uri) extras.getParcelable(MediaSessionCompat.ACTION_ARGUMENT_URI), bundle2);
                } else if (action.equals(MediaSessionCompat.ACTION_PREPARE)) {
                    this.this$0.onPrepare();
                } else if (action.equals(MediaSessionCompat.ACTION_PREPARE_FROM_MEDIA_ID)) {
                    this.this$0.onPrepareFromMediaId(extras.getString(MediaSessionCompat.ACTION_ARGUMENT_MEDIA_ID), bundle2);
                } else if (action.equals(MediaSessionCompat.ACTION_PREPARE_FROM_SEARCH)) {
                    this.this$0.onPrepareFromSearch(extras.getString(MediaSessionCompat.ACTION_ARGUMENT_QUERY), bundle2);
                } else if (action.equals(MediaSessionCompat.ACTION_PREPARE_FROM_URI)) {
                    this.this$0.onPrepareFromUri((Uri) extras.getParcelable(MediaSessionCompat.ACTION_ARGUMENT_URI), bundle2);
                } else if (action.equals(MediaSessionCompat.ACTION_SET_CAPTIONING_ENABLED)) {
                    this.this$0.onSetCaptioningEnabled(extras.getBoolean(MediaSessionCompat.ACTION_ARGUMENT_CAPTIONING_ENABLED));
                } else if (action.equals(MediaSessionCompat.ACTION_SET_REPEAT_MODE)) {
                    this.this$0.onSetRepeatMode(extras.getInt(MediaSessionCompat.ACTION_ARGUMENT_REPEAT_MODE));
                } else if (action.equals(MediaSessionCompat.ACTION_SET_SHUFFLE_MODE)) {
                    this.this$0.onSetShuffleMode(extras.getInt(MediaSessionCompat.ACTION_ARGUMENT_SHUFFLE_MODE));
                } else if (action.equals(MediaSessionCompat.ACTION_SET_RATING)) {
                    this.this$0.onSetRating((RatingCompat) extras.getParcelable(MediaSessionCompat.ACTION_ARGUMENT_RATING), bundle2);
                } else {
                    this.this$0.onCustomAction(action, extras);
                }
            }
        }

        @RequiresApi(23)
        /* renamed from: android.support.v4.media.session.MediaSessionCompat$Callback$StubApi23 */
        private class StubApi23 extends StubApi21 implements MediaSessionCompatApi23.Callback {
            final /* synthetic */ Callback this$0;

            /* JADX WARNING: Illegal instructions before constructor call */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            StubApi23(android.support.p000v4.media.session.MediaSessionCompat.Callback r5) {
                /*
                    r4 = this;
                    r0 = r4
                    r1 = r5
                    r2 = r0
                    r3 = r1
                    r2.this$0 = r3
                    r2 = r0
                    r3 = r1
                    r2.<init>(r3)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.media.session.MediaSessionCompat.Callback.StubApi23.<init>(android.support.v4.media.session.MediaSessionCompat$Callback):void");
            }

            public void onPlayFromUri(Uri uri, Bundle extras) {
                this.this$0.onPlayFromUri(uri, extras);
            }
        }

        @RequiresApi(24)
        /* renamed from: android.support.v4.media.session.MediaSessionCompat$Callback$StubApi24 */
        private class StubApi24 extends StubApi23 implements MediaSessionCompatApi24.Callback {
            final /* synthetic */ Callback this$0;

            /* JADX WARNING: Illegal instructions before constructor call */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            StubApi24(android.support.p000v4.media.session.MediaSessionCompat.Callback r5) {
                /*
                    r4 = this;
                    r0 = r4
                    r1 = r5
                    r2 = r0
                    r3 = r1
                    r2.this$0 = r3
                    r2 = r0
                    r3 = r1
                    r2.<init>(r3)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.media.session.MediaSessionCompat.Callback.StubApi24.<init>(android.support.v4.media.session.MediaSessionCompat$Callback):void");
            }

            public void onPrepare() {
                this.this$0.onPrepare();
            }

            public void onPrepareFromMediaId(String mediaId, Bundle extras) {
                this.this$0.onPrepareFromMediaId(mediaId, extras);
            }

            public void onPrepareFromSearch(String query, Bundle extras) {
                this.this$0.onPrepareFromSearch(query, extras);
            }

            public void onPrepareFromUri(Uri uri, Bundle extras) {
                this.this$0.onPrepareFromUri(uri, extras);
            }
        }
    }

    /* renamed from: android.support.v4.media.session.MediaSessionCompat$Token */
    public static final class Token implements Parcelable {
        public static final Parcelable.Creator<Token> CREATOR;
        private IMediaSession mExtraBinder;
        private final Object mInner;
        private Bundle mSessionToken2Bundle;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        Token(Object inner) {
            this(inner, (IMediaSession) null, (Bundle) null);
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        Token(Object inner, IMediaSession extraBinder) {
            this(inner, extraBinder, (Bundle) null);
        }

        Token(Object inner, IMediaSession extraBinder, Bundle token2Bundle) {
            this.mInner = inner;
            this.mExtraBinder = extraBinder;
            this.mSessionToken2Bundle = token2Bundle;
        }

        public static Token fromToken(Object token) {
            return fromToken(token, (IMediaSession) null);
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public static Token fromToken(Object obj, IMediaSession iMediaSession) {
            Token token;
            Object token2 = obj;
            IMediaSession extraBinder = iMediaSession;
            if (token2 == null || Build.VERSION.SDK_INT < 21) {
                return null;
            }
            new Token(MediaSessionCompatApi21.verifyToken(token2), extraBinder);
            return token;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            Parcel dest = parcel;
            int flags = i;
            if (Build.VERSION.SDK_INT >= 21) {
                dest.writeParcelable((Parcelable) this.mInner, flags);
            } else {
                dest.writeStrongBinder((IBinder) this.mInner);
            }
        }

        public int hashCode() {
            if (this.mInner == null) {
                return 0;
            }
            return this.mInner.hashCode();
        }

        public boolean equals(Object obj) {
            Object obj2 = obj;
            if (this == obj2) {
                return true;
            }
            if (!(obj2 instanceof Token)) {
                return false;
            }
            Token other = (Token) obj2;
            if (this.mInner == null) {
                return other.mInner == null;
            } else if (other.mInner == null) {
                return false;
            } else {
                return this.mInner.equals(other.mInner);
            }
        }

        public Object getToken() {
            return this.mInner;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public IMediaSession getExtraBinder() {
            return this.mExtraBinder;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void setExtraBinder(IMediaSession extraBinder) {
            IMediaSession iMediaSession = extraBinder;
            this.mExtraBinder = iMediaSession;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Bundle getSessionToken2Bundle() {
            return this.mSessionToken2Bundle;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public void setSessionToken2Bundle(Bundle token2Bundle) {
            Bundle bundle = token2Bundle;
            this.mSessionToken2Bundle = bundle;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public Bundle toBundle() {
            Bundle bundle;
            new Bundle();
            Bundle bundle2 = bundle;
            bundle2.putParcelable(MediaSessionCompat.KEY_TOKEN, this);
            if (this.mExtraBinder != null) {
                BundleCompat.putBinder(bundle2, MediaSessionCompat.KEY_EXTRA_BINDER, this.mExtraBinder.asBinder());
            }
            if (this.mSessionToken2Bundle != null) {
                bundle2.putBundle(MediaSessionCompat.KEY_SESSION_TOKEN2_BUNDLE, this.mSessionToken2Bundle);
            }
            return bundle2;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public static Token fromBundle(Bundle bundle) {
            Token token;
            Token token2;
            Bundle tokenBundle = bundle;
            if (tokenBundle == null) {
                return null;
            }
            IMediaSession extraSession = IMediaSession.Stub.asInterface(BundleCompat.getBinder(tokenBundle, MediaSessionCompat.KEY_EXTRA_BINDER));
            Bundle token2Bundle = tokenBundle.getBundle(MediaSessionCompat.KEY_SESSION_TOKEN2_BUNDLE);
            Token token3 = (Token) tokenBundle.getParcelable(MediaSessionCompat.KEY_TOKEN);
            if (token3 == null) {
                token2 = null;
            } else {
                token2 = token;
                new Token(token3.mInner, extraSession, token2Bundle);
            }
            return token2;
        }

        static {
            Parcelable.Creator<Token> creator;
            new Parcelable.Creator<Token>() {
                public Token createFromParcel(Parcel parcel) {
                    Object readStrongBinder;
                    Token token;
                    Parcel in = parcel;
                    if (Build.VERSION.SDK_INT >= 21) {
                        readStrongBinder = in.readParcelable((ClassLoader) null);
                    } else {
                        readStrongBinder = in.readStrongBinder();
                    }
                    new Token(readStrongBinder);
                    return token;
                }

                public Token[] newArray(int size) {
                    return new Token[size];
                }
            };
            CREATOR = creator;
        }
    }

    /* renamed from: android.support.v4.media.session.MediaSessionCompat$QueueItem */
    public static final class QueueItem implements Parcelable {
        public static final Parcelable.Creator<QueueItem> CREATOR;
        public static final int UNKNOWN_ID = -1;
        private final MediaDescriptionCompat mDescription;
        private final long mId;
        private Object mItem;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public QueueItem(MediaDescriptionCompat description, long id) {
            this((Object) null, description, id);
        }

        private QueueItem(Object obj, MediaDescriptionCompat mediaDescriptionCompat, long j) {
            Throwable th;
            Throwable th2;
            Object queueItem = obj;
            MediaDescriptionCompat description = mediaDescriptionCompat;
            long id = j;
            if (description == null) {
                Throwable th3 = th2;
                new IllegalArgumentException("Description cannot be null.");
                throw th3;
            } else if (id == -1) {
                Throwable th4 = th;
                new IllegalArgumentException("Id cannot be QueueItem.UNKNOWN_ID");
                throw th4;
            } else {
                this.mDescription = description;
                this.mId = id;
                this.mItem = queueItem;
            }
        }

        QueueItem(Parcel parcel) {
            Parcel in = parcel;
            this.mDescription = MediaDescriptionCompat.CREATOR.createFromParcel(in);
            this.mId = in.readLong();
        }

        public MediaDescriptionCompat getDescription() {
            return this.mDescription;
        }

        public long getQueueId() {
            return this.mId;
        }

        public void writeToParcel(Parcel parcel, int flags) {
            Parcel dest = parcel;
            this.mDescription.writeToParcel(dest, flags);
            dest.writeLong(this.mId);
        }

        public int describeContents() {
            return 0;
        }

        public Object getQueueItem() {
            if (this.mItem != null || Build.VERSION.SDK_INT < 21) {
                return this.mItem;
            }
            this.mItem = MediaSessionCompatApi21.QueueItem.createItem(this.mDescription.getMediaDescription(), this.mId);
            return this.mItem;
        }

        public static QueueItem fromQueueItem(Object obj) {
            QueueItem queueItem;
            Object queueItem2 = obj;
            if (queueItem2 == null || Build.VERSION.SDK_INT < 21) {
                return null;
            }
            new QueueItem(queueItem2, MediaDescriptionCompat.fromMediaDescription(MediaSessionCompatApi21.QueueItem.getDescription(queueItem2)), MediaSessionCompatApi21.QueueItem.getQueueId(queueItem2));
            return queueItem;
        }

        public static List<QueueItem> fromQueueItemList(List<?> list) {
            List<?> list2;
            List<?> itemList = list;
            if (itemList == null || Build.VERSION.SDK_INT < 21) {
                return null;
            }
            new ArrayList<>();
            List<?> items = list2;
            for (Object itemObj : itemList) {
                boolean add = items.add(fromQueueItem(itemObj));
            }
            return items;
        }

        static {
            Parcelable.Creator<QueueItem> creator;
            new Parcelable.Creator<QueueItem>() {
                public QueueItem createFromParcel(Parcel p) {
                    QueueItem queueItem;
                    new QueueItem(p);
                    return queueItem;
                }

                public QueueItem[] newArray(int size) {
                    return new QueueItem[size];
                }
            };
            CREATOR = creator;
        }

        public String toString() {
            StringBuilder sb;
            new StringBuilder();
            return sb.append("MediaSession.QueueItem {Description=").append(this.mDescription).append(", Id=").append(this.mId).append(" }").toString();
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* renamed from: android.support.v4.media.session.MediaSessionCompat$ResultReceiverWrapper */
    public static final class ResultReceiverWrapper implements Parcelable {
        public static final Parcelable.Creator<ResultReceiverWrapper> CREATOR;
        ResultReceiver mResultReceiver;

        public ResultReceiverWrapper(ResultReceiver resultReceiver) {
            this.mResultReceiver = resultReceiver;
        }

        ResultReceiverWrapper(Parcel in) {
            this.mResultReceiver = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(in);
        }

        static {
            Parcelable.Creator<ResultReceiverWrapper> creator;
            new Parcelable.Creator<ResultReceiverWrapper>() {
                public ResultReceiverWrapper createFromParcel(Parcel p) {
                    ResultReceiverWrapper resultReceiverWrapper;
                    new ResultReceiverWrapper(p);
                    return resultReceiverWrapper;
                }

                public ResultReceiverWrapper[] newArray(int size) {
                    return new ResultReceiverWrapper[size];
                }
            };
            CREATOR = creator;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel dest, int flags) {
            this.mResultReceiver.writeToParcel(dest, flags);
        }
    }

    /* renamed from: android.support.v4.media.session.MediaSessionCompat$MediaSessionImplBase */
    static class MediaSessionImplBase implements MediaSessionImpl {
        static final int RCC_PLAYSTATE_NONE = 0;
        final AudioManager mAudioManager;
        volatile Callback mCallback;
        boolean mCaptioningEnabled;
        private final Context mContext;
        final RemoteCallbackList<IMediaControllerCallback> mControllerCallbacks;
        boolean mDestroyed = false;
        Bundle mExtras;
        int mFlags;
        private MessageHandler mHandler;
        boolean mIsActive = false;
        private boolean mIsMbrRegistered = false;
        private boolean mIsRccRegistered = false;
        int mLocalStream;
        final Object mLock;
        private final ComponentName mMediaButtonReceiverComponentName;
        private final PendingIntent mMediaButtonReceiverIntent;
        MediaMetadataCompat mMetadata;
        final String mPackageName;
        List<QueueItem> mQueue;
        CharSequence mQueueTitle;
        int mRatingType;
        final RemoteControlClient mRcc;
        private MediaSessionManager.RemoteUserInfo mRemoteUserInfo;
        int mRepeatMode;
        PendingIntent mSessionActivity;
        int mShuffleMode;
        PlaybackStateCompat mState;
        private final MediaSessionStub mStub;
        final String mTag;
        private final Token mToken;
        private VolumeProviderCompat.Callback mVolumeCallback;
        VolumeProviderCompat mVolumeProvider;
        int mVolumeType;

        public MediaSessionImplBase(Context context, String str, ComponentName componentName, PendingIntent pendingIntent) {
            Object obj;
            RemoteCallbackList<IMediaControllerCallback> remoteCallbackList;
            VolumeProviderCompat.Callback callback;
            MediaSessionStub mediaSessionStub;
            Token token;
            RemoteControlClient remoteControlClient;
            Throwable th;
            Context context2 = context;
            String tag = str;
            ComponentName mbrComponent = componentName;
            PendingIntent mbrIntent = pendingIntent;
            new Object();
            this.mLock = obj;
            new RemoteCallbackList<>();
            this.mControllerCallbacks = remoteCallbackList;
            new VolumeProviderCompat.Callback(this) {
                final /* synthetic */ MediaSessionImplBase this$0;

                {
                    this.this$0 = this$0;
                }

                public void onVolumeChanged(VolumeProviderCompat volumeProviderCompat) {
                    ParcelableVolumeInfo info;
                    VolumeProviderCompat volumeProvider = volumeProviderCompat;
                    if (this.this$0.mVolumeProvider == volumeProvider) {
                        new ParcelableVolumeInfo(this.this$0.mVolumeType, this.this$0.mLocalStream, volumeProvider.getVolumeControl(), volumeProvider.getMaxVolume(), volumeProvider.getCurrentVolume());
                        this.this$0.sendVolumeInfoChanged(info);
                    }
                }
            };
            this.mVolumeCallback = callback;
            if (mbrComponent == null) {
                Throwable th2 = th;
                new IllegalArgumentException("MediaButtonReceiver component may not be null.");
                throw th2;
            }
            this.mContext = context2;
            this.mPackageName = context2.getPackageName();
            this.mAudioManager = (AudioManager) context2.getSystemService("audio");
            this.mTag = tag;
            this.mMediaButtonReceiverComponentName = mbrComponent;
            this.mMediaButtonReceiverIntent = mbrIntent;
            new MediaSessionStub(this);
            this.mStub = mediaSessionStub;
            new Token(this.mStub);
            this.mToken = token;
            this.mRatingType = 0;
            this.mVolumeType = 1;
            this.mLocalStream = 3;
            new RemoteControlClient(mbrIntent);
            this.mRcc = remoteControlClient;
        }

        /* JADX INFO: finally extract failed */
        public void setCallback(Callback callback, Handler handler) {
            MessageHandler messageHandler;
            Handler handler2;
            Callback callback2 = callback;
            Handler handler3 = handler;
            this.mCallback = callback2;
            if (callback2 != null) {
                if (handler3 == null) {
                    new Handler();
                    handler3 = handler2;
                }
                Object obj = this.mLock;
                Object obj2 = obj;
                synchronized (obj) {
                    try {
                        if (this.mHandler != null) {
                            this.mHandler.removeCallbacksAndMessages((Object) null);
                        }
                        new MessageHandler(this, handler3.getLooper());
                        this.mHandler = messageHandler;
                        this.mCallback.setSessionImpl(this, handler3);
                    } catch (Throwable th) {
                        Throwable th2 = th;
                        Object obj3 = obj2;
                        throw th2;
                    }
                }
            }
        }

        /* JADX INFO: finally extract failed */
        /* access modifiers changed from: package-private */
        public void postToHandler(int i, int i2, int i3, Object obj, Bundle bundle) {
            Bundle bundle2;
            int what = i;
            int arg1 = i2;
            int arg2 = i3;
            Object obj2 = obj;
            Bundle extras = bundle;
            Object obj3 = this.mLock;
            Object obj4 = obj3;
            synchronized (obj3) {
                try {
                    if (this.mHandler != null) {
                        Message msg = this.mHandler.obtainMessage(what, arg1, arg2, obj2);
                        new Bundle();
                        Bundle data = bundle2;
                        data.putString(MediaSessionCompat.DATA_CALLING_PACKAGE, MediaSessionManager.RemoteUserInfo.LEGACY_CONTROLLER);
                        data.putInt("data_calling_pid", Binder.getCallingPid());
                        data.putInt("data_calling_uid", Binder.getCallingUid());
                        if (extras != null) {
                            data.putBundle(MediaSessionCompat.DATA_EXTRAS, extras);
                        }
                        msg.setData(data);
                        msg.sendToTarget();
                    }
                } catch (Throwable th) {
                    Throwable th2 = th;
                    Object obj5 = obj4;
                    throw th2;
                }
            }
        }

        public void setFlags(int i) {
            int flags = i;
            Object obj = this.mLock;
            Object obj2 = obj;
            synchronized (obj) {
                try {
                    this.mFlags = flags;
                    boolean update = update();
                } catch (Throwable th) {
                    while (true) {
                        Throwable th2 = th;
                        Object obj3 = obj2;
                        throw th2;
                    }
                }
            }
        }

        public void setPlaybackToLocal(int i) {
            ParcelableVolumeInfo info;
            int stream = i;
            if (this.mVolumeProvider != null) {
                this.mVolumeProvider.setCallback((VolumeProviderCompat.Callback) null);
            }
            this.mLocalStream = stream;
            this.mVolumeType = 1;
            new ParcelableVolumeInfo(this.mVolumeType, this.mLocalStream, 2, this.mAudioManager.getStreamMaxVolume(this.mLocalStream), this.mAudioManager.getStreamVolume(this.mLocalStream));
            sendVolumeInfoChanged(info);
        }

        public void setPlaybackToRemote(VolumeProviderCompat volumeProviderCompat) {
            ParcelableVolumeInfo info;
            Throwable th;
            VolumeProviderCompat volumeProvider = volumeProviderCompat;
            if (volumeProvider == null) {
                Throwable th2 = th;
                new IllegalArgumentException("volumeProvider may not be null");
                throw th2;
            }
            if (this.mVolumeProvider != null) {
                this.mVolumeProvider.setCallback((VolumeProviderCompat.Callback) null);
            }
            this.mVolumeType = 2;
            this.mVolumeProvider = volumeProvider;
            new ParcelableVolumeInfo(this.mVolumeType, this.mLocalStream, this.mVolumeProvider.getVolumeControl(), this.mVolumeProvider.getMaxVolume(), this.mVolumeProvider.getCurrentVolume());
            sendVolumeInfoChanged(info);
            volumeProvider.setCallback(this.mVolumeCallback);
        }

        public void setActive(boolean z) {
            boolean active = z;
            if (active != this.mIsActive) {
                this.mIsActive = active;
                if (update()) {
                    setMetadata(this.mMetadata);
                    setPlaybackState(this.mState);
                }
            }
        }

        public boolean isActive() {
            return this.mIsActive;
        }

        public void sendSessionEvent(String event, Bundle extras) {
            sendEvent(event, extras);
        }

        public void release() {
            this.mIsActive = false;
            this.mDestroyed = true;
            boolean update = update();
            sendSessionDestroyed();
        }

        public Token getSessionToken() {
            return this.mToken;
        }

        /* JADX INFO: finally extract failed */
        public void setPlaybackState(PlaybackStateCompat playbackStateCompat) {
            PlaybackStateCompat state = playbackStateCompat;
            Object obj = this.mLock;
            Object obj2 = obj;
            synchronized (obj) {
                try {
                    this.mState = state;
                    sendState(state);
                    if (this.mIsActive) {
                        if (state == null) {
                            this.mRcc.setPlaybackState(0);
                            this.mRcc.setTransportControlFlags(0);
                            return;
                        }
                        setRccState(state);
                        this.mRcc.setTransportControlFlags(getRccTransportControlFlagsFromActions(state.getActions()));
                    }
                } catch (Throwable th) {
                    while (true) {
                        Throwable th2 = th;
                        Object obj3 = obj2;
                        throw th2;
                    }
                }
            }
        }

        public PlaybackStateCompat getPlaybackState() {
            Object obj = this.mLock;
            Object obj2 = obj;
            synchronized (obj) {
                try {
                    PlaybackStateCompat playbackStateCompat = this.mState;
                    return playbackStateCompat;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    Object obj3 = obj2;
                    throw th2;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void setRccState(PlaybackStateCompat state) {
            this.mRcc.setPlaybackState(getRccStateFromState(state.getState()));
        }

        /* access modifiers changed from: package-private */
        public int getRccStateFromState(int state) {
            switch (state) {
                case 0:
                    return 0;
                case 1:
                    return 1;
                case 2:
                    return 2;
                case 3:
                    return 3;
                case 4:
                    return 4;
                case 5:
                    return 5;
                case 6:
                case 8:
                    return 8;
                case 7:
                    return 9;
                case 9:
                    return 7;
                case 10:
                case 11:
                    return 6;
                default:
                    return -1;
            }
        }

        /* access modifiers changed from: package-private */
        public int getRccTransportControlFlagsFromActions(long j) {
            long actions = j;
            int transportControlFlags = 0;
            if ((actions & 1) != 0) {
                transportControlFlags = 0 | 32;
            }
            if ((actions & 2) != 0) {
                transportControlFlags |= 16;
            }
            if ((actions & 4) != 0) {
                transportControlFlags |= 4;
            }
            if ((actions & 8) != 0) {
                transportControlFlags |= 2;
            }
            if ((actions & 16) != 0) {
                transportControlFlags |= 1;
            }
            if ((actions & 32) != 0) {
                transportControlFlags |= 128;
            }
            if ((actions & 64) != 0) {
                transportControlFlags |= 64;
            }
            if ((actions & 512) != 0) {
                transportControlFlags |= 8;
            }
            return transportControlFlags;
        }

        /* JADX INFO: finally extract failed */
        public void setMetadata(MediaMetadataCompat mediaMetadataCompat) {
            Bundle bundle;
            MediaMetadataCompat.Builder builder;
            MediaMetadataCompat metadata = mediaMetadataCompat;
            if (metadata != null) {
                new MediaMetadataCompat.Builder(metadata, MediaSessionCompat.sMaxBitmapSize);
                metadata = builder.build();
            }
            Object obj = this.mLock;
            Object obj2 = obj;
            synchronized (obj) {
                try {
                    this.mMetadata = metadata;
                    sendMetadata(metadata);
                    if (this.mIsActive) {
                        if (metadata == null) {
                            bundle = null;
                        } else {
                            bundle = metadata.getBundle();
                        }
                        buildRccMetadata(bundle).apply();
                    }
                } catch (Throwable th) {
                    while (true) {
                        Throwable th2 = th;
                        Object obj3 = obj2;
                        throw th2;
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public RemoteControlClient.MetadataEditor buildRccMetadata(Bundle bundle) {
            Bundle metadata = bundle;
            RemoteControlClient.MetadataEditor editor = this.mRcc.editMetadata(true);
            if (metadata == null) {
                return editor;
            }
            if (metadata.containsKey(MediaMetadataCompat.METADATA_KEY_ART)) {
                Bitmap art = (Bitmap) metadata.getParcelable(MediaMetadataCompat.METADATA_KEY_ART);
                if (art != null) {
                    art = art.copy(art.getConfig(), false);
                }
                RemoteControlClient.MetadataEditor putBitmap = editor.putBitmap(100, art);
            } else if (metadata.containsKey(MediaMetadataCompat.METADATA_KEY_ALBUM_ART)) {
                Bitmap art2 = (Bitmap) metadata.getParcelable(MediaMetadataCompat.METADATA_KEY_ALBUM_ART);
                if (art2 != null) {
                    art2 = art2.copy(art2.getConfig(), false);
                }
                RemoteControlClient.MetadataEditor putBitmap2 = editor.putBitmap(100, art2);
            }
            if (metadata.containsKey(MediaMetadataCompat.METADATA_KEY_ALBUM)) {
                RemoteControlClient.MetadataEditor putString = editor.putString(1, metadata.getString(MediaMetadataCompat.METADATA_KEY_ALBUM));
            }
            if (metadata.containsKey(MediaMetadataCompat.METADATA_KEY_ALBUM_ARTIST)) {
                RemoteControlClient.MetadataEditor putString2 = editor.putString(13, metadata.getString(MediaMetadataCompat.METADATA_KEY_ALBUM_ARTIST));
            }
            if (metadata.containsKey(MediaMetadataCompat.METADATA_KEY_ARTIST)) {
                RemoteControlClient.MetadataEditor putString3 = editor.putString(2, metadata.getString(MediaMetadataCompat.METADATA_KEY_ARTIST));
            }
            if (metadata.containsKey(MediaMetadataCompat.METADATA_KEY_AUTHOR)) {
                RemoteControlClient.MetadataEditor putString4 = editor.putString(3, metadata.getString(MediaMetadataCompat.METADATA_KEY_AUTHOR));
            }
            if (metadata.containsKey(MediaMetadataCompat.METADATA_KEY_COMPILATION)) {
                RemoteControlClient.MetadataEditor putString5 = editor.putString(15, metadata.getString(MediaMetadataCompat.METADATA_KEY_COMPILATION));
            }
            if (metadata.containsKey(MediaMetadataCompat.METADATA_KEY_COMPOSER)) {
                RemoteControlClient.MetadataEditor putString6 = editor.putString(4, metadata.getString(MediaMetadataCompat.METADATA_KEY_COMPOSER));
            }
            if (metadata.containsKey(MediaMetadataCompat.METADATA_KEY_DATE)) {
                RemoteControlClient.MetadataEditor putString7 = editor.putString(5, metadata.getString(MediaMetadataCompat.METADATA_KEY_DATE));
            }
            if (metadata.containsKey(MediaMetadataCompat.METADATA_KEY_DISC_NUMBER)) {
                RemoteControlClient.MetadataEditor putLong = editor.putLong(14, metadata.getLong(MediaMetadataCompat.METADATA_KEY_DISC_NUMBER));
            }
            if (metadata.containsKey(MediaMetadataCompat.METADATA_KEY_DURATION)) {
                RemoteControlClient.MetadataEditor putLong2 = editor.putLong(9, metadata.getLong(MediaMetadataCompat.METADATA_KEY_DURATION));
            }
            if (metadata.containsKey(MediaMetadataCompat.METADATA_KEY_GENRE)) {
                RemoteControlClient.MetadataEditor putString8 = editor.putString(6, metadata.getString(MediaMetadataCompat.METADATA_KEY_GENRE));
            }
            if (metadata.containsKey(MediaMetadataCompat.METADATA_KEY_TITLE)) {
                RemoteControlClient.MetadataEditor putString9 = editor.putString(7, metadata.getString(MediaMetadataCompat.METADATA_KEY_TITLE));
            }
            if (metadata.containsKey(MediaMetadataCompat.METADATA_KEY_TRACK_NUMBER)) {
                RemoteControlClient.MetadataEditor putLong3 = editor.putLong(0, metadata.getLong(MediaMetadataCompat.METADATA_KEY_TRACK_NUMBER));
            }
            if (metadata.containsKey(MediaMetadataCompat.METADATA_KEY_WRITER)) {
                RemoteControlClient.MetadataEditor putString10 = editor.putString(11, metadata.getString(MediaMetadataCompat.METADATA_KEY_WRITER));
            }
            return editor;
        }

        public void setSessionActivity(PendingIntent pendingIntent) {
            PendingIntent pi = pendingIntent;
            Object obj = this.mLock;
            Object obj2 = obj;
            synchronized (obj) {
                try {
                    this.mSessionActivity = pi;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    Object obj3 = obj2;
                    throw th2;
                }
            }
        }

        public void setMediaButtonReceiver(PendingIntent mbr) {
        }

        public void setQueue(List<QueueItem> list) {
            List<QueueItem> queue = list;
            this.mQueue = queue;
            sendQueue(queue);
        }

        public void setQueueTitle(CharSequence charSequence) {
            CharSequence title = charSequence;
            this.mQueueTitle = title;
            sendQueueTitle(title);
        }

        public Object getMediaSession() {
            return null;
        }

        public Object getRemoteControlClient() {
            return null;
        }

        public String getCallingPackage() {
            return null;
        }

        public void setRatingType(int type) {
            int i = type;
            this.mRatingType = i;
        }

        public void setCaptioningEnabled(boolean z) {
            boolean enabled = z;
            if (this.mCaptioningEnabled != enabled) {
                this.mCaptioningEnabled = enabled;
                sendCaptioningEnabled(enabled);
            }
        }

        public void setRepeatMode(int i) {
            int repeatMode = i;
            if (this.mRepeatMode != repeatMode) {
                this.mRepeatMode = repeatMode;
                sendRepeatMode(repeatMode);
            }
        }

        public void setShuffleMode(int i) {
            int shuffleMode = i;
            if (this.mShuffleMode != shuffleMode) {
                this.mShuffleMode = shuffleMode;
                sendShuffleMode(shuffleMode);
            }
        }

        public void setExtras(Bundle bundle) {
            Bundle extras = bundle;
            this.mExtras = extras;
            sendExtras(extras);
        }

        public MediaSessionManager.RemoteUserInfo getCurrentControllerInfo() {
            Object obj = this.mLock;
            Object obj2 = obj;
            synchronized (obj) {
                try {
                    MediaSessionManager.RemoteUserInfo remoteUserInfo = this.mRemoteUserInfo;
                    return remoteUserInfo;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    Object obj3 = obj2;
                    throw th2;
                }
            }
        }

        public void setCurrentControllerInfo(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
            MediaSessionManager.RemoteUserInfo remoteUserInfo2 = remoteUserInfo;
            Object obj = this.mLock;
            Object obj2 = obj;
            synchronized (obj) {
                try {
                    this.mRemoteUserInfo = remoteUserInfo2;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    Object obj3 = obj2;
                    throw th2;
                }
            }
        }

        /* access modifiers changed from: package-private */
        public boolean update() {
            boolean registeredRcc = false;
            if (this.mIsActive) {
                if (!this.mIsMbrRegistered && (this.mFlags & 1) != 0) {
                    registerMediaButtonEventReceiver(this.mMediaButtonReceiverIntent, this.mMediaButtonReceiverComponentName);
                    this.mIsMbrRegistered = true;
                } else if (this.mIsMbrRegistered && (this.mFlags & 1) == 0) {
                    unregisterMediaButtonEventReceiver(this.mMediaButtonReceiverIntent, this.mMediaButtonReceiverComponentName);
                    this.mIsMbrRegistered = false;
                }
                if (!this.mIsRccRegistered && (this.mFlags & 2) != 0) {
                    this.mAudioManager.registerRemoteControlClient(this.mRcc);
                    this.mIsRccRegistered = true;
                    registeredRcc = true;
                } else if (this.mIsRccRegistered && (this.mFlags & 2) == 0) {
                    this.mRcc.setPlaybackState(0);
                    this.mAudioManager.unregisterRemoteControlClient(this.mRcc);
                    this.mIsRccRegistered = false;
                }
            } else {
                if (this.mIsMbrRegistered) {
                    unregisterMediaButtonEventReceiver(this.mMediaButtonReceiverIntent, this.mMediaButtonReceiverComponentName);
                    this.mIsMbrRegistered = false;
                }
                if (this.mIsRccRegistered) {
                    this.mRcc.setPlaybackState(0);
                    this.mAudioManager.unregisterRemoteControlClient(this.mRcc);
                    this.mIsRccRegistered = false;
                }
            }
            return registeredRcc;
        }

        /* access modifiers changed from: package-private */
        public void registerMediaButtonEventReceiver(PendingIntent pendingIntent, ComponentName mbrComponent) {
            PendingIntent pendingIntent2 = pendingIntent;
            this.mAudioManager.registerMediaButtonEventReceiver(mbrComponent);
        }

        /* access modifiers changed from: package-private */
        public void unregisterMediaButtonEventReceiver(PendingIntent pendingIntent, ComponentName mbrComponent) {
            PendingIntent pendingIntent2 = pendingIntent;
            this.mAudioManager.unregisterMediaButtonEventReceiver(mbrComponent);
        }

        /* access modifiers changed from: package-private */
        public void adjustVolume(int i, int i2) {
            int direction = i;
            int flags = i2;
            if (this.mVolumeType != 2) {
                this.mAudioManager.adjustStreamVolume(this.mLocalStream, direction, flags);
            } else if (this.mVolumeProvider != null) {
                this.mVolumeProvider.onAdjustVolume(direction);
            }
        }

        /* access modifiers changed from: package-private */
        public void setVolumeTo(int i, int i2) {
            int value = i;
            int flags = i2;
            if (this.mVolumeType != 2) {
                this.mAudioManager.setStreamVolume(this.mLocalStream, value, flags);
            } else if (this.mVolumeProvider != null) {
                this.mVolumeProvider.onSetVolumeTo(value);
            }
        }

        /* access modifiers changed from: package-private */
        public void sendVolumeInfoChanged(ParcelableVolumeInfo parcelableVolumeInfo) {
            ParcelableVolumeInfo info = parcelableVolumeInfo;
            for (int i = this.mControllerCallbacks.beginBroadcast() - 1; i >= 0; i--) {
                try {
                    this.mControllerCallbacks.getBroadcastItem(i).onVolumeInfoChanged(info);
                } catch (RemoteException e) {
                    RemoteException remoteException = e;
                }
            }
            this.mControllerCallbacks.finishBroadcast();
        }

        private void sendSessionDestroyed() {
            for (int i = this.mControllerCallbacks.beginBroadcast() - 1; i >= 0; i--) {
                try {
                    this.mControllerCallbacks.getBroadcastItem(i).onSessionDestroyed();
                } catch (RemoteException e) {
                    RemoteException remoteException = e;
                }
            }
            this.mControllerCallbacks.finishBroadcast();
            this.mControllerCallbacks.kill();
        }

        private void sendEvent(String str, Bundle bundle) {
            String event = str;
            Bundle extras = bundle;
            for (int i = this.mControllerCallbacks.beginBroadcast() - 1; i >= 0; i--) {
                try {
                    this.mControllerCallbacks.getBroadcastItem(i).onEvent(event, extras);
                } catch (RemoteException e) {
                    RemoteException remoteException = e;
                }
            }
            this.mControllerCallbacks.finishBroadcast();
        }

        private void sendState(PlaybackStateCompat playbackStateCompat) {
            PlaybackStateCompat state = playbackStateCompat;
            for (int i = this.mControllerCallbacks.beginBroadcast() - 1; i >= 0; i--) {
                try {
                    this.mControllerCallbacks.getBroadcastItem(i).onPlaybackStateChanged(state);
                } catch (RemoteException e) {
                    RemoteException remoteException = e;
                }
            }
            this.mControllerCallbacks.finishBroadcast();
        }

        private void sendMetadata(MediaMetadataCompat mediaMetadataCompat) {
            MediaMetadataCompat metadata = mediaMetadataCompat;
            for (int i = this.mControllerCallbacks.beginBroadcast() - 1; i >= 0; i--) {
                try {
                    this.mControllerCallbacks.getBroadcastItem(i).onMetadataChanged(metadata);
                } catch (RemoteException e) {
                    RemoteException remoteException = e;
                }
            }
            this.mControllerCallbacks.finishBroadcast();
        }

        private void sendQueue(List<QueueItem> list) {
            List<QueueItem> queue = list;
            for (int i = this.mControllerCallbacks.beginBroadcast() - 1; i >= 0; i--) {
                try {
                    this.mControllerCallbacks.getBroadcastItem(i).onQueueChanged(queue);
                } catch (RemoteException e) {
                    RemoteException remoteException = e;
                }
            }
            this.mControllerCallbacks.finishBroadcast();
        }

        private void sendQueueTitle(CharSequence charSequence) {
            CharSequence queueTitle = charSequence;
            for (int i = this.mControllerCallbacks.beginBroadcast() - 1; i >= 0; i--) {
                try {
                    this.mControllerCallbacks.getBroadcastItem(i).onQueueTitleChanged(queueTitle);
                } catch (RemoteException e) {
                    RemoteException remoteException = e;
                }
            }
            this.mControllerCallbacks.finishBroadcast();
        }

        private void sendCaptioningEnabled(boolean z) {
            boolean enabled = z;
            for (int i = this.mControllerCallbacks.beginBroadcast() - 1; i >= 0; i--) {
                try {
                    this.mControllerCallbacks.getBroadcastItem(i).onCaptioningEnabledChanged(enabled);
                } catch (RemoteException e) {
                    RemoteException remoteException = e;
                }
            }
            this.mControllerCallbacks.finishBroadcast();
        }

        private void sendRepeatMode(int i) {
            int repeatMode = i;
            for (int i2 = this.mControllerCallbacks.beginBroadcast() - 1; i2 >= 0; i2--) {
                try {
                    this.mControllerCallbacks.getBroadcastItem(i2).onRepeatModeChanged(repeatMode);
                } catch (RemoteException e) {
                    RemoteException remoteException = e;
                }
            }
            this.mControllerCallbacks.finishBroadcast();
        }

        private void sendShuffleMode(int i) {
            int shuffleMode = i;
            for (int i2 = this.mControllerCallbacks.beginBroadcast() - 1; i2 >= 0; i2--) {
                try {
                    this.mControllerCallbacks.getBroadcastItem(i2).onShuffleModeChanged(shuffleMode);
                } catch (RemoteException e) {
                    RemoteException remoteException = e;
                }
            }
            this.mControllerCallbacks.finishBroadcast();
        }

        private void sendExtras(Bundle bundle) {
            Bundle extras = bundle;
            for (int i = this.mControllerCallbacks.beginBroadcast() - 1; i >= 0; i--) {
                try {
                    this.mControllerCallbacks.getBroadcastItem(i).onExtrasChanged(extras);
                } catch (RemoteException e) {
                    RemoteException remoteException = e;
                }
            }
            this.mControllerCallbacks.finishBroadcast();
        }

        /* renamed from: android.support.v4.media.session.MediaSessionCompat$MediaSessionImplBase$MediaSessionStub */
        class MediaSessionStub extends IMediaSession.Stub {
            final /* synthetic */ MediaSessionImplBase this$0;

            MediaSessionStub(MediaSessionImplBase this$02) {
                this.this$0 = this$02;
            }

            public void sendCommand(String command, Bundle args, ResultReceiverWrapper cb) {
                Object obj;
                new Command(command, args, cb.mResultReceiver);
                postToHandler(1, obj);
            }

            public boolean sendMediaButton(KeyEvent keyEvent) {
                KeyEvent mediaButton = keyEvent;
                boolean handlesMediaButtons = (this.this$0.mFlags & 1) != 0;
                if (handlesMediaButtons) {
                    postToHandler(21, (Object) mediaButton);
                }
                return handlesMediaButtons;
            }

            public void registerCallbackListener(IMediaControllerCallback iMediaControllerCallback) {
                Object obj;
                IMediaControllerCallback cb = iMediaControllerCallback;
                if (this.this$0.mDestroyed) {
                    try {
                        cb.onSessionDestroyed();
                    } catch (Exception e) {
                        Exception exc = e;
                    }
                } else {
                    new MediaSessionManager.RemoteUserInfo(MediaSessionManager.RemoteUserInfo.LEGACY_CONTROLLER, getCallingPid(), getCallingUid());
                    boolean register = this.this$0.mControllerCallbacks.register(cb, obj);
                }
            }

            public void unregisterCallbackListener(IMediaControllerCallback cb) {
                boolean unregister = this.this$0.mControllerCallbacks.unregister(cb);
            }

            public String getPackageName() {
                return this.this$0.mPackageName;
            }

            public String getTag() {
                return this.this$0.mTag;
            }

            public PendingIntent getLaunchPendingIntent() {
                Object obj = this.this$0.mLock;
                Object obj2 = obj;
                synchronized (obj) {
                    try {
                        PendingIntent pendingIntent = this.this$0.mSessionActivity;
                        return pendingIntent;
                    } catch (Throwable th) {
                        Throwable th2 = th;
                        Object obj3 = obj2;
                        throw th2;
                    }
                }
            }

            public long getFlags() {
                Object obj = this.this$0.mLock;
                Object obj2 = obj;
                synchronized (obj) {
                    try {
                        long j = (long) this.this$0.mFlags;
                        return j;
                    } catch (Throwable th) {
                        Throwable th2 = th;
                        Object obj3 = obj2;
                        throw th2;
                    }
                }
            }

            /* JADX INFO: finally extract failed */
            public ParcelableVolumeInfo getVolumeAttributes() {
                int controlType;
                int max;
                int current;
                ParcelableVolumeInfo parcelableVolumeInfo;
                Object obj = this.this$0.mLock;
                Object obj2 = obj;
                synchronized (obj) {
                    try {
                        int volumeType = this.this$0.mVolumeType;
                        int stream = this.this$0.mLocalStream;
                        VolumeProviderCompat vp = this.this$0.mVolumeProvider;
                        if (volumeType == 2) {
                            controlType = vp.getVolumeControl();
                            max = vp.getMaxVolume();
                            current = vp.getCurrentVolume();
                        } else {
                            controlType = 2;
                            max = this.this$0.mAudioManager.getStreamMaxVolume(stream);
                            current = this.this$0.mAudioManager.getStreamVolume(stream);
                        }
                        new ParcelableVolumeInfo(volumeType, stream, controlType, max, current);
                        return parcelableVolumeInfo;
                    } catch (Throwable th) {
                        Throwable th2 = th;
                        Object obj3 = obj2;
                        throw th2;
                    }
                }
            }

            public void adjustVolume(int direction, int flags, String str) {
                String str2 = str;
                this.this$0.adjustVolume(direction, flags);
            }

            public void setVolumeTo(int value, int flags, String str) {
                String str2 = str;
                this.this$0.setVolumeTo(value, flags);
            }

            public void prepare() throws RemoteException {
                postToHandler(3);
            }

            public void prepareFromMediaId(String mediaId, Bundle extras) throws RemoteException {
                postToHandler(4, (Object) mediaId, extras);
            }

            public void prepareFromSearch(String query, Bundle extras) throws RemoteException {
                postToHandler(5, (Object) query, extras);
            }

            public void prepareFromUri(Uri uri, Bundle extras) throws RemoteException {
                postToHandler(6, (Object) uri, extras);
            }

            public void play() throws RemoteException {
                postToHandler(7);
            }

            public void playFromMediaId(String mediaId, Bundle extras) throws RemoteException {
                postToHandler(8, (Object) mediaId, extras);
            }

            public void playFromSearch(String query, Bundle extras) throws RemoteException {
                postToHandler(9, (Object) query, extras);
            }

            public void playFromUri(Uri uri, Bundle extras) throws RemoteException {
                postToHandler(10, (Object) uri, extras);
            }

            public void skipToQueueItem(long id) {
                postToHandler(11, (Object) Long.valueOf(id));
            }

            public void pause() throws RemoteException {
                postToHandler(12);
            }

            public void stop() throws RemoteException {
                postToHandler(13);
            }

            public void next() throws RemoteException {
                postToHandler(14);
            }

            public void previous() throws RemoteException {
                postToHandler(15);
            }

            public void fastForward() throws RemoteException {
                postToHandler(16);
            }

            public void rewind() throws RemoteException {
                postToHandler(17);
            }

            public void seekTo(long pos) throws RemoteException {
                postToHandler(18, (Object) Long.valueOf(pos));
            }

            public void rate(RatingCompat rating) throws RemoteException {
                postToHandler(19, (Object) rating);
            }

            public void rateWithExtras(RatingCompat rating, Bundle extras) throws RemoteException {
                postToHandler(31, (Object) rating, extras);
            }

            public void setCaptioningEnabled(boolean enabled) throws RemoteException {
                postToHandler(29, (Object) Boolean.valueOf(enabled));
            }

            public void setRepeatMode(int repeatMode) throws RemoteException {
                postToHandler(23, repeatMode);
            }

            public void setShuffleModeEnabledRemoved(boolean enabled) throws RemoteException {
            }

            public void setShuffleMode(int shuffleMode) throws RemoteException {
                postToHandler(30, shuffleMode);
            }

            public void sendCustomAction(String action, Bundle args) throws RemoteException {
                postToHandler(20, (Object) action, args);
            }

            public MediaMetadataCompat getMetadata() {
                return this.this$0.mMetadata;
            }

            /* JADX INFO: finally extract failed */
            public PlaybackStateCompat getPlaybackState() {
                Object obj = this.this$0.mLock;
                Object obj2 = obj;
                synchronized (obj) {
                    try {
                        PlaybackStateCompat state = this.this$0.mState;
                        MediaMetadataCompat metadata = this.this$0.mMetadata;
                        return MediaSessionCompat.getStateWithUpdatedPosition(state, metadata);
                    } catch (Throwable th) {
                        while (true) {
                            Throwable th2 = th;
                            Object obj3 = obj2;
                            throw th2;
                        }
                    }
                }
            }

            public List<QueueItem> getQueue() {
                Object obj = this.this$0.mLock;
                Object obj2 = obj;
                synchronized (obj) {
                    try {
                        List<QueueItem> list = this.this$0.mQueue;
                        return list;
                    } catch (Throwable th) {
                        Throwable th2 = th;
                        Object obj3 = obj2;
                        throw th2;
                    }
                }
            }

            public void addQueueItem(MediaDescriptionCompat description) {
                postToHandler(25, (Object) description);
            }

            public void addQueueItemAt(MediaDescriptionCompat description, int index) {
                postToHandler(26, (Object) description, index);
            }

            public void removeQueueItem(MediaDescriptionCompat description) {
                postToHandler(27, (Object) description);
            }

            public void removeQueueItemAt(int index) {
                postToHandler(28, index);
            }

            public CharSequence getQueueTitle() {
                return this.this$0.mQueueTitle;
            }

            public Bundle getExtras() {
                Object obj = this.this$0.mLock;
                Object obj2 = obj;
                synchronized (obj) {
                    try {
                        Bundle bundle = this.this$0.mExtras;
                        return bundle;
                    } catch (Throwable th) {
                        Throwable th2 = th;
                        Object obj3 = obj2;
                        throw th2;
                    }
                }
            }

            public int getRatingType() {
                return this.this$0.mRatingType;
            }

            public boolean isCaptioningEnabled() {
                return this.this$0.mCaptioningEnabled;
            }

            public int getRepeatMode() {
                return this.this$0.mRepeatMode;
            }

            public boolean isShuffleModeEnabledRemoved() {
                return false;
            }

            public int getShuffleMode() {
                return this.this$0.mShuffleMode;
            }

            public boolean isTransportControlEnabled() {
                return (this.this$0.mFlags & 2) != 0;
            }

            /* access modifiers changed from: package-private */
            public void postToHandler(int what) {
                this.this$0.postToHandler(what, 0, 0, (Object) null, (Bundle) null);
            }

            /* access modifiers changed from: package-private */
            public void postToHandler(int what, int arg1) {
                this.this$0.postToHandler(what, arg1, 0, (Object) null, (Bundle) null);
            }

            /* access modifiers changed from: package-private */
            public void postToHandler(int what, Object obj) {
                this.this$0.postToHandler(what, 0, 0, obj, (Bundle) null);
            }

            /* access modifiers changed from: package-private */
            public void postToHandler(int what, Object obj, int arg1) {
                this.this$0.postToHandler(what, arg1, 0, obj, (Bundle) null);
            }

            /* access modifiers changed from: package-private */
            public void postToHandler(int what, Object obj, Bundle extras) {
                this.this$0.postToHandler(what, 0, 0, obj, extras);
            }
        }

        /* renamed from: android.support.v4.media.session.MediaSessionCompat$MediaSessionImplBase$Command */
        private static final class Command {
            public final String command;
            public final Bundle extras;
            public final ResultReceiver stub;

            public Command(String command2, Bundle extras2, ResultReceiver stub2) {
                this.command = command2;
                this.extras = extras2;
                this.stub = stub2;
            }
        }

        /* renamed from: android.support.v4.media.session.MediaSessionCompat$MediaSessionImplBase$MessageHandler */
        class MessageHandler extends Handler {
            private static final int KEYCODE_MEDIA_PAUSE = 127;
            private static final int KEYCODE_MEDIA_PLAY = 126;
            private static final int MSG_ADD_QUEUE_ITEM = 25;
            private static final int MSG_ADD_QUEUE_ITEM_AT = 26;
            private static final int MSG_ADJUST_VOLUME = 2;
            private static final int MSG_COMMAND = 1;
            private static final int MSG_CUSTOM_ACTION = 20;
            private static final int MSG_FAST_FORWARD = 16;
            private static final int MSG_MEDIA_BUTTON = 21;
            private static final int MSG_NEXT = 14;
            private static final int MSG_PAUSE = 12;
            private static final int MSG_PLAY = 7;
            private static final int MSG_PLAY_MEDIA_ID = 8;
            private static final int MSG_PLAY_SEARCH = 9;
            private static final int MSG_PLAY_URI = 10;
            private static final int MSG_PREPARE = 3;
            private static final int MSG_PREPARE_MEDIA_ID = 4;
            private static final int MSG_PREPARE_SEARCH = 5;
            private static final int MSG_PREPARE_URI = 6;
            private static final int MSG_PREVIOUS = 15;
            private static final int MSG_RATE = 19;
            private static final int MSG_RATE_EXTRA = 31;
            private static final int MSG_REMOVE_QUEUE_ITEM = 27;
            private static final int MSG_REMOVE_QUEUE_ITEM_AT = 28;
            private static final int MSG_REWIND = 17;
            private static final int MSG_SEEK_TO = 18;
            private static final int MSG_SET_CAPTIONING_ENABLED = 29;
            private static final int MSG_SET_REPEAT_MODE = 23;
            private static final int MSG_SET_SHUFFLE_MODE = 30;
            private static final int MSG_SET_VOLUME = 22;
            private static final int MSG_SKIP_TO_ITEM = 11;
            private static final int MSG_STOP = 13;
            final /* synthetic */ MediaSessionImplBase this$0;

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public MessageHandler(MediaSessionImplBase this$02, Looper looper) {
                super(looper);
                this.this$0 = this$02;
            }

            public void handleMessage(Message message) {
                MediaSessionManager.RemoteUserInfo remoteUserInfo;
                Intent intent;
                Message msg = message;
                Callback cb = this.this$0.mCallback;
                if (cb != null) {
                    Bundle data = msg.getData();
                    MediaSessionCompat.ensureClassLoader(data);
                    new MediaSessionManager.RemoteUserInfo(data.getString(MediaSessionCompat.DATA_CALLING_PACKAGE), data.getInt("data_calling_pid"), data.getInt("data_calling_uid"));
                    this.this$0.setCurrentControllerInfo(remoteUserInfo);
                    Bundle extras = data.getBundle(MediaSessionCompat.DATA_EXTRAS);
                    MediaSessionCompat.ensureClassLoader(extras);
                    try {
                        switch (msg.what) {
                            case 1:
                                Command cmd = (Command) msg.obj;
                                cb.onCommand(cmd.command, cmd.extras, cmd.stub);
                                break;
                            case 2:
                                this.this$0.adjustVolume(msg.arg1, 0);
                                break;
                            case 3:
                                cb.onPrepare();
                                break;
                            case 4:
                                cb.onPrepareFromMediaId((String) msg.obj, extras);
                                break;
                            case 5:
                                cb.onPrepareFromSearch((String) msg.obj, extras);
                                break;
                            case 6:
                                cb.onPrepareFromUri((Uri) msg.obj, extras);
                                break;
                            case 7:
                                cb.onPlay();
                                break;
                            case 8:
                                cb.onPlayFromMediaId((String) msg.obj, extras);
                                break;
                            case 9:
                                cb.onPlayFromSearch((String) msg.obj, extras);
                                break;
                            case 10:
                                cb.onPlayFromUri((Uri) msg.obj, extras);
                                break;
                            case 11:
                                cb.onSkipToQueueItem(((Long) msg.obj).longValue());
                                break;
                            case 12:
                                cb.onPause();
                                break;
                            case 13:
                                cb.onStop();
                                break;
                            case 14:
                                cb.onSkipToNext();
                                break;
                            case 15:
                                cb.onSkipToPrevious();
                                break;
                            case 16:
                                cb.onFastForward();
                                break;
                            case 17:
                                cb.onRewind();
                                break;
                            case 18:
                                cb.onSeekTo(((Long) msg.obj).longValue());
                                break;
                            case 19:
                                cb.onSetRating((RatingCompat) msg.obj);
                                break;
                            case 20:
                                cb.onCustomAction((String) msg.obj, extras);
                                break;
                            case 21:
                                KeyEvent keyEvent = (KeyEvent) msg.obj;
                                new Intent("android.intent.action.MEDIA_BUTTON");
                                Intent intent2 = intent;
                                Intent putExtra = intent2.putExtra("android.intent.extra.KEY_EVENT", keyEvent);
                                if (!cb.onMediaButtonEvent(intent2)) {
                                    onMediaButtonEvent(keyEvent, cb);
                                    break;
                                }
                                break;
                            case 22:
                                this.this$0.setVolumeTo(msg.arg1, 0);
                                break;
                            case 23:
                                cb.onSetRepeatMode(msg.arg1);
                                break;
                            case 25:
                                cb.onAddQueueItem((MediaDescriptionCompat) msg.obj);
                                break;
                            case 26:
                                cb.onAddQueueItem((MediaDescriptionCompat) msg.obj, msg.arg1);
                                break;
                            case 27:
                                cb.onRemoveQueueItem((MediaDescriptionCompat) msg.obj);
                                break;
                            case 28:
                                if (this.this$0.mQueue != null) {
                                    QueueItem item = (msg.arg1 < 0 || msg.arg1 >= this.this$0.mQueue.size()) ? null : this.this$0.mQueue.get(msg.arg1);
                                    if (item != null) {
                                        cb.onRemoveQueueItem(item.getDescription());
                                    }
                                    break;
                                }
                                break;
                            case 29:
                                cb.onSetCaptioningEnabled(((Boolean) msg.obj).booleanValue());
                                break;
                            case 30:
                                cb.onSetShuffleMode(msg.arg1);
                                break;
                            case 31:
                                cb.onSetRating((RatingCompat) msg.obj, extras);
                                break;
                        }
                        this.this$0.setCurrentControllerInfo((MediaSessionManager.RemoteUserInfo) null);
                    } catch (Throwable th) {
                        Throwable th2 = th;
                        this.this$0.setCurrentControllerInfo((MediaSessionManager.RemoteUserInfo) null);
                        throw th2;
                    }
                }
            }

            private void onMediaButtonEvent(KeyEvent keyEvent, Callback callback) {
                KeyEvent ke = keyEvent;
                Callback cb = callback;
                if (ke != null && ke.getAction() == 0) {
                    long validActions = this.this$0.mState == null ? 0 : this.this$0.mState.getActions();
                    switch (ke.getKeyCode()) {
                        case 79:
                        case 85:
                            int w = Log.w(MediaSessionCompat.TAG, "KEYCODE_MEDIA_PLAY_PAUSE and KEYCODE_HEADSETHOOK are handled already");
                            return;
                        case 86:
                            if ((validActions & 1) != 0) {
                                cb.onStop();
                                return;
                            }
                            return;
                        case 87:
                            if ((validActions & 32) != 0) {
                                cb.onSkipToNext();
                                return;
                            }
                            return;
                        case 88:
                            if ((validActions & 16) != 0) {
                                cb.onSkipToPrevious();
                                return;
                            }
                            return;
                        case 89:
                            if ((validActions & 8) != 0) {
                                cb.onRewind();
                                return;
                            }
                            return;
                        case 90:
                            if ((validActions & 64) != 0) {
                                cb.onFastForward();
                                return;
                            }
                            return;
                        case KEYCODE_MEDIA_PLAY /*126*/:
                            if ((validActions & 4) != 0) {
                                cb.onPlay();
                                return;
                            }
                            return;
                        case KEYCODE_MEDIA_PAUSE /*127*/:
                            if ((validActions & 2) != 0) {
                                cb.onPause();
                                return;
                            }
                            return;
                        default:
                            return;
                    }
                }
            }
        }
    }

    @RequiresApi(18)
    /* renamed from: android.support.v4.media.session.MediaSessionCompat$MediaSessionImplApi18 */
    static class MediaSessionImplApi18 extends MediaSessionImplBase {
        private static boolean sIsMbrPendingIntentSupported = true;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        MediaSessionImplApi18(Context context, String tag, ComponentName mbrComponent, PendingIntent mbrIntent) {
            super(context, tag, mbrComponent, mbrIntent);
        }

        public void setCallback(Callback callback, Handler handler) {
            RemoteControlClient.OnPlaybackPositionUpdateListener listener;
            Callback callback2 = callback;
            super.setCallback(callback2, handler);
            if (callback2 == null) {
                this.mRcc.setPlaybackPositionUpdateListener((RemoteControlClient.OnPlaybackPositionUpdateListener) null);
                return;
            }
            new RemoteControlClient.OnPlaybackPositionUpdateListener(this) {
                final /* synthetic */ MediaSessionImplApi18 this$0;

                {
                    this.this$0 = this$0;
                }

                public void onPlaybackPositionUpdate(long newPositionMs) {
                    this.this$0.postToHandler(18, -1, -1, Long.valueOf(newPositionMs), (Bundle) null);
                }
            };
            this.mRcc.setPlaybackPositionUpdateListener(listener);
        }

        /* access modifiers changed from: package-private */
        public void setRccState(PlaybackStateCompat playbackStateCompat) {
            PlaybackStateCompat state = playbackStateCompat;
            long position = state.getPosition();
            float speed = state.getPlaybackSpeed();
            long updateTime = state.getLastPositionUpdateTime();
            long currTime = SystemClock.elapsedRealtime();
            if (state.getState() == 3 && position > 0) {
                long diff = 0;
                if (updateTime > 0) {
                    diff = currTime - updateTime;
                    if (speed > 0.0f && speed != 1.0f) {
                        diff = (long) (((float) diff) * speed);
                    }
                }
                position += diff;
            }
            this.mRcc.setPlaybackState(getRccStateFromState(state.getState()), position, speed);
        }

        /* access modifiers changed from: package-private */
        public int getRccTransportControlFlagsFromActions(long j) {
            long actions = j;
            int transportControlFlags = super.getRccTransportControlFlagsFromActions(actions);
            if ((actions & 256) != 0) {
                transportControlFlags |= 256;
            }
            return transportControlFlags;
        }

        /* access modifiers changed from: package-private */
        public void registerMediaButtonEventReceiver(PendingIntent pendingIntent, ComponentName componentName) {
            PendingIntent mbrIntent = pendingIntent;
            ComponentName mbrComponent = componentName;
            if (sIsMbrPendingIntentSupported) {
                try {
                    this.mAudioManager.registerMediaButtonEventReceiver(mbrIntent);
                } catch (NullPointerException e) {
                    NullPointerException nullPointerException = e;
                    int w = Log.w(MediaSessionCompat.TAG, "Unable to register media button event receiver with PendingIntent, falling back to ComponentName.");
                    sIsMbrPendingIntentSupported = false;
                }
            }
            if (!sIsMbrPendingIntentSupported) {
                super.registerMediaButtonEventReceiver(mbrIntent, mbrComponent);
            }
        }

        /* access modifiers changed from: package-private */
        public void unregisterMediaButtonEventReceiver(PendingIntent pendingIntent, ComponentName componentName) {
            PendingIntent mbrIntent = pendingIntent;
            ComponentName mbrComponent = componentName;
            if (sIsMbrPendingIntentSupported) {
                this.mAudioManager.unregisterMediaButtonEventReceiver(mbrIntent);
            } else {
                super.unregisterMediaButtonEventReceiver(mbrIntent, mbrComponent);
            }
        }
    }

    @RequiresApi(19)
    /* renamed from: android.support.v4.media.session.MediaSessionCompat$MediaSessionImplApi19 */
    static class MediaSessionImplApi19 extends MediaSessionImplApi18 {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        MediaSessionImplApi19(Context context, String tag, ComponentName mbrComponent, PendingIntent mbrIntent) {
            super(context, tag, mbrComponent, mbrIntent);
        }

        public void setCallback(Callback callback, Handler handler) {
            RemoteControlClient.OnMetadataUpdateListener listener;
            Callback callback2 = callback;
            super.setCallback(callback2, handler);
            if (callback2 == null) {
                this.mRcc.setMetadataUpdateListener((RemoteControlClient.OnMetadataUpdateListener) null);
                return;
            }
            new RemoteControlClient.OnMetadataUpdateListener(this) {
                final /* synthetic */ MediaSessionImplApi19 this$0;

                {
                    this.this$0 = this$0;
                }

                public void onMetadataUpdate(int key, Object obj) {
                    Object newValue = obj;
                    if (key == 268435457 && (newValue instanceof Rating)) {
                        this.this$0.postToHandler(19, -1, -1, RatingCompat.fromRating(newValue), (Bundle) null);
                    }
                }
            };
            this.mRcc.setMetadataUpdateListener(listener);
        }

        /* access modifiers changed from: package-private */
        public int getRccTransportControlFlagsFromActions(long j) {
            long actions = j;
            int transportControlFlags = super.getRccTransportControlFlagsFromActions(actions);
            if ((actions & 128) != 0) {
                transportControlFlags |= 512;
            }
            return transportControlFlags;
        }

        /* access modifiers changed from: package-private */
        public RemoteControlClient.MetadataEditor buildRccMetadata(Bundle bundle) {
            Bundle metadata = bundle;
            RemoteControlClient.MetadataEditor editor = super.buildRccMetadata(metadata);
            if (((this.mState == null ? 0 : this.mState.getActions()) & 128) != 0) {
                editor.addEditableKey(268435457);
            }
            if (metadata == null) {
                return editor;
            }
            if (metadata.containsKey(MediaMetadataCompat.METADATA_KEY_YEAR)) {
                RemoteControlClient.MetadataEditor putLong = editor.putLong(8, metadata.getLong(MediaMetadataCompat.METADATA_KEY_YEAR));
            }
            if (metadata.containsKey(MediaMetadataCompat.METADATA_KEY_RATING)) {
                MediaMetadataEditor putObject = editor.putObject(101, metadata.getParcelable(MediaMetadataCompat.METADATA_KEY_RATING));
            }
            if (metadata.containsKey(MediaMetadataCompat.METADATA_KEY_USER_RATING)) {
                MediaMetadataEditor putObject2 = editor.putObject(268435457, metadata.getParcelable(MediaMetadataCompat.METADATA_KEY_USER_RATING));
            }
            return editor;
        }
    }

    @RequiresApi(21)
    /* renamed from: android.support.v4.media.session.MediaSessionCompat$MediaSessionImplApi21 */
    static class MediaSessionImplApi21 implements MediaSessionImpl {
        boolean mCaptioningEnabled;
        boolean mDestroyed = false;
        final RemoteCallbackList<IMediaControllerCallback> mExtraControllerCallbacks;
        MediaMetadataCompat mMetadata;
        PlaybackStateCompat mPlaybackState;
        List<QueueItem> mQueue;
        int mRatingType;
        int mRepeatMode;
        final Object mSessionObj;
        int mShuffleMode;
        final Token mToken;

        MediaSessionImplApi21(Context context, String tag, Bundle token2Bundle) {
            RemoteCallbackList<IMediaControllerCallback> remoteCallbackList;
            Token token;
            IMediaSession iMediaSession;
            new RemoteCallbackList<>();
            this.mExtraControllerCallbacks = remoteCallbackList;
            this.mSessionObj = MediaSessionCompatApi21.createSession(context, tag);
            new ExtraSession(this);
            new Token(MediaSessionCompatApi21.getSessionToken(this.mSessionObj), iMediaSession, token2Bundle);
            this.mToken = token;
        }

        MediaSessionImplApi21(Object mediaSession) {
            RemoteCallbackList<IMediaControllerCallback> remoteCallbackList;
            Token token;
            IMediaSession iMediaSession;
            new RemoteCallbackList<>();
            this.mExtraControllerCallbacks = remoteCallbackList;
            this.mSessionObj = MediaSessionCompatApi21.verifySession(mediaSession);
            new ExtraSession(this);
            new Token(MediaSessionCompatApi21.getSessionToken(this.mSessionObj), iMediaSession);
            this.mToken = token;
        }

        public void setCallback(Callback callback, Handler handler) {
            Callback callback2 = callback;
            Handler handler2 = handler;
            MediaSessionCompatApi21.setCallback(this.mSessionObj, callback2 == null ? null : callback2.mCallbackObj, handler2);
            if (callback2 != null) {
                callback2.setSessionImpl(this, handler2);
            }
        }

        public void setFlags(int flags) {
            MediaSessionCompatApi21.setFlags(this.mSessionObj, flags);
        }

        public void setPlaybackToLocal(int stream) {
            MediaSessionCompatApi21.setPlaybackToLocal(this.mSessionObj, stream);
        }

        public void setPlaybackToRemote(VolumeProviderCompat volumeProvider) {
            MediaSessionCompatApi21.setPlaybackToRemote(this.mSessionObj, volumeProvider.getVolumeProvider());
        }

        public void setActive(boolean active) {
            MediaSessionCompatApi21.setActive(this.mSessionObj, active);
        }

        public boolean isActive() {
            return MediaSessionCompatApi21.isActive(this.mSessionObj);
        }

        public void sendSessionEvent(String str, Bundle bundle) {
            String event = str;
            Bundle extras = bundle;
            if (Build.VERSION.SDK_INT < 23) {
                for (int i = this.mExtraControllerCallbacks.beginBroadcast() - 1; i >= 0; i--) {
                    try {
                        this.mExtraControllerCallbacks.getBroadcastItem(i).onEvent(event, extras);
                    } catch (RemoteException e) {
                        RemoteException remoteException = e;
                    }
                }
                this.mExtraControllerCallbacks.finishBroadcast();
            }
            MediaSessionCompatApi21.sendSessionEvent(this.mSessionObj, event, extras);
        }

        public void release() {
            this.mDestroyed = true;
            MediaSessionCompatApi21.release(this.mSessionObj);
        }

        public Token getSessionToken() {
            return this.mToken;
        }

        public void setPlaybackState(PlaybackStateCompat playbackStateCompat) {
            Object playbackState;
            PlaybackStateCompat state = playbackStateCompat;
            this.mPlaybackState = state;
            for (int i = this.mExtraControllerCallbacks.beginBroadcast() - 1; i >= 0; i--) {
                try {
                    this.mExtraControllerCallbacks.getBroadcastItem(i).onPlaybackStateChanged(state);
                } catch (RemoteException e) {
                    RemoteException remoteException = e;
                }
            }
            this.mExtraControllerCallbacks.finishBroadcast();
            Object obj = this.mSessionObj;
            if (state == null) {
                playbackState = null;
            } else {
                playbackState = state.getPlaybackState();
            }
            MediaSessionCompatApi21.setPlaybackState(obj, playbackState);
        }

        public PlaybackStateCompat getPlaybackState() {
            return this.mPlaybackState;
        }

        public void setMetadata(MediaMetadataCompat mediaMetadataCompat) {
            Object mediaMetadata;
            MediaMetadataCompat metadata = mediaMetadataCompat;
            this.mMetadata = metadata;
            Object obj = this.mSessionObj;
            if (metadata == null) {
                mediaMetadata = null;
            } else {
                mediaMetadata = metadata.getMediaMetadata();
            }
            MediaSessionCompatApi21.setMetadata(obj, mediaMetadata);
        }

        public void setSessionActivity(PendingIntent pi) {
            MediaSessionCompatApi21.setSessionActivity(this.mSessionObj, pi);
        }

        public void setMediaButtonReceiver(PendingIntent mbr) {
            MediaSessionCompatApi21.setMediaButtonReceiver(this.mSessionObj, mbr);
        }

        public void setQueue(List<QueueItem> list) {
            List<Object> list2;
            List<QueueItem> queue = list;
            this.mQueue = queue;
            List<Object> queueObjs = null;
            if (queue != null) {
                new ArrayList<>();
                queueObjs = list2;
                for (QueueItem item : queue) {
                    boolean add = queueObjs.add(item.getQueueItem());
                }
            }
            MediaSessionCompatApi21.setQueue(this.mSessionObj, queueObjs);
        }

        public void setQueueTitle(CharSequence title) {
            MediaSessionCompatApi21.setQueueTitle(this.mSessionObj, title);
        }

        public void setRatingType(int i) {
            int type = i;
            if (Build.VERSION.SDK_INT < 22) {
                this.mRatingType = type;
                return;
            }
            MediaSessionCompatApi22.setRatingType(this.mSessionObj, type);
        }

        public void setCaptioningEnabled(boolean z) {
            boolean enabled = z;
            if (this.mCaptioningEnabled != enabled) {
                this.mCaptioningEnabled = enabled;
                for (int i = this.mExtraControllerCallbacks.beginBroadcast() - 1; i >= 0; i--) {
                    try {
                        this.mExtraControllerCallbacks.getBroadcastItem(i).onCaptioningEnabledChanged(enabled);
                    } catch (RemoteException e) {
                        RemoteException remoteException = e;
                    }
                }
                this.mExtraControllerCallbacks.finishBroadcast();
            }
        }

        public void setRepeatMode(int i) {
            int repeatMode = i;
            if (this.mRepeatMode != repeatMode) {
                this.mRepeatMode = repeatMode;
                for (int i2 = this.mExtraControllerCallbacks.beginBroadcast() - 1; i2 >= 0; i2--) {
                    try {
                        this.mExtraControllerCallbacks.getBroadcastItem(i2).onRepeatModeChanged(repeatMode);
                    } catch (RemoteException e) {
                        RemoteException remoteException = e;
                    }
                }
                this.mExtraControllerCallbacks.finishBroadcast();
            }
        }

        public void setShuffleMode(int i) {
            int shuffleMode = i;
            if (this.mShuffleMode != shuffleMode) {
                this.mShuffleMode = shuffleMode;
                for (int i2 = this.mExtraControllerCallbacks.beginBroadcast() - 1; i2 >= 0; i2--) {
                    try {
                        this.mExtraControllerCallbacks.getBroadcastItem(i2).onShuffleModeChanged(shuffleMode);
                    } catch (RemoteException e) {
                        RemoteException remoteException = e;
                    }
                }
                this.mExtraControllerCallbacks.finishBroadcast();
            }
        }

        public void setExtras(Bundle extras) {
            MediaSessionCompatApi21.setExtras(this.mSessionObj, extras);
        }

        public Object getMediaSession() {
            return this.mSessionObj;
        }

        public Object getRemoteControlClient() {
            return null;
        }

        public void setCurrentControllerInfo(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
        }

        public String getCallingPackage() {
            if (Build.VERSION.SDK_INT < 24) {
                return null;
            }
            return MediaSessionCompatApi24.getCallingPackage(this.mSessionObj);
        }

        public MediaSessionManager.RemoteUserInfo getCurrentControllerInfo() {
            return null;
        }

        /* renamed from: android.support.v4.media.session.MediaSessionCompat$MediaSessionImplApi21$ExtraSession */
        class ExtraSession extends IMediaSession.Stub {
            final /* synthetic */ MediaSessionImplApi21 this$0;

            ExtraSession(MediaSessionImplApi21 this$02) {
                this.this$0 = this$02;
            }

            public void sendCommand(String str, Bundle bundle, ResultReceiverWrapper resultReceiverWrapper) {
                Throwable th;
                String str2 = str;
                Bundle bundle2 = bundle;
                ResultReceiverWrapper resultReceiverWrapper2 = resultReceiverWrapper;
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }

            public boolean sendMediaButton(KeyEvent keyEvent) {
                Throwable th;
                KeyEvent keyEvent2 = keyEvent;
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }

            public void registerCallbackListener(IMediaControllerCallback iMediaControllerCallback) {
                Object obj;
                IMediaControllerCallback cb = iMediaControllerCallback;
                if (!this.this$0.mDestroyed) {
                    String packageName = this.this$0.getCallingPackage();
                    if (packageName == null) {
                        packageName = MediaSessionManager.RemoteUserInfo.LEGACY_CONTROLLER;
                    }
                    new MediaSessionManager.RemoteUserInfo(packageName, getCallingPid(), getCallingUid());
                    boolean register = this.this$0.mExtraControllerCallbacks.register(cb, obj);
                }
            }

            public void unregisterCallbackListener(IMediaControllerCallback cb) {
                boolean unregister = this.this$0.mExtraControllerCallbacks.unregister(cb);
            }

            public String getPackageName() {
                Throwable th;
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }

            public String getTag() {
                Throwable th;
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }

            public PendingIntent getLaunchPendingIntent() {
                Throwable th;
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }

            public long getFlags() {
                Throwable th;
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }

            public ParcelableVolumeInfo getVolumeAttributes() {
                Throwable th;
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }

            public void adjustVolume(int i, int i2, String str) {
                Throwable th;
                int i3 = i;
                int i4 = i2;
                String str2 = str;
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }

            public void setVolumeTo(int i, int i2, String str) {
                Throwable th;
                int i3 = i;
                int i4 = i2;
                String str2 = str;
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }

            public void prepare() throws RemoteException {
                Throwable th;
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }

            public void prepareFromMediaId(String str, Bundle bundle) throws RemoteException {
                Throwable th;
                String str2 = str;
                Bundle bundle2 = bundle;
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }

            public void prepareFromSearch(String str, Bundle bundle) throws RemoteException {
                Throwable th;
                String str2 = str;
                Bundle bundle2 = bundle;
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }

            public void prepareFromUri(Uri uri, Bundle bundle) throws RemoteException {
                Throwable th;
                Uri uri2 = uri;
                Bundle bundle2 = bundle;
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }

            public void play() throws RemoteException {
                Throwable th;
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }

            public void playFromMediaId(String str, Bundle bundle) throws RemoteException {
                Throwable th;
                String str2 = str;
                Bundle bundle2 = bundle;
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }

            public void playFromSearch(String str, Bundle bundle) throws RemoteException {
                Throwable th;
                String str2 = str;
                Bundle bundle2 = bundle;
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }

            public void playFromUri(Uri uri, Bundle bundle) throws RemoteException {
                Throwable th;
                Uri uri2 = uri;
                Bundle bundle2 = bundle;
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }

            public void skipToQueueItem(long j) {
                Throwable th;
                long j2 = j;
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }

            public void pause() throws RemoteException {
                Throwable th;
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }

            public void stop() throws RemoteException {
                Throwable th;
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }

            public void next() throws RemoteException {
                Throwable th;
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }

            public void previous() throws RemoteException {
                Throwable th;
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }

            public void fastForward() throws RemoteException {
                Throwable th;
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }

            public void rewind() throws RemoteException {
                Throwable th;
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }

            public void seekTo(long j) throws RemoteException {
                Throwable th;
                long j2 = j;
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }

            public void rate(RatingCompat ratingCompat) throws RemoteException {
                Throwable th;
                RatingCompat ratingCompat2 = ratingCompat;
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }

            public void rateWithExtras(RatingCompat ratingCompat, Bundle bundle) throws RemoteException {
                Throwable th;
                RatingCompat ratingCompat2 = ratingCompat;
                Bundle bundle2 = bundle;
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }

            public void setCaptioningEnabled(boolean z) throws RemoteException {
                Throwable th;
                boolean z2 = z;
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }

            public void setRepeatMode(int i) throws RemoteException {
                Throwable th;
                int i2 = i;
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }

            public void setShuffleModeEnabledRemoved(boolean enabled) throws RemoteException {
            }

            public void setShuffleMode(int i) throws RemoteException {
                Throwable th;
                int i2 = i;
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }

            public void sendCustomAction(String str, Bundle bundle) throws RemoteException {
                Throwable th;
                String str2 = str;
                Bundle bundle2 = bundle;
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }

            public MediaMetadataCompat getMetadata() {
                Throwable th;
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }

            public PlaybackStateCompat getPlaybackState() {
                return MediaSessionCompat.getStateWithUpdatedPosition(this.this$0.mPlaybackState, this.this$0.mMetadata);
            }

            public List<QueueItem> getQueue() {
                return null;
            }

            public void addQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
                Throwable th;
                MediaDescriptionCompat mediaDescriptionCompat2 = mediaDescriptionCompat;
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }

            public void addQueueItemAt(MediaDescriptionCompat mediaDescriptionCompat, int i) {
                Throwable th;
                MediaDescriptionCompat mediaDescriptionCompat2 = mediaDescriptionCompat;
                int i2 = i;
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }

            public void removeQueueItem(MediaDescriptionCompat mediaDescriptionCompat) {
                Throwable th;
                MediaDescriptionCompat mediaDescriptionCompat2 = mediaDescriptionCompat;
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }

            public void removeQueueItemAt(int i) {
                Throwable th;
                int i2 = i;
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }

            public CharSequence getQueueTitle() {
                Throwable th;
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }

            public Bundle getExtras() {
                Throwable th;
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }

            public int getRatingType() {
                return this.this$0.mRatingType;
            }

            public boolean isCaptioningEnabled() {
                return this.this$0.mCaptioningEnabled;
            }

            public int getRepeatMode() {
                return this.this$0.mRepeatMode;
            }

            public boolean isShuffleModeEnabledRemoved() {
                return false;
            }

            public int getShuffleMode() {
                return this.this$0.mShuffleMode;
            }

            public boolean isTransportControlEnabled() {
                Throwable th;
                Throwable th2 = th;
                new AssertionError();
                throw th2;
            }
        }
    }

    @RequiresApi(28)
    /* renamed from: android.support.v4.media.session.MediaSessionCompat$MediaSessionImplApi28 */
    static class MediaSessionImplApi28 extends MediaSessionImplApi21 {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        MediaSessionImplApi28(Context context, String tag, Bundle token2Bundle) {
            super(context, tag, token2Bundle);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        MediaSessionImplApi28(Object mediaSession) {
            super(mediaSession);
        }

        public void setCurrentControllerInfo(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
        }

        @NonNull
        public final MediaSessionManager.RemoteUserInfo getCurrentControllerInfo() {
            MediaSessionManager.RemoteUserInfo remoteUserInfo;
            new MediaSessionManager.RemoteUserInfo(((MediaSession) this.mSessionObj).getCurrentControllerInfo());
            return remoteUserInfo;
        }
    }
}

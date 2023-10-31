package android.support.p000v4.media;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.BadParcelableException;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.p000v4.app.BundleCompat;
import android.support.p000v4.media.MediaBrowserCompatApi21;
import android.support.p000v4.media.MediaBrowserCompatApi23;
import android.support.p000v4.media.MediaBrowserCompatApi26;
import android.support.p000v4.media.session.IMediaSession;
import android.support.p000v4.media.session.MediaSessionCompat;
import android.support.p000v4.p002os.ResultReceiver;
import android.support.p000v4.util.C1642ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* renamed from: android.support.v4.media.MediaBrowserCompat */
public final class MediaBrowserCompat {
    public static final String CUSTOM_ACTION_DOWNLOAD = "android.support.v4.media.action.DOWNLOAD";
    public static final String CUSTOM_ACTION_REMOVE_DOWNLOADED_FILE = "android.support.v4.media.action.REMOVE_DOWNLOADED_FILE";
    static final boolean DEBUG = Log.isLoggable(TAG, 3);
    public static final String EXTRA_DOWNLOAD_PROGRESS = "android.media.browse.extra.DOWNLOAD_PROGRESS";
    public static final String EXTRA_MEDIA_ID = "android.media.browse.extra.MEDIA_ID";
    public static final String EXTRA_PAGE = "android.media.browse.extra.PAGE";
    public static final String EXTRA_PAGE_SIZE = "android.media.browse.extra.PAGE_SIZE";
    static final String TAG = "MediaBrowserCompat";
    private final MediaBrowserImpl mImpl;

    /* renamed from: android.support.v4.media.MediaBrowserCompat$MediaBrowserImpl */
    interface MediaBrowserImpl {
        void connect();

        void disconnect();

        @Nullable
        Bundle getExtras();

        void getItem(@NonNull String str, @NonNull ItemCallback itemCallback);

        @Nullable
        Bundle getNotifyChildrenChangedOptions();

        @NonNull
        String getRoot();

        ComponentName getServiceComponent();

        @NonNull
        MediaSessionCompat.Token getSessionToken();

        boolean isConnected();

        void search(@NonNull String str, Bundle bundle, @NonNull SearchCallback searchCallback);

        void sendCustomAction(@NonNull String str, Bundle bundle, @Nullable CustomActionCallback customActionCallback);

        void subscribe(@NonNull String str, @Nullable Bundle bundle, @NonNull SubscriptionCallback subscriptionCallback);

        void unsubscribe(@NonNull String str, SubscriptionCallback subscriptionCallback);
    }

    /* renamed from: android.support.v4.media.MediaBrowserCompat$MediaBrowserServiceCallbackImpl */
    interface MediaBrowserServiceCallbackImpl {
        void onConnectionFailed(Messenger messenger);

        void onLoadChildren(Messenger messenger, String str, List list, Bundle bundle, Bundle bundle2);

        void onServiceConnected(Messenger messenger, String str, MediaSessionCompat.Token token, Bundle bundle);
    }

    public MediaBrowserCompat(Context context, ComponentName componentName, ConnectionCallback connectionCallback, Bundle bundle) {
        MediaBrowserImpl mediaBrowserImpl;
        MediaBrowserImpl mediaBrowserImpl2;
        MediaBrowserImpl mediaBrowserImpl3;
        MediaBrowserImpl mediaBrowserImpl4;
        Context context2 = context;
        ComponentName serviceComponent = componentName;
        ConnectionCallback callback = connectionCallback;
        Bundle rootHints = bundle;
        if (Build.VERSION.SDK_INT >= 26) {
            new MediaBrowserImplApi26(context2, serviceComponent, callback, rootHints);
            this.mImpl = mediaBrowserImpl4;
        } else if (Build.VERSION.SDK_INT >= 23) {
            new MediaBrowserImplApi23(context2, serviceComponent, callback, rootHints);
            this.mImpl = mediaBrowserImpl3;
        } else if (Build.VERSION.SDK_INT >= 21) {
            new MediaBrowserImplApi21(context2, serviceComponent, callback, rootHints);
            this.mImpl = mediaBrowserImpl2;
        } else {
            new MediaBrowserImplBase(context2, serviceComponent, callback, rootHints);
            this.mImpl = mediaBrowserImpl;
        }
    }

    public void connect() {
        this.mImpl.connect();
    }

    public void disconnect() {
        this.mImpl.disconnect();
    }

    public boolean isConnected() {
        return this.mImpl.isConnected();
    }

    @NonNull
    public ComponentName getServiceComponent() {
        return this.mImpl.getServiceComponent();
    }

    @NonNull
    public String getRoot() {
        return this.mImpl.getRoot();
    }

    @Nullable
    public Bundle getExtras() {
        return this.mImpl.getExtras();
    }

    @NonNull
    public MediaSessionCompat.Token getSessionToken() {
        return this.mImpl.getSessionToken();
    }

    public void subscribe(@NonNull String str, @NonNull SubscriptionCallback subscriptionCallback) {
        Throwable th;
        Throwable th2;
        String parentId = str;
        SubscriptionCallback callback = subscriptionCallback;
        if (TextUtils.isEmpty(parentId)) {
            Throwable th3 = th2;
            new IllegalArgumentException("parentId is empty");
            throw th3;
        } else if (callback == null) {
            Throwable th4 = th;
            new IllegalArgumentException("callback is null");
            throw th4;
        } else {
            this.mImpl.subscribe(parentId, (Bundle) null, callback);
        }
    }

    public void subscribe(@NonNull String str, @NonNull Bundle bundle, @NonNull SubscriptionCallback subscriptionCallback) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        String parentId = str;
        Bundle options = bundle;
        SubscriptionCallback callback = subscriptionCallback;
        if (TextUtils.isEmpty(parentId)) {
            Throwable th4 = th3;
            new IllegalArgumentException("parentId is empty");
            throw th4;
        } else if (callback == null) {
            Throwable th5 = th2;
            new IllegalArgumentException("callback is null");
            throw th5;
        } else if (options == null) {
            Throwable th6 = th;
            new IllegalArgumentException("options are null");
            throw th6;
        } else {
            this.mImpl.subscribe(parentId, options, callback);
        }
    }

    public void unsubscribe(@NonNull String str) {
        Throwable th;
        String parentId = str;
        if (TextUtils.isEmpty(parentId)) {
            Throwable th2 = th;
            new IllegalArgumentException("parentId is empty");
            throw th2;
        }
        this.mImpl.unsubscribe(parentId, (SubscriptionCallback) null);
    }

    public void unsubscribe(@NonNull String str, @NonNull SubscriptionCallback subscriptionCallback) {
        Throwable th;
        Throwable th2;
        String parentId = str;
        SubscriptionCallback callback = subscriptionCallback;
        if (TextUtils.isEmpty(parentId)) {
            Throwable th3 = th2;
            new IllegalArgumentException("parentId is empty");
            throw th3;
        } else if (callback == null) {
            Throwable th4 = th;
            new IllegalArgumentException("callback is null");
            throw th4;
        } else {
            this.mImpl.unsubscribe(parentId, callback);
        }
    }

    public void getItem(@NonNull String mediaId, @NonNull ItemCallback cb) {
        this.mImpl.getItem(mediaId, cb);
    }

    public void search(@NonNull String str, Bundle bundle, @NonNull SearchCallback searchCallback) {
        Throwable th;
        Throwable th2;
        String query = str;
        Bundle extras = bundle;
        SearchCallback callback = searchCallback;
        if (TextUtils.isEmpty(query)) {
            Throwable th3 = th2;
            new IllegalArgumentException("query cannot be empty");
            throw th3;
        } else if (callback == null) {
            Throwable th4 = th;
            new IllegalArgumentException("callback cannot be null");
            throw th4;
        } else {
            this.mImpl.search(query, extras, callback);
        }
    }

    public void sendCustomAction(@NonNull String str, Bundle bundle, @Nullable CustomActionCallback customActionCallback) {
        Throwable th;
        String action = str;
        Bundle extras = bundle;
        CustomActionCallback callback = customActionCallback;
        if (TextUtils.isEmpty(action)) {
            Throwable th2 = th;
            new IllegalArgumentException("action cannot be empty");
            throw th2;
        }
        this.mImpl.sendCustomAction(action, extras, callback);
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public Bundle getNotifyChildrenChangedOptions() {
        return this.mImpl.getNotifyChildrenChangedOptions();
    }

    /* renamed from: android.support.v4.media.MediaBrowserCompat$MediaItem */
    public static class MediaItem implements Parcelable {
        public static final Parcelable.Creator<MediaItem> CREATOR;
        public static final int FLAG_BROWSABLE = 1;
        public static final int FLAG_PLAYABLE = 2;
        private final MediaDescriptionCompat mDescription;
        private final int mFlags;

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        @Retention(RetentionPolicy.SOURCE)
        /* renamed from: android.support.v4.media.MediaBrowserCompat$MediaItem$Flags */
        public @interface Flags {
        }

        public static MediaItem fromMediaItem(Object obj) {
            MediaItem mediaItem;
            Object itemObj = obj;
            if (itemObj == null || Build.VERSION.SDK_INT < 21) {
                return null;
            }
            int flags = MediaBrowserCompatApi21.MediaItem.getFlags(itemObj);
            new MediaItem(MediaDescriptionCompat.fromMediaDescription(MediaBrowserCompatApi21.MediaItem.getDescription(itemObj)), flags);
            return mediaItem;
        }

        public static List<MediaItem> fromMediaItemList(List<?> list) {
            List<?> list2;
            List<?> itemList = list;
            if (itemList == null || Build.VERSION.SDK_INT < 21) {
                return null;
            }
            new ArrayList<>(itemList.size());
            List<?> items = list2;
            for (Object itemObj : itemList) {
                boolean add = items.add(fromMediaItem(itemObj));
            }
            return items;
        }

        public MediaItem(@NonNull MediaDescriptionCompat mediaDescriptionCompat, int i) {
            Throwable th;
            Throwable th2;
            MediaDescriptionCompat description = mediaDescriptionCompat;
            int flags = i;
            if (description == null) {
                Throwable th3 = th2;
                new IllegalArgumentException("description cannot be null");
                throw th3;
            } else if (TextUtils.isEmpty(description.getMediaId())) {
                Throwable th4 = th;
                new IllegalArgumentException("description must have a non-empty media id");
                throw th4;
            } else {
                this.mFlags = flags;
                this.mDescription = description;
            }
        }

        MediaItem(Parcel parcel) {
            Parcel in = parcel;
            this.mFlags = in.readInt();
            this.mDescription = MediaDescriptionCompat.CREATOR.createFromParcel(in);
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int flags) {
            Parcel out = parcel;
            out.writeInt(this.mFlags);
            this.mDescription.writeToParcel(out, flags);
        }

        public String toString() {
            StringBuilder sb;
            new StringBuilder("MediaItem{");
            StringBuilder sb2 = sb;
            StringBuilder append = sb2.append("mFlags=").append(this.mFlags);
            StringBuilder append2 = sb2.append(", mDescription=").append(this.mDescription);
            StringBuilder append3 = sb2.append('}');
            return sb2.toString();
        }

        static {
            Parcelable.Creator<MediaItem> creator;
            new Parcelable.Creator<MediaItem>() {
                public MediaItem createFromParcel(Parcel in) {
                    MediaItem mediaItem;
                    new MediaItem(in);
                    return mediaItem;
                }

                public MediaItem[] newArray(int size) {
                    return new MediaItem[size];
                }
            };
            CREATOR = creator;
        }

        public int getFlags() {
            return this.mFlags;
        }

        public boolean isBrowsable() {
            return (this.mFlags & 1) != 0;
        }

        public boolean isPlayable() {
            return (this.mFlags & 2) != 0;
        }

        @NonNull
        public MediaDescriptionCompat getDescription() {
            return this.mDescription;
        }

        @Nullable
        public String getMediaId() {
            return this.mDescription.getMediaId();
        }
    }

    /* renamed from: android.support.v4.media.MediaBrowserCompat$ConnectionCallback */
    public static class ConnectionCallback {
        ConnectionCallbackInternal mConnectionCallbackInternal;
        final Object mConnectionCallbackObj;

        /* renamed from: android.support.v4.media.MediaBrowserCompat$ConnectionCallback$ConnectionCallbackInternal */
        interface ConnectionCallbackInternal {
            void onConnected();

            void onConnectionFailed();

            void onConnectionSuspended();
        }

        public ConnectionCallback() {
            MediaBrowserCompatApi21.ConnectionCallback connectionCallback;
            if (Build.VERSION.SDK_INT >= 21) {
                new StubApi21(this);
                this.mConnectionCallbackObj = MediaBrowserCompatApi21.createConnectionCallback(connectionCallback);
                return;
            }
            this.mConnectionCallbackObj = null;
        }

        public void onConnected() {
        }

        public void onConnectionSuspended() {
        }

        public void onConnectionFailed() {
        }

        /* access modifiers changed from: package-private */
        public void setInternalConnectionCallback(ConnectionCallbackInternal connectionCallbackInternal) {
            ConnectionCallbackInternal connectionCallbackInternal2 = connectionCallbackInternal;
            this.mConnectionCallbackInternal = connectionCallbackInternal2;
        }

        /* renamed from: android.support.v4.media.MediaBrowserCompat$ConnectionCallback$StubApi21 */
        private class StubApi21 implements MediaBrowserCompatApi21.ConnectionCallback {
            final /* synthetic */ ConnectionCallback this$0;

            StubApi21(ConnectionCallback connectionCallback) {
                this.this$0 = connectionCallback;
            }

            public void onConnected() {
                if (this.this$0.mConnectionCallbackInternal != null) {
                    this.this$0.mConnectionCallbackInternal.onConnected();
                }
                this.this$0.onConnected();
            }

            public void onConnectionSuspended() {
                if (this.this$0.mConnectionCallbackInternal != null) {
                    this.this$0.mConnectionCallbackInternal.onConnectionSuspended();
                }
                this.this$0.onConnectionSuspended();
            }

            public void onConnectionFailed() {
                if (this.this$0.mConnectionCallbackInternal != null) {
                    this.this$0.mConnectionCallbackInternal.onConnectionFailed();
                }
                this.this$0.onConnectionFailed();
            }
        }
    }

    /* renamed from: android.support.v4.media.MediaBrowserCompat$SubscriptionCallback */
    public static abstract class SubscriptionCallback {
        final Object mSubscriptionCallbackObj;
        WeakReference<Subscription> mSubscriptionRef;
        final IBinder mToken;

        public SubscriptionCallback() {
            IBinder iBinder;
            MediaBrowserCompatApi21.SubscriptionCallback subscriptionCallback;
            MediaBrowserCompatApi26.SubscriptionCallback subscriptionCallback2;
            new Binder();
            this.mToken = iBinder;
            if (Build.VERSION.SDK_INT >= 26) {
                new StubApi26(this);
                this.mSubscriptionCallbackObj = MediaBrowserCompatApi26.createSubscriptionCallback(subscriptionCallback2);
            } else if (Build.VERSION.SDK_INT >= 21) {
                new StubApi21(this);
                this.mSubscriptionCallbackObj = MediaBrowserCompatApi21.createSubscriptionCallback(subscriptionCallback);
            } else {
                this.mSubscriptionCallbackObj = null;
            }
        }

        public void onChildrenLoaded(@NonNull String parentId, @NonNull List<MediaItem> list) {
        }

        public void onChildrenLoaded(@NonNull String parentId, @NonNull List<MediaItem> list, @NonNull Bundle options) {
        }

        public void onError(@NonNull String parentId) {
        }

        public void onError(@NonNull String parentId, @NonNull Bundle options) {
        }

        /* access modifiers changed from: package-private */
        public void setSubscription(Subscription subscription) {
            WeakReference<Subscription> weakReference;
            WeakReference<Subscription> weakReference2 = weakReference;
            new WeakReference<>(subscription);
            this.mSubscriptionRef = weakReference2;
        }

        /* renamed from: android.support.v4.media.MediaBrowserCompat$SubscriptionCallback$StubApi21 */
        private class StubApi21 implements MediaBrowserCompatApi21.SubscriptionCallback {
            final /* synthetic */ SubscriptionCallback this$0;

            StubApi21(SubscriptionCallback subscriptionCallback) {
                this.this$0 = subscriptionCallback;
            }

            public void onChildrenLoaded(@NonNull String str, List<?> list) {
                String parentId = str;
                List<?> children = list;
                Subscription sub = this.this$0.mSubscriptionRef == null ? null : (Subscription) this.this$0.mSubscriptionRef.get();
                if (sub == null) {
                    this.this$0.onChildrenLoaded(parentId, MediaItem.fromMediaItemList(children));
                    return;
                }
                List<MediaItem> itemList = MediaItem.fromMediaItemList(children);
                List<SubscriptionCallback> callbacks = sub.getCallbacks();
                List<Bundle> optionsList = sub.getOptionsList();
                for (int i = 0; i < callbacks.size(); i++) {
                    Bundle options = optionsList.get(i);
                    if (options == null) {
                        this.this$0.onChildrenLoaded(parentId, itemList);
                    } else {
                        this.this$0.onChildrenLoaded(parentId, applyOptions(itemList, options), options);
                    }
                }
            }

            public void onError(@NonNull String parentId) {
                this.this$0.onError(parentId);
            }

            /* access modifiers changed from: package-private */
            public List<MediaItem> applyOptions(List<MediaItem> list, Bundle bundle) {
                List<MediaItem> list2 = list;
                Bundle options = bundle;
                if (list2 == null) {
                    return null;
                }
                int page = options.getInt(MediaBrowserCompat.EXTRA_PAGE, -1);
                int pageSize = options.getInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, -1);
                if (page == -1 && pageSize == -1) {
                    return list2;
                }
                int fromIndex = pageSize * page;
                int toIndex = fromIndex + pageSize;
                if (page < 0 || pageSize < 1 || fromIndex >= list2.size()) {
                    return Collections.emptyList();
                }
                if (toIndex > list2.size()) {
                    toIndex = list2.size();
                }
                return list2.subList(fromIndex, toIndex);
            }
        }

        /* renamed from: android.support.v4.media.MediaBrowserCompat$SubscriptionCallback$StubApi26 */
        private class StubApi26 extends StubApi21 implements MediaBrowserCompatApi26.SubscriptionCallback {
            final /* synthetic */ SubscriptionCallback this$0;

            /* JADX WARNING: Illegal instructions before constructor call */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            StubApi26(android.support.p000v4.media.MediaBrowserCompat.SubscriptionCallback r5) {
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
                throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.media.MediaBrowserCompat.SubscriptionCallback.StubApi26.<init>(android.support.v4.media.MediaBrowserCompat$SubscriptionCallback):void");
            }

            public void onChildrenLoaded(@NonNull String parentId, List<?> children, @NonNull Bundle options) {
                this.this$0.onChildrenLoaded(parentId, MediaItem.fromMediaItemList(children), options);
            }

            public void onError(@NonNull String parentId, @NonNull Bundle options) {
                this.this$0.onError(parentId, options);
            }
        }
    }

    /* renamed from: android.support.v4.media.MediaBrowserCompat$ItemCallback */
    public static abstract class ItemCallback {
        final Object mItemCallbackObj;

        public ItemCallback() {
            MediaBrowserCompatApi23.ItemCallback itemCallback;
            if (Build.VERSION.SDK_INT >= 23) {
                new StubApi23(this);
                this.mItemCallbackObj = MediaBrowserCompatApi23.createItemCallback(itemCallback);
                return;
            }
            this.mItemCallbackObj = null;
        }

        public void onItemLoaded(MediaItem item) {
        }

        public void onError(@NonNull String itemId) {
        }

        /* renamed from: android.support.v4.media.MediaBrowserCompat$ItemCallback$StubApi23 */
        private class StubApi23 implements MediaBrowserCompatApi23.ItemCallback {
            final /* synthetic */ ItemCallback this$0;

            StubApi23(ItemCallback itemCallback) {
                this.this$0 = itemCallback;
            }

            public void onItemLoaded(Parcel parcel) {
                Parcel itemParcel = parcel;
                if (itemParcel == null) {
                    this.this$0.onItemLoaded((MediaItem) null);
                    return;
                }
                itemParcel.setDataPosition(0);
                MediaItem item = MediaItem.CREATOR.createFromParcel(itemParcel);
                itemParcel.recycle();
                this.this$0.onItemLoaded(item);
            }

            public void onError(@NonNull String itemId) {
                this.this$0.onError(itemId);
            }
        }
    }

    /* renamed from: android.support.v4.media.MediaBrowserCompat$SearchCallback */
    public static abstract class SearchCallback {
        public SearchCallback() {
        }

        public void onSearchResult(@NonNull String query, Bundle extras, @NonNull List<MediaItem> list) {
        }

        public void onError(@NonNull String query, Bundle extras) {
        }
    }

    /* renamed from: android.support.v4.media.MediaBrowserCompat$CustomActionCallback */
    public static abstract class CustomActionCallback {
        public CustomActionCallback() {
        }

        public void onProgressUpdate(String action, Bundle extras, Bundle data) {
        }

        public void onResult(String action, Bundle extras, Bundle resultData) {
        }

        public void onError(String action, Bundle extras, Bundle data) {
        }
    }

    /* renamed from: android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase */
    static class MediaBrowserImplBase implements MediaBrowserImpl, MediaBrowserServiceCallbackImpl {
        static final int CONNECT_STATE_CONNECTED = 3;
        static final int CONNECT_STATE_CONNECTING = 2;
        static final int CONNECT_STATE_DISCONNECTED = 1;
        static final int CONNECT_STATE_DISCONNECTING = 0;
        static final int CONNECT_STATE_SUSPENDED = 4;
        final ConnectionCallback mCallback;
        Messenger mCallbacksMessenger;
        final Context mContext;
        private Bundle mExtras;
        final CallbackHandler mHandler;
        private MediaSessionCompat.Token mMediaSessionToken;
        private Bundle mNotifyChildrenChangedOptions;
        final Bundle mRootHints;
        private String mRootId;
        ServiceBinderWrapper mServiceBinderWrapper;
        final ComponentName mServiceComponent;
        MediaServiceConnection mServiceConnection;
        int mState = 1;
        private final C1642ArrayMap<String, Subscription> mSubscriptions;

        public MediaBrowserImplBase(Context context, ComponentName componentName, ConnectionCallback connectionCallback, Bundle bundle) {
            CallbackHandler callbackHandler;
            C1642ArrayMap<String, Subscription> arrayMap;
            Bundle bundle2;
            Bundle bundle3;
            Throwable th;
            Throwable th2;
            Throwable th3;
            Context context2 = context;
            ComponentName serviceComponent = componentName;
            ConnectionCallback callback = connectionCallback;
            Bundle rootHints = bundle;
            new CallbackHandler(this);
            this.mHandler = callbackHandler;
            new C1642ArrayMap<>();
            this.mSubscriptions = arrayMap;
            if (context2 == null) {
                Throwable th4 = th3;
                new IllegalArgumentException("context must not be null");
                throw th4;
            } else if (serviceComponent == null) {
                Throwable th5 = th2;
                new IllegalArgumentException("service component must not be null");
                throw th5;
            } else if (callback == null) {
                Throwable th6 = th;
                new IllegalArgumentException("connection callback must not be null");
                throw th6;
            } else {
                this.mContext = context2;
                this.mServiceComponent = serviceComponent;
                this.mCallback = callback;
                if (rootHints == null) {
                    bundle3 = null;
                } else {
                    bundle3 = bundle2;
                    new Bundle(rootHints);
                }
                this.mRootHints = bundle3;
            }
        }

        public void connect() {
            Runnable runnable;
            Throwable th;
            StringBuilder sb;
            if (this.mState == 0 || this.mState == 1) {
                this.mState = 2;
                new Runnable(this) {
                    final /* synthetic */ MediaBrowserImplBase this$0;

                    {
                        this.this$0 = this$0;
                    }

                    public void run() {
                        Intent intent;
                        MediaServiceConnection mediaServiceConnection;
                        StringBuilder sb;
                        Throwable th;
                        StringBuilder sb2;
                        Throwable th2;
                        StringBuilder sb3;
                        Throwable th3;
                        StringBuilder sb4;
                        if (this.this$0.mState != 0) {
                            this.this$0.mState = 2;
                            if (MediaBrowserCompat.DEBUG && this.this$0.mServiceConnection != null) {
                                Throwable th4 = th3;
                                new StringBuilder();
                                new RuntimeException(sb4.append("mServiceConnection should be null. Instead it is ").append(this.this$0.mServiceConnection).toString());
                                throw th4;
                            } else if (this.this$0.mServiceBinderWrapper != null) {
                                Throwable th5 = th2;
                                new StringBuilder();
                                new RuntimeException(sb3.append("mServiceBinderWrapper should be null. Instead it is ").append(this.this$0.mServiceBinderWrapper).toString());
                                throw th5;
                            } else if (this.this$0.mCallbacksMessenger != null) {
                                Throwable th6 = th;
                                new StringBuilder();
                                new RuntimeException(sb2.append("mCallbacksMessenger should be null. Instead it is ").append(this.this$0.mCallbacksMessenger).toString());
                                throw th6;
                            } else {
                                new Intent(MediaBrowserServiceCompat.SERVICE_INTERFACE);
                                Intent intent2 = intent;
                                Intent component = intent2.setComponent(this.this$0.mServiceComponent);
                                MediaBrowserImplBase mediaBrowserImplBase = this.this$0;
                                new MediaServiceConnection(this.this$0);
                                mediaBrowserImplBase.mServiceConnection = mediaServiceConnection;
                                boolean bound = false;
                                try {
                                    bound = this.this$0.mContext.bindService(intent2, this.this$0.mServiceConnection, 1);
                                } catch (Exception e) {
                                    Exception exc = e;
                                    new StringBuilder();
                                    int e2 = Log.e(MediaBrowserCompat.TAG, sb.append("Failed binding to service ").append(this.this$0.mServiceComponent).toString());
                                }
                                if (!bound) {
                                    this.this$0.forceCloseConnection();
                                    this.this$0.mCallback.onConnectionFailed();
                                }
                                if (MediaBrowserCompat.DEBUG) {
                                    int d = Log.d(MediaBrowserCompat.TAG, "connect...");
                                    this.this$0.dump();
                                }
                            }
                        }
                    }
                };
                boolean post = this.mHandler.post(runnable);
                return;
            }
            Throwable th2 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("connect() called while neigther disconnecting nor disconnected (state=").append(getStateLabel(this.mState)).append(")").toString());
            throw th2;
        }

        public void disconnect() {
            Runnable runnable;
            this.mState = 0;
            new Runnable(this) {
                final /* synthetic */ MediaBrowserImplBase this$0;

                {
                    this.this$0 = this$0;
                }

                public void run() {
                    StringBuilder sb;
                    if (this.this$0.mCallbacksMessenger != null) {
                        try {
                            this.this$0.mServiceBinderWrapper.disconnect(this.this$0.mCallbacksMessenger);
                        } catch (RemoteException e) {
                            RemoteException remoteException = e;
                            new StringBuilder();
                            int w = Log.w(MediaBrowserCompat.TAG, sb.append("RemoteException during connect for ").append(this.this$0.mServiceComponent).toString());
                        }
                    }
                    int state = this.this$0.mState;
                    this.this$0.forceCloseConnection();
                    if (state != 0) {
                        this.this$0.mState = state;
                    }
                    if (MediaBrowserCompat.DEBUG) {
                        int d = Log.d(MediaBrowserCompat.TAG, "disconnect...");
                        this.this$0.dump();
                    }
                }
            };
            boolean post = this.mHandler.post(runnable);
        }

        /* access modifiers changed from: package-private */
        public void forceCloseConnection() {
            if (this.mServiceConnection != null) {
                this.mContext.unbindService(this.mServiceConnection);
            }
            this.mState = 1;
            this.mServiceConnection = null;
            this.mServiceBinderWrapper = null;
            this.mCallbacksMessenger = null;
            this.mHandler.setCallbacksMessenger((Messenger) null);
            this.mRootId = null;
            this.mMediaSessionToken = null;
        }

        public boolean isConnected() {
            return this.mState == 3;
        }

        @NonNull
        public ComponentName getServiceComponent() {
            Throwable th;
            StringBuilder sb;
            if (isConnected()) {
                return this.mServiceComponent;
            }
            Throwable th2 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("getServiceComponent() called while not connected (state=").append(this.mState).append(")").toString());
            throw th2;
        }

        @NonNull
        public String getRoot() {
            Throwable th;
            StringBuilder sb;
            if (isConnected()) {
                return this.mRootId;
            }
            Throwable th2 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("getRoot() called while not connected(state=").append(getStateLabel(this.mState)).append(")").toString());
            throw th2;
        }

        @Nullable
        public Bundle getExtras() {
            Throwable th;
            StringBuilder sb;
            if (isConnected()) {
                return this.mExtras;
            }
            Throwable th2 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("getExtras() called while not connected (state=").append(getStateLabel(this.mState)).append(")").toString());
            throw th2;
        }

        @NonNull
        public MediaSessionCompat.Token getSessionToken() {
            Throwable th;
            StringBuilder sb;
            if (isConnected()) {
                return this.mMediaSessionToken;
            }
            Throwable th2 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("getSessionToken() called while not connected(state=").append(this.mState).append(")").toString());
            throw th2;
        }

        public void subscribe(@NonNull String str, Bundle bundle, @NonNull SubscriptionCallback subscriptionCallback) {
            Bundle bundle2;
            Bundle bundle3;
            StringBuilder sb;
            Subscription subscription;
            String parentId = str;
            Bundle options = bundle;
            SubscriptionCallback callback = subscriptionCallback;
            Subscription sub = this.mSubscriptions.get(parentId);
            if (sub == null) {
                new Subscription();
                sub = subscription;
                Subscription put = this.mSubscriptions.put(parentId, sub);
            }
            if (options == null) {
                bundle3 = null;
            } else {
                bundle3 = bundle2;
                new Bundle(options);
            }
            Bundle copiedOptions = bundle3;
            sub.putCallback(copiedOptions, callback);
            if (isConnected()) {
                try {
                    this.mServiceBinderWrapper.addSubscription(parentId, callback.mToken, copiedOptions, this.mCallbacksMessenger);
                } catch (RemoteException e) {
                    RemoteException remoteException = e;
                    new StringBuilder();
                    int d = Log.d(MediaBrowserCompat.TAG, sb.append("addSubscription failed with RemoteException parentId=").append(parentId).toString());
                }
            }
        }

        public void unsubscribe(@NonNull String str, SubscriptionCallback subscriptionCallback) {
            StringBuilder sb;
            String parentId = str;
            SubscriptionCallback callback = subscriptionCallback;
            Subscription sub = this.mSubscriptions.get(parentId);
            if (sub != null) {
                if (callback == null) {
                    try {
                        if (isConnected()) {
                            this.mServiceBinderWrapper.removeSubscription(parentId, (IBinder) null, this.mCallbacksMessenger);
                        }
                    } catch (RemoteException e) {
                        RemoteException remoteException = e;
                        new StringBuilder();
                        int d = Log.d(MediaBrowserCompat.TAG, sb.append("removeSubscription failed with RemoteException parentId=").append(parentId).toString());
                    }
                } else {
                    List<SubscriptionCallback> callbacks = sub.getCallbacks();
                    List<Bundle> optionsList = sub.getOptionsList();
                    for (int i = callbacks.size() - 1; i >= 0; i--) {
                        if (callbacks.get(i) == callback) {
                            if (isConnected()) {
                                this.mServiceBinderWrapper.removeSubscription(parentId, callback.mToken, this.mCallbacksMessenger);
                            }
                            SubscriptionCallback remove = callbacks.remove(i);
                            Bundle remove2 = optionsList.remove(i);
                        }
                    }
                }
                if (sub.isEmpty() || callback == null) {
                    Subscription remove3 = this.mSubscriptions.remove(parentId);
                }
            }
        }

        public void getItem(@NonNull String str, @NonNull ItemCallback itemCallback) {
            ResultReceiver receiver;
            StringBuilder sb;
            Runnable runnable;
            Runnable runnable2;
            Throwable th;
            Throwable th2;
            String mediaId = str;
            ItemCallback cb = itemCallback;
            if (TextUtils.isEmpty(mediaId)) {
                Throwable th3 = th2;
                new IllegalArgumentException("mediaId is empty");
                throw th3;
            } else if (cb == null) {
                Throwable th4 = th;
                new IllegalArgumentException("cb is null");
                throw th4;
            } else if (!isConnected()) {
                int i = Log.i(MediaBrowserCompat.TAG, "Not connected, unable to retrieve the MediaItem.");
                final ItemCallback itemCallback2 = cb;
                final String str2 = mediaId;
                new Runnable(this) {
                    final /* synthetic */ MediaBrowserImplBase this$0;

                    {
                        this.this$0 = this$0;
                    }

                    public void run() {
                        itemCallback2.onError(str2);
                    }
                };
                boolean post = this.mHandler.post(runnable2);
            } else {
                new ItemReceiver(mediaId, cb, this.mHandler);
                try {
                    this.mServiceBinderWrapper.getMediaItem(mediaId, receiver, this.mCallbacksMessenger);
                } catch (RemoteException e) {
                    RemoteException remoteException = e;
                    new StringBuilder();
                    int i2 = Log.i(MediaBrowserCompat.TAG, sb.append("Remote error getting media item: ").append(mediaId).toString());
                    final ItemCallback itemCallback3 = cb;
                    final String str3 = mediaId;
                    new Runnable(this) {
                        final /* synthetic */ MediaBrowserImplBase this$0;

                        {
                            this.this$0 = this$0;
                        }

                        public void run() {
                            itemCallback3.onError(str3);
                        }
                    };
                    boolean post2 = this.mHandler.post(runnable);
                }
            }
        }

        public void search(@NonNull String str, Bundle bundle, @NonNull SearchCallback searchCallback) {
            ResultReceiver receiver;
            StringBuilder sb;
            Runnable runnable;
            Throwable th;
            StringBuilder sb2;
            String query = str;
            Bundle extras = bundle;
            SearchCallback callback = searchCallback;
            if (!isConnected()) {
                Throwable th2 = th;
                new StringBuilder();
                new IllegalStateException(sb2.append("search() called while not connected (state=").append(getStateLabel(this.mState)).append(")").toString());
                throw th2;
            }
            new SearchResultReceiver(query, extras, callback, this.mHandler);
            try {
                this.mServiceBinderWrapper.search(query, extras, receiver, this.mCallbacksMessenger);
            } catch (RemoteException e) {
                new StringBuilder();
                int i = Log.i(MediaBrowserCompat.TAG, sb.append("Remote error searching items with query: ").append(query).toString(), e);
                final SearchCallback searchCallback2 = callback;
                final String str2 = query;
                final Bundle bundle2 = extras;
                new Runnable(this) {
                    final /* synthetic */ MediaBrowserImplBase this$0;

                    {
                        this.this$0 = this$0;
                    }

                    public void run() {
                        searchCallback2.onError(str2, bundle2);
                    }
                };
                boolean post = this.mHandler.post(runnable);
            }
        }

        public void sendCustomAction(@NonNull String str, Bundle bundle, @Nullable CustomActionCallback customActionCallback) {
            ResultReceiver receiver;
            StringBuilder sb;
            Runnable runnable;
            Throwable th;
            StringBuilder sb2;
            String action = str;
            Bundle extras = bundle;
            CustomActionCallback callback = customActionCallback;
            if (!isConnected()) {
                Throwable th2 = th;
                new StringBuilder();
                new IllegalStateException(sb2.append("Cannot send a custom action (").append(action).append(") with ").append("extras ").append(extras).append(" because the browser is not connected to the ").append("service.").toString());
                throw th2;
            }
            new CustomActionResultReceiver(action, extras, callback, this.mHandler);
            try {
                this.mServiceBinderWrapper.sendCustomAction(action, extras, receiver, this.mCallbacksMessenger);
            } catch (RemoteException e) {
                new StringBuilder();
                int i = Log.i(MediaBrowserCompat.TAG, sb.append("Remote error sending a custom action: action=").append(action).append(", extras=").append(extras).toString(), e);
                if (callback != null) {
                    final CustomActionCallback customActionCallback2 = callback;
                    final String str2 = action;
                    final Bundle bundle2 = extras;
                    new Runnable(this) {
                        final /* synthetic */ MediaBrowserImplBase this$0;

                        {
                            this.this$0 = this$0;
                        }

                        public void run() {
                            customActionCallback2.onError(str2, bundle2, (Bundle) null);
                        }
                    };
                    boolean post = this.mHandler.post(runnable);
                }
            }
        }

        public void onServiceConnected(Messenger callback, String str, MediaSessionCompat.Token token, Bundle bundle) {
            StringBuilder sb;
            String root = str;
            MediaSessionCompat.Token session = token;
            Bundle extra = bundle;
            if (isCurrent(callback, "onConnect")) {
                if (this.mState != 2) {
                    new StringBuilder();
                    int w = Log.w(MediaBrowserCompat.TAG, sb.append("onConnect from service while mState=").append(getStateLabel(this.mState)).append("... ignoring").toString());
                    return;
                }
                this.mRootId = root;
                this.mMediaSessionToken = session;
                this.mExtras = extra;
                this.mState = 3;
                if (MediaBrowserCompat.DEBUG) {
                    int d = Log.d(MediaBrowserCompat.TAG, "ServiceCallbacks.onConnect...");
                    dump();
                }
                this.mCallback.onConnected();
                try {
                    for (Map.Entry<String, Subscription> subscriptionEntry : this.mSubscriptions.entrySet()) {
                        String id = subscriptionEntry.getKey();
                        Subscription sub = subscriptionEntry.getValue();
                        List<SubscriptionCallback> callbackList = sub.getCallbacks();
                        List<Bundle> optionsList = sub.getOptionsList();
                        for (int i = 0; i < callbackList.size(); i++) {
                            this.mServiceBinderWrapper.addSubscription(id, callbackList.get(i).mToken, optionsList.get(i), this.mCallbacksMessenger);
                        }
                    }
                } catch (RemoteException e) {
                    RemoteException remoteException = e;
                    int d2 = Log.d(MediaBrowserCompat.TAG, "addSubscription failed with RemoteException.");
                }
            }
        }

        public void onConnectionFailed(Messenger callback) {
            StringBuilder sb;
            StringBuilder sb2;
            new StringBuilder();
            int e = Log.e(MediaBrowserCompat.TAG, sb.append("onConnectFailed for ").append(this.mServiceComponent).toString());
            if (isCurrent(callback, "onConnectFailed")) {
                if (this.mState != 2) {
                    new StringBuilder();
                    int w = Log.w(MediaBrowserCompat.TAG, sb2.append("onConnect from service while mState=").append(getStateLabel(this.mState)).append("... ignoring").toString());
                    return;
                }
                forceCloseConnection();
                this.mCallback.onConnectionFailed();
            }
        }

        public void onLoadChildren(Messenger callback, String str, List list, Bundle bundle, Bundle bundle2) {
            StringBuilder sb;
            StringBuilder sb2;
            String parentId = str;
            List list2 = list;
            Bundle options = bundle;
            Bundle notifyChildrenChangedOptions = bundle2;
            if (isCurrent(callback, "onLoadChildren")) {
                if (MediaBrowserCompat.DEBUG) {
                    new StringBuilder();
                    int d = Log.d(MediaBrowserCompat.TAG, sb2.append("onLoadChildren for ").append(this.mServiceComponent).append(" id=").append(parentId).toString());
                }
                Subscription subscription = this.mSubscriptions.get(parentId);
                if (subscription != null) {
                    SubscriptionCallback subscriptionCallback = subscription.getCallback(options);
                    if (subscriptionCallback == null) {
                        return;
                    }
                    if (options == null) {
                        if (list2 == null) {
                            subscriptionCallback.onError(parentId);
                            return;
                        }
                        this.mNotifyChildrenChangedOptions = notifyChildrenChangedOptions;
                        subscriptionCallback.onChildrenLoaded(parentId, list2);
                        this.mNotifyChildrenChangedOptions = null;
                    } else if (list2 == null) {
                        subscriptionCallback.onError(parentId, options);
                    } else {
                        this.mNotifyChildrenChangedOptions = notifyChildrenChangedOptions;
                        subscriptionCallback.onChildrenLoaded(parentId, list2, options);
                        this.mNotifyChildrenChangedOptions = null;
                    }
                } else if (MediaBrowserCompat.DEBUG) {
                    new StringBuilder();
                    int d2 = Log.d(MediaBrowserCompat.TAG, sb.append("onLoadChildren for id that isn't subscribed id=").append(parentId).toString());
                }
            }
        }

        public Bundle getNotifyChildrenChangedOptions() {
            return this.mNotifyChildrenChangedOptions;
        }

        private static String getStateLabel(int i) {
            StringBuilder sb;
            int state = i;
            switch (state) {
                case 0:
                    return "CONNECT_STATE_DISCONNECTING";
                case 1:
                    return "CONNECT_STATE_DISCONNECTED";
                case 2:
                    return "CONNECT_STATE_CONNECTING";
                case 3:
                    return "CONNECT_STATE_CONNECTED";
                case 4:
                    return "CONNECT_STATE_SUSPENDED";
                default:
                    new StringBuilder();
                    return sb.append("UNKNOWN/").append(state).toString();
            }
        }

        private boolean isCurrent(Messenger callback, String str) {
            StringBuilder sb;
            String funcName = str;
            if (this.mCallbacksMessenger == callback && this.mState != 0 && this.mState != 1) {
                return true;
            }
            if (!(this.mState == 0 || this.mState == 1)) {
                new StringBuilder();
                int i = Log.i(MediaBrowserCompat.TAG, sb.append(funcName).append(" for ").append(this.mServiceComponent).append(" with mCallbacksMessenger=").append(this.mCallbacksMessenger).append(" this=").append(this).toString());
            }
            return false;
        }

        /* access modifiers changed from: package-private */
        public void dump() {
            StringBuilder sb;
            StringBuilder sb2;
            StringBuilder sb3;
            StringBuilder sb4;
            StringBuilder sb5;
            StringBuilder sb6;
            StringBuilder sb7;
            StringBuilder sb8;
            StringBuilder sb9;
            int d = Log.d(MediaBrowserCompat.TAG, "MediaBrowserCompat...");
            new StringBuilder();
            int d2 = Log.d(MediaBrowserCompat.TAG, sb.append("  mServiceComponent=").append(this.mServiceComponent).toString());
            new StringBuilder();
            int d3 = Log.d(MediaBrowserCompat.TAG, sb2.append("  mCallback=").append(this.mCallback).toString());
            new StringBuilder();
            int d4 = Log.d(MediaBrowserCompat.TAG, sb3.append("  mRootHints=").append(this.mRootHints).toString());
            new StringBuilder();
            int d5 = Log.d(MediaBrowserCompat.TAG, sb4.append("  mState=").append(getStateLabel(this.mState)).toString());
            new StringBuilder();
            int d6 = Log.d(MediaBrowserCompat.TAG, sb5.append("  mServiceConnection=").append(this.mServiceConnection).toString());
            new StringBuilder();
            int d7 = Log.d(MediaBrowserCompat.TAG, sb6.append("  mServiceBinderWrapper=").append(this.mServiceBinderWrapper).toString());
            new StringBuilder();
            int d8 = Log.d(MediaBrowserCompat.TAG, sb7.append("  mCallbacksMessenger=").append(this.mCallbacksMessenger).toString());
            new StringBuilder();
            int d9 = Log.d(MediaBrowserCompat.TAG, sb8.append("  mRootId=").append(this.mRootId).toString());
            new StringBuilder();
            int d10 = Log.d(MediaBrowserCompat.TAG, sb9.append("  mMediaSessionToken=").append(this.mMediaSessionToken).toString());
        }

        /* renamed from: android.support.v4.media.MediaBrowserCompat$MediaBrowserImplBase$MediaServiceConnection */
        private class MediaServiceConnection implements ServiceConnection {
            final /* synthetic */ MediaBrowserImplBase this$0;

            MediaServiceConnection(MediaBrowserImplBase mediaBrowserImplBase) {
                this.this$0 = mediaBrowserImplBase;
            }

            public void onServiceConnected(ComponentName name, IBinder binder) {
                Runnable runnable;
                final ComponentName componentName = name;
                final IBinder iBinder = binder;
                new Runnable(this) {
                    final /* synthetic */ MediaServiceConnection this$1;

                    {
                        this.this$1 = this$1;
                    }

                    public void run() {
                        ServiceBinderWrapper serviceBinderWrapper;
                        Messenger messenger;
                        StringBuilder sb;
                        StringBuilder sb2;
                        if (MediaBrowserCompat.DEBUG) {
                            new StringBuilder();
                            int d = Log.d(MediaBrowserCompat.TAG, sb2.append("MediaServiceConnection.onServiceConnected name=").append(componentName).append(" binder=").append(iBinder).toString());
                            this.this$1.this$0.dump();
                        }
                        if (this.this$1.isCurrent("onServiceConnected")) {
                            MediaBrowserImplBase mediaBrowserImplBase = this.this$1.this$0;
                            new ServiceBinderWrapper(iBinder, this.this$1.this$0.mRootHints);
                            mediaBrowserImplBase.mServiceBinderWrapper = serviceBinderWrapper;
                            MediaBrowserImplBase mediaBrowserImplBase2 = this.this$1.this$0;
                            new Messenger(this.this$1.this$0.mHandler);
                            mediaBrowserImplBase2.mCallbacksMessenger = messenger;
                            this.this$1.this$0.mHandler.setCallbacksMessenger(this.this$1.this$0.mCallbacksMessenger);
                            this.this$1.this$0.mState = 2;
                            try {
                                if (MediaBrowserCompat.DEBUG) {
                                    int d2 = Log.d(MediaBrowserCompat.TAG, "ServiceCallbacks.onConnect...");
                                    this.this$1.this$0.dump();
                                }
                                this.this$1.this$0.mServiceBinderWrapper.connect(this.this$1.this$0.mContext, this.this$1.this$0.mCallbacksMessenger);
                            } catch (RemoteException e) {
                                RemoteException remoteException = e;
                                new StringBuilder();
                                int w = Log.w(MediaBrowserCompat.TAG, sb.append("RemoteException during connect for ").append(this.this$1.this$0.mServiceComponent).toString());
                                if (MediaBrowserCompat.DEBUG) {
                                    int d3 = Log.d(MediaBrowserCompat.TAG, "ServiceCallbacks.onConnect...");
                                    this.this$1.this$0.dump();
                                }
                            }
                        }
                    }
                };
                postOrRun(runnable);
            }

            public void onServiceDisconnected(ComponentName name) {
                Runnable runnable;
                final ComponentName componentName = name;
                new Runnable(this) {
                    final /* synthetic */ MediaServiceConnection this$1;

                    {
                        this.this$1 = this$1;
                    }

                    public void run() {
                        StringBuilder sb;
                        if (MediaBrowserCompat.DEBUG) {
                            new StringBuilder();
                            int d = Log.d(MediaBrowserCompat.TAG, sb.append("MediaServiceConnection.onServiceDisconnected name=").append(componentName).append(" this=").append(this).append(" mServiceConnection=").append(this.this$1.this$0.mServiceConnection).toString());
                            this.this$1.this$0.dump();
                        }
                        if (this.this$1.isCurrent("onServiceDisconnected")) {
                            this.this$1.this$0.mServiceBinderWrapper = null;
                            this.this$1.this$0.mCallbacksMessenger = null;
                            this.this$1.this$0.mHandler.setCallbacksMessenger((Messenger) null);
                            this.this$1.this$0.mState = 4;
                            this.this$1.this$0.mCallback.onConnectionSuspended();
                        }
                    }
                };
                postOrRun(runnable);
            }

            private void postOrRun(Runnable runnable) {
                Runnable r = runnable;
                if (Thread.currentThread() == this.this$0.mHandler.getLooper().getThread()) {
                    r.run();
                } else {
                    boolean post = this.this$0.mHandler.post(r);
                }
            }

            /* access modifiers changed from: package-private */
            public boolean isCurrent(String str) {
                StringBuilder sb;
                String funcName = str;
                if (this.this$0.mServiceConnection == this && this.this$0.mState != 0 && this.this$0.mState != 1) {
                    return true;
                }
                if (!(this.this$0.mState == 0 || this.this$0.mState == 1)) {
                    new StringBuilder();
                    int i = Log.i(MediaBrowserCompat.TAG, sb.append(funcName).append(" for ").append(this.this$0.mServiceComponent).append(" with mServiceConnection=").append(this.this$0.mServiceConnection).append(" this=").append(this).toString());
                }
                return false;
            }
        }
    }

    @RequiresApi(21)
    /* renamed from: android.support.v4.media.MediaBrowserCompat$MediaBrowserImplApi21 */
    static class MediaBrowserImplApi21 implements MediaBrowserImpl, MediaBrowserServiceCallbackImpl, ConnectionCallback.ConnectionCallbackInternal {
        protected final Object mBrowserObj;
        protected Messenger mCallbacksMessenger;
        final Context mContext;
        protected final CallbackHandler mHandler;
        private MediaSessionCompat.Token mMediaSessionToken;
        private Bundle mNotifyChildrenChangedOptions;
        protected final Bundle mRootHints;
        protected ServiceBinderWrapper mServiceBinderWrapper;
        protected int mServiceVersion;
        private final C1642ArrayMap<String, Subscription> mSubscriptions;

        MediaBrowserImplApi21(Context context, ComponentName componentName, ConnectionCallback connectionCallback, Bundle bundle) {
            CallbackHandler callbackHandler;
            C1642ArrayMap<String, Subscription> arrayMap;
            Bundle bundle2;
            Bundle bundle3;
            Bundle bundle4;
            Context context2 = context;
            ComponentName serviceComponent = componentName;
            ConnectionCallback callback = connectionCallback;
            Bundle rootHints = bundle;
            new CallbackHandler(this);
            this.mHandler = callbackHandler;
            new C1642ArrayMap<>();
            this.mSubscriptions = arrayMap;
            this.mContext = context2;
            if (rootHints != null) {
                bundle3 = bundle4;
                new Bundle(rootHints);
            } else {
                bundle3 = bundle2;
                new Bundle();
            }
            this.mRootHints = bundle3;
            this.mRootHints.putInt(MediaBrowserProtocol.EXTRA_CLIENT_VERSION, 1);
            callback.setInternalConnectionCallback(this);
            this.mBrowserObj = MediaBrowserCompatApi21.createBrowser(context2, serviceComponent, callback.mConnectionCallbackObj, this.mRootHints);
        }

        public void connect() {
            MediaBrowserCompatApi21.connect(this.mBrowserObj);
        }

        public void disconnect() {
            if (!(this.mServiceBinderWrapper == null || this.mCallbacksMessenger == null)) {
                try {
                    this.mServiceBinderWrapper.unregisterCallbackMessenger(this.mCallbacksMessenger);
                } catch (RemoteException e) {
                    RemoteException remoteException = e;
                    int i = Log.i(MediaBrowserCompat.TAG, "Remote error unregistering client messenger.");
                }
            }
            MediaBrowserCompatApi21.disconnect(this.mBrowserObj);
        }

        public boolean isConnected() {
            return MediaBrowserCompatApi21.isConnected(this.mBrowserObj);
        }

        public ComponentName getServiceComponent() {
            return MediaBrowserCompatApi21.getServiceComponent(this.mBrowserObj);
        }

        @NonNull
        public String getRoot() {
            return MediaBrowserCompatApi21.getRoot(this.mBrowserObj);
        }

        @Nullable
        public Bundle getExtras() {
            return MediaBrowserCompatApi21.getExtras(this.mBrowserObj);
        }

        @NonNull
        public MediaSessionCompat.Token getSessionToken() {
            if (this.mMediaSessionToken == null) {
                this.mMediaSessionToken = MediaSessionCompat.Token.fromToken(MediaBrowserCompatApi21.getSessionToken(this.mBrowserObj));
            }
            return this.mMediaSessionToken;
        }

        public void subscribe(@NonNull String str, Bundle bundle, @NonNull SubscriptionCallback subscriptionCallback) {
            Bundle bundle2;
            Bundle bundle3;
            StringBuilder sb;
            Subscription subscription;
            String parentId = str;
            Bundle options = bundle;
            SubscriptionCallback callback = subscriptionCallback;
            Subscription sub = this.mSubscriptions.get(parentId);
            if (sub == null) {
                new Subscription();
                sub = subscription;
                Subscription put = this.mSubscriptions.put(parentId, sub);
            }
            callback.setSubscription(sub);
            if (options == null) {
                bundle3 = null;
            } else {
                bundle3 = bundle2;
                new Bundle(options);
            }
            Bundle copiedOptions = bundle3;
            sub.putCallback(copiedOptions, callback);
            if (this.mServiceBinderWrapper == null) {
                MediaBrowserCompatApi21.subscribe(this.mBrowserObj, parentId, callback.mSubscriptionCallbackObj);
                return;
            }
            try {
                this.mServiceBinderWrapper.addSubscription(parentId, callback.mToken, copiedOptions, this.mCallbacksMessenger);
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                new StringBuilder();
                int i = Log.i(MediaBrowserCompat.TAG, sb.append("Remote error subscribing media item: ").append(parentId).toString());
            }
        }

        public void unsubscribe(@NonNull String str, SubscriptionCallback subscriptionCallback) {
            StringBuilder sb;
            String parentId = str;
            SubscriptionCallback callback = subscriptionCallback;
            Subscription sub = this.mSubscriptions.get(parentId);
            if (sub != null) {
                if (this.mServiceBinderWrapper == null) {
                    if (callback == null) {
                        MediaBrowserCompatApi21.unsubscribe(this.mBrowserObj, parentId);
                    } else {
                        List<SubscriptionCallback> callbacks = sub.getCallbacks();
                        List<Bundle> optionsList = sub.getOptionsList();
                        for (int i = callbacks.size() - 1; i >= 0; i--) {
                            if (callbacks.get(i) == callback) {
                                SubscriptionCallback remove = callbacks.remove(i);
                                Bundle remove2 = optionsList.remove(i);
                            }
                        }
                        if (callbacks.size() == 0) {
                            MediaBrowserCompatApi21.unsubscribe(this.mBrowserObj, parentId);
                        }
                    }
                } else if (callback == null) {
                    try {
                        this.mServiceBinderWrapper.removeSubscription(parentId, (IBinder) null, this.mCallbacksMessenger);
                    } catch (RemoteException e) {
                        RemoteException remoteException = e;
                        new StringBuilder();
                        int d = Log.d(MediaBrowserCompat.TAG, sb.append("removeSubscription failed with RemoteException parentId=").append(parentId).toString());
                    }
                } else {
                    List<SubscriptionCallback> callbacks2 = sub.getCallbacks();
                    List<Bundle> optionsList2 = sub.getOptionsList();
                    for (int i2 = callbacks2.size() - 1; i2 >= 0; i2--) {
                        if (callbacks2.get(i2) == callback) {
                            this.mServiceBinderWrapper.removeSubscription(parentId, callback.mToken, this.mCallbacksMessenger);
                            SubscriptionCallback remove3 = callbacks2.remove(i2);
                            Bundle remove4 = optionsList2.remove(i2);
                        }
                    }
                }
                if (sub.isEmpty() || callback == null) {
                    Subscription remove5 = this.mSubscriptions.remove(parentId);
                }
            }
        }

        public void getItem(@NonNull String str, @NonNull ItemCallback itemCallback) {
            ResultReceiver receiver;
            StringBuilder sb;
            Runnable runnable;
            Runnable runnable2;
            Runnable runnable3;
            Throwable th;
            Throwable th2;
            String mediaId = str;
            ItemCallback cb = itemCallback;
            if (TextUtils.isEmpty(mediaId)) {
                Throwable th3 = th2;
                new IllegalArgumentException("mediaId is empty");
                throw th3;
            } else if (cb == null) {
                Throwable th4 = th;
                new IllegalArgumentException("cb is null");
                throw th4;
            } else if (!MediaBrowserCompatApi21.isConnected(this.mBrowserObj)) {
                int i = Log.i(MediaBrowserCompat.TAG, "Not connected, unable to retrieve the MediaItem.");
                final ItemCallback itemCallback2 = cb;
                final String str2 = mediaId;
                new Runnable(this) {
                    final /* synthetic */ MediaBrowserImplApi21 this$0;

                    {
                        this.this$0 = this$0;
                    }

                    public void run() {
                        itemCallback2.onError(str2);
                    }
                };
                boolean post = this.mHandler.post(runnable3);
            } else if (this.mServiceBinderWrapper == null) {
                final ItemCallback itemCallback3 = cb;
                final String str3 = mediaId;
                new Runnable(this) {
                    final /* synthetic */ MediaBrowserImplApi21 this$0;

                    {
                        this.this$0 = this$0;
                    }

                    public void run() {
                        itemCallback3.onError(str3);
                    }
                };
                boolean post2 = this.mHandler.post(runnable2);
            } else {
                new ItemReceiver(mediaId, cb, this.mHandler);
                try {
                    this.mServiceBinderWrapper.getMediaItem(mediaId, receiver, this.mCallbacksMessenger);
                } catch (RemoteException e) {
                    RemoteException remoteException = e;
                    new StringBuilder();
                    int i2 = Log.i(MediaBrowserCompat.TAG, sb.append("Remote error getting media item: ").append(mediaId).toString());
                    final ItemCallback itemCallback4 = cb;
                    final String str4 = mediaId;
                    new Runnable(this) {
                        final /* synthetic */ MediaBrowserImplApi21 this$0;

                        {
                            this.this$0 = this$0;
                        }

                        public void run() {
                            itemCallback4.onError(str4);
                        }
                    };
                    boolean post3 = this.mHandler.post(runnable);
                }
            }
        }

        public void search(@NonNull String str, Bundle bundle, @NonNull SearchCallback searchCallback) {
            ResultReceiver receiver;
            StringBuilder sb;
            Runnable runnable;
            Runnable runnable2;
            Throwable th;
            String query = str;
            Bundle extras = bundle;
            SearchCallback callback = searchCallback;
            if (!isConnected()) {
                Throwable th2 = th;
                new IllegalStateException("search() called while not connected");
                throw th2;
            } else if (this.mServiceBinderWrapper == null) {
                int i = Log.i(MediaBrowserCompat.TAG, "The connected service doesn't support search.");
                final SearchCallback searchCallback2 = callback;
                final String str2 = query;
                final Bundle bundle2 = extras;
                new Runnable(this) {
                    final /* synthetic */ MediaBrowserImplApi21 this$0;

                    {
                        this.this$0 = this$0;
                    }

                    public void run() {
                        searchCallback2.onError(str2, bundle2);
                    }
                };
                boolean post = this.mHandler.post(runnable2);
            } else {
                new SearchResultReceiver(query, extras, callback, this.mHandler);
                try {
                    this.mServiceBinderWrapper.search(query, extras, receiver, this.mCallbacksMessenger);
                } catch (RemoteException e) {
                    new StringBuilder();
                    int i2 = Log.i(MediaBrowserCompat.TAG, sb.append("Remote error searching items with query: ").append(query).toString(), e);
                    final SearchCallback searchCallback3 = callback;
                    final String str3 = query;
                    final Bundle bundle3 = extras;
                    new Runnable(this) {
                        final /* synthetic */ MediaBrowserImplApi21 this$0;

                        {
                            this.this$0 = this$0;
                        }

                        public void run() {
                            searchCallback3.onError(str3, bundle3);
                        }
                    };
                    boolean post2 = this.mHandler.post(runnable);
                }
            }
        }

        public void sendCustomAction(@NonNull String str, Bundle bundle, @Nullable CustomActionCallback customActionCallback) {
            ResultReceiver receiver;
            StringBuilder sb;
            Runnable runnable;
            Runnable runnable2;
            Throwable th;
            StringBuilder sb2;
            String action = str;
            Bundle extras = bundle;
            CustomActionCallback callback = customActionCallback;
            if (!isConnected()) {
                Throwable th2 = th;
                new StringBuilder();
                new IllegalStateException(sb2.append("Cannot send a custom action (").append(action).append(") with ").append("extras ").append(extras).append(" because the browser is not connected to the ").append("service.").toString());
                throw th2;
            }
            if (this.mServiceBinderWrapper == null) {
                int i = Log.i(MediaBrowserCompat.TAG, "The connected service doesn't support sendCustomAction.");
                if (callback != null) {
                    final CustomActionCallback customActionCallback2 = callback;
                    final String str2 = action;
                    final Bundle bundle2 = extras;
                    new Runnable(this) {
                        final /* synthetic */ MediaBrowserImplApi21 this$0;

                        {
                            this.this$0 = this$0;
                        }

                        public void run() {
                            customActionCallback2.onError(str2, bundle2, (Bundle) null);
                        }
                    };
                    boolean post = this.mHandler.post(runnable2);
                }
            }
            new CustomActionResultReceiver(action, extras, callback, this.mHandler);
            try {
                this.mServiceBinderWrapper.sendCustomAction(action, extras, receiver, this.mCallbacksMessenger);
            } catch (RemoteException e) {
                new StringBuilder();
                int i2 = Log.i(MediaBrowserCompat.TAG, sb.append("Remote error sending a custom action: action=").append(action).append(", extras=").append(extras).toString(), e);
                if (callback != null) {
                    final CustomActionCallback customActionCallback3 = callback;
                    final String str3 = action;
                    final Bundle bundle3 = extras;
                    new Runnable(this) {
                        final /* synthetic */ MediaBrowserImplApi21 this$0;

                        {
                            this.this$0 = this$0;
                        }

                        public void run() {
                            customActionCallback3.onError(str3, bundle3, (Bundle) null);
                        }
                    };
                    boolean post2 = this.mHandler.post(runnable);
                }
            }
        }

        public void onConnected() {
            ServiceBinderWrapper serviceBinderWrapper;
            Messenger messenger;
            Bundle extras = MediaBrowserCompatApi21.getExtras(this.mBrowserObj);
            if (extras != null) {
                this.mServiceVersion = extras.getInt(MediaBrowserProtocol.EXTRA_SERVICE_VERSION, 0);
                IBinder serviceBinder = BundleCompat.getBinder(extras, MediaBrowserProtocol.EXTRA_MESSENGER_BINDER);
                if (serviceBinder != null) {
                    new ServiceBinderWrapper(serviceBinder, this.mRootHints);
                    this.mServiceBinderWrapper = serviceBinderWrapper;
                    new Messenger(this.mHandler);
                    this.mCallbacksMessenger = messenger;
                    this.mHandler.setCallbacksMessenger(this.mCallbacksMessenger);
                    try {
                        this.mServiceBinderWrapper.registerCallbackMessenger(this.mContext, this.mCallbacksMessenger);
                    } catch (RemoteException e) {
                        RemoteException remoteException = e;
                        int i = Log.i(MediaBrowserCompat.TAG, "Remote error registering client messenger.");
                    }
                }
                IMediaSession sessionToken = IMediaSession.Stub.asInterface(BundleCompat.getBinder(extras, MediaBrowserProtocol.EXTRA_SESSION_BINDER));
                if (sessionToken != null) {
                    this.mMediaSessionToken = MediaSessionCompat.Token.fromToken(MediaBrowserCompatApi21.getSessionToken(this.mBrowserObj), sessionToken);
                }
            }
        }

        public void onConnectionSuspended() {
            this.mServiceBinderWrapper = null;
            this.mCallbacksMessenger = null;
            this.mMediaSessionToken = null;
            this.mHandler.setCallbacksMessenger((Messenger) null);
        }

        public void onConnectionFailed() {
        }

        public void onServiceConnected(Messenger callback, String root, MediaSessionCompat.Token session, Bundle extra) {
        }

        public void onConnectionFailed(Messenger callback) {
        }

        public void onLoadChildren(Messenger callback, String str, List list, Bundle bundle, Bundle bundle2) {
            StringBuilder sb;
            String parentId = str;
            List list2 = list;
            Bundle options = bundle;
            Bundle notifyChildrenChangedOptions = bundle2;
            if (this.mCallbacksMessenger == callback) {
                Subscription subscription = this.mSubscriptions.get(parentId);
                if (subscription != null) {
                    SubscriptionCallback subscriptionCallback = subscription.getCallback(options);
                    if (subscriptionCallback == null) {
                        return;
                    }
                    if (options == null) {
                        if (list2 == null) {
                            subscriptionCallback.onError(parentId);
                            return;
                        }
                        this.mNotifyChildrenChangedOptions = notifyChildrenChangedOptions;
                        subscriptionCallback.onChildrenLoaded(parentId, list2);
                        this.mNotifyChildrenChangedOptions = null;
                    } else if (list2 == null) {
                        subscriptionCallback.onError(parentId, options);
                    } else {
                        this.mNotifyChildrenChangedOptions = notifyChildrenChangedOptions;
                        subscriptionCallback.onChildrenLoaded(parentId, list2, options);
                        this.mNotifyChildrenChangedOptions = null;
                    }
                } else if (MediaBrowserCompat.DEBUG) {
                    new StringBuilder();
                    int d = Log.d(MediaBrowserCompat.TAG, sb.append("onLoadChildren for id that isn't subscribed id=").append(parentId).toString());
                }
            }
        }

        public Bundle getNotifyChildrenChangedOptions() {
            return this.mNotifyChildrenChangedOptions;
        }
    }

    @RequiresApi(23)
    /* renamed from: android.support.v4.media.MediaBrowserCompat$MediaBrowserImplApi23 */
    static class MediaBrowserImplApi23 extends MediaBrowserImplApi21 {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        MediaBrowserImplApi23(Context context, ComponentName serviceComponent, ConnectionCallback callback, Bundle rootHints) {
            super(context, serviceComponent, callback, rootHints);
        }

        public void getItem(@NonNull String str, @NonNull ItemCallback itemCallback) {
            String mediaId = str;
            ItemCallback cb = itemCallback;
            if (this.mServiceBinderWrapper == null) {
                MediaBrowserCompatApi23.getItem(this.mBrowserObj, mediaId, cb.mItemCallbackObj);
            } else {
                super.getItem(mediaId, cb);
            }
        }
    }

    @RequiresApi(26)
    /* renamed from: android.support.v4.media.MediaBrowserCompat$MediaBrowserImplApi26 */
    static class MediaBrowserImplApi26 extends MediaBrowserImplApi23 {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        MediaBrowserImplApi26(Context context, ComponentName serviceComponent, ConnectionCallback callback, Bundle rootHints) {
            super(context, serviceComponent, callback, rootHints);
        }

        public void subscribe(@NonNull String str, @Nullable Bundle bundle, @NonNull SubscriptionCallback subscriptionCallback) {
            String parentId = str;
            Bundle options = bundle;
            SubscriptionCallback callback = subscriptionCallback;
            if (this.mServiceBinderWrapper != null && this.mServiceVersion >= 2) {
                super.subscribe(parentId, options, callback);
            } else if (options == null) {
                MediaBrowserCompatApi21.subscribe(this.mBrowserObj, parentId, callback.mSubscriptionCallbackObj);
            } else {
                MediaBrowserCompatApi26.subscribe(this.mBrowserObj, parentId, options, callback.mSubscriptionCallbackObj);
            }
        }

        public void unsubscribe(@NonNull String str, SubscriptionCallback subscriptionCallback) {
            String parentId = str;
            SubscriptionCallback callback = subscriptionCallback;
            if (this.mServiceBinderWrapper != null && this.mServiceVersion >= 2) {
                super.unsubscribe(parentId, callback);
            } else if (callback == null) {
                MediaBrowserCompatApi21.unsubscribe(this.mBrowserObj, parentId);
            } else {
                MediaBrowserCompatApi26.unsubscribe(this.mBrowserObj, parentId, callback.mSubscriptionCallbackObj);
            }
        }
    }

    /* renamed from: android.support.v4.media.MediaBrowserCompat$Subscription */
    private static class Subscription {
        private final List<SubscriptionCallback> mCallbacks;
        private final List<Bundle> mOptionsList;

        public Subscription() {
            List<SubscriptionCallback> list;
            List<Bundle> list2;
            new ArrayList();
            this.mCallbacks = list;
            new ArrayList();
            this.mOptionsList = list2;
        }

        public boolean isEmpty() {
            return this.mCallbacks.isEmpty();
        }

        public List<Bundle> getOptionsList() {
            return this.mOptionsList;
        }

        public List<SubscriptionCallback> getCallbacks() {
            return this.mCallbacks;
        }

        public SubscriptionCallback getCallback(Bundle bundle) {
            Bundle options = bundle;
            for (int i = 0; i < this.mOptionsList.size(); i++) {
                if (MediaBrowserCompatUtils.areSameOptions(this.mOptionsList.get(i), options)) {
                    return this.mCallbacks.get(i);
                }
            }
            return null;
        }

        public void putCallback(Bundle bundle, SubscriptionCallback subscriptionCallback) {
            Bundle options = bundle;
            SubscriptionCallback callback = subscriptionCallback;
            for (int i = 0; i < this.mOptionsList.size(); i++) {
                if (MediaBrowserCompatUtils.areSameOptions(this.mOptionsList.get(i), options)) {
                    SubscriptionCallback subscriptionCallback2 = this.mCallbacks.set(i, callback);
                    return;
                }
            }
            boolean add = this.mCallbacks.add(callback);
            boolean add2 = this.mOptionsList.add(options);
        }
    }

    /* renamed from: android.support.v4.media.MediaBrowserCompat$CallbackHandler */
    private static class CallbackHandler extends Handler {
        private final WeakReference<MediaBrowserServiceCallbackImpl> mCallbackImplRef;
        private WeakReference<Messenger> mCallbacksMessengerRef;

        CallbackHandler(MediaBrowserServiceCallbackImpl callbackImpl) {
            WeakReference<MediaBrowserServiceCallbackImpl> weakReference;
            new WeakReference<>(callbackImpl);
            this.mCallbackImplRef = weakReference;
        }

        public void handleMessage(Message message) {
            StringBuilder sb;
            Message msg = message;
            if (this.mCallbacksMessengerRef != null && this.mCallbacksMessengerRef.get() != null && this.mCallbackImplRef.get() != null) {
                Bundle data = msg.getData();
                MediaSessionCompat.ensureClassLoader(data);
                MediaBrowserServiceCallbackImpl serviceCallback = (MediaBrowserServiceCallbackImpl) this.mCallbackImplRef.get();
                Messenger callbacksMessenger = (Messenger) this.mCallbacksMessengerRef.get();
                try {
                    switch (msg.what) {
                        case 1:
                            Bundle rootHints = data.getBundle(MediaBrowserProtocol.DATA_ROOT_HINTS);
                            MediaSessionCompat.ensureClassLoader(rootHints);
                            serviceCallback.onServiceConnected(callbacksMessenger, data.getString(MediaBrowserProtocol.DATA_MEDIA_ITEM_ID), (MediaSessionCompat.Token) data.getParcelable(MediaBrowserProtocol.DATA_MEDIA_SESSION_TOKEN), rootHints);
                            break;
                        case 2:
                            serviceCallback.onConnectionFailed(callbacksMessenger);
                            break;
                        case 3:
                            Bundle options = data.getBundle(MediaBrowserProtocol.DATA_OPTIONS);
                            MediaSessionCompat.ensureClassLoader(options);
                            Bundle notifyChildrenChangedOptions = data.getBundle(MediaBrowserProtocol.DATA_NOTIFY_CHILDREN_CHANGED_OPTIONS);
                            MediaSessionCompat.ensureClassLoader(notifyChildrenChangedOptions);
                            serviceCallback.onLoadChildren(callbacksMessenger, data.getString(MediaBrowserProtocol.DATA_MEDIA_ITEM_ID), data.getParcelableArrayList(MediaBrowserProtocol.DATA_MEDIA_ITEM_LIST), options, notifyChildrenChangedOptions);
                            break;
                        default:
                            new StringBuilder();
                            int w = Log.w(MediaBrowserCompat.TAG, sb.append("Unhandled message: ").append(msg).append("\n  Client version: ").append(1).append("\n  Service version: ").append(msg.arg1).toString());
                            break;
                    }
                } catch (BadParcelableException e) {
                    BadParcelableException badParcelableException = e;
                    int e2 = Log.e(MediaBrowserCompat.TAG, "Could not unparcel the data.");
                    if (msg.what == 1) {
                        serviceCallback.onConnectionFailed(callbacksMessenger);
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void setCallbacksMessenger(Messenger callbacksMessenger) {
            WeakReference<Messenger> weakReference;
            WeakReference<Messenger> weakReference2 = weakReference;
            new WeakReference<>(callbacksMessenger);
            this.mCallbacksMessengerRef = weakReference2;
        }
    }

    /* renamed from: android.support.v4.media.MediaBrowserCompat$ServiceBinderWrapper */
    private static class ServiceBinderWrapper {
        private Messenger mMessenger;
        private Bundle mRootHints;

        public ServiceBinderWrapper(IBinder target, Bundle rootHints) {
            Messenger messenger;
            new Messenger(target);
            this.mMessenger = messenger;
            this.mRootHints = rootHints;
        }

        /* access modifiers changed from: package-private */
        public void connect(Context context, Messenger callbacksMessenger) throws RemoteException {
            Bundle bundle;
            new Bundle();
            Bundle data = bundle;
            data.putString(MediaBrowserProtocol.DATA_PACKAGE_NAME, context.getPackageName());
            data.putBundle(MediaBrowserProtocol.DATA_ROOT_HINTS, this.mRootHints);
            sendRequest(1, data, callbacksMessenger);
        }

        /* access modifiers changed from: package-private */
        public void disconnect(Messenger callbacksMessenger) throws RemoteException {
            sendRequest(2, (Bundle) null, callbacksMessenger);
        }

        /* access modifiers changed from: package-private */
        public void addSubscription(String parentId, IBinder callbackToken, Bundle options, Messenger callbacksMessenger) throws RemoteException {
            Bundle bundle;
            new Bundle();
            Bundle data = bundle;
            data.putString(MediaBrowserProtocol.DATA_MEDIA_ITEM_ID, parentId);
            BundleCompat.putBinder(data, MediaBrowserProtocol.DATA_CALLBACK_TOKEN, callbackToken);
            data.putBundle(MediaBrowserProtocol.DATA_OPTIONS, options);
            sendRequest(3, data, callbacksMessenger);
        }

        /* access modifiers changed from: package-private */
        public void removeSubscription(String parentId, IBinder callbackToken, Messenger callbacksMessenger) throws RemoteException {
            Bundle bundle;
            new Bundle();
            Bundle data = bundle;
            data.putString(MediaBrowserProtocol.DATA_MEDIA_ITEM_ID, parentId);
            BundleCompat.putBinder(data, MediaBrowserProtocol.DATA_CALLBACK_TOKEN, callbackToken);
            sendRequest(4, data, callbacksMessenger);
        }

        /* access modifiers changed from: package-private */
        public void getMediaItem(String mediaId, ResultReceiver receiver, Messenger callbacksMessenger) throws RemoteException {
            Bundle bundle;
            new Bundle();
            Bundle data = bundle;
            data.putString(MediaBrowserProtocol.DATA_MEDIA_ITEM_ID, mediaId);
            data.putParcelable(MediaBrowserProtocol.DATA_RESULT_RECEIVER, receiver);
            sendRequest(5, data, callbacksMessenger);
        }

        /* access modifiers changed from: package-private */
        public void registerCallbackMessenger(Context context, Messenger callbackMessenger) throws RemoteException {
            Bundle bundle;
            new Bundle();
            Bundle data = bundle;
            data.putString(MediaBrowserProtocol.DATA_PACKAGE_NAME, context.getPackageName());
            data.putBundle(MediaBrowserProtocol.DATA_ROOT_HINTS, this.mRootHints);
            sendRequest(6, data, callbackMessenger);
        }

        /* access modifiers changed from: package-private */
        public void unregisterCallbackMessenger(Messenger callbackMessenger) throws RemoteException {
            sendRequest(7, (Bundle) null, callbackMessenger);
        }

        /* access modifiers changed from: package-private */
        public void search(String query, Bundle extras, ResultReceiver receiver, Messenger callbacksMessenger) throws RemoteException {
            Bundle bundle;
            new Bundle();
            Bundle data = bundle;
            data.putString(MediaBrowserProtocol.DATA_SEARCH_QUERY, query);
            data.putBundle(MediaBrowserProtocol.DATA_SEARCH_EXTRAS, extras);
            data.putParcelable(MediaBrowserProtocol.DATA_RESULT_RECEIVER, receiver);
            sendRequest(8, data, callbacksMessenger);
        }

        /* access modifiers changed from: package-private */
        public void sendCustomAction(String action, Bundle extras, ResultReceiver receiver, Messenger callbacksMessenger) throws RemoteException {
            Bundle bundle;
            new Bundle();
            Bundle data = bundle;
            data.putString(MediaBrowserProtocol.DATA_CUSTOM_ACTION, action);
            data.putBundle(MediaBrowserProtocol.DATA_CUSTOM_ACTION_EXTRAS, extras);
            data.putParcelable(MediaBrowserProtocol.DATA_RESULT_RECEIVER, receiver);
            sendRequest(9, data, callbacksMessenger);
        }

        private void sendRequest(int what, Bundle data, Messenger cbMessenger) throws RemoteException {
            Message msg = Message.obtain();
            msg.what = what;
            msg.arg1 = 1;
            msg.setData(data);
            msg.replyTo = cbMessenger;
            this.mMessenger.send(msg);
        }
    }

    /* renamed from: android.support.v4.media.MediaBrowserCompat$ItemReceiver */
    private static class ItemReceiver extends ResultReceiver {
        private final ItemCallback mCallback;
        private final String mMediaId;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        ItemReceiver(String mediaId, ItemCallback callback, Handler handler) {
            super(handler);
            this.mMediaId = mediaId;
            this.mCallback = callback;
        }

        /* access modifiers changed from: protected */
        public void onReceiveResult(int resultCode, Bundle bundle) {
            Bundle resultData = bundle;
            MediaSessionCompat.ensureClassLoader(resultData);
            if (resultCode != 0 || resultData == null || !resultData.containsKey(MediaBrowserServiceCompat.KEY_MEDIA_ITEM)) {
                this.mCallback.onError(this.mMediaId);
                return;
            }
            Parcelable item = resultData.getParcelable(MediaBrowserServiceCompat.KEY_MEDIA_ITEM);
            if (item == null || (item instanceof MediaItem)) {
                this.mCallback.onItemLoaded((MediaItem) item);
            } else {
                this.mCallback.onError(this.mMediaId);
            }
        }
    }

    /* renamed from: android.support.v4.media.MediaBrowserCompat$SearchResultReceiver */
    private static class SearchResultReceiver extends ResultReceiver {
        private final SearchCallback mCallback;
        private final Bundle mExtras;
        private final String mQuery;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        SearchResultReceiver(String query, Bundle extras, SearchCallback callback, Handler handler) {
            super(handler);
            this.mQuery = query;
            this.mExtras = extras;
            this.mCallback = callback;
        }

        /* access modifiers changed from: protected */
        public void onReceiveResult(int resultCode, Bundle bundle) {
            List<MediaItem> list;
            Bundle resultData = bundle;
            MediaSessionCompat.ensureClassLoader(resultData);
            if (resultCode != 0 || resultData == null || !resultData.containsKey(MediaBrowserServiceCompat.KEY_SEARCH_RESULTS)) {
                this.mCallback.onError(this.mQuery, this.mExtras);
                return;
            }
            Parcelable[] items = resultData.getParcelableArray(MediaBrowserServiceCompat.KEY_SEARCH_RESULTS);
            List<MediaItem> results = null;
            if (items != null) {
                new ArrayList<>();
                results = list;
                Parcelable[] parcelableArr = items;
                int length = parcelableArr.length;
                for (int i = 0; i < length; i++) {
                    boolean add = results.add((MediaItem) parcelableArr[i]);
                }
            }
            this.mCallback.onSearchResult(this.mQuery, this.mExtras, results);
        }
    }

    /* renamed from: android.support.v4.media.MediaBrowserCompat$CustomActionResultReceiver */
    private static class CustomActionResultReceiver extends ResultReceiver {
        private final String mAction;
        private final CustomActionCallback mCallback;
        private final Bundle mExtras;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        CustomActionResultReceiver(String action, Bundle extras, CustomActionCallback callback, Handler handler) {
            super(handler);
            this.mAction = action;
            this.mExtras = extras;
            this.mCallback = callback;
        }

        /* access modifiers changed from: protected */
        public void onReceiveResult(int i, Bundle bundle) {
            StringBuilder sb;
            int resultCode = i;
            Bundle resultData = bundle;
            if (this.mCallback != null) {
                MediaSessionCompat.ensureClassLoader(resultData);
                switch (resultCode) {
                    case -1:
                        this.mCallback.onError(this.mAction, this.mExtras, resultData);
                        return;
                    case 0:
                        this.mCallback.onResult(this.mAction, this.mExtras, resultData);
                        return;
                    case 1:
                        this.mCallback.onProgressUpdate(this.mAction, this.mExtras, resultData);
                        return;
                    default:
                        new StringBuilder();
                        int w = Log.w(MediaBrowserCompat.TAG, sb.append("Unknown result code: ").append(resultCode).append(" (extras=").append(this.mExtras).append(", resultData=").append(resultData).append(")").toString());
                        return;
                }
            }
        }
    }
}

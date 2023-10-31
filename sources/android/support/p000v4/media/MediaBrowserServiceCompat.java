package android.support.p000v4.media;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
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
import android.service.media.MediaBrowserService;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.p000v4.app.BundleCompat;
import android.support.p000v4.media.MediaBrowserCompat;
import android.support.p000v4.media.MediaBrowserServiceCompatApi21;
import android.support.p000v4.media.MediaBrowserServiceCompatApi23;
import android.support.p000v4.media.MediaBrowserServiceCompatApi26;
import android.support.p000v4.media.MediaSessionManager;
import android.support.p000v4.media.session.IMediaSession;
import android.support.p000v4.media.session.MediaSessionCompat;
import android.support.p000v4.p002os.ResultReceiver;
import android.support.p000v4.util.C1642ArrayMap;
import android.support.p000v4.util.Pair;
import android.text.TextUtils;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* renamed from: android.support.v4.media.MediaBrowserServiceCompat */
public abstract class MediaBrowserServiceCompat extends Service {
    static final boolean DEBUG = Log.isLoggable(TAG, 3);
    private static final float EPSILON = 1.0E-5f;
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String KEY_MEDIA_ITEM = "media_item";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final String KEY_SEARCH_RESULTS = "search_results";
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final int RESULT_ERROR = -1;
    static final int RESULT_FLAG_ON_LOAD_ITEM_NOT_IMPLEMENTED = 2;
    static final int RESULT_FLAG_ON_SEARCH_NOT_IMPLEMENTED = 4;
    static final int RESULT_FLAG_OPTION_NOT_HANDLED = 1;
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final int RESULT_OK = 0;
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public static final int RESULT_PROGRESS_UPDATE = 1;
    public static final String SERVICE_INTERFACE = "android.media.browse.MediaBrowserService";
    static final String TAG = "MBServiceCompat";
    final C1642ArrayMap<IBinder, ConnectionRecord> mConnections;
    ConnectionRecord mCurConnection;
    final ServiceHandler mHandler;
    private MediaBrowserServiceImpl mImpl;
    MediaSessionCompat.Token mSession;

    /* renamed from: android.support.v4.media.MediaBrowserServiceCompat$MediaBrowserServiceImpl */
    interface MediaBrowserServiceImpl {
        Bundle getBrowserRootHints();

        MediaSessionManager.RemoteUserInfo getCurrentBrowserInfo();

        void notifyChildrenChanged(MediaSessionManager.RemoteUserInfo remoteUserInfo, String str, Bundle bundle);

        void notifyChildrenChanged(String str, Bundle bundle);

        IBinder onBind(Intent intent);

        void onCreate();

        void setSessionToken(MediaSessionCompat.Token token);
    }

    /* renamed from: android.support.v4.media.MediaBrowserServiceCompat$ServiceCallbacks */
    private interface ServiceCallbacks {
        IBinder asBinder();

        void onConnect(String str, MediaSessionCompat.Token token, Bundle bundle) throws RemoteException;

        void onConnectFailed() throws RemoteException;

        void onLoadChildren(String str, List<MediaBrowserCompat.MediaItem> list, Bundle bundle, Bundle bundle2) throws RemoteException;
    }

    @Nullable
    public abstract BrowserRoot onGetRoot(@NonNull String str, int i, @Nullable Bundle bundle);

    public abstract void onLoadChildren(@NonNull String str, @NonNull Result<List<MediaBrowserCompat.MediaItem>> result);

    public MediaBrowserServiceCompat() {
        C1642ArrayMap<IBinder, ConnectionRecord> arrayMap;
        ServiceHandler serviceHandler;
        new C1642ArrayMap<>();
        this.mConnections = arrayMap;
        new ServiceHandler(this);
        this.mHandler = serviceHandler;
    }

    /* renamed from: android.support.v4.media.MediaBrowserServiceCompat$MediaBrowserServiceImplBase */
    class MediaBrowserServiceImplBase implements MediaBrowserServiceImpl {
        private Messenger mMessenger;
        final /* synthetic */ MediaBrowserServiceCompat this$0;

        MediaBrowserServiceImplBase(MediaBrowserServiceCompat this$02) {
            this.this$0 = this$02;
        }

        public void onCreate() {
            Messenger messenger;
            new Messenger(this.this$0.mHandler);
            this.mMessenger = messenger;
        }

        public IBinder onBind(Intent intent) {
            if (MediaBrowserServiceCompat.SERVICE_INTERFACE.equals(intent.getAction())) {
                return this.mMessenger.getBinder();
            }
            return null;
        }

        public void setSessionToken(MediaSessionCompat.Token token) {
            Runnable runnable;
            final MediaSessionCompat.Token token2 = token;
            new Runnable(this) {
                final /* synthetic */ MediaBrowserServiceImplBase this$1;

                {
                    this.this$1 = this$1;
                }

                public void run() {
                    StringBuilder sb;
                    Iterator<ConnectionRecord> iter = this.this$1.this$0.mConnections.values().iterator();
                    while (iter.hasNext()) {
                        ConnectionRecord connection = iter.next();
                        try {
                            connection.callbacks.onConnect(connection.root.getRootId(), token2, connection.root.getExtras());
                        } catch (RemoteException e) {
                            RemoteException remoteException = e;
                            new StringBuilder();
                            int w = Log.w(MediaBrowserServiceCompat.TAG, sb.append("Connection for ").append(connection.pkg).append(" is no longer valid.").toString());
                            iter.remove();
                        }
                    }
                }
            };
            boolean post = this.this$0.mHandler.post(runnable);
        }

        public void notifyChildrenChanged(@NonNull String parentId, Bundle options) {
            Runnable runnable;
            final String str = parentId;
            final Bundle bundle = options;
            new Runnable(this) {
                final /* synthetic */ MediaBrowserServiceImplBase this$1;

                {
                    this.this$1 = this$1;
                }

                public void run() {
                    for (IBinder binder : this.this$1.this$0.mConnections.keySet()) {
                        this.this$1.notifyChildrenChangedOnHandler(this.this$1.this$0.mConnections.get(binder), str, bundle);
                    }
                }
            };
            boolean post = this.this$0.mHandler.post(runnable);
        }

        public void notifyChildrenChanged(@NonNull MediaSessionManager.RemoteUserInfo remoteUserInfo, @NonNull String parentId, Bundle options) {
            Runnable runnable;
            final MediaSessionManager.RemoteUserInfo remoteUserInfo2 = remoteUserInfo;
            final String str = parentId;
            final Bundle bundle = options;
            new Runnable(this) {
                final /* synthetic */ MediaBrowserServiceImplBase this$1;

                {
                    this.this$1 = this$1;
                }

                public void run() {
                    for (int i = 0; i < this.this$1.this$0.mConnections.size(); i++) {
                        ConnectionRecord connection = this.this$1.this$0.mConnections.valueAt(i);
                        if (connection.browserInfo.equals(remoteUserInfo2)) {
                            this.this$1.notifyChildrenChangedOnHandler(connection, str, bundle);
                            return;
                        }
                    }
                }
            };
            boolean post = this.this$0.mHandler.post(runnable);
        }

        /* access modifiers changed from: package-private */
        public void notifyChildrenChangedOnHandler(ConnectionRecord connectionRecord, String str, Bundle bundle) {
            ConnectionRecord connection = connectionRecord;
            String parentId = str;
            Bundle options = bundle;
            List<Pair<IBinder, Bundle>> callbackList = connection.subscriptions.get(parentId);
            if (callbackList != null) {
                for (Pair<IBinder, Bundle> callback : callbackList) {
                    if (MediaBrowserCompatUtils.hasDuplicatedItems(options, (Bundle) callback.second)) {
                        this.this$0.performLoadChildren(parentId, connection, (Bundle) callback.second, options);
                    }
                }
            }
        }

        public Bundle getBrowserRootHints() {
            Bundle bundle;
            Bundle bundle2;
            Throwable th;
            if (this.this$0.mCurConnection == null) {
                Throwable th2 = th;
                new IllegalStateException("This should be called inside of onLoadChildren, onLoadItem, onSearch, or onCustomAction methods");
                throw th2;
            }
            if (this.this$0.mCurConnection.rootHints == null) {
                bundle2 = null;
            } else {
                bundle2 = bundle;
                new Bundle(this.this$0.mCurConnection.rootHints);
            }
            return bundle2;
        }

        public MediaSessionManager.RemoteUserInfo getCurrentBrowserInfo() {
            Throwable th;
            if (this.this$0.mCurConnection != null) {
                return this.this$0.mCurConnection.browserInfo;
            }
            Throwable th2 = th;
            new IllegalStateException("This should be called inside of onLoadChildren, onLoadItem, onSearch, or onCustomAction methods");
            throw th2;
        }
    }

    @RequiresApi(21)
    /* renamed from: android.support.v4.media.MediaBrowserServiceCompat$MediaBrowserServiceImplApi21 */
    class MediaBrowserServiceImplApi21 implements MediaBrowserServiceImpl, MediaBrowserServiceCompatApi21.ServiceCompatProxy {
        Messenger mMessenger;
        final List<Bundle> mRootExtrasList;
        Object mServiceObj;
        final /* synthetic */ MediaBrowserServiceCompat this$0;

        MediaBrowserServiceImplApi21(MediaBrowserServiceCompat this$02) {
            List<Bundle> list;
            this.this$0 = this$02;
            new ArrayList();
            this.mRootExtrasList = list;
        }

        public void onCreate() {
            this.mServiceObj = MediaBrowserServiceCompatApi21.createService(this.this$0, this);
            MediaBrowserServiceCompatApi21.onCreate(this.mServiceObj);
        }

        public IBinder onBind(Intent intent) {
            return MediaBrowserServiceCompatApi21.onBind(this.mServiceObj, intent);
        }

        public void setSessionToken(MediaSessionCompat.Token token) {
            Runnable runnable;
            final MediaSessionCompat.Token token2 = token;
            new Runnable(this) {
                final /* synthetic */ MediaBrowserServiceImplApi21 this$1;

                {
                    this.this$1 = this$1;
                }

                public void run() {
                    if (!this.this$1.mRootExtrasList.isEmpty()) {
                        IMediaSession extraBinder = token2.getExtraBinder();
                        if (extraBinder != null) {
                            for (Bundle rootExtras : this.this$1.mRootExtrasList) {
                                BundleCompat.putBinder(rootExtras, MediaBrowserProtocol.EXTRA_SESSION_BINDER, extraBinder.asBinder());
                            }
                        }
                        this.this$1.mRootExtrasList.clear();
                    }
                    MediaBrowserServiceCompatApi21.setSessionToken(this.this$1.mServiceObj, token2.getToken());
                }
            };
            this.this$0.mHandler.postOrRun(runnable);
        }

        public void notifyChildrenChanged(String str, Bundle bundle) {
            String parentId = str;
            Bundle options = bundle;
            notifyChildrenChangedForFramework(parentId, options);
            notifyChildrenChangedForCompat(parentId, options);
        }

        public void notifyChildrenChanged(MediaSessionManager.RemoteUserInfo remoteUserInfo, String parentId, Bundle options) {
            notifyChildrenChangedForCompat(remoteUserInfo, parentId, options);
        }

        public MediaBrowserServiceCompatApi21.BrowserRoot onGetRoot(String str, int i, Bundle bundle) {
            ConnectionRecord connectionRecord;
            MediaBrowserServiceCompatApi21.BrowserRoot browserRoot;
            Messenger messenger;
            Bundle bundle2;
            IBinder asBinder;
            String clientPackageName = str;
            int clientUid = i;
            Bundle rootHints = bundle;
            Bundle rootExtras = null;
            if (!(rootHints == null || rootHints.getInt(MediaBrowserProtocol.EXTRA_CLIENT_VERSION, 0) == 0)) {
                rootHints.remove(MediaBrowserProtocol.EXTRA_CLIENT_VERSION);
                new Messenger(this.this$0.mHandler);
                this.mMessenger = messenger;
                new Bundle();
                rootExtras = bundle2;
                rootExtras.putInt(MediaBrowserProtocol.EXTRA_SERVICE_VERSION, 2);
                BundleCompat.putBinder(rootExtras, MediaBrowserProtocol.EXTRA_MESSENGER_BINDER, this.mMessenger.getBinder());
                if (this.this$0.mSession != null) {
                    IMediaSession extraBinder = this.this$0.mSession.getExtraBinder();
                    Bundle bundle3 = rootExtras;
                    if (extraBinder == null) {
                        asBinder = null;
                    } else {
                        asBinder = extraBinder.asBinder();
                    }
                    BundleCompat.putBinder(bundle3, MediaBrowserProtocol.EXTRA_SESSION_BINDER, asBinder);
                } else {
                    boolean add = this.mRootExtrasList.add(rootExtras);
                }
            }
            MediaBrowserServiceCompat mediaBrowserServiceCompat = this.this$0;
            new ConnectionRecord(this.this$0, clientPackageName, -1, clientUid, rootHints, (ServiceCallbacks) null);
            mediaBrowserServiceCompat.mCurConnection = connectionRecord;
            BrowserRoot root = this.this$0.onGetRoot(clientPackageName, clientUid, rootHints);
            this.this$0.mCurConnection = null;
            if (root == null) {
                return null;
            }
            if (rootExtras == null) {
                rootExtras = root.getExtras();
            } else if (root.getExtras() != null) {
                rootExtras.putAll(root.getExtras());
            }
            new MediaBrowserServiceCompatApi21.BrowserRoot(root.getRootId(), rootExtras);
            return browserRoot;
        }

        public void onLoadChildren(String str, MediaBrowserServiceCompatApi21.ResultWrapper<List<Parcel>> resultWrapper) {
            Result result;
            String parentId = str;
            final MediaBrowserServiceCompatApi21.ResultWrapper<List<Parcel>> resultWrapper2 = resultWrapper;
            new Result<List<MediaBrowserCompat.MediaItem>>(this, parentId) {
                final /* synthetic */ MediaBrowserServiceImplApi21 this$1;

                {
                    this.this$1 = this$1;
                }

                /* access modifiers changed from: package-private */
                public void onResultSent(List<MediaBrowserCompat.MediaItem> list) {
                    List<Parcel> list2;
                    List<MediaBrowserCompat.MediaItem> list3 = list;
                    List<Parcel> parcelList = null;
                    if (list3 != null) {
                        new ArrayList<>();
                        parcelList = list2;
                        for (MediaBrowserCompat.MediaItem item : list3) {
                            Parcel parcel = Parcel.obtain();
                            item.writeToParcel(parcel, 0);
                            boolean add = parcelList.add(parcel);
                        }
                    }
                    resultWrapper2.sendResult(parcelList);
                }

                public void detach() {
                    resultWrapper2.detach();
                }
            };
            this.this$0.onLoadChildren(parentId, result);
        }

        /* access modifiers changed from: package-private */
        public void notifyChildrenChangedForFramework(String parentId, Bundle bundle) {
            Bundle bundle2 = bundle;
            MediaBrowserServiceCompatApi21.notifyChildrenChanged(this.mServiceObj, parentId);
        }

        /* access modifiers changed from: package-private */
        public void notifyChildrenChangedForCompat(String parentId, Bundle options) {
            Runnable runnable;
            final String str = parentId;
            final Bundle bundle = options;
            new Runnable(this) {
                final /* synthetic */ MediaBrowserServiceImplApi21 this$1;

                {
                    this.this$1 = this$1;
                }

                public void run() {
                    for (IBinder binder : this.this$1.this$0.mConnections.keySet()) {
                        this.this$1.notifyChildrenChangedForCompatOnHandler(this.this$1.this$0.mConnections.get(binder), str, bundle);
                    }
                }
            };
            boolean post = this.this$0.mHandler.post(runnable);
        }

        /* access modifiers changed from: package-private */
        public void notifyChildrenChangedForCompat(MediaSessionManager.RemoteUserInfo remoteUserInfo, String parentId, Bundle options) {
            Runnable runnable;
            final MediaSessionManager.RemoteUserInfo remoteUserInfo2 = remoteUserInfo;
            final String str = parentId;
            final Bundle bundle = options;
            new Runnable(this) {
                final /* synthetic */ MediaBrowserServiceImplApi21 this$1;

                {
                    this.this$1 = this$1;
                }

                public void run() {
                    for (int i = 0; i < this.this$1.this$0.mConnections.size(); i++) {
                        ConnectionRecord connection = this.this$1.this$0.mConnections.valueAt(i);
                        if (connection.browserInfo.equals(remoteUserInfo2)) {
                            this.this$1.notifyChildrenChangedForCompatOnHandler(connection, str, bundle);
                        }
                    }
                }
            };
            boolean post = this.this$0.mHandler.post(runnable);
        }

        /* access modifiers changed from: package-private */
        public void notifyChildrenChangedForCompatOnHandler(ConnectionRecord connectionRecord, String str, Bundle bundle) {
            ConnectionRecord connection = connectionRecord;
            String parentId = str;
            Bundle options = bundle;
            List<Pair<IBinder, Bundle>> callbackList = connection.subscriptions.get(parentId);
            if (callbackList != null) {
                for (Pair<IBinder, Bundle> callback : callbackList) {
                    if (MediaBrowserCompatUtils.hasDuplicatedItems(options, (Bundle) callback.second)) {
                        this.this$0.performLoadChildren(parentId, connection, (Bundle) callback.second, options);
                    }
                }
            }
        }

        public Bundle getBrowserRootHints() {
            Bundle bundle;
            Bundle bundle2;
            Throwable th;
            if (this.mMessenger == null) {
                return null;
            }
            if (this.this$0.mCurConnection == null) {
                Throwable th2 = th;
                new IllegalStateException("This should be called inside of onGetRoot, onLoadChildren, onLoadItem, onSearch, or onCustomAction methods");
                throw th2;
            }
            if (this.this$0.mCurConnection.rootHints == null) {
                bundle2 = null;
            } else {
                bundle2 = bundle;
                new Bundle(this.this$0.mCurConnection.rootHints);
            }
            return bundle2;
        }

        public MediaSessionManager.RemoteUserInfo getCurrentBrowserInfo() {
            Throwable th;
            if (this.this$0.mCurConnection != null) {
                return this.this$0.mCurConnection.browserInfo;
            }
            Throwable th2 = th;
            new IllegalStateException("This should be called inside of onGetRoot, onLoadChildren, onLoadItem, onSearch, or onCustomAction methods");
            throw th2;
        }
    }

    @RequiresApi(23)
    /* renamed from: android.support.v4.media.MediaBrowserServiceCompat$MediaBrowserServiceImplApi23 */
    class MediaBrowserServiceImplApi23 extends MediaBrowserServiceImplApi21 implements MediaBrowserServiceCompatApi23.ServiceCompatProxy {
        final /* synthetic */ MediaBrowserServiceCompat this$0;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        MediaBrowserServiceImplApi23(android.support.p000v4.media.MediaBrowserServiceCompat r5) {
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
            throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.media.MediaBrowserServiceCompat.MediaBrowserServiceImplApi23.<init>(android.support.v4.media.MediaBrowserServiceCompat):void");
        }

        public void onCreate() {
            this.mServiceObj = MediaBrowserServiceCompatApi23.createService(this.this$0, this);
            MediaBrowserServiceCompatApi21.onCreate(this.mServiceObj);
        }

        public void onLoadItem(String str, MediaBrowserServiceCompatApi21.ResultWrapper<Parcel> resultWrapper) {
            Result result;
            String itemId = str;
            final MediaBrowserServiceCompatApi21.ResultWrapper<Parcel> resultWrapper2 = resultWrapper;
            new Result<MediaBrowserCompat.MediaItem>(this, itemId) {
                final /* synthetic */ MediaBrowserServiceImplApi23 this$1;

                {
                    this.this$1 = this$1;
                }

                /* access modifiers changed from: package-private */
                public void onResultSent(MediaBrowserCompat.MediaItem mediaItem) {
                    MediaBrowserCompat.MediaItem item = mediaItem;
                    if (item == null) {
                        resultWrapper2.sendResult(null);
                        return;
                    }
                    Parcel parcelItem = Parcel.obtain();
                    item.writeToParcel(parcelItem, 0);
                    resultWrapper2.sendResult(parcelItem);
                }

                public void detach() {
                    resultWrapper2.detach();
                }
            };
            this.this$0.onLoadItem(itemId, result);
        }
    }

    @RequiresApi(26)
    /* renamed from: android.support.v4.media.MediaBrowserServiceCompat$MediaBrowserServiceImplApi26 */
    class MediaBrowserServiceImplApi26 extends MediaBrowserServiceImplApi23 implements MediaBrowserServiceCompatApi26.ServiceCompatProxy {
        final /* synthetic */ MediaBrowserServiceCompat this$0;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        MediaBrowserServiceImplApi26(android.support.p000v4.media.MediaBrowserServiceCompat r5) {
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
            throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.media.MediaBrowserServiceCompat.MediaBrowserServiceImplApi26.<init>(android.support.v4.media.MediaBrowserServiceCompat):void");
        }

        public void onCreate() {
            this.mServiceObj = MediaBrowserServiceCompatApi26.createService(this.this$0, this);
            MediaBrowserServiceCompatApi21.onCreate(this.mServiceObj);
        }

        public void onLoadChildren(String str, MediaBrowserServiceCompatApi26.ResultWrapper resultWrapper, Bundle options) {
            Result result;
            String parentId = str;
            final MediaBrowserServiceCompatApi26.ResultWrapper resultWrapper2 = resultWrapper;
            new Result<List<MediaBrowserCompat.MediaItem>>(this, parentId) {
                final /* synthetic */ MediaBrowserServiceImplApi26 this$1;

                {
                    this.this$1 = this$1;
                }

                /* access modifiers changed from: package-private */
                public void onResultSent(List<MediaBrowserCompat.MediaItem> list) {
                    List<Parcel> list2;
                    List<MediaBrowserCompat.MediaItem> list3 = list;
                    List<Parcel> parcelList = null;
                    if (list3 != null) {
                        new ArrayList<>();
                        parcelList = list2;
                        for (MediaBrowserCompat.MediaItem item : list3) {
                            Parcel parcel = Parcel.obtain();
                            item.writeToParcel(parcel, 0);
                            boolean add = parcelList.add(parcel);
                        }
                    }
                    resultWrapper2.sendResult(parcelList, getFlags());
                }

                public void detach() {
                    resultWrapper2.detach();
                }
            };
            this.this$0.onLoadChildren(parentId, result, options);
        }

        public Bundle getBrowserRootHints() {
            Bundle bundle;
            Bundle bundle2;
            if (this.this$0.mCurConnection == null) {
                return MediaBrowserServiceCompatApi26.getBrowserRootHints(this.mServiceObj);
            }
            if (this.this$0.mCurConnection.rootHints == null) {
                bundle2 = null;
            } else {
                bundle2 = bundle;
                new Bundle(this.this$0.mCurConnection.rootHints);
            }
            return bundle2;
        }

        /* access modifiers changed from: package-private */
        public void notifyChildrenChangedForFramework(String str, Bundle bundle) {
            String parentId = str;
            Bundle options = bundle;
            if (options != null) {
                MediaBrowserServiceCompatApi26.notifyChildrenChanged(this.mServiceObj, parentId, options);
            } else {
                super.notifyChildrenChangedForFramework(parentId, options);
            }
        }
    }

    @RequiresApi(28)
    /* renamed from: android.support.v4.media.MediaBrowserServiceCompat$MediaBrowserServiceImplApi28 */
    class MediaBrowserServiceImplApi28 extends MediaBrowserServiceImplApi26 {
        final /* synthetic */ MediaBrowserServiceCompat this$0;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        MediaBrowserServiceImplApi28(android.support.p000v4.media.MediaBrowserServiceCompat r5) {
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
            throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.media.MediaBrowserServiceCompat.MediaBrowserServiceImplApi28.<init>(android.support.v4.media.MediaBrowserServiceCompat):void");
        }

        public MediaSessionManager.RemoteUserInfo getCurrentBrowserInfo() {
            MediaSessionManager.RemoteUserInfo remoteUserInfo;
            if (this.this$0.mCurConnection != null) {
                return this.this$0.mCurConnection.browserInfo;
            }
            new MediaSessionManager.RemoteUserInfo(((MediaBrowserService) this.mServiceObj).getCurrentBrowserInfo());
            return remoteUserInfo;
        }
    }

    /* renamed from: android.support.v4.media.MediaBrowserServiceCompat$ServiceHandler */
    private final class ServiceHandler extends Handler {
        private final ServiceBinderImpl mServiceBinderImpl;
        final /* synthetic */ MediaBrowserServiceCompat this$0;

        ServiceHandler(MediaBrowserServiceCompat mediaBrowserServiceCompat) {
            ServiceBinderImpl serviceBinderImpl;
            this.this$0 = mediaBrowserServiceCompat;
            new ServiceBinderImpl(this.this$0);
            this.mServiceBinderImpl = serviceBinderImpl;
        }

        public void handleMessage(Message message) {
            ServiceCallbacks serviceCallbacks;
            ServiceCallbacks serviceCallbacks2;
            ServiceCallbacks serviceCallbacks3;
            ServiceCallbacks serviceCallbacks4;
            ServiceCallbacks serviceCallbacks5;
            ServiceCallbacks serviceCallbacks6;
            ServiceCallbacks serviceCallbacks7;
            ServiceCallbacks serviceCallbacks8;
            ServiceCallbacks serviceCallbacks9;
            StringBuilder sb;
            Message msg = message;
            Bundle data = msg.getData();
            switch (msg.what) {
                case 1:
                    Bundle rootHints = data.getBundle(MediaBrowserProtocol.DATA_ROOT_HINTS);
                    MediaSessionCompat.ensureClassLoader(rootHints);
                    new ServiceCallbacksCompat(msg.replyTo);
                    this.mServiceBinderImpl.connect(data.getString(MediaBrowserProtocol.DATA_PACKAGE_NAME), data.getInt(MediaBrowserProtocol.DATA_CALLING_PID), data.getInt(MediaBrowserProtocol.DATA_CALLING_UID), rootHints, serviceCallbacks9);
                    return;
                case 2:
                    new ServiceCallbacksCompat(msg.replyTo);
                    this.mServiceBinderImpl.disconnect(serviceCallbacks8);
                    return;
                case 3:
                    Bundle options = data.getBundle(MediaBrowserProtocol.DATA_OPTIONS);
                    MediaSessionCompat.ensureClassLoader(options);
                    new ServiceCallbacksCompat(msg.replyTo);
                    this.mServiceBinderImpl.addSubscription(data.getString(MediaBrowserProtocol.DATA_MEDIA_ITEM_ID), BundleCompat.getBinder(data, MediaBrowserProtocol.DATA_CALLBACK_TOKEN), options, serviceCallbacks7);
                    return;
                case 4:
                    new ServiceCallbacksCompat(msg.replyTo);
                    this.mServiceBinderImpl.removeSubscription(data.getString(MediaBrowserProtocol.DATA_MEDIA_ITEM_ID), BundleCompat.getBinder(data, MediaBrowserProtocol.DATA_CALLBACK_TOKEN), serviceCallbacks6);
                    return;
                case 5:
                    new ServiceCallbacksCompat(msg.replyTo);
                    this.mServiceBinderImpl.getMediaItem(data.getString(MediaBrowserProtocol.DATA_MEDIA_ITEM_ID), (ResultReceiver) data.getParcelable(MediaBrowserProtocol.DATA_RESULT_RECEIVER), serviceCallbacks5);
                    return;
                case 6:
                    Bundle rootHints2 = data.getBundle(MediaBrowserProtocol.DATA_ROOT_HINTS);
                    MediaSessionCompat.ensureClassLoader(rootHints2);
                    new ServiceCallbacksCompat(msg.replyTo);
                    this.mServiceBinderImpl.registerCallbacks(serviceCallbacks4, data.getString(MediaBrowserProtocol.DATA_PACKAGE_NAME), data.getInt(MediaBrowserProtocol.DATA_CALLING_PID), data.getInt(MediaBrowserProtocol.DATA_CALLING_UID), rootHints2);
                    return;
                case 7:
                    new ServiceCallbacksCompat(msg.replyTo);
                    this.mServiceBinderImpl.unregisterCallbacks(serviceCallbacks3);
                    return;
                case 8:
                    Bundle searchExtras = data.getBundle(MediaBrowserProtocol.DATA_SEARCH_EXTRAS);
                    MediaSessionCompat.ensureClassLoader(searchExtras);
                    new ServiceCallbacksCompat(msg.replyTo);
                    this.mServiceBinderImpl.search(data.getString(MediaBrowserProtocol.DATA_SEARCH_QUERY), searchExtras, (ResultReceiver) data.getParcelable(MediaBrowserProtocol.DATA_RESULT_RECEIVER), serviceCallbacks2);
                    return;
                case 9:
                    Bundle customActionExtras = data.getBundle(MediaBrowserProtocol.DATA_CUSTOM_ACTION_EXTRAS);
                    MediaSessionCompat.ensureClassLoader(customActionExtras);
                    new ServiceCallbacksCompat(msg.replyTo);
                    this.mServiceBinderImpl.sendCustomAction(data.getString(MediaBrowserProtocol.DATA_CUSTOM_ACTION), customActionExtras, (ResultReceiver) data.getParcelable(MediaBrowserProtocol.DATA_RESULT_RECEIVER), serviceCallbacks);
                    return;
                default:
                    new StringBuilder();
                    int w = Log.w(MediaBrowserServiceCompat.TAG, sb.append("Unhandled message: ").append(msg).append("\n  Service version: ").append(2).append("\n  Client version: ").append(msg.arg1).toString());
                    return;
            }
        }

        public boolean sendMessageAtTime(Message message, long uptimeMillis) {
            Message msg = message;
            Bundle data = msg.getData();
            data.setClassLoader(MediaBrowserCompat.class.getClassLoader());
            data.putInt(MediaBrowserProtocol.DATA_CALLING_UID, Binder.getCallingUid());
            data.putInt(MediaBrowserProtocol.DATA_CALLING_PID, Binder.getCallingPid());
            return super.sendMessageAtTime(msg, uptimeMillis);
        }

        public void postOrRun(Runnable runnable) {
            Runnable r = runnable;
            if (Thread.currentThread() == getLooper().getThread()) {
                r.run();
            } else {
                boolean post = post(r);
            }
        }
    }

    /* renamed from: android.support.v4.media.MediaBrowserServiceCompat$ConnectionRecord */
    private class ConnectionRecord implements IBinder.DeathRecipient {
        public final MediaSessionManager.RemoteUserInfo browserInfo;
        public final ServiceCallbacks callbacks;
        public final int pid;
        public final String pkg;
        public BrowserRoot root;
        public final Bundle rootHints;
        public final HashMap<String, List<Pair<IBinder, Bundle>>> subscriptions;
        final /* synthetic */ MediaBrowserServiceCompat this$0;
        public final int uid;

        ConnectionRecord(MediaBrowserServiceCompat mediaBrowserServiceCompat, String str, int i, int i2, Bundle rootHints2, ServiceCallbacks callback) {
            HashMap<String, List<Pair<IBinder, Bundle>>> hashMap;
            MediaSessionManager.RemoteUserInfo remoteUserInfo;
            String pkg2 = str;
            int pid2 = i;
            int uid2 = i2;
            this.this$0 = mediaBrowserServiceCompat;
            new HashMap<>();
            this.subscriptions = hashMap;
            this.pkg = pkg2;
            this.pid = pid2;
            this.uid = uid2;
            new MediaSessionManager.RemoteUserInfo(pkg2, pid2, uid2);
            this.browserInfo = remoteUserInfo;
            this.rootHints = rootHints2;
            this.callbacks = callback;
        }

        public void binderDied() {
            Runnable runnable;
            new Runnable(this) {
                final /* synthetic */ ConnectionRecord this$1;

                {
                    this.this$1 = this$1;
                }

                public void run() {
                    ConnectionRecord remove = this.this$1.this$0.mConnections.remove(this.this$1.callbacks.asBinder());
                }
            };
            boolean post = this.this$0.mHandler.post(runnable);
        }
    }

    /* renamed from: android.support.v4.media.MediaBrowserServiceCompat$Result */
    public static class Result<T> {
        private final Object mDebug;
        private boolean mDetachCalled;
        private int mFlags;
        private boolean mSendErrorCalled;
        private boolean mSendProgressUpdateCalled;
        private boolean mSendResultCalled;

        Result(Object debug) {
            this.mDebug = debug;
        }

        public void sendResult(T t) {
            Throwable th;
            StringBuilder sb;
            T result = t;
            if (this.mSendResultCalled || this.mSendErrorCalled) {
                Throwable th2 = th;
                new StringBuilder();
                new IllegalStateException(sb.append("sendResult() called when either sendResult() or sendError() had already been called for: ").append(this.mDebug).toString());
                throw th2;
            }
            this.mSendResultCalled = true;
            onResultSent(result);
        }

        public void sendProgressUpdate(Bundle bundle) {
            Throwable th;
            StringBuilder sb;
            Bundle extras = bundle;
            if (this.mSendResultCalled || this.mSendErrorCalled) {
                Throwable th2 = th;
                new StringBuilder();
                new IllegalStateException(sb.append("sendProgressUpdate() called when either sendResult() or sendError() had already been called for: ").append(this.mDebug).toString());
                throw th2;
            }
            checkExtraFields(extras);
            this.mSendProgressUpdateCalled = true;
            onProgressUpdateSent(extras);
        }

        public void sendError(Bundle bundle) {
            Throwable th;
            StringBuilder sb;
            Bundle extras = bundle;
            if (this.mSendResultCalled || this.mSendErrorCalled) {
                Throwable th2 = th;
                new StringBuilder();
                new IllegalStateException(sb.append("sendError() called when either sendResult() or sendError() had already been called for: ").append(this.mDebug).toString());
                throw th2;
            }
            this.mSendErrorCalled = true;
            onErrorSent(extras);
        }

        public void detach() {
            Throwable th;
            StringBuilder sb;
            Throwable th2;
            StringBuilder sb2;
            Throwable th3;
            StringBuilder sb3;
            if (this.mDetachCalled) {
                Throwable th4 = th3;
                new StringBuilder();
                new IllegalStateException(sb3.append("detach() called when detach() had already been called for: ").append(this.mDebug).toString());
                throw th4;
            } else if (this.mSendResultCalled) {
                Throwable th5 = th2;
                new StringBuilder();
                new IllegalStateException(sb2.append("detach() called when sendResult() had already been called for: ").append(this.mDebug).toString());
                throw th5;
            } else if (this.mSendErrorCalled) {
                Throwable th6 = th;
                new StringBuilder();
                new IllegalStateException(sb.append("detach() called when sendError() had already been called for: ").append(this.mDebug).toString());
                throw th6;
            } else {
                this.mDetachCalled = true;
            }
        }

        /* access modifiers changed from: package-private */
        public boolean isDone() {
            return this.mDetachCalled || this.mSendResultCalled || this.mSendErrorCalled;
        }

        /* access modifiers changed from: package-private */
        public void setFlags(int flags) {
            int i = flags;
            this.mFlags = i;
        }

        /* access modifiers changed from: package-private */
        public int getFlags() {
            return this.mFlags;
        }

        /* access modifiers changed from: package-private */
        public void onResultSent(T t) {
        }

        /* access modifiers changed from: package-private */
        public void onProgressUpdateSent(Bundle bundle) {
            Throwable th;
            StringBuilder sb;
            Bundle bundle2 = bundle;
            Throwable th2 = th;
            new StringBuilder();
            new UnsupportedOperationException(sb.append("It is not supported to send an interim update for ").append(this.mDebug).toString());
            throw th2;
        }

        /* access modifiers changed from: package-private */
        public void onErrorSent(Bundle bundle) {
            Throwable th;
            StringBuilder sb;
            Bundle bundle2 = bundle;
            Throwable th2 = th;
            new StringBuilder();
            new UnsupportedOperationException(sb.append("It is not supported to send an error for ").append(this.mDebug).toString());
            throw th2;
        }

        private void checkExtraFields(Bundle bundle) {
            Throwable th;
            Bundle extras = bundle;
            if (extras != null && extras.containsKey(MediaBrowserCompat.EXTRA_DOWNLOAD_PROGRESS)) {
                float value = extras.getFloat(MediaBrowserCompat.EXTRA_DOWNLOAD_PROGRESS);
                if (value < -1.0E-5f || value > 1.00001f) {
                    Throwable th2 = th;
                    new IllegalArgumentException("The value of the EXTRA_DOWNLOAD_PROGRESS field must be a float number within [0.0, 1.0].");
                    throw th2;
                }
            }
        }
    }

    /* renamed from: android.support.v4.media.MediaBrowserServiceCompat$ServiceBinderImpl */
    private class ServiceBinderImpl {
        final /* synthetic */ MediaBrowserServiceCompat this$0;

        ServiceBinderImpl(MediaBrowserServiceCompat mediaBrowserServiceCompat) {
            this.this$0 = mediaBrowserServiceCompat;
        }

        public void connect(String str, int i, int i2, Bundle bundle, ServiceCallbacks serviceCallbacks) {
            Runnable runnable;
            Throwable th;
            StringBuilder sb;
            String pkg = str;
            int pid = i;
            int uid = i2;
            Bundle rootHints = bundle;
            ServiceCallbacks callbacks = serviceCallbacks;
            if (!this.this$0.isValidPackage(pkg, uid)) {
                Throwable th2 = th;
                new StringBuilder();
                new IllegalArgumentException(sb.append("Package/uid mismatch: uid=").append(uid).append(" package=").append(pkg).toString());
                throw th2;
            }
            final ServiceCallbacks serviceCallbacks2 = callbacks;
            final String str2 = pkg;
            final int i3 = pid;
            final int i4 = uid;
            final Bundle bundle2 = rootHints;
            new Runnable(this) {
                final /* synthetic */ ServiceBinderImpl this$1;

                {
                    this.this$1 = this$1;
                }

                public void run() {
                    ConnectionRecord connectionRecord;
                    StringBuilder sb;
                    StringBuilder sb2;
                    StringBuilder sb3;
                    IBinder b = serviceCallbacks2.asBinder();
                    ConnectionRecord remove = this.this$1.this$0.mConnections.remove(b);
                    new ConnectionRecord(this.this$1.this$0, str2, i3, i4, bundle2, serviceCallbacks2);
                    ConnectionRecord connection = connectionRecord;
                    this.this$1.this$0.mCurConnection = connection;
                    connection.root = this.this$1.this$0.onGetRoot(str2, i4, bundle2);
                    this.this$1.this$0.mCurConnection = null;
                    if (connection.root == null) {
                        new StringBuilder();
                        int i = Log.i(MediaBrowserServiceCompat.TAG, sb2.append("No root for client ").append(str2).append(" from service ").append(getClass().getName()).toString());
                        try {
                            serviceCallbacks2.onConnectFailed();
                        } catch (RemoteException e) {
                            RemoteException remoteException = e;
                            new StringBuilder();
                            int w = Log.w(MediaBrowserServiceCompat.TAG, sb3.append("Calling onConnectFailed() failed. Ignoring. pkg=").append(str2).toString());
                        }
                    } else {
                        try {
                            ConnectionRecord put = this.this$1.this$0.mConnections.put(b, connection);
                            b.linkToDeath(connection, 0);
                            if (this.this$1.this$0.mSession != null) {
                                serviceCallbacks2.onConnect(connection.root.getRootId(), this.this$1.this$0.mSession, connection.root.getExtras());
                            }
                        } catch (RemoteException e2) {
                            RemoteException remoteException2 = e2;
                            new StringBuilder();
                            int w2 = Log.w(MediaBrowserServiceCompat.TAG, sb.append("Calling onConnect() failed. Dropping client. pkg=").append(str2).toString());
                            ConnectionRecord remove2 = this.this$1.this$0.mConnections.remove(b);
                        }
                    }
                }
            };
            this.this$0.mHandler.postOrRun(runnable);
        }

        public void disconnect(ServiceCallbacks callbacks) {
            Runnable runnable;
            final ServiceCallbacks serviceCallbacks = callbacks;
            new Runnable(this) {
                final /* synthetic */ ServiceBinderImpl this$1;

                {
                    this.this$1 = this$1;
                }

                public void run() {
                    ConnectionRecord old = this.this$1.this$0.mConnections.remove(serviceCallbacks.asBinder());
                    if (old != null) {
                        boolean unlinkToDeath = old.callbacks.asBinder().unlinkToDeath(old, 0);
                    }
                }
            };
            this.this$0.mHandler.postOrRun(runnable);
        }

        public void addSubscription(String id, IBinder token, Bundle options, ServiceCallbacks callbacks) {
            Runnable runnable;
            final ServiceCallbacks serviceCallbacks = callbacks;
            final String str = id;
            final IBinder iBinder = token;
            final Bundle bundle = options;
            new Runnable(this) {
                final /* synthetic */ ServiceBinderImpl this$1;

                {
                    this.this$1 = this$1;
                }

                public void run() {
                    StringBuilder sb;
                    ConnectionRecord connection = this.this$1.this$0.mConnections.get(serviceCallbacks.asBinder());
                    if (connection == null) {
                        new StringBuilder();
                        int w = Log.w(MediaBrowserServiceCompat.TAG, sb.append("addSubscription for callback that isn't registered id=").append(str).toString());
                        return;
                    }
                    this.this$1.this$0.addSubscription(str, connection, iBinder, bundle);
                }
            };
            this.this$0.mHandler.postOrRun(runnable);
        }

        public void removeSubscription(String id, IBinder token, ServiceCallbacks callbacks) {
            Runnable runnable;
            final ServiceCallbacks serviceCallbacks = callbacks;
            final String str = id;
            final IBinder iBinder = token;
            new Runnable(this) {
                final /* synthetic */ ServiceBinderImpl this$1;

                {
                    this.this$1 = this$1;
                }

                public void run() {
                    StringBuilder sb;
                    StringBuilder sb2;
                    ConnectionRecord connection = this.this$1.this$0.mConnections.get(serviceCallbacks.asBinder());
                    if (connection == null) {
                        new StringBuilder();
                        int w = Log.w(MediaBrowserServiceCompat.TAG, sb2.append("removeSubscription for callback that isn't registered id=").append(str).toString());
                    } else if (!this.this$1.this$0.removeSubscription(str, connection, iBinder)) {
                        new StringBuilder();
                        int w2 = Log.w(MediaBrowserServiceCompat.TAG, sb.append("removeSubscription called for ").append(str).append(" which is not subscribed").toString());
                    }
                }
            };
            this.this$0.mHandler.postOrRun(runnable);
        }

        public void getMediaItem(String str, ResultReceiver resultReceiver, ServiceCallbacks serviceCallbacks) {
            Runnable runnable;
            String mediaId = str;
            ResultReceiver receiver = resultReceiver;
            ServiceCallbacks callbacks = serviceCallbacks;
            if (!TextUtils.isEmpty(mediaId) && receiver != null) {
                final ServiceCallbacks serviceCallbacks2 = callbacks;
                final String str2 = mediaId;
                final ResultReceiver resultReceiver2 = receiver;
                new Runnable(this) {
                    final /* synthetic */ ServiceBinderImpl this$1;

                    {
                        this.this$1 = this$1;
                    }

                    public void run() {
                        StringBuilder sb;
                        ConnectionRecord connection = this.this$1.this$0.mConnections.get(serviceCallbacks2.asBinder());
                        if (connection == null) {
                            new StringBuilder();
                            int w = Log.w(MediaBrowserServiceCompat.TAG, sb.append("getMediaItem for callback that isn't registered id=").append(str2).toString());
                            return;
                        }
                        this.this$1.this$0.performLoadItem(str2, connection, resultReceiver2);
                    }
                };
                this.this$0.mHandler.postOrRun(runnable);
            }
        }

        public void registerCallbacks(ServiceCallbacks callbacks, String pkg, int pid, int uid, Bundle rootHints) {
            Runnable runnable;
            final ServiceCallbacks serviceCallbacks = callbacks;
            final String str = pkg;
            final int i = pid;
            final int i2 = uid;
            final Bundle bundle = rootHints;
            new Runnable(this) {
                final /* synthetic */ ServiceBinderImpl this$1;

                {
                    this.this$1 = this$1;
                }

                public void run() {
                    ConnectionRecord connectionRecord;
                    IBinder b = serviceCallbacks.asBinder();
                    ConnectionRecord remove = this.this$1.this$0.mConnections.remove(b);
                    new ConnectionRecord(this.this$1.this$0, str, i, i2, bundle, serviceCallbacks);
                    ConnectionRecord connection = connectionRecord;
                    ConnectionRecord put = this.this$1.this$0.mConnections.put(b, connection);
                    try {
                        b.linkToDeath(connection, 0);
                    } catch (RemoteException e) {
                        RemoteException remoteException = e;
                        int w = Log.w(MediaBrowserServiceCompat.TAG, "IBinder is already dead.");
                    }
                }
            };
            this.this$0.mHandler.postOrRun(runnable);
        }

        public void unregisterCallbacks(ServiceCallbacks callbacks) {
            Runnable runnable;
            final ServiceCallbacks serviceCallbacks = callbacks;
            new Runnable(this) {
                final /* synthetic */ ServiceBinderImpl this$1;

                {
                    this.this$1 = this$1;
                }

                public void run() {
                    IBinder b = serviceCallbacks.asBinder();
                    ConnectionRecord old = this.this$1.this$0.mConnections.remove(b);
                    if (old != null) {
                        boolean unlinkToDeath = b.unlinkToDeath(old, 0);
                    }
                }
            };
            this.this$0.mHandler.postOrRun(runnable);
        }

        public void search(String str, Bundle bundle, ResultReceiver resultReceiver, ServiceCallbacks serviceCallbacks) {
            Runnable runnable;
            String query = str;
            Bundle extras = bundle;
            ResultReceiver receiver = resultReceiver;
            ServiceCallbacks callbacks = serviceCallbacks;
            if (!TextUtils.isEmpty(query) && receiver != null) {
                final ServiceCallbacks serviceCallbacks2 = callbacks;
                final String str2 = query;
                final Bundle bundle2 = extras;
                final ResultReceiver resultReceiver2 = receiver;
                new Runnable(this) {
                    final /* synthetic */ ServiceBinderImpl this$1;

                    {
                        this.this$1 = this$1;
                    }

                    public void run() {
                        StringBuilder sb;
                        ConnectionRecord connection = this.this$1.this$0.mConnections.get(serviceCallbacks2.asBinder());
                        if (connection == null) {
                            new StringBuilder();
                            int w = Log.w(MediaBrowserServiceCompat.TAG, sb.append("search for callback that isn't registered query=").append(str2).toString());
                            return;
                        }
                        this.this$1.this$0.performSearch(str2, bundle2, connection, resultReceiver2);
                    }
                };
                this.this$0.mHandler.postOrRun(runnable);
            }
        }

        public void sendCustomAction(String str, Bundle bundle, ResultReceiver resultReceiver, ServiceCallbacks serviceCallbacks) {
            Runnable runnable;
            String action = str;
            Bundle extras = bundle;
            ResultReceiver receiver = resultReceiver;
            ServiceCallbacks callbacks = serviceCallbacks;
            if (!TextUtils.isEmpty(action) && receiver != null) {
                final ServiceCallbacks serviceCallbacks2 = callbacks;
                final String str2 = action;
                final Bundle bundle2 = extras;
                final ResultReceiver resultReceiver2 = receiver;
                new Runnable(this) {
                    final /* synthetic */ ServiceBinderImpl this$1;

                    {
                        this.this$1 = this$1;
                    }

                    public void run() {
                        StringBuilder sb;
                        ConnectionRecord connection = this.this$1.this$0.mConnections.get(serviceCallbacks2.asBinder());
                        if (connection == null) {
                            new StringBuilder();
                            int w = Log.w(MediaBrowserServiceCompat.TAG, sb.append("sendCustomAction for callback that isn't registered action=").append(str2).append(", extras=").append(bundle2).toString());
                            return;
                        }
                        this.this$1.this$0.performCustomAction(str2, bundle2, connection, resultReceiver2);
                    }
                };
                this.this$0.mHandler.postOrRun(runnable);
            }
        }
    }

    /* renamed from: android.support.v4.media.MediaBrowserServiceCompat$ServiceCallbacksCompat */
    private static class ServiceCallbacksCompat implements ServiceCallbacks {
        final Messenger mCallbacks;

        ServiceCallbacksCompat(Messenger callbacks) {
            this.mCallbacks = callbacks;
        }

        public IBinder asBinder() {
            return this.mCallbacks.getBinder();
        }

        public void onConnect(String str, MediaSessionCompat.Token token, Bundle bundle) throws RemoteException {
            Bundle bundle2;
            Bundle bundle3;
            String root = str;
            MediaSessionCompat.Token session = token;
            Bundle extras = bundle;
            if (extras == null) {
                new Bundle();
                extras = bundle3;
            }
            extras.putInt(MediaBrowserProtocol.EXTRA_SERVICE_VERSION, 2);
            new Bundle();
            Bundle data = bundle2;
            data.putString(MediaBrowserProtocol.DATA_MEDIA_ITEM_ID, root);
            data.putParcelable(MediaBrowserProtocol.DATA_MEDIA_SESSION_TOKEN, session);
            data.putBundle(MediaBrowserProtocol.DATA_ROOT_HINTS, extras);
            sendRequest(1, data);
        }

        public void onConnectFailed() throws RemoteException {
            sendRequest(2, (Bundle) null);
        }

        public void onLoadChildren(String mediaId, List<MediaBrowserCompat.MediaItem> list, Bundle options, Bundle notifyChildrenChangedOptions) throws RemoteException {
            Bundle bundle;
            ArrayList arrayList;
            ArrayList arrayList2;
            List<MediaBrowserCompat.MediaItem> list2 = list;
            new Bundle();
            Bundle data = bundle;
            data.putString(MediaBrowserProtocol.DATA_MEDIA_ITEM_ID, mediaId);
            data.putBundle(MediaBrowserProtocol.DATA_OPTIONS, options);
            data.putBundle(MediaBrowserProtocol.DATA_NOTIFY_CHILDREN_CHANGED_OPTIONS, notifyChildrenChangedOptions);
            if (list2 != null) {
                Bundle bundle2 = data;
                if (list2 instanceof ArrayList) {
                    arrayList2 = (ArrayList) list2;
                } else {
                    arrayList2 = arrayList;
                    new ArrayList(list2);
                }
                bundle2.putParcelableArrayList(MediaBrowserProtocol.DATA_MEDIA_ITEM_LIST, arrayList2);
            }
            sendRequest(3, data);
        }

        private void sendRequest(int what, Bundle data) throws RemoteException {
            Message msg = Message.obtain();
            msg.what = what;
            msg.arg1 = 2;
            msg.setData(data);
            this.mCallbacks.send(msg);
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void attachToBaseContext(Context base) {
        attachBaseContext(base);
    }

    public void onCreate() {
        MediaBrowserServiceImpl mediaBrowserServiceImpl;
        MediaBrowserServiceImpl mediaBrowserServiceImpl2;
        MediaBrowserServiceImpl mediaBrowserServiceImpl3;
        MediaBrowserServiceImpl mediaBrowserServiceImpl4;
        MediaBrowserServiceImpl mediaBrowserServiceImpl5;
        super.onCreate();
        if (Build.VERSION.SDK_INT >= 28) {
            new MediaBrowserServiceImplApi28(this);
            this.mImpl = mediaBrowserServiceImpl5;
        } else if (Build.VERSION.SDK_INT >= 26) {
            new MediaBrowserServiceImplApi26(this);
            this.mImpl = mediaBrowserServiceImpl4;
        } else if (Build.VERSION.SDK_INT >= 23) {
            new MediaBrowserServiceImplApi23(this);
            this.mImpl = mediaBrowserServiceImpl3;
        } else if (Build.VERSION.SDK_INT >= 21) {
            new MediaBrowserServiceImplApi21(this);
            this.mImpl = mediaBrowserServiceImpl2;
        } else {
            new MediaBrowserServiceImplBase(this);
            this.mImpl = mediaBrowserServiceImpl;
        }
        this.mImpl.onCreate();
    }

    public IBinder onBind(Intent intent) {
        return this.mImpl.onBind(intent);
    }

    public void dump(FileDescriptor fd, PrintWriter writer, String[] args) {
    }

    public void onLoadChildren(@NonNull String parentId, @NonNull Result<List<MediaBrowserCompat.MediaItem>> result, @NonNull Bundle bundle) {
        Result<List<MediaBrowserCompat.MediaItem>> result2 = result;
        Bundle bundle2 = bundle;
        result2.setFlags(1);
        onLoadChildren(parentId, result2);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void onSubscribe(String id, Bundle option) {
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void onUnsubscribe(String id) {
    }

    public void onLoadItem(String str, @NonNull Result<MediaBrowserCompat.MediaItem> result) {
        String str2 = str;
        Result<MediaBrowserCompat.MediaItem> result2 = result;
        result2.setFlags(2);
        result2.sendResult(null);
    }

    public void onSearch(@NonNull String str, Bundle bundle, @NonNull Result<List<MediaBrowserCompat.MediaItem>> result) {
        String str2 = str;
        Bundle bundle2 = bundle;
        Result<List<MediaBrowserCompat.MediaItem>> result2 = result;
        result2.setFlags(4);
        result2.sendResult(null);
    }

    public void onCustomAction(@NonNull String str, Bundle bundle, @NonNull Result<Bundle> result) {
        String str2 = str;
        Bundle bundle2 = bundle;
        result.sendError((Bundle) null);
    }

    public void setSessionToken(MediaSessionCompat.Token token) {
        Throwable th;
        Throwable th2;
        MediaSessionCompat.Token token2 = token;
        if (token2 == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("Session token may not be null.");
            throw th3;
        } else if (this.mSession != null) {
            Throwable th4 = th;
            new IllegalStateException("The session token has already been set.");
            throw th4;
        } else {
            this.mSession = token2;
            this.mImpl.setSessionToken(token2);
        }
    }

    @Nullable
    public MediaSessionCompat.Token getSessionToken() {
        return this.mSession;
    }

    public final Bundle getBrowserRootHints() {
        return this.mImpl.getBrowserRootHints();
    }

    @NonNull
    public final MediaSessionManager.RemoteUserInfo getCurrentBrowserInfo() {
        return this.mImpl.getCurrentBrowserInfo();
    }

    public void notifyChildrenChanged(@NonNull String str) {
        Throwable th;
        String parentId = str;
        if (parentId == null) {
            Throwable th2 = th;
            new IllegalArgumentException("parentId cannot be null in notifyChildrenChanged");
            throw th2;
        }
        this.mImpl.notifyChildrenChanged(parentId, (Bundle) null);
    }

    public void notifyChildrenChanged(@NonNull String str, @NonNull Bundle bundle) {
        Throwable th;
        Throwable th2;
        String parentId = str;
        Bundle options = bundle;
        if (parentId == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("parentId cannot be null in notifyChildrenChanged");
            throw th3;
        } else if (options == null) {
            Throwable th4 = th;
            new IllegalArgumentException("options cannot be null in notifyChildrenChanged");
            throw th4;
        } else {
            this.mImpl.notifyChildrenChanged(parentId, options);
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void notifyChildrenChanged(@NonNull MediaSessionManager.RemoteUserInfo remoteUserInfo, @NonNull String str, @NonNull Bundle bundle) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        MediaSessionManager.RemoteUserInfo remoteUserInfo2 = remoteUserInfo;
        String parentId = str;
        Bundle options = bundle;
        if (remoteUserInfo2 == null) {
            Throwable th4 = th3;
            new IllegalArgumentException("remoteUserInfo cannot be null in notifyChildrenChanged");
            throw th4;
        } else if (parentId == null) {
            Throwable th5 = th2;
            new IllegalArgumentException("parentId cannot be null in notifyChildrenChanged");
            throw th5;
        } else if (options == null) {
            Throwable th6 = th;
            new IllegalArgumentException("options cannot be null in notifyChildrenChanged");
            throw th6;
        } else {
            this.mImpl.notifyChildrenChanged(remoteUserInfo2, parentId, options);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isValidPackage(String str, int i) {
        String pkg = str;
        int uid = i;
        if (pkg == null) {
            return false;
        }
        String[] packages = getPackageManager().getPackagesForUid(uid);
        int N = packages.length;
        for (int i2 = 0; i2 < N; i2++) {
            if (packages[i2].equals(pkg)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void addSubscription(String str, ConnectionRecord connectionRecord, IBinder iBinder, Bundle bundle) {
        Object obj;
        List<Pair<IBinder, Bundle>> list;
        String id = str;
        ConnectionRecord connection = connectionRecord;
        IBinder token = iBinder;
        Bundle options = bundle;
        List<Pair<IBinder, Bundle>> callbackList = connection.subscriptions.get(id);
        if (callbackList == null) {
            new ArrayList<>();
            callbackList = list;
        }
        for (Pair<IBinder, Bundle> callback : callbackList) {
            if (token == callback.first && MediaBrowserCompatUtils.areSameOptions(options, (Bundle) callback.second)) {
                return;
            }
        }
        new Pair(token, options);
        boolean add = callbackList.add(obj);
        List<Pair<IBinder, Bundle>> put = connection.subscriptions.put(id, callbackList);
        performLoadChildren(id, connection, options, (Bundle) null);
        this.mCurConnection = connection;
        onSubscribe(id, options);
        this.mCurConnection = null;
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    public boolean removeSubscription(String str, ConnectionRecord connectionRecord, IBinder iBinder) {
        boolean z;
        String id = str;
        ConnectionRecord connection = connectionRecord;
        IBinder token = iBinder;
        if (token == null) {
            try {
                if (connection.subscriptions.remove(id) != null) {
                    z = true;
                } else {
                    z = false;
                }
                boolean z2 = z;
                this.mCurConnection = connection;
                onUnsubscribe(id);
                this.mCurConnection = null;
                return z2;
            } catch (Throwable th) {
                Throwable th2 = th;
                this.mCurConnection = connection;
                onUnsubscribe(id);
                this.mCurConnection = null;
                throw th2;
            }
        } else {
            boolean removed = false;
            List<Pair<IBinder, Bundle>> callbackList = connection.subscriptions.get(id);
            if (callbackList != null) {
                Iterator<Pair<IBinder, Bundle>> iter = callbackList.iterator();
                while (iter.hasNext()) {
                    if (token == iter.next().first) {
                        removed = true;
                        iter.remove();
                    }
                }
                if (callbackList.size() == 0) {
                    List<Pair<IBinder, Bundle>> remove = connection.subscriptions.remove(id);
                }
            }
            boolean z3 = removed;
            this.mCurConnection = connection;
            onUnsubscribe(id);
            this.mCurConnection = null;
            return z3;
        }
    }

    /* access modifiers changed from: package-private */
    public void performLoadChildren(String str, ConnectionRecord connectionRecord, Bundle bundle, Bundle notifyChildrenChangedOptions) {
        Result<List<MediaBrowserCompat.MediaItem>> result;
        Throwable th;
        StringBuilder sb;
        String parentId = str;
        ConnectionRecord connection = connectionRecord;
        Bundle subscribeOptions = bundle;
        final ConnectionRecord connectionRecord2 = connection;
        final String str2 = parentId;
        final Bundle bundle2 = subscribeOptions;
        final Bundle bundle3 = notifyChildrenChangedOptions;
        new Result<List<MediaBrowserCompat.MediaItem>>(this, parentId) {
            final /* synthetic */ MediaBrowserServiceCompat this$0;

            {
                this.this$0 = this$0;
            }

            /* access modifiers changed from: package-private */
            public void onResultSent(List<MediaBrowserCompat.MediaItem> list) {
                StringBuilder sb;
                StringBuilder sb2;
                List<MediaBrowserCompat.MediaItem> list2 = list;
                if (this.this$0.mConnections.get(connectionRecord2.callbacks.asBinder()) == connectionRecord2) {
                    try {
                        connectionRecord2.callbacks.onLoadChildren(str2, (getFlags() & 1) != 0 ? this.this$0.applyOptions(list2, bundle2) : list2, bundle2, bundle3);
                    } catch (RemoteException e) {
                        RemoteException remoteException = e;
                        new StringBuilder();
                        int w = Log.w(MediaBrowserServiceCompat.TAG, sb.append("Calling onLoadChildren() failed for id=").append(str2).append(" package=").append(connectionRecord2.pkg).toString());
                    }
                } else if (MediaBrowserServiceCompat.DEBUG) {
                    new StringBuilder();
                    int d = Log.d(MediaBrowserServiceCompat.TAG, sb2.append("Not sending onLoadChildren result for connection that has been disconnected. pkg=").append(connectionRecord2.pkg).append(" id=").append(str2).toString());
                }
            }
        };
        Result<List<MediaBrowserCompat.MediaItem>> result2 = result;
        this.mCurConnection = connection;
        if (subscribeOptions == null) {
            onLoadChildren(parentId, result2);
        } else {
            onLoadChildren(parentId, result2, subscribeOptions);
        }
        this.mCurConnection = null;
        if (!result2.isDone()) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("onLoadChildren must call detach() or sendResult() before returning for package=").append(connection.pkg).append(" id=").append(parentId).toString());
            throw th2;
        }
    }

    /* access modifiers changed from: package-private */
    public List<MediaBrowserCompat.MediaItem> applyOptions(List<MediaBrowserCompat.MediaItem> list, Bundle bundle) {
        List<MediaBrowserCompat.MediaItem> list2 = list;
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

    /* access modifiers changed from: package-private */
    public void performLoadItem(String str, ConnectionRecord connection, ResultReceiver receiver) {
        Result<MediaBrowserCompat.MediaItem> result;
        Throwable th;
        StringBuilder sb;
        String itemId = str;
        final ResultReceiver resultReceiver = receiver;
        new Result<MediaBrowserCompat.MediaItem>(this, itemId) {
            final /* synthetic */ MediaBrowserServiceCompat this$0;

            {
                this.this$0 = this$0;
            }

            /* access modifiers changed from: package-private */
            public void onResultSent(MediaBrowserCompat.MediaItem mediaItem) {
                Bundle bundle;
                MediaBrowserCompat.MediaItem item = mediaItem;
                if ((getFlags() & 2) != 0) {
                    resultReceiver.send(-1, (Bundle) null);
                    return;
                }
                new Bundle();
                Bundle bundle2 = bundle;
                bundle2.putParcelable(MediaBrowserServiceCompat.KEY_MEDIA_ITEM, item);
                resultReceiver.send(0, bundle2);
            }
        };
        Result<MediaBrowserCompat.MediaItem> result2 = result;
        this.mCurConnection = connection;
        onLoadItem(itemId, result2);
        this.mCurConnection = null;
        if (!result2.isDone()) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("onLoadItem must call detach() or sendResult() before returning for id=").append(itemId).toString());
            throw th2;
        }
    }

    /* access modifiers changed from: package-private */
    public void performSearch(String str, Bundle extras, ConnectionRecord connection, ResultReceiver receiver) {
        Result<List<MediaBrowserCompat.MediaItem>> result;
        Throwable th;
        StringBuilder sb;
        String query = str;
        final ResultReceiver resultReceiver = receiver;
        new Result<List<MediaBrowserCompat.MediaItem>>(this, query) {
            final /* synthetic */ MediaBrowserServiceCompat this$0;

            {
                this.this$0 = this$0;
            }

            /* access modifiers changed from: package-private */
            public void onResultSent(List<MediaBrowserCompat.MediaItem> list) {
                Bundle bundle;
                List<MediaBrowserCompat.MediaItem> items = list;
                if ((getFlags() & 4) != 0 || items == null) {
                    resultReceiver.send(-1, (Bundle) null);
                    return;
                }
                new Bundle();
                Bundle bundle2 = bundle;
                bundle2.putParcelableArray(MediaBrowserServiceCompat.KEY_SEARCH_RESULTS, (Parcelable[]) items.toArray(new MediaBrowserCompat.MediaItem[0]));
                resultReceiver.send(0, bundle2);
            }
        };
        Result<List<MediaBrowserCompat.MediaItem>> result2 = result;
        this.mCurConnection = connection;
        onSearch(query, extras, result2);
        this.mCurConnection = null;
        if (!result2.isDone()) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("onSearch must call detach() or sendResult() before returning for query=").append(query).toString());
            throw th2;
        }
    }

    /* access modifiers changed from: package-private */
    public void performCustomAction(String str, Bundle bundle, ConnectionRecord connection, ResultReceiver receiver) {
        Result<Bundle> result;
        Throwable th;
        StringBuilder sb;
        String action = str;
        Bundle extras = bundle;
        final ResultReceiver resultReceiver = receiver;
        new Result<Bundle>(this, action) {
            final /* synthetic */ MediaBrowserServiceCompat this$0;

            {
                this.this$0 = this$0;
            }

            /* access modifiers changed from: package-private */
            public void onResultSent(Bundle result) {
                resultReceiver.send(0, result);
            }

            /* access modifiers changed from: package-private */
            public void onProgressUpdateSent(Bundle data) {
                resultReceiver.send(1, data);
            }

            /* access modifiers changed from: package-private */
            public void onErrorSent(Bundle data) {
                resultReceiver.send(-1, data);
            }
        };
        Result<Bundle> result2 = result;
        this.mCurConnection = connection;
        onCustomAction(action, extras, result2);
        this.mCurConnection = null;
        if (!result2.isDone()) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalStateException(sb.append("onCustomAction must call detach() or sendResult() or sendError() before returning for action=").append(action).append(" extras=").append(extras).toString());
            throw th2;
        }
    }

    /* renamed from: android.support.v4.media.MediaBrowserServiceCompat$BrowserRoot */
    public static final class BrowserRoot {
        public static final String EXTRA_OFFLINE = "android.service.media.extra.OFFLINE";
        public static final String EXTRA_RECENT = "android.service.media.extra.RECENT";
        public static final String EXTRA_SUGGESTED = "android.service.media.extra.SUGGESTED";
        @Deprecated
        public static final String EXTRA_SUGGESTION_KEYWORDS = "android.service.media.extra.SUGGESTION_KEYWORDS";
        private final Bundle mExtras;
        private final String mRootId;

        public BrowserRoot(@NonNull String str, @Nullable Bundle bundle) {
            Throwable th;
            String rootId = str;
            Bundle extras = bundle;
            if (rootId == null) {
                Throwable th2 = th;
                new IllegalArgumentException("The root id in BrowserRoot cannot be null. Use null for BrowserRoot instead.");
                throw th2;
            }
            this.mRootId = rootId;
            this.mExtras = extras;
        }

        public String getRootId() {
            return this.mRootId;
        }

        public Bundle getExtras() {
            return this.mExtras;
        }
    }
}

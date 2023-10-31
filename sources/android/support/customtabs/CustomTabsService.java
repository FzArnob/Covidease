package android.support.customtabs;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.customtabs.ICustomTabsService;
import android.support.p000v4.util.C1642ArrayMap;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public abstract class CustomTabsService extends Service {
    public static final String ACTION_CUSTOM_TABS_CONNECTION = "android.support.customtabs.action.CustomTabsService";
    public static final String KEY_URL = "android.support.customtabs.otherurls.URL";
    public static final int RELATION_HANDLE_ALL_URLS = 2;
    public static final int RELATION_USE_AS_ORIGIN = 1;
    public static final int RESULT_FAILURE_DISALLOWED = -1;
    public static final int RESULT_FAILURE_MESSAGING_ERROR = -3;
    public static final int RESULT_FAILURE_REMOTE_ERROR = -2;
    public static final int RESULT_SUCCESS = 0;
    private ICustomTabsService.Stub mBinder;
    final Map<IBinder, IBinder.DeathRecipient> mDeathRecipientMap;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Relation {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Result {
    }

    /* access modifiers changed from: protected */
    public abstract Bundle extraCommand(String str, Bundle bundle);

    /* access modifiers changed from: protected */
    public abstract boolean mayLaunchUrl(CustomTabsSessionToken customTabsSessionToken, Uri uri, Bundle bundle, List<Bundle> list);

    /* access modifiers changed from: protected */
    public abstract boolean newSession(CustomTabsSessionToken customTabsSessionToken);

    /* access modifiers changed from: protected */
    public abstract int postMessage(CustomTabsSessionToken customTabsSessionToken, String str, Bundle bundle);

    /* access modifiers changed from: protected */
    public abstract boolean requestPostMessageChannel(CustomTabsSessionToken customTabsSessionToken, Uri uri);

    /* access modifiers changed from: protected */
    public abstract boolean updateVisuals(CustomTabsSessionToken customTabsSessionToken, Bundle bundle);

    /* access modifiers changed from: protected */
    public abstract boolean validateRelationship(CustomTabsSessionToken customTabsSessionToken, int i, Uri uri, Bundle bundle);

    /* access modifiers changed from: protected */
    public abstract boolean warmup(long j);

    public CustomTabsService() {
        Map<IBinder, IBinder.DeathRecipient> map;
        ICustomTabsService.Stub stub;
        new C1642ArrayMap();
        this.mDeathRecipientMap = map;
        new ICustomTabsService.Stub(this) {
            final /* synthetic */ CustomTabsService this$0;

            {
                this.this$0 = this$0;
            }

            public boolean warmup(long flags) {
                return this.this$0.warmup(flags);
            }

            public boolean newSession(ICustomTabsCallback iCustomTabsCallback) {
                CustomTabsSessionToken customTabsSessionToken;
                IBinder.DeathRecipient deathRecipient;
                Map<IBinder, IBinder.DeathRecipient> map;
                Throwable th;
                ICustomTabsCallback callback = iCustomTabsCallback;
                new CustomTabsSessionToken(callback);
                CustomTabsSessionToken sessionToken = customTabsSessionToken;
                try {
                    final CustomTabsSessionToken customTabsSessionToken2 = sessionToken;
                    new IBinder.DeathRecipient(this) {
                        final /* synthetic */ C00511 this$1;

                        {
                            this.this$1 = this$1;
                        }

                        public void binderDied() {
                            boolean cleanUpSession = this.this$1.this$0.cleanUpSession(customTabsSessionToken2);
                        }
                    };
                    IBinder.DeathRecipient deathRecipient2 = deathRecipient;
                    Map<IBinder, IBinder.DeathRecipient> map2 = this.this$0.mDeathRecipientMap;
                    map = map2;
                    synchronized (map2) {
                        callback.asBinder().linkToDeath(deathRecipient2, 0);
                        IBinder.DeathRecipient put = this.this$0.mDeathRecipientMap.put(callback.asBinder(), deathRecipient2);
                        return this.this$0.newSession(sessionToken);
                    }
                } catch (RemoteException e) {
                    RemoteException remoteException = e;
                    return false;
                } catch (Throwable th2) {
                    while (true) {
                        th = th2;
                        Map<IBinder, IBinder.DeathRecipient> map3 = map;
                    }
                    throw th;
                }
            }

            public boolean mayLaunchUrl(ICustomTabsCallback callback, Uri url, Bundle extras, List<Bundle> otherLikelyBundles) {
                CustomTabsSessionToken customTabsSessionToken;
                new CustomTabsSessionToken(callback);
                return this.this$0.mayLaunchUrl(customTabsSessionToken, url, extras, otherLikelyBundles);
            }

            public Bundle extraCommand(String commandName, Bundle args) {
                return this.this$0.extraCommand(commandName, args);
            }

            public boolean updateVisuals(ICustomTabsCallback callback, Bundle bundle) {
                CustomTabsSessionToken customTabsSessionToken;
                new CustomTabsSessionToken(callback);
                return this.this$0.updateVisuals(customTabsSessionToken, bundle);
            }

            public boolean requestPostMessageChannel(ICustomTabsCallback callback, Uri postMessageOrigin) {
                CustomTabsSessionToken customTabsSessionToken;
                new CustomTabsSessionToken(callback);
                return this.this$0.requestPostMessageChannel(customTabsSessionToken, postMessageOrigin);
            }

            public int postMessage(ICustomTabsCallback callback, String message, Bundle extras) {
                CustomTabsSessionToken customTabsSessionToken;
                new CustomTabsSessionToken(callback);
                return this.this$0.postMessage(customTabsSessionToken, message, extras);
            }

            public boolean validateRelationship(ICustomTabsCallback callback, int relation, Uri origin, Bundle extras) {
                CustomTabsSessionToken customTabsSessionToken;
                new CustomTabsSessionToken(callback);
                return this.this$0.validateRelationship(customTabsSessionToken, relation, origin, extras);
            }
        };
        this.mBinder = stub;
    }

    public IBinder onBind(Intent intent) {
        Intent intent2 = intent;
        return this.mBinder;
    }

    /* access modifiers changed from: protected */
    public boolean cleanUpSession(CustomTabsSessionToken customTabsSessionToken) {
        Map<IBinder, IBinder.DeathRecipient> map;
        CustomTabsSessionToken sessionToken = customTabsSessionToken;
        try {
            Map<IBinder, IBinder.DeathRecipient> map2 = this.mDeathRecipientMap;
            map = map2;
            synchronized (map2) {
                IBinder binder = sessionToken.getCallbackBinder();
                boolean unlinkToDeath = binder.unlinkToDeath(this.mDeathRecipientMap.get(binder), 0);
                IBinder.DeathRecipient remove = this.mDeathRecipientMap.remove(binder);
                return true;
            }
        } catch (NoSuchElementException e) {
            NoSuchElementException noSuchElementException = e;
            return false;
        } catch (Throwable th) {
            Throwable th2 = th;
            Map<IBinder, IBinder.DeathRecipient> map3 = map;
            throw th2;
        }
    }
}

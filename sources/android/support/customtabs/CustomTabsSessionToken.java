package android.support.customtabs;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.customtabs.ICustomTabsCallback;
import android.support.p000v4.app.BundleCompat;
import android.util.Log;

public class CustomTabsSessionToken {
    private static final String TAG = "CustomTabsSessionToken";
    private final CustomTabsCallback mCallback;
    final ICustomTabsCallback mCallbackBinder;

    static class MockCallback extends ICustomTabsCallback.Stub {
        MockCallback() {
        }

        public void onNavigationEvent(int navigationEvent, Bundle extras) {
        }

        public void extraCallback(String callbackName, Bundle args) {
        }

        public void onMessageChannelReady(Bundle extras) {
        }

        public void onPostMessage(String message, Bundle extras) {
        }

        public void onRelationshipValidationResult(int relation, Uri requestedOrigin, boolean result, Bundle extras) {
        }

        public IBinder asBinder() {
            return this;
        }
    }

    public static CustomTabsSessionToken getSessionTokenFromIntent(Intent intent) {
        CustomTabsSessionToken customTabsSessionToken;
        IBinder binder = BundleCompat.getBinder(intent.getExtras(), CustomTabsIntent.EXTRA_SESSION);
        if (binder == null) {
            return null;
        }
        new CustomTabsSessionToken(ICustomTabsCallback.Stub.asInterface(binder));
        return customTabsSessionToken;
    }

    @NonNull
    public static CustomTabsSessionToken createMockSessionTokenForTesting() {
        CustomTabsSessionToken customTabsSessionToken;
        ICustomTabsCallback iCustomTabsCallback;
        CustomTabsSessionToken customTabsSessionToken2 = customTabsSessionToken;
        new MockCallback();
        new CustomTabsSessionToken(iCustomTabsCallback);
        return customTabsSessionToken2;
    }

    CustomTabsSessionToken(ICustomTabsCallback callbackBinder) {
        CustomTabsCallback customTabsCallback;
        this.mCallbackBinder = callbackBinder;
        new CustomTabsCallback(this) {
            final /* synthetic */ CustomTabsSessionToken this$0;

            {
                this.this$0 = this$0;
            }

            public void onNavigationEvent(int navigationEvent, Bundle extras) {
                try {
                    this.this$0.mCallbackBinder.onNavigationEvent(navigationEvent, extras);
                } catch (RemoteException e) {
                    RemoteException remoteException = e;
                    int e2 = Log.e(CustomTabsSessionToken.TAG, "RemoteException during ICustomTabsCallback transaction");
                }
            }

            public void extraCallback(String callbackName, Bundle args) {
                try {
                    this.this$0.mCallbackBinder.extraCallback(callbackName, args);
                } catch (RemoteException e) {
                    RemoteException remoteException = e;
                    int e2 = Log.e(CustomTabsSessionToken.TAG, "RemoteException during ICustomTabsCallback transaction");
                }
            }

            public void onMessageChannelReady(Bundle extras) {
                try {
                    this.this$0.mCallbackBinder.onMessageChannelReady(extras);
                } catch (RemoteException e) {
                    RemoteException remoteException = e;
                    int e2 = Log.e(CustomTabsSessionToken.TAG, "RemoteException during ICustomTabsCallback transaction");
                }
            }

            public void onPostMessage(String message, Bundle extras) {
                try {
                    this.this$0.mCallbackBinder.onPostMessage(message, extras);
                } catch (RemoteException e) {
                    RemoteException remoteException = e;
                    int e2 = Log.e(CustomTabsSessionToken.TAG, "RemoteException during ICustomTabsCallback transaction");
                }
            }

            public void onRelationshipValidationResult(int relation, Uri origin, boolean result, Bundle extras) {
                try {
                    this.this$0.mCallbackBinder.onRelationshipValidationResult(relation, origin, result, extras);
                } catch (RemoteException e) {
                    RemoteException remoteException = e;
                    int e2 = Log.e(CustomTabsSessionToken.TAG, "RemoteException during ICustomTabsCallback transaction");
                }
            }
        };
        this.mCallback = customTabsCallback;
    }

    /* access modifiers changed from: package-private */
    public IBinder getCallbackBinder() {
        return this.mCallbackBinder.asBinder();
    }

    public int hashCode() {
        return getCallbackBinder().hashCode();
    }

    public boolean equals(Object obj) {
        Object o = obj;
        if (!(o instanceof CustomTabsSessionToken)) {
            return false;
        }
        return ((CustomTabsSessionToken) o).getCallbackBinder().equals(this.mCallbackBinder.asBinder());
    }

    public CustomTabsCallback getCallback() {
        return this.mCallback;
    }

    public boolean isAssociatedWith(CustomTabsSession session) {
        return session.getBinder().equals(this.mCallbackBinder);
    }
}

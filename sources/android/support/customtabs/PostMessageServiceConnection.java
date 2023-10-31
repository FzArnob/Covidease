package android.support.customtabs;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.customtabs.ICustomTabsCallback;
import android.support.customtabs.IPostMessageService;

public abstract class PostMessageServiceConnection implements ServiceConnection {
    private final Object mLock;
    private IPostMessageService mService;
    private final ICustomTabsCallback mSessionBinder;

    public PostMessageServiceConnection(CustomTabsSessionToken session) {
        Object obj;
        new Object();
        this.mLock = obj;
        this.mSessionBinder = ICustomTabsCallback.Stub.asInterface(session.getCallbackBinder());
    }

    public boolean bindSessionToPostMessageService(Context context, String packageName) {
        Intent intent;
        new Intent();
        Intent intent2 = intent;
        Intent className = intent2.setClassName(packageName, PostMessageService.class.getName());
        return context.bindService(intent2, this, 1);
    }

    public void unbindFromContext(Context context) {
        context.unbindService(this);
    }

    public final void onServiceConnected(ComponentName componentName, IBinder service) {
        ComponentName componentName2 = componentName;
        this.mService = IPostMessageService.Stub.asInterface(service);
        onPostMessageServiceConnected();
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        ComponentName componentName2 = componentName;
        this.mService = null;
        onPostMessageServiceDisconnected();
    }

    public final boolean notifyMessageChannelReady(Bundle bundle) {
        Bundle extras = bundle;
        if (this.mService == null) {
            return false;
        }
        Object obj = this.mLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                this.mService.onMessageChannelReady(this.mSessionBinder, extras);
                return true;
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                Object obj3 = obj2;
                return false;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj4 = obj2;
                throw th2;
            }
        }
    }

    public final boolean postMessage(String str, Bundle bundle) {
        String message = str;
        Bundle extras = bundle;
        if (this.mService == null) {
            return false;
        }
        Object obj = this.mLock;
        Object obj2 = obj;
        synchronized (obj) {
            try {
                this.mService.onPostMessage(this.mSessionBinder, message, extras);
                return true;
            } catch (RemoteException e) {
                RemoteException remoteException = e;
                Object obj3 = obj2;
                return false;
            } catch (Throwable th) {
                Throwable th2 = th;
                Object obj4 = obj2;
                throw th2;
            }
        }
    }

    public void onPostMessageServiceConnected() {
    }

    public void onPostMessageServiceDisconnected() {
    }
}

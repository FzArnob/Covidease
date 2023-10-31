package android.support.customtabs;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.customtabs.IPostMessageService;

public class PostMessageService extends Service {
    private IPostMessageService.Stub mBinder;

    public PostMessageService() {
        IPostMessageService.Stub stub;
        new IPostMessageService.Stub(this) {
            final /* synthetic */ PostMessageService this$0;

            {
                this.this$0 = this$0;
            }

            public void onMessageChannelReady(ICustomTabsCallback callback, Bundle extras) throws RemoteException {
                callback.onMessageChannelReady(extras);
            }

            public void onPostMessage(ICustomTabsCallback callback, String message, Bundle extras) throws RemoteException {
                callback.onPostMessage(message, extras);
            }
        };
        this.mBinder = stub;
    }

    public IBinder onBind(Intent intent) {
        Intent intent2 = intent;
        return this.mBinder;
    }
}

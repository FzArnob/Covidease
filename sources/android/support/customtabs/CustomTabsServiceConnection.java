package android.support.customtabs;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.customtabs.ICustomTabsService;

public abstract class CustomTabsServiceConnection implements ServiceConnection {
    public abstract void onCustomTabsServiceConnected(ComponentName componentName, CustomTabsClient customTabsClient);

    public CustomTabsServiceConnection() {
    }

    public final void onServiceConnected(ComponentName componentName, IBinder service) {
        CustomTabsClient customTabsClient;
        ComponentName name = componentName;
        new CustomTabsClient(this, ICustomTabsService.Stub.asInterface(service), name) {
            final /* synthetic */ CustomTabsServiceConnection this$0;

            {
                this.this$0 = this$0;
            }
        };
        onCustomTabsServiceConnected(name, customTabsClient);
    }
}

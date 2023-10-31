package com.bumptech.glide.manager;

import android.content.Context;
import com.bumptech.glide.manager.ConnectivityMonitor;

public class ConnectivityMonitorFactory {
    public ConnectivityMonitorFactory() {
    }

    public ConnectivityMonitor build(Context context, ConnectivityMonitor.ConnectivityListener connectivityListener) {
        ConnectivityMonitor connectivityMonitor;
        ConnectivityMonitor connectivityMonitor2;
        Context context2 = context;
        ConnectivityMonitor.ConnectivityListener listener = connectivityListener;
        if (context2.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0) {
            new DefaultConnectivityMonitor(context2, listener);
            return connectivityMonitor2;
        }
        new NullConnectivityMonitor();
        return connectivityMonitor;
    }
}

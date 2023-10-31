package com.bumptech.glide.manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.bumptech.glide.manager.ConnectivityMonitor;

class DefaultConnectivityMonitor implements ConnectivityMonitor {
    private final BroadcastReceiver connectivityReceiver;
    private final Context context;
    /* access modifiers changed from: private */
    public boolean isConnected;
    private boolean isRegistered;
    /* access modifiers changed from: private */
    public final ConnectivityMonitor.ConnectivityListener listener;

    static /* synthetic */ boolean access$002(DefaultConnectivityMonitor x0, boolean x1) {
        boolean z = x1;
        boolean z2 = z;
        x0.isConnected = z2;
        return z;
    }

    public DefaultConnectivityMonitor(Context context2, ConnectivityMonitor.ConnectivityListener listener2) {
        BroadcastReceiver broadcastReceiver;
        new BroadcastReceiver(this) {
            final /* synthetic */ DefaultConnectivityMonitor this$0;

            {
                this.this$0 = r5;
            }

            public void onReceive(Context context, Intent intent) {
                Intent intent2 = intent;
                boolean wasConnected = this.this$0.isConnected;
                boolean access$002 = DefaultConnectivityMonitor.access$002(this.this$0, this.this$0.isConnected(context));
                if (wasConnected != this.this$0.isConnected) {
                    this.this$0.listener.onConnectivityChanged(this.this$0.isConnected);
                }
            }
        };
        this.connectivityReceiver = broadcastReceiver;
        this.context = context2.getApplicationContext();
        this.listener = listener2;
    }

    private void register() {
        IntentFilter intentFilter;
        if (!this.isRegistered) {
            this.isConnected = isConnected(this.context);
            new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
            Intent registerReceiver = this.context.registerReceiver(this.connectivityReceiver, intentFilter);
            this.isRegistered = true;
        }
    }

    private void unregister() {
        if (this.isRegistered) {
            this.context.unregisterReceiver(this.connectivityReceiver);
            this.isRegistered = false;
        }
    }

    /* access modifiers changed from: private */
    public boolean isConnected(Context context2) {
        NetworkInfo networkInfo = ((ConnectivityManager) context2.getSystemService("connectivity")).getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    public void onStart() {
        register();
    }

    public void onStop() {
        unregister();
    }

    public void onDestroy() {
    }
}

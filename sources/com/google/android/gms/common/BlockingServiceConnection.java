package com.google.android.gms.common;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@KeepForSdk
public class BlockingServiceConnection implements ServiceConnection {
    private boolean zze = false;
    private final BlockingQueue<IBinder> zzf;

    public BlockingServiceConnection() {
        BlockingQueue<IBinder> blockingQueue;
        new LinkedBlockingQueue();
        this.zzf = blockingQueue;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        ComponentName componentName2 = componentName;
        boolean add = this.zzf.add(iBinder);
    }

    public void onServiceDisconnected(ComponentName componentName) {
    }

    @KeepForSdk
    public IBinder getServiceWithTimeout(long j, TimeUnit timeUnit) throws InterruptedException, TimeoutException {
        Throwable th;
        Throwable th2;
        long j2 = j;
        TimeUnit timeUnit2 = timeUnit;
        Preconditions.checkNotMainThread("BlockingServiceConnection.getServiceWithTimeout() called on main thread");
        if (this.zze) {
            Throwable th3 = th2;
            new IllegalStateException("Cannot call get on this connection more than once");
            throw th3;
        }
        this.zze = true;
        IBinder poll = this.zzf.poll(j2, timeUnit2);
        IBinder iBinder = poll;
        if (poll != null) {
            return iBinder;
        }
        Throwable th4 = th;
        new TimeoutException("Timed out waiting for the service connection");
        throw th4;
    }

    @KeepForSdk
    public IBinder getService() throws InterruptedException {
        Throwable th;
        Preconditions.checkNotMainThread("BlockingServiceConnection.getService() called on main thread");
        if (this.zze) {
            Throwable th2 = th;
            new IllegalStateException("Cannot call get on this connection more than once");
            throw th2;
        }
        this.zze = true;
        return this.zzf.take();
    }
}

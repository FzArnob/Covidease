package com.google.android.gms.common.providers;

import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.concurrent.ScheduledExecutorService;

@KeepForSdk
public class PooledExecutorsProvider {
    private static PooledExecutorFactory zzey;

    public interface PooledExecutorFactory {
        @KeepForSdk
        ScheduledExecutorService newSingleThreadScheduledExecutor();
    }

    @KeepForSdk
    public static synchronized PooledExecutorFactory getInstance() {
        PooledExecutorFactory pooledExecutorFactory;
        PooledExecutorFactory pooledExecutorFactory2;
        synchronized (PooledExecutorsProvider.class) {
            if (zzey == null) {
                new zza();
                zzey = pooledExecutorFactory2;
            }
            pooledExecutorFactory = zzey;
        }
        return pooledExecutorFactory;
    }

    private PooledExecutorsProvider() {
    }
}

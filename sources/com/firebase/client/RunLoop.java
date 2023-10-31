package com.firebase.client;

import java.util.concurrent.ScheduledFuture;

public interface RunLoop {
    void restart();

    ScheduledFuture schedule(Runnable runnable, long j);

    void scheduleNow(Runnable runnable);

    void shutdown();
}

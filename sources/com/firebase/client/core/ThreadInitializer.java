package com.firebase.client.core;

import java.lang.Thread;

public interface ThreadInitializer {
    public static final ThreadInitializer defaultInstance;

    void setDaemon(Thread thread, boolean z);

    void setName(Thread thread, String str);

    void setUncaughtExceptionHandler(Thread thread, Thread.UncaughtExceptionHandler uncaughtExceptionHandler);

    static {
        ThreadInitializer threadInitializer;
        new ThreadInitializer() {
            public void setName(Thread t, String name) {
                t.setName(name);
            }

            public void setDaemon(Thread t, boolean isDaemon) {
                t.setDaemon(isDaemon);
            }

            public void setUncaughtExceptionHandler(Thread t, Thread.UncaughtExceptionHandler handler) {
                t.setUncaughtExceptionHandler(handler);
            }
        };
        defaultInstance = threadInitializer;
    }
}

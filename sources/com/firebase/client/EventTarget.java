package com.firebase.client;

public interface EventTarget {
    void postEvent(Runnable runnable);

    void restart();

    void shutdown();
}

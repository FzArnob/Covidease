package com.firebase.client.core.view;

import com.firebase.client.core.Path;

public interface Event {

    public enum EventType {
    }

    void fire();

    Path getPath();

    String toString();
}

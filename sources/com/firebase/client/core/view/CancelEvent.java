package com.firebase.client.core.view;

import com.firebase.client.FirebaseError;
import com.firebase.client.core.EventRegistration;
import com.firebase.client.core.Path;

public class CancelEvent implements Event {
    private final FirebaseError error;
    private final EventRegistration eventRegistration;
    private final Path path;

    public CancelEvent(EventRegistration eventRegistration2, FirebaseError error2, Path path2) {
        this.eventRegistration = eventRegistration2;
        this.path = path2;
        this.error = error2;
    }

    public Path getPath() {
        return this.path;
    }

    public void fire() {
        this.eventRegistration.fireCancelEvent(this.error);
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append(getPath()).append(":").append("CANCEL").toString();
    }
}

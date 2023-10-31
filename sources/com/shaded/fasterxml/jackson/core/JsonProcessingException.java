package com.shaded.fasterxml.jackson.core;

import java.io.IOException;

public class JsonProcessingException extends IOException {
    static final long serialVersionUID = 123;
    protected JsonLocation _location;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected JsonProcessingException(String str, JsonLocation jsonLocation, Throwable th) {
        super(str);
        JsonLocation jsonLocation2 = jsonLocation;
        Throwable th2 = th;
        if (th2 != null) {
            Throwable initCause = initCause(th2);
        }
        this._location = jsonLocation2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected JsonProcessingException(String str) {
        super(str);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    protected JsonProcessingException(String str, JsonLocation jsonLocation) {
        this(str, jsonLocation, (Throwable) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    protected JsonProcessingException(String str, Throwable th) {
        this(str, (JsonLocation) null, th);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    protected JsonProcessingException(Throwable th) {
        this((String) null, (JsonLocation) null, th);
    }

    public JsonLocation getLocation() {
        return this._location;
    }

    public String getOriginalMessage() {
        return super.getMessage();
    }

    /* access modifiers changed from: protected */
    public String getMessageSuffix() {
        return null;
    }

    public String getMessage() {
        StringBuilder sb;
        String message = super.getMessage();
        if (message == null) {
            message = "N/A";
        }
        JsonLocation location = getLocation();
        String messageSuffix = getMessageSuffix();
        if (!(location == null && messageSuffix == null)) {
            new StringBuilder(100);
            StringBuilder sb2 = sb;
            StringBuilder append = sb2.append(message);
            if (messageSuffix != null) {
                StringBuilder append2 = sb2.append(messageSuffix);
            }
            if (location != null) {
                StringBuilder append3 = sb2.append(10);
                StringBuilder append4 = sb2.append(" at ");
                StringBuilder append5 = sb2.append(location.toString());
            }
            message = sb2.toString();
        }
        return message;
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append(getClass().getName()).append(": ").append(getMessage()).toString();
    }
}

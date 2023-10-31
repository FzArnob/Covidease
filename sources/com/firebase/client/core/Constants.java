package com.firebase.client.core;

import com.firebase.client.snapshot.ChildKey;

public class Constants {
    public static final ChildKey DOT_INFO = ChildKey.fromString(".info");
    public static final ChildKey DOT_INFO_AUTHENTICATED = ChildKey.fromString("authenticated");
    public static final ChildKey DOT_INFO_CONNECTED = ChildKey.fromString("connected");
    public static final ChildKey DOT_INFO_SERVERTIME_OFFSET = ChildKey.fromString("serverTimeOffset");
    public static final String WIRE_PROTOCOL_VERSION = "5";

    public Constants() {
    }
}

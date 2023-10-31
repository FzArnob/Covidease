package com.firebase.client;

import com.firebase.client.core.ServerValues;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ServerValue {
    public static final Map<String, String> TIMESTAMP = createServerValuePlaceholder("timestamp");

    public ServerValue() {
    }

    private static Map<String, String> createServerValuePlaceholder(String key) {
        Map<String, String> map;
        new HashMap<>();
        Map<String, String> result = map;
        String put = result.put(ServerValues.NAME_SUBKEY_SERVERVALUE, key);
        return Collections.unmodifiableMap(result);
    }
}

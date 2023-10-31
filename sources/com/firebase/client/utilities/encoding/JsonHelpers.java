package com.firebase.client.utilities.encoding;

import com.shaded.fasterxml.jackson.databind.ObjectMapper;

public class JsonHelpers {
    private static final ObjectMapper mapperInstance;

    public JsonHelpers() {
    }

    static {
        ObjectMapper objectMapper;
        new ObjectMapper();
        mapperInstance = objectMapper;
    }

    public static ObjectMapper getMapper() {
        return mapperInstance;
    }
}

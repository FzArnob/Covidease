package com.firebase.client.authentication.util;

import com.firebase.client.utilities.Base64;
import com.firebase.client.utilities.encoding.JsonHelpers;
import com.shaded.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.util.Map;

public class JsonWebToken {
    private final Map<String, Object> claims;
    private final Object data;
    private final Map<String, Object> header;
    private final String signature;

    private JsonWebToken(Map<String, Object> header2, Map<String, Object> claims2, Object data2, String signature2) {
        this.header = header2;
        this.claims = claims2;
        this.data = data2;
        this.signature = signature2;
    }

    public Map<String, Object> getHeader() {
        return this.header;
    }

    public Map<String, Object> getClaims() {
        return this.claims;
    }

    public Object getData() {
        return this.data;
    }

    public String getSignature() {
        return this.signature;
    }

    private static String fixLength(String str) {
        StringBuilder sb;
        String str2 = str;
        int missing = (4 - (str2.length() % 4)) % 4;
        if (missing == 0) {
            return str2;
        }
        new StringBuilder(str2);
        StringBuilder builder = sb;
        for (int i = 0; i < missing; i++) {
            StringBuilder append = builder.append("=");
        }
        return builder.toString();
    }

    public static JsonWebToken decode(String str) throws IOException {
        TypeReference<Map<String, Object>> typeReference;
        JsonWebToken jsonWebToken;
        Throwable th;
        StringBuilder sb;
        String token = str;
        String[] parts = token.split("\\.");
        if (parts.length != 3) {
            Throwable th2 = th;
            new StringBuilder();
            new IOException(sb.append("Not a valid token: ").append(token).toString());
            throw th2;
        }
        new TypeReference<Map<String, Object>>() {
        };
        TypeReference<Map<String, Object>> mapRef = typeReference;
        Map<String, Object> header2 = (Map) JsonHelpers.getMapper().readValue(Base64.decode(fixLength(parts[0])), (TypeReference) mapRef);
        Map<String, Object> claims2 = (Map) JsonHelpers.getMapper().readValue(Base64.decode(fixLength(parts[1])), (TypeReference) mapRef);
        String signature2 = parts[2];
        Object data2 = claims2.get("d");
        Object remove = claims2.remove("d");
        new JsonWebToken(header2, claims2, data2, signature2);
        return jsonWebToken;
    }
}

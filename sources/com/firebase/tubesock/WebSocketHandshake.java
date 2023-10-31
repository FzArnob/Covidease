package com.firebase.tubesock;

import java.net.URI;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import org.shaded.apache.http.protocol.HTTP;

class WebSocketHandshake {
    private static final String WEBSOCKET_VERSION = "13";
    private Map<String, String> extraHeaders = null;
    private String nonce = null;
    private String protocol = null;
    private URI url = null;

    public WebSocketHandshake(URI url2, String protocol2, Map<String, String> extraHeaders2) {
        this.url = url2;
        this.protocol = protocol2;
        this.extraHeaders = extraHeaders2;
        this.nonce = createNonce();
    }

    public byte[] getHandshake() {
        StringBuilder sb;
        StringBuilder sb2;
        String sb3;
        LinkedHashMap linkedHashMap;
        StringBuilder sb4;
        StringBuilder sb5;
        StringBuilder sb6;
        StringBuilder sb7;
        String path = this.url.getPath();
        String query = this.url.getQuery();
        new StringBuilder();
        StringBuilder append = sb.append(path);
        if (query == null) {
            sb3 = "";
        } else {
            new StringBuilder();
            sb3 = sb2.append("?").append(query).toString();
        }
        String path2 = append.append(sb3).toString();
        String host = this.url.getHost();
        if (this.url.getPort() != -1) {
            new StringBuilder();
            host = sb7.append(host).append(":").append(this.url.getPort()).toString();
        }
        new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = linkedHashMap;
        Object put = linkedHashMap2.put(HTTP.TARGET_HOST, host);
        Object put2 = linkedHashMap2.put("Upgrade", "websocket");
        Object put3 = linkedHashMap2.put(HTTP.CONN_DIRECTIVE, "Upgrade");
        Object put4 = linkedHashMap2.put("Sec-WebSocket-Version", WEBSOCKET_VERSION);
        Object put5 = linkedHashMap2.put("Sec-WebSocket-Key", this.nonce);
        if (this.protocol != null) {
            Object put6 = linkedHashMap2.put("Sec-WebSocket-Protocol", this.protocol);
        }
        if (this.extraHeaders != null) {
            for (String fieldName : this.extraHeaders.keySet()) {
                if (!linkedHashMap2.containsKey(fieldName)) {
                    Object put7 = linkedHashMap2.put(fieldName, this.extraHeaders.get(fieldName));
                }
            }
        }
        new StringBuilder();
        String handshake = sb4.append("GET ").append(path2).append(" HTTP/1.1\r\n").toString();
        new StringBuilder();
        String handshake2 = sb5.append(handshake).append(generateHeader(linkedHashMap2)).toString();
        new StringBuilder();
        String handshake3 = sb6.append(handshake2).append(com.google.appinventor.components.runtime.repackaged.org.json.HTTP.CRLF).toString();
        byte[] handshakeBytes = new byte[handshake3.getBytes().length];
        System.arraycopy(handshake3.getBytes(), 0, handshakeBytes, 0, handshake3.getBytes().length);
        return handshakeBytes;
    }

    private String generateHeader(LinkedHashMap<String, String> linkedHashMap) {
        String str;
        StringBuilder sb;
        LinkedHashMap<String, String> headers = linkedHashMap;
        new String();
        String header = str;
        for (String fieldName : headers.keySet()) {
            new StringBuilder();
            header = sb.append(header).append(fieldName).append(": ").append(headers.get(fieldName)).append(com.google.appinventor.components.runtime.repackaged.org.json.HTTP.CRLF).toString();
        }
        return header;
    }

    private String createNonce() {
        byte[] nonce2 = new byte[16];
        for (int i = 0; i < 16; i++) {
            nonce2[i] = (byte) rand(0, 255);
        }
        return Base64.encodeToString(nonce2, false);
    }

    public void verifyServerStatusLine(String statusLine) {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        Throwable th3;
        int statusCode = Integer.valueOf(statusLine.substring(9, 12)).intValue();
        if (statusCode == 407) {
            Throwable th4 = th3;
            new WebSocketException("connection failed: proxy authentication not supported");
            throw th4;
        } else if (statusCode == 404) {
            Throwable th5 = th2;
            new WebSocketException("connection failed: 404 not found");
            throw th5;
        } else if (statusCode != 101) {
            Throwable th6 = th;
            new StringBuilder();
            new WebSocketException(sb.append("connection failed: unknown status code ").append(statusCode).toString());
            throw th6;
        }
    }

    public void verifyServerHandshakeHeaders(HashMap<String, String> hashMap) {
        Throwable th;
        Throwable th2;
        HashMap<String, String> headers = hashMap;
        if (!headers.get("Upgrade").toLowerCase(Locale.US).equals("websocket")) {
            Throwable th3 = th2;
            new WebSocketException("connection failed: missing header field in server handshake: Upgrade");
            throw th3;
        } else if (!headers.get(HTTP.CONN_DIRECTIVE).toLowerCase(Locale.US).equals("upgrade")) {
            Throwable th4 = th;
            new WebSocketException("connection failed: missing header field in server handshake: Connection");
            throw th4;
        }
    }

    private int rand(int min, int max) {
        return (int) ((Math.random() * ((double) max)) + ((double) min));
    }
}

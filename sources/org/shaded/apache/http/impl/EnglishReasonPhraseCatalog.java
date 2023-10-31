package org.shaded.apache.http.impl;

import java.util.Locale;
import org.shaded.apache.http.HttpStatus;
import org.shaded.apache.http.ReasonPhraseCatalog;

public class EnglishReasonPhraseCatalog implements ReasonPhraseCatalog {
    public static final EnglishReasonPhraseCatalog INSTANCE;
    private static final String[][] REASON_PHRASES;

    static {
        EnglishReasonPhraseCatalog englishReasonPhraseCatalog;
        new EnglishReasonPhraseCatalog();
        INSTANCE = englishReasonPhraseCatalog;
        String[][] strArr = new String[6][];
        strArr[0] = null;
        String[][] strArr2 = strArr;
        strArr2[1] = new String[3];
        String[][] strArr3 = strArr2;
        strArr3[2] = new String[8];
        String[][] strArr4 = strArr3;
        strArr4[3] = new String[8];
        String[][] strArr5 = strArr4;
        strArr5[4] = new String[25];
        String[][] strArr6 = strArr5;
        strArr6[5] = new String[8];
        REASON_PHRASES = strArr6;
        setReason(200, "OK");
        setReason(201, "Created");
        setReason(202, "Accepted");
        setReason(HttpStatus.SC_NO_CONTENT, "No Content");
        setReason(301, "Moved Permanently");
        setReason(302, "Moved Temporarily");
        setReason(304, "Not Modified");
        setReason(HttpStatus.SC_BAD_REQUEST, "Bad Request");
        setReason(401, "Unauthorized");
        setReason(403, "Forbidden");
        setReason(404, "Not Found");
        setReason(HttpStatus.SC_INTERNAL_SERVER_ERROR, "Internal Server Error");
        setReason(501, "Not Implemented");
        setReason(502, "Bad Gateway");
        setReason(503, "Service Unavailable");
        setReason(100, "Continue");
        setReason(307, "Temporary Redirect");
        setReason(405, "Method Not Allowed");
        setReason(409, "Conflict");
        setReason(412, "Precondition Failed");
        setReason(413, "Request Too Long");
        setReason(414, "Request-URI Too Long");
        setReason(415, "Unsupported Media Type");
        setReason(HttpStatus.SC_MULTIPLE_CHOICES, "Multiple Choices");
        setReason(303, "See Other");
        setReason(305, "Use Proxy");
        setReason(402, "Payment Required");
        setReason(406, "Not Acceptable");
        setReason(407, "Proxy Authentication Required");
        setReason(408, "Request Timeout");
        setReason(101, "Switching Protocols");
        setReason(HttpStatus.SC_NON_AUTHORITATIVE_INFORMATION, "Non Authoritative Information");
        setReason(HttpStatus.SC_RESET_CONTENT, "Reset Content");
        setReason(HttpStatus.SC_PARTIAL_CONTENT, "Partial Content");
        setReason(504, "Gateway Timeout");
        setReason(505, "Http Version Not Supported");
        setReason(410, "Gone");
        setReason(411, "Length Required");
        setReason(416, "Requested Range Not Satisfiable");
        setReason(417, "Expectation Failed");
        setReason(102, "Processing");
        setReason(HttpStatus.SC_MULTI_STATUS, "Multi-Status");
        setReason(HttpStatus.SC_UNPROCESSABLE_ENTITY, "Unprocessable Entity");
        setReason(419, "Insufficient Space On Resource");
        setReason(HttpStatus.SC_METHOD_FAILURE, "Method Failure");
        setReason(HttpStatus.SC_LOCKED, "Locked");
        setReason(507, "Insufficient Storage");
        setReason(HttpStatus.SC_FAILED_DEPENDENCY, "Failed Dependency");
    }

    protected EnglishReasonPhraseCatalog() {
    }

    public String getReason(int i, Locale locale) {
        Throwable th;
        StringBuffer stringBuffer;
        int status = i;
        Locale locale2 = locale;
        if (status < 100 || status >= 600) {
            Throwable th2 = th;
            new StringBuffer();
            new IllegalArgumentException(stringBuffer.append("Unknown category for status code ").append(status).append(".").toString());
            throw th2;
        }
        int category = status / 100;
        int subcode = status - (100 * category);
        String reason = null;
        if (REASON_PHRASES[category].length > subcode) {
            reason = REASON_PHRASES[category][subcode];
        }
        return reason;
    }

    private static void setReason(int i, String reason) {
        int status = i;
        int category = status / 100;
        REASON_PHRASES[category][status - (100 * category)] = reason;
    }
}

package com.shaded.fasterxml.jackson.core;

public final class Base64Variants {
    public static final Base64Variant MIME;
    public static final Base64Variant MIME_NO_LINEFEEDS;
    public static final Base64Variant MODIFIED_FOR_URL;
    public static final Base64Variant PEM;
    static final String STD_BASE64_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    public Base64Variants() {
    }

    static {
        Base64Variant base64Variant;
        Base64Variant base64Variant2;
        Base64Variant base64Variant3;
        StringBuffer stringBuffer;
        Base64Variant base64Variant4;
        new Base64Variant("MIME", STD_BASE64_ALPHABET, true, '=', 76);
        MIME = base64Variant;
        new Base64Variant(MIME, "MIME-NO-LINEFEEDS", Integer.MAX_VALUE);
        MIME_NO_LINEFEEDS = base64Variant2;
        new Base64Variant(MIME, "PEM", true, '=', 64);
        PEM = base64Variant3;
        new StringBuffer(STD_BASE64_ALPHABET);
        StringBuffer stringBuffer2 = stringBuffer;
        stringBuffer2.setCharAt(stringBuffer2.indexOf("+"), '-');
        stringBuffer2.setCharAt(stringBuffer2.indexOf("/"), '_');
        new Base64Variant("MODIFIED-FOR-URL", stringBuffer2.toString(), false, 0, Integer.MAX_VALUE);
        MODIFIED_FOR_URL = base64Variant4;
    }

    public static Base64Variant getDefaultVariant() {
        return MIME_NO_LINEFEEDS;
    }

    public static Base64Variant valueOf(String str) throws IllegalArgumentException {
        StringBuilder sb;
        String sb2;
        Throwable th;
        StringBuilder sb3;
        String str2 = str;
        if (MIME._name.equals(str2)) {
            return MIME;
        }
        if (MIME_NO_LINEFEEDS._name.equals(str2)) {
            return MIME_NO_LINEFEEDS;
        }
        if (PEM._name.equals(str2)) {
            return PEM;
        }
        if (MODIFIED_FOR_URL._name.equals(str2)) {
            return MODIFIED_FOR_URL;
        }
        if (str2 == null) {
            sb2 = "<null>";
        } else {
            new StringBuilder();
            sb2 = sb.append("'").append(str2).append("'").toString();
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalArgumentException(sb3.append("No Base64Variant with name ").append(sb2).toString());
        throw th2;
    }
}

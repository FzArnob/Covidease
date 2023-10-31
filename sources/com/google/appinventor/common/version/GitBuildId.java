package com.google.appinventor.common.version;

import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;

public final class GitBuildId {
    public static final String ANT_BUILD_DATE = "May 24 2020";
    public static final String GIT_BUILD_FINGERPRINT = "6f0a041f1d87c1e30276cda34a153e3de8990eae";
    public static final String GIT_BUILD_VERSION = "1.4C.4-Eagle";

    private GitBuildId() {
    }

    public static String getVersion() {
        String str = GIT_BUILD_VERSION;
        String str2 = str;
        if (str == "" || str2.contains(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR)) {
            return "none";
        }
        return str2;
    }

    public static String getFingerprint() {
        return GIT_BUILD_FINGERPRINT;
    }

    public static String getDate() {
        return ANT_BUILD_DATE;
    }
}

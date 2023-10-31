package com.firebase.client;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class FirebaseError {
    public static final int AUTHENTICATION_PROVIDER_DISABLED = -12;
    public static final int DATA_STALE = -1;
    public static final int DENIED_BY_USER = -19;
    public static final int DISCONNECTED = -4;
    public static final int EMAIL_TAKEN = -18;
    public static final int EXPIRED_TOKEN = -6;
    public static final int INVALID_AUTH_ARGUMENTS = -21;
    public static final int INVALID_CONFIGURATION = -13;
    public static final int INVALID_CREDENTIALS = -20;
    public static final int INVALID_EMAIL = -15;
    public static final int INVALID_PASSWORD = -16;
    public static final int INVALID_PROVIDER = -14;
    public static final int INVALID_TOKEN = -7;
    public static final int LIMITS_EXCEEDED = -23;
    public static final int MAX_RETRIES = -8;
    public static final int NETWORK_ERROR = -24;
    public static final int OPERATION_FAILED = -2;
    public static final int OVERRIDDEN_BY_SET = -9;
    public static final int PERMISSION_DENIED = -3;
    public static final int PREEMPTED = -5;
    public static final int PROVIDER_ERROR = -22;
    public static final int UNAVAILABLE = -10;
    public static final int UNKNOWN_ERROR = -999;
    public static final int USER_CODE_EXCEPTION = -11;
    public static final int USER_DOES_NOT_EXIST = -17;
    public static final int WRITE_CANCELED = -25;
    private static final Map<String, Integer> errorCodes;
    private static final Map<Integer, String> errorReasons;
    private final int code;
    private final String details;
    private final String message;

    static {
        Map<Integer, String> map;
        Map<String, Integer> map2;
        new HashMap();
        errorReasons = map;
        String put = errorReasons.put(-1, "The transaction needs to be run again with current data");
        String put2 = errorReasons.put(-2, "The server indicated that this operation failed");
        String put3 = errorReasons.put(-3, "This client does not have permission to perform this operation");
        String put4 = errorReasons.put(-4, "The operation had to be aborted due to a network disconnect");
        String put5 = errorReasons.put(-5, "The active or pending auth credentials were superseded by another call to auth");
        String put6 = errorReasons.put(-6, "The supplied auth token has expired");
        String put7 = errorReasons.put(-7, "The supplied auth token was invalid");
        String put8 = errorReasons.put(-8, "The transaction had too many retries");
        String put9 = errorReasons.put(-9, "The transaction was overridden by a subsequent set");
        String put10 = errorReasons.put(-10, "The service is unavailable");
        String put11 = errorReasons.put(-11, "User code called from the Firebase runloop threw an exception:\n");
        String put12 = errorReasons.put(-12, "The specified authentication type is not enabled for this Firebase.");
        String put13 = errorReasons.put(-13, "The specified authentication type is not properly configured for this Firebase.");
        String put14 = errorReasons.put(-14, "Invalid provider specified, please check application code.");
        String put15 = errorReasons.put(-15, "The specified email address is incorrect.");
        String put16 = errorReasons.put(-16, "The specified password is incorrect.");
        String put17 = errorReasons.put(-17, "The specified user does not exist.");
        String put18 = errorReasons.put(-18, "The specified email address is already in use.");
        String put19 = errorReasons.put(-19, "User denied authentication request.");
        String put20 = errorReasons.put(-20, "Invalid authentication credentials provided.");
        String put21 = errorReasons.put(-21, "Invalid authentication arguments provided.");
        String put22 = errorReasons.put(-22, "A third-party provider error occurred. See data for details.");
        String put23 = errorReasons.put(-23, "Limits exceeded.");
        String put24 = errorReasons.put(-24, "The operation could not be performed due to a network error");
        String put25 = errorReasons.put(-25, "The write was canceled by the user.");
        String put26 = errorReasons.put(Integer.valueOf(UNKNOWN_ERROR), "An unknown error occurred");
        new HashMap();
        errorCodes = map2;
        Integer put27 = errorCodes.put("datastale", -1);
        Integer put28 = errorCodes.put("failure", -2);
        Integer put29 = errorCodes.put("permission_denied", -3);
        Integer put30 = errorCodes.put("disconnected", -4);
        Integer put31 = errorCodes.put("preempted", -5);
        Integer put32 = errorCodes.put("expired_token", -6);
        Integer put33 = errorCodes.put("invalid_token", -7);
        Integer put34 = errorCodes.put("maxretries", -8);
        Integer put35 = errorCodes.put("overriddenbyset", -9);
        Integer put36 = errorCodes.put("unavailable", -10);
        Integer put37 = errorCodes.put("authentication_disabled", -12);
        Integer put38 = errorCodes.put("invalid_configuration", -13);
        Integer put39 = errorCodes.put("invalid_provider", -14);
        Integer put40 = errorCodes.put("invalid_email", -15);
        Integer put41 = errorCodes.put("invalid_password", -16);
        Integer put42 = errorCodes.put("invalid_user", -17);
        Integer put43 = errorCodes.put("email_taken", -18);
        Integer put44 = errorCodes.put("user_denied", -19);
        Integer put45 = errorCodes.put("invalid_credentials", -20);
        Integer put46 = errorCodes.put("invalid_arguments", -21);
        Integer put47 = errorCodes.put("provider_error", -22);
        Integer put48 = errorCodes.put("limits_exceeded", -23);
        Integer put49 = errorCodes.put("network_error", -24);
        Integer put50 = errorCodes.put("write_canceled", -25);
    }

    public static FirebaseError fromStatus(String status) {
        return fromStatus(status, (String) null);
    }

    public static FirebaseError fromStatus(String status, String reason) {
        return fromStatus(status, reason, (String) null);
    }

    public static FirebaseError fromCode(int i) {
        FirebaseError firebaseError;
        Throwable th;
        StringBuilder sb;
        int code2 = i;
        if (!errorReasons.containsKey(Integer.valueOf(code2))) {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Invalid Firebase error code: ").append(code2).toString());
            throw th2;
        }
        new FirebaseError(code2, errorReasons.get(Integer.valueOf(code2)), (String) null);
        return firebaseError;
    }

    public static FirebaseError fromStatus(String status, String str, String str2) {
        FirebaseError firebaseError;
        String reason = str;
        String details2 = str2;
        Integer code2 = errorCodes.get(status.toLowerCase());
        if (code2 == null) {
            code2 = Integer.valueOf(UNKNOWN_ERROR);
        }
        new FirebaseError(code2.intValue(), reason == null ? errorReasons.get(code2) : reason, details2);
        return firebaseError;
    }

    public static FirebaseError fromException(Throwable e) {
        StringWriter stringWriter;
        PrintWriter printWriter;
        StringBuilder sb;
        FirebaseError firebaseError;
        new StringWriter();
        StringWriter stringWriter2 = stringWriter;
        new PrintWriter(stringWriter2);
        e.printStackTrace(printWriter);
        new StringBuilder();
        new FirebaseError(-11, sb.append(errorReasons.get(-11)).append(stringWriter2.toString()).toString());
        return firebaseError;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public FirebaseError(int code2, String message2) {
        this(code2, message2, (String) null);
    }

    public FirebaseError(int code2, String message2, String str) {
        String details2 = str;
        this.code = code2;
        this.message = message2;
        this.details = details2 == null ? "" : details2;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getDetails() {
        return this.details;
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("FirebaseError: ").append(this.message).toString();
    }

    public FirebaseException toException() {
        FirebaseException firebaseException;
        StringBuilder sb;
        new StringBuilder();
        new FirebaseException(sb.append("Firebase error: ").append(this.message).toString());
        return firebaseException;
    }
}

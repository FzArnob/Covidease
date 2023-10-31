package com.google.appinventor.components.runtime.errors;

public class PermissionException extends RuntimeException {
    private final String YQI5hXs1ki1Gt2dyWmAO3c8cFflQM1gXzmfpCfdnOt4rmm8mM6xAUYqDPQ7w1Aty;

    public PermissionException(String str) {
        this.YQI5hXs1ki1Gt2dyWmAO3c8cFflQM1gXzmfpCfdnOt4rmm8mM6xAUYqDPQ7w1Aty = str;
    }

    public String getPermissionNeeded() {
        return this.YQI5hXs1ki1Gt2dyWmAO3c8cFflQM1gXzmfpCfdnOt4rmm8mM6xAUYqDPQ7w1Aty;
    }

    public String getMessage() {
        StringBuilder sb;
        new StringBuilder("Unable to complete the operation because the user denied permission: ");
        return sb.append(this.YQI5hXs1ki1Gt2dyWmAO3c8cFflQM1gXzmfpCfdnOt4rmm8mM6xAUYqDPQ7w1Aty).toString();
    }
}

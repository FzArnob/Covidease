package com.firebase.client.authentication;

public class Constants {
    public static final String FIREBASE_AUTH_ANONYMOUS_PATH = "/auth/anonymous";
    public static final String FIREBASE_AUTH_CREATE_USER_PATH = "/users";
    public static final String FIREBASE_AUTH_DEFAULT_API_HOST = "https://auth.firebase.com/";
    public static final String FIREBASE_AUTH_EMAIL_PATH_FORMAT = "/users/%s/email";
    public static final String FIREBASE_AUTH_PASSWORD_PATH = "/auth/password";
    public static final String FIREBASE_AUTH_PASSWORD_PATH_FORMAT = "/users/%s/password";
    public static final String FIREBASE_AUTH_PROVIDER_PATH_FORMAT = "/auth/%s/token";
    public static final String FIREBASE_AUTH_REMOVE_USER_PATH_FORMAT = "/users/%s";

    public Constants() {
    }
}

package com.firebase.client;

public interface CredentialStore {
    boolean clearCredential(String str, String str2);

    String loadCredential(String str, String str2);

    boolean storeCredential(String str, String str2, String str3);
}

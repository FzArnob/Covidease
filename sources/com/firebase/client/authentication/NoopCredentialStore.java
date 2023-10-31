package com.firebase.client.authentication;

import com.firebase.client.CredentialStore;
import com.firebase.client.core.Context;
import com.firebase.client.utilities.LogWrapper;

public class NoopCredentialStore implements CredentialStore {
    private final LogWrapper logger;

    public NoopCredentialStore(Context context) {
        this.logger = context.getLogger("CredentialStore");
    }

    public String loadCredential(String str, String str2) {
        String str3 = str;
        String str4 = str2;
        return null;
    }

    public boolean storeCredential(String str, String str2, String str3) {
        String str4 = str;
        String str5 = str2;
        String str6 = str3;
        this.logger.warn("Using no-op credential store. Not persisting credentials! If you want to persist authentication, please use a custom implementation of CredentialStore.");
        return true;
    }

    public boolean clearCredential(String str, String str2) {
        String str3 = str;
        String str4 = str2;
        return true;
    }
}

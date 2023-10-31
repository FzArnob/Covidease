package com.firebase.client.android;

import android.content.Context;
import android.content.SharedPreferences;
import com.firebase.client.CredentialStore;

public class AndroidCredentialStore implements CredentialStore {
    private static final String ANDROID_SHARED_PREFERENCE_NAME = "com.firebase.authentication.credentials";
    private final SharedPreferences sharedPreferences;

    public AndroidCredentialStore(Context context) {
        this.sharedPreferences = context.getSharedPreferences(ANDROID_SHARED_PREFERENCE_NAME, 0);
    }

    private String buildKey(String firebaseId, String sessionId) {
        StringBuilder sb;
        new StringBuilder();
        return sb.append(firebaseId).append("/").append(sessionId).toString();
    }

    public String loadCredential(String firebaseId, String sessionId) {
        return this.sharedPreferences.getString(buildKey(firebaseId, sessionId), (String) null);
    }

    public boolean storeCredential(String firebaseId, String sessionId, String credential) {
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        SharedPreferences.Editor putString = editor.putString(buildKey(firebaseId, sessionId), credential);
        return editor.commit();
    }

    public boolean clearCredential(String firebaseId, String sessionId) {
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        SharedPreferences.Editor remove = editor.remove(buildKey(firebaseId, sessionId));
        return editor.commit();
    }
}

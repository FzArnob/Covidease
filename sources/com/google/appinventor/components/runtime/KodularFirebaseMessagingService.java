package com.google.appinventor.components.runtime;

import android.util.Log;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

@UsesLibraries(libraries = "firebase-messaging.jar, firebase-messaging.aar")
public class KodularFirebaseMessagingService extends FirebaseMessagingService {
    public KodularFirebaseMessagingService() {
    }

    public void onMessageReceived(RemoteMessage remoteMessage) {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        RemoteMessage remoteMessage2 = remoteMessage;
        KodularFirebaseMessagingService.super.onMessageReceived(remoteMessage2);
        new StringBuilder("From: ");
        int d = Log.d("KodularMessagingService", sb.append(remoteMessage2.getFrom()).toString());
        if (remoteMessage2.getData().size() > 0) {
            new StringBuilder("Message data payload: ");
            int d2 = Log.d("KodularMessagingService", sb3.append(remoteMessage2.getData()).toString());
        }
        if (remoteMessage2.getNotification() != null) {
            new StringBuilder("Message Notification Body: ");
            int d3 = Log.d("KodularMessagingService", sb2.append(remoteMessage2.getNotification().getBody()).toString());
        }
    }
}

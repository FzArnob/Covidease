package com.firebase.client.core;

import com.firebase.client.CredentialStore;
import com.firebase.client.EventTarget;
import com.firebase.client.Logger;
import com.firebase.client.RunLoop;
import com.firebase.client.core.persistence.PersistenceManager;
import java.util.List;

public interface Platform {
    PersistenceManager createPersistenceManager(Context context, String str);

    String getPlatformVersion();

    String getUserAgent(Context context);

    CredentialStore newCredentialStore(Context context);

    EventTarget newEventTarget(Context context);

    Logger newLogger(Context context, Logger.Level level, List<String> list);

    RunLoop newRunLoop(Context context);

    void runBackgroundTask(Context context, Runnable runnable);
}

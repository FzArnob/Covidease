package com.firebase.client;

import com.firebase.client.core.Repo;
import com.firebase.client.core.RepoManager;

public class FirebaseApp {
    /* access modifiers changed from: private */
    public final Repo repo;

    protected FirebaseApp(Repo repo2) {
        this.repo = repo2;
    }

    public void purgeOutstandingWrites() {
        Runnable runnable;
        new Runnable(this) {
            final /* synthetic */ FirebaseApp this$0;

            {
                this.this$0 = r5;
            }

            public void run() {
                this.this$0.repo.purgeOutstandingWrites();
            }
        };
        this.repo.scheduleNow(runnable);
    }

    public void goOnline() {
        RepoManager.resume(this.repo);
    }

    public void goOffline() {
        RepoManager.interrupt(this.repo);
    }
}

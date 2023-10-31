package com.firebase.client.core;

import com.firebase.client.FirebaseException;
import com.firebase.client.RunLoop;
import java.util.HashMap;
import java.util.Map;

public class RepoManager {
    private static final RepoManager instance;
    /* access modifiers changed from: private */
    public final Map<Context, Map<String, Repo>> repos;

    static {
        RepoManager repoManager;
        new RepoManager();
        instance = repoManager;
    }

    public static Repo getRepo(Context ctx, RepoInfo info) throws FirebaseException {
        return instance.getLocalRepo(ctx, info);
    }

    public static void interrupt(Context ctx) {
        instance.interruptInternal(ctx);
    }

    public static void interrupt(Repo repo) {
        Runnable runnable;
        Repo repo2 = repo;
        final Repo repo3 = repo2;
        new Runnable() {
            public void run() {
                repo3.interrupt();
            }
        };
        repo2.scheduleNow(runnable);
    }

    public static void resume(Repo repo) {
        Runnable runnable;
        Repo repo2 = repo;
        final Repo repo3 = repo2;
        new Runnable() {
            public void run() {
                repo3.resume();
            }
        };
        repo2.scheduleNow(runnable);
    }

    public static void resume(Context ctx) {
        instance.resumeInternal(ctx);
    }

    public RepoManager() {
        Map<Context, Map<String, Repo>> map;
        new HashMap();
        this.repos = map;
    }

    /* JADX INFO: finally extract failed */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v30, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: com.firebase.client.core.Repo} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.firebase.client.core.Repo getLocalRepo(com.firebase.client.core.Context r14, com.firebase.client.core.RepoInfo r15) throws com.firebase.client.FirebaseException {
        /*
            r13 = this;
            r0 = r13
            r1 = r14
            r2 = r15
            r8 = r1
            r8.freeze()
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r12 = r8
            r8 = r12
            r9 = r12
            r9.<init>()
            java.lang.String r9 = "https://"
            java.lang.StringBuilder r8 = r8.append(r9)
            r9 = r2
            java.lang.String r9 = r9.host
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r9 = "/"
            java.lang.StringBuilder r8 = r8.append(r9)
            r9 = r2
            java.lang.String r9 = r9.namespace
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r8 = r8.toString()
            r3 = r8
            r8 = r0
            java.util.Map<com.firebase.client.core.Context, java.util.Map<java.lang.String, com.firebase.client.core.Repo>> r8 = r8.repos
            r12 = r8
            r8 = r12
            r9 = r12
            r4 = r9
            monitor-enter(r8)
            r8 = r0
            java.util.Map<com.firebase.client.core.Context, java.util.Map<java.lang.String, com.firebase.client.core.Repo>> r8 = r8.repos     // Catch:{ all -> 0x008a }
            r9 = r1
            boolean r8 = r8.containsKey(r9)     // Catch:{ all -> 0x008a }
            if (r8 != 0) goto L_0x0054
            java.util.HashMap r8 = new java.util.HashMap     // Catch:{ all -> 0x008a }
            r12 = r8
            r8 = r12
            r9 = r12
            r9.<init>()     // Catch:{ all -> 0x008a }
            r5 = r8
            r8 = r0
            java.util.Map<com.firebase.client.core.Context, java.util.Map<java.lang.String, com.firebase.client.core.Repo>> r8 = r8.repos     // Catch:{ all -> 0x008a }
            r9 = r1
            r10 = r5
            java.lang.Object r8 = r8.put(r9, r10)     // Catch:{ all -> 0x008a }
        L_0x0054:
            r8 = r0
            java.util.Map<com.firebase.client.core.Context, java.util.Map<java.lang.String, com.firebase.client.core.Repo>> r8 = r8.repos     // Catch:{ all -> 0x008a }
            r9 = r1
            java.lang.Object r8 = r8.get(r9)     // Catch:{ all -> 0x008a }
            java.util.Map r8 = (java.util.Map) r8     // Catch:{ all -> 0x008a }
            r5 = r8
            r8 = r5
            r9 = r3
            boolean r8 = r8.containsKey(r9)     // Catch:{ all -> 0x008a }
            if (r8 != 0) goto L_0x007e
            com.firebase.client.core.Repo r8 = new com.firebase.client.core.Repo     // Catch:{ all -> 0x008a }
            r12 = r8
            r8 = r12
            r9 = r12
            r10 = r2
            r11 = r1
            r9.<init>(r10, r11)     // Catch:{ all -> 0x008a }
            r6 = r8
            r8 = r5
            r9 = r3
            r10 = r6
            java.lang.Object r8 = r8.put(r9, r10)     // Catch:{ all -> 0x008a }
            r8 = r6
            r9 = r4
            monitor-exit(r9)     // Catch:{ all -> 0x008a }
            r0 = r8
        L_0x007d:
            return r0
        L_0x007e:
            r8 = r5
            r9 = r3
            java.lang.Object r8 = r8.get(r9)     // Catch:{ all -> 0x008a }
            com.firebase.client.core.Repo r8 = (com.firebase.client.core.Repo) r8     // Catch:{ all -> 0x008a }
            r9 = r4
            monitor-exit(r9)     // Catch:{ all -> 0x008a }
            r0 = r8
            goto L_0x007d
        L_0x008a:
            r8 = move-exception
            r7 = r8
            r8 = r4
            monitor-exit(r8)     // Catch:{ all -> 0x008a }
            r8 = r7
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.firebase.client.core.RepoManager.getLocalRepo(com.firebase.client.core.Context, com.firebase.client.core.RepoInfo):com.firebase.client.core.Repo");
    }

    private void interruptInternal(Context context) {
        Runnable runnable;
        Context ctx = context;
        RunLoop runLoop = ctx.getRunLoop();
        if (runLoop != null) {
            final Context context2 = ctx;
            new Runnable(this) {
                final /* synthetic */ RepoManager this$0;

                {
                    this.this$0 = r6;
                }

                /* JADX INFO: finally extract failed */
                public void run() {
                    Map access$000 = this.this$0.repos;
                    Map map = access$000;
                    synchronized (access$000) {
                        boolean allEmpty = true;
                        try {
                            if (this.this$0.repos.containsKey(context2)) {
                                for (Repo repo : ((Map) this.this$0.repos.get(context2)).values()) {
                                    repo.interrupt();
                                    allEmpty = allEmpty && !repo.hasListeners();
                                }
                                if (allEmpty) {
                                    context2.stop();
                                }
                            }
                        } catch (Throwable th) {
                            Throwable th2 = th;
                            Map map2 = map;
                            throw th2;
                        }
                    }
                }
            };
            runLoop.scheduleNow(runnable);
        }
    }

    private void resumeInternal(Context context) {
        Runnable runnable;
        Context ctx = context;
        RunLoop runLoop = ctx.getRunLoop();
        if (runLoop != null) {
            final Context context2 = ctx;
            new Runnable(this) {
                final /* synthetic */ RepoManager this$0;

                {
                    this.this$0 = r6;
                }

                /* JADX INFO: finally extract failed */
                public void run() {
                    Map access$000 = this.this$0.repos;
                    Map map = access$000;
                    synchronized (access$000) {
                        try {
                            if (this.this$0.repos.containsKey(context2)) {
                                for (Repo repo : ((Map) this.this$0.repos.get(context2)).values()) {
                                    repo.resume();
                                }
                            }
                        } catch (Throwable th) {
                            Throwable th2 = th;
                            Map map2 = map;
                            throw th2;
                        }
                    }
                }
            };
            runLoop.scheduleNow(runnable);
        }
    }
}

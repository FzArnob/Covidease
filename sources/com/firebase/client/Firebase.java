package com.firebase.client;

import com.firebase.client.Transaction;
import com.firebase.client.authentication.AuthenticationManager;
import com.firebase.client.core.CompoundWrite;
import com.firebase.client.core.Context;
import com.firebase.client.core.Path;
import com.firebase.client.core.Repo;
import com.firebase.client.core.RepoManager;
import com.firebase.client.core.ValidationPath;
import com.firebase.client.snapshot.ChildKey;
import com.firebase.client.snapshot.Node;
import com.firebase.client.snapshot.NodeUtilities;
import com.firebase.client.snapshot.PriorityUtilities;
import com.firebase.client.utilities.ParsedUrl;
import com.firebase.client.utilities.PushIdGenerator;
import com.firebase.client.utilities.Utilities;
import com.firebase.client.utilities.Validation;
import com.firebase.client.utilities.encoding.JsonHelpers;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class Firebase extends Query {
    private static Config defaultConfig;

    @Deprecated
    public interface AuthListener {
        void onAuthError(FirebaseError firebaseError);

        void onAuthRevoked(FirebaseError firebaseError);

        void onAuthSuccess(Object obj);
    }

    public interface AuthResultHandler {
        void onAuthenticated(AuthData authData);

        void onAuthenticationError(FirebaseError firebaseError);
    }

    public interface AuthStateListener {
        void onAuthStateChanged(AuthData authData);
    }

    public interface CompletionListener {
        void onComplete(FirebaseError firebaseError, Firebase firebase);
    }

    public interface ResultHandler {
        void onError(FirebaseError firebaseError);

        void onSuccess();
    }

    public interface ValueResultHandler<T> {
        void onError(FirebaseError firebaseError);

        void onSuccess(T t);
    }

    private AuthenticationManager getAuthenticationManager() {
        return getRepo().getAuthenticationManager();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public Firebase(String url) {
        this(Utilities.parseUrl(url));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Firebase(Repo repo, Path path) {
        super(repo, path);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    Firebase(String url, Config config) {
        this(Utilities.parseUrl(url), config);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private Firebase(com.firebase.client.utilities.ParsedUrl r9, com.firebase.client.Config r10) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r10
            r3 = r0
            r4 = r2
            r5 = r1
            com.firebase.client.core.RepoInfo r5 = r5.repoInfo
            com.firebase.client.core.Repo r4 = com.firebase.client.core.RepoManager.getRepo(r4, r5)
            r5 = r1
            com.firebase.client.core.Path r5 = r5.path
            com.firebase.client.core.view.QueryParams r6 = com.firebase.client.core.view.QueryParams.DEFAULT_PARAMS
            r7 = 0
            r3.<init>(r4, r5, r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.firebase.client.Firebase.<init>(com.firebase.client.utilities.ParsedUrl, com.firebase.client.Config):void");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    private Firebase(ParsedUrl parsedUrl) {
        this(parsedUrl, getDefaultConfig());
    }

    public Firebase child(String str) {
        Path path;
        Firebase firebase;
        Throwable th;
        String pathString = str;
        if (pathString == null) {
            Throwable th2 = th;
            new NullPointerException("Can't pass null for argument 'pathString' in child()");
            throw th2;
        }
        if (getPath().isEmpty()) {
            Validation.validateRootPathString(pathString);
        } else {
            Validation.validatePathString(pathString);
        }
        new Path(pathString);
        new Firebase(this.repo, getPath().child(path));
        return firebase;
    }

    public Firebase push() {
        Firebase firebase;
        new Firebase(this.repo, getPath().child(ChildKey.fromString(PushIdGenerator.generatePushChildName(this.repo.getServerTime()))));
        return firebase;
    }

    public void setValue(Object value) {
        setValueInternal(value, PriorityUtilities.parsePriority((Object) null), (CompletionListener) null);
    }

    public void setValue(Object value, Object priority) {
        setValueInternal(value, PriorityUtilities.parsePriority(priority), (CompletionListener) null);
    }

    public void setValue(Object value, CompletionListener listener) {
        setValueInternal(value, PriorityUtilities.parsePriority((Object) null), listener);
    }

    public void setValue(Object value, Object priority, CompletionListener listener) {
        setValueInternal(value, PriorityUtilities.parsePriority(priority), listener);
    }

    private void setValueInternal(Object obj, Node node, CompletionListener completionListener) {
        Throwable th;
        Runnable runnable;
        Object value = obj;
        Node priority = node;
        CompletionListener listener = completionListener;
        Validation.validateWritablePath(getPath());
        ValidationPath.validateWithObject(getPath(), value);
        try {
            Object bouncedValue = JsonHelpers.getMapper().convertValue(value, Object.class);
            Validation.validateWritableObject(bouncedValue);
            final Node NodeFromJSON = NodeUtilities.NodeFromJSON(bouncedValue, priority);
            final CompletionListener completionListener2 = listener;
            new Runnable(this) {
                final /* synthetic */ Firebase this$0;

                {
                    this.this$0 = r7;
                }

                public void run() {
                    this.this$0.repo.setValue(this.this$0.getPath(), NodeFromJSON, completionListener2);
                }
            };
            this.repo.scheduleNow(runnable);
        } catch (IllegalArgumentException e) {
            IllegalArgumentException e2 = e;
            Throwable th2 = th;
            new FirebaseException("Failed to parse to snapshot", e2);
            throw th2;
        }
    }

    public void setPriority(Object priority) {
        setPriorityInternal(PriorityUtilities.parsePriority(priority), (CompletionListener) null);
    }

    public void setPriority(Object priority, CompletionListener listener) {
        setPriorityInternal(PriorityUtilities.parsePriority(priority), listener);
    }

    private void setPriorityInternal(Node priority, CompletionListener listener) {
        Runnable runnable;
        Validation.validateWritablePath(getPath());
        final Node node = priority;
        final CompletionListener completionListener = listener;
        new Runnable(this) {
            final /* synthetic */ Firebase this$0;

            {
                this.this$0 = r7;
            }

            public void run() {
                this.this$0.repo.setValue(this.this$0.getPath().child(ChildKey.getPriorityKey()), node, completionListener);
            }
        };
        this.repo.scheduleNow(runnable);
    }

    public void updateChildren(Map<String, Object> update) {
        updateChildren(update, (CompletionListener) null);
    }

    public void updateChildren(Map<String, Object> map, CompletionListener completionListener) {
        Runnable runnable;
        Throwable th;
        Map<String, Object> update = map;
        CompletionListener listener = completionListener;
        if (update == null) {
            Throwable th2 = th;
            new NullPointerException("Can't pass null for argument 'update' in updateChildren()");
            throw th2;
        }
        final CompoundWrite fromPathMerge = CompoundWrite.fromPathMerge(Validation.parseAndValidateUpdate(getPath(), update));
        final CompletionListener completionListener2 = listener;
        final Map<String, Object> map2 = update;
        new Runnable(this) {
            final /* synthetic */ Firebase this$0;

            {
                this.this$0 = r8;
            }

            public void run() {
                this.this$0.repo.updateChildren(this.this$0.getPath(), fromPathMerge, completionListener2, map2);
            }
        };
        this.repo.scheduleNow(runnable);
    }

    public void removeValue() {
        setValue((Object) null);
    }

    public void removeValue(CompletionListener listener) {
        setValue((Object) null, listener);
    }

    public OnDisconnect onDisconnect() {
        OnDisconnect onDisconnect;
        Validation.validateWritablePath(getPath());
        new OnDisconnect(this.repo, getPath());
        return onDisconnect;
    }

    @Deprecated
    public void auth(String str, AuthListener authListener) {
        Throwable th;
        Throwable th2;
        String credential = str;
        AuthListener listener = authListener;
        if (listener == null) {
            Throwable th3 = th2;
            new NullPointerException("Can't pass null for argument 'listener' in auth()");
            throw th3;
        } else if (credential == null) {
            Throwable th4 = th;
            new NullPointerException("Can't pass null for argument 'credential' in auth()");
            throw th4;
        } else {
            getAuthenticationManager().authWithFirebaseToken(credential, listener);
        }
    }

    public void unauth() {
        getAuthenticationManager().unauth();
    }

    @Deprecated
    public void unauth(CompletionListener completionListener) {
        Throwable th;
        CompletionListener listener = completionListener;
        if (listener == null) {
            Throwable th2 = th;
            new NullPointerException("Can't pass null for argument 'listener' in unauth()");
            throw th2;
        }
        getAuthenticationManager().unauth(listener);
    }

    public AuthStateListener addAuthStateListener(AuthStateListener authStateListener) {
        Throwable th;
        AuthStateListener listener = authStateListener;
        if (listener == null) {
            Throwable th2 = th;
            new NullPointerException("Can't pass null for argument 'listener' in addAuthStateListener()");
            throw th2;
        }
        getAuthenticationManager().addAuthStateListener(listener);
        return listener;
    }

    public void removeAuthStateListener(AuthStateListener authStateListener) {
        Throwable th;
        AuthStateListener listener = authStateListener;
        if (listener == null) {
            Throwable th2 = th;
            new NullPointerException("Can't pass null for argument 'listener' in removeAuthStateListener()");
            throw th2;
        }
        getAuthenticationManager().removeAuthStateListener(listener);
    }

    public AuthData getAuth() {
        return getAuthenticationManager().getAuth();
    }

    public void authAnonymously(AuthResultHandler handler) {
        getAuthenticationManager().authAnonymously(handler);
    }

    public void authWithPassword(String str, String str2, AuthResultHandler authResultHandler) {
        Throwable th;
        Throwable th2;
        String email = str;
        String password = str2;
        AuthResultHandler handler = authResultHandler;
        if (email == null) {
            Throwable th3 = th2;
            new NullPointerException("Can't pass null for argument 'email' in authWithPassword()");
            throw th3;
        } else if (password == null) {
            Throwable th4 = th;
            new NullPointerException("Can't pass null for argument 'password' in authWithPassword()");
            throw th4;
        } else {
            getAuthenticationManager().authWithPassword(email, password, handler);
        }
    }

    public void authWithCustomToken(String str, AuthResultHandler authResultHandler) {
        Throwable th;
        String token = str;
        AuthResultHandler handler = authResultHandler;
        if (token == null) {
            Throwable th2 = th;
            new NullPointerException("Can't pass null for argument 'token' in authWithCustomToken()");
            throw th2;
        }
        getAuthenticationManager().authWithCustomToken(token, handler);
    }

    public void authWithOAuthToken(String str, String str2, AuthResultHandler authResultHandler) {
        Throwable th;
        Throwable th2;
        String provider = str;
        String token = str2;
        AuthResultHandler handler = authResultHandler;
        if (provider == null) {
            Throwable th3 = th2;
            new NullPointerException("Can't pass null for argument 'provider' in authWithOAuthToken()");
            throw th3;
        } else if (token == null) {
            Throwable th4 = th;
            new NullPointerException("Can't pass null for argument 'token' in authWithOAuthToken()");
            throw th4;
        } else {
            getAuthenticationManager().authWithOAuthToken(provider, token, handler);
        }
    }

    public void authWithOAuthToken(String str, Map<String, String> map, AuthResultHandler authResultHandler) {
        Throwable th;
        Throwable th2;
        String provider = str;
        Map<String, String> options = map;
        AuthResultHandler handler = authResultHandler;
        if (provider == null) {
            Throwable th3 = th2;
            new NullPointerException("Can't pass null for argument 'provider' in authWithOAuthToken()");
            throw th3;
        } else if (options == null) {
            Throwable th4 = th;
            new NullPointerException("Can't pass null for argument 'options' in authWithOAuthToken()");
            throw th4;
        } else {
            getAuthenticationManager().authWithOAuthToken(provider, options, handler);
        }
    }

    public void createUser(String str, String str2, ResultHandler resultHandler) {
        Throwable th;
        Throwable th2;
        String email = str;
        String password = str2;
        ResultHandler handler = resultHandler;
        if (email == null) {
            Throwable th3 = th2;
            new NullPointerException("Can't pass null for argument 'email' in createUser()");
            throw th3;
        } else if (password == null) {
            Throwable th4 = th;
            new NullPointerException("Can't pass null for argument 'password' in createUser()");
            throw th4;
        } else {
            getAuthenticationManager().createUser(email, password, handler);
        }
    }

    public void createUser(String str, String str2, ValueResultHandler<Map<String, Object>> valueResultHandler) {
        Throwable th;
        Throwable th2;
        String email = str;
        String password = str2;
        ValueResultHandler<Map<String, Object>> handler = valueResultHandler;
        if (email == null) {
            Throwable th3 = th2;
            new NullPointerException("Can't pass null for argument 'email' in createUser()");
            throw th3;
        } else if (password == null) {
            Throwable th4 = th;
            new NullPointerException("Can't pass null for argument 'password' in createUser()");
            throw th4;
        } else {
            getAuthenticationManager().createUser(email, password, handler);
        }
    }

    public void removeUser(String str, String str2, ResultHandler resultHandler) {
        Throwable th;
        Throwable th2;
        String email = str;
        String password = str2;
        ResultHandler handler = resultHandler;
        if (email == null) {
            Throwable th3 = th2;
            new NullPointerException("Can't pass null for argument 'email' in removeUser()");
            throw th3;
        } else if (password == null) {
            Throwable th4 = th;
            new NullPointerException("Can't pass null for argument 'password' in removeUser()");
            throw th4;
        } else {
            getAuthenticationManager().removeUser(email, password, handler);
        }
    }

    public void changePassword(String str, String str2, String str3, ResultHandler resultHandler) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        String email = str;
        String oldPassword = str2;
        String newPassword = str3;
        ResultHandler handler = resultHandler;
        if (email == null) {
            Throwable th4 = th3;
            new NullPointerException("Can't pass null for argument 'email' in changePassword()");
            throw th4;
        } else if (oldPassword == null) {
            Throwable th5 = th2;
            new NullPointerException("Can't pass null for argument 'oldPassword' in changePassword()");
            throw th5;
        } else if (newPassword == null) {
            Throwable th6 = th;
            new NullPointerException("Can't pass null for argument 'newPassword' in changePassword()");
            throw th6;
        } else {
            getAuthenticationManager().changePassword(email, oldPassword, newPassword, handler);
        }
    }

    public void changeEmail(String str, String str2, String str3, ResultHandler resultHandler) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        String oldEmail = str;
        String password = str2;
        String newEmail = str3;
        ResultHandler handler = resultHandler;
        if (oldEmail == null) {
            Throwable th4 = th3;
            new NullPointerException("Can't pass null for argument 'oldEmail' in changeEmail()");
            throw th4;
        } else if (password == null) {
            Throwable th5 = th2;
            new NullPointerException("Can't pass null for argument 'password' in changeEmail()");
            throw th5;
        } else if (newEmail == null) {
            Throwable th6 = th;
            new NullPointerException("Can't pass null for argument 'newEmail' in changeEmail()");
            throw th6;
        } else {
            getAuthenticationManager().changeEmail(oldEmail, password, newEmail, handler);
        }
    }

    public void resetPassword(String str, ResultHandler resultHandler) {
        Throwable th;
        String email = str;
        ResultHandler handler = resultHandler;
        if (email == null) {
            Throwable th2 = th;
            new NullPointerException("Can't pass null for argument 'email' in resetPassword()");
            throw th2;
        }
        getAuthenticationManager().resetPassword(email, handler);
    }

    public void runTransaction(Transaction.Handler handler) {
        runTransaction(handler, true);
    }

    public void runTransaction(Transaction.Handler handler, boolean z) {
        Runnable runnable;
        Throwable th;
        Transaction.Handler handler2 = handler;
        boolean fireLocalEvents = z;
        if (handler2 == null) {
            Throwable th2 = th;
            new NullPointerException("Can't pass null for argument 'handler' in runTransaction()");
            throw th2;
        }
        Validation.validateWritablePath(getPath());
        final Transaction.Handler handler3 = handler2;
        final boolean z2 = fireLocalEvents;
        new Runnable(this) {
            final /* synthetic */ Firebase this$0;

            {
                this.this$0 = r7;
            }

            public void run() {
                this.this$0.repo.startTransaction(this.this$0.getPath(), handler3, z2);
            }
        };
        this.repo.scheduleNow(runnable);
    }

    public static void goOffline() {
        goOffline(getDefaultConfig());
    }

    static void goOffline(Config config) {
        RepoManager.interrupt((Context) config);
    }

    public static void goOnline() {
        goOnline(getDefaultConfig());
    }

    static void goOnline(Config config) {
        RepoManager.resume((Context) config);
    }

    public FirebaseApp getApp() {
        return this.repo.getFirebaseApp();
    }

    public String toString() {
        Throwable th;
        StringBuilder sb;
        StringBuilder sb2;
        Firebase parent = getParent();
        if (parent == null) {
            return this.repo.toString();
        }
        try {
            new StringBuilder();
            return sb2.append(parent.toString()).append("/").append(URLEncoder.encode(getKey(), "UTF-8").replace("+", "%20")).toString();
        } catch (UnsupportedEncodingException e) {
            UnsupportedEncodingException e2 = e;
            Throwable th2 = th;
            new StringBuilder();
            new FirebaseException(sb.append("Failed to URLEncode key: ").append(getKey()).toString(), e2);
            throw th2;
        }
    }

    public Firebase getParent() {
        Firebase firebase;
        Path parentPath = getPath().getParent();
        if (parentPath == null) {
            return null;
        }
        new Firebase(this.repo, parentPath);
        return firebase;
    }

    public Firebase getRoot() {
        Firebase firebase;
        Path path;
        new Path("");
        new Firebase(this.repo, path);
        return firebase;
    }

    public String getKey() {
        if (getPath().isEmpty()) {
            return null;
        }
        return getPath().getBack().asString();
    }

    public boolean equals(Object obj) {
        Object other = obj;
        return (other instanceof Firebase) && toString().equals(other.toString());
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public static String getSdkVersion() {
        return Version.SDK_VERSION;
    }

    /* access modifiers changed from: package-private */
    public void setHijackHash(boolean hijackHash) {
        Runnable runnable;
        final boolean z = hijackHash;
        new Runnable(this) {
            final /* synthetic */ Firebase this$0;

            {
                this.this$0 = r6;
            }

            public void run() {
                this.this$0.repo.setHijackHash(z);
            }
        };
        this.repo.scheduleNow(runnable);
    }

    public static synchronized Config getDefaultConfig() {
        Config config;
        Config config2;
        synchronized (Firebase.class) {
            if (defaultConfig == null) {
                new Config();
                defaultConfig = config2;
            }
            config = defaultConfig;
        }
        return config;
    }

    public static synchronized void setDefaultConfig(Config config) {
        Throwable th;
        Config config2 = config;
        synchronized (Firebase.class) {
            if (defaultConfig == null || !defaultConfig.isFrozen()) {
                defaultConfig = config2;
            } else {
                Throwable th2 = th;
                new FirebaseException("Modifications to Config objects must occur before they are in use");
                throw th2;
            }
        }
    }

    public static void setAndroidContext(android.content.Context context) {
        Throwable th;
        android.content.Context context2 = context;
        if (context2 == null) {
            Throwable th2 = th;
            new NullPointerException("Can't pass null for argument 'context' in setAndroidContext()");
            throw th2;
        }
        Context.setAndroidContext(context2);
    }
}

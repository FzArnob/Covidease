package com.firebase.client.authentication;

import android.support.p000v4.app.NotificationCompat;
import com.firebase.client.AuthData;
import com.firebase.client.CredentialStore;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.authentication.util.JsonWebToken;
import com.firebase.client.core.AuthExpirationBehavior;
import com.firebase.client.core.Context;
import com.firebase.client.core.Path;
import com.firebase.client.core.PersistentConnection;
import com.firebase.client.core.Repo;
import com.firebase.client.core.RepoInfo;
import com.firebase.client.utilities.HttpUtilities;
import com.firebase.client.utilities.LogWrapper;
import com.firebase.client.utilities.Utilities;
import com.firebase.client.utilities.encoding.JsonHelpers;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Semaphore;
import org.shaded.apache.http.client.ResponseHandler;
import org.shaded.apache.http.client.methods.HttpUriRequest;
import org.shaded.apache.http.client.params.ClientPNames;
import org.shaded.apache.http.client.params.CookiePolicy;
import org.shaded.apache.http.cookie.ClientCookie;
import org.shaded.apache.http.impl.client.DefaultHttpClient;
import org.shaded.apache.http.params.BasicHttpParams;
import org.shaded.apache.http.params.HttpConnectionParams;
import org.shaded.apache.http.params.HttpParams;

public class AuthenticationManager {
    static final /* synthetic */ boolean $assertionsDisabled = (!AuthenticationManager.class.desiredAssertionStatus());
    private static final String AUTH_DATA_KEY = "authData";
    private static final int CONNECTION_TIMEOUT = 20000;
    private static final String CUSTOM_PROVIDER = "custom";
    private static final String ERROR_KEY = "error";
    private static final String LOG_TAG = "AuthenticationManager";
    private static final String TOKEN_KEY = "token";
    private static final String USER_DATA_KEY = "userData";
    /* access modifiers changed from: private */
    public AuthData authData = null;
    /* access modifiers changed from: private */
    public final PersistentConnection connection;
    private final Context context;
    private AuthAttempt currentAuthAttempt;
    /* access modifiers changed from: private */
    public final Set<Firebase.AuthStateListener> listenerSet;
    /* access modifiers changed from: private */
    public final LogWrapper logger;
    /* access modifiers changed from: private */
    public final Repo repo;
    private final RepoInfo repoInfo;
    private final CredentialStore store;

    private class AuthAttempt {
        /* access modifiers changed from: private */
        public Firebase.AuthResultHandler handler;
        /* access modifiers changed from: private */
        public final Firebase.AuthListener legacyListener;
        final /* synthetic */ AuthenticationManager this$0;

        static /* synthetic */ Firebase.AuthResultHandler access$102(AuthAttempt x0, Firebase.AuthResultHandler x1) {
            Firebase.AuthResultHandler authResultHandler = x1;
            Firebase.AuthResultHandler authResultHandler2 = authResultHandler;
            x0.handler = authResultHandler2;
            return authResultHandler;
        }

        AuthAttempt(AuthenticationManager authenticationManager, Firebase.AuthResultHandler handler2) {
            this.this$0 = authenticationManager;
            this.handler = handler2;
            this.legacyListener = null;
        }

        AuthAttempt(AuthenticationManager authenticationManager, Firebase.AuthListener legacyListener2) {
            this.this$0 = authenticationManager;
            this.legacyListener = legacyListener2;
            this.handler = null;
        }

        public void fireError(FirebaseError firebaseError) {
            Runnable runnable;
            FirebaseError error = firebaseError;
            if (this.legacyListener != null || this.handler != null) {
                final FirebaseError firebaseError2 = error;
                new Runnable(this) {
                    final /* synthetic */ AuthAttempt this$1;

                    {
                        this.this$1 = r6;
                    }

                    public void run() {
                        if (this.this$1.legacyListener != null) {
                            this.this$1.legacyListener.onAuthError(firebaseError2);
                        } else if (this.this$1.handler != null) {
                            this.this$1.handler.onAuthenticationError(firebaseError2);
                            Firebase.AuthResultHandler access$102 = AuthAttempt.access$102(this.this$1, (Firebase.AuthResultHandler) null);
                        }
                    }
                };
                this.this$0.fireEvent(runnable);
            }
        }

        public void fireSuccess(AuthData authData) {
            Runnable runnable;
            AuthData authData2 = authData;
            if (this.legacyListener != null || this.handler != null) {
                final AuthData authData3 = authData2;
                new Runnable(this) {
                    final /* synthetic */ AuthAttempt this$1;

                    {
                        this.this$1 = r6;
                    }

                    public void run() {
                        if (this.this$1.legacyListener != null) {
                            this.this$1.legacyListener.onAuthSuccess(authData3);
                        } else if (this.this$1.handler != null) {
                            this.this$1.handler.onAuthenticated(authData3);
                            Firebase.AuthResultHandler access$102 = AuthAttempt.access$102(this.this$1, (Firebase.AuthResultHandler) null);
                        }
                    }
                };
                this.this$0.fireEvent(runnable);
            }
        }

        public void fireRevoked(FirebaseError firebaseError) {
            Runnable runnable;
            FirebaseError error = firebaseError;
            if (this.legacyListener != null) {
                final FirebaseError firebaseError2 = error;
                new Runnable(this) {
                    final /* synthetic */ AuthAttempt this$1;

                    {
                        this.this$1 = r6;
                    }

                    public void run() {
                        this.this$1.legacyListener.onAuthRevoked(firebaseError2);
                    }
                };
                this.this$0.fireEvent(runnable);
            }
        }
    }

    public AuthenticationManager(Context context2, Repo repo2, RepoInfo repoInfo2, PersistentConnection connection2) {
        Set<Firebase.AuthStateListener> set;
        Context context3 = context2;
        this.context = context3;
        this.repo = repo2;
        this.repoInfo = repoInfo2;
        this.connection = connection2;
        this.store = context3.getCredentialStore();
        this.logger = context3.getLogger(LOG_TAG);
        new HashSet();
        this.listenerSet = set;
    }

    /* access modifiers changed from: private */
    public void fireEvent(Runnable r) {
        this.context.getEventTarget().postEvent(r);
    }

    /* access modifiers changed from: private */
    public void fireOnSuccess(Firebase.ValueResultHandler valueResultHandler, Object obj) {
        Runnable runnable;
        Firebase.ValueResultHandler handler = valueResultHandler;
        Object result = obj;
        if (handler != null) {
            final Firebase.ValueResultHandler valueResultHandler2 = handler;
            final Object obj2 = result;
            new Runnable(this) {
                final /* synthetic */ AuthenticationManager this$0;

                {
                    this.this$0 = r7;
                }

                public void run() {
                    valueResultHandler2.onSuccess(obj2);
                }
            };
            fireEvent(runnable);
        }
    }

    /* access modifiers changed from: private */
    public void fireOnError(Firebase.ValueResultHandler valueResultHandler, FirebaseError firebaseError) {
        Runnable runnable;
        Firebase.ValueResultHandler handler = valueResultHandler;
        FirebaseError error = firebaseError;
        if (handler != null) {
            final Firebase.ValueResultHandler valueResultHandler2 = handler;
            final FirebaseError firebaseError2 = error;
            new Runnable(this) {
                final /* synthetic */ AuthenticationManager this$0;

                {
                    this.this$0 = r7;
                }

                public void run() {
                    valueResultHandler2.onError(firebaseError2);
                }
            };
            fireEvent(runnable);
        }
    }

    private Firebase.ValueResultHandler ignoreResultValueHandler(Firebase.ResultHandler handler) {
        Firebase.ValueResultHandler valueResultHandler;
        final Firebase.ResultHandler resultHandler = handler;
        new Firebase.ValueResultHandler(this) {
            final /* synthetic */ AuthenticationManager this$0;

            {
                this.this$0 = r6;
            }

            public void onSuccess(Object obj) {
                Object obj2 = obj;
                resultHandler.onSuccess();
            }

            public void onError(FirebaseError error) {
                resultHandler.onError(error);
            }
        };
        return valueResultHandler;
    }

    /* access modifiers changed from: private */
    public void preemptAnyExistingAttempts() {
        FirebaseError error;
        if (this.currentAuthAttempt != null) {
            new FirebaseError(-5, "Due to another authentication attempt, this authentication attempt was aborted before it could complete.");
            this.currentAuthAttempt.fireError(error);
            this.currentAuthAttempt = null;
        }
    }

    /* access modifiers changed from: private */
    public FirebaseError decodeErrorResponse(Object obj) {
        FirebaseError firebaseError;
        Object errorResponse = obj;
        String code = (String) Utilities.getOrNull(errorResponse, "code", String.class);
        String message = (String) Utilities.getOrNull(errorResponse, "message", String.class);
        String details = (String) Utilities.getOrNull(errorResponse, "details", String.class);
        if (code != null) {
            return FirebaseError.fromStatus(code, message, details);
        }
        new FirebaseError(FirebaseError.UNKNOWN_ERROR, message == null ? "Error while authenticating." : message, details);
        return firebaseError;
    }

    /* access modifiers changed from: private */
    public boolean attemptHasBeenPreempted(AuthAttempt attempt) {
        return attempt != this.currentAuthAttempt;
    }

    /* access modifiers changed from: private */
    public AuthAttempt newAuthAttempt(Firebase.AuthResultHandler handler) {
        AuthAttempt authAttempt;
        preemptAnyExistingAttempts();
        new AuthAttempt(this, handler);
        this.currentAuthAttempt = authAttempt;
        return this.currentAuthAttempt;
    }

    /* access modifiers changed from: private */
    public AuthAttempt newAuthAttempt(Firebase.AuthListener listener) {
        AuthAttempt authAttempt;
        preemptAnyExistingAttempts();
        new AuthAttempt(this, listener);
        this.currentAuthAttempt = authAttempt;
        return this.currentAuthAttempt;
    }

    /* access modifiers changed from: private */
    public void fireAuthErrorIfNotPreempted(FirebaseError firebaseError, AuthAttempt authAttempt) {
        Runnable runnable;
        FirebaseError error = firebaseError;
        AuthAttempt attempt = authAttempt;
        if (!attemptHasBeenPreempted(attempt)) {
            if (attempt != null) {
                final AuthAttempt authAttempt2 = attempt;
                final FirebaseError firebaseError2 = error;
                new Runnable(this) {
                    final /* synthetic */ AuthenticationManager this$0;

                    {
                        this.this$0 = r7;
                    }

                    public void run() {
                        authAttempt2.fireError(firebaseError2);
                    }
                };
                fireEvent(runnable);
            }
            this.currentAuthAttempt = null;
        }
    }

    private void checkServerSettings() {
        Throwable th;
        if (this.repoInfo.isDemoHost()) {
            this.logger.warn("Firebase authentication is supported on production Firebases only (*.firebaseio.com). To secure your Firebase, create a production Firebase at https://www.firebase.com.");
        } else if (this.repoInfo.isCustomHost() && !this.context.isCustomAuthenticationServerSet()) {
            Throwable th2 = th;
            new IllegalStateException("For a custom firebase host you must first set your authentication server before using authentication features!");
            throw th2;
        }
    }

    private String getFirebaseCredentialIdentifier() {
        return this.repoInfo.host;
    }

    /* access modifiers changed from: private */
    public void scheduleNow(Runnable r) {
        this.context.getRunLoop().scheduleNow(r);
    }

    private AuthData parseAuthData(String str, Map<String, Object> map, Map<String, Object> map2) {
        long expires;
        AuthData authData2;
        Map<String, Object> map3;
        StringBuilder sb;
        StringBuilder sb2;
        String token = str;
        Map<String, Object> rawAuthData = map;
        Map<String, Object> userData = map2;
        Map<String, Object> authData3 = (Map) Utilities.getOrNull(rawAuthData, "auth", Map.class);
        if (authData3 == null) {
            LogWrapper logWrapper = this.logger;
            new StringBuilder();
            logWrapper.warn(sb2.append("Received invalid auth data: ").append(rawAuthData).toString());
        }
        Object expiresObj = rawAuthData.get(ClientCookie.EXPIRES_ATTR);
        if (expiresObj == null) {
            expires = 0;
        } else if (expiresObj instanceof Integer) {
            expires = (long) ((Integer) expiresObj).intValue();
        } else if (expiresObj instanceof Long) {
            expires = ((Long) expiresObj).longValue();
        } else if (expiresObj instanceof Double) {
            expires = ((Double) expiresObj).longValue();
        } else {
            expires = 0;
        }
        String uid = (String) Utilities.getOrNull(authData3, "uid", String.class);
        if (uid == null) {
            uid = (String) Utilities.getOrNull(userData, "uid", String.class);
        }
        String provider = (String) Utilities.getOrNull(authData3, "provider", String.class);
        if (provider == null) {
            provider = (String) Utilities.getOrNull(userData, "provider", String.class);
        }
        if (provider == null) {
            provider = "custom";
        }
        if (uid == null || uid.isEmpty()) {
            LogWrapper logWrapper2 = this.logger;
            new StringBuilder();
            logWrapper2.warn(sb.append("Received invalid auth data: ").append(authData3).toString());
        }
        Map<String, Object> providerData = (Map) Utilities.getOrNull(userData, provider, Map.class);
        if (providerData == null) {
            new HashMap<>();
            providerData = map3;
        }
        new AuthData(token, expires, uid, provider, authData3, providerData);
        return authData2;
    }

    /* access modifiers changed from: private */
    public void handleBadAuthStatus(FirebaseError firebaseError, AuthAttempt authAttempt, boolean z) {
        Throwable th;
        FirebaseError error = firebaseError;
        AuthAttempt attempt = authAttempt;
        boolean revoked = z;
        if ((error.getCode() == -6) && this.context.getAuthExpirationBehavior() == AuthExpirationBehavior.PAUSE_WRITES_UNTIL_REAUTH) {
            if (this.logger.logsDebug()) {
                this.logger.debug("Pausing writes due to expired token.");
            }
            this.connection.pauseWrites();
        } else if (!this.connection.writesPaused()) {
            boolean clearSession = clearSession();
        } else if (!$assertionsDisabled && this.context.getAuthExpirationBehavior() != AuthExpirationBehavior.PAUSE_WRITES_UNTIL_REAUTH) {
            Throwable th2 = th;
            new AssertionError();
            throw th2;
        } else if (this.logger.logsDebug()) {
            this.logger.debug("Invalid auth while writes are paused; keeping existing session.");
        }
        updateAuthState((AuthData) null);
        if (attempt == null) {
            return;
        }
        if (revoked) {
            attempt.fireRevoked(error);
        } else {
            attempt.fireError(error);
        }
    }

    /* access modifiers changed from: private */
    public void handleAuthSuccess(String str, Map<String, Object> map, Map<String, Object> map2, boolean z, AuthAttempt authAttempt) {
        JsonWebToken token;
        String credential = str;
        Map<String, Object> authDataMap = map;
        Map<String, Object> optionalUserData = map2;
        boolean isNewSession = z;
        AuthAttempt attempt = authAttempt;
        try {
            token = JsonWebToken.decode(credential);
        } catch (IOException e) {
            IOException iOException = e;
            if (this.logger.logsDebug()) {
                this.logger.debug("Failed to parse JWT, probably a Firebase secret.");
            }
            token = null;
        }
        if (isNewSession && token != null && !saveSession(credential, authDataMap, optionalUserData)) {
            this.logger.warn("Failed to store credentials! Authentication will not be persistent!");
        }
        AuthData authData2 = parseAuthData(credential, authDataMap, optionalUserData);
        updateAuthState(authData2);
        if (attempt != null) {
            attempt.fireSuccess(authData2);
        }
        if (this.connection.writesPaused()) {
            if (this.logger.logsDebug()) {
                this.logger.debug("Unpausing writes after successful login.");
            }
            this.connection.unpauseWrites();
        }
    }

    public void resumeSession() {
        TypeReference typeReference;
        Runnable runnable;
        try {
            String credentialData = this.store.loadCredential(getFirebaseCredentialIdentifier(), this.context.getSessionPersistenceKey());
            if (credentialData != null) {
                new TypeReference<Map<String, Object>>(this) {
                    final /* synthetic */ AuthenticationManager this$0;

                    {
                        this.this$0 = r5;
                    }
                };
                Map<String, Object> credentials = (Map) JsonHelpers.getMapper().readValue(credentialData, typeReference);
                String tokenString = (String) Utilities.getOrNull(credentials, TOKEN_KEY, String.class);
                Map<String, Object> authDataObj = (Map) Utilities.getOrNull(credentials, AUTH_DATA_KEY, Map.class);
                Map<String, Object> userData = (Map) Utilities.getOrNull(credentials, USER_DATA_KEY, Map.class);
                if (authDataObj != null) {
                    updateAuthState(parseAuthData(tokenString, authDataObj, userData));
                    final String str = tokenString;
                    final Map<String, Object> map = authDataObj;
                    final Map<String, Object> map2 = userData;
                    new Runnable(this) {
                        final /* synthetic */ AuthenticationManager this$0;

                        {
                            this.this$0 = r8;
                        }

                        public void run() {
                            Firebase.AuthListener authListener;
                            new Firebase.AuthListener(this) {
                                final /* synthetic */ C13026 this$1;

                                {
                                    this.this$1 = r5;
                                }

                                public void onAuthError(FirebaseError error) {
                                    this.this$1.this$0.handleBadAuthStatus(error, (AuthAttempt) null, false);
                                }

                                public void onAuthSuccess(Object obj) {
                                    Object obj2 = obj;
                                    this.this$1.this$0.handleAuthSuccess(str, map, map2, false, (AuthAttempt) null);
                                }

                                public void onAuthRevoked(FirebaseError error) {
                                    this.this$1.this$0.handleBadAuthStatus(error, (AuthAttempt) null, true);
                                }
                            };
                            this.this$0.connection.auth(str, authListener);
                        }
                    };
                    this.context.getRunLoop().scheduleNow(runnable);
                }
            }
        } catch (IOException e) {
            this.logger.warn("Failed resuming authentication session!", e);
            boolean clearSession = clearSession();
        }
    }

    private boolean saveSession(String token, Map<String, Object> authData2, Map<String, Object> userData) {
        Map<String, Object> map;
        Throwable th;
        StringBuilder sb;
        String firebaseId = getFirebaseCredentialIdentifier();
        String sessionId = this.context.getSessionPersistenceKey();
        boolean clearCredential = this.store.clearCredential(firebaseId, sessionId);
        new HashMap<>();
        Map<String, Object> sessionMap = map;
        Object put = sessionMap.put(TOKEN_KEY, token);
        Object put2 = sessionMap.put(AUTH_DATA_KEY, authData2);
        Object put3 = sessionMap.put(USER_DATA_KEY, userData);
        try {
            if (this.logger.logsDebug()) {
                LogWrapper logWrapper = this.logger;
                new StringBuilder();
                logWrapper.debug(sb.append("Storing credentials for Firebase \"").append(firebaseId).append("\" and session \"").append(sessionId).append("\".").toString());
            }
            return this.store.storeCredential(firebaseId, sessionId, JsonHelpers.getMapper().writeValueAsString(sessionMap));
        } catch (JsonProcessingException e) {
            JsonProcessingException e2 = e;
            Throwable th2 = th;
            new RuntimeException(e2);
            throw th2;
        }
    }

    /* access modifiers changed from: private */
    public boolean clearSession() {
        StringBuilder sb;
        String firebaseId = getFirebaseCredentialIdentifier();
        String sessionId = this.context.getSessionPersistenceKey();
        if (this.logger.logsDebug()) {
            LogWrapper logWrapper = this.logger;
            new StringBuilder();
            logWrapper.debug(sb.append("Clearing credentials for Firebase \"").append(firebaseId).append("\" and session \"").append(sessionId).append("\".").toString());
        }
        return this.store.clearCredential(firebaseId, sessionId);
    }

    /* access modifiers changed from: private */
    public void updateAuthState(AuthData authData2) {
        boolean z;
        Runnable runnable;
        AuthData authData3 = authData2;
        if (this.authData == null) {
            z = authData3 != null;
        } else {
            z = !this.authData.equals(authData3);
        }
        boolean changed = z;
        this.authData = authData3;
        if (changed) {
            for (final Firebase.AuthStateListener next : this.listenerSet) {
                final AuthData authData4 = authData3;
                new Runnable(this) {
                    final /* synthetic */ AuthenticationManager this$0;

                    {
                        this.this$0 = r7;
                    }

                    public void run() {
                        next.onAuthStateChanged(authData4);
                    }
                };
                fireEvent(runnable);
            }
        }
    }

    private String buildUrlPath(String str) {
        StringBuilder sb;
        String urlPath = str;
        new StringBuilder();
        StringBuilder path = sb;
        StringBuilder append = path.append("/v2/");
        StringBuilder append2 = path.append(this.repoInfo.namespace);
        if (!urlPath.startsWith("/")) {
            StringBuilder append3 = path.append("/");
        }
        StringBuilder append4 = path.append(urlPath);
        return path.toString();
    }

    private void makeRequest(String urlPath, HttpUtilities.HttpRequestType type, Map<String, String> urlParams, Map<String, String> requestParams, RequestHandler requestHandler) {
        Map<String, String> map;
        Runnable runnable;
        RequestHandler handler = requestHandler;
        new HashMap<>(urlParams);
        Map<String, String> actualUrlParams = map;
        String put = actualUrlParams.put(NotificationCompat.CATEGORY_TRANSPORT, "json");
        String put2 = actualUrlParams.put("v", this.context.getPlatformVersion());
        HttpUriRequest request = HttpUtilities.requestWithType(this.context.getAuthenticationServer(), buildUrlPath(urlPath), type, actualUrlParams, requestParams);
        if (this.logger.logsDebug()) {
            URI uri = request.getURI();
            String scheme = uri.getScheme();
            String authority = uri.getAuthority();
            String path = uri.getPath();
            int numQueryParams = uri.getQuery().split("&").length;
            LogWrapper logWrapper = this.logger;
            Object[] objArr = new Object[4];
            objArr[0] = scheme;
            Object[] objArr2 = objArr;
            objArr2[1] = authority;
            Object[] objArr3 = objArr2;
            objArr3[2] = path;
            Object[] objArr4 = objArr3;
            objArr4[3] = Integer.valueOf(numQueryParams);
            logWrapper.debug(String.format("Sending request to %s://%s%s with %d query params", objArr4));
        }
        final HttpUriRequest httpUriRequest = request;
        final RequestHandler requestHandler2 = handler;
        new Runnable(this) {
            final /* synthetic */ AuthenticationManager this$0;

            {
                this.this$0 = r7;
            }

            public void run() {
                HttpParams httpParams;
                DefaultHttpClient defaultHttpClient;
                Runnable runnable;
                ResponseHandler responseHandler;
                Runnable runnable2;
                Throwable th;
                new BasicHttpParams();
                HttpParams httpParameters = httpParams;
                HttpConnectionParams.setConnectionTimeout(httpParameters, AuthenticationManager.CONNECTION_TIMEOUT);
                HttpConnectionParams.setSoTimeout(httpParameters, AuthenticationManager.CONNECTION_TIMEOUT);
                new DefaultHttpClient(httpParameters);
                DefaultHttpClient httpClient = defaultHttpClient;
                HttpParams parameter = httpClient.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);
                DefaultHttpClient defaultHttpClient2 = httpClient;
                try {
                    new JsonBasicResponseHandler();
                    Map<String, Object> result = (Map) defaultHttpClient2.execute(httpUriRequest, responseHandler);
                    if (result == null) {
                        Throwable th2 = th;
                        new IOException("Authentication server did not respond with a valid response");
                        throw th2;
                    }
                    final Map<String, Object> map = result;
                    new Runnable(this) {
                        final /* synthetic */ C13058 this$1;

                        {
                            this.this$1 = r6;
                        }

                        public void run() {
                            requestHandler2.onResult(map);
                        }
                    };
                    this.this$0.scheduleNow(runnable2);
                } catch (IOException e) {
                    final IOException iOException = e;
                    new Runnable(this) {
                        final /* synthetic */ C13058 this$1;

                        {
                            this.this$1 = r6;
                        }

                        public void run() {
                            requestHandler2.onError(iOException);
                        }
                    };
                    this.this$0.scheduleNow(runnable);
                }
            }
        };
        this.context.runBackgroundTask(runnable);
    }

    /* access modifiers changed from: private */
    public void makeAuthenticationRequest(String urlPath, Map<String, String> params, Firebase.AuthResultHandler handler) {
        RequestHandler requestHandler;
        final AuthAttempt newAuthAttempt = newAuthAttempt(handler);
        new RequestHandler(this) {
            final /* synthetic */ AuthenticationManager this$0;

            {
                this.this$0 = r6;
            }

            public void onResult(Map<String, Object> map) {
                Map<String, Object> result = map;
                Object errorResponse = result.get(AuthenticationManager.ERROR_KEY);
                String token = (String) Utilities.getOrNull(result, AuthenticationManager.TOKEN_KEY, String.class);
                if (errorResponse != null || token == null) {
                    this.this$0.fireAuthErrorIfNotPreempted(this.this$0.decodeErrorResponse(errorResponse), newAuthAttempt);
                } else if (!this.this$0.attemptHasBeenPreempted(newAuthAttempt)) {
                    this.this$0.authWithCredential(token, result, newAuthAttempt);
                }
            }

            public void onError(IOException e) {
                FirebaseError error;
                StringBuilder sb;
                new StringBuilder();
                new FirebaseError(-24, sb.append("There was an exception while connecting to the authentication server: ").append(e.getLocalizedMessage()).toString());
                this.this$0.fireAuthErrorIfNotPreempted(error, newAuthAttempt);
            }
        };
        makeRequest(urlPath, HttpUtilities.HttpRequestType.GET, params, Collections.emptyMap(), requestHandler);
    }

    /* access modifiers changed from: private */
    public void makeOperationRequest(String urlPath, HttpUtilities.HttpRequestType type, Map<String, String> urlParams, Map<String, String> requestParams, Firebase.ResultHandler handler, boolean logUserOut) {
        makeOperationRequestWithResult(urlPath, type, urlParams, requestParams, ignoreResultValueHandler(handler), logUserOut);
    }

    /* access modifiers changed from: private */
    public void makeOperationRequestWithResult(String urlPath, HttpUtilities.HttpRequestType type, Map<String, String> urlParams, Map<String, String> requestParams, Firebase.ValueResultHandler<Map<String, Object>> handler, boolean logUserOut) {
        RequestHandler requestHandler;
        final boolean z = logUserOut;
        final Firebase.ValueResultHandler<Map<String, Object>> valueResultHandler = handler;
        new RequestHandler(this) {
            final /* synthetic */ AuthenticationManager this$0;

            {
                this.this$0 = r7;
            }

            public void onResult(Map<String, Object> map) {
                Runnable runnable;
                String uid;
                Map<String, Object> result = map;
                Object errorResponse = result.get(AuthenticationManager.ERROR_KEY);
                if (errorResponse == null) {
                    if (z && (uid = (String) Utilities.getOrNull(result, "uid", String.class)) != null && this.this$0.authData != null && uid.equals(this.this$0.authData.getUid())) {
                        this.this$0.unauth((Firebase.CompletionListener) null, false);
                    }
                    final Map<String, Object> map2 = result;
                    new Runnable(this) {
                        final /* synthetic */ C128010 this$1;

                        {
                            this.this$1 = r6;
                        }

                        public void run() {
                            this.this$1.this$0.fireOnSuccess(valueResultHandler, map2);
                        }
                    };
                    this.this$0.scheduleNow(runnable);
                    return;
                }
                this.this$0.fireOnError(valueResultHandler, this.this$0.decodeErrorResponse(errorResponse));
            }

            public void onError(IOException e) {
                FirebaseError error;
                StringBuilder sb;
                new StringBuilder();
                new FirebaseError(-24, sb.append("There was an exception while performing the request: ").append(e.getLocalizedMessage()).toString());
                this.this$0.fireOnError(valueResultHandler, error);
            }
        };
        makeRequest(urlPath, type, urlParams, requestParams, requestHandler);
    }

    /* access modifiers changed from: private */
    public void authWithCredential(String str, Map<String, Object> map, AuthAttempt authAttempt) {
        Firebase.AuthListener authListener;
        StringBuilder sb;
        Throwable th;
        String credential = str;
        Map<String, Object> optionalUserData = map;
        AuthAttempt attempt = authAttempt;
        if (attempt != this.currentAuthAttempt) {
            Throwable th2 = th;
            new IllegalStateException("Ooops. We messed up tracking which authentications are running!");
            throw th2;
        }
        if (this.logger.logsDebug()) {
            LogWrapper logWrapper = this.logger;
            new StringBuilder();
            logWrapper.debug(sb.append("Authenticating with credential of length ").append(credential.length()).toString());
        }
        this.currentAuthAttempt = null;
        final String str2 = credential;
        final Map<String, Object> map2 = optionalUserData;
        final AuthAttempt authAttempt2 = attempt;
        new Firebase.AuthListener(this) {
            final /* synthetic */ AuthenticationManager this$0;

            {
                this.this$0 = r8;
            }

            public void onAuthSuccess(Object authData) {
                this.this$0.handleAuthSuccess(str2, (Map) authData, map2, true, authAttempt2);
            }

            public void onAuthRevoked(FirebaseError error) {
                this.this$0.handleBadAuthStatus(error, authAttempt2, true);
            }

            public void onAuthError(FirebaseError error) {
                this.this$0.handleBadAuthStatus(error, authAttempt2, false);
            }
        };
        this.connection.auth(credential, authListener);
    }

    public AuthData getAuth() {
        return this.authData;
    }

    public void unauth() {
        checkServerSettings();
        unauth((Firebase.CompletionListener) null);
    }

    public void unauth(Firebase.CompletionListener listener) {
        unauth(listener, true);
    }

    public void unauth(Firebase.CompletionListener listener, boolean waitForCompletion) {
        Semaphore semaphore;
        Runnable runnable;
        Throwable th;
        checkServerSettings();
        new Semaphore(0);
        Semaphore semaphore2 = semaphore;
        final Semaphore semaphore3 = semaphore2;
        final Firebase.CompletionListener completionListener = listener;
        new Runnable(this) {
            final /* synthetic */ AuthenticationManager this$0;

            {
                this.this$0 = r7;
            }

            public void run() {
                Firebase.CompletionListener completionListener;
                this.this$0.preemptAnyExistingAttempts();
                this.this$0.updateAuthState((AuthData) null);
                semaphore3.release();
                boolean access$1600 = this.this$0.clearSession();
                new Firebase.CompletionListener(this) {
                    final /* synthetic */ C128312 this$1;

                    {
                        this.this$1 = r5;
                    }

                    public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                        Firebase ref;
                        Path path;
                        FirebaseError error = firebaseError;
                        Firebase firebase2 = firebase;
                        if (completionListener != null) {
                            new Path("");
                            new Firebase(this.this$1.this$0.repo, path);
                            completionListener.onComplete(error, ref);
                        }
                    }
                };
                this.this$0.connection.unauth(completionListener);
                if (this.this$0.connection.writesPaused()) {
                    if (this.this$0.logger.logsDebug()) {
                        this.this$0.logger.debug("Unpausing writes after explicit unauth.");
                    }
                    this.this$0.connection.unpauseWrites();
                }
            }
        };
        scheduleNow(runnable);
        if (waitForCompletion) {
            try {
                semaphore2.acquire();
            } catch (InterruptedException e) {
                InterruptedException e2 = e;
                Throwable th2 = th;
                new RuntimeException(e2);
                throw th2;
            }
        }
    }

    public void addAuthStateListener(Firebase.AuthStateListener listener) {
        Runnable runnable;
        checkServerSettings();
        final Firebase.AuthStateListener authStateListener = listener;
        new Runnable(this) {
            final /* synthetic */ AuthenticationManager this$0;

            {
                this.this$0 = r6;
            }

            public void run() {
                Runnable runnable;
                boolean add = this.this$0.listenerSet.add(authStateListener);
                final AuthData access$1100 = this.this$0.authData;
                new Runnable(this) {
                    final /* synthetic */ C128513 this$1;

                    {
                        this.this$1 = r6;
                    }

                    public void run() {
                        authStateListener.onAuthStateChanged(access$1100);
                    }
                };
                this.this$0.fireEvent(runnable);
            }
        };
        scheduleNow(runnable);
    }

    public void removeAuthStateListener(Firebase.AuthStateListener listener) {
        Runnable runnable;
        checkServerSettings();
        final Firebase.AuthStateListener authStateListener = listener;
        new Runnable(this) {
            final /* synthetic */ AuthenticationManager this$0;

            {
                this.this$0 = r6;
            }

            public void run() {
                boolean remove = this.this$0.listenerSet.remove(authStateListener);
            }
        };
        scheduleNow(runnable);
    }

    public void authAnonymously(Firebase.AuthResultHandler handler) {
        Runnable runnable;
        checkServerSettings();
        final Firebase.AuthResultHandler authResultHandler = handler;
        new Runnable(this) {
            final /* synthetic */ AuthenticationManager this$0;

            {
                this.this$0 = r6;
            }

            public void run() {
                Map map;
                new HashMap();
                this.this$0.makeAuthenticationRequest(Constants.FIREBASE_AUTH_ANONYMOUS_PATH, map, authResultHandler);
            }
        };
        scheduleNow(runnable);
    }

    public void authWithPassword(String email, String password, Firebase.AuthResultHandler handler) {
        Runnable runnable;
        checkServerSettings();
        final String str = email;
        final String str2 = password;
        final Firebase.AuthResultHandler authResultHandler = handler;
        new Runnable(this) {
            final /* synthetic */ AuthenticationManager this$0;

            {
                this.this$0 = r8;
            }

            public void run() {
                Map<String, String> map;
                new HashMap<>();
                Map<String, String> params = map;
                String put = params.put("email", str);
                String put2 = params.put("password", str2);
                this.this$0.makeAuthenticationRequest(Constants.FIREBASE_AUTH_PASSWORD_PATH, params, authResultHandler);
            }
        };
        scheduleNow(runnable);
    }

    public void authWithCustomToken(String token, Firebase.AuthResultHandler handler) {
        Runnable runnable;
        final Firebase.AuthResultHandler authResultHandler = handler;
        final String str = token;
        new Runnable(this) {
            final /* synthetic */ AuthenticationManager this$0;

            {
                this.this$0 = r7;
            }

            public void run() {
                this.this$0.authWithCredential(str, (Map<String, Object>) null, this.this$0.newAuthAttempt(authResultHandler));
            }
        };
        scheduleNow(runnable);
    }

    public void authWithFirebaseToken(String token, Firebase.AuthListener listener) {
        Runnable runnable;
        final Firebase.AuthListener authListener = listener;
        final String str = token;
        new Runnable(this) {
            final /* synthetic */ AuthenticationManager this$0;

            {
                this.this$0 = r7;
            }

            public void run() {
                this.this$0.authWithCredential(str, (Map<String, Object>) null, this.this$0.newAuthAttempt(authListener));
            }
        };
        scheduleNow(runnable);
    }

    public void authWithOAuthToken(String str, String str2, Firebase.AuthResultHandler authResultHandler) {
        Map<String, String> map;
        Throwable th;
        String provider = str;
        String token = str2;
        Firebase.AuthResultHandler handler = authResultHandler;
        if (token == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Token must not be null!");
            throw th2;
        }
        new HashMap<>();
        Map<String, String> params = map;
        String put = params.put("access_token", token);
        authWithOAuthToken(provider, params, handler);
    }

    public void authWithOAuthToken(String provider, Map<String, String> params, Firebase.AuthResultHandler handler) {
        Runnable runnable;
        checkServerSettings();
        final String str = provider;
        final Map<String, String> map = params;
        final Firebase.AuthResultHandler authResultHandler = handler;
        new Runnable(this) {
            final /* synthetic */ AuthenticationManager this$0;

            {
                this.this$0 = r8;
            }

            public void run() {
                this.this$0.makeAuthenticationRequest(String.format(Constants.FIREBASE_AUTH_PROVIDER_PATH_FORMAT, new Object[]{str}), map, authResultHandler);
            }
        };
        scheduleNow(runnable);
    }

    public void createUser(String email, String password, Firebase.ResultHandler handler) {
        createUser(email, password, (Firebase.ValueResultHandler<Map<String, Object>>) ignoreResultValueHandler(handler));
    }

    public void createUser(String email, String password, Firebase.ValueResultHandler<Map<String, Object>> handler) {
        Runnable runnable;
        checkServerSettings();
        final String str = email;
        final String str2 = password;
        final Firebase.ValueResultHandler<Map<String, Object>> valueResultHandler = handler;
        new Runnable(this) {
            final /* synthetic */ AuthenticationManager this$0;

            {
                this.this$0 = r8;
            }

            public void run() {
                Map<String, String> map;
                new HashMap<>();
                Map<String, String> requestParams = map;
                String put = requestParams.put("email", str);
                String put2 = requestParams.put("password", str2);
                this.this$0.makeOperationRequestWithResult(Constants.FIREBASE_AUTH_CREATE_USER_PATH, HttpUtilities.HttpRequestType.POST, Collections.emptyMap(), requestParams, valueResultHandler, false);
            }
        };
        scheduleNow(runnable);
    }

    public void removeUser(String email, String password, Firebase.ResultHandler handler) {
        Runnable runnable;
        checkServerSettings();
        final String str = password;
        final String str2 = email;
        final Firebase.ResultHandler resultHandler = handler;
        new Runnable(this) {
            final /* synthetic */ AuthenticationManager this$0;

            {
                this.this$0 = r8;
            }

            public void run() {
                Map<String, String> map;
                new HashMap<>();
                Map<String, String> urlParams = map;
                String put = urlParams.put("password", str);
                this.this$0.makeOperationRequest(String.format(Constants.FIREBASE_AUTH_REMOVE_USER_PATH_FORMAT, new Object[]{str2}), HttpUtilities.HttpRequestType.DELETE, urlParams, Collections.emptyMap(), resultHandler, true);
            }
        };
        scheduleNow(runnable);
    }

    public void changePassword(String email, String oldPassword, String newPassword, Firebase.ResultHandler handler) {
        Runnable runnable;
        checkServerSettings();
        final String str = oldPassword;
        final String str2 = newPassword;
        final String str3 = email;
        final Firebase.ResultHandler resultHandler = handler;
        new Runnable(this) {
            final /* synthetic */ AuthenticationManager this$0;

            {
                this.this$0 = r9;
            }

            public void run() {
                Map<String, String> map;
                Map<String, String> map2;
                new HashMap<>();
                Map<String, String> urlParams = map;
                String put = urlParams.put("oldPassword", str);
                new HashMap<>();
                Map<String, String> requestParams = map2;
                String put2 = requestParams.put("password", str2);
                this.this$0.makeOperationRequest(String.format(Constants.FIREBASE_AUTH_PASSWORD_PATH_FORMAT, new Object[]{str3}), HttpUtilities.HttpRequestType.PUT, urlParams, requestParams, resultHandler, false);
            }
        };
        scheduleNow(runnable);
    }

    public void changeEmail(String oldEmail, String password, String newEmail, Firebase.ResultHandler handler) {
        Runnable runnable;
        checkServerSettings();
        final String str = password;
        final String str2 = newEmail;
        final String str3 = oldEmail;
        final Firebase.ResultHandler resultHandler = handler;
        new Runnable(this) {
            final /* synthetic */ AuthenticationManager this$0;

            {
                this.this$0 = r9;
            }

            public void run() {
                Map<String, String> map;
                Map<String, String> map2;
                new HashMap<>();
                Map<String, String> urlParams = map;
                String put = urlParams.put("password", str);
                new HashMap<>();
                Map<String, String> requestParams = map2;
                String put2 = requestParams.put("email", str2);
                this.this$0.makeOperationRequest(String.format(Constants.FIREBASE_AUTH_EMAIL_PATH_FORMAT, new Object[]{str3}), HttpUtilities.HttpRequestType.PUT, urlParams, requestParams, resultHandler, false);
            }
        };
        scheduleNow(runnable);
    }

    public void resetPassword(String email, Firebase.ResultHandler handler) {
        Runnable runnable;
        checkServerSettings();
        final String str = email;
        final Firebase.ResultHandler resultHandler = handler;
        new Runnable(this) {
            final /* synthetic */ AuthenticationManager this$0;

            {
                this.this$0 = r7;
            }

            public void run() {
                String url = String.format(Constants.FIREBASE_AUTH_PASSWORD_PATH_FORMAT, new Object[]{str});
                Map<String, String> params = Collections.emptyMap();
                this.this$0.makeOperationRequest(url, HttpUtilities.HttpRequestType.POST, params, params, resultHandler, false);
            }
        };
        scheduleNow(runnable);
    }
}

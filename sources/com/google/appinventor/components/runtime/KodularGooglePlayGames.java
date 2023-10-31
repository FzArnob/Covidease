package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.games.AchievementsClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesClient;
import com.google.android.gms.games.LeaderboardsClient;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerLevelInfo;
import com.google.android.gms.games.PlayersClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;

@DesignerComponent(category = ComponentCategory.GOOGLE, description = "Google Play Games component to connect to the Google Play Games services", iconName = "images/gameClient.png", nonVisible = true, version = 1)
@UsesLibraries(libraries = "arch-core-runtime.jar, arch-core-runtime.aar,play-services-games.jar, play-services-games.aar,play-services-base.jar, play-services-base.aar,play-services-basement.jar, play-services-basement.aar,play-services-tasks.jar, play-services-tasks.aar,play-services-auth-base.jar, play-services-auth-base.aar,play-services-auth.jar, play-services-auth.aar")
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET")
public class KodularGooglePlayGames extends AndroidNonvisibleComponent implements ActivityResultListener, OnResumeListener {
    private static final String LOG_TAG = "KodularGooglePlayGames";
    /* access modifiers changed from: private */
    public int achievementCode = 0;
    private AchievementsClient achievementsClient;
    private ComponentContainer componentContainer;
    private Context context;
    private GamesClient gamesClient;
    private GoogleSignInClient googleSignInClient;
    private boolean isCompanion = false;
    /* access modifiers changed from: private */
    public int leaderboardCode = 0;
    private LeaderboardsClient leaderboardsClient;
    private PlayersClient playersClient;
    private int requestCode = 0;
    private GoogleSignInOptions signInOptions;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public KodularGooglePlayGames(com.google.appinventor.components.runtime.ComponentContainer r7) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            r3 = 0
            r2.requestCode = r3
            r2 = r0
            r3 = 0
            r2.achievementCode = r3
            r2 = r0
            r3 = 0
            r2.leaderboardCode = r3
            r2 = r0
            r3 = 0
            r2.isCompanion = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            r2 = r0
            r3 = r1
            r2.componentContainer = r3
            r2 = r0
            com.google.appinventor.components.runtime.Form r2 = r2.form
            r3 = r0
            r2.registerForOnResume(r3)
            r2 = r0
            com.google.android.gms.auth.api.signin.GoogleSignInOptions r3 = com.google.android.gms.auth.api.signin.GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN
            r2.signInOptions = r3
            r2 = r0
            r5 = r2
            r2 = r5
            r3 = r5
            android.content.Context r3 = r3.context
            r4 = r0
            com.google.android.gms.auth.api.signin.GoogleSignInOptions r4 = r4.signInOptions
            com.google.android.gms.auth.api.signin.GoogleSignInClient r3 = com.google.android.gms.auth.api.signin.GoogleSignIn.getClient(r3, r4)
            r2.googleSignInClient = r3
            r2 = r0
            com.google.appinventor.components.runtime.Form r2 = r2.form
            boolean r2 = r2 instanceof com.google.appinventor.components.runtime.ReplForm
            if (r2 == 0) goto L_0x004d
            r2 = r0
            r3 = 1
            r2.isCompanion = r3
        L_0x004d:
            java.lang.String r2 = "KodularGooglePlayGames"
            java.lang.String r3 = "Google Play Games component initialized"
            int r2 = android.util.Log.d(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.KodularGooglePlayGames.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleFunction(description = "Unlock an achievement.")
    public void UnlockAchievement(String str) {
        String str2 = str;
        if (!isCompanionMode()) {
            GoogleSignInAccount lastSignedInAccount = GoogleSignIn.getLastSignedInAccount(this.context);
            GoogleSignInAccount googleSignInAccount = lastSignedInAccount;
            if (lastSignedInAccount != null) {
                Games.getAchievementsClient(this.context, googleSignInAccount).unlock(str2);
            }
        }
    }

    @SimpleFunction(description = "Increment an achievement.")
    public void IncrementAchievement(String str, int i) {
        String str2 = str;
        int i2 = i;
        if (!isCompanionMode() && this.achievementsClient != null) {
            this.achievementsClient.increment(str2, i2);
        }
    }

    @SimpleFunction(description = "Shows Achievement.")
    public void ShowAchievements() {
        OnSuccessListener onSuccessListener;
        if (!isCompanionMode() && this.achievementsClient != null) {
            if (this.achievementCode == 0) {
                this.achievementCode = this.form.registerForActivityResult(this);
            }
            new OnSuccessListener<Intent>(this) {
                private /* synthetic */ KodularGooglePlayGames hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                }

                public final /* synthetic */ void onSuccess(Object obj) {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.startActivityForResult((Intent) obj, this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.achievementCode);
                }
            };
            Task addOnSuccessListener = this.achievementsClient.getAchievementsIntent().addOnSuccessListener(onSuccessListener);
        }
    }

    @SimpleFunction(description = "Submits user score to leaderboard.")
    public void SubmitScore(String str, int i) {
        String str2 = str;
        int i2 = i;
        if (!isCompanionMode() && this.leaderboardsClient != null) {
            this.leaderboardsClient.submitScore(str2, (long) i2);
        }
    }

    @SimpleFunction(description = "Shows Leaderboard.")
    public void ShowLeaderboard(String str) {
        OnSuccessListener onSuccessListener;
        String str2 = str;
        if (!isCompanionMode() && this.leaderboardsClient != null) {
            if (this.leaderboardCode == 0) {
                this.leaderboardCode = this.form.registerForActivityResult(this);
            }
            new OnSuccessListener<Intent>(this) {
                private /* synthetic */ KodularGooglePlayGames hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                }

                public final /* synthetic */ void onSuccess(Object obj) {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form.startActivityForResult((Intent) obj, this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.leaderboardCode);
                }
            };
            Task addOnSuccessListener = this.leaderboardsClient.getLeaderboardIntent(str2).addOnSuccessListener(onSuccessListener);
        }
    }

    @SimpleFunction(description = "Sign in the user. useLastAccount, true: use the last signed in account if possible, false: open a screen to choose for an account")
    public void SignIn(boolean z) {
        boolean z2 = z;
        if (!isCompanionMode()) {
            if (z2) {
                signInSilently(true);
            } else {
                startSignInIntent();
            }
        }
    }

    @SimpleFunction(description = "Sign out the user.")
    public void SignOut() {
        if (!isCompanionMode()) {
            signOut();
        }
    }

    @SimpleFunction(description = "Is user signed in?")
    public boolean IsSignedIn() {
        return isSignedIn();
    }

    @SimpleFunction(description = "Get the current player")
    public void GetPlayer() {
        OnCompleteListener onCompleteListener;
        if (!isCompanionMode() && this.playersClient != null) {
            new OnCompleteListener<Player>(this) {
                private /* synthetic */ KodularGooglePlayGames hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                }

                public final void onComplete(Task<Player> task) {
                    Task<Player> task2 = task;
                    if (task2.isSuccessful()) {
                        Player result = task2.getResult();
                        int i = 0;
                        long j = 0;
                        try {
                            i = result.getLevelInfo().getCurrentLevel().getLevelNumber();
                        } catch (NullPointerException e) {
                        }
                        try {
                            j = result.getLevelInfo().getCurrentXpTotal();
                        } catch (NullPointerException e2) {
                        }
                        PlayerLevelInfo levelInfo = result.getLevelInfo();
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.GotPlayer(result.getDisplayName(), result.getPlayerId(), i, j);
                    }
                }
            };
            Task addOnCompleteListener = this.playersClient.getCurrentPlayer().addOnCompleteListener((Activity) this.form, onCompleteListener);
        }
    }

    @SimpleEvent(description = "User signed in.")
    public void SignedIn(boolean z) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "SignedIn", Boolean.valueOf(z));
    }

    @SimpleEvent(description = "User signed out.")
    public void SignedOut() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "SignedOut", new Object[0]);
    }

    @SimpleEvent(description = "Got player info")
    public void GotPlayer(String str, String str2, int i, long j) {
        Object[] objArr = new Object[4];
        objArr[0] = str;
        Object[] objArr2 = objArr;
        objArr2[1] = str2;
        Object[] objArr3 = objArr2;
        objArr3[2] = Integer.valueOf(i);
        Object[] objArr4 = objArr3;
        objArr4[3] = Long.valueOf(j);
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "GotPlayer", objArr4);
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty(userVisible = false)
    public void GooglePlayGamesAppId(String str) {
    }

    private boolean isCompanionMode() {
        Notifier notifier;
        if (this.isCompanion) {
            try {
                new Notifier(this.componentContainer);
                notifier.ShowMessageDialog("Please export your project as an apk and test google play games on a real device.", "Companion error", "OK");
            } catch (Exception e) {
                int e2 = Log.e(LOG_TAG, String.valueOf(e));
            }
        }
        return this.isCompanion;
    }

    private boolean isSignedIn() {
        return GoogleSignIn.getLastSignedInAccount(this.context) != null;
    }

    private void signInSilently(boolean z) {
        OnCompleteListener onCompleteListener;
        boolean z2 = z;
        GoogleSignInAccount lastSignedInAccount = GoogleSignIn.getLastSignedInAccount(this.context);
        GoogleSignInAccount googleSignInAccount = lastSignedInAccount;
        if (GoogleSignIn.hasPermissions(lastSignedInAccount, this.signInOptions.getScopeArray())) {
            onConnected(googleSignInAccount);
            return;
        }
        final boolean z3 = z2;
        new OnCompleteListener<GoogleSignInAccount>(this) {
            private /* synthetic */ KodularGooglePlayGames hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
            }

            public final void onComplete(Task<GoogleSignInAccount> task) {
                Task<GoogleSignInAccount> task2 = task;
                if (task2.isSuccessful()) {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.onConnected(task2.getResult());
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.SignedIn(true);
                } else if (z3) {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.startSignInIntent();
                } else {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.SignedIn(false);
                }
            }
        };
        Task addOnCompleteListener = this.googleSignInClient.silentSignIn().addOnCompleteListener((Activity) this.form, onCompleteListener);
    }

    /* access modifiers changed from: private */
    public void startSignInIntent() {
        StringBuilder sb;
        this.requestCode = this.form.registerForActivityResult(this);
        new StringBuilder("Activity Started: ");
        int i = Log.i(LOG_TAG, sb.append(this.requestCode).toString());
        this.form.startActivityForResult(this.googleSignInClient.getSignInIntent(), this.requestCode);
    }

    private void signOut() {
        OnCompleteListener onCompleteListener;
        new OnCompleteListener<Void>(this) {
            private /* synthetic */ KodularGooglePlayGames hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void onComplete(Task<Void> task) {
                Task<Void> task2 = task;
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.SignedOut();
            }
        };
        Task addOnCompleteListener = this.googleSignInClient.signOut().addOnCompleteListener((Activity) this.form, onCompleteListener);
        onDisconnected();
    }

    /* access modifiers changed from: private */
    public void onConnected(GoogleSignInAccount googleSignInAccount) {
        GoogleSignInAccount googleSignInAccount2 = googleSignInAccount;
        int d = Log.d(LOG_TAG, "Connected to Google APIs");
        this.achievementsClient = Games.getAchievementsClient(this.context, googleSignInAccount2);
        this.leaderboardsClient = Games.getLeaderboardsClient(this.context, googleSignInAccount2);
        this.gamesClient = Games.getGamesClient(this.context, googleSignInAccount2);
        this.playersClient = Games.getPlayersClient(this.context, googleSignInAccount2);
        Task viewForPopups = this.gamesClient.setViewForPopups(this.form.coordinatorLayout);
        Task gravityForPopups = this.gamesClient.setGravityForPopups(49);
    }

    private void onDisconnected() {
        int d = Log.d(LOG_TAG, "onDisconnected()");
        this.achievementsClient = null;
        this.leaderboardsClient = null;
        this.gamesClient = null;
    }

    public void onResume() {
        signInSilently(false);
    }

    public void resultReturned(int i, int i2, Intent intent) {
        StringBuilder sb;
        int i3 = i2;
        Intent intent2 = intent;
        if (i == this.requestCode) {
            GoogleSignInResult signInResultFromIntent = Auth.GoogleSignInApi.getSignInResultFromIntent(intent2);
            GoogleSignInResult googleSignInResult = signInResultFromIntent;
            if (signInResultFromIntent.isSuccess()) {
                onConnected(googleSignInResult.getSignInAccount());
                return;
            }
            new StringBuilder("signInResult:failed code=");
            int w = Log.w(LOG_TAG, sb.append(googleSignInResult.getStatus().getStatusCode()).toString());
            onDisconnected();
        }
    }
}

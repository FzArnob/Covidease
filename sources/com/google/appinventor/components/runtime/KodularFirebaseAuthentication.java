package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.KodularResourcesUtil;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.PlayGamesAuthProvider;
import com.google.firebase.auth.UserProfileChangeRequest;
import java.util.concurrent.TimeUnit;

@DesignerComponent(category = ComponentCategory.GOOGLE, description = "Firebase Authentication", iconName = "images/firebaseDB.png", nonVisible = true, version = 3)
@UsesLibraries(libraries = "firebase-auth.jar, firebase-auth.aar,firebase-auth-interop.jar, firebase-auth-interop.aar,firebase-common.jar, firebase-common.aar,arch-core-runtime.jar, arch-core-runtime.aar,play-services-auth.jar, play-services-auth.aar,play-services-auth-api-phone.jar, play-services-auth-api-phone.aar,play-services-auth-base.jar, play-services-auth-base.aar,play-services-base.jar, play-services-base.aar,play-services-basement.jar, play-services-basement.aar,play-services-flags.jar, play-services-flags.aar,play-services-tasks.jar, play-services-tasks.aar")
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET, android.permission.ACCESS_NETWORK_STATE, android.permission.WAKE_LOCK, com.google.android.c2dm.permission.RECEIVE")
public class KodularFirebaseAuthentication extends AndroidNonvisibleComponent implements ActivityResultListener {
    private static final String LOG_TAG = "KodularFirebaseAuth";
    private static final String PROVIDER_EMAIL_PASSWORD = "EmailPassword";
    private static final String PROVIDER_GOOGLE = "Google";
    private static final String PROVIDER_PHONE_NUMBER = "PhoneNumber";
    private static final String PROVIDER_PLAY_GAMES = "Play Games";
    private static final String STORAGE_TAG = "KodularFirebaseAuth";
    private Activity activity;
    private Context context;
    private int googleRequestCode = 0;
    private boolean isCompanion = false;
    /* access modifiers changed from: private */
    public FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient = null;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mPhoneAuthCallbacks;
    private GoogleSignInClient mPlayGamesSignInClient = null;
    private String mVerificationId = null;
    private boolean phoneNewUser = true;
    private int playGamesRequestCode = 0;
    /* access modifiers changed from: private */
    public SharedPreferences preferences;

    static /* synthetic */ String access$202(KodularFirebaseAuthentication kodularFirebaseAuthentication, String str) {
        String str2 = str;
        String str3 = str2;
        kodularFirebaseAuthentication.mVerificationId = str3;
        return str2;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public KodularFirebaseAuthentication(com.google.appinventor.components.runtime.ComponentContainer r8) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            r3 = 0
            r2.isCompanion = r3
            r2 = r0
            r3 = 0
            r2.mGoogleSignInClient = r3
            r2 = r0
            r3 = 0
            r2.mPlayGamesSignInClient = r3
            r2 = r0
            r3 = 0
            r2.mVerificationId = r3
            r2 = r0
            r3 = 1
            r2.phoneNewUser = r3
            r2 = r0
            r3 = 0
            r2.googleRequestCode = r3
            r2 = r0
            r3 = 0
            r2.playGamesRequestCode = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.activity = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            r2 = r1
            com.google.appinventor.components.runtime.Form r2 = r2.$form()
            boolean r2 = r2 instanceof com.google.appinventor.components.runtime.ReplForm
            if (r2 == 0) goto L_0x0044
            r2 = r0
            r3 = 1
            r2.isCompanion = r3
        L_0x0044:
            r2 = r0
            r6 = r2
            r2 = r6
            r3 = r6
            android.content.Context r3 = r3.context
            java.lang.String r4 = "KodularFirebaseAuth"
            r5 = 0
            android.content.SharedPreferences r3 = r3.getSharedPreferences(r4, r5)
            r2.preferences = r3
            r2 = r0
            com.google.firebase.auth.FirebaseAuth r3 = com.google.firebase.auth.FirebaseAuth.getInstance()
            r2.mAuth = r3
            r2 = r0
            com.google.appinventor.components.runtime.KodularFirebaseAuthentication$1 r3 = new com.google.appinventor.components.runtime.KodularFirebaseAuthentication$1
            r6 = r3
            r3 = r6
            r4 = r6
            r5 = r0
            r4.<init>(r5)
            r2.mPhoneAuthCallbacks = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.KodularFirebaseAuthentication.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleFunction(description = "Check if the user is signed in")
    public boolean IsSignedIn() {
        return this.mAuth.getCurrentUser() != null;
    }

    @SimpleFunction(description = "Try to get the user that is signed in")
    public void GetCurrentUser() {
        FirebaseUser currentUser = this.mAuth.getCurrentUser();
        FirebaseUser firebaseUser = currentUser;
        if (currentUser != null) {
            CurrentUserSuccess(firebaseUser.getUid(), firebaseUser.getDisplayName(), firebaseUser.getEmail(), firebaseUser.getPhoneNumber(), firebaseUser.getPhotoUrl() == null ? "" : firebaseUser.getPhotoUrl().toString());
        } else {
            CurrentUserFailed();
        }
    }

    @SimpleFunction(description = "Try to let the user sign in with Email and Password")
    public void EmailPasswordLogin(String str, String str2) {
        OnCompleteListener onCompleteListener;
        CompanionToast();
        new OnCompleteListener<AuthResult>(this) {
            private /* synthetic */ KodularFirebaseAuthentication hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser currentUser = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.mAuth.getCurrentUser();
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.LoginSuccess(KodularFirebaseAuthentication.PROVIDER_EMAIL_PASSWORD, currentUser.getUid(), currentUser.getDisplayName(), currentUser.getEmail(), currentUser.getPhoneNumber(), currentUser.getPhotoUrl() == null ? "" : currentUser.getPhotoUrl().toString());
                    return;
                }
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.LoginFailed(KodularFirebaseAuthentication.PROVIDER_EMAIL_PASSWORD);
            }
        };
        Task addOnCompleteListener = this.mAuth.signInWithEmailAndPassword(str, str2).addOnCompleteListener(this.activity, onCompleteListener);
    }

    @SimpleFunction(description = "Create a new user by Email and Password")
    public void EmailPasswordSignUp(String str, String str2) {
        OnCompleteListener onCompleteListener;
        CompanionToast();
        new OnCompleteListener<AuthResult>(this) {
            private /* synthetic */ KodularFirebaseAuthentication hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void onComplete(@NonNull Task<AuthResult> task) {
                StringBuilder sb;
                Task<AuthResult> task2 = task;
                if (task2.isSuccessful()) {
                    FirebaseUser currentUser = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.mAuth.getCurrentUser();
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.SignUpSuccess(KodularFirebaseAuthentication.PROVIDER_EMAIL_PASSWORD, currentUser.getUid(), currentUser.getDisplayName(), currentUser.getEmail(), currentUser.getPhoneNumber(), currentUser.getPhotoUrl() == null ? "" : currentUser.getPhotoUrl().toString());
                    return;
                }
                KodularFirebaseAuthentication kodularFirebaseAuthentication = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                new StringBuilder();
                kodularFirebaseAuthentication.SignUpFailed(KodularFirebaseAuthentication.PROVIDER_EMAIL_PASSWORD, sb.append(task2.getException().getMessage()).toString());
            }
        };
        Task addOnCompleteListener = this.mAuth.createUserWithEmailAndPassword(str, str2).addOnCompleteListener(this.activity, onCompleteListener);
    }

    @SimpleFunction(description = "Try to let the user sign in with Google")
    public void GoogleSignIn() {
        StringBuilder sb;
        CompanionToast();
        if (this.googleRequestCode == 0) {
            this.googleRequestCode = this.form.registerForActivityResult(this);
        }
        new StringBuilder("Activity Started: ");
        int i = Log.i("KodularFirebaseAuth", sb.append(this.googleRequestCode).toString());
        this.activity.startActivityForResult(getGoogleSignInClient().getSignInIntent(), this.googleRequestCode);
    }

    public void PlayGamesSignIn() {
        StringBuilder sb;
        CompanionToast();
        if (this.playGamesRequestCode == 0) {
            this.playGamesRequestCode = this.form.registerForActivityResult(this);
        }
        new StringBuilder("Activity Started: ");
        int i = Log.i("KodularFirebaseAuth", sb.append(this.playGamesRequestCode).toString());
        this.activity.startActivityForResult(getPlayGamesSignInClient().getSignInIntent(), this.playGamesRequestCode);
    }

    @SimpleFunction(description = "Try to let the user sign in with a Phone Number")
    public void PhoneNumberSignIn(String str) {
        CompanionToast();
        this.phoneNewUser = true;
        PhoneAuthProvider.getInstance(this.mAuth).verifyPhoneNumber(str, 60, TimeUnit.SECONDS, this.activity, this.mPhoneAuthCallbacks);
    }

    @SimpleFunction(description = "Verify the SMS code")
    public void VerifyPhoneCode(String str) {
        String str2 = str;
        if (this.mVerificationId == null) {
            this.mVerificationId = this.preferences.getString("VerificationId", (String) null);
        }
        if (this.mVerificationId != null) {
            firebaseAuthWithPhoneNumber(PhoneAuthProvider.getCredential(this.mVerificationId, str2));
        }
        this.preferences.edit().remove("VerificationId").apply();
    }

    @SimpleFunction(description = "Verify the Email")
    public void VerifyEmail() {
        FirebaseUser currentUser = this.mAuth.getCurrentUser();
        FirebaseUser firebaseUser = currentUser;
        if (currentUser != null) {
            Task sendEmailVerification = firebaseUser.sendEmailVerification(ActionCodeSettings.newBuilder().setAndroidPackageName(this.form.getPackageName(), true, "16").build());
        }
    }

    @SimpleFunction(description = "Log the current user out")
    public void Logout() {
        this.mAuth.signOut();
        Task signOut = getGoogleSignInClient().signOut();
        Task signOut2 = getPlayGamesSignInClient().signOut();
    }

    @SimpleFunction(description = "Try to update the profile of the current user (The user must have been recently signed in)")
    public void UpdateProfile(String str, String str2) {
        UserProfileChangeRequest.Builder builder;
        OnCompleteListener onCompleteListener;
        String str3 = str;
        String str4 = str2;
        FirebaseUser currentUser = this.mAuth.getCurrentUser();
        FirebaseUser firebaseUser = currentUser;
        if (currentUser != null) {
            new UserProfileChangeRequest.Builder();
            UserProfileChangeRequest build = builder.setDisplayName(str3).setPhotoUri(Uri.parse(str4)).build();
            new OnCompleteListener<Void>(this) {
                private /* synthetic */ KodularFirebaseAuthentication hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                }

                public final void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.UserUpdateSuccess("Profile");
                    } else {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.UserUpdateFailed("Profile");
                    }
                }
            };
            Task addOnCompleteListener = firebaseUser.updateProfile(build).addOnCompleteListener(this.activity, onCompleteListener);
            return;
        }
        UserUpdateFailed("Profile");
    }

    @SimpleFunction(description = "Try to update the email of the current user (The user must have been recently signed in)")
    public void UpdateEmail(String str) {
        OnCompleteListener onCompleteListener;
        String str2 = str;
        FirebaseUser currentUser = this.mAuth.getCurrentUser();
        FirebaseUser firebaseUser = currentUser;
        if (currentUser != null) {
            new OnCompleteListener<Void>(this) {
                private /* synthetic */ KodularFirebaseAuthentication hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                }

                public final void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.UserUpdateSuccess("Email");
                    } else {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.UserUpdateFailed("Email");
                    }
                }
            };
            Task addOnCompleteListener = firebaseUser.updateEmail(str2).addOnCompleteListener(this.activity, onCompleteListener);
            return;
        }
        UserUpdateFailed("Email");
    }

    @SimpleFunction(description = "Try to update the phone number of the current user (The user must have been recently signed in)")
    public void UpdatePhoneNumber(String str) {
        String str2 = str;
        if (this.mAuth.getCurrentUser() != null) {
            this.phoneNewUser = false;
            PhoneAuthProvider.getInstance(this.mAuth).verifyPhoneNumber(str2, 60, TimeUnit.SECONDS, this.activity, this.mPhoneAuthCallbacks);
            return;
        }
        UserUpdateFailed(PROVIDER_PHONE_NUMBER);
    }

    @SimpleFunction(description = "Get the id token from the current user.")
    public void GetIdToken() {
        OnSuccessListener onSuccessListener;
        FirebaseUser currentUser = this.mAuth.getCurrentUser();
        FirebaseUser firebaseUser = currentUser;
        if (currentUser != null) {
            new OnSuccessListener<GetTokenResult>(this) {
                private /* synthetic */ KodularFirebaseAuthentication hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                }

                public final /* synthetic */ void onSuccess(Object obj) {
                    String token = ((GetTokenResult) obj).getToken();
                    String str = token;
                    if (token != null) {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.GotIdToken(str);
                    } else {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.GotIdToken("");
                    }
                }
            };
            Task addOnSuccessListener = firebaseUser.getIdToken(true).addOnSuccessListener(onSuccessListener);
            return;
        }
        GotIdToken("");
    }

    @SimpleFunction(description = "Get the id token from the current user.")
    public void SendResetPasswordEmail(String str) {
        OnCompleteListener onCompleteListener;
        new OnCompleteListener<Void>(this) {
            private /* synthetic */ KodularFirebaseAuthentication hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.ResetPasswordEmailDone(true);
                } else {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.ResetPasswordEmailDone(false);
                }
            }
        };
        Task addOnCompleteListener = this.mAuth.sendPasswordResetEmail(str).addOnCompleteListener(onCompleteListener);
    }

    @SimpleEvent(description = "Triggers when the 'Get Id Token' got a result. If there was a error it returns a empty text.")
    public void GotIdToken(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "GotIdToken", str);
    }

    @SimpleEvent(description = "Triggers when the 'Send Reset Password Email' got a result. Returns true if the reset email was send.")
    public void ResetPasswordEmailDone(boolean z) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "ResetPasswordEmailDone", Boolean.valueOf(z));
    }

    @SimpleEvent(description = "Triggers when the Firebase Login was successful")
    public void LoginSuccess(String str, String str2, String str3, String str4, String str5, String str6) {
        String str7 = str2;
        String str8 = str3;
        String str9 = str4;
        String str10 = str5;
        String str11 = str6;
        Object[] objArr = new Object[6];
        objArr[0] = str;
        Object[] objArr2 = objArr;
        Object[] objArr3 = objArr2;
        objArr2[1] = str7 != null ? str7 : "";
        Object[] objArr4 = objArr3;
        Object[] objArr5 = objArr4;
        objArr4[2] = str8 != null ? str8 : "";
        Object[] objArr6 = objArr5;
        Object[] objArr7 = objArr6;
        objArr6[3] = str9 != null ? str9 : "";
        Object[] objArr8 = objArr7;
        Object[] objArr9 = objArr8;
        objArr8[4] = str10 != null ? str10 : "";
        Object[] objArr10 = objArr9;
        Object[] objArr11 = objArr10;
        objArr10[5] = str11 != null ? str11 : "";
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "LoginSuccess", objArr11);
    }

    @SimpleEvent(description = "Triggers when the Firebase Login failed")
    public void LoginFailed(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "LoginFailed", str);
    }

    @SimpleEvent(description = "Triggers when the Firebase Sign Up failed")
    public void SignUpFailed(String str, String str2) {
        Object[] objArr = new Object[2];
        objArr[0] = str;
        Object[] objArr2 = objArr;
        objArr2[1] = str2;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "SignUpFailed", objArr2);
    }

    @SimpleEvent(description = "Triggers when the Firebase Sign Up failed")
    public void SignUpSuccess(String str, String str2, String str3, String str4, String str5, String str6) {
        String str7 = str2;
        String str8 = str3;
        String str9 = str4;
        String str10 = str5;
        String str11 = str6;
        Object[] objArr = new Object[6];
        objArr[0] = str;
        Object[] objArr2 = objArr;
        Object[] objArr3 = objArr2;
        objArr2[1] = str7 != null ? str7 : "";
        Object[] objArr4 = objArr3;
        Object[] objArr5 = objArr4;
        objArr4[2] = str8 != null ? str8 : "";
        Object[] objArr6 = objArr5;
        Object[] objArr7 = objArr6;
        objArr6[3] = str9 != null ? str9 : "";
        Object[] objArr8 = objArr7;
        Object[] objArr9 = objArr8;
        objArr8[4] = str10 != null ? str10 : "";
        Object[] objArr10 = objArr9;
        Object[] objArr11 = objArr10;
        objArr10[5] = str11 != null ? str11 : "";
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "SignUpSuccess", objArr11);
    }

    @SimpleEvent(description = "Triggers when updating of the user was successful")
    public void UserUpdateSuccess(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "UserUpdateSuccess", str);
    }

    @SimpleEvent(description = "Triggers when updating of the user failed")
    public void UserUpdateFailed(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "UserUpdateFailed", str);
    }

    @SimpleEvent(description = "Triggers when the current Firebase User was successful loaded")
    public void CurrentUserSuccess(String str, String str2, String str3, String str4, String str5) {
        String str6 = str;
        String str7 = str2;
        String str8 = str3;
        String str9 = str4;
        String str10 = str5;
        Object[] objArr = new Object[5];
        Object[] objArr2 = objArr;
        objArr[0] = str6 != null ? str6 : "";
        Object[] objArr3 = objArr2;
        Object[] objArr4 = objArr3;
        objArr3[1] = str7 != null ? str7 : "";
        Object[] objArr5 = objArr4;
        Object[] objArr6 = objArr5;
        objArr5[2] = str8 != null ? str8 : "";
        Object[] objArr7 = objArr6;
        Object[] objArr8 = objArr7;
        objArr7[3] = str9 != null ? str9 : "";
        Object[] objArr9 = objArr8;
        Object[] objArr10 = objArr9;
        objArr9[4] = str10 != null ? str10 : "";
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "CurrentUserSuccess", objArr10);
    }

    @SimpleEvent(description = "Triggers when the current Firebase User failed to load")
    public void CurrentUserFailed() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "CurrentUserFailed", new Object[0]);
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount googleSignInAccount) {
        StringBuilder sb;
        GoogleSignInAccount googleSignInAccount2 = googleSignInAccount;
        new StringBuilder("firebaseAuthWithGoogle:");
        int d = Log.d("KodularFirebaseAuth", sb.append(googleSignInAccount2.getId()).toString());
        firebaseAuthWithCredential(GoogleAuthProvider.getCredential(googleSignInAccount2.getIdToken(), (String) null), PROVIDER_GOOGLE);
    }

    private void firebaseAuthWithPlayGames(GoogleSignInAccount googleSignInAccount) {
        StringBuilder sb;
        GoogleSignInAccount googleSignInAccount2 = googleSignInAccount;
        new StringBuilder("firebaseAuthWithPlayGames:");
        int d = Log.d("KodularFirebaseAuth", sb.append(googleSignInAccount2.getId()).toString());
        String serverAuthCode = googleSignInAccount2.getServerAuthCode();
        String str = serverAuthCode;
        if (serverAuthCode != null) {
            firebaseAuthWithCredential(PlayGamesAuthProvider.getCredential(str), PROVIDER_PLAY_GAMES);
            return;
        }
        LoginFailed(PROVIDER_PLAY_GAMES);
    }

    private void firebaseAuthWithCredential(AuthCredential authCredential, String str) {
        OnCompleteListener onCompleteListener;
        final String str2 = str;
        new OnCompleteListener<AuthResult>(this) {
            private /* synthetic */ KodularFirebaseAuthentication hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
            }

            public final void onComplete(@NonNull Task<AuthResult> task) {
                Task<AuthResult> task2 = task;
                if (task2.isSuccessful()) {
                    int d = Log.d("KodularFirebaseAuth", "signInWithCredential:success");
                    FirebaseUser currentUser = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.mAuth.getCurrentUser();
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.LoginSuccess(str2, currentUser.getUid(), currentUser.getDisplayName(), currentUser.getEmail(), currentUser.getPhoneNumber(), currentUser.getPhotoUrl() == null ? "" : currentUser.getPhotoUrl().toString());
                    return;
                }
                int w = Log.w("KodularFirebaseAuth", "signInWithCredential:failure", task2.getException());
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.LoginFailed(str2);
            }
        };
        Task addOnCompleteListener = this.mAuth.signInWithCredential(authCredential).addOnCompleteListener(this.activity, onCompleteListener);
    }

    /* access modifiers changed from: private */
    public void firebaseAuthWithPhoneNumber(PhoneAuthCredential phoneAuthCredential) {
        OnCompleteListener onCompleteListener;
        OnCompleteListener onCompleteListener2;
        PhoneAuthCredential phoneAuthCredential2 = phoneAuthCredential;
        if (this.phoneNewUser) {
            new OnCompleteListener<AuthResult>(this) {
                private /* synthetic */ KodularFirebaseAuthentication hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                }

                public final void onComplete(@NonNull Task<AuthResult> task) {
                    Task<AuthResult> task2 = task;
                    if (task2.isSuccessful()) {
                        int d = Log.d("KodularFirebaseAuth", "signInWithCredential:success");
                        FirebaseUser currentUser = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.mAuth.getCurrentUser();
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.LoginSuccess(KodularFirebaseAuthentication.PROVIDER_PHONE_NUMBER, currentUser.getUid(), currentUser.getDisplayName(), currentUser.getEmail(), currentUser.getPhoneNumber(), currentUser.getPhotoUrl() == null ? "" : currentUser.getPhotoUrl().toString());
                        return;
                    }
                    int w = Log.w("KodularFirebaseAuth", "signInWithCredential:failure", task2.getException());
                    Exception exception = task2.getException();
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.LoginFailed(KodularFirebaseAuthentication.PROVIDER_PHONE_NUMBER);
                }
            };
            Task addOnCompleteListener = this.mAuth.signInWithCredential(phoneAuthCredential2).addOnCompleteListener(this.activity, onCompleteListener2);
            return;
        }
        FirebaseUser currentUser = this.mAuth.getCurrentUser();
        FirebaseUser firebaseUser = currentUser;
        if (currentUser != null) {
            new OnCompleteListener<Void>(this) {
                private /* synthetic */ KodularFirebaseAuthentication hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                }

                public final void onComplete(Task<Void> task) {
                    if (task.isSuccessful()) {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.UserUpdateSuccess(KodularFirebaseAuthentication.PROVIDER_PHONE_NUMBER);
                    } else {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.UserUpdateFailed(KodularFirebaseAuthentication.PROVIDER_PHONE_NUMBER);
                    }
                }
            };
            Task addOnCompleteListener2 = firebaseUser.updatePhoneNumber(phoneAuthCredential2).addOnCompleteListener(this.activity, onCompleteListener);
        }
    }

    private GoogleSignInClient getGoogleSignInClient() {
        GoogleSignInOptions.Builder builder;
        if (this.mGoogleSignInClient == null) {
            new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN);
            GoogleSignInOptions build = builder.requestIdToken(KodularResourcesUtil.getString(this.context, "default_web_client_id")).requestEmail().build();
            this.mGoogleSignInClient = GoogleSignIn.getClient(this.context, build);
        }
        return this.mGoogleSignInClient;
    }

    private GoogleSignInClient getPlayGamesSignInClient() {
        if (this.mPlayGamesSignInClient == null) {
            this.mPlayGamesSignInClient = GoogleSignIn.getClient(this.context, GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN);
        }
        return this.mPlayGamesSignInClient;
    }

    private void CompanionToast() {
        if (this.isCompanion) {
            Toast.makeText(this.context, "Firebase Auth doesn't work in the Companion", 1).show();
        }
    }

    public void resultReturned(int i, int i2, Intent intent) {
        StringBuilder sb;
        StringBuilder sb2;
        int i3 = i;
        int i4 = i2;
        Intent intent2 = intent;
        int i5 = Log.i("KodularFirebaseAuth", "Result Returned: ".concat(String.valueOf(i3)));
        if (i3 == this.googleRequestCode) {
            int i6 = Log.i("KodularFirebaseAuth", "Google Sign in Result Returned");
            try {
                firebaseAuthWithGoogle((GoogleSignInAccount) GoogleSignIn.getSignedInAccountFromIntent(intent2).getResult(ApiException.class));
            } catch (ApiException e) {
                ApiException apiException = e;
                new StringBuilder("Google sign in failed: ");
                int w = Log.w("KodularFirebaseAuth", sb2.append(apiException.getMessage()).toString(), apiException);
                LoginFailed(PROVIDER_GOOGLE);
            }
        } else if (i3 == this.playGamesRequestCode) {
            int i7 = Log.i("KodularFirebaseAuth", "Play Games Sign in Result Returned");
            try {
                firebaseAuthWithPlayGames((GoogleSignInAccount) GoogleSignIn.getSignedInAccountFromIntent(intent2).getResult(ApiException.class));
            } catch (ApiException e2) {
                ApiException apiException2 = e2;
                new StringBuilder("Play Games sign in failed: ");
                int w2 = Log.w("KodularFirebaseAuth", sb.append(apiException2.getMessage()).toString(), apiException2);
                LoginFailed(PROVIDER_PLAY_GAMES);
            }
        }
    }
}

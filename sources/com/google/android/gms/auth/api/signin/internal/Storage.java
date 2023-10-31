package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONException;

public class Storage {
    private static final Lock zaaj;
    @GuardedBy("sLk")
    private static Storage zaak;
    private final Lock zaal;
    @GuardedBy("mLk")
    private final SharedPreferences zaam;

    /* JADX INFO: finally extract failed */
    @KeepForSdk
    public static Storage getInstance(Context context) {
        Storage storage;
        Context context2 = context;
        Object checkNotNull = Preconditions.checkNotNull(context2);
        zaaj.lock();
        try {
            if (zaak == null) {
                new Storage(context2.getApplicationContext());
                zaak = storage;
            }
            Storage storage2 = zaak;
            zaaj.unlock();
            return storage2;
        } catch (Throwable th) {
            Throwable th2 = th;
            zaaj.unlock();
            throw th2;
        }
    }

    @VisibleForTesting
    private Storage(Context context) {
        Lock lock;
        new ReentrantLock();
        this.zaal = lock;
        this.zaam = context.getSharedPreferences("com.google.android.gms.signin", 0);
    }

    @KeepForSdk
    public void saveDefaultGoogleSignInAccount(GoogleSignInAccount googleSignInAccount, GoogleSignInOptions googleSignInOptions) {
        GoogleSignInAccount googleSignInAccount2 = googleSignInAccount;
        GoogleSignInOptions googleSignInOptions2 = googleSignInOptions;
        Object checkNotNull = Preconditions.checkNotNull(googleSignInAccount2);
        Object checkNotNull2 = Preconditions.checkNotNull(googleSignInOptions2);
        zaa("defaultGoogleSignInAccount", googleSignInAccount2.zab());
        GoogleSignInOptions googleSignInOptions3 = googleSignInOptions2;
        GoogleSignInAccount googleSignInAccount3 = googleSignInAccount2;
        Object checkNotNull3 = Preconditions.checkNotNull(googleSignInAccount3);
        Object checkNotNull4 = Preconditions.checkNotNull(googleSignInOptions3);
        String zab = googleSignInAccount3.zab();
        zaa(zab("googleSignInAccount", zab), googleSignInAccount3.zac());
        zaa(zab("googleSignInOptions", zab), googleSignInOptions3.zae());
    }

    private final void zaa(String str, String str2) {
        String str3 = str;
        String str4 = str2;
        this.zaal.lock();
        try {
            this.zaam.edit().putString(str3, str4).apply();
            this.zaal.unlock();
        } catch (Throwable th) {
            Throwable th2 = th;
            this.zaal.unlock();
            throw th2;
        }
    }

    @KeepForSdk
    @Nullable
    public GoogleSignInAccount getSavedDefaultGoogleSignInAccount() {
        return zad(zaf("defaultGoogleSignInAccount"));
    }

    @Nullable
    @VisibleForTesting
    private final GoogleSignInAccount zad(String str) {
        String str2 = str;
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        String zaf = zaf(zab("googleSignInAccount", str2));
        if (zaf == null) {
            return null;
        }
        try {
            return GoogleSignInAccount.zaa(zaf);
        } catch (JSONException e) {
            return null;
        }
    }

    @KeepForSdk
    @Nullable
    public GoogleSignInOptions getSavedDefaultGoogleSignInOptions() {
        return zae(zaf("defaultGoogleSignInAccount"));
    }

    @Nullable
    @VisibleForTesting
    private final GoogleSignInOptions zae(String str) {
        String str2 = str;
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        String zaf = zaf(zab("googleSignInOptions", str2));
        if (zaf == null) {
            return null;
        }
        try {
            return GoogleSignInOptions.zab(zaf);
        } catch (JSONException e) {
            return null;
        }
    }

    @KeepForSdk
    @Nullable
    public String getSavedRefreshToken() {
        return zaf("refreshToken");
    }

    /* JADX INFO: finally extract failed */
    @Nullable
    private final String zaf(String str) {
        String str2 = str;
        this.zaal.lock();
        try {
            String string = this.zaam.getString(str2, (String) null);
            this.zaal.unlock();
            return string;
        } catch (Throwable th) {
            Throwable th2 = th;
            this.zaal.unlock();
            throw th2;
        }
    }

    public final void zaf() {
        String zaf = zaf("defaultGoogleSignInAccount");
        zag("defaultGoogleSignInAccount");
        String str = zaf;
        if (!TextUtils.isEmpty(str)) {
            zag(zab("googleSignInAccount", str));
            zag(zab("googleSignInOptions", str));
        }
    }

    private final void zag(String str) {
        String str2 = str;
        this.zaal.lock();
        try {
            this.zaam.edit().remove(str2).apply();
            this.zaal.unlock();
        } catch (Throwable th) {
            Throwable th2 = th;
            this.zaal.unlock();
            throw th2;
        }
    }

    @KeepForSdk
    public void clear() {
        this.zaal.lock();
        try {
            this.zaam.edit().clear().apply();
            this.zaal.unlock();
        } catch (Throwable th) {
            Throwable th2 = th;
            this.zaal.unlock();
            throw th2;
        }
    }

    private static String zab(String str, String str2) {
        StringBuilder sb;
        String str3 = str;
        String str4 = str2;
        new StringBuilder(1 + String.valueOf(str3).length() + String.valueOf(str4).length());
        return sb.append(str3).append(":").append(str4).toString();
    }

    static {
        Lock lock;
        new ReentrantLock();
        zaaj = lock;
    }
}

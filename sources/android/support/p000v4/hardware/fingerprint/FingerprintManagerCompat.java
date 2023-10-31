package android.support.p000v4.hardware.fingerprint;

import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RequiresPermission;
import android.support.p000v4.p002os.CancellationSignal;
import java.security.Signature;
import javax.crypto.Cipher;
import javax.crypto.Mac;

/* renamed from: android.support.v4.hardware.fingerprint.FingerprintManagerCompat */
public final class FingerprintManagerCompat {
    private final Context mContext;

    @NonNull
    public static FingerprintManagerCompat from(@NonNull Context context) {
        FingerprintManagerCompat fingerprintManagerCompat;
        new FingerprintManagerCompat(context);
        return fingerprintManagerCompat;
    }

    private FingerprintManagerCompat(Context context) {
        this.mContext = context;
    }

    @RequiresPermission("android.permission.USE_FINGERPRINT")
    public boolean hasEnrolledFingerprints() {
        if (Build.VERSION.SDK_INT < 23) {
            return false;
        }
        FingerprintManager fp = getFingerprintManagerOrNull(this.mContext);
        return fp != null && fp.hasEnrolledFingerprints();
    }

    @RequiresPermission("android.permission.USE_FINGERPRINT")
    public boolean isHardwareDetected() {
        if (Build.VERSION.SDK_INT < 23) {
            return false;
        }
        FingerprintManager fp = getFingerprintManagerOrNull(this.mContext);
        return fp != null && fp.isHardwareDetected();
    }

    @RequiresPermission("android.permission.USE_FINGERPRINT")
    public void authenticate(@Nullable CryptoObject cryptoObject, int i, @Nullable CancellationSignal cancellationSignal, @NonNull AuthenticationCallback authenticationCallback, @Nullable Handler handler) {
        FingerprintManager fp;
        CryptoObject crypto = cryptoObject;
        int flags = i;
        CancellationSignal cancel = cancellationSignal;
        AuthenticationCallback callback = authenticationCallback;
        Handler handler2 = handler;
        if (Build.VERSION.SDK_INT >= 23 && (fp = getFingerprintManagerOrNull(this.mContext)) != null) {
            fp.authenticate(wrapCryptoObject(crypto), cancel != null ? (android.os.CancellationSignal) cancel.getCancellationSignalObject() : null, flags, wrapCallback(callback), handler2);
        }
    }

    @Nullable
    @RequiresApi(23)
    private static FingerprintManager getFingerprintManagerOrNull(@NonNull Context context) {
        Context context2 = context;
        if (context2.getPackageManager().hasSystemFeature("android.hardware.fingerprint")) {
            return (FingerprintManager) context2.getSystemService(FingerprintManager.class);
        }
        return null;
    }

    @RequiresApi(23)
    private static FingerprintManager.CryptoObject wrapCryptoObject(CryptoObject cryptoObject) {
        FingerprintManager.CryptoObject cryptoObject2;
        FingerprintManager.CryptoObject cryptoObject3;
        FingerprintManager.CryptoObject cryptoObject4;
        CryptoObject cryptoObject5 = cryptoObject;
        if (cryptoObject5 == null) {
            return null;
        }
        if (cryptoObject5.getCipher() != null) {
            new FingerprintManager.CryptoObject(cryptoObject5.getCipher());
            return cryptoObject4;
        } else if (cryptoObject5.getSignature() != null) {
            new FingerprintManager.CryptoObject(cryptoObject5.getSignature());
            return cryptoObject3;
        } else if (cryptoObject5.getMac() == null) {
            return null;
        } else {
            new FingerprintManager.CryptoObject(cryptoObject5.getMac());
            return cryptoObject2;
        }
    }

    @RequiresApi(23)
    static CryptoObject unwrapCryptoObject(FingerprintManager.CryptoObject cryptoObject) {
        CryptoObject cryptoObject2;
        CryptoObject cryptoObject3;
        CryptoObject cryptoObject4;
        FingerprintManager.CryptoObject cryptoObject5 = cryptoObject;
        if (cryptoObject5 == null) {
            return null;
        }
        if (cryptoObject5.getCipher() != null) {
            new CryptoObject(cryptoObject5.getCipher());
            return cryptoObject4;
        } else if (cryptoObject5.getSignature() != null) {
            new CryptoObject(cryptoObject5.getSignature());
            return cryptoObject3;
        } else if (cryptoObject5.getMac() == null) {
            return null;
        } else {
            new CryptoObject(cryptoObject5.getMac());
            return cryptoObject2;
        }
    }

    @RequiresApi(23)
    private static FingerprintManager.AuthenticationCallback wrapCallback(AuthenticationCallback callback) {
        FingerprintManager.AuthenticationCallback authenticationCallback;
        final AuthenticationCallback authenticationCallback2 = callback;
        new FingerprintManager.AuthenticationCallback() {
            public void onAuthenticationError(int errMsgId, CharSequence errString) {
                authenticationCallback2.onAuthenticationError(errMsgId, errString);
            }

            public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
                authenticationCallback2.onAuthenticationHelp(helpMsgId, helpString);
            }

            public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
                AuthenticationResult authenticationResult;
                new AuthenticationResult(FingerprintManagerCompat.unwrapCryptoObject(result.getCryptoObject()));
                authenticationCallback2.onAuthenticationSucceeded(authenticationResult);
            }

            public void onAuthenticationFailed() {
                authenticationCallback2.onAuthenticationFailed();
            }
        };
        return authenticationCallback;
    }

    /* renamed from: android.support.v4.hardware.fingerprint.FingerprintManagerCompat$CryptoObject */
    public static class CryptoObject {
        private final Cipher mCipher;
        private final Mac mMac;
        private final Signature mSignature;

        public CryptoObject(@NonNull Signature signature) {
            this.mSignature = signature;
            this.mCipher = null;
            this.mMac = null;
        }

        public CryptoObject(@NonNull Cipher cipher) {
            this.mCipher = cipher;
            this.mSignature = null;
            this.mMac = null;
        }

        public CryptoObject(@NonNull Mac mac) {
            this.mMac = mac;
            this.mCipher = null;
            this.mSignature = null;
        }

        @Nullable
        public Signature getSignature() {
            return this.mSignature;
        }

        @Nullable
        public Cipher getCipher() {
            return this.mCipher;
        }

        @Nullable
        public Mac getMac() {
            return this.mMac;
        }
    }

    /* renamed from: android.support.v4.hardware.fingerprint.FingerprintManagerCompat$AuthenticationResult */
    public static final class AuthenticationResult {
        private final CryptoObject mCryptoObject;

        public AuthenticationResult(CryptoObject crypto) {
            this.mCryptoObject = crypto;
        }

        public CryptoObject getCryptoObject() {
            return this.mCryptoObject;
        }
    }

    /* renamed from: android.support.v4.hardware.fingerprint.FingerprintManagerCompat$AuthenticationCallback */
    public static abstract class AuthenticationCallback {
        public AuthenticationCallback() {
        }

        public void onAuthenticationError(int errMsgId, CharSequence errString) {
        }

        public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
        }

        public void onAuthenticationSucceeded(AuthenticationResult result) {
        }

        public void onAuthenticationFailed() {
        }
    }
}

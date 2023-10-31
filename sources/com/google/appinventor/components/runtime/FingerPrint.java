package com.google.appinventor.components.runtime;

import android.app.Dialog;
import android.app.KeyguardManager;
import android.content.Context;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.support.p000v4.app.ActivityCompat;
import android.support.p000v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.p000v4.p002os.CancellationSignal;
import android.util.Log;
import android.widget.TextView;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.TextViewUtil;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

@SimpleObject
@DesignerComponent(category = ComponentCategory.SENSORS, description = "Component to check for a FingerScanner and read fingerprints from the scanner", helpUrl = "https://docs.kodular.io/components/sensors/fingerprint/", iconName = "images/fingerprint.png", nonVisible = true, version = 1)
@UsesPermissions(permissionNames = "android.permission.USE_FINGERPRINT")
public final class FingerPrint extends AndroidNonvisibleComponent implements Component {
    private TextView B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    private String EFLFxJJ7hYZabk7bFItjKUUJ7DOOvUWrCh3qTHGqJQXtWFnrbkhe9SsRqCU9oxdB = "Sign in with your fingerprint";
    private Context context;
    private Dialog hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private KeyguardManager f380hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private FingerprintManagerCompat.AuthenticationCallback f381hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private FingerprintManagerCompat.CryptoObject f382hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private FingerprintManagerCompat f383hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private CancellationSignal f384hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private TextView f385hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private KeyStore f386hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private Cipher f387hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private String jKqYCd0kbp4PLjuOSuX9UMjydG4JrQByekpZGS3DgrCgeBLPmjJ5QsHwhJoPxxWm = "Scan your finger";
    private boolean qPA23Y7cwrM2jVYdTZaCOyuoTEsak9zRoCsFocZMlDYlamZRzkT9xY09N4QNxUyM = true;
    private boolean sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp = false;
    private TextView wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;

    static /* synthetic */ void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(FingerPrint fingerPrint, String str, int i) {
        int i2 = i;
        FingerPrint fingerPrint2 = fingerPrint;
        FingerPrint fingerPrint3 = fingerPrint2;
        fingerPrint2.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.setText(TextViewUtil.fromHtml(str));
        fingerPrint3.f385hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setTextColor(i2);
        fingerPrint3.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.setTextColor(i2);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public FingerPrint(com.google.appinventor.components.runtime.ComponentContainer r8) {
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
            r2.sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp = r3
            r2 = r0
            r3 = 1
            r2.qPA23Y7cwrM2jVYdTZaCOyuoTEsak9zRoCsFocZMlDYlamZRzkT9xY09N4QNxUyM = r3
            r2 = r0
            java.lang.String r3 = "Sign in with your fingerprint"
            r2.EFLFxJJ7hYZabk7bFItjKUUJ7DOOvUWrCh3qTHGqJQXtWFnrbkhe9SsRqCU9oxdB = r3
            r2 = r0
            java.lang.String r3 = "Scan your finger"
            r2.jKqYCd0kbp4PLjuOSuX9UMjydG4JrQByekpZGS3DgrCgeBLPmjJ5QsHwhJoPxxWm = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            r2 = r0
            r6 = r2
            r2 = r6
            r3 = r6
            android.content.Context r3 = r3.context
            android.support.v4.hardware.fingerprint.FingerprintManagerCompat r3 = android.support.p000v4.hardware.fingerprint.FingerprintManagerCompat.from(r3)
            r2.f383hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r0
            r6 = r2
            r2 = r6
            r3 = r6
            android.content.Context r3 = r3.context
            java.lang.String r4 = "keyguard"
            java.lang.Object r3 = r3.getSystemService(r4)
            android.app.KeyguardManager r3 = (android.app.KeyguardManager) r3
            r2.f380hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r0
            android.app.Dialog r3 = new android.app.Dialog
            r6 = r3
            r3 = r6
            r4 = r6
            r5 = r0
            android.content.Context r5 = r5.context
            r4.<init>(r5)
            r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r0
            android.support.v4.os.CancellationSignal r3 = new android.support.v4.os.CancellationSignal
            r6 = r3
            r3 = r6
            r4 = r6
            r4.<init>()
            r2.f384hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            java.lang.String r2 = "FingerPrint"
            java.lang.String r3 = "FingerPrint Created"
            int r2 = android.util.Log.d(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.FingerPrint.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleFunction(description = "True if hardware is present and functional, false otherwise")
    public final boolean HasFingerPrintScanner() {
        if (ActivityCompat.checkSelfPermission(this.context, "android.permission.USE_FINGERPRINT") == 0) {
            return this.f383hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isHardwareDetected();
        }
        return false;
    }

    @SimpleFunction(description = "True if at least one fingerprint is enrolled, false otherwise")
    public final boolean HasFingersAdded() {
        if (ActivityCompat.checkSelfPermission(this.context, "android.permission.USE_FINGERPRINT") == 0) {
            return this.f383hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hasEnrolledFingerprints();
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:42:? A[RETURN, SYNTHETIC] */
    @com.google.appinventor.components.annotations.SimpleFunction(description = "Authenticate the user with a Fingerprint scanner")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void Authenticate() {
        /*
            r13 = this;
            r1 = r13
            int r6 = android.os.Build.VERSION.SDK_INT
            r7 = 23
            if (r6 < r7) goto L_0x026f
            r6 = r1
            android.content.Context r6 = r6.context
            java.lang.String r7 = "android.permission.USE_FINGERPRINT"
            int r6 = android.support.p000v4.app.ActivityCompat.checkSelfPermission(r6, r7)
            if (r6 != 0) goto L_0x026f
            r6 = r1
            r2 = r6
            int r6 = android.os.Build.VERSION.SDK_INT
            r7 = 23
            if (r6 < r7) goto L_0x002e
            r6 = r2
            android.support.v4.hardware.fingerprint.FingerprintManagerCompat r6 = r6.f383hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            boolean r6 = r6.isHardwareDetected()
            if (r6 != 0) goto L_0x0270
            java.lang.String r6 = "FingerPrint"
            java.lang.String r7 = "Fingerprint authentication not supported"
            int r6 = android.util.Log.d(r6, r7)
        L_0x002e:
            r6 = 0
        L_0x002f:
            if (r6 == 0) goto L_0x026f
            r6 = r1
            r6.generateKey()
            r6 = r1
            boolean r6 = r6.cipherInit()
            if (r6 == 0) goto L_0x026f
            r6 = r1
            android.support.v4.hardware.fingerprint.FingerprintManagerCompat$CryptoObject r7 = new android.support.v4.hardware.fingerprint.FingerprintManagerCompat$CryptoObject
            r12 = r7
            r7 = r12
            r8 = r12
            r9 = r1
            javax.crypto.Cipher r9 = r9.f387hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r8.<init>((javax.crypto.Cipher) r9)
            r6.f382hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7
            r6 = r1
            com.google.appinventor.components.runtime.FingerPrint$1 r7 = new com.google.appinventor.components.runtime.FingerPrint$1
            r12 = r7
            r7 = r12
            r8 = r12
            r9 = r1
            r8.<init>(r9)
            r6.f381hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7
            r6 = r1
            android.support.v4.os.CancellationSignal r7 = new android.support.v4.os.CancellationSignal
            r12 = r7
            r7 = r12
            r8 = r12
            r8.<init>()
            r6.f384hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7
            r6 = r1
            android.support.v4.hardware.fingerprint.FingerprintManagerCompat r6 = r6.f383hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r7 = r1
            android.support.v4.hardware.fingerprint.FingerprintManagerCompat$CryptoObject r7 = r7.f382hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r8 = 0
            r9 = r1
            android.support.v4.os.CancellationSignal r9 = r9.f384hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r10 = r1
            android.support.v4.hardware.fingerprint.FingerprintManagerCompat$AuthenticationCallback r10 = r10.f381hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r11 = 0
            r6.authenticate(r7, r8, r9, r10, r11)
            r6 = r1
            boolean r6 = r6.qPA23Y7cwrM2jVYdTZaCOyuoTEsak9zRoCsFocZMlDYlamZRzkT9xY09N4QNxUyM
            if (r6 == 0) goto L_0x026f
            r6 = r1
            android.app.Dialog r6 = r6.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            boolean r6 = r6.isShowing()
            if (r6 != 0) goto L_0x026f
            r6 = r1
            r12 = r6
            r6 = r12
            r7 = r12
            r2 = r7
            android.app.Dialog r7 = new android.app.Dialog
            r12 = r7
            r7 = r12
            r8 = r12
            r9 = r2
            android.content.Context r9 = r9.context
            r10 = 0
            r8.<init>(r9, r10)
            r6.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7
            r6 = r2
            android.content.Context r6 = r6.context
            android.content.res.AssetManager r6 = r6.getAssets()
            java.lang.String r7 = "MaterialIcons-Regular.ttf"
            android.graphics.Typeface r6 = android.graphics.Typeface.createFromAsset(r6, r7)
            r3 = r6
            r6 = r2
            boolean r6 = r6.sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp
            if (r6 == 0) goto L_0x00bc
            r6 = r2
            android.app.Dialog r6 = r6.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ NullPointerException -> 0x029d }
            android.view.Window r6 = r6.getWindow()     // Catch:{ NullPointerException -> 0x029d }
            android.view.View r6 = r6.getDecorView()     // Catch:{ NullPointerException -> 0x029d }
            android.graphics.drawable.Drawable r6 = r6.getBackground()     // Catch:{ NullPointerException -> 0x029d }
            r7 = -1
            android.graphics.PorterDuff$Mode r8 = android.graphics.PorterDuff.Mode.SRC_ATOP     // Catch:{ NullPointerException -> 0x029d }
            r6.setColorFilter(r7, r8)     // Catch:{ NullPointerException -> 0x029d }
        L_0x00bc:
            r6 = r2
            android.widget.TextView r7 = new android.widget.TextView
            r12 = r7
            r7 = r12
            r8 = r12
            r9 = r2
            android.content.Context r9 = r9.context
            r8.<init>(r9)
            r6.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = r7
            r6 = r2
            android.widget.TextView r6 = r6.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T
            r7 = r2
            java.lang.String r7 = r7.EFLFxJJ7hYZabk7bFItjKUUJ7DOOvUWrCh3qTHGqJQXtWFnrbkhe9SsRqCU9oxdB
            android.text.Spanned r7 = com.google.appinventor.components.runtime.util.TextViewUtil.fromHtml(r7)
            r6.setText(r7)
            r6 = r2
            android.widget.TextView r6 = r6.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T
            r7 = 1098907648(0x41800000, float:16.0)
            r6.setTextSize(r7)
            r6 = r2
            android.widget.TextView r6 = r6.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T
            android.graphics.Typeface r7 = android.graphics.Typeface.DEFAULT_BOLD
            r6.setTypeface(r7)
            r6 = r2
            android.widget.TextView r6 = r6.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T
            r7 = r2
            boolean r7 = r7.sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp
            if (r7 == 0) goto L_0x02a0
            r7 = -16777216(0xffffffffff000000, float:-1.7014118E38)
        L_0x00f1:
            r6.setTextColor(r7)
            r6 = r2
            android.widget.TextView r6 = r6.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T
            r7 = 1
            r6.setGravity(r7)
            r6 = r2
            android.widget.TextView r7 = new android.widget.TextView
            r12 = r7
            r7 = r12
            r8 = r12
            r9 = r2
            android.content.Context r9 = r9.context
            r8.<init>(r9)
            r6.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = r7
            r6 = r2
            android.widget.TextView r6 = r6.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou
            r7 = r2
            java.lang.String r7 = r7.jKqYCd0kbp4PLjuOSuX9UMjydG4JrQByekpZGS3DgrCgeBLPmjJ5QsHwhJoPxxWm
            android.text.Spanned r7 = com.google.appinventor.components.runtime.util.TextViewUtil.fromHtml(r7)
            r6.setText(r7)
            r6 = r2
            android.widget.TextView r6 = r6.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou
            r7 = 1098907648(0x41800000, float:16.0)
            r6.setTextSize(r7)
            r6 = r2
            android.widget.TextView r6 = r6.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou
            r7 = -6381922(0xffffffffff9e9e9e, float:NaN)
            r6.setTextColor(r7)
            r6 = r2
            android.widget.TextView r6 = r6.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou
            r7 = 1
            r6.setGravity(r7)
            r6 = r2
            android.widget.TextView r7 = new android.widget.TextView
            r12 = r7
            r7 = r12
            r8 = r12
            r9 = r2
            android.content.Context r9 = r9.context
            r8.<init>(r9)
            r6.f385hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7
            r6 = r2
            android.widget.TextView r6 = r6.f385hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            java.lang.String r7 = "&#xE90D;"
            android.text.Spanned r7 = com.google.appinventor.components.runtime.util.TextViewUtil.fromHtml(r7)
            r6.setText(r7)
            r6 = r2
            android.widget.TextView r6 = r6.f385hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r7 = 1116733440(0x42900000, float:72.0)
            r6.setTextSize(r7)
            r6 = r2
            android.widget.TextView r6 = r6.f385hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r7 = r3
            r8 = 0
            android.graphics.Typeface r7 = android.graphics.Typeface.create(r7, r8)
            r6.setTypeface(r7)
            r6 = r2
            android.widget.TextView r6 = r6.f385hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r7 = -6381922(0xffffffffff9e9e9e, float:NaN)
            r6.setTextColor(r7)
            r6 = r2
            android.widget.TextView r6 = r6.f385hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r7 = 1
            r6.setGravity(r7)
            android.widget.LinearLayout r6 = new android.widget.LinearLayout
            r12 = r6
            r6 = r12
            r7 = r12
            r8 = r2
            android.content.Context r8 = r8.context
            r7.<init>(r8)
            r12 = r6
            r6 = r12
            r7 = r12
            r3 = r7
            android.widget.LinearLayout$LayoutParams r7 = new android.widget.LinearLayout$LayoutParams
            r12 = r7
            r7 = r12
            r8 = r12
            r9 = -1
            r10 = r2
            android.content.Context r10 = r10.context
            r11 = 8
            int r10 = com.google.appinventor.components.runtime.util.KodularUnitUtil.DpToPixels((android.content.Context) r10, (int) r11)
            r8.<init>(r9, r10)
            r6.setLayoutParams(r7)
            android.widget.LinearLayout r6 = new android.widget.LinearLayout
            r12 = r6
            r6 = r12
            r7 = r12
            r8 = r2
            android.content.Context r8 = r8.context
            r7.<init>(r8)
            r12 = r6
            r6 = r12
            r7 = r12
            r4 = r7
            android.widget.LinearLayout$LayoutParams r7 = new android.widget.LinearLayout$LayoutParams
            r12 = r7
            r7 = r12
            r8 = r12
            r9 = -1
            r10 = -2
            r8.<init>(r9, r10)
            r6.setLayoutParams(r7)
            r6 = r2
            android.content.Context r6 = r6.context
            r7 = 24
            int r6 = com.google.appinventor.components.runtime.util.KodularUnitUtil.DpToPixels((android.content.Context) r6, (int) r7)
            r5 = r6
            r6 = r4
            r7 = r5
            r12 = r7
            r7 = r12
            r8 = r12
            r9 = r5
            r12 = r9
            r9 = r12
            r10 = r12
            r6.setPadding(r7, r8, r9, r10)
            r6 = r4
            r7 = r2
            android.widget.TextView r7 = r7.f385hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r6.addView(r7)
            r6 = r4
            r7 = r3
            r6.addView(r7)
            r6 = r4
            r7 = r2
            android.widget.TextView r7 = r7.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T
            r6.addView(r7)
            r6 = r4
            r7 = r2
            android.widget.TextView r7 = r7.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou
            r6.addView(r7)
            r6 = r4
            r7 = 1
            r6.setOrientation(r7)
            r6 = r4
            r7 = 17
            r6.setGravity(r7)
            r6 = r2
            android.app.Dialog r6 = r6.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            com.google.appinventor.components.runtime.FingerPrint$2 r7 = new com.google.appinventor.components.runtime.FingerPrint$2
            r12 = r7
            r7 = r12
            r8 = r12
            r9 = r2
            r8.<init>(r9)
            r6.setOnCancelListener(r7)
            r6 = r2
            android.app.Dialog r6 = r6.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            com.google.appinventor.components.runtime.FingerPrint$3 r7 = new com.google.appinventor.components.runtime.FingerPrint$3
            r12 = r7
            r7 = r12
            r8 = r12
            r9 = r2
            r8.<init>(r9)
            r6.setOnDismissListener(r7)
            r6 = r2
            android.app.Dialog r6 = r6.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            com.google.appinventor.components.runtime.FingerPrint$4 r7 = new com.google.appinventor.components.runtime.FingerPrint$4
            r12 = r7
            r7 = r12
            r8 = r12
            r9 = r2
            r8.<init>(r9)
            r6.setOnKeyListener(r7)
            r6 = r2
            android.app.Dialog r6 = r6.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r7 = r4
            r6.setContentView(r7)
            android.view.WindowManager$LayoutParams r6 = new android.view.WindowManager$LayoutParams
            r12 = r6
            r6 = r12
            r7 = r12
            r7.<init>()
            r12 = r6
            r6 = r12
            r7 = r12
            r3 = r7
            r7 = r2
            android.app.Dialog r7 = r7.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            android.view.Window r7 = r7.getWindow()
            android.view.WindowManager$LayoutParams r7 = r7.getAttributes()
            int r6 = r6.copyFrom(r7)
            r6 = r2
            android.content.Context r6 = r6.context
            android.content.res.Resources r6 = r6.getResources()
            android.util.DisplayMetrics r6 = r6.getDisplayMetrics()
            float r6 = r6.xdpi
            double r6 = (double) r6
            r8 = 4606281698874543309(0x3feccccccccccccd, double:0.9)
            double r6 = r6 * r8
            int r6 = (int) r6
            r4 = r6
            r6 = r3
            r7 = r2
            android.content.Context r7 = r7.context
            r8 = r4
            r9 = 360(0x168, float:5.04E-43)
            if (r8 <= r9) goto L_0x02a3
            r8 = 360(0x168, float:5.04E-43)
        L_0x0258:
            int r7 = com.google.appinventor.components.runtime.util.KodularUnitUtil.DpToPixels((android.content.Context) r7, (int) r8)
            r6.width = r7
            r6 = r2
            android.app.Dialog r6 = r6.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r6.show()
            r6 = r2
            android.app.Dialog r6 = r6.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            android.view.Window r6 = r6.getWindow()
            r7 = r3
            r6.setAttributes(r7)
        L_0x026f:
            return
        L_0x0270:
            r6 = r2
            android.support.v4.hardware.fingerprint.FingerprintManagerCompat r6 = r6.f383hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            boolean r6 = r6.hasEnrolledFingerprints()
            if (r6 != 0) goto L_0x0285
            java.lang.String r6 = "FingerPrint"
            java.lang.String r7 = "No fingerprint configured."
            int r6 = android.util.Log.d(r6, r7)
            goto L_0x002e
        L_0x0285:
            r6 = r2
            android.app.KeyguardManager r6 = r6.f380hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            boolean r6 = r6.isKeyguardSecure()
            if (r6 != 0) goto L_0x029a
            java.lang.String r6 = "FingerPrint"
            java.lang.String r7 = "Secure lock screen not enabled"
            int r6 = android.util.Log.d(r6, r7)
            goto L_0x002e
        L_0x029a:
            r6 = 1
            goto L_0x002f
        L_0x029d:
            r6 = move-exception
            goto L_0x00bc
        L_0x02a0:
            r7 = -1
            goto L_0x00f1
        L_0x02a3:
            r8 = r4
            goto L_0x0258
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.FingerPrint.Authenticate():void");
    }

    @SimpleFunction(description = "Cancel the current Fingerprint Scan")
    public final void CancelScan() {
        this.f384hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.cancel();
        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isShowing()) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.dismiss();
        }
    }

    @SimpleEvent(description = "Triggers when there is a Authentication Error")
    public final void OnAuthenticationError(int i, String str) {
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(i);
        Object[] objArr2 = objArr;
        objArr2[1] = str;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "OnAuthenticationError", objArr2);
    }

    @SimpleEvent(description = "Triggers when there is a Authentication Help")
    public final void OnAuthenticationHelp(int i, String str) {
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(i);
        Object[] objArr2 = objArr;
        objArr2[1] = str;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "OnAuthenticationHelp", objArr2);
    }

    @SimpleEvent(description = "Triggers when the Authentication Failed")
    public final void OnAuthenticationFailed() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "OnAuthenticationFailed", new Object[0]);
    }

    @SimpleEvent(description = "Trigger when the Authentication Succeeded")
    public final void OnAuthenticationSucceeded() {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "OnAuthenticationSucceeded", new Object[0]);
    }

    @DesignerProperty(defaultValue = "False", editorType = "boolean")
    @SimpleProperty(description = "Sets the current theme")
    public final void LightTheme(boolean z) {
        boolean z2 = z;
        this.sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp = z2;
    }

    @SimpleProperty(description = "Gets the current theme")
    public final boolean LightTheme() {
        return this.sR32qIN7Ar1u7i1api4nHQx9ll4d2UKsyYwGlFPHAR6MP73rw39BVQBnLHX3cktp;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    @SimpleProperty(description = "Whether to use a dialog")
    public final void UseDialog(boolean z) {
        boolean z2 = z;
        this.qPA23Y7cwrM2jVYdTZaCOyuoTEsak9zRoCsFocZMlDYlamZRzkT9xY09N4QNxUyM = z2;
    }

    @SimpleProperty(description = "Whether to use a dialog")
    public final boolean UseDialog() {
        return this.qPA23Y7cwrM2jVYdTZaCOyuoTEsak9zRoCsFocZMlDYlamZRzkT9xY09N4QNxUyM;
    }

    @DesignerProperty(defaultValue = "Sign in with your fingerprint", editorType = "string")
    @SimpleProperty(description = "Sets the dialog title")
    public final void DialogTitle(String str) {
        String str2 = str;
        this.EFLFxJJ7hYZabk7bFItjKUUJ7DOOvUWrCh3qTHGqJQXtWFnrbkhe9SsRqCU9oxdB = str2;
    }

    @SimpleProperty(description = "Gets the dialog title")
    public final String DialogTitle() {
        return this.EFLFxJJ7hYZabk7bFItjKUUJ7DOOvUWrCh3qTHGqJQXtWFnrbkhe9SsRqCU9oxdB;
    }

    @DesignerProperty(defaultValue = "Scan your finger", editorType = "string")
    @SimpleProperty(description = "Sets the dialog help text")
    public final void DialogHelpText(String str) {
        String str2 = str;
        this.jKqYCd0kbp4PLjuOSuX9UMjydG4JrQByekpZGS3DgrCgeBLPmjJ5QsHwhJoPxxWm = str2;
    }

    @SimpleProperty(description = "Gets the dialog help text")
    public final String DialogHelpText() {
        return this.jKqYCd0kbp4PLjuOSuX9UMjydG4JrQByekpZGS3DgrCgeBLPmjJ5QsHwhJoPxxWm;
    }

    /* access modifiers changed from: protected */
    public final void generateKey() {
        Throwable th;
        Throwable th2;
        KeyGenParameterSpec.Builder builder;
        try {
            this.f386hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = KeyStore.getInstance("AndroidKeyStore");
        } catch (Exception e) {
            int e2 = Log.e("FingerPrint", String.valueOf(e));
        }
        try {
            KeyGenerator instance = KeyGenerator.getInstance("AES", "AndroidKeyStore");
            try {
                this.f386hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.load((KeyStore.LoadStoreParameter) null);
                new KeyGenParameterSpec.Builder("makeroidApp", 3);
                String[] strArr = {"PKCS7Padding"};
                instance.init(builder.setBlockModes(new String[]{"CBC"}).setUserAuthenticationRequired(true).setEncryptionPaddings(strArr).build());
                SecretKey generateKey = instance.generateKey();
            } catch (IOException | InvalidAlgorithmParameterException | NoSuchAlgorithmException | CertificateException e3) {
                Throwable th3 = e3;
                Throwable th4 = th2;
                new RuntimeException(th3);
                throw th4;
            }
        } catch (NoSuchAlgorithmException | NoSuchProviderException e4) {
            Throwable th5 = e4;
            Throwable th6 = th;
            new RuntimeException("Failed to get KeyGenerator instance", th5);
            throw th6;
        }
    }

    public final boolean cipherInit() {
        Throwable th;
        Throwable th2;
        try {
            this.f387hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = Cipher.getInstance("AES/CBC/PKCS7Padding");
            try {
                this.f386hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.load((KeyStore.LoadStoreParameter) null);
                this.f387hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.init(1, (SecretKey) this.f386hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getKey("makeroidApp", (char[]) null));
                return true;
            } catch (KeyPermanentlyInvalidatedException e) {
                return false;
            } catch (IOException | InvalidKeyException | KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException | CertificateException e2) {
                Throwable th3 = e2;
                Throwable th4 = th2;
                new RuntimeException("Failed to init Cipher", th3);
                throw th4;
            }
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e3) {
            Throwable th5 = e3;
            Throwable th6 = th;
            new RuntimeException("Failed to get Cipher", th5);
            throw th6;
        }
    }
}

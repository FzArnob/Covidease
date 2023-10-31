package com.google.appinventor.components.runtime.util.crypt;

import android.util.Log;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

public class KodularRSA {
    private static PrivateKey hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private static PublicKey f605hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    public KodularRSA() {
    }

    public static KeyPair buildKeyPair() throws NoSuchAlgorithmException {
        StringBuilder sb;
        KeyPair buildKeyPair = buildKeyPair();
        f605hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = buildKeyPair.getPublic();
        hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = buildKeyPair.getPrivate();
        try {
            KeyPairGenerator instance = KeyPairGenerator.getInstance("RSA");
            instance.initialize(2048);
            return instance.genKeyPair();
        } catch (NoSuchAlgorithmException e) {
            new StringBuilder();
            int e2 = Log.e("MakeroidCrypt", sb.append(e.getMessage()).toString());
            return null;
        }
    }

    public static String encrypt(String str) throws Exception {
        StringBuilder sb;
        String str2;
        try {
            String str3 = str2;
            new String(encrypt(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, str));
            return str3;
        } catch (Exception e) {
            new StringBuilder();
            int e2 = Log.e("MakeroidCrypt", sb.append(e.getMessage()).toString());
            return "";
        }
    }

    public static String decrypt(String str) throws Exception {
        StringBuilder sb;
        String str2;
        try {
            String str3 = str2;
            new String(decrypt(f605hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, str.getBytes()));
            return str3;
        } catch (Exception e) {
            new StringBuilder();
            int e2 = Log.e("MakeroidCrypt", sb.append(e.getMessage()).toString());
            return "";
        }
    }

    public static byte[] encrypt(PrivateKey privateKey, String str) throws Exception {
        StringBuilder sb;
        PrivateKey privateKey2 = privateKey;
        String str2 = str;
        try {
            Cipher instance = Cipher.getInstance("RSA");
            instance.init(1, privateKey2);
            return instance.doFinal(str2.getBytes());
        } catch (Exception e) {
            new StringBuilder();
            int e2 = Log.e("MakeroidCrypt", sb.append(e.getMessage()).toString());
            return new byte[0];
        }
    }

    public static byte[] decrypt(PublicKey publicKey, byte[] bArr) throws Exception {
        StringBuilder sb;
        PublicKey publicKey2 = publicKey;
        byte[] bArr2 = bArr;
        try {
            Cipher instance = Cipher.getInstance("RSA");
            instance.init(2, publicKey2);
            return instance.doFinal(bArr2);
        } catch (Exception e) {
            new StringBuilder();
            int e2 = Log.e("MakeroidCrypt", sb.append(e.getMessage()).toString());
            return new byte[0];
        }
    }
}

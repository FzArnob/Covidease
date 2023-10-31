package com.google.appinventor.components.runtime.util.crypt;

import android.util.Log;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.xml.bind.DatatypeConverter;

public class KodularPBKDF2 {
    public static final int HASH_ALGORITHM_INDEX = 0;
    public static final int HASH_SECTIONS = 5;
    public static final int HASH_SIZE_INDEX = 2;
    public static final int ITERATION_INDEX = 1;
    public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";
    public static final int PBKDF2_INDEX = 4;
    public static final int SALT_INDEX = 3;

    public KodularPBKDF2() {
    }

    public static class InvalidHashException extends Exception {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public InvalidHashException(String str) {
            super(str);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public InvalidHashException(String str, Throwable th) {
            super(str, th);
        }
    }

    public static class CannotPerformOperationException extends Exception {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public CannotPerformOperationException(String str) {
            super(str);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public CannotPerformOperationException(String str, Throwable th) {
            super(str, th);
        }
    }

    public static String createHash(String str, int i, int i2, int i3) throws CannotPerformOperationException {
        return createHash(str.toCharArray(), i, i2, i3);
    }

    public static String createHash(char[] cArr, int i, int i2, int i3) throws CannotPerformOperationException {
        SecureRandom secureRandom;
        StringBuilder sb;
        int i4 = i;
        new SecureRandom();
        byte[] bArr = new byte[i2];
        secureRandom.nextBytes(bArr);
        byte[] hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(cArr, bArr, i4, i3);
        byte[] bArr2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        int length = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.length;
        new StringBuilder("sha1:");
        return sb.append(i4).append(":").append(length).append(":").append(DatatypeConverter.printBase64Binary(bArr)).append(":").append(DatatypeConverter.printBase64Binary(bArr2)).toString();
    }

    public static boolean verifyPassword(String str, String str2) throws CannotPerformOperationException, InvalidHashException {
        return verifyPassword(str.toCharArray(), str2);
    }

    public static boolean verifyPassword(char[] cArr, String str) throws CannotPerformOperationException, InvalidHashException {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        char[] cArr2 = cArr;
        String[] split = str.split(":");
        String[] strArr = split;
        if (split.length != 5) {
            Throwable th5 = th4;
            new InvalidHashException("Fields are missing from the password hash.");
            throw th5;
        } else if (!strArr[0].equals("sha1")) {
            Throwable th6 = th3;
            new CannotPerformOperationException("Unsupported hash type.");
            throw th6;
        } else {
            try {
                int parseInt = Integer.parseInt(strArr[1]);
                if (parseInt <= 0) {
                    Throwable th7 = th2;
                    new InvalidHashException("Invalid number of iterations. Must be >= 1.");
                    throw th7;
                }
                try {
                    byte[] parseBase64Binary = DatatypeConverter.parseBase64Binary(strArr[3]);
                    try {
                        byte[] parseBase64Binary2 = DatatypeConverter.parseBase64Binary(strArr[4]);
                        try {
                            if (Integer.parseInt(strArr[2]) != parseBase64Binary2.length) {
                                Throwable th8 = th;
                                new InvalidHashException("Hash length doesn't match stored hash length.");
                                throw th8;
                            }
                            return hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(parseBase64Binary2, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(cArr2, parseBase64Binary, parseInt, parseBase64Binary2.length));
                        } catch (NumberFormatException e) {
                            new StringBuilder();
                            int e2 = Log.e("MakeroidCrypt", sb4.append(e.getMessage()).toString());
                            return false;
                        }
                    } catch (IllegalArgumentException e3) {
                        new StringBuilder();
                        int e4 = Log.e("MakeroidCrypt", sb3.append(e3.getMessage()).toString());
                        return false;
                    }
                } catch (IllegalArgumentException e5) {
                    new StringBuilder();
                    int e6 = Log.e("MakeroidCrypt", sb2.append(e5.getMessage()).toString());
                    return false;
                }
            } catch (NumberFormatException e7) {
                new StringBuilder();
                int e8 = Log.e("MakeroidCrypt", sb.append(e7.getMessage()).toString());
                return false;
            }
        }
    }

    private static boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = bArr;
        byte[] bArr4 = bArr2;
        byte length = bArr3.length ^ bArr4.length;
        int i = 0;
        while (i < bArr3.length && i < bArr4.length) {
            length |= bArr3[i] ^ bArr4[i];
            i++;
        }
        return length == 0;
    }

    private static byte[] hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(char[] cArr, byte[] bArr, int i, int i2) throws CannotPerformOperationException {
        StringBuilder sb;
        StringBuilder sb2;
        KeySpec keySpec;
        try {
            new PBEKeySpec(cArr, bArr, i, i2 << 3);
            return SecretKeyFactory.getInstance(PBKDF2_ALGORITHM).generateSecret(keySpec).getEncoded();
        } catch (NoSuchAlgorithmException e) {
            new StringBuilder();
            int e2 = Log.e("MakeroidCrypt", sb2.append(e.getMessage()).toString());
            return new byte[0];
        } catch (InvalidKeySpecException e3) {
            new StringBuilder();
            int e4 = Log.e("MakeroidCrypt", sb.append(e3.getMessage()).toString());
            return new byte[0];
        }
    }
}

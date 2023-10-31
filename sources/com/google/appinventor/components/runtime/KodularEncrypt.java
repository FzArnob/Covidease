package com.google.appinventor.components.runtime;

import android.content.Context;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.YailList;
import com.google.appinventor.components.runtime.util.crypt.KodularAES128;
import com.google.appinventor.components.runtime.util.crypt.KodularAES256;
import com.google.appinventor.components.runtime.util.crypt.KodularBCrypt;
import com.google.appinventor.components.runtime.util.crypt.KodularBase64;
import com.google.appinventor.components.runtime.util.crypt.KodularEnigma;
import com.google.appinventor.components.runtime.util.crypt.KodularMD5;
import com.google.appinventor.components.runtime.util.crypt.KodularPBKDF2;
import com.google.appinventor.components.runtime.util.crypt.KodularSHA1;
import com.google.appinventor.components.runtime.util.crypt.KodularSHA224;
import com.google.appinventor.components.runtime.util.crypt.KodularSHA256;
import com.google.appinventor.components.runtime.util.crypt.KodularSHA384;
import com.google.appinventor.components.runtime.util.crypt.KodularSHA512;
import com.google.appinventor.components.runtime.util.crypt.KodularTripleDES;
import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;

@SimpleObject
@DesignerComponent(category = ComponentCategory.UTILITIES, description = "Component which performs several encrypt/decrypt functions", helpUrl = "https://docs.kodular.io/components/storage/cryptography/", iconName = "images/encrypt.png", nonVisible = true, version = 2)
@UsesLibraries(libraries = "javax-xml-bind.jar, apache-xerces.jar")
public class KodularEncrypt extends AndroidNonvisibleComponent {
    private String MquXNtZWbdf4047WbYlKutT53l31krf70C8DMUB6GwZPFECVbiJ9LgmrYHlSNQF0 = "";
    private int SHSZV5sTay8ykRHafXU624sH0bmI6VeYHAe3FtLV8LV4djzabBBIyQGaIvRsAwmk = 18;
    private int SzducuiMqWJn2qdHTwu7wT0uVCkk0nPPJy3Na6C8gzyeOLBidRCWmYhIkQdo5gq3 = 10;
    private int XkTExsgDjzL7UIaxQorrmSTr7jZbDxCmXSVfTpg7zAbwFxeOpcI2x1hAyx12QFiS = 64000;
    private String YvCSL3NGR2QTRYJR2TwKp0VDudhjPNaOEVNDS5yB8p5C86x6HrXaW1BAbVOWs3Le = "";
    private Context context;
    private int eQeQhiSmlElGLbwx31fXfv53XsxBlAuCtzOUdhRtXefyKZkKyhRMzOCKSg7WR08 = 24;
    private String nvMxrgxLbjkSMxKVTHnXYUSqg15Nn9sVGKlCuWXpKupRQzyTNqJO1nkpaVIaCsw = "";

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public KodularEncrypt(com.google.appinventor.components.runtime.ComponentContainer r5) {
        /*
            r4 = this;
            r0 = r4
            r1 = r5
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            r3 = 64000(0xfa00, float:8.9683E-41)
            r2.XkTExsgDjzL7UIaxQorrmSTr7jZbDxCmXSVfTpg7zAbwFxeOpcI2x1hAyx12QFiS = r3
            r2 = r0
            r3 = 24
            r2.eQeQhiSmlElGLbwx31fXfv53XsxBlAuCtzOUdhRtXefyKZkKyhRMzOCKSg7WR08 = r3
            r2 = r0
            r3 = 18
            r2.SHSZV5sTay8ykRHafXU624sH0bmI6VeYHAe3FtLV8LV4djzabBBIyQGaIvRsAwmk = r3
            r2 = r0
            java.lang.String r3 = ""
            r2.YvCSL3NGR2QTRYJR2TwKp0VDudhjPNaOEVNDS5yB8p5C86x6HrXaW1BAbVOWs3Le = r3
            r2 = r0
            r3 = 10
            r2.SzducuiMqWJn2qdHTwu7wT0uVCkk0nPPJy3Na6C8gzyeOLBidRCWmYhIkQdo5gq3 = r3
            r2 = r0
            java.lang.String r3 = ""
            r2.nvMxrgxLbjkSMxKVTHnXYUSqg15Nn9sVGKlCuWXpKupRQzyTNqJO1nkpaVIaCsw = r3
            r2 = r0
            java.lang.String r3 = ""
            r2.MquXNtZWbdf4047WbYlKutT53l31krf70C8DMUB6GwZPFECVbiJ9LgmrYHlSNQF0 = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            java.lang.String r2 = "Kodular Encrypt"
            java.lang.String r3 = "Kodular Encrypt Component Created"
            int r2 = android.util.Log.d(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.KodularEncrypt.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @DesignerProperty(defaultValue = "64000", editorType = "integer")
    @SimpleProperty(description = "Set the PBKDF2 number of Iterations")
    public void PBKDF2Iterations(int i) {
        int i2 = i;
        this.XkTExsgDjzL7UIaxQorrmSTr7jZbDxCmXSVfTpg7zAbwFxeOpcI2x1hAyx12QFiS = i2;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the PBKDF2 number of Iterations")
    public int PBKDF2Iterations() {
        return this.XkTExsgDjzL7UIaxQorrmSTr7jZbDxCmXSVfTpg7zAbwFxeOpcI2x1hAyx12QFiS;
    }

    @DesignerProperty(defaultValue = "24", editorType = "integer")
    @SimpleProperty(description = "Set the PBKDF2 Salt Byte Size")
    public void PBKDF2SaltByteSize(int i) {
        int i2 = i;
        this.eQeQhiSmlElGLbwx31fXfv53XsxBlAuCtzOUdhRtXefyKZkKyhRMzOCKSg7WR08 = i2;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the PBKDF2 Salt Byte Size")
    public int PBKDF2SaltByteSize() {
        return this.eQeQhiSmlElGLbwx31fXfv53XsxBlAuCtzOUdhRtXefyKZkKyhRMzOCKSg7WR08;
    }

    @DesignerProperty(defaultValue = "18", editorType = "integer")
    @SimpleProperty(description = "Set the PBKDF2 Hash Byte Size")
    public void PBKDF2HashByteSize(int i) {
        int i2 = i;
        this.SHSZV5sTay8ykRHafXU624sH0bmI6VeYHAe3FtLV8LV4djzabBBIyQGaIvRsAwmk = i2;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the PBKDF2 Hash Byte Size")
    public int PBKDF2HashByteSize() {
        return this.SHSZV5sTay8ykRHafXU624sH0bmI6VeYHAe3FtLV8LV4djzabBBIyQGaIvRsAwmk;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty(description = "Set the TripleDES Key")
    public void TripleDESKey(String str) {
        String str2 = str;
        this.YvCSL3NGR2QTRYJR2TwKp0VDudhjPNaOEVNDS5yB8p5C86x6HrXaW1BAbVOWs3Le = str2;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the TripleDES Key")
    public String TripleDESKey() {
        return this.YvCSL3NGR2QTRYJR2TwKp0VDudhjPNaOEVNDS5yB8p5C86x6HrXaW1BAbVOWs3Le;
    }

    @DesignerProperty(defaultValue = "10", editorType = "integer")
    @SimpleProperty(description = "Set the BCrypt Salt Size")
    public void BCryptSaltSize(int i) {
        int i2 = i;
        this.SzducuiMqWJn2qdHTwu7wT0uVCkk0nPPJy3Na6C8gzyeOLBidRCWmYhIkQdo5gq3 = i2;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the BCrypt Salt Size")
    public int BCryptSaltSize() {
        return this.SzducuiMqWJn2qdHTwu7wT0uVCkk0nPPJy3Na6C8gzyeOLBidRCWmYhIkQdo5gq3;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty(description = "Set the AES-128 Key")
    public void AES128Key(String str) {
        String str2 = str;
        this.nvMxrgxLbjkSMxKVTHnXYUSqg15Nn9sVGKlCuWXpKupRQzyTNqJO1nkpaVIaCsw = str2;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the AES-128 Key")
    public String AES128Key() {
        return this.nvMxrgxLbjkSMxKVTHnXYUSqg15Nn9sVGKlCuWXpKupRQzyTNqJO1nkpaVIaCsw;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    @SimpleProperty(description = "Set the AES-256 Key")
    public void AES256Key(String str) {
        String str2 = str;
        this.MquXNtZWbdf4047WbYlKutT53l31krf70C8DMUB6GwZPFECVbiJ9LgmrYHlSNQF0 = str2;
    }

    @SimpleProperty(category = PropertyCategory.APPEARANCE, description = "Returns the AES-256 Key")
    public String AES256Key() {
        return this.MquXNtZWbdf4047WbYlKutT53l31krf70C8DMUB6GwZPFECVbiJ9LgmrYHlSNQF0;
    }

    @SimpleFunction(description = "Verifies if the input password is the same one as the correct hashed password using PBKDF2 algorithm")
    public boolean PBKDF2VerifyPassword(String str, String str2) {
        try {
            return KodularPBKDF2.verifyPassword(str, str2);
        } catch (Exception e) {
            int e2 = Log.e("Kodular Encrypt", String.valueOf(e));
            return false;
        }
    }

    @SimpleFunction(description = "Generates a hash using PBKDF2")
    public String PBKDF2CreateHash(String str) {
        try {
            return KodularPBKDF2.createHash(str, this.XkTExsgDjzL7UIaxQorrmSTr7jZbDxCmXSVfTpg7zAbwFxeOpcI2x1hAyx12QFiS, this.eQeQhiSmlElGLbwx31fXfv53XsxBlAuCtzOUdhRtXefyKZkKyhRMzOCKSg7WR08, this.SHSZV5sTay8ykRHafXU624sH0bmI6VeYHAe3FtLV8LV4djzabBBIyQGaIvRsAwmk);
        } catch (Exception e) {
            int e2 = Log.e("Kodular Encrypt", String.valueOf(e));
            return "";
        }
    }

    @SimpleFunction(description = "Generates a MD5 hash")
    public String MD5CreateHash(String str) {
        try {
            return KodularMD5.createHash(str);
        } catch (Exception e) {
            int e2 = Log.e("Kodular Encrypt", String.valueOf(e));
            return "";
        }
    }

    @SimpleFunction(description = "Decodes the given hash using Base64")
    public String Base64Decode(String str) {
        try {
            return KodularBase64.decode(str);
        } catch (Exception e) {
            int e2 = Log.e("Kodular Encrypt", String.valueOf(e));
            return "";
        }
    }

    @SimpleFunction(description = "Encodes the given string using Base64")
    public String Base64Encode(String str) {
        try {
            return KodularBase64.encode(str);
        } catch (Exception e) {
            int e2 = Log.e("Kodular Encrypt", String.valueOf(e));
            return "";
        }
    }

    @SimpleFunction(description = "Decodes the given hash using the given key through TripleDES")
    public String TripleDESDecode(String str) {
        try {
            return KodularTripleDES.decode(str, this.YvCSL3NGR2QTRYJR2TwKp0VDudhjPNaOEVNDS5yB8p5C86x6HrXaW1BAbVOWs3Le);
        } catch (Exception e) {
            int e2 = Log.e("Kodular Encrypt", String.valueOf(e));
            return "";
        }
    }

    @SimpleFunction(description = "Encodes the given string using the given key through TripleDES")
    public String TripleDESEncode(String str) {
        try {
            return KodularTripleDES.encode(str, this.YvCSL3NGR2QTRYJR2TwKp0VDudhjPNaOEVNDS5yB8p5C86x6HrXaW1BAbVOWs3Le);
        } catch (Exception e) {
            int e2 = Log.e("Kodular Encrypt", String.valueOf(e));
            return "";
        }
    }

    @SimpleFunction(description = "Verifies if the input password is the same one as the correct hashed password using BCrypt algorithm")
    public boolean BCryptVerifyPassword(String str, String str2) {
        try {
            return KodularBCrypt.checkPassword(str, str2);
        } catch (Exception e) {
            int e2 = Log.e("Kodular Encrypt", String.valueOf(e));
            return false;
        }
    }

    @SimpleFunction(description = "Generates a hash using BCrypt")
    public String BCryptCreateHash(String str, String str2) {
        try {
            return KodularBCrypt.hashPassword(str, str2);
        } catch (Exception e) {
            int e2 = Log.e("Kodular Encrypt", String.valueOf(e));
            return "";
        }
    }

    @SimpleFunction(description = "Generates a salt usable for hashing with BCrypt")
    public String BCryptGenerateSalt() {
        try {
            return KodularBCrypt.gensalt(this.SzducuiMqWJn2qdHTwu7wT0uVCkk0nPPJy3Na6C8gzyeOLBidRCWmYhIkQdo5gq3);
        } catch (Exception e) {
            int e2 = Log.e("Kodular Encrypt", String.valueOf(e));
            return "";
        }
    }

    @SimpleFunction(description = "Generates a hashed SHA-1 string")
    public String SHA1(String str) {
        try {
            return KodularSHA1.sha1(str);
        } catch (Exception e) {
            int e2 = Log.e("Kodular Encrypt", String.valueOf(e));
            return "";
        }
    }

    @SimpleFunction(description = "Generates a hashed SHA-224 string")
    public String SHA224(String str) {
        try {
            return KodularSHA224.sha224(str);
        } catch (Exception e) {
            int e2 = Log.e("Kodular Encrypt", String.valueOf(e));
            return "";
        }
    }

    @SimpleFunction(description = "Generates a hashed SHA-256 string")
    public String SHA256(String str) {
        try {
            return KodularSHA256.sha256(str);
        } catch (Exception e) {
            int e2 = Log.e("Kodular Encrypt", String.valueOf(e));
            return "";
        }
    }

    @SimpleFunction(description = "Generates a hashed SHA-384 string")
    public String SHA384(String str) {
        try {
            return KodularSHA384.sha384(str);
        } catch (Exception e) {
            int e2 = Log.e("Kodular Encrypt", String.valueOf(e));
            return "";
        }
    }

    @SimpleFunction(description = "Generates a hashed SHA-512 string")
    public String SHA512(String str) {
        try {
            return KodularSHA512.sha512(str);
        } catch (Exception e) {
            int e2 = Log.e("Kodular Encrypt", String.valueOf(e));
            return "";
        }
    }

    @SimpleFunction(description = "Decodes the given hash using the given key through AES-128. If any exception occurs, returns empty string.")
    public String AES128Decode(String str) {
        return KodularAES128.decode(str, this.nvMxrgxLbjkSMxKVTHnXYUSqg15Nn9sVGKlCuWXpKupRQzyTNqJO1nkpaVIaCsw);
    }

    @SimpleFunction(description = "Encodes the given string using the given key through AES-128. If any exception occurs, returns empty string.")
    public String AES128Encode(String str) {
        return KodularAES128.encode(str, this.nvMxrgxLbjkSMxKVTHnXYUSqg15Nn9sVGKlCuWXpKupRQzyTNqJO1nkpaVIaCsw);
    }

    @SimpleFunction(description = "Generates a secure random AES 128 key")
    public String AES128GenKey() {
        return KodularAES128.generateKey();
    }

    @SimpleFunction(description = "Generates a secure random AES 256 key")
    public String AES256GenKey() {
        return KodularAES128.generateKey();
    }

    @SimpleFunction(description = "Decodes the given hash using the given key through AES-256. If there are any exceptions, returns empty string")
    public String AES256Decode(String str) {
        return KodularAES256.decode(str, this.MquXNtZWbdf4047WbYlKutT53l31krf70C8DMUB6GwZPFECVbiJ9LgmrYHlSNQF0, this.context);
    }

    @SimpleFunction(description = "Encodes the given string using the given key through AES-256. If there are any exceptions, returns empty string")
    public String AES256Encode(String str) {
        return KodularAES256.encode(str, this.MquXNtZWbdf4047WbYlKutT53l31krf70C8DMUB6GwZPFECVbiJ9LgmrYHlSNQF0, this.context);
    }

    @SimpleFunction(description = "Encrypts or decrypts the given string simulating an Enigma machine. Rotors can go from 1 to 8, but they cannot be repeated. Reflector can be B, C or 0 if none. Plugboard is a list with sub-list of two items containing a character each one, which replace the first character with the second one.")
    public String Enigma(String str, int i, int i2, int i3, String str2, YailList yailList) {
        StringBuilder sb;
        String str3 = str;
        int i4 = i;
        int i5 = i2;
        int i6 = i3;
        String str4 = str2;
        YailList yailList2 = yailList;
        try {
            new StringBuilder();
            StringBuilder sb2 = sb;
            String str5 = "";
            for (int i7 = 0; i7 < yailList2.size(); i7++) {
                Object object = yailList2.getObject(i7);
                Object obj = object;
                if (!(object instanceof YailList)) {
                    return "Sub-item not a list";
                }
                YailList yailList3 = (YailList) obj;
                YailList yailList4 = yailList3;
                if (yailList3.size() != 2) {
                    return "Sub-item does not have two items";
                }
                StringBuilder append = sb2.append(str5).append(yailList4.getObject(0).toString()).append(yailList4.getObject(1).toString());
                str5 = MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
            }
            String sb3 = sb2.toString();
            if (i4 > 8 || i5 > 8 || i6 > 8 || i4 <= 0 || i5 <= 0 || i6 <= 0) {
                return "Rotors has to be between 1 and 8";
            }
            if (str4 != "B" && str4 != "C" && str4 != "0") {
                return "Reflector has to be B, C or 0 8 (none)";
            }
            if (!KodularEnigma.pbParser(sb3)) {
                return "Duplicated entries in plugboard";
            }
            return KodularEnigma.encrypt(str3, i4, i5, i6, str4, sb3);
        } catch (Exception e) {
            int e2 = Log.e("Kodular Encrypt", String.valueOf(e));
            return "";
        }
    }
}

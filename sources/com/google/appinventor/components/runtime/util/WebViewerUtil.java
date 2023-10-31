package com.google.appinventor.components.runtime.util;

import android.content.Context;
import android.telephony.TelephonyManager;

public class WebViewerUtil {
    private static String[] ANz72NxTeEmYo9CF87MXRUuH7WvE4u0mpZwxffTnyiMdygEQRKvmdTCHaXqAvud;
    private static String CURRENT_COUNTRY = "";
    private static String[] ENGLISH;
    private static String[] GERMAN;
    private static String[] KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2;
    private static String[] PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC;
    private static String[] moH2L3ThNq0Qhevz3oD0wpnUoXefdMi3gJ5JHfEbjrtgsvRvpM1e34BZQYI4s5i0;
    private static String[] oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS;
    private static String[] sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb;
    private static String[] tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE;
    private static String[] wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0;

    private WebViewerUtil() {
    }

    static {
        String[] strArr = new String[5];
        strArr[0] = "Berechtigungsanfrage";
        String[] strArr2 = strArr;
        strArr2[1] = "Diese Anwendung";
        String[] strArr3 = strArr2;
        strArr3[2] = " möchte auf Ihren Standort zugreifen.";
        String[] strArr4 = strArr3;
        strArr4[3] = "Zulassen";
        String[] strArr5 = strArr4;
        strArr5[4] = "Ablehnen";
        GERMAN = strArr5;
        String[] strArr6 = new String[5];
        strArr6[0] = "Permission Request";
        String[] strArr7 = strArr6;
        strArr7[1] = "This Application";
        String[] strArr8 = strArr7;
        strArr8[2] = " would like to access your location.";
        String[] strArr9 = strArr8;
        strArr9[3] = "Allow";
        String[] strArr10 = strArr9;
        strArr10[4] = "Denied";
        ENGLISH = strArr10;
        String[] strArr11 = new String[5];
        strArr11[0] = "Permintaan Izin";
        String[] strArr12 = strArr11;
        strArr12[1] = "Aplikasi ini";
        String[] strArr13 = strArr12;
        strArr13[2] = " ingin mengakses lokasi Anda.";
        String[] strArr14 = strArr13;
        strArr14[3] = "Mengizinkan";
        String[] strArr15 = strArr14;
        strArr15[4] = "Ditolak";
        oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS = strArr15;
        String[] strArr16 = new String[5];
        strArr16[0] = "İzin İsteği";
        String[] strArr17 = strArr16;
        strArr17[1] = "Bu uygulama";
        String[] strArr18 = strArr17;
        strArr18[2] = " bulunduğunuz konuma erişmek istiyor.";
        String[] strArr19 = strArr18;
        strArr19[3] = "İzin Ver";
        String[] strArr20 = strArr19;
        strArr20[4] = "Reddet";
        PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC = strArr20;
        String[] strArr21 = new String[5];
        strArr21[0] = "Richiesta autorizzazione";
        String[] strArr22 = strArr21;
        strArr22[1] = "Questa applicazione";
        String[] strArr23 = strArr22;
        strArr23[2] = " vorrebbe accedere alla tua posizione.";
        String[] strArr24 = strArr23;
        strArr24[3] = "Permetti";
        String[] strArr25 = strArr24;
        strArr25[4] = "Nega";
        moH2L3ThNq0Qhevz3oD0wpnUoXefdMi3gJ5JHfEbjrtgsvRvpM1e34BZQYI4s5i0 = strArr25;
        String[] strArr26 = new String[5];
        strArr26[0] = "Requer Permissão";
        String[] strArr27 = strArr26;
        strArr27[1] = "Esta Aplicação";
        String[] strArr28 = strArr27;
        strArr28[2] = " gostaria de acessar sua localização.";
        String[] strArr29 = strArr28;
        strArr29[3] = "Permitir";
        String[] strArr30 = strArr29;
        strArr30[4] = "Negado";
        tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE = strArr30;
        String[] strArr31 = new String[5];
        strArr31[0] = "Toestemming geven";
        String[] strArr32 = strArr31;
        strArr32[1] = "Deze applicatie";
        String[] strArr33 = strArr32;
        strArr33[2] = " wil uw locatie weten.";
        String[] strArr34 = strArr33;
        strArr34[3] = "Toestaan";
        String[] strArr35 = strArr34;
        strArr35[4] = "Weigeren";
        ANz72NxTeEmYo9CF87MXRUuH7WvE4u0mpZwxffTnyiMdygEQRKvmdTCHaXqAvud = strArr35;
        String[] strArr36 = new String[5];
        strArr36[0] = "अनुमति का अनुरोध";
        String[] strArr37 = strArr36;
        strArr37[1] = "यह अॅपलिकेशन";
        String[] strArr38 = strArr37;
        strArr38[2] = " आपका स्थान जाँचना चाहेंगी।";
        String[] strArr39 = strArr38;
        strArr39[3] = "अनुमति दें";
        String[] strArr40 = strArr39;
        strArr40[4] = "अस्वीकृत करें";
        wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0 = strArr40;
        String[] strArr41 = new String[5];
        strArr41[0] = "Solicitar Permiso";
        String[] strArr42 = strArr41;
        strArr42[1] = "Esta App";
        String[] strArr43 = strArr42;
        strArr43[2] = " solicita acceso a la ubicación.";
        String[] strArr44 = strArr43;
        strArr44[3] = "Permitir";
        String[] strArr45 = strArr44;
        strArr45[4] = "Rechazar";
        KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2 = strArr45;
        String[] strArr46 = new String[5];
        strArr46[0] = "Engedélykérés";
        String[] strArr47 = strArr46;
        strArr47[1] = "Ez az alkalmazás";
        String[] strArr48 = strArr47;
        strArr48[2] = " hozzáférést kér a tartózkodási helyéhez.";
        String[] strArr49 = strArr48;
        strArr49[3] = "Engedélyez";
        String[] strArr50 = strArr49;
        strArr50[4] = "Megtagad";
        sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb = strArr50;
    }

    public static void initialize(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        TelephonyManager telephonyManager2 = telephonyManager;
        if (telephonyManager.getNetworkCountryIso() != null) {
            CURRENT_COUNTRY = telephonyManager2.getNetworkCountryIso();
        } else {
            CURRENT_COUNTRY = "default";
        }
    }

    public static String getPermissionTitleString() {
        return getResult(0);
    }

    public static String getPermissionApplicationString() {
        return getResult(1);
    }

    public static String getPermissionMessageString() {
        return getResult(2);
    }

    public static String getPermissionAllowString() {
        return getResult(3);
    }

    public static String getPermissionDeniedString() {
        return getResult(4);
    }

    private static String getResult(int i) {
        int i2 = i;
        String lowerCase = CURRENT_COUNTRY.toLowerCase();
        boolean z = true;
        switch (lowerCase.hashCode()) {
            case 3123:
                if (lowerCase.equals("at")) {
                    z = true;
                    break;
                }
                break;
            case 3173:
                if (lowerCase.equals("ch")) {
                    z = true;
                    break;
                }
                break;
            case ErrorMessages.ERROR_INDEX_MISSING_IN_LIST:
                if (lowerCase.equals("de")) {
                    z = true;
                    break;
                }
                break;
            case 3246:
                if (lowerCase.equals("es")) {
                    z = true;
                    break;
                }
                break;
            case 3291:
                if (lowerCase.equals("gb")) {
                    z = true;
                    break;
                }
                break;
            case 3341:
                if (lowerCase.equals("hu")) {
                    z = true;
                    break;
                }
                break;
            case 3355:
                if (lowerCase.equals("id")) {
                    z = true;
                    break;
                }
                break;
            case 3356:
                if (lowerCase.equals("ie")) {
                    z = true;
                    break;
                }
                break;
            case 3365:
                if (lowerCase.equals("in")) {
                    z = true;
                    break;
                }
                break;
            case 3371:
                if (lowerCase.equals("it")) {
                    z = true;
                    break;
                }
                break;
            case 3518:
                if (lowerCase.equals("nl")) {
                    z = true;
                    break;
                }
                break;
            case 3588:
                if (lowerCase.equals("pt")) {
                    z = true;
                    break;
                }
                break;
            case 3710:
                if (lowerCase.equals("tr")) {
                    z = true;
                    break;
                }
                break;
            case 3742:
                if (lowerCase.equals("us")) {
                    z = true;
                    break;
                }
                break;
            case 1544803905:
                if (lowerCase.equals("default")) {
                    z = false;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
                return ENGLISH[i2];
            case true:
            case true:
            case true:
                return GERMAN[i2];
            case true:
            case true:
            case true:
                return ENGLISH[i2];
            case true:
                return oDikMCstR6tlR2dTNi9SmHhjXnOW8gvVc7RVCpamOJDxjRqCmBMqmRVoMYhtpjS[i2];
            case true:
                return wGMjbGuJ9Yk6s2LaEm8v1pEJlXt36TYBWZSsia0LUgb1yMdHNGB7uRz3VqnF79D0[i2];
            case true:
                return PpoaLB2hgzDTOuqu9FkRmbGOAi4DOSz44cSb2WOQzHfJN0AfA0f9dWyZXVLXkHHC[i2];
            case true:
                return moH2L3ThNq0Qhevz3oD0wpnUoXefdMi3gJ5JHfEbjrtgsvRvpM1e34BZQYI4s5i0[i2];
            case true:
                return tee1T53LBirhPaiZzuBv7do2U2eC3n16AtczACNW2pKYEGgDhkkMv1hGUupyoKZE[i2];
            case true:
                return ANz72NxTeEmYo9CF87MXRUuH7WvE4u0mpZwxffTnyiMdygEQRKvmdTCHaXqAvud[i2];
            case true:
                return KXDzEMeLg0aMKCNRnRJuQGoMaVrKUgtBW3gGmn2kxU5q0F1ZNh5DKQo95IN9JPm2[i2];
            case true:
                return sLSXXiYjDERyx7CKvO5GstTCcI8HiXXLiPYrugcXt2517h4ADL52v0RLLmUd9xMb[i2];
            default:
                return ENGLISH[i2];
        }
    }
}

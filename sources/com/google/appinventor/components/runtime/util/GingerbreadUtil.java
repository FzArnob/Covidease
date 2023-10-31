package com.google.appinventor.components.runtime.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.os.Parcelable;
import android.util.Log;
import com.google.appinventor.components.runtime.NearField;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookieStore;
import java.nio.charset.Charset;
import java.util.Locale;
import org.shaded.apache.http.protocol.HTTP;

public class GingerbreadUtil {
    private GingerbreadUtil() {
    }

    public static CookieHandler newCookieManager() {
        CookieHandler cookieHandler;
        CookieHandler cookieHandler2 = cookieHandler;
        new CookieManager();
        return cookieHandler2;
    }

    public static boolean clearCookies(CookieHandler cookieHandler) {
        CookieHandler cookieHandler2 = cookieHandler;
        if (cookieHandler2 instanceof CookieManager) {
            CookieStore cookieStore = ((CookieManager) cookieHandler2).getCookieStore();
            CookieStore cookieStore2 = cookieStore;
            if (cookieStore != null) {
                boolean removeAll = cookieStore2.removeAll();
                return true;
            }
        }
        return false;
    }

    public static NfcAdapter newNfcAdapter(Context context) {
        return NfcAdapter.getDefaultAdapter(context);
    }

    public static void enableNFCWriteMode(Activity activity, NfcAdapter nfcAdapter, String str) {
        NdefMessage ndefMessage;
        NdefMessage ndefMessage2 = ndefMessage;
        new NdefMessage(new NdefRecord[]{createTextRecord(str, true)});
        nfcAdapter.enableForegroundNdefPush(activity, ndefMessage2);
    }

    public static void disableNFCAdapter(Activity activity, NfcAdapter nfcAdapter) {
        nfcAdapter.disableForegroundNdefPush(activity);
    }

    public static NdefRecord createTextRecord(String str, boolean z) {
        NdefRecord ndefRecord;
        String str2 = str;
        boolean z2 = z;
        byte[] bytes = Locale.getDefault().getLanguage().getBytes(Charset.forName("US-ASCII"));
        byte[] bytes2 = str2.getBytes(z2 ? Charset.forName("UTF-8") : Charset.forName(HTTP.UTF_16));
        char length = (char) ((z2 ? 0 : 128) + bytes.length);
        byte[] bArr = new byte[(1 + bytes.length + bytes2.length)];
        byte[] bArr2 = bArr;
        bArr[0] = (byte) length;
        System.arraycopy(bytes, 0, bArr2, 1, bytes.length);
        System.arraycopy(bytes2, 0, bArr2, 1 + bytes.length, bytes2.length);
        new NdefRecord(1, NdefRecord.RTD_TEXT, new byte[0], bArr2);
        return ndefRecord;
    }

    public static void resolveNFCIntent(Intent intent, NearField nearField) {
        NdefMessage ndefMessage;
        NdefRecord ndefRecord;
        NdefMessage ndefMessage2;
        NdefMessage[] ndefMessageArr;
        String str;
        Intent intent2 = intent;
        NearField nearField2 = nearField;
        if (!"android.nfc.action.NDEF_DISCOVERED".equals(intent2.getAction())) {
            int e = Log.e("nearfield", "Unknown intent ".concat(String.valueOf(intent2)));
        } else if (nearField2.ReadMode()) {
            Parcelable[] parcelableArrayExtra = intent2.getParcelableArrayExtra("android.nfc.extra.NDEF_MESSAGES");
            Parcelable[] parcelableArr = parcelableArrayExtra;
            if (parcelableArrayExtra != null) {
                ndefMessageArr = new NdefMessage[parcelableArr.length];
                for (int i = 0; i < parcelableArr.length; i++) {
                    ndefMessageArr[i] = (NdefMessage) parcelableArr[i];
                }
            } else {
                byte[] bArr = new byte[0];
                NdefRecord ndefRecord2 = ndefRecord;
                byte[] bArr2 = bArr;
                new NdefRecord(5, bArr2, bArr2, bArr);
                NdefRecord ndefRecord3 = ndefRecord2;
                new NdefMessage(new NdefRecord[]{ndefRecord3});
                ndefMessageArr = new NdefMessage[]{ndefMessage2};
            }
            new String(ndefMessageArr[0].getRecords()[0].getPayload());
            nearField2.TagRead(NearField.toHexString(((Tag) intent2.getParcelableExtra("android.nfc.extra.TAG")).getId()), str.substring(3));
        } else {
            Tag tag = (Tag) intent2.getParcelableExtra("android.nfc.extra.TAG");
            NdefMessage ndefMessage3 = null;
            if (nearField2.WriteType() == 1) {
                new NdefMessage(new NdefRecord[]{createTextRecord(nearField2.TextToWrite(), true)});
                ndefMessage3 = ndefMessage;
            }
            if (writeNFCTag(ndefMessage3, tag)) {
                nearField2.TagWritten();
            }
        }
    }

    public static boolean writeNFCTag(NdefMessage ndefMessage, Tag tag) {
        NdefMessage ndefMessage2 = ndefMessage;
        Tag tag2 = tag;
        int length = ndefMessage2.toByteArray().length;
        try {
            Ndef ndef = Ndef.get(tag2);
            Ndef ndef2 = ndef;
            if (ndef != null) {
                ndef2.connect();
                if (!ndef2.isWritable()) {
                    return false;
                }
                if (ndef2.getMaxSize() < length) {
                    return false;
                }
                ndef2.writeNdefMessage(ndefMessage2);
                return true;
            }
            NdefFormatable ndefFormatable = NdefFormatable.get(tag2);
            NdefFormatable ndefFormatable2 = ndefFormatable;
            if (ndefFormatable == null) {
                return false;
            }
            try {
                ndefFormatable2.connect();
                ndefFormatable2.format(ndefMessage2);
                return true;
            } catch (IOException e) {
                return false;
            }
        } catch (Exception e2) {
            return false;
        }
    }
}

package com.google.appinventor.components.runtime.util;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import java.io.InputStream;

public class HoneycombMR1Util {
    private HoneycombMR1Util() {
    }

    public static Uri getContentUri() {
        return ContactsContract.Contacts.CONTENT_URI;
    }

    public static Uri getPhoneContentUri() {
        return ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
    }

    public static Uri getDataContentUri() {
        return ContactsContract.Data.CONTENT_URI;
    }

    public static String[] getContactProjection() {
        String[] strArr = new String[4];
        strArr[0] = "_id";
        String[] strArr2 = strArr;
        strArr2[1] = "display_name";
        String[] strArr3 = strArr2;
        strArr3[2] = "photo_thumb_uri";
        String[] strArr4 = strArr3;
        String[] strArr5 = strArr4;
        strArr4[3] = "photo_uri";
        return strArr5;
    }

    public static String[] getNameProjection() {
        String[] strArr = new String[4];
        strArr[0] = "contact_id";
        String[] strArr2 = strArr;
        strArr2[1] = "display_name";
        String[] strArr3 = strArr2;
        strArr3[2] = "photo_thumb_uri";
        String[] strArr4 = strArr3;
        String[] strArr5 = strArr4;
        strArr4[3] = "data1";
        return strArr5;
    }

    public static String[] getDataProjection() {
        String[] strArr = new String[5];
        strArr[0] = "mimetype";
        String[] strArr2 = strArr;
        strArr2[1] = "data1";
        String[] strArr3 = strArr2;
        strArr3[2] = "data2";
        String[] strArr4 = strArr3;
        strArr4[3] = "data1";
        String[] strArr5 = strArr4;
        String[] strArr6 = strArr5;
        strArr5[4] = "data2";
        return strArr6;
    }

    public static String[] getEmailAdapterProjection() {
        String[] strArr = new String[4];
        strArr[0] = "_id";
        String[] strArr2 = strArr;
        strArr2[1] = "display_name";
        String[] strArr3 = strArr2;
        strArr3[2] = "data1";
        String[] strArr4 = strArr3;
        String[] strArr5 = strArr4;
        strArr4[3] = "mimetype";
        return strArr5;
    }

    public static int getIdIndex(Cursor cursor) {
        return cursor.getColumnIndex("_id");
    }

    public static int getContactIdIndex(Cursor cursor) {
        return cursor.getColumnIndex("contact_id");
    }

    public static int getNameIndex(Cursor cursor) {
        return cursor.getColumnIndex("display_name");
    }

    public static int getThumbnailIndex(Cursor cursor) {
        return cursor.getColumnIndex("photo_thumb_uri");
    }

    public static int getPhotoIndex(Cursor cursor) {
        return cursor.getColumnIndex("photo_uri");
    }

    public static int getPhoneIndex(Cursor cursor) {
        return cursor.getColumnIndex("data1");
    }

    public static int getEmailIndex(Cursor cursor) {
        return cursor.getColumnIndex("data1");
    }

    public static int getMimeIndex(Cursor cursor) {
        return cursor.getColumnIndex("mimetype");
    }

    public static String getPhoneType() {
        return "vnd.android.cursor.item/phone_v2";
    }

    public static String getEmailType() {
        return "vnd.android.cursor.item/email_v2";
    }

    public static String getDisplayName() {
        return "display_name";
    }

    public static String getEmailAddress() {
        return "data1";
    }

    public static String getDataMimeType() {
        return "mimetype";
    }

    public static Cursor getDataCursor(String str, Activity activity, String[] strArr) {
        String[] strArr2 = new String[3];
        strArr2[0] = str;
        String[] strArr3 = strArr2;
        strArr3[1] = "vnd.android.cursor.item/phone_v2";
        String[] strArr4 = strArr3;
        strArr4[2] = "vnd.android.cursor.item/email_v2";
        return activity.getContentResolver().query(ContactsContract.Data.CONTENT_URI, strArr, "contact_id=? AND (mimetype=? OR mimetype=?)", strArr4, (String) null);
    }

    public static InputStream openContactPhotoInputStreamHelper(ContentResolver contentResolver, Uri uri) {
        return ContactsContract.Contacts.openContactPhotoInputStream(contentResolver, uri);
    }

    public static String getTimesContacted() {
        return "times_contacted";
    }
}

package com.google.appinventor.components.runtime;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.os.Build;
import android.provider.Contacts;
import android.text.TextUtils;
import android.text.util.Rfc822Token;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;
import com.google.appinventor.components.runtime.util.HoneycombMR1Util;

public class EmailAddressAdapter extends ResourceCursorAdapter {
    private static String G9FdWvm6zShqpzDC2UOqX6MiQExLolZTefPBjzVvfkE9Kp2rmQld5rkb2wriBYfL = null;
    public static final int PRE_HONEYCOMB_DATA_INDEX = 2;
    public static final int PRE_HONEYCOMB_NAME_INDEX = 1;
    private static final String[] hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO;
    private static final String[] vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R = HoneycombMR1Util.getEmailAdapterProjection();
    private Context context;
    private ContentResolver hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    static {
        String[] strArr = new String[3];
        strArr[0] = "_id";
        String[] strArr2 = strArr;
        strArr2[1] = "name";
        String[] strArr3 = strArr2;
        strArr3[2] = "data";
        hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = strArr3;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public EmailAddressAdapter(android.content.Context r8) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r0
            r3 = r1
            r4 = 17367050(0x109000a, float:2.5162954E-38)
            r5 = 0
            r2.<init>(r3, r4, r5)
            r2 = r0
            r3 = r1
            android.content.ContentResolver r3 = r3.getContentResolver()
            r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r0
            r3 = r1
            r2.context = r3
            int r2 = android.os.Build.VERSION.SDK_INT
            r3 = 12
            if (r2 < r3) goto L_0x0043
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r6 = r2
            r2 = r6
            r3 = r6
            r3.<init>()
            java.lang.String r3 = com.google.appinventor.components.runtime.util.HoneycombMR1Util.getTimesContacted()
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = " DESC, "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = com.google.appinventor.components.runtime.util.HoneycombMR1Util.getDisplayName()
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            G9FdWvm6zShqpzDC2UOqX6MiQExLolZTefPBjzVvfkE9Kp2rmQld5rkb2wriBYfL = r2
        L_0x0042:
            return
        L_0x0043:
            java.lang.String r2 = "times_contacted DESC, name"
            G9FdWvm6zShqpzDC2UOqX6MiQExLolZTefPBjzVvfkE9Kp2rmQld5rkb2wriBYfL = r2
            goto L_0x0042
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.EmailAddressAdapter.<init>(android.content.Context):void");
    }

    public final String convertToString(Cursor cursor) {
        String string;
        String string2;
        Rfc822Token rfc822Token;
        Cursor cursor2 = cursor;
        int columnIndex = cursor2.getColumnIndex(HoneycombMR1Util.getDisplayName());
        int columnIndex2 = cursor2.getColumnIndex(HoneycombMR1Util.getEmailAddress());
        if (Build.VERSION.SDK_INT >= 12) {
            string = cursor2.getString(columnIndex);
            string2 = cursor2.getString(columnIndex2);
        } else {
            string = cursor2.getString(1);
            string2 = cursor2.getString(2);
        }
        new Rfc822Token(string, string2, (String) null);
        return rfc822Token.toString();
    }

    public final void bindView(View view, Context context2, Cursor cursor) {
        StringBuilder sb;
        String string;
        String string2;
        Context context3 = context2;
        TextView textView = (TextView) view;
        Cursor cursor2 = cursor;
        Cursor cursor3 = cursor2;
        int columnIndex = cursor2.getColumnIndex(HoneycombMR1Util.getDisplayName());
        int columnIndex2 = cursor3.getColumnIndex(HoneycombMR1Util.getEmailAddress());
        new StringBuilder();
        StringBuilder sb2 = sb;
        boolean z = false;
        if (Build.VERSION.SDK_INT >= 12) {
            string = cursor3.getString(columnIndex);
            string2 = cursor3.getString(columnIndex2);
        } else {
            string = cursor3.getString(1);
            string2 = cursor3.getString(2);
        }
        if (!TextUtils.isEmpty(string)) {
            StringBuilder append = sb2.append(string);
            z = true;
        }
        if (z) {
            StringBuilder append2 = sb2.append(" <");
        }
        StringBuilder append3 = sb2.append(string2);
        if (z) {
            StringBuilder append4 = sb2.append(">");
        }
        textView.setText(sb2.toString());
    }

    public Cursor runQueryOnBackgroundThread(CharSequence charSequence) {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        CharSequence charSequence2 = charSequence;
        Uri uri = null;
        new StringBuilder();
        StringBuilder sb4 = sb;
        if (charSequence2 != null) {
            new StringBuilder();
            String sqlEscapeString = DatabaseUtils.sqlEscapeString(sb2.append(charSequence2.toString()).append('%').toString());
            if (Build.VERSION.SDK_INT >= 12) {
                uri = HoneycombMR1Util.getDataContentUri();
                new StringBuilder("(");
                StringBuilder append = sb4.append(sb3.append(HoneycombMR1Util.getDataMimeType()).append("='").append(HoneycombMR1Util.getEmailType()).append("')").toString());
                StringBuilder append2 = sb4.append(" AND ");
                StringBuilder append3 = sb4.append("(display_name LIKE ");
                StringBuilder append4 = sb4.append(sqlEscapeString);
                StringBuilder append5 = sb4.append(")");
            } else {
                uri = Contacts.ContactMethods.CONTENT_EMAIL_URI;
                StringBuilder append6 = sb4.append("(name LIKE ");
                StringBuilder append7 = sb4.append(sqlEscapeString);
                StringBuilder append8 = sb4.append(") OR (display_name LIKE ");
                StringBuilder append9 = sb4.append(sqlEscapeString);
                StringBuilder append10 = sb4.append(")");
            }
        }
        String sb5 = sb4.toString();
        if (Build.VERSION.SDK_INT >= 12) {
            return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.query(uri, vwEpIRqEf6xdtwTR9dehwBO7JUhyLV6iEzEK2WqfPN10eUMQDPn3AUmqAFfsnr6R, sb5, (String[]) null, G9FdWvm6zShqpzDC2UOqX6MiQExLolZTefPBjzVvfkE9Kp2rmQld5rkb2wriBYfL);
        }
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.query(uri, hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO, sb5, (String[]) null, G9FdWvm6zShqpzDC2UOqX6MiQExLolZTefPBjzVvfkE9Kp2rmQld5rkb2wriBYfL);
    }
}

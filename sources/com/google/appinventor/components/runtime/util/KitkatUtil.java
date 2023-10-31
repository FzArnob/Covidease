package com.google.appinventor.components.runtime.util;

import android.content.Intent;
import android.os.Build;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class KitkatUtil {
    private KitkatUtil() {
    }

    public static List<SmsMessage> getMessagesFromIntent(Intent intent) {
        List<SmsMessage> list;
        new ArrayList();
        List<SmsMessage> list2 = list;
        SmsMessage[] messagesFromIntent = Telephony.Sms.Intents.getMessagesFromIntent(intent);
        SmsMessage[] smsMessageArr = messagesFromIntent;
        if (messagesFromIntent != null && smsMessageArr.length >= 0) {
            boolean addAll = Collections.addAll(list2, smsMessageArr);
        }
        return list2;
    }

    public static int getMinWidth(TextView textView) {
        TextView textView2 = textView;
        if (Build.VERSION.SDK_INT >= 16) {
            return textView2.getMinWidth();
        }
        return textView2.getWidth();
    }

    public static int getMinHeight(TextView textView) {
        TextView textView2 = textView;
        if (Build.VERSION.SDK_INT >= 16) {
            return textView2.getMinHeight();
        }
        return textView2.getHeight();
    }
}

package com.google.appinventor.components.runtime.util;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.p000v4.app.NotificationCompat;
import android.telephony.PhoneNumberUtils;
import android.telephony.SmsMessage;
import android.util.Log;
import com.google.appinventor.components.runtime.ReplForm;
import com.google.appinventor.components.runtime.Texting;
import gnu.expr.Declaration;

public class SmsBroadcastReceiver extends BroadcastReceiver {
    public static final int NOTIFICATION_ID = 8647;
    public static final String TAG = "SmsBroadcastReceiver";

    public SmsBroadcastReceiver() {
    }

    public void onReceive(Context context, Intent intent) {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        Intent intent2;
        NotificationCompat.Builder builder;
        StringBuilder sb5;
        StringBuilder sb6;
        StringBuilder sb7;
        StringBuilder sb8;
        Context context2 = context;
        Intent intent3 = intent;
        int i = Log.i(TAG, "onReceive");
        String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(intent3);
        String B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(intent3);
        new StringBuilder("Received ");
        int i2 = Log.i(TAG, sb.append(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).append(" : ").append(B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T).toString());
        int isReceivingEnabled = Texting.isReceivingEnabled(context2);
        int i3 = isReceivingEnabled;
        if (isReceivingEnabled == 1) {
            new StringBuilder();
            int i4 = Log.i(TAG, sb8.append(context2.getApplicationInfo().packageName).append(" Receiving is not enabled, ignoring message.").toString());
        } else if ((i3 == 2 || hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(context2)) && !Texting.isRunning()) {
            new StringBuilder();
            int i5 = Log.i(TAG, sb7.append(context2.getApplicationInfo().packageName).append(" Texting isn't running, and either receivingEnabled is FOREGROUND or we are the repl.").toString());
        } else {
            Texting.handledReceivedMessage(context2, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T);
            if (Texting.isRunning()) {
                new StringBuilder();
                int i6 = Log.i(TAG, sb6.append(context2.getApplicationInfo().packageName).append(" App in Foreground, delivering message.").toString());
                return;
            }
            new StringBuilder();
            int i7 = Log.i(TAG, sb2.append(context2.getApplicationInfo().packageName).append(" Texting isn't running, but receivingEnabled == 2, sending notification.").toString());
            String str = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            String str2 = B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
            String str3 = str;
            Context context3 = context2;
            new StringBuilder("sendingNotification ");
            int i8 = Log.i(TAG, sb3.append(str3).append(":").append(str2).toString());
            String packageName = context3.getPackageName();
            int i9 = Log.i(TAG, "Package name : ".concat(String.valueOf(packageName)));
            try {
                new StringBuilder();
                String sb9 = sb4.append(packageName).append(".Screen1").toString();
                new Intent(context3, Class.forName(sb9));
                Intent intent4 = intent2;
                Intent intent5 = intent4;
                Intent action = intent4.setAction("android.intent.action.MAIN");
                Intent addCategory = intent5.addCategory("android.intent.category.LAUNCHER");
                Intent addFlags = intent5.addFlags(805306368);
                PendingIntent activity = PendingIntent.getActivity(context3, 0, intent5, Declaration.PACKAGE_ACCESS);
                NotificationManager notificationManager = (NotificationManager) context3.getSystemService("notification");
                new NotificationCompat.Builder(context3);
                NotificationCompat.Builder smallIcon = builder.setSmallIcon(17301648);
                new StringBuilder();
                notificationManager.notify((String) null, NOTIFICATION_ID, smallIcon.setTicker(sb5.append(str3).append(" : ").append(str2).toString()).setWhen(System.currentTimeMillis()).setAutoCancel(true).setDefaults(1).setContentTitle("Sms from ".concat(String.valueOf(str3))).setContentText(str2).setContentIntent(activity).setNumber(Texting.getCachedMsgCount()).build());
                int i10 = Log.i(TAG, "Notification sent, classname: ".concat(String.valueOf(sb9)));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private static String hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Intent intent) {
        Intent intent2 = intent;
        String str = "";
        try {
            if (intent2.getAction().equals("com.google.android.apps.googlevoice.SMS_RECEIVED")) {
                String string = intent2.getExtras().getString(Texting.PHONE_NUMBER_TAG);
                String str2 = string;
                str = PhoneNumberUtils.formatNumber(string);
            } else if (Build.VERSION.SDK_INT >= 19) {
                for (SmsMessage next : KitkatUtil.getMessagesFromIntent(intent2)) {
                    SmsMessage smsMessage = next;
                    if (next != null) {
                        String originatingAddress = smsMessage.getOriginatingAddress();
                        if (Build.VERSION.SDK_INT >= 21) {
                            str = LollipopUtil.formatNumber(originatingAddress);
                        } else {
                            str = PhoneNumberUtils.formatNumber(originatingAddress);
                        }
                    }
                }
            } else {
                Object[] objArr = (Object[]) intent2.getExtras().get("pdus");
                Object[] objArr2 = objArr;
                int length = objArr.length;
                for (int i = 0; i < length; i++) {
                    String originatingAddress2 = SmsMessage.createFromPdu((byte[]) objArr2[i]).getOriginatingAddress();
                    String str3 = originatingAddress2;
                    str = PhoneNumberUtils.formatNumber(originatingAddress2);
                }
            }
        } catch (NullPointerException e) {
            int w = Log.w(TAG, "Unable to retrieve originating address from SmsMessage", e);
        }
        return str;
    }

    private static String B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(Intent intent) {
        StringBuilder sb;
        StringBuilder sb2;
        Intent intent2 = intent;
        String str = "";
        try {
            if (intent2.getAction().equals("com.google.android.apps.googlevoice.SMS_RECEIVED")) {
                str = intent2.getExtras().getString(Texting.MESSAGE_TAG);
            } else if (Build.VERSION.SDK_INT >= 19) {
                new StringBuilder();
                StringBuilder sb3 = sb2;
                for (SmsMessage next : KitkatUtil.getMessagesFromIntent(intent2)) {
                    SmsMessage smsMessage = next;
                    if (next != null) {
                        StringBuilder append = sb3.append(smsMessage.getMessageBody());
                    }
                }
                str = sb3.toString();
            } else {
                new StringBuilder();
                StringBuilder sb4 = sb;
                Object[] objArr = (Object[]) intent2.getExtras().get("pdus");
                Object[] objArr2 = objArr;
                int length = objArr.length;
                for (int i = 0; i < length; i++) {
                    StringBuilder append2 = sb4.append(SmsMessage.createFromPdu((byte[]) objArr2[i]).getMessageBody());
                }
                str = sb4.toString();
            }
        } catch (NullPointerException e) {
            int w = Log.w(TAG, "Unable to retrieve message body from SmsMessage", e);
        }
        return str;
    }

    private static boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Context context) {
        StringBuilder sb;
        try {
            String packageName = context.getPackageName();
            new StringBuilder();
            if (Class.forName(sb.append(packageName).append(".Screen1").toString()).getSuperclass().equals(ReplForm.class)) {
                return true;
            }
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}

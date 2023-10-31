package android.support.p000v4.app;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.os.Build;
import android.support.annotation.NonNull;

/* renamed from: android.support.v4.app.AlarmManagerCompat */
public final class AlarmManagerCompat {
    public static void setAlarmClock(@NonNull AlarmManager alarmManager, long j, @NonNull PendingIntent pendingIntent, @NonNull PendingIntent pendingIntent2) {
        AlarmManager.AlarmClockInfo alarmClockInfo;
        AlarmManager alarmManager2 = alarmManager;
        long triggerTime = j;
        PendingIntent showIntent = pendingIntent;
        PendingIntent operation = pendingIntent2;
        if (Build.VERSION.SDK_INT >= 21) {
            new AlarmManager.AlarmClockInfo(triggerTime, showIntent);
            alarmManager2.setAlarmClock(alarmClockInfo, operation);
            return;
        }
        setExact(alarmManager2, 0, triggerTime, operation);
    }

    public static void setAndAllowWhileIdle(@NonNull AlarmManager alarmManager, int i, long j, @NonNull PendingIntent pendingIntent) {
        AlarmManager alarmManager2 = alarmManager;
        int type = i;
        long triggerAtMillis = j;
        PendingIntent operation = pendingIntent;
        if (Build.VERSION.SDK_INT >= 23) {
            alarmManager2.setAndAllowWhileIdle(type, triggerAtMillis, operation);
        } else {
            alarmManager2.set(type, triggerAtMillis, operation);
        }
    }

    public static void setExact(@NonNull AlarmManager alarmManager, int i, long j, @NonNull PendingIntent pendingIntent) {
        AlarmManager alarmManager2 = alarmManager;
        int type = i;
        long triggerAtMillis = j;
        PendingIntent operation = pendingIntent;
        if (Build.VERSION.SDK_INT >= 19) {
            alarmManager2.setExact(type, triggerAtMillis, operation);
        } else {
            alarmManager2.set(type, triggerAtMillis, operation);
        }
    }

    public static void setExactAndAllowWhileIdle(@NonNull AlarmManager alarmManager, int i, long j, @NonNull PendingIntent pendingIntent) {
        AlarmManager alarmManager2 = alarmManager;
        int type = i;
        long triggerAtMillis = j;
        PendingIntent operation = pendingIntent;
        if (Build.VERSION.SDK_INT >= 23) {
            alarmManager2.setExactAndAllowWhileIdle(type, triggerAtMillis, operation);
        } else {
            setExact(alarmManager2, type, triggerAtMillis, operation);
        }
    }

    private AlarmManagerCompat() {
    }
}

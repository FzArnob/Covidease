package com.firebase.client.android;

import android.util.Log;
import com.firebase.client.Logger;
import com.firebase.client.utilities.DefaultLogger;
import java.util.List;

public class AndroidLogger extends DefaultLogger {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AndroidLogger(Logger.Level level, List<String> enabledComponents) {
        super(level, enabledComponents);
    }

    /* access modifiers changed from: protected */
    public String buildLogMessage(Logger.Level level, String str, String message, long j) {
        Logger.Level level2 = level;
        String str2 = str;
        long j2 = j;
        return message;
    }

    /* access modifiers changed from: protected */
    public void error(String tag, String toLog) {
        int e = Log.e(tag, toLog);
    }

    /* access modifiers changed from: protected */
    public void warn(String tag, String toLog) {
        int w = Log.w(tag, toLog);
    }

    /* access modifiers changed from: protected */
    public void info(String tag, String toLog) {
        int i = Log.i(tag, toLog);
    }

    /* access modifiers changed from: protected */
    public void debug(String tag, String toLog) {
        int d = Log.d(tag, toLog);
    }
}

package android.support.p000v4.util;

import android.support.annotation.RestrictTo;
import android.util.Log;
import java.io.Writer;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v4.util.LogWriter */
public class LogWriter extends Writer {
    private StringBuilder mBuilder;
    private final String mTag;

    public LogWriter(String tag) {
        StringBuilder sb;
        new StringBuilder(128);
        this.mBuilder = sb;
        this.mTag = tag;
    }

    public void close() {
        flushBuilder();
    }

    public void flush() {
        flushBuilder();
    }

    public void write(char[] cArr, int i, int i2) {
        char[] buf = cArr;
        int offset = i;
        int count = i2;
        for (int i3 = 0; i3 < count; i3++) {
            char c = buf[offset + i3];
            if (c == 10) {
                flushBuilder();
            } else {
                StringBuilder append = this.mBuilder.append(c);
            }
        }
    }

    private void flushBuilder() {
        if (this.mBuilder.length() > 0) {
            int d = Log.d(this.mTag, this.mBuilder.toString());
            StringBuilder delete = this.mBuilder.delete(0, this.mBuilder.length());
        }
    }
}

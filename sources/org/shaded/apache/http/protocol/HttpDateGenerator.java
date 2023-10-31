package org.shaded.apache.http.protocol;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class HttpDateGenerator {
    public static final TimeZone GMT = TimeZone.getTimeZone("GMT");
    public static final String PATTERN_RFC1123 = "EEE, dd MMM yyyy HH:mm:ss zzz";
    private long dateAsLong = 0;
    private String dateAsText = null;
    private final DateFormat dateformat;

    public HttpDateGenerator() {
        DateFormat dateFormat;
        new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
        this.dateformat = dateFormat;
        this.dateformat.setTimeZone(GMT);
    }

    public synchronized String getCurrentDate() {
        String str;
        Date date;
        synchronized (this) {
            long now = System.currentTimeMillis();
            if (now - this.dateAsLong > 1000) {
                new Date(now);
                this.dateAsText = this.dateformat.format(date);
                this.dateAsLong = now;
            }
            str = this.dateAsText;
        }
        return str;
    }
}

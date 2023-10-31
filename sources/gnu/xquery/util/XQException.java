package gnu.xquery.util;

import android.support.p000v4.app.NotificationCompat;
import gnu.mapping.Symbol;
import gnu.mapping.Values;

public class XQException extends RuntimeException {
    public static Symbol FOER0000_QNAME = Symbol.make("http://www.w3.org/2005/xqt-errors", "FOER0000", NotificationCompat.CATEGORY_ERROR);
    public Symbol code;
    public String description;
    public Object errorValue;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public XQException(gnu.mapping.Symbol r7, java.lang.String r8, java.lang.Object r9) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r8
            r3 = r9
            r4 = r0
            r5 = r2
            r4.<init>(r5)
            r4 = r0
            r5 = r1
            r4.code = r5
            r4 = r0
            r5 = r2
            r4.description = r5
            r4 = r0
            r5 = r3
            r4.errorValue = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xquery.util.XQException.<init>(gnu.mapping.Symbol, java.lang.String, java.lang.Object):void");
    }

    public static void error() {
        Throwable th;
        Throwable th2 = th;
        new XQException(FOER0000_QNAME, (String) null, (Object) null);
        throw th2;
    }

    public static void error(Symbol error) {
        Throwable th;
        Throwable th2 = th;
        new XQException(error, (String) null, (Object) null);
        throw th2;
    }

    public static void error(Object obj, String str) {
        Throwable th;
        Object error = obj;
        String description2 = str;
        if (error == null || error == Values.empty) {
            error = FOER0000_QNAME;
        }
        Throwable th2 = th;
        new XQException((Symbol) error, description2, (Object) null);
        throw th2;
    }

    public static void error(Object obj, String str, Object obj2) {
        Throwable th;
        Object error = obj;
        String description2 = str;
        Object errorValue2 = obj2;
        if (error == null || error == Values.empty) {
            error = FOER0000_QNAME;
        }
        Throwable th2 = th;
        new XQException((Symbol) error, description2, errorValue2);
        throw th2;
    }

    public String getMessage() {
        StringBuffer stringBuffer;
        new StringBuffer(100);
        StringBuffer sbuf = stringBuffer;
        if (this.description == null) {
            StringBuffer append = sbuf.append("XQuery-error");
        } else {
            StringBuffer append2 = sbuf.append(this.description);
        }
        if (this.code != null) {
            StringBuffer append3 = sbuf.append(" [");
            String prefix = this.code.getPrefix();
            if (prefix != null && prefix.length() > 0) {
                StringBuffer append4 = sbuf.append(prefix);
                StringBuffer append5 = sbuf.append(':');
            }
            StringBuffer append6 = sbuf.append(this.code.getLocalName());
            StringBuffer append7 = sbuf.append(']');
        }
        if (!(this.errorValue == null || this.errorValue == Values.empty)) {
            StringBuffer append8 = sbuf.append(" value: ");
            StringBuffer append9 = sbuf.append(this.errorValue);
        }
        return sbuf.toString();
    }
}

package gnu.kawa.servlet;

import gnu.kawa.xml.HttpPrinter;

public class ServletPrinter extends HttpPrinter {
    HttpRequestContext hctx;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ServletPrinter(gnu.kawa.servlet.HttpRequestContext r10, int r11) throws java.io.IOException {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r2 = r11
            r3 = r0
            gnu.kawa.servlet.HttpOutputStream r4 = new gnu.kawa.servlet.HttpOutputStream
            r8 = r4
            r4 = r8
            r5 = r8
            r6 = r1
            r7 = r2
            r5.<init>(r6, r7)
            r3.<init>((java.io.OutputStream) r4)
            r3 = r0
            r4 = r1
            r3.hctx = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.servlet.ServletPrinter.<init>(gnu.kawa.servlet.HttpRequestContext, int):void");
    }

    public void addHeader(String str, String str2) {
        String label = str;
        String value = str2;
        if (label.equalsIgnoreCase("Content-type")) {
            this.sawContentType = value;
            this.hctx.setContentType(value);
        } else if (label.equalsIgnoreCase("Status")) {
            int lval = value.length();
            int code = 0;
            int i = 0;
            while (i < lval) {
                if (i >= lval) {
                    this.hctx.statusCode = code;
                    return;
                }
                char ch = value.charAt(i);
                int digit = Character.digit(ch, 10);
                if (digit >= 0) {
                    code = (10 * code) + digit;
                    i++;
                } else {
                    if (ch == ' ') {
                        i++;
                    }
                    this.hctx.statusCode = code;
                    this.hctx.statusReasonPhrase = value.substring(i);
                    return;
                }
            }
        } else {
            this.hctx.setResponseHeader(label, value);
        }
    }

    public void printHeaders() {
    }

    public boolean reset(boolean headersAlso) {
        return ((HttpOutputStream) this.ostream).reset() & super.reset(headersAlso);
    }
}

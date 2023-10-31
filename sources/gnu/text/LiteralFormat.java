package gnu.text;

import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;
import java.text.ParsePosition;

public class LiteralFormat extends ReportFormat {
    char[] text;

    public LiteralFormat(char[] text2) {
        this.text = text2;
    }

    public LiteralFormat(String text2) {
        this.text = text2.toCharArray();
    }

    public LiteralFormat(StringBuffer stringBuffer) {
        StringBuffer sbuf = stringBuffer;
        int len = sbuf.length();
        this.text = new char[len];
        sbuf.getChars(0, len, this.text, 0);
    }

    public int format(Object[] objArr, int start, Writer dst, FieldPosition fieldPosition) throws IOException {
        Object[] objArr2 = objArr;
        FieldPosition fieldPosition2 = fieldPosition;
        dst.write(this.text);
        return start;
    }

    public Object parseObject(String str, ParsePosition parsePosition) {
        Throwable th;
        String str2 = str;
        ParsePosition parsePosition2 = parsePosition;
        Throwable th2 = th;
        new Error("LiteralFormat.parseObject - not implemented");
        throw th2;
    }

    public String content() {
        String str;
        new String(this.text);
        return str;
    }

    public String toString() {
        StringBuffer stringBuffer;
        new StringBuffer("LiteralFormat[\"");
        StringBuffer sbuf = stringBuffer;
        StringBuffer append = sbuf.append(this.text);
        StringBuffer append2 = sbuf.append("\"]");
        return sbuf.toString();
    }
}

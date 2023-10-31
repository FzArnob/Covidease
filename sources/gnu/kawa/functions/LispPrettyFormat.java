package gnu.kawa.functions;

import gnu.mapping.OutPort;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;
import java.text.Format;

/* compiled from: LispFormat */
class LispPrettyFormat extends ReportFormat {
    Format body;
    boolean perLine;
    String prefix;
    boolean seenAt;
    Format[] segments;
    String suffix;

    LispPrettyFormat() {
    }

    /* JADX INFO: finally extract failed */
    public int format(Object[] objArr, int i, Writer writer, FieldPosition fieldPosition) throws IOException {
        int start;
        Object[] args = objArr;
        int start2 = i;
        Writer dst = writer;
        FieldPosition fpos = fieldPosition;
        String pre = this.prefix;
        String suf = this.suffix;
        OutPort out = dst instanceof OutPort ? (OutPort) dst : null;
        try {
            if (this.seenAt) {
                if (out != null) {
                    out.startLogicalBlock(pre, this.perLine, this.suffix);
                }
                start = ReportFormat.format(this.body, args, start2, dst, fpos);
            } else {
                Object curArg = args[start2];
                Object[] curArr = LispFormat.asArray(curArg);
                if (curArr == null) {
                    String str = "";
                    suf = str;
                    pre = str;
                }
                if (out != null) {
                    out.startLogicalBlock(pre, this.perLine, this.suffix);
                }
                if (curArr == null) {
                    boolean format = ObjectFormat.format(curArg, dst, -1, true);
                } else {
                    int format2 = ReportFormat.format(this.body, curArr, 0, dst, fpos);
                }
                start = start2 + 1;
            }
            if (out != null) {
                out.endLogicalBlock(suf);
            }
            return start;
        } catch (Throwable th) {
            Throwable th2 = th;
            if (out != null) {
                out.endLogicalBlock(suf);
            }
            throw th2;
        }
    }

    public String toString() {
        StringBuffer stringBuffer;
        new StringBuffer();
        StringBuffer sbuf = stringBuffer;
        StringBuffer append = sbuf.append("LispPrettyFormat[");
        StringBuffer append2 = sbuf.append("prefix: \"");
        StringBuffer append3 = sbuf.append(this.prefix);
        StringBuffer append4 = sbuf.append("\", suffix: \"");
        StringBuffer append5 = sbuf.append(this.suffix);
        StringBuffer append6 = sbuf.append("\", body: ");
        StringBuffer append7 = sbuf.append(this.body);
        StringBuffer append8 = sbuf.append("]");
        return sbuf.toString();
    }
}

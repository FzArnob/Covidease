package gnu.kawa.functions;

import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;
import java.text.Format;

/* compiled from: LispFormat */
class LispIterationFormat extends ReportFormat {
    boolean atLeastOnce;
    Format body;
    int maxIterations;
    boolean seenAt;
    boolean seenColon;

    LispIterationFormat() {
    }

    public static int format(Format format, int i, Object[] objArr, int i2, Writer writer, boolean z, boolean z2) throws IOException {
        Format body2 = format;
        int maxIterations2 = i;
        Object[] args = objArr;
        int start = i2;
        Writer dst = writer;
        boolean seenColon2 = z;
        boolean atLeastOnce2 = z2;
        int i3 = 0;
        while (true) {
            if ((i3 == maxIterations2 && maxIterations2 != -1) || (start == args.length && (i3 > 0 || !atLeastOnce2))) {
                break;
            }
            if (seenColon2) {
                Object[] curArr = LispFormat.asArray(args[start]);
                if (curArr == null) {
                }
                start++;
                if (ReportFormat.resultCode(ReportFormat.format(body2, curArr, 0, dst, (FieldPosition) null)) == 242) {
                    break;
                }
            } else {
                start = ReportFormat.format(body2, args, start, dst, (FieldPosition) null);
                if (start < 0) {
                    start = ReportFormat.nextArg(start);
                    break;
                }
            }
            i3++;
        }
        return start;
    }

    public int format(Object[] objArr, int i, Writer writer, FieldPosition fieldPosition) throws IOException {
        StringBuilder sb;
        Format format;
        Object[] args = objArr;
        int start = i;
        Writer dst = writer;
        FieldPosition fieldPosition2 = fieldPosition;
        int maxIterations2 = getParam(this.maxIterations, -1, args, start);
        if (this.maxIterations == -1610612736) {
            start++;
        }
        Format body2 = this.body;
        if (body2 == null) {
            int i2 = start;
            start++;
            Object arg = args[i2];
            if (arg instanceof Format) {
                body2 = (Format) arg;
            } else {
                try {
                    Format format2 = format;
                    new LispFormat(arg.toString());
                    body2 = format2;
                } catch (Exception e) {
                    Exception exc = e;
                    print(dst, "<invalid argument for \"~{~}\" format>");
                    return args.length;
                }
            }
        }
        if (this.seenAt) {
            return format(body2, maxIterations2, args, start, dst, this.seenColon, this.atLeastOnce);
        }
        Object arg2 = args[start];
        Object[] curArgs = LispFormat.asArray(arg2);
        if (curArgs == null) {
            new StringBuilder();
            dst.write(sb.append("{").append(arg2).append("}".toString()).toString());
        } else {
            int format3 = format(body2, maxIterations2, curArgs, 0, dst, this.seenColon, this.atLeastOnce);
        }
        return start + 1;
    }

    public String toString() {
        StringBuffer stringBuffer;
        new StringBuffer();
        StringBuffer sbuf = stringBuffer;
        StringBuffer append = sbuf.append("LispIterationFormat[");
        StringBuffer append2 = sbuf.append(this.body);
        StringBuffer append3 = sbuf.append("]");
        return sbuf.toString();
    }
}

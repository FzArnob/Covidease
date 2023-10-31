package gnu.text;

import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

public class CompoundFormat extends ReportFormat {
    protected Format[] formats;
    protected int length;

    public CompoundFormat(Format[] formats2, int length2) {
        this.formats = formats2;
        this.length = length2;
    }

    public CompoundFormat(Format[] formatArr) {
        Format[] formats2 = formatArr;
        this.formats = formats2;
        this.length = formats2.length;
    }

    public int format(Object[] objArr, int i, Writer writer, FieldPosition fieldPosition) throws IOException {
        StringBuffer stringBuffer;
        Object[] args = objArr;
        int start = i;
        Writer dst = writer;
        FieldPosition fpos = fieldPosition;
        for (int i2 = 0; i2 < this.length; i2++) {
            Format fmt = this.formats[i2];
            if (fmt instanceof ReportFormat) {
                start = ((ReportFormat) fmt).format(args, start, dst, fpos);
                if (start < 0) {
                    return start;
                }
            } else if (start >= args.length) {
                dst.write("#<missing format argument>");
            } else {
                new StringBuffer();
                StringBuffer sbuf = stringBuffer;
                StringBuffer format = fmt.format(args[start], sbuf, fpos);
                dst.write(sbuf.toString());
                start++;
            }
        }
        return start;
    }

    public final int format(Object[] objArr, int i, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        Object[] args = objArr;
        int start = i;
        StringBuffer sbuf = stringBuffer;
        FieldPosition fpos = fieldPosition;
        for (int i2 = 0; i2 < this.length; i2++) {
            Format fmt = this.formats[i2];
            if (fmt instanceof ReportFormat) {
                start = ((ReportFormat) fmt).format(args, start, sbuf, fpos);
                if (start < 0) {
                    return start;
                }
            } else {
                StringBuffer format = fmt.format(args[start], sbuf, fpos);
                start++;
            }
        }
        return start;
    }

    public Object parseObject(String str, ParsePosition parsePosition) {
        Throwable th;
        String str2 = str;
        ParsePosition parsePosition2 = parsePosition;
        Throwable th2 = th;
        new Error("CompoundFormat.parseObject - not implemented");
        throw th2;
    }

    public String toString() {
        StringBuffer stringBuffer;
        new StringBuffer();
        StringBuffer sbuf = stringBuffer;
        StringBuffer append = sbuf.append("CompoundFormat[");
        for (int i = 0; i < this.length; i++) {
            if (i > 0) {
                StringBuffer append2 = sbuf.append(", ");
            }
            StringBuffer append3 = sbuf.append(this.formats[i]);
        }
        StringBuffer append4 = sbuf.append("]");
        return sbuf.toString();
    }
}

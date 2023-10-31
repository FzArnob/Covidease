package gnu.text;

import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;
import java.text.Format;
import java.text.MessageFormat;

public class PadFormat extends ReportFormat {
    Format fmt;
    int minWidth;
    char padChar;
    int where;

    public PadFormat(Format fmt2, int minWidth2, char padChar2, int where2) {
        this.fmt = fmt2;
        this.minWidth = minWidth2;
        this.padChar = padChar2;
        this.where = where2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public PadFormat(Format fmt2, int minWidth2) {
        this(fmt2, minWidth2, ' ', 100);
    }

    public int format(Object[] args, int start, Writer dst, FieldPosition fpos) throws IOException {
        return format(this.fmt, args, start, dst, this.padChar, this.minWidth, 1, 0, this.where, fpos);
    }

    public static int padNeeded(int i, int i2, int i3, int minPad) {
        int actualWidth = i;
        int minWidth2 = i2;
        int colInc = i3;
        int total = actualWidth + minPad;
        if (colInc <= 1) {
            colInc = minWidth2 - total;
        }
        while (total < minWidth2) {
            total += colInc;
        }
        return total - actualWidth;
    }

    public static int format(Format format, Object[] objArr, int i, Writer writer, char c, int i2, int i3, int i4, int i5, FieldPosition fieldPosition) throws IOException {
        StringBuffer stringBuffer;
        int start;
        Format fmt2 = format;
        Object[] args = objArr;
        int start2 = i;
        Writer dst = writer;
        char padChar2 = c;
        int minWidth2 = i2;
        int colInc = i3;
        int minPad = i4;
        int where2 = i5;
        FieldPosition fpos = fieldPosition;
        new StringBuffer(200);
        StringBuffer tbuf = stringBuffer;
        if (fmt2 instanceof ReportFormat) {
            start = ((ReportFormat) fmt2).format(args, start2, tbuf, fpos);
        } else if (fmt2 instanceof MessageFormat) {
            StringBuffer format2 = fmt2.format(args, tbuf, fpos);
            start = args.length;
        } else {
            StringBuffer format3 = fmt2.format(args[start2], tbuf, fpos);
            start = start2 + 1;
        }
        int len = tbuf.length();
        int pad = padNeeded(len, minWidth2, colInc, minPad);
        int prefix = 0;
        String text = tbuf.toString();
        if (pad > 0) {
            if (where2 == -1) {
                if (len > 0) {
                    char ch = text.charAt(0);
                    if (ch == '-' || ch == '+') {
                        prefix = 0 + 1;
                        dst.write(ch);
                    }
                    if (len - prefix > 2 && text.charAt(prefix) == '0') {
                        dst.write(48);
                        prefix++;
                        char ch2 = text.charAt(prefix);
                        if (ch2 == 'x' || ch2 == 'X') {
                            prefix++;
                            dst.write(ch2);
                        }
                    }
                    if (prefix > 0) {
                        text = text.substring(prefix);
                    }
                }
                where2 = 0;
            }
            int padAfter = (pad * where2) / 100;
            int padBefore = pad - padAfter;
            while (true) {
                padBefore--;
                if (padBefore < 0) {
                    break;
                }
                dst.write(padChar2);
            }
            dst.write(text);
            while (true) {
                padAfter--;
                if (padAfter < 0) {
                    break;
                }
                dst.write(padChar2);
            }
        } else {
            dst.write(text);
        }
        return start;
    }
}

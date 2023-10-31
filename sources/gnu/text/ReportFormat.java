package gnu.text;

import gnu.lists.Consumer;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.FieldPosition;
import java.text.Format;
import java.text.MessageFormat;
import java.text.ParsePosition;

public abstract class ReportFormat extends Format {
    public static final int PARAM_FROM_COUNT = -1342177280;
    public static final int PARAM_FROM_LIST = -1610612736;
    public static final int PARAM_UNSPECIFIED = -1073741824;

    public abstract int format(Object[] objArr, int i, Writer writer, FieldPosition fieldPosition) throws IOException;

    public ReportFormat() {
    }

    public static int result(int resultCode, int nextArg) {
        return (resultCode << 24) | nextArg;
    }

    public static int nextArg(int result) {
        return result & 16777215;
    }

    public static int resultCode(int result) {
        return result >>> 24;
    }

    public int format(Object obj, int i, Writer writer, FieldPosition fieldPosition) throws IOException {
        Object arg = obj;
        int start = i;
        Writer dst = writer;
        FieldPosition fpos = fieldPosition;
        if (arg instanceof Object[]) {
            return format((Object[]) arg, start, dst, fpos);
        }
        return format(new Object[]{arg}, start, dst, fpos);
    }

    public StringBuffer format(Object obj, StringBuffer stringBuffer, FieldPosition fpos) {
        StringBuffer sbuf = stringBuffer;
        int format = format((Object[]) obj, 0, sbuf, fpos);
        return sbuf;
    }

    public int format(Object[] args, int start, StringBuffer stringBuffer, FieldPosition fpos) {
        CharArrayWriter charArrayWriter;
        Throwable th;
        StringBuilder sb;
        StringBuffer sbuf = stringBuffer;
        new CharArrayWriter();
        CharArrayWriter wr = charArrayWriter;
        try {
            int start2 = format(args, start, (Writer) wr, fpos);
            if (start2 < 0) {
                return start2;
            }
            StringBuffer append = sbuf.append(wr.toCharArray());
            return start2;
        } catch (IOException e) {
            IOException ex = e;
            Throwable th2 = th;
            new StringBuilder();
            new Error(sb.append("unexpected exception: ").append(ex).toString());
            throw th2;
        }
    }

    public static int format(Format format, Object[] objArr, int i, Writer writer, FieldPosition fieldPosition) throws IOException {
        StringBuffer stringBuffer;
        int start;
        Format fmt = format;
        Object[] args = objArr;
        int start2 = i;
        Writer dst = writer;
        FieldPosition fpos = fieldPosition;
        if (fmt instanceof ReportFormat) {
            return ((ReportFormat) fmt).format(args, start2, dst, fpos);
        }
        new StringBuffer();
        StringBuffer sbuf = stringBuffer;
        if (fmt instanceof MessageFormat) {
            start = format(fmt, args, start2, sbuf, fpos);
        } else {
            int i2 = start2;
            start = start2 + 1;
            StringBuffer format2 = fmt.format(args[i2], sbuf, fpos);
        }
        int slen = sbuf.length();
        char[] cbuf = new char[slen];
        sbuf.getChars(0, slen, cbuf, 0);
        dst.write(cbuf);
        return start;
    }

    public static int format(Format format, Object[] objArr, int i, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        Object[] objArr2;
        int nargs;
        Format fmt = format;
        Object[] args = objArr;
        int start = i;
        StringBuffer sbuf = stringBuffer;
        FieldPosition fpos = fieldPosition;
        if (fmt instanceof ReportFormat) {
            return ((ReportFormat) fmt).format(args, start, sbuf, fpos);
        }
        if (fmt instanceof MessageFormat) {
            nargs = args.length - start;
            if (start > 0) {
                Object[] subarr = new Object[(args.length - start)];
                System.arraycopy(args, start, subarr, 0, subarr.length);
                objArr2 = subarr;
            } else {
                objArr2 = args;
            }
        } else {
            objArr2 = args[start];
            nargs = 1;
        }
        StringBuffer format2 = fmt.format(objArr2, sbuf, fpos);
        return start + nargs;
    }

    public static void print(Writer writer, String str) throws IOException {
        Writer dst = writer;
        String str2 = str;
        if (dst instanceof PrintWriter) {
            ((PrintWriter) dst).print(str2);
        } else {
            dst.write(str2.toCharArray());
        }
    }

    public static void print(Object obj, Consumer consumer) {
        Object value = obj;
        Consumer out = consumer;
        if (value instanceof Printable) {
            ((Printable) value).print(out);
        } else {
            out.write(value == null ? "null" : value.toString());
        }
    }

    public Object parseObject(String str, ParsePosition parsePosition) {
        Throwable th;
        String str2 = str;
        ParsePosition parsePosition2 = parsePosition;
        Throwable th2 = th;
        new Error("ReportFormat.parseObject - not implemented");
        throw th2;
    }

    public static int getParam(Object obj, int i) {
        Object arg = obj;
        int defaultValue = i;
        if (arg instanceof Number) {
            return ((Number) arg).intValue();
        }
        if (arg instanceof Character) {
            return ((Character) arg).charValue();
        }
        if (arg instanceof Char) {
            return ((Char) arg).charValue();
        }
        return defaultValue;
    }

    protected static int getParam(int i, int i2, Object[] objArr, int i3) {
        int param = i;
        int defaultValue = i2;
        Object[] args = objArr;
        int start = i3;
        if (param == -1342177280) {
            return args.length - start;
        }
        if (param == -1610612736) {
            return args == null ? defaultValue : getParam(args[start], defaultValue);
        } else if (param == -1073741824) {
            return defaultValue;
        } else {
            return param;
        }
    }

    protected static char getParam(int param, char defaultValue, Object[] args, int start) {
        return (char) getParam(param, (int) defaultValue, args, start);
    }
}

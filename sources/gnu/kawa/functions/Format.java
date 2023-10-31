package gnu.kawa.functions;

import gnu.lists.FString;
import gnu.mapping.CharArrayOutPort;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import gnu.mapping.Values;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.text.FieldPosition;
import java.text.MessageFormat;

public class Format extends ProcedureN {
    public static final Format format;

    public Format() {
    }

    static {
        Format format2;
        new Format();
        format = format2;
        format.setName("format");
        format.setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileMisc:validateApplyFormat");
    }

    public static void format(Writer dst, Object[] objArr, int i) {
        Object[] args = objArr;
        int arg_offset = i;
        int i2 = arg_offset;
        int arg_offset2 = arg_offset + 1;
        Object format2 = args[i2];
        Object[] vals = new Object[(args.length - arg_offset2)];
        System.arraycopy(args, arg_offset2, vals, 0, vals.length);
        formatToWriter(dst, format2, vals);
    }

    public static void formatToWriter(Writer writer, Object obj, Object... objArr) {
        Throwable th;
        StringBuilder sb;
        Writer dst = writer;
        Object format2 = obj;
        Object[] vals = objArr;
        if (dst == null) {
            dst = OutPort.outDefault();
        }
        try {
            if (format2 instanceof MessageFormat) {
                dst.write(((MessageFormat) format2).format(vals));
                return;
            }
            if (!(format2 instanceof ReportFormat)) {
                format2 = ParseFormat.parseFormat.apply1(format2);
            }
            int format3 = ((ReportFormat) format2).format(vals, 0, dst, (FieldPosition) null);
        } catch (IOException e) {
            IOException ex = e;
            Throwable th2 = th;
            new StringBuilder();
            new RuntimeException(sb.append("Error in format: ").append(ex).toString());
            throw th2;
        }
    }

    public static void formatToOutputStream(OutputStream dst, Object format2, Object... vals) {
        OutPort outPort;
        new OutPort(dst);
        OutPort port = outPort;
        Object[] objArr = new Object[3];
        objArr[0] = port;
        Object[] objArr2 = objArr;
        objArr2[1] = format2;
        Object[] objArr3 = objArr2;
        objArr3[2] = vals;
        Object format3 = format(objArr3);
        port.closeThis();
    }

    public static String formatToString(int arg_offset, Object... args) {
        CharArrayOutPort charArrayOutPort;
        new CharArrayOutPort();
        CharArrayOutPort port = charArrayOutPort;
        format(port, args, arg_offset);
        String str = port.toString();
        port.close();
        return str;
    }

    public static FString formatToFString(char style, Object fmt, Object[] args) {
        CharArrayOutPort charArrayOutPort;
        Throwable th;
        StringBuilder sb;
        FString fString;
        ReportFormat rfmt = ParseFormat.asFormat(fmt, style);
        new CharArrayOutPort();
        CharArrayOutPort port = charArrayOutPort;
        try {
            int format2 = rfmt.format(args, 0, (Writer) port, (FieldPosition) null);
            char[] chars = port.toCharArray();
            port.close();
            new FString(chars);
            return fString;
        } catch (IOException e) {
            IOException ex = e;
            Throwable th2 = th;
            new StringBuilder();
            new RuntimeException(sb.append("Error in format: ").append(ex).toString());
            throw th2;
        }
    }

    public Object applyN(Object[] args) {
        return format(args);
    }

    public static Object format(Object... objArr) {
        Throwable th;
        Object[] args = objArr;
        Object port_arg = args[0];
        if (port_arg == Boolean.TRUE) {
            format(OutPort.outDefault(), args, 1);
            return Values.empty;
        } else if (port_arg == Boolean.FALSE) {
            return formatToString(1, args);
        } else {
            if ((port_arg instanceof MessageFormat) || (port_arg instanceof CharSequence) || (port_arg instanceof ReportFormat)) {
                return formatToString(0, args);
            }
            if (port_arg instanceof Writer) {
                format((Writer) port_arg, args, 1);
                return Values.empty;
            } else if (port_arg instanceof OutputStream) {
                formatToOutputStream((OutputStream) port_arg, args[1], drop2(args));
                return Values.empty;
            } else {
                Throwable th2 = th;
                new RuntimeException("bad first argument to format");
                throw th2;
            }
        }
    }

    static Object[] drop2(Object[] objArr) {
        Object[] vals = objArr;
        int xlen = vals.length - 2;
        Object[] xvals = new Object[xlen];
        System.arraycopy(vals, 2, xvals, 0, xlen);
        return xvals;
    }
}

package gnu.kawa.functions;

import gnu.lists.AbstractFormat;
import gnu.lists.Consumer;
import gnu.mapping.OutPort;
import gnu.text.ReportFormat;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;
import java.text.ParsePosition;
import kawa.standard.Scheme;

public class ObjectFormat extends ReportFormat {
    private static ObjectFormat plainFormat;
    private static ObjectFormat readableFormat;
    int maxChars;
    boolean readable;

    public static ObjectFormat getInstance(boolean readable2) {
        ObjectFormat objectFormat;
        ObjectFormat objectFormat2;
        if (readable2) {
            if (readableFormat == null) {
                new ObjectFormat(true);
                readableFormat = objectFormat2;
            }
            return readableFormat;
        }
        if (plainFormat == null) {
            new ObjectFormat(false);
            plainFormat = objectFormat;
        }
        return plainFormat;
    }

    public ObjectFormat(boolean readable2) {
        this.readable = readable2;
        this.maxChars = -1073741824;
    }

    public ObjectFormat(boolean readable2, int maxChars2) {
        this.readable = readable2;
        this.maxChars = maxChars2;
    }

    public int format(Object[] objArr, int i, Writer writer, FieldPosition fieldPosition) throws IOException {
        Object[] args = objArr;
        int start = i;
        Writer dst = writer;
        FieldPosition fieldPosition2 = fieldPosition;
        int maxChars2 = getParam(this.maxChars, -1, args, start);
        if (this.maxChars == -1610612736) {
            start++;
        }
        return format(args, start, dst, maxChars2, this.readable);
    }

    private static void print(Object obj, OutPort outPort, boolean z) {
        Object obj2 = obj;
        OutPort out = outPort;
        boolean readable2 = z;
        boolean saveReadable = out.printReadable;
        AbstractFormat saveFormat = out.objectFormat;
        try {
            out.printReadable = readable2;
            AbstractFormat format = readable2 ? Scheme.writeFormat : Scheme.displayFormat;
            out.objectFormat = format;
            format.writeObject(obj2, (Consumer) out);
            out.printReadable = saveReadable;
            out.objectFormat = saveFormat;
        } catch (Throwable th) {
            Throwable th2 = th;
            out.printReadable = saveReadable;
            out.objectFormat = saveFormat;
            throw th2;
        }
    }

    public static boolean format(Object obj, Writer writer, int i, boolean z) throws IOException {
        CharArrayWriter charArrayWriter;
        OutPort outPort;
        OutPort outPort2;
        Object arg = obj;
        Writer dst = writer;
        int maxChars2 = i;
        boolean readable2 = z;
        if (maxChars2 < 0 && (dst instanceof OutPort)) {
            print(arg, (OutPort) dst, readable2);
            return true;
        } else if (maxChars2 >= 0 || !(dst instanceof CharArrayWriter)) {
            new CharArrayWriter();
            CharArrayWriter wr = charArrayWriter;
            new OutPort((Writer) wr);
            OutPort oport = outPort;
            print(arg, oport, readable2);
            oport.close();
            int len = wr.size();
            if (maxChars2 < 0 || len <= maxChars2) {
                wr.writeTo(dst);
                return true;
            }
            dst.write(wr.toCharArray(), 0, maxChars2);
            return false;
        } else {
            new OutPort(dst);
            OutPort oport2 = outPort2;
            print(arg, oport2, readable2);
            oport2.close();
            return true;
        }
    }

    public static int format(Object[] objArr, int i, Writer writer, int i2, boolean z) throws IOException {
        Object obj;
        Object[] args = objArr;
        int start = i;
        Writer dst = writer;
        int maxChars2 = i2;
        boolean readable2 = z;
        if (start >= args.length) {
            obj = "#<missing format argument>";
            start--;
            readable2 = false;
            maxChars2 = -1;
        } else {
            obj = args[start];
        }
        boolean format = format(obj, dst, maxChars2, readable2);
        return start + 1;
    }

    public Object parseObject(String str, ParsePosition parsePosition) {
        Throwable th;
        String str2 = str;
        ParsePosition parsePosition2 = parsePosition;
        Throwable th2 = th;
        new RuntimeException("ObjectFormat.parseObject - not implemented");
        throw th2;
    }
}

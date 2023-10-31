package gnu.lists;

import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import gnu.mapping.CharArrayOutPort;
import gnu.mapping.OutPort;
import java.io.Writer;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

public abstract class AbstractFormat extends Format {
    public abstract void writeObject(Object obj, Consumer consumer);

    public AbstractFormat() {
    }

    /* access modifiers changed from: protected */
    public void write(String str, Consumer out) {
        out.write(str);
    }

    public void write(int v, Consumer out) {
        out.write(v);
    }

    public void writeLong(long v, Consumer out) {
        out.writeLong(v);
    }

    public void writeInt(int i, Consumer out) {
        writeLong((long) i, out);
    }

    public void writeBoolean(boolean v, Consumer out) {
        out.writeBoolean(v);
    }

    public void startElement(Object type, Consumer consumer) {
        Consumer out = consumer;
        write("(", out);
        write(type.toString(), out);
        write(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, out);
    }

    public void endElement(Consumer out) {
        write(")", out);
    }

    public void startAttribute(Object attrType, Consumer consumer) {
        Consumer out = consumer;
        write(attrType.toString(), out);
        write(": ", out);
    }

    public void endAttribute(Consumer out) {
        write(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, out);
    }

    public void format(Object obj, Consumer consumer) {
        Object value = obj;
        Consumer out = consumer;
        if (out instanceof OutPort) {
            OutPort pout = (OutPort) out;
            AbstractFormat saveFormat = pout.objectFormat;
            try {
                pout.objectFormat = this;
                out.writeObject(value);
                pout.objectFormat = saveFormat;
            } catch (Throwable th) {
                Throwable th2 = th;
                pout.objectFormat = saveFormat;
                throw th2;
            }
        } else {
            out.writeObject(value);
        }
    }

    public final void writeObject(Object obj, PrintConsumer out) {
        writeObject(obj, (Consumer) out);
    }

    public final void writeObject(Object obj, Writer writer) {
        OutPort port;
        Object obj2 = obj;
        Writer out = writer;
        if (out instanceof Consumer) {
            writeObject(obj2, (Consumer) out);
            return;
        }
        new OutPort(out, false, true);
        writeObject(obj2, (Consumer) out);
        port.close();
    }

    public StringBuffer format(Object val, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        CharArrayOutPort charArrayOutPort;
        StringBuffer sbuf = stringBuffer;
        FieldPosition fieldPosition2 = fieldPosition;
        new CharArrayOutPort();
        CharArrayOutPort out = charArrayOutPort;
        writeObject(val, (PrintConsumer) out);
        StringBuffer append = sbuf.append(out.toCharArray());
        out.close();
        return sbuf;
    }

    public Object parseObject(String str, ParsePosition parsePosition) {
        Throwable th;
        StringBuilder sb;
        String str2 = str;
        ParsePosition parsePosition2 = parsePosition;
        Throwable th2 = th;
        new StringBuilder();
        new Error(sb.append(getClass().getName()).append(".parseObject - not implemented").toString());
        throw th2;
    }
}

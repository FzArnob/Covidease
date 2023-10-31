package gnu.xquery.util;

import gnu.mapping.OutPort;
import gnu.mapping.WrappedException;
import gnu.xml.XMLPrinter;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class Debug {
    public static String traceFilename = "XQuery-trace.log";
    public static OutPort tracePort = null;
    public static String tracePrefix = "XQuery-trace: ";
    public static boolean traceShouldAppend = false;
    public static boolean traceShouldFlush = true;

    public Debug() {
    }

    public static synchronized Object trace(Object obj, Object obj2) {
        XMLPrinter xout;
        Object value;
        Object obj3;
        StringBuilder sb;
        OutPort outPort;
        OutputStream outputStream;
        Object value2 = obj;
        Object message = obj2;
        synchronized (Debug.class) {
            OutPort out = tracePort;
            if (out == null) {
                try {
                    OutPort outPort2 = outPort;
                    new FileOutputStream(traceFilename, traceShouldAppend);
                    new OutPort(outputStream);
                    out = outPort2;
                } catch (Throwable th) {
                    Throwable ex = th;
                    Object obj4 = obj3;
                    new StringBuilder();
                    new WrappedException(sb.append("Could not open '").append(traceFilename).append("' for fn:trace output").toString(), ex);
                }
                tracePort = out;
            }
            out.print(tracePrefix);
            out.print(message);
            out.print(' ');
            new XMLPrinter(out, false);
            xout.writeObject(value2);
            out.println();
            if (traceShouldFlush) {
                out.flush();
            }
            value = value2;
        }
        return value;
    }
}

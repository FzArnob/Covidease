package gnu.kawa.xml;

import gnu.mapping.OutPort;
import gnu.mapping.Procedure2;
import gnu.mapping.Values;
import gnu.text.Path;
import gnu.xml.XMLPrinter;
import java.io.OutputStream;

public class WriteTo extends Procedure2 {
    public static final WriteTo writeTo;
    public static final WriteTo writeToIfChanged;
    boolean ifChanged;

    public WriteTo() {
    }

    static {
        WriteTo writeTo2;
        WriteTo writeTo3;
        new WriteTo();
        writeTo = writeTo2;
        new WriteTo();
        writeToIfChanged = writeTo3;
        writeToIfChanged.ifChanged = true;
    }

    public static void writeTo(Object obj, Path path, OutputStream outs) throws Throwable {
        OutPort outPort;
        XMLPrinter xMLPrinter;
        Object value = obj;
        Path ppath = path;
        new OutPort(outs, ppath);
        OutPort out = outPort;
        new XMLPrinter(out, false);
        XMLPrinter consumer = xMLPrinter;
        if ("html".equals(ppath.getExtension())) {
            consumer.setStyle("html");
        }
        Values.writeValues(value, consumer);
        out.close();
    }

    public static void writeTo(Object value, Object path) throws Throwable {
        Path ppath = Path.valueOf(path);
        writeTo(value, ppath, ppath.openOutputStream());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x003e, code lost:
        if (r8 != false) goto L_0x005e;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void writeToIfChanged(java.lang.Object r13, java.lang.Object r14) throws java.lang.Throwable {
        /*
            r0 = r13
            r1 = r14
            r9 = r1
            gnu.text.Path r9 = gnu.text.Path.valueOf(r9)
            r2 = r9
            java.io.ByteArrayOutputStream r9 = new java.io.ByteArrayOutputStream
            r12 = r9
            r9 = r12
            r10 = r12
            r10.<init>()
            r3 = r9
            r9 = r0
            r10 = r2
            r11 = r3
            writeTo(r9, r10, r11)
            r9 = r3
            byte[] r9 = r9.toByteArray()
            r4 = r9
            java.io.BufferedInputStream r9 = new java.io.BufferedInputStream     // Catch:{ Throwable -> 0x0071 }
            r12 = r9
            r9 = r12
            r10 = r12
            r11 = r2
            java.io.InputStream r11 = r11.openInputStream()     // Catch:{ Throwable -> 0x0071 }
            r10.<init>(r11)     // Catch:{ Throwable -> 0x0071 }
            r5 = r9
            r9 = 0
            r6 = r9
        L_0x002d:
            r9 = r5
            int r9 = r9.read()     // Catch:{ Throwable -> 0x0071 }
            r7 = r9
            r9 = r6
            r10 = r4
            int r10 = r10.length     // Catch:{ Throwable -> 0x0071 }
            if (r9 != r10) goto L_0x005c
            r9 = 1
        L_0x0039:
            r8 = r9
            r9 = r7
            if (r9 >= 0) goto L_0x0063
            r9 = r8
            if (r9 != 0) goto L_0x005e
        L_0x0040:
            r9 = r5
            r9.close()     // Catch:{ Throwable -> 0x0071 }
        L_0x0044:
            java.io.BufferedOutputStream r9 = new java.io.BufferedOutputStream
            r12 = r9
            r9 = r12
            r10 = r12
            r11 = r2
            java.io.OutputStream r11 = r11.openOutputStream()
            r10.<init>(r11)
            r5 = r9
            r9 = r5
            r10 = r4
            r9.write(r10)
            r9 = r5
            r9.close()
        L_0x005b:
            return
        L_0x005c:
            r9 = 0
            goto L_0x0039
        L_0x005e:
            r9 = r5
            r9.close()     // Catch:{ Throwable -> 0x0071 }
            goto L_0x005b
        L_0x0063:
            r9 = r8
            if (r9 != 0) goto L_0x0040
            r9 = r4
            r10 = r6
            int r6 = r6 + 1
            byte r9 = r9[r10]     // Catch:{ Throwable -> 0x0071 }
            r10 = r7
            if (r9 == r10) goto L_0x0070
            goto L_0x0040
        L_0x0070:
            goto L_0x002d
        L_0x0071:
            r9 = move-exception
            r5 = r9
            goto L_0x0044
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.xml.WriteTo.writeToIfChanged(java.lang.Object, java.lang.Object):void");
    }

    public Object apply2(Object obj, Object obj2) throws Throwable {
        Object value = obj;
        Object fileName = obj2;
        if (this.ifChanged) {
            writeToIfChanged(value, fileName.toString());
        } else {
            writeTo(value, fileName.toString());
        }
        return Values.empty;
    }
}

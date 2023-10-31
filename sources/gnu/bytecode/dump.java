package gnu.bytecode;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Writer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class dump extends ClassFileInput {
    ClassTypeWriter writer;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    dump(InputStream str, ClassTypeWriter classTypeWriter) throws IOException, ClassFormatError {
        super(str);
        ClassType classType;
        ClassTypeWriter writer2 = classTypeWriter;
        new ClassType();
        this.ctype = classType;
        readFormatVersion();
        ConstantPool readConstants = readConstants();
        readClassInfo();
        readFields();
        readMethods();
        int readAttributes = readAttributes(this.ctype);
        writer2.print(this.ctype);
        writer2.flush();
    }

    public ConstantPool readConstants() throws IOException {
        this.ctype.constants = super.readConstants();
        return this.ctype.constants;
    }

    public Attribute readAttribute(String name, int length, AttrContainer container) throws IOException {
        return super.readAttribute(name, length, container);
    }

    static int readMagic(InputStream inputStream) throws IOException {
        int b;
        InputStream in = inputStream;
        int magic = 0;
        for (int j = 0; j < 4 && (b = in.read()) >= 0; j++) {
            magic = (magic << 8) | (b & 255);
        }
        return magic;
    }

    public static void process(InputStream in, String filename, OutputStream out, int flags) throws IOException {
        ClassTypeWriter classTypeWriter;
        new ClassTypeWriter((ClassType) null, out, flags);
        process(in, filename, classTypeWriter);
    }

    public static void process(InputStream in, String filename, Writer out, int flags) throws IOException {
        ClassTypeWriter classTypeWriter;
        new ClassTypeWriter((ClassType) null, out, flags);
        process(in, filename, classTypeWriter);
    }

    public static void process(InputStream in, String str, ClassTypeWriter classTypeWriter) throws IOException {
        InputStream inputStream;
        StringBuilder sb;
        ZipInputStream zipInputStream;
        Object obj;
        Object obj2;
        String filename = str;
        ClassTypeWriter out = classTypeWriter;
        new BufferedInputStream(in);
        InputStream inp = inputStream;
        inp.mark(5);
        int magic = readMagic(inp);
        if (magic == -889275714) {
            out.print("Reading .class from ");
            out.print(filename);
            out.println('.');
            Object obj3 = obj2;
            new dump(inp, out);
        } else if (magic == 1347093252) {
            inp.reset();
            out.print("Reading classes from archive ");
            out.print(filename);
            out.println('.');
            new ZipInputStream(inp);
            ZipInputStream zin = zipInputStream;
            while (true) {
                ZipEntry nextEntry = zin.getNextEntry();
                ZipEntry zent = nextEntry;
                if (nextEntry != null) {
                    String name = zent.getName();
                    if (zent.isDirectory()) {
                        out.print("Archive directory: ");
                        out.print(name);
                        out.println('.');
                    } else {
                        out.println();
                        if (readMagic(zin) == -889275714) {
                            out.print("Reading class member: ");
                            out.print(name);
                            out.println('.');
                            Object obj4 = obj;
                            new dump(zin, out);
                        } else {
                            out.print("Skipping non-class member: ");
                            out.print(name);
                            out.println('.');
                        }
                    }
                } else {
                    System.exit(-1);
                    return;
                }
            }
        } else {
            PrintStream printStream = System.err;
            new StringBuilder();
            printStream.println(sb.append("File ").append(filename).append(" is not a valid .class file").toString());
            System.exit(-1);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0176, code lost:
        r13 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0177, code lost:
        r8 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:?, code lost:
        java.lang.System.err.print("File for URL ");
        java.lang.System.err.print(r4);
        java.lang.System.err.println(" not found.");
        java.lang.System.exit(-1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0192, code lost:
        r6 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x01b3, code lost:
        r13 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x01b4, code lost:
        r8 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:?, code lost:
        java.lang.System.err.print("Error opening zip archive ");
        java.lang.System.err.print(r4);
        java.lang.System.err.println(" not found.");
        r8.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x01d4, code lost:
        if (r8.getCause() != null) goto L_0x01d6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x01d6, code lost:
        r8.getCause().printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x01de, code lost:
        java.lang.System.exit(-1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x01e2, code lost:
        r6 = null;
     */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0176 A[ExcHandler: FileNotFoundException (r13v72 'e' java.io.FileNotFoundException A[CUSTOM_DECLARE]), Splitter:B:29:0x00d2] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x01b3 A[ExcHandler: ZipException (r13v61 'e' java.util.zip.ZipException A[CUSTOM_DECLARE]), Splitter:B:51:0x0160] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void main(java.lang.String[] r19) {
        /*
            r0 = r19
            r13 = r0
            int r13 = r13.length
            r1 = r13
            gnu.bytecode.ClassTypeWriter r13 = new gnu.bytecode.ClassTypeWriter
            r18 = r13
            r13 = r18
            r14 = r18
            r15 = 0
            java.io.PrintStream r16 = java.lang.System.out
            r17 = 0
            r14.<init>((gnu.bytecode.ClassType) r15, (java.io.OutputStream) r16, (int) r17)
            r2 = r13
            r13 = r1
            if (r13 != 0) goto L_0x001e
            java.io.PrintStream r13 = java.lang.System.err
            usage(r13)
        L_0x001e:
            r13 = 0
            r3 = r13
        L_0x0020:
            r13 = r3
            r14 = r1
            if (r13 >= r14) goto L_0x02a9
            r13 = r0
            r14 = r3
            r13 = r13[r14]
            r4 = r13
            r13 = r4
            java.lang.String r14 = "-verbose"
            boolean r13 = r13.equals(r14)
            if (r13 != 0) goto L_0x003d
            r13 = r4
            java.lang.String r14 = "--verbose"
            boolean r13 = r13.equals(r14)
            if (r13 == 0) goto L_0x0046
        L_0x003d:
            r13 = r2
            r14 = 15
            r13.setFlags(r14)
        L_0x0043:
            int r3 = r3 + 1
            goto L_0x0020
        L_0x0046:
            r13 = r4
            boolean r13 = uriSchemeSpecified(r13)
            r5 = r13
            r13 = r5
            if (r13 == 0) goto L_0x01e6
            r13 = r4
            java.lang.String r14 = "jar:"
            boolean r13 = r13.startsWith(r14)     // Catch:{ IOException -> 0x028f }
            r7 = r13
            r13 = r7
            if (r13 == 0) goto L_0x00d2
            r13 = r4
            r14 = 4
            java.lang.String r13 = r13.substring(r14)     // Catch:{ IOException -> 0x028f }
            r8 = r13
            r13 = r8
            boolean r13 = uriSchemeSpecified(r13)     // Catch:{ IOException -> 0x028f }
            if (r13 != 0) goto L_0x00bb
            r13 = r8
            r14 = 33
            int r13 = r13.indexOf(r14)     // Catch:{ IOException -> 0x028f }
            r9 = r13
            r13 = r9
            if (r13 < 0) goto L_0x00bb
            r13 = r8
            r14 = 0
            r15 = r9
            java.lang.String r13 = r13.substring(r14, r15)     // Catch:{ IOException -> 0x028f }
            r10 = r13
            java.io.File r13 = new java.io.File     // Catch:{ IOException -> 0x028f }
            r18 = r13
            r13 = r18
            r14 = r18
            r15 = r10
            r14.<init>(r15)     // Catch:{ IOException -> 0x028f }
            java.net.URI r13 = r13.toURI()     // Catch:{ IOException -> 0x028f }
            java.net.URL r13 = r13.toURL()     // Catch:{ IOException -> 0x028f }
            java.lang.String r13 = r13.toString()     // Catch:{ IOException -> 0x028f }
            r10 = r13
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x028f }
            r18 = r13
            r13 = r18
            r14 = r18
            r14.<init>()     // Catch:{ IOException -> 0x028f }
            java.lang.String r14 = "jar:"
            java.lang.StringBuilder r13 = r13.append(r14)     // Catch:{ IOException -> 0x028f }
            r14 = r10
            java.lang.StringBuilder r13 = r13.append(r14)     // Catch:{ IOException -> 0x028f }
            r14 = r8
            r15 = r9
            java.lang.String r14 = r14.substring(r15)     // Catch:{ IOException -> 0x028f }
            java.lang.StringBuilder r13 = r13.append(r14)     // Catch:{ IOException -> 0x028f }
            java.lang.String r13 = r13.toString()     // Catch:{ IOException -> 0x028f }
            r4 = r13
        L_0x00bb:
            r13 = r8
            java.lang.String r14 = "!/"
            int r13 = r13.indexOf(r14)     // Catch:{ IOException -> 0x028f }
            if (r13 >= 0) goto L_0x00d2
            r13 = r4
            r14 = 33
            int r13 = r13.lastIndexOf(r14)     // Catch:{ IOException -> 0x028f }
            r9 = r13
            r13 = r9
            if (r13 > 0) goto L_0x00f1
            r13 = 0
            r7 = r13
        L_0x00d2:
            java.net.URL r13 = new java.net.URL     // Catch:{ FileNotFoundException -> 0x0176, ZipException -> 0x01b3 }
            r18 = r13
            r13 = r18
            r14 = r18
            r15 = r4
            r14.<init>(r15)     // Catch:{ FileNotFoundException -> 0x0176, ZipException -> 0x01b3 }
            r8 = r13
            r13 = r8
            java.net.URLConnection r13 = r13.openConnection()     // Catch:{ ZipException -> 0x0142, FileNotFoundException -> 0x0176 }
            java.io.InputStream r13 = r13.getInputStream()     // Catch:{ ZipException -> 0x0142, FileNotFoundException -> 0x0176 }
            r6 = r13
        L_0x00e9:
            r13 = r6
            r14 = r4
            r15 = r2
            process(r13, r14, r15)     // Catch:{ IOException -> 0x028f }
            goto L_0x0043
        L_0x00f1:
            r13 = r4
            r14 = 47
            r15 = r9
            int r13 = r13.indexOf(r14, r15)     // Catch:{ IOException -> 0x028f }
            if (r13 >= 0) goto L_0x00d2
            r13 = r4
            r14 = r9
            r15 = 1
            int r14 = r14 + 1
            java.lang.String r13 = r13.substring(r14)     // Catch:{ IOException -> 0x028f }
            r8 = r13
            r13 = r8
            r14 = 46
            r15 = 47
            java.lang.String r13 = r13.replace(r14, r15)     // Catch:{ IOException -> 0x028f }
            r8 = r13
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x028f }
            r18 = r13
            r13 = r18
            r14 = r18
            r14.<init>()     // Catch:{ IOException -> 0x028f }
            r14 = r4
            r15 = 0
            r16 = r9
            r17 = 1
            int r16 = r16 + 1
            java.lang.String r14 = r14.substring(r15, r16)     // Catch:{ IOException -> 0x028f }
            java.lang.StringBuilder r13 = r13.append(r14)     // Catch:{ IOException -> 0x028f }
            r14 = 47
            java.lang.StringBuilder r13 = r13.append(r14)     // Catch:{ IOException -> 0x028f }
            r14 = r8
            java.lang.StringBuilder r13 = r13.append(r14)     // Catch:{ IOException -> 0x028f }
            java.lang.String r14 = ".class"
            java.lang.StringBuilder r13 = r13.append(r14)     // Catch:{ IOException -> 0x028f }
            java.lang.String r13 = r13.toString()     // Catch:{ IOException -> 0x028f }
            r4 = r13
            goto L_0x00d2
        L_0x0142:
            r13 = move-exception
            r9 = r13
            r13 = r7
            if (r13 == 0) goto L_0x0174
            r13 = r8
            java.lang.String r13 = r13.getFile()     // Catch:{ FileNotFoundException -> 0x0176, ZipException -> 0x01b3 }
            r10 = r13
            r13 = r10
            r14 = 33
            int r13 = r13.lastIndexOf(r14)     // Catch:{ FileNotFoundException -> 0x0176, ZipException -> 0x01b3 }
            r11 = r13
            r13 = r11
            if (r13 <= 0) goto L_0x0160
            r13 = r10
            r14 = 0
            r15 = r11
            java.lang.String r13 = r13.substring(r14, r15)     // Catch:{ FileNotFoundException -> 0x0176, ZipException -> 0x01b3 }
            r10 = r13
        L_0x0160:
            java.net.URL r13 = new java.net.URL     // Catch:{ FileNotFoundException -> 0x0196, ZipException -> 0x01b3 }
            r18 = r13
            r13 = r18
            r14 = r18
            r15 = r10
            r14.<init>(r15)     // Catch:{ FileNotFoundException -> 0x0196, ZipException -> 0x01b3 }
            java.net.URLConnection r13 = r13.openConnection()     // Catch:{ FileNotFoundException -> 0x0196, ZipException -> 0x01b3 }
            java.io.InputStream r13 = r13.getInputStream()     // Catch:{ FileNotFoundException -> 0x0196, ZipException -> 0x01b3 }
        L_0x0174:
            r13 = r9
            throw r13     // Catch:{ FileNotFoundException -> 0x0176, ZipException -> 0x01b3 }
        L_0x0176:
            r13 = move-exception
            r8 = r13
            java.io.PrintStream r13 = java.lang.System.err     // Catch:{ IOException -> 0x028f }
            java.lang.String r14 = "File for URL "
            r13.print(r14)     // Catch:{ IOException -> 0x028f }
            java.io.PrintStream r13 = java.lang.System.err     // Catch:{ IOException -> 0x028f }
            r14 = r4
            r13.print(r14)     // Catch:{ IOException -> 0x028f }
            java.io.PrintStream r13 = java.lang.System.err     // Catch:{ IOException -> 0x028f }
            java.lang.String r14 = " not found."
            r13.println(r14)     // Catch:{ IOException -> 0x028f }
            r13 = -1
            java.lang.System.exit(r13)     // Catch:{ IOException -> 0x028f }
            r13 = 0
            r6 = r13
            goto L_0x00e9
        L_0x0196:
            r13 = move-exception
            r12 = r13
            java.io.PrintStream r13 = java.lang.System.err     // Catch:{ FileNotFoundException -> 0x0176, ZipException -> 0x01b3 }
            java.lang.String r14 = "Jar File for URL "
            r13.print(r14)     // Catch:{ FileNotFoundException -> 0x0176, ZipException -> 0x01b3 }
            java.io.PrintStream r13 = java.lang.System.err     // Catch:{ FileNotFoundException -> 0x0176, ZipException -> 0x01b3 }
            r14 = r10
            r13.print(r14)     // Catch:{ FileNotFoundException -> 0x0176, ZipException -> 0x01b3 }
            java.io.PrintStream r13 = java.lang.System.err     // Catch:{ FileNotFoundException -> 0x0176, ZipException -> 0x01b3 }
            java.lang.String r14 = " not found."
            r13.println(r14)     // Catch:{ FileNotFoundException -> 0x0176, ZipException -> 0x01b3 }
            r13 = -1
            java.lang.System.exit(r13)     // Catch:{ FileNotFoundException -> 0x0176, ZipException -> 0x01b3 }
            goto L_0x0174
        L_0x01b3:
            r13 = move-exception
            r8 = r13
            java.io.PrintStream r13 = java.lang.System.err     // Catch:{ IOException -> 0x028f }
            java.lang.String r14 = "Error opening zip archive "
            r13.print(r14)     // Catch:{ IOException -> 0x028f }
            java.io.PrintStream r13 = java.lang.System.err     // Catch:{ IOException -> 0x028f }
            r14 = r4
            r13.print(r14)     // Catch:{ IOException -> 0x028f }
            java.io.PrintStream r13 = java.lang.System.err     // Catch:{ IOException -> 0x028f }
            java.lang.String r14 = " not found."
            r13.println(r14)     // Catch:{ IOException -> 0x028f }
            r13 = r8
            r13.printStackTrace()     // Catch:{ IOException -> 0x028f }
            r13 = r8
            java.lang.Throwable r13 = r13.getCause()     // Catch:{ IOException -> 0x028f }
            if (r13 == 0) goto L_0x01de
            r13 = r8
            java.lang.Throwable r13 = r13.getCause()     // Catch:{ IOException -> 0x028f }
            r13.printStackTrace()     // Catch:{ IOException -> 0x028f }
        L_0x01de:
            r13 = -1
            java.lang.System.exit(r13)     // Catch:{ IOException -> 0x028f }
            r13 = 0
            r6 = r13
            goto L_0x00e9
        L_0x01e6:
            java.io.FileInputStream r13 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x01f5 }
            r18 = r13
            r13 = r18
            r14 = r18
            r15 = r4
            r14.<init>(r15)     // Catch:{ FileNotFoundException -> 0x01f5 }
            r6 = r13
            goto L_0x00e9
        L_0x01f5:
            r13 = move-exception
            r7 = r13
            r13 = r4
            java.lang.Class r13 = gnu.bytecode.ObjectType.getContextClass(r13)     // Catch:{ NoClassDefFoundError -> 0x0240, Throwable -> 0x0248 }
            r9 = r13
            r13 = r9
            java.lang.ClassLoader r13 = r13.getClassLoader()     // Catch:{ NoClassDefFoundError -> 0x0240, Throwable -> 0x0248 }
            r8 = r13
        L_0x0203:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x028f }
            r18 = r13
            r13 = r18
            r14 = r18
            r14.<init>()     // Catch:{ IOException -> 0x028f }
            r14 = r4
            r15 = 46
            r16 = 47
            java.lang.String r14 = r14.replace(r15, r16)     // Catch:{ IOException -> 0x028f }
            java.lang.StringBuilder r13 = r13.append(r14)     // Catch:{ IOException -> 0x028f }
            java.lang.String r14 = ".class"
            java.lang.StringBuilder r13 = r13.append(r14)     // Catch:{ IOException -> 0x028f }
            java.lang.String r13 = r13.toString()     // Catch:{ IOException -> 0x028f }
            r9 = r13
            r13 = r8
            r14 = r9
            java.net.URL r13 = r13.getResource(r14)     // Catch:{ Throwable -> 0x0269 }
            r10 = r13
            r13 = r10
            java.net.URLConnection r13 = r13.openConnection()     // Catch:{ Throwable -> 0x0269 }
            java.io.InputStream r13 = r13.getInputStream()     // Catch:{ Throwable -> 0x0269 }
            r6 = r13
            r13 = r10
            java.lang.String r13 = r13.toString()     // Catch:{ Throwable -> 0x0269 }
            r4 = r13
            goto L_0x00e9
        L_0x0240:
            r13 = move-exception
            r9 = r13
            java.lang.ClassLoader r13 = gnu.bytecode.ObjectType.getContextClassLoader()     // Catch:{ IOException -> 0x028f }
            r8 = r13
            goto L_0x0203
        L_0x0248:
            r13 = move-exception
            r9 = r13
            java.io.PrintStream r13 = java.lang.System.err     // Catch:{ IOException -> 0x028f }
            java.lang.String r14 = "File "
            r13.print(r14)     // Catch:{ IOException -> 0x028f }
            java.io.PrintStream r13 = java.lang.System.err     // Catch:{ IOException -> 0x028f }
            r14 = r4
            r13.print(r14)     // Catch:{ IOException -> 0x028f }
            java.io.PrintStream r13 = java.lang.System.err     // Catch:{ IOException -> 0x028f }
            java.lang.String r14 = " not found."
            r13.println(r14)     // Catch:{ IOException -> 0x028f }
            r13 = -1
            java.lang.System.exit(r13)     // Catch:{ IOException -> 0x028f }
            r13 = 0
            r8 = r13
            r13 = 0
            r6 = r13
            goto L_0x0203
        L_0x0269:
            r13 = move-exception
            r10 = r13
            java.io.PrintStream r13 = java.lang.System.err     // Catch:{ IOException -> 0x028f }
            java.lang.String r14 = "Can't find .class file for class "
            r13.print(r14)     // Catch:{ IOException -> 0x028f }
            java.io.PrintStream r13 = java.lang.System.err     // Catch:{ IOException -> 0x028f }
            r14 = r4
            r13.print(r14)     // Catch:{ IOException -> 0x028f }
            java.io.PrintStream r13 = java.lang.System.err     // Catch:{ IOException -> 0x028f }
            java.lang.String r14 = " - "
            r13.print(r14)     // Catch:{ IOException -> 0x028f }
            java.io.PrintStream r13 = java.lang.System.err     // Catch:{ IOException -> 0x028f }
            r14 = r10
            r13.println(r14)     // Catch:{ IOException -> 0x028f }
            r13 = -1
            java.lang.System.exit(r13)     // Catch:{ IOException -> 0x028f }
            r13 = 0
            r6 = r13
            goto L_0x00e9
        L_0x028f:
            r13 = move-exception
            r7 = r13
            r13 = r7
            r13.printStackTrace()
            java.io.PrintStream r13 = java.lang.System.err
            java.lang.String r14 = "caught "
            r13.println(r14)
            java.io.PrintStream r13 = java.lang.System.err
            r14 = r7
            r13.print(r14)
            r13 = -1
            java.lang.System.exit(r13)
            goto L_0x0043
        L_0x02a9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.bytecode.dump.main(java.lang.String[]):void");
    }

    static int uriSchemeLength(String str) {
        String uri = str;
        int len = uri.length();
        int i = 0;
        while (i < len) {
            char ch = uri.charAt(i);
            if (ch == ':') {
                return i;
            }
            if (i != 0) {
                if (!(Character.isLetterOrDigit(ch) || ch == '+' || ch == '-' || ch == '.')) {
                }
                i++;
            } else if (Character.isLetter(ch)) {
                i++;
            }
            return -1;
        }
        return -1;
    }

    static boolean uriSchemeSpecified(String str) {
        String name = str;
        int ulen = uriSchemeLength(name);
        if (ulen == 1 && File.separatorChar == '\\') {
            char drive = name.charAt(0);
            return (drive < 'a' || drive > 'z') && (drive < 'A' || drive > 'Z');
        }
        return ulen > 0;
    }

    public static void usage(PrintStream printStream) {
        PrintStream err = printStream;
        err.println("Prints and dis-assembles the contents of JVM .class files.");
        err.println("Usage: [--verbose] class-or-jar ...");
        err.println("where a class-or-jar can be one of:");
        err.println("- a fully-qualified class name; or");
        err.println("- the name of a .class file, or a URL reference to one; or");
        err.println("- the name of a .jar or .zip archive file, or a URL reference to one.");
        err.println("If a .jar/.zip archive is named, all its.class file members are printed.");
        err.println();
        err.println("You can name a single .class member of an archive with a jar: URL,");
        err.println("which looks like: jar:jar-spec!/p1/p2/cl.class");
        err.println("The jar-spec can be a URL or the name of the .jar file.");
        err.println("You can also use the shorthand syntax: jar:jar-spec!p1.p2.cl");
        System.exit(-1);
    }
}

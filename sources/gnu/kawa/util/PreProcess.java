package gnu.kawa.util;

import com.google.appinventor.components.runtime.util.Ev3Constants;
import com.shaded.fasterxml.jackson.core.util.BufferRecycler;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Hashtable;
import java.util.StringTokenizer;

public class PreProcess {
    static final String JAVA4_FEATURES = "+JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio";
    static final String JAVA5_FEATURES = "+JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio +use:org.w3c.dom.Node +use:javax.xml.transform +JAXP-1.3 -JAXP-QName";
    static final String NO_JAVA4_FEATURES = "-JAVA5 -use:java.util.IdentityHashMap -use:java.lang.CharSequence -use:java.lang.Throwable.getCause -use:java.net.URI -use:java.util.regex -use:org.w3c.dom.Node -JAXP-1.3 -use:javax.xml.transform -JAVA5 -JAVA6 -JAVA6COMPAT5 -JAXP-QName -use:java.text.Normalizer -SAX2 -use:java.nio -Android";
    static final String NO_JAVA6_FEATURES = "-JAVA6 -JAVA7 -use:java.dyn -use:java.text.Normalizer";
    static String[] version_features;
    String filename;
    Hashtable keywords;
    int lineno;
    byte[] resultBuffer;
    int resultLength;

    public PreProcess() {
        Hashtable hashtable;
        new Hashtable();
        this.keywords = hashtable;
    }

    static {
        String[] strArr = new String[18];
        strArr[0] = "java1";
        String[] strArr2 = strArr;
        strArr2[1] = "-JAVA2 -JAVA5 -use:java.util.IdentityHashMap -use:java.lang.CharSequence -use:java.lang.Throwable.getCause -use:java.net.URI -use:java.util.regex -use:org.w3c.dom.Node -JAXP-1.3 -use:javax.xml.transform -JAVA5 -JAVA6 -JAVA6COMPAT5 -JAXP-QName -use:java.text.Normalizer -SAX2 -use:java.nio -Android -JAVA6 -JAVA7 -use:java.dyn -use:java.text.Normalizer";
        String[] strArr3 = strArr2;
        strArr3[2] = "java2";
        String[] strArr4 = strArr3;
        strArr4[3] = "+JAVA2 -JAVA5 -use:java.util.IdentityHashMap -use:java.lang.CharSequence -use:java.lang.Throwable.getCause -use:java.net.URI -use:java.util.regex -use:org.w3c.dom.Node -JAXP-1.3 -use:javax.xml.transform -JAVA5 -JAVA6 -JAVA6COMPAT5 -JAXP-QName -use:java.text.Normalizer -SAX2 -use:java.nio -Android -JAVA6 -JAVA7 -use:java.dyn -use:java.text.Normalizer";
        String[] strArr5 = strArr4;
        strArr5[4] = "java4";
        String[] strArr6 = strArr5;
        strArr6[5] = "-JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio -use:org.w3c.dom.Node -JAXP-1.3 -use:javax.xml.transform -JAXP-QName -JAVA6COMPAT5 -Android -JAVA6 -JAVA7 -use:java.dyn -use:java.text.Normalizer";
        String[] strArr7 = strArr6;
        strArr7[6] = "java4x";
        String[] strArr8 = strArr7;
        strArr8[7] = "-JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio +use:org.w3c.dom.Node +JAXP-1.3 +use:javax.xml.transform -JAXP-QName -JAVA6COMPAT5 -Android -JAVA6 -JAVA7 -use:java.dyn -use:java.text.Normalizer";
        String[] strArr9 = strArr8;
        strArr9[8] = "java5";
        String[] strArr10 = strArr9;
        strArr10[9] = "+JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio +use:org.w3c.dom.Node +use:javax.xml.transform +JAXP-1.3 -JAXP-QName -JAVA6COMPAT5 -Android -JAVA6 -JAVA7 -use:java.dyn -use:java.text.Normalizer";
        String[] strArr11 = strArr10;
        strArr11[10] = "java6compat5";
        String[] strArr12 = strArr11;
        strArr12[11] = "+JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio +use:org.w3c.dom.Node +use:javax.xml.transform +JAXP-1.3 -JAXP-QName -JAVA6 -JAVA7 +JAVA6COMPAT5 +use:java.text.Normalizer -use:java.dyn -Android";
        String[] strArr13 = strArr12;
        strArr13[12] = "java6";
        String[] strArr14 = strArr13;
        strArr14[13] = "+JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio +use:org.w3c.dom.Node +use:javax.xml.transform +JAXP-1.3 -JAXP-QName +JAVA6 -JAVA7 -JAVA6COMPAT5 +use:java.text.Normalizer -use:java.dyn -Android";
        String[] strArr15 = strArr14;
        strArr15[14] = "java7";
        String[] strArr16 = strArr15;
        strArr16[15] = "+JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio +use:org.w3c.dom.Node +use:javax.xml.transform +JAXP-1.3 -JAXP-QName +JAVA6 +JAVA7 -JAVA6COMPAT5 +use:java.text.Normalizer +use:java.dyn -Android";
        String[] strArr17 = strArr16;
        strArr17[16] = "android";
        String[] strArr18 = strArr17;
        strArr18[17] = "+JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio +use:org.w3c.dom.Node +JAXP-1.3 -JAXP-QName -use:javax.xml.transform -JAVA6 -JAVA6COMPAT5 +Android -JAVA6 -JAVA7 -use:java.dyn -use:java.text.Normalizer";
        version_features = strArr18;
    }

    /* access modifiers changed from: package-private */
    public void error(String msg) {
        StringBuilder sb;
        PrintStream printStream = System.err;
        new StringBuilder();
        printStream.println(sb.append(this.filename).append(':').append(this.lineno).append(": ").append(msg).toString());
        System.exit(-1);
    }

    public void filter(String str) throws Throwable {
        BufferedInputStream bufferedInputStream;
        InputStream inputStream;
        FileOutputStream fileOutputStream;
        StringBuilder sb;
        String filename2 = str;
        new FileInputStream(filename2);
        new BufferedInputStream(inputStream);
        if (filter(filename2, bufferedInputStream)) {
            new FileOutputStream(filename2);
            FileOutputStream out = fileOutputStream;
            out.write(this.resultBuffer, 0, this.resultLength);
            out.close();
            PrintStream printStream = System.err;
            new StringBuilder();
            printStream.println(sb.append("Pre-processed ").append(filename2).toString());
        }
    }

    public boolean filter(String str, BufferedInputStream bufferedInputStream) throws Throwable {
        String str2;
        String rest;
        Object obj;
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        boolean doComment;
        int d;
        StringBuilder sb5;
        String filename2 = str;
        BufferedInputStream in = bufferedInputStream;
        this.filename = filename2;
        boolean changed = false;
        byte[] buf = new byte[BufferRecycler.DEFAULT_WRITE_CONCAT_BUFFER_LEN];
        int len = 0;
        int lineStart = 0;
        int dataStart = -1;
        int cmdLine = 0;
        this.lineno = 1;
        int commentAt = -1;
        int curIndent = 0;
        int nesting = 0;
        int skipNesting = 0;
        String cmd = null;
        int changedLine = 0;
        while (true) {
            int c = in.read();
            if (c < 0) {
                break;
            }
            if (len + 10 >= buf.length) {
                byte[] nbuf = new byte[(2 * len)];
                System.arraycopy(buf, 0, nbuf, 0, len);
                buf = nbuf;
            }
            if (c == 10 && len > 0 && buf[len - 1] == 13) {
                int i = len;
                len++;
                buf[i] = (byte) c;
            } else {
                if (commentAt >= 0 && dataStart < 0 && changedLine <= 0 && c != 13 && c != 10 && (commentAt == curIndent || !(c == 32 || c == 9))) {
                    if (c == 47) {
                        in.mark(100);
                        int d2 = in.read();
                        if (d2 == 47) {
                            doComment = false;
                        } else if (d2 == 42) {
                            while (true) {
                                d = in.read();
                                if (d != 32 && d != 9) {
                                    break;
                                }
                            }
                            doComment = d != 35;
                        } else {
                            doComment = true;
                        }
                        in.reset();
                    } else {
                        doComment = true;
                    }
                    if (doComment) {
                        int i2 = len;
                        int len2 = len + 1;
                        buf[i2] = Ev3Constants.Opcode.INIT_BYTES;
                        int i3 = len2;
                        int len3 = len2 + 1;
                        buf[i3] = Ev3Constants.Opcode.INIT_BYTES;
                        int i4 = len3;
                        len = len3 + 1;
                        buf[i4] = 32;
                        changedLine = 1;
                        changed = true;
                    }
                }
                if (!(c == 32 || c == 9 || dataStart >= 0)) {
                    dataStart = len;
                    if (nesting > 0 && commentAt != curIndent && c == 47) {
                        c = in.read();
                        if (c < 0) {
                            break;
                        } else if (c != 47) {
                            int i5 = len;
                            len++;
                            buf[i5] = Ev3Constants.Opcode.INIT_BYTES;
                        } else {
                            c = in.read();
                            if (c < 0) {
                                break;
                            }
                            changedLine = -1;
                            changed = true;
                            if (c == 32) {
                                c = in.read();
                                if (c == 32 || c == 9) {
                                    dataStart = -1;
                                }
                            }
                        }
                    }
                }
                buf[len] = (byte) c;
                len++;
                if (c == 13 || c == 10) {
                    int firstNonSpace = -1;
                    int lastNonSpace = 0;
                    for (int i6 = lineStart; i6 < len - 1; i6++) {
                        if (!(buf[i6] == 32 || buf[i6] == 9)) {
                            lastNonSpace = i6;
                            if (firstNonSpace < 0) {
                                firstNonSpace = i6;
                            }
                        }
                    }
                    if (lastNonSpace - firstNonSpace >= 4 && buf[firstNonSpace] == 47 && buf[firstNonSpace + 1] == 42 && buf[lastNonSpace - 1] == 42 && buf[lastNonSpace] == 47) {
                        int firstNonSpace2 = firstNonSpace + 2;
                        while (firstNonSpace2 < lastNonSpace && buf[firstNonSpace2] == 32) {
                            firstNonSpace2++;
                        }
                        int lastNonSpace2 = lastNonSpace - 2;
                        while (lastNonSpace2 > firstNonSpace2 && buf[lastNonSpace2] == 32) {
                            lastNonSpace2--;
                        }
                        if (buf[firstNonSpace2] == 35) {
                            new String(buf, firstNonSpace2, (lastNonSpace2 - firstNonSpace2) + 1, "ISO-8859-1");
                            String cmnt = str2;
                            int sp = cmnt.indexOf(32);
                            cmdLine = this.lineno;
                            if (sp > 0) {
                                cmd = cmnt.substring(0, sp);
                                rest = cmnt.substring(sp).trim();
                                obj = this.keywords.get(rest);
                            } else {
                                cmd = cmnt;
                                rest = "";
                                obj = null;
                            }
                            if ("#ifdef".equals(cmd) || "#ifndef".equals(cmd)) {
                                if (obj == null) {
                                    PrintStream printStream = System.err;
                                    new StringBuilder();
                                    printStream.println(sb.append(filename2).append(":").append(this.lineno).append(": warning - undefined keyword: ").append(rest).toString());
                                    obj = Boolean.FALSE;
                                }
                                nesting++;
                                if (skipNesting > 0) {
                                    commentAt = curIndent;
                                } else {
                                    if ((cmd.charAt(3) == 'n') != (obj == Boolean.FALSE)) {
                                        commentAt = curIndent;
                                        skipNesting = nesting;
                                    }
                                }
                            } else if ("#else".equals(cmd)) {
                                if (nesting == 0) {
                                    new StringBuilder();
                                    error(sb4.append("unexpected ").append(cmd).toString());
                                } else if (nesting == skipNesting) {
                                    commentAt = -1;
                                    skipNesting = 0;
                                } else {
                                    commentAt = curIndent;
                                    if (skipNesting == 0) {
                                        skipNesting = nesting;
                                    }
                                }
                            } else if ("#endif".equals(cmd)) {
                                if (nesting == 0) {
                                    new StringBuilder();
                                    error(sb3.append("unexpected ").append(cmd).toString());
                                }
                                if (nesting == skipNesting) {
                                    skipNesting = 0;
                                    commentAt = -1;
                                } else if (skipNesting > 0) {
                                    commentAt = curIndent;
                                }
                                nesting--;
                            } else {
                                new StringBuilder();
                                error(sb2.append("unknown command: ").append(cmnt).toString());
                            }
                        }
                    }
                    lineStart = len;
                    dataStart = -1;
                    curIndent = 0;
                    this.lineno++;
                    changedLine = 0;
                } else if (dataStart < 0) {
                    curIndent = c == 9 ? (curIndent + 8) & -8 : curIndent + 1;
                }
            }
        }
        if (nesting != 0) {
            this.lineno = cmdLine;
            new StringBuilder();
            error(sb5.append("unterminated ").append(cmd).toString());
        }
        this.resultBuffer = buf;
        this.resultLength = len;
        return changed;
    }

    /* access modifiers changed from: package-private */
    public void handleArg(String str) {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        StringTokenizer stringTokenizer;
        StringBuilder sb4;
        String arg = str;
        if (arg.charAt(0) == '%') {
            String arg2 = arg.substring(1);
            int i = 0;
            while (true) {
                if (i >= version_features.length) {
                    PrintStream printStream = System.err;
                    new StringBuilder();
                    printStream.println(sb4.append("Unknown version: ").append(arg2).toString());
                    System.exit(-1);
                }
                if (arg2.equals(version_features[i])) {
                    break;
                }
                i += 2;
            }
            String features = version_features[i + 1];
            PrintStream printStream2 = System.err;
            new StringBuilder();
            printStream2.println(sb3.append("(variant ").append(arg2).append(" maps to: ").append(features).append(")").toString());
            new StringTokenizer(features);
            StringTokenizer tokenizer = stringTokenizer;
            while (tokenizer.hasMoreTokens()) {
                handleArg(tokenizer.nextToken());
            }
        } else if (arg.charAt(0) == '+') {
            Object put = this.keywords.put(arg.substring(1), Boolean.TRUE);
        } else if (arg.charAt(0) == '-') {
            int eq = arg.indexOf(61);
            if (eq > 1) {
                String keyword = arg.substring(arg.charAt(1) == '-' ? 2 : 1, eq);
                String value = arg.substring(eq + 1);
                Boolean b = Boolean.FALSE;
                if (value.equalsIgnoreCase("true")) {
                    b = Boolean.TRUE;
                } else if (!value.equalsIgnoreCase("false")) {
                    PrintStream printStream3 = System.err;
                    new StringBuilder();
                    printStream3.println(sb2.append("invalid value ").append(value).append(" for ").append(keyword).toString());
                    System.exit(-1);
                }
                Object put2 = this.keywords.put(keyword, b);
                return;
            }
            Object put3 = this.keywords.put(arg.substring(1), Boolean.FALSE);
        } else {
            try {
                filter(arg);
            } catch (Throwable th) {
                Throwable ex = th;
                PrintStream printStream4 = System.err;
                new StringBuilder();
                printStream4.println(sb.append("caught ").append(ex).toString());
                ex.printStackTrace();
                System.exit(-1);
            }
        }
    }

    public static void main(String[] strArr) {
        PreProcess preProcess;
        String[] args = strArr;
        new PreProcess();
        PreProcess pp = preProcess;
        Object put = pp.keywords.put("true", Boolean.TRUE);
        Object put2 = pp.keywords.put("false", Boolean.FALSE);
        for (int i = 0; i < args.length; i++) {
            pp.handleArg(args[i]);
        }
    }
}

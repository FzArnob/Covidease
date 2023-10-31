package kawa;

import gnu.bytecode.ArrayClassLoader;
import gnu.bytecode.ZipLoader;
import gnu.expr.Compilation;
import gnu.expr.CompiledModule;
import gnu.expr.Language;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleExp;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleManager;
import gnu.lists.AbstractFormat;
import gnu.lists.Consumer;
import gnu.lists.VoidConsumer;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.InPort;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.TtyInPort;
import gnu.mapping.Values;
import gnu.mapping.WrappedException;
import gnu.mapping.WrongArguments;
import gnu.text.FilePath;
import gnu.text.Lexer;
import gnu.text.Path;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.net.URL;

public class Shell {
    private static Class[] boolClasses = {Boolean.TYPE};
    public static ThreadLocal currentLoadPath;
    public static Object[] defaultFormatInfo;
    public static Method defaultFormatMethod;
    public static String defaultFormatName;
    static Object[][] formats;
    private static Class[] httpPrinterClasses = {OutPort.class};
    private static Class[] noClasses = new Class[0];
    private static Object portArg = "(port)";
    private static Class[] xmlPrinterClasses;

    public Shell() {
    }

    static {
        ThreadLocal threadLocal;
        new ThreadLocal();
        currentLoadPath = threadLocal;
        Class[] clsArr = new Class[2];
        clsArr[0] = OutPort.class;
        Class[] clsArr2 = clsArr;
        clsArr2[1] = Object.class;
        xmlPrinterClasses = clsArr2;
        Object[][] objArr = new Object[14][];
        Object[][] objArr2 = objArr;
        Object[][] objArr3 = objArr;
        Object[] objArr4 = new Object[5];
        objArr4[0] = "scheme";
        Object[] objArr5 = objArr4;
        objArr5[1] = "gnu.kawa.functions.DisplayFormat";
        Object[] objArr6 = objArr5;
        objArr6[2] = "getSchemeFormat";
        Object[] objArr7 = objArr6;
        objArr7[3] = boolClasses;
        Object[] objArr8 = objArr7;
        objArr8[4] = Boolean.FALSE;
        objArr3[0] = objArr8;
        Object[][] objArr9 = objArr2;
        Object[][] objArr10 = objArr9;
        Object[][] objArr11 = objArr9;
        Object[] objArr12 = new Object[5];
        objArr12[0] = "readable-scheme";
        Object[] objArr13 = objArr12;
        objArr13[1] = "gnu.kawa.functions.DisplayFormat";
        Object[] objArr14 = objArr13;
        objArr14[2] = "getSchemeFormat";
        Object[] objArr15 = objArr14;
        objArr15[3] = boolClasses;
        Object[] objArr16 = objArr15;
        objArr16[4] = Boolean.TRUE;
        objArr11[1] = objArr16;
        Object[][] objArr17 = objArr10;
        Object[][] objArr18 = objArr17;
        Object[][] objArr19 = objArr17;
        Object[] objArr20 = new Object[5];
        objArr20[0] = "elisp";
        Object[] objArr21 = objArr20;
        objArr21[1] = "gnu.kawa.functions.DisplayFormat";
        Object[] objArr22 = objArr21;
        objArr22[2] = "getEmacsLispFormat";
        Object[] objArr23 = objArr22;
        objArr23[3] = boolClasses;
        Object[] objArr24 = objArr23;
        objArr24[4] = Boolean.FALSE;
        objArr19[2] = objArr24;
        Object[][] objArr25 = objArr18;
        Object[][] objArr26 = objArr25;
        Object[][] objArr27 = objArr25;
        Object[] objArr28 = new Object[5];
        objArr28[0] = "readable-elisp";
        Object[] objArr29 = objArr28;
        objArr29[1] = "gnu.kawa.functions.DisplayFormat";
        Object[] objArr30 = objArr29;
        objArr30[2] = "getEmacsLispFormat";
        Object[] objArr31 = objArr30;
        objArr31[3] = boolClasses;
        Object[] objArr32 = objArr31;
        objArr32[4] = Boolean.TRUE;
        objArr27[3] = objArr32;
        Object[][] objArr33 = objArr26;
        Object[][] objArr34 = objArr33;
        Object[][] objArr35 = objArr33;
        Object[] objArr36 = new Object[5];
        objArr36[0] = "clisp";
        Object[] objArr37 = objArr36;
        objArr37[1] = "gnu.kawa.functions.DisplayFormat";
        Object[] objArr38 = objArr37;
        objArr38[2] = "getCommonLispFormat";
        Object[] objArr39 = objArr38;
        objArr39[3] = boolClasses;
        Object[] objArr40 = objArr39;
        objArr40[4] = Boolean.FALSE;
        objArr35[4] = objArr40;
        Object[][] objArr41 = objArr34;
        Object[][] objArr42 = objArr41;
        Object[][] objArr43 = objArr41;
        Object[] objArr44 = new Object[5];
        objArr44[0] = "readable-clisp";
        Object[] objArr45 = objArr44;
        objArr45[1] = "gnu.kawa.functions.DisplayFormat";
        Object[] objArr46 = objArr45;
        objArr46[2] = "getCommonLispFormat";
        Object[] objArr47 = objArr46;
        objArr47[3] = boolClasses;
        Object[] objArr48 = objArr47;
        objArr48[4] = Boolean.TRUE;
        objArr43[5] = objArr48;
        Object[][] objArr49 = objArr42;
        Object[][] objArr50 = objArr49;
        Object[][] objArr51 = objArr49;
        Object[] objArr52 = new Object[5];
        objArr52[0] = "commonlisp";
        Object[] objArr53 = objArr52;
        objArr53[1] = "gnu.kawa.functions.DisplayFormat";
        Object[] objArr54 = objArr53;
        objArr54[2] = "getCommonLispFormat";
        Object[] objArr55 = objArr54;
        objArr55[3] = boolClasses;
        Object[] objArr56 = objArr55;
        objArr56[4] = Boolean.FALSE;
        objArr51[6] = objArr56;
        Object[][] objArr57 = objArr50;
        Object[][] objArr58 = objArr57;
        Object[][] objArr59 = objArr57;
        Object[] objArr60 = new Object[5];
        objArr60[0] = "readable-commonlisp";
        Object[] objArr61 = objArr60;
        objArr61[1] = "gnu.kawa.functions.DisplayFormat";
        Object[] objArr62 = objArr61;
        objArr62[2] = "getCommonLispFormat";
        Object[] objArr63 = objArr62;
        objArr63[3] = boolClasses;
        Object[] objArr64 = objArr63;
        objArr64[4] = Boolean.TRUE;
        objArr59[7] = objArr64;
        Object[][] objArr65 = objArr58;
        Object[][] objArr66 = objArr65;
        Object[][] objArr67 = objArr65;
        Object[] objArr68 = new Object[6];
        objArr68[0] = "xml";
        Object[] objArr69 = objArr68;
        objArr69[1] = "gnu.xml.XMLPrinter";
        Object[] objArr70 = objArr69;
        objArr70[2] = "make";
        Object[] objArr71 = objArr70;
        objArr71[3] = xmlPrinterClasses;
        Object[] objArr72 = objArr71;
        objArr72[4] = portArg;
        Object[] objArr73 = objArr72;
        objArr73[5] = null;
        objArr67[8] = objArr73;
        Object[][] objArr74 = objArr66;
        Object[][] objArr75 = objArr74;
        Object[][] objArr76 = objArr74;
        Object[] objArr77 = new Object[6];
        objArr77[0] = "html";
        Object[] objArr78 = objArr77;
        objArr78[1] = "gnu.xml.XMLPrinter";
        Object[] objArr79 = objArr78;
        objArr79[2] = "make";
        Object[] objArr80 = objArr79;
        objArr80[3] = xmlPrinterClasses;
        Object[] objArr81 = objArr80;
        objArr81[4] = portArg;
        Object[] objArr82 = objArr81;
        objArr82[5] = "html";
        objArr76[9] = objArr82;
        Object[][] objArr83 = objArr75;
        Object[][] objArr84 = objArr83;
        Object[][] objArr85 = objArr83;
        Object[] objArr86 = new Object[6];
        objArr86[0] = "xhtml";
        Object[] objArr87 = objArr86;
        objArr87[1] = "gnu.xml.XMLPrinter";
        Object[] objArr88 = objArr87;
        objArr88[2] = "make";
        Object[] objArr89 = objArr88;
        objArr89[3] = xmlPrinterClasses;
        Object[] objArr90 = objArr89;
        objArr90[4] = portArg;
        Object[] objArr91 = objArr90;
        objArr91[5] = "xhtml";
        objArr85[10] = objArr91;
        Object[][] objArr92 = objArr84;
        Object[][] objArr93 = objArr92;
        Object[][] objArr94 = objArr92;
        Object[] objArr95 = new Object[5];
        objArr95[0] = "cgi";
        Object[] objArr96 = objArr95;
        objArr96[1] = "gnu.kawa.xml.HttpPrinter";
        Object[] objArr97 = objArr96;
        objArr97[2] = "make";
        Object[] objArr98 = objArr97;
        objArr98[3] = httpPrinterClasses;
        Object[] objArr99 = objArr98;
        objArr99[4] = portArg;
        objArr94[11] = objArr99;
        Object[][] objArr100 = objArr93;
        Object[][] objArr101 = objArr100;
        Object[][] objArr102 = objArr100;
        Object[] objArr103 = new Object[4];
        objArr103[0] = "ignore";
        Object[] objArr104 = objArr103;
        objArr104[1] = "gnu.lists.VoidConsumer";
        Object[] objArr105 = objArr104;
        objArr105[2] = "getInstance";
        Object[] objArr106 = objArr105;
        objArr106[3] = noClasses;
        objArr102[12] = objArr106;
        Object[][] objArr107 = objArr101;
        Object[][] objArr108 = objArr107;
        objArr107[13] = new Object[]{null};
        formats = objArr108;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: java.lang.Class[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v6, resolved type: java.lang.String[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v7, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v8, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v9, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v10, resolved type: java.lang.String[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v11, resolved type: java.lang.String[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v19, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v14, resolved type: java.lang.String[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v15, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v7, resolved type: java.lang.Class[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v8, resolved type: java.lang.Class[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void setDefaultFormat(java.lang.String r10) {
        /*
            r0 = r10
            r5 = r0
            java.lang.String r5 = r5.intern()
            r0 = r5
            r5 = r0
            defaultFormatName = r5
            r5 = 0
            r1 = r5
        L_0x000c:
            java.lang.Object[][] r5 = formats
            r6 = r1
            r5 = r5[r6]
            r2 = r5
            r5 = r2
            r6 = 0
            r5 = r5[r6]
            r3 = r5
            r5 = r3
            if (r5 != 0) goto L_0x0045
            java.io.PrintStream r5 = java.lang.System.err
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r9 = r6
            r6 = r9
            r7 = r9
            r7.<init>()
            java.lang.String r7 = "kawa: unknown output format '"
            java.lang.StringBuilder r6 = r6.append(r7)
            r7 = r0
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r7 = "'"
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r6 = r6.toString()
            r5.println(r6)
            r5 = -1
            java.lang.System.exit(r5)
        L_0x0042:
            int r1 = r1 + 1
            goto L_0x000c
        L_0x0045:
            r5 = r3
            r6 = r0
            if (r5 != r6) goto L_0x0042
            r5 = r2
            defaultFormatInfo = r5
            r5 = r2
            r6 = 1
            r5 = r5[r6]     // Catch:{ Throwable -> 0x007f }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ Throwable -> 0x007f }
            java.lang.Class r5 = java.lang.Class.forName(r5)     // Catch:{ Throwable -> 0x007f }
            r4 = r5
            r5 = r4
            r6 = r2
            r7 = 2
            r6 = r6[r7]     // Catch:{ Throwable -> 0x007f }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ Throwable -> 0x007f }
            r7 = r2
            r8 = 3
            r7 = r7[r8]     // Catch:{ Throwable -> 0x007f }
            java.lang.Class[] r7 = (java.lang.Class[]) r7     // Catch:{ Throwable -> 0x007f }
            java.lang.Class[] r7 = (java.lang.Class[]) r7     // Catch:{ Throwable -> 0x007f }
            java.lang.reflect.Method r5 = r5.getMethod(r6, r7)     // Catch:{ Throwable -> 0x007f }
            defaultFormatMethod = r5     // Catch:{ Throwable -> 0x007f }
        L_0x006c:
            java.lang.Object[] r5 = defaultFormatInfo
            r6 = 1
            r5 = r5[r6]
            java.lang.String r6 = "gnu.lists.VoidConsumer"
            boolean r5 = r5.equals(r6)
            if (r5 != 0) goto L_0x007e
            r5 = 1
            gnu.expr.ModuleBody.setMainPrintValues(r5)
        L_0x007e:
            return
        L_0x007f:
            r5 = move-exception
            r4 = r5
            java.io.PrintStream r5 = java.lang.System.err
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r9 = r6
            r6 = r9
            r7 = r9
            r7.<init>()
            java.lang.String r7 = "kawa:  caught "
            java.lang.StringBuilder r6 = r6.append(r7)
            r7 = r4
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r7 = " while looking for format '"
            java.lang.StringBuilder r6 = r6.append(r7)
            r7 = r0
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r7 = "'"
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r6 = r6.toString()
            r5.println(r6)
            r5 = -1
            java.lang.System.exit(r5)
            goto L_0x006c
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.Shell.setDefaultFormat(java.lang.String):void");
    }

    public static Consumer getOutputConsumer(OutPort outPort) {
        Throwable th;
        StringBuilder sb;
        OutPort out = outPort;
        Object[] info = defaultFormatInfo;
        if (out == null) {
            return VoidConsumer.getInstance();
        }
        if (info == null) {
            return Language.getDefaultLanguage().getOutputConsumer(out);
        }
        try {
            Object[] args = new Object[(info.length - 4)];
            System.arraycopy(info, 4, args, 0, args.length);
            int i = args.length;
            while (true) {
                i--;
                if (i < 0) {
                    break;
                } else if (args[i] == portArg) {
                    args[i] = out;
                }
            }
            Object format = defaultFormatMethod.invoke((Object) null, args);
            if (!(format instanceof AbstractFormat)) {
                return (Consumer) format;
            }
            out.objectFormat = (AbstractFormat) format;
            return out;
        } catch (Throwable th2) {
            Throwable ex = th2;
            Throwable th3 = th;
            new StringBuilder();
            new RuntimeException(sb.append("cannot get output-format '").append(defaultFormatName).append("' - caught ").append(ex).toString());
            throw th3;
        }
    }

    public static boolean run(Language language, Environment environment) {
        SourceMessages sourceMessages;
        OutPort perr;
        Language language2 = language;
        Environment env = environment;
        InPort inp = InPort.inDefault();
        new SourceMessages();
        SourceMessages messages = sourceMessages;
        if (inp instanceof TtyInPort) {
            Procedure prompter = language2.getPrompter();
            if (prompter != null) {
                ((TtyInPort) inp).setPrompter(prompter);
            }
            perr = OutPort.errDefault();
        } else {
            perr = null;
        }
        Throwable ex = run(language2, env, inp, OutPort.outDefault(), perr, messages);
        if (ex == null) {
            return true;
        }
        printError(ex, messages, OutPort.errDefault());
        return false;
    }

    /* JADX INFO: finally extract failed */
    public static Throwable run(Language language, Environment environment, InPort inPort, OutPort outPort, OutPort outPort2, SourceMessages sourceMessages) {
        Language language2 = language;
        Environment env = environment;
        InPort inp = inPort;
        OutPort pout = outPort;
        OutPort perr = outPort2;
        SourceMessages messages = sourceMessages;
        AbstractFormat saveFormat = null;
        if (pout != null) {
            saveFormat = pout.objectFormat;
        }
        try {
            Throwable run = run(language2, env, inp, getOutputConsumer(pout), perr, (URL) null, messages);
            if (pout != null) {
                pout.objectFormat = saveFormat;
            }
            return run;
        } catch (Throwable th) {
            Throwable th2 = th;
            if (pout != null) {
                pout.objectFormat = saveFormat;
            }
            throw th2;
        }
    }

    public static boolean run(Language language, Environment env, InPort inp, Consumer out, OutPort outPort, URL url) {
        SourceMessages sourceMessages;
        OutPort perr = outPort;
        new SourceMessages();
        SourceMessages messages = sourceMessages;
        Throwable ex = run(language, env, inp, out, perr, url, messages);
        if (ex != null) {
            printError(ex, messages, perr);
        }
        return ex == null;
    }

    public static Throwable run(Language language, Environment environment, InPort inPort, Consumer consumer, OutPort outPort, URL url, SourceMessages sourceMessages) {
        Throwable th;
        boolean sawError;
        StringBuilder sb;
        int ch;
        ClassLoader classLoader;
        Language language2 = language;
        Environment env = environment;
        InPort inp = inPort;
        Consumer out = consumer;
        OutPort perr = outPort;
        URL url2 = url;
        SourceMessages messages = sourceMessages;
        Language saveLanguage = Language.setSaveCurrent(language2);
        Lexer lexer = language2.getLexer(inp, messages);
        boolean interactive = perr != null;
        lexer.setInteractive(interactive);
        CallContext ctx = CallContext.getInstance();
        Consumer saveConsumer = null;
        if (out != null) {
            saveConsumer = ctx.consumer;
            ctx.consumer = out;
        }
        try {
            Thread thread = Thread.currentThread();
            ClassLoader parentLoader = thread.getContextClassLoader();
            if (!(parentLoader instanceof ArrayClassLoader)) {
                new ArrayClassLoader(parentLoader);
                thread.setContextClassLoader(classLoader);
            }
        } catch (SecurityException e) {
            SecurityException securityException = e;
        }
        while (true) {
            try {
                Compilation comp = language2.parse(lexer, 7, (ModuleInfo) null);
                if (interactive) {
                    sawError = messages.checkErrors((PrintWriter) perr, 20);
                } else if (messages.seenErrors()) {
                    Throwable th2 = th;
                    new SyntaxException(messages);
                    throw th2;
                } else {
                    sawError = false;
                }
                if (comp == null) {
                    break;
                } else if (sawError) {
                    continue;
                } else {
                    ModuleExp module = comp.getModule();
                    new StringBuilder();
                    StringBuilder append = sb.append("atInteractiveLevel$");
                    int i = ModuleExp.interactiveCounter + 1;
                    ModuleExp.interactiveCounter = i;
                    module.setName(append.append(i).toString());
                    while (true) {
                        ch = inp.read();
                        if (ch >= 0 && ch != 13 && ch != 10) {
                            if (ch != 32 && ch != 9) {
                                inp.unread();
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    if (ModuleExp.evalModule(env, ctx, comp, url2, perr)) {
                        if (out instanceof Writer) {
                            ((Writer) out).flush();
                        }
                        if (ch < 0) {
                            break;
                        }
                    }
                }
            } catch (Throwable th3) {
                Throwable th4 = th3;
                if (out != null) {
                    ctx.consumer = saveConsumer;
                }
                Language.restoreCurrent(saveLanguage);
                throw th4;
            }
        }
        if (out != null) {
            ctx.consumer = saveConsumer;
        }
        Language.restoreCurrent(saveLanguage);
        return null;
    }

    public static void printError(Throwable th, SourceMessages sourceMessages, OutPort outPort) {
        StringBuilder sb;
        StringBuilder sb2;
        Throwable ex = th;
        SourceMessages messages = sourceMessages;
        OutPort perr = outPort;
        if (ex instanceof WrongArguments) {
            WrongArguments e = (WrongArguments) ex;
            messages.printAll((PrintWriter) perr, 20);
            if (e.usage != null) {
                new StringBuilder();
                perr.println(sb2.append("usage: ").append(e.usage).toString());
            }
            e.printStackTrace(perr);
        } else if (ex instanceof ClassCastException) {
            messages.printAll((PrintWriter) perr, 20);
            new StringBuilder();
            perr.println(sb.append("Invalid parameter, was: ").append(ex.getMessage()).toString());
            ex.printStackTrace(perr);
        } else {
            if (ex instanceof SyntaxException) {
                SyntaxException syntaxException = (SyntaxException) ex;
                SyntaxException se = syntaxException;
                if (syntaxException.getMessages() == messages) {
                    se.printAll(perr, 20);
                    se.clear();
                    return;
                }
            }
            messages.printAll((PrintWriter) perr, 20);
            ex.printStackTrace(perr);
        }
    }

    public static final CompiledModule checkCompiledZip(InputStream inputStream, Path path, Environment environment, Language language) throws IOException {
        Throwable th;
        StringBuilder sb;
        ZipLoader loader;
        Throwable th2;
        StringBuilder sb2;
        Throwable th3;
        StringBuilder sb3;
        Throwable th4;
        StringBuilder sb4;
        InputStream fs = inputStream;
        Path path2 = path;
        Environment env = environment;
        Language language2 = language;
        try {
            fs.mark(5);
            boolean isZip = fs.read() == 80 && fs.read() == 75 && fs.read() == 3 && fs.read() == 4;
            fs.reset();
            if (!isZip) {
                return null;
            }
            fs.close();
            Environment orig_env = Environment.getCurrent();
            String name = path2.toString();
            if (env != orig_env) {
                try {
                    Environment.setCurrent(env);
                } catch (IOException e) {
                    IOException ex = e;
                    Throwable th5 = th;
                    new StringBuilder();
                    new WrappedException(sb.append("load: ").append(name).append(" - ").append(ex.toString()).toString(), ex);
                    throw th5;
                } catch (Throwable th6) {
                    Throwable th7 = th6;
                    if (env != orig_env) {
                        Environment.setCurrent(orig_env);
                    }
                    throw th7;
                }
            }
            if (!(path2 instanceof FilePath)) {
                Throwable th8 = th4;
                new StringBuilder();
                new RuntimeException(sb4.append("load: ").append(name).append(" - not a file path").toString());
                throw th8;
            }
            File zfile = ((FilePath) path2).toFile();
            if (!zfile.exists()) {
                Throwable th9 = th3;
                new StringBuilder();
                new RuntimeException(sb3.append("load: ").append(name).append(" - not found").toString());
                throw th9;
            } else if (!zfile.canRead()) {
                Throwable th10 = th2;
                new StringBuilder();
                new RuntimeException(sb2.append("load: ").append(name).append(" - not readable").toString());
                throw th10;
            } else {
                new ZipLoader(name);
                CompiledModule make = CompiledModule.make(loader.loadAllClasses(), language2);
                if (env != orig_env) {
                    Environment.setCurrent(orig_env);
                }
                return make;
            }
        } catch (IOException e2) {
            IOException iOException = e2;
            return null;
        }
    }

    public static boolean runFileOrClass(String str, boolean z, int i) {
        StringBuilder sb;
        Path path;
        InputStream fs;
        String fname = str;
        boolean lineByLine = z;
        int skipLines = i;
        Language language = Language.getDefaultLanguage();
        try {
            if (fname.equals("-")) {
                path = Path.valueOf("/dev/stdin");
                fs = System.in;
            } else {
                path = Path.valueOf(fname);
                fs = path.openInputStream();
            }
            return runFile(fs, path, Environment.getCurrent(), lineByLine, skipLines);
        } catch (Throwable th) {
            Throwable e = th;
            try {
                CompiledModule.make(Class.forName(fname), language).evalModule(Environment.getCurrent(), OutPort.outDefault());
                return true;
            } catch (Throwable th2) {
                th2.printStackTrace();
                return false;
            }
        }
    }

    public static final boolean runFile(InputStream inputStream, Path path, Environment environment, boolean z, int i) throws Throwable {
        InPort src;
        SourceMessages sourceMessages;
        Consumer consumer;
        Consumer out;
        InputStream inputStream2;
        InputStream fs = inputStream;
        Path path2 = path;
        Environment env = environment;
        boolean lineByLine = z;
        int skipLines = i;
        if (!(fs instanceof BufferedInputStream)) {
            new BufferedInputStream(fs);
            fs = inputStream2;
        }
        Language language = Language.getDefaultLanguage();
        Path savePath = (Path) currentLoadPath.get();
        try {
            currentLoadPath.set(path2);
            CompiledModule cmodule = checkCompiledZip(fs, path2, env, language);
            if (cmodule == null) {
                src = InPort.openFile(fs, path2);
                while (true) {
                    skipLines--;
                    if (skipLines < 0) {
                        break;
                    }
                    src.skipRestOfLine();
                }
                new SourceMessages();
                SourceMessages messages = sourceMessages;
                URL url = path2.toURL();
                if (lineByLine) {
                    if (ModuleBody.getMainPrintValues()) {
                        out = getOutputConsumer(OutPort.outDefault());
                    } else {
                        out = consumer;
                        new VoidConsumer();
                    }
                    Throwable ex = run(language, env, src, out, (OutPort) null, url, messages);
                    if (ex != null) {
                        throw ex;
                    }
                } else {
                    cmodule = compileSource(src, env, url, language, messages);
                    messages.printAll((PrintWriter) OutPort.errDefault(), 20);
                    if (cmodule == null) {
                        src.close();
                        currentLoadPath.set(savePath);
                        return false;
                    }
                }
                src.close();
            }
            if (cmodule != null) {
                cmodule.evalModule(env, OutPort.outDefault());
            }
            currentLoadPath.set(savePath);
            return true;
        } catch (Throwable th) {
            Throwable th2 = th;
            currentLoadPath.set(savePath);
            throw th2;
        }
    }

    static CompiledModule compileSource(InPort inPort, Environment env, URL url, Language language, SourceMessages sourceMessages) throws SyntaxException, IOException {
        CompiledModule compiledModule;
        InPort port = inPort;
        Language language2 = language;
        SourceMessages messages = sourceMessages;
        Compilation comp = language2.parse(port, messages, 1, ModuleManager.getInstance().findWithSourcePath(port.getName()));
        CallContext.getInstance().values = Values.noArgs;
        Object inst = ModuleExp.evalModule1(env, comp, url, (OutPort) null);
        if (inst == null || messages.seenErrors()) {
            return null;
        }
        new CompiledModule(comp.getModule(), inst, language2);
        return compiledModule;
    }
}

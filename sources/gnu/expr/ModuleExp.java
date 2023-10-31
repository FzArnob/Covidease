package gnu.expr;

import android.support.p000v4.media.session.PlaybackStateCompat;
import gnu.bytecode.ArrayClassLoader;
import gnu.bytecode.ClassType;
import gnu.bytecode.Field;
import gnu.bytecode.Variable;
import gnu.kawa.reflect.ClassMemberLocation;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.Location;
import gnu.mapping.NamedLocation;
import gnu.mapping.OutPort;
import gnu.mapping.Symbol;
import gnu.mapping.WrappedException;
import gnu.text.Path;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.Externalizable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.OutputStream;
import java.net.URL;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ModuleExp extends LambdaExp implements Externalizable {
    public static final int EXPORT_SPECIFIED = 16384;
    public static final int IMMEDIATE = 1048576;
    public static final int LAZY_DECLARATIONS = 524288;
    public static final int NONSTATIC_SPECIFIED = 65536;
    public static final int STATIC_RUN_SPECIFIED = 262144;
    public static final int STATIC_SPECIFIED = 32768;
    public static final int SUPERTYPE_SPECIFIED = 131072;
    public static boolean alwaysCompile = compilerAvailable;
    public static boolean compilerAvailable = true;
    public static String dumpZipPrefix;
    public static int interactiveCounter;
    static int lastZipCounter;
    public static boolean neverCompile = false;
    ModuleInfo info;
    ClassType[] interfaces;
    ClassType superType;

    public ModuleExp() {
    }

    public static Class evalToClass(Compilation compilation, URL url) throws SyntaxException {
        StringBuilder sb;
        Throwable th;
        Throwable th2;
        Throwable th3;
        StringBuilder sb2;
        ZipEntry zipEntry;
        CRC32 crc32;
        StringBuffer stringBuffer;
        OutputStream outputStream;
        ZipOutputStream zipOutputStream;
        Compilation comp = compilation;
        URL url2 = url;
        ModuleExp module = comp.getModule();
        SourceMessages messages = comp.getMessages();
        try {
            comp.minfo.loadByStages(12);
            if (messages.seenErrors()) {
                return null;
            }
            ArrayClassLoader loader = comp.loader;
            if (url2 == null) {
                url2 = Path.currentPath().toURL();
            }
            loader.setResourceContext(url2);
            ZipOutputStream zout = null;
            if (dumpZipPrefix != null) {
                new StringBuffer(dumpZipPrefix);
                StringBuffer zipname = stringBuffer;
                lastZipCounter++;
                if (interactiveCounter > lastZipCounter) {
                    lastZipCounter = interactiveCounter;
                }
                StringBuffer append = zipname.append(lastZipCounter);
                StringBuffer append2 = zipname.append(".zip");
                new FileOutputStream(zipname.toString());
                new ZipOutputStream(outputStream);
                zout = zipOutputStream;
            }
            for (int iClass = 0; iClass < comp.numClasses; iClass++) {
                ClassType clas = comp.classes[iClass];
                String className = clas.getName();
                byte[] classBytes = clas.writeToArray();
                loader.addClass(className, classBytes);
                if (zout != null) {
                    new StringBuilder();
                    new ZipEntry(sb2.append(className.replace('.', '/')).append(".class").toString());
                    ZipEntry zent = zipEntry;
                    zent.setSize((long) classBytes.length);
                    new CRC32();
                    CRC32 crc = crc32;
                    crc.update(classBytes);
                    zent.setCrc(crc.getValue());
                    zent.setMethod(0);
                    zout.putNextEntry(zent);
                    zout.write(classBytes);
                }
            }
            if (zout != null) {
                zout.close();
            }
            Class clas2 = null;
            ArrayClassLoader context = loader;
            while (context.getParent() instanceof ArrayClassLoader) {
                context = (ArrayClassLoader) context.getParent();
            }
            for (int iClass2 = 0; iClass2 < comp.numClasses; iClass2++) {
                ClassType ctype = comp.classes[iClass2];
                Class cclass = loader.loadClass(ctype.getName());
                ctype.setReflectClass(cclass);
                ctype.setExisting(true);
                if (iClass2 == 0) {
                    clas2 = cclass;
                } else if (context != loader) {
                    context.addClass(cclass);
                }
            }
            ModuleInfo minfo = comp.minfo;
            minfo.setModuleClass(clas2);
            comp.cleanupAfterCompilation();
            int ndeps = minfo.numDependencies;
            for (int idep = 0; idep < ndeps; idep++) {
                ModuleInfo dep = minfo.dependencies[idep];
                Class dclass = dep.getModuleClassRaw();
                if (dclass == null) {
                    dclass = evalToClass(dep.comp, (URL) null);
                }
                comp.loader.addClass(dclass);
            }
            return clas2;
        } catch (IOException e) {
            IOException ex = e;
            Throwable th4 = th3;
            new WrappedException("I/O error in lambda eval", ex);
            throw th4;
        } catch (ClassNotFoundException e2) {
            ClassNotFoundException ex2 = e2;
            Throwable th5 = th2;
            new WrappedException("class not found in lambda eval", ex2);
            throw th5;
        } catch (Throwable th6) {
            Throwable ex3 = th6;
            SourceMessages messages2 = comp.getMessages();
            new StringBuilder();
            messages2.error('f', sb.append("internal compile error - caught ").append(ex3).toString(), ex3);
            Throwable th7 = th;
            new SyntaxException(messages);
            throw th7;
        }
    }

    public static void mustNeverCompile() {
        alwaysCompile = false;
        neverCompile = true;
        compilerAvailable = false;
    }

    public static void mustAlwaysCompile() {
        alwaysCompile = true;
        neverCompile = false;
    }

    public static final boolean evalModule(Environment environment, CallContext callContext, Compilation compilation, URL url, OutPort msg) throws Throwable {
        Environment env = environment;
        CallContext ctx = callContext;
        Compilation comp = compilation;
        ModuleExp mexp = comp.getModule();
        Language language = comp.getLanguage();
        Object inst = evalModule1(env, comp, url, msg);
        if (inst == null) {
            return false;
        }
        evalModule2(env, ctx, language, mexp, inst);
        return true;
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0061, code lost:
        if (r7.checkErrors((java.io.PrintWriter) r3, 20) != false) goto L_0x0063;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x007d, code lost:
        if (r7.seenErrors() == false) goto L_0x007f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0082, code lost:
        if (r1.mustCompile != false) goto L_0x00da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0086, code lost:
        if (gnu.expr.Compilation.debugPrintFinalExpr == false) goto L_0x00c4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0089, code lost:
        if (r3 == null) goto L_0x00c4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x008b, code lost:
        new java.lang.StringBuilder();
        r3.println(r16.append("[Evaluating final module \"").append(r4.getName()).append("\":").toString());
        r4.print(r3);
        r3.println(']');
        r3.flush();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00c6, code lost:
        r10 = java.lang.Boolean.TRUE;
        gnu.mapping.Environment.restoreCurrent(r5);
        gnu.expr.Compilation.restoreCurrent(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00d0, code lost:
        if (0 == 0) goto L_0x00d7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00d2, code lost:
        r13 = null;
        r13.setContextClassLoader((java.lang.ClassLoader) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00df, code lost:
        r10 = evalToClass(r1, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00e2, code lost:
        if (r10 != null) goto L_0x00fa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00e4, code lost:
        gnu.mapping.Environment.restoreCurrent(r5);
        gnu.expr.Compilation.restoreCurrent(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00ef, code lost:
        if (0 == 0) goto L_0x00f6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00f1, code lost:
        r13 = null;
        r13.setContextClassLoader((java.lang.ClassLoader) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
        r9 = java.lang.Thread.currentThread();
        r8 = r9.getContextClassLoader();
        r9.setContextClassLoader(r10.getClassLoader());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0121, code lost:
        if (r7.checkErrors((java.io.PrintWriter) r3, 20) != false) goto L_0x0123;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x013d, code lost:
        r13 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x013e, code lost:
        r11 = r13;
        r9 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0147, code lost:
        if (r7.seenErrors() == false) goto L_0x0149;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0149, code lost:
        r11 = r10;
        gnu.mapping.Environment.restoreCurrent(r5);
        gnu.expr.Compilation.restoreCurrent(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0154, code lost:
        if (r9 == null) goto L_0x015b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0156, code lost:
        r9.setContextClassLoader(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:?, code lost:
        return r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:?, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:?, code lost:
        return r11;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object evalModule1(gnu.mapping.Environment r17, gnu.expr.Compilation r18, java.net.URL r19, gnu.mapping.OutPort r20) throws gnu.text.SyntaxException {
        /*
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r20
            r13 = r1
            gnu.expr.ModuleExp r13 = r13.getModule()
            r4 = r13
            r13 = r4
            r14 = r1
            gnu.expr.ModuleInfo r14 = r14.minfo
            r13.info = r14
            r13 = r0
            gnu.mapping.Environment r13 = gnu.mapping.Environment.setSaveCurrent(r13)
            r5 = r13
            r13 = r1
            gnu.expr.Compilation r13 = gnu.expr.Compilation.setSaveCurrent(r13)
            r6 = r13
            r13 = r1
            gnu.text.SourceMessages r13 = r13.getMessages()
            r7 = r13
            r13 = 0
            r8 = r13
            r13 = 0
            r9 = r13
            boolean r13 = alwaysCompile
            if (r13 == 0) goto L_0x0041
            boolean r13 = neverCompile
            if (r13 == 0) goto L_0x0041
            java.lang.RuntimeException r13 = new java.lang.RuntimeException
            r16 = r13
            r13 = r16
            r14 = r16
            java.lang.String r15 = "alwaysCompile and neverCompile are both true!"
            r14.<init>(r15)
            throw r13
        L_0x0041:
            boolean r13 = neverCompile
            if (r13 == 0) goto L_0x0049
            r13 = r1
            r14 = 0
            r13.mustCompile = r14
        L_0x0049:
            r13 = r1
            r14 = 6
            r13.process(r14)     // Catch:{ all -> 0x015f }
            r13 = r1
            gnu.expr.ModuleInfo r13 = r13.minfo     // Catch:{ all -> 0x015f }
            r14 = 8
            r13.loadByStages(r14)     // Catch:{ all -> 0x015f }
            r13 = r3
            if (r13 == 0) goto L_0x0078
            r13 = r7
            r14 = r3
            r15 = 20
            boolean r13 = r13.checkErrors((java.io.PrintWriter) r14, (int) r15)     // Catch:{ all -> 0x015f }
            if (r13 == 0) goto L_0x007f
        L_0x0063:
            r13 = 0
            r10 = r13
            r13 = r5
            gnu.mapping.Environment.restoreCurrent(r13)
            r13 = r6
            gnu.expr.Compilation.restoreCurrent(r13)
            r13 = r9
            if (r13 == 0) goto L_0x0075
            r13 = r9
            r14 = r8
            r13.setContextClassLoader(r14)
        L_0x0075:
            r13 = r10
            r0 = r13
        L_0x0077:
            return r0
        L_0x0078:
            r13 = r7
            boolean r13 = r13.seenErrors()     // Catch:{ all -> 0x015f }
            if (r13 != 0) goto L_0x0063
        L_0x007f:
            r13 = r1
            boolean r13 = r13.mustCompile     // Catch:{ all -> 0x015f }
            if (r13 != 0) goto L_0x00da
            boolean r13 = gnu.expr.Compilation.debugPrintFinalExpr     // Catch:{ all -> 0x015f }
            if (r13 == 0) goto L_0x00c4
            r13 = r3
            if (r13 == 0) goto L_0x00c4
            r13 = r3
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ all -> 0x015f }
            r16 = r14
            r14 = r16
            r15 = r16
            r15.<init>()     // Catch:{ all -> 0x015f }
            java.lang.String r15 = "[Evaluating final module \""
            java.lang.StringBuilder r14 = r14.append(r15)     // Catch:{ all -> 0x015f }
            r15 = r4
            java.lang.String r15 = r15.getName()     // Catch:{ all -> 0x015f }
            java.lang.StringBuilder r14 = r14.append(r15)     // Catch:{ all -> 0x015f }
            java.lang.String r15 = "\":"
            java.lang.StringBuilder r14 = r14.append(r15)     // Catch:{ all -> 0x015f }
            java.lang.String r14 = r14.toString()     // Catch:{ all -> 0x015f }
            r13.println(r14)     // Catch:{ all -> 0x015f }
            r13 = r4
            r14 = r3
            r13.print(r14)     // Catch:{ all -> 0x015f }
            r13 = r3
            r14 = 93
            r13.println(r14)     // Catch:{ all -> 0x015f }
            r13 = r3
            r13.flush()     // Catch:{ all -> 0x015f }
        L_0x00c4:
            java.lang.Boolean r13 = java.lang.Boolean.TRUE     // Catch:{ all -> 0x015f }
            r10 = r13
            r13 = r5
            gnu.mapping.Environment.restoreCurrent(r13)
            r13 = r6
            gnu.expr.Compilation.restoreCurrent(r13)
            r13 = r9
            if (r13 == 0) goto L_0x00d7
            r13 = r9
            r14 = r8
            r13.setContextClassLoader(r14)
        L_0x00d7:
            r13 = r10
            r0 = r13
            goto L_0x0077
        L_0x00da:
            r13 = r1
            r14 = r2
            java.lang.Class r13 = evalToClass(r13, r14)     // Catch:{ all -> 0x015f }
            r10 = r13
            r13 = r10
            if (r13 != 0) goto L_0x00fa
            r13 = 0
            r11 = r13
            r13 = r5
            gnu.mapping.Environment.restoreCurrent(r13)
            r13 = r6
            gnu.expr.Compilation.restoreCurrent(r13)
            r13 = r9
            if (r13 == 0) goto L_0x00f6
            r13 = r9
            r14 = r8
            r13.setContextClassLoader(r14)
        L_0x00f6:
            r13 = r11
            r0 = r13
            goto L_0x0077
        L_0x00fa:
            java.lang.Thread r13 = java.lang.Thread.currentThread()     // Catch:{ Throwable -> 0x013d }
            r9 = r13
            r13 = r9
            java.lang.ClassLoader r13 = r13.getContextClassLoader()     // Catch:{ Throwable -> 0x013d }
            r8 = r13
            r13 = r9
            r14 = r10
            java.lang.ClassLoader r14 = r14.getClassLoader()     // Catch:{ Throwable -> 0x013d }
            r13.setContextClassLoader(r14)     // Catch:{ Throwable -> 0x013d }
        L_0x010e:
            r13 = r4
            r14 = 0
            r13.body = r14     // Catch:{ all -> 0x015f }
            r13 = r4
            r14 = 0
            r13.thisVariable = r14     // Catch:{ all -> 0x015f }
            r13 = r3
            if (r13 == 0) goto L_0x0142
            r13 = r7
            r14 = r3
            r15 = 20
            boolean r13 = r13.checkErrors((java.io.PrintWriter) r14, (int) r15)     // Catch:{ all -> 0x015f }
            if (r13 == 0) goto L_0x0149
        L_0x0123:
            r13 = 0
            java.lang.Boolean r13 = java.lang.Boolean.valueOf(r13)     // Catch:{ all -> 0x015f }
            r11 = r13
            r13 = r5
            gnu.mapping.Environment.restoreCurrent(r13)
            r13 = r6
            gnu.expr.Compilation.restoreCurrent(r13)
            r13 = r9
            if (r13 == 0) goto L_0x0139
            r13 = r9
            r14 = r8
            r13.setContextClassLoader(r14)
        L_0x0139:
            r13 = r11
            r0 = r13
            goto L_0x0077
        L_0x013d:
            r13 = move-exception
            r11 = r13
            r13 = 0
            r9 = r13
            goto L_0x010e
        L_0x0142:
            r13 = r7
            boolean r13 = r13.seenErrors()     // Catch:{ all -> 0x015f }
            if (r13 != 0) goto L_0x0123
        L_0x0149:
            r13 = r10
            r11 = r13
            r13 = r5
            gnu.mapping.Environment.restoreCurrent(r13)
            r13 = r6
            gnu.expr.Compilation.restoreCurrent(r13)
            r13 = r9
            if (r13 == 0) goto L_0x015b
            r13 = r9
            r14 = r8
            r13.setContextClassLoader(r14)
        L_0x015b:
            r13 = r11
            r0 = r13
            goto L_0x0077
        L_0x015f:
            r13 = move-exception
            r12 = r13
            r13 = r5
            gnu.mapping.Environment.restoreCurrent(r13)
            r13 = r6
            gnu.expr.Compilation.restoreCurrent(r13)
            r13 = r9
            if (r13 == 0) goto L_0x0171
            r13 = r9
            r14 = r8
            r13.setContextClassLoader(r14)
        L_0x0171:
            r13 = r12
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.ModuleExp.evalModule1(gnu.mapping.Environment, gnu.expr.Compilation, java.net.URL, gnu.mapping.OutPort):java.lang.Object");
    }

    public static final void evalModule2(Environment environment, CallContext callContext, Language language, ModuleExp moduleExp, Object obj) throws Throwable {
        Symbol make;
        StaticFieldLocation staticFieldLocation;
        Object value;
        Environment env = environment;
        CallContext ctx = callContext;
        Language language2 = language;
        ModuleExp mexp = moduleExp;
        Object inst = obj;
        Environment orig_env = Environment.setSaveCurrent(env);
        try {
            if (inst == Boolean.TRUE) {
                mexp.body.apply(ctx);
            } else {
                if (inst instanceof Class) {
                    inst = ModuleContext.getContext().findInstance((Class) inst);
                }
                if (inst instanceof Runnable) {
                    if (inst instanceof ModuleBody) {
                        ModuleBody mb = (ModuleBody) inst;
                        if (!mb.runDone) {
                            mb.runDone = true;
                            mb.run(ctx);
                        }
                    } else {
                        ((Runnable) inst).run();
                    }
                }
                if (mexp == null) {
                    ClassMemberLocation.defineAll(inst, language2, env);
                } else {
                    for (Declaration decl = mexp.firstDecl(); decl != null; decl = decl.nextDecl()) {
                        Object dname = decl.getSymbol();
                        if (!decl.isPrivate() && dname != null) {
                            Field fld = decl.field;
                            if (dname instanceof Symbol) {
                                make = (Symbol) dname;
                            } else {
                                make = Symbol.make("", dname.toString().intern());
                            }
                            Symbol sym = make;
                            Object property = language2.getEnvPropertyFor(decl);
                            Expression dvalue = decl.getValue();
                            if ((decl.field.getModifiers() & 16) != 0) {
                                if (!(dvalue instanceof QuoteExp) || dvalue == QuoteExp.undefined_exp) {
                                    value = decl.field.getReflectField().get((Object) null);
                                    if (!decl.isIndirectBinding()) {
                                        decl.setValue(QuoteExp.getInstance(value));
                                    } else if (!decl.isAlias() || !(dvalue instanceof ReferenceExp)) {
                                        decl.setValue((Expression) null);
                                    }
                                } else {
                                    value = ((QuoteExp) dvalue).getValue();
                                }
                                if (decl.isIndirectBinding()) {
                                    NamedLocation addLocation = env.addLocation(sym, property, (Location) value);
                                } else {
                                    env.define(sym, property, value);
                                }
                            } else {
                                new StaticFieldLocation(fld.getDeclaringClass(), fld.getName());
                                StaticFieldLocation loc = staticFieldLocation;
                                loc.setDeclaration(decl);
                                NamedLocation addLocation2 = env.addLocation(sym, property, loc);
                                decl.setValue((Expression) null);
                            }
                        }
                    }
                }
            }
            ctx.runUntilDone();
            Environment.restoreCurrent(orig_env);
            if (0 != 0) {
                Thread thread = null;
                thread.setContextClassLoader((ClassLoader) null);
            }
        } catch (Throwable th) {
            Throwable th2 = th;
            Environment.restoreCurrent(orig_env);
            if (0 != 0) {
                Thread thread2 = null;
                thread2.setContextClassLoader((ClassLoader) null);
            }
            throw th2;
        }
    }

    public String getNamespaceUri() {
        return this.info.uri;
    }

    public final ClassType getSuperType() {
        return this.superType;
    }

    public final void setSuperType(ClassType s) {
        ClassType classType = s;
        this.superType = classType;
    }

    public final ClassType[] getInterfaces() {
        return this.interfaces;
    }

    public final void setInterfaces(ClassType[] s) {
        ClassType[] classTypeArr = s;
        this.interfaces = classTypeArr;
    }

    public final boolean isStatic() {
        return getFlag(32768) || ((Compilation.moduleStatic >= 0 || getFlag(1048576)) && !getFlag(131072) && !getFlag(65536));
    }

    public boolean staticInitRun() {
        return isStatic() && (getFlag(262144) || Compilation.moduleStatic == 2);
    }

    public void allocChildClasses(Compilation compilation) {
        Compilation comp = compilation;
        Variable declareClosureEnv = declareClosureEnv();
        if (comp.usingCPStyle()) {
            allocFrame(comp);
        }
    }

    /* access modifiers changed from: package-private */
    public void allocFields(Compilation compilation) {
        Compilation comp = compilation;
        Declaration firstDecl = firstDecl();
        while (true) {
            Declaration decl = firstDecl;
            if (decl == null) {
                break;
            }
            if ((!decl.isSimple() || decl.isPublic()) && decl.field == null && decl.getFlag(PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) && decl.getFlag(6)) {
                decl.makeField(comp, (Expression) null);
            }
            firstDecl = decl.nextDecl();
        }
        Declaration firstDecl2 = firstDecl();
        while (true) {
            Declaration decl2 = firstDecl2;
            if (decl2 != null) {
                if (decl2.field == null) {
                    Expression value = decl2.getValue();
                    if ((!decl2.isSimple() || decl2.isPublic() || decl2.isNamespaceDecl() || (decl2.getFlag(16384) && decl2.getFlag(6))) && !decl2.getFlag(PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH)) {
                        if (!(value instanceof LambdaExp) || (value instanceof ModuleExp) || (value instanceof ClassExp)) {
                            decl2.makeField(comp, (decl2.shouldEarlyInit() || decl2.isAlias()) ? value : null);
                        } else {
                            Field allocFieldFor = ((LambdaExp) value).allocFieldFor(comp);
                        }
                    }
                }
                firstDecl2 = decl2.nextDecl();
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public <R, D> R visit(ExpVisitor<R, D> visitor, D d) {
        return visitor.visitModuleExp(this, d);
    }

    public void print(OutPort outPort) {
        OutPort out = outPort;
        out.startLogicalBlock("(Module/", ")", 2);
        Object sym = getSymbol();
        if (sym != null) {
            out.print(sym);
            out.print('/');
        }
        out.print(this.f60id);
        out.print('/');
        out.writeSpaceFill();
        out.startLogicalBlock("(", false, ")");
        Declaration decl = firstDecl();
        if (decl != null) {
            out.print("Declarations:");
            while (decl != null) {
                out.writeSpaceFill();
                decl.printInfo(out);
                decl = decl.nextDecl();
            }
        }
        out.endLogicalBlock(")");
        out.writeSpaceLinear();
        if (this.body == null) {
            out.print("<null body>");
        } else {
            this.body.print(out);
        }
        out.endLogicalBlock(")");
    }

    public Declaration firstDecl() {
        synchronized (this) {
            try {
                if (getFlag(524288)) {
                    ModuleExp moduleExp = this.info.setupModuleExp();
                }
                return this.decls;
            } catch (Throwable th) {
                while (true) {
                    Throwable th2 = th;
                    throw th2;
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x00b3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public gnu.bytecode.ClassType classFor(gnu.expr.Compilation r15) {
        /*
            r14 = this;
            r0 = r14
            r1 = r15
            r9 = r0
            gnu.bytecode.ClassType r9 = r9.type
            if (r9 == 0) goto L_0x0013
            r9 = r0
            gnu.bytecode.ClassType r9 = r9.type
            gnu.bytecode.ClassType r10 = gnu.expr.Compilation.typeProcedure
            if (r9 == r10) goto L_0x0013
            r9 = r0
            gnu.bytecode.ClassType r9 = r9.type
            r0 = r9
        L_0x0012:
            return r0
        L_0x0013:
            r9 = r0
            java.lang.String r9 = r9.getFileName()
            r2 = r9
            r9 = r0
            java.lang.String r9 = r9.getName()
            r3 = r9
            r9 = 0
            r4 = r9
            r9 = 0
            r5 = r9
            r9 = r3
            if (r9 == 0) goto L_0x00c0
            r9 = r3
            r2 = r9
        L_0x0028:
            r9 = r0
            java.lang.String r9 = r9.getName()
            if (r9 != 0) goto L_0x0034
            r9 = r0
            r10 = r2
            r9.setName(r10)
        L_0x0034:
            r9 = r2
            java.lang.String r9 = gnu.expr.Compilation.mangleNameIfNeeded(r9)
            r2 = r9
            r9 = r1
            java.lang.String r9 = r9.classPrefix
            int r9 = r9.length()
            if (r9 != 0) goto L_0x013d
            r9 = r5
            if (r9 == 0) goto L_0x013d
            r9 = r5
            boolean r9 = r9.isAbsolute()
            if (r9 != 0) goto L_0x013d
            r9 = r5
            gnu.text.Path r9 = r9.getParent()
            r13 = r9
            r9 = r13
            r10 = r13
            r6 = r10
            if (r9 == 0) goto L_0x013d
            r9 = r6
            java.lang.String r9 = r9.toString()
            r13 = r9
            r9 = r13
            r10 = r13
            r7 = r10
            int r9 = r9.length()
            if (r9 <= 0) goto L_0x013d
            r9 = r7
            java.lang.String r10 = ".."
            int r9 = r9.indexOf(r10)
            if (r9 >= 0) goto L_0x013d
            r9 = r7
            java.lang.String r10 = "file.separator"
            java.lang.String r10 = java.lang.System.getProperty(r10)
            java.lang.String r11 = "/"
            java.lang.String r9 = r9.replaceAll(r10, r11)
            r7 = r9
            r9 = r7
            java.lang.String r10 = "./"
            boolean r9 = r9.startsWith(r10)
            if (r9 == 0) goto L_0x0092
            r9 = r7
            r10 = 2
            java.lang.String r9 = r9.substring(r10)
            r7 = r9
        L_0x0092:
            r9 = r7
            java.lang.String r10 = "."
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x011a
            r9 = r2
        L_0x009d:
            r4 = r9
        L_0x009e:
            gnu.bytecode.ClassType r9 = new gnu.bytecode.ClassType
            r13 = r9
            r9 = r13
            r10 = r13
            r11 = r4
            r10.<init>(r11)
            r8 = r9
            r9 = r0
            r10 = r8
            r9.setType(r10)
            r9 = r1
            gnu.expr.ModuleExp r9 = r9.mainLambda
            r10 = r0
            if (r9 != r10) goto L_0x00bc
            r9 = r1
            gnu.bytecode.ClassType r9 = r9.mainClass
            if (r9 != 0) goto L_0x0158
            r9 = r1
            r10 = r8
            r9.mainClass = r10
        L_0x00bc:
            r9 = r8
            r0 = r9
            goto L_0x0012
        L_0x00c0:
            r9 = r2
            if (r9 != 0) goto L_0x00d2
            r9 = r0
            java.lang.String r9 = r9.getName()
            r2 = r9
            r9 = r2
            if (r9 != 0) goto L_0x0028
            java.lang.String r9 = "$unnamed_input_file$"
            r2 = r9
            goto L_0x0028
        L_0x00d2:
            r9 = r0
            java.lang.String r9 = r9.filename
            java.lang.String r10 = "-"
            boolean r9 = r9.equals(r10)
            if (r9 != 0) goto L_0x00ea
            r9 = r0
            java.lang.String r9 = r9.filename
            java.lang.String r10 = "/dev/stdin"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x00f9
        L_0x00ea:
            r9 = r0
            java.lang.String r9 = r9.getName()
            r2 = r9
            r9 = r2
            if (r9 != 0) goto L_0x0028
            java.lang.String r9 = "$stdin$"
            r2 = r9
            goto L_0x0028
        L_0x00f9:
            r9 = r2
            gnu.text.Path r9 = gnu.text.Path.valueOf(r9)
            r5 = r9
            r9 = r5
            java.lang.String r9 = r9.getLast()
            r2 = r9
            r9 = r2
            r10 = 46
            int r9 = r9.lastIndexOf(r10)
            r6 = r9
            r9 = r6
            if (r9 <= 0) goto L_0x0028
            r9 = r2
            r10 = 0
            r11 = r6
            java.lang.String r9 = r9.substring(r10, r11)
            r2 = r9
            goto L_0x0028
        L_0x011a:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r13 = r9
            r9 = r13
            r10 = r13
            r10.<init>()
            r10 = r7
            java.lang.String r10 = gnu.expr.Compilation.mangleURI(r10)
            java.lang.StringBuilder r9 = r9.append(r10)
            java.lang.String r10 = "."
            java.lang.StringBuilder r9 = r9.append(r10)
            r10 = r2
            java.lang.StringBuilder r9 = r9.append(r10)
            java.lang.String r9 = r9.toString()
            goto L_0x009d
        L_0x013d:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r13 = r9
            r9 = r13
            r10 = r13
            r10.<init>()
            r10 = r1
            java.lang.String r10 = r10.classPrefix
            java.lang.StringBuilder r9 = r9.append(r10)
            r10 = r2
            java.lang.StringBuilder r9 = r9.append(r10)
            java.lang.String r9 = r9.toString()
            r4 = r9
            goto L_0x009e
        L_0x0158:
            r9 = r4
            r10 = r1
            gnu.bytecode.ClassType r10 = r10.mainClass
            java.lang.String r10 = r10.getName()
            boolean r9 = r9.equals(r10)
            if (r9 != 0) goto L_0x00bc
            r9 = r1
            r10 = 101(0x65, float:1.42E-43)
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r13 = r11
            r11 = r13
            r12 = r13
            r12.<init>()
            java.lang.String r12 = "inconsistent main class name: "
            java.lang.StringBuilder r11 = r11.append(r12)
            r12 = r4
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.String r12 = " - old name: "
            java.lang.StringBuilder r11 = r11.append(r12)
            r12 = r1
            gnu.bytecode.ClassType r12 = r12.mainClass
            java.lang.String r12 = r12.getName()
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.String r11 = r11.toString()
            r9.error(r10, r11)
            goto L_0x00bc
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.expr.ModuleExp.classFor(gnu.expr.Compilation):gnu.bytecode.ClassType");
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        ObjectOutput out = objectOutput;
        String name = null;
        if (this.type == null || this.type == Compilation.typeProcedure || this.type.isExisting()) {
            if (0 == 0) {
                name = getName();
            }
            if (name == null) {
                name = getFileName();
            }
            out.writeObject(name);
            return;
        }
        out.writeObject(this.type);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        Object name = in.readObject();
        if (name instanceof ClassType) {
            this.type = (ClassType) name;
            setName(this.type.getName());
        } else {
            setName((String) name);
        }
        this.flags |= 524288;
    }
}

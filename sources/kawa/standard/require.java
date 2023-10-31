package kawa.standard;

import android.support.p000v4.media.session.PlaybackStateCompat;
import gnu.bytecode.ClassType;
import gnu.bytecode.Field;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Language;
import gnu.expr.ModuleExp;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleManager;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.ScopeExp;
import gnu.expr.SetExp;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.reflect.SlotGet;
import gnu.lists.Pair;
import gnu.mapping.InPort;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.text.Path;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.Translator;
import org.shaded.apache.http.HttpHost;

public class require extends Syntax {
    private static final String SLIB_PREFIX = "gnu.kawa.slib.";
    static Hashtable featureMap;
    public static final require require;

    public require() {
    }

    static {
        require require2;
        Hashtable hashtable;
        new require();
        require = require2;
        require.setName("require");
        new Hashtable();
        featureMap = hashtable;
        map("generic-write", "gnu.kawa.slib.genwrite");
        map("pretty-print", "gnu.kawa.slib.pp");
        map("pprint-file", "gnu.kawa.slib.ppfile");
        map("printf", "gnu.kawa.slib.printf");
        map("xml", "gnu.kawa.slib.XML");
        map("readtable", "gnu.kawa.slib.readtable");
        map("srfi-10", "gnu.kawa.slib.readtable");
        map(HttpHost.DEFAULT_SCHEME_NAME, "gnu.kawa.servlet.HTTP");
        map("servlets", "gnu.kawa.servlet.servlets");
        map("srfi-1", "gnu.kawa.slib.srfi1");
        map("list-lib", "gnu.kawa.slib.srfi1");
        map("srfi-2", "gnu.kawa.slib.srfi2");
        map("and-let*", "gnu.kawa.slib.srfi2");
        map("srfi-13", "gnu.kawa.slib.srfi13");
        map("string-lib", "gnu.kawa.slib.srfi13");
        map("srfi-34", "gnu.kawa.slib.srfi34");
        map("srfi-35", "gnu.kawa.slib.conditions");
        map("condition", "gnu.kawa.slib.conditions");
        map("conditions", "gnu.kawa.slib.conditions");
        map("srfi-37", "gnu.kawa.slib.srfi37");
        map("args-fold", "gnu.kawa.slib.srfi37");
        map("srfi-64", "gnu.kawa.slib.testing");
        map("testing", "gnu.kawa.slib.testing");
        map("srfi-69", "gnu.kawa.slib.srfi69");
        map("hash-table", "gnu.kawa.slib.srfi69");
        map("basic-hash-tables", "gnu.kawa.slib.srfi69");
        map("srfi-95", "kawa.lib.srfi95");
        map("sorting-and-merging", "kawa.lib.srfi95");
        map("regex", "kawa.lib.kawa.regex");
        map("pregexp", "gnu.kawa.slib.pregexp");
        map("gui", "gnu.kawa.slib.gui");
        map("swing-gui", "gnu.kawa.slib.swing");
        map("android-defs", "gnu.kawa.android.defs");
        map("syntax-utils", "gnu.kawa.slib.syntaxutils");
    }

    static void map(String featureName, String className) {
        Object put = featureMap.put(featureName, className);
    }

    public static String mapFeature(String featureName) {
        return (String) featureMap.get(featureName);
    }

    public static Object find(String typeName) {
        return ModuleManager.getInstance().findWithClassName(typeName).getInstance();
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x00cb  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x01a4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean scanForDefinitions(gnu.lists.Pair r19, java.util.Vector r20, gnu.expr.ScopeExp r21, kawa.lang.Translator r22) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r2 = r20
            r3 = r21
            r4 = r22
            r11 = r4
            int r11 = r11.getState()
            r12 = 1
            if (r11 != r12) goto L_0x001e
            r11 = r4
            r12 = 2
            r11.setState(r12)
            r11 = r4
            r12 = r1
            r11.pendingForm = r12
            r11 = 1
            r0 = r11
        L_0x001d:
            return r0
        L_0x001e:
            r11 = r1
            java.lang.Object r11 = r11.getCdr()
            gnu.lists.Pair r11 = (gnu.lists.Pair) r11
            r5 = r11
            r11 = r5
            java.lang.Object r11 = r11.getCar()
            r6 = r11
            r11 = 0
            r7 = r11
            r11 = r6
            boolean r11 = r11 instanceof gnu.lists.Pair
            if (r11 == 0) goto L_0x00d8
            r11 = r4
            r12 = r6
            gnu.lists.Pair r12 = (gnu.lists.Pair) r12
            r17 = r12
            r12 = r17
            r13 = r17
            r8 = r13
            java.lang.Object r12 = r12.getCar()
            java.lang.String r13 = "quote"
            boolean r11 = r11.matches(r12, r13)
            if (r11 == 0) goto L_0x00d8
            r11 = r8
            java.lang.Object r11 = r11.getCdr()
            r6 = r11
            r11 = r6
            boolean r11 = r11 instanceof gnu.lists.Pair
            if (r11 == 0) goto L_0x0071
            r11 = r6
            gnu.lists.Pair r11 = (gnu.lists.Pair) r11
            r17 = r11
            r11 = r17
            r12 = r17
            r8 = r12
            java.lang.Object r11 = r11.getCdr()
            gnu.lists.LList r12 = gnu.lists.LList.Empty
            if (r11 != r12) goto L_0x0071
            r11 = r8
            java.lang.Object r11 = r11.getCar()
            boolean r11 = r11 instanceof gnu.mapping.Symbol
            if (r11 != 0) goto L_0x007d
        L_0x0071:
            r11 = r4
            r12 = 101(0x65, float:1.42E-43)
            java.lang.String r13 = "invalid quoted symbol for 'require'"
            r11.error(r12, r13)
            r11 = 0
            r0 = r11
            goto L_0x001d
        L_0x007d:
            r11 = r8
            java.lang.Object r11 = r11.getCar()
            java.lang.String r11 = r11.toString()
            java.lang.String r11 = mapFeature(r11)
            r6 = r11
            r11 = r6
            if (r11 != 0) goto L_0x00be
            r11 = r4
            r12 = 101(0x65, float:1.42E-43)
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r17 = r13
            r13 = r17
            r14 = r17
            r14.<init>()
            java.lang.String r14 = "unknown feature name '"
            java.lang.StringBuilder r13 = r13.append(r14)
            r14 = r8
            java.lang.Object r14 = r14.getCar()
            java.lang.StringBuilder r13 = r13.append(r14)
            java.lang.String r14 = "' for 'require'"
            java.lang.StringBuilder r13 = r13.append(r14)
            java.lang.String r13 = r13.toString()
            r11.error(r12, r13)
            r11 = 0
            r0 = r11
            goto L_0x001d
        L_0x00be:
            r11 = r6
            java.lang.String r11 = (java.lang.String) r11
            gnu.bytecode.ClassType r11 = gnu.bytecode.ClassType.make(r11)
            r7 = r11
        L_0x00c6:
            r11 = r7
            boolean r11 = r11 instanceof gnu.bytecode.ClassType
            if (r11 != 0) goto L_0x01a4
            r11 = r4
            r12 = 101(0x65, float:1.42E-43)
            java.lang.String r13 = "invalid specifier for 'require'"
            r11.error(r12, r13)
            r11 = 0
            r0 = r11
            goto L_0x001d
        L_0x00d8:
            r11 = r6
            boolean r11 = r11 instanceof java.lang.CharSequence
            if (r11 == 0) goto L_0x0120
            r11 = r6
            java.lang.String r11 = r11.toString()
            r9 = r11
            r11 = r9
            r12 = r3
            gnu.expr.ModuleInfo r11 = lookupModuleFromSourcePath(r11, r12)
            r10 = r11
            r11 = r10
            if (r11 != 0) goto L_0x0112
            r11 = r4
            r12 = 101(0x65, float:1.42E-43)
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r17 = r13
            r13 = r17
            r14 = r17
            r14.<init>()
            java.lang.String r14 = "malformed URL: "
            java.lang.StringBuilder r13 = r13.append(r14)
            r14 = r9
            java.lang.StringBuilder r13 = r13.append(r14)
            java.lang.String r13 = r13.toString()
            r11.error(r12, r13)
            r11 = 0
            r0 = r11
            goto L_0x001d
        L_0x0112:
            r11 = 0
            r12 = r10
            r13 = 0
            r14 = r2
            r15 = r3
            r16 = r4
            boolean r11 = importDefinitions(r11, r12, r13, r14, r15, r16)
            r0 = r11
            goto L_0x001d
        L_0x0120:
            r11 = r6
            boolean r11 = r11 instanceof gnu.mapping.Symbol
            if (r11 == 0) goto L_0x00c6
            r11 = r4
            r12 = r6
            boolean r11 = r11.selfEvaluatingSymbol(r12)
            if (r11 != 0) goto L_0x00c6
            r11 = r4
            gnu.expr.Language r11 = r11.getLanguage()
            r12 = r4
            r13 = r6
            r14 = 0
            gnu.expr.Expression r12 = r12.rewrite(r13, r14)
            gnu.bytecode.Type r11 = r11.getTypeFor((gnu.expr.Expression) r12)
            r7 = r11
            r11 = r7
            boolean r11 = r11 instanceof gnu.bytecode.ClassType
            if (r11 == 0) goto L_0x00c6
            r11 = r5
            java.lang.Object r11 = r11.getCdr()
            boolean r11 = r11 instanceof gnu.lists.Pair
            if (r11 == 0) goto L_0x00c6
            r11 = r5
            java.lang.Object r11 = r11.getCdr()
            gnu.lists.Pair r11 = (gnu.lists.Pair) r11
            java.lang.Object r11 = r11.getCar()
            r6 = r11
            r11 = r6
            boolean r11 = r11 instanceof java.lang.CharSequence
            if (r11 == 0) goto L_0x00c6
            r11 = r6
            java.lang.String r11 = r11.toString()
            r9 = r11
            r11 = r9
            r12 = r3
            gnu.expr.ModuleInfo r11 = lookupModuleFromSourcePath(r11, r12)
            r10 = r11
            r11 = r10
            if (r11 != 0) goto L_0x0192
            r11 = r4
            r12 = 101(0x65, float:1.42E-43)
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r17 = r13
            r13 = r17
            r14 = r17
            r14.<init>()
            java.lang.String r14 = "malformed URL: "
            java.lang.StringBuilder r13 = r13.append(r14)
            r14 = r9
            java.lang.StringBuilder r13 = r13.append(r14)
            java.lang.String r13 = r13.toString()
            r11.error(r12, r13)
            r11 = 0
            r0 = r11
            goto L_0x001d
        L_0x0192:
            r11 = r7
            java.lang.String r11 = r11.getName()
            r12 = r10
            r13 = 0
            r14 = r2
            r15 = r3
            r16 = r4
            boolean r11 = importDefinitions(r11, r12, r13, r14, r15, r16)
            r0 = r11
            goto L_0x001d
        L_0x01a4:
            r11 = r7
            gnu.bytecode.ClassType r11 = (gnu.bytecode.ClassType) r11     // Catch:{ Exception -> 0x01bb }
            gnu.expr.ModuleInfo r11 = gnu.expr.ModuleInfo.find(r11)     // Catch:{ Exception -> 0x01bb }
            r9 = r11
            r11 = 0
            r12 = r9
            r13 = 0
            r14 = r2
            r15 = r3
            r16 = r4
            boolean r11 = importDefinitions(r11, r12, r13, r14, r15, r16)
            r11 = 1
            r0 = r11
            goto L_0x001d
        L_0x01bb:
            r11 = move-exception
            r10 = r11
            r11 = r4
            r12 = 101(0x65, float:1.42E-43)
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r17 = r13
            r13 = r17
            r14 = r17
            r14.<init>()
            java.lang.String r14 = "unknown class "
            java.lang.StringBuilder r13 = r13.append(r14)
            r14 = r7
            java.lang.String r14 = r14.getName()
            java.lang.StringBuilder r13 = r13.append(r14)
            java.lang.String r13 = r13.toString()
            r11.error(r12, r13)
            r11 = 0
            r0 = r11
            goto L_0x001d
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.standard.require.scanForDefinitions(gnu.lists.Pair, java.util.Vector, gnu.expr.ScopeExp, kawa.lang.Translator):boolean");
    }

    public static ModuleInfo lookupModuleFromSourcePath(String str, ScopeExp defs) {
        String sourceName = str;
        ModuleManager manager = ModuleManager.getInstance();
        String baseName = defs.getFileName();
        if (baseName != null) {
            sourceName = Path.valueOf(baseName).resolve(sourceName).toString();
        }
        return manager.findWithSourcePath(sourceName);
    }

    public static boolean importDefinitions(String str, ModuleInfo moduleInfo, Procedure procedure, Vector vector, ScopeExp scopeExp, Compilation compilation) {
        boolean isSubtype;
        Expression expression;
        Vector vector2;
        Expression expression2;
        Expression expression3;
        Expression expression4;
        Expression expression5;
        Expression expression6;
        StringBuilder sb;
        Declaration adecl;
        ReferenceExp referenceExp;
        SetExp setExp;
        StringBuilder sb2;
        Declaration declaration;
        SetExp setExp2;
        Throwable th;
        StringBuilder sb3;
        Throwable th2;
        StringBuilder sb4;
        StringBuilder sb5;
        StringBuilder sb6;
        String className = str;
        ModuleInfo info = moduleInfo;
        Procedure renamer = procedure;
        Vector forms = vector;
        ScopeExp defs = scopeExp;
        Compilation tr = compilation;
        ModuleManager manager = ModuleManager.getInstance();
        if ((info.getState() & 1) == 0 && info.getCompilation() == null) {
            long currentTimeMillis = System.currentTimeMillis();
            long j = currentTimeMillis;
            if (!info.checkCurrent(manager, currentTimeMillis)) {
                SourceMessages messages = tr.getMessages();
                Language language = Language.getDefaultLanguage();
                try {
                    InPort fstream = InPort.openFile(info.getSourceAbsPath());
                    info.clearClass();
                    info.setClassName(className);
                    int options = 8;
                    if (tr.immediate) {
                        options = 8 | 1;
                    }
                    Compilation comp = language.parse(fstream, messages, options, info);
                    info.setClassName(comp.getModule().classFor(comp).getName());
                } catch (FileNotFoundException e) {
                    new StringBuilder();
                    tr.error('e', sb6.append("not found: ").append(e.getMessage()).toString());
                    return false;
                } catch (IOException e2) {
                    new StringBuilder();
                    tr.error('e', sb5.append("caught ").append(e2).toString());
                    return false;
                } catch (SyntaxException e3) {
                    SyntaxException ex = e3;
                    if (ex.getMessages() == messages) {
                        return false;
                    }
                    Throwable th3 = th2;
                    new StringBuilder();
                    new RuntimeException(sb4.append("confussing syntax error: ").append(ex).toString());
                    throw th3;
                }
            }
        }
        if (tr.minfo != null && tr.getState() < 4) {
            tr.minfo.addDependency(info);
            if (!info.loadEager(12) && info.getState() < 6) {
                tr.pushPendingImport(info, defs, forms.size());
                return true;
            }
        }
        ClassType type = info.getClassType();
        String tname = type.getName();
        boolean sharedModule = tr.sharedModuleDefs();
        if (info.getState() < 6) {
            isSubtype = info.getCompilation().makeRunnable();
        } else {
            isSubtype = type.isSubtype(Compilation.typeRunnable);
        }
        boolean isRunnable = isSubtype;
        Declaration decl = null;
        ClassType thisType = ClassType.make("kawa.standard.require");
        new QuoteExp(tname);
        Expression dofind = Invoke.makeInvokeStatic(thisType, "find", new Expression[]{expression});
        Field instanceField = null;
        Language language2 = tr.getLanguage();
        dofind.setLine(tr);
        int formsStart = forms.size();
        ModuleExp mod = info.setupModuleExp();
        new Vector();
        Vector declPairs = vector2;
        Declaration firstDecl = mod.firstDecl();
        while (true) {
            Declaration fdecl = firstDecl;
            if (fdecl == null) {
                break;
            }
            if (!fdecl.isPrivate()) {
                Symbol aname = (Symbol) fdecl.getSymbol();
                if (renamer != null) {
                    try {
                        th = renamer.apply1(aname);
                    } catch (Throwable th4) {
                        th = th4;
                    }
                    if (th != null) {
                        if (!(th instanceof Symbol)) {
                            new StringBuilder();
                            tr.error('e', sb3.append("internal error - import name mapper returned non-symbol: ").append(th.getClass().getName()).toString());
                        } else {
                            aname = (Symbol) th;
                        }
                    }
                }
                boolean isStatic = fdecl.getFlag(PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH);
                if (!isStatic && decl == null) {
                    new StringBuilder();
                    new Declaration((Object) SimpleSymbol.valueOf(sb2.append(tname.replace('.', '$')).append("$instance").toString()), (Type) type);
                    decl = declaration;
                    decl.setPrivate(true);
                    decl.setFlag(1073758208);
                    defs.addDeclaration(decl);
                    decl.noteValue(dofind);
                    new SetExp(decl, dofind);
                    SetExp sexp = setExp2;
                    sexp.setLine(tr);
                    sexp.setDefining(true);
                    forms.addElement(sexp);
                    formsStart = forms.size();
                    decl.setFlag(536870912);
                    if (isRunnable) {
                        decl.setSimple(false);
                    }
                    decl.setFlag(PlaybackStateCompat.ACTION_PLAY_FROM_URI);
                }
                if (fdecl.field == null || !fdecl.field.getName().equals("$instance")) {
                    boolean isImportedInstance = fdecl.field != null && fdecl.field.getName().endsWith("$instance");
                    Declaration old = defs.lookup(aname, language2, language2.getNamespaceOf(fdecl));
                    if (isImportedInstance) {
                        if (old == null) {
                            adecl = defs.addDeclaration((Object) aname);
                            adecl.setFlag(1073758208);
                            adecl.setType(fdecl.getType());
                            adecl.setFlag(PlaybackStateCompat.ACTION_PLAY_FROM_URI);
                        }
                    } else if (old == null || old.getFlag(512) || Declaration.followAliases(old) != Declaration.followAliases(fdecl)) {
                        if (old == null || !old.getFlag(66048)) {
                            adecl = defs.addDeclaration((Object) aname);
                            if (old != null) {
                                ScopeExp.duplicateDeclarationError(old, adecl, tr);
                            }
                        } else {
                            old.setFlag(false, 66048);
                            adecl = old;
                        }
                        adecl.setAlias(true);
                        adecl.setIndirectBinding(true);
                    }
                    adecl.setLocation(tr);
                    new ReferenceExp(fdecl);
                    ReferenceExp fref = referenceExp;
                    fref.setContextDecl(decl);
                    if (!isImportedInstance) {
                        fref.setDontDereference(true);
                        if (!sharedModule) {
                            adecl.setPrivate(true);
                        }
                    }
                    adecl.setFlag(16384);
                    if (fdecl.getFlag(PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID)) {
                        adecl.setFlag(PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID);
                    }
                    if (fdecl.isProcedureDecl()) {
                        adecl.setProcedureDecl(true);
                    }
                    if (isStatic) {
                        adecl.setFlag(PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH);
                    }
                    new SetExp(adecl, (Expression) fref);
                    SetExp sexp2 = setExp;
                    adecl.setFlag(536870912);
                    sexp2.setDefining(true);
                    if (isImportedInstance) {
                        forms.insertElementAt(sexp2, formsStart);
                        formsStart++;
                    } else {
                        forms.addElement(sexp2);
                    }
                    boolean add = declPairs.add(adecl);
                    boolean add2 = declPairs.add(fdecl);
                    adecl.noteValue(fref);
                    adecl.setFlag(PlaybackStateCompat.ACTION_PREPARE_FROM_URI);
                    tr.push(adecl);
                } else {
                    instanceField = fdecl.field;
                }
            }
            firstDecl = fdecl.nextDecl();
        }
        int ndecls = declPairs.size();
        for (int i = 0; i < ndecls; i += 2) {
            Declaration adecl2 = (Declaration) declPairs.elementAt(i);
            Declaration fdecl2 = (Declaration) declPairs.elementAt(i + 1);
            Expression fval = fdecl2.getValue();
            if (fdecl2.isIndirectBinding() && (fval instanceof ReferenceExp)) {
                ReferenceExp aref = (ReferenceExp) adecl2.getValue();
                Declaration xdecl = ((ReferenceExp) fval).getBinding();
                aref.setBinding(xdecl);
                if (xdecl.needsContext()) {
                    new StringBuilder();
                    Declaration cdecl = defs.lookup(SimpleSymbol.valueOf(sb.append(xdecl.field.getDeclaringClass().getName().replace('.', '$')).append("$instance").toString()));
                    cdecl.setFlag(PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID);
                    aref.setContextDecl(cdecl);
                }
            }
        }
        if (isRunnable) {
            Method run = Compilation.typeRunnable.getDeclaredMethod("run", 0);
            if (decl != null) {
                new ReferenceExp(decl);
                dofind = expression6;
            } else if (instanceField != null) {
                Expression[] expressionArr = new Expression[2];
                new QuoteExp(type);
                expressionArr[0] = expression2;
                Expression[] args = expressionArr;
                new QuoteExp("$instance");
                args[1] = expression3;
                new ApplyExp((Procedure) SlotGet.staticField, args);
                dofind = expression4;
            }
            new ApplyExp(run, dofind);
            Expression dofind2 = expression5;
            dofind2.setLine(tr);
            forms.addElement(dofind2);
        }
        return true;
    }

    public Expression rewriteForm(Pair pair, Translator translator) {
        Pair pair2 = pair;
        Translator translator2 = translator;
        return null;
    }
}

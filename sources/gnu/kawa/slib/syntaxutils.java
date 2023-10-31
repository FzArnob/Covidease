package gnu.kawa.slib;

import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.BeginExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.IfExp;
import gnu.expr.Keyword;
import gnu.expr.LambdaExp;
import gnu.expr.Language;
import gnu.expr.LetExp;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleExp;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.expr.NameLookup;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.SetExp;
import gnu.expr.Special;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.Format;
import gnu.kawa.functions.GetNamedPart;
import gnu.kawa.lispexpr.LangObjType;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.lists.Consumer;
import gnu.lists.EofClass;
import gnu.lists.LList;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.math.Numeric;
import gnu.text.Char;
import gnu.text.SourceMessages;
import kawa.lang.Macro;
import kawa.lang.Quote;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lang.Translator;
import kawa.lib.C1245lists;
import kawa.lib.misc;
import kawa.standard.Scheme;

/* compiled from: syntaxutils.scm */
public class syntaxutils extends ModuleBody {
    public static final Macro $Prvt$$Ex = Macro.make(Lit15, Lit16, $instance);
    public static final Macro $Prvt$typecase$Pc = Macro.make(Lit13, Lit14, $instance);
    public static final syntaxutils $instance;
    static final Keyword Lit0 = Keyword.make("env");
    static final PairWithPosition Lit1 = PairWithPosition.make(Lit21, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/syntaxutils.scm", 278557);
    static final PairWithPosition Lit10 = PairWithPosition.make(Lit19, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/syntaxutils.scm", 552972);
    static final PairWithPosition Lit11 = PairWithPosition.make(Lit25, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/syntaxutils.scm", 626704);
    static final PairWithPosition Lit12;
    static final SimpleSymbol Lit13;
    static final SyntaxRules Lit14;
    static final SimpleSymbol Lit15;
    static final SyntaxRules Lit16;
    static final SimpleSymbol Lit17;
    static final SimpleSymbol Lit18;
    static final SimpleSymbol Lit19;
    static final Keyword Lit2 = Keyword.make("lang");
    static final SimpleSymbol Lit20;
    static final SimpleSymbol Lit21;
    static final SimpleSymbol Lit22;
    static final SimpleSymbol Lit23;
    static final SimpleSymbol Lit24;
    static final SimpleSymbol Lit25;
    static final SimpleSymbol Lit26;
    static final PairWithPosition Lit3;
    static final PairWithPosition Lit4 = PairWithPosition.make(Lit26, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/syntaxutils.scm", 376839);
    static final PairWithPosition Lit5 = PairWithPosition.make(Lit21, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/syntaxutils.scm", 409627);
    static final PairWithPosition Lit6;
    static final IntNum Lit7 = IntNum.make(0);
    static final IntNum Lit8 = IntNum.make(1);
    static final PairWithPosition Lit9 = PairWithPosition.make(Lit23, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/syntaxutils.scm", 479236);
    public static final ModuleMethod expand;

    /* compiled from: syntaxutils.scm */
    public class frame extends ModuleBody {
        LList pack;

        public frame() {
        }
    }

    /* compiled from: syntaxutils.scm */
    public class frame0 extends ModuleBody {
        LList pack;

        public frame0() {
        }
    }

    /* compiled from: syntaxutils.scm */
    public class frame1 extends ModuleBody {
        LList pack;

        public frame1() {
        }
    }

    static {
        SimpleSymbol simpleSymbol;
        SimpleSymbol simpleSymbol2;
        SimpleSymbol simpleSymbol3;
        SimpleSymbol simpleSymbol4;
        SimpleSymbol simpleSymbol5;
        SimpleSymbol simpleSymbol6;
        SimpleSymbol simpleSymbol7;
        SimpleSymbol simpleSymbol8;
        SimpleSymbol simpleSymbol9;
        SimpleSymbol simpleSymbol10;
        SyntaxRules syntaxRules;
        SimpleSymbol simpleSymbol11;
        SyntaxRule syntaxRule;
        SyntaxPattern syntaxPattern;
        SimpleSymbol simpleSymbol12;
        SyntaxRules syntaxRules2;
        SimpleSymbol simpleSymbol13;
        SyntaxRule syntaxRule2;
        SyntaxPattern syntaxPattern2;
        SyntaxRule syntaxRule3;
        SyntaxPattern syntaxPattern3;
        SimpleSymbol simpleSymbol14;
        SyntaxRule syntaxRule4;
        SyntaxPattern syntaxPattern4;
        SyntaxRule syntaxRule5;
        SyntaxPattern syntaxPattern5;
        SimpleSymbol simpleSymbol15;
        SyntaxRule syntaxRule6;
        SyntaxPattern syntaxPattern6;
        SimpleSymbol simpleSymbol16;
        SimpleSymbol simpleSymbol17;
        SyntaxRule syntaxRule7;
        SyntaxPattern syntaxPattern7;
        SimpleSymbol simpleSymbol18;
        SimpleSymbol simpleSymbol19;
        SimpleSymbol simpleSymbol20;
        SimpleSymbol simpleSymbol21;
        SimpleSymbol simpleSymbol22;
        SimpleSymbol simpleSymbol23;
        syntaxutils syntaxutils;
        ModuleMethod moduleMethod;
        new SimpleSymbol("lambda");
        Lit26 = (SimpleSymbol) simpleSymbol.readResolve();
        new SimpleSymbol("as");
        Lit25 = (SimpleSymbol) simpleSymbol2.readResolve();
        new SimpleSymbol("else");
        Lit24 = (SimpleSymbol) simpleSymbol3.readResolve();
        new SimpleSymbol("let");
        Lit23 = (SimpleSymbol) simpleSymbol4.readResolve();
        new SimpleSymbol("cond");
        Lit22 = (SimpleSymbol) simpleSymbol5.readResolve();
        new SimpleSymbol("begin");
        Lit21 = (SimpleSymbol) simpleSymbol6.readResolve();
        new SimpleSymbol("or");
        Lit20 = (SimpleSymbol) simpleSymbol7.readResolve();
        new SimpleSymbol(LispLanguage.quote_sym);
        Lit19 = (SimpleSymbol) simpleSymbol8.readResolve();
        new SimpleSymbol("eql");
        Lit18 = (SimpleSymbol) simpleSymbol9.readResolve();
        new SimpleSymbol("expand");
        Lit17 = (SimpleSymbol) simpleSymbol10.readResolve();
        SyntaxRules syntaxRules3 = syntaxRules;
        Object[] objArr = new Object[1];
        Object[] objArr2 = objArr;
        Object[] objArr3 = objArr;
        new SimpleSymbol("!");
        SimpleSymbol simpleSymbol24 = (SimpleSymbol) simpleSymbol11.readResolve();
        Lit15 = simpleSymbol24;
        objArr3[0] = simpleSymbol24;
        SyntaxRule[] syntaxRuleArr = new SyntaxRule[1];
        SyntaxRule[] syntaxRuleArr2 = syntaxRuleArr;
        SyntaxRule[] syntaxRuleArr3 = syntaxRuleArr;
        SyntaxRule syntaxRule8 = syntaxRule;
        SyntaxPattern syntaxPattern8 = syntaxPattern;
        new SyntaxPattern("\f\u0018\f\u0007\f\u000f\r\u0017\u0010\b\b", new Object[0], 3);
        Object[] objArr4 = new Object[2];
        new SimpleSymbol("invoke");
        objArr4[0] = (SimpleSymbol) simpleSymbol12.readResolve();
        Object[] objArr5 = objArr4;
        objArr5[1] = Lit19;
        new SyntaxRule(syntaxPattern8, "\u0001\u0001\u0003", "\u0011\u0018\u0004\t\u000b)\u0011\u0018\f\b\u0003\b\u0015\u0013", objArr5, 1);
        syntaxRuleArr3[0] = syntaxRule8;
        new SyntaxRules(objArr2, syntaxRuleArr2, 3);
        Lit16 = syntaxRules3;
        SyntaxRules syntaxRules4 = syntaxRules2;
        Object[] objArr6 = new Object[3];
        Object[] objArr7 = objArr6;
        Object[] objArr8 = objArr6;
        new SimpleSymbol("typecase%");
        SimpleSymbol simpleSymbol25 = (SimpleSymbol) simpleSymbol13.readResolve();
        Lit13 = simpleSymbol25;
        objArr8[0] = simpleSymbol25;
        Object[] objArr9 = objArr7;
        objArr9[1] = Lit18;
        Object[] objArr10 = objArr9;
        Object[] objArr11 = objArr10;
        objArr10[2] = Lit20;
        SyntaxRule[] syntaxRuleArr4 = new SyntaxRule[6];
        SyntaxRule[] syntaxRuleArr5 = syntaxRuleArr4;
        SyntaxRule[] syntaxRuleArr6 = syntaxRuleArr4;
        SyntaxRule syntaxRule9 = syntaxRule2;
        SyntaxPattern syntaxPattern9 = syntaxPattern2;
        new SyntaxPattern("\f\u0018\f\u0007<\f\u0002\r\u000f\b\b\b\r\u0017\u0010\b\b", new Object[]{Boolean.TRUE}, 3);
        new SyntaxRule(syntaxPattern9, "\u0001\u0003\u0003", "\u0011\u0018\u0004\b\r\u000b", new Object[]{Lit21}, 1);
        syntaxRuleArr6[0] = syntaxRule9;
        SyntaxRule[] syntaxRuleArr7 = syntaxRuleArr5;
        SyntaxRule[] syntaxRuleArr8 = syntaxRuleArr7;
        SyntaxRule[] syntaxRuleArr9 = syntaxRuleArr7;
        SyntaxRule syntaxRule10 = syntaxRule3;
        SyntaxPattern syntaxPattern10 = syntaxPattern3;
        new SyntaxPattern("\f\u0018\f\u0007\\,\f\u0002\f\u000f\b\r\u0017\u0010\b\b\r\u001f\u0018\b\b", new Object[]{Lit18}, 4);
        Object[] objArr12 = new Object[5];
        objArr12[0] = Lit22;
        Object[] objArr13 = objArr12;
        new SimpleSymbol("eqv?");
        objArr13[1] = (SimpleSymbol) simpleSymbol14.readResolve();
        Object[] objArr14 = objArr13;
        objArr14[2] = Lit19;
        Object[] objArr15 = objArr14;
        objArr15[3] = Lit24;
        Object[] objArr16 = objArr15;
        objArr16[4] = Lit13;
        new SyntaxRule(syntaxPattern10, "\u0001\u0001\u0003\u0003", "\u0011\u0018\u0004yY\u0011\u0018\f\t\u0003\b\u0011\u0018\u0014\b\u000b\b\u0015\u0013\b\u0011\u0018\u001c\b\u0011\u0018$\t\u0003\b\u001d\u001b", objArr16, 1);
        syntaxRuleArr9[1] = syntaxRule10;
        SyntaxRule[] syntaxRuleArr10 = syntaxRuleArr8;
        SyntaxRule[] syntaxRuleArr11 = syntaxRuleArr10;
        SyntaxRule[] syntaxRuleArr12 = syntaxRuleArr10;
        SyntaxRule syntaxRule11 = syntaxRule4;
        SyntaxPattern syntaxPattern11 = syntaxPattern4;
        new SyntaxPattern("\f\u0018\f\u0007\\,\f\u0002\f\u000f\b\r\u0017\u0010\b\b\r\u001f\u0018\b\b", new Object[]{Lit20}, 4);
        new SyntaxRule(syntaxPattern11, "\u0001\u0001\u0003\u0003", "\u0011\u0018\u0004\t\u0003)\t\u000b\b\u0015\u0013\b\u001d\u001b", new Object[]{Lit13}, 1);
        syntaxRuleArr12[2] = syntaxRule11;
        SyntaxRule[] syntaxRuleArr13 = syntaxRuleArr11;
        SyntaxRule[] syntaxRuleArr14 = syntaxRuleArr13;
        SyntaxRule[] syntaxRuleArr15 = syntaxRuleArr13;
        SyntaxRule syntaxRule12 = syntaxRule5;
        SyntaxPattern syntaxPattern12 = syntaxPattern5;
        new SyntaxPattern("\f\u0018\f\u0007l<\f\u0002\r\u000f\b\b\b\r\u0017\u0010\b\b\r\u001f\u0018\b\b", new Object[]{Lit20}, 4);
        Object[] objArr17 = new Object[6];
        objArr17[0] = Lit23;
        Object[] objArr18 = objArr17;
        new SimpleSymbol("f");
        objArr18[1] = (SimpleSymbol) simpleSymbol15.readResolve();
        Object[] objArr19 = objArr18;
        objArr19[2] = Lit26;
        Object[] objArr20 = objArr19;
        objArr20[3] = Lit21;
        Object[] objArr21 = objArr20;
        objArr21[4] = Lit13;
        Object[] objArr22 = objArr21;
        objArr22[5] = Boolean.TRUE;
        new SyntaxRule(syntaxPattern12, "\u0001\u0003\u0003\u0003", "\u0011\u0018\u0004\b\u0011\u0018\f\b\u0011\u0018\u0014\u0011\b\u0003\b\u0011\u0018\u001c\b\u0015\u0013\b\u0011\u0018$\t\u0003I\r\t\u000b\b\u0011\u0018\f\b\u0003\b\u0011\u0018,\b\u0011\u0018$\t\u0003\b\u001d\u001b", objArr22, 1);
        syntaxRuleArr15[3] = syntaxRule12;
        SyntaxRule[] syntaxRuleArr16 = syntaxRuleArr14;
        SyntaxRule[] syntaxRuleArr17 = syntaxRuleArr16;
        SyntaxRule[] syntaxRuleArr18 = syntaxRuleArr16;
        SyntaxRule syntaxRule13 = syntaxRule6;
        SyntaxPattern syntaxPattern13 = syntaxPattern6;
        new SyntaxPattern("\f\u0018\f\u0007<\f\u000f\r\u0017\u0010\b\b\r\u001f\u0018\b\b", new Object[0], 4);
        Object[] objArr23 = new Object[7];
        objArr23[0] = Lit22;
        Object[] objArr24 = objArr23;
        new SimpleSymbol(GetNamedPart.INSTANCEOF_METHOD_NAME);
        objArr24[1] = (SimpleSymbol) simpleSymbol16.readResolve();
        Object[] objArr25 = objArr24;
        objArr25[2] = Lit23;
        Object[] objArr26 = objArr25;
        new SimpleSymbol("::");
        objArr26[3] = (SimpleSymbol) simpleSymbol17.readResolve();
        Object[] objArr27 = objArr26;
        objArr27[4] = Lit21;
        Object[] objArr28 = objArr27;
        objArr28[5] = Lit24;
        Object[] objArr29 = objArr28;
        objArr29[6] = Lit13;
        new SyntaxRule(syntaxPattern13, "\u0001\u0001\u0003\u0003", "\u0011\u0018\u0004ñ9\u0011\u0018\f\t\u0003\b\u000b\b\u0011\u0018\u0014Q\b\t\u0003\u0011\u0018\u001c\t\u000b\b\u0003\b\u0011\u0018$\b\u0015\u0013\b\u0011\u0018,\b\u0011\u00184\t\u0003\b\u001d\u001b", objArr29, 1);
        syntaxRuleArr18[4] = syntaxRule13;
        SyntaxRule[] syntaxRuleArr19 = syntaxRuleArr17;
        SyntaxRule[] syntaxRuleArr20 = syntaxRuleArr19;
        SyntaxRule[] syntaxRuleArr21 = syntaxRuleArr19;
        SyntaxRule syntaxRule14 = syntaxRule7;
        SyntaxPattern syntaxPattern14 = syntaxPattern7;
        new SyntaxPattern("\f\u0018\f\u0007\b", new Object[0], 1);
        Object[] objArr30 = new Object[6];
        new SimpleSymbol("error");
        objArr30[0] = (SimpleSymbol) simpleSymbol18.readResolve();
        Object[] objArr31 = objArr30;
        objArr31[1] = "typecase% failed";
        Object[] objArr32 = objArr31;
        objArr32[2] = Lit15;
        Object[] objArr33 = objArr32;
        new SimpleSymbol("getClass");
        objArr33[3] = (SimpleSymbol) simpleSymbol19.readResolve();
        Object[] objArr34 = objArr33;
        objArr34[4] = Lit25;
        Object[] objArr35 = objArr34;
        new SimpleSymbol("<object>");
        objArr35[5] = (SimpleSymbol) simpleSymbol20.readResolve();
        new SyntaxRule(syntaxPattern14, "\u0001", "\u0011\u0018\u0004\u0011\u0018\f\t\u0003\b\u0011\u0018\u0014\u0011\u0018\u001c\b\u0011\u0018$\u0011\u0018,\b\u0003", objArr35, 0);
        syntaxRuleArr21[5] = syntaxRule14;
        new SyntaxRules(objArr11, syntaxRuleArr20, 4);
        Lit14 = syntaxRules4;
        new SimpleSymbol(":");
        Lit12 = PairWithPosition.make((SimpleSymbol) simpleSymbol21.readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/syntaxutils.scm", 634896);
        new SimpleSymbol("if");
        Lit6 = PairWithPosition.make((SimpleSymbol) simpleSymbol22.readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/syntaxutils.scm", 417799);
        new SimpleSymbol("set");
        Lit3 = PairWithPosition.make((SimpleSymbol) simpleSymbol23.readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/syntaxutils.scm", 368647);
        new syntaxutils();
        $instance = syntaxutils;
        new ModuleMethod($instance, 1, Lit17, -4095);
        expand = moduleMethod;
        $instance.run();
    }

    public syntaxutils() {
        ModuleInfo.register(this);
    }

    public final void run(CallContext $ctx) {
        Consumer consumer = $ctx.consumer;
    }

    public static Object expand$V(Object obj, Object[] argsArray) {
        Object sexp = obj;
        Object searchForKeyword = Keyword.searchForKeyword(argsArray, 0, Lit0);
        Object obj2 = searchForKeyword;
        if (searchForKeyword == Special.dfault) {
            obj2 = misc.interactionEnvironment();
        }
        Object env = obj2;
        Object[] objArr = new Object[2];
        objArr[0] = Lit1;
        Object[] objArr2 = objArr;
        Object[] objArr3 = objArr2;
        Object[] objArr4 = objArr2;
        Object[] objArr5 = new Object[2];
        objArr5[0] = sexp;
        Object[] objArr6 = objArr5;
        objArr6[1] = LList.Empty;
        objArr4[1] = Quote.consX$V(objArr6);
        Object append$V = Quote.append$V(objArr3);
        Object[] objArr7 = new Object[2];
        objArr7[0] = Lit0;
        Object[] objArr8 = objArr7;
        objArr8[1] = env;
        return unrewrite(rewriteForm$V(append$V, objArr8));
    }

    public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object[] objArr2 = objArr;
        if (moduleMethod2.selector != 1) {
            return super.applyN(moduleMethod2, objArr2);
        }
        Object obj = objArr2[0];
        int length = objArr2.length - 1;
        Object[] objArr3 = new Object[length];
        while (true) {
            length--;
            if (length < 0) {
                return expand$V(obj, objArr3);
            }
            Object[] objArr4 = objArr3;
            objArr3 = objArr4;
            objArr4[length] = objArr2[length + 1];
        }
    }

    public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object[] objArr2 = objArr;
        CallContext callContext2 = callContext;
        if (moduleMethod2.selector != 1) {
            return super.matchN(moduleMethod2, objArr2, callContext2);
        }
        callContext2.values = objArr2;
        callContext2.proc = moduleMethod2;
        callContext2.f239pc = 5;
        return 0;
    }

    /* JADX INFO: finally extract failed */
    static Expression rewriteForm$V(Object obj, Object[] objArr) {
        Throwable th;
        Throwable th2;
        SourceMessages sourceMessages;
        Throwable th3;
        Object exp = obj;
        Object[] argsArray = objArr;
        Object searchForKeyword = Keyword.searchForKeyword(argsArray, 0, Lit2);
        Object obj2 = searchForKeyword;
        if (searchForKeyword == Special.dfault) {
            obj2 = Language.getDefaultLanguage();
        }
        Object lang = obj2;
        Object searchForKeyword2 = Keyword.searchForKeyword(argsArray, 0, Lit0);
        Object env = searchForKeyword2;
        if (searchForKeyword2 == Special.dfault) {
            env = misc.interactionEnvironment();
        }
        Object obj3 = env;
        Object obj4 = obj3;
        try {
            Environment environment = (Environment) obj3;
            Object obj5 = lang;
            Object obj6 = obj5;
            try {
                NameLookup namelookup = NameLookup.getInstance(environment, (Language) obj5);
                new SourceMessages();
                SourceMessages messages = sourceMessages;
                Translator translator = r17;
                Object obj7 = lang;
                Object obj8 = obj7;
                try {
                    Translator translator2 = new Translator((Language) obj7, messages, namelookup);
                    Translator translator3 = translator;
                    ModuleExp pushNewModule = translator3.pushNewModule((String) null);
                    Compilation saved$Mncomp = Compilation.setSaveCurrent(translator3);
                    try {
                        Expression rewrite = translator3.rewrite(exp);
                        Compilation.restoreCurrent(saved$Mncomp);
                        return rewrite;
                    } catch (Throwable th4) {
                        Throwable th5 = th4;
                        Compilation.restoreCurrent(saved$Mncomp);
                        throw th5;
                    }
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th6 = th3;
                    new WrongType(classCastException, "kawa.lang.Translator.<init>(gnu.expr.Language,gnu.text.SourceMessages,gnu.expr.NameLookup)", 1, obj8);
                    throw th6;
                }
            } catch (ClassCastException e2) {
                ClassCastException classCastException2 = e2;
                Throwable th7 = th2;
                new WrongType(classCastException2, "gnu.expr.NameLookup.getInstance(gnu.mapping.Environment,gnu.expr.Language)", 2, obj6);
                throw th7;
            }
        } catch (ClassCastException e3) {
            ClassCastException classCastException3 = e3;
            Throwable th8 = th;
            new WrongType(classCastException3, "gnu.expr.NameLookup.getInstance(gnu.mapping.Environment,gnu.expr.Language)", 1, obj4);
            throw th8;
        }
    }

    static Object unrewrite(Expression expression) {
        frame frame2;
        Object obj;
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        Throwable th7;
        Throwable th8;
        Expression exp = expression;
        new frame();
        frame frame3 = frame2;
        if (exp instanceof LetExp) {
            Expression expression2 = exp;
            Expression expression3 = expression2;
            try {
                obj = unrewriteLet((LetExp) expression2);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th9 = th8;
                new WrongType(classCastException, "exp", -2, (Object) expression3);
                throw th9;
            }
        } else if (exp instanceof QuoteExp) {
            Expression expression4 = exp;
            Expression expression5 = expression4;
            try {
                obj = unrewriteQuote((QuoteExp) expression4);
            } catch (ClassCastException e2) {
                ClassCastException classCastException2 = e2;
                Throwable th10 = th7;
                new WrongType(classCastException2, "exp", -2, (Object) expression5);
                throw th10;
            }
        } else if (exp instanceof SetExp) {
            Expression expression6 = exp;
            Expression expression7 = expression6;
            try {
                SetExp exp2 = (SetExp) expression6;
                Object[] objArr = new Object[2];
                objArr[0] = Lit3;
                Object[] objArr2 = objArr;
                Object[] objArr3 = objArr2;
                Object[] objArr4 = objArr2;
                Object[] objArr5 = new Object[2];
                objArr5[0] = exp2.getSymbol();
                Object[] objArr6 = objArr5;
                Object[] objArr7 = objArr6;
                Object[] objArr8 = objArr6;
                Object[] objArr9 = new Object[2];
                objArr9[0] = unrewrite(exp2.getNewValue());
                Object[] objArr10 = objArr9;
                objArr10[1] = LList.Empty;
                objArr8[1] = Quote.consX$V(objArr10);
                objArr4[1] = Quote.consX$V(objArr7);
                obj = Quote.append$V(objArr3);
            } catch (ClassCastException e3) {
                ClassCastException classCastException3 = e3;
                Throwable th11 = th6;
                new WrongType(classCastException3, "exp", -2, (Object) expression7);
                throw th11;
            }
        } else if (exp instanceof LambdaExp) {
            Expression expression8 = exp;
            Expression expression9 = expression8;
            try {
                LambdaExp exp3 = (LambdaExp) expression8;
                Object[] objArr11 = new Object[2];
                objArr11[0] = Lit4;
                Object[] objArr12 = objArr11;
                Object[] objArr13 = objArr12;
                Object[] objArr14 = objArr12;
                Object[] objArr15 = new Object[2];
                Object[] objArr16 = objArr15;
                Object[] objArr17 = objArr15;
                frame3.pack = LList.Empty;
                Declaration firstDecl = exp3.firstDecl();
                while (true) {
                    Declaration decl = firstDecl;
                    frame frame4 = frame3;
                    if (decl == null) {
                        break;
                    }
                    frame3.pack = C1245lists.cons(decl.getSymbol(), frame3.pack);
                    firstDecl = decl.nextDecl();
                }
                objArr17[0] = C1245lists.reverse$Ex(frame3.pack);
                Object[] objArr18 = objArr16;
                Object[] objArr19 = objArr18;
                Object[] objArr20 = objArr18;
                Object[] objArr21 = new Object[2];
                objArr21[0] = unrewrite(exp3.body);
                Object[] objArr22 = objArr21;
                objArr22[1] = LList.Empty;
                objArr20[1] = Quote.consX$V(objArr22);
                objArr14[1] = Quote.consX$V(objArr19);
                obj = Quote.append$V(objArr13);
            } catch (ClassCastException e4) {
                ClassCastException classCastException4 = e4;
                Throwable th12 = th5;
                new WrongType(classCastException4, "exp", -2, (Object) expression9);
                throw th12;
            }
        } else if (exp instanceof ReferenceExp) {
            Expression expression10 = exp;
            Expression expression11 = expression10;
            try {
                obj = ((ReferenceExp) expression10).getSymbol();
            } catch (ClassCastException e5) {
                ClassCastException classCastException5 = e5;
                Throwable th13 = th4;
                new WrongType(classCastException5, "exp", -2, (Object) expression11);
                throw th13;
            }
        } else if (exp instanceof ApplyExp) {
            Expression expression12 = exp;
            Expression expression13 = expression12;
            try {
                obj = unrewriteApply((ApplyExp) expression12);
            } catch (ClassCastException e6) {
                ClassCastException classCastException6 = e6;
                Throwable th14 = th3;
                new WrongType(classCastException6, "exp", -2, (Object) expression13);
                throw th14;
            }
        } else if (exp instanceof BeginExp) {
            Expression expression14 = exp;
            Expression expression15 = expression14;
            try {
                BeginExp exp4 = (BeginExp) expression14;
                Object[] objArr23 = new Object[2];
                objArr23[0] = Lit5;
                Object[] objArr24 = objArr23;
                objArr24[1] = unrewrite$St(exp4.getExpressions());
                obj = Quote.append$V(objArr24);
            } catch (ClassCastException e7) {
                ClassCastException classCastException7 = e7;
                Throwable th15 = th2;
                new WrongType(classCastException7, "exp", -2, (Object) expression15);
                throw th15;
            }
        } else if (exp instanceof IfExp) {
            Expression expression16 = exp;
            Expression expression17 = expression16;
            try {
                IfExp exp5 = (IfExp) expression16;
                Object[] objArr25 = new Object[2];
                objArr25[0] = Lit6;
                Object[] objArr26 = objArr25;
                Object[] objArr27 = objArr26;
                Object[] objArr28 = objArr26;
                Object[] objArr29 = new Object[2];
                objArr29[0] = unrewrite(exp5.getTest());
                Object[] objArr30 = objArr29;
                Object[] objArr31 = objArr30;
                Object[] objArr32 = objArr30;
                Object[] objArr33 = new Object[2];
                objArr33[0] = unrewrite(exp5.getThenClause());
                Object[] objArr34 = objArr33;
                Object[] objArr35 = objArr34;
                Object[] objArr36 = objArr34;
                Object[] objArr37 = new Object[2];
                Object[] objArr38 = objArr37;
                Object[] objArr39 = objArr37;
                Expression eclause = exp5.getElseClause();
                objArr39[0] = eclause == null ? LList.Empty : LList.list1(unrewrite(eclause));
                Object[] objArr40 = objArr38;
                objArr40[1] = LList.Empty;
                objArr36[1] = Quote.append$V(objArr40);
                objArr32[1] = Quote.consX$V(objArr35);
                objArr28[1] = Quote.consX$V(objArr31);
                obj = Quote.append$V(objArr27);
            } catch (ClassCastException e8) {
                ClassCastException classCastException8 = e8;
                Throwable th16 = th;
                new WrongType(classCastException8, "exp", -2, (Object) expression17);
                throw th16;
            }
        } else {
            obj = exp;
        }
        return obj;
    }

    static Object unrewrite$St(Expression[] expressionArr) {
        frame0 frame02;
        Expression[] exps = expressionArr;
        new frame0();
        frame0 frame03 = frame02;
        frame03.pack = LList.Empty;
        Integer valueOf = Integer.valueOf(exps.length);
        Object obj = Lit7;
        while (true) {
            Object obj2 = obj;
            Integer len = valueOf;
            frame0 frame04 = frame03;
            if (Scheme.numEqu.apply2(obj2, len) != Boolean.FALSE) {
                return C1245lists.reverse$Ex(frame03.pack);
            }
            frame03.pack = C1245lists.cons(unrewrite(exps[((Number) obj2).intValue()]), frame03.pack);
            valueOf = len;
            obj = AddOp.$Pl.apply2(obj2, Lit8);
        }
    }

    static Object unrewriteLet(LetExp letExp) {
        frame1 frame12;
        LetExp exp = letExp;
        new frame1();
        frame1 frame13 = frame12;
        Object[] objArr = new Object[2];
        objArr[0] = Lit9;
        Object[] objArr2 = objArr;
        Object[] objArr3 = objArr2;
        Object[] objArr4 = objArr2;
        Object[] objArr5 = new Object[2];
        Object[] objArr6 = objArr5;
        Object[] objArr7 = objArr5;
        frame13.pack = LList.Empty;
        Declaration firstDecl = exp.firstDecl();
        Object obj = Lit7;
        while (true) {
            Object obj2 = obj;
            Declaration decl = firstDecl;
            frame1 frame14 = frame13;
            if (decl != null) {
                frame13.pack = C1245lists.cons(LList.list2(decl.getSymbol(), unrewrite(exp.inits[((Number) obj2).intValue()])), frame13.pack);
                firstDecl = decl.nextDecl();
                obj = AddOp.$Pl.apply2(obj2, Lit8);
            } else {
                objArr7[0] = C1245lists.reverse$Ex(frame13.pack);
                Object[] objArr8 = objArr6;
                Object[] objArr9 = objArr8;
                Object[] objArr10 = objArr8;
                Object[] objArr11 = new Object[2];
                objArr11[0] = unrewrite(exp.body);
                Object[] objArr12 = objArr11;
                objArr12[1] = LList.Empty;
                objArr10[1] = Quote.consX$V(objArr12);
                objArr4[1] = Quote.consX$V(objArr9);
                return Quote.append$V(objArr3);
            }
        }
    }

    static Object unrewriteQuote(QuoteExp exp) {
        Object append$V;
        Throwable th;
        String name;
        Throwable th2;
        Numeric numeric;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        Throwable th7;
        Object val = exp.getValue();
        if (Numeric.asNumericOrNull(val) != null) {
            Object obj = val;
            Object obj2 = obj;
            try {
                numeric = LangObjType.coerceNumeric(obj);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th8 = th7;
                new WrongType(classCastException, "val", -2, obj2);
                throw th8;
            }
        } else if (val instanceof Boolean) {
            Object obj3 = val;
            Object obj4 = obj3;
            try {
                numeric = obj3 != Boolean.FALSE ? Boolean.TRUE : Boolean.FALSE;
            } catch (ClassCastException e2) {
                ClassCastException classCastException2 = e2;
                Throwable th9 = th6;
                new WrongType(classCastException2, "val", -2, obj4);
                throw th9;
            }
        } else if (val instanceof Char) {
            Object obj5 = val;
            Object obj6 = obj5;
            try {
                numeric = (Char) obj5;
            } catch (ClassCastException e3) {
                ClassCastException classCastException3 = e3;
                Throwable th10 = th5;
                new WrongType(classCastException3, "val", -2, obj6);
                throw th10;
            }
        } else if (val instanceof Keyword) {
            Object obj7 = val;
            Object obj8 = obj7;
            try {
                numeric = (Keyword) obj7;
            } catch (ClassCastException e4) {
                ClassCastException classCastException4 = e4;
                Throwable th11 = th4;
                new WrongType(classCastException4, "val", -2, obj8);
                throw th11;
            }
        } else if (val instanceof CharSequence) {
            Object obj9 = val;
            Object obj10 = obj9;
            try {
                numeric = (CharSequence) obj9;
            } catch (ClassCastException e5) {
                ClassCastException classCastException5 = e5;
                Throwable th12 = th3;
                new WrongType(classCastException5, "val", -2, obj10);
                throw th12;
            }
        } else if (val == Special.undefined) {
            numeric = val;
        } else if (val == EofClass.eofValue) {
            numeric = val;
        } else {
            if (val instanceof Type) {
                Object obj11 = val;
                Object obj12 = obj11;
                try {
                    name = ((Type) obj11).getName();
                } catch (ClassCastException e6) {
                    ClassCastException classCastException6 = e6;
                    Throwable th13 = th2;
                    new WrongType(classCastException6, "val", -2, obj12);
                    throw th13;
                }
            } else if (val instanceof Class) {
                Object obj13 = val;
                Object obj14 = obj13;
                try {
                    name = ((Class) obj13).getName();
                } catch (ClassCastException e7) {
                    ClassCastException classCastException7 = e7;
                    Throwable th14 = th;
                    new WrongType(classCastException7, "val", -2, obj14);
                    throw th14;
                }
            } else {
                Object[] objArr = new Object[2];
                objArr[0] = Lit10;
                Object[] objArr2 = objArr;
                Object[] objArr3 = objArr2;
                Object[] objArr4 = objArr2;
                Object[] objArr5 = new Object[2];
                objArr5[0] = val;
                Object[] objArr6 = objArr5;
                objArr6[1] = LList.Empty;
                objArr4[1] = Quote.consX$V(objArr6);
                append$V = Quote.append$V(objArr3);
                return append$V;
            }
            Object[] objArr7 = new Object[2];
            objArr7[0] = "<~a>";
            Object[] objArr8 = objArr7;
            objArr8[1] = name;
            append$V = misc.string$To$Symbol(Format.formatToString(0, objArr8));
            return append$V;
        }
        append$V = numeric;
        return append$V;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x007b, code lost:
        if (r6 != false) goto L_0x006d;
     */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00a1  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00c0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.lang.Object unrewriteApply(gnu.expr.ApplyExp r17) {
        /*
            r0 = r17
            r8 = r0
            gnu.expr.Expression r8 = r8.getFunction()
            r1 = r8
            r8 = r0
            gnu.expr.Expression[] r8 = r8.getArgs()
            java.lang.Object r8 = unrewrite$St(r8)
            r2 = r8
            r8 = r1
            boolean r8 = r8 instanceof gnu.expr.ReferenceExp
            if (r8 == 0) goto L_0x0070
            r8 = r1
            r15 = r8
            r8 = r15
            r9 = r15
            r5 = r9
            gnu.expr.ReferenceExp r8 = (gnu.expr.ReferenceExp) r8     // Catch:{ ClassCastException -> 0x00da }
            r4 = r8
            r8 = r4
            gnu.expr.Declaration r8 = r8.getBinding()
        L_0x0024:
            r3 = r8
            java.lang.String r8 = "kawa.standard.Scheme"
            java.lang.String r9 = "applyToArgs"
            gnu.expr.Declaration r8 = gnu.expr.Declaration.getDeclarationFromStatic(r8, r9)
            r4 = r8
            r8 = r0
            java.lang.Object r8 = r8.getFunctionValue()
            r5 = r8
            r8 = r3
            r9 = 0
            if (r8 != r9) goto L_0x0072
            r8 = 1
        L_0x003b:
            r9 = 1
            int r8 = r8 + 1
            r9 = 1
            r8 = r8 & 1
            r6 = r8
            r8 = r6
            if (r8 == 0) goto L_0x007a
            r8 = r4
            r9 = 0
            if (r8 != r9) goto L_0x0074
            r8 = 1
        L_0x004a:
            r9 = 1
            int r8 = r8 + 1
            r9 = 1
            r8 = r8 & 1
            r7 = r8
            r8 = r7
            if (r8 == 0) goto L_0x0076
            r8 = 0
            r9 = r3
            java.lang.String r10 = "field"
            java.lang.String r11 = "field"
            java.lang.String r12 = "getField"
            java.lang.String r13 = "isField"
            kawa.standard.Scheme r14 = kawa.standard.Scheme.instance
            java.lang.Object r8 = gnu.kawa.reflect.SlotGet.getSlotValue(r8, r9, r10, r11, r12, r13, r14)
            r9 = r4
            gnu.bytecode.Field r9 = r9.field
            if (r8 != r9) goto L_0x007e
        L_0x006d:
            r8 = r2
        L_0x006e:
            r0 = r8
            return r0
        L_0x0070:
            r8 = 0
            goto L_0x0024
        L_0x0072:
            r8 = 0
            goto L_0x003b
        L_0x0074:
            r8 = 0
            goto L_0x004a
        L_0x0076:
            r8 = r7
            if (r8 == 0) goto L_0x007e
            goto L_0x006d
        L_0x007a:
            r8 = r6
            if (r8 == 0) goto L_0x007e
            goto L_0x006d
        L_0x007e:
            r8 = r5
            boolean r8 = r8 instanceof gnu.kawa.functions.Convert
            if (r8 == 0) goto L_0x00a1
            r8 = 2
            java.lang.Object[] r8 = new java.lang.Object[r8]
            r15 = r8
            r8 = r15
            r9 = r15
            r10 = 0
            gnu.lists.PairWithPosition r11 = Lit11
            r9[r10] = r11
            r15 = r8
            r8 = r15
            r9 = r15
            r10 = 1
            r11 = r2
            r9[r10] = r11
            java.lang.Object r8 = kawa.lang.Quote.append$V(r8)
        L_0x0099:
            r6 = r8
            r8 = r6
            java.lang.Boolean r9 = java.lang.Boolean.FALSE
            if (r8 == r9) goto L_0x00c0
            r8 = r6
            goto L_0x006e
        L_0x00a1:
            r8 = r5
            boolean r8 = r8 instanceof gnu.kawa.functions.GetNamedPart
            if (r8 == 0) goto L_0x00bd
            r8 = 2
            java.lang.Object[] r8 = new java.lang.Object[r8]
            r15 = r8
            r8 = r15
            r9 = r15
            r10 = 0
            gnu.lists.PairWithPosition r11 = Lit12
            r9[r10] = r11
            r15 = r8
            r8 = r15
            r9 = r15
            r10 = 1
            r11 = r2
            r9[r10] = r11
            java.lang.Object r8 = kawa.lang.Quote.append$V(r8)
            goto L_0x0099
        L_0x00bd:
            java.lang.Boolean r8 = java.lang.Boolean.FALSE
            goto L_0x0099
        L_0x00c0:
            r8 = 2
            java.lang.Object[] r8 = new java.lang.Object[r8]
            r15 = r8
            r8 = r15
            r9 = r15
            r10 = 0
            r11 = r1
            java.lang.Object r11 = unrewrite(r11)
            r9[r10] = r11
            r15 = r8
            r8 = r15
            r9 = r15
            r10 = 1
            r11 = r2
            r9[r10] = r11
            java.lang.Object r8 = kawa.lang.Quote.consX$V(r8)
            goto L_0x006e
        L_0x00da:
            r8 = move-exception
            gnu.mapping.WrongType r9 = new gnu.mapping.WrongType
            r15 = r8
            r16 = r9
            r8 = r16
            r9 = r15
            r10 = r16
            r15 = r9
            r16 = r10
            r9 = r16
            r10 = r15
            java.lang.String r11 = "fun"
            r12 = -2
            r13 = r5
            r9.<init>((java.lang.ClassCastException) r10, (java.lang.String) r11, (int) r12, (java.lang.Object) r13)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.syntaxutils.unrewriteApply(gnu.expr.ApplyExp):java.lang.Object");
    }
}

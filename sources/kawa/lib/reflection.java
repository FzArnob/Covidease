package kawa.lib;

import android.support.p000v4.app.FragmentTransaction;
import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.expr.Compilation;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.lispexpr.LangObjType;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import kawa.lang.GetFieldProc;
import kawa.lang.Macro;
import kawa.lang.Record;
import kawa.lang.RecordConstructor;
import kawa.lang.SetFieldProc;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lang.SyntaxTemplate;
import kawa.lang.TemplateScope;
import kawa.standard.syntax_case;

/* compiled from: reflection.scm */
public class reflection extends ModuleBody {
    public static final reflection $instance;
    static final SimpleSymbol Lit0;
    static final SyntaxPattern Lit1;
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit12;
    static final SimpleSymbol Lit13;
    static final SimpleSymbol Lit14;
    static final SyntaxRules Lit15;
    static final SimpleSymbol Lit16;
    static final SyntaxRules Lit17;
    static final SimpleSymbol Lit18;
    static final SyntaxRules Lit19;
    static final SyntaxTemplate Lit2;
    static final SimpleSymbol Lit20;
    static final SyntaxRules Lit21;
    static final SimpleSymbol Lit22;
    static final SyntaxRules Lit23;
    static final SimpleSymbol Lit24;
    static final SyntaxRules Lit25;
    static final SimpleSymbol Lit26;
    static final SyntaxRules Lit27;
    static final SimpleSymbol Lit28;
    static final SyntaxRules Lit29;
    static final SyntaxPattern Lit3;
    static final SimpleSymbol Lit30;
    static final SimpleSymbol Lit31;
    static final SimpleSymbol Lit32;
    static final IntNum Lit33 = IntNum.make(9);
    static final IntNum Lit34 = IntNum.make(1);
    static final SyntaxTemplate Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    public static final ModuleMethod make$Mnrecord$Mntype;
    public static final Macro primitive$Mnarray$Mnget = Macro.make(Lit18, Lit19, $instance);
    public static final Macro primitive$Mnarray$Mnlength = Macro.make(Lit20, Lit21, $instance);
    public static final Macro primitive$Mnarray$Mnnew = Macro.make(Lit14, Lit15, $instance);
    public static final Macro primitive$Mnarray$Mnset = Macro.make(Lit16, Lit17, $instance);
    public static final Macro primitive$Mnconstructor;
    public static final Macro primitive$Mnget$Mnfield = Macro.make(Lit22, Lit23, $instance);
    public static final Macro primitive$Mnget$Mnstatic = Macro.make(Lit26, Lit27, $instance);
    public static final Macro primitive$Mnset$Mnfield = Macro.make(Lit24, Lit25, $instance);
    public static final Macro primitive$Mnset$Mnstatic = Macro.make(Lit28, Lit29, $instance);
    public static final ModuleMethod record$Mnaccessor;
    public static final ModuleMethod record$Mnconstructor;
    public static final ModuleMethod record$Mnmodifier;
    public static final ModuleMethod record$Mnpredicate;
    public static final ModuleMethod record$Mntype$Mndescriptor;
    public static final ModuleMethod record$Mntype$Mnfield$Mnnames;
    public static final ModuleMethod record$Mntype$Mnname;
    public static final ModuleMethod record$Qu;
    public static final ModuleMethod subtype$Qu;

    static {
        SimpleSymbol simpleSymbol;
        SimpleSymbol simpleSymbol2;
        SimpleSymbol simpleSymbol3;
        SyntaxRules syntaxRules;
        SimpleSymbol simpleSymbol4;
        SyntaxRule syntaxRule;
        SyntaxPattern syntaxPattern;
        SimpleSymbol simpleSymbol5;
        SyntaxRules syntaxRules2;
        SimpleSymbol simpleSymbol6;
        SyntaxRule syntaxRule2;
        SyntaxPattern syntaxPattern2;
        SimpleSymbol simpleSymbol7;
        SyntaxRules syntaxRules3;
        SimpleSymbol simpleSymbol8;
        SyntaxRule syntaxRule3;
        SyntaxPattern syntaxPattern3;
        SimpleSymbol simpleSymbol9;
        SyntaxRules syntaxRules4;
        SimpleSymbol simpleSymbol10;
        SyntaxRule syntaxRule4;
        SyntaxPattern syntaxPattern4;
        SimpleSymbol simpleSymbol11;
        SyntaxRules syntaxRules5;
        SimpleSymbol simpleSymbol12;
        SyntaxRule syntaxRule5;
        SyntaxPattern syntaxPattern5;
        SimpleSymbol simpleSymbol13;
        SyntaxRules syntaxRules6;
        SimpleSymbol simpleSymbol14;
        SyntaxRule syntaxRule6;
        SyntaxPattern syntaxPattern6;
        SimpleSymbol simpleSymbol15;
        SyntaxRules syntaxRules7;
        SimpleSymbol simpleSymbol16;
        SyntaxRule syntaxRule7;
        SyntaxPattern syntaxPattern7;
        SimpleSymbol simpleSymbol17;
        SyntaxRules syntaxRules8;
        SimpleSymbol simpleSymbol18;
        SyntaxRule syntaxRule8;
        SyntaxPattern syntaxPattern8;
        SimpleSymbol simpleSymbol19;
        SimpleSymbol simpleSymbol20;
        SimpleSymbol simpleSymbol21;
        SimpleSymbol simpleSymbol22;
        SimpleSymbol simpleSymbol23;
        SimpleSymbol simpleSymbol24;
        SimpleSymbol simpleSymbol25;
        SimpleSymbol simpleSymbol26;
        SimpleSymbol simpleSymbol27;
        SimpleSymbol simpleSymbol28;
        SyntaxTemplate syntaxTemplate;
        SimpleSymbol simpleSymbol29;
        SimpleSymbol simpleSymbol30;
        SimpleSymbol simpleSymbol31;
        SyntaxPattern syntaxPattern9;
        SyntaxTemplate syntaxTemplate2;
        SyntaxPattern syntaxPattern10;
        SimpleSymbol simpleSymbol32;
        reflection reflection;
        Procedure procedure;
        ModuleMethod moduleMethod;
        ModuleMethod moduleMethod2;
        ModuleMethod moduleMethod3;
        ModuleMethod moduleMethod4;
        ModuleMethod moduleMethod5;
        ModuleMethod moduleMethod6;
        ModuleMethod moduleMethod7;
        ModuleMethod moduleMethod8;
        ModuleMethod moduleMethod9;
        ModuleMethod moduleMethod10;
        new SimpleSymbol("make");
        Lit32 = (SimpleSymbol) simpleSymbol.readResolve();
        new SimpleSymbol("constant-fold");
        Lit31 = (SimpleSymbol) simpleSymbol2.readResolve();
        new SimpleSymbol("subtype?");
        Lit30 = (SimpleSymbol) simpleSymbol3.readResolve();
        SyntaxRules syntaxRules9 = syntaxRules;
        Object[] objArr = new Object[1];
        Object[] objArr2 = objArr;
        Object[] objArr3 = objArr;
        new SimpleSymbol("primitive-set-static");
        SimpleSymbol simpleSymbol33 = (SimpleSymbol) simpleSymbol4.readResolve();
        Lit28 = simpleSymbol33;
        objArr3[0] = simpleSymbol33;
        SyntaxRule[] syntaxRuleArr = new SyntaxRule[1];
        SyntaxRule[] syntaxRuleArr2 = syntaxRuleArr;
        SyntaxRule[] syntaxRuleArr3 = syntaxRuleArr;
        SyntaxRule syntaxRule9 = syntaxRule;
        SyntaxPattern syntaxPattern11 = syntaxPattern;
        new SyntaxPattern("\f\u0018\f\u0007\f\u000f\f\u0017\b", new Object[0], 3);
        Object[] objArr4 = new Object[4];
        objArr4[0] = Lit31;
        Object[] objArr5 = objArr4;
        objArr5[1] = Lit32;
        Object[] objArr6 = objArr5;
        new SimpleSymbol("<gnu.kawa.reflect.StaticSet>");
        objArr6[2] = (SimpleSymbol) simpleSymbol5.readResolve();
        Object[] objArr7 = objArr6;
        objArr7[3] = PairWithPosition.make(Lit33, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/reflection.scm", 454679);
        new SyntaxRule(syntaxPattern11, "\u0001\u0001\u0001", "\u0011\u0018\u0004\u0011\u0018\f\u0011\u0018\u0014\t\u0003\t\u000b\t\u0013\u0018\u001c", objArr7, 0);
        syntaxRuleArr3[0] = syntaxRule9;
        new SyntaxRules(objArr2, syntaxRuleArr2, 3);
        Lit29 = syntaxRules9;
        SyntaxRules syntaxRules10 = syntaxRules2;
        Object[] objArr8 = new Object[1];
        Object[] objArr9 = objArr8;
        Object[] objArr10 = objArr8;
        new SimpleSymbol("primitive-get-static");
        SimpleSymbol simpleSymbol34 = (SimpleSymbol) simpleSymbol6.readResolve();
        Lit26 = simpleSymbol34;
        objArr10[0] = simpleSymbol34;
        SyntaxRule[] syntaxRuleArr4 = new SyntaxRule[1];
        SyntaxRule[] syntaxRuleArr5 = syntaxRuleArr4;
        SyntaxRule[] syntaxRuleArr6 = syntaxRuleArr4;
        SyntaxRule syntaxRule10 = syntaxRule2;
        SyntaxPattern syntaxPattern12 = syntaxPattern2;
        new SyntaxPattern("\f\u0018\f\u0007\f\u000f\f\u0017\b", new Object[0], 3);
        Object[] objArr11 = new Object[4];
        objArr11[0] = Lit31;
        Object[] objArr12 = objArr11;
        objArr12[1] = Lit32;
        Object[] objArr13 = objArr12;
        new SimpleSymbol("<gnu.kawa.reflect.StaticGet>");
        objArr13[2] = (SimpleSymbol) simpleSymbol7.readResolve();
        Object[] objArr14 = objArr13;
        objArr14[3] = PairWithPosition.make(Lit33, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/reflection.scm", 430103);
        new SyntaxRule(syntaxPattern12, "\u0001\u0001\u0001", "\u0011\u0018\u0004\u0011\u0018\f\u0011\u0018\u0014\t\u0003\t\u000b\t\u0013\u0018\u001c", objArr14, 0);
        syntaxRuleArr6[0] = syntaxRule10;
        new SyntaxRules(objArr9, syntaxRuleArr5, 3);
        Lit27 = syntaxRules10;
        SyntaxRules syntaxRules11 = syntaxRules3;
        Object[] objArr15 = new Object[1];
        Object[] objArr16 = objArr15;
        Object[] objArr17 = objArr15;
        new SimpleSymbol("primitive-set-field");
        SimpleSymbol simpleSymbol35 = (SimpleSymbol) simpleSymbol8.readResolve();
        Lit24 = simpleSymbol35;
        objArr17[0] = simpleSymbol35;
        SyntaxRule[] syntaxRuleArr7 = new SyntaxRule[1];
        SyntaxRule[] syntaxRuleArr8 = syntaxRuleArr7;
        SyntaxRule[] syntaxRuleArr9 = syntaxRuleArr7;
        SyntaxRule syntaxRule11 = syntaxRule3;
        SyntaxPattern syntaxPattern13 = syntaxPattern3;
        new SyntaxPattern("\f\u0018\f\u0007\f\u000f\f\u0017\b", new Object[0], 3);
        Object[] objArr18 = new Object[4];
        objArr18[0] = Lit31;
        Object[] objArr19 = objArr18;
        objArr19[1] = Lit32;
        Object[] objArr20 = objArr19;
        new SimpleSymbol("<kawa.lang.SetFieldProc>");
        objArr20[2] = (SimpleSymbol) simpleSymbol9.readResolve();
        Object[] objArr21 = objArr20;
        objArr21[3] = PairWithPosition.make(Lit34, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/reflection.scm", 401431);
        new SyntaxRule(syntaxPattern13, "\u0001\u0001\u0001", "\u0011\u0018\u0004\u0011\u0018\f\u0011\u0018\u0014\t\u0003\t\u000b\t\u0013\u0018\u001c", objArr21, 0);
        syntaxRuleArr9[0] = syntaxRule11;
        new SyntaxRules(objArr16, syntaxRuleArr8, 3);
        Lit25 = syntaxRules11;
        SyntaxRules syntaxRules12 = syntaxRules4;
        Object[] objArr22 = new Object[1];
        Object[] objArr23 = objArr22;
        Object[] objArr24 = objArr22;
        new SimpleSymbol("primitive-get-field");
        SimpleSymbol simpleSymbol36 = (SimpleSymbol) simpleSymbol10.readResolve();
        Lit22 = simpleSymbol36;
        objArr24[0] = simpleSymbol36;
        SyntaxRule[] syntaxRuleArr10 = new SyntaxRule[1];
        SyntaxRule[] syntaxRuleArr11 = syntaxRuleArr10;
        SyntaxRule[] syntaxRuleArr12 = syntaxRuleArr10;
        SyntaxRule syntaxRule12 = syntaxRule4;
        SyntaxPattern syntaxPattern14 = syntaxPattern4;
        new SyntaxPattern("\f\u0018\f\u0007\f\u000f\f\u0017\b", new Object[0], 3);
        Object[] objArr25 = new Object[4];
        objArr25[0] = Lit31;
        Object[] objArr26 = objArr25;
        objArr26[1] = Lit32;
        Object[] objArr27 = objArr26;
        new SimpleSymbol("<kawa.lang.GetFieldProc>");
        objArr27[2] = (SimpleSymbol) simpleSymbol11.readResolve();
        Object[] objArr28 = objArr27;
        objArr28[3] = PairWithPosition.make(Lit34, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/reflection.scm", 376855);
        new SyntaxRule(syntaxPattern14, "\u0001\u0001\u0001", "\u0011\u0018\u0004\u0011\u0018\f\u0011\u0018\u0014\t\u0003\t\u000b\t\u0013\u0018\u001c", objArr28, 0);
        syntaxRuleArr12[0] = syntaxRule12;
        new SyntaxRules(objArr23, syntaxRuleArr11, 3);
        Lit23 = syntaxRules12;
        SyntaxRules syntaxRules13 = syntaxRules5;
        Object[] objArr29 = new Object[1];
        Object[] objArr30 = objArr29;
        Object[] objArr31 = objArr29;
        new SimpleSymbol("primitive-array-length");
        SimpleSymbol simpleSymbol37 = (SimpleSymbol) simpleSymbol12.readResolve();
        Lit20 = simpleSymbol37;
        objArr31[0] = simpleSymbol37;
        SyntaxRule[] syntaxRuleArr13 = new SyntaxRule[1];
        SyntaxRule[] syntaxRuleArr14 = syntaxRuleArr13;
        SyntaxRule[] syntaxRuleArr15 = syntaxRuleArr13;
        SyntaxRule syntaxRule13 = syntaxRule5;
        SyntaxPattern syntaxPattern15 = syntaxPattern5;
        new SyntaxPattern("\f\u0018\f\u0007\b", new Object[0], 1);
        Object[] objArr32 = new Object[3];
        objArr32[0] = Lit31;
        Object[] objArr33 = objArr32;
        objArr33[1] = Lit32;
        Object[] objArr34 = objArr33;
        new SimpleSymbol("<gnu.kawa.reflect.ArrayLength>");
        objArr34[2] = (SimpleSymbol) simpleSymbol13.readResolve();
        new SyntaxRule(syntaxPattern15, "\u0001", "\u0011\u0018\u0004\u0011\u0018\f\u0011\u0018\u0014\b\u0003", objArr34, 0);
        syntaxRuleArr15[0] = syntaxRule13;
        new SyntaxRules(objArr30, syntaxRuleArr14, 1);
        Lit21 = syntaxRules13;
        SyntaxRules syntaxRules14 = syntaxRules6;
        Object[] objArr35 = new Object[1];
        Object[] objArr36 = objArr35;
        Object[] objArr37 = objArr35;
        new SimpleSymbol("primitive-array-get");
        SimpleSymbol simpleSymbol38 = (SimpleSymbol) simpleSymbol14.readResolve();
        Lit18 = simpleSymbol38;
        objArr37[0] = simpleSymbol38;
        SyntaxRule[] syntaxRuleArr16 = new SyntaxRule[1];
        SyntaxRule[] syntaxRuleArr17 = syntaxRuleArr16;
        SyntaxRule[] syntaxRuleArr18 = syntaxRuleArr16;
        SyntaxRule syntaxRule14 = syntaxRule6;
        SyntaxPattern syntaxPattern16 = syntaxPattern6;
        new SyntaxPattern("\f\u0018\f\u0007\b", new Object[0], 1);
        Object[] objArr38 = new Object[3];
        objArr38[0] = Lit31;
        Object[] objArr39 = objArr38;
        objArr39[1] = Lit32;
        Object[] objArr40 = objArr39;
        new SimpleSymbol("<gnu.kawa.reflect.ArrayGet>");
        objArr40[2] = (SimpleSymbol) simpleSymbol15.readResolve();
        new SyntaxRule(syntaxPattern16, "\u0001", "\u0011\u0018\u0004\u0011\u0018\f\u0011\u0018\u0014\b\u0003", objArr40, 0);
        syntaxRuleArr18[0] = syntaxRule14;
        new SyntaxRules(objArr36, syntaxRuleArr17, 1);
        Lit19 = syntaxRules14;
        SyntaxRules syntaxRules15 = syntaxRules7;
        Object[] objArr41 = new Object[1];
        Object[] objArr42 = objArr41;
        Object[] objArr43 = objArr41;
        new SimpleSymbol("primitive-array-set");
        SimpleSymbol simpleSymbol39 = (SimpleSymbol) simpleSymbol16.readResolve();
        Lit16 = simpleSymbol39;
        objArr43[0] = simpleSymbol39;
        SyntaxRule[] syntaxRuleArr19 = new SyntaxRule[1];
        SyntaxRule[] syntaxRuleArr20 = syntaxRuleArr19;
        SyntaxRule[] syntaxRuleArr21 = syntaxRuleArr19;
        SyntaxRule syntaxRule15 = syntaxRule7;
        SyntaxPattern syntaxPattern17 = syntaxPattern7;
        new SyntaxPattern("\f\u0018\f\u0007\b", new Object[0], 1);
        Object[] objArr44 = new Object[3];
        objArr44[0] = Lit31;
        Object[] objArr45 = objArr44;
        objArr45[1] = Lit32;
        Object[] objArr46 = objArr45;
        new SimpleSymbol("<gnu.kawa.reflect.ArraySet>");
        objArr46[2] = (SimpleSymbol) simpleSymbol17.readResolve();
        new SyntaxRule(syntaxPattern17, "\u0001", "\u0011\u0018\u0004\u0011\u0018\f\u0011\u0018\u0014\b\u0003", objArr46, 0);
        syntaxRuleArr21[0] = syntaxRule15;
        new SyntaxRules(objArr42, syntaxRuleArr20, 1);
        Lit17 = syntaxRules15;
        SyntaxRules syntaxRules16 = syntaxRules8;
        Object[] objArr47 = new Object[1];
        Object[] objArr48 = objArr47;
        Object[] objArr49 = objArr47;
        new SimpleSymbol("primitive-array-new");
        SimpleSymbol simpleSymbol40 = (SimpleSymbol) simpleSymbol18.readResolve();
        Lit14 = simpleSymbol40;
        objArr49[0] = simpleSymbol40;
        SyntaxRule[] syntaxRuleArr22 = new SyntaxRule[1];
        SyntaxRule[] syntaxRuleArr23 = syntaxRuleArr22;
        SyntaxRule[] syntaxRuleArr24 = syntaxRuleArr22;
        SyntaxRule syntaxRule16 = syntaxRule8;
        SyntaxPattern syntaxPattern18 = syntaxPattern8;
        new SyntaxPattern("\f\u0018\f\u0007\b", new Object[0], 1);
        Object[] objArr50 = new Object[3];
        objArr50[0] = Lit31;
        Object[] objArr51 = objArr50;
        objArr51[1] = Lit32;
        Object[] objArr52 = objArr51;
        new SimpleSymbol("<gnu.kawa.reflect.ArrayNew>");
        objArr52[2] = (SimpleSymbol) simpleSymbol19.readResolve();
        new SyntaxRule(syntaxPattern18, "\u0001", "\u0011\u0018\u0004\u0011\u0018\f\u0011\u0018\u0014\b\u0003", objArr52, 0);
        syntaxRuleArr24[0] = syntaxRule16;
        new SyntaxRules(objArr48, syntaxRuleArr23, 1);
        Lit15 = syntaxRules16;
        new SimpleSymbol("record-type-field-names");
        Lit13 = (SimpleSymbol) simpleSymbol20.readResolve();
        new SimpleSymbol("record-type-name");
        Lit12 = (SimpleSymbol) simpleSymbol21.readResolve();
        new SimpleSymbol("record-type-descriptor");
        Lit11 = (SimpleSymbol) simpleSymbol22.readResolve();
        new SimpleSymbol("record-predicate");
        Lit10 = (SimpleSymbol) simpleSymbol23.readResolve();
        new SimpleSymbol("record?");
        Lit9 = (SimpleSymbol) simpleSymbol24.readResolve();
        new SimpleSymbol("record-modifier");
        Lit8 = (SimpleSymbol) simpleSymbol25.readResolve();
        new SimpleSymbol("record-accessor");
        Lit7 = (SimpleSymbol) simpleSymbol26.readResolve();
        new SimpleSymbol("record-constructor");
        Lit6 = (SimpleSymbol) simpleSymbol27.readResolve();
        new SimpleSymbol("make-record-type");
        Lit5 = (SimpleSymbol) simpleSymbol28.readResolve();
        SyntaxTemplate syntaxTemplate3 = syntaxTemplate;
        Object[] objArr53 = new Object[4];
        new SimpleSymbol("lambda");
        objArr53[0] = (SimpleSymbol) simpleSymbol29.readResolve();
        Object[] objArr54 = objArr53;
        new SimpleSymbol("::");
        objArr54[1] = (SimpleSymbol) simpleSymbol30.readResolve();
        Object[] objArr55 = objArr54;
        objArr55[2] = Lit32;
        Object[] objArr56 = objArr55;
        new SimpleSymbol("as");
        objArr56[3] = (SimpleSymbol) simpleSymbol31.readResolve();
        new SyntaxTemplate("\u0001\u0001\u0003\u0003", "\u0011\u0018\u0004\u0019\b\u001d\u001b\u0011\u0018\f\t\u000b\b\u0011\u0018\u0014\t\u000b\b\u0015\u0011\u0018\u001c\t\u0013\b\u001b", objArr56, 1);
        Lit4 = syntaxTemplate3;
        new SyntaxPattern("\r\u001f\u0018\b\b", new Object[0], 4);
        Lit3 = syntaxPattern9;
        new SyntaxTemplate("\u0001\u0001\u0003", "\b\u0015\u0013", new Object[0], 1);
        Lit2 = syntaxTemplate2;
        new SyntaxPattern("\f\u0007\f\u000f,\r\u0017\u0010\b\b\b", new Object[0], 3);
        Lit1 = syntaxPattern10;
        new SimpleSymbol("primitive-constructor");
        Lit0 = (SimpleSymbol) simpleSymbol32.readResolve();
        new reflection();
        $instance = reflection;
        reflection reflection2 = $instance;
        new ModuleMethod(reflection2, 2, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        primitive$Mnconstructor = Macro.make(Lit0, procedure, $instance);
        new ModuleMethod(reflection2, 3, Lit5, 8194);
        make$Mnrecord$Mntype = moduleMethod;
        new ModuleMethod(reflection2, 4, Lit6, 8193);
        record$Mnconstructor = moduleMethod2;
        new ModuleMethod(reflection2, 6, Lit7, 8194);
        record$Mnaccessor = moduleMethod3;
        new ModuleMethod(reflection2, 7, Lit8, 8194);
        record$Mnmodifier = moduleMethod4;
        new ModuleMethod(reflection2, 8, Lit9, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        record$Qu = moduleMethod5;
        new ModuleMethod(reflection2, 9, Lit10, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        record$Mnpredicate = moduleMethod6;
        new ModuleMethod(reflection2, 10, Lit11, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        record$Mntype$Mndescriptor = moduleMethod7;
        new ModuleMethod(reflection2, 11, Lit12, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        record$Mntype$Mnname = moduleMethod8;
        new ModuleMethod(reflection2, 12, Lit13, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        record$Mntype$Mnfield$Mnnames = moduleMethod9;
        new ModuleMethod(reflection2, 13, Lit30, 8194);
        subtype$Qu = moduleMethod10;
        $instance.run();
    }

    public reflection() {
        ModuleInfo.register(this);
    }

    public static RecordConstructor recordConstructor(ClassType classType) {
        return recordConstructor(classType, (Object) null);
    }

    public final void run(CallContext $ctx) {
        Consumer consumer = $ctx.consumer;
    }

    static Object lambda2(Object obj) {
        Object form;
        Object form2 = obj;
        Object[] allocVars = SyntaxPattern.allocVars(3, (Object[]) null);
        Object obj2 = form2;
        if (Lit1.match(form2, allocVars, 0)) {
            Object generateTemporaries = std_syntax.generateTemporaries(Lit2.execute(allocVars, TemplateScope.make()));
            Object[] allocVars2 = SyntaxPattern.allocVars(4, allocVars);
            Object obj3 = generateTemporaries;
            if (Lit3.match(obj3, allocVars2, 0)) {
                form = Lit4.execute(allocVars2, TemplateScope.make());
            } else {
                form = syntax_case.error("syntax-case", obj3);
            }
        } else {
            form = syntax_case.error("syntax-case", form2);
        }
        return form;
    }

    public static ClassType makeRecordType(String name, LList fnames) {
        return Record.makeRecordType(name, fnames);
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 3:
                callContext2.value1 = obj3;
                CallContext callContext3 = callContext2;
                Object obj5 = obj4;
                Object obj6 = obj5;
                if (!(obj5 instanceof LList)) {
                    return -786430;
                }
                callContext3.value2 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 4:
                CallContext callContext4 = callContext2;
                Object obj7 = obj3;
                Object obj8 = obj7;
                if (LangObjType.coerceToClassTypeOrNull(obj7) == null) {
                    return -786431;
                }
                callContext4.value1 = obj8;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 6:
                CallContext callContext5 = callContext2;
                Object obj9 = obj3;
                Object obj10 = obj9;
                if (LangObjType.coerceToClassTypeOrNull(obj9) == null) {
                    return -786431;
                }
                callContext5.value1 = obj10;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 7:
                CallContext callContext6 = callContext2;
                Object obj11 = obj3;
                Object obj12 = obj11;
                if (LangObjType.coerceToClassTypeOrNull(obj11) == null) {
                    return -786431;
                }
                callContext6.value1 = obj12;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 13:
                CallContext callContext7 = callContext2;
                Object obj13 = obj3;
                Object obj14 = obj13;
                if (LangObjType.coerceToTypeOrNull(obj13) == null) {
                    return -786431;
                }
                callContext7.value1 = obj14;
                CallContext callContext8 = callContext2;
                Object obj15 = obj4;
                Object obj16 = obj15;
                if (LangObjType.coerceToTypeOrNull(obj15) == null) {
                    return -786430;
                }
                callContext8.value2 = obj16;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            default:
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
        }
    }

    public static RecordConstructor recordConstructor(ClassType cl, Object flds) {
        RecordConstructor recordConstructor;
        new RecordConstructor(cl, flds);
        return recordConstructor;
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 2:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 4:
                CallContext callContext3 = callContext2;
                Object obj3 = obj2;
                Object obj4 = obj3;
                if (LangObjType.coerceToClassTypeOrNull(obj3) == null) {
                    return -786431;
                }
                callContext3.value1 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 8:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 9:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 10:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 11:
                CallContext callContext4 = callContext2;
                Object obj5 = obj2;
                Object obj6 = obj5;
                if (LangObjType.coerceToClassTypeOrNull(obj5) == null) {
                    return -786431;
                }
                callContext4.value1 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 12:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            default:
                return super.match1(moduleMethod2, obj2, callContext2);
        }
    }

    public static GetFieldProc recordAccessor(ClassType classType, String fname) {
        GetFieldProc getFieldProc;
        new GetFieldProc(classType, fname);
        return getFieldProc;
    }

    public static SetFieldProc recordModifier(ClassType classType, String fname) {
        SetFieldProc setFieldProc;
        new SetFieldProc(classType, fname);
        return setFieldProc;
    }

    public static boolean isRecord(Object obj) {
        return obj instanceof Record;
    }

    /* compiled from: reflection.scm */
    public class frame extends ModuleBody {
        final ModuleMethod lambda$Fn1;
        Object rtype;

        public frame() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 1, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/reflection.scm:30");
            this.lambda$Fn1 = moduleMethod2;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            Boolean bool;
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector != 1) {
                return super.apply1(moduleMethod2, obj2);
            }
            if (lambda1(obj2)) {
                bool = Boolean.TRUE;
            } else {
                bool = Boolean.FALSE;
            }
            return bool;
        }

        /* access modifiers changed from: package-private */
        public boolean lambda1(Object obj) {
            Throwable th;
            Object object = obj;
            Object obj2 = this.rtype;
            Object obj3 = obj2;
            try {
                return ((Type) obj2).isInstance(object);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "gnu.bytecode.Type.isInstance(java.lang.Object)", 1, obj3);
                throw th2;
            }
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 1) {
                return super.match1(moduleMethod2, obj2, callContext2);
            }
            callContext2.value1 = obj2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 1;
            return 0;
        }
    }

    public static Procedure recordPredicate(Object rtype) {
        frame frame2;
        new frame();
        frame frame3 = frame2;
        frame3.rtype = rtype;
        return frame3.lambda$Fn1;
    }

    public static Type recordTypeDescriptor(Object object) {
        return Type.make(object.getClass());
    }

    public static String recordTypeName(ClassType rtd) {
        return Compilation.demangleName(rtd.getName(), true);
    }

    public static LList recordTypeFieldNames(Object rtd) {
        Throwable th;
        Object obj = rtd;
        Object obj2 = obj;
        try {
            return Record.typeFieldNames(LangObjType.coerceToClassType(obj));
        } catch (ClassCastException e) {
            ClassCastException classCastException = e;
            Throwable th2 = th;
            new WrongType(classCastException, "kawa.lang.Record.typeFieldNames(class-type)", 1, obj2);
            throw th2;
        }
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        Throwable th;
        Throwable th2;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        switch (moduleMethod2.selector) {
            case 2:
                return lambda2(obj2);
            case 4:
                try {
                    return recordConstructor(LangObjType.coerceToClassType(obj2));
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th3 = th2;
                    new WrongType(classCastException, "record-constructor", 1, obj2);
                    throw th3;
                }
            case 8:
                return isRecord(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 9:
                return recordPredicate(obj2);
            case 10:
                return recordTypeDescriptor(obj2);
            case 11:
                try {
                    return recordTypeName(LangObjType.coerceToClassType(obj2));
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th4 = th;
                    new WrongType(classCastException2, "record-type-name", 1, obj2);
                    throw th4;
                }
            case 12:
                return recordTypeFieldNames(obj2);
            default:
                return super.apply1(moduleMethod2, obj2);
        }
    }

    public static boolean isSubtype(Type t1, Type t2) {
        return t1.isSubtype(t2);
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        switch (moduleMethod2.selector) {
            case 3:
                Object obj5 = obj3;
                try {
                    return makeRecordType(obj5 == null ? null : obj5.toString(), (LList) obj4);
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th7 = th6;
                    new WrongType(classCastException, "make-record-type", 2, obj4);
                    throw th7;
                }
            case 4:
                try {
                    return recordConstructor(LangObjType.coerceToClassType(obj3), obj4);
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th8 = th5;
                    new WrongType(classCastException2, "record-constructor", 1, obj3);
                    throw th8;
                }
            case 6:
                try {
                    Object obj6 = obj4;
                    return recordAccessor(LangObjType.coerceToClassType(obj3), obj6 == null ? null : obj6.toString());
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th9 = th4;
                    new WrongType(classCastException3, "record-accessor", 1, obj3);
                    throw th9;
                }
            case 7:
                try {
                    Object obj7 = obj4;
                    return recordModifier(LangObjType.coerceToClassType(obj3), obj7 == null ? null : obj7.toString());
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th10 = th3;
                    new WrongType(classCastException4, "record-modifier", 1, obj3);
                    throw th10;
                }
            case 13:
                try {
                    try {
                        return isSubtype(LangObjType.coerceToType(obj3), LangObjType.coerceToType(obj4)) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e5) {
                        ClassCastException classCastException5 = e5;
                        Throwable th11 = th2;
                        new WrongType(classCastException5, "subtype?", 2, obj4);
                        throw th11;
                    }
                } catch (ClassCastException e6) {
                    ClassCastException classCastException6 = e6;
                    Throwable th12 = th;
                    new WrongType(classCastException6, "subtype?", 1, obj3);
                    throw th12;
                }
            default:
                return super.apply2(moduleMethod2, obj3, obj4);
        }
    }
}

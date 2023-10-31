package kawa.lib;

import android.support.p000v4.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.expr.SynchronizedExp;
import gnu.expr.TryExp;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.math.IntNum;
import kawa.lang.Macro;
import kawa.lang.Quote;
import kawa.lang.SyntaxForms;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lang.SyntaxTemplate;
import kawa.lang.TemplateScope;
import kawa.lang.Translator;
import kawa.standard.IfFeature;
import kawa.standard.Scheme;
import kawa.standard.syntax_case;

/* compiled from: syntax.scm */
public class syntax extends ModuleBody {
    public static final Macro $Pccond$Mnexpand;
    public static final Macro $Pcimport;
    public static final Location $Prvt$and = StaticFieldLocation.make("kawa.lib.std_syntax", "and");
    public static final Location $Prvt$define$Mnconstant = StaticFieldLocation.make("kawa.lib.prim_syntax", "define$Mnconstant");
    public static final Location $Prvt$define$Mnsyntax = StaticFieldLocation.make("kawa.lib.prim_syntax", "define$Mnsyntax");
    public static final Location $Prvt$if = StaticFieldLocation.make("kawa.lib.prim_syntax", "if");
    public static final Location $Prvt$let = StaticFieldLocation.make("kawa.lib.std_syntax", "let");
    public static final Location $Prvt$or = StaticFieldLocation.make("kawa.lib.std_syntax", "or");
    public static final Location $Prvt$try$Mncatch = StaticFieldLocation.make("kawa.lib.prim_syntax", "try$Mncatch");
    public static final syntax $instance;
    static final SyntaxPattern Lit0;
    static final SyntaxTemplate Lit1;
    static final SyntaxPattern Lit10;
    static final SimpleSymbol Lit100;
    static final SyntaxRules Lit101;
    static final SimpleSymbol Lit102;
    static final SimpleSymbol Lit103;
    static final SimpleSymbol Lit104;
    static final SimpleSymbol Lit105;
    static final SimpleSymbol Lit106;
    static final SimpleSymbol Lit107;
    static final SimpleSymbol Lit108;
    static final SimpleSymbol Lit109;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit110;
    static final SimpleSymbol Lit111;
    static final SimpleSymbol Lit112;
    static final SimpleSymbol Lit113;
    static final SimpleSymbol Lit114;
    static final SimpleSymbol Lit115;
    static final SimpleSymbol Lit116;
    static final SimpleSymbol Lit117;
    static final SimpleSymbol Lit118;
    static final SimpleSymbol Lit119;
    static final SyntaxRules Lit12;
    static final SimpleSymbol Lit120;
    static final SimpleSymbol Lit121;
    static final SimpleSymbol Lit122;
    static final SimpleSymbol Lit123;
    static final SimpleSymbol Lit13;
    static final SyntaxRules Lit14;
    static final SimpleSymbol Lit15;
    static final SyntaxRules Lit16;
    static final SimpleSymbol Lit17;
    static final SyntaxRules Lit18;
    static final SimpleSymbol Lit19;
    static final SyntaxTemplate Lit2;
    static final SyntaxRules Lit20;
    static final SimpleSymbol Lit21;
    static final SyntaxPattern Lit22;
    static final SyntaxTemplate Lit23;
    static final SyntaxTemplate Lit24;
    static final SimpleSymbol Lit25;
    static final SyntaxPattern Lit26;
    static final SyntaxTemplate Lit27;
    static final SyntaxTemplate Lit28;
    static final SimpleSymbol Lit29;
    static final SyntaxPattern Lit3;
    static final SimpleSymbol Lit30;
    static final SimpleSymbol Lit31;
    static final SimpleSymbol Lit32;
    static final SimpleSymbol Lit33;
    static final SimpleSymbol Lit34;
    static final SimpleSymbol Lit35;
    static final SimpleSymbol Lit36;
    static final SyntaxRules Lit37;
    static final SimpleSymbol Lit38;
    static final SyntaxPattern Lit39;
    static final SyntaxPattern Lit4;
    static final SyntaxTemplate Lit40;
    static final SyntaxTemplate Lit41;
    static final SyntaxTemplate Lit42;
    static final SyntaxTemplate Lit43;
    static final SyntaxTemplate Lit44;
    static final SyntaxTemplate Lit45;
    static final SyntaxPattern Lit46;
    static final SyntaxTemplate Lit47;
    static final SyntaxTemplate Lit48;
    static final SyntaxTemplate Lit49;
    static final SyntaxPattern Lit5;
    static final SyntaxTemplate Lit50;
    static final SyntaxTemplate Lit51;
    static final SyntaxTemplate Lit52;
    static final SyntaxPattern Lit53;
    static final SyntaxTemplate Lit54;
    static final SyntaxTemplate Lit55;
    static final SyntaxTemplate Lit56;
    static final SyntaxTemplate Lit57;
    static final SyntaxTemplate Lit58;
    static final SyntaxTemplate Lit59;
    static final SyntaxTemplate Lit6;
    static final SyntaxPattern Lit60;
    static final SyntaxTemplate Lit61;
    static final SyntaxTemplate Lit62;
    static final SyntaxTemplate Lit63;
    static final SyntaxTemplate Lit64;
    static final SyntaxPattern Lit65;
    static final SyntaxTemplate Lit66;
    static final SyntaxPattern Lit67;
    static final SyntaxTemplate Lit68;
    static final SyntaxTemplate Lit69;
    static final SyntaxTemplate Lit7;
    static final SyntaxTemplate Lit70;
    static final SyntaxTemplate Lit71;
    static final SyntaxPattern Lit72;
    static final SyntaxTemplate Lit73;
    static final SyntaxTemplate Lit74;
    static final SyntaxTemplate Lit75;
    static final SyntaxTemplate Lit76;
    static final SimpleSymbol Lit77;
    static final SyntaxRules Lit78;
    static final SimpleSymbol Lit79;
    static final SyntaxTemplate Lit8;
    static final SyntaxRules Lit80;
    static final SimpleSymbol Lit81;
    static final SyntaxPattern Lit82;
    static final SyntaxTemplate Lit83;
    static final SyntaxTemplate Lit84;
    static final SyntaxPattern Lit85;
    static final SyntaxTemplate Lit86;
    static final SyntaxTemplate Lit87;
    static final SyntaxPattern Lit88;
    static final SyntaxPattern Lit89;
    static final SyntaxPattern Lit9;
    static final SyntaxTemplate Lit90;
    static final SimpleSymbol Lit91;
    static final SyntaxRules Lit92;
    static final SimpleSymbol Lit93;
    static final SyntaxPattern Lit94;
    static final SyntaxTemplate Lit95;
    static final SyntaxTemplate Lit96;
    static final SyntaxTemplate Lit97;
    static final SimpleSymbol Lit98;
    static final SyntaxRules Lit99;
    public static final Macro case$Mnlambda;
    public static final Macro cond$Mnexpand = Macro.make(Lit91, Lit92, $instance);
    public static final Macro define$Mnalias$Mnparameter = Macro.make(Lit100, Lit101, $instance);
    public static final Macro define$Mnmacro = Macro.make(Lit13, Lit14, $instance);
    public static final Macro define$Mnsyntax$Mncase = Macro.make(Lit15, Lit16, $instance);
    public static final Macro defmacro = Macro.make(Lit11, Lit12, $instance);
    public static final ModuleMethod identifier$Mnlist$Qu;
    public static final ModuleMethod identifier$Mnpair$Mnlist$Qu;

    /* renamed from: import  reason: not valid java name */
    public static final Macro f611import = Macro.make(Lit36, Lit37, $instance);
    public static final ModuleMethod import$Mnhandle$Mnexcept;
    public static final ModuleMethod import$Mnhandle$Mnonly;
    public static final ModuleMethod import$Mnhandle$Mnprefix;
    public static final ModuleMethod import$Mnhandle$Mnrename;
    public static final ModuleMethod import$Mnmapper;
    public static final Macro let$Mnvalues = Macro.make(Lit77, Lit78, $instance);
    public static final Macro let$St$Mnvalues = Macro.make(Lit79, Lit80, $instance);
    public static final Macro receive = Macro.make(Lit98, Lit99, $instance);

    /* renamed from: synchronized  reason: not valid java name */
    public static final Macro f612synchronized;
    public static final Macro try$Mnfinally;
    public static final Macro unless = Macro.make(Lit19, Lit20, $instance);
    public static final Macro when = Macro.make(Lit17, Lit18, $instance);

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
        SimpleSymbol simpleSymbol11;
        SimpleSymbol simpleSymbol12;
        SimpleSymbol simpleSymbol13;
        SimpleSymbol simpleSymbol14;
        SimpleSymbol simpleSymbol15;
        SimpleSymbol simpleSymbol16;
        SimpleSymbol simpleSymbol17;
        SimpleSymbol simpleSymbol18;
        SimpleSymbol simpleSymbol19;
        SimpleSymbol simpleSymbol20;
        SimpleSymbol simpleSymbol21;
        SimpleSymbol simpleSymbol22;
        SyntaxRules syntaxRules;
        SimpleSymbol simpleSymbol23;
        SyntaxRule syntaxRule;
        SyntaxPattern syntaxPattern;
        SimpleSymbol simpleSymbol24;
        SimpleSymbol simpleSymbol25;
        SimpleSymbol simpleSymbol26;
        SimpleSymbol simpleSymbol27;
        SimpleSymbol simpleSymbol28;
        SimpleSymbol simpleSymbol29;
        SimpleSymbol simpleSymbol30;
        SimpleSymbol simpleSymbol31;
        SimpleSymbol simpleSymbol32;
        SimpleSymbol simpleSymbol33;
        SimpleSymbol simpleSymbol34;
        SimpleSymbol simpleSymbol35;
        SimpleSymbol simpleSymbol36;
        SimpleSymbol simpleSymbol37;
        SimpleSymbol simpleSymbol38;
        SyntaxRules syntaxRules2;
        SimpleSymbol simpleSymbol39;
        SyntaxRule syntaxRule2;
        SyntaxPattern syntaxPattern2;
        SyntaxTemplate syntaxTemplate;
        SimpleSymbol simpleSymbol40;
        SyntaxTemplate syntaxTemplate2;
        SyntaxTemplate syntaxTemplate3;
        SyntaxPattern syntaxPattern3;
        SimpleSymbol simpleSymbol41;
        SyntaxRules syntaxRules3;
        SyntaxRule syntaxRule3;
        SyntaxPattern syntaxPattern4;
        SimpleSymbol simpleSymbol42;
        SyntaxRule syntaxRule4;
        SyntaxPattern syntaxPattern5;
        SyntaxRule syntaxRule5;
        SyntaxPattern syntaxPattern6;
        SyntaxRule syntaxRule6;
        SyntaxPattern syntaxPattern7;
        SyntaxRule syntaxRule7;
        SyntaxPattern syntaxPattern8;
        SyntaxRule syntaxRule8;
        SyntaxPattern syntaxPattern9;
        SyntaxRule syntaxRule9;
        SyntaxPattern syntaxPattern10;
        SyntaxRule syntaxRule10;
        SyntaxPattern syntaxPattern11;
        SyntaxTemplate syntaxTemplate4;
        SyntaxPattern syntaxPattern12;
        SyntaxPattern syntaxPattern13;
        SyntaxTemplate syntaxTemplate5;
        SyntaxTemplate syntaxTemplate6;
        SyntaxPattern syntaxPattern14;
        SyntaxTemplate syntaxTemplate7;
        SyntaxTemplate syntaxTemplate8;
        SimpleSymbol simpleSymbol43;
        SimpleSymbol simpleSymbol44;
        SyntaxPattern syntaxPattern15;
        SimpleSymbol simpleSymbol45;
        SyntaxRules syntaxRules4;
        SimpleSymbol simpleSymbol46;
        SyntaxRule syntaxRule11;
        SyntaxPattern syntaxPattern16;
        SyntaxRule syntaxRule12;
        SyntaxPattern syntaxPattern17;
        SimpleSymbol simpleSymbol47;
        SyntaxRules syntaxRules5;
        SyntaxRule syntaxRule13;
        SyntaxPattern syntaxPattern18;
        SyntaxRule syntaxRule14;
        SyntaxPattern syntaxPattern19;
        SyntaxRule syntaxRule15;
        SyntaxPattern syntaxPattern20;
        SyntaxRule syntaxRule16;
        SyntaxPattern syntaxPattern21;
        SyntaxRule syntaxRule17;
        SyntaxPattern syntaxPattern22;
        SyntaxRule syntaxRule18;
        SyntaxPattern syntaxPattern23;
        SyntaxTemplate syntaxTemplate9;
        SyntaxTemplate syntaxTemplate10;
        SyntaxTemplate syntaxTemplate11;
        SyntaxTemplate syntaxTemplate12;
        SyntaxPattern syntaxPattern24;
        SyntaxTemplate syntaxTemplate13;
        SyntaxTemplate syntaxTemplate14;
        SyntaxTemplate syntaxTemplate15;
        SyntaxTemplate syntaxTemplate16;
        SyntaxPattern syntaxPattern25;
        SimpleSymbol simpleSymbol48;
        SyntaxTemplate syntaxTemplate17;
        SyntaxPattern syntaxPattern26;
        SyntaxTemplate syntaxTemplate18;
        SyntaxTemplate syntaxTemplate19;
        SyntaxTemplate syntaxTemplate20;
        SyntaxTemplate syntaxTemplate21;
        SimpleSymbol simpleSymbol49;
        SyntaxPattern syntaxPattern27;
        SyntaxTemplate syntaxTemplate22;
        SyntaxTemplate syntaxTemplate23;
        SyntaxTemplate syntaxTemplate24;
        SyntaxTemplate syntaxTemplate25;
        SyntaxTemplate syntaxTemplate26;
        SyntaxTemplate syntaxTemplate27;
        SyntaxPattern syntaxPattern28;
        SimpleSymbol simpleSymbol50;
        SyntaxTemplate syntaxTemplate28;
        SyntaxTemplate syntaxTemplate29;
        SyntaxTemplate syntaxTemplate30;
        SyntaxTemplate syntaxTemplate31;
        SyntaxTemplate syntaxTemplate32;
        SyntaxTemplate syntaxTemplate33;
        SyntaxPattern syntaxPattern29;
        SimpleSymbol simpleSymbol51;
        SyntaxTemplate syntaxTemplate34;
        SimpleSymbol simpleSymbol52;
        SyntaxTemplate syntaxTemplate35;
        SyntaxTemplate syntaxTemplate36;
        SyntaxTemplate syntaxTemplate37;
        SyntaxTemplate syntaxTemplate38;
        SyntaxTemplate syntaxTemplate39;
        SyntaxPattern syntaxPattern30;
        SimpleSymbol simpleSymbol53;
        SyntaxRules syntaxRules6;
        SimpleSymbol simpleSymbol54;
        SyntaxRule syntaxRule19;
        SyntaxPattern syntaxPattern31;
        SimpleSymbol simpleSymbol55;
        SimpleSymbol simpleSymbol56;
        SimpleSymbol simpleSymbol57;
        SimpleSymbol simpleSymbol58;
        SimpleSymbol simpleSymbol59;
        SimpleSymbol simpleSymbol60;
        SimpleSymbol simpleSymbol61;
        SyntaxTemplate syntaxTemplate40;
        SyntaxTemplate syntaxTemplate41;
        SyntaxPattern syntaxPattern32;
        SimpleSymbol simpleSymbol62;
        SyntaxTemplate syntaxTemplate42;
        SyntaxTemplate syntaxTemplate43;
        SyntaxPattern syntaxPattern33;
        SimpleSymbol simpleSymbol63;
        SyntaxRules syntaxRules7;
        SimpleSymbol simpleSymbol64;
        SyntaxRule syntaxRule20;
        SyntaxPattern syntaxPattern34;
        SimpleSymbol simpleSymbol65;
        SyntaxRules syntaxRules8;
        SyntaxRule syntaxRule21;
        SyntaxPattern syntaxPattern35;
        SyntaxRules syntaxRules9;
        SimpleSymbol simpleSymbol66;
        SyntaxRule syntaxRule22;
        SyntaxPattern syntaxPattern36;
        SimpleSymbol simpleSymbol67;
        SimpleSymbol simpleSymbol68;
        SyntaxRules syntaxRules10;
        SimpleSymbol simpleSymbol69;
        SyntaxRule syntaxRule23;
        SyntaxPattern syntaxPattern37;
        SyntaxRule syntaxRule24;
        SyntaxPattern syntaxPattern38;
        SyntaxRules syntaxRules11;
        SimpleSymbol simpleSymbol70;
        SyntaxRule syntaxRule25;
        SyntaxPattern syntaxPattern39;
        SyntaxPattern syntaxPattern40;
        SyntaxPattern syntaxPattern41;
        SyntaxTemplate syntaxTemplate44;
        SyntaxTemplate syntaxTemplate45;
        SyntaxTemplate syntaxTemplate46;
        SyntaxPattern syntaxPattern42;
        SyntaxPattern syntaxPattern43;
        SyntaxPattern syntaxPattern44;
        SyntaxTemplate syntaxTemplate47;
        SyntaxTemplate syntaxTemplate48;
        SyntaxPattern syntaxPattern45;
        syntax syntax;
        Procedure procedure;
        Procedure procedure2;
        ModuleMethod moduleMethod;
        ModuleMethod moduleMethod2;
        ModuleMethod moduleMethod3;
        ModuleMethod moduleMethod4;
        ModuleMethod moduleMethod5;
        ModuleMethod moduleMethod6;
        ModuleMethod moduleMethod7;
        Procedure procedure3;
        Procedure procedure4;
        Procedure procedure5;
        new SimpleSymbol("%define-macro");
        Lit123 = (SimpleSymbol) simpleSymbol.readResolve();
        new SimpleSymbol("form");
        Lit122 = (SimpleSymbol) simpleSymbol2.readResolve();
        new SimpleSymbol("if");
        Lit121 = (SimpleSymbol) simpleSymbol3.readResolve();
        new SimpleSymbol("prefix");
        Lit120 = (SimpleSymbol) simpleSymbol4.readResolve();
        new SimpleSymbol("instance");
        Lit119 = (SimpleSymbol) simpleSymbol5.readResolve();
        new SimpleSymbol("kawa.standard.ImportFromLibrary");
        Lit118 = (SimpleSymbol) simpleSymbol6.readResolve();
        new SimpleSymbol("x");
        Lit117 = (SimpleSymbol) simpleSymbol7.readResolve();
        new SimpleSymbol("call-with-values");
        Lit116 = (SimpleSymbol) simpleSymbol8.readResolve();
        new SimpleSymbol("let");
        Lit115 = (SimpleSymbol) simpleSymbol9.readResolve();
        new SimpleSymbol("not");
        Lit114 = (SimpleSymbol) simpleSymbol10.readResolve();
        new SimpleSymbol("or");
        Lit113 = (SimpleSymbol) simpleSymbol11.readResolve();
        new SimpleSymbol("and");
        Lit112 = (SimpleSymbol) simpleSymbol12.readResolve();
        new SimpleSymbol("else");
        Lit111 = (SimpleSymbol) simpleSymbol13.readResolve();
        new SimpleSymbol("begin");
        Lit110 = (SimpleSymbol) simpleSymbol14.readResolve();
        new SimpleSymbol("lambda");
        Lit109 = (SimpleSymbol) simpleSymbol15.readResolve();
        new SimpleSymbol(LispLanguage.quote_sym);
        Lit108 = (SimpleSymbol) simpleSymbol16.readResolve();
        new SimpleSymbol("wt");
        Lit107 = (SimpleSymbol) simpleSymbol17.readResolve();
        new SimpleSymbol("as");
        Lit106 = (SimpleSymbol) simpleSymbol18.readResolve();
        new SimpleSymbol("arg");
        Lit105 = (SimpleSymbol) simpleSymbol19.readResolve();
        new SimpleSymbol(LispLanguage.quasiquote_sym);
        Lit104 = (SimpleSymbol) simpleSymbol20.readResolve();
        new SimpleSymbol("gnu.mapping.LocationProc");
        Lit103 = (SimpleSymbol) simpleSymbol21.readResolve();
        new SimpleSymbol("$lookup$");
        Lit102 = (SimpleSymbol) simpleSymbol22.readResolve();
        SyntaxRules syntaxRules12 = syntaxRules;
        Object[] objArr = new Object[1];
        Object[] objArr2 = objArr;
        Object[] objArr3 = objArr;
        new SimpleSymbol("define-alias-parameter");
        SimpleSymbol simpleSymbol71 = (SimpleSymbol) simpleSymbol23.readResolve();
        Lit100 = simpleSymbol71;
        objArr3[0] = simpleSymbol71;
        SyntaxRule[] syntaxRuleArr = new SyntaxRule[1];
        SyntaxRule[] syntaxRuleArr2 = syntaxRuleArr;
        SyntaxRule[] syntaxRuleArr3 = syntaxRuleArr;
        SyntaxRule syntaxRule26 = syntaxRule;
        SyntaxPattern syntaxPattern46 = syntaxPattern;
        new SyntaxPattern("\f\u0018\f\u0007\f\u000f\f\u0017\b", new Object[0], 3);
        Object[] objArr4 = new Object[21];
        objArr4[0] = Lit110;
        Object[] objArr5 = objArr4;
        new SimpleSymbol("define-constant");
        objArr5[1] = (SimpleSymbol) simpleSymbol24.readResolve();
        Object[] objArr6 = objArr5;
        new SimpleSymbol("::");
        objArr6[2] = (SimpleSymbol) simpleSymbol25.readResolve();
        Object[] objArr7 = objArr6;
        new SimpleSymbol("<gnu.mapping.LocationProc>");
        objArr7[3] = (SimpleSymbol) simpleSymbol26.readResolve();
        Object[] objArr8 = objArr7;
        SimpleSymbol simpleSymbol72 = Lit102;
        SimpleSymbol simpleSymbol73 = Lit103;
        SimpleSymbol simpleSymbol74 = Lit104;
        new SimpleSymbol("makeNamed");
        objArr8[4] = PairWithPosition.make(simpleSymbol72, Pair.make(simpleSymbol73, Pair.make(Pair.make(simpleSymbol74, Pair.make((SimpleSymbol) simpleSymbol27.readResolve(), LList.Empty)), LList.Empty)), "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1069060);
        Object[] objArr9 = objArr8;
        objArr9[5] = Lit108;
        Object[] objArr10 = objArr9;
        SimpleSymbol simpleSymbol75 = Lit102;
        SimpleSymbol simpleSymbol76 = Lit103;
        SimpleSymbol simpleSymbol77 = Lit104;
        new SimpleSymbol("pushConverter");
        objArr10[6] = PairWithPosition.make(simpleSymbol75, Pair.make(simpleSymbol76, Pair.make(Pair.make(simpleSymbol77, Pair.make((SimpleSymbol) simpleSymbol28.readResolve(), LList.Empty)), LList.Empty)), "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1073161);
        Object[] objArr11 = objArr10;
        objArr11[7] = Lit109;
        Object[] objArr12 = objArr11;
        objArr12[8] = PairWithPosition.make(Lit105, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1081354);
        Object[] objArr13 = objArr12;
        new SimpleSymbol("try-catch");
        objArr13[9] = (SimpleSymbol) simpleSymbol29.readResolve();
        Object[] objArr14 = objArr13;
        objArr14[10] = Lit106;
        Object[] objArr15 = objArr14;
        objArr15[11] = PairWithPosition.make(Lit105, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1089550);
        Object[] objArr16 = objArr15;
        new SimpleSymbol("ex");
        objArr16[12] = (SimpleSymbol) simpleSymbol30.readResolve();
        Object[] objArr17 = objArr16;
        new SimpleSymbol("<java.lang.ClassCastException>");
        objArr17[13] = (SimpleSymbol) simpleSymbol31.readResolve();
        Object[] objArr18 = objArr17;
        objArr18[14] = Lit115;
        Object[] objArr19 = objArr18;
        objArr19[15] = Lit107;
        Object[] objArr20 = objArr19;
        SimpleSymbol simpleSymbol78 = Lit102;
        new SimpleSymbol("gnu.mapping.WrongType");
        SimpleSymbol simpleSymbol79 = Lit104;
        new SimpleSymbol("make");
        objArr20[16] = PairWithPosition.make(simpleSymbol78, Pair.make((SimpleSymbol) simpleSymbol32.readResolve(), Pair.make(Pair.make(simpleSymbol79, Pair.make((SimpleSymbol) simpleSymbol33.readResolve(), LList.Empty)), LList.Empty)), "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1097748);
        Object[] objArr21 = objArr20;
        SimpleSymbol simpleSymbol80 = Lit106;
        new SimpleSymbol("<int>");
        objArr21[17] = PairWithPosition.make(PairWithPosition.make(simpleSymbol80, PairWithPosition.make((SimpleSymbol) simpleSymbol34.readResolve(), PairWithPosition.make(IntNum.make(1), LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1101846), "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1101840), "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1101836), PairWithPosition.make(Lit105, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1101849), "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1101836);
        Object[] objArr22 = objArr21;
        new SimpleSymbol("set!");
        objArr22[18] = (SimpleSymbol) simpleSymbol35.readResolve();
        Object[] objArr23 = objArr22;
        new SimpleSymbol("field");
        SimpleSymbol simpleSymbol81 = Lit107;
        SimpleSymbol simpleSymbol82 = Lit108;
        new SimpleSymbol("expectedType");
        objArr23[19] = PairWithPosition.make((SimpleSymbol) simpleSymbol36.readResolve(), PairWithPosition.make(simpleSymbol81, PairWithPosition.make(PairWithPosition.make(simpleSymbol82, PairWithPosition.make((SimpleSymbol) simpleSymbol37.readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1105941), "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1105941), LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1105940), "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1105937), "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1105930);
        Object[] objArr24 = objArr23;
        new SimpleSymbol("primitive-throw");
        objArr24[20] = PairWithPosition.make(PairWithPosition.make((SimpleSymbol) simpleSymbol38.readResolve(), PairWithPosition.make(Lit107, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1110037), "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1110020), LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 1110020);
        new SyntaxRule(syntaxPattern46, "\u0001\u0001\u0001", "\u0011\u0018\u0004¹\u0011\u0018\f\t\u0003\u0011\u0018\u0014\u0011\u0018\u001c\b\u0011\u0018$)\u0011\u0018,\b\u0003\b\u0013\b\u0011\u00184\t\u0003\b\u0011\u0018<\u0011\u0018D\b\u0011\u0018L9\u0011\u0018T\t\u000b\u0018\\\b\u0011\u0018d\u0011\u0018l\b\u0011\u0018ty\b\u0011\u0018|\b\u0011\u0018\u0011\u0018d\t\u0003\u0018A\u0011\u0018\u0011\u0018\b\u000b\u0018¤", objArr24, 0);
        syntaxRuleArr3[0] = syntaxRule26;
        new SyntaxRules(objArr2, syntaxRuleArr2, 3);
        Lit101 = syntaxRules12;
        SyntaxRules syntaxRules13 = syntaxRules2;
        Object[] objArr25 = new Object[1];
        Object[] objArr26 = objArr25;
        Object[] objArr27 = objArr25;
        new SimpleSymbol("receive");
        SimpleSymbol simpleSymbol83 = (SimpleSymbol) simpleSymbol39.readResolve();
        Lit98 = simpleSymbol83;
        objArr27[0] = simpleSymbol83;
        SyntaxRule[] syntaxRuleArr4 = new SyntaxRule[1];
        SyntaxRule[] syntaxRuleArr5 = syntaxRuleArr4;
        SyntaxRule[] syntaxRuleArr6 = syntaxRuleArr4;
        SyntaxRule syntaxRule27 = syntaxRule2;
        SyntaxPattern syntaxPattern47 = syntaxPattern2;
        new SyntaxPattern("\f\u0018\f\u0007\f\u000f\r\u0017\u0010\b\b", new Object[0], 3);
        Object[] objArr28 = new Object[2];
        objArr28[0] = Lit116;
        Object[] objArr29 = objArr28;
        objArr29[1] = Lit109;
        new SyntaxRule(syntaxPattern47, "\u0001\u0001\u0003", "\u0011\u0018\u00049\u0011\u0018\f\t\u0010\b\u000b\b\u0011\u0018\f\t\u0003\b\u0015\u0013", objArr29, 1);
        syntaxRuleArr6[0] = syntaxRule27;
        new SyntaxRules(objArr26, syntaxRuleArr5, 3);
        Lit99 = syntaxRules13;
        SyntaxTemplate syntaxTemplate49 = syntaxTemplate;
        Object[] objArr30 = new Object[1];
        Object[] objArr31 = objArr30;
        Object[] objArr32 = objArr30;
        new SimpleSymbol("cond-expand");
        SimpleSymbol simpleSymbol84 = (SimpleSymbol) simpleSymbol40.readResolve();
        Lit91 = simpleSymbol84;
        objArr32[0] = simpleSymbol84;
        new SyntaxTemplate("\u0001\u0001\u0000\u0000", "\u0011\u0018\u0004\u001a", objArr31, 0);
        Lit97 = syntaxTemplate49;
        SyntaxTemplate syntaxTemplate50 = syntaxTemplate2;
        new SyntaxTemplate("\u0001\u0001\u0000\u0000", "\u0011\u0018\u0004\u0012", new Object[]{Lit110}, 0);
        Lit96 = syntaxTemplate50;
        new SyntaxTemplate("\u0001\u0001\u0000\u0000", "\u000b", new Object[0], 0);
        Lit95 = syntaxTemplate3;
        new SyntaxPattern("\f\u0007\u001c\f\u000f\u0013\u001b", new Object[0], 4);
        Lit94 = syntaxPattern3;
        new SimpleSymbol("%cond-expand");
        Lit93 = (SimpleSymbol) simpleSymbol41.readResolve();
        SyntaxRules syntaxRules14 = syntaxRules3;
        Object[] objArr33 = new Object[5];
        objArr33[0] = Lit91;
        Object[] objArr34 = objArr33;
        objArr34[1] = Lit112;
        Object[] objArr35 = objArr34;
        objArr35[2] = Lit113;
        Object[] objArr36 = objArr35;
        objArr36[3] = Lit114;
        Object[] objArr37 = objArr36;
        Object[] objArr38 = objArr37;
        objArr37[4] = Lit111;
        SyntaxRule[] syntaxRuleArr7 = new SyntaxRule[8];
        SyntaxRule[] syntaxRuleArr8 = syntaxRuleArr7;
        SyntaxRule[] syntaxRuleArr9 = syntaxRuleArr7;
        SyntaxRule syntaxRule28 = syntaxRule3;
        new SyntaxPattern("\f\u0018\b", new Object[0], 0);
        new SimpleSymbol("%syntax-error");
        new SyntaxRule(syntaxPattern4, "", "\u0018\u0004", new Object[]{PairWithPosition.make((SimpleSymbol) simpleSymbol42.readResolve(), PairWithPosition.make("Unfulfilled cond-expand", LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 802851), "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 802836)}, 0);
        syntaxRuleArr9[0] = syntaxRule28;
        SyntaxRule[] syntaxRuleArr10 = syntaxRuleArr8;
        SyntaxRule[] syntaxRuleArr11 = syntaxRuleArr10;
        SyntaxRule[] syntaxRuleArr12 = syntaxRuleArr10;
        SyntaxRule syntaxRule29 = syntaxRule4;
        SyntaxPattern syntaxPattern48 = syntaxPattern5;
        new SyntaxPattern("\f\u0018<\f\u0002\r\u0007\u0000\b\b\b", new Object[]{Lit111}, 1);
        new SyntaxRule(syntaxPattern48, "\u0003", "\u0011\u0018\u0004\b\u0005\u0003", new Object[]{Lit110}, 1);
        syntaxRuleArr12[1] = syntaxRule29;
        SyntaxRule[] syntaxRuleArr13 = syntaxRuleArr11;
        SyntaxRule[] syntaxRuleArr14 = syntaxRuleArr13;
        SyntaxRule[] syntaxRuleArr15 = syntaxRuleArr13;
        SyntaxRule syntaxRule30 = syntaxRule5;
        SyntaxPattern syntaxPattern49 = syntaxPattern6;
        new SyntaxPattern("\f\u0018L\u001c\f\u0002\b\r\u0007\u0000\b\b\r\u000f\b\b\b", new Object[]{Lit112}, 2);
        new SyntaxRule(syntaxPattern49, "\u0003\u0003", "\u0011\u0018\u0004\b\u0005\u0003", new Object[]{Lit110}, 1);
        syntaxRuleArr15[2] = syntaxRule30;
        SyntaxRule[] syntaxRuleArr16 = syntaxRuleArr14;
        SyntaxRule[] syntaxRuleArr17 = syntaxRuleArr16;
        SyntaxRule[] syntaxRuleArr18 = syntaxRuleArr16;
        SyntaxRule syntaxRule31 = syntaxRule6;
        SyntaxPattern syntaxPattern50 = syntaxPattern7;
        new SyntaxPattern("\f\u0018|L\f\u0002\f\u0007\r\u000f\b\b\b\r\u0017\u0010\b\b\r\u001f\u0018\b\b", new Object[]{Lit112}, 4);
        Object[] objArr39 = new Object[2];
        objArr39[0] = Lit91;
        Object[] objArr40 = objArr39;
        objArr40[1] = Lit112;
        new SyntaxRule(syntaxPattern50, "\u0001\u0003\u0003\u0003", "\u0011\u0018\u0004¡\t\u0003\b\u0011\u0018\u0004Q1\u0011\u0018\f\b\r\u000b\b\u0015\u0013\b\u001d\u001b\b\u001d\u001b", objArr40, 1);
        syntaxRuleArr18[3] = syntaxRule31;
        SyntaxRule[] syntaxRuleArr19 = syntaxRuleArr17;
        SyntaxRule[] syntaxRuleArr20 = syntaxRuleArr19;
        SyntaxRule[] syntaxRuleArr21 = syntaxRuleArr19;
        SyntaxRule syntaxRule32 = syntaxRule7;
        SyntaxPattern syntaxPattern51 = syntaxPattern8;
        new SyntaxPattern("\f\u0018L\u001c\f\u0002\b\r\u0007\u0000\b\b\r\u000f\b\b\b", new Object[]{Lit113}, 2);
        new SyntaxRule(syntaxPattern51, "\u0003\u0003", "\u0011\u0018\u0004\b\r\u000b", new Object[]{Lit91}, 1);
        syntaxRuleArr21[4] = syntaxRule32;
        SyntaxRule[] syntaxRuleArr22 = syntaxRuleArr20;
        SyntaxRule[] syntaxRuleArr23 = syntaxRuleArr22;
        SyntaxRule[] syntaxRuleArr24 = syntaxRuleArr22;
        SyntaxRule syntaxRule33 = syntaxRule8;
        SyntaxPattern syntaxPattern52 = syntaxPattern9;
        new SyntaxPattern("\f\u0018|L\f\u0002\f\u0007\r\u000f\b\b\b\r\u0017\u0010\b\b\r\u001f\u0018\b\b", new Object[]{Lit113}, 4);
        Object[] objArr41 = new Object[4];
        objArr41[0] = Lit91;
        Object[] objArr42 = objArr41;
        objArr42[1] = Lit110;
        Object[] objArr43 = objArr42;
        objArr43[2] = Lit111;
        Object[] objArr44 = objArr43;
        objArr44[3] = Lit113;
        new SyntaxRule(syntaxPattern52, "\u0001\u0003\u0003\u0003", "\u0011\u0018\u0004I\t\u0003\b\u0011\u0018\f\b\u0015\u0013\b\u0011\u0018\u0014\b\u0011\u0018\u0004Q1\u0011\u0018\u001c\b\r\u000b\b\u0015\u0013\b\u001d\u001b", objArr44, 1);
        syntaxRuleArr24[5] = syntaxRule33;
        SyntaxRule[] syntaxRuleArr25 = syntaxRuleArr23;
        SyntaxRule[] syntaxRuleArr26 = syntaxRuleArr25;
        SyntaxRule[] syntaxRuleArr27 = syntaxRuleArr25;
        SyntaxRule syntaxRule34 = syntaxRule9;
        SyntaxPattern syntaxPattern53 = syntaxPattern10;
        new SyntaxPattern("\f\u0018\\,\f\u0002\f\u0007\b\r\u000f\b\b\b\r\u0017\u0010\b\b", new Object[]{Lit114}, 3);
        Object[] objArr45 = new Object[2];
        objArr45[0] = Lit91;
        Object[] objArr46 = objArr45;
        objArr46[1] = Lit111;
        new SyntaxRule(syntaxPattern53, "\u0001\u0003\u0003", "\u0011\u0018\u0004I\t\u0003\b\u0011\u0018\u0004\b\u0015\u0013\b\u0011\u0018\f\b\r\u000b", objArr46, 1);
        syntaxRuleArr27[6] = syntaxRule34;
        SyntaxRule[] syntaxRuleArr28 = syntaxRuleArr26;
        SyntaxRule[] syntaxRuleArr29 = syntaxRuleArr28;
        SyntaxRule[] syntaxRuleArr30 = syntaxRuleArr28;
        SyntaxRule syntaxRule35 = syntaxRule10;
        new SyntaxPattern("\f\u0018\u001c\f\u0007\u000b\u0013", new Object[0], 3);
        new SyntaxRule(syntaxPattern11, "\u0001\u0000\u0000", "\u0011\u0018\u0004\u0019\t\u0003\n\u0012", new Object[]{Lit93}, 0);
        syntaxRuleArr30[7] = syntaxRule35;
        new SyntaxRules(objArr38, syntaxRuleArr29, 4);
        Lit92 = syntaxRules14;
        new SyntaxTemplate("\u0001\u0000\u0000", "\u0012", new Object[0], 0);
        Lit90 = syntaxTemplate4;
        new SyntaxPattern("\u0013", new Object[0], 3);
        Lit89 = syntaxPattern12;
        new SyntaxPattern("\b", new Object[0], 2);
        Lit88 = syntaxPattern13;
        new SyntaxTemplate("\u0001\u0000\u0001\u0000\u0000", "\"", new Object[0], 0);
        Lit87 = syntaxTemplate5;
        SyntaxTemplate syntaxTemplate51 = syntaxTemplate6;
        new SyntaxTemplate("\u0001\u0000\u0001\u0000\u0000", "\u0011\u0018\u0004\t\u0013\u001a", new Object[]{Lit109}, 0);
        Lit86 = syntaxTemplate51;
        new SyntaxPattern("\u001c\f\u0017\u001b#", new Object[0], 5);
        Lit85 = syntaxPattern14;
        new SyntaxTemplate("\u0001\u0000", "\n", new Object[0], 0);
        Lit84 = syntaxTemplate7;
        SyntaxTemplate syntaxTemplate52 = syntaxTemplate8;
        SimpleSymbol simpleSymbol85 = Lit102;
        new SimpleSymbol("gnu.expr.GenericProc");
        SimpleSymbol simpleSymbol86 = Lit104;
        new SimpleSymbol("makeWithoutSorting");
        new SyntaxTemplate("\u0001\u0000", "\u0018\u0004", new Object[]{PairWithPosition.make(simpleSymbol85, Pair.make((SimpleSymbol) simpleSymbol43.readResolve(), Pair.make(Pair.make(simpleSymbol86, Pair.make((SimpleSymbol) simpleSymbol44.readResolve(), LList.Empty)), LList.Empty)), "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 651273)}, 0);
        Lit83 = syntaxTemplate52;
        new SyntaxPattern("\f\u0007\u000b", new Object[0], 2);
        Lit82 = syntaxPattern15;
        new SimpleSymbol("case-lambda");
        Lit81 = (SimpleSymbol) simpleSymbol45.readResolve();
        SyntaxRules syntaxRules15 = syntaxRules4;
        Object[] objArr47 = new Object[1];
        Object[] objArr48 = objArr47;
        Object[] objArr49 = objArr47;
        new SimpleSymbol("let*-values");
        SimpleSymbol simpleSymbol87 = (SimpleSymbol) simpleSymbol46.readResolve();
        Lit79 = simpleSymbol87;
        objArr49[0] = simpleSymbol87;
        SyntaxRule[] syntaxRuleArr31 = new SyntaxRule[2];
        SyntaxRule[] syntaxRuleArr32 = syntaxRuleArr31;
        SyntaxRule[] syntaxRuleArr33 = syntaxRuleArr31;
        SyntaxRule syntaxRule36 = syntaxRule11;
        new SyntaxPattern("\f\u0018\f\b\f\u0007\r\u000f\b\b\b", new Object[0], 2);
        new SyntaxRule(syntaxPattern16, "\u0001\u0003", "\u0011\u0018\u0004\t\u0003\b\r\u000b", new Object[]{Lit110}, 1);
        syntaxRuleArr33[0] = syntaxRule36;
        SyntaxRule[] syntaxRuleArr34 = syntaxRuleArr32;
        SyntaxRule[] syntaxRuleArr35 = syntaxRuleArr34;
        SyntaxRule[] syntaxRuleArr36 = syntaxRuleArr34;
        SyntaxRule syntaxRule37 = syntaxRule12;
        SyntaxPattern syntaxPattern54 = syntaxPattern17;
        new SyntaxPattern("\f\u0018<\f\u0007\r\u000f\b\b\b\f\u0017\r\u001f\u0018\b\b", new Object[0], 4);
        Object[] objArr50 = new Object[2];
        Object[] objArr51 = objArr50;
        Object[] objArr52 = objArr50;
        new SimpleSymbol("let-values");
        SimpleSymbol simpleSymbol88 = (SimpleSymbol) simpleSymbol47.readResolve();
        Lit77 = simpleSymbol88;
        objArr52[0] = simpleSymbol88;
        Object[] objArr53 = objArr51;
        objArr53[1] = Lit79;
        new SyntaxRule(syntaxPattern54, "\u0001\u0003\u0001\u0003", "\u0011\u0018\u0004\u0011\b\u0003\b\u0011\u0018\f\u0019\b\r\u000b\t\u0013\b\u001d\u001b", objArr53, 1);
        syntaxRuleArr36[1] = syntaxRule37;
        new SyntaxRules(objArr48, syntaxRuleArr35, 4);
        Lit80 = syntaxRules15;
        SyntaxRules syntaxRules16 = syntaxRules5;
        Object[] objArr54 = new Object[1];
        Object[] objArr55 = objArr54;
        objArr54[0] = Lit77;
        SyntaxRule[] syntaxRuleArr37 = new SyntaxRule[6];
        SyntaxRule[] syntaxRuleArr38 = syntaxRuleArr37;
        SyntaxRule[] syntaxRuleArr39 = syntaxRuleArr37;
        SyntaxRule syntaxRule38 = syntaxRule13;
        SyntaxPattern syntaxPattern55 = syntaxPattern18;
        new SyntaxPattern("\f\u0018,\r\u0007\u0000\b\b\f\u000f\r\u0017\u0010\b\b", new Object[0], 3);
        Object[] objArr56 = new Object[3];
        objArr56[0] = Lit77;
        Object[] objArr57 = objArr56;
        objArr57[1] = "bind";
        Object[] objArr58 = objArr57;
        objArr58[2] = Lit110;
        new SyntaxRule(syntaxPattern55, "\u0003\u0001\u0003", "\u0011\u0018\u0004\u0011\u0018\f\u0019\b\u0005\u0003\t\u0010\b\u0011\u0018\u0014\t\u000b\b\u0015\u0013", objArr58, 1);
        syntaxRuleArr39[0] = syntaxRule38;
        SyntaxRule[] syntaxRuleArr40 = syntaxRuleArr38;
        SyntaxRule[] syntaxRuleArr41 = syntaxRuleArr40;
        SyntaxRule[] syntaxRuleArr42 = syntaxRuleArr40;
        SyntaxRule syntaxRule39 = syntaxRule14;
        SyntaxPattern syntaxPattern56 = syntaxPattern19;
        new SyntaxPattern("\f\u0018\f\u0002\f\b\f\u0007\f\u000f\b", new Object[]{"bind"}, 2);
        new SyntaxRule(syntaxPattern56, "\u0001\u0001", "\u0011\u0018\u0004\t\u0003\b\u000b", new Object[]{Lit115}, 0);
        syntaxRuleArr42[1] = syntaxRule39;
        SyntaxRule[] syntaxRuleArr43 = syntaxRuleArr41;
        SyntaxRule[] syntaxRuleArr44 = syntaxRuleArr43;
        SyntaxRule[] syntaxRuleArr45 = syntaxRuleArr43;
        SyntaxRule syntaxRule40 = syntaxRule15;
        SyntaxPattern syntaxPattern57 = syntaxPattern20;
        new SyntaxPattern("\f\u0018\f\u0002\\,\f\u0007\f\u000f\b\r\u0017\u0010\b\b\f\u001f\f'\b", new Object[]{"bind"}, 5);
        Object[] objArr59 = new Object[2];
        objArr59[0] = Lit77;
        Object[] objArr60 = objArr59;
        objArr60[1] = "mktmp";
        new SyntaxRule(syntaxPattern57, "\u0001\u0001\u0003\u0001\u0001", "\u0011\u0018\u0004\u0011\u0018\f\t\u0003\t\u000b\t\u0010\u0019\b\u0015\u0013\t\u001b\b#", objArr60, 1);
        syntaxRuleArr45[2] = syntaxRule40;
        SyntaxRule[] syntaxRuleArr46 = syntaxRuleArr44;
        SyntaxRule[] syntaxRuleArr47 = syntaxRuleArr46;
        SyntaxRule[] syntaxRuleArr48 = syntaxRuleArr46;
        SyntaxRule syntaxRule41 = syntaxRule16;
        SyntaxPattern syntaxPattern58 = syntaxPattern21;
        new SyntaxPattern("\f\u0018\f\u0002\f\b\f\u0007\f\u000f\f\u0017\f\u001f\f'\b", new Object[]{"mktmp"}, 5);
        Object[] objArr61 = new Object[4];
        objArr61[0] = Lit116;
        Object[] objArr62 = objArr61;
        objArr62[1] = Lit109;
        Object[] objArr63 = objArr62;
        objArr63[2] = Lit77;
        Object[] objArr64 = objArr63;
        objArr64[3] = "bind";
        new SyntaxRule(syntaxPattern58, "\u0001\u0001\u0001\u0001\u0001", "\u0011\u0018\u00049\u0011\u0018\f\t\u0010\b\u0003\b\u0011\u0018\f\t\u000b\b\u0011\u0018\u0014\u0011\u0018\u001c\t\u0013\t\u001b\b#", objArr64, 0);
        syntaxRuleArr48[3] = syntaxRule41;
        SyntaxRule[] syntaxRuleArr49 = syntaxRuleArr47;
        SyntaxRule[] syntaxRuleArr50 = syntaxRuleArr49;
        SyntaxRule[] syntaxRuleArr51 = syntaxRuleArr49;
        SyntaxRule syntaxRule42 = syntaxRule17;
        SyntaxPattern syntaxPattern59 = syntaxPattern22;
        new SyntaxPattern("\f\u0018\f\u0002\u001c\f\u0007\u000b\f\u0017,\r\u001f\u0018\b\b\f',\r/(\b\b\f7\b", new Object[]{"mktmp"}, 7);
        Object[] objArr65 = new Object[4];
        objArr65[0] = Lit77;
        Object[] objArr66 = objArr65;
        objArr66[1] = "mktmp";
        Object[] objArr67 = objArr66;
        objArr67[2] = PairWithPosition.make(Lit117, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 569387);
        Object[] objArr68 = objArr67;
        objArr68[3] = PairWithPosition.make(Lit117, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 569414);
        new SyntaxRule(syntaxPattern59, "\u0001\u0000\u0001\u0003\u0001\u0003\u0001", "\u0011\u0018\u0004\u0011\u0018\f\t\n\t\u0013)\u0011\u001d\u001b\u0018\u0014\t#A\u0011-+\b\t\u0003\u0018\u001c\b3", objArr68, 1);
        syntaxRuleArr51[4] = syntaxRule42;
        SyntaxRule[] syntaxRuleArr52 = syntaxRuleArr50;
        SyntaxRule[] syntaxRuleArr53 = syntaxRuleArr52;
        SyntaxRule[] syntaxRuleArr54 = syntaxRuleArr52;
        SyntaxRule syntaxRule43 = syntaxRule18;
        SyntaxPattern syntaxPattern60 = syntaxPattern23;
        new SyntaxPattern("\f\u0018\f\u0002\f\u0007\f\u000f,\r\u0017\u0010\b\b\f\u001f,\r' \b\b\f/\b", new Object[]{"mktmp"}, 6);
        Object[] objArr69 = new Object[6];
        objArr69[0] = Lit116;
        Object[] objArr70 = objArr69;
        objArr70[1] = Lit109;
        Object[] objArr71 = objArr70;
        objArr71[2] = Lit117;
        Object[] objArr72 = objArr71;
        objArr72[3] = Lit77;
        Object[] objArr73 = objArr72;
        objArr73[4] = "bind";
        Object[] objArr74 = objArr73;
        objArr74[5] = PairWithPosition.make(Lit117, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 593973);
        new SyntaxRule(syntaxPattern60, "\u0001\u0001\u0003\u0001\u0003\u0001", "\u0011\u0018\u00049\u0011\u0018\f\t\u0010\b\u000b\b\u0011\u0018\f)\u0011\u0015\u0013\u0018\u0014\b\u0011\u0018\u001c\u0011\u0018$\t\u001bA\u0011%#\b\t\u0003\u0018,\b+", objArr74, 1);
        syntaxRuleArr54[5] = syntaxRule43;
        new SyntaxRules(objArr55, syntaxRuleArr53, 7);
        Lit78 = syntaxRules16;
        new SyntaxTemplate("\u0001\u0001\u0001", "\u0010", new Object[0], 0);
        Lit76 = syntaxTemplate9;
        new SyntaxTemplate("\u0001\u0001\u0001", "\u0013", new Object[0], 0);
        Lit75 = syntaxTemplate10;
        new SyntaxTemplate("\u0001\u0001\u0001", "\b\u000b", new Object[0], 0);
        Lit74 = syntaxTemplate11;
        SyntaxTemplate syntaxTemplate53 = syntaxTemplate12;
        new SyntaxTemplate("\u0001\u0001\u0001", "\u0018\u0004", new Object[]{PairWithPosition.make(Lit102, Pair.make(Lit118, Pair.make(Pair.make(Lit104, Pair.make(Lit119, LList.Empty)), LList.Empty)), "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 466951)}, 0);
        Lit73 = syntaxTemplate53;
        new SyntaxPattern("\f\u0007\f\u000f\f\u0017\b", new Object[0], 3);
        Lit72 = syntaxPattern24;
        new SyntaxTemplate("\u0001\u0001\u0001", "\u0010", new Object[0], 0);
        Lit71 = syntaxTemplate13;
        new SyntaxTemplate("\u0001\u0001\u0001", "\u0013", new Object[0], 0);
        Lit70 = syntaxTemplate14;
        new SyntaxTemplate("\u0001\u0001\u0001", "\b\u000b", new Object[0], 0);
        Lit69 = syntaxTemplate15;
        SyntaxTemplate syntaxTemplate54 = syntaxTemplate16;
        new SyntaxTemplate("\u0001\u0001\u0001", "\u0018\u0004", new Object[]{PairWithPosition.make(Lit102, Pair.make(Lit118, Pair.make(Pair.make(Lit104, Pair.make(Lit119, LList.Empty)), LList.Empty)), "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 458759)}, 0);
        Lit68 = syntaxTemplate54;
        SyntaxPattern syntaxPattern61 = syntaxPattern25;
        new SimpleSymbol("library");
        new SyntaxPattern("\f\u0007,\f\u0002\f\u000f\b\f\u0017\b", new Object[]{(SimpleSymbol) simpleSymbol48.readResolve()}, 3);
        Lit67 = syntaxPattern61;
        new SyntaxTemplate("\u0001\u0001\u0000\u0001", "\u0012", new Object[0], 0);
        Lit66 = syntaxTemplate17;
        SyntaxPattern syntaxPattern62 = syntaxPattern26;
        new SyntaxPattern("\f\u0007,\f\u0002\f\u000f\u0013\f\u001f\b", new Object[]{Lit120}, 4);
        Lit65 = syntaxPattern62;
        new SyntaxTemplate("\u0001\u0001\u0001\u0001", "\u0010", new Object[0], 0);
        Lit64 = syntaxTemplate18;
        new SyntaxTemplate("\u0001\u0001\u0001\u0001", "\u001b", new Object[0], 0);
        Lit63 = syntaxTemplate19;
        new SyntaxTemplate("\u0001\u0001\u0001\u0001", "\u0013", new Object[0], 0);
        Lit62 = syntaxTemplate20;
        SyntaxTemplate syntaxTemplate55 = syntaxTemplate21;
        Object[] objArr75 = new Object[1];
        Object[] objArr76 = objArr75;
        Object[] objArr77 = objArr75;
        new SimpleSymbol("%import");
        SimpleSymbol simpleSymbol89 = (SimpleSymbol) simpleSymbol49.readResolve();
        Lit38 = simpleSymbol89;
        objArr77[0] = simpleSymbol89;
        new SyntaxTemplate("\u0001\u0001\u0001\u0001", "\u0011\u0018\u0004\b\u000b", objArr76, 0);
        Lit61 = syntaxTemplate55;
        SyntaxPattern syntaxPattern63 = syntaxPattern27;
        new SyntaxPattern("\f\u0007<\f\u0002\f\u000f\f\u0017\b\f\u001f\b", new Object[]{Lit120}, 4);
        Lit60 = syntaxPattern63;
        new SyntaxTemplate("\u0001\u0001\u0000\u0001", "\u0012", new Object[0], 0);
        Lit59 = syntaxTemplate22;
        new SyntaxTemplate("\u0001\u0001\u0000\u0001", "\u0010", new Object[0], 0);
        Lit58 = syntaxTemplate23;
        new SyntaxTemplate("\u0001\u0001\u0000\u0001", "\u001b", new Object[0], 0);
        Lit57 = syntaxTemplate24;
        new SyntaxTemplate("\u0001\u0001\u0000\u0001", "\u0012", new Object[0], 0);
        Lit56 = syntaxTemplate25;
        SyntaxTemplate syntaxTemplate56 = syntaxTemplate26;
        new SyntaxTemplate("\u0001\u0001\u0000\u0001", "\u0011\u0018\u0004\b\u000b", new Object[]{Lit38}, 0);
        Lit55 = syntaxTemplate56;
        new SyntaxTemplate("\u0001\u0001\u0000\u0001", "\u0012", new Object[0], 0);
        Lit54 = syntaxTemplate27;
        SyntaxPattern syntaxPattern64 = syntaxPattern28;
        new SimpleSymbol("except");
        new SyntaxPattern("\f\u0007,\f\u0002\f\u000f\u0013\f\u001f\b", new Object[]{(SimpleSymbol) simpleSymbol50.readResolve()}, 4);
        Lit53 = syntaxPattern64;
        new SyntaxTemplate("\u0001\u0001\u0000\u0001", "\u0012", new Object[0], 0);
        Lit52 = syntaxTemplate28;
        new SyntaxTemplate("\u0001\u0001\u0000\u0001", "\u0010", new Object[0], 0);
        Lit51 = syntaxTemplate29;
        new SyntaxTemplate("\u0001\u0001\u0000\u0001", "\u001b", new Object[0], 0);
        Lit50 = syntaxTemplate30;
        new SyntaxTemplate("\u0001\u0001\u0000\u0001", "\u0012", new Object[0], 0);
        Lit49 = syntaxTemplate31;
        SyntaxTemplate syntaxTemplate57 = syntaxTemplate32;
        new SyntaxTemplate("\u0001\u0001\u0000\u0001", "\u0011\u0018\u0004\b\u000b", new Object[]{Lit38}, 0);
        Lit48 = syntaxTemplate57;
        new SyntaxTemplate("\u0001\u0001\u0000\u0001", "\u0012", new Object[0], 0);
        Lit47 = syntaxTemplate33;
        SyntaxPattern syntaxPattern65 = syntaxPattern29;
        new SimpleSymbol("only");
        new SyntaxPattern("\f\u0007,\f\u0002\f\u000f\u0013\f\u001f\b", new Object[]{(SimpleSymbol) simpleSymbol51.readResolve()}, 4);
        Lit46 = syntaxPattern65;
        SyntaxTemplate syntaxTemplate58 = syntaxTemplate34;
        new SimpleSymbol("rest");
        new SyntaxTemplate("\u0001\u0001\u0000\u0001", "\u0018\u0004", new Object[]{(SimpleSymbol) simpleSymbol52.readResolve()}, 0);
        Lit45 = syntaxTemplate58;
        new SyntaxTemplate("\u0001\u0001\u0000\u0001", "\u0010", new Object[0], 0);
        Lit44 = syntaxTemplate35;
        new SyntaxTemplate("\u0001\u0001\u0000\u0001", "\u001b", new Object[0], 0);
        Lit43 = syntaxTemplate36;
        new SyntaxTemplate("\u0001\u0001\u0000\u0001", "\u0012", new Object[0], 0);
        Lit42 = syntaxTemplate37;
        SyntaxTemplate syntaxTemplate59 = syntaxTemplate38;
        new SyntaxTemplate("\u0001\u0001\u0000\u0001", "\u0011\u0018\u0004\b\u000b", new Object[]{Lit38}, 0);
        Lit41 = syntaxTemplate59;
        new SyntaxTemplate("\u0001\u0001\u0000\u0001", "\u0012", new Object[0], 0);
        Lit40 = syntaxTemplate39;
        SyntaxPattern syntaxPattern66 = syntaxPattern30;
        new SimpleSymbol("rename");
        new SyntaxPattern("\f\u0007,\f\u0002\f\u000f\u0013\f\u001f\b", new Object[]{(SimpleSymbol) simpleSymbol53.readResolve()}, 4);
        Lit39 = syntaxPattern66;
        SyntaxRules syntaxRules17 = syntaxRules6;
        Object[] objArr78 = new Object[1];
        Object[] objArr79 = objArr78;
        Object[] objArr80 = objArr78;
        new SimpleSymbol("import");
        SimpleSymbol simpleSymbol90 = (SimpleSymbol) simpleSymbol54.readResolve();
        Lit36 = simpleSymbol90;
        objArr80[0] = simpleSymbol90;
        SyntaxRule[] syntaxRuleArr55 = new SyntaxRule[1];
        SyntaxRule[] syntaxRuleArr56 = syntaxRuleArr55;
        SyntaxRule[] syntaxRuleArr57 = syntaxRuleArr55;
        SyntaxRule syntaxRule44 = syntaxRule19;
        SyntaxPattern syntaxPattern67 = syntaxPattern31;
        new SyntaxPattern("\f\u0018\r\u0007\u0000\b\b", new Object[0], 1);
        Object[] objArr81 = new Object[3];
        objArr81[0] = Lit110;
        Object[] objArr82 = objArr81;
        objArr82[1] = Lit38;
        Object[] objArr83 = objArr82;
        objArr83[2] = PairWithPosition.make(LList.Empty, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 376866);
        new SyntaxRule(syntaxPattern67, "\u0003", "\u0011\u0018\u0004\b\u0005\u0011\u0018\f\t\u0003\u0018\u0014", objArr83, 1);
        syntaxRuleArr57[0] = syntaxRule44;
        new SyntaxRules(objArr79, syntaxRuleArr56, 1);
        Lit37 = syntaxRules17;
        new SimpleSymbol("import-mapper");
        Lit35 = (SimpleSymbol) simpleSymbol55.readResolve();
        new SimpleSymbol("import-handle-rename");
        Lit34 = (SimpleSymbol) simpleSymbol56.readResolve();
        new SimpleSymbol("import-handle-prefix");
        Lit33 = (SimpleSymbol) simpleSymbol57.readResolve();
        new SimpleSymbol("import-handle-except");
        Lit32 = (SimpleSymbol) simpleSymbol58.readResolve();
        new SimpleSymbol("import-handle-only");
        Lit31 = (SimpleSymbol) simpleSymbol59.readResolve();
        new SimpleSymbol("identifier-pair-list?");
        Lit30 = (SimpleSymbol) simpleSymbol60.readResolve();
        new SimpleSymbol("identifier-list?");
        Lit29 = (SimpleSymbol) simpleSymbol61.readResolve();
        new SyntaxTemplate("\u0001\u0001\u0000", "\u0012", new Object[0], 0);
        Lit28 = syntaxTemplate40;
        new SyntaxTemplate("\u0001\u0001\u0000", "\u000b", new Object[0], 0);
        Lit27 = syntaxTemplate41;
        new SyntaxPattern("\f\u0007\f\u000f\u0013", new Object[0], 3);
        Lit26 = syntaxPattern32;
        new SimpleSymbol("synchronized");
        Lit25 = (SimpleSymbol) simpleSymbol62.readResolve();
        new SyntaxTemplate("\u0001\u0001\u0001", "\u0013", new Object[0], 0);
        Lit24 = syntaxTemplate42;
        new SyntaxTemplate("\u0001\u0001\u0001", "\u000b", new Object[0], 0);
        Lit23 = syntaxTemplate43;
        new SyntaxPattern("\f\u0007\f\u000f\f\u0017\b", new Object[0], 3);
        Lit22 = syntaxPattern33;
        new SimpleSymbol("try-finally");
        Lit21 = (SimpleSymbol) simpleSymbol63.readResolve();
        SyntaxRules syntaxRules18 = syntaxRules7;
        Object[] objArr84 = new Object[1];
        Object[] objArr85 = objArr84;
        Object[] objArr86 = objArr84;
        new SimpleSymbol("when");
        SimpleSymbol simpleSymbol91 = (SimpleSymbol) simpleSymbol64.readResolve();
        Lit17 = simpleSymbol91;
        objArr86[0] = simpleSymbol91;
        SyntaxRule[] syntaxRuleArr58 = new SyntaxRule[1];
        SyntaxRule[] syntaxRuleArr59 = syntaxRuleArr58;
        SyntaxRule[] syntaxRuleArr60 = syntaxRuleArr58;
        SyntaxRule syntaxRule45 = syntaxRule20;
        SyntaxPattern syntaxPattern68 = syntaxPattern34;
        new SyntaxPattern("\f\u0018\f\u0007\r\u000f\b\b\b", new Object[0], 2);
        Object[] objArr87 = new Object[3];
        objArr87[0] = Lit121;
        Object[] objArr88 = objArr87;
        objArr88[1] = Lit114;
        Object[] objArr89 = objArr88;
        objArr89[2] = Lit110;
        new SyntaxRule(syntaxPattern68, "\u0001\u0003", "\u0011\u0018\u0004)\u0011\u0018\f\b\u0003\b\u0011\u0018\u0014\b\r\u000b", objArr89, 1);
        syntaxRuleArr60[0] = syntaxRule45;
        new SyntaxRules(objArr85, syntaxRuleArr59, 2);
        Lit20 = syntaxRules18;
        new SimpleSymbol("unless");
        Lit19 = (SimpleSymbol) simpleSymbol65.readResolve();
        SyntaxRules syntaxRules19 = syntaxRules8;
        Object[] objArr90 = new Object[1];
        Object[] objArr91 = objArr90;
        objArr90[0] = Lit17;
        SyntaxRule[] syntaxRuleArr61 = new SyntaxRule[1];
        SyntaxRule[] syntaxRuleArr62 = syntaxRuleArr61;
        SyntaxRule[] syntaxRuleArr63 = syntaxRuleArr61;
        SyntaxRule syntaxRule46 = syntaxRule21;
        SyntaxPattern syntaxPattern69 = syntaxPattern35;
        new SyntaxPattern("\f\u0018\f\u0007\r\u000f\b\b\b", new Object[0], 2);
        Object[] objArr92 = new Object[2];
        objArr92[0] = Lit121;
        Object[] objArr93 = objArr92;
        objArr93[1] = Lit110;
        new SyntaxRule(syntaxPattern69, "\u0001\u0003", "\u0011\u0018\u0004\t\u0003\b\u0011\u0018\f\b\r\u000b", objArr93, 1);
        syntaxRuleArr63[0] = syntaxRule46;
        new SyntaxRules(objArr91, syntaxRuleArr62, 2);
        Lit18 = syntaxRules19;
        SyntaxRules syntaxRules20 = syntaxRules9;
        Object[] objArr94 = new Object[1];
        Object[] objArr95 = objArr94;
        Object[] objArr96 = objArr94;
        new SimpleSymbol("define-syntax-case");
        SimpleSymbol simpleSymbol92 = (SimpleSymbol) simpleSymbol66.readResolve();
        Lit15 = simpleSymbol92;
        objArr96[0] = simpleSymbol92;
        SyntaxRule[] syntaxRuleArr64 = new SyntaxRule[1];
        SyntaxRule[] syntaxRuleArr65 = syntaxRuleArr64;
        SyntaxRule[] syntaxRuleArr66 = syntaxRuleArr64;
        SyntaxRule syntaxRule47 = syntaxRule22;
        SyntaxPattern syntaxPattern70 = syntaxPattern36;
        new SyntaxPattern("\f\u0018\f\u0007\f\u000f\u0013", new Object[0], 3);
        Object[] objArr97 = new Object[5];
        new SimpleSymbol("define-syntax");
        objArr97[0] = (SimpleSymbol) simpleSymbol67.readResolve();
        Object[] objArr98 = objArr97;
        objArr98[1] = Lit109;
        Object[] objArr99 = objArr98;
        objArr99[2] = PairWithPosition.make(Lit122, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm", 90129);
        Object[] objArr100 = objArr99;
        new SimpleSymbol("syntax-case");
        objArr100[3] = (SimpleSymbol) simpleSymbol68.readResolve();
        Object[] objArr101 = objArr100;
        objArr101[4] = Lit122;
        new SyntaxRule(syntaxPattern70, "\u0001\u0001\u0000", "\u0011\u0018\u0004\t\u0003\b\u0011\u0018\f\u0011\u0018\u0014\b\u0011\u0018\u001c\u0011\u0018$\t\u000b\u0012", objArr101, 0);
        syntaxRuleArr66[0] = syntaxRule47;
        new SyntaxRules(objArr95, syntaxRuleArr65, 3);
        Lit16 = syntaxRules20;
        SyntaxRules syntaxRules21 = syntaxRules10;
        Object[] objArr102 = new Object[1];
        Object[] objArr103 = objArr102;
        Object[] objArr104 = objArr102;
        new SimpleSymbol("define-macro");
        SimpleSymbol simpleSymbol93 = (SimpleSymbol) simpleSymbol69.readResolve();
        Lit13 = simpleSymbol93;
        objArr104[0] = simpleSymbol93;
        SyntaxRule[] syntaxRuleArr67 = new SyntaxRule[2];
        SyntaxRule[] syntaxRuleArr68 = syntaxRuleArr67;
        SyntaxRule[] syntaxRuleArr69 = syntaxRuleArr67;
        SyntaxRule syntaxRule48 = syntaxRule23;
        SyntaxPattern syntaxPattern71 = syntaxPattern37;
        new SyntaxPattern("\f\u0018\u001c\f\u0007\u000b\u0013", new Object[0], 3);
        Object[] objArr105 = new Object[2];
        objArr105[0] = Lit123;
        Object[] objArr106 = objArr105;
        objArr106[1] = Lit109;
        new SyntaxRule(syntaxPattern71, "\u0001\u0000\u0000", "\u0011\u0018\u0004\t\u0003\b\u0011\u0018\f\t\n\u0012", objArr106, 0);
        syntaxRuleArr69[0] = syntaxRule48;
        SyntaxRule[] syntaxRuleArr70 = syntaxRuleArr68;
        SyntaxRule[] syntaxRuleArr71 = syntaxRuleArr70;
        SyntaxRule[] syntaxRuleArr72 = syntaxRuleArr70;
        SyntaxRule syntaxRule49 = syntaxRule24;
        new SyntaxPattern("\f\u0018\f\u0007\f\u000f\b", new Object[0], 2);
        new SyntaxRule(syntaxPattern38, "\u0001\u0001", "\u0011\u0018\u0004\t\u0003\b\u000b", new Object[]{Lit123}, 0);
        syntaxRuleArr72[1] = syntaxRule49;
        new SyntaxRules(objArr103, syntaxRuleArr71, 3);
        Lit14 = syntaxRules21;
        SyntaxRules syntaxRules22 = syntaxRules11;
        Object[] objArr107 = new Object[1];
        Object[] objArr108 = objArr107;
        Object[] objArr109 = objArr107;
        new SimpleSymbol("defmacro");
        SimpleSymbol simpleSymbol94 = (SimpleSymbol) simpleSymbol70.readResolve();
        Lit11 = simpleSymbol94;
        objArr109[0] = simpleSymbol94;
        SyntaxRule[] syntaxRuleArr73 = new SyntaxRule[1];
        SyntaxRule[] syntaxRuleArr74 = syntaxRuleArr73;
        SyntaxRule[] syntaxRuleArr75 = syntaxRuleArr73;
        SyntaxRule syntaxRule50 = syntaxRule25;
        SyntaxPattern syntaxPattern72 = syntaxPattern39;
        new SyntaxPattern("\f\u0018\f\u0007\f\u000f\u0013", new Object[0], 3);
        Object[] objArr110 = new Object[2];
        objArr110[0] = Lit123;
        Object[] objArr111 = objArr110;
        objArr111[1] = Lit109;
        new SyntaxRule(syntaxPattern72, "\u0001\u0001\u0000", "\u0011\u0018\u0004\t\u0003\b\u0011\u0018\f\t\u000b\u0012", objArr111, 0);
        syntaxRuleArr75[0] = syntaxRule50;
        new SyntaxRules(objArr108, syntaxRuleArr74, 3);
        Lit12 = syntaxRules22;
        new SyntaxPattern("\u0003", new Object[0], 1);
        Lit10 = syntaxPattern40;
        new SyntaxPattern("\b", new Object[0], 0);
        Lit9 = syntaxPattern41;
        new SyntaxTemplate("\u0001\u0001\u0000", "\u0012", new Object[0], 0);
        Lit8 = syntaxTemplate44;
        new SyntaxTemplate("\u0001\u0001\u0000", "\u000b", new Object[0], 0);
        Lit7 = syntaxTemplate45;
        new SyntaxTemplate("\u0001\u0001\u0000", "\u0003", new Object[0], 0);
        Lit6 = syntaxTemplate46;
        new SyntaxPattern(",\f\u0007\f\u000f\b\u0013", new Object[0], 3);
        Lit5 = syntaxPattern42;
        new SyntaxPattern("\u0003", new Object[0], 1);
        Lit4 = syntaxPattern43;
        new SyntaxPattern("\b", new Object[0], 0);
        Lit3 = syntaxPattern44;
        new SyntaxTemplate("\u0001\u0000", "\n", new Object[0], 0);
        Lit2 = syntaxTemplate47;
        new SyntaxTemplate("\u0001\u0000", "\u0003", new Object[0], 0);
        Lit1 = syntaxTemplate48;
        new SyntaxPattern("\f\u0007\u000b", new Object[0], 2);
        Lit0 = syntaxPattern45;
        new syntax();
        $instance = syntax;
        syntax syntax2 = $instance;
        new ModuleMethod(syntax2, 2, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        try$Mnfinally = Macro.make(Lit21, procedure, $instance);
        new ModuleMethod(syntax2, 3, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        f612synchronized = Macro.make(Lit25, procedure2, $instance);
        new ModuleMethod(syntax2, 4, Lit29, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        identifier$Mnlist$Qu = moduleMethod;
        new ModuleMethod(syntax2, 5, Lit30, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        identifier$Mnpair$Mnlist$Qu = moduleMethod2;
        new ModuleMethod(syntax2, 6, Lit31, 8194);
        import$Mnhandle$Mnonly = moduleMethod3;
        new ModuleMethod(syntax2, 7, Lit32, 8194);
        import$Mnhandle$Mnexcept = moduleMethod4;
        new ModuleMethod(syntax2, 8, Lit33, 8194);
        import$Mnhandle$Mnprefix = moduleMethod5;
        new ModuleMethod(syntax2, 9, Lit34, 8194);
        import$Mnhandle$Mnrename = moduleMethod6;
        new ModuleMethod(syntax2, 10, Lit35, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        import$Mnmapper = moduleMethod7;
        new ModuleMethod(syntax2, 11, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        $Pcimport = Macro.make(Lit38, procedure3, $instance);
        new ModuleMethod(syntax2, 12, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        case$Mnlambda = Macro.make(Lit81, procedure4, $instance);
        SimpleSymbol simpleSymbol95 = Lit93;
        new ModuleMethod(syntax2, 13, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        Procedure procedure6 = procedure5;
        procedure6.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm:227");
        $Pccond$Mnexpand = Macro.make(simpleSymbol95, procedure6, $instance);
        $instance.run();
    }

    public syntax() {
        ModuleInfo.register(this);
    }

    public final void run(CallContext $ctx) {
        Consumer consumer = $ctx.consumer;
    }

    static Object lambda2(Object obj) {
        Object x;
        Object obj2;
        Object x2 = obj;
        Object[] allocVars = SyntaxPattern.allocVars(3, (Object[]) null);
        Object obj3 = x2;
        if (Lit22.match(x2, allocVars, 0)) {
            x = obj2;
            new TryExp(SyntaxForms.rewrite(Lit23.execute(allocVars, TemplateScope.make())), SyntaxForms.rewrite(Lit24.execute(allocVars, TemplateScope.make())));
        } else {
            x = syntax_case.error("syntax-case", x2);
        }
        return x;
    }

    static Object lambda3(Object obj) {
        Object x;
        Object obj2;
        Object x2 = obj;
        Object[] allocVars = SyntaxPattern.allocVars(3, (Object[]) null);
        Object obj3 = x2;
        if (Lit26.match(x2, allocVars, 0)) {
            x = obj2;
            new SynchronizedExp(SyntaxForms.rewrite(Lit27.execute(allocVars, TemplateScope.make())), SyntaxForms.rewriteBody(Lit28.execute(allocVars, TemplateScope.make())));
        } else {
            x = syntax_case.error("syntax-case", x2);
        }
        return x;
    }

    public static boolean isIdentifierList(Object obj) {
        boolean z;
        Object obj2 = obj;
        boolean x = Translator.listLength(obj2) >= 0;
        if (x) {
            Object obj3 = obj2;
            while (true) {
                Object obj4 = obj3;
                Object[] allocVars = SyntaxPattern.allocVars(2, (Object[]) null);
                Object obj5 = obj4;
                if (Lit0.match(obj4, allocVars, 0)) {
                    boolean x2 = std_syntax.isIdentifier(Lit1.execute(allocVars, TemplateScope.make()));
                    if (!x2) {
                        z = x2;
                        break;
                    }
                    obj3 = Lit2.execute(allocVars, TemplateScope.make());
                } else {
                    z = Lit3.match(obj4, allocVars, 0) ? true : Lit4.match(obj4, allocVars, 0) ? false : syntax_case.error("syntax-case", obj4) != Boolean.FALSE;
                }
            }
        } else {
            z = x;
        }
        return z;
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
            case 3:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 4:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 5:
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
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 12:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 13:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            default:
                return super.match1(moduleMethod2, obj2, callContext2);
        }
    }

    public static boolean isIdentifierPairList(Object obj) {
        boolean z;
        Object obj2 = obj;
        boolean x = Translator.listLength(obj2) >= 0;
        if (x) {
            Object obj3 = obj2;
            while (true) {
                Object obj4 = obj3;
                Object[] allocVars = SyntaxPattern.allocVars(3, (Object[]) null);
                Object obj5 = obj4;
                if (Lit5.match(obj4, allocVars, 0)) {
                    boolean x2 = std_syntax.isIdentifier(Lit6.execute(allocVars, TemplateScope.make()));
                    if (!x2) {
                        z = x2;
                        break;
                    }
                    boolean x3 = std_syntax.isIdentifier(Lit7.execute(allocVars, TemplateScope.make()));
                    if (!x3) {
                        z = x3;
                        break;
                    }
                    obj3 = Lit8.execute(allocVars, TemplateScope.make());
                } else {
                    z = Lit9.match(obj4, allocVars, 0) ? true : Lit10.match(obj4, allocVars, 0) ? false : syntax_case.error("syntax-case", obj4) != Boolean.FALSE;
                }
            }
        } else {
            z = x;
        }
        return z;
    }

    public static Object importHandleOnly(Object obj, Object list) {
        Object name = obj;
        return C1245lists.memq(name, list) != Boolean.FALSE ? name : null;
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 6:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 7:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 8:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 9:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            default:
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
        }
    }

    public static Object importHandleExcept(Object obj, Object list) {
        Object name = obj;
        return C1245lists.memq(name, list) != Boolean.FALSE ? null : name;
    }

    public static Object importHandlePrefix(Object name, Object obj) {
        Object obj2 = obj;
        return name == null ? null : null;
    }

    public static Object importHandleRename(Object obj, Object obj2) {
        Object name;
        Object name2 = obj;
        Object rename$Mnpairs = obj2;
        if (!C1245lists.isPair(rename$Mnpairs)) {
            name = name2;
        } else if (name2 == C1245lists.caar.apply1(rename$Mnpairs)) {
            name = C1245lists.cadar.apply1(rename$Mnpairs);
        } else {
            name = importHandleRename(name2, C1245lists.cdr.apply1(rename$Mnpairs));
        }
        return name;
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        switch (moduleMethod2.selector) {
            case 6:
                return importHandleOnly(obj3, obj4);
            case 7:
                return importHandleExcept(obj3, obj4);
            case 8:
                return importHandlePrefix(obj3, obj4);
            case 9:
                return importHandleRename(obj3, obj4);
            default:
                return super.apply2(moduleMethod2, obj3, obj4);
        }
    }

    /* compiled from: syntax.scm */
    public class frame extends ModuleBody {
        final ModuleMethod lambda$Fn1;
        Object list;

        public frame() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 1, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/syntax.scm:83");
            this.lambda$Fn1 = moduleMethod2;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector == 1) {
                return lambda1(obj2);
            }
            return super.apply1(moduleMethod2, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda1(Object name) {
            Object obj;
            Object obj2 = this.list;
            Object obj3 = name;
            while (true) {
                obj = obj3;
                Object l = obj2;
                boolean x = obj == null;
                if (x) {
                    if (x) {
                        break;
                    }
                    obj2 = C1245lists.cdr.apply1(l);
                    obj3 = Scheme.applyToArgs.apply3(C1245lists.caar.apply1(l), obj, C1245lists.cdar.apply1(l));
                } else if (C1245lists.isNull(l)) {
                    break;
                } else {
                    obj2 = C1245lists.cdr.apply1(l);
                    obj3 = Scheme.applyToArgs.apply3(C1245lists.caar.apply1(l), obj, C1245lists.cdar.apply1(l));
                }
            }
            return obj;
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

    public static Procedure importMapper(Object list) {
        frame frame2;
        new frame();
        frame frame3 = frame2;
        frame3.list = list;
        return frame3.lambda$Fn1;
    }

    static Object lambda4(Object obj) {
        Object form;
        Object[] objArr;
        Object[] objArr2;
        Object[] objArr3;
        Object[] objArr4;
        Object form2 = obj;
        Object[] allocVars = SyntaxPattern.allocVars(4, (Object[]) null);
        Object obj2 = form2;
        if (Lit39.match(form2, allocVars, 0)) {
            if (isIdentifierPairList(Lit40.execute(allocVars, TemplateScope.make()))) {
                TemplateScope make = TemplateScope.make();
                Object[] objArr5 = new Object[2];
                objArr5[0] = Lit41.execute(allocVars, make);
                Object[] objArr6 = objArr5;
                Object[] objArr7 = objArr6;
                Object[] objArr8 = objArr6;
                Object[] objArr9 = new Object[2];
                objArr9[0] = C1245lists.cons(C1245lists.cons(import$Mnhandle$Mnrename, Lit42.execute(allocVars, make)), Lit43.execute(allocVars, make));
                Object[] objArr10 = objArr9;
                objArr10[1] = Lit44.execute(allocVars, make);
                objArr8[1] = Quote.consX$V(objArr10);
                form = Quote.append$V(objArr7);
            } else {
                Object execute = Lit45.execute(allocVars, TemplateScope.make());
                Object obj3 = "invalid 'rename' clause in import";
                Object obj4 = obj3;
                if (obj3 instanceof Object[]) {
                    objArr4 = (Object[]) obj4;
                } else {
                    Object obj5 = obj4;
                    Object[] objArr11 = new Object[1];
                    objArr4 = objArr11;
                    objArr11[0] = obj5;
                }
                form = prim_syntax.syntaxError(execute, objArr4);
            }
        } else if (Lit46.match(form2, allocVars, 0)) {
            if (isIdentifierList(Lit47.execute(allocVars, TemplateScope.make()))) {
                TemplateScope make2 = TemplateScope.make();
                Object[] objArr12 = new Object[2];
                objArr12[0] = Lit48.execute(allocVars, make2);
                Object[] objArr13 = objArr12;
                Object[] objArr14 = objArr13;
                Object[] objArr15 = objArr13;
                Object[] objArr16 = new Object[2];
                objArr16[0] = C1245lists.cons(C1245lists.cons(import$Mnhandle$Mnonly, Lit49.execute(allocVars, make2)), Lit50.execute(allocVars, make2));
                Object[] objArr17 = objArr16;
                objArr17[1] = Lit51.execute(allocVars, make2);
                objArr15[1] = Quote.consX$V(objArr17);
                form = Quote.append$V(objArr14);
            } else {
                Object execute2 = Lit52.execute(allocVars, TemplateScope.make());
                Object obj6 = "invalid 'only' identifier list";
                Object obj7 = obj6;
                if (obj6 instanceof Object[]) {
                    objArr3 = (Object[]) obj7;
                } else {
                    Object obj8 = obj7;
                    Object[] objArr18 = new Object[1];
                    objArr3 = objArr18;
                    objArr18[0] = obj8;
                }
                form = prim_syntax.syntaxError(execute2, objArr3);
            }
        } else if (Lit53.match(form2, allocVars, 0)) {
            if (isIdentifierList(Lit54.execute(allocVars, TemplateScope.make()))) {
                TemplateScope make3 = TemplateScope.make();
                Object[] objArr19 = new Object[2];
                objArr19[0] = Lit55.execute(allocVars, make3);
                Object[] objArr20 = objArr19;
                Object[] objArr21 = objArr20;
                Object[] objArr22 = objArr20;
                Object[] objArr23 = new Object[2];
                objArr23[0] = C1245lists.cons(C1245lists.cons(import$Mnhandle$Mnexcept, Lit56.execute(allocVars, make3)), Lit57.execute(allocVars, make3));
                Object[] objArr24 = objArr23;
                objArr24[1] = Lit58.execute(allocVars, make3);
                objArr22[1] = Quote.consX$V(objArr24);
                form = Quote.append$V(objArr21);
            } else {
                Object execute3 = Lit59.execute(allocVars, TemplateScope.make());
                Object obj9 = "invalid 'except' identifier list";
                Object obj10 = obj9;
                if (obj9 instanceof Object[]) {
                    objArr2 = (Object[]) obj10;
                } else {
                    Object obj11 = obj10;
                    Object[] objArr25 = new Object[1];
                    objArr2 = objArr25;
                    objArr25[0] = obj11;
                }
                form = prim_syntax.syntaxError(execute3, objArr2);
            }
        } else if (Lit60.match(form2, allocVars, 0)) {
            TemplateScope make4 = TemplateScope.make();
            Object[] objArr26 = new Object[2];
            objArr26[0] = Lit61.execute(allocVars, make4);
            Object[] objArr27 = objArr26;
            Object[] objArr28 = objArr27;
            Object[] objArr29 = objArr27;
            Object[] objArr30 = new Object[2];
            objArr30[0] = C1245lists.cons(C1245lists.cons(import$Mnhandle$Mnprefix, Lit62.execute(allocVars, make4)), Lit63.execute(allocVars, make4));
            Object[] objArr31 = objArr30;
            objArr31[1] = Lit64.execute(allocVars, make4);
            objArr29[1] = Quote.consX$V(objArr31);
            form = Quote.append$V(objArr28);
        } else if (Lit65.match(form2, allocVars, 0)) {
            Object execute4 = Lit66.execute(allocVars, TemplateScope.make());
            Object obj12 = "invalid prefix clause in import";
            Object obj13 = obj12;
            if (obj12 instanceof Object[]) {
                objArr = (Object[]) obj13;
            } else {
                Object obj14 = obj13;
                Object[] objArr32 = new Object[1];
                objArr = objArr32;
                objArr32[0] = obj14;
            }
            form = prim_syntax.syntaxError(execute4, objArr);
        } else if (Lit67.match(form2, allocVars, 0)) {
            TemplateScope make5 = TemplateScope.make();
            Object execute5 = Lit68.execute(allocVars, make5);
            Object[] objArr33 = new Object[2];
            objArr33[0] = Lit69.execute(allocVars, make5);
            Object[] objArr34 = objArr33;
            Object[] objArr35 = objArr34;
            Object[] objArr36 = objArr34;
            Object[] objArr37 = new Object[2];
            objArr37[0] = importMapper(std_syntax.syntaxObject$To$Datum(Lit70.execute(allocVars, make5)));
            Object[] objArr38 = objArr37;
            objArr38[1] = Lit71.execute(allocVars, make5);
            objArr36[1] = Quote.consX$V(objArr38);
            form = Pair.make(execute5, Quote.append$V(objArr35));
        } else if (Lit72.match(form2, allocVars, 0)) {
            TemplateScope make6 = TemplateScope.make();
            Object execute6 = Lit73.execute(allocVars, make6);
            Object[] objArr39 = new Object[2];
            objArr39[0] = Lit74.execute(allocVars, make6);
            Object[] objArr40 = objArr39;
            Object[] objArr41 = objArr40;
            Object[] objArr42 = objArr40;
            Object[] objArr43 = new Object[2];
            objArr43[0] = importMapper(std_syntax.syntaxObject$To$Datum(Lit75.execute(allocVars, make6)));
            Object[] objArr44 = objArr43;
            objArr44[1] = Lit76.execute(allocVars, make6);
            objArr42[1] = Quote.consX$V(objArr44);
            form = Pair.make(execute6, Quote.append$V(objArr41));
        } else {
            form = syntax_case.error("syntax-case", form2);
        }
        return form;
    }

    static Object lambda5(Object obj) {
        frame0 frame02;
        Object form;
        Object form2 = obj;
        new frame0();
        frame0 frame03 = frame02;
        frame03.$unnamed$1 = SyntaxPattern.allocVars(2, (Object[]) null);
        Object obj2 = form2;
        if (Lit82.match(form2, frame03.$unnamed$1, 0)) {
            frame03.$unnamed$0 = TemplateScope.make();
            form = Pair.make(Lit83.execute(frame03.$unnamed$1, frame03.$unnamed$0), frame03.lambda6loop(Lit84.execute(frame03.$unnamed$1, frame03.$unnamed$0)));
        } else {
            form = syntax_case.error("syntax-case", form2);
        }
        return form;
    }

    /* compiled from: syntax.scm */
    public class frame0 extends ModuleBody {
        TemplateScope $unnamed$0;
        Object[] $unnamed$1;

        public frame0() {
        }

        public Object lambda6loop(Object obj) {
            Object error;
            Object[] objArr;
            Object clauses = obj;
            Object[] allocVars = SyntaxPattern.allocVars(5, this.$unnamed$1);
            Object obj2 = clauses;
            if (syntax.Lit85.match(clauses, allocVars, 0)) {
                error = Pair.make(syntax.Lit86.execute(allocVars, this.$unnamed$0), lambda6loop(syntax.Lit87.execute(allocVars, this.$unnamed$0)));
            } else if (syntax.Lit88.match(clauses, allocVars, 0)) {
                error = LList.Empty;
            } else if (syntax.Lit89.match(clauses, allocVars, 0)) {
                Object execute = syntax.Lit90.execute(allocVars, this.$unnamed$0);
                Object obj3 = "invalid case-lambda clause";
                Object obj4 = obj3;
                if (obj3 instanceof Object[]) {
                    objArr = (Object[]) obj4;
                } else {
                    Object obj5 = obj4;
                    Object[] objArr2 = new Object[1];
                    objArr = objArr2;
                    objArr2[0] = obj5;
                }
                error = LList.list1(prim_syntax.syntaxError(execute, objArr));
            } else {
                error = syntax_case.error("syntax-case", clauses);
            }
            return error;
        }
    }

    static Object lambda7(Object obj) {
        Object x;
        Object x2 = obj;
        Object[] allocVars = SyntaxPattern.allocVars(4, (Object[]) null);
        Object obj2 = x2;
        if (Lit94.match(x2, allocVars, 0)) {
            if (IfFeature.testFeature(Lit95.execute(allocVars, TemplateScope.make()))) {
                x = Lit96.execute(allocVars, TemplateScope.make());
            } else {
                x = Lit97.execute(allocVars, TemplateScope.make());
            }
        } else {
            x = syntax_case.error("syntax-case", x2);
        }
        return x;
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        switch (moduleMethod2.selector) {
            case 2:
                return lambda2(obj2);
            case 3:
                return lambda3(obj2);
            case 4:
                return isIdentifierList(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 5:
                return isIdentifierPairList(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 10:
                return importMapper(obj2);
            case 11:
                return lambda4(obj2);
            case 12:
                return lambda5(obj2);
            case 13:
                return lambda7(obj2);
            default:
                return super.apply1(moduleMethod2, obj2);
        }
    }
}

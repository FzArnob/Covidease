package gnu.kawa.slib;

import android.support.p000v4.app.FragmentTransaction;
import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.ApplyToArgs;
import gnu.kawa.functions.GetNamedPart;
import gnu.kawa.functions.IsEqual;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.InPort;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.text.Path;
import kawa.lang.Eval;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lang.SyntaxTemplate;
import kawa.lang.TemplateScope;
import kawa.lib.C1245lists;
import kawa.lib.misc;
import kawa.lib.numbers;
import kawa.lib.parameters;
import kawa.lib.ports;
import kawa.lib.std_syntax;
import kawa.lib.strings;
import kawa.standard.Scheme;
import kawa.standard.readchar;
import kawa.standard.syntax_case;

/* compiled from: testing.scm */
public class testing extends ModuleBody {
    public static final ModuleMethod $Pctest$Mnbegin;
    static final ModuleMethod $Pctest$Mnnull$Mncallback;
    public static final ModuleMethod $Prvt$$Pctest$Mnapproximimate$Eq;
    public static final ModuleMethod $Prvt$$Pctest$Mnas$Mnspecifier;
    public static final Macro $Prvt$$Pctest$Mncomp1body = Macro.make(Lit92, Lit93, $instance);
    public static final Macro $Prvt$$Pctest$Mncomp2body = Macro.make(Lit89, Lit90, $instance);
    public static final ModuleMethod $Prvt$$Pctest$Mnend;
    public static final Macro $Prvt$$Pctest$Mnerror = Macro.make(Lit115, Lit116, $instance);
    public static final Macro $Prvt$$Pctest$Mnevaluate$Mnwith$Mncatch = Macro.make(Lit84, Lit85, $instance);
    public static final ModuleMethod $Prvt$$Pctest$Mnmatch$Mnall;
    public static final ModuleMethod $Prvt$$Pctest$Mnmatch$Mnany;
    public static final ModuleMethod $Prvt$$Pctest$Mnmatch$Mnnth;
    public static final ModuleMethod $Prvt$$Pctest$Mnon$Mntest$Mnbegin;
    public static final ModuleMethod $Prvt$$Pctest$Mnon$Mntest$Mnend;
    public static final ModuleMethod $Prvt$$Pctest$Mnreport$Mnresult;
    public static final ModuleMethod $Prvt$$Pctest$Mnrunner$Mnfail$Mnlist;
    public static final ModuleMethod $Prvt$$Pctest$Mnrunner$Mnfail$Mnlist$Ex;
    public static final ModuleMethod $Prvt$$Pctest$Mnrunner$Mnskip$Mnlist;
    public static final ModuleMethod $Prvt$$Pctest$Mnrunner$Mnskip$Mnlist$Ex;
    public static final ModuleMethod $Prvt$$Pctest$Mnshould$Mnexecute;
    public static final Macro $Prvt$test$Mngroup = Macro.make(Lit70, Lit71, $instance);
    public static final testing $instance;
    static final IntNum Lit0 = IntNum.make(0);
    static final SimpleSymbol Lit1;
    static final PairWithPosition Lit10;
    static final SyntaxPattern Lit100;
    static final SyntaxTemplate Lit101;
    static final SyntaxPattern Lit102;
    static final SyntaxTemplate Lit103;
    static final SimpleSymbol Lit104;
    static final SyntaxTemplate Lit105;
    static final SimpleSymbol Lit106;
    static final SyntaxTemplate Lit107;
    static final SimpleSymbol Lit108;
    static final SyntaxTemplate Lit109;
    static final PairWithPosition Lit11;
    static final SimpleSymbol Lit110;
    static final SyntaxPattern Lit111;
    static final SyntaxTemplate Lit112;
    static final SyntaxPattern Lit113;
    static final SyntaxTemplate Lit114;
    static final SimpleSymbol Lit115;
    static final SyntaxRules Lit116;
    static final SimpleSymbol Lit117;
    static final SyntaxPattern Lit118;
    static final SyntaxTemplate Lit119;
    static final SimpleSymbol Lit12;
    static final SyntaxPattern Lit120;
    static final SyntaxTemplate Lit121;
    static final SyntaxPattern Lit122;
    static final SyntaxTemplate Lit123;
    static final SimpleSymbol Lit124;
    static final SimpleSymbol Lit125;
    static final SyntaxRules Lit126;
    static final SimpleSymbol Lit127;
    static final SimpleSymbol Lit128;
    static final SyntaxRules Lit129;
    static final IntNum Lit13;
    static final SimpleSymbol Lit130;
    static final SimpleSymbol Lit131;
    static final SyntaxRules Lit132;
    static final SimpleSymbol Lit133;
    static final SimpleSymbol Lit134;
    static final SyntaxRules Lit135;
    static final SimpleSymbol Lit136;
    static final SimpleSymbol Lit137;
    static final SyntaxRules Lit138;
    static final SimpleSymbol Lit139;
    static final SimpleSymbol Lit14;
    static final SyntaxRules Lit140;
    static final SimpleSymbol Lit141;
    static final SimpleSymbol Lit142;
    static final SimpleSymbol Lit143;
    static final SimpleSymbol Lit144;
    static final SimpleSymbol Lit145;
    static final SimpleSymbol Lit146;
    static final SimpleSymbol Lit147;
    static final SimpleSymbol Lit148;
    static final SimpleSymbol Lit149;
    static final SimpleSymbol Lit15;
    static final SimpleSymbol Lit150;
    static final SimpleSymbol Lit151;
    static final SimpleSymbol Lit152;
    static final SimpleSymbol Lit153;
    static final SimpleSymbol Lit154;
    static final SimpleSymbol Lit155;
    static final SimpleSymbol Lit156;
    static final SimpleSymbol Lit157;
    static final SimpleSymbol Lit158;
    static final SimpleSymbol Lit159;
    static final SyntaxPattern Lit16;
    static final SimpleSymbol Lit160;
    static final SimpleSymbol Lit161;
    static final SimpleSymbol Lit162;
    static final SimpleSymbol Lit163;
    static final SimpleSymbol Lit164;
    static final SimpleSymbol Lit165;
    static final SyntaxTemplate Lit17;
    static final SyntaxPattern Lit18;
    static final SyntaxTemplate Lit19;
    static final SimpleSymbol Lit2;
    static final SimpleSymbol Lit20;
    static final SimpleSymbol Lit21;
    static final SimpleSymbol Lit22;
    static final SimpleSymbol Lit23;
    static final SimpleSymbol Lit24;
    static final SimpleSymbol Lit25;
    static final SimpleSymbol Lit26;
    static final SimpleSymbol Lit27;
    static final SimpleSymbol Lit28;
    static final SimpleSymbol Lit29;
    static final SimpleSymbol Lit3;
    static final SimpleSymbol Lit30;
    static final SimpleSymbol Lit31;
    static final SimpleSymbol Lit32;
    static final SimpleSymbol Lit33;
    static final SimpleSymbol Lit34;
    static final SimpleSymbol Lit35;
    static final SimpleSymbol Lit36;
    static final SimpleSymbol Lit37;
    static final SimpleSymbol Lit38;
    static final SimpleSymbol Lit39;
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit40;
    static final SimpleSymbol Lit41;
    static final SimpleSymbol Lit42;
    static final SimpleSymbol Lit43;
    static final SimpleSymbol Lit44;
    static final SimpleSymbol Lit45;
    static final SimpleSymbol Lit46;
    static final SimpleSymbol Lit47;
    static final SimpleSymbol Lit48;
    static final SimpleSymbol Lit49;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit50;
    static final SimpleSymbol Lit51;
    static final SimpleSymbol Lit52;
    static final SimpleSymbol Lit53;
    static final SimpleSymbol Lit54;
    static final SimpleSymbol Lit55;
    static final SimpleSymbol Lit56;
    static final SimpleSymbol Lit57;
    static final SimpleSymbol Lit58;
    static final SimpleSymbol Lit59;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit60;
    static final SimpleSymbol Lit61;
    static final SimpleSymbol Lit62;
    static final SimpleSymbol Lit63;
    static final SimpleSymbol Lit64;
    static final SimpleSymbol Lit65;
    static final SimpleSymbol Lit66;
    static final SimpleSymbol Lit67;
    static final SimpleSymbol Lit68;
    static final SimpleSymbol Lit69;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit70;
    static final SyntaxRules Lit71;
    static final SimpleSymbol Lit72;
    static final SyntaxRules Lit73;
    static final SimpleSymbol Lit74;
    static final SimpleSymbol Lit75;
    static final SyntaxRules Lit76;
    static final SimpleSymbol Lit77;
    static final SimpleSymbol Lit78;
    static final SimpleSymbol Lit79;
    static final PairWithPosition Lit8 = PairWithPosition.make(Lit14, PairWithPosition.make(Lit9, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 1966107), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 1966101);
    static final SimpleSymbol Lit80;
    static final SimpleSymbol Lit81;
    static final SimpleSymbol Lit82;
    static final SimpleSymbol Lit83;
    static final SimpleSymbol Lit84;
    static final SyntaxRules Lit85;
    static final SimpleSymbol Lit86;
    static final SimpleSymbol Lit87;
    static final SimpleSymbol Lit88;
    static final SimpleSymbol Lit89;
    static final SimpleSymbol Lit9;
    static final SyntaxRules Lit90;
    static final SimpleSymbol Lit91;
    static final SimpleSymbol Lit92;
    static final SyntaxRules Lit93;
    static final SimpleSymbol Lit94;
    static final SyntaxPattern Lit95;
    static final SyntaxTemplate Lit96;
    static final SyntaxPattern Lit97;
    static final SyntaxTemplate Lit98;
    static final SimpleSymbol Lit99;
    static final ModuleMethod lambda$Fn1;
    static final ModuleMethod lambda$Fn2;
    static final ModuleMethod lambda$Fn3;
    public static final ModuleMethod test$Mnapply;
    public static final Macro test$Mnapproximate;
    public static final Macro test$Mnassert;
    public static final Macro test$Mnend;
    public static final Macro test$Mneq;
    public static final Macro test$Mnequal;
    public static final Macro test$Mneqv;
    public static final Macro test$Mnerror;
    public static final Macro test$Mnexpect$Mnfail = Macro.make(Lit139, Lit140, $instance);
    public static final Macro test$Mngroup$Mnwith$Mncleanup = Macro.make(Lit72, Lit73, $instance);
    public static Boolean test$Mnlog$Mnto$Mnfile;
    public static final Macro test$Mnmatch$Mnall = Macro.make(Lit131, Lit132, $instance);
    public static final Macro test$Mnmatch$Mnany = Macro.make(Lit134, Lit135, $instance);
    public static final ModuleMethod test$Mnmatch$Mnname;
    public static final Macro test$Mnmatch$Mnnth = Macro.make(Lit128, Lit129, $instance);
    public static final ModuleMethod test$Mnon$Mnbad$Mncount$Mnsimple;
    public static final ModuleMethod test$Mnon$Mnbad$Mnend$Mnname$Mnsimple;
    public static final ModuleMethod test$Mnon$Mnfinal$Mnsimple;
    public static final ModuleMethod test$Mnon$Mngroup$Mnbegin$Mnsimple;
    public static final ModuleMethod test$Mnon$Mngroup$Mnend$Mnsimple;
    static final ModuleMethod test$Mnon$Mntest$Mnbegin$Mnsimple;
    public static final ModuleMethod test$Mnon$Mntest$Mnend$Mnsimple;
    public static final ModuleMethod test$Mnpassed$Qu;
    public static final ModuleMethod test$Mnread$Mneval$Mnstring;
    public static final ModuleMethod test$Mnresult$Mnalist;
    public static final ModuleMethod test$Mnresult$Mnalist$Ex;
    public static final ModuleMethod test$Mnresult$Mnclear;
    public static final ModuleMethod test$Mnresult$Mnkind;
    public static final Macro test$Mnresult$Mnref = Macro.make(Lit75, Lit76, $instance);
    public static final ModuleMethod test$Mnresult$Mnremove;
    public static final ModuleMethod test$Mnresult$Mnset$Ex;
    static final Class test$Mnrunner = test$Mnrunner.class;
    public static final ModuleMethod test$Mnrunner$Mnaux$Mnvalue;
    public static final ModuleMethod test$Mnrunner$Mnaux$Mnvalue$Ex;
    public static final ModuleMethod test$Mnrunner$Mncreate;
    public static Object test$Mnrunner$Mncurrent;
    public static Object test$Mnrunner$Mnfactory;
    public static final ModuleMethod test$Mnrunner$Mnfail$Mncount;
    public static final ModuleMethod test$Mnrunner$Mnfail$Mncount$Ex;
    public static final ModuleMethod test$Mnrunner$Mnget;
    public static final ModuleMethod test$Mnrunner$Mngroup$Mnpath;
    public static final ModuleMethod test$Mnrunner$Mngroup$Mnstack;
    public static final ModuleMethod test$Mnrunner$Mngroup$Mnstack$Ex;
    public static final ModuleMethod test$Mnrunner$Mnnull;
    public static final ModuleMethod test$Mnrunner$Mnon$Mnbad$Mncount;
    public static final ModuleMethod test$Mnrunner$Mnon$Mnbad$Mncount$Ex;
    public static final ModuleMethod test$Mnrunner$Mnon$Mnbad$Mnend$Mnname;
    public static final ModuleMethod test$Mnrunner$Mnon$Mnbad$Mnend$Mnname$Ex;
    public static final ModuleMethod test$Mnrunner$Mnon$Mnfinal;
    public static final ModuleMethod test$Mnrunner$Mnon$Mnfinal$Ex;
    public static final ModuleMethod test$Mnrunner$Mnon$Mngroup$Mnbegin;
    public static final ModuleMethod test$Mnrunner$Mnon$Mngroup$Mnbegin$Ex;
    public static final ModuleMethod test$Mnrunner$Mnon$Mngroup$Mnend;
    public static final ModuleMethod test$Mnrunner$Mnon$Mngroup$Mnend$Ex;
    public static final ModuleMethod test$Mnrunner$Mnon$Mntest$Mnbegin;
    public static final ModuleMethod test$Mnrunner$Mnon$Mntest$Mnbegin$Ex;
    public static final ModuleMethod test$Mnrunner$Mnon$Mntest$Mnend;
    public static final ModuleMethod test$Mnrunner$Mnon$Mntest$Mnend$Ex;
    public static final ModuleMethod test$Mnrunner$Mnpass$Mncount;
    public static final ModuleMethod test$Mnrunner$Mnpass$Mncount$Ex;
    public static final ModuleMethod test$Mnrunner$Mnreset;
    public static final ModuleMethod test$Mnrunner$Mnsimple;
    public static final ModuleMethod test$Mnrunner$Mnskip$Mncount;
    public static final ModuleMethod test$Mnrunner$Mnskip$Mncount$Ex;
    public static final ModuleMethod test$Mnrunner$Mntest$Mnname;
    public static final ModuleMethod test$Mnrunner$Mnxfail$Mncount;
    public static final ModuleMethod test$Mnrunner$Mnxfail$Mncount$Ex;
    public static final ModuleMethod test$Mnrunner$Mnxpass$Mncount;
    public static final ModuleMethod test$Mnrunner$Mnxpass$Mncount$Ex;
    public static final ModuleMethod test$Mnrunner$Qu;
    public static final Macro test$Mnskip = Macro.make(Lit137, Lit138, $instance);
    public static final Macro test$Mnwith$Mnrunner = Macro.make(Lit125, Lit126, $instance);

    public testing() {
        ModuleInfo.register(this);
    }

    public final void run(CallContext $ctx) {
        Consumer consumer = $ctx.consumer;
        test$Mnlog$Mnto$Mnfile = Boolean.TRUE;
        test$Mnrunner$Mncurrent = parameters.makeParameter(Boolean.FALSE);
        test$Mnrunner$Mnfactory = parameters.makeParameter(test$Mnrunner$Mnsimple);
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
        SimpleSymbol simpleSymbol23;
        SimpleSymbol simpleSymbol24;
        SimpleSymbol simpleSymbol25;
        SyntaxRules syntaxRules;
        SimpleSymbol simpleSymbol26;
        SyntaxRule syntaxRule;
        SyntaxPattern syntaxPattern;
        SimpleSymbol simpleSymbol27;
        SimpleSymbol simpleSymbol28;
        SimpleSymbol simpleSymbol29;
        SimpleSymbol simpleSymbol30;
        SimpleSymbol simpleSymbol31;
        SyntaxRules syntaxRules2;
        SimpleSymbol simpleSymbol32;
        SyntaxRule syntaxRule2;
        SyntaxPattern syntaxPattern2;
        SimpleSymbol simpleSymbol33;
        SimpleSymbol simpleSymbol34;
        SyntaxRules syntaxRules3;
        SimpleSymbol simpleSymbol35;
        SyntaxRule syntaxRule3;
        SyntaxPattern syntaxPattern3;
        SimpleSymbol simpleSymbol36;
        SyntaxRules syntaxRules4;
        SyntaxRule syntaxRule4;
        SyntaxPattern syntaxPattern4;
        SimpleSymbol simpleSymbol37;
        SyntaxRules syntaxRules5;
        SimpleSymbol simpleSymbol38;
        SyntaxRule syntaxRule5;
        SyntaxPattern syntaxPattern5;
        SyntaxRule syntaxRule6;
        SyntaxPattern syntaxPattern6;
        SimpleSymbol simpleSymbol39;
        SyntaxRules syntaxRules6;
        SimpleSymbol simpleSymbol40;
        SyntaxRule syntaxRule7;
        SyntaxPattern syntaxPattern7;
        SimpleSymbol simpleSymbol41;
        SyntaxTemplate syntaxTemplate;
        SimpleSymbol simpleSymbol42;
        SimpleSymbol simpleSymbol43;
        SyntaxPattern syntaxPattern8;
        SyntaxTemplate syntaxTemplate2;
        SyntaxPattern syntaxPattern9;
        SyntaxTemplate syntaxTemplate3;
        SimpleSymbol simpleSymbol44;
        SimpleSymbol simpleSymbol45;
        SyntaxPattern syntaxPattern10;
        SimpleSymbol simpleSymbol46;
        SyntaxRules syntaxRules7;
        SyntaxRule syntaxRule8;
        SyntaxPattern syntaxPattern11;
        SimpleSymbol simpleSymbol47;
        SimpleSymbol simpleSymbol48;
        SimpleSymbol simpleSymbol49;
        SimpleSymbol simpleSymbol50;
        SyntaxRule syntaxRule9;
        SyntaxPattern syntaxPattern12;
        SimpleSymbol simpleSymbol51;
        SimpleSymbol simpleSymbol52;
        SimpleSymbol simpleSymbol53;
        SimpleSymbol simpleSymbol54;
        SimpleSymbol simpleSymbol55;
        SimpleSymbol simpleSymbol56;
        SimpleSymbol simpleSymbol57;
        SyntaxTemplate syntaxTemplate4;
        SimpleSymbol simpleSymbol58;
        SimpleSymbol simpleSymbol59;
        SyntaxPattern syntaxPattern13;
        SyntaxTemplate syntaxTemplate5;
        SyntaxPattern syntaxPattern14;
        SimpleSymbol simpleSymbol60;
        SyntaxTemplate syntaxTemplate6;
        SimpleSymbol simpleSymbol61;
        SimpleSymbol simpleSymbol62;
        SyntaxTemplate syntaxTemplate7;
        SimpleSymbol simpleSymbol63;
        SimpleSymbol simpleSymbol64;
        SyntaxTemplate syntaxTemplate8;
        SimpleSymbol simpleSymbol65;
        SimpleSymbol simpleSymbol66;
        SyntaxTemplate syntaxTemplate9;
        SimpleSymbol simpleSymbol67;
        SyntaxPattern syntaxPattern15;
        SyntaxTemplate syntaxTemplate10;
        SyntaxPattern syntaxPattern16;
        SimpleSymbol simpleSymbol68;
        SyntaxTemplate syntaxTemplate11;
        SimpleSymbol simpleSymbol69;
        SyntaxPattern syntaxPattern17;
        SyntaxTemplate syntaxTemplate12;
        SyntaxPattern syntaxPattern18;
        SimpleSymbol simpleSymbol70;
        SyntaxRules syntaxRules8;
        SyntaxRule syntaxRule10;
        SyntaxPattern syntaxPattern19;
        SimpleSymbol simpleSymbol71;
        SyntaxRules syntaxRules9;
        SyntaxRule syntaxRule11;
        SyntaxPattern syntaxPattern20;
        SimpleSymbol simpleSymbol72;
        SimpleSymbol simpleSymbol73;
        SyntaxRules syntaxRules10;
        SyntaxRule syntaxRule12;
        SyntaxPattern syntaxPattern21;
        SimpleSymbol simpleSymbol74;
        SimpleSymbol simpleSymbol75;
        SimpleSymbol simpleSymbol76;
        SimpleSymbol simpleSymbol77;
        SimpleSymbol simpleSymbol78;
        SyntaxRules syntaxRules11;
        SimpleSymbol simpleSymbol79;
        SyntaxRule syntaxRule13;
        SyntaxPattern syntaxPattern22;
        SyntaxRule syntaxRule14;
        SyntaxPattern syntaxPattern23;
        SimpleSymbol simpleSymbol80;
        SimpleSymbol simpleSymbol81;
        SimpleSymbol simpleSymbol82;
        SimpleSymbol simpleSymbol83;
        SyntaxRules syntaxRules12;
        SimpleSymbol simpleSymbol84;
        SyntaxRule syntaxRule15;
        SyntaxPattern syntaxPattern24;
        SimpleSymbol simpleSymbol85;
        SyntaxRule syntaxRule16;
        SyntaxPattern syntaxPattern25;
        SyntaxRule syntaxRule17;
        SyntaxPattern syntaxPattern26;
        SimpleSymbol simpleSymbol86;
        SyntaxRules syntaxRules13;
        SyntaxRule syntaxRule18;
        SyntaxPattern syntaxPattern27;
        SimpleSymbol simpleSymbol87;
        SimpleSymbol simpleSymbol88;
        SimpleSymbol simpleSymbol89;
        SimpleSymbol simpleSymbol90;
        SimpleSymbol simpleSymbol91;
        SimpleSymbol simpleSymbol92;
        SimpleSymbol simpleSymbol93;
        SimpleSymbol simpleSymbol94;
        SimpleSymbol simpleSymbol95;
        SimpleSymbol simpleSymbol96;
        SimpleSymbol simpleSymbol97;
        SimpleSymbol simpleSymbol98;
        SimpleSymbol simpleSymbol99;
        SimpleSymbol simpleSymbol100;
        SimpleSymbol simpleSymbol101;
        SimpleSymbol simpleSymbol102;
        SimpleSymbol simpleSymbol103;
        SimpleSymbol simpleSymbol104;
        SimpleSymbol simpleSymbol105;
        SimpleSymbol simpleSymbol106;
        SimpleSymbol simpleSymbol107;
        SimpleSymbol simpleSymbol108;
        SimpleSymbol simpleSymbol109;
        SimpleSymbol simpleSymbol110;
        SimpleSymbol simpleSymbol111;
        SimpleSymbol simpleSymbol112;
        SimpleSymbol simpleSymbol113;
        SimpleSymbol simpleSymbol114;
        SimpleSymbol simpleSymbol115;
        SimpleSymbol simpleSymbol116;
        SimpleSymbol simpleSymbol117;
        SimpleSymbol simpleSymbol118;
        SimpleSymbol simpleSymbol119;
        SimpleSymbol simpleSymbol120;
        SimpleSymbol simpleSymbol121;
        SimpleSymbol simpleSymbol122;
        SimpleSymbol simpleSymbol123;
        SimpleSymbol simpleSymbol124;
        SimpleSymbol simpleSymbol125;
        SimpleSymbol simpleSymbol126;
        SimpleSymbol simpleSymbol127;
        SimpleSymbol simpleSymbol128;
        SimpleSymbol simpleSymbol129;
        SimpleSymbol simpleSymbol130;
        SyntaxTemplate syntaxTemplate13;
        SyntaxPattern syntaxPattern28;
        SyntaxTemplate syntaxTemplate14;
        SyntaxPattern syntaxPattern29;
        SimpleSymbol simpleSymbol131;
        SimpleSymbol simpleSymbol132;
        SimpleSymbol simpleSymbol133;
        SimpleSymbol simpleSymbol134;
        SimpleSymbol simpleSymbol135;
        SimpleSymbol simpleSymbol136;
        SimpleSymbol simpleSymbol137;
        SimpleSymbol simpleSymbol138;
        SimpleSymbol simpleSymbol139;
        testing testing;
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
        ModuleMethod moduleMethod11;
        ModuleMethod moduleMethod12;
        ModuleMethod moduleMethod13;
        ModuleMethod moduleMethod14;
        ModuleMethod moduleMethod15;
        ModuleMethod moduleMethod16;
        ModuleMethod moduleMethod17;
        ModuleMethod moduleMethod18;
        ModuleMethod moduleMethod19;
        ModuleMethod moduleMethod20;
        ModuleMethod moduleMethod21;
        ModuleMethod moduleMethod22;
        ModuleMethod moduleMethod23;
        ModuleMethod moduleMethod24;
        ModuleMethod moduleMethod25;
        ModuleMethod moduleMethod26;
        ModuleMethod moduleMethod27;
        ModuleMethod moduleMethod28;
        ModuleMethod moduleMethod29;
        ModuleMethod moduleMethod30;
        ModuleMethod moduleMethod31;
        ModuleMethod moduleMethod32;
        ModuleMethod moduleMethod33;
        ModuleMethod moduleMethod34;
        ModuleMethod moduleMethod35;
        ModuleMethod moduleMethod36;
        ModuleMethod moduleMethod37;
        ModuleMethod moduleMethod38;
        ModuleMethod moduleMethod39;
        ModuleMethod moduleMethod40;
        ModuleMethod moduleMethod41;
        ModuleMethod moduleMethod42;
        ModuleMethod moduleMethod43;
        ModuleMethod moduleMethod44;
        ModuleMethod moduleMethod45;
        ModuleMethod moduleMethod46;
        ModuleMethod moduleMethod47;
        ModuleMethod moduleMethod48;
        ModuleMethod moduleMethod49;
        ModuleMethod moduleMethod50;
        ModuleMethod moduleMethod51;
        ModuleMethod moduleMethod52;
        ModuleMethod moduleMethod53;
        ModuleMethod moduleMethod54;
        ModuleMethod moduleMethod55;
        ModuleMethod moduleMethod56;
        ModuleMethod moduleMethod57;
        ModuleMethod moduleMethod58;
        ModuleMethod moduleMethod59;
        ModuleMethod moduleMethod60;
        ModuleMethod moduleMethod61;
        ModuleMethod moduleMethod62;
        ModuleMethod moduleMethod63;
        ModuleMethod moduleMethod64;
        ModuleMethod moduleMethod65;
        Procedure procedure;
        Procedure procedure2;
        Procedure procedure3;
        Procedure procedure4;
        Procedure procedure5;
        Procedure procedure6;
        Procedure procedure7;
        ModuleMethod moduleMethod66;
        ModuleMethod moduleMethod67;
        ModuleMethod moduleMethod68;
        ModuleMethod moduleMethod69;
        ModuleMethod moduleMethod70;
        ModuleMethod moduleMethod71;
        ModuleMethod moduleMethod72;
        new SimpleSymbol("dynamic-wind");
        Lit165 = (SimpleSymbol) simpleSymbol.readResolve();
        new SimpleSymbol("p");
        Lit164 = (SimpleSymbol) simpleSymbol2.readResolve();
        new SimpleSymbol("exp");
        Lit163 = (SimpleSymbol) simpleSymbol3.readResolve();
        new SimpleSymbol("res");
        Lit162 = (SimpleSymbol) simpleSymbol4.readResolve();
        new SimpleSymbol("if");
        Lit161 = (SimpleSymbol) simpleSymbol5.readResolve();
        new SimpleSymbol("name");
        Lit160 = (SimpleSymbol) simpleSymbol6.readResolve();
        new SimpleSymbol(GetNamedPart.INSTANCEOF_METHOD_NAME);
        Lit159 = (SimpleSymbol) simpleSymbol7.readResolve();
        new SimpleSymbol("cond");
        Lit158 = (SimpleSymbol) simpleSymbol8.readResolve();
        new SimpleSymbol("actual-error");
        Lit157 = (SimpleSymbol) simpleSymbol9.readResolve();
        new SimpleSymbol("<java.lang.Throwable>");
        Lit156 = (SimpleSymbol) simpleSymbol10.readResolve();
        new SimpleSymbol("actual-value");
        Lit155 = (SimpleSymbol) simpleSymbol11.readResolve();
        new SimpleSymbol("try-catch");
        Lit154 = (SimpleSymbol) simpleSymbol12.readResolve();
        new SimpleSymbol("et");
        Lit153 = (SimpleSymbol) simpleSymbol13.readResolve();
        new SimpleSymbol("expected-error");
        Lit152 = (SimpleSymbol) simpleSymbol14.readResolve();
        new SimpleSymbol("ex");
        Lit151 = (SimpleSymbol) simpleSymbol15.readResolve();
        new SimpleSymbol("let*");
        Lit150 = (SimpleSymbol) simpleSymbol16.readResolve();
        new SimpleSymbol("r");
        Lit149 = (SimpleSymbol) simpleSymbol17.readResolve();
        new SimpleSymbol("saved-runner");
        Lit148 = (SimpleSymbol) simpleSymbol18.readResolve();
        new SimpleSymbol("lambda");
        Lit147 = (SimpleSymbol) simpleSymbol19.readResolve();
        new SimpleSymbol("test-runner-current");
        Lit146 = (SimpleSymbol) simpleSymbol20.readResolve();
        new SimpleSymbol("cons");
        Lit145 = (SimpleSymbol) simpleSymbol21.readResolve();
        new SimpleSymbol("let");
        Lit144 = (SimpleSymbol) simpleSymbol22.readResolve();
        new SimpleSymbol("runner");
        Lit143 = (SimpleSymbol) simpleSymbol23.readResolve();
        new SimpleSymbol("test-read-eval-string");
        Lit142 = (SimpleSymbol) simpleSymbol24.readResolve();
        new SimpleSymbol("test-match-name");
        Lit141 = (SimpleSymbol) simpleSymbol25.readResolve();
        SyntaxRules syntaxRules14 = syntaxRules;
        Object[] objArr = new Object[1];
        Object[] objArr2 = objArr;
        Object[] objArr3 = objArr;
        new SimpleSymbol("test-expect-fail");
        SimpleSymbol simpleSymbol140 = (SimpleSymbol) simpleSymbol26.readResolve();
        Lit139 = simpleSymbol140;
        objArr3[0] = simpleSymbol140;
        SyntaxRule[] syntaxRuleArr = new SyntaxRule[1];
        SyntaxRule[] syntaxRuleArr2 = syntaxRuleArr;
        SyntaxRule[] syntaxRuleArr3 = syntaxRuleArr;
        SyntaxRule syntaxRule19 = syntaxRule;
        SyntaxPattern syntaxPattern30 = syntaxPattern;
        new SyntaxPattern("\f\u0018\r\u0007\u0000\b\b", new Object[0], 1);
        Object[] objArr4 = new Object[8];
        objArr4[0] = Lit144;
        Object[] objArr5 = objArr4;
        Object[] objArr6 = objArr5;
        Object[] objArr7 = objArr5;
        SimpleSymbol simpleSymbol141 = Lit143;
        new SimpleSymbol("test-runner-get");
        SimpleSymbol simpleSymbol142 = (SimpleSymbol) simpleSymbol27.readResolve();
        Lit60 = simpleSymbol142;
        objArr7[1] = PairWithPosition.make(PairWithPosition.make(simpleSymbol141, PairWithPosition.make(PairWithPosition.make(simpleSymbol142, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3952660), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3952660), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3952652), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3952651);
        Object[] objArr8 = objArr6;
        Object[] objArr9 = objArr8;
        Object[] objArr10 = objArr8;
        new SimpleSymbol("%test-runner-fail-list!");
        SimpleSymbol simpleSymbol143 = (SimpleSymbol) simpleSymbol28.readResolve();
        Lit34 = simpleSymbol143;
        objArr10[2] = simpleSymbol143;
        Object[] objArr11 = objArr9;
        objArr11[3] = Lit143;
        Object[] objArr12 = objArr11;
        objArr12[4] = Lit145;
        Object[] objArr13 = objArr12;
        Object[] objArr14 = objArr13;
        Object[] objArr15 = objArr13;
        new SimpleSymbol("test-match-all");
        SimpleSymbol simpleSymbol144 = (SimpleSymbol) simpleSymbol29.readResolve();
        Lit131 = simpleSymbol144;
        objArr15[5] = simpleSymbol144;
        Object[] objArr16 = objArr14;
        Object[] objArr17 = objArr16;
        Object[] objArr18 = objArr16;
        new SimpleSymbol("%test-as-specifier");
        SimpleSymbol simpleSymbol145 = (SimpleSymbol) simpleSymbol30.readResolve();
        Lit136 = simpleSymbol145;
        objArr18[6] = simpleSymbol145;
        Object[] objArr19 = objArr17;
        Object[] objArr20 = objArr19;
        Object[] objArr21 = objArr19;
        new SimpleSymbol("%test-runner-fail-list");
        SimpleSymbol simpleSymbol146 = (SimpleSymbol) simpleSymbol31.readResolve();
        Lit33 = simpleSymbol146;
        objArr21[7] = PairWithPosition.make(PairWithPosition.make(simpleSymbol146, PairWithPosition.make(Lit143, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3964958), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3964934), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3964934);
        new SyntaxRule(syntaxPattern30, "\u0003", "\u0011\u0018\u0004\u0011\u0018\f\b\u0011\u0018\u0014\u0011\u0018\u001c\b\u0011\u0018$Q\u0011\u0018,\b\u0005\u0011\u00184\b\u0003\u0018<", objArr20, 1);
        syntaxRuleArr3[0] = syntaxRule19;
        new SyntaxRules(objArr2, syntaxRuleArr2, 1);
        Lit140 = syntaxRules14;
        SyntaxRules syntaxRules15 = syntaxRules2;
        Object[] objArr22 = new Object[1];
        Object[] objArr23 = objArr22;
        Object[] objArr24 = objArr22;
        new SimpleSymbol("test-skip");
        SimpleSymbol simpleSymbol147 = (SimpleSymbol) simpleSymbol32.readResolve();
        Lit137 = simpleSymbol147;
        objArr24[0] = simpleSymbol147;
        SyntaxRule[] syntaxRuleArr4 = new SyntaxRule[1];
        SyntaxRule[] syntaxRuleArr5 = syntaxRuleArr4;
        SyntaxRule[] syntaxRuleArr6 = syntaxRuleArr4;
        SyntaxRule syntaxRule20 = syntaxRule2;
        SyntaxPattern syntaxPattern31 = syntaxPattern2;
        new SyntaxPattern("\f\u0018\r\u0007\u0000\b\b", new Object[0], 1);
        Object[] objArr25 = new Object[8];
        objArr25[0] = Lit144;
        Object[] objArr26 = objArr25;
        objArr26[1] = PairWithPosition.make(PairWithPosition.make(Lit143, PairWithPosition.make(PairWithPosition.make(Lit60, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3919892), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3919892), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3919884), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3919883);
        Object[] objArr27 = objArr26;
        Object[] objArr28 = objArr27;
        Object[] objArr29 = objArr27;
        new SimpleSymbol("%test-runner-skip-list!");
        SimpleSymbol simpleSymbol148 = (SimpleSymbol) simpleSymbol33.readResolve();
        Lit32 = simpleSymbol148;
        objArr29[2] = simpleSymbol148;
        Object[] objArr30 = objArr28;
        objArr30[3] = Lit143;
        Object[] objArr31 = objArr30;
        objArr31[4] = Lit145;
        Object[] objArr32 = objArr31;
        objArr32[5] = Lit131;
        Object[] objArr33 = objArr32;
        objArr33[6] = Lit136;
        Object[] objArr34 = objArr33;
        Object[] objArr35 = objArr34;
        Object[] objArr36 = objArr34;
        new SimpleSymbol("%test-runner-skip-list");
        SimpleSymbol simpleSymbol149 = (SimpleSymbol) simpleSymbol34.readResolve();
        Lit31 = simpleSymbol149;
        objArr36[7] = PairWithPosition.make(PairWithPosition.make(simpleSymbol149, PairWithPosition.make(Lit143, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3932190), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3932166), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3932166);
        new SyntaxRule(syntaxPattern31, "\u0003", "\u0011\u0018\u0004\u0011\u0018\f\b\u0011\u0018\u0014\u0011\u0018\u001c\b\u0011\u0018$Q\u0011\u0018,\b\u0005\u0011\u00184\b\u0003\u0018<", objArr35, 1);
        syntaxRuleArr6[0] = syntaxRule20;
        new SyntaxRules(objArr23, syntaxRuleArr5, 1);
        Lit138 = syntaxRules15;
        SyntaxRules syntaxRules16 = syntaxRules3;
        Object[] objArr37 = new Object[1];
        Object[] objArr38 = objArr37;
        Object[] objArr39 = objArr37;
        new SimpleSymbol("test-match-any");
        SimpleSymbol simpleSymbol150 = (SimpleSymbol) simpleSymbol35.readResolve();
        Lit134 = simpleSymbol150;
        objArr39[0] = simpleSymbol150;
        SyntaxRule[] syntaxRuleArr7 = new SyntaxRule[1];
        SyntaxRule[] syntaxRuleArr8 = syntaxRuleArr7;
        SyntaxRule[] syntaxRuleArr9 = syntaxRuleArr7;
        SyntaxRule syntaxRule21 = syntaxRule3;
        SyntaxPattern syntaxPattern32 = syntaxPattern3;
        new SyntaxPattern("\f\u0018\r\u0007\u0000\b\b", new Object[0], 1);
        Object[] objArr40 = new Object[2];
        Object[] objArr41 = objArr40;
        Object[] objArr42 = objArr40;
        new SimpleSymbol("%test-match-any");
        SimpleSymbol simpleSymbol151 = (SimpleSymbol) simpleSymbol36.readResolve();
        Lit133 = simpleSymbol151;
        objArr42[0] = simpleSymbol151;
        Object[] objArr43 = objArr41;
        objArr43[1] = Lit136;
        new SyntaxRule(syntaxPattern32, "\u0003", "\u0011\u0018\u0004\b\u0005\u0011\u0018\f\b\u0003", objArr43, 1);
        syntaxRuleArr9[0] = syntaxRule21;
        new SyntaxRules(objArr38, syntaxRuleArr8, 1);
        Lit135 = syntaxRules16;
        SyntaxRules syntaxRules17 = syntaxRules4;
        Object[] objArr44 = new Object[1];
        Object[] objArr45 = objArr44;
        objArr44[0] = Lit131;
        SyntaxRule[] syntaxRuleArr10 = new SyntaxRule[1];
        SyntaxRule[] syntaxRuleArr11 = syntaxRuleArr10;
        SyntaxRule[] syntaxRuleArr12 = syntaxRuleArr10;
        SyntaxRule syntaxRule22 = syntaxRule4;
        SyntaxPattern syntaxPattern33 = syntaxPattern4;
        new SyntaxPattern("\f\u0018\r\u0007\u0000\b\b", new Object[0], 1);
        Object[] objArr46 = new Object[2];
        Object[] objArr47 = objArr46;
        Object[] objArr48 = objArr46;
        new SimpleSymbol("%test-match-all");
        SimpleSymbol simpleSymbol152 = (SimpleSymbol) simpleSymbol37.readResolve();
        Lit130 = simpleSymbol152;
        objArr48[0] = simpleSymbol152;
        Object[] objArr49 = objArr47;
        objArr49[1] = Lit136;
        new SyntaxRule(syntaxPattern33, "\u0003", "\u0011\u0018\u0004\b\u0005\u0011\u0018\f\b\u0003", objArr49, 1);
        syntaxRuleArr12[0] = syntaxRule22;
        new SyntaxRules(objArr45, syntaxRuleArr11, 1);
        Lit132 = syntaxRules17;
        SyntaxRules syntaxRules18 = syntaxRules5;
        Object[] objArr50 = new Object[1];
        Object[] objArr51 = objArr50;
        Object[] objArr52 = objArr50;
        new SimpleSymbol("test-match-nth");
        SimpleSymbol simpleSymbol153 = (SimpleSymbol) simpleSymbol38.readResolve();
        Lit128 = simpleSymbol153;
        objArr52[0] = simpleSymbol153;
        SyntaxRule[] syntaxRuleArr13 = new SyntaxRule[2];
        SyntaxRule[] syntaxRuleArr14 = syntaxRuleArr13;
        SyntaxRule[] syntaxRuleArr15 = syntaxRuleArr13;
        SyntaxRule syntaxRule23 = syntaxRule5;
        SyntaxPattern syntaxPattern34 = syntaxPattern5;
        new SyntaxPattern("\f\u0018\f\u0007\b", new Object[0], 1);
        Object[] objArr53 = new Object[2];
        objArr53[0] = Lit128;
        Object[] objArr54 = objArr53;
        Object[] objArr55 = objArr54;
        Object[] objArr56 = objArr54;
        IntNum make = IntNum.make(1);
        Lit13 = make;
        objArr56[1] = PairWithPosition.make(make, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3727384);
        new SyntaxRule(syntaxPattern34, "\u0001", "\u0011\u0018\u0004\t\u0003\u0018\f", objArr55, 0);
        syntaxRuleArr15[0] = syntaxRule23;
        SyntaxRule[] syntaxRuleArr16 = syntaxRuleArr14;
        SyntaxRule[] syntaxRuleArr17 = syntaxRuleArr16;
        SyntaxRule[] syntaxRuleArr18 = syntaxRuleArr16;
        SyntaxRule syntaxRule24 = syntaxRule6;
        SyntaxPattern syntaxPattern35 = syntaxPattern6;
        new SyntaxPattern("\f\u0018\f\u0007\f\u000f\b", new Object[0], 2);
        Object[] objArr57 = new Object[1];
        Object[] objArr58 = objArr57;
        Object[] objArr59 = objArr57;
        new SimpleSymbol("%test-match-nth");
        SimpleSymbol simpleSymbol154 = (SimpleSymbol) simpleSymbol39.readResolve();
        Lit127 = simpleSymbol154;
        objArr59[0] = simpleSymbol154;
        new SyntaxRule(syntaxPattern35, "\u0001\u0001", "\u0011\u0018\u0004\t\u0003\b\u000b", objArr58, 0);
        syntaxRuleArr18[1] = syntaxRule24;
        new SyntaxRules(objArr51, syntaxRuleArr17, 2);
        Lit129 = syntaxRules18;
        SyntaxRules syntaxRules19 = syntaxRules6;
        Object[] objArr60 = new Object[1];
        Object[] objArr61 = objArr60;
        Object[] objArr62 = objArr60;
        new SimpleSymbol("test-with-runner");
        SimpleSymbol simpleSymbol155 = (SimpleSymbol) simpleSymbol40.readResolve();
        Lit125 = simpleSymbol155;
        objArr62[0] = simpleSymbol155;
        SyntaxRule[] syntaxRuleArr19 = new SyntaxRule[1];
        SyntaxRule[] syntaxRuleArr20 = syntaxRuleArr19;
        SyntaxRule[] syntaxRuleArr21 = syntaxRuleArr19;
        SyntaxRule syntaxRule25 = syntaxRule7;
        SyntaxPattern syntaxPattern36 = syntaxPattern7;
        new SyntaxPattern("\f\u0018\f\u0007\r\u000f\b\b\b", new Object[0], 2);
        Object[] objArr63 = new Object[6];
        objArr63[0] = Lit144;
        Object[] objArr64 = objArr63;
        objArr64[1] = PairWithPosition.make(PairWithPosition.make(Lit148, PairWithPosition.make(PairWithPosition.make(Lit146, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3657754), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3657754), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3657740), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3657739);
        Object[] objArr65 = objArr64;
        objArr65[2] = Lit165;
        Object[] objArr66 = objArr65;
        objArr66[3] = Lit147;
        Object[] objArr67 = objArr66;
        objArr67[4] = Lit146;
        Object[] objArr68 = objArr67;
        objArr68[5] = PairWithPosition.make(PairWithPosition.make(Lit147, PairWithPosition.make(LList.Empty, PairWithPosition.make(PairWithPosition.make(Lit146, PairWithPosition.make(Lit148, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3674156), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3674135), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3674135), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3674132), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3674124), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3674124);
        new SyntaxRule(syntaxPattern36, "\u0001\u0003", "\u0011\u0018\u0004\u0011\u0018\f\b\u0011\u0018\u0014Y\u0011\u0018\u001c\t\u0010\b\u0011\u0018$\b\u0003A\u0011\u0018\u001c\t\u0010\b\r\u000b\u0018,", objArr68, 1);
        syntaxRuleArr21[0] = syntaxRule25;
        new SyntaxRules(objArr61, syntaxRuleArr20, 2);
        Lit126 = syntaxRules19;
        new SimpleSymbol("test-apply");
        Lit124 = (SimpleSymbol) simpleSymbol41.readResolve();
        SyntaxTemplate syntaxTemplate15 = syntaxTemplate;
        Object[] objArr69 = new Object[6];
        objArr69[0] = Lit150;
        Object[] objArr70 = objArr69;
        objArr70[1] = PairWithPosition.make(PairWithPosition.make(Lit149, PairWithPosition.make(PairWithPosition.make(Lit60, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3514382), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3514382), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3514379), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3514378);
        Object[] objArr71 = objArr70;
        Object[] objArr72 = objArr71;
        Object[] objArr73 = objArr71;
        new SimpleSymbol("test-result-alist!");
        SimpleSymbol simpleSymbol156 = (SimpleSymbol) simpleSymbol42.readResolve();
        Lit52 = simpleSymbol156;
        objArr73[2] = simpleSymbol156;
        Object[] objArr74 = objArr72;
        objArr74[3] = Lit149;
        Object[] objArr75 = objArr74;
        Object[] objArr76 = objArr75;
        Object[] objArr77 = objArr75;
        new SimpleSymbol("%test-error");
        SimpleSymbol simpleSymbol157 = (SimpleSymbol) simpleSymbol43.readResolve();
        Lit115 = simpleSymbol157;
        objArr77[4] = simpleSymbol157;
        Object[] objArr78 = objArr76;
        objArr78[5] = Boolean.TRUE;
        new SyntaxTemplate("\u0001\u0001\u0001", "\u0011\u0018\u0004\u0011\u0018\fA\u0011\u0018\u0014\u0011\u0018\u001c\b\u0013\b\u0011\u0018$\u0011\u0018\u001c\u0011\u0018,\b\u000b", objArr78, 0);
        Lit123 = syntaxTemplate15;
        new SyntaxPattern(",\f\u0007\f\u000f\b\f\u0017\b", new Object[0], 3);
        Lit122 = syntaxPattern8;
        SyntaxTemplate syntaxTemplate16 = syntaxTemplate2;
        Object[] objArr79 = new Object[5];
        objArr79[0] = Lit150;
        Object[] objArr80 = objArr79;
        objArr80[1] = PairWithPosition.make(PairWithPosition.make(Lit149, PairWithPosition.make(PairWithPosition.make(Lit60, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3493902), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3493902), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3493899), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3493898);
        Object[] objArr81 = objArr80;
        objArr81[2] = Lit52;
        Object[] objArr82 = objArr81;
        objArr82[3] = Lit149;
        Object[] objArr83 = objArr82;
        objArr83[4] = Lit115;
        new SyntaxTemplate("\u0001\u0001\u0001\u0001", "\u0011\u0018\u0004\u0011\u0018\fA\u0011\u0018\u0014\u0011\u0018\u001c\b\u001b\b\u0011\u0018$\u0011\u0018\u001c\t\u000b\b\u0013", objArr83, 0);
        Lit121 = syntaxTemplate16;
        new SyntaxPattern("<\f\u0007\f\u000f\f\u0017\b\f\u001f\b", new Object[0], 4);
        Lit120 = syntaxPattern9;
        SyntaxTemplate syntaxTemplate17 = syntaxTemplate3;
        Object[] objArr84 = new Object[8];
        objArr84[0] = Lit150;
        Object[] objArr85 = objArr84;
        objArr85[1] = PairWithPosition.make(Lit149, PairWithPosition.make(PairWithPosition.make(Lit60, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3469326), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3469326), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3469323);
        Object[] objArr86 = objArr85;
        objArr86[2] = Lit160;
        Object[] objArr87 = objArr86;
        objArr87[3] = Lit52;
        Object[] objArr88 = objArr87;
        objArr88[4] = Lit149;
        Object[] objArr89 = objArr88;
        objArr89[5] = Lit145;
        Object[] objArr90 = objArr89;
        Object[] objArr91 = objArr90;
        Object[] objArr92 = objArr90;
        new SimpleSymbol(LispLanguage.quote_sym);
        SimpleSymbol simpleSymbol158 = (SimpleSymbol) simpleSymbol44.readResolve();
        SimpleSymbol simpleSymbol159 = simpleSymbol158;
        Lit15 = simpleSymbol158;
        new SimpleSymbol("test-name");
        SimpleSymbol simpleSymbol160 = (SimpleSymbol) simpleSymbol45.readResolve();
        Lit7 = simpleSymbol160;
        objArr92[6] = PairWithPosition.make(simpleSymbol159, PairWithPosition.make(simpleSymbol160, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3477545), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3477545);
        Object[] objArr93 = objArr91;
        objArr93[7] = Lit115;
        new SyntaxTemplate("\u0001\u0001\u0001\u0001\u0001", "\u0011\u0018\u0004I\u0011\u0018\f\b\u0011\u0018\u0014\b\u000b©\u0011\u0018\u001c\u0011\u0018$\b\u0011\u0018,A\u0011\u0018,\u0011\u00184\b\u000b\b#\b\u0011\u0018<\u0011\u0018$\t\u0013\b\u001b", objArr93, 0);
        Lit119 = syntaxTemplate17;
        new SyntaxPattern("L\f\u0007\f\u000f\f\u0017\f\u001f\b\f'\b", new Object[0], 5);
        Lit118 = syntaxPattern10;
        new SimpleSymbol("test-error");
        Lit117 = (SimpleSymbol) simpleSymbol46.readResolve();
        SyntaxRules syntaxRules20 = syntaxRules7;
        Object[] objArr94 = new Object[1];
        Object[] objArr95 = objArr94;
        objArr94[0] = Lit115;
        SyntaxRule[] syntaxRuleArr22 = new SyntaxRule[2];
        SyntaxRule[] syntaxRuleArr23 = syntaxRuleArr22;
        SyntaxRule[] syntaxRuleArr24 = syntaxRuleArr22;
        SyntaxRule syntaxRule26 = syntaxRule8;
        SyntaxPattern syntaxPattern37 = syntaxPattern11;
        new SyntaxPattern("\f\u0018\f\u0007\f\u0002\f\u000f\b", new Object[]{Boolean.TRUE}, 2);
        Object[] objArr96 = new Object[14];
        objArr96[0] = Lit158;
        Object[] objArr97 = objArr96;
        Object[] objArr98 = objArr97;
        Object[] objArr99 = objArr97;
        new SimpleSymbol("%test-on-test-begin");
        SimpleSymbol simpleSymbol161 = (SimpleSymbol) simpleSymbol47.readResolve();
        Lit86 = simpleSymbol161;
        objArr99[1] = simpleSymbol161;
        Object[] objArr100 = objArr98;
        Object[] objArr101 = objArr100;
        Object[] objArr102 = objArr100;
        new SimpleSymbol("test-result-set!");
        SimpleSymbol simpleSymbol162 = (SimpleSymbol) simpleSymbol48.readResolve();
        Lit78 = simpleSymbol162;
        objArr102[2] = simpleSymbol162;
        Object[] objArr103 = objArr101;
        objArr103[3] = PairWithPosition.make(PairWithPosition.make(Lit15, PairWithPosition.make(Lit152, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3223581), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3223581), PairWithPosition.make(Boolean.TRUE, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3223596), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3223580);
        Object[] objArr104 = objArr103;
        Object[] objArr105 = objArr104;
        Object[] objArr106 = objArr104;
        new SimpleSymbol("%test-on-test-end");
        SimpleSymbol simpleSymbol163 = (SimpleSymbol) simpleSymbol49.readResolve();
        Lit87 = simpleSymbol163;
        objArr106[4] = simpleSymbol163;
        Object[] objArr107 = objArr105;
        objArr107[5] = Lit154;
        Object[] objArr108 = objArr107;
        objArr108[6] = Lit144;
        Object[] objArr109 = objArr108;
        objArr109[7] = PairWithPosition.make(Lit15, PairWithPosition.make(Lit155, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3239966), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3239966);
        Object[] objArr110 = objArr109;
        objArr110[8] = PairWithPosition.make(Boolean.FALSE, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3244041);
        Object[] objArr111 = objArr110;
        objArr111[9] = Lit151;
        Object[] objArr112 = objArr111;
        objArr112[10] = Lit156;
        Object[] objArr113 = objArr112;
        objArr113[11] = PairWithPosition.make(PairWithPosition.make(Lit15, PairWithPosition.make(Lit157, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3252256), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3252256), PairWithPosition.make(Lit151, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3252269), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3252255);
        Object[] objArr114 = objArr113;
        objArr114[12] = PairWithPosition.make(Boolean.TRUE, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3256331);
        Object[] objArr115 = objArr114;
        Object[] objArr116 = objArr115;
        Object[] objArr117 = objArr115;
        new SimpleSymbol("%test-report-result");
        SimpleSymbol simpleSymbol164 = (SimpleSymbol) simpleSymbol50.readResolve();
        Lit83 = simpleSymbol164;
        objArr117[13] = PairWithPosition.make(PairWithPosition.make(simpleSymbol164, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3260424), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3260424);
        new SyntaxRule(syntaxPattern37, "\u0001\u0001", "\u0011\u0018\u0004\b)\u0011\u0018\f\b\u00039\u0011\u0018\u0014\t\u0003\u0018\u001cũ\u0011\u0018$\t\u0003\b\u0011\u0018,\u0011\u00184\t\u0010Q\u0011\u0018\u0014\t\u0003\u0011\u0018<\b\u000b\u0018D\b\u0011\u0018L\u0011\u0018T9\u0011\u0018\u0014\t\u0003\u0018\\\u0018d\u0018l", objArr116, 0);
        syntaxRuleArr24[0] = syntaxRule26;
        SyntaxRule[] syntaxRuleArr25 = syntaxRuleArr23;
        SyntaxRule[] syntaxRuleArr26 = syntaxRuleArr25;
        SyntaxRule[] syntaxRuleArr27 = syntaxRuleArr25;
        SyntaxRule syntaxRule27 = syntaxRule9;
        SyntaxPattern syntaxPattern38 = syntaxPattern12;
        new SyntaxPattern("\f\u0018\f\u0007\f\u000f\f\u0017\b", new Object[0], 3);
        Object[] objArr118 = new Object[15];
        objArr118[0] = Lit161;
        Object[] objArr119 = objArr118;
        objArr119[1] = Lit86;
        Object[] objArr120 = objArr119;
        objArr120[2] = Lit144;
        Object[] objArr121 = objArr120;
        objArr121[3] = Lit153;
        Object[] objArr122 = objArr121;
        objArr122[4] = Lit78;
        Object[] objArr123 = objArr122;
        objArr123[5] = PairWithPosition.make(PairWithPosition.make(Lit15, PairWithPosition.make(Lit152, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3276828), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3276828), PairWithPosition.make(Lit153, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3276843), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3276827);
        Object[] objArr124 = objArr123;
        objArr124[6] = Lit87;
        Object[] objArr125 = objArr124;
        objArr125[7] = Lit154;
        Object[] objArr126 = objArr125;
        objArr126[8] = PairWithPosition.make(Lit15, PairWithPosition.make(Lit155, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3293213), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3293213);
        Object[] objArr127 = objArr126;
        objArr127[9] = PairWithPosition.make(Boolean.FALSE, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3297288);
        Object[] objArr128 = objArr127;
        objArr128[10] = Lit151;
        Object[] objArr129 = objArr128;
        objArr129[11] = Lit156;
        Object[] objArr130 = objArr129;
        objArr130[12] = PairWithPosition.make(PairWithPosition.make(Lit15, PairWithPosition.make(Lit157, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3305503), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3305503), PairWithPosition.make(Lit151, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3305516), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3305502);
        Object[] objArr131 = objArr130;
        SimpleSymbol simpleSymbol165 = Lit158;
        new SimpleSymbol("and");
        SimpleSymbol simpleSymbol166 = Lit159;
        SimpleSymbol simpleSymbol167 = Lit153;
        new SimpleSymbol("<gnu.bytecode.ClassType>");
        PairWithPosition make2 = PairWithPosition.make(simpleSymbol166, PairWithPosition.make(simpleSymbol167, PairWithPosition.make((SimpleSymbol) simpleSymbol52.readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3309604), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3309601), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3309590);
        new SimpleSymbol("$lookup$");
        new SimpleSymbol("gnu.bytecode.ClassType");
        new SimpleSymbol(LispLanguage.quasiquote_sym);
        new SimpleSymbol("isSubclass");
        PairWithPosition make3 = PairWithPosition.make(PairWithPosition.make((SimpleSymbol) simpleSymbol51.readResolve(), PairWithPosition.make(make2, PairWithPosition.make(PairWithPosition.make(PairWithPosition.make((SimpleSymbol) simpleSymbol53.readResolve(), Pair.make((SimpleSymbol) simpleSymbol54.readResolve(), Pair.make(Pair.make((SimpleSymbol) simpleSymbol55.readResolve(), Pair.make((SimpleSymbol) simpleSymbol56.readResolve(), LList.Empty)), LList.Empty)), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3313673), PairWithPosition.make(Lit153, PairWithPosition.make(Lit156, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3313710), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3313707), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3313672), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3313672), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3309590), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3309585), PairWithPosition.make(PairWithPosition.make(Lit159, PairWithPosition.make(Lit151, PairWithPosition.make(Lit153, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3317784), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3317781), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3317770), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3317770), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3309584);
        new SimpleSymbol("else");
        objArr131[13] = PairWithPosition.make(PairWithPosition.make(simpleSymbol165, PairWithPosition.make(make3, PairWithPosition.make(PairWithPosition.make((SimpleSymbol) simpleSymbol57.readResolve(), PairWithPosition.make(Boolean.TRUE, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3321871), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3321865), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3321865), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3309584), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3309578), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3309578);
        Object[] objArr132 = objArr131;
        objArr132[14] = PairWithPosition.make(PairWithPosition.make(Lit83, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3325959), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 3325959);
        new SyntaxRule(syntaxPattern38, "\u0001\u0001\u0001", "\u0011\u0018\u0004)\u0011\u0018\f\b\u0003\b\u0011\u0018\u00141\b\u0011\u0018\u001c\b\u000b9\u0011\u0018$\t\u0003\u0018,ũ\u0011\u00184\t\u0003\b\u0011\u0018<\u0011\u0018\u0014\t\u0010Q\u0011\u0018$\t\u0003\u0011\u0018D\b\u0013\u0018L\b\u0011\u0018T\u0011\u0018\\9\u0011\u0018$\t\u0003\u0018d\u0018l\u0018t", objArr132, 0);
        syntaxRuleArr27[1] = syntaxRule27;
        new SyntaxRules(objArr95, syntaxRuleArr26, 3);
        Lit116 = syntaxRules20;
        SyntaxTemplate syntaxTemplate18 = syntaxTemplate4;
        Object[] objArr133 = new Object[6];
        objArr133[0] = Lit150;
        Object[] objArr134 = objArr133;
        objArr134[1] = PairWithPosition.make(PairWithPosition.make(Lit149, PairWithPosition.make(PairWithPosition.make(Lit60, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2916364), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2916364), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2916361), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2916360);
        Object[] objArr135 = objArr134;
        objArr135[2] = Lit52;
        Object[] objArr136 = objArr135;
        objArr136[3] = Lit149;
        Object[] objArr137 = objArr136;
        Object[] objArr138 = objArr137;
        Object[] objArr139 = objArr137;
        new SimpleSymbol("%test-comp2body");
        SimpleSymbol simpleSymbol168 = (SimpleSymbol) simpleSymbol58.readResolve();
        Lit89 = simpleSymbol168;
        objArr139[4] = simpleSymbol168;
        Object[] objArr140 = objArr138;
        Object[] objArr141 = objArr140;
        Object[] objArr142 = objArr140;
        new SimpleSymbol("%test-approximimate=");
        SimpleSymbol simpleSymbol169 = (SimpleSymbol) simpleSymbol59.readResolve();
        Lit91 = simpleSymbol169;
        objArr142[5] = simpleSymbol169;
        new SyntaxTemplate("\u0001\u0001\u0001\u0001\u0001", "\u0011\u0018\u0004\u0011\u0018\fA\u0011\u0018\u0014\u0011\u0018\u001c\b#\b\u0011\u0018$\u0011\u0018\u001c)\u0011\u0018,\b\u001b\t\u000b\b\u0013", objArr141, 0);
        Lit114 = syntaxTemplate18;
        new SyntaxPattern("L\f\u0007\f\u000f\f\u0017\f\u001f\b\f'\b", new Object[0], 5);
        Lit113 = syntaxPattern13;
        SyntaxTemplate syntaxTemplate19 = syntaxTemplate5;
        Object[] objArr143 = new Object[9];
        objArr143[0] = Lit150;
        Object[] objArr144 = objArr143;
        objArr144[1] = PairWithPosition.make(Lit149, PairWithPosition.make(PairWithPosition.make(Lit60, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2891788), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2891788), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2891785);
        Object[] objArr145 = objArr144;
        objArr145[2] = Lit160;
        Object[] objArr146 = objArr145;
        objArr146[3] = Lit52;
        Object[] objArr147 = objArr146;
        objArr147[4] = Lit149;
        Object[] objArr148 = objArr147;
        objArr148[5] = Lit145;
        Object[] objArr149 = objArr148;
        objArr149[6] = PairWithPosition.make(Lit15, PairWithPosition.make(Lit7, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2900007), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2900007);
        Object[] objArr150 = objArr149;
        objArr150[7] = Lit89;
        Object[] objArr151 = objArr150;
        objArr151[8] = Lit91;
        new SyntaxTemplate("\u0001\u0001\u0001\u0001\u0001\u0001", "\u0011\u0018\u0004I\u0011\u0018\f\b\u0011\u0018\u0014\b\u000b©\u0011\u0018\u001c\u0011\u0018$\b\u0011\u0018,A\u0011\u0018,\u0011\u00184\b\u000b\b+\b\u0011\u0018<\u0011\u0018$)\u0011\u0018D\b#\t\u0013\b\u001b", objArr151, 0);
        Lit112 = syntaxTemplate19;
        new SyntaxPattern("\\\f\u0007\f\u000f\f\u0017\f\u001f\f'\b\f/\b", new Object[0], 6);
        Lit111 = syntaxPattern14;
        new SimpleSymbol("test-approximate");
        Lit110 = (SimpleSymbol) simpleSymbol60.readResolve();
        SyntaxTemplate syntaxTemplate20 = syntaxTemplate6;
        new SimpleSymbol("equal?");
        new SyntaxTemplate("", "\u0018\u0004", new Object[]{(SimpleSymbol) simpleSymbol61.readResolve()}, 0);
        Lit109 = syntaxTemplate20;
        new SimpleSymbol("test-equal");
        Lit108 = (SimpleSymbol) simpleSymbol62.readResolve();
        SyntaxTemplate syntaxTemplate21 = syntaxTemplate7;
        new SimpleSymbol("eq?");
        new SyntaxTemplate("", "\u0018\u0004", new Object[]{(SimpleSymbol) simpleSymbol63.readResolve()}, 0);
        Lit107 = syntaxTemplate21;
        new SimpleSymbol("test-eq");
        Lit106 = (SimpleSymbol) simpleSymbol64.readResolve();
        SyntaxTemplate syntaxTemplate22 = syntaxTemplate8;
        new SimpleSymbol("eqv?");
        new SyntaxTemplate("", "\u0018\u0004", new Object[]{(SimpleSymbol) simpleSymbol65.readResolve()}, 0);
        Lit105 = syntaxTemplate22;
        new SimpleSymbol("test-eqv");
        Lit104 = (SimpleSymbol) simpleSymbol66.readResolve();
        SyntaxTemplate syntaxTemplate23 = syntaxTemplate9;
        Object[] objArr152 = new Object[5];
        objArr152[0] = Lit150;
        Object[] objArr153 = objArr152;
        objArr153[1] = PairWithPosition.make(PairWithPosition.make(Lit149, PairWithPosition.make(PairWithPosition.make(Lit60, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2781198), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2781198), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2781195), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2781194);
        Object[] objArr154 = objArr153;
        objArr154[2] = Lit52;
        Object[] objArr155 = objArr154;
        objArr155[3] = Lit149;
        Object[] objArr156 = objArr155;
        Object[] objArr157 = objArr156;
        Object[] objArr158 = objArr156;
        new SimpleSymbol("%test-comp1body");
        SimpleSymbol simpleSymbol170 = (SimpleSymbol) simpleSymbol67.readResolve();
        Lit92 = simpleSymbol170;
        objArr158[4] = simpleSymbol170;
        new SyntaxTemplate("\u0001\u0001\u0001", "\u0011\u0018\u0004\u0011\u0018\fA\u0011\u0018\u0014\u0011\u0018\u001c\b\u0013\b\u0011\u0018$\u0011\u0018\u001c\b\u000b", objArr157, 0);
        Lit103 = syntaxTemplate23;
        new SyntaxPattern(",\f\u0007\f\u000f\b\f\u0017\b", new Object[0], 3);
        Lit102 = syntaxPattern15;
        SyntaxTemplate syntaxTemplate24 = syntaxTemplate10;
        Object[] objArr159 = new Object[8];
        objArr159[0] = Lit150;
        Object[] objArr160 = objArr159;
        objArr160[1] = PairWithPosition.make(Lit149, PairWithPosition.make(PairWithPosition.make(Lit60, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2756622), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2756622), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2756619);
        Object[] objArr161 = objArr160;
        objArr161[2] = Lit160;
        Object[] objArr162 = objArr161;
        objArr162[3] = Lit52;
        Object[] objArr163 = objArr162;
        objArr163[4] = Lit149;
        Object[] objArr164 = objArr163;
        objArr164[5] = Lit145;
        Object[] objArr165 = objArr164;
        objArr165[6] = PairWithPosition.make(Lit15, PairWithPosition.make(Lit7, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2764841), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2764841);
        Object[] objArr166 = objArr165;
        objArr166[7] = Lit92;
        new SyntaxTemplate("\u0001\u0001\u0001\u0001", "\u0011\u0018\u0004I\u0011\u0018\f\b\u0011\u0018\u0014\b\u000b©\u0011\u0018\u001c\u0011\u0018$\b\u0011\u0018,A\u0011\u0018,\u0011\u00184\b\u000b\b\u001b\b\u0011\u0018<\u0011\u0018$\b\u0013", objArr166, 0);
        Lit101 = syntaxTemplate24;
        new SyntaxPattern("<\f\u0007\f\u000f\f\u0017\b\f\u001f\b", new Object[0], 4);
        Lit100 = syntaxPattern16;
        new SimpleSymbol("test-assert");
        Lit99 = (SimpleSymbol) simpleSymbol68.readResolve();
        SyntaxTemplate syntaxTemplate25 = syntaxTemplate11;
        Object[] objArr167 = new Object[2];
        Object[] objArr168 = objArr167;
        Object[] objArr169 = objArr167;
        new SimpleSymbol("%test-end");
        SimpleSymbol simpleSymbol171 = (SimpleSymbol) simpleSymbol69.readResolve();
        Lit69 = simpleSymbol171;
        objArr169[0] = simpleSymbol171;
        Object[] objArr170 = objArr168;
        objArr170[1] = Boolean.FALSE;
        new SyntaxTemplate("\u0001\u0001", "\u0011\u0018\u0004\u0011\u0018\f\b\u000b", objArr170, 0);
        Lit98 = syntaxTemplate25;
        new SyntaxPattern("\u001c\f\u0007\b\f\u000f\b", new Object[0], 2);
        Lit97 = syntaxPattern17;
        SyntaxTemplate syntaxTemplate26 = syntaxTemplate12;
        new SyntaxTemplate("\u0001\u0001\u0001", "\u0011\u0018\u0004\t\u000b\b\u0013", new Object[]{Lit69}, 0);
        Lit96 = syntaxTemplate26;
        new SyntaxPattern(",\f\u0007\f\u000f\b\f\u0017\b", new Object[0], 3);
        Lit95 = syntaxPattern18;
        new SimpleSymbol("test-end");
        Lit94 = (SimpleSymbol) simpleSymbol70.readResolve();
        SyntaxRules syntaxRules21 = syntaxRules8;
        Object[] objArr171 = new Object[1];
        Object[] objArr172 = objArr171;
        objArr171[0] = Lit92;
        SyntaxRule[] syntaxRuleArr28 = new SyntaxRule[1];
        SyntaxRule[] syntaxRuleArr29 = syntaxRuleArr28;
        SyntaxRule[] syntaxRuleArr30 = syntaxRuleArr28;
        SyntaxRule syntaxRule28 = syntaxRule10;
        SyntaxPattern syntaxPattern39 = syntaxPattern19;
        new SyntaxPattern("\f\u0018\f\u0007\f\u000f\b", new Object[0], 2);
        Object[] objArr173 = new Object[10];
        objArr173[0] = Lit144;
        Object[] objArr174 = objArr173;
        objArr174[1] = Lit161;
        Object[] objArr175 = objArr174;
        objArr175[2] = Lit86;
        Object[] objArr176 = objArr175;
        objArr176[3] = Lit162;
        Object[] objArr177 = objArr176;
        Object[] objArr178 = objArr177;
        Object[] objArr179 = objArr177;
        new SimpleSymbol("%test-evaluate-with-catch");
        SimpleSymbol simpleSymbol172 = (SimpleSymbol) simpleSymbol71.readResolve();
        Lit84 = simpleSymbol172;
        objArr179[4] = simpleSymbol172;
        Object[] objArr180 = objArr178;
        objArr180[5] = Lit78;
        Object[] objArr181 = objArr180;
        objArr181[6] = PairWithPosition.make(PairWithPosition.make(Lit15, PairWithPosition.make(Lit155, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2666526), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2666526), PairWithPosition.make(Lit162, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2666539), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2666525);
        Object[] objArr182 = objArr181;
        objArr182[7] = Lit87;
        Object[] objArr183 = objArr182;
        objArr183[8] = PairWithPosition.make(Lit162, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2670622);
        Object[] objArr184 = objArr183;
        objArr184[9] = PairWithPosition.make(PairWithPosition.make(Lit83, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2674696), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2674696);
        new SyntaxRule(syntaxPattern39, "\u0001\u0001", "\u0011\u0018\u0004\t\u0010ű\u0011\u0018\f)\u0011\u0018\u0014\b\u0003\b\u0011\u0018\u0004\t\u0010\b\u0011\u0018\u0004Q\b\u0011\u0018\u001c\b\u0011\u0018$\b\u000b9\u0011\u0018,\t\u0003\u00184\b\u0011\u0018<\t\u0003\u0018D\u0018L", objArr184, 0);
        syntaxRuleArr30[0] = syntaxRule28;
        new SyntaxRules(objArr172, syntaxRuleArr29, 2);
        Lit93 = syntaxRules21;
        SyntaxRules syntaxRules22 = syntaxRules9;
        Object[] objArr185 = new Object[1];
        Object[] objArr186 = objArr185;
        objArr185[0] = Lit89;
        SyntaxRule[] syntaxRuleArr31 = new SyntaxRule[1];
        SyntaxRule[] syntaxRuleArr32 = syntaxRuleArr31;
        SyntaxRule[] syntaxRuleArr33 = syntaxRuleArr31;
        SyntaxRule syntaxRule29 = syntaxRule11;
        SyntaxPattern syntaxPattern40 = syntaxPattern20;
        new SyntaxPattern("\f\u0018\f\u0007\f\u000f\f\u0017\f\u001f\b", new Object[0], 4);
        Object[] objArr187 = new Object[12];
        objArr187[0] = Lit144;
        Object[] objArr188 = objArr187;
        objArr188[1] = Lit161;
        Object[] objArr189 = objArr188;
        objArr189[2] = Lit86;
        Object[] objArr190 = objArr189;
        objArr190[3] = Lit163;
        Object[] objArr191 = objArr190;
        objArr191[4] = Lit78;
        Object[] objArr192 = objArr191;
        SimpleSymbol simpleSymbol173 = Lit15;
        new SimpleSymbol("expected-value");
        objArr192[5] = PairWithPosition.make(PairWithPosition.make(simpleSymbol173, PairWithPosition.make((SimpleSymbol) simpleSymbol72.readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2592794), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2592794), PairWithPosition.make(Lit163, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2592809), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2592793);
        Object[] objArr193 = objArr192;
        objArr193[6] = Lit162;
        Object[] objArr194 = objArr193;
        objArr194[7] = Lit84;
        Object[] objArr195 = objArr194;
        objArr195[8] = PairWithPosition.make(PairWithPosition.make(Lit15, PairWithPosition.make(Lit155, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2600988), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2600988), PairWithPosition.make(Lit162, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2601001), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2600987);
        Object[] objArr196 = objArr195;
        objArr196[9] = Lit87;
        Object[] objArr197 = objArr196;
        objArr197[10] = PairWithPosition.make(Lit163, PairWithPosition.make(Lit162, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2605094), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2605090);
        Object[] objArr198 = objArr197;
        objArr198[11] = PairWithPosition.make(PairWithPosition.make(Lit83, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2609158), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2609158);
        new SyntaxRule(syntaxPattern40, "\u0001\u0001\u0001\u0001", "\u0011\u0018\u0004\t\u0010Ǳ\u0011\u0018\f)\u0011\u0018\u0014\b\u0003\b\u0011\u0018\u00041\b\u0011\u0018\u001c\b\u00139\u0011\u0018$\t\u0003\u0018,\b\u0011\u0018\u0004Q\b\u0011\u00184\b\u0011\u0018<\b\u001b9\u0011\u0018$\t\u0003\u0018D\b\u0011\u0018L\t\u0003\b\t\u000b\u0018T\u0018\\", objArr198, 0);
        syntaxRuleArr33[0] = syntaxRule29;
        new SyntaxRules(objArr186, syntaxRuleArr32, 4);
        Lit90 = syntaxRules22;
        new SimpleSymbol("test-runner-test-name");
        Lit88 = (SimpleSymbol) simpleSymbol73.readResolve();
        SyntaxRules syntaxRules23 = syntaxRules10;
        Object[] objArr199 = new Object[1];
        Object[] objArr200 = objArr199;
        objArr199[0] = Lit84;
        SyntaxRule[] syntaxRuleArr34 = new SyntaxRule[1];
        SyntaxRule[] syntaxRuleArr35 = syntaxRuleArr34;
        SyntaxRule[] syntaxRuleArr36 = syntaxRuleArr34;
        SyntaxRule syntaxRule30 = syntaxRule12;
        SyntaxPattern syntaxPattern41 = syntaxPattern21;
        new SyntaxPattern("\f\u0018\f\u0007\b", new Object[0], 1);
        Object[] objArr201 = new Object[2];
        objArr201[0] = Lit154;
        Object[] objArr202 = objArr201;
        objArr202[1] = PairWithPosition.make(PairWithPosition.make(Lit151, PairWithPosition.make(Lit156, PairWithPosition.make(PairWithPosition.make(Lit78, PairWithPosition.make(PairWithPosition.make(Lit146, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2347035), PairWithPosition.make(PairWithPosition.make(Lit15, PairWithPosition.make(Lit157, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2347058), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2347058), PairWithPosition.make(Lit151, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2347071), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2347057), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2347035), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2347017), PairWithPosition.make(Boolean.FALSE, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2351113), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2347017), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2342921), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2342917), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2342917);
        new SyntaxRule(syntaxPattern41, "\u0001", "\u0011\u0018\u0004\t\u0003\u0018\f", objArr202, 0);
        syntaxRuleArr36[0] = syntaxRule30;
        new SyntaxRules(objArr200, syntaxRuleArr35, 1);
        Lit85 = syntaxRules23;
        new SimpleSymbol("test-passed?");
        Lit82 = (SimpleSymbol) simpleSymbol74.readResolve();
        new SimpleSymbol("test-result-kind");
        Lit81 = (SimpleSymbol) simpleSymbol75.readResolve();
        new SimpleSymbol("test-result-remove");
        Lit80 = (SimpleSymbol) simpleSymbol76.readResolve();
        new SimpleSymbol("test-result-clear");
        Lit79 = (SimpleSymbol) simpleSymbol77.readResolve();
        new SimpleSymbol("test-on-test-end-simple");
        Lit77 = (SimpleSymbol) simpleSymbol78.readResolve();
        SyntaxRules syntaxRules24 = syntaxRules11;
        Object[] objArr203 = new Object[1];
        Object[] objArr204 = objArr203;
        Object[] objArr205 = objArr203;
        new SimpleSymbol("test-result-ref");
        SimpleSymbol simpleSymbol174 = (SimpleSymbol) simpleSymbol79.readResolve();
        Lit75 = simpleSymbol174;
        objArr205[0] = simpleSymbol174;
        SyntaxRule[] syntaxRuleArr37 = new SyntaxRule[2];
        SyntaxRule[] syntaxRuleArr38 = syntaxRuleArr37;
        SyntaxRule[] syntaxRuleArr39 = syntaxRuleArr37;
        SyntaxRule syntaxRule31 = syntaxRule13;
        SyntaxPattern syntaxPattern42 = syntaxPattern22;
        new SyntaxPattern("\f\u0018\f\u0007\f\u000f\b", new Object[0], 2);
        Object[] objArr206 = new Object[2];
        objArr206[0] = Lit75;
        Object[] objArr207 = objArr206;
        objArr207[1] = PairWithPosition.make(Boolean.FALSE, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 1933348);
        new SyntaxRule(syntaxPattern42, "\u0001\u0001", "\u0011\u0018\u0004\t\u0003\t\u000b\u0018\f", objArr207, 0);
        syntaxRuleArr39[0] = syntaxRule31;
        SyntaxRule[] syntaxRuleArr40 = syntaxRuleArr38;
        SyntaxRule[] syntaxRuleArr41 = syntaxRuleArr40;
        SyntaxRule[] syntaxRuleArr42 = syntaxRuleArr40;
        SyntaxRule syntaxRule32 = syntaxRule14;
        SyntaxPattern syntaxPattern43 = syntaxPattern23;
        new SyntaxPattern("\f\u0018\f\u0007\f\u000f\f\u0017\b", new Object[0], 3);
        Object[] objArr208 = new Object[6];
        objArr208[0] = Lit144;
        Object[] objArr209 = objArr208;
        objArr209[1] = Lit164;
        Object[] objArr210 = objArr209;
        new SimpleSymbol("assq");
        objArr210[2] = (SimpleSymbol) simpleSymbol80.readResolve();
        Object[] objArr211 = objArr210;
        Object[] objArr212 = objArr211;
        Object[] objArr213 = objArr211;
        new SimpleSymbol("test-result-alist");
        SimpleSymbol simpleSymbol175 = (SimpleSymbol) simpleSymbol81.readResolve();
        Lit51 = simpleSymbol175;
        objArr213[3] = simpleSymbol175;
        Object[] objArr214 = objArr212;
        objArr214[4] = Lit161;
        Object[] objArr215 = objArr214;
        new SimpleSymbol("cdr");
        objArr215[5] = PairWithPosition.make((SimpleSymbol) simpleSymbol82.readResolve(), PairWithPosition.make(Lit164, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 1945619), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 1945614);
        new SyntaxRule(syntaxPattern43, "\u0001\u0001\u0001", "\u0011\u0018\u0004\b\u0011\u0018\f\b\u0011\u0018\u0014\t\u000b\b\u0011\u0018\u001c\b\u0003\b\u0011\u0018$\u0011\u0018\f\u0011\u0018,\b\u0013", objArr215, 0);
        syntaxRuleArr42[1] = syntaxRule32;
        new SyntaxRules(objArr204, syntaxRuleArr41, 3);
        Lit76 = syntaxRules24;
        new SimpleSymbol("test-on-test-begin-simple");
        Lit74 = (SimpleSymbol) simpleSymbol83.readResolve();
        SyntaxRules syntaxRules25 = syntaxRules12;
        Object[] objArr216 = new Object[1];
        Object[] objArr217 = objArr216;
        Object[] objArr218 = objArr216;
        new SimpleSymbol("test-group-with-cleanup");
        SimpleSymbol simpleSymbol176 = (SimpleSymbol) simpleSymbol84.readResolve();
        Lit72 = simpleSymbol176;
        objArr218[0] = simpleSymbol176;
        SyntaxRule[] syntaxRuleArr43 = new SyntaxRule[3];
        SyntaxRule[] syntaxRuleArr44 = syntaxRuleArr43;
        SyntaxRule[] syntaxRuleArr45 = syntaxRuleArr43;
        SyntaxRule syntaxRule33 = syntaxRule15;
        SyntaxPattern syntaxPattern44 = syntaxPattern24;
        new SyntaxPattern("\f\u0018\f\u0007\f\u000f\f\u0017\b", new Object[0], 3);
        Object[] objArr219 = new Object[4];
        Object[] objArr220 = objArr219;
        Object[] objArr221 = objArr219;
        new SimpleSymbol("test-group");
        SimpleSymbol simpleSymbol177 = (SimpleSymbol) simpleSymbol85.readResolve();
        Lit70 = simpleSymbol177;
        objArr221[0] = simpleSymbol177;
        Object[] objArr222 = objArr220;
        objArr222[1] = Lit165;
        Object[] objArr223 = objArr222;
        objArr223[2] = PairWithPosition.make(Lit147, PairWithPosition.make(LList.Empty, PairWithPosition.make(Boolean.FALSE, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 1826831), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 1826828), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 1826820);
        Object[] objArr224 = objArr223;
        objArr224[3] = Lit147;
        new SyntaxRule(syntaxPattern44, "\u0001\u0001\u0001", "\u0011\u0018\u0004\t\u0003\b\u0011\u0018\f\u0011\u0018\u00149\u0011\u0018\u001c\t\u0010\b\u000b\b\u0011\u0018\u001c\t\u0010\b\u0013", objArr224, 0);
        syntaxRuleArr45[0] = syntaxRule33;
        SyntaxRule[] syntaxRuleArr46 = syntaxRuleArr44;
        SyntaxRule[] syntaxRuleArr47 = syntaxRuleArr46;
        SyntaxRule[] syntaxRuleArr48 = syntaxRuleArr46;
        SyntaxRule syntaxRule34 = syntaxRule16;
        SyntaxPattern syntaxPattern45 = syntaxPattern25;
        new SyntaxPattern("\f\u0018\f\u0007\f\u000f\b", new Object[0], 2);
        Object[] objArr225 = new Object[2];
        objArr225[0] = Lit72;
        Object[] objArr226 = objArr225;
        objArr226[1] = Boolean.FALSE;
        new SyntaxRule(syntaxPattern45, "\u0001\u0001", "\u0011\u0018\u0004\t\u0003\u0011\u0018\f\b\u000b", objArr226, 0);
        syntaxRuleArr48[1] = syntaxRule34;
        SyntaxRule[] syntaxRuleArr49 = syntaxRuleArr47;
        SyntaxRule[] syntaxRuleArr50 = syntaxRuleArr49;
        SyntaxRule[] syntaxRuleArr51 = syntaxRuleArr49;
        SyntaxRule syntaxRule35 = syntaxRule17;
        SyntaxPattern syntaxPattern46 = syntaxPattern26;
        new SyntaxPattern("\f\u0018\f\u0007\f\u000f\f\u0017\f\u001f#", new Object[0], 5);
        Object[] objArr227 = new Object[2];
        objArr227[0] = Lit72;
        Object[] objArr228 = objArr227;
        new SimpleSymbol("begin");
        objArr228[1] = (SimpleSymbol) simpleSymbol86.readResolve();
        new SyntaxRule(syntaxPattern46, "\u0001\u0001\u0001\u0001\u0000", "\u0011\u0018\u0004\t\u00039\u0011\u0018\f\t\u000b\b\u0013\t\u001b\"", objArr228, 0);
        syntaxRuleArr51[2] = syntaxRule35;
        new SyntaxRules(objArr217, syntaxRuleArr50, 5);
        Lit73 = syntaxRules25;
        SyntaxRules syntaxRules26 = syntaxRules13;
        Object[] objArr229 = new Object[1];
        Object[] objArr230 = objArr229;
        objArr229[0] = Lit70;
        SyntaxRule[] syntaxRuleArr52 = new SyntaxRule[1];
        SyntaxRule[] syntaxRuleArr53 = syntaxRuleArr52;
        SyntaxRule[] syntaxRuleArr54 = syntaxRuleArr52;
        SyntaxRule syntaxRule36 = syntaxRule18;
        SyntaxPattern syntaxPattern47 = syntaxPattern27;
        new SyntaxPattern("\f\u0018\f\u0007\u000b", new Object[0], 2);
        Object[] objArr231 = new Object[13];
        objArr231[0] = Lit144;
        Object[] objArr232 = objArr231;
        objArr232[1] = PairWithPosition.make(PairWithPosition.make(Lit149, PairWithPosition.make(PairWithPosition.make(Lit146, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 1769487), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 1769487), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 1769484), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 1769483);
        Object[] objArr233 = objArr232;
        objArr233[2] = Lit52;
        Object[] objArr234 = objArr233;
        objArr234[3] = Lit149;
        Object[] objArr235 = objArr234;
        new SimpleSymbol("list");
        objArr235[4] = (SimpleSymbol) simpleSymbol87.readResolve();
        Object[] objArr236 = objArr235;
        objArr236[5] = Lit145;
        Object[] objArr237 = objArr236;
        objArr237[6] = PairWithPosition.make(Lit15, PairWithPosition.make(Lit7, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 1777707), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 1777707);
        Object[] objArr238 = objArr237;
        objArr238[7] = Lit161;
        Object[] objArr239 = objArr238;
        Object[] objArr240 = objArr239;
        Object[] objArr241 = objArr239;
        new SimpleSymbol("%test-should-execute");
        SimpleSymbol simpleSymbol178 = (SimpleSymbol) simpleSymbol88.readResolve();
        Lit62 = simpleSymbol178;
        objArr241[8] = PairWithPosition.make(simpleSymbol178, PairWithPosition.make(Lit149, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 1781794), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 1781772);
        Object[] objArr242 = objArr240;
        objArr242[9] = Lit165;
        Object[] objArr243 = objArr242;
        objArr243[10] = Lit147;
        Object[] objArr244 = objArr243;
        new SimpleSymbol("test-begin");
        objArr244[11] = (SimpleSymbol) simpleSymbol89.readResolve();
        Object[] objArr245 = objArr244;
        objArr245[12] = Lit94;
        new SyntaxRule(syntaxPattern47, "\u0001\u0000", "\u0011\u0018\u0004\u0011\u0018\f\u0011\u0018\u0014\u0011\u0018\u001c\b\u0011\u0018$\b\u0011\u0018,\u0011\u00184\b\u0003\b\u0011\u0018<\u0011\u0018D\b\u0011\u0018LY\u0011\u0018T\t\u0010\b\u0011\u0018\\\b\u00031\u0011\u0018T\t\u0010\n\b\u0011\u0018T\t\u0010\b\u0011\u0018d\b\u0003", objArr245, 0);
        syntaxRuleArr54[0] = syntaxRule36;
        new SyntaxRules(objArr230, syntaxRuleArr53, 2);
        Lit71 = syntaxRules26;
        new SimpleSymbol("test-on-final-simple");
        Lit68 = (SimpleSymbol) simpleSymbol90.readResolve();
        new SimpleSymbol("test-on-bad-end-name-simple");
        Lit67 = (SimpleSymbol) simpleSymbol91.readResolve();
        new SimpleSymbol("test-on-bad-count-simple");
        Lit66 = (SimpleSymbol) simpleSymbol92.readResolve();
        new SimpleSymbol("test-on-group-end-simple");
        Lit65 = (SimpleSymbol) simpleSymbol93.readResolve();
        new SimpleSymbol("test-on-group-begin-simple");
        Lit64 = (SimpleSymbol) simpleSymbol94.readResolve();
        new SimpleSymbol("%test-begin");
        Lit63 = (SimpleSymbol) simpleSymbol95.readResolve();
        new SimpleSymbol("test-runner-create");
        Lit61 = (SimpleSymbol) simpleSymbol96.readResolve();
        new SimpleSymbol("test-runner-simple");
        Lit59 = (SimpleSymbol) simpleSymbol97.readResolve();
        new SimpleSymbol("test-runner-null");
        Lit58 = (SimpleSymbol) simpleSymbol98.readResolve();
        new SimpleSymbol("%test-null-callback");
        Lit57 = (SimpleSymbol) simpleSymbol99.readResolve();
        new SimpleSymbol("test-runner-group-path");
        Lit56 = (SimpleSymbol) simpleSymbol100.readResolve();
        new SimpleSymbol("test-runner-reset");
        Lit55 = (SimpleSymbol) simpleSymbol101.readResolve();
        new SimpleSymbol("test-runner-aux-value!");
        Lit54 = (SimpleSymbol) simpleSymbol102.readResolve();
        new SimpleSymbol("test-runner-aux-value");
        Lit53 = (SimpleSymbol) simpleSymbol103.readResolve();
        new SimpleSymbol("test-runner-on-bad-end-name!");
        Lit50 = (SimpleSymbol) simpleSymbol104.readResolve();
        new SimpleSymbol("test-runner-on-bad-end-name");
        Lit49 = (SimpleSymbol) simpleSymbol105.readResolve();
        new SimpleSymbol("test-runner-on-bad-count!");
        Lit48 = (SimpleSymbol) simpleSymbol106.readResolve();
        new SimpleSymbol("test-runner-on-bad-count");
        Lit47 = (SimpleSymbol) simpleSymbol107.readResolve();
        new SimpleSymbol("test-runner-on-final!");
        Lit46 = (SimpleSymbol) simpleSymbol108.readResolve();
        new SimpleSymbol("test-runner-on-final");
        Lit45 = (SimpleSymbol) simpleSymbol109.readResolve();
        new SimpleSymbol("test-runner-on-group-end!");
        Lit44 = (SimpleSymbol) simpleSymbol110.readResolve();
        new SimpleSymbol("test-runner-on-group-end");
        Lit43 = (SimpleSymbol) simpleSymbol111.readResolve();
        new SimpleSymbol("test-runner-on-group-begin!");
        Lit42 = (SimpleSymbol) simpleSymbol112.readResolve();
        new SimpleSymbol("test-runner-on-group-begin");
        Lit41 = (SimpleSymbol) simpleSymbol113.readResolve();
        new SimpleSymbol("test-runner-on-test-end!");
        Lit40 = (SimpleSymbol) simpleSymbol114.readResolve();
        new SimpleSymbol("test-runner-on-test-end");
        Lit39 = (SimpleSymbol) simpleSymbol115.readResolve();
        new SimpleSymbol("test-runner-on-test-begin!");
        Lit38 = (SimpleSymbol) simpleSymbol116.readResolve();
        new SimpleSymbol("test-runner-on-test-begin");
        Lit37 = (SimpleSymbol) simpleSymbol117.readResolve();
        new SimpleSymbol("test-runner-group-stack!");
        Lit36 = (SimpleSymbol) simpleSymbol118.readResolve();
        new SimpleSymbol("test-runner-group-stack");
        Lit35 = (SimpleSymbol) simpleSymbol119.readResolve();
        new SimpleSymbol("test-runner-skip-count!");
        Lit30 = (SimpleSymbol) simpleSymbol120.readResolve();
        new SimpleSymbol("test-runner-skip-count");
        Lit29 = (SimpleSymbol) simpleSymbol121.readResolve();
        new SimpleSymbol("test-runner-xfail-count!");
        Lit28 = (SimpleSymbol) simpleSymbol122.readResolve();
        new SimpleSymbol("test-runner-xfail-count");
        Lit27 = (SimpleSymbol) simpleSymbol123.readResolve();
        new SimpleSymbol("test-runner-xpass-count!");
        Lit26 = (SimpleSymbol) simpleSymbol124.readResolve();
        new SimpleSymbol("test-runner-xpass-count");
        Lit25 = (SimpleSymbol) simpleSymbol125.readResolve();
        new SimpleSymbol("test-runner-fail-count!");
        Lit24 = (SimpleSymbol) simpleSymbol126.readResolve();
        new SimpleSymbol("test-runner-fail-count");
        Lit23 = (SimpleSymbol) simpleSymbol127.readResolve();
        new SimpleSymbol("test-runner-pass-count!");
        Lit22 = (SimpleSymbol) simpleSymbol128.readResolve();
        new SimpleSymbol("test-runner-pass-count");
        Lit21 = (SimpleSymbol) simpleSymbol129.readResolve();
        new SimpleSymbol("test-runner?");
        Lit20 = (SimpleSymbol) simpleSymbol130.readResolve();
        SyntaxTemplate syntaxTemplate27 = syntaxTemplate13;
        Object[] objArr246 = new Object[5];
        objArr246[0] = Lit150;
        Object[] objArr247 = objArr246;
        objArr247[1] = PairWithPosition.make(PairWithPosition.make(Lit149, PairWithPosition.make(PairWithPosition.make(Lit60, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2834444), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2834444), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2834441), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2834440);
        Object[] objArr248 = objArr247;
        objArr248[2] = Lit52;
        Object[] objArr249 = objArr248;
        objArr249[3] = Lit149;
        Object[] objArr250 = objArr249;
        objArr250[4] = Lit89;
        new SyntaxTemplate("\u0001\u0001\u0001\u0001\u0001", "\u0011\u0018\u0004\u0011\u0018\fA\u0011\u0018\u0014\u0011\u0018\u001c\b\u001b\b\u0011\u0018$\u0011\u0018\u001c\t#\t\u000b\b\u0013", objArr250, 0);
        Lit19 = syntaxTemplate27;
        new SyntaxPattern("<\f\u0007\f\u000f\f\u0017\b\f\u001f\f'\b", new Object[0], 5);
        Lit18 = syntaxPattern28;
        SyntaxTemplate syntaxTemplate28 = syntaxTemplate14;
        Object[] objArr251 = new Object[8];
        objArr251[0] = Lit150;
        Object[] objArr252 = objArr251;
        objArr252[1] = PairWithPosition.make(Lit149, PairWithPosition.make(PairWithPosition.make(Lit60, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2809868), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2809868), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2809865);
        Object[] objArr253 = objArr252;
        objArr253[2] = Lit160;
        Object[] objArr254 = objArr253;
        objArr254[3] = Lit52;
        Object[] objArr255 = objArr254;
        objArr255[4] = Lit149;
        Object[] objArr256 = objArr255;
        objArr256[5] = Lit145;
        Object[] objArr257 = objArr256;
        objArr257[6] = PairWithPosition.make(Lit15, PairWithPosition.make(Lit7, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2818087), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2818087);
        Object[] objArr258 = objArr257;
        objArr258[7] = Lit89;
        new SyntaxTemplate("\u0001\u0001\u0001\u0001\u0001\u0001", "\u0011\u0018\u0004I\u0011\u0018\f\b\u0011\u0018\u0014\b\u000b©\u0011\u0018\u001c\u0011\u0018$\b\u0011\u0018,A\u0011\u0018,\u0011\u00184\b\u000b\b#\b\u0011\u0018<\u0011\u0018$\t+\t\u0013\b\u001b", objArr258, 0);
        Lit17 = syntaxTemplate28;
        new SyntaxPattern("L\f\u0007\f\u000f\f\u0017\f\u001f\b\f'\f/\b", new Object[0], 6);
        Lit16 = syntaxPattern29;
        new SimpleSymbol("fail");
        Lit14 = (SimpleSymbol) simpleSymbol131.readResolve();
        new SimpleSymbol("pass");
        Lit12 = (SimpleSymbol) simpleSymbol132.readResolve();
        SimpleSymbol simpleSymbol179 = Lit12;
        new SimpleSymbol("xpass");
        SimpleSymbol simpleSymbol180 = (SimpleSymbol) simpleSymbol133.readResolve();
        Lit9 = simpleSymbol180;
        Lit11 = PairWithPosition.make(simpleSymbol179, PairWithPosition.make(simpleSymbol180, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2220088), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2220082);
        SimpleSymbol simpleSymbol181 = Lit7;
        new SimpleSymbol("source-file");
        SimpleSymbol simpleSymbol182 = (SimpleSymbol) simpleSymbol134.readResolve();
        SimpleSymbol simpleSymbol183 = simpleSymbol182;
        Lit4 = simpleSymbol182;
        new SimpleSymbol("source-line");
        SimpleSymbol simpleSymbol184 = (SimpleSymbol) simpleSymbol135.readResolve();
        SimpleSymbol simpleSymbol185 = simpleSymbol184;
        Lit5 = simpleSymbol184;
        new SimpleSymbol("source-form");
        SimpleSymbol simpleSymbol186 = (SimpleSymbol) simpleSymbol136.readResolve();
        Lit6 = simpleSymbol186;
        Lit10 = PairWithPosition.make(simpleSymbol181, PairWithPosition.make(simpleSymbol183, PairWithPosition.make(simpleSymbol185, PairWithPosition.make(simpleSymbol186, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2072618), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2072606), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2072594), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm", 2072583);
        new SimpleSymbol("xfail");
        Lit3 = (SimpleSymbol) simpleSymbol137.readResolve();
        new SimpleSymbol("skip");
        Lit2 = (SimpleSymbol) simpleSymbol138.readResolve();
        new SimpleSymbol("result-kind");
        Lit1 = (SimpleSymbol) simpleSymbol139.readResolve();
        new testing();
        $instance = testing;
        testing testing2 = $instance;
        new ModuleMethod(testing2, 12, Lit20, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        test$Mnrunner$Qu = moduleMethod;
        new ModuleMethod(testing2, 13, Lit21, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        test$Mnrunner$Mnpass$Mncount = moduleMethod2;
        new ModuleMethod(testing2, 14, Lit22, 8194);
        test$Mnrunner$Mnpass$Mncount$Ex = moduleMethod3;
        new ModuleMethod(testing2, 15, Lit23, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        test$Mnrunner$Mnfail$Mncount = moduleMethod4;
        new ModuleMethod(testing2, 16, Lit24, 8194);
        test$Mnrunner$Mnfail$Mncount$Ex = moduleMethod5;
        new ModuleMethod(testing2, 17, Lit25, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        test$Mnrunner$Mnxpass$Mncount = moduleMethod6;
        new ModuleMethod(testing2, 18, Lit26, 8194);
        test$Mnrunner$Mnxpass$Mncount$Ex = moduleMethod7;
        new ModuleMethod(testing2, 19, Lit27, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        test$Mnrunner$Mnxfail$Mncount = moduleMethod8;
        new ModuleMethod(testing2, 20, Lit28, 8194);
        test$Mnrunner$Mnxfail$Mncount$Ex = moduleMethod9;
        new ModuleMethod(testing2, 21, Lit29, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        test$Mnrunner$Mnskip$Mncount = moduleMethod10;
        new ModuleMethod(testing2, 22, Lit30, 8194);
        test$Mnrunner$Mnskip$Mncount$Ex = moduleMethod11;
        new ModuleMethod(testing2, 23, Lit31, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        $Prvt$$Pctest$Mnrunner$Mnskip$Mnlist = moduleMethod12;
        new ModuleMethod(testing2, 24, Lit32, 8194);
        $Prvt$$Pctest$Mnrunner$Mnskip$Mnlist$Ex = moduleMethod13;
        new ModuleMethod(testing2, 25, Lit33, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        $Prvt$$Pctest$Mnrunner$Mnfail$Mnlist = moduleMethod14;
        new ModuleMethod(testing2, 26, Lit34, 8194);
        $Prvt$$Pctest$Mnrunner$Mnfail$Mnlist$Ex = moduleMethod15;
        new ModuleMethod(testing2, 27, Lit35, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        test$Mnrunner$Mngroup$Mnstack = moduleMethod16;
        new ModuleMethod(testing2, 28, Lit36, 8194);
        test$Mnrunner$Mngroup$Mnstack$Ex = moduleMethod17;
        new ModuleMethod(testing2, 29, Lit37, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        test$Mnrunner$Mnon$Mntest$Mnbegin = moduleMethod18;
        new ModuleMethod(testing2, 30, Lit38, 8194);
        test$Mnrunner$Mnon$Mntest$Mnbegin$Ex = moduleMethod19;
        new ModuleMethod(testing2, 31, Lit39, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        test$Mnrunner$Mnon$Mntest$Mnend = moduleMethod20;
        new ModuleMethod(testing2, 32, Lit40, 8194);
        test$Mnrunner$Mnon$Mntest$Mnend$Ex = moduleMethod21;
        new ModuleMethod(testing2, 33, Lit41, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        test$Mnrunner$Mnon$Mngroup$Mnbegin = moduleMethod22;
        new ModuleMethod(testing2, 34, Lit42, 8194);
        test$Mnrunner$Mnon$Mngroup$Mnbegin$Ex = moduleMethod23;
        new ModuleMethod(testing2, 35, Lit43, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        test$Mnrunner$Mnon$Mngroup$Mnend = moduleMethod24;
        new ModuleMethod(testing2, 36, Lit44, 8194);
        test$Mnrunner$Mnon$Mngroup$Mnend$Ex = moduleMethod25;
        new ModuleMethod(testing2, 37, Lit45, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        test$Mnrunner$Mnon$Mnfinal = moduleMethod26;
        new ModuleMethod(testing2, 38, Lit46, 8194);
        test$Mnrunner$Mnon$Mnfinal$Ex = moduleMethod27;
        new ModuleMethod(testing2, 39, Lit47, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        test$Mnrunner$Mnon$Mnbad$Mncount = moduleMethod28;
        new ModuleMethod(testing2, 40, Lit48, 8194);
        test$Mnrunner$Mnon$Mnbad$Mncount$Ex = moduleMethod29;
        new ModuleMethod(testing2, 41, Lit49, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        test$Mnrunner$Mnon$Mnbad$Mnend$Mnname = moduleMethod30;
        new ModuleMethod(testing2, 42, Lit50, 8194);
        test$Mnrunner$Mnon$Mnbad$Mnend$Mnname$Ex = moduleMethod31;
        new ModuleMethod(testing2, 43, Lit51, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        test$Mnresult$Mnalist = moduleMethod32;
        new ModuleMethod(testing2, 44, Lit52, 8194);
        test$Mnresult$Mnalist$Ex = moduleMethod33;
        new ModuleMethod(testing2, 45, Lit53, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        test$Mnrunner$Mnaux$Mnvalue = moduleMethod34;
        new ModuleMethod(testing2, 46, Lit54, 8194);
        test$Mnrunner$Mnaux$Mnvalue$Ex = moduleMethod35;
        new ModuleMethod(testing2, 47, Lit55, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        test$Mnrunner$Mnreset = moduleMethod36;
        new ModuleMethod(testing2, 48, Lit56, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        test$Mnrunner$Mngroup$Mnpath = moduleMethod37;
        new ModuleMethod(testing2, 49, Lit57, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        $Pctest$Mnnull$Mncallback = moduleMethod38;
        new ModuleMethod(testing2, 50, (Object) null, 12291);
        ModuleMethod moduleMethod73 = moduleMethod39;
        moduleMethod73.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm:182");
        lambda$Fn1 = moduleMethod73;
        new ModuleMethod(testing2, 51, (Object) null, 12291);
        ModuleMethod moduleMethod74 = moduleMethod40;
        moduleMethod74.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm:187");
        lambda$Fn2 = moduleMethod74;
        new ModuleMethod(testing2, 52, (Object) null, 12291);
        ModuleMethod moduleMethod75 = moduleMethod41;
        moduleMethod75.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm:188");
        lambda$Fn3 = moduleMethod75;
        new ModuleMethod(testing2, 53, Lit58, 0);
        test$Mnrunner$Mnnull = moduleMethod42;
        new ModuleMethod(testing2, 54, Lit59, 0);
        test$Mnrunner$Mnsimple = moduleMethod43;
        new ModuleMethod(testing2, 55, Lit60, 0);
        test$Mnrunner$Mnget = moduleMethod44;
        new ModuleMethod(testing2, 56, Lit61, 0);
        test$Mnrunner$Mncreate = moduleMethod45;
        new ModuleMethod(testing2, 57, Lit62, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        $Prvt$$Pctest$Mnshould$Mnexecute = moduleMethod46;
        new ModuleMethod(testing2, 58, Lit63, 8194);
        $Pctest$Mnbegin = moduleMethod47;
        new ModuleMethod(testing2, 59, Lit64, 12291);
        test$Mnon$Mngroup$Mnbegin$Mnsimple = moduleMethod48;
        new ModuleMethod(testing2, 60, Lit65, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        test$Mnon$Mngroup$Mnend$Mnsimple = moduleMethod49;
        new ModuleMethod(testing2, 61, Lit66, 12291);
        test$Mnon$Mnbad$Mncount$Mnsimple = moduleMethod50;
        new ModuleMethod(testing2, 62, Lit67, 12291);
        test$Mnon$Mnbad$Mnend$Mnname$Mnsimple = moduleMethod51;
        new ModuleMethod(testing2, 63, Lit68, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        test$Mnon$Mnfinal$Mnsimple = moduleMethod52;
        new ModuleMethod(testing2, 64, Lit69, 8194);
        $Prvt$$Pctest$Mnend = moduleMethod53;
        new ModuleMethod(testing2, 65, Lit74, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        test$Mnon$Mntest$Mnbegin$Mnsimple = moduleMethod54;
        new ModuleMethod(testing2, 66, Lit77, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        test$Mnon$Mntest$Mnend$Mnsimple = moduleMethod55;
        new ModuleMethod(testing2, 67, Lit78, 12291);
        test$Mnresult$Mnset$Ex = moduleMethod56;
        new ModuleMethod(testing2, 68, Lit79, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        test$Mnresult$Mnclear = moduleMethod57;
        new ModuleMethod(testing2, 69, Lit80, 8194);
        test$Mnresult$Mnremove = moduleMethod58;
        new ModuleMethod(testing2, 70, Lit81, -4096);
        test$Mnresult$Mnkind = moduleMethod59;
        new ModuleMethod(testing2, 71, Lit82, -4096);
        test$Mnpassed$Qu = moduleMethod60;
        new ModuleMethod(testing2, 72, Lit83, 0);
        $Prvt$$Pctest$Mnreport$Mnresult = moduleMethod61;
        new ModuleMethod(testing2, 73, Lit86, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        $Prvt$$Pctest$Mnon$Mntest$Mnbegin = moduleMethod62;
        new ModuleMethod(testing2, 74, Lit87, 8194);
        $Prvt$$Pctest$Mnon$Mntest$Mnend = moduleMethod63;
        new ModuleMethod(testing2, 75, Lit88, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        test$Mnrunner$Mntest$Mnname = moduleMethod64;
        new ModuleMethod(testing2, 76, Lit91, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        $Prvt$$Pctest$Mnapproximimate$Eq = moduleMethod65;
        SimpleSymbol simpleSymbol187 = Lit94;
        new ModuleMethod(testing2, 77, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        Procedure procedure8 = procedure;
        procedure8.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm:660");
        test$Mnend = Macro.make(simpleSymbol187, procedure8, $instance);
        SimpleSymbol simpleSymbol188 = Lit99;
        new ModuleMethod(testing2, 78, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        Procedure procedure9 = procedure2;
        procedure9.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm:669");
        test$Mnassert = Macro.make(simpleSymbol188, procedure9, $instance);
        SimpleSymbol simpleSymbol189 = Lit104;
        new ModuleMethod(testing2, 79, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        Procedure procedure10 = procedure3;
        procedure10.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm:696");
        test$Mneqv = Macro.make(simpleSymbol189, procedure10, $instance);
        SimpleSymbol simpleSymbol190 = Lit106;
        new ModuleMethod(testing2, 80, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        Procedure procedure11 = procedure4;
        procedure11.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm:698");
        test$Mneq = Macro.make(simpleSymbol190, procedure11, $instance);
        SimpleSymbol simpleSymbol191 = Lit108;
        new ModuleMethod(testing2, 81, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        Procedure procedure12 = procedure5;
        procedure12.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm:700");
        test$Mnequal = Macro.make(simpleSymbol191, procedure12, $instance);
        SimpleSymbol simpleSymbol192 = Lit110;
        new ModuleMethod(testing2, 82, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        Procedure procedure13 = procedure6;
        procedure13.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm:702");
        test$Mnapproximate = Macro.make(simpleSymbol192, procedure13, $instance);
        SimpleSymbol simpleSymbol193 = Lit117;
        new ModuleMethod(testing2, 83, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        Procedure procedure14 = procedure7;
        procedure14.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm:843");
        test$Mnerror = Macro.make(simpleSymbol193, procedure14, $instance);
        new ModuleMethod(testing2, 84, Lit124, -4095);
        test$Mnapply = moduleMethod66;
        new ModuleMethod(testing2, 85, Lit127, 8194);
        $Prvt$$Pctest$Mnmatch$Mnnth = moduleMethod67;
        new ModuleMethod(testing2, 86, Lit130, -4096);
        $Prvt$$Pctest$Mnmatch$Mnall = moduleMethod68;
        new ModuleMethod(testing2, 87, Lit133, -4096);
        $Prvt$$Pctest$Mnmatch$Mnany = moduleMethod69;
        new ModuleMethod(testing2, 88, Lit136, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        $Prvt$$Pctest$Mnas$Mnspecifier = moduleMethod70;
        new ModuleMethod(testing2, 89, Lit141, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        test$Mnmatch$Mnname = moduleMethod71;
        new ModuleMethod(testing2, 90, Lit142, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        test$Mnread$Mneval$Mnstring = moduleMethod72;
        $instance.run();
    }

    static test$Mnrunner $PcTestRunnerAlloc() {
        test$Mnrunner test_mnrunner;
        test$Mnrunner test_mnrunner2 = test_mnrunner;
        new test$Mnrunner();
        return test_mnrunner2;
    }

    public static boolean isTestRunner(Object obj) {
        return obj instanceof test$Mnrunner;
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 12:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 13:
                CallContext callContext3 = callContext2;
                Object obj3 = obj2;
                Object obj4 = obj3;
                if (!(obj3 instanceof test$Mnrunner)) {
                    return -786431;
                }
                callContext3.value1 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 15:
                CallContext callContext4 = callContext2;
                Object obj5 = obj2;
                Object obj6 = obj5;
                if (!(obj5 instanceof test$Mnrunner)) {
                    return -786431;
                }
                callContext4.value1 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 17:
                CallContext callContext5 = callContext2;
                Object obj7 = obj2;
                Object obj8 = obj7;
                if (!(obj7 instanceof test$Mnrunner)) {
                    return -786431;
                }
                callContext5.value1 = obj8;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 19:
                CallContext callContext6 = callContext2;
                Object obj9 = obj2;
                Object obj10 = obj9;
                if (!(obj9 instanceof test$Mnrunner)) {
                    return -786431;
                }
                callContext6.value1 = obj10;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 21:
                CallContext callContext7 = callContext2;
                Object obj11 = obj2;
                Object obj12 = obj11;
                if (!(obj11 instanceof test$Mnrunner)) {
                    return -786431;
                }
                callContext7.value1 = obj12;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 23:
                CallContext callContext8 = callContext2;
                Object obj13 = obj2;
                Object obj14 = obj13;
                if (!(obj13 instanceof test$Mnrunner)) {
                    return -786431;
                }
                callContext8.value1 = obj14;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 25:
                CallContext callContext9 = callContext2;
                Object obj15 = obj2;
                Object obj16 = obj15;
                if (!(obj15 instanceof test$Mnrunner)) {
                    return -786431;
                }
                callContext9.value1 = obj16;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 27:
                CallContext callContext10 = callContext2;
                Object obj17 = obj2;
                Object obj18 = obj17;
                if (!(obj17 instanceof test$Mnrunner)) {
                    return -786431;
                }
                callContext10.value1 = obj18;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 29:
                CallContext callContext11 = callContext2;
                Object obj19 = obj2;
                Object obj20 = obj19;
                if (!(obj19 instanceof test$Mnrunner)) {
                    return -786431;
                }
                callContext11.value1 = obj20;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 31:
                CallContext callContext12 = callContext2;
                Object obj21 = obj2;
                Object obj22 = obj21;
                if (!(obj21 instanceof test$Mnrunner)) {
                    return -786431;
                }
                callContext12.value1 = obj22;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 33:
                CallContext callContext13 = callContext2;
                Object obj23 = obj2;
                Object obj24 = obj23;
                if (!(obj23 instanceof test$Mnrunner)) {
                    return -786431;
                }
                callContext13.value1 = obj24;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 35:
                CallContext callContext14 = callContext2;
                Object obj25 = obj2;
                Object obj26 = obj25;
                if (!(obj25 instanceof test$Mnrunner)) {
                    return -786431;
                }
                callContext14.value1 = obj26;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 37:
                CallContext callContext15 = callContext2;
                Object obj27 = obj2;
                Object obj28 = obj27;
                if (!(obj27 instanceof test$Mnrunner)) {
                    return -786431;
                }
                callContext15.value1 = obj28;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 39:
                CallContext callContext16 = callContext2;
                Object obj29 = obj2;
                Object obj30 = obj29;
                if (!(obj29 instanceof test$Mnrunner)) {
                    return -786431;
                }
                callContext16.value1 = obj30;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 41:
                CallContext callContext17 = callContext2;
                Object obj31 = obj2;
                Object obj32 = obj31;
                if (!(obj31 instanceof test$Mnrunner)) {
                    return -786431;
                }
                callContext17.value1 = obj32;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 43:
                CallContext callContext18 = callContext2;
                Object obj33 = obj2;
                Object obj34 = obj33;
                if (!(obj33 instanceof test$Mnrunner)) {
                    return -786431;
                }
                callContext18.value1 = obj34;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 45:
                CallContext callContext19 = callContext2;
                Object obj35 = obj2;
                Object obj36 = obj35;
                if (!(obj35 instanceof test$Mnrunner)) {
                    return -786431;
                }
                callContext19.value1 = obj36;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 47:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 48:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 49:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 57:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 60:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 63:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 65:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 66:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 68:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 73:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 75:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 76:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 77:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 78:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 79:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 80:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 81:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 82:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 83:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 88:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 89:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 90:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            default:
                return super.match1(moduleMethod2, obj2, callContext2);
        }
    }

    public static Object testRunnerPassCount(test$Mnrunner obj) {
        return obj.pass$Mncount;
    }

    public static void testRunnerPassCount$Ex(test$Mnrunner obj, Object value) {
        obj.pass$Mncount = value;
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 14:
                CallContext callContext3 = callContext2;
                Object obj5 = obj3;
                Object obj6 = obj5;
                if (!(obj5 instanceof test$Mnrunner)) {
                    return -786431;
                }
                callContext3.value1 = obj6;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 16:
                CallContext callContext4 = callContext2;
                Object obj7 = obj3;
                Object obj8 = obj7;
                if (!(obj7 instanceof test$Mnrunner)) {
                    return -786431;
                }
                callContext4.value1 = obj8;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 18:
                CallContext callContext5 = callContext2;
                Object obj9 = obj3;
                Object obj10 = obj9;
                if (!(obj9 instanceof test$Mnrunner)) {
                    return -786431;
                }
                callContext5.value1 = obj10;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 20:
                CallContext callContext6 = callContext2;
                Object obj11 = obj3;
                Object obj12 = obj11;
                if (!(obj11 instanceof test$Mnrunner)) {
                    return -786431;
                }
                callContext6.value1 = obj12;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 22:
                CallContext callContext7 = callContext2;
                Object obj13 = obj3;
                Object obj14 = obj13;
                if (!(obj13 instanceof test$Mnrunner)) {
                    return -786431;
                }
                callContext7.value1 = obj14;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 24:
                CallContext callContext8 = callContext2;
                Object obj15 = obj3;
                Object obj16 = obj15;
                if (!(obj15 instanceof test$Mnrunner)) {
                    return -786431;
                }
                callContext8.value1 = obj16;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 26:
                CallContext callContext9 = callContext2;
                Object obj17 = obj3;
                Object obj18 = obj17;
                if (!(obj17 instanceof test$Mnrunner)) {
                    return -786431;
                }
                callContext9.value1 = obj18;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 28:
                CallContext callContext10 = callContext2;
                Object obj19 = obj3;
                Object obj20 = obj19;
                if (!(obj19 instanceof test$Mnrunner)) {
                    return -786431;
                }
                callContext10.value1 = obj20;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 30:
                CallContext callContext11 = callContext2;
                Object obj21 = obj3;
                Object obj22 = obj21;
                if (!(obj21 instanceof test$Mnrunner)) {
                    return -786431;
                }
                callContext11.value1 = obj22;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 32:
                CallContext callContext12 = callContext2;
                Object obj23 = obj3;
                Object obj24 = obj23;
                if (!(obj23 instanceof test$Mnrunner)) {
                    return -786431;
                }
                callContext12.value1 = obj24;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 34:
                CallContext callContext13 = callContext2;
                Object obj25 = obj3;
                Object obj26 = obj25;
                if (!(obj25 instanceof test$Mnrunner)) {
                    return -786431;
                }
                callContext13.value1 = obj26;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 36:
                CallContext callContext14 = callContext2;
                Object obj27 = obj3;
                Object obj28 = obj27;
                if (!(obj27 instanceof test$Mnrunner)) {
                    return -786431;
                }
                callContext14.value1 = obj28;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 38:
                CallContext callContext15 = callContext2;
                Object obj29 = obj3;
                Object obj30 = obj29;
                if (!(obj29 instanceof test$Mnrunner)) {
                    return -786431;
                }
                callContext15.value1 = obj30;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 40:
                CallContext callContext16 = callContext2;
                Object obj31 = obj3;
                Object obj32 = obj31;
                if (!(obj31 instanceof test$Mnrunner)) {
                    return -786431;
                }
                callContext16.value1 = obj32;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 42:
                CallContext callContext17 = callContext2;
                Object obj33 = obj3;
                Object obj34 = obj33;
                if (!(obj33 instanceof test$Mnrunner)) {
                    return -786431;
                }
                callContext17.value1 = obj34;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 44:
                CallContext callContext18 = callContext2;
                Object obj35 = obj3;
                Object obj36 = obj35;
                if (!(obj35 instanceof test$Mnrunner)) {
                    return -786431;
                }
                callContext18.value1 = obj36;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 46:
                CallContext callContext19 = callContext2;
                Object obj37 = obj3;
                Object obj38 = obj37;
                if (!(obj37 instanceof test$Mnrunner)) {
                    return -786431;
                }
                callContext19.value1 = obj38;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 58:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 64:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 69:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 74:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 85:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            default:
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
        }
    }

    public static Object testRunnerFailCount(test$Mnrunner obj) {
        return obj.fail$Mncount;
    }

    public static void testRunnerFailCount$Ex(test$Mnrunner obj, Object value) {
        obj.fail$Mncount = value;
    }

    public static Object testRunnerXpassCount(test$Mnrunner obj) {
        return obj.xpass$Mncount;
    }

    public static void testRunnerXpassCount$Ex(test$Mnrunner obj, Object value) {
        obj.xpass$Mncount = value;
    }

    public static Object testRunnerXfailCount(test$Mnrunner obj) {
        return obj.xfail$Mncount;
    }

    public static void testRunnerXfailCount$Ex(test$Mnrunner obj, Object value) {
        obj.xfail$Mncount = value;
    }

    public static Object testRunnerSkipCount(test$Mnrunner obj) {
        return obj.skip$Mncount;
    }

    public static void testRunnerSkipCount$Ex(test$Mnrunner obj, Object value) {
        obj.skip$Mncount = value;
    }

    public static Object $PcTestRunnerSkipList(test$Mnrunner obj) {
        return obj.skip$Mnlist;
    }

    public static void $PcTestRunnerSkipList$Ex(test$Mnrunner obj, Object value) {
        obj.skip$Mnlist = value;
    }

    public static Object $PcTestRunnerFailList(test$Mnrunner obj) {
        return obj.fail$Mnlist;
    }

    public static void $PcTestRunnerFailList$Ex(test$Mnrunner obj, Object value) {
        obj.fail$Mnlist = value;
    }

    static Object $PcTestRunnerRunList(test$Mnrunner obj) {
        return obj.run$Mnlist;
    }

    static void $PcTestRunnerRunList$Ex(test$Mnrunner obj, Object value) {
        obj.run$Mnlist = value;
    }

    static Object $PcTestRunnerSkipSave(test$Mnrunner obj) {
        return obj.skip$Mnsave;
    }

    static void $PcTestRunnerSkipSave$Ex(test$Mnrunner obj, Object value) {
        obj.skip$Mnsave = value;
    }

    static Object $PcTestRunnerFailSave(test$Mnrunner obj) {
        return obj.fail$Mnsave;
    }

    static void $PcTestRunnerFailSave$Ex(test$Mnrunner obj, Object value) {
        obj.fail$Mnsave = value;
    }

    public static Object testRunnerGroupStack(test$Mnrunner obj) {
        return obj.group$Mnstack;
    }

    public static void testRunnerGroupStack$Ex(test$Mnrunner obj, Object value) {
        obj.group$Mnstack = value;
    }

    public static Object testRunnerOnTestBegin(test$Mnrunner obj) {
        return obj.on$Mntest$Mnbegin;
    }

    public static void testRunnerOnTestBegin$Ex(test$Mnrunner obj, Object value) {
        obj.on$Mntest$Mnbegin = value;
    }

    public static Object testRunnerOnTestEnd(test$Mnrunner obj) {
        return obj.on$Mntest$Mnend;
    }

    public static void testRunnerOnTestEnd$Ex(test$Mnrunner obj, Object value) {
        obj.on$Mntest$Mnend = value;
    }

    public static Object testRunnerOnGroupBegin(test$Mnrunner obj) {
        return obj.on$Mngroup$Mnbegin;
    }

    public static void testRunnerOnGroupBegin$Ex(test$Mnrunner obj, Object value) {
        obj.on$Mngroup$Mnbegin = value;
    }

    public static Object testRunnerOnGroupEnd(test$Mnrunner obj) {
        return obj.on$Mngroup$Mnend;
    }

    public static void testRunnerOnGroupEnd$Ex(test$Mnrunner obj, Object value) {
        obj.on$Mngroup$Mnend = value;
    }

    public static Object testRunnerOnFinal(test$Mnrunner obj) {
        return obj.on$Mnfinal;
    }

    public static void testRunnerOnFinal$Ex(test$Mnrunner obj, Object value) {
        obj.on$Mnfinal = value;
    }

    public static Object testRunnerOnBadCount(test$Mnrunner obj) {
        return obj.on$Mnbad$Mncount;
    }

    public static void testRunnerOnBadCount$Ex(test$Mnrunner obj, Object value) {
        obj.on$Mnbad$Mncount = value;
    }

    public static Object testRunnerOnBadEndName(test$Mnrunner obj) {
        return obj.on$Mnbad$Mnend$Mnname;
    }

    public static void testRunnerOnBadEndName$Ex(test$Mnrunner obj, Object value) {
        obj.on$Mnbad$Mnend$Mnname = value;
    }

    static Object $PcTestRunnerTotalCount(test$Mnrunner obj) {
        return obj.total$Mncount;
    }

    static void $PcTestRunnerTotalCount$Ex(test$Mnrunner obj, Object value) {
        obj.total$Mncount = value;
    }

    static Object $PcTestRunnerCountList(test$Mnrunner obj) {
        return obj.count$Mnlist;
    }

    static void $PcTestRunnerCountList$Ex(test$Mnrunner obj, Object value) {
        obj.count$Mnlist = value;
    }

    public static Object testResultAlist(test$Mnrunner obj) {
        return obj.result$Mnalist;
    }

    public static void testResultAlist$Ex(test$Mnrunner obj, Object value) {
        obj.result$Mnalist = value;
    }

    public static Object testRunnerAuxValue(test$Mnrunner obj) {
        return obj.aux$Mnvalue;
    }

    public static void testRunnerAuxValue$Ex(test$Mnrunner obj, Object value) {
        obj.aux$Mnvalue = value;
    }

    public static void testRunnerReset(Object obj) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        Throwable th7;
        Throwable th8;
        Throwable th9;
        Throwable th10;
        Throwable th11;
        Throwable th12;
        Throwable th13;
        Throwable th14;
        Object runner = obj;
        Object obj2 = runner;
        Object obj3 = obj2;
        try {
            testResultAlist$Ex((test$Mnrunner) obj2, LList.Empty);
            Object obj4 = runner;
            Object obj5 = obj4;
            try {
                testRunnerPassCount$Ex((test$Mnrunner) obj4, Lit0);
                Object obj6 = runner;
                Object obj7 = obj6;
                try {
                    testRunnerFailCount$Ex((test$Mnrunner) obj6, Lit0);
                    Object obj8 = runner;
                    Object obj9 = obj8;
                    try {
                        testRunnerXpassCount$Ex((test$Mnrunner) obj8, Lit0);
                        Object obj10 = runner;
                        Object obj11 = obj10;
                        try {
                            testRunnerXfailCount$Ex((test$Mnrunner) obj10, Lit0);
                            Object obj12 = runner;
                            Object obj13 = obj12;
                            try {
                                testRunnerSkipCount$Ex((test$Mnrunner) obj12, Lit0);
                                Object obj14 = runner;
                                Object obj15 = obj14;
                                try {
                                    $PcTestRunnerTotalCount$Ex((test$Mnrunner) obj14, Lit0);
                                    Object obj16 = runner;
                                    Object obj17 = obj16;
                                    try {
                                        $PcTestRunnerCountList$Ex((test$Mnrunner) obj16, LList.Empty);
                                        Object obj18 = runner;
                                        Object obj19 = obj18;
                                        try {
                                            $PcTestRunnerRunList$Ex((test$Mnrunner) obj18, Boolean.TRUE);
                                            Object obj20 = runner;
                                            Object obj21 = obj20;
                                            try {
                                                $PcTestRunnerSkipList$Ex((test$Mnrunner) obj20, LList.Empty);
                                                Object obj22 = runner;
                                                Object obj23 = obj22;
                                                try {
                                                    $PcTestRunnerFailList$Ex((test$Mnrunner) obj22, LList.Empty);
                                                    Object obj24 = runner;
                                                    Object obj25 = obj24;
                                                    try {
                                                        $PcTestRunnerSkipSave$Ex((test$Mnrunner) obj24, LList.Empty);
                                                        Object obj26 = runner;
                                                        Object obj27 = obj26;
                                                        try {
                                                            $PcTestRunnerFailSave$Ex((test$Mnrunner) obj26, LList.Empty);
                                                            Object obj28 = runner;
                                                            Object obj29 = obj28;
                                                            try {
                                                                testRunnerGroupStack$Ex((test$Mnrunner) obj28, LList.Empty);
                                                            } catch (ClassCastException e) {
                                                                ClassCastException classCastException = e;
                                                                Throwable th15 = th14;
                                                                new WrongType(classCastException, "test-runner-group-stack!", 0, obj29);
                                                                throw th15;
                                                            }
                                                        } catch (ClassCastException e2) {
                                                            ClassCastException classCastException2 = e2;
                                                            Throwable th16 = th13;
                                                            new WrongType(classCastException2, "%test-runner-fail-save!", 0, obj27);
                                                            throw th16;
                                                        }
                                                    } catch (ClassCastException e3) {
                                                        ClassCastException classCastException3 = e3;
                                                        Throwable th17 = th12;
                                                        new WrongType(classCastException3, "%test-runner-skip-save!", 0, obj25);
                                                        throw th17;
                                                    }
                                                } catch (ClassCastException e4) {
                                                    ClassCastException classCastException4 = e4;
                                                    Throwable th18 = th11;
                                                    new WrongType(classCastException4, "%test-runner-fail-list!", 0, obj23);
                                                    throw th18;
                                                }
                                            } catch (ClassCastException e5) {
                                                ClassCastException classCastException5 = e5;
                                                Throwable th19 = th10;
                                                new WrongType(classCastException5, "%test-runner-skip-list!", 0, obj21);
                                                throw th19;
                                            }
                                        } catch (ClassCastException e6) {
                                            ClassCastException classCastException6 = e6;
                                            Throwable th20 = th9;
                                            new WrongType(classCastException6, "%test-runner-run-list!", 0, obj19);
                                            throw th20;
                                        }
                                    } catch (ClassCastException e7) {
                                        ClassCastException classCastException7 = e7;
                                        Throwable th21 = th8;
                                        new WrongType(classCastException7, "%test-runner-count-list!", 0, obj17);
                                        throw th21;
                                    }
                                } catch (ClassCastException e8) {
                                    ClassCastException classCastException8 = e8;
                                    Throwable th22 = th7;
                                    new WrongType(classCastException8, "%test-runner-total-count!", 0, obj15);
                                    throw th22;
                                }
                            } catch (ClassCastException e9) {
                                ClassCastException classCastException9 = e9;
                                Throwable th23 = th6;
                                new WrongType(classCastException9, "test-runner-skip-count!", 0, obj13);
                                throw th23;
                            }
                        } catch (ClassCastException e10) {
                            ClassCastException classCastException10 = e10;
                            Throwable th24 = th5;
                            new WrongType(classCastException10, "test-runner-xfail-count!", 0, obj11);
                            throw th24;
                        }
                    } catch (ClassCastException e11) {
                        ClassCastException classCastException11 = e11;
                        Throwable th25 = th4;
                        new WrongType(classCastException11, "test-runner-xpass-count!", 0, obj9);
                        throw th25;
                    }
                } catch (ClassCastException e12) {
                    ClassCastException classCastException12 = e12;
                    Throwable th26 = th3;
                    new WrongType(classCastException12, "test-runner-fail-count!", 0, obj7);
                    throw th26;
                }
            } catch (ClassCastException e13) {
                ClassCastException classCastException13 = e13;
                Throwable th27 = th2;
                new WrongType(classCastException13, "test-runner-pass-count!", 0, obj5);
                throw th27;
            }
        } catch (ClassCastException e14) {
            ClassCastException classCastException14 = e14;
            Throwable th28 = th;
            new WrongType(classCastException14, "test-result-alist!", 0, obj3);
            throw th28;
        }
    }

    public static LList testRunnerGroupPath(Object runner) {
        Throwable th;
        Throwable th2;
        Object obj = runner;
        Object obj2 = obj;
        try {
            Object testRunnerGroupStack = testRunnerGroupStack((test$Mnrunner) obj);
            Object obj3 = testRunnerGroupStack;
            try {
                return C1245lists.reverse((LList) testRunnerGroupStack);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th3 = th2;
                new WrongType(classCastException, "reverse", 1, obj3);
                throw th3;
            }
        } catch (ClassCastException e2) {
            ClassCastException classCastException2 = e2;
            Throwable th4 = th;
            new WrongType(classCastException2, "test-runner-group-stack", 0, obj2);
            throw th4;
        }
    }

    static Boolean $PcTestNullCallback(Object obj) {
        Object obj2 = obj;
        return Boolean.FALSE;
    }

    public static test$Mnrunner testRunnerNull() {
        test$Mnrunner runner = $PcTestRunnerAlloc();
        testRunnerReset(runner);
        testRunnerOnGroupBegin$Ex(runner, lambda$Fn1);
        testRunnerOnGroupEnd$Ex(runner, $Pctest$Mnnull$Mncallback);
        testRunnerOnFinal$Ex(runner, $Pctest$Mnnull$Mncallback);
        testRunnerOnTestBegin$Ex(runner, $Pctest$Mnnull$Mncallback);
        testRunnerOnTestEnd$Ex(runner, $Pctest$Mnnull$Mncallback);
        testRunnerOnBadCount$Ex(runner, lambda$Fn2);
        testRunnerOnBadEndName$Ex(runner, lambda$Fn3);
        return runner;
    }

    public int match0(ModuleMethod moduleMethod, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 53:
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 0;
                return 0;
            case 54:
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 0;
                return 0;
            case 55:
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 0;
                return 0;
            case 56:
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 0;
                return 0;
            case 72:
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 0;
                return 0;
            default:
                return super.match0(moduleMethod2, callContext2);
        }
    }

    static Boolean lambda1(Object obj, Object obj2, Object obj3) {
        Object obj4 = obj;
        Object obj5 = obj2;
        Object obj6 = obj3;
        return Boolean.FALSE;
    }

    public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj4 = obj;
        Object obj5 = obj2;
        Object obj6 = obj3;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 50:
                callContext2.value1 = obj4;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 51:
                callContext2.value1 = obj4;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 52:
                callContext2.value1 = obj4;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 59:
                callContext2.value1 = obj4;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 61:
                callContext2.value1 = obj4;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 62:
                callContext2.value1 = obj4;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 67:
                callContext2.value1 = obj4;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            default:
                return super.match3(moduleMethod2, obj4, obj5, obj6, callContext2);
        }
    }

    static Boolean lambda2(Object obj, Object obj2, Object obj3) {
        Object obj4 = obj;
        Object obj5 = obj2;
        Object obj6 = obj3;
        return Boolean.FALSE;
    }

    static Boolean lambda3(Object obj, Object obj2, Object obj3) {
        Object obj4 = obj;
        Object obj5 = obj2;
        Object obj6 = obj3;
        return Boolean.FALSE;
    }

    public static test$Mnrunner testRunnerSimple() {
        test$Mnrunner runner = $PcTestRunnerAlloc();
        testRunnerReset(runner);
        testRunnerOnGroupBegin$Ex(runner, test$Mnon$Mngroup$Mnbegin$Mnsimple);
        testRunnerOnGroupEnd$Ex(runner, test$Mnon$Mngroup$Mnend$Mnsimple);
        testRunnerOnFinal$Ex(runner, test$Mnon$Mnfinal$Mnsimple);
        testRunnerOnTestBegin$Ex(runner, test$Mnon$Mntest$Mnbegin$Mnsimple);
        testRunnerOnTestEnd$Ex(runner, test$Mnon$Mntest$Mnend$Mnsimple);
        testRunnerOnBadCount$Ex(runner, test$Mnon$Mnbad$Mncount$Mnsimple);
        testRunnerOnBadEndName$Ex(runner, test$Mnon$Mnbad$Mnend$Mnname$Mnsimple);
        return runner;
    }

    public static Object testRunnerGet() {
        Object r = ((Procedure) test$Mnrunner$Mncurrent).apply0();
        if (r == Boolean.FALSE) {
            Object error$V = misc.error$V("test-runner not initialized - test-begin missing?", new Object[0]);
        }
        return r;
    }

    static Object $PcTestSpecificierMatches(Object spec, Object runner) {
        return Scheme.applyToArgs.apply2(spec, runner);
    }

    public static Object testRunnerCreate() {
        return Scheme.applyToArgs.apply1(((Procedure) test$Mnrunner$Mnfactory).apply0());
    }

    static Object $PcTestAnySpecifierMatches(Object list, Object obj) {
        Object runner = obj;
        Boolean result = Boolean.FALSE;
        Object obj2 = list;
        while (true) {
            Object l = obj2;
            if (C1245lists.isNull(l)) {
                return result;
            }
            if ($PcTestSpecificierMatches(C1245lists.car.apply1(l), runner) != Boolean.FALSE) {
                result = Boolean.TRUE;
            }
            obj2 = C1245lists.cdr.apply1(l);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0023, code lost:
        if (r2 != false) goto L_0x0025;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0058, code lost:
        if ($PcTestAnySpecifierMatches($PcTestRunnerSkipList((gnu.kawa.slib.test$Mnrunner) r11), r0) != java.lang.Boolean.FALSE) goto L_0x0025;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x005b, code lost:
        r11 = r0;
        r2 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x006d, code lost:
        if ($PcTestAnySpecifierMatches($PcTestRunnerFailList((gnu.kawa.slib.test$Mnrunner) r11), r0) == java.lang.Boolean.FALSE) goto L_0x007b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x006f, code lost:
        r5 = testResultSet$Ex(r0, Lit1, Lit3);
        r5 = Lit3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x007b, code lost:
        r5 = java.lang.Boolean.TRUE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00bd, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00be, code lost:
        r11 = r5;
        r5 = r12;
        new gnu.mapping.WrongType(r11, "%test-runner-fail-list", 0, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00d1, code lost:
        throw r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object $PcTestShouldExecute(java.lang.Object r13) {
        /*
            r0 = r13
            r5 = r0
            r11 = r5
            r5 = r11
            r6 = r11
            r2 = r6
            gnu.kawa.slib.test$Mnrunner r5 = (gnu.kawa.slib.test$Mnrunner) r5     // Catch:{ ClassCastException -> 0x007e }
            java.lang.Object r5 = $PcTestRunnerRunList(r5)
            r1 = r5
            r5 = r1
            java.lang.Boolean r6 = java.lang.Boolean.TRUE
            if (r5 != r6) goto L_0x0032
            r5 = 1
        L_0x0013:
            r3 = r5
            r5 = r3
            if (r5 == 0) goto L_0x0034
            r5 = r3
        L_0x0018:
            r6 = 1
            int r5 = r5 + 1
            r6 = 1
            r5 = r5 & 1
            r2 = r5
            r5 = r2
            if (r5 == 0) goto L_0x0046
            r5 = r2
            if (r5 == 0) goto L_0x005b
        L_0x0025:
            r5 = r0
            gnu.mapping.SimpleSymbol r6 = Lit1
            gnu.mapping.SimpleSymbol r7 = Lit2
            java.lang.Object r5 = testResultSet$Ex(r5, r6, r7)
            java.lang.Boolean r5 = java.lang.Boolean.FALSE
        L_0x0030:
            r0 = r5
            return r0
        L_0x0032:
            r5 = 0
            goto L_0x0013
        L_0x0034:
            r5 = r1
            r6 = r0
            java.lang.Object r5 = $PcTestAnySpecifierMatches(r5, r6)
            r11 = r5
            r5 = r11
            r6 = r11
            r4 = r6
            java.lang.Boolean r6 = java.lang.Boolean.FALSE     // Catch:{ ClassCastException -> 0x0093 }
            if (r5 == r6) goto L_0x0044
            r5 = 1
            goto L_0x0018
        L_0x0044:
            r5 = 0
            goto L_0x0018
        L_0x0046:
            r5 = r0
            r11 = r5
            r5 = r11
            r6 = r11
            r3 = r6
            gnu.kawa.slib.test$Mnrunner r5 = (gnu.kawa.slib.test$Mnrunner) r5     // Catch:{ ClassCastException -> 0x00a8 }
            java.lang.Object r5 = $PcTestRunnerSkipList(r5)
            r6 = r0
            java.lang.Object r5 = $PcTestAnySpecifierMatches(r5, r6)
            java.lang.Boolean r6 = java.lang.Boolean.FALSE
            if (r5 == r6) goto L_0x005b
            goto L_0x0025
        L_0x005b:
            r5 = r0
            r11 = r5
            r5 = r11
            r6 = r11
            r2 = r6
            gnu.kawa.slib.test$Mnrunner r5 = (gnu.kawa.slib.test$Mnrunner) r5     // Catch:{ ClassCastException -> 0x00bd }
            java.lang.Object r5 = $PcTestRunnerFailList(r5)
            r6 = r0
            java.lang.Object r5 = $PcTestAnySpecifierMatches(r5, r6)
            java.lang.Boolean r6 = java.lang.Boolean.FALSE
            if (r5 == r6) goto L_0x007b
            r5 = r0
            gnu.mapping.SimpleSymbol r6 = Lit1
            gnu.mapping.SimpleSymbol r7 = Lit3
            java.lang.Object r5 = testResultSet$Ex(r5, r6, r7)
            gnu.mapping.SimpleSymbol r5 = Lit3
            goto L_0x0030
        L_0x007b:
            java.lang.Boolean r5 = java.lang.Boolean.TRUE
            goto L_0x0030
        L_0x007e:
            r5 = move-exception
            gnu.mapping.WrongType r6 = new gnu.mapping.WrongType
            r11 = r5
            r12 = r6
            r5 = r12
            r6 = r11
            r7 = r12
            r11 = r6
            r12 = r7
            r6 = r12
            r7 = r11
            java.lang.String r8 = "%test-runner-run-list"
            r9 = 0
            r10 = r2
            r6.<init>((java.lang.ClassCastException) r7, (java.lang.String) r8, (int) r9, (java.lang.Object) r10)
            throw r5
        L_0x0093:
            r5 = move-exception
            gnu.mapping.WrongType r6 = new gnu.mapping.WrongType
            r11 = r5
            r12 = r6
            r5 = r12
            r6 = r11
            r7 = r12
            r11 = r6
            r12 = r7
            r6 = r12
            r7 = r11
            java.lang.String r8 = "x"
            r9 = -2
            r10 = r4
            r6.<init>((java.lang.ClassCastException) r7, (java.lang.String) r8, (int) r9, (java.lang.Object) r10)
            throw r5
        L_0x00a8:
            r5 = move-exception
            gnu.mapping.WrongType r6 = new gnu.mapping.WrongType
            r11 = r5
            r12 = r6
            r5 = r12
            r6 = r11
            r7 = r12
            r11 = r6
            r12 = r7
            r6 = r12
            r7 = r11
            java.lang.String r8 = "%test-runner-skip-list"
            r9 = 0
            r10 = r3
            r6.<init>((java.lang.ClassCastException) r7, (java.lang.String) r8, (int) r9, (java.lang.Object) r10)
            throw r5
        L_0x00bd:
            r5 = move-exception
            gnu.mapping.WrongType r6 = new gnu.mapping.WrongType
            r11 = r5
            r12 = r6
            r5 = r12
            r6 = r11
            r7 = r12
            r11 = r6
            r12 = r7
            r6 = r12
            r7 = r11
            java.lang.String r8 = "%test-runner-fail-list"
            r9 = 0
            r10 = r2
            r6.<init>((java.lang.ClassCastException) r7, (java.lang.String) r8, (int) r9, (java.lang.Object) r10)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.testing.$PcTestShouldExecute(java.lang.Object):java.lang.Object");
    }

    public static void $PcTestBegin(Object obj, Object obj2) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        Throwable th7;
        Throwable th8;
        Throwable th9;
        Throwable th10;
        Throwable th11;
        Throwable th12;
        Object suite$Mnname = obj;
        Object count = obj2;
        if (((Procedure) test$Mnrunner$Mncurrent).apply0() == Boolean.FALSE) {
            Object apply1 = ((Procedure) test$Mnrunner$Mncurrent).apply1(testRunnerCreate());
        }
        Object runner = ((Procedure) test$Mnrunner$Mncurrent).apply0();
        Object obj3 = runner;
        Object obj4 = obj3;
        try {
            Object apply4 = Scheme.applyToArgs.apply4(testRunnerOnGroupBegin((test$Mnrunner) obj3), runner, suite$Mnname, count);
            Object obj5 = runner;
            Object obj6 = obj5;
            try {
                test$Mnrunner test_mnrunner = (test$Mnrunner) obj5;
                Object obj7 = runner;
                Object obj8 = obj7;
                try {
                    Object $PcTestRunnerSkipList = $PcTestRunnerSkipList((test$Mnrunner) obj7);
                    Object obj9 = runner;
                    Object obj10 = obj9;
                    try {
                        $PcTestRunnerSkipSave$Ex(test_mnrunner, C1245lists.cons($PcTestRunnerSkipList, $PcTestRunnerSkipSave((test$Mnrunner) obj9)));
                        Object obj11 = runner;
                        Object obj12 = obj11;
                        try {
                            test$Mnrunner test_mnrunner2 = (test$Mnrunner) obj11;
                            Object obj13 = runner;
                            Object obj14 = obj13;
                            try {
                                Object $PcTestRunnerFailList = $PcTestRunnerFailList((test$Mnrunner) obj13);
                                Object obj15 = runner;
                                Object obj16 = obj15;
                                try {
                                    $PcTestRunnerFailSave$Ex(test_mnrunner2, C1245lists.cons($PcTestRunnerFailList, $PcTestRunnerFailSave((test$Mnrunner) obj15)));
                                    Object obj17 = runner;
                                    Object obj18 = obj17;
                                    try {
                                        test$Mnrunner test_mnrunner3 = (test$Mnrunner) obj17;
                                        Object obj19 = runner;
                                        Object obj20 = obj19;
                                        try {
                                            Pair cons = C1245lists.cons($PcTestRunnerTotalCount((test$Mnrunner) obj19), count);
                                            Object obj21 = runner;
                                            Object obj22 = obj21;
                                            try {
                                                $PcTestRunnerCountList$Ex(test_mnrunner3, C1245lists.cons(cons, $PcTestRunnerCountList((test$Mnrunner) obj21)));
                                                Object obj23 = runner;
                                                Object obj24 = obj23;
                                                try {
                                                    test$Mnrunner test_mnrunner4 = (test$Mnrunner) obj23;
                                                    Object obj25 = runner;
                                                    Object obj26 = obj25;
                                                    try {
                                                        testRunnerGroupStack$Ex(test_mnrunner4, C1245lists.cons(suite$Mnname, testRunnerGroupStack((test$Mnrunner) obj25)));
                                                    } catch (ClassCastException e) {
                                                        ClassCastException classCastException = e;
                                                        Throwable th13 = th12;
                                                        new WrongType(classCastException, "test-runner-group-stack", 0, obj26);
                                                        throw th13;
                                                    }
                                                } catch (ClassCastException e2) {
                                                    ClassCastException classCastException2 = e2;
                                                    Throwable th14 = th11;
                                                    new WrongType(classCastException2, "test-runner-group-stack!", 0, obj24);
                                                    throw th14;
                                                }
                                            } catch (ClassCastException e3) {
                                                ClassCastException classCastException3 = e3;
                                                Throwable th15 = th10;
                                                new WrongType(classCastException3, "%test-runner-count-list", 0, obj22);
                                                throw th15;
                                            }
                                        } catch (ClassCastException e4) {
                                            ClassCastException classCastException4 = e4;
                                            Throwable th16 = th9;
                                            new WrongType(classCastException4, "%test-runner-total-count", 0, obj20);
                                            throw th16;
                                        }
                                    } catch (ClassCastException e5) {
                                        ClassCastException classCastException5 = e5;
                                        Throwable th17 = th8;
                                        new WrongType(classCastException5, "%test-runner-count-list!", 0, obj18);
                                        throw th17;
                                    }
                                } catch (ClassCastException e6) {
                                    ClassCastException classCastException6 = e6;
                                    Throwable th18 = th7;
                                    new WrongType(classCastException6, "%test-runner-fail-save", 0, obj16);
                                    throw th18;
                                }
                            } catch (ClassCastException e7) {
                                ClassCastException classCastException7 = e7;
                                Throwable th19 = th6;
                                new WrongType(classCastException7, "%test-runner-fail-list", 0, obj14);
                                throw th19;
                            }
                        } catch (ClassCastException e8) {
                            ClassCastException classCastException8 = e8;
                            Throwable th20 = th5;
                            new WrongType(classCastException8, "%test-runner-fail-save!", 0, obj12);
                            throw th20;
                        }
                    } catch (ClassCastException e9) {
                        ClassCastException classCastException9 = e9;
                        Throwable th21 = th4;
                        new WrongType(classCastException9, "%test-runner-skip-save", 0, obj10);
                        throw th21;
                    }
                } catch (ClassCastException e10) {
                    ClassCastException classCastException10 = e10;
                    Throwable th22 = th3;
                    new WrongType(classCastException10, "%test-runner-skip-list", 0, obj8);
                    throw th22;
                }
            } catch (ClassCastException e11) {
                ClassCastException classCastException11 = e11;
                Throwable th23 = th2;
                new WrongType(classCastException11, "%test-runner-skip-save!", 0, obj6);
                throw th23;
            }
        } catch (ClassCastException e12) {
            ClassCastException classCastException12 = e12;
            Throwable th24 = th;
            new WrongType(classCastException12, "test-runner-on-group-begin", 0, obj4);
            throw th24;
        }
    }

    public static Boolean testOnGroupBeginSimple(Object obj, Object obj2, Object obj3) {
        Throwable th;
        Throwable th2;
        Object stringAppend;
        Throwable th3;
        Throwable th4;
        Object runner = obj;
        Object suite$Mnname = obj2;
        Object obj4 = obj3;
        Object obj5 = runner;
        Object obj6 = obj5;
        try {
            if (C1245lists.isNull(testRunnerGroupStack((test$Mnrunner) obj5))) {
                ports.display("%%%% Starting test ");
                ports.display(suite$Mnname);
                if (strings.isString(Boolean.TRUE)) {
                    stringAppend = Boolean.TRUE;
                } else {
                    Object[] objArr = new Object[2];
                    objArr[0] = suite$Mnname;
                    Object[] objArr2 = objArr;
                    objArr2[1] = ".log";
                    stringAppend = strings.stringAppend(objArr2);
                }
                Object log$Mnfile$Mnname = stringAppend;
                Object obj7 = log$Mnfile$Mnname;
                Object obj8 = obj7;
                try {
                    OutPort log$Mnfile = ports.openOutputFile(Path.valueOf(obj7));
                    ports.display("%%%% Starting test ", log$Mnfile);
                    ports.display(suite$Mnname, log$Mnfile);
                    ports.newline(log$Mnfile);
                    Object obj9 = runner;
                    Object obj10 = obj9;
                    try {
                        testRunnerAuxValue$Ex((test$Mnrunner) obj9, log$Mnfile);
                        ports.display("  (Writing full log to \"");
                        ports.display(log$Mnfile$Mnname);
                        ports.display("\")");
                        ports.newline();
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th5 = th4;
                        new WrongType(classCastException, "test-runner-aux-value!", 0, obj10);
                        throw th5;
                    }
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th6 = th3;
                    new WrongType(classCastException2, "open-output-file", 1, obj8);
                    throw th6;
                }
            }
            Object obj11 = runner;
            Object obj12 = obj11;
            try {
                Object log = testRunnerAuxValue((test$Mnrunner) obj11);
                if (ports.isOutputPort(log)) {
                    ports.display("Group begin: ", log);
                    ports.display(suite$Mnname, log);
                    ports.newline(log);
                }
                return Boolean.FALSE;
            } catch (ClassCastException e3) {
                ClassCastException classCastException3 = e3;
                Throwable th7 = th2;
                new WrongType(classCastException3, "test-runner-aux-value", 0, obj12);
                throw th7;
            }
        } catch (ClassCastException e4) {
            ClassCastException classCastException4 = e4;
            Throwable th8 = th;
            new WrongType(classCastException4, "test-runner-group-stack", 0, obj6);
            throw th8;
        }
    }

    public static Boolean testOnGroupEndSimple(Object obj) {
        Throwable th;
        Throwable th2;
        Object runner = obj;
        Object obj2 = runner;
        Object obj3 = obj2;
        try {
            Object log = testRunnerAuxValue((test$Mnrunner) obj2);
            if (ports.isOutputPort(log)) {
                ports.display("Group end: ", log);
                Object obj4 = runner;
                Object obj5 = obj4;
                try {
                    ports.display(C1245lists.car.apply1(testRunnerGroupStack((test$Mnrunner) obj4)), log);
                    ports.newline(log);
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th3 = th2;
                    new WrongType(classCastException, "test-runner-group-stack", 0, obj5);
                    throw th3;
                }
            }
            return Boolean.FALSE;
        } catch (ClassCastException e2) {
            ClassCastException classCastException2 = e2;
            Throwable th4 = th;
            new WrongType(classCastException2, "test-runner-aux-value", 0, obj3);
            throw th4;
        }
    }

    static void $PcTestOnBadCountWrite(Object obj, Object count, Object expected$Mncount, Object obj2) {
        Object obj3 = obj;
        Object port = obj2;
        ports.display("*** Total number of tests was ", port);
        ports.display(count, port);
        ports.display(" but should be ", port);
        ports.display(expected$Mncount, port);
        ports.display(". ***", port);
        ports.newline(port);
        ports.display("*** Discrepancy indicates testsuite error or exceptions. ***", port);
        ports.newline(port);
    }

    public static void testOnBadCountSimple(Object obj, Object obj2, Object obj3) {
        Throwable th;
        Object runner = obj;
        Object count = obj2;
        Object expected$Mncount = obj3;
        $PcTestOnBadCountWrite(runner, count, expected$Mncount, ports.current$Mnoutput$Mnport.apply0());
        Object obj4 = runner;
        Object obj5 = obj4;
        try {
            Object log = testRunnerAuxValue((test$Mnrunner) obj4);
            if (ports.isOutputPort(log)) {
                $PcTestOnBadCountWrite(runner, count, expected$Mncount, log);
            }
        } catch (ClassCastException e) {
            ClassCastException classCastException = e;
            Throwable th2 = th;
            new WrongType(classCastException, "test-runner-aux-value", 0, obj5);
            throw th2;
        }
    }

    public static Object testOnBadEndNameSimple(Object runner, Object begin$Mnname, Object end$Mnname) {
        Object[] objArr = new Object[5];
        objArr[0] = $PcTestFormatLine(runner);
        Object[] objArr2 = objArr;
        objArr2[1] = "test-end ";
        Object[] objArr3 = objArr2;
        objArr3[2] = begin$Mnname;
        Object[] objArr4 = objArr3;
        objArr4[3] = " does not match test-begin ";
        Object[] objArr5 = objArr4;
        objArr5[4] = end$Mnname;
        return misc.error$V(strings.stringAppend(objArr5), new Object[0]);
    }

    static void $PcTestFinalReport1(Object obj, Object obj2, Object obj3) {
        Object value = obj;
        Object label = obj2;
        Object port = obj3;
        if (Scheme.numGrt.apply2(value, Lit0) != Boolean.FALSE) {
            ports.display(label, port);
            ports.display(value, port);
            ports.newline(port);
        }
    }

    static void $PcTestFinalReportSimple(Object obj, Object obj2) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Object runner = obj;
        Object port = obj2;
        Object obj3 = runner;
        Object obj4 = obj3;
        try {
            $PcTestFinalReport1(testRunnerPassCount((test$Mnrunner) obj3), "# of expected passes      ", port);
            Object obj5 = runner;
            Object obj6 = obj5;
            try {
                $PcTestFinalReport1(testRunnerXfailCount((test$Mnrunner) obj5), "# of expected failures    ", port);
                Object obj7 = runner;
                Object obj8 = obj7;
                try {
                    $PcTestFinalReport1(testRunnerXpassCount((test$Mnrunner) obj7), "# of unexpected successes ", port);
                    Object obj9 = runner;
                    Object obj10 = obj9;
                    try {
                        $PcTestFinalReport1(testRunnerFailCount((test$Mnrunner) obj9), "# of unexpected failures  ", port);
                        Object obj11 = runner;
                        Object obj12 = obj11;
                        try {
                            $PcTestFinalReport1(testRunnerSkipCount((test$Mnrunner) obj11), "# of skipped tests        ", port);
                        } catch (ClassCastException e) {
                            ClassCastException classCastException = e;
                            Throwable th6 = th5;
                            new WrongType(classCastException, "test-runner-skip-count", 0, obj12);
                            throw th6;
                        }
                    } catch (ClassCastException e2) {
                        ClassCastException classCastException2 = e2;
                        Throwable th7 = th4;
                        new WrongType(classCastException2, "test-runner-fail-count", 0, obj10);
                        throw th7;
                    }
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th8 = th3;
                    new WrongType(classCastException3, "test-runner-xpass-count", 0, obj8);
                    throw th8;
                }
            } catch (ClassCastException e4) {
                ClassCastException classCastException4 = e4;
                Throwable th9 = th2;
                new WrongType(classCastException4, "test-runner-xfail-count", 0, obj6);
                throw th9;
            }
        } catch (ClassCastException e5) {
            ClassCastException classCastException5 = e5;
            Throwable th10 = th;
            new WrongType(classCastException5, "test-runner-pass-count", 0, obj4);
            throw th10;
        }
    }

    public static void testOnFinalSimple(Object obj) {
        Throwable th;
        Object runner = obj;
        $PcTestFinalReportSimple(runner, ports.current$Mnoutput$Mnport.apply0());
        Object obj2 = runner;
        Object obj3 = obj2;
        try {
            Object log = testRunnerAuxValue((test$Mnrunner) obj2);
            if (ports.isOutputPort(log)) {
                $PcTestFinalReportSimple(runner, log);
            }
        } catch (ClassCastException e) {
            ClassCastException classCastException = e;
            Throwable th2 = th;
            new WrongType(classCastException, "test-runner-aux-value", 0, obj3);
            throw th2;
        }
    }

    static Object $PcTestFormatLine(Object runner) {
        Throwable th;
        Object runner2;
        Throwable th2;
        Object obj = runner;
        Object obj2 = obj;
        try {
            Object line$Mninfo = testResultAlist((test$Mnrunner) obj);
            Object source$Mnfile = C1245lists.assq(Lit4, line$Mninfo);
            Object source$Mnline = C1245lists.assq(Lit5, line$Mninfo);
            Object file = source$Mnfile != Boolean.FALSE ? C1245lists.cdr.apply1(source$Mnfile) : "";
            if (source$Mnline != Boolean.FALSE) {
                Object[] objArr = new Object[4];
                objArr[0] = file;
                Object[] objArr2 = objArr;
                objArr2[1] = ":";
                Object[] objArr3 = objArr2;
                Object[] objArr4 = objArr3;
                Object[] objArr5 = objArr3;
                Object apply1 = C1245lists.cdr.apply1(source$Mnline);
                Object obj3 = apply1;
                try {
                    objArr5[2] = numbers.number$To$String((Number) apply1);
                    Object[] objArr6 = objArr4;
                    objArr6[3] = ": ";
                    runner2 = strings.stringAppend(objArr6);
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th3 = th2;
                    new WrongType(classCastException, "number->string", 1, obj3);
                    throw th3;
                }
            } else {
                runner2 = "";
            }
            return runner2;
        } catch (ClassCastException e2) {
            ClassCastException classCastException2 = e2;
            Throwable th4 = th;
            new WrongType(classCastException2, "test-result-alist", 0, obj2);
            throw th4;
        }
    }

    public static Object $PcTestEnd(Object obj, Object obj2) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        Throwable th7;
        Throwable th8;
        Throwable th9;
        Throwable th10;
        Throwable th11;
        Throwable th12;
        Throwable th13;
        Throwable th14;
        Throwable th15;
        Throwable th16;
        Throwable th17;
        Object suite$Mnname;
        Throwable th18;
        Throwable th19;
        Throwable th20;
        Object suite$Mnname2 = obj;
        Object line$Mninfo = obj2;
        Object r = testRunnerGet();
        Object obj3 = r;
        Object obj4 = obj3;
        try {
            Object groups = testRunnerGroupStack((test$Mnrunner) obj3);
            Object line = $PcTestFormatLine(r);
            Object obj5 = r;
            Object obj6 = obj5;
            try {
                testResultAlist$Ex((test$Mnrunner) obj5, line$Mninfo);
                if (C1245lists.isNull(groups)) {
                    Object[] objArr = new Object[2];
                    objArr[0] = line;
                    Object[] objArr2 = objArr;
                    objArr2[1] = "test-end not in a group";
                    Object error$V = misc.error$V(strings.stringAppend(objArr2), new Object[0]);
                }
                if (suite$Mnname2 == Boolean.FALSE ? suite$Mnname2 != Boolean.FALSE : !IsEqual.apply(suite$Mnname2, C1245lists.car.apply1(groups))) {
                    Object obj7 = r;
                    Object obj8 = obj7;
                    try {
                        Object apply4 = Scheme.applyToArgs.apply4(testRunnerOnBadEndName((test$Mnrunner) obj7), r, suite$Mnname2, C1245lists.car.apply1(groups));
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th21 = th20;
                        new WrongType(classCastException, "test-runner-on-bad-end-name", 0, obj8);
                        throw th21;
                    }
                }
                Object obj9 = r;
                Object obj10 = obj9;
                try {
                    Object count$Mnlist = $PcTestRunnerCountList((test$Mnrunner) obj9);
                    Object expected$Mncount = C1245lists.cdar.apply1(count$Mnlist);
                    Object saved$Mncount = C1245lists.caar.apply1(count$Mnlist);
                    Object obj11 = r;
                    Object obj12 = obj11;
                    try {
                        Object group$Mncount = AddOp.$Mn.apply2($PcTestRunnerTotalCount((test$Mnrunner) obj11), saved$Mncount);
                        if (expected$Mncount == Boolean.FALSE ? expected$Mncount != Boolean.FALSE : Scheme.numEqu.apply2(expected$Mncount, group$Mncount) == Boolean.FALSE) {
                            Object obj13 = r;
                            Object obj14 = obj13;
                            try {
                                Object apply42 = Scheme.applyToArgs.apply4(testRunnerOnBadCount((test$Mnrunner) obj13), r, group$Mncount, expected$Mncount);
                            } catch (ClassCastException e2) {
                                ClassCastException classCastException2 = e2;
                                Throwable th22 = th19;
                                new WrongType(classCastException2, "test-runner-on-bad-count", 0, obj14);
                                throw th22;
                            }
                        }
                        Object obj15 = r;
                        Object obj16 = obj15;
                        try {
                            Object apply2 = Scheme.applyToArgs.apply2(testRunnerOnGroupEnd((test$Mnrunner) obj15), r);
                            Object obj17 = r;
                            Object obj18 = obj17;
                            try {
                                test$Mnrunner test_mnrunner = (test$Mnrunner) obj17;
                                Object obj19 = r;
                                Object obj20 = obj19;
                                try {
                                    testRunnerGroupStack$Ex(test_mnrunner, C1245lists.cdr.apply1(testRunnerGroupStack((test$Mnrunner) obj19)));
                                    Object obj21 = r;
                                    Object obj22 = obj21;
                                    try {
                                        test$Mnrunner test_mnrunner2 = (test$Mnrunner) obj21;
                                        Object obj23 = r;
                                        Object obj24 = obj23;
                                        try {
                                            $PcTestRunnerSkipList$Ex(test_mnrunner2, C1245lists.car.apply1($PcTestRunnerSkipSave((test$Mnrunner) obj23)));
                                            Object obj25 = r;
                                            Object obj26 = obj25;
                                            try {
                                                test$Mnrunner test_mnrunner3 = (test$Mnrunner) obj25;
                                                Object obj27 = r;
                                                Object obj28 = obj27;
                                                try {
                                                    $PcTestRunnerSkipSave$Ex(test_mnrunner3, C1245lists.cdr.apply1($PcTestRunnerSkipSave((test$Mnrunner) obj27)));
                                                    Object obj29 = r;
                                                    Object obj30 = obj29;
                                                    try {
                                                        test$Mnrunner test_mnrunner4 = (test$Mnrunner) obj29;
                                                        Object obj31 = r;
                                                        Object obj32 = obj31;
                                                        try {
                                                            $PcTestRunnerFailList$Ex(test_mnrunner4, C1245lists.car.apply1($PcTestRunnerFailSave((test$Mnrunner) obj31)));
                                                            Object obj33 = r;
                                                            Object obj34 = obj33;
                                                            try {
                                                                test$Mnrunner test_mnrunner5 = (test$Mnrunner) obj33;
                                                                Object obj35 = r;
                                                                Object obj36 = obj35;
                                                                try {
                                                                    $PcTestRunnerFailSave$Ex(test_mnrunner5, C1245lists.cdr.apply1($PcTestRunnerFailSave((test$Mnrunner) obj35)));
                                                                    Object obj37 = r;
                                                                    Object obj38 = obj37;
                                                                    try {
                                                                        $PcTestRunnerCountList$Ex((test$Mnrunner) obj37, C1245lists.cdr.apply1(count$Mnlist));
                                                                        Object obj39 = r;
                                                                        Object obj40 = obj39;
                                                                        try {
                                                                            if (C1245lists.isNull(testRunnerGroupStack((test$Mnrunner) obj39))) {
                                                                                Object obj41 = r;
                                                                                Object obj42 = obj41;
                                                                                try {
                                                                                    suite$Mnname = Scheme.applyToArgs.apply2(testRunnerOnFinal((test$Mnrunner) obj41), r);
                                                                                } catch (ClassCastException e3) {
                                                                                    ClassCastException classCastException3 = e3;
                                                                                    Throwable th23 = th18;
                                                                                    new WrongType(classCastException3, "test-runner-on-final", 0, obj42);
                                                                                    throw th23;
                                                                                }
                                                                            } else {
                                                                                suite$Mnname = Values.empty;
                                                                            }
                                                                            return suite$Mnname;
                                                                        } catch (ClassCastException e4) {
                                                                            ClassCastException classCastException4 = e4;
                                                                            Throwable th24 = th17;
                                                                            new WrongType(classCastException4, "test-runner-group-stack", 0, obj40);
                                                                            throw th24;
                                                                        }
                                                                    } catch (ClassCastException e5) {
                                                                        ClassCastException classCastException5 = e5;
                                                                        Throwable th25 = th16;
                                                                        new WrongType(classCastException5, "%test-runner-count-list!", 0, obj38);
                                                                        throw th25;
                                                                    }
                                                                } catch (ClassCastException e6) {
                                                                    ClassCastException classCastException6 = e6;
                                                                    Throwable th26 = th15;
                                                                    new WrongType(classCastException6, "%test-runner-fail-save", 0, obj36);
                                                                    throw th26;
                                                                }
                                                            } catch (ClassCastException e7) {
                                                                ClassCastException classCastException7 = e7;
                                                                Throwable th27 = th14;
                                                                new WrongType(classCastException7, "%test-runner-fail-save!", 0, obj34);
                                                                throw th27;
                                                            }
                                                        } catch (ClassCastException e8) {
                                                            ClassCastException classCastException8 = e8;
                                                            Throwable th28 = th13;
                                                            new WrongType(classCastException8, "%test-runner-fail-save", 0, obj32);
                                                            throw th28;
                                                        }
                                                    } catch (ClassCastException e9) {
                                                        ClassCastException classCastException9 = e9;
                                                        Throwable th29 = th12;
                                                        new WrongType(classCastException9, "%test-runner-fail-list!", 0, obj30);
                                                        throw th29;
                                                    }
                                                } catch (ClassCastException e10) {
                                                    ClassCastException classCastException10 = e10;
                                                    Throwable th30 = th11;
                                                    new WrongType(classCastException10, "%test-runner-skip-save", 0, obj28);
                                                    throw th30;
                                                }
                                            } catch (ClassCastException e11) {
                                                ClassCastException classCastException11 = e11;
                                                Throwable th31 = th10;
                                                new WrongType(classCastException11, "%test-runner-skip-save!", 0, obj26);
                                                throw th31;
                                            }
                                        } catch (ClassCastException e12) {
                                            ClassCastException classCastException12 = e12;
                                            Throwable th32 = th9;
                                            new WrongType(classCastException12, "%test-runner-skip-save", 0, obj24);
                                            throw th32;
                                        }
                                    } catch (ClassCastException e13) {
                                        ClassCastException classCastException13 = e13;
                                        Throwable th33 = th8;
                                        new WrongType(classCastException13, "%test-runner-skip-list!", 0, obj22);
                                        throw th33;
                                    }
                                } catch (ClassCastException e14) {
                                    ClassCastException classCastException14 = e14;
                                    Throwable th34 = th7;
                                    new WrongType(classCastException14, "test-runner-group-stack", 0, obj20);
                                    throw th34;
                                }
                            } catch (ClassCastException e15) {
                                ClassCastException classCastException15 = e15;
                                Throwable th35 = th6;
                                new WrongType(classCastException15, "test-runner-group-stack!", 0, obj18);
                                throw th35;
                            }
                        } catch (ClassCastException e16) {
                            ClassCastException classCastException16 = e16;
                            Throwable th36 = th5;
                            new WrongType(classCastException16, "test-runner-on-group-end", 0, obj16);
                            throw th36;
                        }
                    } catch (ClassCastException e17) {
                        ClassCastException classCastException17 = e17;
                        Throwable th37 = th4;
                        new WrongType(classCastException17, "%test-runner-total-count", 0, obj12);
                        throw th37;
                    }
                } catch (ClassCastException e18) {
                    ClassCastException classCastException18 = e18;
                    Throwable th38 = th3;
                    new WrongType(classCastException18, "%test-runner-count-list", 0, obj10);
                    throw th38;
                }
            } catch (ClassCastException e19) {
                ClassCastException classCastException19 = e19;
                Throwable th39 = th2;
                new WrongType(classCastException19, "test-result-alist!", 0, obj6);
                throw th39;
            }
        } catch (ClassCastException e20) {
            ClassCastException classCastException20 = e20;
            Throwable th40 = th;
            new WrongType(classCastException20, "test-runner-group-stack", 0, obj4);
            throw th40;
        }
    }

    static Object testOnTestBeginSimple(Object obj) {
        Throwable th;
        Object runner;
        Throwable th2;
        Object runner2 = obj;
        Object obj2 = runner2;
        Object obj3 = obj2;
        try {
            Object log = testRunnerAuxValue((test$Mnrunner) obj2);
            if (ports.isOutputPort(log)) {
                Object obj4 = runner2;
                Object obj5 = obj4;
                try {
                    Object results = testResultAlist((test$Mnrunner) obj4);
                    Object source$Mnfile = C1245lists.assq(Lit4, results);
                    Object source$Mnline = C1245lists.assq(Lit5, results);
                    Object source$Mnform = C1245lists.assq(Lit6, results);
                    Object test$Mnname = C1245lists.assq(Lit7, results);
                    ports.display("Test begin:", log);
                    ports.newline(log);
                    if (test$Mnname != Boolean.FALSE) {
                        Object $PcTestWriteResult1 = $PcTestWriteResult1(test$Mnname, log);
                    }
                    if (source$Mnfile != Boolean.FALSE) {
                        Object $PcTestWriteResult12 = $PcTestWriteResult1(source$Mnfile, log);
                    }
                    if (source$Mnline != Boolean.FALSE) {
                        Object $PcTestWriteResult13 = $PcTestWriteResult1(source$Mnline, log);
                    }
                    runner = source$Mnfile != Boolean.FALSE ? $PcTestWriteResult1(source$Mnform, log) : Values.empty;
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th3 = th2;
                    new WrongType(classCastException, "test-result-alist", 0, obj5);
                    throw th3;
                }
            } else {
                runner = Values.empty;
            }
            return runner;
        } catch (ClassCastException e2) {
            ClassCastException classCastException2 = e2;
            Throwable th4 = th;
            new WrongType(classCastException2, "test-runner-aux-value", 0, obj3);
            throw th4;
        }
    }

    public static Object testOnTestEndSimple(Object obj) {
        Throwable th;
        Throwable th2;
        Object runner;
        Throwable th3;
        Throwable th4;
        Object runner2 = obj;
        Object obj2 = runner2;
        Object obj3 = obj2;
        try {
            Object testRunnerAuxValue = testRunnerAuxValue((test$Mnrunner) obj2);
            Object obj4 = runner2;
            Object obj5 = obj4;
            try {
                Object p = C1245lists.assq(Lit1, testResultAlist((test$Mnrunner) obj4));
                Object kind = p != Boolean.FALSE ? C1245lists.cdr.apply1(p) : Boolean.FALSE;
                Object log = testRunnerAuxValue;
                if (C1245lists.memq(kind, Lit8) != Boolean.FALSE) {
                    Object obj6 = runner2;
                    Object obj7 = obj6;
                    try {
                        Object p2 = testResultAlist((test$Mnrunner) obj6);
                        Object source$Mnfile = C1245lists.assq(Lit4, p2);
                        Object source$Mnline = C1245lists.assq(Lit5, p2);
                        Object test$Mnname = C1245lists.assq(Lit7, p2);
                        if (!(source$Mnfile == Boolean.FALSE && source$Mnline == Boolean.FALSE)) {
                            if (source$Mnfile != Boolean.FALSE) {
                                ports.display(C1245lists.cdr.apply1(source$Mnfile));
                            }
                            ports.display(":");
                            if (source$Mnline != Boolean.FALSE) {
                                ports.display(C1245lists.cdr.apply1(source$Mnline));
                            }
                            ports.display(": ");
                        }
                        ports.display(kind == Lit9 ? "XPASS" : "FAIL");
                        if (test$Mnname != Boolean.FALSE) {
                            ports.display(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                            ports.display(C1245lists.cdr.apply1(test$Mnname));
                        }
                        ports.newline();
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th5 = th4;
                        new WrongType(classCastException, "test-result-alist", 0, obj7);
                        throw th5;
                    }
                }
                if (ports.isOutputPort(log)) {
                    ports.display("Test end:", log);
                    ports.newline(log);
                    Object obj8 = runner2;
                    Object obj9 = obj8;
                    try {
                        Object testResultAlist = testResultAlist((test$Mnrunner) obj8);
                        while (true) {
                            Object list = testResultAlist;
                            if (!C1245lists.isPair(list)) {
                                break;
                            }
                            Object pair = C1245lists.car.apply1(list);
                            if (C1245lists.memq(C1245lists.car.apply1(pair), Lit10) == Boolean.FALSE) {
                                Object $PcTestWriteResult1 = $PcTestWriteResult1(pair, log);
                            }
                            testResultAlist = C1245lists.cdr.apply1(list);
                        }
                        runner = Values.empty;
                    } catch (ClassCastException e2) {
                        ClassCastException classCastException2 = e2;
                        Throwable th6 = th3;
                        new WrongType(classCastException2, "test-result-alist", 0, obj9);
                        throw th6;
                    }
                } else {
                    runner = Values.empty;
                }
                return runner;
            } catch (ClassCastException e3) {
                ClassCastException classCastException3 = e3;
                Throwable th7 = th2;
                new WrongType(classCastException3, "test-result-alist", 0, obj5);
                throw th7;
            }
        } catch (ClassCastException e4) {
            ClassCastException classCastException4 = e4;
            Throwable th8 = th;
            new WrongType(classCastException4, "test-runner-aux-value", 0, obj3);
            throw th8;
        }
    }

    static Object $PcTestWriteResult1(Object obj, Object obj2) {
        Object pair = obj;
        Object port = obj2;
        ports.display("  ", port);
        ports.display(C1245lists.car.apply1(pair), port);
        ports.display(": ", port);
        ports.write(C1245lists.cdr.apply1(pair), port);
        ports.newline(port);
        return Values.empty;
    }

    public static Object testResultSet$Ex(Object obj, Object obj2, Object obj3) {
        Throwable th;
        Throwable th2;
        Object runner;
        Throwable th3;
        Object runner2 = obj;
        Object pname = obj2;
        Object value = obj3;
        Object obj4 = runner2;
        Object obj5 = obj4;
        try {
            Object alist = testResultAlist((test$Mnrunner) obj4);
            Object p = C1245lists.assq(pname, alist);
            if (p != Boolean.FALSE) {
                Object obj6 = p;
                Object obj7 = obj6;
                try {
                    C1245lists.setCdr$Ex((Pair) obj6, value);
                    runner = Values.empty;
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th4 = th3;
                    new WrongType(classCastException, "set-cdr!", 1, obj7);
                    throw th4;
                }
            } else {
                Object obj8 = runner2;
                Object obj9 = obj8;
                try {
                    testResultAlist$Ex((test$Mnrunner) obj8, C1245lists.cons(C1245lists.cons(pname, value), alist));
                    runner = Values.empty;
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th5 = th2;
                    new WrongType(classCastException2, "test-result-alist!", 0, obj9);
                    throw th5;
                }
            }
            return runner;
        } catch (ClassCastException e3) {
            ClassCastException classCastException3 = e3;
            Throwable th6 = th;
            new WrongType(classCastException3, "test-result-alist", 0, obj5);
            throw th6;
        }
    }

    public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj4 = obj;
        Object obj5 = obj2;
        Object obj6 = obj3;
        switch (moduleMethod2.selector) {
            case 50:
                return lambda1(obj4, obj5, obj6);
            case 51:
                return lambda2(obj4, obj5, obj6);
            case 52:
                return lambda3(obj4, obj5, obj6);
            case 59:
                return testOnGroupBeginSimple(obj4, obj5, obj6);
            case 61:
                testOnBadCountSimple(obj4, obj5, obj6);
                return Values.empty;
            case 62:
                return testOnBadEndNameSimple(obj4, obj5, obj6);
            case 67:
                return testResultSet$Ex(obj4, obj5, obj6);
            default:
                return super.apply3(moduleMethod2, obj4, obj5, obj6);
        }
    }

    public static void testResultClear(Object runner) {
        Throwable th;
        Object obj = runner;
        Object obj2 = obj;
        try {
            testResultAlist$Ex((test$Mnrunner) obj, LList.Empty);
        } catch (ClassCastException e) {
            ClassCastException classCastException = e;
            Throwable th2 = th;
            new WrongType(classCastException, "test-result-alist!", 0, obj2);
            throw th2;
        }
    }

    public static void testResultRemove(Object obj, Object obj2) {
        frame frame6;
        Throwable th;
        Throwable th2;
        Object runner = obj;
        Object pname = obj2;
        new frame();
        frame frame7 = frame6;
        Object obj3 = runner;
        Object obj4 = obj3;
        try {
            Object alist = testResultAlist((test$Mnrunner) obj3);
            frame7.f233p = C1245lists.assq(pname, alist);
            if (frame7.f233p != Boolean.FALSE) {
                Object obj5 = runner;
                Object obj6 = obj5;
                try {
                    testResultAlist$Ex((test$Mnrunner) obj5, frame7.lambda4loop(alist));
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th3 = th2;
                    new WrongType(classCastException, "test-result-alist!", 0, obj6);
                    throw th3;
                }
            }
        } catch (ClassCastException e2) {
            ClassCastException classCastException2 = e2;
            Throwable th4 = th;
            new WrongType(classCastException2, "test-result-alist", 0, obj4);
            throw th4;
        }
    }

    /* compiled from: testing.scm */
    public class frame extends ModuleBody {

        /* renamed from: p */
        Object f233p;

        public frame() {
        }

        public Object lambda4loop(Object obj) {
            Object cons;
            Object r = obj;
            if (r == this.f233p) {
                cons = C1245lists.cdr.apply1(r);
            } else {
                cons = C1245lists.cons(C1245lists.car.apply1(r), lambda4loop(C1245lists.cdr.apply1(r)));
            }
            return cons;
        }
    }

    public static Object testResultKind$V(Object[] argsArray) {
        Object runner;
        Throwable th;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        LList rest = makeList;
        if (C1245lists.isPair(rest)) {
            runner = C1245lists.car.apply1(rest);
        } else {
            runner = ((Procedure) test$Mnrunner$Mncurrent).apply0();
        }
        Object obj = runner;
        Object obj2 = obj;
        try {
            Object p = C1245lists.assq(Lit1, testResultAlist((test$Mnrunner) obj));
            return p != Boolean.FALSE ? C1245lists.cdr.apply1(p) : Boolean.FALSE;
        } catch (ClassCastException e) {
            ClassCastException classCastException = e;
            Throwable th2 = th;
            new WrongType(classCastException, "test-result-alist", 0, obj2);
            throw th2;
        }
    }

    public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object[] objArr2 = objArr;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 70:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 71:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 84:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 86:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 87:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            default:
                return super.matchN(moduleMethod2, objArr2, callContext2);
        }
    }

    public static Object isTestPassed$V(Object[] argsArray) {
        Throwable th;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        LList rest = makeList;
        Object apply1 = C1245lists.isPair(rest) ? C1245lists.car.apply1(rest) : testRunnerGet();
        Object obj = apply1;
        try {
            Object p = C1245lists.assq(Lit1, testResultAlist((test$Mnrunner) apply1));
            return C1245lists.memq(p != Boolean.FALSE ? C1245lists.cdr.apply1(p) : Boolean.FALSE, Lit11);
        } catch (ClassCastException e) {
            ClassCastException classCastException = e;
            Throwable th2 = th;
            new WrongType(classCastException, "test-result-alist", 0, obj);
            throw th2;
        }
    }

    public static Object $PcTestReportResult() {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        Throwable th7;
        Throwable th8;
        Throwable th9;
        Throwable th10;
        Throwable th11;
        Throwable th12;
        Throwable th13;
        Object r = testRunnerGet();
        Object result$Mnkind = testResultKind$V(new Object[]{r});
        if (Scheme.isEqv.apply2(result$Mnkind, Lit12) != Boolean.FALSE) {
            Object obj = r;
            Object obj2 = obj;
            try {
                test$Mnrunner test_mnrunner = (test$Mnrunner) obj;
                Object obj3 = r;
                Object obj4 = obj3;
                try {
                    testRunnerPassCount$Ex(test_mnrunner, AddOp.$Pl.apply2(Lit13, testRunnerPassCount((test$Mnrunner) obj3)));
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th14 = th13;
                    new WrongType(classCastException, "test-runner-pass-count", 0, obj4);
                    throw th14;
                }
            } catch (ClassCastException e2) {
                ClassCastException classCastException2 = e2;
                Throwable th15 = th12;
                new WrongType(classCastException2, "test-runner-pass-count!", 0, obj2);
                throw th15;
            }
        } else if (Scheme.isEqv.apply2(result$Mnkind, Lit14) != Boolean.FALSE) {
            Object obj5 = r;
            Object obj6 = obj5;
            try {
                test$Mnrunner test_mnrunner2 = (test$Mnrunner) obj5;
                Object obj7 = r;
                Object obj8 = obj7;
                try {
                    testRunnerFailCount$Ex(test_mnrunner2, AddOp.$Pl.apply2(Lit13, testRunnerFailCount((test$Mnrunner) obj7)));
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th16 = th8;
                    new WrongType(classCastException3, "test-runner-fail-count", 0, obj8);
                    throw th16;
                }
            } catch (ClassCastException e4) {
                ClassCastException classCastException4 = e4;
                Throwable th17 = th7;
                new WrongType(classCastException4, "test-runner-fail-count!", 0, obj6);
                throw th17;
            }
        } else if (Scheme.isEqv.apply2(result$Mnkind, Lit9) != Boolean.FALSE) {
            Object obj9 = r;
            Object obj10 = obj9;
            try {
                test$Mnrunner test_mnrunner3 = (test$Mnrunner) obj9;
                Object obj11 = r;
                Object obj12 = obj11;
                try {
                    testRunnerXpassCount$Ex(test_mnrunner3, AddOp.$Pl.apply2(Lit13, testRunnerXpassCount((test$Mnrunner) obj11)));
                } catch (ClassCastException e5) {
                    ClassCastException classCastException5 = e5;
                    Throwable th18 = th6;
                    new WrongType(classCastException5, "test-runner-xpass-count", 0, obj12);
                    throw th18;
                }
            } catch (ClassCastException e6) {
                ClassCastException classCastException6 = e6;
                Throwable th19 = th5;
                new WrongType(classCastException6, "test-runner-xpass-count!", 0, obj10);
                throw th19;
            }
        } else if (Scheme.isEqv.apply2(result$Mnkind, Lit3) != Boolean.FALSE) {
            Object obj13 = r;
            Object obj14 = obj13;
            try {
                test$Mnrunner test_mnrunner4 = (test$Mnrunner) obj13;
                Object obj15 = r;
                Object obj16 = obj15;
                try {
                    testRunnerXfailCount$Ex(test_mnrunner4, AddOp.$Pl.apply2(Lit13, testRunnerXfailCount((test$Mnrunner) obj15)));
                } catch (ClassCastException e7) {
                    ClassCastException classCastException7 = e7;
                    Throwable th20 = th4;
                    new WrongType(classCastException7, "test-runner-xfail-count", 0, obj16);
                    throw th20;
                }
            } catch (ClassCastException e8) {
                ClassCastException classCastException8 = e8;
                Throwable th21 = th3;
                new WrongType(classCastException8, "test-runner-xfail-count!", 0, obj14);
                throw th21;
            }
        } else {
            Object obj17 = r;
            Object obj18 = obj17;
            try {
                test$Mnrunner test_mnrunner5 = (test$Mnrunner) obj17;
                Object obj19 = r;
                Object obj20 = obj19;
                try {
                    testRunnerSkipCount$Ex(test_mnrunner5, AddOp.$Pl.apply2(Lit13, testRunnerSkipCount((test$Mnrunner) obj19)));
                } catch (ClassCastException e9) {
                    ClassCastException classCastException9 = e9;
                    Throwable th22 = th2;
                    new WrongType(classCastException9, "test-runner-skip-count", 0, obj20);
                    throw th22;
                }
            } catch (ClassCastException e10) {
                ClassCastException classCastException10 = e10;
                Throwable th23 = th;
                new WrongType(classCastException10, "test-runner-skip-count!", 0, obj18);
                throw th23;
            }
        }
        Object obj21 = r;
        Object obj22 = obj21;
        try {
            test$Mnrunner test_mnrunner6 = (test$Mnrunner) obj21;
            Object obj23 = r;
            Object obj24 = obj23;
            try {
                $PcTestRunnerTotalCount$Ex(test_mnrunner6, AddOp.$Pl.apply2(Lit13, $PcTestRunnerTotalCount((test$Mnrunner) obj23)));
                Object obj25 = r;
                Object obj26 = obj25;
                try {
                    return Scheme.applyToArgs.apply2(testRunnerOnTestEnd((test$Mnrunner) obj25), r);
                } catch (ClassCastException e11) {
                    ClassCastException classCastException11 = e11;
                    Throwable th24 = th11;
                    new WrongType(classCastException11, "test-runner-on-test-end", 0, obj26);
                    throw th24;
                }
            } catch (ClassCastException e12) {
                ClassCastException classCastException12 = e12;
                Throwable th25 = th10;
                new WrongType(classCastException12, "%test-runner-total-count", 0, obj24);
                throw th25;
            }
        } catch (ClassCastException e13) {
            ClassCastException classCastException13 = e13;
            Throwable th26 = th9;
            new WrongType(classCastException13, "%test-runner-total-count!", 0, obj22);
            throw th26;
        }
    }

    public Object apply0(ModuleMethod moduleMethod) {
        ModuleMethod moduleMethod2 = moduleMethod;
        switch (moduleMethod2.selector) {
            case 53:
                return testRunnerNull();
            case 54:
                return testRunnerSimple();
            case 55:
                return testRunnerGet();
            case 56:
                return testRunnerCreate();
            case 72:
                return $PcTestReportResult();
            default:
                return super.apply0(moduleMethod2);
        }
    }

    static Object $PcTestSyntaxFile(Object form) {
        return std_syntax.syntaxSource(form);
    }

    static Pair $PcTestSourceLine2(Object obj) {
        Object form = obj;
        Object line = std_syntax.syntaxLine(form);
        Object file = $PcTestSyntaxFile(form);
        Object line$Mnpair = line != Boolean.FALSE ? LList.list1(C1245lists.cons(Lit5, line)) : LList.Empty;
        return C1245lists.cons(C1245lists.cons(Lit6, std_syntax.syntaxObject$To$Datum(form)), file != Boolean.FALSE ? C1245lists.cons(C1245lists.cons(Lit4, file), line$Mnpair) : line$Mnpair);
    }

    public static boolean $PcTestOnTestBegin(Object obj) {
        Throwable th;
        Throwable th2;
        int i;
        Object r = obj;
        Object $PcTestShouldExecute = $PcTestShouldExecute(r);
        Object obj2 = r;
        Object obj3 = obj2;
        try {
            Object apply2 = Scheme.applyToArgs.apply2(testRunnerOnTestBegin((test$Mnrunner) obj2), r);
            SimpleSymbol simpleSymbol = Lit2;
            Object obj4 = r;
            Object obj5 = obj4;
            try {
                Object p = C1245lists.assq(Lit1, testResultAlist((test$Mnrunner) obj4));
                if (simpleSymbol == (p != Boolean.FALSE ? C1245lists.cdr.apply1(p) : Boolean.FALSE)) {
                    i = 1;
                } else {
                    i = 0;
                }
                return (i + 1) & true;
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th3 = th2;
                new WrongType(classCastException, "test-result-alist", 0, obj5);
                throw th3;
            }
        } catch (ClassCastException e2) {
            ClassCastException classCastException2 = e2;
            Throwable th4 = th;
            new WrongType(classCastException2, "test-runner-on-test-begin", 0, obj3);
            throw th4;
        }
    }

    public static Object $PcTestOnTestEnd(Object obj, Object obj2) {
        Throwable th;
        Object obj3;
        SimpleSymbol simpleSymbol;
        Object r = obj;
        Object result = obj2;
        Object obj4 = r;
        SimpleSymbol simpleSymbol2 = Lit1;
        Object obj5 = r;
        Object obj6 = obj5;
        try {
            Object p = C1245lists.assq(Lit1, testResultAlist((test$Mnrunner) obj5));
            if (p != Boolean.FALSE) {
                obj3 = C1245lists.cdr.apply1(p);
            } else {
                obj3 = Boolean.FALSE;
            }
            if (obj3 == Lit3) {
                simpleSymbol = result != Boolean.FALSE ? Lit9 : Lit3;
            } else {
                simpleSymbol = result != Boolean.FALSE ? Lit12 : Lit14;
            }
            return testResultSet$Ex(obj4, simpleSymbol2, simpleSymbol);
        } catch (ClassCastException e) {
            ClassCastException classCastException = e;
            Throwable th2 = th;
            new WrongType(classCastException, "test-result-alist", 0, obj6);
            throw th2;
        }
    }

    public static Object testRunnerTestName(Object runner) {
        Throwable th;
        Object obj = runner;
        Object obj2 = obj;
        try {
            Object p = C1245lists.assq(Lit7, testResultAlist((test$Mnrunner) obj));
            return p != Boolean.FALSE ? C1245lists.cdr.apply1(p) : "";
        } catch (ClassCastException e) {
            ClassCastException classCastException = e;
            Throwable th2 = th;
            new WrongType(classCastException, "test-result-alist", 0, obj2);
            throw th2;
        }
    }

    /* compiled from: testing.scm */
    public class frame0 extends ModuleBody {
        Object error;
        final ModuleMethod lambda$Fn4;

        public frame0() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 1, (Object) null, 8194);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm:640");
            this.lambda$Fn4 = moduleMethod2;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            Boolean bool;
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            if (moduleMethod2.selector != 1) {
                return super.apply2(moduleMethod2, obj3, obj4);
            }
            if (lambda5(obj3, obj4)) {
                bool = Boolean.TRUE;
            } else {
                bool = Boolean.FALSE;
            }
            return bool;
        }

        /* access modifiers changed from: package-private */
        public boolean lambda5(Object obj, Object obj2) {
            Throwable th;
            boolean z;
            Object value = obj;
            Object expected = obj2;
            Object apply2 = Scheme.numGEq.apply2(value, AddOp.$Mn.apply2(expected, this.error));
            Object obj3 = apply2;
            try {
                boolean x = ((Boolean) apply2).booleanValue();
                if (x) {
                    z = ((Boolean) Scheme.numLEq.apply2(value, AddOp.$Pl.apply2(expected, this.error))).booleanValue();
                } else {
                    z = x;
                }
                return z;
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "x", -2, obj3);
                throw th2;
            }
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 1) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }
    }

    public static Procedure $PcTestApproximimate$Eq(Object error) {
        frame0 frame02;
        new frame0();
        frame0 frame03 = frame02;
        frame03.error = error;
        return frame03.lambda$Fn4;
    }

    static Object lambda16(Object obj) {
        Object x;
        Object x2 = obj;
        Pair list2 = LList.list2(x2, LList.list2(Lit15, $PcTestSourceLine2(x2)));
        Object[] allocVars = SyntaxPattern.allocVars(3, (Object[]) null);
        Pair pair = list2;
        if (Lit95.match(pair, allocVars, 0)) {
            x = Lit96.execute(allocVars, TemplateScope.make());
        } else if (Lit97.match(pair, allocVars, 0)) {
            x = Lit98.execute(allocVars, TemplateScope.make());
        } else {
            x = syntax_case.error("syntax-case", pair);
        }
        return x;
    }

    static Object lambda17(Object obj) {
        Object x;
        Object x2 = obj;
        Pair list2 = LList.list2(x2, LList.list2(Lit15, $PcTestSourceLine2(x2)));
        Object[] allocVars = SyntaxPattern.allocVars(4, (Object[]) null);
        Pair pair = list2;
        if (Lit100.match(pair, allocVars, 0)) {
            x = Lit101.execute(allocVars, TemplateScope.make());
        } else if (Lit102.match(pair, allocVars, 0)) {
            x = Lit103.execute(allocVars, TemplateScope.make());
        } else {
            x = syntax_case.error("syntax-case", pair);
        }
        return x;
    }

    static Object $PcTestComp2(Object comp, Object obj) {
        Object comp2;
        Object x = obj;
        Pair list3 = LList.list3(x, LList.list2(Lit15, $PcTestSourceLine2(x)), comp);
        Object[] allocVars = SyntaxPattern.allocVars(6, (Object[]) null);
        Pair pair = list3;
        if (Lit16.match(pair, allocVars, 0)) {
            comp2 = Lit17.execute(allocVars, TemplateScope.make());
        } else if (Lit18.match(pair, allocVars, 0)) {
            comp2 = Lit19.execute(allocVars, TemplateScope.make());
        } else {
            comp2 = syntax_case.error("syntax-case", pair);
        }
        return comp2;
    }

    static Object lambda18(Object x) {
        return $PcTestComp2(Lit105.execute((Object[]) null, TemplateScope.make()), x);
    }

    static Object lambda19(Object x) {
        return $PcTestComp2(Lit107.execute((Object[]) null, TemplateScope.make()), x);
    }

    static Object lambda20(Object x) {
        return $PcTestComp2(Lit109.execute((Object[]) null, TemplateScope.make()), x);
    }

    static Object lambda21(Object obj) {
        Object x;
        Object x2 = obj;
        Pair list2 = LList.list2(x2, LList.list2(Lit15, $PcTestSourceLine2(x2)));
        Object[] allocVars = SyntaxPattern.allocVars(6, (Object[]) null);
        Pair pair = list2;
        if (Lit111.match(pair, allocVars, 0)) {
            x = Lit112.execute(allocVars, TemplateScope.make());
        } else if (Lit113.match(pair, allocVars, 0)) {
            x = Lit114.execute(allocVars, TemplateScope.make());
        } else {
            x = syntax_case.error("syntax-case", pair);
        }
        return x;
    }

    static Object lambda22(Object obj) {
        Object x;
        Object x2 = obj;
        Pair list2 = LList.list2(x2, LList.list2(Lit15, $PcTestSourceLine2(x2)));
        Object[] allocVars = SyntaxPattern.allocVars(5, (Object[]) null);
        Pair pair = list2;
        if (Lit118.match(pair, allocVars, 0)) {
            x = Lit119.execute(allocVars, TemplateScope.make());
        } else if (Lit120.match(pair, allocVars, 0)) {
            x = Lit121.execute(allocVars, TemplateScope.make());
        } else if (Lit122.match(pair, allocVars, 0)) {
            x = Lit123.execute(allocVars, TemplateScope.make());
        } else {
            x = syntax_case.error("syntax-case", pair);
        }
        return x;
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        Throwable th7;
        Throwable th8;
        Throwable th9;
        Throwable th10;
        Throwable th11;
        Throwable th12;
        Throwable th13;
        Throwable th14;
        Throwable th15;
        Throwable th16;
        Throwable th17;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        switch (moduleMethod2.selector) {
            case 12:
                return isTestRunner(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 13:
                try {
                    return testRunnerPassCount((test$Mnrunner) obj2);
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th18 = th17;
                    new WrongType(classCastException, "test-runner-pass-count", 1, obj2);
                    throw th18;
                }
            case 15:
                try {
                    return testRunnerFailCount((test$Mnrunner) obj2);
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th19 = th16;
                    new WrongType(classCastException2, "test-runner-fail-count", 1, obj2);
                    throw th19;
                }
            case 17:
                try {
                    return testRunnerXpassCount((test$Mnrunner) obj2);
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th20 = th15;
                    new WrongType(classCastException3, "test-runner-xpass-count", 1, obj2);
                    throw th20;
                }
            case 19:
                try {
                    return testRunnerXfailCount((test$Mnrunner) obj2);
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th21 = th14;
                    new WrongType(classCastException4, "test-runner-xfail-count", 1, obj2);
                    throw th21;
                }
            case 21:
                try {
                    return testRunnerSkipCount((test$Mnrunner) obj2);
                } catch (ClassCastException e5) {
                    ClassCastException classCastException5 = e5;
                    Throwable th22 = th13;
                    new WrongType(classCastException5, "test-runner-skip-count", 1, obj2);
                    throw th22;
                }
            case 23:
                try {
                    return $PcTestRunnerSkipList((test$Mnrunner) obj2);
                } catch (ClassCastException e6) {
                    ClassCastException classCastException6 = e6;
                    Throwable th23 = th12;
                    new WrongType(classCastException6, "%test-runner-skip-list", 1, obj2);
                    throw th23;
                }
            case 25:
                try {
                    return $PcTestRunnerFailList((test$Mnrunner) obj2);
                } catch (ClassCastException e7) {
                    ClassCastException classCastException7 = e7;
                    Throwable th24 = th11;
                    new WrongType(classCastException7, "%test-runner-fail-list", 1, obj2);
                    throw th24;
                }
            case 27:
                try {
                    return testRunnerGroupStack((test$Mnrunner) obj2);
                } catch (ClassCastException e8) {
                    ClassCastException classCastException8 = e8;
                    Throwable th25 = th10;
                    new WrongType(classCastException8, "test-runner-group-stack", 1, obj2);
                    throw th25;
                }
            case 29:
                try {
                    return testRunnerOnTestBegin((test$Mnrunner) obj2);
                } catch (ClassCastException e9) {
                    ClassCastException classCastException9 = e9;
                    Throwable th26 = th9;
                    new WrongType(classCastException9, "test-runner-on-test-begin", 1, obj2);
                    throw th26;
                }
            case 31:
                try {
                    return testRunnerOnTestEnd((test$Mnrunner) obj2);
                } catch (ClassCastException e10) {
                    ClassCastException classCastException10 = e10;
                    Throwable th27 = th8;
                    new WrongType(classCastException10, "test-runner-on-test-end", 1, obj2);
                    throw th27;
                }
            case 33:
                try {
                    return testRunnerOnGroupBegin((test$Mnrunner) obj2);
                } catch (ClassCastException e11) {
                    ClassCastException classCastException11 = e11;
                    Throwable th28 = th7;
                    new WrongType(classCastException11, "test-runner-on-group-begin", 1, obj2);
                    throw th28;
                }
            case 35:
                try {
                    return testRunnerOnGroupEnd((test$Mnrunner) obj2);
                } catch (ClassCastException e12) {
                    ClassCastException classCastException12 = e12;
                    Throwable th29 = th6;
                    new WrongType(classCastException12, "test-runner-on-group-end", 1, obj2);
                    throw th29;
                }
            case 37:
                try {
                    return testRunnerOnFinal((test$Mnrunner) obj2);
                } catch (ClassCastException e13) {
                    ClassCastException classCastException13 = e13;
                    Throwable th30 = th5;
                    new WrongType(classCastException13, "test-runner-on-final", 1, obj2);
                    throw th30;
                }
            case 39:
                try {
                    return testRunnerOnBadCount((test$Mnrunner) obj2);
                } catch (ClassCastException e14) {
                    ClassCastException classCastException14 = e14;
                    Throwable th31 = th4;
                    new WrongType(classCastException14, "test-runner-on-bad-count", 1, obj2);
                    throw th31;
                }
            case 41:
                try {
                    return testRunnerOnBadEndName((test$Mnrunner) obj2);
                } catch (ClassCastException e15) {
                    ClassCastException classCastException15 = e15;
                    Throwable th32 = th3;
                    new WrongType(classCastException15, "test-runner-on-bad-end-name", 1, obj2);
                    throw th32;
                }
            case 43:
                try {
                    return testResultAlist((test$Mnrunner) obj2);
                } catch (ClassCastException e16) {
                    ClassCastException classCastException16 = e16;
                    Throwable th33 = th2;
                    new WrongType(classCastException16, "test-result-alist", 1, obj2);
                    throw th33;
                }
            case 45:
                try {
                    return testRunnerAuxValue((test$Mnrunner) obj2);
                } catch (ClassCastException e17) {
                    ClassCastException classCastException17 = e17;
                    Throwable th34 = th;
                    new WrongType(classCastException17, "test-runner-aux-value", 1, obj2);
                    throw th34;
                }
            case 47:
                testRunnerReset(obj2);
                return Values.empty;
            case 48:
                return testRunnerGroupPath(obj2);
            case 49:
                return $PcTestNullCallback(obj2);
            case 57:
                return $PcTestShouldExecute(obj2);
            case 60:
                return testOnGroupEndSimple(obj2);
            case 63:
                testOnFinalSimple(obj2);
                return Values.empty;
            case 65:
                return testOnTestBeginSimple(obj2);
            case 66:
                return testOnTestEndSimple(obj2);
            case 68:
                testResultClear(obj2);
                return Values.empty;
            case 73:
                return $PcTestOnTestBegin(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 75:
                return testRunnerTestName(obj2);
            case 76:
                return $PcTestApproximimate$Eq(obj2);
            case 77:
                return lambda16(obj2);
            case 78:
                return lambda17(obj2);
            case 79:
                return lambda18(obj2);
            case 80:
                return lambda19(obj2);
            case 81:
                return lambda20(obj2);
            case 82:
                return lambda21(obj2);
            case 83:
                return lambda22(obj2);
            case 88:
                return $PcTestAsSpecifier(obj2);
            case 89:
                return testMatchName(obj2);
            case 90:
                return testReadEvalString(obj2);
            default:
                return super.apply1(moduleMethod2, obj2);
        }
    }

    public static Object testApply$V(Object first, Object[] argsArray) {
        frame1 frame12;
        Throwable th;
        Object first2;
        Throwable th2;
        Throwable th3;
        Pair cons;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        new frame1();
        frame1 frame13 = frame12;
        frame13.first = first;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame13.rest = makeList;
        if (isTestRunner(frame13.first)) {
            frame13.saved$Mnrunner$1 = ((Procedure) test$Mnrunner$Mncurrent).apply0();
            first2 = misc.dynamicWind(frame13.lambda$Fn5, frame13.lambda$Fn6, frame13.lambda$Fn7);
        } else {
            Object r = ((Procedure) test$Mnrunner$Mncurrent).apply0();
            if (r != Boolean.FALSE) {
                Object obj = r;
                Object obj2 = obj;
                try {
                    Object run$Mnlist = $PcTestRunnerRunList((test$Mnrunner) obj);
                    if (C1245lists.isNull(frame13.rest)) {
                        Object obj3 = r;
                        Object obj4 = obj3;
                        try {
                            test$Mnrunner test_mnrunner = (test$Mnrunner) obj3;
                            Object obj5 = run$Mnlist;
                            Object obj6 = obj5;
                            try {
                                $PcTestRunnerRunList$Ex(test_mnrunner, C1245lists.reverse$Ex((LList) obj5));
                                first2 = Scheme.applyToArgs.apply1(frame13.first);
                            } catch (ClassCastException e) {
                                ClassCastException classCastException = e;
                                Throwable th7 = th6;
                                new WrongType(classCastException, "reverse!", 1, obj6);
                                throw th7;
                            }
                        } catch (ClassCastException e2) {
                            ClassCastException classCastException2 = e2;
                            Throwable th8 = th5;
                            new WrongType(classCastException2, "%test-runner-run-list!", 0, obj4);
                            throw th8;
                        }
                    } else {
                        Object obj7 = r;
                        Object obj8 = obj7;
                        try {
                            test$Mnrunner test_mnrunner2 = (test$Mnrunner) obj7;
                            if (run$Mnlist == Boolean.TRUE) {
                                cons = LList.list1(frame13.first);
                            } else {
                                cons = C1245lists.cons(frame13.first, run$Mnlist);
                            }
                            $PcTestRunnerRunList$Ex(test_mnrunner2, cons);
                            Object apply2 = Scheme.apply.apply2(test$Mnapply, frame13.rest);
                            Object obj9 = r;
                            Object obj10 = obj9;
                            try {
                                $PcTestRunnerRunList$Ex((test$Mnrunner) obj9, run$Mnlist);
                                first2 = Values.empty;
                            } catch (ClassCastException e3) {
                                ClassCastException classCastException3 = e3;
                                Throwable th9 = th4;
                                new WrongType(classCastException3, "%test-runner-run-list!", 0, obj10);
                                throw th9;
                            }
                        } catch (ClassCastException e4) {
                            ClassCastException classCastException4 = e4;
                            Throwable th10 = th3;
                            new WrongType(classCastException4, "%test-runner-run-list!", 0, obj8);
                            throw th10;
                        }
                    }
                } catch (ClassCastException e5) {
                    ClassCastException classCastException5 = e5;
                    Throwable th11 = th2;
                    new WrongType(classCastException5, "%test-runner-run-list", 0, obj2);
                    throw th11;
                }
            } else {
                frame13.f234r = testRunnerCreate();
                frame13.saved$Mnrunner = ((Procedure) test$Mnrunner$Mncurrent).apply0();
                Object dynamicWind = misc.dynamicWind(frame13.lambda$Fn8, frame13.lambda$Fn9, frame13.lambda$Fn10);
                ApplyToArgs applyToArgs = Scheme.applyToArgs;
                Object obj11 = frame13.f234r;
                Object obj12 = obj11;
                try {
                    first2 = applyToArgs.apply2(testRunnerOnFinal((test$Mnrunner) obj11), frame13.f234r);
                } catch (ClassCastException e6) {
                    ClassCastException classCastException6 = e6;
                    Throwable th12 = th;
                    new WrongType(classCastException6, "test-runner-on-final", 0, obj12);
                    throw th12;
                }
            }
        }
        return first2;
    }

    /* compiled from: testing.scm */
    public class frame1 extends ModuleBody {
        Object first;
        final ModuleMethod lambda$Fn10;
        final ModuleMethod lambda$Fn5;
        final ModuleMethod lambda$Fn6;
        final ModuleMethod lambda$Fn7;
        final ModuleMethod lambda$Fn8;
        final ModuleMethod lambda$Fn9;

        /* renamed from: r */
        Object f234r;
        LList rest;
        Object saved$Mnrunner;
        Object saved$Mnrunner$1;

        public frame1() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            ModuleMethod moduleMethod3;
            ModuleMethod moduleMethod4;
            ModuleMethod moduleMethod5;
            ModuleMethod moduleMethod6;
            new ModuleMethod(this, 2, (Object) null, 0);
            this.lambda$Fn5 = moduleMethod;
            new ModuleMethod(this, 3, (Object) null, 0);
            this.lambda$Fn6 = moduleMethod2;
            new ModuleMethod(this, 4, (Object) null, 0);
            ModuleMethod moduleMethod7 = moduleMethod3;
            moduleMethod7.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm:897");
            this.lambda$Fn7 = moduleMethod7;
            new ModuleMethod(this, 5, (Object) null, 0);
            this.lambda$Fn8 = moduleMethod4;
            new ModuleMethod(this, 6, (Object) null, 0);
            this.lambda$Fn9 = moduleMethod5;
            new ModuleMethod(this, 7, (Object) null, 0);
            ModuleMethod moduleMethod8 = moduleMethod6;
            moduleMethod8.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm:897");
            this.lambda$Fn10 = moduleMethod8;
        }

        /* access modifiers changed from: package-private */
        public Object lambda6() {
            return ((Procedure) testing.test$Mnrunner$Mncurrent).apply1(this.first);
        }

        /* access modifiers changed from: package-private */
        public Object lambda7() {
            return Scheme.apply.apply2(testing.test$Mnapply, this.rest);
        }

        /* access modifiers changed from: package-private */
        public Object lambda10() {
            return Scheme.apply.apply3(testing.test$Mnapply, this.first, this.rest);
        }

        /* access modifiers changed from: package-private */
        public Object lambda9() {
            return ((Procedure) testing.test$Mnrunner$Mncurrent).apply1(this.f234r);
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            switch (moduleMethod2.selector) {
                case 2:
                    return lambda6();
                case 3:
                    return lambda7();
                case 4:
                    return lambda8();
                case 5:
                    return lambda9();
                case 6:
                    return lambda10();
                case 7:
                    return lambda11();
                default:
                    return super.apply0(moduleMethod2);
            }
        }

        /* access modifiers changed from: package-private */
        public Object lambda11() {
            return ((Procedure) testing.test$Mnrunner$Mncurrent).apply1(this.saved$Mnrunner);
        }

        /* access modifiers changed from: package-private */
        public Object lambda8() {
            return ((Procedure) testing.test$Mnrunner$Mncurrent).apply1(this.saved$Mnrunner$1);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            switch (moduleMethod2.selector) {
                case 2:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 3:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 4:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 5:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 6:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 7:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                default:
                    return super.match0(moduleMethod2, callContext2);
            }
        }
    }

    public static Procedure $PcTestMatchNth(Object n, Object count) {
        frame2 frame22;
        new frame2();
        frame2 frame23 = frame22;
        frame23.f236n = n;
        frame23.count = count;
        frame23.f235i = Lit0;
        return frame23.lambda$Fn11;
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        Throwable th7;
        Throwable th8;
        Throwable th9;
        Throwable th10;
        Throwable th11;
        Throwable th12;
        Throwable th13;
        Throwable th14;
        Throwable th15;
        Throwable th16;
        Throwable th17;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        switch (moduleMethod2.selector) {
            case 14:
                try {
                    testRunnerPassCount$Ex((test$Mnrunner) obj3, obj4);
                    return Values.empty;
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th18 = th17;
                    new WrongType(classCastException, "test-runner-pass-count!", 1, obj3);
                    throw th18;
                }
            case 16:
                try {
                    testRunnerFailCount$Ex((test$Mnrunner) obj3, obj4);
                    return Values.empty;
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th19 = th16;
                    new WrongType(classCastException2, "test-runner-fail-count!", 1, obj3);
                    throw th19;
                }
            case 18:
                try {
                    testRunnerXpassCount$Ex((test$Mnrunner) obj3, obj4);
                    return Values.empty;
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th20 = th15;
                    new WrongType(classCastException3, "test-runner-xpass-count!", 1, obj3);
                    throw th20;
                }
            case 20:
                try {
                    testRunnerXfailCount$Ex((test$Mnrunner) obj3, obj4);
                    return Values.empty;
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th21 = th14;
                    new WrongType(classCastException4, "test-runner-xfail-count!", 1, obj3);
                    throw th21;
                }
            case 22:
                try {
                    testRunnerSkipCount$Ex((test$Mnrunner) obj3, obj4);
                    return Values.empty;
                } catch (ClassCastException e5) {
                    ClassCastException classCastException5 = e5;
                    Throwable th22 = th13;
                    new WrongType(classCastException5, "test-runner-skip-count!", 1, obj3);
                    throw th22;
                }
            case 24:
                try {
                    $PcTestRunnerSkipList$Ex((test$Mnrunner) obj3, obj4);
                    return Values.empty;
                } catch (ClassCastException e6) {
                    ClassCastException classCastException6 = e6;
                    Throwable th23 = th12;
                    new WrongType(classCastException6, "%test-runner-skip-list!", 1, obj3);
                    throw th23;
                }
            case 26:
                try {
                    $PcTestRunnerFailList$Ex((test$Mnrunner) obj3, obj4);
                    return Values.empty;
                } catch (ClassCastException e7) {
                    ClassCastException classCastException7 = e7;
                    Throwable th24 = th11;
                    new WrongType(classCastException7, "%test-runner-fail-list!", 1, obj3);
                    throw th24;
                }
            case 28:
                try {
                    testRunnerGroupStack$Ex((test$Mnrunner) obj3, obj4);
                    return Values.empty;
                } catch (ClassCastException e8) {
                    ClassCastException classCastException8 = e8;
                    Throwable th25 = th10;
                    new WrongType(classCastException8, "test-runner-group-stack!", 1, obj3);
                    throw th25;
                }
            case 30:
                try {
                    testRunnerOnTestBegin$Ex((test$Mnrunner) obj3, obj4);
                    return Values.empty;
                } catch (ClassCastException e9) {
                    ClassCastException classCastException9 = e9;
                    Throwable th26 = th9;
                    new WrongType(classCastException9, "test-runner-on-test-begin!", 1, obj3);
                    throw th26;
                }
            case 32:
                try {
                    testRunnerOnTestEnd$Ex((test$Mnrunner) obj3, obj4);
                    return Values.empty;
                } catch (ClassCastException e10) {
                    ClassCastException classCastException10 = e10;
                    Throwable th27 = th8;
                    new WrongType(classCastException10, "test-runner-on-test-end!", 1, obj3);
                    throw th27;
                }
            case 34:
                try {
                    testRunnerOnGroupBegin$Ex((test$Mnrunner) obj3, obj4);
                    return Values.empty;
                } catch (ClassCastException e11) {
                    ClassCastException classCastException11 = e11;
                    Throwable th28 = th7;
                    new WrongType(classCastException11, "test-runner-on-group-begin!", 1, obj3);
                    throw th28;
                }
            case 36:
                try {
                    testRunnerOnGroupEnd$Ex((test$Mnrunner) obj3, obj4);
                    return Values.empty;
                } catch (ClassCastException e12) {
                    ClassCastException classCastException12 = e12;
                    Throwable th29 = th6;
                    new WrongType(classCastException12, "test-runner-on-group-end!", 1, obj3);
                    throw th29;
                }
            case 38:
                try {
                    testRunnerOnFinal$Ex((test$Mnrunner) obj3, obj4);
                    return Values.empty;
                } catch (ClassCastException e13) {
                    ClassCastException classCastException13 = e13;
                    Throwable th30 = th5;
                    new WrongType(classCastException13, "test-runner-on-final!", 1, obj3);
                    throw th30;
                }
            case 40:
                try {
                    testRunnerOnBadCount$Ex((test$Mnrunner) obj3, obj4);
                    return Values.empty;
                } catch (ClassCastException e14) {
                    ClassCastException classCastException14 = e14;
                    Throwable th31 = th4;
                    new WrongType(classCastException14, "test-runner-on-bad-count!", 1, obj3);
                    throw th31;
                }
            case 42:
                try {
                    testRunnerOnBadEndName$Ex((test$Mnrunner) obj3, obj4);
                    return Values.empty;
                } catch (ClassCastException e15) {
                    ClassCastException classCastException15 = e15;
                    Throwable th32 = th3;
                    new WrongType(classCastException15, "test-runner-on-bad-end-name!", 1, obj3);
                    throw th32;
                }
            case 44:
                try {
                    testResultAlist$Ex((test$Mnrunner) obj3, obj4);
                    return Values.empty;
                } catch (ClassCastException e16) {
                    ClassCastException classCastException16 = e16;
                    Throwable th33 = th2;
                    new WrongType(classCastException16, "test-result-alist!", 1, obj3);
                    throw th33;
                }
            case 46:
                try {
                    testRunnerAuxValue$Ex((test$Mnrunner) obj3, obj4);
                    return Values.empty;
                } catch (ClassCastException e17) {
                    ClassCastException classCastException17 = e17;
                    Throwable th34 = th;
                    new WrongType(classCastException17, "test-runner-aux-value!", 1, obj3);
                    throw th34;
                }
            case 58:
                $PcTestBegin(obj3, obj4);
                return Values.empty;
            case 64:
                return $PcTestEnd(obj3, obj4);
            case 69:
                testResultRemove(obj3, obj4);
                return Values.empty;
            case 74:
                return $PcTestOnTestEnd(obj3, obj4);
            case 85:
                return $PcTestMatchNth(obj3, obj4);
            default:
                return super.apply2(moduleMethod2, obj3, obj4);
        }
    }

    /* compiled from: testing.scm */
    public class frame2 extends ModuleBody {
        Object count;

        /* renamed from: i */
        Object f235i;
        final ModuleMethod lambda$Fn11;

        /* renamed from: n */
        Object f236n;

        public frame2() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 8, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm:903");
            this.lambda$Fn11 = moduleMethod2;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            Boolean bool;
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector != 8) {
                return super.apply1(moduleMethod2, obj2);
            }
            if (lambda12(obj2)) {
                bool = Boolean.TRUE;
            } else {
                bool = Boolean.FALSE;
            }
            return bool;
        }

        /* access modifiers changed from: package-private */
        public boolean lambda12(Object obj) {
            Throwable th;
            Object obj2 = obj;
            this.f235i = AddOp.$Pl.apply2(this.f235i, testing.Lit13);
            Object apply2 = Scheme.numGEq.apply2(this.f235i, this.f236n);
            Object obj3 = apply2;
            try {
                boolean x = ((Boolean) apply2).booleanValue();
                return x ? ((Boolean) Scheme.numLss.apply2(this.f235i, AddOp.$Pl.apply2(this.f236n, this.count))).booleanValue() : x;
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "x", -2, obj3);
                throw th2;
            }
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 8) {
                return super.match1(moduleMethod2, obj2, callContext2);
            }
            callContext2.value1 = obj2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 1;
            return 0;
        }
    }

    /* compiled from: testing.scm */
    public class frame3 extends ModuleBody {
        final ModuleMethod lambda$Fn12;
        LList pred$Mnlist;

        public frame3() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 9, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm:915");
            this.lambda$Fn12 = moduleMethod2;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector == 9) {
                return lambda13(obj2);
            }
            return super.apply1(moduleMethod2, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda13(Object obj) {
            Object runner = obj;
            Boolean result = Boolean.TRUE;
            Object obj2 = this.pred$Mnlist;
            while (true) {
                Object obj3 = obj2;
                if (C1245lists.isNull(obj3)) {
                    return result;
                }
                if (Scheme.applyToArgs.apply2(C1245lists.car.apply1(obj3), runner) == Boolean.FALSE) {
                    result = Boolean.FALSE;
                }
                obj2 = C1245lists.cdr.apply1(obj3);
            }
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 9) {
                return super.match1(moduleMethod2, obj2, callContext2);
            }
            callContext2.value1 = obj2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 1;
            return 0;
        }
    }

    public static Procedure $PcTestMatchAll$V(Object[] argsArray) {
        frame3 frame32;
        new frame3();
        frame3 frame33 = frame32;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame33.pred$Mnlist = makeList;
        return frame33.lambda$Fn12;
    }

    /* compiled from: testing.scm */
    public class frame4 extends ModuleBody {
        final ModuleMethod lambda$Fn13;
        LList pred$Mnlist;

        public frame4() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 10, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm:931");
            this.lambda$Fn13 = moduleMethod2;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector == 10) {
                return lambda14(obj2);
            }
            return super.apply1(moduleMethod2, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda14(Object obj) {
            Object runner = obj;
            Boolean result = Boolean.FALSE;
            Object obj2 = this.pred$Mnlist;
            while (true) {
                Object obj3 = obj2;
                if (C1245lists.isNull(obj3)) {
                    return result;
                }
                if (Scheme.applyToArgs.apply2(C1245lists.car.apply1(obj3), runner) != Boolean.FALSE) {
                    result = Boolean.TRUE;
                }
                obj2 = C1245lists.cdr.apply1(obj3);
            }
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 10) {
                return super.match1(moduleMethod2, obj2, callContext2);
            }
            callContext2.value1 = obj2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 1;
            return 0;
        }
    }

    public static Procedure $PcTestMatchAny$V(Object[] argsArray) {
        frame4 frame42;
        new frame4();
        frame4 frame43 = frame42;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame43.pred$Mnlist = makeList;
        return frame43.lambda$Fn13;
    }

    public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object[] objArr2 = objArr;
        switch (moduleMethod2.selector) {
            case 70:
                return testResultKind$V(objArr2);
            case 71:
                return isTestPassed$V(objArr2);
            case 84:
                Object obj = objArr2[0];
                int length = objArr2.length - 1;
                Object[] objArr3 = new Object[length];
                while (true) {
                    length--;
                    if (length < 0) {
                        return testApply$V(obj, objArr3);
                    }
                    Object[] objArr4 = objArr3;
                    objArr3 = objArr4;
                    objArr4[length] = objArr2[length + 1];
                }
            case 86:
                return $PcTestMatchAll$V(objArr2);
            case 87:
                return $PcTestMatchAny$V(objArr2);
            default:
                return super.applyN(moduleMethod2, objArr2);
        }
    }

    public static Object $PcTestAsSpecifier(Object obj) {
        Object specifier;
        Object specifier2 = obj;
        if (misc.isProcedure(specifier2)) {
            specifier = specifier2;
        } else if (numbers.isInteger(specifier2)) {
            specifier = $PcTestMatchNth(Lit13, specifier2);
        } else if (strings.isString(specifier2)) {
            specifier = testMatchName(specifier2);
        } else {
            specifier = misc.error$V("not a valid test specifier", new Object[0]);
        }
        return specifier;
    }

    /* compiled from: testing.scm */
    public class frame5 extends ModuleBody {
        final ModuleMethod lambda$Fn14;
        Object name;

        public frame5() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 11, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/testing.scm:971");
            this.lambda$Fn14 = moduleMethod2;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            Boolean bool;
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector != 11) {
                return super.apply1(moduleMethod2, obj2);
            }
            if (lambda15(obj2)) {
                bool = Boolean.TRUE;
            } else {
                bool = Boolean.FALSE;
            }
            return bool;
        }

        /* access modifiers changed from: package-private */
        public boolean lambda15(Object runner) {
            return IsEqual.apply(this.name, testing.testRunnerTestName(runner));
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 11) {
                return super.match1(moduleMethod2, obj2, callContext2);
            }
            callContext2.value1 = obj2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 1;
            return 0;
        }
    }

    public static Procedure testMatchName(Object name) {
        frame5 frame52;
        new frame5();
        frame5 frame53 = frame52;
        frame53.name = name;
        return frame53.lambda$Fn14;
    }

    public static Object testReadEvalString(Object string) {
        Throwable th;
        Object string2;
        Object obj = string;
        Object obj2 = obj;
        try {
            InPort port = ports.openInputString((CharSequence) obj);
            Object form = ports.read(port);
            if (ports.isEofObject(readchar.readChar.apply1(port))) {
                string2 = Eval.eval.apply1(form);
            } else {
                string2 = misc.error$V("(not at eof)", new Object[0]);
            }
            return string2;
        } catch (ClassCastException e) {
            ClassCastException classCastException = e;
            Throwable th2 = th;
            new WrongType(classCastException, "open-input-string", 1, obj2);
            throw th2;
        }
    }
}

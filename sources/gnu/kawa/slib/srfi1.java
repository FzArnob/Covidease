package gnu.kawa.slib;

import android.support.p000v4.app.FragmentTransaction;
import com.google.appinventor.components.common.ComponentConstants;
import gnu.expr.GenericProc;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.Apply;
import gnu.kawa.functions.Map;
import gnu.kawa.functions.MultiplyOp;
import gnu.kawa.lispexpr.LangObjType;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.math.Numeric;
import kawa.lang.Continuation;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lib.C1245lists;
import kawa.lib.misc;
import kawa.lib.numbers;
import kawa.standard.Scheme;
import kawa.standard.append;
import kawa.standard.call_with_values;

/* compiled from: srfi1.scm */
public class srfi1 extends ModuleBody {
    public static final Macro $Pcevery = Macro.make(Lit84, Lit85, $instance);
    public static final int $Pcprovide$Pclist$Mnlib = 123;
    public static final int $Pcprovide$Pcsrfi$Mn1 = 123;
    public static final srfi1 $instance;
    static final IntNum Lit0 = IntNum.make(0);
    static final IntNum Lit1 = IntNum.make(1);
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit100;
    static final SimpleSymbol Lit101;
    static final SimpleSymbol Lit102;
    static final SimpleSymbol Lit103;
    static final SimpleSymbol Lit104;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit12;
    static final SimpleSymbol Lit13;
    static final SimpleSymbol Lit14;
    static final SimpleSymbol Lit15;
    static final SimpleSymbol Lit16;
    static final SimpleSymbol Lit17;
    static final SimpleSymbol Lit18;
    static final SimpleSymbol Lit19;
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
    static final SimpleSymbol Lit71;
    static final SimpleSymbol Lit72;
    static final SimpleSymbol Lit73;
    static final SimpleSymbol Lit74;
    static final SimpleSymbol Lit75;
    static final SimpleSymbol Lit76;
    static final SimpleSymbol Lit77;
    static final SimpleSymbol Lit78;
    static final SimpleSymbol Lit79;
    static final SimpleSymbol Lit8;
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
    static final SimpleSymbol Lit90;
    static final SimpleSymbol Lit91;
    static final SimpleSymbol Lit92;
    static final SimpleSymbol Lit93;
    static final SimpleSymbol Lit94;
    static final SimpleSymbol Lit95;
    static final SimpleSymbol Lit96;
    static final SimpleSymbol Lit97;
    static final SimpleSymbol Lit98;
    static final SimpleSymbol Lit99;
    public static final ModuleMethod alist$Mncons;
    public static final ModuleMethod alist$Mncopy;
    public static final ModuleMethod alist$Mndelete;
    public static final ModuleMethod alist$Mndelete$Ex;
    public static final ModuleMethod any;
    public static final ModuleMethod append$Ex;
    public static final ModuleMethod append$Mnmap;
    public static final ModuleMethod append$Mnmap$Ex;
    public static final ModuleMethod append$Mnreverse;
    public static final ModuleMethod append$Mnreverse$Ex;

    /* renamed from: break  reason: not valid java name */
    public static final ModuleMethod f607break;
    public static final ModuleMethod break$Ex;
    public static final ModuleMethod car$Plcdr;
    public static final ModuleMethod circular$Mnlist;
    public static final ModuleMethod circular$Mnlist$Qu;
    public static final ModuleMethod concatenate;
    public static final ModuleMethod concatenate$Ex;
    public static final ModuleMethod cons$St;
    public static final ModuleMethod count;
    public static final ModuleMethod delete;
    public static final ModuleMethod delete$Ex;
    public static final ModuleMethod delete$Mnduplicates;
    public static final ModuleMethod delete$Mnduplicates$Ex;
    public static final ModuleMethod dotted$Mnlist$Qu;
    public static final ModuleMethod drop;
    public static final ModuleMethod drop$Mnright;
    public static final ModuleMethod drop$Mnright$Ex;
    public static final ModuleMethod drop$Mnwhile;
    public static final ModuleMethod eighth;
    public static final ModuleMethod every;
    public static final ModuleMethod fifth;
    public static final ModuleMethod filter;
    public static final ModuleMethod filter$Ex;
    public static final ModuleMethod filter$Mnmap;
    public static final ModuleMethod find;
    public static final ModuleMethod find$Mntail;
    public static GenericProc first;
    public static final ModuleMethod fold;
    public static final ModuleMethod fold$Mnright;
    public static GenericProc fourth;
    public static final ModuleMethod iota;
    static final ModuleMethod lambda$Fn64;
    static final ModuleMethod lambda$Fn78;
    public static final ModuleMethod last;
    public static final ModuleMethod last$Mnpair;
    public static final ModuleMethod length$Pl;
    public static final ModuleMethod list$Eq;
    public static final ModuleMethod list$Mncopy;
    public static final ModuleMethod list$Mnindex;
    public static final ModuleMethod list$Mntabulate;
    public static final ModuleMethod lset$Eq;
    public static final ModuleMethod lset$Ls$Eq;
    public static final ModuleMethod lset$Mnadjoin;
    public static final ModuleMethod lset$Mndiff$Plintersection;
    public static final ModuleMethod lset$Mndiff$Plintersection$Ex;
    public static final ModuleMethod lset$Mndifference;
    public static final ModuleMethod lset$Mndifference$Ex;
    public static final ModuleMethod lset$Mnintersection;
    public static final ModuleMethod lset$Mnintersection$Ex;
    public static final ModuleMethod lset$Mnunion;
    public static final ModuleMethod lset$Mnunion$Ex;
    public static final ModuleMethod lset$Mnxor;
    public static final ModuleMethod lset$Mnxor$Ex;
    public static final ModuleMethod make$Mnlist;
    public static final ModuleMethod map$Ex;
    public static Map map$Mnin$Mnorder;
    public static final ModuleMethod ninth;
    public static final ModuleMethod not$Mnpair$Qu;
    public static final ModuleMethod null$Mnlist$Qu;
    public static final ModuleMethod pair$Mnfold;
    public static final ModuleMethod pair$Mnfold$Mnright;
    public static final ModuleMethod pair$Mnfor$Mneach;
    public static final ModuleMethod partition;
    public static final ModuleMethod partition$Ex;
    public static final ModuleMethod proper$Mnlist$Qu;
    public static final ModuleMethod reduce;
    public static final ModuleMethod reduce$Mnright;
    public static final ModuleMethod remove;
    public static final ModuleMethod remove$Ex;
    public static GenericProc second;
    public static final ModuleMethod seventh;
    public static final ModuleMethod sixth;
    public static final ModuleMethod span;
    public static final ModuleMethod span$Ex;
    public static final ModuleMethod split$Mnat;
    public static final ModuleMethod split$Mnat$Ex;
    public static final ModuleMethod take;
    public static final ModuleMethod take$Ex;
    public static final ModuleMethod take$Mnright;
    public static final ModuleMethod take$Mnwhile;
    public static final ModuleMethod take$Mnwhile$Ex;
    public static final ModuleMethod tenth;
    public static GenericProc third;
    public static final ModuleMethod unfold;
    public static final ModuleMethod unfold$Mnright;
    public static final ModuleMethod unzip1;
    public static final ModuleMethod unzip2;
    public static final ModuleMethod unzip3;
    public static final ModuleMethod unzip4;
    public static final ModuleMethod unzip5;
    public static final ModuleMethod xcons;
    public static final ModuleMethod zip;

    /* compiled from: srfi1.scm */
    public class frame62 extends ModuleBody {
        Object cars$Mnfinal;

        public frame62() {
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
        SimpleSymbol simpleSymbol11;
        SimpleSymbol simpleSymbol12;
        SimpleSymbol simpleSymbol13;
        SimpleSymbol simpleSymbol14;
        SimpleSymbol simpleSymbol15;
        SimpleSymbol simpleSymbol16;
        SimpleSymbol simpleSymbol17;
        SimpleSymbol simpleSymbol18;
        SimpleSymbol simpleSymbol19;
        SyntaxRules syntaxRules;
        SimpleSymbol simpleSymbol20;
        SyntaxRule syntaxRule;
        SyntaxPattern syntaxPattern;
        SimpleSymbol simpleSymbol21;
        SimpleSymbol simpleSymbol22;
        SimpleSymbol simpleSymbol23;
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
        SimpleSymbol simpleSymbol39;
        SimpleSymbol simpleSymbol40;
        SimpleSymbol simpleSymbol41;
        SimpleSymbol simpleSymbol42;
        SimpleSymbol simpleSymbol43;
        SimpleSymbol simpleSymbol44;
        SimpleSymbol simpleSymbol45;
        SimpleSymbol simpleSymbol46;
        SimpleSymbol simpleSymbol47;
        SimpleSymbol simpleSymbol48;
        SimpleSymbol simpleSymbol49;
        SimpleSymbol simpleSymbol50;
        SimpleSymbol simpleSymbol51;
        SimpleSymbol simpleSymbol52;
        SimpleSymbol simpleSymbol53;
        SimpleSymbol simpleSymbol54;
        SimpleSymbol simpleSymbol55;
        SimpleSymbol simpleSymbol56;
        SimpleSymbol simpleSymbol57;
        SimpleSymbol simpleSymbol58;
        SimpleSymbol simpleSymbol59;
        SimpleSymbol simpleSymbol60;
        SimpleSymbol simpleSymbol61;
        SimpleSymbol simpleSymbol62;
        SimpleSymbol simpleSymbol63;
        SimpleSymbol simpleSymbol64;
        SimpleSymbol simpleSymbol65;
        SimpleSymbol simpleSymbol66;
        SimpleSymbol simpleSymbol67;
        SimpleSymbol simpleSymbol68;
        SimpleSymbol simpleSymbol69;
        SimpleSymbol simpleSymbol70;
        SimpleSymbol simpleSymbol71;
        SimpleSymbol simpleSymbol72;
        SimpleSymbol simpleSymbol73;
        SimpleSymbol simpleSymbol74;
        SimpleSymbol simpleSymbol75;
        SimpleSymbol simpleSymbol76;
        SimpleSymbol simpleSymbol77;
        SimpleSymbol simpleSymbol78;
        SimpleSymbol simpleSymbol79;
        SimpleSymbol simpleSymbol80;
        SimpleSymbol simpleSymbol81;
        SimpleSymbol simpleSymbol82;
        SimpleSymbol simpleSymbol83;
        SimpleSymbol simpleSymbol84;
        SimpleSymbol simpleSymbol85;
        SimpleSymbol simpleSymbol86;
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
        srfi1 srfi1;
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
        ModuleMethod moduleMethod66;
        ModuleMethod moduleMethod67;
        ModuleMethod moduleMethod68;
        ModuleMethod moduleMethod69;
        ModuleMethod moduleMethod70;
        ModuleMethod moduleMethod71;
        ModuleMethod moduleMethod72;
        ModuleMethod moduleMethod73;
        ModuleMethod moduleMethod74;
        ModuleMethod moduleMethod75;
        ModuleMethod moduleMethod76;
        ModuleMethod moduleMethod77;
        ModuleMethod moduleMethod78;
        ModuleMethod moduleMethod79;
        ModuleMethod moduleMethod80;
        ModuleMethod moduleMethod81;
        ModuleMethod moduleMethod82;
        ModuleMethod moduleMethod83;
        ModuleMethod moduleMethod84;
        ModuleMethod moduleMethod85;
        ModuleMethod moduleMethod86;
        ModuleMethod moduleMethod87;
        ModuleMethod moduleMethod88;
        ModuleMethod moduleMethod89;
        ModuleMethod moduleMethod90;
        ModuleMethod moduleMethod91;
        ModuleMethod moduleMethod92;
        ModuleMethod moduleMethod93;
        ModuleMethod moduleMethod94;
        ModuleMethod moduleMethod95;
        ModuleMethod moduleMethod96;
        ModuleMethod moduleMethod97;
        new SimpleSymbol("cdr");
        Lit104 = (SimpleSymbol) simpleSymbol.readResolve();
        new SimpleSymbol("car");
        Lit103 = (SimpleSymbol) simpleSymbol2.readResolve();
        new SimpleSymbol("lp");
        Lit102 = (SimpleSymbol) simpleSymbol3.readResolve();
        new SimpleSymbol("head");
        Lit101 = (SimpleSymbol) simpleSymbol4.readResolve();
        new SimpleSymbol("tail");
        Lit100 = (SimpleSymbol) simpleSymbol5.readResolve();
        new SimpleSymbol("lset-diff+intersection!");
        Lit99 = (SimpleSymbol) simpleSymbol6.readResolve();
        new SimpleSymbol("lset-diff+intersection");
        Lit98 = (SimpleSymbol) simpleSymbol7.readResolve();
        new SimpleSymbol("lset-xor!");
        Lit97 = (SimpleSymbol) simpleSymbol8.readResolve();
        new SimpleSymbol("lset-xor");
        Lit96 = (SimpleSymbol) simpleSymbol9.readResolve();
        new SimpleSymbol("lset-difference!");
        Lit95 = (SimpleSymbol) simpleSymbol10.readResolve();
        new SimpleSymbol("lset-difference");
        Lit94 = (SimpleSymbol) simpleSymbol11.readResolve();
        new SimpleSymbol("lset-intersection!");
        Lit93 = (SimpleSymbol) simpleSymbol12.readResolve();
        new SimpleSymbol("lset-intersection");
        Lit92 = (SimpleSymbol) simpleSymbol13.readResolve();
        new SimpleSymbol("lset-union!");
        Lit91 = (SimpleSymbol) simpleSymbol14.readResolve();
        new SimpleSymbol("lset-union");
        Lit90 = (SimpleSymbol) simpleSymbol15.readResolve();
        new SimpleSymbol("lset-adjoin");
        Lit89 = (SimpleSymbol) simpleSymbol16.readResolve();
        new SimpleSymbol("lset=");
        Lit88 = (SimpleSymbol) simpleSymbol17.readResolve();
        new SimpleSymbol("lset<=");
        Lit87 = (SimpleSymbol) simpleSymbol18.readResolve();
        new SimpleSymbol("list-index");
        Lit86 = (SimpleSymbol) simpleSymbol19.readResolve();
        SyntaxRules syntaxRules2 = syntaxRules;
        Object[] objArr = new Object[1];
        Object[] objArr2 = objArr;
        Object[] objArr3 = objArr;
        new SimpleSymbol("%every");
        SimpleSymbol simpleSymbol105 = (SimpleSymbol) simpleSymbol20.readResolve();
        Lit84 = simpleSymbol105;
        objArr3[0] = simpleSymbol105;
        SyntaxRule[] syntaxRuleArr = new SyntaxRule[1];
        SyntaxRule[] syntaxRuleArr2 = syntaxRuleArr;
        SyntaxRule[] syntaxRuleArr3 = syntaxRuleArr;
        SyntaxRule syntaxRule2 = syntaxRule;
        SyntaxPattern syntaxPattern2 = syntaxPattern;
        new SyntaxPattern("\f\u0018\f\u0007\f\u000f\b", new Object[0], 2);
        Object[] objArr4 = new Object[10];
        new SimpleSymbol("let");
        objArr4[0] = (SimpleSymbol) simpleSymbol21.readResolve();
        Object[] objArr5 = objArr4;
        objArr5[1] = Lit102;
        Object[] objArr6 = objArr5;
        objArr6[2] = Lit101;
        Object[] objArr7 = objArr6;
        objArr7[3] = Lit103;
        Object[] objArr8 = objArr7;
        objArr8[4] = Lit100;
        Object[] objArr9 = objArr8;
        objArr9[5] = Lit104;
        Object[] objArr10 = objArr9;
        new SimpleSymbol("and");
        objArr10[6] = (SimpleSymbol) simpleSymbol22.readResolve();
        Object[] objArr11 = objArr10;
        Object[] objArr12 = objArr11;
        Object[] objArr13 = objArr11;
        new SimpleSymbol("null-list?");
        SimpleSymbol simpleSymbol106 = (SimpleSymbol) simpleSymbol23.readResolve();
        Lit14 = simpleSymbol106;
        objArr13[7] = PairWithPosition.make(simpleSymbol106, PairWithPosition.make(Lit100, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm", 5722136), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm", 5722124);
        Object[] objArr14 = objArr12;
        objArr14[8] = PairWithPosition.make(Lit101, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm", 5722148);
        Object[] objArr15 = objArr14;
        objArr15[9] = PairWithPosition.make(PairWithPosition.make(Lit102, PairWithPosition.make(PairWithPosition.make(Lit103, PairWithPosition.make(Lit100, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm", 5722163), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm", 5722158), PairWithPosition.make(PairWithPosition.make(Lit104, PairWithPosition.make(Lit100, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm", 5722174), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm", 5722169), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm", 5722169), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm", 5722158), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm", 5722154), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm", 5722154);
        new SyntaxRule(syntaxPattern2, "\u0001\u0001", "\u0011\u0018\u0004\u0011\u0018\f¡I\u0011\u0018\u0014\b\u0011\u0018\u001c\b\u000b\b\u0011\u0018$\b\u0011\u0018,\b\u000b\b\u0011\u00184\u0011\u0018<!\t\u0003\u0018D\u0018L", objArr15, 0);
        syntaxRuleArr3[0] = syntaxRule2;
        new SyntaxRules(objArr2, syntaxRuleArr2, 2);
        Lit85 = syntaxRules2;
        new SimpleSymbol("every");
        Lit83 = (SimpleSymbol) simpleSymbol24.readResolve();
        new SimpleSymbol("any");
        Lit82 = (SimpleSymbol) simpleSymbol25.readResolve();
        new SimpleSymbol("break!");
        Lit81 = (SimpleSymbol) simpleSymbol26.readResolve();
        new SimpleSymbol("break");
        Lit80 = (SimpleSymbol) simpleSymbol27.readResolve();
        new SimpleSymbol("span!");
        Lit79 = (SimpleSymbol) simpleSymbol28.readResolve();
        new SimpleSymbol("span");
        Lit78 = (SimpleSymbol) simpleSymbol29.readResolve();
        new SimpleSymbol("take-while!");
        Lit77 = (SimpleSymbol) simpleSymbol30.readResolve();
        new SimpleSymbol("drop-while");
        Lit76 = (SimpleSymbol) simpleSymbol31.readResolve();
        new SimpleSymbol("take-while");
        Lit75 = (SimpleSymbol) simpleSymbol32.readResolve();
        new SimpleSymbol("find-tail");
        Lit74 = (SimpleSymbol) simpleSymbol33.readResolve();
        new SimpleSymbol("find");
        Lit73 = (SimpleSymbol) simpleSymbol34.readResolve();
        new SimpleSymbol("alist-delete!");
        Lit72 = (SimpleSymbol) simpleSymbol35.readResolve();
        new SimpleSymbol("alist-delete");
        Lit71 = (SimpleSymbol) simpleSymbol36.readResolve();
        new SimpleSymbol("alist-copy");
        Lit70 = (SimpleSymbol) simpleSymbol37.readResolve();
        new SimpleSymbol("alist-cons");
        Lit69 = (SimpleSymbol) simpleSymbol38.readResolve();
        new SimpleSymbol("delete-duplicates!");
        Lit68 = (SimpleSymbol) simpleSymbol39.readResolve();
        new SimpleSymbol("delete-duplicates");
        Lit67 = (SimpleSymbol) simpleSymbol40.readResolve();
        new SimpleSymbol("delete!");
        Lit66 = (SimpleSymbol) simpleSymbol41.readResolve();
        new SimpleSymbol("delete");
        Lit65 = (SimpleSymbol) simpleSymbol42.readResolve();
        new SimpleSymbol("remove!");
        Lit64 = (SimpleSymbol) simpleSymbol43.readResolve();
        new SimpleSymbol("remove");
        Lit63 = (SimpleSymbol) simpleSymbol44.readResolve();
        new SimpleSymbol("partition!");
        Lit62 = (SimpleSymbol) simpleSymbol45.readResolve();
        new SimpleSymbol("partition");
        Lit61 = (SimpleSymbol) simpleSymbol46.readResolve();
        new SimpleSymbol("filter!");
        Lit60 = (SimpleSymbol) simpleSymbol47.readResolve();
        new SimpleSymbol("filter");
        Lit59 = (SimpleSymbol) simpleSymbol48.readResolve();
        new SimpleSymbol("filter-map");
        Lit58 = (SimpleSymbol) simpleSymbol49.readResolve();
        new SimpleSymbol("map!");
        Lit57 = (SimpleSymbol) simpleSymbol50.readResolve();
        new SimpleSymbol("pair-for-each");
        Lit56 = (SimpleSymbol) simpleSymbol51.readResolve();
        new SimpleSymbol("append-map!");
        Lit55 = (SimpleSymbol) simpleSymbol52.readResolve();
        new SimpleSymbol("append-map");
        Lit54 = (SimpleSymbol) simpleSymbol53.readResolve();
        new SimpleSymbol("reduce-right");
        Lit53 = (SimpleSymbol) simpleSymbol54.readResolve();
        new SimpleSymbol("reduce");
        Lit52 = (SimpleSymbol) simpleSymbol55.readResolve();
        new SimpleSymbol("pair-fold");
        Lit51 = (SimpleSymbol) simpleSymbol56.readResolve();
        new SimpleSymbol("pair-fold-right");
        Lit50 = (SimpleSymbol) simpleSymbol57.readResolve();
        new SimpleSymbol("fold-right");
        Lit49 = (SimpleSymbol) simpleSymbol58.readResolve();
        new SimpleSymbol("fold");
        Lit48 = (SimpleSymbol) simpleSymbol59.readResolve();
        new SimpleSymbol("unfold");
        Lit47 = (SimpleSymbol) simpleSymbol60.readResolve();
        new SimpleSymbol("unfold-right");
        Lit46 = (SimpleSymbol) simpleSymbol61.readResolve();
        new SimpleSymbol("count");
        Lit45 = (SimpleSymbol) simpleSymbol62.readResolve();
        new SimpleSymbol("concatenate!");
        Lit44 = (SimpleSymbol) simpleSymbol63.readResolve();
        new SimpleSymbol("concatenate");
        Lit43 = (SimpleSymbol) simpleSymbol64.readResolve();
        new SimpleSymbol("append-reverse!");
        Lit42 = (SimpleSymbol) simpleSymbol65.readResolve();
        new SimpleSymbol("append-reverse");
        Lit41 = (SimpleSymbol) simpleSymbol66.readResolve();
        new SimpleSymbol("append!");
        Lit40 = (SimpleSymbol) simpleSymbol67.readResolve();
        new SimpleSymbol("unzip5");
        Lit39 = (SimpleSymbol) simpleSymbol68.readResolve();
        new SimpleSymbol("unzip4");
        Lit38 = (SimpleSymbol) simpleSymbol69.readResolve();
        new SimpleSymbol("unzip3");
        Lit37 = (SimpleSymbol) simpleSymbol70.readResolve();
        new SimpleSymbol("unzip2");
        Lit36 = (SimpleSymbol) simpleSymbol71.readResolve();
        new SimpleSymbol("unzip1");
        Lit35 = (SimpleSymbol) simpleSymbol72.readResolve();
        new SimpleSymbol("last-pair");
        Lit34 = (SimpleSymbol) simpleSymbol73.readResolve();
        new SimpleSymbol("last");
        Lit33 = (SimpleSymbol) simpleSymbol74.readResolve();
        new SimpleSymbol("split-at!");
        Lit32 = (SimpleSymbol) simpleSymbol75.readResolve();
        new SimpleSymbol("split-at");
        Lit31 = (SimpleSymbol) simpleSymbol76.readResolve();
        new SimpleSymbol("drop-right!");
        Lit30 = (SimpleSymbol) simpleSymbol77.readResolve();
        new SimpleSymbol("drop-right");
        Lit29 = (SimpleSymbol) simpleSymbol78.readResolve();
        new SimpleSymbol("take-right");
        Lit28 = (SimpleSymbol) simpleSymbol79.readResolve();
        new SimpleSymbol("take!");
        Lit27 = (SimpleSymbol) simpleSymbol80.readResolve();
        new SimpleSymbol("drop");
        Lit26 = (SimpleSymbol) simpleSymbol81.readResolve();
        new SimpleSymbol("take");
        Lit25 = (SimpleSymbol) simpleSymbol82.readResolve();
        new SimpleSymbol("car+cdr");
        Lit24 = (SimpleSymbol) simpleSymbol83.readResolve();
        new SimpleSymbol("tenth");
        Lit23 = (SimpleSymbol) simpleSymbol84.readResolve();
        new SimpleSymbol("ninth");
        Lit22 = (SimpleSymbol) simpleSymbol85.readResolve();
        new SimpleSymbol("eighth");
        Lit21 = (SimpleSymbol) simpleSymbol86.readResolve();
        new SimpleSymbol("seventh");
        Lit20 = (SimpleSymbol) simpleSymbol87.readResolve();
        new SimpleSymbol("sixth");
        Lit19 = (SimpleSymbol) simpleSymbol88.readResolve();
        new SimpleSymbol("fifth");
        Lit18 = (SimpleSymbol) simpleSymbol89.readResolve();
        new SimpleSymbol("zip");
        Lit17 = (SimpleSymbol) simpleSymbol90.readResolve();
        new SimpleSymbol("length+");
        Lit16 = (SimpleSymbol) simpleSymbol91.readResolve();
        new SimpleSymbol("list=");
        Lit15 = (SimpleSymbol) simpleSymbol92.readResolve();
        new SimpleSymbol("not-pair?");
        Lit13 = (SimpleSymbol) simpleSymbol93.readResolve();
        new SimpleSymbol("circular-list?");
        Lit12 = (SimpleSymbol) simpleSymbol94.readResolve();
        new SimpleSymbol("dotted-list?");
        Lit11 = (SimpleSymbol) simpleSymbol95.readResolve();
        new SimpleSymbol("proper-list?");
        Lit10 = (SimpleSymbol) simpleSymbol96.readResolve();
        new SimpleSymbol("circular-list");
        Lit9 = (SimpleSymbol) simpleSymbol97.readResolve();
        new SimpleSymbol("iota");
        Lit8 = (SimpleSymbol) simpleSymbol98.readResolve();
        new SimpleSymbol("list-copy");
        Lit7 = (SimpleSymbol) simpleSymbol99.readResolve();
        new SimpleSymbol("cons*");
        Lit6 = (SimpleSymbol) simpleSymbol100.readResolve();
        new SimpleSymbol("list-tabulate");
        Lit5 = (SimpleSymbol) simpleSymbol101.readResolve();
        new SimpleSymbol("make-list");
        Lit4 = (SimpleSymbol) simpleSymbol102.readResolve();
        new SimpleSymbol("xcons");
        Lit3 = (SimpleSymbol) simpleSymbol103.readResolve();
        new SimpleSymbol("tmp");
        Lit2 = (SimpleSymbol) simpleSymbol104.readResolve();
        new srfi1();
        $instance = srfi1;
        srfi1 srfi12 = $instance;
        new ModuleMethod(srfi12, 78, Lit3, 8194);
        xcons = moduleMethod;
        new ModuleMethod(srfi12, 79, Lit4, -4095);
        make$Mnlist = moduleMethod2;
        new ModuleMethod(srfi12, 80, Lit5, 8194);
        list$Mntabulate = moduleMethod3;
        new ModuleMethod(srfi12, 81, Lit6, -4096);
        cons$St = moduleMethod4;
        new ModuleMethod(srfi12, 82, Lit7, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        list$Mncopy = moduleMethod5;
        new ModuleMethod(srfi12, 83, Lit8, 12289);
        iota = moduleMethod6;
        new ModuleMethod(srfi12, 86, Lit9, -4095);
        circular$Mnlist = moduleMethod7;
        new ModuleMethod(srfi12, 87, Lit10, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        proper$Mnlist$Qu = moduleMethod8;
        new ModuleMethod(srfi12, 88, Lit11, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        dotted$Mnlist$Qu = moduleMethod9;
        new ModuleMethod(srfi12, 89, Lit12, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        circular$Mnlist$Qu = moduleMethod10;
        new ModuleMethod(srfi12, 90, Lit13, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        not$Mnpair$Qu = moduleMethod11;
        new ModuleMethod(srfi12, 91, Lit14, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        null$Mnlist$Qu = moduleMethod12;
        new ModuleMethod(srfi12, 92, Lit15, -4095);
        list$Eq = moduleMethod13;
        new ModuleMethod(srfi12, 93, Lit16, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        length$Pl = moduleMethod14;
        new ModuleMethod(srfi12, 94, Lit17, -4095);
        zip = moduleMethod15;
        new ModuleMethod(srfi12, 95, Lit18, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fifth = moduleMethod16;
        new ModuleMethod(srfi12, 96, Lit19, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        sixth = moduleMethod17;
        new ModuleMethod(srfi12, 97, Lit20, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        seventh = moduleMethod18;
        new ModuleMethod(srfi12, 98, Lit21, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        eighth = moduleMethod19;
        new ModuleMethod(srfi12, 99, Lit22, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ninth = moduleMethod20;
        new ModuleMethod(srfi12, 100, Lit23, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        tenth = moduleMethod21;
        new ModuleMethod(srfi12, 101, Lit24, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        car$Plcdr = moduleMethod22;
        new ModuleMethod(srfi12, 102, Lit25, 8194);
        take = moduleMethod23;
        new ModuleMethod(srfi12, 103, Lit26, 8194);
        drop = moduleMethod24;
        new ModuleMethod(srfi12, 104, Lit27, 8194);
        take$Ex = moduleMethod25;
        new ModuleMethod(srfi12, 105, Lit28, 8194);
        take$Mnright = moduleMethod26;
        new ModuleMethod(srfi12, 106, Lit29, 8194);
        drop$Mnright = moduleMethod27;
        new ModuleMethod(srfi12, 107, Lit30, 8194);
        drop$Mnright$Ex = moduleMethod28;
        new ModuleMethod(srfi12, 108, Lit31, 8194);
        split$Mnat = moduleMethod29;
        new ModuleMethod(srfi12, 109, Lit32, 8194);
        split$Mnat$Ex = moduleMethod30;
        new ModuleMethod(srfi12, 110, Lit33, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        last = moduleMethod31;
        new ModuleMethod(srfi12, 111, Lit34, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        last$Mnpair = moduleMethod32;
        new ModuleMethod(srfi12, 112, Lit35, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        unzip1 = moduleMethod33;
        new ModuleMethod(srfi12, 113, Lit36, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        unzip2 = moduleMethod34;
        new ModuleMethod(srfi12, 114, Lit37, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        unzip3 = moduleMethod35;
        new ModuleMethod(srfi12, 115, Lit38, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        unzip4 = moduleMethod36;
        new ModuleMethod(srfi12, 116, Lit39, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        unzip5 = moduleMethod37;
        new ModuleMethod(srfi12, 117, Lit40, -4096);
        append$Ex = moduleMethod38;
        new ModuleMethod(srfi12, 118, Lit41, 8194);
        append$Mnreverse = moduleMethod39;
        new ModuleMethod(srfi12, 119, Lit42, 8194);
        append$Mnreverse$Ex = moduleMethod40;
        new ModuleMethod(srfi12, 120, Lit43, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        concatenate = moduleMethod41;
        new ModuleMethod(srfi12, 121, Lit44, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        concatenate$Ex = moduleMethod42;
        new ModuleMethod(srfi12, 122, Lit45, -4094);
        count = moduleMethod43;
        new ModuleMethod(srfi12, 123, Lit46, 20484);
        unfold$Mnright = moduleMethod44;
        new ModuleMethod(srfi12, 125, Lit47, -4092);
        unfold = moduleMethod45;
        new ModuleMethod(srfi12, 126, Lit48, -4093);
        fold = moduleMethod46;
        new ModuleMethod(srfi12, 127, Lit49, -4093);
        fold$Mnright = moduleMethod47;
        new ModuleMethod(srfi12, 128, Lit50, -4093);
        pair$Mnfold$Mnright = moduleMethod48;
        new ModuleMethod(srfi12, 129, Lit51, -4093);
        pair$Mnfold = moduleMethod49;
        new ModuleMethod(srfi12, 130, Lit52, 12291);
        reduce = moduleMethod50;
        new ModuleMethod(srfi12, 131, Lit53, 12291);
        reduce$Mnright = moduleMethod51;
        new ModuleMethod(srfi12, 132, Lit54, -4094);
        append$Mnmap = moduleMethod52;
        new ModuleMethod(srfi12, 133, Lit55, -4094);
        append$Mnmap$Ex = moduleMethod53;
        new ModuleMethod(srfi12, 134, Lit56, -4094);
        pair$Mnfor$Mneach = moduleMethod54;
        new ModuleMethod(srfi12, 135, Lit57, -4094);
        map$Ex = moduleMethod55;
        new ModuleMethod(srfi12, 136, Lit58, -4094);
        filter$Mnmap = moduleMethod56;
        new ModuleMethod(srfi12, 137, Lit59, 8194);
        filter = moduleMethod57;
        new ModuleMethod(srfi12, 138, Lit60, 8194);
        filter$Ex = moduleMethod58;
        new ModuleMethod(srfi12, 139, Lit61, 8194);
        partition = moduleMethod59;
        new ModuleMethod(srfi12, 140, Lit62, 8194);
        partition$Ex = moduleMethod60;
        new ModuleMethod(srfi12, 141, Lit63, 8194);
        remove = moduleMethod61;
        new ModuleMethod(srfi12, 142, Lit64, 8194);
        remove$Ex = moduleMethod62;
        new ModuleMethod(srfi12, 143, Lit65, 12290);
        delete = moduleMethod63;
        new ModuleMethod(srfi12, 145, Lit66, 12290);
        delete$Ex = moduleMethod64;
        new ModuleMethod(srfi12, 147, Lit67, 8193);
        delete$Mnduplicates = moduleMethod65;
        new ModuleMethod(srfi12, 149, Lit68, 8193);
        delete$Mnduplicates$Ex = moduleMethod66;
        new ModuleMethod(srfi12, 151, Lit69, 12291);
        alist$Mncons = moduleMethod67;
        new ModuleMethod(srfi12, 152, Lit70, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        alist$Mncopy = moduleMethod68;
        new ModuleMethod(srfi12, 153, Lit71, 12290);
        alist$Mndelete = moduleMethod69;
        new ModuleMethod(srfi12, 155, Lit72, 12290);
        alist$Mndelete$Ex = moduleMethod70;
        new ModuleMethod(srfi12, 157, Lit73, 8194);
        find = moduleMethod71;
        new ModuleMethod(srfi12, 158, Lit74, 8194);
        find$Mntail = moduleMethod72;
        new ModuleMethod(srfi12, 159, Lit75, 8194);
        take$Mnwhile = moduleMethod73;
        new ModuleMethod(srfi12, ComponentConstants.TEXTBOX_PREFERRED_WIDTH, Lit76, 8194);
        drop$Mnwhile = moduleMethod74;
        new ModuleMethod(srfi12, 161, Lit77, 8194);
        take$Mnwhile$Ex = moduleMethod75;
        new ModuleMethod(srfi12, 162, Lit78, 8194);
        span = moduleMethod76;
        new ModuleMethod(srfi12, 163, Lit79, 8194);
        span$Ex = moduleMethod77;
        new ModuleMethod(srfi12, 164, Lit80, 8194);
        f607break = moduleMethod78;
        new ModuleMethod(srfi12, 165, Lit81, 8194);
        break$Ex = moduleMethod79;
        new ModuleMethod(srfi12, 166, Lit82, -4094);
        any = moduleMethod80;
        new ModuleMethod(srfi12, 167, Lit83, -4094);
        every = moduleMethod81;
        new ModuleMethod(srfi12, 168, Lit86, -4094);
        list$Mnindex = moduleMethod82;
        new ModuleMethod(srfi12, 169, Lit87, -4095);
        lset$Ls$Eq = moduleMethod83;
        new ModuleMethod(srfi12, 170, Lit88, -4095);
        lset$Eq = moduleMethod84;
        new ModuleMethod(srfi12, 171, Lit89, -4094);
        lset$Mnadjoin = moduleMethod85;
        new ModuleMethod(srfi12, 172, Lit90, -4095);
        lset$Mnunion = moduleMethod86;
        new ModuleMethod(srfi12, 173, Lit91, -4095);
        lset$Mnunion$Ex = moduleMethod87;
        new ModuleMethod(srfi12, 174, Lit92, -4094);
        lset$Mnintersection = moduleMethod88;
        new ModuleMethod(srfi12, 175, Lit93, -4094);
        lset$Mnintersection$Ex = moduleMethod89;
        new ModuleMethod(srfi12, 176, Lit94, -4094);
        lset$Mndifference = moduleMethod90;
        new ModuleMethod(srfi12, 177, Lit95, -4094);
        lset$Mndifference$Ex = moduleMethod91;
        new ModuleMethod(srfi12, 178, Lit96, -4095);
        lset$Mnxor = moduleMethod92;
        new ModuleMethod(srfi12, 179, Lit97, -4095);
        lset$Mnxor$Ex = moduleMethod93;
        new ModuleMethod(srfi12, 180, Lit98, -4094);
        lset$Mndiff$Plintersection = moduleMethod94;
        new ModuleMethod(srfi12, 181, Lit99, -4094);
        lset$Mndiff$Plintersection$Ex = moduleMethod95;
        new ModuleMethod(srfi12, 182, (Object) null, 8194);
        lambda$Fn64 = moduleMethod96;
        new ModuleMethod(srfi12, 183, (Object) null, 8194);
        lambda$Fn78 = moduleMethod97;
        $instance.run();
    }

    public srfi1() {
        ModuleInfo.register(this);
    }

    public static Object alistDelete(Object obj, Object obj2) {
        return alistDelete(obj, obj2, Scheme.isEqual);
    }

    public static Object alistDelete$Ex(Object obj, Object obj2) {
        return alistDelete$Ex(obj, obj2, Scheme.isEqual);
    }

    public static Object delete(Object obj, Object obj2) {
        return delete(obj, obj2, Scheme.isEqual);
    }

    public static Object delete$Ex(Object obj, Object obj2) {
        return delete$Ex(obj, obj2, Scheme.isEqual);
    }

    public static Object deleteDuplicates(Object obj) {
        return deleteDuplicates(obj, Scheme.isEqual);
    }

    public static Object deleteDuplicates$Ex(Object obj) {
        return deleteDuplicates$Ex(obj, Scheme.isEqual);
    }

    public static Object iota(IntNum intNum) {
        return iota(intNum, Lit0, Lit1);
    }

    public static Object iota(IntNum intNum, Numeric numeric) {
        return iota(intNum, numeric, Lit1);
    }

    public static Object unfoldRight(Procedure procedure, Procedure procedure2, Procedure procedure3, Object obj) {
        return unfoldRight(procedure, procedure2, procedure3, obj, LList.Empty);
    }

    public final void run(CallContext $ctx) {
        Consumer consumer = $ctx.consumer;
        first = C1245lists.car;
        second = C1245lists.cadr;
        third = C1245lists.caddr;
        fourth = C1245lists.cadddr;
        map$Mnin$Mnorder = Scheme.map;
    }

    public static Pair xcons(Object d, Object a) {
        return C1245lists.cons(a, d);
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 78:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 80:
                callContext2.value1 = obj3;
                CallContext callContext3 = callContext2;
                Object obj5 = obj4;
                Object obj6 = obj5;
                if (!(obj5 instanceof Procedure)) {
                    return -786430;
                }
                callContext3.value2 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 83:
                CallContext callContext4 = callContext2;
                Object obj7 = obj3;
                Object obj8 = obj7;
                if (IntNum.asIntNumOrNull(obj7) == null) {
                    return -786431;
                }
                callContext4.value1 = obj8;
                CallContext callContext5 = callContext2;
                Object obj9 = obj4;
                Object obj10 = obj9;
                if (Numeric.asNumericOrNull(obj9) == null) {
                    return -786430;
                }
                callContext5.value2 = obj10;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 102:
                callContext2.value1 = obj3;
                CallContext callContext6 = callContext2;
                Object obj11 = obj4;
                Object obj12 = obj11;
                if (IntNum.asIntNumOrNull(obj11) == null) {
                    return -786430;
                }
                callContext6.value2 = obj12;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 103:
                callContext2.value1 = obj3;
                CallContext callContext7 = callContext2;
                Object obj13 = obj4;
                Object obj14 = obj13;
                if (IntNum.asIntNumOrNull(obj13) == null) {
                    return -786430;
                }
                callContext7.value2 = obj14;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 104:
                callContext2.value1 = obj3;
                CallContext callContext8 = callContext2;
                Object obj15 = obj4;
                Object obj16 = obj15;
                if (IntNum.asIntNumOrNull(obj15) == null) {
                    return -786430;
                }
                callContext8.value2 = obj16;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 105:
                callContext2.value1 = obj3;
                CallContext callContext9 = callContext2;
                Object obj17 = obj4;
                Object obj18 = obj17;
                if (IntNum.asIntNumOrNull(obj17) == null) {
                    return -786430;
                }
                callContext9.value2 = obj18;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 106:
                callContext2.value1 = obj3;
                CallContext callContext10 = callContext2;
                Object obj19 = obj4;
                Object obj20 = obj19;
                if (IntNum.asIntNumOrNull(obj19) == null) {
                    return -786430;
                }
                callContext10.value2 = obj20;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 107:
                callContext2.value1 = obj3;
                CallContext callContext11 = callContext2;
                Object obj21 = obj4;
                Object obj22 = obj21;
                if (IntNum.asIntNumOrNull(obj21) == null) {
                    return -786430;
                }
                callContext11.value2 = obj22;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 108:
                callContext2.value1 = obj3;
                CallContext callContext12 = callContext2;
                Object obj23 = obj4;
                Object obj24 = obj23;
                if (IntNum.asIntNumOrNull(obj23) == null) {
                    return -786430;
                }
                callContext12.value2 = obj24;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 109:
                callContext2.value1 = obj3;
                CallContext callContext13 = callContext2;
                Object obj25 = obj4;
                Object obj26 = obj25;
                if (IntNum.asIntNumOrNull(obj25) == null) {
                    return -786430;
                }
                callContext13.value2 = obj26;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 118:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 119:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 137:
                CallContext callContext14 = callContext2;
                Object obj27 = obj3;
                Object obj28 = obj27;
                if (!(obj27 instanceof Procedure)) {
                    return -786431;
                }
                callContext14.value1 = obj28;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 138:
                CallContext callContext15 = callContext2;
                Object obj29 = obj3;
                Object obj30 = obj29;
                if (!(obj29 instanceof Procedure)) {
                    return -786431;
                }
                callContext15.value1 = obj30;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 139:
                CallContext callContext16 = callContext2;
                Object obj31 = obj3;
                Object obj32 = obj31;
                if (!(obj31 instanceof Procedure)) {
                    return -786431;
                }
                callContext16.value1 = obj32;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 140:
                CallContext callContext17 = callContext2;
                Object obj33 = obj3;
                Object obj34 = obj33;
                if (!(obj33 instanceof Procedure)) {
                    return -786431;
                }
                callContext17.value1 = obj34;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 141:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 142:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 143:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 145:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 147:
                callContext2.value1 = obj3;
                CallContext callContext18 = callContext2;
                Object obj35 = obj4;
                Object obj36 = obj35;
                if (!(obj35 instanceof Procedure)) {
                    return -786430;
                }
                callContext18.value2 = obj36;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 149:
                callContext2.value1 = obj3;
                CallContext callContext19 = callContext2;
                Object obj37 = obj4;
                Object obj38 = obj37;
                if (!(obj37 instanceof Procedure)) {
                    return -786430;
                }
                callContext19.value2 = obj38;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 153:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 155:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 157:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 158:
                CallContext callContext20 = callContext2;
                Object obj39 = obj3;
                Object obj40 = obj39;
                if (!(obj39 instanceof Procedure)) {
                    return -786431;
                }
                callContext20.value1 = obj40;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 159:
                CallContext callContext21 = callContext2;
                Object obj41 = obj3;
                Object obj42 = obj41;
                if (!(obj41 instanceof Procedure)) {
                    return -786431;
                }
                callContext21.value1 = obj42;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case ComponentConstants.TEXTBOX_PREFERRED_WIDTH:
                CallContext callContext22 = callContext2;
                Object obj43 = obj3;
                Object obj44 = obj43;
                if (!(obj43 instanceof Procedure)) {
                    return -786431;
                }
                callContext22.value1 = obj44;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 161:
                CallContext callContext23 = callContext2;
                Object obj45 = obj3;
                Object obj46 = obj45;
                if (!(obj45 instanceof Procedure)) {
                    return -786431;
                }
                callContext23.value1 = obj46;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 162:
                CallContext callContext24 = callContext2;
                Object obj47 = obj3;
                Object obj48 = obj47;
                if (!(obj47 instanceof Procedure)) {
                    return -786431;
                }
                callContext24.value1 = obj48;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 163:
                CallContext callContext25 = callContext2;
                Object obj49 = obj3;
                Object obj50 = obj49;
                if (!(obj49 instanceof Procedure)) {
                    return -786431;
                }
                callContext25.value1 = obj50;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 164:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 165:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 182:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 183:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            default:
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
        }
    }

    public static Object makeList$V(Object obj, Object[] argsArray) {
        Object error$V;
        Object len = obj;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        LList maybe$Mnelt = makeList;
        boolean x = ((numbers.isInteger(len) ? 1 : 0) + true) & true;
        if (!x ? Scheme.numLss.apply2(len, Lit0) != Boolean.FALSE : x) {
            Object error$V2 = misc.error$V("make-list arg#1 must be a non-negative integer", new Object[0]);
        }
        if (C1245lists.isNull(maybe$Mnelt)) {
            error$V = Boolean.FALSE;
        } else if (C1245lists.isNull(C1245lists.cdr.apply1(maybe$Mnelt))) {
            error$V = C1245lists.car.apply1(maybe$Mnelt);
        } else {
            error$V = misc.error$V("Too many arguments to MAKE-LIST", new Object[]{C1245lists.cons(len, maybe$Mnelt)});
        }
        Object elt = error$V;
        Object obj2 = len;
        Object obj3 = LList.Empty;
        while (true) {
            Object len2 = obj3;
            Object i = obj2;
            if (Scheme.numLEq.apply2(i, Lit0) != Boolean.FALSE) {
                return len2;
            }
            obj2 = AddOp.$Mn.apply2(i, Lit1);
            obj3 = C1245lists.cons(elt, len2);
        }
    }

    public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object[] objArr2 = objArr;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 79:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 81:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 86:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 92:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 94:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 117:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 122:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 123:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 125:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 126:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 127:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 128:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 129:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 132:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 133:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 134:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 135:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 136:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 166:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 167:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 168:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 169:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 170:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 171:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 172:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 173:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 174:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 175:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 176:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 177:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 178:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 179:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 180:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 181:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            default:
                return super.matchN(moduleMethod2, objArr2, callContext2);
        }
    }

    public static Object listTabulate(Object obj, Procedure procedure) {
        Object len = obj;
        Procedure proc = procedure;
        boolean x = ((numbers.isInteger(len) ? 1 : 0) + true) & true;
        if (!x ? Scheme.numLss.apply2(len, Lit0) != Boolean.FALSE : x) {
            Object error$V = misc.error$V("list-tabulate arg#1 must be a non-negative integer", new Object[0]);
        }
        Object apply2 = AddOp.$Mn.apply2(len, Lit1);
        Object obj2 = LList.Empty;
        while (true) {
            Object len2 = obj2;
            Object i = apply2;
            if (Scheme.numLss.apply2(i, Lit0) != Boolean.FALSE) {
                return len2;
            }
            apply2 = AddOp.$Mn.apply2(i, Lit1);
            obj2 = C1245lists.cons(proc.apply1(i), len2);
        }
    }

    public static Object cons$St(Object... args) {
        return LList.consX(args);
    }

    public static LList listCopy(LList lis) {
        Throwable th;
        LList result = LList.Empty;
        LList prev = LList.Empty;
        LList lList = lis;
        while (true) {
            LList lis2 = lList;
            if (!C1245lists.isPair(lis2)) {
                return result;
            }
            LList p = C1245lists.cons(C1245lists.car.apply1(lis2), LList.Empty);
            if (prev == LList.Empty) {
                result = p;
            } else {
                LList lList2 = prev;
                LList lList3 = lList2;
                try {
                    C1245lists.setCdr$Ex((Pair) lList2, p);
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th2 = th;
                    new WrongType(classCastException, "set-cdr!", 1, (Object) lList3);
                    throw th2;
                }
            }
            prev = p;
            lList = (LList) C1245lists.cdr.apply1(lis2);
        }
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 82:
                CallContext callContext3 = callContext2;
                Object obj3 = obj2;
                Object obj4 = obj3;
                if (!(obj3 instanceof LList)) {
                    return -786431;
                }
                callContext3.value1 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 83:
                CallContext callContext4 = callContext2;
                Object obj5 = obj2;
                Object obj6 = obj5;
                if (IntNum.asIntNumOrNull(obj5) == null) {
                    return -786431;
                }
                callContext4.value1 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 87:
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
            case 91:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 93:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 95:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 96:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 97:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 98:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 99:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 100:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 101:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 110:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 111:
                CallContext callContext5 = callContext2;
                Object obj7 = obj2;
                Object obj8 = obj7;
                if (!(obj7 instanceof Pair)) {
                    return -786431;
                }
                callContext5.value1 = obj8;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 112:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 113:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 114:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 115:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 116:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 120:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 121:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 147:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 149:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 152:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            default:
                return super.match1(moduleMethod2, obj2, callContext2);
        }
    }

    public static Object iota(IntNum intNum, Numeric numeric, Numeric numeric2) {
        Throwable th;
        IntNum count2 = intNum;
        Numeric start = numeric;
        Numeric step = numeric2;
        if (IntNum.compare(count2, 0) < 0) {
            Object[] objArr = new Object[2];
            objArr[0] = iota;
            Object[] objArr2 = objArr;
            objArr2[1] = count2;
            Object error$V = misc.error$V("Negative step count", objArr2);
        }
        Object apply2 = AddOp.$Pl.apply2(start, MultiplyOp.$St.apply2(IntNum.add(count2, -1), step));
        Object obj = apply2;
        try {
            Object obj2 = count2;
            Object obj3 = (Numeric) apply2;
            Object obj4 = LList.Empty;
            while (true) {
                Object obj5 = obj4;
                Object obj6 = obj3;
                Object obj7 = obj2;
                if (Scheme.numLEq.apply2(obj7, Lit0) != Boolean.FALSE) {
                    return obj5;
                }
                obj2 = AddOp.$Mn.apply2(obj7, Lit1);
                obj3 = AddOp.$Mn.apply2(obj6, step);
                obj4 = C1245lists.cons(obj6, obj5);
            }
        } catch (ClassCastException e) {
            ClassCastException classCastException = e;
            Throwable th2 = th;
            new WrongType(classCastException, "last-val", -2, obj);
            throw th2;
        }
    }

    public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj4 = obj;
        Object obj5 = obj2;
        Object obj6 = obj3;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 83:
                CallContext callContext3 = callContext2;
                Object obj7 = obj4;
                Object obj8 = obj7;
                if (IntNum.asIntNumOrNull(obj7) == null) {
                    return -786431;
                }
                callContext3.value1 = obj8;
                CallContext callContext4 = callContext2;
                Object obj9 = obj5;
                Object obj10 = obj9;
                if (Numeric.asNumericOrNull(obj9) == null) {
                    return -786430;
                }
                callContext4.value2 = obj10;
                CallContext callContext5 = callContext2;
                Object obj11 = obj6;
                Object obj12 = obj11;
                if (Numeric.asNumericOrNull(obj11) == null) {
                    return -786429;
                }
                callContext5.value3 = obj12;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 130:
                CallContext callContext6 = callContext2;
                Object obj13 = obj4;
                Object obj14 = obj13;
                if (!(obj13 instanceof Procedure)) {
                    return -786431;
                }
                callContext6.value1 = obj14;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 131:
                CallContext callContext7 = callContext2;
                Object obj15 = obj4;
                Object obj16 = obj15;
                if (!(obj15 instanceof Procedure)) {
                    return -786431;
                }
                callContext7.value1 = obj16;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 143:
                callContext2.value1 = obj4;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 145:
                callContext2.value1 = obj4;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 151:
                callContext2.value1 = obj4;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 153:
                callContext2.value1 = obj4;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 155:
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

    public static Pair circularList$V(Object val1, Object[] argsArray) {
        Throwable th;
        LList vals = LList.makeList(argsArray, 0);
        LList lList = vals;
        Pair ans = C1245lists.cons(val1, vals);
        Object lastPair = lastPair(ans);
        Object obj = lastPair;
        try {
            C1245lists.setCdr$Ex((Pair) lastPair, ans);
            return ans;
        } catch (ClassCastException e) {
            ClassCastException classCastException = e;
            Throwable th2 = th;
            new WrongType(classCastException, "set-cdr!", 1, obj);
            throw th2;
        }
    }

    public static Object isProperList(Object obj) {
        Boolean bool;
        Object x = obj;
        Object obj2 = x;
        Object obj3 = x;
        while (true) {
            Object obj4 = obj3;
            Object x2 = obj2;
            if (C1245lists.isPair(x2)) {
                Object x3 = C1245lists.cdr.apply1(x2);
                if (C1245lists.isPair(x3)) {
                    Object apply1 = C1245lists.cdr.apply1(x3);
                    Object lag = C1245lists.cdr.apply1(obj4);
                    Object x4 = apply1;
                    boolean x5 = ((x4 == lag ? 1 : 0) + 1) & true;
                    if (x5) {
                        obj2 = x4;
                        obj3 = lag;
                    } else {
                        bool = x5 ? Boolean.TRUE : Boolean.FALSE;
                    }
                } else {
                    bool = C1245lists.isNull(x3) ? Boolean.TRUE : Boolean.FALSE;
                }
            } else {
                bool = C1245lists.isNull(x2) ? Boolean.TRUE : Boolean.FALSE;
            }
        }
        return bool;
    }

    public static Object isDottedList(Object obj) {
        Boolean bool;
        Object x = obj;
        Object obj2 = x;
        Object obj3 = x;
        while (true) {
            Object obj4 = obj3;
            Object x2 = obj2;
            if (C1245lists.isPair(x2)) {
                Object x3 = C1245lists.cdr.apply1(x2);
                if (C1245lists.isPair(x3)) {
                    Object apply1 = C1245lists.cdr.apply1(x3);
                    Object lag = C1245lists.cdr.apply1(obj4);
                    Object x4 = apply1;
                    boolean x5 = ((x4 == lag ? 1 : 0) + 1) & true;
                    if (x5) {
                        obj2 = x4;
                        obj3 = lag;
                    } else {
                        bool = x5 ? Boolean.TRUE : Boolean.FALSE;
                    }
                } else {
                    bool = C1245lists.isNull(x3) ? Boolean.FALSE : Boolean.TRUE;
                }
            } else {
                bool = C1245lists.isNull(x2) ? Boolean.FALSE : Boolean.TRUE;
            }
        }
        return bool;
    }

    public static Object isCircularList(Object obj) {
        Boolean bool;
        Object x = obj;
        Object obj2 = x;
        Object obj3 = x;
        while (true) {
            Object obj4 = obj3;
            Object x2 = obj2;
            boolean x3 = C1245lists.isPair(x2);
            if (x3) {
                Object x4 = C1245lists.cdr.apply1(x2);
                boolean x5 = C1245lists.isPair(x4);
                if (x5) {
                    Object apply1 = C1245lists.cdr.apply1(x4);
                    Object lag = C1245lists.cdr.apply1(obj4);
                    Object x6 = apply1;
                    boolean x7 = x6 == lag;
                    if (x7) {
                        bool = x7 ? Boolean.TRUE : Boolean.FALSE;
                    } else {
                        obj2 = x6;
                        obj3 = lag;
                    }
                } else {
                    bool = x5 ? Boolean.TRUE : Boolean.FALSE;
                }
            } else {
                bool = x3 ? Boolean.TRUE : Boolean.FALSE;
            }
        }
        return bool;
    }

    public static boolean isNotPair(Object x) {
        return ((C1245lists.isPair(x) ? 1 : 0) + true) & true;
    }

    public static Object isNullList(Object obj) {
        Object l;
        Object l2 = obj;
        if (l2 instanceof Pair) {
            l = Boolean.FALSE;
        } else if (l2 == LList.Empty) {
            l = Boolean.TRUE;
        } else {
            l = misc.error$V("null-list?: argument out of domain", new Object[]{l2});
        }
        return l;
    }

    public static Object list$Eq$V(Object obj, Object[] argsArray) {
        Object $Eq;
        Throwable th;
        Object $Eq2 = obj;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        LList lists = makeList;
        boolean x = C1245lists.isNull(lists);
        if (x) {
            $Eq = x ? Boolean.TRUE : Boolean.FALSE;
        } else {
            Object apply1 = C1245lists.car.apply1(lists);
            Object apply12 = C1245lists.cdr.apply1(lists);
            loop0:
            while (true) {
                Object obj2 = apply12;
                Object list$Mna = apply1;
                boolean x2 = C1245lists.isNull(obj2);
                if (x2) {
                    $Eq = x2 ? Boolean.TRUE : Boolean.FALSE;
                } else {
                    Object apply13 = C1245lists.car.apply1(obj2);
                    Object others = C1245lists.cdr.apply1(obj2);
                    Object list$Mnb = apply13;
                    if (list$Mna == list$Mnb) {
                        apply1 = list$Mnb;
                        apply12 = others;
                    } else {
                        Object obj3 = list$Mna;
                        Object obj4 = list$Mnb;
                        while (true) {
                            Object obj5 = obj4;
                            Object list$Mna2 = obj3;
                            if (isNullList(list$Mna2) != Boolean.FALSE) {
                                Object x3 = isNullList(obj5);
                                if (x3 == Boolean.FALSE) {
                                    $Eq = x3;
                                    break;
                                }
                                apply1 = obj5;
                                apply12 = others;
                            } else {
                                Object isNullList = isNullList(obj5);
                                Object obj6 = isNullList;
                                try {
                                    boolean x4 = ((isNullList != Boolean.FALSE ? 1 : 0) + 1) & true;
                                    if (x4) {
                                        Object x5 = Scheme.applyToArgs.apply3($Eq2, C1245lists.car.apply1(list$Mna2), C1245lists.car.apply1(obj5));
                                        if (x5 == Boolean.FALSE) {
                                            $Eq = x5;
                                            break loop0;
                                        }
                                        obj3 = C1245lists.cdr.apply1(list$Mna2);
                                        obj4 = C1245lists.cdr.apply1(obj5);
                                    } else {
                                        $Eq = x4 ? Boolean.TRUE : Boolean.FALSE;
                                    }
                                } catch (ClassCastException e) {
                                    ClassCastException classCastException = e;
                                    Throwable th2 = th;
                                    new WrongType(classCastException, "x", -2, obj6);
                                    throw th2;
                                }
                            }
                        }
                    }
                }
            }
        }
        return $Eq;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v2, resolved type: gnu.math.IntNum} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v0, resolved type: gnu.math.IntNum} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v6, resolved type: gnu.math.IntNum} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object length$Pl(java.lang.Object r15) {
        /*
            r0 = r15
            r10 = r0
            r11 = r0
            gnu.math.IntNum r12 = Lit0
            r3 = r12
            r2 = r11
            r1 = r10
        L_0x0008:
            r10 = r1
            boolean r10 = kawa.lib.C1245lists.isPair(r10)
            if (r10 == 0) goto L_0x0066
            gnu.expr.GenericProc r10 = kawa.lib.C1245lists.cdr
            r11 = r1
            java.lang.Object r10 = r10.apply1(r11)
            gnu.kawa.functions.AddOp r11 = gnu.kawa.functions.AddOp.$Pl
            r12 = r3
            gnu.math.IntNum r13 = Lit1
            java.lang.Object r11 = r11.apply2(r12, r13)
            r5 = r11
            r4 = r10
            r10 = r4
            boolean r10 = kawa.lib.C1245lists.isPair(r10)
            if (r10 == 0) goto L_0x0064
            gnu.expr.GenericProc r10 = kawa.lib.C1245lists.cdr
            r11 = r4
            java.lang.Object r10 = r10.apply1(r11)
            gnu.expr.GenericProc r11 = kawa.lib.C1245lists.cdr
            r12 = r2
            java.lang.Object r11 = r11.apply1(r12)
            gnu.kawa.functions.AddOp r12 = gnu.kawa.functions.AddOp.$Pl
            r13 = r5
            gnu.math.IntNum r14 = Lit1
            java.lang.Object r12 = r12.apply2(r13, r14)
            r8 = r12
            r7 = r11
            r6 = r10
            r10 = r6
            r11 = r7
            if (r10 != r11) goto L_0x0058
            r10 = 1
        L_0x0047:
            r11 = 1
            int r10 = r10 + 1
            r11 = 1
            r10 = r10 & 1
            r9 = r10
            r10 = r9
            if (r10 == 0) goto L_0x005a
            r10 = r6
            r11 = r7
            r12 = r8
            r3 = r12
            r2 = r11
            r1 = r10
            goto L_0x0008
        L_0x0058:
            r10 = 0
            goto L_0x0047
        L_0x005a:
            r10 = r9
            if (r10 == 0) goto L_0x0061
            java.lang.Boolean r10 = java.lang.Boolean.TRUE
        L_0x005f:
            r0 = r10
            return r0
        L_0x0061:
            java.lang.Boolean r10 = java.lang.Boolean.FALSE
            goto L_0x005f
        L_0x0064:
            r10 = r5
            goto L_0x005f
        L_0x0066:
            r10 = r3
            goto L_0x005f
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.srfi1.length$Pl(java.lang.Object):java.lang.Object");
    }

    public static Object zip$V(Object list1, Object[] argsArray) {
        LList more$Mnlists = LList.makeList(argsArray, 0);
        LList lList = more$Mnlists;
        return Scheme.apply.apply4(Scheme.map, LangObjType.listType, list1, more$Mnlists);
    }

    public static Object fifth(Object x) {
        return C1245lists.car.apply1(C1245lists.cddddr.apply1(x));
    }

    public static Object sixth(Object x) {
        return C1245lists.cadr.apply1(C1245lists.cddddr.apply1(x));
    }

    public static Object seventh(Object x) {
        return C1245lists.caddr.apply1(C1245lists.cddddr.apply1(x));
    }

    public static Object eighth(Object x) {
        return C1245lists.cadddr.apply1(C1245lists.cddddr.apply1(x));
    }

    public static Object ninth(Object x) {
        return C1245lists.car.apply1(C1245lists.cddddr.apply1(C1245lists.cddddr.apply1(x)));
    }

    public static Object tenth(Object x) {
        return C1245lists.cadr.apply1(C1245lists.cddddr.apply1(C1245lists.cddddr.apply1(x)));
    }

    public static Object car$PlCdr(Object obj) {
        Object pair = obj;
        Object[] objArr = new Object[2];
        objArr[0] = C1245lists.car.apply1(pair);
        Object[] objArr2 = objArr;
        objArr2[1] = C1245lists.cdr.apply1(pair);
        return misc.values(objArr2);
    }

    public static Object take(Object lis, IntNum k) {
        Throwable th;
        Throwable th2;
        Object obj = lis;
        Object obj2 = k;
        LList lList = LList.Empty;
        while (true) {
            LList lList2 = lList;
            Object obj3 = obj2;
            Object lis2 = obj;
            Object obj4 = obj3;
            Object obj5 = obj4;
            try {
                if (numbers.isZero((Number) obj4)) {
                    LList lList3 = lList2;
                    LList lList4 = lList3;
                    try {
                        return C1245lists.reverse$Ex(lList3);
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th3 = th2;
                        new WrongType(classCastException, "reverse!", 1, (Object) lList4);
                        throw th3;
                    }
                } else {
                    obj = C1245lists.cdr.apply1(lis2);
                    obj2 = AddOp.$Mn.apply2(obj3, Lit1);
                    lList = C1245lists.cons(C1245lists.car.apply1(lis2), lList2);
                }
            } catch (ClassCastException e2) {
                ClassCastException classCastException2 = e2;
                Throwable th4 = th;
                new WrongType(classCastException2, "zero?", 1, obj5);
                throw th4;
            }
        }
    }

    public static Object drop(Object lis, IntNum k) {
        Throwable th;
        Object obj = lis;
        Object obj2 = k;
        while (true) {
            Object obj3 = obj2;
            Object lis2 = obj;
            Object obj4 = obj3;
            Object obj5 = obj4;
            try {
                if (numbers.isZero((Number) obj4)) {
                    return lis2;
                }
                obj = C1245lists.cdr.apply1(lis2);
                obj2 = AddOp.$Mn.apply2(obj3, Lit1);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "zero?", 1, obj5);
                throw th2;
            }
        }
    }

    public static Object take$Ex(Object obj, IntNum intNum) {
        Throwable th;
        Object lis;
        Object lis2 = obj;
        IntNum k = intNum;
        if (numbers.isZero(k)) {
            lis = LList.Empty;
        } else {
            Object drop2 = drop(lis2, IntNum.add(k, -1));
            Object obj2 = drop2;
            try {
                C1245lists.setCdr$Ex((Pair) drop2, LList.Empty);
                lis = lis2;
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "set-cdr!", 1, obj2);
                throw th2;
            }
        }
        return lis;
    }

    public static Object takeRight(Object obj, IntNum k) {
        Object lis = obj;
        Object obj2 = lis;
        Object drop2 = drop(lis, k);
        while (true) {
            Object obj3 = drop2;
            Object lag = obj2;
            if (!C1245lists.isPair(obj3)) {
                return lag;
            }
            obj2 = C1245lists.cdr.apply1(lag);
            drop2 = C1245lists.cdr.apply1(obj3);
        }
    }

    public static Object dropRight(Object obj, IntNum k) {
        Object lis = obj;
        return lambda1recur(lis, drop(lis, k));
    }

    public static Object lambda1recur(Object obj, Object obj2) {
        Object lag = obj;
        Object lead = obj2;
        return C1245lists.isPair(lead) ? C1245lists.cons(C1245lists.car.apply1(lag), lambda1recur(C1245lists.cdr.apply1(lag), C1245lists.cdr.apply1(lead))) : LList.Empty;
    }

    public static Object dropRight$Ex(Object obj, IntNum k) {
        Object lis;
        Object lag;
        Throwable th;
        Object lis2 = obj;
        Object lead = drop(lis2, k);
        if (C1245lists.isPair(lead)) {
            Object obj2 = lis2;
            Object apply1 = C1245lists.cdr.apply1(lead);
            while (true) {
                Object obj3 = apply1;
                lag = obj2;
                if (!C1245lists.isPair(obj3)) {
                    break;
                }
                obj2 = C1245lists.cdr.apply1(lag);
                apply1 = C1245lists.cdr.apply1(obj3);
            }
            Object obj4 = lag;
            Object obj5 = obj4;
            try {
                C1245lists.setCdr$Ex((Pair) obj4, LList.Empty);
                lis = lis2;
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "set-cdr!", 1, obj5);
                throw th2;
            }
        } else {
            lis = LList.Empty;
        }
        return lis;
    }

    public static Object splitAt(Object x, IntNum k) {
        Throwable th;
        Throwable th2;
        LList lList = LList.Empty;
        Object obj = x;
        Object obj2 = k;
        while (true) {
            Object obj3 = obj2;
            Object obj4 = obj;
            LList lList2 = lList;
            Object obj5 = obj3;
            Object obj6 = obj5;
            try {
                if (numbers.isZero((Number) obj5)) {
                    Object[] objArr = new Object[2];
                    Object[] objArr2 = objArr;
                    Object[] objArr3 = objArr;
                    LList lList3 = lList2;
                    LList lList4 = lList3;
                    try {
                        objArr3[0] = C1245lists.reverse$Ex(lList3);
                        Object[] objArr4 = objArr2;
                        objArr4[1] = obj4;
                        return misc.values(objArr4);
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th3 = th2;
                        new WrongType(classCastException, "reverse!", 1, (Object) lList4);
                        throw th3;
                    }
                } else {
                    lList = C1245lists.cons(C1245lists.car.apply1(obj4), lList2);
                    obj = C1245lists.cdr.apply1(obj4);
                    obj2 = AddOp.$Mn.apply2(obj3, Lit1);
                }
            } catch (ClassCastException e2) {
                ClassCastException classCastException2 = e2;
                Throwable th4 = th;
                new WrongType(classCastException2, "zero?", 1, obj6);
                throw th4;
            }
        }
    }

    public static Object splitAt$Ex(Object obj, IntNum intNum) {
        Throwable th;
        Object x;
        Object x2 = obj;
        IntNum k = intNum;
        if (numbers.isZero(k)) {
            Object[] objArr = new Object[2];
            objArr[0] = LList.Empty;
            Object[] objArr2 = objArr;
            objArr2[1] = x2;
            x = misc.values(objArr2);
        } else {
            Object prev = drop(x2, IntNum.add(k, -1));
            Object suffix = C1245lists.cdr.apply1(prev);
            Object obj2 = prev;
            Object obj3 = obj2;
            try {
                C1245lists.setCdr$Ex((Pair) obj2, LList.Empty);
                Object[] objArr3 = new Object[2];
                objArr3[0] = x2;
                Object[] objArr4 = objArr3;
                objArr4[1] = suffix;
                x = misc.values(objArr4);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "set-cdr!", 1, obj3);
                throw th2;
            }
        }
        return x;
    }

    public static Object last(Object lis) {
        Throwable th;
        Object obj = lis;
        Object obj2 = obj;
        try {
            return C1245lists.car.apply1(lastPair((Pair) obj));
        } catch (ClassCastException e) {
            ClassCastException classCastException = e;
            Throwable th2 = th;
            new WrongType(classCastException, "last-pair", 0, obj2);
            throw th2;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: gnu.lists.Pair} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object lastPair(gnu.lists.Pair r5) {
        /*
            r0 = r5
            r3 = r0
            r1 = r3
        L_0x0003:
            gnu.expr.GenericProc r3 = kawa.lib.C1245lists.cdr
            r4 = r1
            java.lang.Object r3 = r3.apply1(r4)
            r2 = r3
            r3 = r2
            boolean r3 = kawa.lib.C1245lists.isPair(r3)
            if (r3 == 0) goto L_0x0015
            r3 = r2
            r1 = r3
            goto L_0x0003
        L_0x0015:
            r3 = r1
            r0 = r3
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.srfi1.lastPair(gnu.lists.Pair):java.lang.Object");
    }

    public static LList unzip1(Object lis) {
        Throwable th;
        Object obj = lis;
        Object obj2 = LList.Empty;
        while (true) {
            Object obj3 = obj2;
            Object arg0 = obj;
            if (arg0 == LList.Empty) {
                return LList.reverseInPlace(obj3);
            }
            Object obj4 = arg0;
            Object obj5 = obj4;
            try {
                Pair arg02 = (Pair) obj4;
                obj = arg02.getCdr();
                obj2 = Pair.make(C1245lists.car.apply1(arg02.getCar()), obj3);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "arg0", -2, obj5);
                throw th2;
            }
        }
    }

    public static Object unzip2(Object lis) {
        Object obj;
        new frame();
        Object obj2 = obj;
        return frame.lambda2recur(lis);
    }

    /* compiled from: srfi1.scm */
    public class frame extends ModuleBody {
        public frame() {
        }

        public static Object lambda2recur(Object lis) {
            frame0 frame0;
            Object lis2;
            new frame0();
            frame0 frame02 = frame0;
            frame02.lis = lis;
            if (srfi1.isNullList(frame02.lis) != Boolean.FALSE) {
                Object[] objArr = new Object[2];
                objArr[0] = frame02.lis;
                Object[] objArr2 = objArr;
                objArr2[1] = frame02.lis;
                lis2 = misc.values(objArr2);
            } else {
                frame02.elt = C1245lists.car.apply1(frame02.lis);
                lis2 = call_with_values.callWithValues(frame02.lambda$Fn1, frame02.lambda$Fn2);
            }
            return lis2;
        }
    }

    /* compiled from: srfi1.scm */
    public class frame0 extends ModuleBody {
        Object elt;
        final ModuleMethod lambda$Fn1;
        final ModuleMethod lambda$Fn2;
        Object lis;

        public frame0() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 1, (Object) null, 0);
            this.lambda$Fn1 = moduleMethod;
            new ModuleMethod(this, 2, (Object) null, 8194);
            ModuleMethod moduleMethod3 = moduleMethod2;
            moduleMethod3.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:627");
            this.lambda$Fn2 = moduleMethod3;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 1 ? lambda3() : super.apply0(moduleMethod2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 1) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            if (moduleMethod2.selector == 2) {
                return lambda4(obj3, obj4);
            }
            return super.apply2(moduleMethod2, obj3, obj4);
        }

        /* access modifiers changed from: package-private */
        public Object lambda3() {
            return frame.lambda2recur(C1245lists.cdr.apply1(this.lis));
        }

        /* access modifiers changed from: package-private */
        public Object lambda4(Object a, Object b) {
            Object[] objArr = new Object[2];
            objArr[0] = C1245lists.cons(C1245lists.car.apply1(this.elt), a);
            Object[] objArr2 = objArr;
            objArr2[1] = C1245lists.cons(C1245lists.cadr.apply1(this.elt), b);
            return misc.values(objArr2);
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 2) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }
    }

    public static Object unzip3(Object lis) {
        Object obj;
        new frame1();
        Object obj2 = obj;
        return frame1.lambda5recur(lis);
    }

    /* compiled from: srfi1.scm */
    public class frame1 extends ModuleBody {
        public frame1() {
        }

        public static Object lambda5recur(Object lis) {
            frame2 frame2;
            Object lis2;
            new frame2();
            frame2 frame22 = frame2;
            frame22.lis = lis;
            if (srfi1.isNullList(frame22.lis) != Boolean.FALSE) {
                Object[] objArr = new Object[3];
                objArr[0] = frame22.lis;
                Object[] objArr2 = objArr;
                objArr2[1] = frame22.lis;
                Object[] objArr3 = objArr2;
                objArr3[2] = frame22.lis;
                lis2 = misc.values(objArr3);
            } else {
                frame22.elt = C1245lists.car.apply1(frame22.lis);
                lis2 = call_with_values.callWithValues(frame22.lambda$Fn3, frame22.lambda$Fn4);
            }
            return lis2;
        }
    }

    /* compiled from: srfi1.scm */
    public class frame2 extends ModuleBody {
        Object elt;
        final ModuleMethod lambda$Fn3;
        final ModuleMethod lambda$Fn4;
        Object lis;

        public frame2() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 3, (Object) null, 0);
            this.lambda$Fn3 = moduleMethod;
            new ModuleMethod(this, 4, (Object) null, 12291);
            ModuleMethod moduleMethod3 = moduleMethod2;
            moduleMethod3.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:635");
            this.lambda$Fn4 = moduleMethod3;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 3 ? lambda6() : super.apply0(moduleMethod2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 3) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            if (moduleMethod2.selector == 4) {
                return lambda7(obj4, obj5, obj6);
            }
            return super.apply3(moduleMethod2, obj4, obj5, obj6);
        }

        /* access modifiers changed from: package-private */
        public Object lambda6() {
            return frame1.lambda5recur(C1245lists.cdr.apply1(this.lis));
        }

        /* access modifiers changed from: package-private */
        public Object lambda7(Object a, Object b, Object c) {
            Object[] objArr = new Object[3];
            objArr[0] = C1245lists.cons(C1245lists.car.apply1(this.elt), a);
            Object[] objArr2 = objArr;
            objArr2[1] = C1245lists.cons(C1245lists.cadr.apply1(this.elt), b);
            Object[] objArr3 = objArr2;
            objArr3[2] = C1245lists.cons(C1245lists.caddr.apply1(this.elt), c);
            return misc.values(objArr3);
        }

        public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj4 = obj;
            Object obj5 = obj2;
            Object obj6 = obj3;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 4) {
                return super.match3(moduleMethod2, obj4, obj5, obj6, callContext2);
            }
            callContext2.value1 = obj4;
            callContext2.value2 = obj5;
            callContext2.value3 = obj6;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 3;
            return 0;
        }
    }

    public static Object unzip4(Object lis) {
        Object obj;
        new frame3();
        Object obj2 = obj;
        return frame3.lambda8recur(lis);
    }

    /* compiled from: srfi1.scm */
    public class frame3 extends ModuleBody {
        public frame3() {
        }

        public static Object lambda8recur(Object lis) {
            frame4 frame4;
            Object lis2;
            new frame4();
            frame4 frame42 = frame4;
            frame42.lis = lis;
            if (srfi1.isNullList(frame42.lis) != Boolean.FALSE) {
                Object[] objArr = new Object[4];
                objArr[0] = frame42.lis;
                Object[] objArr2 = objArr;
                objArr2[1] = frame42.lis;
                Object[] objArr3 = objArr2;
                objArr3[2] = frame42.lis;
                Object[] objArr4 = objArr3;
                objArr4[3] = frame42.lis;
                lis2 = misc.values(objArr4);
            } else {
                frame42.elt = C1245lists.car.apply1(frame42.lis);
                lis2 = call_with_values.callWithValues(frame42.lambda$Fn5, frame42.lambda$Fn6);
            }
            return lis2;
        }
    }

    /* compiled from: srfi1.scm */
    public class frame4 extends ModuleBody {
        Object elt;
        final ModuleMethod lambda$Fn5;
        final ModuleMethod lambda$Fn6;
        Object lis;

        public frame4() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 5, (Object) null, 0);
            this.lambda$Fn5 = moduleMethod;
            new ModuleMethod(this, 6, (Object) null, 16388);
            ModuleMethod moduleMethod3 = moduleMethod2;
            moduleMethod3.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:644");
            this.lambda$Fn6 = moduleMethod3;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 5 ? lambda9() : super.apply0(moduleMethod2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 5) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public Object apply4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj5 = obj;
            Object obj6 = obj2;
            Object obj7 = obj3;
            Object obj8 = obj4;
            if (moduleMethod2.selector == 6) {
                return lambda10(obj5, obj6, obj7, obj8);
            }
            return super.apply4(moduleMethod2, obj5, obj6, obj7, obj8);
        }

        /* access modifiers changed from: package-private */
        public Object lambda10(Object a, Object b, Object c, Object d) {
            Object[] objArr = new Object[4];
            objArr[0] = C1245lists.cons(C1245lists.car.apply1(this.elt), a);
            Object[] objArr2 = objArr;
            objArr2[1] = C1245lists.cons(C1245lists.cadr.apply1(this.elt), b);
            Object[] objArr3 = objArr2;
            objArr3[2] = C1245lists.cons(C1245lists.caddr.apply1(this.elt), c);
            Object[] objArr4 = objArr3;
            objArr4[3] = C1245lists.cons(C1245lists.cadddr.apply1(this.elt), d);
            return misc.values(objArr4);
        }

        /* access modifiers changed from: package-private */
        public Object lambda9() {
            return frame3.lambda8recur(C1245lists.cdr.apply1(this.lis));
        }

        public int match4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj5 = obj;
            Object obj6 = obj2;
            Object obj7 = obj3;
            Object obj8 = obj4;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 6) {
                return super.match4(moduleMethod2, obj5, obj6, obj7, obj8, callContext2);
            }
            callContext2.value1 = obj5;
            callContext2.value2 = obj6;
            callContext2.value3 = obj7;
            callContext2.value4 = obj8;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 4;
            return 0;
        }
    }

    public static Object unzip5(Object lis) {
        Object obj;
        new frame5();
        Object obj2 = obj;
        return frame5.lambda11recur(lis);
    }

    /* compiled from: srfi1.scm */
    public class frame5 extends ModuleBody {
        public frame5() {
        }

        public static Object lambda11recur(Object lis) {
            frame6 frame6;
            Object lis2;
            new frame6();
            frame6 frame62 = frame6;
            frame62.lis = lis;
            if (srfi1.isNullList(frame62.lis) != Boolean.FALSE) {
                Object[] objArr = new Object[5];
                objArr[0] = frame62.lis;
                Object[] objArr2 = objArr;
                objArr2[1] = frame62.lis;
                Object[] objArr3 = objArr2;
                objArr3[2] = frame62.lis;
                Object[] objArr4 = objArr3;
                objArr4[3] = frame62.lis;
                Object[] objArr5 = objArr4;
                objArr5[4] = frame62.lis;
                lis2 = misc.values(objArr5);
            } else {
                frame62.elt = C1245lists.car.apply1(frame62.lis);
                lis2 = call_with_values.callWithValues(frame62.lambda$Fn7, frame62.lambda$Fn8);
            }
            return lis2;
        }
    }

    /* compiled from: srfi1.scm */
    public class frame6 extends ModuleBody {
        Object elt;
        final ModuleMethod lambda$Fn7;
        final ModuleMethod lambda$Fn8;
        Object lis;

        public frame6() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 7, (Object) null, 0);
            this.lambda$Fn7 = moduleMethod;
            new ModuleMethod(this, 8, (Object) null, 20485);
            ModuleMethod moduleMethod3 = moduleMethod2;
            moduleMethod3.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:654");
            this.lambda$Fn8 = moduleMethod3;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 7 ? lambda12() : super.apply0(moduleMethod2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 7) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object[] objArr2 = objArr;
            if (moduleMethod2.selector == 8) {
                return lambda13(objArr2[0], objArr2[1], objArr2[2], objArr2[3], objArr2[4]);
            }
            return super.applyN(moduleMethod2, objArr2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda12() {
            return frame5.lambda11recur(C1245lists.cdr.apply1(this.lis));
        }

        /* access modifiers changed from: package-private */
        public Object lambda13(Object a, Object b, Object c, Object d, Object e) {
            Object[] objArr = new Object[5];
            objArr[0] = C1245lists.cons(C1245lists.car.apply1(this.elt), a);
            Object[] objArr2 = objArr;
            objArr2[1] = C1245lists.cons(C1245lists.cadr.apply1(this.elt), b);
            Object[] objArr3 = objArr2;
            objArr3[2] = C1245lists.cons(C1245lists.caddr.apply1(this.elt), c);
            Object[] objArr4 = objArr3;
            objArr4[3] = C1245lists.cons(C1245lists.cadddr.apply1(this.elt), d);
            Object[] objArr5 = objArr4;
            objArr5[4] = C1245lists.cons(C1245lists.car.apply1(C1245lists.cddddr.apply1(this.elt)), e);
            return misc.values(objArr5);
        }

        public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object[] objArr2 = objArr;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 8) {
                return super.matchN(moduleMethod2, objArr2, callContext2);
            }
            callContext2.values = objArr2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 5;
            return 0;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v0, resolved type: gnu.lists.Pair} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v21, resolved type: gnu.lists.Pair} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object append$Ex$V(java.lang.Object[] r19) {
        /*
            r0 = r19
            r11 = r0
            r12 = 0
            gnu.lists.LList r11 = gnu.lists.LList.makeList(r11, r12)
            r17 = r11
            r11 = r17
            r12 = r17
            r2 = r12
            r1 = r11
            r11 = r1
            gnu.lists.LList r12 = gnu.lists.LList.Empty
            r3 = r12
            r2 = r11
        L_0x0015:
            r11 = r2
            boolean r11 = kawa.lib.C1245lists.isPair(r11)
            if (r11 != 0) goto L_0x001f
            r11 = r3
        L_0x001d:
            r0 = r11
            return r0
        L_0x001f:
            gnu.expr.GenericProc r11 = kawa.lib.C1245lists.car
            r12 = r2
            java.lang.Object r11 = r11.apply1(r12)
            gnu.expr.GenericProc r12 = kawa.lib.C1245lists.cdr
            r13 = r2
            java.lang.Object r12 = r12.apply1(r13)
            r5 = r12
            r4 = r11
            r11 = r4
            boolean r11 = kawa.lib.C1245lists.isPair(r11)
            if (r11 != 0) goto L_0x003b
            r11 = r5
            r12 = r4
            r3 = r12
            r2 = r11
            goto L_0x0015
        L_0x003b:
            r11 = r4
            r17 = r11
            r11 = r17
            r12 = r17
            r6 = r12
            gnu.lists.Pair r11 = (gnu.lists.Pair) r11     // Catch:{ ClassCastException -> 0x008e }
            java.lang.Object r11 = lastPair(r11)
            r12 = r5
            r7 = r12
            r6 = r11
        L_0x004c:
            r11 = r7
            boolean r11 = kawa.lib.C1245lists.isPair(r11)
            if (r11 == 0) goto L_0x008c
            gnu.expr.GenericProc r11 = kawa.lib.C1245lists.car
            r12 = r7
            java.lang.Object r11 = r11.apply1(r12)
            gnu.expr.GenericProc r12 = kawa.lib.C1245lists.cdr
            r13 = r7
            java.lang.Object r12 = r12.apply1(r13)
            r9 = r12
            r8 = r11
            r11 = r6
            r17 = r11
            r11 = r17
            r12 = r17
            r10 = r12
            gnu.lists.Pair r11 = (gnu.lists.Pair) r11     // Catch:{ ClassCastException -> 0x00ad }
            r12 = r8
            kawa.lib.C1245lists.setCdr$Ex(r11, r12)
            r11 = r8
            boolean r11 = kawa.lib.C1245lists.isPair(r11)
            if (r11 == 0) goto L_0x008a
            r11 = r8
            r17 = r11
            r11 = r17
            r12 = r17
            r10 = r12
            gnu.lists.Pair r11 = (gnu.lists.Pair) r11     // Catch:{ ClassCastException -> 0x00cc }
            java.lang.Object r11 = lastPair(r11)
        L_0x0086:
            r12 = r9
            r7 = r12
            r6 = r11
            goto L_0x004c
        L_0x008a:
            r11 = r6
            goto L_0x0086
        L_0x008c:
            r11 = r4
            goto L_0x001d
        L_0x008e:
            r11 = move-exception
            gnu.mapping.WrongType r12 = new gnu.mapping.WrongType
            r17 = r11
            r18 = r12
            r11 = r18
            r12 = r17
            r13 = r18
            r17 = r12
            r18 = r13
            r12 = r18
            r13 = r17
            java.lang.String r14 = "last-pair"
            r15 = 0
            r16 = r6
            r12.<init>((java.lang.ClassCastException) r13, (java.lang.String) r14, (int) r15, (java.lang.Object) r16)
            throw r11
        L_0x00ad:
            r11 = move-exception
            gnu.mapping.WrongType r12 = new gnu.mapping.WrongType
            r17 = r11
            r18 = r12
            r11 = r18
            r12 = r17
            r13 = r18
            r17 = r12
            r18 = r13
            r12 = r18
            r13 = r17
            java.lang.String r14 = "set-cdr!"
            r15 = 1
            r16 = r10
            r12.<init>((java.lang.ClassCastException) r13, (java.lang.String) r14, (int) r15, (java.lang.Object) r16)
            throw r11
        L_0x00cc:
            r11 = move-exception
            gnu.mapping.WrongType r12 = new gnu.mapping.WrongType
            r17 = r11
            r18 = r12
            r11 = r18
            r12 = r17
            r13 = r18
            r17 = r12
            r18 = r13
            r12 = r18
            r13 = r17
            java.lang.String r14 = "last-pair"
            r15 = 0
            r16 = r10
            r12.<init>((java.lang.ClassCastException) r13, (java.lang.String) r14, (int) r15, (java.lang.Object) r16)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.srfi1.append$Ex$V(java.lang.Object[]):java.lang.Object");
    }

    public static Object appendReverse(Object rev$Mnhead, Object tail) {
        Object obj = rev$Mnhead;
        Object obj2 = tail;
        while (true) {
            Object obj3 = obj2;
            Object rev$Mnhead2 = obj;
            if (isNullList(rev$Mnhead2) != Boolean.FALSE) {
                return obj3;
            }
            obj = C1245lists.cdr.apply1(rev$Mnhead2);
            obj2 = C1245lists.cons(C1245lists.car.apply1(rev$Mnhead2), obj3);
        }
    }

    public static Object appendReverse$Ex(Object rev$Mnhead, Object tail) {
        Throwable th;
        Object obj = rev$Mnhead;
        Object obj2 = tail;
        while (true) {
            Object obj3 = obj2;
            Object rev$Mnhead2 = obj;
            if (isNullList(rev$Mnhead2) != Boolean.FALSE) {
                return obj3;
            }
            Object next$Mnrev = C1245lists.cdr.apply1(rev$Mnhead2);
            Object obj4 = rev$Mnhead2;
            Object obj5 = obj4;
            try {
                C1245lists.setCdr$Ex((Pair) obj4, obj3);
                obj = next$Mnrev;
                obj2 = rev$Mnhead2;
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "set-cdr!", 1, obj5);
                throw th2;
            }
        }
    }

    public static Object concatenate(Object lists) {
        return reduceRight(append.append, LList.Empty, lists);
    }

    public static Object concatenate$Ex(Object lists) {
        return reduceRight(append$Ex, LList.Empty, lists);
    }

    static Object $PcCdrs(Object obj) {
        Continuation continuation;
        Object lists;
        frame55 frame552;
        Object lists2 = obj;
        Continuation continuation2 = continuation;
        CallContext instance = CallContext.getInstance();
        CallContext callContext = instance;
        new Continuation(instance);
        Continuation continuation3 = continuation2;
        Continuation abort = continuation3;
        try {
            new frame55();
            frame55 frame553 = frame552;
            frame553.abort = abort;
            Object lambda74recur = frame553.lambda74recur(lists2);
            continuation3.invoked = true;
            lists = lambda74recur;
        } catch (Throwable th) {
            lists = Continuation.handleException(th, continuation3);
        }
        return lists;
    }

    /* compiled from: srfi1.scm */
    public class frame55 extends ModuleBody {
        Continuation abort;

        public frame55() {
        }

        public Object lambda74recur(Object obj) {
            Object obj2;
            Object lists = obj;
            if (C1245lists.isPair(lists)) {
                Object lis = C1245lists.car.apply1(lists);
                obj2 = srfi1.isNullList(lis) != Boolean.FALSE ? this.abort.apply1(LList.Empty) : C1245lists.cons(C1245lists.cdr.apply1(lis), lambda74recur(C1245lists.cdr.apply1(lists)));
            } else {
                obj2 = LList.Empty;
            }
            return obj2;
        }
    }

    static Object $PcCars$Pl(Object lists, Object lastElt) {
        frame56 frame562;
        new frame56();
        frame56 frame563 = frame562;
        frame563.last$Mnelt = lastElt;
        return frame563.lambda75recur(lists);
    }

    /* compiled from: srfi1.scm */
    public class frame56 extends ModuleBody {
        Object last$Mnelt;

        public frame56() {
        }

        public Object lambda75recur(Object obj) {
            Object lists = obj;
            return C1245lists.isPair(lists) ? C1245lists.cons(C1245lists.caar.apply1(lists), lambda75recur(C1245lists.cdr.apply1(lists))) : LList.list1(this.last$Mnelt);
        }
    }

    static Object $PcCars$PlCdrs(Object obj) {
        Continuation continuation;
        Object lists;
        frame57 frame572;
        Object lists2 = obj;
        Continuation continuation2 = continuation;
        CallContext instance = CallContext.getInstance();
        CallContext callContext = instance;
        new Continuation(instance);
        Continuation continuation3 = continuation2;
        Continuation abort = continuation3;
        try {
            new frame57();
            frame57 frame573 = frame572;
            frame573.abort = abort;
            Object lambda76recur = frame573.lambda76recur(lists2);
            continuation3.invoked = true;
            lists = lambda76recur;
        } catch (Throwable th) {
            lists = Continuation.handleException(th, continuation3);
        }
        return lists;
    }

    /* compiled from: srfi1.scm */
    public class frame57 extends ModuleBody {
        Continuation abort;

        public frame57() {
        }

        public Object lambda76recur(Object lists) {
            frame58 frame58;
            Object values;
            new frame58();
            frame58 frame582 = frame58;
            frame582.staticLink = this;
            frame58 frame583 = frame582;
            frame583.lists = lists;
            if (C1245lists.isPair(frame583.lists)) {
                values = call_with_values.callWithValues(frame583.lambda$Fn57, frame583.lambda$Fn58);
            } else {
                Object[] objArr = new Object[2];
                objArr[0] = LList.Empty;
                Object[] objArr2 = objArr;
                objArr2[1] = LList.Empty;
                values = misc.values(objArr2);
            }
            return values;
        }
    }

    /* compiled from: srfi1.scm */
    public class frame58 extends ModuleBody {
        final ModuleMethod lambda$Fn57;
        final ModuleMethod lambda$Fn58;
        Object lists;
        frame57 staticLink;

        public frame58() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 61, (Object) null, 0);
            this.lambda$Fn57 = moduleMethod;
            new ModuleMethod(this, 62, (Object) null, 8194);
            ModuleMethod moduleMethod3 = moduleMethod2;
            moduleMethod3.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:762");
            this.lambda$Fn58 = moduleMethod3;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 61 ? lambda77() : super.apply0(moduleMethod2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 61) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            if (moduleMethod2.selector == 62) {
                return lambda78(obj3, obj4);
            }
            return super.apply2(moduleMethod2, obj3, obj4);
        }

        /* access modifiers changed from: package-private */
        public Object lambda77() {
            return srfi1.car$PlCdr(this.lists);
        }

        /* access modifiers changed from: package-private */
        public Object lambda78(Object list, Object otherLists) {
            frame59 frame59;
            Object callWithValues;
            new frame59();
            frame59 frame592 = frame59;
            frame592.staticLink = this;
            frame59 frame593 = frame592;
            frame593.list = list;
            frame593.other$Mnlists = otherLists;
            if (srfi1.isNullList(frame593.list) != Boolean.FALSE) {
                callWithValues = this.staticLink.abort.apply2(LList.Empty, LList.Empty);
            } else {
                callWithValues = call_with_values.callWithValues(frame593.lambda$Fn59, frame593.lambda$Fn60);
            }
            return callWithValues;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 62) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    public class frame59 extends ModuleBody {
        final ModuleMethod lambda$Fn59;
        final ModuleMethod lambda$Fn60;
        Object list;
        Object other$Mnlists;
        frame58 staticLink;

        public frame59() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 59, (Object) null, 0);
            this.lambda$Fn59 = moduleMethod;
            new ModuleMethod(this, 60, (Object) null, 8194);
            ModuleMethod moduleMethod3 = moduleMethod2;
            moduleMethod3.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:764");
            this.lambda$Fn60 = moduleMethod3;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 59 ? lambda79() : super.apply0(moduleMethod2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 59) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            if (moduleMethod2.selector == 60) {
                return lambda80(obj3, obj4);
            }
            return super.apply2(moduleMethod2, obj3, obj4);
        }

        /* access modifiers changed from: package-private */
        public Object lambda79() {
            return srfi1.car$PlCdr(this.list);
        }

        /* access modifiers changed from: package-private */
        public Object lambda80(Object a, Object d) {
            frame60 frame60;
            new frame60();
            frame60 frame602 = frame60;
            frame602.staticLink = this;
            frame60 frame603 = frame602;
            frame603.f136a = a;
            frame603.f137d = d;
            return call_with_values.callWithValues(frame603.lambda$Fn61, frame603.lambda$Fn62);
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 60) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    public class frame60 extends ModuleBody {

        /* renamed from: a */
        Object f136a;

        /* renamed from: d */
        Object f137d;
        final ModuleMethod lambda$Fn61;
        final ModuleMethod lambda$Fn62;
        frame59 staticLink;

        public frame60() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 57, (Object) null, 0);
            this.lambda$Fn61 = moduleMethod;
            new ModuleMethod(this, 58, (Object) null, 8194);
            ModuleMethod moduleMethod3 = moduleMethod2;
            moduleMethod3.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:765");
            this.lambda$Fn62 = moduleMethod3;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 57 ? lambda81() : super.apply0(moduleMethod2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 57) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            if (moduleMethod2.selector == 58) {
                return lambda82(obj3, obj4);
            }
            return super.apply2(moduleMethod2, obj3, obj4);
        }

        /* access modifiers changed from: package-private */
        public Object lambda81() {
            return this.staticLink.staticLink.staticLink.lambda76recur(this.staticLink.other$Mnlists);
        }

        /* access modifiers changed from: package-private */
        public Object lambda82(Object cars, Object cdrs) {
            Object[] objArr = new Object[2];
            objArr[0] = C1245lists.cons(this.f136a, cars);
            Object[] objArr2 = objArr;
            objArr2[1] = C1245lists.cons(this.f137d, cdrs);
            return misc.values(objArr2);
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 58) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    public class frame61 extends ModuleBody {
        final ModuleMethod lambda$Fn63;
        Object lists;

        public frame61() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 63, (Object) null, 0);
            this.lambda$Fn63 = moduleMethod;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 63 ? lambda83() : super.apply0(moduleMethod2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 63) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        static Pair lambda84(Object x, Object x2) {
            return C1245lists.cons(x, x2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda83() {
            return srfi1.$PcCars$PlCdrs(this.lists);
        }
    }

    static Object $PcCars$PlCdrs$SlPair(Object lists) {
        frame61 frame612;
        new frame61();
        frame61 frame613 = frame612;
        frame613.lists = lists;
        return call_with_values.callWithValues(frame613.lambda$Fn63, lambda$Fn64);
    }

    static Object $PcCars$PlCdrs$Pl(Object obj, Object carsFinal) {
        frame62 frame622;
        Continuation continuation;
        Object lists;
        frame63 frame632;
        Object lists2 = obj;
        new frame62();
        frame62 frame623 = frame622;
        frame623.cars$Mnfinal = carsFinal;
        Continuation continuation2 = continuation;
        CallContext instance = CallContext.getInstance();
        CallContext callContext = instance;
        new Continuation(instance);
        Continuation continuation3 = continuation2;
        Continuation abort = continuation3;
        frame62 closureEnv = frame623;
        try {
            new frame63();
            frame63 frame633 = frame632;
            frame633.staticLink = closureEnv;
            frame63 frame634 = frame633;
            frame634.abort = abort;
            Object lambda85recur = frame634.lambda85recur(lists2);
            continuation3.invoked = true;
            lists = lambda85recur;
        } catch (Throwable th) {
            lists = Continuation.handleException(th, continuation3);
        }
        return lists;
    }

    /* compiled from: srfi1.scm */
    public class frame63 extends ModuleBody {
        Continuation abort;
        frame62 staticLink;

        public frame63() {
        }

        public Object lambda85recur(Object lists) {
            frame64 frame64;
            Object values;
            new frame64();
            frame64 frame642 = frame64;
            frame642.staticLink = this;
            frame64 frame643 = frame642;
            frame643.lists = lists;
            if (C1245lists.isPair(frame643.lists)) {
                values = call_with_values.callWithValues(frame643.lambda$Fn65, frame643.lambda$Fn66);
            } else {
                Object[] objArr = new Object[2];
                objArr[0] = LList.list1(this.staticLink.cars$Mnfinal);
                Object[] objArr2 = objArr;
                objArr2[1] = LList.Empty;
                values = misc.values(objArr2);
            }
            return values;
        }
    }

    /* compiled from: srfi1.scm */
    public class frame64 extends ModuleBody {
        final ModuleMethod lambda$Fn65;
        final ModuleMethod lambda$Fn66;
        Object lists;
        frame63 staticLink;

        public frame64() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 68, (Object) null, 0);
            this.lambda$Fn65 = moduleMethod;
            new ModuleMethod(this, 69, (Object) null, 8194);
            ModuleMethod moduleMethod3 = moduleMethod2;
            moduleMethod3.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:783");
            this.lambda$Fn66 = moduleMethod3;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 68 ? lambda86() : super.apply0(moduleMethod2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 68) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            if (moduleMethod2.selector == 69) {
                return lambda87(obj3, obj4);
            }
            return super.apply2(moduleMethod2, obj3, obj4);
        }

        /* access modifiers changed from: package-private */
        public Object lambda86() {
            return srfi1.car$PlCdr(this.lists);
        }

        /* access modifiers changed from: package-private */
        public Object lambda87(Object list, Object otherLists) {
            frame65 frame65;
            Object callWithValues;
            new frame65();
            frame65 frame652 = frame65;
            frame652.staticLink = this;
            frame65 frame653 = frame652;
            frame653.list = list;
            frame653.other$Mnlists = otherLists;
            if (srfi1.isNullList(frame653.list) != Boolean.FALSE) {
                callWithValues = this.staticLink.abort.apply2(LList.Empty, LList.Empty);
            } else {
                callWithValues = call_with_values.callWithValues(frame653.lambda$Fn67, frame653.lambda$Fn68);
            }
            return callWithValues;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 69) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    public class frame65 extends ModuleBody {
        final ModuleMethod lambda$Fn67;
        final ModuleMethod lambda$Fn68;
        Object list;
        Object other$Mnlists;
        frame64 staticLink;

        public frame65() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 66, (Object) null, 0);
            this.lambda$Fn67 = moduleMethod;
            new ModuleMethod(this, 67, (Object) null, 8194);
            ModuleMethod moduleMethod3 = moduleMethod2;
            moduleMethod3.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:785");
            this.lambda$Fn68 = moduleMethod3;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 66 ? lambda88() : super.apply0(moduleMethod2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 66) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            if (moduleMethod2.selector == 67) {
                return lambda89(obj3, obj4);
            }
            return super.apply2(moduleMethod2, obj3, obj4);
        }

        /* access modifiers changed from: package-private */
        public Object lambda88() {
            return srfi1.car$PlCdr(this.list);
        }

        /* access modifiers changed from: package-private */
        public Object lambda89(Object a, Object d) {
            frame66 frame66;
            new frame66();
            frame66 frame662 = frame66;
            frame662.staticLink = this;
            frame66 frame663 = frame662;
            frame663.f138a = a;
            frame663.f139d = d;
            return call_with_values.callWithValues(frame663.lambda$Fn69, frame663.lambda$Fn70);
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 67) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    public class frame66 extends ModuleBody {

        /* renamed from: a */
        Object f138a;

        /* renamed from: d */
        Object f139d;
        final ModuleMethod lambda$Fn69;
        final ModuleMethod lambda$Fn70;
        frame65 staticLink;

        public frame66() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 64, (Object) null, 0);
            this.lambda$Fn69 = moduleMethod;
            new ModuleMethod(this, 65, (Object) null, 8194);
            ModuleMethod moduleMethod3 = moduleMethod2;
            moduleMethod3.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:786");
            this.lambda$Fn70 = moduleMethod3;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 64 ? lambda90() : super.apply0(moduleMethod2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 64) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            if (moduleMethod2.selector == 65) {
                return lambda91(obj3, obj4);
            }
            return super.apply2(moduleMethod2, obj3, obj4);
        }

        /* access modifiers changed from: package-private */
        public Object lambda90() {
            return this.staticLink.staticLink.staticLink.lambda85recur(this.staticLink.other$Mnlists);
        }

        /* access modifiers changed from: package-private */
        public Object lambda91(Object cars, Object cdrs) {
            Object[] objArr = new Object[2];
            objArr[0] = C1245lists.cons(this.f138a, cars);
            Object[] objArr2 = objArr;
            objArr2[1] = C1245lists.cons(this.f139d, cdrs);
            return misc.values(objArr2);
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 65) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }
    }

    static Object $PcCars$PlCdrs$SlNoTest(Object lists) {
        Object obj;
        new frame67();
        Object obj2 = obj;
        return frame67.lambda92recur(lists);
    }

    /* compiled from: srfi1.scm */
    public class frame67 extends ModuleBody {
        public frame67() {
        }

        public static Object lambda92recur(Object lists) {
            frame68 frame68;
            Object lists2;
            new frame68();
            frame68 frame682 = frame68;
            frame682.lists = lists;
            if (C1245lists.isPair(frame682.lists)) {
                lists2 = call_with_values.callWithValues(frame682.lambda$Fn71, frame682.lambda$Fn72);
            } else {
                Object[] objArr = new Object[2];
                objArr[0] = LList.Empty;
                Object[] objArr2 = objArr;
                objArr2[1] = LList.Empty;
                lists2 = misc.values(objArr2);
            }
            return lists2;
        }
    }

    /* compiled from: srfi1.scm */
    public class frame68 extends ModuleBody {
        final ModuleMethod lambda$Fn71;
        final ModuleMethod lambda$Fn72;
        Object lists;

        public frame68() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 74, (Object) null, 0);
            this.lambda$Fn71 = moduleMethod;
            new ModuleMethod(this, 75, (Object) null, 8194);
            ModuleMethod moduleMethod3 = moduleMethod2;
            moduleMethod3.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:794");
            this.lambda$Fn72 = moduleMethod3;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 74 ? lambda93() : super.apply0(moduleMethod2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 74) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            if (moduleMethod2.selector == 75) {
                return lambda94(obj3, obj4);
            }
            return super.apply2(moduleMethod2, obj3, obj4);
        }

        /* access modifiers changed from: package-private */
        public Object lambda93() {
            return srfi1.car$PlCdr(this.lists);
        }

        /* access modifiers changed from: package-private */
        public Object lambda94(Object list, Object otherLists) {
            frame69 frame69;
            new frame69();
            frame69 frame692 = frame69;
            frame692.staticLink = this;
            frame69 frame693 = frame692;
            frame693.list = list;
            frame693.other$Mnlists = otherLists;
            return call_with_values.callWithValues(frame693.lambda$Fn73, frame693.lambda$Fn74);
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 75) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    public class frame69 extends ModuleBody {
        final ModuleMethod lambda$Fn73;
        final ModuleMethod lambda$Fn74;
        Object list;
        Object other$Mnlists;
        frame68 staticLink;

        public frame69() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 72, (Object) null, 0);
            this.lambda$Fn73 = moduleMethod;
            new ModuleMethod(this, 73, (Object) null, 8194);
            ModuleMethod moduleMethod3 = moduleMethod2;
            moduleMethod3.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:795");
            this.lambda$Fn74 = moduleMethod3;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 72 ? lambda95() : super.apply0(moduleMethod2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 72) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            if (moduleMethod2.selector == 73) {
                return lambda96(obj3, obj4);
            }
            return super.apply2(moduleMethod2, obj3, obj4);
        }

        /* access modifiers changed from: package-private */
        public Object lambda95() {
            return srfi1.car$PlCdr(this.list);
        }

        /* access modifiers changed from: package-private */
        public Object lambda96(Object a, Object d) {
            frame70 frame70;
            new frame70();
            frame70 frame702 = frame70;
            frame702.staticLink = this;
            frame70 frame703 = frame702;
            frame703.f140a = a;
            frame703.f141d = d;
            return call_with_values.callWithValues(frame703.lambda$Fn75, frame703.lambda$Fn76);
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 73) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    public class frame70 extends ModuleBody {

        /* renamed from: a */
        Object f140a;

        /* renamed from: d */
        Object f141d;
        final ModuleMethod lambda$Fn75;
        final ModuleMethod lambda$Fn76;
        frame69 staticLink;

        public frame70() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 70, (Object) null, 0);
            this.lambda$Fn75 = moduleMethod;
            new ModuleMethod(this, 71, (Object) null, 8194);
            ModuleMethod moduleMethod3 = moduleMethod2;
            moduleMethod3.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:796");
            this.lambda$Fn76 = moduleMethod3;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 70 ? lambda97() : super.apply0(moduleMethod2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 70) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            if (moduleMethod2.selector == 71) {
                return lambda98(obj3, obj4);
            }
            return super.apply2(moduleMethod2, obj3, obj4);
        }

        /* access modifiers changed from: package-private */
        public Object lambda97() {
            return frame67.lambda92recur(this.staticLink.other$Mnlists);
        }

        /* access modifiers changed from: package-private */
        public Object lambda98(Object cars, Object cdrs) {
            Object[] objArr = new Object[2];
            objArr[0] = C1245lists.cons(this.f140a, cars);
            Object[] objArr2 = objArr;
            objArr2[1] = C1245lists.cons(this.f141d, cdrs);
            return misc.values(objArr2);
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 71) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    public class frame71 extends ModuleBody {
        final ModuleMethod lambda$Fn77;
        Object lists;

        public frame71() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 76, (Object) null, 0);
            this.lambda$Fn77 = moduleMethod;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 76 ? lambda99() : super.apply0(moduleMethod2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 76) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        static Pair lambda100(Object x, Object x2) {
            return C1245lists.cons(x, x2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda99() {
            return srfi1.$PcCars$PlCdrs$SlNoTest(this.lists);
        }
    }

    static Object $PcCars$PlCdrs$SlNoTest$SlPair(Object lists) {
        frame71 frame712;
        new frame71();
        frame71 frame713 = frame712;
        frame713.lists = lists;
        return call_with_values.callWithValues(frame713.lambda$Fn77, lambda$Fn78);
    }

    public static Object count$V(Procedure procedure, Object obj, Object[] argsArray) {
        Object i;
        Object obj2;
        Procedure pred = procedure;
        Object list1 = obj;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        LList lists = makeList;
        if (C1245lists.isPair(lists)) {
            Object obj3 = list1;
            LList lList2 = lists;
            Object obj4 = Lit0;
            while (true) {
                Object i2 = obj4;
                Object obj5 = lList2;
                Object list12 = obj3;
                if (isNullList(list12) != Boolean.FALSE) {
                    obj2 = i2;
                    break;
                }
                Object split = $PcCars$PlCdrs$SlPair(obj5);
                Object a$Mns = C1245lists.car.apply1(split);
                Object d$Mns = C1245lists.cdr.apply1(split);
                if (C1245lists.isNull(a$Mns)) {
                    obj2 = i2;
                    break;
                }
                obj3 = C1245lists.cdr.apply1(list12);
                lList2 = d$Mns;
                if (Scheme.apply.apply3(pred, C1245lists.car.apply1(list12), a$Mns) != Boolean.FALSE) {
                    obj4 = AddOp.$Pl.apply2(i2, Lit1);
                } else {
                    obj4 = i2;
                }
            }
        } else {
            Object obj6 = list1;
            Object obj7 = Lit0;
            while (true) {
                i = obj7;
                Object lis = obj6;
                if (isNullList(lis) != Boolean.FALSE) {
                    break;
                }
                obj6 = C1245lists.cdr.apply1(lis);
                obj7 = pred.apply1(C1245lists.car.apply1(lis)) != Boolean.FALSE ? AddOp.$Pl.apply2(i, Lit1) : i;
            }
            obj2 = i;
        }
        return obj2;
    }

    public static Object unfoldRight(Procedure procedure, Procedure procedure2, Procedure procedure3, Object seed, Object maybe$Mntail) {
        Procedure p = procedure;
        Procedure f = procedure2;
        Procedure g = procedure3;
        Object obj = seed;
        Object obj2 = maybe$Mntail;
        while (true) {
            Object obj3 = obj2;
            Object seed2 = obj;
            if (p.apply1(seed2) != Boolean.FALSE) {
                return obj3;
            }
            obj = g.apply1(seed2);
            obj2 = C1245lists.cons(f.apply1(seed2), obj3);
        }
    }

    public Object apply4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj5 = obj;
        Object obj6 = obj2;
        Object obj7 = obj3;
        Object obj8 = obj4;
        if (moduleMethod2.selector != 123) {
            return super.apply4(moduleMethod2, obj5, obj6, obj7, obj8);
        }
        try {
            try {
                try {
                    return unfoldRight((Procedure) obj5, (Procedure) obj6, (Procedure) obj7, obj8);
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th4 = th3;
                    new WrongType(classCastException, "unfold-right", 3, obj7);
                    throw th4;
                }
            } catch (ClassCastException e2) {
                ClassCastException classCastException2 = e2;
                Throwable th5 = th2;
                new WrongType(classCastException2, "unfold-right", 2, obj6);
                throw th5;
            }
        } catch (ClassCastException e3) {
            ClassCastException classCastException3 = e3;
            Throwable th6 = th;
            new WrongType(classCastException3, "unfold-right", 1, obj5);
            throw th6;
        }
    }

    public int match4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj5 = obj;
        Object obj6 = obj2;
        Object obj7 = obj3;
        Object obj8 = obj4;
        CallContext callContext2 = callContext;
        if (moduleMethod2.selector != 123) {
            return super.match4(moduleMethod2, obj5, obj6, obj7, obj8, callContext2);
        }
        CallContext callContext3 = callContext2;
        Object obj9 = obj5;
        Object obj10 = obj9;
        if (!(obj9 instanceof Procedure)) {
            return -786431;
        }
        callContext3.value1 = obj10;
        CallContext callContext4 = callContext2;
        Object obj11 = obj6;
        Object obj12 = obj11;
        if (!(obj11 instanceof Procedure)) {
            return -786430;
        }
        callContext4.value2 = obj12;
        CallContext callContext5 = callContext2;
        Object obj13 = obj7;
        Object obj14 = obj13;
        if (!(obj13 instanceof Procedure)) {
            return -786429;
        }
        callContext5.value3 = obj14;
        callContext2.value4 = obj8;
        callContext2.proc = moduleMethod2;
        callContext2.f239pc = 4;
        return 0;
    }

    public static Object unfold$V(Procedure procedure, Procedure procedure2, Procedure procedure3, Object obj, Object[] argsArray) {
        LList lList;
        Throwable th;
        Object reverse$Ex;
        Object obj2;
        Object seed;
        Procedure p = procedure;
        Procedure f = procedure2;
        Procedure g = procedure3;
        Object seed2 = obj;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList2 = makeList;
        LList maybe$Mntail$Mngen = makeList;
        if (C1245lists.isPair(maybe$Mntail$Mngen)) {
            Object tail$Mngen = C1245lists.car.apply1(maybe$Mntail$Mngen);
            if (C1245lists.isPair(C1245lists.cdr.apply1(maybe$Mntail$Mngen))) {
                Apply apply = Scheme.apply;
                Object[] objArr = new Object[8];
                objArr[0] = misc.error;
                Object[] objArr2 = objArr;
                objArr2[1] = "Too many arguments";
                Object[] objArr3 = objArr2;
                objArr3[2] = unfold;
                Object[] objArr4 = objArr3;
                objArr4[3] = p;
                Object[] objArr5 = objArr4;
                objArr5[4] = f;
                Object[] objArr6 = objArr5;
                objArr6[5] = g;
                Object[] objArr7 = objArr6;
                objArr7[6] = seed2;
                Object[] objArr8 = objArr7;
                objArr8[7] = maybe$Mntail$Mngen;
                reverse$Ex = apply.applyN(objArr8);
            } else {
                Object obj3 = seed2;
                Object obj4 = LList.Empty;
                while (true) {
                    obj2 = obj4;
                    seed = obj3;
                    if (p.apply1(seed) != Boolean.FALSE) {
                        break;
                    }
                    obj3 = g.apply1(seed);
                    obj4 = C1245lists.cons(f.apply1(seed), obj2);
                }
                reverse$Ex = appendReverse$Ex(obj2, Scheme.applyToArgs.apply2(tail$Mngen, seed));
            }
        } else {
            Object obj5 = seed2;
            LList lList3 = LList.Empty;
            while (true) {
                lList = lList3;
                Object seed3 = obj5;
                if (p.apply1(seed3) != Boolean.FALSE) {
                    break;
                }
                obj5 = g.apply1(seed3);
                lList3 = C1245lists.cons(f.apply1(seed3), lList);
            }
            LList lList4 = lList;
            LList lList5 = lList4;
            try {
                reverse$Ex = C1245lists.reverse$Ex(lList4);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "reverse!", 1, (Object) lList5);
                throw th2;
            }
        }
        return reverse$Ex;
    }

    public static Object fold$V(Procedure kons, Object obj, Object obj2, Object[] argsArray) {
        frame7 frame73;
        Object obj3;
        Object obj4;
        Object knil = obj;
        Object lis1 = obj2;
        new frame7();
        frame7 frame74 = frame73;
        frame74.kons = kons;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        LList lists = makeList;
        if (C1245lists.isPair(lists)) {
            obj4 = frame74.lambda14lp(C1245lists.cons(lis1, lists), knil);
        } else {
            Object obj5 = lis1;
            Object obj6 = knil;
            while (true) {
                obj3 = obj6;
                Object lis = obj5;
                if (isNullList(lis) != Boolean.FALSE) {
                    break;
                }
                obj5 = C1245lists.cdr.apply1(lis);
                obj6 = frame74.kons.apply2(C1245lists.car.apply1(lis), obj3);
            }
            obj4 = obj3;
        }
        return obj4;
    }

    /* compiled from: srfi1.scm */
    public class frame7 extends ModuleBody {
        Procedure kons;

        public frame7() {
        }

        public Object lambda14lp(Object lists, Object ans) {
            frame8 frame8;
            new frame8();
            frame8 frame82 = frame8;
            frame82.staticLink = this;
            frame8 frame83 = frame82;
            frame83.lists = lists;
            frame83.ans = ans;
            return call_with_values.callWithValues(frame83.lambda$Fn9, frame83.lambda$Fn10);
        }
    }

    /* compiled from: srfi1.scm */
    public class frame8 extends ModuleBody {
        Object ans;
        final ModuleMethod lambda$Fn10;
        final ModuleMethod lambda$Fn9;
        Object lists;
        frame7 staticLink;

        public frame8() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 9, (Object) null, 0);
            this.lambda$Fn9 = moduleMethod;
            new ModuleMethod(this, 10, (Object) null, 8194);
            ModuleMethod moduleMethod3 = moduleMethod2;
            moduleMethod3.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:859");
            this.lambda$Fn10 = moduleMethod3;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 9 ? lambda15() : super.apply0(moduleMethod2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 9) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            if (moduleMethod2.selector == 10) {
                return lambda16(obj3, obj4);
            }
            return super.apply2(moduleMethod2, obj3, obj4);
        }

        /* access modifiers changed from: package-private */
        public Object lambda15() {
            return srfi1.$PcCars$PlCdrs$Pl(this.lists, this.ans);
        }

        /* access modifiers changed from: package-private */
        public Object lambda16(Object obj, Object obj2) {
            Object lambda14lp;
            Object cars$Plans = obj;
            Object cdrs = obj2;
            if (C1245lists.isNull(cars$Plans)) {
                lambda14lp = this.ans;
            } else {
                lambda14lp = this.staticLink.lambda14lp(cdrs, Scheme.apply.apply2(this.staticLink.kons, cars$Plans));
            }
            return lambda14lp;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 10) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }
    }

    public static Object foldRight$V(Procedure kons, Object knil, Object obj, Object[] argsArray) {
        frame9 frame92;
        Object lambda18recur;
        Object lis1 = obj;
        new frame9();
        frame9 frame93 = frame92;
        frame93.kons = kons;
        frame93.knil = knil;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        LList lists = makeList;
        if (C1245lists.isPair(lists)) {
            lambda18recur = frame93.lambda17recur(C1245lists.cons(lis1, lists));
        } else {
            lambda18recur = frame93.lambda18recur(lis1);
        }
        return lambda18recur;
    }

    /* compiled from: srfi1.scm */
    public class frame9 extends ModuleBody {
        Object knil;
        Procedure kons;

        public frame9() {
        }

        public Object lambda17recur(Object obj) {
            Object apply2;
            Object lists = obj;
            Object cdrs = srfi1.$PcCdrs(lists);
            if (C1245lists.isNull(cdrs)) {
                apply2 = this.knil;
            } else {
                apply2 = Scheme.apply.apply2(this.kons, srfi1.$PcCars$Pl(lists, lambda17recur(cdrs)));
            }
            return apply2;
        }

        public Object lambda18recur(Object obj) {
            Object apply2;
            Object lis = obj;
            if (srfi1.isNullList(lis) != Boolean.FALSE) {
                apply2 = this.knil;
            } else {
                apply2 = this.kons.apply2(C1245lists.car.apply1(lis), lambda18recur(C1245lists.cdr.apply1(lis)));
            }
            return apply2;
        }
    }

    public static Object pairFoldRight$V(Procedure f, Object zero, Object obj, Object[] argsArray) {
        frame10 frame102;
        Object lambda20recur;
        Object lis1 = obj;
        new frame10();
        frame10 frame103 = frame102;
        frame103.f121f = f;
        frame103.zero = zero;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        LList lists = makeList;
        if (C1245lists.isPair(lists)) {
            lambda20recur = frame103.lambda19recur(C1245lists.cons(lis1, lists));
        } else {
            lambda20recur = frame103.lambda20recur(lis1);
        }
        return lambda20recur;
    }

    /* compiled from: srfi1.scm */
    public class frame10 extends ModuleBody {

        /* renamed from: f */
        Procedure f121f;
        Object zero;

        public frame10() {
        }

        public Object lambda19recur(Object obj) {
            Object apply2;
            Object lists = obj;
            Object cdrs = srfi1.$PcCdrs(lists);
            if (C1245lists.isNull(cdrs)) {
                apply2 = this.zero;
            } else {
                Apply apply = Scheme.apply;
                Procedure procedure = this.f121f;
                Object[] objArr = new Object[2];
                objArr[0] = lists;
                Object[] objArr2 = objArr;
                objArr2[1] = LList.list1(lambda19recur(cdrs));
                apply2 = apply.apply2(procedure, srfi1.append$Ex$V(objArr2));
            }
            return apply2;
        }

        public Object lambda20recur(Object obj) {
            Object lis = obj;
            return srfi1.isNullList(lis) != Boolean.FALSE ? this.zero : this.f121f.apply2(lis, lambda20recur(C1245lists.cdr.apply1(lis)));
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v18, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v1, resolved type: gnu.lists.Pair} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v0, resolved type: gnu.lists.Pair} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object pairFold$V(gnu.mapping.Procedure r16, java.lang.Object r17, java.lang.Object r18, java.lang.Object[] r19) {
        /*
            r0 = r16
            r1 = r17
            r2 = r18
            r3 = r19
            r8 = r3
            r9 = 0
            gnu.lists.LList r8 = gnu.lists.LList.makeList(r8, r9)
            r15 = r8
            r8 = r15
            r9 = r15
            r5 = r9
            r4 = r8
            r8 = r4
            boolean r8 = kawa.lib.C1245lists.isPair(r8)
            if (r8 == 0) goto L_0x0057
            r8 = r2
            r9 = r4
            gnu.lists.Pair r8 = kawa.lib.C1245lists.cons(r8, r9)
            r9 = r1
            r6 = r9
            r5 = r8
        L_0x0023:
            r8 = r5
            java.lang.Object r8 = $PcCdrs(r8)
            r7 = r8
            r8 = r7
            boolean r8 = kawa.lib.C1245lists.isNull(r8)
            if (r8 == 0) goto L_0x0033
            r8 = r6
        L_0x0031:
            r0 = r8
            return r0
        L_0x0033:
            r8 = r7
            gnu.kawa.functions.Apply r9 = kawa.standard.Scheme.apply
            r10 = r0
            r11 = 2
            java.lang.Object[] r11 = new java.lang.Object[r11]
            r15 = r11
            r11 = r15
            r12 = r15
            r13 = 0
            r14 = r5
            r12[r13] = r14
            r15 = r11
            r11 = r15
            r12 = r15
            r13 = 1
            r14 = r6
            gnu.lists.Pair r14 = gnu.lists.LList.list1(r14)
            r12[r13] = r14
            java.lang.Object r11 = append$Ex$V(r11)
            java.lang.Object r9 = r9.apply2(r10, r11)
            r6 = r9
            r5 = r8
            goto L_0x0023
        L_0x0057:
            r8 = r2
            r9 = r1
            r6 = r9
            r5 = r8
        L_0x005b:
            r8 = r5
            java.lang.Object r8 = isNullList(r8)
            java.lang.Boolean r9 = java.lang.Boolean.FALSE
            if (r8 == r9) goto L_0x0066
            r8 = r6
            goto L_0x0031
        L_0x0066:
            gnu.expr.GenericProc r8 = kawa.lib.C1245lists.cdr
            r9 = r5
            java.lang.Object r8 = r8.apply1(r9)
            r7 = r8
            r8 = r7
            r9 = r0
            r10 = r5
            r11 = r6
            java.lang.Object r9 = r9.apply2(r10, r11)
            r6 = r9
            r5 = r8
            goto L_0x005b
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.srfi1.pairFold$V(gnu.mapping.Procedure, java.lang.Object, java.lang.Object, java.lang.Object[]):java.lang.Object");
    }

    public static Object reduce(Procedure procedure, Object obj, Object obj2) {
        Object fold$V;
        Procedure f = procedure;
        Object ridentity = obj;
        Object lis = obj2;
        if (isNullList(lis) != Boolean.FALSE) {
            fold$V = ridentity;
        } else {
            fold$V = fold$V(f, C1245lists.car.apply1(lis), C1245lists.cdr.apply1(lis), new Object[0]);
        }
        return fold$V;
    }

    public static Object reduceRight(Procedure f, Object obj, Object obj2) {
        frame11 frame112;
        Object lambda21recur;
        Object ridentity = obj;
        Object lis = obj2;
        new frame11();
        frame11 frame113 = frame112;
        frame113.f122f = f;
        if (isNullList(lis) != Boolean.FALSE) {
            lambda21recur = ridentity;
        } else {
            lambda21recur = frame113.lambda21recur(C1245lists.car.apply1(lis), C1245lists.cdr.apply1(lis));
        }
        return lambda21recur;
    }

    /* compiled from: srfi1.scm */
    public class frame11 extends ModuleBody {

        /* renamed from: f */
        Procedure f122f;

        public frame11() {
        }

        public Object lambda21recur(Object obj, Object obj2) {
            Object obj3;
            Object head = obj;
            Object lis = obj2;
            if (C1245lists.isPair(lis)) {
                obj3 = this.f122f.apply2(head, lambda21recur(C1245lists.car.apply1(lis), C1245lists.cdr.apply1(lis)));
            } else {
                obj3 = head;
            }
            return obj3;
        }
    }

    public static Object appendMap$V(Object obj, Object obj2, Object[] argsArray) {
        Object obj3;
        Object f;
        Throwable th;
        Object f2 = obj;
        Object lis1 = obj2;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        LList lists = makeList;
        if (C1245lists.isPair(lists)) {
            f = Scheme.apply.apply2(append.append, Scheme.apply.apply4(Scheme.map, f2, lis1, lists));
        } else {
            Apply apply = Scheme.apply;
            append append = append.append;
            Object obj4 = lis1;
            Object obj5 = LList.Empty;
            while (true) {
                obj3 = obj5;
                Object arg0 = obj4;
                if (arg0 == LList.Empty) {
                    break;
                }
                Object obj6 = arg0;
                Object obj7 = obj6;
                try {
                    Pair arg02 = (Pair) obj6;
                    obj4 = arg02.getCdr();
                    obj5 = Pair.make(Scheme.applyToArgs.apply2(f2, arg02.getCar()), obj3);
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th2 = th;
                    new WrongType(classCastException, "arg0", -2, obj7);
                    throw th2;
                }
            }
            f = apply.apply2(append, LList.reverseInPlace(obj3));
        }
        return f;
    }

    public static Object appendMap$Ex$V(Object obj, Object obj2, Object[] argsArray) {
        Object obj3;
        Object f;
        Throwable th;
        Object f2 = obj;
        Object lis1 = obj2;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        LList lists = makeList;
        if (C1245lists.isPair(lists)) {
            f = Scheme.apply.apply2(append$Ex, Scheme.apply.apply4(Scheme.map, f2, lis1, lists));
        } else {
            Apply apply = Scheme.apply;
            ModuleMethod moduleMethod = append$Ex;
            Object obj4 = lis1;
            Object obj5 = LList.Empty;
            while (true) {
                obj3 = obj5;
                Object arg0 = obj4;
                if (arg0 == LList.Empty) {
                    break;
                }
                Object obj6 = arg0;
                Object obj7 = obj6;
                try {
                    Pair arg02 = (Pair) obj6;
                    obj4 = arg02.getCdr();
                    obj5 = Pair.make(Scheme.applyToArgs.apply2(f2, arg02.getCar()), obj3);
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th2 = th;
                    new WrongType(classCastException, "arg0", -2, obj7);
                    throw th2;
                }
            }
            f = apply.apply2(moduleMethod, LList.reverseInPlace(obj3));
        }
        return f;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v20, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v1, resolved type: gnu.lists.Pair} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object pairForEach$V(gnu.mapping.Procedure r10, java.lang.Object r11, java.lang.Object[] r12) {
        /*
            r0 = r10
            r1 = r11
            r2 = r12
            r6 = r2
            r7 = 0
            gnu.lists.LList r6 = gnu.lists.LList.makeList(r6, r7)
            r9 = r6
            r6 = r9
            r7 = r9
            r4 = r7
            r3 = r6
            r6 = r3
            boolean r6 = kawa.lib.C1245lists.isPair(r6)
            if (r6 == 0) goto L_0x0038
            r6 = r1
            r7 = r3
            gnu.lists.Pair r6 = kawa.lib.C1245lists.cons(r6, r7)
            r4 = r6
        L_0x001c:
            r6 = r4
            java.lang.Object r6 = $PcCdrs(r6)
            r5 = r6
            r6 = r5
            boolean r6 = kawa.lib.C1245lists.isPair(r6)
            if (r6 == 0) goto L_0x0034
            gnu.kawa.functions.Apply r6 = kawa.standard.Scheme.apply
            r7 = r0
            r8 = r4
            java.lang.Object r6 = r6.apply2(r7, r8)
            r6 = r5
            r4 = r6
            goto L_0x001c
        L_0x0034:
            gnu.mapping.Values r6 = gnu.mapping.Values.empty
        L_0x0036:
            r0 = r6
            return r0
        L_0x0038:
            r6 = r1
            r4 = r6
        L_0x003a:
            r6 = r4
            java.lang.Object r6 = isNullList(r6)
            java.lang.Boolean r7 = java.lang.Boolean.FALSE
            if (r6 != r7) goto L_0x0054
            gnu.expr.GenericProc r6 = kawa.lib.C1245lists.cdr
            r7 = r4
            java.lang.Object r6 = r6.apply1(r7)
            r5 = r6
            r6 = r0
            r7 = r4
            java.lang.Object r6 = r6.apply1(r7)
            r6 = r5
            r4 = r6
            goto L_0x003a
        L_0x0054:
            gnu.mapping.Values r6 = gnu.mapping.Values.empty
            goto L_0x0036
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.srfi1.pairForEach$V(gnu.mapping.Procedure, java.lang.Object, java.lang.Object[]):java.lang.Object");
    }

    public static Object map$Ex$V(Procedure f, Object obj, Object[] argsArray) {
        frame12 frame122;
        Throwable th;
        Object lis1 = obj;
        new frame12();
        frame12 frame123 = frame122;
        frame123.f123f = f;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        LList lists = makeList;
        if (C1245lists.isPair(lists)) {
            Object obj2 = lis1;
            LList lList2 = lists;
            while (true) {
                Object obj3 = lList2;
                Object lis12 = obj2;
                if (isNullList(lis12) != Boolean.FALSE) {
                    break;
                }
                Object split = $PcCars$PlCdrs$SlNoTest$SlPair(obj3);
                Object heads = C1245lists.car.apply1(split);
                Object tails = C1245lists.cdr.apply1(split);
                Object obj4 = lis12;
                Object obj5 = obj4;
                try {
                    C1245lists.setCar$Ex((Pair) obj4, Scheme.apply.apply3(frame123.f123f, C1245lists.car.apply1(lis12), heads));
                    obj2 = C1245lists.cdr.apply1(lis12);
                    lList2 = tails;
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th2 = th;
                    new WrongType(classCastException, "set-car!", 1, obj5);
                    throw th2;
                }
            }
        } else {
            Object pairForEach$V = pairForEach$V(frame123.lambda$Fn11, lis1, new Object[0]);
        }
        return lis1;
    }

    /* compiled from: srfi1.scm */
    public class frame12 extends ModuleBody {

        /* renamed from: f */
        Procedure f123f;
        final ModuleMethod lambda$Fn11;

        public frame12() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 11, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:961");
            this.lambda$Fn11 = moduleMethod2;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector != 11) {
                return super.apply1(moduleMethod2, obj2);
            }
            lambda22(obj2);
            return Values.empty;
        }

        /* access modifiers changed from: package-private */
        public void lambda22(Object obj) {
            Throwable th;
            Object pair = obj;
            Object obj2 = pair;
            Object obj3 = obj2;
            try {
                C1245lists.setCar$Ex((Pair) obj2, this.f123f.apply1(C1245lists.car.apply1(pair)));
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "set-car!", 1, obj3);
                throw th2;
            }
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

    public static Object filterMap$V(Procedure f, Object obj, Object[] argsArray) {
        frame13 frame132;
        LList lList;
        Throwable th;
        Object reverse$Ex;
        Object lis1 = obj;
        new frame13();
        frame13 frame133 = frame132;
        frame133.f124f = f;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList2 = makeList;
        LList lists = makeList;
        if (C1245lists.isPair(lists)) {
            reverse$Ex = frame133.lambda23recur(C1245lists.cons(lis1, lists), LList.Empty);
        } else {
            Object obj2 = lis1;
            LList lList3 = LList.Empty;
            while (true) {
                lList = lList3;
                Object lis = obj2;
                if (isNullList(lis) != Boolean.FALSE) {
                    break;
                }
                Object apply1 = frame133.f124f.apply1(C1245lists.car.apply1(lis));
                Object tail = C1245lists.cdr.apply1(lis);
                Object head = apply1;
                if (head != Boolean.FALSE) {
                    obj2 = tail;
                    lList3 = C1245lists.cons(head, lList);
                } else {
                    obj2 = tail;
                    lList3 = lList;
                }
            }
            LList lList4 = lList;
            LList lList5 = lList4;
            try {
                reverse$Ex = C1245lists.reverse$Ex(lList4);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "reverse!", 1, (Object) lList5);
                throw th2;
            }
        }
        return reverse$Ex;
    }

    /* compiled from: srfi1.scm */
    public class frame13 extends ModuleBody {

        /* renamed from: f */
        Procedure f124f;

        public frame13() {
        }

        public Object lambda23recur(Object lists, Object res) {
            frame14 frame14;
            new frame14();
            frame14 frame142 = frame14;
            frame142.staticLink = this;
            frame14 frame143 = frame142;
            frame143.lists = lists;
            frame143.res = res;
            return call_with_values.callWithValues(frame143.lambda$Fn12, frame143.lambda$Fn13);
        }
    }

    /* compiled from: srfi1.scm */
    public class frame14 extends ModuleBody {
        final ModuleMethod lambda$Fn12;
        final ModuleMethod lambda$Fn13;
        Object lists;
        Object res;
        frame13 staticLink;

        public frame14() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 12, (Object) null, 0);
            this.lambda$Fn12 = moduleMethod;
            new ModuleMethod(this, 13, (Object) null, 8194);
            ModuleMethod moduleMethod3 = moduleMethod2;
            moduleMethod3.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:969");
            this.lambda$Fn13 = moduleMethod3;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 12 ? lambda24() : super.apply0(moduleMethod2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 12) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            if (moduleMethod2.selector == 13) {
                return lambda25(obj3, obj4);
            }
            return super.apply2(moduleMethod2, obj3, obj4);
        }

        /* access modifiers changed from: package-private */
        public Object lambda24() {
            return srfi1.$PcCars$PlCdrs(this.lists);
        }

        /* access modifiers changed from: package-private */
        public Object lambda25(Object obj, Object obj2) {
            Object lambda23recur;
            Throwable th;
            Object cars = obj;
            Object cdrs = obj2;
            if (srfi1.isNotPair(cars)) {
                Object obj3 = this.res;
                Object obj4 = obj3;
                try {
                    lambda23recur = C1245lists.reverse$Ex((LList) obj3);
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th2 = th;
                    new WrongType(classCastException, "reverse!", 1, obj4);
                    throw th2;
                }
            } else {
                Object head = Scheme.apply.apply2(this.staticLink.f124f, cars);
                if (head != Boolean.FALSE) {
                    lambda23recur = this.staticLink.lambda23recur(cdrs, C1245lists.cons(head, this.res));
                } else {
                    lambda23recur = this.staticLink.lambda23recur(cdrs, this.res);
                }
            }
            return lambda23recur;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 13) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }
    }

    public static Object filter(Procedure procedure, Object lis) {
        Throwable th;
        Procedure pred = procedure;
        Object obj = lis;
        LList lList = LList.Empty;
        while (true) {
            LList lList2 = lList;
            Object lis2 = obj;
            if (isNullList(lis2) != Boolean.FALSE) {
                LList lList3 = lList2;
                LList lList4 = lList3;
                try {
                    return C1245lists.reverse$Ex(lList3);
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th2 = th;
                    new WrongType(classCastException, "reverse!", 1, (Object) lList4);
                    throw th2;
                }
            } else {
                Object apply1 = C1245lists.car.apply1(lis2);
                Object tail = C1245lists.cdr.apply1(lis2);
                Object head = apply1;
                if (pred.apply1(head) != Boolean.FALSE) {
                    obj = tail;
                    lList = C1245lists.cons(head, lList2);
                } else {
                    obj = tail;
                    lList = lList2;
                }
            }
        }
    }

    public static Object filter$Ex(Procedure procedure, Object lis) {
        Object obj;
        Throwable th;
        Throwable th2;
        Procedure pred = procedure;
        Object obj2 = lis;
        while (true) {
            Object ans = obj2;
            if (isNullList(ans) != Boolean.FALSE) {
                obj = ans;
                break;
            } else if (pred.apply1(C1245lists.car.apply1(ans)) == Boolean.FALSE) {
                obj2 = C1245lists.cdr.apply1(ans);
            } else {
                Object obj3 = ans;
                Object apply1 = C1245lists.cdr.apply1(ans);
                loop1:
                while (true) {
                    Object obj4 = apply1;
                    Object prev = obj3;
                    if (!C1245lists.isPair(obj4)) {
                        break;
                    } else if (pred.apply1(C1245lists.car.apply1(obj4)) != Boolean.FALSE) {
                        obj3 = obj4;
                        apply1 = C1245lists.cdr.apply1(obj4);
                    } else {
                        Object prev2 = prev;
                        Object apply12 = C1245lists.cdr.apply1(obj4);
                        while (true) {
                            Object lis2 = apply12;
                            if (!C1245lists.isPair(lis2)) {
                                Object obj5 = prev2;
                                Object obj6 = obj5;
                                try {
                                    C1245lists.setCdr$Ex((Pair) obj5, lis2);
                                    break loop1;
                                } catch (ClassCastException e) {
                                    ClassCastException classCastException = e;
                                    Throwable th3 = th;
                                    new WrongType(classCastException, "set-cdr!", 1, obj6);
                                    throw th3;
                                }
                            } else if (pred.apply1(C1245lists.car.apply1(lis2)) != Boolean.FALSE) {
                                Object obj7 = prev2;
                                Object obj8 = obj7;
                                try {
                                    C1245lists.setCdr$Ex((Pair) obj7, lis2);
                                    obj3 = lis2;
                                    apply1 = C1245lists.cdr.apply1(lis2);
                                    break;
                                } catch (ClassCastException e2) {
                                    ClassCastException classCastException2 = e2;
                                    Throwable th4 = th2;
                                    new WrongType(classCastException2, "set-cdr!", 1, obj8);
                                    throw th4;
                                }
                            } else {
                                apply12 = C1245lists.cdr.apply1(lis2);
                            }
                        }
                    }
                }
                obj = ans;
            }
        }
        return obj;
    }

    public static Object partition(Procedure procedure, Object lis) {
        Throwable th;
        Throwable th2;
        Procedure pred = procedure;
        Object obj = lis;
        LList lList = LList.Empty;
        LList lList2 = LList.Empty;
        while (true) {
            LList lList3 = lList2;
            LList lList4 = lList;
            Object lis2 = obj;
            if (isNullList(lis2) != Boolean.FALSE) {
                Object[] objArr = new Object[2];
                Object[] objArr2 = objArr;
                Object[] objArr3 = objArr;
                LList lList5 = lList4;
                LList lList6 = lList5;
                try {
                    objArr3[0] = C1245lists.reverse$Ex(lList5);
                    Object[] objArr4 = objArr2;
                    Object[] objArr5 = objArr4;
                    Object[] objArr6 = objArr4;
                    LList lList7 = lList3;
                    LList lList8 = lList7;
                    try {
                        objArr6[1] = C1245lists.reverse$Ex(lList7);
                        return misc.values(objArr5);
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th3 = th2;
                        new WrongType(classCastException, "reverse!", 1, (Object) lList8);
                        throw th3;
                    }
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th4 = th;
                    new WrongType(classCastException2, "reverse!", 1, (Object) lList6);
                    throw th4;
                }
            } else {
                Object apply1 = C1245lists.car.apply1(lis2);
                Object tail = C1245lists.cdr.apply1(lis2);
                Object head = apply1;
                if (pred.apply1(head) != Boolean.FALSE) {
                    obj = tail;
                    lList = C1245lists.cons(head, lList4);
                    lList2 = lList3;
                } else {
                    obj = tail;
                    lList = lList4;
                    lList2 = C1245lists.cons(head, lList3);
                }
            }
        }
    }

    public static Object partition$Ex(Procedure procedure, Object lis) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Procedure pred = procedure;
        Pair cons = C1245lists.cons(Lit2, LList.Empty);
        Pair out$Mnhead = C1245lists.cons(Lit2, LList.Empty);
        Pair in$Mnhead = cons;
        Pair pair = in$Mnhead;
        Pair pair2 = out$Mnhead;
        Object obj = lis;
        while (true) {
            Object obj2 = obj;
            Object out = pair2;
            Pair pair3 = pair;
            if (isNotPair(obj2)) {
                Pair pair4 = pair3;
                Pair pair5 = pair4;
                try {
                    C1245lists.setCdr$Ex(pair4, LList.Empty);
                    Object obj3 = out;
                    Object obj4 = obj3;
                    try {
                        C1245lists.setCdr$Ex((Pair) obj3, LList.Empty);
                        Object[] objArr = new Object[2];
                        objArr[0] = C1245lists.cdr.apply1(in$Mnhead);
                        Object[] objArr2 = objArr;
                        objArr2[1] = C1245lists.cdr.apply1(out$Mnhead);
                        return misc.values(objArr2);
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th5 = th2;
                        new WrongType(classCastException, "set-cdr!", 1, obj4);
                        throw th5;
                    }
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th6 = th;
                    new WrongType(classCastException2, "set-cdr!", 1, (Object) pair5);
                    throw th6;
                }
            } else if (pred.apply1(C1245lists.car.apply1(obj2)) != Boolean.FALSE) {
                Pair pair6 = pair3;
                Pair pair7 = pair6;
                try {
                    C1245lists.setCdr$Ex(pair6, obj2);
                    pair = obj2;
                    pair2 = out;
                    obj = C1245lists.cdr.apply1(obj2);
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th7 = th4;
                    new WrongType(classCastException3, "set-cdr!", 1, (Object) pair7);
                    throw th7;
                }
            } else {
                Object obj5 = out;
                Object obj6 = obj5;
                try {
                    C1245lists.setCdr$Ex((Pair) obj5, obj2);
                    pair = pair3;
                    pair2 = obj2;
                    obj = C1245lists.cdr.apply1(obj2);
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th8 = th3;
                    new WrongType(classCastException4, "set-cdr!", 1, obj6);
                    throw th8;
                }
            }
        }
    }

    /* compiled from: srfi1.scm */
    public class frame15 extends ModuleBody {
        final ModuleMethod lambda$Fn14;
        Object pred;

        public frame15() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 14, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1199");
            this.lambda$Fn14 = moduleMethod2;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            Boolean bool;
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector != 14) {
                return super.apply1(moduleMethod2, obj2);
            }
            if (lambda26(obj2)) {
                bool = Boolean.TRUE;
            } else {
                bool = Boolean.FALSE;
            }
            return bool;
        }

        /* access modifiers changed from: package-private */
        public boolean lambda26(Object x) {
            return ((Scheme.applyToArgs.apply2(this.pred, x) != Boolean.FALSE ? 1 : 0) + 1) & true;
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 14) {
                return super.match1(moduleMethod2, obj2, callContext2);
            }
            callContext2.value1 = obj2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 1;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    public class frame16 extends ModuleBody {
        final ModuleMethod lambda$Fn15;
        Object pred;

        public frame16() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 15, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1200");
            this.lambda$Fn15 = moduleMethod2;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            Boolean bool;
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector != 15) {
                return super.apply1(moduleMethod2, obj2);
            }
            if (lambda27(obj2)) {
                bool = Boolean.TRUE;
            } else {
                bool = Boolean.FALSE;
            }
            return bool;
        }

        /* access modifiers changed from: package-private */
        public boolean lambda27(Object x) {
            return ((Scheme.applyToArgs.apply2(this.pred, x) != Boolean.FALSE ? 1 : 0) + 1) & true;
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 15) {
                return super.match1(moduleMethod2, obj2, callContext2);
            }
            callContext2.value1 = obj2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 1;
            return 0;
        }
    }

    public static Object remove(Object pred, Object l) {
        frame15 frame152;
        new frame15();
        frame15 frame153 = frame152;
        frame153.pred = pred;
        return filter(frame153.lambda$Fn14, l);
    }

    public static Object remove$Ex(Object pred, Object l) {
        frame16 frame162;
        new frame16();
        frame16 frame163 = frame162;
        frame163.pred = pred;
        return filter$Ex(frame163.lambda$Fn15, l);
    }

    /* compiled from: srfi1.scm */
    public class frame17 extends ModuleBody {
        final ModuleMethod lambda$Fn16;
        Object maybe$Mn$Eq;

        /* renamed from: x */
        Object f125x;

        public frame17() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 16, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1222");
            this.lambda$Fn16 = moduleMethod2;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            Boolean bool;
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector != 16) {
                return super.apply1(moduleMethod2, obj2);
            }
            if (lambda28(obj2)) {
                bool = Boolean.TRUE;
            } else {
                bool = Boolean.FALSE;
            }
            return bool;
        }

        /* access modifiers changed from: package-private */
        public boolean lambda28(Object y) {
            return ((Scheme.applyToArgs.apply3(this.maybe$Mn$Eq, this.f125x, y) != Boolean.FALSE ? 1 : 0) + 1) & true;
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 16) {
                return super.match1(moduleMethod2, obj2, callContext2);
            }
            callContext2.value1 = obj2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 1;
            return 0;
        }
    }

    public static Object delete(Object x, Object lis, Object maybe$Mn$Eq) {
        frame17 frame172;
        new frame17();
        frame17 frame173 = frame172;
        frame173.f125x = x;
        frame173.maybe$Mn$Eq = maybe$Mn$Eq;
        return filter(frame173.lambda$Fn16, lis);
    }

    /* compiled from: srfi1.scm */
    public class frame18 extends ModuleBody {
        final ModuleMethod lambda$Fn17;
        Object maybe$Mn$Eq;

        /* renamed from: x */
        Object f126x;

        public frame18() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 17, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1225");
            this.lambda$Fn17 = moduleMethod2;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            Boolean bool;
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector != 17) {
                return super.apply1(moduleMethod2, obj2);
            }
            if (lambda29(obj2)) {
                bool = Boolean.TRUE;
            } else {
                bool = Boolean.FALSE;
            }
            return bool;
        }

        /* access modifiers changed from: package-private */
        public boolean lambda29(Object y) {
            return ((Scheme.applyToArgs.apply3(this.maybe$Mn$Eq, this.f126x, y) != Boolean.FALSE ? 1 : 0) + 1) & true;
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 17) {
                return super.match1(moduleMethod2, obj2, callContext2);
            }
            callContext2.value1 = obj2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 1;
            return 0;
        }
    }

    public static Object delete$Ex(Object x, Object lis, Object maybe$Mn$Eq) {
        frame18 frame182;
        new frame18();
        frame18 frame183 = frame182;
        frame183.f126x = x;
        frame183.maybe$Mn$Eq = maybe$Mn$Eq;
        return filter$Ex(frame183.lambda$Fn17, lis);
    }

    public static Object deleteDuplicates(Object lis, Procedure maybe$Mn$Eq) {
        frame19 frame192;
        new frame19();
        frame19 frame193 = frame192;
        frame193.maybe$Mn$Eq = maybe$Mn$Eq;
        return frame193.lambda30recur(lis);
    }

    /* compiled from: srfi1.scm */
    public class frame19 extends ModuleBody {
        Procedure maybe$Mn$Eq;

        public frame19() {
        }

        public Object lambda30recur(Object obj) {
            Object cons;
            Object lis = obj;
            if (srfi1.isNullList(lis) != Boolean.FALSE) {
                cons = lis;
            } else {
                Object x = C1245lists.car.apply1(lis);
                Object tail = C1245lists.cdr.apply1(lis);
                Object new$Mntail = lambda30recur(srfi1.delete(x, tail, this.maybe$Mn$Eq));
                cons = tail == new$Mntail ? lis : C1245lists.cons(x, new$Mntail);
            }
            return cons;
        }
    }

    public static Object deleteDuplicates$Ex(Object lis, Procedure maybe$Mn$Eq) {
        frame20 frame202;
        new frame20();
        frame20 frame203 = frame202;
        frame203.maybe$Mn$Eq = maybe$Mn$Eq;
        return frame203.lambda31recur(lis);
    }

    /* compiled from: srfi1.scm */
    public class frame20 extends ModuleBody {
        Procedure maybe$Mn$Eq;

        public frame20() {
        }

        public Object lambda31recur(Object obj) {
            Object cons;
            Object lis = obj;
            if (srfi1.isNullList(lis) != Boolean.FALSE) {
                cons = lis;
            } else {
                Object x = C1245lists.car.apply1(lis);
                Object tail = C1245lists.cdr.apply1(lis);
                Object new$Mntail = lambda31recur(srfi1.delete$Ex(x, tail, this.maybe$Mn$Eq));
                cons = tail == new$Mntail ? lis : C1245lists.cons(x, new$Mntail);
            }
            return cons;
        }
    }

    public static Pair alistCons(Object key, Object datum, Object alist) {
        return C1245lists.cons(C1245lists.cons(key, datum), alist);
    }

    public static LList alistCopy(Object alist) {
        Throwable th;
        Object obj = alist;
        Object obj2 = LList.Empty;
        while (true) {
            Object obj3 = obj2;
            Object arg0 = obj;
            if (arg0 == LList.Empty) {
                return LList.reverseInPlace(obj3);
            }
            Object obj4 = arg0;
            Object obj5 = obj4;
            try {
                Pair arg02 = (Pair) obj4;
                obj = arg02.getCdr();
                Object elt = arg02.getCar();
                obj2 = Pair.make(C1245lists.cons(C1245lists.car.apply1(elt), C1245lists.cdr.apply1(elt)), obj3);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "arg0", -2, obj5);
                throw th2;
            }
        }
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        switch (moduleMethod2.selector) {
            case 82:
                try {
                    return listCopy((LList) obj2);
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th4 = th3;
                    new WrongType(classCastException, "list-copy", 1, obj2);
                    throw th4;
                }
            case 83:
                try {
                    return iota(LangObjType.coerceIntNum(obj2));
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th5 = th2;
                    new WrongType(classCastException2, "iota", 1, obj2);
                    throw th5;
                }
            case 87:
                return isProperList(obj2);
            case 88:
                return isDottedList(obj2);
            case 89:
                return isCircularList(obj2);
            case 90:
                return isNotPair(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 91:
                return isNullList(obj2);
            case 93:
                return length$Pl(obj2);
            case 95:
                return fifth(obj2);
            case 96:
                return sixth(obj2);
            case 97:
                return seventh(obj2);
            case 98:
                return eighth(obj2);
            case 99:
                return ninth(obj2);
            case 100:
                return tenth(obj2);
            case 101:
                return car$PlCdr(obj2);
            case 110:
                return last(obj2);
            case 111:
                try {
                    return lastPair((Pair) obj2);
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th6 = th;
                    new WrongType(classCastException3, "last-pair", 1, obj2);
                    throw th6;
                }
            case 112:
                return unzip1(obj2);
            case 113:
                return unzip2(obj2);
            case 114:
                return unzip3(obj2);
            case 115:
                return unzip4(obj2);
            case 116:
                return unzip5(obj2);
            case 120:
                return concatenate(obj2);
            case 121:
                return concatenate$Ex(obj2);
            case 147:
                return deleteDuplicates(obj2);
            case 149:
                return deleteDuplicates$Ex(obj2);
            case 152:
                return alistCopy(obj2);
            default:
                return super.apply1(moduleMethod2, obj2);
        }
    }

    /* compiled from: srfi1.scm */
    public class frame21 extends ModuleBody {
        Object key;
        final ModuleMethod lambda$Fn18;
        Object maybe$Mn$Eq;

        public frame21() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 18, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1280");
            this.lambda$Fn18 = moduleMethod2;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            Boolean bool;
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector != 18) {
                return super.apply1(moduleMethod2, obj2);
            }
            if (lambda32(obj2)) {
                bool = Boolean.TRUE;
            } else {
                bool = Boolean.FALSE;
            }
            return bool;
        }

        /* access modifiers changed from: package-private */
        public boolean lambda32(Object elt) {
            return ((Scheme.applyToArgs.apply3(this.maybe$Mn$Eq, this.key, C1245lists.car.apply1(elt)) != Boolean.FALSE ? 1 : 0) + 1) & true;
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 18) {
                return super.match1(moduleMethod2, obj2, callContext2);
            }
            callContext2.value1 = obj2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 1;
            return 0;
        }
    }

    public static Object alistDelete(Object key, Object alist, Object maybe$Mn$Eq) {
        frame21 frame212;
        new frame21();
        frame21 frame213 = frame212;
        frame213.key = key;
        frame213.maybe$Mn$Eq = maybe$Mn$Eq;
        return filter(frame213.lambda$Fn18, alist);
    }

    /* compiled from: srfi1.scm */
    public class frame22 extends ModuleBody {
        Object key;
        final ModuleMethod lambda$Fn19;
        Object maybe$Mn$Eq;

        public frame22() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 19, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1283");
            this.lambda$Fn19 = moduleMethod2;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            Boolean bool;
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector != 19) {
                return super.apply1(moduleMethod2, obj2);
            }
            if (lambda33(obj2)) {
                bool = Boolean.TRUE;
            } else {
                bool = Boolean.FALSE;
            }
            return bool;
        }

        /* access modifiers changed from: package-private */
        public boolean lambda33(Object elt) {
            return ((Scheme.applyToArgs.apply3(this.maybe$Mn$Eq, this.key, C1245lists.car.apply1(elt)) != Boolean.FALSE ? 1 : 0) + 1) & true;
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 19) {
                return super.match1(moduleMethod2, obj2, callContext2);
            }
            callContext2.value1 = obj2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 1;
            return 0;
        }
    }

    public static Object alistDelete$Ex(Object key, Object alist, Object maybe$Mn$Eq) {
        frame22 frame222;
        new frame22();
        frame22 frame223 = frame222;
        frame223.key = key;
        frame223.maybe$Mn$Eq = maybe$Mn$Eq;
        return filter$Ex(frame223.lambda$Fn19, alist);
    }

    public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj4 = obj;
        Object obj5 = obj2;
        Object obj6 = obj3;
        switch (moduleMethod2.selector) {
            case 83:
                try {
                    try {
                        try {
                            return iota(LangObjType.coerceIntNum(obj4), LangObjType.coerceNumeric(obj5), LangObjType.coerceNumeric(obj6));
                        } catch (ClassCastException e) {
                            ClassCastException classCastException = e;
                            Throwable th6 = th5;
                            new WrongType(classCastException, "iota", 3, obj6);
                            throw th6;
                        }
                    } catch (ClassCastException e2) {
                        ClassCastException classCastException2 = e2;
                        Throwable th7 = th4;
                        new WrongType(classCastException2, "iota", 2, obj5);
                        throw th7;
                    }
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th8 = th3;
                    new WrongType(classCastException3, "iota", 1, obj4);
                    throw th8;
                }
            case 130:
                try {
                    return reduce((Procedure) obj4, obj5, obj6);
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th9 = th2;
                    new WrongType(classCastException4, "reduce", 1, obj4);
                    throw th9;
                }
            case 131:
                try {
                    return reduceRight((Procedure) obj4, obj5, obj6);
                } catch (ClassCastException e5) {
                    ClassCastException classCastException5 = e5;
                    Throwable th10 = th;
                    new WrongType(classCastException5, "reduce-right", 1, obj4);
                    throw th10;
                }
            case 143:
                return delete(obj4, obj5, obj6);
            case 145:
                return delete$Ex(obj4, obj5, obj6);
            case 151:
                return alistCons(obj4, obj5, obj6);
            case 153:
                return alistDelete(obj4, obj5, obj6);
            case 155:
                return alistDelete$Ex(obj4, obj5, obj6);
            default:
                return super.apply3(moduleMethod2, obj4, obj5, obj6);
        }
    }

    public static Object find(Object pred, Object list) {
        Throwable th;
        Object obj = pred;
        Object obj2 = obj;
        try {
            Object temp = findTail((Procedure) obj, list);
            return temp != Boolean.FALSE ? C1245lists.car.apply1(temp) : Boolean.FALSE;
        } catch (ClassCastException e) {
            ClassCastException classCastException = e;
            Throwable th2 = th;
            new WrongType(classCastException, "find-tail", 0, obj2);
            throw th2;
        }
    }

    public static Object findTail(Procedure procedure, Object list) {
        Throwable th;
        Object obj;
        Procedure pred = procedure;
        Object obj2 = list;
        while (true) {
            Object list2 = obj2;
            Object isNullList = isNullList(list2);
            Object obj3 = isNullList;
            try {
                boolean x = ((isNullList != Boolean.FALSE ? 1 : 0) + 1) & true;
                if (!x) {
                    obj = x ? Boolean.TRUE : Boolean.FALSE;
                } else if (pred.apply1(C1245lists.car.apply1(list2)) != Boolean.FALSE) {
                    obj = list2;
                    break;
                } else {
                    obj2 = C1245lists.cdr.apply1(list2);
                }
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "x", -2, obj3);
                throw th2;
            }
        }
        return obj;
    }

    public static Object takeWhile(Procedure pred, Object lis) {
        frame23 frame232;
        new frame23();
        frame23 frame233 = frame232;
        frame233.pred = pred;
        return frame233.lambda34recur(lis);
    }

    /* compiled from: srfi1.scm */
    public class frame23 extends ModuleBody {
        Procedure pred;

        public frame23() {
        }

        public Object lambda34recur(Object obj) {
            Object cons;
            Object lis = obj;
            if (srfi1.isNullList(lis) != Boolean.FALSE) {
                cons = LList.Empty;
            } else {
                Object x = C1245lists.car.apply1(lis);
                cons = this.pred.apply1(x) != Boolean.FALSE ? C1245lists.cons(x, lambda34recur(C1245lists.cdr.apply1(lis))) : LList.Empty;
            }
            return cons;
        }
    }

    public static Object dropWhile(Procedure procedure, Object lis) {
        Object obj;
        Procedure pred = procedure;
        Object obj2 = lis;
        while (true) {
            Object lis2 = obj2;
            if (isNullList(lis2) == Boolean.FALSE) {
                if (pred.apply1(C1245lists.car.apply1(lis2)) == Boolean.FALSE) {
                    obj = lis2;
                    break;
                }
                obj2 = C1245lists.cdr.apply1(lis2);
            } else {
                obj = LList.Empty;
                break;
            }
        }
        return obj;
    }

    public static Object takeWhile$Ex(Procedure procedure, Object obj) {
        Object obj2;
        Throwable th;
        Procedure pred = procedure;
        Object lis = obj;
        Object x = isNullList(lis);
        if (x == Boolean.FALSE ? pred.apply1(C1245lists.car.apply1(lis)) != Boolean.FALSE : x == Boolean.FALSE) {
            Object obj3 = lis;
            Object apply1 = C1245lists.cdr.apply1(lis);
            while (true) {
                Object obj4 = apply1;
                Object prev = obj3;
                if (!C1245lists.isPair(obj4)) {
                    break;
                }
                if (pred.apply1(C1245lists.car.apply1(obj4)) != Boolean.FALSE) {
                    obj3 = obj4;
                    apply1 = C1245lists.cdr.apply1(obj4);
                } else {
                    Object obj5 = prev;
                    Object obj6 = obj5;
                    try {
                        C1245lists.setCdr$Ex((Pair) obj5, LList.Empty);
                        break;
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th2 = th;
                        new WrongType(classCastException, "set-cdr!", 1, obj6);
                        throw th2;
                    }
                }
            }
            obj2 = lis;
        } else {
            obj2 = LList.Empty;
        }
        return obj2;
    }

    public static Object span(Procedure procedure, Object lis) {
        Throwable th;
        Object values;
        Throwable th2;
        Procedure pred = procedure;
        Object obj = lis;
        LList lList = LList.Empty;
        while (true) {
            LList lList2 = lList;
            Object lis2 = obj;
            if (isNullList(lis2) != Boolean.FALSE) {
                Object[] objArr = new Object[2];
                Object[] objArr2 = objArr;
                Object[] objArr3 = objArr;
                LList lList3 = lList2;
                LList lList4 = lList3;
                try {
                    objArr3[0] = C1245lists.reverse$Ex(lList3);
                    Object[] objArr4 = objArr2;
                    objArr4[1] = lis2;
                    values = misc.values(objArr4);
                    break;
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th3 = th;
                    new WrongType(classCastException, "reverse!", 1, (Object) lList4);
                    throw th3;
                }
            } else {
                Object head = C1245lists.car.apply1(lis2);
                if (pred.apply1(head) != Boolean.FALSE) {
                    obj = C1245lists.cdr.apply1(lis2);
                    lList = C1245lists.cons(head, lList2);
                } else {
                    Object[] objArr5 = new Object[2];
                    Object[] objArr6 = objArr5;
                    Object[] objArr7 = objArr5;
                    LList lList5 = lList2;
                    LList lList6 = lList5;
                    try {
                        objArr7[0] = C1245lists.reverse$Ex(lList5);
                        Object[] objArr8 = objArr6;
                        objArr8[1] = lis2;
                        values = misc.values(objArr8);
                        break;
                    } catch (ClassCastException e2) {
                        ClassCastException classCastException2 = e2;
                        Throwable th4 = th2;
                        new WrongType(classCastException2, "reverse!", 1, (Object) lList6);
                        throw th4;
                    }
                }
            }
        }
        return values;
    }

    public static Object span$Ex(Procedure procedure, Object obj) {
        Object suffix;
        Throwable th;
        Object values;
        Procedure pred = procedure;
        Object lis = obj;
        Object x = isNullList(lis);
        if (x == Boolean.FALSE ? pred.apply1(C1245lists.car.apply1(lis)) != Boolean.FALSE : x == Boolean.FALSE) {
            Object obj2 = lis;
            Object apply1 = C1245lists.cdr.apply1(lis);
            while (true) {
                Object obj3 = apply1;
                Object prev = obj2;
                if (isNullList(obj3) != Boolean.FALSE) {
                    suffix = obj3;
                    break;
                }
                if (pred.apply1(C1245lists.car.apply1(obj3)) != Boolean.FALSE) {
                    obj2 = obj3;
                    apply1 = C1245lists.cdr.apply1(obj3);
                } else {
                    Object obj4 = prev;
                    Object obj5 = obj4;
                    try {
                        C1245lists.setCdr$Ex((Pair) obj4, LList.Empty);
                        suffix = obj3;
                        break;
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th2 = th;
                        new WrongType(classCastException, "set-cdr!", 1, obj5);
                        throw th2;
                    }
                }
            }
            Object[] objArr = new Object[2];
            objArr[0] = lis;
            Object[] objArr2 = objArr;
            objArr2[1] = suffix;
            values = misc.values(objArr2);
        } else {
            Object[] objArr3 = new Object[2];
            objArr3[0] = LList.Empty;
            Object[] objArr4 = objArr3;
            objArr4[1] = lis;
            values = misc.values(objArr4);
        }
        return values;
    }

    /* compiled from: srfi1.scm */
    public class frame24 extends ModuleBody {
        final ModuleMethod lambda$Fn20;
        Object pred;

        public frame24() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 20, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1343");
            this.lambda$Fn20 = moduleMethod2;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            Boolean bool;
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector != 20) {
                return super.apply1(moduleMethod2, obj2);
            }
            if (lambda35(obj2)) {
                bool = Boolean.TRUE;
            } else {
                bool = Boolean.FALSE;
            }
            return bool;
        }

        /* access modifiers changed from: package-private */
        public boolean lambda35(Object x) {
            return ((Scheme.applyToArgs.apply2(this.pred, x) != Boolean.FALSE ? 1 : 0) + 1) & true;
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 20) {
                return super.match1(moduleMethod2, obj2, callContext2);
            }
            callContext2.value1 = obj2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 1;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    public class frame25 extends ModuleBody {
        final ModuleMethod lambda$Fn21;
        Object pred;

        public frame25() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 21, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1344");
            this.lambda$Fn21 = moduleMethod2;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            Boolean bool;
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector != 21) {
                return super.apply1(moduleMethod2, obj2);
            }
            if (lambda36(obj2)) {
                bool = Boolean.TRUE;
            } else {
                bool = Boolean.FALSE;
            }
            return bool;
        }

        /* access modifiers changed from: package-private */
        public boolean lambda36(Object x) {
            return ((Scheme.applyToArgs.apply2(this.pred, x) != Boolean.FALSE ? 1 : 0) + 1) & true;
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 21) {
                return super.match1(moduleMethod2, obj2, callContext2);
            }
            callContext2.value1 = obj2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 1;
            return 0;
        }
    }

    /* renamed from: break  reason: not valid java name */
    public static Object m75break(Object pred, Object lis) {
        frame24 frame242;
        new frame24();
        frame24 frame243 = frame242;
        frame243.pred = pred;
        return span(frame243.lambda$Fn20, lis);
    }

    public static Object break$Ex(Object pred, Object lis) {
        frame25 frame252;
        new frame25();
        frame25 frame253 = frame252;
        frame253.pred = pred;
        return span$Ex(frame253.lambda$Fn21, lis);
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
        Throwable th18;
        Throwable th19;
        Throwable th20;
        Throwable th21;
        Throwable th22;
        Throwable th23;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        switch (moduleMethod2.selector) {
            case 78:
                return xcons(obj3, obj4);
            case 80:
                try {
                    return listTabulate(obj3, (Procedure) obj4);
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th24 = th23;
                    new WrongType(classCastException, "list-tabulate", 2, obj4);
                    throw th24;
                }
            case 83:
                try {
                    try {
                        return iota(LangObjType.coerceIntNum(obj3), LangObjType.coerceNumeric(obj4));
                    } catch (ClassCastException e2) {
                        ClassCastException classCastException2 = e2;
                        Throwable th25 = th22;
                        new WrongType(classCastException2, "iota", 2, obj4);
                        throw th25;
                    }
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th26 = th21;
                    new WrongType(classCastException3, "iota", 1, obj3);
                    throw th26;
                }
            case 102:
                try {
                    return take(obj3, LangObjType.coerceIntNum(obj4));
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th27 = th20;
                    new WrongType(classCastException4, "take", 2, obj4);
                    throw th27;
                }
            case 103:
                try {
                    return drop(obj3, LangObjType.coerceIntNum(obj4));
                } catch (ClassCastException e5) {
                    ClassCastException classCastException5 = e5;
                    Throwable th28 = th19;
                    new WrongType(classCastException5, "drop", 2, obj4);
                    throw th28;
                }
            case 104:
                try {
                    return take$Ex(obj3, LangObjType.coerceIntNum(obj4));
                } catch (ClassCastException e6) {
                    ClassCastException classCastException6 = e6;
                    Throwable th29 = th18;
                    new WrongType(classCastException6, "take!", 2, obj4);
                    throw th29;
                }
            case 105:
                try {
                    return takeRight(obj3, LangObjType.coerceIntNum(obj4));
                } catch (ClassCastException e7) {
                    ClassCastException classCastException7 = e7;
                    Throwable th30 = th17;
                    new WrongType(classCastException7, "take-right", 2, obj4);
                    throw th30;
                }
            case 106:
                try {
                    return dropRight(obj3, LangObjType.coerceIntNum(obj4));
                } catch (ClassCastException e8) {
                    ClassCastException classCastException8 = e8;
                    Throwable th31 = th16;
                    new WrongType(classCastException8, "drop-right", 2, obj4);
                    throw th31;
                }
            case 107:
                try {
                    return dropRight$Ex(obj3, LangObjType.coerceIntNum(obj4));
                } catch (ClassCastException e9) {
                    ClassCastException classCastException9 = e9;
                    Throwable th32 = th15;
                    new WrongType(classCastException9, "drop-right!", 2, obj4);
                    throw th32;
                }
            case 108:
                try {
                    return splitAt(obj3, LangObjType.coerceIntNum(obj4));
                } catch (ClassCastException e10) {
                    ClassCastException classCastException10 = e10;
                    Throwable th33 = th14;
                    new WrongType(classCastException10, "split-at", 2, obj4);
                    throw th33;
                }
            case 109:
                try {
                    return splitAt$Ex(obj3, LangObjType.coerceIntNum(obj4));
                } catch (ClassCastException e11) {
                    ClassCastException classCastException11 = e11;
                    Throwable th34 = th13;
                    new WrongType(classCastException11, "split-at!", 2, obj4);
                    throw th34;
                }
            case 118:
                return appendReverse(obj3, obj4);
            case 119:
                return appendReverse$Ex(obj3, obj4);
            case 137:
                try {
                    return filter((Procedure) obj3, obj4);
                } catch (ClassCastException e12) {
                    ClassCastException classCastException12 = e12;
                    Throwable th35 = th12;
                    new WrongType(classCastException12, "filter", 1, obj3);
                    throw th35;
                }
            case 138:
                try {
                    return filter$Ex((Procedure) obj3, obj4);
                } catch (ClassCastException e13) {
                    ClassCastException classCastException13 = e13;
                    Throwable th36 = th11;
                    new WrongType(classCastException13, "filter!", 1, obj3);
                    throw th36;
                }
            case 139:
                try {
                    return partition((Procedure) obj3, obj4);
                } catch (ClassCastException e14) {
                    ClassCastException classCastException14 = e14;
                    Throwable th37 = th10;
                    new WrongType(classCastException14, "partition", 1, obj3);
                    throw th37;
                }
            case 140:
                try {
                    return partition$Ex((Procedure) obj3, obj4);
                } catch (ClassCastException e15) {
                    ClassCastException classCastException15 = e15;
                    Throwable th38 = th9;
                    new WrongType(classCastException15, "partition!", 1, obj3);
                    throw th38;
                }
            case 141:
                return remove(obj3, obj4);
            case 142:
                return remove$Ex(obj3, obj4);
            case 143:
                return delete(obj3, obj4);
            case 145:
                return delete$Ex(obj3, obj4);
            case 147:
                try {
                    return deleteDuplicates(obj3, (Procedure) obj4);
                } catch (ClassCastException e16) {
                    ClassCastException classCastException16 = e16;
                    Throwable th39 = th8;
                    new WrongType(classCastException16, "delete-duplicates", 2, obj4);
                    throw th39;
                }
            case 149:
                try {
                    return deleteDuplicates$Ex(obj3, (Procedure) obj4);
                } catch (ClassCastException e17) {
                    ClassCastException classCastException17 = e17;
                    Throwable th40 = th7;
                    new WrongType(classCastException17, "delete-duplicates!", 2, obj4);
                    throw th40;
                }
            case 153:
                return alistDelete(obj3, obj4);
            case 155:
                return alistDelete$Ex(obj3, obj4);
            case 157:
                return find(obj3, obj4);
            case 158:
                try {
                    return findTail((Procedure) obj3, obj4);
                } catch (ClassCastException e18) {
                    ClassCastException classCastException18 = e18;
                    Throwable th41 = th6;
                    new WrongType(classCastException18, "find-tail", 1, obj3);
                    throw th41;
                }
            case 159:
                try {
                    return takeWhile((Procedure) obj3, obj4);
                } catch (ClassCastException e19) {
                    ClassCastException classCastException19 = e19;
                    Throwable th42 = th5;
                    new WrongType(classCastException19, "take-while", 1, obj3);
                    throw th42;
                }
            case ComponentConstants.TEXTBOX_PREFERRED_WIDTH:
                try {
                    return dropWhile((Procedure) obj3, obj4);
                } catch (ClassCastException e20) {
                    ClassCastException classCastException20 = e20;
                    Throwable th43 = th4;
                    new WrongType(classCastException20, "drop-while", 1, obj3);
                    throw th43;
                }
            case 161:
                try {
                    return takeWhile$Ex((Procedure) obj3, obj4);
                } catch (ClassCastException e21) {
                    ClassCastException classCastException21 = e21;
                    Throwable th44 = th3;
                    new WrongType(classCastException21, "take-while!", 1, obj3);
                    throw th44;
                }
            case 162:
                try {
                    return span((Procedure) obj3, obj4);
                } catch (ClassCastException e22) {
                    ClassCastException classCastException22 = e22;
                    Throwable th45 = th2;
                    new WrongType(classCastException22, "span", 1, obj3);
                    throw th45;
                }
            case 163:
                try {
                    return span$Ex((Procedure) obj3, obj4);
                } catch (ClassCastException e23) {
                    ClassCastException classCastException23 = e23;
                    Throwable th46 = th;
                    new WrongType(classCastException23, "span!", 1, obj3);
                    throw th46;
                }
            case 164:
                return m75break(obj3, obj4);
            case 165:
                return break$Ex(obj3, obj4);
            case 182:
                return frame61.lambda84(obj3, obj4);
            case 183:
                return frame71.lambda100(obj3, obj4);
            default:
                return super.apply2(moduleMethod2, obj3, obj4);
        }
    }

    public static Object any$V(Procedure pred, Object lis1, Object[] argsArray) {
        frame26 frame262;
        Throwable th;
        Object obj;
        new frame26();
        frame26 frame263 = frame262;
        frame263.pred = pred;
        frame263.lis1 = lis1;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame263.lists = makeList;
        if (C1245lists.isPair(frame263.lists)) {
            obj = call_with_values.callWithValues(frame263.lambda$Fn22, frame263.lambda$Fn23);
        } else {
            Object isNullList = isNullList(frame263.lis1);
            Object obj2 = isNullList;
            try {
                boolean x = ((isNullList != Boolean.FALSE ? 1 : 0) + 1) & true;
                if (x) {
                    Object apply1 = C1245lists.car.apply1(frame263.lis1);
                    Object apply12 = C1245lists.cdr.apply1(frame263.lis1);
                    while (true) {
                        Object obj3 = apply12;
                        Object head = apply1;
                        if (isNullList(obj3) != Boolean.FALSE) {
                            obj = frame263.pred.apply1(head);
                            break;
                        }
                        Object x2 = frame263.pred.apply1(head);
                        if (x2 != Boolean.FALSE) {
                            obj = x2;
                            break;
                        }
                        apply1 = C1245lists.car.apply1(obj3);
                        apply12 = C1245lists.cdr.apply1(obj3);
                    }
                } else {
                    obj = x ? Boolean.TRUE : Boolean.FALSE;
                }
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "x", -2, obj2);
                throw th2;
            }
        }
        return obj;
    }

    /* compiled from: srfi1.scm */
    public class frame26 extends ModuleBody {
        final ModuleMethod lambda$Fn22;
        final ModuleMethod lambda$Fn23;
        Object lis1;
        LList lists;
        Procedure pred;

        public frame26() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 22, (Object) null, 0);
            this.lambda$Fn22 = moduleMethod;
            new ModuleMethod(this, 23, (Object) null, 8194);
            ModuleMethod moduleMethod3 = moduleMethod2;
            moduleMethod3.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1350");
            this.lambda$Fn23 = moduleMethod3;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 22 ? lambda37() : super.apply0(moduleMethod2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 22) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            if (moduleMethod2.selector == 23) {
                return lambda38(obj3, obj4);
            }
            return super.apply2(moduleMethod2, obj3, obj4);
        }

        /* access modifiers changed from: package-private */
        public Object lambda37() {
            return srfi1.$PcCars$PlCdrs(C1245lists.cons(this.lis1, this.lists));
        }

        /* access modifiers changed from: package-private */
        public Object lambda38(Object obj, Object obj2) {
            Object obj3;
            Object heads = obj;
            Object tails = obj2;
            boolean x = C1245lists.isPair(heads);
            if (x) {
                Object obj4 = heads;
                Object obj5 = tails;
                while (true) {
                    Object heads2 = obj4;
                    Object split = srfi1.$PcCars$PlCdrs$SlPair(obj5);
                    Object next$Mnheads = C1245lists.car.apply1(split);
                    Object next$Mntails = C1245lists.cdr.apply1(split);
                    if (!C1245lists.isPair(next$Mnheads)) {
                        obj3 = Scheme.apply.apply2(this.pred, heads2);
                        break;
                    }
                    Object x2 = Scheme.apply.apply2(this.pred, heads2);
                    if (x2 != Boolean.FALSE) {
                        obj3 = x2;
                        break;
                    }
                    obj4 = next$Mnheads;
                    obj5 = next$Mntails;
                }
            } else {
                obj3 = x ? Boolean.TRUE : Boolean.FALSE;
            }
            return obj3;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 23) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }
    }

    public static Object every$V(Procedure pred, Object lis1, Object[] argsArray) {
        frame27 frame272;
        Object apply1;
        new frame27();
        frame27 frame273 = frame272;
        frame273.pred = pred;
        frame273.lis1 = lis1;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame273.lists = makeList;
        if (!C1245lists.isPair(frame273.lists)) {
            Object x = isNullList(frame273.lis1);
            if (x == Boolean.FALSE) {
                Object apply12 = C1245lists.car.apply1(frame273.lis1);
                Object apply13 = C1245lists.cdr.apply1(frame273.lis1);
                while (true) {
                    Object obj = apply13;
                    Object head = apply12;
                    if (isNullList(obj) == Boolean.FALSE) {
                        Object x2 = frame273.pred.apply1(head);
                        if (x2 == Boolean.FALSE) {
                            apply1 = x2;
                            break;
                        }
                        apply12 = C1245lists.car.apply1(obj);
                        apply13 = C1245lists.cdr.apply1(obj);
                    } else {
                        apply1 = frame273.pred.apply1(head);
                        break;
                    }
                }
            } else {
                apply1 = x;
            }
        } else {
            apply1 = call_with_values.callWithValues(frame273.lambda$Fn24, frame273.lambda$Fn25);
        }
        return apply1;
    }

    /* compiled from: srfi1.scm */
    public class frame27 extends ModuleBody {
        final ModuleMethod lambda$Fn24;
        final ModuleMethod lambda$Fn25;
        Object lis1;
        LList lists;
        Procedure pred;

        public frame27() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 26, (Object) null, 0);
            this.lambda$Fn24 = moduleMethod;
            new ModuleMethod(this, 27, (Object) null, 8194);
            ModuleMethod moduleMethod3 = moduleMethod2;
            moduleMethod3.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1378");
            this.lambda$Fn25 = moduleMethod3;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 26 ? lambda39() : super.apply0(moduleMethod2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 26) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            if (moduleMethod2.selector == 27) {
                return lambda40(obj3, obj4);
            }
            return super.apply2(moduleMethod2, obj3, obj4);
        }

        /* access modifiers changed from: package-private */
        public Object lambda39() {
            return srfi1.$PcCars$PlCdrs(C1245lists.cons(this.lis1, this.lists));
        }

        /* access modifiers changed from: package-private */
        public Object lambda40(Object obj, Object obj2) {
            frame28 frame28;
            Object lambda41lp;
            Object heads = obj;
            Object tails = obj2;
            new frame28();
            frame28 frame282 = frame28;
            frame282.staticLink = this;
            frame28 frame283 = frame282;
            boolean x = ((C1245lists.isPair(heads) ? 1 : 0) + true) & true;
            if (x) {
                lambda41lp = x ? Boolean.TRUE : Boolean.FALSE;
            } else {
                lambda41lp = frame283.lambda41lp(heads, tails);
            }
            return lambda41lp;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 27) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    public class frame28 extends ModuleBody {
        frame27 staticLink;

        public frame28() {
        }

        public Object lambda41lp(Object heads, Object tails) {
            frame29 frame29;
            new frame29();
            frame29 frame292 = frame29;
            frame292.staticLink = this;
            frame29 frame293 = frame292;
            frame293.heads = heads;
            frame293.tails = tails;
            return call_with_values.callWithValues(frame293.lambda$Fn26, frame293.lambda$Fn27);
        }
    }

    /* compiled from: srfi1.scm */
    public class frame29 extends ModuleBody {
        Object heads;
        final ModuleMethod lambda$Fn26;
        final ModuleMethod lambda$Fn27;
        frame28 staticLink;
        Object tails;

        public frame29() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 24, (Object) null, 0);
            this.lambda$Fn26 = moduleMethod;
            new ModuleMethod(this, 25, (Object) null, 8194);
            ModuleMethod moduleMethod3 = moduleMethod2;
            moduleMethod3.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1381");
            this.lambda$Fn27 = moduleMethod3;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 24 ? lambda42() : super.apply0(moduleMethod2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 24) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            if (moduleMethod2.selector == 25) {
                return lambda43(obj3, obj4);
            }
            return super.apply2(moduleMethod2, obj3, obj4);
        }

        /* access modifiers changed from: package-private */
        public Object lambda42() {
            return srfi1.$PcCars$PlCdrs(this.tails);
        }

        /* access modifiers changed from: package-private */
        public Object lambda43(Object obj, Object obj2) {
            Object apply2;
            Object next$Mnheads = obj;
            Object next$Mntails = obj2;
            if (C1245lists.isPair(next$Mnheads)) {
                Object x = Scheme.apply.apply2(this.staticLink.staticLink.pred, this.heads);
                apply2 = x != Boolean.FALSE ? this.staticLink.lambda41lp(next$Mnheads, next$Mntails) : x;
            } else {
                apply2 = Scheme.apply.apply2(this.staticLink.staticLink.pred, this.heads);
            }
            return apply2;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 25) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }
    }

    public static Object listIndex$V(Procedure pred, Object obj, Object[] argsArray) {
        frame30 frame302;
        Throwable th;
        Object obj2;
        Object lis1 = obj;
        new frame30();
        frame30 frame303 = frame302;
        frame303.pred = pred;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        LList lists = makeList;
        if (C1245lists.isPair(lists)) {
            obj2 = frame303.lambda44lp(C1245lists.cons(lis1, lists), Lit0);
        } else {
            Object obj3 = lis1;
            Object obj4 = Lit0;
            while (true) {
                Object obj5 = obj4;
                Object lis = obj3;
                Object isNullList = isNullList(lis);
                Object obj6 = isNullList;
                try {
                    boolean x = ((isNullList != Boolean.FALSE ? 1 : 0) + 1) & true;
                    if (!x) {
                        obj2 = x ? Boolean.TRUE : Boolean.FALSE;
                    } else if (frame303.pred.apply1(C1245lists.car.apply1(lis)) != Boolean.FALSE) {
                        obj2 = obj5;
                        break;
                    } else {
                        obj3 = C1245lists.cdr.apply1(lis);
                        obj4 = AddOp.$Pl.apply2(obj5, Lit1);
                    }
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th2 = th;
                    new WrongType(classCastException, "x", -2, obj6);
                    throw th2;
                }
            }
        }
        return obj2;
    }

    /* compiled from: srfi1.scm */
    public class frame30 extends ModuleBody {
        Procedure pred;

        public frame30() {
        }

        public Object lambda44lp(Object lists, Object n) {
            frame31 frame31;
            new frame31();
            frame31 frame312 = frame31;
            frame312.staticLink = this;
            frame31 frame313 = frame312;
            frame313.lists = lists;
            frame313.f127n = n;
            return call_with_values.callWithValues(frame313.lambda$Fn28, frame313.lambda$Fn29);
        }
    }

    /* compiled from: srfi1.scm */
    public class frame31 extends ModuleBody {
        final ModuleMethod lambda$Fn28;
        final ModuleMethod lambda$Fn29;
        Object lists;

        /* renamed from: n */
        Object f127n;
        frame30 staticLink;

        public frame31() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 28, (Object) null, 0);
            this.lambda$Fn28 = moduleMethod;
            new ModuleMethod(this, 29, (Object) null, 8194);
            ModuleMethod moduleMethod3 = moduleMethod2;
            moduleMethod3.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1404");
            this.lambda$Fn29 = moduleMethod3;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 28 ? lambda45() : super.apply0(moduleMethod2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 28) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            if (moduleMethod2.selector == 29) {
                return lambda46(obj3, obj4);
            }
            return super.apply2(moduleMethod2, obj3, obj4);
        }

        /* access modifiers changed from: package-private */
        public Object lambda45() {
            return srfi1.$PcCars$PlCdrs(this.lists);
        }

        /* access modifiers changed from: package-private */
        public Object lambda46(Object obj, Object obj2) {
            Object obj3;
            Object heads = obj;
            Object tails = obj2;
            boolean x = C1245lists.isPair(heads);
            if (x) {
                obj3 = Scheme.apply.apply2(this.staticLink.pred, heads) != Boolean.FALSE ? this.f127n : this.staticLink.lambda44lp(tails, AddOp.$Pl.apply2(this.f127n, srfi1.Lit1));
            } else {
                obj3 = x ? Boolean.TRUE : Boolean.FALSE;
            }
            return obj3;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 29) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    public class frame72 extends ModuleBody {
        Object $Eq;
        final ModuleMethod lambda$Fn79;
        Object lis2;

        public frame72() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 77, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1443");
            this.lambda$Fn79 = moduleMethod2;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector == 77) {
                return lambda101(obj2);
            }
            return super.apply1(moduleMethod2, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda101(Object x) {
            Throwable th;
            Object obj = x;
            Object obj2 = this.lis2;
            Object obj3 = this.$Eq;
            Object obj4 = obj3;
            try {
                return C1245lists.member(obj, obj2, (Procedure) obj3);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "member", 3, obj4);
                throw th2;
            }
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 77) {
                return super.match1(moduleMethod2, obj2, callContext2);
            }
            callContext2.value1 = obj2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 1;
            return 0;
        }
    }

    static Object $PcLset2$Ls$Eq(Object $Eq, Object lis1, Object lis2) {
        frame72 frame722;
        new frame72();
        frame72 frame723 = frame722;
        frame723.$Eq = $Eq;
        frame723.lis2 = lis2;
        return every$V(frame723.lambda$Fn79, lis1, new Object[0]);
    }

    public static Object lset$Ls$Eq$V(Procedure procedure, Object[] argsArray) {
        Object obj;
        Procedure $Eq = procedure;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        LList lists = makeList;
        boolean x = ((C1245lists.isPair(lists) ? 1 : 0) + true) & true;
        if (!x) {
            Object apply1 = C1245lists.car.apply1(lists);
            Object apply12 = C1245lists.cdr.apply1(lists);
            while (true) {
                Object obj2 = apply12;
                Object s1 = apply1;
                boolean x2 = ((C1245lists.isPair(obj2) ? 1 : 0) + true) & true;
                if (!x2) {
                    Object apply13 = C1245lists.car.apply1(obj2);
                    Object rest = C1245lists.cdr.apply1(obj2);
                    Object s2 = apply13;
                    boolean x3 = s2 == s1;
                    Object x4 = x3 ? x3 ? Boolean.TRUE : Boolean.FALSE : $PcLset2$Ls$Eq($Eq, s1, s2);
                    if (x4 == Boolean.FALSE) {
                        obj = x4;
                        break;
                    }
                    apply1 = s2;
                    apply12 = rest;
                } else {
                    obj = x2 ? Boolean.TRUE : Boolean.FALSE;
                }
            }
        } else {
            obj = x ? Boolean.TRUE : Boolean.FALSE;
        }
        return obj;
    }

    public static Object lset$Eq$V(Procedure procedure, Object[] argsArray) {
        Object obj;
        Object $PcLset2$Ls$Eq;
        Procedure $Eq = procedure;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        LList lists = makeList;
        boolean x = (C1245lists.isPair(lists) + 1) & true;
        if (!x) {
            Object apply1 = C1245lists.car.apply1(lists);
            Object apply12 = C1245lists.cdr.apply1(lists);
            while (true) {
                Object obj2 = apply12;
                Object s1 = apply1;
                boolean x2 = (C1245lists.isPair(obj2) + 1) & true;
                if (!x2) {
                    Object apply13 = C1245lists.car.apply1(obj2);
                    Object rest = C1245lists.cdr.apply1(obj2);
                    Object s2 = apply13;
                    boolean x3 = s1 == s2;
                    if (x3) {
                        $PcLset2$Ls$Eq = x3 ? Boolean.TRUE : Boolean.FALSE;
                    } else {
                        Object x4 = $PcLset2$Ls$Eq($Eq, s1, s2);
                        $PcLset2$Ls$Eq = x4 != Boolean.FALSE ? $PcLset2$Ls$Eq($Eq, s2, s1) : x4;
                    }
                    Object x5 = $PcLset2$Ls$Eq;
                    if (x5 == Boolean.FALSE) {
                        obj = x5;
                        break;
                    }
                    apply1 = s2;
                    apply12 = rest;
                } else {
                    obj = x2 ? Boolean.TRUE : Boolean.FALSE;
                }
            }
        } else {
            obj = x ? Boolean.TRUE : Boolean.FALSE;
        }
        return obj;
    }

    /* compiled from: srfi1.scm */
    public class frame32 extends ModuleBody {
        Procedure $Eq;
        final ModuleMethod lambda$Fn30;

        public frame32() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 30, (Object) null, 8194);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1466");
            this.lambda$Fn30 = moduleMethod2;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            if (moduleMethod2.selector == 30) {
                return lambda47(obj3, obj4);
            }
            return super.apply2(moduleMethod2, obj3, obj4);
        }

        /* access modifiers changed from: package-private */
        public Object lambda47(Object obj, Object obj2) {
            Object elt = obj;
            Object ans = obj2;
            return C1245lists.member(elt, ans, this.$Eq) != Boolean.FALSE ? ans : C1245lists.cons(elt, ans);
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 30) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }
    }

    public static Object lsetAdjoin$V(Procedure $Eq, Object lis, Object[] argsArray) {
        frame32 frame322;
        new frame32();
        frame32 frame323 = frame322;
        frame323.$Eq = $Eq;
        LList elts = LList.makeList(argsArray, 0);
        LList lList = elts;
        return fold$V(frame323.lambda$Fn30, lis, elts, new Object[0]);
    }

    /* compiled from: srfi1.scm */
    public class frame33 extends ModuleBody {
        Procedure $Eq;
        final ModuleMethod lambda$Fn31;
        final ModuleMethod lambda$Fn32;

        public frame33() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 32, (Object) null, 8194);
            ModuleMethod moduleMethod3 = moduleMethod;
            moduleMethod3.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1476");
            this.lambda$Fn32 = moduleMethod3;
            new ModuleMethod(this, 33, (Object) null, 8194);
            ModuleMethod moduleMethod4 = moduleMethod2;
            moduleMethod4.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1471");
            this.lambda$Fn31 = moduleMethod4;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            switch (moduleMethod2.selector) {
                case 32:
                    return lambda49(obj3, obj4);
                case 33:
                    return lambda48(obj3, obj4);
                default:
                    return super.apply2(moduleMethod2, obj3, obj4);
            }
        }

        /* access modifiers changed from: package-private */
        public Object lambda48(Object obj, Object obj2) {
            Object fold$V;
            Object lis = obj;
            Object ans = obj2;
            if (C1245lists.isNull(lis)) {
                fold$V = ans;
            } else if (C1245lists.isNull(ans)) {
                fold$V = lis;
            } else if (lis == ans) {
                fold$V = ans;
            } else {
                fold$V = srfi1.fold$V(this.lambda$Fn32, ans, lis, new Object[0]);
            }
            return fold$V;
        }

        /* access modifiers changed from: package-private */
        public Object lambda49(Object elt, Object obj) {
            frame34 frame34;
            Object cons;
            Object ans = obj;
            new frame34();
            frame34 frame342 = frame34;
            frame342.staticLink = this;
            frame34 frame343 = frame342;
            frame343.elt = elt;
            if (srfi1.any$V(frame343.lambda$Fn33, ans, new Object[0]) != Boolean.FALSE) {
                cons = ans;
            } else {
                cons = C1245lists.cons(frame343.elt, ans);
            }
            return cons;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            switch (moduleMethod2.selector) {
                case 32:
                    callContext2.value1 = obj3;
                    callContext2.value2 = obj4;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 2;
                    return 0;
                case 33:
                    callContext2.value1 = obj3;
                    callContext2.value2 = obj4;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 2;
                    return 0;
                default:
                    return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
        }
    }

    public static Object lsetUnion$V(Procedure $Eq, Object[] argsArray) {
        frame33 frame332;
        new frame33();
        frame33 frame333 = frame332;
        frame333.$Eq = $Eq;
        LList lists = LList.makeList(argsArray, 0);
        LList lList = lists;
        return reduce(frame333.lambda$Fn31, LList.Empty, lists);
    }

    /* compiled from: srfi1.scm */
    public class frame34 extends ModuleBody {
        Object elt;
        final ModuleMethod lambda$Fn33;
        frame33 staticLink;

        public frame34() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 31, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1476");
            this.lambda$Fn33 = moduleMethod2;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector == 31) {
                return lambda50(obj2);
            }
            return super.apply1(moduleMethod2, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda50(Object x) {
            return this.staticLink.$Eq.apply2(x, this.elt);
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 31) {
                return super.match1(moduleMethod2, obj2, callContext2);
            }
            callContext2.value1 = obj2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 1;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    public class frame35 extends ModuleBody {
        Procedure $Eq;
        final ModuleMethod lambda$Fn34;
        final ModuleMethod lambda$Fn35;

        public frame35() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 35, (Object) null, 8194);
            ModuleMethod moduleMethod3 = moduleMethod;
            moduleMethod3.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1488");
            this.lambda$Fn35 = moduleMethod3;
            new ModuleMethod(this, 36, (Object) null, 8194);
            ModuleMethod moduleMethod4 = moduleMethod2;
            moduleMethod4.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1483");
            this.lambda$Fn34 = moduleMethod4;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            switch (moduleMethod2.selector) {
                case 35:
                    return lambda52(obj3, obj4);
                case 36:
                    return lambda51(obj3, obj4);
                default:
                    return super.apply2(moduleMethod2, obj3, obj4);
            }
        }

        /* access modifiers changed from: package-private */
        public Object lambda51(Object obj, Object obj2) {
            Object pairFold$V;
            Object lis = obj;
            Object ans = obj2;
            if (C1245lists.isNull(lis)) {
                pairFold$V = ans;
            } else if (C1245lists.isNull(ans)) {
                pairFold$V = lis;
            } else if (lis == ans) {
                pairFold$V = ans;
            } else {
                pairFold$V = srfi1.pairFold$V(this.lambda$Fn35, ans, lis, new Object[0]);
            }
            return pairFold$V;
        }

        /* access modifiers changed from: package-private */
        public Object lambda52(Object obj, Object obj2) {
            frame36 frame36;
            Throwable th;
            Object obj3;
            Object pair = obj;
            Object ans = obj2;
            new frame36();
            frame36 frame362 = frame36;
            frame362.staticLink = this;
            frame36 frame363 = frame362;
            frame363.elt = C1245lists.car.apply1(pair);
            if (srfi1.any$V(frame363.lambda$Fn36, ans, new Object[0]) != Boolean.FALSE) {
                obj3 = ans;
            } else {
                Object obj4 = pair;
                Object obj5 = obj4;
                try {
                    C1245lists.setCdr$Ex((Pair) obj4, ans);
                    obj3 = pair;
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th2 = th;
                    new WrongType(classCastException, "set-cdr!", 1, obj5);
                    throw th2;
                }
            }
            return obj3;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            switch (moduleMethod2.selector) {
                case 35:
                    callContext2.value1 = obj3;
                    callContext2.value2 = obj4;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 2;
                    return 0;
                case 36:
                    callContext2.value1 = obj3;
                    callContext2.value2 = obj4;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 2;
                    return 0;
                default:
                    return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
        }
    }

    public static Object lsetUnion$Ex$V(Procedure $Eq, Object[] argsArray) {
        frame35 frame352;
        new frame35();
        frame35 frame353 = frame352;
        frame353.$Eq = $Eq;
        LList lists = LList.makeList(argsArray, 0);
        LList lList = lists;
        return reduce(frame353.lambda$Fn34, LList.Empty, lists);
    }

    /* compiled from: srfi1.scm */
    public class frame36 extends ModuleBody {
        Object elt;
        final ModuleMethod lambda$Fn36;
        frame35 staticLink;

        public frame36() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 34, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1490");
            this.lambda$Fn36 = moduleMethod2;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector == 34) {
                return lambda53(obj2);
            }
            return super.apply1(moduleMethod2, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda53(Object x) {
            return this.staticLink.$Eq.apply2(x, this.elt);
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 34) {
                return super.match1(moduleMethod2, obj2, callContext2);
            }
            callContext2.value1 = obj2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 1;
            return 0;
        }
    }

    public static Object lsetIntersection$V(Procedure $Eq, Object obj, Object[] argsArray) {
        frame37 frame372;
        Object filter2;
        Object lis1 = obj;
        new frame37();
        frame37 frame373 = frame372;
        frame373.$Eq = $Eq;
        LList lists = LList.makeList(argsArray, 0);
        LList lList = lists;
        frame373.lists = delete(lis1, lists, Scheme.isEq);
        if (any$V(null$Mnlist$Qu, frame373.lists, new Object[0]) != Boolean.FALSE) {
            filter2 = LList.Empty;
        } else if (C1245lists.isNull(frame373.lists)) {
            filter2 = lis1;
        } else {
            filter2 = filter(frame373.lambda$Fn37, lis1);
        }
        return filter2;
    }

    /* compiled from: srfi1.scm */
    public class frame37 extends ModuleBody {
        Procedure $Eq;
        final ModuleMethod lambda$Fn37;
        Object lists;

        public frame37() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 38, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1501");
            this.lambda$Fn37 = moduleMethod2;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector == 38) {
                return lambda54(obj2);
            }
            return super.apply1(moduleMethod2, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda54(Object x) {
            frame38 frame38;
            new frame38();
            frame38 frame382 = frame38;
            frame382.staticLink = this;
            frame38 frame383 = frame382;
            frame383.f128x = x;
            return srfi1.every$V(frame383.lambda$Fn38, this.lists, new Object[0]);
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 38) {
                return super.match1(moduleMethod2, obj2, callContext2);
            }
            callContext2.value1 = obj2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 1;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    public class frame38 extends ModuleBody {
        final ModuleMethod lambda$Fn38;
        frame37 staticLink;

        /* renamed from: x */
        Object f128x;

        public frame38() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 37, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1502");
            this.lambda$Fn38 = moduleMethod2;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector == 37) {
                return lambda55(obj2);
            }
            return super.apply1(moduleMethod2, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda55(Object lis) {
            return C1245lists.member(this.f128x, lis, this.staticLink.$Eq);
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 37) {
                return super.match1(moduleMethod2, obj2, callContext2);
            }
            callContext2.value1 = obj2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 1;
            return 0;
        }
    }

    public static Object lsetIntersection$Ex$V(Procedure $Eq, Object obj, Object[] argsArray) {
        frame39 frame392;
        Object filter$Ex2;
        Object lis1 = obj;
        new frame39();
        frame39 frame393 = frame392;
        frame393.$Eq = $Eq;
        LList lists = LList.makeList(argsArray, 0);
        LList lList = lists;
        frame393.lists = delete(lis1, lists, Scheme.isEq);
        if (any$V(null$Mnlist$Qu, frame393.lists, new Object[0]) != Boolean.FALSE) {
            filter$Ex2 = LList.Empty;
        } else if (C1245lists.isNull(frame393.lists)) {
            filter$Ex2 = lis1;
        } else {
            filter$Ex2 = filter$Ex(frame393.lambda$Fn39, lis1);
        }
        return filter$Ex2;
    }

    /* compiled from: srfi1.scm */
    public class frame39 extends ModuleBody {
        Procedure $Eq;
        final ModuleMethod lambda$Fn39;
        Object lists;

        public frame39() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 40, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1509");
            this.lambda$Fn39 = moduleMethod2;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector == 40) {
                return lambda56(obj2);
            }
            return super.apply1(moduleMethod2, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda56(Object x) {
            frame40 frame40;
            new frame40();
            frame40 frame402 = frame40;
            frame402.staticLink = this;
            frame40 frame403 = frame402;
            frame403.f129x = x;
            return srfi1.every$V(frame403.lambda$Fn40, this.lists, new Object[0]);
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 40) {
                return super.match1(moduleMethod2, obj2, callContext2);
            }
            callContext2.value1 = obj2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 1;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    public class frame40 extends ModuleBody {
        final ModuleMethod lambda$Fn40;
        frame39 staticLink;

        /* renamed from: x */
        Object f129x;

        public frame40() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 39, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1510");
            this.lambda$Fn40 = moduleMethod2;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector == 39) {
                return lambda57(obj2);
            }
            return super.apply1(moduleMethod2, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda57(Object lis) {
            return C1245lists.member(this.f129x, lis, this.staticLink.$Eq);
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 39) {
                return super.match1(moduleMethod2, obj2, callContext2);
            }
            callContext2.value1 = obj2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 1;
            return 0;
        }
    }

    public static Object lsetDifference$V(Procedure $Eq, Object obj, Object[] argsArray) {
        frame41 frame412;
        Object lis1 = obj;
        new frame41();
        frame41 frame413 = frame412;
        frame413.$Eq = $Eq;
        LList lists = LList.makeList(argsArray, 0);
        LList lList = lists;
        frame413.lists = filter(C1245lists.pair$Qu, lists);
        return C1245lists.isNull(frame413.lists) ? lis1 : C1245lists.memq(lis1, frame413.lists) != Boolean.FALSE ? LList.Empty : filter(frame413.lambda$Fn41, lis1);
    }

    /* compiled from: srfi1.scm */
    public class frame41 extends ModuleBody {
        Procedure $Eq;
        final ModuleMethod lambda$Fn41;
        Object lists;

        public frame41() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 42, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1518");
            this.lambda$Fn41 = moduleMethod2;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector == 42) {
                return lambda58(obj2);
            }
            return super.apply1(moduleMethod2, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda58(Object x) {
            frame42 frame42;
            new frame42();
            frame42 frame422 = frame42;
            frame422.staticLink = this;
            frame42 frame423 = frame422;
            frame423.f130x = x;
            return srfi1.every$V(frame423.lambda$Fn42, this.lists, new Object[0]);
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 42) {
                return super.match1(moduleMethod2, obj2, callContext2);
            }
            callContext2.value1 = obj2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 1;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    public class frame42 extends ModuleBody {
        final ModuleMethod lambda$Fn42;
        frame41 staticLink;

        /* renamed from: x */
        Object f130x;

        public frame42() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 41, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1519");
            this.lambda$Fn42 = moduleMethod2;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            Boolean bool;
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector != 41) {
                return super.apply1(moduleMethod2, obj2);
            }
            if (lambda59(obj2)) {
                bool = Boolean.TRUE;
            } else {
                bool = Boolean.FALSE;
            }
            return bool;
        }

        /* access modifiers changed from: package-private */
        public boolean lambda59(Object lis) {
            return ((C1245lists.member(this.f130x, lis, this.staticLink.$Eq) != Boolean.FALSE ? 1 : 0) + 1) & true;
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 41) {
                return super.match1(moduleMethod2, obj2, callContext2);
            }
            callContext2.value1 = obj2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 1;
            return 0;
        }
    }

    public static Object lsetDifference$Ex$V(Procedure $Eq, Object obj, Object[] argsArray) {
        frame43 frame432;
        Object lis1 = obj;
        new frame43();
        frame43 frame433 = frame432;
        frame433.$Eq = $Eq;
        LList lists = LList.makeList(argsArray, 0);
        LList lList = lists;
        frame433.lists = filter(C1245lists.pair$Qu, lists);
        return C1245lists.isNull(frame433.lists) ? lis1 : C1245lists.memq(lis1, frame433.lists) != Boolean.FALSE ? LList.Empty : filter$Ex(frame433.lambda$Fn43, lis1);
    }

    /* compiled from: srfi1.scm */
    public class frame43 extends ModuleBody {
        Procedure $Eq;
        final ModuleMethod lambda$Fn43;
        Object lists;

        public frame43() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 44, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1527");
            this.lambda$Fn43 = moduleMethod2;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector == 44) {
                return lambda60(obj2);
            }
            return super.apply1(moduleMethod2, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda60(Object x) {
            frame44 frame44;
            new frame44();
            frame44 frame442 = frame44;
            frame442.staticLink = this;
            frame44 frame443 = frame442;
            frame443.f131x = x;
            return srfi1.every$V(frame443.lambda$Fn44, this.lists, new Object[0]);
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 44) {
                return super.match1(moduleMethod2, obj2, callContext2);
            }
            callContext2.value1 = obj2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 1;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    public class frame44 extends ModuleBody {
        final ModuleMethod lambda$Fn44;
        frame43 staticLink;

        /* renamed from: x */
        Object f131x;

        public frame44() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 43, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1528");
            this.lambda$Fn44 = moduleMethod2;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            Boolean bool;
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector != 43) {
                return super.apply1(moduleMethod2, obj2);
            }
            if (lambda61(obj2)) {
                bool = Boolean.TRUE;
            } else {
                bool = Boolean.FALSE;
            }
            return bool;
        }

        /* access modifiers changed from: package-private */
        public boolean lambda61(Object lis) {
            return ((C1245lists.member(this.f131x, lis, this.staticLink.$Eq) != Boolean.FALSE ? 1 : 0) + 1) & true;
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 43) {
                return super.match1(moduleMethod2, obj2, callContext2);
            }
            callContext2.value1 = obj2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 1;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    public class frame45 extends ModuleBody {
        Procedure $Eq;
        final ModuleMethod lambda$Fn45;

        public frame45() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 48, (Object) null, 8194);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1534");
            this.lambda$Fn45 = moduleMethod2;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            if (moduleMethod2.selector == 48) {
                return lambda62(obj3, obj4);
            }
            return super.apply2(moduleMethod2, obj3, obj4);
        }

        /* access modifiers changed from: package-private */
        public Object lambda62(Object b, Object a) {
            frame46 frame46;
            new frame46();
            frame46 frame462 = frame46;
            frame462.staticLink = this;
            frame46 frame463 = frame462;
            frame463.f133b = b;
            frame463.f132a = a;
            return call_with_values.callWithValues(frame463.lambda$Fn46, frame463.lambda$Fn47);
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 48) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }
    }

    public static Object lsetXor$V(Procedure $Eq, Object[] argsArray) {
        frame45 frame452;
        new frame45();
        frame45 frame453 = frame452;
        frame453.$Eq = $Eq;
        LList lists = LList.makeList(argsArray, 0);
        LList lList = lists;
        return reduce(frame453.lambda$Fn45, LList.Empty, lists);
    }

    /* compiled from: srfi1.scm */
    public class frame46 extends ModuleBody {

        /* renamed from: a */
        Object f132a;

        /* renamed from: b */
        Object f133b;
        final ModuleMethod lambda$Fn46;
        final ModuleMethod lambda$Fn47;
        frame45 staticLink;

        public frame46() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 46, (Object) null, 0);
            this.lambda$Fn46 = moduleMethod;
            new ModuleMethod(this, 47, (Object) null, 8194);
            ModuleMethod moduleMethod3 = moduleMethod2;
            moduleMethod3.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1544");
            this.lambda$Fn47 = moduleMethod3;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 46 ? lambda63() : super.apply0(moduleMethod2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 46) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            if (moduleMethod2.selector == 47) {
                return lambda64(obj3, obj4);
            }
            return super.apply2(moduleMethod2, obj3, obj4);
        }

        /* access modifiers changed from: package-private */
        public Object lambda63() {
            return srfi1.lsetDiff$PlIntersection$V(this.staticLink.$Eq, this.f132a, new Object[]{this.f133b});
        }

        /* access modifiers changed from: package-private */
        public Object lambda64(Object obj, Object aIntB) {
            frame47 frame47;
            Object fold$V;
            Object a$Mnb = obj;
            new frame47();
            frame47 frame472 = frame47;
            frame472.staticLink = this;
            frame47 frame473 = frame472;
            frame473.a$Mnint$Mnb = aIntB;
            if (C1245lists.isNull(a$Mnb)) {
                fold$V = srfi1.lsetDifference$V(this.staticLink.$Eq, this.f133b, new Object[]{this.f132a});
            } else if (C1245lists.isNull(frame473.a$Mnint$Mnb)) {
                Object[] objArr = new Object[2];
                objArr[0] = this.f133b;
                Object[] objArr2 = objArr;
                objArr2[1] = this.f132a;
                fold$V = append.append$V(objArr2);
            } else {
                fold$V = srfi1.fold$V(frame473.lambda$Fn48, a$Mnb, this.f133b, new Object[0]);
            }
            return fold$V;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 47) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    public class frame47 extends ModuleBody {
        Object a$Mnint$Mnb;
        final ModuleMethod lambda$Fn48;
        frame46 staticLink;

        public frame47() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 45, (Object) null, 8194);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1547");
            this.lambda$Fn48 = moduleMethod2;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            if (moduleMethod2.selector == 45) {
                return lambda65(obj3, obj4);
            }
            return super.apply2(moduleMethod2, obj3, obj4);
        }

        /* access modifiers changed from: package-private */
        public Object lambda65(Object obj, Object obj2) {
            Object xb = obj;
            Object ans = obj2;
            return C1245lists.member(xb, this.a$Mnint$Mnb, this.staticLink.staticLink.$Eq) != Boolean.FALSE ? ans : C1245lists.cons(xb, ans);
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 45) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    public class frame48 extends ModuleBody {
        Procedure $Eq;
        final ModuleMethod lambda$Fn49;

        public frame48() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 52, (Object) null, 8194);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1555");
            this.lambda$Fn49 = moduleMethod2;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            if (moduleMethod2.selector == 52) {
                return lambda66(obj3, obj4);
            }
            return super.apply2(moduleMethod2, obj3, obj4);
        }

        /* access modifiers changed from: package-private */
        public Object lambda66(Object b, Object a) {
            frame49 frame49;
            new frame49();
            frame49 frame492 = frame49;
            frame492.staticLink = this;
            frame49 frame493 = frame492;
            frame493.f135b = b;
            frame493.f134a = a;
            return call_with_values.callWithValues(frame493.lambda$Fn50, frame493.lambda$Fn51);
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 52) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }
    }

    public static Object lsetXor$Ex$V(Procedure $Eq, Object[] argsArray) {
        frame48 frame482;
        new frame48();
        frame48 frame483 = frame482;
        frame483.$Eq = $Eq;
        LList lists = LList.makeList(argsArray, 0);
        LList lList = lists;
        return reduce(frame483.lambda$Fn49, LList.Empty, lists);
    }

    /* compiled from: srfi1.scm */
    public class frame49 extends ModuleBody {

        /* renamed from: a */
        Object f134a;

        /* renamed from: b */
        Object f135b;
        final ModuleMethod lambda$Fn50;
        final ModuleMethod lambda$Fn51;
        frame48 staticLink;

        public frame49() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 50, (Object) null, 0);
            this.lambda$Fn50 = moduleMethod;
            new ModuleMethod(this, 51, (Object) null, 8194);
            ModuleMethod moduleMethod3 = moduleMethod2;
            moduleMethod3.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1565");
            this.lambda$Fn51 = moduleMethod3;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 50 ? lambda67() : super.apply0(moduleMethod2);
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 50) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            if (moduleMethod2.selector == 51) {
                return lambda68(obj3, obj4);
            }
            return super.apply2(moduleMethod2, obj3, obj4);
        }

        /* access modifiers changed from: package-private */
        public Object lambda67() {
            return srfi1.lsetDiff$PlIntersection$Ex$V(this.staticLink.$Eq, this.f134a, new Object[]{this.f135b});
        }

        /* access modifiers changed from: package-private */
        public Object lambda68(Object obj, Object aIntB) {
            frame50 frame50;
            Object pairFold$V;
            Object a$Mnb = obj;
            new frame50();
            frame50 frame502 = frame50;
            frame502.staticLink = this;
            frame50 frame503 = frame502;
            frame503.a$Mnint$Mnb = aIntB;
            if (C1245lists.isNull(a$Mnb)) {
                pairFold$V = srfi1.lsetDifference$Ex$V(this.staticLink.$Eq, this.f135b, new Object[]{this.f134a});
            } else if (C1245lists.isNull(frame503.a$Mnint$Mnb)) {
                Object[] objArr = new Object[2];
                objArr[0] = this.f135b;
                Object[] objArr2 = objArr;
                objArr2[1] = this.f134a;
                pairFold$V = srfi1.append$Ex$V(objArr2);
            } else {
                pairFold$V = srfi1.pairFold$V(frame503.lambda$Fn52, a$Mnb, this.f135b, new Object[0]);
            }
            return pairFold$V;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 51) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    public class frame50 extends ModuleBody {
        Object a$Mnint$Mnb;
        final ModuleMethod lambda$Fn52;
        frame49 staticLink;

        public frame50() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 49, (Object) null, 8194);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1568");
            this.lambda$Fn52 = moduleMethod2;
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            if (moduleMethod2.selector == 49) {
                return lambda69(obj3, obj4);
            }
            return super.apply2(moduleMethod2, obj3, obj4);
        }

        /* access modifiers changed from: package-private */
        public Object lambda69(Object obj, Object obj2) {
            Throwable th;
            Object obj3;
            Object b$Mnpair = obj;
            Object ans = obj2;
            if (C1245lists.member(C1245lists.car.apply1(b$Mnpair), this.a$Mnint$Mnb, this.staticLink.staticLink.$Eq) != Boolean.FALSE) {
                obj3 = ans;
            } else {
                Object obj4 = b$Mnpair;
                Object obj5 = obj4;
                try {
                    C1245lists.setCdr$Ex((Pair) obj4, ans);
                    obj3 = b$Mnpair;
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th2 = th;
                    new WrongType(classCastException, "set-cdr!", 1, obj5);
                    throw th2;
                }
            }
            return obj3;
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj3 = obj;
            Object obj4 = obj2;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 49) {
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
            }
            callContext2.value1 = obj3;
            callContext2.value2 = obj4;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 2;
            return 0;
        }
    }

    public static Object lsetDiff$PlIntersection$V(Procedure $Eq, Object obj, Object[] argsArray) {
        frame51 frame512;
        Object partition2;
        Object lis1 = obj;
        new frame51();
        frame51 frame513 = frame512;
        frame513.$Eq = $Eq;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame513.lists = makeList;
        if (every$V(null$Mnlist$Qu, frame513.lists, new Object[0]) != Boolean.FALSE) {
            Object[] objArr = new Object[2];
            objArr[0] = lis1;
            Object[] objArr2 = objArr;
            objArr2[1] = LList.Empty;
            partition2 = misc.values(objArr2);
        } else if (C1245lists.memq(lis1, frame513.lists) != Boolean.FALSE) {
            Object[] objArr3 = new Object[2];
            objArr3[0] = LList.Empty;
            Object[] objArr4 = objArr3;
            objArr4[1] = lis1;
            partition2 = misc.values(objArr4);
        } else {
            partition2 = partition(frame513.lambda$Fn53, lis1);
        }
        return partition2;
    }

    /* compiled from: srfi1.scm */
    public class frame51 extends ModuleBody {
        Procedure $Eq;
        final ModuleMethod lambda$Fn53;
        LList lists;

        public frame51() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 54, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1579");
            this.lambda$Fn53 = moduleMethod2;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            Boolean bool;
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector != 54) {
                return super.apply1(moduleMethod2, obj2);
            }
            if (lambda70(obj2)) {
                bool = Boolean.TRUE;
            } else {
                bool = Boolean.FALSE;
            }
            return bool;
        }

        /* access modifiers changed from: package-private */
        public boolean lambda70(Object elt) {
            frame52 frame52;
            new frame52();
            frame52 frame522 = frame52;
            frame522.staticLink = this;
            frame52 frame523 = frame522;
            frame523.elt = elt;
            return ((srfi1.any$V(frame523.lambda$Fn54, this.lists, new Object[0]) != Boolean.FALSE ? 1 : 0) + 1) & true;
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 54) {
                return super.match1(moduleMethod2, obj2, callContext2);
            }
            callContext2.value1 = obj2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 1;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    public class frame52 extends ModuleBody {
        Object elt;
        final ModuleMethod lambda$Fn54;
        frame51 staticLink;

        public frame52() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 53, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1580");
            this.lambda$Fn54 = moduleMethod2;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector == 53) {
                return lambda71(obj2);
            }
            return super.apply1(moduleMethod2, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda71(Object lis) {
            return C1245lists.member(this.elt, lis, this.staticLink.$Eq);
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 53) {
                return super.match1(moduleMethod2, obj2, callContext2);
            }
            callContext2.value1 = obj2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 1;
            return 0;
        }
    }

    public static Object lsetDiff$PlIntersection$Ex$V(Procedure $Eq, Object obj, Object[] argsArray) {
        frame53 frame532;
        Object partition$Ex2;
        Object lis1 = obj;
        new frame53();
        frame53 frame533 = frame532;
        frame533.$Eq = $Eq;
        LList makeList = LList.makeList(argsArray, 0);
        LList lList = makeList;
        frame533.lists = makeList;
        if (every$V(null$Mnlist$Qu, frame533.lists, new Object[0]) != Boolean.FALSE) {
            Object[] objArr = new Object[2];
            objArr[0] = lis1;
            Object[] objArr2 = objArr;
            objArr2[1] = LList.Empty;
            partition$Ex2 = misc.values(objArr2);
        } else if (C1245lists.memq(lis1, frame533.lists) != Boolean.FALSE) {
            Object[] objArr3 = new Object[2];
            objArr3[0] = LList.Empty;
            Object[] objArr4 = objArr3;
            objArr4[1] = lis1;
            partition$Ex2 = misc.values(objArr4);
        } else {
            partition$Ex2 = partition$Ex(frame533.lambda$Fn55, lis1);
        }
        return partition$Ex2;
    }

    public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
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
        Throwable th18;
        Throwable th19;
        Throwable th20;
        Throwable th21;
        Throwable th22;
        Throwable th23;
        Throwable th24;
        Throwable th25;
        Throwable th26;
        Throwable th27;
        Throwable th28;
        Throwable th29;
        Object unfoldRight;
        Throwable th30;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object[] objArr2 = objArr;
        switch (moduleMethod2.selector) {
            case 79:
                Object obj = objArr2[0];
                int length = objArr2.length - 1;
                Object[] objArr3 = new Object[length];
                while (true) {
                    length--;
                    if (length < 0) {
                        return makeList$V(obj, objArr3);
                    }
                    Object[] objArr4 = objArr3;
                    objArr3 = objArr4;
                    objArr4[length] = objArr2[length + 1];
                }
            case 81:
                return cons$St(objArr2);
            case 86:
                Object obj2 = objArr2[0];
                int length2 = objArr2.length - 1;
                Object[] objArr5 = new Object[length2];
                while (true) {
                    length2--;
                    if (length2 < 0) {
                        return circularList$V(obj2, objArr5);
                    }
                    Object[] objArr6 = objArr5;
                    objArr5 = objArr6;
                    objArr6[length2] = objArr2[length2 + 1];
                }
            case 92:
                Object obj3 = objArr2[0];
                int length3 = objArr2.length - 1;
                Object[] objArr7 = new Object[length3];
                while (true) {
                    length3--;
                    if (length3 < 0) {
                        return list$Eq$V(obj3, objArr7);
                    }
                    Object[] objArr8 = objArr7;
                    objArr7 = objArr8;
                    objArr8[length3] = objArr2[length3 + 1];
                }
            case 94:
                Object obj4 = objArr2[0];
                int length4 = objArr2.length - 1;
                Object[] objArr9 = new Object[length4];
                while (true) {
                    length4--;
                    if (length4 < 0) {
                        return zip$V(obj4, objArr9);
                    }
                    Object[] objArr10 = objArr9;
                    objArr9 = objArr10;
                    objArr10[length4] = objArr2[length4 + 1];
                }
            case 117:
                return append$Ex$V(objArr2);
            case 122:
                Object obj5 = objArr2[0];
                Object obj6 = obj5;
                try {
                    Procedure procedure = (Procedure) obj5;
                    Object obj7 = objArr2[1];
                    int length5 = objArr2.length - 2;
                    Object[] objArr11 = new Object[length5];
                    while (true) {
                        length5--;
                        if (length5 < 0) {
                            return count$V(procedure, obj7, objArr11);
                        }
                        Object[] objArr12 = objArr11;
                        objArr11 = objArr12;
                        objArr12[length5] = objArr2[length5 + 2];
                    }
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th31 = th30;
                    new WrongType(classCastException, "count", 1, obj6);
                    throw th31;
                }
            case 123:
                int length6 = objArr2.length - 4;
                Object obj8 = objArr2[0];
                Object obj9 = obj8;
                try {
                    Procedure procedure2 = (Procedure) obj8;
                    Object obj10 = objArr2[1];
                    Object obj11 = obj10;
                    try {
                        Procedure procedure3 = (Procedure) obj10;
                        Object obj12 = objArr2[2];
                        Object obj13 = obj12;
                        try {
                            Procedure procedure4 = (Procedure) obj12;
                            Object obj14 = objArr2[3];
                            if (length6 <= 0) {
                                unfoldRight = unfoldRight(procedure2, procedure3, procedure4, obj14);
                            } else {
                                int i = length6 - 1;
                                unfoldRight = unfoldRight(procedure2, procedure3, procedure4, obj14, objArr2[4]);
                            }
                            return unfoldRight;
                        } catch (ClassCastException e2) {
                            ClassCastException classCastException2 = e2;
                            Throwable th32 = th29;
                            new WrongType(classCastException2, "unfold-right", 3, obj13);
                            throw th32;
                        }
                    } catch (ClassCastException e3) {
                        ClassCastException classCastException3 = e3;
                        Throwable th33 = th28;
                        new WrongType(classCastException3, "unfold-right", 2, obj11);
                        throw th33;
                    }
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th34 = th27;
                    new WrongType(classCastException4, "unfold-right", 1, obj9);
                    throw th34;
                }
            case 125:
                Object obj15 = objArr2[0];
                Object obj16 = obj15;
                try {
                    Procedure procedure5 = (Procedure) obj15;
                    Object obj17 = objArr2[1];
                    Object obj18 = obj17;
                    try {
                        Procedure procedure6 = (Procedure) obj17;
                        Object obj19 = objArr2[2];
                        Object obj20 = obj19;
                        try {
                            Procedure procedure7 = (Procedure) obj19;
                            Object obj21 = objArr2[3];
                            int length7 = objArr2.length - 4;
                            Object[] objArr13 = new Object[length7];
                            while (true) {
                                length7--;
                                if (length7 < 0) {
                                    return unfold$V(procedure5, procedure6, procedure7, obj21, objArr13);
                                }
                                Object[] objArr14 = objArr13;
                                objArr13 = objArr14;
                                objArr14[length7] = objArr2[length7 + 4];
                            }
                        } catch (ClassCastException e5) {
                            ClassCastException classCastException5 = e5;
                            Throwable th35 = th26;
                            new WrongType(classCastException5, "unfold", 3, obj20);
                            throw th35;
                        }
                    } catch (ClassCastException e6) {
                        ClassCastException classCastException6 = e6;
                        Throwable th36 = th25;
                        new WrongType(classCastException6, "unfold", 2, obj18);
                        throw th36;
                    }
                } catch (ClassCastException e7) {
                    ClassCastException classCastException7 = e7;
                    Throwable th37 = th24;
                    new WrongType(classCastException7, "unfold", 1, obj16);
                    throw th37;
                }
            case 126:
                Object obj22 = objArr2[0];
                Object obj23 = obj22;
                try {
                    Procedure procedure8 = (Procedure) obj22;
                    Object obj24 = objArr2[1];
                    Object obj25 = objArr2[2];
                    int length8 = objArr2.length - 3;
                    Object[] objArr15 = new Object[length8];
                    while (true) {
                        length8--;
                        if (length8 < 0) {
                            return fold$V(procedure8, obj24, obj25, objArr15);
                        }
                        Object[] objArr16 = objArr15;
                        objArr15 = objArr16;
                        objArr16[length8] = objArr2[length8 + 3];
                    }
                } catch (ClassCastException e8) {
                    ClassCastException classCastException8 = e8;
                    Throwable th38 = th23;
                    new WrongType(classCastException8, "fold", 1, obj23);
                    throw th38;
                }
            case 127:
                Object obj26 = objArr2[0];
                Object obj27 = obj26;
                try {
                    Procedure procedure9 = (Procedure) obj26;
                    Object obj28 = objArr2[1];
                    Object obj29 = objArr2[2];
                    int length9 = objArr2.length - 3;
                    Object[] objArr17 = new Object[length9];
                    while (true) {
                        length9--;
                        if (length9 < 0) {
                            return foldRight$V(procedure9, obj28, obj29, objArr17);
                        }
                        Object[] objArr18 = objArr17;
                        objArr17 = objArr18;
                        objArr18[length9] = objArr2[length9 + 3];
                    }
                } catch (ClassCastException e9) {
                    ClassCastException classCastException9 = e9;
                    Throwable th39 = th22;
                    new WrongType(classCastException9, "fold-right", 1, obj27);
                    throw th39;
                }
            case 128:
                Object obj30 = objArr2[0];
                Object obj31 = obj30;
                try {
                    Procedure procedure10 = (Procedure) obj30;
                    Object obj32 = objArr2[1];
                    Object obj33 = objArr2[2];
                    int length10 = objArr2.length - 3;
                    Object[] objArr19 = new Object[length10];
                    while (true) {
                        length10--;
                        if (length10 < 0) {
                            return pairFoldRight$V(procedure10, obj32, obj33, objArr19);
                        }
                        Object[] objArr20 = objArr19;
                        objArr19 = objArr20;
                        objArr20[length10] = objArr2[length10 + 3];
                    }
                } catch (ClassCastException e10) {
                    ClassCastException classCastException10 = e10;
                    Throwable th40 = th21;
                    new WrongType(classCastException10, "pair-fold-right", 1, obj31);
                    throw th40;
                }
            case 129:
                Object obj34 = objArr2[0];
                Object obj35 = obj34;
                try {
                    Procedure procedure11 = (Procedure) obj34;
                    Object obj36 = objArr2[1];
                    Object obj37 = objArr2[2];
                    int length11 = objArr2.length - 3;
                    Object[] objArr21 = new Object[length11];
                    while (true) {
                        length11--;
                        if (length11 < 0) {
                            return pairFold$V(procedure11, obj36, obj37, objArr21);
                        }
                        Object[] objArr22 = objArr21;
                        objArr21 = objArr22;
                        objArr22[length11] = objArr2[length11 + 3];
                    }
                } catch (ClassCastException e11) {
                    ClassCastException classCastException11 = e11;
                    Throwable th41 = th20;
                    new WrongType(classCastException11, "pair-fold", 1, obj35);
                    throw th41;
                }
            case 132:
                Object obj38 = objArr2[0];
                Object obj39 = objArr2[1];
                int length12 = objArr2.length - 2;
                Object[] objArr23 = new Object[length12];
                while (true) {
                    length12--;
                    if (length12 < 0) {
                        return appendMap$V(obj38, obj39, objArr23);
                    }
                    Object[] objArr24 = objArr23;
                    objArr23 = objArr24;
                    objArr24[length12] = objArr2[length12 + 2];
                }
            case 133:
                Object obj40 = objArr2[0];
                Object obj41 = objArr2[1];
                int length13 = objArr2.length - 2;
                Object[] objArr25 = new Object[length13];
                while (true) {
                    length13--;
                    if (length13 < 0) {
                        return appendMap$Ex$V(obj40, obj41, objArr25);
                    }
                    Object[] objArr26 = objArr25;
                    objArr25 = objArr26;
                    objArr26[length13] = objArr2[length13 + 2];
                }
            case 134:
                Object obj42 = objArr2[0];
                Object obj43 = obj42;
                try {
                    Procedure procedure12 = (Procedure) obj42;
                    Object obj44 = objArr2[1];
                    int length14 = objArr2.length - 2;
                    Object[] objArr27 = new Object[length14];
                    while (true) {
                        length14--;
                        if (length14 < 0) {
                            return pairForEach$V(procedure12, obj44, objArr27);
                        }
                        Object[] objArr28 = objArr27;
                        objArr27 = objArr28;
                        objArr28[length14] = objArr2[length14 + 2];
                    }
                } catch (ClassCastException e12) {
                    ClassCastException classCastException12 = e12;
                    Throwable th42 = th19;
                    new WrongType(classCastException12, "pair-for-each", 1, obj43);
                    throw th42;
                }
            case 135:
                Object obj45 = objArr2[0];
                Object obj46 = obj45;
                try {
                    Procedure procedure13 = (Procedure) obj45;
                    Object obj47 = objArr2[1];
                    int length15 = objArr2.length - 2;
                    Object[] objArr29 = new Object[length15];
                    while (true) {
                        length15--;
                        if (length15 < 0) {
                            return map$Ex$V(procedure13, obj47, objArr29);
                        }
                        Object[] objArr30 = objArr29;
                        objArr29 = objArr30;
                        objArr30[length15] = objArr2[length15 + 2];
                    }
                } catch (ClassCastException e13) {
                    ClassCastException classCastException13 = e13;
                    Throwable th43 = th18;
                    new WrongType(classCastException13, "map!", 1, obj46);
                    throw th43;
                }
            case 136:
                Object obj48 = objArr2[0];
                Object obj49 = obj48;
                try {
                    Procedure procedure14 = (Procedure) obj48;
                    Object obj50 = objArr2[1];
                    int length16 = objArr2.length - 2;
                    Object[] objArr31 = new Object[length16];
                    while (true) {
                        length16--;
                        if (length16 < 0) {
                            return filterMap$V(procedure14, obj50, objArr31);
                        }
                        Object[] objArr32 = objArr31;
                        objArr31 = objArr32;
                        objArr32[length16] = objArr2[length16 + 2];
                    }
                } catch (ClassCastException e14) {
                    ClassCastException classCastException14 = e14;
                    Throwable th44 = th17;
                    new WrongType(classCastException14, "filter-map", 1, obj49);
                    throw th44;
                }
            case 166:
                Object obj51 = objArr2[0];
                Object obj52 = obj51;
                try {
                    Procedure procedure15 = (Procedure) obj51;
                    Object obj53 = objArr2[1];
                    int length17 = objArr2.length - 2;
                    Object[] objArr33 = new Object[length17];
                    while (true) {
                        length17--;
                        if (length17 < 0) {
                            return any$V(procedure15, obj53, objArr33);
                        }
                        Object[] objArr34 = objArr33;
                        objArr33 = objArr34;
                        objArr34[length17] = objArr2[length17 + 2];
                    }
                } catch (ClassCastException e15) {
                    ClassCastException classCastException15 = e15;
                    Throwable th45 = th16;
                    new WrongType(classCastException15, "any", 1, obj52);
                    throw th45;
                }
            case 167:
                Object obj54 = objArr2[0];
                Object obj55 = obj54;
                try {
                    Procedure procedure16 = (Procedure) obj54;
                    Object obj56 = objArr2[1];
                    int length18 = objArr2.length - 2;
                    Object[] objArr35 = new Object[length18];
                    while (true) {
                        length18--;
                        if (length18 < 0) {
                            return every$V(procedure16, obj56, objArr35);
                        }
                        Object[] objArr36 = objArr35;
                        objArr35 = objArr36;
                        objArr36[length18] = objArr2[length18 + 2];
                    }
                } catch (ClassCastException e16) {
                    ClassCastException classCastException16 = e16;
                    Throwable th46 = th15;
                    new WrongType(classCastException16, "every", 1, obj55);
                    throw th46;
                }
            case 168:
                Object obj57 = objArr2[0];
                Object obj58 = obj57;
                try {
                    Procedure procedure17 = (Procedure) obj57;
                    Object obj59 = objArr2[1];
                    int length19 = objArr2.length - 2;
                    Object[] objArr37 = new Object[length19];
                    while (true) {
                        length19--;
                        if (length19 < 0) {
                            return listIndex$V(procedure17, obj59, objArr37);
                        }
                        Object[] objArr38 = objArr37;
                        objArr37 = objArr38;
                        objArr38[length19] = objArr2[length19 + 2];
                    }
                } catch (ClassCastException e17) {
                    ClassCastException classCastException17 = e17;
                    Throwable th47 = th14;
                    new WrongType(classCastException17, "list-index", 1, obj58);
                    throw th47;
                }
            case 169:
                Object obj60 = objArr2[0];
                Object obj61 = obj60;
                try {
                    Procedure procedure18 = (Procedure) obj60;
                    int length20 = objArr2.length - 1;
                    Object[] objArr39 = new Object[length20];
                    while (true) {
                        length20--;
                        if (length20 < 0) {
                            return lset$Ls$Eq$V(procedure18, objArr39);
                        }
                        Object[] objArr40 = objArr39;
                        objArr39 = objArr40;
                        objArr40[length20] = objArr2[length20 + 1];
                    }
                } catch (ClassCastException e18) {
                    ClassCastException classCastException18 = e18;
                    Throwable th48 = th13;
                    new WrongType(classCastException18, "lset<=", 1, obj61);
                    throw th48;
                }
            case 170:
                Object obj62 = objArr2[0];
                Object obj63 = obj62;
                try {
                    Procedure procedure19 = (Procedure) obj62;
                    int length21 = objArr2.length - 1;
                    Object[] objArr41 = new Object[length21];
                    while (true) {
                        length21--;
                        if (length21 < 0) {
                            return lset$Eq$V(procedure19, objArr41);
                        }
                        Object[] objArr42 = objArr41;
                        objArr41 = objArr42;
                        objArr42[length21] = objArr2[length21 + 1];
                    }
                } catch (ClassCastException e19) {
                    ClassCastException classCastException19 = e19;
                    Throwable th49 = th12;
                    new WrongType(classCastException19, "lset=", 1, obj63);
                    throw th49;
                }
            case 171:
                Object obj64 = objArr2[0];
                Object obj65 = obj64;
                try {
                    Procedure procedure20 = (Procedure) obj64;
                    Object obj66 = objArr2[1];
                    int length22 = objArr2.length - 2;
                    Object[] objArr43 = new Object[length22];
                    while (true) {
                        length22--;
                        if (length22 < 0) {
                            return lsetAdjoin$V(procedure20, obj66, objArr43);
                        }
                        Object[] objArr44 = objArr43;
                        objArr43 = objArr44;
                        objArr44[length22] = objArr2[length22 + 2];
                    }
                } catch (ClassCastException e20) {
                    ClassCastException classCastException20 = e20;
                    Throwable th50 = th11;
                    new WrongType(classCastException20, "lset-adjoin", 1, obj65);
                    throw th50;
                }
            case 172:
                Object obj67 = objArr2[0];
                Object obj68 = obj67;
                try {
                    Procedure procedure21 = (Procedure) obj67;
                    int length23 = objArr2.length - 1;
                    Object[] objArr45 = new Object[length23];
                    while (true) {
                        length23--;
                        if (length23 < 0) {
                            return lsetUnion$V(procedure21, objArr45);
                        }
                        Object[] objArr46 = objArr45;
                        objArr45 = objArr46;
                        objArr46[length23] = objArr2[length23 + 1];
                    }
                } catch (ClassCastException e21) {
                    ClassCastException classCastException21 = e21;
                    Throwable th51 = th10;
                    new WrongType(classCastException21, "lset-union", 1, obj68);
                    throw th51;
                }
            case 173:
                Object obj69 = objArr2[0];
                Object obj70 = obj69;
                try {
                    Procedure procedure22 = (Procedure) obj69;
                    int length24 = objArr2.length - 1;
                    Object[] objArr47 = new Object[length24];
                    while (true) {
                        length24--;
                        if (length24 < 0) {
                            return lsetUnion$Ex$V(procedure22, objArr47);
                        }
                        Object[] objArr48 = objArr47;
                        objArr47 = objArr48;
                        objArr48[length24] = objArr2[length24 + 1];
                    }
                } catch (ClassCastException e22) {
                    ClassCastException classCastException22 = e22;
                    Throwable th52 = th9;
                    new WrongType(classCastException22, "lset-union!", 1, obj70);
                    throw th52;
                }
            case 174:
                Object obj71 = objArr2[0];
                Object obj72 = obj71;
                try {
                    Procedure procedure23 = (Procedure) obj71;
                    Object obj73 = objArr2[1];
                    int length25 = objArr2.length - 2;
                    Object[] objArr49 = new Object[length25];
                    while (true) {
                        length25--;
                        if (length25 < 0) {
                            return lsetIntersection$V(procedure23, obj73, objArr49);
                        }
                        Object[] objArr50 = objArr49;
                        objArr49 = objArr50;
                        objArr50[length25] = objArr2[length25 + 2];
                    }
                } catch (ClassCastException e23) {
                    ClassCastException classCastException23 = e23;
                    Throwable th53 = th8;
                    new WrongType(classCastException23, "lset-intersection", 1, obj72);
                    throw th53;
                }
            case 175:
                Object obj74 = objArr2[0];
                Object obj75 = obj74;
                try {
                    Procedure procedure24 = (Procedure) obj74;
                    Object obj76 = objArr2[1];
                    int length26 = objArr2.length - 2;
                    Object[] objArr51 = new Object[length26];
                    while (true) {
                        length26--;
                        if (length26 < 0) {
                            return lsetIntersection$Ex$V(procedure24, obj76, objArr51);
                        }
                        Object[] objArr52 = objArr51;
                        objArr51 = objArr52;
                        objArr52[length26] = objArr2[length26 + 2];
                    }
                } catch (ClassCastException e24) {
                    ClassCastException classCastException24 = e24;
                    Throwable th54 = th7;
                    new WrongType(classCastException24, "lset-intersection!", 1, obj75);
                    throw th54;
                }
            case 176:
                Object obj77 = objArr2[0];
                Object obj78 = obj77;
                try {
                    Procedure procedure25 = (Procedure) obj77;
                    Object obj79 = objArr2[1];
                    int length27 = objArr2.length - 2;
                    Object[] objArr53 = new Object[length27];
                    while (true) {
                        length27--;
                        if (length27 < 0) {
                            return lsetDifference$V(procedure25, obj79, objArr53);
                        }
                        Object[] objArr54 = objArr53;
                        objArr53 = objArr54;
                        objArr54[length27] = objArr2[length27 + 2];
                    }
                } catch (ClassCastException e25) {
                    ClassCastException classCastException25 = e25;
                    Throwable th55 = th6;
                    new WrongType(classCastException25, "lset-difference", 1, obj78);
                    throw th55;
                }
            case 177:
                Object obj80 = objArr2[0];
                Object obj81 = obj80;
                try {
                    Procedure procedure26 = (Procedure) obj80;
                    Object obj82 = objArr2[1];
                    int length28 = objArr2.length - 2;
                    Object[] objArr55 = new Object[length28];
                    while (true) {
                        length28--;
                        if (length28 < 0) {
                            return lsetDifference$Ex$V(procedure26, obj82, objArr55);
                        }
                        Object[] objArr56 = objArr55;
                        objArr55 = objArr56;
                        objArr56[length28] = objArr2[length28 + 2];
                    }
                } catch (ClassCastException e26) {
                    ClassCastException classCastException26 = e26;
                    Throwable th56 = th5;
                    new WrongType(classCastException26, "lset-difference!", 1, obj81);
                    throw th56;
                }
            case 178:
                Object obj83 = objArr2[0];
                Object obj84 = obj83;
                try {
                    Procedure procedure27 = (Procedure) obj83;
                    int length29 = objArr2.length - 1;
                    Object[] objArr57 = new Object[length29];
                    while (true) {
                        length29--;
                        if (length29 < 0) {
                            return lsetXor$V(procedure27, objArr57);
                        }
                        Object[] objArr58 = objArr57;
                        objArr57 = objArr58;
                        objArr58[length29] = objArr2[length29 + 1];
                    }
                } catch (ClassCastException e27) {
                    ClassCastException classCastException27 = e27;
                    Throwable th57 = th4;
                    new WrongType(classCastException27, "lset-xor", 1, obj84);
                    throw th57;
                }
            case 179:
                Object obj85 = objArr2[0];
                Object obj86 = obj85;
                try {
                    Procedure procedure28 = (Procedure) obj85;
                    int length30 = objArr2.length - 1;
                    Object[] objArr59 = new Object[length30];
                    while (true) {
                        length30--;
                        if (length30 < 0) {
                            return lsetXor$Ex$V(procedure28, objArr59);
                        }
                        Object[] objArr60 = objArr59;
                        objArr59 = objArr60;
                        objArr60[length30] = objArr2[length30 + 1];
                    }
                } catch (ClassCastException e28) {
                    ClassCastException classCastException28 = e28;
                    Throwable th58 = th3;
                    new WrongType(classCastException28, "lset-xor!", 1, obj86);
                    throw th58;
                }
            case 180:
                Object obj87 = objArr2[0];
                Object obj88 = obj87;
                try {
                    Procedure procedure29 = (Procedure) obj87;
                    Object obj89 = objArr2[1];
                    int length31 = objArr2.length - 2;
                    Object[] objArr61 = new Object[length31];
                    while (true) {
                        length31--;
                        if (length31 < 0) {
                            return lsetDiff$PlIntersection$V(procedure29, obj89, objArr61);
                        }
                        Object[] objArr62 = objArr61;
                        objArr61 = objArr62;
                        objArr62[length31] = objArr2[length31 + 2];
                    }
                } catch (ClassCastException e29) {
                    ClassCastException classCastException29 = e29;
                    Throwable th59 = th2;
                    new WrongType(classCastException29, "lset-diff+intersection", 1, obj88);
                    throw th59;
                }
            case 181:
                Object obj90 = objArr2[0];
                Object obj91 = obj90;
                try {
                    Procedure procedure30 = (Procedure) obj90;
                    Object obj92 = objArr2[1];
                    int length32 = objArr2.length - 2;
                    Object[] objArr63 = new Object[length32];
                    while (true) {
                        length32--;
                        if (length32 < 0) {
                            return lsetDiff$PlIntersection$Ex$V(procedure30, obj92, objArr63);
                        }
                        Object[] objArr64 = objArr63;
                        objArr63 = objArr64;
                        objArr64[length32] = objArr2[length32 + 2];
                    }
                } catch (ClassCastException e30) {
                    ClassCastException classCastException30 = e30;
                    Throwable th60 = th;
                    new WrongType(classCastException30, "lset-diff+intersection!", 1, obj91);
                    throw th60;
                }
            default:
                return super.applyN(moduleMethod2, objArr2);
        }
    }

    /* compiled from: srfi1.scm */
    public class frame53 extends ModuleBody {
        Procedure $Eq;
        final ModuleMethod lambda$Fn55;
        LList lists;

        public frame53() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 56, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1587");
            this.lambda$Fn55 = moduleMethod2;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            Boolean bool;
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector != 56) {
                return super.apply1(moduleMethod2, obj2);
            }
            if (lambda72(obj2)) {
                bool = Boolean.TRUE;
            } else {
                bool = Boolean.FALSE;
            }
            return bool;
        }

        /* access modifiers changed from: package-private */
        public boolean lambda72(Object elt) {
            frame54 frame54;
            new frame54();
            frame54 frame542 = frame54;
            frame542.staticLink = this;
            frame54 frame543 = frame542;
            frame543.elt = elt;
            return ((srfi1.any$V(frame543.lambda$Fn56, this.lists, new Object[0]) != Boolean.FALSE ? 1 : 0) + 1) & true;
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 56) {
                return super.match1(moduleMethod2, obj2, callContext2);
            }
            callContext2.value1 = obj2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 1;
            return 0;
        }
    }

    /* compiled from: srfi1.scm */
    public class frame54 extends ModuleBody {
        Object elt;
        final ModuleMethod lambda$Fn56;
        frame53 staticLink;

        public frame54() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 55, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi1.scm:1588");
            this.lambda$Fn56 = moduleMethod2;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector == 55) {
                return lambda73(obj2);
            }
            return super.apply1(moduleMethod2, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda73(Object lis) {
            return C1245lists.member(this.elt, lis, this.staticLink.$Eq);
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 55) {
                return super.match1(moduleMethod2, obj2, callContext2);
            }
            callContext2.value1 = obj2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 1;
            return 0;
        }
    }
}

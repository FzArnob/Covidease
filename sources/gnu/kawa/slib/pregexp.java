package gnu.kawa.slib;

import android.support.p000v4.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.text.Char;
import kawa.lib.C1245lists;
import kawa.lib.characters;
import kawa.lib.misc;
import kawa.lib.numbers;
import kawa.lib.ports;
import kawa.lib.rnrs.unicode;
import kawa.lib.strings;
import kawa.standard.Scheme;
import kawa.standard.append;
import org.shaded.apache.http.protocol.HTTP;

/* compiled from: pregexp.scm */
public class pregexp extends ModuleBody {
    public static Char $Stpregexp$Mncomment$Mnchar$St;
    public static Object $Stpregexp$Mnnul$Mnchar$Mnint$St;
    public static Object $Stpregexp$Mnreturn$Mnchar$St;
    public static Object $Stpregexp$Mnspace$Mnsensitive$Qu$St;
    public static Object $Stpregexp$Mntab$Mnchar$St;
    public static IntNum $Stpregexp$Mnversion$St;
    public static final pregexp $instance;
    static final IntNum Lit0 = IntNum.make(20050502);
    static final Char Lit1 = Char.make(59);
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit100;
    static final SimpleSymbol Lit101;
    static final SimpleSymbol Lit102;
    static final SimpleSymbol Lit103;
    static final SimpleSymbol Lit104;
    static final SimpleSymbol Lit105;
    static final PairWithPosition Lit106 = PairWithPosition.make(Lit68, PairWithPosition.make(Boolean.FALSE, PairWithPosition.make(Lit73, PairWithPosition.make(Boolean.FALSE, PairWithPosition.make(Lit14, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 2302017), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 2302014), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 2302012), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 2302009), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 2301999);
    static final SimpleSymbol Lit107;
    static final PairWithPosition Lit108;
    static final SimpleSymbol Lit109;
    static final Char Lit11;
    static final SimpleSymbol Lit110;
    static final SimpleSymbol Lit111;
    static final SimpleSymbol Lit112;
    static final Char Lit113 = Char.make(38);
    static final SimpleSymbol Lit114;
    static final SimpleSymbol Lit115;
    static final PairWithPosition Lit116;
    static final SimpleSymbol Lit117;
    static final SimpleSymbol Lit118;
    static final SimpleSymbol Lit119;
    static final SimpleSymbol Lit12;
    static final SimpleSymbol Lit120;
    static final SimpleSymbol Lit121;
    static final SimpleSymbol Lit122;
    static final SimpleSymbol Lit123;
    static final SimpleSymbol Lit124;
    static final SimpleSymbol Lit125;
    static final SimpleSymbol Lit126;
    static final SimpleSymbol Lit127;
    static final SimpleSymbol Lit128;
    static final SimpleSymbol Lit129;
    static final Char Lit13;
    static final SimpleSymbol Lit130;
    static final SimpleSymbol Lit131;
    static final SimpleSymbol Lit132;
    static final SimpleSymbol Lit133;
    static final SimpleSymbol Lit134;
    static final SimpleSymbol Lit135;
    static final SimpleSymbol Lit14;
    static final Char Lit15;
    static final IntNum Lit16 = IntNum.make(2);
    static final SimpleSymbol Lit17;
    static final Char Lit18;
    static final Char Lit19;
    static final Char Lit2 = Char.make(97);
    static final SimpleSymbol Lit20;
    static final SimpleSymbol Lit21;
    static final SimpleSymbol Lit22;
    static final SimpleSymbol Lit23;
    static final Char Lit24 = Char.make(10);
    static final Char Lit25 = Char.make(98);
    static final SimpleSymbol Lit26;
    static final Char Lit27 = Char.make(66);
    static final SimpleSymbol Lit28;
    static final Char Lit29 = Char.make(100);
    static final Char Lit3 = Char.make(32);
    static final SimpleSymbol Lit30;
    static final Char Lit31 = Char.make(68);
    static final PairWithPosition Lit32;
    static final Char Lit33 = Char.make(110);
    static final Char Lit34 = Char.make(114);
    static final Char Lit35 = Char.make(115);
    static final SimpleSymbol Lit36;
    static final Char Lit37 = Char.make(83);
    static final PairWithPosition Lit38;
    static final Char Lit39 = Char.make(116);
    static final SimpleSymbol Lit4;
    static final Char Lit40 = Char.make(119);
    static final SimpleSymbol Lit41;
    static final Char Lit42 = Char.make(87);
    static final PairWithPosition Lit43;
    static final Char Lit44 = Char.make(58);
    static final SimpleSymbol Lit45;
    static final Char Lit46;
    static final Char Lit47;
    static final Char Lit48 = Char.make(61);
    static final PairWithPosition Lit49 = PairWithPosition.make(Lit103, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 851996);
    static final SimpleSymbol Lit5;
    static final Char Lit50 = Char.make(33);
    static final PairWithPosition Lit51 = PairWithPosition.make(Lit104, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 856092);
    static final Char Lit52 = Char.make(62);
    static final PairWithPosition Lit53 = PairWithPosition.make(Lit109, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 860188);
    static final Char Lit54 = Char.make(60);
    static final PairWithPosition Lit55 = PairWithPosition.make(Lit105, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 872479);
    static final PairWithPosition Lit56 = PairWithPosition.make(Lit107, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 876575);
    static final SimpleSymbol Lit57;
    static final Char Lit58 = Char.make(45);
    static final Char Lit59 = Char.make(105);
    static final Char Lit6;
    static final SimpleSymbol Lit60;
    static final SimpleSymbol Lit61;
    static final Char Lit62 = Char.make(120);
    static final PairWithPosition Lit63 = PairWithPosition.make(Lit100, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 942102);
    static final SimpleSymbol Lit64;
    static final Char Lit65;
    static final Char Lit66;
    static final Char Lit67;
    static final SimpleSymbol Lit68;
    static final SimpleSymbol Lit69;
    static final Char Lit7;
    static final SimpleSymbol Lit70;
    static final SimpleSymbol Lit71;
    static final SimpleSymbol Lit72;
    static final IntNum Lit73;
    static final SimpleSymbol Lit74;
    static final SimpleSymbol Lit75;
    static final SimpleSymbol Lit76;
    static final Char Lit77 = Char.make(44);
    static final Char Lit78;
    static final SimpleSymbol Lit79;
    static final IntNum Lit8 = IntNum.make(1);
    static final SimpleSymbol Lit80;
    static final SimpleSymbol Lit81;
    static final SimpleSymbol Lit82;
    static final SimpleSymbol Lit83;
    static final Char Lit84 = Char.make(95);
    static final SimpleSymbol Lit85;
    static final SimpleSymbol Lit86;
    static final SimpleSymbol Lit87;
    static final SimpleSymbol Lit88;
    static final SimpleSymbol Lit89;
    static final Char Lit9;
    static final SimpleSymbol Lit90;
    static final SimpleSymbol Lit91;
    static final SimpleSymbol Lit92;
    static final SimpleSymbol Lit93;
    static final SimpleSymbol Lit94;
    static final SimpleSymbol Lit95;
    static final Char Lit96 = Char.make(99);
    static final Char Lit97 = Char.make(101);
    static final Char Lit98 = Char.make(102);
    static final SimpleSymbol Lit99;
    static final ModuleMethod lambda$Fn1;
    static final ModuleMethod lambda$Fn10;
    static final ModuleMethod lambda$Fn6;
    static final ModuleMethod lambda$Fn7;
    static final ModuleMethod lambda$Fn8;
    static final ModuleMethod lambda$Fn9;
    public static final ModuleMethod pregexp;
    public static final ModuleMethod pregexp$Mnat$Mnword$Mnboundary$Qu;
    public static final ModuleMethod pregexp$Mnchar$Mnword$Qu;
    public static final ModuleMethod pregexp$Mncheck$Mnif$Mnin$Mnchar$Mnclass$Qu;
    public static final ModuleMethod pregexp$Mnerror;
    public static final ModuleMethod pregexp$Mninvert$Mnchar$Mnlist;
    public static final ModuleMethod pregexp$Mnlist$Mnref;
    public static final ModuleMethod pregexp$Mnmake$Mnbackref$Mnlist;
    public static final ModuleMethod pregexp$Mnmatch;
    public static final ModuleMethod pregexp$Mnmatch$Mnpositions;
    public static final ModuleMethod pregexp$Mnmatch$Mnpositions$Mnaux;
    public static final ModuleMethod pregexp$Mnquote;
    public static final ModuleMethod pregexp$Mnread$Mnbranch;
    public static final ModuleMethod pregexp$Mnread$Mnchar$Mnlist;
    public static final ModuleMethod pregexp$Mnread$Mncluster$Mntype;
    public static final ModuleMethod pregexp$Mnread$Mnescaped$Mnchar;
    public static final ModuleMethod pregexp$Mnread$Mnescaped$Mnnumber;
    public static final ModuleMethod pregexp$Mnread$Mnnums;
    public static final ModuleMethod pregexp$Mnread$Mnpattern;
    public static final ModuleMethod pregexp$Mnread$Mnpiece;
    public static final ModuleMethod pregexp$Mnread$Mnposix$Mnchar$Mnclass;
    public static final ModuleMethod pregexp$Mnread$Mnsubpattern;
    public static final ModuleMethod pregexp$Mnreplace;
    public static final ModuleMethod pregexp$Mnreplace$Mnaux;
    public static final ModuleMethod pregexp$Mnreplace$St;
    public static final ModuleMethod pregexp$Mnreverse$Ex;
    public static final ModuleMethod pregexp$Mnsplit;
    public static final ModuleMethod pregexp$Mnstring$Mnmatch;
    public static final ModuleMethod pregexp$Mnwrap$Mnquantifier$Mnif$Mnany;

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
        pregexp pregexp2;
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
        new SimpleSymbol("pregexp-quote");
        Lit135 = (SimpleSymbol) simpleSymbol.readResolve();
        new SimpleSymbol("pregexp-replace*");
        Lit134 = (SimpleSymbol) simpleSymbol2.readResolve();
        new SimpleSymbol("pregexp-replace");
        Lit133 = (SimpleSymbol) simpleSymbol3.readResolve();
        new SimpleSymbol("pregexp-split");
        Lit132 = (SimpleSymbol) simpleSymbol4.readResolve();
        new SimpleSymbol("pregexp-match");
        Lit131 = (SimpleSymbol) simpleSymbol5.readResolve();
        new SimpleSymbol("pregexp");
        Lit130 = (SimpleSymbol) simpleSymbol6.readResolve();
        new SimpleSymbol("pregexp-replace-aux");
        Lit129 = (SimpleSymbol) simpleSymbol7.readResolve();
        new SimpleSymbol("pregexp-make-backref-list");
        Lit128 = (SimpleSymbol) simpleSymbol8.readResolve();
        new SimpleSymbol("pregexp-list-ref");
        Lit127 = (SimpleSymbol) simpleSymbol9.readResolve();
        new SimpleSymbol("pregexp-at-word-boundary?");
        Lit126 = (SimpleSymbol) simpleSymbol10.readResolve();
        new SimpleSymbol("pregexp-char-word?");
        Lit125 = (SimpleSymbol) simpleSymbol11.readResolve();
        new SimpleSymbol("pregexp-string-match");
        Lit124 = (SimpleSymbol) simpleSymbol12.readResolve();
        new SimpleSymbol("pregexp-invert-char-list");
        Lit123 = (SimpleSymbol) simpleSymbol13.readResolve();
        new SimpleSymbol("pregexp-read-escaped-char");
        Lit122 = (SimpleSymbol) simpleSymbol14.readResolve();
        new SimpleSymbol("pregexp-read-escaped-number");
        Lit121 = (SimpleSymbol) simpleSymbol15.readResolve();
        new SimpleSymbol("pregexp-read-branch");
        Lit120 = (SimpleSymbol) simpleSymbol16.readResolve();
        new SimpleSymbol("pregexp-read-pattern");
        Lit119 = (SimpleSymbol) simpleSymbol17.readResolve();
        new SimpleSymbol("pregexp-error");
        Lit118 = (SimpleSymbol) simpleSymbol18.readResolve();
        new SimpleSymbol("pregexp-reverse!");
        Lit117 = (SimpleSymbol) simpleSymbol19.readResolve();
        Char make = Char.make(92);
        Char charR = make;
        Lit19 = make;
        Char make2 = Char.make(46);
        Char charR2 = make2;
        Lit13 = make2;
        Char make3 = Char.make(63);
        Char charR3 = make3;
        Lit47 = make3;
        Char make4 = Char.make(42);
        Char charR4 = make4;
        Lit65 = make4;
        Char make5 = Char.make(43);
        Char charR5 = make5;
        Lit66 = make5;
        Char make6 = Char.make(124);
        Char charR6 = make6;
        Lit7 = make6;
        Char make7 = Char.make(94);
        Char charR7 = make7;
        Lit9 = make7;
        Char make8 = Char.make(36);
        Char charR8 = make8;
        Lit11 = make8;
        Char make9 = Char.make(91);
        Char charR9 = make9;
        Lit15 = make9;
        Char make10 = Char.make(93);
        Char charR10 = make10;
        Lit46 = make10;
        Char make11 = Char.make(123);
        Char charR11 = make11;
        Lit67 = make11;
        Char make12 = Char.make(125);
        Char charR12 = make12;
        Lit78 = make12;
        Char make13 = Char.make(40);
        Char charR13 = make13;
        Lit18 = make13;
        Char make14 = Char.make(41);
        Lit6 = make14;
        Lit116 = PairWithPosition.make(charR, PairWithPosition.make(charR2, PairWithPosition.make(charR3, PairWithPosition.make(charR4, PairWithPosition.make(charR5, PairWithPosition.make(charR6, PairWithPosition.make(charR7, PairWithPosition.make(charR8, PairWithPosition.make(charR9, PairWithPosition.make(charR10, PairWithPosition.make(charR11, PairWithPosition.make(charR12, PairWithPosition.make(charR13, PairWithPosition.make(make14, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 3153977), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 3153973), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 3153969), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 3153965), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 3153961), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 3153957), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 3149885), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 3149881), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 3149877), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 3149873), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 3149869), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 3149865), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 3149861), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 3149856);
        new SimpleSymbol("pattern-must-be-compiled-or-string-regexp");
        Lit115 = (SimpleSymbol) simpleSymbol20.readResolve();
        new SimpleSymbol("pregexp-match-positions");
        Lit114 = (SimpleSymbol) simpleSymbol21.readResolve();
        new SimpleSymbol(HTTP.IDENTITY_CODING);
        Lit112 = (SimpleSymbol) simpleSymbol22.readResolve();
        new SimpleSymbol("fk");
        Lit111 = (SimpleSymbol) simpleSymbol23.readResolve();
        new SimpleSymbol("greedy-quantifier-operand-could-be-empty");
        Lit110 = (SimpleSymbol) simpleSymbol24.readResolve();
        new SimpleSymbol(":no-backtrack");
        Lit109 = (SimpleSymbol) simpleSymbol25.readResolve();
        new SimpleSymbol(":between");
        SimpleSymbol simpleSymbol78 = (SimpleSymbol) simpleSymbol26.readResolve();
        SimpleSymbol simpleSymbol79 = simpleSymbol78;
        Lit68 = simpleSymbol78;
        Boolean bool = Boolean.FALSE;
        IntNum make15 = IntNum.make(0);
        IntNum intNum = make15;
        Lit73 = make15;
        Boolean bool2 = Boolean.FALSE;
        new SimpleSymbol(":any");
        SimpleSymbol simpleSymbol80 = (SimpleSymbol) simpleSymbol27.readResolve();
        Lit14 = simpleSymbol80;
        Lit108 = PairWithPosition.make(simpleSymbol79, PairWithPosition.make(bool, PairWithPosition.make(intNum, PairWithPosition.make(bool2, PairWithPosition.make(simpleSymbol80, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 2338881), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 2338878), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 2338876), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 2338873), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 2338863);
        new SimpleSymbol(":neg-lookbehind");
        Lit107 = (SimpleSymbol) simpleSymbol28.readResolve();
        new SimpleSymbol(":lookbehind");
        Lit105 = (SimpleSymbol) simpleSymbol29.readResolve();
        new SimpleSymbol(":neg-lookahead");
        Lit104 = (SimpleSymbol) simpleSymbol30.readResolve();
        new SimpleSymbol(":lookahead");
        Lit103 = (SimpleSymbol) simpleSymbol31.readResolve();
        new SimpleSymbol("non-existent-backref");
        Lit102 = (SimpleSymbol) simpleSymbol32.readResolve();
        new SimpleSymbol("pregexp-match-positions-aux");
        Lit101 = (SimpleSymbol) simpleSymbol33.readResolve();
        new SimpleSymbol(":sub");
        Lit100 = (SimpleSymbol) simpleSymbol34.readResolve();
        new SimpleSymbol("pregexp-check-if-in-char-class?");
        Lit99 = (SimpleSymbol) simpleSymbol35.readResolve();
        new SimpleSymbol(":xdigit");
        Lit95 = (SimpleSymbol) simpleSymbol36.readResolve();
        new SimpleSymbol(":upper");
        Lit94 = (SimpleSymbol) simpleSymbol37.readResolve();
        new SimpleSymbol(":punct");
        Lit93 = (SimpleSymbol) simpleSymbol38.readResolve();
        new SimpleSymbol(":print");
        Lit92 = (SimpleSymbol) simpleSymbol39.readResolve();
        new SimpleSymbol(":lower");
        Lit91 = (SimpleSymbol) simpleSymbol40.readResolve();
        new SimpleSymbol(":graph");
        Lit90 = (SimpleSymbol) simpleSymbol41.readResolve();
        new SimpleSymbol(":cntrl");
        Lit89 = (SimpleSymbol) simpleSymbol42.readResolve();
        new SimpleSymbol(":blank");
        Lit88 = (SimpleSymbol) simpleSymbol43.readResolve();
        new SimpleSymbol(":ascii");
        Lit87 = (SimpleSymbol) simpleSymbol44.readResolve();
        new SimpleSymbol(":alpha");
        Lit86 = (SimpleSymbol) simpleSymbol45.readResolve();
        new SimpleSymbol(":alnum");
        Lit85 = (SimpleSymbol) simpleSymbol46.readResolve();
        new SimpleSymbol(":char-range");
        Lit83 = (SimpleSymbol) simpleSymbol47.readResolve();
        new SimpleSymbol(":one-of-chars");
        Lit82 = (SimpleSymbol) simpleSymbol48.readResolve();
        new SimpleSymbol("character-class-ended-too-soon");
        Lit81 = (SimpleSymbol) simpleSymbol49.readResolve();
        new SimpleSymbol("pregexp-read-char-list");
        Lit80 = (SimpleSymbol) simpleSymbol50.readResolve();
        new SimpleSymbol(":none-of-chars");
        Lit79 = (SimpleSymbol) simpleSymbol51.readResolve();
        new SimpleSymbol("pregexp-read-nums");
        Lit76 = (SimpleSymbol) simpleSymbol52.readResolve();
        new SimpleSymbol("left-brace-must-be-followed-by-number");
        Lit75 = (SimpleSymbol) simpleSymbol53.readResolve();
        new SimpleSymbol("pregexp-wrap-quantifier-if-any");
        Lit74 = (SimpleSymbol) simpleSymbol54.readResolve();
        new SimpleSymbol("next-i");
        Lit72 = (SimpleSymbol) simpleSymbol55.readResolve();
        new SimpleSymbol("at-most");
        Lit71 = (SimpleSymbol) simpleSymbol56.readResolve();
        new SimpleSymbol("at-least");
        Lit70 = (SimpleSymbol) simpleSymbol57.readResolve();
        new SimpleSymbol("minimal?");
        Lit69 = (SimpleSymbol) simpleSymbol58.readResolve();
        new SimpleSymbol("pregexp-read-subpattern");
        Lit64 = (SimpleSymbol) simpleSymbol59.readResolve();
        new SimpleSymbol(":case-insensitive");
        Lit61 = (SimpleSymbol) simpleSymbol60.readResolve();
        new SimpleSymbol(":case-sensitive");
        Lit60 = (SimpleSymbol) simpleSymbol61.readResolve();
        new SimpleSymbol("pregexp-read-cluster-type");
        Lit57 = (SimpleSymbol) simpleSymbol62.readResolve();
        new SimpleSymbol("pregexp-read-posix-char-class");
        Lit45 = (SimpleSymbol) simpleSymbol63.readResolve();
        new SimpleSymbol(":neg-char");
        SimpleSymbol simpleSymbol81 = (SimpleSymbol) simpleSymbol64.readResolve();
        SimpleSymbol simpleSymbol82 = simpleSymbol81;
        Lit17 = simpleSymbol81;
        new SimpleSymbol(":word");
        SimpleSymbol simpleSymbol83 = (SimpleSymbol) simpleSymbol65.readResolve();
        Lit41 = simpleSymbol83;
        Lit43 = PairWithPosition.make(simpleSymbol82, PairWithPosition.make(simpleSymbol83, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 696359), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 696348);
        SimpleSymbol simpleSymbol84 = Lit17;
        new SimpleSymbol(":space");
        SimpleSymbol simpleSymbol85 = (SimpleSymbol) simpleSymbol66.readResolve();
        Lit36 = simpleSymbol85;
        Lit38 = PairWithPosition.make(simpleSymbol84, PairWithPosition.make(simpleSymbol85, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 684071), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 684060);
        SimpleSymbol simpleSymbol86 = Lit17;
        new SimpleSymbol(":digit");
        SimpleSymbol simpleSymbol87 = (SimpleSymbol) simpleSymbol67.readResolve();
        Lit30 = simpleSymbol87;
        Lit32 = PairWithPosition.make(simpleSymbol86, PairWithPosition.make(simpleSymbol87, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 667687), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm", 667676);
        new SimpleSymbol(":not-wbdry");
        Lit28 = (SimpleSymbol) simpleSymbol68.readResolve();
        new SimpleSymbol(":wbdry");
        Lit26 = (SimpleSymbol) simpleSymbol69.readResolve();
        new SimpleSymbol(":empty");
        Lit23 = (SimpleSymbol) simpleSymbol70.readResolve();
        new SimpleSymbol("backslash");
        Lit22 = (SimpleSymbol) simpleSymbol71.readResolve();
        new SimpleSymbol("pregexp-read-piece");
        Lit21 = (SimpleSymbol) simpleSymbol72.readResolve();
        new SimpleSymbol(":backref");
        Lit20 = (SimpleSymbol) simpleSymbol73.readResolve();
        new SimpleSymbol(":eos");
        Lit12 = (SimpleSymbol) simpleSymbol74.readResolve();
        new SimpleSymbol(":bos");
        Lit10 = (SimpleSymbol) simpleSymbol75.readResolve();
        new SimpleSymbol(":seq");
        Lit5 = (SimpleSymbol) simpleSymbol76.readResolve();
        new SimpleSymbol(":or");
        Lit4 = (SimpleSymbol) simpleSymbol77.readResolve();
        new pregexp();
        $instance = pregexp2;
        pregexp pregexp3 = $instance;
        new ModuleMethod(pregexp3, 16, Lit117, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ModuleMethod moduleMethod36 = moduleMethod;
        moduleMethod36.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:47");
        pregexp$Mnreverse$Ex = moduleMethod36;
        new ModuleMethod(pregexp3, 17, Lit118, -4096);
        ModuleMethod moduleMethod37 = moduleMethod2;
        moduleMethod37.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:57");
        pregexp$Mnerror = moduleMethod37;
        new ModuleMethod(pregexp3, 18, Lit119, 12291);
        ModuleMethod moduleMethod38 = moduleMethod3;
        moduleMethod38.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:65");
        pregexp$Mnread$Mnpattern = moduleMethod38;
        new ModuleMethod(pregexp3, 19, Lit120, 12291);
        ModuleMethod moduleMethod39 = moduleMethod4;
        moduleMethod39.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:79");
        pregexp$Mnread$Mnbranch = moduleMethod39;
        new ModuleMethod(pregexp3, 20, Lit21, 12291);
        ModuleMethod moduleMethod40 = moduleMethod5;
        moduleMethod40.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:91");
        pregexp$Mnread$Mnpiece = moduleMethod40;
        new ModuleMethod(pregexp3, 21, Lit121, 12291);
        ModuleMethod moduleMethod41 = moduleMethod6;
        moduleMethod41.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:138");
        pregexp$Mnread$Mnescaped$Mnnumber = moduleMethod41;
        new ModuleMethod(pregexp3, 22, Lit122, 12291);
        ModuleMethod moduleMethod42 = moduleMethod7;
        moduleMethod42.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:155");
        pregexp$Mnread$Mnescaped$Mnchar = moduleMethod42;
        new ModuleMethod(pregexp3, 23, Lit45, 12291);
        ModuleMethod moduleMethod43 = moduleMethod8;
        moduleMethod43.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:174");
        pregexp$Mnread$Mnposix$Mnchar$Mnclass = moduleMethod43;
        new ModuleMethod(pregexp3, 24, Lit57, 12291);
        ModuleMethod moduleMethod44 = moduleMethod9;
        moduleMethod44.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:200");
        pregexp$Mnread$Mncluster$Mntype = moduleMethod44;
        new ModuleMethod(pregexp3, 25, Lit64, 12291);
        ModuleMethod moduleMethod45 = moduleMethod10;
        moduleMethod45.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:233");
        pregexp$Mnread$Mnsubpattern = moduleMethod45;
        new ModuleMethod(pregexp3, 26, Lit74, 12291);
        ModuleMethod moduleMethod46 = moduleMethod11;
        moduleMethod46.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:254");
        pregexp$Mnwrap$Mnquantifier$Mnif$Mnany = moduleMethod46;
        new ModuleMethod(pregexp3, 27, Lit76, 12291);
        ModuleMethod moduleMethod47 = moduleMethod12;
        moduleMethod47.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:300");
        pregexp$Mnread$Mnnums = moduleMethod47;
        new ModuleMethod(pregexp3, 28, Lit123, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ModuleMethod moduleMethod48 = moduleMethod13;
        moduleMethod48.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:323");
        pregexp$Mninvert$Mnchar$Mnlist = moduleMethod48;
        new ModuleMethod(pregexp3, 29, Lit80, 12291);
        ModuleMethod moduleMethod49 = moduleMethod14;
        moduleMethod49.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:330");
        pregexp$Mnread$Mnchar$Mnlist = moduleMethod49;
        new ModuleMethod(pregexp3, 30, Lit124, 24582);
        ModuleMethod moduleMethod50 = moduleMethod15;
        moduleMethod50.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:368");
        pregexp$Mnstring$Mnmatch = moduleMethod50;
        new ModuleMethod(pregexp3, 31, Lit125, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ModuleMethod moduleMethod51 = moduleMethod16;
        moduleMethod51.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:379");
        pregexp$Mnchar$Mnword$Qu = moduleMethod51;
        new ModuleMethod(pregexp3, 32, Lit126, 12291);
        ModuleMethod moduleMethod52 = moduleMethod17;
        moduleMethod52.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:387");
        pregexp$Mnat$Mnword$Mnboundary$Qu = moduleMethod52;
        new ModuleMethod(pregexp3, 33, Lit99, 8194);
        ModuleMethod moduleMethod53 = moduleMethod18;
        moduleMethod53.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:399");
        pregexp$Mncheck$Mnif$Mnin$Mnchar$Mnclass$Qu = moduleMethod53;
        new ModuleMethod(pregexp3, 34, Lit127, 8194);
        ModuleMethod moduleMethod54 = moduleMethod19;
        moduleMethod54.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:429");
        pregexp$Mnlist$Mnref = moduleMethod54;
        new ModuleMethod(pregexp3, 35, Lit128, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ModuleMethod moduleMethod55 = moduleMethod20;
        moduleMethod55.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:448");
        pregexp$Mnmake$Mnbackref$Mnlist = moduleMethod55;
        new ModuleMethod(pregexp3, 36, (Object) null, 0);
        ModuleMethod moduleMethod56 = moduleMethod21;
        moduleMethod56.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:463");
        lambda$Fn1 = moduleMethod56;
        new ModuleMethod(pregexp3, 37, (Object) null, 0);
        ModuleMethod moduleMethod57 = moduleMethod22;
        moduleMethod57.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:551");
        lambda$Fn6 = moduleMethod57;
        new ModuleMethod(pregexp3, 38, (Object) null, 0);
        ModuleMethod moduleMethod58 = moduleMethod23;
        moduleMethod58.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:556");
        lambda$Fn7 = moduleMethod58;
        new ModuleMethod(pregexp3, 39, (Object) null, 0);
        ModuleMethod moduleMethod59 = moduleMethod24;
        moduleMethod59.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:564");
        lambda$Fn8 = moduleMethod59;
        new ModuleMethod(pregexp3, 40, (Object) null, 0);
        ModuleMethod moduleMethod60 = moduleMethod25;
        moduleMethod60.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:573");
        lambda$Fn9 = moduleMethod60;
        new ModuleMethod(pregexp3, 41, (Object) null, 0);
        ModuleMethod moduleMethod61 = moduleMethod26;
        moduleMethod61.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:578");
        lambda$Fn10 = moduleMethod61;
        new ModuleMethod(pregexp3, 42, Lit101, 24582);
        ModuleMethod moduleMethod62 = moduleMethod27;
        moduleMethod62.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:459");
        pregexp$Mnmatch$Mnpositions$Mnaux = moduleMethod62;
        new ModuleMethod(pregexp3, 43, Lit129, 16388);
        ModuleMethod moduleMethod63 = moduleMethod28;
        moduleMethod63.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:639");
        pregexp$Mnreplace$Mnaux = moduleMethod63;
        new ModuleMethod(pregexp3, 44, Lit130, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ModuleMethod moduleMethod64 = moduleMethod29;
        moduleMethod64.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:665");
        pregexp = moduleMethod64;
        new ModuleMethod(pregexp3, 45, Lit114, -4094);
        ModuleMethod moduleMethod65 = moduleMethod30;
        moduleMethod65.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:670");
        pregexp$Mnmatch$Mnpositions = moduleMethod65;
        new ModuleMethod(pregexp3, 46, Lit131, -4094);
        ModuleMethod moduleMethod66 = moduleMethod31;
        moduleMethod66.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:690");
        pregexp$Mnmatch = moduleMethod66;
        new ModuleMethod(pregexp3, 47, Lit132, 8194);
        ModuleMethod moduleMethod67 = moduleMethod32;
        moduleMethod67.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:700");
        pregexp$Mnsplit = moduleMethod67;
        new ModuleMethod(pregexp3, 48, Lit133, 12291);
        ModuleMethod moduleMethod68 = moduleMethod33;
        moduleMethod68.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:723");
        pregexp$Mnreplace = moduleMethod68;
        new ModuleMethod(pregexp3, 49, Lit134, 12291);
        ModuleMethod moduleMethod69 = moduleMethod34;
        moduleMethod69.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:736");
        pregexp$Mnreplace$St = moduleMethod69;
        new ModuleMethod(pregexp3, 50, Lit135, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ModuleMethod moduleMethod70 = moduleMethod35;
        moduleMethod70.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:764");
        pregexp$Mnquote = moduleMethod70;
        $instance.run();
    }

    public pregexp() {
        ModuleInfo.register(this);
    }

    public final void run(CallContext $ctx) {
        Consumer consumer = $ctx.consumer;
        $Stpregexp$Mnversion$St = Lit0;
        $Stpregexp$Mncomment$Mnchar$St = Lit1;
        $Stpregexp$Mnnul$Mnchar$Mnint$St = Integer.valueOf(characters.char$To$Integer(Lit2) - 97);
        $Stpregexp$Mnreturn$Mnchar$St = characters.integer$To$Char(13 + ((Number) $Stpregexp$Mnnul$Mnchar$Mnint$St).intValue());
        $Stpregexp$Mntab$Mnchar$St = characters.integer$To$Char(9 + ((Number) $Stpregexp$Mnnul$Mnchar$Mnint$St).intValue());
        $Stpregexp$Mnspace$Mnsensitive$Qu$St = Boolean.TRUE;
    }

    public static Object pregexpReverse$Ex(Object s) {
        Throwable th;
        Object obj = s;
        Object obj2 = LList.Empty;
        while (true) {
            Object obj3 = obj2;
            Object s2 = obj;
            if (C1245lists.isNull(s2)) {
                return obj3;
            }
            Object d = C1245lists.cdr.apply1(s2);
            Object obj4 = s2;
            Object obj5 = obj4;
            try {
                C1245lists.setCdr$Ex((Pair) obj4, obj3);
                obj = d;
                obj2 = s2;
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "set-cdr!", 1, obj5);
                throw th2;
            }
        }
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 16:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 28:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 31:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 35:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 44:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 50:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            default:
                return super.match1(moduleMethod2, obj2, callContext2);
        }
    }

    public static Object pregexpError$V(Object[] argsArray) {
        Throwable th;
        LList whatever = LList.makeList(argsArray, 0);
        LList lList = whatever;
        ports.display("Error:");
        Object obj = whatever;
        while (true) {
            Object obj2 = obj;
            if (obj2 == LList.Empty) {
                ports.newline();
                return misc.error$V("pregexp-error", new Object[0]);
            }
            Object obj3 = obj2;
            Object obj4 = obj3;
            try {
                Pair arg0 = (Pair) obj3;
                Object x = arg0.getCar();
                ports.display(Lit3);
                ports.write(x);
                obj = arg0.getCdr();
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "arg0", -2, obj4);
                throw th2;
            }
        }
    }

    public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object[] objArr2 = objArr;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 17:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 30:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 42:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 45:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 46:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            default:
                return super.matchN(moduleMethod2, objArr2, callContext2);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0099  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00bf  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object pregexpReadPattern(java.lang.Object r15, java.lang.Object r16, java.lang.Object r17) {
        /*
            r0 = r15
            r1 = r16
            r2 = r17
            gnu.kawa.functions.NumberCompare r7 = kawa.standard.Scheme.numGEq
            r8 = r1
            r9 = r2
            java.lang.Object r7 = r7.apply2(r8, r9)
            java.lang.Boolean r8 = java.lang.Boolean.FALSE
            if (r7 == r8) goto L_0x0024
            gnu.mapping.SimpleSymbol r7 = Lit4
            gnu.mapping.SimpleSymbol r8 = Lit5
            gnu.lists.Pair r8 = gnu.lists.LList.list1(r8)
            gnu.lists.Pair r7 = gnu.lists.LList.list2(r7, r8)
            r8 = r1
            gnu.lists.Pair r7 = gnu.lists.LList.list2(r7, r8)
        L_0x0022:
            r0 = r7
            return r0
        L_0x0024:
            gnu.lists.LList r7 = gnu.lists.LList.Empty
            r8 = r1
            r4 = r8
            r3 = r7
        L_0x0029:
            gnu.kawa.functions.NumberCompare r7 = kawa.standard.Scheme.numGEq
            r8 = r4
            r9 = r2
            java.lang.Object r7 = r7.apply2(r8, r9)
            r13 = r7
            r7 = r13
            r8 = r13
            r6 = r8
            java.lang.Boolean r7 = (java.lang.Boolean) r7     // Catch:{ ClassCastException -> 0x00c1 }
            boolean r7 = r7.booleanValue()     // Catch:{ ClassCastException -> 0x00c1 }
            r5 = r7
            r7 = r5
            if (r7 == 0) goto L_0x0053
            r7 = r5
            if (r7 == 0) goto L_0x0076
        L_0x0042:
            gnu.mapping.SimpleSymbol r7 = Lit4
            r8 = r3
            java.lang.Object r8 = pregexpReverse$Ex(r8)
            gnu.lists.Pair r7 = kawa.lib.C1245lists.cons(r7, r8)
            r8 = r4
            gnu.lists.Pair r7 = gnu.lists.LList.list2(r7, r8)
            goto L_0x0022
        L_0x0053:
            r7 = r0
            r13 = r7
            r7 = r13
            r8 = r13
            r6 = r8
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7     // Catch:{ ClassCastException -> 0x00d6 }
            r8 = r4
            r13 = r8
            r8 = r13
            r9 = r13
            r6 = r9
            java.lang.Number r8 = (java.lang.Number) r8     // Catch:{ ClassCastException -> 0x00eb }
            int r8 = r8.intValue()     // Catch:{ ClassCastException -> 0x00eb }
            char r7 = kawa.lib.strings.stringRef(r7, r8)
            gnu.text.Char r7 = gnu.text.Char.make(r7)
            gnu.text.Char r8 = Lit6
            boolean r7 = kawa.lib.characters.isChar$Eq(r7, r8)
            if (r7 == 0) goto L_0x0076
            goto L_0x0042
        L_0x0076:
            r7 = r0
            r8 = r0
            r13 = r8
            r8 = r13
            r9 = r13
            r6 = r9
            java.lang.CharSequence r8 = (java.lang.CharSequence) r8     // Catch:{ ClassCastException -> 0x0100 }
            r9 = r4
            r13 = r9
            r9 = r13
            r10 = r13
            r6 = r10
            java.lang.Number r9 = (java.lang.Number) r9     // Catch:{ ClassCastException -> 0x0115 }
            int r9 = r9.intValue()     // Catch:{ ClassCastException -> 0x0115 }
            char r8 = kawa.lib.strings.stringRef(r8, r9)
            gnu.text.Char r8 = gnu.text.Char.make(r8)
            gnu.text.Char r9 = Lit7
            boolean r8 = kawa.lib.characters.isChar$Eq(r8, r9)
            if (r8 == 0) goto L_0x00bf
            gnu.kawa.functions.AddOp r8 = gnu.kawa.functions.AddOp.$Pl
            r9 = r4
            gnu.math.IntNum r10 = Lit8
            java.lang.Object r8 = r8.apply2(r9, r10)
        L_0x00a2:
            r9 = r2
            java.lang.Object r7 = pregexpReadBranch(r7, r8, r9)
            r5 = r7
            gnu.expr.GenericProc r7 = kawa.lib.C1245lists.car
            r8 = r5
            java.lang.Object r7 = r7.apply1(r8)
            r8 = r3
            gnu.lists.Pair r7 = kawa.lib.C1245lists.cons(r7, r8)
            gnu.expr.GenericProc r8 = kawa.lib.C1245lists.cadr
            r9 = r5
            java.lang.Object r8 = r8.apply1(r9)
            r4 = r8
            r3 = r7
            goto L_0x0029
        L_0x00bf:
            r8 = r4
            goto L_0x00a2
        L_0x00c1:
            r7 = move-exception
            gnu.mapping.WrongType r8 = new gnu.mapping.WrongType
            r13 = r7
            r14 = r8
            r7 = r14
            r8 = r13
            r9 = r14
            r13 = r8
            r14 = r9
            r8 = r14
            r9 = r13
            java.lang.String r10 = "x"
            r11 = -2
            r12 = r6
            r8.<init>((java.lang.ClassCastException) r9, (java.lang.String) r10, (int) r11, (java.lang.Object) r12)
            throw r7
        L_0x00d6:
            r7 = move-exception
            gnu.mapping.WrongType r8 = new gnu.mapping.WrongType
            r13 = r7
            r14 = r8
            r7 = r14
            r8 = r13
            r9 = r14
            r13 = r8
            r14 = r9
            r8 = r14
            r9 = r13
            java.lang.String r10 = "string-ref"
            r11 = 1
            r12 = r6
            r8.<init>((java.lang.ClassCastException) r9, (java.lang.String) r10, (int) r11, (java.lang.Object) r12)
            throw r7
        L_0x00eb:
            r7 = move-exception
            gnu.mapping.WrongType r8 = new gnu.mapping.WrongType
            r13 = r7
            r14 = r8
            r7 = r14
            r8 = r13
            r9 = r14
            r13 = r8
            r14 = r9
            r8 = r14
            r9 = r13
            java.lang.String r10 = "string-ref"
            r11 = 2
            r12 = r6
            r8.<init>((java.lang.ClassCastException) r9, (java.lang.String) r10, (int) r11, (java.lang.Object) r12)
            throw r7
        L_0x0100:
            r7 = move-exception
            gnu.mapping.WrongType r8 = new gnu.mapping.WrongType
            r13 = r7
            r14 = r8
            r7 = r14
            r8 = r13
            r9 = r14
            r13 = r8
            r14 = r9
            r8 = r14
            r9 = r13
            java.lang.String r10 = "string-ref"
            r11 = 1
            r12 = r6
            r8.<init>((java.lang.ClassCastException) r9, (java.lang.String) r10, (int) r11, (java.lang.Object) r12)
            throw r7
        L_0x0115:
            r7 = move-exception
            gnu.mapping.WrongType r8 = new gnu.mapping.WrongType
            r13 = r7
            r14 = r8
            r7 = r14
            r8 = r13
            r9 = r14
            r13 = r8
            r14 = r9
            r8 = r14
            r9 = r13
            java.lang.String r10 = "string-ref"
            r11 = 2
            r12 = r6
            r8.<init>((java.lang.ClassCastException) r9, (java.lang.String) r10, (int) r11, (java.lang.Object) r12)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.pregexp.pregexpReadPattern(java.lang.Object, java.lang.Object, java.lang.Object):java.lang.Object");
    }

    public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj4 = obj;
        Object obj5 = obj2;
        Object obj6 = obj3;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 18:
                callContext2.value1 = obj4;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 19:
                callContext2.value1 = obj4;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 20:
                callContext2.value1 = obj4;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 21:
                callContext2.value1 = obj4;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 22:
                callContext2.value1 = obj4;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 23:
                callContext2.value1 = obj4;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 24:
                callContext2.value1 = obj4;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 25:
                callContext2.value1 = obj4;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 26:
                callContext2.value1 = obj4;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 27:
                callContext2.value1 = obj4;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 29:
                callContext2.value1 = obj4;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 32:
                callContext2.value1 = obj4;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 48:
                callContext2.value1 = obj4;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 49:
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

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0051, code lost:
        r7 = gnu.lists.LList.list2(kawa.lib.C1245lists.cons(Lit5, pregexpReverse$Ex(r3)), r4);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object pregexpReadBranch(java.lang.Object r15, java.lang.Object r16, java.lang.Object r17) {
        /*
            r0 = r15
            r1 = r16
            r2 = r17
            gnu.lists.LList r7 = gnu.lists.LList.Empty
            r8 = r1
            r4 = r8
            r3 = r7
        L_0x000a:
            gnu.kawa.functions.NumberCompare r7 = kawa.standard.Scheme.numGEq
            r8 = r4
            r9 = r2
            java.lang.Object r7 = r7.apply2(r8, r9)
            java.lang.Boolean r8 = java.lang.Boolean.FALSE
            if (r7 == r8) goto L_0x0028
            gnu.mapping.SimpleSymbol r7 = Lit5
            r8 = r3
            java.lang.Object r8 = pregexpReverse$Ex(r8)
            gnu.lists.Pair r7 = kawa.lib.C1245lists.cons(r7, r8)
            r8 = r4
            gnu.lists.Pair r7 = gnu.lists.LList.list2(r7, r8)
        L_0x0026:
            r0 = r7
            return r0
        L_0x0028:
            r7 = r0
            r13 = r7
            r7 = r13
            r8 = r13
            r6 = r8
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7     // Catch:{ ClassCastException -> 0x008f }
            r8 = r4
            r13 = r8
            r8 = r13
            r9 = r13
            r6 = r9
            java.lang.Number r8 = (java.lang.Number) r8     // Catch:{ ClassCastException -> 0x00a4 }
            int r8 = r8.intValue()     // Catch:{ ClassCastException -> 0x00a4 }
            char r7 = kawa.lib.strings.stringRef(r7, r8)
            r5 = r7
            r7 = r5
            gnu.text.Char r7 = gnu.text.Char.make(r7)
            gnu.text.Char r8 = Lit7
            boolean r7 = kawa.lib.characters.isChar$Eq(r7, r8)
            r6 = r7
            r7 = r6
            if (r7 == 0) goto L_0x0062
            r7 = r6
            if (r7 == 0) goto L_0x0070
        L_0x0051:
            gnu.mapping.SimpleSymbol r7 = Lit5
            r8 = r3
            java.lang.Object r8 = pregexpReverse$Ex(r8)
            gnu.lists.Pair r7 = kawa.lib.C1245lists.cons(r7, r8)
            r8 = r4
            gnu.lists.Pair r7 = gnu.lists.LList.list2(r7, r8)
            goto L_0x0026
        L_0x0062:
            r7 = r5
            gnu.text.Char r7 = gnu.text.Char.make(r7)
            gnu.text.Char r8 = Lit6
            boolean r7 = kawa.lib.characters.isChar$Eq(r7, r8)
            if (r7 == 0) goto L_0x0070
            goto L_0x0051
        L_0x0070:
            r7 = r0
            r8 = r4
            r9 = r2
            java.lang.Object r7 = pregexpReadPiece(r7, r8, r9)
            r5 = r7
            gnu.expr.GenericProc r7 = kawa.lib.C1245lists.car
            r8 = r5
            java.lang.Object r7 = r7.apply1(r8)
            r8 = r3
            gnu.lists.Pair r7 = kawa.lib.C1245lists.cons(r7, r8)
            gnu.expr.GenericProc r8 = kawa.lib.C1245lists.cadr
            r9 = r5
            java.lang.Object r8 = r8.apply1(r9)
            r4 = r8
            r3 = r7
            goto L_0x000a
        L_0x008f:
            r7 = move-exception
            gnu.mapping.WrongType r8 = new gnu.mapping.WrongType
            r13 = r7
            r14 = r8
            r7 = r14
            r8 = r13
            r9 = r14
            r13 = r8
            r14 = r9
            r8 = r14
            r9 = r13
            java.lang.String r10 = "string-ref"
            r11 = 1
            r12 = r6
            r8.<init>((java.lang.ClassCastException) r9, (java.lang.String) r10, (int) r11, (java.lang.Object) r12)
            throw r7
        L_0x00a4:
            r7 = move-exception
            gnu.mapping.WrongType r8 = new gnu.mapping.WrongType
            r13 = r7
            r14 = r8
            r7 = r14
            r8 = r13
            r9 = r14
            r13 = r8
            r14 = r9
            r8 = r14
            r9 = r13
            java.lang.String r10 = "string-ref"
            r11 = 2
            r12 = r6
            r8.<init>((java.lang.ClassCastException) r9, (java.lang.String) r10, (int) r11, (java.lang.Object) r12)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.pregexp.pregexpReadBranch(java.lang.Object, java.lang.Object, java.lang.Object):java.lang.Object");
    }

    public static Object pregexpReadPiece(Object obj, Object obj2, Object obj3) {
        Throwable th;
        Throwable th2;
        Object x;
        Object s;
        Throwable th3;
        Throwable th4;
        boolean x2;
        Object pregexpError$V;
        Throwable th5;
        Object tmp;
        Object pregexpReadCharList;
        Throwable th6;
        Throwable th7;
        Object s2 = obj;
        Object i = obj2;
        Object n = obj3;
        Object obj4 = s2;
        Object obj5 = obj4;
        try {
            CharSequence charSequence = (CharSequence) obj4;
            Object obj6 = i;
            Object obj7 = obj6;
            try {
                char c = strings.stringRef(charSequence, ((Number) obj6).intValue());
                if (Scheme.isEqv.apply2(Char.make(c), Lit9) != Boolean.FALSE) {
                    s = LList.list2(Lit10, AddOp.$Pl.apply2(i, Lit8));
                } else if (Scheme.isEqv.apply2(Char.make(c), Lit11) != Boolean.FALSE) {
                    s = LList.list2(Lit12, AddOp.$Pl.apply2(i, Lit8));
                } else if (Scheme.isEqv.apply2(Char.make(c), Lit13) != Boolean.FALSE) {
                    s = pregexpWrapQuantifierIfAny(LList.list2(Lit14, AddOp.$Pl.apply2(i, Lit8)), s2, n);
                } else if (Scheme.isEqv.apply2(Char.make(c), Lit15) != Boolean.FALSE) {
                    Object i$Pl1 = AddOp.$Pl.apply2(i, Lit8);
                    Object apply2 = Scheme.numLss.apply2(i$Pl1, n);
                    Object obj8 = apply2;
                    try {
                        boolean x3 = ((Boolean) apply2).booleanValue();
                        if (x3) {
                            Object obj9 = s2;
                            Object obj10 = obj9;
                            try {
                                CharSequence charSequence2 = (CharSequence) obj9;
                                Object obj11 = i$Pl1;
                                Object obj12 = obj11;
                                try {
                                    tmp = Char.make(strings.stringRef(charSequence2, ((Number) obj11).intValue()));
                                } catch (ClassCastException e) {
                                    ClassCastException classCastException = e;
                                    Throwable th8 = th7;
                                    new WrongType(classCastException, "string-ref", 2, obj12);
                                    throw th8;
                                }
                            } catch (ClassCastException e2) {
                                ClassCastException classCastException2 = e2;
                                Throwable th9 = th6;
                                new WrongType(classCastException2, "string-ref", 1, obj10);
                                throw th9;
                            }
                        } else {
                            tmp = x3 ? Boolean.TRUE : Boolean.FALSE;
                        }
                        if (Scheme.isEqv.apply2(tmp, Lit9) != Boolean.FALSE) {
                            Object vv = pregexpReadCharList(s2, AddOp.$Pl.apply2(i, Lit16), n);
                            pregexpReadCharList = LList.list2(LList.list2(Lit17, C1245lists.car.apply1(vv)), C1245lists.cadr.apply1(vv));
                        } else {
                            pregexpReadCharList = pregexpReadCharList(s2, i$Pl1, n);
                        }
                        s = pregexpWrapQuantifierIfAny(pregexpReadCharList, s2, n);
                    } catch (ClassCastException e3) {
                        ClassCastException classCastException3 = e3;
                        Throwable th10 = th5;
                        new WrongType(classCastException3, "x", -2, obj8);
                        throw th10;
                    }
                } else if (Scheme.isEqv.apply2(Char.make(c), Lit18) != Boolean.FALSE) {
                    s = pregexpWrapQuantifierIfAny(pregexpReadSubpattern(s2, AddOp.$Pl.apply2(i, Lit8), n), s2, n);
                } else if (Scheme.isEqv.apply2(Char.make(c), Lit19) != Boolean.FALSE) {
                    Object temp = pregexpReadEscapedNumber(s2, i, n);
                    if (temp != Boolean.FALSE) {
                        pregexpError$V = LList.list2(LList.list2(Lit20, C1245lists.car.apply1(temp)), C1245lists.cadr.apply1(temp));
                    } else {
                        Object temp2 = pregexpReadEscapedChar(s2, i, n);
                        if (temp2 != Boolean.FALSE) {
                            pregexpError$V = LList.list2(C1245lists.car.apply1(temp2), C1245lists.cadr.apply1(temp2));
                        } else {
                            Object[] objArr = new Object[2];
                            objArr[0] = Lit21;
                            Object[] objArr2 = objArr;
                            objArr2[1] = Lit22;
                            pregexpError$V = pregexpError$V(objArr2);
                        }
                    }
                    s = pregexpWrapQuantifierIfAny(pregexpError$V, s2, n);
                } else {
                    if ((x = $Stpregexp$Mnspace$Mnsensitive$Qu$St) == Boolean.FALSE ? (unicode.isCharWhitespace(Char.make(c)) ? 1 : 0) + true == false || !true ? !x2 : characters.isChar$Eq(Char.make(c), Lit1) : x == Boolean.FALSE) {
                        Object obj13 = i;
                        Boolean bool = Boolean.FALSE;
                        while (true) {
                            Boolean bool2 = bool;
                            Object i2 = obj13;
                            if (Scheme.numGEq.apply2(i2, n) != Boolean.FALSE) {
                                s = LList.list2(Lit23, i2);
                                break;
                            }
                            Object obj14 = s2;
                            Object obj15 = obj14;
                            try {
                                CharSequence charSequence3 = (CharSequence) obj14;
                                Object obj16 = i2;
                                Object obj17 = obj16;
                                try {
                                    char c2 = strings.stringRef(charSequence3, ((Number) obj16).intValue());
                                    if (bool2 == Boolean.FALSE) {
                                        if (!unicode.isCharWhitespace(Char.make(c2))) {
                                            if (!characters.isChar$Eq(Char.make(c2), Lit1)) {
                                                s = LList.list2(Lit23, i2);
                                                break;
                                            }
                                            obj13 = AddOp.$Pl.apply2(i2, Lit8);
                                            bool = Boolean.TRUE;
                                        } else {
                                            obj13 = AddOp.$Pl.apply2(i2, Lit8);
                                            bool = Boolean.FALSE;
                                        }
                                    } else {
                                        obj13 = AddOp.$Pl.apply2(i2, Lit8);
                                        bool = characters.isChar$Eq(Char.make(c2), Lit24) ? Boolean.FALSE : Boolean.TRUE;
                                    }
                                } catch (ClassCastException e4) {
                                    ClassCastException classCastException4 = e4;
                                    Throwable th11 = th4;
                                    new WrongType(classCastException4, "string-ref", 2, obj17);
                                    throw th11;
                                }
                            } catch (ClassCastException e5) {
                                ClassCastException classCastException5 = e5;
                                Throwable th12 = th3;
                                new WrongType(classCastException5, "string-ref", 1, obj15);
                                throw th12;
                            }
                        }
                    } else {
                        s = pregexpWrapQuantifierIfAny(LList.list2(Char.make(c), AddOp.$Pl.apply2(i, Lit8)), s2, n);
                    }
                }
                return s;
            } catch (ClassCastException e6) {
                ClassCastException classCastException6 = e6;
                Throwable th13 = th2;
                new WrongType(classCastException6, "string-ref", 2, obj7);
                throw th13;
            }
        } catch (ClassCastException e7) {
            ClassCastException classCastException7 = e7;
            Throwable th14 = th;
            new WrongType(classCastException7, "string-ref", 1, obj5);
            throw th14;
        }
    }

    public static Object pregexpReadEscapedNumber(Object obj, Object obj2, Object obj3) {
        Throwable th;
        Object s;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        Throwable th7;
        Object s2 = obj;
        Object i = obj2;
        Object n = obj3;
        Object apply2 = Scheme.numLss.apply2(AddOp.$Pl.apply2(i, Lit8), n);
        Object obj4 = apply2;
        try {
            boolean x = ((Boolean) apply2).booleanValue();
            if (x) {
                Object obj5 = s2;
                Object obj6 = obj5;
                try {
                    CharSequence charSequence = (CharSequence) obj5;
                    Object apply22 = AddOp.$Pl.apply2(i, Lit8);
                    Object obj7 = apply22;
                    try {
                        char c = strings.stringRef(charSequence, ((Number) apply22).intValue());
                        boolean x2 = unicode.isCharNumeric(Char.make(c));
                        if (x2) {
                            Object apply23 = AddOp.$Pl.apply2(i, Lit16);
                            Pair list1 = LList.list1(Char.make(c));
                            while (true) {
                                Pair r = list1;
                                Object i2 = apply23;
                                if (Scheme.numGEq.apply2(i2, n) != Boolean.FALSE) {
                                    Object pregexpReverse$Ex = pregexpReverse$Ex(r);
                                    Object obj8 = pregexpReverse$Ex;
                                    try {
                                        s = LList.list2(numbers.string$To$Number(strings.list$To$String((LList) pregexpReverse$Ex)), i2);
                                        break;
                                    } catch (ClassCastException e) {
                                        ClassCastException classCastException = e;
                                        Throwable th8 = th4;
                                        new WrongType(classCastException, "list->string", 1, obj8);
                                        throw th8;
                                    }
                                } else {
                                    Object obj9 = s2;
                                    Object obj10 = obj9;
                                    try {
                                        CharSequence charSequence2 = (CharSequence) obj9;
                                        Object obj11 = i2;
                                        Object obj12 = obj11;
                                        try {
                                            char c2 = strings.stringRef(charSequence2, ((Number) obj11).intValue());
                                            if (unicode.isCharNumeric(Char.make(c2))) {
                                                apply23 = AddOp.$Pl.apply2(i2, Lit8);
                                                list1 = C1245lists.cons(Char.make(c2), r);
                                            } else {
                                                Object pregexpReverse$Ex2 = pregexpReverse$Ex(r);
                                                Object obj13 = pregexpReverse$Ex2;
                                                try {
                                                    s = LList.list2(numbers.string$To$Number(strings.list$To$String((LList) pregexpReverse$Ex2)), i2);
                                                    break;
                                                } catch (ClassCastException e2) {
                                                    ClassCastException classCastException2 = e2;
                                                    Throwable th9 = th7;
                                                    new WrongType(classCastException2, "list->string", 1, obj13);
                                                    throw th9;
                                                }
                                            }
                                        } catch (ClassCastException e3) {
                                            ClassCastException classCastException3 = e3;
                                            Throwable th10 = th6;
                                            new WrongType(classCastException3, "string-ref", 2, obj12);
                                            throw th10;
                                        }
                                    } catch (ClassCastException e4) {
                                        ClassCastException classCastException4 = e4;
                                        Throwable th11 = th5;
                                        new WrongType(classCastException4, "string-ref", 1, obj10);
                                        throw th11;
                                    }
                                }
                            }
                        } else {
                            s = x2 ? Boolean.TRUE : Boolean.FALSE;
                        }
                    } catch (ClassCastException e5) {
                        ClassCastException classCastException5 = e5;
                        Throwable th12 = th3;
                        new WrongType(classCastException5, "string-ref", 2, obj7);
                        throw th12;
                    }
                } catch (ClassCastException e6) {
                    ClassCastException classCastException6 = e6;
                    Throwable th13 = th2;
                    new WrongType(classCastException6, "string-ref", 1, obj6);
                    throw th13;
                }
            } else {
                s = x ? Boolean.TRUE : Boolean.FALSE;
            }
            return s;
        } catch (ClassCastException e7) {
            ClassCastException classCastException7 = e7;
            Throwable th14 = th;
            new WrongType(classCastException7, "x", -2, obj4);
            throw th14;
        }
    }

    public static Object pregexpReadEscapedChar(Object obj, Object obj2, Object n) {
        Throwable th;
        Object s;
        Throwable th2;
        Throwable th3;
        Object s2 = obj;
        Object i = obj2;
        Object apply2 = Scheme.numLss.apply2(AddOp.$Pl.apply2(i, Lit8), n);
        Object obj3 = apply2;
        try {
            boolean x = ((Boolean) apply2).booleanValue();
            if (x) {
                Object obj4 = s2;
                Object obj5 = obj4;
                try {
                    CharSequence charSequence = (CharSequence) obj4;
                    Object apply22 = AddOp.$Pl.apply2(i, Lit8);
                    Object obj6 = apply22;
                    try {
                        char c = strings.stringRef(charSequence, ((Number) apply22).intValue());
                        s = Scheme.isEqv.apply2(Char.make(c), Lit25) != Boolean.FALSE ? LList.list2(Lit26, AddOp.$Pl.apply2(i, Lit16)) : Scheme.isEqv.apply2(Char.make(c), Lit27) != Boolean.FALSE ? LList.list2(Lit28, AddOp.$Pl.apply2(i, Lit16)) : Scheme.isEqv.apply2(Char.make(c), Lit29) != Boolean.FALSE ? LList.list2(Lit30, AddOp.$Pl.apply2(i, Lit16)) : Scheme.isEqv.apply2(Char.make(c), Lit31) != Boolean.FALSE ? LList.list2(Lit32, AddOp.$Pl.apply2(i, Lit16)) : Scheme.isEqv.apply2(Char.make(c), Lit33) != Boolean.FALSE ? LList.list2(Lit24, AddOp.$Pl.apply2(i, Lit16)) : Scheme.isEqv.apply2(Char.make(c), Lit34) != Boolean.FALSE ? LList.list2($Stpregexp$Mnreturn$Mnchar$St, AddOp.$Pl.apply2(i, Lit16)) : Scheme.isEqv.apply2(Char.make(c), Lit35) != Boolean.FALSE ? LList.list2(Lit36, AddOp.$Pl.apply2(i, Lit16)) : Scheme.isEqv.apply2(Char.make(c), Lit37) != Boolean.FALSE ? LList.list2(Lit38, AddOp.$Pl.apply2(i, Lit16)) : Scheme.isEqv.apply2(Char.make(c), Lit39) != Boolean.FALSE ? LList.list2($Stpregexp$Mntab$Mnchar$St, AddOp.$Pl.apply2(i, Lit16)) : Scheme.isEqv.apply2(Char.make(c), Lit40) != Boolean.FALSE ? LList.list2(Lit41, AddOp.$Pl.apply2(i, Lit16)) : Scheme.isEqv.apply2(Char.make(c), Lit42) != Boolean.FALSE ? LList.list2(Lit43, AddOp.$Pl.apply2(i, Lit16)) : LList.list2(Char.make(c), AddOp.$Pl.apply2(i, Lit16));
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th4 = th3;
                        new WrongType(classCastException, "string-ref", 2, obj6);
                        throw th4;
                    }
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th5 = th2;
                    new WrongType(classCastException2, "string-ref", 1, obj5);
                    throw th5;
                }
            } else {
                s = x ? Boolean.TRUE : Boolean.FALSE;
            }
            return s;
        } catch (ClassCastException e3) {
            ClassCastException classCastException3 = e3;
            Throwable th6 = th;
            new WrongType(classCastException3, "x", -2, obj3);
            throw th6;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00b0, code lost:
        if (r7 != false) goto L_0x00b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00eb, code lost:
        if (kawa.lib.characters.isChar$Eq(gnu.text.Char.make(kawa.lib.strings.stringRef(r9, ((java.lang.Number) r15).intValue())), Lit46) == false) goto L_0x00b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00ee, code lost:
        r15 = pregexpReverse$Ex(r5);
        r8 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00f9, code lost:
        r7 = kawa.lib.misc.string$To$Symbol(kawa.lib.strings.list$To$String((gnu.lists.LList) r15));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0105, code lost:
        if (r3 == java.lang.Boolean.FALSE) goto L_0x011c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0107, code lost:
        r9 = gnu.lists.LList.list2(Lit17, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x010e, code lost:
        r9 = gnu.lists.LList.list2(r9, gnu.kawa.functions.AddOp.$Pl.apply2(r4, Lit16));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x011c, code lost:
        r9 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x01b1, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x01b2, code lost:
        r15 = r9;
        r9 = r16;
        new gnu.mapping.WrongType(r15, "list->string", 1, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x01ca, code lost:
        throw r9;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object pregexpReadPosixCharClass(java.lang.Object r17, java.lang.Object r18, java.lang.Object r19) {
        /*
            r0 = r17
            r1 = r18
            r2 = r19
            java.lang.Boolean r9 = java.lang.Boolean.FALSE
            r3 = r9
            r9 = r1
            gnu.text.Char r10 = Lit44
            gnu.lists.Pair r10 = gnu.lists.LList.list1(r10)
            r5 = r10
            r4 = r9
        L_0x0012:
            gnu.kawa.functions.NumberCompare r9 = kawa.standard.Scheme.numGEq
            r10 = r4
            r11 = r2
            java.lang.Object r9 = r9.apply2(r10, r11)
            java.lang.Boolean r10 = java.lang.Boolean.FALSE
            if (r9 == r10) goto L_0x002f
            r9 = 1
            java.lang.Object[] r9 = new java.lang.Object[r9]
            r15 = r9
            r9 = r15
            r10 = r15
            r11 = 0
            gnu.mapping.SimpleSymbol r12 = Lit45
            r10[r11] = r12
            java.lang.Object r9 = pregexpError$V(r9)
        L_0x002d:
            r0 = r9
            return r0
        L_0x002f:
            r9 = r0
            r15 = r9
            r9 = r15
            r10 = r15
            r7 = r10
            java.lang.CharSequence r9 = (java.lang.CharSequence) r9     // Catch:{ ClassCastException -> 0x012f }
            r10 = r4
            r15 = r10
            r10 = r15
            r11 = r15
            r7 = r11
            java.lang.Number r10 = (java.lang.Number) r10     // Catch:{ ClassCastException -> 0x0149 }
            int r10 = r10.intValue()     // Catch:{ ClassCastException -> 0x0149 }
            char r9 = kawa.lib.strings.stringRef(r9, r10)
            r6 = r9
            r9 = r6
            gnu.text.Char r9 = gnu.text.Char.make(r9)
            gnu.text.Char r10 = Lit9
            boolean r9 = kawa.lib.characters.isChar$Eq(r9, r10)
            if (r9 == 0) goto L_0x0063
            java.lang.Boolean r9 = java.lang.Boolean.TRUE
            r3 = r9
            gnu.kawa.functions.AddOp r9 = gnu.kawa.functions.AddOp.$Pl
            r10 = r4
            gnu.math.IntNum r11 = Lit8
            java.lang.Object r9 = r9.apply2(r10, r11)
            r10 = r5
            r5 = r10
            r4 = r9
            goto L_0x0012
        L_0x0063:
            r9 = r6
            gnu.text.Char r9 = gnu.text.Char.make(r9)
            boolean r9 = kawa.lib.rnrs.unicode.isCharAlphabetic(r9)
            if (r9 == 0) goto L_0x0084
            gnu.kawa.functions.AddOp r9 = gnu.kawa.functions.AddOp.$Pl
            r10 = r4
            gnu.math.IntNum r11 = Lit8
            java.lang.Object r9 = r9.apply2(r10, r11)
            r10 = r6
            gnu.text.Char r10 = gnu.text.Char.make(r10)
            r11 = r5
            gnu.lists.Pair r10 = kawa.lib.C1245lists.cons(r10, r11)
            r5 = r10
            r4 = r9
            goto L_0x0012
        L_0x0084:
            r9 = r6
            gnu.text.Char r9 = gnu.text.Char.make(r9)
            gnu.text.Char r10 = Lit44
            boolean r9 = kawa.lib.characters.isChar$Eq(r9, r10)
            if (r9 == 0) goto L_0x011e
            gnu.kawa.functions.NumberCompare r9 = kawa.standard.Scheme.numGEq
            gnu.kawa.functions.AddOp r10 = gnu.kawa.functions.AddOp.$Pl
            r11 = r4
            gnu.math.IntNum r12 = Lit8
            java.lang.Object r10 = r10.apply2(r11, r12)
            r11 = r2
            java.lang.Object r9 = r9.apply2(r10, r11)
            r15 = r9
            r9 = r15
            r10 = r15
            r8 = r10
            java.lang.Boolean r9 = (java.lang.Boolean) r9     // Catch:{ ClassCastException -> 0x0163 }
            boolean r9 = r9.booleanValue()     // Catch:{ ClassCastException -> 0x0163 }
            r7 = r9
            r9 = r7
            if (r9 == 0) goto L_0x00c3
            r9 = r7
            if (r9 == 0) goto L_0x00ee
        L_0x00b2:
            r9 = 1
            java.lang.Object[] r9 = new java.lang.Object[r9]
            r15 = r9
            r9 = r15
            r10 = r15
            r11 = 0
            gnu.mapping.SimpleSymbol r12 = Lit45
            r10[r11] = r12
            java.lang.Object r9 = pregexpError$V(r9)
        L_0x00c1:
            goto L_0x002d
        L_0x00c3:
            r9 = r0
            r15 = r9
            r9 = r15
            r10 = r15
            r8 = r10
            java.lang.CharSequence r9 = (java.lang.CharSequence) r9     // Catch:{ ClassCastException -> 0x017d }
            gnu.kawa.functions.AddOp r10 = gnu.kawa.functions.AddOp.$Pl
            r11 = r4
            gnu.math.IntNum r12 = Lit8
            java.lang.Object r10 = r10.apply2(r11, r12)
            r15 = r10
            r10 = r15
            r11 = r15
            r8 = r11
            java.lang.Number r10 = (java.lang.Number) r10     // Catch:{ ClassCastException -> 0x0197 }
            int r10 = r10.intValue()     // Catch:{ ClassCastException -> 0x0197 }
            char r9 = kawa.lib.strings.stringRef(r9, r10)
            gnu.text.Char r9 = gnu.text.Char.make(r9)
            gnu.text.Char r10 = Lit46
            boolean r9 = kawa.lib.characters.isChar$Eq(r9, r10)
            if (r9 != 0) goto L_0x00ee
            goto L_0x00b2
        L_0x00ee:
            r9 = r5
            java.lang.Object r9 = pregexpReverse$Ex(r9)
            r15 = r9
            r9 = r15
            r10 = r15
            r8 = r10
            gnu.lists.LList r9 = (gnu.lists.LList) r9     // Catch:{ ClassCastException -> 0x01b1 }
            java.lang.CharSequence r9 = kawa.lib.strings.list$To$String(r9)
            gnu.mapping.SimpleSymbol r9 = kawa.lib.misc.string$To$Symbol(r9)
            r7 = r9
            r9 = r3
            java.lang.Boolean r10 = java.lang.Boolean.FALSE
            if (r9 == r10) goto L_0x011c
            gnu.mapping.SimpleSymbol r9 = Lit17
            r10 = r7
            gnu.lists.Pair r9 = gnu.lists.LList.list2(r9, r10)
        L_0x010e:
            gnu.kawa.functions.AddOp r10 = gnu.kawa.functions.AddOp.$Pl
            r11 = r4
            gnu.math.IntNum r12 = Lit16
            java.lang.Object r10 = r10.apply2(r11, r12)
            gnu.lists.Pair r9 = gnu.lists.LList.list2(r9, r10)
            goto L_0x00c1
        L_0x011c:
            r9 = r7
            goto L_0x010e
        L_0x011e:
            r9 = 1
            java.lang.Object[] r9 = new java.lang.Object[r9]
            r15 = r9
            r9 = r15
            r10 = r15
            r11 = 0
            gnu.mapping.SimpleSymbol r12 = Lit45
            r10[r11] = r12
            java.lang.Object r9 = pregexpError$V(r9)
            goto L_0x002d
        L_0x012f:
            r9 = move-exception
            gnu.mapping.WrongType r10 = new gnu.mapping.WrongType
            r15 = r9
            r16 = r10
            r9 = r16
            r10 = r15
            r11 = r16
            r15 = r10
            r16 = r11
            r10 = r16
            r11 = r15
            java.lang.String r12 = "string-ref"
            r13 = 1
            r14 = r7
            r10.<init>((java.lang.ClassCastException) r11, (java.lang.String) r12, (int) r13, (java.lang.Object) r14)
            throw r9
        L_0x0149:
            r9 = move-exception
            gnu.mapping.WrongType r10 = new gnu.mapping.WrongType
            r15 = r9
            r16 = r10
            r9 = r16
            r10 = r15
            r11 = r16
            r15 = r10
            r16 = r11
            r10 = r16
            r11 = r15
            java.lang.String r12 = "string-ref"
            r13 = 2
            r14 = r7
            r10.<init>((java.lang.ClassCastException) r11, (java.lang.String) r12, (int) r13, (java.lang.Object) r14)
            throw r9
        L_0x0163:
            r9 = move-exception
            gnu.mapping.WrongType r10 = new gnu.mapping.WrongType
            r15 = r9
            r16 = r10
            r9 = r16
            r10 = r15
            r11 = r16
            r15 = r10
            r16 = r11
            r10 = r16
            r11 = r15
            java.lang.String r12 = "x"
            r13 = -2
            r14 = r8
            r10.<init>((java.lang.ClassCastException) r11, (java.lang.String) r12, (int) r13, (java.lang.Object) r14)
            throw r9
        L_0x017d:
            r9 = move-exception
            gnu.mapping.WrongType r10 = new gnu.mapping.WrongType
            r15 = r9
            r16 = r10
            r9 = r16
            r10 = r15
            r11 = r16
            r15 = r10
            r16 = r11
            r10 = r16
            r11 = r15
            java.lang.String r12 = "string-ref"
            r13 = 1
            r14 = r8
            r10.<init>((java.lang.ClassCastException) r11, (java.lang.String) r12, (int) r13, (java.lang.Object) r14)
            throw r9
        L_0x0197:
            r9 = move-exception
            gnu.mapping.WrongType r10 = new gnu.mapping.WrongType
            r15 = r9
            r16 = r10
            r9 = r16
            r10 = r15
            r11 = r16
            r15 = r10
            r16 = r11
            r10 = r16
            r11 = r15
            java.lang.String r12 = "string-ref"
            r13 = 2
            r14 = r8
            r10.<init>((java.lang.ClassCastException) r11, (java.lang.String) r12, (int) r13, (java.lang.Object) r14)
            throw r9
        L_0x01b1:
            r9 = move-exception
            gnu.mapping.WrongType r10 = new gnu.mapping.WrongType
            r15 = r9
            r16 = r10
            r9 = r16
            r10 = r15
            r11 = r16
            r15 = r10
            r16 = r11
            r10 = r16
            r11 = r15
            java.lang.String r12 = "list->string"
            r13 = 1
            r14 = r8
            r10.<init>((java.lang.ClassCastException) r11, (java.lang.String) r12, (int) r13, (java.lang.Object) r14)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.pregexp.pregexpReadPosixCharClass(java.lang.Object, java.lang.Object, java.lang.Object):java.lang.Object");
    }

    public static Object pregexpReadClusterType(Object obj, Object obj2, Object obj3) {
        Throwable th;
        Throwable th2;
        Object s;
        Throwable th3;
        Throwable th4;
        LList lList;
        Object i;
        Throwable th5;
        Throwable th6;
        char c;
        Throwable th7;
        Throwable th8;
        Object s2 = obj;
        Object i2 = obj2;
        Object obj4 = obj3;
        Object obj5 = s2;
        Object obj6 = obj5;
        try {
            CharSequence charSequence = (CharSequence) obj5;
            Object obj7 = i2;
            Object obj8 = obj7;
            try {
                if (Scheme.isEqv.apply2(Char.make(strings.stringRef(charSequence, ((Number) obj7).intValue())), Lit47) != Boolean.FALSE) {
                    Object i3 = AddOp.$Pl.apply2(i2, Lit8);
                    Object obj9 = s2;
                    Object obj10 = obj9;
                    try {
                        CharSequence charSequence2 = (CharSequence) obj9;
                        Object obj11 = i3;
                        Object obj12 = obj11;
                        try {
                            char tmp = strings.stringRef(charSequence2, ((Number) obj11).intValue());
                            if (Scheme.isEqv.apply2(Char.make(tmp), Lit44) != Boolean.FALSE) {
                                s = LList.list2(LList.Empty, AddOp.$Pl.apply2(i3, Lit8));
                            } else if (Scheme.isEqv.apply2(Char.make(tmp), Lit48) != Boolean.FALSE) {
                                s = LList.list2(Lit49, AddOp.$Pl.apply2(i3, Lit8));
                            } else if (Scheme.isEqv.apply2(Char.make(tmp), Lit50) != Boolean.FALSE) {
                                s = LList.list2(Lit51, AddOp.$Pl.apply2(i3, Lit8));
                            } else if (Scheme.isEqv.apply2(Char.make(tmp), Lit52) != Boolean.FALSE) {
                                s = LList.list2(Lit53, AddOp.$Pl.apply2(i3, Lit8));
                            } else if (Scheme.isEqv.apply2(Char.make(tmp), Lit54) != Boolean.FALSE) {
                                Object obj13 = s2;
                                Object obj14 = obj13;
                                try {
                                    CharSequence charSequence3 = (CharSequence) obj13;
                                    Object apply2 = AddOp.$Pl.apply2(i3, Lit8);
                                    Object obj15 = apply2;
                                    try {
                                        char tmp2 = strings.stringRef(charSequence3, ((Number) apply2).intValue());
                                        s = LList.list2(Scheme.isEqv.apply2(Char.make(tmp2), Lit48) != Boolean.FALSE ? Lit55 : Scheme.isEqv.apply2(Char.make(tmp2), Lit50) != Boolean.FALSE ? Lit56 : pregexpError$V(new Object[]{Lit57}), AddOp.$Pl.apply2(i3, Lit16));
                                    } catch (ClassCastException e) {
                                        ClassCastException classCastException = e;
                                        Throwable th9 = th8;
                                        new WrongType(classCastException, "string-ref", 2, obj15);
                                        throw th9;
                                    }
                                } catch (ClassCastException e2) {
                                    ClassCastException classCastException2 = e2;
                                    Throwable th10 = th7;
                                    new WrongType(classCastException2, "string-ref", 1, obj14);
                                    throw th10;
                                }
                            } else {
                                Object obj16 = i3;
                                LList lList2 = LList.Empty;
                                Boolean bool = Boolean.FALSE;
                                while (true) {
                                    Boolean bool2 = bool;
                                    lList = lList2;
                                    i = obj16;
                                    Object obj17 = s2;
                                    Object obj18 = obj17;
                                    try {
                                        CharSequence charSequence4 = (CharSequence) obj17;
                                        Object obj19 = i;
                                        Object obj20 = obj19;
                                        try {
                                            c = strings.stringRef(charSequence4, ((Number) obj19).intValue());
                                            if (Scheme.isEqv.apply2(Char.make(c), Lit58) == Boolean.FALSE) {
                                                if (Scheme.isEqv.apply2(Char.make(c), Lit59) == Boolean.FALSE) {
                                                    if (Scheme.isEqv.apply2(Char.make(c), Lit62) == Boolean.FALSE) {
                                                        break;
                                                    }
                                                    $Stpregexp$Mnspace$Mnsensitive$Qu$St = bool2;
                                                    obj16 = AddOp.$Pl.apply2(i, Lit8);
                                                    lList2 = lList;
                                                    bool = Boolean.FALSE;
                                                } else {
                                                    obj16 = AddOp.$Pl.apply2(i, Lit8);
                                                    lList2 = C1245lists.cons(bool2 != Boolean.FALSE ? Lit60 : Lit61, lList);
                                                    bool = Boolean.FALSE;
                                                }
                                            } else {
                                                obj16 = AddOp.$Pl.apply2(i, Lit8);
                                                lList2 = lList;
                                                bool = Boolean.TRUE;
                                            }
                                        } catch (ClassCastException e3) {
                                            ClassCastException classCastException3 = e3;
                                            Throwable th11 = th6;
                                            new WrongType(classCastException3, "string-ref", 2, obj20);
                                            throw th11;
                                        }
                                    } catch (ClassCastException e4) {
                                        ClassCastException classCastException4 = e4;
                                        Throwable th12 = th5;
                                        new WrongType(classCastException4, "string-ref", 1, obj18);
                                        throw th12;
                                    }
                                }
                                s = Scheme.isEqv.apply2(Char.make(c), Lit44) != Boolean.FALSE ? LList.list2(lList, AddOp.$Pl.apply2(i, Lit8)) : pregexpError$V(new Object[]{Lit57});
                            }
                        } catch (ClassCastException e5) {
                            ClassCastException classCastException5 = e5;
                            Throwable th13 = th4;
                            new WrongType(classCastException5, "string-ref", 2, obj12);
                            throw th13;
                        }
                    } catch (ClassCastException e6) {
                        ClassCastException classCastException6 = e6;
                        Throwable th14 = th3;
                        new WrongType(classCastException6, "string-ref", 1, obj10);
                        throw th14;
                    }
                } else {
                    s = LList.list2(Lit63, i2);
                }
                return s;
            } catch (ClassCastException e7) {
                ClassCastException classCastException7 = e7;
                Throwable th15 = th2;
                new WrongType(classCastException7, "string-ref", 2, obj8);
                throw th15;
            }
        } catch (ClassCastException e8) {
            ClassCastException classCastException8 = e8;
            Throwable th16 = th;
            new WrongType(classCastException8, "string-ref", 1, obj6);
            throw th16;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x007b, code lost:
        if (kawa.lib.characters.isChar$Eq(gnu.text.Char.make(kawa.lib.strings.stringRef(r12, ((java.lang.Number) r18).intValue())), Lit6) != false) goto L_0x007d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0099, code lost:
        if (r10 != false) goto L_0x007d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00b2, code lost:
        r12 = pregexpError$V(new java.lang.Object[]{Lit64});
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object pregexpReadSubpattern(java.lang.Object r20, java.lang.Object r21, java.lang.Object r22) {
        /*
            r0 = r20
            r1 = r21
            r2 = r22
            java.lang.Object r12 = $Stpregexp$Mnspace$Mnsensitive$Qu$St
            r3 = r12
            r12 = r0
            r13 = r1
            r14 = r2
            java.lang.Object r12 = pregexpReadClusterType(r12, r13, r14)
            r4 = r12
            gnu.expr.GenericProc r12 = kawa.lib.C1245lists.car
            r13 = r4
            java.lang.Object r12 = r12.apply1(r13)
            r5 = r12
            gnu.expr.GenericProc r12 = kawa.lib.C1245lists.cadr
            r13 = r4
            java.lang.Object r12 = r12.apply1(r13)
            r6 = r12
            r12 = r0
            r13 = r6
            r14 = r2
            java.lang.Object r12 = pregexpReadPattern(r12, r13, r14)
            r7 = r12
            r12 = r3
            $Stpregexp$Mnspace$Mnsensitive$Qu$St = r12
            gnu.expr.GenericProc r12 = kawa.lib.C1245lists.car
            r13 = r7
            java.lang.Object r12 = r12.apply1(r13)
            gnu.expr.GenericProc r13 = kawa.lib.C1245lists.cadr
            r14 = r7
            java.lang.Object r13 = r13.apply1(r14)
            r9 = r13
            r8 = r12
            gnu.kawa.functions.NumberCompare r12 = kawa.standard.Scheme.numLss
            r13 = r9
            r14 = r2
            java.lang.Object r12 = r12.apply2(r13, r14)
            r18 = r12
            r12 = r18
            r13 = r18
            r11 = r13
            java.lang.Boolean r12 = (java.lang.Boolean) r12     // Catch:{ ClassCastException -> 0x00c5 }
            boolean r12 = r12.booleanValue()     // Catch:{ ClassCastException -> 0x00c5 }
            r10 = r12
            r12 = r10
            if (r12 == 0) goto L_0x0098
            r12 = r0
            r18 = r12
            r12 = r18
            r13 = r18
            r11 = r13
            java.lang.CharSequence r12 = (java.lang.CharSequence) r12     // Catch:{ ClassCastException -> 0x00e5 }
            r13 = r9
            r18 = r13
            r13 = r18
            r14 = r18
            r11 = r14
            java.lang.Number r13 = (java.lang.Number) r13     // Catch:{ ClassCastException -> 0x0105 }
            int r13 = r13.intValue()     // Catch:{ ClassCastException -> 0x0105 }
            char r12 = kawa.lib.strings.stringRef(r12, r13)
            gnu.text.Char r12 = gnu.text.Char.make(r12)
            gnu.text.Char r13 = Lit6
            boolean r12 = kawa.lib.characters.isChar$Eq(r12, r13)
            if (r12 == 0) goto L_0x00b2
        L_0x007d:
            r12 = r5
            r13 = r8
            r11 = r13
            r10 = r12
        L_0x0081:
            r12 = r10
            boolean r12 = kawa.lib.C1245lists.isNull(r12)
            if (r12 == 0) goto L_0x009c
            r12 = r11
            gnu.kawa.functions.AddOp r13 = gnu.kawa.functions.AddOp.$Pl
            r14 = r9
            gnu.math.IntNum r15 = Lit8
            java.lang.Object r13 = r13.apply2(r14, r15)
            gnu.lists.Pair r12 = gnu.lists.LList.list2(r12, r13)
        L_0x0096:
            r0 = r12
            return r0
        L_0x0098:
            r12 = r10
            if (r12 == 0) goto L_0x00b2
            goto L_0x007d
        L_0x009c:
            gnu.expr.GenericProc r12 = kawa.lib.C1245lists.cdr
            r13 = r10
            java.lang.Object r12 = r12.apply1(r13)
            gnu.expr.GenericProc r13 = kawa.lib.C1245lists.car
            r14 = r10
            java.lang.Object r13 = r13.apply1(r14)
            r14 = r11
            gnu.lists.Pair r13 = gnu.lists.LList.list2(r13, r14)
            r11 = r13
            r10 = r12
            goto L_0x0081
        L_0x00b2:
            r12 = 1
            java.lang.Object[] r12 = new java.lang.Object[r12]
            r18 = r12
            r12 = r18
            r13 = r18
            r14 = 0
            gnu.mapping.SimpleSymbol r15 = Lit64
            r13[r14] = r15
            java.lang.Object r12 = pregexpError$V(r12)
            goto L_0x0096
        L_0x00c5:
            r12 = move-exception
            gnu.mapping.WrongType r13 = new gnu.mapping.WrongType
            r18 = r12
            r19 = r13
            r12 = r19
            r13 = r18
            r14 = r19
            r18 = r13
            r19 = r14
            r13 = r19
            r14 = r18
            java.lang.String r15 = "x"
            r16 = -2
            r17 = r11
            r13.<init>((java.lang.ClassCastException) r14, (java.lang.String) r15, (int) r16, (java.lang.Object) r17)
            throw r12
        L_0x00e5:
            r12 = move-exception
            gnu.mapping.WrongType r13 = new gnu.mapping.WrongType
            r18 = r12
            r19 = r13
            r12 = r19
            r13 = r18
            r14 = r19
            r18 = r13
            r19 = r14
            r13 = r19
            r14 = r18
            java.lang.String r15 = "string-ref"
            r16 = 1
            r17 = r11
            r13.<init>((java.lang.ClassCastException) r14, (java.lang.String) r15, (int) r16, (java.lang.Object) r17)
            throw r12
        L_0x0105:
            r12 = move-exception
            gnu.mapping.WrongType r13 = new gnu.mapping.WrongType
            r18 = r12
            r19 = r13
            r12 = r19
            r13 = r18
            r14 = r19
            r18 = r13
            r19 = r14
            r13 = r19
            r14 = r18
            java.lang.String r15 = "string-ref"
            r16 = 2
            r17 = r11
            r13.<init>((java.lang.ClassCastException) r14, (java.lang.String) r15, (int) r16, (java.lang.Object) r17)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.pregexp.pregexpReadSubpattern(java.lang.Object, java.lang.Object, java.lang.Object):java.lang.Object");
    }

    public static Object pregexpWrapQuantifierIfAny(Object obj, Object obj2, Object obj3) {
        Object i;
        Pair vv;
        Throwable th;
        Throwable th2;
        char c;
        Object x;
        Object x2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        Throwable th7;
        Throwable th8;
        Object i2;
        Throwable th9;
        Throwable th10;
        Throwable th11;
        Throwable th12;
        char c2;
        Throwable th13;
        Throwable th14;
        Throwable th15;
        Throwable th16;
        Throwable th17;
        Throwable th18;
        Object vv2 = obj;
        Object s = obj2;
        Object n = obj3;
        Object re = C1245lists.car.apply1(vv2);
        Object apply1 = C1245lists.cadr.apply1(vv2);
        while (true) {
            i = apply1;
            if (Scheme.numGEq.apply2(i, n) != Boolean.FALSE) {
                vv = vv2;
                break;
            }
            Object obj4 = s;
            Object obj5 = obj4;
            try {
                CharSequence charSequence = (CharSequence) obj4;
                Object obj6 = i;
                Object obj7 = obj6;
                try {
                    c = strings.stringRef(charSequence, ((Number) obj6).intValue());
                    boolean x3 = unicode.isCharWhitespace(Char.make(c));
                    if (!x3) {
                        if (!x3) {
                            break;
                        }
                    } else if ($Stpregexp$Mnspace$Mnsensitive$Qu$St != Boolean.FALSE) {
                        break;
                    }
                    apply1 = AddOp.$Pl.apply2(i, Lit8);
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th19 = th2;
                    new WrongType(classCastException, "string-ref", 2, obj7);
                    throw th19;
                }
            } catch (ClassCastException e2) {
                ClassCastException classCastException2 = e2;
                Throwable th20 = th;
                new WrongType(classCastException2, "string-ref", 1, obj5);
                throw th20;
            }
        }
        Object x4 = Scheme.isEqv.apply2(Char.make(c), Lit65);
        if (x4 == Boolean.FALSE ? (x = Scheme.isEqv.apply2(Char.make(c), Lit66)) == Boolean.FALSE ? (x2 = Scheme.isEqv.apply2(Char.make(c), Lit47)) == Boolean.FALSE ? Scheme.isEqv.apply2(Char.make(c), Lit67) == Boolean.FALSE : x2 == Boolean.FALSE : x == Boolean.FALSE : x4 == Boolean.FALSE) {
            vv = vv2;
            return vv;
        }
        Pair list1 = LList.list1(Lit68);
        Pair chain4 = LList.chain4(list1, Lit69, Lit70, Lit71, re);
        Pair new$Mnre = list1;
        Pair new$Mnvv = LList.list2(new$Mnre, Lit72);
        if (Scheme.isEqv.apply2(Char.make(c), Lit65) != Boolean.FALSE) {
            Object apply12 = C1245lists.cddr.apply1(new$Mnre);
            Object obj8 = apply12;
            try {
                C1245lists.setCar$Ex((Pair) apply12, Lit73);
                Object apply13 = C1245lists.cdddr.apply1(new$Mnre);
                Object obj9 = apply13;
                try {
                    C1245lists.setCar$Ex((Pair) apply13, Boolean.FALSE);
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th21 = th18;
                    new WrongType(classCastException3, "set-car!", 1, obj9);
                    throw th21;
                }
            } catch (ClassCastException e4) {
                ClassCastException classCastException4 = e4;
                Throwable th22 = th17;
                new WrongType(classCastException4, "set-car!", 1, obj8);
                throw th22;
            }
        } else if (Scheme.isEqv.apply2(Char.make(c), Lit66) != Boolean.FALSE) {
            Object apply14 = C1245lists.cddr.apply1(new$Mnre);
            Object obj10 = apply14;
            try {
                C1245lists.setCar$Ex((Pair) apply14, Lit8);
                Object apply15 = C1245lists.cdddr.apply1(new$Mnre);
                Object obj11 = apply15;
                try {
                    C1245lists.setCar$Ex((Pair) apply15, Boolean.FALSE);
                } catch (ClassCastException e5) {
                    ClassCastException classCastException5 = e5;
                    Throwable th23 = th8;
                    new WrongType(classCastException5, "set-car!", 1, obj11);
                    throw th23;
                }
            } catch (ClassCastException e6) {
                ClassCastException classCastException6 = e6;
                Throwable th24 = th7;
                new WrongType(classCastException6, "set-car!", 1, obj10);
                throw th24;
            }
        } else if (Scheme.isEqv.apply2(Char.make(c), Lit47) != Boolean.FALSE) {
            Object apply16 = C1245lists.cddr.apply1(new$Mnre);
            Object obj12 = apply16;
            try {
                C1245lists.setCar$Ex((Pair) apply16, Lit73);
                Object apply17 = C1245lists.cdddr.apply1(new$Mnre);
                Object obj13 = apply17;
                try {
                    C1245lists.setCar$Ex((Pair) apply17, Lit8);
                } catch (ClassCastException e7) {
                    ClassCastException classCastException7 = e7;
                    Throwable th25 = th6;
                    new WrongType(classCastException7, "set-car!", 1, obj13);
                    throw th25;
                }
            } catch (ClassCastException e8) {
                ClassCastException classCastException8 = e8;
                Throwable th26 = th5;
                new WrongType(classCastException8, "set-car!", 1, obj12);
                throw th26;
            }
        } else if (Scheme.isEqv.apply2(Char.make(c), Lit67) != Boolean.FALSE) {
            Object pq = pregexpReadNums(s, AddOp.$Pl.apply2(i, Lit8), n);
            if (pq == Boolean.FALSE) {
                Object[] objArr = new Object[2];
                objArr[0] = Lit74;
                Object[] objArr2 = objArr;
                objArr2[1] = Lit75;
                Object pregexpError$V = pregexpError$V(objArr2);
            }
            Object apply18 = C1245lists.cddr.apply1(new$Mnre);
            Object obj14 = apply18;
            try {
                C1245lists.setCar$Ex((Pair) apply18, C1245lists.car.apply1(pq));
                Object apply19 = C1245lists.cdddr.apply1(new$Mnre);
                Object obj15 = apply19;
                try {
                    C1245lists.setCar$Ex((Pair) apply19, C1245lists.cadr.apply1(pq));
                    i = C1245lists.caddr.apply1(pq);
                } catch (ClassCastException e9) {
                    ClassCastException classCastException9 = e9;
                    Throwable th27 = th4;
                    new WrongType(classCastException9, "set-car!", 1, obj15);
                    throw th27;
                }
            } catch (ClassCastException e10) {
                ClassCastException classCastException10 = e10;
                Throwable th28 = th3;
                new WrongType(classCastException10, "set-car!", 1, obj14);
                throw th28;
            }
        }
        Object apply2 = AddOp.$Pl.apply2(i, Lit8);
        while (true) {
            i2 = apply2;
            if (Scheme.numGEq.apply2(i2, n) != Boolean.FALSE) {
                Object apply110 = C1245lists.cdr.apply1(new$Mnre);
                Object obj16 = apply110;
                try {
                    C1245lists.setCar$Ex((Pair) apply110, Boolean.FALSE);
                    Object apply111 = C1245lists.cdr.apply1(new$Mnvv);
                    Object obj17 = apply111;
                    try {
                        C1245lists.setCar$Ex((Pair) apply111, i2);
                        break;
                    } catch (ClassCastException e11) {
                        ClassCastException classCastException11 = e11;
                        Throwable th29 = th10;
                        new WrongType(classCastException11, "set-car!", 1, obj17);
                        throw th29;
                    }
                } catch (ClassCastException e12) {
                    ClassCastException classCastException12 = e12;
                    Throwable th30 = th9;
                    new WrongType(classCastException12, "set-car!", 1, obj16);
                    throw th30;
                }
            } else {
                Object obj18 = s;
                Object obj19 = obj18;
                try {
                    CharSequence charSequence2 = (CharSequence) obj18;
                    Object obj20 = i2;
                    Object obj21 = obj20;
                    try {
                        c2 = strings.stringRef(charSequence2, ((Number) obj20).intValue());
                        boolean x5 = unicode.isCharWhitespace(Char.make(c2));
                        if (!x5) {
                            if (!x5) {
                                break;
                            }
                        } else if ($Stpregexp$Mnspace$Mnsensitive$Qu$St != Boolean.FALSE) {
                            break;
                        }
                        apply2 = AddOp.$Pl.apply2(i2, Lit8);
                    } catch (ClassCastException e13) {
                        ClassCastException classCastException13 = e13;
                        Throwable th31 = th12;
                        new WrongType(classCastException13, "string-ref", 2, obj21);
                        throw th31;
                    }
                } catch (ClassCastException e14) {
                    ClassCastException classCastException14 = e14;
                    Throwable th32 = th11;
                    new WrongType(classCastException14, "string-ref", 1, obj19);
                    throw th32;
                }
            }
        }
        if (characters.isChar$Eq(Char.make(c2), Lit47)) {
            Object apply112 = C1245lists.cdr.apply1(new$Mnre);
            Object obj22 = apply112;
            try {
                C1245lists.setCar$Ex((Pair) apply112, Boolean.TRUE);
                Object apply113 = C1245lists.cdr.apply1(new$Mnvv);
                Object obj23 = apply113;
                try {
                    C1245lists.setCar$Ex((Pair) apply113, AddOp.$Pl.apply2(i2, Lit8));
                } catch (ClassCastException e15) {
                    ClassCastException classCastException15 = e15;
                    Throwable th33 = th16;
                    new WrongType(classCastException15, "set-car!", 1, obj23);
                    throw th33;
                }
            } catch (ClassCastException e16) {
                ClassCastException classCastException16 = e16;
                Throwable th34 = th15;
                new WrongType(classCastException16, "set-car!", 1, obj22);
                throw th34;
            }
        } else {
            Object apply114 = C1245lists.cdr.apply1(new$Mnre);
            Object obj24 = apply114;
            try {
                C1245lists.setCar$Ex((Pair) apply114, Boolean.FALSE);
                Object apply115 = C1245lists.cdr.apply1(new$Mnvv);
                Object obj25 = apply115;
                try {
                    C1245lists.setCar$Ex((Pair) apply115, i2);
                } catch (ClassCastException e17) {
                    ClassCastException classCastException17 = e17;
                    Throwable th35 = th14;
                    new WrongType(classCastException17, "set-car!", 1, obj25);
                    throw th35;
                }
            } catch (ClassCastException e18) {
                ClassCastException classCastException18 = e18;
                Throwable th36 = th13;
                new WrongType(classCastException18, "set-car!", 1, obj24);
                throw th36;
            }
        }
        vv = new$Mnvv;
        return vv;
    }

    public static Object pregexpReadNums(Object obj, Object i, Object obj2) {
        IntNum intNum;
        Object obj3;
        LList lList;
        LList lList2;
        Throwable th;
        Throwable th2;
        char c;
        boolean x;
        Object s;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Object s2 = obj;
        Object n = obj2;
        LList lList3 = LList.Empty;
        LList lList4 = LList.Empty;
        Object obj4 = i;
        IntNum intNum2 = Lit8;
        while (true) {
            intNum = intNum2;
            obj3 = obj4;
            lList = lList4;
            lList2 = lList3;
            if (Scheme.numGEq.apply2(obj3, n) != Boolean.FALSE) {
                Object pregexpError$V = pregexpError$V(new Object[]{Lit76});
            }
            Object obj5 = s2;
            Object obj6 = obj5;
            try {
                CharSequence charSequence = (CharSequence) obj5;
                Object obj7 = obj3;
                Object obj8 = obj7;
                try {
                    c = strings.stringRef(charSequence, ((Number) obj7).intValue());
                    if (!unicode.isCharNumeric(Char.make(c))) {
                        if (!(x = unicode.isCharWhitespace(Char.make(c))) ? !x : $Stpregexp$Mnspace$Mnsensitive$Qu$St != Boolean.FALSE) {
                            boolean x2 = characters.isChar$Eq(Char.make(c), Lit77);
                            if (!x2) {
                                if (!x2) {
                                    break;
                                }
                            } else if (Scheme.numEqu.apply2(intNum, Lit8) == Boolean.FALSE) {
                                break;
                            }
                            lList3 = lList2;
                            lList4 = lList;
                            obj4 = AddOp.$Pl.apply2(obj3, Lit8);
                            intNum2 = Lit16;
                        } else {
                            lList3 = lList2;
                            lList4 = lList;
                            obj4 = AddOp.$Pl.apply2(obj3, Lit8);
                            intNum2 = intNum;
                        }
                    } else if (Scheme.numEqu.apply2(intNum, Lit8) != Boolean.FALSE) {
                        lList3 = C1245lists.cons(Char.make(c), lList2);
                        lList4 = lList;
                        obj4 = AddOp.$Pl.apply2(obj3, Lit8);
                        intNum2 = Lit8;
                    } else {
                        lList3 = lList2;
                        lList4 = C1245lists.cons(Char.make(c), lList);
                        obj4 = AddOp.$Pl.apply2(obj3, Lit8);
                        intNum2 = Lit16;
                    }
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th6 = th2;
                    new WrongType(classCastException, "string-ref", 2, obj8);
                    throw th6;
                }
            } catch (ClassCastException e2) {
                ClassCastException classCastException2 = e2;
                Throwable th7 = th;
                new WrongType(classCastException2, "string-ref", 1, obj6);
                throw th7;
            }
        }
        if (characters.isChar$Eq(Char.make(c), Lit78)) {
            Object pregexpReverse$Ex = pregexpReverse$Ex(lList2);
            Object obj9 = pregexpReverse$Ex;
            try {
                Object string$To$Number = numbers.string$To$Number(strings.list$To$String((LList) pregexpReverse$Ex));
                Object pregexpReverse$Ex2 = pregexpReverse$Ex(lList);
                Object obj10 = pregexpReverse$Ex2;
                try {
                    Object q = numbers.string$To$Number(strings.list$To$String((LList) pregexpReverse$Ex2));
                    Object p = string$To$Number;
                    Object obj11 = p;
                    Object obj12 = obj11;
                    try {
                        boolean x3 = ((obj11 != Boolean.FALSE ? 1 : 0) + 1) & true;
                        s = (!x3 ? !x3 : Scheme.numEqu.apply2(intNum, Lit8) == Boolean.FALSE) ? Scheme.numEqu.apply2(intNum, Lit8) != Boolean.FALSE ? LList.list3(p, p, obj3) : LList.list3(p, q, obj3) : LList.list3(Lit73, Boolean.FALSE, obj3);
                    } catch (ClassCastException e3) {
                        ClassCastException classCastException3 = e3;
                        Throwable th8 = th5;
                        new WrongType(classCastException3, "x", -2, obj12);
                        throw th8;
                    }
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th9 = th4;
                    new WrongType(classCastException4, "list->string", 1, obj10);
                    throw th9;
                }
            } catch (ClassCastException e5) {
                ClassCastException classCastException5 = e5;
                Throwable th10 = th3;
                new WrongType(classCastException5, "list->string", 1, obj9);
                throw th10;
            }
        } else {
            s = Boolean.FALSE;
        }
        return s;
    }

    public static Object pregexpInvertCharList(Object obj) {
        Throwable th;
        Object vv = obj;
        Object apply1 = C1245lists.car.apply1(vv);
        Object obj2 = apply1;
        try {
            C1245lists.setCar$Ex((Pair) apply1, Lit79);
            return vv;
        } catch (ClassCastException e) {
            ClassCastException classCastException = e;
            Throwable th2 = th;
            new WrongType(classCastException, "set-car!", 1, obj2);
            throw th2;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0109, code lost:
        if (r6 != false) goto L_0x010b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x016b, code lost:
        if (kawa.lib.characters.isChar$Eq(gnu.text.Char.make(kawa.lib.strings.stringRef(r10, ((java.lang.Number) r16).intValue())), Lit46) != false) goto L_0x010b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x016f, code lost:
        if (r8 != false) goto L_0x010b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0172, code lost:
        r6 = kawa.lib.C1245lists.car.apply1(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x017f, code lost:
        if (kawa.lib.characters.isChar(r6) == false) goto L_0x01c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0181, code lost:
        r10 = Lit83;
        r11 = r6;
        r16 = r0;
        r7 = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:?, code lost:
        r12 = (java.lang.CharSequence) r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x018e, code lost:
        r16 = gnu.kawa.functions.AddOp.$Pl.apply2(r4, Lit8);
        r7 = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x01a3, code lost:
        r10 = kawa.lib.C1245lists.cons(gnu.lists.LList.list3(r10, r11, gnu.text.Char.make(kawa.lib.strings.stringRef(r12, ((java.lang.Number) r16).intValue()))), kawa.lib.C1245lists.cdr.apply1(r3));
        r11 = gnu.kawa.functions.AddOp.$Pl.apply2(r4, Lit16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x01c8, code lost:
        r10 = kawa.lib.C1245lists.cons(gnu.text.Char.make(r5), r3);
        r11 = gnu.kawa.functions.AddOp.$Pl.apply2(r4, Lit8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x030b, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x030c, code lost:
        r16 = r10;
        r10 = r17;
        new gnu.mapping.WrongType(r16, "string-ref", 1, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0328, code lost:
        throw r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0329, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x032a, code lost:
        r16 = r10;
        r10 = r17;
        new gnu.mapping.WrongType(r16, "string-ref", 2, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x0346, code lost:
        throw r10;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object pregexpReadCharList(java.lang.Object r18, java.lang.Object r19, java.lang.Object r20) {
        /*
            r0 = r18
            r1 = r19
            r2 = r20
            gnu.lists.LList r10 = gnu.lists.LList.Empty
            r11 = r1
            r4 = r11
            r3 = r10
        L_0x000b:
            gnu.kawa.functions.NumberCompare r10 = kawa.standard.Scheme.numGEq
            r11 = r4
            r12 = r2
            java.lang.Object r10 = r10.apply2(r11, r12)
            java.lang.Boolean r11 = java.lang.Boolean.FALSE
            if (r10 == r11) goto L_0x0036
            r10 = 2
            java.lang.Object[] r10 = new java.lang.Object[r10]
            r16 = r10
            r10 = r16
            r11 = r16
            r12 = 0
            gnu.mapping.SimpleSymbol r13 = Lit80
            r11[r12] = r13
            r16 = r10
            r10 = r16
            r11 = r16
            r12 = 1
            gnu.mapping.SimpleSymbol r13 = Lit81
            r11[r12] = r13
            java.lang.Object r10 = pregexpError$V(r10)
        L_0x0034:
            r0 = r10
            return r0
        L_0x0036:
            r10 = r0
            r16 = r10
            r10 = r16
            r11 = r16
            r6 = r11
            java.lang.CharSequence r10 = (java.lang.CharSequence) r10     // Catch:{ ClassCastException -> 0x0275 }
            r11 = r4
            r16 = r11
            r11 = r16
            r12 = r16
            r6 = r12
            java.lang.Number r11 = (java.lang.Number) r11     // Catch:{ ClassCastException -> 0x0293 }
            int r11 = r11.intValue()     // Catch:{ ClassCastException -> 0x0293 }
            char r10 = kawa.lib.strings.stringRef(r10, r11)
            r5 = r10
            gnu.kawa.functions.IsEqv r10 = kawa.standard.Scheme.isEqv
            r11 = r5
            gnu.text.Char r11 = gnu.text.Char.make(r11)
            gnu.text.Char r12 = Lit46
            java.lang.Object r10 = r10.apply2(r11, r12)
            java.lang.Boolean r11 = java.lang.Boolean.FALSE
            if (r10 == r11) goto L_0x009a
            r10 = r3
            boolean r10 = kawa.lib.C1245lists.isNull(r10)
            if (r10 == 0) goto L_0x0081
            r10 = r5
            gnu.text.Char r10 = gnu.text.Char.make(r10)
            r11 = r3
            gnu.lists.Pair r10 = kawa.lib.C1245lists.cons(r10, r11)
            gnu.kawa.functions.AddOp r11 = gnu.kawa.functions.AddOp.$Pl
            r12 = r4
            gnu.math.IntNum r13 = Lit8
            java.lang.Object r11 = r11.apply2(r12, r13)
            r4 = r11
            r3 = r10
            goto L_0x000b
        L_0x0081:
            gnu.mapping.SimpleSymbol r10 = Lit82
            r11 = r3
            java.lang.Object r11 = pregexpReverse$Ex(r11)
            gnu.lists.Pair r10 = kawa.lib.C1245lists.cons(r10, r11)
            gnu.kawa.functions.AddOp r11 = gnu.kawa.functions.AddOp.$Pl
            r12 = r4
            gnu.math.IntNum r13 = Lit8
            java.lang.Object r11 = r11.apply2(r12, r13)
            gnu.lists.Pair r10 = gnu.lists.LList.list2(r10, r11)
            goto L_0x0034
        L_0x009a:
            gnu.kawa.functions.IsEqv r10 = kawa.standard.Scheme.isEqv
            r11 = r5
            gnu.text.Char r11 = gnu.text.Char.make(r11)
            gnu.text.Char r12 = Lit19
            java.lang.Object r10 = r10.apply2(r11, r12)
            java.lang.Boolean r11 = java.lang.Boolean.FALSE
            if (r10 == r11) goto L_0x00ee
            r10 = r0
            r11 = r4
            r12 = r2
            java.lang.Object r10 = pregexpReadEscapedChar(r10, r11, r12)
            r6 = r10
            r10 = r6
            java.lang.Boolean r11 = java.lang.Boolean.FALSE
            if (r10 == r11) goto L_0x00cf
            gnu.expr.GenericProc r10 = kawa.lib.C1245lists.car
            r11 = r6
            java.lang.Object r10 = r10.apply1(r11)
            r11 = r3
            gnu.lists.Pair r10 = kawa.lib.C1245lists.cons(r10, r11)
            gnu.expr.GenericProc r11 = kawa.lib.C1245lists.cadr
            r12 = r6
            java.lang.Object r11 = r11.apply1(r12)
            r4 = r11
            r3 = r10
            goto L_0x000b
        L_0x00cf:
            r10 = 2
            java.lang.Object[] r10 = new java.lang.Object[r10]
            r16 = r10
            r10 = r16
            r11 = r16
            r12 = 0
            gnu.mapping.SimpleSymbol r13 = Lit80
            r11[r12] = r13
            r16 = r10
            r10 = r16
            r11 = r16
            r12 = 1
            gnu.mapping.SimpleSymbol r13 = Lit22
            r11[r12] = r13
            java.lang.Object r10 = pregexpError$V(r10)
            goto L_0x0034
        L_0x00ee:
            gnu.kawa.functions.IsEqv r10 = kawa.standard.Scheme.isEqv
            r11 = r5
            gnu.text.Char r11 = gnu.text.Char.make(r11)
            gnu.text.Char r12 = Lit58
            java.lang.Object r10 = r10.apply2(r11, r12)
            java.lang.Boolean r11 = java.lang.Boolean.FALSE
            if (r10 == r11) goto L_0x01df
            r10 = r3
            boolean r10 = kawa.lib.C1245lists.isNull(r10)
            r6 = r10
            r10 = r6
            if (r10 == 0) goto L_0x0122
            r10 = r6
            if (r10 == 0) goto L_0x0172
        L_0x010b:
            r10 = r5
            gnu.text.Char r10 = gnu.text.Char.make(r10)
            r11 = r3
            gnu.lists.Pair r10 = kawa.lib.C1245lists.cons(r10, r11)
            gnu.kawa.functions.AddOp r11 = gnu.kawa.functions.AddOp.$Pl
            r12 = r4
            gnu.math.IntNum r13 = Lit8
            java.lang.Object r11 = r11.apply2(r12, r13)
            r4 = r11
            r3 = r10
            goto L_0x000b
        L_0x0122:
            gnu.kawa.functions.AddOp r10 = gnu.kawa.functions.AddOp.$Pl
            r11 = r4
            gnu.math.IntNum r12 = Lit8
            java.lang.Object r10 = r10.apply2(r11, r12)
            r7 = r10
            gnu.kawa.functions.NumberCompare r10 = kawa.standard.Scheme.numLss
            r11 = r7
            r12 = r2
            java.lang.Object r10 = r10.apply2(r11, r12)
            r16 = r10
            r10 = r16
            r11 = r16
            r9 = r11
            java.lang.Boolean r10 = (java.lang.Boolean) r10     // Catch:{ ClassCastException -> 0x02b1 }
            boolean r10 = r10.booleanValue()     // Catch:{ ClassCastException -> 0x02b1 }
            r8 = r10
            r10 = r8
            if (r10 == 0) goto L_0x016e
            r10 = r0
            r16 = r10
            r10 = r16
            r11 = r16
            r9 = r11
            java.lang.CharSequence r10 = (java.lang.CharSequence) r10     // Catch:{ ClassCastException -> 0x02cf }
            r11 = r7
            r16 = r11
            r11 = r16
            r12 = r16
            r9 = r12
            java.lang.Number r11 = (java.lang.Number) r11     // Catch:{ ClassCastException -> 0x02ed }
            int r11 = r11.intValue()     // Catch:{ ClassCastException -> 0x02ed }
            char r10 = kawa.lib.strings.stringRef(r10, r11)
            gnu.text.Char r10 = gnu.text.Char.make(r10)
            gnu.text.Char r11 = Lit46
            boolean r10 = kawa.lib.characters.isChar$Eq(r10, r11)
            if (r10 == 0) goto L_0x0172
            goto L_0x010b
        L_0x016e:
            r10 = r8
            if (r10 == 0) goto L_0x0172
            goto L_0x010b
        L_0x0172:
            gnu.expr.GenericProc r10 = kawa.lib.C1245lists.car
            r11 = r3
            java.lang.Object r10 = r10.apply1(r11)
            r6 = r10
            r10 = r6
            boolean r10 = kawa.lib.characters.isChar(r10)
            if (r10 == 0) goto L_0x01c8
            gnu.mapping.SimpleSymbol r10 = Lit83
            r11 = r6
            r12 = r0
            r16 = r12
            r12 = r16
            r13 = r16
            r7 = r13
            java.lang.CharSequence r12 = (java.lang.CharSequence) r12     // Catch:{ ClassCastException -> 0x030b }
            gnu.kawa.functions.AddOp r13 = gnu.kawa.functions.AddOp.$Pl
            r14 = r4
            gnu.math.IntNum r15 = Lit8
            java.lang.Object r13 = r13.apply2(r14, r15)
            r16 = r13
            r13 = r16
            r14 = r16
            r7 = r14
            java.lang.Number r13 = (java.lang.Number) r13     // Catch:{ ClassCastException -> 0x0329 }
            int r13 = r13.intValue()     // Catch:{ ClassCastException -> 0x0329 }
            char r12 = kawa.lib.strings.stringRef(r12, r13)
            gnu.text.Char r12 = gnu.text.Char.make(r12)
            gnu.lists.Pair r10 = gnu.lists.LList.list3(r10, r11, r12)
            gnu.expr.GenericProc r11 = kawa.lib.C1245lists.cdr
            r12 = r3
            java.lang.Object r11 = r11.apply1(r12)
            gnu.lists.Pair r10 = kawa.lib.C1245lists.cons(r10, r11)
            gnu.kawa.functions.AddOp r11 = gnu.kawa.functions.AddOp.$Pl
            r12 = r4
            gnu.math.IntNum r13 = Lit16
            java.lang.Object r11 = r11.apply2(r12, r13)
            r4 = r11
            r3 = r10
            goto L_0x000b
        L_0x01c8:
            r10 = r5
            gnu.text.Char r10 = gnu.text.Char.make(r10)
            r11 = r3
            gnu.lists.Pair r10 = kawa.lib.C1245lists.cons(r10, r11)
            gnu.kawa.functions.AddOp r11 = gnu.kawa.functions.AddOp.$Pl
            r12 = r4
            gnu.math.IntNum r13 = Lit8
            java.lang.Object r11 = r11.apply2(r12, r13)
            r4 = r11
            r3 = r10
            goto L_0x000b
        L_0x01df:
            gnu.kawa.functions.IsEqv r10 = kawa.standard.Scheme.isEqv
            r11 = r5
            gnu.text.Char r11 = gnu.text.Char.make(r11)
            gnu.text.Char r12 = Lit15
            java.lang.Object r10 = r10.apply2(r11, r12)
            java.lang.Boolean r11 = java.lang.Boolean.FALSE
            if (r10 == r11) goto L_0x025e
            r10 = r0
            r16 = r10
            r10 = r16
            r11 = r16
            r6 = r11
            java.lang.CharSequence r10 = (java.lang.CharSequence) r10     // Catch:{ ClassCastException -> 0x0347 }
            gnu.kawa.functions.AddOp r11 = gnu.kawa.functions.AddOp.$Pl
            r12 = r4
            gnu.math.IntNum r13 = Lit8
            java.lang.Object r11 = r11.apply2(r12, r13)
            r16 = r11
            r11 = r16
            r12 = r16
            r6 = r12
            java.lang.Number r11 = (java.lang.Number) r11     // Catch:{ ClassCastException -> 0x0365 }
            int r11 = r11.intValue()     // Catch:{ ClassCastException -> 0x0365 }
            char r10 = kawa.lib.strings.stringRef(r10, r11)
            gnu.text.Char r10 = gnu.text.Char.make(r10)
            gnu.text.Char r11 = Lit44
            boolean r10 = kawa.lib.characters.isChar$Eq(r10, r11)
            if (r10 == 0) goto L_0x0247
            r10 = r0
            gnu.kawa.functions.AddOp r11 = gnu.kawa.functions.AddOp.$Pl
            r12 = r4
            gnu.math.IntNum r13 = Lit16
            java.lang.Object r11 = r11.apply2(r12, r13)
            r12 = r2
            java.lang.Object r10 = pregexpReadPosixCharClass(r10, r11, r12)
            r6 = r10
            gnu.expr.GenericProc r10 = kawa.lib.C1245lists.car
            r11 = r6
            java.lang.Object r10 = r10.apply1(r11)
            r11 = r3
            gnu.lists.Pair r10 = kawa.lib.C1245lists.cons(r10, r11)
            gnu.expr.GenericProc r11 = kawa.lib.C1245lists.cadr
            r12 = r6
            java.lang.Object r11 = r11.apply1(r12)
            r4 = r11
            r3 = r10
            goto L_0x000b
        L_0x0247:
            r10 = r5
            gnu.text.Char r10 = gnu.text.Char.make(r10)
            r11 = r3
            gnu.lists.Pair r10 = kawa.lib.C1245lists.cons(r10, r11)
            gnu.kawa.functions.AddOp r11 = gnu.kawa.functions.AddOp.$Pl
            r12 = r4
            gnu.math.IntNum r13 = Lit8
            java.lang.Object r11 = r11.apply2(r12, r13)
            r4 = r11
            r3 = r10
            goto L_0x000b
        L_0x025e:
            r10 = r5
            gnu.text.Char r10 = gnu.text.Char.make(r10)
            r11 = r3
            gnu.lists.Pair r10 = kawa.lib.C1245lists.cons(r10, r11)
            gnu.kawa.functions.AddOp r11 = gnu.kawa.functions.AddOp.$Pl
            r12 = r4
            gnu.math.IntNum r13 = Lit8
            java.lang.Object r11 = r11.apply2(r12, r13)
            r4 = r11
            r3 = r10
            goto L_0x000b
        L_0x0275:
            r10 = move-exception
            gnu.mapping.WrongType r11 = new gnu.mapping.WrongType
            r16 = r10
            r17 = r11
            r10 = r17
            r11 = r16
            r12 = r17
            r16 = r11
            r17 = r12
            r11 = r17
            r12 = r16
            java.lang.String r13 = "string-ref"
            r14 = 1
            r15 = r6
            r11.<init>((java.lang.ClassCastException) r12, (java.lang.String) r13, (int) r14, (java.lang.Object) r15)
            throw r10
        L_0x0293:
            r10 = move-exception
            gnu.mapping.WrongType r11 = new gnu.mapping.WrongType
            r16 = r10
            r17 = r11
            r10 = r17
            r11 = r16
            r12 = r17
            r16 = r11
            r17 = r12
            r11 = r17
            r12 = r16
            java.lang.String r13 = "string-ref"
            r14 = 2
            r15 = r6
            r11.<init>((java.lang.ClassCastException) r12, (java.lang.String) r13, (int) r14, (java.lang.Object) r15)
            throw r10
        L_0x02b1:
            r10 = move-exception
            gnu.mapping.WrongType r11 = new gnu.mapping.WrongType
            r16 = r10
            r17 = r11
            r10 = r17
            r11 = r16
            r12 = r17
            r16 = r11
            r17 = r12
            r11 = r17
            r12 = r16
            java.lang.String r13 = "x"
            r14 = -2
            r15 = r9
            r11.<init>((java.lang.ClassCastException) r12, (java.lang.String) r13, (int) r14, (java.lang.Object) r15)
            throw r10
        L_0x02cf:
            r10 = move-exception
            gnu.mapping.WrongType r11 = new gnu.mapping.WrongType
            r16 = r10
            r17 = r11
            r10 = r17
            r11 = r16
            r12 = r17
            r16 = r11
            r17 = r12
            r11 = r17
            r12 = r16
            java.lang.String r13 = "string-ref"
            r14 = 1
            r15 = r9
            r11.<init>((java.lang.ClassCastException) r12, (java.lang.String) r13, (int) r14, (java.lang.Object) r15)
            throw r10
        L_0x02ed:
            r10 = move-exception
            gnu.mapping.WrongType r11 = new gnu.mapping.WrongType
            r16 = r10
            r17 = r11
            r10 = r17
            r11 = r16
            r12 = r17
            r16 = r11
            r17 = r12
            r11 = r17
            r12 = r16
            java.lang.String r13 = "string-ref"
            r14 = 2
            r15 = r9
            r11.<init>((java.lang.ClassCastException) r12, (java.lang.String) r13, (int) r14, (java.lang.Object) r15)
            throw r10
        L_0x030b:
            r10 = move-exception
            gnu.mapping.WrongType r11 = new gnu.mapping.WrongType
            r16 = r10
            r17 = r11
            r10 = r17
            r11 = r16
            r12 = r17
            r16 = r11
            r17 = r12
            r11 = r17
            r12 = r16
            java.lang.String r13 = "string-ref"
            r14 = 1
            r15 = r7
            r11.<init>((java.lang.ClassCastException) r12, (java.lang.String) r13, (int) r14, (java.lang.Object) r15)
            throw r10
        L_0x0329:
            r10 = move-exception
            gnu.mapping.WrongType r11 = new gnu.mapping.WrongType
            r16 = r10
            r17 = r11
            r10 = r17
            r11 = r16
            r12 = r17
            r16 = r11
            r17 = r12
            r11 = r17
            r12 = r16
            java.lang.String r13 = "string-ref"
            r14 = 2
            r15 = r7
            r11.<init>((java.lang.ClassCastException) r12, (java.lang.String) r13, (int) r14, (java.lang.Object) r15)
            throw r10
        L_0x0347:
            r10 = move-exception
            gnu.mapping.WrongType r11 = new gnu.mapping.WrongType
            r16 = r10
            r17 = r11
            r10 = r17
            r11 = r16
            r12 = r17
            r16 = r11
            r17 = r12
            r11 = r17
            r12 = r16
            java.lang.String r13 = "string-ref"
            r14 = 1
            r15 = r6
            r11.<init>((java.lang.ClassCastException) r12, (java.lang.String) r13, (int) r14, (java.lang.Object) r15)
            throw r10
        L_0x0365:
            r10 = move-exception
            gnu.mapping.WrongType r11 = new gnu.mapping.WrongType
            r16 = r10
            r17 = r11
            r10 = r17
            r11 = r16
            r12 = r17
            r16 = r11
            r17 = r12
            r11 = r17
            r12 = r16
            java.lang.String r13 = "string-ref"
            r14 = 2
            r15 = r6
            r11.<init>((java.lang.ClassCastException) r12, (java.lang.String) r13, (int) r14, (java.lang.Object) r15)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.pregexp.pregexpReadCharList(java.lang.Object, java.lang.Object, java.lang.Object):java.lang.Object");
    }

    public static Object pregexpStringMatch(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        Throwable th;
        Object s1;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Object s12 = obj;
        Object s = obj2;
        Object i = obj3;
        Object n = obj4;
        Object sk = obj5;
        Object fk = obj6;
        Object obj7 = s12;
        Object obj8 = obj7;
        try {
            int n1 = strings.stringLength((CharSequence) obj7);
            if (Scheme.numGrt.apply2(Integer.valueOf(n1), n) != Boolean.FALSE) {
                s1 = Scheme.applyToArgs.apply1(fk);
            } else {
                Object obj9 = Lit73;
                Object obj10 = i;
                while (true) {
                    Object obj11 = obj10;
                    Object obj12 = obj9;
                    if (Scheme.numGEq.apply2(obj12, Integer.valueOf(n1)) != Boolean.FALSE) {
                        s1 = Scheme.applyToArgs.apply2(sk, obj11);
                        break;
                    } else if (Scheme.numGEq.apply2(obj11, n) != Boolean.FALSE) {
                        s1 = Scheme.applyToArgs.apply1(fk);
                        break;
                    } else {
                        Object obj13 = s12;
                        Object obj14 = obj13;
                        try {
                            CharSequence charSequence = (CharSequence) obj13;
                            Object obj15 = obj12;
                            Object obj16 = obj15;
                            try {
                                Char make = Char.make(strings.stringRef(charSequence, ((Number) obj15).intValue()));
                                Object obj17 = s;
                                Object obj18 = obj17;
                                try {
                                    CharSequence charSequence2 = (CharSequence) obj17;
                                    Object obj19 = obj11;
                                    Object obj20 = obj19;
                                    try {
                                        if (!characters.isChar$Eq(make, Char.make(strings.stringRef(charSequence2, ((Number) obj19).intValue())))) {
                                            s1 = Scheme.applyToArgs.apply1(fk);
                                            break;
                                        }
                                        obj9 = AddOp.$Pl.apply2(obj12, Lit8);
                                        obj10 = AddOp.$Pl.apply2(obj11, Lit8);
                                    } catch (ClassCastException e) {
                                        ClassCastException classCastException = e;
                                        Throwable th6 = th5;
                                        new WrongType(classCastException, "string-ref", 2, obj20);
                                        throw th6;
                                    }
                                } catch (ClassCastException e2) {
                                    ClassCastException classCastException2 = e2;
                                    Throwable th7 = th4;
                                    new WrongType(classCastException2, "string-ref", 1, obj18);
                                    throw th7;
                                }
                            } catch (ClassCastException e3) {
                                ClassCastException classCastException3 = e3;
                                Throwable th8 = th3;
                                new WrongType(classCastException3, "string-ref", 2, obj16);
                                throw th8;
                            }
                        } catch (ClassCastException e4) {
                            ClassCastException classCastException4 = e4;
                            Throwable th9 = th2;
                            new WrongType(classCastException4, "string-ref", 1, obj14);
                            throw th9;
                        }
                    }
                }
            }
            return s1;
        } catch (ClassCastException e5) {
            ClassCastException classCastException5 = e5;
            Throwable th10 = th;
            new WrongType(classCastException5, "string-length", 1, obj8);
            throw th10;
        }
    }

    public static boolean isPregexpCharWord(Object obj) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        boolean isChar$Eq;
        Object c = obj;
        Object obj2 = c;
        Object obj3 = obj2;
        try {
            boolean x = unicode.isCharAlphabetic((Char) obj2);
            if (x) {
                isChar$Eq = x;
            } else {
                Object obj4 = c;
                Object obj5 = obj4;
                try {
                    boolean x2 = unicode.isCharNumeric((Char) obj4);
                    if (x2) {
                        isChar$Eq = x2;
                    } else {
                        Object obj6 = c;
                        Object obj7 = obj6;
                        try {
                            isChar$Eq = characters.isChar$Eq((Char) obj6, Lit84);
                        } catch (ClassCastException e) {
                            ClassCastException classCastException = e;
                            Throwable th4 = th3;
                            new WrongType(classCastException, "char=?", 1, obj7);
                            throw th4;
                        }
                    }
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th5 = th2;
                    new WrongType(classCastException2, "char-numeric?", 1, obj5);
                    throw th5;
                }
            }
            return isChar$Eq;
        } catch (ClassCastException e3) {
            ClassCastException classCastException3 = e3;
            Throwable th6 = th;
            new WrongType(classCastException3, "char-alphabetic?", 1, obj3);
            throw th6;
        }
    }

    public static Object isPregexpAtWordBoundary(Object obj, Object obj2, Object obj3) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        Throwable th7;
        Object s;
        Object s2 = obj;
        Object i = obj2;
        Object n = obj3;
        Object apply2 = Scheme.numEqu.apply2(i, Lit73);
        Object obj4 = apply2;
        try {
            boolean x = ((Boolean) apply2).booleanValue();
            if (x) {
                s = x ? Boolean.TRUE : Boolean.FALSE;
            } else {
                Object apply22 = Scheme.numGEq.apply2(i, n);
                Object obj5 = apply22;
                try {
                    boolean x2 = ((Boolean) apply22).booleanValue();
                    if (x2) {
                        s = x2 ? Boolean.TRUE : Boolean.FALSE;
                    } else {
                        Object obj6 = s2;
                        Object obj7 = obj6;
                        try {
                            CharSequence charSequence = (CharSequence) obj6;
                            Object obj8 = i;
                            Object obj9 = obj8;
                            try {
                                char c$Sli = strings.stringRef(charSequence, ((Number) obj8).intValue());
                                Object obj10 = s2;
                                Object obj11 = obj10;
                                try {
                                    CharSequence charSequence2 = (CharSequence) obj10;
                                    Object apply23 = AddOp.$Mn.apply2(i, Lit8);
                                    Object obj12 = apply23;
                                    try {
                                        char c$Sli$Mn1 = strings.stringRef(charSequence2, ((Number) apply23).intValue());
                                        Object isPregexpCheckIfInCharClass = isPregexpCheckIfInCharClass(Char.make(c$Sli), Lit41);
                                        Object c$Sli$Mn1$Slw$Qu = isPregexpCheckIfInCharClass(Char.make(c$Sli$Mn1), Lit41);
                                        Object c$Sli$Slw$Qu = isPregexpCheckIfInCharClass;
                                        Object x3 = c$Sli$Slw$Qu != Boolean.FALSE ? c$Sli$Mn1$Slw$Qu != Boolean.FALSE ? Boolean.FALSE : Boolean.TRUE : c$Sli$Slw$Qu;
                                        if (x3 != Boolean.FALSE) {
                                            s = x3;
                                        } else {
                                            Object obj13 = c$Sli$Slw$Qu;
                                            Object obj14 = obj13;
                                            try {
                                                boolean x4 = ((obj13 != Boolean.FALSE ? 1 : 0) + 1) & true;
                                                s = x4 ? c$Sli$Mn1$Slw$Qu : x4 ? Boolean.TRUE : Boolean.FALSE;
                                            } catch (ClassCastException e) {
                                                ClassCastException classCastException = e;
                                                Throwable th8 = th7;
                                                new WrongType(classCastException, "x", -2, obj14);
                                                throw th8;
                                            }
                                        }
                                    } catch (ClassCastException e2) {
                                        ClassCastException classCastException2 = e2;
                                        Throwable th9 = th6;
                                        new WrongType(classCastException2, "string-ref", 2, obj12);
                                        throw th9;
                                    }
                                } catch (ClassCastException e3) {
                                    ClassCastException classCastException3 = e3;
                                    Throwable th10 = th5;
                                    new WrongType(classCastException3, "string-ref", 1, obj11);
                                    throw th10;
                                }
                            } catch (ClassCastException e4) {
                                ClassCastException classCastException4 = e4;
                                Throwable th11 = th4;
                                new WrongType(classCastException4, "string-ref", 2, obj9);
                                throw th11;
                            }
                        } catch (ClassCastException e5) {
                            ClassCastException classCastException5 = e5;
                            Throwable th12 = th3;
                            new WrongType(classCastException5, "string-ref", 1, obj7);
                            throw th12;
                        }
                    }
                } catch (ClassCastException e6) {
                    ClassCastException classCastException6 = e6;
                    Throwable th13 = th2;
                    new WrongType(classCastException6, "x", -2, obj5);
                    throw th13;
                }
            }
            return s;
        } catch (ClassCastException e7) {
            ClassCastException classCastException7 = e7;
            Throwable th14 = th;
            new WrongType(classCastException7, "x", -2, obj4);
            throw th14;
        }
    }

    public static Object isPregexpCheckIfInCharClass(Object obj, Object obj2) {
        Object c;
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
        Throwable th30;
        Object c2 = obj;
        Object char$Mnclass = obj2;
        if (Scheme.isEqv.apply2(char$Mnclass, Lit14) != Boolean.FALSE) {
            Object obj3 = c2;
            Object obj4 = obj3;
            try {
                c = characters.isChar$Eq((Char) obj3, Lit24) ? Boolean.FALSE : Boolean.TRUE;
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th31 = th30;
                new WrongType(classCastException, "char=?", 1, obj4);
                throw th31;
            }
        } else if (Scheme.isEqv.apply2(char$Mnclass, Lit85) != Boolean.FALSE) {
            Object obj5 = c2;
            Object obj6 = obj5;
            try {
                boolean x = unicode.isCharAlphabetic((Char) obj5);
                if (x) {
                    c = x ? Boolean.TRUE : Boolean.FALSE;
                } else {
                    Object obj7 = c2;
                    Object obj8 = obj7;
                    try {
                        c = unicode.isCharNumeric((Char) obj7) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e2) {
                        ClassCastException classCastException2 = e2;
                        Throwable th32 = th29;
                        new WrongType(classCastException2, "char-numeric?", 1, obj8);
                        throw th32;
                    }
                }
            } catch (ClassCastException e3) {
                ClassCastException classCastException3 = e3;
                Throwable th33 = th28;
                new WrongType(classCastException3, "char-alphabetic?", 1, obj6);
                throw th33;
            }
        } else if (Scheme.isEqv.apply2(char$Mnclass, Lit86) != Boolean.FALSE) {
            Object obj9 = c2;
            Object obj10 = obj9;
            try {
                c = unicode.isCharAlphabetic((Char) obj9) ? Boolean.TRUE : Boolean.FALSE;
            } catch (ClassCastException e4) {
                ClassCastException classCastException4 = e4;
                Throwable th34 = th27;
                new WrongType(classCastException4, "char-alphabetic?", 1, obj10);
                throw th34;
            }
        } else if (Scheme.isEqv.apply2(char$Mnclass, Lit87) != Boolean.FALSE) {
            Object obj11 = c2;
            Object obj12 = obj11;
            try {
                c = characters.char$To$Integer((Char) obj11) < 128 ? Boolean.TRUE : Boolean.FALSE;
            } catch (ClassCastException e5) {
                ClassCastException classCastException5 = e5;
                Throwable th35 = th26;
                new WrongType(classCastException5, "char->integer", 1, obj12);
                throw th35;
            }
        } else if (Scheme.isEqv.apply2(char$Mnclass, Lit88) != Boolean.FALSE) {
            Object obj13 = c2;
            Object obj14 = obj13;
            try {
                boolean x2 = characters.isChar$Eq((Char) obj13, Lit3);
                if (x2) {
                    c = x2 ? Boolean.TRUE : Boolean.FALSE;
                } else {
                    Object obj15 = c2;
                    Object obj16 = obj15;
                    try {
                        Char charR = (Char) obj15;
                        Object obj17 = $Stpregexp$Mntab$Mnchar$St;
                        Object obj18 = obj17;
                        try {
                            c = characters.isChar$Eq(charR, (Char) obj17) ? Boolean.TRUE : Boolean.FALSE;
                        } catch (ClassCastException e6) {
                            ClassCastException classCastException6 = e6;
                            Throwable th36 = th25;
                            new WrongType(classCastException6, "char=?", 2, obj18);
                            throw th36;
                        }
                    } catch (ClassCastException e7) {
                        ClassCastException classCastException7 = e7;
                        Throwable th37 = th24;
                        new WrongType(classCastException7, "char=?", 1, obj16);
                        throw th37;
                    }
                }
            } catch (ClassCastException e8) {
                ClassCastException classCastException8 = e8;
                Throwable th38 = th23;
                new WrongType(classCastException8, "char=?", 1, obj14);
                throw th38;
            }
        } else if (Scheme.isEqv.apply2(char$Mnclass, Lit89) != Boolean.FALSE) {
            Object obj19 = c2;
            Object obj20 = obj19;
            try {
                c = characters.char$To$Integer((Char) obj19) < 32 ? Boolean.TRUE : Boolean.FALSE;
            } catch (ClassCastException e9) {
                ClassCastException classCastException9 = e9;
                Throwable th39 = th22;
                new WrongType(classCastException9, "char->integer", 1, obj20);
                throw th39;
            }
        } else if (Scheme.isEqv.apply2(char$Mnclass, Lit30) != Boolean.FALSE) {
            Object obj21 = c2;
            Object obj22 = obj21;
            try {
                c = unicode.isCharNumeric((Char) obj21) ? Boolean.TRUE : Boolean.FALSE;
            } catch (ClassCastException e10) {
                ClassCastException classCastException10 = e10;
                Throwable th40 = th21;
                new WrongType(classCastException10, "char-numeric?", 1, obj22);
                throw th40;
            }
        } else if (Scheme.isEqv.apply2(char$Mnclass, Lit90) != Boolean.FALSE) {
            Object obj23 = c2;
            Object obj24 = obj23;
            try {
                boolean x3 = characters.char$To$Integer((Char) obj23) >= 32;
                if (x3) {
                    Object obj25 = c2;
                    Object obj26 = obj25;
                    try {
                        c = unicode.isCharWhitespace((Char) obj25) ? Boolean.FALSE : Boolean.TRUE;
                    } catch (ClassCastException e11) {
                        ClassCastException classCastException11 = e11;
                        Throwable th41 = th20;
                        new WrongType(classCastException11, "char-whitespace?", 1, obj26);
                        throw th41;
                    }
                } else {
                    c = x3 ? Boolean.TRUE : Boolean.FALSE;
                }
            } catch (ClassCastException e12) {
                ClassCastException classCastException12 = e12;
                Throwable th42 = th19;
                new WrongType(classCastException12, "char->integer", 1, obj24);
                throw th42;
            }
        } else if (Scheme.isEqv.apply2(char$Mnclass, Lit91) != Boolean.FALSE) {
            Object obj27 = c2;
            Object obj28 = obj27;
            try {
                c = unicode.isCharLowerCase((Char) obj27) ? Boolean.TRUE : Boolean.FALSE;
            } catch (ClassCastException e13) {
                ClassCastException classCastException13 = e13;
                Throwable th43 = th18;
                new WrongType(classCastException13, "char-lower-case?", 1, obj28);
                throw th43;
            }
        } else if (Scheme.isEqv.apply2(char$Mnclass, Lit92) != Boolean.FALSE) {
            Object obj29 = c2;
            Object obj30 = obj29;
            try {
                c = characters.char$To$Integer((Char) obj29) >= 32 ? Boolean.TRUE : Boolean.FALSE;
            } catch (ClassCastException e14) {
                ClassCastException classCastException14 = e14;
                Throwable th44 = th17;
                new WrongType(classCastException14, "char->integer", 1, obj30);
                throw th44;
            }
        } else if (Scheme.isEqv.apply2(char$Mnclass, Lit93) != Boolean.FALSE) {
            Object obj31 = c2;
            Object obj32 = obj31;
            try {
                boolean x4 = characters.char$To$Integer((Char) obj31) >= 32;
                if (x4) {
                    Object obj33 = c2;
                    Object obj34 = obj33;
                    try {
                        boolean x5 = ((unicode.isCharWhitespace((Char) obj33) ? 1 : 0) + true) & true;
                        if (x5) {
                            Object obj35 = c2;
                            Object obj36 = obj35;
                            try {
                                boolean x6 = ((unicode.isCharAlphabetic((Char) obj35) ? 1 : 0) + true) & true;
                                if (x6) {
                                    Object obj37 = c2;
                                    Object obj38 = obj37;
                                    try {
                                        c = unicode.isCharNumeric((Char) obj37) ? Boolean.FALSE : Boolean.TRUE;
                                    } catch (ClassCastException e15) {
                                        ClassCastException classCastException15 = e15;
                                        Throwable th45 = th16;
                                        new WrongType(classCastException15, "char-numeric?", 1, obj38);
                                        throw th45;
                                    }
                                } else {
                                    c = x6 ? Boolean.TRUE : Boolean.FALSE;
                                }
                            } catch (ClassCastException e16) {
                                ClassCastException classCastException16 = e16;
                                Throwable th46 = th15;
                                new WrongType(classCastException16, "char-alphabetic?", 1, obj36);
                                throw th46;
                            }
                        } else {
                            c = x5 ? Boolean.TRUE : Boolean.FALSE;
                        }
                    } catch (ClassCastException e17) {
                        ClassCastException classCastException17 = e17;
                        Throwable th47 = th14;
                        new WrongType(classCastException17, "char-whitespace?", 1, obj34);
                        throw th47;
                    }
                } else {
                    c = x4 ? Boolean.TRUE : Boolean.FALSE;
                }
            } catch (ClassCastException e18) {
                ClassCastException classCastException18 = e18;
                Throwable th48 = th13;
                new WrongType(classCastException18, "char->integer", 1, obj32);
                throw th48;
            }
        } else if (Scheme.isEqv.apply2(char$Mnclass, Lit36) != Boolean.FALSE) {
            Object obj39 = c2;
            Object obj40 = obj39;
            try {
                c = unicode.isCharWhitespace((Char) obj39) ? Boolean.TRUE : Boolean.FALSE;
            } catch (ClassCastException e19) {
                ClassCastException classCastException19 = e19;
                Throwable th49 = th12;
                new WrongType(classCastException19, "char-whitespace?", 1, obj40);
                throw th49;
            }
        } else if (Scheme.isEqv.apply2(char$Mnclass, Lit94) != Boolean.FALSE) {
            Object obj41 = c2;
            Object obj42 = obj41;
            try {
                c = unicode.isCharUpperCase((Char) obj41) ? Boolean.TRUE : Boolean.FALSE;
            } catch (ClassCastException e20) {
                ClassCastException classCastException20 = e20;
                Throwable th50 = th11;
                new WrongType(classCastException20, "char-upper-case?", 1, obj42);
                throw th50;
            }
        } else if (Scheme.isEqv.apply2(char$Mnclass, Lit41) != Boolean.FALSE) {
            Object obj43 = c2;
            Object obj44 = obj43;
            try {
                boolean x7 = unicode.isCharAlphabetic((Char) obj43);
                if (x7) {
                    c = x7 ? Boolean.TRUE : Boolean.FALSE;
                } else {
                    Object obj45 = c2;
                    Object obj46 = obj45;
                    try {
                        boolean x8 = unicode.isCharNumeric((Char) obj45);
                        if (x8) {
                            c = x8 ? Boolean.TRUE : Boolean.FALSE;
                        } else {
                            Object obj47 = c2;
                            Object obj48 = obj47;
                            try {
                                c = characters.isChar$Eq((Char) obj47, Lit84) ? Boolean.TRUE : Boolean.FALSE;
                            } catch (ClassCastException e21) {
                                ClassCastException classCastException21 = e21;
                                Throwable th51 = th10;
                                new WrongType(classCastException21, "char=?", 1, obj48);
                                throw th51;
                            }
                        }
                    } catch (ClassCastException e22) {
                        ClassCastException classCastException22 = e22;
                        Throwable th52 = th9;
                        new WrongType(classCastException22, "char-numeric?", 1, obj46);
                        throw th52;
                    }
                }
            } catch (ClassCastException e23) {
                ClassCastException classCastException23 = e23;
                Throwable th53 = th8;
                new WrongType(classCastException23, "char-alphabetic?", 1, obj44);
                throw th53;
            }
        } else if (Scheme.isEqv.apply2(char$Mnclass, Lit95) != Boolean.FALSE) {
            Object obj49 = c2;
            Object obj50 = obj49;
            try {
                boolean x9 = unicode.isCharNumeric((Char) obj49);
                if (x9) {
                    c = x9 ? Boolean.TRUE : Boolean.FALSE;
                } else {
                    Object obj51 = c2;
                    Object obj52 = obj51;
                    try {
                        boolean x10 = unicode.isCharCi$Eq((Char) obj51, Lit2);
                        if (x10) {
                            c = x10 ? Boolean.TRUE : Boolean.FALSE;
                        } else {
                            Object obj53 = c2;
                            Object obj54 = obj53;
                            try {
                                boolean x11 = unicode.isCharCi$Eq((Char) obj53, Lit25);
                                if (x11) {
                                    c = x11 ? Boolean.TRUE : Boolean.FALSE;
                                } else {
                                    Object obj55 = c2;
                                    Object obj56 = obj55;
                                    try {
                                        boolean x12 = unicode.isCharCi$Eq((Char) obj55, Lit96);
                                        if (x12) {
                                            c = x12 ? Boolean.TRUE : Boolean.FALSE;
                                        } else {
                                            Object obj57 = c2;
                                            Object obj58 = obj57;
                                            try {
                                                boolean x13 = unicode.isCharCi$Eq((Char) obj57, Lit29);
                                                if (x13) {
                                                    c = x13 ? Boolean.TRUE : Boolean.FALSE;
                                                } else {
                                                    Object obj59 = c2;
                                                    Object obj60 = obj59;
                                                    try {
                                                        boolean x14 = unicode.isCharCi$Eq((Char) obj59, Lit97);
                                                        if (x14) {
                                                            c = x14 ? Boolean.TRUE : Boolean.FALSE;
                                                        } else {
                                                            Object obj61 = c2;
                                                            Object obj62 = obj61;
                                                            try {
                                                                c = unicode.isCharCi$Eq((Char) obj61, Lit98) ? Boolean.TRUE : Boolean.FALSE;
                                                            } catch (ClassCastException e24) {
                                                                ClassCastException classCastException24 = e24;
                                                                Throwable th54 = th7;
                                                                new WrongType(classCastException24, "char-ci=?", 1, obj62);
                                                                throw th54;
                                                            }
                                                        }
                                                    } catch (ClassCastException e25) {
                                                        ClassCastException classCastException25 = e25;
                                                        Throwable th55 = th6;
                                                        new WrongType(classCastException25, "char-ci=?", 1, obj60);
                                                        throw th55;
                                                    }
                                                }
                                            } catch (ClassCastException e26) {
                                                ClassCastException classCastException26 = e26;
                                                Throwable th56 = th5;
                                                new WrongType(classCastException26, "char-ci=?", 1, obj58);
                                                throw th56;
                                            }
                                        }
                                    } catch (ClassCastException e27) {
                                        ClassCastException classCastException27 = e27;
                                        Throwable th57 = th4;
                                        new WrongType(classCastException27, "char-ci=?", 1, obj56);
                                        throw th57;
                                    }
                                }
                            } catch (ClassCastException e28) {
                                ClassCastException classCastException28 = e28;
                                Throwable th58 = th3;
                                new WrongType(classCastException28, "char-ci=?", 1, obj54);
                                throw th58;
                            }
                        }
                    } catch (ClassCastException e29) {
                        ClassCastException classCastException29 = e29;
                        Throwable th59 = th2;
                        new WrongType(classCastException29, "char-ci=?", 1, obj52);
                        throw th59;
                    }
                }
            } catch (ClassCastException e30) {
                ClassCastException classCastException30 = e30;
                Throwable th60 = th;
                new WrongType(classCastException30, "char-numeric?", 1, obj50);
                throw th60;
            }
        } else {
            c = pregexpError$V(new Object[]{Lit99});
        }
        return c;
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 33:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 34:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 47:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            default:
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
        }
    }

    public static Object pregexpListRef(Object s, Object obj) {
        Object obj2;
        Object i = obj;
        Object obj3 = s;
        Object obj4 = Lit73;
        while (true) {
            Object obj5 = obj4;
            Object s2 = obj3;
            if (C1245lists.isNull(s2)) {
                obj2 = Boolean.FALSE;
                break;
            } else if (Scheme.numEqu.apply2(obj5, i) != Boolean.FALSE) {
                obj2 = C1245lists.car.apply1(s2);
                break;
            } else {
                obj3 = C1245lists.cdr.apply1(s2);
                obj4 = AddOp.$Pl.apply2(obj5, Lit8);
            }
        }
        return obj2;
    }

    public static Object pregexpMakeBackrefList(Object re) {
        return lambda1sub(re);
    }

    public static Object lambda1sub(Object obj) {
        Object re;
        Object re2 = obj;
        if (C1245lists.isPair(re2)) {
            Object apply1 = C1245lists.car.apply1(re2);
            Object sub$Mncdr$Mnre = lambda1sub(C1245lists.cdr.apply1(re2));
            Object car$Mnre = apply1;
            if (Scheme.isEqv.apply2(car$Mnre, Lit100) != Boolean.FALSE) {
                re = C1245lists.cons(C1245lists.cons(re2, Boolean.FALSE), sub$Mncdr$Mnre);
            } else {
                Object[] objArr = new Object[2];
                objArr[0] = lambda1sub(car$Mnre);
                Object[] objArr2 = objArr;
                objArr2[1] = sub$Mncdr$Mnre;
                re = append.append$V(objArr2);
            }
        } else {
            re = LList.Empty;
        }
        return re;
    }

    /* compiled from: pregexp.scm */
    public class frame extends ModuleBody {
        Object backrefs;
        Object case$Mnsensitive$Qu;
        Procedure identity;

        /* renamed from: n */
        Object f97n;

        /* renamed from: s */
        Object f98s;

        /* renamed from: sn */
        Object f99sn;
        Object start;

        public frame() {
            Procedure procedure;
            new ModuleMethod(this, 15, pregexp.Lit112, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            Procedure procedure2 = procedure;
            procedure2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:460");
            this.identity = procedure2;
        }

        public static Object lambda2identity(Object x) {
            return x;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector == 15) {
                return lambda2identity(obj2);
            }
            return super.apply1(moduleMethod2, obj2);
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

        static Boolean lambda4() {
            return Boolean.FALSE;
        }

        public Object lambda3sub(Object re, Object i, Object sk, Object fk) {
            frame0 frame0;
            boolean x;
            Object apply1;
            Object x2;
            Throwable th;
            boolean z;
            Throwable th2;
            int i2;
            Object obj;
            Throwable th3;
            Throwable th4;
            Throwable th5;
            Throwable th6;
            Throwable th7;
            Object x3;
            Throwable th8;
            Throwable th9;
            Throwable th10;
            Throwable th11;
            new frame0();
            frame0 frame02 = frame0;
            frame02.staticLink = this;
            frame0 frame03 = frame02;
            frame03.re$1 = re;
            frame03.f101i = i;
            frame03.f105sk = sk;
            frame03.f100fk = fk;
            if (Scheme.isEqv.apply2(frame03.re$1, pregexp.Lit10) != Boolean.FALSE) {
                apply1 = Scheme.numEqu.apply2(frame03.f101i, this.start) != Boolean.FALSE ? Scheme.applyToArgs.apply2(frame03.f105sk, frame03.f101i) : Scheme.applyToArgs.apply1(frame03.f100fk);
            } else if (Scheme.isEqv.apply2(frame03.re$1, pregexp.Lit12) != Boolean.FALSE) {
                apply1 = Scheme.numGEq.apply2(frame03.f101i, this.f97n) != Boolean.FALSE ? Scheme.applyToArgs.apply2(frame03.f105sk, frame03.f101i) : Scheme.applyToArgs.apply1(frame03.f100fk);
            } else if (Scheme.isEqv.apply2(frame03.re$1, pregexp.Lit23) != Boolean.FALSE) {
                apply1 = Scheme.applyToArgs.apply2(frame03.f105sk, frame03.f101i);
            } else if (Scheme.isEqv.apply2(frame03.re$1, pregexp.Lit26) != Boolean.FALSE) {
                apply1 = pregexp.isPregexpAtWordBoundary(this.f98s, frame03.f101i, this.f97n) != Boolean.FALSE ? Scheme.applyToArgs.apply2(frame03.f105sk, frame03.f101i) : Scheme.applyToArgs.apply1(frame03.f100fk);
            } else if (Scheme.isEqv.apply2(frame03.re$1, pregexp.Lit28) != Boolean.FALSE) {
                apply1 = pregexp.isPregexpAtWordBoundary(this.f98s, frame03.f101i, this.f97n) != Boolean.FALSE ? Scheme.applyToArgs.apply1(frame03.f100fk) : Scheme.applyToArgs.apply2(frame03.f105sk, frame03.f101i);
            } else {
                if (!(x = characters.isChar(frame03.re$1)) ? !x : Scheme.numLss.apply2(frame03.f101i, this.f97n) == Boolean.FALSE) {
                    boolean x4 = ((C1245lists.isPair(frame03.re$1) ? 1 : 0) + true) & true;
                    if (!x4 ? !x4 : Scheme.numLss.apply2(frame03.f101i, this.f97n) == Boolean.FALSE) {
                        boolean x5 = C1245lists.isPair(frame03.re$1);
                        if (!x5 ? x5 : !((x3 = Scheme.isEqv.apply2(C1245lists.car.apply1(frame03.re$1), pregexp.Lit83)) == Boolean.FALSE ? x3 == Boolean.FALSE : Scheme.numLss.apply2(frame03.f101i, this.f97n) == Boolean.FALSE)) {
                            Object obj2 = this.f98s;
                            Object obj3 = obj2;
                            try {
                                CharSequence charSequence = (CharSequence) obj2;
                                Object obj4 = frame03.f101i;
                                Object obj5 = obj4;
                                try {
                                    char c = strings.stringRef(charSequence, ((Number) obj4).intValue());
                                    ModuleMethod c$Ls = this.case$Mnsensitive$Qu != Boolean.FALSE ? characters.char$Ls$Eq$Qu : unicode.char$Mnci$Ls$Eq$Qu;
                                    Object x6 = c$Ls.apply2(C1245lists.cadr.apply1(frame03.re$1), Char.make(c));
                                    apply1 = (x6 == Boolean.FALSE ? x6 == Boolean.FALSE : c$Ls.apply2(Char.make(c), C1245lists.caddr.apply1(frame03.re$1)) == Boolean.FALSE) ? Scheme.applyToArgs.apply1(frame03.f100fk) : Scheme.applyToArgs.apply2(frame03.f105sk, AddOp.$Pl.apply2(frame03.f101i, pregexp.Lit8));
                                } catch (ClassCastException e) {
                                    ClassCastException classCastException = e;
                                    Throwable th12 = th7;
                                    new WrongType(classCastException, "string-ref", 2, obj5);
                                    throw th12;
                                }
                            } catch (ClassCastException e2) {
                                ClassCastException classCastException2 = e2;
                                Throwable th13 = th6;
                                new WrongType(classCastException2, "string-ref", 1, obj3);
                                throw th13;
                            }
                        } else if (C1245lists.isPair(frame03.re$1)) {
                            Object tmp = C1245lists.car.apply1(frame03.re$1);
                            if (Scheme.isEqv.apply2(tmp, pregexp.Lit83) != Boolean.FALSE) {
                                apply1 = Scheme.numGEq.apply2(frame03.f101i, this.f97n) != Boolean.FALSE ? Scheme.applyToArgs.apply1(frame03.f100fk) : pregexp.pregexpError$V(new Object[]{pregexp.Lit101});
                            } else if (Scheme.isEqv.apply2(tmp, pregexp.Lit82) != Boolean.FALSE) {
                                apply1 = Scheme.numGEq.apply2(frame03.f101i, this.f97n) != Boolean.FALSE ? Scheme.applyToArgs.apply1(frame03.f100fk) : frame03.lambda5loupOneOfChars(C1245lists.cdr.apply1(frame03.re$1));
                            } else if (Scheme.isEqv.apply2(tmp, pregexp.Lit17) != Boolean.FALSE) {
                                apply1 = Scheme.numGEq.apply2(frame03.f101i, this.f97n) != Boolean.FALSE ? Scheme.applyToArgs.apply1(frame03.f100fk) : lambda3sub(C1245lists.cadr.apply1(frame03.re$1), frame03.f101i, frame03.lambda$Fn2, frame03.lambda$Fn3);
                            } else if (Scheme.isEqv.apply2(tmp, pregexp.Lit5) != Boolean.FALSE) {
                                apply1 = frame03.lambda6loupSeq(C1245lists.cdr.apply1(frame03.re$1), frame03.f101i);
                            } else if (Scheme.isEqv.apply2(tmp, pregexp.Lit4) != Boolean.FALSE) {
                                apply1 = frame03.lambda7loupOr(C1245lists.cdr.apply1(frame03.re$1));
                            } else if (Scheme.isEqv.apply2(tmp, pregexp.Lit20) != Boolean.FALSE) {
                                Object c2 = pregexp.pregexpListRef(this.backrefs, C1245lists.cadr.apply1(frame03.re$1));
                                if (c2 != Boolean.FALSE) {
                                    obj = C1245lists.cdr.apply1(c2);
                                } else {
                                    Object[] objArr = new Object[3];
                                    objArr[0] = pregexp.Lit101;
                                    Object[] objArr2 = objArr;
                                    objArr2[1] = pregexp.Lit102;
                                    Object[] objArr3 = objArr2;
                                    objArr3[2] = frame03.re$1;
                                    Object pregexpError$V = pregexp.pregexpError$V(objArr3);
                                    obj = Boolean.FALSE;
                                }
                                Object backref = obj;
                                if (backref != Boolean.FALSE) {
                                    Object obj6 = this.f98s;
                                    Object obj7 = obj6;
                                    try {
                                        CharSequence charSequence2 = (CharSequence) obj6;
                                        Object apply12 = C1245lists.car.apply1(backref);
                                        Object obj8 = apply12;
                                        try {
                                            int intValue = ((Number) apply12).intValue();
                                            Object apply13 = C1245lists.cdr.apply1(backref);
                                            Object obj9 = apply13;
                                            try {
                                                apply1 = pregexp.pregexpStringMatch(strings.substring(charSequence2, intValue, ((Number) apply13).intValue()), this.f98s, frame03.f101i, this.f97n, frame03.lambda$Fn4, frame03.f100fk);
                                            } catch (ClassCastException e3) {
                                                ClassCastException classCastException3 = e3;
                                                Throwable th14 = th5;
                                                new WrongType(classCastException3, "substring", 3, obj9);
                                                throw th14;
                                            }
                                        } catch (ClassCastException e4) {
                                            ClassCastException classCastException4 = e4;
                                            Throwable th15 = th4;
                                            new WrongType(classCastException4, "substring", 2, obj8);
                                            throw th15;
                                        }
                                    } catch (ClassCastException e5) {
                                        ClassCastException classCastException5 = e5;
                                        Throwable th16 = th3;
                                        new WrongType(classCastException5, "substring", 1, obj7);
                                        throw th16;
                                    }
                                } else {
                                    apply1 = Scheme.applyToArgs.apply2(frame03.f105sk, frame03.f101i);
                                }
                            } else if (Scheme.isEqv.apply2(tmp, pregexp.Lit100) != Boolean.FALSE) {
                                apply1 = lambda3sub(C1245lists.cadr.apply1(frame03.re$1), frame03.f101i, frame03.lambda$Fn5, frame03.f100fk);
                            } else if (Scheme.isEqv.apply2(tmp, pregexp.Lit103) != Boolean.FALSE) {
                                apply1 = lambda3sub(C1245lists.cadr.apply1(frame03.re$1), frame03.f101i, this.identity, pregexp.lambda$Fn6) != Boolean.FALSE ? Scheme.applyToArgs.apply2(frame03.f105sk, frame03.f101i) : Scheme.applyToArgs.apply1(frame03.f100fk);
                            } else if (Scheme.isEqv.apply2(tmp, pregexp.Lit104) != Boolean.FALSE) {
                                apply1 = lambda3sub(C1245lists.cadr.apply1(frame03.re$1), frame03.f101i, this.identity, pregexp.lambda$Fn7) != Boolean.FALSE ? Scheme.applyToArgs.apply1(frame03.f100fk) : Scheme.applyToArgs.apply2(frame03.f105sk, frame03.f101i);
                            } else if (Scheme.isEqv.apply2(tmp, pregexp.Lit105) != Boolean.FALSE) {
                                Object n$Mnactual = this.f97n;
                                Object sn$Mnactual = this.f99sn;
                                this.f97n = frame03.f101i;
                                this.f99sn = frame03.f101i;
                                Object found$Mnit$Qu = lambda3sub(LList.list4(pregexp.Lit5, pregexp.Lit106, C1245lists.cadr.apply1(frame03.re$1), pregexp.Lit12), pregexp.Lit73, this.identity, pregexp.lambda$Fn8);
                                this.f97n = n$Mnactual;
                                this.f99sn = sn$Mnactual;
                                apply1 = found$Mnit$Qu != Boolean.FALSE ? Scheme.applyToArgs.apply2(frame03.f105sk, frame03.f101i) : Scheme.applyToArgs.apply1(frame03.f100fk);
                            } else if (Scheme.isEqv.apply2(tmp, pregexp.Lit107) != Boolean.FALSE) {
                                Object n$Mnactual2 = this.f97n;
                                Object sn$Mnactual2 = this.f99sn;
                                this.f97n = frame03.f101i;
                                this.f99sn = frame03.f101i;
                                Object found$Mnit$Qu2 = lambda3sub(LList.list4(pregexp.Lit5, pregexp.Lit108, C1245lists.cadr.apply1(frame03.re$1), pregexp.Lit12), pregexp.Lit73, this.identity, pregexp.lambda$Fn9);
                                this.f97n = n$Mnactual2;
                                this.f99sn = sn$Mnactual2;
                                apply1 = found$Mnit$Qu2 != Boolean.FALSE ? Scheme.applyToArgs.apply1(frame03.f100fk) : Scheme.applyToArgs.apply2(frame03.f105sk, frame03.f101i);
                            } else if (Scheme.isEqv.apply2(tmp, pregexp.Lit109) != Boolean.FALSE) {
                                Object found$Mnit$Qu3 = lambda3sub(C1245lists.cadr.apply1(frame03.re$1), frame03.f101i, this.identity, pregexp.lambda$Fn10);
                                apply1 = found$Mnit$Qu3 != Boolean.FALSE ? Scheme.applyToArgs.apply2(frame03.f105sk, found$Mnit$Qu3) : Scheme.applyToArgs.apply1(frame03.f100fk);
                            } else {
                                if ((x2 = Scheme.isEqv.apply2(tmp, pregexp.Lit60)) == Boolean.FALSE ? Scheme.isEqv.apply2(tmp, pregexp.Lit61) != Boolean.FALSE : x2 != Boolean.FALSE) {
                                    frame03.old = this.case$Mnsensitive$Qu;
                                    this.case$Mnsensitive$Qu = Scheme.isEqv.apply2(C1245lists.car.apply1(frame03.re$1), pregexp.Lit60);
                                    apply1 = lambda3sub(C1245lists.cadr.apply1(frame03.re$1), frame03.f101i, frame03.lambda$Fn11, frame03.lambda$Fn12);
                                } else if (Scheme.isEqv.apply2(tmp, pregexp.Lit68) != Boolean.FALSE) {
                                    Object apply14 = C1245lists.cadr.apply1(frame03.re$1);
                                    Object x7 = apply14;
                                    try {
                                        frame03.maximal$Qu = ((apply14 != Boolean.FALSE ? 1 : 0) + 1) & true;
                                        frame03.f102p = C1245lists.caddr.apply1(frame03.re$1);
                                        frame03.f103q = C1245lists.cadddr.apply1(frame03.re$1);
                                        if (frame03.maximal$Qu) {
                                            Object obj10 = frame03.f103q;
                                            Object obj11 = obj10;
                                            try {
                                                if (obj10 != Boolean.FALSE) {
                                                    i2 = 1;
                                                } else {
                                                    i2 = 0;
                                                }
                                                z = (i2 + 1) & true;
                                            } catch (ClassCastException e6) {
                                                ClassCastException classCastException6 = e6;
                                                Throwable th17 = th2;
                                                new WrongType(classCastException6, "could-loop-infinitely?", -2, obj11);
                                                throw th17;
                                            }
                                        } else {
                                            z = frame03.maximal$Qu;
                                        }
                                        frame03.could$Mnloop$Mninfinitely$Qu = z;
                                        frame03.f104re = C1245lists.car.apply1(C1245lists.cddddr.apply1(frame03.re$1));
                                        apply1 = frame03.lambda8loupP(pregexp.Lit73, frame03.f101i);
                                    } catch (ClassCastException e7) {
                                        ClassCastException classCastException7 = e7;
                                        Throwable th18 = th;
                                        new WrongType(classCastException7, "maximal?", -2, x7);
                                        throw th18;
                                    }
                                } else {
                                    apply1 = pregexp.pregexpError$V(new Object[]{pregexp.Lit101});
                                }
                            }
                        } else {
                            apply1 = Scheme.numGEq.apply2(frame03.f101i, this.f97n) != Boolean.FALSE ? Scheme.applyToArgs.apply1(frame03.f100fk) : pregexp.pregexpError$V(new Object[]{pregexp.Lit101});
                        }
                    } else {
                        Object obj12 = this.f98s;
                        Object obj13 = obj12;
                        try {
                            CharSequence charSequence3 = (CharSequence) obj12;
                            Object obj14 = frame03.f101i;
                            Object obj15 = obj14;
                            try {
                                apply1 = pregexp.isPregexpCheckIfInCharClass(Char.make(strings.stringRef(charSequence3, ((Number) obj14).intValue())), frame03.re$1) != Boolean.FALSE ? Scheme.applyToArgs.apply2(frame03.f105sk, AddOp.$Pl.apply2(frame03.f101i, pregexp.Lit8)) : Scheme.applyToArgs.apply1(frame03.f100fk);
                            } catch (ClassCastException e8) {
                                ClassCastException classCastException8 = e8;
                                Throwable th19 = th9;
                                new WrongType(classCastException8, "string-ref", 2, obj15);
                                throw th19;
                            }
                        } catch (ClassCastException e9) {
                            ClassCastException classCastException9 = e9;
                            Throwable th20 = th8;
                            new WrongType(classCastException9, "string-ref", 1, obj13);
                            throw th20;
                        }
                    }
                } else {
                    ModuleMethod moduleMethod = this.case$Mnsensitive$Qu != Boolean.FALSE ? characters.char$Eq$Qu : unicode.char$Mnci$Eq$Qu;
                    Object obj16 = this.f98s;
                    Object obj17 = obj16;
                    try {
                        CharSequence charSequence4 = (CharSequence) obj16;
                        Object obj18 = frame03.f101i;
                        Object obj19 = obj18;
                        try {
                            apply1 = moduleMethod.apply2(Char.make(strings.stringRef(charSequence4, ((Number) obj18).intValue())), frame03.re$1) != Boolean.FALSE ? Scheme.applyToArgs.apply2(frame03.f105sk, AddOp.$Pl.apply2(frame03.f101i, pregexp.Lit8)) : Scheme.applyToArgs.apply1(frame03.f100fk);
                        } catch (ClassCastException e10) {
                            ClassCastException classCastException10 = e10;
                            Throwable th21 = th11;
                            new WrongType(classCastException10, "string-ref", 2, obj19);
                            throw th21;
                        }
                    } catch (ClassCastException e11) {
                        ClassCastException classCastException11 = e11;
                        Throwable th22 = th10;
                        new WrongType(classCastException11, "string-ref", 1, obj17);
                        throw th22;
                    }
                }
            }
            return apply1;
        }
    }

    public static Object pregexpMatchPositionsAux(Object obj, Object s, Object sn, Object start, Object n, Object i) {
        frame frame6;
        Object obj2;
        Throwable th;
        Object re = obj;
        new frame();
        frame frame7 = frame6;
        frame7.f98s = s;
        frame7.f99sn = sn;
        frame7.start = start;
        frame7.f97n = n;
        Procedure procedure = frame7.identity;
        Object pregexpMakeBackrefList = pregexpMakeBackrefList(re);
        frame7.case$Mnsensitive$Qu = Boolean.TRUE;
        frame7.backrefs = pregexpMakeBackrefList;
        frame7.identity = procedure;
        Object lambda3sub = frame7.lambda3sub(re, i, frame7.identity, lambda$Fn1);
        Object obj3 = frame7.backrefs;
        Object obj4 = LList.Empty;
        while (true) {
            obj2 = obj4;
            Object arg0 = obj3;
            if (arg0 == LList.Empty) {
                break;
            }
            Object obj5 = arg0;
            Object obj6 = obj5;
            try {
                Pair arg02 = (Pair) obj5;
                obj3 = arg02.getCdr();
                obj4 = Pair.make(C1245lists.cdr.apply1(arg02.getCar()), obj2);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "arg0", -2, obj6);
                throw th2;
            }
        }
        LList backrefs = LList.reverseInPlace(obj2);
        Object x = C1245lists.car.apply1(backrefs);
        return x != Boolean.FALSE ? backrefs : x;
    }

    public int match0(ModuleMethod moduleMethod, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 36:
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 0;
                return 0;
            case 37:
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 0;
                return 0;
            case 38:
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 0;
                return 0;
            case 39:
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 0;
                return 0;
            case 40:
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 0;
                return 0;
            case 41:
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 0;
                return 0;
            default:
                return super.match0(moduleMethod2, callContext2);
        }
    }

    /* compiled from: pregexp.scm */
    public class frame0 extends ModuleBody {
        boolean could$Mnloop$Mninfinitely$Qu;

        /* renamed from: fk */
        Object f100fk;

        /* renamed from: i */
        Object f101i;
        final ModuleMethod lambda$Fn11;
        final ModuleMethod lambda$Fn12;
        final ModuleMethod lambda$Fn2;
        final ModuleMethod lambda$Fn3;
        final ModuleMethod lambda$Fn4;
        final ModuleMethod lambda$Fn5;
        boolean maximal$Qu;
        Object old;

        /* renamed from: p */
        Object f102p;

        /* renamed from: q */
        Object f103q;

        /* renamed from: re */
        Object f104re;
        Object re$1;

        /* renamed from: sk */
        Object f105sk;
        frame staticLink;

        public frame0() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            ModuleMethod moduleMethod3;
            ModuleMethod moduleMethod4;
            ModuleMethod moduleMethod5;
            ModuleMethod moduleMethod6;
            new ModuleMethod(this, 9, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod7 = moduleMethod;
            moduleMethod7.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:513");
            this.lambda$Fn2 = moduleMethod7;
            new ModuleMethod(this, 10, (Object) null, 0);
            ModuleMethod moduleMethod8 = moduleMethod2;
            moduleMethod8.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:514");
            this.lambda$Fn3 = moduleMethod8;
            new ModuleMethod(this, 11, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod9 = moduleMethod3;
            moduleMethod9.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:541");
            this.lambda$Fn4 = moduleMethod9;
            new ModuleMethod(this, 12, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod10 = moduleMethod4;
            moduleMethod10.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:545");
            this.lambda$Fn5 = moduleMethod10;
            new ModuleMethod(this, 13, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod11 = moduleMethod5;
            moduleMethod11.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:587");
            this.lambda$Fn11 = moduleMethod11;
            new ModuleMethod(this, 14, (Object) null, 0);
            ModuleMethod moduleMethod12 = moduleMethod6;
            moduleMethod12.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:590");
            this.lambda$Fn12 = moduleMethod12;
        }

        public Object lambda5loupOneOfChars(Object chars) {
            frame1 frame1;
            Object lambda3sub;
            new frame1();
            frame1 frame12 = frame1;
            frame12.staticLink = this;
            frame1 frame13 = frame12;
            frame13.chars = chars;
            if (C1245lists.isNull(frame13.chars)) {
                lambda3sub = Scheme.applyToArgs.apply1(this.f100fk);
            } else {
                lambda3sub = this.staticLink.lambda3sub(C1245lists.car.apply1(frame13.chars), this.f101i, this.f105sk, frame13.lambda$Fn13);
            }
            return lambda3sub;
        }

        /* access modifiers changed from: package-private */
        public Object lambda9(Object obj) {
            Object obj2 = obj;
            return Scheme.applyToArgs.apply1(this.f100fk);
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            switch (moduleMethod2.selector) {
                case 9:
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

        /* access modifiers changed from: package-private */
        public Object lambda10() {
            return Scheme.applyToArgs.apply2(this.f105sk, AddOp.$Pl.apply2(this.f101i, pregexp.Lit8));
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            switch (moduleMethod2.selector) {
                case 10:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                case 14:
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 0;
                    return 0;
                default:
                    return super.match0(moduleMethod2, callContext2);
            }
        }

        public Object lambda6loupSeq(Object res, Object obj) {
            frame2 frame2;
            Object lambda3sub;
            Object i = obj;
            new frame2();
            frame2 frame22 = frame2;
            frame22.staticLink = this;
            frame2 frame23 = frame22;
            frame23.res = res;
            if (C1245lists.isNull(frame23.res)) {
                lambda3sub = Scheme.applyToArgs.apply2(this.f105sk, i);
            } else {
                lambda3sub = this.staticLink.lambda3sub(C1245lists.car.apply1(frame23.res), i, frame23.lambda$Fn14, this.f100fk);
            }
            return lambda3sub;
        }

        public Object lambda7loupOr(Object res) {
            frame3 frame3;
            Object lambda3sub;
            new frame3();
            frame3 frame32 = frame3;
            frame32.staticLink = this;
            frame3 frame33 = frame32;
            frame33.res = res;
            if (C1245lists.isNull(frame33.res)) {
                lambda3sub = Scheme.applyToArgs.apply1(this.f100fk);
            } else {
                lambda3sub = this.staticLink.lambda3sub(C1245lists.car.apply1(frame33.res), this.f101i, frame33.lambda$Fn15, frame33.lambda$Fn16);
            }
            return lambda3sub;
        }

        /* access modifiers changed from: package-private */
        public Object lambda11(Object i) {
            return Scheme.applyToArgs.apply2(this.f105sk, i);
        }

        /* access modifiers changed from: package-private */
        public Object lambda12(Object obj) {
            Throwable th;
            Object i1 = obj;
            Object assv = C1245lists.assv(this.re$1, this.staticLink.backrefs);
            Object obj2 = assv;
            try {
                C1245lists.setCdr$Ex((Pair) assv, C1245lists.cons(this.f101i, i1));
                return Scheme.applyToArgs.apply2(this.f105sk, i1);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "set-cdr!", 1, obj2);
                throw th2;
            }
        }

        static Boolean lambda13() {
            return Boolean.FALSE;
        }

        static Boolean lambda14() {
            return Boolean.FALSE;
        }

        static Boolean lambda15() {
            return Boolean.FALSE;
        }

        static Boolean lambda16() {
            return Boolean.FALSE;
        }

        static Boolean lambda17() {
            return Boolean.FALSE;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            switch (moduleMethod2.selector) {
                case 9:
                    return lambda9(obj2);
                case 11:
                    return lambda11(obj2);
                case 12:
                    return lambda12(obj2);
                case 13:
                    return lambda18(obj2);
                default:
                    return super.apply1(moduleMethod2, obj2);
            }
        }

        /* access modifiers changed from: package-private */
        public Object lambda18(Object i1) {
            this.staticLink.case$Mnsensitive$Qu = this.old;
            return Scheme.applyToArgs.apply2(this.f105sk, i1);
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            switch (moduleMethod2.selector) {
                case 10:
                    return lambda10();
                case 14:
                    return lambda19();
                default:
                    return super.apply0(moduleMethod2);
            }
        }

        /* access modifiers changed from: package-private */
        public Object lambda19() {
            this.staticLink.case$Mnsensitive$Qu = this.old;
            return Scheme.applyToArgs.apply1(this.f100fk);
        }

        public Object lambda8loupP(Object k, Object i) {
            frame4 frame4;
            Object lambda24loupQ;
            new frame4();
            frame4 frame42 = frame4;
            frame42.staticLink = this;
            frame4 frame43 = frame42;
            frame43.f107k = k;
            frame43.f106i = i;
            if (Scheme.numLss.apply2(frame43.f107k, this.f102p) != Boolean.FALSE) {
                lambda24loupQ = this.staticLink.lambda3sub(this.f104re, frame43.f106i, frame43.lambda$Fn17, this.f100fk);
            } else {
                frame43.f108q = this.f103q != Boolean.FALSE ? AddOp.$Mn.apply2(this.f103q, this.f102p) : this.f103q;
                lambda24loupQ = frame43.lambda24loupQ(pregexp.Lit73, frame43.f106i);
            }
            return lambda24loupQ;
        }
    }

    /* compiled from: pregexp.scm */
    public class frame1 extends ModuleBody {
        Object chars;
        final ModuleMethod lambda$Fn13;
        frame0 staticLink;

        public frame1() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 1, (Object) null, 0);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:508");
            this.lambda$Fn13 = moduleMethod2;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            if (moduleMethod2.selector == 1) {
                return lambda20();
            }
            return super.apply0(moduleMethod2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda20() {
            return this.staticLink.lambda5loupOneOfChars(C1245lists.cdr.apply1(this.chars));
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
    }

    /* compiled from: pregexp.scm */
    public class frame2 extends ModuleBody {
        final ModuleMethod lambda$Fn14;
        Object res;
        frame0 staticLink;

        public frame2() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 2, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:519");
            this.lambda$Fn14 = moduleMethod2;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector == 2) {
                return lambda21(obj2);
            }
            return super.apply1(moduleMethod2, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda21(Object i1) {
            return this.staticLink.lambda6loupSeq(C1245lists.cdr.apply1(this.res), i1);
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 2) {
                return super.match1(moduleMethod2, obj2, callContext2);
            }
            callContext2.value1 = obj2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 1;
            return 0;
        }
    }

    /* compiled from: pregexp.scm */
    public class frame3 extends ModuleBody {
        final ModuleMethod lambda$Fn15;
        final ModuleMethod lambda$Fn16;
        Object res;
        frame0 staticLink;

        public frame3() {
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 3, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod3 = moduleMethod;
            moduleMethod3.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:526");
            this.lambda$Fn15 = moduleMethod3;
            new ModuleMethod(this, 4, (Object) null, 0);
            ModuleMethod moduleMethod4 = moduleMethod2;
            moduleMethod4.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:529");
            this.lambda$Fn16 = moduleMethod4;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector == 3) {
                return lambda22(obj2);
            }
            return super.apply1(moduleMethod2, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda22(Object i1) {
            Object lambda7loupOr;
            Object x = Scheme.applyToArgs.apply2(this.staticLink.f105sk, i1);
            if (x != Boolean.FALSE) {
                lambda7loupOr = x;
            } else {
                lambda7loupOr = this.staticLink.lambda7loupOr(C1245lists.cdr.apply1(this.res));
            }
            return lambda7loupOr;
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 3) {
                return super.match1(moduleMethod2, obj2, callContext2);
            }
            callContext2.value1 = obj2;
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 1;
            return 0;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            if (moduleMethod2.selector == 4) {
                return lambda23();
            }
            return super.apply0(moduleMethod2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda23() {
            return this.staticLink.lambda7loupOr(C1245lists.cdr.apply1(this.res));
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            CallContext callContext2 = callContext;
            if (moduleMethod2.selector != 4) {
                return super.match0(moduleMethod2, callContext2);
            }
            callContext2.proc = moduleMethod2;
            callContext2.f239pc = 0;
            return 0;
        }
    }

    public Object apply0(ModuleMethod moduleMethod) {
        ModuleMethod moduleMethod2 = moduleMethod;
        switch (moduleMethod2.selector) {
            case 36:
                return frame.lambda4();
            case 37:
                return frame0.lambda13();
            case 38:
                return frame0.lambda14();
            case 39:
                return frame0.lambda15();
            case 40:
                return frame0.lambda16();
            case 41:
                return frame0.lambda17();
            default:
                return super.apply0(moduleMethod2);
        }
    }

    /* compiled from: pregexp.scm */
    public class frame4 extends ModuleBody {

        /* renamed from: i */
        Object f106i;

        /* renamed from: k */
        Object f107k;
        final ModuleMethod lambda$Fn17;

        /* renamed from: q */
        Object f108q;
        frame0 staticLink;

        public frame4() {
            ModuleMethod moduleMethod;
            new ModuleMethod(this, 8, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod2 = moduleMethod;
            moduleMethod2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:602");
            this.lambda$Fn17 = moduleMethod2;
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            if (moduleMethod2.selector == 8) {
                return lambda25(obj2);
            }
            return super.apply1(moduleMethod2, obj2);
        }

        /* access modifiers changed from: package-private */
        public Object lambda25(Object obj) {
            Object i1 = obj;
            if (!this.staticLink.could$Mnloop$Mninfinitely$Qu ? this.staticLink.could$Mnloop$Mninfinitely$Qu : Scheme.numEqu.apply2(i1, this.f106i) != Boolean.FALSE) {
                Object[] objArr = new Object[2];
                objArr[0] = pregexp.Lit101;
                Object[] objArr2 = objArr;
                objArr2[1] = pregexp.Lit110;
                Object pregexpError$V = pregexp.pregexpError$V(objArr2);
            }
            return this.staticLink.lambda8loupP(AddOp.$Pl.apply2(this.f107k, pregexp.Lit8), i1);
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

        public Object lambda24loupQ(Object k, Object i) {
            frame5 frame5;
            Object lambda3sub;
            new frame5();
            frame5 frame52 = frame5;
            frame52.staticLink = this;
            frame5 frame53 = frame52;
            frame53.f111k = k;
            frame53.f110i = i;
            frame53.f109fk = frame53.f109fk;
            if (this.f108q == Boolean.FALSE ? this.f108q != Boolean.FALSE : Scheme.numGEq.apply2(frame53.f111k, this.f108q) != Boolean.FALSE) {
                lambda3sub = frame53.lambda26fk();
            } else if (this.staticLink.maximal$Qu) {
                lambda3sub = this.staticLink.staticLink.lambda3sub(this.staticLink.f104re, frame53.f110i, frame53.lambda$Fn18, frame53.f109fk);
            } else {
                Object x = frame53.lambda26fk();
                lambda3sub = x != Boolean.FALSE ? x : this.staticLink.staticLink.lambda3sub(this.staticLink.f104re, frame53.f110i, frame53.lambda$Fn19, frame53.f109fk);
            }
            return lambda3sub;
        }
    }

    /* compiled from: pregexp.scm */
    public class frame5 extends ModuleBody {

        /* renamed from: fk */
        Procedure f109fk;

        /* renamed from: i */
        Object f110i;

        /* renamed from: k */
        Object f111k;
        final ModuleMethod lambda$Fn18;
        final ModuleMethod lambda$Fn19;
        frame4 staticLink;

        public frame5() {
            Procedure procedure;
            ModuleMethod moduleMethod;
            ModuleMethod moduleMethod2;
            new ModuleMethod(this, 5, pregexp.Lit111, 0);
            Procedure procedure2 = procedure;
            procedure2.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:612");
            this.f109fk = procedure2;
            new ModuleMethod(this, 6, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod3 = moduleMethod;
            moduleMethod3.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:617");
            this.lambda$Fn18 = moduleMethod3;
            new ModuleMethod(this, 7, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ModuleMethod moduleMethod4 = moduleMethod2;
            moduleMethod4.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/pregexp.scm:628");
            this.lambda$Fn19 = moduleMethod4;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            if (moduleMethod2.selector == 5) {
                return lambda26fk();
            }
            return super.apply0(moduleMethod2);
        }

        public Object lambda26fk() {
            return Scheme.applyToArgs.apply2(this.staticLink.staticLink.f105sk, this.f110i);
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

        /* access modifiers changed from: package-private */
        public Object lambda27(Object obj) {
            Object i1 = obj;
            if (!this.staticLink.staticLink.could$Mnloop$Mninfinitely$Qu ? this.staticLink.staticLink.could$Mnloop$Mninfinitely$Qu : Scheme.numEqu.apply2(i1, this.f110i) != Boolean.FALSE) {
                Object[] objArr = new Object[2];
                objArr[0] = pregexp.Lit101;
                Object[] objArr2 = objArr;
                objArr2[1] = pregexp.Lit110;
                Object pregexpError$V = pregexp.pregexpError$V(objArr2);
            }
            Object x = this.staticLink.lambda24loupQ(AddOp.$Pl.apply2(this.f111k, pregexp.Lit8), i1);
            return x != Boolean.FALSE ? x : lambda26fk();
        }

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            CallContext callContext2 = callContext;
            switch (moduleMethod2.selector) {
                case 6:
                    callContext2.value1 = obj2;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 1;
                    return 0;
                case 7:
                    callContext2.value1 = obj2;
                    callContext2.proc = moduleMethod2;
                    callContext2.f239pc = 1;
                    return 0;
                default:
                    return super.match1(moduleMethod2, obj2, callContext2);
            }
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            ModuleMethod moduleMethod2 = moduleMethod;
            Object obj2 = obj;
            switch (moduleMethod2.selector) {
                case 6:
                    return lambda27(obj2);
                case 7:
                    return lambda28(obj2);
                default:
                    return super.apply1(moduleMethod2, obj2);
            }
        }

        /* access modifiers changed from: package-private */
        public Object lambda28(Object i1) {
            return this.staticLink.lambda24loupQ(AddOp.$Pl.apply2(this.f111k, pregexp.Lit8), i1);
        }
    }

    public static Object pregexpReplaceAux(Object obj, Object obj2, Object obj3, Object obj4) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Object obj5;
        Object apply2;
        Object obj6;
        Throwable th5;
        Throwable th6;
        Throwable th7;
        Throwable th8;
        Throwable th9;
        Object str = obj;
        Object ins = obj2;
        Object n = obj3;
        Object backrefs = obj4;
        Number number = Lit73;
        Object obj7 = "";
        while (true) {
            Object r = obj7;
            Number number2 = number;
            if (Scheme.numGEq.apply2(number2, n) != Boolean.FALSE) {
                return r;
            }
            Object obj8 = ins;
            Object obj9 = obj8;
            try {
                CharSequence charSequence = (CharSequence) obj8;
                Number number3 = number2;
                Number number4 = number3;
                try {
                    char c = strings.stringRef(charSequence, number3.intValue());
                    if (characters.isChar$Eq(Char.make(c), Lit19)) {
                        Object br$Mni = pregexpReadEscapedNumber(ins, number2, n);
                        if (br$Mni != Boolean.FALSE) {
                            obj5 = C1245lists.car.apply1(br$Mni);
                        } else {
                            Object obj10 = ins;
                            Object obj11 = obj10;
                            try {
                                CharSequence charSequence2 = (CharSequence) obj10;
                                Object apply22 = AddOp.$Pl.apply2(number2, Lit8);
                                Object obj12 = apply22;
                                try {
                                    obj5 = characters.isChar$Eq(Char.make(strings.stringRef(charSequence2, ((Number) apply22).intValue())), Lit113) ? Lit73 : Boolean.FALSE;
                                } catch (ClassCastException e) {
                                    ClassCastException classCastException = e;
                                    Throwable th10 = th4;
                                    new WrongType(classCastException, "string-ref", 2, obj12);
                                    throw th10;
                                }
                            } catch (ClassCastException e2) {
                                ClassCastException classCastException2 = e2;
                                Throwable th11 = th3;
                                new WrongType(classCastException2, "string-ref", 1, obj11);
                                throw th11;
                            }
                        }
                        Object br = obj5;
                        if (br$Mni != Boolean.FALSE) {
                            apply2 = C1245lists.cadr.apply1(br$Mni);
                        } else if (br != Boolean.FALSE) {
                            apply2 = AddOp.$Pl.apply2(number2, Lit16);
                        } else {
                            apply2 = AddOp.$Pl.apply2(number2, Lit8);
                        }
                        Object i = apply2;
                        if (br == Boolean.FALSE) {
                            Object obj13 = ins;
                            Object obj14 = obj13;
                            try {
                                CharSequence charSequence3 = (CharSequence) obj13;
                                Object obj15 = i;
                                Object obj16 = obj15;
                                try {
                                    char c2 = strings.stringRef(charSequence3, ((Number) obj15).intValue());
                                    number = AddOp.$Pl.apply2(i, Lit8);
                                    if (characters.isChar$Eq(Char.make(c2), Lit11)) {
                                        obj7 = r;
                                    } else {
                                        Object[] objArr = new Object[2];
                                        objArr[0] = r;
                                        Object[] objArr2 = objArr;
                                        objArr2[1] = strings.$make$string$(Char.make(c2));
                                        obj7 = strings.stringAppend(objArr2);
                                    }
                                } catch (ClassCastException e3) {
                                    ClassCastException classCastException3 = e3;
                                    Throwable th12 = th9;
                                    new WrongType(classCastException3, "string-ref", 2, obj16);
                                    throw th12;
                                }
                            } catch (ClassCastException e4) {
                                ClassCastException classCastException4 = e4;
                                Throwable th13 = th8;
                                new WrongType(classCastException4, "string-ref", 1, obj14);
                                throw th13;
                            }
                        } else {
                            number = i;
                            Object backref = pregexpListRef(backrefs, br);
                            if (backref != Boolean.FALSE) {
                                Object[] objArr3 = new Object[2];
                                objArr3[0] = r;
                                Object[] objArr4 = objArr3;
                                Object[] objArr5 = objArr4;
                                Object[] objArr6 = objArr4;
                                Object obj17 = str;
                                Object obj18 = obj17;
                                try {
                                    CharSequence charSequence4 = (CharSequence) obj17;
                                    Object apply1 = C1245lists.car.apply1(backref);
                                    Object obj19 = apply1;
                                    try {
                                        int intValue = ((Number) apply1).intValue();
                                        Object apply12 = C1245lists.cdr.apply1(backref);
                                        Object obj20 = apply12;
                                        try {
                                            objArr6[1] = strings.substring(charSequence4, intValue, ((Number) apply12).intValue());
                                            obj6 = strings.stringAppend(objArr5);
                                        } catch (ClassCastException e5) {
                                            ClassCastException classCastException5 = e5;
                                            Throwable th14 = th7;
                                            new WrongType(classCastException5, "substring", 3, obj20);
                                            throw th14;
                                        }
                                    } catch (ClassCastException e6) {
                                        ClassCastException classCastException6 = e6;
                                        Throwable th15 = th6;
                                        new WrongType(classCastException6, "substring", 2, obj19);
                                        throw th15;
                                    }
                                } catch (ClassCastException e7) {
                                    ClassCastException classCastException7 = e7;
                                    Throwable th16 = th5;
                                    new WrongType(classCastException7, "substring", 1, obj18);
                                    throw th16;
                                }
                            } else {
                                obj6 = r;
                            }
                        }
                    } else {
                        number = AddOp.$Pl.apply2(number2, Lit8);
                        Object[] objArr7 = new Object[2];
                        objArr7[0] = r;
                        Object[] objArr8 = objArr7;
                        objArr8[1] = strings.$make$string$(Char.make(c));
                        obj7 = strings.stringAppend(objArr8);
                    }
                } catch (ClassCastException e8) {
                    ClassCastException classCastException8 = e8;
                    Throwable th17 = th2;
                    new WrongType(classCastException8, "string-ref", 2, (Object) number4);
                    throw th17;
                }
            } catch (ClassCastException e9) {
                ClassCastException classCastException9 = e9;
                Throwable th18 = th;
                new WrongType(classCastException9, "string-ref", 1, obj9);
                throw th18;
            }
        }
    }

    public Object apply4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj5 = obj;
        Object obj6 = obj2;
        Object obj7 = obj3;
        Object obj8 = obj4;
        if (moduleMethod2.selector == 43) {
            return pregexpReplaceAux(obj5, obj6, obj7, obj8);
        }
        return super.apply4(moduleMethod2, obj5, obj6, obj7, obj8);
    }

    public int match4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj5 = obj;
        Object obj6 = obj2;
        Object obj7 = obj3;
        Object obj8 = obj4;
        CallContext callContext2 = callContext;
        if (moduleMethod2.selector != 43) {
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

    public static Pair pregexp(Object obj) {
        Throwable th;
        Object s = obj;
        $Stpregexp$Mnspace$Mnsensitive$Qu$St = Boolean.TRUE;
        Object obj2 = s;
        Object obj3 = obj2;
        try {
            return LList.list2(Lit100, C1245lists.car.apply1(pregexpReadPattern(s, Lit73, Integer.valueOf(strings.stringLength((CharSequence) obj2)))));
        } catch (ClassCastException e) {
            ClassCastException classCastException = e;
            Throwable th2 = th;
            new WrongType(classCastException, "string-length", 1, obj3);
            throw th2;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v24, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v7, resolved type: gnu.lists.LList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: gnu.lists.Pair} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object pregexpMatchPositions$V(java.lang.Object r18, java.lang.Object r19, java.lang.Object[] r20) {
        /*
            r0 = r18
            r1 = r19
            r2 = r20
            r10 = r2
            r11 = 0
            gnu.lists.LList r10 = gnu.lists.LList.makeList(r10, r11)
            r16 = r10
            r10 = r16
            r11 = r16
            r4 = r11
            r3 = r10
            r10 = r0
            boolean r10 = kawa.lib.strings.isString(r10)
            if (r10 == 0) goto L_0x0079
            r10 = r0
            gnu.lists.Pair r10 = pregexp(r10)
            r0 = r10
        L_0x0021:
            r10 = r1
            r16 = r10
            r10 = r16
            r11 = r16
            r5 = r11
            java.lang.CharSequence r10 = (java.lang.CharSequence) r10     // Catch:{ ClassCastException -> 0x00e4 }
            int r10 = kawa.lib.strings.stringLength(r10)
            r4 = r10
            r10 = r3
            boolean r10 = kawa.lib.C1245lists.isNull(r10)
            if (r10 == 0) goto L_0x00aa
            gnu.math.IntNum r10 = Lit73
        L_0x0039:
            r5 = r10
            r10 = r3
            boolean r10 = kawa.lib.C1245lists.isNull(r10)
            if (r10 == 0) goto L_0x00c6
            r10 = r4
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
        L_0x0046:
            r6 = r10
            r10 = r5
            r7 = r10
        L_0x0049:
            gnu.kawa.functions.NumberCompare r10 = kawa.standard.Scheme.numLEq
            r11 = r7
            r12 = r6
            java.lang.Object r10 = r10.apply2(r11, r12)
            r16 = r10
            r10 = r16
            r11 = r16
            r9 = r11
            java.lang.Boolean r10 = (java.lang.Boolean) r10     // Catch:{ ClassCastException -> 0x0120 }
            boolean r10 = r10.booleanValue()     // Catch:{ ClassCastException -> 0x0120 }
            r8 = r10
            r10 = r8
            if (r10 == 0) goto L_0x00db
            r10 = r0
            r11 = r1
            r12 = r4
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)
            r13 = r5
            r14 = r6
            r15 = r7
            java.lang.Object r10 = pregexpMatchPositionsAux(r10, r11, r12, r13, r14, r15)
            r9 = r10
            r10 = r9
            java.lang.Boolean r11 = java.lang.Boolean.FALSE
            if (r10 == r11) goto L_0x00cf
            r10 = r9
        L_0x0077:
            r0 = r10
            return r0
        L_0x0079:
            r10 = r0
            boolean r10 = kawa.lib.C1245lists.isPair(r10)
            if (r10 == 0) goto L_0x0081
            goto L_0x0021
        L_0x0081:
            r10 = 3
            java.lang.Object[] r10 = new java.lang.Object[r10]
            r16 = r10
            r10 = r16
            r11 = r16
            r12 = 0
            gnu.mapping.SimpleSymbol r13 = Lit114
            r11[r12] = r13
            r16 = r10
            r10 = r16
            r11 = r16
            r12 = 1
            gnu.mapping.SimpleSymbol r13 = Lit115
            r11[r12] = r13
            r16 = r10
            r10 = r16
            r11 = r16
            r12 = 2
            r13 = r0
            r11[r12] = r13
            java.lang.Object r10 = pregexpError$V(r10)
            goto L_0x0021
        L_0x00aa:
            gnu.expr.GenericProc r10 = kawa.lib.C1245lists.car
            r11 = r3
            java.lang.Object r10 = r10.apply1(r11)
            r6 = r10
            gnu.expr.GenericProc r10 = kawa.lib.C1245lists.cdr
            r11 = r3
            java.lang.Object r10 = r10.apply1(r11)
            r16 = r10
            r10 = r16
            r11 = r16
            r7 = r11
            gnu.lists.LList r10 = (gnu.lists.LList) r10     // Catch:{ ClassCastException -> 0x0102 }
            r3 = r10
            r10 = r6
            goto L_0x0039
        L_0x00c6:
            gnu.expr.GenericProc r10 = kawa.lib.C1245lists.car
            r11 = r3
            java.lang.Object r10 = r10.apply1(r11)
            goto L_0x0046
        L_0x00cf:
            gnu.kawa.functions.AddOp r10 = gnu.kawa.functions.AddOp.$Pl
            r11 = r7
            gnu.math.IntNum r12 = Lit8
            java.lang.Object r10 = r10.apply2(r11, r12)
            r7 = r10
            goto L_0x0049
        L_0x00db:
            r10 = r8
            if (r10 == 0) goto L_0x00e1
            java.lang.Boolean r10 = java.lang.Boolean.TRUE
            goto L_0x0077
        L_0x00e1:
            java.lang.Boolean r10 = java.lang.Boolean.FALSE
            goto L_0x0077
        L_0x00e4:
            r10 = move-exception
            gnu.mapping.WrongType r11 = new gnu.mapping.WrongType
            r16 = r10
            r17 = r11
            r10 = r17
            r11 = r16
            r12 = r17
            r16 = r11
            r17 = r12
            r11 = r17
            r12 = r16
            java.lang.String r13 = "string-length"
            r14 = 1
            r15 = r5
            r11.<init>((java.lang.ClassCastException) r12, (java.lang.String) r13, (int) r14, (java.lang.Object) r15)
            throw r10
        L_0x0102:
            r10 = move-exception
            gnu.mapping.WrongType r11 = new gnu.mapping.WrongType
            r16 = r10
            r17 = r11
            r10 = r17
            r11 = r16
            r12 = r17
            r16 = r11
            r17 = r12
            r11 = r17
            r12 = r16
            java.lang.String r13 = "opt-args"
            r14 = -2
            r15 = r7
            r11.<init>((java.lang.ClassCastException) r12, (java.lang.String) r13, (int) r14, (java.lang.Object) r15)
            throw r10
        L_0x0120:
            r10 = move-exception
            gnu.mapping.WrongType r11 = new gnu.mapping.WrongType
            r16 = r10
            r17 = r11
            r10 = r17
            r11 = r16
            r12 = r17
            r16 = r11
            r17 = r12
            r11 = r17
            r12 = r16
            java.lang.String r13 = "x"
            r14 = -2
            r15 = r9
            r11.<init>((java.lang.ClassCastException) r12, (java.lang.String) r13, (int) r14, (java.lang.Object) r15)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.slib.pregexp.pregexpMatchPositions$V(java.lang.Object, java.lang.Object, java.lang.Object[]):java.lang.Object");
    }

    public static Object pregexpMatch$V(Object pat, Object obj, Object[] argsArray) {
        Object pat2;
        Object obj2;
        Throwable th;
        Object obj3;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Object str = obj;
        LList opt$Mnargs = LList.makeList(argsArray, 0);
        LList lList = opt$Mnargs;
        Object ix$Mnprs = Scheme.apply.apply4(pregexp$Mnmatch$Mnpositions, pat, str, opt$Mnargs);
        if (ix$Mnprs != Boolean.FALSE) {
            Object obj4 = ix$Mnprs;
            Object obj5 = LList.Empty;
            while (true) {
                obj2 = obj5;
                Object arg0 = obj4;
                if (arg0 == LList.Empty) {
                    break;
                }
                Object obj6 = arg0;
                Object obj7 = obj6;
                try {
                    Pair arg02 = (Pair) obj6;
                    obj4 = arg02.getCdr();
                    Object ix$Mnpr = arg02.getCar();
                    if (ix$Mnpr != Boolean.FALSE) {
                        Object obj8 = str;
                        Object obj9 = obj8;
                        try {
                            CharSequence charSequence = (CharSequence) obj8;
                            Object apply1 = C1245lists.car.apply1(ix$Mnpr);
                            Object obj10 = apply1;
                            try {
                                int intValue = ((Number) apply1).intValue();
                                Object apply12 = C1245lists.cdr.apply1(ix$Mnpr);
                                Object obj11 = apply12;
                                try {
                                    obj3 = strings.substring(charSequence, intValue, ((Number) apply12).intValue());
                                } catch (ClassCastException e) {
                                    ClassCastException classCastException = e;
                                    Throwable th5 = th4;
                                    new WrongType(classCastException, "substring", 3, obj11);
                                    throw th5;
                                }
                            } catch (ClassCastException e2) {
                                ClassCastException classCastException2 = e2;
                                Throwable th6 = th3;
                                new WrongType(classCastException2, "substring", 2, obj10);
                                throw th6;
                            }
                        } catch (ClassCastException e3) {
                            ClassCastException classCastException3 = e3;
                            Throwable th7 = th2;
                            new WrongType(classCastException3, "substring", 1, obj9);
                            throw th7;
                        }
                    } else {
                        obj3 = ix$Mnpr;
                    }
                    obj5 = Pair.make(obj3, obj2);
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th8 = th;
                    new WrongType(classCastException4, "arg0", -2, obj7);
                    throw th8;
                }
            }
            pat2 = LList.reverseInPlace(obj2);
        } else {
            pat2 = ix$Mnprs;
        }
        return pat2;
    }

    public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object[] objArr2 = objArr;
        switch (moduleMethod2.selector) {
            case 17:
                return pregexpError$V(objArr2);
            case 30:
                return pregexpStringMatch(objArr2[0], objArr2[1], objArr2[2], objArr2[3], objArr2[4], objArr2[5]);
            case 42:
                return pregexpMatchPositionsAux(objArr2[0], objArr2[1], objArr2[2], objArr2[3], objArr2[4], objArr2[5]);
            case 45:
                Object obj = objArr2[0];
                Object obj2 = objArr2[1];
                int length = objArr2.length - 2;
                Object[] objArr3 = new Object[length];
                while (true) {
                    length--;
                    if (length < 0) {
                        return pregexpMatchPositions$V(obj, obj2, objArr3);
                    }
                    Object[] objArr4 = objArr3;
                    objArr3 = objArr4;
                    objArr4[length] = objArr2[length + 2];
                }
            case 46:
                Object obj3 = objArr2[0];
                Object obj4 = objArr2[1];
                int length2 = objArr2.length - 2;
                Object[] objArr5 = new Object[length2];
                while (true) {
                    length2--;
                    if (length2 < 0) {
                        return pregexpMatch$V(obj3, obj4, objArr5);
                    }
                    Object[] objArr6 = objArr5;
                    objArr5 = objArr6;
                    objArr6[length2] = objArr2[length2 + 2];
                }
            default:
                return super.applyN(moduleMethod2, objArr2);
        }
    }

    public static Object pregexpSplit(Object obj, Object obj2) {
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
        Object pat = obj;
        Object str = obj2;
        Object obj3 = str;
        Object obj4 = obj3;
        try {
            int n = strings.stringLength((CharSequence) obj3);
            Number number = Lit73;
            LList lList = LList.Empty;
            Boolean bool = Boolean.FALSE;
            while (true) {
                Boolean bool2 = bool;
                LList lList2 = lList;
                Number number2 = number;
                if (Scheme.numGEq.apply2(number2, Integer.valueOf(n)) != Boolean.FALSE) {
                    return pregexpReverse$Ex(lList2);
                }
                Object[] objArr = new Object[2];
                objArr[0] = number2;
                Object[] objArr2 = objArr;
                objArr2[1] = Integer.valueOf(n);
                Object temp = pregexpMatchPositions$V(pat, str, objArr2);
                if (temp != Boolean.FALSE) {
                    Object jk = C1245lists.car.apply1(temp);
                    Object apply1 = C1245lists.car.apply1(jk);
                    Object k = C1245lists.cdr.apply1(jk);
                    Object j = apply1;
                    if (Scheme.numEqu.apply2(j, k) != Boolean.FALSE) {
                        number = AddOp.$Pl.apply2(k, Lit8);
                        Object obj5 = str;
                        Object obj6 = obj5;
                        try {
                            CharSequence charSequence = (CharSequence) obj5;
                            Number number3 = number2;
                            Number number4 = number3;
                            try {
                                int intValue = number3.intValue();
                                Object apply2 = AddOp.$Pl.apply2(j, Lit8);
                                Object obj7 = apply2;
                                try {
                                    lList = C1245lists.cons(strings.substring(charSequence, intValue, ((Number) apply2).intValue()), lList2);
                                    bool = Boolean.TRUE;
                                } catch (ClassCastException e) {
                                    ClassCastException classCastException = e;
                                    Throwable th11 = th10;
                                    new WrongType(classCastException, "substring", 3, obj7);
                                    throw th11;
                                }
                            } catch (ClassCastException e2) {
                                ClassCastException classCastException2 = e2;
                                Throwable th12 = th9;
                                new WrongType(classCastException2, "substring", 2, (Object) number4);
                                throw th12;
                            }
                        } catch (ClassCastException e3) {
                            ClassCastException classCastException3 = e3;
                            Throwable th13 = th8;
                            new WrongType(classCastException3, "substring", 1, obj6);
                            throw th13;
                        }
                    } else {
                        Object apply22 = Scheme.numEqu.apply2(j, number2);
                        Object obj8 = apply22;
                        try {
                            boolean x = ((Boolean) apply22).booleanValue();
                            if (!x ? !x : bool2 == Boolean.FALSE) {
                                number = k;
                                Object obj9 = str;
                                Object obj10 = obj9;
                                try {
                                    CharSequence charSequence2 = (CharSequence) obj9;
                                    Number number5 = number2;
                                    Number number6 = number5;
                                    try {
                                        int intValue2 = number5.intValue();
                                        Object obj11 = j;
                                        Object obj12 = obj11;
                                        try {
                                            lList = C1245lists.cons(strings.substring(charSequence2, intValue2, ((Number) obj11).intValue()), lList2);
                                            bool = Boolean.FALSE;
                                        } catch (ClassCastException e4) {
                                            ClassCastException classCastException4 = e4;
                                            Throwable th14 = th7;
                                            new WrongType(classCastException4, "substring", 3, obj12);
                                            throw th14;
                                        }
                                    } catch (ClassCastException e5) {
                                        ClassCastException classCastException5 = e5;
                                        Throwable th15 = th6;
                                        new WrongType(classCastException5, "substring", 2, (Object) number6);
                                        throw th15;
                                    }
                                } catch (ClassCastException e6) {
                                    ClassCastException classCastException6 = e6;
                                    Throwable th16 = th5;
                                    new WrongType(classCastException6, "substring", 1, obj10);
                                    throw th16;
                                }
                            } else {
                                number = k;
                                lList = lList2;
                                bool = Boolean.FALSE;
                            }
                        } catch (ClassCastException e7) {
                            ClassCastException classCastException7 = e7;
                            Throwable th17 = th4;
                            new WrongType(classCastException7, "x", -2, obj8);
                            throw th17;
                        }
                    }
                } else {
                    number = Integer.valueOf(n);
                    Object obj13 = str;
                    Object obj14 = obj13;
                    try {
                        CharSequence charSequence3 = (CharSequence) obj13;
                        Number number7 = number2;
                        Number number8 = number7;
                        try {
                            lList = C1245lists.cons(strings.substring(charSequence3, number7.intValue(), n), lList2);
                            bool = Boolean.FALSE;
                        } catch (ClassCastException e8) {
                            ClassCastException classCastException8 = e8;
                            Throwable th18 = th3;
                            new WrongType(classCastException8, "substring", 2, (Object) number8);
                            throw th18;
                        }
                    } catch (ClassCastException e9) {
                        ClassCastException classCastException9 = e9;
                        Throwable th19 = th2;
                        new WrongType(classCastException9, "substring", 1, obj14);
                        throw th19;
                    }
                }
            }
        } catch (ClassCastException e10) {
            ClassCastException classCastException10 = e10;
            Throwable th20 = th;
            new WrongType(classCastException10, "string-length", 1, obj4);
            throw th20;
        }
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        switch (moduleMethod2.selector) {
            case 33:
                return isPregexpCheckIfInCharClass(obj3, obj4);
            case 34:
                return pregexpListRef(obj3, obj4);
            case 47:
                return pregexpSplit(obj3, obj4);
            default:
                return super.apply2(moduleMethod2, obj3, obj4);
        }
    }

    public static Object pregexpReplace(Object obj, Object obj2, Object obj3) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        Object pat;
        Object pat2 = obj;
        Object str = obj2;
        Object ins = obj3;
        Object obj4 = str;
        Object obj5 = obj4;
        try {
            int n = strings.stringLength((CharSequence) obj4);
            Object[] objArr = new Object[2];
            objArr[0] = Lit73;
            Object[] objArr2 = objArr;
            objArr2[1] = Integer.valueOf(n);
            Object pp = pregexpMatchPositions$V(pat2, str, objArr2);
            if (pp == Boolean.FALSE) {
                pat = str;
            } else {
                Object obj6 = ins;
                Object obj7 = obj6;
                try {
                    int stringLength = strings.stringLength((CharSequence) obj6);
                    Object apply1 = C1245lists.caar.apply1(pp);
                    Object m$Mnn = C1245lists.cdar.apply1(pp);
                    Object m$Mni = apply1;
                    int ins$Mnlen = stringLength;
                    Object[] objArr3 = new Object[3];
                    Object[] objArr4 = objArr3;
                    Object[] objArr5 = objArr3;
                    Object obj8 = str;
                    Object obj9 = obj8;
                    try {
                        CharSequence charSequence = (CharSequence) obj8;
                        Object obj10 = m$Mni;
                        Object obj11 = obj10;
                        try {
                            objArr5[0] = strings.substring(charSequence, 0, ((Number) obj10).intValue());
                            Object[] objArr6 = objArr4;
                            objArr6[1] = pregexpReplaceAux(str, ins, Integer.valueOf(ins$Mnlen), pp);
                            Object[] objArr7 = objArr6;
                            Object[] objArr8 = objArr7;
                            Object[] objArr9 = objArr7;
                            Object obj12 = str;
                            Object obj13 = obj12;
                            try {
                                CharSequence charSequence2 = (CharSequence) obj12;
                                Object obj14 = m$Mnn;
                                Object obj15 = obj14;
                                try {
                                    objArr9[2] = strings.substring(charSequence2, ((Number) obj14).intValue(), n);
                                    pat = strings.stringAppend(objArr8);
                                } catch (ClassCastException e) {
                                    ClassCastException classCastException = e;
                                    Throwable th7 = th6;
                                    new WrongType(classCastException, "substring", 2, obj15);
                                    throw th7;
                                }
                            } catch (ClassCastException e2) {
                                ClassCastException classCastException2 = e2;
                                Throwable th8 = th5;
                                new WrongType(classCastException2, "substring", 1, obj13);
                                throw th8;
                            }
                        } catch (ClassCastException e3) {
                            ClassCastException classCastException3 = e3;
                            Throwable th9 = th4;
                            new WrongType(classCastException3, "substring", 3, obj11);
                            throw th9;
                        }
                    } catch (ClassCastException e4) {
                        ClassCastException classCastException4 = e4;
                        Throwable th10 = th3;
                        new WrongType(classCastException4, "substring", 1, obj9);
                        throw th10;
                    }
                } catch (ClassCastException e5) {
                    ClassCastException classCastException5 = e5;
                    Throwable th11 = th2;
                    new WrongType(classCastException5, "string-length", 1, obj7);
                    throw th11;
                }
            }
            return pat;
        } catch (ClassCastException e6) {
            ClassCastException classCastException6 = e6;
            Throwable th12 = th;
            new WrongType(classCastException6, "string-length", 1, obj5);
            throw th12;
        }
    }

    public static Object pregexpReplace$St(Object obj, Object obj2, Object obj3) {
        Throwable th;
        Throwable th2;
        Object obj4;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        Throwable th7;
        Object pat = obj;
        Object str = obj2;
        Object ins = obj3;
        Object pregexp2 = strings.isString(pat) ? pregexp(pat) : pat;
        Object obj5 = str;
        Object obj6 = obj5;
        try {
            int stringLength = strings.stringLength((CharSequence) obj5);
            Object obj7 = ins;
            Object obj8 = obj7;
            try {
                int ins$Mnlen = strings.stringLength((CharSequence) obj7);
                int n = stringLength;
                Object pat2 = pregexp2;
                Object obj9 = Lit73;
                Object obj10 = "";
                while (true) {
                    Object obj11 = obj10;
                    Object obj12 = obj9;
                    if (Scheme.numGEq.apply2(obj12, Integer.valueOf(n)) != Boolean.FALSE) {
                        obj4 = obj11;
                        break;
                    }
                    Object[] objArr = new Object[2];
                    objArr[0] = obj12;
                    Object[] objArr2 = objArr;
                    objArr2[1] = Integer.valueOf(n);
                    Object pp = pregexpMatchPositions$V(pat2, str, objArr2);
                    if (pp != Boolean.FALSE) {
                        obj9 = C1245lists.cdar.apply1(pp);
                        Object[] objArr3 = new Object[3];
                        objArr3[0] = obj11;
                        Object[] objArr4 = objArr3;
                        Object[] objArr5 = objArr4;
                        Object[] objArr6 = objArr4;
                        Object obj13 = str;
                        Object obj14 = obj13;
                        try {
                            CharSequence charSequence = (CharSequence) obj13;
                            Object obj15 = obj12;
                            Object obj16 = obj15;
                            try {
                                int intValue = ((Number) obj15).intValue();
                                Object apply1 = C1245lists.caar.apply1(pp);
                                Object obj17 = apply1;
                                try {
                                    objArr6[1] = strings.substring(charSequence, intValue, ((Number) apply1).intValue());
                                    Object[] objArr7 = objArr5;
                                    objArr7[2] = pregexpReplaceAux(str, ins, Integer.valueOf(ins$Mnlen), pp);
                                    obj10 = strings.stringAppend(objArr7);
                                } catch (ClassCastException e) {
                                    ClassCastException classCastException = e;
                                    Throwable th8 = th7;
                                    new WrongType(classCastException, "substring", 3, obj17);
                                    throw th8;
                                }
                            } catch (ClassCastException e2) {
                                ClassCastException classCastException2 = e2;
                                Throwable th9 = th6;
                                new WrongType(classCastException2, "substring", 2, obj16);
                                throw th9;
                            }
                        } catch (ClassCastException e3) {
                            ClassCastException classCastException3 = e3;
                            Throwable th10 = th5;
                            new WrongType(classCastException3, "substring", 1, obj14);
                            throw th10;
                        }
                    } else if (Scheme.numEqu.apply2(obj12, Lit73) != Boolean.FALSE) {
                        obj4 = str;
                    } else {
                        Object[] objArr8 = new Object[2];
                        objArr8[0] = obj11;
                        Object[] objArr9 = objArr8;
                        Object[] objArr10 = objArr9;
                        Object[] objArr11 = objArr9;
                        Object obj18 = str;
                        Object obj19 = obj18;
                        try {
                            CharSequence charSequence2 = (CharSequence) obj18;
                            Object obj20 = obj12;
                            Object obj21 = obj20;
                            try {
                                objArr11[1] = strings.substring(charSequence2, ((Number) obj20).intValue(), n);
                                obj4 = strings.stringAppend(objArr10);
                            } catch (ClassCastException e4) {
                                ClassCastException classCastException4 = e4;
                                Throwable th11 = th4;
                                new WrongType(classCastException4, "substring", 2, obj21);
                                throw th11;
                            }
                        } catch (ClassCastException e5) {
                            ClassCastException classCastException5 = e5;
                            Throwable th12 = th3;
                            new WrongType(classCastException5, "substring", 1, obj19);
                            throw th12;
                        }
                    }
                }
                return obj4;
            } catch (ClassCastException e6) {
                ClassCastException classCastException6 = e6;
                Throwable th13 = th2;
                new WrongType(classCastException6, "string-length", 1, obj8);
                throw th13;
            }
        } catch (ClassCastException e7) {
            ClassCastException classCastException7 = e7;
            Throwable th14 = th;
            new WrongType(classCastException7, "string-length", 1, obj6);
            throw th14;
        }
    }

    public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj4 = obj;
        Object obj5 = obj2;
        Object obj6 = obj3;
        switch (moduleMethod2.selector) {
            case 18:
                return pregexpReadPattern(obj4, obj5, obj6);
            case 19:
                return pregexpReadBranch(obj4, obj5, obj6);
            case 20:
                return pregexpReadPiece(obj4, obj5, obj6);
            case 21:
                return pregexpReadEscapedNumber(obj4, obj5, obj6);
            case 22:
                return pregexpReadEscapedChar(obj4, obj5, obj6);
            case 23:
                return pregexpReadPosixCharClass(obj4, obj5, obj6);
            case 24:
                return pregexpReadClusterType(obj4, obj5, obj6);
            case 25:
                return pregexpReadSubpattern(obj4, obj5, obj6);
            case 26:
                return pregexpWrapQuantifierIfAny(obj4, obj5, obj6);
            case 27:
                return pregexpReadNums(obj4, obj5, obj6);
            case 29:
                return pregexpReadCharList(obj4, obj5, obj6);
            case 32:
                return isPregexpAtWordBoundary(obj4, obj5, obj6);
            case 48:
                return pregexpReplace(obj4, obj5, obj6);
            case 49:
                return pregexpReplace$St(obj4, obj5, obj6);
            default:
                return super.apply3(moduleMethod2, obj4, obj5, obj6);
        }
    }

    public static Object pregexpQuote(Object obj) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Object s = obj;
        Object obj2 = s;
        Object obj3 = obj2;
        try {
            Object valueOf = Integer.valueOf(strings.stringLength((CharSequence) obj2) - 1);
            LList lList = LList.Empty;
            while (true) {
                LList lList2 = lList;
                Object obj4 = valueOf;
                if (Scheme.numLss.apply2(obj4, Lit73) != Boolean.FALSE) {
                    LList lList3 = lList2;
                    LList lList4 = lList3;
                    try {
                        return strings.list$To$String(lList3);
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th5 = th2;
                        new WrongType(classCastException, "list->string", 1, (Object) lList4);
                        throw th5;
                    }
                } else {
                    valueOf = AddOp.$Mn.apply2(obj4, Lit8);
                    Object obj5 = s;
                    Object obj6 = obj5;
                    try {
                        CharSequence charSequence = (CharSequence) obj5;
                        Object obj7 = obj4;
                        Object obj8 = obj7;
                        try {
                            char c = strings.stringRef(charSequence, ((Number) obj7).intValue());
                            if (C1245lists.memv(Char.make(c), Lit116) != Boolean.FALSE) {
                                lList = C1245lists.cons(Lit19, C1245lists.cons(Char.make(c), lList2));
                            } else {
                                lList = C1245lists.cons(Char.make(c), lList2);
                            }
                        } catch (ClassCastException e2) {
                            ClassCastException classCastException2 = e2;
                            Throwable th6 = th4;
                            new WrongType(classCastException2, "string-ref", 2, obj8);
                            throw th6;
                        }
                    } catch (ClassCastException e3) {
                        ClassCastException classCastException3 = e3;
                        Throwable th7 = th3;
                        new WrongType(classCastException3, "string-ref", 1, obj6);
                        throw th7;
                    }
                }
            }
        } catch (ClassCastException e4) {
            ClassCastException classCastException4 = e4;
            Throwable th8 = th;
            new WrongType(classCastException4, "string-length", 1, obj3);
            throw th8;
        }
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        switch (moduleMethod2.selector) {
            case 16:
                return pregexpReverse$Ex(obj2);
            case 28:
                return pregexpInvertCharList(obj2);
            case 31:
                return isPregexpCharWord(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 35:
                return pregexpMakeBackrefList(obj2);
            case 44:
                return pregexp(obj2);
            case 50:
                return pregexpQuote(obj2);
            default:
                return super.apply1(moduleMethod2, obj2);
        }
    }
}

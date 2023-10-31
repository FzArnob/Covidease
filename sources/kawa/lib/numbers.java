package kawa.lib;

import android.support.p000v4.app.FragmentTransaction;
import gnu.expr.GenericProc;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.Arithmetic;
import gnu.kawa.functions.DivideOp;
import gnu.kawa.functions.Format;
import gnu.kawa.functions.GetNamedPart;
import gnu.kawa.functions.MultiplyOp;
import gnu.kawa.lispexpr.LangObjType;
import gnu.kawa.lispexpr.LispReader;
import gnu.lists.Consumer;
import gnu.lists.FString;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;
import gnu.math.BitOps;
import gnu.math.Complex;
import gnu.math.DComplex;
import gnu.math.DFloNum;
import gnu.math.Duration;
import gnu.math.IntNum;
import gnu.math.Numeric;
import gnu.math.Quantity;
import gnu.math.RatNum;
import gnu.math.RealNum;
import gnu.math.Unit;
import java.math.BigDecimal;
import java.math.BigInteger;
import kawa.standard.Scheme;

/* compiled from: numbers.scm */
public class numbers extends ModuleBody {
    public static final numbers $instance;
    static final IntNum Lit0 = IntNum.make(0);
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit12;
    static final SimpleSymbol Lit13;
    static final SimpleSymbol Lit14;
    static final SimpleSymbol Lit15;
    static final SimpleSymbol Lit16;
    static final SimpleSymbol Lit17;
    static final SimpleSymbol Lit18;
    static final SimpleSymbol Lit19;
    static final IntNum Lit2 = IntNum.make(1);
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
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    public static final ModuleMethod abs;
    public static final ModuleMethod acos;
    public static final ModuleMethod angle;
    public static final ModuleMethod asin;
    public static final GenericProc atan = null;
    public static final ModuleMethod bitwise$Mnbit$Mncount;
    public static final ModuleMethod bitwise$Mnbit$Mnfield;
    public static final ModuleMethod bitwise$Mnbit$Mnset$Qu;
    public static final ModuleMethod bitwise$Mncopy$Mnbit;
    public static final ModuleMethod bitwise$Mncopy$Mnbit$Mnfield;
    public static final ModuleMethod bitwise$Mnfirst$Mnbit$Mnset;
    public static final ModuleMethod bitwise$Mnif;
    public static final ModuleMethod bitwise$Mnlength;
    public static final ModuleMethod bitwise$Mnreverse$Mnbit$Mnfield;
    public static final ModuleMethod bitwise$Mnrotate$Mnbit$Mnfield;
    public static final ModuleMethod ceiling;
    public static final ModuleMethod complex$Qu;
    public static final ModuleMethod cos;
    public static final ModuleMethod denominator;
    public static final ModuleMethod div$Mnand$Mnmod;
    public static final ModuleMethod div0$Mnand$Mnmod0;
    public static final ModuleMethod duration;
    public static final ModuleMethod exact;
    public static final ModuleMethod exact$Mn$Grinexact;
    public static final ModuleMethod exact$Qu;
    public static final ModuleMethod exp;
    public static final ModuleMethod floor;
    public static final ModuleMethod gcd;
    public static final ModuleMethod imag$Mnpart;
    public static final ModuleMethod inexact;
    public static final ModuleMethod inexact$Mn$Grexact;
    public static final ModuleMethod inexact$Qu;
    public static final ModuleMethod integer$Qu;
    static final ModuleMethod lambda$Fn1;
    static final ModuleMethod lambda$Fn2;
    static final ModuleMethod lambda$Fn3;
    static final ModuleMethod lambda$Fn4;
    public static final ModuleMethod lcm;
    public static final ModuleMethod log;
    public static final ModuleMethod logcount;
    public static final ModuleMethod logop;
    public static final ModuleMethod logtest;
    public static final ModuleMethod magnitude;
    public static final ModuleMethod make$Mnpolar;
    public static final ModuleMethod make$Mnquantity;
    public static final ModuleMethod make$Mnrectangular;
    public static final ModuleMethod max;
    public static final ModuleMethod min;
    public static final ModuleMethod negative$Qu;
    public static final ModuleMethod number$Mn$Grstring;
    public static final ModuleMethod number$Qu;
    public static final ModuleMethod numerator;
    public static final ModuleMethod positive$Qu;
    public static final ModuleMethod quantity$Mn$Grnumber;
    public static final ModuleMethod quantity$Mn$Grunit;
    public static final ModuleMethod quantity$Qu;
    public static final ModuleMethod rational$Qu;
    public static final ModuleMethod rationalize;
    public static final ModuleMethod real$Mnpart;
    public static final ModuleMethod real$Qu;
    public static final ModuleMethod round;
    public static final ModuleMethod sin;
    public static final GenericProc sqrt = null;
    public static final ModuleMethod string$Mn$Grnumber;
    public static final ModuleMethod tan;
    public static final ModuleMethod truncate;
    public static final ModuleMethod zero$Qu;

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
        numbers numbers;
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
        new SimpleSymbol("duration");
        Lit63 = (SimpleSymbol) simpleSymbol.readResolve();
        new SimpleSymbol("make-quantity");
        Lit62 = (SimpleSymbol) simpleSymbol2.readResolve();
        new SimpleSymbol("quantity->unit");
        Lit61 = (SimpleSymbol) simpleSymbol3.readResolve();
        new SimpleSymbol("quantity->number");
        Lit60 = (SimpleSymbol) simpleSymbol4.readResolve();
        new SimpleSymbol("string->number");
        Lit59 = (SimpleSymbol) simpleSymbol5.readResolve();
        new SimpleSymbol("number->string");
        Lit58 = (SimpleSymbol) simpleSymbol6.readResolve();
        new SimpleSymbol("bitwise-reverse-bit-field");
        Lit57 = (SimpleSymbol) simpleSymbol7.readResolve();
        new SimpleSymbol("bitwise-rotate-bit-field");
        Lit56 = (SimpleSymbol) simpleSymbol8.readResolve();
        new SimpleSymbol("bitwise-first-bit-set");
        Lit55 = (SimpleSymbol) simpleSymbol9.readResolve();
        new SimpleSymbol("bitwise-length");
        Lit54 = (SimpleSymbol) simpleSymbol10.readResolve();
        new SimpleSymbol("bitwise-bit-count");
        Lit53 = (SimpleSymbol) simpleSymbol11.readResolve();
        new SimpleSymbol("logcount");
        Lit52 = (SimpleSymbol) simpleSymbol12.readResolve();
        new SimpleSymbol("logtest");
        Lit51 = (SimpleSymbol) simpleSymbol13.readResolve();
        new SimpleSymbol("bitwise-if");
        Lit50 = (SimpleSymbol) simpleSymbol14.readResolve();
        new SimpleSymbol("bitwise-bit-field");
        Lit49 = (SimpleSymbol) simpleSymbol15.readResolve();
        new SimpleSymbol("bitwise-copy-bit-field");
        Lit48 = (SimpleSymbol) simpleSymbol16.readResolve();
        new SimpleSymbol("bitwise-copy-bit");
        Lit47 = (SimpleSymbol) simpleSymbol17.readResolve();
        new SimpleSymbol("bitwise-bit-set?");
        Lit46 = (SimpleSymbol) simpleSymbol18.readResolve();
        new SimpleSymbol("logop");
        Lit45 = (SimpleSymbol) simpleSymbol19.readResolve();
        new SimpleSymbol("inexact->exact");
        Lit44 = (SimpleSymbol) simpleSymbol20.readResolve();
        new SimpleSymbol("exact->inexact");
        Lit43 = (SimpleSymbol) simpleSymbol21.readResolve();
        new SimpleSymbol("exact");
        Lit42 = (SimpleSymbol) simpleSymbol22.readResolve();
        new SimpleSymbol("inexact");
        Lit41 = (SimpleSymbol) simpleSymbol23.readResolve();
        new SimpleSymbol("angle");
        Lit40 = (SimpleSymbol) simpleSymbol24.readResolve();
        new SimpleSymbol("magnitude");
        Lit39 = (SimpleSymbol) simpleSymbol25.readResolve();
        new SimpleSymbol("imag-part");
        Lit38 = (SimpleSymbol) simpleSymbol26.readResolve();
        new SimpleSymbol("real-part");
        Lit37 = (SimpleSymbol) simpleSymbol27.readResolve();
        new SimpleSymbol("make-polar");
        Lit36 = (SimpleSymbol) simpleSymbol28.readResolve();
        new SimpleSymbol("make-rectangular");
        Lit35 = (SimpleSymbol) simpleSymbol29.readResolve();
        new SimpleSymbol("acos");
        Lit34 = (SimpleSymbol) simpleSymbol30.readResolve();
        new SimpleSymbol("asin");
        Lit33 = (SimpleSymbol) simpleSymbol31.readResolve();
        new SimpleSymbol("tan");
        Lit32 = (SimpleSymbol) simpleSymbol32.readResolve();
        new SimpleSymbol("cos");
        Lit31 = (SimpleSymbol) simpleSymbol33.readResolve();
        new SimpleSymbol("sin");
        Lit30 = (SimpleSymbol) simpleSymbol34.readResolve();
        new SimpleSymbol("log");
        Lit29 = (SimpleSymbol) simpleSymbol35.readResolve();
        new SimpleSymbol("exp");
        Lit28 = (SimpleSymbol) simpleSymbol36.readResolve();
        new SimpleSymbol("rationalize");
        Lit27 = (SimpleSymbol) simpleSymbol37.readResolve();
        new SimpleSymbol("round");
        Lit26 = (SimpleSymbol) simpleSymbol38.readResolve();
        new SimpleSymbol("truncate");
        Lit25 = (SimpleSymbol) simpleSymbol39.readResolve();
        new SimpleSymbol("ceiling");
        Lit24 = (SimpleSymbol) simpleSymbol40.readResolve();
        new SimpleSymbol("floor");
        Lit23 = (SimpleSymbol) simpleSymbol41.readResolve();
        new SimpleSymbol("denominator");
        Lit22 = (SimpleSymbol) simpleSymbol42.readResolve();
        new SimpleSymbol("numerator");
        Lit21 = (SimpleSymbol) simpleSymbol43.readResolve();
        new SimpleSymbol("lcm");
        Lit20 = (SimpleSymbol) simpleSymbol44.readResolve();
        new SimpleSymbol("gcd");
        Lit19 = (SimpleSymbol) simpleSymbol45.readResolve();
        new SimpleSymbol("div0-and-mod0");
        Lit18 = (SimpleSymbol) simpleSymbol46.readResolve();
        new SimpleSymbol("div-and-mod");
        Lit17 = (SimpleSymbol) simpleSymbol47.readResolve();
        new SimpleSymbol("abs");
        Lit16 = (SimpleSymbol) simpleSymbol48.readResolve();
        new SimpleSymbol("min");
        Lit15 = (SimpleSymbol) simpleSymbol49.readResolve();
        new SimpleSymbol("max");
        Lit14 = (SimpleSymbol) simpleSymbol50.readResolve();
        new SimpleSymbol("negative?");
        Lit13 = (SimpleSymbol) simpleSymbol51.readResolve();
        new SimpleSymbol("positive?");
        Lit12 = (SimpleSymbol) simpleSymbol52.readResolve();
        new SimpleSymbol("zero?");
        Lit11 = (SimpleSymbol) simpleSymbol53.readResolve();
        new SimpleSymbol("inexact?");
        Lit10 = (SimpleSymbol) simpleSymbol54.readResolve();
        new SimpleSymbol("exact?");
        Lit9 = (SimpleSymbol) simpleSymbol55.readResolve();
        new SimpleSymbol("integer?");
        Lit8 = (SimpleSymbol) simpleSymbol56.readResolve();
        new SimpleSymbol("rational?");
        Lit7 = (SimpleSymbol) simpleSymbol57.readResolve();
        new SimpleSymbol("real?");
        Lit6 = (SimpleSymbol) simpleSymbol58.readResolve();
        new SimpleSymbol("complex?");
        Lit5 = (SimpleSymbol) simpleSymbol59.readResolve();
        new SimpleSymbol("quantity?");
        Lit4 = (SimpleSymbol) simpleSymbol60.readResolve();
        new SimpleSymbol("number?");
        Lit3 = (SimpleSymbol) simpleSymbol61.readResolve();
        new SimpleSymbol("signum");
        Lit1 = (SimpleSymbol) simpleSymbol62.readResolve();
        new numbers();
        $instance = numbers;
        numbers numbers2 = $instance;
        new ModuleMethod(numbers2, 1, Lit3, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        number$Qu = moduleMethod;
        new ModuleMethod(numbers2, 2, Lit4, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        quantity$Qu = moduleMethod2;
        new ModuleMethod(numbers2, 3, Lit5, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        complex$Qu = moduleMethod3;
        new ModuleMethod(numbers2, 4, Lit6, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        real$Qu = moduleMethod4;
        new ModuleMethod(numbers2, 5, Lit7, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        rational$Qu = moduleMethod5;
        new ModuleMethod(numbers2, 6, Lit8, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        integer$Qu = moduleMethod6;
        new ModuleMethod(numbers2, 7, Lit9, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        exact$Qu = moduleMethod7;
        new ModuleMethod(numbers2, 8, Lit10, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        inexact$Qu = moduleMethod8;
        new ModuleMethod(numbers2, 9, Lit11, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        zero$Qu = moduleMethod9;
        new ModuleMethod(numbers2, 10, Lit12, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        positive$Qu = moduleMethod10;
        new ModuleMethod(numbers2, 11, Lit13, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        negative$Qu = moduleMethod11;
        new ModuleMethod(numbers2, 12, Lit14, -4096);
        max = moduleMethod12;
        new ModuleMethod(numbers2, 13, Lit15, -4096);
        min = moduleMethod13;
        new ModuleMethod(numbers2, 14, Lit16, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        abs = moduleMethod14;
        new ModuleMethod(numbers2, 15, Lit17, 8194);
        div$Mnand$Mnmod = moduleMethod15;
        new ModuleMethod(numbers2, 16, Lit18, 8194);
        div0$Mnand$Mnmod0 = moduleMethod16;
        new ModuleMethod(numbers2, 17, Lit19, -4096);
        gcd = moduleMethod17;
        new ModuleMethod(numbers2, 18, Lit20, -4096);
        lcm = moduleMethod18;
        new ModuleMethod(numbers2, 19, Lit21, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        numerator = moduleMethod19;
        new ModuleMethod(numbers2, 20, Lit22, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        denominator = moduleMethod20;
        new ModuleMethod(numbers2, 21, Lit23, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        floor = moduleMethod21;
        new ModuleMethod(numbers2, 22, Lit24, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ceiling = moduleMethod22;
        new ModuleMethod(numbers2, 23, Lit25, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        truncate = moduleMethod23;
        new ModuleMethod(numbers2, 24, Lit26, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        round = moduleMethod24;
        new ModuleMethod(numbers2, 25, Lit27, 8194);
        rationalize = moduleMethod25;
        new ModuleMethod(numbers2, 26, Lit28, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        exp = moduleMethod26;
        new ModuleMethod(numbers2, 27, Lit29, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        log = moduleMethod27;
        new ModuleMethod(numbers2, 28, Lit30, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        sin = moduleMethod28;
        new ModuleMethod(numbers2, 29, Lit31, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        cos = moduleMethod29;
        new ModuleMethod(numbers2, 30, Lit32, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        tan = moduleMethod30;
        new ModuleMethod(numbers2, 31, Lit33, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        asin = moduleMethod31;
        new ModuleMethod(numbers2, 32, Lit34, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        acos = moduleMethod32;
        new ModuleMethod(numbers2, 33, (Object) null, 8194);
        ModuleMethod moduleMethod66 = moduleMethod33;
        moduleMethod66.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/numbers.scm:146");
        lambda$Fn1 = moduleMethod66;
        new ModuleMethod(numbers2, 34, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ModuleMethod moduleMethod67 = moduleMethod34;
        moduleMethod67.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/numbers.scm:148");
        lambda$Fn2 = moduleMethod67;
        new ModuleMethod(numbers2, 35, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ModuleMethod moduleMethod68 = moduleMethod35;
        moduleMethod68.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/numbers.scm:152");
        lambda$Fn3 = moduleMethod68;
        new ModuleMethod(numbers2, 36, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ModuleMethod moduleMethod69 = moduleMethod36;
        moduleMethod69.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/numbers.scm:156");
        lambda$Fn4 = moduleMethod69;
        new ModuleMethod(numbers2, 37, Lit35, 8194);
        make$Mnrectangular = moduleMethod37;
        new ModuleMethod(numbers2, 38, Lit36, 8194);
        make$Mnpolar = moduleMethod38;
        new ModuleMethod(numbers2, 39, Lit37, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        real$Mnpart = moduleMethod39;
        new ModuleMethod(numbers2, 40, Lit38, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        imag$Mnpart = moduleMethod40;
        new ModuleMethod(numbers2, 41, Lit39, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        magnitude = moduleMethod41;
        new ModuleMethod(numbers2, 42, Lit40, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        angle = moduleMethod42;
        new ModuleMethod(numbers2, 43, Lit41, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        inexact = moduleMethod43;
        new ModuleMethod(numbers2, 44, Lit42, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        exact = moduleMethod44;
        new ModuleMethod(numbers2, 45, Lit43, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        exact$Mn$Grinexact = moduleMethod45;
        new ModuleMethod(numbers2, 46, Lit44, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        inexact$Mn$Grexact = moduleMethod46;
        new ModuleMethod(numbers2, 47, Lit45, 12291);
        logop = moduleMethod47;
        new ModuleMethod(numbers2, 48, Lit46, 8194);
        bitwise$Mnbit$Mnset$Qu = moduleMethod48;
        new ModuleMethod(numbers2, 49, Lit47, 12291);
        bitwise$Mncopy$Mnbit = moduleMethod49;
        new ModuleMethod(numbers2, 50, Lit48, 16388);
        bitwise$Mncopy$Mnbit$Mnfield = moduleMethod50;
        new ModuleMethod(numbers2, 51, Lit49, 12291);
        bitwise$Mnbit$Mnfield = moduleMethod51;
        new ModuleMethod(numbers2, 52, Lit50, 12291);
        bitwise$Mnif = moduleMethod52;
        new ModuleMethod(numbers2, 53, Lit51, 8194);
        logtest = moduleMethod53;
        new ModuleMethod(numbers2, 54, Lit52, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        logcount = moduleMethod54;
        new ModuleMethod(numbers2, 55, Lit53, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        bitwise$Mnbit$Mncount = moduleMethod55;
        new ModuleMethod(numbers2, 56, Lit54, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        bitwise$Mnlength = moduleMethod56;
        new ModuleMethod(numbers2, 57, Lit55, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        bitwise$Mnfirst$Mnbit$Mnset = moduleMethod57;
        new ModuleMethod(numbers2, 58, Lit56, 16388);
        bitwise$Mnrotate$Mnbit$Mnfield = moduleMethod58;
        new ModuleMethod(numbers2, 59, Lit57, 12291);
        bitwise$Mnreverse$Mnbit$Mnfield = moduleMethod59;
        new ModuleMethod(numbers2, 60, Lit58, 8193);
        number$Mn$Grstring = moduleMethod60;
        new ModuleMethod(numbers2, 62, Lit59, 8193);
        string$Mn$Grnumber = moduleMethod61;
        new ModuleMethod(numbers2, 64, Lit60, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        quantity$Mn$Grnumber = moduleMethod62;
        new ModuleMethod(numbers2, 65, Lit61, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        quantity$Mn$Grunit = moduleMethod63;
        new ModuleMethod(numbers2, 66, Lit62, 8194);
        make$Mnquantity = moduleMethod64;
        new ModuleMethod(numbers2, 67, Lit63, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        duration = moduleMethod65;
        $instance.run();
    }

    public numbers() {
        ModuleInfo.register(this);
    }

    public static CharSequence number$To$String(Number number) {
        return number$To$String(number, 10);
    }

    public static Object string$To$Number(CharSequence charSequence) {
        return string$To$Number(charSequence, 10);
    }

    public final void run(CallContext $ctx) {
        GenericProc genericProc;
        GenericProc genericProc2;
        Consumer consumer = $ctx.consumer;
        new GenericProc("atan");
        atan = genericProc;
        atan.setProperties(new Object[]{lambda$Fn1, lambda$Fn2});
        new GenericProc("sqrt");
        sqrt = genericProc2;
        sqrt.setProperties(new Object[]{lambda$Fn3, lambda$Fn4});
    }

    public static boolean isNumber(Object x) {
        return x instanceof Number;
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 1:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
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
            case 8:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 9:
                CallContext callContext3 = callContext2;
                Object obj3 = obj2;
                Object obj4 = obj3;
                if (!(obj3 instanceof Number)) {
                    return -786431;
                }
                callContext3.value1 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 10:
                CallContext callContext4 = callContext2;
                Object obj5 = obj2;
                Object obj6 = obj5;
                if (RealNum.asRealNumOrNull(obj5) == null) {
                    return -786431;
                }
                callContext4.value1 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 11:
                CallContext callContext5 = callContext2;
                Object obj7 = obj2;
                Object obj8 = obj7;
                if (RealNum.asRealNumOrNull(obj7) == null) {
                    return -786431;
                }
                callContext5.value1 = obj8;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 14:
                CallContext callContext6 = callContext2;
                Object obj9 = obj2;
                Object obj10 = obj9;
                if (!(obj9 instanceof Number)) {
                    return -786431;
                }
                callContext6.value1 = obj10;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 19:
                CallContext callContext7 = callContext2;
                Object obj11 = obj2;
                Object obj12 = obj11;
                if (RatNum.asRatNumOrNull(obj11) == null) {
                    return -786431;
                }
                callContext7.value1 = obj12;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 20:
                CallContext callContext8 = callContext2;
                Object obj13 = obj2;
                Object obj14 = obj13;
                if (RatNum.asRatNumOrNull(obj13) == null) {
                    return -786431;
                }
                callContext8.value1 = obj14;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 21:
                CallContext callContext9 = callContext2;
                Object obj15 = obj2;
                Object obj16 = obj15;
                if (RealNum.asRealNumOrNull(obj15) == null) {
                    return -786431;
                }
                callContext9.value1 = obj16;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 22:
                CallContext callContext10 = callContext2;
                Object obj17 = obj2;
                Object obj18 = obj17;
                if (RealNum.asRealNumOrNull(obj17) == null) {
                    return -786431;
                }
                callContext10.value1 = obj18;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 23:
                CallContext callContext11 = callContext2;
                Object obj19 = obj2;
                Object obj20 = obj19;
                if (RealNum.asRealNumOrNull(obj19) == null) {
                    return -786431;
                }
                callContext11.value1 = obj20;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 24:
                CallContext callContext12 = callContext2;
                Object obj21 = obj2;
                Object obj22 = obj21;
                if (RealNum.asRealNumOrNull(obj21) == null) {
                    return -786431;
                }
                callContext12.value1 = obj22;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 26:
                CallContext callContext13 = callContext2;
                Object obj23 = obj2;
                Object obj24 = obj23;
                if (!(obj23 instanceof Complex)) {
                    return -786431;
                }
                callContext13.value1 = obj24;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 27:
                CallContext callContext14 = callContext2;
                Object obj25 = obj2;
                Object obj26 = obj25;
                if (!(obj25 instanceof Complex)) {
                    return -786431;
                }
                callContext14.value1 = obj26;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 28:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 29:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 30:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 31:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 32:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 34:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 35:
                CallContext callContext15 = callContext2;
                Object obj27 = obj2;
                Object obj28 = obj27;
                if (!(obj27 instanceof Quantity)) {
                    return -786431;
                }
                callContext15.value1 = obj28;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 36:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 39:
                CallContext callContext16 = callContext2;
                Object obj29 = obj2;
                Object obj30 = obj29;
                if (!(obj29 instanceof Complex)) {
                    return -786431;
                }
                callContext16.value1 = obj30;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 40:
                CallContext callContext17 = callContext2;
                Object obj31 = obj2;
                Object obj32 = obj31;
                if (!(obj31 instanceof Complex)) {
                    return -786431;
                }
                callContext17.value1 = obj32;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 41:
                CallContext callContext18 = callContext2;
                Object obj33 = obj2;
                Object obj34 = obj33;
                if (!(obj33 instanceof Number)) {
                    return -786431;
                }
                callContext18.value1 = obj34;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 42:
                CallContext callContext19 = callContext2;
                Object obj35 = obj2;
                Object obj36 = obj35;
                if (!(obj35 instanceof Complex)) {
                    return -786431;
                }
                callContext19.value1 = obj36;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 43:
                CallContext callContext20 = callContext2;
                Object obj37 = obj2;
                Object obj38 = obj37;
                if (!(obj37 instanceof Number)) {
                    return -786431;
                }
                callContext20.value1 = obj38;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 44:
                CallContext callContext21 = callContext2;
                Object obj39 = obj2;
                Object obj40 = obj39;
                if (!(obj39 instanceof Number)) {
                    return -786431;
                }
                callContext21.value1 = obj40;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 45:
                CallContext callContext22 = callContext2;
                Object obj41 = obj2;
                Object obj42 = obj41;
                if (!(obj41 instanceof Number)) {
                    return -786431;
                }
                callContext22.value1 = obj42;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 46:
                CallContext callContext23 = callContext2;
                Object obj43 = obj2;
                Object obj44 = obj43;
                if (!(obj43 instanceof Number)) {
                    return -786431;
                }
                callContext23.value1 = obj44;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 54:
                CallContext callContext24 = callContext2;
                Object obj45 = obj2;
                Object obj46 = obj45;
                if (IntNum.asIntNumOrNull(obj45) == null) {
                    return -786431;
                }
                callContext24.value1 = obj46;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 55:
                CallContext callContext25 = callContext2;
                Object obj47 = obj2;
                Object obj48 = obj47;
                if (IntNum.asIntNumOrNull(obj47) == null) {
                    return -786431;
                }
                callContext25.value1 = obj48;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 56:
                CallContext callContext26 = callContext2;
                Object obj49 = obj2;
                Object obj50 = obj49;
                if (IntNum.asIntNumOrNull(obj49) == null) {
                    return -786431;
                }
                callContext26.value1 = obj50;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 57:
                CallContext callContext27 = callContext2;
                Object obj51 = obj2;
                Object obj52 = obj51;
                if (IntNum.asIntNumOrNull(obj51) == null) {
                    return -786431;
                }
                callContext27.value1 = obj52;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 60:
                CallContext callContext28 = callContext2;
                Object obj53 = obj2;
                Object obj54 = obj53;
                if (!(obj53 instanceof Number)) {
                    return -786431;
                }
                callContext28.value1 = obj54;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 62:
                CallContext callContext29 = callContext2;
                Object obj55 = obj2;
                Object obj56 = obj55;
                if (!(obj55 instanceof CharSequence)) {
                    return -786431;
                }
                callContext29.value1 = obj56;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 64:
                CallContext callContext30 = callContext2;
                Object obj57 = obj2;
                Object obj58 = obj57;
                if (!(obj57 instanceof Quantity)) {
                    return -786431;
                }
                callContext30.value1 = obj58;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 65:
                CallContext callContext31 = callContext2;
                Object obj59 = obj2;
                Object obj60 = obj59;
                if (!(obj59 instanceof Quantity)) {
                    return -786431;
                }
                callContext31.value1 = obj60;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 67:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            default:
                return super.match1(moduleMethod2, obj2, callContext2);
        }
    }

    public static boolean isQuantity(Object x) {
        return x instanceof Quantity;
    }

    public static boolean isComplex(Object x) {
        return x instanceof Complex;
    }

    public static boolean isReal(Object x) {
        return RealNum.asRealNumOrNull(x) != null;
    }

    public static boolean isRational(Object x) {
        return RatNum.asRatNumOrNull(x) != null;
    }

    public static boolean isInteger(Object obj) {
        boolean z;
        boolean z2;
        Throwable th;
        Object x = obj;
        boolean x2 = x instanceof IntNum;
        if (x2) {
            z2 = x2;
        } else {
            boolean x3 = x instanceof DFloNum;
            if (x3) {
                Object obj2 = x;
                Object obj3 = obj2;
                try {
                    z = Math.IEEEremainder(((DFloNum) obj2).doubleValue(), 1.0d) == 0.0d;
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th2 = th;
                    new WrongType(classCastException, "gnu.math.DFloNum.doubleValue()", 1, obj3);
                    throw th2;
                }
            } else {
                z = x3;
            }
            boolean x4 = z;
            if (x4) {
                z2 = x4;
            } else {
                boolean x5 = x instanceof Number;
                if (x5) {
                    boolean x6 = x instanceof Long;
                    if (x6) {
                        z2 = x6;
                    } else {
                        boolean x7 = x instanceof Integer;
                        if (x7) {
                            z2 = x7;
                        } else {
                            boolean x8 = x instanceof Short;
                            z2 = x8 ? x8 : x instanceof BigInteger;
                        }
                    }
                } else {
                    z2 = x5;
                }
            }
        }
        return z2;
    }

    public static boolean isExact(Object obj) {
        Object x = obj;
        boolean x2 = x instanceof Number;
        return x2 ? Arithmetic.isExact((Number) x) : x2;
    }

    public static boolean isInexact(Object obj) {
        Object x = obj;
        boolean x2 = x instanceof Number;
        return x2 ? ((Arithmetic.isExact((Number) x) ? 1 : 0) + true) & true : x2;
    }

    public static boolean isZero(Number number) {
        boolean z;
        Number x = number;
        if (x instanceof Numeric) {
            z = ((Numeric) x).isZero();
        } else if (x instanceof BigInteger) {
            z = Scheme.numEqu.apply2(Lit0, GetNamedPart.getNamedPart.apply2((BigInteger) x, Lit1)) != Boolean.FALSE;
        } else if (x instanceof BigDecimal) {
            z = Scheme.numEqu.apply2(Lit0, GetNamedPart.getNamedPart.apply2((BigDecimal) x, Lit1)) != Boolean.FALSE;
        } else {
            z = x.doubleValue() == 0.0d;
        }
        return z;
    }

    public static boolean isPositive(RealNum x) {
        return x.sign() > 0;
    }

    public static boolean isNegative(RealNum x) {
        return x.isNegative();
    }

    public static Object max(Object... objArr) {
        Throwable th;
        Throwable th2;
        Object[] args = objArr;
        int length = args.length;
        Object obj = args[0];
        Object obj2 = obj;
        try {
            RealNum result = LangObjType.coerceRealNum(obj);
            int n = length;
            int i = 1;
            while (i < n) {
                RealNum realNum = result;
                Object obj3 = args[i];
                Object obj4 = obj3;
                try {
                    result = realNum.max(LangObjType.coerceRealNum(obj3));
                    i++;
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th3 = th2;
                    new WrongType(classCastException, "gnu.math.RealNum.max(real)", 2, obj4);
                    throw th3;
                }
            }
            return result;
        } catch (ClassCastException e2) {
            ClassCastException classCastException2 = e2;
            Throwable th4 = th;
            new WrongType(classCastException2, "result", -2, obj2);
            throw th4;
        }
    }

    public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object[] objArr2 = objArr;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 12:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 13:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 17:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 18:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            default:
                return super.matchN(moduleMethod2, objArr2, callContext2);
        }
    }

    public static Object min(Object... objArr) {
        Throwable th;
        Throwable th2;
        Object[] args = objArr;
        int length = args.length;
        Object obj = args[0];
        Object obj2 = obj;
        try {
            RealNum result = LangObjType.coerceRealNum(obj);
            int n = length;
            int i = 0;
            while (i < n) {
                RealNum realNum = result;
                Object obj3 = args[i];
                Object obj4 = obj3;
                try {
                    result = realNum.min(LangObjType.coerceRealNum(obj3));
                    i++;
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th3 = th2;
                    new WrongType(classCastException, "gnu.math.RealNum.min(real)", 2, obj4);
                    throw th3;
                }
            }
            return result;
        } catch (ClassCastException e2) {
            ClassCastException classCastException2 = e2;
            Throwable th4 = th;
            new WrongType(classCastException2, "result", -2, obj2);
            throw th4;
        }
    }

    public static Number abs(Number number) {
        Number x;
        Number x2 = number;
        if (x2 instanceof Numeric) {
            x = ((Numeric) x2).abs();
        } else if (Scheme.numGEq.apply2(x2, Lit0) != Boolean.FALSE) {
            x = x2;
        } else {
            x = (Number) AddOp.$Mn.apply1(x2);
        }
        return x;
    }

    public static Object divAndMod(RealNum realNum, RealNum realNum2) {
        Throwable th;
        Throwable th2;
        RealNum x = realNum;
        RealNum y = realNum2;
        Object apply2 = DivideOp.div.apply2(x, y);
        Object obj = apply2;
        try {
            RealNum q = LangObjType.coerceRealNum(apply2);
            Object apply22 = AddOp.$Mn.apply2(x, MultiplyOp.$St.apply2(q, y));
            Object obj2 = apply22;
            try {
                RealNum r = LangObjType.coerceRealNum(apply22);
                Object[] objArr = new Object[2];
                objArr[0] = q;
                Object[] objArr2 = objArr;
                objArr2[1] = r;
                return misc.values(objArr2);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th3 = th2;
                new WrongType(classCastException, "r", -2, obj2);
                throw th3;
            }
        } catch (ClassCastException e2) {
            ClassCastException classCastException2 = e2;
            Throwable th4 = th;
            new WrongType(classCastException2, "q", -2, obj);
            throw th4;
        }
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 15:
                CallContext callContext3 = callContext2;
                Object obj5 = obj3;
                Object obj6 = obj5;
                if (RealNum.asRealNumOrNull(obj5) == null) {
                    return -786431;
                }
                callContext3.value1 = obj6;
                CallContext callContext4 = callContext2;
                Object obj7 = obj4;
                Object obj8 = obj7;
                if (RealNum.asRealNumOrNull(obj7) == null) {
                    return -786430;
                }
                callContext4.value2 = obj8;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 16:
                CallContext callContext5 = callContext2;
                Object obj9 = obj3;
                Object obj10 = obj9;
                if (RealNum.asRealNumOrNull(obj9) == null) {
                    return -786431;
                }
                callContext5.value1 = obj10;
                CallContext callContext6 = callContext2;
                Object obj11 = obj4;
                Object obj12 = obj11;
                if (RealNum.asRealNumOrNull(obj11) == null) {
                    return -786430;
                }
                callContext6.value2 = obj12;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 25:
                CallContext callContext7 = callContext2;
                Object obj13 = obj3;
                Object obj14 = obj13;
                if (RealNum.asRealNumOrNull(obj13) == null) {
                    return -786431;
                }
                callContext7.value1 = obj14;
                CallContext callContext8 = callContext2;
                Object obj15 = obj4;
                Object obj16 = obj15;
                if (RealNum.asRealNumOrNull(obj15) == null) {
                    return -786430;
                }
                callContext8.value2 = obj16;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 33:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 37:
                CallContext callContext9 = callContext2;
                Object obj17 = obj3;
                Object obj18 = obj17;
                if (RealNum.asRealNumOrNull(obj17) == null) {
                    return -786431;
                }
                callContext9.value1 = obj18;
                CallContext callContext10 = callContext2;
                Object obj19 = obj4;
                Object obj20 = obj19;
                if (RealNum.asRealNumOrNull(obj19) == null) {
                    return -786430;
                }
                callContext10.value2 = obj20;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 38:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 48:
                CallContext callContext11 = callContext2;
                Object obj21 = obj3;
                Object obj22 = obj21;
                if (IntNum.asIntNumOrNull(obj21) == null) {
                    return -786431;
                }
                callContext11.value1 = obj22;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 53:
                CallContext callContext12 = callContext2;
                Object obj23 = obj3;
                Object obj24 = obj23;
                if (IntNum.asIntNumOrNull(obj23) == null) {
                    return -786431;
                }
                callContext12.value1 = obj24;
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
            case 60:
                CallContext callContext14 = callContext2;
                Object obj27 = obj3;
                Object obj28 = obj27;
                if (!(obj27 instanceof Number)) {
                    return -786431;
                }
                callContext14.value1 = obj28;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 62:
                CallContext callContext15 = callContext2;
                Object obj29 = obj3;
                Object obj30 = obj29;
                if (!(obj29 instanceof CharSequence)) {
                    return -786431;
                }
                callContext15.value1 = obj30;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 66:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            default:
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
        }
    }

    public static Object div0AndMod0(RealNum realNum, RealNum realNum2) {
        Throwable th;
        Throwable th2;
        RealNum x = realNum;
        RealNum y = realNum2;
        Object apply2 = DivideOp.div0.apply2(x, y);
        Object obj = apply2;
        try {
            RealNum q = LangObjType.coerceRealNum(apply2);
            Object apply22 = AddOp.$Mn.apply2(x, MultiplyOp.$St.apply2(q, y));
            Object obj2 = apply22;
            try {
                RealNum r = LangObjType.coerceRealNum(apply22);
                Object[] objArr = new Object[2];
                objArr[0] = q;
                Object[] objArr2 = objArr;
                objArr2[1] = r;
                return misc.values(objArr2);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th3 = th2;
                new WrongType(classCastException, "r", -2, obj2);
                throw th3;
            }
        } catch (ClassCastException e2) {
            ClassCastException classCastException2 = e2;
            Throwable th4 = th;
            new WrongType(classCastException2, "q", -2, obj);
            throw th4;
        }
    }

    public static IntNum gcd(IntNum... intNumArr) {
        IntNum intNum;
        IntNum[] args = intNumArr;
        int n = args.length;
        if (n == 0) {
            intNum = Lit0;
        } else {
            IntNum result = args[0];
            for (int i = 1; i < n; i++) {
                result = IntNum.gcd(result, args[i]);
            }
            intNum = result;
        }
        return intNum;
    }

    public static IntNum lcm(IntNum... intNumArr) {
        IntNum intNum;
        IntNum[] args = intNumArr;
        int n = args.length;
        if (n == 0) {
            intNum = Lit2;
        } else {
            IntNum result = IntNum.abs(args[0]);
            for (int i = 1; i < n; i++) {
                result = IntNum.lcm(result, args[i]);
            }
            intNum = result;
        }
        return intNum;
    }

    public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
        Throwable th;
        Throwable th2;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object[] objArr2 = objArr;
        switch (moduleMethod2.selector) {
            case 12:
                return max(objArr2);
            case 13:
                return min(objArr2);
            case 17:
                int length = objArr2.length;
                IntNum[] intNumArr = new IntNum[length];
                while (true) {
                    length--;
                    if (length < 0) {
                        return gcd(intNumArr);
                    }
                    IntNum[] intNumArr2 = intNumArr;
                    intNumArr = intNumArr2;
                    IntNum[] intNumArr3 = intNumArr2;
                    int i = length;
                    Object obj = objArr2[length];
                    Object obj2 = obj;
                    try {
                        intNumArr3[i] = LangObjType.coerceIntNum(obj);
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th3 = th2;
                        new WrongType(classCastException, "gcd", 0, obj2);
                        throw th3;
                    }
                }
            case 18:
                int length2 = objArr2.length;
                IntNum[] intNumArr4 = new IntNum[length2];
                while (true) {
                    length2--;
                    if (length2 < 0) {
                        return lcm(intNumArr4);
                    }
                    IntNum[] intNumArr5 = intNumArr4;
                    intNumArr4 = intNumArr5;
                    IntNum[] intNumArr6 = intNumArr5;
                    int i2 = length2;
                    Object obj3 = objArr2[length2];
                    Object obj4 = obj3;
                    try {
                        intNumArr6[i2] = LangObjType.coerceIntNum(obj3);
                    } catch (ClassCastException e2) {
                        ClassCastException classCastException2 = e2;
                        Throwable th4 = th;
                        new WrongType(classCastException2, "lcm", 0, obj4);
                        throw th4;
                    }
                }
            default:
                return super.applyN(moduleMethod2, objArr2);
        }
    }

    public static IntNum numerator(RatNum x) {
        return x.numerator();
    }

    public static IntNum denominator(RatNum x) {
        return x.denominator();
    }

    public static RealNum floor(RealNum x) {
        return x.toInt(Numeric.FLOOR);
    }

    public static RealNum ceiling(RealNum x) {
        return x.toInt(Numeric.CEILING);
    }

    public static RealNum truncate(RealNum x) {
        return x.toInt(Numeric.TRUNCATE);
    }

    public static RealNum round(RealNum x) {
        return x.toInt(Numeric.ROUND);
    }

    public static RealNum rationalize(RealNum realNum, RealNum realNum2) {
        RealNum x = realNum;
        RealNum y = realNum2;
        return RatNum.rationalize(LangObjType.coerceRealNum(x.sub(y)), LangObjType.coerceRealNum(x.add(y)));
    }

    public static Complex exp(Complex x) {
        return x.exp();
    }

    public static Complex log(Complex x) {
        return x.log();
    }

    public static double sin(double x) {
        return Math.sin(x);
    }

    public static double cos(double x) {
        return Math.cos(x);
    }

    public static double tan(double x) {
        return Math.tan(x);
    }

    public static double asin(double x) {
        return Math.asin(x);
    }

    public static double acos(double x) {
        return Math.acos(x);
    }

    static double lambda1(double y, double x) {
        return Math.atan2(y, x);
    }

    static double lambda2(double x) {
        return Math.atan(x);
    }

    static Quantity lambda3(Quantity quantity) {
        Quantity num = quantity;
        return Quantity.make(num.number().sqrt(), num.unit().sqrt());
    }

    static double lambda4(double x) {
        return Math.sqrt(x);
    }

    public static Complex makeRectangular(RealNum x, RealNum y) {
        return Complex.make(x, y);
    }

    public static DComplex makePolar(double x, double y) {
        return Complex.polar(x, y);
    }

    public static RealNum realPart(Complex x) {
        return x.mo17635re();
    }

    public static RealNum imagPart(Complex x) {
        return x.mo17634im();
    }

    public static Number magnitude(Number x) {
        return abs(x);
    }

    public static RealNum angle(Complex x) {
        return x.angle();
    }

    public static Number inexact(Number num) {
        return Arithmetic.toInexact(num);
    }

    public static Number exact(Number num) {
        return Arithmetic.toExact(num);
    }

    public static Number exact$To$Inexact(Number num) {
        return Arithmetic.toInexact(num);
    }

    public static Number inexact$To$Exact(Number num) {
        return Arithmetic.toExact(num);
    }

    public static IntNum logop(int op, IntNum i, IntNum j) {
        return BitOps.bitOp(op, i, j);
    }

    public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj4 = obj;
        Object obj5 = obj2;
        Object obj6 = obj3;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 47:
                callContext2.value1 = obj4;
                CallContext callContext3 = callContext2;
                Object obj7 = obj5;
                Object obj8 = obj7;
                if (IntNum.asIntNumOrNull(obj7) == null) {
                    return -786430;
                }
                callContext3.value2 = obj8;
                CallContext callContext4 = callContext2;
                Object obj9 = obj6;
                Object obj10 = obj9;
                if (IntNum.asIntNumOrNull(obj9) == null) {
                    return -786429;
                }
                callContext4.value3 = obj10;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 49:
                CallContext callContext5 = callContext2;
                Object obj11 = obj4;
                Object obj12 = obj11;
                if (IntNum.asIntNumOrNull(obj11) == null) {
                    return -786431;
                }
                callContext5.value1 = obj12;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 51:
                CallContext callContext6 = callContext2;
                Object obj13 = obj4;
                Object obj14 = obj13;
                if (IntNum.asIntNumOrNull(obj13) == null) {
                    return -786431;
                }
                callContext6.value1 = obj14;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 52:
                CallContext callContext7 = callContext2;
                Object obj15 = obj4;
                Object obj16 = obj15;
                if (IntNum.asIntNumOrNull(obj15) == null) {
                    return -786431;
                }
                callContext7.value1 = obj16;
                CallContext callContext8 = callContext2;
                Object obj17 = obj5;
                Object obj18 = obj17;
                if (IntNum.asIntNumOrNull(obj17) == null) {
                    return -786430;
                }
                callContext8.value2 = obj18;
                CallContext callContext9 = callContext2;
                Object obj19 = obj6;
                Object obj20 = obj19;
                if (IntNum.asIntNumOrNull(obj19) == null) {
                    return -786429;
                }
                callContext9.value3 = obj20;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 59:
                CallContext callContext10 = callContext2;
                Object obj21 = obj4;
                Object obj22 = obj21;
                if (IntNum.asIntNumOrNull(obj21) == null) {
                    return -786431;
                }
                callContext10.value1 = obj22;
                callContext2.value2 = obj5;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            default:
                return super.match3(moduleMethod2, obj4, obj5, obj6, callContext2);
        }
    }

    public static boolean isBitwiseBitSet(IntNum i, int bitno) {
        return BitOps.bitValue(i, bitno);
    }

    public static IntNum bitwiseCopyBit(IntNum i, int bitno, int new$Mnvalue) {
        return BitOps.setBitValue(i, bitno, new$Mnvalue);
    }

    public static IntNum bitwiseCopyBitField(IntNum to, int i, int end, IntNum from) {
        int start = i;
        int mask1 = IntNum.shift(-1, start);
        return bitwiseIf(BitOps.and(IntNum.make(mask1), BitOps.not(IntNum.make(IntNum.shift(-1, end)))), IntNum.shift(from, start), to);
    }

    public int match4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj5 = obj;
        Object obj6 = obj2;
        Object obj7 = obj3;
        Object obj8 = obj4;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 50:
                CallContext callContext3 = callContext2;
                Object obj9 = obj5;
                Object obj10 = obj9;
                if (IntNum.asIntNumOrNull(obj9) == null) {
                    return -786431;
                }
                callContext3.value1 = obj10;
                callContext2.value2 = obj6;
                callContext2.value3 = obj7;
                CallContext callContext4 = callContext2;
                Object obj11 = obj8;
                Object obj12 = obj11;
                if (IntNum.asIntNumOrNull(obj11) == null) {
                    return -786428;
                }
                callContext4.value4 = obj12;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 4;
                return 0;
            case 58:
                CallContext callContext5 = callContext2;
                Object obj13 = obj5;
                Object obj14 = obj13;
                if (IntNum.asIntNumOrNull(obj13) == null) {
                    return -786431;
                }
                callContext5.value1 = obj14;
                callContext2.value2 = obj6;
                callContext2.value3 = obj7;
                callContext2.value4 = obj8;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 4;
                return 0;
            default:
                return super.match4(moduleMethod2, obj5, obj6, obj7, obj8, callContext2);
        }
    }

    public static IntNum bitwiseBitField(IntNum i, int start, int end) {
        return BitOps.extract(i, start, end);
    }

    public static IntNum bitwiseIf(IntNum intNum, IntNum e2, IntNum e3) {
        IntNum e1 = intNum;
        return BitOps.ior(BitOps.and(e1, e2), BitOps.and(BitOps.not(e1), e3));
    }

    public static boolean logtest(IntNum i, IntNum j) {
        return BitOps.test(i, j);
    }

    public static int logcount(IntNum intNum) {
        IntNum i = intNum;
        return BitOps.bitCount(IntNum.compare(i, 0) >= 0 ? i : BitOps.not(i));
    }

    public static int bitwiseBitCount(IntNum intNum) {
        int bitCount;
        IntNum i = intNum;
        if (IntNum.compare(i, 0) >= 0) {
            bitCount = BitOps.bitCount(i);
        } else {
            bitCount = -1 - BitOps.bitCount(BitOps.not(i));
        }
        return bitCount;
    }

    public static int bitwiseLength(IntNum i) {
        return i.intLength();
    }

    public static int bitwiseFirstBitSet(IntNum i) {
        return BitOps.lowestBitSet(i);
    }

    public static IntNum bitwiseRotateBitField(IntNum intNum, int i, int i2, int i3) {
        IntNum n;
        IntNum n2 = intNum;
        int start = i;
        int end = i2;
        int count = i3;
        int width = end - start;
        if (width > 0) {
            int r = count % width;
            int count2 = r < 0 ? r + width : r;
            IntNum field0 = bitwiseBitField(n2, start, end);
            n = bitwiseCopyBitField(n2, start, end, BitOps.ior(IntNum.shift(field0, count2), IntNum.shift(field0, count2 - width)));
        } else {
            n = n2;
        }
        return n;
    }

    public Object apply4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Throwable th6;
        Throwable th7;
        Throwable th8;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj5 = obj;
        Object obj6 = obj2;
        Object obj7 = obj3;
        Object obj8 = obj4;
        switch (moduleMethod2.selector) {
            case 50:
                try {
                    try {
                        try {
                            try {
                                return bitwiseCopyBitField(LangObjType.coerceIntNum(obj5), ((Number) obj6).intValue(), ((Number) obj7).intValue(), LangObjType.coerceIntNum(obj8));
                            } catch (ClassCastException e) {
                                ClassCastException classCastException = e;
                                Throwable th9 = th8;
                                new WrongType(classCastException, "bitwise-copy-bit-field", 4, obj8);
                                throw th9;
                            }
                        } catch (ClassCastException e2) {
                            ClassCastException classCastException2 = e2;
                            Throwable th10 = th7;
                            new WrongType(classCastException2, "bitwise-copy-bit-field", 3, obj7);
                            throw th10;
                        }
                    } catch (ClassCastException e3) {
                        ClassCastException classCastException3 = e3;
                        Throwable th11 = th6;
                        new WrongType(classCastException3, "bitwise-copy-bit-field", 2, obj6);
                        throw th11;
                    }
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th12 = th5;
                    new WrongType(classCastException4, "bitwise-copy-bit-field", 1, obj5);
                    throw th12;
                }
            case 58:
                try {
                    try {
                        try {
                            try {
                                return bitwiseRotateBitField(LangObjType.coerceIntNum(obj5), ((Number) obj6).intValue(), ((Number) obj7).intValue(), ((Number) obj8).intValue());
                            } catch (ClassCastException e5) {
                                ClassCastException classCastException5 = e5;
                                Throwable th13 = th4;
                                new WrongType(classCastException5, "bitwise-rotate-bit-field", 4, obj8);
                                throw th13;
                            }
                        } catch (ClassCastException e6) {
                            ClassCastException classCastException6 = e6;
                            Throwable th14 = th3;
                            new WrongType(classCastException6, "bitwise-rotate-bit-field", 3, obj7);
                            throw th14;
                        }
                    } catch (ClassCastException e7) {
                        ClassCastException classCastException7 = e7;
                        Throwable th15 = th2;
                        new WrongType(classCastException7, "bitwise-rotate-bit-field", 2, obj6);
                        throw th15;
                    }
                } catch (ClassCastException e8) {
                    ClassCastException classCastException8 = e8;
                    Throwable th16 = th;
                    new WrongType(classCastException8, "bitwise-rotate-bit-field", 1, obj5);
                    throw th16;
                }
            default:
                return super.apply4(moduleMethod2, obj5, obj6, obj7, obj8);
        }
    }

    public static IntNum bitwiseReverseBitField(IntNum n, int start, int end) {
        return BitOps.reverseBits(n, start, end);
    }

    public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
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
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj4 = obj;
        Object obj5 = obj2;
        Object obj6 = obj3;
        switch (moduleMethod2.selector) {
            case 47:
                try {
                    try {
                        try {
                            return logop(((Number) obj4).intValue(), LangObjType.coerceIntNum(obj5), LangObjType.coerceIntNum(obj6));
                        } catch (ClassCastException e) {
                            ClassCastException classCastException = e;
                            Throwable th16 = th15;
                            new WrongType(classCastException, "logop", 3, obj6);
                            throw th16;
                        }
                    } catch (ClassCastException e2) {
                        ClassCastException classCastException2 = e2;
                        Throwable th17 = th14;
                        new WrongType(classCastException2, "logop", 2, obj5);
                        throw th17;
                    }
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th18 = th13;
                    new WrongType(classCastException3, "logop", 1, obj4);
                    throw th18;
                }
            case 49:
                try {
                    try {
                        try {
                            return bitwiseCopyBit(LangObjType.coerceIntNum(obj4), ((Number) obj5).intValue(), ((Number) obj6).intValue());
                        } catch (ClassCastException e4) {
                            ClassCastException classCastException4 = e4;
                            Throwable th19 = th12;
                            new WrongType(classCastException4, "bitwise-copy-bit", 3, obj6);
                            throw th19;
                        }
                    } catch (ClassCastException e5) {
                        ClassCastException classCastException5 = e5;
                        Throwable th20 = th11;
                        new WrongType(classCastException5, "bitwise-copy-bit", 2, obj5);
                        throw th20;
                    }
                } catch (ClassCastException e6) {
                    ClassCastException classCastException6 = e6;
                    Throwable th21 = th10;
                    new WrongType(classCastException6, "bitwise-copy-bit", 1, obj4);
                    throw th21;
                }
            case 51:
                try {
                    try {
                        try {
                            return bitwiseBitField(LangObjType.coerceIntNum(obj4), ((Number) obj5).intValue(), ((Number) obj6).intValue());
                        } catch (ClassCastException e7) {
                            ClassCastException classCastException7 = e7;
                            Throwable th22 = th9;
                            new WrongType(classCastException7, "bitwise-bit-field", 3, obj6);
                            throw th22;
                        }
                    } catch (ClassCastException e8) {
                        ClassCastException classCastException8 = e8;
                        Throwable th23 = th8;
                        new WrongType(classCastException8, "bitwise-bit-field", 2, obj5);
                        throw th23;
                    }
                } catch (ClassCastException e9) {
                    ClassCastException classCastException9 = e9;
                    Throwable th24 = th7;
                    new WrongType(classCastException9, "bitwise-bit-field", 1, obj4);
                    throw th24;
                }
            case 52:
                try {
                    try {
                        try {
                            return bitwiseIf(LangObjType.coerceIntNum(obj4), LangObjType.coerceIntNum(obj5), LangObjType.coerceIntNum(obj6));
                        } catch (ClassCastException e10) {
                            ClassCastException classCastException10 = e10;
                            Throwable th25 = th6;
                            new WrongType(classCastException10, "bitwise-if", 3, obj6);
                            throw th25;
                        }
                    } catch (ClassCastException e11) {
                        ClassCastException classCastException11 = e11;
                        Throwable th26 = th5;
                        new WrongType(classCastException11, "bitwise-if", 2, obj5);
                        throw th26;
                    }
                } catch (ClassCastException e12) {
                    ClassCastException classCastException12 = e12;
                    Throwable th27 = th4;
                    new WrongType(classCastException12, "bitwise-if", 1, obj4);
                    throw th27;
                }
            case 59:
                try {
                    try {
                        try {
                            return bitwiseReverseBitField(LangObjType.coerceIntNum(obj4), ((Number) obj5).intValue(), ((Number) obj6).intValue());
                        } catch (ClassCastException e13) {
                            ClassCastException classCastException13 = e13;
                            Throwable th28 = th3;
                            new WrongType(classCastException13, "bitwise-reverse-bit-field", 3, obj6);
                            throw th28;
                        }
                    } catch (ClassCastException e14) {
                        ClassCastException classCastException14 = e14;
                        Throwable th29 = th2;
                        new WrongType(classCastException14, "bitwise-reverse-bit-field", 2, obj5);
                        throw th29;
                    }
                } catch (ClassCastException e15) {
                    ClassCastException classCastException15 = e15;
                    Throwable th30 = th;
                    new WrongType(classCastException15, "bitwise-reverse-bit-field", 1, obj4);
                    throw th30;
                }
            default:
                return super.apply3(moduleMethod2, obj4, obj5, obj6);
        }
    }

    public static CharSequence number$To$String(Number arg, int radix) {
        CharSequence charSequence;
        new FString((CharSequence) Arithmetic.toString(arg, radix));
        return charSequence;
    }

    public static Object string$To$Number(CharSequence str, int radix) {
        Object result = LispReader.parseNumber(str, radix);
        return result instanceof Numeric ? result : Boolean.FALSE;
    }

    public static Complex quantity$To$Number(Quantity quantity) {
        Complex make;
        Quantity q = quantity;
        Unit unit = q.unit();
        if (q.doubleValue() == 1.0d) {
            make = q.number();
        } else {
            make = Complex.make(q.reValue(), q.imValue());
        }
        return make;
    }

    public static Unit quantity$To$Unit(Quantity q) {
        return q.unit();
    }

    public static Quantity makeQuantity(Object obj, Object obj2) {
        Unit lookup;
        Throwable th;
        Throwable th2;
        Throwable th3;
        Object val = obj;
        Object unit = obj2;
        if (unit instanceof Unit) {
            Object obj3 = unit;
            Object obj4 = obj3;
            try {
                lookup = (Unit) obj3;
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th4 = th3;
                new WrongType(classCastException, "u", -2, obj4);
                throw th4;
            }
        } else {
            Object obj5 = unit;
            lookup = Unit.lookup(obj5 == null ? null : obj5.toString());
        }
        Unit u = lookup;
        if (u == null) {
            Throwable th5 = th2;
            Object[] objArr = new Object[2];
            objArr[0] = "unknown unit: ~s";
            Object[] objArr2 = objArr;
            objArr2[1] = unit;
            new IllegalArgumentException(Format.formatToString(0, objArr2).toString());
            throw th5;
        }
        Object obj6 = val;
        Object obj7 = obj6;
        try {
            return Quantity.make((Complex) obj6, u);
        } catch (ClassCastException e2) {
            ClassCastException classCastException2 = e2;
            Throwable th6 = th;
            new WrongType(classCastException2, "gnu.math.Quantity.make(gnu.math.Complex,gnu.math.Unit)", 1, obj7);
            throw th6;
        }
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
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        switch (moduleMethod2.selector) {
            case 15:
                try {
                    try {
                        return divAndMod(LangObjType.coerceRealNum(obj3), LangObjType.coerceRealNum(obj4));
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th21 = th20;
                        new WrongType(classCastException, "div-and-mod", 2, obj4);
                        throw th21;
                    }
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th22 = th19;
                    new WrongType(classCastException2, "div-and-mod", 1, obj3);
                    throw th22;
                }
            case 16:
                try {
                    try {
                        return div0AndMod0(LangObjType.coerceRealNum(obj3), LangObjType.coerceRealNum(obj4));
                    } catch (ClassCastException e3) {
                        ClassCastException classCastException3 = e3;
                        Throwable th23 = th18;
                        new WrongType(classCastException3, "div0-and-mod0", 2, obj4);
                        throw th23;
                    }
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th24 = th17;
                    new WrongType(classCastException4, "div0-and-mod0", 1, obj3);
                    throw th24;
                }
            case 25:
                try {
                    try {
                        return rationalize(LangObjType.coerceRealNum(obj3), LangObjType.coerceRealNum(obj4));
                    } catch (ClassCastException e5) {
                        ClassCastException classCastException5 = e5;
                        Throwable th25 = th16;
                        new WrongType(classCastException5, "rationalize", 2, obj4);
                        throw th25;
                    }
                } catch (ClassCastException e6) {
                    ClassCastException classCastException6 = e6;
                    Throwable th26 = th15;
                    new WrongType(classCastException6, "rationalize", 1, obj3);
                    throw th26;
                }
            case 33:
                try {
                    try {
                        return Double.valueOf(lambda1(((Number) obj3).doubleValue(), ((Number) obj4).doubleValue()));
                    } catch (ClassCastException e7) {
                        ClassCastException classCastException7 = e7;
                        Throwable th27 = th14;
                        new WrongType(classCastException7, "lambda", 2, obj4);
                        throw th27;
                    }
                } catch (ClassCastException e8) {
                    ClassCastException classCastException8 = e8;
                    Throwable th28 = th13;
                    new WrongType(classCastException8, "lambda", 1, obj3);
                    throw th28;
                }
            case 37:
                try {
                    try {
                        return makeRectangular(LangObjType.coerceRealNum(obj3), LangObjType.coerceRealNum(obj4));
                    } catch (ClassCastException e9) {
                        ClassCastException classCastException9 = e9;
                        Throwable th29 = th12;
                        new WrongType(classCastException9, "make-rectangular", 2, obj4);
                        throw th29;
                    }
                } catch (ClassCastException e10) {
                    ClassCastException classCastException10 = e10;
                    Throwable th30 = th11;
                    new WrongType(classCastException10, "make-rectangular", 1, obj3);
                    throw th30;
                }
            case 38:
                try {
                    try {
                        return makePolar(((Number) obj3).doubleValue(), ((Number) obj4).doubleValue());
                    } catch (ClassCastException e11) {
                        ClassCastException classCastException11 = e11;
                        Throwable th31 = th10;
                        new WrongType(classCastException11, "make-polar", 2, obj4);
                        throw th31;
                    }
                } catch (ClassCastException e12) {
                    ClassCastException classCastException12 = e12;
                    Throwable th32 = th9;
                    new WrongType(classCastException12, "make-polar", 1, obj3);
                    throw th32;
                }
            case 48:
                try {
                    try {
                        return isBitwiseBitSet(LangObjType.coerceIntNum(obj3), ((Number) obj4).intValue()) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e13) {
                        ClassCastException classCastException13 = e13;
                        Throwable th33 = th8;
                        new WrongType(classCastException13, "bitwise-bit-set?", 2, obj4);
                        throw th33;
                    }
                } catch (ClassCastException e14) {
                    ClassCastException classCastException14 = e14;
                    Throwable th34 = th7;
                    new WrongType(classCastException14, "bitwise-bit-set?", 1, obj3);
                    throw th34;
                }
            case 53:
                try {
                    try {
                        return logtest(LangObjType.coerceIntNum(obj3), LangObjType.coerceIntNum(obj4)) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e15) {
                        ClassCastException classCastException15 = e15;
                        Throwable th35 = th6;
                        new WrongType(classCastException15, "logtest", 2, obj4);
                        throw th35;
                    }
                } catch (ClassCastException e16) {
                    ClassCastException classCastException16 = e16;
                    Throwable th36 = th5;
                    new WrongType(classCastException16, "logtest", 1, obj3);
                    throw th36;
                }
            case 60:
                try {
                    try {
                        return number$To$String((Number) obj3, ((Number) obj4).intValue());
                    } catch (ClassCastException e17) {
                        ClassCastException classCastException17 = e17;
                        Throwable th37 = th4;
                        new WrongType(classCastException17, "number->string", 2, obj4);
                        throw th37;
                    }
                } catch (ClassCastException e18) {
                    ClassCastException classCastException18 = e18;
                    Throwable th38 = th3;
                    new WrongType(classCastException18, "number->string", 1, obj3);
                    throw th38;
                }
            case 62:
                try {
                    try {
                        return string$To$Number((CharSequence) obj3, ((Number) obj4).intValue());
                    } catch (ClassCastException e19) {
                        ClassCastException classCastException19 = e19;
                        Throwable th39 = th2;
                        new WrongType(classCastException19, "string->number", 2, obj4);
                        throw th39;
                    }
                } catch (ClassCastException e20) {
                    ClassCastException classCastException20 = e20;
                    Throwable th40 = th;
                    new WrongType(classCastException20, "string->number", 1, obj3);
                    throw th40;
                }
            case 66:
                return makeQuantity(obj3, obj4);
            default:
                return super.apply2(moduleMethod2, obj3, obj4);
        }
    }

    public static Duration duration(Object duration2) {
        Object obj = duration2;
        return Duration.parseDuration(obj == null ? null : obj.toString());
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
        Throwable th31;
        Throwable th32;
        Throwable th33;
        Throwable th34;
        Throwable th35;
        Throwable th36;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        switch (moduleMethod2.selector) {
            case 1:
                return isNumber(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 2:
                return isQuantity(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 3:
                return isComplex(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 4:
                return isReal(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 5:
                return isRational(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 6:
                return isInteger(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 7:
                return isExact(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 8:
                return isInexact(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 9:
                try {
                    return isZero((Number) obj2) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th37 = th36;
                    new WrongType(classCastException, "zero?", 1, obj2);
                    throw th37;
                }
            case 10:
                try {
                    return isPositive(LangObjType.coerceRealNum(obj2)) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th38 = th35;
                    new WrongType(classCastException2, "positive?", 1, obj2);
                    throw th38;
                }
            case 11:
                try {
                    return isNegative(LangObjType.coerceRealNum(obj2)) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th39 = th34;
                    new WrongType(classCastException3, "negative?", 1, obj2);
                    throw th39;
                }
            case 14:
                try {
                    return abs((Number) obj2);
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th40 = th33;
                    new WrongType(classCastException4, "abs", 1, obj2);
                    throw th40;
                }
            case 19:
                try {
                    return numerator(LangObjType.coerceRatNum(obj2));
                } catch (ClassCastException e5) {
                    ClassCastException classCastException5 = e5;
                    Throwable th41 = th32;
                    new WrongType(classCastException5, "numerator", 1, obj2);
                    throw th41;
                }
            case 20:
                try {
                    return denominator(LangObjType.coerceRatNum(obj2));
                } catch (ClassCastException e6) {
                    ClassCastException classCastException6 = e6;
                    Throwable th42 = th31;
                    new WrongType(classCastException6, "denominator", 1, obj2);
                    throw th42;
                }
            case 21:
                try {
                    return floor(LangObjType.coerceRealNum(obj2));
                } catch (ClassCastException e7) {
                    ClassCastException classCastException7 = e7;
                    Throwable th43 = th30;
                    new WrongType(classCastException7, "floor", 1, obj2);
                    throw th43;
                }
            case 22:
                try {
                    return ceiling(LangObjType.coerceRealNum(obj2));
                } catch (ClassCastException e8) {
                    ClassCastException classCastException8 = e8;
                    Throwable th44 = th29;
                    new WrongType(classCastException8, "ceiling", 1, obj2);
                    throw th44;
                }
            case 23:
                try {
                    return truncate(LangObjType.coerceRealNum(obj2));
                } catch (ClassCastException e9) {
                    ClassCastException classCastException9 = e9;
                    Throwable th45 = th28;
                    new WrongType(classCastException9, "truncate", 1, obj2);
                    throw th45;
                }
            case 24:
                try {
                    return round(LangObjType.coerceRealNum(obj2));
                } catch (ClassCastException e10) {
                    ClassCastException classCastException10 = e10;
                    Throwable th46 = th27;
                    new WrongType(classCastException10, "round", 1, obj2);
                    throw th46;
                }
            case 26:
                try {
                    return exp((Complex) obj2);
                } catch (ClassCastException e11) {
                    ClassCastException classCastException11 = e11;
                    Throwable th47 = th26;
                    new WrongType(classCastException11, "exp", 1, obj2);
                    throw th47;
                }
            case 27:
                try {
                    return log((Complex) obj2);
                } catch (ClassCastException e12) {
                    ClassCastException classCastException12 = e12;
                    Throwable th48 = th25;
                    new WrongType(classCastException12, "log", 1, obj2);
                    throw th48;
                }
            case 28:
                try {
                    return Double.valueOf(sin(((Number) obj2).doubleValue()));
                } catch (ClassCastException e13) {
                    ClassCastException classCastException13 = e13;
                    Throwable th49 = th24;
                    new WrongType(classCastException13, "sin", 1, obj2);
                    throw th49;
                }
            case 29:
                try {
                    return Double.valueOf(cos(((Number) obj2).doubleValue()));
                } catch (ClassCastException e14) {
                    ClassCastException classCastException14 = e14;
                    Throwable th50 = th23;
                    new WrongType(classCastException14, "cos", 1, obj2);
                    throw th50;
                }
            case 30:
                try {
                    return Double.valueOf(tan(((Number) obj2).doubleValue()));
                } catch (ClassCastException e15) {
                    ClassCastException classCastException15 = e15;
                    Throwable th51 = th22;
                    new WrongType(classCastException15, "tan", 1, obj2);
                    throw th51;
                }
            case 31:
                try {
                    return Double.valueOf(asin(((Number) obj2).doubleValue()));
                } catch (ClassCastException e16) {
                    ClassCastException classCastException16 = e16;
                    Throwable th52 = th21;
                    new WrongType(classCastException16, "asin", 1, obj2);
                    throw th52;
                }
            case 32:
                try {
                    return Double.valueOf(acos(((Number) obj2).doubleValue()));
                } catch (ClassCastException e17) {
                    ClassCastException classCastException17 = e17;
                    Throwable th53 = th20;
                    new WrongType(classCastException17, "acos", 1, obj2);
                    throw th53;
                }
            case 34:
                try {
                    return Double.valueOf(lambda2(((Number) obj2).doubleValue()));
                } catch (ClassCastException e18) {
                    ClassCastException classCastException18 = e18;
                    Throwable th54 = th19;
                    new WrongType(classCastException18, "lambda", 1, obj2);
                    throw th54;
                }
            case 35:
                try {
                    return lambda3((Quantity) obj2);
                } catch (ClassCastException e19) {
                    ClassCastException classCastException19 = e19;
                    Throwable th55 = th18;
                    new WrongType(classCastException19, "lambda", 1, obj2);
                    throw th55;
                }
            case 36:
                try {
                    return Double.valueOf(lambda4(((Number) obj2).doubleValue()));
                } catch (ClassCastException e20) {
                    ClassCastException classCastException20 = e20;
                    Throwable th56 = th17;
                    new WrongType(classCastException20, "lambda", 1, obj2);
                    throw th56;
                }
            case 39:
                try {
                    return realPart((Complex) obj2);
                } catch (ClassCastException e21) {
                    ClassCastException classCastException21 = e21;
                    Throwable th57 = th16;
                    new WrongType(classCastException21, "real-part", 1, obj2);
                    throw th57;
                }
            case 40:
                try {
                    return imagPart((Complex) obj2);
                } catch (ClassCastException e22) {
                    ClassCastException classCastException22 = e22;
                    Throwable th58 = th15;
                    new WrongType(classCastException22, "imag-part", 1, obj2);
                    throw th58;
                }
            case 41:
                try {
                    return magnitude((Number) obj2);
                } catch (ClassCastException e23) {
                    ClassCastException classCastException23 = e23;
                    Throwable th59 = th14;
                    new WrongType(classCastException23, "magnitude", 1, obj2);
                    throw th59;
                }
            case 42:
                try {
                    return angle((Complex) obj2);
                } catch (ClassCastException e24) {
                    ClassCastException classCastException24 = e24;
                    Throwable th60 = th13;
                    new WrongType(classCastException24, "angle", 1, obj2);
                    throw th60;
                }
            case 43:
                try {
                    return inexact((Number) obj2);
                } catch (ClassCastException e25) {
                    ClassCastException classCastException25 = e25;
                    Throwable th61 = th12;
                    new WrongType(classCastException25, "inexact", 1, obj2);
                    throw th61;
                }
            case 44:
                try {
                    return exact((Number) obj2);
                } catch (ClassCastException e26) {
                    ClassCastException classCastException26 = e26;
                    Throwable th62 = th11;
                    new WrongType(classCastException26, "exact", 1, obj2);
                    throw th62;
                }
            case 45:
                try {
                    return exact$To$Inexact((Number) obj2);
                } catch (ClassCastException e27) {
                    ClassCastException classCastException27 = e27;
                    Throwable th63 = th10;
                    new WrongType(classCastException27, "exact->inexact", 1, obj2);
                    throw th63;
                }
            case 46:
                try {
                    return inexact$To$Exact((Number) obj2);
                } catch (ClassCastException e28) {
                    ClassCastException classCastException28 = e28;
                    Throwable th64 = th9;
                    new WrongType(classCastException28, "inexact->exact", 1, obj2);
                    throw th64;
                }
            case 54:
                try {
                    return Integer.valueOf(logcount(LangObjType.coerceIntNum(obj2)));
                } catch (ClassCastException e29) {
                    ClassCastException classCastException29 = e29;
                    Throwable th65 = th8;
                    new WrongType(classCastException29, "logcount", 1, obj2);
                    throw th65;
                }
            case 55:
                try {
                    return Integer.valueOf(bitwiseBitCount(LangObjType.coerceIntNum(obj2)));
                } catch (ClassCastException e30) {
                    ClassCastException classCastException30 = e30;
                    Throwable th66 = th7;
                    new WrongType(classCastException30, "bitwise-bit-count", 1, obj2);
                    throw th66;
                }
            case 56:
                try {
                    return Integer.valueOf(bitwiseLength(LangObjType.coerceIntNum(obj2)));
                } catch (ClassCastException e31) {
                    ClassCastException classCastException31 = e31;
                    Throwable th67 = th6;
                    new WrongType(classCastException31, "bitwise-length", 1, obj2);
                    throw th67;
                }
            case 57:
                try {
                    return Integer.valueOf(bitwiseFirstBitSet(LangObjType.coerceIntNum(obj2)));
                } catch (ClassCastException e32) {
                    ClassCastException classCastException32 = e32;
                    Throwable th68 = th5;
                    new WrongType(classCastException32, "bitwise-first-bit-set", 1, obj2);
                    throw th68;
                }
            case 60:
                try {
                    return number$To$String((Number) obj2);
                } catch (ClassCastException e33) {
                    ClassCastException classCastException33 = e33;
                    Throwable th69 = th4;
                    new WrongType(classCastException33, "number->string", 1, obj2);
                    throw th69;
                }
            case 62:
                try {
                    return string$To$Number((CharSequence) obj2);
                } catch (ClassCastException e34) {
                    ClassCastException classCastException34 = e34;
                    Throwable th70 = th3;
                    new WrongType(classCastException34, "string->number", 1, obj2);
                    throw th70;
                }
            case 64:
                try {
                    return quantity$To$Number((Quantity) obj2);
                } catch (ClassCastException e35) {
                    ClassCastException classCastException35 = e35;
                    Throwable th71 = th2;
                    new WrongType(classCastException35, "quantity->number", 1, obj2);
                    throw th71;
                }
            case 65:
                try {
                    return quantity$To$Unit((Quantity) obj2);
                } catch (ClassCastException e36) {
                    ClassCastException classCastException36 = e36;
                    Throwable th72 = th;
                    new WrongType(classCastException36, "quantity->unit", 1, obj2);
                    throw th72;
                }
            case 67:
                return duration(obj2);
            default:
                return super.apply1(moduleMethod2, obj2);
        }
    }
}

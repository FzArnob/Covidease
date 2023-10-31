package kawa.lib.rnrs;

import android.support.p000v4.app.FragmentTransaction;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.UnicodeUtils;
import gnu.lists.Consumer;
import gnu.lists.FString;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.WrongType;
import gnu.text.Char;
import java.util.Locale;
import kawa.lib.misc;

/* compiled from: unicode.scm */
public class unicode extends ModuleBody {
    public static final unicode $instance;
    static final SimpleSymbol Lit0;
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
    static final SimpleSymbol Lit3;
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    public static final ModuleMethod char$Mnalphabetic$Qu;
    public static final ModuleMethod char$Mnci$Eq$Qu;
    public static final ModuleMethod char$Mnci$Gr$Eq$Qu;
    public static final ModuleMethod char$Mnci$Gr$Qu;
    public static final ModuleMethod char$Mnci$Ls$Eq$Qu;
    public static final ModuleMethod char$Mnci$Ls$Qu;
    public static final ModuleMethod char$Mndowncase;
    public static final ModuleMethod char$Mnfoldcase;
    public static final ModuleMethod char$Mngeneral$Mncategory;
    public static final ModuleMethod char$Mnlower$Mncase$Qu;
    public static final ModuleMethod char$Mnnumeric$Qu;
    public static final ModuleMethod char$Mntitle$Mncase$Qu;
    public static final ModuleMethod char$Mntitlecase;
    public static final ModuleMethod char$Mnupcase;
    public static final ModuleMethod char$Mnupper$Mncase$Qu;
    public static final ModuleMethod char$Mnwhitespace$Qu;
    public static final ModuleMethod string$Mnci$Eq$Qu;
    public static final ModuleMethod string$Mnci$Gr$Eq$Qu;
    public static final ModuleMethod string$Mnci$Gr$Qu;
    public static final ModuleMethod string$Mnci$Ls$Eq$Qu;
    public static final ModuleMethod string$Mnci$Ls$Qu;
    public static final ModuleMethod string$Mndowncase;
    public static final ModuleMethod string$Mnfoldcase;
    public static final ModuleMethod string$Mnnormalize$Mnnfc;
    public static final ModuleMethod string$Mnnormalize$Mnnfd;
    public static final ModuleMethod string$Mnnormalize$Mnnfkc;
    public static final ModuleMethod string$Mnnormalize$Mnnfkd;
    public static final ModuleMethod string$Mntitlecase;
    public static final ModuleMethod string$Mnupcase;

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
        unicode unicode;
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
        new SimpleSymbol("string-normalize-nfkc");
        Lit28 = (SimpleSymbol) simpleSymbol.readResolve();
        new SimpleSymbol("string-normalize-nfc");
        Lit27 = (SimpleSymbol) simpleSymbol2.readResolve();
        new SimpleSymbol("string-normalize-nfkd");
        Lit26 = (SimpleSymbol) simpleSymbol3.readResolve();
        new SimpleSymbol("string-normalize-nfd");
        Lit25 = (SimpleSymbol) simpleSymbol4.readResolve();
        new SimpleSymbol("string-ci>=?");
        Lit24 = (SimpleSymbol) simpleSymbol5.readResolve();
        new SimpleSymbol("string-ci<=?");
        Lit23 = (SimpleSymbol) simpleSymbol6.readResolve();
        new SimpleSymbol("string-ci>?");
        Lit22 = (SimpleSymbol) simpleSymbol7.readResolve();
        new SimpleSymbol("string-ci<?");
        Lit21 = (SimpleSymbol) simpleSymbol8.readResolve();
        new SimpleSymbol("string-ci=?");
        Lit20 = (SimpleSymbol) simpleSymbol9.readResolve();
        new SimpleSymbol("string-foldcase");
        Lit19 = (SimpleSymbol) simpleSymbol10.readResolve();
        new SimpleSymbol("string-titlecase");
        Lit18 = (SimpleSymbol) simpleSymbol11.readResolve();
        new SimpleSymbol("string-downcase");
        Lit17 = (SimpleSymbol) simpleSymbol12.readResolve();
        new SimpleSymbol("string-upcase");
        Lit16 = (SimpleSymbol) simpleSymbol13.readResolve();
        new SimpleSymbol("char-general-category");
        Lit15 = (SimpleSymbol) simpleSymbol14.readResolve();
        new SimpleSymbol("char-ci>=?");
        Lit14 = (SimpleSymbol) simpleSymbol15.readResolve();
        new SimpleSymbol("char-ci<=?");
        Lit13 = (SimpleSymbol) simpleSymbol16.readResolve();
        new SimpleSymbol("char-ci>?");
        Lit12 = (SimpleSymbol) simpleSymbol17.readResolve();
        new SimpleSymbol("char-ci<?");
        Lit11 = (SimpleSymbol) simpleSymbol18.readResolve();
        new SimpleSymbol("char-ci=?");
        Lit10 = (SimpleSymbol) simpleSymbol19.readResolve();
        new SimpleSymbol("char-foldcase");
        Lit9 = (SimpleSymbol) simpleSymbol20.readResolve();
        new SimpleSymbol("char-title-case?");
        Lit8 = (SimpleSymbol) simpleSymbol21.readResolve();
        new SimpleSymbol("char-lower-case?");
        Lit7 = (SimpleSymbol) simpleSymbol22.readResolve();
        new SimpleSymbol("char-upper-case?");
        Lit6 = (SimpleSymbol) simpleSymbol23.readResolve();
        new SimpleSymbol("char-whitespace?");
        Lit5 = (SimpleSymbol) simpleSymbol24.readResolve();
        new SimpleSymbol("char-numeric?");
        Lit4 = (SimpleSymbol) simpleSymbol25.readResolve();
        new SimpleSymbol("char-alphabetic?");
        Lit3 = (SimpleSymbol) simpleSymbol26.readResolve();
        new SimpleSymbol("char-titlecase");
        Lit2 = (SimpleSymbol) simpleSymbol27.readResolve();
        new SimpleSymbol("char-downcase");
        Lit1 = (SimpleSymbol) simpleSymbol28.readResolve();
        new SimpleSymbol("char-upcase");
        Lit0 = (SimpleSymbol) simpleSymbol29.readResolve();
        new unicode();
        $instance = unicode;
        unicode unicode2 = $instance;
        new ModuleMethod(unicode2, 1, Lit0, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        char$Mnupcase = moduleMethod;
        new ModuleMethod(unicode2, 2, Lit1, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        char$Mndowncase = moduleMethod2;
        new ModuleMethod(unicode2, 3, Lit2, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        char$Mntitlecase = moduleMethod3;
        new ModuleMethod(unicode2, 4, Lit3, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        char$Mnalphabetic$Qu = moduleMethod4;
        new ModuleMethod(unicode2, 5, Lit4, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        char$Mnnumeric$Qu = moduleMethod5;
        new ModuleMethod(unicode2, 6, Lit5, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        char$Mnwhitespace$Qu = moduleMethod6;
        new ModuleMethod(unicode2, 7, Lit6, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        char$Mnupper$Mncase$Qu = moduleMethod7;
        new ModuleMethod(unicode2, 8, Lit7, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        char$Mnlower$Mncase$Qu = moduleMethod8;
        new ModuleMethod(unicode2, 9, Lit8, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        char$Mntitle$Mncase$Qu = moduleMethod9;
        new ModuleMethod(unicode2, 10, Lit9, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        char$Mnfoldcase = moduleMethod10;
        new ModuleMethod(unicode2, 11, Lit10, 8194);
        char$Mnci$Eq$Qu = moduleMethod11;
        new ModuleMethod(unicode2, 12, Lit11, 8194);
        char$Mnci$Ls$Qu = moduleMethod12;
        new ModuleMethod(unicode2, 13, Lit12, 8194);
        char$Mnci$Gr$Qu = moduleMethod13;
        new ModuleMethod(unicode2, 14, Lit13, 8194);
        char$Mnci$Ls$Eq$Qu = moduleMethod14;
        new ModuleMethod(unicode2, 15, Lit14, 8194);
        char$Mnci$Gr$Eq$Qu = moduleMethod15;
        new ModuleMethod(unicode2, 16, Lit15, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        char$Mngeneral$Mncategory = moduleMethod16;
        new ModuleMethod(unicode2, 17, Lit16, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        string$Mnupcase = moduleMethod17;
        new ModuleMethod(unicode2, 18, Lit17, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        string$Mndowncase = moduleMethod18;
        new ModuleMethod(unicode2, 19, Lit18, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        string$Mntitlecase = moduleMethod19;
        new ModuleMethod(unicode2, 20, Lit19, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        string$Mnfoldcase = moduleMethod20;
        new ModuleMethod(unicode2, 21, Lit20, 8194);
        string$Mnci$Eq$Qu = moduleMethod21;
        new ModuleMethod(unicode2, 22, Lit21, 8194);
        string$Mnci$Ls$Qu = moduleMethod22;
        new ModuleMethod(unicode2, 23, Lit22, 8194);
        string$Mnci$Gr$Qu = moduleMethod23;
        new ModuleMethod(unicode2, 24, Lit23, 8194);
        string$Mnci$Ls$Eq$Qu = moduleMethod24;
        new ModuleMethod(unicode2, 25, Lit24, 8194);
        string$Mnci$Gr$Eq$Qu = moduleMethod25;
        new ModuleMethod(unicode2, 26, Lit25, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        string$Mnnormalize$Mnnfd = moduleMethod26;
        new ModuleMethod(unicode2, 27, Lit26, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        string$Mnnormalize$Mnnfkd = moduleMethod27;
        new ModuleMethod(unicode2, 28, Lit27, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        string$Mnnormalize$Mnnfc = moduleMethod28;
        new ModuleMethod(unicode2, 29, Lit28, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        string$Mnnormalize$Mnnfkc = moduleMethod29;
        $instance.run();
    }

    public unicode() {
        ModuleInfo.register(this);
    }

    public final void run(CallContext $ctx) {
        Consumer consumer = $ctx.consumer;
    }

    public static Char charUpcase(Char ch) {
        return Char.make(Character.toUpperCase(ch.intValue()));
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 1:
                CallContext callContext3 = callContext2;
                Object obj3 = obj2;
                Object obj4 = obj3;
                if (!(obj3 instanceof Char)) {
                    return -786431;
                }
                callContext3.value1 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 2:
                CallContext callContext4 = callContext2;
                Object obj5 = obj2;
                Object obj6 = obj5;
                if (!(obj5 instanceof Char)) {
                    return -786431;
                }
                callContext4.value1 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 3:
                CallContext callContext5 = callContext2;
                Object obj7 = obj2;
                Object obj8 = obj7;
                if (!(obj7 instanceof Char)) {
                    return -786431;
                }
                callContext5.value1 = obj8;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 4:
                CallContext callContext6 = callContext2;
                Object obj9 = obj2;
                Object obj10 = obj9;
                if (!(obj9 instanceof Char)) {
                    return -786431;
                }
                callContext6.value1 = obj10;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 5:
                CallContext callContext7 = callContext2;
                Object obj11 = obj2;
                Object obj12 = obj11;
                if (!(obj11 instanceof Char)) {
                    return -786431;
                }
                callContext7.value1 = obj12;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 6:
                CallContext callContext8 = callContext2;
                Object obj13 = obj2;
                Object obj14 = obj13;
                if (!(obj13 instanceof Char)) {
                    return -786431;
                }
                callContext8.value1 = obj14;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 7:
                CallContext callContext9 = callContext2;
                Object obj15 = obj2;
                Object obj16 = obj15;
                if (!(obj15 instanceof Char)) {
                    return -786431;
                }
                callContext9.value1 = obj16;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 8:
                CallContext callContext10 = callContext2;
                Object obj17 = obj2;
                Object obj18 = obj17;
                if (!(obj17 instanceof Char)) {
                    return -786431;
                }
                callContext10.value1 = obj18;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 9:
                CallContext callContext11 = callContext2;
                Object obj19 = obj2;
                Object obj20 = obj19;
                if (!(obj19 instanceof Char)) {
                    return -786431;
                }
                callContext11.value1 = obj20;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 10:
                CallContext callContext12 = callContext2;
                Object obj21 = obj2;
                Object obj22 = obj21;
                if (!(obj21 instanceof Char)) {
                    return -786431;
                }
                callContext12.value1 = obj22;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 16:
                CallContext callContext13 = callContext2;
                Object obj23 = obj2;
                Object obj24 = obj23;
                if (!(obj23 instanceof Char)) {
                    return -786431;
                }
                callContext13.value1 = obj24;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 17:
                CallContext callContext14 = callContext2;
                Object obj25 = obj2;
                Object obj26 = obj25;
                if (!(obj25 instanceof CharSequence)) {
                    return -786431;
                }
                callContext14.value1 = obj26;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 18:
                CallContext callContext15 = callContext2;
                Object obj27 = obj2;
                Object obj28 = obj27;
                if (!(obj27 instanceof CharSequence)) {
                    return -786431;
                }
                callContext15.value1 = obj28;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 19:
                CallContext callContext16 = callContext2;
                Object obj29 = obj2;
                Object obj30 = obj29;
                if (!(obj29 instanceof CharSequence)) {
                    return -786431;
                }
                callContext16.value1 = obj30;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 20:
                CallContext callContext17 = callContext2;
                Object obj31 = obj2;
                Object obj32 = obj31;
                if (!(obj31 instanceof CharSequence)) {
                    return -786431;
                }
                callContext17.value1 = obj32;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 26:
                CallContext callContext18 = callContext2;
                Object obj33 = obj2;
                Object obj34 = obj33;
                if (!(obj33 instanceof CharSequence)) {
                    return -786431;
                }
                callContext18.value1 = obj34;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 27:
                CallContext callContext19 = callContext2;
                Object obj35 = obj2;
                Object obj36 = obj35;
                if (!(obj35 instanceof CharSequence)) {
                    return -786431;
                }
                callContext19.value1 = obj36;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 28:
                CallContext callContext20 = callContext2;
                Object obj37 = obj2;
                Object obj38 = obj37;
                if (!(obj37 instanceof CharSequence)) {
                    return -786431;
                }
                callContext20.value1 = obj38;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 29:
                CallContext callContext21 = callContext2;
                Object obj39 = obj2;
                Object obj40 = obj39;
                if (!(obj39 instanceof CharSequence)) {
                    return -786431;
                }
                callContext21.value1 = obj40;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            default:
                return super.match1(moduleMethod2, obj2, callContext2);
        }
    }

    public static Char charDowncase(Char ch) {
        return Char.make(Character.toLowerCase(ch.intValue()));
    }

    public static Char charTitlecase(Char ch) {
        return Char.make(Character.toTitleCase(ch.intValue()));
    }

    public static boolean isCharAlphabetic(Char ch) {
        return Character.isLetter(ch.intValue());
    }

    public static boolean isCharNumeric(Char ch) {
        return Character.isDigit(ch.intValue());
    }

    public static boolean isCharWhitespace(Char ch) {
        return UnicodeUtils.isWhitespace(ch.intValue());
    }

    public static boolean isCharUpperCase(Char ch) {
        return Character.isUpperCase(ch.intValue());
    }

    public static boolean isCharLowerCase(Char ch) {
        return Character.isLowerCase(ch.intValue());
    }

    public static boolean isCharTitleCase(Char ch) {
        return Character.isTitleCase(ch.intValue());
    }

    public static Char charFoldcase(Char charR) {
        Char ch;
        Char ch2 = charR;
        int val = ch2.intValue();
        boolean x = val == 304;
        if (!x ? val != 305 : !x) {
            ch = Char.make(Character.toLowerCase(Character.toUpperCase(val)));
        } else {
            ch = ch2;
        }
        return ch;
    }

    public static boolean isCharCi$Eq(Char c1, Char c2) {
        return Character.toUpperCase(c1.intValue()) == Character.toUpperCase(c2.intValue());
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 11:
                CallContext callContext3 = callContext2;
                Object obj5 = obj3;
                Object obj6 = obj5;
                if (!(obj5 instanceof Char)) {
                    return -786431;
                }
                callContext3.value1 = obj6;
                CallContext callContext4 = callContext2;
                Object obj7 = obj4;
                Object obj8 = obj7;
                if (!(obj7 instanceof Char)) {
                    return -786430;
                }
                callContext4.value2 = obj8;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 12:
                CallContext callContext5 = callContext2;
                Object obj9 = obj3;
                Object obj10 = obj9;
                if (!(obj9 instanceof Char)) {
                    return -786431;
                }
                callContext5.value1 = obj10;
                CallContext callContext6 = callContext2;
                Object obj11 = obj4;
                Object obj12 = obj11;
                if (!(obj11 instanceof Char)) {
                    return -786430;
                }
                callContext6.value2 = obj12;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 13:
                CallContext callContext7 = callContext2;
                Object obj13 = obj3;
                Object obj14 = obj13;
                if (!(obj13 instanceof Char)) {
                    return -786431;
                }
                callContext7.value1 = obj14;
                CallContext callContext8 = callContext2;
                Object obj15 = obj4;
                Object obj16 = obj15;
                if (!(obj15 instanceof Char)) {
                    return -786430;
                }
                callContext8.value2 = obj16;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 14:
                CallContext callContext9 = callContext2;
                Object obj17 = obj3;
                Object obj18 = obj17;
                if (!(obj17 instanceof Char)) {
                    return -786431;
                }
                callContext9.value1 = obj18;
                CallContext callContext10 = callContext2;
                Object obj19 = obj4;
                Object obj20 = obj19;
                if (!(obj19 instanceof Char)) {
                    return -786430;
                }
                callContext10.value2 = obj20;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 15:
                CallContext callContext11 = callContext2;
                Object obj21 = obj3;
                Object obj22 = obj21;
                if (!(obj21 instanceof Char)) {
                    return -786431;
                }
                callContext11.value1 = obj22;
                CallContext callContext12 = callContext2;
                Object obj23 = obj4;
                Object obj24 = obj23;
                if (!(obj23 instanceof Char)) {
                    return -786430;
                }
                callContext12.value2 = obj24;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 21:
                CallContext callContext13 = callContext2;
                Object obj25 = obj3;
                Object obj26 = obj25;
                if (!(obj25 instanceof CharSequence)) {
                    return -786431;
                }
                callContext13.value1 = obj26;
                CallContext callContext14 = callContext2;
                Object obj27 = obj4;
                Object obj28 = obj27;
                if (!(obj27 instanceof CharSequence)) {
                    return -786430;
                }
                callContext14.value2 = obj28;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 22:
                CallContext callContext15 = callContext2;
                Object obj29 = obj3;
                Object obj30 = obj29;
                if (!(obj29 instanceof CharSequence)) {
                    return -786431;
                }
                callContext15.value1 = obj30;
                CallContext callContext16 = callContext2;
                Object obj31 = obj4;
                Object obj32 = obj31;
                if (!(obj31 instanceof CharSequence)) {
                    return -786430;
                }
                callContext16.value2 = obj32;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 23:
                CallContext callContext17 = callContext2;
                Object obj33 = obj3;
                Object obj34 = obj33;
                if (!(obj33 instanceof CharSequence)) {
                    return -786431;
                }
                callContext17.value1 = obj34;
                CallContext callContext18 = callContext2;
                Object obj35 = obj4;
                Object obj36 = obj35;
                if (!(obj35 instanceof CharSequence)) {
                    return -786430;
                }
                callContext18.value2 = obj36;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 24:
                CallContext callContext19 = callContext2;
                Object obj37 = obj3;
                Object obj38 = obj37;
                if (!(obj37 instanceof CharSequence)) {
                    return -786431;
                }
                callContext19.value1 = obj38;
                CallContext callContext20 = callContext2;
                Object obj39 = obj4;
                Object obj40 = obj39;
                if (!(obj39 instanceof CharSequence)) {
                    return -786430;
                }
                callContext20.value2 = obj40;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 25:
                CallContext callContext21 = callContext2;
                Object obj41 = obj3;
                Object obj42 = obj41;
                if (!(obj41 instanceof CharSequence)) {
                    return -786431;
                }
                callContext21.value1 = obj42;
                CallContext callContext22 = callContext2;
                Object obj43 = obj4;
                Object obj44 = obj43;
                if (!(obj43 instanceof CharSequence)) {
                    return -786430;
                }
                callContext22.value2 = obj44;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            default:
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
        }
    }

    public static boolean isCharCi$Ls(Char c1, Char c2) {
        return Character.toUpperCase(c1.intValue()) < Character.toUpperCase(c2.intValue());
    }

    public static boolean isCharCi$Gr(Char c1, Char c2) {
        return Character.toUpperCase(c1.intValue()) > Character.toUpperCase(c2.intValue());
    }

    public static boolean isCharCi$Ls$Eq(Char c1, Char c2) {
        return Character.toUpperCase(c1.intValue()) <= Character.toUpperCase(c2.intValue());
    }

    public static boolean isCharCi$Gr$Eq(Char c1, Char c2) {
        return Character.toUpperCase(c1.intValue()) >= Character.toUpperCase(c2.intValue());
    }

    public static Symbol charGeneralCategory(Char ch) {
        return UnicodeUtils.generalCategory(ch.intValue());
    }

    public static CharSequence stringUpcase(CharSequence str) {
        CharSequence str2;
        new FString((CharSequence) str.toString().toUpperCase(Locale.ENGLISH));
        return str2;
    }

    public static CharSequence stringDowncase(CharSequence str) {
        CharSequence str2;
        new FString((CharSequence) str.toString().toLowerCase(Locale.ENGLISH));
        return str2;
    }

    public static CharSequence stringTitlecase(CharSequence str) {
        CharSequence str2 = r5;
        CharSequence charSequence = str;
        FString fString = new FString((CharSequence) UnicodeUtils.capitalize(charSequence == null ? null : charSequence.toString()));
        return str2;
    }

    public static CharSequence stringFoldcase(CharSequence str) {
        CharSequence str2;
        new FString((CharSequence) UnicodeUtils.foldCase(str));
        return str2;
    }

    public static boolean isStringCi$Eq(CharSequence str1, CharSequence str2) {
        return UnicodeUtils.foldCase(str1).equals(UnicodeUtils.foldCase(str2));
    }

    public static boolean isStringCi$Ls(CharSequence str1, CharSequence str2) {
        return UnicodeUtils.foldCase(str1).compareTo(UnicodeUtils.foldCase(str2)) < 0;
    }

    public static boolean isStringCi$Gr(CharSequence str1, CharSequence str2) {
        return UnicodeUtils.foldCase(str1).compareTo(UnicodeUtils.foldCase(str2)) > 0;
    }

    public static boolean isStringCi$Ls$Eq(CharSequence str1, CharSequence str2) {
        return UnicodeUtils.foldCase(str1).compareTo(UnicodeUtils.foldCase(str2)) <= 0;
    }

    public static boolean isStringCi$Gr$Eq(CharSequence str1, CharSequence str2) {
        return UnicodeUtils.foldCase(str1).compareTo(UnicodeUtils.foldCase(str2)) >= 0;
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
            case 11:
                try {
                    try {
                        return isCharCi$Eq((Char) obj3, (Char) obj4) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th21 = th20;
                        new WrongType(classCastException, "char-ci=?", 2, obj4);
                        throw th21;
                    }
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th22 = th19;
                    new WrongType(classCastException2, "char-ci=?", 1, obj3);
                    throw th22;
                }
            case 12:
                try {
                    try {
                        return isCharCi$Ls((Char) obj3, (Char) obj4) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e3) {
                        ClassCastException classCastException3 = e3;
                        Throwable th23 = th18;
                        new WrongType(classCastException3, "char-ci<?", 2, obj4);
                        throw th23;
                    }
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th24 = th17;
                    new WrongType(classCastException4, "char-ci<?", 1, obj3);
                    throw th24;
                }
            case 13:
                try {
                    try {
                        return isCharCi$Gr((Char) obj3, (Char) obj4) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e5) {
                        ClassCastException classCastException5 = e5;
                        Throwable th25 = th16;
                        new WrongType(classCastException5, "char-ci>?", 2, obj4);
                        throw th25;
                    }
                } catch (ClassCastException e6) {
                    ClassCastException classCastException6 = e6;
                    Throwable th26 = th15;
                    new WrongType(classCastException6, "char-ci>?", 1, obj3);
                    throw th26;
                }
            case 14:
                try {
                    try {
                        return isCharCi$Ls$Eq((Char) obj3, (Char) obj4) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e7) {
                        ClassCastException classCastException7 = e7;
                        Throwable th27 = th14;
                        new WrongType(classCastException7, "char-ci<=?", 2, obj4);
                        throw th27;
                    }
                } catch (ClassCastException e8) {
                    ClassCastException classCastException8 = e8;
                    Throwable th28 = th13;
                    new WrongType(classCastException8, "char-ci<=?", 1, obj3);
                    throw th28;
                }
            case 15:
                try {
                    try {
                        return isCharCi$Gr$Eq((Char) obj3, (Char) obj4) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e9) {
                        ClassCastException classCastException9 = e9;
                        Throwable th29 = th12;
                        new WrongType(classCastException9, "char-ci>=?", 2, obj4);
                        throw th29;
                    }
                } catch (ClassCastException e10) {
                    ClassCastException classCastException10 = e10;
                    Throwable th30 = th11;
                    new WrongType(classCastException10, "char-ci>=?", 1, obj3);
                    throw th30;
                }
            case 21:
                try {
                    try {
                        return isStringCi$Eq((CharSequence) obj3, (CharSequence) obj4) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e11) {
                        ClassCastException classCastException11 = e11;
                        Throwable th31 = th10;
                        new WrongType(classCastException11, "string-ci=?", 2, obj4);
                        throw th31;
                    }
                } catch (ClassCastException e12) {
                    ClassCastException classCastException12 = e12;
                    Throwable th32 = th9;
                    new WrongType(classCastException12, "string-ci=?", 1, obj3);
                    throw th32;
                }
            case 22:
                try {
                    try {
                        return isStringCi$Ls((CharSequence) obj3, (CharSequence) obj4) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e13) {
                        ClassCastException classCastException13 = e13;
                        Throwable th33 = th8;
                        new WrongType(classCastException13, "string-ci<?", 2, obj4);
                        throw th33;
                    }
                } catch (ClassCastException e14) {
                    ClassCastException classCastException14 = e14;
                    Throwable th34 = th7;
                    new WrongType(classCastException14, "string-ci<?", 1, obj3);
                    throw th34;
                }
            case 23:
                try {
                    try {
                        return isStringCi$Gr((CharSequence) obj3, (CharSequence) obj4) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e15) {
                        ClassCastException classCastException15 = e15;
                        Throwable th35 = th6;
                        new WrongType(classCastException15, "string-ci>?", 2, obj4);
                        throw th35;
                    }
                } catch (ClassCastException e16) {
                    ClassCastException classCastException16 = e16;
                    Throwable th36 = th5;
                    new WrongType(classCastException16, "string-ci>?", 1, obj3);
                    throw th36;
                }
            case 24:
                try {
                    try {
                        return isStringCi$Ls$Eq((CharSequence) obj3, (CharSequence) obj4) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e17) {
                        ClassCastException classCastException17 = e17;
                        Throwable th37 = th4;
                        new WrongType(classCastException17, "string-ci<=?", 2, obj4);
                        throw th37;
                    }
                } catch (ClassCastException e18) {
                    ClassCastException classCastException18 = e18;
                    Throwable th38 = th3;
                    new WrongType(classCastException18, "string-ci<=?", 1, obj3);
                    throw th38;
                }
            case 25:
                try {
                    try {
                        return isStringCi$Gr$Eq((CharSequence) obj3, (CharSequence) obj4) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e19) {
                        ClassCastException classCastException19 = e19;
                        Throwable th39 = th2;
                        new WrongType(classCastException19, "string-ci>=?", 2, obj4);
                        throw th39;
                    }
                } catch (ClassCastException e20) {
                    ClassCastException classCastException20 = e20;
                    Throwable th40 = th;
                    new WrongType(classCastException20, "string-ci>=?", 1, obj3);
                    throw th40;
                }
            default:
                return super.apply2(moduleMethod2, obj3, obj4);
        }
    }

    public static CharSequence stringNormalizeNfd(CharSequence charSequence) {
        CharSequence charSequence2 = charSequence;
        return (CharSequence) misc.error$V("unicode string normalization not available", new Object[0]);
    }

    public static CharSequence stringNormalizeNfkd(CharSequence charSequence) {
        CharSequence charSequence2 = charSequence;
        return (CharSequence) misc.error$V("unicode string normalization not available", new Object[0]);
    }

    public static CharSequence stringNormalizeNfc(CharSequence charSequence) {
        CharSequence charSequence2 = charSequence;
        return (CharSequence) misc.error$V("unicode string normalization not available", new Object[0]);
    }

    public static CharSequence stringNormalizeNfkc(CharSequence charSequence) {
        CharSequence charSequence2 = charSequence;
        return (CharSequence) misc.error$V("unicode string normalization not available", new Object[0]);
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
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        switch (moduleMethod2.selector) {
            case 1:
                try {
                    return charUpcase((Char) obj2);
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th20 = th19;
                    new WrongType(classCastException, "char-upcase", 1, obj2);
                    throw th20;
                }
            case 2:
                try {
                    return charDowncase((Char) obj2);
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th21 = th18;
                    new WrongType(classCastException2, "char-downcase", 1, obj2);
                    throw th21;
                }
            case 3:
                try {
                    return charTitlecase((Char) obj2);
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th22 = th17;
                    new WrongType(classCastException3, "char-titlecase", 1, obj2);
                    throw th22;
                }
            case 4:
                try {
                    return isCharAlphabetic((Char) obj2) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th23 = th16;
                    new WrongType(classCastException4, "char-alphabetic?", 1, obj2);
                    throw th23;
                }
            case 5:
                try {
                    return isCharNumeric((Char) obj2) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e5) {
                    ClassCastException classCastException5 = e5;
                    Throwable th24 = th15;
                    new WrongType(classCastException5, "char-numeric?", 1, obj2);
                    throw th24;
                }
            case 6:
                try {
                    return isCharWhitespace((Char) obj2) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e6) {
                    ClassCastException classCastException6 = e6;
                    Throwable th25 = th14;
                    new WrongType(classCastException6, "char-whitespace?", 1, obj2);
                    throw th25;
                }
            case 7:
                try {
                    return isCharUpperCase((Char) obj2) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e7) {
                    ClassCastException classCastException7 = e7;
                    Throwable th26 = th13;
                    new WrongType(classCastException7, "char-upper-case?", 1, obj2);
                    throw th26;
                }
            case 8:
                try {
                    return isCharLowerCase((Char) obj2) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e8) {
                    ClassCastException classCastException8 = e8;
                    Throwable th27 = th12;
                    new WrongType(classCastException8, "char-lower-case?", 1, obj2);
                    throw th27;
                }
            case 9:
                try {
                    return isCharTitleCase((Char) obj2) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e9) {
                    ClassCastException classCastException9 = e9;
                    Throwable th28 = th11;
                    new WrongType(classCastException9, "char-title-case?", 1, obj2);
                    throw th28;
                }
            case 10:
                try {
                    return charFoldcase((Char) obj2);
                } catch (ClassCastException e10) {
                    ClassCastException classCastException10 = e10;
                    Throwable th29 = th10;
                    new WrongType(classCastException10, "char-foldcase", 1, obj2);
                    throw th29;
                }
            case 16:
                try {
                    return charGeneralCategory((Char) obj2);
                } catch (ClassCastException e11) {
                    ClassCastException classCastException11 = e11;
                    Throwable th30 = th9;
                    new WrongType(classCastException11, "char-general-category", 1, obj2);
                    throw th30;
                }
            case 17:
                try {
                    return stringUpcase((CharSequence) obj2);
                } catch (ClassCastException e12) {
                    ClassCastException classCastException12 = e12;
                    Throwable th31 = th8;
                    new WrongType(classCastException12, "string-upcase", 1, obj2);
                    throw th31;
                }
            case 18:
                try {
                    return stringDowncase((CharSequence) obj2);
                } catch (ClassCastException e13) {
                    ClassCastException classCastException13 = e13;
                    Throwable th32 = th7;
                    new WrongType(classCastException13, "string-downcase", 1, obj2);
                    throw th32;
                }
            case 19:
                try {
                    return stringTitlecase((CharSequence) obj2);
                } catch (ClassCastException e14) {
                    ClassCastException classCastException14 = e14;
                    Throwable th33 = th6;
                    new WrongType(classCastException14, "string-titlecase", 1, obj2);
                    throw th33;
                }
            case 20:
                try {
                    return stringFoldcase((CharSequence) obj2);
                } catch (ClassCastException e15) {
                    ClassCastException classCastException15 = e15;
                    Throwable th34 = th5;
                    new WrongType(classCastException15, "string-foldcase", 1, obj2);
                    throw th34;
                }
            case 26:
                try {
                    return stringNormalizeNfd((CharSequence) obj2);
                } catch (ClassCastException e16) {
                    ClassCastException classCastException16 = e16;
                    Throwable th35 = th4;
                    new WrongType(classCastException16, "string-normalize-nfd", 1, obj2);
                    throw th35;
                }
            case 27:
                try {
                    return stringNormalizeNfkd((CharSequence) obj2);
                } catch (ClassCastException e17) {
                    ClassCastException classCastException17 = e17;
                    Throwable th36 = th3;
                    new WrongType(classCastException17, "string-normalize-nfkd", 1, obj2);
                    throw th36;
                }
            case 28:
                try {
                    return stringNormalizeNfc((CharSequence) obj2);
                } catch (ClassCastException e18) {
                    ClassCastException classCastException18 = e18;
                    Throwable th37 = th2;
                    new WrongType(classCastException18, "string-normalize-nfc", 1, obj2);
                    throw th37;
                }
            case 29:
                try {
                    return stringNormalizeNfkc((CharSequence) obj2);
                } catch (ClassCastException e19) {
                    ClassCastException classCastException19 = e19;
                    Throwable th38 = th;
                    new WrongType(classCastException19, "string-normalize-nfkc", 1, obj2);
                    throw th38;
                }
            default:
                return super.apply1(moduleMethod2, obj2);
        }
    }
}

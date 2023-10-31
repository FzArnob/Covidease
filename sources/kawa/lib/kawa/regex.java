package kawa.lib.kawa;

import android.support.p000v4.app.FragmentTransaction;
import android.support.p000v4.view.InputDeviceCompat;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kawa.lib.C1245lists;
import kawa.lib.misc;
import kawa.standard.Scheme;

/* compiled from: regex.scm */
public class regex extends ModuleBody {
    public static final regex $instance;
    static final SimpleSymbol Lit0;
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit2;
    static final SimpleSymbol Lit3;
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    public static final ModuleMethod regex$Mnmatch;
    public static final ModuleMethod regex$Mnmatch$Mnpositions;
    public static final ModuleMethod regex$Mnmatch$Qu;
    public static final ModuleMethod regex$Mnquote;
    public static final ModuleMethod regex$Mnreplace;
    public static final ModuleMethod regex$Mnreplace$St;
    public static final ModuleMethod regex$Mnsplit;

    static {
        SimpleSymbol simpleSymbol;
        SimpleSymbol simpleSymbol2;
        SimpleSymbol simpleSymbol3;
        SimpleSymbol simpleSymbol4;
        SimpleSymbol simpleSymbol5;
        SimpleSymbol simpleSymbol6;
        SimpleSymbol simpleSymbol7;
        SimpleSymbol simpleSymbol8;
        regex regex;
        ModuleMethod moduleMethod;
        ModuleMethod moduleMethod2;
        ModuleMethod moduleMethod3;
        ModuleMethod moduleMethod4;
        ModuleMethod moduleMethod5;
        ModuleMethod moduleMethod6;
        ModuleMethod moduleMethod7;
        new SimpleSymbol("regex-replace*");
        Lit7 = (SimpleSymbol) simpleSymbol.readResolve();
        new SimpleSymbol("regex-replace");
        Lit6 = (SimpleSymbol) simpleSymbol2.readResolve();
        new SimpleSymbol("regex-split");
        Lit5 = (SimpleSymbol) simpleSymbol3.readResolve();
        new SimpleSymbol("regex-match-positions");
        Lit4 = (SimpleSymbol) simpleSymbol4.readResolve();
        new SimpleSymbol("regex-match");
        Lit3 = (SimpleSymbol) simpleSymbol5.readResolve();
        new SimpleSymbol("regex-match?");
        Lit2 = (SimpleSymbol) simpleSymbol6.readResolve();
        new SimpleSymbol("regex-quote");
        Lit1 = (SimpleSymbol) simpleSymbol7.readResolve();
        new SimpleSymbol("loop");
        Lit0 = (SimpleSymbol) simpleSymbol8.readResolve();
        new regex();
        $instance = regex;
        regex regex2 = $instance;
        new ModuleMethod(regex2, 2, Lit1, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        regex$Mnquote = moduleMethod;
        new ModuleMethod(regex2, 3, Lit2, InputDeviceCompat.SOURCE_STYLUS);
        regex$Mnmatch$Qu = moduleMethod2;
        new ModuleMethod(regex2, 6, Lit3, InputDeviceCompat.SOURCE_STYLUS);
        regex$Mnmatch = moduleMethod3;
        new ModuleMethod(regex2, 9, Lit4, InputDeviceCompat.SOURCE_STYLUS);
        regex$Mnmatch$Mnpositions = moduleMethod4;
        new ModuleMethod(regex2, 12, Lit5, 8194);
        regex$Mnsplit = moduleMethod5;
        new ModuleMethod(regex2, 13, Lit6, 12291);
        regex$Mnreplace = moduleMethod6;
        new ModuleMethod(regex2, 14, Lit7, 12291);
        regex$Mnreplace$St = moduleMethod7;
        $instance.run();
    }

    public regex() {
        ModuleInfo.register(this);
    }

    public static boolean isRegexMatch(Object obj, CharSequence charSequence) {
        return isRegexMatch(obj, charSequence, 0);
    }

    public static boolean isRegexMatch(Object obj, CharSequence charSequence, int i) {
        CharSequence charSequence2 = charSequence;
        return isRegexMatch(obj, charSequence2, i, charSequence2.length());
    }

    public static Object regexMatch(Object obj, CharSequence charSequence) {
        return regexMatch(obj, charSequence, 0);
    }

    public static Object regexMatch(Object obj, CharSequence charSequence, int i) {
        CharSequence charSequence2 = charSequence;
        return regexMatch(obj, charSequence2, i, charSequence2.length());
    }

    public static Object regexMatchPositions(Object obj, CharSequence charSequence) {
        return regexMatchPositions(obj, charSequence, 0);
    }

    public static Object regexMatchPositions(Object obj, CharSequence charSequence, int i) {
        CharSequence charSequence2 = charSequence;
        return regexMatchPositions(obj, charSequence2, i, charSequence2.length());
    }

    public final void run(CallContext $ctx) {
        Consumer consumer = $ctx.consumer;
    }

    public static String regexQuote(CharSequence str) {
        CharSequence charSequence = str;
        return Pattern.quote(charSequence == null ? null : charSequence.toString());
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        Throwable th;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        if (moduleMethod2.selector != 2) {
            return super.apply1(moduleMethod2, obj2);
        }
        try {
            return regexQuote((CharSequence) obj2);
        } catch (ClassCastException e) {
            ClassCastException classCastException = e;
            Throwable th2 = th;
            new WrongType(classCastException, "regex-quote", 1, obj2);
            throw th2;
        }
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        CallContext callContext2 = callContext;
        if (moduleMethod2.selector != 2) {
            return super.match1(moduleMethod2, obj2, callContext2);
        }
        CallContext callContext3 = callContext2;
        Object obj3 = obj2;
        Object obj4 = obj3;
        if (!(obj3 instanceof CharSequence)) {
            return -786431;
        }
        callContext3.value1 = obj4;
        callContext2.proc = moduleMethod2;
        callContext2.f239pc = 1;
        return 0;
    }

    public static boolean isRegexMatch(Object obj, CharSequence charSequence, int i, int i2) {
        Pattern rex;
        Throwable th;
        Object re = obj;
        CharSequence str = charSequence;
        int start = i;
        int end = i2;
        if (re instanceof Pattern) {
            Object obj2 = re;
            Object obj3 = obj2;
            try {
                rex = (Pattern) obj2;
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "rex", -2, obj3);
                throw th2;
            }
        } else {
            rex = Pattern.compile(re.toString());
        }
        Matcher matcher = rex.matcher(str);
        Matcher region = matcher.region(start, end);
        return matcher.find();
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
                if (!(obj5 instanceof CharSequence)) {
                    return -786430;
                }
                callContext3.value2 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 6:
                callContext2.value1 = obj3;
                CallContext callContext4 = callContext2;
                Object obj7 = obj4;
                Object obj8 = obj7;
                if (!(obj7 instanceof CharSequence)) {
                    return -786430;
                }
                callContext4.value2 = obj8;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 9:
                callContext2.value1 = obj3;
                CallContext callContext5 = callContext2;
                Object obj9 = obj4;
                Object obj10 = obj9;
                if (!(obj9 instanceof CharSequence)) {
                    return -786430;
                }
                callContext5.value2 = obj10;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 12:
                callContext2.value1 = obj3;
                CallContext callContext6 = callContext2;
                Object obj11 = obj4;
                Object obj12 = obj11;
                if (!(obj11 instanceof CharSequence)) {
                    return -786430;
                }
                callContext6.value2 = obj12;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            default:
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
        }
    }

    public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj4 = obj;
        Object obj5 = obj2;
        Object obj6 = obj3;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 3:
                callContext2.value1 = obj4;
                CallContext callContext3 = callContext2;
                Object obj7 = obj5;
                Object obj8 = obj7;
                if (!(obj7 instanceof CharSequence)) {
                    return -786430;
                }
                callContext3.value2 = obj8;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 6:
                callContext2.value1 = obj4;
                CallContext callContext4 = callContext2;
                Object obj9 = obj5;
                Object obj10 = obj9;
                if (!(obj9 instanceof CharSequence)) {
                    return -786430;
                }
                callContext4.value2 = obj10;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 9:
                callContext2.value1 = obj4;
                CallContext callContext5 = callContext2;
                Object obj11 = obj5;
                Object obj12 = obj11;
                if (!(obj11 instanceof CharSequence)) {
                    return -786430;
                }
                callContext5.value2 = obj12;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 13:
                callContext2.value1 = obj4;
                CallContext callContext6 = callContext2;
                Object obj13 = obj5;
                Object obj14 = obj13;
                if (!(obj13 instanceof CharSequence)) {
                    return -786430;
                }
                callContext6.value2 = obj14;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 14:
                callContext2.value1 = obj4;
                CallContext callContext7 = callContext2;
                Object obj15 = obj5;
                Object obj16 = obj15;
                if (!(obj15 instanceof CharSequence)) {
                    return -786430;
                }
                callContext7.value2 = obj16;
                callContext2.value3 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            default:
                return super.match3(moduleMethod2, obj4, obj5, obj6, callContext2);
        }
    }

    public int match4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj5 = obj;
        Object obj6 = obj2;
        Object obj7 = obj3;
        Object obj8 = obj4;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 3:
                callContext2.value1 = obj5;
                CallContext callContext3 = callContext2;
                Object obj9 = obj6;
                Object obj10 = obj9;
                if (!(obj9 instanceof CharSequence)) {
                    return -786430;
                }
                callContext3.value2 = obj10;
                callContext2.value3 = obj7;
                callContext2.value4 = obj8;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 4;
                return 0;
            case 6:
                callContext2.value1 = obj5;
                CallContext callContext4 = callContext2;
                Object obj11 = obj6;
                Object obj12 = obj11;
                if (!(obj11 instanceof CharSequence)) {
                    return -786430;
                }
                callContext4.value2 = obj12;
                callContext2.value3 = obj7;
                callContext2.value4 = obj8;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 4;
                return 0;
            case 9:
                callContext2.value1 = obj5;
                CallContext callContext5 = callContext2;
                Object obj13 = obj6;
                Object obj14 = obj13;
                if (!(obj13 instanceof CharSequence)) {
                    return -786430;
                }
                callContext5.value2 = obj14;
                callContext2.value3 = obj7;
                callContext2.value4 = obj8;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 4;
                return 0;
            default:
                return super.match4(moduleMethod2, obj5, obj6, obj7, obj8, callContext2);
        }
    }

    public static Object regexMatch(Object obj, CharSequence charSequence, int i, int i2) {
        Pattern rex;
        Object re;
        Object subSequence;
        Throwable th;
        Object re2 = obj;
        CharSequence str = charSequence;
        int start = i;
        int end = i2;
        if (re2 instanceof Pattern) {
            Object obj2 = re2;
            Object obj3 = obj2;
            try {
                rex = (Pattern) obj2;
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "rex", -2, obj3);
                throw th2;
            }
        } else {
            rex = Pattern.compile(re2.toString());
        }
        Matcher matcher = rex.matcher(str);
        Matcher region = matcher.region(start, end);
        if (matcher.find()) {
            int groupCount = matcher.groupCount();
            Object obj4 = LList.Empty;
            for (int igroup = groupCount; igroup >= 0; igroup--) {
                int start2 = matcher.start(igroup);
                if (start2 < 0) {
                    subSequence = Boolean.FALSE;
                } else {
                    subSequence = str.subSequence(start2, matcher.end(igroup));
                }
                obj4 = C1245lists.cons(subSequence, obj4);
            }
            re = obj4;
        } else {
            re = Boolean.FALSE;
        }
        return re;
    }

    public static Object regexMatchPositions(Object obj, CharSequence charSequence, int i, int i2) {
        Pattern rex;
        Object re;
        Object cons;
        Throwable th;
        Object re2 = obj;
        CharSequence str = charSequence;
        int start = i;
        int end = i2;
        if (re2 instanceof Pattern) {
            Object obj2 = re2;
            Object obj3 = obj2;
            try {
                rex = (Pattern) obj2;
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "rex", -2, obj3);
                throw th2;
            }
        } else {
            rex = Pattern.compile(re2.toString());
        }
        Matcher matcher = rex.matcher(str);
        Matcher region = matcher.region(start, end);
        if (matcher.find()) {
            int groupCount = matcher.groupCount();
            Object obj4 = LList.Empty;
            for (int igroup = groupCount; igroup >= 0; igroup--) {
                int start2 = matcher.start(igroup);
                if (start2 < 0) {
                    cons = Boolean.FALSE;
                } else {
                    cons = C1245lists.cons(Integer.valueOf(start2), Integer.valueOf(matcher.end(igroup)));
                }
                obj4 = C1245lists.cons(cons, obj4);
            }
            re = obj4;
        } else {
            re = Boolean.FALSE;
        }
        return re;
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
        Throwable th9;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj5 = obj;
        Object obj6 = obj2;
        Object obj7 = obj3;
        Object obj8 = obj4;
        switch (moduleMethod2.selector) {
            case 3:
                try {
                    try {
                        try {
                            return isRegexMatch(obj5, (CharSequence) obj6, ((Number) obj7).intValue(), ((Number) obj8).intValue()) ? Boolean.TRUE : Boolean.FALSE;
                        } catch (ClassCastException e) {
                            ClassCastException classCastException = e;
                            Throwable th10 = th9;
                            new WrongType(classCastException, "regex-match?", 4, obj8);
                            throw th10;
                        }
                    } catch (ClassCastException e2) {
                        ClassCastException classCastException2 = e2;
                        Throwable th11 = th8;
                        new WrongType(classCastException2, "regex-match?", 3, obj7);
                        throw th11;
                    }
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th12 = th7;
                    new WrongType(classCastException3, "regex-match?", 2, obj6);
                    throw th12;
                }
            case 6:
                try {
                    try {
                        try {
                            return regexMatch(obj5, (CharSequence) obj6, ((Number) obj7).intValue(), ((Number) obj8).intValue());
                        } catch (ClassCastException e4) {
                            ClassCastException classCastException4 = e4;
                            Throwable th13 = th6;
                            new WrongType(classCastException4, "regex-match", 4, obj8);
                            throw th13;
                        }
                    } catch (ClassCastException e5) {
                        ClassCastException classCastException5 = e5;
                        Throwable th14 = th5;
                        new WrongType(classCastException5, "regex-match", 3, obj7);
                        throw th14;
                    }
                } catch (ClassCastException e6) {
                    ClassCastException classCastException6 = e6;
                    Throwable th15 = th4;
                    new WrongType(classCastException6, "regex-match", 2, obj6);
                    throw th15;
                }
            case 9:
                try {
                    try {
                        try {
                            return regexMatchPositions(obj5, (CharSequence) obj6, ((Number) obj7).intValue(), ((Number) obj8).intValue());
                        } catch (ClassCastException e7) {
                            ClassCastException classCastException7 = e7;
                            Throwable th16 = th3;
                            new WrongType(classCastException7, "regex-match-positions", 4, obj8);
                            throw th16;
                        }
                    } catch (ClassCastException e8) {
                        ClassCastException classCastException8 = e8;
                        Throwable th17 = th2;
                        new WrongType(classCastException8, "regex-match-positions", 3, obj7);
                        throw th17;
                    }
                } catch (ClassCastException e9) {
                    ClassCastException classCastException9 = e9;
                    Throwable th18 = th;
                    new WrongType(classCastException9, "regex-match-positions", 2, obj6);
                    throw th18;
                }
            default:
                return super.apply4(moduleMethod2, obj5, obj6, obj7, obj8);
        }
    }

    public static LList regexSplit(Object obj, CharSequence charSequence) {
        Pattern rex;
        Throwable th;
        Object re = obj;
        CharSequence str = charSequence;
        if (re instanceof Pattern) {
            Object obj2 = re;
            Object obj3 = obj2;
            try {
                rex = (Pattern) obj2;
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "rex", -2, obj3);
                throw th2;
            }
        } else {
            rex = Pattern.compile(re.toString());
        }
        return LList.makeList(rex.split(str, -1), 0);
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        switch (moduleMethod2.selector) {
            case 3:
                try {
                    return isRegexMatch(obj3, (CharSequence) obj4) ? Boolean.TRUE : Boolean.FALSE;
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th5 = th4;
                    new WrongType(classCastException, "regex-match?", 2, obj4);
                    throw th5;
                }
            case 6:
                try {
                    return regexMatch(obj3, (CharSequence) obj4);
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th6 = th3;
                    new WrongType(classCastException2, "regex-match", 2, obj4);
                    throw th6;
                }
            case 9:
                try {
                    return regexMatchPositions(obj3, (CharSequence) obj4);
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th7 = th2;
                    new WrongType(classCastException3, "regex-match-positions", 2, obj4);
                    throw th7;
                }
            case 12:
                try {
                    return regexSplit(obj3, (CharSequence) obj4);
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th8 = th;
                    new WrongType(classCastException4, "regex-split", 2, obj4);
                    throw th8;
                }
            default:
                return super.apply2(moduleMethod2, obj3, obj4);
        }
    }

    public static CharSequence regexReplace(Object obj, CharSequence charSequence, Object obj2) {
        Pattern rex;
        CharSequence charSequence2;
        StringBuffer stringBuffer;
        String obj3;
        Throwable th;
        Object re = obj;
        CharSequence str = charSequence;
        Object repl = obj2;
        if (re instanceof Pattern) {
            Object obj4 = re;
            Object obj5 = obj4;
            try {
                rex = (Pattern) obj4;
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "rex", -2, obj5);
                throw th2;
            }
        } else {
            rex = Pattern.compile(re.toString());
        }
        Matcher matcher = rex.matcher(str);
        if (matcher.find()) {
            new StringBuffer();
            StringBuffer sbuf = stringBuffer;
            Matcher matcher2 = matcher;
            StringBuffer stringBuffer2 = sbuf;
            if (misc.isProcedure(repl)) {
                Object apply2 = Scheme.applyToArgs.apply2(repl, matcher.group());
                obj3 = Matcher.quoteReplacement(apply2 == null ? null : apply2.toString());
            } else {
                Object obj6 = repl;
                obj3 = obj6 == null ? null : obj6.toString();
            }
            Matcher appendReplacement = matcher2.appendReplacement(stringBuffer2, obj3);
            StringBuffer appendTail = matcher.appendTail(sbuf);
            charSequence2 = sbuf.toString();
        } else {
            charSequence2 = str;
        }
        return charSequence2;
    }

    public static CharSequence regexReplace$St(Object obj, CharSequence charSequence, Object repl) {
        frame frame2;
        Pattern rex;
        StringBuffer stringBuffer;
        String replaceAll;
        Throwable th;
        Object re = obj;
        CharSequence str = charSequence;
        new frame();
        frame frame3 = frame2;
        frame3.repl = repl;
        if (re instanceof Pattern) {
            Object obj2 = re;
            Object obj3 = obj2;
            try {
                rex = (Pattern) obj2;
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "rex", -2, obj3);
                throw th2;
            }
        } else {
            rex = Pattern.compile(re.toString());
        }
        frame3.matcher = rex.matcher(str);
        new StringBuffer();
        frame3.sbuf = stringBuffer;
        if (misc.isProcedure(frame3.repl)) {
            frame3.loop = frame3.loop;
            replaceAll = frame3.lambda1loop();
        } else {
            Matcher matcher = frame3.matcher;
            Object obj4 = frame3.repl;
            replaceAll = matcher.replaceAll(obj4 == null ? null : obj4.toString());
        }
        return replaceAll;
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
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj4 = obj;
        Object obj5 = obj2;
        Object obj6 = obj3;
        switch (moduleMethod2.selector) {
            case 3:
                try {
                    try {
                        return isRegexMatch(obj4, (CharSequence) obj5, ((Number) obj6).intValue()) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th9 = th8;
                        new WrongType(classCastException, "regex-match?", 3, obj6);
                        throw th9;
                    }
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th10 = th7;
                    new WrongType(classCastException2, "regex-match?", 2, obj5);
                    throw th10;
                }
            case 6:
                try {
                    try {
                        return regexMatch(obj4, (CharSequence) obj5, ((Number) obj6).intValue());
                    } catch (ClassCastException e3) {
                        ClassCastException classCastException3 = e3;
                        Throwable th11 = th6;
                        new WrongType(classCastException3, "regex-match", 3, obj6);
                        throw th11;
                    }
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th12 = th5;
                    new WrongType(classCastException4, "regex-match", 2, obj5);
                    throw th12;
                }
            case 9:
                try {
                    try {
                        return regexMatchPositions(obj4, (CharSequence) obj5, ((Number) obj6).intValue());
                    } catch (ClassCastException e5) {
                        ClassCastException classCastException5 = e5;
                        Throwable th13 = th4;
                        new WrongType(classCastException5, "regex-match-positions", 3, obj6);
                        throw th13;
                    }
                } catch (ClassCastException e6) {
                    ClassCastException classCastException6 = e6;
                    Throwable th14 = th3;
                    new WrongType(classCastException6, "regex-match-positions", 2, obj5);
                    throw th14;
                }
            case 13:
                try {
                    return regexReplace(obj4, (CharSequence) obj5, obj6);
                } catch (ClassCastException e7) {
                    ClassCastException classCastException7 = e7;
                    Throwable th15 = th2;
                    new WrongType(classCastException7, "regex-replace", 2, obj5);
                    throw th15;
                }
            case 14:
                try {
                    return regexReplace$St(obj4, (CharSequence) obj5, obj6);
                } catch (ClassCastException e8) {
                    ClassCastException classCastException8 = e8;
                    Throwable th16 = th;
                    new WrongType(classCastException8, "regex-replace*", 2, obj5);
                    throw th16;
                }
            default:
                return super.apply3(moduleMethod2, obj4, obj5, obj6);
        }
    }

    /* compiled from: regex.scm */
    public class frame extends ModuleBody {
        Object loop;
        Matcher matcher;
        Object repl;
        StringBuffer sbuf;

        public frame() {
            Object obj;
            new ModuleMethod(this, 1, regex.Lit0, 0);
            this.loop = obj;
        }

        public Object apply0(ModuleMethod moduleMethod) {
            ModuleMethod moduleMethod2 = moduleMethod;
            return moduleMethod2.selector == 1 ? lambda1loop() : super.apply0(moduleMethod2);
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

        public String lambda1loop() {
            if (this.matcher.find()) {
                Matcher matcher2 = this.matcher;
                StringBuffer stringBuffer = this.sbuf;
                Object apply2 = Scheme.applyToArgs.apply2(this.repl, this.matcher.group());
                Matcher appendReplacement = matcher2.appendReplacement(stringBuffer, Matcher.quoteReplacement(apply2 == null ? null : apply2.toString()));
            }
            StringBuffer appendTail = this.matcher.appendTail(this.sbuf);
            return this.sbuf.toString();
        }
    }
}

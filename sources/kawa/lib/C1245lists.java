package kawa.lib;

import android.support.p000v4.app.FragmentTransaction;
import gnu.expr.GenericProc;
import gnu.expr.Keyword;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.reflect.SlotSet;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import kawa.standard.Scheme;

/* renamed from: kawa.lib.lists */
/* compiled from: lists.scm */
public class C1245lists extends ModuleBody {
    public static final Location $Prvt$define$Mnprocedure = StaticFieldLocation.make("kawa.lib.std_syntax", "define$Mnprocedure");
    public static final C1245lists $instance;
    static final Keyword Lit0 = Keyword.make("setter");
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
    static final SimpleSymbol Lit3;
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    public static final ModuleMethod assoc;
    public static final ModuleMethod assq;
    public static final ModuleMethod assv;
    public static final GenericProc caaaar = null;
    static final ModuleMethod caaaar$Fn28;
    public static final GenericProc caaadr = null;
    static final ModuleMethod caaadr$Fn30;
    public static final GenericProc caaar = null;
    static final ModuleMethod caaar$Fn12;
    public static final GenericProc caadar = null;
    static final ModuleMethod caadar$Fn32;
    public static final GenericProc caaddr = null;
    static final ModuleMethod caaddr$Fn34;
    public static final GenericProc caadr = null;
    static final ModuleMethod caadr$Fn14;
    public static final GenericProc caar = null;
    static final ModuleMethod caar$Fn4;
    public static final GenericProc cadaar = null;
    static final ModuleMethod cadaar$Fn36;
    public static final GenericProc cadadr = null;
    static final ModuleMethod cadadr$Fn38;
    public static final GenericProc cadar = null;
    static final ModuleMethod cadar$Fn16;
    public static final GenericProc caddar = null;
    static final ModuleMethod caddar$Fn40;
    public static final GenericProc cadddr = null;
    static final ModuleMethod cadddr$Fn42;
    public static final GenericProc caddr = null;
    static final ModuleMethod caddr$Fn18;
    public static final GenericProc cadr = null;
    static final ModuleMethod cadr$Fn6;
    public static final GenericProc car = null;
    static final ModuleMethod car$Fn1;
    public static final GenericProc cdaaar = null;
    static final ModuleMethod cdaaar$Fn44;
    public static final GenericProc cdaadr = null;
    static final ModuleMethod cdaadr$Fn46;
    public static final GenericProc cdaar = null;
    static final ModuleMethod cdaar$Fn20;
    public static final GenericProc cdadar = null;
    static final ModuleMethod cdadar$Fn48;
    public static final GenericProc cdaddr = null;
    static final ModuleMethod cdaddr$Fn50;
    public static final GenericProc cdadr = null;
    static final ModuleMethod cdadr$Fn22;
    public static final GenericProc cdar = null;
    static final ModuleMethod cdar$Fn8;
    public static final GenericProc cddaar = null;
    static final ModuleMethod cddaar$Fn52;
    public static final GenericProc cddadr = null;
    static final ModuleMethod cddadr$Fn54;
    public static final GenericProc cddar = null;
    static final ModuleMethod cddar$Fn24;
    public static final GenericProc cdddar = null;
    static final ModuleMethod cdddar$Fn56;
    public static final GenericProc cddddr = null;
    static final ModuleMethod cddddr$Fn58;
    public static final GenericProc cdddr = null;
    static final ModuleMethod cdddr$Fn26;
    public static final GenericProc cddr = null;
    static final ModuleMethod cddr$Fn10;
    public static final GenericProc cdr = null;
    static final ModuleMethod cdr$Fn2;
    public static final ModuleMethod cons;
    static final ModuleMethod lambda$Fn11;
    static final ModuleMethod lambda$Fn13;
    static final ModuleMethod lambda$Fn15;
    static final ModuleMethod lambda$Fn17;
    static final ModuleMethod lambda$Fn19;
    static final ModuleMethod lambda$Fn21;
    static final ModuleMethod lambda$Fn23;
    static final ModuleMethod lambda$Fn25;
    static final ModuleMethod lambda$Fn27;
    static final ModuleMethod lambda$Fn29;
    static final ModuleMethod lambda$Fn3;
    static final ModuleMethod lambda$Fn31;
    static final ModuleMethod lambda$Fn33;
    static final ModuleMethod lambda$Fn35;
    static final ModuleMethod lambda$Fn37;
    static final ModuleMethod lambda$Fn39;
    static final ModuleMethod lambda$Fn41;
    static final ModuleMethod lambda$Fn43;
    static final ModuleMethod lambda$Fn45;
    static final ModuleMethod lambda$Fn47;
    static final ModuleMethod lambda$Fn49;
    static final ModuleMethod lambda$Fn5;
    static final ModuleMethod lambda$Fn51;
    static final ModuleMethod lambda$Fn53;
    static final ModuleMethod lambda$Fn55;
    static final ModuleMethod lambda$Fn57;
    static final ModuleMethod lambda$Fn7;
    static final ModuleMethod lambda$Fn9;
    public static final ModuleMethod length;
    public static final ModuleMethod list$Mnref;
    public static final ModuleMethod list$Mntail;
    public static final ModuleMethod list$Qu;
    public static final ModuleMethod member;
    public static final ModuleMethod memq;
    public static final ModuleMethod memv;
    public static final ModuleMethod null$Qu;
    public static final ModuleMethod pair$Qu;
    public static final ModuleMethod reverse;
    public static final ModuleMethod reverse$Ex;
    public static final ModuleMethod set$Mncar$Ex;
    public static final ModuleMethod set$Mncdr$Ex;

    public C1245lists() {
        ModuleInfo.register(this);
    }

    public static Object assoc(Object obj, Object obj2) {
        return assoc(obj, obj2, Scheme.isEqual);
    }

    public static Object member(Object obj, Object obj2) {
        return member(obj, obj2, Scheme.isEqual);
    }

    public final void run(CallContext $ctx) {
        GenericProc genericProc;
        GenericProc genericProc2;
        GenericProc genericProc3;
        GenericProc genericProc4;
        GenericProc genericProc5;
        GenericProc genericProc6;
        GenericProc genericProc7;
        GenericProc genericProc8;
        GenericProc genericProc9;
        GenericProc genericProc10;
        GenericProc genericProc11;
        GenericProc genericProc12;
        GenericProc genericProc13;
        GenericProc genericProc14;
        GenericProc genericProc15;
        GenericProc genericProc16;
        GenericProc genericProc17;
        GenericProc genericProc18;
        GenericProc genericProc19;
        GenericProc genericProc20;
        GenericProc genericProc21;
        GenericProc genericProc22;
        GenericProc genericProc23;
        GenericProc genericProc24;
        GenericProc genericProc25;
        GenericProc genericProc26;
        GenericProc genericProc27;
        GenericProc genericProc28;
        GenericProc genericProc29;
        GenericProc genericProc30;
        Consumer consumer = $ctx.consumer;
        new GenericProc("car");
        car = genericProc;
        car.setProperties(new Object[]{Lit0, set$Mncar$Ex, car$Fn1});
        new GenericProc("cdr");
        cdr = genericProc2;
        cdr.setProperties(new Object[]{Lit0, set$Mncdr$Ex, cdr$Fn2});
        new GenericProc("caar");
        caar = genericProc3;
        caar.setProperties(new Object[]{Lit0, lambda$Fn3, caar$Fn4});
        new GenericProc("cadr");
        cadr = genericProc4;
        cadr.setProperties(new Object[]{Lit0, lambda$Fn5, cadr$Fn6});
        new GenericProc("cdar");
        cdar = genericProc5;
        cdar.setProperties(new Object[]{Lit0, lambda$Fn7, cdar$Fn8});
        new GenericProc("cddr");
        cddr = genericProc6;
        cddr.setProperties(new Object[]{Lit0, lambda$Fn9, cddr$Fn10});
        new GenericProc("caaar");
        caaar = genericProc7;
        caaar.setProperties(new Object[]{Lit0, lambda$Fn11, caaar$Fn12});
        new GenericProc("caadr");
        caadr = genericProc8;
        caadr.setProperties(new Object[]{Lit0, lambda$Fn13, caadr$Fn14});
        new GenericProc("cadar");
        cadar = genericProc9;
        cadar.setProperties(new Object[]{Lit0, lambda$Fn15, cadar$Fn16});
        new GenericProc("caddr");
        caddr = genericProc10;
        caddr.setProperties(new Object[]{Lit0, lambda$Fn17, caddr$Fn18});
        new GenericProc("cdaar");
        cdaar = genericProc11;
        cdaar.setProperties(new Object[]{Lit0, lambda$Fn19, cdaar$Fn20});
        new GenericProc("cdadr");
        cdadr = genericProc12;
        cdadr.setProperties(new Object[]{Lit0, lambda$Fn21, cdadr$Fn22});
        new GenericProc("cddar");
        cddar = genericProc13;
        cddar.setProperties(new Object[]{Lit0, lambda$Fn23, cddar$Fn24});
        new GenericProc("cdddr");
        cdddr = genericProc14;
        cdddr.setProperties(new Object[]{Lit0, lambda$Fn25, cdddr$Fn26});
        new GenericProc("caaaar");
        caaaar = genericProc15;
        caaaar.setProperties(new Object[]{Lit0, lambda$Fn27, caaaar$Fn28});
        new GenericProc("caaadr");
        caaadr = genericProc16;
        caaadr.setProperties(new Object[]{Lit0, lambda$Fn29, caaadr$Fn30});
        new GenericProc("caadar");
        caadar = genericProc17;
        caadar.setProperties(new Object[]{Lit0, lambda$Fn31, caadar$Fn32});
        new GenericProc("caaddr");
        caaddr = genericProc18;
        caaddr.setProperties(new Object[]{Lit0, lambda$Fn33, caaddr$Fn34});
        new GenericProc("cadaar");
        cadaar = genericProc19;
        cadaar.setProperties(new Object[]{Lit0, lambda$Fn35, cadaar$Fn36});
        new GenericProc("cadadr");
        cadadr = genericProc20;
        cadadr.setProperties(new Object[]{Lit0, lambda$Fn37, cadadr$Fn38});
        new GenericProc("caddar");
        caddar = genericProc21;
        caddar.setProperties(new Object[]{Lit0, lambda$Fn39, caddar$Fn40});
        new GenericProc("cadddr");
        cadddr = genericProc22;
        cadddr.setProperties(new Object[]{Lit0, lambda$Fn41, cadddr$Fn42});
        new GenericProc("cdaaar");
        cdaaar = genericProc23;
        cdaaar.setProperties(new Object[]{Lit0, lambda$Fn43, cdaaar$Fn44});
        new GenericProc("cdaadr");
        cdaadr = genericProc24;
        cdaadr.setProperties(new Object[]{Lit0, lambda$Fn45, cdaadr$Fn46});
        new GenericProc("cdadar");
        cdadar = genericProc25;
        cdadar.setProperties(new Object[]{Lit0, lambda$Fn47, cdadar$Fn48});
        new GenericProc("cdaddr");
        cdaddr = genericProc26;
        cdaddr.setProperties(new Object[]{Lit0, lambda$Fn49, cdaddr$Fn50});
        new GenericProc("cddaar");
        cddaar = genericProc27;
        cddaar.setProperties(new Object[]{Lit0, lambda$Fn51, cddaar$Fn52});
        new GenericProc("cddadr");
        cddadr = genericProc28;
        cddadr.setProperties(new Object[]{Lit0, lambda$Fn53, cddadr$Fn54});
        new GenericProc("cdddar");
        cdddar = genericProc29;
        cdddar.setProperties(new Object[]{Lit0, lambda$Fn55, cdddar$Fn56});
        new GenericProc("cddddr");
        cddddr = genericProc30;
        cddddr.setProperties(new Object[]{Lit0, lambda$Fn57, cddddr$Fn58});
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
        C1245lists lists;
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
        new SimpleSymbol("assoc");
        Lit19 = (SimpleSymbol) simpleSymbol.readResolve();
        new SimpleSymbol("assv");
        Lit18 = (SimpleSymbol) simpleSymbol2.readResolve();
        new SimpleSymbol("assq");
        Lit17 = (SimpleSymbol) simpleSymbol3.readResolve();
        new SimpleSymbol("member");
        Lit16 = (SimpleSymbol) simpleSymbol4.readResolve();
        new SimpleSymbol("memv");
        Lit15 = (SimpleSymbol) simpleSymbol5.readResolve();
        new SimpleSymbol("memq");
        Lit14 = (SimpleSymbol) simpleSymbol6.readResolve();
        new SimpleSymbol("reverse!");
        Lit13 = (SimpleSymbol) simpleSymbol7.readResolve();
        new SimpleSymbol("list?");
        Lit12 = (SimpleSymbol) simpleSymbol8.readResolve();
        new SimpleSymbol("list-ref");
        Lit11 = (SimpleSymbol) simpleSymbol9.readResolve();
        new SimpleSymbol("list-tail");
        Lit10 = (SimpleSymbol) simpleSymbol10.readResolve();
        new SimpleSymbol("reverse");
        Lit9 = (SimpleSymbol) simpleSymbol11.readResolve();
        new SimpleSymbol("length");
        Lit8 = (SimpleSymbol) simpleSymbol12.readResolve();
        new SimpleSymbol("set-cdr!");
        Lit7 = (SimpleSymbol) simpleSymbol13.readResolve();
        new SimpleSymbol("set-car!");
        Lit6 = (SimpleSymbol) simpleSymbol14.readResolve();
        new SimpleSymbol("null?");
        Lit5 = (SimpleSymbol) simpleSymbol15.readResolve();
        new SimpleSymbol("cons");
        Lit4 = (SimpleSymbol) simpleSymbol16.readResolve();
        new SimpleSymbol("pair?");
        Lit3 = (SimpleSymbol) simpleSymbol17.readResolve();
        new SimpleSymbol("cdr");
        Lit2 = (SimpleSymbol) simpleSymbol18.readResolve();
        new SimpleSymbol("car");
        Lit1 = (SimpleSymbol) simpleSymbol19.readResolve();
        new C1245lists();
        $instance = lists;
        C1245lists lists2 = $instance;
        new ModuleMethod(lists2, 1, Lit3, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        pair$Qu = moduleMethod;
        new ModuleMethod(lists2, 2, Lit4, 8194);
        cons = moduleMethod2;
        new ModuleMethod(lists2, 3, Lit5, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        null$Qu = moduleMethod3;
        new ModuleMethod(lists2, 4, Lit6, 8194);
        set$Mncar$Ex = moduleMethod4;
        new ModuleMethod(lists2, 5, Lit7, 8194);
        set$Mncdr$Ex = moduleMethod5;
        new ModuleMethod(lists2, 6, "car", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ModuleMethod moduleMethod76 = moduleMethod6;
        moduleMethod76.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/lists.scm:31");
        car$Fn1 = moduleMethod76;
        new ModuleMethod(lists2, 7, "cdr", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ModuleMethod moduleMethod77 = moduleMethod7;
        moduleMethod77.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/lists.scm:36");
        cdr$Fn2 = moduleMethod77;
        new ModuleMethod(lists2, 8, (Object) null, 8194);
        lambda$Fn3 = moduleMethod8;
        new ModuleMethod(lists2, 9, "caar", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        caar$Fn4 = moduleMethod9;
        new ModuleMethod(lists2, 10, (Object) null, 8194);
        lambda$Fn5 = moduleMethod10;
        new ModuleMethod(lists2, 11, "cadr", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        cadr$Fn6 = moduleMethod11;
        new ModuleMethod(lists2, 12, (Object) null, 8194);
        lambda$Fn7 = moduleMethod12;
        new ModuleMethod(lists2, 13, "cdar", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        cdar$Fn8 = moduleMethod13;
        new ModuleMethod(lists2, 14, (Object) null, 8194);
        lambda$Fn9 = moduleMethod14;
        new ModuleMethod(lists2, 15, "cddr", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        cddr$Fn10 = moduleMethod15;
        new ModuleMethod(lists2, 16, (Object) null, 8194);
        lambda$Fn11 = moduleMethod16;
        new ModuleMethod(lists2, 17, "caaar", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        caaar$Fn12 = moduleMethod17;
        new ModuleMethod(lists2, 18, (Object) null, 8194);
        lambda$Fn13 = moduleMethod18;
        new ModuleMethod(lists2, 19, "caadr", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        caadr$Fn14 = moduleMethod19;
        new ModuleMethod(lists2, 20, (Object) null, 8194);
        lambda$Fn15 = moduleMethod20;
        new ModuleMethod(lists2, 21, "cadar", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        cadar$Fn16 = moduleMethod21;
        new ModuleMethod(lists2, 22, (Object) null, 8194);
        lambda$Fn17 = moduleMethod22;
        new ModuleMethod(lists2, 23, "caddr", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        caddr$Fn18 = moduleMethod23;
        new ModuleMethod(lists2, 24, (Object) null, 8194);
        lambda$Fn19 = moduleMethod24;
        new ModuleMethod(lists2, 25, "cdaar", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        cdaar$Fn20 = moduleMethod25;
        new ModuleMethod(lists2, 26, (Object) null, 8194);
        lambda$Fn21 = moduleMethod26;
        new ModuleMethod(lists2, 27, "cdadr", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        cdadr$Fn22 = moduleMethod27;
        new ModuleMethod(lists2, 28, (Object) null, 8194);
        lambda$Fn23 = moduleMethod28;
        new ModuleMethod(lists2, 29, "cddar", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        cddar$Fn24 = moduleMethod29;
        new ModuleMethod(lists2, 30, (Object) null, 8194);
        lambda$Fn25 = moduleMethod30;
        new ModuleMethod(lists2, 31, "cdddr", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        cdddr$Fn26 = moduleMethod31;
        new ModuleMethod(lists2, 32, (Object) null, 8194);
        lambda$Fn27 = moduleMethod32;
        new ModuleMethod(lists2, 33, "caaaar", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        caaaar$Fn28 = moduleMethod33;
        new ModuleMethod(lists2, 34, (Object) null, 8194);
        lambda$Fn29 = moduleMethod34;
        new ModuleMethod(lists2, 35, "caaadr", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        caaadr$Fn30 = moduleMethod35;
        new ModuleMethod(lists2, 36, (Object) null, 8194);
        lambda$Fn31 = moduleMethod36;
        new ModuleMethod(lists2, 37, "caadar", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        caadar$Fn32 = moduleMethod37;
        new ModuleMethod(lists2, 38, (Object) null, 8194);
        lambda$Fn33 = moduleMethod38;
        new ModuleMethod(lists2, 39, "caaddr", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        caaddr$Fn34 = moduleMethod39;
        new ModuleMethod(lists2, 40, (Object) null, 8194);
        lambda$Fn35 = moduleMethod40;
        new ModuleMethod(lists2, 41, "cadaar", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        cadaar$Fn36 = moduleMethod41;
        new ModuleMethod(lists2, 42, (Object) null, 8194);
        lambda$Fn37 = moduleMethod42;
        new ModuleMethod(lists2, 43, "cadadr", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        cadadr$Fn38 = moduleMethod43;
        new ModuleMethod(lists2, 44, (Object) null, 8194);
        lambda$Fn39 = moduleMethod44;
        new ModuleMethod(lists2, 45, "caddar", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        caddar$Fn40 = moduleMethod45;
        new ModuleMethod(lists2, 46, (Object) null, 8194);
        lambda$Fn41 = moduleMethod46;
        new ModuleMethod(lists2, 47, "cadddr", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        cadddr$Fn42 = moduleMethod47;
        new ModuleMethod(lists2, 48, (Object) null, 8194);
        lambda$Fn43 = moduleMethod48;
        new ModuleMethod(lists2, 49, "cdaaar", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        cdaaar$Fn44 = moduleMethod49;
        new ModuleMethod(lists2, 50, (Object) null, 8194);
        lambda$Fn45 = moduleMethod50;
        new ModuleMethod(lists2, 51, "cdaadr", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        cdaadr$Fn46 = moduleMethod51;
        new ModuleMethod(lists2, 52, (Object) null, 8194);
        lambda$Fn47 = moduleMethod52;
        new ModuleMethod(lists2, 53, "cdadar", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        cdadar$Fn48 = moduleMethod53;
        new ModuleMethod(lists2, 54, (Object) null, 8194);
        lambda$Fn49 = moduleMethod54;
        new ModuleMethod(lists2, 55, "cdaddr", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        cdaddr$Fn50 = moduleMethod55;
        new ModuleMethod(lists2, 56, (Object) null, 8194);
        lambda$Fn51 = moduleMethod56;
        new ModuleMethod(lists2, 57, "cddaar", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        cddaar$Fn52 = moduleMethod57;
        new ModuleMethod(lists2, 58, (Object) null, 8194);
        lambda$Fn53 = moduleMethod58;
        new ModuleMethod(lists2, 59, "cddadr", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        cddadr$Fn54 = moduleMethod59;
        new ModuleMethod(lists2, 60, (Object) null, 8194);
        lambda$Fn55 = moduleMethod60;
        new ModuleMethod(lists2, 61, "cdddar", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        cdddar$Fn56 = moduleMethod61;
        new ModuleMethod(lists2, 62, (Object) null, 8194);
        lambda$Fn57 = moduleMethod62;
        new ModuleMethod(lists2, 63, "cddddr", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        cddddr$Fn58 = moduleMethod63;
        new ModuleMethod(lists2, 64, Lit8, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        length = moduleMethod64;
        new ModuleMethod(lists2, 65, Lit9, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        reverse = moduleMethod65;
        new ModuleMethod(lists2, 66, Lit10, 8194);
        list$Mntail = moduleMethod66;
        new ModuleMethod(lists2, 67, Lit11, 8194);
        list$Mnref = moduleMethod67;
        new ModuleMethod(lists2, 68, Lit12, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        list$Qu = moduleMethod68;
        new ModuleMethod(lists2, 69, Lit13, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        reverse$Ex = moduleMethod69;
        new ModuleMethod(lists2, 70, Lit14, 8194);
        memq = moduleMethod70;
        new ModuleMethod(lists2, 71, Lit15, 8194);
        memv = moduleMethod71;
        new ModuleMethod(lists2, 72, Lit16, 12290);
        member = moduleMethod72;
        new ModuleMethod(lists2, 74, Lit17, 8194);
        assq = moduleMethod73;
        new ModuleMethod(lists2, 75, Lit18, 8194);
        assv = moduleMethod74;
        new ModuleMethod(lists2, 76, Lit19, 12290);
        assoc = moduleMethod75;
        $instance.run();
    }

    public static boolean isPair(Object x) {
        return x instanceof Pair;
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
            case 3:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 6:
                CallContext callContext3 = callContext2;
                Object obj3 = obj2;
                Object obj4 = obj3;
                if (!(obj3 instanceof Pair)) {
                    return -786431;
                }
                callContext3.value1 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 7:
                CallContext callContext4 = callContext2;
                Object obj5 = obj2;
                Object obj6 = obj5;
                if (!(obj5 instanceof Pair)) {
                    return -786431;
                }
                callContext4.value1 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
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
            case 13:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 15:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 17:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 19:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 21:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 23:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 25:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 27:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 29:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 31:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 33:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 35:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 37:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 39:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 41:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 43:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 45:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 47:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 49:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 51:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 53:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 55:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 57:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 59:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 61:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 63:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 64:
                CallContext callContext5 = callContext2;
                Object obj7 = obj2;
                Object obj8 = obj7;
                if (!(obj7 instanceof LList)) {
                    return -786431;
                }
                callContext5.value1 = obj8;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 65:
                CallContext callContext6 = callContext2;
                Object obj9 = obj2;
                Object obj10 = obj9;
                if (!(obj9 instanceof LList)) {
                    return -786431;
                }
                callContext6.value1 = obj10;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 68:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 69:
                CallContext callContext7 = callContext2;
                Object obj11 = obj2;
                Object obj12 = obj11;
                if (!(obj11 instanceof LList)) {
                    return -786431;
                }
                callContext7.value1 = obj12;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            default:
                return super.match1(moduleMethod2, obj2, callContext2);
        }
    }

    public static Pair cons(Object car2, Object cdr2) {
        Pair pair;
        new Pair(car2, cdr2);
        return pair;
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 2:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 4:
                CallContext callContext3 = callContext2;
                Object obj5 = obj3;
                Object obj6 = obj5;
                if (!(obj5 instanceof Pair)) {
                    return -786431;
                }
                callContext3.value1 = obj6;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 5:
                CallContext callContext4 = callContext2;
                Object obj7 = obj3;
                Object obj8 = obj7;
                if (!(obj7 instanceof Pair)) {
                    return -786431;
                }
                callContext4.value1 = obj8;
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
            case 10:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 12:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 14:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 16:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 18:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 20:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 22:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 24:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 26:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 28:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 30:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 32:
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
            case 36:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 38:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 40:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 42:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 44:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 46:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 48:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 50:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 52:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 54:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 56:
                callContext2.value1 = obj3;
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
            case 60:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 62:
                callContext2.value1 = obj3;
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
            case 67:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 70:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 71:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 72:
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
            case 75:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 76:
                callContext2.value1 = obj3;
                callContext2.value2 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            default:
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
        }
    }

    public static boolean isNull(Object x) {
        return x == LList.Empty;
    }

    public static void setCar$Ex(Pair p, Object x) {
        p.setCar(x);
    }

    public static void setCdr$Ex(Pair p, Object x) {
        p.setCdr(x);
    }

    static Object car(Pair x) {
        return x.getCar();
    }

    static Object cdr(Pair x) {
        return x.getCdr();
    }

    static Object caar(Object arg) {
        return ((Pair) ((Pair) arg).getCar()).getCar();
    }

    static void lambda1(Object arg, Object value) {
        Object apply3 = SlotSet.set$Mnfield$Ex.apply3(((Pair) arg).getCar(), Lit1, value);
    }

    static Object cadr(Object arg) {
        return ((Pair) ((Pair) arg).getCdr()).getCar();
    }

    static void lambda2(Object arg, Object value) {
        Object apply3 = SlotSet.set$Mnfield$Ex.apply3(((Pair) arg).getCdr(), Lit1, value);
    }

    static Object cdar(Object arg) {
        return ((Pair) ((Pair) arg).getCar()).getCdr();
    }

    static void lambda3(Object arg, Object value) {
        Object apply3 = SlotSet.set$Mnfield$Ex.apply3(((Pair) arg).getCar(), Lit2, value);
    }

    static Object cddr(Object arg) {
        return ((Pair) ((Pair) arg).getCdr()).getCdr();
    }

    static void lambda4(Object arg, Object value) {
        Object apply3 = SlotSet.set$Mnfield$Ex.apply3(((Pair) arg).getCdr(), Lit2, value);
    }

    static Object caaar(Object arg) {
        return ((Pair) ((Pair) ((Pair) arg).getCar()).getCar()).getCar();
    }

    static void lambda5(Object arg, Object value) {
        Object apply3 = SlotSet.set$Mnfield$Ex.apply3(((Pair) ((Pair) arg).getCar()).getCar(), Lit1, value);
    }

    static Object caadr(Object arg) {
        return ((Pair) ((Pair) ((Pair) arg).getCdr()).getCar()).getCar();
    }

    static void lambda6(Object arg, Object value) {
        Object apply3 = SlotSet.set$Mnfield$Ex.apply3(((Pair) ((Pair) arg).getCdr()).getCar(), Lit1, value);
    }

    static Object cadar(Object arg) {
        return ((Pair) ((Pair) ((Pair) arg).getCar()).getCdr()).getCar();
    }

    static void lambda7(Object arg, Object value) {
        Object apply3 = SlotSet.set$Mnfield$Ex.apply3(((Pair) ((Pair) arg).getCar()).getCdr(), Lit1, value);
    }

    static Object caddr(Object arg) {
        return ((Pair) ((Pair) ((Pair) arg).getCdr()).getCdr()).getCar();
    }

    static void lambda8(Object arg, Object value) {
        Object apply3 = SlotSet.set$Mnfield$Ex.apply3(((Pair) ((Pair) arg).getCdr()).getCdr(), Lit1, value);
    }

    static Object cdaar(Object arg) {
        return ((Pair) ((Pair) ((Pair) arg).getCar()).getCar()).getCdr();
    }

    static void lambda9(Object arg, Object value) {
        Object apply3 = SlotSet.set$Mnfield$Ex.apply3(((Pair) ((Pair) arg).getCar()).getCar(), Lit2, value);
    }

    static Object cdadr(Object arg) {
        return ((Pair) ((Pair) ((Pair) arg).getCdr()).getCar()).getCdr();
    }

    static void lambda10(Object arg, Object value) {
        Object apply3 = SlotSet.set$Mnfield$Ex.apply3(((Pair) ((Pair) arg).getCdr()).getCar(), Lit2, value);
    }

    static Object cddar(Object arg) {
        return ((Pair) ((Pair) ((Pair) arg).getCar()).getCdr()).getCdr();
    }

    static void lambda11(Object arg, Object value) {
        Object apply3 = SlotSet.set$Mnfield$Ex.apply3(((Pair) ((Pair) arg).getCar()).getCdr(), Lit2, value);
    }

    static Object cdddr(Object arg) {
        return ((Pair) ((Pair) ((Pair) arg).getCdr()).getCdr()).getCdr();
    }

    static void lambda12(Object arg, Object value) {
        Object apply3 = SlotSet.set$Mnfield$Ex.apply3(((Pair) ((Pair) arg).getCdr()).getCdr(), Lit2, value);
    }

    static Object caaaar(Object arg) {
        return ((Pair) ((Pair) ((Pair) ((Pair) arg).getCar()).getCar()).getCar()).getCar();
    }

    static void lambda13(Object arg, Object value) {
        Object apply3 = SlotSet.set$Mnfield$Ex.apply3(((Pair) ((Pair) ((Pair) arg).getCar()).getCar()).getCar(), Lit1, value);
    }

    static Object caaadr(Object arg) {
        return ((Pair) ((Pair) ((Pair) ((Pair) arg).getCdr()).getCar()).getCar()).getCar();
    }

    static void lambda14(Object arg, Object value) {
        Object apply3 = SlotSet.set$Mnfield$Ex.apply3(((Pair) ((Pair) ((Pair) arg).getCdr()).getCar()).getCar(), Lit1, value);
    }

    static Object caadar(Object arg) {
        return ((Pair) ((Pair) ((Pair) ((Pair) arg).getCar()).getCdr()).getCar()).getCar();
    }

    static void lambda15(Object arg, Object value) {
        Object apply3 = SlotSet.set$Mnfield$Ex.apply3(((Pair) ((Pair) ((Pair) arg).getCar()).getCdr()).getCar(), Lit1, value);
    }

    static Object caaddr(Object arg) {
        return ((Pair) ((Pair) ((Pair) ((Pair) arg).getCdr()).getCdr()).getCar()).getCar();
    }

    static void lambda16(Object arg, Object value) {
        Object apply3 = SlotSet.set$Mnfield$Ex.apply3(((Pair) ((Pair) ((Pair) arg).getCdr()).getCdr()).getCar(), Lit1, value);
    }

    static Object cadaar(Object arg) {
        return ((Pair) ((Pair) ((Pair) ((Pair) arg).getCar()).getCar()).getCdr()).getCar();
    }

    static void lambda17(Object arg, Object value) {
        Object apply3 = SlotSet.set$Mnfield$Ex.apply3(((Pair) ((Pair) ((Pair) arg).getCar()).getCar()).getCdr(), Lit1, value);
    }

    static Object cadadr(Object arg) {
        return ((Pair) ((Pair) ((Pair) ((Pair) arg).getCdr()).getCar()).getCdr()).getCar();
    }

    static void lambda18(Object arg, Object value) {
        Object apply3 = SlotSet.set$Mnfield$Ex.apply3(((Pair) ((Pair) ((Pair) arg).getCdr()).getCar()).getCdr(), Lit1, value);
    }

    static Object caddar(Object arg) {
        return ((Pair) ((Pair) ((Pair) ((Pair) arg).getCar()).getCdr()).getCdr()).getCar();
    }

    static void lambda19(Object arg, Object value) {
        Object apply3 = SlotSet.set$Mnfield$Ex.apply3(((Pair) ((Pair) ((Pair) arg).getCar()).getCdr()).getCdr(), Lit1, value);
    }

    static Object cadddr(Object arg) {
        return ((Pair) ((Pair) ((Pair) ((Pair) arg).getCdr()).getCdr()).getCdr()).getCar();
    }

    static void lambda20(Object arg, Object value) {
        Object apply3 = SlotSet.set$Mnfield$Ex.apply3(((Pair) ((Pair) ((Pair) arg).getCdr()).getCdr()).getCdr(), Lit1, value);
    }

    static Object cdaaar(Object arg) {
        return ((Pair) ((Pair) ((Pair) ((Pair) arg).getCar()).getCar()).getCar()).getCdr();
    }

    static void lambda21(Object arg, Object value) {
        Object apply3 = SlotSet.set$Mnfield$Ex.apply3(((Pair) ((Pair) ((Pair) arg).getCar()).getCar()).getCar(), Lit2, value);
    }

    static Object cdaadr(Object arg) {
        return ((Pair) ((Pair) ((Pair) ((Pair) arg).getCdr()).getCar()).getCar()).getCdr();
    }

    static void lambda22(Object arg, Object value) {
        Object apply3 = SlotSet.set$Mnfield$Ex.apply3(((Pair) ((Pair) ((Pair) arg).getCdr()).getCar()).getCar(), Lit2, value);
    }

    static Object cdadar(Object arg) {
        return ((Pair) ((Pair) ((Pair) ((Pair) arg).getCar()).getCdr()).getCar()).getCdr();
    }

    static void lambda23(Object arg, Object value) {
        Object apply3 = SlotSet.set$Mnfield$Ex.apply3(((Pair) ((Pair) ((Pair) arg).getCar()).getCdr()).getCar(), Lit2, value);
    }

    static Object cdaddr(Object arg) {
        return ((Pair) ((Pair) ((Pair) ((Pair) arg).getCdr()).getCdr()).getCar()).getCdr();
    }

    static void lambda24(Object arg, Object value) {
        Object apply3 = SlotSet.set$Mnfield$Ex.apply3(((Pair) ((Pair) ((Pair) arg).getCdr()).getCdr()).getCar(), Lit2, value);
    }

    static Object cddaar(Object arg) {
        return ((Pair) ((Pair) ((Pair) ((Pair) arg).getCar()).getCar()).getCdr()).getCdr();
    }

    static void lambda25(Object arg, Object value) {
        Object apply3 = SlotSet.set$Mnfield$Ex.apply3(((Pair) ((Pair) ((Pair) arg).getCar()).getCar()).getCdr(), Lit2, value);
    }

    static Object cddadr(Object arg) {
        return ((Pair) ((Pair) ((Pair) ((Pair) arg).getCdr()).getCar()).getCdr()).getCdr();
    }

    static void lambda26(Object arg, Object value) {
        Object apply3 = SlotSet.set$Mnfield$Ex.apply3(((Pair) ((Pair) ((Pair) arg).getCdr()).getCar()).getCdr(), Lit2, value);
    }

    static Object cdddar(Object arg) {
        return ((Pair) ((Pair) ((Pair) ((Pair) arg).getCar()).getCdr()).getCdr()).getCdr();
    }

    static void lambda27(Object arg, Object value) {
        Object apply3 = SlotSet.set$Mnfield$Ex.apply3(((Pair) ((Pair) ((Pair) arg).getCar()).getCdr()).getCdr(), Lit2, value);
    }

    static Object cddddr(Object arg) {
        return ((Pair) ((Pair) ((Pair) ((Pair) arg).getCdr()).getCdr()).getCdr()).getCdr();
    }

    static void lambda28(Object arg, Object value) {
        Object apply3 = SlotSet.set$Mnfield$Ex.apply3(((Pair) ((Pair) ((Pair) arg).getCdr()).getCdr()).getCdr(), Lit2, value);
    }

    public static int length(LList list) {
        return LList.length(list);
    }

    public static LList reverse(LList list) {
        Throwable th;
        Object obj = list;
        LList lList = LList.Empty;
        while (true) {
            LList list2 = lList;
            Object obj2 = obj;
            if (isNull(obj2)) {
                return list2;
            }
            Object obj3 = obj2;
            Object obj4 = obj3;
            try {
                Pair pair = (Pair) obj3;
                obj = cdr.apply1(pair);
                lList = cons(car.apply1(pair), list2);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "pair", -2, obj4);
                throw th2;
            }
        }
    }

    public static Object listTail(Object list, int count) {
        return LList.listTail(list, count);
    }

    public static Object listRef(Object list, int index) {
        return car.apply1(listTail(list, index));
    }

    public static boolean isList(Object obj) {
        return LList.listLength(obj, false) >= 0;
    }

    public static LList reverse$Ex(LList list) {
        return LList.reverseInPlace(list);
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        switch (moduleMethod2.selector) {
            case 1:
                return isPair(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 3:
                return isNull(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 6:
                try {
                    return car((Pair) obj2);
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th6 = th5;
                    new WrongType(classCastException, "car", 1, obj2);
                    throw th6;
                }
            case 7:
                try {
                    return cdr((Pair) obj2);
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th7 = th4;
                    new WrongType(classCastException2, "cdr", 1, obj2);
                    throw th7;
                }
            case 9:
                return caar(obj2);
            case 11:
                return cadr(obj2);
            case 13:
                return cdar(obj2);
            case 15:
                return cddr(obj2);
            case 17:
                return caaar(obj2);
            case 19:
                return caadr(obj2);
            case 21:
                return cadar(obj2);
            case 23:
                return caddr(obj2);
            case 25:
                return cdaar(obj2);
            case 27:
                return cdadr(obj2);
            case 29:
                return cddar(obj2);
            case 31:
                return cdddr(obj2);
            case 33:
                return caaaar(obj2);
            case 35:
                return caaadr(obj2);
            case 37:
                return caadar(obj2);
            case 39:
                return caaddr(obj2);
            case 41:
                return cadaar(obj2);
            case 43:
                return cadadr(obj2);
            case 45:
                return caddar(obj2);
            case 47:
                return cadddr(obj2);
            case 49:
                return cdaaar(obj2);
            case 51:
                return cdaadr(obj2);
            case 53:
                return cdadar(obj2);
            case 55:
                return cdaddr(obj2);
            case 57:
                return cddaar(obj2);
            case 59:
                return cddadr(obj2);
            case 61:
                return cdddar(obj2);
            case 63:
                return cddddr(obj2);
            case 64:
                try {
                    return Integer.valueOf(length((LList) obj2));
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th8 = th3;
                    new WrongType(classCastException3, "length", 1, obj2);
                    throw th8;
                }
            case 65:
                try {
                    return reverse((LList) obj2);
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th9 = th2;
                    new WrongType(classCastException4, "reverse", 1, obj2);
                    throw th9;
                }
            case 68:
                return isList(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 69:
                try {
                    return reverse$Ex((LList) obj2);
                } catch (ClassCastException e5) {
                    ClassCastException classCastException5 = e5;
                    Throwable th10 = th;
                    new WrongType(classCastException5, "reverse!", 1, obj2);
                    throw th10;
                }
            default:
                return super.apply1(moduleMethod2, obj2);
        }
    }

    public static Object memq(Object obj, Object list) {
        Object obj2;
        Throwable th;
        Object obj3 = obj;
        Object obj4 = list;
        while (true) {
            Object lst = obj4;
            boolean x = lst instanceof Pair;
            if (x) {
                Object obj5 = lst;
                Object obj6 = obj5;
                try {
                    Pair p = (Pair) obj5;
                    if (obj3 == p.getCar()) {
                        obj2 = lst;
                        break;
                    }
                    obj4 = p.getCdr();
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th2 = th;
                    new WrongType(classCastException, "p", -2, obj6);
                    throw th2;
                }
            } else {
                obj2 = x ? Boolean.TRUE : Boolean.FALSE;
            }
        }
        return obj2;
    }

    public static Object memv(Object obj, Object list) {
        Object obj2;
        Throwable th;
        Object obj3 = obj;
        Object obj4 = list;
        while (true) {
            Object lst = obj4;
            boolean x = lst instanceof Pair;
            if (x) {
                Object obj5 = lst;
                Object obj6 = obj5;
                try {
                    Pair p = (Pair) obj5;
                    if (Scheme.isEqv.apply2(obj3, p.getCar()) != Boolean.FALSE) {
                        obj2 = lst;
                        break;
                    }
                    obj4 = p.getCdr();
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th2 = th;
                    new WrongType(classCastException, "p", -2, obj6);
                    throw th2;
                }
            } else {
                obj2 = x ? Boolean.TRUE : Boolean.FALSE;
            }
        }
        return obj2;
    }

    public static Object member(Object obj, Object list, Procedure procedure) {
        Object obj2;
        Throwable th;
        Object obj3 = obj;
        Procedure test = procedure;
        Object obj4 = list;
        while (true) {
            Object lst = obj4;
            boolean x = lst instanceof Pair;
            if (x) {
                Object obj5 = lst;
                Object obj6 = obj5;
                try {
                    Pair p = (Pair) obj5;
                    if (test.apply2(obj3, p.getCar()) != Boolean.FALSE) {
                        obj2 = lst;
                        break;
                    }
                    obj4 = p.getCdr();
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th2 = th;
                    new WrongType(classCastException, "p", -2, obj6);
                    throw th2;
                }
            } else {
                obj2 = x ? Boolean.TRUE : Boolean.FALSE;
            }
        }
        return obj2;
    }

    public int match3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj4 = obj;
        Object obj5 = obj2;
        Object obj6 = obj3;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 72:
                callContext2.value1 = obj4;
                callContext2.value2 = obj5;
                CallContext callContext3 = callContext2;
                Object obj7 = obj6;
                Object obj8 = obj7;
                if (!(obj7 instanceof Procedure)) {
                    return -786429;
                }
                callContext3.value3 = obj8;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            case 76:
                callContext2.value1 = obj4;
                callContext2.value2 = obj5;
                CallContext callContext4 = callContext2;
                Object obj9 = obj6;
                Object obj10 = obj9;
                if (!(obj9 instanceof Procedure)) {
                    return -786429;
                }
                callContext4.value3 = obj10;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 3;
                return 0;
            default:
                return super.match3(moduleMethod2, obj4, obj5, obj6, callContext2);
        }
    }

    public static Object assq(Object obj, Object list) {
        Object x;
        Throwable th;
        Object x2 = obj;
        Object obj2 = list;
        while (true) {
            Object list2 = obj2;
            if (list2 == LList.Empty) {
                x = Boolean.FALSE;
                break;
            }
            Object apply1 = car.apply1(list2);
            Object obj3 = apply1;
            try {
                Object pair = (Pair) apply1;
                if (pair.getCar() == x2) {
                    x = pair;
                    break;
                }
                obj2 = cdr.apply1(list2);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "pair", -2, obj3);
                throw th2;
            }
        }
        return x;
    }

    public static Object assv(Object obj, Object list) {
        Object x;
        Throwable th;
        Object x2 = obj;
        Object obj2 = list;
        while (true) {
            Object list2 = obj2;
            if (list2 == LList.Empty) {
                x = Boolean.FALSE;
                break;
            }
            Object apply1 = car.apply1(list2);
            Object obj3 = apply1;
            try {
                Object pair = (Pair) apply1;
                if (Scheme.isEqv.apply2(pair.getCar(), x2) != Boolean.FALSE) {
                    x = pair;
                    break;
                }
                obj2 = cdr.apply1(list2);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "pair", -2, obj3);
                throw th2;
            }
        }
        return x;
    }

    public static Object assoc(Object obj, Object list, Procedure procedure) {
        Object x;
        Throwable th;
        Object x2 = obj;
        Procedure test = procedure;
        Object obj2 = list;
        while (true) {
            Object list2 = obj2;
            if (list2 == LList.Empty) {
                x = Boolean.FALSE;
                break;
            }
            Object apply1 = car.apply1(list2);
            Object obj3 = apply1;
            try {
                Object pair = (Pair) apply1;
                if (test.apply2(pair.getCar(), x2) != Boolean.FALSE) {
                    x = pair;
                    break;
                }
                obj2 = cdr.apply1(list2);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "pair", -2, obj3);
                throw th2;
            }
        }
        return x;
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
            case 2:
                return cons(obj3, obj4);
            case 4:
                try {
                    setCar$Ex((Pair) obj3, obj4);
                    return Values.empty;
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th5 = th4;
                    new WrongType(classCastException, "set-car!", 1, obj3);
                    throw th5;
                }
            case 5:
                try {
                    setCdr$Ex((Pair) obj3, obj4);
                    return Values.empty;
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th6 = th3;
                    new WrongType(classCastException2, "set-cdr!", 1, obj3);
                    throw th6;
                }
            case 8:
                lambda1(obj3, obj4);
                return Values.empty;
            case 10:
                lambda2(obj3, obj4);
                return Values.empty;
            case 12:
                lambda3(obj3, obj4);
                return Values.empty;
            case 14:
                lambda4(obj3, obj4);
                return Values.empty;
            case 16:
                lambda5(obj3, obj4);
                return Values.empty;
            case 18:
                lambda6(obj3, obj4);
                return Values.empty;
            case 20:
                lambda7(obj3, obj4);
                return Values.empty;
            case 22:
                lambda8(obj3, obj4);
                return Values.empty;
            case 24:
                lambda9(obj3, obj4);
                return Values.empty;
            case 26:
                lambda10(obj3, obj4);
                return Values.empty;
            case 28:
                lambda11(obj3, obj4);
                return Values.empty;
            case 30:
                lambda12(obj3, obj4);
                return Values.empty;
            case 32:
                lambda13(obj3, obj4);
                return Values.empty;
            case 34:
                lambda14(obj3, obj4);
                return Values.empty;
            case 36:
                lambda15(obj3, obj4);
                return Values.empty;
            case 38:
                lambda16(obj3, obj4);
                return Values.empty;
            case 40:
                lambda17(obj3, obj4);
                return Values.empty;
            case 42:
                lambda18(obj3, obj4);
                return Values.empty;
            case 44:
                lambda19(obj3, obj4);
                return Values.empty;
            case 46:
                lambda20(obj3, obj4);
                return Values.empty;
            case 48:
                lambda21(obj3, obj4);
                return Values.empty;
            case 50:
                lambda22(obj3, obj4);
                return Values.empty;
            case 52:
                lambda23(obj3, obj4);
                return Values.empty;
            case 54:
                lambda24(obj3, obj4);
                return Values.empty;
            case 56:
                lambda25(obj3, obj4);
                return Values.empty;
            case 58:
                lambda26(obj3, obj4);
                return Values.empty;
            case 60:
                lambda27(obj3, obj4);
                return Values.empty;
            case 62:
                lambda28(obj3, obj4);
                return Values.empty;
            case 66:
                try {
                    return listTail(obj3, ((Number) obj4).intValue());
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th7 = th2;
                    new WrongType(classCastException3, "list-tail", 2, obj4);
                    throw th7;
                }
            case 67:
                try {
                    return listRef(obj3, ((Number) obj4).intValue());
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th8 = th;
                    new WrongType(classCastException4, "list-ref", 2, obj4);
                    throw th8;
                }
            case 70:
                return memq(obj3, obj4);
            case 71:
                return memv(obj3, obj4);
            case 72:
                return member(obj3, obj4);
            case 74:
                return assq(obj3, obj4);
            case 75:
                return assv(obj3, obj4);
            case 76:
                return assoc(obj3, obj4);
            default:
                return super.apply2(moduleMethod2, obj3, obj4);
        }
    }

    public Object apply3(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3) {
        Throwable th;
        Throwable th2;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj4 = obj;
        Object obj5 = obj2;
        Object obj6 = obj3;
        switch (moduleMethod2.selector) {
            case 72:
                try {
                    return member(obj4, obj5, (Procedure) obj6);
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th3 = th2;
                    new WrongType(classCastException, "member", 3, obj6);
                    throw th3;
                }
            case 76:
                try {
                    return assoc(obj4, obj5, (Procedure) obj6);
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th4 = th;
                    new WrongType(classCastException2, "assoc", 3, obj6);
                    throw th4;
                }
            default:
                return super.apply3(moduleMethod2, obj4, obj5, obj6);
        }
    }
}

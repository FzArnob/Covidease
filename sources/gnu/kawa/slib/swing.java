package gnu.kawa.slib;

import android.support.p000v4.app.FragmentTransaction;
import gnu.expr.Keyword;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.GetNamedPart;
import gnu.kawa.models.Paintable;
import gnu.kawa.models.WithTransform;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.kawa.swingviews.SwingDisplay;
import gnu.kawa.swingviews.SwingPaintable;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.ThreadLocation;
import gnu.mapping.UnboundLocationException;
import gnu.mapping.WrongType;
import gnu.math.Complex;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Shape;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import kawa.lib.numbers;
import kawa.standard.Scheme;

/* compiled from: swing.scm */
public class swing extends ModuleBody {
    public static final swing $instance;
    public static final Location Button = StaticFieldLocation.make("gnu.kawa.slib.gui", "Button");
    public static final Location Column = StaticFieldLocation.make("gnu.kawa.slib.gui", "Column");
    public static final Location Image = StaticFieldLocation.make("gnu.kawa.slib.gui", "Image");
    public static final Location Label = StaticFieldLocation.make("gnu.kawa.slib.gui", "Label");
    static final SimpleSymbol Lit0;
    static final Keyword Lit1 = Keyword.make("label");
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
    static final Keyword Lit2 = Keyword.make("image");
    static final SimpleSymbol Lit20;
    static final SimpleSymbol Lit21;
    static final SimpleSymbol Lit22;
    static final SimpleSymbol Lit23;
    static final SimpleSymbol Lit24;
    static final SimpleSymbol Lit25;
    static final SimpleSymbol Lit26;
    static final Keyword Lit3 = Keyword.make("default");
    static final Keyword Lit4 = Keyword.make("oncommand");
    static final Keyword Lit5 = Keyword.make("disabled");
    static final Keyword Lit6 = Keyword.make("accesskey");
    static final Keyword Lit7 = Keyword.make("w");
    static final Keyword Lit8 = Keyword.make("h");
    static final SimpleSymbol Lit9;
    public static final Location Row = StaticFieldLocation.make("gnu.kawa.slib.gui", "Row");
    public static final Location Text = StaticFieldLocation.make("gnu.kawa.slib.gui", "Text");
    public static final Location Window = StaticFieldLocation.make("gnu.kawa.slib.gui", "Window");
    public static final Location button = StaticFieldLocation.make("gnu.kawa.slib.gui", "button");
    public static final Color color$Mnred = null;
    public static final ModuleMethod composite$Mnsrc;
    public static final ModuleMethod composite$Mnsrc$Mnover;
    public static final ModuleMethod draw;
    public static final ModuleMethod fill;
    public static final Location image$Mnheight = StaticFieldLocation.make("gnu.kawa.slib.gui", "image$Mnheight");
    public static final Location image$Mnread = StaticFieldLocation.make("gnu.kawa.slib.gui", "image$Mnread");
    public static final Location image$Mnwidth = StaticFieldLocation.make("gnu.kawa.slib.gui", "image$Mnwidth");
    static final Location loc$$Lsgnu$Dtkawa$Dtmodels$DtDrawShape$Gr = ThreadLocation.getInstance(Lit10, (Object) null);
    static final Location loc$$Lsgnu$Dtkawa$Dtmodels$DtFillShape$Gr = ThreadLocation.getInstance(Lit9, (Object) null);
    static final Location loc$$Lsgnu$Dtkawa$Dtmodels$DtWithPaint$Gr = ThreadLocation.getInstance(Lit11, (Object) null);
    static final Location loc$gnu$Dtkawa$Dtmodels$DtWithComposite = ThreadLocation.getInstance(Lit12, (Object) null);
    public static final ModuleMethod make$Mnaction$Mnlistener;
    public static final ModuleMethod menu;
    public static final ModuleMethod menubar;
    public static final ModuleMethod menuitem;
    public static final ModuleMethod polygon;
    public static final ModuleMethod rotation;
    public static final Location run$Mnapplication = StaticFieldLocation.make("gnu.kawa.slib.gui", "run$Mnapplication");
    public static final ModuleMethod scroll;
    public static final Location set$Mncontent = StaticFieldLocation.make("gnu.kawa.slib.gui", "set$Mncontent");
    public static final ModuleMethod with$Mncomposite;
    public static final ModuleMethod with$Mnpaint;
    public static final ModuleMethod with$Mntransform;

    public swing() {
        ModuleInfo.register(this);
    }

    public static Composite compositeSrc() {
        return compositeSrc(1.0f);
    }

    public static Composite compositeSrcOver() {
        return compositeSrcOver(1.0f);
    }

    public final void run(CallContext $ctx) {
        Consumer consumer = $ctx.consumer;
        color$Mnred = Color.red;
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
        swing swing;
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
        new SimpleSymbol("scroll");
        Lit26 = (SimpleSymbol) simpleSymbol.readResolve();
        new SimpleSymbol("polygon");
        Lit25 = (SimpleSymbol) simpleSymbol2.readResolve();
        new SimpleSymbol("menuitem");
        Lit24 = (SimpleSymbol) simpleSymbol3.readResolve();
        new SimpleSymbol("menu");
        Lit23 = (SimpleSymbol) simpleSymbol4.readResolve();
        new SimpleSymbol("menubar");
        Lit22 = (SimpleSymbol) simpleSymbol5.readResolve();
        new SimpleSymbol("with-transform");
        Lit21 = (SimpleSymbol) simpleSymbol6.readResolve();
        new SimpleSymbol("rotation");
        Lit20 = (SimpleSymbol) simpleSymbol7.readResolve();
        new SimpleSymbol("composite-src");
        Lit19 = (SimpleSymbol) simpleSymbol8.readResolve();
        new SimpleSymbol("composite-src-over");
        Lit18 = (SimpleSymbol) simpleSymbol9.readResolve();
        new SimpleSymbol("with-composite");
        Lit17 = (SimpleSymbol) simpleSymbol10.readResolve();
        new SimpleSymbol("with-paint");
        Lit16 = (SimpleSymbol) simpleSymbol11.readResolve();
        new SimpleSymbol("draw");
        Lit15 = (SimpleSymbol) simpleSymbol12.readResolve();
        new SimpleSymbol("fill");
        Lit14 = (SimpleSymbol) simpleSymbol13.readResolve();
        new SimpleSymbol("make-action-listener");
        Lit13 = (SimpleSymbol) simpleSymbol14.readResolve();
        new SimpleSymbol("gnu.kawa.models.WithComposite");
        Lit12 = (SimpleSymbol) simpleSymbol15.readResolve();
        new SimpleSymbol("<gnu.kawa.models.WithPaint>");
        Lit11 = (SimpleSymbol) simpleSymbol16.readResolve();
        new SimpleSymbol("<gnu.kawa.models.DrawShape>");
        Lit10 = (SimpleSymbol) simpleSymbol17.readResolve();
        new SimpleSymbol("<gnu.kawa.models.FillShape>");
        Lit9 = (SimpleSymbol) simpleSymbol18.readResolve();
        new SimpleSymbol("make");
        Lit0 = (SimpleSymbol) simpleSymbol19.readResolve();
        new swing();
        $instance = swing;
        swing swing2 = $instance;
        new ModuleMethod(swing2, 1, Lit13, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        make$Mnaction$Mnlistener = moduleMethod;
        new ModuleMethod(swing2, 2, Lit14, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fill = moduleMethod2;
        new ModuleMethod(swing2, 3, Lit15, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        draw = moduleMethod3;
        new ModuleMethod(swing2, 4, Lit16, 8194);
        with$Mnpaint = moduleMethod4;
        new ModuleMethod(swing2, 5, Lit17, -4096);
        with$Mncomposite = moduleMethod5;
        new ModuleMethod(swing2, 6, Lit18, 4096);
        composite$Mnsrc$Mnover = moduleMethod6;
        new ModuleMethod(swing2, 8, Lit19, 4096);
        composite$Mnsrc = moduleMethod7;
        new ModuleMethod(swing2, 10, Lit20, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        rotation = moduleMethod8;
        new ModuleMethod(swing2, 11, Lit21, 8194);
        with$Mntransform = moduleMethod9;
        new ModuleMethod(swing2, 12, Lit22, -4096);
        menubar = moduleMethod10;
        new ModuleMethod(swing2, 13, Lit23, -4096);
        menu = moduleMethod11;
        new ModuleMethod(swing2, 14, Lit24, -4096);
        menuitem = moduleMethod12;
        new ModuleMethod(swing2, 15, Lit25, -4095);
        polygon = moduleMethod13;
        new ModuleMethod(swing2, 16, Lit26, -4095);
        scroll = moduleMethod14;
        $instance.run();
    }

    public static ActionListener makeActionListener(Object proc) {
        return SwingDisplay.makeActionListener(proc);
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
                CallContext callContext3 = callContext2;
                Object obj3 = obj2;
                Object obj4 = obj3;
                if (!(obj3 instanceof Shape)) {
                    return -786431;
                }
                callContext3.value1 = obj4;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 3:
                CallContext callContext4 = callContext2;
                Object obj5 = obj2;
                Object obj6 = obj5;
                if (!(obj5 instanceof Shape)) {
                    return -786431;
                }
                callContext4.value1 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 6:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 8:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 10:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            default:
                return super.match1(moduleMethod2, obj2, callContext2);
        }
    }

    public static Paintable fill(Shape shape) {
        try {
            return (Paintable) Invoke.make.apply2(loc$$Lsgnu$Dtkawa$Dtmodels$DtFillShape$Gr.get(), shape);
        } catch (UnboundLocationException e) {
            UnboundLocationException unboundLocationException = e;
            UnboundLocationException unboundLocationException2 = unboundLocationException;
            unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/swing.scm", 19, 9);
            throw unboundLocationException2;
        }
    }

    public static Paintable draw(Shape shape) {
        try {
            return (Paintable) Invoke.make.apply2(loc$$Lsgnu$Dtkawa$Dtmodels$DtDrawShape$Gr.get(), shape);
        } catch (UnboundLocationException e) {
            UnboundLocationException unboundLocationException = e;
            UnboundLocationException unboundLocationException2 = unboundLocationException;
            unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/swing.scm", 22, 9);
            throw unboundLocationException2;
        }
    }

    public static Object withPaint(Color paint, Paintable pic) {
        try {
            return Invoke.make.apply3(loc$$Lsgnu$Dtkawa$Dtmodels$DtWithPaint$Gr.get(), pic, paint);
        } catch (UnboundLocationException e) {
            UnboundLocationException unboundLocationException = e;
            UnboundLocationException unboundLocationException2 = unboundLocationException;
            unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/swing.scm", 26, 10);
            throw unboundLocationException2;
        }
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 4:
                CallContext callContext3 = callContext2;
                Object obj5 = obj3;
                Object obj6 = obj5;
                if (!(obj5 instanceof Color)) {
                    return -786431;
                }
                callContext3.value1 = obj6;
                CallContext callContext4 = callContext2;
                Object obj7 = obj4;
                Object obj8 = obj7;
                if (!(obj7 instanceof Paintable)) {
                    return -786430;
                }
                callContext4.value2 = obj8;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 11:
                CallContext callContext5 = callContext2;
                Object obj9 = obj3;
                Object obj10 = obj9;
                if (!(obj9 instanceof AffineTransform)) {
                    return -786431;
                }
                callContext5.value1 = obj10;
                CallContext callContext6 = callContext2;
                Object obj11 = obj4;
                Object obj12 = obj11;
                if (!(obj11 instanceof Paintable)) {
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

    public static Object withComposite(Object... objArr) {
        Object[] arguments = objArr;
        try {
            return Scheme.applyToArgs.apply2(GetNamedPart.getNamedPart.apply2(loc$gnu$Dtkawa$Dtmodels$DtWithComposite.get(), Lit0), arguments);
        } catch (UnboundLocationException e) {
            UnboundLocationException unboundLocationException = e;
            UnboundLocationException unboundLocationException2 = unboundLocationException;
            unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/swing.scm", 29, 4);
            throw unboundLocationException2;
        }
    }

    public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object[] objArr2 = objArr;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 5:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
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
            case 14:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 15:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 16:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            default:
                return super.matchN(moduleMethod2, objArr2, callContext2);
        }
    }

    public static Composite compositeSrcOver(float alpha) {
        return AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
    }

    public int match0(ModuleMethod moduleMethod, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 6:
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 0;
                return 0;
            case 8:
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 0;
                return 0;
            default:
                return super.match0(moduleMethod2, callContext2);
        }
    }

    public static Composite compositeSrc(float alpha) {
        return AlphaComposite.getInstance(AlphaComposite.SRC, alpha);
    }

    public Object apply0(ModuleMethod moduleMethod) {
        ModuleMethod moduleMethod2 = moduleMethod;
        switch (moduleMethod2.selector) {
            case 6:
                return compositeSrcOver();
            case 8:
                return compositeSrc();
            default:
                return super.apply0(moduleMethod2);
        }
    }

    public static AffineTransform rotation(double theta) {
        return AffineTransform.getRotateInstance(theta);
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
                return makeActionListener(obj2);
            case 2:
                try {
                    return fill((Shape) obj2);
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th6 = th5;
                    new WrongType(classCastException, "fill", 1, obj2);
                    throw th6;
                }
            case 3:
                try {
                    return draw((Shape) obj2);
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th7 = th4;
                    new WrongType(classCastException2, "draw", 1, obj2);
                    throw th7;
                }
            case 6:
                try {
                    return compositeSrcOver(((Number) obj2).floatValue());
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th8 = th3;
                    new WrongType(classCastException3, "composite-src-over", 1, obj2);
                    throw th8;
                }
            case 8:
                try {
                    return compositeSrc(((Number) obj2).floatValue());
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th9 = th2;
                    new WrongType(classCastException4, "composite-src", 1, obj2);
                    throw th9;
                }
            case 10:
                try {
                    return rotation(((Number) obj2).doubleValue());
                } catch (ClassCastException e5) {
                    ClassCastException classCastException5 = e5;
                    Throwable th10 = th;
                    new WrongType(classCastException5, "rotation", 1, obj2);
                    throw th10;
                }
            default:
                return super.apply1(moduleMethod2, obj2);
        }
    }

    public static WithTransform withTransform(AffineTransform transform, Paintable pic) {
        WithTransform withTransform;
        new WithTransform(pic, transform);
        return withTransform;
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
            case 4:
                try {
                    try {
                        return withPaint((Color) obj3, (Paintable) obj4);
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th5 = th4;
                        new WrongType(classCastException, "with-paint", 2, obj4);
                        throw th5;
                    }
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th6 = th3;
                    new WrongType(classCastException2, "with-paint", 1, obj3);
                    throw th6;
                }
            case 11:
                try {
                    try {
                        return withTransform((AffineTransform) obj3, (Paintable) obj4);
                    } catch (ClassCastException e3) {
                        ClassCastException classCastException3 = e3;
                        Throwable th7 = th2;
                        new WrongType(classCastException3, "with-transform", 2, obj4);
                        throw th7;
                    }
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th8 = th;
                    new WrongType(classCastException4, "with-transform", 1, obj3);
                    throw th8;
                }
            default:
                return super.apply2(moduleMethod2, obj3, obj4);
        }
    }

    public static JMenuBar menubar(Object... objArr) {
        JMenuBar jMenuBar;
        Object[] args = objArr;
        new JMenuBar();
        int num$Mnargs = args.length;
        JMenuBar menubar2 = jMenuBar;
        for (int i = 0; i < num$Mnargs; i++) {
            JMenu add = menubar2.add((JMenu) args[i]);
        }
        return menubar2;
    }

    public static JMenu menu(Object... objArr) {
        JMenu jMenu;
        Object[] args = objArr;
        new JMenu();
        int num$Mnargs = args.length;
        JMenu menu2 = jMenu;
        int i = 0;
        while (i < num$Mnargs) {
            Object arg = args[i];
            boolean x = arg == Lit1;
            if (!x ? !x : i + 1 >= num$Mnargs) {
                JMenuItem add = menu2.add((JMenuItem) arg);
                i++;
            } else {
                JMenu jMenu2 = menu2;
                Object obj = args[i + 1];
                jMenu2.setText(obj == null ? null : obj.toString());
                i += 2;
            }
        }
        return menu2;
    }

    public static JMenuItem menuitem$V(Object[] objArr) {
        JMenuItem jMenuItem;
        Object[] argsArray = objArr;
        Object searchForKeyword = Keyword.searchForKeyword(argsArray, 0, Lit1, (Object) null);
        String label = searchForKeyword == null ? null : searchForKeyword.toString();
        Object searchForKeyword2 = Keyword.searchForKeyword(argsArray, 0, Lit2, (Object) null);
        Object searchForKeyword3 = Keyword.searchForKeyword(argsArray, 0, Lit3, (Object) null);
        Object oncommand = Keyword.searchForKeyword(argsArray, 0, Lit4, (Object) null);
        Object disabled = Keyword.searchForKeyword(argsArray, 0, Lit5, Boolean.FALSE);
        Object searchForKeyword4 = Keyword.searchForKeyword(argsArray, 0, Lit6, (Object) null);
        new JMenuItem();
        JMenuItem menuitem2 = jMenuItem;
        if (disabled != Boolean.FALSE) {
            menuitem2.setEnabled(false);
        }
        if (label != null) {
            menuitem2.setText(label);
        }
        if (oncommand != null) {
            menuitem2.addActionListener(makeActionListener(oncommand));
        }
        return menuitem2;
    }

    public static Object polygon(Complex complex, Object... objArr) {
        GeneralPath generalPath;
        Throwable th;
        Complex initial = complex;
        Object[] more$Mnpoints = objArr;
        new GeneralPath();
        int n$Mnpoints = more$Mnpoints.length;
        GeneralPath path = generalPath;
        path.moveTo(numbers.realPart(initial).doubleValue(), numbers.imagPart(initial).doubleValue());
        int i = 0;
        while (i < n$Mnpoints) {
            Object obj = more$Mnpoints[i];
            Object obj2 = obj;
            try {
                Complex pt = (Complex) obj;
                path.lineTo(numbers.realPart(pt).doubleValue(), numbers.imagPart(pt).doubleValue());
                i++;
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "pt", -2, obj2);
                throw th2;
            }
        }
        path.closePath();
        return path;
    }

    public static JScrollPane scroll$V(Object obj, Object[] objArr) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Object contents = obj;
        Object[] argsArray = objArr;
        Object w = Keyword.searchForKeyword(argsArray, 0, Lit7, Boolean.FALSE);
        Object h = Keyword.searchForKeyword(argsArray, 0, Lit8, Boolean.FALSE);
        if (contents instanceof Paintable) {
            Object obj2 = r12;
            Object obj3 = contents;
            Object obj4 = obj3;
            try {
                SwingPaintable swingPaintable = new SwingPaintable((Paintable) obj3);
                contents = obj2;
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th5 = th4;
                new WrongType(classCastException, "gnu.kawa.swingviews.SwingPaintable.<init>(gnu.kawa.models.Paintable)", 1, obj4);
                throw th5;
            }
        }
        JScrollPane jScrollPane = r12;
        Object obj5 = contents;
        Object obj6 = obj5;
        try {
            JScrollPane jScrollPane2 = new JScrollPane((Component) obj5);
            JScrollPane scr = jScrollPane;
            JScrollPane jScrollPane3 = scr;
            Dimension dimension = r12;
            Object obj7 = w;
            Object obj8 = obj7;
            try {
                int intValue = ((Number) obj7).intValue();
                Object obj9 = h;
                Object obj10 = obj9;
                try {
                    Dimension dimension2 = new Dimension(intValue, ((Number) obj9).intValue());
                    jScrollPane3.setPreferredSize(dimension);
                    return scr;
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th6 = th3;
                    new WrongType(classCastException2, "java.awt.Dimension.<init>(int,int)", 2, obj10);
                    throw th6;
                }
            } catch (ClassCastException e3) {
                ClassCastException classCastException3 = e3;
                Throwable th7 = th2;
                new WrongType(classCastException3, "java.awt.Dimension.<init>(int,int)", 1, obj8);
                throw th7;
            }
        } catch (ClassCastException e4) {
            ClassCastException classCastException4 = e4;
            Throwable th8 = th;
            new WrongType(classCastException4, "javax.swing.JScrollPane.<init>(java.awt.Component)", 1, obj6);
            throw th8;
        }
    }

    public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
        Throwable th;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object[] objArr2 = objArr;
        switch (moduleMethod2.selector) {
            case 5:
                return withComposite(objArr2);
            case 12:
                return menubar(objArr2);
            case 13:
                return menu(objArr2);
            case 14:
                return menuitem$V(objArr2);
            case 15:
                Object obj = objArr2[0];
                Object obj2 = obj;
                try {
                    Complex complex = (Complex) obj;
                    int length = objArr2.length - 1;
                    Object[] objArr3 = new Object[length];
                    while (true) {
                        length--;
                        if (length < 0) {
                            return polygon(complex, objArr3);
                        }
                        Object[] objArr4 = objArr3;
                        objArr3 = objArr4;
                        objArr4[length] = objArr2[length + 1];
                    }
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th2 = th;
                    new WrongType(classCastException, "polygon", 1, obj2);
                    throw th2;
                }
            case 16:
                Object obj3 = objArr2[0];
                int length2 = objArr2.length - 1;
                Object[] objArr5 = new Object[length2];
                while (true) {
                    length2--;
                    if (length2 < 0) {
                        return scroll$V(obj3, objArr5);
                    }
                    Object[] objArr6 = objArr5;
                    objArr5 = objArr6;
                    objArr6[length2] = objArr2[length2 + 1];
                }
            default:
                return super.applyN(moduleMethod2, objArr2);
        }
    }
}

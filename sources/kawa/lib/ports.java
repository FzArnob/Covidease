package kawa.lib;

import android.support.p000v4.app.FragmentTransaction;
import gnu.bytecode.ClassType;
import gnu.expr.GenericProc;
import gnu.expr.Keyword;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.kawa.lispexpr.LispReader;
import gnu.lists.Consumer;
import gnu.lists.EofClass;
import gnu.lists.FString;
import gnu.mapping.CallContext;
import gnu.mapping.CharArrayInPort;
import gnu.mapping.CharArrayOutPort;
import gnu.mapping.InPort;
import gnu.mapping.LocationProc;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.TtyInPort;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.text.Char;
import gnu.text.LineBufferedReader;
import gnu.text.Path;
import gnu.text.SyntaxException;
import java.io.Writer;
import kawa.standard.Scheme;
import kawa.standard.char_ready_p;
import kawa.standard.read_line;

/* compiled from: ports.scm */
public class ports extends ModuleBody {
    public static final ports $instance;
    static final SimpleSymbol Lit0;
    static final ClassType Lit1 = ClassType.make("gnu.mapping.InPort");
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
    static final SimpleSymbol Lit29;
    static final ClassType Lit3 = ClassType.make("gnu.mapping.OutPort");
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
    static final Keyword Lit5 = Keyword.make("setter");
    static final IntNum Lit6 = IntNum.make(1);
    static final Char Lit7 = Char.make(10);
    static final Char Lit8 = Char.make(32);
    static final SimpleSymbol Lit9;
    public static final ModuleMethod call$Mnwith$Mninput$Mnfile;
    public static final ModuleMethod call$Mnwith$Mninput$Mnstring;
    public static final ModuleMethod call$Mnwith$Mnoutput$Mnfile;
    public static final ModuleMethod call$Mnwith$Mnoutput$Mnstring;
    public static final ModuleMethod char$Mnready$Qu;
    public static final ModuleMethod close$Mninput$Mnport;
    public static final ModuleMethod close$Mnoutput$Mnport;
    public static final LocationProc current$Mnerror$Mnport = null;
    public static final LocationProc current$Mninput$Mnport = null;
    public static final LocationProc current$Mnoutput$Mnport = null;
    public static final ModuleMethod default$Mnprompter;
    public static final ModuleMethod display;
    public static final ModuleMethod eof$Mnobject$Qu;
    public static final ModuleMethod force$Mnoutput;
    public static final ModuleMethod get$Mnoutput$Mnstring;
    public static final ModuleMethod input$Mnport$Mncolumn$Mnnumber;
    public static final GenericProc input$Mnport$Mnline$Mnnumber = null;
    static final ModuleMethod input$Mnport$Mnline$Mnnumber$Fn5;
    public static final GenericProc input$Mnport$Mnprompter = null;
    static final ModuleMethod input$Mnport$Mnprompter$Fn6;
    public static final ModuleMethod input$Mnport$Mnread$Mnstate;
    public static final ModuleMethod input$Mnport$Qu;
    static final ModuleMethod lambda$Fn1;
    static final ModuleMethod lambda$Fn2;
    static final ModuleMethod lambda$Fn3;
    public static final ModuleMethod newline;
    public static final ModuleMethod open$Mninput$Mnfile;
    public static final ModuleMethod open$Mninput$Mnstring;
    public static final ModuleMethod open$Mnoutput$Mnfile;
    public static final ModuleMethod open$Mnoutput$Mnstring;
    public static final ModuleMethod output$Mnport$Qu;
    public static final ModuleMethod port$Mncolumn;
    public static final GenericProc port$Mnline = null;
    static final ModuleMethod port$Mnline$Fn4;
    public static final ModuleMethod read;
    public static final ModuleMethod read$Mnline;
    public static final ModuleMethod set$Mninput$Mnport$Mnline$Mnnumber$Ex;
    public static final ModuleMethod set$Mninput$Mnport$Mnprompter$Ex;
    public static final ModuleMethod set$Mnport$Mnline$Ex;
    public static final ModuleMethod transcript$Mnoff;
    public static final ModuleMethod transcript$Mnon;
    public static final ModuleMethod with$Mninput$Mnfrom$Mnfile;
    public static final ModuleMethod with$Mnoutput$Mnto$Mnfile;
    public static final ModuleMethod write;
    public static final ModuleMethod write$Mnchar;

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
        ports ports;
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
        new SimpleSymbol("transcript-off");
        Lit45 = (SimpleSymbol) simpleSymbol.readResolve();
        new SimpleSymbol("transcript-on");
        Lit44 = (SimpleSymbol) simpleSymbol2.readResolve();
        new SimpleSymbol("read-line");
        Lit43 = (SimpleSymbol) simpleSymbol3.readResolve();
        new SimpleSymbol("read");
        Lit42 = (SimpleSymbol) simpleSymbol4.readResolve();
        new SimpleSymbol("close-output-port");
        Lit41 = (SimpleSymbol) simpleSymbol5.readResolve();
        new SimpleSymbol("close-input-port");
        Lit40 = (SimpleSymbol) simpleSymbol6.readResolve();
        new SimpleSymbol("input-port-prompter");
        Lit39 = (SimpleSymbol) simpleSymbol7.readResolve();
        new SimpleSymbol("set-input-port-prompter!");
        Lit38 = (SimpleSymbol) simpleSymbol8.readResolve();
        new SimpleSymbol("default-prompter");
        Lit37 = (SimpleSymbol) simpleSymbol9.readResolve();
        new SimpleSymbol("input-port-column-number");
        Lit36 = (SimpleSymbol) simpleSymbol10.readResolve();
        new SimpleSymbol("port-column");
        Lit35 = (SimpleSymbol) simpleSymbol11.readResolve();
        new SimpleSymbol("input-port-line-number");
        Lit34 = (SimpleSymbol) simpleSymbol12.readResolve();
        new SimpleSymbol("set-input-port-line-number!");
        Lit33 = (SimpleSymbol) simpleSymbol13.readResolve();
        new SimpleSymbol("port-line");
        Lit32 = (SimpleSymbol) simpleSymbol14.readResolve();
        new SimpleSymbol("set-port-line!");
        Lit31 = (SimpleSymbol) simpleSymbol15.readResolve();
        new SimpleSymbol("input-port-read-state");
        Lit30 = (SimpleSymbol) simpleSymbol16.readResolve();
        new SimpleSymbol("display");
        Lit29 = (SimpleSymbol) simpleSymbol17.readResolve();
        new SimpleSymbol("write");
        Lit28 = (SimpleSymbol) simpleSymbol18.readResolve();
        new SimpleSymbol("char-ready?");
        Lit27 = (SimpleSymbol) simpleSymbol19.readResolve();
        new SimpleSymbol("eof-object?");
        Lit26 = (SimpleSymbol) simpleSymbol20.readResolve();
        new SimpleSymbol("newline");
        Lit25 = (SimpleSymbol) simpleSymbol21.readResolve();
        new SimpleSymbol("force-output");
        Lit24 = (SimpleSymbol) simpleSymbol22.readResolve();
        new SimpleSymbol("call-with-output-string");
        Lit23 = (SimpleSymbol) simpleSymbol23.readResolve();
        new SimpleSymbol("call-with-input-string");
        Lit22 = (SimpleSymbol) simpleSymbol24.readResolve();
        new SimpleSymbol("get-output-string");
        Lit21 = (SimpleSymbol) simpleSymbol25.readResolve();
        new SimpleSymbol("open-output-string");
        Lit20 = (SimpleSymbol) simpleSymbol26.readResolve();
        new SimpleSymbol("open-input-string");
        Lit19 = (SimpleSymbol) simpleSymbol27.readResolve();
        new SimpleSymbol("write-char");
        Lit18 = (SimpleSymbol) simpleSymbol28.readResolve();
        new SimpleSymbol("output-port?");
        Lit17 = (SimpleSymbol) simpleSymbol29.readResolve();
        new SimpleSymbol("input-port?");
        Lit16 = (SimpleSymbol) simpleSymbol30.readResolve();
        new SimpleSymbol("with-output-to-file");
        Lit15 = (SimpleSymbol) simpleSymbol31.readResolve();
        new SimpleSymbol("with-input-from-file");
        Lit14 = (SimpleSymbol) simpleSymbol32.readResolve();
        new SimpleSymbol("call-with-output-file");
        Lit13 = (SimpleSymbol) simpleSymbol33.readResolve();
        new SimpleSymbol("call-with-input-file");
        Lit12 = (SimpleSymbol) simpleSymbol34.readResolve();
        new SimpleSymbol("open-output-file");
        Lit11 = (SimpleSymbol) simpleSymbol35.readResolve();
        new SimpleSymbol("open-input-file");
        Lit10 = (SimpleSymbol) simpleSymbol36.readResolve();
        new SimpleSymbol("trim");
        Lit9 = (SimpleSymbol) simpleSymbol37.readResolve();
        new SimpleSymbol("current-error-port");
        Lit4 = (SimpleSymbol) simpleSymbol38.readResolve();
        new SimpleSymbol("current-output-port");
        Lit2 = (SimpleSymbol) simpleSymbol39.readResolve();
        new SimpleSymbol("current-input-port");
        Lit0 = (SimpleSymbol) simpleSymbol40.readResolve();
        new ports();
        $instance = ports;
        ports ports2 = $instance;
        new ModuleMethod(ports2, 1, Lit10, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        open$Mninput$Mnfile = moduleMethod;
        new ModuleMethod(ports2, 2, Lit11, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        open$Mnoutput$Mnfile = moduleMethod2;
        new ModuleMethod(ports2, 3, Lit12, 8194);
        call$Mnwith$Mninput$Mnfile = moduleMethod3;
        new ModuleMethod(ports2, 4, Lit13, 8194);
        call$Mnwith$Mnoutput$Mnfile = moduleMethod4;
        new ModuleMethod(ports2, 5, Lit14, 8194);
        with$Mninput$Mnfrom$Mnfile = moduleMethod5;
        new ModuleMethod(ports2, 6, Lit15, 8194);
        with$Mnoutput$Mnto$Mnfile = moduleMethod6;
        new ModuleMethod(ports2, 7, Lit16, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        input$Mnport$Qu = moduleMethod7;
        new ModuleMethod(ports2, 8, Lit17, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        output$Mnport$Qu = moduleMethod8;
        new ModuleMethod(ports2, 9, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        lambda$Fn1 = moduleMethod9;
        new ModuleMethod(ports2, 10, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        lambda$Fn2 = moduleMethod10;
        new ModuleMethod(ports2, 11, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        lambda$Fn3 = moduleMethod11;
        new ModuleMethod(ports2, 12, Lit18, 8193);
        write$Mnchar = moduleMethod12;
        new ModuleMethod(ports2, 14, Lit19, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        open$Mninput$Mnstring = moduleMethod13;
        new ModuleMethod(ports2, 15, Lit20, 0);
        open$Mnoutput$Mnstring = moduleMethod14;
        new ModuleMethod(ports2, 16, Lit21, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        get$Mnoutput$Mnstring = moduleMethod15;
        new ModuleMethod(ports2, 17, Lit22, 8194);
        call$Mnwith$Mninput$Mnstring = moduleMethod16;
        new ModuleMethod(ports2, 18, Lit23, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        call$Mnwith$Mnoutput$Mnstring = moduleMethod17;
        new ModuleMethod(ports2, 19, Lit24, 4096);
        force$Mnoutput = moduleMethod18;
        new ModuleMethod(ports2, 21, Lit25, 4096);
        newline = moduleMethod19;
        new ModuleMethod(ports2, 23, Lit26, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        eof$Mnobject$Qu = moduleMethod20;
        new ModuleMethod(ports2, 24, Lit27, 4096);
        char$Mnready$Qu = moduleMethod21;
        new ModuleMethod(ports2, 26, Lit28, 8193);
        write = moduleMethod22;
        new ModuleMethod(ports2, 28, Lit29, 8193);
        display = moduleMethod23;
        new ModuleMethod(ports2, 30, Lit30, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        input$Mnport$Mnread$Mnstate = moduleMethod24;
        new ModuleMethod(ports2, 31, Lit31, 8194);
        set$Mnport$Mnline$Ex = moduleMethod25;
        new ModuleMethod(ports2, 32, Lit32, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        port$Mnline$Fn4 = moduleMethod26;
        new ModuleMethod(ports2, 33, Lit33, 8194);
        set$Mninput$Mnport$Mnline$Mnnumber$Ex = moduleMethod27;
        new ModuleMethod(ports2, 34, Lit34, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        input$Mnport$Mnline$Mnnumber$Fn5 = moduleMethod28;
        new ModuleMethod(ports2, 35, Lit35, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        port$Mncolumn = moduleMethod29;
        new ModuleMethod(ports2, 36, Lit36, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        input$Mnport$Mncolumn$Mnnumber = moduleMethod30;
        new ModuleMethod(ports2, 37, Lit37, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        default$Mnprompter = moduleMethod31;
        new ModuleMethod(ports2, 38, Lit38, 8194);
        set$Mninput$Mnport$Mnprompter$Ex = moduleMethod32;
        new ModuleMethod(ports2, 39, Lit39, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        input$Mnport$Mnprompter$Fn6 = moduleMethod33;
        new ModuleMethod(ports2, 40, Lit40, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        close$Mninput$Mnport = moduleMethod34;
        new ModuleMethod(ports2, 41, Lit41, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        close$Mnoutput$Mnport = moduleMethod35;
        new ModuleMethod(ports2, 42, Lit42, 4096);
        read = moduleMethod36;
        new ModuleMethod(ports2, 44, Lit43, 8192);
        read$Mnline = moduleMethod37;
        new ModuleMethod(ports2, 47, Lit44, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transcript$Mnon = moduleMethod38;
        new ModuleMethod(ports2, 48, Lit45, 0);
        transcript$Mnoff = moduleMethod39;
        $instance.run();
    }

    public ports() {
        ModuleInfo.register(this);
    }

    public static void display(Object obj) {
        display(obj, current$Mnoutput$Mnport.apply0());
    }

    public static void forceOutput() {
        forceOutput(current$Mnoutput$Mnport.apply0());
    }

    public static boolean isCharReady() {
        return isCharReady(current$Mninput$Mnport.apply0());
    }

    public static void newline() {
        newline(current$Mnoutput$Mnport.apply0());
    }

    public static Object read() {
        return read((InPort) current$Mninput$Mnport.apply0());
    }

    public static Object readLine() {
        return readLine((LineBufferedReader) current$Mninput$Mnport.apply0(), Lit9);
    }

    public static Object readLine(LineBufferedReader lineBufferedReader) {
        return readLine(lineBufferedReader, Lit9);
    }

    public static void write(Object obj) {
        write(obj, current$Mnoutput$Mnport.apply0());
    }

    public static void writeChar(Object obj) {
        writeChar(obj, OutPort.outDefault());
    }

    public final void run(CallContext $ctx) {
        GenericProc genericProc;
        GenericProc genericProc2;
        GenericProc genericProc3;
        Consumer consumer = $ctx.consumer;
        current$Mninput$Mnport = LocationProc.makeNamed(Lit0, InPort.inLocation);
        current$Mninput$Mnport.pushConverter(lambda$Fn1);
        current$Mnoutput$Mnport = LocationProc.makeNamed(Lit2, OutPort.outLocation);
        current$Mnoutput$Mnport.pushConverter(lambda$Fn2);
        current$Mnerror$Mnport = LocationProc.makeNamed(Lit4, OutPort.errLocation);
        current$Mnerror$Mnport.pushConverter(lambda$Fn3);
        new GenericProc("port-line");
        port$Mnline = genericProc;
        GenericProc genericProc4 = port$Mnline;
        ModuleMethod moduleMethod = port$Mnline$Fn4;
        genericProc4.setProperties(new Object[]{Lit5, set$Mnport$Mnline$Ex, port$Mnline$Fn4});
        new GenericProc("input-port-line-number");
        input$Mnport$Mnline$Mnnumber = genericProc2;
        GenericProc genericProc5 = input$Mnport$Mnline$Mnnumber;
        Procedure port$Mnline2 = input$Mnport$Mnline$Mnnumber$Fn5;
        genericProc5.setProperties(new Object[]{Lit5, set$Mninput$Mnport$Mnline$Mnnumber$Ex, input$Mnport$Mnline$Mnnumber$Fn5});
        new GenericProc("input-port-prompter");
        input$Mnport$Mnprompter = genericProc3;
        GenericProc genericProc6 = input$Mnport$Mnprompter;
        Procedure input$Mnport$Mnline$Mnnumber2 = input$Mnport$Mnprompter$Fn6;
        genericProc6.setProperties(new Object[]{Lit5, set$Mninput$Mnport$Mnprompter$Ex, input$Mnport$Mnprompter$Fn6});
    }

    public static InPort openInputFile(Path name) {
        return InPort.openFile(name);
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
                if (Path.coerceToPathOrNull(obj3) == null) {
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
                if (Path.coerceToPathOrNull(obj5) == null) {
                    return -786431;
                }
                callContext4.value1 = obj6;
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
            case 14:
                CallContext callContext5 = callContext2;
                Object obj7 = obj2;
                Object obj8 = obj7;
                if (!(obj7 instanceof CharSequence)) {
                    return -786431;
                }
                callContext5.value1 = obj8;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 16:
                CallContext callContext6 = callContext2;
                Object obj9 = obj2;
                Object obj10 = obj9;
                if (!(obj9 instanceof CharArrayOutPort)) {
                    return -786431;
                }
                callContext6.value1 = obj10;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 18:
                CallContext callContext7 = callContext2;
                Object obj11 = obj2;
                Object obj12 = obj11;
                if (!(obj11 instanceof Procedure)) {
                    return -786431;
                }
                callContext7.value1 = obj12;
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
            case 24:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 26:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 28:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 30:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 32:
                CallContext callContext8 = callContext2;
                Object obj13 = obj2;
                Object obj14 = obj13;
                if (!(obj13 instanceof LineBufferedReader)) {
                    return -786431;
                }
                callContext8.value1 = obj14;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 34:
                CallContext callContext9 = callContext2;
                Object obj15 = obj2;
                Object obj16 = obj15;
                if (!(obj15 instanceof LineBufferedReader)) {
                    return -786431;
                }
                callContext9.value1 = obj16;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 35:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 36:
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
                CallContext callContext10 = callContext2;
                Object obj17 = obj2;
                Object obj18 = obj17;
                if (!(obj17 instanceof TtyInPort)) {
                    return -786431;
                }
                callContext10.value1 = obj18;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 40:
                CallContext callContext11 = callContext2;
                Object obj19 = obj2;
                Object obj20 = obj19;
                if (!(obj19 instanceof InPort)) {
                    return -786431;
                }
                callContext11.value1 = obj20;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 41:
                CallContext callContext12 = callContext2;
                Object obj21 = obj2;
                Object obj22 = obj21;
                if (!(obj21 instanceof OutPort)) {
                    return -786431;
                }
                callContext12.value1 = obj22;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 42:
                CallContext callContext13 = callContext2;
                Object obj23 = obj2;
                Object obj24 = obj23;
                if (!(obj23 instanceof InPort)) {
                    return -786431;
                }
                callContext13.value1 = obj24;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 44:
                CallContext callContext14 = callContext2;
                Object obj25 = obj2;
                Object obj26 = obj25;
                if (!(obj25 instanceof LineBufferedReader)) {
                    return -786431;
                }
                callContext14.value1 = obj26;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 47:
                callContext2.value1 = obj2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            default:
                return super.match1(moduleMethod2, obj2, callContext2);
        }
    }

    public static OutPort openOutputFile(Path name) {
        return OutPort.openFile(name);
    }

    /* JADX INFO: finally extract failed */
    public static Object callWithInputFile(Path path, Procedure proc) {
        InPort port = openInputFile(path);
        try {
            Object apply1 = proc.apply1(port);
            Object closeInputPort = closeInputPort(port);
            return apply1;
        } catch (Throwable th) {
            Throwable th2 = th;
            Object closeInputPort2 = closeInputPort(port);
            throw th2;
        }
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 3:
                CallContext callContext3 = callContext2;
                Object obj5 = obj3;
                Object obj6 = obj5;
                if (Path.coerceToPathOrNull(obj5) == null) {
                    return -786431;
                }
                callContext3.value1 = obj6;
                CallContext callContext4 = callContext2;
                Object obj7 = obj4;
                Object obj8 = obj7;
                if (!(obj7 instanceof Procedure)) {
                    return -786430;
                }
                callContext4.value2 = obj8;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 4:
                CallContext callContext5 = callContext2;
                Object obj9 = obj3;
                Object obj10 = obj9;
                if (Path.coerceToPathOrNull(obj9) == null) {
                    return -786431;
                }
                callContext5.value1 = obj10;
                CallContext callContext6 = callContext2;
                Object obj11 = obj4;
                Object obj12 = obj11;
                if (!(obj11 instanceof Procedure)) {
                    return -786430;
                }
                callContext6.value2 = obj12;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 5:
                CallContext callContext7 = callContext2;
                Object obj13 = obj3;
                Object obj14 = obj13;
                if (Path.coerceToPathOrNull(obj13) == null) {
                    return -786431;
                }
                callContext7.value1 = obj14;
                CallContext callContext8 = callContext2;
                Object obj15 = obj4;
                Object obj16 = obj15;
                if (!(obj15 instanceof Procedure)) {
                    return -786430;
                }
                callContext8.value2 = obj16;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 6:
                CallContext callContext9 = callContext2;
                Object obj17 = obj3;
                Object obj18 = obj17;
                if (Path.coerceToPathOrNull(obj17) == null) {
                    return -786431;
                }
                callContext9.value1 = obj18;
                CallContext callContext10 = callContext2;
                Object obj19 = obj4;
                Object obj20 = obj19;
                if (!(obj19 instanceof Procedure)) {
                    return -786430;
                }
                callContext10.value2 = obj20;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 12:
                callContext2.value1 = obj3;
                CallContext callContext11 = callContext2;
                Object obj21 = obj4;
                Object obj22 = obj21;
                if (!(obj21 instanceof OutPort)) {
                    return -786430;
                }
                callContext11.value2 = obj22;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 17:
                CallContext callContext12 = callContext2;
                Object obj23 = obj3;
                Object obj24 = obj23;
                if (!(obj23 instanceof CharSequence)) {
                    return -786431;
                }
                callContext12.value1 = obj24;
                CallContext callContext13 = callContext2;
                Object obj25 = obj4;
                Object obj26 = obj25;
                if (!(obj25 instanceof Procedure)) {
                    return -786430;
                }
                callContext13.value2 = obj26;
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
            case 31:
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
            case 38:
                CallContext callContext14 = callContext2;
                Object obj27 = obj3;
                Object obj28 = obj27;
                if (!(obj27 instanceof TtyInPort)) {
                    return -786431;
                }
                callContext14.value1 = obj28;
                CallContext callContext15 = callContext2;
                Object obj29 = obj4;
                Object obj30 = obj29;
                if (!(obj29 instanceof Procedure)) {
                    return -786430;
                }
                callContext15.value2 = obj30;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            case 44:
                CallContext callContext16 = callContext2;
                Object obj31 = obj3;
                Object obj32 = obj31;
                if (!(obj31 instanceof LineBufferedReader)) {
                    return -786431;
                }
                callContext16.value1 = obj32;
                CallContext callContext17 = callContext2;
                Object obj33 = obj4;
                Object obj34 = obj33;
                if (!(obj33 instanceof Symbol)) {
                    return -786430;
                }
                callContext17.value2 = obj34;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 2;
                return 0;
            default:
                return super.match2(moduleMethod2, obj3, obj4, callContext2);
        }
    }

    /* JADX INFO: finally extract failed */
    public static Object callWithOutputFile(Path path, Procedure proc) {
        OutPort port = openOutputFile(path);
        try {
            Object apply1 = proc.apply1(port);
            Object closeOutputPort = closeOutputPort(port);
            return apply1;
        } catch (Throwable th) {
            Throwable th2 = th;
            Object closeOutputPort2 = closeOutputPort(port);
            throw th2;
        }
    }

    /* JADX INFO: finally extract failed */
    public static Object withInputFromFile(Path pathname, Procedure procedure) {
        Procedure proc = procedure;
        InPort openFile = InPort.openFile(pathname);
        InPort save = InPort.inDefault();
        InPort port = openFile;
        try {
            InPort.setInDefault(port);
            Object apply0 = proc.apply0();
            InPort.setInDefault(save);
            port.close();
            return apply0;
        } catch (Throwable th) {
            Throwable th2 = th;
            InPort.setInDefault(save);
            port.close();
            throw th2;
        }
    }

    /* JADX INFO: finally extract failed */
    public static Object withOutputToFile(Path path, Procedure procedure) {
        Procedure proc = procedure;
        OutPort port = OutPort.openFile(path);
        OutPort save = OutPort.outDefault();
        try {
            OutPort.setOutDefault(port);
            Object apply0 = proc.apply0();
            OutPort.setOutDefault(save);
            port.close();
            return apply0;
        } catch (Throwable th) {
            Throwable th2 = th;
            OutPort.setOutDefault(save);
            port.close();
            throw th2;
        }
    }

    public static boolean isInputPort(Object x) {
        return x instanceof InPort;
    }

    public static boolean isOutputPort(Object x) {
        return x instanceof OutPort;
    }

    static Object lambda1(Object obj) {
        Object arg = obj;
        try {
            return (InPort) arg;
        } catch (ClassCastException e) {
            WrongType wt = WrongType.make(e, (Procedure) current$Mninput$Mnport, 1, arg);
            wt.expectedType = Lit1;
            throw wt;
        }
    }

    static Object lambda2(Object obj) {
        Object arg = obj;
        try {
            return (OutPort) arg;
        } catch (ClassCastException e) {
            WrongType wt = WrongType.make(e, (Procedure) current$Mnoutput$Mnport, 1, arg);
            wt.expectedType = Lit3;
            throw wt;
        }
    }

    static Object lambda3(Object obj) {
        Object arg = obj;
        try {
            return (OutPort) arg;
        } catch (ClassCastException e) {
            WrongType wt = WrongType.make(e, (Procedure) current$Mnerror$Mnport, 1, arg);
            wt.expectedType = Lit3;
            throw wt;
        }
    }

    public static void writeChar(Object ch, OutPort port) {
        Throwable th;
        Object obj = ch;
        Object obj2 = obj;
        try {
            Char.print(characters.char$To$Integer((Char) obj), port);
        } catch (ClassCastException e) {
            ClassCastException classCastException = e;
            Throwable th2 = th;
            new WrongType(classCastException, "char->integer", 1, obj2);
            throw th2;
        }
    }

    public static InPort openInputString(CharSequence str) {
        CharArrayInPort charArrayInPort = r5;
        CharSequence charSequence = str;
        CharArrayInPort charArrayInPort2 = new CharArrayInPort(charSequence == null ? null : charSequence.toString());
        return charArrayInPort;
    }

    public static CharArrayOutPort openOutputString() {
        CharArrayOutPort charArrayOutPort;
        CharArrayOutPort charArrayOutPort2 = charArrayOutPort;
        new CharArrayOutPort();
        return charArrayOutPort2;
    }

    public int match0(ModuleMethod moduleMethod, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 15:
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 0;
                return 0;
            case 19:
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 0;
                return 0;
            case 21:
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 0;
                return 0;
            case 24:
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 0;
                return 0;
            case 42:
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 0;
                return 0;
            case 44:
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 0;
                return 0;
            case 48:
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 0;
                return 0;
            default:
                return super.match0(moduleMethod2, callContext2);
        }
    }

    public static FString getOutputString(CharArrayOutPort output$Mnport) {
        FString fString;
        new FString(output$Mnport.toCharArray());
        return fString;
    }

    public static Object callWithInputString(CharSequence str, Procedure procedure) {
        Procedure proc = procedure;
        CharArrayInPort charArrayInPort = r8;
        CharSequence charSequence = str;
        CharArrayInPort charArrayInPort2 = new CharArrayInPort(charSequence == null ? null : charSequence.toString());
        CharArrayInPort port = charArrayInPort;
        Object result = proc.apply1(port);
        Object closeInputPort = closeInputPort(port);
        return result;
    }

    public static Object callWithOutputString(Procedure proc) {
        CharArrayOutPort charArrayOutPort;
        Object obj;
        new CharArrayOutPort();
        CharArrayOutPort port = charArrayOutPort;
        Object apply1 = proc.apply1(port);
        char[] chars = port.toCharArray();
        port.close();
        new FString(chars);
        return obj;
    }

    public static void forceOutput(Object port) {
        Throwable th;
        Object obj = port;
        Object obj2 = obj;
        try {
            ((Writer) obj).flush();
        } catch (ClassCastException e) {
            ClassCastException classCastException = e;
            Throwable th2 = th;
            new WrongType(classCastException, "java.io.Writer.flush()", 1, obj2);
            throw th2;
        }
    }

    public static void newline(Object port) {
        Throwable th;
        Object obj = port;
        Object obj2 = obj;
        try {
            ((OutPort) obj).println();
        } catch (ClassCastException e) {
            ClassCastException classCastException = e;
            Throwable th2 = th;
            new WrongType(classCastException, "gnu.mapping.OutPort.println()", 1, obj2);
            throw th2;
        }
    }

    public static boolean isEofObject(Object obj) {
        return obj == EofClass.eofValue;
    }

    public static boolean isCharReady(Object port) {
        return char_ready_p.ready(port);
    }

    public static void write(Object value, Object out) {
        Throwable th;
        Object obj = out;
        Object obj2 = obj;
        try {
            Scheme.writeFormat.format(value, (Consumer) obj);
        } catch (ClassCastException e) {
            ClassCastException classCastException = e;
            Throwable th2 = th;
            new WrongType(classCastException, "gnu.lists.AbstractFormat.format(java.lang.Object,gnu.lists.Consumer)", 3, obj2);
            throw th2;
        }
    }

    public static void display(Object value, Object out) {
        Throwable th;
        Object obj = out;
        Object obj2 = obj;
        try {
            Scheme.displayFormat.format(value, (Consumer) obj);
        } catch (ClassCastException e) {
            ClassCastException classCastException = e;
            Throwable th2 = th;
            new WrongType(classCastException, "gnu.lists.AbstractFormat.format(java.lang.Object,gnu.lists.Consumer)", 3, obj2);
            throw th2;
        }
    }

    public static char inputPortReadState(Object port) {
        Throwable th;
        Object obj = port;
        Object obj2 = obj;
        try {
            return ((InPort) obj).getReadState();
        } catch (ClassCastException e) {
            ClassCastException classCastException = e;
            Throwable th2 = th;
            new WrongType(classCastException, "gnu.mapping.InPort.getReadState()", 1, obj2);
            throw th2;
        }
    }

    public static void setPortLine$Ex(Object port, Object line) {
        Throwable th;
        Throwable th2;
        Object obj = port;
        Object obj2 = obj;
        try {
            LineBufferedReader lineBufferedReader = (LineBufferedReader) obj;
            Object obj3 = line;
            Object obj4 = obj3;
            try {
                lineBufferedReader.setLineNumber(((Number) obj3).intValue());
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th3 = th2;
                new WrongType(classCastException, "gnu.text.LineBufferedReader.setLineNumber(int)", 2, obj4);
                throw th3;
            }
        } catch (ClassCastException e2) {
            ClassCastException classCastException2 = e2;
            Throwable th4 = th;
            new WrongType(classCastException2, "gnu.text.LineBufferedReader.setLineNumber(int)", 1, obj2);
            throw th4;
        }
    }

    public static int portLine(LineBufferedReader port) {
        return port.getLineNumber();
    }

    public static void setInputPortLineNumber$Ex(Object port, Object num) {
        setPortLine$Ex(port, AddOp.$Mn.apply2(num, Lit6));
    }

    public static Object inputPortLineNumber(LineBufferedReader port) {
        return AddOp.$Pl.apply2(Lit6, port$Mnline.apply1(port));
    }

    public static int portColumn(Object port) {
        Throwable th;
        Object obj = port;
        Object obj2 = obj;
        try {
            return ((LineBufferedReader) obj).getColumnNumber();
        } catch (ClassCastException e) {
            ClassCastException classCastException = e;
            Throwable th2 = th;
            new WrongType(classCastException, "gnu.text.LineBufferedReader.getColumnNumber()", 1, obj2);
            throw th2;
        }
    }

    public static int inputPortColumnNumber(Object port) {
        return 1 + portColumn(port);
    }

    public static Object defaultPrompter(Object obj) {
        Object stringAppend;
        Throwable th;
        Object port;
        Object port2 = obj;
        char state = inputPortReadState(port2);
        if (characters.isChar$Eq(Char.make(state), Lit7)) {
            port = "";
        } else {
            Object[] objArr = new Object[3];
            Object[] objArr2 = objArr;
            Object[] objArr3 = objArr;
            if (characters.isChar$Eq(Char.make(state), Lit8)) {
                stringAppend = "#|kawa:";
            } else {
                Object[] objArr4 = new Object[3];
                objArr4[0] = "#|";
                Object[] objArr5 = objArr4;
                objArr5[1] = strings.makeString(1, Char.make(state));
                Object[] objArr6 = objArr5;
                objArr6[2] = "---:";
                stringAppend = strings.stringAppend(objArr6);
            }
            objArr3[0] = stringAppend;
            Object[] objArr7 = objArr2;
            Object[] objArr8 = objArr7;
            Object[] objArr9 = objArr7;
            Object apply1 = input$Mnport$Mnline$Mnnumber.apply1(port2);
            Object obj2 = apply1;
            try {
                objArr9[1] = numbers.number$To$String((Number) apply1);
                Object[] objArr10 = objArr8;
                objArr10[2] = "|# ";
                port = strings.stringAppend(objArr10);
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "number->string", 0, obj2);
                throw th2;
            }
        }
        return port;
    }

    public static void setInputPortPrompter$Ex(TtyInPort port, Procedure prompter) {
        port.setPrompter(prompter);
    }

    public static Procedure inputPortPrompter(TtyInPort port) {
        return port.getPrompter();
    }

    public static Object closeInputPort(InPort port) {
        port.close();
        return Values.empty;
    }

    public static Object closeOutputPort(OutPort port) {
        port.close();
        return Values.empty;
    }

    public static Object read(InPort port) {
        LispReader lispReader;
        Throwable th;
        new LispReader(port);
        LispReader lexer = lispReader;
        try {
            Object result = lexer.readObject();
            if (!lexer.seenErrors()) {
                return result;
            }
            new SyntaxException(lexer.getMessages());
            throw th;
        } catch (SyntaxException e) {
            SyntaxException ex = e;
            ex.setHeader("syntax error in read:");
            throw ex;
        }
    }

    public static Object readLine(LineBufferedReader port, Symbol handling) {
        Symbol symbol = handling;
        return read_line.apply(port, symbol == null ? null : symbol.toString());
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
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        switch (moduleMethod2.selector) {
            case 3:
                try {
                    try {
                        return callWithInputFile(Path.valueOf(obj3), (Procedure) obj4);
                    } catch (ClassCastException e) {
                        ClassCastException classCastException = e;
                        Throwable th16 = th15;
                        new WrongType(classCastException, "call-with-input-file", 2, obj4);
                        throw th16;
                    }
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th17 = th14;
                    new WrongType(classCastException2, "call-with-input-file", 1, obj3);
                    throw th17;
                }
            case 4:
                try {
                    try {
                        return callWithOutputFile(Path.valueOf(obj3), (Procedure) obj4);
                    } catch (ClassCastException e3) {
                        ClassCastException classCastException3 = e3;
                        Throwable th18 = th13;
                        new WrongType(classCastException3, "call-with-output-file", 2, obj4);
                        throw th18;
                    }
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th19 = th12;
                    new WrongType(classCastException4, "call-with-output-file", 1, obj3);
                    throw th19;
                }
            case 5:
                try {
                    try {
                        return withInputFromFile(Path.valueOf(obj3), (Procedure) obj4);
                    } catch (ClassCastException e5) {
                        ClassCastException classCastException5 = e5;
                        Throwable th20 = th11;
                        new WrongType(classCastException5, "with-input-from-file", 2, obj4);
                        throw th20;
                    }
                } catch (ClassCastException e6) {
                    ClassCastException classCastException6 = e6;
                    Throwable th21 = th10;
                    new WrongType(classCastException6, "with-input-from-file", 1, obj3);
                    throw th21;
                }
            case 6:
                try {
                    try {
                        return withOutputToFile(Path.valueOf(obj3), (Procedure) obj4);
                    } catch (ClassCastException e7) {
                        ClassCastException classCastException7 = e7;
                        Throwable th22 = th9;
                        new WrongType(classCastException7, "with-output-to-file", 2, obj4);
                        throw th22;
                    }
                } catch (ClassCastException e8) {
                    ClassCastException classCastException8 = e8;
                    Throwable th23 = th8;
                    new WrongType(classCastException8, "with-output-to-file", 1, obj3);
                    throw th23;
                }
            case 12:
                try {
                    writeChar(obj3, (OutPort) obj4);
                    return Values.empty;
                } catch (ClassCastException e9) {
                    ClassCastException classCastException9 = e9;
                    Throwable th24 = th7;
                    new WrongType(classCastException9, "write-char", 2, obj4);
                    throw th24;
                }
            case 17:
                try {
                    try {
                        return callWithInputString((CharSequence) obj3, (Procedure) obj4);
                    } catch (ClassCastException e10) {
                        ClassCastException classCastException10 = e10;
                        Throwable th25 = th6;
                        new WrongType(classCastException10, "call-with-input-string", 2, obj4);
                        throw th25;
                    }
                } catch (ClassCastException e11) {
                    ClassCastException classCastException11 = e11;
                    Throwable th26 = th5;
                    new WrongType(classCastException11, "call-with-input-string", 1, obj3);
                    throw th26;
                }
            case 26:
                write(obj3, obj4);
                return Values.empty;
            case 28:
                display(obj3, obj4);
                return Values.empty;
            case 31:
                setPortLine$Ex(obj3, obj4);
                return Values.empty;
            case 33:
                setInputPortLineNumber$Ex(obj3, obj4);
                return Values.empty;
            case 38:
                try {
                    try {
                        setInputPortPrompter$Ex((TtyInPort) obj3, (Procedure) obj4);
                        return Values.empty;
                    } catch (ClassCastException e12) {
                        ClassCastException classCastException12 = e12;
                        Throwable th27 = th4;
                        new WrongType(classCastException12, "set-input-port-prompter!", 2, obj4);
                        throw th27;
                    }
                } catch (ClassCastException e13) {
                    ClassCastException classCastException13 = e13;
                    Throwable th28 = th3;
                    new WrongType(classCastException13, "set-input-port-prompter!", 1, obj3);
                    throw th28;
                }
            case 44:
                try {
                    try {
                        return readLine((LineBufferedReader) obj3, (Symbol) obj4);
                    } catch (ClassCastException e14) {
                        ClassCastException classCastException14 = e14;
                        Throwable th29 = th2;
                        new WrongType(classCastException14, "read-line", 2, obj4);
                        throw th29;
                    }
                } catch (ClassCastException e15) {
                    ClassCastException classCastException15 = e15;
                    Throwable th30 = th;
                    new WrongType(classCastException15, "read-line", 1, obj3);
                    throw th30;
                }
            default:
                return super.apply2(moduleMethod2, obj3, obj4);
        }
    }

    public static void transcriptOn(Object filename) {
        OutPort.setLogFile(filename.toString());
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
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        switch (moduleMethod2.selector) {
            case 1:
                try {
                    return openInputFile(Path.valueOf(obj2));
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th13 = th12;
                    new WrongType(classCastException, "open-input-file", 1, obj2);
                    throw th13;
                }
            case 2:
                try {
                    return openOutputFile(Path.valueOf(obj2));
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th14 = th11;
                    new WrongType(classCastException2, "open-output-file", 1, obj2);
                    throw th14;
                }
            case 7:
                return isInputPort(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 8:
                return isOutputPort(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 9:
                return lambda1(obj2);
            case 10:
                return lambda2(obj2);
            case 11:
                return lambda3(obj2);
            case 12:
                writeChar(obj2);
                return Values.empty;
            case 14:
                try {
                    return openInputString((CharSequence) obj2);
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th15 = th10;
                    new WrongType(classCastException3, "open-input-string", 1, obj2);
                    throw th15;
                }
            case 16:
                try {
                    return getOutputString((CharArrayOutPort) obj2);
                } catch (ClassCastException e4) {
                    ClassCastException classCastException4 = e4;
                    Throwable th16 = th9;
                    new WrongType(classCastException4, "get-output-string", 1, obj2);
                    throw th16;
                }
            case 18:
                try {
                    return callWithOutputString((Procedure) obj2);
                } catch (ClassCastException e5) {
                    ClassCastException classCastException5 = e5;
                    Throwable th17 = th8;
                    new WrongType(classCastException5, "call-with-output-string", 1, obj2);
                    throw th17;
                }
            case 19:
                forceOutput(obj2);
                return Values.empty;
            case 21:
                newline(obj2);
                return Values.empty;
            case 23:
                return isEofObject(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 24:
                return isCharReady(obj2) ? Boolean.TRUE : Boolean.FALSE;
            case 26:
                write(obj2);
                return Values.empty;
            case 28:
                display(obj2);
                return Values.empty;
            case 30:
                return Char.make(inputPortReadState(obj2));
            case 32:
                try {
                    return Integer.valueOf(portLine((LineBufferedReader) obj2));
                } catch (ClassCastException e6) {
                    ClassCastException classCastException6 = e6;
                    Throwable th18 = th7;
                    new WrongType(classCastException6, "port-line", 1, obj2);
                    throw th18;
                }
            case 34:
                try {
                    return inputPortLineNumber((LineBufferedReader) obj2);
                } catch (ClassCastException e7) {
                    ClassCastException classCastException7 = e7;
                    Throwable th19 = th6;
                    new WrongType(classCastException7, "input-port-line-number", 1, obj2);
                    throw th19;
                }
            case 35:
                return Integer.valueOf(portColumn(obj2));
            case 36:
                return Integer.valueOf(inputPortColumnNumber(obj2));
            case 37:
                return defaultPrompter(obj2);
            case 39:
                try {
                    return inputPortPrompter((TtyInPort) obj2);
                } catch (ClassCastException e8) {
                    ClassCastException classCastException8 = e8;
                    Throwable th20 = th5;
                    new WrongType(classCastException8, "input-port-prompter", 1, obj2);
                    throw th20;
                }
            case 40:
                try {
                    return closeInputPort((InPort) obj2);
                } catch (ClassCastException e9) {
                    ClassCastException classCastException9 = e9;
                    Throwable th21 = th4;
                    new WrongType(classCastException9, "close-input-port", 1, obj2);
                    throw th21;
                }
            case 41:
                try {
                    return closeOutputPort((OutPort) obj2);
                } catch (ClassCastException e10) {
                    ClassCastException classCastException10 = e10;
                    Throwable th22 = th3;
                    new WrongType(classCastException10, "close-output-port", 1, obj2);
                    throw th22;
                }
            case 42:
                try {
                    return read((InPort) obj2);
                } catch (ClassCastException e11) {
                    ClassCastException classCastException11 = e11;
                    Throwable th23 = th2;
                    new WrongType(classCastException11, "read", 1, obj2);
                    throw th23;
                }
            case 44:
                try {
                    return readLine((LineBufferedReader) obj2);
                } catch (ClassCastException e12) {
                    ClassCastException classCastException12 = e12;
                    Throwable th24 = th;
                    new WrongType(classCastException12, "read-line", 1, obj2);
                    throw th24;
                }
            case 47:
                transcriptOn(obj2);
                return Values.empty;
            default:
                return super.apply1(moduleMethod2, obj2);
        }
    }

    public static void transcriptOff() {
        OutPort.closeLogFile();
    }

    public Object apply0(ModuleMethod moduleMethod) {
        ModuleMethod moduleMethod2 = moduleMethod;
        switch (moduleMethod2.selector) {
            case 15:
                return openOutputString();
            case 19:
                forceOutput();
                return Values.empty;
            case 21:
                newline();
                return Values.empty;
            case 24:
                return isCharReady() ? Boolean.TRUE : Boolean.FALSE;
            case 42:
                return read();
            case 44:
                return readLine();
            case 48:
                transcriptOff();
                return Values.empty;
            default:
                return super.apply0(moduleMethod2);
        }
    }
}

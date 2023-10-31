package gnu.kawa.slib;

import android.support.p000v4.app.FragmentTransaction;
import com.google.appinventor.components.common.PropertyTypeConstants;
import gnu.expr.Keyword;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.Format;
import gnu.kawa.functions.GetNamedPart;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.kawa.models.Box;
import gnu.kawa.models.Button;
import gnu.kawa.models.Display;
import gnu.kawa.models.Label;
import gnu.kawa.models.Model;
import gnu.kawa.models.Text;
import gnu.kawa.models.Window;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.reflect.SlotGet;
import gnu.kawa.xml.KAttr;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.ThreadLocation;
import gnu.mapping.UnboundLocationException;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.text.Path;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lib.misc;
import kawa.standard.Scheme;

/* compiled from: gui.scm */
public class gui extends ModuleBody {
    public static final gui $instance;
    public static final ModuleMethod Button;
    public static final ModuleMethod Column;
    public static final Macro Image = Macro.make(Lit12, Lit13, $instance);
    public static final ModuleMethod Label;
    static final Class Lit0 = Color.class;
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit12;
    static final SyntaxRules Lit13;
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
    static final SyntaxRules Lit24;
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
    static final IntNum Lit44 = IntNum.make(1);
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    static final SyntaxRules Lit8;
    static final SimpleSymbol Lit9;
    public static final ModuleMethod Row;
    public static final ModuleMethod Text;
    public static final ModuleMethod Window;
    public static final ModuleMethod as$Mncolor;
    public static final ModuleMethod button;
    public static final ModuleMethod image$Mnheight;
    public static final ModuleMethod image$Mnread;
    public static final ModuleMethod image$Mnwidth;
    static final Location loc$$Lsgnu$Dtkawa$Dtmodels$DtColumn$Gr = ThreadLocation.getInstance(Lit6, (Object) null);
    static final Location loc$$Lsgnu$Dtkawa$Dtmodels$DtRow$Gr = ThreadLocation.getInstance(Lit5, (Object) null);
    static final Location loc$$St$DtgetHeight = ThreadLocation.getInstance(Lit4, (Object) null);
    static final Location loc$$St$DtgetWidth = ThreadLocation.getInstance(Lit3, (Object) null);
    public static final Macro process$Mnkeywords = Macro.make(Lit7, Lit8, $instance);
    public static final Macro run$Mnapplication = Macro.make(Lit23, Lit24, $instance);
    public static final ModuleMethod set$Mncontent;

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
        SyntaxRules syntaxRules2;
        SimpleSymbol simpleSymbol32;
        SyntaxRule syntaxRule2;
        SyntaxPattern syntaxPattern2;
        SimpleSymbol simpleSymbol33;
        SimpleSymbol simpleSymbol34;
        SimpleSymbol simpleSymbol35;
        SimpleSymbol simpleSymbol36;
        SimpleSymbol simpleSymbol37;
        SimpleSymbol simpleSymbol38;
        SyntaxRules syntaxRules3;
        SimpleSymbol simpleSymbol39;
        SyntaxRule syntaxRule3;
        SyntaxPattern syntaxPattern3;
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
        gui gui;
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
        new SimpleSymbol("value");
        Lit43 = (SimpleSymbol) simpleSymbol.readResolve();
        new SimpleSymbol("name");
        Lit42 = (SimpleSymbol) simpleSymbol2.readResolve();
        new SimpleSymbol("invoke");
        Lit41 = (SimpleSymbol) simpleSymbol3.readResolve();
        new SimpleSymbol("getName");
        Lit40 = (SimpleSymbol) simpleSymbol4.readResolve();
        new SimpleSymbol(LispLanguage.quote_sym);
        Lit39 = (SimpleSymbol) simpleSymbol5.readResolve();
        new SimpleSymbol("attr");
        Lit38 = (SimpleSymbol) simpleSymbol6.readResolve();
        new SimpleSymbol("<gnu.kawa.xml.KAttr>");
        Lit37 = (SimpleSymbol) simpleSymbol7.readResolve();
        new SimpleSymbol(GetNamedPart.INSTANCEOF_METHOD_NAME);
        Lit36 = (SimpleSymbol) simpleSymbol8.readResolve();
        new SimpleSymbol("+");
        Lit35 = (SimpleSymbol) simpleSymbol9.readResolve();
        new SimpleSymbol("loop");
        Lit34 = (SimpleSymbol) simpleSymbol10.readResolve();
        new SimpleSymbol("<object>");
        Lit33 = (SimpleSymbol) simpleSymbol11.readResolve();
        new SimpleSymbol("primitive-array-get");
        Lit32 = (SimpleSymbol) simpleSymbol12.readResolve();
        new SimpleSymbol(LispLanguage.quasiquote_sym);
        Lit31 = (SimpleSymbol) simpleSymbol13.readResolve();
        new SimpleSymbol("$lookup$");
        Lit30 = (SimpleSymbol) simpleSymbol14.readResolve();
        new SimpleSymbol("arg");
        Lit29 = (SimpleSymbol) simpleSymbol15.readResolve();
        new SimpleSymbol("num-args");
        Lit28 = (SimpleSymbol) simpleSymbol16.readResolve();
        new SimpleSymbol("i");
        Lit27 = (SimpleSymbol) simpleSymbol17.readResolve();
        new SimpleSymbol("<int>");
        Lit26 = (SimpleSymbol) simpleSymbol18.readResolve();
        new SimpleSymbol("::");
        Lit25 = (SimpleSymbol) simpleSymbol19.readResolve();
        SyntaxRules syntaxRules4 = syntaxRules;
        Object[] objArr = new Object[1];
        Object[] objArr2 = objArr;
        Object[] objArr3 = objArr;
        new SimpleSymbol("run-application");
        SimpleSymbol simpleSymbol58 = (SimpleSymbol) simpleSymbol20.readResolve();
        Lit23 = simpleSymbol58;
        objArr3[0] = simpleSymbol58;
        SyntaxRule[] syntaxRuleArr = new SyntaxRule[1];
        SyntaxRule[] syntaxRuleArr2 = syntaxRuleArr;
        SyntaxRule[] syntaxRuleArr3 = syntaxRuleArr;
        SyntaxRule syntaxRule4 = syntaxRule;
        new SyntaxPattern("\f\u0018\f\u0007\b", new Object[0], 1);
        SimpleSymbol simpleSymbol59 = Lit30;
        new SimpleSymbol("gnu.kawa.models.Window");
        SimpleSymbol simpleSymbol60 = Lit31;
        new SimpleSymbol("open");
        new SyntaxRule(syntaxPattern, "\u0001", "\u0011\u0018\u0004\b\u0003", new Object[]{PairWithPosition.make(simpleSymbol59, Pair.make((SimpleSymbol) simpleSymbol21.readResolve(), Pair.make(Pair.make(simpleSymbol60, Pair.make((SimpleSymbol) simpleSymbol22.readResolve(), LList.Empty)), LList.Empty)), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 749575)}, 0);
        syntaxRuleArr3[0] = syntaxRule4;
        new SyntaxRules(objArr2, syntaxRuleArr2, 1);
        Lit24 = syntaxRules4;
        new SimpleSymbol("Window");
        Lit22 = (SimpleSymbol) simpleSymbol23.readResolve();
        new SimpleSymbol("set-content");
        Lit21 = (SimpleSymbol) simpleSymbol24.readResolve();
        new SimpleSymbol("Column");
        Lit20 = (SimpleSymbol) simpleSymbol25.readResolve();
        new SimpleSymbol("Row");
        Lit19 = (SimpleSymbol) simpleSymbol26.readResolve();
        new SimpleSymbol("Text");
        Lit18 = (SimpleSymbol) simpleSymbol27.readResolve();
        new SimpleSymbol("Label");
        Lit17 = (SimpleSymbol) simpleSymbol28.readResolve();
        new SimpleSymbol("image-height");
        Lit16 = (SimpleSymbol) simpleSymbol29.readResolve();
        new SimpleSymbol("image-width");
        Lit15 = (SimpleSymbol) simpleSymbol30.readResolve();
        new SimpleSymbol("image-read");
        Lit14 = (SimpleSymbol) simpleSymbol31.readResolve();
        SyntaxRules syntaxRules5 = syntaxRules2;
        Object[] objArr4 = new Object[1];
        Object[] objArr5 = objArr4;
        new SimpleSymbol("text-field");
        objArr4[0] = (SimpleSymbol) simpleSymbol32.readResolve();
        SyntaxRule[] syntaxRuleArr4 = new SyntaxRule[1];
        SyntaxRule[] syntaxRuleArr5 = syntaxRuleArr4;
        SyntaxRule[] syntaxRuleArr6 = syntaxRuleArr4;
        SyntaxRule syntaxRule5 = syntaxRule2;
        SyntaxPattern syntaxPattern4 = syntaxPattern2;
        new SyntaxPattern("\f\u0018\u0003", new Object[0], 1);
        Object[] objArr6 = new Object[2];
        new SimpleSymbol("make");
        objArr6[0] = (SimpleSymbol) simpleSymbol33.readResolve();
        Object[] objArr7 = objArr6;
        new SimpleSymbol("<gnu.kawa.models.DrawImage>");
        objArr7[1] = (SimpleSymbol) simpleSymbol34.readResolve();
        new SyntaxRule(syntaxPattern4, "\u0000", "\u0011\u0018\u0004\u0011\u0018\f\u0002", objArr7, 0);
        syntaxRuleArr6[0] = syntaxRule5;
        new SyntaxRules(objArr5, syntaxRuleArr5, 1);
        Lit13 = syntaxRules5;
        new SimpleSymbol("Image");
        Lit12 = (SimpleSymbol) simpleSymbol35.readResolve();
        new SimpleSymbol("Button");
        Lit11 = (SimpleSymbol) simpleSymbol36.readResolve();
        new SimpleSymbol("button");
        Lit10 = (SimpleSymbol) simpleSymbol37.readResolve();
        new SimpleSymbol("as-color");
        Lit9 = (SimpleSymbol) simpleSymbol38.readResolve();
        SyntaxRules syntaxRules6 = syntaxRules3;
        Object[] objArr8 = new Object[1];
        Object[] objArr9 = objArr8;
        Object[] objArr10 = objArr8;
        new SimpleSymbol("process-keywords");
        SimpleSymbol simpleSymbol61 = (SimpleSymbol) simpleSymbol39.readResolve();
        Lit7 = simpleSymbol61;
        objArr10[0] = simpleSymbol61;
        SyntaxRule[] syntaxRuleArr7 = new SyntaxRule[1];
        SyntaxRule[] syntaxRuleArr8 = syntaxRuleArr7;
        SyntaxRule[] syntaxRuleArr9 = syntaxRuleArr7;
        SyntaxRule syntaxRule6 = syntaxRule3;
        SyntaxPattern syntaxPattern5 = syntaxPattern3;
        new SyntaxPattern("\f\u0018\f\u0007\f\u000f\f\u0017\f\u001f\b", new Object[0], 4);
        Object[] objArr11 = new Object[27];
        new SimpleSymbol("let");
        objArr11[0] = (SimpleSymbol) simpleSymbol40.readResolve();
        Object[] objArr12 = objArr11;
        objArr12[1] = Lit28;
        Object[] objArr13 = objArr12;
        objArr13[2] = Lit25;
        Object[] objArr14 = objArr13;
        objArr14[3] = Lit26;
        Object[] objArr15 = objArr14;
        new SimpleSymbol("field");
        objArr15[4] = (SimpleSymbol) simpleSymbol41.readResolve();
        Object[] objArr16 = objArr15;
        SimpleSymbol simpleSymbol62 = Lit39;
        new SimpleSymbol("length");
        objArr16[5] = PairWithPosition.make(PairWithPosition.make(simpleSymbol62, PairWithPosition.make((SimpleSymbol) simpleSymbol42.readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 16426), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 16426), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 16425);
        Object[] objArr17 = objArr16;
        objArr17[6] = Lit34;
        Object[] objArr18 = objArr17;
        objArr18[7] = PairWithPosition.make(PairWithPosition.make(Lit27, PairWithPosition.make(Lit25, PairWithPosition.make(Lit26, PairWithPosition.make(IntNum.make(0), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 20509), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 20503), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 20500), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 20497), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 20496);
        Object[] objArr19 = objArr18;
        new SimpleSymbol("if");
        objArr19[8] = (SimpleSymbol) simpleSymbol43.readResolve();
        Object[] objArr20 = objArr19;
        new SimpleSymbol("<");
        objArr20[9] = PairWithPosition.make((SimpleSymbol) simpleSymbol44.readResolve(), PairWithPosition.make(Lit27, PairWithPosition.make(Lit28, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 24593), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 24591), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 24588);
        Object[] objArr21 = objArr20;
        objArr21[10] = Lit29;
        Object[] objArr22 = objArr21;
        objArr22[11] = PairWithPosition.make(Lit32, PairWithPosition.make(Lit33, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 28710), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 28689);
        Object[] objArr23 = objArr22;
        objArr23[12] = PairWithPosition.make(Lit27, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 28725);
        Object[] objArr24 = objArr23;
        new SimpleSymbol("cond");
        objArr24[13] = (SimpleSymbol) simpleSymbol45.readResolve();
        Object[] objArr25 = objArr24;
        SimpleSymbol simpleSymbol63 = Lit36;
        SimpleSymbol simpleSymbol64 = Lit29;
        new SimpleSymbol("<gnu.expr.Keyword>");
        objArr25[14] = PairWithPosition.make(simpleSymbol63, PairWithPosition.make(simpleSymbol64, PairWithPosition.make((SimpleSymbol) simpleSymbol46.readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 32797), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 32793), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 32782);
        Object[] objArr26 = objArr25;
        SimpleSymbol simpleSymbol65 = Lit30;
        new SimpleSymbol("gnu.expr.Keyword");
        objArr26[15] = PairWithPosition.make(PairWithPosition.make(simpleSymbol65, Pair.make((SimpleSymbol) simpleSymbol47.readResolve(), Pair.make(Pair.make(Lit31, Pair.make(Lit40, LList.Empty)), LList.Empty)), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 40970), PairWithPosition.make(Lit29, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 40995), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 40969);
        Object[] objArr27 = objArr26;
        objArr27[16] = PairWithPosition.make(Lit32, PairWithPosition.make(Lit33, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 45087), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 45066);
        Object[] objArr28 = objArr27;
        objArr28[17] = PairWithPosition.make(PairWithPosition.make(Lit35, PairWithPosition.make(Lit27, PairWithPosition.make(Lit44, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 45107), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 45105), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 45102), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 45102);
        Object[] objArr29 = objArr28;
        objArr29[18] = PairWithPosition.make(PairWithPosition.make(Lit34, PairWithPosition.make(PairWithPosition.make(Lit35, PairWithPosition.make(Lit27, PairWithPosition.make(IntNum.make(2), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 49170), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 49168), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 49165), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 49165), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 49159), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 49159);
        Object[] objArr30 = objArr29;
        objArr30[19] = PairWithPosition.make(Lit36, PairWithPosition.make(Lit29, PairWithPosition.make(Lit37, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 53270), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 53266), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 53255);
        Object[] objArr31 = objArr30;
        new SimpleSymbol("let*");
        objArr31[20] = (SimpleSymbol) simpleSymbol48.readResolve();
        Object[] objArr32 = objArr31;
        PairWithPosition make = PairWithPosition.make(Lit38, PairWithPosition.make(Lit25, PairWithPosition.make(Lit37, PairWithPosition.make(Lit29, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 57388), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 57367), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 57364), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 57358);
        SimpleSymbol simpleSymbol66 = Lit42;
        SimpleSymbol simpleSymbol67 = Lit25;
        new SimpleSymbol("<java.lang.String>");
        PairWithPosition make2 = PairWithPosition.make(simpleSymbol66, PairWithPosition.make(simpleSymbol67, PairWithPosition.make((SimpleSymbol) simpleSymbol49.readResolve(), PairWithPosition.make(PairWithPosition.make(Lit41, PairWithPosition.make(Lit38, PairWithPosition.make(PairWithPosition.make(Lit39, PairWithPosition.make(Lit40, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 61489), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 61489), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 61488), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 61483), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 61475), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 61475), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 61456), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 61453), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 61447);
        SimpleSymbol simpleSymbol68 = Lit43;
        SimpleSymbol simpleSymbol69 = Lit41;
        SimpleSymbol simpleSymbol70 = Lit38;
        SimpleSymbol simpleSymbol71 = Lit39;
        new SimpleSymbol("getObjectValue");
        objArr32[21] = PairWithPosition.make(make, PairWithPosition.make(make2, PairWithPosition.make(PairWithPosition.make(simpleSymbol68, PairWithPosition.make(PairWithPosition.make(simpleSymbol69, PairWithPosition.make(simpleSymbol70, PairWithPosition.make(PairWithPosition.make(simpleSymbol71, PairWithPosition.make((SimpleSymbol) simpleSymbol50.readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 65564), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 65564), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 65563), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 65558), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 65550), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 65550), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 65543), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 65543), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 61447), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 57357);
        Object[] objArr33 = objArr32;
        objArr33[22] = PairWithPosition.make(Lit42, PairWithPosition.make(Lit43, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 69666), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 69661);
        Object[] objArr34 = objArr33;
        objArr34[23] = PairWithPosition.make(PairWithPosition.make(Lit34, PairWithPosition.make(PairWithPosition.make(Lit35, PairWithPosition.make(Lit27, PairWithPosition.make(Lit44, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 73746), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 73744), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 73741), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 73741), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 73735), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 73735);
        Object[] objArr35 = objArr34;
        new SimpleSymbol("else");
        objArr35[24] = (SimpleSymbol) simpleSymbol51.readResolve();
        Object[] objArr36 = objArr35;
        objArr36[25] = PairWithPosition.make(Lit29, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 81951);
        Object[] objArr37 = objArr36;
        objArr37[26] = PairWithPosition.make(PairWithPosition.make(Lit34, PairWithPosition.make(PairWithPosition.make(Lit35, PairWithPosition.make(Lit27, PairWithPosition.make(Lit44, LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 86034), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 86032), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 86029), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 86029), "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 86023), LList.Empty, "/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 86023);
        new SyntaxRule(syntaxPattern5, "\u0001\u0001\u0001\u0001", "\u0011\u0018\u0004\b\u0011\u0018\f\u0011\u0018\u0014\u0011\u0018\u001c\b\u0011\u0018$\t\u000b\u0018,\b\u0011\u0018\u0004\u0011\u00184\u0011\u0018<\b\u0011\u0018D\u0011\u0018L\b\u0011\u0018\u0004a\b\u0011\u0018T\b\u0011\u0018\\\t\u000b\u0018d\b\u0011\u0018l©\u0011\u0018ty\t\u0013\t\u0003\u0011\u0018|\b\u0011\u0018\t\u000b\u0018\u0018\u0011\u0018i\u0011\u0018¤\u0011\u0018¬\b\t\u0013\t\u0003\u0018´\u0018¼\b\u0011\u0018Ä1\t\u001b\t\u0003\u0018Ì\u0018Ô", objArr37, 0);
        syntaxRuleArr9[0] = syntaxRule6;
        new SyntaxRules(objArr9, syntaxRuleArr8, 4);
        Lit8 = syntaxRules6;
        new SimpleSymbol("<gnu.kawa.models.Column>");
        Lit6 = (SimpleSymbol) simpleSymbol52.readResolve();
        new SimpleSymbol("<gnu.kawa.models.Row>");
        Lit5 = (SimpleSymbol) simpleSymbol53.readResolve();
        new SimpleSymbol("*.getHeight");
        Lit4 = (SimpleSymbol) simpleSymbol54.readResolve();
        new SimpleSymbol("*.getWidth");
        Lit3 = (SimpleSymbol) simpleSymbol55.readResolve();
        new SimpleSymbol("cell-spacing");
        Lit2 = (SimpleSymbol) simpleSymbol56.readResolve();
        new SimpleSymbol(PropertyTypeConstants.PROPERTY_TYPE_TEXT);
        Lit1 = (SimpleSymbol) simpleSymbol57.readResolve();
        new gui();
        $instance = gui;
        gui gui2 = $instance;
        new ModuleMethod(gui2, 1, Lit9, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        as$Mncolor = moduleMethod;
        new ModuleMethod(gui2, 2, Lit10, -4096);
        button = moduleMethod2;
        new ModuleMethod(gui2, 3, Lit11, -4096);
        Button = moduleMethod3;
        new ModuleMethod(gui2, 4, Lit14, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        image$Mnread = moduleMethod4;
        new ModuleMethod(gui2, 5, Lit15, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        image$Mnwidth = moduleMethod5;
        new ModuleMethod(gui2, 6, Lit16, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        image$Mnheight = moduleMethod6;
        new ModuleMethod(gui2, 7, Lit17, -4096);
        Label = moduleMethod7;
        new ModuleMethod(gui2, 8, Lit18, -4096);
        Text = moduleMethod8;
        new ModuleMethod(gui2, 9, Lit19, -4096);
        Row = moduleMethod9;
        new ModuleMethod(gui2, 10, Lit20, -4096);
        Column = moduleMethod10;
        new ModuleMethod(gui2, 11, Lit21, 8194);
        set$Mncontent = moduleMethod11;
        new ModuleMethod(gui2, 12, Lit22, -4096);
        Window = moduleMethod12;
        $instance.run();
    }

    public gui() {
        ModuleInfo.register(this);
    }

    public final void run(CallContext $ctx) {
        Consumer consumer = $ctx.consumer;
    }

    public static Color asColor(Object obj) {
        Color color;
        Color color2;
        Throwable th;
        Object value = obj;
        if (value instanceof Color) {
            color = (Color) value;
        } else if (value instanceof Integer) {
            color = r8;
            Object obj2 = value;
            Object obj3 = obj2;
            try {
                Color color3 = new Color(((Integer) obj2).intValue());
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "java.lang.Integer.intValue()", 1, obj3);
                throw th2;
            }
        } else if (value instanceof IntNum) {
            color = color2;
            new Color(IntNum.intValue(value));
        } else {
            color = (Color) SlotGet.staticField.apply2(Lit0, value.toString());
        }
        return color;
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
            case 4:
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
            case 5:
                CallContext callContext4 = callContext2;
                Object obj5 = obj2;
                Object obj6 = obj5;
                if (!(obj5 instanceof BufferedImage)) {
                    return -786431;
                }
                callContext4.value1 = obj6;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            case 6:
                CallContext callContext5 = callContext2;
                Object obj7 = obj2;
                Object obj8 = obj7;
                if (!(obj7 instanceof BufferedImage)) {
                    return -786431;
                }
                callContext5.value1 = obj8;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 1;
                return 0;
            default:
                return super.match1(moduleMethod2, obj2, callContext2);
        }
    }

    static Object buttonKeyword(Button button2, String str, Object obj) {
        Object error$V;
        Throwable th;
        Button button3 = button2;
        String name = str;
        Object value = obj;
        if (name == "foreground") {
            button3.setForeground(asColor(value));
            error$V = Values.empty;
        } else if (name == "background") {
            button3.setBackground(asColor(value));
            error$V = Values.empty;
        } else if (name == "action") {
            button3.setAction(value);
            error$V = Values.empty;
        } else if (name == PropertyTypeConstants.PROPERTY_TYPE_TEXT) {
            Object obj2 = value;
            button3.setText(obj2 == null ? null : obj2.toString());
            error$V = Values.empty;
        } else if (name == "disabled") {
            Object obj3 = value;
            Object obj4 = obj3;
            try {
                button3.setDisabled(obj3 != Boolean.FALSE);
                error$V = Values.empty;
            } catch (ClassCastException e) {
                ClassCastException classCastException = e;
                Throwable th2 = th;
                new WrongType(classCastException, "gnu.kawa.models.Button.setDisabled(boolean)", 2, obj4);
                throw th2;
            }
        } else {
            Object[] objArr = new Object[2];
            objArr[0] = "unknown button attribute ~s";
            Object[] objArr2 = objArr;
            objArr2[1] = name;
            error$V = misc.error$V(Format.formatToString(0, objArr2), new Object[0]);
        }
        return error$V;
    }

    static Boolean buttonNonKeyword(Button button2, Object obj) {
        Button button3 = button2;
        Object obj2 = obj;
        return Boolean.TRUE;
    }

    public static Button button(Object... objArr) {
        Button button2;
        Throwable th;
        Throwable th2;
        Object[] args = objArr;
        new Button();
        Button button3 = button2;
        int num$Mnargs = args.length;
        int i = 0;
        while (i < num$Mnargs) {
            Object arg = args[i];
            if (arg instanceof Keyword) {
                Object obj = arg;
                Object obj2 = obj;
                try {
                    Object buttonKeyword = buttonKeyword(button3, ((Keyword) obj).getName(), args[i + 1]);
                    i += 2;
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th3 = th2;
                    new WrongType(classCastException, "gnu.expr.Keyword.getName()", 1, obj2);
                    throw th3;
                }
            } else if (arg instanceof KAttr) {
                Object obj3 = arg;
                Object obj4 = obj3;
                try {
                    KAttr attr = (KAttr) obj3;
                    Object buttonKeyword2 = buttonKeyword(button3, attr.getName(), attr.getObjectValue());
                    i++;
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th4 = th;
                    new WrongType(classCastException2, "attr", -2, obj4);
                    throw th4;
                }
            } else {
                Boolean buttonNonKeyword = buttonNonKeyword(button3, arg);
                i++;
            }
        }
        return button3;
    }

    public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object[] objArr2 = objArr;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
            case 2:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 3:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 7:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 8:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 9:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 10:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            case 12:
                callContext2.values = objArr2;
                callContext2.proc = moduleMethod2;
                callContext2.f239pc = 5;
                return 0;
            default:
                return super.matchN(moduleMethod2, objArr2, callContext2);
        }
    }

    public static Button Button(Object... objArr) {
        Button button2;
        Throwable th;
        Throwable th2;
        Object[] args = objArr;
        new Button();
        Button button3 = button2;
        int num$Mnargs = args.length;
        int i = 0;
        while (i < num$Mnargs) {
            Object arg = args[i];
            if (arg instanceof Keyword) {
                Object obj = arg;
                Object obj2 = obj;
                try {
                    Object buttonKeyword = buttonKeyword(button3, ((Keyword) obj).getName(), args[i + 1]);
                    i += 2;
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th3 = th2;
                    new WrongType(classCastException, "gnu.expr.Keyword.getName()", 1, obj2);
                    throw th3;
                }
            } else if (arg instanceof KAttr) {
                Object obj3 = arg;
                Object obj4 = obj3;
                try {
                    KAttr attr = (KAttr) obj3;
                    Object buttonKeyword2 = buttonKeyword(button3, attr.getName(), attr.getObjectValue());
                    i++;
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th4 = th;
                    new WrongType(classCastException2, "attr", -2, obj4);
                    throw th4;
                }
            } else {
                Boolean buttonNonKeyword = buttonNonKeyword(button3, arg);
                i++;
            }
        }
        return button3;
    }

    public static BufferedImage imageRead(Path uri) {
        return ImageIO.read(uri.openInputStream());
    }

    public static int imageWidth(BufferedImage image) {
        try {
            return ((Number) Scheme.applyToArgs.apply2(loc$$St$DtgetWidth.get(), image)).intValue();
        } catch (UnboundLocationException e) {
            UnboundLocationException unboundLocationException = e;
            UnboundLocationException unboundLocationException2 = unboundLocationException;
            unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 74, 3);
            throw unboundLocationException2;
        }
    }

    public static int imageHeight(BufferedImage image) {
        try {
            return ((Number) Scheme.applyToArgs.apply2(loc$$St$DtgetHeight.get(), image)).intValue();
        } catch (UnboundLocationException e) {
            UnboundLocationException unboundLocationException = e;
            UnboundLocationException unboundLocationException2 = unboundLocationException;
            unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 77, 3);
            throw unboundLocationException2;
        }
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        switch (moduleMethod2.selector) {
            case 1:
                return asColor(obj2);
            case 4:
                try {
                    return imageRead(Path.valueOf(obj2));
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th4 = th3;
                    new WrongType(classCastException, "image-read", 1, obj2);
                    throw th4;
                }
            case 5:
                try {
                    return Integer.valueOf(imageWidth((BufferedImage) obj2));
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th5 = th2;
                    new WrongType(classCastException2, "image-width", 1, obj2);
                    throw th5;
                }
            case 6:
                try {
                    return Integer.valueOf(imageHeight((BufferedImage) obj2));
                } catch (ClassCastException e3) {
                    ClassCastException classCastException3 = e3;
                    Throwable th6 = th;
                    new WrongType(classCastException3, "image-height", 1, obj2);
                    throw th6;
                }
            default:
                return super.apply1(moduleMethod2, obj2);
        }
    }

    static Object labelKeyword(Label label, String str, Object obj) {
        Object error$V;
        String obj2;
        Label instance = label;
        String name = str;
        Object value = obj;
        if (name == Lit1) {
            Label label2 = instance;
            Object obj3 = value;
            Object obj4 = obj3;
            if (obj3 == null) {
                obj2 = null;
            } else {
                obj2 = obj4.toString();
            }
            label2.setText(obj2);
            error$V = Values.empty;
        } else {
            Object[] objArr = new Object[2];
            objArr[0] = "unknown label attribute ~s";
            Object[] objArr2 = objArr;
            objArr2[1] = name;
            error$V = misc.error$V(Format.formatToString(0, objArr2), new Object[0]);
        }
        return error$V;
    }

    static void labelNonKeyword(Label instance, Object arg) {
        Object obj = arg;
        instance.setText(obj == null ? null : obj.toString());
    }

    public static Label Label(Object... objArr) {
        Label label;
        Throwable th;
        Throwable th2;
        Object[] args = objArr;
        new Label();
        Label instance = label;
        int num$Mnargs = args.length;
        int i = 0;
        while (i < num$Mnargs) {
            Object arg = args[i];
            if (arg instanceof Keyword) {
                Object obj = arg;
                Object obj2 = obj;
                try {
                    Object labelKeyword = labelKeyword(instance, ((Keyword) obj).getName(), args[i + 1]);
                    i += 2;
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th3 = th2;
                    new WrongType(classCastException, "gnu.expr.Keyword.getName()", 1, obj2);
                    throw th3;
                }
            } else if (arg instanceof KAttr) {
                Object obj3 = arg;
                Object obj4 = obj3;
                try {
                    KAttr attr = (KAttr) obj3;
                    Object labelKeyword2 = labelKeyword(instance, attr.getName(), attr.getObjectValue());
                    i++;
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th4 = th;
                    new WrongType(classCastException2, "attr", -2, obj4);
                    throw th4;
                }
            } else {
                labelNonKeyword(instance, arg);
                i++;
            }
        }
        return instance;
    }

    static Object textKeyword(Text text, String str, Object obj) {
        Object error$V;
        String obj2;
        Text instance = text;
        String name = str;
        Object value = obj;
        if (name == Lit1) {
            Text text2 = instance;
            Object obj3 = value;
            Object obj4 = obj3;
            if (obj3 == null) {
                obj2 = null;
            } else {
                obj2 = obj4.toString();
            }
            text2.setText(obj2);
            error$V = Values.empty;
        } else {
            Object[] objArr = new Object[2];
            objArr[0] = "unknown text attribute ~s";
            Object[] objArr2 = objArr;
            objArr2[1] = name;
            error$V = misc.error$V(Format.formatToString(0, objArr2), new Object[0]);
        }
        return error$V;
    }

    static void textNonKeyword(Text instance, Object arg) {
        Object obj = arg;
        instance.setText(obj == null ? null : obj.toString());
    }

    public static Text Text(Object... objArr) {
        Text text;
        Throwable th;
        Throwable th2;
        Object[] args = objArr;
        new Text();
        Text instance = text;
        int num$Mnargs = args.length;
        int i = 0;
        while (i < num$Mnargs) {
            Object arg = args[i];
            if (arg instanceof Keyword) {
                Object obj = arg;
                Object obj2 = obj;
                try {
                    Object textKeyword = textKeyword(instance, ((Keyword) obj).getName(), args[i + 1]);
                    i += 2;
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th3 = th2;
                    new WrongType(classCastException, "gnu.expr.Keyword.getName()", 1, obj2);
                    throw th3;
                }
            } else if (arg instanceof KAttr) {
                Object obj3 = arg;
                Object obj4 = obj3;
                try {
                    KAttr attr = (KAttr) obj3;
                    Object textKeyword2 = textKeyword(instance, attr.getName(), attr.getObjectValue());
                    i++;
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th4 = th;
                    new WrongType(classCastException2, "attr", -2, obj4);
                    throw th4;
                }
            } else {
                textNonKeyword(instance, arg);
                i++;
            }
        }
        return instance;
    }

    static Object boxKeyword(Box box, String str, Object obj) {
        Object error$V;
        Box instance = box;
        String name = str;
        Object value = obj;
        if (name == Lit2) {
            instance.setCellSpacing(value);
            error$V = Values.empty;
        } else {
            Object[] objArr = new Object[2];
            objArr[0] = "unknown box attribute ~s";
            Object[] objArr2 = objArr;
            objArr2[1] = name;
            error$V = misc.error$V(Format.formatToString(0, objArr2), new Object[0]);
        }
        return error$V;
    }

    static Model asModel(Object arg) {
        return Display.getInstance().coerceToModel(arg);
    }

    static void boxNonKeyword(Box box, Object arg) {
        box.add(asModel(arg));
    }

    public static Object Row(Object... objArr) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Object[] args = objArr;
        try {
            Object instance = Invoke.make.apply1(loc$$Lsgnu$Dtkawa$Dtmodels$DtRow$Gr.get());
            int num$Mnargs = args.length;
            int i = 0;
            while (i < num$Mnargs) {
                Object arg = args[i];
                if (arg instanceof Keyword) {
                    Object obj = instance;
                    Object obj2 = obj;
                    try {
                        Box box = (Box) obj;
                        Object obj3 = arg;
                        Object obj4 = obj3;
                        try {
                            Object boxKeyword = boxKeyword(box, ((Keyword) obj3).getName(), args[i + 1]);
                            i += 2;
                        } catch (ClassCastException e) {
                            ClassCastException classCastException = e;
                            Throwable th6 = th5;
                            new WrongType(classCastException, "gnu.expr.Keyword.getName()", 1, obj4);
                            throw th6;
                        }
                    } catch (ClassCastException e2) {
                        ClassCastException classCastException2 = e2;
                        Throwable th7 = th4;
                        new WrongType(classCastException2, "box-keyword", 0, obj2);
                        throw th7;
                    }
                } else if (arg instanceof KAttr) {
                    Object obj5 = arg;
                    Object obj6 = obj5;
                    try {
                        KAttr attr = (KAttr) obj5;
                        Object obj7 = instance;
                        Object obj8 = obj7;
                        try {
                            Object boxKeyword2 = boxKeyword((Box) obj7, attr.getName(), attr.getObjectValue());
                            i++;
                        } catch (ClassCastException e3) {
                            ClassCastException classCastException3 = e3;
                            Throwable th8 = th3;
                            new WrongType(classCastException3, "box-keyword", 0, obj8);
                            throw th8;
                        }
                    } catch (ClassCastException e4) {
                        ClassCastException classCastException4 = e4;
                        Throwable th9 = th2;
                        new WrongType(classCastException4, "attr", -2, obj6);
                        throw th9;
                    }
                } else {
                    Object obj9 = instance;
                    Object obj10 = obj9;
                    try {
                        boxNonKeyword((Box) obj9, arg);
                        i++;
                    } catch (ClassCastException e5) {
                        ClassCastException classCastException5 = e5;
                        Throwable th10 = th;
                        new WrongType(classCastException5, "box-non-keyword", 0, obj10);
                        throw th10;
                    }
                }
            }
            return instance;
        } catch (UnboundLocationException e6) {
            UnboundLocationException unboundLocationException = e6;
            UnboundLocationException unboundLocationException2 = unboundLocationException;
            unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 146, 25);
            throw unboundLocationException2;
        }
    }

    public static Object Column(Object... objArr) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        Throwable th5;
        Object[] args = objArr;
        try {
            Object instance = Invoke.make.apply1(loc$$Lsgnu$Dtkawa$Dtmodels$DtColumn$Gr.get());
            int num$Mnargs = args.length;
            int i = 0;
            while (i < num$Mnargs) {
                Object arg = args[i];
                if (arg instanceof Keyword) {
                    Object obj = instance;
                    Object obj2 = obj;
                    try {
                        Box box = (Box) obj;
                        Object obj3 = arg;
                        Object obj4 = obj3;
                        try {
                            Object boxKeyword = boxKeyword(box, ((Keyword) obj3).getName(), args[i + 1]);
                            i += 2;
                        } catch (ClassCastException e) {
                            ClassCastException classCastException = e;
                            Throwable th6 = th5;
                            new WrongType(classCastException, "gnu.expr.Keyword.getName()", 1, obj4);
                            throw th6;
                        }
                    } catch (ClassCastException e2) {
                        ClassCastException classCastException2 = e2;
                        Throwable th7 = th4;
                        new WrongType(classCastException2, "box-keyword", 0, obj2);
                        throw th7;
                    }
                } else if (arg instanceof KAttr) {
                    Object obj5 = arg;
                    Object obj6 = obj5;
                    try {
                        KAttr attr = (KAttr) obj5;
                        Object obj7 = instance;
                        Object obj8 = obj7;
                        try {
                            Object boxKeyword2 = boxKeyword((Box) obj7, attr.getName(), attr.getObjectValue());
                            i++;
                        } catch (ClassCastException e3) {
                            ClassCastException classCastException3 = e3;
                            Throwable th8 = th3;
                            new WrongType(classCastException3, "box-keyword", 0, obj8);
                            throw th8;
                        }
                    } catch (ClassCastException e4) {
                        ClassCastException classCastException4 = e4;
                        Throwable th9 = th2;
                        new WrongType(classCastException4, "attr", -2, obj6);
                        throw th9;
                    }
                } else {
                    Object obj9 = instance;
                    Object obj10 = obj9;
                    try {
                        boxNonKeyword((Box) obj9, arg);
                        i++;
                    } catch (ClassCastException e5) {
                        ClassCastException classCastException5 = e5;
                        Throwable th10 = th;
                        new WrongType(classCastException5, "box-non-keyword", 0, obj10);
                        throw th10;
                    }
                }
            }
            return instance;
        } catch (UnboundLocationException e6) {
            UnboundLocationException unboundLocationException = e6;
            UnboundLocationException unboundLocationException2 = unboundLocationException;
            unboundLocationException.setLine("/u2/home/jis/ai2-kawa/gnu/kawa/slib/gui.scm", 151, 25);
            throw unboundLocationException2;
        }
    }

    public static void setContent(Window window, Object pane) {
        window.setContent(pane);
    }

    public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
        Throwable th;
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        if (moduleMethod2.selector != 11) {
            return super.apply2(moduleMethod2, obj3, obj4);
        }
        try {
            setContent((Window) obj3, obj4);
            return Values.empty;
        } catch (ClassCastException e) {
            ClassCastException classCastException = e;
            Throwable th2 = th;
            new WrongType(classCastException, "set-content", 1, obj3);
            throw th2;
        }
    }

    public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj3 = obj;
        Object obj4 = obj2;
        CallContext callContext2 = callContext;
        if (moduleMethod2.selector != 11) {
            return super.match2(moduleMethod2, obj3, obj4, callContext2);
        }
        CallContext callContext3 = callContext2;
        Object obj5 = obj3;
        Object obj6 = obj5;
        if (!(obj5 instanceof Window)) {
            return -786431;
        }
        callContext3.value1 = obj6;
        callContext2.value2 = obj4;
        callContext2.proc = moduleMethod2;
        callContext2.f239pc = 2;
        return 0;
    }

    static Object windowKeyword(Window window, String str, Object obj) {
        Object error$V;
        Window instance = window;
        String name = str;
        Object value = obj;
        if (name == "title") {
            Object obj2 = value;
            instance.setTitle(obj2 == null ? null : obj2.toString());
            error$V = Values.empty;
        } else if (name == "content") {
            instance.setContent(value);
            error$V = Values.empty;
        } else if (name == "menubar") {
            instance.setMenuBar(value);
            error$V = Values.empty;
        } else {
            Object[] objArr = new Object[2];
            objArr[0] = "unknown window attribute ~s";
            Object[] objArr2 = objArr;
            objArr2[1] = name;
            error$V = misc.error$V(Format.formatToString(0, objArr2), new Object[0]);
        }
        return error$V;
    }

    static void windowNonKeyword(Window instance, Object arg) {
        instance.setContent(arg);
    }

    public static Window Window(Object... objArr) {
        Throwable th;
        Throwable th2;
        Object[] args = objArr;
        Window instance = Display.getInstance().makeWindow();
        int num$Mnargs = args.length;
        int i = 0;
        while (i < num$Mnargs) {
            Object arg = args[i];
            if (arg instanceof Keyword) {
                Object obj = arg;
                Object obj2 = obj;
                try {
                    Object windowKeyword = windowKeyword(instance, ((Keyword) obj).getName(), args[i + 1]);
                    i += 2;
                } catch (ClassCastException e) {
                    ClassCastException classCastException = e;
                    Throwable th3 = th2;
                    new WrongType(classCastException, "gnu.expr.Keyword.getName()", 1, obj2);
                    throw th3;
                }
            } else if (arg instanceof KAttr) {
                Object obj3 = arg;
                Object obj4 = obj3;
                try {
                    KAttr attr = (KAttr) obj3;
                    Object windowKeyword2 = windowKeyword(instance, attr.getName(), attr.getObjectValue());
                    i++;
                } catch (ClassCastException e2) {
                    ClassCastException classCastException2 = e2;
                    Throwable th4 = th;
                    new WrongType(classCastException2, "attr", -2, obj4);
                    throw th4;
                }
            } else {
                windowNonKeyword(instance, arg);
                i++;
            }
        }
        return instance;
    }

    public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object[] objArr2 = objArr;
        switch (moduleMethod2.selector) {
            case 2:
                return button(objArr2);
            case 3:
                return Button(objArr2);
            case 7:
                return Label(objArr2);
            case 8:
                return Text(objArr2);
            case 9:
                return Row(objArr2);
            case 10:
                return Column(objArr2);
            case 12:
                return Window(objArr2);
            default:
                return super.applyN(moduleMethod2, objArr2);
        }
    }
}

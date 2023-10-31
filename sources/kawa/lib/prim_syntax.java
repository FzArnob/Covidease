package kawa.lib;

import android.support.p000v4.app.FragmentTransaction;
import gnu.expr.Expression;
import gnu.expr.IfExp;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.expr.Special;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.math.IntNum;
import kawa.lang.Macro;
import kawa.lang.Quote;
import kawa.lang.SyntaxForms;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lang.SyntaxTemplate;
import kawa.lang.TemplateScope;
import kawa.standard.syntax_case;
import kawa.standard.syntax_error;
import kawa.standard.try_catch;

/* compiled from: prim_syntax.scm */
public class prim_syntax extends ModuleBody {
    public static final prim_syntax $instance;
    static final SimpleSymbol Lit0;
    static final SyntaxRules Lit1;
    static final SyntaxRules Lit10;
    static final SimpleSymbol Lit11;
    static final SyntaxRules Lit12;
    static final SimpleSymbol Lit13;
    static final SyntaxPattern Lit14;
    static final SyntaxTemplate Lit15;
    static final SyntaxTemplate Lit16;
    static final SyntaxPattern Lit17;
    static final SyntaxTemplate Lit18;
    static final SyntaxTemplate Lit19;
    static final SimpleSymbol Lit2;
    static final SyntaxTemplate Lit20;
    static final SyntaxPattern Lit21;
    static final SyntaxTemplate Lit22;
    static final SyntaxPattern Lit23;
    static final SyntaxTemplate Lit24;
    static final SimpleSymbol Lit25;
    static final SyntaxPattern Lit26;
    static final SyntaxTemplate Lit27;
    static final SyntaxTemplate Lit28;
    static final SimpleSymbol Lit29;
    static final SyntaxRules Lit3;
    static final SyntaxPattern Lit30;
    static final SyntaxTemplate Lit31;
    static final SyntaxTemplate Lit32;
    static final SyntaxTemplate Lit33;
    static final SyntaxPattern Lit34;
    static final SyntaxPattern Lit35;
    static final SyntaxTemplate Lit36;
    static final SyntaxTemplate Lit37;
    static final SyntaxTemplate Lit38;
    static final SyntaxPattern Lit39;
    static final SimpleSymbol Lit4;
    static final SyntaxTemplate Lit40;
    static final SyntaxTemplate Lit41;
    static final SyntaxTemplate Lit42;
    static final SyntaxPattern Lit43;
    static final SyntaxPattern Lit44;
    static final SimpleSymbol Lit45;
    static final SimpleSymbol Lit46;
    static final SimpleSymbol Lit47;
    static final SimpleSymbol Lit48;
    static final SimpleSymbol Lit49;
    static final SyntaxRules Lit5;
    static final SimpleSymbol Lit50;
    static final IntNum Lit51 = IntNum.make(9);
    static final IntNum Lit52 = IntNum.make(8);
    static final IntNum Lit53 = IntNum.make(5);
    static final IntNum Lit54 = IntNum.make(4);
    static final IntNum Lit55 = IntNum.make(1);
    static final IntNum Lit56 = IntNum.make(0);
    static final SimpleSymbol Lit57;
    static final SimpleSymbol Lit58;
    static final SimpleSymbol Lit6;
    static final SyntaxRules Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    public static final Macro define = Macro.make(Lit2, Lit3, $instance);
    public static final Macro define$Mnconstant = Macro.make(Lit6, Lit7, $instance);
    public static final Macro define$Mnprivate = Macro.make(Lit4, Lit5, $instance);
    public static final Macro define$Mnsyntax = Macro.make(Lit0, Lit1, $instance);

    /* renamed from: if */
    public static final Macro f271if;
    public static final Macro letrec;
    public static final Macro syntax$Mn$Grexpression = Macro.make(Lit9, Lit10, $instance);
    public static final Macro syntax$Mnbody$Mn$Grexpression = Macro.make(Lit11, Lit12, $instance);
    public static final ModuleMethod syntax$Mnerror;
    public static final Macro try$Mncatch;

    static {
        SimpleSymbol simpleSymbol;
        SimpleSymbol simpleSymbol2;
        SimpleSymbol simpleSymbol3;
        SimpleSymbol simpleSymbol4;
        SimpleSymbol simpleSymbol5;
        SimpleSymbol simpleSymbol6;
        SimpleSymbol simpleSymbol7;
        SimpleSymbol simpleSymbol8;
        SyntaxPattern syntaxPattern;
        SyntaxPattern syntaxPattern2;
        SyntaxTemplate syntaxTemplate;
        SyntaxTemplate syntaxTemplate2;
        SyntaxTemplate syntaxTemplate3;
        SyntaxPattern syntaxPattern3;
        SyntaxTemplate syntaxTemplate4;
        SyntaxTemplate syntaxTemplate5;
        SyntaxTemplate syntaxTemplate6;
        SyntaxPattern syntaxPattern4;
        SyntaxPattern syntaxPattern5;
        SyntaxTemplate syntaxTemplate7;
        SyntaxTemplate syntaxTemplate8;
        SimpleSymbol simpleSymbol9;
        SyntaxTemplate syntaxTemplate9;
        SyntaxPattern syntaxPattern6;
        SimpleSymbol simpleSymbol10;
        SyntaxTemplate syntaxTemplate10;
        SyntaxTemplate syntaxTemplate11;
        SyntaxPattern syntaxPattern7;
        SimpleSymbol simpleSymbol11;
        SyntaxTemplate syntaxTemplate12;
        SyntaxPattern syntaxPattern8;
        SyntaxTemplate syntaxTemplate13;
        SyntaxPattern syntaxPattern9;
        SyntaxTemplate syntaxTemplate14;
        SyntaxTemplate syntaxTemplate15;
        SyntaxTemplate syntaxTemplate16;
        SyntaxPattern syntaxPattern10;
        SyntaxTemplate syntaxTemplate17;
        SyntaxTemplate syntaxTemplate18;
        SyntaxPattern syntaxPattern11;
        SimpleSymbol simpleSymbol12;
        SyntaxRules syntaxRules;
        SimpleSymbol simpleSymbol13;
        SyntaxRule syntaxRule;
        SyntaxPattern syntaxPattern12;
        SimpleSymbol simpleSymbol14;
        SyntaxRules syntaxRules2;
        SimpleSymbol simpleSymbol15;
        SyntaxRule syntaxRule2;
        SyntaxPattern syntaxPattern13;
        SimpleSymbol simpleSymbol16;
        SimpleSymbol simpleSymbol17;
        SyntaxRules syntaxRules3;
        SimpleSymbol simpleSymbol18;
        SyntaxRule syntaxRule3;
        SyntaxPattern syntaxPattern14;
        SyntaxRule syntaxRule4;
        SyntaxPattern syntaxPattern15;
        SyntaxRule syntaxRule5;
        SyntaxPattern syntaxPattern16;
        SyntaxRule syntaxRule6;
        SyntaxPattern syntaxPattern17;
        SyntaxRule syntaxRule7;
        SyntaxPattern syntaxPattern18;
        SyntaxRules syntaxRules4;
        SimpleSymbol simpleSymbol19;
        SyntaxRule syntaxRule8;
        SyntaxPattern syntaxPattern19;
        SyntaxRule syntaxRule9;
        SyntaxPattern syntaxPattern20;
        SyntaxRule syntaxRule10;
        SyntaxPattern syntaxPattern21;
        SyntaxRule syntaxRule11;
        SyntaxPattern syntaxPattern22;
        SyntaxRule syntaxRule12;
        SyntaxPattern syntaxPattern23;
        SyntaxRules syntaxRules5;
        SimpleSymbol simpleSymbol20;
        SyntaxRule syntaxRule13;
        SyntaxPattern syntaxPattern24;
        SyntaxRule syntaxRule14;
        SyntaxPattern syntaxPattern25;
        SyntaxRule syntaxRule15;
        SyntaxPattern syntaxPattern26;
        SyntaxRule syntaxRule16;
        SyntaxPattern syntaxPattern27;
        SyntaxRule syntaxRule17;
        SyntaxPattern syntaxPattern28;
        SyntaxRules syntaxRules6;
        SimpleSymbol simpleSymbol21;
        SyntaxRule syntaxRule18;
        SyntaxPattern syntaxPattern29;
        SyntaxRule syntaxRule19;
        SyntaxPattern syntaxPattern30;
        SyntaxRule syntaxRule20;
        SyntaxPattern syntaxPattern31;
        SyntaxRule syntaxRule21;
        SyntaxPattern syntaxPattern32;
        prim_syntax prim_syntax;
        ModuleMethod moduleMethod;
        Procedure procedure;
        Procedure procedure2;
        Procedure procedure3;
        new SimpleSymbol("lambda");
        Lit58 = (SimpleSymbol) simpleSymbol.readResolve();
        new SimpleSymbol("%define-syntax");
        Lit57 = (SimpleSymbol) simpleSymbol2.readResolve();
        new SimpleSymbol("%define");
        Lit50 = (SimpleSymbol) simpleSymbol3.readResolve();
        new SimpleSymbol("::");
        Lit49 = (SimpleSymbol) simpleSymbol4.readResolve();
        new SimpleSymbol(LispLanguage.quasiquote_sym);
        Lit48 = (SimpleSymbol) simpleSymbol5.readResolve();
        new SimpleSymbol("kawa.lang.SyntaxForms");
        Lit47 = (SimpleSymbol) simpleSymbol6.readResolve();
        new SimpleSymbol("$lookup$");
        Lit46 = (SimpleSymbol) simpleSymbol7.readResolve();
        new SimpleSymbol("set!");
        Lit45 = (SimpleSymbol) simpleSymbol8.readResolve();
        new SyntaxPattern("\u001b", new Object[0], 4);
        Lit44 = syntaxPattern;
        new SyntaxPattern("\u001c\f\u001f\b#", new Object[0], 5);
        Lit43 = syntaxPattern2;
        new SyntaxTemplate("\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000", ":", new Object[0], 0);
        Lit42 = syntaxTemplate;
        SyntaxTemplate syntaxTemplate19 = syntaxTemplate2;
        new SyntaxTemplate("\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000", "\u0011\u0018\u0004\t\u001b\b3", new Object[]{Lit45}, 0);
        Lit41 = syntaxTemplate19;
        SyntaxTemplate syntaxTemplate20 = syntaxTemplate3;
        new SyntaxTemplate("\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000", "\t\u001b\t#\t+\u0018\u0004", new Object[]{PairWithPosition.make(Special.undefined, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/prim_syntax.scm", 471102)}, 0);
        Lit40 = syntaxTemplate20;
        new SyntaxPattern("L\f\u001f\f'\f/\f7\b;", new Object[0], 8);
        Lit39 = syntaxPattern3;
        new SyntaxTemplate("\u0001\u0001\u0000\u0001\u0001\u0000", "*", new Object[0], 0);
        Lit38 = syntaxTemplate4;
        SyntaxTemplate syntaxTemplate21 = syntaxTemplate5;
        new SyntaxTemplate("\u0001\u0001\u0000\u0001\u0001\u0000", "\u0011\u0018\u0004\t\u001b\b#", new Object[]{Lit45}, 0);
        Lit37 = syntaxTemplate21;
        SyntaxTemplate syntaxTemplate22 = syntaxTemplate6;
        new SyntaxTemplate("\u0001\u0001\u0000\u0001\u0001\u0000", "\t\u001b\u0018\u0004", new Object[]{PairWithPosition.make(Special.undefined, LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/prim_syntax.scm", 450594)}, 0);
        Lit36 = syntaxTemplate22;
        new SyntaxPattern(",\f\u001f\f'\b+", new Object[0], 6);
        Lit35 = syntaxPattern4;
        new SyntaxPattern("\b", new Object[0], 3);
        Lit34 = syntaxPattern5;
        new SyntaxTemplate("\u0001\u0001\u0000", "\u0012", new Object[0], 0);
        Lit33 = syntaxTemplate7;
        SyntaxTemplate syntaxTemplate23 = syntaxTemplate8;
        new SimpleSymbol("%let");
        new SyntaxTemplate("\u0001\u0001\u0000", "\u0018\u0004", new Object[]{PairWithPosition.make((SimpleSymbol) simpleSymbol9.readResolve(), LList.Empty, "/u2/home/jis/ai2-kawa/kawa/lib/prim_syntax.scm", 512011)}, 0);
        Lit32 = syntaxTemplate23;
        new SyntaxTemplate("\u0001\u0001\u0000", "\u000b", new Object[0], 0);
        Lit31 = syntaxTemplate9;
        new SyntaxPattern("\f\u0007\f\u000f\u0013", new Object[0], 3);
        Lit30 = syntaxPattern6;
        new SimpleSymbol("letrec");
        Lit29 = (SimpleSymbol) simpleSymbol10.readResolve();
        SyntaxTemplate syntaxTemplate24 = syntaxTemplate10;
        new SyntaxTemplate("\u0001\u0001\u0003\u0003\u0002", "(\b\u0015A\b\t\u0013\u0011\u0018\u0004\b\u001b\"", new Object[]{Lit49}, 1);
        Lit28 = syntaxTemplate24;
        new SyntaxTemplate("\u0001\u0001\u0003\u0003\u0002", "\u000b", new Object[0], 0);
        Lit27 = syntaxTemplate11;
        new SyntaxPattern("\f\u0007\f\u000f-\f\u0017\f\u001f#\u0010\u0018\b", new Object[0], 5);
        Lit26 = syntaxPattern7;
        new SimpleSymbol("try-catch");
        Lit25 = (SimpleSymbol) simpleSymbol11.readResolve();
        new SyntaxTemplate("\u0001\u0000", "\n", new Object[0], 0);
        Lit24 = syntaxTemplate12;
        new SyntaxPattern("\f\u0007\u000b", new Object[0], 2);
        Lit23 = syntaxPattern8;
        new SyntaxTemplate("\u0001\u0001\u0001\u0001\u0001\u0000", "#", new Object[0], 0);
        Lit22 = syntaxTemplate13;
        new SyntaxPattern("\f\u0007\f\u000f\f\u0017\f\u001f\f'+", new Object[0], 6);
        Lit21 = syntaxPattern9;
        new SyntaxTemplate("\u0001\u0001\u0001\u0001", "\u001b", new Object[0], 0);
        Lit20 = syntaxTemplate14;
        new SyntaxTemplate("\u0001\u0001\u0001\u0001", "\u0013", new Object[0], 0);
        Lit19 = syntaxTemplate15;
        new SyntaxTemplate("\u0001\u0001\u0001\u0001", "\u000b", new Object[0], 0);
        Lit18 = syntaxTemplate16;
        new SyntaxPattern("\f\u0007\f\u000f\f\u0017\f\u001f\b", new Object[0], 4);
        Lit17 = syntaxPattern10;
        new SyntaxTemplate("\u0001\u0001\u0001", "\u0013", new Object[0], 0);
        Lit16 = syntaxTemplate17;
        new SyntaxTemplate("\u0001\u0001\u0001", "\u000b", new Object[0], 0);
        Lit15 = syntaxTemplate18;
        new SyntaxPattern("\f\u0007\f\u000f\f\u0017\b", new Object[0], 3);
        Lit14 = syntaxPattern11;
        new SimpleSymbol("if");
        Lit13 = (SimpleSymbol) simpleSymbol12.readResolve();
        SyntaxRules syntaxRules7 = syntaxRules;
        Object[] objArr = new Object[1];
        Object[] objArr2 = objArr;
        Object[] objArr3 = objArr;
        new SimpleSymbol("syntax-body->expression");
        SimpleSymbol simpleSymbol22 = (SimpleSymbol) simpleSymbol13.readResolve();
        Lit11 = simpleSymbol22;
        objArr3[0] = simpleSymbol22;
        SyntaxRule[] syntaxRuleArr = new SyntaxRule[1];
        SyntaxRule[] syntaxRuleArr2 = syntaxRuleArr;
        SyntaxRule[] syntaxRuleArr3 = syntaxRuleArr;
        SyntaxRule syntaxRule22 = syntaxRule;
        new SyntaxPattern("\f\u0018\f\u0007\b", new Object[0], 1);
        SimpleSymbol simpleSymbol23 = Lit46;
        SimpleSymbol simpleSymbol24 = Lit47;
        SimpleSymbol simpleSymbol25 = Lit48;
        new SimpleSymbol("rewriteBody");
        new SyntaxRule(syntaxPattern12, "\u0001", "\u0011\u0018\u0004\b\u0003", new Object[]{PairWithPosition.make(simpleSymbol23, Pair.make(simpleSymbol24, Pair.make(Pair.make(simpleSymbol25, Pair.make((SimpleSymbol) simpleSymbol14.readResolve(), LList.Empty)), LList.Empty)), "/u2/home/jis/ai2-kawa/kawa/lib/prim_syntax.scm", 270343)}, 0);
        syntaxRuleArr3[0] = syntaxRule22;
        new SyntaxRules(objArr2, syntaxRuleArr2, 1);
        Lit12 = syntaxRules7;
        SyntaxRules syntaxRules8 = syntaxRules2;
        Object[] objArr4 = new Object[1];
        Object[] objArr5 = objArr4;
        Object[] objArr6 = objArr4;
        new SimpleSymbol("syntax->expression");
        SimpleSymbol simpleSymbol26 = (SimpleSymbol) simpleSymbol15.readResolve();
        Lit9 = simpleSymbol26;
        objArr6[0] = simpleSymbol26;
        SyntaxRule[] syntaxRuleArr4 = new SyntaxRule[1];
        SyntaxRule[] syntaxRuleArr5 = syntaxRuleArr4;
        SyntaxRule[] syntaxRuleArr6 = syntaxRuleArr4;
        SyntaxRule syntaxRule23 = syntaxRule2;
        new SyntaxPattern("\f\u0018\f\u0007\b", new Object[0], 1);
        SimpleSymbol simpleSymbol27 = Lit46;
        SimpleSymbol simpleSymbol28 = Lit47;
        SimpleSymbol simpleSymbol29 = Lit48;
        new SimpleSymbol("rewrite");
        new SyntaxRule(syntaxPattern13, "\u0001", "\u0011\u0018\u0004\b\u0003", new Object[]{PairWithPosition.make(simpleSymbol27, Pair.make(simpleSymbol28, Pair.make(Pair.make(simpleSymbol29, Pair.make((SimpleSymbol) simpleSymbol16.readResolve(), LList.Empty)), LList.Empty)), "/u2/home/jis/ai2-kawa/kawa/lib/prim_syntax.scm", 249863)}, 0);
        syntaxRuleArr6[0] = syntaxRule23;
        new SyntaxRules(objArr5, syntaxRuleArr5, 1);
        Lit10 = syntaxRules8;
        new SimpleSymbol("syntax-error");
        Lit8 = (SimpleSymbol) simpleSymbol17.readResolve();
        SyntaxRules syntaxRules9 = syntaxRules3;
        Object[] objArr7 = new Object[3];
        Object[] objArr8 = objArr7;
        Object[] objArr9 = objArr7;
        new SimpleSymbol("define-constant");
        SimpleSymbol simpleSymbol30 = (SimpleSymbol) simpleSymbol18.readResolve();
        Lit6 = simpleSymbol30;
        objArr9[0] = simpleSymbol30;
        Object[] objArr10 = objArr8;
        objArr10[1] = Lit49;
        Object[] objArr11 = objArr10;
        Object[] objArr12 = objArr11;
        objArr11[2] = Lit46;
        SyntaxRule[] syntaxRuleArr7 = new SyntaxRule[5];
        SyntaxRule[] syntaxRuleArr8 = syntaxRuleArr7;
        SyntaxRule[] syntaxRuleArr9 = syntaxRuleArr7;
        SyntaxRule syntaxRule24 = syntaxRule3;
        SyntaxPattern syntaxPattern33 = syntaxPattern14;
        Object[] objArr13 = new Object[2];
        objArr13[0] = Lit46;
        Object[] objArr14 = objArr13;
        objArr14[1] = Lit49;
        new SyntaxPattern("\f\u0018\\\f\u0002\f\u0007,\f\u000f\f\u0017\b\b\f\n\f\u001f\f'\b", objArr14, 5);
        Object[] objArr15 = new Object[3];
        objArr15[0] = Lit50;
        Object[] objArr16 = objArr15;
        objArr16[1] = Lit46;
        Object[] objArr17 = objArr16;
        objArr17[2] = Lit51;
        new SyntaxRule(syntaxPattern33, "\u0001\u0001\u0001\u0001\u0001", "\u0011\u0018\u0004Q\u0011\u0018\f\t\u0003\b\t\u000b\b\u0013\u0011\u0018\u0014\t\u001b\b#", objArr17, 0);
        syntaxRuleArr9[0] = syntaxRule24;
        SyntaxRule[] syntaxRuleArr10 = syntaxRuleArr8;
        SyntaxRule[] syntaxRuleArr11 = syntaxRuleArr10;
        SyntaxRule[] syntaxRuleArr12 = syntaxRuleArr10;
        SyntaxRule syntaxRule25 = syntaxRule4;
        SyntaxPattern syntaxPattern34 = syntaxPattern15;
        new SyntaxPattern("\f\u0018\\\f\u0002\f\u0007,\f\u000f\f\u0017\b\b\f\u001f\b", new Object[]{Lit46}, 4);
        Object[] objArr18 = new Object[4];
        objArr18[0] = Lit50;
        Object[] objArr19 = objArr18;
        objArr19[1] = Lit46;
        Object[] objArr20 = objArr19;
        objArr20[2] = Lit52;
        new SyntaxRule(syntaxPattern34, "\u0001\u0001\u0001\u0001", "\u0011\u0018\u0004Q\u0011\u0018\f\t\u0003\b\t\u000b\b\u0013\u0011\u0018\u0014\u0011\u0018\u001c\b\u001b", objArr20, 0);
        syntaxRuleArr12[1] = syntaxRule25;
        SyntaxRule[] syntaxRuleArr13 = syntaxRuleArr11;
        SyntaxRule[] syntaxRuleArr14 = syntaxRuleArr13;
        SyntaxRule[] syntaxRuleArr15 = syntaxRuleArr13;
        SyntaxRule syntaxRule26 = syntaxRule5;
        SyntaxPattern syntaxPattern35 = syntaxPattern16;
        new SyntaxPattern("\f\u0018\u001c\f\u0007\u000b\u0013", new Object[0], 3);
        Object[] objArr21 = new Object[3];
        objArr21[0] = Lit50;
        Object[] objArr22 = objArr21;
        objArr22[1] = IntNum.make(10);
        Object[] objArr23 = objArr22;
        objArr23[2] = Boolean.TRUE;
        new SyntaxRule(syntaxPattern35, "\u0001\u0000\u0000", "\u0011\u0018\u0004\t\u0003\u0011\u0018\f\u0011\u0018\u0014\t\n\u0012", objArr23, 0);
        syntaxRuleArr15[2] = syntaxRule26;
        SyntaxRule[] syntaxRuleArr16 = syntaxRuleArr14;
        SyntaxRule[] syntaxRuleArr17 = syntaxRuleArr16;
        SyntaxRule[] syntaxRuleArr18 = syntaxRuleArr16;
        SyntaxRule syntaxRule27 = syntaxRule6;
        SyntaxPattern syntaxPattern36 = syntaxPattern17;
        new SyntaxPattern("\f\u0018\f\u0007\f\u0002\f\u000f\f\u0017\b", new Object[]{Lit49}, 3);
        Object[] objArr24 = new Object[2];
        objArr24[0] = Lit50;
        Object[] objArr25 = objArr24;
        objArr25[1] = Lit51;
        new SyntaxRule(syntaxPattern36, "\u0001\u0001\u0001", "\u0011\u0018\u0004\t\u0003\u0011\u0018\f\t\u000b\b\u0013", objArr25, 0);
        syntaxRuleArr18[3] = syntaxRule27;
        SyntaxRule[] syntaxRuleArr19 = syntaxRuleArr17;
        SyntaxRule[] syntaxRuleArr20 = syntaxRuleArr19;
        SyntaxRule[] syntaxRuleArr21 = syntaxRuleArr19;
        SyntaxRule syntaxRule28 = syntaxRule7;
        SyntaxPattern syntaxPattern37 = syntaxPattern18;
        new SyntaxPattern("\f\u0018\f\u0007\f\u000f\b", new Object[0], 2);
        Object[] objArr26 = new Object[3];
        objArr26[0] = Lit50;
        Object[] objArr27 = objArr26;
        objArr27[1] = Lit52;
        new SyntaxRule(syntaxPattern37, "\u0001\u0001", "\u0011\u0018\u0004\t\u0003\u0011\u0018\f\u0011\u0018\u0014\b\u000b", objArr27, 0);
        syntaxRuleArr21[4] = syntaxRule28;
        new SyntaxRules(objArr12, syntaxRuleArr20, 5);
        Lit7 = syntaxRules9;
        SyntaxRules syntaxRules10 = syntaxRules4;
        Object[] objArr28 = new Object[3];
        Object[] objArr29 = objArr28;
        Object[] objArr30 = objArr28;
        new SimpleSymbol("define-private");
        SimpleSymbol simpleSymbol31 = (SimpleSymbol) simpleSymbol19.readResolve();
        Lit4 = simpleSymbol31;
        objArr30[0] = simpleSymbol31;
        Object[] objArr31 = objArr29;
        objArr31[1] = Lit49;
        Object[] objArr32 = objArr31;
        Object[] objArr33 = objArr32;
        objArr32[2] = Lit46;
        SyntaxRule[] syntaxRuleArr22 = new SyntaxRule[5];
        SyntaxRule[] syntaxRuleArr23 = syntaxRuleArr22;
        SyntaxRule[] syntaxRuleArr24 = syntaxRuleArr22;
        SyntaxRule syntaxRule29 = syntaxRule8;
        SyntaxPattern syntaxPattern38 = syntaxPattern19;
        Object[] objArr34 = new Object[2];
        objArr34[0] = Lit46;
        Object[] objArr35 = objArr34;
        objArr35[1] = Lit49;
        new SyntaxPattern("\f\u0018\\\f\u0002\f\u0007,\f\u000f\f\u0017\b\b\f\n\f\u001f\f'\b", objArr35, 5);
        Object[] objArr36 = new Object[3];
        objArr36[0] = Lit50;
        Object[] objArr37 = objArr36;
        objArr37[1] = Lit46;
        Object[] objArr38 = objArr37;
        objArr38[2] = Lit53;
        new SyntaxRule(syntaxPattern38, "\u0001\u0001\u0001\u0001\u0001", "\u0011\u0018\u0004Q\u0011\u0018\f\t\u0003\b\t\u000b\b\u0013\u0011\u0018\u0014\t\u001b\b#", objArr38, 0);
        syntaxRuleArr24[0] = syntaxRule29;
        SyntaxRule[] syntaxRuleArr25 = syntaxRuleArr23;
        SyntaxRule[] syntaxRuleArr26 = syntaxRuleArr25;
        SyntaxRule[] syntaxRuleArr27 = syntaxRuleArr25;
        SyntaxRule syntaxRule30 = syntaxRule9;
        SyntaxPattern syntaxPattern39 = syntaxPattern20;
        new SyntaxPattern("\f\u0018\\\f\u0002\f\u0007,\f\u000f\f\u0017\b\b\f\u001f\b", new Object[]{Lit46}, 4);
        Object[] objArr39 = new Object[4];
        objArr39[0] = Lit50;
        Object[] objArr40 = objArr39;
        objArr40[1] = Lit46;
        Object[] objArr41 = objArr40;
        objArr41[2] = Lit54;
        new SyntaxRule(syntaxPattern39, "\u0001\u0001\u0001\u0001", "\u0011\u0018\u0004Q\u0011\u0018\f\t\u0003\b\t\u000b\b\u0013\u0011\u0018\u0014\u0011\u0018\u001c\b\u001b", objArr41, 0);
        syntaxRuleArr27[1] = syntaxRule30;
        SyntaxRule[] syntaxRuleArr28 = syntaxRuleArr26;
        SyntaxRule[] syntaxRuleArr29 = syntaxRuleArr28;
        SyntaxRule[] syntaxRuleArr30 = syntaxRuleArr28;
        SyntaxRule syntaxRule31 = syntaxRule10;
        SyntaxPattern syntaxPattern40 = syntaxPattern21;
        new SyntaxPattern("\f\u0018\u001c\f\u0007\u000b\u0013", new Object[0], 3);
        Object[] objArr42 = new Object[3];
        objArr42[0] = Lit50;
        Object[] objArr43 = objArr42;
        objArr43[1] = IntNum.make(6);
        Object[] objArr44 = objArr43;
        objArr44[2] = Boolean.TRUE;
        new SyntaxRule(syntaxPattern40, "\u0001\u0000\u0000", "\u0011\u0018\u0004\t\u0003\u0011\u0018\f\u0011\u0018\u0014\t\n\u0012", objArr44, 0);
        syntaxRuleArr30[2] = syntaxRule31;
        SyntaxRule[] syntaxRuleArr31 = syntaxRuleArr29;
        SyntaxRule[] syntaxRuleArr32 = syntaxRuleArr31;
        SyntaxRule[] syntaxRuleArr33 = syntaxRuleArr31;
        SyntaxRule syntaxRule32 = syntaxRule11;
        SyntaxPattern syntaxPattern41 = syntaxPattern22;
        new SyntaxPattern("\f\u0018\f\u0007\f\u0002\f\u000f\f\u0017\b", new Object[]{Lit49}, 3);
        Object[] objArr45 = new Object[2];
        objArr45[0] = Lit50;
        Object[] objArr46 = objArr45;
        objArr46[1] = Lit53;
        new SyntaxRule(syntaxPattern41, "\u0001\u0001\u0001", "\u0011\u0018\u0004\t\u0003\u0011\u0018\f\t\u000b\b\u0013", objArr46, 0);
        syntaxRuleArr33[3] = syntaxRule32;
        SyntaxRule[] syntaxRuleArr34 = syntaxRuleArr32;
        SyntaxRule[] syntaxRuleArr35 = syntaxRuleArr34;
        SyntaxRule[] syntaxRuleArr36 = syntaxRuleArr34;
        SyntaxRule syntaxRule33 = syntaxRule12;
        SyntaxPattern syntaxPattern42 = syntaxPattern23;
        new SyntaxPattern("\f\u0018\f\u0007\f\u000f\b", new Object[0], 2);
        Object[] objArr47 = new Object[3];
        objArr47[0] = Lit50;
        Object[] objArr48 = objArr47;
        objArr48[1] = Lit54;
        new SyntaxRule(syntaxPattern42, "\u0001\u0001", "\u0011\u0018\u0004\t\u0003\u0011\u0018\f\u0011\u0018\u0014\b\u000b", objArr48, 0);
        syntaxRuleArr36[4] = syntaxRule33;
        new SyntaxRules(objArr33, syntaxRuleArr35, 5);
        Lit5 = syntaxRules10;
        SyntaxRules syntaxRules11 = syntaxRules5;
        Object[] objArr49 = new Object[3];
        Object[] objArr50 = objArr49;
        Object[] objArr51 = objArr49;
        new SimpleSymbol("define");
        SimpleSymbol simpleSymbol32 = (SimpleSymbol) simpleSymbol20.readResolve();
        Lit2 = simpleSymbol32;
        objArr51[0] = simpleSymbol32;
        Object[] objArr52 = objArr50;
        objArr52[1] = Lit49;
        Object[] objArr53 = objArr52;
        Object[] objArr54 = objArr53;
        objArr53[2] = Lit46;
        SyntaxRule[] syntaxRuleArr37 = new SyntaxRule[5];
        SyntaxRule[] syntaxRuleArr38 = syntaxRuleArr37;
        SyntaxRule[] syntaxRuleArr39 = syntaxRuleArr37;
        SyntaxRule syntaxRule34 = syntaxRule13;
        SyntaxPattern syntaxPattern43 = syntaxPattern24;
        Object[] objArr55 = new Object[2];
        objArr55[0] = Lit46;
        Object[] objArr56 = objArr55;
        objArr56[1] = Lit49;
        new SyntaxPattern("\f\u0018\\\f\u0002\f\u0007,\f\u000f\f\u0017\b\b\f\n\f\u001f\f'\b", objArr56, 5);
        Object[] objArr57 = new Object[3];
        objArr57[0] = Lit50;
        Object[] objArr58 = objArr57;
        objArr58[1] = Lit46;
        Object[] objArr59 = objArr58;
        objArr59[2] = Lit55;
        new SyntaxRule(syntaxPattern43, "\u0001\u0001\u0001\u0001\u0001", "\u0011\u0018\u0004Q\u0011\u0018\f\t\u0003\b\t\u000b\b\u0013\u0011\u0018\u0014\t\u001b\b#", objArr59, 0);
        syntaxRuleArr39[0] = syntaxRule34;
        SyntaxRule[] syntaxRuleArr40 = syntaxRuleArr38;
        SyntaxRule[] syntaxRuleArr41 = syntaxRuleArr40;
        SyntaxRule[] syntaxRuleArr42 = syntaxRuleArr40;
        SyntaxRule syntaxRule35 = syntaxRule14;
        SyntaxPattern syntaxPattern44 = syntaxPattern25;
        new SyntaxPattern("\f\u0018\\\f\u0002\f\u0007,\f\u000f\f\u0017\b\b\f\u001f\b", new Object[]{Lit46}, 4);
        Object[] objArr60 = new Object[4];
        objArr60[0] = Lit50;
        Object[] objArr61 = objArr60;
        objArr61[1] = Lit46;
        Object[] objArr62 = objArr61;
        objArr62[2] = Lit56;
        new SyntaxRule(syntaxPattern44, "\u0001\u0001\u0001\u0001", "\u0011\u0018\u0004Q\u0011\u0018\f\t\u0003\b\t\u000b\b\u0013\u0011\u0018\u0014\u0011\u0018\u001c\b\u001b", objArr62, 0);
        syntaxRuleArr42[1] = syntaxRule35;
        SyntaxRule[] syntaxRuleArr43 = syntaxRuleArr41;
        SyntaxRule[] syntaxRuleArr44 = syntaxRuleArr43;
        SyntaxRule[] syntaxRuleArr45 = syntaxRuleArr43;
        SyntaxRule syntaxRule36 = syntaxRule15;
        SyntaxPattern syntaxPattern45 = syntaxPattern26;
        new SyntaxPattern("\f\u0018\u001c\f\u0007\u000b\u0013", new Object[0], 3);
        Object[] objArr63 = new Object[3];
        objArr63[0] = Lit50;
        Object[] objArr64 = objArr63;
        objArr64[1] = IntNum.make(2);
        Object[] objArr65 = objArr64;
        objArr65[2] = Boolean.TRUE;
        new SyntaxRule(syntaxPattern45, "\u0001\u0000\u0000", "\u0011\u0018\u0004\t\u0003\u0011\u0018\f\u0011\u0018\u0014\t\n\u0012", objArr65, 0);
        syntaxRuleArr45[2] = syntaxRule36;
        SyntaxRule[] syntaxRuleArr46 = syntaxRuleArr44;
        SyntaxRule[] syntaxRuleArr47 = syntaxRuleArr46;
        SyntaxRule[] syntaxRuleArr48 = syntaxRuleArr46;
        SyntaxRule syntaxRule37 = syntaxRule16;
        SyntaxPattern syntaxPattern46 = syntaxPattern27;
        new SyntaxPattern("\f\u0018\f\u0007\f\u0002\f\u000f\f\u0017\b", new Object[]{Lit49}, 3);
        Object[] objArr66 = new Object[2];
        objArr66[0] = Lit50;
        Object[] objArr67 = objArr66;
        objArr67[1] = Lit55;
        new SyntaxRule(syntaxPattern46, "\u0001\u0001\u0001", "\u0011\u0018\u0004\t\u0003\u0011\u0018\f\t\u000b\b\u0013", objArr67, 0);
        syntaxRuleArr48[3] = syntaxRule37;
        SyntaxRule[] syntaxRuleArr49 = syntaxRuleArr47;
        SyntaxRule[] syntaxRuleArr50 = syntaxRuleArr49;
        SyntaxRule[] syntaxRuleArr51 = syntaxRuleArr49;
        SyntaxRule syntaxRule38 = syntaxRule17;
        SyntaxPattern syntaxPattern47 = syntaxPattern28;
        new SyntaxPattern("\f\u0018\f\u0007\f\u000f\b", new Object[0], 2);
        Object[] objArr68 = new Object[3];
        objArr68[0] = Lit50;
        Object[] objArr69 = objArr68;
        objArr69[1] = Lit56;
        new SyntaxRule(syntaxPattern47, "\u0001\u0001", "\u0011\u0018\u0004\t\u0003\u0011\u0018\f\u0011\u0018\u0014\b\u000b", objArr69, 0);
        syntaxRuleArr51[4] = syntaxRule38;
        new SyntaxRules(objArr54, syntaxRuleArr50, 5);
        Lit3 = syntaxRules11;
        SyntaxRules syntaxRules12 = syntaxRules6;
        Object[] objArr70 = new Object[2];
        Object[] objArr71 = objArr70;
        Object[] objArr72 = objArr70;
        new SimpleSymbol("define-syntax");
        SimpleSymbol simpleSymbol33 = (SimpleSymbol) simpleSymbol21.readResolve();
        Lit0 = simpleSymbol33;
        objArr72[0] = simpleSymbol33;
        Object[] objArr73 = objArr71;
        Object[] objArr74 = objArr73;
        objArr73[1] = Lit46;
        SyntaxRule[] syntaxRuleArr52 = new SyntaxRule[4];
        SyntaxRule[] syntaxRuleArr53 = syntaxRuleArr52;
        SyntaxRule[] syntaxRuleArr54 = syntaxRuleArr52;
        SyntaxRule syntaxRule39 = syntaxRule18;
        SyntaxPattern syntaxPattern48 = syntaxPattern29;
        new SyntaxPattern("\f\u0018l\\\f\u0002\f\u0007,\f\u000f\f\u0017\b\b\u001b#", new Object[]{Lit46}, 5);
        Object[] objArr75 = new Object[3];
        objArr75[0] = Lit57;
        Object[] objArr76 = objArr75;
        objArr76[1] = Lit46;
        Object[] objArr77 = objArr76;
        objArr77[2] = Lit58;
        new SyntaxRule(syntaxPattern48, "\u0001\u0001\u0001\u0000\u0000", "\u0011\u0018\u0004Q\u0011\u0018\f\t\u0003\b\t\u000b\b\u0013\b\u0011\u0018\u0014\t\u001a\"", objArr77, 0);
        syntaxRuleArr54[0] = syntaxRule39;
        SyntaxRule[] syntaxRuleArr55 = syntaxRuleArr53;
        SyntaxRule[] syntaxRuleArr56 = syntaxRuleArr55;
        SyntaxRule[] syntaxRuleArr57 = syntaxRuleArr55;
        SyntaxRule syntaxRule40 = syntaxRule19;
        SyntaxPattern syntaxPattern49 = syntaxPattern30;
        new SyntaxPattern("\f\u0018\\\f\u0002\f\u0007,\f\u000f\f\u0017\b\b\f\u001f\b", new Object[]{Lit46}, 4);
        Object[] objArr78 = new Object[2];
        objArr78[0] = Lit57;
        Object[] objArr79 = objArr78;
        objArr79[1] = Lit46;
        new SyntaxRule(syntaxPattern49, "\u0001\u0001\u0001\u0001", "\u0011\u0018\u0004Q\u0011\u0018\f\t\u0003\b\t\u000b\b\u0013\b\u001b", objArr79, 0);
        syntaxRuleArr57[1] = syntaxRule40;
        SyntaxRule[] syntaxRuleArr58 = syntaxRuleArr56;
        SyntaxRule[] syntaxRuleArr59 = syntaxRuleArr58;
        SyntaxRule[] syntaxRuleArr60 = syntaxRuleArr58;
        SyntaxRule syntaxRule41 = syntaxRule20;
        SyntaxPattern syntaxPattern50 = syntaxPattern31;
        new SyntaxPattern("\f\u0018\u001c\f\u0007\u000b\u0013", new Object[0], 3);
        Object[] objArr80 = new Object[2];
        objArr80[0] = Lit57;
        Object[] objArr81 = objArr80;
        objArr81[1] = Lit58;
        new SyntaxRule(syntaxPattern50, "\u0001\u0000\u0000", "\u0011\u0018\u0004\t\u0003\b\u0011\u0018\f\t\n\u0012", objArr81, 0);
        syntaxRuleArr60[2] = syntaxRule41;
        SyntaxRule[] syntaxRuleArr61 = syntaxRuleArr59;
        SyntaxRule[] syntaxRuleArr62 = syntaxRuleArr61;
        SyntaxRule[] syntaxRuleArr63 = syntaxRuleArr61;
        SyntaxRule syntaxRule42 = syntaxRule21;
        new SyntaxPattern("\f\u0018\f\u0007\f\u000f\b", new Object[0], 2);
        new SyntaxRule(syntaxPattern32, "\u0001\u0001", "\u0011\u0018\u0004\t\u0003\b\u000b", new Object[]{Lit57}, 0);
        syntaxRuleArr63[3] = syntaxRule42;
        new SyntaxRules(objArr74, syntaxRuleArr62, 5);
        Lit1 = syntaxRules12;
        new prim_syntax();
        $instance = prim_syntax;
        prim_syntax prim_syntax2 = $instance;
        new ModuleMethod(prim_syntax2, 1, Lit8, -4095);
        syntax$Mnerror = moduleMethod;
        SimpleSymbol simpleSymbol34 = Lit13;
        new ModuleMethod(prim_syntax2, 2, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        Procedure procedure4 = procedure;
        procedure4.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/prim_syntax.scm:69");
        f271if = Macro.make(simpleSymbol34, procedure4, $instance);
        SimpleSymbol simpleSymbol35 = Lit25;
        new ModuleMethod(prim_syntax2, 3, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        Procedure procedure5 = procedure2;
        procedure5.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/prim_syntax.scm:89");
        try$Mncatch = Macro.make(simpleSymbol35, procedure5, $instance);
        SimpleSymbol simpleSymbol36 = Lit29;
        new ModuleMethod(prim_syntax2, 4, (Object) null, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        Procedure procedure6 = procedure3;
        procedure6.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/prim_syntax.scm:98");
        letrec = Macro.make(simpleSymbol36, procedure6, $instance);
        $instance.run();
    }

    public prim_syntax() {
        ModuleInfo.register(this);
    }

    public final void run(CallContext $ctx) {
        Consumer consumer = $ctx.consumer;
    }

    public static Expression syntaxError(Object id, Object... msg) {
        return syntax_error.error(id, msg);
    }

    public Object applyN(ModuleMethod moduleMethod, Object[] objArr) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object[] objArr2 = objArr;
        if (moduleMethod2.selector != 1) {
            return super.applyN(moduleMethod2, objArr2);
        }
        Object obj = objArr2[0];
        int length = objArr2.length - 1;
        Object[] objArr3 = new Object[length];
        while (true) {
            length--;
            if (length < 0) {
                return syntaxError(obj, objArr3);
            }
            Object[] objArr4 = objArr3;
            objArr3 = objArr4;
            objArr4[length] = objArr2[length + 1];
        }
    }

    public int matchN(ModuleMethod moduleMethod, Object[] objArr, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object[] objArr2 = objArr;
        CallContext callContext2 = callContext;
        if (moduleMethod2.selector != 1) {
            return super.matchN(moduleMethod2, objArr2, callContext2);
        }
        callContext2.values = objArr2;
        callContext2.proc = moduleMethod2;
        callContext2.f239pc = 5;
        return 0;
    }

    static Object lambda1(Object obj) {
        Object x;
        Object[] objArr;
        Object[] objArr2;
        Object obj2;
        Object obj3;
        Object x2 = obj;
        Object[] allocVars = SyntaxPattern.allocVars(6, (Object[]) null);
        Object obj4 = x2;
        if (Lit14.match(x2, allocVars, 0)) {
            x = obj3;
            new IfExp(SyntaxForms.rewrite(Lit15.execute(allocVars, TemplateScope.make())), SyntaxForms.rewrite(Lit16.execute(allocVars, TemplateScope.make())), (Expression) null);
        } else if (Lit17.match(x2, allocVars, 0)) {
            x = obj2;
            new IfExp(SyntaxForms.rewrite(Lit18.execute(allocVars, TemplateScope.make())), SyntaxForms.rewrite(Lit19.execute(allocVars, TemplateScope.make())), SyntaxForms.rewrite(Lit20.execute(allocVars, TemplateScope.make())));
        } else if (Lit21.match(x2, allocVars, 0)) {
            Object execute = Lit22.execute(allocVars, TemplateScope.make());
            Object obj5 = "too many expressions for 'if'";
            Object obj6 = obj5;
            if (obj5 instanceof Object[]) {
                objArr2 = (Object[]) obj6;
            } else {
                Object obj7 = obj6;
                Object[] objArr3 = new Object[1];
                objArr2 = objArr3;
                objArr3[0] = obj7;
            }
            x = syntaxError(execute, objArr2);
        } else if (Lit23.match(x2, allocVars, 0)) {
            Object execute2 = Lit24.execute(allocVars, TemplateScope.make());
            Object obj8 = "too few expressions for 'if'";
            Object obj9 = obj8;
            if (obj8 instanceof Object[]) {
                objArr = (Object[]) obj9;
            } else {
                Object obj10 = obj9;
                Object[] objArr4 = new Object[1];
                objArr = objArr4;
                objArr4[0] = obj10;
            }
            x = syntaxError(execute2, objArr);
        } else {
            x = syntax_case.error("syntax-case", x2);
        }
        return x;
    }

    public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        CallContext callContext2 = callContext;
        switch (moduleMethod2.selector) {
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
            default:
                return super.match1(moduleMethod2, obj2, callContext2);
        }
    }

    static Object lambda2(Object obj) {
        Object x;
        Object x2 = obj;
        Object[] allocVars = SyntaxPattern.allocVars(5, (Object[]) null);
        Object obj2 = x2;
        if (Lit26.match(x2, allocVars, 0)) {
            x = try_catch.rewrite(Lit27.execute(allocVars, TemplateScope.make()), Lit28.execute(allocVars, TemplateScope.make()));
        } else {
            x = syntax_case.error("syntax-case", x2);
        }
        return x;
    }

    static Object lambda3(Object obj) {
        frame frame2;
        Object form;
        Object form2 = obj;
        new frame();
        frame frame3 = frame2;
        LList lList = LList.Empty;
        frame3.out$Mninits = LList.Empty;
        frame3.out$Mnbindings = lList;
        frame3.$unnamed$0 = SyntaxPattern.allocVars(3, (Object[]) null);
        Object obj2 = form2;
        if (Lit30.match(form2, frame3.$unnamed$0, 0)) {
            Object lambda4processBinding = frame3.lambda4processBinding(Lit31.execute(frame3.$unnamed$0, TemplateScope.make()));
            frame3.out$Mnbindings = LList.reverseInPlace(frame3.out$Mnbindings);
            frame3.out$Mninits = LList.reverseInPlace(frame3.out$Mninits);
            TemplateScope make = TemplateScope.make();
            Object[] objArr = new Object[2];
            objArr[0] = Lit32.execute(frame3.$unnamed$0, make);
            Object[] objArr2 = objArr;
            Object[] objArr3 = objArr2;
            Object[] objArr4 = objArr2;
            Object[] objArr5 = new Object[2];
            objArr5[0] = frame3.out$Mnbindings;
            Object[] objArr6 = objArr5;
            Object[] objArr7 = objArr6;
            Object[] objArr8 = objArr6;
            Object[] objArr9 = new Object[2];
            objArr9[0] = frame3.out$Mninits;
            Object[] objArr10 = objArr9;
            objArr10[1] = Lit33.execute(frame3.$unnamed$0, make);
            objArr8[1] = Quote.append$V(objArr10);
            objArr4[1] = Quote.consX$V(objArr7);
            form = Quote.append$V(objArr3);
        } else {
            form = syntax_case.error("syntax-case", form2);
        }
        return form;
    }

    public Object apply1(ModuleMethod moduleMethod, Object obj) {
        ModuleMethod moduleMethod2 = moduleMethod;
        Object obj2 = obj;
        switch (moduleMethod2.selector) {
            case 2:
                return lambda1(obj2);
            case 3:
                return lambda2(obj2);
            case 4:
                return lambda3(obj2);
            default:
                return super.apply1(moduleMethod2, obj2);
        }
    }

    /* compiled from: prim_syntax.scm */
    public class frame extends ModuleBody {
        Object[] $unnamed$0;
        Object out$Mnbindings;
        Object out$Mninits;

        public frame() {
        }

        public Object lambda4processBinding(Object obj) {
            Object error;
            Object[] objArr;
            Object[] objArr2;
            Object obj2;
            Object obj3;
            Object obj4;
            Object obj5;
            Object b = obj;
            Object[] allocVars = SyntaxPattern.allocVars(8, this.$unnamed$0);
            Object obj6 = b;
            if (prim_syntax.Lit34.match(b, allocVars, 0)) {
                error = Values.empty;
            } else if (prim_syntax.Lit35.match(b, allocVars, 0)) {
                new Pair(prim_syntax.Lit36.execute(allocVars, TemplateScope.make()), this.out$Mnbindings);
                this.out$Mnbindings = obj4;
                new Pair(prim_syntax.Lit37.execute(allocVars, TemplateScope.make()), this.out$Mninits);
                this.out$Mninits = obj5;
                error = lambda4processBinding(prim_syntax.Lit38.execute(allocVars, TemplateScope.make()));
            } else if (prim_syntax.Lit39.match(b, allocVars, 0)) {
                new Pair(prim_syntax.Lit40.execute(allocVars, TemplateScope.make()), this.out$Mnbindings);
                this.out$Mnbindings = obj2;
                new Pair(prim_syntax.Lit41.execute(allocVars, TemplateScope.make()), this.out$Mninits);
                this.out$Mninits = obj3;
                error = lambda4processBinding(prim_syntax.Lit42.execute(allocVars, TemplateScope.make()));
            } else if (prim_syntax.Lit43.match(b, allocVars, 0)) {
                Object obj7 = b;
                Object obj8 = "missing initializion in letrec";
                Object obj9 = obj8;
                if (obj8 instanceof Object[]) {
                    objArr2 = (Object[]) obj9;
                } else {
                    Object obj10 = obj9;
                    Object[] objArr3 = new Object[1];
                    objArr2 = objArr3;
                    objArr3[0] = obj10;
                }
                error = prim_syntax.syntaxError(obj7, objArr2);
            } else if (prim_syntax.Lit44.match(b, allocVars, 0)) {
                Object obj11 = b;
                Object obj12 = "invalid bindings syntax in letrec";
                Object obj13 = obj12;
                if (obj12 instanceof Object[]) {
                    objArr = (Object[]) obj13;
                } else {
                    Object obj14 = obj13;
                    Object[] objArr4 = new Object[1];
                    objArr = objArr4;
                    objArr4[0] = obj14;
                }
                error = prim_syntax.syntaxError(obj11, objArr);
            } else {
                error = syntax_case.error("syntax-case", b);
            }
            return error;
        }
    }
}

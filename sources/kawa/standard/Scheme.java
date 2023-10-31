package kawa.standard;

import com.google.appinventor.components.common.PropertyTypeConstants;
import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Language;
import gnu.expr.ReferenceExp;
import gnu.kawa.functions.Apply;
import gnu.kawa.functions.ApplyToArgs;
import gnu.kawa.functions.DisplayFormat;
import gnu.kawa.functions.GetNamedPart;
import gnu.kawa.functions.IsEq;
import gnu.kawa.functions.IsEqual;
import gnu.kawa.functions.IsEqv;
import gnu.kawa.functions.Map;
import gnu.kawa.functions.Not;
import gnu.kawa.functions.NumberCompare;
import gnu.kawa.functions.NumberPredicate;
import gnu.kawa.lispexpr.LangObjType;
import gnu.kawa.lispexpr.LangPrimType;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.kawa.lispexpr.LispReader;
import gnu.kawa.lispexpr.ReadTable;
import gnu.kawa.lispexpr.ReaderDispatch;
import gnu.kawa.lispexpr.ReaderDispatchMisc;
import gnu.kawa.lispexpr.ReaderParens;
import gnu.kawa.lispexpr.ReaderQuote;
import gnu.kawa.reflect.InstanceOf;
import gnu.kawa.servlet.HttpRequestContext;
import gnu.lists.AbstractFormat;
import gnu.mapping.CharArrayInPort;
import gnu.mapping.Environment;
import gnu.mapping.InPort;
import gnu.mapping.NamedLocation;
import gnu.mapping.Namespace;
import gnu.mapping.SimpleEnvironment;
import gnu.mapping.Symbol;
import gnu.mapping.WrappedException;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import kawa.lang.Eval;
import org.shaded.apache.http.cookie.ClientCookie;

public class Scheme extends LispLanguage {
    public static final Apply apply;
    static final Declaration applyFieldDecl = Declaration.getDeclarationFromStatic("kawa.standard.Scheme", "applyToArgs");
    public static final ApplyToArgs applyToArgs;
    public static LangPrimType booleanType;
    public static final AbstractFormat displayFormat;
    public static final Map forEach;
    public static final Scheme instance;
    public static final InstanceOf instanceOf;
    public static final IsEq isEq;
    public static final IsEqual isEqual;
    public static final IsEqv isEqv;
    public static final NumberPredicate isEven;
    public static final NumberPredicate isOdd;
    protected static final SimpleEnvironment kawaEnvironment = Environment.make("kawa-environment", r5Environment);
    public static final Map map;
    public static final Not not;
    public static final Environment nullEnvironment = Environment.make("null-environment");
    public static final NumberCompare numEqu = NumberCompare.make(instance, "=", 8);
    public static final NumberCompare numGEq = NumberCompare.make(instance, ">=", 24);
    public static final NumberCompare numGrt = NumberCompare.make(instance, ">", 16);
    public static final NumberCompare numLEq = NumberCompare.make(instance, "<=", 12);
    public static final NumberCompare numLss = NumberCompare.make(instance, "<", 4);
    public static final Environment r4Environment = Environment.make("r4rs-environment", nullEnvironment);
    public static final Environment r5Environment = Environment.make("r5rs-environment", r4Environment);
    static HashMap<Type, String> typeToStringMap;
    static HashMap<String, Type> types;
    public static final Namespace unitNamespace = Namespace.valueOf("http://kawa.gnu.org/unit", "unit");
    public static final AbstractFormat writeFormat;

    static {
        Scheme scheme;
        InstanceOf instanceOf2;
        Not not2;
        ApplyToArgs applyToArgs2;
        Apply apply2;
        IsEq isEq2;
        IsEqv isEqv2;
        IsEqual isEqual2;
        Map map2;
        Map map3;
        NumberPredicate numberPredicate;
        NumberPredicate numberPredicate2;
        AbstractFormat abstractFormat;
        AbstractFormat abstractFormat2;
        new Scheme(kawaEnvironment);
        instance = scheme;
        new InstanceOf(instance, GetNamedPart.INSTANCEOF_METHOD_NAME);
        instanceOf = instanceOf2;
        new Not(instance, "not");
        not = not2;
        new ApplyToArgs("apply-to-args", instance);
        applyToArgs = applyToArgs2;
        new Apply("apply", applyToArgs);
        apply = apply2;
        new IsEq(instance, "eq?");
        isEq = isEq2;
        new IsEqv(instance, "eqv?", isEq);
        isEqv = isEqv2;
        new IsEqual(instance, "equal?");
        isEqual = isEqual2;
        new Map(true, applyToArgs, applyFieldDecl, isEq);
        map = map2;
        new Map(false, applyToArgs, applyFieldDecl, isEq);
        forEach = map3;
        new NumberPredicate(instance, "odd?", 1);
        isOdd = numberPredicate;
        new NumberPredicate(instance, "even?", 2);
        isEven = numberPredicate2;
        instance.initScheme();
        int withServlets = HttpRequestContext.importServletDefinitions;
        if (withServlets > 0) {
            try {
                instance.loadClass(withServlets > 1 ? "gnu.kawa.servlet.servlets" : "gnu.kawa.servlet.HTTP");
            } catch (Throwable th) {
                Throwable th2 = th;
            }
        }
        new DisplayFormat(true, 'S');
        writeFormat = abstractFormat;
        new DisplayFormat(false, 'S');
        displayFormat = abstractFormat2;
    }

    public static Scheme getInstance() {
        return instance;
    }

    public static Environment builtin() {
        return kawaEnvironment;
    }

    private void initScheme() {
        this.environ = nullEnvironment;
        NamedLocation addLocation = this.environ.addLocation(LispLanguage.lookup_sym, (Object) null, getNamedPartLocation);
        defSntxStFld("lambda", "kawa.standard.SchemeCompilation", "lambda");
        defSntxStFld(LispLanguage.quote_sym, "kawa.lang.Quote", "plainQuote");
        defSntxStFld("%define", "kawa.standard.define", "defineRaw");
        defSntxStFld("define", "kawa.lib.prim_syntax");
        defSntxStFld("if", "kawa.lib.prim_syntax");
        defSntxStFld("set!", "kawa.standard.set_b", "set");
        defSntxStFld("cond", "kawa.lib.std_syntax");
        defSntxStFld("case", "kawa.lib.std_syntax");
        defSntxStFld("and", "kawa.lib.std_syntax");
        defSntxStFld("or", "kawa.lib.std_syntax");
        defSntxStFld("%let", "kawa.standard.let", "let");
        defSntxStFld("let", "kawa.lib.std_syntax");
        defSntxStFld("let*", "kawa.lib.std_syntax");
        defSntxStFld("letrec", "kawa.lib.prim_syntax");
        defSntxStFld("begin", "kawa.standard.begin", "begin");
        defSntxStFld("do", "kawa.lib.std_syntax");
        defSntxStFld("delay", "kawa.lib.std_syntax");
        defProcStFld("%make-promise", "kawa.lib.std_syntax");
        defSntxStFld(LispLanguage.quasiquote_sym, "kawa.lang.Quote", "quasiQuote");
        defSntxStFld("define-syntax", "kawa.lib.prim_syntax");
        defSntxStFld("let-syntax", "kawa.standard.let_syntax", "let_syntax");
        defSntxStFld("letrec-syntax", "kawa.standard.let_syntax", "letrec_syntax");
        defSntxStFld("syntax-rules", "kawa.standard.syntax_rules", "syntax_rules");
        nullEnvironment.setLocked();
        this.environ = r4Environment;
        defProcStFld("not", "kawa.standard.Scheme");
        defProcStFld("boolean?", "kawa.lib.misc");
        defProcStFld("eq?", "kawa.standard.Scheme", "isEq");
        defProcStFld("eqv?", "kawa.standard.Scheme", "isEqv");
        defProcStFld("equal?", "kawa.standard.Scheme", "isEqual");
        defProcStFld("pair?", "kawa.lib.lists");
        defProcStFld("cons", "kawa.lib.lists");
        defProcStFld("car", "kawa.lib.lists");
        defProcStFld("cdr", "kawa.lib.lists");
        defProcStFld("set-car!", "kawa.lib.lists");
        defProcStFld("set-cdr!", "kawa.lib.lists");
        defProcStFld("caar", "kawa.lib.lists");
        defProcStFld("cadr", "kawa.lib.lists");
        defProcStFld("cdar", "kawa.lib.lists");
        defProcStFld("cddr", "kawa.lib.lists");
        defProcStFld("caaar", "kawa.lib.lists");
        defProcStFld("caadr", "kawa.lib.lists");
        defProcStFld("cadar", "kawa.lib.lists");
        defProcStFld("caddr", "kawa.lib.lists");
        defProcStFld("cdaar", "kawa.lib.lists");
        defProcStFld("cdadr", "kawa.lib.lists");
        defProcStFld("cddar", "kawa.lib.lists");
        defProcStFld("cdddr", "kawa.lib.lists");
        defProcStFld("caaaar", "kawa.lib.lists");
        defProcStFld("caaadr", "kawa.lib.lists");
        defProcStFld("caadar", "kawa.lib.lists");
        defProcStFld("caaddr", "kawa.lib.lists");
        defProcStFld("cadaar", "kawa.lib.lists");
        defProcStFld("cadadr", "kawa.lib.lists");
        defProcStFld("caddar", "kawa.lib.lists");
        defProcStFld("cadddr", "kawa.lib.lists");
        defProcStFld("cdaaar", "kawa.lib.lists");
        defProcStFld("cdaadr", "kawa.lib.lists");
        defProcStFld("cdadar", "kawa.lib.lists");
        defProcStFld("cdaddr", "kawa.lib.lists");
        defProcStFld("cddaar", "kawa.lib.lists");
        defProcStFld("cddadr", "kawa.lib.lists");
        defProcStFld("cdddar", "kawa.lib.lists");
        defProcStFld("cddddr", "kawa.lib.lists");
        defProcStFld("null?", "kawa.lib.lists");
        defProcStFld("list?", "kawa.lib.lists");
        defProcStFld("length", "kawa.lib.lists");
        defProcStFld("append", "kawa.standard.append", "append");
        defProcStFld("reverse", "kawa.lib.lists");
        defProcStFld("reverse!", "kawa.lib.lists");
        defProcStFld("list-tail", "kawa.lib.lists");
        defProcStFld("list-ref", "kawa.lib.lists");
        defProcStFld("memq", "kawa.lib.lists");
        defProcStFld("memv", "kawa.lib.lists");
        defProcStFld("member", "kawa.lib.lists");
        defProcStFld("assq", "kawa.lib.lists");
        defProcStFld("assv", "kawa.lib.lists");
        defProcStFld("assoc", "kawa.lib.lists");
        defProcStFld("symbol?", "kawa.lib.misc");
        defProcStFld("symbol->string", "kawa.lib.misc");
        defProcStFld("string->symbol", "kawa.lib.misc");
        defProcStFld("symbol=?", "kawa.lib.misc");
        defProcStFld("symbol-local-name", "kawa.lib.misc");
        defProcStFld("symbol-namespace", "kawa.lib.misc");
        defProcStFld("symbol-namespace-uri", "kawa.lib.misc");
        defProcStFld("symbol-prefix", "kawa.lib.misc");
        defProcStFld("namespace-uri", "kawa.lib.misc");
        defProcStFld("namespace-prefix", "kawa.lib.misc");
        defProcStFld("number?", "kawa.lib.numbers");
        defProcStFld("quantity?", "kawa.lib.numbers");
        defProcStFld("complex?", "kawa.lib.numbers");
        defProcStFld("real?", "kawa.lib.numbers");
        defProcStFld("rational?", "kawa.lib.numbers");
        defProcStFld("integer?", "kawa.lib.numbers");
        defProcStFld("exact?", "kawa.lib.numbers");
        defProcStFld("inexact?", "kawa.lib.numbers");
        defProcStFld("=", "kawa.standard.Scheme", "numEqu");
        defProcStFld("<", "kawa.standard.Scheme", "numLss");
        defProcStFld(">", "kawa.standard.Scheme", "numGrt");
        defProcStFld("<=", "kawa.standard.Scheme", "numLEq");
        defProcStFld(">=", "kawa.standard.Scheme", "numGEq");
        defProcStFld("zero?", "kawa.lib.numbers");
        defProcStFld("positive?", "kawa.lib.numbers");
        defProcStFld("negative?", "kawa.lib.numbers");
        defProcStFld("odd?", "kawa.standard.Scheme", "isOdd");
        defProcStFld("even?", "kawa.standard.Scheme", "isEven");
        defProcStFld("max", "kawa.lib.numbers");
        defProcStFld("min", "kawa.lib.numbers");
        defProcStFld("+", "gnu.kawa.functions.AddOp", "$Pl");
        defProcStFld("-", "gnu.kawa.functions.AddOp", "$Mn");
        defProcStFld("*", "gnu.kawa.functions.MultiplyOp", "$St");
        defProcStFld("/", "gnu.kawa.functions.DivideOp", "$Sl");
        defProcStFld("abs", "kawa.lib.numbers");
        defProcStFld("quotient", "gnu.kawa.functions.DivideOp", "quotient");
        defProcStFld("remainder", "gnu.kawa.functions.DivideOp", "remainder");
        defProcStFld("modulo", "gnu.kawa.functions.DivideOp", "modulo");
        defProcStFld("div", "gnu.kawa.functions.DivideOp", "div");
        defProcStFld("mod", "gnu.kawa.functions.DivideOp", "mod");
        defProcStFld("div0", "gnu.kawa.functions.DivideOp", "div0");
        defProcStFld("mod0", "gnu.kawa.functions.DivideOp", "mod0");
        defProcStFld("div-and-mod", "kawa.lib.numbers");
        defProcStFld("div0-and-mod0", "kawa.lib.numbers");
        defProcStFld("gcd", "kawa.lib.numbers");
        defProcStFld("lcm", "kawa.lib.numbers");
        defProcStFld("numerator", "kawa.lib.numbers");
        defProcStFld("denominator", "kawa.lib.numbers");
        defProcStFld("floor", "kawa.lib.numbers");
        defProcStFld("ceiling", "kawa.lib.numbers");
        defProcStFld("truncate", "kawa.lib.numbers");
        defProcStFld("round", "kawa.lib.numbers");
        defProcStFld("rationalize", "kawa.lib.numbers");
        defProcStFld("exp", "kawa.lib.numbers");
        defProcStFld("log", "kawa.lib.numbers");
        defProcStFld("sin", "kawa.lib.numbers");
        defProcStFld("cos", "kawa.lib.numbers");
        defProcStFld("tan", "kawa.lib.numbers");
        defProcStFld("asin", "kawa.lib.numbers");
        defProcStFld("acos", "kawa.lib.numbers");
        defProcStFld("atan", "kawa.lib.numbers");
        defProcStFld("sqrt", "kawa.lib.numbers");
        defProcStFld("expt", "kawa.standard.expt");
        defProcStFld("make-rectangular", "kawa.lib.numbers");
        defProcStFld("make-polar", "kawa.lib.numbers");
        defProcStFld("real-part", "kawa.lib.numbers");
        defProcStFld("imag-part", "kawa.lib.numbers");
        defProcStFld("magnitude", "kawa.lib.numbers");
        defProcStFld("angle", "kawa.lib.numbers");
        defProcStFld("inexact", "kawa.lib.numbers");
        defProcStFld("exact", "kawa.lib.numbers");
        defProcStFld("exact->inexact", "kawa.lib.numbers");
        defProcStFld("inexact->exact", "kawa.lib.numbers");
        defProcStFld("number->string", "kawa.lib.numbers");
        defProcStFld("string->number", "kawa.lib.numbers");
        defProcStFld("char?", "kawa.lib.characters");
        defProcStFld("char=?", "kawa.lib.characters");
        defProcStFld("char<?", "kawa.lib.characters");
        defProcStFld("char>?", "kawa.lib.characters");
        defProcStFld("char<=?", "kawa.lib.characters");
        defProcStFld("char>=?", "kawa.lib.characters");
        defProcStFld("char-ci=?", "kawa.lib.rnrs.unicode");
        defProcStFld("char-ci<?", "kawa.lib.rnrs.unicode");
        defProcStFld("char-ci>?", "kawa.lib.rnrs.unicode");
        defProcStFld("char-ci<=?", "kawa.lib.rnrs.unicode");
        defProcStFld("char-ci>=?", "kawa.lib.rnrs.unicode");
        defProcStFld("char-alphabetic?", "kawa.lib.rnrs.unicode");
        defProcStFld("char-numeric?", "kawa.lib.rnrs.unicode");
        defProcStFld("char-whitespace?", "kawa.lib.rnrs.unicode");
        defProcStFld("char-upper-case?", "kawa.lib.rnrs.unicode");
        defProcStFld("char-lower-case?", "kawa.lib.rnrs.unicode");
        defProcStFld("char-title-case?", "kawa.lib.rnrs.unicode");
        defProcStFld("char->integer", "kawa.lib.characters");
        defProcStFld("integer->char", "kawa.lib.characters");
        defProcStFld("char-upcase", "kawa.lib.rnrs.unicode");
        defProcStFld("char-downcase", "kawa.lib.rnrs.unicode");
        defProcStFld("char-titlecase", "kawa.lib.rnrs.unicode");
        defProcStFld("char-foldcase", "kawa.lib.rnrs.unicode");
        defProcStFld("char-general-category", "kawa.lib.rnrs.unicode");
        defProcStFld("string?", "kawa.lib.strings");
        defProcStFld("make-string", "kawa.lib.strings");
        defProcStFld("string-length", "kawa.lib.strings");
        defProcStFld("string-ref", "kawa.lib.strings");
        defProcStFld("string-set!", "kawa.lib.strings");
        defProcStFld("string=?", "kawa.lib.strings");
        defProcStFld("string<?", "kawa.lib.strings");
        defProcStFld("string>?", "kawa.lib.strings");
        defProcStFld("string<=?", "kawa.lib.strings");
        defProcStFld("string>=?", "kawa.lib.strings");
        defProcStFld("string-ci=?", "kawa.lib.rnrs.unicode");
        defProcStFld("string-ci<?", "kawa.lib.rnrs.unicode");
        defProcStFld("string-ci>?", "kawa.lib.rnrs.unicode");
        defProcStFld("string-ci<=?", "kawa.lib.rnrs.unicode");
        defProcStFld("string-ci>=?", "kawa.lib.rnrs.unicode");
        defProcStFld("string-normalize-nfd", "kawa.lib.rnrs.unicode");
        defProcStFld("string-normalize-nfkd", "kawa.lib.rnrs.unicode");
        defProcStFld("string-normalize-nfc", "kawa.lib.rnrs.unicode");
        defProcStFld("string-normalize-nfkc", "kawa.lib.rnrs.unicode");
        defProcStFld("substring", "kawa.lib.strings");
        defProcStFld("string-append", "kawa.lib.strings");
        defProcStFld("string-append/shared", "kawa.lib.strings");
        defProcStFld("string->list", "kawa.lib.strings");
        defProcStFld("list->string", "kawa.lib.strings");
        defProcStFld("string-copy", "kawa.lib.strings");
        defProcStFld("string-fill!", "kawa.lib.strings");
        defProcStFld("vector?", "kawa.lib.vectors");
        defProcStFld("make-vector", "kawa.lib.vectors");
        defProcStFld("vector-length", "kawa.lib.vectors");
        defProcStFld("vector-ref", "kawa.lib.vectors");
        defProcStFld("vector-set!", "kawa.lib.vectors");
        defProcStFld("list->vector", "kawa.lib.vectors");
        defProcStFld("vector->list", "kawa.lib.vectors");
        defProcStFld("vector-fill!", "kawa.lib.vectors");
        defProcStFld("vector-append", "kawa.standard.vector_append", "vectorAppend");
        defProcStFld("values-append", "gnu.kawa.functions.AppendValues", "appendValues");
        defProcStFld("procedure?", "kawa.lib.misc");
        defProcStFld("apply", "kawa.standard.Scheme", "apply");
        defProcStFld("map", "kawa.standard.Scheme", "map");
        defProcStFld("for-each", "kawa.standard.Scheme", "forEach");
        defProcStFld("call-with-current-continuation", "gnu.kawa.functions.CallCC", "callcc");
        defProcStFld("call/cc", "kawa.standard.callcc", "callcc");
        defProcStFld("force", "kawa.lib.misc");
        defProcStFld("call-with-input-file", "kawa.lib.ports");
        defProcStFld("call-with-output-file", "kawa.lib.ports");
        defProcStFld("input-port?", "kawa.lib.ports");
        defProcStFld("output-port?", "kawa.lib.ports");
        defProcStFld("current-input-port", "kawa.lib.ports");
        defProcStFld("current-output-port", "kawa.lib.ports");
        defProcStFld("with-input-from-file", "kawa.lib.ports");
        defProcStFld("with-output-to-file", "kawa.lib.ports");
        defProcStFld("open-input-file", "kawa.lib.ports");
        defProcStFld("open-output-file", "kawa.lib.ports");
        defProcStFld("close-input-port", "kawa.lib.ports");
        defProcStFld("close-output-port", "kawa.lib.ports");
        defProcStFld("read", "kawa.lib.ports");
        defProcStFld("read-line", "kawa.lib.ports");
        defProcStFld("read-char", "kawa.standard.readchar", "readChar");
        defProcStFld("peek-char", "kawa.standard.readchar", "peekChar");
        defProcStFld("eof-object?", "kawa.lib.ports");
        defProcStFld("char-ready?", "kawa.lib.ports");
        defProcStFld("write", "kawa.lib.ports");
        defProcStFld("display", "kawa.lib.ports");
        defProcStFld("print-as-xml", "gnu.xquery.lang.XQuery", "writeFormat");
        defProcStFld("write-char", "kawa.lib.ports");
        defProcStFld("newline", "kawa.lib.ports");
        defProcStFld("load", "kawa.standard.load", "load");
        defProcStFld("load-relative", "kawa.standard.load", "loadRelative");
        defProcStFld("transcript-off", "kawa.lib.ports");
        defProcStFld("transcript-on", "kawa.lib.ports");
        defProcStFld("call-with-input-string", "kawa.lib.ports");
        defProcStFld("open-input-string", "kawa.lib.ports");
        defProcStFld("open-output-string", "kawa.lib.ports");
        defProcStFld("get-output-string", "kawa.lib.ports");
        defProcStFld("call-with-output-string", "kawa.lib.ports");
        defProcStFld("force-output", "kawa.lib.ports");
        defProcStFld("port-line", "kawa.lib.ports");
        defProcStFld("set-port-line!", "kawa.lib.ports");
        defProcStFld("port-column", "kawa.lib.ports");
        defProcStFld("current-error-port", "kawa.lib.ports");
        defProcStFld("input-port-line-number", "kawa.lib.ports");
        defProcStFld("set-input-port-line-number!", "kawa.lib.ports");
        defProcStFld("input-port-column-number", "kawa.lib.ports");
        defProcStFld("input-port-read-state", "kawa.lib.ports");
        defProcStFld("default-prompter", "kawa.lib.ports");
        defProcStFld("input-port-prompter", "kawa.lib.ports");
        defProcStFld("set-input-port-prompter!", "kawa.lib.ports");
        defProcStFld("base-uri", "kawa.lib.misc");
        defProcStFld("%syntax-error", "kawa.standard.syntax_error", "syntax_error");
        defProcStFld("syntax-error", "kawa.lib.prim_syntax");
        r4Environment.setLocked();
        this.environ = r5Environment;
        defProcStFld("values", "kawa.lib.misc");
        defProcStFld("call-with-values", "kawa.standard.call_with_values", "callWithValues");
        defSntxStFld("let-values", "kawa.lib.syntax");
        defSntxStFld("let*-values", "kawa.lib.syntax");
        defSntxStFld("case-lambda", "kawa.lib.syntax");
        defSntxStFld("receive", "kawa.lib.syntax");
        defProcStFld("eval", "kawa.lang.Eval");
        defProcStFld("repl", "kawa.standard.SchemeCompilation", "repl");
        defProcStFld("scheme-report-environment", "kawa.lib.misc");
        defProcStFld("null-environment", "kawa.lib.misc");
        defProcStFld("interaction-environment", "kawa.lib.misc");
        defProcStFld("dynamic-wind", "kawa.lib.misc");
        r5Environment.setLocked();
        this.environ = kawaEnvironment;
        defSntxStFld("define-private", "kawa.lib.prim_syntax");
        defSntxStFld("define-constant", "kawa.lib.prim_syntax");
        defSntxStFld("define-autoload", "kawa.standard.define_autoload", "define_autoload");
        defSntxStFld("define-autoloads-from-file", "kawa.standard.define_autoload", "define_autoloads_from_file");
        defProcStFld("exit", "kawa.lib.rnrs.programs");
        defProcStFld("command-line", "kawa.lib.rnrs.programs");
        defProcStFld("bitwise-arithmetic-shift", "gnu.kawa.functions.BitwiseOp", "ashift");
        defProcStFld("arithmetic-shift", "gnu.kawa.functions.BitwiseOp", "ashift");
        defProcStFld("ash", "gnu.kawa.functions.BitwiseOp", "ashift");
        defProcStFld("bitwise-arithmetic-shift-left", "gnu.kawa.functions.BitwiseOp", "ashiftl");
        defProcStFld("bitwise-arithmetic-shift-right", "gnu.kawa.functions.BitwiseOp", "ashiftr");
        defProcStFld("bitwise-and", "gnu.kawa.functions.BitwiseOp", "and");
        defProcStFld("logand", "gnu.kawa.functions.BitwiseOp", "and");
        defProcStFld("bitwise-ior", "gnu.kawa.functions.BitwiseOp", "ior");
        defProcStFld("logior", "gnu.kawa.functions.BitwiseOp", "ior");
        defProcStFld("bitwise-xor", "gnu.kawa.functions.BitwiseOp", "xor");
        defProcStFld("logxor", "gnu.kawa.functions.BitwiseOp", "xor");
        defProcStFld("bitwise-if", "kawa.lib.numbers");
        defProcStFld("bitwise-not", "gnu.kawa.functions.BitwiseOp", "not");
        defProcStFld("lognot", "gnu.kawa.functions.BitwiseOp", "not");
        defProcStFld("logop", "kawa.lib.numbers");
        defProcStFld("bitwise-bit-set?", "kawa.lib.numbers");
        defProcStFld("logbit?", "kawa.lib.numbers", Compilation.mangleNameIfNeeded("bitwise-bit-set?"));
        defProcStFld("logtest", "kawa.lib.numbers");
        defProcStFld("bitwise-bit-count", "kawa.lib.numbers");
        defProcStFld("logcount", "kawa.lib.numbers");
        defProcStFld("bitwise-copy-bit", "kawa.lib.numbers");
        defProcStFld("bitwise-copy-bit-field", "kawa.lib.numbers");
        defProcStFld("bitwise-bit-field", "kawa.lib.numbers");
        defProcStFld("bit-extract", "kawa.lib.numbers", Compilation.mangleNameIfNeeded("bitwise-bit-field"));
        defProcStFld("bitwise-length", "kawa.lib.numbers");
        defProcStFld("integer-length", "kawa.lib.numbers", "bitwise$Mnlength");
        defProcStFld("bitwise-first-bit-set", "kawa.lib.numbers");
        defProcStFld("bitwise-rotate-bit-field", "kawa.lib.numbers");
        defProcStFld("bitwise-reverse-bit-field", "kawa.lib.numbers");
        defProcStFld("string-upcase!", "kawa.lib.strings");
        defProcStFld("string-downcase!", "kawa.lib.strings");
        defProcStFld("string-capitalize!", "kawa.lib.strings");
        defProcStFld("string-upcase", "kawa.lib.rnrs.unicode");
        defProcStFld("string-downcase", "kawa.lib.rnrs.unicode");
        defProcStFld("string-titlecase", "kawa.lib.rnrs.unicode");
        defProcStFld("string-foldcase", "kawa.lib.rnrs.unicode");
        defProcStFld("string-capitalize", "kawa.lib.strings");
        defSntxStFld("primitive-virtual-method", "kawa.standard.prim_method", "virtual_method");
        defSntxStFld("primitive-static-method", "kawa.standard.prim_method", "static_method");
        defSntxStFld("primitive-interface-method", "kawa.standard.prim_method", "interface_method");
        defSntxStFld("primitive-constructor", "kawa.lib.reflection");
        defSntxStFld("primitive-op1", "kawa.standard.prim_method", "op1");
        defSntxStFld("primitive-get-field", "kawa.lib.reflection");
        defSntxStFld("primitive-set-field", "kawa.lib.reflection");
        defSntxStFld("primitive-get-static", "kawa.lib.reflection");
        defSntxStFld("primitive-set-static", "kawa.lib.reflection");
        defSntxStFld("primitive-array-new", "kawa.lib.reflection");
        defSntxStFld("primitive-array-get", "kawa.lib.reflection");
        defSntxStFld("primitive-array-set", "kawa.lib.reflection");
        defSntxStFld("primitive-array-length", "kawa.lib.reflection");
        defProcStFld("subtype?", "kawa.lib.reflection");
        defProcStFld("primitive-throw", "kawa.standard.prim_throw", "primitiveThrow");
        defSntxStFld("try-finally", "kawa.lib.syntax");
        defSntxStFld("try-catch", "kawa.lib.prim_syntax");
        defProcStFld("throw", "kawa.standard.throw_name", "throwName");
        defProcStFld("catch", "kawa.lib.system");
        defProcStFld("error", "kawa.lib.misc");
        defProcStFld("as", "gnu.kawa.functions.Convert", "as");
        defProcStFld(GetNamedPart.INSTANCEOF_METHOD_NAME, "kawa.standard.Scheme", "instanceOf");
        defSntxStFld("synchronized", "kawa.lib.syntax");
        defSntxStFld("object", "kawa.standard.object", "objectSyntax");
        defSntxStFld("define-class", "kawa.standard.define_class", "define_class");
        defSntxStFld("define-simple-class", "kawa.standard.define_class", "define_simple_class");
        defSntxStFld("this", "kawa.standard.thisRef", "thisSyntax");
        defProcStFld("make", "gnu.kawa.reflect.Invoke", "make");
        defProcStFld("slot-ref", "gnu.kawa.reflect.SlotGet", "slotRef");
        defProcStFld("slot-set!", "gnu.kawa.reflect.SlotSet", "set$Mnfield$Ex");
        defProcStFld("field", "gnu.kawa.reflect.SlotGet");
        defProcStFld("class-methods", "gnu.kawa.reflect.ClassMethods", "classMethods");
        defProcStFld("static-field", "gnu.kawa.reflect.SlotGet", "staticField");
        defProcStFld("invoke", "gnu.kawa.reflect.Invoke", "invoke");
        defProcStFld("invoke-static", "gnu.kawa.reflect.Invoke", "invokeStatic");
        defProcStFld("invoke-special", "gnu.kawa.reflect.Invoke", "invokeSpecial");
        defSntxStFld("define-macro", "kawa.lib.syntax");
        defSntxStFld("%define-macro", "kawa.standard.define_syntax", "define_macro");
        defSntxStFld("define-syntax-case", "kawa.lib.syntax");
        defSntxStFld("syntax-case", "kawa.standard.syntax_case", "syntax_case");
        defSntxStFld("%define-syntax", "kawa.standard.define_syntax", "define_syntax");
        defSntxStFld("syntax", "kawa.standard.syntax", "syntax");
        defSntxStFld("quasisyntax", "kawa.standard.syntax", "quasiSyntax");
        defProcStFld("syntax-object->datum", "kawa.lib.std_syntax");
        defProcStFld("datum->syntax-object", "kawa.lib.std_syntax");
        defProcStFld("syntax->expression", "kawa.lib.prim_syntax");
        defProcStFld("syntax-body->expression", "kawa.lib.prim_syntax");
        defProcStFld("generate-temporaries", "kawa.lib.std_syntax");
        defSntxStFld("with-syntax", "kawa.lib.std_syntax");
        defProcStFld("identifier?", "kawa.lib.std_syntax");
        defProcStFld("free-identifier=?", "kawa.lib.std_syntax");
        defProcStFld("syntax-source", "kawa.lib.std_syntax");
        defProcStFld("syntax-line", "kawa.lib.std_syntax");
        defProcStFld("syntax-column", "kawa.lib.std_syntax");
        defSntxStFld("begin-for-syntax", "kawa.lib.std_syntax");
        defSntxStFld("define-for-syntax", "kawa.lib.std_syntax");
        defSntxStFld("include", "kawa.lib.misc_syntax");
        defSntxStFld("include-relative", "kawa.lib.misc_syntax");
        defProcStFld("file-exists?", "kawa.lib.files");
        defProcStFld("file-directory?", "kawa.lib.files");
        defProcStFld("file-readable?", "kawa.lib.files");
        defProcStFld("file-writable?", "kawa.lib.files");
        defProcStFld("delete-file", "kawa.lib.files");
        defProcStFld("system-tmpdir", "kawa.lib.files");
        defProcStFld("make-temporary-file", "kawa.lib.files");
        defProcStFld("rename-file", "kawa.lib.files");
        defProcStFld("copy-file", "kawa.lib.files");
        defProcStFld("create-directory", "kawa.lib.files");
        defProcStFld("->pathname", "kawa.lib.files");
        define("port-char-encoding", Boolean.TRUE);
        define("symbol-read-case", "P");
        defProcStFld("system", "kawa.lib.system");
        defProcStFld("make-process", "kawa.lib.system");
        defProcStFld("tokenize-string-to-string-array", "kawa.lib.system");
        defProcStFld("tokenize-string-using-shell", "kawa.lib.system");
        defProcStFld("command-parse", "kawa.lib.system");
        defProcStFld("process-command-line-assignments", "kawa.lib.system");
        defProcStFld("record-accessor", "kawa.lib.reflection");
        defProcStFld("record-modifier", "kawa.lib.reflection");
        defProcStFld("record-predicate", "kawa.lib.reflection");
        defProcStFld("record-constructor", "kawa.lib.reflection");
        defProcStFld("make-record-type", "kawa.lib.reflection");
        defProcStFld("record-type-descriptor", "kawa.lib.reflection");
        defProcStFld("record-type-name", "kawa.lib.reflection");
        defProcStFld("record-type-field-names", "kawa.lib.reflection");
        defProcStFld("record?", "kawa.lib.reflection");
        defSntxStFld("define-record-type", "gnu.kawa.slib.DefineRecordType");
        defSntxStFld("when", "kawa.lib.syntax");
        defSntxStFld("unless", "kawa.lib.syntax");
        defSntxStFld("fluid-let", "kawa.standard.fluid_let", "fluid_let");
        defSntxStFld("constant-fold", "kawa.standard.constant_fold", "constant_fold");
        defProcStFld("make-parameter", "kawa.lib.parameters");
        defSntxStFld("parameterize", "kawa.lib.parameters");
        defProcStFld("compile-file", "kawa.lib.system");
        defProcStFld("environment-bound?", "kawa.lib.misc");
        defProcStFld("scheme-implementation-version", "kawa.lib.misc");
        defProcStFld("scheme-window", "kawa.lib.windows");
        defSntxStFld("define-procedure", "kawa.lib.std_syntax");
        defProcStFld("add-procedure-properties", "kawa.lib.misc");
        defProcStFld("make-procedure", "gnu.kawa.functions.MakeProcedure", "makeProcedure");
        defProcStFld("procedure-property", "kawa.lib.misc");
        defProcStFld("set-procedure-property!", "kawa.lib.misc");
        defSntxStFld("provide", "kawa.lib.misc_syntax");
        defSntxStFld("test-begin", "kawa.lib.misc_syntax");
        defProcStFld("quantity->number", "kawa.lib.numbers");
        defProcStFld("quantity->unit", "kawa.lib.numbers");
        defProcStFld("make-quantity", "kawa.lib.numbers");
        defSntxStFld("define-namespace", "gnu.kawa.lispexpr.DefineNamespace", "define_namespace");
        defSntxStFld("define-xml-namespace", "gnu.kawa.lispexpr.DefineNamespace", "define_xml_namespace");
        defSntxStFld("define-private-namespace", "gnu.kawa.lispexpr.DefineNamespace", "define_private_namespace");
        defSntxStFld("define-unit", "kawa.standard.define_unit", "define_unit");
        defSntxStFld("define-base-unit", "kawa.standard.define_unit", "define_base_unit");
        defProcStFld("duration", "kawa.lib.numbers");
        defProcStFld("gentemp", "kawa.lib.misc");
        defSntxStFld("defmacro", "kawa.lib.syntax");
        defProcStFld("setter", "gnu.kawa.functions.Setter", "setter");
        defSntxStFld("resource-url", "kawa.lib.misc_syntax");
        defSntxStFld("module-uri", "kawa.lib.misc_syntax");
        defSntxStFld("future", "kawa.lib.thread");
        defProcStFld("sleep", "kawa.lib.thread");
        defProcStFld("runnable", "kawa.lib.thread");
        defSntxStFld("trace", "kawa.lib.trace");
        defSntxStFld("untrace", "kawa.lib.trace");
        defSntxStFld("disassemble", "kawa.lib.trace");
        defProcStFld("format", "gnu.kawa.functions.Format");
        defProcStFld("parse-format", "gnu.kawa.functions.ParseFormat", "parseFormat");
        defProcStFld("make-element", "gnu.kawa.xml.MakeElement", "makeElement");
        defProcStFld("make-attribute", "gnu.kawa.xml.MakeAttribute", "makeAttribute");
        defProcStFld("map-values", "gnu.kawa.functions.ValuesMap", "valuesMap");
        defProcStFld("children", "gnu.kawa.xml.Children", "children");
        defProcStFld("attributes", "gnu.kawa.xml.Attributes");
        defProcStFld("unescaped-data", "gnu.kawa.xml.MakeUnescapedData", "unescapedData");
        defProcStFld("keyword?", "kawa.lib.keywords");
        defProcStFld("keyword->string", "kawa.lib.keywords");
        defProcStFld("string->keyword", "kawa.lib.keywords");
        defSntxStFld("location", "kawa.standard.location", "location");
        defSntxStFld("define-alias", "kawa.standard.define_alias", "define_alias");
        defSntxStFld("define-variable", "kawa.standard.define_variable", "define_variable");
        defSntxStFld("define-member-alias", "kawa.standard.define_member_alias", "define_member_alias");
        defSntxStFld("define-enum", "gnu.kawa.slib.enums");
        defSntxStFld("import", "kawa.lib.syntax");
        defSntxStFld("require", "kawa.standard.require", "require");
        defSntxStFld("module-name", "kawa.standard.module_name", "module_name");
        defSntxStFld("module-extends", "kawa.standard.module_extends", "module_extends");
        defSntxStFld("module-implements", "kawa.standard.module_implements", "module_implements");
        defSntxStFld("module-static", "kawa.standard.module_static", "module_static");
        defSntxStFld("module-export", "kawa.standard.export", "module_export");
        defSntxStFld("export", "kawa.standard.export", "export");
        defSntxStFld("module-compile-options", "kawa.standard.module_compile_options", "module_compile_options");
        defSntxStFld("with-compile-options", "kawa.standard.with_compile_options", "with_compile_options");
        defProcStFld("array?", "kawa.lib.arrays");
        defProcStFld("array-rank", "kawa.lib.arrays");
        defProcStFld("make-array", "kawa.lib.arrays");
        defProcStFld("array", "kawa.lib.arrays");
        defProcStFld("array-start", "kawa.lib.arrays");
        defProcStFld("array-end", "kawa.lib.arrays");
        defProcStFld("shape", "kawa.lib.arrays");
        defProcStFld("array-ref", "gnu.kawa.functions.ArrayRef", "arrayRef");
        defProcStFld("array-set!", "gnu.kawa.functions.ArraySet", "arraySet");
        defProcStFld("share-array", "kawa.lib.arrays");
        defProcStFld("s8vector?", "kawa.lib.uniform");
        defProcStFld("make-s8vector", "kawa.lib.uniform");
        defProcStFld("s8vector", "kawa.lib.uniform");
        defProcStFld("s8vector-length", "kawa.lib.uniform");
        defProcStFld("s8vector-ref", "kawa.lib.uniform");
        defProcStFld("s8vector-set!", "kawa.lib.uniform");
        defProcStFld("s8vector->list", "kawa.lib.uniform");
        defProcStFld("list->s8vector", "kawa.lib.uniform");
        defProcStFld("u8vector?", "kawa.lib.uniform");
        defProcStFld("make-u8vector", "kawa.lib.uniform");
        defProcStFld("u8vector", "kawa.lib.uniform");
        defProcStFld("u8vector-length", "kawa.lib.uniform");
        defProcStFld("u8vector-ref", "kawa.lib.uniform");
        defProcStFld("u8vector-set!", "kawa.lib.uniform");
        defProcStFld("u8vector->list", "kawa.lib.uniform");
        defProcStFld("list->u8vector", "kawa.lib.uniform");
        defProcStFld("s16vector?", "kawa.lib.uniform");
        defProcStFld("make-s16vector", "kawa.lib.uniform");
        defProcStFld("s16vector", "kawa.lib.uniform");
        defProcStFld("s16vector-length", "kawa.lib.uniform");
        defProcStFld("s16vector-ref", "kawa.lib.uniform");
        defProcStFld("s16vector-set!", "kawa.lib.uniform");
        defProcStFld("s16vector->list", "kawa.lib.uniform");
        defProcStFld("list->s16vector", "kawa.lib.uniform");
        defProcStFld("u16vector?", "kawa.lib.uniform");
        defProcStFld("make-u16vector", "kawa.lib.uniform");
        defProcStFld("u16vector", "kawa.lib.uniform");
        defProcStFld("u16vector-length", "kawa.lib.uniform");
        defProcStFld("u16vector-ref", "kawa.lib.uniform");
        defProcStFld("u16vector-set!", "kawa.lib.uniform");
        defProcStFld("u16vector->list", "kawa.lib.uniform");
        defProcStFld("list->u16vector", "kawa.lib.uniform");
        defProcStFld("s32vector?", "kawa.lib.uniform");
        defProcStFld("make-s32vector", "kawa.lib.uniform");
        defProcStFld("s32vector", "kawa.lib.uniform");
        defProcStFld("s32vector-length", "kawa.lib.uniform");
        defProcStFld("s32vector-ref", "kawa.lib.uniform");
        defProcStFld("s32vector-set!", "kawa.lib.uniform");
        defProcStFld("s32vector->list", "kawa.lib.uniform");
        defProcStFld("list->s32vector", "kawa.lib.uniform");
        defProcStFld("u32vector?", "kawa.lib.uniform");
        defProcStFld("make-u32vector", "kawa.lib.uniform");
        defProcStFld("u32vector", "kawa.lib.uniform");
        defProcStFld("u32vector-length", "kawa.lib.uniform");
        defProcStFld("u32vector-ref", "kawa.lib.uniform");
        defProcStFld("u32vector-set!", "kawa.lib.uniform");
        defProcStFld("u32vector->list", "kawa.lib.uniform");
        defProcStFld("list->u32vector", "kawa.lib.uniform");
        defProcStFld("s64vector?", "kawa.lib.uniform");
        defProcStFld("make-s64vector", "kawa.lib.uniform");
        defProcStFld("s64vector", "kawa.lib.uniform");
        defProcStFld("s64vector-length", "kawa.lib.uniform");
        defProcStFld("s64vector-ref", "kawa.lib.uniform");
        defProcStFld("s64vector-set!", "kawa.lib.uniform");
        defProcStFld("s64vector->list", "kawa.lib.uniform");
        defProcStFld("list->s64vector", "kawa.lib.uniform");
        defProcStFld("u64vector?", "kawa.lib.uniform");
        defProcStFld("make-u64vector", "kawa.lib.uniform");
        defProcStFld("u64vector", "kawa.lib.uniform");
        defProcStFld("u64vector-length", "kawa.lib.uniform");
        defProcStFld("u64vector-ref", "kawa.lib.uniform");
        defProcStFld("u64vector-set!", "kawa.lib.uniform");
        defProcStFld("u64vector->list", "kawa.lib.uniform");
        defProcStFld("list->u64vector", "kawa.lib.uniform");
        defProcStFld("f32vector?", "kawa.lib.uniform");
        defProcStFld("make-f32vector", "kawa.lib.uniform");
        defProcStFld("f32vector", "kawa.lib.uniform");
        defProcStFld("f32vector-length", "kawa.lib.uniform");
        defProcStFld("f32vector-ref", "kawa.lib.uniform");
        defProcStFld("f32vector-set!", "kawa.lib.uniform");
        defProcStFld("f32vector->list", "kawa.lib.uniform");
        defProcStFld("list->f32vector", "kawa.lib.uniform");
        defProcStFld("f64vector?", "kawa.lib.uniform");
        defProcStFld("make-f64vector", "kawa.lib.uniform");
        defProcStFld("f64vector", "kawa.lib.uniform");
        defProcStFld("f64vector-length", "kawa.lib.uniform");
        defProcStFld("f64vector-ref", "kawa.lib.uniform");
        defProcStFld("f64vector-set!", "kawa.lib.uniform");
        defProcStFld("f64vector->list", "kawa.lib.uniform");
        defProcStFld("list->f64vector", "kawa.lib.uniform");
        defSntxStFld("cut", "gnu.kawa.slib.cut");
        defSntxStFld("cute", "gnu.kawa.slib.cut");
        defSntxStFld("cond-expand", "kawa.lib.syntax");
        defSntxStFld("%cond-expand", "kawa.lib.syntax");
        defAliasStFld("*print-base*", "gnu.kawa.functions.DisplayFormat", "outBase");
        defAliasStFld("*print-radix*", "gnu.kawa.functions.DisplayFormat", "outRadix");
        defAliasStFld("*print-right-margin*", "gnu.text.PrettyWriter", "lineLengthLoc");
        defAliasStFld("*print-miser-width*", "gnu.text.PrettyWriter", "miserWidthLoc");
        defAliasStFld("html", "gnu.kawa.xml.XmlNamespace", "HTML");
        defAliasStFld("unit", "kawa.standard.Scheme", "unitNamespace");
        defAliasStFld(ClientCookie.PATH_ATTR, "gnu.kawa.lispexpr.LangObjType", "pathType");
        defAliasStFld("filepath", "gnu.kawa.lispexpr.LangObjType", "filepathType");
        defAliasStFld("URI", "gnu.kawa.lispexpr.LangObjType", "URIType");
        defProcStFld("resolve-uri", "kawa.lib.files");
        defAliasStFld("vector", "gnu.kawa.lispexpr.LangObjType", "vectorType");
        defAliasStFld(PropertyTypeConstants.PROPERTY_TYPE_STRING, "gnu.kawa.lispexpr.LangObjType", "stringType");
        defAliasStFld("list", "gnu.kawa.lispexpr.LangObjType", "listType");
        defAliasStFld("regex", "gnu.kawa.lispexpr.LangObjType", "regexType");
        defProcStFld("path?", "kawa.lib.files");
        defProcStFld("filepath?", "kawa.lib.files");
        defProcStFld("URI?", "kawa.lib.files");
        defProcStFld("absolute-path?", "kawa.lib.files");
        defProcStFld("path-scheme", "kawa.lib.files");
        defProcStFld("path-authority", "kawa.lib.files");
        defProcStFld("path-user-info", "kawa.lib.files");
        defProcStFld("path-host", "kawa.lib.files");
        defProcStFld("path-port", "kawa.lib.files");
        defProcStFld("path-file", "kawa.lib.files");
        defProcStFld("path-parent", "kawa.lib.files");
        defProcStFld("path-directory", "kawa.lib.files");
        defProcStFld("path-last", "kawa.lib.files");
        defProcStFld("path-extension", "kawa.lib.files");
        defProcStFld("path-fragment", "kawa.lib.files");
        defProcStFld("path-query", "kawa.lib.files");
        kawaEnvironment.setLocked();
    }

    public Scheme() {
        this.environ = kawaEnvironment;
        this.userEnv = getNewEnvironment();
    }

    protected Scheme(Environment env) {
        this.environ = env;
    }

    public String getName() {
        return "Scheme";
    }

    public static Object eval(String string, Environment env) {
        InPort inPort;
        new CharArrayInPort(string);
        return eval(inPort, env);
    }

    public static Object eval(InPort inPort, Environment environment) {
        SourceMessages sourceMessages;
        Throwable th;
        Throwable th2;
        StringBuilder sb;
        Throwable th3;
        StringBuilder sb2;
        Throwable th4;
        InPort port = inPort;
        Environment env = environment;
        new SourceMessages();
        SourceMessages messages = sourceMessages;
        try {
            Object body = ReaderParens.readList((LispReader) Language.getDefaultLanguage().getLexer(port, messages), 0, 1, -1);
            if (!messages.seenErrors()) {
                return Eval.evalBody(body, env, messages);
            }
            Throwable th5 = th4;
            new SyntaxException(messages);
            throw th5;
        } catch (SyntaxException e) {
            SyntaxException e2 = e;
            Throwable th6 = th3;
            new StringBuilder();
            new RuntimeException(sb2.append("eval: errors while compiling:\n").append(e2.getMessages().toString(20)).toString());
            throw th6;
        } catch (IOException e3) {
            IOException e4 = e3;
            Throwable th7 = th2;
            new StringBuilder();
            new RuntimeException(sb.append("eval: I/O exception: ").append(e4.toString()).toString());
            throw th7;
        } catch (RuntimeException e5) {
            throw e5;
        } catch (Error e6) {
            throw e6;
        } catch (Throwable th8) {
            Throwable ex = th8;
            Throwable th9 = th;
            new WrappedException(ex);
            throw th9;
        }
    }

    public static Object eval(Object sexpr, Environment env) {
        Throwable th;
        try {
            return Eval.eval(sexpr, env);
        } catch (RuntimeException e) {
            throw e;
        } catch (Error e2) {
            throw e2;
        } catch (Throwable th2) {
            Throwable ex = th2;
            Throwable th3 = th;
            new WrappedException(ex);
            throw th3;
        }
    }

    public AbstractFormat getFormat(boolean readable) {
        return readable ? writeFormat : displayFormat;
    }

    public int getNamespaceOf(Declaration declaration) {
        Declaration declaration2 = declaration;
        return 3;
    }

    public static Type getTypeValue(Expression exp) {
        return getInstance().getTypeFor(exp);
    }

    static synchronized HashMap<String, Type> getTypeMap() {
        HashMap<String, Type> hashMap;
        LangPrimType langPrimType;
        HashMap<String, Type> hashMap2;
        synchronized (Scheme.class) {
            if (types == null) {
                new LangPrimType(Type.booleanType, getInstance());
                booleanType = langPrimType;
                new HashMap();
                types = hashMap2;
                Type put = types.put("void", LangPrimType.voidType);
                Type put2 = types.put("int", LangPrimType.intType);
                Type put3 = types.put("char", LangPrimType.charType);
                Type put4 = types.put(PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN, booleanType);
                Type put5 = types.put("byte", LangPrimType.byteType);
                Type put6 = types.put("short", LangPrimType.shortType);
                Type put7 = types.put("long", LangPrimType.longType);
                Type put8 = types.put(PropertyTypeConstants.PROPERTY_TYPE_FLOAT, LangPrimType.floatType);
                Type put9 = types.put("double", LangPrimType.doubleType);
                Type put10 = types.put("never-returns", Type.neverReturnsType);
                Type put11 = types.put("Object", Type.objectType);
                Type put12 = types.put("String", Type.toStringType);
                Type put13 = types.put("object", Type.objectType);
                Type put14 = types.put("number", LangObjType.numericType);
                Type put15 = types.put("quantity", ClassType.make("gnu.math.Quantity"));
                Type put16 = types.put("complex", ClassType.make("gnu.math.Complex"));
                Type put17 = types.put("real", LangObjType.realType);
                Type put18 = types.put("rational", LangObjType.rationalType);
                Type put19 = types.put(PropertyTypeConstants.PROPERTY_TYPE_INTEGER, LangObjType.integerType);
                Type put20 = types.put("symbol", ClassType.make("gnu.mapping.Symbol"));
                Type put21 = types.put("namespace", ClassType.make("gnu.mapping.Namespace"));
                Type put22 = types.put("keyword", ClassType.make("gnu.expr.Keyword"));
                Type put23 = types.put("pair", ClassType.make("gnu.lists.Pair"));
                Type put24 = types.put("pair-with-position", ClassType.make("gnu.lists.PairWithPosition"));
                Type put25 = types.put("constant-string", ClassType.make("java.lang.String"));
                Type put26 = types.put("abstract-string", ClassType.make("gnu.lists.CharSeq"));
                Type put27 = types.put("character", ClassType.make("gnu.text.Char"));
                Type put28 = types.put("vector", LangObjType.vectorType);
                Type put29 = types.put(PropertyTypeConstants.PROPERTY_TYPE_STRING, LangObjType.stringType);
                Type put30 = types.put("list", LangObjType.listType);
                Type put31 = types.put("function", ClassType.make("gnu.mapping.Procedure"));
                Type put32 = types.put("procedure", ClassType.make("gnu.mapping.Procedure"));
                Type put33 = types.put("input-port", ClassType.make("gnu.mapping.InPort"));
                Type put34 = types.put("output-port", ClassType.make("gnu.mapping.OutPort"));
                Type put35 = types.put("string-output-port", ClassType.make("gnu.mapping.CharArrayOutPort"));
                Type put36 = types.put("record", ClassType.make("kawa.lang.Record"));
                Type put37 = types.put("type", LangObjType.typeType);
                Type put38 = types.put("class-type", LangObjType.typeClassType);
                Type put39 = types.put("class", LangObjType.typeClass);
                Type put40 = types.put("s8vector", ClassType.make("gnu.lists.S8Vector"));
                Type put41 = types.put("u8vector", ClassType.make("gnu.lists.U8Vector"));
                Type put42 = types.put("s16vector", ClassType.make("gnu.lists.S16Vector"));
                Type put43 = types.put("u16vector", ClassType.make("gnu.lists.U16Vector"));
                Type put44 = types.put("s32vector", ClassType.make("gnu.lists.S32Vector"));
                Type put45 = types.put("u32vector", ClassType.make("gnu.lists.U32Vector"));
                Type put46 = types.put("s64vector", ClassType.make("gnu.lists.S64Vector"));
                Type put47 = types.put("u64vector", ClassType.make("gnu.lists.U64Vector"));
                Type put48 = types.put("f32vector", ClassType.make("gnu.lists.F32Vector"));
                Type put49 = types.put("f64vector", ClassType.make("gnu.lists.F64Vector"));
                Type put50 = types.put("document", ClassType.make("gnu.kawa.xml.KDocument"));
                Type put51 = types.put("readtable", ClassType.make("gnu.kawa.lispexpr.ReadTable"));
            }
            hashMap = types;
        }
        return hashMap;
    }

    public static Type getNamedType(String str) {
        Throwable th;
        StringBuilder sb;
        String name = str;
        HashMap<String, Type> typeMap = getTypeMap();
        Type type = types.get(name);
        if (type == null && (name.startsWith("elisp:") || name.startsWith("clisp:"))) {
            int colon = name.indexOf(58);
            Class clas = getNamedType(name.substring(colon + 1)).getReflectClass();
            String lang = name.substring(0, colon);
            Language interp = Language.getInstance(lang);
            if (interp == null) {
                Throwable th2 = th;
                new StringBuilder();
                new RuntimeException(sb.append("unknown type '").append(name).append("' - unknown language '").append(lang).append('\'').toString());
                throw th2;
            }
            type = interp.getTypeFor(clas);
            if (type != null) {
                Type put = types.put(name, type);
            }
        }
        return type;
    }

    public Type getTypeFor(Class cls) {
        Class clas = cls;
        String name = clas.getName();
        if (clas.isPrimitive()) {
            return getNamedType(name);
        }
        if ("java.lang.String".equals(name)) {
            return Type.toStringType;
        }
        if ("gnu.math.IntNum".equals(name)) {
            return LangObjType.integerType;
        }
        if ("gnu.math.DFloNum".equals(name)) {
            return LangObjType.dflonumType;
        }
        if ("gnu.math.RatNum".equals(name)) {
            return LangObjType.rationalType;
        }
        if ("gnu.math.RealNum".equals(name)) {
            return LangObjType.realType;
        }
        if ("gnu.math.Numeric".equals(name)) {
            return LangObjType.numericType;
        }
        if ("gnu.lists.FVector".equals(name)) {
            return LangObjType.vectorType;
        }
        if ("gnu.lists.LList".equals(name)) {
            return LangObjType.listType;
        }
        if ("gnu.text.Path".equals(name)) {
            return LangObjType.pathType;
        }
        if ("gnu.text.URIPath".equals(name)) {
            return LangObjType.URIType;
        }
        if ("gnu.text.FilePath".equals(name)) {
            return LangObjType.filepathType;
        }
        if ("java.lang.Class".equals(name)) {
            return LangObjType.typeClass;
        }
        if ("gnu.bytecode.Type".equals(name)) {
            return LangObjType.typeType;
        }
        if ("gnu.bytecode.ClassType".equals(name)) {
            return LangObjType.typeClassType;
        }
        return Type.make(clas);
    }

    public String formatType(Type type) {
        HashMap<Type, String> hashMap;
        Type type2 = type;
        if (typeToStringMap == null) {
            new HashMap<>();
            typeToStringMap = hashMap;
            for (Map.Entry<String, Type> e : getTypeMap().entrySet()) {
                String s = e.getKey();
                Type t = e.getValue();
                String put = typeToStringMap.put(t, s);
                Type it = t.getImplementationType();
                if (it != t) {
                    String put2 = typeToStringMap.put(it, s);
                }
            }
        }
        String str = typeToStringMap.get(type2);
        if (str != null) {
            return str;
        }
        return super.formatType(type2);
    }

    public static Type string2Type(String str) {
        Type t;
        String name = str;
        if (name.endsWith("[]")) {
            t = string2Type(name.substring(0, name.length() - 2));
            if (t != null) {
                t = ArrayType.make(t);
            }
        } else {
            t = getNamedType(name);
        }
        if (t != null) {
            return t;
        }
        Type t2 = Language.string2Type(name);
        if (t2 != null) {
            Type put = types.put(name, t2);
        }
        return t2;
    }

    public Type getTypeFor(String name) {
        return string2Type(name);
    }

    public static Type exp2Type(Expression exp) {
        return getInstance().getTypeFor(exp);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v6, resolved type: gnu.bytecode.ArrayType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v8, resolved type: gnu.bytecode.ArrayType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v204, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v48, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v5, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v221, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v53, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v223, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v6, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v225, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v7, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v8, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v228, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v229, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v9, resolved type: boolean} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public gnu.expr.Expression checkDefaultBinding(gnu.mapping.Symbol r36, kawa.lang.Translator r37) {
        /*
            r35 = this;
            r3 = r35
            r4 = r36
            r5 = r37
            r24 = r4
            gnu.mapping.Namespace r24 = r24.getNamespace()
            r6 = r24
            r24 = r4
            java.lang.String r24 = r24.getLocalPart()
            r7 = r24
            r24 = r6
            r0 = r24
            boolean r0 = r0 instanceof gnu.kawa.xml.XmlNamespace
            r24 = r0
            if (r24 == 0) goto L_0x0031
            r24 = r6
            gnu.kawa.xml.XmlNamespace r24 = (gnu.kawa.xml.XmlNamespace) r24
            r25 = r7
            java.lang.Object r24 = r24.get(r25)
            gnu.expr.QuoteExp r24 = gnu.expr.QuoteExp.getInstance(r24)
            r3 = r24
        L_0x0030:
            return r3
        L_0x0031:
            r24 = r6
            java.lang.String r24 = r24.getName()
            gnu.mapping.Namespace r25 = unitNamespace
            java.lang.String r25 = r25.getName()
            r0 = r24
            r1 = r25
            if (r0 != r1) goto L_0x0058
            r24 = r7
            gnu.math.NamedUnit r24 = gnu.math.Unit.lookup(r24)
            r8 = r24
            r24 = r8
            if (r24 == 0) goto L_0x0058
            r24 = r8
            gnu.expr.QuoteExp r24 = gnu.expr.QuoteExp.getInstance(r24)
            r3 = r24
            goto L_0x0030
        L_0x0058:
            r24 = r4
            java.lang.String r24 = r24.toString()
            r8 = r24
            r24 = r8
            int r24 = r24.length()
            r9 = r24
            r24 = r9
            if (r24 != 0) goto L_0x0071
            r24 = 0
            r3 = r24
            goto L_0x0030
        L_0x0071:
            r24 = r9
            r25 = 1
            r0 = r24
            r1 = r25
            if (r0 <= r1) goto L_0x016d
            r24 = r8
            r25 = r9
            r26 = 1
            int r25 = r25 + -1
            char r24 = r24.charAt(r25)
            r25 = 63
            r0 = r24
            r1 = r25
            if (r0 != r1) goto L_0x016d
            r24 = r7
            int r24 = r24.length()
            r10 = r24
            r24 = r10
            r25 = 1
            r0 = r24
            r1 = r25
            if (r0 <= r1) goto L_0x016d
            r24 = r7
            r25 = 0
            r26 = r10
            r27 = 1
            int r26 = r26 + -1
            java.lang.String r24 = r24.substring(r25, r26)
            java.lang.String r24 = r24.intern()
            r11 = r24
            r24 = r6
            r25 = r11
            gnu.mapping.Symbol r24 = r24.getSymbol(r25)
            r12 = r24
            r24 = r5
            r25 = r12
            r26 = 0
            gnu.expr.Expression r24 = r24.rewrite(r25, r26)
            r13 = r24
            r24 = r13
            r0 = r24
            boolean r0 = r0 instanceof gnu.expr.ReferenceExp
            r24 = r0
            if (r24 == 0) goto L_0x015e
            r24 = r13
            gnu.expr.ReferenceExp r24 = (gnu.expr.ReferenceExp) r24
            gnu.expr.Declaration r24 = r24.getBinding()
            r14 = r24
            r24 = r14
            if (r24 == 0) goto L_0x00ee
            r24 = r14
            r25 = 65536(0x10000, double:3.2379E-319)
            boolean r24 = r24.getFlag(r25)
            if (r24 == 0) goto L_0x00f2
        L_0x00ee:
            r24 = 0
            r13 = r24
        L_0x00f2:
            r24 = r13
            if (r24 == 0) goto L_0x016d
            gnu.expr.LambdaExp r24 = new gnu.expr.LambdaExp
            r34 = r24
            r24 = r34
            r25 = r34
            r26 = 1
            r25.<init>((int) r26)
            r14 = r24
            r24 = r14
            r25 = r4
            r24.setSymbol(r25)
            r24 = r14
            r25 = 0
            java.lang.Object r25 = (java.lang.Object) r25
            gnu.expr.Declaration r24 = r24.addDeclaration((java.lang.Object) r25)
            r15 = r24
            r24 = r14
            gnu.expr.ApplyExp r25 = new gnu.expr.ApplyExp
            r34 = r25
            r25 = r34
            r26 = r34
            gnu.kawa.reflect.InstanceOf r27 = instanceOf
            r28 = 2
            r0 = r28
            gnu.expr.Expression[] r0 = new gnu.expr.Expression[r0]
            r28 = r0
            r34 = r28
            r28 = r34
            r29 = r34
            r30 = 0
            gnu.expr.ReferenceExp r31 = new gnu.expr.ReferenceExp
            r34 = r31
            r31 = r34
            r32 = r34
            r33 = r15
            r32.<init>((gnu.expr.Declaration) r33)
            r29[r30] = r31
            r34 = r28
            r28 = r34
            r29 = r34
            r30 = 1
            r31 = r13
            r29[r30] = r31
            r26.<init>((gnu.mapping.Procedure) r27, (gnu.expr.Expression[]) r28)
            r0 = r25
            r1 = r24
            r1.body = r0
            r24 = r14
            r3 = r24
            goto L_0x0030
        L_0x015e:
            r24 = r13
            r0 = r24
            boolean r0 = r0 instanceof gnu.expr.QuoteExp
            r24 = r0
            if (r24 != 0) goto L_0x00f2
            r24 = 0
            r13 = r24
            goto L_0x00f2
        L_0x016d:
            r24 = r8
            r25 = 0
            char r24 = r24.charAt(r25)
            r10 = r24
            r24 = r10
            r25 = 45
            r0 = r24
            r1 = r25
            if (r0 == r1) goto L_0x0195
            r24 = r10
            r25 = 43
            r0 = r24
            r1 = r25
            if (r0 == r1) goto L_0x0195
            r24 = r10
            r25 = 10
            int r24 = java.lang.Character.digit(r24, r25)
            if (r24 < 0) goto L_0x02f4
        L_0x0195:
            r24 = 0
            r11 = r24
            r24 = 0
            r12 = r24
        L_0x019d:
            r24 = r12
            r25 = r9
            r0 = r24
            r1 = r25
            if (r0 >= r1) goto L_0x028e
            r24 = r8
            r25 = r12
            char r24 = r24.charAt(r25)
            r13 = r24
            r24 = r13
            r25 = 10
            int r24 = java.lang.Character.digit(r24, r25)
            if (r24 < 0) goto L_0x01dc
            r24 = r11
            r25 = 3
            r0 = r24
            r1 = r25
            if (r0 >= r1) goto L_0x01cc
            r24 = 2
        L_0x01c7:
            r11 = r24
        L_0x01c9:
            int r12 = r12 + 1
            goto L_0x019d
        L_0x01cc:
            r24 = r11
            r25 = 5
            r0 = r24
            r1 = r25
            if (r0 >= r1) goto L_0x01d9
            r24 = 4
            goto L_0x01c7
        L_0x01d9:
            r24 = 5
            goto L_0x01c7
        L_0x01dc:
            r24 = r13
            r25 = 43
            r0 = r24
            r1 = r25
            if (r0 == r1) goto L_0x01f0
            r24 = r13
            r25 = 45
            r0 = r24
            r1 = r25
            if (r0 != r1) goto L_0x01f9
        L_0x01f0:
            r24 = r11
            if (r24 != 0) goto L_0x01f9
            r24 = 1
            r11 = r24
            goto L_0x01c9
        L_0x01f9:
            r24 = r13
            r25 = 46
            r0 = r24
            r1 = r25
            if (r0 != r1) goto L_0x0212
            r24 = r11
            r25 = 3
            r0 = r24
            r1 = r25
            if (r0 >= r1) goto L_0x0212
            r24 = 3
            r11 = r24
            goto L_0x01c9
        L_0x0212:
            r24 = r13
            r25 = 101(0x65, float:1.42E-43)
            r0 = r24
            r1 = r25
            if (r0 == r1) goto L_0x0226
            r24 = r13
            r25 = 69
            r0 = r24
            r1 = r25
            if (r0 != r1) goto L_0x028e
        L_0x0226:
            r24 = r11
            r25 = 2
            r0 = r24
            r1 = r25
            if (r0 == r1) goto L_0x023a
            r24 = r11
            r25 = 4
            r0 = r24
            r1 = r25
            if (r0 != r1) goto L_0x028e
        L_0x023a:
            r24 = r12
            r25 = 1
            int r24 = r24 + 1
            r25 = r9
            r0 = r24
            r1 = r25
            if (r0 >= r1) goto L_0x028e
            r24 = r12
            r25 = 1
            int r24 = r24 + 1
            r14 = r24
            r24 = r8
            r25 = r14
            char r24 = r24.charAt(r25)
            r15 = r24
            r24 = r15
            r25 = 45
            r0 = r24
            r1 = r25
            if (r0 == r1) goto L_0x026e
            r24 = r15
            r25 = 43
            r0 = r24
            r1 = r25
            if (r0 != r1) goto L_0x0284
        L_0x026e:
            int r14 = r14 + 1
            r24 = r14
            r25 = r9
            r0 = r24
            r1 = r25
            if (r0 >= r1) goto L_0x0284
            r24 = r8
            r25 = r14
            char r24 = r24.charAt(r25)
            r15 = r24
        L_0x0284:
            r24 = r15
            r25 = 10
            int r24 = java.lang.Character.digit(r24, r25)
            if (r24 >= 0) goto L_0x036d
        L_0x028e:
            r24 = r12
            r25 = r9
            r0 = r24
            r1 = r25
            if (r0 >= r1) goto L_0x02f4
            r24 = r11
            r25 = 1
            r0 = r24
            r1 = r25
            if (r0 <= r1) goto L_0x02f4
            gnu.math.DFloNum r24 = new gnu.math.DFloNum
            r34 = r24
            r24 = r34
            r25 = r34
            r26 = r8
            r27 = 0
            r28 = r12
            java.lang.String r26 = r26.substring(r27, r28)
            r25.<init>((java.lang.String) r26)
            r13 = r24
            r24 = 0
            r14 = r24
            java.util.Vector r24 = new java.util.Vector
            r34 = r24
            r24 = r34
            r25 = r34
            r25.<init>()
            r15 = r24
        L_0x02ca:
            r24 = r12
            r25 = r9
            r0 = r24
            r1 = r25
            if (r0 >= r1) goto L_0x04f1
            r24 = r8
            r25 = r12
            int r12 = r12 + 1
            char r24 = r24.charAt(r25)
            r16 = r24
            r24 = r16
            r25 = 42
            r0 = r24
            r1 = r25
            if (r0 != r1) goto L_0x03ab
            r24 = r12
            r25 = r9
            r0 = r24
            r1 = r25
            if (r0 != r1) goto L_0x037b
        L_0x02f4:
            r24 = r9
            r25 = 2
            r0 = r24
            r1 = r25
            if (r0 <= r1) goto L_0x05ff
            r24 = r10
            r25 = 60
            r0 = r24
            r1 = r25
            if (r0 != r1) goto L_0x05ff
            r24 = r8
            r25 = r9
            r26 = 1
            int r25 = r25 + -1
            char r24 = r24.charAt(r25)
            r25 = 62
            r0 = r24
            r1 = r25
            if (r0 != r1) goto L_0x05ff
            r24 = r8
            r25 = 1
            r26 = r9
            r27 = 1
            int r26 = r26 + -1
            java.lang.String r24 = r24.substring(r25, r26)
            r8 = r24
            int r9 = r9 + -2
            r24 = 1
            r11 = r24
        L_0x0332:
            r24 = 0
            r12 = r24
        L_0x0336:
            r24 = r9
            r25 = 2
            r0 = r24
            r1 = r25
            if (r0 <= r1) goto L_0x0605
            r24 = r8
            r25 = r9
            r26 = 2
            int r25 = r25 + -2
            char r24 = r24.charAt(r25)
            r25 = 91
            r0 = r24
            r1 = r25
            if (r0 != r1) goto L_0x0605
            r24 = r8
            r25 = r9
            r26 = 1
            int r25 = r25 + -1
            char r24 = r24.charAt(r25)
            r25 = 93
            r0 = r24
            r1 = r25
            if (r0 != r1) goto L_0x0605
            int r9 = r9 + -2
            int r12 = r12 + 1
            goto L_0x0336
        L_0x036d:
            r24 = 5
            r11 = r24
            r24 = r14
            r25 = 1
            int r24 = r24 + 1
            r12 = r24
            goto L_0x01c9
        L_0x037b:
            r24 = r8
            r25 = r12
            int r12 = r12 + 1
            char r24 = r24.charAt(r25)
            r16 = r24
        L_0x0387:
            r24 = r12
            r25 = 1
            int r24 = r24 + -1
            r17 = r24
        L_0x038f:
            r24 = r16
            boolean r24 = java.lang.Character.isLetter(r24)
            if (r24 != 0) goto L_0x03d6
            r24 = r12
            r25 = 1
            int r24 = r24 + -1
            r18 = r24
            r24 = r18
            r25 = r17
            r0 = r24
            r1 = r25
            if (r0 != r1) goto L_0x03e8
            goto L_0x02f4
        L_0x03ab:
            r24 = r16
            r25 = 47
            r0 = r24
            r1 = r25
            if (r0 != r1) goto L_0x0387
            r24 = r12
            r25 = r9
            r0 = r24
            r1 = r25
            if (r0 == r1) goto L_0x02f4
            r24 = r14
            if (r24 == 0) goto L_0x03c5
            goto L_0x02f4
        L_0x03c5:
            r24 = 1
            r14 = r24
            r24 = r8
            r25 = r12
            int r12 = r12 + 1
            char r24 = r24.charAt(r25)
            r16 = r24
            goto L_0x0387
        L_0x03d6:
            r24 = r12
            r25 = r9
            r0 = r24
            r1 = r25
            if (r0 != r1) goto L_0x0415
            r24 = r12
            r18 = r24
            r24 = 49
            r16 = r24
        L_0x03e8:
            r24 = r15
            r25 = r8
            r26 = r17
            r27 = r18
            java.lang.String r25 = r25.substring(r26, r27)
            r24.addElement(r25)
            r24 = 0
            r19 = r24
            r24 = r16
            r25 = 94
            r0 = r24
            r1 = r25
            if (r0 != r1) goto L_0x042f
            r24 = 1
            r19 = r24
            r24 = r12
            r25 = r9
            r0 = r24
            r1 = r25
            if (r0 != r1) goto L_0x0423
            goto L_0x02f4
        L_0x0415:
            r24 = r8
            r25 = r12
            int r12 = r12 + 1
            char r24 = r24.charAt(r25)
            r16 = r24
            goto L_0x038f
        L_0x0423:
            r24 = r8
            r25 = r12
            int r12 = r12 + 1
            char r24 = r24.charAt(r25)
            r16 = r24
        L_0x042f:
            r24 = r14
            r20 = r24
            r24 = r16
            r25 = 43
            r0 = r24
            r1 = r25
            if (r0 != r1) goto L_0x047f
            r24 = 1
            r19 = r24
            r24 = r12
            r25 = r9
            r0 = r24
            r1 = r25
            if (r0 != r1) goto L_0x044d
            goto L_0x02f4
        L_0x044d:
            r24 = r8
            r25 = r12
            int r12 = r12 + 1
            char r24 = r24.charAt(r25)
            r16 = r24
        L_0x0459:
            r24 = 0
            r21 = r24
            r24 = 0
            r22 = r24
        L_0x0461:
            r24 = r16
            r25 = 10
            int r24 = java.lang.Character.digit(r24, r25)
            r23 = r24
            r24 = r23
            if (r24 > 0) goto L_0x04b1
            int r12 = r12 + -1
        L_0x0471:
            r24 = r21
            if (r24 != 0) goto L_0x04d7
            r24 = 1
            r22 = r24
            r24 = r19
            if (r24 == 0) goto L_0x04d7
            goto L_0x02f4
        L_0x047f:
            r24 = r16
            r25 = 45
            r0 = r24
            r1 = r25
            if (r0 != r1) goto L_0x0459
            r24 = 1
            r19 = r24
            r24 = r12
            r25 = r9
            r0 = r24
            r1 = r25
            if (r0 != r1) goto L_0x0499
            goto L_0x02f4
        L_0x0499:
            r24 = r8
            r25 = r12
            int r12 = r12 + 1
            char r24 = r24.charAt(r25)
            r16 = r24
            r24 = r20
            if (r24 != 0) goto L_0x04ae
            r24 = 1
        L_0x04ab:
            r20 = r24
            goto L_0x0459
        L_0x04ae:
            r24 = 0
            goto L_0x04ab
        L_0x04b1:
            r24 = 10
            r25 = r22
            int r24 = r24 * r25
            r25 = r23
            int r24 = r24 + r25
            r22 = r24
            int r21 = r21 + 1
            r24 = r12
            r25 = r9
            r0 = r24
            r1 = r25
            if (r0 != r1) goto L_0x04ca
            goto L_0x0471
        L_0x04ca:
            r24 = r8
            r25 = r12
            int r12 = r12 + 1
            char r24 = r24.charAt(r25)
            r16 = r24
            goto L_0x0461
        L_0x04d7:
            r24 = r20
            if (r24 == 0) goto L_0x04e4
            r24 = r22
            r0 = r24
            int r0 = -r0
            r24 = r0
            r22 = r24
        L_0x04e4:
            r24 = r15
            r25 = r22
            gnu.math.IntNum r25 = gnu.math.IntNum.make((int) r25)
            r24.addElement(r25)
            goto L_0x02ca
        L_0x04f1:
            r24 = r12
            r25 = r9
            r0 = r24
            r1 = r25
            if (r0 != r1) goto L_0x02f4
            r24 = r15
            int r24 = r24.size()
            r25 = 1
            int r24 = r24 >> 1
            r16 = r24
            r24 = r16
            r0 = r24
            gnu.expr.Expression[] r0 = new gnu.expr.Expression[r0]
            r24 = r0
            r17 = r24
            r24 = 0
            r12 = r24
        L_0x0515:
            r24 = r12
            r25 = r16
            r0 = r24
            r1 = r25
            if (r0 >= r1) goto L_0x05a6
            r24 = r15
            r25 = 2
            r26 = r12
            int r25 = r25 * r26
            java.lang.Object r24 = r24.elementAt(r25)
            java.lang.String r24 = (java.lang.String) r24
            r18 = r24
            gnu.mapping.Namespace r24 = unitNamespace
            r25 = r18
            java.lang.String r25 = r25.intern()
            gnu.mapping.Symbol r24 = r24.getSymbol(r25)
            r19 = r24
            r24 = r5
            r25 = r19
            gnu.expr.Expression r24 = r24.rewrite(r25)
            r20 = r24
            r24 = r15
            r25 = 2
            r26 = r12
            int r25 = r25 * r26
            r26 = 1
            int r25 = r25 + 1
            java.lang.Object r24 = r24.elementAt(r25)
            gnu.math.IntNum r24 = (gnu.math.IntNum) r24
            r21 = r24
            r24 = r21
            long r24 = r24.longValue()
            r26 = 1
            int r24 = (r24 > r26 ? 1 : (r24 == r26 ? 0 : -1))
            if (r24 == 0) goto L_0x059a
            gnu.expr.ApplyExp r24 = new gnu.expr.ApplyExp
            r34 = r24
            r24 = r34
            r25 = r34
            kawa.standard.expt r26 = kawa.standard.expt.expt
            r27 = 2
            r0 = r27
            gnu.expr.Expression[] r0 = new gnu.expr.Expression[r0]
            r27 = r0
            r34 = r27
            r27 = r34
            r28 = r34
            r29 = 0
            r30 = r20
            r28[r29] = r30
            r34 = r27
            r27 = r34
            r28 = r34
            r29 = 1
            r30 = r21
            gnu.expr.QuoteExp r30 = gnu.expr.QuoteExp.getInstance(r30)
            r28[r29] = r30
            r25.<init>((gnu.mapping.Procedure) r26, (gnu.expr.Expression[]) r27)
            r20 = r24
        L_0x059a:
            r24 = r17
            r25 = r12
            r26 = r20
            r24[r25] = r26
            int r12 = r12 + 1
            goto L_0x0515
        L_0x05a6:
            r24 = r16
            r25 = 1
            r0 = r24
            r1 = r25
            if (r0 != r1) goto L_0x05ed
            r24 = r17
            r25 = 0
            r24 = r24[r25]
            r18 = r24
        L_0x05b8:
            gnu.expr.ApplyExp r24 = new gnu.expr.ApplyExp
            r34 = r24
            r24 = r34
            r25 = r34
            gnu.kawa.functions.MultiplyOp r26 = gnu.kawa.functions.MultiplyOp.$St
            r27 = 2
            r0 = r27
            gnu.expr.Expression[] r0 = new gnu.expr.Expression[r0]
            r27 = r0
            r34 = r27
            r27 = r34
            r28 = r34
            r29 = 0
            r30 = r13
            gnu.expr.QuoteExp r30 = gnu.expr.QuoteExp.getInstance(r30)
            r28[r29] = r30
            r34 = r27
            r27 = r34
            r28 = r34
            r29 = 1
            r30 = r18
            r28[r29] = r30
            r25.<init>((gnu.mapping.Procedure) r26, (gnu.expr.Expression[]) r27)
            r3 = r24
            goto L_0x0030
        L_0x05ed:
            gnu.expr.ApplyExp r24 = new gnu.expr.ApplyExp
            r34 = r24
            r24 = r34
            r25 = r34
            gnu.kawa.functions.MultiplyOp r26 = gnu.kawa.functions.MultiplyOp.$St
            r27 = r17
            r25.<init>((gnu.mapping.Procedure) r26, (gnu.expr.Expression[]) r27)
            r18 = r24
            goto L_0x05b8
        L_0x05ff:
            r24 = 0
            r11 = r24
            goto L_0x0332
        L_0x0605:
            r24 = r8
            r13 = r24
            r24 = r12
            if (r24 == 0) goto L_0x0619
            r24 = r8
            r25 = 0
            r26 = r9
            java.lang.String r24 = r24.substring(r25, r26)
            r13 = r24
        L_0x0619:
            r24 = r13
            gnu.bytecode.Type r24 = getNamedType(r24)     // Catch:{ ClassNotFoundException -> 0x0711, Throwable -> 0x072b }
            r15 = r24
            r24 = r12
            if (r24 <= 0) goto L_0x0669
            r24 = r11
            if (r24 == 0) goto L_0x062d
            r24 = r15
            if (r24 != 0) goto L_0x0669
        L_0x062d:
            r24 = r6
            r25 = r13
            java.lang.String r25 = r25.intern()     // Catch:{ ClassNotFoundException -> 0x0711, Throwable -> 0x072b }
            gnu.mapping.Symbol r24 = r24.getSymbol(r25)     // Catch:{ ClassNotFoundException -> 0x0711, Throwable -> 0x072b }
            r16 = r24
            r24 = r5
            r25 = r16
            r26 = 0
            gnu.expr.Expression r24 = r24.rewrite(r25, r26)     // Catch:{ ClassNotFoundException -> 0x0711, Throwable -> 0x072b }
            r17 = r24
            r24 = r17
            r25 = r5
            gnu.expr.Expression r24 = gnu.expr.InlineCalls.inlineCalls(r24, r25)     // Catch:{ ClassNotFoundException -> 0x0711, Throwable -> 0x072b }
            r17 = r24
            r24 = r17
            r0 = r24
            boolean r0 = r0 instanceof gnu.expr.ErrorExp     // Catch:{ ClassNotFoundException -> 0x0711, Throwable -> 0x072b }
            r24 = r0
            if (r24 != 0) goto L_0x0669
            r24 = r5
            gnu.expr.Language r24 = r24.getLanguage()     // Catch:{ ClassNotFoundException -> 0x0711, Throwable -> 0x072b }
            r25 = r17
            gnu.bytecode.Type r24 = r24.getTypeFor((gnu.expr.Expression) r25)     // Catch:{ ClassNotFoundException -> 0x0711, Throwable -> 0x072b }
            r15 = r24
        L_0x0669:
            r24 = r15
            if (r24 == 0) goto L_0x0686
        L_0x066d:
            int r12 = r12 + -1
            r24 = r12
            if (r24 < 0) goto L_0x067c
            r24 = r15
            gnu.bytecode.ArrayType r24 = gnu.bytecode.ArrayType.make((gnu.bytecode.Type) r24)     // Catch:{ ClassNotFoundException -> 0x0711, Throwable -> 0x072b }
            r15 = r24
            goto L_0x066d
        L_0x067c:
            r24 = r15
            gnu.expr.QuoteExp r24 = gnu.expr.QuoteExp.getInstance(r24)     // Catch:{ ClassNotFoundException -> 0x0711, Throwable -> 0x072b }
            r3 = r24
            goto L_0x0030
        L_0x0686:
            r24 = r13
            gnu.bytecode.Type r24 = gnu.bytecode.Type.lookupType(r24)     // Catch:{ ClassNotFoundException -> 0x0711, Throwable -> 0x072b }
            r15 = r24
            r24 = r15
            r0 = r24
            boolean r0 = r0 instanceof gnu.bytecode.PrimType     // Catch:{ ClassNotFoundException -> 0x0711, Throwable -> 0x072b }
            r24 = r0
            if (r24 == 0) goto L_0x06bf
            r24 = r15
            java.lang.Class r24 = r24.getReflectClass()     // Catch:{ ClassNotFoundException -> 0x0711, Throwable -> 0x072b }
            r14 = r24
        L_0x06a0:
            r24 = r14
            if (r24 == 0) goto L_0x070b
            r24 = r12
            if (r24 <= 0) goto L_0x0701
            r24 = r14
            gnu.bytecode.Type r24 = gnu.bytecode.Type.make(r24)     // Catch:{ ClassNotFoundException -> 0x0711, Throwable -> 0x072b }
            r15 = r24
        L_0x06b0:
            int r12 = r12 + -1
            r24 = r12
            if (r24 < 0) goto L_0x06f9
            r24 = r15
            gnu.bytecode.ArrayType r24 = gnu.bytecode.ArrayType.make((gnu.bytecode.Type) r24)     // Catch:{ ClassNotFoundException -> 0x0711, Throwable -> 0x072b }
            r15 = r24
            goto L_0x06b0
        L_0x06bf:
            r24 = r13
            r25 = 46
            int r24 = r24.indexOf(r25)     // Catch:{ ClassNotFoundException -> 0x0711, Throwable -> 0x072b }
            if (r24 >= 0) goto L_0x06f0
            java.lang.StringBuilder r24 = new java.lang.StringBuilder     // Catch:{ ClassNotFoundException -> 0x0711, Throwable -> 0x072b }
            r34 = r24
            r24 = r34
            r25 = r34
            r25.<init>()     // Catch:{ ClassNotFoundException -> 0x0711, Throwable -> 0x072b }
            r25 = r5
            r0 = r25
            java.lang.String r0 = r0.classPrefix     // Catch:{ ClassNotFoundException -> 0x0711, Throwable -> 0x072b }
            r25 = r0
            java.lang.StringBuilder r24 = r24.append(r25)     // Catch:{ ClassNotFoundException -> 0x0711, Throwable -> 0x072b }
            r25 = r13
            java.lang.String r25 = gnu.expr.Compilation.mangleNameIfNeeded(r25)     // Catch:{ ClassNotFoundException -> 0x0711, Throwable -> 0x072b }
            java.lang.StringBuilder r24 = r24.append(r25)     // Catch:{ ClassNotFoundException -> 0x0711, Throwable -> 0x072b }
            java.lang.String r24 = r24.toString()     // Catch:{ ClassNotFoundException -> 0x0711, Throwable -> 0x072b }
            r13 = r24
        L_0x06f0:
            r24 = r13
            java.lang.Class r24 = gnu.bytecode.ClassType.getContextClass(r24)     // Catch:{ ClassNotFoundException -> 0x0711, Throwable -> 0x072b }
            r14 = r24
            goto L_0x06a0
        L_0x06f9:
            r24 = r15
            java.lang.Class r24 = r24.getReflectClass()     // Catch:{ ClassNotFoundException -> 0x0711, Throwable -> 0x072b }
            r14 = r24
        L_0x0701:
            r24 = r14
            gnu.expr.QuoteExp r24 = gnu.expr.QuoteExp.getInstance(r24)     // Catch:{ ClassNotFoundException -> 0x0711, Throwable -> 0x072b }
            r3 = r24
            goto L_0x0030
        L_0x070b:
            r24 = 0
            r3 = r24
            goto L_0x0030
        L_0x0711:
            r24 = move-exception
            r14 = r24
            r24 = r8
            java.lang.Package r24 = gnu.bytecode.ArrayClassLoader.getContextPackage(r24)
            r15 = r24
            r24 = r15
            if (r24 == 0) goto L_0x072a
            r24 = r15
            gnu.expr.QuoteExp r24 = gnu.expr.QuoteExp.getInstance(r24)
            r3 = r24
            goto L_0x0030
        L_0x072a:
            goto L_0x070b
        L_0x072b:
            r24 = move-exception
            r14 = r24
            goto L_0x070b
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.standard.Scheme.checkDefaultBinding(gnu.mapping.Symbol, kawa.lang.Translator):gnu.expr.Expression");
    }

    public Expression makeApply(Expression func, Expression[] expressionArr) {
        Expression expression;
        Expression expression2;
        Expression[] args = expressionArr;
        Expression[] exps = new Expression[(args.length + 1)];
        exps[0] = func;
        System.arraycopy(args, 0, exps, 1, args.length);
        new ReferenceExp(applyFieldDecl);
        new ApplyExp(expression2, exps);
        return expression;
    }

    public Symbol asSymbol(String ident) {
        return Namespace.EmptyNamespace.getSymbol(ident);
    }

    public ReadTable createReadTable() {
        Object obj;
        Object obj2;
        ReadTable tab = ReadTable.createInitial();
        tab.postfixLookupOperator = ':';
        ReaderDispatch dispatchTable = (ReaderDispatch) tab.lookup(35);
        new ReaderQuote(asSymbol("syntax"));
        dispatchTable.set(39, obj);
        new ReaderQuote(asSymbol("quasisyntax"));
        dispatchTable.set(96, obj2);
        dispatchTable.set(44, ReaderDispatchMisc.getInstance());
        tab.putReaderCtorFld(ClientCookie.PATH_ATTR, "gnu.kawa.lispexpr.LangObjType", "pathType");
        tab.putReaderCtorFld("filepath", "gnu.kawa.lispexpr.LangObjType", "filepathType");
        tab.putReaderCtorFld("URI", "gnu.kawa.lispexpr.LangObjType", "URIType");
        tab.putReaderCtor("symbol", (Type) ClassType.make("gnu.mapping.Symbol"));
        tab.putReaderCtor("namespace", (Type) ClassType.make("gnu.mapping.Namespace"));
        tab.putReaderCtorFld("duration", "kawa.lib.numbers", "duration");
        tab.setFinalColonIsKeyword(true);
        return tab;
    }

    public static void registerEnvironment() {
        Language.setDefaults(getInstance());
    }
}

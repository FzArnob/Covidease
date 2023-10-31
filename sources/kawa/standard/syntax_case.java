package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.BlockExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.ExitExp;
import gnu.expr.Expression;
import gnu.expr.IfExp;
import gnu.expr.Language;
import gnu.expr.LetExp;
import gnu.expr.PrimProcedure;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.ScopeExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Procedure;
import gnu.math.IntNum;
import kawa.lang.Pattern;
import kawa.lang.PatternScope;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.SyntaxPattern;
import kawa.lang.Translator;

public class syntax_case extends Syntax {
    public static final syntax_case syntax_case;
    PrimProcedure call_error;

    public syntax_case() {
    }

    static {
        syntax_case syntax_case2;
        new syntax_case();
        syntax_case = syntax_case2;
        syntax_case.setName("syntax-case");
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    public Expression rewriteClauses(Object obj, syntax_case_work syntax_case_work, Translator translator) {
        SyntaxPattern syntaxPattern;
        BlockExp blockExp;
        Expression expression;
        Expression expression2;
        Expression expression3;
        Expression expression4;
        Expression expression5;
        Procedure procedure;
        Expression expression6;
        Expression expression7;
        Expression output;
        Expression expression8;
        Expression expression9;
        Expression expression10;
        Expression expression11;
        Expression expression12;
        PrimProcedure primProcedure;
        Object clauses = obj;
        syntax_case_work work = syntax_case_work;
        Translator tr = translator;
        Language language = tr.getLanguage();
        if (clauses == LList.Empty) {
            new QuoteExp("syntax-case");
            new ReferenceExp(work.inputExpression);
            Expression[] args = {expression10, expression11};
            if (this.call_error == null) {
                new PrimProcedure(ClassType.make("kawa.standard.syntax_case").addMethod("error", new Type[]{Compilation.javaStringType, Type.objectType}, (Type) Type.objectType, 9), language);
                this.call_error = primProcedure;
            }
            new ApplyExp((Procedure) this.call_error, args);
            return expression12;
        }
        Object savePos = tr.pushPositionOf(clauses);
        try {
            if (clauses instanceof Pair) {
                Object car = ((Pair) clauses).getCar();
                Object clause = car;
                if (car instanceof Pair) {
                    Pair pair = (Pair) clause;
                    PatternScope clauseScope = PatternScope.push(tr);
                    clauseScope.matchArray = tr.matchArray;
                    tr.push((ScopeExp) clauseScope);
                    SyntaxForm syntax = null;
                    Object tail = pair.getCdr();
                    while (tail instanceof SyntaxForm) {
                        syntax = (SyntaxForm) tail;
                        tail = syntax.getDatum();
                    }
                    if (!(tail instanceof Pair)) {
                        Expression syntaxError = tr.syntaxError("missing syntax-case output expression");
                        tr.popPositionOf(savePos);
                        return syntaxError;
                    }
                    int outerVarCount = clauseScope.pattern_names.size();
                    new SyntaxPattern(pair.getCar(), work.literal_identifiers, tr);
                    SyntaxPattern pattern = syntaxPattern;
                    int varCount = pattern.varCount();
                    if (varCount > work.maxVars) {
                        work.maxVars = varCount;
                    }
                    new BlockExp();
                    BlockExp block = blockExp;
                    new QuoteExp(pattern);
                    new ReferenceExp(work.inputExpression);
                    new ReferenceExp(tr.matchArray);
                    new QuoteExp(IntNum.zero());
                    new PrimProcedure(Pattern.matchPatternMethod, language);
                    new ApplyExp(procedure, expression, expression2, expression3, expression4);
                    Expression tryMatch = expression5;
                    int newVarCount = varCount - outerVarCount;
                    Expression[] inits = new Expression[newVarCount];
                    int i = newVarCount;
                    while (true) {
                        i--;
                        if (i < 0) {
                            break;
                        }
                        inits[i] = QuoteExp.undefined_exp;
                    }
                    clauseScope.inits = inits;
                    Pair pair2 = (Pair) tail;
                    if (pair2.getCdr() == LList.Empty) {
                        output = tr.rewrite_car(pair2, syntax);
                    } else {
                        Expression fender = tr.rewrite_car(pair2, syntax);
                        if (pair2.getCdr() instanceof Pair) {
                            Pair pair3 = (Pair) pair2.getCdr();
                            Pair pair4 = pair3;
                            if (pair3.getCdr() == LList.Empty) {
                                Expression expression13 = expression6;
                                new ExitExp(block);
                                new IfExp(fender, tr.rewrite_car(pair4, syntax), expression7);
                                output = expression13;
                            }
                        }
                        Expression syntaxError2 = tr.syntaxError("syntax-case:  bad clause");
                        tr.popPositionOf(savePos);
                        return syntaxError2;
                    }
                    clauseScope.setBody(output);
                    tr.pop(clauseScope);
                    PatternScope.pop(tr);
                    new ExitExp(block);
                    new IfExp(tryMatch, clauseScope, expression9);
                    block.setBody(expression8, rewriteClauses(((Pair) clauses).getCdr(), work, tr));
                    BlockExp blockExp2 = block;
                    tr.popPositionOf(savePos);
                    return blockExp2;
                }
            }
            Expression syntaxError3 = tr.syntaxError("syntax-case:  bad clause list");
            tr.popPositionOf(savePos);
            return syntaxError3;
        } catch (Throwable th) {
            Throwable th2 = th;
            tr.popPositionOf(savePos);
            throw th2;
        }
    }

    public Expression rewriteForm(Pair form, Translator translator) {
        syntax_case_work syntax_case_work;
        LetExp letExp;
        Expression expression;
        Expression expression2;
        Expression expression3;
        Translator tr = translator;
        new syntax_case_work();
        syntax_case_work work = syntax_case_work;
        Object obj = form.getCdr();
        if (!(obj instanceof Pair) || !(((Pair) obj).getCdr() instanceof Pair)) {
            return tr.syntaxError("insufficiant arguments to syntax-case");
        }
        Expression[] linits = new Expression[2];
        new LetExp(linits);
        LetExp let = letExp;
        work.inputExpression = let.addDeclaration((Object) null);
        Declaration matchArrayOuter = tr.matchArray;
        Declaration matchArray = let.addDeclaration((Object) null);
        matchArray.setType(Compilation.objArrayType);
        matchArray.setCanRead(true);
        tr.matchArray = matchArray;
        work.inputExpression.setCanRead(true);
        tr.push((ScopeExp) let);
        Pair form2 = (Pair) obj;
        linits[0] = tr.rewrite(form2.getCar());
        work.inputExpression.noteValue(linits[0]);
        Pair form3 = (Pair) form2.getCdr();
        work.literal_identifiers = SyntaxPattern.getLiteralsList(form3.getCar(), (SyntaxForm) null, tr);
        let.body = rewriteClauses(form3.getCdr(), work, tr);
        tr.pop(let);
        Method allocVars = ClassType.make("kawa.lang.SyntaxPattern").getDeclaredMethod("allocVars", 2);
        Expression[] args = new Expression[2];
        new QuoteExp(IntNum.make(work.maxVars));
        args[0] = expression;
        if (matchArrayOuter == null) {
            args[1] = QuoteExp.nullExp;
        } else {
            new ReferenceExp(matchArrayOuter);
            args[1] = expression2;
        }
        new ApplyExp(allocVars, args);
        linits[1] = expression3;
        matchArray.noteValue(linits[1]);
        tr.matchArray = matchArrayOuter;
        return let;
    }

    public static Object error(String str, Object obj) {
        StringBuilder sb;
        Throwable th;
        String str2 = str;
        Object obj2 = obj;
        Translator tr = (Translator) Compilation.getCurrent();
        if (tr == null) {
            Throwable th2 = th;
            new RuntimeException("no match in syntax-case");
            throw th2;
        }
        Syntax syntax = tr.getCurrentSyntax();
        String name = syntax == null ? "some syntax" : syntax.getName();
        new StringBuilder();
        return tr.syntaxError(sb.append("no matching case while expanding ").append(name).toString());
    }
}

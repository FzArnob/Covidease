package gnu.xquery.util;

import gnu.bytecode.Access;
import gnu.bytecode.ClassType;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConsumerTarget;
import gnu.expr.Expression;
import gnu.expr.IgnoreTarget;
import gnu.expr.Inlineable;
import gnu.expr.LambdaExp;
import gnu.expr.Target;
import gnu.kawa.functions.NumberCompare;
import gnu.kawa.functions.ValuesMap;
import gnu.kawa.xml.SortedNodes;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;
import gnu.mapping.Values;
import gnu.math.DFloNum;
import gnu.math.IntNum;

public class ValuesFilter extends MethodProc implements Inlineable {
    public static final ValuesFilter exprFilter;
    public static final ValuesFilter forwardFilter;
    public static final Method matchesMethod = typeValuesFilter.getDeclaredMethod("matches", 2);
    public static final ValuesFilter reverseFilter;
    public static final ClassType typeValuesFilter = ClassType.make("gnu.xquery.util.ValuesFilter");
    char kind;
    int last_or_position_needed = 2;

    public ValuesFilter(char kind2) {
        this.kind = kind2;
        setProperty(Procedure.validateApplyKey, "gnu.xquery.util.CompileMisc:validateApplyValuesFilter");
    }

    public static ValuesFilter get(char c) {
        char kind2 = c;
        if (kind2 == 'F') {
            return forwardFilter;
        }
        if (kind2 == 'R') {
            return reverseFilter;
        }
        return exprFilter;
    }

    public int numArgs() {
        return 8194;
    }

    public static boolean matches(Object obj, long j) {
        boolean z;
        Object result = obj;
        long count = j;
        if (result instanceof Values) {
            result = ((Values) result).canonicalize();
        }
        if (!(result instanceof Number)) {
            return BooleanValue.booleanValue(result);
        }
        if (result instanceof IntNum) {
            if (IntNum.compare((IntNum) result, count) == 0) {
                z = true;
            } else {
                z = false;
            }
            return z;
        } else if ((result instanceof Double) || (result instanceof Float) || (result instanceof DFloNum)) {
            return ((Number) result).doubleValue() == ((double) count);
        } else if (!(result instanceof Long) && !(result instanceof Integer) && !(result instanceof Short) && !(result instanceof Byte)) {
            return NumberCompare.applyWithPromotion(8, IntNum.make(count), result);
        } else {
            return count == ((Number) result).longValue();
        }
    }

    public void apply(CallContext callContext) throws Throwable {
        Values values;
        Values values2;
        CallContext ctx = callContext;
        Object arg = ctx.getNextArg();
        Procedure proc = (Procedure) ctx.getNextArg();
        Consumer out = ctx.consumer;
        if (this.kind != 'P') {
            new SortedNodes();
            Values nodes = values2;
            Values.writeValues(arg, nodes);
            values = nodes;
        } else if (arg instanceof Values) {
            values = (Values) arg;
        } else {
            IntNum one = IntNum.one();
            if (matches(proc.apply3(arg, one, one), 1)) {
                out.writeObject(arg);
                return;
            }
            return;
        }
        int count = values.size();
        int it = 0;
        IntNum countObj = IntNum.make(count);
        int pmax = proc.maxArgs();
        for (int i = 0; i < count; i++) {
            it = values.nextPos(it);
            Object dot = values.getPosPrevious(it);
            int pos = this.kind == 'R' ? count - i : i + 1;
            IntNum posObj = IntNum.make(pos);
            if (matches(pmax == 2 ? proc.apply2(dot, posObj) : proc.apply3(dot, posObj, countObj), (long) pos)) {
                out.writeObject(dot);
            }
        }
    }

    public void compile(ApplyExp applyExp, Compilation compilation, Target target) {
        ApplyExp exp = applyExp;
        Compilation comp = compilation;
        Target target2 = target;
        Expression[] args = exp.getArgs();
        Expression exp1 = args[0];
        Expression exp2 = args[1];
        if (target2 instanceof IgnoreTarget) {
            exp1.compile(comp, target2);
            exp2.compile(comp, target2);
        } else if (!(exp2 instanceof LambdaExp)) {
            ApplyExp.compile(exp, comp, target2);
        } else if (!(target2 instanceof ConsumerTarget)) {
            ConsumerTarget.compileUsingConsumer(exp, comp, target2);
        } else {
            ValuesMap.compileInlined((LambdaExp) exp2, exp1, 1, matchesMethod, comp, target2);
        }
    }

    public Type getReturnType(Expression[] expressionArr) {
        Expression[] expressionArr2 = expressionArr;
        return Type.pointer_type;
    }

    static {
        ValuesFilter valuesFilter;
        ValuesFilter valuesFilter2;
        ValuesFilter valuesFilter3;
        new ValuesFilter(Access.FIELD_CONTEXT);
        forwardFilter = valuesFilter;
        new ValuesFilter('R');
        reverseFilter = valuesFilter2;
        new ValuesFilter('P');
        exprFilter = valuesFilter3;
    }
}

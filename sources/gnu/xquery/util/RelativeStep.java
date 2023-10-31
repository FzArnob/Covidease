package gnu.xquery.util;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConsumerTarget;
import gnu.expr.Expression;
import gnu.expr.IgnoreTarget;
import gnu.expr.Inlineable;
import gnu.expr.LambdaExp;
import gnu.expr.QuoteExp;
import gnu.expr.Target;
import gnu.kawa.functions.ValuesMap;
import gnu.kawa.reflect.OccurrenceType;
import gnu.kawa.xml.AttributeAxis;
import gnu.kawa.xml.ChildAxis;
import gnu.kawa.xml.NodeSetType;
import gnu.kawa.xml.NodeType;
import gnu.kawa.xml.Nodes;
import gnu.kawa.xml.SelfAxis;
import gnu.kawa.xml.TreeScanner;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;
import gnu.mapping.Values;
import gnu.math.IntNum;

public class RelativeStep extends MethodProc implements Inlineable {
    public static final RelativeStep relativeStep;

    static {
        RelativeStep relativeStep2;
        new RelativeStep();
        relativeStep = relativeStep2;
    }

    RelativeStep() {
        setProperty(Procedure.validateApplyKey, "gnu.xquery.util.CompileMisc:validateApplyRelativeStep");
    }

    public int numArgs() {
        return 8194;
    }

    public void apply(CallContext callContext) throws Throwable {
        Nodes nodes;
        Nodes values;
        RelativeStepFilter relativeStepFilter;
        CallContext ctx = callContext;
        Object arg = ctx.getNextArg();
        Procedure proc = (Procedure) ctx.getNextArg();
        Consumer out = ctx.consumer;
        if (arg instanceof Nodes) {
            values = (Nodes) arg;
        } else {
            new Nodes();
            values = nodes;
            Values.writeValues(arg, values);
        }
        int count = values.size();
        int it = 0;
        IntNum countObj = IntNum.make(count);
        new RelativeStepFilter(out);
        RelativeStepFilter filter = relativeStepFilter;
        for (int pos = 1; pos <= count; pos++) {
            it = values.nextPos(it);
            proc.check3(values.getPosPrevious(it), IntNum.make(pos), countObj, ctx);
            Values.writeValues(ctx.runUntilValue(), filter);
        }
        filter.finish();
    }

    public void compile(ApplyExp applyExp, Compilation compilation, Target target) {
        char expectedKind;
        ClassType mclass;
        Variable tconsumer;
        Variable mconsumer;
        Target mtarget;
        Method initMethod;
        Target target2;
        ApplyExp exp = applyExp;
        Compilation comp = compilation;
        Target target3 = target;
        Expression[] args = exp.getArgs();
        Expression exp1 = args[0];
        Expression exp2 = args[1];
        if (target3 instanceof IgnoreTarget) {
            exp1.compile(comp, target3);
            exp2.compile(comp, target3);
            return;
        }
        Type rtype = exp.getTypeRaw();
        if (rtype == null) {
            rtype = Type.pointer_type;
        }
        int nodeCompare = NodeType.anyNodeTest.compare(OccurrenceType.itemPrimeType(rtype));
        if (nodeCompare >= 0) {
            expectedKind = 'N';
        } else if (nodeCompare == -3) {
            expectedKind = 'A';
        } else {
            expectedKind = ' ';
        }
        TreeScanner step = extractStep(exp2);
        if (step != null) {
            Type type1 = exp1.getType();
            if (((step instanceof ChildAxis) || (step instanceof AttributeAxis) || (step instanceof SelfAxis)) && ((type1 instanceof NodeSetType) || (expectedKind == 'N' && OccurrenceType.itemCountIsZeroOrOne(exp1.getType())))) {
                expectedKind = 'S';
            }
        }
        if (!(target3 instanceof ConsumerTarget)) {
            ConsumerTarget.compileUsingConsumer(exp, comp, target3);
            return;
        }
        CodeAttr code = comp.getCode();
        Scope scope = code.pushScope();
        if (expectedKind == 'A' || expectedKind == 'S') {
            mtarget = target3;
            mclass = null;
            mconsumer = null;
            tconsumer = null;
        } else {
            if (expectedKind == 'N') {
                mclass = ClassType.make("gnu.kawa.xml.SortedNodes");
                initMethod = mclass.getDeclaredMethod("<init>", 0);
            } else {
                mclass = ClassType.make("gnu.xquery.util.RelativeStepFilter");
                initMethod = mclass.getDeclaredMethod("<init>", 1);
            }
            mconsumer = scope.addVariable(code, mclass, (String) null);
            new ConsumerTarget(mconsumer);
            mtarget = target2;
            code.emitNew(mclass);
            code.emitDup((Type) mclass);
            tconsumer = ((ConsumerTarget) target3).getConsumerVariable();
            if (expectedKind != 'N') {
                code.emitLoad(tconsumer);
            }
            code.emitInvoke(initMethod);
            code.emitStore(mconsumer);
        }
        ValuesMap.compileInlined((LambdaExp) exp2, exp1, 1, (Method) null, comp, mtarget);
        if (expectedKind == 'N') {
            code.emitLoad(mconsumer);
            code.emitLoad(tconsumer);
            code.emitInvokeStatic(Compilation.typeValues.getDeclaredMethod("writeValues", 2));
        } else if (expectedKind == ' ') {
            code.emitLoad(mconsumer);
            code.emitInvoke(mclass.getDeclaredMethod("finish", 0));
        }
        Scope popScope = code.popScope();
    }

    public Type getReturnType(Expression[] expressionArr) {
        Expression[] expressionArr2 = expressionArr;
        return Type.pointer_type;
    }

    public static TreeScanner extractStep(Expression expression) {
        Expression exp = expression;
        while (exp instanceof ApplyExp) {
            ApplyExp aexp = (ApplyExp) exp;
            Expression func = aexp.getFunction();
            if (func instanceof QuoteExp) {
                Object value = ((QuoteExp) func).getValue();
                if (value instanceof TreeScanner) {
                    return (TreeScanner) value;
                }
                if (value instanceof ValuesFilter) {
                    exp = aexp.getArgs()[0];
                }
            }
            return null;
        }
        return null;
    }
}

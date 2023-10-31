package gnu.xquery.util;

import com.google.appinventor.components.common.PropertyTypeConstants;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConsumerTarget;
import gnu.expr.Declaration;
import gnu.expr.ErrorExp;
import gnu.expr.Expression;
import gnu.expr.IfExp;
import gnu.expr.InlineCalls;
import gnu.expr.LambdaExp;
import gnu.expr.LetExp;
import gnu.expr.PrimProcedure;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.Target;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.ValuesMap;
import gnu.kawa.reflect.CompileReflect;
import gnu.kawa.xml.CoerceNodes;
import gnu.kawa.xml.SortNodes;
import gnu.kawa.xml.XDataType;
import gnu.mapping.Procedure;
import gnu.math.IntNum;
import gnu.xquery.lang.XQuery;

public class CompileMisc {
    static final Method castMethod = typeXDataType.getDeclaredMethod("cast", 1);
    static final Method castableMethod = typeXDataType.getDeclaredMethod("castable", 1);
    static final ClassType typeTuples = ClassType.make("gnu.xquery.util.OrderedTuples");
    static final ClassType typeXDataType = ClassType.make("gnu.kawa.xml.XDataType");

    public CompileMisc() {
    }

    public static Expression validateCompare(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        ApplyExp applyExp2;
        Expression expression;
        ApplyExp exp = applyExp;
        InlineCalls visitor = inlineCalls;
        Type type2 = type;
        Procedure proc = procedure;
        exp.visitArgs(visitor);
        Expression folded = exp.inlineIfConstant(proc, visitor);
        if (folded != exp) {
            return folded;
        }
        Compare cproc = (Compare) proc;
        if ((cproc.flags & 32) == 0) {
            ApplyExp applyExp3 = applyExp2;
            Method declaredMethod = ClassType.make("gnu.xquery.util.Compare").getDeclaredMethod("apply", 4);
            Expression[] expressionArr = new Expression[4];
            new QuoteExp(IntNum.make(cproc.flags));
            expressionArr[0] = expression;
            Expression[] expressionArr2 = expressionArr;
            expressionArr2[1] = exp.getArg(0);
            Expression[] expressionArr3 = expressionArr2;
            expressionArr3[2] = exp.getArg(1);
            Expression[] expressionArr4 = expressionArr3;
            expressionArr4[3] = QuoteExp.nullExp;
            new ApplyExp(declaredMethod, expressionArr4);
            exp = applyExp3;
        }
        if (exp.getTypeRaw() == null) {
            exp.setType(XDataType.booleanType);
        }
        return exp;
    }

    public static Expression validateBooleanValue(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        Expression expression;
        ApplyExp exp = applyExp;
        InlineCalls visitor = inlineCalls;
        Type type2 = type;
        Procedure procedure2 = procedure;
        exp.visitArgs(visitor);
        Expression[] args = exp.getArgs();
        if (args.length == 1) {
            Expression arg = args[0];
            Type type3 = arg.getType();
            if (type3 == XDataType.booleanType) {
                return arg;
            }
            if (type3 == null) {
                exp.setType(XDataType.booleanType);
            }
            if (arg instanceof QuoteExp) {
                try {
                    return BooleanValue.booleanValue(((QuoteExp) arg).getValue()) ? XQuery.trueExp : XQuery.falseExp;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    String message = "cannot convert to a boolean";
                    visitor.getMessages().error('e', message);
                    new ErrorExp(message);
                    return expression;
                }
            }
        }
        return exp;
    }

    public static Expression validateArithOp(ApplyExp applyExp, InlineCalls visitor, Type type, Procedure procedure) {
        ApplyExp exp = applyExp;
        Type type2 = type;
        Procedure procedure2 = procedure;
        exp.visitArgs(visitor);
        return exp;
    }

    public static Expression validateApplyValuesFilter(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure proc) {
        Type seqType;
        Expression expression;
        Method sizeMethod;
        Expression pred;
        Expression expression2;
        ApplyExp applyExp2;
        Expression expression3;
        Expression expression4;
        Expression expression5;
        LetExp letExp;
        Declaration declaration;
        Expression expression6;
        Expression expression7;
        Expression expression8;
        Expression expression9;
        Expression expression10;
        Expression expression11;
        Expression expression12;
        Expression expression13;
        ApplyExp exp = applyExp;
        InlineCalls visitor = inlineCalls;
        Type required = type;
        ValuesFilter vproc = (ValuesFilter) proc;
        exp.visitArgs(visitor);
        Expression[] args = exp.getArgs();
        Expression exp2 = args[1];
        if (exp2 instanceof LambdaExp) {
            LambdaExp lambdaExp = (LambdaExp) exp2;
            LambdaExp lexp2 = lambdaExp;
            if (lambdaExp.min_args == 3 && lexp2.max_args == 3) {
                exp.setType(args[0].getType());
                Compilation parser = visitor.getCompilation();
                Declaration dotArg = lexp2.firstDecl();
                Declaration posArg = dotArg.nextDecl();
                Declaration lastArg = posArg.nextDecl();
                lexp2.setInlineOnly(true);
                lexp2.returnContinuation = exp;
                lexp2.inlineHome = visitor.getCurrentLambda();
                lexp2.remove(posArg, lastArg);
                lexp2.min_args = 2;
                lexp2.max_args = 2;
                if (!lastArg.getCanRead() && vproc.kind != 'R') {
                    return exp;
                }
                parser.letStart();
                Expression seq = args[0];
                if (vproc.kind == 'P') {
                    seqType = seq.getType();
                    sizeMethod = Compilation.typeValues.getDeclaredMethod("countValues", 1);
                } else {
                    seqType = SortNodes.typeSortedNodes;
                    Expression expression14 = expression;
                    new ApplyExp((Procedure) SortNodes.sortNodes, seq);
                    seq = expression14;
                    sizeMethod = CoerceNodes.typeNodes.getDeclaredMethod(PropertyTypeConstants.PROPERTY_TYPE_FAB_SIZE, 0);
                }
                Declaration sequence = parser.letVariable("sequence", seqType, seq);
                parser.letEnter();
                Expression pred2 = lexp2.body;
                if (lexp2.body.getType() != XDataType.booleanType) {
                    Expression expression15 = expression12;
                    ValuesFilter valuesFilter = vproc;
                    Method method = ValuesFilter.matchesMethod;
                    Expression[] expressionArr = new Expression[2];
                    expressionArr[0] = pred2;
                    Expression[] expressionArr2 = expressionArr;
                    new ReferenceExp(posArg);
                    expressionArr2[1] = expression13;
                    new ApplyExp(method, expressionArr2);
                    pred2 = expression15;
                }
                if (vproc.kind == 'R') {
                    new Declaration((Object) null, (Type) Type.intType);
                    Declaration posIncoming = declaration;
                    Expression expression16 = expression6;
                    AddOp addOp = AddOp.$Mn;
                    Expression[] expressionArr3 = new Expression[2];
                    new ReferenceExp(lastArg);
                    expressionArr3[0] = expression7;
                    Expression[] expressionArr4 = expressionArr3;
                    new ReferenceExp(posIncoming);
                    expressionArr4[1] = expression8;
                    new ApplyExp((Procedure) addOp, expressionArr4);
                    Expression init = expression16;
                    Expression expression17 = expression9;
                    AddOp addOp2 = AddOp.$Pl;
                    Expression[] expressionArr5 = new Expression[2];
                    expressionArr5[0] = init;
                    Expression[] expressionArr6 = expressionArr5;
                    new QuoteExp(IntNum.one());
                    expressionArr6[1] = expression10;
                    new ApplyExp((Procedure) addOp2, expressionArr6);
                    Expression init2 = expression17;
                    Expression expression18 = expression11;
                    new LetExp(new Expression[]{init2});
                    Expression let = expression18;
                    lexp2.replaceFollowing(dotArg, posIncoming);
                    let.add(posArg);
                    let.body = pred2;
                    pred2 = let;
                }
                new ReferenceExp(dotArg);
                new IfExp(pred2, expression2, QuoteExp.voidExp);
                lexp2.body = pred;
                ApplyExp applyExp3 = applyExp2;
                ValuesMap valuesMap = ValuesMap.valuesMapWithPos;
                Expression[] expressionArr7 = new Expression[2];
                expressionArr7[0] = lexp2;
                Expression[] expressionArr8 = expressionArr7;
                new ReferenceExp(sequence);
                expressionArr8[1] = expression3;
                new ApplyExp((Procedure) valuesMap, expressionArr8);
                ApplyExp doMap = applyExp3;
                doMap.setType(dotArg.getType());
                lexp2.returnContinuation = doMap;
                Expression expression19 = expression4;
                new ReferenceExp(sequence);
                new ApplyExp(sizeMethod, expression5);
                Expression lastInit = expression19;
                LetExp letExp2 = letExp;
                new LetExp(new Expression[]{lastInit});
                LetExp let2 = letExp2;
                let2.add(lastArg);
                let2.body = gnu.kawa.functions.CompileMisc.validateApplyValuesMap(doMap, visitor, required, ValuesMap.valuesMapWithPos);
                return parser.letDone(let2);
            }
        }
        return exp;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x02d5, code lost:
        r22 = (gnu.expr.LambdaExp) r21;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static gnu.expr.Expression validateApplyRelativeStep(gnu.expr.ApplyExp r36, gnu.expr.InlineCalls r37, gnu.bytecode.Type r38, gnu.mapping.Procedure r39) {
        /*
            r2 = r36
            r3 = r37
            r4 = r38
            r5 = r39
            r25 = r2
            r26 = r3
            r25.visitArgs(r26)
            r25 = r2
            gnu.expr.Expression[] r25 = r25.getArgs()
            r6 = r25
            r25 = r6
            r26 = 0
            r25 = r25[r26]
            r7 = r25
            r25 = r6
            r26 = 1
            r25 = r25[r26]
            r8 = r25
            r25 = r3
            gnu.expr.Compilation r25 = r25.getCompilation()
            r10 = r25
            r25 = r8
            r0 = r25
            boolean r0 = r0 instanceof gnu.expr.LambdaExp
            r25 = r0
            if (r25 == 0) goto L_0x006d
            r25 = r10
            r0 = r25
            boolean r0 = r0.mustCompile
            r25 = r0
            if (r25 == 0) goto L_0x006d
            r25 = r8
            gnu.expr.LambdaExp r25 = (gnu.expr.LambdaExp) r25
            r35 = r25
            r25 = r35
            r26 = r35
            r9 = r26
            r0 = r25
            int r0 = r0.min_args
            r25 = r0
            r26 = 3
            r0 = r25
            r1 = r26
            if (r0 != r1) goto L_0x006d
            r25 = r9
            r0 = r25
            int r0 = r0.max_args
            r25 = r0
            r26 = 3
            r0 = r25
            r1 = r26
            if (r0 == r1) goto L_0x0072
        L_0x006d:
            r25 = r2
            r2 = r25
        L_0x0071:
            return r2
        L_0x0072:
            r25 = r9
            r26 = 1
            r25.setInlineOnly(r26)
            r25 = r9
            r26 = r2
            r0 = r26
            r1 = r25
            r1.returnContinuation = r0
            r25 = r9
            r26 = r3
            gnu.expr.LambdaExp r26 = r26.getCurrentLambda()
            r0 = r26
            r1 = r25
            r1.inlineHome = r0
            r25 = r9
            r0 = r25
            gnu.expr.Expression r0 = r0.body
            r25 = r0
            r8 = r25
            r25 = r9
            gnu.expr.Declaration r25 = r25.firstDecl()
            r11 = r25
            r25 = r11
            gnu.expr.Declaration r25 = r25.nextDecl()
            r12 = r25
            r25 = r12
            gnu.expr.Declaration r25 = r25.nextDecl()
            r13 = r25
            r25 = r12
            r26 = r13
            gnu.expr.Declaration r26 = r26.nextDecl()
            r25.setNext(r26)
            r25 = r13
            r26 = 0
            r25.setNext(r26)
            r25 = r9
            r26 = 2
            r0 = r26
            r1 = r25
            r1.min_args = r0
            r25 = r9
            r26 = 2
            r0 = r26
            r1 = r25
            r1.max_args = r0
            r25 = r7
            gnu.bytecode.Type r25 = r25.getType()
            r14 = r25
            r25 = r14
            if (r25 == 0) goto L_0x014a
            gnu.kawa.xml.NodeType r25 = gnu.kawa.xml.NodeType.anyNodeTest
            r26 = r14
            int r25 = r25.compare(r26)
            r26 = -3
            r0 = r25
            r1 = r26
            if (r0 != r1) goto L_0x014a
            r25 = r3
            gnu.expr.Compilation r25 = r25.getCompilation()
            gnu.expr.Language r25 = r25.getLanguage()
            r15 = r25
            java.lang.StringBuilder r25 = new java.lang.StringBuilder
            r35 = r25
            r25 = r35
            r26 = r35
            r26.<init>()
            java.lang.String r26 = "step input is "
            java.lang.StringBuilder r25 = r25.append(r26)
            r26 = r15
            r27 = r14
            java.lang.String r26 = r26.formatType(r27)
            java.lang.StringBuilder r25 = r25.append(r26)
            java.lang.String r26 = " - not a node sequence"
            java.lang.StringBuilder r25 = r25.append(r26)
            java.lang.String r25 = r25.toString()
            r16 = r25
            r25 = r3
            gnu.text.SourceMessages r25 = r25.getMessages()
            r26 = 101(0x65, float:1.42E-43)
            r27 = r16
            r25.error(r26, r27)
            gnu.expr.ErrorExp r25 = new gnu.expr.ErrorExp
            r35 = r25
            r25 = r35
            r26 = r35
            r27 = r16
            r26.<init>(r27)
            r2 = r25
            goto L_0x0071
        L_0x014a:
            r25 = r2
            gnu.bytecode.Type r25 = r25.getTypeRaw()
            r15 = r25
            r25 = r15
            if (r25 == 0) goto L_0x0160
            r25 = r15
            gnu.bytecode.ClassType r26 = gnu.bytecode.Type.pointer_type
            r0 = r25
            r1 = r26
            if (r0 != r1) goto L_0x018d
        L_0x0160:
            r25 = r8
            gnu.bytecode.Type r25 = r25.getType()
            r18 = r25
            r25 = r18
            gnu.bytecode.Type r25 = gnu.kawa.reflect.OccurrenceType.itemPrimeType(r25)
            r16 = r25
            gnu.kawa.xml.NodeType r25 = gnu.kawa.xml.NodeType.anyNodeTest
            r26 = r16
            int r25 = r25.compare(r26)
            r17 = r25
            r25 = r17
            if (r25 < 0) goto L_0x0283
            r25 = r16
            gnu.bytecode.Type r25 = gnu.kawa.xml.NodeSetType.getInstance(r25)
            r15 = r25
        L_0x0186:
            r25 = r2
            r26 = r15
            r25.setType(r26)
        L_0x018d:
            r25 = r13
            boolean r25 = r25.getCanRead()
            if (r25 == 0) goto L_0x0291
            gnu.bytecode.ClassType r25 = gnu.kawa.xml.CoerceNodes.typeNodes
            r18 = r25
            r25 = r10
            r25.letStart()
            r25 = r10
            r26 = 0
            r27 = r18
            gnu.expr.ApplyExp r28 = new gnu.expr.ApplyExp
            r35 = r28
            r28 = r35
            r29 = r35
            gnu.kawa.xml.CoerceNodes r30 = gnu.kawa.xml.CoerceNodes.coerceNodes
            r31 = 1
            r0 = r31
            gnu.expr.Expression[] r0 = new gnu.expr.Expression[r0]
            r31 = r0
            r35 = r31
            r31 = r35
            r32 = r35
            r33 = 0
            r34 = r7
            r32[r33] = r34
            r29.<init>((gnu.mapping.Procedure) r30, (gnu.expr.Expression[]) r31)
            gnu.expr.Declaration r25 = r25.letVariable(r26, r27, r28)
            r19 = r25
            r25 = r10
            r25.letEnter()
            r25 = r18
            java.lang.String r26 = "size"
            r27 = 0
            gnu.bytecode.Method r25 = r25.getDeclaredMethod((java.lang.String) r26, (int) r27)
            r20 = r25
            gnu.expr.ApplyExp r25 = new gnu.expr.ApplyExp
            r35 = r25
            r25 = r35
            r26 = r35
            r27 = r20
            r28 = 1
            r0 = r28
            gnu.expr.Expression[] r0 = new gnu.expr.Expression[r0]
            r28 = r0
            r35 = r28
            r28 = r35
            r29 = r35
            r30 = 0
            gnu.expr.ReferenceExp r31 = new gnu.expr.ReferenceExp
            r35 = r31
            r31 = r35
            r32 = r35
            r33 = r19
            r32.<init>((gnu.expr.Declaration) r33)
            r29[r30] = r31
            r26.<init>((gnu.bytecode.Method) r27, (gnu.expr.Expression[]) r28)
            r21 = r25
            gnu.expr.LetExp r25 = new gnu.expr.LetExp
            r35 = r25
            r25 = r35
            r26 = r35
            r27 = 1
            r0 = r27
            gnu.expr.Expression[] r0 = new gnu.expr.Expression[r0]
            r27 = r0
            r35 = r27
            r27 = r35
            r28 = r35
            r29 = 0
            r30 = r21
            r28[r29] = r30
            r26.<init>(r27)
            r22 = r25
            r25 = r22
            r26 = r13
            r25.addDeclaration((gnu.expr.Declaration) r26)
            r25 = r22
            gnu.expr.ApplyExp r26 = new gnu.expr.ApplyExp
            r35 = r26
            r26 = r35
            r27 = r35
            r28 = r2
            gnu.expr.Expression r28 = r28.getFunction()
            r29 = 2
            r0 = r29
            gnu.expr.Expression[] r0 = new gnu.expr.Expression[r0]
            r29 = r0
            r35 = r29
            r29 = r35
            r30 = r35
            r31 = 0
            gnu.expr.ReferenceExp r32 = new gnu.expr.ReferenceExp
            r35 = r32
            r32 = r35
            r33 = r35
            r34 = r19
            r33.<init>((gnu.expr.Declaration) r34)
            r30[r31] = r32
            r35 = r29
            r29 = r35
            r30 = r35
            r31 = 1
            r32 = r9
            r30[r31] = r32
            r27.<init>((gnu.expr.Expression) r28, (gnu.expr.Expression[]) r29)
            r0 = r26
            r1 = r25
            r1.body = r0
            r25 = r10
            r26 = r22
            gnu.expr.LetExp r25 = r25.letDone(r26)
            r2 = r25
            goto L_0x0071
        L_0x0283:
            r25 = r16
            r26 = 0
            r27 = -1
            gnu.bytecode.Type r25 = gnu.kawa.reflect.OccurrenceType.getInstance(r25, r26, r27)
            r15 = r25
            goto L_0x0186
        L_0x0291:
            r25 = r2
            r18 = r25
            r25 = r8
            r0 = r25
            boolean r0 = r0 instanceof gnu.expr.ApplyExp
            r25 = r0
            if (r25 == 0) goto L_0x0347
            r25 = r8
            gnu.expr.ApplyExp r25 = (gnu.expr.ApplyExp) r25
            r19 = r25
            r25 = r19
            gnu.expr.Expression r25 = r25.getFunction()
            java.lang.Object r25 = r25.valueIfConstant()
            r20 = r25
            r25 = r20
            r0 = r25
            boolean r0 = r0 instanceof gnu.xquery.util.ValuesFilter
            r25 = r0
            if (r25 == 0) goto L_0x0347
            r25 = r19
            gnu.expr.Expression[] r25 = r25.getArgs()
            r26 = 1
            r25 = r25[r26]
            r35 = r25
            r25 = r35
            r26 = r35
            r21 = r26
            r0 = r25
            boolean r0 = r0 instanceof gnu.expr.LambdaExp
            r25 = r0
            if (r25 == 0) goto L_0x0347
            r25 = r21
            gnu.expr.LambdaExp r25 = (gnu.expr.LambdaExp) r25
            r22 = r25
            r25 = r22
            gnu.expr.Declaration r25 = r25.firstDecl()
            r23 = r25
            r25 = r23
            if (r25 == 0) goto L_0x0347
            r25 = r23
            gnu.expr.Declaration r25 = r25.nextDecl()
            r35 = r25
            r25 = r35
            r26 = r35
            r24 = r26
            if (r25 == 0) goto L_0x0347
            r25 = r24
            gnu.expr.Declaration r25 = r25.nextDecl()
            if (r25 != 0) goto L_0x0347
            r25 = r24
            boolean r25 = r25.getCanRead()
            if (r25 != 0) goto L_0x0347
            java.lang.String r25 = "java.lang.Number"
            gnu.bytecode.ClassType r25 = gnu.bytecode.ClassType.make(r25)
            r26 = r22
            r0 = r26
            gnu.expr.Expression r0 = r0.body
            r26 = r0
            gnu.bytecode.Type r26 = r26.getType()
            int r25 = r25.compare(r26)
            r26 = -3
            r0 = r25
            r1 = r26
            if (r0 != r1) goto L_0x0347
            r25 = r19
            r26 = 0
            gnu.expr.Expression r25 = r25.getArg(r26)
            r8 = r25
            r25 = r9
            r26 = r8
            r0 = r26
            r1 = r25
            r1.body = r0
            r25 = r19
            r26 = 0
            r27 = r2
            r25.setArg(r26, r27)
            r25 = r19
            r18 = r25
        L_0x0347:
            r25 = r7
            r0 = r25
            boolean r0 = r0 instanceof gnu.expr.ApplyExp
            r25 = r0
            if (r25 == 0) goto L_0x0414
            r25 = r8
            r0 = r25
            boolean r0 = r0 instanceof gnu.expr.ApplyExp
            r25 = r0
            if (r25 == 0) goto L_0x0414
            r25 = r7
            gnu.expr.ApplyExp r25 = (gnu.expr.ApplyExp) r25
            r19 = r25
            r25 = r8
            gnu.expr.ApplyExp r25 = (gnu.expr.ApplyExp) r25
            r20 = r25
            r25 = r19
            gnu.expr.Expression r25 = r25.getFunction()
            java.lang.Object r25 = r25.valueIfConstant()
            r21 = r25
            r25 = r20
            gnu.expr.Expression r25 = r25.getFunction()
            java.lang.Object r25 = r25.valueIfConstant()
            r22 = r25
            r25 = r21
            gnu.xquery.util.RelativeStep r26 = gnu.xquery.util.RelativeStep.relativeStep
            r0 = r25
            r1 = r26
            if (r0 != r1) goto L_0x0414
            r25 = r22
            r0 = r25
            boolean r0 = r0 instanceof gnu.kawa.xml.ChildAxis
            r25 = r0
            if (r25 == 0) goto L_0x0414
            r25 = r19
            int r25 = r25.getArgCount()
            r26 = 2
            r0 = r25
            r1 = r26
            if (r0 != r1) goto L_0x0414
            r25 = r19
            r26 = 1
            gnu.expr.Expression r25 = r25.getArg(r26)
            r35 = r25
            r25 = r35
            r26 = r35
            r23 = r26
            r0 = r25
            boolean r0 = r0 instanceof gnu.expr.LambdaExp
            r25 = r0
            if (r25 == 0) goto L_0x0414
            r25 = r23
            gnu.expr.LambdaExp r25 = (gnu.expr.LambdaExp) r25
            r24 = r25
            r25 = r24
            r0 = r25
            gnu.expr.Expression r0 = r0.body
            r25 = r0
            r0 = r25
            boolean r0 = r0 instanceof gnu.expr.ApplyExp
            r25 = r0
            if (r25 == 0) goto L_0x0414
            r25 = r24
            r0 = r25
            gnu.expr.Expression r0 = r0.body
            r25 = r0
            gnu.expr.ApplyExp r25 = (gnu.expr.ApplyExp) r25
            gnu.expr.Expression r25 = r25.getFunction()
            java.lang.Object r25 = r25.valueIfConstant()
            gnu.kawa.xml.DescendantOrSelfAxis r26 = gnu.kawa.xml.DescendantOrSelfAxis.anyNode
            r0 = r25
            r1 = r26
            if (r0 != r1) goto L_0x0414
            r25 = r2
            r26 = 0
            r27 = r19
            r28 = 0
            gnu.expr.Expression r27 = r27.getArg(r28)
            r25.setArg(r26, r27)
            r25 = r20
            gnu.expr.QuoteExp r26 = new gnu.expr.QuoteExp
            r35 = r26
            r26 = r35
            r27 = r35
            r28 = r22
            gnu.kawa.xml.ChildAxis r28 = (gnu.kawa.xml.ChildAxis) r28
            gnu.lists.NodePredicate r28 = r28.getNodePredicate()
            gnu.kawa.xml.DescendantAxis r28 = gnu.kawa.xml.DescendantAxis.make(r28)
            r27.<init>(r28)
            r25.setFunction(r26)
        L_0x0414:
            r25 = r18
            r2 = r25
            goto L_0x0071
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xquery.util.CompileMisc.validateApplyRelativeStep(gnu.expr.ApplyExp, gnu.expr.InlineCalls, gnu.bytecode.Type, gnu.mapping.Procedure):gnu.expr.Expression");
    }

    public static Expression validateApplyOrderedMap(ApplyExp applyExp, InlineCalls visitor, Type type, Procedure procedure) {
        Expression expression;
        ApplyExp exp;
        ApplyExp exp2 = applyExp;
        Type type2 = type;
        Procedure proc = procedure;
        exp2.visitArgs(visitor);
        Expression[] args = exp2.getArgs();
        if (args.length <= 2) {
            return exp2;
        }
        Expression[] rargs = new Expression[(args.length - 1)];
        System.arraycopy(args, 1, rargs, 0, rargs.length);
        new ApplyExp(typeTuples.getDeclaredMethod("make$V", 2), rargs);
        new ApplyExp(proc, args[0], expression);
        return exp;
    }

    public static void compileOrderedMap(ApplyExp applyExp, Compilation compilation, Target target, Procedure procedure) {
        Target target2;
        ApplyExp exp = applyExp;
        Compilation comp = compilation;
        Target target3 = target;
        Procedure procedure2 = procedure;
        Expression[] args = exp.getArgs();
        if (args.length != 2) {
            ApplyExp.compile(exp, comp, target3);
            return;
        }
        CodeAttr code = comp.getCode();
        Variable consumer = code.pushScope().addVariable(code, typeTuples, (String) null);
        args[1].compile(comp, Target.pushValue(typeTuples));
        code.emitStore(consumer);
        new ConsumerTarget(consumer);
        args[0].compile(comp, target2);
        Method mm = typeTuples.getDeclaredMethod("run$X", 1);
        code.emitLoad(consumer);
        PrimProcedure.compileInvoke(comp, mm, target3, exp.isTailCall(), 182, Type.pointer_type);
        Scope popScope = code.popScope();
    }

    public static Expression validateApplyCastAs(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        ApplyExp exp;
        ApplyExp exp2 = applyExp;
        InlineCalls visitor = inlineCalls;
        Type type2 = type;
        Procedure procedure2 = procedure;
        exp2.visitArgs(visitor);
        ApplyExp exp3 = CompileReflect.inlineClassName(exp2, 0, visitor);
        Expression[] args = exp3.getArgs();
        if (args.length != 2 || !(args[0] instanceof QuoteExp)) {
            return exp3;
        }
        if (!(((QuoteExp) args[0]).getValue() instanceof XDataType)) {
            return exp3;
        }
        new ApplyExp(castMethod, args);
        return exp;
    }

    public static Expression validateApplyCastableAs(ApplyExp applyExp, InlineCalls inlineCalls, Type type, Procedure procedure) {
        ApplyExp applyExp2;
        ApplyExp exp = applyExp;
        InlineCalls visitor = inlineCalls;
        Type type2 = type;
        Procedure procedure2 = procedure;
        exp.visitArgs(visitor);
        ApplyExp exp2 = CompileReflect.inlineClassName(exp, 1, visitor);
        Expression[] args = exp2.getArgs();
        if (args.length != 2 || !(args[1] instanceof QuoteExp)) {
            return exp2;
        }
        if (!(((QuoteExp) args[1]).getValue() instanceof XDataType)) {
            return exp2;
        }
        ApplyExp exp3 = applyExp2;
        Method method = castableMethod;
        Expression[] expressionArr = new Expression[2];
        expressionArr[0] = args[1];
        Expression[] expressionArr2 = expressionArr;
        expressionArr2[1] = args[0];
        new ApplyExp(method, expressionArr2);
        return exp3;
    }
}

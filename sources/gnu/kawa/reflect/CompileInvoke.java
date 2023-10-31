package gnu.kawa.reflect;

import com.google.appinventor.components.runtime.Component;
import gnu.bytecode.ClassType;
import gnu.bytecode.Member;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.expr.ClassExp;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.Keyword;
import gnu.expr.PrimProcedure;
import gnu.mapping.MethodProc;

public class CompileInvoke {
    public CompileInvoke() {
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v0, resolved type: gnu.bytecode.Type} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v2, resolved type: gnu.bytecode.Type} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v0, resolved type: gnu.bytecode.Type} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v47, resolved type: gnu.bytecode.ArrayType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r39v3, resolved type: gnu.bytecode.ArrayType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r37v22, resolved type: gnu.bytecode.ArrayType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r37v35, resolved type: gnu.bytecode.ArrayType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r37v68, resolved type: gnu.bytecode.ArrayType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v164, resolved type: gnu.bytecode.ArrayType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r37v81, resolved type: gnu.bytecode.ArrayType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r37v83, resolved type: gnu.bytecode.ArrayType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v226, resolved type: gnu.bytecode.ArrayType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r37v126, resolved type: gnu.bytecode.ArrayType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r46v10, resolved type: gnu.expr.ApplyExp} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r41v3, resolved type: gnu.expr.ApplyExp} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v2, resolved type: gnu.expr.ApplyExp} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r38v45, resolved type: gnu.bytecode.ArrayType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r37v141, resolved type: gnu.expr.ApplyExp} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v263, resolved type: gnu.expr.ApplyExp} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v264, resolved type: gnu.expr.ApplyExp} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r28v8, resolved type: gnu.expr.ApplyExp} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r27v10, resolved type: gnu.expr.ApplyExp} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r37v160, resolved type: gnu.bytecode.ArrayType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r38v62, resolved type: gnu.bytecode.ArrayType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r37v167, resolved type: gnu.bytecode.ArrayType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v328, resolved type: gnu.bytecode.ArrayType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v338, resolved type: gnu.bytecode.ArrayType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v348, resolved type: gnu.bytecode.ArrayType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v130, resolved type: gnu.bytecode.ArrayType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v350, resolved type: gnu.bytecode.ClassType} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v1, resolved type: gnu.expr.QuoteExp} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v2, resolved type: gnu.expr.QuoteExp} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v358, resolved type: gnu.expr.QuoteExp} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v3, resolved type: gnu.expr.QuoteExp} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r37v189, resolved type: gnu.expr.QuoteExp} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r41v9, resolved type: gnu.expr.ApplyExp} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r37v195, resolved type: gnu.expr.ApplyExp} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r36v408, resolved type: gnu.expr.ApplyExp} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v5, resolved type: gnu.expr.QuoteExp} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v3, resolved type: gnu.bytecode.ClassType} */
    /* JADX WARNING: type inference failed for: r36v255, types: [gnu.expr.LetExp] */
    /* JADX WARNING: type inference failed for: r1v42, types: [gnu.expr.LetExp] */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x04e1, code lost:
        if ((gnu.kawa.reflect.ClassMethods.selectApplicable(r19, new gnu.bytecode.Type[]{gnu.expr.Compilation.typeClassType}) >> 32) == 1) goto L_0x04e3;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static gnu.expr.Expression validateApplyInvoke(gnu.expr.ApplyExp r47, gnu.expr.InlineCalls r48, gnu.bytecode.Type r49, gnu.mapping.Procedure r50) {
        /*
            r3 = r47
            r4 = r48
            r5 = r49
            r6 = r50
            r36 = r6
            gnu.kawa.reflect.Invoke r36 = (gnu.kawa.reflect.Invoke) r36
            r7 = r36
            r36 = r7
            r0 = r36
            char r0 = r0.kind
            r36 = r0
            r8 = r36
            r36 = r4
            gnu.expr.Compilation r36 = r36.getCompilation()
            r9 = r36
            r36 = r3
            gnu.expr.Expression[] r36 = r36.getArgs()
            r10 = r36
            r36 = r10
            r0 = r36
            int r0 = r0.length
            r36 = r0
            r11 = r36
            r36 = r9
            r0 = r36
            boolean r0 = r0.mustCompile
            r36 = r0
            if (r36 == 0) goto L_0x005d
            r36 = r11
            if (r36 == 0) goto L_0x005d
            r36 = r8
            r37 = 86
            r0 = r36
            r1 = r37
            if (r0 == r1) goto L_0x0053
            r36 = r8
            r37 = 42
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x0069
        L_0x0053:
            r36 = r11
            r37 = 1
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x0069
        L_0x005d:
            r36 = r3
            r37 = r4
            r36.visitArgs(r37)
            r36 = r3
            r3 = r36
        L_0x0068:
            return r3
        L_0x0069:
            r36 = r4
            r37 = r10
            r38 = 0
            r37 = r37[r38]
            r38 = 0
            gnu.expr.Expression r36 = r36.visit((gnu.expr.Expression) r37, (gnu.bytecode.Type) r38)
            r13 = r36
            r36 = r10
            r37 = 0
            r38 = r13
            r36[r37] = r38
            r36 = r8
            r37 = 86
            r0 = r36
            r1 = r37
            if (r0 == r1) goto L_0x0095
            r36 = r8
            r37 = 42
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x01f4
        L_0x0095:
            r36 = r13
            gnu.bytecode.Type r36 = r36.getType()
        L_0x009b:
            r14 = r36
            r36 = r14
            r0 = r36
            boolean r0 = r0 instanceof gnu.expr.PairClassType
            r36 = r0
            if (r36 == 0) goto L_0x0204
            r36 = r8
            r37 = 78
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x0204
            r36 = r14
            gnu.expr.PairClassType r36 = (gnu.expr.PairClassType) r36
            r0 = r36
            gnu.bytecode.ClassType r0 = r0.instanceType
            r36 = r0
            r12 = r36
        L_0x00bd:
            r36 = r10
            r37 = r8
            java.lang.String r36 = getMethodName(r36, r37)
            r15 = r36
            r36 = r8
            r37 = 86
            r0 = r36
            r1 = r37
            if (r0 == r1) goto L_0x00db
            r36 = r8
            r37 = 42
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x021c
        L_0x00db:
            r36 = r11
            r37 = 1
            int r36 = r36 + -1
            r16 = r36
            r36 = 2
            r17 = r36
            r36 = 0
            r18 = r36
        L_0x00eb:
            r36 = r8
            r37 = 78
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x0408
            r36 = r12
            r0 = r36
            boolean r0 = r0 instanceof gnu.bytecode.ArrayType
            r36 = r0
            if (r36 == 0) goto L_0x0408
            r36 = r12
            gnu.bytecode.ArrayType r36 = (gnu.bytecode.ArrayType) r36
            r19 = r36
            r36 = r19
            gnu.bytecode.Type r36 = r36.getComponentType()
            r20 = r36
            r36 = 0
            r21 = r36
            r36 = 0
            r22 = r36
            r36 = r10
            r0 = r36
            int r0 = r0.length
            r36 = r0
            r37 = 3
            r0 = r36
            r1 = r37
            if (r0 < r1) goto L_0x017a
            r36 = r10
            r37 = 1
            r36 = r36[r37]
            r0 = r36
            boolean r0 = r0 instanceof gnu.expr.QuoteExp
            r36 = r0
            if (r36 == 0) goto L_0x017a
            r36 = r10
            r37 = 1
            r36 = r36[r37]
            gnu.expr.QuoteExp r36 = (gnu.expr.QuoteExp) r36
            java.lang.Object r36 = r36.getValue()
            r23 = r36
            r36 = r23
            r0 = r36
            boolean r0 = r0 instanceof gnu.expr.Keyword
            r36 = r0
            if (r36 == 0) goto L_0x017a
            java.lang.String r36 = "length"
            r37 = r23
            gnu.expr.Keyword r37 = (gnu.expr.Keyword) r37
            java.lang.String r37 = r37.getName()
            r46 = r37
            r37 = r46
            r38 = r46
            r15 = r38
            boolean r36 = r36.equals(r37)
            if (r36 != 0) goto L_0x016e
            java.lang.String r36 = "size"
            r37 = r15
            boolean r36 = r36.equals(r37)
            if (r36 == 0) goto L_0x017a
        L_0x016e:
            r36 = r10
            r37 = 2
            r36 = r36[r37]
            r21 = r36
            r36 = 1
            r22 = r36
        L_0x017a:
            r36 = r21
            if (r36 != 0) goto L_0x019a
            java.lang.Integer r36 = new java.lang.Integer
            r46 = r36
            r36 = r46
            r37 = r46
            r38 = r10
            r0 = r38
            int r0 = r0.length
            r38 = r0
            r39 = 1
            int r38 = r38 + -1
            r37.<init>(r38)
            gnu.expr.QuoteExp r36 = gnu.expr.QuoteExp.getInstance(r36)
            r21 = r36
        L_0x019a:
            r36 = r4
            r37 = r21
            gnu.bytecode.PrimType r38 = gnu.bytecode.Type.intType
            gnu.expr.Expression r36 = r36.visit((gnu.expr.Expression) r37, (gnu.bytecode.Type) r38)
            r21 = r36
            gnu.expr.ApplyExp r36 = new gnu.expr.ApplyExp
            r46 = r36
            r36 = r46
            r37 = r46
            gnu.kawa.reflect.ArrayNew r38 = new gnu.kawa.reflect.ArrayNew
            r46 = r38
            r38 = r46
            r39 = r46
            r40 = r20
            r39.<init>(r40)
            r39 = 1
            r0 = r39
            gnu.expr.Expression[] r0 = new gnu.expr.Expression[r0]
            r39 = r0
            r46 = r39
            r39 = r46
            r40 = r46
            r41 = 0
            r42 = r21
            r40[r41] = r42
            r37.<init>((gnu.mapping.Procedure) r38, (gnu.expr.Expression[]) r39)
            r23 = r36
            r36 = r23
            r37 = r19
            r36.setType(r37)
            r36 = r22
            if (r36 == 0) goto L_0x0283
            r36 = r10
            r0 = r36
            int r0 = r0.length
            r36 = r0
            r37 = 3
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x0283
            r36 = r23
            r3 = r36
            goto L_0x0068
        L_0x01f4:
            r36 = r7
            r0 = r36
            gnu.expr.Language r0 = r0.language
            r36 = r0
            r37 = r13
            gnu.bytecode.Type r36 = r36.getTypeFor((gnu.expr.Expression) r37)
            goto L_0x009b
        L_0x0204:
            r36 = r14
            r0 = r36
            boolean r0 = r0 instanceof gnu.bytecode.ObjectType
            r36 = r0
            if (r36 == 0) goto L_0x0216
            r36 = r14
            gnu.bytecode.ObjectType r36 = (gnu.bytecode.ObjectType) r36
            r12 = r36
            goto L_0x00bd
        L_0x0216:
            r36 = 0
            r12 = r36
            goto L_0x00bd
        L_0x021c:
            r36 = r8
            r37 = 78
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x0234
            r36 = r11
            r16 = r36
            r36 = 0
            r17 = r36
            r36 = -1
            r18 = r36
            goto L_0x00eb
        L_0x0234:
            r36 = r8
            r37 = 83
            r0 = r36
            r1 = r37
            if (r0 == r1) goto L_0x0248
            r36 = r8
            r37 = 115(0x73, float:1.61E-43)
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x025a
        L_0x0248:
            r36 = r11
            r37 = 2
            int r36 = r36 + -2
            r16 = r36
            r36 = 2
            r17 = r36
            r36 = -1
            r18 = r36
            goto L_0x00eb
        L_0x025a:
            r36 = r8
            r37 = 80
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x0276
            r36 = r11
            r37 = 2
            int r36 = r36 + -2
            r16 = r36
            r36 = 3
            r17 = r36
            r36 = 1
            r18 = r36
            goto L_0x00eb
        L_0x0276:
            r36 = r3
            r37 = r4
            r36.visitArgs(r37)
            r36 = r3
            r3 = r36
            goto L_0x0068
        L_0x0283:
            gnu.expr.LetExp r36 = new gnu.expr.LetExp
            r46 = r36
            r36 = r46
            r37 = r46
            r38 = 1
            r0 = r38
            gnu.expr.Expression[] r0 = new gnu.expr.Expression[r0]
            r38 = r0
            r46 = r38
            r38 = r46
            r39 = r46
            r40 = 0
            r41 = r23
            r39[r40] = r41
            r37.<init>(r38)
            r24 = r36
            r36 = r24
            r37 = 0
            java.lang.String r37 = (java.lang.String) r37
            r38 = r19
            gnu.expr.Declaration r36 = r36.addDeclaration(r37, r38)
            r25 = r36
            r36 = r25
            r37 = r23
            r36.noteValue(r37)
            gnu.expr.BeginExp r36 = new gnu.expr.BeginExp
            r46 = r36
            r36 = r46
            r37 = r46
            r37.<init>()
            r26 = r36
            r36 = 0
            r27 = r36
            r36 = r22
            if (r36 == 0) goto L_0x03af
            r36 = 3
        L_0x02d0:
            r28 = r36
        L_0x02d2:
            r36 = r28
            r37 = r10
            r0 = r37
            int r0 = r0.length
            r37 = r0
            r0 = r36
            r1 = r37
            if (r0 >= r1) goto L_0x03e6
            r36 = r10
            r37 = r28
            r36 = r36[r37]
            r29 = r36
            r36 = r22
            if (r36 == 0) goto L_0x033a
            r36 = r28
            r37 = 1
            int r36 = r36 + 1
            r37 = r10
            r0 = r37
            int r0 = r0.length
            r37 = r0
            r0 = r36
            r1 = r37
            if (r0 >= r1) goto L_0x033a
            r36 = r29
            r0 = r36
            boolean r0 = r0 instanceof gnu.expr.QuoteExp
            r36 = r0
            if (r36 == 0) goto L_0x033a
            r36 = r29
            gnu.expr.QuoteExp r36 = (gnu.expr.QuoteExp) r36
            java.lang.Object r36 = r36.getValue()
            r30 = r36
            r36 = r30
            r0 = r36
            boolean r0 = r0 instanceof gnu.expr.Keyword
            r36 = r0
            if (r36 == 0) goto L_0x033a
            r36 = r30
            gnu.expr.Keyword r36 = (gnu.expr.Keyword) r36
            java.lang.String r36 = r36.getName()
            r31 = r36
            r36 = r31
            int r36 = java.lang.Integer.parseInt(r36)     // Catch:{ Throwable -> 0x03b3 }
            r27 = r36
            r36 = r10
            int r28 = r28 + 1
            r37 = r28
            r36 = r36[r37]     // Catch:{ Throwable -> 0x03b3 }
            r29 = r36
        L_0x033a:
            r36 = r4
            r37 = r29
            r38 = r20
            gnu.expr.Expression r36 = r36.visit((gnu.expr.Expression) r37, (gnu.bytecode.Type) r38)
            r29 = r36
            r36 = r26
            gnu.expr.ApplyExp r37 = new gnu.expr.ApplyExp
            r46 = r37
            r37 = r46
            r38 = r46
            gnu.kawa.reflect.ArraySet r39 = new gnu.kawa.reflect.ArraySet
            r46 = r39
            r39 = r46
            r40 = r46
            r41 = r20
            r40.<init>(r41)
            r40 = 3
            r0 = r40
            gnu.expr.Expression[] r0 = new gnu.expr.Expression[r0]
            r40 = r0
            r46 = r40
            r40 = r46
            r41 = r46
            r42 = 0
            gnu.expr.ReferenceExp r43 = new gnu.expr.ReferenceExp
            r46 = r43
            r43 = r46
            r44 = r46
            r45 = r25
            r44.<init>((gnu.expr.Declaration) r45)
            r41[r42] = r43
            r46 = r40
            r40 = r46
            r41 = r46
            r42 = 1
            java.lang.Integer r43 = new java.lang.Integer
            r46 = r43
            r43 = r46
            r44 = r46
            r45 = r27
            r44.<init>(r45)
            gnu.expr.QuoteExp r43 = gnu.expr.QuoteExp.getInstance(r43)
            r41[r42] = r43
            r46 = r40
            r40 = r46
            r41 = r46
            r42 = 2
            r43 = r29
            r41[r42] = r43
            r38.<init>((gnu.mapping.Procedure) r39, (gnu.expr.Expression[]) r40)
            r36.add(r37)
            int r27 = r27 + 1
            int r28 = r28 + 1
            goto L_0x02d2
        L_0x03af:
            r36 = 1
            goto L_0x02d0
        L_0x03b3:
            r36 = move-exception
            r32 = r36
            r36 = r9
            r37 = 101(0x65, float:1.42E-43)
            java.lang.StringBuilder r38 = new java.lang.StringBuilder
            r46 = r38
            r38 = r46
            r39 = r46
            r39.<init>()
            java.lang.String r39 = "non-integer keyword '"
            java.lang.StringBuilder r38 = r38.append(r39)
            r39 = r31
            java.lang.StringBuilder r38 = r38.append(r39)
            java.lang.String r39 = "' in array constructor"
            java.lang.StringBuilder r38 = r38.append(r39)
            java.lang.String r38 = r38.toString()
            r36.error(r37, r38)
            r36 = r3
            r3 = r36
            goto L_0x0068
        L_0x03e6:
            r36 = r26
            gnu.expr.ReferenceExp r37 = new gnu.expr.ReferenceExp
            r46 = r37
            r37 = r46
            r38 = r46
            r39 = r25
            r38.<init>((gnu.expr.Declaration) r39)
            r36.add(r37)
            r36 = r24
            r37 = r26
            r0 = r37
            r1 = r36
            r1.body = r0
            r36 = r24
            r3 = r36
            goto L_0x0068
        L_0x0408:
            r36 = r12
            if (r36 == 0) goto L_0x0ce8
            r36 = r15
            if (r36 == 0) goto L_0x0ce8
            r36 = r12
            r0 = r36
            boolean r0 = r0 instanceof gnu.expr.TypeValue
            r36 = r0
            if (r36 == 0) goto L_0x046c
            r36 = r8
            r37 = 78
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x046c
            r36 = r12
            gnu.expr.TypeValue r36 = (gnu.expr.TypeValue) r36
            gnu.mapping.Procedure r36 = r36.getConstructor()
            r19 = r36
            r36 = r19
            if (r36 == 0) goto L_0x046c
            r36 = r11
            r37 = 1
            int r36 = r36 + -1
            r0 = r36
            gnu.expr.Expression[] r0 = new gnu.expr.Expression[r0]
            r36 = r0
            r20 = r36
            r36 = r10
            r37 = 1
            r38 = r20
            r39 = 0
            r40 = r11
            r41 = 1
            int r40 = r40 + -1
            java.lang.System.arraycopy(r36, r37, r38, r39, r40)
            r36 = r4
            gnu.expr.ApplyExp r37 = new gnu.expr.ApplyExp
            r46 = r37
            r37 = r46
            r38 = r46
            r39 = r19
            r40 = r20
            r38.<init>((gnu.mapping.Procedure) r39, (gnu.expr.Expression[]) r40)
            r38 = r5
            gnu.expr.Expression r36 = r36.visit((gnu.expr.Expression) r37, (gnu.bytecode.Type) r38)
            r3 = r36
            goto L_0x0068
        L_0x046c:
            r36 = r9
            if (r36 != 0) goto L_0x058c
            r36 = 0
        L_0x0472:
            r20 = r36
            r36 = r12
            r21 = r36
            r36 = r21
            r37 = r15
            r38 = r20
            r39 = r7
            gnu.expr.PrimProcedure[] r36 = getMethods(r36, r37, r38, r39)     // Catch:{ Exception -> 0x05aa }
            r19 = r36
            r36 = r19
            r37 = r16
            int r36 = gnu.kawa.reflect.ClassMethods.selectApplicable((gnu.expr.PrimProcedure[]) r36, (int) r37)     // Catch:{ Exception -> 0x05aa }
            r22 = r36
            r36 = -1
            r23 = r36
            r36 = r8
            r37 = 78
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x0888
            r36 = 1
            r37 = r10
            int r36 = hasKeywordArgument(r36, r37)
            r46 = r36
            r36 = r46
            r37 = r46
            r25 = r37
            r37 = r10
            r0 = r37
            int r0 = r0.length
            r37 = r0
            r0 = r36
            r1 = r37
            if (r0 < r1) goto L_0x04e3
            r36 = r22
            if (r36 > 0) goto L_0x0888
            r36 = r19
            r37 = 1
            r0 = r37
            gnu.bytecode.Type[] r0 = new gnu.bytecode.Type[r0]
            r37 = r0
            r46 = r37
            r37 = r46
            r38 = r46
            r39 = 0
            gnu.bytecode.ClassType r40 = gnu.expr.Compilation.typeClassType
            r38[r39] = r40
            long r36 = gnu.kawa.reflect.ClassMethods.selectApplicable((gnu.expr.PrimProcedure[]) r36, (gnu.bytecode.Type[]) r37)
            r38 = 32
            long r36 = r36 >> r38
            r38 = 1
            int r36 = (r36 > r38 ? 1 : (r36 == r38 ? 0 : -1))
            if (r36 != 0) goto L_0x0888
        L_0x04e3:
            r36 = r21
            r37 = r10
            r38 = r25
            r39 = r20
            java.lang.Object[] r36 = checkKeywords(r36, r37, r38, r39)
            r46 = r36
            r36 = r46
            r37 = r46
            r24 = r37
            r0 = r36
            int r0 = r0.length
            r36 = r0
            r37 = 2
            int r36 = r36 * 2
            r37 = r10
            r0 = r37
            int r0 = r0.length
            r37 = r0
            r38 = r25
            int r37 = r37 - r38
            r0 = r36
            r1 = r37
            if (r0 == r1) goto L_0x052e
            r36 = r21
            java.lang.String r37 = "add"
            r38 = 86
            r39 = 0
            r40 = r7
            r0 = r40
            gnu.expr.Language r0 = r0.language
            r40 = r0
            gnu.expr.PrimProcedure[] r36 = gnu.kawa.reflect.ClassMethods.getMethods(r36, r37, r38, r39, r40)
            r37 = 2
            int r36 = gnu.kawa.reflect.ClassMethods.selectApplicable((gnu.expr.PrimProcedure[]) r36, (int) r37)
            if (r36 <= 0) goto L_0x0888
        L_0x052e:
            r36 = 0
            r26 = r36
            r36 = 0
            r27 = r36
        L_0x0536:
            r36 = r27
            r37 = r24
            r0 = r37
            int r0 = r0.length
            r37 = r0
            r0 = r36
            r1 = r37
            if (r0 >= r1) goto L_0x05e4
            r36 = r24
            r37 = r27
            r36 = r36[r37]
            r0 = r36
            boolean r0 = r0 instanceof java.lang.String
            r36 = r0
            if (r36 == 0) goto L_0x0589
            r36 = r26
            if (r36 != 0) goto L_0x05da
            java.lang.StringBuffer r36 = new java.lang.StringBuffer
            r46 = r36
            r36 = r46
            r37 = r46
            r37.<init>()
            r26 = r36
            r36 = r26
            java.lang.String r37 = "no field or setter "
            java.lang.StringBuffer r36 = r36.append(r37)
        L_0x056d:
            r36 = r26
            r37 = 96
            java.lang.StringBuffer r36 = r36.append(r37)
            r36 = r26
            r37 = r24
            r38 = r27
            r37 = r37[r38]
            java.lang.StringBuffer r36 = r36.append(r37)
            r36 = r26
            r37 = 39
            java.lang.StringBuffer r36 = r36.append(r37)
        L_0x0589:
            int r27 = r27 + 1
            goto L_0x0536
        L_0x058c:
            r36 = r9
            r0 = r36
            gnu.bytecode.ClassType r0 = r0.curClass
            r36 = r0
            if (r36 == 0) goto L_0x05a0
            r36 = r9
            r0 = r36
            gnu.bytecode.ClassType r0 = r0.curClass
            r36 = r0
            goto L_0x0472
        L_0x05a0:
            r36 = r9
            r0 = r36
            gnu.bytecode.ClassType r0 = r0.mainClass
            r36 = r0
            goto L_0x0472
        L_0x05aa:
            r36 = move-exception
            r23 = r36
            r36 = r9
            r37 = 119(0x77, float:1.67E-43)
            java.lang.StringBuilder r38 = new java.lang.StringBuilder
            r46 = r38
            r38 = r46
            r39 = r46
            r39.<init>()
            java.lang.String r39 = "unknown class: "
            java.lang.StringBuilder r38 = r38.append(r39)
            r39 = r12
            java.lang.String r39 = r39.getName()
            java.lang.StringBuilder r38 = r38.append(r39)
            java.lang.String r38 = r38.toString()
            r36.error(r37, r38)
            r36 = r3
            r3 = r36
            goto L_0x0068
        L_0x05da:
            r36 = r26
            java.lang.String r37 = ", "
            java.lang.StringBuffer r36 = r36.append(r37)
            goto L_0x056d
        L_0x05e4:
            r36 = r26
            if (r36 == 0) goto L_0x0610
            r36 = r26
            java.lang.String r37 = " in class "
            java.lang.StringBuffer r36 = r36.append(r37)
            r36 = r26
            r37 = r12
            java.lang.String r37 = r37.getName()
            java.lang.StringBuffer r36 = r36.append(r37)
            r36 = r9
            r37 = 119(0x77, float:1.67E-43)
            r38 = r26
            java.lang.String r38 = r38.toString()
            r36.error(r37, r38)
            r36 = r3
            r3 = r36
            goto L_0x0068
        L_0x0610:
            r36 = r25
            r37 = r10
            r0 = r37
            int r0 = r0.length
            r37 = r0
            r0 = r36
            r1 = r37
            if (r0 >= r1) goto L_0x0721
            r36 = r25
            r0 = r36
            gnu.expr.Expression[] r0 = new gnu.expr.Expression[r0]
            r36 = r0
            r28 = r36
            r36 = r10
            r37 = 0
            r38 = r28
            r39 = 0
            r40 = r25
            java.lang.System.arraycopy(r36, r37, r38, r39, r40)
            r36 = r4
            gnu.expr.ApplyExp r37 = new gnu.expr.ApplyExp
            r46 = r37
            r37 = r46
            r38 = r46
            r39 = r3
            gnu.expr.Expression r39 = r39.getFunction()
            r40 = r28
            r38.<init>((gnu.expr.Expression) r39, (gnu.expr.Expression[]) r40)
            r38 = r21
            gnu.expr.Expression r36 = r36.visit((gnu.expr.Expression) r37, (gnu.bytecode.Type) r38)
            gnu.expr.ApplyExp r36 = (gnu.expr.ApplyExp) r36
            r27 = r36
        L_0x0655:
            r36 = r27
            r37 = r21
            r36.setType(r37)
            r36 = r27
            r28 = r36
            r36 = r10
            r0 = r36
            int r0 = r0.length
            r36 = r0
            if (r36 <= 0) goto L_0x0874
            r36 = 0
            r29 = r36
        L_0x066d:
            r36 = r29
            r37 = r24
            r0 = r37
            int r0 = r0.length
            r37 = r0
            r0 = r36
            r1 = r37
            if (r0 >= r1) goto L_0x0766
            r36 = r24
            r37 = r29
            r36 = r36[r37]
            r30 = r36
            r36 = r30
            r0 = r36
            boolean r0 = r0 instanceof gnu.bytecode.Method
            r36 = r0
            if (r36 == 0) goto L_0x074a
            r36 = r30
            gnu.bytecode.Method r36 = (gnu.bytecode.Method) r36
            gnu.bytecode.Type[] r36 = r36.getParameterTypes()
            r37 = 0
            r36 = r36[r37]
            r31 = r36
        L_0x069c:
            r36 = r31
            if (r36 == 0) goto L_0x06b0
            r36 = r7
            r0 = r36
            gnu.expr.Language r0 = r0.language
            r36 = r0
            r37 = r31
            gnu.bytecode.Type r36 = r36.getLangTypeFor(r37)
            r31 = r36
        L_0x06b0:
            r36 = r4
            r37 = r10
            r38 = r25
            r39 = 2
            r40 = r29
            int r39 = r39 * r40
            int r38 = r38 + r39
            r39 = 1
            int r38 = r38 + 1
            r37 = r37[r38]
            r38 = r31
            gnu.expr.Expression r36 = r36.visit((gnu.expr.Expression) r37, (gnu.bytecode.Type) r38)
            r32 = r36
            r36 = 3
            r0 = r36
            gnu.expr.Expression[] r0 = new gnu.expr.Expression[r0]
            r36 = r0
            r46 = r36
            r36 = r46
            r37 = r46
            r38 = 0
            r39 = r27
            r37[r38] = r39
            r46 = r36
            r36 = r46
            r37 = r46
            r38 = 1
            gnu.expr.QuoteExp r39 = new gnu.expr.QuoteExp
            r46 = r39
            r39 = r46
            r40 = r46
            r41 = r30
            r40.<init>(r41)
            r37[r38] = r39
            r46 = r36
            r36 = r46
            r37 = r46
            r38 = 2
            r39 = r32
            r37[r38] = r39
            r33 = r36
            gnu.expr.ApplyExp r36 = new gnu.expr.ApplyExp
            r46 = r36
            r36 = r46
            r37 = r46
            gnu.kawa.reflect.SlotSet r38 = gnu.kawa.reflect.SlotSet.setFieldReturnObject
            r39 = r33
            r37.<init>((gnu.mapping.Procedure) r38, (gnu.expr.Expression[]) r39)
            r27 = r36
            r36 = r27
            r37 = r21
            r36.setType(r37)
            int r29 = r29 + 1
            goto L_0x066d
        L_0x0721:
            gnu.expr.ApplyExp r36 = new gnu.expr.ApplyExp
            r46 = r36
            r36 = r46
            r37 = r46
            r38 = r19
            r39 = 0
            r38 = r38[r39]
            r39 = 1
            r0 = r39
            gnu.expr.Expression[] r0 = new gnu.expr.Expression[r0]
            r39 = r0
            r46 = r39
            r39 = r46
            r40 = r46
            r41 = 0
            r42 = r13
            r40[r41] = r42
            r37.<init>((gnu.mapping.Procedure) r38, (gnu.expr.Expression[]) r39)
            r27 = r36
            goto L_0x0655
        L_0x074a:
            r36 = r30
            r0 = r36
            boolean r0 = r0 instanceof gnu.bytecode.Field
            r36 = r0
            if (r36 == 0) goto L_0x0760
            r36 = r30
            gnu.bytecode.Field r36 = (gnu.bytecode.Field) r36
            gnu.bytecode.Type r36 = r36.getType()
            r31 = r36
            goto L_0x069c
        L_0x0760:
            r36 = 0
            r31 = r36
            goto L_0x069c
        L_0x0766:
            r36 = r25
            r37 = r10
            r0 = r37
            int r0 = r0.length
            r37 = r0
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x0843
            r36 = 1
        L_0x0777:
            r29 = r36
            r36 = r27
            r28 = r36
            r36 = r29
            r37 = r10
            r0 = r37
            int r0 = r0.length
            r37 = r0
            r0 = r36
            r1 = r37
            if (r0 >= r1) goto L_0x0874
            gnu.expr.LetExp r36 = new gnu.expr.LetExp
            r46 = r36
            r36 = r46
            r37 = r46
            r38 = 1
            r0 = r38
            gnu.expr.Expression[] r0 = new gnu.expr.Expression[r0]
            r38 = r0
            r46 = r38
            r38 = r46
            r39 = r46
            r40 = 0
            r41 = r28
            r39[r40] = r41
            r37.<init>(r38)
            r30 = r36
            r36 = r30
            r37 = 0
            java.lang.String r37 = (java.lang.String) r37
            r38 = r21
            gnu.expr.Declaration r36 = r36.addDeclaration(r37, r38)
            r31 = r36
            r36 = r31
            r37 = r28
            r36.noteValue(r37)
            gnu.expr.BeginExp r36 = new gnu.expr.BeginExp
            r46 = r36
            r36 = r46
            r37 = r46
            r37.<init>()
            r32 = r36
            r36 = r29
            r33 = r36
        L_0x07d3:
            r36 = r33
            r37 = r10
            r0 = r37
            int r0 = r0.length
            r37 = r0
            r0 = r36
            r1 = r37
            if (r0 >= r1) goto L_0x0854
            r36 = 3
            r0 = r36
            gnu.expr.Expression[] r0 = new gnu.expr.Expression[r0]
            r36 = r0
            r46 = r36
            r36 = r46
            r37 = r46
            r38 = 0
            gnu.expr.ReferenceExp r39 = new gnu.expr.ReferenceExp
            r46 = r39
            r39 = r46
            r40 = r46
            r41 = r31
            r40.<init>((gnu.expr.Declaration) r41)
            r37[r38] = r39
            r46 = r36
            r36 = r46
            r37 = r46
            r38 = 1
            java.lang.String r39 = "add"
            gnu.expr.QuoteExp r39 = gnu.expr.QuoteExp.getInstance(r39)
            r37[r38] = r39
            r46 = r36
            r36 = r46
            r37 = r46
            r38 = 2
            r39 = r10
            r40 = r33
            r39 = r39[r40]
            r37[r38] = r39
            r34 = r36
            r36 = r32
            r37 = r4
            gnu.expr.ApplyExp r38 = new gnu.expr.ApplyExp
            r46 = r38
            r38 = r46
            r39 = r46
            gnu.kawa.reflect.Invoke r40 = gnu.kawa.reflect.Invoke.invoke
            r41 = r34
            r39.<init>((gnu.mapping.Procedure) r40, (gnu.expr.Expression[]) r41)
            r39 = 0
            gnu.expr.Expression r37 = r37.visit((gnu.expr.Expression) r38, (gnu.bytecode.Type) r39)
            r36.add(r37)
            int r33 = r33 + 1
            goto L_0x07d3
        L_0x0843:
            r36 = 2
            r37 = r24
            r0 = r37
            int r0 = r0.length
            r37 = r0
            int r36 = r36 * r37
            r37 = r25
            int r36 = r36 + r37
            goto L_0x0777
        L_0x0854:
            r36 = r32
            gnu.expr.ReferenceExp r37 = new gnu.expr.ReferenceExp
            r46 = r37
            r37 = r46
            r38 = r46
            r39 = r31
            r38.<init>((gnu.expr.Declaration) r39)
            r36.add(r37)
            r36 = r30
            r37 = r32
            r0 = r37
            r1 = r36
            r1.body = r0
            r36 = r30
            r28 = r36
        L_0x0874:
            r36 = r4
            r37 = r28
            r38 = r3
            gnu.expr.Expression r37 = r37.setLine((gnu.expr.Expression) r38)
            r38 = r5
            gnu.expr.Expression r36 = r36.checkType(r37, r38)
            r3 = r36
            goto L_0x0068
        L_0x0888:
            r36 = r22
            if (r36 < 0) goto L_0x0b42
            r36 = 1
            r28 = r36
        L_0x0890:
            r36 = r28
            r37 = r11
            r0 = r36
            r1 = r37
            if (r0 >= r1) goto L_0x09bc
            r36 = 0
            r29 = r36
            r36 = r28
            r37 = r11
            r38 = 1
            int r37 = r37 + -1
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x08f3
            r36 = 1
        L_0x08ae:
            r30 = r36
            r36 = r8
            r37 = 80
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x08c4
            r36 = r28
            r37 = 2
            r0 = r36
            r1 = r37
            if (r0 == r1) goto L_0x08d8
        L_0x08c4:
            r36 = r8
            r37 = 78
            r0 = r36
            r1 = r37
            if (r0 == r1) goto L_0x08f6
            r36 = r28
            r37 = 1
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x08f6
        L_0x08d8:
            r36 = 0
            r29 = r36
        L_0x08dc:
            r36 = r10
            r37 = r28
            r38 = r4
            r39 = r10
            r40 = r28
            r39 = r39[r40]
            r40 = r29
            gnu.expr.Expression r38 = r38.visit((gnu.expr.Expression) r39, (gnu.bytecode.Type) r40)
            r36[r37] = r38
            int r28 = r28 + 1
            goto L_0x0890
        L_0x08f3:
            r36 = 0
            goto L_0x08ae
        L_0x08f6:
            r36 = r8
            r37 = 80
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x090f
            r36 = r28
            r37 = 1
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x090f
            r36 = r21
            r29 = r36
            goto L_0x08dc
        L_0x090f:
            r36 = r22
            if (r36 <= 0) goto L_0x08dc
            r36 = r28
            r37 = r8
            r38 = 78
            r0 = r37
            r1 = r38
            if (r0 != r1) goto L_0x0979
            r37 = 1
        L_0x0921:
            int r36 = r36 - r37
            r31 = r36
            r36 = 0
            r32 = r36
        L_0x0929:
            r36 = r32
            r37 = r22
            r0 = r36
            r1 = r37
            if (r0 >= r1) goto L_0x08dc
            r36 = r19
            r37 = r32
            r36 = r36[r37]
            r33 = r36
            r36 = r31
            r37 = r8
            r38 = 83
            r0 = r37
            r1 = r38
            if (r0 == r1) goto L_0x097c
            r37 = r33
            boolean r37 = r37.takesTarget()
            if (r37 == 0) goto L_0x097c
            r37 = 1
        L_0x0951:
            int r36 = r36 + r37
            r34 = r36
            r36 = r30
            if (r36 == 0) goto L_0x097f
            r36 = r33
            boolean r36 = r36.takesVarArgs()
            if (r36 == 0) goto L_0x097f
            r36 = r34
            r37 = r33
            int r37 = r37.minArgs()
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x097f
            r36 = 0
            r29 = r36
        L_0x0973:
            r36 = r29
            if (r36 != 0) goto L_0x09b8
            goto L_0x08dc
        L_0x0979:
            r37 = r17
            goto L_0x0921
        L_0x097c:
            r37 = 0
            goto L_0x0951
        L_0x097f:
            r36 = r33
            r37 = r34
            gnu.bytecode.Type r36 = r36.getParameterType(r37)
            r35 = r36
            r36 = r32
            if (r36 != 0) goto L_0x0992
            r36 = r35
            r29 = r36
            goto L_0x0973
        L_0x0992:
            r36 = r35
            r0 = r36
            boolean r0 = r0 instanceof gnu.bytecode.PrimType
            r36 = r0
            r37 = r29
            r0 = r37
            boolean r0 = r0 instanceof gnu.bytecode.PrimType
            r37 = r0
            r0 = r36
            r1 = r37
            if (r0 == r1) goto L_0x09ad
            r36 = 0
            r29 = r36
            goto L_0x0973
        L_0x09ad:
            r36 = r29
            r37 = r35
            gnu.bytecode.Type r36 = gnu.bytecode.Type.lowestCommonSuperType(r36, r37)
            r29 = r36
            goto L_0x0973
        L_0x09b8:
            int r32 = r32 + 1
            goto L_0x0929
        L_0x09bc:
            r36 = r19
            r37 = r21
            r38 = r10
            r39 = r16
            r40 = r17
            r41 = r18
            long r36 = selectApplicable(r36, r37, r38, r39, r40, r41)
            r28 = r36
            r36 = r28
            r38 = 32
            long r36 = r36 >> r38
            r0 = r36
            int r0 = (int) r0
            r36 = r0
            r26 = r36
            r36 = r28
            r0 = r36
            int r0 = (int) r0
            r36 = r0
            r27 = r36
        L_0x09e4:
            r36 = r19
            r0 = r36
            int r0 = r0.length
            r36 = r0
            r28 = r36
            r36 = r26
            r37 = r27
            int r36 = r36 + r37
            if (r36 != 0) goto L_0x0a42
            r36 = r8
            r37 = 78
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x0a42
            r36 = r21
            java.lang.String r37 = "valueOf"
            r38 = r20
            gnu.kawa.reflect.Invoke r39 = gnu.kawa.reflect.Invoke.invokeStatic
            gnu.expr.PrimProcedure[] r36 = getMethods(r36, r37, r38, r39)
            r19 = r36
            r36 = 1
            r17 = r36
            r36 = r11
            r37 = 1
            int r36 = r36 + -1
            r16 = r36
            r36 = r19
            r37 = r21
            r38 = r10
            r39 = r16
            r40 = r17
            r41 = -1
            long r36 = selectApplicable(r36, r37, r38, r39, r40, r41)
            r29 = r36
            r36 = r29
            r38 = 32
            long r36 = r36 >> r38
            r0 = r36
            int r0 = (int) r0
            r36 = r0
            r26 = r36
            r36 = r29
            r0 = r36
            int r0 = (int) r0
            r36 = r0
            r27 = r36
        L_0x0a42:
            r36 = r26
            r37 = r27
            int r36 = r36 + r37
            if (r36 != 0) goto L_0x0b85
            r36 = r8
            r37 = 80
            r0 = r36
            r1 = r37
            if (r0 == r1) goto L_0x0a5c
            r36 = r9
            boolean r36 = r36.warnInvokeUnknownMethod()
            if (r36 == 0) goto L_0x0adb
        L_0x0a5c:
            r36 = r8
            r37 = 78
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x0a84
            java.lang.StringBuilder r36 = new java.lang.StringBuilder
            r46 = r36
            r36 = r46
            r37 = r46
            r37.<init>()
            r37 = r15
            java.lang.StringBuilder r36 = r36.append(r37)
            java.lang.String r37 = "/valueOf"
            java.lang.StringBuilder r36 = r36.append(r37)
            java.lang.String r36 = r36.toString()
            r15 = r36
        L_0x0a84:
            java.lang.StringBuilder r36 = new java.lang.StringBuilder
            r46 = r36
            r36 = r46
            r37 = r46
            r37.<init>()
            r29 = r36
            r36 = r28
            r37 = r19
            r0 = r37
            int r0 = r0.length
            r37 = r0
            int r36 = r36 + r37
            if (r36 != 0) goto L_0x0b4c
            r36 = r29
            java.lang.String r37 = "no accessible method '"
            java.lang.StringBuilder r36 = r36.append(r37)
        L_0x0aa7:
            r36 = r29
            r37 = r15
            java.lang.StringBuilder r36 = r36.append(r37)
            r36 = r29
            java.lang.String r37 = "' in "
            java.lang.StringBuilder r36 = r36.append(r37)
            r36 = r29
            r37 = r12
            java.lang.String r37 = r37.getName()
            java.lang.StringBuilder r36 = r36.append(r37)
            r36 = r9
            r37 = r8
            r38 = 80
            r0 = r37
            r1 = r38
            if (r0 != r1) goto L_0x0b81
            r37 = 101(0x65, float:1.42E-43)
        L_0x0ad2:
            r38 = r29
            java.lang.String r38 = r38.toString()
            r36.error(r37, r38)
        L_0x0adb:
            r36 = r23
            if (r36 < 0) goto L_0x0ce8
            r36 = r16
            r0 = r36
            gnu.expr.Expression[] r0 = new gnu.expr.Expression[r0]
            r36 = r0
            r29 = r36
            r36 = r19
            r37 = r23
            r36 = r36[r37]
            r30 = r36
            r36 = r30
            boolean r36 = r36.takesVarArgs()
            r31 = r36
            r36 = 0
            r32 = r36
            r36 = r18
            if (r36 < 0) goto L_0x0b0f
            r36 = r29
            r37 = r32
            int r32 = r32 + 1
            r38 = r10
            r39 = r18
            r38 = r38[r39]
            r36[r37] = r38
        L_0x0b0f:
            r36 = r17
            r33 = r36
        L_0x0b13:
            r36 = r33
            r37 = r10
            r0 = r37
            int r0 = r0.length
            r37 = r0
            r0 = r36
            r1 = r37
            if (r0 >= r1) goto L_0x0cc1
            r36 = r32
            r37 = r29
            r0 = r37
            int r0 = r0.length
            r37 = r0
            r0 = r36
            r1 = r37
            if (r0 >= r1) goto L_0x0cc1
            r36 = r29
            r37 = r32
            r38 = r10
            r39 = r33
            r38 = r38[r39]
            r36[r37] = r38
            int r33 = r33 + 1
            int r32 = r32 + 1
            goto L_0x0b13
        L_0x0b42:
            r36 = 0
            r26 = r36
            r36 = 0
            r27 = r36
            goto L_0x09e4
        L_0x0b4c:
            r36 = r22
            r37 = -983040(0xfffffffffff10000, float:NaN)
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x0b61
            r36 = r29
            java.lang.String r37 = "too few arguments for method '"
            java.lang.StringBuilder r36 = r36.append(r37)
            goto L_0x0aa7
        L_0x0b61:
            r36 = r22
            r37 = -917504(0xfffffffffff20000, float:NaN)
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x0b76
            r36 = r29
            java.lang.String r37 = "too many arguments for method '"
            java.lang.StringBuilder r36 = r36.append(r37)
            goto L_0x0aa7
        L_0x0b76:
            r36 = r29
            java.lang.String r37 = "no possibly applicable method '"
            java.lang.StringBuilder r36 = r36.append(r37)
            goto L_0x0aa7
        L_0x0b81:
            r37 = 119(0x77, float:1.67E-43)
            goto L_0x0ad2
        L_0x0b85:
            r36 = r26
            r37 = 1
            r0 = r36
            r1 = r37
            if (r0 == r1) goto L_0x0b9d
            r36 = r26
            if (r36 != 0) goto L_0x0ba3
            r36 = r27
            r37 = 1
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x0ba3
        L_0x0b9d:
            r36 = 0
            r23 = r36
            goto L_0x0adb
        L_0x0ba3:
            r36 = r26
            if (r36 <= 0) goto L_0x0c57
            r36 = r19
            r37 = r26
            int r36 = gnu.mapping.MethodProc.mostSpecific((gnu.mapping.MethodProc[]) r36, (int) r37)
            r23 = r36
            r36 = r23
            if (r36 >= 0) goto L_0x0be1
            r36 = r8
            r37 = 83
            r0 = r36
            r1 = r37
            if (r0 != r1) goto L_0x0be1
            r36 = 0
            r29 = r36
        L_0x0bc3:
            r36 = r29
            r37 = r26
            r0 = r36
            r1 = r37
            if (r0 >= r1) goto L_0x0be1
            r36 = r19
            r37 = r29
            r36 = r36[r37]
            boolean r36 = r36.getStaticFlag()
            if (r36 == 0) goto L_0x0c50
            r36 = r23
            if (r36 < 0) goto L_0x0c4c
            r36 = -1
            r23 = r36
        L_0x0be1:
            r36 = r23
            if (r36 >= 0) goto L_0x0adb
            r36 = r8
            r37 = 80
            r0 = r36
            r1 = r37
            if (r0 == r1) goto L_0x0bf7
            r36 = r9
            boolean r36 = r36.warnInvokeUnknownMethod()
            if (r36 == 0) goto L_0x0adb
        L_0x0bf7:
            java.lang.StringBuffer r36 = new java.lang.StringBuffer
            r46 = r36
            r36 = r46
            r37 = r46
            r37.<init>()
            r29 = r36
            r36 = r29
            java.lang.String r37 = "more than one definitely applicable method `"
            java.lang.StringBuffer r36 = r36.append(r37)
            r36 = r29
            r37 = r15
            java.lang.StringBuffer r36 = r36.append(r37)
            r36 = r29
            java.lang.String r37 = "' in "
            java.lang.StringBuffer r36 = r36.append(r37)
            r36 = r29
            r37 = r12
            java.lang.String r37 = r37.getName()
            java.lang.StringBuffer r36 = r36.append(r37)
            r36 = r19
            r37 = r26
            r38 = r29
            append(r36, r37, r38)
            r36 = r9
            r37 = r8
            r38 = 80
            r0 = r37
            r1 = r38
            if (r0 != r1) goto L_0x0c54
            r37 = 101(0x65, float:1.42E-43)
        L_0x0c41:
            r38 = r29
            java.lang.String r38 = r38.toString()
            r36.error(r37, r38)
            goto L_0x0adb
        L_0x0c4c:
            r36 = r29
            r23 = r36
        L_0x0c50:
            int r29 = r29 + 1
            goto L_0x0bc3
        L_0x0c54:
            r37 = 119(0x77, float:1.67E-43)
            goto L_0x0c41
        L_0x0c57:
            r36 = r8
            r37 = 80
            r0 = r36
            r1 = r37
            if (r0 == r1) goto L_0x0c69
            r36 = r9
            boolean r36 = r36.warnInvokeUnknownMethod()
            if (r36 == 0) goto L_0x0adb
        L_0x0c69:
            java.lang.StringBuffer r36 = new java.lang.StringBuffer
            r46 = r36
            r36 = r46
            r37 = r46
            r37.<init>()
            r29 = r36
            r36 = r29
            java.lang.String r37 = "more than one possibly applicable method '"
            java.lang.StringBuffer r36 = r36.append(r37)
            r36 = r29
            r37 = r15
            java.lang.StringBuffer r36 = r36.append(r37)
            r36 = r29
            java.lang.String r37 = "' in "
            java.lang.StringBuffer r36 = r36.append(r37)
            r36 = r29
            r37 = r12
            java.lang.String r37 = r37.getName()
            java.lang.StringBuffer r36 = r36.append(r37)
            r36 = r19
            r37 = r27
            r38 = r29
            append(r36, r37, r38)
            r36 = r9
            r37 = r8
            r38 = 80
            r0 = r37
            r1 = r38
            if (r0 != r1) goto L_0x0cbe
            r37 = 101(0x65, float:1.42E-43)
        L_0x0cb3:
            r38 = r29
            java.lang.String r38 = r38.toString()
            r36.error(r37, r38)
            goto L_0x0adb
        L_0x0cbe:
            r37 = 119(0x77, float:1.67E-43)
            goto L_0x0cb3
        L_0x0cc1:
            gnu.expr.ApplyExp r36 = new gnu.expr.ApplyExp
            r46 = r36
            r36 = r46
            r37 = r46
            r38 = r30
            r39 = r29
            r37.<init>((gnu.mapping.Procedure) r38, (gnu.expr.Expression[]) r39)
            r33 = r36
            r36 = r33
            r37 = r3
            gnu.expr.Expression r36 = r36.setLine((gnu.expr.Expression) r37)
            r36 = r4
            r37 = r33
            r38 = r5
            gnu.expr.Expression r36 = r36.visitApplyOnly(r37, r38)
            r3 = r36
            goto L_0x0068
        L_0x0ce8:
            r36 = r3
            r37 = r4
            r36.visitArgs(r37)
            r36 = r3
            r3 = r36
            goto L_0x0068
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.reflect.CompileInvoke.validateApplyInvoke(gnu.expr.ApplyExp, gnu.expr.InlineCalls, gnu.bytecode.Type, gnu.mapping.Procedure):gnu.expr.Expression");
    }

    static Object[] checkKeywords(ObjectType objectType, Expression[] expressionArr, int i, ClassType classType) {
        Member member;
        ObjectType type = objectType;
        Expression[] args = expressionArr;
        int start = i;
        ClassType caller = classType;
        int len = args.length;
        int npairs = 0;
        while (start + (2 * npairs) + 1 < len && (args[start + (2 * npairs)].valueIfConstant() instanceof Keyword)) {
            npairs++;
        }
        Object[] fields = new Object[npairs];
        for (int i2 = 0; i2 < npairs; i2++) {
            String name = ((Keyword) args[start + (2 * i2)].valueIfConstant()).getName();
            Member slot = SlotSet.lookupMember(type, name, caller);
            if (slot == null) {
                slot = type.getMethod(ClassExp.slotToMethodName(Component.DEFAULT_VALUE_FAB_ICON_NAME, name), SlotSet.type1Array);
            }
            Object[] objArr = fields;
            int i3 = i2;
            if (slot != null) {
                member = slot;
            } else {
                member = name;
            }
            objArr[i3] = member;
        }
        return fields;
    }

    private static String getMethodName(Expression[] expressionArr, char c) {
        Expression[] args = expressionArr;
        char kind = c;
        if (kind == 'N') {
            return "<init>";
        }
        int nameIndex = kind == 'P' ? 2 : 1;
        if (args.length >= nameIndex + 1) {
            return ClassMethods.checkName(args[nameIndex], false);
        }
        return null;
    }

    private static void append(PrimProcedure[] primProcedureArr, int i, StringBuffer stringBuffer) {
        PrimProcedure[] methods = primProcedureArr;
        int mcount = i;
        StringBuffer sbuf = stringBuffer;
        for (int i2 = 0; i2 < mcount; i2++) {
            StringBuffer append = sbuf.append("\n  candidate: ");
            StringBuffer append2 = sbuf.append(methods[i2]);
        }
    }

    protected static PrimProcedure[] getMethods(ObjectType ctype, String mname, ClassType classType, Invoke invoke) {
        ClassType caller = classType;
        Invoke iproc = invoke;
        int kind = iproc.kind;
        return ClassMethods.getMethods(ctype, mname, kind == 80 ? 'P' : (kind == 42 || kind == 86) ? 'V' : 0, caller, iproc.language);
    }

    static int hasKeywordArgument(int argsStartIndex, Expression[] expressionArr) {
        Expression[] args = expressionArr;
        for (int i = argsStartIndex; i < args.length; i++) {
            if (args[i].valueIfConstant() instanceof Keyword) {
                return i;
            }
        }
        return args.length;
    }

    private static long selectApplicable(PrimProcedure[] primProcedureArr, ObjectType objectType, Expression[] expressionArr, int margsLength, int i, int objIndex) {
        PrimProcedure[] methods = primProcedureArr;
        ObjectType ctype = objectType;
        Expression[] args = expressionArr;
        int argsStartIndex = i;
        Type[] atypes = new Type[margsLength];
        int dst = 0;
        if (objIndex >= 0) {
            dst = 0 + 1;
            atypes[0] = ctype;
        }
        int src = argsStartIndex;
        while (src < args.length && dst < atypes.length) {
            Expression arg = args[src];
            Type atype = null;
            if (InlineCalls.checkIntValue(arg) != null) {
                atype = Type.intType;
            } else if (InlineCalls.checkLongValue(arg) != null) {
                atype = Type.longType;
            } else if (0 == 0) {
                atype = arg.getType();
            }
            atypes[dst] = atype;
            src++;
            dst++;
        }
        return ClassMethods.selectApplicable(methods, atypes);
    }

    public static synchronized PrimProcedure getStaticMethod(ClassType classType, String str, Expression[] expressionArr) {
        int index;
        PrimProcedure primProcedure;
        PrimProcedure primProcedure2;
        ClassType type = classType;
        String name = str;
        Expression[] args = expressionArr;
        synchronized (CompileInvoke.class) {
            PrimProcedure[] methods = getMethods(type, name, (ClassType) null, Invoke.invokeStatic);
            long num = selectApplicable(methods, type, args, args.length, 0, -1);
            int okCount = (int) (num >> 32);
            int maybeCount = (int) num;
            if (methods == null) {
                index = -1;
            } else if (okCount > 0) {
                index = MethodProc.mostSpecific((MethodProc[]) methods, okCount);
            } else if (maybeCount == 1) {
                index = 0;
            } else {
                index = -1;
            }
            if (index < 0) {
                primProcedure = null;
            } else {
                primProcedure = methods[index];
            }
            primProcedure2 = primProcedure;
        }
        return primProcedure2;
    }
}

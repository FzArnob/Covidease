package kawa.lang;

import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.ModuleExp;
import gnu.expr.ScopeExp;
import gnu.lists.Consumer;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.OutPort;
import gnu.mapping.Symbol;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.PrintWriter;
import java.util.Vector;

public class SyntaxPattern extends Pattern implements Externalizable {
    static final int MATCH_ANY = 3;
    static final int MATCH_ANY_CAR = 7;
    static final int MATCH_EQUALS = 2;
    static final int MATCH_IGNORE = 24;
    static final int MATCH_LENGTH = 6;
    static final int MATCH_LREPEAT = 5;
    static final int MATCH_MISC = 0;
    static final int MATCH_NIL = 8;
    static final int MATCH_PAIR = 4;
    static final int MATCH_VECTOR = 16;
    static final int MATCH_WIDE = 1;
    Object[] literals;
    String program;
    int varCount;

    public int varCount() {
        return this.varCount;
    }

    public boolean match(Object obj, Object[] vars, int start_vars) {
        return match(obj, vars, start_vars, 0, (SyntaxForm) null);
    }

    public SyntaxPattern(String program2, Object[] literals2, int varCount2) {
        this.program = program2;
        this.literals = literals2;
        this.varCount = varCount2;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SyntaxPattern(java.lang.Object r12, java.lang.Object[] r13, kawa.lang.Translator r14) {
        /*
            r11 = this;
            r0 = r11
            r1 = r12
            r2 = r13
            r3 = r14
            r4 = r0
            java.lang.StringBuffer r5 = new java.lang.StringBuffer
            r10 = r5
            r5 = r10
            r6 = r10
            r6.<init>()
            r6 = r1
            r7 = 0
            r8 = r2
            r9 = r3
            r4.<init>(r5, r6, r7, r8, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.lang.SyntaxPattern.<init>(java.lang.Object, java.lang.Object[], kawa.lang.Translator):void");
    }

    SyntaxPattern(StringBuffer stringBuffer, Object pattern, SyntaxForm syntaxForm, Object[] literal_identifiers, Translator translator) {
        Vector vector;
        StringBuffer programbuf = stringBuffer;
        SyntaxForm syntaxForm2 = syntaxForm;
        Translator tr = translator;
        new Vector();
        Vector literalsbuf = vector;
        translate(pattern, programbuf, literal_identifiers, 0, literalsbuf, (SyntaxForm) null, 0, tr);
        this.program = programbuf.toString();
        this.literals = new Object[literalsbuf.size()];
        literalsbuf.copyInto(this.literals);
        this.varCount = tr.patternScope.pattern_names.size();
    }

    public void disassemble() {
        disassemble(OutPort.errDefault(), (Translator) Compilation.getCurrent(), 0, this.program.length());
    }

    public void disassemble(PrintWriter ps, Translator tr) {
        disassemble(ps, tr, 0, this.program.length());
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void disassemble(java.io.PrintWriter r18, kawa.lang.Translator r19, int r20, int r21) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r20
            r4 = r21
            r10 = 0
            r5 = r10
            r10 = r2
            if (r10 == 0) goto L_0x001a
            r10 = r2
            kawa.lang.PatternScope r10 = r10.patternScope
            if (r10 == 0) goto L_0x001a
            r10 = r2
            kawa.lang.PatternScope r10 = r10.patternScope
            java.util.Vector r10 = r10.pattern_names
            r5 = r10
        L_0x001a:
            r10 = 0
            r6 = r10
            r10 = r3
            r7 = r10
        L_0x001e:
            r10 = r7
            r11 = r4
            if (r10 >= r11) goto L_0x02af
            r10 = r0
            java.lang.String r10 = r10.program
            r11 = r7
            char r10 = r10.charAt(r11)
            r8 = r10
            r10 = r1
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r16 = r11
            r11 = r16
            r12 = r16
            r12.<init>()
            java.lang.String r12 = " "
            java.lang.StringBuilder r11 = r11.append(r12)
            r12 = r7
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.String r12 = ": "
            java.lang.StringBuilder r11 = r11.append(r12)
            r12 = r8
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.String r11 = r11.toString()
            r10.print(r11)
            int r7 = r7 + 1
            r10 = r8
            r11 = 7
            r10 = r10 & 7
            r9 = r10
            r10 = r6
            r11 = 13
            int r10 = r10 << 13
            r11 = r8
            r12 = 3
            int r11 = r11 >> 3
            r10 = r10 | r11
            r6 = r10
            r10 = r9
            switch(r10) {
                case 0: goto L_0x0252;
                case 1: goto L_0x0099;
                case 2: goto L_0x00ba;
                case 3: goto L_0x00fe;
                case 4: goto L_0x014a;
                case 5: goto L_0x0172;
                case 6: goto L_0x0216;
                case 7: goto L_0x00fe;
                default: goto L_0x006c;
            }
        L_0x006c:
            r10 = r1
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r16 = r11
            r11 = r16
            r12 = r16
            r12.<init>()
            java.lang.String r12 = " - "
            java.lang.StringBuilder r11 = r11.append(r12)
            r12 = r9
            java.lang.StringBuilder r11 = r11.append(r12)
            r12 = 47
            java.lang.StringBuilder r11 = r11.append(r12)
            r12 = r6
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.String r11 = r11.toString()
            r10.println(r11)
        L_0x0096:
            r10 = 0
            r6 = r10
            goto L_0x001e
        L_0x0099:
            r10 = r1
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r16 = r11
            r11 = r16
            r12 = r16
            r12.<init>()
            java.lang.String r12 = " - WIDE "
            java.lang.StringBuilder r11 = r11.append(r12)
            r12 = r6
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.String r11 = r11.toString()
            r10.println(r11)
            goto L_0x001e
        L_0x00ba:
            r10 = r1
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r16 = r11
            r11 = r16
            r12 = r16
            r12.<init>()
            java.lang.String r12 = " - EQUALS["
            java.lang.StringBuilder r11 = r11.append(r12)
            r12 = r6
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.String r12 = "]"
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.String r11 = r11.toString()
            r10.print(r11)
            r10 = r0
            java.lang.Object[] r10 = r10.literals
            if (r10 == 0) goto L_0x00f9
            r10 = r6
            if (r10 < 0) goto L_0x00f9
            r10 = r6
            r11 = r0
            java.lang.Object[] r11 = r11.literals
            int r11 = r11.length
            if (r10 >= r11) goto L_0x00f9
            r10 = r1
            r11 = r0
            java.lang.Object[] r11 = r11.literals
            r12 = r6
            r11 = r11[r12]
            r10.print(r11)
        L_0x00f9:
            r10 = r1
            r10.println()
            goto L_0x0096
        L_0x00fe:
            r10 = r1
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r16 = r11
            r11 = r16
            r12 = r16
            r12.<init>()
            r12 = r9
            r13 = 3
            if (r12 != r13) goto L_0x0146
            java.lang.String r12 = " - ANY["
        L_0x0111:
            java.lang.StringBuilder r11 = r11.append(r12)
            r12 = r6
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.String r12 = "]"
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.String r11 = r11.toString()
            r10.print(r11)
            r10 = r5
            if (r10 == 0) goto L_0x0140
            r10 = r6
            if (r10 < 0) goto L_0x0140
            r10 = r6
            r11 = r5
            int r11 = r11.size()
            if (r10 >= r11) goto L_0x0140
            r10 = r1
            r11 = r5
            r12 = r6
            java.lang.Object r11 = r11.elementAt(r12)
            r10.print(r11)
        L_0x0140:
            r10 = r1
            r10.println()
            goto L_0x0096
        L_0x0146:
            java.lang.String r12 = " - ANY_CAR["
            goto L_0x0111
        L_0x014a:
            r10 = r1
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r16 = r11
            r11 = r16
            r12 = r16
            r12.<init>()
            java.lang.String r12 = " - PAIR["
            java.lang.StringBuilder r11 = r11.append(r12)
            r12 = r6
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.String r12 = "]"
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.String r11 = r11.toString()
            r10.println(r11)
            goto L_0x0096
        L_0x0172:
            r10 = r1
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r16 = r11
            r11 = r16
            r12 = r16
            r12.<init>()
            java.lang.String r12 = " - LREPEAT["
            java.lang.StringBuilder r11 = r11.append(r12)
            r12 = r6
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.String r12 = "]"
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.String r11 = r11.toString()
            r10.println(r11)
            r10 = r0
            r11 = r1
            r12 = r2
            r13 = r7
            r14 = r7
            r15 = r6
            int r14 = r14 + r15
            r10.disassemble(r11, r12, r13, r14)
            r10 = r7
            r11 = r6
            int r10 = r10 + r11
            r7 = r10
            r10 = r1
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r16 = r11
            r11 = r16
            r12 = r16
            r12.<init>()
            java.lang.String r12 = " "
            java.lang.StringBuilder r11 = r11.append(r12)
            r12 = r7
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.String r12 = ": - repeat first var:"
            java.lang.StringBuilder r11 = r11.append(r12)
            r12 = r0
            java.lang.String r12 = r12.program
            r13 = r7
            int r7 = r7 + 1
            char r12 = r12.charAt(r13)
            r13 = 3
            int r12 = r12 >> 3
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.String r11 = r11.toString()
            r10.println(r11)
            r10 = r1
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r16 = r11
            r11 = r16
            r12 = r16
            r12.<init>()
            java.lang.String r12 = " "
            java.lang.StringBuilder r11 = r11.append(r12)
            r12 = r7
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.String r12 = ": - repeast nested vars:"
            java.lang.StringBuilder r11 = r11.append(r12)
            r12 = r0
            java.lang.String r12 = r12.program
            r13 = r7
            int r7 = r7 + 1
            char r12 = r12.charAt(r13)
            r13 = 3
            int r12 = r12 >> 3
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.String r11 = r11.toString()
            r10.println(r11)
            goto L_0x0096
        L_0x0216:
            r10 = r1
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r16 = r11
            r11 = r16
            r12 = r16
            r12.<init>()
            java.lang.String r12 = " - LENGTH "
            java.lang.StringBuilder r11 = r11.append(r12)
            r12 = r6
            r13 = 1
            int r12 = r12 >> 1
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.String r12 = " pairs. "
            java.lang.StringBuilder r11 = r11.append(r12)
            r12 = r6
            r13 = 1
            r12 = r12 & 1
            if (r12 != 0) goto L_0x024e
            java.lang.String r12 = "pure list"
        L_0x0241:
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.String r11 = r11.toString()
            r10.println(r11)
            goto L_0x0096
        L_0x024e:
            java.lang.String r12 = "impure list"
            goto L_0x0241
        L_0x0252:
            r10 = r1
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r16 = r11
            r11 = r16
            r12 = r16
            r12.<init>()
            java.lang.String r12 = "[misc ch:"
            java.lang.StringBuilder r11 = r11.append(r12)
            r12 = r8
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.String r12 = " n:"
            java.lang.StringBuilder r11 = r11.append(r12)
            r12 = 8
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.String r12 = "]"
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.String r11 = r11.toString()
            r10.print(r11)
            r10 = r8
            r11 = 8
            if (r10 != r11) goto L_0x0293
            r10 = r1
            java.lang.String r11 = " - NIL"
            r10.println(r11)
            goto L_0x0096
        L_0x0293:
            r10 = r8
            r11 = 16
            if (r10 != r11) goto L_0x02a1
            r10 = r1
            java.lang.String r11 = " - VECTOR"
            r10.println(r11)
            goto L_0x0096
        L_0x02a1:
            r10 = r8
            r11 = 24
            if (r10 != r11) goto L_0x006c
            r10 = r1
            java.lang.String r11 = " - IGNORE"
            r10.println(r11)
            goto L_0x0096
        L_0x02af:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.lang.SyntaxPattern.disassemble(java.io.PrintWriter, kawa.lang.Translator, int, int):void");
    }

    /* access modifiers changed from: package-private */
    public void translate(Object obj, StringBuffer stringBuffer, Object[] objArr, int i, Vector vector, SyntaxForm syntaxForm, char c, Translator translator) {
        StringBuilder sb;
        ScopeExp scope1;
        Object literal;
        ScopeExp scope2;
        Object next;
        int restLength;
        Object pattern = obj;
        StringBuffer program2 = stringBuffer;
        Object[] literal_identifiers = objArr;
        int nesting = i;
        Vector literals2 = vector;
        SyntaxForm syntax = syntaxForm;
        char context = c;
        Translator tr = translator;
        PatternScope patternScope = tr.patternScope;
        Vector patternNames = patternScope.pattern_names;
        while (true) {
            if (pattern instanceof SyntaxForm) {
                syntax = (SyntaxForm) pattern;
                pattern = syntax.getDatum();
            } else if (pattern instanceof Pair) {
                Object savePos = tr.pushPositionOf(pattern);
                try {
                    int start_pc = program2.length();
                    StringBuffer append = program2.append(4);
                    Pair pair = (Pair) pattern;
                    SyntaxForm car_syntax = syntax;
                    Object next2 = pair.getCdr();
                    while (next instanceof SyntaxForm) {
                        syntax = (SyntaxForm) next;
                        next2 = syntax.getDatum();
                    }
                    boolean repeat = false;
                    if ((next instanceof Pair) && tr.matches(((Pair) next).getCar(), "...")) {
                        repeat = true;
                        next = ((Pair) next).getCdr();
                        while (next instanceof SyntaxForm) {
                            syntax = (SyntaxForm) next;
                            next = syntax.getDatum();
                        }
                    }
                    int subvar0 = patternNames.size();
                    if (context == 'P') {
                        context = 0;
                    }
                    translate(pair.getCar(), program2, literal_identifiers, repeat ? nesting + 1 : nesting, literals2, car_syntax, context == 'V' ? 0 : 'P', tr);
                    int subvarN = patternNames.size() - subvar0;
                    int width = (((program2.length() - start_pc) - 1) << 3) | (repeat ? 5 : 4);
                    if (width > 65535) {
                        start_pc += insertInt(start_pc, program2, (width >> 13) + 1);
                    }
                    program2.setCharAt(start_pc, (char) width);
                    int restLength2 = Translator.listLength(next);
                    if (restLength2 == Integer.MIN_VALUE) {
                        Expression syntaxError = tr.syntaxError("cyclic pattern list");
                        tr.popPositionOf(savePos);
                        return;
                    }
                    if (repeat) {
                        addInt(program2, subvar0 << 3);
                        addInt(program2, subvarN << 3);
                        if (next == LList.Empty) {
                            StringBuffer append2 = program2.append(8);
                            tr.popPositionOf(savePos);
                            return;
                        }
                        if (restLength2 >= 0) {
                            restLength = restLength2 << 1;
                        } else {
                            restLength = ((-restLength2) << 1) - 1;
                        }
                        addInt(program2, (restLength << 3) | 6);
                    }
                    pattern = next;
                    tr.popPositionOf(savePos);
                } catch (Throwable th) {
                    Throwable th2 = th;
                    tr.popPositionOf(savePos);
                    throw th2;
                }
            } else if (pattern instanceof Symbol) {
                int j = literal_identifiers.length;
                do {
                    j--;
                    if (j >= 0) {
                        ScopeExp current = tr.currentScope();
                        scope1 = syntax == null ? current : syntax.getScope();
                        literal = literal_identifiers[j];
                        if (literal instanceof SyntaxForm) {
                            SyntaxForm syntax2 = (SyntaxForm) literal;
                            literal = syntax2.getDatum();
                            scope2 = syntax2.getScope();
                        } else if (tr.currentMacroDefinition != null) {
                            scope2 = tr.currentMacroDefinition.getCapturedScope();
                        } else {
                            scope2 = current;
                        }
                    } else {
                        if (patternNames.contains(pattern)) {
                            new StringBuilder();
                            Expression syntaxError2 = tr.syntaxError(sb.append("duplicated pattern variable ").append(pattern).toString());
                        }
                        int i2 = patternNames.size();
                        patternNames.addElement(pattern);
                        boolean matchCar = context == 'P';
                        StringBuffer append3 = patternScope.patternNesting.append((char) ((nesting << 1) + (matchCar ? 1 : 0)));
                        Declaration decl = patternScope.addDeclaration(pattern);
                        decl.setLocation(tr);
                        tr.push(decl);
                        addInt(program2, (i2 << 3) | (matchCar ? 7 : 3));
                        return;
                    }
                } while (!literalIdentifierEq(pattern, scope1, literal, scope2));
                int i3 = SyntaxTemplate.indexOf(literals2, pattern);
                if (i3 < 0) {
                    i3 = literals2.size();
                    literals2.addElement(pattern);
                }
                addInt(program2, (i3 << 3) | 2);
                return;
            } else if (pattern == LList.Empty) {
                StringBuffer append4 = program2.append(8);
                return;
            } else if (pattern instanceof FVector) {
                StringBuffer append5 = program2.append(16);
                pattern = LList.makeList((FVector) pattern);
                context = 'V';
            } else {
                int i4 = SyntaxTemplate.indexOf(literals2, pattern);
                if (i4 < 0) {
                    i4 = literals2.size();
                    literals2.addElement(pattern);
                }
                addInt(program2, (i4 << 3) | 2);
                return;
            }
        }
    }

    private static void addInt(StringBuffer stringBuffer, int i) {
        StringBuffer sbuf = stringBuffer;
        int val = i;
        if (val > 65535) {
            addInt(sbuf, (val << 13) + 1);
        }
        StringBuffer append = sbuf.append((char) val);
    }

    private static int insertInt(int i, StringBuffer stringBuffer, int i2) {
        int offset = i;
        StringBuffer sbuf = stringBuffer;
        int val = i2;
        if (val > 65535) {
            offset += insertInt(offset, sbuf, (val << 13) + 1);
        }
        StringBuffer insert = sbuf.insert(offset, (char) val);
        return offset + 1;
    }

    /* access modifiers changed from: package-private */
    public boolean match_car(Pair pair, Object[] objArr, int i, int i2, SyntaxForm syntaxForm) {
        int value;
        Pair p = pair;
        Object[] vars = objArr;
        int start_vars = i;
        int pc = i2;
        SyntaxForm syntax = syntaxForm;
        int pc_start = pc;
        int i3 = pc;
        int pc2 = pc + 1;
        char charAt = this.program.charAt(i3);
        char ch = charAt;
        int i4 = charAt >> 3;
        while (true) {
            value = i4;
            if ((ch & 7) != 1) {
                break;
            }
            int i5 = pc2;
            pc2++;
            char charAt2 = this.program.charAt(i5);
            ch = charAt2;
            i4 = (value << 13) | (charAt2 >> 3);
        }
        if ((ch & 7) != 7) {
            return match(p.getCar(), vars, start_vars, pc_start, syntax);
        }
        if (syntax != null && !(p.getCar() instanceof SyntaxForm)) {
            p = Translator.makePair(p, SyntaxForms.fromDatum(p.getCar(), syntax), p.getCdr());
        }
        vars[start_vars + value] = p;
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:172:?, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean match(java.lang.Object r38, java.lang.Object[] r39, int r40, int r41, kawa.lang.SyntaxForm r42) {
        /*
            r37 = this;
            r2 = r37
            r3 = r38
            r4 = r39
            r5 = r40
            r6 = r41
            r7 = r42
            r30 = 0
            r8 = r30
        L_0x0010:
            r30 = r3
            r0 = r30
            boolean r0 = r0 instanceof kawa.lang.SyntaxForm
            r30 = r0
            if (r30 == 0) goto L_0x0029
            r30 = r3
            kawa.lang.SyntaxForm r30 = (kawa.lang.SyntaxForm) r30
            r7 = r30
            r30 = r7
            java.lang.Object r30 = r30.getDatum()
            r3 = r30
            goto L_0x0010
        L_0x0029:
            r30 = r2
            r0 = r30
            java.lang.String r0 = r0.program
            r30 = r0
            r31 = r6
            int r6 = r6 + 1
            char r30 = r30.charAt(r31)
            r10 = r30
            r30 = r10
            r31 = 7
            r30 = r30 & 7
            r11 = r30
            r30 = r8
            r31 = 13
            int r30 = r30 << 13
            r31 = r10
            r32 = 3
            int r31 = r31 >> 3
            r30 = r30 | r31
            r8 = r30
            r30 = r11
            switch(r30) {
                case 0: goto L_0x0086;
                case 1: goto L_0x0085;
                case 2: goto L_0x03f2;
                case 3: goto L_0x0494;
                case 4: goto L_0x0178;
                case 5: goto L_0x01bc;
                case 6: goto L_0x0104;
                case 7: goto L_0x0058;
                case 8: goto L_0x00f2;
                default: goto L_0x0058;
            }
        L_0x0058:
            r30 = r2
            r30.disassemble()
            java.lang.Error r30 = new java.lang.Error
            r36 = r30
            r30 = r36
            r31 = r36
            java.lang.StringBuilder r32 = new java.lang.StringBuilder
            r36 = r32
            r32 = r36
            r33 = r36
            r33.<init>()
            java.lang.String r33 = "unrecognized pattern opcode @pc:"
            java.lang.StringBuilder r32 = r32.append(r33)
            r33 = r6
            java.lang.StringBuilder r32 = r32.append(r33)
            java.lang.String r32 = r32.toString()
            r31.<init>(r32)
            throw r30
        L_0x0085:
            goto L_0x0010
        L_0x0086:
            r30 = r10
            r31 = 8
            r0 = r30
            r1 = r31
            if (r0 != r1) goto L_0x00a2
            r30 = r3
            gnu.lists.LList r31 = gnu.lists.LList.Empty
            r0 = r30
            r1 = r31
            if (r0 != r1) goto L_0x009f
            r30 = 1
        L_0x009c:
            r2 = r30
        L_0x009e:
            return r2
        L_0x009f:
            r30 = 0
            goto L_0x009c
        L_0x00a2:
            r30 = r10
            r31 = 16
            r0 = r30
            r1 = r31
            if (r0 != r1) goto L_0x00d4
            r30 = r3
            r0 = r30
            boolean r0 = r0 instanceof gnu.lists.FVector
            r30 = r0
            if (r30 != 0) goto L_0x00bb
            r30 = 0
            r2 = r30
            goto L_0x009e
        L_0x00bb:
            r30 = r2
            r31 = r3
            gnu.lists.FVector r31 = (gnu.lists.FVector) r31
            gnu.lists.LList r31 = gnu.lists.LList.makeList(r31)
            r32 = r4
            r33 = r5
            r34 = r6
            r35 = r7
            boolean r30 = r30.match(r31, r32, r33, r34, r35)
            r2 = r30
            goto L_0x009e
        L_0x00d4:
            r30 = r10
            r31 = 24
            r0 = r30
            r1 = r31
            if (r0 != r1) goto L_0x00e3
            r30 = 1
            r2 = r30
            goto L_0x009e
        L_0x00e3:
            java.lang.Error r30 = new java.lang.Error
            r36 = r30
            r30 = r36
            r31 = r36
            java.lang.String r32 = "unknwon pattern opcode"
            r31.<init>(r32)
            throw r30
        L_0x00f2:
            r30 = r3
            gnu.lists.LList r31 = gnu.lists.LList.Empty
            r0 = r30
            r1 = r31
            if (r0 != r1) goto L_0x0101
            r30 = 1
        L_0x00fe:
            r2 = r30
            goto L_0x009e
        L_0x0101:
            r30 = 0
            goto L_0x00fe
        L_0x0104:
            r30 = r8
            r31 = 1
            int r30 = r30 >> 1
            r12 = r30
            r30 = r3
            r13 = r30
            r30 = 0
            r14 = r30
        L_0x0114:
            r30 = r13
            r0 = r30
            boolean r0 = r0 instanceof kawa.lang.SyntaxForm
            r30 = r0
            if (r30 == 0) goto L_0x0129
            r30 = r13
            kawa.lang.SyntaxForm r30 = (kawa.lang.SyntaxForm) r30
            java.lang.Object r30 = r30.getDatum()
            r13 = r30
            goto L_0x0114
        L_0x0129:
            r30 = r14
            r31 = r12
            r0 = r30
            r1 = r31
            if (r0 != r1) goto L_0x015b
            r30 = r8
            r31 = 1
            r30 = r30 & 1
            if (r30 != 0) goto L_0x014b
            r30 = r13
            gnu.lists.LList r31 = gnu.lists.LList.Empty
            r0 = r30
            r1 = r31
            if (r0 == r1) goto L_0x0155
        L_0x0145:
            r30 = 0
            r2 = r30
            goto L_0x009e
        L_0x014b:
            r30 = r13
            r0 = r30
            boolean r0 = r0 instanceof gnu.lists.Pair
            r30 = r0
            if (r30 != 0) goto L_0x0145
        L_0x0155:
            r30 = 0
            r8 = r30
            goto L_0x0010
        L_0x015b:
            r30 = r13
            r0 = r30
            boolean r0 = r0 instanceof gnu.lists.Pair
            r30 = r0
            if (r30 == 0) goto L_0x0172
            r30 = r13
            gnu.lists.Pair r30 = (gnu.lists.Pair) r30
            java.lang.Object r30 = r30.getCdr()
            r13 = r30
            int r14 = r14 + 1
            goto L_0x0114
        L_0x0172:
            r30 = 0
            r2 = r30
            goto L_0x009e
        L_0x0178:
            r30 = r3
            r0 = r30
            boolean r0 = r0 instanceof gnu.lists.Pair
            r30 = r0
            if (r30 != 0) goto L_0x0188
            r30 = 0
            r2 = r30
            goto L_0x009e
        L_0x0188:
            r30 = r3
            gnu.lists.Pair r30 = (gnu.lists.Pair) r30
            r9 = r30
            r30 = r2
            r31 = r9
            r32 = r4
            r33 = r5
            r34 = r6
            r35 = r7
            boolean r30 = r30.match_car(r31, r32, r33, r34, r35)
            if (r30 != 0) goto L_0x01a6
            r30 = 0
            r2 = r30
            goto L_0x009e
        L_0x01a6:
            r30 = r6
            r31 = r8
            int r30 = r30 + r31
            r6 = r30
            r30 = 0
            r8 = r30
            r30 = r9
            java.lang.Object r30 = r30.getCdr()
            r3 = r30
            goto L_0x0010
        L_0x01bc:
            r30 = r6
            r14 = r30
            r30 = r6
            r31 = r8
            int r30 = r30 + r31
            r6 = r30
            r30 = r2
            r0 = r30
            java.lang.String r0 = r0.program
            r30 = r0
            r31 = r6
            int r6 = r6 + 1
            char r30 = r30.charAt(r31)
            r36 = r30
            r30 = r36
            r31 = r36
            r10 = r31
            r31 = 3
            int r30 = r30 >> 3
            r15 = r30
        L_0x01e6:
            r30 = r10
            r31 = 7
            r30 = r30 & 7
            r31 = 1
            r0 = r30
            r1 = r31
            if (r0 != r1) goto L_0x021b
            r30 = r15
            r31 = 13
            int r30 = r30 << 13
            r31 = r2
            r0 = r31
            java.lang.String r0 = r0.program
            r31 = r0
            r32 = r6
            int r6 = r6 + 1
            char r31 = r31.charAt(r32)
            r36 = r31
            r31 = r36
            r32 = r36
            r10 = r32
            r32 = 3
            int r31 = r31 >> 3
            r30 = r30 | r31
            r15 = r30
            goto L_0x01e6
        L_0x021b:
            r30 = r15
            r31 = r5
            int r30 = r30 + r31
            r15 = r30
            r30 = r2
            r0 = r30
            java.lang.String r0 = r0.program
            r30 = r0
            r31 = r6
            int r6 = r6 + 1
            char r30 = r30.charAt(r31)
            r31 = 3
            int r30 = r30 >> 3
            r16 = r30
        L_0x0239:
            r30 = r10
            r31 = 7
            r30 = r30 & 7
            r31 = 1
            r0 = r30
            r1 = r31
            if (r0 != r1) goto L_0x026e
            r30 = r16
            r31 = 13
            int r30 = r30 << 13
            r31 = r2
            r0 = r31
            java.lang.String r0 = r0.program
            r31 = r0
            r32 = r6
            int r6 = r6 + 1
            char r31 = r31.charAt(r32)
            r36 = r31
            r31 = r36
            r32 = r36
            r10 = r32
            r32 = 3
            int r31 = r31 >> 3
            r30 = r30 | r31
            r16 = r30
            goto L_0x0239
        L_0x026e:
            r30 = r2
            r0 = r30
            java.lang.String r0 = r0.program
            r30 = r0
            r31 = r6
            int r6 = r6 + 1
            char r30 = r30.charAt(r31)
            r10 = r30
            r30 = 1
            r17 = r30
            r30 = r10
            r31 = 8
            r0 = r30
            r1 = r31
            if (r0 != r1) goto L_0x02ba
            r30 = 0
            r18 = r30
        L_0x0292:
            r30 = r3
            int r30 = kawa.lang.Translator.listLength(r30)
            r19 = r30
            r30 = r19
            if (r30 < 0) goto L_0x030c
            r30 = 1
            r20 = r30
        L_0x02a2:
            r30 = r19
            r31 = r18
            r0 = r30
            r1 = r31
            if (r0 < r1) goto L_0x02b4
            r30 = r17
            if (r30 == 0) goto L_0x0319
            r30 = r20
            if (r30 != 0) goto L_0x0319
        L_0x02b4:
            r30 = 0
            r2 = r30
            goto L_0x009e
        L_0x02ba:
            r30 = r10
            r31 = 3
            int r30 = r30 >> 3
            r8 = r30
        L_0x02c2:
            r30 = r10
            r31 = 7
            r30 = r30 & 7
            r31 = 1
            r0 = r30
            r1 = r31
            if (r0 != r1) goto L_0x02f7
            r30 = r8
            r31 = 13
            int r30 = r30 << 13
            r31 = r2
            r0 = r31
            java.lang.String r0 = r0.program
            r31 = r0
            r32 = r6
            int r6 = r6 + 1
            char r31 = r31.charAt(r32)
            r36 = r31
            r31 = r36
            r32 = r36
            r10 = r32
            r32 = 3
            int r31 = r31 >> 3
            r30 = r30 | r31
            r8 = r30
            goto L_0x02c2
        L_0x02f7:
            r30 = r8
            r31 = 1
            r30 = r30 & 1
            if (r30 == 0) goto L_0x0303
            r30 = 0
            r17 = r30
        L_0x0303:
            r30 = r8
            r31 = 1
            int r30 = r30 >> 1
            r18 = r30
            goto L_0x0292
        L_0x030c:
            r30 = 0
            r20 = r30
            r30 = -1
            r31 = r19
            int r30 = -1 - r31
            r19 = r30
            goto L_0x02a2
        L_0x0319:
            r30 = r19
            r31 = r18
            int r30 = r30 - r31
            r21 = r30
            r30 = r16
            r0 = r30
            java.lang.Object[][] r0 = new java.lang.Object[r0][]
            r30 = r0
            r22 = r30
            r30 = 0
            r23 = r30
        L_0x032f:
            r30 = r23
            r31 = r16
            r0 = r30
            r1 = r31
            if (r0 >= r1) goto L_0x034a
            r30 = r22
            r31 = r23
            r32 = r21
            r0 = r32
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r32 = r0
            r30[r31] = r32
            int r23 = r23 + 1
            goto L_0x032f
        L_0x034a:
            r30 = 0
            r23 = r30
        L_0x034e:
            r30 = r23
            r31 = r21
            r0 = r30
            r1 = r31
            if (r0 >= r1) goto L_0x03bf
        L_0x0358:
            r30 = r3
            r0 = r30
            boolean r0 = r0 instanceof kawa.lang.SyntaxForm
            r30 = r0
            if (r30 == 0) goto L_0x0371
            r30 = r3
            kawa.lang.SyntaxForm r30 = (kawa.lang.SyntaxForm) r30
            r7 = r30
            r30 = r7
            java.lang.Object r30 = r30.getDatum()
            r3 = r30
            goto L_0x0358
        L_0x0371:
            r30 = r3
            gnu.lists.Pair r30 = (gnu.lists.Pair) r30
            r9 = r30
            r30 = r2
            r31 = r9
            r32 = r4
            r33 = r5
            r34 = r14
            r35 = r7
            boolean r30 = r30.match_car(r31, r32, r33, r34, r35)
            if (r30 != 0) goto L_0x038f
            r30 = 0
            r2 = r30
            goto L_0x009e
        L_0x038f:
            r30 = r9
            java.lang.Object r30 = r30.getCdr()
            r3 = r30
            r30 = 0
            r24 = r30
        L_0x039b:
            r30 = r24
            r31 = r16
            r0 = r30
            r1 = r31
            if (r0 >= r1) goto L_0x03bc
            r30 = r22
            r31 = r24
            r30 = r30[r31]
            r31 = r23
            r32 = r4
            r33 = r15
            r34 = r24
            int r33 = r33 + r34
            r32 = r32[r33]
            r30[r31] = r32
            int r24 = r24 + 1
            goto L_0x039b
        L_0x03bc:
            int r23 = r23 + 1
            goto L_0x034e
        L_0x03bf:
            r30 = 0
            r23 = r30
        L_0x03c3:
            r30 = r23
            r31 = r16
            r0 = r30
            r1 = r31
            if (r0 >= r1) goto L_0x03e0
            r30 = r4
            r31 = r15
            r32 = r23
            int r31 = r31 + r32
            r32 = r22
            r33 = r23
            r32 = r32[r33]
            r30[r31] = r32
            int r23 = r23 + 1
            goto L_0x03c3
        L_0x03e0:
            r30 = 0
            r8 = r30
            r30 = r18
            if (r30 != 0) goto L_0x0010
            r30 = r17
            if (r30 == 0) goto L_0x0010
            r30 = 1
            r2 = r30
            goto L_0x009e
        L_0x03f2:
            r30 = r2
            r0 = r30
            java.lang.Object[] r0 = r0.literals
            r30 = r0
            r31 = r8
            r30 = r30[r31]
            r23 = r30
            gnu.expr.Compilation r30 = gnu.expr.Compilation.getCurrent()
            kawa.lang.Translator r30 = (kawa.lang.Translator) r30
            r28 = r30
            r30 = r23
            r0 = r30
            boolean r0 = r0 instanceof kawa.lang.SyntaxForm
            r30 = r0
            if (r30 == 0) goto L_0x0458
            r30 = r23
            kawa.lang.SyntaxForm r30 = (kawa.lang.SyntaxForm) r30
            r29 = r30
            r30 = r29
            java.lang.Object r30 = r30.getDatum()
            r24 = r30
            r30 = r29
            kawa.lang.TemplateScope r30 = r30.getScope()
            r26 = r30
        L_0x0428:
            r30 = r3
            r0 = r30
            boolean r0 = r0 instanceof kawa.lang.SyntaxForm
            r30 = r0
            if (r30 == 0) goto L_0x047c
            r30 = r3
            kawa.lang.SyntaxForm r30 = (kawa.lang.SyntaxForm) r30
            r29 = r30
            r30 = r29
            java.lang.Object r30 = r30.getDatum()
            r25 = r30
            r30 = r29
            kawa.lang.TemplateScope r30 = r30.getScope()
            r27 = r30
        L_0x0448:
            r30 = r24
            r31 = r26
            r32 = r25
            r33 = r27
            boolean r30 = literalIdentifierEq(r30, r31, r32, r33)
            r2 = r30
            goto L_0x009e
        L_0x0458:
            r30 = r23
            r24 = r30
            r30 = r28
            kawa.lang.Syntax r30 = r30.getCurrentSyntax()
            r29 = r30
            r30 = r29
            r0 = r30
            boolean r0 = r0 instanceof kawa.lang.Macro
            r30 = r0
            if (r30 == 0) goto L_0x0479
            r30 = r29
            kawa.lang.Macro r30 = (kawa.lang.Macro) r30
            gnu.expr.ScopeExp r30 = r30.getCapturedScope()
        L_0x0476:
            r26 = r30
            goto L_0x0428
        L_0x0479:
            r30 = 0
            goto L_0x0476
        L_0x047c:
            r30 = r3
            r25 = r30
            r30 = r7
            if (r30 != 0) goto L_0x048d
            r30 = r28
            gnu.expr.ScopeExp r30 = r30.currentScope()
        L_0x048a:
            r27 = r30
            goto L_0x0448
        L_0x048d:
            r30 = r7
            kawa.lang.TemplateScope r30 = r30.getScope()
            goto L_0x048a
        L_0x0494:
            r30 = r7
            if (r30 == 0) goto L_0x04a2
            r30 = r3
            r31 = r7
            java.lang.Object r30 = kawa.lang.SyntaxForms.fromDatum(r30, r31)
            r3 = r30
        L_0x04a2:
            r30 = r4
            r31 = r5
            r32 = r8
            int r31 = r31 + r32
            r32 = r3
            r30[r31] = r32
            r30 = 1
            r2 = r30
            goto L_0x009e
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.lang.SyntaxPattern.match(java.lang.Object, java.lang.Object[], int, int, kawa.lang.SyntaxForm):boolean");
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        ObjectOutput out = objectOutput;
        out.writeObject(this.program);
        out.writeObject(this.literals);
        out.writeInt(this.varCount);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        ObjectInput in = objectInput;
        this.literals = (Object[]) in.readObject();
        this.program = (String) in.readObject();
        this.varCount = in.readInt();
    }

    public static Object[] allocVars(int varCount2, Object[] objArr) {
        Object[] outer = objArr;
        Object[] vars = new Object[varCount2];
        if (outer != null) {
            System.arraycopy(outer, 0, vars, 0, outer.length);
        }
        return vars;
    }

    public static boolean literalIdentifierEq(Object obj, ScopeExp scopeExp, Object obj2, ScopeExp scopeExp2) {
        boolean z;
        Object id1 = obj;
        ScopeExp sc1 = scopeExp;
        Object id2 = obj2;
        ScopeExp sc2 = scopeExp2;
        if (id1 != id2 && (id1 == null || id2 == null || !id1.equals(id2))) {
            return false;
        }
        if (sc1 == sc2) {
            return true;
        }
        Declaration d1 = null;
        Declaration d2 = null;
        while (sc1 != null && !(sc1 instanceof ModuleExp)) {
            d1 = sc1.lookup(id1);
            if (d1 != null) {
                break;
            }
            sc1 = sc1.outer;
        }
        while (sc2 != null && !(sc2 instanceof ModuleExp)) {
            d2 = sc2.lookup(id2);
            if (d2 != null) {
                break;
            }
            sc2 = sc2.outer;
        }
        if (d1 == d2) {
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    public static Object[] getLiteralsList(Object obj, SyntaxForm syntaxForm, Translator translator) {
        Object wrapped;
        StringBuilder sb;
        Object list = obj;
        SyntaxForm syntaxForm2 = syntaxForm;
        Translator tr = translator;
        Object savePos = tr.pushPositionOf(list);
        int count = Translator.listLength(list);
        if (count < 0) {
            tr.error('e', "missing or malformed literals list");
            count = 0;
        }
        Object[] literals2 = new Object[(count + 1)];
        for (int i = 1; i <= count; i++) {
            while (list instanceof SyntaxForm) {
                list = ((SyntaxForm) list).getDatum();
            }
            Pair pair = (Pair) list;
            Object pushPositionOf = tr.pushPositionOf(pair);
            Object literal = pair.getCar();
            if (literal instanceof SyntaxForm) {
                wrapped = literal;
                literal = ((SyntaxForm) literal).getDatum();
            } else {
                wrapped = literal;
            }
            if (!(literal instanceof Symbol)) {
                new StringBuilder();
                tr.error('e', sb.append("non-symbol '").append(literal).append("' in literals list").toString());
            }
            literals2[i] = wrapped;
            list = pair.getCdr();
        }
        tr.popPositionOf(savePos);
        return literals2;
    }

    public void print(Consumer out) {
        out.write("#<syntax-pattern>");
    }
}

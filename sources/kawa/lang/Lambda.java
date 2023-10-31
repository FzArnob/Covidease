package kawa.lang;

import android.support.p000v4.media.session.PlaybackStateCompat;
import gnu.expr.Declaration;
import gnu.expr.ErrorExp;
import gnu.expr.Expression;
import gnu.expr.Keyword;
import gnu.expr.LambdaExp;
import gnu.expr.LangExp;
import gnu.expr.QuoteExp;
import gnu.expr.ScopeExp;
import gnu.kawa.lispexpr.LangObjType;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.Symbol;

public class Lambda extends Syntax {
    public static final Keyword nameKeyword = Keyword.make("name");
    public Expression defaultDefault = QuoteExp.falseExp;
    public Object keyKeyword;
    public Object optionalKeyword;
    public Object restKeyword;

    public Lambda() {
    }

    public void setKeywords(Object optional, Object rest, Object key) {
        this.optionalKeyword = optional;
        this.restKeyword = rest;
        this.keyKeyword = key;
    }

    public Expression rewriteForm(Pair pair, Translator tr) {
        Pair form = pair;
        Expression exp = rewrite(form.getCdr(), tr);
        Translator.setLine(exp, (Object) form);
        return exp;
    }

    public Expression rewrite(Object obj, Translator translator) {
        LambdaExp lambdaExp;
        Expression expression;
        Object obj2 = obj;
        Translator tr = translator;
        if (!(obj2 instanceof Pair)) {
            return tr.syntaxError("missing formals in lambda");
        }
        int old_errors = tr.getMessages().getErrorCount();
        new LambdaExp();
        LambdaExp lexp = lambdaExp;
        Pair pair = (Pair) obj2;
        Translator.setLine((Expression) lexp, (Object) pair);
        rewrite(lexp, pair.getCar(), pair.getCdr(), tr, (TemplateScope) null);
        if (tr.getMessages().getErrorCount() <= old_errors) {
            return lexp;
        }
        new ErrorExp("bad lambda expression");
        return expression;
    }

    public void rewrite(LambdaExp lambdaExp, Object formals, Object obj, Translator translator, TemplateScope templateScopeRest) {
        LambdaExp lexp = lambdaExp;
        Object body = obj;
        Translator tr = translator;
        rewriteFormals(lexp, formals, tr, templateScopeRest);
        if (body instanceof PairWithPosition) {
            lexp.setFile(((PairWithPosition) body).getFileName());
        }
        rewriteBody(lexp, rewriteAttrs(lexp, body, tr), tr);
    }

    public void rewriteFormals(LambdaExp lambdaExp, Object obj, Translator translator, TemplateScope templateScope) {
        Declaration declaration;
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        StringBuilder sb5;
        StringBuilder sb6;
        Declaration declaration2;
        Expression expression;
        Expression expression2;
        StringBuilder sb7;
        StringBuilder sb8;
        StringBuilder sb9;
        StringBuilder sb10;
        StringBuilder sb11;
        StringBuilder sb12;
        StringBuilder sb13;
        StringBuilder sb14;
        LambdaExp lexp = lambdaExp;
        Object formals = obj;
        Translator tr = translator;
        TemplateScope templateScopeRest = templateScope;
        if (lexp.getSymbol() == null) {
            String filename = lexp.getFileName();
            int line = lexp.getLineNumber();
            if (filename != null && line > 0) {
                lexp.setSourceLocation(filename, line);
            }
        }
        Object bindings = formals;
        int opt_args = -1;
        int rest_args = -1;
        int key_args = -1;
        while (true) {
            if (bindings instanceof SyntaxForm) {
                bindings = ((SyntaxForm) bindings).getDatum();
            }
            if (!(bindings instanceof Pair)) {
                if (bindings instanceof Symbol) {
                    if (opt_args >= 0 || key_args >= 0 || rest_args >= 0) {
                        new StringBuilder();
                        Expression syntaxError = tr.syntaxError(sb9.append("dotted rest-arg after ").append(this.optionalKeyword).append(", ").append(this.restKeyword).append(", or ").append(this.keyKeyword).toString());
                        return;
                    }
                    rest_args = 1;
                } else if (bindings != LList.Empty) {
                    Expression syntaxError2 = tr.syntaxError("misformed formals in lambda");
                    return;
                }
                if (rest_args > 1) {
                    new StringBuilder();
                    Expression syntaxError3 = tr.syntaxError(sb8.append("multiple ").append(this.restKeyword).append(" parameters").toString());
                    return;
                }
                if (opt_args < 0) {
                    opt_args = 0;
                }
                if (rest_args < 0) {
                    rest_args = 0;
                }
                if (key_args < 0) {
                    key_args = 0;
                }
                if (rest_args > 0) {
                    lexp.max_args = -1;
                } else {
                    lexp.max_args = lexp.min_args + opt_args + (2 * key_args);
                }
                if (opt_args + key_args > 0) {
                    lexp.defaultArgs = new Expression[(opt_args + key_args)];
                }
                if (key_args > 0) {
                    lexp.keywords = new Keyword[key_args];
                }
                Object bindings2 = formals;
                int opt_args2 = 0;
                int key_args2 = 0;
                Object obj2 = null;
                while (true) {
                    if (bindings2 instanceof SyntaxForm) {
                        SyntaxForm sf = (SyntaxForm) bindings2;
                        bindings2 = sf.getDatum();
                        templateScopeRest = sf.getScope();
                    }
                    TemplateScope templateScope2 = templateScopeRest;
                    if (!(bindings2 instanceof Pair)) {
                        if (bindings2 instanceof SyntaxForm) {
                            SyntaxForm sf2 = (SyntaxForm) bindings2;
                            bindings2 = sf2.getDatum();
                            templateScopeRest = sf2.getScope();
                        }
                        if (bindings2 instanceof Symbol) {
                            new Declaration(bindings2);
                            Declaration decl = declaration;
                            decl.setType(LangObjType.listType);
                            decl.setFlag(PlaybackStateCompat.ACTION_SET_REPEAT_MODE);
                            decl.noteValue((Expression) null);
                            addParam(decl, templateScopeRest, lexp, tr);
                            return;
                        }
                        return;
                    }
                    Pair pair = (Pair) bindings2;
                    Object pair_car = pair.getCar();
                    if (pair_car instanceof SyntaxForm) {
                        SyntaxForm sf3 = (SyntaxForm) pair_car;
                        pair_car = sf3.getDatum();
                        templateScope2 = sf3.getScope();
                    }
                    if (pair_car == this.optionalKeyword || pair_car == this.restKeyword || pair_car == this.keyKeyword) {
                        obj2 = pair_car;
                    } else {
                        Object savePos = tr.pushPositionOf(pair);
                        Object name = null;
                        Object defaultValue = this.defaultDefault;
                        Pair typeSpecPair = null;
                        if (tr.matches(pair_car, "::")) {
                            Expression syntaxError4 = tr.syntaxError("'::' must follow parameter name");
                            return;
                        }
                        Object pair_car2 = tr.namespaceResolve(pair_car);
                        if (pair_car2 instanceof Symbol) {
                            name = pair_car2;
                            if (pair.getCdr() instanceof Pair) {
                                Pair pair2 = (Pair) pair.getCdr();
                                Pair p = pair2;
                                if (tr.matches(pair2.getCar(), "::")) {
                                    if (!(pair.getCdr() instanceof Pair)) {
                                        new StringBuilder();
                                        Expression syntaxError5 = tr.syntaxError(sb7.append("'::' not followed by a type specifier (for parameter '").append(name).append("')").toString());
                                        return;
                                    }
                                    Pair p2 = (Pair) p.getCdr();
                                    typeSpecPair = p2;
                                    pair = p2;
                                }
                            }
                        } else if (pair_car2 instanceof Pair) {
                            Pair p3 = (Pair) pair_car2;
                            Object pair_car3 = p3.getCar();
                            if (pair_car3 instanceof SyntaxForm) {
                                SyntaxForm sf4 = (SyntaxForm) pair_car3;
                                pair_car3 = sf4.getDatum();
                                templateScope2 = sf4.getScope();
                            }
                            Object pair_car4 = tr.namespaceResolve(pair_car3);
                            if ((pair_car4 instanceof Symbol) && (p3.getCdr() instanceof Pair)) {
                                name = pair_car4;
                                Pair p4 = (Pair) p3.getCdr();
                                if (tr.matches(p4.getCar(), "::")) {
                                    if (!(p4.getCdr() instanceof Pair)) {
                                        new StringBuilder();
                                        Expression syntaxError6 = tr.syntaxError(sb4.append("'::' not followed by a type specifier (for parameter '").append(name).append("')").toString());
                                        return;
                                    }
                                    Pair p5 = (Pair) p4.getCdr();
                                    typeSpecPair = p5;
                                    if (p5.getCdr() instanceof Pair) {
                                        p4 = (Pair) p5.getCdr();
                                    } else {
                                        if (p5.getCdr() == LList.Empty) {
                                            p4 = null;
                                        } else {
                                            new StringBuilder();
                                            Expression syntaxError7 = tr.syntaxError(sb5.append("improper list in specifier for parameter '").append(name).append("')").toString());
                                            return;
                                        }
                                    }
                                }
                                if (!(p4 == null || obj2 == null)) {
                                    defaultValue = p4.getCar();
                                    if (p4.getCdr() instanceof Pair) {
                                        p4 = (Pair) p4.getCdr();
                                    } else {
                                        if (p4.getCdr() == LList.Empty) {
                                            p4 = null;
                                        } else {
                                            new StringBuilder();
                                            Expression syntaxError8 = tr.syntaxError(sb3.append("improper list in specifier for parameter '").append(name).append("')").toString());
                                            return;
                                        }
                                    }
                                }
                                if (p4 != null) {
                                    if (typeSpecPair != null) {
                                        new StringBuilder();
                                        Expression syntaxError9 = tr.syntaxError(sb.append("duplicate type specifier for parameter '").append(name).append('\'').toString());
                                        return;
                                    }
                                    typeSpecPair = p4;
                                    if (p4.getCdr() != LList.Empty) {
                                        new StringBuilder();
                                        Expression syntaxError10 = tr.syntaxError(sb2.append("junk at end of specifier for parameter '").append(name).append('\'').append(" after type ").append(p4.getCar()).toString());
                                        return;
                                    }
                                }
                            }
                        }
                        if (name == null) {
                            new StringBuilder();
                            Expression syntaxError11 = tr.syntaxError(sb6.append("parameter is neither name nor (name :: type) nor (name default): ").append(pair).toString());
                            return;
                        }
                        if (obj2 == this.optionalKeyword || obj2 == this.keyKeyword) {
                            int i = opt_args2;
                            opt_args2++;
                            new LangExp(defaultValue);
                            lexp.defaultArgs[i] = expression2;
                        }
                        if (obj2 == this.keyKeyword) {
                            int i2 = key_args2;
                            key_args2++;
                            lexp.keywords[i2] = Keyword.make(name instanceof Symbol ? ((Symbol) name).getName() : name.toString());
                        }
                        new Declaration(name);
                        Declaration decl2 = declaration2;
                        Translator.setLine(decl2, bindings2);
                        if (typeSpecPair != null) {
                            new LangExp(typeSpecPair);
                            decl2.setTypeExp(expression);
                            decl2.setFlag(PlaybackStateCompat.ACTION_PLAY_FROM_URI);
                        } else {
                            if (obj2 == this.restKeyword) {
                                decl2.setType(LangObjType.listType);
                            }
                        }
                        decl2.setFlag(PlaybackStateCompat.ACTION_SET_REPEAT_MODE);
                        decl2.noteValue((Expression) null);
                        addParam(decl2, templateScope2, lexp, tr);
                        tr.popPositionOf(savePos);
                    }
                    bindings2 = pair.getCdr();
                }
            } else {
                Pair pair3 = (Pair) bindings;
                Object pair_car5 = pair3.getCar();
                if (pair_car5 instanceof SyntaxForm) {
                    pair_car5 = ((SyntaxForm) pair_car5).getDatum();
                }
                if (pair_car5 != this.optionalKeyword) {
                    if (pair_car5 != this.restKeyword) {
                        if (pair_car5 == this.keyKeyword) {
                            if (key_args >= 0) {
                                new StringBuilder();
                                Expression syntaxError12 = tr.syntaxError(sb10.append("multiple ").append(this.keyKeyword).append(" in parameter list").toString());
                                return;
                            }
                            key_args = 0;
                        } else if (tr.matches(pair3.getCar(), "::") && (pair3.getCdr() instanceof Pair)) {
                            pair3 = (Pair) pair3.getCdr();
                        } else if (key_args >= 0) {
                            key_args++;
                        } else if (rest_args >= 0) {
                            rest_args++;
                        } else if (opt_args >= 0) {
                            opt_args++;
                        } else {
                            lexp.min_args++;
                        }
                    } else if (rest_args >= 0) {
                        new StringBuilder();
                        Expression syntaxError13 = tr.syntaxError(sb11.append("multiple ").append(this.restKeyword).append(" in parameter list").toString());
                        return;
                    } else if (key_args >= 0) {
                        new StringBuilder();
                        Expression syntaxError14 = tr.syntaxError(sb12.append(this.restKeyword.toString()).append(" after ").append(this.keyKeyword).toString());
                        return;
                    } else {
                        rest_args = 0;
                    }
                } else if (opt_args >= 0) {
                    new StringBuilder();
                    Expression syntaxError15 = tr.syntaxError(sb13.append("multiple ").append(this.optionalKeyword).append(" in parameter list").toString());
                    return;
                } else if (rest_args >= 0 || key_args >= 0) {
                    new StringBuilder();
                    Expression syntaxError16 = tr.syntaxError(sb14.append(this.optionalKeyword.toString()).append(" after ").append(this.restKeyword).append(" or ").append(this.keyKeyword).toString());
                } else {
                    opt_args = 0;
                }
                Object bindings3 = pair3.getCdr();
                bindings = pair3.getCdr();
            }
        }
        new StringBuilder();
        Expression syntaxError162 = tr.syntaxError(sb14.append(this.optionalKeyword.toString()).append(" after ").append(this.restKeyword).append(" or ").append(this.keyKeyword).toString());
    }

    private static void addParam(Declaration declaration, ScopeExp scopeExp, LambdaExp lambdaExp, Translator translator) {
        Declaration decl = declaration;
        ScopeExp templateScope = scopeExp;
        LambdaExp lexp = lambdaExp;
        Translator tr = translator;
        if (templateScope != null) {
            decl = tr.makeRenamedAlias(decl, templateScope);
        }
        lexp.addDeclaration(decl);
        if (templateScope != null) {
            decl.context = templateScope;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: gnu.expr.Keyword} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: gnu.expr.Keyword} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: gnu.expr.Keyword} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: gnu.expr.Keyword} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object rewriteAttrs(gnu.expr.LambdaExp r32, java.lang.Object r33, kawa.lang.Translator r34) {
        /*
            r31 = this;
            r3 = r31
            r4 = r32
            r5 = r33
            r6 = r34
            r23 = 0
            r7 = r23
            r23 = 0
            r8 = r23
            r23 = 0
            r9 = r23
            r23 = 0
            r10 = r23
            r23 = 0
            r11 = r23
        L_0x001c:
            r23 = r5
            r0 = r23
            boolean r0 = r0 instanceof kawa.lang.SyntaxForm
            r23 = r0
            if (r23 == 0) goto L_0x0035
            r23 = r5
            kawa.lang.SyntaxForm r23 = (kawa.lang.SyntaxForm) r23
            r11 = r23
            r23 = r11
            java.lang.Object r23 = r23.getDatum()
            r5 = r23
            goto L_0x001c
        L_0x0035:
            r23 = r5
            r0 = r23
            boolean r0 = r0 instanceof gnu.lists.Pair
            r23 = r0
            if (r23 != 0) goto L_0x0070
        L_0x003f:
            r23 = r9
            r24 = r10
            r23 = r23 | r24
            r9 = r23
            r23 = r9
            if (r23 == 0) goto L_0x005d
            r23 = r4
            r0 = r23
            gnu.expr.Declaration r0 = r0.nameDecl
            r23 = r0
            r24 = r9
            r0 = r24
            long r0 = (long) r0
            r24 = r0
            r23.setFlag(r24)
        L_0x005d:
            r23 = r11
            if (r23 == 0) goto L_0x006b
            r23 = r5
            r24 = r11
            java.lang.Object r23 = kawa.lang.SyntaxForms.fromDatumIfNeeded(r23, r24)
            r5 = r23
        L_0x006b:
            r23 = r5
            r3 = r23
            return r3
        L_0x0070:
            r23 = r5
            gnu.lists.Pair r23 = (gnu.lists.Pair) r23
            r12 = r23
            r23 = r12
            java.lang.Object r23 = r23.getCar()
            java.lang.Object r23 = kawa.lang.Translator.stripSyntax(r23)
            r13 = r23
            r23 = r6
            r24 = r13
            java.lang.String r25 = "::"
            boolean r23 = r23.matches(r24, r25)
            if (r23 == 0) goto L_0x00b8
            r23 = 0
            r13 = r23
        L_0x0093:
            r23 = r11
            r14 = r23
            r23 = r12
            java.lang.Object r23 = r23.getCdr()
            r15 = r23
        L_0x009f:
            r23 = r15
            r0 = r23
            boolean r0 = r0 instanceof kawa.lang.SyntaxForm
            r23 = r0
            if (r23 == 0) goto L_0x00c4
            r23 = r15
            kawa.lang.SyntaxForm r23 = (kawa.lang.SyntaxForm) r23
            r14 = r23
            r23 = r14
            java.lang.Object r23 = r23.getDatum()
            r15 = r23
            goto L_0x009f
        L_0x00b8:
            r23 = r13
            r0 = r23
            boolean r0 = r0 instanceof gnu.expr.Keyword
            r23 = r0
            if (r23 != 0) goto L_0x0093
            goto L_0x003f
        L_0x00c4:
            r23 = r15
            r0 = r23
            boolean r0 = r0 instanceof gnu.lists.Pair
            r23 = r0
            if (r23 != 0) goto L_0x00d0
            goto L_0x003f
        L_0x00d0:
            r23 = r15
            gnu.lists.Pair r23 = (gnu.lists.Pair) r23
            r16 = r23
            r23 = r13
            if (r23 != 0) goto L_0x0139
            r23 = r4
            boolean r23 = r23.isClassMethod()
            if (r23 == 0) goto L_0x0105
            java.lang.String r23 = "*init*"
            r24 = r4
            java.lang.String r24 = r24.getName()
            boolean r23 = r23.equals(r24)
            if (r23 == 0) goto L_0x0105
            r23 = r6
            r24 = 101(0x65, float:1.42E-43)
            java.lang.String r25 = "explicit return type for '*init*' method"
            r23.error(r24, r25)
        L_0x00fb:
            r23 = r16
            java.lang.Object r23 = r23.getCdr()
            r5 = r23
            goto L_0x001c
        L_0x0105:
            r23 = r4
            gnu.expr.LangExp r24 = new gnu.expr.LangExp
            r30 = r24
            r24 = r30
            r25 = r30
            r26 = 2
            r0 = r26
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r26 = r0
            r30 = r26
            r26 = r30
            r27 = r30
            r28 = 0
            r29 = r16
            r27[r28] = r29
            r30 = r26
            r26 = r30
            r27 = r30
            r28 = 1
            r29 = r14
            r27[r28] = r29
            r25.<init>(r26)
            r0 = r24
            r1 = r23
            r1.body = r0
            goto L_0x00fb
        L_0x0139:
            r23 = r13
            gnu.expr.Keyword r24 = kawa.standard.object.accessKeyword
            r0 = r23
            r1 = r24
            if (r0 != r1) goto L_0x022b
            r23 = r6
            r24 = r16
            r25 = r14
            gnu.expr.Expression r23 = r23.rewrite_car((gnu.lists.Pair) r24, (kawa.lang.SyntaxForm) r25)
            r18 = r23
            r23 = r18
            r0 = r23
            boolean r0 = r0 instanceof gnu.expr.QuoteExp
            r23 = r0
            if (r23 == 0) goto L_0x017b
            r23 = r18
            gnu.expr.QuoteExp r23 = (gnu.expr.QuoteExp) r23
            java.lang.Object r23 = r23.getValue()
            r30 = r23
            r23 = r30
            r24 = r30
            r17 = r24
            r0 = r23
            boolean r0 = r0 instanceof gnu.mapping.SimpleSymbol
            r23 = r0
            if (r23 != 0) goto L_0x0187
            r23 = r17
            r0 = r23
            boolean r0 = r0 instanceof java.lang.CharSequence
            r23 = r0
            if (r23 != 0) goto L_0x0187
        L_0x017b:
            r23 = r6
            r24 = 101(0x65, float:1.42E-43)
            java.lang.String r25 = "access: value not a constant symbol or string"
            r23.error(r24, r25)
        L_0x0185:
            goto L_0x00fb
        L_0x0187:
            r23 = r4
            r0 = r23
            gnu.expr.Declaration r0 = r0.nameDecl
            r23 = r0
            if (r23 != 0) goto L_0x019c
            r23 = r6
            r24 = 101(0x65, float:1.42E-43)
            java.lang.String r25 = "access: not allowed for anonymous function"
            r23.error(r24, r25)
            goto L_0x0185
        L_0x019c:
            r23 = r17
            java.lang.String r23 = r23.toString()
            r19 = r23
            java.lang.String r23 = "private"
            r24 = r19
            boolean r23 = r23.equals(r24)
            if (r23 == 0) goto L_0x01f0
            r23 = 16777216(0x1000000, float:2.3509887E-38)
            r9 = r23
        L_0x01b3:
            r23 = r7
            if (r23 == 0) goto L_0x01eb
            r23 = r19
            if (r23 == 0) goto L_0x01eb
            r23 = r6
            r24 = 101(0x65, float:1.42E-43)
            java.lang.StringBuilder r25 = new java.lang.StringBuilder
            r30 = r25
            r25 = r30
            r26 = r30
            r26.<init>()
            java.lang.String r26 = "duplicate access specifiers - "
            java.lang.StringBuilder r25 = r25.append(r26)
            r26 = r7
            java.lang.StringBuilder r25 = r25.append(r26)
            java.lang.String r26 = " and "
            java.lang.StringBuilder r25 = r25.append(r26)
            r26 = r19
            java.lang.StringBuilder r25 = r25.append(r26)
            java.lang.String r25 = r25.toString()
            r23.error(r24, r25)
        L_0x01eb:
            r23 = r19
            r7 = r23
            goto L_0x0185
        L_0x01f0:
            java.lang.String r23 = "protected"
            r24 = r19
            boolean r23 = r23.equals(r24)
            if (r23 == 0) goto L_0x0200
            r23 = 33554432(0x2000000, float:9.403955E-38)
            r9 = r23
            goto L_0x01b3
        L_0x0200:
            java.lang.String r23 = "public"
            r24 = r19
            boolean r23 = r23.equals(r24)
            if (r23 == 0) goto L_0x0210
            r23 = 67108864(0x4000000, float:1.5046328E-36)
            r9 = r23
            goto L_0x01b3
        L_0x0210:
            java.lang.String r23 = "package"
            r24 = r19
            boolean r23 = r23.equals(r24)
            if (r23 == 0) goto L_0x0220
            r23 = 134217728(0x8000000, float:3.85186E-34)
            r9 = r23
            goto L_0x01b3
        L_0x0220:
            r23 = r6
            r24 = 101(0x65, float:1.42E-43)
            java.lang.String r25 = "unknown access specifier"
            r23.error(r24, r25)
            goto L_0x01b3
        L_0x022b:
            r23 = r13
            gnu.expr.Keyword r24 = kawa.standard.object.allocationKeyword
            r0 = r23
            r1 = r24
            if (r0 != r1) goto L_0x0308
            r23 = r6
            r24 = r16
            r25 = r14
            gnu.expr.Expression r23 = r23.rewrite_car((gnu.lists.Pair) r24, (kawa.lang.SyntaxForm) r25)
            r18 = r23
            r23 = r18
            r0 = r23
            boolean r0 = r0 instanceof gnu.expr.QuoteExp
            r23 = r0
            if (r23 == 0) goto L_0x026d
            r23 = r18
            gnu.expr.QuoteExp r23 = (gnu.expr.QuoteExp) r23
            java.lang.Object r23 = r23.getValue()
            r30 = r23
            r23 = r30
            r24 = r30
            r17 = r24
            r0 = r23
            boolean r0 = r0 instanceof gnu.mapping.SimpleSymbol
            r23 = r0
            if (r23 != 0) goto L_0x0279
            r23 = r17
            r0 = r23
            boolean r0 = r0 instanceof java.lang.CharSequence
            r23 = r0
            if (r23 != 0) goto L_0x0279
        L_0x026d:
            r23 = r6
            r24 = 101(0x65, float:1.42E-43)
            java.lang.String r25 = "allocation: value not a constant symbol or string"
            r23.error(r24, r25)
        L_0x0277:
            goto L_0x00fb
        L_0x0279:
            r23 = r4
            r0 = r23
            gnu.expr.Declaration r0 = r0.nameDecl
            r23 = r0
            if (r23 != 0) goto L_0x028e
            r23 = r6
            r24 = 101(0x65, float:1.42E-43)
            java.lang.String r25 = "allocation: not allowed for anonymous function"
            r23.error(r24, r25)
            goto L_0x0277
        L_0x028e:
            r23 = r17
            java.lang.String r23 = r23.toString()
            r19 = r23
            java.lang.String r23 = "class"
            r24 = r19
            boolean r23 = r23.equals(r24)
            if (r23 != 0) goto L_0x02ac
            java.lang.String r23 = "static"
            r24 = r19
            boolean r23 = r23.equals(r24)
            if (r23 == 0) goto L_0x02ed
        L_0x02ac:
            r23 = 2048(0x800, float:2.87E-42)
            r10 = r23
        L_0x02b0:
            r23 = r8
            if (r23 == 0) goto L_0x02e8
            r23 = r19
            if (r23 == 0) goto L_0x02e8
            r23 = r6
            r24 = 101(0x65, float:1.42E-43)
            java.lang.StringBuilder r25 = new java.lang.StringBuilder
            r30 = r25
            r25 = r30
            r26 = r30
            r26.<init>()
            java.lang.String r26 = "duplicate allocation specifiers - "
            java.lang.StringBuilder r25 = r25.append(r26)
            r26 = r8
            java.lang.StringBuilder r25 = r25.append(r26)
            java.lang.String r26 = " and "
            java.lang.StringBuilder r25 = r25.append(r26)
            r26 = r19
            java.lang.StringBuilder r25 = r25.append(r26)
            java.lang.String r25 = r25.toString()
            r23.error(r24, r25)
        L_0x02e8:
            r23 = r19
            r8 = r23
            goto L_0x0277
        L_0x02ed:
            java.lang.String r23 = "instance"
            r24 = r19
            boolean r23 = r23.equals(r24)
            if (r23 == 0) goto L_0x02fd
            r23 = 4096(0x1000, float:5.74E-42)
            r10 = r23
            goto L_0x02b0
        L_0x02fd:
            r23 = r6
            r24 = 101(0x65, float:1.42E-43)
            java.lang.String r25 = "unknown allocation specifier"
            r23.error(r24, r25)
            goto L_0x02b0
        L_0x0308:
            r23 = r13
            gnu.expr.Keyword r24 = kawa.standard.object.throwsKeyword
            r0 = r23
            r1 = r24
            if (r0 != r1) goto L_0x039b
            r23 = r16
            java.lang.Object r23 = r23.getCar()
            r17 = r23
            r23 = r17
            int r23 = kawa.lang.Translator.listLength(r23)
            r18 = r23
            r23 = r18
            if (r23 >= 0) goto L_0x0332
            r23 = r6
            r24 = 101(0x65, float:1.42E-43)
            java.lang.String r25 = "throws: not followed by a list"
            r23.error(r24, r25)
        L_0x0330:
            goto L_0x00fb
        L_0x0332:
            r23 = r18
            r0 = r23
            gnu.expr.Expression[] r0 = new gnu.expr.Expression[r0]
            r23 = r0
            r19 = r23
            r23 = r14
            r20 = r23
            r23 = 0
            r21 = r23
        L_0x0344:
            r23 = r21
            r24 = r18
            r0 = r23
            r1 = r24
            if (r0 >= r1) goto L_0x0393
        L_0x034e:
            r23 = r17
            r0 = r23
            boolean r0 = r0 instanceof kawa.lang.SyntaxForm
            r23 = r0
            if (r23 == 0) goto L_0x0367
            r23 = r17
            kawa.lang.SyntaxForm r23 = (kawa.lang.SyntaxForm) r23
            r20 = r23
            r23 = r20
            java.lang.Object r23 = r23.getDatum()
            r17 = r23
            goto L_0x034e
        L_0x0367:
            r23 = r17
            gnu.lists.Pair r23 = (gnu.lists.Pair) r23
            r22 = r23
            r23 = r19
            r24 = r21
            r25 = r6
            r26 = r22
            r27 = r20
            gnu.expr.Expression r25 = r25.rewrite_car((gnu.lists.Pair) r26, (kawa.lang.SyntaxForm) r27)
            r23[r24] = r25
            r23 = r19
            r24 = r21
            r23 = r23[r24]
            r24 = r22
            kawa.lang.Translator.setLine((gnu.expr.Expression) r23, (java.lang.Object) r24)
            r23 = r22
            java.lang.Object r23 = r23.getCdr()
            r17 = r23
            int r21 = r21 + 1
            goto L_0x0344
        L_0x0393:
            r23 = r4
            r24 = r19
            r23.setExceptions(r24)
            goto L_0x0330
        L_0x039b:
            r23 = r13
            gnu.expr.Keyword r24 = nameKeyword
            r0 = r23
            r1 = r24
            if (r0 != r1) goto L_0x03ce
            r23 = r6
            r24 = r16
            r25 = r14
            gnu.expr.Expression r23 = r23.rewrite_car((gnu.lists.Pair) r24, (kawa.lang.SyntaxForm) r25)
            r18 = r23
            r23 = r18
            r0 = r23
            boolean r0 = r0 instanceof gnu.expr.QuoteExp
            r23 = r0
            if (r23 == 0) goto L_0x03cc
            r23 = r4
            r24 = r18
            gnu.expr.QuoteExp r24 = (gnu.expr.QuoteExp) r24
            java.lang.Object r24 = r24.getValue()
            java.lang.String r24 = r24.toString()
            r23.setName(r24)
        L_0x03cc:
            goto L_0x00fb
        L_0x03ce:
            r23 = r6
            r24 = 119(0x77, float:1.67E-43)
            java.lang.StringBuilder r25 = new java.lang.StringBuilder
            r30 = r25
            r25 = r30
            r26 = r30
            r26.<init>()
            java.lang.String r26 = "unknown procedure property "
            java.lang.StringBuilder r25 = r25.append(r26)
            r26 = r13
            java.lang.StringBuilder r25 = r25.append(r26)
            java.lang.String r25 = r25.toString()
            r23.error(r24, r25)
            goto L_0x00fb
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.lang.Lambda.rewriteAttrs(gnu.expr.LambdaExp, java.lang.Object, kawa.lang.Translator):java.lang.Object");
    }

    public Object skipAttrs(LambdaExp lambdaExp, Object obj, Translator translator) {
        LambdaExp lambdaExp2 = lambdaExp;
        Object body = obj;
        Translator tr = translator;
        while (body instanceof Pair) {
            Pair pair = (Pair) body;
            if (!(pair.getCdr() instanceof Pair)) {
                break;
            }
            Object attrName = pair.getCar();
            if (!tr.matches(attrName, "::")) {
                if (!(attrName instanceof Keyword)) {
                    break;
                }
            }
            body = ((Pair) pair.getCdr()).getCdr();
        }
        return body;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0294, code lost:
        if ((r18 instanceof java.lang.Class) == false) goto L_0x0320;
     */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x02ef  */
    /* JADX WARNING: Removed duplicated region for block: B:66:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void rewriteBody(gnu.expr.LambdaExp r28, java.lang.Object r29, kawa.lang.Translator r30) {
        /*
            r27 = this;
            r3 = r27
            r4 = r28
            r5 = r29
            r6 = r30
            r21 = 0
            r7 = r21
            r21 = r6
            r0 = r21
            gnu.expr.LambdaExp r0 = r0.curMethodLambda
            r21 = r0
            if (r21 != 0) goto L_0x0038
            r21 = r4
            r0 = r21
            gnu.expr.Declaration r0 = r0.nameDecl
            r21 = r0
            if (r21 == 0) goto L_0x0038
            r21 = r6
            gnu.expr.ModuleExp r21 = r21.getModule()
            r22 = 131072(0x20000, float:1.83671E-40)
            boolean r21 = r21.getFlag(r22)
            if (r21 == 0) goto L_0x0038
            r21 = r6
            r22 = r4
            r0 = r22
            r1 = r21
            r1.curMethodLambda = r0
        L_0x0038:
            r21 = r6
            gnu.expr.ScopeExp r21 = r21.currentScope()
            r8 = r21
            r21 = r6
            r22 = r4
            r21.pushScope(r22)
            r21 = 0
            r9 = r21
            r21 = r4
            r0 = r21
            gnu.expr.Keyword[] r0 = r0.keywords
            r21 = r0
            if (r21 != 0) goto L_0x0159
            r21 = 0
        L_0x0057:
            r10 = r21
            r21 = r4
            r0 = r21
            gnu.expr.Expression[] r0 = r0.defaultArgs
            r21 = r0
            if (r21 != 0) goto L_0x0168
            r21 = 0
        L_0x0065:
            r11 = r21
            r21 = 0
            r12 = r21
            r21 = 0
            r13 = r21
            r21 = r4
            gnu.expr.Declaration r21 = r21.firstDecl()
            r14 = r21
        L_0x0077:
            r21 = r14
            if (r21 == 0) goto L_0x017b
            r21 = r14
            boolean r21 = r21.isAlias()
            if (r21 == 0) goto L_0x00af
            r21 = r14
            gnu.expr.ReferenceExp r21 = kawa.lang.Translator.getOriginalRef(r21)
            gnu.expr.Declaration r21 = r21.getBinding()
            r15 = r21
            r21 = r4
            r22 = r9
            r23 = r15
            r21.replaceFollowing(r22, r23)
            r21 = r15
            r22 = r4
            r0 = r22
            r1 = r21
            r1.context = r0
            r21 = r6
            r22 = r14
            r21.pushRenamedAlias(r22)
            int r7 = r7 + 1
            r21 = r15
            r14 = r21
        L_0x00af:
            r21 = r14
            gnu.expr.Expression r21 = r21.getTypeExp()
            r15 = r21
            r21 = r15
            r0 = r21
            boolean r0 = r0 instanceof gnu.expr.LangExp
            r21 = r0
            if (r21 == 0) goto L_0x00da
            r21 = r15
            gnu.expr.LangExp r21 = (gnu.expr.LangExp) r21
            java.lang.Object r21 = r21.getLangValue()
            gnu.lists.Pair r21 = (gnu.lists.Pair) r21
            r16 = r21
            r21 = r14
            r22 = r6
            r23 = r16
            gnu.bytecode.Type r22 = r22.exp2Type(r23)
            r21.setType(r22)
        L_0x00da:
            r21 = r14
            r9 = r21
            r21 = r12
            r22 = r4
            r0 = r22
            int r0 = r0.min_args
            r22 = r0
            r0 = r21
            r1 = r22
            if (r0 < r1) goto L_0x0140
            r21 = r12
            r22 = r4
            r0 = r22
            int r0 = r0.min_args
            r22 = r0
            r23 = r11
            int r22 = r22 + r23
            r0 = r21
            r1 = r22
            if (r0 < r1) goto L_0x0120
            r21 = r4
            r0 = r21
            int r0 = r0.max_args
            r21 = r0
            if (r21 >= 0) goto L_0x0120
            r21 = r12
            r22 = r4
            r0 = r22
            int r0 = r0.min_args
            r22 = r0
            r23 = r11
            int r22 = r22 + r23
            r0 = r21
            r1 = r22
            if (r0 == r1) goto L_0x0140
        L_0x0120:
            r21 = r4
            r0 = r21
            gnu.expr.Expression[] r0 = r0.defaultArgs
            r21 = r0
            r22 = r13
            r23 = r6
            r24 = r4
            r0 = r24
            gnu.expr.Expression[] r0 = r0.defaultArgs
            r24 = r0
            r25 = r13
            r24 = r24[r25]
            gnu.expr.Expression r23 = r23.rewrite(r24)
            r21[r22] = r23
            int r13 = r13 + 1
        L_0x0140:
            int r12 = r12 + 1
            r21 = r6
            r0 = r21
            gnu.expr.NameLookup r0 = r0.lexical
            r21 = r0
            r22 = r14
            r21.push((gnu.expr.Declaration) r22)
            r21 = r14
            gnu.expr.Declaration r21 = r21.nextDecl()
            r14 = r21
            goto L_0x0077
        L_0x0159:
            r21 = r4
            r0 = r21
            gnu.expr.Keyword[] r0 = r0.keywords
            r21 = r0
            r0 = r21
            int r0 = r0.length
            r21 = r0
            goto L_0x0057
        L_0x0168:
            r21 = r4
            r0 = r21
            gnu.expr.Expression[] r0 = r0.defaultArgs
            r21 = r0
            r0 = r21
            int r0 = r0.length
            r21 = r0
            r22 = r10
            int r21 = r21 - r22
            goto L_0x0065
        L_0x017b:
            r21 = r4
            boolean r21 = r21.isClassMethod()
            if (r21 == 0) goto L_0x01a7
            r21 = r4
            r0 = r21
            gnu.expr.Declaration r0 = r0.nameDecl
            r21 = r0
            r22 = 2048(0x800, double:1.0118E-320)
            boolean r21 = r21.getFlag(r22)
            if (r21 != 0) goto L_0x01a7
            r21 = r4
            r22 = 0
            gnu.expr.Declaration r23 = new gnu.expr.Declaration
            r26 = r23
            r23 = r26
            r24 = r26
            java.lang.String r25 = gnu.expr.ThisExp.THIS_NAME
            r24.<init>((java.lang.Object) r25)
            r21.add(r22, r23)
        L_0x01a7:
            r21 = r6
            r0 = r21
            gnu.expr.LambdaExp r0 = r0.curLambda
            r21 = r0
            r14 = r21
            r21 = r6
            r22 = r4
            r0 = r22
            r1 = r21
            r1.curLambda = r0
            r21 = r4
            r0 = r21
            gnu.bytecode.Type r0 = r0.returnType
            r21 = r0
            r15 = r21
            r21 = r4
            r0 = r21
            gnu.expr.Expression r0 = r0.body
            r21 = r0
            r0 = r21
            boolean r0 = r0 instanceof gnu.expr.LangExp
            r21 = r0
            if (r21 == 0) goto L_0x020f
            r21 = r4
            r0 = r21
            gnu.expr.Expression r0 = r0.body
            r21 = r0
            gnu.expr.LangExp r21 = (gnu.expr.LangExp) r21
            java.lang.Object r21 = r21.getLangValue()
            java.lang.Object[] r21 = (java.lang.Object[]) r21
            java.lang.Object[] r21 = (java.lang.Object[]) r21
            r16 = r21
            r21 = r6
            r22 = r16
            r23 = 0
            r22 = r22[r23]
            gnu.lists.Pair r22 = (gnu.lists.Pair) r22
            r23 = r16
            r24 = 1
            r23 = r23[r24]
            kawa.lang.SyntaxForm r23 = (kawa.lang.SyntaxForm) r23
            gnu.expr.Expression r21 = r21.rewrite_car((gnu.lists.Pair) r22, (kawa.lang.SyntaxForm) r23)
            r17 = r21
            r21 = r6
            gnu.expr.Language r21 = r21.getLanguage()
            r22 = r17
            gnu.bytecode.Type r21 = r21.getTypeFor((gnu.expr.Expression) r22)
            r15 = r21
        L_0x020f:
            r21 = r4
            r22 = r6
            r23 = r5
            gnu.expr.Expression r22 = r22.rewrite_body(r23)
            r0 = r22
            r1 = r21
            r1.body = r0
            r21 = r6
            r22 = r14
            r0 = r22
            r1 = r21
            r1.curLambda = r0
            r21 = r4
            r0 = r21
            gnu.expr.Expression r0 = r0.body
            r21 = r0
            r0 = r21
            boolean r0 = r0 instanceof gnu.expr.BeginExp
            r21 = r0
            if (r21 == 0) goto L_0x0320
            r21 = r4
            r0 = r21
            gnu.expr.Expression r0 = r0.body
            r21 = r0
            gnu.expr.BeginExp r21 = (gnu.expr.BeginExp) r21
            gnu.expr.Expression[] r21 = r21.getExpressions()
            r26 = r21
            r21 = r26
            r22 = r26
            r16 = r22
            r0 = r21
            int r0 = r0.length
            r21 = r0
            r26 = r21
            r21 = r26
            r22 = r26
            r17 = r22
            r22 = 1
            r0 = r21
            r1 = r22
            if (r0 <= r1) goto L_0x0320
            r21 = r16
            r22 = 0
            r21 = r21[r22]
            r0 = r21
            boolean r0 = r0 instanceof gnu.expr.ReferenceExp
            r21 = r0
            if (r21 != 0) goto L_0x0296
            r21 = r16
            r22 = 0
            r21 = r21[r22]
            java.lang.Object r21 = r21.valueIfConstant()
            r26 = r21
            r21 = r26
            r22 = r26
            r18 = r22
            r0 = r21
            boolean r0 = r0 instanceof gnu.bytecode.Type
            r21 = r0
            if (r21 != 0) goto L_0x0296
            r21 = r18
            r0 = r21
            boolean r0 = r0 instanceof java.lang.Class
            r21 = r0
            if (r21 == 0) goto L_0x0320
        L_0x0296:
            r21 = r16
            r22 = 0
            r21 = r21[r22]
            r19 = r21
            int r17 = r17 + -1
            r21 = r17
            r22 = 1
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x02fa
            r21 = r4
            r22 = r16
            r23 = 1
            r22 = r22[r23]
            r0 = r22
            r1 = r21
            r1.body = r0
        L_0x02b8:
            r21 = r4
            r22 = r19
            r23 = r6
            gnu.expr.Language r23 = r23.getLanguage()
            r21.setCoercedReturnValue(r22, r23)
        L_0x02c5:
            r21 = r6
            r22 = r4
            r21.pop(r22)
            r21 = r4
            int r21 = r21.countDecls()
            r21 = r6
            r22 = r7
            r21.popRenamedAlias(r22)
            r21 = r4
            int r21 = r21.countDecls()
            r21 = r6
            r0 = r21
            gnu.expr.LambdaExp r0 = r0.curMethodLambda
            r21 = r0
            r22 = r4
            r0 = r21
            r1 = r22
            if (r0 != r1) goto L_0x02f9
            r21 = r6
            r22 = 0
            r0 = r22
            r1 = r21
            r1.curMethodLambda = r0
        L_0x02f9:
            return
        L_0x02fa:
            r21 = r17
            r0 = r21
            gnu.expr.Expression[] r0 = new gnu.expr.Expression[r0]
            r21 = r0
            r20 = r21
            r21 = r16
            r22 = 1
            r23 = r20
            r24 = 0
            r25 = r17
            java.lang.System.arraycopy(r21, r22, r23, r24, r25)
            r21 = r4
            r22 = r20
            gnu.expr.Expression r22 = gnu.expr.BeginExp.canonicalize((gnu.expr.Expression[]) r22)
            r0 = r22
            r1 = r21
            r1.body = r0
            goto L_0x02b8
        L_0x0320:
            r21 = r4
            r22 = r15
            r21.setCoercedReturnType(r22)
            goto L_0x02c5
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.lang.Lambda.rewriteBody(gnu.expr.LambdaExp, java.lang.Object, kawa.lang.Translator):void");
    }

    public void print(Consumer out) {
        out.write("#<builtin lambda>");
    }
}

package kawa.standard;

import android.support.p000v4.media.session.PlaybackStateCompat;
import gnu.bytecode.Type;
import gnu.expr.ClassExp;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Keyword;
import gnu.expr.LambdaExp;
import gnu.expr.ObjectExp;
import gnu.expr.ReferenceExp;
import gnu.expr.ScopeExp;
import gnu.lists.FString;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Namespace;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import java.util.Vector;
import kawa.lang.Lambda;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

public class object extends Syntax {
    public static final Keyword accessKeyword = Keyword.make("access");
    public static final Keyword allocationKeyword = Keyword.make("allocation");
    public static final Keyword classNameKeyword = Keyword.make("class-name");
    static final Symbol coloncolon = Namespace.EmptyNamespace.getSymbol("::");
    static final Keyword initKeyword = Keyword.make("init");
    static final Keyword init_formKeyword = Keyword.make("init-form");
    static final Keyword init_keywordKeyword = Keyword.make("init-keyword");
    static final Keyword init_valueKeyword = Keyword.make("init-value");
    static final Keyword initformKeyword = Keyword.make("initform");
    public static final Keyword interfaceKeyword = Keyword.make("interface");
    public static final object objectSyntax;
    public static final Keyword throwsKeyword = Keyword.make("throws");
    static final Keyword typeKeyword = Keyword.make("type");
    Lambda lambda;

    static {
        object object;
        new object(SchemeCompilation.lambda);
        objectSyntax = object;
        objectSyntax.setName("object");
    }

    public object(Lambda lambda2) {
        this.lambda = lambda2;
    }

    public Expression rewriteForm(Pair pair, Translator translator) {
        ObjectExp objectExp;
        Pair form = pair;
        Translator tr = translator;
        if (!(form.getCdr() instanceof Pair)) {
            return tr.syntaxError("missing superclass specification in object");
        }
        Pair pair2 = (Pair) form.getCdr();
        new ObjectExp();
        ObjectExp oexp = objectExp;
        if (pair2.getCar() instanceof FString) {
            if (!(pair2.getCdr() instanceof Pair)) {
                return tr.syntaxError("missing superclass specification after object class name");
            }
            pair2 = (Pair) pair2.getCdr();
        }
        Object[] saved = scanClassDef(pair2, oexp, tr);
        if (saved != null) {
            rewriteClassDef(saved, tr);
        }
        return oexp;
    }

    public Object[] scanClassDef(Pair pair, ClassExp classExp, Translator translator) {
        Vector vector;
        Object pair_car;
        Object pair_car2;
        Declaration decl;
        Pair args;
        Object args2;
        StringBuilder sb;
        Object obj;
        Object key;
        StringBuilder sb2;
        StringBuilder sb3;
        LambdaExp lambdaExp;
        Pair pair2 = pair;
        ClassExp oexp = classExp;
        Translator tr = translator;
        tr.mustCompileHere();
        Object superlist = pair2.getCar();
        Object components = pair2.getCdr();
        Object classNamePair = null;
        LambdaExp method_list = null;
        LambdaExp last_method = null;
        long classAccessFlag = 0;
        new Vector(20);
        Vector inits = vector;
        Object obj2 = components;
        while (obj2 != LList.Empty) {
            while (obj2 instanceof SyntaxForm) {
                obj2 = ((SyntaxForm) obj2).getDatum();
            }
            if (!(obj2 instanceof Pair)) {
                tr.error('e', "object member not a list");
                return null;
            }
            Pair pair3 = (Pair) obj2;
            Object car = pair3.getCar();
            while (true) {
                pair_car = car;
                if (!(pair_car instanceof SyntaxForm)) {
                    break;
                }
                car = ((SyntaxForm) pair_car).getDatum();
            }
            obj2 = pair3.getCdr();
            Object savedPos1 = tr.pushPositionOf(pair3);
            if (pair_car instanceof Keyword) {
                while (obj2 instanceof SyntaxForm) {
                    obj2 = ((SyntaxForm) obj2).getDatum();
                }
                if (obj2 instanceof Pair) {
                    if (pair_car == interfaceKeyword) {
                        if (((Pair) obj2).getCar() == Boolean.FALSE) {
                            oexp.setFlag(65536);
                        } else {
                            oexp.setFlag(32768);
                        }
                        obj2 = ((Pair) obj2).getCdr();
                        tr.popPositionOf(savedPos1);
                    } else if (pair_car == classNameKeyword) {
                        if (classNamePair != null) {
                            tr.error('e', "duplicate class-name specifiers");
                        }
                        classNamePair = obj2;
                        obj2 = ((Pair) obj2).getCdr();
                        tr.popPositionOf(savedPos1);
                    } else if (pair_car == accessKeyword) {
                        Object savedPos2 = tr.pushPositionOf(obj2);
                        classAccessFlag = addAccessFlags(((Pair) obj2).getCar(), classAccessFlag, Declaration.CLASS_ACCESS_FLAGS, "class", tr);
                        if (oexp.nameDecl == null) {
                            tr.error('e', "access specifier for anonymous class");
                        }
                        tr.popPositionOf(savedPos2);
                        obj2 = ((Pair) obj2).getCdr();
                        tr.popPositionOf(savedPos1);
                    }
                }
            }
            if (!(pair_car instanceof Pair)) {
                tr.error('e', "object member not a list");
                return null;
            }
            Pair pair4 = (Pair) pair_car;
            Object car2 = pair4.getCar();
            while (true) {
                pair_car2 = car2;
                if (!(pair_car2 instanceof SyntaxForm)) {
                    break;
                }
                car2 = ((SyntaxForm) pair_car2).getDatum();
            }
            if ((pair_car2 instanceof String) || (pair_car2 instanceof Symbol) || (pair_car2 instanceof Keyword)) {
                Pair typePair = null;
                Object sname = pair_car2;
                int allocationFlag = 0;
                long accessFlag = 0;
                if (sname instanceof Keyword) {
                    decl = null;
                    args = pair4;
                } else {
                    decl = oexp.addDeclaration(sname);
                    decl.setSimple(false);
                    decl.setFlag(PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED);
                    Translator.setLine(decl, (Object) pair4);
                    args = pair4.getCdr();
                }
                int nKeywords = 0;
                boolean seenInit = false;
                Pair initPair = null;
                while (true) {
                    if (args2 == LList.Empty) {
                        break;
                    }
                    while (args2 instanceof SyntaxForm) {
                        args2 = ((SyntaxForm) args2).getDatum();
                    }
                    Pair pair5 = (Pair) args2;
                    Pair keyPair = pair5;
                    Object car3 = pair5.getCar();
                    while (true) {
                        key = car3;
                        if (!(key instanceof SyntaxForm)) {
                            break;
                        }
                        car3 = ((SyntaxForm) key).getDatum();
                    }
                    Object savedPos22 = tr.pushPositionOf(pair5);
                    args2 = pair5.getCdr();
                    if ((key == coloncolon || (key instanceof Keyword)) && (args2 instanceof Pair)) {
                        nKeywords++;
                        Pair pair6 = (Pair) args2;
                        Object value = pair6.getCar();
                        args2 = pair6.getCdr();
                        if (key == coloncolon || key == typeKeyword) {
                            typePair = pair6;
                        } else if (key == allocationKeyword) {
                            if (allocationFlag != 0) {
                                tr.error('e', "duplicate allocation: specification");
                            }
                            if (matches(value, "class", tr) || matches(value, "static", tr)) {
                                allocationFlag = 2048;
                            } else if (matches(value, "instance", tr)) {
                                allocationFlag = 4096;
                            } else {
                                new StringBuilder();
                                tr.error('e', sb3.append("unknown allocation kind '").append(value).append("'").toString());
                            }
                        } else if (key == initKeyword || key == initformKeyword || key == init_formKeyword || key == init_valueKeyword) {
                            if (seenInit) {
                                tr.error('e', "duplicate initialization");
                            }
                            seenInit = true;
                            if (key != initKeyword) {
                                initPair = pair6;
                            }
                        } else if (key == init_keywordKeyword) {
                            if (!(value instanceof Keyword)) {
                                tr.error('e', "invalid 'init-keyword' - not a keyword");
                            } else {
                                if (((Keyword) value).getName() != sname.toString()) {
                                    tr.error('w', "init-keyword option ignored");
                                }
                            }
                        } else if (key == accessKeyword) {
                            Object savedPos3 = tr.pushPositionOf(pair6);
                            accessFlag = addAccessFlags(value, accessFlag, Declaration.FIELD_ACCESS_FLAGS, "field", tr);
                            tr.popPositionOf(savedPos3);
                        } else {
                            new StringBuilder();
                            tr.error('w', sb2.append("unknown slot keyword '").append(key).append("'").toString());
                        }
                    } else if (args2 != LList.Empty || seenInit) {
                        if (!(args2 instanceof Pair) || nKeywords != 0 || seenInit || typePair != null) {
                            break;
                        }
                        Pair pair7 = (Pair) args2;
                        Pair pair8 = pair7;
                        if (pair7.getCdr() != LList.Empty) {
                            break;
                        }
                        typePair = keyPair;
                        initPair = pair8;
                        args2 = pair8.getCdr();
                        seenInit = true;
                    } else {
                        initPair = keyPair;
                        seenInit = true;
                    }
                    tr.popPositionOf(savedPos22);
                }
                args2 = null;
                if (args2 != LList.Empty) {
                    Translator translator2 = tr;
                    new StringBuilder();
                    translator2.error('e', sb.append("invalid argument list for slot '").append(sname).append('\'').append(" args:").append(args2 == null ? "null" : args2.getClass().getName()).toString());
                    return null;
                }
                if (seenInit) {
                    boolean isStatic = allocationFlag == 2048;
                    Vector vector2 = inits;
                    if (decl != null) {
                        obj = decl;
                    } else {
                        obj = isStatic ? Boolean.TRUE : Boolean.FALSE;
                    }
                    vector2.addElement(obj);
                    inits.addElement(initPair);
                }
                if (decl != null) {
                    if (typePair != null) {
                        decl.setType(tr.exp2Type(typePair));
                    }
                    if (allocationFlag != 0) {
                        decl.setFlag((long) allocationFlag);
                    }
                    if (accessFlag != 0) {
                        decl.setFlag(accessFlag);
                    }
                    decl.setCanRead(true);
                    decl.setCanWrite(true);
                } else if (!seenInit) {
                    tr.error('e', "missing field name");
                    return null;
                }
            } else if (pair_car2 instanceof Pair) {
                Pair mpair = (Pair) pair_car2;
                Object mname = mpair.getCar();
                if ((mname instanceof String) || (mname instanceof Symbol)) {
                    new LambdaExp();
                    LambdaExp lexp = lambdaExp;
                    Translator.setLine(oexp.addMethod(lexp, mname), (Object) mpair);
                    if (last_method == null) {
                        method_list = lexp;
                    } else {
                        last_method.nextSibling = lexp;
                    }
                    last_method = lexp;
                } else {
                    tr.error('e', "missing method name");
                    return null;
                }
            } else {
                tr.error('e', "invalid field/method definition");
            }
            tr.popPositionOf(savedPos1);
        }
        if (classAccessFlag != 0) {
            oexp.nameDecl.setFlag(classAccessFlag);
        }
        Object[] objArr = new Object[6];
        objArr[0] = oexp;
        Object[] objArr2 = objArr;
        objArr2[1] = components;
        Object[] objArr3 = objArr2;
        objArr3[2] = inits;
        Object[] objArr4 = objArr3;
        objArr4[3] = method_list;
        Object[] objArr5 = objArr4;
        objArr5[4] = superlist;
        Object[] result = objArr5;
        result[5] = classNamePair;
        return result;
    }

    public void rewriteClassDef(Object[] objArr, Translator translator) {
        boolean z;
        Declaration decl;
        Object[] saved = objArr;
        Translator tr = translator;
        ClassExp oexp = (ClassExp) saved[0];
        Object components = saved[1];
        Vector inits = (Vector) saved[2];
        LambdaExp method_list = (LambdaExp) saved[3];
        Object superlist = saved[4];
        Object classNamePair = saved[5];
        oexp.firstChild = method_list;
        int num_supers = Translator.listLength(superlist);
        if (num_supers < 0) {
            tr.error('e', "object superclass specification not a list");
            num_supers = 0;
        }
        Expression[] supers = new Expression[num_supers];
        for (int i = 0; i < num_supers; i++) {
            while (superlist instanceof SyntaxForm) {
                superlist = ((SyntaxForm) superlist).getDatum();
            }
            Pair superpair = (Pair) superlist;
            supers[i] = tr.rewrite_car(superpair, false);
            if ((supers[i] instanceof ReferenceExp) && (decl = Declaration.followAliases(((ReferenceExp) supers[i]).getBinding())) != null) {
                Expression value = decl.getValue();
                Expression svalue = value;
                if (value instanceof ClassExp) {
                    ((ClassExp) svalue).setFlag(131072);
                }
            }
            superlist = superpair.getCdr();
        }
        if (classNamePair != null) {
            Object classNameVal = tr.rewrite_car((Pair) classNamePair, false).valueIfConstant();
            if (classNameVal instanceof CharSequence) {
                String obj = classNameVal.toString();
                String classNameSpecifier = obj;
                if (obj.length() > 0) {
                    oexp.classNameSpecifier = classNameSpecifier;
                }
            }
            Object savedPos = tr.pushPositionOf(classNamePair);
            tr.error('e', "class-name specifier must be a non-empty string literal");
            tr.popPositionOf(savedPos);
        }
        oexp.supers = supers;
        oexp.setTypes(tr);
        int len = inits.size();
        for (int i2 = 0; i2 < len; i2 += 2) {
            Object init = inits.elementAt(i2 + 1);
            if (init != null) {
                rewriteInit(inits.elementAt(i2), oexp, (Pair) init, tr, (SyntaxForm) null);
            }
        }
        tr.push((ScopeExp) oexp);
        LambdaExp meth = method_list;
        int init_index = 0;
        SyntaxForm componentsSyntax = null;
        Object obj2 = components;
        while (obj2 != LList.Empty) {
            while (obj2 instanceof SyntaxForm) {
                componentsSyntax = (SyntaxForm) obj2;
                obj2 = componentsSyntax.getDatum();
            }
            Pair pair = (Pair) obj2;
            Object savedPos1 = tr.pushPositionOf(pair);
            Object pair_car = pair.getCar();
            SyntaxForm memberSyntax = componentsSyntax;
            while (pair_car instanceof SyntaxForm) {
                memberSyntax = (SyntaxForm) pair_car;
                pair_car = memberSyntax.getDatum();
            }
            try {
                obj2 = pair.getCdr();
                if (!(pair_car instanceof Keyword) || !(obj2 instanceof Pair)) {
                    Pair pair2 = (Pair) pair_car;
                    Object pair_car2 = pair2.getCar();
                    SyntaxForm memberCarSyntax = memberSyntax;
                    while (pair_car2 instanceof SyntaxForm) {
                        memberCarSyntax = (SyntaxForm) pair_car2;
                        pair_car2 = memberCarSyntax.getDatum();
                    }
                    if ((pair_car2 instanceof String) || (pair_car2 instanceof Symbol) || (pair_car2 instanceof Keyword)) {
                        Object type = null;
                        int nKeywords = 0;
                        Object args = pair_car2 instanceof Keyword ? pair2 : pair2.getCdr();
                        Pair initPair = null;
                        SyntaxForm initSyntax = null;
                        while (true) {
                            if (args == LList.Empty) {
                                break;
                            }
                            while (args instanceof SyntaxForm) {
                                memberSyntax = (SyntaxForm) args;
                                args = memberSyntax.getDatum();
                            }
                            Pair pair3 = (Pair) args;
                            Object key = pair3.getCar();
                            while (key instanceof SyntaxForm) {
                                key = ((SyntaxForm) key).getDatum();
                            }
                            Object savedPos2 = tr.pushPositionOf(pair3);
                            args = pair3.getCdr();
                            if ((key == coloncolon || (key instanceof Keyword)) && (args instanceof Pair)) {
                                nKeywords++;
                                Pair pair4 = (Pair) args;
                                Object value2 = pair4.getCar();
                                args = pair4.getCdr();
                                if (key == coloncolon || key == typeKeyword) {
                                    type = value2;
                                } else if (key == initKeyword || key == initformKeyword || key == init_formKeyword || key == init_valueKeyword) {
                                    initPair = pair4;
                                    initSyntax = memberSyntax;
                                }
                            } else if (args != LList.Empty || initPair != null) {
                                if (!(args instanceof Pair) || nKeywords != 0 || initPair != null || type != null) {
                                    break;
                                }
                                Pair pair5 = (Pair) args;
                                Pair pair6 = pair5;
                                if (pair5.getCdr() != LList.Empty) {
                                    break;
                                }
                                type = key;
                                initPair = pair6;
                                initSyntax = memberSyntax;
                                args = pair6.getCdr();
                            } else {
                                initPair = pair3;
                                initSyntax = memberSyntax;
                            }
                            tr.popPositionOf(savedPos2);
                        }
                        if (initPair != null) {
                            int i3 = init_index;
                            int init_index2 = init_index + 1;
                            Object d = inits.elementAt(i3);
                            if (d instanceof Declaration) {
                                z = ((Declaration) d).getFlag(PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH);
                            } else {
                                z = d == Boolean.TRUE;
                            }
                            boolean z2 = z;
                            int i4 = init_index2;
                            init_index = init_index2 + 1;
                            if (inits.elementAt(i4) == null) {
                                rewriteInit(d, oexp, initPair, tr, initSyntax);
                            }
                        }
                    } else if (pair_car2 instanceof Pair) {
                        ScopeExp save_scope = tr.currentScope();
                        if (memberSyntax != null) {
                            tr.setCurrentScope(memberSyntax.getScope());
                        }
                        if ("*init*".equals(meth.getName())) {
                            meth.setReturnType(Type.voidType);
                        }
                        Translator.setLine((Expression) meth, (Object) pair2);
                        LambdaExp saveLambda = tr.curMethodLambda;
                        tr.curMethodLambda = meth;
                        this.lambda.rewrite(meth, ((Pair) pair_car2).getCdr(), pair2.getCdr(), tr, (memberCarSyntax == null || (memberSyntax != null && memberCarSyntax.getScope() == memberSyntax.getScope())) ? null : memberCarSyntax.getScope());
                        tr.curMethodLambda = saveLambda;
                        if (memberSyntax != null) {
                            tr.setCurrentScope(save_scope);
                        }
                        meth = meth.nextSibling;
                    } else {
                        Expression syntaxError = tr.syntaxError("invalid field/method definition");
                    }
                    tr.popPositionOf(savedPos1);
                } else {
                    obj2 = ((Pair) obj2).getCdr();
                    tr.popPositionOf(savedPos1);
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                tr.popPositionOf(savedPos1);
                throw th2;
            }
        }
        if (oexp.initMethod != null) {
            oexp.initMethod.outer = oexp;
        }
        if (oexp.clinitMethod != null) {
            oexp.clinitMethod.outer = oexp;
        }
        tr.pop(oexp);
        oexp.declareParts(tr);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v1, resolved type: gnu.expr.ApplyExp} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v0, resolved type: gnu.expr.ApplyExp} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v30, resolved type: gnu.expr.SetExp} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void rewriteInit(java.lang.Object r17, gnu.expr.ClassExp r18, gnu.lists.Pair r19, kawa.lang.Translator r20, kawa.lang.SyntaxForm r21) {
        /*
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r20
            r4 = r21
            r11 = r0
            boolean r11 = r11 instanceof gnu.expr.Declaration
            if (r11 == 0) goto L_0x00a8
            r11 = r0
            gnu.expr.Declaration r11 = (gnu.expr.Declaration) r11
            r12 = 2048(0x800, double:1.0118E-320)
            boolean r11 = r11.getFlag(r12)
        L_0x0018:
            r5 = r11
            r11 = r5
            if (r11 == 0) goto L_0x00b3
            r11 = r1
            gnu.expr.LambdaExp r11 = r11.clinitMethod
        L_0x001f:
            r6 = r11
            r11 = r6
            if (r11 != 0) goto L_0x005d
            gnu.expr.LambdaExp r11 = new gnu.expr.LambdaExp
            r16 = r11
            r11 = r16
            r12 = r16
            gnu.expr.BeginExp r13 = new gnu.expr.BeginExp
            r16 = r13
            r13 = r16
            r14 = r16
            r14.<init>()
            r12.<init>((gnu.expr.Expression) r13)
            r6 = r11
            r11 = r6
            r12 = 1
            r11.setClassMethod(r12)
            r11 = r6
            gnu.bytecode.PrimType r12 = gnu.bytecode.Type.voidType
            r11.setReturnType(r12)
            r11 = r5
            if (r11 == 0) goto L_0x00b8
            r11 = r6
            java.lang.String r12 = "$clinit$"
            r11.setName(r12)
            r11 = r1
            r12 = r6
            r11.clinitMethod = r12
        L_0x0053:
            r11 = r6
            r12 = r1
            gnu.expr.LambdaExp r12 = r12.firstChild
            r11.nextSibling = r12
            r11 = r1
            r12 = r6
            r11.firstChild = r12
        L_0x005d:
            r11 = r3
            r12 = r6
            r11.push((gnu.expr.ScopeExp) r12)
            r11 = r3
            gnu.expr.LambdaExp r11 = r11.curMethodLambda
            r7 = r11
            r11 = r3
            r12 = r6
            r11.curMethodLambda = r12
            r11 = r3
            r12 = r2
            r13 = r4
            gnu.expr.Expression r11 = r11.rewrite_car((gnu.lists.Pair) r12, (kawa.lang.SyntaxForm) r13)
            r8 = r11
            r11 = r0
            boolean r11 = r11 instanceof gnu.expr.Declaration
            if (r11 == 0) goto L_0x00d7
            r11 = r0
            gnu.expr.Declaration r11 = (gnu.expr.Declaration) r11
            r9 = r11
            gnu.expr.SetExp r11 = new gnu.expr.SetExp
            r16 = r11
            r11 = r16
            r12 = r16
            r13 = r9
            r14 = r8
            r12.<init>((gnu.expr.Declaration) r13, (gnu.expr.Expression) r14)
            r10 = r11
            r11 = r10
            r12 = r9
            r11.setLocation(r12)
            r11 = r9
            r12 = 0
            r11.noteValue(r12)
            r11 = r10
            r8 = r11
        L_0x0095:
            r11 = r6
            gnu.expr.Expression r11 = r11.body
            gnu.expr.BeginExp r11 = (gnu.expr.BeginExp) r11
            r12 = r8
            r11.add(r12)
            r11 = r3
            r12 = r7
            r11.curMethodLambda = r12
            r11 = r3
            r12 = r6
            r11.pop(r12)
            return
        L_0x00a8:
            r11 = r0
            java.lang.Boolean r12 = java.lang.Boolean.TRUE
            if (r11 != r12) goto L_0x00b0
            r11 = 1
            goto L_0x0018
        L_0x00b0:
            r11 = 0
            goto L_0x0018
        L_0x00b3:
            r11 = r1
            gnu.expr.LambdaExp r11 = r11.initMethod
            goto L_0x001f
        L_0x00b8:
            r11 = r6
            java.lang.String r12 = "$finit$"
            r11.setName(r12)
            r11 = r1
            r12 = r6
            r11.initMethod = r12
            r11 = r6
            r12 = 0
            gnu.expr.Declaration r13 = new gnu.expr.Declaration
            r16 = r13
            r13 = r16
            r14 = r16
            java.lang.String r15 = gnu.expr.ThisExp.THIS_NAME
            r14.<init>((java.lang.Object) r15)
            r11.add(r12, r13)
            goto L_0x0053
        L_0x00d7:
            r11 = r8
            gnu.expr.QuoteExp r12 = new gnu.expr.QuoteExp
            r16 = r12
            r12 = r16
            r13 = r16
            gnu.bytecode.PrimType r14 = gnu.bytecode.Type.voidType
            r13.<init>(r14)
            gnu.expr.ApplyExp r11 = gnu.expr.Compilation.makeCoercion((gnu.expr.Expression) r11, (gnu.expr.Expression) r12)
            r8 = r11
            goto L_0x0095
        */
        throw new UnsupportedOperationException("Method not decompiled: kawa.standard.object.rewriteInit(java.lang.Object, gnu.expr.ClassExp, gnu.lists.Pair, kawa.lang.Translator, kawa.lang.SyntaxForm):void");
    }

    static boolean matches(Object obj, String str, Translator translator) {
        String value;
        Object exp = obj;
        String tag = str;
        Translator tr = translator;
        if (exp instanceof Keyword) {
            value = ((Keyword) exp).getName();
        } else if (exp instanceof FString) {
            value = ((FString) exp).toString();
        } else {
            if (exp instanceof Pair) {
                Object matchQuoted = tr.matchQuoted((Pair) exp);
                Object qvalue = matchQuoted;
                if (matchQuoted instanceof SimpleSymbol) {
                    value = qvalue.toString();
                }
            }
            return false;
        }
        return tag == null || tag.equals(value);
    }

    static long addAccessFlags(Object obj, long j, long j2, String str, Translator translator) {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        Object value = obj;
        long previous = j;
        long allowed = j2;
        String kind = str;
        Translator tr = translator;
        long flags = matchAccess(value, tr);
        if (flags == 0) {
            new StringBuilder();
            tr.error('e', sb3.append("unknown access specifier ").append(value).toString());
        } else if ((flags & (allowed ^ -1)) != 0) {
            new StringBuilder();
            tr.error('e', sb2.append("invalid ").append(kind).append(" access specifier ").append(value).toString());
        } else if ((previous & flags) != 0) {
            new StringBuilder();
            tr.error('w', sb.append("duplicate ").append(kind).append(" access specifiers ").append(value).toString());
        }
        return previous | flags;
    }

    static long matchAccess(Object obj, Translator translator) {
        Object value = obj;
        Translator tr = translator;
        while (value instanceof SyntaxForm) {
            value = ((SyntaxForm) value).getDatum();
        }
        if (value instanceof Pair) {
            Pair pair = (Pair) value;
            value = tr.matchQuoted((Pair) value);
            if (value instanceof Pair) {
                return matchAccess2((Pair) value, tr);
            }
        }
        return matchAccess1(value, tr);
    }

    private static long matchAccess2(Pair pair, Translator translator) {
        Pair pair2 = pair;
        Translator tr = translator;
        long icar = matchAccess1(pair2.getCar(), tr);
        Object cdr = pair2.getCdr();
        if (cdr == LList.Empty || icar == 0) {
            return icar;
        }
        if (cdr instanceof Pair) {
            long icdr = matchAccess2((Pair) cdr, tr);
            if (icdr != 0) {
                return icar | icdr;
            }
        }
        return 0;
    }

    private static long matchAccess1(Object obj, Translator translator) {
        Object value = obj;
        Translator translator2 = translator;
        if (value instanceof Keyword) {
            value = ((Keyword) value).getName();
        } else if (value instanceof FString) {
            value = ((FString) value).toString();
        } else if (value instanceof SimpleSymbol) {
            value = value.toString();
        }
        if ("private".equals(value)) {
            return 16777216;
        }
        if ("protected".equals(value)) {
            return 33554432;
        }
        if ("public".equals(value)) {
            return 67108864;
        }
        if ("package".equals(value)) {
            return 134217728;
        }
        if ("volatile".equals(value)) {
            return Declaration.VOLATILE_ACCESS;
        }
        if ("transient".equals(value)) {
            return Declaration.TRANSIENT_ACCESS;
        }
        if ("enum".equals(value)) {
            return Declaration.ENUM_ACCESS;
        }
        if ("final".equals(value)) {
            return Declaration.FINAL_ACCESS;
        }
        return 0;
    }
}

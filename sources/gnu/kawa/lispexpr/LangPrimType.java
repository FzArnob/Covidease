package gnu.kawa.lispexpr;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Language;
import gnu.expr.Target;
import gnu.expr.TypeValue;
import gnu.kawa.reflect.InstanceOf;
import gnu.mapping.Procedure;
import gnu.mapping.Values;
import gnu.text.Char;

public class LangPrimType extends PrimType implements TypeValue {
    public static final PrimType byteType = Type.byteType;
    public static final LangPrimType charType;
    public static final PrimType doubleType = Type.doubleType;
    public static final PrimType floatType = Type.floatType;
    public static final PrimType intType = Type.intType;
    public static final PrimType longType = Type.longType;
    public static final PrimType shortType = Type.shortType;
    public static final LangPrimType voidType;
    PrimType implementationType;
    Language language;

    static {
        LangPrimType langPrimType;
        LangPrimType langPrimType2;
        new LangPrimType(Type.charType);
        charType = langPrimType;
        new LangPrimType(Type.voidType);
        voidType = langPrimType2;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public LangPrimType(gnu.bytecode.PrimType r5) {
        /*
            r4 = this;
            r0 = r4
            r1 = r5
            r2 = r0
            r3 = r1
            r2.<init>(r3)
            r2 = r0
            r3 = r1
            r2.implementationType = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.lispexpr.LangPrimType.<init>(gnu.bytecode.PrimType):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public LangPrimType(gnu.bytecode.PrimType r6, gnu.expr.Language r7) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r2 = r7
            r3 = r0
            r4 = r1
            r3.<init>(r4)
            r3 = r0
            r4 = r2
            r3.language = r4
            r3 = r0
            r4 = r1
            r3.implementationType = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.lispexpr.LangPrimType.<init>(gnu.bytecode.PrimType, gnu.expr.Language):void");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LangPrimType(String nam, String sig, int siz, Class reflectClass) {
        super(nam, sig, siz, reflectClass);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public LangPrimType(java.lang.String r12, java.lang.String r13, int r14, java.lang.Class r15, gnu.expr.Language r16) {
        /*
            r11 = this;
            r0 = r11
            r1 = r12
            r2 = r13
            r3 = r14
            r4 = r15
            r5 = r16
            r6 = r0
            r7 = r1
            r8 = r2
            r9 = r3
            r10 = r4
            r6.<init>(r7, r8, r9, r10)
            r6 = r0
            r7 = r2
            r8 = 0
            char r7 = r7.charAt(r8)
            gnu.bytecode.PrimType r7 = gnu.bytecode.Type.signatureToPrimitive(r7)
            r6.implementationType = r7
            r6 = r0
            r7 = r5
            r6.language = r7
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.lispexpr.LangPrimType.<init>(java.lang.String, java.lang.String, int, java.lang.Class, gnu.expr.Language):void");
    }

    public Type getImplementationType() {
        return this.implementationType;
    }

    public Object coerceFromObject(Object obj) {
        Object obj2;
        Object obj3 = obj;
        if (obj3.getClass() == this.reflectClass) {
            return obj3;
        }
        switch (getSignature().charAt(0)) {
            case 'C':
                new Character(((Char) obj3).charValue());
                return obj2;
            case 'V':
                return Values.empty;
            case 'Z':
                return this.language.isTrue(obj3) ? Boolean.TRUE : Boolean.FALSE;
            default:
                return super.coerceFromObject(obj3);
        }
    }

    public char charValue(Object obj) {
        Object value = obj;
        if (value instanceof Character) {
            return ((Character) value).charValue();
        }
        return ((Char) value).charValue();
    }

    public void emitIsInstance(CodeAttr codeAttr) {
        CodeAttr code = codeAttr;
        switch (getSignature().charAt(0)) {
            case 'C':
                code.emitInstanceof(ClassType.make("gnu.text.Char"));
                return;
            case 'Z':
                code.emitPop(1);
                code.emitPushInt(1);
                return;
            default:
                super.emitIsInstance(code);
                return;
        }
    }

    public void emitCoerceFromObject(CodeAttr codeAttr) {
        CodeAttr code = codeAttr;
        switch (getSignature().charAt(0)) {
            case 'C':
                ClassType scmCharType = ClassType.make("gnu.text.Char");
                Method charValueMethod = scmCharType.getDeclaredMethod("charValue", 0);
                code.emitCheckcast(scmCharType);
                code.emitInvokeVirtual(charValueMethod);
                return;
            case 'Z':
                this.language.emitCoerceToBoolean(code);
                return;
            default:
                super.emitCoerceFromObject(code);
                return;
        }
    }

    public Object coerceToObject(Object obj) {
        Object obj2 = obj;
        switch (getSignature().charAt(0)) {
            case 'C':
                if (obj2 instanceof Char) {
                    return obj2;
                }
                return Char.make(((Character) obj2).charValue());
            case 'V':
                return Values.empty;
            case 'Z':
                return this.language.booleanObject(((Boolean) obj2).booleanValue());
            default:
                return super.coerceToObject(obj2);
        }
    }

    public void emitCoerceToObject(CodeAttr codeAttr) {
        CodeAttr code = codeAttr;
        switch (getSignature().charAt(0)) {
            case 'C':
                code.emitInvokeStatic(ClassType.make("gnu.text.Char").getDeclaredMethod("make", 1));
                break;
            case 'Z':
                code.emitIfIntNotZero();
                this.language.emitPushBoolean(true, code);
                code.emitElse();
                this.language.emitPushBoolean(false, code);
                code.emitFi();
                break;
            default:
                super.emitCoerceToObject(code);
                break;
        }
        if (0 != 0) {
            code.emitInvokeStatic(ClassType.make((String) null).getDeclaredMethod("make", new Type[]{null}));
        }
    }

    public int compare(Type type) {
        Type other = type;
        char sig1 = getSignature().charAt(0);
        if (other instanceof PrimType) {
            char sig2 = other.getSignature().charAt(0);
            if (sig1 == sig2) {
                return 0;
            }
            if (sig1 == 'V') {
                return 1;
            }
            if (sig2 == 'V' || sig2 == 'Z') {
                return -1;
            }
        }
        if (sig1 == 'V' || sig1 == 'Z') {
            return 1;
        }
        if (sig1 == 'C' && other.getName().equals("gnu.text.Char")) {
            return -1;
        }
        if (other instanceof LangObjType) {
            return swappedCompareResult(other.compare(this));
        }
        return super.compare(other);
    }

    public void emitTestIf(Variable variable, Declaration declaration, Compilation compilation) {
        Variable incoming = variable;
        Declaration decl = declaration;
        Compilation comp = compilation;
        char charAt = getSignature().charAt(0);
        CodeAttr code = comp.getCode();
        if (incoming != null) {
            code.emitLoad(incoming);
        }
        if (decl != null) {
            code.emitDup();
            decl.compileStore(comp);
        }
        emitIsInstance(code);
        code.emitIfIntNotZero();
    }

    public Expression convertValue(Expression expression) {
        Expression expression2 = expression;
        return null;
    }

    public void emitIsInstance(Variable incoming, Compilation comp, Target target) {
        InstanceOf.emitIsInstance(this, incoming, comp, target);
    }

    public Procedure getConstructor() {
        return null;
    }
}

package gnu.bytecode;

public class PrimType extends Type {
    private static final String numberHierarchy = "A:java.lang.Byte;B:java.lang.Short;C:java.lang.Integer;D:java.lang.Long;E:gnu.math.IntNum;E:java.gnu.math.BitInteger;G:gnu.math.RatNum;H:java.lang.Float;I:java.lang.Double;I:gnu.math.DFloNum;J:gnu.math.RealNum;K:gnu.math.Complex;L:gnu.math.Quantity;K:gnu.math.Numeric;N:java.lang.Number;";

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PrimType(String nam, String sig, int siz, Class cls) {
        super(nam, sig);
        Class reflectClass = cls;
        this.size = siz;
        this.reflectClass = reflectClass;
        Type.registerTypeForClass(reflectClass, this);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected PrimType(gnu.bytecode.PrimType r6) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r2 = r0
            r3 = r1
            java.lang.String r3 = r3.this_name
            r4 = r1
            java.lang.String r4 = r4.signature
            r2.<init>(r3, r4)
            r2 = r0
            r3 = r1
            int r3 = r3.size
            r2.size = r3
            r2 = r0
            r3 = r1
            java.lang.Class r3 = r3.reflectClass
            r2.reflectClass = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.bytecode.PrimType.<init>(gnu.bytecode.PrimType):void");
    }

    public Object coerceFromObject(Object obj) {
        Throwable th;
        StringBuilder sb;
        Object obj2 = obj;
        if (obj2.getClass() == this.reflectClass) {
            return obj2;
        }
        switch ((this.signature == null || this.signature.length() != 1) ? ' ' : this.signature.charAt(0)) {
            case 'B':
                return Byte.valueOf(((Number) obj2).byteValue());
            case 'D':
                return Double.valueOf(((Number) obj2).doubleValue());
            case 'F':
                return Float.valueOf(((Number) obj2).floatValue());
            case 'I':
                return Integer.valueOf(((Number) obj2).intValue());
            case 'J':
                return Long.valueOf(((Number) obj2).longValue());
            case 'S':
                return Short.valueOf(((Number) obj2).shortValue());
            case 'Z':
                return Boolean.valueOf(((Boolean) obj2).booleanValue());
            default:
                Throwable th2 = th;
                new StringBuilder();
                new ClassCastException(sb.append("don't know how to coerce ").append(obj2.getClass().getName()).append(" to ").append(getName()).toString());
                throw th2;
        }
    }

    public char charValue(Object value) {
        return ((Character) value).charValue();
    }

    public static boolean booleanValue(Object obj) {
        Object value = obj;
        return !(value instanceof Boolean) || ((Boolean) value).booleanValue();
    }

    public ClassType boxedType() {
        String cname;
        switch (getSignature().charAt(0)) {
            case 'B':
                cname = "java.lang.Byte";
                break;
            case 'C':
                cname = "java.lang.Character";
                break;
            case 'D':
                cname = "java.lang.Double";
                break;
            case 'F':
                cname = "java.lang.Float";
                break;
            case 'I':
                cname = "java.lang.Integer";
                break;
            case 'J':
                cname = "java.lang.Long";
                break;
            case 'S':
                cname = "java.lang.Short";
                break;
            case 'Z':
                cname = "java.lang.Boolean";
                break;
            default:
                cname = null;
                break;
        }
        return ClassType.make(cname);
    }

    public void emitCoerceToObject(CodeAttr codeAttr) {
        Method method;
        CodeAttr code = codeAttr;
        char sig1 = getSignature().charAt(0);
        ClassType clas = boxedType();
        if (sig1 == 'Z') {
            code.emitIfIntNotZero();
            code.emitGetStatic(clas.getDeclaredField("TRUE"));
            code.emitElse();
            code.emitGetStatic(clas.getDeclaredField("FALSE"));
            code.emitFi();
            return;
        }
        Type[] args = {this};
        if (code.getMethod().getDeclaringClass().classfileFormatVersion >= 3211264) {
            method = clas.getDeclaredMethod("valueOf", args);
        } else {
            method = clas.getDeclaredMethod("<init>", args);
            code.emitNew(clas);
            code.emitDupX();
            code.emitSwap();
        }
        code.emitInvoke(method);
    }

    public void emitIsInstance(CodeAttr codeAttr) {
        CodeAttr code = codeAttr;
        char sig1 = (this.signature == null || this.signature.length() != 1) ? ' ' : this.signature.charAt(0);
        if (sig1 == 'Z') {
            javalangBooleanType.emitIsInstance(code);
        } else if (sig1 == 'V') {
            code.emitPop(1);
            code.emitPushInt(1);
        } else {
            javalangNumberType.emitIsInstance(code);
        }
    }

    public void emitCoerceFromObject(CodeAttr codeAttr) {
        CodeAttr code = codeAttr;
        char sig1 = (this.signature == null || this.signature.length() != 1) ? ' ' : this.signature.charAt(0);
        if (sig1 == 'Z') {
            code.emitCheckcast(javalangBooleanType);
            code.emitInvokeVirtual(booleanValue_method);
        } else if (sig1 == 'V') {
            code.emitPop(1);
        } else {
            code.emitCheckcast(javalangNumberType);
            if (sig1 == 'I' || sig1 == 'S' || sig1 == 'B') {
                code.emitInvokeVirtual(intValue_method);
            } else if (sig1 == 'J') {
                code.emitInvokeVirtual(longValue_method);
            } else if (sig1 == 'D') {
                code.emitInvokeVirtual(doubleValue_method);
            } else if (sig1 == 'F') {
                code.emitInvokeVirtual(floatValue_method);
            } else {
                super.emitCoerceFromObject(code);
            }
        }
    }

    public static int compare(PrimType primType, PrimType primType2) {
        PrimType type1 = primType;
        PrimType type2 = primType2;
        char sig1 = type1.signature.charAt(0);
        char sig2 = type2.signature.charAt(0);
        if (sig1 == sig2) {
            return 0;
        }
        if (sig1 == 'V') {
            return 1;
        }
        if (sig2 == 'V') {
            return -1;
        }
        if (sig1 == 'Z' || sig2 == 'Z') {
            return -3;
        }
        if (sig1 == 'C') {
            return type2.size > 2 ? -1 : -3;
        } else if (sig2 == 'C') {
            return type1.size > 2 ? 1 : -3;
        } else if (sig1 == 'D') {
            return 1;
        } else {
            if (sig2 == 'D') {
                return -1;
            }
            if (sig1 == 'F') {
                return 1;
            }
            if (sig2 == 'F') {
                return -1;
            }
            if (sig1 == 'J') {
                return 1;
            }
            if (sig2 == 'J') {
                return -1;
            }
            if (sig1 == 'I') {
                return 1;
            }
            if (sig2 == 'I') {
                return -1;
            }
            if (sig1 == 'S') {
                return 1;
            }
            if (sig2 == 'S') {
                return -1;
            }
            return -3;
        }
    }

    public Type promotedType() {
        switch (this.signature.charAt(0)) {
            case 'B':
            case 'C':
            case 'I':
            case 'S':
            case 'Z':
                return Type.intType;
            default:
                return getImplementationType();
        }
    }

    private static char findInHierarchy(String cname) {
        int pos = numberHierarchy.indexOf(cname) - 2;
        return pos < 0 ? 0 : numberHierarchy.charAt(pos);
    }

    public int compare(Type type) {
        char otherPriority;
        int i;
        Type other = type;
        if (other instanceof PrimType) {
            if (other.getImplementationType() != other) {
                return swappedCompareResult(other.compare(this));
            }
            return compare(this, (PrimType) other);
        } else if (other instanceof ClassType) {
            char sig1 = this.signature.charAt(0);
            String otherName = other.getName();
            if (otherName == null) {
                return -1;
            }
            char thisPriority = 0;
            switch (sig1) {
                case 'B':
                    thisPriority = 'A';
                    break;
                case 'C':
                    break;
                case 'D':
                    thisPriority = 'I';
                    break;
                case 'F':
                    thisPriority = 'H';
                    break;
                case 'I':
                    thisPriority = 'C';
                    break;
                case 'J':
                    thisPriority = 'D';
                    break;
                case 'S':
                    thisPriority = 'B';
                    break;
                case 'V':
                    return 1;
                case 'Z':
                    if (otherName.equals("java.lang.Boolean")) {
                        return 0;
                    }
                    break;
            }
            if (otherName.equals("java.lang.Character")) {
                return 0;
            }
            if (thisPriority != 0 && (otherPriority = findInHierarchy(otherName)) != 0) {
                if (otherPriority == thisPriority) {
                    i = 0;
                } else {
                    i = otherPriority < thisPriority ? 1 : -1;
                }
                return i;
            } else if (otherName.equals("java.lang.Object") || other == toStringType) {
                return -1;
            } else {
                return -3;
            }
        } else if (other instanceof ArrayType) {
            return -3;
        } else {
            return swappedCompareResult(other.compare(this));
        }
    }
}

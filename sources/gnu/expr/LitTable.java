package gnu.expr;

import com.google.appinventor.components.common.PropertyTypeConstants;
import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.lists.FString;
import gnu.mapping.Symbol;
import gnu.mapping.Table2D;
import gnu.mapping.Values;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectOutput;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.IdentityHashMap;
import java.util.regex.Pattern;

public class LitTable implements ObjectOutput {
    static Table2D staticTable;
    Compilation comp;
    IdentityHashMap literalTable;
    Literal literalsChain;
    int literalsCount;
    ClassType mainClass;
    int stackPointer;
    Type[] typeStack = new Type[20];
    Object[] valueStack = new Object[20];

    static {
        Table2D table2D;
        new Table2D(100);
        staticTable = table2D;
    }

    public LitTable(Compilation compilation) {
        IdentityHashMap identityHashMap;
        Compilation comp2 = compilation;
        new IdentityHashMap(100);
        this.literalTable = identityHashMap;
        this.comp = comp2;
        this.mainClass = comp2.mainClass;
    }

    public void emit() throws IOException {
        Literal literal = this.literalsChain;
        while (true) {
            Literal init = literal;
            if (init == null) {
                break;
            }
            writeObject(init.value);
            literal = init.next;
        }
        Literal literal2 = this.literalsChain;
        while (true) {
            Literal init2 = literal2;
            if (init2 != null) {
                emit(init2, true);
                literal2 = init2.next;
            } else {
                this.literalTable = null;
                this.literalsCount = 0;
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void push(Object obj, Type type) {
        Object value = obj;
        Type type2 = type;
        if (this.stackPointer >= this.valueStack.length) {
            Object[] newValues = new Object[(2 * this.valueStack.length)];
            Type[] newTypes = new Type[(2 * this.typeStack.length)];
            System.arraycopy(this.valueStack, 0, newValues, 0, this.stackPointer);
            System.arraycopy(this.typeStack, 0, newTypes, 0, this.stackPointer);
            this.valueStack = newValues;
            this.typeStack = newTypes;
        }
        this.valueStack[this.stackPointer] = value;
        this.typeStack[this.stackPointer] = type2;
        this.stackPointer++;
    }

    /* access modifiers changed from: package-private */
    public void error(String msg) {
        Throwable th;
        Throwable th2 = th;
        new Error(msg);
        throw th2;
    }

    public void flush() {
    }

    public void close() {
    }

    public void write(int i) throws IOException {
        int i2 = i;
        error("cannot handle call to write(int) when externalizing literal");
    }

    public void writeBytes(String str) throws IOException {
        String str2 = str;
        error("cannot handle call to writeBytes(String) when externalizing literal");
    }

    public void write(byte[] bArr) throws IOException {
        byte[] bArr2 = bArr;
        error("cannot handle call to write(byte[]) when externalizing literal");
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        byte[] bArr2 = bArr;
        int i3 = i;
        int i4 = i2;
        error("cannot handle call to write(byte[],int,int) when externalizing literal");
    }

    public void writeBoolean(boolean v) {
        Object obj;
        new Boolean(v);
        push(obj, Type.booleanType);
    }

    public void writeChar(int v) {
        Object obj;
        new Character((char) v);
        push(obj, Type.charType);
    }

    public void writeByte(int v) {
        Object obj;
        new Byte((byte) v);
        push(obj, Type.byteType);
    }

    public void writeShort(int v) {
        Object obj;
        new Short((short) v);
        push(obj, Type.shortType);
    }

    public void writeInt(int v) {
        Object obj;
        new Integer(v);
        push(obj, Type.intType);
    }

    public void writeLong(long v) {
        Object obj;
        new Long(v);
        push(obj, Type.longType);
    }

    public void writeFloat(float v) {
        Object obj;
        new Float(v);
        push(obj, Type.floatType);
    }

    public void writeDouble(double v) {
        Object obj;
        new Double(v);
        push(obj, Type.doubleType);
    }

    public void writeUTF(String v) {
        push(v, Type.string_type);
    }

    public void writeChars(String v) {
        push(v, Type.string_type);
    }

    public void writeObject(Object obj) throws IOException {
        StringBuilder sb;
        Object obj2 = obj;
        Literal lit = findLiteral(obj2);
        if ((lit.flags & 3) != 0) {
            if (lit.field == null && obj2 != null && !(obj2 instanceof String)) {
                lit.assign(this);
            }
            if ((lit.flags & 2) == 0) {
                lit.flags |= 4;
            }
        } else {
            lit.flags |= 1;
            int oldStack = this.stackPointer;
            if ((obj2 instanceof FString) && ((FString) obj2).size() < 65535) {
                push(obj2.toString(), Type.string_type);
            } else if (obj2 instanceof Externalizable) {
                ((Externalizable) obj2).writeExternal(this);
            } else if (obj2 instanceof Object[]) {
                Object[] arr = (Object[]) obj2;
                for (int i = 0; i < arr.length; i++) {
                    writeObject(arr[i]);
                }
            } else if (obj2 != null && !(obj2 instanceof String) && !(lit.type instanceof ArrayType)) {
                if (obj2 instanceof BigInteger) {
                    writeChars(obj2.toString());
                } else if (obj2 instanceof BigDecimal) {
                    BigDecimal dec = (BigDecimal) obj2;
                    writeObject(dec.unscaledValue());
                    writeInt(dec.scale());
                } else if (obj2 instanceof Integer) {
                    push(obj2, Type.intType);
                } else if (obj2 instanceof Short) {
                    push(obj2, Type.shortType);
                } else if (obj2 instanceof Byte) {
                    push(obj2, Type.byteType);
                } else if (obj2 instanceof Long) {
                    push(obj2, Type.longType);
                } else if (obj2 instanceof Double) {
                    push(obj2, Type.doubleType);
                } else if (obj2 instanceof Float) {
                    push(obj2, Type.floatType);
                } else if (obj2 instanceof Character) {
                    push(obj2, Type.charType);
                } else if (obj2 instanceof Class) {
                    push(obj2, Type.java_lang_Class_type);
                } else if (obj2 instanceof Pattern) {
                    Pattern pat = (Pattern) obj2;
                    push(pat.pattern(), Type.string_type);
                    push(Integer.valueOf(pat.flags()), Type.intType);
                } else {
                    new StringBuilder();
                    error(sb.append(obj2.getClass().getName()).append(" does not implement Externalizable").toString());
                }
            }
            int nargs = this.stackPointer - oldStack;
            if (nargs == 0) {
                lit.argValues = Values.noArgs;
                lit.argTypes = Type.typeArray0;
            } else {
                lit.argValues = new Object[nargs];
                lit.argTypes = new Type[nargs];
                System.arraycopy(this.valueStack, oldStack, lit.argValues, 0, nargs);
                System.arraycopy(this.typeStack, oldStack, lit.argTypes, 0, nargs);
                this.stackPointer = oldStack;
            }
            lit.flags |= 2;
        }
        push(lit, lit.type);
    }

    public Literal findLiteral(Object obj) {
        Literal literal;
        Field fld;
        StringBuilder sb;
        Literal literal2;
        Literal literal3;
        Object value = obj;
        if (value == null) {
            return Literal.nullLiteral;
        }
        Literal literal4 = (Literal) this.literalTable.get(value);
        if (literal4 != null) {
            return literal4;
        }
        if (this.comp.immediate) {
            new Literal(value, this);
            return literal3;
        }
        Class valueClass = value.getClass();
        Type valueType = Type.make(valueClass);
        Table2D table2D = staticTable;
        Table2D table2D2 = table2D;
        synchronized (table2D) {
            try {
                Literal literal5 = (Literal) staticTable.get(value, (Object) null, (Object) null);
                if ((literal5 == null || literal5.value != value) && (valueType instanceof ClassType)) {
                    Class fldClass = valueClass;
                    ClassType classType = (ClassType) valueType;
                    while (true) {
                        ClassType fldType = classType;
                        if (staticTable.get(fldClass, Boolean.TRUE, (Object) null) != null) {
                            break;
                        }
                        Object put = staticTable.put(fldClass, Boolean.TRUE, fldClass);
                        Field fields = fldType.getFields();
                        while (true) {
                            fld = fields;
                            if (fld == null) {
                                break;
                            }
                            if ((fld.getModifiers() & 25) == 25) {
                                Object litValue = fld.getReflectField().get((Object) null);
                                if (litValue != null && fldClass.isInstance(litValue)) {
                                    new Literal(litValue, fld, this);
                                    Literal lit = literal2;
                                    Object put2 = staticTable.put(litValue, (Object) null, lit);
                                    if (value == litValue) {
                                        literal5 = lit;
                                    }
                                }
                            }
                            fields = fld.getNext();
                        }
                        fldClass = fldClass.getSuperclass();
                        if (fldClass == null) {
                            break;
                        }
                        classType = (ClassType) Type.make(fldClass);
                    }
                }
                if (literal5 != null) {
                    Object put3 = this.literalTable.put(value, literal5);
                } else {
                    new Literal(value, valueType, this);
                    literal5 = literal;
                }
                return literal5;
            } catch (Throwable th) {
                Throwable th2 = th;
                Table2D table2D3 = table2D2;
                throw th2;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Method getMethod(ClassType classType, String str, Literal literal, boolean z) {
        Literal literal2;
        ClassType type = classType;
        String name = str;
        Literal literal3 = literal;
        boolean isStatic = z;
        Type[] argTypes = literal3.argTypes;
        int argLength = argTypes.length;
        Method best = null;
        long bestArrayArgs = 0;
        boolean ambiguous = false;
        Type[] bParameters = null;
        for (Method method = type.getDeclaredMethods(); method != null; method = method.getNext()) {
            if (name.equals(method.getName()) && isStatic == method.getStaticFlag()) {
                long arrayArgs = 0;
                Type[] mParameters = method.getParameterTypes();
                int iarg = 0;
                int iparam = 0;
                while (true) {
                    if (iarg != argLength || iparam != mParameters.length) {
                        if (iarg == argLength || iparam == mParameters.length) {
                            break;
                        }
                        Type aType = argTypes[iarg];
                        Type pType = mParameters[iparam];
                        if (!aType.isSubtype(pType)) {
                            if (!(pType instanceof ArrayType) || iparam >= 64 || (aType != Type.intType && aType != Type.shortType)) {
                                break;
                            }
                            int count = ((Number) literal3.argValues[iarg]).intValue();
                            if (count < 0 && type.getName().equals("gnu.math.IntNum")) {
                                count -= Integer.MIN_VALUE;
                            }
                            Type elementType = ((ArrayType) pType).getComponentType();
                            if (count < 0 || iarg + count >= argLength) {
                                break;
                            }
                            int j = count;
                            while (true) {
                                j--;
                                if (j < 0) {
                                    iarg += count;
                                    arrayArgs |= (long) (1 << iparam);
                                    break;
                                }
                                Type t = argTypes[iarg + j + 1];
                                if (elementType instanceof PrimType) {
                                    if (elementType.getSignature() != t.getSignature()) {
                                        break;
                                    }
                                } else if (!t.isSubtype(elementType)) {
                                    break;
                                }
                            }
                        }
                        iarg++;
                        iparam++;
                    } else if (best == null || (bestArrayArgs != 0 && arrayArgs == 0)) {
                        best = method;
                        bParameters = mParameters;
                        bestArrayArgs = arrayArgs;
                    } else if (arrayArgs == 0) {
                        boolean not1 = false;
                        boolean not2 = false;
                        int j2 = argLength;
                        while (true) {
                            j2--;
                            if (j2 < 0) {
                                break;
                            }
                            int c = bParameters[j2].compare(mParameters[j2]);
                            if (c != 1) {
                                not2 = true;
                                if (not1) {
                                    break;
                                }
                            }
                            if (c != -1) {
                                not1 = true;
                                if (not2) {
                                    break;
                                }
                            }
                        }
                        if (not1) {
                            best = method;
                            bParameters = mParameters;
                        }
                        ambiguous = not1 && not2;
                    }
                }
            }
        }
        if (ambiguous) {
            return null;
        }
        if (bestArrayArgs != 0) {
            Object[] args = new Object[bParameters.length];
            Type[] types = new Type[bParameters.length];
            int iarg2 = 0;
            int iparam2 = 0;
            while (iarg2 != argLength) {
                Type pType2 = bParameters[iparam2];
                if ((bestArrayArgs & ((long) (1 << iparam2))) == 0) {
                    args[iparam2] = literal3.argValues[iarg2];
                    types[iparam2] = literal3.argTypes[iarg2];
                } else {
                    int count2 = ((Number) literal3.argValues[iarg2]).intValue();
                    boolean isIntNum = type.getName().equals("gnu.math.IntNum");
                    if (isIntNum) {
                        count2 -= Integer.MIN_VALUE;
                    }
                    Type elementType2 = ((ArrayType) pType2).getComponentType();
                    types[iparam2] = pType2;
                    args[iparam2] = Array.newInstance(elementType2.getReflectClass(), count2);
                    Object[] argValues = literal3.argValues;
                    if (!isIntNum) {
                        int j3 = count2;
                        while (true) {
                            j3--;
                            if (j3 < 0) {
                                break;
                            }
                            Array.set(args[iparam2], j3, argValues[iarg2 + 1 + j3]);
                        }
                    } else {
                        int[] arr = (int[]) args[iparam2];
                        for (int j4 = count2; j4 > 0; j4--) {
                            arr[count2 - j4] = ((Integer) argValues[iarg2 + j4]).intValue();
                        }
                    }
                    new Literal(args[iparam2], pType2);
                    Literal arrayLiteral = literal2;
                    if (elementType2 instanceof ObjectType) {
                        arrayLiteral.argValues = (Object[]) args[iparam2];
                    }
                    args[iparam2] = arrayLiteral;
                    iarg2 += count2;
                }
                iarg2++;
                iparam2++;
            }
            literal3.argValues = args;
            literal3.argTypes = types;
        }
        return best;
    }

    /* access modifiers changed from: package-private */
    public void putArgs(Literal literal, CodeAttr codeAttr) {
        Target target;
        Literal literal2 = literal;
        CodeAttr codeAttr2 = codeAttr;
        Type[] argTypes = literal2.argTypes;
        int len = argTypes.length;
        for (int i = 0; i < len; i++) {
            Object value = literal2.argValues[i];
            if (value instanceof Literal) {
                emit((Literal) value, false);
            } else {
                new StackTarget(argTypes[i]);
                this.comp.compileConstant(value, target);
            }
        }
    }

    private void store(Literal literal, boolean z, CodeAttr codeAttr) {
        Literal literal2 = literal;
        boolean ignore = z;
        CodeAttr code = codeAttr;
        if (literal2.field != null) {
            if (!ignore) {
                code.emitDup(literal2.type);
            }
            code.emitPutStatic(literal2.field);
        }
        literal2.flags |= 8;
    }

    /* access modifiers changed from: package-private */
    public void emit(Literal literal, boolean z) {
        StringBuilder sb;
        StringBuilder sb2;
        Literal literal2 = literal;
        boolean ignore = z;
        CodeAttr code = this.comp.getCode();
        if (literal2.value == null) {
            if (!ignore) {
                code.emitPushNull();
            }
        } else if (literal2.value instanceof String) {
            if (!ignore) {
                code.emitPushString(literal2.value.toString());
            }
        } else if ((literal2.flags & 8) != 0) {
            if (!ignore) {
                code.emitGetStatic(literal2.field);
            }
        } else if (literal2.value instanceof Object[]) {
            int len = literal2.argValues.length;
            Type elementType = ((ArrayType) literal2.type).getComponentType();
            code.emitPushInt(len);
            code.emitNewArray(elementType);
            store(literal2, ignore, code);
            for (int i = 0; i < len; i++) {
                Literal el = (Literal) literal2.argValues[i];
                if (el.value != null) {
                    code.emitDup(elementType);
                    code.emitPushInt(i);
                    emit(el, false);
                    code.emitArrayStore(elementType);
                }
            }
        } else if (literal2.type instanceof ArrayType) {
            code.emitPushPrimArray(literal2.value, (ArrayType) literal2.type);
            store(literal2, ignore, code);
        } else if (literal2.value instanceof Class) {
            Class clas = (Class) literal2.value;
            if (clas.isPrimitive()) {
                String cname = clas.getName();
                if (cname.equals("int")) {
                    cname = PropertyTypeConstants.PROPERTY_TYPE_INTEGER;
                }
                new StringBuilder();
                code.emitGetStatic(ClassType.make(sb2.append("java.lang.").append(Character.toUpperCase(cname.charAt(0))).append(cname.substring(1)).toString()).getDeclaredField("TYPE"));
            } else {
                this.comp.loadClassRef((ObjectType) Type.make(clas));
            }
            store(literal2, ignore, code);
        } else if (!(literal2.value instanceof ClassType) || ((ClassType) literal2.value).isExisting()) {
            ClassType type = (ClassType) literal2.type;
            boolean useDefaultInit = (literal2.flags & 4) != 0;
            Method method = null;
            boolean makeStatic = false;
            if (!useDefaultInit) {
                if (!(literal2.value instanceof Symbol)) {
                    method = getMethod(type, "valueOf", literal2, true);
                }
                if (method == null && !(literal2.value instanceof Values)) {
                    String mname = "make";
                    if (literal2.value instanceof Pattern) {
                        mname = "compile";
                    }
                    method = getMethod(type, mname, literal2, true);
                }
                if (method != null) {
                    makeStatic = true;
                } else if (literal2.argTypes.length > 0) {
                    method = getMethod(type, "<init>", literal2, false);
                }
                if (method == null) {
                    useDefaultInit = true;
                }
            }
            if (useDefaultInit) {
                method = getMethod(type, "set", literal2, false);
            }
            if (method == null && literal2.argTypes.length > 0) {
                new StringBuilder();
                error(sb.append("no method to construct ").append(literal2.type).toString());
            }
            if (makeStatic) {
                putArgs(literal2, code);
                code.emitInvokeStatic(method);
            } else if (useDefaultInit) {
                code.emitNew(type);
                code.emitDup((Type) type);
                code.emitInvokeSpecial(type.getDeclaredMethod("<init>", 0));
            } else {
                code.emitNew(type);
                code.emitDup((Type) type);
                putArgs(literal2, code);
                code.emitInvokeSpecial(method);
            }
            Method resolveMethod = (makeStatic || (literal2.value instanceof Values)) ? null : type.getDeclaredMethod("readResolve", 0);
            if (resolveMethod != null) {
                code.emitInvokeVirtual(resolveMethod);
                type.emitCoerceFromObject(code);
            }
            store(literal2, ignore && (!useDefaultInit || method == null), code);
            if (useDefaultInit && method != null) {
                if (!ignore) {
                    code.emitDup((Type) type);
                }
                putArgs(literal2, code);
                code.emitInvokeVirtual(method);
            }
        } else {
            this.comp.loadClassRef((ClassType) literal2.value);
            Method meth = Compilation.typeType.getDeclaredMethod("valueOf", 1);
            if (meth == null) {
                meth = Compilation.typeType.getDeclaredMethod("make", 1);
            }
            code.emitInvokeStatic(meth);
            code.emitCheckcast(Compilation.typeClassType);
            store(literal2, ignore, code);
        }
    }
}

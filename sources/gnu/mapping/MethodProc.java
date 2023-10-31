package gnu.mapping;

import android.support.p000v4.internal.view.SupportMenu;
import gnu.bytecode.ArrayType;
import gnu.bytecode.Type;

public abstract class MethodProc extends ProcedureN {
    public static final int NO_MATCH = -1;
    public static final int NO_MATCH_AMBIGUOUS = -851968;
    public static final int NO_MATCH_BAD_TYPE = -786432;
    public static final int NO_MATCH_TOO_FEW_ARGS = -983040;
    public static final int NO_MATCH_TOO_MANY_ARGS = -917504;
    static final Type[] unknownArgTypes = {Type.pointer_type};
    protected Object argTypes;

    public MethodProc() {
    }

    public int isApplicable(Type[] typeArr) {
        Type[] argTypes2 = typeArr;
        int argCount = argTypes2.length;
        int num = numArgs();
        if (argCount < (num & 4095) || (num >= 0 && argCount > (num >> 12))) {
            return -1;
        }
        int result = 1;
        int i = argCount;
        while (true) {
            i--;
            if (i < 0) {
                return result;
            }
            int code = getParameterType(i).compare(argTypes2[i]);
            if (code == -3) {
                return -1;
            }
            if (code < 0) {
                result = 0;
            }
        }
    }

    public int numParameters() {
        int num = numArgs();
        int max = num >> 12;
        if (max >= 0) {
            return max;
        }
        return (num & 4095) + 1;
    }

    /* access modifiers changed from: protected */
    public void resolveParameterTypes() {
        Type[] typeArr = unknownArgTypes;
        this.argTypes = typeArr;
    }

    public Type getParameterType(int i) {
        int index = i;
        if (!(this.argTypes instanceof Type[])) {
            resolveParameterTypes();
        }
        Type[] atypes = (Type[]) this.argTypes;
        if (index < atypes.length && (index < atypes.length - 1 || maxArgs() >= 0)) {
            return atypes[index];
        }
        if (maxArgs() < 0) {
            Type rtype = atypes[atypes.length - 1];
            if (rtype instanceof ArrayType) {
                return ((ArrayType) rtype).getComponentType();
            }
        }
        return Type.objectType;
    }

    public static RuntimeException matchFailAsException(int i, Procedure procedure, Object[] objArr) {
        RuntimeException runtimeException;
        int code = i;
        Procedure proc = procedure;
        Object[] args = objArr;
        int arg = (short) code;
        if ((code & SupportMenu.CATEGORY_MASK) != -786432) {
            new WrongArguments(proc, args.length);
            return runtimeException;
        }
        WrongType wrongType = r11;
        WrongType wrongType2 = new WrongType(proc, arg, arg > 0 ? args[arg - 1] : null);
        return wrongType;
    }

    public Object applyN(Object[] objArr) throws Throwable {
        Object[] args = objArr;
        checkArgCount(this, args.length);
        CallContext ctx = CallContext.getInstance();
        checkN(args, ctx);
        return ctx.runUntilValue();
    }

    public static MethodProc mostSpecific(MethodProc methodProc, MethodProc methodProc2) {
        MethodProc proc1 = methodProc;
        MethodProc proc2 = methodProc2;
        boolean not1 = false;
        boolean not2 = false;
        int min1 = proc1.minArgs();
        int min2 = proc2.minArgs();
        int max1 = proc1.maxArgs();
        int max2 = proc2.maxArgs();
        if ((max1 >= 0 && max1 < min2) || (max2 >= 0 && max2 < min1)) {
            return null;
        }
        int num1 = proc1.numParameters();
        int num2 = proc2.numParameters();
        int limit = num1 > num2 ? num1 : num2;
        if (max1 != max2) {
            if (max1 < 0) {
                not1 = true;
            }
            if (max2 < 0) {
                not2 = true;
            }
        }
        if (min1 < min2) {
            not1 = true;
        } else if (min1 > min2) {
            not2 = true;
        }
        for (int i = 0; i < limit; i++) {
            int comp = proc1.getParameterType(i).compare(proc2.getParameterType(i));
            if (comp == -1) {
                not2 = true;
                if (not1) {
                    return null;
                }
            }
            if (comp == 1) {
                not1 = true;
                if (not2) {
                    return null;
                }
            }
        }
        return not2 ? proc1 : not1 ? proc2 : null;
    }

    public static int mostSpecific(MethodProc[] methodProcArr, int i) {
        MethodProc[] procs = methodProcArr;
        int length = i;
        if (length <= 1) {
            return length - 1;
        }
        MethodProc best = procs[0];
        MethodProc[] bests = null;
        int bestn = 0;
        for (int i2 = 1; i2 < length; i2++) {
            MethodProc method = procs[i2];
            if (best == null) {
                int j = 0;
                while (true) {
                    if (j >= bestn) {
                        best = method;
                        bestn = i2;
                        break;
                    }
                    MethodProc old = bests[j];
                    MethodProc winner = mostSpecific(old, method);
                    if (winner == old) {
                        break;
                    } else if (winner == null) {
                        int i3 = bestn;
                        bestn++;
                        bests[i3] = method;
                        break;
                    } else {
                        j++;
                    }
                }
            } else {
                MethodProc winner2 = mostSpecific(best, method);
                if (winner2 == null) {
                    if (bests == null) {
                        bests = new MethodProc[length];
                    }
                    bests[0] = best;
                    bests[1] = method;
                    bestn = 2;
                    best = null;
                } else if (winner2 == method) {
                    best = method;
                    bestn = i2;
                }
            }
        }
        return best == null ? -1 : bestn;
    }
}

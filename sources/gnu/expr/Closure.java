package gnu.expr;

import gnu.bytecode.ArrayType;
import gnu.bytecode.Type;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.MethodProc;
import java.lang.reflect.Array;

/* compiled from: LambdaExp */
class Closure extends MethodProc {
    Object[][] evalFrames;
    LambdaExp lambda;

    public int numArgs() {
        return this.lambda.min_args | (this.lambda.max_args << 12);
    }

    public Closure(LambdaExp lexp, CallContext ctx) {
        this.lambda = lexp;
        Object[][] oldFrames = ctx.evalFrames;
        if (oldFrames != null) {
            int n = oldFrames.length;
            while (n > 0 && oldFrames[n - 1] == null) {
                n--;
            }
            this.evalFrames = new Object[n][];
            System.arraycopy(oldFrames, 0, this.evalFrames, 0, n);
        }
        setSymbol(this.lambda.getSymbol());
    }

    public int match0(CallContext ctx) {
        return matchN(new Object[0], ctx);
    }

    public int match1(Object arg1, CallContext ctx) {
        return matchN(new Object[]{arg1}, ctx);
    }

    public int match2(Object arg1, Object arg2, CallContext ctx) {
        Object[] objArr = new Object[2];
        objArr[0] = arg1;
        Object[] objArr2 = objArr;
        objArr2[1] = arg2;
        return matchN(objArr2, ctx);
    }

    public int match3(Object arg1, Object arg2, Object arg3, CallContext ctx) {
        Object[] objArr = new Object[3];
        objArr[0] = arg1;
        Object[] objArr2 = objArr;
        objArr2[1] = arg2;
        Object[] objArr3 = objArr2;
        objArr3[2] = arg3;
        return matchN(objArr3, ctx);
    }

    public int match4(Object arg1, Object arg2, Object arg3, Object arg4, CallContext ctx) {
        Object[] objArr = new Object[4];
        objArr[0] = arg1;
        Object[] objArr2 = objArr;
        objArr2[1] = arg2;
        Object[] objArr3 = objArr2;
        objArr3[2] = arg3;
        Object[] objArr4 = objArr3;
        objArr4[3] = arg4;
        return matchN(objArr4, ctx);
    }

    public int matchN(Object[] objArr, CallContext callContext) {
        int length;
        int length2;
        Location value;
        Object[] args = objArr;
        CallContext ctx = callContext;
        int num = numArgs();
        int nargs = args.length;
        int min = num & 4095;
        if (nargs < min) {
            return -983040 | min;
        }
        int max = num >> 12;
        if (nargs > max && max >= 0) {
            return -917504 | max;
        }
        Object[] evalFrame = new Object[this.lambda.frameSize];
        if (this.lambda.keywords == null) {
            length = 0;
        } else {
            length = this.lambda.keywords.length;
        }
        int key_args = length;
        if (this.lambda.defaultArgs == null) {
            length2 = 0;
        } else {
            length2 = this.lambda.defaultArgs.length - key_args;
        }
        int opt_args = length2;
        int i = 0;
        int opt_i = 0;
        int key_i = 0;
        int min_args = this.lambda.min_args;
        Declaration firstDecl = this.lambda.firstDecl();
        while (true) {
            Declaration decl = firstDecl;
            if (decl != null) {
                if (i < min_args) {
                    int i2 = i;
                    i++;
                    value = args[i2];
                } else if (i < min_args + opt_args) {
                    if (i < nargs) {
                        int i3 = i;
                        i++;
                        value = args[i3];
                    } else {
                        value = this.lambda.evalDefaultArg(opt_i, ctx);
                    }
                    opt_i++;
                } else {
                    if (this.lambda.max_args >= 0 || i != min_args + opt_args) {
                        int i4 = key_i;
                        key_i++;
                        Object value2 = Keyword.searchForKeyword(args, min_args + opt_args, this.lambda.keywords[i4]);
                        if (value2 == Special.dfault) {
                            value2 = this.lambda.evalDefaultArg(opt_i, ctx);
                        }
                        opt_i++;
                    } else if (decl.type instanceof ArrayType) {
                        int rem = nargs - i;
                        Type elementType = ((ArrayType) decl.type).getComponentType();
                        if (elementType == Type.objectType) {
                            Object[] rest = new Object[rem];
                            System.arraycopy(args, i, rest, 0, rem);
                            value = rest;
                        } else {
                            value = Array.newInstance(elementType.getReflectClass(), rem);
                            int j = 0;
                            while (j < rem) {
                                try {
                                    Array.set(value, j, elementType.coerceFromObject(args[i + j]));
                                    j++;
                                } catch (ClassCastException e) {
                                    ClassCastException classCastException = e;
                                    return -786432 | (i + j);
                                }
                            }
                        }
                    } else {
                        value = LList.makeList(args, i);
                    }
                }
                if (decl.type != null) {
                    try {
                        value = decl.type.coerceFromObject(value);
                    } catch (ClassCastException e2) {
                        ClassCastException classCastException2 = e2;
                        return -786432 | i;
                    }
                }
                if (decl.isIndirectBinding()) {
                    Location loc = decl.makeIndirectLocationFor();
                    loc.set(value);
                    value = loc;
                }
                evalFrame[decl.evalIndex] = value;
                firstDecl = decl.nextDecl();
            } else {
                ctx.values = evalFrame;
                ctx.where = 0;
                ctx.next = 0;
                ctx.proc = this;
                return 0;
            }
        }
    }

    public void apply(CallContext callContext) throws Throwable {
        int length;
        StringBuffer stringBuffer;
        Throwable th;
        CallContext ctx = callContext;
        int level = ScopeExp.nesting(this.lambda);
        Object[] evalFrame = ctx.values;
        Object[][] saveFrames = ctx.evalFrames;
        if (this.evalFrames == null) {
            length = 0;
        } else {
            length = this.evalFrames.length;
        }
        int numFrames = length;
        if (level >= numFrames) {
            numFrames = level;
        }
        Object[][] newFrames = new Object[(numFrames + 10)][];
        if (this.evalFrames != null) {
            System.arraycopy(this.evalFrames, 0, newFrames, 0, this.evalFrames.length);
        }
        newFrames[level] = evalFrame;
        ctx.evalFrames = newFrames;
        try {
            if (this.lambda.body == null) {
                new StringBuffer("procedure ");
                StringBuffer sbuf = stringBuffer;
                String name = this.lambda.getName();
                if (name == null) {
                    name = "<anonymous>";
                }
                StringBuffer append = sbuf.append(name);
                int line = this.lambda.getLineNumber();
                if (line > 0) {
                    StringBuffer append2 = sbuf.append(" at line ");
                    StringBuffer append3 = sbuf.append(line);
                }
                StringBuffer append4 = sbuf.append(" was called before it was expanded");
                Throwable th2 = th;
                new RuntimeException(sbuf.toString());
                throw th2;
            }
            this.lambda.body.apply(ctx);
            ctx.evalFrames = saveFrames;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            ctx.evalFrames = saveFrames;
            throw th4;
        }
    }

    public Object getProperty(Object obj, Object obj2) {
        Object key = obj;
        Object defaultValue = obj2;
        Object value = super.getProperty(key, defaultValue);
        if (value == null) {
            value = this.lambda.getProperty(key, defaultValue);
        }
        return value;
    }
}

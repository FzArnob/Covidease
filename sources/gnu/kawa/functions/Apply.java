package gnu.kawa.functions;

import gnu.lists.Pair;
import gnu.lists.Sequence;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import gnu.mapping.WrongArguments;
import gnu.mapping.WrongType;

public class Apply extends ProcedureN {
    ApplyToArgs applyToArgs;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Apply(String name, ApplyToArgs applyToArgs2) {
        super(name);
        this.applyToArgs = applyToArgs2;
    }

    public static Object[] getArguments(Object[] objArr, int i, Procedure procedure) {
        int last_count;
        Throwable th;
        Throwable th2;
        StringBuilder sb;
        Object[] args = objArr;
        int skip = i;
        Procedure proc = procedure;
        int count = args.length;
        if (count < skip + 1) {
            Throwable th3 = th2;
            new StringBuilder();
            new WrongArguments("apply", 2, sb.append("(apply proc [args] args) [count:").append(count).append(" skip:").append(skip).append("]").toString());
            throw th3;
        }
        Object last = args[count - 1];
        if (last instanceof Object[]) {
            Object[] last_arr = (Object[]) last;
            if (count == 2) {
                return last_arr;
            }
            last_count = last_arr.length;
        } else if (last instanceof Sequence) {
            last_count = ((Sequence) last).size();
        } else {
            last_count = -1;
        }
        if (last_count < 0) {
            Throwable th4 = th;
            new WrongType(proc, count, last, "sequence or array");
            throw th4;
        }
        Object[] proc_args = new Object[(last_count + ((count - skip) - 1))];
        int i2 = 0;
        while (i2 < (count - skip) - 1) {
            proc_args[i2] = args[i2 + skip];
            i2++;
        }
        if (last instanceof Object[]) {
            System.arraycopy((Object[]) last, 0, proc_args, i2, last_count);
        } else {
            while (last instanceof Pair) {
                Pair pair = (Pair) last;
                int i3 = i2;
                i2++;
                proc_args[i3] = pair.getCar();
                last = pair.getCdr();
                last_count--;
            }
            if (last_count > 0) {
                Sequence last_seq = (Sequence) last;
                for (int j = 0; j < last_count; j++) {
                    int i4 = i2;
                    i2++;
                    proc_args[i4] = last_seq.get(j);
                }
            }
        }
        return proc_args;
    }

    public Object applyN(Object[] args) throws Throwable {
        return this.applyToArgs.applyN(getArguments(args, 0, this));
    }

    public void apply(CallContext callContext) throws Throwable {
        CallContext ctx = callContext;
        this.applyToArgs.checkN(getArguments(ctx.getArgs(), 0, this), ctx);
    }
}

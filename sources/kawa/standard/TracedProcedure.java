package kawa.standard;

import gnu.kawa.functions.ObjectFormat;
import gnu.mapping.Environment;
import gnu.mapping.Location;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import gnu.mapping.Symbol;
import gnu.math.IntNum;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

public class TracedProcedure extends ProcedureN {
    static Symbol curIndentSym = Symbol.makeUninterned("current-indentation");
    static int indentationStep = 2;
    boolean enabled;
    public Procedure proc;

    public TracedProcedure(Procedure procedure, boolean enable) {
        Procedure proc2 = procedure;
        this.proc = proc2;
        this.enabled = enable;
        String name = proc2.getName();
        if (name != null) {
            setName(name);
        }
    }

    static void put(Object value, PrintWriter printWriter) {
        PrintWriter out = printWriter;
        try {
            if (!ObjectFormat.format(value, (Writer) out, 50, true)) {
                out.print("...");
            }
        } catch (IOException e) {
            out.print("<caught ");
            out.print(e);
            out.print('>');
        }
    }

    static void indent(int i, PrintWriter printWriter) {
        int i2 = i;
        PrintWriter out = printWriter;
        while (true) {
            i2--;
            if (i2 >= 0) {
                out.print(' ');
            } else {
                return;
            }
        }
    }

    public Object applyN(Object[] objArr) throws Throwable {
        int curIndent;
        StringBuilder sb;
        Object[] args = objArr;
        if (!this.enabled) {
            return this.proc.applyN(args);
        }
        Location curIndentLoc = Environment.getCurrent().getLocation(curIndentSym);
        Object oldIndent = curIndentLoc.get((Object) null);
        if (!(oldIndent instanceof IntNum)) {
            curIndent = 0;
            curIndentLoc.set(IntNum.zero());
        } else {
            curIndent = ((IntNum) oldIndent).intValue();
        }
        PrintWriter out = OutPort.errDefault();
        String name = getName();
        if (name == null) {
            name = "??";
        }
        indent(curIndent, out);
        out.print("call to ");
        out.print(name);
        int len = args.length;
        out.print(" (");
        for (int i = 0; i < len; i++) {
            if (i > 0) {
                out.print(' ');
            }
            put(args[i], out);
        }
        out.println(")");
        Object save = curIndentLoc.setWithSave(IntNum.make(curIndent + indentationStep));
        try {
            Object result = this.proc.applyN(args);
            curIndentLoc.setRestore(save);
            indent(curIndent, out);
            out.print("return from ");
            out.print(name);
            out.print(" => ");
            put(result, out);
            out.println();
            return result;
        } catch (RuntimeException e) {
            RuntimeException e2 = e;
            indent(curIndent, out);
            new StringBuilder();
            out.println(sb.append("procedure ").append(name).append(" throws exception ").append(e2).toString());
            throw e2;
        } catch (Throwable th) {
            Throwable th2 = th;
            curIndentLoc.setRestore(save);
            throw th2;
        }
    }

    public static Procedure doTrace(Procedure procedure, boolean z) {
        Procedure proc2;
        Procedure proc3 = procedure;
        boolean enable = z;
        if (proc3 instanceof TracedProcedure) {
            ((TracedProcedure) proc3).enabled = enable;
            return proc3;
        }
        new TracedProcedure(proc3, enable);
        return proc2;
    }

    public void print(PrintWriter printWriter) {
        PrintWriter ps = printWriter;
        ps.print("#<procedure ");
        String n = getName();
        if (n == null) {
            ps.print("<unnamed>");
        } else {
            ps.print(n);
        }
        ps.print(this.enabled ? ", traced>" : ">");
    }
}

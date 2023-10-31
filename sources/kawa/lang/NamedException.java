package kawa.lang;

import gnu.mapping.Procedure;
import gnu.mapping.Symbol;

public class NamedException extends RuntimeException {
    Object[] args;
    Symbol name;

    public NamedException(Symbol name2, Object[] args2) {
        this.name = name2;
        this.args = args2;
    }

    public void checkMatch(Object obj) {
        Object key = obj;
        if (key != this.name && key != Boolean.TRUE) {
            throw this;
        }
    }

    public Object applyHandler(Object key, Procedure handler) throws Throwable {
        checkMatch(key);
        return handler.applyN(this.args);
    }

    public String toString() {
        StringBuffer stringBuffer;
        new StringBuffer();
        StringBuffer buf = stringBuffer;
        StringBuffer append = buf.append("#<ERROR");
        int i = 0;
        int len = this.args.length;
        if (len > 1 && this.args[0] == "misc-error") {
            i = 0 + 1;
        }
        while (i < len) {
            StringBuffer append2 = buf.append(' ');
            StringBuffer append3 = buf.append(this.args[i]);
            i++;
        }
        StringBuffer append4 = buf.append(">");
        return buf.toString();
    }
}

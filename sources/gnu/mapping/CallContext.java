package gnu.mapping;

import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.math.IntNum;

public class CallContext {
    public static final int ARG_IN_IVALUE1 = 5;
    public static final int ARG_IN_IVALUE2 = 6;
    public static final int ARG_IN_VALUE1 = 1;
    public static final int ARG_IN_VALUE2 = 2;
    public static final int ARG_IN_VALUE3 = 3;
    public static final int ARG_IN_VALUE4 = 4;
    public static final int ARG_IN_VALUES_ARRAY = 0;
    static ThreadLocal currentContext;
    public Consumer consumer = this.vstack;
    public int count;
    public Object[][] evalFrames;
    public int ivalue1;
    public int ivalue2;
    public int next;

    /* renamed from: pc */
    public int f239pc;
    public Procedure proc;
    public Object value1;
    public Object value2;
    public Object value3;
    public Object value4;
    public Object[] values;
    public ValueStack vstack;
    public int where;

    public CallContext() {
        ValueStack valueStack;
        new ValueStack();
        this.vstack = valueStack;
    }

    static {
        ThreadLocal threadLocal;
        new ThreadLocal();
        currentContext = threadLocal;
    }

    public static void setInstance(CallContext ctx) {
        Thread currentThread = Thread.currentThread();
        currentContext.set(ctx);
    }

    public static CallContext getOnlyInstance() {
        return (CallContext) currentContext.get();
    }

    public static CallContext getInstance() {
        CallContext callContext;
        CallContext ctx = getOnlyInstance();
        if (ctx == null) {
            new CallContext();
            ctx = callContext;
            setInstance(ctx);
        }
        return ctx;
    }

    /* access modifiers changed from: package-private */
    public Object getArgAsObject(int i) {
        int i2 = i;
        if (i2 < 8) {
            switch ((this.where >> (4 * i2)) & 15) {
                case 1:
                    return this.value1;
                case 2:
                    return this.value2;
                case 3:
                    return this.value3;
                case 4:
                    return this.value4;
                case 5:
                    return IntNum.make(this.ivalue1);
                case 6:
                    return IntNum.make(this.ivalue2);
            }
        }
        return this.values[i2];
    }

    public int getArgCount() {
        return this.count;
    }

    public Object getNextArg() {
        Throwable th;
        if (this.next >= this.count) {
            Throwable th2 = th;
            new WrongArguments((Procedure) null, this.count);
            throw th2;
        }
        int i = this.next;
        int i2 = i + 1;
        this.next = i2;
        return getArgAsObject(i);
    }

    public int getNextIntArg() {
        Throwable th;
        if (this.next >= this.count) {
            Throwable th2 = th;
            new WrongArguments((Procedure) null, this.count);
            throw th2;
        }
        int i = this.next;
        int i2 = i + 1;
        this.next = i2;
        return ((Number) getArgAsObject(i)).intValue();
    }

    public Object getNextArg(Object obj) {
        Object defaultValue = obj;
        if (this.next >= this.count) {
            return defaultValue;
        }
        int i = this.next;
        int i2 = i + 1;
        this.next = i2;
        return getArgAsObject(i);
    }

    public int getNextIntArg(int i) {
        int defaultValue = i;
        if (this.next >= this.count) {
            return defaultValue;
        }
        int i2 = this.next;
        int i3 = i2 + 1;
        this.next = i3;
        return ((Number) getArgAsObject(i2)).intValue();
    }

    public final Object[] getRestArgsArray(int i) {
        int next2 = i;
        Object[] args = new Object[(this.count - next2)];
        int i2 = 0;
        while (next2 < this.count) {
            int i3 = i2;
            i2++;
            int i4 = next2;
            next2++;
            args[i3] = getArgAsObject(i4);
        }
        return args;
    }

    public final LList getRestArgsList(int i) {
        LList lList;
        int next2 = i;
        LList nil = LList.Empty;
        LList list = nil;
        LList lList2 = null;
        while (true) {
            LList last = lList2;
            if (next2 >= this.count) {
                return list;
            }
            int i2 = next2;
            next2++;
            new Pair(getArgAsObject(i2), nil);
            LList pair = lList;
            if (last == null) {
                list = pair;
            } else {
                last.setCdr(pair);
            }
            lList2 = pair;
        }
    }

    public void lastArg() {
        Throwable th;
        if (this.next < this.count) {
            Throwable th2 = th;
            new WrongArguments((Procedure) null, this.count);
            throw th2;
        }
        this.values = null;
    }

    public Object[] getArgs() {
        if (this.where == 0) {
            return this.values;
        }
        int n = this.count;
        this.next = 0;
        Object[] args = new Object[n];
        for (int i = 0; i < n; i++) {
            args[i] = getNextArg();
        }
        return args;
    }

    public void runUntilDone() throws Throwable {
        while (true) {
            Procedure proc2 = this.proc;
            if (proc2 != null) {
                this.proc = null;
                proc2.apply(this);
            } else {
                return;
            }
        }
    }

    public final int startFromContext() {
        ValueStack vst = this.vstack;
        int oindex = vst.find(this.consumer);
        vst.ensureSpace(3);
        int gapStart = vst.gapStart;
        int i = gapStart;
        int gapStart2 = gapStart + 1;
        vst.data[i] = 61698;
        vst.setIntN(gapStart2, oindex);
        int gapStart3 = gapStart2 + 2;
        this.consumer = vst;
        vst.gapStart = gapStart3;
        return gapStart3;
    }

    public final Object getFromContext(int i) throws Throwable {
        int oldIndex = i;
        runUntilDone();
        ValueStack vst = this.vstack;
        Object result = Values.make(vst, oldIndex, vst.gapStart);
        cleanupFromContext(oldIndex);
        return result;
    }

    public final void cleanupFromContext(int i) {
        int oldIndex = i;
        ValueStack vst = this.vstack;
        char[] data = vst.data;
        int oindex = (data[oldIndex - 2] << 16) | (data[oldIndex - 1] & 65535);
        this.consumer = (Consumer) vst.objects[oindex];
        vst.objects[oindex] = null;
        vst.oindex = oindex;
        vst.gapStart = oldIndex - 3;
    }

    /* JADX INFO: finally extract failed */
    public final Object runUntilValue() throws Throwable {
        Consumer consumerSave = this.consumer;
        ValueStack vst = this.vstack;
        this.consumer = vst;
        int dindexSave = vst.gapStart;
        int oindexSave = vst.oindex;
        try {
            runUntilDone();
            Object make = Values.make(vst, dindexSave, vst.gapStart);
            this.consumer = consumerSave;
            vst.gapStart = dindexSave;
            vst.oindex = oindexSave;
            return make;
        } catch (Throwable th) {
            Throwable th2 = th;
            this.consumer = consumerSave;
            vst.gapStart = dindexSave;
            vst.oindex = oindexSave;
            throw th2;
        }
    }

    public final void runUntilValue(Consumer out) throws Throwable {
        Consumer consumerSave = this.consumer;
        this.consumer = out;
        try {
            runUntilDone();
            this.consumer = consumerSave;
        } catch (Throwable th) {
            Throwable th2 = th;
            this.consumer = consumerSave;
            throw th2;
        }
    }

    public void writeValue(Object value) {
        Values.writeValues(value, this.consumer);
    }
}

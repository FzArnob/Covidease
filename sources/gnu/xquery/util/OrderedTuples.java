package gnu.xquery.util;

import gnu.kawa.functions.NumberCompare;
import gnu.kawa.xml.KNode;
import gnu.kawa.xml.UntypedAtomic;
import gnu.lists.Consumer;
import gnu.lists.FilterConsumer;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;

public class OrderedTuples extends FilterConsumer {
    Procedure body;
    Object[] comps;
    int first;

    /* renamed from: n */
    int f251n;
    int[] next;
    Object[] tuples = new Object[10];

    public void writeObject(Object obj) {
        Object v = obj;
        if (this.f251n >= this.tuples.length) {
            Object[] tmp = new Object[(2 * this.f251n)];
            System.arraycopy(this.tuples, 0, tmp, 0, this.f251n);
            this.tuples = tmp;
        }
        Object[] objArr = this.tuples;
        int i = this.f251n;
        int i2 = i + 1;
        this.f251n = i2;
        objArr[i] = v;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    OrderedTuples() {
        super((Consumer) null);
    }

    public static OrderedTuples make$V(Procedure body2, Object[] comps2) {
        OrderedTuples orderedTuples;
        new OrderedTuples();
        OrderedTuples tuples2 = orderedTuples;
        tuples2.comps = comps2;
        tuples2.body = body2;
        return tuples2;
    }

    public void run$X(CallContext ctx) throws Throwable {
        this.first = listsort(0);
        emit(ctx);
    }

    /* access modifiers changed from: package-private */
    public void emit(CallContext callContext) throws Throwable {
        CallContext ctx = callContext;
        int i = this.first;
        while (true) {
            int p = i;
            if (p >= 0) {
                emit(p, ctx);
                i = this.next[p];
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void emit(int index, CallContext callContext) throws Throwable {
        CallContext ctx = callContext;
        this.body.checkN((Object[]) this.tuples[index], ctx);
        ctx.runUntilDone();
    }

    /* access modifiers changed from: package-private */
    public int cmp(int i, int i2) throws Throwable {
        int c;
        boolean z;
        int i3;
        int a = i;
        int b = i2;
        int i4 = 0;
        while (true) {
            if (i4 >= this.comps.length) {
                return 0;
            }
            Procedure comparator = (Procedure) this.comps[i4];
            String flags = (String) this.comps[i4 + 1];
            NamedCollator collator = (NamedCollator) this.comps[i4 + 2];
            if (collator == null) {
                collator = NamedCollator.codepointCollation;
            }
            Object val1 = comparator.applyN((Object[]) this.tuples[a]);
            Object val2 = comparator.applyN((Object[]) this.tuples[b]);
            Object val12 = KNode.atomicValue(val1);
            Object val22 = KNode.atomicValue(val2);
            if (val12 instanceof UntypedAtomic) {
                val12 = val12.toString();
            }
            if (val22 instanceof UntypedAtomic) {
                val22 = val22.toString();
            }
            boolean empty1 = SequenceUtils.isEmptySequence(val12);
            boolean empty2 = SequenceUtils.isEmptySequence(val22);
            if (!empty1 || !empty2) {
                if (empty1 || empty2) {
                    boolean z2 = empty1;
                    if (flags.charAt(1) == 'L') {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z2 == z) {
                        i3 = -1;
                    } else {
                        i3 = 1;
                    }
                    c = i3;
                } else {
                    boolean isNaN1 = (val12 instanceof Number) && Double.isNaN(((Number) val12).doubleValue());
                    boolean isNaN2 = (val22 instanceof Number) && Double.isNaN(((Number) val22).doubleValue());
                    if (!isNaN1 || !isNaN2) {
                        if (isNaN1 || isNaN2) {
                            c = isNaN1 == (flags.charAt(1) == 'L') ? -1 : 1;
                        } else if (!(val12 instanceof Number) || !(val22 instanceof Number)) {
                            c = collator.compare(val12.toString(), val22.toString());
                        } else {
                            c = NumberCompare.compare(val12, val22, false);
                        }
                    }
                }
                if (c != 0) {
                    return flags.charAt(0) == 'A' ? c : -c;
                }
            }
            i4 += 3;
        }
    }

    /* access modifiers changed from: package-private */
    public int listsort(int i) throws Throwable {
        int e;
        int list = i;
        if (this.f251n == 0) {
            return -1;
        }
        this.next = new int[this.f251n];
        int i2 = 1;
        while (i2 != this.f251n) {
            this.next[i2 - 1] = i2;
            i2++;
        }
        this.next[i2 - 1] = -1;
        int i3 = 1;
        while (true) {
            int insize = i3;
            int p = list;
            list = -1;
            int tail = -1;
            int nmerges = 0;
            while (p >= 0) {
                nmerges++;
                int q = p;
                int psize = 0;
                for (int i4 = 0; i4 < insize; i4++) {
                    psize++;
                    q = this.next[q];
                    if (q < 0) {
                        break;
                    }
                }
                int qsize = insize;
                while (true) {
                    if (psize > 0 || (qsize > 0 && q >= 0)) {
                        if (psize == 0) {
                            e = q;
                            q = this.next[q];
                            qsize--;
                        } else if (qsize == 0 || q < 0) {
                            e = p;
                            p = this.next[p];
                            psize--;
                        } else if (cmp(p, q) <= 0) {
                            e = p;
                            p = this.next[p];
                            psize--;
                        } else {
                            e = q;
                            q = this.next[q];
                            qsize--;
                        }
                        if (tail >= 0) {
                            this.next[tail] = e;
                        } else {
                            list = e;
                        }
                        tail = e;
                    }
                }
                p = q;
            }
            this.next[tail] = -1;
            if (nmerges <= 1) {
                return list;
            }
            i3 = insize * 2;
        }
    }
}

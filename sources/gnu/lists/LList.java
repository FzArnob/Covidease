package gnu.lists;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.util.Iterator;
import java.util.List;

public class LList extends ExtSequence implements Sequence, Externalizable, Comparable {
    public static final LList Empty;

    public LList() {
    }

    static {
        LList lList;
        new LList();
        Empty = lList;
    }

    public static int listLength(Object obj, boolean z) {
        Object obj2 = obj;
        boolean allowOtherSequence = z;
        int n = 0;
        Object slow = obj2;
        Object fast = obj2;
        while (fast != Empty) {
            if (fast instanceof Pair) {
                Pair fast_pair = (Pair) fast;
                if (fast_pair.cdr == Empty) {
                    return n + 1;
                }
                if (fast == slow && n > 0) {
                    return -1;
                }
                if (!(fast_pair.cdr instanceof Pair)) {
                    n++;
                    fast = fast_pair.cdr;
                } else if (!(slow instanceof Pair)) {
                    return -2;
                } else {
                    slow = ((Pair) slow).cdr;
                    fast = ((Pair) fast_pair.cdr).cdr;
                    n += 2;
                }
            } else if (!(fast instanceof Sequence) || !allowOtherSequence) {
                return -2;
            } else {
                int j = ((Sequence) fast).size();
                return j >= 0 ? n + j : j;
            }
        }
        return n;
    }

    public boolean equals(Object obj) {
        return this == obj;
    }

    public int compareTo(Object obj) {
        return obj == Empty ? 0 : -1;
    }

    public int size() {
        return 0;
    }

    public boolean isEmpty() {
        return true;
    }

    public SeqPosition getIterator(int index) {
        SeqPosition seqPosition;
        new LListPosition(this, index, false);
        return seqPosition;
    }

    public int createPos(int index, boolean isAfter) {
        SeqPosition seqPosition;
        new LListPosition(this, index, isAfter);
        return PositionManager.manager.register(seqPosition);
    }

    public int createRelativePos(int i, int i2, boolean z) {
        LListPosition lListPosition;
        Throwable th;
        Throwable th2;
        int pos = i;
        int delta = i2;
        boolean isAfter = z;
        boolean old_after = isAfterPos(pos);
        if (delta < 0 || pos == 0) {
            return super.createRelativePos(pos, delta, isAfter);
        }
        if (delta == 0) {
            if (isAfter == old_after) {
                return copyPos(pos);
            }
            if (isAfter && !old_after) {
                return super.createRelativePos(pos, delta, isAfter);
            }
        }
        if (pos < 0) {
            Throwable th3 = th2;
            new IndexOutOfBoundsException();
            throw th3;
        }
        LListPosition old = (LListPosition) PositionManager.getPositionObject(pos);
        if (old.xpos == null) {
            return super.createRelativePos(pos, delta, isAfter);
        }
        new LListPosition(old);
        LListPosition it = lListPosition;
        int it_ipos = it.ipos;
        if (isAfter && !old_after) {
            delta--;
            it_ipos += 3;
        }
        if (!isAfter && old_after) {
            delta++;
            it_ipos -= 3;
        }
        for (Object it_xpos = it.xpos; it_xpos instanceof Pair; it_xpos = ((Pair) it_xpos).cdr) {
            delta--;
            if (delta < 0) {
                it.ipos = it_ipos;
                it.xpos = it_xpos;
                return PositionManager.manager.register(it);
            }
            it_ipos += 2;
        }
        Throwable th4 = th;
        new IndexOutOfBoundsException();
        throw th4;
    }

    public boolean hasNext(int i) {
        int i2 = i;
        return false;
    }

    public int nextPos(int i) {
        int i2 = i;
        return 0;
    }

    public Object getPosNext(int i) {
        int i2 = i;
        return eofValue;
    }

    public Object getPosPrevious(int i) {
        int i2 = i;
        return eofValue;
    }

    /* access modifiers changed from: protected */
    public void setPosNext(int i, Object obj) {
        Throwable th;
        int ipos = i;
        Object value = obj;
        if (ipos > 0) {
            PositionManager.getPositionObject(ipos).setNext(value);
        } else if (ipos == -1 || !(this instanceof Pair)) {
            Throwable th2 = th;
            new IndexOutOfBoundsException();
            throw th2;
        } else {
            ((Pair) this).car = value;
        }
    }

    /* access modifiers changed from: protected */
    public void setPosPrevious(int i, Object obj) {
        Throwable th;
        int ipos = i;
        Object value = obj;
        if (ipos > 0) {
            PositionManager.getPositionObject(ipos).setPrevious(value);
        } else if (ipos == 0 || !(this instanceof Pair)) {
            Throwable th2 = th;
            new IndexOutOfBoundsException();
            throw th2;
        } else {
            ((Pair) this).lastPair().car = value;
        }
    }

    public Object get(int i) {
        Throwable th;
        int i2 = i;
        Throwable th2 = th;
        new IndexOutOfBoundsException();
        throw th2;
    }

    public static final int length(Object obj) {
        int count = 0;
        for (Object arg = obj; arg instanceof Pair; arg = ((Pair) arg).cdr) {
            count++;
        }
        return count;
    }

    public static LList makeList(List vals) {
        LList lList;
        Iterator e = vals.iterator();
        LList result = Empty;
        LList lList2 = null;
        while (true) {
            LList last = lList2;
            if (!e.hasNext()) {
                return result;
            }
            new Pair(e.next(), Empty);
            LList pair = lList;
            if (last == null) {
                result = pair;
            } else {
                last.cdr = pair;
            }
            lList2 = pair;
        }
    }

    public static LList makeList(Object[] objArr, int i, int length) {
        LList lList;
        Object[] vals = objArr;
        int offset = i;
        LList result = Empty;
        int i2 = length;
        while (true) {
            i2--;
            if (i2 < 0) {
                return result;
            }
            new Pair(vals[offset + i2], result);
            result = lList;
        }
    }

    public static LList makeList(Object[] objArr, int i) {
        LList lList;
        Object[] vals = objArr;
        int offset = i;
        LList result = Empty;
        int i2 = vals.length - offset;
        while (true) {
            i2--;
            if (i2 < 0) {
                return result;
            }
            new Pair(vals[offset + i2], result);
            result = lList;
        }
    }

    public void consume(Consumer consumer) {
        Consumer out = consumer;
        Object list = this;
        out.startElement("list");
        while (list instanceof Pair) {
            if (list != this) {
                out.write(32);
            }
            Pair pair = (Pair) list;
            out.writeObject(pair.car);
            list = pair.cdr;
        }
        if (list != Empty) {
            out.write(32);
            out.write(". ");
            out.writeObject(checkNonList(list));
        }
        out.endElement();
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
    }

    public void writeExternal(ObjectOutput out) throws IOException {
    }

    public Object readResolve() throws ObjectStreamException {
        return Empty;
    }

    public static Pair list1(Object arg1) {
        Pair pair;
        new Pair(arg1, Empty);
        return pair;
    }

    public static Pair list2(Object arg1, Object arg2) {
        Pair pair;
        Object obj;
        new Pair(arg2, Empty);
        new Pair(arg1, obj);
        return pair;
    }

    public static Pair list3(Object arg1, Object arg2, Object arg3) {
        Pair pair;
        Object obj;
        Object obj2;
        new Pair(arg3, Empty);
        new Pair(arg2, obj2);
        new Pair(arg1, obj);
        return pair;
    }

    public static Pair list4(Object arg1, Object arg2, Object arg3, Object arg4) {
        Pair pair;
        Object obj;
        Object obj2;
        Object obj3;
        new Pair(arg4, Empty);
        new Pair(arg3, obj3);
        new Pair(arg2, obj2);
        new Pair(arg1, obj);
        return pair;
    }

    public static Pair chain1(Pair old, Object arg1) {
        Pair pair;
        new Pair(arg1, Empty);
        Pair p1 = pair;
        old.cdr = p1;
        return p1;
    }

    public static Pair chain4(Pair old, Object arg1, Object arg2, Object arg3, Object arg4) {
        Pair pair;
        Object obj;
        Object obj2;
        Object obj3;
        new Pair(arg4, Empty);
        Pair p4 = pair;
        new Pair(arg3, p4);
        new Pair(arg2, obj3);
        new Pair(arg1, obj2);
        old.cdr = obj;
        return p4;
    }

    public static LList reverseInPlace(Object obj) {
        Object list = obj;
        LList lList = Empty;
        while (true) {
            LList prev = lList;
            if (list == Empty) {
                return prev;
            }
            Pair pair = (Pair) list;
            list = pair.cdr;
            pair.cdr = prev;
            lList = pair;
        }
    }

    public static Object listTail(Object obj, int i) {
        Throwable th;
        Object list = obj;
        int count = i;
        while (true) {
            count--;
            if (count < 0) {
                return list;
            }
            if (!(list instanceof Pair)) {
                Throwable th2 = th;
                new IndexOutOfBoundsException("List is too short.");
                throw th2;
            }
            list = ((Pair) list).cdr;
        }
    }

    public static Object consX(Object[] objArr) {
        Pair pair;
        Pair pair2;
        Object[] args = objArr;
        Object first = args[0];
        int n = args.length - 1;
        if (n <= 0) {
            return first;
        }
        new Pair(first, (Object) null);
        Pair result = pair;
        Pair prev = result;
        for (int i = 1; i < n; i++) {
            new Pair(args[i], (Object) null);
            Pair next = pair2;
            prev.cdr = next;
            prev = next;
        }
        prev.cdr = args[n];
        return result;
    }

    public String toString() {
        StringBuffer stringBuffer;
        LList rest = this;
        int i = 0;
        new StringBuffer(100);
        StringBuffer sbuf = stringBuffer;
        StringBuffer append = sbuf.append('(');
        while (true) {
            if (rest == Empty) {
                break;
            }
            if (i > 0) {
                StringBuffer append2 = sbuf.append(' ');
            }
            if (i < 10) {
                if (!(rest instanceof Pair)) {
                    StringBuffer append3 = sbuf.append(". ");
                    StringBuffer append4 = sbuf.append(checkNonList(rest));
                    break;
                }
                Pair pair = (Pair) rest;
                StringBuffer append5 = sbuf.append(pair.car);
                rest = pair.cdr;
                i++;
            } else {
                StringBuffer append6 = sbuf.append("...");
                break;
            }
        }
        StringBuffer append7 = sbuf.append(')');
        return sbuf.toString();
    }

    public static Object checkNonList(Object obj) {
        Object rest = obj;
        return rest instanceof LList ? "#<not a pair>" : rest;
    }
}

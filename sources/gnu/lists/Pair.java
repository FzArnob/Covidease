package gnu.lists;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;

public class Pair extends LList implements Externalizable {
    protected Object car;
    protected Object cdr;

    public Pair(Object carval, Object cdrval) {
        this.car = carval;
        this.cdr = cdrval;
    }

    public Pair() {
    }

    public Object getCar() {
        return this.car;
    }

    public Object getCdr() {
        return this.cdr;
    }

    public void setCar(Object car2) {
        Object obj = car2;
        this.car = obj;
    }

    public void setCdr(Object cdr2) {
        Object obj = cdr2;
        this.cdr = obj;
    }

    public void setCdrBackdoor(Object cdr2) {
        Object obj = cdr2;
        this.cdr = obj;
    }

    public int size() {
        Throwable th;
        int n = listLength(this, true);
        if (n >= 0) {
            return n;
        }
        if (n == -1) {
            return Integer.MAX_VALUE;
        }
        Throwable th2 = th;
        new RuntimeException("not a true list");
        throw th2;
    }

    public boolean isEmpty() {
        return false;
    }

    public int length() {
        int n = 0;
        Pair slow = this;
        Pair pair = this;
        while (pair != Empty) {
            if (pair instanceof Pair) {
                Pair fast_pair = (Pair) pair;
                if (fast_pair.cdr == Empty) {
                    return n + 1;
                }
                if (pair == slow && n > 0) {
                    return -1;
                }
                if (!(fast_pair.cdr instanceof Pair)) {
                    n++;
                    pair = fast_pair.cdr;
                } else if (!(slow instanceof Pair)) {
                    return -2;
                } else {
                    slow = ((Pair) slow).cdr;
                    pair = ((Pair) fast_pair.cdr).cdr;
                    n += 2;
                }
            } else if (!(pair instanceof Sequence)) {
                return -2;
            } else {
                int j = ((Sequence) pair).size();
                return j >= 0 ? n + j : j;
            }
        }
        return n;
    }

    public boolean hasNext(int i) {
        int ipos = i;
        if (ipos > 0) {
            return PositionManager.getPositionObject(ipos).hasNext();
        }
        return ipos == 0;
    }

    public int nextPos(int i) {
        SeqPosition seqPosition;
        int ipos = i;
        if (ipos > 0) {
            return ((LListPosition) PositionManager.getPositionObject(ipos)).gotoNext() ? ipos : 0;
        } else if (ipos < 0) {
            return 0;
        } else {
            new LListPosition(this, 1, true);
            return PositionManager.manager.register(seqPosition);
        }
    }

    public Object getPosNext(int i) {
        int ipos = i;
        if (ipos > 0) {
            return PositionManager.getPositionObject(ipos).getNext();
        }
        return ipos == 0 ? this.car : eofValue;
    }

    public Object getPosPrevious(int i) {
        int ipos = i;
        if (ipos > 0) {
            return PositionManager.getPositionObject(ipos).getPrevious();
        }
        return ipos == 0 ? eofValue : lastPair().car;
    }

    public final Pair lastPair() {
        Pair pair = this;
        while (true) {
            Pair pair2 = pair;
            Object next = pair2.cdr;
            if (!(next instanceof Pair)) {
                return pair2;
            }
            pair = (Pair) next;
        }
    }

    public int hashCode() {
        Object obj;
        int hash = 1;
        Object obj2 = this;
        while (true) {
            obj = obj2;
            if (!(obj instanceof Pair)) {
                break;
            }
            Pair pair = (Pair) obj;
            Object obj3 = pair.car;
            hash = (31 * hash) + (obj3 == null ? 0 : obj3.hashCode());
            obj2 = pair.cdr;
        }
        if (!(obj == LList.Empty || obj == null)) {
            hash ^= obj.hashCode();
        }
        return hash;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean equals(gnu.lists.Pair r6, gnu.lists.Pair r7) {
        /*
            r0 = r6
            r1 = r7
            r4 = r0
            r5 = r1
            if (r4 != r5) goto L_0x0009
            r4 = 1
            r0 = r4
        L_0x0008:
            return r0
        L_0x0009:
            r4 = r0
            if (r4 == 0) goto L_0x000f
            r4 = r1
            if (r4 != 0) goto L_0x001a
        L_0x000f:
            r4 = 0
            r0 = r4
            goto L_0x0008
        L_0x0012:
            r4 = r2
            gnu.lists.Pair r4 = (gnu.lists.Pair) r4
            r0 = r4
            r4 = r3
            gnu.lists.Pair r4 = (gnu.lists.Pair) r4
            r1 = r4
        L_0x001a:
            r4 = r0
            java.lang.Object r4 = r4.car
            r2 = r4
            r4 = r1
            java.lang.Object r4 = r4.car
            r3 = r4
            r4 = r2
            r5 = r3
            if (r4 == r5) goto L_0x0034
            r4 = r2
            if (r4 == 0) goto L_0x0031
            r4 = r2
            r5 = r3
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L_0x0034
        L_0x0031:
            r4 = 0
            r0 = r4
            goto L_0x0008
        L_0x0034:
            r4 = r0
            java.lang.Object r4 = r4.cdr
            r2 = r4
            r4 = r1
            java.lang.Object r4 = r4.cdr
            r3 = r4
            r4 = r2
            r5 = r3
            if (r4 != r5) goto L_0x0043
            r4 = 1
            r0 = r4
            goto L_0x0008
        L_0x0043:
            r4 = r2
            if (r4 == 0) goto L_0x0049
            r4 = r3
            if (r4 != 0) goto L_0x004c
        L_0x0049:
            r4 = 0
            r0 = r4
            goto L_0x0008
        L_0x004c:
            r4 = r2
            boolean r4 = r4 instanceof gnu.lists.Pair
            if (r4 == 0) goto L_0x0056
            r4 = r3
            boolean r4 = r4 instanceof gnu.lists.Pair
            if (r4 != 0) goto L_0x0012
        L_0x0056:
            r4 = r2
            r5 = r3
            boolean r4 = r4.equals(r5)
            r0 = r4
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.lists.Pair.equals(gnu.lists.Pair, gnu.lists.Pair):boolean");
    }

    public static int compareTo(Pair pair, Pair pair2) {
        Object x1;
        Object x2;
        Pair pair1 = pair;
        Pair pair22 = pair2;
        if (pair1 == pair22) {
            return 0;
        }
        if (pair1 == null) {
            return -1;
        }
        if (pair22 == null) {
            return 1;
        }
        while (true) {
            int d = ((Comparable) pair1.car).compareTo((Comparable) pair22.car);
            if (d != 0) {
                return d;
            }
            x1 = pair1.cdr;
            x2 = pair22.cdr;
            if (x1 == x2) {
                return 0;
            }
            if (x1 == null) {
                return -1;
            }
            if (x2 == null) {
                return 1;
            }
            if ((x1 instanceof Pair) && (x2 instanceof Pair)) {
                pair1 = (Pair) x1;
                pair22 = (Pair) x2;
            }
        }
        return ((Comparable) x1).compareTo((Comparable) x2);
    }

    public int compareTo(Object obj) {
        Object obj2 = obj;
        if (obj2 == Empty) {
            return 1;
        }
        return compareTo(this, (Pair) obj2);
    }

    public Object get(int index) {
        Throwable th;
        Pair pair = this;
        int i = index;
        while (true) {
            if (i <= 0) {
                break;
            }
            i--;
            if (pair.cdr instanceof Pair) {
                pair = (Pair) pair.cdr;
            } else if (pair.cdr instanceof Sequence) {
                return ((Sequence) pair.cdr).get(i);
            }
        }
        if (i == 0) {
            return pair.car;
        }
        Throwable th2 = th;
        new IndexOutOfBoundsException();
        throw th2;
    }

    public boolean equals(Object obj) {
        Object obj2 = obj;
        if (obj2 == null || !(obj2 instanceof Pair)) {
            return false;
        }
        return equals(this, (Pair) obj2);
    }

    public static Pair make(Object car2, Object cdr2) {
        Pair pair;
        new Pair(car2, cdr2);
        return pair;
    }

    public Object[] toArray() {
        int len = size();
        Object[] arr = new Object[len];
        int i = 0;
        Sequence rest = this;
        while (i < len && (rest instanceof Pair)) {
            Pair pair = (Pair) rest;
            arr[i] = pair.car;
            rest = (Sequence) pair.cdr;
            i++;
        }
        int prefix = i;
        while (i < len) {
            arr[i] = rest.get(i - prefix);
            i++;
        }
        return arr;
    }

    public Object[] toArray(Object[] objArr) {
        Object[] arr = objArr;
        int alen = arr.length;
        int len = length();
        if (len > alen) {
            arr = new Object[len];
            alen = len;
        }
        int i = 0;
        Sequence rest = this;
        while (i < len && (rest instanceof Pair)) {
            Pair pair = (Pair) rest;
            arr[i] = pair.car;
            rest = (Sequence) pair.cdr;
            i++;
        }
        int prefix = i;
        while (i < len) {
            arr[i] = rest.get(i - prefix);
            i++;
        }
        if (len < alen) {
            arr[len] = null;
        }
        return arr;
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        ObjectOutput out = objectOutput;
        out.writeObject(this.car);
        out.writeObject(this.cdr);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        ObjectInput in = objectInput;
        this.car = in.readObject();
        this.cdr = in.readObject();
    }

    public Object readResolve() throws ObjectStreamException {
        return this;
    }
}

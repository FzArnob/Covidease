package gnu.mapping;

import gnu.lists.Consumer;
import gnu.lists.TreeList;
import gnu.text.Printable;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.util.List;

public class Values extends TreeList implements Printable, Externalizable {
    public static final Values empty;
    public static final Object[] noArgs = new Object[0];

    static {
        Values values;
        new Values(noArgs);
        empty = values;
    }

    public Values() {
    }

    public Values(Object[] objArr) {
        Object[] values = objArr;
        for (int i = 0; i < values.length; i++) {
            writeObject(values[i]);
        }
    }

    public Object[] getValues() {
        return isEmpty() ? noArgs : toArray();
    }

    public static Object values(Object... vals) {
        return make(vals);
    }

    public static Values make() {
        Values values;
        Values values2 = values;
        new Values();
        return values2;
    }

    public static Object make(Object[] objArr) {
        Object obj;
        Object[] vals = objArr;
        if (vals.length == 1) {
            return vals[0];
        }
        if (vals.length == 0) {
            return empty;
        }
        new Values(vals);
        return obj;
    }

    public static Object make(List list) {
        Values values;
        List<Object> seq = list;
        int count = seq == null ? 0 : seq.size();
        if (count == 0) {
            return empty;
        }
        if (count == 1) {
            return seq.get(0);
        }
        new Values();
        Values vals = values;
        for (Object writeObject : seq) {
            vals.writeObject(writeObject);
        }
        return vals;
    }

    public static Object make(TreeList treeList) {
        TreeList list = treeList;
        return make(list, 0, list.data.length);
    }

    public static Object make(TreeList treeList, int i, int i2) {
        TreeList treeList2;
        TreeList list = treeList;
        int startPosition = i;
        int endPosition = i2;
        if (startPosition != endPosition) {
            int nextDataIndex = list.nextDataIndex(startPosition);
            int next = nextDataIndex;
            if (nextDataIndex > 0) {
                if (next == endPosition || list.nextDataIndex(next) < 0) {
                    return list.getPosNext(startPosition << 1);
                }
                new Values();
                TreeList vals = treeList2;
                int consumeIRange = list.consumeIRange(startPosition, endPosition, vals);
                return vals;
            }
        }
        return empty;
    }

    public final Object canonicalize() {
        if (this.gapEnd == this.data.length) {
            if (this.gapStart == 0) {
                return empty;
            }
            if (nextDataIndex(0) == this.gapStart) {
                return getPosNext(0);
            }
        }
        return this;
    }

    public Object call_with(Procedure proc) throws Throwable {
        return proc.applyN(toArray());
    }

    public void print(Consumer consumer) {
        Consumer out = consumer;
        if (this == empty) {
            out.write("#!void");
            return;
        }
        int length = toArray().length;
        if (1 != 0) {
            out.write("#<values");
        }
        int i = 0;
        while (true) {
            int i2 = i;
            int next = nextDataIndex(i2);
            if (next < 0) {
                break;
            }
            out.write(32);
            if (i2 >= this.gapEnd) {
                i2 -= this.gapEnd - this.gapStart;
            }
            Object val = getPosNext(i2 << 1);
            if (val instanceof Printable) {
                ((Printable) val).print(out);
            } else {
                out.writeObject(val);
            }
            i = next;
        }
        if (1 != 0) {
            out.write(62);
        }
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        ObjectOutput out = objectOutput;
        Object[] vals = toArray();
        int len = vals.length;
        out.writeInt(len);
        for (int i = 0; i < len; i++) {
            out.writeObject(vals[i]);
        }
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        ObjectInput in = objectInput;
        int len = in.readInt();
        for (int i = 0; i < len; i++) {
            writeObject(in.readObject());
        }
    }

    public Object readResolve() throws ObjectStreamException {
        return isEmpty() ? empty : this;
    }

    public static int nextIndex(Object obj, int i) {
        Object values = obj;
        int curIndex = i;
        if (values instanceof Values) {
            return ((Values) values).nextDataIndex(curIndex);
        }
        return curIndex == 0 ? 1 : -1;
    }

    public static Object nextValue(Object obj, int i) {
        Object values = obj;
        int curIndex = i;
        if (!(values instanceof Values)) {
            return values;
        }
        Values v = (Values) values;
        if (curIndex >= v.gapEnd) {
            curIndex -= v.gapEnd - v.gapStart;
        }
        return ((Values) values).getPosNext(curIndex << 1);
    }

    public static void writeValues(Object obj, Consumer consumer) {
        Object value = obj;
        Consumer out = consumer;
        if (value instanceof Values) {
            ((Values) value).consume(out);
        } else {
            out.writeObject(value);
        }
    }

    public static int countValues(Object obj) {
        Object value = obj;
        return value instanceof Values ? ((Values) value).size() : 1;
    }
}

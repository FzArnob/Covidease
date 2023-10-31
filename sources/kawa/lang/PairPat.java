package kawa.lang;

import gnu.lists.Consumer;
import gnu.lists.Pair;
import gnu.text.Printable;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class PairPat extends Pattern implements Printable, Externalizable {
    Pattern car;
    private int car_count;
    Pattern cdr;
    private int cdr_count;

    public PairPat() {
    }

    public PairPat(Pattern pattern, Pattern pattern2) {
        Pattern car2 = pattern;
        Pattern cdr2 = pattern2;
        this.car = car2;
        this.cdr = cdr2;
        this.car_count = car2.varCount();
        this.cdr_count = cdr2.varCount();
    }

    public static PairPat make(Pattern car2, Pattern cdr2) {
        PairPat pairPat;
        new PairPat(car2, cdr2);
        return pairPat;
    }

    public boolean match(Object obj, Object[] objArr, int i) {
        Object obj2 = obj;
        Object[] vars = objArr;
        int start_vars = i;
        if (!(obj2 instanceof Pair)) {
            return false;
        }
        Pair pair = (Pair) obj2;
        return this.car.match(pair.getCar(), vars, start_vars) && this.cdr.match(pair.getCdr(), vars, start_vars + this.car_count);
    }

    public void print(Consumer consumer) {
        Consumer out = consumer;
        out.write("#<pair-pattern car: ");
        this.car.print(out);
        out.write(" cdr: ");
        this.cdr.print(out);
        out.write(62);
    }

    public int varCount() {
        return this.car_count + this.cdr_count;
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        ObjectOutput out = objectOutput;
        out.writeObject(this.car);
        out.writeObject(this.cdr);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        ObjectInput in = objectInput;
        this.car = (Pattern) in.readObject();
        this.cdr = (Pattern) in.readObject();
    }
}

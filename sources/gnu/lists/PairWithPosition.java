package gnu.lists;

import gnu.text.SourceLocator;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class PairWithPosition extends ImmutablePair implements SourceLocator {
    String filename;
    int position;

    public final void setFile(String filename2) {
        String str = filename2;
        this.filename = str;
    }

    public final void setLine(int i, int i2) {
        int lineno = i;
        int colno = i2;
        if (lineno < 0) {
            lineno = 0;
        }
        if (colno < 0) {
            colno = 0;
        }
        this.position = (lineno << 12) + colno;
    }

    public final void setLine(int lineno) {
        setLine(lineno, 0);
    }

    public final String getFileName() {
        return this.filename;
    }

    public String getPublicId() {
        return null;
    }

    public String getSystemId() {
        return this.filename;
    }

    public final int getLineNumber() {
        int line = this.position >> 12;
        return line == 0 ? -1 : line;
    }

    public final int getColumnNumber() {
        int column = this.position & 4095;
        return column == 0 ? -1 : column;
    }

    public boolean isStableSourceLocation() {
        return true;
    }

    public PairWithPosition() {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PairWithPosition(SourceLocator sourceLocator, Object car, Object cdr) {
        super(car, cdr);
        SourceLocator where = sourceLocator;
        this.filename = where.getFileName();
        setLine(where.getLineNumber(), where.getColumnNumber());
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PairWithPosition(Object car, Object cdr) {
        super(car, cdr);
    }

    public static PairWithPosition make(Object car, Object cdr, String filename2, int line, int column) {
        PairWithPosition pairWithPosition;
        new PairWithPosition(car, cdr);
        PairWithPosition pair = pairWithPosition;
        pair.filename = filename2;
        pair.setLine(line, column);
        return pair;
    }

    public static PairWithPosition make(Object car, Object cdr, String filename2, int position2) {
        PairWithPosition pairWithPosition;
        new PairWithPosition(car, cdr);
        PairWithPosition pair = pairWithPosition;
        pair.filename = filename2;
        pair.position = position2;
        return pair;
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        ObjectOutput out = objectOutput;
        out.writeObject(this.car);
        out.writeObject(this.cdr);
        out.writeObject(this.filename);
        out.writeInt(this.position);
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        ObjectInput in = objectInput;
        this.car = in.readObject();
        this.cdr = in.readObject();
        this.filename = (String) in.readObject();
        this.position = in.readInt();
    }
}

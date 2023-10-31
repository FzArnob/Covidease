package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class LineNumbersAttr extends Attribute {
    int linenumber_count;
    short[] linenumber_table;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LineNumbersAttr(CodeAttr codeAttr) {
        super("LineNumberTable");
        CodeAttr code = codeAttr;
        addToFrontOf(code);
        code.lines = this;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public LineNumbersAttr(short[] sArr, CodeAttr code) {
        this(code);
        short[] numbers = sArr;
        this.linenumber_table = numbers;
        this.linenumber_count = numbers.length >> 1;
    }

    public void put(int i, int i2) {
        int linenumber = i;
        int PC = i2;
        if (this.linenumber_table == null) {
            this.linenumber_table = new short[32];
        } else if (2 * this.linenumber_count >= this.linenumber_table.length) {
            short[] new_linenumbers = new short[(2 * this.linenumber_table.length)];
            System.arraycopy(this.linenumber_table, 0, new_linenumbers, 0, 2 * this.linenumber_count);
            this.linenumber_table = new_linenumbers;
        }
        this.linenumber_table[2 * this.linenumber_count] = (short) PC;
        this.linenumber_table[(2 * this.linenumber_count) + 1] = (short) linenumber;
        this.linenumber_count++;
    }

    public final int getLength() {
        return 2 + (4 * this.linenumber_count);
    }

    public int getLineCount() {
        return this.linenumber_count;
    }

    public short[] getLineNumberTable() {
        return this.linenumber_table;
    }

    public void write(DataOutputStream dataOutputStream) throws IOException {
        DataOutputStream dstr = dataOutputStream;
        dstr.writeShort(this.linenumber_count);
        int count = 2 * this.linenumber_count;
        for (int i = 0; i < count; i++) {
            dstr.writeShort(this.linenumber_table[i]);
        }
    }

    public void print(ClassTypeWriter classTypeWriter) {
        ClassTypeWriter dst = classTypeWriter;
        dst.print("Attribute \"");
        dst.print(getName());
        dst.print("\", length:");
        dst.print(getLength());
        dst.print(", count: ");
        dst.println(this.linenumber_count);
        for (int i = 0; i < this.linenumber_count; i++) {
            dst.print("  line: ");
            dst.print(this.linenumber_table[(2 * i) + 1] & 65535);
            dst.print(" at pc: ");
            dst.println(this.linenumber_table[2 * i] & 65535);
        }
    }
}

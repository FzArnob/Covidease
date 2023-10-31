package gnu.kawa.lispexpr;

public class ReaderMisc extends ReadTableEntry {
    int kind;

    public ReaderMisc(int kind2) {
        this.kind = kind2;
    }

    public int getKind() {
        return this.kind;
    }
}

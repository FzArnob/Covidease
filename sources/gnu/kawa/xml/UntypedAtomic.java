package gnu.kawa.xml;

public class UntypedAtomic {
    String text;

    public String toString() {
        return this.text;
    }

    public UntypedAtomic(String text2) {
        this.text = text2;
    }

    public int hashCode() {
        return this.text.hashCode();
    }

    public boolean equals(Object obj) {
        Object arg = obj;
        return (arg instanceof UntypedAtomic) && this.text.equals(((UntypedAtomic) arg).text);
    }
}

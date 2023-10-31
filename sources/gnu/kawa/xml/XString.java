package gnu.kawa.xml;

public class XString implements CharSequence {
    public String text;
    private XStringType type;

    public XStringType getStringType() {
        return this.type;
    }

    public char charAt(int index) {
        return this.text.charAt(index);
    }

    public int length() {
        return this.text.length();
    }

    public CharSequence subSequence(int start, int end) {
        return this.text.substring(start, end);
    }

    public String toString() {
        return this.text;
    }

    XString(String text2, XStringType type2) {
        this.text = text2;
        this.type = type2;
    }
}

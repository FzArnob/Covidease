package gnu.bytecode;

public class Access {
    public static final short ABSTRACT = 1024;
    public static final short ANNOTATION = 8192;
    public static final short BRIDGE = 64;
    public static final char CLASS_CONTEXT = 'C';
    public static final short CLASS_MODIFIERS = 30257;
    public static final short ENUM = 16384;
    public static final char FIELD_CONTEXT = 'F';
    public static final short FIELD_MODIFIERS = 20703;
    public static final short FINAL = 16;
    public static final char INNERCLASS_CONTEXT = 'I';
    public static final short INNERCLASS_MODIFIERS = 30239;
    public static final short INTERFACE = 512;
    public static final char METHOD_CONTEXT = 'M';
    public static final short METHOD_MODIFIERS = 7679;
    public static final short NATIVE = 256;
    public static final short PRIVATE = 2;
    public static final short PROTECTED = 4;
    public static final short PUBLIC = 1;
    public static final short STATIC = 8;
    public static final short STRICT = 2048;
    public static final short SUPER = 32;
    public static final short SYNCHRONIZED = 32;
    public static final short SYNTHETIC = 4096;
    public static final short TRANSIENT = 128;
    public static final short VARARGS = 128;
    public static final short VOLATILE = 64;

    public Access() {
    }

    public static String toString(int flags) {
        return toString(flags, 0);
    }

    public static String toString(int i, char c) {
        int i2;
        StringBuffer stringBuffer;
        int flags = i;
        char kind = c;
        if (kind == 'C') {
            i2 = 30257;
        } else {
            i2 = kind == 'I' ? 30239 : kind == 'F' ? 20703 : kind == 'M' ? 7679 : 32767;
        }
        int mask = i2;
        short bad_flags = (short) (flags & (mask ^ -1));
        int flags2 = flags & mask;
        new StringBuffer();
        StringBuffer buf = stringBuffer;
        if ((flags2 & 1) != 0) {
            StringBuffer append = buf.append(" public");
        }
        if ((flags2 & 2) != 0) {
            StringBuffer append2 = buf.append(" private");
        }
        if ((flags2 & 4) != 0) {
            StringBuffer append3 = buf.append(" protected");
        }
        if ((flags2 & 8) != 0) {
            StringBuffer append4 = buf.append(" static");
        }
        if ((flags2 & 16) != 0) {
            StringBuffer append5 = buf.append(" final");
        }
        if ((flags2 & 32) != 0) {
            StringBuffer append6 = buf.append(kind == 'C' ? " super" : " synchronized");
        }
        if ((flags2 & 64) != 0) {
            StringBuffer append7 = buf.append(kind == 'M' ? " bridge" : " volatile");
        }
        if ((flags2 & 128) != 0) {
            StringBuffer append8 = buf.append(kind == 'M' ? " varargs" : " transient");
        }
        if ((flags2 & 256) != 0) {
            StringBuffer append9 = buf.append(" native");
        }
        if ((flags2 & 512) != 0) {
            StringBuffer append10 = buf.append(" interface");
        }
        if ((flags2 & 1024) != 0) {
            StringBuffer append11 = buf.append(" abstract");
        }
        if ((flags2 & 2048) != 0) {
            StringBuffer append12 = buf.append(" strict");
        }
        if ((flags2 & 16384) != 0) {
            StringBuffer append13 = buf.append(" enum");
        }
        if ((flags2 & 4096) != 0) {
            StringBuffer append14 = buf.append(" synthetic");
        }
        if ((flags2 & 8192) != 0) {
            StringBuffer append15 = buf.append(" annotation");
        }
        if (bad_flags != 0) {
            StringBuffer append16 = buf.append(" unknown-flags:0x");
            StringBuffer append17 = buf.append(Integer.toHexString(bad_flags));
        }
        return buf.toString();
    }
}

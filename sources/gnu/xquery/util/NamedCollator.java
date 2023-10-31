package gnu.xquery.util;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.text.CollationKey;
import java.text.Collator;

public class NamedCollator extends Collator implements Externalizable {
    public static final String UNICODE_CODEPOINT_COLLATION = "http://www.w3.org/2005/xpath-functions/collation/codepoint";
    public static final NamedCollator codepointCollation;
    Collator collator;
    String name;

    public NamedCollator() {
    }

    public static NamedCollator make(String name2) {
        NamedCollator namedCollator;
        new NamedCollator();
        NamedCollator coll = namedCollator;
        coll.name = name2;
        coll.resolve();
        return coll;
    }

    public String getName() {
        return this.name;
    }

    public static NamedCollator find(String name2) {
        return make(name2);
    }

    static {
        NamedCollator namedCollator;
        new NamedCollator();
        codepointCollation = namedCollator;
        codepointCollation.name = UNICODE_CODEPOINT_COLLATION;
    }

    public void resolve() {
        Throwable th;
        StringBuilder sb;
        if (this.name != null && !this.name.equals(UNICODE_CODEPOINT_COLLATION)) {
            Throwable th2 = th;
            new StringBuilder();
            new RuntimeException(sb.append("unknown collation: ").append(this.name).toString());
            throw th2;
        }
    }

    public static int codepointCompare(String str, String str2) {
        int i;
        String str1 = str;
        String str22 = str2;
        int i1 = 0;
        int i2 = 0;
        int len1 = str1.length();
        int len2 = str22.length();
        while (i1 != len1) {
            if (i2 == len2) {
                return 1;
            }
            int i3 = i1;
            i1++;
            int c1 = str1.charAt(i3);
            if (c1 >= 55296 && c1 < 56320 && i1 < len1) {
                int i4 = i1;
                i1++;
                c1 = ((c1 - 55296) * 1024) + (str1.charAt(i4) - 56320) + 65536;
            }
            int i5 = i2;
            i2++;
            int c2 = str22.charAt(i5);
            if (c2 >= 55296 && c2 < 56320 && i2 < len2) {
                int i6 = i2;
                i2++;
                c2 = ((c2 - 55296) * 1024) + (str22.charAt(i6) - 56320) + 65536;
            }
            if (c1 != c2) {
                return c1 < c2 ? -1 : 1;
            }
        }
        if (i2 == len2) {
            i = 0;
        } else {
            i = -1;
        }
        return i;
    }

    public int compare(String str, String str2) {
        String str1 = str;
        String str22 = str2;
        if (this.collator != null) {
            return this.collator.compare(str1, str22);
        }
        return codepointCompare(str1, str22);
    }

    public CollationKey getCollationKey(String source) {
        return this.collator.getCollationKey(source);
    }

    public int hashCode() {
        if (this.collator != null) {
            return this.collator.hashCode();
        }
        return 0;
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(this.name);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.name = in.readUTF();
        resolve();
    }
}

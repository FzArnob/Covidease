package gnu.xml;

import gnu.mapping.Symbol;

/* compiled from: XMLFilter */
final class MappingInfo {
    int index = -1;
    String local;
    NamespaceBinding namespaces;
    MappingInfo nextInBucket;
    String prefix;
    Symbol qname;
    int tagHash;
    XName type;
    String uri;

    MappingInfo() {
    }

    static int hash(String str, String local2) {
        String prefix2 = str;
        int hash = local2.hashCode();
        if (prefix2 != null) {
            hash ^= prefix2.hashCode();
        }
        return hash;
    }

    static int hash(char[] cArr, int i, int i2) {
        int i3;
        char[] data = cArr;
        int start = i;
        int length = i2;
        int hash = 0;
        int prefixHash = 0;
        int colonPos = -1;
        for (int i4 = 0; i4 < length; i4++) {
            char ch = data[start + i4];
            if (ch != ':' || colonPos >= 0) {
                i3 = (31 * hash) + ch;
            } else {
                colonPos = i4;
                prefixHash = hash;
                i3 = 0;
            }
            hash = i3;
        }
        return prefixHash ^ hash;
    }

    /* access modifiers changed from: package-private */
    public boolean match(char[] cArr, int i, int i2) {
        char[] data = cArr;
        int start = i;
        int length = i2;
        if (this.prefix == null) {
            return equals(this.local, data, start, length);
        }
        int localLength = this.local.length();
        int prefixLength = this.prefix.length();
        return length == (prefixLength + 1) + localLength && data[prefixLength] == ':' && equals(this.prefix, data, start, prefixLength) && equals(this.local, data, (start + prefixLength) + 1, localLength);
    }

    static boolean equals(String str, StringBuffer stringBuffer) {
        String tag = str;
        StringBuffer sbuf = stringBuffer;
        int length = sbuf.length();
        if (tag.length() != length) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (sbuf.charAt(i) != tag.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    static boolean equals(String str, char[] cArr, int i, int i2) {
        String tag = str;
        char[] data = cArr;
        int start = i;
        int length = i2;
        if (tag.length() != length) {
            return false;
        }
        for (int i3 = 0; i3 < length; i3++) {
            if (data[start + i3] != tag.charAt(i3)) {
                return false;
            }
        }
        return true;
    }
}

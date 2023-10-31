package com.shaded.fasterxml.jackson.core.sym;

import com.shaded.fasterxml.jackson.core.util.InternCache;
import java.util.Arrays;

public final class CharsToNameCanonicalizer {
    protected static final int DEFAULT_TABLE_SIZE = 64;
    public static final int HASH_MULT = 33;
    static final int MAX_COLL_CHAIN_FOR_REUSE = 63;
    static final int MAX_COLL_CHAIN_LENGTH = 255;
    static final int MAX_ENTRIES_FOR_REUSE = 12000;
    protected static final int MAX_TABLE_SIZE = 65536;
    static final CharsToNameCanonicalizer sBootstrapSymbolTable;
    protected Bucket[] _buckets;
    protected final boolean _canonicalize;
    protected boolean _dirty;
    private final int _hashSeed;
    protected int _indexMask;
    protected final boolean _intern;
    protected int _longestCollisionList;
    protected CharsToNameCanonicalizer _parent;
    protected int _size;
    protected int _sizeThreshold;
    protected String[] _symbols;

    static {
        CharsToNameCanonicalizer charsToNameCanonicalizer;
        new CharsToNameCanonicalizer();
        sBootstrapSymbolTable = charsToNameCanonicalizer;
    }

    public static CharsToNameCanonicalizer createRoot() {
        long currentTimeMillis = System.currentTimeMillis();
        return createRoot((((int) currentTimeMillis) + ((int) (currentTimeMillis >>> 32))) | 1);
    }

    protected static CharsToNameCanonicalizer createRoot(int i) {
        return sBootstrapSymbolTable.makeOrphan(i);
    }

    private CharsToNameCanonicalizer() {
        this._canonicalize = true;
        this._intern = true;
        this._dirty = true;
        this._hashSeed = 0;
        this._longestCollisionList = 0;
        initTables(64);
    }

    private void initTables(int i) {
        int i2 = i;
        this._symbols = new String[i2];
        this._buckets = new Bucket[(i2 >> 1)];
        this._indexMask = i2 - 1;
        this._size = 0;
        this._longestCollisionList = 0;
        this._sizeThreshold = _thresholdSize(i2);
    }

    private static int _thresholdSize(int i) {
        int i2 = i;
        return i2 - (i2 >> 2);
    }

    private CharsToNameCanonicalizer(CharsToNameCanonicalizer charsToNameCanonicalizer, boolean z, boolean z2, String[] strArr, Bucket[] bucketArr, int i, int i2, int i3) {
        String[] strArr2 = strArr;
        this._parent = charsToNameCanonicalizer;
        this._canonicalize = z;
        this._intern = z2;
        this._symbols = strArr2;
        this._buckets = bucketArr;
        this._size = i;
        this._hashSeed = i2;
        int length = strArr2.length;
        this._sizeThreshold = _thresholdSize(length);
        this._indexMask = length - 1;
        this._longestCollisionList = i3;
        this._dirty = false;
    }

    /* JADX INFO: finally extract failed */
    public CharsToNameCanonicalizer makeChild(boolean z, boolean z2) {
        CharsToNameCanonicalizer charsToNameCanonicalizer;
        boolean z3 = z;
        boolean z4 = z2;
        synchronized (this) {
            try {
                String[] strArr = this._symbols;
                Bucket[] bucketArr = this._buckets;
                int i = this._size;
                int i2 = this._hashSeed;
                int i3 = this._longestCollisionList;
                new CharsToNameCanonicalizer(this, z3, z4, strArr, bucketArr, i, i2, i3);
                return charsToNameCanonicalizer;
            } catch (Throwable th) {
                while (true) {
                    Throwable th2 = th;
                    throw th2;
                }
            }
        }
    }

    private CharsToNameCanonicalizer makeOrphan(int i) {
        CharsToNameCanonicalizer charsToNameCanonicalizer;
        new CharsToNameCanonicalizer((CharsToNameCanonicalizer) null, true, true, this._symbols, this._buckets, this._size, i, this._longestCollisionList);
        return charsToNameCanonicalizer;
    }

    /* JADX INFO: finally extract failed */
    private void mergeChild(CharsToNameCanonicalizer charsToNameCanonicalizer) {
        CharsToNameCanonicalizer charsToNameCanonicalizer2 = charsToNameCanonicalizer;
        if (charsToNameCanonicalizer2.size() > MAX_ENTRIES_FOR_REUSE || charsToNameCanonicalizer2._longestCollisionList > 63) {
            synchronized (this) {
                try {
                    initTables(64);
                    this._dirty = false;
                } catch (Throwable th) {
                    Throwable th2 = th;
                    throw th2;
                }
            }
        } else if (charsToNameCanonicalizer2.size() > size()) {
            synchronized (this) {
                try {
                    this._symbols = charsToNameCanonicalizer2._symbols;
                    this._buckets = charsToNameCanonicalizer2._buckets;
                    this._size = charsToNameCanonicalizer2._size;
                    this._sizeThreshold = charsToNameCanonicalizer2._sizeThreshold;
                    this._indexMask = charsToNameCanonicalizer2._indexMask;
                    this._longestCollisionList = charsToNameCanonicalizer2._longestCollisionList;
                    this._dirty = false;
                } catch (Throwable th3) {
                    Throwable th4 = th3;
                    throw th4;
                }
            }
        }
    }

    public void release() {
        if (maybeDirty() && this._parent != null) {
            this._parent.mergeChild(this);
            this._dirty = false;
        }
    }

    public int size() {
        return this._size;
    }

    public int bucketCount() {
        return this._symbols.length;
    }

    public boolean maybeDirty() {
        return this._dirty;
    }

    public int hashSeed() {
        return this._hashSeed;
    }

    public int collisionCount() {
        int i = 0;
        Bucket[] bucketArr = this._buckets;
        int length = bucketArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            Bucket bucket = bucketArr[i2];
            if (bucket != null) {
                i += bucket.length();
            }
        }
        return i;
    }

    public int maxCollisionLength() {
        return this._longestCollisionList;
    }

    public String findSymbol(char[] cArr, int i, int i2, int i3) {
        String str;
        Bucket bucket;
        String find;
        String str2;
        char[] cArr2 = cArr;
        int i4 = i;
        int i5 = i2;
        int i6 = i3;
        if (i5 < 1) {
            return "";
        }
        if (!this._canonicalize) {
            new String(cArr2, i4, i5);
            return str2;
        }
        int _hashToIndex = _hashToIndex(i6);
        String str3 = this._symbols[_hashToIndex];
        if (str3 != null) {
            if (str3.length() == i5) {
                int i7 = 0;
                while (str3.charAt(i7) == cArr2[i4 + i7]) {
                    i7++;
                    if (i7 >= i5) {
                        break;
                    }
                }
                if (i7 == i5) {
                    return str3;
                }
            }
            Bucket bucket2 = this._buckets[_hashToIndex >> 1];
            if (!(bucket2 == null || (find = bucket2.find(cArr2, i4, i5)) == null)) {
                return find;
            }
        }
        if (!this._dirty) {
            copyArrays();
            this._dirty = true;
        } else if (this._size >= this._sizeThreshold) {
            rehash();
            _hashToIndex = _hashToIndex(calcHash(cArr2, i4, i5));
        }
        new String(cArr2, i4, i5);
        String str4 = str;
        if (this._intern) {
            str4 = InternCache.instance.intern(str4);
        }
        this._size++;
        if (this._symbols[_hashToIndex] == null) {
            this._symbols[_hashToIndex] = str4;
        } else {
            int i8 = _hashToIndex >> 1;
            new Bucket(str4, this._buckets[i8]);
            Bucket bucket3 = bucket;
            this._buckets[i8] = bucket3;
            this._longestCollisionList = Math.max(bucket3.length(), this._longestCollisionList);
            if (this._longestCollisionList > 255) {
                reportTooManyCollisions(255);
            }
        }
        return str4;
    }

    public int _hashToIndex(int i) {
        int i2 = i;
        return (i2 + (i2 >>> 15)) & this._indexMask;
    }

    public int calcHash(char[] cArr, int i, int i2) {
        char[] cArr2 = cArr;
        int i3 = i;
        int i4 = i2;
        int i5 = this._hashSeed;
        for (int i6 = 0; i6 < i4; i6++) {
            i5 = (i5 * 33) + cArr2[i6];
        }
        return i5 == 0 ? 1 : i5;
    }

    public int calcHash(String str) {
        String str2 = str;
        int length = str2.length();
        int i = this._hashSeed;
        for (int i2 = 0; i2 < length; i2++) {
            i = (i * 33) + str2.charAt(i2);
        }
        return i == 0 ? 1 : i;
    }

    private void copyArrays() {
        String[] strArr = this._symbols;
        int length = strArr.length;
        this._symbols = new String[length];
        System.arraycopy(strArr, 0, this._symbols, 0, length);
        Bucket[] bucketArr = this._buckets;
        int length2 = bucketArr.length;
        this._buckets = new Bucket[length2];
        System.arraycopy(bucketArr, 0, this._buckets, 0, length2);
    }

    private void rehash() {
        Throwable th;
        StringBuilder sb;
        Bucket bucket;
        Bucket bucket2;
        int length = this._symbols.length;
        int i = length + length;
        if (i > 65536) {
            this._size = 0;
            Arrays.fill(this._symbols, (Object) null);
            Arrays.fill(this._buckets, (Object) null);
            this._dirty = true;
            return;
        }
        String[] strArr = this._symbols;
        Bucket[] bucketArr = this._buckets;
        this._symbols = new String[i];
        this._buckets = new Bucket[(i >> 1)];
        this._indexMask = i - 1;
        this._sizeThreshold = _thresholdSize(i);
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            String str = strArr[i4];
            if (str != null) {
                i2++;
                int _hashToIndex = _hashToIndex(calcHash(str));
                if (this._symbols[_hashToIndex] == null) {
                    this._symbols[_hashToIndex] = str;
                } else {
                    int i5 = _hashToIndex >> 1;
                    new Bucket(str, this._buckets[i5]);
                    Bucket bucket3 = bucket2;
                    this._buckets[i5] = bucket3;
                    i3 = Math.max(i3, bucket3.length());
                }
            }
        }
        int i6 = length >> 1;
        for (int i7 = 0; i7 < i6; i7++) {
            Bucket bucket4 = bucketArr[i7];
            while (true) {
                Bucket bucket5 = bucket4;
                if (bucket5 == null) {
                    break;
                }
                i2++;
                String symbol = bucket5.getSymbol();
                int _hashToIndex2 = _hashToIndex(calcHash(symbol));
                if (this._symbols[_hashToIndex2] == null) {
                    this._symbols[_hashToIndex2] = symbol;
                } else {
                    int i8 = _hashToIndex2 >> 1;
                    new Bucket(symbol, this._buckets[i8]);
                    Bucket bucket6 = bucket;
                    this._buckets[i8] = bucket6;
                    i3 = Math.max(i3, bucket6.length());
                }
                bucket4 = bucket5.getNext();
            }
        }
        this._longestCollisionList = i3;
        if (i2 != this._size) {
            Throwable th2 = th;
            new StringBuilder();
            new Error(sb.append("Internal error on SymbolTable.rehash(): had ").append(this._size).append(" entries; now have ").append(i2).append(".").toString());
            throw th2;
        }
    }

    /* access modifiers changed from: protected */
    public void reportTooManyCollisions(int i) {
        Throwable th;
        StringBuilder sb;
        Throwable th2 = th;
        new StringBuilder();
        new IllegalStateException(sb.append("Longest collision chain in symbol table (of size ").append(this._size).append(") now exceeds maximum, ").append(i).append(" -- suspect a DoS attack based on hash collisions").toString());
        throw th2;
    }

    static final class Bucket {
        private final int _length;
        private final Bucket _next;
        private final String _symbol;

        public Bucket(String str, Bucket bucket) {
            Bucket bucket2 = bucket;
            this._symbol = str;
            this._next = bucket2;
            this._length = bucket2 == null ? 1 : bucket2._length + 1;
        }

        public String getSymbol() {
            return this._symbol;
        }

        public Bucket getNext() {
            return this._next;
        }

        public int length() {
            return this._length;
        }

        public String find(char[] cArr, int i, int i2) {
            char[] cArr2 = cArr;
            int i3 = i;
            int i4 = i2;
            String str = this._symbol;
            Bucket bucket = this._next;
            while (true) {
                Bucket bucket2 = bucket;
                if (str.length() == i4) {
                    int i5 = 0;
                    while (str.charAt(i5) == cArr2[i3 + i5]) {
                        i5++;
                        if (i5 >= i4) {
                            break;
                        }
                    }
                    if (i5 == i4) {
                        return str;
                    }
                }
                if (bucket2 == null) {
                    return null;
                }
                str = bucket2.getSymbol();
                bucket = bucket2.getNext();
            }
        }
    }
}

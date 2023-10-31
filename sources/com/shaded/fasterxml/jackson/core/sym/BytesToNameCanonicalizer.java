package com.shaded.fasterxml.jackson.core.sym;

import android.support.p000v4.view.InputDeviceCompat;
import com.google.appinventor.components.runtime.util.Ev3Constants;
import com.shaded.fasterxml.jackson.core.util.InternCache;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public final class BytesToNameCanonicalizer {
    protected static final int DEFAULT_TABLE_SIZE = 64;
    static final int INITIAL_COLLISION_LEN = 32;
    static final int LAST_VALID_BUCKET = 254;
    static final int MAX_COLL_CHAIN_FOR_REUSE = 63;
    static final int MAX_COLL_CHAIN_LENGTH = 255;
    static final int MAX_ENTRIES_FOR_REUSE = 6000;
    protected static final int MAX_TABLE_SIZE = 65536;
    static final int MIN_HASH_SIZE = 16;
    private static final int MULT = 33;
    private static final int MULT2 = 65599;
    private static final int MULT3 = 31;
    protected int _collCount;
    protected int _collEnd;
    protected Bucket[] _collList;
    private boolean _collListShared;
    protected int _count;
    private final int _hashSeed;
    protected final boolean _intern;
    protected int _longestCollisionList;
    protected int[] _mainHash;
    protected int _mainHashMask;
    private boolean _mainHashShared;
    protected Name[] _mainNames;
    private boolean _mainNamesShared;
    private transient boolean _needRehash;
    protected final BytesToNameCanonicalizer _parent;
    protected final AtomicReference<TableInfo> _tableInfo;

    private BytesToNameCanonicalizer(int i, boolean z, int i2) {
        int i3;
        AtomicReference<TableInfo> atomicReference;
        int i4 = i;
        this._parent = null;
        this._hashSeed = i2;
        this._intern = z;
        if (i4 < 16) {
            i4 = 16;
        } else if ((i4 & (i4 - 1)) != 0) {
            int i5 = 16;
            while (true) {
                i3 = i5;
                if (i3 >= i4) {
                    break;
                }
                i5 = i3 + i3;
            }
            i4 = i3;
        }
        new AtomicReference<>(initTableInfo(i4));
        this._tableInfo = atomicReference;
    }

    private BytesToNameCanonicalizer(BytesToNameCanonicalizer bytesToNameCanonicalizer, boolean z, int i, TableInfo tableInfo) {
        TableInfo tableInfo2 = tableInfo;
        this._parent = bytesToNameCanonicalizer;
        this._hashSeed = i;
        this._intern = z;
        this._tableInfo = null;
        this._count = tableInfo2.count;
        this._mainHashMask = tableInfo2.mainHashMask;
        this._mainHash = tableInfo2.mainHash;
        this._mainNames = tableInfo2.mainNames;
        this._collList = tableInfo2.collList;
        this._collCount = tableInfo2.collCount;
        this._collEnd = tableInfo2.collEnd;
        this._longestCollisionList = tableInfo2.longestCollisionList;
        this._needRehash = false;
        this._mainHashShared = true;
        this._mainNamesShared = true;
        this._collListShared = true;
    }

    private TableInfo initTableInfo(int i) {
        TableInfo tableInfo;
        int i2 = i;
        new TableInfo(0, i2 - 1, new int[i2], new Name[i2], (Bucket[]) null, 0, 0, 0);
        return tableInfo;
    }

    public static BytesToNameCanonicalizer createRoot() {
        long currentTimeMillis = System.currentTimeMillis();
        return createRoot((((int) currentTimeMillis) + ((int) (currentTimeMillis >>> 32))) | 1);
    }

    protected static BytesToNameCanonicalizer createRoot(int i) {
        BytesToNameCanonicalizer bytesToNameCanonicalizer;
        new BytesToNameCanonicalizer(64, true, i);
        return bytesToNameCanonicalizer;
    }

    public BytesToNameCanonicalizer makeChild(boolean z, boolean z2) {
        BytesToNameCanonicalizer bytesToNameCanonicalizer;
        boolean z3 = z;
        new BytesToNameCanonicalizer(this, z2, this._hashSeed, this._tableInfo.get());
        return bytesToNameCanonicalizer;
    }

    public void release() {
        TableInfo tableInfo;
        if (this._parent != null && maybeDirty()) {
            new TableInfo(this);
            this._parent.mergeChild(tableInfo);
            this._mainHashShared = true;
            this._mainNamesShared = true;
            this._collListShared = true;
        }
    }

    private void mergeChild(TableInfo tableInfo) {
        TableInfo tableInfo2 = tableInfo;
        int i = tableInfo2.count;
        TableInfo tableInfo3 = this._tableInfo.get();
        if (i > tableInfo3.count) {
            if (i > MAX_ENTRIES_FOR_REUSE || tableInfo2.longestCollisionList > 63) {
                tableInfo2 = initTableInfo(64);
            }
            boolean compareAndSet = this._tableInfo.compareAndSet(tableInfo3, tableInfo2);
        }
    }

    public int size() {
        if (this._tableInfo != null) {
            return this._tableInfo.get().count;
        }
        return this._count;
    }

    public int bucketCount() {
        return this._mainHash.length;
    }

    public boolean maybeDirty() {
        return !this._mainHashShared;
    }

    public int hashSeed() {
        return this._hashSeed;
    }

    public int collisionCount() {
        return this._collCount;
    }

    public int maxCollisionLength() {
        return this._longestCollisionList;
    }

    public static Name getEmptyName() {
        return Name1.getEmptyName();
    }

    public Name findName(int i) {
        Bucket bucket;
        int i2 = i;
        int calcHash = calcHash(i2);
        int i3 = calcHash & this._mainHashMask;
        int i4 = this._mainHash[i3];
        if ((((i4 >> 8) ^ calcHash) << 8) == 0) {
            Name name = this._mainNames[i3];
            if (name == null) {
                return null;
            }
            if (name.equals(i2)) {
                return name;
            }
        } else if (i4 == 0) {
            return null;
        }
        int i5 = i4 & 255;
        if (i5 <= 0 || (bucket = this._collList[i5 - 1]) == null) {
            return null;
        }
        return bucket.find(calcHash, i2, 0);
    }

    public Name findName(int i, int i2) {
        int calcHash;
        Bucket bucket;
        int i3 = i;
        int i4 = i2;
        if (i4 == 0) {
            calcHash = calcHash(i3);
        } else {
            calcHash = calcHash(i3, i4);
        }
        int i5 = calcHash;
        int i6 = i5 & this._mainHashMask;
        int i7 = this._mainHash[i6];
        if ((((i7 >> 8) ^ i5) << 8) == 0) {
            Name name = this._mainNames[i6];
            if (name == null) {
                return null;
            }
            if (name.equals(i3, i4)) {
                return name;
            }
        } else if (i7 == 0) {
            return null;
        }
        int i8 = i7 & 255;
        if (i8 <= 0 || (bucket = this._collList[i8 - 1]) == null) {
            return null;
        }
        return bucket.find(i5, i3, i4);
    }

    public Name findName(int[] iArr, int i) {
        Bucket bucket;
        int i2;
        int[] iArr2 = iArr;
        int i3 = i;
        if (i3 < 3) {
            int i4 = iArr2[0];
            if (i3 < 2) {
                i2 = 0;
            } else {
                i2 = iArr2[1];
            }
            return findName(i4, i2);
        }
        int calcHash = calcHash(iArr2, i3);
        int i5 = calcHash & this._mainHashMask;
        int i6 = this._mainHash[i5];
        if ((((i6 >> 8) ^ calcHash) << 8) == 0) {
            Name name = this._mainNames[i5];
            if (name == null || name.equals(iArr2, i3)) {
                return name;
            }
        } else if (i6 == 0) {
            return null;
        }
        int i7 = i6 & 255;
        if (i7 <= 0 || (bucket = this._collList[i7 - 1]) == null) {
            return null;
        }
        return bucket.find(calcHash, iArr2, i3);
    }

    public Name addName(String str, int i, int i2) {
        String str2 = str;
        int i3 = i;
        int i4 = i2;
        if (this._intern) {
            str2 = InternCache.instance.intern(str2);
        }
        int calcHash = i4 == 0 ? calcHash(i3) : calcHash(i3, i4);
        Name constructName = constructName(calcHash, str2, i3, i4);
        _addSymbol(calcHash, constructName);
        return constructName;
    }

    public Name addName(String str, int[] iArr, int i) {
        int calcHash;
        String str2 = str;
        int[] iArr2 = iArr;
        int i2 = i;
        if (this._intern) {
            str2 = InternCache.instance.intern(str2);
        }
        if (i2 < 3) {
            calcHash = i2 == 1 ? calcHash(iArr2[0]) : calcHash(iArr2[0], iArr2[1]);
        } else {
            calcHash = calcHash(iArr2, i2);
        }
        Name constructName = constructName(calcHash, str2, iArr2, i2);
        _addSymbol(calcHash, constructName);
        return constructName;
    }

    public int calcHash(int i) {
        int i2 = i ^ this._hashSeed;
        int i3 = i2 + (i2 >>> 15);
        return i3 ^ (i3 >>> 9);
    }

    public int calcHash(int i, int i2) {
        int i3 = i;
        int i4 = ((i3 ^ (i3 >>> 15)) + (i2 * 33)) ^ this._hashSeed;
        return i4 + (i4 >>> 7);
    }

    public int calcHash(int[] iArr, int i) {
        Throwable th;
        int[] iArr2 = iArr;
        int i2 = i;
        if (i2 < 3) {
            Throwable th2 = th;
            new IllegalArgumentException();
            throw th2;
        }
        int i3 = iArr2[0] ^ this._hashSeed;
        int i4 = (((i3 + (i3 >>> 9)) * 33) + iArr2[1]) * MULT2;
        int i5 = (i4 + (i4 >>> 15)) ^ iArr2[2];
        int i6 = i5 + (i5 >>> 17);
        for (int i7 = 3; i7 < i2; i7++) {
            int i8 = (i6 * 31) ^ iArr2[i7];
            int i9 = i8 + (i8 >>> 3);
            i6 = i9 ^ (i9 << 7);
        }
        int i10 = i6 + (i6 >>> 15);
        return i10 ^ (i10 << 9);
    }

    protected static int[] calcQuads(byte[] bArr) {
        byte[] bArr2 = bArr;
        int length = bArr2.length;
        int[] iArr = new int[((length + 3) / 4)];
        int i = 0;
        while (i < length) {
            byte b = bArr2[i] & Ev3Constants.Opcode.TST;
            int i2 = i + 1;
            if (i2 < length) {
                b = (b << 8) | (bArr2[i2] & Ev3Constants.Opcode.TST);
                i2++;
                if (i2 < length) {
                    b = (b << 8) | (bArr2[i2] & Ev3Constants.Opcode.TST);
                    i2++;
                    if (i2 < length) {
                        b = (b << 8) | (bArr2[i2] & Ev3Constants.Opcode.TST);
                    }
                }
            }
            iArr[i2 >> 2] = b;
            i = i2 + 1;
        }
        return iArr;
    }

    private void _addSymbol(int i, Name name) {
        int i2;
        Bucket bucket;
        int i3 = i;
        Name name2 = name;
        if (this._mainHashShared) {
            unshareMain();
        }
        if (this._needRehash) {
            rehash();
        }
        this._count++;
        int i4 = i3 & this._mainHashMask;
        if (this._mainNames[i4] == null) {
            this._mainHash[i4] = i3 << 8;
            if (this._mainNamesShared) {
                unshareNames();
            }
            this._mainNames[i4] = name2;
        } else {
            if (this._collListShared) {
                unshareCollision();
            }
            this._collCount++;
            int i5 = this._mainHash[i4];
            int i6 = i5 & 255;
            if (i6 == 0) {
                if (this._collEnd <= 254) {
                    i2 = this._collEnd;
                    this._collEnd++;
                    if (i2 >= this._collList.length) {
                        expandCollision();
                    }
                } else {
                    i2 = findBestBucket();
                }
                this._mainHash[i4] = (i5 & InputDeviceCompat.SOURCE_ANY) | (i2 + 1);
            } else {
                i2 = i6 - 1;
            }
            new Bucket(name2, this._collList[i2]);
            Bucket bucket2 = bucket;
            this._collList[i2] = bucket2;
            this._longestCollisionList = Math.max(bucket2.length(), this._longestCollisionList);
            if (this._longestCollisionList > 255) {
                reportTooManyCollisions(255);
            }
        }
        int length = this._mainHash.length;
        if (this._count > (length >> 1)) {
            int i7 = length >> 2;
            if (this._count > length - i7) {
                this._needRehash = true;
            } else if (this._collCount >= i7) {
                this._needRehash = true;
            }
        }
    }

    private void rehash() {
        Throwable th;
        StringBuilder sb;
        int i;
        Bucket bucket;
        this._needRehash = false;
        this._mainNamesShared = false;
        int length = this._mainHash.length;
        int i2 = length + length;
        if (i2 > 65536) {
            nukeSymbols();
            return;
        }
        this._mainHash = new int[i2];
        this._mainHashMask = i2 - 1;
        Name[] nameArr = this._mainNames;
        this._mainNames = new Name[i2];
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            Name name = nameArr[i4];
            if (name != null) {
                i3++;
                int hashCode = name.hashCode();
                int i5 = hashCode & this._mainHashMask;
                this._mainNames[i5] = name;
                this._mainHash[i5] = hashCode << 8;
            }
        }
        int i6 = this._collEnd;
        if (i6 == 0) {
            this._longestCollisionList = 0;
            return;
        }
        this._collCount = 0;
        this._collEnd = 0;
        this._collListShared = false;
        int i7 = 0;
        Bucket[] bucketArr = this._collList;
        this._collList = new Bucket[bucketArr.length];
        for (int i8 = 0; i8 < i6; i8++) {
            Bucket bucket2 = bucketArr[i8];
            while (true) {
                Bucket bucket3 = bucket2;
                if (bucket3 == null) {
                    break;
                }
                i3++;
                Name name2 = bucket3._name;
                int hashCode2 = name2.hashCode();
                int i9 = hashCode2 & this._mainHashMask;
                int i10 = this._mainHash[i9];
                if (this._mainNames[i9] == null) {
                    this._mainHash[i9] = hashCode2 << 8;
                    this._mainNames[i9] = name2;
                } else {
                    this._collCount++;
                    int i11 = i10 & 255;
                    if (i11 == 0) {
                        if (this._collEnd <= 254) {
                            i = this._collEnd;
                            this._collEnd++;
                            if (i >= this._collList.length) {
                                expandCollision();
                            }
                        } else {
                            i = findBestBucket();
                        }
                        this._mainHash[i9] = (i10 & InputDeviceCompat.SOURCE_ANY) | (i + 1);
                    } else {
                        i = i11 - 1;
                    }
                    new Bucket(name2, this._collList[i]);
                    Bucket bucket4 = bucket;
                    this._collList[i] = bucket4;
                    i7 = Math.max(i7, bucket4.length());
                }
                bucket2 = bucket3._next;
            }
        }
        this._longestCollisionList = i7;
        if (i3 != this._count) {
            Throwable th2 = th;
            new StringBuilder();
            new RuntimeException(sb.append("Internal error: count after rehash ").append(i3).append("; should be ").append(this._count).toString());
            throw th2;
        }
    }

    private void nukeSymbols() {
        this._count = 0;
        this._longestCollisionList = 0;
        Arrays.fill(this._mainHash, 0);
        Arrays.fill(this._mainNames, (Object) null);
        Arrays.fill(this._collList, (Object) null);
        this._collCount = 0;
        this._collEnd = 0;
    }

    private int findBestBucket() {
        Bucket[] bucketArr = this._collList;
        int i = Integer.MAX_VALUE;
        int i2 = -1;
        int i3 = this._collEnd;
        for (int i4 = 0; i4 < i3; i4++) {
            int length = bucketArr[i4].length();
            if (length < i) {
                if (length == 1) {
                    return i4;
                }
                i = length;
                i2 = i4;
            }
        }
        return i2;
    }

    private void unshareMain() {
        int[] iArr = this._mainHash;
        int length = this._mainHash.length;
        this._mainHash = new int[length];
        System.arraycopy(iArr, 0, this._mainHash, 0, length);
        this._mainHashShared = false;
    }

    private void unshareCollision() {
        Bucket[] bucketArr = this._collList;
        if (bucketArr == null) {
            this._collList = new Bucket[32];
        } else {
            int length = bucketArr.length;
            this._collList = new Bucket[length];
            System.arraycopy(bucketArr, 0, this._collList, 0, length);
        }
        this._collListShared = false;
    }

    private void unshareNames() {
        Name[] nameArr = this._mainNames;
        int length = nameArr.length;
        this._mainNames = new Name[length];
        System.arraycopy(nameArr, 0, this._mainNames, 0, length);
        this._mainNamesShared = false;
    }

    private void expandCollision() {
        Bucket[] bucketArr = this._collList;
        int length = bucketArr.length;
        this._collList = new Bucket[(length + length)];
        System.arraycopy(bucketArr, 0, this._collList, 0, length);
    }

    private static Name constructName(int i, String str, int i2, int i3) {
        Name name;
        Name name2;
        int i4 = i;
        String str2 = str;
        int i5 = i2;
        int i6 = i3;
        if (i6 == 0) {
            new Name1(str2, i4, i5);
            return name2;
        }
        new Name2(str2, i4, i5, i6);
        return name;
    }

    private static Name constructName(int i, String str, int[] iArr, int i2) {
        Name name;
        Name name2;
        Name name3;
        Name name4;
        int i3 = i;
        String str2 = str;
        int[] iArr2 = iArr;
        int i4 = i2;
        if (i4 < 4) {
            switch (i4) {
                case 1:
                    new Name1(str2, i3, iArr2[0]);
                    return name4;
                case 2:
                    new Name2(str2, i3, iArr2[0], iArr2[1]);
                    return name3;
                case 3:
                    new Name3(str2, i3, iArr2[0], iArr2[1], iArr2[2]);
                    return name2;
            }
        }
        int[] iArr3 = new int[i4];
        for (int i5 = 0; i5 < i4; i5++) {
            iArr3[i5] = iArr2[i5];
        }
        new NameN(str2, i3, iArr3, i4);
        return name;
    }

    /* access modifiers changed from: protected */
    public void reportTooManyCollisions(int i) {
        Throwable th;
        StringBuilder sb;
        Throwable th2 = th;
        new StringBuilder();
        new IllegalStateException(sb.append("Longest collision chain in symbol table (of size ").append(this._count).append(") now exceeds maximum, ").append(i).append(" -- suspect a DoS attack based on hash collisions").toString());
        throw th2;
    }

    private static final class TableInfo {
        public final int collCount;
        public final int collEnd;
        public final Bucket[] collList;
        public final int count;
        public final int longestCollisionList;
        public final int[] mainHash;
        public final int mainHashMask;
        public final Name[] mainNames;

        public TableInfo(int i, int i2, int[] iArr, Name[] nameArr, Bucket[] bucketArr, int i3, int i4, int i5) {
            this.count = i;
            this.mainHashMask = i2;
            this.mainHash = iArr;
            this.mainNames = nameArr;
            this.collList = bucketArr;
            this.collCount = i3;
            this.collEnd = i4;
            this.longestCollisionList = i5;
        }

        public TableInfo(BytesToNameCanonicalizer bytesToNameCanonicalizer) {
            BytesToNameCanonicalizer bytesToNameCanonicalizer2 = bytesToNameCanonicalizer;
            this.count = bytesToNameCanonicalizer2._count;
            this.mainHashMask = bytesToNameCanonicalizer2._mainHashMask;
            this.mainHash = bytesToNameCanonicalizer2._mainHash;
            this.mainNames = bytesToNameCanonicalizer2._mainNames;
            this.collList = bytesToNameCanonicalizer2._collList;
            this.collCount = bytesToNameCanonicalizer2._collCount;
            this.collEnd = bytesToNameCanonicalizer2._collEnd;
            this.longestCollisionList = bytesToNameCanonicalizer2._longestCollisionList;
        }
    }

    static final class Bucket {
        private final int _length;
        protected final Name _name;
        protected final Bucket _next;

        Bucket(Name name, Bucket bucket) {
            Bucket bucket2 = bucket;
            this._name = name;
            this._next = bucket2;
            this._length = bucket2 == null ? 1 : bucket2._length + 1;
        }

        public int length() {
            return this._length;
        }

        public Name find(int i, int i2, int i3) {
            int i4 = i;
            int i5 = i2;
            int i6 = i3;
            if (this._name.hashCode() == i4 && this._name.equals(i5, i6)) {
                return this._name;
            }
            Bucket bucket = this._next;
            while (true) {
                Bucket bucket2 = bucket;
                if (bucket2 == null) {
                    return null;
                }
                Name name = bucket2._name;
                if (name.hashCode() == i4 && name.equals(i5, i6)) {
                    return name;
                }
                bucket = bucket2._next;
            }
        }

        public Name find(int i, int[] iArr, int i2) {
            int i3 = i;
            int[] iArr2 = iArr;
            int i4 = i2;
            if (this._name.hashCode() == i3 && this._name.equals(iArr2, i4)) {
                return this._name;
            }
            Bucket bucket = this._next;
            while (true) {
                Bucket bucket2 = bucket;
                if (bucket2 == null) {
                    return null;
                }
                Name name = bucket2._name;
                if (name.hashCode() == i3 && name.equals(iArr2, i4)) {
                    return name;
                }
                bucket = bucket2._next;
            }
        }
    }
}

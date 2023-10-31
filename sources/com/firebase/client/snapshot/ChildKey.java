package com.firebase.client.snapshot;

import com.firebase.client.utilities.Utilities;

public class ChildKey implements Comparable<ChildKey> {
    static final /* synthetic */ boolean $assertionsDisabled = (!ChildKey.class.desiredAssertionStatus());
    private static final ChildKey INFO_CHILD_KEY;
    private static final ChildKey MAX_KEY;
    private static final ChildKey MIN_KEY;
    private static final ChildKey PRIORITY_CHILD_KEY;
    /* access modifiers changed from: private */
    public final String key;

    static {
        ChildKey childKey;
        ChildKey childKey2;
        ChildKey childKey3;
        ChildKey childKey4;
        new ChildKey("[MIN_KEY]");
        MIN_KEY = childKey;
        new ChildKey("[MAX_KEY]");
        MAX_KEY = childKey2;
        new ChildKey(".priority");
        PRIORITY_CHILD_KEY = childKey3;
        new ChildKey(".info");
        INFO_CHILD_KEY = childKey4;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    /* synthetic */ ChildKey(String x0, C14141 r7) {
        this(x0);
        C14141 r2 = r7;
    }

    public static ChildKey getMinName() {
        return MIN_KEY;
    }

    public static ChildKey getMaxName() {
        return MAX_KEY;
    }

    public static ChildKey getPriorityKey() {
        return PRIORITY_CHILD_KEY;
    }

    public static ChildKey getInfoKey() {
        return INFO_CHILD_KEY;
    }

    private ChildKey(String key2) {
        this.key = key2;
    }

    public String asString() {
        return this.key;
    }

    public boolean isPriorityChildName() {
        return this == PRIORITY_CHILD_KEY;
    }

    /* access modifiers changed from: protected */
    public boolean isInt() {
        return false;
    }

    /* access modifiers changed from: protected */
    public int intValue() {
        return 0;
    }

    public int compareTo(ChildKey childKey) {
        ChildKey other = childKey;
        if (this == other) {
            return 0;
        }
        if (this == MIN_KEY || other == MAX_KEY) {
            return -1;
        }
        if (other == MIN_KEY || this == MAX_KEY) {
            return 1;
        }
        if (isInt()) {
            if (!other.isInt()) {
                return -1;
            }
            int cmp = Utilities.compareInts(intValue(), other.intValue());
            return cmp == 0 ? Utilities.compareInts(this.key.length(), other.key.length()) : cmp;
        } else if (other.isInt()) {
            return 1;
        } else {
            return this.key.compareTo(other.key);
        }
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("ChildKey(\"").append(this.key).append("\")").toString();
    }

    public int hashCode() {
        return this.key.hashCode();
    }

    public boolean equals(Object obj) {
        Object obj2 = obj;
        if (!(obj2 instanceof ChildKey)) {
            return false;
        }
        if (this == obj2) {
            return true;
        }
        return this.key.equals(((ChildKey) obj2).key);
    }

    public static ChildKey fromString(String str) {
        ChildKey childKey;
        Throwable th;
        ChildKey childKey2;
        String key2 = str;
        Integer intValue = Utilities.tryParseInt(key2);
        if (intValue != null) {
            new IntegerChildKey(key2, intValue.intValue());
            return childKey2;
        } else if (key2.equals(".priority")) {
            return PRIORITY_CHILD_KEY;
        } else {
            if ($assertionsDisabled || !key2.contains("/")) {
                new ChildKey(key2);
                return childKey;
            }
            Throwable th2 = th;
            new AssertionError();
            throw th2;
        }
    }

    private static class IntegerChildKey extends ChildKey {
        private final int intValue;

        public /* bridge */ /* synthetic */ int compareTo(Object x0) {
            return ChildKey.super.compareTo((ChildKey) x0);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        IntegerChildKey(String name, int intValue2) {
            super(name, (C14141) null);
            this.intValue = intValue2;
        }

        /* access modifiers changed from: protected */
        public boolean isInt() {
            return true;
        }

        /* access modifiers changed from: protected */
        public int intValue() {
            return this.intValue;
        }

        public String toString() {
            StringBuilder sb;
            new StringBuilder();
            return sb.append("IntegerChildName(\"").append(this.key).append("\")").toString();
        }
    }
}

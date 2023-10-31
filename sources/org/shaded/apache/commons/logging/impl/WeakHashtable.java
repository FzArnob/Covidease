package org.shaded.apache.commons.logging.impl;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public final class WeakHashtable extends Hashtable {
    private static final int MAX_CHANGES_BEFORE_PURGE = 100;
    private static final int PARTIAL_PURGE_COUNT = 10;
    private int changeCount = 0;
    private ReferenceQueue queue;

    public WeakHashtable() {
        ReferenceQueue referenceQueue;
        new ReferenceQueue();
        this.queue = referenceQueue;
    }

    public boolean containsKey(Object key) {
        Object obj;
        new Referenced(key, (C14941) null);
        return super.containsKey(obj);
    }

    public Enumeration elements() {
        purge();
        return super.elements();
    }

    public Set entrySet() {
        Set set;
        Object obj;
        purge();
        Set<Map.Entry> referencedEntries = super.entrySet();
        new HashSet();
        Set unreferencedEntries = set;
        for (Map.Entry entry : referencedEntries) {
            Object key = Referenced.access$100((Referenced) entry.getKey());
            Object value = entry.getValue();
            if (key != null) {
                new Entry(key, value, (C14941) null);
                boolean add = unreferencedEntries.add(obj);
            }
        }
        return unreferencedEntries;
    }

    public Object get(Object key) {
        Object obj;
        new Referenced(key, (C14941) null);
        return super.get(obj);
    }

    public Enumeration keys() {
        Enumeration enumeration;
        purge();
        new Enumeration(this, super.keys()) {
            private final WeakHashtable this$0;
            private final Enumeration val$enumer;

            public boolean hasMoreElements() {
                return this.val$enumer.hasMoreElements();
            }

            {
                this.this$0 = this$0;
                this.val$enumer = val$enumer;
            }

            public Object nextElement() {
                return Referenced.access$100((Referenced) this.val$enumer.nextElement());
            }
        };
        return enumeration;
    }

    public Set keySet() {
        Set set;
        purge();
        Set<Referenced> referencedKeys = super.keySet();
        new HashSet();
        Set unreferencedKeys = set;
        for (Referenced referenceKey : referencedKeys) {
            Object keyValue = Referenced.access$100(referenceKey);
            if (keyValue != null) {
                boolean add = unreferencedKeys.add(keyValue);
            }
        }
        return unreferencedKeys;
    }

    public Object put(Object obj, Object obj2) {
        Object obj3;
        Throwable th;
        Throwable th2;
        Object key = obj;
        Object value = obj2;
        if (key == null) {
            Throwable th3 = th2;
            new NullPointerException("Null keys are not allowed");
            throw th3;
        } else if (value == null) {
            Throwable th4 = th;
            new NullPointerException("Null values are not allowed");
            throw th4;
        } else {
            int i = this.changeCount;
            int i2 = i;
            this.changeCount = i + 1;
            if (i2 > 100) {
                purge();
                this.changeCount = 0;
            } else if (this.changeCount % 10 == 0) {
                purgeOne();
            }
            new Referenced(key, this.queue, (C14941) null);
            return super.put(obj3, value);
        }
    }

    public void putAll(Map map) {
        Map t = map;
        if (t != null) {
            for (Map.Entry entry : t.entrySet()) {
                Object put = put(entry.getKey(), entry.getValue());
            }
        }
    }

    public Collection values() {
        purge();
        return super.values();
    }

    public Object remove(Object obj) {
        Object obj2;
        Object key = obj;
        int i = this.changeCount;
        int i2 = i;
        this.changeCount = i + 1;
        if (i2 > 100) {
            purge();
            this.changeCount = 0;
        } else if (this.changeCount % 10 == 0) {
            purgeOne();
        }
        new Referenced(key, (C14941) null);
        return super.remove(obj2);
    }

    public boolean isEmpty() {
        purge();
        return super.isEmpty();
    }

    public int size() {
        purge();
        return super.size();
    }

    public String toString() {
        purge();
        return super.toString();
    }

    /* access modifiers changed from: protected */
    public void rehash() {
        purge();
        super.rehash();
    }

    /* JADX INFO: finally extract failed */
    private void purge() {
        ReferenceQueue referenceQueue = this.queue;
        ReferenceQueue referenceQueue2 = referenceQueue;
        synchronized (referenceQueue) {
            while (true) {
                try {
                    WeakKey weakKey = (WeakKey) this.queue.poll();
                    WeakKey key = weakKey;
                    if (weakKey != null) {
                        Object remove = super.remove(WeakKey.access$400(key));
                    } else {
                        return;
                    }
                } catch (Throwable th) {
                    Throwable th2 = th;
                    ReferenceQueue referenceQueue3 = referenceQueue2;
                    throw th2;
                }
            }
        }
    }

    /* JADX INFO: finally extract failed */
    private void purgeOne() {
        ReferenceQueue referenceQueue = this.queue;
        ReferenceQueue referenceQueue2 = referenceQueue;
        synchronized (referenceQueue) {
            try {
                WeakKey key = (WeakKey) this.queue.poll();
                if (key != null) {
                    Object remove = super.remove(WeakKey.access$400(key));
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                ReferenceQueue referenceQueue3 = referenceQueue2;
                throw th2;
            }
        }
    }

    private static final class Entry implements Map.Entry {
        private final Object key;
        private final Object value;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        Entry(Object x0, Object x1, C14941 r10) {
            this(x0, x1);
            C14941 r3 = r10;
        }

        private Entry(Object key2, Object value2) {
            this.key = key2;
            this.value = value2;
        }

        public boolean equals(Object obj) {
            boolean z;
            Object o = obj;
            boolean result = false;
            if (o != null && (o instanceof Map.Entry)) {
                Map.Entry entry = (Map.Entry) o;
                if (getKey() != null ? getKey().equals(entry.getKey()) : entry.getKey() == null) {
                    if (getValue() != null ? getValue().equals(entry.getValue()) : entry.getValue() == null) {
                        z = true;
                        result = z;
                    }
                }
                z = false;
                result = z;
            }
            return result;
        }

        public int hashCode() {
            return (getKey() == null ? 0 : getKey().hashCode()) ^ (getValue() == null ? 0 : getValue().hashCode());
        }

        public Object setValue(Object obj) {
            Throwable th;
            Object obj2 = obj;
            Throwable th2 = th;
            new UnsupportedOperationException("Entry.setValue is not supported.");
            throw th2;
        }

        public Object getValue() {
            return this.value;
        }

        public Object getKey() {
            return this.key;
        }
    }

    private static final class Referenced {
        private final int hashCode;
        private final WeakReference reference;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        Referenced(Object x0, ReferenceQueue x1, C14941 r10) {
            this(x0, x1);
            C14941 r3 = r10;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        Referenced(Object x0, C14941 r7) {
            this(x0);
            C14941 r2 = r7;
        }

        static Object access$100(Referenced x0) {
            return x0.getValue();
        }

        private Referenced(Object obj) {
            WeakReference weakReference;
            Object referant = obj;
            new WeakReference(referant);
            this.reference = weakReference;
            this.hashCode = referant.hashCode();
        }

        private Referenced(Object obj, ReferenceQueue queue) {
            WeakReference weakReference;
            Object key = obj;
            new WeakKey(key, queue, this, (C14941) null);
            this.reference = weakReference;
            this.hashCode = key.hashCode();
        }

        public int hashCode() {
            return this.hashCode;
        }

        private Object getValue() {
            return this.reference.get();
        }

        public boolean equals(Object obj) {
            boolean z;
            Object o = obj;
            boolean result = false;
            if (o instanceof Referenced) {
                Referenced otherKey = (Referenced) o;
                Object thisKeyValue = getValue();
                Object otherKeyValue = otherKey.getValue();
                if (thisKeyValue == null) {
                    result = otherKeyValue == null;
                    if (result) {
                        if (hashCode() == otherKey.hashCode()) {
                            z = true;
                        } else {
                            z = false;
                        }
                        result = z;
                    }
                } else {
                    result = thisKeyValue.equals(otherKeyValue);
                }
            }
            return result;
        }
    }

    private static final class WeakKey extends WeakReference {
        private final Referenced referenced;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        WeakKey(Object x0, ReferenceQueue x1, Referenced x2, C14941 r13) {
            this(x0, x1, x2);
            C14941 r4 = r13;
        }

        static Referenced access$400(WeakKey x0) {
            return x0.getReferenced();
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        private WeakKey(Object key, ReferenceQueue queue, Referenced referenced2) {
            super(key, queue);
            this.referenced = referenced2;
        }

        private Referenced getReferenced() {
            return this.referenced;
        }
    }
}

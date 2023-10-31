package com.shaded.fasterxml.jackson.annotation;

import java.io.Serializable;

public abstract class ObjectIdGenerator<T> implements Serializable {
    public abstract boolean canUseFor(ObjectIdGenerator<?> objectIdGenerator);

    public abstract ObjectIdGenerator<T> forScope(Class<?> cls);

    public abstract T generateId(Object obj);

    public abstract Class<?> getScope();

    public abstract IdKey key(Object obj);

    public abstract ObjectIdGenerator<T> newForSerialization(Object obj);

    public ObjectIdGenerator() {
    }

    public static final class IdKey implements Serializable {
        private static final long serialVersionUID = 1;
        private final int hashCode;
        private final Object key;
        private final Class<?> scope;
        private final Class<?> type;

        public IdKey(Class<?> cls, Class<?> cls2, Object obj) {
            Class<?> cls3 = cls;
            Class<?> cls4 = cls2;
            Object obj2 = obj;
            this.type = cls3;
            this.scope = cls4;
            this.key = obj2;
            int hashCode2 = obj2.hashCode() + cls3.getName().hashCode();
            this.hashCode = cls4 != null ? hashCode2 ^ cls4.getName().hashCode() : hashCode2;
        }

        public int hashCode() {
            return this.hashCode;
        }

        /* JADX WARNING: type inference failed for: r6v0, types: [java.lang.Object] */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean equals(java.lang.Object r6) {
            /*
                r5 = this;
                r0 = r5
                r1 = r6
                r3 = r1
                r4 = r0
                if (r3 != r4) goto L_0x0009
                r3 = 1
                r0 = r3
            L_0x0008:
                return r0
            L_0x0009:
                r3 = r1
                if (r3 != 0) goto L_0x000f
                r3 = 0
                r0 = r3
                goto L_0x0008
            L_0x000f:
                r3 = r1
                java.lang.Class r3 = r3.getClass()
                r4 = r0
                java.lang.Class r4 = r4.getClass()
                if (r3 == r4) goto L_0x001e
                r3 = 0
                r0 = r3
                goto L_0x0008
            L_0x001e:
                r3 = r1
                com.shaded.fasterxml.jackson.annotation.ObjectIdGenerator$IdKey r3 = (com.shaded.fasterxml.jackson.annotation.ObjectIdGenerator.IdKey) r3
                r2 = r3
                r3 = r2
                java.lang.Object r3 = r3.key
                r4 = r0
                java.lang.Object r4 = r4.key
                boolean r3 = r3.equals(r4)
                if (r3 == 0) goto L_0x0041
                r3 = r2
                java.lang.Class<?> r3 = r3.type
                r4 = r0
                java.lang.Class<?> r4 = r4.type
                if (r3 != r4) goto L_0x0041
                r3 = r2
                java.lang.Class<?> r3 = r3.scope
                r4 = r0
                java.lang.Class<?> r4 = r4.scope
                if (r3 != r4) goto L_0x0041
                r3 = 1
            L_0x003f:
                r0 = r3
                goto L_0x0008
            L_0x0041:
                r3 = 0
                goto L_0x003f
            */
            throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.annotation.ObjectIdGenerator.IdKey.equals(java.lang.Object):boolean");
        }
    }
}

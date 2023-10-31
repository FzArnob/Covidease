package com.shaded.fasterxml.jackson.annotation;

import com.shaded.fasterxml.jackson.annotation.ObjectIdGenerator;
import java.util.UUID;

public class ObjectIdGenerators {
    public ObjectIdGenerators() {
    }

    private static abstract class Base<T> extends ObjectIdGenerator<T> {
        protected final Class<?> _scope;

        public abstract T generateId(Object obj);

        protected Base(Class<?> cls) {
            this._scope = cls;
        }

        public final Class<?> getScope() {
            return this._scope;
        }

        public boolean canUseFor(ObjectIdGenerator<?> objectIdGenerator) {
            ObjectIdGenerator<?> objectIdGenerator2 = objectIdGenerator;
            return objectIdGenerator2.getClass() == getClass() && objectIdGenerator2.getScope() == this._scope;
        }
    }

    public static abstract class None extends ObjectIdGenerator<Object> {
        public None() {
        }
    }

    public static abstract class PropertyGenerator extends Base<Object> {
        private static final long serialVersionUID = 1;

        public /* bridge */ /* synthetic */ boolean canUseFor(ObjectIdGenerator objectIdGenerator) {
            return super.canUseFor(objectIdGenerator);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        protected PropertyGenerator(Class<?> cls) {
            super(cls);
        }
    }

    public static final class IntSequenceGenerator extends Base<Integer> {
        private static final long serialVersionUID = 1;
        protected transient int _nextValue;

        public /* bridge */ /* synthetic */ boolean canUseFor(ObjectIdGenerator objectIdGenerator) {
            return super.canUseFor(objectIdGenerator);
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public IntSequenceGenerator() {
            this(Object.class, -1);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public IntSequenceGenerator(Class<?> cls, int i) {
            super(cls);
            this._nextValue = i;
        }

        /* access modifiers changed from: protected */
        public int initialValue() {
            return 1;
        }

        public ObjectIdGenerator<Integer> forScope(Class<?> cls) {
            IntSequenceGenerator intSequenceGenerator;
            IntSequenceGenerator intSequenceGenerator2;
            Class<?> cls2 = cls;
            if (this._scope == cls2) {
                intSequenceGenerator2 = this;
            } else {
                intSequenceGenerator2 = intSequenceGenerator;
                new IntSequenceGenerator(cls2, this._nextValue);
            }
            return intSequenceGenerator2;
        }

        public ObjectIdGenerator<Integer> newForSerialization(Object obj) {
            ObjectIdGenerator<Integer> objectIdGenerator;
            Object obj2 = obj;
            new IntSequenceGenerator(this._scope, initialValue());
            return objectIdGenerator;
        }

        public ObjectIdGenerator.IdKey key(Object obj) {
            ObjectIdGenerator.IdKey idKey;
            new ObjectIdGenerator.IdKey(getClass(), this._scope, obj);
            return idKey;
        }

        public Integer generateId(Object obj) {
            Object obj2 = obj;
            int i = this._nextValue;
            this._nextValue++;
            return Integer.valueOf(i);
        }
    }

    public static final class UUIDGenerator extends Base<UUID> {
        private static final long serialVersionUID = 1;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public UUIDGenerator() {
            this(Object.class);
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        private UUIDGenerator(Class<?> cls) {
            super(Object.class);
            Class<?> cls2 = cls;
        }

        public ObjectIdGenerator<UUID> forScope(Class<?> cls) {
            Class<?> cls2 = cls;
            return this;
        }

        public ObjectIdGenerator<UUID> newForSerialization(Object obj) {
            Object obj2 = obj;
            return this;
        }

        public UUID generateId(Object obj) {
            Object obj2 = obj;
            return UUID.randomUUID();
        }

        public ObjectIdGenerator.IdKey key(Object obj) {
            ObjectIdGenerator.IdKey idKey;
            new ObjectIdGenerator.IdKey(getClass(), (Class<?>) null, obj);
            return idKey;
        }

        public boolean canUseFor(ObjectIdGenerator<?> objectIdGenerator) {
            return objectIdGenerator.getClass() == getClass();
        }
    }
}

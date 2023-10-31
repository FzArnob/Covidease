package com.shaded.fasterxml.jackson.databind;

import java.io.Serializable;
import java.util.Map;

public abstract class InjectableValues {
    public abstract Object findInjectableValue(Object obj, DeserializationContext deserializationContext, BeanProperty beanProperty, Object obj2);

    public InjectableValues() {
    }

    public static class Std extends InjectableValues implements Serializable {
        private static final long serialVersionUID = 1;
        protected final Map<String, Object> _values;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public Std() {
            /*
                r5 = this;
                r0 = r5
                r1 = r0
                java.util.HashMap r2 = new java.util.HashMap
                r4 = r2
                r2 = r4
                r3 = r4
                r3.<init>()
                r1.<init>(r2)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.InjectableValues.Std.<init>():void");
        }

        public Std(Map<String, Object> map) {
            this._values = map;
        }

        public Std addValue(String str, Object obj) {
            Object put = this._values.put(str, obj);
            return this;
        }

        public Std addValue(Class<?> cls, Object obj) {
            Object put = this._values.put(cls.getName(), obj);
            return this;
        }

        public Object findInjectableValue(Object obj, DeserializationContext deserializationContext, BeanProperty beanProperty, Object obj2) {
            Throwable th;
            StringBuilder sb;
            Throwable th2;
            StringBuilder sb2;
            Object obj3 = obj;
            DeserializationContext deserializationContext2 = deserializationContext;
            BeanProperty beanProperty2 = beanProperty;
            Object obj4 = obj2;
            if (!(obj3 instanceof String)) {
                String name = obj3 == null ? "[null]" : obj3.getClass().getName();
                Throwable th3 = th2;
                new StringBuilder();
                new IllegalArgumentException(sb2.append("Unrecognized inject value id type (").append(name).append("), expecting String").toString());
                throw th3;
            }
            String str = (String) obj3;
            Object obj5 = this._values.get(str);
            if (obj5 != null || this._values.containsKey(str)) {
                return obj5;
            }
            Throwable th4 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("No injectable id with value '").append(str).append("' found (for property '").append(beanProperty2.getName()).append("')").toString());
            throw th4;
        }
    }
}

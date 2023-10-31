package gnu.bytecode;

import java.lang.annotation.Annotation;
import java.util.LinkedHashMap;
import java.util.Map;

public class AnnotationEntry implements Annotation {
    ClassType annotationType;
    LinkedHashMap<String, Object> elementsValue;

    public AnnotationEntry() {
        LinkedHashMap<String, Object> linkedHashMap;
        new LinkedHashMap<>(10);
        this.elementsValue = linkedHashMap;
    }

    public ClassType getAnnotationType() {
        return this.annotationType;
    }

    public void addMember(String name, Object value) {
        Object put = this.elementsValue.put(name, value);
    }

    public Class<? extends Annotation> annotationType() {
        return this.annotationType.getReflectClass();
    }

    public boolean equals(Object obj) {
        Object obj2 = obj;
        if (!(obj2 instanceof AnnotationEntry)) {
            return false;
        }
        AnnotationEntry other = (AnnotationEntry) obj2;
        if (!getAnnotationType().getName().equals(other.getAnnotationType().getName())) {
            return false;
        }
        for (Map.Entry<String, Object> it : this.elementsValue.entrySet()) {
            String key = it.getKey();
            Object value1 = it.getValue();
            Object value2 = other.elementsValue.get(key);
            if (value1 != value2 && (value1 == null || value2 == null || !value1.equals(value2))) {
                return false;
            }
        }
        for (Map.Entry<String, Object> it2 : other.elementsValue.entrySet()) {
            String key2 = it2.getKey();
            Object value22 = it2.getValue();
            Object value12 = this.elementsValue.get(key2);
            if (value12 != value22 && (value12 == null || value22 == null || !value12.equals(value22))) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int hash = 0;
        for (Map.Entry<String, Object> it : this.elementsValue.entrySet()) {
            hash += (127 * it.getKey().hashCode()) ^ it.getValue().hashCode();
        }
        return hash;
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        StringBuilder sbuf = sb;
        StringBuilder append = sbuf.append('@');
        StringBuilder append2 = sbuf.append(getAnnotationType().getName());
        StringBuilder append3 = sbuf.append('(');
        int count = 0;
        for (Map.Entry<String, Object> it : this.elementsValue.entrySet()) {
            if (count > 0) {
                StringBuilder append4 = sbuf.append(", ");
            }
            StringBuilder append5 = sbuf.append(it.getKey());
            StringBuilder append6 = sbuf.append('=');
            StringBuilder append7 = sbuf.append(it.getValue());
            count++;
        }
        StringBuilder append8 = sbuf.append(')');
        return sbuf.toString();
    }
}

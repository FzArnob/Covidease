package gnu.mapping;

public class LazyPropertyKey<T> extends PropertyKey<T> {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LazyPropertyKey(String name) {
        super(name);
    }

    public T get(PropertySet propertySet, T defaultValue) {
        Throwable th;
        StringBuilder sb;
        StringBuilder sb2;
        LazyPropertyKey<T> result;
        PropertySet container = propertySet;
        Object raw = container.getProperty(this, defaultValue);
        if (!(raw instanceof String)) {
            return raw;
        }
        String str = (String) raw;
        int cstart = str.charAt(0) == '*' ? 1 : 0;
        int colon = str.indexOf(58);
        if (colon <= cstart || colon >= str.length() - 1) {
            Throwable th2 = th;
            new StringBuilder();
            new RuntimeException(sb.append("lazy property ").append(this).append(" must have the form \"ClassName:fieldName\" or \"ClassName:staticMethodName\"").toString());
            throw th2;
        }
        String cname = str.substring(cstart, colon);
        String mname = str.substring(colon + 1);
        try {
            Class clas = Class.forName(cname, true, container.getClass().getClassLoader());
            if (cstart == 0) {
                result = clas.getField(mname).get((Object) null);
            } else {
                result = clas.getDeclaredMethod(mname, new Class[]{Object.class}).invoke((Object) null, new Object[]{container});
            }
            container.setProperty(this, result);
            return result;
        } catch (Throwable th3) {
            Throwable ex = th3;
            RuntimeException runtimeException = r19;
            new StringBuilder();
            RuntimeException runtimeException2 = new RuntimeException(sb2.append("lazy property ").append(this).append(" has specifier \"").append(str).append("\" but there is no such ").append(cstart == 0 ? "field" : "method").toString(), ex);
            throw runtimeException;
        }
    }

    public void set(PropertySet container, String specifier) {
        container.setProperty(this, specifier);
    }
}

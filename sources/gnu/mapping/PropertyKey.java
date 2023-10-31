package gnu.mapping;

public class PropertyKey<T> {
    String name;

    public PropertyKey(String name2) {
        this.name = name2;
    }

    public T get(PropertySet container, T defaultValue) {
        return container.getProperty(this, defaultValue);
    }

    public final T get(PropertySet container) {
        return get(container, (Object) null);
    }

    public void set(PropertySet container, T value) {
        container.setProperty(this, value);
    }
}

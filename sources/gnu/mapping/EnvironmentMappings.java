package gnu.mapping;

import java.util.AbstractSet;
import java.util.Iterator;

/* compiled from: SimpleEnvironment */
class EnvironmentMappings extends AbstractSet {
    SimpleEnvironment env;

    public EnvironmentMappings(SimpleEnvironment env2) {
        this.env = env2;
    }

    public int size() {
        return this.env.size();
    }

    public Iterator iterator() {
        Iterator it;
        new LocationEnumeration(this.env);
        return it;
    }
}

package gnu.expr;

import gnu.mapping.Environment;
import gnu.mapping.Location;
import gnu.mapping.LocationEnumeration;
import gnu.mapping.NamedLocation;
import gnu.mapping.Symbol;
import gnu.mapping.ThreadLocation;

public class BuiltinEnvironment extends Environment {
    static final BuiltinEnvironment instance;

    static {
        BuiltinEnvironment builtinEnvironment;
        new BuiltinEnvironment();
        instance = builtinEnvironment;
        instance.setName("language-builtins");
    }

    private BuiltinEnvironment() {
    }

    public static BuiltinEnvironment getInstance() {
        return instance;
    }

    public Environment getLangEnvironment() {
        Language lang = Language.getDefaultLanguage();
        return lang == null ? null : lang.getLangEnvironment();
    }

    public NamedLocation lookup(Symbol symbol, Object obj, int i) {
        Symbol name = symbol;
        Object property = obj;
        int hash = i;
        if (property == ThreadLocation.ANONYMOUS) {
            return null;
        }
        Language lang = Language.getDefaultLanguage();
        return lang == null ? null : lang.lookupBuiltin(name, property, hash);
    }

    public NamedLocation getLocation(Symbol symbol, Object obj, int i, boolean z) {
        Throwable th;
        Symbol symbol2 = symbol;
        Object obj2 = obj;
        int i2 = i;
        boolean z2 = z;
        Throwable th2 = th;
        new RuntimeException();
        throw th2;
    }

    public void define(Symbol symbol, Object obj, Object obj2) {
        Throwable th;
        Symbol symbol2 = symbol;
        Object obj3 = obj;
        Object obj4 = obj2;
        Throwable th2 = th;
        new RuntimeException();
        throw th2;
    }

    public LocationEnumeration enumerateLocations() {
        return getLangEnvironment().enumerateLocations();
    }

    public LocationEnumeration enumerateAllLocations() {
        return getLangEnvironment().enumerateAllLocations();
    }

    /* access modifiers changed from: protected */
    public boolean hasMoreElements(LocationEnumeration locationEnumeration) {
        Throwable th;
        LocationEnumeration locationEnumeration2 = locationEnumeration;
        Throwable th2 = th;
        new RuntimeException();
        throw th2;
    }

    public NamedLocation addLocation(Symbol symbol, Object obj, Location location) {
        Throwable th;
        Symbol symbol2 = symbol;
        Object obj2 = obj;
        Location location2 = location;
        Throwable th2 = th;
        new RuntimeException();
        throw th2;
    }
}

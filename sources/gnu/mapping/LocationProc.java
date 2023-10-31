package gnu.mapping;

public class LocationProc extends Procedure0or1 implements HasSetter {
    Location loc;

    public LocationProc(Location loc2) {
        this.loc = loc2;
    }

    public static LocationProc makeNamed(Symbol name, Location loc2) {
        LocationProc locationProc;
        new LocationProc(loc2);
        LocationProc lproc = locationProc;
        lproc.setSymbol(name);
        return lproc;
    }

    public LocationProc(Location loc2, Procedure procedure) {
        Procedure converter = procedure;
        this.loc = loc2;
        if (converter != null) {
            pushConverter(converter);
        }
    }

    public void pushConverter(Procedure converter) {
        this.loc = ConstrainedLocation.make(this.loc, converter);
    }

    public Object apply0() throws Throwable {
        return this.loc.get();
    }

    public Object apply1(Object value) throws Throwable {
        set0(value);
        return Values.empty;
    }

    public void set0(Object value) throws Throwable {
        this.loc.set(value);
    }

    public Procedure getSetter() {
        Procedure procedure;
        new Setter0(this);
        return procedure;
    }

    public final Location getLocation() {
        return this.loc;
    }

    public String toString() {
        StringBuilder sb;
        if (getSymbol() != null) {
            return super.toString();
        }
        new StringBuilder();
        return sb.append("#<location-proc ").append(this.loc).append(">").toString();
    }
}

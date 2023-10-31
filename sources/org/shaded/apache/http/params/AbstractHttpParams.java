package org.shaded.apache.http.params;

public abstract class AbstractHttpParams implements HttpParams {
    protected AbstractHttpParams() {
    }

    public long getLongParameter(String name, long j) {
        long defaultValue = j;
        Object param = getParameter(name);
        if (param == null) {
            return defaultValue;
        }
        return ((Long) param).longValue();
    }

    public HttpParams setLongParameter(String name, long value) {
        Object obj;
        new Long(value);
        HttpParams parameter = setParameter(name, obj);
        return this;
    }

    public int getIntParameter(String name, int i) {
        int defaultValue = i;
        Object param = getParameter(name);
        if (param == null) {
            return defaultValue;
        }
        return ((Integer) param).intValue();
    }

    public HttpParams setIntParameter(String name, int value) {
        Object obj;
        new Integer(value);
        HttpParams parameter = setParameter(name, obj);
        return this;
    }

    public double getDoubleParameter(String name, double d) {
        double defaultValue = d;
        Object param = getParameter(name);
        if (param == null) {
            return defaultValue;
        }
        return ((Double) param).doubleValue();
    }

    public HttpParams setDoubleParameter(String name, double value) {
        Object obj;
        new Double(value);
        HttpParams parameter = setParameter(name, obj);
        return this;
    }

    public boolean getBooleanParameter(String name, boolean z) {
        boolean defaultValue = z;
        Object param = getParameter(name);
        if (param == null) {
            return defaultValue;
        }
        return ((Boolean) param).booleanValue();
    }

    public HttpParams setBooleanParameter(String name, boolean value) {
        HttpParams parameter = setParameter(name, value ? Boolean.TRUE : Boolean.FALSE);
        return this;
    }

    public boolean isParameterTrue(String name) {
        return getBooleanParameter(name, false);
    }

    public boolean isParameterFalse(String name) {
        return !getBooleanParameter(name, false);
    }
}

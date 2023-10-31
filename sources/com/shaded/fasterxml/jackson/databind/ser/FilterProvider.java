package com.shaded.fasterxml.jackson.databind.ser;

public abstract class FilterProvider {
    public abstract BeanPropertyFilter findFilter(Object obj);

    public FilterProvider() {
    }
}

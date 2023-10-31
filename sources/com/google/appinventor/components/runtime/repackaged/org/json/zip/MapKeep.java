package com.google.appinventor.components.runtime.repackaged.org.json.zip;

import com.google.appinventor.components.runtime.repackaged.org.json.Kim;
import java.util.HashMap;

class MapKeep extends Keep {
    private Object[] list = new Object[this.capacity];
    private HashMap map;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MapKeep(int bits) {
        super(bits);
        HashMap hashMap;
        new HashMap(this.capacity);
        this.map = hashMap;
    }

    private void compact() {
        Object obj;
        int to = 0;
        for (int from = 0; from < this.capacity; from++) {
            Object key = this.list[from];
            long usage = age(this.uses[from]);
            if (usage > 0) {
                this.uses[to] = usage;
                this.list[to] = key;
                new Integer(to);
                Object put = this.map.put(key, obj);
                to++;
            } else {
                Object remove = this.map.remove(key);
            }
        }
        if (to < this.capacity) {
            this.length = to;
        } else {
            this.map.clear();
            this.length = 0;
        }
        this.power = 0;
    }

    public int find(Object key) {
        Object o = this.map.get(key);
        return o instanceof Integer ? ((Integer) o).intValue() : -1;
    }

    public boolean postMortem(PostMortem pm) {
        boolean b;
        StringBuffer stringBuffer;
        StringBuffer stringBuffer2;
        MapKeep that = (MapKeep) pm;
        if (this.length != that.length) {
            new StringBuffer();
            JSONzip.log(stringBuffer2.append(this.length).append(" <> ").append(that.length).toString());
            return false;
        }
        for (int i = 0; i < this.length; i++) {
            if (this.list[i] instanceof Kim) {
                b = ((Kim) this.list[i]).equals(that.list[i]);
            } else {
                Object o = this.list[i];
                Object q = that.list[i];
                if (o instanceof Number) {
                    o = o.toString();
                }
                if (q instanceof Number) {
                    q = q.toString();
                }
                b = o.equals(q);
            }
            if (!b) {
                new StringBuffer();
                JSONzip.log(stringBuffer.append("\n[").append(i).append("]\n ").append(this.list[i]).append("\n ").append(that.list[i]).append("\n ").append(this.uses[i]).append("\n ").append(that.uses[i]).toString());
                return false;
            }
        }
        return true;
    }

    public void register(Object obj) {
        Object obj2;
        Object value = obj;
        if (this.length >= this.capacity) {
            compact();
        }
        this.list[this.length] = value;
        new Integer(this.length);
        Object put = this.map.put(value, obj2);
        this.uses[this.length] = 1;
        this.length++;
    }

    public Object value(int integer) {
        return this.list[integer];
    }
}

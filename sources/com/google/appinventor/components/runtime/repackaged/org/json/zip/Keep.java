package com.google.appinventor.components.runtime.repackaged.org.json.zip;

abstract class Keep implements None, PostMortem {
    protected int capacity;
    protected int length = 0;
    protected int power = 0;
    protected long[] uses = new long[this.capacity];

    public abstract Object value(int i);

    public Keep(int bits) {
        this.capacity = JSONzip.twos[bits];
    }

    public static long age(long j) {
        long use = j;
        return use >= 32 ? 16 : use / 2;
    }

    public int bitsize() {
        while (JSONzip.twos[this.power] < this.length) {
            this.power++;
        }
        return this.power;
    }

    public void tick(int integer) {
        long[] jArr = this.uses;
        int i = integer;
        jArr[i] = jArr[i] + 1;
    }
}

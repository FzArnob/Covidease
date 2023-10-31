package com.google.appinventor.components.runtime.repackaged.org.json.zip;

import com.google.appinventor.components.runtime.repackaged.org.json.JSONException;

public class Huff implements None, PostMortem {
    private final int domain;
    private final Symbol[] symbols;
    private Symbol table;
    private boolean upToDate = false;
    private int width;

    private static class Symbol implements PostMortem {
        public Symbol back = null;
        public final int integer;
        public Symbol next = null;
        public Symbol one = null;
        public long weight = 0;
        public Symbol zero = null;

        public Symbol(int integer2) {
            this.integer = integer2;
        }

        public boolean postMortem(PostMortem pm) {
            boolean result = true;
            Symbol that = (Symbol) pm;
            if (this.integer != that.integer || this.weight != that.weight) {
                return false;
            }
            if ((this.back != null) != (that.back != null)) {
                return false;
            }
            Symbol zero2 = this.zero;
            Symbol one2 = this.one;
            if (zero2 != null) {
                result = zero2.postMortem(that.zero);
            } else if (that.zero != null) {
                return false;
            }
            if (one2 != null) {
                result = one2.postMortem(that.one);
            } else if (that.one != null) {
                return false;
            }
            return result;
        }
    }

    public Huff(int i) {
        Symbol symbol;
        Symbol symbol2;
        int domain2 = i;
        this.domain = domain2;
        int length = (domain2 * 2) - 1;
        this.symbols = new Symbol[length];
        for (int i2 = 0; i2 < domain2; i2++) {
            new Symbol(i2);
            this.symbols[i2] = symbol2;
        }
        for (int i3 = domain2; i3 < length; i3++) {
            new Symbol(-1);
            this.symbols[i3] = symbol;
        }
    }

    public void generate() {
        Symbol next;
        Symbol next2;
        if (!this.upToDate) {
            Symbol head = this.symbols[0];
            Symbol previous = head;
            this.table = null;
            head.next = null;
            for (int i = 1; i < this.domain; i++) {
                Symbol symbol = this.symbols[i];
                if (symbol.weight < head.weight) {
                    symbol.next = head;
                    head = symbol;
                } else {
                    if (symbol.weight < previous.weight) {
                        previous = head;
                    }
                    while (true) {
                        next2 = previous.next;
                        if (next2 == null || symbol.weight < next2.weight) {
                            symbol.next = next2;
                            previous.next = symbol;
                            previous = symbol;
                        } else {
                            previous = next2;
                        }
                    }
                    symbol.next = next2;
                    previous.next = symbol;
                    previous = symbol;
                }
            }
            int avail = this.domain;
            Symbol symbol2 = head;
            while (true) {
                Symbol previous2 = symbol2;
                Symbol first = head;
                Symbol second = first.next;
                head = second.next;
                Symbol symbol3 = this.symbols[avail];
                avail++;
                symbol3.weight = first.weight + second.weight;
                symbol3.zero = first;
                symbol3.one = second;
                symbol3.back = null;
                first.back = symbol3;
                second.back = symbol3;
                if (head == null) {
                    this.table = symbol3;
                    this.upToDate = true;
                    return;
                } else if (symbol3.weight < head.weight) {
                    symbol3.next = head;
                    head = symbol3;
                    symbol2 = head;
                } else {
                    while (true) {
                        next = previous2.next;
                        if (next == null || symbol3.weight < next.weight) {
                            symbol3.next = next;
                            previous2.next = symbol3;
                            symbol2 = symbol3;
                        } else {
                            previous2 = next;
                        }
                    }
                    symbol3.next = next;
                    previous2.next = symbol3;
                    symbol2 = symbol3;
                }
            }
        }
    }

    private boolean postMortem(int i) {
        Symbol symbol;
        int integer = i;
        int[] bits = new int[this.domain];
        Symbol symbol2 = this.symbols[integer];
        if (symbol2.integer != integer) {
            return false;
        }
        int i2 = 0;
        while (true) {
            Symbol back = symbol2.back;
            if (back != null) {
                if (back.zero == symbol2) {
                    bits[i2] = 0;
                } else if (back.one != symbol2) {
                    return false;
                } else {
                    bits[i2] = 1;
                }
                i2++;
                symbol2 = back;
            } else if (symbol2 != this.table) {
                return false;
            } else {
                this.width = 0;
                Symbol symbol3 = this.table;
                while (true) {
                    symbol = symbol3;
                    if (symbol.integer != -1) {
                        break;
                    }
                    i2--;
                    symbol3 = bits[i2] != 0 ? symbol.one : symbol.zero;
                }
                return symbol.integer == integer && i2 == 0;
            }
        }
    }

    public boolean postMortem(PostMortem postMortem) {
        PostMortem pm = postMortem;
        for (int integer = 0; integer < this.domain; integer++) {
            if (!postMortem(integer)) {
                JSONzip.log("\nBad huff ");
                JSONzip.logchar(integer, integer);
                return false;
            }
        }
        return this.table.postMortem(((Huff) pm).table);
    }

    public int read(BitReader bitReader) throws JSONException {
        Throwable th;
        BitReader bitreader = bitReader;
        try {
            this.width = 0;
            Symbol symbol = this.table;
            while (symbol.integer == -1) {
                this.width++;
                symbol = bitreader.bit() ? symbol.one : symbol.zero;
            }
            tick(symbol.integer);
            return symbol.integer;
        } catch (Throwable th2) {
            Throwable e = th2;
            Throwable th3 = th;
            new JSONException(e);
            throw th3;
        }
    }

    public void tick(int value) {
        this.symbols[value].weight++;
        this.upToDate = false;
    }

    public void tick(int from, int i) {
        int to = i;
        for (int value = from; value <= to; value++) {
            tick(value);
        }
    }

    private void write(Symbol symbol, BitWriter bitWriter) throws JSONException {
        Throwable th;
        Symbol symbol2 = symbol;
        BitWriter bitwriter = bitWriter;
        try {
            Symbol back = symbol2.back;
            if (back != null) {
                this.width++;
                write(back, bitwriter);
                if (back.zero == symbol2) {
                    bitwriter.zero();
                } else {
                    bitwriter.one();
                }
            }
        } catch (Throwable th2) {
            Throwable e = th2;
            Throwable th3 = th;
            new JSONException(e);
            throw th3;
        }
    }

    public void write(int i, BitWriter bitwriter) throws JSONException {
        int value = i;
        this.width = 0;
        write(this.symbols[value], bitwriter);
        tick(value);
    }
}

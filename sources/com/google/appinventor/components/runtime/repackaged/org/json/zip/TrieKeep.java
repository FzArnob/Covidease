package com.google.appinventor.components.runtime.repackaged.org.json.zip;

import com.google.appinventor.components.runtime.repackaged.org.json.Kim;
import com.google.appinventor.components.runtime.util.Ev3Constants;

class TrieKeep extends Keep {
    private int[] froms = new int[this.capacity];
    private Kim[] kims = new Kim[this.capacity];
    private Node root;
    private int[] thrus = new int[this.capacity];

    class Node implements PostMortem {
        private int integer = -1;
        private Node[] next = null;
        private final TrieKeep this$0;

        static int access$000(Node x0) {
            return x0.integer;
        }

        static int access$002(Node x0, int x1) {
            int i = x1;
            int i2 = i;
            x0.integer = i2;
            return i;
        }

        public Node(TrieKeep trieKeep) {
            this.this$0 = trieKeep;
        }

        public Node get(int cell) {
            return this.next == null ? null : this.next[cell];
        }

        public Node get(byte cell) {
            return get((int) cell & Ev3Constants.Opcode.TST);
        }

        public boolean postMortem(PostMortem pm) {
            StringBuffer stringBuffer;
            StringBuffer stringBuffer2;
            StringBuffer stringBuffer3;
            Node that = (Node) pm;
            if (that == null) {
                JSONzip.log("\nMisalign");
                return false;
            } else if (this.integer != that.integer) {
                new StringBuffer();
                JSONzip.log(stringBuffer3.append("\nInteger ").append(this.integer).append(" <> ").append(that.integer).toString());
                return false;
            } else if (this.next != null) {
                for (int i = 0; i < 256; i++) {
                    Node node = this.next[i];
                    if (node != null) {
                        if (!node.postMortem(that.next[i])) {
                            return false;
                        }
                    } else if (that.next[i] != null) {
                        new StringBuffer();
                        JSONzip.log(stringBuffer.append("\nMisalign ").append(i).toString());
                        return false;
                    }
                }
                return true;
            } else if (that.next == null) {
                return true;
            } else {
                new StringBuffer();
                JSONzip.log(stringBuffer2.append("\nNext is null ").append(this.integer).toString());
                return false;
            }
        }

        public void set(int i, Node node) {
            int cell = i;
            Node node2 = node;
            if (this.next == null) {
                this.next = new Node[256];
            }
            this.next[cell] = node2;
        }

        public void set(byte cell, Node node) {
            set((int) cell & Ev3Constants.Opcode.TST, node);
        }

        public Node vet(int i) {
            Node node;
            int cell = i;
            Node node2 = get(cell);
            if (node2 == null) {
                new Node(this.this$0);
                node2 = node;
                set(cell, node2);
            }
            return node2;
        }

        public Node vet(byte cell) {
            return vet((int) cell & Ev3Constants.Opcode.TST);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TrieKeep(int bits) {
        super(bits);
        Node node;
        new Node(this);
        this.root = node;
    }

    public Kim kim(int i) {
        Kim kim;
        int integer = i;
        Kim kim2 = this.kims[integer];
        int from = this.froms[integer];
        int thru = this.thrus[integer];
        if (!(from == 0 && thru == kim2.length)) {
            new Kim(kim2, from, thru);
            kim2 = kim;
            this.froms[integer] = 0;
            this.thrus[integer] = kim2.length;
            this.kims[integer] = kim2;
        }
        return kim2;
    }

    public int length(int i) {
        int integer = i;
        return this.thrus[integer] - this.froms[integer];
    }

    public int match(Kim kim, int i, int i2) {
        Kim kim2 = kim;
        int from = i;
        int thru = i2;
        Node node = this.root;
        int best = -1;
        for (int at = from; at < thru; at++) {
            node = node.get(kim2.get(at));
            if (node == null) {
                break;
            }
            if (Node.access$000(node) != -1) {
                best = Node.access$000(node);
            }
            from++;
        }
        return best;
    }

    public boolean postMortem(PostMortem pm) {
        StringBuffer stringBuffer;
        StringBuffer stringBuffer2;
        StringBuffer stringBuffer3;
        boolean result = true;
        TrieKeep that = (TrieKeep) pm;
        if (this.length != that.length) {
            new StringBuffer();
            JSONzip.log(stringBuffer3.append("\nLength ").append(this.length).append(" <> ").append(that.length).toString());
            return false;
        } else if (this.capacity != that.capacity) {
            new StringBuffer();
            JSONzip.log(stringBuffer2.append("\nCapacity ").append(this.capacity).append(" <> ").append(that.capacity).toString());
            return false;
        } else {
            for (int i = 0; i < this.length; i++) {
                Kim thiskim = kim(i);
                Kim thatkim = that.kim(i);
                if (!thiskim.equals(thatkim)) {
                    new StringBuffer();
                    JSONzip.log(stringBuffer.append("\n[").append(i).append("] ").append(thiskim).append(" <> ").append(thatkim).toString());
                    result = false;
                }
            }
            return result && this.root.postMortem(that.root);
        }
    }

    public void registerMany(Kim kim) {
        Kim kim2 = kim;
        int length = kim2.length;
        int limit = this.capacity - this.length;
        if (limit > 40) {
            limit = 40;
        }
        int until = length - 2;
        for (int from = 0; from < until; from++) {
            int len = length - from;
            if (len > 10) {
                len = 10;
            }
            int len2 = len + from;
            Node node = this.root;
            for (int at = from; at < len2; at++) {
                Node next = node.vet(kim2.get(at));
                if (Node.access$000(next) == -1 && at - from >= 2) {
                    int access$002 = Node.access$002(next, this.length);
                    this.uses[this.length] = 1;
                    this.kims[this.length] = kim2;
                    this.froms[this.length] = from;
                    this.thrus[this.length] = at + 1;
                    this.length++;
                    limit--;
                    if (limit <= 0) {
                        return;
                    }
                }
                node = next;
            }
        }
    }

    public void registerOne(Kim kim) {
        Kim kim2 = kim;
        int integer = registerOne(kim2, 0, kim2.length);
        if (integer != -1) {
            this.kims[integer] = kim2;
        }
    }

    public int registerOne(Kim kim, int i, int i2) {
        Kim kim2 = kim;
        int from = i;
        int thru = i2;
        if (this.length < this.capacity) {
            Node node = this.root;
            for (int at = from; at < thru; at++) {
                node = node.vet(kim2.get(at));
            }
            if (Node.access$000(node) == -1) {
                int at2 = this.length;
                int access$002 = Node.access$002(node, at2);
                this.uses[at2] = 1;
                this.kims[at2] = kim2;
                this.froms[at2] = from;
                this.thrus[at2] = thru;
                this.length++;
                return at2;
            }
        }
        return -1;
    }

    public void reserve() {
        Node node;
        Node node2;
        if (this.capacity - this.length < 40) {
            int to = 0;
            new Node(this);
            this.root = node;
            for (int from = 0; from < this.capacity; from++) {
                if (this.uses[from] > 1) {
                    Kim kim = this.kims[from];
                    int thru = this.thrus[from];
                    Node node3 = this.root;
                    for (int at = this.froms[from]; at < thru; at++) {
                        node3 = node3.vet(kim.get(at));
                    }
                    int access$002 = Node.access$002(node3, to);
                    this.uses[to] = age(this.uses[from]);
                    this.froms[to] = this.froms[from];
                    this.thrus[to] = thru;
                    this.kims[to] = kim;
                    to++;
                }
            }
            if (this.capacity - to < 40) {
                this.power = 0;
                new Node(this);
                this.root = node2;
                to = 0;
            }
            this.length = to;
            while (to < this.capacity) {
                this.uses[to] = 0;
                this.kims[to] = null;
                this.froms[to] = 0;
                this.thrus[to] = 0;
                to++;
            }
        }
    }

    public Object value(int integer) {
        return kim(integer);
    }
}

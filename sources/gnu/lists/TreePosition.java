package gnu.lists;

import java.io.PrintStream;

public class TreePosition extends SeqPosition implements Cloneable {
    int depth;
    int[] istack;
    AbstractSequence[] sstack;
    int start;
    private Object xpos;

    public TreePosition() {
        this.depth = -1;
    }

    public TreePosition(Object root) {
        this.xpos = root;
        this.depth = -1;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TreePosition(AbstractSequence seq, int index) {
        super(seq, index, false);
    }

    public TreePosition(TreePosition pos) {
        set(pos);
    }

    public Object clone() {
        TreePosition treePosition;
        new TreePosition(this);
        return treePosition;
    }

    public void set(TreePosition treePosition) {
        TreePosition position = treePosition;
        release();
        int d = position.depth;
        this.depth = d;
        if (d < 0) {
            this.xpos = position.xpos;
            return;
        }
        if (this.sstack == null || this.sstack.length <= d) {
            this.sstack = new AbstractSequence[(d + 10)];
        }
        if (this.istack == null || this.istack.length <= d) {
            this.istack = new int[(d + 10)];
        }
        for (int i = 0; i < this.depth; i++) {
            int j = i + position.start;
            AbstractSequence seq = position.sstack[j];
            this.sstack[this.depth - 1] = seq;
            this.istack[this.depth - i] = seq.copyPos(position.istack[j]);
        }
        AbstractSequence seq2 = position.sequence;
        this.sequence = seq2;
        this.ipos = seq2.copyPos(position.ipos);
    }

    public int getDepth() {
        return this.depth + 1;
    }

    public AbstractSequence getRoot() {
        return this.depth == 0 ? this.sequence : this.sstack[this.start];
    }

    public Object getPosNext() {
        return this.sequence == null ? this.xpos : this.sequence.getPosNext(this.ipos);
    }

    public void push(AbstractSequence abstractSequence, int i) {
        AbstractSequence child = abstractSequence;
        int iposChild = i;
        int d = this.depth + this.start;
        if (d >= 0) {
            if (d == 0) {
                this.istack = new int[8];
                this.sstack = new AbstractSequence[8];
            } else if (d >= this.istack.length) {
                int ndepth = 2 * d;
                int[] itemp = new int[ndepth];
                Object[] objArr = new Object[ndepth];
                AbstractSequence[] stemp = new AbstractSequence[ndepth];
                System.arraycopy(this.istack, 0, itemp, 0, this.depth);
                System.arraycopy(this.sstack, 0, stemp, 0, this.depth);
                this.istack = itemp;
                this.sstack = stemp;
            }
            this.sstack[d] = this.sequence;
            this.istack[d] = this.ipos;
        }
        this.depth++;
        this.sequence = child;
        this.ipos = iposChild;
    }

    public void pop() {
        this.sequence.releasePos(this.ipos);
        popNoRelease();
    }

    public void popNoRelease() {
        int i = this.depth - 1;
        int i2 = i;
        this.depth = i;
        if (i2 < 0) {
            this.xpos = this.sequence;
            this.sequence = null;
            return;
        }
        this.sequence = this.sstack[this.start + this.depth];
        this.ipos = this.istack[this.start + this.depth];
    }

    public final boolean gotoParent() {
        return this.sequence == null ? false : this.sequence.gotoParent(this);
    }

    public boolean gotoChildrenStart() {
        if (this.sequence == null) {
            if (!(this.xpos instanceof AbstractSequence)) {
                return false;
            }
            this.depth = 0;
            this.sequence = (AbstractSequence) this.xpos;
            setPos(this.sequence.startPos());
        } else if (!this.sequence.gotoChildrenStart(this)) {
            return false;
        }
        return true;
    }

    public boolean gotoAttributesStart() {
        if (this.sequence != null) {
            return this.sequence.gotoAttributesStart(this);
        }
        if (this.xpos instanceof AbstractSequence) {
        }
        return false;
    }

    public Object getAncestor(int i) {
        int up = i;
        if (up == 0) {
            return this.sequence.getPosNext(this.ipos);
        }
        int i2 = this.depth - up;
        if (i2 <= 0) {
            return getRoot();
        }
        int i3 = i2 + this.start;
        return this.sstack[i3].getPosNext(this.istack[i3]);
    }

    public void release() {
        while (this.sequence != null) {
            this.sequence.releasePos(this.ipos);
            pop();
        }
        this.xpos = null;
    }

    public void dump() {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        PrintStream printStream = System.err;
        new StringBuilder();
        printStream.println(sb.append("TreePosition dump depth:").append(this.depth).append(" start:").append(this.start).toString());
        int i = 0;
        while (i <= this.depth) {
            AbstractSequence seq = i == 0 ? this.sequence : this.sstack[this.depth - i];
            PrintStream printStream2 = System.err;
            new StringBuilder();
            printStream2.print(sb2.append("#").append(i).append(" seq:").append(seq).toString());
            PrintStream printStream3 = System.err;
            new StringBuilder();
            printStream3.println(sb3.append(" ipos:").append(i == 0 ? this.ipos : this.istack[this.depth - i]).toString());
            i++;
        }
    }
}

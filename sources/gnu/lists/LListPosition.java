package gnu.lists;

class LListPosition extends ExtPosition {
    Object xpos;

    public LListPosition(LListPosition lListPosition) {
        LListPosition old = lListPosition;
        this.sequence = old.sequence;
        this.ipos = old.ipos;
        this.xpos = old.xpos;
    }

    public SeqPosition copy() {
        LListPosition lListPosition;
        new LListPosition(this);
        return lListPosition;
    }

    public LListPosition(LList seq, int index, boolean isAfter) {
        set(seq, index, isAfter);
    }

    public void set(LList lList, int i, boolean z) {
        int skip;
        LList seq = lList;
        int index = i;
        boolean isAfter = z;
        this.sequence = seq;
        this.ipos = (index << 1) | (isAfter ? 1 : 0);
        int skip2 = index;
        if (isAfter) {
            skip = skip2 - 2;
        } else {
            skip = skip2 - 1;
        }
        if (skip >= 0) {
            Object obj = seq;
            while (true) {
                Object obj2 = obj;
                skip--;
                if (skip >= 0) {
                    obj = ((Pair) obj2).cdr;
                } else {
                    this.xpos = obj2;
                    return;
                }
            }
        } else {
            this.xpos = null;
        }
    }

    public void set(AbstractSequence seq, int index, boolean isAfter) {
        set((LList) seq, index, isAfter);
    }

    public boolean hasNext() {
        if (this.xpos != null) {
            Object next = ((Pair) this.xpos).cdr;
            if ((this.ipos & 1) > 0) {
                next = ((Pair) next).cdr;
            }
            return next != LList.Empty;
        } else if ((this.ipos >> 1) == 0) {
            return this.sequence != LList.Empty;
        } else {
            return ((Pair) this.sequence).cdr != LList.Empty;
        }
    }

    public boolean hasPrevious() {
        return (this.ipos >>> 1) != 0;
    }

    public Pair getNextPair() {
        AbstractSequence next;
        if ((this.ipos & 1) > 0) {
            if (this.xpos == null) {
                next = this.sequence;
                if ((this.ipos >> 1) != 0) {
                    next = ((Pair) next).cdr;
                }
            } else {
                next = ((Pair) ((Pair) this.xpos).cdr).cdr;
            }
        } else if (this.xpos == null) {
            next = this.sequence;
        } else {
            next = ((Pair) this.xpos).cdr;
        }
        if (next == LList.Empty) {
            return null;
        }
        return (Pair) next;
    }

    public Object getNext() {
        Pair pair = getNextPair();
        return pair == null ? LList.eofValue : pair.car;
    }

    public void setNext(Object value) {
        Object obj = value;
        getNextPair().car = obj;
    }

    public Pair getPreviousPair() {
        int isAfter = this.ipos & 1;
        Object p = this.xpos;
        if (isAfter > 0) {
            p = p == null ? this.sequence : ((Pair) p).cdr;
        } else if (p == null) {
            return null;
        }
        if (p == LList.Empty) {
            return null;
        }
        return (Pair) p;
    }

    public Object getPrevious() {
        Pair pair = getPreviousPair();
        return pair == null ? LList.eofValue : pair.car;
    }

    public void setPrevious(Object value) {
        Object obj = value;
        getPreviousPair().car = obj;
    }

    public int nextIndex() {
        return this.ipos >> 1;
    }

    public boolean gotoNext() {
        boolean isAfter = (this.ipos & 1) != 0;
        int i = this.ipos;
        Object xp = this.xpos;
        if (xp != null) {
            if (isAfter) {
                xp = ((Pair) xp).cdr;
            }
            if (((Pair) xp).cdr == LList.Empty) {
                return false;
            }
            this.xpos = xp;
            this.ipos = (this.ipos | 1) + 2;
        } else if ((this.ipos >> 1) != 0) {
            AbstractSequence xp2 = this.sequence;
            if (((Pair) xp2).cdr == LList.Empty) {
                return false;
            }
            this.ipos = 5;
            this.xpos = xp2;
        } else if (this.sequence == LList.Empty) {
            return false;
        } else {
            this.ipos = 3;
        }
        return true;
    }

    public boolean gotoPrevious() {
        if ((this.ipos >>> 1) == 0) {
            return false;
        }
        if ((this.ipos & 1) != 0) {
            this.ipos -= 3;
            return true;
        }
        set((LList) this.sequence, nextIndex() - 1, false);
        return true;
    }

    public String toString() {
        StringBuffer stringBuffer;
        new StringBuffer();
        StringBuffer sbuf = stringBuffer;
        StringBuffer append = sbuf.append("LListPos[");
        StringBuffer append2 = sbuf.append("index:");
        StringBuffer append3 = sbuf.append(this.ipos);
        if (isAfter()) {
            StringBuffer append4 = sbuf.append(" after");
        }
        if (this.position >= 0) {
            StringBuffer append5 = sbuf.append(" position:");
            StringBuffer append6 = sbuf.append(this.position);
        }
        StringBuffer append7 = sbuf.append(']');
        return sbuf.toString();
    }
}

package gnu.kawa.xml;

import android.support.p000v4.app.FragmentTransaction;
import gnu.lists.AbstractSequence;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;
import gnu.mapping.WrongType;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public abstract class TreeScanner extends MethodProc implements Externalizable {
    public NodePredicate type;

    public abstract void scan(AbstractSequence abstractSequence, int i, PositionConsumer positionConsumer);

    TreeScanner() {
        setProperty(Procedure.validateApplyKey, "gnu.kawa.xml.CompileXmlFunctions:validateApplyTreeScanner");
    }

    public NodePredicate getNodePredicate() {
        return this.type;
    }

    public int numArgs() {
        return FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
    }

    public void apply(CallContext callContext) throws Throwable {
        Throwable th;
        CallContext ctx = callContext;
        PositionConsumer out = (PositionConsumer) ctx.consumer;
        Object node = ctx.getNextArg();
        ctx.lastArg();
        try {
            KNode spos = (KNode) node;
            scan(spos.sequence, spos.getPos(), out);
        } catch (ClassCastException e) {
            ClassCastException classCastException = e;
            Throwable th2 = th;
            new WrongType(getDesc(), -4, node, "node()");
            throw th2;
        }
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.type);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        NodePredicate nodePredicate = (NodePredicate) in.readObject();
        this.type = nodePredicate;
    }

    public String getDesc() {
        StringBuilder sb;
        String thisName = getClass().getName();
        int dot = thisName.lastIndexOf(46);
        if (dot > 0) {
            thisName = thisName.substring(dot + 1);
        }
        new StringBuilder();
        return sb.append(thisName).append("::").append(this.type).toString();
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("#<").append(getClass().getName()).append(' ').append(this.type).append('>').toString();
    }
}

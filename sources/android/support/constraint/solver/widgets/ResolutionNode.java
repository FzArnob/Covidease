package android.support.constraint.solver.widgets;

import java.util.HashSet;
import java.util.Iterator;

public class ResolutionNode {
    public static final int REMOVED = 2;
    public static final int RESOLVED = 1;
    public static final int UNRESOLVED = 0;
    HashSet<ResolutionNode> dependents;
    int state = 0;

    public ResolutionNode() {
        HashSet<ResolutionNode> hashSet;
        new HashSet<>(2);
        this.dependents = hashSet;
    }

    public void addDependent(ResolutionNode node) {
        boolean add = this.dependents.add(node);
    }

    public void reset() {
        this.state = 0;
        this.dependents.clear();
    }

    public void invalidate() {
        this.state = 0;
        Iterator<ResolutionNode> it = this.dependents.iterator();
        while (it.hasNext()) {
            it.next().invalidate();
        }
    }

    public void invalidateAnchors() {
        if (this instanceof ResolutionAnchor) {
            this.state = 0;
        }
        Iterator<ResolutionNode> it = this.dependents.iterator();
        while (it.hasNext()) {
            it.next().invalidateAnchors();
        }
    }

    public void didResolve() {
        this.state = 1;
        Iterator<ResolutionNode> it = this.dependents.iterator();
        while (it.hasNext()) {
            it.next().resolve();
        }
    }

    public boolean isResolved() {
        return this.state == 1;
    }

    public void resolve() {
    }

    public void remove(ResolutionDimension resolutionDimension) {
    }
}

package gnu.mapping;

import java.lang.ref.WeakReference;

/* compiled from: Namespace */
class SymbolRef extends WeakReference {
    SymbolRef next;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SymbolRef(Symbol sym, Namespace namespace) {
        super(sym);
        Namespace namespace2 = namespace;
    }

    /* access modifiers changed from: package-private */
    public Symbol getSymbol() {
        return (Symbol) get();
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("SymbolRef[").append(getSymbol()).append("]").toString();
    }
}

package gnu.kawa.lispexpr;

import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Namespace;
import gnu.mapping.Symbol;

public class LispPackage extends Namespace {
    Namespace exported;
    NamespaceUse imported;
    NamespaceUse importing;
    LList shadowingSymbols = LList.Empty;

    public LispPackage() {
    }

    public Symbol lookup(String str, int i, boolean z) {
        Symbol symbol;
        String name = str;
        int hash = i;
        boolean create = z;
        Symbol sym = this.exported.lookup(name, hash, false);
        if (sym != null) {
            return sym;
        }
        Symbol sym2 = lookupInternal(name, hash);
        if (sym2 != null) {
            return sym2;
        }
        NamespaceUse namespaceUse = this.imported;
        while (true) {
            NamespaceUse used = namespaceUse;
            if (used != null) {
                Symbol sym3 = lookup(name, hash, false);
                if (sym3 != null) {
                    return sym3;
                }
                namespaceUse = used.nextImported;
            } else if (!create) {
                return null;
            } else {
                new Symbol(this, name);
                return add(symbol, hash);
            }
        }
    }

    public Symbol lookupPresent(String str, int i, boolean z) {
        String name = str;
        int hash = i;
        boolean intern = z;
        Symbol sym = this.exported.lookup(name, hash, false);
        if (sym == null) {
            sym = super.lookup(name, hash, intern);
        }
        return sym;
    }

    public boolean isPresent(String str) {
        String name = str;
        return lookupPresent(name, name.hashCode(), false) != null;
    }

    public boolean unintern(Symbol symbol) {
        Symbol symbol2 = symbol;
        String name = symbol2.getName();
        int hash = name.hashCode();
        if (this.exported.lookup(name, hash, false) == symbol2) {
            boolean remove = this.exported.remove(symbol2);
        } else if (super.lookup(name, hash, false) != symbol2) {
            return false;
        } else {
            boolean remove2 = super.remove(symbol2);
        }
        symbol2.setNamespace((Namespace) null);
        if (removeFromShadowingSymbols(symbol2)) {
        }
        return true;
    }

    private void addToShadowingSymbols(Symbol symbol) {
        LList lList;
        Symbol sym = symbol;
        Object obj = this.shadowingSymbols;
        while (true) {
            Object obj2 = obj;
            if (obj2 != LList.Empty) {
                Pair p = (Pair) obj2;
                if (p.getCar() != sym) {
                    obj = p.getCdr();
                } else {
                    return;
                }
            } else {
                new Pair(sym, this.shadowingSymbols);
                this.shadowingSymbols = lList;
                return;
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: gnu.lists.LList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: gnu.lists.LList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: gnu.lists.LList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v1, resolved type: gnu.mapping.Symbol} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean removeFromShadowingSymbols(gnu.mapping.Symbol r8) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r5 = 0
            r2 = r5
            r5 = r0
            gnu.lists.LList r5 = r5.shadowingSymbols
            r3 = r5
        L_0x0008:
            r5 = r3
            gnu.lists.LList r6 = gnu.lists.LList.Empty
            if (r5 == r6) goto L_0x0034
            r5 = r3
            gnu.lists.Pair r5 = (gnu.lists.Pair) r5
            r4 = r5
            r5 = r4
            java.lang.Object r5 = r5.getCdr()
            r3 = r5
            r5 = r4
            java.lang.Object r5 = r5.getCar()
            r6 = r1
            if (r5 != r6) goto L_0x0031
            r5 = r2
            if (r5 != 0) goto L_0x002b
            r5 = r0
            r6 = r3
            gnu.lists.LList r6 = (gnu.lists.LList) r6
            r5.shadowingSymbols = r6
        L_0x0028:
            r5 = 1
            r0 = r5
        L_0x002a:
            return r0
        L_0x002b:
            r5 = r2
            r6 = r3
            r5.setCdr(r6)
            goto L_0x0028
        L_0x0031:
            r5 = r4
            r2 = r5
            goto L_0x0008
        L_0x0034:
            r5 = 0
            r0 = r5
            goto L_0x002a
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.kawa.lispexpr.LispPackage.removeFromShadowingSymbols(gnu.mapping.Symbol):boolean");
    }

    public void shadow(String str) {
        String name = str;
        addToShadowingSymbols(lookupPresent(name, name.hashCode(), true));
    }

    public void shadowingImport(Symbol symbol) {
        Symbol symbol2 = symbol;
        String name = symbol2.getName();
        int hashCode = name.hashCode();
        Symbol old = lookupPresent(name, name.hashCode(), false);
        if (!(old == null || old == symbol2)) {
            boolean unintern = unintern(old);
        }
        addToShadowingSymbols(symbol2);
    }
}

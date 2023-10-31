package gnu.expr;

import gnu.kawa.util.GeneralHashTable;
import gnu.kawa.util.HashNode;
import gnu.mapping.Environment;
import gnu.mapping.Location;
import gnu.mapping.Symbol;

public class NameLookup extends GeneralHashTable<Object, Declaration> {
    static final Symbol KEY = Symbol.makeUninterned("<current-NameLookup>");
    Language language;

    public Language getLanguage() {
        return this.language;
    }

    public void setLanguage(Language language2) {
        Language language3 = language2;
        this.language = language3;
    }

    public NameLookup(Language language2) {
        this.language = language2;
    }

    public static NameLookup getInstance(Environment env, Language language2) {
        NameLookup nameLookup;
        Language language3 = language2;
        Location loc = env.getLocation(KEY);
        NameLookup nl = (NameLookup) loc.get((Object) null);
        if (nl == null) {
            new NameLookup(language3);
            nl = nameLookup;
            loc.set(nl);
        } else {
            nl.setLanguage(language3);
        }
        return nl;
    }

    public static void setInstance(Environment environment, NameLookup nameLookup) {
        Environment env = environment;
        NameLookup instance = nameLookup;
        if (instance == null) {
            env.remove(KEY);
        } else {
            env.put(KEY, (Object) null, instance);
        }
    }

    public void push(Declaration declaration) {
        Declaration decl = declaration;
        Object symbol = decl.getSymbol();
        if (symbol != null) {
            int i = this.num_bindings + 1;
            int i2 = i;
            this.num_bindings = i;
            if (i2 >= ((HashNode[]) this.table).length) {
                rehash();
            }
            int hash = hash(symbol);
            HashNode node = makeEntry(symbol, hash, decl);
            int index = hashToIndex(hash);
            node.next = ((HashNode[]) this.table)[index];
            ((HashNode[]) this.table)[index] = node;
        }
    }

    public boolean pop(Declaration declaration) {
        Declaration decl = declaration;
        Object symbol = decl.getSymbol();
        if (symbol == null) {
            return false;
        }
        HashNode prev = null;
        int index = hashToIndex(hash(symbol));
        HashNode hashNode = ((HashNode[]) this.table)[index];
        while (true) {
            HashNode node = hashNode;
            if (node == null) {
                return false;
            }
            HashNode next = node.next;
            if (node.getValue() == decl) {
                if (prev == null) {
                    ((HashNode[]) this.table)[index] = next;
                } else {
                    prev.next = next;
                }
                this.num_bindings--;
                return true;
            }
            prev = node;
            hashNode = next;
        }
    }

    public void push(ScopeExp exp) {
        Declaration firstDecl = exp.firstDecl();
        while (true) {
            Declaration decl = firstDecl;
            if (decl != null) {
                push(decl);
                firstDecl = decl.nextDecl();
            } else {
                return;
            }
        }
    }

    public void pop(ScopeExp exp) {
        Declaration firstDecl = exp.firstDecl();
        while (true) {
            Declaration decl = firstDecl;
            if (decl != null) {
                boolean pop = pop(decl);
                firstDecl = decl.nextDecl();
            } else {
                return;
            }
        }
    }

    public void removeSubsumed(Declaration declaration) {
        Declaration decl = declaration;
        int index = hashToIndex(hash(decl.getSymbol()));
        HashNode prev = null;
        HashNode hashNode = ((HashNode[]) this.table)[index];
        while (true) {
            HashNode node = hashNode;
            if (node != null) {
                HashNode next = node.next;
                Declaration ndecl = (Declaration) node.getValue();
                if (ndecl == decl || !subsumedBy(decl, ndecl)) {
                    prev = node;
                } else if (prev == null) {
                    ((HashNode[]) this.table)[index] = next;
                } else {
                    prev.next = next;
                }
                hashNode = next;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean subsumedBy(Declaration declaration, Declaration declaration2) {
        Declaration decl = declaration;
        Declaration other = declaration2;
        return decl.getSymbol() == other.getSymbol() && (this.language.getNamespaceOf(decl) & this.language.getNamespaceOf(other)) != 0;
    }

    public Declaration lookup(Object obj, int i) {
        Object symbol = obj;
        int namespace = i;
        HashNode hashNode = ((HashNode[]) this.table)[hashToIndex(hash(symbol))];
        while (true) {
            HashNode node = hashNode;
            if (node == null) {
                return null;
            }
            Declaration decl = (Declaration) node.getValue();
            if (symbol.equals(decl.getSymbol()) && this.language.hasNamespace(decl, namespace)) {
                return decl;
            }
            hashNode = node.next;
        }
    }

    public Declaration lookup(Object symbol, boolean function) {
        return lookup(symbol, function ? 2 : 1);
    }
}

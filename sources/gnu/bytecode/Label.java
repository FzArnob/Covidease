package gnu.bytecode;

import java.util.ArrayList;
import java.util.Iterator;

public class Label {
    int first_fixup;
    Type[] localTypes;
    boolean needsStackMapEntry;
    int position;
    Type[] stackTypes;
    private Object[] typeChangeListeners;

    public final boolean defined() {
        return this.position >= 0;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public Label() {
        this(-1);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public Label(CodeAttr codeAttr) {
        this(-1);
        CodeAttr codeAttr2 = codeAttr;
    }

    public Label(int position2) {
        this.position = position2;
    }

    /* access modifiers changed from: package-private */
    public Type mergeTypes(Type type, Type type2) {
        Type t1 = type;
        Type t2 = type2;
        if ((t1 instanceof PrimType) != (t2 instanceof PrimType)) {
            return null;
        }
        return Type.lowestCommonSuperType(t1, t2);
    }

    /* access modifiers changed from: package-private */
    public void setTypes(Type[] typeArr, int i, Type[] typeArr2, int i2) {
        Throwable th;
        Type[] locals = typeArr;
        int usedLocals = i;
        Type[] stack = typeArr2;
        int usedStack = i2;
        while (usedLocals > 0 && locals[usedLocals - 1] == null) {
            usedLocals--;
        }
        if (this.stackTypes == null) {
            if (usedStack == 0) {
                this.stackTypes = Type.typeArray0;
            } else {
                this.stackTypes = new Type[usedStack];
                System.arraycopy(stack, 0, this.stackTypes, 0, usedStack);
            }
            if (usedLocals == 0) {
                this.localTypes = Type.typeArray0;
                return;
            }
            this.localTypes = new Type[usedLocals];
            System.arraycopy(locals, 0, this.localTypes, 0, usedLocals);
            return;
        }
        int SP = usedStack;
        if (SP != this.stackTypes.length) {
            Throwable th2 = th;
            new InternalError("inconsistent stack length");
            throw th2;
        }
        for (int i3 = 0; i3 < SP; i3++) {
            this.stackTypes[i3] = mergeTypes(this.stackTypes[i3], stack[i3]);
        }
        int i4 = usedLocals < this.localTypes.length ? usedLocals : this.localTypes.length;
        for (int i5 = 0; i5 < i4; i5++) {
            mergeLocalType(i5, locals[i5]);
        }
        for (int i6 = usedLocals; i6 < this.localTypes.length; i6++) {
            this.localTypes[i6] = null;
        }
    }

    public void setTypes(CodeAttr codeAttr) {
        Throwable th;
        CodeAttr code = codeAttr;
        addTypeChangeListeners(code);
        if (this.stackTypes == null || code.f56SP == this.stackTypes.length) {
            setTypes(code.local_types, code.local_types == null ? 0 : code.local_types.length, code.stack_types, code.f56SP);
            return;
        }
        Throwable th2 = th;
        new InternalError();
        throw th2;
    }

    public void setTypes(Label label) {
        Label other = label;
        setTypes(other.localTypes, other.localTypes.length, other.stackTypes, other.stackTypes.length);
    }

    private void mergeLocalType(int i, Type newType) {
        int varnum = i;
        Type oldLocal = this.localTypes[varnum];
        Type newLocal = mergeTypes(oldLocal, newType);
        this.localTypes[varnum] = newLocal;
        if (newLocal != oldLocal) {
            notifyTypeChangeListeners(varnum, newLocal);
        }
    }

    private void notifyTypeChangeListeners(int i, Type type) {
        Object listeners;
        int varnum = i;
        Type newType = type;
        Object[] arr = this.typeChangeListeners;
        if (arr != null && arr.length > varnum && (listeners = arr[varnum]) != null) {
            if (listeners instanceof Label) {
                ((Label) listeners).mergeLocalType(varnum, newType);
            } else {
                Iterator i$ = ((ArrayList) listeners).iterator();
                while (i$.hasNext()) {
                    ((Label) i$.next()).mergeLocalType(varnum, newType);
                }
            }
            if (newType == null) {
                arr[varnum] = null;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void addTypeChangeListener(int i, Label label) {
        ArrayList arrayList;
        ArrayList arrayList2;
        int varnum = i;
        Label listener = label;
        Object[] arr = this.typeChangeListeners;
        if (arr == null) {
            Object[] objArr = new Object[(varnum + 10)];
            arr = objArr;
            this.typeChangeListeners = objArr;
        } else if (arr.length <= varnum) {
            arr = new Object[(varnum + 10)];
            System.arraycopy(this.typeChangeListeners, 0, arr, 0, this.typeChangeListeners.length);
            this.typeChangeListeners = arr;
        }
        Object set = arr[varnum];
        if (set == null) {
            arr[varnum] = listener;
            return;
        }
        if (set instanceof Label) {
            new ArrayList();
            arrayList = arrayList2;
            boolean add = arrayList.add((Label) set);
            arr[varnum] = arrayList;
        } else {
            arrayList = (ArrayList) set;
        }
        boolean add2 = arrayList.add(listener);
    }

    /* access modifiers changed from: package-private */
    public void addTypeChangeListeners(CodeAttr codeAttr) {
        CodeAttr code = codeAttr;
        if (code.local_types != null && code.previousLabel != null) {
            int len = code.local_types.length;
            for (int varnum = 0; varnum < len; varnum++) {
                if (code.local_types[varnum] != null && (code.varsSetInCurrentBlock == null || code.varsSetInCurrentBlock.length <= varnum || !code.varsSetInCurrentBlock[varnum])) {
                    code.previousLabel.addTypeChangeListener(varnum, this);
                }
            }
        }
    }

    public void defineRaw(CodeAttr codeAttr) {
        Throwable th;
        CodeAttr code = codeAttr;
        if (this.position >= 0) {
            Throwable th2 = th;
            new Error("label definition more than once");
            throw th2;
        }
        this.position = code.f55PC;
        this.first_fixup = code.fixup_count;
        if (this.first_fixup >= 0) {
            code.fixupAdd(1, this);
        }
    }

    public void define(CodeAttr codeAttr) {
        CodeAttr code = codeAttr;
        if (code.reachableHere()) {
            setTypes(code);
        } else if (this.localTypes != null) {
            int i = this.localTypes.length;
            while (true) {
                i--;
                if (i < 0) {
                    break;
                } else if (this.localTypes[i] != null && (code.locals.used == null || code.locals.used[i] == null)) {
                    this.localTypes[i] = null;
                }
            }
        }
        code.previousLabel = this;
        code.varsSetInCurrentBlock = null;
        defineRaw(code);
        if (this.localTypes != null) {
            code.setTypes(this);
        }
        code.setReachable(true);
    }
}

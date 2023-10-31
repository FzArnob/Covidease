package gnu.bytecode;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ConstantPool {
    public static final byte CLASS = 7;
    public static final byte DOUBLE = 6;
    public static final byte FIELDREF = 9;
    public static final byte FLOAT = 4;
    public static final byte INTEGER = 3;
    public static final byte INTERFACE_METHODREF = 11;
    public static final byte LONG = 5;
    public static final byte METHODREF = 10;
    public static final byte NAME_AND_TYPE = 12;
    public static final byte STRING = 8;
    public static final byte UTF8 = 1;
    int count;
    CpoolEntry[] hashTab;
    boolean locked;
    CpoolEntry[] pool;

    public final int getCount() {
        return this.count;
    }

    public final CpoolEntry getPoolEntry(int index) {
        return this.pool[index];
    }

    /* access modifiers changed from: package-private */
    public void rehash() {
        if (this.hashTab == null && this.count > 0) {
            int i = this.pool.length;
            while (true) {
                i--;
                if (i < 0) {
                    break;
                }
                CpoolEntry entry = this.pool[i];
                if (entry != null) {
                    int hashCode = entry.hashCode();
                }
            }
        }
        this.hashTab = new CpoolEntry[(this.count < 5 ? 101 : 2 * this.count)];
        if (this.pool != null) {
            int i2 = this.pool.length;
            while (true) {
                i2--;
                if (i2 >= 0) {
                    CpoolEntry entry2 = this.pool[i2];
                    if (entry2 != null) {
                        entry2.add_hashed(this);
                    }
                } else {
                    return;
                }
            }
        }
    }

    public CpoolUtf8 addUtf8(String s) {
        CpoolUtf8 cpoolUtf8;
        Throwable th;
        StringBuilder sb;
        String s2 = s.intern();
        int h = s2.hashCode();
        if (this.hashTab == null) {
            rehash();
        }
        CpoolEntry cpoolEntry = this.hashTab[(h & Integer.MAX_VALUE) % this.hashTab.length];
        while (true) {
            CpoolEntry entry = cpoolEntry;
            if (entry != null) {
                if (h == entry.hash && (entry instanceof CpoolUtf8)) {
                    CpoolUtf8 utf = (CpoolUtf8) entry;
                    if (utf.string == s2) {
                        return utf;
                    }
                }
                cpoolEntry = entry.next;
            } else if (this.locked) {
                Throwable th2 = th;
                new StringBuilder();
                new Error(sb.append("adding new Utf8 entry to locked contant pool: ").append(s2).toString());
                throw th2;
            } else {
                new CpoolUtf8(this, h, s2);
                return cpoolUtf8;
            }
        }
    }

    public CpoolClass addClass(ObjectType objectType) {
        ObjectType otype = objectType;
        CpoolClass entry = addClass(addUtf8(otype.getInternalName()));
        entry.clas = otype;
        return entry;
    }

    public CpoolClass addClass(CpoolUtf8 cpoolUtf8) {
        CpoolClass cpoolClass;
        CpoolUtf8 name = cpoolUtf8;
        int h = CpoolClass.hashCode(name);
        if (this.hashTab == null) {
            rehash();
        }
        CpoolEntry cpoolEntry = this.hashTab[(h & Integer.MAX_VALUE) % this.hashTab.length];
        while (true) {
            CpoolEntry entry = cpoolEntry;
            if (entry != null) {
                if (h == entry.hash && (entry instanceof CpoolClass)) {
                    CpoolClass ent = (CpoolClass) entry;
                    if (ent.name == name) {
                        return ent;
                    }
                }
                cpoolEntry = entry.next;
            } else {
                new CpoolClass(this, h, name);
                return cpoolClass;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public CpoolValue1 addValue1(int i, int i2) {
        CpoolValue1 cpoolValue1;
        int tag = i;
        int val = i2;
        int h = CpoolValue1.hashCode(val);
        if (this.hashTab == null) {
            rehash();
        }
        CpoolEntry cpoolEntry = this.hashTab[(h & Integer.MAX_VALUE) % this.hashTab.length];
        while (true) {
            CpoolEntry entry = cpoolEntry;
            if (entry != null) {
                if (h == entry.hash && (entry instanceof CpoolValue1)) {
                    CpoolValue1 ent = (CpoolValue1) entry;
                    if (ent.tag == tag && ent.value == val) {
                        return ent;
                    }
                }
                cpoolEntry = entry.next;
            } else {
                new CpoolValue1(this, tag, h, val);
                return cpoolValue1;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public CpoolValue2 addValue2(int i, long j) {
        CpoolValue2 cpoolValue2;
        int tag = i;
        long val = j;
        int h = CpoolValue2.hashCode(val);
        if (this.hashTab == null) {
            rehash();
        }
        CpoolEntry cpoolEntry = this.hashTab[(h & Integer.MAX_VALUE) % this.hashTab.length];
        while (true) {
            CpoolEntry entry = cpoolEntry;
            if (entry != null) {
                if (h == entry.hash && (entry instanceof CpoolValue2)) {
                    CpoolValue2 ent = (CpoolValue2) entry;
                    if (ent.tag == tag && ent.value == val) {
                        return ent;
                    }
                }
                cpoolEntry = entry.next;
            } else {
                new CpoolValue2(this, tag, h, val);
                return cpoolValue2;
            }
        }
    }

    public CpoolValue1 addInt(int val) {
        return addValue1(3, val);
    }

    public CpoolValue2 addLong(long val) {
        return addValue2(5, val);
    }

    public CpoolValue1 addFloat(float val) {
        return addValue1(4, Float.floatToIntBits(val));
    }

    public CpoolValue2 addDouble(double val) {
        return addValue2(6, Double.doubleToLongBits(val));
    }

    public final CpoolString addString(String string) {
        return addString(addUtf8(string));
    }

    public CpoolString addString(CpoolUtf8 cpoolUtf8) {
        CpoolString cpoolString;
        CpoolUtf8 str = cpoolUtf8;
        int h = CpoolString.hashCode(str);
        if (this.hashTab == null) {
            rehash();
        }
        CpoolEntry cpoolEntry = this.hashTab[(h & Integer.MAX_VALUE) % this.hashTab.length];
        while (true) {
            CpoolEntry entry = cpoolEntry;
            if (entry != null) {
                if (h == entry.hash && (entry instanceof CpoolString)) {
                    CpoolString ent = (CpoolString) entry;
                    if (ent.str == str) {
                        return ent;
                    }
                }
                cpoolEntry = entry.next;
            } else {
                new CpoolString(this, h, str);
                return cpoolString;
            }
        }
    }

    public CpoolNameAndType addNameAndType(Method method) {
        Method method2 = method;
        return addNameAndType(addUtf8(method2.getName()), addUtf8(method2.getSignature()));
    }

    public CpoolNameAndType addNameAndType(Field field) {
        Field field2 = field;
        return addNameAndType(addUtf8(field2.getName()), addUtf8(field2.getSignature()));
    }

    public CpoolNameAndType addNameAndType(CpoolUtf8 cpoolUtf8, CpoolUtf8 cpoolUtf82) {
        CpoolNameAndType cpoolNameAndType;
        CpoolUtf8 name = cpoolUtf8;
        CpoolUtf8 type = cpoolUtf82;
        int h = CpoolNameAndType.hashCode(name, type);
        if (this.hashTab == null) {
            rehash();
        }
        CpoolEntry cpoolEntry = this.hashTab[(h & Integer.MAX_VALUE) % this.hashTab.length];
        while (true) {
            CpoolEntry entry = cpoolEntry;
            if (entry == null) {
                new CpoolNameAndType(this, h, name, type);
                return cpoolNameAndType;
            } else if (h == entry.hash && (entry instanceof CpoolNameAndType) && ((CpoolNameAndType) entry).name == name && ((CpoolNameAndType) entry).type == type) {
                return (CpoolNameAndType) entry;
            } else {
                cpoolEntry = entry.next;
            }
        }
    }

    public CpoolRef addRef(int i, CpoolClass cpoolClass, CpoolNameAndType cpoolNameAndType) {
        CpoolRef cpoolRef;
        int tag = i;
        CpoolClass clas = cpoolClass;
        CpoolNameAndType nameAndType = cpoolNameAndType;
        int h = CpoolRef.hashCode(clas, nameAndType);
        if (this.hashTab == null) {
            rehash();
        }
        CpoolEntry cpoolEntry = this.hashTab[(h & Integer.MAX_VALUE) % this.hashTab.length];
        while (true) {
            CpoolEntry entry = cpoolEntry;
            if (entry != null) {
                if (h == entry.hash && (entry instanceof CpoolRef)) {
                    CpoolRef ref = (CpoolRef) entry;
                    if (ref.tag == tag && ref.clas == clas && ref.nameAndType == nameAndType) {
                        return ref;
                    }
                }
                cpoolEntry = entry.next;
            } else {
                new CpoolRef(this, h, tag, clas, nameAndType);
                return cpoolRef;
            }
        }
    }

    public CpoolRef addMethodRef(Method method) {
        int tag;
        Method method2 = method;
        CpoolClass clas = addClass((ObjectType) method2.classfile);
        if ((method2.getDeclaringClass().getModifiers() & 512) == 0) {
            tag = 10;
        } else {
            tag = 11;
        }
        return addRef(tag, clas, addNameAndType(method2));
    }

    public CpoolRef addFieldRef(Field field) {
        Field field2 = field;
        return addRef(9, addClass((ObjectType) field2.owner), addNameAndType(field2));
    }

    /* access modifiers changed from: package-private */
    public void write(DataOutputStream dataOutputStream) throws IOException {
        DataOutputStream dstr = dataOutputStream;
        dstr.writeShort(this.count + 1);
        for (int i = 1; i <= this.count; i++) {
            CpoolEntry entry = this.pool[i];
            if (entry != null) {
                entry.write(dstr);
            }
        }
        this.locked = true;
    }

    /* access modifiers changed from: package-private */
    public CpoolEntry getForced(int index, int i) {
        Throwable th;
        StringBuilder sb;
        CpoolEntry cpoolEntry;
        CpoolEntry cpoolEntry2;
        CpoolEntry cpoolEntry3;
        CpoolEntry cpoolEntry4;
        CpoolEntry cpoolEntry5;
        CpoolEntry cpoolEntry6;
        CpoolEntry cpoolEntry7;
        Throwable th2;
        int tag = i;
        int index2 = index & 65535;
        CpoolEntry entry = this.pool[index2];
        if (entry == null) {
            if (this.locked) {
                Throwable th3 = th2;
                new Error("adding new entry to locked contant pool");
                throw th3;
            }
            switch (tag) {
                case 1:
                    new CpoolUtf8();
                    entry = cpoolEntry7;
                    break;
                case 3:
                case 4:
                    new CpoolValue1(tag);
                    entry = cpoolEntry6;
                    break;
                case 5:
                case 6:
                    new CpoolValue2(tag);
                    entry = cpoolEntry5;
                    break;
                case 7:
                    new CpoolClass();
                    entry = cpoolEntry4;
                    break;
                case 8:
                    new CpoolString();
                    entry = cpoolEntry3;
                    break;
                case 9:
                case 10:
                case 11:
                    new CpoolRef(tag);
                    entry = cpoolEntry2;
                    break;
                case 12:
                    new CpoolNameAndType();
                    entry = cpoolEntry;
                    break;
            }
            this.pool[index2] = entry;
            entry.index = index2;
        } else if (entry.getTag() != tag) {
            Throwable th4 = th;
            new StringBuilder();
            new ClassFormatError(sb.append("conflicting constant pool tags at ").append(index2).toString());
            throw th4;
        }
        return entry;
    }

    /* access modifiers changed from: package-private */
    public CpoolClass getForcedClass(int index) {
        return (CpoolClass) getForced(index, 7);
    }

    public ConstantPool() {
    }

    public ConstantPool(DataInputStream dataInputStream) throws IOException {
        DataInputStream dstr = dataInputStream;
        this.count = dstr.readUnsignedShort() - 1;
        this.pool = new CpoolEntry[(this.count + 1)];
        int i = 1;
        while (i <= this.count) {
            byte tag = dstr.readByte();
            CpoolEntry entry = getForced(i, tag);
            switch (tag) {
                case 1:
                    ((CpoolUtf8) entry).string = dstr.readUTF();
                    break;
                case 3:
                case 4:
                    ((CpoolValue1) entry).value = dstr.readInt();
                    break;
                case 5:
                case 6:
                    ((CpoolValue2) entry).value = dstr.readLong();
                    i++;
                    break;
                case 7:
                    ((CpoolClass) entry).name = (CpoolUtf8) getForced(dstr.readUnsignedShort(), 1);
                    break;
                case 8:
                    ((CpoolString) entry).str = (CpoolUtf8) getForced(dstr.readUnsignedShort(), 1);
                    break;
                case 9:
                case 10:
                case 11:
                    CpoolRef ref = (CpoolRef) entry;
                    ref.clas = getForcedClass(dstr.readUnsignedShort());
                    ref.nameAndType = (CpoolNameAndType) getForced(dstr.readUnsignedShort(), 12);
                    break;
                case 12:
                    CpoolNameAndType ntyp = (CpoolNameAndType) entry;
                    ntyp.name = (CpoolUtf8) getForced(dstr.readUnsignedShort(), 1);
                    ntyp.type = (CpoolUtf8) getForced(dstr.readUnsignedShort(), 1);
                    break;
            }
            i++;
        }
    }
}

package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public abstract class Attribute {
    AttrContainer container;
    String name;
    int name_index;
    Attribute next;

    public abstract int getLength();

    public abstract void write(DataOutputStream dataOutputStream) throws IOException;

    public final AttrContainer getContainer() {
        return this.container;
    }

    public final void setContainer(AttrContainer container2) {
        AttrContainer attrContainer = container2;
        this.container = attrContainer;
    }

    public final Attribute getNext() {
        return this.next;
    }

    public final void setNext(Attribute next2) {
        Attribute attribute = next2;
        this.next = attribute;
    }

    public void addToFrontOf(AttrContainer attrContainer) {
        AttrContainer container2 = attrContainer;
        setContainer(container2);
        setNext(container2.getAttributes());
        container2.setAttributes(this);
    }

    public final boolean isSkipped() {
        return this.name_index < 0;
    }

    public final void setSkipped(boolean skip) {
        this.name_index = skip ? -1 : 0;
    }

    public final void setSkipped() {
        this.name_index = -1;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String name2) {
        String intern = name2.intern();
        this.name = intern;
    }

    public final int getNameIndex() {
        return this.name_index;
    }

    public final void setNameIndex(int index) {
        int i = index;
        this.name_index = i;
    }

    public Attribute(String name2) {
        this.name = name2;
    }

    public static Attribute get(AttrContainer container2, String str) {
        String name2 = str;
        Attribute attributes = container2.getAttributes();
        while (true) {
            Attribute attr = attributes;
            if (attr == null) {
                return null;
            }
            if (attr.getName() == name2) {
                return attr;
            }
            attributes = attr.next;
        }
    }

    public void assignConstants(ClassType classType) {
        ClassType cl = classType;
        if (this.name_index == 0) {
            this.name_index = cl.getConstants().addUtf8(this.name).getIndex();
        }
    }

    public static void assignConstants(AttrContainer container2, ClassType classType) {
        ClassType cl = classType;
        Attribute attributes = container2.getAttributes();
        while (true) {
            Attribute attr = attributes;
            if (attr != null) {
                if (!attr.isSkipped()) {
                    attr.assignConstants(cl);
                }
                attributes = attr.next;
            } else {
                return;
            }
        }
    }

    public static int getLengthAll(AttrContainer container2) {
        int length = 0;
        Attribute attributes = container2.getAttributes();
        while (true) {
            Attribute attr = attributes;
            if (attr == null) {
                return length;
            }
            if (!attr.isSkipped()) {
                length += 6 + attr.getLength();
            }
            attributes = attr.next;
        }
    }

    public static int count(AttrContainer container2) {
        int count = 0;
        Attribute attributes = container2.getAttributes();
        while (true) {
            Attribute attr = attributes;
            if (attr == null) {
                return count;
            }
            if (!attr.isSkipped()) {
                count++;
            }
            attributes = attr.next;
        }
    }

    public static void writeAll(AttrContainer attrContainer, DataOutputStream dataOutputStream) throws IOException {
        Throwable th;
        AttrContainer container2 = attrContainer;
        DataOutputStream dstr = dataOutputStream;
        dstr.writeShort(count(container2));
        Attribute attributes = container2.getAttributes();
        while (true) {
            Attribute attr = attributes;
            if (attr != null) {
                if (!attr.isSkipped()) {
                    if (attr.name_index == 0) {
                        Throwable th2 = th;
                        new Error("Attribute.writeAll called without assignConstants");
                        throw th2;
                    }
                    dstr.writeShort(attr.name_index);
                    dstr.writeInt(attr.getLength());
                    attr.write(dstr);
                }
                attributes = attr.next;
            } else {
                return;
            }
        }
    }

    public void print(ClassTypeWriter classTypeWriter) {
        ClassTypeWriter dst = classTypeWriter;
        dst.print("Attribute \"");
        dst.print(getName());
        dst.print("\", length:");
        dst.println(getLength());
    }
}

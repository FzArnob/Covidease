package gnu.bytecode;

import com.google.appinventor.components.common.PropertyTypeConstants;
import java.io.DataOutputStream;
import java.io.IOException;
import kawa.Telnet;

public class StackMapTableAttr extends MiscAttr {
    public static boolean compressStackMapTable = true;
    int countLocals;
    int countStack;
    int[] encodedLocals;
    int[] encodedStack;
    int numEntries;
    int prevPosition = -1;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public StackMapTableAttr() {
        super("StackMapTable", (byte[]) null, 0, 0);
        put2(0);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public StackMapTableAttr(byte[] r9, gnu.bytecode.CodeAttr r10) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r2 = r10
            r3 = r0
            java.lang.String r4 = "StackMapTable"
            r5 = r1
            r6 = 0
            r7 = r1
            int r7 = r7.length
            r3.<init>(r4, r5, r6, r7)
            r3 = r0
            r4 = -1
            r3.prevPosition = r4
            r3 = r0
            r4 = r2
            r3.addToFrontOf(r4)
            r3 = r0
            r4 = r0
            r5 = 0
            int r4 = r4.mo15179u2(r5)
            r3.numEntries = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.bytecode.StackMapTableAttr.<init>(byte[], gnu.bytecode.CodeAttr):void");
    }

    public Method getMethod() {
        return ((CodeAttr) this.container).getMethod();
    }

    public void write(DataOutputStream dstr) throws IOException {
        put2(0, this.numEntries);
        super.write(dstr);
    }

    /* access modifiers changed from: package-private */
    public void emitVerificationType(int i) {
        int encoding = i;
        int tag = encoding & 255;
        put1(tag);
        if (tag >= 7) {
            put2(encoding >> 8);
        }
    }

    /* access modifiers changed from: package-private */
    public int encodeVerificationType(Type type, CodeAttr codeAttr) {
        Type type2 = type;
        CodeAttr code = codeAttr;
        if (type2 == null) {
            return 0;
        }
        if (type2 instanceof UninitializedType) {
            Label label = ((UninitializedType) type2).label;
            if (label == null) {
                return 6;
            }
            return (label.position << 8) | 8;
        }
        Type type3 = type2.getImplementationType();
        if (type3 instanceof PrimType) {
            switch (type3.signature.charAt(0)) {
                case 'B':
                case 'C':
                case 'I':
                case 'S':
                case 'Z':
                    return 1;
                case 'D':
                    return 3;
                case 'F':
                    return 2;
                case 'J':
                    return 4;
                default:
                    return 0;
            }
        } else if (type3 == Type.nullType) {
            return 5;
        } else {
            return (code.getConstants().addClass((ObjectType) type3).index << 8) | 7;
        }
    }

    public void emitStackMapEntry(Label label, CodeAttr codeAttr) {
        Label label2 = label;
        CodeAttr code = codeAttr;
        int offset_delta = (label2.position - this.prevPosition) - 1;
        int rawLocalsCount = label2.localTypes.length;
        if (rawLocalsCount > this.encodedLocals.length) {
            int[] tmp = new int[(rawLocalsCount + this.encodedLocals.length)];
            System.arraycopy(this.encodedLocals, 0, tmp, 0, this.countLocals);
            this.encodedLocals = tmp;
        }
        int rawStackCount = label2.stackTypes.length;
        if (rawStackCount > this.encodedStack.length) {
            int[] tmp2 = new int[(rawStackCount + this.encodedStack.length)];
            System.arraycopy(this.encodedStack, 0, tmp2, 0, this.countStack);
            this.encodedStack = tmp2;
        }
        int unchangedLocals = 0;
        int curLocalsCount = 0;
        int i = 0;
        while (i < rawLocalsCount) {
            int prevType = this.encodedLocals[curLocalsCount];
            int nextType = encodeVerificationType(label2.localTypes[i], code);
            if (prevType == nextType && unchangedLocals == curLocalsCount) {
                unchangedLocals = curLocalsCount + 1;
            }
            int i2 = curLocalsCount;
            curLocalsCount++;
            this.encodedLocals[i2] = nextType;
            if (nextType == 3 || nextType == 4) {
                i++;
            }
            i++;
        }
        while (curLocalsCount > 0 && this.encodedLocals[curLocalsCount - 1] == 0) {
            curLocalsCount--;
        }
        int curStackCount = 0;
        int i3 = 0;
        while (i3 < rawStackCount) {
            int i4 = this.encodedStack[curStackCount];
            Type t = label2.stackTypes[i3];
            if (t == Type.voidType) {
                i3++;
                t = label2.stackTypes[i3];
            }
            int i5 = curStackCount;
            curStackCount++;
            this.encodedStack[i5] = encodeVerificationType(t, code);
            i3++;
        }
        int localsDelta = curLocalsCount - this.countLocals;
        if (!compressStackMapTable || localsDelta != 0 || curLocalsCount != unchangedLocals || curStackCount > 1) {
            if (compressStackMapTable && curStackCount == 0) {
                if (curLocalsCount < this.countLocals && unchangedLocals == curLocalsCount && localsDelta >= -3) {
                    put1(Telnet.WILL + localsDelta);
                    put2(offset_delta);
                }
            }
            if (!compressStackMapTable || curStackCount != 0 || this.countLocals != unchangedLocals || localsDelta > 3) {
                put1(255);
                put2(offset_delta);
                put2(curLocalsCount);
                for (int i6 = 0; i6 < curLocalsCount; i6++) {
                    emitVerificationType(this.encodedLocals[i6]);
                }
                put2(curStackCount);
                for (int i7 = 0; i7 < curStackCount; i7++) {
                    emitVerificationType(this.encodedStack[i7]);
                }
            } else {
                put1(Telnet.WILL + localsDelta);
                put2(offset_delta);
                for (int i8 = 0; i8 < localsDelta; i8++) {
                    emitVerificationType(this.encodedLocals[unchangedLocals + i8]);
                }
            }
        } else if (curStackCount != 0) {
            if (offset_delta <= 63) {
                put1(64 + offset_delta);
            } else {
                put1(247);
                put2(offset_delta);
            }
            emitVerificationType(this.encodedStack[0]);
        } else if (offset_delta <= 63) {
            put1(offset_delta);
        } else {
            put1(Telnet.WILL);
            put2(offset_delta);
        }
        this.countLocals = curLocalsCount;
        this.countStack = curStackCount;
        this.prevPosition = label2.position;
        this.numEntries++;
    }

    /* access modifiers changed from: package-private */
    public void printVerificationType(int i, ClassTypeWriter classTypeWriter) {
        StringBuilder sb;
        int encoding = i;
        ClassTypeWriter dst = classTypeWriter;
        int tag = encoding & 255;
        switch (tag) {
            case 0:
                dst.print("top/unavailable");
                return;
            case 1:
                dst.print(PropertyTypeConstants.PROPERTY_TYPE_INTEGER);
                return;
            case 2:
                dst.print(PropertyTypeConstants.PROPERTY_TYPE_FLOAT);
                return;
            case 3:
                dst.print("double");
                return;
            case 4:
                dst.print("long");
                return;
            case 5:
                dst.print("null");
                return;
            case 6:
                dst.print("uninitialized this");
                return;
            case 7:
                int index = encoding >> 8;
                dst.printOptionalIndex(index);
                dst.printConstantTersely(index, 7);
                return;
            case 8:
                dst.print("uninitialized object created at ");
                dst.print(encoding >> 8);
                return;
            default:
                new StringBuilder();
                dst.print(sb.append("<bad verification type tag ").append(tag).append('>').toString());
                return;
        }
    }

    /* access modifiers changed from: package-private */
    public int extractVerificationType(int i, int i2) {
        int startOffset = i;
        int tag = i2;
        if (tag == 7 || tag == 8) {
            tag |= mo15179u2(startOffset + 1) << 8;
        }
        return tag;
    }

    static int[] reallocBuffer(int[] iArr, int i) {
        int[] buffer = iArr;
        int needed = i;
        if (buffer == null) {
            buffer = new int[(needed + 10)];
        } else if (needed > buffer.length) {
            int[] tmp = new int[(needed + 10)];
            System.arraycopy(buffer, 0, tmp, 0, buffer.length);
            buffer = tmp;
        }
        return buffer;
    }

    /* access modifiers changed from: package-private */
    public int extractVerificationTypes(int startOffset, int i, int i2, int[] iArr) {
        int encoding;
        int count = i;
        int startIndex = i2;
        int[] buffer = iArr;
        int offset = startOffset;
        while (true) {
            count--;
            if (count < 0) {
                return offset;
            }
            if (offset >= this.dataLength) {
                encoding = -1;
            } else {
                byte b = this.data[offset];
                encoding = extractVerificationType(offset, b);
                offset += (b == 7 || b == 8) ? 3 : 1;
            }
            int i3 = startIndex;
            startIndex++;
            buffer[i3] = encoding;
        }
    }

    /* access modifiers changed from: package-private */
    public void printVerificationTypes(int[] iArr, int i, int i2, ClassTypeWriter classTypeWriter) {
        int[] encodings = iArr;
        int startIndex = i;
        int count = i2;
        ClassTypeWriter dst = classTypeWriter;
        int regno = 0;
        for (int i3 = 0; i3 < startIndex + count; i3++) {
            int encoding = encodings[i3];
            int tag = encoding & 255;
            if (i3 >= startIndex) {
                dst.print("  ");
                if (regno < 100) {
                    if (regno < 10) {
                        dst.print(' ');
                    }
                    dst.print(' ');
                }
                dst.print(regno);
                dst.print(": ");
                printVerificationType(encoding, dst);
                dst.println();
            }
            regno++;
            if (tag == 3 || tag == 4) {
                regno++;
            }
        }
    }

    public void print(ClassTypeWriter classTypeWriter) {
        ClassTypeWriter dst = classTypeWriter;
        dst.print("Attribute \"");
        dst.print(getName());
        dst.print("\", length:");
        dst.print(getLength());
        dst.print(", number of entries: ");
        dst.println(this.numEntries);
        int ipos = 2;
        int pc_offset = -1;
        Method method = getMethod();
        int[] encodedTypes = null;
        int curLocals = (method.getStaticFlag() ? 0 : 1) + method.arg_types.length;
        int i = 0;
        while (true) {
            if (i < this.numEntries) {
                if (ipos < this.dataLength) {
                    int i2 = ipos;
                    ipos++;
                    int tag = mo15177u1(i2);
                    int pc_offset2 = pc_offset + 1;
                    if (tag <= 127) {
                        pc_offset = pc_offset2 + (tag & 63);
                    } else {
                        if (ipos + 1 < this.dataLength) {
                            pc_offset = pc_offset2 + mo15179u2(ipos);
                            ipos += 2;
                        } else {
                            return;
                        }
                    }
                    dst.print("  offset: ");
                    dst.print(pc_offset);
                    if (tag <= 63) {
                        dst.println(" - same_frame");
                    } else if (tag <= 127 || tag == 247) {
                        dst.println(tag <= 127 ? " - same_locals_1_stack_item_frame" : " - same_locals_1_stack_item_frame_extended");
                        encodedTypes = reallocBuffer(encodedTypes, 1);
                        ipos = extractVerificationTypes(ipos, 1, 0, encodedTypes);
                        printVerificationTypes(encodedTypes, 0, 1, dst);
                    } else if (tag <= 246) {
                        dst.print(" - tag reserved for future use - ");
                        dst.println(tag);
                        return;
                    } else if (tag <= 250) {
                        int count = 251 - tag;
                        dst.print(" - chop_frame - undefine ");
                        dst.print(count);
                        dst.println(" locals");
                        curLocals -= count;
                    } else if (tag == 251) {
                        dst.println(" - same_frame_extended");
                    } else if (tag <= 254) {
                        int count2 = tag - 251;
                        dst.print(" - append_frame - define ");
                        dst.print(count2);
                        dst.println(" more locals");
                        encodedTypes = reallocBuffer(encodedTypes, curLocals + count2);
                        ipos = extractVerificationTypes(ipos, count2, curLocals, encodedTypes);
                        printVerificationTypes(encodedTypes, curLocals, count2, dst);
                        curLocals += count2;
                    } else {
                        if (ipos + 1 < this.dataLength) {
                            int num_locals = mo15179u2(ipos);
                            dst.print(" - full_frame.  Locals count: ");
                            dst.println(num_locals);
                            int[] encodedTypes2 = reallocBuffer(encodedTypes, num_locals);
                            int ipos2 = extractVerificationTypes(ipos + 2, num_locals, 0, encodedTypes2);
                            printVerificationTypes(encodedTypes2, 0, num_locals, dst);
                            curLocals = num_locals;
                            if (ipos2 + 1 < this.dataLength) {
                                int num_stack = mo15179u2(ipos2);
                                int ipos3 = ipos2 + 2;
                                dst.print("    (end of locals)");
                                int nspaces = Integer.toString(pc_offset).length();
                                while (true) {
                                    nspaces--;
                                    if (nspaces < 0) {
                                        break;
                                    }
                                    dst.print(' ');
                                }
                                dst.print("       Stack count: ");
                                dst.println(num_stack);
                                encodedTypes = reallocBuffer(encodedTypes2, num_stack);
                                ipos = extractVerificationTypes(ipos3, num_stack, 0, encodedTypes);
                                printVerificationTypes(encodedTypes, 0, num_stack, dst);
                                int curStack = num_stack;
                            } else {
                                return;
                            }
                        } else {
                            return;
                        }
                    }
                    if (ipos < 0) {
                        dst.println("<ERROR - missing data>");
                        return;
                    }
                    i++;
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }
}

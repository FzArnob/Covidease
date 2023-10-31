package gnu.xquery.util;

import gnu.kawa.xml.KAttr;
import gnu.kawa.xml.KNode;
import gnu.kawa.xml.NodeType;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.xml.NodeTree;

public class SequenceUtils {
    public static final NodeType textOrElement;

    public SequenceUtils() {
    }

    public static boolean isZeroOrOne(Object obj) {
        Object arg = obj;
        return !(arg instanceof Values) || ((Values) arg).isEmpty();
    }

    static Object coerceToZeroOrOne(Object obj, String str, int i) {
        Throwable th;
        Object arg = obj;
        String functionName = str;
        int iarg = i;
        if (isZeroOrOne(arg)) {
            return arg;
        }
        Throwable th2 = th;
        new WrongType(functionName, iarg, arg, "xs:item()?");
        throw th2;
    }

    public static Object zeroOrOne(Object arg) {
        return coerceToZeroOrOne(arg, "zero-or-one", 1);
    }

    public static Object oneOrMore(Object obj) {
        Throwable th;
        Object arg = obj;
        if (!(arg instanceof Values) || !((Values) arg).isEmpty()) {
            return arg;
        }
        Throwable th2 = th;
        new IllegalArgumentException();
        throw th2;
    }

    public static Object exactlyOne(Object obj) {
        Throwable th;
        Object arg = obj;
        if (!(arg instanceof Values)) {
            return arg;
        }
        Throwable th2 = th;
        new IllegalArgumentException();
        throw th2;
    }

    public static boolean isEmptySequence(Object obj) {
        Object arg = obj;
        return (arg instanceof Values) && ((Values) arg).isEmpty();
    }

    public static boolean exists(Object obj) {
        Object arg = obj;
        return !(arg instanceof Values) || !((Values) arg).isEmpty();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0041, code lost:
        if (r16 == r1) goto L_0x0043;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void insertBefore$X(java.lang.Object r19, long r20, java.lang.Object r22, gnu.mapping.CallContext r23) {
        /*
            r0 = r19
            r1 = r20
            r3 = r22
            r4 = r23
            r12 = r4
            gnu.lists.Consumer r12 = r12.consumer
            r5 = r12
            r12 = 0
            r6 = r12
            r12 = r1
            r14 = 0
            int r12 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r12 > 0) goto L_0x0018
            r12 = 1
            r1 = r12
        L_0x0018:
            r12 = r0
            boolean r12 = r12 instanceof gnu.mapping.Values
            if (r12 == 0) goto L_0x0058
            r12 = r0
            gnu.mapping.Values r12 = (gnu.mapping.Values) r12
            r7 = r12
            r12 = 0
            r8 = r12
            r12 = 0
            r9 = r12
        L_0x0026:
            r12 = r7
            r13 = r8
            int r12 = r12.nextPos(r13)
            r11 = r12
            r12 = r11
            if (r12 != 0) goto L_0x0033
            r12 = r6
            if (r12 == 0) goto L_0x0043
        L_0x0033:
            r12 = r9
            r14 = 1
            long r12 = r12 + r14
            r16 = r12
            r12 = r16
            r14 = r16
            r9 = r14
            r14 = r1
            int r12 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r12 != 0) goto L_0x004a
        L_0x0043:
            r12 = r3
            r13 = r5
            gnu.mapping.Values.writeValues(r12, r13)
            r12 = 1
            r6 = r12
        L_0x004a:
            r12 = r11
            if (r12 != 0) goto L_0x004e
        L_0x004d:
            return
        L_0x004e:
            r12 = r7
            r13 = r8
            r14 = r11
            r15 = r5
            r12.consumePosRange(r13, r14, r15)
            r12 = r11
            r8 = r12
            goto L_0x0026
        L_0x0058:
            r12 = r1
            r14 = 1
            int r12 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r12 > 0) goto L_0x0064
            r12 = r3
            r13 = r5
            gnu.mapping.Values.writeValues(r12, r13)
        L_0x0064:
            r12 = r5
            r13 = r0
            r12.writeObject(r13)
            r12 = r1
            r14 = 1
            int r12 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r12 <= 0) goto L_0x004d
            r12 = r3
            r13 = r5
            gnu.mapping.Values.writeValues(r12, r13)
            goto L_0x004d
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xquery.util.SequenceUtils.insertBefore$X(java.lang.Object, long, java.lang.Object, gnu.mapping.CallContext):void");
    }

    public static void remove$X(Object obj, long j, CallContext ctx) {
        Object arg = obj;
        long position = j;
        Consumer out = ctx.consumer;
        if (arg instanceof Values) {
            Values values = (Values) arg;
            int ipos = 0;
            long i = 0;
            while (true) {
                int next = values.nextPos(ipos);
                if (next != 0) {
                    long j2 = i + 1;
                    i = j2;
                    if (j2 != position) {
                        values.consumePosRange(ipos, next, out);
                    }
                    ipos = next;
                } else {
                    return;
                }
            }
        } else if (position != 1) {
            out.writeObject(arg);
        }
    }

    public static void reverse$X(Object obj, CallContext ctx) {
        Object arg = obj;
        Consumer out = ctx.consumer;
        if (!(arg instanceof Values)) {
            out.writeObject(arg);
            return;
        }
        Values vals = (Values) arg;
        int ipos = 0;
        int[] poses = new int[100];
        int n = 0;
        do {
            if (n >= poses.length) {
                int[] t = new int[(2 * n)];
                System.arraycopy(poses, 0, t, 0, n);
                poses = t;
            }
            int i = n;
            n++;
            poses[i] = ipos;
            ipos = vals.nextPos(ipos);
        } while (ipos != 0);
        int i2 = n - 1;
        while (true) {
            i2--;
            if (i2 >= 0) {
                vals.consumePosRange(poses[i2], poses[i2 + 1], out);
            } else {
                return;
            }
        }
    }

    public static void indexOf$X(Object obj, Object obj2, NamedCollator namedCollator, CallContext ctx) {
        Object seqParam = obj;
        Object srchParam = obj2;
        NamedCollator collator = namedCollator;
        Consumer out = ctx.consumer;
        if (seqParam instanceof Values) {
            Values vals = (Values) seqParam;
            int ipos = vals.startPos();
            int i = 1;
            while (true) {
                int nextPos = vals.nextPos(ipos);
                ipos = nextPos;
                if (nextPos != 0) {
                    if (Compare.apply(72, vals.getPosPrevious(ipos), srchParam, collator)) {
                        out.writeInt(i);
                    }
                    i++;
                } else {
                    return;
                }
            }
        } else if (Compare.apply(72, seqParam, srchParam, collator)) {
            out.writeInt(1);
        }
    }

    static {
        NodeType nodeType;
        new NodeType("element-or-text", 3);
        textOrElement = nodeType;
    }

    public static boolean deepEqualChildren(NodeTree nodeTree, int ipos1, NodeTree nodeTree2, int ipos2, NamedCollator namedCollator) {
        int child2;
        NodeTree seq1 = nodeTree;
        NodeTree seq2 = nodeTree2;
        NamedCollator collator = namedCollator;
        NodeType filter = textOrElement;
        int child1 = seq1.firstChildPos(ipos1, filter);
        int firstChildPos = seq2.firstChildPos(ipos2, filter);
        while (true) {
            child2 = firstChildPos;
            if (child1 != 0 && child2 != 0) {
                if (!deepEqual(seq1, child1, seq2, child2, collator)) {
                    return false;
                }
                child1 = seq1.nextMatching(child1, filter, -1, false);
                firstChildPos = seq2.nextMatching(child2, filter, -1, false);
            }
        }
        return child1 == child2;
    }

    public static boolean deepEqual(NodeTree nodeTree, int i, NodeTree nodeTree2, int i2, NamedCollator namedCollator) {
        NodeTree seq1 = nodeTree;
        int ipos1 = i;
        NodeTree seq2 = nodeTree2;
        int ipos2 = i2;
        NamedCollator collator = namedCollator;
        int kind1 = seq1.getNextKind(ipos1);
        int kind2 = seq2.getNextKind(ipos2);
        switch (kind1) {
            case 33:
                if (kind1 != kind2) {
                    return false;
                }
                if (seq1.posLocalName(ipos1) != seq2.posLocalName(ipos2)) {
                    return false;
                }
                if (seq1.posNamespaceURI(ipos1) != seq2.posNamespaceURI(ipos2)) {
                    return false;
                }
                int attr1 = seq1.firstAttributePos(ipos1);
                int nattr1 = 0;
                while (attr1 != 0 && seq1.getNextKind(attr1) == 35) {
                    nattr1++;
                    int attr2 = seq2.getAttributeI(ipos2, seq1.posNamespaceURI(attr1), seq1.posLocalName(attr1));
                    if (attr2 == 0) {
                        return false;
                    }
                    if (!deepEqualItems(KNode.getNodeValue(seq1, attr1), KNode.getNodeValue(seq2, attr2), collator)) {
                        return false;
                    }
                    attr1 = seq1.nextPos(attr1);
                }
                if (nattr1 != seq2.getAttributeCount(ipos2)) {
                    return false;
                }
                break;
            case 34:
                break;
            case 35:
                if (seq1.posLocalName(ipos1) == seq2.posLocalName(ipos2) && seq1.posNamespaceURI(ipos1) == seq2.posNamespaceURI(ipos2)) {
                    return deepEqualItems(KAttr.getObjectValue(seq1, ipos1), KAttr.getObjectValue(seq2, ipos2), collator);
                }
                return false;
            case 37:
                if (!seq1.posTarget(ipos1).equals(seq2.posTarget(ipos2))) {
                    return false;
                }
                return KNode.getNodeValue(seq1, ipos1).equals(KNode.getNodeValue(seq2, ipos2));
            default:
                if (kind1 != kind2) {
                    return false;
                }
                return KNode.getNodeValue(seq1, ipos1).equals(KNode.getNodeValue(seq2, ipos2));
        }
        return deepEqualChildren(seq1, ipos1, seq2, ipos2, collator);
    }

    public static boolean deepEqualItems(Object obj, Object obj2, NamedCollator namedCollator) {
        Object arg1 = obj;
        Object arg2 = obj2;
        NamedCollator collator = namedCollator;
        if (!NumberValue.isNaN(arg1) || !NumberValue.isNaN(arg2)) {
            return Compare.atomicCompare(8, arg1, arg2, collator);
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:101:?, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean deepEqual(java.lang.Object r20, java.lang.Object r21, gnu.xquery.util.NamedCollator r22) {
        /*
            r1 = r20
            r2 = r21
            r3 = r22
            r15 = r1
            r16 = r2
            r0 = r16
            if (r15 != r0) goto L_0x0010
            r15 = 1
            r1 = r15
        L_0x000f:
            return r1
        L_0x0010:
            r15 = r1
            if (r15 == 0) goto L_0x001a
            r15 = r1
            gnu.mapping.Values r16 = gnu.mapping.Values.empty
            r0 = r16
            if (r15 != r0) goto L_0x0029
        L_0x001a:
            r15 = r2
            if (r15 == 0) goto L_0x0024
            r15 = r2
            gnu.mapping.Values r16 = gnu.mapping.Values.empty
            r0 = r16
            if (r15 != r0) goto L_0x0027
        L_0x0024:
            r15 = 1
        L_0x0025:
            r1 = r15
            goto L_0x000f
        L_0x0027:
            r15 = 0
            goto L_0x0025
        L_0x0029:
            r15 = r2
            if (r15 == 0) goto L_0x0033
            r15 = r2
            gnu.mapping.Values r16 = gnu.mapping.Values.empty
            r0 = r16
            if (r15 != r0) goto L_0x0036
        L_0x0033:
            r15 = 0
            r1 = r15
            goto L_0x000f
        L_0x0036:
            r15 = 1
            r4 = r15
            r15 = 1
            r5 = r15
            r15 = r1
            boolean r15 = r15 instanceof gnu.mapping.Values
            r6 = r15
            r15 = r2
            boolean r15 = r15 instanceof gnu.mapping.Values
            r7 = r15
            r15 = r6
            if (r15 == 0) goto L_0x008a
            r15 = r1
            gnu.mapping.Values r15 = (gnu.mapping.Values) r15
        L_0x0048:
            r8 = r15
            r15 = r7
            if (r15 == 0) goto L_0x008c
            r15 = r2
            gnu.mapping.Values r15 = (gnu.mapping.Values) r15
        L_0x004f:
            r9 = r15
            r15 = 1
            r10 = r15
        L_0x0052:
            r15 = r6
            if (r15 == 0) goto L_0x0066
            r15 = r10
            if (r15 == 0) goto L_0x005e
            r15 = r8
            int r15 = r15.startPos()
            r4 = r15
        L_0x005e:
            r15 = r8
            r16 = r4
            int r15 = r15.nextPos(r16)
            r4 = r15
        L_0x0066:
            r15 = r7
            if (r15 == 0) goto L_0x007a
            r15 = r10
            if (r15 == 0) goto L_0x0072
            r15 = r9
            int r15 = r15.startPos()
            r5 = r15
        L_0x0072:
            r15 = r9
            r16 = r5
            int r15 = r15.nextPos(r16)
            r5 = r15
        L_0x007a:
            r15 = r4
            if (r15 == 0) goto L_0x0080
            r15 = r5
            if (r15 != 0) goto L_0x0090
        L_0x0080:
            r15 = r4
            r16 = r5
            r0 = r16
            if (r15 != r0) goto L_0x008e
            r15 = 1
        L_0x0088:
            r1 = r15
            goto L_0x000f
        L_0x008a:
            r15 = 0
            goto L_0x0048
        L_0x008c:
            r15 = 0
            goto L_0x004f
        L_0x008e:
            r15 = 0
            goto L_0x0088
        L_0x0090:
            r15 = r6
            if (r15 == 0) goto L_0x00bf
            r15 = r8
            r16 = r4
            java.lang.Object r15 = r15.getPosPrevious(r16)
        L_0x009a:
            r11 = r15
            r15 = r7
            if (r15 == 0) goto L_0x00c1
            r15 = r9
            r16 = r5
            java.lang.Object r15 = r15.getPosPrevious(r16)
        L_0x00a5:
            r12 = r15
            r15 = r11
            boolean r15 = r15 instanceof gnu.kawa.xml.KNode
            if (r15 != 0) goto L_0x00d9
            r15 = r12
            boolean r15 = r15 instanceof gnu.kawa.xml.KNode
            if (r15 != 0) goto L_0x00d9
            r15 = r1
            r16 = r2
            r17 = r3
            boolean r15 = deepEqualItems(r15, r16, r17)     // Catch:{ Throwable -> 0x00d3 }
            if (r15 != 0) goto L_0x00c3
            r15 = 0
            r1 = r15
            goto L_0x000f
        L_0x00bf:
            r15 = r1
            goto L_0x009a
        L_0x00c1:
            r15 = r2
            goto L_0x00a5
        L_0x00c3:
            r15 = r10
            if (r15 == 0) goto L_0x00d2
            r15 = 0
            r10 = r15
            r15 = r6
            if (r15 != 0) goto L_0x00cd
            r15 = 0
            r4 = r15
        L_0x00cd:
            r15 = r7
            if (r15 != 0) goto L_0x00d2
            r15 = 0
            r5 = r15
        L_0x00d2:
            goto L_0x0052
        L_0x00d3:
            r15 = move-exception
            r13 = r15
            r15 = 0
            r1 = r15
            goto L_0x000f
        L_0x00d9:
            r15 = r11
            boolean r15 = r15 instanceof gnu.kawa.xml.KNode
            if (r15 == 0) goto L_0x0117
            r15 = r12
            boolean r15 = r15 instanceof gnu.kawa.xml.KNode
            if (r15 == 0) goto L_0x0117
            r15 = r11
            gnu.kawa.xml.KNode r15 = (gnu.kawa.xml.KNode) r15
            r13 = r15
            r15 = r12
            gnu.kawa.xml.KNode r15 = (gnu.kawa.xml.KNode) r15
            r14 = r15
            r15 = r13
            gnu.lists.AbstractSequence r15 = r15.sequence
            gnu.xml.NodeTree r15 = (gnu.xml.NodeTree) r15
            r16 = r13
            r0 = r16
            int r0 = r0.ipos
            r16 = r0
            r17 = r14
            r0 = r17
            gnu.lists.AbstractSequence r0 = r0.sequence
            r17 = r0
            gnu.xml.NodeTree r17 = (gnu.xml.NodeTree) r17
            r18 = r14
            r0 = r18
            int r0 = r0.ipos
            r18 = r0
            r19 = r3
            boolean r15 = deepEqual(r15, r16, r17, r18, r19)
            if (r15 != 0) goto L_0x0116
            r15 = 0
            r1 = r15
            goto L_0x000f
        L_0x0116:
            goto L_0x00c3
        L_0x0117:
            r15 = 0
            r1 = r15
            goto L_0x000f
        */
        throw new UnsupportedOperationException("Method not decompiled: gnu.xquery.util.SequenceUtils.deepEqual(java.lang.Object, java.lang.Object, gnu.xquery.util.NamedCollator):boolean");
    }
}

package android.support.p000v4.graphics;

import android.graphics.Path;
import android.support.annotation.RestrictTo;
import android.util.Log;
import java.util.ArrayList;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v4.graphics.PathParser */
public class PathParser {
    private static final String LOGTAG = "PathParser";

    static float[] copyOfRange(float[] fArr, int i, int i2) {
        Throwable th;
        Throwable th2;
        float[] original = fArr;
        int start = i;
        int end = i2;
        if (start > end) {
            Throwable th3 = th2;
            new IllegalArgumentException();
            throw th3;
        }
        int originalLength = original.length;
        if (start < 0 || start > originalLength) {
            Throwable th4 = th;
            new ArrayIndexOutOfBoundsException();
            throw th4;
        }
        int resultLength = end - start;
        float[] result = new float[resultLength];
        System.arraycopy(original, start, result, 0, Math.min(resultLength, originalLength - start));
        return result;
    }

    public static Path createPathFromPathData(String str) {
        Path path;
        Throwable th;
        StringBuilder sb;
        String pathData = str;
        new Path();
        Path path2 = path;
        PathDataNode[] nodes = createNodesFromPathData(pathData);
        if (nodes == null) {
            return null;
        }
        try {
            PathDataNode.nodesToPath(nodes, path2);
            return path2;
        } catch (RuntimeException e) {
            RuntimeException e2 = e;
            Throwable th2 = th;
            new StringBuilder();
            new RuntimeException(sb.append("Error in parsing ").append(pathData).toString(), e2);
            throw th2;
        }
    }

    public static PathDataNode[] createNodesFromPathData(String str) {
        ArrayList arrayList;
        String pathData = str;
        if (pathData == null) {
            return null;
        }
        int start = 0;
        int end = 1;
        new ArrayList();
        ArrayList arrayList2 = arrayList;
        while (end < pathData.length()) {
            int end2 = nextStart(pathData, end);
            String s = pathData.substring(start, end2).trim();
            if (s.length() > 0) {
                addNode(arrayList2, s.charAt(0), getFloats(s));
            }
            start = end2;
            end = end2 + 1;
        }
        if (end - start == 1 && start < pathData.length()) {
            addNode(arrayList2, pathData.charAt(start), new float[0]);
        }
        return (PathDataNode[]) arrayList2.toArray(new PathDataNode[arrayList2.size()]);
    }

    public static PathDataNode[] deepCopyNodes(PathDataNode[] pathDataNodeArr) {
        PathDataNode pathDataNode;
        PathDataNode[] source = pathDataNodeArr;
        if (source == null) {
            return null;
        }
        PathDataNode[] copy = new PathDataNode[source.length];
        for (int i = 0; i < source.length; i++) {
            new PathDataNode(source[i]);
            copy[i] = pathDataNode;
        }
        return copy;
    }

    public static boolean canMorph(PathDataNode[] pathDataNodeArr, PathDataNode[] pathDataNodeArr2) {
        PathDataNode[] nodesFrom = pathDataNodeArr;
        PathDataNode[] nodesTo = pathDataNodeArr2;
        if (nodesFrom == null || nodesTo == null) {
            return false;
        }
        if (nodesFrom.length != nodesTo.length) {
            return false;
        }
        for (int i = 0; i < nodesFrom.length; i++) {
            if (nodesFrom[i].mType != nodesTo[i].mType || nodesFrom[i].mParams.length != nodesTo[i].mParams.length) {
                return false;
            }
        }
        return true;
    }

    public static void updateNodes(PathDataNode[] pathDataNodeArr, PathDataNode[] pathDataNodeArr2) {
        PathDataNode[] target = pathDataNodeArr;
        PathDataNode[] source = pathDataNodeArr2;
        for (int i = 0; i < source.length; i++) {
            target[i].mType = source[i].mType;
            for (int j = 0; j < source[i].mParams.length; j++) {
                target[i].mParams[j] = source[i].mParams[j];
            }
        }
    }

    private static int nextStart(String str, int i) {
        String s = str;
        int end = i;
        while (end < s.length()) {
            char c = s.charAt(end);
            if (((c - 'A') * (c - 'Z') <= 0 || (c - 'a') * (c - 'z') <= 0) && c != 'e' && c != 'E') {
                return end;
            }
            end++;
        }
        return end;
    }

    private static void addNode(ArrayList<PathDataNode> list, char cmd, float[] val) {
        Object obj;
        new PathDataNode(cmd, val);
        boolean add = list.add(obj);
    }

    /* renamed from: android.support.v4.graphics.PathParser$ExtractFloatResult */
    private static class ExtractFloatResult {
        int mEndPosition;
        boolean mEndWithNegOrDot;

        ExtractFloatResult() {
        }
    }

    private static float[] getFloats(String str) {
        Throwable th;
        StringBuilder sb;
        ExtractFloatResult extractFloatResult;
        String s = str;
        if (s.charAt(0) == 'z' || s.charAt(0) == 'Z') {
            return new float[0];
        }
        try {
            float[] results = new float[s.length()];
            int count = 0;
            int startPosition = 1;
            new ExtractFloatResult();
            ExtractFloatResult result = extractFloatResult;
            int totalLength = s.length();
            while (startPosition < totalLength) {
                extract(s, startPosition, result);
                int endPosition = result.mEndPosition;
                if (startPosition < endPosition) {
                    int i = count;
                    count++;
                    results[i] = Float.parseFloat(s.substring(startPosition, endPosition));
                }
                if (result.mEndWithNegOrDot) {
                    startPosition = endPosition;
                } else {
                    startPosition = endPosition + 1;
                }
            }
            return copyOfRange(results, 0, count);
        } catch (NumberFormatException e) {
            NumberFormatException e2 = e;
            Throwable th2 = th;
            new StringBuilder();
            new RuntimeException(sb.append("error in parsing \"").append(s).append("\"").toString(), e2);
            throw th2;
        }
    }

    private static void extract(String str, int i, ExtractFloatResult extractFloatResult) {
        String s = str;
        int start = i;
        ExtractFloatResult result = extractFloatResult;
        boolean foundSeparator = false;
        result.mEndWithNegOrDot = false;
        boolean secondDot = false;
        boolean isExponential = false;
        for (int currentIndex = start; currentIndex < s.length(); currentIndex++) {
            boolean isPrevExponential = isExponential;
            isExponential = false;
            switch (s.charAt(currentIndex)) {
                case ' ':
                case ',':
                    foundSeparator = true;
                    break;
                case '-':
                    if (currentIndex != start && !isPrevExponential) {
                        foundSeparator = true;
                        result.mEndWithNegOrDot = true;
                        break;
                    }
                case '.':
                    if (secondDot) {
                        foundSeparator = true;
                        result.mEndWithNegOrDot = true;
                        break;
                    } else {
                        secondDot = true;
                        break;
                    }
                case 'E':
                case 'e':
                    isExponential = true;
                    break;
            }
            if (foundSeparator) {
                result.mEndPosition = currentIndex;
            }
        }
        result.mEndPosition = currentIndex;
    }

    /* renamed from: android.support.v4.graphics.PathParser$PathDataNode */
    public static class PathDataNode {
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public float[] mParams;
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public char mType;

        PathDataNode(char type, float[] params) {
            this.mType = type;
            this.mParams = params;
        }

        PathDataNode(PathDataNode pathDataNode) {
            PathDataNode n = pathDataNode;
            this.mType = n.mType;
            this.mParams = PathParser.copyOfRange(n.mParams, 0, n.mParams.length);
        }

        public static void nodesToPath(PathDataNode[] pathDataNodeArr, Path path) {
            PathDataNode[] node = pathDataNodeArr;
            Path path2 = path;
            float[] current = new float[6];
            char previousCommand = 'm';
            for (int i = 0; i < node.length; i++) {
                addCommand(path2, current, previousCommand, node[i].mType, node[i].mParams);
                previousCommand = node[i].mType;
            }
        }

        public void interpolatePathDataNode(PathDataNode pathDataNode, PathDataNode pathDataNode2, float f) {
            PathDataNode nodeFrom = pathDataNode;
            PathDataNode nodeTo = pathDataNode2;
            float fraction = f;
            for (int i = 0; i < nodeFrom.mParams.length; i++) {
                this.mParams[i] = (nodeFrom.mParams[i] * (1.0f - fraction)) + (nodeTo.mParams[i] * fraction);
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v0, resolved type: int} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v0, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v0, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v0, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v0, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v1, resolved type: int} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: int} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: int} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v4, resolved type: int} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v5, resolved type: int} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v1, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v1, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v2, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v2, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v2, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v2, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v6, resolved type: int} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v3, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v3, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v3, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: java.lang.Object[]} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v4, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v4, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v5, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v5, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v1, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v1, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v6, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v6, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v6, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v6, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v4, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v4, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v7, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v7, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v8, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v8, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v8, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v8, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v9, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v9, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v7, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v7, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v10, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v10, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v10, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v10, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v10, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v10, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v11, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v11, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v12, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v12, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v12, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v12, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v13, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v13, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v14, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v14, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v16, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v16, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v18, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v18, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v3, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v3, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v4, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v4, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v14, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v14, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v20, resolved type: float} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v20, resolved type: float} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static void addCommand(android.graphics.Path r29, float[] r30, char r31, char r32, float[] r33) {
            /*
                r2 = r29
                r3 = r30
                r4 = r31
                r5 = r32
                r6 = r33
                r17 = 2
                r7 = r17
                r17 = r3
                r18 = 0
                r17 = r17[r18]
                r8 = r17
                r17 = r3
                r18 = 1
                r17 = r17[r18]
                r9 = r17
                r17 = r3
                r18 = 2
                r17 = r17[r18]
                r10 = r17
                r17 = r3
                r18 = 3
                r17 = r17[r18]
                r11 = r17
                r17 = r3
                r18 = 4
                r17 = r17[r18]
                r12 = r17
                r17 = r3
                r18 = 5
                r17 = r17[r18]
                r13 = r17
                r17 = r5
                switch(r17) {
                    case 65: goto L_0x009b;
                    case 67: goto L_0x0091;
                    case 72: goto L_0x008c;
                    case 76: goto L_0x0087;
                    case 77: goto L_0x0087;
                    case 81: goto L_0x0096;
                    case 83: goto L_0x0096;
                    case 84: goto L_0x0087;
                    case 86: goto L_0x008c;
                    case 90: goto L_0x0068;
                    case 97: goto L_0x009b;
                    case 99: goto L_0x0091;
                    case 104: goto L_0x008c;
                    case 108: goto L_0x0087;
                    case 109: goto L_0x0087;
                    case 113: goto L_0x0096;
                    case 115: goto L_0x0096;
                    case 116: goto L_0x0087;
                    case 118: goto L_0x008c;
                    case 122: goto L_0x0068;
                    default: goto L_0x0043;
                }
            L_0x0043:
                r17 = 0
                r16 = r17
            L_0x0047:
                r17 = r16
                r18 = r6
                r0 = r18
                int r0 = r0.length
                r18 = r0
                r0 = r17
                r1 = r18
                if (r0 >= r1) goto L_0x07a4
                r17 = r5
                switch(r17) {
                    case 65: goto L_0x071d;
                    case 67: goto L_0x02d1;
                    case 72: goto L_0x01ed;
                    case 76: goto L_0x0197;
                    case 77: goto L_0x0102;
                    case 81: goto L_0x0511;
                    case 83: goto L_0x03f7;
                    case 84: goto L_0x05ff;
                    case 86: goto L_0x022f;
                    case 97: goto L_0x0686;
                    case 99: goto L_0x024e;
                    case 104: goto L_0x01ca;
                    case 108: goto L_0x015c;
                    case 109: goto L_0x00a0;
                    case 113: goto L_0x04a2;
                    case 115: goto L_0x0344;
                    case 116: goto L_0x0570;
                    case 118: goto L_0x020c;
                    default: goto L_0x005b;
                }
            L_0x005b:
                r17 = r5
                r4 = r17
                r17 = r16
                r18 = r7
                int r17 = r17 + r18
                r16 = r17
                goto L_0x0047
            L_0x0068:
                r17 = r2
                r17.close()
                r17 = r12
                r8 = r17
                r17 = r13
                r9 = r17
                r17 = r12
                r10 = r17
                r17 = r13
                r11 = r17
                r17 = r2
                r18 = r8
                r19 = r9
                r17.moveTo(r18, r19)
                goto L_0x0043
            L_0x0087:
                r17 = 2
                r7 = r17
                goto L_0x0043
            L_0x008c:
                r17 = 1
                r7 = r17
                goto L_0x0043
            L_0x0091:
                r17 = 6
                r7 = r17
                goto L_0x0043
            L_0x0096:
                r17 = 4
                r7 = r17
                goto L_0x0043
            L_0x009b:
                r17 = 7
                r7 = r17
                goto L_0x0043
            L_0x00a0:
                r17 = r8
                r18 = r6
                r19 = r16
                r20 = 0
                int r19 = r19 + 0
                r18 = r18[r19]
                float r17 = r17 + r18
                r8 = r17
                r17 = r9
                r18 = r6
                r19 = r16
                r20 = 1
                int r19 = r19 + 1
                r18 = r18[r19]
                float r17 = r17 + r18
                r9 = r17
                r17 = r16
                if (r17 <= 0) goto L_0x00df
                r17 = r2
                r18 = r6
                r19 = r16
                r20 = 0
                int r19 = r19 + 0
                r18 = r18[r19]
                r19 = r6
                r20 = r16
                r21 = 1
                int r20 = r20 + 1
                r19 = r19[r20]
                r17.rLineTo(r18, r19)
                goto L_0x005b
            L_0x00df:
                r17 = r2
                r18 = r6
                r19 = r16
                r20 = 0
                int r19 = r19 + 0
                r18 = r18[r19]
                r19 = r6
                r20 = r16
                r21 = 1
                int r20 = r20 + 1
                r19 = r19[r20]
                r17.rMoveTo(r18, r19)
                r17 = r8
                r12 = r17
                r17 = r9
                r13 = r17
                goto L_0x005b
            L_0x0102:
                r17 = r6
                r18 = r16
                r19 = 0
                int r18 = r18 + 0
                r17 = r17[r18]
                r8 = r17
                r17 = r6
                r18 = r16
                r19 = 1
                int r18 = r18 + 1
                r17 = r17[r18]
                r9 = r17
                r17 = r16
                if (r17 <= 0) goto L_0x0139
                r17 = r2
                r18 = r6
                r19 = r16
                r20 = 0
                int r19 = r19 + 0
                r18 = r18[r19]
                r19 = r6
                r20 = r16
                r21 = 1
                int r20 = r20 + 1
                r19 = r19[r20]
                r17.lineTo(r18, r19)
                goto L_0x005b
            L_0x0139:
                r17 = r2
                r18 = r6
                r19 = r16
                r20 = 0
                int r19 = r19 + 0
                r18 = r18[r19]
                r19 = r6
                r20 = r16
                r21 = 1
                int r20 = r20 + 1
                r19 = r19[r20]
                r17.moveTo(r18, r19)
                r17 = r8
                r12 = r17
                r17 = r9
                r13 = r17
                goto L_0x005b
            L_0x015c:
                r17 = r2
                r18 = r6
                r19 = r16
                r20 = 0
                int r19 = r19 + 0
                r18 = r18[r19]
                r19 = r6
                r20 = r16
                r21 = 1
                int r20 = r20 + 1
                r19 = r19[r20]
                r17.rLineTo(r18, r19)
                r17 = r8
                r18 = r6
                r19 = r16
                r20 = 0
                int r19 = r19 + 0
                r18 = r18[r19]
                float r17 = r17 + r18
                r8 = r17
                r17 = r9
                r18 = r6
                r19 = r16
                r20 = 1
                int r19 = r19 + 1
                r18 = r18[r19]
                float r17 = r17 + r18
                r9 = r17
                goto L_0x005b
            L_0x0197:
                r17 = r2
                r18 = r6
                r19 = r16
                r20 = 0
                int r19 = r19 + 0
                r18 = r18[r19]
                r19 = r6
                r20 = r16
                r21 = 1
                int r20 = r20 + 1
                r19 = r19[r20]
                r17.lineTo(r18, r19)
                r17 = r6
                r18 = r16
                r19 = 0
                int r18 = r18 + 0
                r17 = r17[r18]
                r8 = r17
                r17 = r6
                r18 = r16
                r19 = 1
                int r18 = r18 + 1
                r17 = r17[r18]
                r9 = r17
                goto L_0x005b
            L_0x01ca:
                r17 = r2
                r18 = r6
                r19 = r16
                r20 = 0
                int r19 = r19 + 0
                r18 = r18[r19]
                r19 = 0
                r17.rLineTo(r18, r19)
                r17 = r8
                r18 = r6
                r19 = r16
                r20 = 0
                int r19 = r19 + 0
                r18 = r18[r19]
                float r17 = r17 + r18
                r8 = r17
                goto L_0x005b
            L_0x01ed:
                r17 = r2
                r18 = r6
                r19 = r16
                r20 = 0
                int r19 = r19 + 0
                r18 = r18[r19]
                r19 = r9
                r17.lineTo(r18, r19)
                r17 = r6
                r18 = r16
                r19 = 0
                int r18 = r18 + 0
                r17 = r17[r18]
                r8 = r17
                goto L_0x005b
            L_0x020c:
                r17 = r2
                r18 = 0
                r19 = r6
                r20 = r16
                r21 = 0
                int r20 = r20 + 0
                r19 = r19[r20]
                r17.rLineTo(r18, r19)
                r17 = r9
                r18 = r6
                r19 = r16
                r20 = 0
                int r19 = r19 + 0
                r18 = r18[r19]
                float r17 = r17 + r18
                r9 = r17
                goto L_0x005b
            L_0x022f:
                r17 = r2
                r18 = r8
                r19 = r6
                r20 = r16
                r21 = 0
                int r20 = r20 + 0
                r19 = r19[r20]
                r17.lineTo(r18, r19)
                r17 = r6
                r18 = r16
                r19 = 0
                int r18 = r18 + 0
                r17 = r17[r18]
                r9 = r17
                goto L_0x005b
            L_0x024e:
                r17 = r2
                r18 = r6
                r19 = r16
                r20 = 0
                int r19 = r19 + 0
                r18 = r18[r19]
                r19 = r6
                r20 = r16
                r21 = 1
                int r20 = r20 + 1
                r19 = r19[r20]
                r20 = r6
                r21 = r16
                r22 = 2
                int r21 = r21 + 2
                r20 = r20[r21]
                r21 = r6
                r22 = r16
                r23 = 3
                int r22 = r22 + 3
                r21 = r21[r22]
                r22 = r6
                r23 = r16
                r24 = 4
                int r23 = r23 + 4
                r22 = r22[r23]
                r23 = r6
                r24 = r16
                r25 = 5
                int r24 = r24 + 5
                r23 = r23[r24]
                r17.rCubicTo(r18, r19, r20, r21, r22, r23)
                r17 = r8
                r18 = r6
                r19 = r16
                r20 = 2
                int r19 = r19 + 2
                r18 = r18[r19]
                float r17 = r17 + r18
                r10 = r17
                r17 = r9
                r18 = r6
                r19 = r16
                r20 = 3
                int r19 = r19 + 3
                r18 = r18[r19]
                float r17 = r17 + r18
                r11 = r17
                r17 = r8
                r18 = r6
                r19 = r16
                r20 = 4
                int r19 = r19 + 4
                r18 = r18[r19]
                float r17 = r17 + r18
                r8 = r17
                r17 = r9
                r18 = r6
                r19 = r16
                r20 = 5
                int r19 = r19 + 5
                r18 = r18[r19]
                float r17 = r17 + r18
                r9 = r17
                goto L_0x005b
            L_0x02d1:
                r17 = r2
                r18 = r6
                r19 = r16
                r20 = 0
                int r19 = r19 + 0
                r18 = r18[r19]
                r19 = r6
                r20 = r16
                r21 = 1
                int r20 = r20 + 1
                r19 = r19[r20]
                r20 = r6
                r21 = r16
                r22 = 2
                int r21 = r21 + 2
                r20 = r20[r21]
                r21 = r6
                r22 = r16
                r23 = 3
                int r22 = r22 + 3
                r21 = r21[r22]
                r22 = r6
                r23 = r16
                r24 = 4
                int r23 = r23 + 4
                r22 = r22[r23]
                r23 = r6
                r24 = r16
                r25 = 5
                int r24 = r24 + 5
                r23 = r23[r24]
                r17.cubicTo(r18, r19, r20, r21, r22, r23)
                r17 = r6
                r18 = r16
                r19 = 4
                int r18 = r18 + 4
                r17 = r17[r18]
                r8 = r17
                r17 = r6
                r18 = r16
                r19 = 5
                int r18 = r18 + 5
                r17 = r17[r18]
                r9 = r17
                r17 = r6
                r18 = r16
                r19 = 2
                int r18 = r18 + 2
                r17 = r17[r18]
                r10 = r17
                r17 = r6
                r18 = r16
                r19 = 3
                int r18 = r18 + 3
                r17 = r17[r18]
                r11 = r17
                goto L_0x005b
            L_0x0344:
                r17 = 0
                r14 = r17
                r17 = 0
                r15 = r17
                r17 = r4
                r18 = 99
                r0 = r17
                r1 = r18
                if (r0 == r1) goto L_0x0374
                r17 = r4
                r18 = 115(0x73, float:1.61E-43)
                r0 = r17
                r1 = r18
                if (r0 == r1) goto L_0x0374
                r17 = r4
                r18 = 67
                r0 = r17
                r1 = r18
                if (r0 == r1) goto L_0x0374
                r17 = r4
                r18 = 83
                r0 = r17
                r1 = r18
                if (r0 != r1) goto L_0x0384
            L_0x0374:
                r17 = r8
                r18 = r10
                float r17 = r17 - r18
                r14 = r17
                r17 = r9
                r18 = r11
                float r17 = r17 - r18
                r15 = r17
            L_0x0384:
                r17 = r2
                r18 = r14
                r19 = r15
                r20 = r6
                r21 = r16
                r22 = 0
                int r21 = r21 + 0
                r20 = r20[r21]
                r21 = r6
                r22 = r16
                r23 = 1
                int r22 = r22 + 1
                r21 = r21[r22]
                r22 = r6
                r23 = r16
                r24 = 2
                int r23 = r23 + 2
                r22 = r22[r23]
                r23 = r6
                r24 = r16
                r25 = 3
                int r24 = r24 + 3
                r23 = r23[r24]
                r17.rCubicTo(r18, r19, r20, r21, r22, r23)
                r17 = r8
                r18 = r6
                r19 = r16
                r20 = 0
                int r19 = r19 + 0
                r18 = r18[r19]
                float r17 = r17 + r18
                r10 = r17
                r17 = r9
                r18 = r6
                r19 = r16
                r20 = 1
                int r19 = r19 + 1
                r18 = r18[r19]
                float r17 = r17 + r18
                r11 = r17
                r17 = r8
                r18 = r6
                r19 = r16
                r20 = 2
                int r19 = r19 + 2
                r18 = r18[r19]
                float r17 = r17 + r18
                r8 = r17
                r17 = r9
                r18 = r6
                r19 = r16
                r20 = 3
                int r19 = r19 + 3
                r18 = r18[r19]
                float r17 = r17 + r18
                r9 = r17
                goto L_0x005b
            L_0x03f7:
                r17 = r8
                r14 = r17
                r17 = r9
                r15 = r17
                r17 = r4
                r18 = 99
                r0 = r17
                r1 = r18
                if (r0 == r1) goto L_0x0427
                r17 = r4
                r18 = 115(0x73, float:1.61E-43)
                r0 = r17
                r1 = r18
                if (r0 == r1) goto L_0x0427
                r17 = r4
                r18 = 67
                r0 = r17
                r1 = r18
                if (r0 == r1) goto L_0x0427
                r17 = r4
                r18 = 83
                r0 = r17
                r1 = r18
                if (r0 != r1) goto L_0x043f
            L_0x0427:
                r17 = 1073741824(0x40000000, float:2.0)
                r18 = r8
                float r17 = r17 * r18
                r18 = r10
                float r17 = r17 - r18
                r14 = r17
                r17 = 1073741824(0x40000000, float:2.0)
                r18 = r9
                float r17 = r17 * r18
                r18 = r11
                float r17 = r17 - r18
                r15 = r17
            L_0x043f:
                r17 = r2
                r18 = r14
                r19 = r15
                r20 = r6
                r21 = r16
                r22 = 0
                int r21 = r21 + 0
                r20 = r20[r21]
                r21 = r6
                r22 = r16
                r23 = 1
                int r22 = r22 + 1
                r21 = r21[r22]
                r22 = r6
                r23 = r16
                r24 = 2
                int r23 = r23 + 2
                r22 = r22[r23]
                r23 = r6
                r24 = r16
                r25 = 3
                int r24 = r24 + 3
                r23 = r23[r24]
                r17.cubicTo(r18, r19, r20, r21, r22, r23)
                r17 = r6
                r18 = r16
                r19 = 0
                int r18 = r18 + 0
                r17 = r17[r18]
                r10 = r17
                r17 = r6
                r18 = r16
                r19 = 1
                int r18 = r18 + 1
                r17 = r17[r18]
                r11 = r17
                r17 = r6
                r18 = r16
                r19 = 2
                int r18 = r18 + 2
                r17 = r17[r18]
                r8 = r17
                r17 = r6
                r18 = r16
                r19 = 3
                int r18 = r18 + 3
                r17 = r17[r18]
                r9 = r17
                goto L_0x005b
            L_0x04a2:
                r17 = r2
                r18 = r6
                r19 = r16
                r20 = 0
                int r19 = r19 + 0
                r18 = r18[r19]
                r19 = r6
                r20 = r16
                r21 = 1
                int r20 = r20 + 1
                r19 = r19[r20]
                r20 = r6
                r21 = r16
                r22 = 2
                int r21 = r21 + 2
                r20 = r20[r21]
                r21 = r6
                r22 = r16
                r23 = 3
                int r22 = r22 + 3
                r21 = r21[r22]
                r17.rQuadTo(r18, r19, r20, r21)
                r17 = r8
                r18 = r6
                r19 = r16
                r20 = 0
                int r19 = r19 + 0
                r18 = r18[r19]
                float r17 = r17 + r18
                r10 = r17
                r17 = r9
                r18 = r6
                r19 = r16
                r20 = 1
                int r19 = r19 + 1
                r18 = r18[r19]
                float r17 = r17 + r18
                r11 = r17
                r17 = r8
                r18 = r6
                r19 = r16
                r20 = 2
                int r19 = r19 + 2
                r18 = r18[r19]
                float r17 = r17 + r18
                r8 = r17
                r17 = r9
                r18 = r6
                r19 = r16
                r20 = 3
                int r19 = r19 + 3
                r18 = r18[r19]
                float r17 = r17 + r18
                r9 = r17
                goto L_0x005b
            L_0x0511:
                r17 = r2
                r18 = r6
                r19 = r16
                r20 = 0
                int r19 = r19 + 0
                r18 = r18[r19]
                r19 = r6
                r20 = r16
                r21 = 1
                int r20 = r20 + 1
                r19 = r19[r20]
                r20 = r6
                r21 = r16
                r22 = 2
                int r21 = r21 + 2
                r20 = r20[r21]
                r21 = r6
                r22 = r16
                r23 = 3
                int r22 = r22 + 3
                r21 = r21[r22]
                r17.quadTo(r18, r19, r20, r21)
                r17 = r6
                r18 = r16
                r19 = 0
                int r18 = r18 + 0
                r17 = r17[r18]
                r10 = r17
                r17 = r6
                r18 = r16
                r19 = 1
                int r18 = r18 + 1
                r17 = r17[r18]
                r11 = r17
                r17 = r6
                r18 = r16
                r19 = 2
                int r18 = r18 + 2
                r17 = r17[r18]
                r8 = r17
                r17 = r6
                r18 = r16
                r19 = 3
                int r18 = r18 + 3
                r17 = r17[r18]
                r9 = r17
                goto L_0x005b
            L_0x0570:
                r17 = 0
                r14 = r17
                r17 = 0
                r15 = r17
                r17 = r4
                r18 = 113(0x71, float:1.58E-43)
                r0 = r17
                r1 = r18
                if (r0 == r1) goto L_0x05a0
                r17 = r4
                r18 = 116(0x74, float:1.63E-43)
                r0 = r17
                r1 = r18
                if (r0 == r1) goto L_0x05a0
                r17 = r4
                r18 = 81
                r0 = r17
                r1 = r18
                if (r0 == r1) goto L_0x05a0
                r17 = r4
                r18 = 84
                r0 = r17
                r1 = r18
                if (r0 != r1) goto L_0x05b0
            L_0x05a0:
                r17 = r8
                r18 = r10
                float r17 = r17 - r18
                r14 = r17
                r17 = r9
                r18 = r11
                float r17 = r17 - r18
                r15 = r17
            L_0x05b0:
                r17 = r2
                r18 = r14
                r19 = r15
                r20 = r6
                r21 = r16
                r22 = 0
                int r21 = r21 + 0
                r20 = r20[r21]
                r21 = r6
                r22 = r16
                r23 = 1
                int r22 = r22 + 1
                r21 = r21[r22]
                r17.rQuadTo(r18, r19, r20, r21)
                r17 = r8
                r18 = r14
                float r17 = r17 + r18
                r10 = r17
                r17 = r9
                r18 = r15
                float r17 = r17 + r18
                r11 = r17
                r17 = r8
                r18 = r6
                r19 = r16
                r20 = 0
                int r19 = r19 + 0
                r18 = r18[r19]
                float r17 = r17 + r18
                r8 = r17
                r17 = r9
                r18 = r6
                r19 = r16
                r20 = 1
                int r19 = r19 + 1
                r18 = r18[r19]
                float r17 = r17 + r18
                r9 = r17
                goto L_0x005b
            L_0x05ff:
                r17 = r8
                r14 = r17
                r17 = r9
                r15 = r17
                r17 = r4
                r18 = 113(0x71, float:1.58E-43)
                r0 = r17
                r1 = r18
                if (r0 == r1) goto L_0x062f
                r17 = r4
                r18 = 116(0x74, float:1.63E-43)
                r0 = r17
                r1 = r18
                if (r0 == r1) goto L_0x062f
                r17 = r4
                r18 = 81
                r0 = r17
                r1 = r18
                if (r0 == r1) goto L_0x062f
                r17 = r4
                r18 = 84
                r0 = r17
                r1 = r18
                if (r0 != r1) goto L_0x0647
            L_0x062f:
                r17 = 1073741824(0x40000000, float:2.0)
                r18 = r8
                float r17 = r17 * r18
                r18 = r10
                float r17 = r17 - r18
                r14 = r17
                r17 = 1073741824(0x40000000, float:2.0)
                r18 = r9
                float r17 = r17 * r18
                r18 = r11
                float r17 = r17 - r18
                r15 = r17
            L_0x0647:
                r17 = r2
                r18 = r14
                r19 = r15
                r20 = r6
                r21 = r16
                r22 = 0
                int r21 = r21 + 0
                r20 = r20[r21]
                r21 = r6
                r22 = r16
                r23 = 1
                int r22 = r22 + 1
                r21 = r21[r22]
                r17.quadTo(r18, r19, r20, r21)
                r17 = r14
                r10 = r17
                r17 = r15
                r11 = r17
                r17 = r6
                r18 = r16
                r19 = 0
                int r18 = r18 + 0
                r17 = r17[r18]
                r8 = r17
                r17 = r6
                r18 = r16
                r19 = 1
                int r18 = r18 + 1
                r17 = r17[r18]
                r9 = r17
                goto L_0x005b
            L_0x0686:
                r17 = r2
                r18 = r8
                r19 = r9
                r20 = r6
                r21 = r16
                r22 = 5
                int r21 = r21 + 5
                r20 = r20[r21]
                r21 = r8
                float r20 = r20 + r21
                r21 = r6
                r22 = r16
                r23 = 6
                int r22 = r22 + 6
                r21 = r21[r22]
                r22 = r9
                float r21 = r21 + r22
                r22 = r6
                r23 = r16
                r24 = 0
                int r23 = r23 + 0
                r22 = r22[r23]
                r23 = r6
                r24 = r16
                r25 = 1
                int r24 = r24 + 1
                r23 = r23[r24]
                r24 = r6
                r25 = r16
                r26 = 2
                int r25 = r25 + 2
                r24 = r24[r25]
                r25 = r6
                r26 = r16
                r27 = 3
                int r26 = r26 + 3
                r25 = r25[r26]
                r26 = 0
                int r25 = (r25 > r26 ? 1 : (r25 == r26 ? 0 : -1))
                if (r25 == 0) goto L_0x0717
                r25 = 1
            L_0x06d8:
                r26 = r6
                r27 = r16
                r28 = 4
                int r27 = r27 + 4
                r26 = r26[r27]
                r27 = 0
                int r26 = (r26 > r27 ? 1 : (r26 == r27 ? 0 : -1))
                if (r26 == 0) goto L_0x071a
                r26 = 1
            L_0x06ea:
                drawArc(r17, r18, r19, r20, r21, r22, r23, r24, r25, r26)
                r17 = r8
                r18 = r6
                r19 = r16
                r20 = 5
                int r19 = r19 + 5
                r18 = r18[r19]
                float r17 = r17 + r18
                r8 = r17
                r17 = r9
                r18 = r6
                r19 = r16
                r20 = 6
                int r19 = r19 + 6
                r18 = r18[r19]
                float r17 = r17 + r18
                r9 = r17
                r17 = r8
                r10 = r17
                r17 = r9
                r11 = r17
                goto L_0x005b
            L_0x0717:
                r25 = 0
                goto L_0x06d8
            L_0x071a:
                r26 = 0
                goto L_0x06ea
            L_0x071d:
                r17 = r2
                r18 = r8
                r19 = r9
                r20 = r6
                r21 = r16
                r22 = 5
                int r21 = r21 + 5
                r20 = r20[r21]
                r21 = r6
                r22 = r16
                r23 = 6
                int r22 = r22 + 6
                r21 = r21[r22]
                r22 = r6
                r23 = r16
                r24 = 0
                int r23 = r23 + 0
                r22 = r22[r23]
                r23 = r6
                r24 = r16
                r25 = 1
                int r24 = r24 + 1
                r23 = r23[r24]
                r24 = r6
                r25 = r16
                r26 = 2
                int r25 = r25 + 2
                r24 = r24[r25]
                r25 = r6
                r26 = r16
                r27 = 3
                int r26 = r26 + 3
                r25 = r25[r26]
                r26 = 0
                int r25 = (r25 > r26 ? 1 : (r25 == r26 ? 0 : -1))
                if (r25 == 0) goto L_0x079e
                r25 = 1
            L_0x0767:
                r26 = r6
                r27 = r16
                r28 = 4
                int r27 = r27 + 4
                r26 = r26[r27]
                r27 = 0
                int r26 = (r26 > r27 ? 1 : (r26 == r27 ? 0 : -1))
                if (r26 == 0) goto L_0x07a1
                r26 = 1
            L_0x0779:
                drawArc(r17, r18, r19, r20, r21, r22, r23, r24, r25, r26)
                r17 = r6
                r18 = r16
                r19 = 5
                int r18 = r18 + 5
                r17 = r17[r18]
                r8 = r17
                r17 = r6
                r18 = r16
                r19 = 6
                int r18 = r18 + 6
                r17 = r17[r18]
                r9 = r17
                r17 = r8
                r10 = r17
                r17 = r9
                r11 = r17
                goto L_0x005b
            L_0x079e:
                r25 = 0
                goto L_0x0767
            L_0x07a1:
                r26 = 0
                goto L_0x0779
            L_0x07a4:
                r17 = r3
                r18 = 0
                r19 = r8
                r17[r18] = r19
                r17 = r3
                r18 = 1
                r19 = r9
                r17[r18] = r19
                r17 = r3
                r18 = 2
                r19 = r10
                r17[r18] = r19
                r17 = r3
                r18 = 3
                r19 = r11
                r17[r18] = r19
                r17 = r3
                r18 = 4
                r19 = r12
                r17[r18] = r19
                r17 = r3
                r18 = 5
                r19 = r13
                r17[r18] = r19
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.graphics.PathParser.PathDataNode.addCommand(android.graphics.Path, float[], char, char, float[]):void");
        }

        private static void drawArc(Path path, float f, float f2, float f3, float f4, float f5, float f6, float f7, boolean z, boolean z2) {
            double cx;
            double cy;
            StringBuilder sb;
            Path p = path;
            float x0 = f;
            float y0 = f2;
            float x1 = f3;
            float y1 = f4;
            float a = f5;
            float b = f6;
            float theta = f7;
            boolean isMoreThanHalf = z;
            boolean isPositiveArc = z2;
            double thetaD = Math.toRadians((double) theta);
            double cosTheta = Math.cos(thetaD);
            double sinTheta = Math.sin(thetaD);
            double x0p = ((((double) x0) * cosTheta) + (((double) y0) * sinTheta)) / ((double) a);
            double y0p = ((((double) (-x0)) * sinTheta) + (((double) y0) * cosTheta)) / ((double) b);
            double x1p = ((((double) x1) * cosTheta) + (((double) y1) * sinTheta)) / ((double) a);
            double y1p = ((((double) (-x1)) * sinTheta) + (((double) y1) * cosTheta)) / ((double) b);
            double dx = x0p - x1p;
            double dy = y0p - y1p;
            double xm = (x0p + x1p) / 2.0d;
            double ym = (y0p + y1p) / 2.0d;
            double dsq = (dx * dx) + (dy * dy);
            if (dsq == 0.0d) {
                int w = Log.w(PathParser.LOGTAG, " Points are coincident");
                return;
            }
            double disc = (1.0d / dsq) - 0.25d;
            if (disc < 0.0d) {
                new StringBuilder();
                int w2 = Log.w(PathParser.LOGTAG, sb.append("Points are too far apart ").append(dsq).toString());
                float adjust = (float) (Math.sqrt(dsq) / 1.99999d);
                drawArc(p, x0, y0, x1, y1, a * adjust, b * adjust, theta, isMoreThanHalf, isPositiveArc);
                return;
            }
            double s = Math.sqrt(disc);
            double sdx = s * dx;
            double sdy = s * dy;
            if (isMoreThanHalf == isPositiveArc) {
                cx = xm - sdy;
                cy = ym + sdx;
            } else {
                cx = xm + sdy;
                cy = ym - sdx;
            }
            double eta0 = Math.atan2(y0p - cy, x0p - cx);
            double sweep = Math.atan2(y1p - cy, x1p - cx) - eta0;
            if (isPositiveArc != (sweep >= 0.0d)) {
                if (sweep > 0.0d) {
                    sweep -= 6.283185307179586d;
                } else {
                    sweep += 6.283185307179586d;
                }
            }
            double cx2 = cx * ((double) a);
            double cy2 = cy * ((double) b);
            arcToBezier(p, (cx2 * cosTheta) - (cy2 * sinTheta), (cx2 * sinTheta) + (cy2 * cosTheta), (double) a, (double) b, (double) x0, (double) y0, thetaD, eta0, sweep);
        }

        private static void arcToBezier(Path path, double d, double d2, double d3, double d4, double d5, double d6, double d7, double start, double d8) {
            Path p = path;
            double cx = d;
            double cy = d2;
            double a = d3;
            double b = d4;
            double e1x = d5;
            double e1y = d6;
            double theta = d7;
            double sweep = d8;
            int numSegments = (int) Math.ceil(Math.abs((sweep * 4.0d) / 3.141592653589793d));
            double eta1 = start;
            double cosTheta = Math.cos(theta);
            double sinTheta = Math.sin(theta);
            double cosEta1 = Math.cos(eta1);
            double sinEta1 = Math.sin(eta1);
            double ep1x = (((-a) * cosTheta) * sinEta1) - ((b * sinTheta) * cosEta1);
            double ep1y = ((-a) * sinTheta * sinEta1) + (b * cosTheta * cosEta1);
            double anglePerSegment = sweep / ((double) numSegments);
            for (int i = 0; i < numSegments; i++) {
                double eta2 = eta1 + anglePerSegment;
                double sinEta2 = Math.sin(eta2);
                double cosEta2 = Math.cos(eta2);
                double e2x = (cx + ((a * cosTheta) * cosEta2)) - ((b * sinTheta) * sinEta2);
                double e2y = cy + (a * sinTheta * cosEta2) + (b * cosTheta * sinEta2);
                double ep2x = (((-a) * cosTheta) * sinEta2) - ((b * sinTheta) * cosEta2);
                double ep2y = ((-a) * sinTheta * sinEta2) + (b * cosTheta * cosEta2);
                double tanDiff2 = Math.tan((eta2 - eta1) / 2.0d);
                double alpha = (Math.sin(eta2 - eta1) * (Math.sqrt(4.0d + ((3.0d * tanDiff2) * tanDiff2)) - 1.0d)) / 3.0d;
                p.rLineTo(0.0f, 0.0f);
                p.cubicTo((float) (e1x + (alpha * ep1x)), (float) (e1y + (alpha * ep1y)), (float) (e2x - (alpha * ep2x)), (float) (e2y - (alpha * ep2y)), (float) e2x, (float) e2y);
                eta1 = eta2;
                e1x = e2x;
                e1y = e2y;
                ep1x = ep2x;
                ep1y = ep2y;
            }
        }
    }

    private PathParser() {
    }
}

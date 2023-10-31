package android.support.constraint.solver;

import java.util.Arrays;

public class SolverVariable {
    private static final boolean INTERNAL_DEBUG = false;
    static final int MAX_STRENGTH = 7;
    public static final int STRENGTH_BARRIER = 7;
    public static final int STRENGTH_EQUALITY = 5;
    public static final int STRENGTH_FIXED = 6;
    public static final int STRENGTH_HIGH = 3;
    public static final int STRENGTH_HIGHEST = 4;
    public static final int STRENGTH_LOW = 1;
    public static final int STRENGTH_MEDIUM = 2;
    public static final int STRENGTH_NONE = 0;
    private static int uniqueConstantId = 1;
    private static int uniqueErrorId = 1;
    private static int uniqueId = 1;
    private static int uniqueSlackId = 1;
    private static int uniqueUnrestrictedId = 1;
    public float computedValue;
    int definitionId = -1;

    /* renamed from: id */
    public int f3id = -1;
    ArrayRow[] mClientEquations = new ArrayRow[8];
    int mClientEquationsCount = 0;
    private String mName;
    Type mType;
    public int strength = 0;
    float[] strengthVector = new float[7];
    public int usageInRowCount = 0;

    public enum Type {
    }

    static void increaseErrorId() {
        uniqueErrorId++;
    }

    private static String getUniqueName(Type type, String str) {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        StringBuilder sb5;
        Throwable th;
        StringBuilder sb6;
        Type type2 = type;
        String prefix = str;
        if (prefix != null) {
            new StringBuilder();
            return sb6.append(prefix).append(uniqueErrorId).toString();
        }
        switch (type2) {
            case UNRESTRICTED:
                new StringBuilder();
                StringBuilder append = sb5.append("U");
                int i = uniqueUnrestrictedId + 1;
                uniqueUnrestrictedId = i;
                return append.append(i).toString();
            case CONSTANT:
                new StringBuilder();
                StringBuilder append2 = sb4.append("C");
                int i2 = uniqueConstantId + 1;
                uniqueConstantId = i2;
                return append2.append(i2).toString();
            case SLACK:
                new StringBuilder();
                StringBuilder append3 = sb3.append("S");
                int i3 = uniqueSlackId + 1;
                uniqueSlackId = i3;
                return append3.append(i3).toString();
            case ERROR:
                new StringBuilder();
                StringBuilder append4 = sb2.append("e");
                int i4 = uniqueErrorId + 1;
                uniqueErrorId = i4;
                return append4.append(i4).toString();
            case UNKNOWN:
                new StringBuilder();
                StringBuilder append5 = sb.append("V");
                int i5 = uniqueId + 1;
                uniqueId = i5;
                return append5.append(i5).toString();
            default:
                Throwable th2 = th;
                new AssertionError(type2.name());
                throw th2;
        }
    }

    public SolverVariable(String name, Type type) {
        this.mName = name;
        this.mType = type;
    }

    public SolverVariable(Type type, String str) {
        String str2 = str;
        this.mType = type;
    }

    /* access modifiers changed from: package-private */
    public void clearStrengths() {
        for (int i = 0; i < 7; i++) {
            this.strengthVector[i] = 0.0f;
        }
    }

    /* access modifiers changed from: package-private */
    public String strengthsToString() {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        StringBuilder sb5;
        String sb6;
        StringBuilder sb7;
        new StringBuilder();
        String representation = sb.append(this).append("[").toString();
        boolean negative = false;
        boolean empty = true;
        for (int j = 0; j < this.strengthVector.length; j++) {
            new StringBuilder();
            String representation2 = sb4.append(representation).append(this.strengthVector[j]).toString();
            if (this.strengthVector[j] > 0.0f) {
                negative = false;
            } else if (this.strengthVector[j] < 0.0f) {
                negative = true;
            }
            if (this.strengthVector[j] != 0.0f) {
                empty = false;
            }
            if (j < this.strengthVector.length - 1) {
                new StringBuilder();
                sb6 = sb7.append(representation2).append(", ").toString();
            } else {
                new StringBuilder();
                sb6 = sb5.append(representation2).append("] ").toString();
            }
            representation = sb6;
        }
        if (negative) {
            new StringBuilder();
            representation = sb3.append(representation).append(" (-)").toString();
        }
        if (empty) {
            new StringBuilder();
            representation = sb2.append(representation).append(" (*)").toString();
        }
        return representation;
    }

    public final void addToRow(ArrayRow arrayRow) {
        ArrayRow row = arrayRow;
        int i = 0;
        while (i < this.mClientEquationsCount) {
            if (this.mClientEquations[i] != row) {
                i++;
            } else {
                return;
            }
        }
        if (this.mClientEquationsCount >= this.mClientEquations.length) {
            this.mClientEquations = (ArrayRow[]) Arrays.copyOf(this.mClientEquations, this.mClientEquations.length * 2);
        }
        this.mClientEquations[this.mClientEquationsCount] = row;
        this.mClientEquationsCount++;
    }

    public final void removeFromRow(ArrayRow arrayRow) {
        ArrayRow row = arrayRow;
        int count = this.mClientEquationsCount;
        for (int i = 0; i < count; i++) {
            if (this.mClientEquations[i] == row) {
                for (int j = 0; j < (count - i) - 1; j++) {
                    this.mClientEquations[i + j] = this.mClientEquations[i + j + 1];
                }
                this.mClientEquationsCount--;
                return;
            }
        }
    }

    public final void updateReferencesWithNewDefinition(ArrayRow arrayRow) {
        ArrayRow definition = arrayRow;
        int count = this.mClientEquationsCount;
        for (int i = 0; i < count; i++) {
            this.mClientEquations[i].variables.updateFromRow(this.mClientEquations[i], definition, false);
        }
        this.mClientEquationsCount = 0;
    }

    public void reset() {
        this.mName = null;
        this.mType = Type.UNKNOWN;
        this.strength = 0;
        this.f3id = -1;
        this.definitionId = -1;
        this.computedValue = 0.0f;
        this.mClientEquationsCount = 0;
        this.usageInRowCount = 0;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String name) {
        String str = name;
        this.mName = str;
    }

    public void setType(Type type, String str) {
        String str2 = str;
        Type type2 = type;
        this.mType = type2;
    }

    /*  JADX ERROR: NullPointerException in pass: CodeShrinkVisitor
        java.lang.NullPointerException
        	at jadx.core.dex.instructions.args.InsnArg.wrapInstruction(InsnArg.java:118)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.inline(CodeShrinkVisitor.java:146)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.shrinkBlock(CodeShrinkVisitor.java:71)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.shrinkMethod(CodeShrinkVisitor.java:43)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.visit(CodeShrinkVisitor.java:35)
        */
    public java.lang.String toString() {
        /*
            r5 = this;
            r0 = r5
            java.lang.String r2 = ""
            r1 = r2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r4 = r2
            r2 = r4
            r3 = r4
            r3.<init>()
            r3 = r1
            java.lang.StringBuilder r2 = r2.append(r3)
            r3 = r0
            java.lang.String r3 = r3.mName
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1 = r2
            r2 = r1
            r0 = r2
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.SolverVariable.toString():java.lang.String");
    }
}

package android.support.constraint.solver;

import android.support.constraint.solver.SolverVariable;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.constraint.solver.widgets.ConstraintWidget;
import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;

public class LinearSystem {
    private static final boolean DEBUG = false;
    public static final boolean FULL_DEBUG = false;
    private static int POOL_SIZE = 1000;
    public static Metrics sMetrics;
    private int TABLE_SIZE;
    public boolean graphOptimizer;
    private boolean[] mAlreadyTestedCandidates;
    final Cache mCache;
    private Row mGoal;
    private int mMaxColumns;
    private int mMaxRows;
    int mNumColumns;
    int mNumRows;
    private SolverVariable[] mPoolVariables;
    private int mPoolVariablesCount;
    ArrayRow[] mRows;
    private final Row mTempGoal;
    private HashMap<String, SolverVariable> mVariables;
    int mVariablesID;
    private ArrayRow[] tempClientsCopy;

    interface Row {
        void addError(SolverVariable solverVariable);

        void clear();

        SolverVariable getKey();

        SolverVariable getPivotCandidate(LinearSystem linearSystem, boolean[] zArr);

        void initFromRow(Row row);

        boolean isEmpty();
    }

    public LinearSystem() {
        Cache cache;
        Row row;
        Row row2;
        this.mVariablesID = 0;
        this.mVariables = null;
        this.TABLE_SIZE = 32;
        this.mMaxColumns = this.TABLE_SIZE;
        this.mRows = null;
        this.graphOptimizer = false;
        this.mAlreadyTestedCandidates = new boolean[this.TABLE_SIZE];
        this.mNumColumns = 1;
        this.mNumRows = 0;
        this.mMaxRows = this.TABLE_SIZE;
        this.mPoolVariables = new SolverVariable[POOL_SIZE];
        this.mPoolVariablesCount = 0;
        this.tempClientsCopy = new ArrayRow[this.TABLE_SIZE];
        this.mRows = new ArrayRow[this.TABLE_SIZE];
        releaseRows();
        new Cache();
        this.mCache = cache;
        new GoalRow(this.mCache);
        this.mGoal = row;
        new ArrayRow(this.mCache);
        this.mTempGoal = row2;
    }

    public void fillMetrics(Metrics metrics) {
        sMetrics = metrics;
    }

    public static Metrics getMetrics() {
        return sMetrics;
    }

    private void increaseTableSize() {
        this.TABLE_SIZE *= 2;
        this.mRows = (ArrayRow[]) Arrays.copyOf(this.mRows, this.TABLE_SIZE);
        this.mCache.mIndexedVariables = (SolverVariable[]) Arrays.copyOf(this.mCache.mIndexedVariables, this.TABLE_SIZE);
        this.mAlreadyTestedCandidates = new boolean[this.TABLE_SIZE];
        this.mMaxColumns = this.TABLE_SIZE;
        this.mMaxRows = this.TABLE_SIZE;
        if (sMetrics != null) {
            sMetrics.tableSizeIncrease++;
            sMetrics.maxTableSize = Math.max(sMetrics.maxTableSize, (long) this.TABLE_SIZE);
            sMetrics.lastTableSize = sMetrics.maxTableSize;
        }
    }

    private void releaseRows() {
        for (int i = 0; i < this.mRows.length; i++) {
            ArrayRow row = this.mRows[i];
            if (row != null) {
                boolean release = this.mCache.arrayRowPool.release(row);
            }
            this.mRows[i] = null;
        }
    }

    public void reset() {
        for (int i = 0; i < this.mCache.mIndexedVariables.length; i++) {
            SolverVariable variable = this.mCache.mIndexedVariables[i];
            if (variable != null) {
                variable.reset();
            }
        }
        this.mCache.solverVariablePool.releaseAll(this.mPoolVariables, this.mPoolVariablesCount);
        this.mPoolVariablesCount = 0;
        Arrays.fill(this.mCache.mIndexedVariables, (Object) null);
        if (this.mVariables != null) {
            this.mVariables.clear();
        }
        this.mVariablesID = 0;
        this.mGoal.clear();
        this.mNumColumns = 1;
        for (int i2 = 0; i2 < this.mNumRows; i2++) {
            this.mRows[i2].used = false;
        }
        releaseRows();
        this.mNumRows = 0;
    }

    public SolverVariable createObjectVariable(Object obj) {
        Object anchor = obj;
        if (anchor == null) {
            return null;
        }
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable variable = null;
        if (anchor instanceof ConstraintAnchor) {
            variable = ((ConstraintAnchor) anchor).getSolverVariable();
            if (variable == null) {
                ((ConstraintAnchor) anchor).resetSolverVariable(this.mCache);
                variable = ((ConstraintAnchor) anchor).getSolverVariable();
            }
            if (variable.f3id == -1 || variable.f3id > this.mVariablesID || this.mCache.mIndexedVariables[variable.f3id] == null) {
                if (variable.f3id != -1) {
                    variable.reset();
                }
                this.mVariablesID++;
                this.mNumColumns++;
                variable.f3id = this.mVariablesID;
                variable.mType = SolverVariable.Type.UNRESTRICTED;
                this.mCache.mIndexedVariables[this.mVariablesID] = variable;
            }
        }
        return variable;
    }

    public ArrayRow createRow() {
        ArrayRow arrayRow;
        ArrayRow row = this.mCache.arrayRowPool.acquire();
        if (row == null) {
            new ArrayRow(this.mCache);
            row = arrayRow;
        } else {
            row.reset();
        }
        SolverVariable.increaseErrorId();
        return row;
    }

    public SolverVariable createSlackVariable() {
        if (sMetrics != null) {
            sMetrics.slackvariables++;
        }
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable variable = acquireSolverVariable(SolverVariable.Type.SLACK, (String) null);
        this.mVariablesID++;
        this.mNumColumns++;
        variable.f3id = this.mVariablesID;
        this.mCache.mIndexedVariables[this.mVariablesID] = variable;
        return variable;
    }

    public SolverVariable createExtraVariable() {
        if (sMetrics != null) {
            sMetrics.extravariables++;
        }
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable variable = acquireSolverVariable(SolverVariable.Type.SLACK, (String) null);
        this.mVariablesID++;
        this.mNumColumns++;
        variable.f3id = this.mVariablesID;
        this.mCache.mIndexedVariables[this.mVariablesID] = variable;
        return variable;
    }

    private void addError(ArrayRow row) {
        ArrayRow addError = row.addError(this, 0);
    }

    private void addSingleError(ArrayRow row, int sign) {
        addSingleError(row, sign, 0);
    }

    /* access modifiers changed from: package-private */
    public void addSingleError(ArrayRow row, int sign, int strength) {
        ArrayRow addSingleError = row.addSingleError(createErrorVariable(strength, (String) null), sign);
    }

    private SolverVariable createVariable(String str, SolverVariable.Type type) {
        HashMap<String, SolverVariable> hashMap;
        String name = str;
        SolverVariable.Type type2 = type;
        if (sMetrics != null) {
            sMetrics.variables++;
        }
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable variable = acquireSolverVariable(type2, (String) null);
        variable.setName(name);
        this.mVariablesID++;
        this.mNumColumns++;
        variable.f3id = this.mVariablesID;
        if (this.mVariables == null) {
            new HashMap<>();
            this.mVariables = hashMap;
        }
        SolverVariable put = this.mVariables.put(name, variable);
        this.mCache.mIndexedVariables[this.mVariablesID] = variable;
        return variable;
    }

    public SolverVariable createErrorVariable(int i, String str) {
        int strength = i;
        String prefix = str;
        if (sMetrics != null) {
            sMetrics.errors++;
        }
        if (this.mNumColumns + 1 >= this.mMaxColumns) {
            increaseTableSize();
        }
        SolverVariable variable = acquireSolverVariable(SolverVariable.Type.ERROR, prefix);
        this.mVariablesID++;
        this.mNumColumns++;
        variable.f3id = this.mVariablesID;
        variable.strength = strength;
        this.mCache.mIndexedVariables[this.mVariablesID] = variable;
        this.mGoal.addError(variable);
        return variable;
    }

    private SolverVariable acquireSolverVariable(SolverVariable.Type type, String str) {
        SolverVariable solverVariable;
        SolverVariable.Type type2 = type;
        String prefix = str;
        SolverVariable variable = this.mCache.solverVariablePool.acquire();
        if (variable == null) {
            new SolverVariable(type2, prefix);
            variable = solverVariable;
            variable.setType(type2, prefix);
        } else {
            variable.reset();
            variable.setType(type2, prefix);
        }
        if (this.mPoolVariablesCount >= POOL_SIZE) {
            POOL_SIZE *= 2;
            this.mPoolVariables = (SolverVariable[]) Arrays.copyOf(this.mPoolVariables, POOL_SIZE);
        }
        SolverVariable[] solverVariableArr = this.mPoolVariables;
        int i = this.mPoolVariablesCount;
        int i2 = i + 1;
        this.mPoolVariablesCount = i2;
        solverVariableArr[i] = variable;
        return variable;
    }

    /* access modifiers changed from: package-private */
    public Row getGoal() {
        return this.mGoal;
    }

    /* access modifiers changed from: package-private */
    public ArrayRow getRow(int n) {
        return this.mRows[n];
    }

    /* access modifiers changed from: package-private */
    public float getValueFor(String name) {
        SolverVariable v = getVariable(name, SolverVariable.Type.UNRESTRICTED);
        if (v == null) {
            return 0.0f;
        }
        return v.computedValue;
    }

    public int getObjectVariableValue(Object anchor) {
        SolverVariable variable = ((ConstraintAnchor) anchor).getSolverVariable();
        if (variable != null) {
            return (int) (variable.computedValue + 0.5f);
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public SolverVariable getVariable(String str, SolverVariable.Type type) {
        HashMap<String, SolverVariable> hashMap;
        String name = str;
        SolverVariable.Type type2 = type;
        if (this.mVariables == null) {
            new HashMap<>();
            this.mVariables = hashMap;
        }
        SolverVariable variable = this.mVariables.get(name);
        if (variable == null) {
            variable = createVariable(name, type2);
        }
        return variable;
    }

    public void minimize() throws Exception {
        if (sMetrics != null) {
            sMetrics.minimize++;
        }
        if (this.graphOptimizer) {
            if (sMetrics != null) {
                sMetrics.graphOptimizer++;
            }
            boolean fullySolved = true;
            int i = 0;
            while (true) {
                if (i >= this.mNumRows) {
                    break;
                } else if (!this.mRows[i].isSimpleDefinition) {
                    fullySolved = false;
                    break;
                } else {
                    i++;
                }
            }
            if (!fullySolved) {
                minimizeGoal(this.mGoal);
                return;
            }
            if (sMetrics != null) {
                sMetrics.fullySolved++;
            }
            computeValues();
            return;
        }
        minimizeGoal(this.mGoal);
    }

    /* access modifiers changed from: package-private */
    public void minimizeGoal(Row row) throws Exception {
        Row goal = row;
        if (sMetrics != null) {
            sMetrics.minimizeGoal++;
            sMetrics.maxVariables = Math.max(sMetrics.maxVariables, (long) this.mNumColumns);
            sMetrics.maxRows = Math.max(sMetrics.maxRows, (long) this.mNumRows);
        }
        updateRowFromVariables((ArrayRow) goal);
        int enforceBFS = enforceBFS(goal);
        int optimize = optimize(goal, false);
        computeValues();
    }

    private final void updateRowFromVariables(ArrayRow arrayRow) {
        ArrayRow row = arrayRow;
        if (this.mNumRows > 0) {
            row.variables.updateFromSystem(row, this.mRows);
            if (row.variables.currentSize == 0) {
                row.isSimpleDefinition = true;
            }
        }
    }

    public void addConstraint(ArrayRow arrayRow) {
        SolverVariable pivotCandidate;
        ArrayRow row = arrayRow;
        if (row != null) {
            if (sMetrics != null) {
                sMetrics.constraints++;
                if (row.isSimpleDefinition) {
                    sMetrics.simpleconstraints++;
                }
            }
            if (this.mNumRows + 1 >= this.mMaxRows || this.mNumColumns + 1 >= this.mMaxColumns) {
                increaseTableSize();
            }
            boolean added = false;
            if (!row.isSimpleDefinition) {
                updateRowFromVariables(row);
                if (!row.isEmpty()) {
                    row.ensurePositiveConstant();
                    if (row.chooseSubject(this)) {
                        SolverVariable extra = createExtraVariable();
                        row.variable = extra;
                        addRow(row);
                        added = true;
                        this.mTempGoal.initFromRow(row);
                        int optimize = optimize(this.mTempGoal, true);
                        if (extra.definitionId == -1) {
                            if (row.variable == extra && (pivotCandidate = row.pickPivot(extra)) != null) {
                                if (sMetrics != null) {
                                    sMetrics.pivots++;
                                }
                                row.pivot(pivotCandidate);
                            }
                            if (!row.isSimpleDefinition) {
                                row.variable.updateReferencesWithNewDefinition(row);
                            }
                            this.mNumRows--;
                        }
                    }
                    if (!row.hasKeyVariable()) {
                        return;
                    }
                } else {
                    return;
                }
            }
            if (!added) {
                addRow(row);
            }
        }
    }

    private final void addRow(ArrayRow arrayRow) {
        ArrayRow row = arrayRow;
        if (this.mRows[this.mNumRows] != null) {
            boolean release = this.mCache.arrayRowPool.release(this.mRows[this.mNumRows]);
        }
        this.mRows[this.mNumRows] = row;
        row.variable.definitionId = this.mNumRows;
        this.mNumRows++;
        row.variable.updateReferencesWithNewDefinition(row);
    }

    /* JADX WARNING: CFG modification limit reached, blocks count: 158 */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0117, code lost:
        r3 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x011b, code lost:
        r3 = true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int optimize(android.support.constraint.solver.LinearSystem.Row r20, boolean r21) {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            r2 = r21
            android.support.constraint.solver.Metrics r13 = sMetrics
            if (r13 == 0) goto L_0x001a
            android.support.constraint.solver.Metrics r13 = sMetrics
            r18 = r13
            r13 = r18
            r14 = r18
            long r14 = r14.optimize
            r16 = 1
            long r14 = r14 + r16
            r13.optimize = r14
        L_0x001a:
            r13 = 0
            r3 = r13
            r13 = 0
            r4 = r13
            r13 = 0
            r5 = r13
        L_0x0020:
            r13 = r5
            r14 = r0
            int r14 = r14.mNumColumns
            if (r13 >= r14) goto L_0x0067
            r13 = r0
            boolean[] r13 = r13.mAlreadyTestedCandidates
            r14 = r5
            r15 = 0
            r13[r14] = r15
            int r5 = r5 + 1
            goto L_0x0020
        L_0x0030:
            r13 = r7
            r14 = -1
            if (r13 <= r14) goto L_0x0117
            r13 = r0
            android.support.constraint.solver.ArrayRow[] r13 = r13.mRows
            r14 = r7
            r13 = r13[r14]
            r8 = r13
            r13 = r8
            android.support.constraint.solver.SolverVariable r13 = r13.variable
            r14 = -1
            r13.definitionId = r14
            android.support.constraint.solver.Metrics r13 = sMetrics
            if (r13 == 0) goto L_0x0055
            android.support.constraint.solver.Metrics r13 = sMetrics
            r18 = r13
            r13 = r18
            r14 = r18
            long r14 = r14.pivots
            r16 = 1
            long r14 = r14 + r16
            r13.pivots = r14
        L_0x0055:
            r13 = r8
            r14 = r5
            r13.pivot(r14)
            r13 = r8
            android.support.constraint.solver.SolverVariable r13 = r13.variable
            r14 = r7
            r13.definitionId = r14
            r13 = r8
            android.support.constraint.solver.SolverVariable r13 = r13.variable
            r14 = r8
            r13.updateReferencesWithNewDefinition(r14)
        L_0x0067:
            r13 = r3
            if (r13 != 0) goto L_0x011f
            android.support.constraint.solver.Metrics r13 = sMetrics
            if (r13 == 0) goto L_0x007e
            android.support.constraint.solver.Metrics r13 = sMetrics
            r18 = r13
            r13 = r18
            r14 = r18
            long r14 = r14.iterations
            r16 = 1
            long r14 = r14 + r16
            r13.iterations = r14
        L_0x007e:
            int r4 = r4 + 1
            r13 = r4
            r14 = 2
            r15 = r0
            int r15 = r15.mNumColumns
            int r14 = r14 * r15
            if (r13 < r14) goto L_0x008b
            r13 = r4
            r0 = r13
        L_0x008a:
            return r0
        L_0x008b:
            r13 = r1
            android.support.constraint.solver.SolverVariable r13 = r13.getKey()
            if (r13 == 0) goto L_0x009f
            r13 = r0
            boolean[] r13 = r13.mAlreadyTestedCandidates
            r14 = r1
            android.support.constraint.solver.SolverVariable r14 = r14.getKey()
            int r14 = r14.f3id
            r15 = 1
            r13[r14] = r15
        L_0x009f:
            r13 = r1
            r14 = r0
            r15 = r0
            boolean[] r15 = r15.mAlreadyTestedCandidates
            android.support.constraint.solver.SolverVariable r13 = r13.getPivotCandidate(r14, r15)
            r5 = r13
            r13 = r5
            if (r13 == 0) goto L_0x00c2
            r13 = r0
            boolean[] r13 = r13.mAlreadyTestedCandidates
            r14 = r5
            int r14 = r14.f3id
            boolean r13 = r13[r14]
            if (r13 == 0) goto L_0x00b9
            r13 = r4
            r0 = r13
            goto L_0x008a
        L_0x00b9:
            r13 = r0
            boolean[] r13 = r13.mAlreadyTestedCandidates
            r14 = r5
            int r14 = r14.f3id
            r15 = 1
            r13[r14] = r15
        L_0x00c2:
            r13 = r5
            if (r13 == 0) goto L_0x011b
            r13 = 2139095039(0x7f7fffff, float:3.4028235E38)
            r6 = r13
            r13 = -1
            r7 = r13
            r13 = 0
            r8 = r13
        L_0x00cd:
            r13 = r8
            r14 = r0
            int r14 = r14.mNumRows
            if (r13 >= r14) goto L_0x0030
            r13 = r0
            android.support.constraint.solver.ArrayRow[] r13 = r13.mRows
            r14 = r8
            r13 = r13[r14]
            r9 = r13
            r13 = r9
            android.support.constraint.solver.SolverVariable r13 = r13.variable
            r10 = r13
            r13 = r10
            android.support.constraint.solver.SolverVariable$Type r13 = r13.mType
            android.support.constraint.solver.SolverVariable$Type r14 = android.support.constraint.solver.SolverVariable.Type.UNRESTRICTED
            if (r13 != r14) goto L_0x00e8
        L_0x00e5:
            int r8 = r8 + 1
            goto L_0x00cd
        L_0x00e8:
            r13 = r9
            boolean r13 = r13.isSimpleDefinition
            if (r13 == 0) goto L_0x00ee
            goto L_0x00e5
        L_0x00ee:
            r13 = r9
            r14 = r5
            boolean r13 = r13.hasVariable(r14)
            if (r13 == 0) goto L_0x00e5
            r13 = r9
            android.support.constraint.solver.ArrayLinkedVariables r13 = r13.variables
            r14 = r5
            float r13 = r13.get(r14)
            r11 = r13
            r13 = r11
            r14 = 0
            int r13 = (r13 > r14 ? 1 : (r13 == r14 ? 0 : -1))
            if (r13 >= 0) goto L_0x00e5
            r13 = r9
            float r13 = r13.constantValue
            float r13 = -r13
            r14 = r11
            float r13 = r13 / r14
            r12 = r13
            r13 = r12
            r14 = r6
            int r13 = (r13 > r14 ? 1 : (r13 == r14 ? 0 : -1))
            if (r13 >= 0) goto L_0x00e5
            r13 = r12
            r6 = r13
            r13 = r8
            r7 = r13
            goto L_0x00e5
        L_0x0117:
            r13 = 1
            r3 = r13
            goto L_0x0067
        L_0x011b:
            r13 = 1
            r3 = r13
            goto L_0x0067
        L_0x011f:
            r13 = r4
            r0 = r13
            goto L_0x008a
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.LinearSystem.optimize(android.support.constraint.solver.LinearSystem$Row, boolean):int");
    }

    private int enforceBFS(Row row) throws Exception {
        Row row2 = row;
        int tries = 0;
        boolean infeasibleSystem = false;
        int i = 0;
        while (true) {
            if (i >= this.mNumRows) {
                break;
            }
            if (this.mRows[i].variable.mType != SolverVariable.Type.UNRESTRICTED) {
                if (this.mRows[i].constantValue < 0.0f) {
                    infeasibleSystem = true;
                    break;
                }
            }
            i++;
        }
        if (infeasibleSystem) {
            boolean done = false;
            tries = 0;
            while (!done) {
                if (sMetrics != null) {
                    sMetrics.bfs++;
                }
                tries++;
                float min = Float.MAX_VALUE;
                int strength = 0;
                int pivotRowIndex = -1;
                int pivotColumnIndex = -1;
                int i2 = 0;
                while (true) {
                    if (i2 >= this.mNumRows) {
                        break;
                    }
                    ArrayRow current = this.mRows[i2];
                    if (current.variable.mType != SolverVariable.Type.UNRESTRICTED && !current.isSimpleDefinition && current.constantValue < 0.0f) {
                        int j = 1;
                        while (true) {
                            if (j >= this.mNumColumns) {
                                break;
                            }
                            SolverVariable candidate = this.mCache.mIndexedVariables[j];
                            float a_j = current.variables.get(candidate);
                            if (a_j > 0.0f) {
                                for (int k = 0; k < 7; k++) {
                                    float value = candidate.strengthVector[k] / a_j;
                                    if ((value < min && k == strength) || k > strength) {
                                        min = value;
                                        pivotRowIndex = i2;
                                        pivotColumnIndex = j;
                                        strength = k;
                                    }
                                }
                            }
                            j++;
                        }
                    }
                    i2++;
                }
                if (pivotRowIndex != -1) {
                    ArrayRow pivotEquation = this.mRows[pivotRowIndex];
                    pivotEquation.variable.definitionId = -1;
                    if (sMetrics != null) {
                        sMetrics.pivots++;
                    }
                    pivotEquation.pivot(this.mCache.mIndexedVariables[pivotColumnIndex]);
                    pivotEquation.variable.definitionId = pivotRowIndex;
                    pivotEquation.variable.updateReferencesWithNewDefinition(pivotEquation);
                } else {
                    done = true;
                }
                if (tries > this.mNumColumns / 2) {
                    done = true;
                }
            }
        }
        return tries;
    }

    private void computeValues() {
        for (int i = 0; i < this.mNumRows; i++) {
            ArrayRow row = this.mRows[i];
            row.variable.computedValue = row.constantValue;
        }
    }

    private void displayRows() {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        displaySolverVariables();
        String s = "";
        for (int i = 0; i < this.mNumRows; i++) {
            new StringBuilder();
            String s2 = sb2.append(s).append(this.mRows[i]).toString();
            new StringBuilder();
            s = sb3.append(s2).append("\n").toString();
        }
        new StringBuilder();
        System.out.println(sb.append(s).append(this.mGoal).append("\n").toString());
    }

    /* access modifiers changed from: package-private */
    public void displayReadableRows() {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        displaySolverVariables();
        String s = " #  ";
        for (int i = 0; i < this.mNumRows; i++) {
            new StringBuilder();
            String s2 = sb2.append(s).append(this.mRows[i].toReadableString()).toString();
            new StringBuilder();
            s = sb3.append(s2).append("\n #  ").toString();
        }
        if (this.mGoal != null) {
            new StringBuilder();
            s = sb.append(s).append(this.mGoal).append("\n").toString();
        }
        System.out.println(s);
    }

    public void displayVariablesReadableRows() {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        displaySolverVariables();
        String s = "";
        for (int i = 0; i < this.mNumRows; i++) {
            if (this.mRows[i].variable.mType == SolverVariable.Type.UNRESTRICTED) {
                new StringBuilder();
                String s2 = sb2.append(s).append(this.mRows[i].toReadableString()).toString();
                new StringBuilder();
                s = sb3.append(s2).append("\n").toString();
            }
        }
        new StringBuilder();
        System.out.println(sb.append(s).append(this.mGoal).append("\n").toString());
    }

    public int getMemoryUsed() {
        int actualRowSize = 0;
        for (int i = 0; i < this.mNumRows; i++) {
            if (this.mRows[i] != null) {
                actualRowSize += this.mRows[i].sizeInBytes();
            }
        }
        return actualRowSize;
    }

    public int getNumEquations() {
        return this.mNumRows;
    }

    public int getNumVariables() {
        return this.mVariablesID;
    }

    /* access modifiers changed from: package-private */
    public void displaySystemInformations() {
        StringBuilder sb;
        int rowSize = 0;
        for (int i = 0; i < this.TABLE_SIZE; i++) {
            if (this.mRows[i] != null) {
                rowSize += this.mRows[i].sizeInBytes();
            }
        }
        int actualRowSize = 0;
        for (int i2 = 0; i2 < this.mNumRows; i2++) {
            if (this.mRows[i2] != null) {
                actualRowSize += this.mRows[i2].sizeInBytes();
            }
        }
        PrintStream printStream = System.out;
        new StringBuilder();
        printStream.println(sb.append("Linear System -> Table size: ").append(this.TABLE_SIZE).append(" (").append(getDisplaySize(this.TABLE_SIZE * this.TABLE_SIZE)).append(") -- row sizes: ").append(getDisplaySize(rowSize)).append(", actual size: ").append(getDisplaySize(actualRowSize)).append(" rows: ").append(this.mNumRows).append("/").append(this.mMaxRows).append(" cols: ").append(this.mNumColumns).append("/").append(this.mMaxColumns).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(0).append(" occupied cells, ").append(getDisplaySize(0)).toString());
    }

    private void displaySolverVariables() {
        StringBuilder sb;
        new StringBuilder();
        System.out.println(sb.append("Display Rows (").append(this.mNumRows).append("x").append(this.mNumColumns).append(")\n").toString());
    }

    private String getDisplaySize(int i) {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        int n = i;
        int mb = ((n * 4) / 1024) / 1024;
        if (mb > 0) {
            new StringBuilder();
            return sb3.append("").append(mb).append(" Mb").toString();
        }
        int kb = (n * 4) / 1024;
        if (kb > 0) {
            new StringBuilder();
            return sb2.append("").append(kb).append(" Kb").toString();
        }
        new StringBuilder();
        return sb.append("").append(n * 4).append(" bytes").toString();
    }

    public Cache getCache() {
        return this.mCache;
    }

    private String getDisplayStrength(int i) {
        int strength = i;
        if (strength == 1) {
            return "LOW";
        }
        if (strength == 2) {
            return "MEDIUM";
        }
        if (strength == 3) {
            return "HIGH";
        }
        if (strength == 4) {
            return "HIGHEST";
        }
        if (strength == 5) {
            return "EQUALITY";
        }
        if (strength == 6) {
            return "FIXED";
        }
        return "NONE";
    }

    public void addGreaterThan(SolverVariable a, SolverVariable b, int margin, int i) {
        int strength = i;
        ArrayRow row = createRow();
        SolverVariable slack = createSlackVariable();
        slack.strength = 0;
        ArrayRow createRowGreaterThan = row.createRowGreaterThan(a, b, slack, margin);
        if (strength != 6) {
            addSingleError(row, (int) (-1.0f * row.variables.get(slack)), strength);
        }
        addConstraint(row);
    }

    public void addGreaterThan(SolverVariable a, int b) {
        ArrayRow row = createRow();
        SolverVariable slack = createSlackVariable();
        slack.strength = 0;
        ArrayRow createRowGreaterThan = row.createRowGreaterThan(a, b, slack);
        addConstraint(row);
    }

    public void addGreaterBarrier(SolverVariable a, SolverVariable b, boolean hasMatchConstraintWidgets) {
        ArrayRow row = createRow();
        SolverVariable slack = createSlackVariable();
        slack.strength = 0;
        ArrayRow createRowGreaterThan = row.createRowGreaterThan(a, b, slack, 0);
        if (hasMatchConstraintWidgets) {
            addSingleError(row, (int) (-1.0f * row.variables.get(slack)), 1);
        }
        addConstraint(row);
    }

    public void addLowerThan(SolverVariable a, SolverVariable b, int margin, int i) {
        int strength = i;
        ArrayRow row = createRow();
        SolverVariable slack = createSlackVariable();
        slack.strength = 0;
        ArrayRow createRowLowerThan = row.createRowLowerThan(a, b, slack, margin);
        if (strength != 6) {
            addSingleError(row, (int) (-1.0f * row.variables.get(slack)), strength);
        }
        addConstraint(row);
    }

    public void addLowerBarrier(SolverVariable a, SolverVariable b, boolean hasMatchConstraintWidgets) {
        ArrayRow row = createRow();
        SolverVariable slack = createSlackVariable();
        slack.strength = 0;
        ArrayRow createRowLowerThan = row.createRowLowerThan(a, b, slack, 0);
        if (hasMatchConstraintWidgets) {
            addSingleError(row, (int) (-1.0f * row.variables.get(slack)), 1);
        }
        addConstraint(row);
    }

    public void addCentering(SolverVariable a, SolverVariable b, int m1, float bias, SolverVariable c, SolverVariable d, int m2, int i) {
        int strength = i;
        ArrayRow row = createRow();
        ArrayRow createRowCentering = row.createRowCentering(a, b, m1, bias, c, d, m2);
        if (strength != 6) {
            ArrayRow addError = row.addError(this, strength);
        }
        addConstraint(row);
    }

    public void addRatio(SolverVariable a, SolverVariable b, SolverVariable c, SolverVariable d, float ratio, int i) {
        int strength = i;
        ArrayRow row = createRow();
        ArrayRow createRowDimensionRatio = row.createRowDimensionRatio(a, b, c, d, ratio);
        if (strength != 6) {
            ArrayRow addError = row.addError(this, strength);
        }
        addConstraint(row);
    }

    public ArrayRow addEquality(SolverVariable a, SolverVariable b, int margin, int i) {
        int strength = i;
        ArrayRow row = createRow();
        ArrayRow createRowEquals = row.createRowEquals(a, b, margin);
        if (strength != 6) {
            ArrayRow addError = row.addError(this, strength);
        }
        addConstraint(row);
        return row;
    }

    public void addEquality(SolverVariable solverVariable, int i) {
        SolverVariable a = solverVariable;
        int value = i;
        int idx = a.definitionId;
        if (a.definitionId != -1) {
            ArrayRow row = this.mRows[idx];
            if (row.isSimpleDefinition) {
                row.constantValue = (float) value;
            } else if (row.variables.currentSize == 0) {
                row.isSimpleDefinition = true;
                row.constantValue = (float) value;
            } else {
                ArrayRow newRow = createRow();
                ArrayRow createRowEquals = newRow.createRowEquals(a, value);
                addConstraint(newRow);
            }
        } else {
            ArrayRow row2 = createRow();
            ArrayRow createRowDefinition = row2.createRowDefinition(a, value);
            addConstraint(row2);
        }
    }

    public void addEquality(SolverVariable solverVariable, int i, int i2) {
        SolverVariable a = solverVariable;
        int value = i;
        int strength = i2;
        int idx = a.definitionId;
        if (a.definitionId != -1) {
            ArrayRow row = this.mRows[idx];
            if (row.isSimpleDefinition) {
                row.constantValue = (float) value;
                return;
            }
            ArrayRow newRow = createRow();
            ArrayRow createRowEquals = newRow.createRowEquals(a, value);
            ArrayRow addError = newRow.addError(this, strength);
            addConstraint(newRow);
            return;
        }
        ArrayRow row2 = createRow();
        ArrayRow createRowDefinition = row2.createRowDefinition(a, value);
        ArrayRow addError2 = row2.addError(this, strength);
        addConstraint(row2);
    }

    public static ArrayRow createRowEquals(LinearSystem linearSystem, SolverVariable variableA, SolverVariable variableB, int margin, boolean withError) {
        LinearSystem linearSystem2 = linearSystem;
        ArrayRow row = linearSystem2.createRow();
        ArrayRow createRowEquals = row.createRowEquals(variableA, variableB, margin);
        if (withError) {
            linearSystem2.addSingleError(row, 1);
        }
        return row;
    }

    public static ArrayRow createRowDimensionPercent(LinearSystem linearSystem, SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, float f, boolean withError) {
        LinearSystem linearSystem2 = linearSystem;
        SolverVariable variableA = solverVariable;
        SolverVariable variableB = solverVariable2;
        SolverVariable variableC = solverVariable3;
        float percent = f;
        ArrayRow row = linearSystem2.createRow();
        if (withError) {
            linearSystem2.addError(row);
        }
        return row.createRowDimensionPercent(variableA, variableB, variableC, percent);
    }

    public static ArrayRow createRowGreaterThan(LinearSystem linearSystem, SolverVariable variableA, SolverVariable variableB, int margin, boolean withError) {
        LinearSystem linearSystem2 = linearSystem;
        SolverVariable slack = linearSystem2.createSlackVariable();
        ArrayRow row = linearSystem2.createRow();
        ArrayRow createRowGreaterThan = row.createRowGreaterThan(variableA, variableB, slack, margin);
        if (withError) {
            linearSystem2.addSingleError(row, (int) (-1.0f * row.variables.get(slack)));
        }
        return row;
    }

    public static ArrayRow createRowLowerThan(LinearSystem linearSystem, SolverVariable variableA, SolverVariable variableB, int margin, boolean withError) {
        LinearSystem linearSystem2 = linearSystem;
        SolverVariable slack = linearSystem2.createSlackVariable();
        ArrayRow row = linearSystem2.createRow();
        ArrayRow createRowLowerThan = row.createRowLowerThan(variableA, variableB, slack, margin);
        if (withError) {
            linearSystem2.addSingleError(row, (int) (-1.0f * row.variables.get(slack)));
        }
        return row;
    }

    public static ArrayRow createRowCentering(LinearSystem linearSystem, SolverVariable variableA, SolverVariable variableB, int marginA, float bias, SolverVariable variableC, SolverVariable variableD, int marginB, boolean withError) {
        LinearSystem linearSystem2 = linearSystem;
        ArrayRow row = linearSystem2.createRow();
        ArrayRow createRowCentering = row.createRowCentering(variableA, variableB, marginA, bias, variableC, variableD, marginB);
        if (withError) {
            ArrayRow addError = row.addError(linearSystem2, 4);
        }
        return row;
    }

    public void addCenterPoint(ConstraintWidget constraintWidget, ConstraintWidget constraintWidget2, float f, int i) {
        ConstraintWidget widget = constraintWidget;
        ConstraintWidget target = constraintWidget2;
        float angle = f;
        int radius = i;
        SolverVariable Al = createObjectVariable(widget.getAnchor(ConstraintAnchor.Type.LEFT));
        SolverVariable At = createObjectVariable(widget.getAnchor(ConstraintAnchor.Type.TOP));
        SolverVariable Ar = createObjectVariable(widget.getAnchor(ConstraintAnchor.Type.RIGHT));
        SolverVariable Ab = createObjectVariable(widget.getAnchor(ConstraintAnchor.Type.BOTTOM));
        SolverVariable Bl = createObjectVariable(target.getAnchor(ConstraintAnchor.Type.LEFT));
        SolverVariable Bt = createObjectVariable(target.getAnchor(ConstraintAnchor.Type.TOP));
        SolverVariable Br = createObjectVariable(target.getAnchor(ConstraintAnchor.Type.RIGHT));
        SolverVariable Bb = createObjectVariable(target.getAnchor(ConstraintAnchor.Type.BOTTOM));
        ArrayRow row = createRow();
        ArrayRow createRowWithAngle = row.createRowWithAngle(At, Ab, Bt, Bb, (float) (Math.sin((double) angle) * ((double) radius)));
        addConstraint(row);
        ArrayRow row2 = createRow();
        ArrayRow createRowWithAngle2 = row2.createRowWithAngle(Al, Ar, Bl, Br, (float) (Math.cos((double) angle) * ((double) radius)));
        addConstraint(row2);
    }
}

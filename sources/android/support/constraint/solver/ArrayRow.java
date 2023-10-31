package android.support.constraint.solver;

import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.SolverVariable;
import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;

public class ArrayRow implements LinearSystem.Row {
    private static final boolean DEBUG = false;
    private static final float epsilon = 0.001f;
    float constantValue = 0.0f;
    boolean isSimpleDefinition = false;
    boolean used = false;
    SolverVariable variable = null;
    public final ArrayLinkedVariables variables;

    public ArrayRow(Cache cache) {
        ArrayLinkedVariables arrayLinkedVariables;
        new ArrayLinkedVariables(this, cache);
        this.variables = arrayLinkedVariables;
    }

    /* access modifiers changed from: package-private */
    public boolean hasKeyVariable() {
        return this.variable != null && (this.variable.mType == SolverVariable.Type.UNRESTRICTED || this.constantValue >= 0.0f);
    }

    public String toString() {
        return toReadableString();
    }

    /* access modifiers changed from: package-private */
    public String toReadableString() {
        StringBuilder sb;
        String s;
        StringBuilder sb2;
        String s2;
        StringBuilder sb3;
        StringBuilder sb4;
        StringBuilder sb5;
        StringBuilder sb6;
        StringBuilder sb7;
        StringBuilder sb8;
        StringBuilder sb9;
        StringBuilder sb10;
        String s3 = "";
        if (this.variable == null) {
            new StringBuilder();
            s = sb10.append(s3).append("0").toString();
        } else {
            new StringBuilder();
            s = sb.append(s3).append(this.variable).toString();
        }
        new StringBuilder();
        String s4 = sb2.append(s).append(" = ").toString();
        boolean addedVariable = false;
        if (this.constantValue != 0.0f) {
            new StringBuilder();
            s4 = sb9.append(s4).append(this.constantValue).toString();
            addedVariable = true;
        }
        int count = this.variables.currentSize;
        for (int i = 0; i < count; i++) {
            SolverVariable v = this.variables.getVariable(i);
            if (v != null) {
                float amount = this.variables.getVariableValue(i);
                if (amount != 0.0f) {
                    String name = v.toString();
                    if (!addedVariable) {
                        if (amount < 0.0f) {
                            new StringBuilder();
                            s2 = sb8.append(s2).append("- ").toString();
                            amount *= -1.0f;
                        }
                    } else if (amount > 0.0f) {
                        new StringBuilder();
                        s2 = sb5.append(s2).append(" + ").toString();
                    } else {
                        new StringBuilder();
                        s2 = sb4.append(s2).append(" - ").toString();
                        amount *= -1.0f;
                    }
                    if (amount == 1.0f) {
                        new StringBuilder();
                        s2 = sb7.append(s2).append(name).toString();
                    } else {
                        new StringBuilder();
                        s2 = sb6.append(s2).append(amount).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(name).toString();
                    }
                    addedVariable = true;
                }
            }
        }
        if (!addedVariable) {
            new StringBuilder();
            s2 = sb3.append(s2).append("0.0").toString();
        }
        return s2;
    }

    public void reset() {
        this.variable = null;
        this.variables.clear();
        this.constantValue = 0.0f;
        this.isSimpleDefinition = false;
    }

    /* access modifiers changed from: package-private */
    public boolean hasVariable(SolverVariable v) {
        return this.variables.containsKey(v);
    }

    /* access modifiers changed from: package-private */
    public ArrayRow createRowDefinition(SolverVariable solverVariable, int i) {
        SolverVariable variable2 = solverVariable;
        int value = i;
        this.variable = variable2;
        variable2.computedValue = (float) value;
        this.constantValue = (float) value;
        this.isSimpleDefinition = true;
        return this;
    }

    public ArrayRow createRowEquals(SolverVariable solverVariable, int i) {
        SolverVariable variable2 = solverVariable;
        int value = i;
        if (value < 0) {
            this.constantValue = (float) (-1 * value);
            this.variables.put(variable2, 1.0f);
        } else {
            this.constantValue = (float) value;
            this.variables.put(variable2, -1.0f);
        }
        return this;
    }

    public ArrayRow createRowEquals(SolverVariable solverVariable, SolverVariable solverVariable2, int i) {
        SolverVariable variableA = solverVariable;
        SolverVariable variableB = solverVariable2;
        int margin = i;
        boolean inverse = false;
        if (margin != 0) {
            int m = margin;
            if (m < 0) {
                m = -1 * m;
                inverse = true;
            }
            this.constantValue = (float) m;
        }
        if (!inverse) {
            this.variables.put(variableA, -1.0f);
            this.variables.put(variableB, 1.0f);
        } else {
            this.variables.put(variableA, 1.0f);
            this.variables.put(variableB, -1.0f);
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public ArrayRow addSingleError(SolverVariable error, int sign) {
        this.variables.put(error, (float) sign);
        return this;
    }

    public ArrayRow createRowGreaterThan(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, int i) {
        SolverVariable variableA = solverVariable;
        SolverVariable variableB = solverVariable2;
        SolverVariable slack = solverVariable3;
        int margin = i;
        boolean inverse = false;
        if (margin != 0) {
            int m = margin;
            if (m < 0) {
                m = -1 * m;
                inverse = true;
            }
            this.constantValue = (float) m;
        }
        if (!inverse) {
            this.variables.put(variableA, -1.0f);
            this.variables.put(variableB, 1.0f);
            this.variables.put(slack, 1.0f);
        } else {
            this.variables.put(variableA, 1.0f);
            this.variables.put(variableB, -1.0f);
            this.variables.put(slack, -1.0f);
        }
        return this;
    }

    public ArrayRow createRowGreaterThan(SolverVariable a, int b, SolverVariable solverVariable) {
        SolverVariable solverVariable2 = solverVariable;
        this.constantValue = (float) b;
        this.variables.put(a, -1.0f);
        return this;
    }

    public ArrayRow createRowLowerThan(SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, int i) {
        SolverVariable variableA = solverVariable;
        SolverVariable variableB = solverVariable2;
        SolverVariable slack = solverVariable3;
        int margin = i;
        boolean inverse = false;
        if (margin != 0) {
            int m = margin;
            if (m < 0) {
                m = -1 * m;
                inverse = true;
            }
            this.constantValue = (float) m;
        }
        if (!inverse) {
            this.variables.put(variableA, -1.0f);
            this.variables.put(variableB, 1.0f);
            this.variables.put(slack, -1.0f);
        } else {
            this.variables.put(variableA, 1.0f);
            this.variables.put(variableB, -1.0f);
            this.variables.put(slack, 1.0f);
        }
        return this;
    }

    public ArrayRow createRowEqualMatchDimensions(float f, float f2, float f3, SolverVariable solverVariable, SolverVariable solverVariable2, SolverVariable solverVariable3, SolverVariable solverVariable4) {
        float currentWeight = f;
        float totalWeights = f2;
        float nextWeight = f3;
        SolverVariable variableStartA = solverVariable;
        SolverVariable variableEndA = solverVariable2;
        SolverVariable variableStartB = solverVariable3;
        SolverVariable variableEndB = solverVariable4;
        this.constantValue = 0.0f;
        if (totalWeights == 0.0f || currentWeight == nextWeight) {
            this.variables.put(variableStartA, 1.0f);
            this.variables.put(variableEndA, -1.0f);
            this.variables.put(variableEndB, 1.0f);
            this.variables.put(variableStartB, -1.0f);
        } else if (currentWeight == 0.0f) {
            this.variables.put(variableStartA, 1.0f);
            this.variables.put(variableEndA, -1.0f);
        } else if (nextWeight == 0.0f) {
            this.variables.put(variableStartB, 1.0f);
            this.variables.put(variableEndB, -1.0f);
        } else {
            float w = (currentWeight / totalWeights) / (nextWeight / totalWeights);
            this.variables.put(variableStartA, 1.0f);
            this.variables.put(variableEndA, -1.0f);
            this.variables.put(variableEndB, w);
            this.variables.put(variableStartB, -w);
        }
        return this;
    }

    public ArrayRow createRowEqualDimension(float f, float f2, float f3, SolverVariable solverVariable, int i, SolverVariable solverVariable2, int i2, SolverVariable solverVariable3, int i3, SolverVariable solverVariable4, int i4) {
        float currentWeight = f;
        float totalWeights = f2;
        float nextWeight = f3;
        SolverVariable variableStartA = solverVariable;
        int marginStartA = i;
        SolverVariable variableEndA = solverVariable2;
        int marginEndA = i2;
        SolverVariable variableStartB = solverVariable3;
        int marginStartB = i3;
        SolverVariable variableEndB = solverVariable4;
        int marginEndB = i4;
        if (totalWeights == 0.0f || currentWeight == nextWeight) {
            this.constantValue = (float) (((-marginStartA) - marginEndA) + marginStartB + marginEndB);
            this.variables.put(variableStartA, 1.0f);
            this.variables.put(variableEndA, -1.0f);
            this.variables.put(variableEndB, 1.0f);
            this.variables.put(variableStartB, -1.0f);
        } else {
            float w = (currentWeight / totalWeights) / (nextWeight / totalWeights);
            this.constantValue = ((float) ((-marginStartA) - marginEndA)) + (w * ((float) marginStartB)) + (w * ((float) marginEndB));
            this.variables.put(variableStartA, 1.0f);
            this.variables.put(variableEndA, -1.0f);
            this.variables.put(variableEndB, w);
            this.variables.put(variableStartB, -w);
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public ArrayRow createRowCentering(SolverVariable solverVariable, SolverVariable solverVariable2, int i, float f, SolverVariable solverVariable3, SolverVariable solverVariable4, int i2) {
        SolverVariable variableA = solverVariable;
        SolverVariable variableB = solverVariable2;
        int marginA = i;
        float bias = f;
        SolverVariable variableC = solverVariable3;
        SolverVariable variableD = solverVariable4;
        int marginB = i2;
        if (variableB == variableC) {
            this.variables.put(variableA, 1.0f);
            this.variables.put(variableD, 1.0f);
            this.variables.put(variableB, -2.0f);
            return this;
        }
        if (bias == 0.5f) {
            this.variables.put(variableA, 1.0f);
            this.variables.put(variableB, -1.0f);
            this.variables.put(variableC, -1.0f);
            this.variables.put(variableD, 1.0f);
            if (marginA > 0 || marginB > 0) {
                this.constantValue = (float) ((-marginA) + marginB);
            }
        } else if (bias <= 0.0f) {
            this.variables.put(variableA, -1.0f);
            this.variables.put(variableB, 1.0f);
            this.constantValue = (float) marginA;
        } else if (bias >= 1.0f) {
            this.variables.put(variableC, -1.0f);
            this.variables.put(variableD, 1.0f);
            this.constantValue = (float) marginB;
        } else {
            this.variables.put(variableA, 1.0f * (1.0f - bias));
            this.variables.put(variableB, -1.0f * (1.0f - bias));
            this.variables.put(variableC, -1.0f * bias);
            this.variables.put(variableD, 1.0f * bias);
            if (marginA > 0 || marginB > 0) {
                this.constantValue = (((float) (-marginA)) * (1.0f - bias)) + (((float) marginB) * bias);
            }
        }
        return this;
    }

    public ArrayRow addError(LinearSystem linearSystem, int i) {
        LinearSystem system = linearSystem;
        int strength = i;
        this.variables.put(system.createErrorVariable(strength, "ep"), 1.0f);
        this.variables.put(system.createErrorVariable(strength, "em"), -1.0f);
        return this;
    }

    /* access modifiers changed from: package-private */
    public ArrayRow createRowDimensionPercent(SolverVariable variableA, SolverVariable variableB, SolverVariable variableC, float f) {
        float percent = f;
        this.variables.put(variableA, -1.0f);
        this.variables.put(variableB, 1.0f - percent);
        this.variables.put(variableC, percent);
        return this;
    }

    public ArrayRow createRowDimensionRatio(SolverVariable variableA, SolverVariable variableB, SolverVariable variableC, SolverVariable variableD, float f) {
        float ratio = f;
        this.variables.put(variableA, -1.0f);
        this.variables.put(variableB, 1.0f);
        this.variables.put(variableC, ratio);
        this.variables.put(variableD, -ratio);
        return this;
    }

    public ArrayRow createRowWithAngle(SolverVariable at, SolverVariable ab, SolverVariable bt, SolverVariable bb, float angleComponent) {
        this.variables.put(bt, 0.5f);
        this.variables.put(bb, 0.5f);
        this.variables.put(at, -0.5f);
        this.variables.put(ab, -0.5f);
        this.constantValue = -angleComponent;
        return this;
    }

    /* access modifiers changed from: package-private */
    public int sizeInBytes() {
        int size = 0;
        if (this.variable != null) {
            size = 0 + 4;
        }
        return size + 4 + 4 + this.variables.sizeInBytes();
    }

    /* access modifiers changed from: package-private */
    public void ensurePositiveConstant() {
        if (this.constantValue < 0.0f) {
            this.constantValue *= -1.0f;
            this.variables.invert();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean chooseSubject(LinearSystem system) {
        boolean addedExtra = false;
        SolverVariable pivotCandidate = this.variables.chooseSubject(system);
        if (pivotCandidate == null) {
            addedExtra = true;
        } else {
            pivot(pivotCandidate);
        }
        if (this.variables.currentSize == 0) {
            this.isSimpleDefinition = true;
        }
        return addedExtra;
    }

    /* access modifiers changed from: package-private */
    public SolverVariable pickPivot(SolverVariable exclude) {
        return this.variables.getPivotCandidate((boolean[]) null, exclude);
    }

    /* access modifiers changed from: package-private */
    public void pivot(SolverVariable solverVariable) {
        SolverVariable v = solverVariable;
        if (this.variable != null) {
            this.variables.put(this.variable, -1.0f);
            this.variable = null;
        }
        float amount = this.variables.remove(v, true) * -1.0f;
        this.variable = v;
        if (amount != 1.0f) {
            this.constantValue /= amount;
            this.variables.divideByAmount(amount);
        }
    }

    public boolean isEmpty() {
        return this.variable == null && this.constantValue == 0.0f && this.variables.currentSize == 0;
    }

    public SolverVariable getPivotCandidate(LinearSystem linearSystem, boolean[] avoid) {
        LinearSystem linearSystem2 = linearSystem;
        return this.variables.getPivotCandidate(avoid, (SolverVariable) null);
    }

    public void clear() {
        this.variables.clear();
        this.variable = null;
        this.constantValue = 0.0f;
    }

    public void initFromRow(LinearSystem.Row row) {
        LinearSystem.Row row2 = row;
        if (row2 instanceof ArrayRow) {
            ArrayRow copiedRow = (ArrayRow) row2;
            this.variable = null;
            this.variables.clear();
            for (int i = 0; i < copiedRow.variables.currentSize; i++) {
                this.variables.add(copiedRow.variables.getVariable(i), copiedRow.variables.getVariableValue(i), true);
            }
        }
    }

    public void addError(SolverVariable solverVariable) {
        SolverVariable error = solverVariable;
        float weight = 1.0f;
        if (error.strength == 1) {
            weight = 1.0f;
        } else if (error.strength == 2) {
            weight = 1000.0f;
        } else if (error.strength == 3) {
            weight = 1000000.0f;
        } else if (error.strength == 4) {
            weight = 1.0E9f;
        } else if (error.strength == 5) {
            weight = 1.0E12f;
        }
        this.variables.put(error, weight);
    }

    public SolverVariable getKey() {
        return this.variable;
    }
}

package android.support.constraint.solver;

public class GoalRow extends ArrayRow {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GoalRow(Cache cache) {
        super(cache);
    }

    public void addError(SolverVariable solverVariable) {
        SolverVariable error = solverVariable;
        super.addError(error);
        SolverVariable solverVariable2 = error;
        solverVariable2.usageInRowCount--;
    }
}

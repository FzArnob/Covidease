package android.support.constraint.solver;

import android.support.constraint.solver.Pools;

public class Cache {
    Pools.Pool<ArrayRow> arrayRowPool;
    SolverVariable[] mIndexedVariables = new SolverVariable[32];
    Pools.Pool<SolverVariable> solverVariablePool;

    public Cache() {
        Pools.Pool<ArrayRow> pool;
        Pools.Pool<SolverVariable> pool2;
        new Pools.SimplePool(256);
        this.arrayRowPool = pool;
        new Pools.SimplePool(256);
        this.solverVariablePool = pool2;
    }
}

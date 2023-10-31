package android.support.constraint.solver;

import java.util.ArrayList;

public class Metrics {
    public long additionalMeasures;
    public long barrierConnectionResolved;
    public long bfs;
    public long centerConnectionResolved;
    public long chainConnectionResolved;
    public long constraints;
    public long errors;
    public long extravariables;
    public long fullySolved;
    public long graphOptimizer;
    public long iterations;
    public long lastTableSize;
    public long matchConnectionResolved;
    public long maxRows;
    public long maxTableSize;
    public long maxVariables;
    public long measures;
    public long minimize;
    public long minimizeGoal;
    public long nonresolvedWidgets;
    public long oldresolvedWidgets;
    public long optimize;
    public long pivots;
    public ArrayList<String> problematicLayouts;
    public long resolutions;
    public long resolvedWidgets;
    public long simpleconstraints;
    public long slackvariables;
    public long tableSizeIncrease;
    public long variables;

    public Metrics() {
        ArrayList<String> arrayList;
        new ArrayList<>();
        this.problematicLayouts = arrayList;
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("\n*** Metrics ***\nmeasures: ").append(this.measures).append("\nadditionalMeasures: ").append(this.additionalMeasures).append("\nresolutions passes: ").append(this.resolutions).append("\ntable increases: ").append(this.tableSizeIncrease).append("\nmaxTableSize: ").append(this.maxTableSize).append("\nmaxVariables: ").append(this.maxVariables).append("\nmaxRows: ").append(this.maxRows).append("\n\nminimize: ").append(this.minimize).append("\nminimizeGoal: ").append(this.minimizeGoal).append("\nconstraints: ").append(this.constraints).append("\nsimpleconstraints: ").append(this.simpleconstraints).append("\noptimize: ").append(this.optimize).append("\niterations: ").append(this.iterations).append("\npivots: ").append(this.pivots).append("\nbfs: ").append(this.bfs).append("\nvariables: ").append(this.variables).append("\nerrors: ").append(this.errors).append("\nslackvariables: ").append(this.slackvariables).append("\nextravariables: ").append(this.extravariables).append("\nfullySolved: ").append(this.fullySolved).append("\ngraphOptimizer: ").append(this.graphOptimizer).append("\nresolvedWidgets: ").append(this.resolvedWidgets).append("\noldresolvedWidgets: ").append(this.oldresolvedWidgets).append("\nnonresolvedWidgets: ").append(this.nonresolvedWidgets).append("\ncenterConnectionResolved: ").append(this.centerConnectionResolved).append("\nmatchConnectionResolved: ").append(this.matchConnectionResolved).append("\nchainConnectionResolved: ").append(this.chainConnectionResolved).append("\nbarrierConnectionResolved: ").append(this.barrierConnectionResolved).append("\nproblematicsLayouts: ").append(this.problematicLayouts).append("\n").toString();
    }

    public void reset() {
        this.measures = 0;
        this.additionalMeasures = 0;
        this.resolutions = 0;
        this.tableSizeIncrease = 0;
        this.maxTableSize = 0;
        this.lastTableSize = 0;
        this.maxVariables = 0;
        this.maxRows = 0;
        this.minimize = 0;
        this.minimizeGoal = 0;
        this.constraints = 0;
        this.simpleconstraints = 0;
        this.optimize = 0;
        this.iterations = 0;
        this.pivots = 0;
        this.bfs = 0;
        this.variables = 0;
        this.errors = 0;
        this.slackvariables = 0;
        this.extravariables = 0;
        this.fullySolved = 0;
        this.graphOptimizer = 0;
        this.resolvedWidgets = 0;
        this.oldresolvedWidgets = 0;
        this.nonresolvedWidgets = 0;
        this.centerConnectionResolved = 0;
        this.matchConnectionResolved = 0;
        this.chainConnectionResolved = 0;
        this.barrierConnectionResolved = 0;
        this.problematicLayouts.clear();
    }
}

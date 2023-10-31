package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.constraint.solver.widgets.ConstraintWidget;
import java.util.ArrayList;

public class ConstraintTableLayout extends ConstraintWidgetContainer {
    public static final int ALIGN_CENTER = 0;
    private static final int ALIGN_FULL = 3;
    public static final int ALIGN_LEFT = 1;
    public static final int ALIGN_RIGHT = 2;
    private ArrayList<Guideline> mHorizontalGuidelines;
    private ArrayList<HorizontalSlice> mHorizontalSlices;
    private int mNumCols = 0;
    private int mNumRows = 0;
    private int mPadding = 8;
    private boolean mVerticalGrowth = true;
    private ArrayList<Guideline> mVerticalGuidelines;
    private ArrayList<VerticalSlice> mVerticalSlices;
    private LinearSystem system;

    class HorizontalSlice {
        ConstraintWidget bottom;
        int padding;
        final /* synthetic */ ConstraintTableLayout this$0;
        ConstraintWidget top;

        HorizontalSlice(ConstraintTableLayout this$02) {
            this.this$0 = this$02;
        }
    }

    class VerticalSlice {
        int alignment = 1;
        ConstraintWidget left;
        int padding;
        ConstraintWidget right;
        final /* synthetic */ ConstraintTableLayout this$0;

        VerticalSlice(ConstraintTableLayout this$02) {
            this.this$0 = this$02;
        }
    }

    public ConstraintTableLayout() {
        ArrayList<VerticalSlice> arrayList;
        ArrayList<HorizontalSlice> arrayList2;
        ArrayList<Guideline> arrayList3;
        ArrayList<Guideline> arrayList4;
        new ArrayList<>();
        this.mVerticalSlices = arrayList;
        new ArrayList<>();
        this.mHorizontalSlices = arrayList2;
        new ArrayList<>();
        this.mVerticalGuidelines = arrayList3;
        new ArrayList<>();
        this.mHorizontalGuidelines = arrayList4;
        this.system = null;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ConstraintTableLayout(int x, int y, int width, int height) {
        super(x, y, width, height);
        ArrayList<VerticalSlice> arrayList;
        ArrayList<HorizontalSlice> arrayList2;
        ArrayList<Guideline> arrayList3;
        ArrayList<Guideline> arrayList4;
        new ArrayList<>();
        this.mVerticalSlices = arrayList;
        new ArrayList<>();
        this.mHorizontalSlices = arrayList2;
        new ArrayList<>();
        this.mVerticalGuidelines = arrayList3;
        new ArrayList<>();
        this.mHorizontalGuidelines = arrayList4;
        this.system = null;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ConstraintTableLayout(int width, int height) {
        super(width, height);
        ArrayList<VerticalSlice> arrayList;
        ArrayList<HorizontalSlice> arrayList2;
        ArrayList<Guideline> arrayList3;
        ArrayList<Guideline> arrayList4;
        new ArrayList<>();
        this.mVerticalSlices = arrayList;
        new ArrayList<>();
        this.mHorizontalSlices = arrayList2;
        new ArrayList<>();
        this.mVerticalGuidelines = arrayList3;
        new ArrayList<>();
        this.mHorizontalGuidelines = arrayList4;
        this.system = null;
    }

    public String getType() {
        return "ConstraintTableLayout";
    }

    public int getNumRows() {
        return this.mNumRows;
    }

    public int getNumCols() {
        return this.mNumCols;
    }

    public int getPadding() {
        return this.mPadding;
    }

    public String getColumnsAlignmentRepresentation() {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        int numSlices = this.mVerticalSlices.size();
        String result = "";
        for (int i = 0; i < numSlices; i++) {
            VerticalSlice slice = this.mVerticalSlices.get(i);
            if (slice.alignment == 1) {
                new StringBuilder();
                result = sb4.append(result).append("L").toString();
            } else if (slice.alignment == 0) {
                new StringBuilder();
                result = sb3.append(result).append("C").toString();
            } else if (slice.alignment == 3) {
                new StringBuilder();
                result = sb2.append(result).append("F").toString();
            } else if (slice.alignment == 2) {
                new StringBuilder();
                result = sb.append(result).append("R").toString();
            }
        }
        return result;
    }

    public String getColumnAlignmentRepresentation(int column) {
        VerticalSlice slice = this.mVerticalSlices.get(column);
        if (slice.alignment == 1) {
            return "L";
        }
        if (slice.alignment == 0) {
            return "C";
        }
        if (slice.alignment == 3) {
            return "F";
        }
        if (slice.alignment == 2) {
            return "R";
        }
        return "!";
    }

    public void setNumCols(int i) {
        int num = i;
        if (this.mVerticalGrowth && this.mNumCols != num) {
            this.mNumCols = num;
            setVerticalSlices();
            setTableDimensions();
        }
    }

    public void setNumRows(int i) {
        int num = i;
        if (!this.mVerticalGrowth && this.mNumCols != num) {
            this.mNumRows = num;
            setHorizontalSlices();
            setTableDimensions();
        }
    }

    public boolean isVerticalGrowth() {
        return this.mVerticalGrowth;
    }

    public void setVerticalGrowth(boolean value) {
        boolean z = value;
        this.mVerticalGrowth = z;
    }

    public void setPadding(int i) {
        int padding = i;
        if (padding > 1) {
            this.mPadding = padding;
        }
    }

    public void setColumnAlignment(int i, int i2) {
        int column = i;
        int alignment = i2;
        if (column < this.mVerticalSlices.size()) {
            this.mVerticalSlices.get(column).alignment = alignment;
            setChildrenConnections();
        }
    }

    public void cycleColumnAlignment(int column) {
        VerticalSlice slice = this.mVerticalSlices.get(column);
        switch (slice.alignment) {
            case 0:
                slice.alignment = 2;
                break;
            case 1:
                slice.alignment = 0;
                break;
            case 2:
                slice.alignment = 1;
                break;
        }
        setChildrenConnections();
    }

    public void setColumnAlignment(String str) {
        String alignment = str;
        int n = alignment.length();
        for (int i = 0; i < n; i++) {
            char c = alignment.charAt(i);
            if (c == 'L') {
                setColumnAlignment(i, 1);
            } else if (c == 'C') {
                setColumnAlignment(i, 0);
            } else if (c == 'F') {
                setColumnAlignment(i, 3);
            } else if (c == 'R') {
                setColumnAlignment(i, 2);
            } else {
                setColumnAlignment(i, 0);
            }
        }
    }

    public ArrayList<Guideline> getVerticalGuidelines() {
        return this.mVerticalGuidelines;
    }

    public ArrayList<Guideline> getHorizontalGuidelines() {
        return this.mHorizontalGuidelines;
    }

    public void addToSolver(LinearSystem linearSystem) {
        LinearSystem system2 = linearSystem;
        super.addToSolver(system2);
        int count = this.mChildren.size();
        if (count != 0) {
            setTableDimensions();
            if (system2 == this.mSystem) {
                int num = this.mVerticalGuidelines.size();
                for (int i = 0; i < num; i++) {
                    Guideline guideline = this.mVerticalGuidelines.get(i);
                    guideline.setPositionRelaxed(getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT);
                    guideline.addToSolver(system2);
                }
                int num2 = this.mHorizontalGuidelines.size();
                for (int i2 = 0; i2 < num2; i2++) {
                    Guideline guideline2 = this.mHorizontalGuidelines.get(i2);
                    guideline2.setPositionRelaxed(getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT);
                    guideline2.addToSolver(system2);
                }
                for (int i3 = 0; i3 < count; i3++) {
                    ((ConstraintWidget) this.mChildren.get(i3)).addToSolver(system2);
                }
            }
        }
    }

    public void setTableDimensions() {
        int extra = 0;
        int count = this.mChildren.size();
        for (int i = 0; i < count; i++) {
            extra += ((ConstraintWidget) this.mChildren.get(i)).getContainerItemSkip();
        }
        int count2 = count + extra;
        if (this.mVerticalGrowth) {
            if (this.mNumCols == 0) {
                setNumCols(1);
            }
            int rows = count2 / this.mNumCols;
            if (rows * this.mNumCols < count2) {
                rows++;
            }
            if (this.mNumRows != rows || this.mVerticalGuidelines.size() != this.mNumCols - 1) {
                this.mNumRows = rows;
                setHorizontalSlices();
            } else {
                return;
            }
        } else {
            if (this.mNumRows == 0) {
                setNumRows(1);
            }
            int cols = count2 / this.mNumRows;
            if (cols * this.mNumRows < count2) {
                cols++;
            }
            if (this.mNumCols != cols || this.mHorizontalGuidelines.size() != this.mNumRows - 1) {
                this.mNumCols = cols;
                setVerticalSlices();
            } else {
                return;
            }
        }
        setChildrenConnections();
    }

    public void setDebugSolverName(LinearSystem linearSystem, String name) {
        LinearSystem s = linearSystem;
        this.system = s;
        super.setDebugSolverName(s, name);
        updateDebugSolverNames();
    }

    private void updateDebugSolverNames() {
        StringBuilder sb;
        StringBuilder sb2;
        if (this.system != null) {
            int num = this.mVerticalGuidelines.size();
            for (int i = 0; i < num; i++) {
                LinearSystem linearSystem = this.system;
                new StringBuilder();
                this.mVerticalGuidelines.get(i).setDebugSolverName(linearSystem, sb2.append(getDebugName()).append(".VG").append(i).toString());
            }
            int num2 = this.mHorizontalGuidelines.size();
            for (int i2 = 0; i2 < num2; i2++) {
                LinearSystem linearSystem2 = this.system;
                new StringBuilder();
                this.mHorizontalGuidelines.get(i2).setDebugSolverName(linearSystem2, sb.append(getDebugName()).append(".HG").append(i2).toString());
            }
        }
    }

    private void setVerticalSlices() {
        VerticalSlice verticalSlice;
        Guideline guideline;
        this.mVerticalSlices.clear();
        ConstraintWidget previous = this;
        float increment = 100.0f / ((float) this.mNumCols);
        float percent = increment;
        for (int i = 0; i < this.mNumCols; i++) {
            new VerticalSlice(this);
            VerticalSlice slice = verticalSlice;
            slice.left = previous;
            if (i < this.mNumCols - 1) {
                new Guideline();
                Guideline guideline2 = guideline;
                guideline2.setOrientation(1);
                guideline2.setParent(this);
                guideline2.setGuidePercent((int) percent);
                percent += increment;
                slice.right = guideline2;
                boolean add = this.mVerticalGuidelines.add(guideline2);
            } else {
                slice.right = this;
            }
            previous = slice.right;
            boolean add2 = this.mVerticalSlices.add(slice);
        }
        updateDebugSolverNames();
    }

    private void setHorizontalSlices() {
        HorizontalSlice horizontalSlice;
        Guideline guideline;
        this.mHorizontalSlices.clear();
        float increment = 100.0f / ((float) this.mNumRows);
        float percent = increment;
        ConstraintWidget previous = this;
        for (int i = 0; i < this.mNumRows; i++) {
            new HorizontalSlice(this);
            HorizontalSlice slice = horizontalSlice;
            slice.top = previous;
            if (i < this.mNumRows - 1) {
                new Guideline();
                Guideline guideline2 = guideline;
                guideline2.setOrientation(0);
                guideline2.setParent(this);
                guideline2.setGuidePercent((int) percent);
                percent += increment;
                slice.bottom = guideline2;
                boolean add = this.mHorizontalGuidelines.add(guideline2);
            } else {
                slice.bottom = this;
            }
            previous = slice.bottom;
            boolean add2 = this.mHorizontalSlices.add(slice);
        }
        updateDebugSolverNames();
    }

    private void setChildrenConnections() {
        int count = this.mChildren.size();
        int index = 0;
        for (int i = 0; i < count; i++) {
            ConstraintWidget target = (ConstraintWidget) this.mChildren.get(i);
            int index2 = index + target.getContainerItemSkip();
            int col = index2 % this.mNumCols;
            HorizontalSlice horizontalSlice = this.mHorizontalSlices.get(index2 / this.mNumCols);
            VerticalSlice verticalSlice = this.mVerticalSlices.get(col);
            ConstraintWidget targetLeft = verticalSlice.left;
            ConstraintWidget targetRight = verticalSlice.right;
            ConstraintWidget targetTop = horizontalSlice.top;
            ConstraintWidget targetBottom = horizontalSlice.bottom;
            boolean connect = target.getAnchor(ConstraintAnchor.Type.LEFT).connect(targetLeft.getAnchor(ConstraintAnchor.Type.LEFT), this.mPadding);
            if (targetRight instanceof Guideline) {
                boolean connect2 = target.getAnchor(ConstraintAnchor.Type.RIGHT).connect(targetRight.getAnchor(ConstraintAnchor.Type.LEFT), this.mPadding);
            } else {
                boolean connect3 = target.getAnchor(ConstraintAnchor.Type.RIGHT).connect(targetRight.getAnchor(ConstraintAnchor.Type.RIGHT), this.mPadding);
            }
            switch (verticalSlice.alignment) {
                case 1:
                    target.getAnchor(ConstraintAnchor.Type.LEFT).setStrength(ConstraintAnchor.Strength.STRONG);
                    target.getAnchor(ConstraintAnchor.Type.RIGHT).setStrength(ConstraintAnchor.Strength.WEAK);
                    break;
                case 2:
                    target.getAnchor(ConstraintAnchor.Type.LEFT).setStrength(ConstraintAnchor.Strength.WEAK);
                    target.getAnchor(ConstraintAnchor.Type.RIGHT).setStrength(ConstraintAnchor.Strength.STRONG);
                    break;
                case 3:
                    target.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
                    break;
            }
            boolean connect4 = target.getAnchor(ConstraintAnchor.Type.TOP).connect(targetTop.getAnchor(ConstraintAnchor.Type.TOP), this.mPadding);
            if (targetBottom instanceof Guideline) {
                boolean connect5 = target.getAnchor(ConstraintAnchor.Type.BOTTOM).connect(targetBottom.getAnchor(ConstraintAnchor.Type.TOP), this.mPadding);
            } else {
                boolean connect6 = target.getAnchor(ConstraintAnchor.Type.BOTTOM).connect(targetBottom.getAnchor(ConstraintAnchor.Type.BOTTOM), this.mPadding);
            }
            index = index2 + 1;
        }
    }

    public void updateFromSolver(LinearSystem linearSystem) {
        LinearSystem system2 = linearSystem;
        super.updateFromSolver(system2);
        if (system2 == this.mSystem) {
            int num = this.mVerticalGuidelines.size();
            for (int i = 0; i < num; i++) {
                this.mVerticalGuidelines.get(i).updateFromSolver(system2);
            }
            int num2 = this.mHorizontalGuidelines.size();
            for (int i2 = 0; i2 < num2; i2++) {
                this.mHorizontalGuidelines.get(i2).updateFromSolver(system2);
            }
        }
    }

    public boolean handlesInternalConstraints() {
        return true;
    }

    public void computeGuidelinesPercentPositions() {
        int num = this.mVerticalGuidelines.size();
        for (int i = 0; i < num; i++) {
            this.mVerticalGuidelines.get(i).inferRelativePercentPosition();
        }
        int num2 = this.mHorizontalGuidelines.size();
        for (int i2 = 0; i2 < num2; i2++) {
            this.mHorizontalGuidelines.get(i2).inferRelativePercentPosition();
        }
    }
}

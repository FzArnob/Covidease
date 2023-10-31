package android.support.constraint;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.constraint.C0025R;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Constraints;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ConstraintSet {
    private static final int ALPHA = 43;
    private static final int BARRIER_ALLOWS_GONE_WIDGETS = 74;
    private static final int BARRIER_DIRECTION = 72;
    private static final int BARRIER_TYPE = 1;
    public static final int BASELINE = 5;
    private static final int BASELINE_TO_BASELINE = 1;
    public static final int BOTTOM = 4;
    private static final int BOTTOM_MARGIN = 2;
    private static final int BOTTOM_TO_BOTTOM = 3;
    private static final int BOTTOM_TO_TOP = 4;
    public static final int CHAIN_PACKED = 2;
    public static final int CHAIN_SPREAD = 0;
    public static final int CHAIN_SPREAD_INSIDE = 1;
    private static final int CHAIN_USE_RTL = 71;
    private static final int CIRCLE = 61;
    private static final int CIRCLE_ANGLE = 63;
    private static final int CIRCLE_RADIUS = 62;
    private static final int CONSTRAINT_REFERENCED_IDS = 73;
    private static final boolean DEBUG = false;
    private static final int DIMENSION_RATIO = 5;
    private static final int EDITOR_ABSOLUTE_X = 6;
    private static final int EDITOR_ABSOLUTE_Y = 7;
    private static final int ELEVATION = 44;
    public static final int END = 7;
    private static final int END_MARGIN = 8;
    private static final int END_TO_END = 9;
    private static final int END_TO_START = 10;
    public static final int GONE = 8;
    private static final int GONE_BOTTOM_MARGIN = 11;
    private static final int GONE_END_MARGIN = 12;
    private static final int GONE_LEFT_MARGIN = 13;
    private static final int GONE_RIGHT_MARGIN = 14;
    private static final int GONE_START_MARGIN = 15;
    private static final int GONE_TOP_MARGIN = 16;
    private static final int GUIDE_BEGIN = 17;
    private static final int GUIDE_END = 18;
    private static final int GUIDE_PERCENT = 19;
    private static final int HEIGHT_DEFAULT = 55;
    private static final int HEIGHT_MAX = 57;
    private static final int HEIGHT_MIN = 59;
    private static final int HEIGHT_PERCENT = 70;
    public static final int HORIZONTAL = 0;
    private static final int HORIZONTAL_BIAS = 20;
    public static final int HORIZONTAL_GUIDELINE = 0;
    private static final int HORIZONTAL_STYLE = 41;
    private static final int HORIZONTAL_WEIGHT = 39;
    public static final int INVISIBLE = 4;
    private static final int LAYOUT_HEIGHT = 21;
    private static final int LAYOUT_VISIBILITY = 22;
    private static final int LAYOUT_WIDTH = 23;
    public static final int LEFT = 1;
    private static final int LEFT_MARGIN = 24;
    private static final int LEFT_TO_LEFT = 25;
    private static final int LEFT_TO_RIGHT = 26;
    public static final int MATCH_CONSTRAINT = 0;
    public static final int MATCH_CONSTRAINT_SPREAD = 0;
    public static final int MATCH_CONSTRAINT_WRAP = 1;
    private static final int ORIENTATION = 27;
    public static final int PARENT_ID = 0;
    public static final int RIGHT = 2;
    private static final int RIGHT_MARGIN = 28;
    private static final int RIGHT_TO_LEFT = 29;
    private static final int RIGHT_TO_RIGHT = 30;
    private static final int ROTATION = 60;
    private static final int ROTATION_X = 45;
    private static final int ROTATION_Y = 46;
    private static final int SCALE_X = 47;
    private static final int SCALE_Y = 48;
    public static final int START = 6;
    private static final int START_MARGIN = 31;
    private static final int START_TO_END = 32;
    private static final int START_TO_START = 33;
    private static final String TAG = "ConstraintSet";
    public static final int TOP = 3;
    private static final int TOP_MARGIN = 34;
    private static final int TOP_TO_BOTTOM = 35;
    private static final int TOP_TO_TOP = 36;
    private static final int TRANSFORM_PIVOT_X = 49;
    private static final int TRANSFORM_PIVOT_Y = 50;
    private static final int TRANSLATION_X = 51;
    private static final int TRANSLATION_Y = 52;
    private static final int TRANSLATION_Z = 53;
    public static final int UNSET = -1;
    private static final int UNUSED = 75;
    public static final int VERTICAL = 1;
    private static final int VERTICAL_BIAS = 37;
    public static final int VERTICAL_GUIDELINE = 1;
    private static final int VERTICAL_STYLE = 42;
    private static final int VERTICAL_WEIGHT = 40;
    private static final int VIEW_ID = 38;
    private static final int[] VISIBILITY_FLAGS = {0, 4, 8};
    public static final int VISIBLE = 0;
    private static final int WIDTH_DEFAULT = 54;
    private static final int WIDTH_MAX = 56;
    private static final int WIDTH_MIN = 58;
    private static final int WIDTH_PERCENT = 69;
    public static final int WRAP_CONTENT = -2;
    private static SparseIntArray mapToConstant;
    private HashMap<Integer, Constraint> mConstraints;

    public ConstraintSet() {
        HashMap<Integer, Constraint> hashMap;
        new HashMap<>();
        this.mConstraints = hashMap;
    }

    static {
        SparseIntArray sparseIntArray;
        new SparseIntArray();
        mapToConstant = sparseIntArray;
        mapToConstant.append(C0025R.styleable.ConstraintSet_layout_constraintLeft_toLeftOf, 25);
        mapToConstant.append(C0025R.styleable.ConstraintSet_layout_constraintLeft_toRightOf, 26);
        mapToConstant.append(C0025R.styleable.ConstraintSet_layout_constraintRight_toLeftOf, 29);
        mapToConstant.append(C0025R.styleable.ConstraintSet_layout_constraintRight_toRightOf, 30);
        mapToConstant.append(C0025R.styleable.ConstraintSet_layout_constraintTop_toTopOf, 36);
        mapToConstant.append(C0025R.styleable.ConstraintSet_layout_constraintTop_toBottomOf, 35);
        mapToConstant.append(C0025R.styleable.ConstraintSet_layout_constraintBottom_toTopOf, 4);
        mapToConstant.append(C0025R.styleable.ConstraintSet_layout_constraintBottom_toBottomOf, 3);
        mapToConstant.append(C0025R.styleable.ConstraintSet_layout_constraintBaseline_toBaselineOf, 1);
        mapToConstant.append(C0025R.styleable.ConstraintSet_layout_editor_absoluteX, 6);
        mapToConstant.append(C0025R.styleable.ConstraintSet_layout_editor_absoluteY, 7);
        mapToConstant.append(C0025R.styleable.ConstraintSet_layout_constraintGuide_begin, 17);
        mapToConstant.append(C0025R.styleable.ConstraintSet_layout_constraintGuide_end, 18);
        mapToConstant.append(C0025R.styleable.ConstraintSet_layout_constraintGuide_percent, 19);
        mapToConstant.append(C0025R.styleable.ConstraintSet_android_orientation, 27);
        mapToConstant.append(C0025R.styleable.ConstraintSet_layout_constraintStart_toEndOf, 32);
        mapToConstant.append(C0025R.styleable.ConstraintSet_layout_constraintStart_toStartOf, 33);
        mapToConstant.append(C0025R.styleable.ConstraintSet_layout_constraintEnd_toStartOf, 10);
        mapToConstant.append(C0025R.styleable.ConstraintSet_layout_constraintEnd_toEndOf, 9);
        mapToConstant.append(C0025R.styleable.ConstraintSet_layout_goneMarginLeft, 13);
        mapToConstant.append(C0025R.styleable.ConstraintSet_layout_goneMarginTop, 16);
        mapToConstant.append(C0025R.styleable.ConstraintSet_layout_goneMarginRight, 14);
        mapToConstant.append(C0025R.styleable.ConstraintSet_layout_goneMarginBottom, 11);
        mapToConstant.append(C0025R.styleable.ConstraintSet_layout_goneMarginStart, 15);
        mapToConstant.append(C0025R.styleable.ConstraintSet_layout_goneMarginEnd, 12);
        mapToConstant.append(C0025R.styleable.ConstraintSet_layout_constraintVertical_weight, 40);
        mapToConstant.append(C0025R.styleable.ConstraintSet_layout_constraintHorizontal_weight, 39);
        mapToConstant.append(C0025R.styleable.ConstraintSet_layout_constraintHorizontal_chainStyle, 41);
        mapToConstant.append(C0025R.styleable.ConstraintSet_layout_constraintVertical_chainStyle, 42);
        mapToConstant.append(C0025R.styleable.ConstraintSet_layout_constraintHorizontal_bias, 20);
        mapToConstant.append(C0025R.styleable.ConstraintSet_layout_constraintVertical_bias, 37);
        mapToConstant.append(C0025R.styleable.ConstraintSet_layout_constraintDimensionRatio, 5);
        mapToConstant.append(C0025R.styleable.ConstraintSet_layout_constraintLeft_creator, 75);
        mapToConstant.append(C0025R.styleable.ConstraintSet_layout_constraintTop_creator, 75);
        mapToConstant.append(C0025R.styleable.ConstraintSet_layout_constraintRight_creator, 75);
        mapToConstant.append(C0025R.styleable.ConstraintSet_layout_constraintBottom_creator, 75);
        mapToConstant.append(C0025R.styleable.ConstraintSet_layout_constraintBaseline_creator, 75);
        mapToConstant.append(C0025R.styleable.ConstraintSet_android_layout_marginLeft, 24);
        mapToConstant.append(C0025R.styleable.ConstraintSet_android_layout_marginRight, 28);
        mapToConstant.append(C0025R.styleable.ConstraintSet_android_layout_marginStart, 31);
        mapToConstant.append(C0025R.styleable.ConstraintSet_android_layout_marginEnd, 8);
        mapToConstant.append(C0025R.styleable.ConstraintSet_android_layout_marginTop, 34);
        mapToConstant.append(C0025R.styleable.ConstraintSet_android_layout_marginBottom, 2);
        mapToConstant.append(C0025R.styleable.ConstraintSet_android_layout_width, 23);
        mapToConstant.append(C0025R.styleable.ConstraintSet_android_layout_height, 21);
        mapToConstant.append(C0025R.styleable.ConstraintSet_android_visibility, 22);
        mapToConstant.append(C0025R.styleable.ConstraintSet_android_alpha, 43);
        mapToConstant.append(C0025R.styleable.ConstraintSet_android_elevation, 44);
        mapToConstant.append(C0025R.styleable.ConstraintSet_android_rotationX, 45);
        mapToConstant.append(C0025R.styleable.ConstraintSet_android_rotationY, 46);
        mapToConstant.append(C0025R.styleable.ConstraintSet_android_rotation, 60);
        mapToConstant.append(C0025R.styleable.ConstraintSet_android_scaleX, 47);
        mapToConstant.append(C0025R.styleable.ConstraintSet_android_scaleY, 48);
        mapToConstant.append(C0025R.styleable.ConstraintSet_android_transformPivotX, 49);
        mapToConstant.append(C0025R.styleable.ConstraintSet_android_transformPivotY, 50);
        mapToConstant.append(C0025R.styleable.ConstraintSet_android_translationX, 51);
        mapToConstant.append(C0025R.styleable.ConstraintSet_android_translationY, 52);
        mapToConstant.append(C0025R.styleable.ConstraintSet_android_translationZ, 53);
        mapToConstant.append(C0025R.styleable.ConstraintSet_layout_constraintWidth_default, 54);
        mapToConstant.append(C0025R.styleable.ConstraintSet_layout_constraintHeight_default, 55);
        mapToConstant.append(C0025R.styleable.ConstraintSet_layout_constraintWidth_max, 56);
        mapToConstant.append(C0025R.styleable.ConstraintSet_layout_constraintHeight_max, 57);
        mapToConstant.append(C0025R.styleable.ConstraintSet_layout_constraintWidth_min, 58);
        mapToConstant.append(C0025R.styleable.ConstraintSet_layout_constraintHeight_min, 59);
        mapToConstant.append(C0025R.styleable.ConstraintSet_layout_constraintCircle, 61);
        mapToConstant.append(C0025R.styleable.ConstraintSet_layout_constraintCircleRadius, 62);
        mapToConstant.append(C0025R.styleable.ConstraintSet_layout_constraintCircleAngle, 63);
        mapToConstant.append(C0025R.styleable.ConstraintSet_android_id, 38);
        mapToConstant.append(C0025R.styleable.ConstraintSet_layout_constraintWidth_percent, 69);
        mapToConstant.append(C0025R.styleable.ConstraintSet_layout_constraintHeight_percent, 70);
        mapToConstant.append(C0025R.styleable.ConstraintSet_chainUseRtl, 71);
        mapToConstant.append(C0025R.styleable.ConstraintSet_barrierDirection, 72);
        mapToConstant.append(C0025R.styleable.ConstraintSet_constraint_referenced_ids, 73);
        mapToConstant.append(C0025R.styleable.ConstraintSet_barrierAllowsGoneWidgets, 74);
    }

    public Constraint getParameters(int mId) {
        return get(mId);
    }

    private static class Constraint {
        static final int UNSET = -1;
        public float alpha;
        public boolean applyElevation;
        public int baselineToBaseline;
        public int bottomMargin;
        public int bottomToBottom;
        public int bottomToTop;
        public float circleAngle;
        public int circleConstraint;
        public int circleRadius;
        public boolean constrainedHeight;
        public boolean constrainedWidth;
        public String dimensionRatio;
        public int editorAbsoluteX;
        public int editorAbsoluteY;
        public float elevation;
        public int endMargin;
        public int endToEnd;
        public int endToStart;
        public int goneBottomMargin;
        public int goneEndMargin;
        public int goneLeftMargin;
        public int goneRightMargin;
        public int goneStartMargin;
        public int goneTopMargin;
        public int guideBegin;
        public int guideEnd;
        public float guidePercent;
        public int heightDefault;
        public int heightMax;
        public int heightMin;
        public float heightPercent;
        public float horizontalBias;
        public int horizontalChainStyle;
        public float horizontalWeight;
        public int leftMargin;
        public int leftToLeft;
        public int leftToRight;
        public boolean mBarrierAllowsGoneWidgets;
        public int mBarrierDirection;
        public int mHeight;
        public int mHelperType;
        boolean mIsGuideline;
        public String mReferenceIdString;
        public int[] mReferenceIds;
        int mViewId;
        public int mWidth;
        public int orientation;
        public int rightMargin;
        public int rightToLeft;
        public int rightToRight;
        public float rotation;
        public float rotationX;
        public float rotationY;
        public float scaleX;
        public float scaleY;
        public int startMargin;
        public int startToEnd;
        public int startToStart;
        public int topMargin;
        public int topToBottom;
        public int topToTop;
        public float transformPivotX;
        public float transformPivotY;
        public float translationX;
        public float translationY;
        public float translationZ;
        public float verticalBias;
        public int verticalChainStyle;
        public float verticalWeight;
        public int visibility;
        public int widthDefault;
        public int widthMax;
        public int widthMin;
        public float widthPercent;

        private Constraint() {
            this.mIsGuideline = false;
            this.guideBegin = -1;
            this.guideEnd = -1;
            this.guidePercent = -1.0f;
            this.leftToLeft = -1;
            this.leftToRight = -1;
            this.rightToLeft = -1;
            this.rightToRight = -1;
            this.topToTop = -1;
            this.topToBottom = -1;
            this.bottomToTop = -1;
            this.bottomToBottom = -1;
            this.baselineToBaseline = -1;
            this.startToEnd = -1;
            this.startToStart = -1;
            this.endToStart = -1;
            this.endToEnd = -1;
            this.horizontalBias = 0.5f;
            this.verticalBias = 0.5f;
            this.dimensionRatio = null;
            this.circleConstraint = -1;
            this.circleRadius = 0;
            this.circleAngle = 0.0f;
            this.editorAbsoluteX = -1;
            this.editorAbsoluteY = -1;
            this.orientation = -1;
            this.leftMargin = -1;
            this.rightMargin = -1;
            this.topMargin = -1;
            this.bottomMargin = -1;
            this.endMargin = -1;
            this.startMargin = -1;
            this.visibility = 0;
            this.goneLeftMargin = -1;
            this.goneTopMargin = -1;
            this.goneRightMargin = -1;
            this.goneBottomMargin = -1;
            this.goneEndMargin = -1;
            this.goneStartMargin = -1;
            this.verticalWeight = 0.0f;
            this.horizontalWeight = 0.0f;
            this.horizontalChainStyle = 0;
            this.verticalChainStyle = 0;
            this.alpha = 1.0f;
            this.applyElevation = false;
            this.elevation = 0.0f;
            this.rotation = 0.0f;
            this.rotationX = 0.0f;
            this.rotationY = 0.0f;
            this.scaleX = 1.0f;
            this.scaleY = 1.0f;
            this.transformPivotX = Float.NaN;
            this.transformPivotY = Float.NaN;
            this.translationX = 0.0f;
            this.translationY = 0.0f;
            this.translationZ = 0.0f;
            this.constrainedWidth = false;
            this.constrainedHeight = false;
            this.widthDefault = 0;
            this.heightDefault = 0;
            this.widthMax = -1;
            this.heightMax = -1;
            this.widthMin = -1;
            this.heightMin = -1;
            this.widthPercent = 1.0f;
            this.heightPercent = 1.0f;
            this.mBarrierAllowsGoneWidgets = false;
            this.mBarrierDirection = -1;
            this.mHelperType = -1;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ Constraint(C00241 r4) {
            this();
            C00241 r1 = r4;
        }

        public Constraint clone() {
            Constraint constraint;
            new Constraint();
            Constraint clone = constraint;
            clone.mIsGuideline = this.mIsGuideline;
            clone.mWidth = this.mWidth;
            clone.mHeight = this.mHeight;
            clone.guideBegin = this.guideBegin;
            clone.guideEnd = this.guideEnd;
            clone.guidePercent = this.guidePercent;
            clone.leftToLeft = this.leftToLeft;
            clone.leftToRight = this.leftToRight;
            clone.rightToLeft = this.rightToLeft;
            clone.rightToRight = this.rightToRight;
            clone.topToTop = this.topToTop;
            clone.topToBottom = this.topToBottom;
            clone.bottomToTop = this.bottomToTop;
            clone.bottomToBottom = this.bottomToBottom;
            clone.baselineToBaseline = this.baselineToBaseline;
            clone.startToEnd = this.startToEnd;
            clone.startToStart = this.startToStart;
            clone.endToStart = this.endToStart;
            clone.endToEnd = this.endToEnd;
            clone.horizontalBias = this.horizontalBias;
            clone.verticalBias = this.verticalBias;
            clone.dimensionRatio = this.dimensionRatio;
            clone.editorAbsoluteX = this.editorAbsoluteX;
            clone.editorAbsoluteY = this.editorAbsoluteY;
            clone.horizontalBias = this.horizontalBias;
            clone.horizontalBias = this.horizontalBias;
            clone.horizontalBias = this.horizontalBias;
            clone.horizontalBias = this.horizontalBias;
            clone.horizontalBias = this.horizontalBias;
            clone.orientation = this.orientation;
            clone.leftMargin = this.leftMargin;
            clone.rightMargin = this.rightMargin;
            clone.topMargin = this.topMargin;
            clone.bottomMargin = this.bottomMargin;
            clone.endMargin = this.endMargin;
            clone.startMargin = this.startMargin;
            clone.visibility = this.visibility;
            clone.goneLeftMargin = this.goneLeftMargin;
            clone.goneTopMargin = this.goneTopMargin;
            clone.goneRightMargin = this.goneRightMargin;
            clone.goneBottomMargin = this.goneBottomMargin;
            clone.goneEndMargin = this.goneEndMargin;
            clone.goneStartMargin = this.goneStartMargin;
            clone.verticalWeight = this.verticalWeight;
            clone.horizontalWeight = this.horizontalWeight;
            clone.horizontalChainStyle = this.horizontalChainStyle;
            clone.verticalChainStyle = this.verticalChainStyle;
            clone.alpha = this.alpha;
            clone.applyElevation = this.applyElevation;
            clone.elevation = this.elevation;
            clone.rotation = this.rotation;
            clone.rotationX = this.rotationX;
            clone.rotationY = this.rotationY;
            clone.scaleX = this.scaleX;
            clone.scaleY = this.scaleY;
            clone.transformPivotX = this.transformPivotX;
            clone.transformPivotY = this.transformPivotY;
            clone.translationX = this.translationX;
            clone.translationY = this.translationY;
            clone.translationZ = this.translationZ;
            clone.constrainedWidth = this.constrainedWidth;
            clone.constrainedHeight = this.constrainedHeight;
            clone.widthDefault = this.widthDefault;
            clone.heightDefault = this.heightDefault;
            clone.widthMax = this.widthMax;
            clone.heightMax = this.heightMax;
            clone.widthMin = this.widthMin;
            clone.heightMin = this.heightMin;
            clone.widthPercent = this.widthPercent;
            clone.heightPercent = this.heightPercent;
            clone.mBarrierDirection = this.mBarrierDirection;
            clone.mHelperType = this.mHelperType;
            if (this.mReferenceIds != null) {
                clone.mReferenceIds = Arrays.copyOf(this.mReferenceIds, this.mReferenceIds.length);
            }
            clone.circleConstraint = this.circleConstraint;
            clone.circleRadius = this.circleRadius;
            clone.circleAngle = this.circleAngle;
            clone.mBarrierAllowsGoneWidgets = this.mBarrierAllowsGoneWidgets;
            return clone;
        }

        /* access modifiers changed from: private */
        public void fillFromConstraints(ConstraintHelper constraintHelper, int viewId, Constraints.LayoutParams param) {
            ConstraintHelper helper = constraintHelper;
            fillFromConstraints(viewId, param);
            if (helper instanceof Barrier) {
                this.mHelperType = 1;
                Barrier barrier = (Barrier) helper;
                this.mBarrierDirection = barrier.getType();
                this.mReferenceIds = barrier.getReferencedIds();
            }
        }

        /* access modifiers changed from: private */
        public void fillFromConstraints(int viewId, Constraints.LayoutParams layoutParams) {
            Constraints.LayoutParams param = layoutParams;
            fillFrom(viewId, param);
            this.alpha = param.alpha;
            this.rotation = param.rotation;
            this.rotationX = param.rotationX;
            this.rotationY = param.rotationY;
            this.scaleX = param.scaleX;
            this.scaleY = param.scaleY;
            this.transformPivotX = param.transformPivotX;
            this.transformPivotY = param.transformPivotY;
            this.translationX = param.translationX;
            this.translationY = param.translationY;
            this.translationZ = param.translationZ;
            this.elevation = param.elevation;
            this.applyElevation = param.applyElevation;
        }

        /* access modifiers changed from: private */
        public void fillFrom(int viewId, ConstraintLayout.LayoutParams layoutParams) {
            ConstraintLayout.LayoutParams param = layoutParams;
            this.mViewId = viewId;
            this.leftToLeft = param.leftToLeft;
            this.leftToRight = param.leftToRight;
            this.rightToLeft = param.rightToLeft;
            this.rightToRight = param.rightToRight;
            this.topToTop = param.topToTop;
            this.topToBottom = param.topToBottom;
            this.bottomToTop = param.bottomToTop;
            this.bottomToBottom = param.bottomToBottom;
            this.baselineToBaseline = param.baselineToBaseline;
            this.startToEnd = param.startToEnd;
            this.startToStart = param.startToStart;
            this.endToStart = param.endToStart;
            this.endToEnd = param.endToEnd;
            this.horizontalBias = param.horizontalBias;
            this.verticalBias = param.verticalBias;
            this.dimensionRatio = param.dimensionRatio;
            this.circleConstraint = param.circleConstraint;
            this.circleRadius = param.circleRadius;
            this.circleAngle = param.circleAngle;
            this.editorAbsoluteX = param.editorAbsoluteX;
            this.editorAbsoluteY = param.editorAbsoluteY;
            this.orientation = param.orientation;
            this.guidePercent = param.guidePercent;
            this.guideBegin = param.guideBegin;
            this.guideEnd = param.guideEnd;
            this.mWidth = param.width;
            this.mHeight = param.height;
            this.leftMargin = param.leftMargin;
            this.rightMargin = param.rightMargin;
            this.topMargin = param.topMargin;
            this.bottomMargin = param.bottomMargin;
            this.verticalWeight = param.verticalWeight;
            this.horizontalWeight = param.horizontalWeight;
            this.verticalChainStyle = param.verticalChainStyle;
            this.horizontalChainStyle = param.horizontalChainStyle;
            this.constrainedWidth = param.constrainedWidth;
            this.constrainedHeight = param.constrainedHeight;
            this.widthDefault = param.matchConstraintDefaultWidth;
            this.heightDefault = param.matchConstraintDefaultHeight;
            this.constrainedWidth = param.constrainedWidth;
            this.widthMax = param.matchConstraintMaxWidth;
            this.heightMax = param.matchConstraintMaxHeight;
            this.widthMin = param.matchConstraintMinWidth;
            this.heightMin = param.matchConstraintMinHeight;
            this.widthPercent = param.matchConstraintPercentWidth;
            this.heightPercent = param.matchConstraintPercentHeight;
            if (Build.VERSION.SDK_INT >= 17) {
                this.endMargin = param.getMarginEnd();
                this.startMargin = param.getMarginStart();
            }
        }

        public void applyTo(ConstraintLayout.LayoutParams layoutParams) {
            ConstraintLayout.LayoutParams param = layoutParams;
            param.leftToLeft = this.leftToLeft;
            param.leftToRight = this.leftToRight;
            param.rightToLeft = this.rightToLeft;
            param.rightToRight = this.rightToRight;
            param.topToTop = this.topToTop;
            param.topToBottom = this.topToBottom;
            param.bottomToTop = this.bottomToTop;
            param.bottomToBottom = this.bottomToBottom;
            param.baselineToBaseline = this.baselineToBaseline;
            param.startToEnd = this.startToEnd;
            param.startToStart = this.startToStart;
            param.endToStart = this.endToStart;
            param.endToEnd = this.endToEnd;
            param.leftMargin = this.leftMargin;
            param.rightMargin = this.rightMargin;
            param.topMargin = this.topMargin;
            param.bottomMargin = this.bottomMargin;
            param.goneStartMargin = this.goneStartMargin;
            param.goneEndMargin = this.goneEndMargin;
            param.horizontalBias = this.horizontalBias;
            param.verticalBias = this.verticalBias;
            param.circleConstraint = this.circleConstraint;
            param.circleRadius = this.circleRadius;
            param.circleAngle = this.circleAngle;
            param.dimensionRatio = this.dimensionRatio;
            param.editorAbsoluteX = this.editorAbsoluteX;
            param.editorAbsoluteY = this.editorAbsoluteY;
            param.verticalWeight = this.verticalWeight;
            param.horizontalWeight = this.horizontalWeight;
            param.verticalChainStyle = this.verticalChainStyle;
            param.horizontalChainStyle = this.horizontalChainStyle;
            param.constrainedWidth = this.constrainedWidth;
            param.constrainedHeight = this.constrainedHeight;
            param.matchConstraintDefaultWidth = this.widthDefault;
            param.matchConstraintDefaultHeight = this.heightDefault;
            param.matchConstraintMaxWidth = this.widthMax;
            param.matchConstraintMaxHeight = this.heightMax;
            param.matchConstraintMinWidth = this.widthMin;
            param.matchConstraintMinHeight = this.heightMin;
            param.matchConstraintPercentWidth = this.widthPercent;
            param.matchConstraintPercentHeight = this.heightPercent;
            param.orientation = this.orientation;
            param.guidePercent = this.guidePercent;
            param.guideBegin = this.guideBegin;
            param.guideEnd = this.guideEnd;
            param.width = this.mWidth;
            param.height = this.mHeight;
            if (Build.VERSION.SDK_INT >= 17) {
                param.setMarginStart(this.startMargin);
                param.setMarginEnd(this.endMargin);
            }
            param.validate();
        }
    }

    public void clone(Context context, int constraintLayoutId) {
        clone((ConstraintLayout) LayoutInflater.from(context).inflate(constraintLayoutId, (ViewGroup) null));
    }

    public void clone(ConstraintSet constraintSet) {
        ConstraintSet set = constraintSet;
        this.mConstraints.clear();
        for (Integer key : set.mConstraints.keySet()) {
            Constraint put = this.mConstraints.put(key, set.mConstraints.get(key).clone());
        }
    }

    public void clone(ConstraintLayout constraintLayout) {
        Throwable th;
        Object obj;
        ConstraintLayout constraintLayout2 = constraintLayout;
        int count = constraintLayout2.getChildCount();
        this.mConstraints.clear();
        for (int i = 0; i < count; i++) {
            View view = constraintLayout2.getChildAt(i);
            ConstraintLayout.LayoutParams param = (ConstraintLayout.LayoutParams) view.getLayoutParams();
            int id = view.getId();
            if (id == -1) {
                Throwable th2 = th;
                new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
                throw th2;
            }
            if (!this.mConstraints.containsKey(Integer.valueOf(id))) {
                new Constraint((C00241) null);
                Constraint put = this.mConstraints.put(Integer.valueOf(id), obj);
            }
            Constraint constraint = this.mConstraints.get(Integer.valueOf(id));
            constraint.fillFrom(id, param);
            constraint.visibility = view.getVisibility();
            if (Build.VERSION.SDK_INT >= 17) {
                constraint.alpha = view.getAlpha();
                constraint.rotation = view.getRotation();
                constraint.rotationX = view.getRotationX();
                constraint.rotationY = view.getRotationY();
                constraint.scaleX = view.getScaleX();
                constraint.scaleY = view.getScaleY();
                float pivotX = view.getPivotX();
                float pivotY = view.getPivotY();
                if (!(((double) pivotX) == 0.0d && ((double) pivotY) == 0.0d)) {
                    constraint.transformPivotX = pivotX;
                    constraint.transformPivotY = pivotY;
                }
                constraint.translationX = view.getTranslationX();
                constraint.translationY = view.getTranslationY();
                if (Build.VERSION.SDK_INT >= 21) {
                    constraint.translationZ = view.getTranslationZ();
                    if (constraint.applyElevation) {
                        constraint.elevation = view.getElevation();
                    }
                }
            }
            if (view instanceof Barrier) {
                Barrier barrier = (Barrier) view;
                constraint.mBarrierAllowsGoneWidgets = barrier.allowsGoneWidget();
                constraint.mReferenceIds = barrier.getReferencedIds();
                constraint.mBarrierDirection = barrier.getType();
            }
        }
    }

    public void clone(Constraints constraints) {
        Throwable th;
        Object obj;
        Constraints constraints2 = constraints;
        int count = constraints2.getChildCount();
        this.mConstraints.clear();
        for (int i = 0; i < count; i++) {
            View view = constraints2.getChildAt(i);
            Constraints.LayoutParams param = (Constraints.LayoutParams) view.getLayoutParams();
            int id = view.getId();
            if (id == -1) {
                Throwable th2 = th;
                new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
                throw th2;
            }
            if (!this.mConstraints.containsKey(Integer.valueOf(id))) {
                new Constraint((C00241) null);
                Constraint put = this.mConstraints.put(Integer.valueOf(id), obj);
            }
            Constraint constraint = this.mConstraints.get(Integer.valueOf(id));
            if (view instanceof ConstraintHelper) {
                constraint.fillFromConstraints((ConstraintHelper) view, id, param);
            }
            constraint.fillFromConstraints(id, param);
        }
    }

    public void applyTo(ConstraintLayout constraintLayout) {
        ConstraintLayout constraintLayout2 = constraintLayout;
        applyToInternal(constraintLayout2);
        constraintLayout2.setConstraintSet((ConstraintSet) null);
    }

    /* access modifiers changed from: package-private */
    public void applyToInternal(ConstraintLayout constraintLayout) {
        HashSet hashSet;
        Guideline guideline;
        Barrier barrier;
        Throwable th;
        ConstraintLayout constraintLayout2 = constraintLayout;
        int count = constraintLayout2.getChildCount();
        new HashSet(this.mConstraints.keySet());
        HashSet hashSet2 = hashSet;
        for (int i = 0; i < count; i++) {
            View view = constraintLayout2.getChildAt(i);
            int id = view.getId();
            if (id == -1) {
                Throwable th2 = th;
                new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
                throw th2;
            }
            if (this.mConstraints.containsKey(Integer.valueOf(id))) {
                boolean remove = hashSet2.remove(Integer.valueOf(id));
                Constraint constraint = this.mConstraints.get(Integer.valueOf(id));
                if (view instanceof Barrier) {
                    constraint.mHelperType = 1;
                }
                if (constraint.mHelperType != -1) {
                    switch (constraint.mHelperType) {
                        case 1:
                            Barrier barrier2 = (Barrier) view;
                            barrier2.setId(id);
                            barrier2.setType(constraint.mBarrierDirection);
                            barrier2.setAllowsGoneWidget(constraint.mBarrierAllowsGoneWidgets);
                            if (constraint.mReferenceIds == null) {
                                if (constraint.mReferenceIdString != null) {
                                    constraint.mReferenceIds = convertReferenceString(barrier2, constraint.mReferenceIdString);
                                    barrier2.setReferencedIds(constraint.mReferenceIds);
                                    break;
                                }
                            } else {
                                barrier2.setReferencedIds(constraint.mReferenceIds);
                                break;
                            }
                            break;
                    }
                }
                ConstraintLayout.LayoutParams param = (ConstraintLayout.LayoutParams) view.getLayoutParams();
                constraint.applyTo(param);
                view.setLayoutParams(param);
                view.setVisibility(constraint.visibility);
                if (Build.VERSION.SDK_INT >= 17) {
                    view.setAlpha(constraint.alpha);
                    view.setRotation(constraint.rotation);
                    view.setRotationX(constraint.rotationX);
                    view.setRotationY(constraint.rotationY);
                    view.setScaleX(constraint.scaleX);
                    view.setScaleY(constraint.scaleY);
                    if (!Float.isNaN(constraint.transformPivotX)) {
                        view.setPivotX(constraint.transformPivotX);
                    }
                    if (!Float.isNaN(constraint.transformPivotY)) {
                        view.setPivotY(constraint.transformPivotY);
                    }
                    view.setTranslationX(constraint.translationX);
                    view.setTranslationY(constraint.translationY);
                    if (Build.VERSION.SDK_INT >= 21) {
                        view.setTranslationZ(constraint.translationZ);
                        if (constraint.applyElevation) {
                            view.setElevation(constraint.elevation);
                        }
                    }
                }
            }
        }
        Iterator it = hashSet2.iterator();
        while (it.hasNext()) {
            Integer id2 = (Integer) it.next();
            Constraint constraint2 = this.mConstraints.get(id2);
            if (constraint2.mHelperType != -1) {
                switch (constraint2.mHelperType) {
                    case 1:
                        new Barrier(constraintLayout2.getContext());
                        Barrier barrier3 = barrier;
                        barrier3.setId(id2.intValue());
                        if (constraint2.mReferenceIds != null) {
                            barrier3.setReferencedIds(constraint2.mReferenceIds);
                        } else if (constraint2.mReferenceIdString != null) {
                            constraint2.mReferenceIds = convertReferenceString(barrier3, constraint2.mReferenceIdString);
                            barrier3.setReferencedIds(constraint2.mReferenceIds);
                        }
                        barrier3.setType(constraint2.mBarrierDirection);
                        ConstraintLayout.LayoutParams param2 = constraintLayout2.generateDefaultLayoutParams();
                        barrier3.validateParams();
                        constraint2.applyTo(param2);
                        constraintLayout2.addView(barrier3, param2);
                        break;
                }
            }
            if (constraint2.mIsGuideline) {
                new Guideline(constraintLayout2.getContext());
                Guideline g = guideline;
                g.setId(id2.intValue());
                ConstraintLayout.LayoutParams param3 = constraintLayout2.generateDefaultLayoutParams();
                constraint2.applyTo(param3);
                constraintLayout2.addView(g, param3);
            }
        }
    }

    public void center(int i, int i2, int i3, int i4, int i5, int i6, int i7, float f) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        int centerID = i;
        int firstID = i2;
        int firstSide = i3;
        int firstMargin = i4;
        int secondId = i5;
        int secondSide = i6;
        int secondMargin = i7;
        float bias = f;
        if (firstMargin < 0) {
            Throwable th4 = th3;
            new IllegalArgumentException("margin must be > 0");
            throw th4;
        } else if (secondMargin < 0) {
            Throwable th5 = th2;
            new IllegalArgumentException("margin must be > 0");
            throw th5;
        } else if (bias <= 0.0f || bias > 1.0f) {
            Throwable th6 = th;
            new IllegalArgumentException("bias must be between 0 and 1 inclusive");
            throw th6;
        } else if (firstSide == 1 || firstSide == 2) {
            connect(centerID, 1, firstID, firstSide, firstMargin);
            connect(centerID, 2, secondId, secondSide, secondMargin);
            this.mConstraints.get(Integer.valueOf(centerID)).horizontalBias = bias;
        } else if (firstSide == 6 || firstSide == 7) {
            connect(centerID, 6, firstID, firstSide, firstMargin);
            connect(centerID, 7, secondId, secondSide, secondMargin);
            this.mConstraints.get(Integer.valueOf(centerID)).horizontalBias = bias;
        } else {
            connect(centerID, 3, firstID, firstSide, firstMargin);
            connect(centerID, 4, secondId, secondSide, secondMargin);
            this.mConstraints.get(Integer.valueOf(centerID)).verticalBias = bias;
        }
    }

    public void centerHorizontally(int i, int leftId, int leftSide, int leftMargin, int rightId, int rightSide, int rightMargin, float bias) {
        int centerID = i;
        connect(centerID, 1, leftId, leftSide, leftMargin);
        connect(centerID, 2, rightId, rightSide, rightMargin);
        this.mConstraints.get(Integer.valueOf(centerID)).horizontalBias = bias;
    }

    public void centerHorizontallyRtl(int i, int startId, int startSide, int startMargin, int endId, int endSide, int endMargin, float bias) {
        int centerID = i;
        connect(centerID, 6, startId, startSide, startMargin);
        connect(centerID, 7, endId, endSide, endMargin);
        this.mConstraints.get(Integer.valueOf(centerID)).horizontalBias = bias;
    }

    public void centerVertically(int i, int topId, int topSide, int topMargin, int bottomId, int bottomSide, int bottomMargin, float bias) {
        int centerID = i;
        connect(centerID, 3, topId, topSide, topMargin);
        connect(centerID, 4, bottomId, bottomSide, bottomMargin);
        this.mConstraints.get(Integer.valueOf(centerID)).verticalBias = bias;
    }

    public void createVerticalChain(int i, int i2, int i3, int i4, int[] iArr, float[] fArr, int i5) {
        Throwable th;
        Throwable th2;
        int topId = i;
        int topSide = i2;
        int bottomId = i3;
        int bottomSide = i4;
        int[] chainIds = iArr;
        float[] weights = fArr;
        int style = i5;
        if (chainIds.length < 2) {
            Throwable th3 = th2;
            new IllegalArgumentException("must have 2 or more widgets in a chain");
            throw th3;
        } else if (weights == null || weights.length == chainIds.length) {
            if (weights != null) {
                get(chainIds[0]).verticalWeight = weights[0];
            }
            get(chainIds[0]).verticalChainStyle = style;
            connect(chainIds[0], 3, topId, topSide, 0);
            for (int i6 = 1; i6 < chainIds.length; i6++) {
                int i7 = chainIds[i6];
                connect(chainIds[i6], 3, chainIds[i6 - 1], 4, 0);
                connect(chainIds[i6 - 1], 4, chainIds[i6], 3, 0);
                if (weights != null) {
                    get(chainIds[i6]).verticalWeight = weights[i6];
                }
            }
            connect(chainIds[chainIds.length - 1], 4, bottomId, bottomSide, 0);
        } else {
            Throwable th4 = th;
            new IllegalArgumentException("must have 2 or more widgets in a chain");
            throw th4;
        }
    }

    public void createHorizontalChain(int leftId, int leftSide, int rightId, int rightSide, int[] chainIds, float[] weights, int style) {
        createHorizontalChain(leftId, leftSide, rightId, rightSide, chainIds, weights, style, 1, 2);
    }

    public void createHorizontalChainRtl(int startId, int startSide, int endId, int endSide, int[] chainIds, float[] weights, int style) {
        createHorizontalChain(startId, startSide, endId, endSide, chainIds, weights, style, 6, 7);
    }

    private void createHorizontalChain(int i, int i2, int i3, int i4, int[] iArr, float[] fArr, int i5, int i6, int i7) {
        Throwable th;
        Throwable th2;
        int leftId = i;
        int leftSide = i2;
        int rightId = i3;
        int rightSide = i4;
        int[] chainIds = iArr;
        float[] weights = fArr;
        int style = i5;
        int left = i6;
        int right = i7;
        if (chainIds.length < 2) {
            Throwable th3 = th2;
            new IllegalArgumentException("must have 2 or more widgets in a chain");
            throw th3;
        } else if (weights == null || weights.length == chainIds.length) {
            if (weights != null) {
                get(chainIds[0]).horizontalWeight = weights[0];
            }
            get(chainIds[0]).horizontalChainStyle = style;
            connect(chainIds[0], left, leftId, leftSide, -1);
            for (int i8 = 1; i8 < chainIds.length; i8++) {
                int i9 = chainIds[i8];
                connect(chainIds[i8], left, chainIds[i8 - 1], right, -1);
                connect(chainIds[i8 - 1], right, chainIds[i8], left, -1);
                if (weights != null) {
                    get(chainIds[i8]).horizontalWeight = weights[i8];
                }
            }
            connect(chainIds[chainIds.length - 1], right, rightId, rightSide, -1);
        } else {
            Throwable th4 = th;
            new IllegalArgumentException("must have 2 or more widgets in a chain");
            throw th4;
        }
    }

    public void connect(int i, int i2, int i3, int i4, int i5) {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        Throwable th3;
        StringBuilder sb3;
        Throwable th4;
        StringBuilder sb4;
        Throwable th5;
        StringBuilder sb5;
        Throwable th6;
        StringBuilder sb6;
        Throwable th7;
        StringBuilder sb7;
        Throwable th8;
        StringBuilder sb8;
        Object obj;
        int startID = i;
        int startSide = i2;
        int endID = i3;
        int endSide = i4;
        int margin = i5;
        if (!this.mConstraints.containsKey(Integer.valueOf(startID))) {
            new Constraint((C00241) null);
            Constraint put = this.mConstraints.put(Integer.valueOf(startID), obj);
        }
        Constraint constraint = this.mConstraints.get(Integer.valueOf(startID));
        switch (startSide) {
            case 1:
                if (endSide == 1) {
                    constraint.leftToLeft = endID;
                    constraint.leftToRight = -1;
                } else if (endSide == 2) {
                    constraint.leftToRight = endID;
                    constraint.leftToLeft = -1;
                } else {
                    Throwable th9 = th7;
                    new StringBuilder();
                    new IllegalArgumentException(sb7.append("Left to ").append(sideToString(endSide)).append(" undefined").toString());
                    throw th9;
                }
                constraint.leftMargin = margin;
                return;
            case 2:
                if (endSide == 1) {
                    constraint.rightToLeft = endID;
                    constraint.rightToRight = -1;
                } else if (endSide == 2) {
                    constraint.rightToRight = endID;
                    constraint.rightToLeft = -1;
                } else {
                    Throwable th10 = th6;
                    new StringBuilder();
                    new IllegalArgumentException(sb6.append("right to ").append(sideToString(endSide)).append(" undefined").toString());
                    throw th10;
                }
                constraint.rightMargin = margin;
                return;
            case 3:
                if (endSide == 3) {
                    constraint.topToTop = endID;
                    constraint.topToBottom = -1;
                    constraint.baselineToBaseline = -1;
                } else if (endSide == 4) {
                    constraint.topToBottom = endID;
                    constraint.topToTop = -1;
                    constraint.baselineToBaseline = -1;
                } else {
                    Throwable th11 = th5;
                    new StringBuilder();
                    new IllegalArgumentException(sb5.append("right to ").append(sideToString(endSide)).append(" undefined").toString());
                    throw th11;
                }
                constraint.topMargin = margin;
                return;
            case 4:
                if (endSide == 4) {
                    constraint.bottomToBottom = endID;
                    constraint.bottomToTop = -1;
                    constraint.baselineToBaseline = -1;
                } else if (endSide == 3) {
                    constraint.bottomToTop = endID;
                    constraint.bottomToBottom = -1;
                    constraint.baselineToBaseline = -1;
                } else {
                    Throwable th12 = th4;
                    new StringBuilder();
                    new IllegalArgumentException(sb4.append("right to ").append(sideToString(endSide)).append(" undefined").toString());
                    throw th12;
                }
                constraint.bottomMargin = margin;
                return;
            case 5:
                if (endSide == 5) {
                    constraint.baselineToBaseline = endID;
                    constraint.bottomToBottom = -1;
                    constraint.bottomToTop = -1;
                    constraint.topToTop = -1;
                    constraint.topToBottom = -1;
                    return;
                }
                Throwable th13 = th3;
                new StringBuilder();
                new IllegalArgumentException(sb3.append("right to ").append(sideToString(endSide)).append(" undefined").toString());
                throw th13;
            case 6:
                if (endSide == 6) {
                    constraint.startToStart = endID;
                    constraint.startToEnd = -1;
                } else if (endSide == 7) {
                    constraint.startToEnd = endID;
                    constraint.startToStart = -1;
                } else {
                    Throwable th14 = th2;
                    new StringBuilder();
                    new IllegalArgumentException(sb2.append("right to ").append(sideToString(endSide)).append(" undefined").toString());
                    throw th14;
                }
                constraint.startMargin = margin;
                return;
            case 7:
                if (endSide == 7) {
                    constraint.endToEnd = endID;
                    constraint.endToStart = -1;
                } else if (endSide == 6) {
                    constraint.endToStart = endID;
                    constraint.endToEnd = -1;
                } else {
                    Throwable th15 = th;
                    new StringBuilder();
                    new IllegalArgumentException(sb.append("right to ").append(sideToString(endSide)).append(" undefined").toString());
                    throw th15;
                }
                constraint.endMargin = margin;
                return;
            default:
                Throwable th16 = th8;
                new StringBuilder();
                new IllegalArgumentException(sb8.append(sideToString(startSide)).append(" to ").append(sideToString(endSide)).append(" unknown").toString());
                throw th16;
        }
    }

    public void connect(int i, int i2, int i3, int i4) {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        Throwable th3;
        StringBuilder sb3;
        Throwable th4;
        StringBuilder sb4;
        Throwable th5;
        StringBuilder sb5;
        Throwable th6;
        StringBuilder sb6;
        Throwable th7;
        StringBuilder sb7;
        Throwable th8;
        StringBuilder sb8;
        Object obj;
        int startID = i;
        int startSide = i2;
        int endID = i3;
        int endSide = i4;
        if (!this.mConstraints.containsKey(Integer.valueOf(startID))) {
            new Constraint((C00241) null);
            Constraint put = this.mConstraints.put(Integer.valueOf(startID), obj);
        }
        Constraint constraint = this.mConstraints.get(Integer.valueOf(startID));
        switch (startSide) {
            case 1:
                if (endSide == 1) {
                    constraint.leftToLeft = endID;
                    constraint.leftToRight = -1;
                    return;
                } else if (endSide == 2) {
                    constraint.leftToRight = endID;
                    constraint.leftToLeft = -1;
                    return;
                } else {
                    Throwable th9 = th7;
                    new StringBuilder();
                    new IllegalArgumentException(sb7.append("left to ").append(sideToString(endSide)).append(" undefined").toString());
                    throw th9;
                }
            case 2:
                if (endSide == 1) {
                    constraint.rightToLeft = endID;
                    constraint.rightToRight = -1;
                    return;
                } else if (endSide == 2) {
                    constraint.rightToRight = endID;
                    constraint.rightToLeft = -1;
                    return;
                } else {
                    Throwable th10 = th6;
                    new StringBuilder();
                    new IllegalArgumentException(sb6.append("right to ").append(sideToString(endSide)).append(" undefined").toString());
                    throw th10;
                }
            case 3:
                if (endSide == 3) {
                    constraint.topToTop = endID;
                    constraint.topToBottom = -1;
                    constraint.baselineToBaseline = -1;
                    return;
                } else if (endSide == 4) {
                    constraint.topToBottom = endID;
                    constraint.topToTop = -1;
                    constraint.baselineToBaseline = -1;
                    return;
                } else {
                    Throwable th11 = th5;
                    new StringBuilder();
                    new IllegalArgumentException(sb5.append("right to ").append(sideToString(endSide)).append(" undefined").toString());
                    throw th11;
                }
            case 4:
                if (endSide == 4) {
                    constraint.bottomToBottom = endID;
                    constraint.bottomToTop = -1;
                    constraint.baselineToBaseline = -1;
                    return;
                } else if (endSide == 3) {
                    constraint.bottomToTop = endID;
                    constraint.bottomToBottom = -1;
                    constraint.baselineToBaseline = -1;
                    return;
                } else {
                    Throwable th12 = th4;
                    new StringBuilder();
                    new IllegalArgumentException(sb4.append("right to ").append(sideToString(endSide)).append(" undefined").toString());
                    throw th12;
                }
            case 5:
                if (endSide == 5) {
                    constraint.baselineToBaseline = endID;
                    constraint.bottomToBottom = -1;
                    constraint.bottomToTop = -1;
                    constraint.topToTop = -1;
                    constraint.topToBottom = -1;
                    return;
                }
                Throwable th13 = th3;
                new StringBuilder();
                new IllegalArgumentException(sb3.append("right to ").append(sideToString(endSide)).append(" undefined").toString());
                throw th13;
            case 6:
                if (endSide == 6) {
                    constraint.startToStart = endID;
                    constraint.startToEnd = -1;
                    return;
                } else if (endSide == 7) {
                    constraint.startToEnd = endID;
                    constraint.startToStart = -1;
                    return;
                } else {
                    Throwable th14 = th2;
                    new StringBuilder();
                    new IllegalArgumentException(sb2.append("right to ").append(sideToString(endSide)).append(" undefined").toString());
                    throw th14;
                }
            case 7:
                if (endSide == 7) {
                    constraint.endToEnd = endID;
                    constraint.endToStart = -1;
                    return;
                } else if (endSide == 6) {
                    constraint.endToStart = endID;
                    constraint.endToEnd = -1;
                    return;
                } else {
                    Throwable th15 = th;
                    new StringBuilder();
                    new IllegalArgumentException(sb.append("right to ").append(sideToString(endSide)).append(" undefined").toString());
                    throw th15;
                }
            default:
                Throwable th16 = th8;
                new StringBuilder();
                new IllegalArgumentException(sb8.append(sideToString(startSide)).append(" to ").append(sideToString(endSide)).append(" unknown").toString());
                throw th16;
        }
    }

    public void centerHorizontally(int i, int i2) {
        int viewId = i;
        int toView = i2;
        if (toView == 0) {
            center(viewId, 0, 1, 0, 0, 2, 0, 0.5f);
        } else {
            center(viewId, toView, 2, 0, toView, 1, 0, 0.5f);
        }
    }

    public void centerHorizontallyRtl(int i, int i2) {
        int viewId = i;
        int toView = i2;
        if (toView == 0) {
            center(viewId, 0, 6, 0, 0, 7, 0, 0.5f);
        } else {
            center(viewId, toView, 7, 0, toView, 6, 0, 0.5f);
        }
    }

    public void centerVertically(int i, int i2) {
        int viewId = i;
        int toView = i2;
        if (toView == 0) {
            center(viewId, 0, 3, 0, 0, 4, 0, 0.5f);
        } else {
            center(viewId, toView, 4, 0, toView, 3, 0, 0.5f);
        }
    }

    public void clear(int viewId) {
        Constraint remove = this.mConstraints.remove(Integer.valueOf(viewId));
    }

    public void clear(int i, int i2) {
        Throwable th;
        int viewId = i;
        int anchor = i2;
        if (this.mConstraints.containsKey(Integer.valueOf(viewId))) {
            Constraint constraint = this.mConstraints.get(Integer.valueOf(viewId));
            switch (anchor) {
                case 1:
                    constraint.leftToRight = -1;
                    constraint.leftToLeft = -1;
                    constraint.leftMargin = -1;
                    constraint.goneLeftMargin = -1;
                    return;
                case 2:
                    constraint.rightToRight = -1;
                    constraint.rightToLeft = -1;
                    constraint.rightMargin = -1;
                    constraint.goneRightMargin = -1;
                    return;
                case 3:
                    constraint.topToBottom = -1;
                    constraint.topToTop = -1;
                    constraint.topMargin = -1;
                    constraint.goneTopMargin = -1;
                    return;
                case 4:
                    constraint.bottomToTop = -1;
                    constraint.bottomToBottom = -1;
                    constraint.bottomMargin = -1;
                    constraint.goneBottomMargin = -1;
                    return;
                case 5:
                    constraint.baselineToBaseline = -1;
                    return;
                case 6:
                    constraint.startToEnd = -1;
                    constraint.startToStart = -1;
                    constraint.startMargin = -1;
                    constraint.goneStartMargin = -1;
                    return;
                case 7:
                    constraint.endToStart = -1;
                    constraint.endToEnd = -1;
                    constraint.endMargin = -1;
                    constraint.goneEndMargin = -1;
                    return;
                default:
                    Throwable th2 = th;
                    new IllegalArgumentException("unknown constraint");
                    throw th2;
            }
        }
    }

    public void setMargin(int viewId, int anchor, int i) {
        Throwable th;
        Throwable th2;
        int value = i;
        Constraint constraint = get(viewId);
        switch (anchor) {
            case 1:
                constraint.leftMargin = value;
                return;
            case 2:
                constraint.rightMargin = value;
                return;
            case 3:
                constraint.topMargin = value;
                return;
            case 4:
                constraint.bottomMargin = value;
                return;
            case 5:
                Throwable th3 = th;
                new IllegalArgumentException("baseline does not support margins");
                throw th3;
            case 6:
                constraint.startMargin = value;
                return;
            case 7:
                constraint.endMargin = value;
                return;
            default:
                Throwable th4 = th2;
                new IllegalArgumentException("unknown constraint");
                throw th4;
        }
    }

    public void setGoneMargin(int viewId, int anchor, int i) {
        Throwable th;
        Throwable th2;
        int value = i;
        Constraint constraint = get(viewId);
        switch (anchor) {
            case 1:
                constraint.goneLeftMargin = value;
                return;
            case 2:
                constraint.goneRightMargin = value;
                return;
            case 3:
                constraint.goneTopMargin = value;
                return;
            case 4:
                constraint.goneBottomMargin = value;
                return;
            case 5:
                Throwable th3 = th;
                new IllegalArgumentException("baseline does not support margins");
                throw th3;
            case 6:
                constraint.goneStartMargin = value;
                return;
            case 7:
                constraint.goneEndMargin = value;
                return;
            default:
                Throwable th4 = th2;
                new IllegalArgumentException("unknown constraint");
                throw th4;
        }
    }

    public void setHorizontalBias(int viewId, float bias) {
        get(viewId).horizontalBias = bias;
    }

    public void setVerticalBias(int viewId, float bias) {
        get(viewId).verticalBias = bias;
    }

    public void setDimensionRatio(int viewId, String ratio) {
        get(viewId).dimensionRatio = ratio;
    }

    public void setVisibility(int viewId, int visibility) {
        get(viewId).visibility = visibility;
    }

    public void setAlpha(int viewId, float alpha) {
        get(viewId).alpha = alpha;
    }

    public boolean getApplyElevation(int viewId) {
        return get(viewId).applyElevation;
    }

    public void setApplyElevation(int viewId, boolean apply) {
        get(viewId).applyElevation = apply;
    }

    public void setElevation(int i, float elevation) {
        int viewId = i;
        get(viewId).elevation = elevation;
        get(viewId).applyElevation = true;
    }

    public void setRotation(int viewId, float rotation) {
        get(viewId).rotation = rotation;
    }

    public void setRotationX(int viewId, float rotationX) {
        get(viewId).rotationX = rotationX;
    }

    public void setRotationY(int viewId, float rotationY) {
        get(viewId).rotationY = rotationY;
    }

    public void setScaleX(int viewId, float scaleX) {
        get(viewId).scaleX = scaleX;
    }

    public void setScaleY(int viewId, float scaleY) {
        get(viewId).scaleY = scaleY;
    }

    public void setTransformPivotX(int viewId, float transformPivotX) {
        get(viewId).transformPivotX = transformPivotX;
    }

    public void setTransformPivotY(int viewId, float transformPivotY) {
        get(viewId).transformPivotY = transformPivotY;
    }

    public void setTransformPivot(int viewId, float transformPivotX, float transformPivotY) {
        Constraint constraint = get(viewId);
        constraint.transformPivotY = transformPivotY;
        constraint.transformPivotX = transformPivotX;
    }

    public void setTranslationX(int viewId, float translationX) {
        get(viewId).translationX = translationX;
    }

    public void setTranslationY(int viewId, float translationY) {
        get(viewId).translationY = translationY;
    }

    public void setTranslation(int viewId, float translationX, float translationY) {
        Constraint constraint = get(viewId);
        constraint.translationX = translationX;
        constraint.translationY = translationY;
    }

    public void setTranslationZ(int viewId, float translationZ) {
        get(viewId).translationZ = translationZ;
    }

    public void constrainHeight(int viewId, int height) {
        get(viewId).mHeight = height;
    }

    public void constrainWidth(int viewId, int width) {
        get(viewId).mWidth = width;
    }

    public void constrainCircle(int viewId, int id, int radius, float angle) {
        Constraint constraint = get(viewId);
        constraint.circleConstraint = id;
        constraint.circleRadius = radius;
        constraint.circleAngle = angle;
    }

    public void constrainMaxHeight(int viewId, int height) {
        get(viewId).heightMax = height;
    }

    public void constrainMaxWidth(int viewId, int width) {
        get(viewId).widthMax = width;
    }

    public void constrainMinHeight(int viewId, int height) {
        get(viewId).heightMin = height;
    }

    public void constrainMinWidth(int viewId, int width) {
        get(viewId).widthMin = width;
    }

    public void constrainPercentWidth(int viewId, float percent) {
        get(viewId).widthPercent = percent;
    }

    public void constrainPercentHeight(int viewId, float percent) {
        get(viewId).heightPercent = percent;
    }

    public void constrainDefaultHeight(int viewId, int height) {
        get(viewId).heightDefault = height;
    }

    public void constrainDefaultWidth(int viewId, int width) {
        get(viewId).widthDefault = width;
    }

    public void setHorizontalWeight(int viewId, float weight) {
        get(viewId).horizontalWeight = weight;
    }

    public void setVerticalWeight(int viewId, float weight) {
        get(viewId).verticalWeight = weight;
    }

    public void setHorizontalChainStyle(int viewId, int chainStyle) {
        get(viewId).horizontalChainStyle = chainStyle;
    }

    public void setVerticalChainStyle(int viewId, int chainStyle) {
        get(viewId).verticalChainStyle = chainStyle;
    }

    public void addToHorizontalChain(int i, int i2, int i3) {
        int viewId = i;
        int leftId = i2;
        int rightId = i3;
        connect(viewId, 1, leftId, leftId == 0 ? 1 : 2, 0);
        connect(viewId, 2, rightId, rightId == 0 ? 2 : 1, 0);
        if (leftId != 0) {
            connect(leftId, 2, viewId, 1, 0);
        }
        if (rightId != 0) {
            connect(rightId, 1, viewId, 2, 0);
        }
    }

    public void addToHorizontalChainRTL(int i, int i2, int i3) {
        int viewId = i;
        int leftId = i2;
        int rightId = i3;
        connect(viewId, 6, leftId, leftId == 0 ? 6 : 7, 0);
        connect(viewId, 7, rightId, rightId == 0 ? 7 : 6, 0);
        if (leftId != 0) {
            connect(leftId, 7, viewId, 6, 0);
        }
        if (rightId != 0) {
            connect(rightId, 6, viewId, 7, 0);
        }
    }

    public void addToVerticalChain(int i, int i2, int i3) {
        int viewId = i;
        int topId = i2;
        int bottomId = i3;
        connect(viewId, 3, topId, topId == 0 ? 3 : 4, 0);
        connect(viewId, 4, bottomId, bottomId == 0 ? 4 : 3, 0);
        if (topId != 0) {
            connect(topId, 4, viewId, 3, 0);
        }
        if (topId != 0) {
            connect(bottomId, 3, viewId, 4, 0);
        }
    }

    public void removeFromVerticalChain(int i) {
        int viewId = i;
        if (this.mConstraints.containsKey(Integer.valueOf(viewId))) {
            Constraint constraint = this.mConstraints.get(Integer.valueOf(viewId));
            int topId = constraint.topToBottom;
            int bottomId = constraint.bottomToTop;
            if (!(topId == -1 && bottomId == -1)) {
                if (topId != -1 && bottomId != -1) {
                    connect(topId, 4, bottomId, 3, 0);
                    connect(bottomId, 3, topId, 4, 0);
                } else if (!(topId == -1 && bottomId == -1)) {
                    if (constraint.bottomToBottom != -1) {
                        connect(topId, 4, constraint.bottomToBottom, 4, 0);
                    } else if (constraint.topToTop != -1) {
                        connect(bottomId, 3, constraint.topToTop, 3, 0);
                    }
                }
            }
        }
        clear(viewId, 3);
        clear(viewId, 4);
    }

    public void removeFromHorizontalChain(int i) {
        int viewId = i;
        if (this.mConstraints.containsKey(Integer.valueOf(viewId))) {
            Constraint constraint = this.mConstraints.get(Integer.valueOf(viewId));
            int leftId = constraint.leftToRight;
            int rightId = constraint.rightToLeft;
            if (leftId == -1 && rightId == -1) {
                int startId = constraint.startToEnd;
                int endId = constraint.endToStart;
                if (!(startId == -1 && endId == -1)) {
                    if (startId != -1 && endId != -1) {
                        connect(startId, 7, endId, 6, 0);
                        connect(endId, 6, leftId, 7, 0);
                    } else if (!(leftId == -1 && endId == -1)) {
                        if (constraint.rightToRight != -1) {
                            connect(leftId, 7, constraint.rightToRight, 7, 0);
                        } else if (constraint.leftToLeft != -1) {
                            connect(endId, 6, constraint.leftToLeft, 6, 0);
                        }
                    }
                }
                clear(viewId, 6);
                clear(viewId, 7);
                return;
            }
            if (leftId != -1 && rightId != -1) {
                connect(leftId, 2, rightId, 1, 0);
                connect(rightId, 1, leftId, 2, 0);
            } else if (!(leftId == -1 && rightId == -1)) {
                if (constraint.rightToRight != -1) {
                    connect(leftId, 2, constraint.rightToRight, 2, 0);
                } else if (constraint.leftToLeft != -1) {
                    connect(rightId, 1, constraint.leftToLeft, 1, 0);
                }
            }
            clear(viewId, 1);
            clear(viewId, 2);
        }
    }

    public void create(int guidelineID, int orientation) {
        Constraint constraint = get(guidelineID);
        constraint.mIsGuideline = true;
        constraint.orientation = orientation;
    }

    public void createBarrier(int id, int direction, int... referenced) {
        Constraint constraint = get(id);
        constraint.mHelperType = 1;
        constraint.mBarrierDirection = direction;
        constraint.mIsGuideline = false;
        constraint.mReferenceIds = referenced;
    }

    public void setGuidelineBegin(int i, int margin) {
        int guidelineID = i;
        get(guidelineID).guideBegin = margin;
        get(guidelineID).guideEnd = -1;
        get(guidelineID).guidePercent = -1.0f;
    }

    public void setGuidelineEnd(int i, int margin) {
        int guidelineID = i;
        get(guidelineID).guideEnd = margin;
        get(guidelineID).guideBegin = -1;
        get(guidelineID).guidePercent = -1.0f;
    }

    public void setGuidelinePercent(int i, float ratio) {
        int guidelineID = i;
        get(guidelineID).guidePercent = ratio;
        get(guidelineID).guideEnd = -1;
        get(guidelineID).guideBegin = -1;
    }

    public void setBarrierType(int id, int type) {
    }

    private Constraint get(int i) {
        Object obj;
        int id = i;
        if (!this.mConstraints.containsKey(Integer.valueOf(id))) {
            new Constraint((C00241) null);
            Constraint put = this.mConstraints.put(Integer.valueOf(id), obj);
        }
        return this.mConstraints.get(Integer.valueOf(id));
    }

    private String sideToString(int side) {
        switch (side) {
            case 1:
                return "left";
            case 2:
                return "right";
            case 3:
                return "top";
            case 4:
                return "bottom";
            case 5:
                return "baseline";
            case 6:
                return "start";
            case 7:
                return "end";
            default:
                return "undefined";
        }
    }

    public void load(Context context, int resourceId) {
        Context context2 = context;
        XmlPullParser parser = context2.getResources().getXml(resourceId);
        try {
            for (int eventType = parser.getEventType(); eventType != 1; eventType = parser.next()) {
                switch (eventType) {
                    case 0:
                        String document = parser.getName();
                        break;
                    case 2:
                        String tagName = parser.getName();
                        Constraint constraint = fillFromAttributeList(context2, Xml.asAttributeSet(parser));
                        if (tagName.equalsIgnoreCase("Guideline")) {
                            constraint.mIsGuideline = true;
                        }
                        Constraint put = this.mConstraints.put(Integer.valueOf(constraint.mViewId), constraint);
                        break;
                    case 3:
                        break;
                }
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private static int lookupID(TypedArray typedArray, int i, int def) {
        TypedArray a = typedArray;
        int index = i;
        int ret = a.getResourceId(index, def);
        if (ret == -1) {
            ret = a.getInt(index, -1);
        }
        return ret;
    }

    private Constraint fillFromAttributeList(Context context, AttributeSet attrs) {
        Constraint constraint;
        new Constraint((C00241) null);
        Constraint c = constraint;
        TypedArray a = context.obtainStyledAttributes(attrs, C0025R.styleable.ConstraintSet);
        populateConstraint(c, a);
        a.recycle();
        return c;
    }

    private void populateConstraint(Constraint constraint, TypedArray typedArray) {
        StringBuilder sb;
        StringBuilder sb2;
        Constraint c = constraint;
        TypedArray a = typedArray;
        int N = a.getIndexCount();
        for (int i = 0; i < N; i++) {
            int attr = a.getIndex(i);
            switch (mapToConstant.get(attr)) {
                case 1:
                    c.baselineToBaseline = lookupID(a, attr, c.baselineToBaseline);
                    break;
                case 2:
                    c.bottomMargin = a.getDimensionPixelSize(attr, c.bottomMargin);
                    break;
                case 3:
                    c.bottomToBottom = lookupID(a, attr, c.bottomToBottom);
                    break;
                case 4:
                    c.bottomToTop = lookupID(a, attr, c.bottomToTop);
                    break;
                case 5:
                    c.dimensionRatio = a.getString(attr);
                    break;
                case 6:
                    c.editorAbsoluteX = a.getDimensionPixelOffset(attr, c.editorAbsoluteX);
                    break;
                case 7:
                    c.editorAbsoluteY = a.getDimensionPixelOffset(attr, c.editorAbsoluteY);
                    break;
                case 8:
                    c.endMargin = a.getDimensionPixelSize(attr, c.endMargin);
                    break;
                case 9:
                    c.endToEnd = lookupID(a, attr, c.endToEnd);
                    break;
                case 10:
                    c.endToStart = lookupID(a, attr, c.endToStart);
                    break;
                case 11:
                    c.goneBottomMargin = a.getDimensionPixelSize(attr, c.goneBottomMargin);
                    break;
                case 12:
                    c.goneEndMargin = a.getDimensionPixelSize(attr, c.goneEndMargin);
                    break;
                case 13:
                    c.goneLeftMargin = a.getDimensionPixelSize(attr, c.goneLeftMargin);
                    break;
                case 14:
                    c.goneRightMargin = a.getDimensionPixelSize(attr, c.goneRightMargin);
                    break;
                case 15:
                    c.goneStartMargin = a.getDimensionPixelSize(attr, c.goneStartMargin);
                    break;
                case 16:
                    c.goneTopMargin = a.getDimensionPixelSize(attr, c.goneTopMargin);
                    break;
                case 17:
                    c.guideBegin = a.getDimensionPixelOffset(attr, c.guideBegin);
                    break;
                case 18:
                    c.guideEnd = a.getDimensionPixelOffset(attr, c.guideEnd);
                    break;
                case 19:
                    c.guidePercent = a.getFloat(attr, c.guidePercent);
                    break;
                case 20:
                    c.horizontalBias = a.getFloat(attr, c.horizontalBias);
                    break;
                case 21:
                    c.mHeight = a.getLayoutDimension(attr, c.mHeight);
                    break;
                case 22:
                    c.visibility = a.getInt(attr, c.visibility);
                    c.visibility = VISIBILITY_FLAGS[c.visibility];
                    break;
                case 23:
                    c.mWidth = a.getLayoutDimension(attr, c.mWidth);
                    break;
                case 24:
                    c.leftMargin = a.getDimensionPixelSize(attr, c.leftMargin);
                    break;
                case 25:
                    c.leftToLeft = lookupID(a, attr, c.leftToLeft);
                    break;
                case 26:
                    c.leftToRight = lookupID(a, attr, c.leftToRight);
                    break;
                case 27:
                    c.orientation = a.getInt(attr, c.orientation);
                    break;
                case 28:
                    c.rightMargin = a.getDimensionPixelSize(attr, c.rightMargin);
                    break;
                case 29:
                    c.rightToLeft = lookupID(a, attr, c.rightToLeft);
                    break;
                case 30:
                    c.rightToRight = lookupID(a, attr, c.rightToRight);
                    break;
                case 31:
                    c.startMargin = a.getDimensionPixelSize(attr, c.startMargin);
                    break;
                case 32:
                    c.startToEnd = lookupID(a, attr, c.startToEnd);
                    break;
                case 33:
                    c.startToStart = lookupID(a, attr, c.startToStart);
                    break;
                case 34:
                    c.topMargin = a.getDimensionPixelSize(attr, c.topMargin);
                    break;
                case 35:
                    c.topToBottom = lookupID(a, attr, c.topToBottom);
                    break;
                case 36:
                    c.topToTop = lookupID(a, attr, c.topToTop);
                    break;
                case 37:
                    c.verticalBias = a.getFloat(attr, c.verticalBias);
                    break;
                case 38:
                    c.mViewId = a.getResourceId(attr, c.mViewId);
                    break;
                case 39:
                    c.horizontalWeight = a.getFloat(attr, c.horizontalWeight);
                    break;
                case 40:
                    c.verticalWeight = a.getFloat(attr, c.verticalWeight);
                    break;
                case 41:
                    c.horizontalChainStyle = a.getInt(attr, c.horizontalChainStyle);
                    break;
                case 42:
                    c.verticalChainStyle = a.getInt(attr, c.verticalChainStyle);
                    break;
                case 43:
                    c.alpha = a.getFloat(attr, c.alpha);
                    break;
                case 44:
                    c.applyElevation = true;
                    c.elevation = a.getDimension(attr, c.elevation);
                    break;
                case 45:
                    c.rotationX = a.getFloat(attr, c.rotationX);
                    break;
                case 46:
                    c.rotationY = a.getFloat(attr, c.rotationY);
                    break;
                case 47:
                    c.scaleX = a.getFloat(attr, c.scaleX);
                    break;
                case 48:
                    c.scaleY = a.getFloat(attr, c.scaleY);
                    break;
                case 49:
                    c.transformPivotX = a.getFloat(attr, c.transformPivotX);
                    break;
                case 50:
                    c.transformPivotY = a.getFloat(attr, c.transformPivotY);
                    break;
                case 51:
                    c.translationX = a.getDimension(attr, c.translationX);
                    break;
                case 52:
                    c.translationY = a.getDimension(attr, c.translationY);
                    break;
                case 53:
                    c.translationZ = a.getDimension(attr, c.translationZ);
                    break;
                case 60:
                    c.rotation = a.getFloat(attr, c.rotation);
                    break;
                case 61:
                    c.circleConstraint = lookupID(a, attr, c.circleConstraint);
                    break;
                case 62:
                    c.circleRadius = a.getDimensionPixelSize(attr, c.circleRadius);
                    break;
                case 63:
                    c.circleAngle = a.getFloat(attr, c.circleAngle);
                    break;
                case 69:
                    c.widthPercent = a.getFloat(attr, 1.0f);
                    break;
                case 70:
                    c.heightPercent = a.getFloat(attr, 1.0f);
                    break;
                case 71:
                    int e = Log.e(TAG, "CURRENTLY UNSUPPORTED");
                    break;
                case 72:
                    c.mBarrierDirection = a.getInt(attr, c.mBarrierDirection);
                    break;
                case 73:
                    c.mReferenceIdString = a.getString(attr);
                    break;
                case 74:
                    c.mBarrierAllowsGoneWidgets = a.getBoolean(attr, c.mBarrierAllowsGoneWidgets);
                    break;
                case 75:
                    new StringBuilder();
                    int w = Log.w(TAG, sb.append("unused attribute 0x").append(Integer.toHexString(attr)).append("   ").append(mapToConstant.get(attr)).toString());
                    break;
                default:
                    new StringBuilder();
                    int w2 = Log.w(TAG, sb2.append("Unknown attribute 0x").append(Integer.toHexString(attr)).append("   ").append(mapToConstant.get(attr)).toString());
                    break;
            }
        }
    }

    private int[] convertReferenceString(View view, String referenceIdString) {
        Object value;
        View view2 = view;
        String[] split = referenceIdString.split(",");
        Context context = view2.getContext();
        int[] tags = new int[split.length];
        int count = 0;
        for (int i = 0; i < split.length; i++) {
            String idString = split[i].trim();
            int tag = 0;
            try {
                tag = C0025R.C0026id.class.getField(idString).getInt((Object) null);
            } catch (Exception e) {
                Exception exc = e;
            }
            if (tag == 0) {
                tag = context.getResources().getIdentifier(idString, "id", context.getPackageName());
            }
            if (tag == 0 && view2.isInEditMode() && (view2.getParent() instanceof ConstraintLayout) && (value = ((ConstraintLayout) view2.getParent()).getDesignInformation(0, idString)) != null && (value instanceof Integer)) {
                tag = ((Integer) value).intValue();
            }
            int i2 = count;
            count++;
            tags[i2] = tag;
        }
        if (count != split.length) {
            tags = Arrays.copyOf(tags, count);
        }
        return tags;
    }
}

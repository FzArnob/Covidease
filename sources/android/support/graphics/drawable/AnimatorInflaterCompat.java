package android.support.graphics.drawable;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Build;
import android.support.annotation.AnimatorRes;
import android.support.annotation.RestrictTo;
import android.support.p000v4.content.res.TypedArrayUtils;
import android.support.p000v4.graphics.PathParser;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import android.view.InflateException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.shaded.apache.http.HttpStatus;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class AnimatorInflaterCompat {
    private static final boolean DBG_ANIMATOR_INFLATER = false;
    private static final int MAX_NUM_POINTS = 100;
    private static final String TAG = "AnimatorInflater";
    private static final int TOGETHER = 0;
    private static final int VALUE_TYPE_COLOR = 3;
    private static final int VALUE_TYPE_FLOAT = 0;
    private static final int VALUE_TYPE_INT = 1;
    private static final int VALUE_TYPE_PATH = 2;
    private static final int VALUE_TYPE_UNDEFINED = 4;

    public static Animator loadAnimator(Context context, @AnimatorRes int i) throws Resources.NotFoundException {
        Animator objectAnimator;
        Context context2 = context;
        int id = i;
        if (Build.VERSION.SDK_INT >= 24) {
            objectAnimator = AnimatorInflater.loadAnimator(context2, id);
        } else {
            objectAnimator = loadAnimator(context2, context2.getResources(), context2.getTheme(), id);
        }
        return objectAnimator;
    }

    public static Animator loadAnimator(Context context, Resources resources, Resources.Theme theme, @AnimatorRes int id) throws Resources.NotFoundException {
        return loadAnimator(context, resources, theme, id, 1.0f);
    }

    public static Animator loadAnimator(Context context, Resources resources, Resources.Theme theme, @AnimatorRes int i, float f) throws Resources.NotFoundException {
        Resources.NotFoundException notFoundException;
        StringBuilder sb;
        Resources.NotFoundException notFoundException2;
        StringBuilder sb2;
        Context context2 = context;
        Resources resources2 = resources;
        Resources.Theme theme2 = theme;
        int id = i;
        float pathErrorScale = f;
        XmlResourceParser parser = null;
        try {
            parser = resources2.getAnimation(id);
            Animator createAnimatorFromXml = createAnimatorFromXml(context2, resources2, theme2, parser, pathErrorScale);
            if (parser != null) {
                parser.close();
            }
            return createAnimatorFromXml;
        } catch (XmlPullParserException e) {
            XmlPullParserException ex = e;
            new StringBuilder();
            new Resources.NotFoundException(sb2.append("Can't load animation resource ID #0x").append(Integer.toHexString(id)).toString());
            Resources.NotFoundException rnf = notFoundException2;
            Throwable initCause = rnf.initCause(ex);
            throw rnf;
        } catch (IOException e2) {
            IOException ex2 = e2;
            new StringBuilder();
            new Resources.NotFoundException(sb.append("Can't load animation resource ID #0x").append(Integer.toHexString(id)).toString());
            Resources.NotFoundException rnf2 = notFoundException;
            Throwable initCause2 = rnf2.initCause(ex2);
            throw rnf2;
        } catch (Throwable th) {
            Throwable th2 = th;
            if (parser != null) {
                parser.close();
            }
            throw th2;
        }
    }

    private static class PathDataEvaluator implements TypeEvaluator<PathParser.PathDataNode[]> {
        private PathParser.PathDataNode[] mNodeArray;

        PathDataEvaluator() {
        }

        PathDataEvaluator(PathParser.PathDataNode[] nodeArray) {
            this.mNodeArray = nodeArray;
        }

        public PathParser.PathDataNode[] evaluate(float f, PathParser.PathDataNode[] pathDataNodeArr, PathParser.PathDataNode[] pathDataNodeArr2) {
            Throwable th;
            float fraction = f;
            PathParser.PathDataNode[] startPathData = pathDataNodeArr;
            PathParser.PathDataNode[] endPathData = pathDataNodeArr2;
            if (!PathParser.canMorph(startPathData, endPathData)) {
                Throwable th2 = th;
                new IllegalArgumentException("Can't interpolate between two incompatible pathData");
                throw th2;
            }
            if (this.mNodeArray == null || !PathParser.canMorph(this.mNodeArray, startPathData)) {
                this.mNodeArray = PathParser.deepCopyNodes(startPathData);
            }
            for (int i = 0; i < startPathData.length; i++) {
                this.mNodeArray[i].interpolatePathDataNode(startPathData[i], endPathData[i], fraction);
            }
            return this.mNodeArray;
        }
    }

    private static PropertyValuesHolder getPVH(TypedArray typedArray, int i, int i2, int i3, String str) {
        int valueTo;
        int valueFrom;
        int valueTo2;
        float valueTo3;
        float valueFrom2;
        float valueTo4;
        TypeEvaluator evaluator;
        TypeEvaluator typeEvaluator;
        Throwable th;
        StringBuilder sb;
        TypedArray styledAttributes = typedArray;
        int valueType = i;
        int valueFromId = i2;
        int valueToId = i3;
        String propertyName = str;
        TypedValue tvFrom = styledAttributes.peekValue(valueFromId);
        boolean hasFrom = tvFrom != null;
        int fromType = hasFrom ? tvFrom.type : 0;
        TypedValue tvTo = styledAttributes.peekValue(valueToId);
        boolean hasTo = tvTo != null;
        int toType = hasTo ? tvTo.type : 0;
        if (valueType == 4) {
            if ((!hasFrom || !isColorType(fromType)) && (!hasTo || !isColorType(toType))) {
                valueType = 0;
            } else {
                valueType = 3;
            }
        }
        boolean getFloats = valueType == 0;
        PropertyValuesHolder returnValue = null;
        if (valueType == 2) {
            String fromString = styledAttributes.getString(valueFromId);
            String toString = styledAttributes.getString(valueToId);
            PathParser.PathDataNode[] nodesFrom = PathParser.createNodesFromPathData(fromString);
            PathParser.PathDataNode[] nodesTo = PathParser.createNodesFromPathData(toString);
            if (!(nodesFrom == null && nodesTo == null)) {
                if (nodesFrom != null) {
                    new PathDataEvaluator();
                    TypeEvaluator evaluator2 = typeEvaluator;
                    if (nodesTo == null) {
                        returnValue = PropertyValuesHolder.ofObject(propertyName, evaluator2, new Object[]{nodesFrom});
                    } else if (!PathParser.canMorph(nodesFrom, nodesTo)) {
                        Throwable th2 = th;
                        new StringBuilder();
                        new InflateException(sb.append(" Can't morph from ").append(fromString).append(" to ").append(toString).toString());
                        throw th2;
                    } else {
                        Object[] objArr = new Object[2];
                        objArr[0] = nodesFrom;
                        Object[] objArr2 = objArr;
                        objArr2[1] = nodesTo;
                        returnValue = PropertyValuesHolder.ofObject(propertyName, evaluator2, objArr2);
                    }
                } else if (nodesTo != null) {
                    new PathDataEvaluator();
                    returnValue = PropertyValuesHolder.ofObject(propertyName, evaluator, new Object[]{nodesTo});
                }
            }
        } else {
            TypeEvaluator evaluator3 = null;
            if (valueType == 3) {
                evaluator3 = ArgbEvaluator.getInstance();
            }
            if (getFloats) {
                if (hasFrom) {
                    if (fromType == 5) {
                        valueFrom2 = styledAttributes.getDimension(valueFromId, 0.0f);
                    } else {
                        valueFrom2 = styledAttributes.getFloat(valueFromId, 0.0f);
                    }
                    if (hasTo) {
                        if (toType == 5) {
                            valueTo4 = styledAttributes.getDimension(valueToId, 0.0f);
                        } else {
                            valueTo4 = styledAttributes.getFloat(valueToId, 0.0f);
                        }
                        float[] fArr = new float[2];
                        fArr[0] = valueFrom2;
                        float[] fArr2 = fArr;
                        fArr2[1] = valueTo4;
                        returnValue = PropertyValuesHolder.ofFloat(propertyName, fArr2);
                    } else {
                        returnValue = PropertyValuesHolder.ofFloat(propertyName, new float[]{valueFrom2});
                    }
                } else {
                    if (toType == 5) {
                        valueTo3 = styledAttributes.getDimension(valueToId, 0.0f);
                    } else {
                        valueTo3 = styledAttributes.getFloat(valueToId, 0.0f);
                    }
                    returnValue = PropertyValuesHolder.ofFloat(propertyName, new float[]{valueTo3});
                }
            } else if (hasFrom) {
                if (fromType == 5) {
                    valueFrom = (int) styledAttributes.getDimension(valueFromId, 0.0f);
                } else if (isColorType(fromType)) {
                    valueFrom = styledAttributes.getColor(valueFromId, 0);
                } else {
                    valueFrom = styledAttributes.getInt(valueFromId, 0);
                }
                if (hasTo) {
                    if (toType == 5) {
                        valueTo2 = (int) styledAttributes.getDimension(valueToId, 0.0f);
                    } else if (isColorType(toType)) {
                        valueTo2 = styledAttributes.getColor(valueToId, 0);
                    } else {
                        valueTo2 = styledAttributes.getInt(valueToId, 0);
                    }
                    int[] iArr = new int[2];
                    iArr[0] = valueFrom;
                    int[] iArr2 = iArr;
                    iArr2[1] = valueTo2;
                    returnValue = PropertyValuesHolder.ofInt(propertyName, iArr2);
                } else {
                    returnValue = PropertyValuesHolder.ofInt(propertyName, new int[]{valueFrom});
                }
            } else if (hasTo) {
                if (toType == 5) {
                    valueTo = (int) styledAttributes.getDimension(valueToId, 0.0f);
                } else if (isColorType(toType)) {
                    valueTo = styledAttributes.getColor(valueToId, 0);
                } else {
                    valueTo = styledAttributes.getInt(valueToId, 0);
                }
                returnValue = PropertyValuesHolder.ofInt(propertyName, new int[]{valueTo});
            }
            if (!(returnValue == null || evaluator3 == null)) {
                returnValue.setEvaluator(evaluator3);
            }
        }
        return returnValue;
    }

    private static void parseAnimatorFromTypeArray(ValueAnimator valueAnimator, TypedArray typedArray, TypedArray typedArray2, float f, XmlPullParser xmlPullParser) {
        ValueAnimator anim = valueAnimator;
        TypedArray arrayAnimator = typedArray;
        TypedArray arrayObjectAnimator = typedArray2;
        float pixelSize = f;
        XmlPullParser parser = xmlPullParser;
        long duration = (long) TypedArrayUtils.getNamedInt(arrayAnimator, parser, "duration", 1, HttpStatus.SC_MULTIPLE_CHOICES);
        long startDelay = (long) TypedArrayUtils.getNamedInt(arrayAnimator, parser, "startOffset", 2, 0);
        int valueType = TypedArrayUtils.getNamedInt(arrayAnimator, parser, "valueType", 7, 4);
        if (TypedArrayUtils.hasAttribute(parser, "valueFrom") && TypedArrayUtils.hasAttribute(parser, "valueTo")) {
            if (valueType == 4) {
                valueType = inferValueTypeFromValues(arrayAnimator, 5, 6);
            }
            PropertyValuesHolder pvh = getPVH(arrayAnimator, valueType, 5, 6, "");
            if (pvh != null) {
                anim.setValues(new PropertyValuesHolder[]{pvh});
            }
        }
        ValueAnimator duration2 = anim.setDuration(duration);
        anim.setStartDelay(startDelay);
        anim.setRepeatCount(TypedArrayUtils.getNamedInt(arrayAnimator, parser, "repeatCount", 3, 0));
        anim.setRepeatMode(TypedArrayUtils.getNamedInt(arrayAnimator, parser, "repeatMode", 4, 1));
        if (arrayObjectAnimator != null) {
            setupObjectAnimator(anim, arrayObjectAnimator, valueType, pixelSize, parser);
        }
    }

    private static void setupObjectAnimator(ValueAnimator anim, TypedArray typedArray, int i, float f, XmlPullParser xmlPullParser) {
        Throwable th;
        StringBuilder sb;
        TypedArray arrayObjectAnimator = typedArray;
        int valueType = i;
        float pixelSize = f;
        XmlPullParser parser = xmlPullParser;
        ObjectAnimator oa = (ObjectAnimator) anim;
        String pathData = TypedArrayUtils.getNamedString(arrayObjectAnimator, parser, "pathData", 1);
        if (pathData != null) {
            String propertyXName = TypedArrayUtils.getNamedString(arrayObjectAnimator, parser, "propertyXName", 2);
            String propertyYName = TypedArrayUtils.getNamedString(arrayObjectAnimator, parser, "propertyYName", 3);
            if (valueType == 2 || valueType == 4) {
            }
            if (propertyXName == null && propertyYName == null) {
                Throwable th2 = th;
                new StringBuilder();
                new InflateException(sb.append(arrayObjectAnimator.getPositionDescription()).append(" propertyXName or propertyYName is needed for PathData").toString());
                throw th2;
            }
            setupPathMotion(PathParser.createPathFromPathData(pathData), oa, 0.5f * pixelSize, propertyXName, propertyYName);
            return;
        }
        oa.setPropertyName(TypedArrayUtils.getNamedString(arrayObjectAnimator, parser, "propertyName", 0));
    }

    private static void setupPathMotion(Path path, ObjectAnimator objectAnimator, float f, String str, String str2) {
        PathMeasure pathMeasure;
        ArrayList arrayList;
        PathMeasure pathMeasure2;
        Path path2 = path;
        ObjectAnimator oa = objectAnimator;
        float precision = f;
        String propertyXName = str;
        String propertyYName = str2;
        new PathMeasure(path2, false);
        PathMeasure measureForTotalLength = pathMeasure;
        float totalLength = 0.0f;
        new ArrayList();
        ArrayList arrayList2 = arrayList;
        boolean add = arrayList2.add(Float.valueOf(0.0f));
        do {
            totalLength += measureForTotalLength.getLength();
            boolean add2 = arrayList2.add(Float.valueOf(totalLength));
        } while (measureForTotalLength.nextContour());
        new PathMeasure(path2, false);
        PathMeasure pathMeasure3 = pathMeasure2;
        int numPoints = Math.min(100, ((int) (totalLength / precision)) + 1);
        float[] mX = new float[numPoints];
        float[] mY = new float[numPoints];
        float[] position = new float[2];
        int contourIndex = 0;
        float step = totalLength / ((float) (numPoints - 1));
        float currentDistance = 0.0f;
        for (int i = 0; i < numPoints; i++) {
            boolean posTan = pathMeasure3.getPosTan(currentDistance - ((Float) arrayList2.get(contourIndex)).floatValue(), position, (float[]) null);
            mX[i] = position[0];
            mY[i] = position[1];
            currentDistance += step;
            if (contourIndex + 1 < arrayList2.size() && currentDistance > ((Float) arrayList2.get(contourIndex + 1)).floatValue()) {
                contourIndex++;
                boolean nextContour = pathMeasure3.nextContour();
            }
        }
        PropertyValuesHolder x = null;
        PropertyValuesHolder y = null;
        if (propertyXName != null) {
            x = PropertyValuesHolder.ofFloat(propertyXName, mX);
        }
        if (propertyYName != null) {
            y = PropertyValuesHolder.ofFloat(propertyYName, mY);
        }
        if (x == null) {
            oa.setValues(new PropertyValuesHolder[]{y});
        } else if (y == null) {
            oa.setValues(new PropertyValuesHolder[]{x});
        } else {
            PropertyValuesHolder[] propertyValuesHolderArr = new PropertyValuesHolder[2];
            propertyValuesHolderArr[0] = x;
            PropertyValuesHolder[] propertyValuesHolderArr2 = propertyValuesHolderArr;
            propertyValuesHolderArr2[1] = y;
            oa.setValues(propertyValuesHolderArr2);
        }
    }

    private static Animator createAnimatorFromXml(Context context, Resources res, Resources.Theme theme, XmlPullParser xmlPullParser, float pixelSize) throws XmlPullParserException, IOException {
        XmlPullParser parser = xmlPullParser;
        return createAnimatorFromXml(context, res, theme, parser, Xml.asAttributeSet(parser), (AnimatorSet) null, 0, pixelSize);
    }

    private static Animator createAnimatorFromXml(Context context, Resources resources, Resources.Theme theme, XmlPullParser xmlPullParser, AttributeSet attributeSet, AnimatorSet animatorSet, int i, float f) throws XmlPullParserException, IOException {
        Throwable th;
        StringBuilder sb;
        Animator animator;
        ArrayList arrayList;
        Context context2 = context;
        Resources res = resources;
        Resources.Theme theme2 = theme;
        XmlPullParser parser = xmlPullParser;
        AttributeSet attrs = attributeSet;
        AnimatorSet parent = animatorSet;
        int sequenceOrdering = i;
        float pixelSize = f;
        Animator anim = null;
        ArrayList arrayList2 = null;
        int depth = parser.getDepth();
        while (true) {
            int next = parser.next();
            int type = next;
            if ((next != 3 || parser.getDepth() > depth) && type != 1) {
                if (type == 2) {
                    String name = parser.getName();
                    boolean gotValues = false;
                    if (name.equals("objectAnimator")) {
                        anim = loadObjectAnimator(context2, res, theme2, attrs, pixelSize, parser);
                    } else if (name.equals("animator")) {
                        anim = loadAnimator(context2, res, theme2, attrs, (ValueAnimator) null, pixelSize, parser);
                    } else if (name.equals("set")) {
                        new AnimatorSet();
                        anim = animator;
                        TypedArray a = TypedArrayUtils.obtainAttributes(res, theme2, attrs, AndroidResources.STYLEABLE_ANIMATOR_SET);
                        Animator createAnimatorFromXml = createAnimatorFromXml(context2, res, theme2, parser, attrs, (AnimatorSet) anim, TypedArrayUtils.getNamedInt(a, parser, "ordering", 0, 0), pixelSize);
                        a.recycle();
                    } else if (name.equals("propertyValuesHolder")) {
                        PropertyValuesHolder[] values = loadValues(context2, res, theme2, parser, Xml.asAttributeSet(parser));
                        if (!(values == null || anim == null || !(anim instanceof ValueAnimator))) {
                            ((ValueAnimator) anim).setValues(values);
                        }
                        gotValues = true;
                    } else {
                        Throwable th2 = th;
                        new StringBuilder();
                        new RuntimeException(sb.append("Unknown animator name: ").append(parser.getName()).toString());
                        throw th2;
                    }
                    if (parent != null && !gotValues) {
                        if (arrayList2 == null) {
                            new ArrayList();
                            arrayList2 = arrayList;
                        }
                        boolean add = arrayList2.add(anim);
                    }
                }
            }
        }
        if (!(parent == null || arrayList2 == null)) {
            Animator[] animsArray = new Animator[arrayList2.size()];
            int index = 0;
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                int i2 = index;
                index++;
                animsArray[i2] = (Animator) it.next();
            }
            if (sequenceOrdering == 0) {
                parent.playTogether(animsArray);
            } else {
                parent.playSequentially(animsArray);
            }
        }
        return anim;
    }

    private static PropertyValuesHolder[] loadValues(Context context, Resources resources, Resources.Theme theme, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        ArrayList arrayList;
        Context context2 = context;
        Resources res = resources;
        Resources.Theme theme2 = theme;
        XmlPullParser parser = xmlPullParser;
        AttributeSet attrs = attributeSet;
        ArrayList arrayList2 = null;
        while (true) {
            int eventType = parser.getEventType();
            int type = eventType;
            if (eventType == 3 || type == 1) {
                PropertyValuesHolder[] valuesArray = null;
            } else if (type != 2) {
                int next = parser.next();
            } else {
                if (parser.getName().equals("propertyValuesHolder")) {
                    TypedArray a = TypedArrayUtils.obtainAttributes(res, theme2, attrs, AndroidResources.STYLEABLE_PROPERTY_VALUES_HOLDER);
                    String propertyName = TypedArrayUtils.getNamedString(a, parser, "propertyName", 3);
                    int valueType = TypedArrayUtils.getNamedInt(a, parser, "valueType", 2, 4);
                    PropertyValuesHolder pvh = loadPvh(context2, res, theme2, parser, propertyName, valueType);
                    if (pvh == null) {
                        pvh = getPVH(a, valueType, 0, 1, propertyName);
                    }
                    if (pvh != null) {
                        if (arrayList2 == null) {
                            new ArrayList();
                            arrayList2 = arrayList;
                        }
                        boolean add = arrayList2.add(pvh);
                    }
                    a.recycle();
                }
                int next2 = parser.next();
            }
        }
        PropertyValuesHolder[] valuesArray2 = null;
        if (arrayList2 != null) {
            int count = arrayList2.size();
            valuesArray2 = new PropertyValuesHolder[count];
            for (int i = 0; i < count; i++) {
                valuesArray2[i] = (PropertyValuesHolder) arrayList2.get(i);
            }
        }
        return valuesArray2;
    }

    private static int inferValueTypeOfKeyframe(Resources res, Resources.Theme theme, AttributeSet attrs, XmlPullParser parser) {
        int valueType;
        TypedArray a = TypedArrayUtils.obtainAttributes(res, theme, attrs, AndroidResources.STYLEABLE_KEYFRAME);
        TypedValue keyframeValue = TypedArrayUtils.peekNamedValue(a, parser, "value", 0);
        if (!(keyframeValue != null) || !isColorType(keyframeValue.type)) {
            valueType = 0;
        } else {
            valueType = 3;
        }
        a.recycle();
        return valueType;
    }

    private static int inferValueTypeFromValues(TypedArray typedArray, int valueFromId, int i) {
        int valueType;
        TypedArray styledAttributes = typedArray;
        int valueToId = i;
        TypedValue tvFrom = styledAttributes.peekValue(valueFromId);
        boolean hasFrom = tvFrom != null;
        int fromType = hasFrom ? tvFrom.type : 0;
        TypedValue tvTo = styledAttributes.peekValue(valueToId);
        boolean hasTo = tvTo != null;
        int toType = hasTo ? tvTo.type : 0;
        if ((!hasFrom || !isColorType(fromType)) && (!hasTo || !isColorType(toType))) {
            valueType = 0;
        } else {
            valueType = 3;
        }
        return valueType;
    }

    private static void dumpKeyframes(Object[] objArr, String str) {
        StringBuilder sb;
        Object[] keyframes = objArr;
        String header = str;
        if (keyframes != null && keyframes.length != 0) {
            int d = Log.d(TAG, header);
            int count = keyframes.length;
            for (int i = 0; i < count; i++) {
                Keyframe keyframe = (Keyframe) keyframes[i];
                new StringBuilder();
                int d2 = Log.d(TAG, sb.append("Keyframe ").append(i).append(": fraction ").append(keyframe.getFraction() < 0.0f ? "null" : Float.valueOf(keyframe.getFraction())).append(", ").append(", value : ").append(keyframe.hasValue() ? keyframe.getValue() : "null").toString());
            }
        }
    }

    private static PropertyValuesHolder loadPvh(Context context, Resources resources, Resources.Theme theme, XmlPullParser xmlPullParser, String str, int i) throws XmlPullParserException, IOException {
        ArrayList arrayList;
        Context context2 = context;
        Resources res = resources;
        Resources.Theme theme2 = theme;
        XmlPullParser parser = xmlPullParser;
        String propertyName = str;
        int valueType = i;
        PropertyValuesHolder value = null;
        ArrayList arrayList2 = null;
        while (true) {
            int next = parser.next();
            int type = next;
            if (next != 3 && type != 1) {
                if (parser.getName().equals("keyframe")) {
                    if (valueType == 4) {
                        valueType = inferValueTypeOfKeyframe(res, theme2, Xml.asAttributeSet(parser), parser);
                    }
                    Keyframe keyframe = loadKeyframe(context2, res, theme2, Xml.asAttributeSet(parser), valueType, parser);
                    if (keyframe != null) {
                        if (arrayList2 == null) {
                            new ArrayList();
                            arrayList2 = arrayList;
                        }
                        boolean add = arrayList2.add(keyframe);
                    }
                    int next2 = parser.next();
                }
            }
        }
        if (arrayList2 != null) {
            int size = arrayList2.size();
            int count = size;
            if (size > 0) {
                Keyframe firstKeyframe = (Keyframe) arrayList2.get(0);
                Keyframe lastKeyframe = (Keyframe) arrayList2.get(count - 1);
                float endFraction = lastKeyframe.getFraction();
                if (endFraction < 1.0f) {
                    if (endFraction < 0.0f) {
                        lastKeyframe.setFraction(1.0f);
                    } else {
                        arrayList2.add(arrayList2.size(), createNewKeyframe(lastKeyframe, 1.0f));
                        count++;
                    }
                }
                float startFraction = firstKeyframe.getFraction();
                if (startFraction != 0.0f) {
                    if (startFraction < 0.0f) {
                        firstKeyframe.setFraction(0.0f);
                    } else {
                        arrayList2.add(0, createNewKeyframe(firstKeyframe, 0.0f));
                        count++;
                    }
                }
                Keyframe[] keyframeArray = new Keyframe[count];
                Object[] array = arrayList2.toArray(keyframeArray);
                for (int i2 = 0; i2 < count; i2++) {
                    Keyframe keyframe2 = keyframeArray[i2];
                    if (keyframe2.getFraction() < 0.0f) {
                        if (i2 == 0) {
                            keyframe2.setFraction(0.0f);
                        } else if (i2 == count - 1) {
                            keyframe2.setFraction(1.0f);
                        } else {
                            int startIndex = i2;
                            int endIndex = i2;
                            int j = startIndex + 1;
                            while (j < count - 1 && keyframeArray[j].getFraction() < 0.0f) {
                                endIndex = j;
                                j++;
                            }
                            distributeKeyframes(keyframeArray, keyframeArray[endIndex + 1].getFraction() - keyframeArray[startIndex - 1].getFraction(), startIndex, endIndex);
                        }
                    }
                }
                value = PropertyValuesHolder.ofKeyframe(propertyName, keyframeArray);
                if (valueType == 3) {
                    value.setEvaluator(ArgbEvaluator.getInstance());
                }
            }
        }
        return value;
    }

    private static Keyframe createNewKeyframe(Keyframe keyframe, float f) {
        Keyframe sampleKeyframe;
        Keyframe sampleKeyframe2 = keyframe;
        float fraction = f;
        if (sampleKeyframe2.getType() == Float.TYPE) {
            sampleKeyframe = Keyframe.ofFloat(fraction);
        } else if (sampleKeyframe2.getType() == Integer.TYPE) {
            sampleKeyframe = Keyframe.ofInt(fraction);
        } else {
            sampleKeyframe = Keyframe.ofObject(fraction);
        }
        return sampleKeyframe;
    }

    private static void distributeKeyframes(Keyframe[] keyframeArr, float gap, int i, int i2) {
        Keyframe[] keyframes = keyframeArr;
        int startIndex = i;
        int endIndex = i2;
        float increment = gap / ((float) ((endIndex - startIndex) + 2));
        for (int i3 = startIndex; i3 <= endIndex; i3++) {
            keyframes[i3].setFraction(keyframes[i3 - 1].getFraction() + increment);
        }
    }

    private static Keyframe loadKeyframe(Context context, Resources res, Resources.Theme theme, AttributeSet attrs, int i, XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Keyframe ofInt;
        Context context2 = context;
        int valueType = i;
        XmlPullParser parser = xmlPullParser;
        TypedArray a = TypedArrayUtils.obtainAttributes(res, theme, attrs, AndroidResources.STYLEABLE_KEYFRAME);
        Keyframe keyframe = null;
        float fraction = TypedArrayUtils.getNamedFloat(a, parser, "fraction", 3, -1.0f);
        TypedValue keyframeValue = TypedArrayUtils.peekNamedValue(a, parser, "value", 0);
        boolean hasValue = keyframeValue != null;
        if (valueType == 4) {
            if (!hasValue || !isColorType(keyframeValue.type)) {
                valueType = 0;
            } else {
                valueType = 3;
            }
        }
        if (hasValue) {
            switch (valueType) {
                case 0:
                    keyframe = Keyframe.ofFloat(fraction, TypedArrayUtils.getNamedFloat(a, parser, "value", 0, 0.0f));
                    break;
                case 1:
                case 3:
                    keyframe = Keyframe.ofInt(fraction, TypedArrayUtils.getNamedInt(a, parser, "value", 0, 0));
                    break;
            }
        } else {
            if (valueType == 0) {
                ofInt = Keyframe.ofFloat(fraction);
            } else {
                ofInt = Keyframe.ofInt(fraction);
            }
            keyframe = ofInt;
        }
        int resID = TypedArrayUtils.getNamedResourceId(a, parser, "interpolator", 1, 0);
        if (resID > 0) {
            keyframe.setInterpolator(AnimationUtilsCompat.loadInterpolator(context2, resID));
        }
        a.recycle();
        return keyframe;
    }

    private static ObjectAnimator loadObjectAnimator(Context context, Resources res, Resources.Theme theme, AttributeSet attrs, float pathErrorScale, XmlPullParser parser) throws Resources.NotFoundException {
        ObjectAnimator objectAnimator;
        new ObjectAnimator();
        ObjectAnimator anim = objectAnimator;
        ValueAnimator loadAnimator = loadAnimator(context, res, theme, attrs, anim, pathErrorScale, parser);
        return anim;
    }

    private static ValueAnimator loadAnimator(Context context, Resources resources, Resources.Theme theme, AttributeSet attributeSet, ValueAnimator valueAnimator, float f, XmlPullParser xmlPullParser) throws Resources.NotFoundException {
        ValueAnimator valueAnimator2;
        Context context2 = context;
        Resources res = resources;
        Resources.Theme theme2 = theme;
        AttributeSet attrs = attributeSet;
        ValueAnimator anim = valueAnimator;
        float pathErrorScale = f;
        XmlPullParser parser = xmlPullParser;
        TypedArray arrayAnimator = TypedArrayUtils.obtainAttributes(res, theme2, attrs, AndroidResources.STYLEABLE_ANIMATOR);
        TypedArray arrayObjectAnimator = TypedArrayUtils.obtainAttributes(res, theme2, attrs, AndroidResources.STYLEABLE_PROPERTY_ANIMATOR);
        if (anim == null) {
            new ValueAnimator();
            anim = valueAnimator2;
        }
        parseAnimatorFromTypeArray(anim, arrayAnimator, arrayObjectAnimator, pathErrorScale, parser);
        int resID = TypedArrayUtils.getNamedResourceId(arrayAnimator, parser, "interpolator", 0, 0);
        if (resID > 0) {
            anim.setInterpolator(AnimationUtilsCompat.loadInterpolator(context2, resID));
        }
        arrayAnimator.recycle();
        if (arrayObjectAnimator != null) {
            arrayObjectAnimator.recycle();
        }
        return anim;
    }

    private static boolean isColorType(int i) {
        int type = i;
        return type >= 28 && type <= 31;
    }

    private AnimatorInflaterCompat() {
    }
}

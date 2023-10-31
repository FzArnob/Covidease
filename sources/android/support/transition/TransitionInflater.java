package android.support.transition;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.support.annotation.NonNull;
import android.support.p000v4.content.res.TypedArrayUtils;
import android.support.p000v4.util.C1642ArrayMap;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.InflateException;
import android.view.ViewGroup;
import java.io.IOException;
import java.lang.reflect.Constructor;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class TransitionInflater {
    private static final C1642ArrayMap<String, Constructor> CONSTRUCTORS;
    private static final Class<?>[] CONSTRUCTOR_SIGNATURE;
    private final Context mContext;

    static {
        C1642ArrayMap<String, Constructor> arrayMap;
        Class<?>[] clsArr = new Class[2];
        clsArr[0] = Context.class;
        Class<?>[] clsArr2 = clsArr;
        clsArr2[1] = AttributeSet.class;
        CONSTRUCTOR_SIGNATURE = clsArr2;
        new C1642ArrayMap<>();
        CONSTRUCTORS = arrayMap;
    }

    private TransitionInflater(@NonNull Context context) {
        this.mContext = context;
    }

    public static TransitionInflater from(Context context) {
        TransitionInflater transitionInflater;
        new TransitionInflater(context);
        return transitionInflater;
    }

    public Transition inflateTransition(int resource) {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        XmlResourceParser parser = this.mContext.getResources().getXml(resource);
        try {
            Transition createTransitionFromXml = createTransitionFromXml(parser, Xml.asAttributeSet(parser), (Transition) null);
            parser.close();
            return createTransitionFromXml;
        } catch (XmlPullParserException e) {
            XmlPullParserException e2 = e;
            Throwable th3 = th2;
            new InflateException(e2.getMessage(), e2);
            throw th3;
        } catch (IOException e3) {
            IOException e4 = e3;
            Throwable th4 = th;
            new StringBuilder();
            new InflateException(sb.append(parser.getPositionDescription()).append(": ").append(e4.getMessage()).toString(), e4);
            throw th4;
        } catch (Throwable th5) {
            Throwable th6 = th5;
            parser.close();
            throw th6;
        }
    }

    public TransitionManager inflateTransitionManager(int resource, ViewGroup viewGroup) {
        InflateException inflateException;
        StringBuilder sb;
        InflateException inflateException2;
        ViewGroup sceneRoot = viewGroup;
        XmlResourceParser parser = this.mContext.getResources().getXml(resource);
        try {
            TransitionManager createTransitionManagerFromXml = createTransitionManagerFromXml(parser, Xml.asAttributeSet(parser), sceneRoot);
            parser.close();
            return createTransitionManagerFromXml;
        } catch (XmlPullParserException e) {
            XmlPullParserException e2 = e;
            new InflateException(e2.getMessage());
            InflateException ex = inflateException2;
            Throwable initCause = ex.initCause(e2);
            throw ex;
        } catch (IOException e3) {
            IOException e4 = e3;
            new StringBuilder();
            new InflateException(sb.append(parser.getPositionDescription()).append(": ").append(e4.getMessage()).toString());
            InflateException ex2 = inflateException;
            Throwable initCause2 = ex2.initCause(e4);
            throw ex2;
        } catch (Throwable th) {
            Throwable th2 = th;
            parser.close();
            throw th2;
        }
    }

    private Transition createTransitionFromXml(XmlPullParser xmlPullParser, AttributeSet attributeSet, Transition transition) throws XmlPullParserException, IOException {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        PathMotion pathMotion;
        Throwable th3;
        Throwable th4;
        PathMotion pathMotion2;
        Transition transition2;
        Transition transition3;
        Transition transition4;
        Transition transition5;
        Transition transition6;
        Transition transition7;
        Transition transition8;
        Transition transition9;
        Transition transition10;
        Throwable th5;
        Transition transition11;
        XmlPullParser parser = xmlPullParser;
        AttributeSet attrs = attributeSet;
        Transition parent = transition;
        Transition transition12 = null;
        int depth = parser.getDepth();
        TransitionSet transitionSet = parent instanceof TransitionSet ? (TransitionSet) parent : null;
        while (true) {
            int next = parser.next();
            int type = next;
            if ((next != 3 || parser.getDepth() > depth) && type != 1) {
                if (type == 2) {
                    String name = parser.getName();
                    if ("fade".equals(name)) {
                        new Fade(this.mContext, attrs);
                        transition12 = transition11;
                    } else if ("changeBounds".equals(name)) {
                        new ChangeBounds(this.mContext, attrs);
                        transition12 = transition10;
                    } else if ("slide".equals(name)) {
                        new Slide(this.mContext, attrs);
                        transition12 = transition9;
                    } else if ("explode".equals(name)) {
                        new Explode(this.mContext, attrs);
                        transition12 = transition8;
                    } else if ("changeImageTransform".equals(name)) {
                        new ChangeImageTransform(this.mContext, attrs);
                        transition12 = transition7;
                    } else if ("changeTransform".equals(name)) {
                        new ChangeTransform(this.mContext, attrs);
                        transition12 = transition6;
                    } else if ("changeClipBounds".equals(name)) {
                        new ChangeClipBounds(this.mContext, attrs);
                        transition12 = transition5;
                    } else if ("autoTransition".equals(name)) {
                        new AutoTransition(this.mContext, attrs);
                        transition12 = transition4;
                    } else if ("changeScroll".equals(name)) {
                        new ChangeScroll(this.mContext, attrs);
                        transition12 = transition3;
                    } else if ("transitionSet".equals(name)) {
                        new TransitionSet(this.mContext, attrs);
                        transition12 = transition2;
                    } else if ("transition".equals(name)) {
                        transition12 = (Transition) createCustom(attrs, Transition.class, "transition");
                    } else if ("targets".equals(name)) {
                        getTargetIds(parser, attrs, parent);
                    } else if ("arcMotion".equals(name)) {
                        if (parent == null) {
                            Throwable th6 = th4;
                            new RuntimeException("Invalid use of arcMotion element");
                            throw th6;
                        }
                        new ArcMotion(this.mContext, attrs);
                        parent.setPathMotion(pathMotion2);
                    } else if ("pathMotion".equals(name)) {
                        if (parent == null) {
                            Throwable th7 = th3;
                            new RuntimeException("Invalid use of pathMotion element");
                            throw th7;
                        }
                        parent.setPathMotion((PathMotion) createCustom(attrs, PathMotion.class, "pathMotion"));
                    } else if (!"patternPathMotion".equals(name)) {
                        Throwable th8 = th;
                        new StringBuilder();
                        new RuntimeException(sb.append("Unknown scene name: ").append(parser.getName()).toString());
                        throw th8;
                    } else if (parent == null) {
                        Throwable th9 = th2;
                        new RuntimeException("Invalid use of patternPathMotion element");
                        throw th9;
                    } else {
                        new PatternPathMotion(this.mContext, attrs);
                        parent.setPathMotion(pathMotion);
                    }
                    if (transition12 == null) {
                        continue;
                    } else {
                        if (!parser.isEmptyElementTag()) {
                            Transition createTransitionFromXml = createTransitionFromXml(parser, attrs, transition12);
                        }
                        if (transitionSet != null) {
                            TransitionSet addTransition = transitionSet.addTransition(transition12);
                            transition12 = null;
                        } else if (parent != null) {
                            Throwable th10 = th5;
                            new InflateException("Could not add transition to another transition.");
                            throw th10;
                        }
                    }
                }
            }
        }
        return transition12;
    }

    private Object createCustom(AttributeSet attributeSet, Class cls, String str) {
        C1642ArrayMap<String, Constructor> arrayMap;
        Throwable th;
        Class<? extends U> asSubclass;
        Throwable th2;
        StringBuilder sb;
        AttributeSet attrs = attributeSet;
        Class expectedType = cls;
        String tag = str;
        String className = attrs.getAttributeValue((String) null, "class");
        if (className == null) {
            Throwable th3 = th2;
            new StringBuilder();
            new InflateException(sb.append(tag).append(" tag must have a 'class' attribute").toString());
            throw th3;
        }
        try {
            C1642ArrayMap<String, Constructor> arrayMap2 = CONSTRUCTORS;
            arrayMap = arrayMap2;
            synchronized (arrayMap2) {
                Constructor constructor = CONSTRUCTORS.get(className);
                if (constructor == null && (asSubclass = this.mContext.getClassLoader().loadClass(className).asSubclass(expectedType)) != null) {
                    constructor = asSubclass.getConstructor(CONSTRUCTOR_SIGNATURE);
                    constructor.setAccessible(true);
                    Constructor put = CONSTRUCTORS.put(className, constructor);
                }
                Object[] objArr = new Object[2];
                objArr[0] = this.mContext;
                Object[] objArr2 = objArr;
                objArr2[1] = attrs;
                Object newInstance = constructor.newInstance(objArr2);
                return newInstance;
            }
        } catch (Exception th4) {
            new StringBuilder();
            new InflateException(e.append("Could not instantiate ").append(expectedType).append(" class ").append(className).toString(), e);
            throw r9;
        } finally {
        }
    }

    private void getTargetIds(XmlPullParser xmlPullParser, AttributeSet attributeSet, Transition transition) throws XmlPullParserException, IOException {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        XmlPullParser parser = xmlPullParser;
        AttributeSet attrs = attributeSet;
        Transition transition2 = transition;
        int depth = parser.getDepth();
        while (true) {
            int next = parser.next();
            int type = next;
            if ((next == 3 && parser.getDepth() <= depth) || type == 1) {
                return;
            }
            if (type == 2) {
                if (parser.getName().equals("target")) {
                    TypedArray a = this.mContext.obtainStyledAttributes(attrs, Styleable.TRANSITION_TARGET);
                    int id = TypedArrayUtils.getNamedResourceId(a, parser, "targetId", 1, 0);
                    if (id != 0) {
                        Transition addTarget = transition2.addTarget(id);
                    } else {
                        int namedResourceId = TypedArrayUtils.getNamedResourceId(a, parser, "excludeId", 2, 0);
                        int id2 = namedResourceId;
                        if (namedResourceId != 0) {
                            Transition excludeTarget = transition2.excludeTarget(id2, true);
                        } else {
                            String namedString = TypedArrayUtils.getNamedString(a, parser, "targetName", 4);
                            String transitionName = namedString;
                            if (namedString != null) {
                                Transition addTarget2 = transition2.addTarget(transitionName);
                            } else {
                                String namedString2 = TypedArrayUtils.getNamedString(a, parser, "excludeName", 5);
                                String transitionName2 = namedString2;
                                if (namedString2 != null) {
                                    Transition excludeTarget2 = transition2.excludeTarget(transitionName2, true);
                                } else {
                                    String className = TypedArrayUtils.getNamedString(a, parser, "excludeClass", 3);
                                    if (className != null) {
                                        try {
                                            Transition excludeTarget3 = transition2.excludeTarget(Class.forName(className), true);
                                        } catch (ClassNotFoundException e) {
                                            ClassNotFoundException e2 = e;
                                            a.recycle();
                                            Throwable th3 = th2;
                                            new StringBuilder();
                                            new RuntimeException(sb2.append("Could not create ").append(className).toString(), e2);
                                            throw th3;
                                        }
                                    } else {
                                        String namedString3 = TypedArrayUtils.getNamedString(a, parser, "targetClass", 0);
                                        String className2 = namedString3;
                                        if (namedString3 != null) {
                                            Transition addTarget3 = transition2.addTarget(Class.forName(className2));
                                        }
                                    }
                                }
                            }
                        }
                    }
                    a.recycle();
                } else {
                    Throwable th4 = th;
                    new StringBuilder();
                    new RuntimeException(sb.append("Unknown scene name: ").append(parser.getName()).toString());
                    throw th4;
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0082, code lost:
        return r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.support.transition.TransitionManager createTransitionManagerFromXml(org.xmlpull.v1.XmlPullParser r15, android.util.AttributeSet r16, android.view.ViewGroup r17) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r14 = this;
            r0 = r14
            r1 = r15
            r2 = r16
            r3 = r17
            r8 = r1
            int r8 = r8.getDepth()
            r5 = r8
            r8 = 0
            r6 = r8
        L_0x000e:
            r8 = r1
            int r8 = r8.next()
            r13 = r8
            r8 = r13
            r9 = r13
            r4 = r9
            r9 = 3
            if (r8 != r9) goto L_0x0022
            r8 = r1
            int r8 = r8.getDepth()
            r9 = r5
            if (r8 <= r9) goto L_0x0080
        L_0x0022:
            r8 = r4
            r9 = 1
            if (r8 == r9) goto L_0x0080
            r8 = r4
            r9 = 2
            if (r8 == r9) goto L_0x002b
            goto L_0x000e
        L_0x002b:
            r8 = r1
            java.lang.String r8 = r8.getName()
            r7 = r8
            r8 = r7
            java.lang.String r9 = "transitionManager"
            boolean r8 = r8.equals(r9)
            if (r8 == 0) goto L_0x0045
            android.support.transition.TransitionManager r8 = new android.support.transition.TransitionManager
            r13 = r8
            r8 = r13
            r9 = r13
            r9.<init>()
            r6 = r8
        L_0x0044:
            goto L_0x000e
        L_0x0045:
            r8 = r7
            java.lang.String r9 = "transition"
            boolean r8 = r8.equals(r9)
            if (r8 == 0) goto L_0x005b
            r8 = r6
            if (r8 == 0) goto L_0x005b
            r8 = r0
            r9 = r2
            r10 = r1
            r11 = r3
            r12 = r6
            r8.loadTransition(r9, r10, r11, r12)
            goto L_0x0044
        L_0x005b:
            java.lang.RuntimeException r8 = new java.lang.RuntimeException
            r13 = r8
            r8 = r13
            r9 = r13
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r13 = r10
            r10 = r13
            r11 = r13
            r11.<init>()
            java.lang.String r11 = "Unknown scene name: "
            java.lang.StringBuilder r10 = r10.append(r11)
            r11 = r1
            java.lang.String r11 = r11.getName()
            java.lang.StringBuilder r10 = r10.append(r11)
            java.lang.String r10 = r10.toString()
            r9.<init>(r10)
            throw r8
        L_0x0080:
            r8 = r6
            r0 = r8
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.transition.TransitionInflater.createTransitionManagerFromXml(org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.view.ViewGroup):android.support.transition.TransitionManager");
    }

    private void loadTransition(AttributeSet attrs, XmlPullParser xmlPullParser, ViewGroup viewGroup, TransitionManager transitionManager) throws Resources.NotFoundException {
        Transition transition;
        Throwable th;
        StringBuilder sb;
        XmlPullParser parser = xmlPullParser;
        ViewGroup sceneRoot = viewGroup;
        TransitionManager transitionManager2 = transitionManager;
        TypedArray a = this.mContext.obtainStyledAttributes(attrs, Styleable.TRANSITION_MANAGER);
        int transitionId = TypedArrayUtils.getNamedResourceId(a, parser, "transition", 2, -1);
        int fromId = TypedArrayUtils.getNamedResourceId(a, parser, "fromScene", 0, -1);
        Scene fromScene = fromId < 0 ? null : Scene.getSceneForLayout(sceneRoot, fromId, this.mContext);
        int toId = TypedArrayUtils.getNamedResourceId(a, parser, "toScene", 1, -1);
        Scene toScene = toId < 0 ? null : Scene.getSceneForLayout(sceneRoot, toId, this.mContext);
        if (transitionId >= 0 && (transition = inflateTransition(transitionId)) != null) {
            if (toScene == null) {
                Throwable th2 = th;
                new StringBuilder();
                new RuntimeException(sb.append("No toScene for transition ID ").append(transitionId).toString());
                throw th2;
            } else if (fromScene == null) {
                transitionManager2.setTransition(toScene, transition);
            } else {
                transitionManager2.setTransition(fromScene, toScene, transition);
            }
        }
        a.recycle();
    }
}

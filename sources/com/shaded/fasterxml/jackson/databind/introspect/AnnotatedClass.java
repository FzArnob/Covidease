package com.shaded.fasterxml.jackson.databind.introspect;

import com.shaded.fasterxml.jackson.databind.AnnotationIntrospector;
import com.shaded.fasterxml.jackson.databind.introspect.ClassIntrospector;
import com.shaded.fasterxml.jackson.databind.util.Annotations;
import com.shaded.fasterxml.jackson.databind.util.ClassUtil;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public final class AnnotatedClass extends Annotated {
    private static final AnnotationMap[] NO_ANNOTATION_MAPS = new AnnotationMap[0];
    protected final AnnotationIntrospector _annotationIntrospector;
    protected final Class<?> _class;
    protected AnnotationMap _classAnnotations;
    protected List<AnnotatedConstructor> _constructors;
    protected List<AnnotatedMethod> _creatorMethods;
    protected boolean _creatorsResolved = false;
    protected AnnotatedConstructor _defaultConstructor;
    protected List<AnnotatedField> _fields;
    protected AnnotatedMethodMap _memberMethods;
    protected final ClassIntrospector.MixInResolver _mixInResolver;
    protected final Class<?> _primaryMixIn;
    protected final List<Class<?>> _superTypes;

    private AnnotatedClass(Class<?> cls, List<Class<?>> list, AnnotationIntrospector annotationIntrospector, ClassIntrospector.MixInResolver mixInResolver, AnnotationMap annotationMap) {
        AnnotationMap annotationMap2 = annotationMap;
        this._class = cls;
        this._superTypes = list;
        this._annotationIntrospector = annotationIntrospector;
        this._mixInResolver = mixInResolver;
        this._primaryMixIn = this._mixInResolver == null ? null : this._mixInResolver.findMixInClassFor(this._class);
        this._classAnnotations = annotationMap2;
    }

    public AnnotatedClass withAnnotations(AnnotationMap annotationMap) {
        AnnotatedClass annotatedClass;
        new AnnotatedClass(this._class, this._superTypes, this._annotationIntrospector, this._mixInResolver, annotationMap);
        return annotatedClass;
    }

    public static AnnotatedClass construct(Class<?> cls, AnnotationIntrospector annotationIntrospector, ClassIntrospector.MixInResolver mixInResolver) {
        AnnotatedClass annotatedClass;
        Class<?> cls2 = cls;
        new AnnotatedClass(cls2, ClassUtil.findSuperTypes(cls2, (Class<?>) null), annotationIntrospector, mixInResolver, (AnnotationMap) null);
        return annotatedClass;
    }

    public static AnnotatedClass constructWithoutSuperTypes(Class<?> cls, AnnotationIntrospector annotationIntrospector, ClassIntrospector.MixInResolver mixInResolver) {
        AnnotatedClass annotatedClass;
        new AnnotatedClass(cls, Collections.emptyList(), annotationIntrospector, mixInResolver, (AnnotationMap) null);
        return annotatedClass;
    }

    public Class<?> getAnnotated() {
        return this._class;
    }

    public int getModifiers() {
        return this._class.getModifiers();
    }

    public String getName() {
        return this._class.getName();
    }

    public <A extends Annotation> A getAnnotation(Class<A> cls) {
        Class<A> cls2 = cls;
        if (this._classAnnotations == null) {
            resolveClassAnnotations();
        }
        return this._classAnnotations.get(cls2);
    }

    public Type getGenericType() {
        return this._class;
    }

    public Class<?> getRawType() {
        return this._class;
    }

    /* access modifiers changed from: protected */
    public AnnotationMap getAllAnnotations() {
        if (this._classAnnotations == null) {
            resolveClassAnnotations();
        }
        return this._classAnnotations;
    }

    public Annotations getAnnotations() {
        if (this._classAnnotations == null) {
            resolveClassAnnotations();
        }
        return this._classAnnotations;
    }

    public boolean hasAnnotations() {
        if (this._classAnnotations == null) {
            resolveClassAnnotations();
        }
        return this._classAnnotations.size() > 0;
    }

    public AnnotatedConstructor getDefaultConstructor() {
        if (!this._creatorsResolved) {
            resolveCreators();
        }
        return this._defaultConstructor;
    }

    public List<AnnotatedConstructor> getConstructors() {
        if (!this._creatorsResolved) {
            resolveCreators();
        }
        return this._constructors;
    }

    public List<AnnotatedMethod> getStaticMethods() {
        if (!this._creatorsResolved) {
            resolveCreators();
        }
        return this._creatorMethods;
    }

    public Iterable<AnnotatedMethod> memberMethods() {
        if (this._memberMethods == null) {
            resolveMemberMethods();
        }
        return this._memberMethods;
    }

    public int getMemberMethodCount() {
        if (this._memberMethods == null) {
            resolveMemberMethods();
        }
        return this._memberMethods.size();
    }

    public AnnotatedMethod findMethod(String str, Class<?>[] clsArr) {
        String str2 = str;
        Class<?>[] clsArr2 = clsArr;
        if (this._memberMethods == null) {
            resolveMemberMethods();
        }
        return this._memberMethods.find(str2, clsArr2);
    }

    public int getFieldCount() {
        if (this._fields == null) {
            resolveFields();
        }
        return this._fields.size();
    }

    public Iterable<AnnotatedField> fields() {
        if (this._fields == null) {
            resolveFields();
        }
        return this._fields;
    }

    private void resolveClassAnnotations() {
        AnnotationMap annotationMap;
        new AnnotationMap();
        this._classAnnotations = annotationMap;
        if (this._annotationIntrospector != null) {
            if (this._primaryMixIn != null) {
                _addClassMixIns(this._classAnnotations, this._class, this._primaryMixIn);
            }
            _addAnnotationsIfNotPresent(this._classAnnotations, this._class.getDeclaredAnnotations());
            for (Class next : this._superTypes) {
                _addClassMixIns(this._classAnnotations, next);
                _addAnnotationsIfNotPresent(this._classAnnotations, next.getDeclaredAnnotations());
            }
            _addClassMixIns(this._classAnnotations, Object.class);
        }
    }

    private void resolveCreators() {
        List<AnnotatedMethod> list;
        List<AnnotatedConstructor> list2;
        List<AnnotatedConstructor> list3 = null;
        Constructor[] declaredConstructors = this._class.getDeclaredConstructors();
        Constructor[] constructorArr = declaredConstructors;
        int length = constructorArr.length;
        for (int i = 0; i < length; i++) {
            Constructor constructor = constructorArr[i];
            if (constructor.getParameterTypes().length == 0) {
                this._defaultConstructor = _constructConstructor(constructor, true);
            } else {
                if (list3 == null) {
                    new ArrayList(Math.max(10, declaredConstructors.length));
                    list3 = list2;
                }
                boolean add = list3.add(_constructConstructor(constructor, false));
            }
        }
        if (list3 == null) {
            this._constructors = Collections.emptyList();
        } else {
            this._constructors = list3;
        }
        if (this._primaryMixIn != null && (this._defaultConstructor != null || !this._constructors.isEmpty())) {
            _addConstructorMixIns(this._primaryMixIn);
        }
        if (this._annotationIntrospector != null) {
            if (this._defaultConstructor != null && this._annotationIntrospector.hasIgnoreMarker(this._defaultConstructor)) {
                this._defaultConstructor = null;
            }
            if (this._constructors != null) {
                int size = this._constructors.size();
                while (true) {
                    size--;
                    if (size < 0) {
                        break;
                    } else if (this._annotationIntrospector.hasIgnoreMarker(this._constructors.get(size))) {
                        AnnotatedConstructor remove = this._constructors.remove(size);
                    }
                }
            }
        }
        List<AnnotatedMethod> list4 = null;
        Method[] declaredMethods = this._class.getDeclaredMethods();
        int length2 = declaredMethods.length;
        for (int i2 = 0; i2 < length2; i2++) {
            Method method = declaredMethods[i2];
            if (Modifier.isStatic(method.getModifiers())) {
                if (list4 == null) {
                    new ArrayList(8);
                    list4 = list;
                }
                boolean add2 = list4.add(_constructCreatorMethod(method));
            }
        }
        if (list4 == null) {
            this._creatorMethods = Collections.emptyList();
        } else {
            this._creatorMethods = list4;
            if (this._primaryMixIn != null) {
                _addFactoryMixIns(this._primaryMixIn);
            }
            if (this._annotationIntrospector != null) {
                int size2 = this._creatorMethods.size();
                while (true) {
                    size2--;
                    if (size2 < 0) {
                        break;
                    } else if (this._annotationIntrospector.hasIgnoreMarker(this._creatorMethods.get(size2))) {
                        AnnotatedMethod remove2 = this._creatorMethods.remove(size2);
                    }
                }
            }
        }
        this._creatorsResolved = true;
    }

    private void resolveMemberMethods() {
        AnnotatedMethodMap annotatedMethodMap;
        AnnotatedMethodMap annotatedMethodMap2;
        Class<?> findMixInClassFor;
        new AnnotatedMethodMap();
        this._memberMethods = annotatedMethodMap;
        new AnnotatedMethodMap();
        AnnotatedMethodMap annotatedMethodMap3 = annotatedMethodMap2;
        _addMemberMethods(this._class, this._memberMethods, this._primaryMixIn, annotatedMethodMap3);
        for (Class next : this._superTypes) {
            _addMemberMethods(next, this._memberMethods, this._mixInResolver == null ? null : this._mixInResolver.findMixInClassFor(next), annotatedMethodMap3);
        }
        if (!(this._mixInResolver == null || (findMixInClassFor = this._mixInResolver.findMixInClassFor(Object.class)) == null)) {
            _addMethodMixIns(this._class, this._memberMethods, findMixInClassFor, annotatedMethodMap3);
        }
        if (this._annotationIntrospector != null && !annotatedMethodMap3.isEmpty()) {
            Iterator<AnnotatedMethod> it = annotatedMethodMap3.iterator();
            while (it.hasNext()) {
                AnnotatedMethod next2 = it.next();
                try {
                    Method declaredMethod = Object.class.getDeclaredMethod(next2.getName(), next2.getRawParameterTypes());
                    if (declaredMethod != null) {
                        AnnotatedMethod _constructMethod = _constructMethod(declaredMethod);
                        _addMixOvers(next2.getAnnotated(), _constructMethod, false);
                        this._memberMethods.add(_constructMethod);
                    }
                } catch (Exception e) {
                    Exception exc = e;
                }
            }
        }
    }

    private void resolveFields() {
        List<AnnotatedField> list;
        Map<String, AnnotatedField> _findFields = _findFields(this._class, (Map<String, AnnotatedField>) null);
        if (_findFields == null || _findFields.size() == 0) {
            this._fields = Collections.emptyList();
            return;
        }
        new ArrayList(_findFields.size());
        this._fields = list;
        boolean addAll = this._fields.addAll(_findFields.values());
    }

    /* access modifiers changed from: protected */
    public void _addClassMixIns(AnnotationMap annotationMap, Class<?> cls) {
        AnnotationMap annotationMap2 = annotationMap;
        Class<?> cls2 = cls;
        if (this._mixInResolver != null) {
            _addClassMixIns(annotationMap2, cls2, this._mixInResolver.findMixInClassFor(cls2));
        }
    }

    /* access modifiers changed from: protected */
    public void _addClassMixIns(AnnotationMap annotationMap, Class<?> cls, Class<?> cls2) {
        AnnotationMap annotationMap2 = annotationMap;
        Class<?> cls3 = cls;
        Class<?> cls4 = cls2;
        if (cls4 != null) {
            _addAnnotationsIfNotPresent(annotationMap2, cls4.getDeclaredAnnotations());
            for (Class declaredAnnotations : ClassUtil.findSuperTypes(cls4, cls3)) {
                _addAnnotationsIfNotPresent(annotationMap2, declaredAnnotations.getDeclaredAnnotations());
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _addConstructorMixIns(Class<?> cls) {
        MemberKey memberKey;
        Object obj;
        Class<?> cls2 = cls;
        MemberKey[] memberKeyArr = null;
        int size = this._constructors == null ? 0 : this._constructors.size();
        Constructor[] declaredConstructors = cls2.getDeclaredConstructors();
        int length = declaredConstructors.length;
        for (int i = 0; i < length; i++) {
            Constructor constructor = declaredConstructors[i];
            if (constructor.getParameterTypes().length != 0) {
                if (memberKeyArr == null) {
                    memberKeyArr = new MemberKey[size];
                    for (int i2 = 0; i2 < size; i2++) {
                        new MemberKey((Constructor<?>) this._constructors.get(i2).getAnnotated());
                        memberKeyArr[i2] = obj;
                    }
                }
                new MemberKey((Constructor<?>) constructor);
                MemberKey memberKey2 = memberKey;
                int i3 = 0;
                while (true) {
                    if (i3 < size) {
                        if (memberKey2.equals(memberKeyArr[i3])) {
                            _addMixOvers((Constructor<?>) constructor, this._constructors.get(i3), true);
                            break;
                        }
                        i3++;
                    } else {
                        break;
                    }
                }
            } else if (this._defaultConstructor != null) {
                _addMixOvers((Constructor<?>) constructor, this._defaultConstructor, false);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _addFactoryMixIns(Class<?> cls) {
        MemberKey memberKey;
        Object obj;
        MemberKey[] memberKeyArr = null;
        int size = this._creatorMethods.size();
        Method[] declaredMethods = cls.getDeclaredMethods();
        int length = declaredMethods.length;
        for (int i = 0; i < length; i++) {
            Method method = declaredMethods[i];
            if (Modifier.isStatic(method.getModifiers()) && method.getParameterTypes().length != 0) {
                if (memberKeyArr == null) {
                    memberKeyArr = new MemberKey[size];
                    for (int i2 = 0; i2 < size; i2++) {
                        new MemberKey(this._creatorMethods.get(i2).getAnnotated());
                        memberKeyArr[i2] = obj;
                    }
                }
                new MemberKey(method);
                MemberKey memberKey2 = memberKey;
                int i3 = 0;
                while (true) {
                    if (i3 < size) {
                        if (memberKey2.equals(memberKeyArr[i3])) {
                            _addMixOvers(method, this._creatorMethods.get(i3), true);
                            break;
                        }
                        i3++;
                    } else {
                        break;
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _addMemberMethods(Class<?> cls, AnnotatedMethodMap annotatedMethodMap, Class<?> cls2, AnnotatedMethodMap annotatedMethodMap2) {
        Class<?> cls3 = cls;
        AnnotatedMethodMap annotatedMethodMap3 = annotatedMethodMap;
        Class<?> cls4 = cls2;
        AnnotatedMethodMap annotatedMethodMap4 = annotatedMethodMap2;
        if (cls4 != null) {
            _addMethodMixIns(cls3, annotatedMethodMap3, cls4, annotatedMethodMap4);
        }
        if (cls3 != null) {
            Method[] declaredMethods = cls3.getDeclaredMethods();
            int length = declaredMethods.length;
            for (int i = 0; i < length; i++) {
                Method method = declaredMethods[i];
                if (_isIncludableMemberMethod(method)) {
                    AnnotatedMethod find = annotatedMethodMap3.find(method);
                    if (find == null) {
                        AnnotatedMethod _constructMethod = _constructMethod(method);
                        annotatedMethodMap3.add(_constructMethod);
                        AnnotatedMethod remove = annotatedMethodMap4.remove(method);
                        if (remove != null) {
                            _addMixOvers(remove.getAnnotated(), _constructMethod, false);
                        }
                    } else {
                        _addMixUnders(method, find);
                        if (find.getDeclaringClass().isInterface() && !method.getDeclaringClass().isInterface()) {
                            annotatedMethodMap3.add(find.withMethod(method));
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _addMethodMixIns(Class<?> cls, AnnotatedMethodMap annotatedMethodMap, Class<?> cls2, AnnotatedMethodMap annotatedMethodMap2) {
        List list;
        AnnotatedMethodMap annotatedMethodMap3 = annotatedMethodMap;
        Class<?> cls3 = cls2;
        AnnotatedMethodMap annotatedMethodMap4 = annotatedMethodMap2;
        new ArrayList();
        List<Class> list2 = list;
        boolean add = list2.add(cls3);
        List<Class<?>> findSuperTypes = ClassUtil.findSuperTypes(cls3, cls, list2);
        for (Class declaredMethods : list2) {
            Method[] declaredMethods2 = declaredMethods.getDeclaredMethods();
            int length = declaredMethods2.length;
            for (int i = 0; i < length; i++) {
                Method method = declaredMethods2[i];
                if (_isIncludableMemberMethod(method)) {
                    AnnotatedMethod find = annotatedMethodMap3.find(method);
                    if (find != null) {
                        _addMixUnders(method, find);
                    } else {
                        annotatedMethodMap4.add(_constructMethod(method));
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public Map<String, AnnotatedField> _findFields(Class<?> cls, Map<String, AnnotatedField> map) {
        Class<?> findMixInClassFor;
        Map<String, AnnotatedField> map2;
        Class<?> cls2 = cls;
        Map<String, AnnotatedField> map3 = map;
        Class<? super Object> superclass = cls2.getSuperclass();
        if (superclass != null) {
            map3 = _findFields(superclass, map3);
            Field[] declaredFields = cls2.getDeclaredFields();
            int length = declaredFields.length;
            for (int i = 0; i < length; i++) {
                Field field = declaredFields[i];
                if (_isIncludableField(field)) {
                    if (map3 == null) {
                        new LinkedHashMap();
                        map3 = map2;
                    }
                    AnnotatedField put = map3.put(field.getName(), _constructField(field));
                }
            }
            if (!(this._mixInResolver == null || (findMixInClassFor = this._mixInResolver.findMixInClassFor(cls2)) == null)) {
                _addFieldMixIns(superclass, findMixInClassFor, map3);
            }
        }
        return map3;
    }

    /* access modifiers changed from: protected */
    public void _addFieldMixIns(Class<?> cls, Class<?> cls2, Map<String, AnnotatedField> map) {
        List list;
        AnnotatedField annotatedField;
        Class<?> cls3 = cls2;
        Map<String, AnnotatedField> map2 = map;
        new ArrayList();
        List<Class> list2 = list;
        boolean add = list2.add(cls3);
        List<Class<?>> findSuperTypes = ClassUtil.findSuperTypes(cls3, cls, list2);
        for (Class declaredFields : list2) {
            Field[] declaredFields2 = declaredFields.getDeclaredFields();
            int length = declaredFields2.length;
            for (int i = 0; i < length; i++) {
                Field field = declaredFields2[i];
                if (_isIncludableField(field) && (annotatedField = map2.get(field.getName())) != null) {
                    _addOrOverrideAnnotations(annotatedField, field.getDeclaredAnnotations());
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public AnnotatedMethod _constructMethod(Method method) {
        AnnotatedMethod annotatedMethod;
        AnnotatedMethod annotatedMethod2;
        Method method2 = method;
        if (this._annotationIntrospector == null) {
            new AnnotatedMethod(method2, _emptyAnnotationMap(), (AnnotationMap[]) null);
            return annotatedMethod2;
        }
        new AnnotatedMethod(method2, _collectRelevantAnnotations(method2.getDeclaredAnnotations()), (AnnotationMap[]) null);
        return annotatedMethod;
    }

    /* access modifiers changed from: protected */
    public AnnotatedConstructor _constructConstructor(Constructor<?> constructor, boolean z) {
        AnnotatedConstructor annotatedConstructor;
        Throwable th;
        StringBuilder sb;
        AnnotatedConstructor annotatedConstructor2;
        AnnotatedConstructor annotatedConstructor3;
        Constructor<?> constructor2 = constructor;
        boolean z2 = z;
        if (this._annotationIntrospector == null) {
            new AnnotatedConstructor(constructor2, _emptyAnnotationMap(), _emptyAnnotationMaps(constructor2.getParameterTypes().length));
            return annotatedConstructor3;
        } else if (z2) {
            new AnnotatedConstructor(constructor2, _collectRelevantAnnotations(constructor2.getDeclaredAnnotations()), (AnnotationMap[]) null);
            return annotatedConstructor2;
        } else {
            Annotation[][] parameterAnnotations = constructor2.getParameterAnnotations();
            int length = constructor2.getParameterTypes().length;
            AnnotationMap[] annotationMapArr = null;
            if (length != parameterAnnotations.length) {
                Class<?> declaringClass = constructor2.getDeclaringClass();
                if (declaringClass.isEnum() && length == parameterAnnotations.length + 2) {
                    Annotation[][] annotationArr = parameterAnnotations;
                    parameterAnnotations = new Annotation[(annotationArr.length + 2)][];
                    System.arraycopy(annotationArr, 0, parameterAnnotations, 2, annotationArr.length);
                    annotationMapArr = _collectRelevantAnnotations(parameterAnnotations);
                } else if (declaringClass.isMemberClass() && length == parameterAnnotations.length + 1) {
                    Annotation[][] annotationArr2 = parameterAnnotations;
                    parameterAnnotations = new Annotation[(annotationArr2.length + 1)][];
                    System.arraycopy(annotationArr2, 0, parameterAnnotations, 1, annotationArr2.length);
                    annotationMapArr = _collectRelevantAnnotations(parameterAnnotations);
                }
                if (annotationMapArr == null) {
                    Throwable th2 = th;
                    new StringBuilder();
                    new IllegalStateException(sb.append("Internal error: constructor for ").append(constructor2.getDeclaringClass().getName()).append(" has mismatch: ").append(length).append(" parameters; ").append(parameterAnnotations.length).append(" sets of annotations").toString());
                    throw th2;
                }
            } else {
                annotationMapArr = _collectRelevantAnnotations(parameterAnnotations);
            }
            new AnnotatedConstructor(constructor2, _collectRelevantAnnotations(constructor2.getDeclaredAnnotations()), annotationMapArr);
            return annotatedConstructor;
        }
    }

    /* access modifiers changed from: protected */
    public AnnotatedMethod _constructCreatorMethod(Method method) {
        AnnotatedMethod annotatedMethod;
        AnnotatedMethod annotatedMethod2;
        Method method2 = method;
        if (this._annotationIntrospector == null) {
            new AnnotatedMethod(method2, _emptyAnnotationMap(), _emptyAnnotationMaps(method2.getParameterTypes().length));
            return annotatedMethod2;
        }
        new AnnotatedMethod(method2, _collectRelevantAnnotations(method2.getDeclaredAnnotations()), _collectRelevantAnnotations(method2.getParameterAnnotations()));
        return annotatedMethod;
    }

    /* access modifiers changed from: protected */
    public AnnotatedField _constructField(Field field) {
        AnnotatedField annotatedField;
        AnnotatedField annotatedField2;
        Field field2 = field;
        if (this._annotationIntrospector == null) {
            new AnnotatedField(field2, _emptyAnnotationMap());
            return annotatedField2;
        }
        new AnnotatedField(field2, _collectRelevantAnnotations(field2.getDeclaredAnnotations()));
        return annotatedField;
    }

    private AnnotationMap _emptyAnnotationMap() {
        AnnotationMap annotationMap;
        new AnnotationMap();
        return annotationMap;
    }

    private AnnotationMap[] _emptyAnnotationMaps(int i) {
        int i2 = i;
        if (i2 == 0) {
            return NO_ANNOTATION_MAPS;
        }
        AnnotationMap[] annotationMapArr = new AnnotationMap[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            annotationMapArr[i3] = _emptyAnnotationMap();
        }
        return annotationMapArr;
    }

    /* access modifiers changed from: protected */
    public boolean _isIncludableMemberMethod(Method method) {
        Method method2 = method;
        if (Modifier.isStatic(method2.getModifiers())) {
            return false;
        }
        if (method2.isSynthetic() || method2.isBridge()) {
            return false;
        }
        return method2.getParameterTypes().length <= 2;
    }

    private boolean _isIncludableField(Field field) {
        Field field2 = field;
        if (field2.isSynthetic()) {
            return false;
        }
        int modifiers = field2.getModifiers();
        if (Modifier.isStatic(modifiers) || Modifier.isTransient(modifiers)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public AnnotationMap[] _collectRelevantAnnotations(Annotation[][] annotationArr) {
        Annotation[][] annotationArr2 = annotationArr;
        int length = annotationArr2.length;
        AnnotationMap[] annotationMapArr = new AnnotationMap[length];
        for (int i = 0; i < length; i++) {
            annotationMapArr[i] = _collectRelevantAnnotations(annotationArr2[i]);
        }
        return annotationMapArr;
    }

    /* access modifiers changed from: protected */
    public AnnotationMap _collectRelevantAnnotations(Annotation[] annotationArr) {
        AnnotationMap annotationMap;
        new AnnotationMap();
        AnnotationMap annotationMap2 = annotationMap;
        _addAnnotationsIfNotPresent(annotationMap2, annotationArr);
        return annotationMap2;
    }

    private void _addAnnotationsIfNotPresent(AnnotationMap annotationMap, Annotation[] annotationArr) {
        List list;
        AnnotationMap annotationMap2 = annotationMap;
        Annotation[] annotationArr2 = annotationArr;
        if (annotationArr2 != null) {
            List<Annotation[]> list2 = null;
            Annotation[] annotationArr3 = annotationArr2;
            int length = annotationArr3.length;
            for (int i = 0; i < length; i++) {
                Annotation annotation = annotationArr3[i];
                if (_isAnnotationBundle(annotation)) {
                    if (list2 == null) {
                        new LinkedList();
                        list2 = list;
                    }
                    boolean add = list2.add(annotation.annotationType().getDeclaredAnnotations());
                } else {
                    annotationMap2.addIfNotPresent(annotation);
                }
            }
            if (list2 != null) {
                for (Annotation[] _addAnnotationsIfNotPresent : list2) {
                    _addAnnotationsIfNotPresent(annotationMap2, _addAnnotationsIfNotPresent);
                }
            }
        }
    }

    private void _addAnnotationsIfNotPresent(AnnotatedMember annotatedMember, Annotation[] annotationArr) {
        List list;
        AnnotatedMember annotatedMember2 = annotatedMember;
        Annotation[] annotationArr2 = annotationArr;
        if (annotationArr2 != null) {
            List<Annotation[]> list2 = null;
            Annotation[] annotationArr3 = annotationArr2;
            int length = annotationArr3.length;
            for (int i = 0; i < length; i++) {
                Annotation annotation = annotationArr3[i];
                if (_isAnnotationBundle(annotation)) {
                    if (list2 == null) {
                        new LinkedList();
                        list2 = list;
                    }
                    boolean add = list2.add(annotation.annotationType().getDeclaredAnnotations());
                } else {
                    annotatedMember2.addIfNotPresent(annotation);
                }
            }
            if (list2 != null) {
                for (Annotation[] _addAnnotationsIfNotPresent : list2) {
                    _addAnnotationsIfNotPresent(annotatedMember2, _addAnnotationsIfNotPresent);
                }
            }
        }
    }

    private void _addOrOverrideAnnotations(AnnotatedMember annotatedMember, Annotation[] annotationArr) {
        List list;
        AnnotatedMember annotatedMember2 = annotatedMember;
        Annotation[] annotationArr2 = annotationArr;
        if (annotationArr2 != null) {
            List<Annotation[]> list2 = null;
            Annotation[] annotationArr3 = annotationArr2;
            int length = annotationArr3.length;
            for (int i = 0; i < length; i++) {
                Annotation annotation = annotationArr3[i];
                if (_isAnnotationBundle(annotation)) {
                    if (list2 == null) {
                        new LinkedList();
                        list2 = list;
                    }
                    boolean add = list2.add(annotation.annotationType().getDeclaredAnnotations());
                } else {
                    annotatedMember2.addOrOverride(annotation);
                }
            }
            if (list2 != null) {
                for (Annotation[] _addOrOverrideAnnotations : list2) {
                    _addOrOverrideAnnotations(annotatedMember2, _addOrOverrideAnnotations);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _addMixOvers(Constructor<?> constructor, AnnotatedConstructor annotatedConstructor, boolean z) {
        Constructor<?> constructor2 = constructor;
        AnnotatedConstructor annotatedConstructor2 = annotatedConstructor;
        _addOrOverrideAnnotations(annotatedConstructor2, constructor2.getDeclaredAnnotations());
        if (z) {
            Annotation[][] parameterAnnotations = constructor2.getParameterAnnotations();
            int length = parameterAnnotations.length;
            for (int i = 0; i < length; i++) {
                Annotation[] annotationArr = parameterAnnotations[i];
                int length2 = annotationArr.length;
                for (int i2 = 0; i2 < length2; i2++) {
                    annotatedConstructor2.addOrOverrideParam(i, annotationArr[i2]);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _addMixOvers(Method method, AnnotatedMethod annotatedMethod, boolean z) {
        Method method2 = method;
        AnnotatedMethod annotatedMethod2 = annotatedMethod;
        _addOrOverrideAnnotations(annotatedMethod2, method2.getDeclaredAnnotations());
        if (z) {
            Annotation[][] parameterAnnotations = method2.getParameterAnnotations();
            int length = parameterAnnotations.length;
            for (int i = 0; i < length; i++) {
                Annotation[] annotationArr = parameterAnnotations[i];
                int length2 = annotationArr.length;
                for (int i2 = 0; i2 < length2; i2++) {
                    annotatedMethod2.addOrOverrideParam(i, annotationArr[i2]);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _addMixUnders(Method method, AnnotatedMethod annotatedMethod) {
        _addAnnotationsIfNotPresent((AnnotatedMember) annotatedMethod, method.getDeclaredAnnotations());
    }

    private final boolean _isAnnotationBundle(Annotation annotation) {
        return this._annotationIntrospector != null && this._annotationIntrospector.isAnnotationBundle(annotation);
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        return sb.append("[AnnotedClass ").append(this._class.getName()).append("]").toString();
    }
}

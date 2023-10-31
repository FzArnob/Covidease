package com.shaded.fasterxml.jackson.databind.introspect;

import com.shaded.fasterxml.jackson.databind.AnnotationIntrospector;
import com.shaded.fasterxml.jackson.databind.PropertyName;

public class POJOPropertyBuilder extends BeanPropertyDefinition implements Comparable<POJOPropertyBuilder> {
    protected final AnnotationIntrospector _annotationIntrospector;
    protected Linked<AnnotatedParameter> _ctorParameters;
    protected Linked<AnnotatedField> _fields;
    protected final boolean _forSerialization;
    protected Linked<AnnotatedMethod> _getters;
    protected final String _internalName;
    protected final String _name;
    protected Linked<AnnotatedMethod> _setters;

    private interface WithMember<T> {
        T withMember(AnnotatedMember annotatedMember);
    }

    public POJOPropertyBuilder(String str, AnnotationIntrospector annotationIntrospector, boolean z) {
        String str2 = str;
        this._internalName = str2;
        this._name = str2;
        this._annotationIntrospector = annotationIntrospector;
        this._forSerialization = z;
    }

    public POJOPropertyBuilder(POJOPropertyBuilder pOJOPropertyBuilder, String str) {
        POJOPropertyBuilder pOJOPropertyBuilder2 = pOJOPropertyBuilder;
        this._internalName = pOJOPropertyBuilder2._internalName;
        this._name = str;
        this._annotationIntrospector = pOJOPropertyBuilder2._annotationIntrospector;
        this._fields = pOJOPropertyBuilder2._fields;
        this._ctorParameters = pOJOPropertyBuilder2._ctorParameters;
        this._getters = pOJOPropertyBuilder2._getters;
        this._setters = pOJOPropertyBuilder2._setters;
        this._forSerialization = pOJOPropertyBuilder2._forSerialization;
    }

    public POJOPropertyBuilder withName(String str) {
        POJOPropertyBuilder pOJOPropertyBuilder;
        new POJOPropertyBuilder(this, str);
        return pOJOPropertyBuilder;
    }

    public int compareTo(POJOPropertyBuilder pOJOPropertyBuilder) {
        POJOPropertyBuilder pOJOPropertyBuilder2 = pOJOPropertyBuilder;
        if (this._ctorParameters != null) {
            if (pOJOPropertyBuilder2._ctorParameters == null) {
                return -1;
            }
        } else if (pOJOPropertyBuilder2._ctorParameters != null) {
            return 1;
        }
        return getName().compareTo(pOJOPropertyBuilder2.getName());
    }

    public String getName() {
        return this._name;
    }

    public String getInternalName() {
        return this._internalName;
    }

    public PropertyName getWrapperName() {
        AnnotatedMember primaryMember = getPrimaryMember();
        return (primaryMember == null || this._annotationIntrospector == null) ? null : this._annotationIntrospector.findWrapperName(primaryMember);
    }

    public boolean isExplicitlyIncluded() {
        return _anyExplicitNames(this._fields) || _anyExplicitNames(this._getters) || _anyExplicitNames(this._setters) || _anyExplicitNames(this._ctorParameters);
    }

    public boolean hasGetter() {
        return this._getters != null;
    }

    public boolean hasSetter() {
        return this._setters != null;
    }

    public boolean hasField() {
        return this._fields != null;
    }

    public boolean hasConstructorParameter() {
        return this._ctorParameters != null;
    }

    public boolean couldSerialize() {
        return (this._getters == null && this._fields == null) ? false : true;
    }

    public AnnotatedMethod getGetter() {
        AnnotatedMethod annotatedMethod;
        Throwable th;
        StringBuilder sb;
        if (this._getters == null) {
            return null;
        }
        AnnotatedMethod annotatedMethod2 = (AnnotatedMethod) this._getters.value;
        Linked<T> linked = this._getters.next;
        while (true) {
            Linked<T> linked2 = linked;
            if (linked2 == null) {
                return annotatedMethod2;
            }
            annotatedMethod = (AnnotatedMethod) linked2.value;
            Class<?> declaringClass = annotatedMethod2.getDeclaringClass();
            Class<?> declaringClass2 = annotatedMethod.getDeclaringClass();
            if (declaringClass == declaringClass2) {
                break;
            }
            if (!declaringClass.isAssignableFrom(declaringClass2)) {
                if (!declaringClass2.isAssignableFrom(declaringClass)) {
                    break;
                }
            } else {
                annotatedMethod2 = annotatedMethod;
            }
            linked = linked2.next;
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalArgumentException(sb.append("Conflicting getter definitions for property \"").append(getName()).append("\": ").append(annotatedMethod2.getFullName()).append(" vs ").append(annotatedMethod.getFullName()).toString());
        throw th2;
    }

    public AnnotatedMethod getSetter() {
        AnnotatedMethod annotatedMethod;
        Throwable th;
        StringBuilder sb;
        if (this._setters == null) {
            return null;
        }
        AnnotatedMethod annotatedMethod2 = (AnnotatedMethod) this._setters.value;
        Linked<T> linked = this._setters.next;
        while (true) {
            Linked<T> linked2 = linked;
            if (linked2 == null) {
                return annotatedMethod2;
            }
            annotatedMethod = (AnnotatedMethod) linked2.value;
            Class<?> declaringClass = annotatedMethod2.getDeclaringClass();
            Class<?> declaringClass2 = annotatedMethod.getDeclaringClass();
            if (declaringClass == declaringClass2) {
                break;
            }
            if (!declaringClass.isAssignableFrom(declaringClass2)) {
                if (!declaringClass2.isAssignableFrom(declaringClass)) {
                    break;
                }
            } else {
                annotatedMethod2 = annotatedMethod;
            }
            linked = linked2.next;
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalArgumentException(sb.append("Conflicting setter definitions for property \"").append(getName()).append("\": ").append(annotatedMethod2.getFullName()).append(" vs ").append(annotatedMethod.getFullName()).toString());
        throw th2;
    }

    public AnnotatedField getField() {
        AnnotatedField annotatedField;
        Throwable th;
        StringBuilder sb;
        if (this._fields == null) {
            return null;
        }
        AnnotatedField annotatedField2 = (AnnotatedField) this._fields.value;
        Linked<T> linked = this._fields.next;
        while (true) {
            Linked<T> linked2 = linked;
            if (linked2 == null) {
                return annotatedField2;
            }
            annotatedField = (AnnotatedField) linked2.value;
            Class<?> declaringClass = annotatedField2.getDeclaringClass();
            Class<?> declaringClass2 = annotatedField.getDeclaringClass();
            if (declaringClass == declaringClass2) {
                break;
            }
            if (!declaringClass.isAssignableFrom(declaringClass2)) {
                if (!declaringClass2.isAssignableFrom(declaringClass)) {
                    break;
                }
            } else {
                annotatedField2 = annotatedField;
            }
            linked = linked2.next;
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalArgumentException(sb.append("Multiple fields representing property \"").append(getName()).append("\": ").append(annotatedField2.getFullName()).append(" vs ").append(annotatedField.getFullName()).toString());
        throw th2;
    }

    public AnnotatedParameter getConstructorParameter() {
        if (this._ctorParameters == null) {
            return null;
        }
        Linked<T> linked = this._ctorParameters;
        while (!(((AnnotatedParameter) linked.value).getOwner() instanceof AnnotatedConstructor)) {
            linked = linked.next;
            if (linked == null) {
                return (AnnotatedParameter) this._ctorParameters.value;
            }
        }
        return (AnnotatedParameter) linked.value;
    }

    public AnnotatedMember getAccessor() {
        AnnotatedMember getter = getGetter();
        if (getter == null) {
            getter = getField();
        }
        return getter;
    }

    public AnnotatedMember getMutator() {
        AnnotatedMember constructorParameter = getConstructorParameter();
        if (constructorParameter == null) {
            constructorParameter = getSetter();
            if (constructorParameter == null) {
                constructorParameter = getField();
            }
        }
        return constructorParameter;
    }

    public AnnotatedMember getPrimaryMember() {
        if (this._forSerialization) {
            return getAccessor();
        }
        return getMutator();
    }

    public Class<?>[] findViews() {
        WithMember withMember;
        new WithMember<Class<?>[]>(this) {
            final /* synthetic */ POJOPropertyBuilder this$0;

            {
                this.this$0 = r5;
            }

            public Class<?>[] withMember(AnnotatedMember annotatedMember) {
                return this.this$0._annotationIntrospector.findViews(annotatedMember);
            }
        };
        return (Class[]) fromMemberAnnotations(withMember);
    }

    public AnnotationIntrospector.ReferenceProperty findReferenceType() {
        WithMember withMember;
        new WithMember<AnnotationIntrospector.ReferenceProperty>(this) {
            final /* synthetic */ POJOPropertyBuilder this$0;

            {
                this.this$0 = r5;
            }

            public AnnotationIntrospector.ReferenceProperty withMember(AnnotatedMember annotatedMember) {
                return this.this$0._annotationIntrospector.findReferenceType(annotatedMember);
            }
        };
        return (AnnotationIntrospector.ReferenceProperty) fromMemberAnnotations(withMember);
    }

    public boolean isTypeId() {
        WithMember withMember;
        new WithMember<Boolean>(this) {
            final /* synthetic */ POJOPropertyBuilder this$0;

            {
                this.this$0 = r5;
            }

            public Boolean withMember(AnnotatedMember annotatedMember) {
                return this.this$0._annotationIntrospector.isTypeId(annotatedMember);
            }
        };
        Boolean bool = (Boolean) fromMemberAnnotations(withMember);
        return bool != null && bool.booleanValue();
    }

    public boolean isRequired() {
        WithMember withMember;
        new WithMember<Boolean>(this) {
            final /* synthetic */ POJOPropertyBuilder this$0;

            {
                this.this$0 = r5;
            }

            public Boolean withMember(AnnotatedMember annotatedMember) {
                return this.this$0._annotationIntrospector.hasRequiredMarker(annotatedMember);
            }
        };
        Boolean bool = (Boolean) fromMemberAnnotations(withMember);
        return bool != null && bool.booleanValue();
    }

    public ObjectIdInfo findObjectIdInfo() {
        WithMember withMember;
        new WithMember<ObjectIdInfo>(this) {
            final /* synthetic */ POJOPropertyBuilder this$0;

            {
                this.this$0 = r5;
            }

            public ObjectIdInfo withMember(AnnotatedMember annotatedMember) {
                AnnotatedMember annotatedMember2 = annotatedMember;
                ObjectIdInfo findObjectIdInfo = this.this$0._annotationIntrospector.findObjectIdInfo(annotatedMember2);
                if (findObjectIdInfo != null) {
                    findObjectIdInfo = this.this$0._annotationIntrospector.findObjectReferenceInfo(annotatedMember2, findObjectIdInfo);
                }
                return findObjectIdInfo;
            }
        };
        return (ObjectIdInfo) fromMemberAnnotations(withMember);
    }

    public void addField(AnnotatedField annotatedField, String str, boolean z, boolean z2) {
        Linked<AnnotatedField> linked;
        new Linked<>(annotatedField, this._fields, str, z, z2);
        this._fields = linked;
    }

    public void addCtor(AnnotatedParameter annotatedParameter, String str, boolean z, boolean z2) {
        Linked<AnnotatedParameter> linked;
        new Linked<>(annotatedParameter, this._ctorParameters, str, z, z2);
        this._ctorParameters = linked;
    }

    public void addGetter(AnnotatedMethod annotatedMethod, String str, boolean z, boolean z2) {
        Linked<AnnotatedMethod> linked;
        new Linked<>(annotatedMethod, this._getters, str, z, z2);
        this._getters = linked;
    }

    public void addSetter(AnnotatedMethod annotatedMethod, String str, boolean z, boolean z2) {
        Linked<AnnotatedMethod> linked;
        new Linked<>(annotatedMethod, this._setters, str, z, z2);
        this._setters = linked;
    }

    public void addAll(POJOPropertyBuilder pOJOPropertyBuilder) {
        POJOPropertyBuilder pOJOPropertyBuilder2 = pOJOPropertyBuilder;
        this._fields = merge(this._fields, pOJOPropertyBuilder2._fields);
        this._ctorParameters = merge(this._ctorParameters, pOJOPropertyBuilder2._ctorParameters);
        this._getters = merge(this._getters, pOJOPropertyBuilder2._getters);
        this._setters = merge(this._setters, pOJOPropertyBuilder2._setters);
    }

    private static <T> Linked<T> merge(Linked<T> linked, Linked<T> linked2) {
        Linked<T> linked3 = linked;
        Linked<T> linked4 = linked2;
        if (linked3 == null) {
            return linked4;
        }
        if (linked4 == null) {
            return linked3;
        }
        return linked3.append(linked4);
    }

    public void removeIgnored() {
        this._fields = _removeIgnored(this._fields);
        this._getters = _removeIgnored(this._getters);
        this._setters = _removeIgnored(this._setters);
        this._ctorParameters = _removeIgnored(this._ctorParameters);
    }

    @Deprecated
    public void removeNonVisible() {
        removeNonVisible(false);
    }

    public void removeNonVisible(boolean z) {
        this._getters = _removeNonVisible(this._getters);
        this._ctorParameters = _removeNonVisible(this._ctorParameters);
        if (z || this._getters == null) {
            this._fields = _removeNonVisible(this._fields);
            this._setters = _removeNonVisible(this._setters);
        }
    }

    public void trimByVisibility() {
        this._fields = _trimByVisibility(this._fields);
        this._getters = _trimByVisibility(this._getters);
        this._setters = _trimByVisibility(this._setters);
        this._ctorParameters = _trimByVisibility(this._ctorParameters);
    }

    public void mergeAnnotations(boolean z) {
        if (z) {
            if (this._getters != null) {
                Linked[] linkedArr = new Linked[4];
                linkedArr[0] = this._getters;
                Linked[] linkedArr2 = linkedArr;
                linkedArr2[1] = this._fields;
                Linked[] linkedArr3 = linkedArr2;
                linkedArr3[2] = this._ctorParameters;
                Linked[] linkedArr4 = linkedArr3;
                linkedArr4[3] = this._setters;
                this._getters = this._getters.withValue(((AnnotatedMethod) this._getters.value).withAnnotations(_mergeAnnotations(0, linkedArr4)));
            } else if (this._fields != null) {
                Linked[] linkedArr5 = new Linked[3];
                linkedArr5[0] = this._fields;
                Linked[] linkedArr6 = linkedArr5;
                linkedArr6[1] = this._ctorParameters;
                Linked[] linkedArr7 = linkedArr6;
                linkedArr7[2] = this._setters;
                this._fields = this._fields.withValue(((AnnotatedField) this._fields.value).withAnnotations(_mergeAnnotations(0, linkedArr7)));
            }
        } else if (this._ctorParameters != null) {
            Linked[] linkedArr8 = new Linked[4];
            linkedArr8[0] = this._ctorParameters;
            Linked[] linkedArr9 = linkedArr8;
            linkedArr9[1] = this._setters;
            Linked[] linkedArr10 = linkedArr9;
            linkedArr10[2] = this._fields;
            Linked[] linkedArr11 = linkedArr10;
            linkedArr11[3] = this._getters;
            this._ctorParameters = this._ctorParameters.withValue(((AnnotatedParameter) this._ctorParameters.value).withAnnotations(_mergeAnnotations(0, linkedArr11)));
        } else if (this._setters != null) {
            Linked[] linkedArr12 = new Linked[3];
            linkedArr12[0] = this._setters;
            Linked[] linkedArr13 = linkedArr12;
            linkedArr13[1] = this._fields;
            Linked[] linkedArr14 = linkedArr13;
            linkedArr14[2] = this._getters;
            this._setters = this._setters.withValue(((AnnotatedMethod) this._setters.value).withAnnotations(_mergeAnnotations(0, linkedArr14)));
        } else if (this._fields != null) {
            Linked[] linkedArr15 = new Linked[2];
            linkedArr15[0] = this._fields;
            Linked[] linkedArr16 = linkedArr15;
            linkedArr16[1] = this._getters;
            this._fields = this._fields.withValue(((AnnotatedField) this._fields.value).withAnnotations(_mergeAnnotations(0, linkedArr16)));
        }
    }

    private AnnotationMap _mergeAnnotations(int i, Linked<? extends AnnotatedMember>... linkedArr) {
        int i2 = i;
        Linked<? extends AnnotatedMember>[] linkedArr2 = linkedArr;
        AnnotationMap allAnnotations = ((AnnotatedMember) linkedArr2[i2].value).getAllAnnotations();
        while (true) {
            i2++;
            if (i2 >= linkedArr2.length) {
                return allAnnotations;
            }
            if (linkedArr2[i2] != null) {
                return AnnotationMap.merge(allAnnotations, _mergeAnnotations(i2, linkedArr2));
            }
        }
    }

    private <T> Linked<T> _removeIgnored(Linked<T> linked) {
        Linked<T> linked2 = linked;
        if (linked2 == null) {
            return linked2;
        }
        return linked2.withoutIgnored();
    }

    private <T> Linked<T> _removeNonVisible(Linked<T> linked) {
        Linked<T> linked2 = linked;
        if (linked2 == null) {
            return linked2;
        }
        return linked2.withoutNonVisible();
    }

    private <T> Linked<T> _trimByVisibility(Linked<T> linked) {
        Linked<T> linked2 = linked;
        if (linked2 == null) {
            return linked2;
        }
        return linked2.trimByVisibility();
    }

    private <T> boolean _anyExplicitNames(Linked<T> linked) {
        for (Linked<T> linked2 = linked; linked2 != null; linked2 = linked2.next) {
            if (linked2.explicitName != null && linked2.explicitName.length() > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean anyVisible() {
        return _anyVisible(this._fields) || _anyVisible(this._getters) || _anyVisible(this._setters) || _anyVisible(this._ctorParameters);
    }

    private <T> boolean _anyVisible(Linked<T> linked) {
        for (Linked<T> linked2 = linked; linked2 != null; linked2 = linked2.next) {
            if (linked2.isVisible) {
                return true;
            }
        }
        return false;
    }

    public boolean anyIgnorals() {
        return _anyIgnorals(this._fields) || _anyIgnorals(this._getters) || _anyIgnorals(this._setters) || _anyIgnorals(this._ctorParameters);
    }

    private <T> boolean _anyIgnorals(Linked<T> linked) {
        for (Linked<T> linked2 = linked; linked2 != null; linked2 = linked2.next) {
            if (linked2.isMarkedIgnored) {
                return true;
            }
        }
        return false;
    }

    public String findNewName() {
        Linked<? extends AnnotatedMember> findRenamed = findRenamed(this._ctorParameters, findRenamed(this._setters, findRenamed(this._getters, findRenamed(this._fields, (Linked<? extends AnnotatedMember>) null))));
        return findRenamed == null ? null : findRenamed.explicitName;
    }

    private Linked<? extends AnnotatedMember> findRenamed(Linked<? extends AnnotatedMember> linked, Linked<? extends AnnotatedMember> linked2) {
        Throwable th;
        StringBuilder sb;
        Linked<? extends AnnotatedMember> linked3 = linked2;
        for (Linked<T> linked4 = linked; linked4 != null; linked4 = linked4.next) {
            String str = linked4.explicitName;
            if (str != null && !str.equals(this._name)) {
                if (linked3 == null) {
                    linked3 = linked4;
                } else if (!str.equals(linked3.explicitName)) {
                    Throwable th2 = th;
                    new StringBuilder();
                    new IllegalStateException(sb.append("Conflicting property name definitions: '").append(linked3.explicitName).append("' (for ").append(linked3.value).append(") vs '").append(linked4.explicitName).append("' (for ").append(linked4.value).append(")").toString());
                    throw th2;
                }
            }
        }
        return linked3;
    }

    public String toString() {
        StringBuilder sb;
        new StringBuilder();
        StringBuilder sb2 = sb;
        StringBuilder append = sb2.append("[Property '").append(this._name).append("'; ctors: ").append(this._ctorParameters).append(", field(s): ").append(this._fields).append(", getter(s): ").append(this._getters).append(", setter(s): ").append(this._setters);
        StringBuilder append2 = sb2.append("]");
        return sb2.toString();
    }

    /* access modifiers changed from: protected */
    public <T> T fromMemberAnnotations(WithMember<T> withMember) {
        WithMember<T> withMember2 = withMember;
        T t = null;
        if (this._annotationIntrospector != null) {
            if (!this._forSerialization) {
                if (this._ctorParameters != null) {
                    t = withMember2.withMember((AnnotatedMember) this._ctorParameters.value);
                }
                if (t == null && this._setters != null) {
                    t = withMember2.withMember((AnnotatedMember) this._setters.value);
                }
            } else if (this._getters != null) {
                t = withMember2.withMember((AnnotatedMember) this._getters.value);
            }
            if (t == null && this._fields != null) {
                t = withMember2.withMember((AnnotatedMember) this._fields.value);
            }
        }
        return t;
    }

    private static final class Linked<T> {
        public final String explicitName;
        public final boolean isMarkedIgnored;
        public final boolean isVisible;
        public final Linked<T> next;
        public final T value;

        public Linked(T t, Linked<T> linked, String str, boolean z, boolean z2) {
            String str2 = str;
            boolean z3 = z;
            boolean z4 = z2;
            this.value = t;
            this.next = linked;
            if (str2 == null) {
                this.explicitName = null;
            } else {
                this.explicitName = str2.length() == 0 ? null : str2;
            }
            this.isVisible = z3;
            this.isMarkedIgnored = z4;
        }

        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.shaded.fasterxml.jackson.databind.introspect.POJOPropertyBuilder.Linked<T> withValue(T r11) {
            /*
                r10 = this;
                r0 = r10
                r1 = r11
                r2 = r1
                r3 = r0
                T r3 = r3.value
                if (r2 != r3) goto L_0x000b
                r2 = r0
                r0 = r2
            L_0x000a:
                return r0
            L_0x000b:
                com.shaded.fasterxml.jackson.databind.introspect.POJOPropertyBuilder$Linked r2 = new com.shaded.fasterxml.jackson.databind.introspect.POJOPropertyBuilder$Linked
                r9 = r2
                r2 = r9
                r3 = r9
                r4 = r1
                r5 = r0
                com.shaded.fasterxml.jackson.databind.introspect.POJOPropertyBuilder$Linked<T> r5 = r5.next
                r6 = r0
                java.lang.String r6 = r6.explicitName
                r7 = r0
                boolean r7 = r7.isVisible
                r8 = r0
                boolean r8 = r8.isMarkedIgnored
                r3.<init>(r4, r5, r6, r7, r8)
                r0 = r2
                goto L_0x000a
            */
            throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.introspect.POJOPropertyBuilder.Linked.withValue(java.lang.Object):com.shaded.fasterxml.jackson.databind.introspect.POJOPropertyBuilder$Linked");
        }

        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.shaded.fasterxml.jackson.databind.introspect.POJOPropertyBuilder.Linked<T> withNext(com.shaded.fasterxml.jackson.databind.introspect.POJOPropertyBuilder.Linked<T> r11) {
            /*
                r10 = this;
                r0 = r10
                r1 = r11
                r2 = r1
                r3 = r0
                com.shaded.fasterxml.jackson.databind.introspect.POJOPropertyBuilder$Linked<T> r3 = r3.next
                if (r2 != r3) goto L_0x000b
                r2 = r0
                r0 = r2
            L_0x000a:
                return r0
            L_0x000b:
                com.shaded.fasterxml.jackson.databind.introspect.POJOPropertyBuilder$Linked r2 = new com.shaded.fasterxml.jackson.databind.introspect.POJOPropertyBuilder$Linked
                r9 = r2
                r2 = r9
                r3 = r9
                r4 = r0
                T r4 = r4.value
                r5 = r1
                r6 = r0
                java.lang.String r6 = r6.explicitName
                r7 = r0
                boolean r7 = r7.isVisible
                r8 = r0
                boolean r8 = r8.isMarkedIgnored
                r3.<init>(r4, r5, r6, r7, r8)
                r0 = r2
                goto L_0x000a
            */
            throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.introspect.POJOPropertyBuilder.Linked.withNext(com.shaded.fasterxml.jackson.databind.introspect.POJOPropertyBuilder$Linked):com.shaded.fasterxml.jackson.databind.introspect.POJOPropertyBuilder$Linked");
        }

        public Linked<T> withoutIgnored() {
            Linked<T> withoutIgnored;
            if (this.isMarkedIgnored) {
                return this.next == null ? null : this.next.withoutIgnored();
            } else if (this.next != null && (withoutIgnored = this.next.withoutIgnored()) != this.next) {
                return withNext(withoutIgnored);
            } else {
                return this;
            }
        }

        public Linked<T> withoutNonVisible() {
            Linked<T> withoutNonVisible = this.next == null ? null : this.next.withoutNonVisible();
            return this.isVisible ? withNext(withoutNonVisible) : withoutNonVisible;
        }

        /* access modifiers changed from: private */
        public Linked<T> append(Linked<T> linked) {
            Linked<T> linked2 = linked;
            if (this.next == null) {
                return withNext(linked2);
            }
            return withNext(this.next.append(linked2));
        }

        public Linked<T> trimByVisibility() {
            if (this.next == null) {
                return this;
            }
            Linked<T> trimByVisibility = this.next.trimByVisibility();
            if (this.explicitName != null) {
                if (trimByVisibility.explicitName == null) {
                    return withNext((Linked) null);
                }
                return withNext(trimByVisibility);
            } else if (trimByVisibility.explicitName != null) {
                return trimByVisibility;
            } else {
                if (this.isVisible == trimByVisibility.isVisible) {
                    return withNext(trimByVisibility);
                }
                return this.isVisible ? withNext((Linked) null) : trimByVisibility;
            }
        }

        public String toString() {
            StringBuilder sb;
            StringBuilder sb2;
            new StringBuilder();
            String sb3 = sb.append(this.value.toString()).append("[visible=").append(this.isVisible).append("]").toString();
            if (this.next != null) {
                new StringBuilder();
                sb3 = sb2.append(sb3).append(", ").append(this.next.toString()).toString();
            }
            return sb3;
        }
    }
}

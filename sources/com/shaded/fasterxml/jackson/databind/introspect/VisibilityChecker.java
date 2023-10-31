package com.shaded.fasterxml.jackson.databind.introspect;

import com.shaded.fasterxml.jackson.annotation.JsonAutoDetect;
import com.shaded.fasterxml.jackson.annotation.PropertyAccessor;
import com.shaded.fasterxml.jackson.databind.introspect.VisibilityChecker;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

public interface VisibilityChecker<T extends VisibilityChecker<T>> {
    boolean isCreatorVisible(AnnotatedMember annotatedMember);

    boolean isCreatorVisible(Member member);

    boolean isFieldVisible(AnnotatedField annotatedField);

    boolean isFieldVisible(Field field);

    boolean isGetterVisible(AnnotatedMethod annotatedMethod);

    boolean isGetterVisible(Method method);

    boolean isIsGetterVisible(AnnotatedMethod annotatedMethod);

    boolean isIsGetterVisible(Method method);

    boolean isSetterVisible(AnnotatedMethod annotatedMethod);

    boolean isSetterVisible(Method method);

    T with(JsonAutoDetect.Visibility visibility);

    T with(JsonAutoDetect jsonAutoDetect);

    T withCreatorVisibility(JsonAutoDetect.Visibility visibility);

    T withFieldVisibility(JsonAutoDetect.Visibility visibility);

    T withGetterVisibility(JsonAutoDetect.Visibility visibility);

    T withIsGetterVisibility(JsonAutoDetect.Visibility visibility);

    T withSetterVisibility(JsonAutoDetect.Visibility visibility);

    T withVisibility(PropertyAccessor propertyAccessor, JsonAutoDetect.Visibility visibility);

    @JsonAutoDetect(creatorVisibility = JsonAutoDetect.Visibility.ANY, fieldVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY, getterVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY, isGetterVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY, setterVisibility = JsonAutoDetect.Visibility.ANY)
    public static class Std implements VisibilityChecker<Std>, Serializable {
        protected static final Std DEFAULT;
        private static final long serialVersionUID = -7073939237187922755L;
        protected final JsonAutoDetect.Visibility _creatorMinLevel;
        protected final JsonAutoDetect.Visibility _fieldMinLevel;
        protected final JsonAutoDetect.Visibility _getterMinLevel;
        protected final JsonAutoDetect.Visibility _isGetterMinLevel;
        protected final JsonAutoDetect.Visibility _setterMinLevel;

        static {
            Std std;
            new Std((JsonAutoDetect) Std.class.getAnnotation(JsonAutoDetect.class));
            DEFAULT = std;
        }

        public static Std defaultInstance() {
            return DEFAULT;
        }

        public Std(JsonAutoDetect jsonAutoDetect) {
            JsonAutoDetect jsonAutoDetect2 = jsonAutoDetect;
            this._getterMinLevel = jsonAutoDetect2.getterVisibility();
            this._isGetterMinLevel = jsonAutoDetect2.isGetterVisibility();
            this._setterMinLevel = jsonAutoDetect2.setterVisibility();
            this._creatorMinLevel = jsonAutoDetect2.creatorVisibility();
            this._fieldMinLevel = jsonAutoDetect2.fieldVisibility();
        }

        public Std(JsonAutoDetect.Visibility visibility, JsonAutoDetect.Visibility visibility2, JsonAutoDetect.Visibility visibility3, JsonAutoDetect.Visibility visibility4, JsonAutoDetect.Visibility visibility5) {
            this._getterMinLevel = visibility;
            this._isGetterMinLevel = visibility2;
            this._setterMinLevel = visibility3;
            this._creatorMinLevel = visibility4;
            this._fieldMinLevel = visibility5;
        }

        public Std(JsonAutoDetect.Visibility visibility) {
            JsonAutoDetect.Visibility visibility2 = visibility;
            if (visibility2 == JsonAutoDetect.Visibility.DEFAULT) {
                this._getterMinLevel = DEFAULT._getterMinLevel;
                this._isGetterMinLevel = DEFAULT._isGetterMinLevel;
                this._setterMinLevel = DEFAULT._setterMinLevel;
                this._creatorMinLevel = DEFAULT._creatorMinLevel;
                this._fieldMinLevel = DEFAULT._fieldMinLevel;
                return;
            }
            this._getterMinLevel = visibility2;
            this._isGetterMinLevel = visibility2;
            this._setterMinLevel = visibility2;
            this._creatorMinLevel = visibility2;
            this._fieldMinLevel = visibility2;
        }

        public Std with(JsonAutoDetect jsonAutoDetect) {
            JsonAutoDetect jsonAutoDetect2 = jsonAutoDetect;
            Std std = this;
            if (jsonAutoDetect2 != null) {
                std = std.withGetterVisibility(jsonAutoDetect2.getterVisibility()).withIsGetterVisibility(jsonAutoDetect2.isGetterVisibility()).withSetterVisibility(jsonAutoDetect2.setterVisibility()).withCreatorVisibility(jsonAutoDetect2.creatorVisibility()).withFieldVisibility(jsonAutoDetect2.fieldVisibility());
            }
            return std;
        }

        public Std with(JsonAutoDetect.Visibility visibility) {
            Std std;
            JsonAutoDetect.Visibility visibility2 = visibility;
            if (visibility2 == JsonAutoDetect.Visibility.DEFAULT) {
                return DEFAULT;
            }
            new Std(visibility2);
            return std;
        }

        public Std withVisibility(PropertyAccessor propertyAccessor, JsonAutoDetect.Visibility visibility) {
            JsonAutoDetect.Visibility visibility2 = visibility;
            switch (propertyAccessor) {
                case GETTER:
                    return withGetterVisibility(visibility2);
                case SETTER:
                    return withSetterVisibility(visibility2);
                case CREATOR:
                    return withCreatorVisibility(visibility2);
                case FIELD:
                    return withFieldVisibility(visibility2);
                case IS_GETTER:
                    return withIsGetterVisibility(visibility2);
                case ALL:
                    return with(visibility2);
                default:
                    return this;
            }
        }

        public Std withGetterVisibility(JsonAutoDetect.Visibility visibility) {
            Std std;
            JsonAutoDetect.Visibility visibility2 = visibility;
            if (visibility2 == JsonAutoDetect.Visibility.DEFAULT) {
                visibility2 = DEFAULT._getterMinLevel;
            }
            if (this._getterMinLevel == visibility2) {
                return this;
            }
            new Std(visibility2, this._isGetterMinLevel, this._setterMinLevel, this._creatorMinLevel, this._fieldMinLevel);
            return std;
        }

        public Std withIsGetterVisibility(JsonAutoDetect.Visibility visibility) {
            Std std;
            JsonAutoDetect.Visibility visibility2 = visibility;
            if (visibility2 == JsonAutoDetect.Visibility.DEFAULT) {
                visibility2 = DEFAULT._isGetterMinLevel;
            }
            if (this._isGetterMinLevel == visibility2) {
                return this;
            }
            new Std(this._getterMinLevel, visibility2, this._setterMinLevel, this._creatorMinLevel, this._fieldMinLevel);
            return std;
        }

        public Std withSetterVisibility(JsonAutoDetect.Visibility visibility) {
            Std std;
            JsonAutoDetect.Visibility visibility2 = visibility;
            if (visibility2 == JsonAutoDetect.Visibility.DEFAULT) {
                visibility2 = DEFAULT._setterMinLevel;
            }
            if (this._setterMinLevel == visibility2) {
                return this;
            }
            new Std(this._getterMinLevel, this._isGetterMinLevel, visibility2, this._creatorMinLevel, this._fieldMinLevel);
            return std;
        }

        public Std withCreatorVisibility(JsonAutoDetect.Visibility visibility) {
            Std std;
            JsonAutoDetect.Visibility visibility2 = visibility;
            if (visibility2 == JsonAutoDetect.Visibility.DEFAULT) {
                visibility2 = DEFAULT._creatorMinLevel;
            }
            if (this._creatorMinLevel == visibility2) {
                return this;
            }
            new Std(this._getterMinLevel, this._isGetterMinLevel, this._setterMinLevel, visibility2, this._fieldMinLevel);
            return std;
        }

        public Std withFieldVisibility(JsonAutoDetect.Visibility visibility) {
            Std std;
            JsonAutoDetect.Visibility visibility2 = visibility;
            if (visibility2 == JsonAutoDetect.Visibility.DEFAULT) {
                visibility2 = DEFAULT._fieldMinLevel;
            }
            if (this._fieldMinLevel == visibility2) {
                return this;
            }
            new Std(this._getterMinLevel, this._isGetterMinLevel, this._setterMinLevel, this._creatorMinLevel, visibility2);
            return std;
        }

        public boolean isCreatorVisible(Member member) {
            return this._creatorMinLevel.isVisible(member);
        }

        public boolean isCreatorVisible(AnnotatedMember annotatedMember) {
            return isCreatorVisible(annotatedMember.getMember());
        }

        public boolean isFieldVisible(Field field) {
            return this._fieldMinLevel.isVisible(field);
        }

        public boolean isFieldVisible(AnnotatedField annotatedField) {
            return isFieldVisible(annotatedField.getAnnotated());
        }

        public boolean isGetterVisible(Method method) {
            return this._getterMinLevel.isVisible(method);
        }

        public boolean isGetterVisible(AnnotatedMethod annotatedMethod) {
            return isGetterVisible(annotatedMethod.getAnnotated());
        }

        public boolean isIsGetterVisible(Method method) {
            return this._isGetterMinLevel.isVisible(method);
        }

        public boolean isIsGetterVisible(AnnotatedMethod annotatedMethod) {
            return isIsGetterVisible(annotatedMethod.getAnnotated());
        }

        public boolean isSetterVisible(Method method) {
            return this._setterMinLevel.isVisible(method);
        }

        public boolean isSetterVisible(AnnotatedMethod annotatedMethod) {
            return isSetterVisible(annotatedMethod.getAnnotated());
        }

        public String toString() {
            StringBuilder sb;
            new StringBuilder("[Visibility:");
            return sb.append(" getter: ").append(this._getterMinLevel).append(", isGetter: ").append(this._isGetterMinLevel).append(", setter: ").append(this._setterMinLevel).append(", creator: ").append(this._creatorMinLevel).append(", field: ").append(this._fieldMinLevel).append("]").toString();
        }
    }
}

package com.shaded.fasterxml.jackson.databind;

import com.shaded.fasterxml.jackson.databind.cfg.MapperConfig;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import java.io.Serializable;

public abstract class PropertyNamingStrategy implements Serializable {
    public static final PropertyNamingStrategy CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES;
    public static final PropertyNamingStrategy PASCAL_CASE_TO_CAMEL_CASE;

    public PropertyNamingStrategy() {
    }

    public String nameForField(MapperConfig<?> mapperConfig, AnnotatedField annotatedField, String str) {
        MapperConfig<?> mapperConfig2 = mapperConfig;
        AnnotatedField annotatedField2 = annotatedField;
        return str;
    }

    public String nameForGetterMethod(MapperConfig<?> mapperConfig, AnnotatedMethod annotatedMethod, String str) {
        MapperConfig<?> mapperConfig2 = mapperConfig;
        AnnotatedMethod annotatedMethod2 = annotatedMethod;
        return str;
    }

    public String nameForSetterMethod(MapperConfig<?> mapperConfig, AnnotatedMethod annotatedMethod, String str) {
        MapperConfig<?> mapperConfig2 = mapperConfig;
        AnnotatedMethod annotatedMethod2 = annotatedMethod;
        return str;
    }

    public String nameForConstructorParameter(MapperConfig<?> mapperConfig, AnnotatedParameter annotatedParameter, String str) {
        MapperConfig<?> mapperConfig2 = mapperConfig;
        AnnotatedParameter annotatedParameter2 = annotatedParameter;
        return str;
    }

    public static abstract class PropertyNamingStrategyBase extends PropertyNamingStrategy {
        public abstract String translate(String str);

        public PropertyNamingStrategyBase() {
        }

        public String nameForField(MapperConfig<?> mapperConfig, AnnotatedField annotatedField, String str) {
            MapperConfig<?> mapperConfig2 = mapperConfig;
            AnnotatedField annotatedField2 = annotatedField;
            return translate(str);
        }

        public String nameForGetterMethod(MapperConfig<?> mapperConfig, AnnotatedMethod annotatedMethod, String str) {
            MapperConfig<?> mapperConfig2 = mapperConfig;
            AnnotatedMethod annotatedMethod2 = annotatedMethod;
            return translate(str);
        }

        public String nameForSetterMethod(MapperConfig<?> mapperConfig, AnnotatedMethod annotatedMethod, String str) {
            MapperConfig<?> mapperConfig2 = mapperConfig;
            AnnotatedMethod annotatedMethod2 = annotatedMethod;
            return translate(str);
        }

        public String nameForConstructorParameter(MapperConfig<?> mapperConfig, AnnotatedParameter annotatedParameter, String str) {
            MapperConfig<?> mapperConfig2 = mapperConfig;
            AnnotatedParameter annotatedParameter2 = annotatedParameter;
            return translate(str);
        }
    }

    static {
        PropertyNamingStrategy propertyNamingStrategy;
        PropertyNamingStrategy propertyNamingStrategy2;
        new LowerCaseWithUnderscoresStrategy();
        CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES = propertyNamingStrategy;
        new PascalCaseStrategy();
        PASCAL_CASE_TO_CAMEL_CASE = propertyNamingStrategy2;
    }

    public static class LowerCaseWithUnderscoresStrategy extends PropertyNamingStrategyBase {
        public LowerCaseWithUnderscoresStrategy() {
        }

        public String translate(String str) {
            StringBuilder sb;
            String str2 = str;
            if (str2 == null) {
                return str2;
            }
            int length = str2.length();
            new StringBuilder(length * 2);
            StringBuilder sb2 = sb;
            int i = 0;
            boolean z = false;
            for (int i2 = 0; i2 < length; i2++) {
                char charAt = str2.charAt(i2);
                if (i2 > 0 || charAt != '_') {
                    if (Character.isUpperCase(charAt)) {
                        if (!z && i > 0 && sb2.charAt(i - 1) != '_') {
                            StringBuilder append = sb2.append('_');
                            i++;
                        }
                        charAt = Character.toLowerCase(charAt);
                        z = true;
                    } else {
                        z = false;
                    }
                    StringBuilder append2 = sb2.append(charAt);
                    i++;
                }
            }
            return i > 0 ? sb2.toString() : str2;
        }
    }

    public static class PascalCaseStrategy extends PropertyNamingStrategyBase {
        public PascalCaseStrategy() {
        }

        public String translate(String str) {
            StringBuilder sb;
            String str2 = str;
            if (str2 == null || str2.length() == 0) {
                return str2;
            }
            char charAt = str2.charAt(0);
            if (Character.isUpperCase(charAt)) {
                return str2;
            }
            new StringBuilder(str2);
            StringBuilder sb2 = sb;
            sb2.setCharAt(0, Character.toUpperCase(charAt));
            return sb2.toString();
        }
    }
}

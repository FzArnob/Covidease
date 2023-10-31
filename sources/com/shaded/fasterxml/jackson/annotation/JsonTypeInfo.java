package com.shaded.fasterxml.jackson.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@JacksonAnnotation
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonTypeInfo {

    /* renamed from: com.shaded.fasterxml.jackson.annotation.JsonTypeInfo$As */
    public enum C1433As {
    }

    Class<?> defaultImpl() default None.class;

    C1433As include() default C1433As.PROPERTY;

    String property() default "";

    C1434Id use();

    boolean visible() default false;

    /* renamed from: com.shaded.fasterxml.jackson.annotation.JsonTypeInfo$Id */
    public enum C1434Id {
        ;
        
        private final String _defaultPropertyName;

        private C1434Id(String str) {
            String str2 = r8;
            int i = r9;
            this._defaultPropertyName = str;
        }

        public String getDefaultPropertyName() {
            return this._defaultPropertyName;
        }
    }

    public static abstract class None {
        public None() {
        }
    }
}

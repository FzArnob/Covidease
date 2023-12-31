package com.google.appinventor.components.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DesignerProperty {
    String defaultValue() default "";

    String[] editorArgs() default {};

    String editorType() default "text";

    String propertyType() default "common";
}

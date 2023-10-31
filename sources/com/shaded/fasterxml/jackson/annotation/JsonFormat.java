package com.shaded.fasterxml.jackson.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Locale;
import java.util.TimeZone;

@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE})
@JacksonAnnotation
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonFormat {
    public static final String DEFAULT_LOCALE = "##default";
    public static final String DEFAULT_TIMEZONE = "##default";

    String locale() default "##default";

    String pattern() default "";

    Shape shape() default Shape.ANY;

    String timezone() default "##default";

    public enum Shape {
        ;

        public boolean isNumeric() {
            return this == NUMBER || this == NUMBER_INT || this == NUMBER_FLOAT;
        }

        public boolean isStructured() {
            return this == OBJECT || this == ARRAY;
        }
    }

    public static class Value {
        private final Locale locale;
        private final String pattern;
        private final Shape shape;
        private final TimeZone timezone;

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public Value() {
            this("", Shape.ANY, "", "");
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public Value(com.shaded.fasterxml.jackson.annotation.JsonFormat r8) {
            /*
                r7 = this;
                r0 = r7
                r1 = r8
                r2 = r0
                r3 = r1
                java.lang.String r3 = r3.pattern()
                r4 = r1
                com.shaded.fasterxml.jackson.annotation.JsonFormat$Shape r4 = r4.shape()
                r5 = r1
                java.lang.String r5 = r5.locale()
                r6 = r1
                java.lang.String r6 = r6.timezone()
                r2.<init>((java.lang.String) r3, (com.shaded.fasterxml.jackson.annotation.JsonFormat.Shape) r4, (java.lang.String) r5, (java.lang.String) r6)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.annotation.JsonFormat.Value.<init>(com.shaded.fasterxml.jackson.annotation.JsonFormat):void");
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public Value(java.lang.String r13, com.shaded.fasterxml.jackson.annotation.JsonFormat.Shape r14, java.lang.String r15, java.lang.String r16) {
            /*
                r12 = this;
                r0 = r12
                r1 = r13
                r2 = r14
                r3 = r15
                r4 = r16
                r5 = r0
                r6 = r1
                r7 = r2
                r8 = r3
                if (r8 == 0) goto L_0x001d
                r8 = r3
                int r8 = r8.length()
                if (r8 == 0) goto L_0x001d
                java.lang.String r8 = "##default"
                r9 = r3
                boolean r8 = r8.equals(r9)
                if (r8 == 0) goto L_0x0037
            L_0x001d:
                r8 = 0
            L_0x001e:
                r9 = r4
                if (r9 == 0) goto L_0x0032
                r9 = r4
                int r9 = r9.length()
                if (r9 == 0) goto L_0x0032
                java.lang.String r9 = "##default"
                r10 = r4
                boolean r9 = r9.equals(r10)
                if (r9 == 0) goto L_0x0041
            L_0x0032:
                r9 = 0
            L_0x0033:
                r5.<init>((java.lang.String) r6, (com.shaded.fasterxml.jackson.annotation.JsonFormat.Shape) r7, (java.util.Locale) r8, (java.util.TimeZone) r9)
                return
            L_0x0037:
                java.util.Locale r8 = new java.util.Locale
                r11 = r8
                r8 = r11
                r9 = r11
                r10 = r3
                r9.<init>(r10)
                goto L_0x001e
            L_0x0041:
                r9 = r4
                java.util.TimeZone r9 = java.util.TimeZone.getTimeZone(r9)
                goto L_0x0033
            */
            throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.annotation.JsonFormat.Value.<init>(java.lang.String, com.shaded.fasterxml.jackson.annotation.JsonFormat$Shape, java.lang.String, java.lang.String):void");
        }

        public Value(String str, Shape shape2, Locale locale2, TimeZone timeZone) {
            this.pattern = str;
            this.shape = shape2;
            this.locale = locale2;
            this.timezone = timeZone;
        }

        public Value withPattern(String str) {
            Value value;
            new Value(str, this.shape, this.locale, this.timezone);
            return value;
        }

        public Value withShape(Shape shape2) {
            Value value;
            new Value(this.pattern, shape2, this.locale, this.timezone);
            return value;
        }

        public Value withLocale(Locale locale2) {
            Value value;
            new Value(this.pattern, this.shape, locale2, this.timezone);
            return value;
        }

        public Value withTimeZone(TimeZone timeZone) {
            Value value;
            new Value(this.pattern, this.shape, this.locale, timeZone);
            return value;
        }

        public String getPattern() {
            return this.pattern;
        }

        public Shape getShape() {
            return this.shape;
        }

        public Locale getLocale() {
            return this.locale;
        }

        public TimeZone getTimeZone() {
            return this.timezone;
        }
    }
}

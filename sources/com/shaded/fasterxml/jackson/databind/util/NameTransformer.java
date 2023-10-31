package com.shaded.fasterxml.jackson.databind.util;

public abstract class NameTransformer {
    public static final NameTransformer NOP;

    public abstract String reverse(String str);

    public abstract String transform(String str);

    static {
        NameTransformer nameTransformer;
        new NameTransformer() {
            public String transform(String str) {
                return str;
            }

            public String reverse(String str) {
                return str;
            }
        };
        NOP = nameTransformer;
    }

    protected NameTransformer() {
    }

    public static NameTransformer simpleTransformer(String str, String str2) {
        NameTransformer nameTransformer;
        NameTransformer nameTransformer2;
        NameTransformer nameTransformer3;
        String str3 = str;
        String str4 = str2;
        boolean z = str3 != null && str3.length() > 0;
        boolean z2 = str4 != null && str4.length() > 0;
        if (z) {
            if (z2) {
                final String str5 = str3;
                final String str6 = str4;
                new NameTransformer() {
                    public String transform(String str) {
                        StringBuilder sb;
                        new StringBuilder();
                        return sb.append(str5).append(str).append(str6).toString();
                    }

                    public String reverse(String str) {
                        String str2 = str;
                        if (str2.startsWith(str5)) {
                            String substring = str2.substring(str5.length());
                            if (substring.endsWith(str6)) {
                                return substring.substring(0, substring.length() - str6.length());
                            }
                        }
                        return null;
                    }

                    public String toString() {
                        StringBuilder sb;
                        new StringBuilder();
                        return sb.append("[PreAndSuffixTransformer('").append(str5).append("','").append(str6).append("')]").toString();
                    }
                };
                return nameTransformer3;
            }
            final String str7 = str3;
            new NameTransformer() {
                public String transform(String str) {
                    StringBuilder sb;
                    new StringBuilder();
                    return sb.append(str7).append(str).toString();
                }

                public String reverse(String str) {
                    String str2 = str;
                    if (str2.startsWith(str7)) {
                        return str2.substring(str7.length());
                    }
                    return null;
                }

                public String toString() {
                    StringBuilder sb;
                    new StringBuilder();
                    return sb.append("[PrefixTransformer('").append(str7).append("')]").toString();
                }
            };
            return nameTransformer2;
        } else if (!z2) {
            return NOP;
        } else {
            final String str8 = str4;
            new NameTransformer() {
                public String transform(String str) {
                    StringBuilder sb;
                    new StringBuilder();
                    return sb.append(str).append(str8).toString();
                }

                public String reverse(String str) {
                    String str2 = str;
                    if (str2.endsWith(str8)) {
                        return str2.substring(0, str2.length() - str8.length());
                    }
                    return null;
                }

                public String toString() {
                    StringBuilder sb;
                    new StringBuilder();
                    return sb.append("[SuffixTransformer('").append(str8).append("')]").toString();
                }
            };
            return nameTransformer;
        }
    }

    public static NameTransformer chainedTransformer(NameTransformer nameTransformer, NameTransformer nameTransformer2) {
        NameTransformer nameTransformer3;
        new Chained(nameTransformer, nameTransformer2);
        return nameTransformer3;
    }

    public static class Chained extends NameTransformer {
        protected final NameTransformer _t1;
        protected final NameTransformer _t2;

        public Chained(NameTransformer nameTransformer, NameTransformer nameTransformer2) {
            this._t1 = nameTransformer;
            this._t2 = nameTransformer2;
        }

        public String transform(String str) {
            return this._t1.transform(this._t2.transform(str));
        }

        public String reverse(String str) {
            String reverse = this._t1.reverse(str);
            if (reverse != null) {
                reverse = this._t2.reverse(reverse);
            }
            return reverse;
        }

        public String toString() {
            StringBuilder sb;
            new StringBuilder();
            return sb.append("[ChainedTransformer(").append(this._t1).append(", ").append(this._t2).append(")]").toString();
        }
    }
}

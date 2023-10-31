package android.support.p000v4.text;

import java.nio.CharBuffer;
import java.util.Locale;

/* renamed from: android.support.v4.text.TextDirectionHeuristicsCompat */
public final class TextDirectionHeuristicsCompat {
    public static final TextDirectionHeuristicCompat ANYRTL_LTR;
    public static final TextDirectionHeuristicCompat FIRSTSTRONG_LTR;
    public static final TextDirectionHeuristicCompat FIRSTSTRONG_RTL;
    public static final TextDirectionHeuristicCompat LOCALE = TextDirectionHeuristicLocale.INSTANCE;
    public static final TextDirectionHeuristicCompat LTR;
    public static final TextDirectionHeuristicCompat RTL;
    private static final int STATE_FALSE = 1;
    private static final int STATE_TRUE = 0;
    private static final int STATE_UNKNOWN = 2;

    /* renamed from: android.support.v4.text.TextDirectionHeuristicsCompat$TextDirectionAlgorithm */
    private interface TextDirectionAlgorithm {
        int checkRtl(CharSequence charSequence, int i, int i2);
    }

    static {
        TextDirectionHeuristicCompat textDirectionHeuristicCompat;
        TextDirectionHeuristicCompat textDirectionHeuristicCompat2;
        TextDirectionHeuristicCompat textDirectionHeuristicCompat3;
        TextDirectionHeuristicCompat textDirectionHeuristicCompat4;
        TextDirectionHeuristicCompat textDirectionHeuristicCompat5;
        new TextDirectionHeuristicInternal((TextDirectionAlgorithm) null, false);
        LTR = textDirectionHeuristicCompat;
        new TextDirectionHeuristicInternal((TextDirectionAlgorithm) null, true);
        RTL = textDirectionHeuristicCompat2;
        new TextDirectionHeuristicInternal(FirstStrong.INSTANCE, false);
        FIRSTSTRONG_LTR = textDirectionHeuristicCompat3;
        new TextDirectionHeuristicInternal(FirstStrong.INSTANCE, true);
        FIRSTSTRONG_RTL = textDirectionHeuristicCompat4;
        new TextDirectionHeuristicInternal(AnyStrong.INSTANCE_RTL, false);
        ANYRTL_LTR = textDirectionHeuristicCompat5;
    }

    static int isRtlText(int directionality) {
        switch (directionality) {
            case 0:
                return 1;
            case 1:
            case 2:
                return 0;
            default:
                return 2;
        }
    }

    static int isRtlTextOrFormat(int directionality) {
        switch (directionality) {
            case 0:
            case 14:
            case 15:
                return 1;
            case 1:
            case 2:
            case 16:
            case 17:
                return 0;
            default:
                return 2;
        }
    }

    /* renamed from: android.support.v4.text.TextDirectionHeuristicsCompat$TextDirectionHeuristicImpl */
    private static abstract class TextDirectionHeuristicImpl implements TextDirectionHeuristicCompat {
        private final TextDirectionAlgorithm mAlgorithm;

        /* access modifiers changed from: protected */
        public abstract boolean defaultIsRtl();

        TextDirectionHeuristicImpl(TextDirectionAlgorithm algorithm) {
            this.mAlgorithm = algorithm;
        }

        public boolean isRtl(char[] array, int start, int count) {
            return isRtl((CharSequence) CharBuffer.wrap(array), start, count);
        }

        public boolean isRtl(CharSequence charSequence, int i, int i2) {
            Throwable th;
            CharSequence cs = charSequence;
            int start = i;
            int count = i2;
            if (cs == null || start < 0 || count < 0 || cs.length() - count < start) {
                Throwable th2 = th;
                new IllegalArgumentException();
                throw th2;
            } else if (this.mAlgorithm == null) {
                return defaultIsRtl();
            } else {
                return doCheck(cs, start, count);
            }
        }

        private boolean doCheck(CharSequence cs, int start, int count) {
            switch (this.mAlgorithm.checkRtl(cs, start, count)) {
                case 0:
                    return true;
                case 1:
                    return false;
                default:
                    return defaultIsRtl();
            }
        }
    }

    /* renamed from: android.support.v4.text.TextDirectionHeuristicsCompat$TextDirectionHeuristicInternal */
    private static class TextDirectionHeuristicInternal extends TextDirectionHeuristicImpl {
        private final boolean mDefaultIsRtl;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        TextDirectionHeuristicInternal(TextDirectionAlgorithm algorithm, boolean defaultIsRtl) {
            super(algorithm);
            this.mDefaultIsRtl = defaultIsRtl;
        }

        /* access modifiers changed from: protected */
        public boolean defaultIsRtl() {
            return this.mDefaultIsRtl;
        }
    }

    /* renamed from: android.support.v4.text.TextDirectionHeuristicsCompat$FirstStrong */
    private static class FirstStrong implements TextDirectionAlgorithm {
        static final FirstStrong INSTANCE;

        public int checkRtl(CharSequence charSequence, int i, int count) {
            CharSequence cs = charSequence;
            int start = i;
            int result = 2;
            int e = start + count;
            for (int i2 = start; i2 < e && result == 2; i2++) {
                result = TextDirectionHeuristicsCompat.isRtlTextOrFormat(Character.getDirectionality(cs.charAt(i2)));
            }
            return result;
        }

        private FirstStrong() {
        }

        static {
            FirstStrong firstStrong;
            new FirstStrong();
            INSTANCE = firstStrong;
        }
    }

    /* renamed from: android.support.v4.text.TextDirectionHeuristicsCompat$AnyStrong */
    private static class AnyStrong implements TextDirectionAlgorithm {
        static final AnyStrong INSTANCE_LTR;
        static final AnyStrong INSTANCE_RTL;
        private final boolean mLookForRtl;

        public int checkRtl(CharSequence charSequence, int i, int count) {
            CharSequence cs = charSequence;
            int start = i;
            boolean haveUnlookedFor = false;
            int e = start + count;
            for (int i2 = start; i2 < e; i2++) {
                switch (TextDirectionHeuristicsCompat.isRtlText(Character.getDirectionality(cs.charAt(i2)))) {
                    case 0:
                        if (!this.mLookForRtl) {
                            haveUnlookedFor = true;
                            break;
                        } else {
                            return 0;
                        }
                    case 1:
                        if (this.mLookForRtl) {
                            haveUnlookedFor = true;
                            break;
                        } else {
                            return 1;
                        }
                }
            }
            if (!haveUnlookedFor) {
                return 2;
            }
            return this.mLookForRtl ? 1 : 0;
        }

        private AnyStrong(boolean lookForRtl) {
            this.mLookForRtl = lookForRtl;
        }

        static {
            AnyStrong anyStrong;
            AnyStrong anyStrong2;
            new AnyStrong(true);
            INSTANCE_RTL = anyStrong;
            new AnyStrong(false);
            INSTANCE_LTR = anyStrong2;
        }
    }

    /* renamed from: android.support.v4.text.TextDirectionHeuristicsCompat$TextDirectionHeuristicLocale */
    private static class TextDirectionHeuristicLocale extends TextDirectionHeuristicImpl {
        static final TextDirectionHeuristicLocale INSTANCE;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        TextDirectionHeuristicLocale() {
            super((TextDirectionAlgorithm) null);
        }

        /* access modifiers changed from: protected */
        public boolean defaultIsRtl() {
            return TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == 1;
        }

        static {
            TextDirectionHeuristicLocale textDirectionHeuristicLocale;
            new TextDirectionHeuristicLocale();
            INSTANCE = textDirectionHeuristicLocale;
        }
    }

    private TextDirectionHeuristicsCompat() {
    }
}

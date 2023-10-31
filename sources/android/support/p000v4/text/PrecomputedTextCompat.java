package android.support.p000v4.text;

import android.os.Build;
import android.support.annotation.GuardedBy;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.p000v4.p002os.TraceCompat;
import android.support.p000v4.util.ObjectsCompat;
import android.support.p000v4.util.Preconditions;
import android.text.Layout;
import android.text.PrecomputedText;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.MetricAffectingSpan;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;

/* renamed from: android.support.v4.text.PrecomputedTextCompat */
public class PrecomputedTextCompat implements Spannable {
    private static final char LINE_FEED = '\n';
    @GuardedBy("sLock")
    @NonNull
    private static Executor sExecutor = null;
    private static final Object sLock;
    @NonNull
    private final int[] mParagraphEnds;
    @NonNull
    private final Params mParams;
    @NonNull
    private final Spannable mText;
    @Nullable
    private final PrecomputedText mWrapped;

    static {
        Object obj;
        new Object();
        sLock = obj;
    }

    /* renamed from: android.support.v4.text.PrecomputedTextCompat$Params */
    public static final class Params {
        private final int mBreakStrategy;
        private final int mHyphenationFrequency;
        @NonNull
        private final TextPaint mPaint;
        @Nullable
        private final TextDirectionHeuristic mTextDir;
        final PrecomputedText.Params mWrapped;

        /* renamed from: android.support.v4.text.PrecomputedTextCompat$Params$Builder */
        public static class Builder {
            private int mBreakStrategy;
            private int mHyphenationFrequency;
            @NonNull
            private final TextPaint mPaint;
            private TextDirectionHeuristic mTextDir;

            public Builder(@NonNull TextPaint paint) {
                this.mPaint = paint;
                if (Build.VERSION.SDK_INT >= 23) {
                    this.mBreakStrategy = 1;
                    this.mHyphenationFrequency = 1;
                } else {
                    this.mHyphenationFrequency = 0;
                    this.mBreakStrategy = 0;
                }
                if (Build.VERSION.SDK_INT >= 18) {
                    this.mTextDir = TextDirectionHeuristics.FIRSTSTRONG_LTR;
                    return;
                }
                this.mTextDir = null;
            }

            @RequiresApi(23)
            public Builder setBreakStrategy(int strategy) {
                this.mBreakStrategy = strategy;
                return this;
            }

            @RequiresApi(23)
            public Builder setHyphenationFrequency(int frequency) {
                this.mHyphenationFrequency = frequency;
                return this;
            }

            @RequiresApi(18)
            public Builder setTextDirection(@NonNull TextDirectionHeuristic textDir) {
                this.mTextDir = textDir;
                return this;
            }

            @NonNull
            public Params build() {
                Params params;
                new Params(this.mPaint, this.mTextDir, this.mBreakStrategy, this.mHyphenationFrequency);
                return params;
            }
        }

        Params(@NonNull TextPaint textPaint, @NonNull TextDirectionHeuristic textDirectionHeuristic, int i, int i2) {
            PrecomputedText.Params.Builder builder;
            TextPaint paint = textPaint;
            TextDirectionHeuristic textDir = textDirectionHeuristic;
            int strategy = i;
            int frequency = i2;
            if (Build.VERSION.SDK_INT >= 28) {
                new PrecomputedText.Params.Builder(paint);
                this.mWrapped = builder.setBreakStrategy(strategy).setHyphenationFrequency(frequency).setTextDirection(textDir).build();
            } else {
                this.mWrapped = null;
            }
            this.mPaint = paint;
            this.mTextDir = textDir;
            this.mBreakStrategy = strategy;
            this.mHyphenationFrequency = frequency;
        }

        @RequiresApi(28)
        public Params(@NonNull PrecomputedText.Params params) {
            PrecomputedText.Params wrapped = params;
            this.mPaint = wrapped.getTextPaint();
            this.mTextDir = wrapped.getTextDirection();
            this.mBreakStrategy = wrapped.getBreakStrategy();
            this.mHyphenationFrequency = wrapped.getHyphenationFrequency();
            this.mWrapped = wrapped;
        }

        @NonNull
        public TextPaint getTextPaint() {
            return this.mPaint;
        }

        @Nullable
        @RequiresApi(18)
        public TextDirectionHeuristic getTextDirection() {
            return this.mTextDir;
        }

        @RequiresApi(23)
        public int getBreakStrategy() {
            return this.mBreakStrategy;
        }

        @RequiresApi(23)
        public int getHyphenationFrequency() {
            return this.mHyphenationFrequency;
        }

        public boolean equals(@Nullable Object obj) {
            Object o = obj;
            if (o == this) {
                return true;
            }
            if (o == null || !(o instanceof Params)) {
                return false;
            }
            Params other = (Params) o;
            if (this.mWrapped != null) {
                return this.mWrapped.equals(other.mWrapped);
            }
            if (Build.VERSION.SDK_INT >= 23) {
                if (this.mBreakStrategy != other.getBreakStrategy()) {
                    return false;
                }
                if (this.mHyphenationFrequency != other.getHyphenationFrequency()) {
                    return false;
                }
            }
            if (Build.VERSION.SDK_INT >= 18 && this.mTextDir != other.getTextDirection()) {
                return false;
            }
            if (this.mPaint.getTextSize() != other.getTextPaint().getTextSize()) {
                return false;
            }
            if (this.mPaint.getTextScaleX() != other.getTextPaint().getTextScaleX()) {
                return false;
            }
            if (this.mPaint.getTextSkewX() != other.getTextPaint().getTextSkewX()) {
                return false;
            }
            if (Build.VERSION.SDK_INT >= 21) {
                if (this.mPaint.getLetterSpacing() != other.getTextPaint().getLetterSpacing()) {
                    return false;
                }
                if (!TextUtils.equals(this.mPaint.getFontFeatureSettings(), other.getTextPaint().getFontFeatureSettings())) {
                    return false;
                }
            }
            if (this.mPaint.getFlags() != other.getTextPaint().getFlags()) {
                return false;
            }
            if (Build.VERSION.SDK_INT >= 24) {
                if (!this.mPaint.getTextLocales().equals(other.getTextPaint().getTextLocales())) {
                    return false;
                }
            } else if (Build.VERSION.SDK_INT >= 17 && !this.mPaint.getTextLocale().equals(other.getTextPaint().getTextLocale())) {
                return false;
            }
            if (this.mPaint.getTypeface() == null) {
                if (other.getTextPaint().getTypeface() != null) {
                    return false;
                }
            } else if (!this.mPaint.getTypeface().equals(other.getTextPaint().getTypeface())) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            if (Build.VERSION.SDK_INT >= 24) {
                Object[] objArr = new Object[11];
                objArr[0] = Float.valueOf(this.mPaint.getTextSize());
                Object[] objArr2 = objArr;
                objArr2[1] = Float.valueOf(this.mPaint.getTextScaleX());
                Object[] objArr3 = objArr2;
                objArr3[2] = Float.valueOf(this.mPaint.getTextSkewX());
                Object[] objArr4 = objArr3;
                objArr4[3] = Float.valueOf(this.mPaint.getLetterSpacing());
                Object[] objArr5 = objArr4;
                objArr5[4] = Integer.valueOf(this.mPaint.getFlags());
                Object[] objArr6 = objArr5;
                objArr6[5] = this.mPaint.getTextLocales();
                Object[] objArr7 = objArr6;
                objArr7[6] = this.mPaint.getTypeface();
                Object[] objArr8 = objArr7;
                objArr8[7] = Boolean.valueOf(this.mPaint.isElegantTextHeight());
                Object[] objArr9 = objArr8;
                objArr9[8] = this.mTextDir;
                Object[] objArr10 = objArr9;
                objArr10[9] = Integer.valueOf(this.mBreakStrategy);
                Object[] objArr11 = objArr10;
                objArr11[10] = Integer.valueOf(this.mHyphenationFrequency);
                return ObjectsCompat.hash(objArr11);
            } else if (Build.VERSION.SDK_INT >= 21) {
                Object[] objArr12 = new Object[11];
                objArr12[0] = Float.valueOf(this.mPaint.getTextSize());
                Object[] objArr13 = objArr12;
                objArr13[1] = Float.valueOf(this.mPaint.getTextScaleX());
                Object[] objArr14 = objArr13;
                objArr14[2] = Float.valueOf(this.mPaint.getTextSkewX());
                Object[] objArr15 = objArr14;
                objArr15[3] = Float.valueOf(this.mPaint.getLetterSpacing());
                Object[] objArr16 = objArr15;
                objArr16[4] = Integer.valueOf(this.mPaint.getFlags());
                Object[] objArr17 = objArr16;
                objArr17[5] = this.mPaint.getTextLocale();
                Object[] objArr18 = objArr17;
                objArr18[6] = this.mPaint.getTypeface();
                Object[] objArr19 = objArr18;
                objArr19[7] = Boolean.valueOf(this.mPaint.isElegantTextHeight());
                Object[] objArr20 = objArr19;
                objArr20[8] = this.mTextDir;
                Object[] objArr21 = objArr20;
                objArr21[9] = Integer.valueOf(this.mBreakStrategy);
                Object[] objArr22 = objArr21;
                objArr22[10] = Integer.valueOf(this.mHyphenationFrequency);
                return ObjectsCompat.hash(objArr22);
            } else if (Build.VERSION.SDK_INT >= 18) {
                Object[] objArr23 = new Object[9];
                objArr23[0] = Float.valueOf(this.mPaint.getTextSize());
                Object[] objArr24 = objArr23;
                objArr24[1] = Float.valueOf(this.mPaint.getTextScaleX());
                Object[] objArr25 = objArr24;
                objArr25[2] = Float.valueOf(this.mPaint.getTextSkewX());
                Object[] objArr26 = objArr25;
                objArr26[3] = Integer.valueOf(this.mPaint.getFlags());
                Object[] objArr27 = objArr26;
                objArr27[4] = this.mPaint.getTextLocale();
                Object[] objArr28 = objArr27;
                objArr28[5] = this.mPaint.getTypeface();
                Object[] objArr29 = objArr28;
                objArr29[6] = this.mTextDir;
                Object[] objArr30 = objArr29;
                objArr30[7] = Integer.valueOf(this.mBreakStrategy);
                Object[] objArr31 = objArr30;
                objArr31[8] = Integer.valueOf(this.mHyphenationFrequency);
                return ObjectsCompat.hash(objArr31);
            } else if (Build.VERSION.SDK_INT >= 17) {
                Object[] objArr32 = new Object[9];
                objArr32[0] = Float.valueOf(this.mPaint.getTextSize());
                Object[] objArr33 = objArr32;
                objArr33[1] = Float.valueOf(this.mPaint.getTextScaleX());
                Object[] objArr34 = objArr33;
                objArr34[2] = Float.valueOf(this.mPaint.getTextSkewX());
                Object[] objArr35 = objArr34;
                objArr35[3] = Integer.valueOf(this.mPaint.getFlags());
                Object[] objArr36 = objArr35;
                objArr36[4] = this.mPaint.getTextLocale();
                Object[] objArr37 = objArr36;
                objArr37[5] = this.mPaint.getTypeface();
                Object[] objArr38 = objArr37;
                objArr38[6] = this.mTextDir;
                Object[] objArr39 = objArr38;
                objArr39[7] = Integer.valueOf(this.mBreakStrategy);
                Object[] objArr40 = objArr39;
                objArr40[8] = Integer.valueOf(this.mHyphenationFrequency);
                return ObjectsCompat.hash(objArr40);
            } else {
                Object[] objArr41 = new Object[8];
                objArr41[0] = Float.valueOf(this.mPaint.getTextSize());
                Object[] objArr42 = objArr41;
                objArr42[1] = Float.valueOf(this.mPaint.getTextScaleX());
                Object[] objArr43 = objArr42;
                objArr43[2] = Float.valueOf(this.mPaint.getTextSkewX());
                Object[] objArr44 = objArr43;
                objArr44[3] = Integer.valueOf(this.mPaint.getFlags());
                Object[] objArr45 = objArr44;
                objArr45[4] = this.mPaint.getTypeface();
                Object[] objArr46 = objArr45;
                objArr46[5] = this.mTextDir;
                Object[] objArr47 = objArr46;
                objArr47[6] = Integer.valueOf(this.mBreakStrategy);
                Object[] objArr48 = objArr47;
                objArr48[7] = Integer.valueOf(this.mHyphenationFrequency);
                return ObjectsCompat.hash(objArr48);
            }
        }

        public String toString() {
            StringBuilder sb;
            StringBuilder sb2;
            StringBuilder sb3;
            StringBuilder sb4;
            StringBuilder sb5;
            StringBuilder sb6;
            StringBuilder sb7;
            StringBuilder sb8;
            StringBuilder sb9;
            StringBuilder sb10;
            StringBuilder sb11;
            StringBuilder sb12;
            StringBuilder sb13;
            new StringBuilder("{");
            StringBuilder sb14 = sb;
            new StringBuilder();
            StringBuilder append = sb14.append(sb2.append("textSize=").append(this.mPaint.getTextSize()).toString());
            new StringBuilder();
            StringBuilder append2 = sb14.append(sb3.append(", textScaleX=").append(this.mPaint.getTextScaleX()).toString());
            new StringBuilder();
            StringBuilder append3 = sb14.append(sb4.append(", textSkewX=").append(this.mPaint.getTextSkewX()).toString());
            if (Build.VERSION.SDK_INT >= 21) {
                new StringBuilder();
                StringBuilder append4 = sb14.append(sb12.append(", letterSpacing=").append(this.mPaint.getLetterSpacing()).toString());
                new StringBuilder();
                StringBuilder append5 = sb14.append(sb13.append(", elegantTextHeight=").append(this.mPaint.isElegantTextHeight()).toString());
            }
            if (Build.VERSION.SDK_INT >= 24) {
                new StringBuilder();
                StringBuilder append6 = sb14.append(sb11.append(", textLocale=").append(this.mPaint.getTextLocales()).toString());
            } else if (Build.VERSION.SDK_INT >= 17) {
                new StringBuilder();
                StringBuilder append7 = sb14.append(sb5.append(", textLocale=").append(this.mPaint.getTextLocale()).toString());
            }
            new StringBuilder();
            StringBuilder append8 = sb14.append(sb6.append(", typeface=").append(this.mPaint.getTypeface()).toString());
            if (Build.VERSION.SDK_INT >= 26) {
                new StringBuilder();
                StringBuilder append9 = sb14.append(sb10.append(", variationSettings=").append(this.mPaint.getFontVariationSettings()).toString());
            }
            new StringBuilder();
            StringBuilder append10 = sb14.append(sb7.append(", textDir=").append(this.mTextDir).toString());
            new StringBuilder();
            StringBuilder append11 = sb14.append(sb8.append(", breakStrategy=").append(this.mBreakStrategy).toString());
            new StringBuilder();
            StringBuilder append12 = sb14.append(sb9.append(", hyphenationFrequency=").append(this.mHyphenationFrequency).toString());
            StringBuilder append13 = sb14.append("}");
            return sb14.toString();
        }
    }

    /* JADX INFO: finally extract failed */
    public static PrecomputedTextCompat create(@NonNull CharSequence charSequence, @NonNull Params params) {
        ArrayList arrayList;
        Object obj;
        PrecomputedTextCompat precomputedTextCompat;
        int paraEnd;
        PrecomputedTextCompat precomputedTextCompat2;
        CharSequence text = charSequence;
        Params params2 = params;
        Object checkNotNull = Preconditions.checkNotNull(text);
        Object checkNotNull2 = Preconditions.checkNotNull(params2);
        try {
            TraceCompat.beginSection("PrecomputedText");
            if (Build.VERSION.SDK_INT < 28 || params2.mWrapped == null) {
                new ArrayList();
                ArrayList arrayList2 = arrayList;
                int end = text.length();
                int paraStart = 0;
                while (paraStart < end) {
                    int paraEnd2 = TextUtils.indexOf(text, LINE_FEED, paraStart, end);
                    if (paraEnd2 < 0) {
                        paraEnd = end;
                    } else {
                        paraEnd = paraEnd2 + 1;
                    }
                    boolean add = arrayList2.add(Integer.valueOf(paraEnd));
                    paraStart = paraEnd;
                }
                int[] result = new int[arrayList2.size()];
                for (int i = 0; i < arrayList2.size(); i++) {
                    result[i] = ((Integer) arrayList2.get(i)).intValue();
                }
                if (Build.VERSION.SDK_INT >= 23) {
                    StaticLayout build = StaticLayout.Builder.obtain(text, 0, text.length(), params2.getTextPaint(), Integer.MAX_VALUE).setBreakStrategy(params2.getBreakStrategy()).setHyphenationFrequency(params2.getHyphenationFrequency()).setTextDirection(params2.getTextDirection()).build();
                } else if (Build.VERSION.SDK_INT >= 21) {
                    Object obj2 = obj;
                    new StaticLayout(text, params2.getTextPaint(), Integer.MAX_VALUE, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
                }
                PrecomputedTextCompat precomputedTextCompat3 = precomputedTextCompat;
                new PrecomputedTextCompat(text, params2, result);
                TraceCompat.endSection();
                return precomputedTextCompat3;
            }
            PrecomputedTextCompat precomputedTextCompat4 = precomputedTextCompat2;
            new PrecomputedTextCompat(PrecomputedText.create(text, params2.mWrapped), params2);
            TraceCompat.endSection();
            return precomputedTextCompat4;
        } catch (Throwable th) {
            TraceCompat.endSection();
            throw th;
        }
    }

    private PrecomputedTextCompat(@NonNull CharSequence text, @NonNull Params params, @NonNull int[] paraEnds) {
        Spannable spannable;
        new SpannableString(text);
        this.mText = spannable;
        this.mParams = params;
        this.mParagraphEnds = paraEnds;
        this.mWrapped = null;
    }

    @RequiresApi(28)
    private PrecomputedTextCompat(@NonNull PrecomputedText precomputedText, @NonNull Params params) {
        PrecomputedText precomputed = precomputedText;
        this.mText = precomputed;
        this.mParams = params;
        this.mParagraphEnds = null;
        this.mWrapped = precomputed;
    }

    @Nullable
    @RequiresApi(28)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public PrecomputedText getPrecomputedText() {
        if (this.mText instanceof PrecomputedText) {
            return (PrecomputedText) this.mText;
        }
        return null;
    }

    @NonNull
    public Params getParams() {
        return this.mParams;
    }

    @IntRange(from = 0)
    public int getParagraphCount() {
        if (Build.VERSION.SDK_INT >= 28) {
            return this.mWrapped.getParagraphCount();
        }
        return this.mParagraphEnds.length;
    }

    @IntRange(from = 0)
    public int getParagraphStart(@IntRange(from = 0) int i) {
        int paraIndex = i;
        int checkArgumentInRange = Preconditions.checkArgumentInRange(paraIndex, 0, getParagraphCount(), "paraIndex");
        if (Build.VERSION.SDK_INT >= 28) {
            return this.mWrapped.getParagraphStart(paraIndex);
        }
        return paraIndex == 0 ? 0 : this.mParagraphEnds[paraIndex - 1];
    }

    @IntRange(from = 0)
    public int getParagraphEnd(@IntRange(from = 0) int i) {
        int paraIndex = i;
        int checkArgumentInRange = Preconditions.checkArgumentInRange(paraIndex, 0, getParagraphCount(), "paraIndex");
        if (Build.VERSION.SDK_INT >= 28) {
            return this.mWrapped.getParagraphEnd(paraIndex);
        }
        return this.mParagraphEnds[paraIndex];
    }

    private int findParaIndex(@IntRange(from = 0) int i) {
        Throwable th;
        StringBuilder sb;
        int pos = i;
        for (int i2 = 0; i2 < this.mParagraphEnds.length; i2++) {
            if (pos < this.mParagraphEnds[i2]) {
                return i2;
            }
        }
        Throwable th2 = th;
        new StringBuilder();
        new IndexOutOfBoundsException(sb.append("pos must be less than ").append(this.mParagraphEnds[this.mParagraphEnds.length - 1]).append(", gave ").append(pos).toString());
        throw th2;
    }

    /* renamed from: android.support.v4.text.PrecomputedTextCompat$PrecomputedTextFutureTask */
    private static class PrecomputedTextFutureTask extends FutureTask<PrecomputedTextCompat> {

        /* renamed from: android.support.v4.text.PrecomputedTextCompat$PrecomputedTextFutureTask$PrecomputedTextCallback */
        private static class PrecomputedTextCallback implements Callable<PrecomputedTextCompat> {
            private Params mParams;
            private CharSequence mText;

            PrecomputedTextCallback(@NonNull Params params, @NonNull CharSequence cs) {
                this.mParams = params;
                this.mText = cs;
            }

            public PrecomputedTextCompat call() throws Exception {
                return PrecomputedTextCompat.create(this.mText, this.mParams);
            }
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        PrecomputedTextFutureTask(@android.support.annotation.NonNull android.support.p000v4.text.PrecomputedTextCompat.Params r10, @android.support.annotation.NonNull java.lang.CharSequence r11) {
            /*
                r9 = this;
                r0 = r9
                r1 = r10
                r2 = r11
                r3 = r0
                android.support.v4.text.PrecomputedTextCompat$PrecomputedTextFutureTask$PrecomputedTextCallback r4 = new android.support.v4.text.PrecomputedTextCompat$PrecomputedTextFutureTask$PrecomputedTextCallback
                r8 = r4
                r4 = r8
                r5 = r8
                r6 = r1
                r7 = r2
                r5.<init>(r6, r7)
                r3.<init>(r4)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.text.PrecomputedTextCompat.PrecomputedTextFutureTask.<init>(android.support.v4.text.PrecomputedTextCompat$Params, java.lang.CharSequence):void");
        }
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [java.util.concurrent.Future<android.support.v4.text.PrecomputedTextCompat>] */
    /* JADX WARNING: Multi-variable type inference failed */
    @android.support.annotation.UiThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.concurrent.Future<android.support.p000v4.text.PrecomputedTextCompat> getTextFuture(@android.support.annotation.NonNull java.lang.CharSequence r11, @android.support.annotation.NonNull android.support.p000v4.text.PrecomputedTextCompat.Params r12, @android.support.annotation.Nullable java.util.concurrent.Executor r13) {
        /*
            r0 = r11
            r1 = r12
            r2 = r13
            android.support.v4.text.PrecomputedTextCompat$PrecomputedTextFutureTask r6 = new android.support.v4.text.PrecomputedTextCompat$PrecomputedTextFutureTask
            r10 = r6
            r6 = r10
            r7 = r10
            r8 = r1
            r9 = r0
            r7.<init>(r8, r9)
            r3 = r6
            r6 = r2
            if (r6 != 0) goto L_0x0028
            java.lang.Object r6 = sLock
            r10 = r6
            r6 = r10
            r7 = r10
            r4 = r7
            monitor-enter(r6)
            java.util.concurrent.Executor r6 = sExecutor     // Catch:{ all -> 0x0030 }
            if (r6 != 0) goto L_0x0023
            r6 = 1
            java.util.concurrent.ExecutorService r6 = java.util.concurrent.Executors.newFixedThreadPool(r6)     // Catch:{ all -> 0x0030 }
            sExecutor = r6     // Catch:{ all -> 0x0030 }
        L_0x0023:
            java.util.concurrent.Executor r6 = sExecutor     // Catch:{ all -> 0x0030 }
            r2 = r6
            r6 = r4
            monitor-exit(r6)     // Catch:{ all -> 0x0030 }
        L_0x0028:
            r6 = r2
            r7 = r3
            r6.execute(r7)
            r6 = r3
            r0 = r6
            return r0
        L_0x0030:
            r6 = move-exception
            r5 = r6
            r6 = r4
            monitor-exit(r6)     // Catch:{ all -> 0x0030 }
            r6 = r5
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.p000v4.text.PrecomputedTextCompat.getTextFuture(java.lang.CharSequence, android.support.v4.text.PrecomputedTextCompat$Params, java.util.concurrent.Executor):java.util.concurrent.Future");
    }

    public void setSpan(Object obj, int i, int i2, int i3) {
        Throwable th;
        Object what = obj;
        int start = i;
        int end = i2;
        int flags = i3;
        if (what instanceof MetricAffectingSpan) {
            Throwable th2 = th;
            new IllegalArgumentException("MetricAffectingSpan can not be set to PrecomputedText.");
            throw th2;
        } else if (Build.VERSION.SDK_INT >= 28) {
            this.mWrapped.setSpan(what, start, end, flags);
        } else {
            this.mText.setSpan(what, start, end, flags);
        }
    }

    public void removeSpan(Object obj) {
        Throwable th;
        Object what = obj;
        if (what instanceof MetricAffectingSpan) {
            Throwable th2 = th;
            new IllegalArgumentException("MetricAffectingSpan can not be removed from PrecomputedText.");
            throw th2;
        } else if (Build.VERSION.SDK_INT >= 28) {
            this.mWrapped.removeSpan(what);
        } else {
            this.mText.removeSpan(what);
        }
    }

    public <T> T[] getSpans(int i, int i2, Class<T> cls) {
        int start = i;
        int end = i2;
        Class<T> type = cls;
        if (Build.VERSION.SDK_INT >= 28) {
            return this.mWrapped.getSpans(start, end, type);
        }
        return this.mText.getSpans(start, end, type);
    }

    public int getSpanStart(Object tag) {
        return this.mText.getSpanStart(tag);
    }

    public int getSpanEnd(Object tag) {
        return this.mText.getSpanEnd(tag);
    }

    public int getSpanFlags(Object tag) {
        return this.mText.getSpanFlags(tag);
    }

    public int nextSpanTransition(int start, int limit, Class type) {
        return this.mText.nextSpanTransition(start, limit, type);
    }

    public int length() {
        return this.mText.length();
    }

    public char charAt(int index) {
        return this.mText.charAt(index);
    }

    public CharSequence subSequence(int start, int end) {
        return this.mText.subSequence(start, end);
    }

    public String toString() {
        return this.mText.toString();
    }
}

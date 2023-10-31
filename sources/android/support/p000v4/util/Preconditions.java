package android.support.p000v4.util;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.text.TextUtils;
import java.util.Collection;
import java.util.Locale;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v4.util.Preconditions */
public class Preconditions {
    public static void checkArgument(boolean expression) {
        Throwable th;
        if (!expression) {
            Throwable th2 = th;
            new IllegalArgumentException();
            throw th2;
        }
    }

    public static void checkArgument(boolean expression, Object obj) {
        Throwable th;
        Object errorMessage = obj;
        if (!expression) {
            Throwable th2 = th;
            new IllegalArgumentException(String.valueOf(errorMessage));
            throw th2;
        }
    }

    @NonNull
    public static <T extends CharSequence> T checkStringNotEmpty(T t) {
        Throwable th;
        T string = t;
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        Throwable th2 = th;
        new IllegalArgumentException();
        throw th2;
    }

    @NonNull
    public static <T extends CharSequence> T checkStringNotEmpty(T t, Object obj) {
        Throwable th;
        T string = t;
        Object errorMessage = obj;
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        Throwable th2 = th;
        new IllegalArgumentException(String.valueOf(errorMessage));
        throw th2;
    }

    @NonNull
    public static <T> T checkNotNull(T t) {
        Throwable th;
        T reference = t;
        if (reference != null) {
            return reference;
        }
        Throwable th2 = th;
        new NullPointerException();
        throw th2;
    }

    @NonNull
    public static <T> T checkNotNull(T t, Object obj) {
        Throwable th;
        T reference = t;
        Object errorMessage = obj;
        if (reference != null) {
            return reference;
        }
        Throwable th2 = th;
        new NullPointerException(String.valueOf(errorMessage));
        throw th2;
    }

    public static void checkState(boolean expression, String str) {
        Throwable th;
        String message = str;
        if (!expression) {
            Throwable th2 = th;
            new IllegalStateException(message);
            throw th2;
        }
    }

    public static void checkState(boolean expression) {
        checkState(expression, (String) null);
    }

    public static int checkFlagsArgument(int i, int i2) {
        Throwable th;
        StringBuilder sb;
        int requestedFlags = i;
        int allowedFlags = i2;
        if ((requestedFlags & allowedFlags) == requestedFlags) {
            return requestedFlags;
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalArgumentException(sb.append("Requested flags 0x").append(Integer.toHexString(requestedFlags)).append(", but only 0x").append(Integer.toHexString(allowedFlags)).append(" are allowed").toString());
        throw th2;
    }

    @IntRange(from = 0)
    public static int checkArgumentNonnegative(int i, String str) {
        Throwable th;
        int value = i;
        String errorMessage = str;
        if (value >= 0) {
            return value;
        }
        Throwable th2 = th;
        new IllegalArgumentException(errorMessage);
        throw th2;
    }

    @IntRange(from = 0)
    public static int checkArgumentNonnegative(int i) {
        Throwable th;
        int value = i;
        if (value >= 0) {
            return value;
        }
        Throwable th2 = th;
        new IllegalArgumentException();
        throw th2;
    }

    public static long checkArgumentNonnegative(long j) {
        Throwable th;
        long value = j;
        if (value >= 0) {
            return value;
        }
        Throwable th2 = th;
        new IllegalArgumentException();
        throw th2;
    }

    public static long checkArgumentNonnegative(long j, String str) {
        Throwable th;
        long value = j;
        String errorMessage = str;
        if (value >= 0) {
            return value;
        }
        Throwable th2 = th;
        new IllegalArgumentException(errorMessage);
        throw th2;
    }

    public static int checkArgumentPositive(int i, String str) {
        Throwable th;
        int value = i;
        String errorMessage = str;
        if (value > 0) {
            return value;
        }
        Throwable th2 = th;
        new IllegalArgumentException(errorMessage);
        throw th2;
    }

    public static float checkArgumentFinite(float f, String str) {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        float value = f;
        String valueName = str;
        if (Float.isNaN(value)) {
            Throwable th3 = th2;
            new StringBuilder();
            new IllegalArgumentException(sb2.append(valueName).append(" must not be NaN").toString());
            throw th3;
        } else if (!Float.isInfinite(value)) {
            return value;
        } else {
            Throwable th4 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append(valueName).append(" must not be infinite").toString());
            throw th4;
        }
    }

    public static float checkArgumentInRange(float f, float f2, float f3, String str) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        StringBuilder sb;
        float value = f;
        float lower = f2;
        float upper = f3;
        String valueName = str;
        if (Float.isNaN(value)) {
            Throwable th4 = th3;
            new StringBuilder();
            new IllegalArgumentException(sb.append(valueName).append(" must not be NaN").toString());
            throw th4;
        } else if (value < lower) {
            Throwable th5 = th2;
            Locale locale = Locale.US;
            Object[] objArr = new Object[3];
            objArr[0] = valueName;
            Object[] objArr2 = objArr;
            objArr2[1] = Float.valueOf(lower);
            Object[] objArr3 = objArr2;
            objArr3[2] = Float.valueOf(upper);
            new IllegalArgumentException(String.format(locale, "%s is out of range of [%f, %f] (too low)", objArr3));
            throw th5;
        } else if (value <= upper) {
            return value;
        } else {
            Throwable th6 = th;
            Locale locale2 = Locale.US;
            Object[] objArr4 = new Object[3];
            objArr4[0] = valueName;
            Object[] objArr5 = objArr4;
            objArr5[1] = Float.valueOf(lower);
            Object[] objArr6 = objArr5;
            objArr6[2] = Float.valueOf(upper);
            new IllegalArgumentException(String.format(locale2, "%s is out of range of [%f, %f] (too high)", objArr6));
            throw th6;
        }
    }

    public static int checkArgumentInRange(int i, int i2, int i3, String str) {
        Throwable th;
        Throwable th2;
        int value = i;
        int lower = i2;
        int upper = i3;
        String valueName = str;
        if (value < lower) {
            Throwable th3 = th2;
            Locale locale = Locale.US;
            Object[] objArr = new Object[3];
            objArr[0] = valueName;
            Object[] objArr2 = objArr;
            objArr2[1] = Integer.valueOf(lower);
            Object[] objArr3 = objArr2;
            objArr3[2] = Integer.valueOf(upper);
            new IllegalArgumentException(String.format(locale, "%s is out of range of [%d, %d] (too low)", objArr3));
            throw th3;
        } else if (value <= upper) {
            return value;
        } else {
            Throwable th4 = th;
            Locale locale2 = Locale.US;
            Object[] objArr4 = new Object[3];
            objArr4[0] = valueName;
            Object[] objArr5 = objArr4;
            objArr5[1] = Integer.valueOf(lower);
            Object[] objArr6 = objArr5;
            objArr6[2] = Integer.valueOf(upper);
            new IllegalArgumentException(String.format(locale2, "%s is out of range of [%d, %d] (too high)", objArr6));
            throw th4;
        }
    }

    public static long checkArgumentInRange(long j, long j2, long j3, String str) {
        Throwable th;
        Throwable th2;
        long value = j;
        long lower = j2;
        long upper = j3;
        String valueName = str;
        if (value < lower) {
            Throwable th3 = th2;
            Locale locale = Locale.US;
            Object[] objArr = new Object[3];
            objArr[0] = valueName;
            Object[] objArr2 = objArr;
            objArr2[1] = Long.valueOf(lower);
            Object[] objArr3 = objArr2;
            objArr3[2] = Long.valueOf(upper);
            new IllegalArgumentException(String.format(locale, "%s is out of range of [%d, %d] (too low)", objArr3));
            throw th3;
        } else if (value <= upper) {
            return value;
        } else {
            Throwable th4 = th;
            Locale locale2 = Locale.US;
            Object[] objArr4 = new Object[3];
            objArr4[0] = valueName;
            Object[] objArr5 = objArr4;
            objArr5[1] = Long.valueOf(lower);
            Object[] objArr6 = objArr5;
            objArr6[2] = Long.valueOf(upper);
            new IllegalArgumentException(String.format(locale2, "%s is out of range of [%d, %d] (too high)", objArr6));
            throw th4;
        }
    }

    public static <T> T[] checkArrayElementsNotNull(T[] tArr, String str) {
        Throwable th;
        Throwable th2;
        StringBuilder sb;
        T[] value = tArr;
        String valueName = str;
        if (value == null) {
            Throwable th3 = th2;
            new StringBuilder();
            new NullPointerException(sb.append(valueName).append(" must not be null").toString());
            throw th3;
        }
        for (int i = 0; i < value.length; i++) {
            if (value[i] == null) {
                Throwable th4 = th;
                Locale locale = Locale.US;
                Object[] objArr = new Object[2];
                objArr[0] = valueName;
                Object[] objArr2 = objArr;
                objArr2[1] = Integer.valueOf(i);
                new NullPointerException(String.format(locale, "%s[%d] must not be null", objArr2));
                throw th4;
            }
        }
        return value;
    }

    @NonNull
    public static <C extends Collection<T>, T> C checkCollectionElementsNotNull(C c, String str) {
        Throwable th;
        Throwable th2;
        StringBuilder sb;
        C<T> value = c;
        String valueName = str;
        if (value == null) {
            Throwable th3 = th2;
            new StringBuilder();
            new NullPointerException(sb.append(valueName).append(" must not be null").toString());
            throw th3;
        }
        long ctr = 0;
        for (T t : value) {
            if (t == null) {
                Throwable th4 = th;
                Locale locale = Locale.US;
                Object[] objArr = new Object[2];
                objArr[0] = valueName;
                Object[] objArr2 = objArr;
                objArr2[1] = Long.valueOf(ctr);
                new NullPointerException(String.format(locale, "%s[%d] must not be null", objArr2));
                throw th4;
            }
            ctr++;
        }
        return value;
    }

    public static <T> Collection<T> checkCollectionNotEmpty(Collection<T> collection, String str) {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        Collection<T> value = collection;
        String valueName = str;
        if (value == null) {
            Throwable th3 = th2;
            new StringBuilder();
            new NullPointerException(sb2.append(valueName).append(" must not be null").toString());
            throw th3;
        } else if (!value.isEmpty()) {
            return value;
        } else {
            Throwable th4 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append(valueName).append(" is empty").toString());
            throw th4;
        }
    }

    public static float[] checkArrayElementsInRange(float[] fArr, float f, float f2, String str) {
        StringBuilder sb;
        Throwable th;
        StringBuilder sb2;
        Throwable th2;
        Throwable th3;
        float[] value = fArr;
        float lower = f;
        float upper = f2;
        String valueName = str;
        new StringBuilder();
        Object checkNotNull = checkNotNull(value, sb.append(valueName).append(" must not be null").toString());
        int i = 0;
        while (i < value.length) {
            float v = value[i];
            if (Float.isNaN(v)) {
                Throwable th4 = th;
                new StringBuilder();
                new IllegalArgumentException(sb2.append(valueName).append("[").append(i).append("] must not be NaN").toString());
                throw th4;
            } else if (v < lower) {
                Throwable th5 = th2;
                Locale locale = Locale.US;
                Object[] objArr = new Object[4];
                objArr[0] = valueName;
                Object[] objArr2 = objArr;
                objArr2[1] = Integer.valueOf(i);
                Object[] objArr3 = objArr2;
                objArr3[2] = Float.valueOf(lower);
                Object[] objArr4 = objArr3;
                objArr4[3] = Float.valueOf(upper);
                new IllegalArgumentException(String.format(locale, "%s[%d] is out of range of [%f, %f] (too low)", objArr4));
                throw th5;
            } else if (v > upper) {
                Throwable th6 = th3;
                Locale locale2 = Locale.US;
                Object[] objArr5 = new Object[4];
                objArr5[0] = valueName;
                Object[] objArr6 = objArr5;
                objArr6[1] = Integer.valueOf(i);
                Object[] objArr7 = objArr6;
                objArr7[2] = Float.valueOf(lower);
                Object[] objArr8 = objArr7;
                objArr8[3] = Float.valueOf(upper);
                new IllegalArgumentException(String.format(locale2, "%s[%d] is out of range of [%f, %f] (too high)", objArr8));
                throw th6;
            } else {
                i++;
            }
        }
        return value;
    }

    private Preconditions() {
    }
}

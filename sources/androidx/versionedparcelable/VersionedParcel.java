package androidx.versionedparcelable;

import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.NetworkOnMainThreadException;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.p000v4.util.C1643ArraySet;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseBooleanArray;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public abstract class VersionedParcel {
    private static final int EX_BAD_PARCELABLE = -2;
    private static final int EX_ILLEGAL_ARGUMENT = -3;
    private static final int EX_ILLEGAL_STATE = -5;
    private static final int EX_NETWORK_MAIN_THREAD = -6;
    private static final int EX_NULL_POINTER = -4;
    private static final int EX_PARCELABLE = -9;
    private static final int EX_SECURITY = -1;
    private static final int EX_UNSUPPORTED_OPERATION = -7;
    private static final String TAG = "VersionedParcel";
    private static final int TYPE_BINDER = 5;
    private static final int TYPE_PARCELABLE = 2;
    private static final int TYPE_SERIALIZABLE = 3;
    private static final int TYPE_STRING = 4;
    private static final int TYPE_VERSIONED_PARCELABLE = 1;

    /* access modifiers changed from: protected */
    public abstract void closeField();

    /* access modifiers changed from: protected */
    public abstract VersionedParcel createSubParcel();

    /* access modifiers changed from: protected */
    public abstract boolean readBoolean();

    /* access modifiers changed from: protected */
    public abstract Bundle readBundle();

    /* access modifiers changed from: protected */
    public abstract byte[] readByteArray();

    /* access modifiers changed from: protected */
    public abstract double readDouble();

    /* access modifiers changed from: protected */
    public abstract boolean readField(int i);

    /* access modifiers changed from: protected */
    public abstract float readFloat();

    /* access modifiers changed from: protected */
    public abstract int readInt();

    /* access modifiers changed from: protected */
    public abstract long readLong();

    /* access modifiers changed from: protected */
    public abstract <T extends Parcelable> T readParcelable();

    /* access modifiers changed from: protected */
    public abstract String readString();

    /* access modifiers changed from: protected */
    public abstract IBinder readStrongBinder();

    /* access modifiers changed from: protected */
    public abstract void setOutputField(int i);

    /* access modifiers changed from: protected */
    public abstract void writeBoolean(boolean z);

    /* access modifiers changed from: protected */
    public abstract void writeBundle(Bundle bundle);

    /* access modifiers changed from: protected */
    public abstract void writeByteArray(byte[] bArr);

    /* access modifiers changed from: protected */
    public abstract void writeByteArray(byte[] bArr, int i, int i2);

    /* access modifiers changed from: protected */
    public abstract void writeDouble(double d);

    /* access modifiers changed from: protected */
    public abstract void writeFloat(float f);

    /* access modifiers changed from: protected */
    public abstract void writeInt(int i);

    /* access modifiers changed from: protected */
    public abstract void writeLong(long j);

    /* access modifiers changed from: protected */
    public abstract void writeParcelable(Parcelable parcelable);

    /* access modifiers changed from: protected */
    public abstract void writeString(String str);

    /* access modifiers changed from: protected */
    public abstract void writeStrongBinder(IBinder iBinder);

    /* access modifiers changed from: protected */
    public abstract void writeStrongInterface(IInterface iInterface);

    public VersionedParcel() {
    }

    public boolean isStream() {
        return false;
    }

    public void setSerializationFlags(boolean allowSerialization, boolean ignoreParcelables) {
    }

    public void writeStrongInterface(IInterface val, int fieldId) {
        setOutputField(fieldId);
        writeStrongInterface(val);
    }

    public void writeBundle(Bundle val, int fieldId) {
        setOutputField(fieldId);
        writeBundle(val);
    }

    public void writeBoolean(boolean val, int fieldId) {
        setOutputField(fieldId);
        writeBoolean(val);
    }

    public void writeByteArray(byte[] b, int fieldId) {
        setOutputField(fieldId);
        writeByteArray(b);
    }

    public void writeByteArray(byte[] b, int offset, int len, int fieldId) {
        setOutputField(fieldId);
        writeByteArray(b, offset, len);
    }

    public void writeInt(int val, int fieldId) {
        setOutputField(fieldId);
        writeInt(val);
    }

    public void writeLong(long val, int fieldId) {
        setOutputField(fieldId);
        writeLong(val);
    }

    public void writeFloat(float val, int fieldId) {
        setOutputField(fieldId);
        writeFloat(val);
    }

    public void writeDouble(double val, int fieldId) {
        setOutputField(fieldId);
        writeDouble(val);
    }

    public void writeString(String val, int fieldId) {
        setOutputField(fieldId);
        writeString(val);
    }

    public void writeStrongBinder(IBinder val, int fieldId) {
        setOutputField(fieldId);
        writeStrongBinder(val);
    }

    public void writeParcelable(Parcelable p, int fieldId) {
        setOutputField(fieldId);
        writeParcelable(p);
    }

    public boolean readBoolean(boolean z, int fieldId) {
        boolean def = z;
        if (!readField(fieldId)) {
            return def;
        }
        return readBoolean();
    }

    public int readInt(int i, int fieldId) {
        int def = i;
        if (!readField(fieldId)) {
            return def;
        }
        return readInt();
    }

    public long readLong(long j, int fieldId) {
        long def = j;
        if (!readField(fieldId)) {
            return def;
        }
        return readLong();
    }

    public float readFloat(float f, int fieldId) {
        float def = f;
        if (!readField(fieldId)) {
            return def;
        }
        return readFloat();
    }

    public double readDouble(double d, int fieldId) {
        double def = d;
        if (!readField(fieldId)) {
            return def;
        }
        return readDouble();
    }

    public String readString(String str, int fieldId) {
        String def = str;
        if (!readField(fieldId)) {
            return def;
        }
        return readString();
    }

    public IBinder readStrongBinder(IBinder iBinder, int fieldId) {
        IBinder def = iBinder;
        if (!readField(fieldId)) {
            return def;
        }
        return readStrongBinder();
    }

    public byte[] readByteArray(byte[] bArr, int fieldId) {
        byte[] def = bArr;
        if (!readField(fieldId)) {
            return def;
        }
        return readByteArray();
    }

    public <T extends Parcelable> T readParcelable(T t, int fieldId) {
        VersionedParcel def = t;
        if (!readField(fieldId)) {
            return def;
        }
        return readParcelable();
    }

    public Bundle readBundle(Bundle bundle, int fieldId) {
        Bundle def = bundle;
        if (!readField(fieldId)) {
            return def;
        }
        return readBundle();
    }

    public void writeByte(byte val, int fieldId) {
        setOutputField(fieldId);
        writeInt(val);
    }

    @RequiresApi(api = 21)
    public void writeSize(Size size, int fieldId) {
        Size val = size;
        setOutputField(fieldId);
        writeBoolean(val != null);
        if (val != null) {
            writeInt(val.getWidth());
            writeInt(val.getHeight());
        }
    }

    @RequiresApi(api = 21)
    public void writeSizeF(SizeF sizeF, int fieldId) {
        SizeF val = sizeF;
        setOutputField(fieldId);
        writeBoolean(val != null);
        if (val != null) {
            writeFloat(val.getWidth());
            writeFloat(val.getHeight());
        }
    }

    public void writeSparseBooleanArray(SparseBooleanArray sparseBooleanArray, int fieldId) {
        SparseBooleanArray val = sparseBooleanArray;
        setOutputField(fieldId);
        if (val == null) {
            writeInt(-1);
            return;
        }
        int n = val.size();
        writeInt(n);
        for (int i = 0; i < n; i++) {
            writeInt(val.keyAt(i));
            writeBoolean(val.valueAt(i));
        }
    }

    public void writeBooleanArray(boolean[] val, int fieldId) {
        setOutputField(fieldId);
        writeBooleanArray(val);
    }

    /* access modifiers changed from: protected */
    public void writeBooleanArray(boolean[] zArr) {
        boolean[] val = zArr;
        if (val != null) {
            int n = val.length;
            writeInt(n);
            for (int i = 0; i < n; i++) {
                writeInt(val[i] ? 1 : 0);
            }
            return;
        }
        writeInt(-1);
    }

    public boolean[] readBooleanArray(boolean[] zArr, int fieldId) {
        boolean[] def = zArr;
        if (!readField(fieldId)) {
            return def;
        }
        return readBooleanArray();
    }

    /* access modifiers changed from: protected */
    public boolean[] readBooleanArray() {
        int n = readInt();
        if (n < 0) {
            return null;
        }
        boolean[] val = new boolean[n];
        for (int i = 0; i < n; i++) {
            val[i] = readInt() != 0;
        }
        return val;
    }

    public void writeCharArray(char[] cArr, int fieldId) {
        char[] val = cArr;
        setOutputField(fieldId);
        if (val != null) {
            int n = val.length;
            writeInt(n);
            for (int i = 0; i < n; i++) {
                writeInt(val[i]);
            }
            return;
        }
        writeInt(-1);
    }

    public char[] readCharArray(char[] cArr, int fieldId) {
        char[] def = cArr;
        if (!readField(fieldId)) {
            return def;
        }
        int n = readInt();
        if (n < 0) {
            return null;
        }
        char[] val = new char[n];
        for (int i = 0; i < n; i++) {
            val[i] = (char) readInt();
        }
        return val;
    }

    public void writeIntArray(int[] val, int fieldId) {
        setOutputField(fieldId);
        writeIntArray(val);
    }

    /* access modifiers changed from: protected */
    public void writeIntArray(int[] iArr) {
        int[] val = iArr;
        if (val != null) {
            int n = val.length;
            writeInt(n);
            for (int i = 0; i < n; i++) {
                writeInt(val[i]);
            }
            return;
        }
        writeInt(-1);
    }

    public int[] readIntArray(int[] iArr, int fieldId) {
        int[] def = iArr;
        if (!readField(fieldId)) {
            return def;
        }
        return readIntArray();
    }

    /* access modifiers changed from: protected */
    public int[] readIntArray() {
        int n = readInt();
        if (n < 0) {
            return null;
        }
        int[] val = new int[n];
        for (int i = 0; i < n; i++) {
            val[i] = readInt();
        }
        return val;
    }

    public void writeLongArray(long[] val, int fieldId) {
        setOutputField(fieldId);
        writeLongArray(val);
    }

    /* access modifiers changed from: protected */
    public void writeLongArray(long[] jArr) {
        long[] val = jArr;
        if (val != null) {
            int n = val.length;
            writeInt(n);
            for (int i = 0; i < n; i++) {
                writeLong(val[i]);
            }
            return;
        }
        writeInt(-1);
    }

    public long[] readLongArray(long[] jArr, int fieldId) {
        long[] def = jArr;
        if (!readField(fieldId)) {
            return def;
        }
        return readLongArray();
    }

    /* access modifiers changed from: protected */
    public long[] readLongArray() {
        int n = readInt();
        if (n < 0) {
            return null;
        }
        long[] val = new long[n];
        for (int i = 0; i < n; i++) {
            val[i] = readLong();
        }
        return val;
    }

    public void writeFloatArray(float[] val, int fieldId) {
        setOutputField(fieldId);
        writeFloatArray(val);
    }

    /* access modifiers changed from: protected */
    public void writeFloatArray(float[] fArr) {
        float[] val = fArr;
        if (val != null) {
            int n = val.length;
            writeInt(n);
            for (int i = 0; i < n; i++) {
                writeFloat(val[i]);
            }
            return;
        }
        writeInt(-1);
    }

    public float[] readFloatArray(float[] fArr, int fieldId) {
        float[] def = fArr;
        if (!readField(fieldId)) {
            return def;
        }
        return readFloatArray();
    }

    /* access modifiers changed from: protected */
    public float[] readFloatArray() {
        int n = readInt();
        if (n < 0) {
            return null;
        }
        float[] val = new float[n];
        for (int i = 0; i < n; i++) {
            val[i] = readFloat();
        }
        return val;
    }

    public void writeDoubleArray(double[] val, int fieldId) {
        setOutputField(fieldId);
        writeDoubleArray(val);
    }

    /* access modifiers changed from: protected */
    public void writeDoubleArray(double[] dArr) {
        double[] val = dArr;
        if (val != null) {
            int n = val.length;
            writeInt(n);
            for (int i = 0; i < n; i++) {
                writeDouble(val[i]);
            }
            return;
        }
        writeInt(-1);
    }

    public double[] readDoubleArray(double[] dArr, int fieldId) {
        double[] def = dArr;
        if (!readField(fieldId)) {
            return def;
        }
        return readDoubleArray();
    }

    /* access modifiers changed from: protected */
    public double[] readDoubleArray() {
        int n = readInt();
        if (n < 0) {
            return null;
        }
        double[] val = new double[n];
        for (int i = 0; i < n; i++) {
            val[i] = readDouble();
        }
        return val;
    }

    public <T> void writeSet(Set<T> val, int fieldId) {
        writeCollection(val, fieldId);
    }

    public <T> void writeList(List<T> val, int fieldId) {
        writeCollection(val, fieldId);
    }

    private <T> void writeCollection(Collection<T> collection, int fieldId) {
        Collection<T> val = collection;
        setOutputField(fieldId);
        if (val == null) {
            writeInt(-1);
            return;
        }
        int n = val.size();
        writeInt(n);
        if (n > 0) {
            int type = getType(val.iterator().next());
            writeInt(type);
            switch (type) {
                case 1:
                    for (T writeVersionedParcelable : val) {
                        writeVersionedParcelable(writeVersionedParcelable);
                    }
                    return;
                case 2:
                    for (T writeParcelable : val) {
                        writeParcelable(writeParcelable);
                    }
                    return;
                case 3:
                    for (T writeSerializable : val) {
                        writeSerializable(writeSerializable);
                    }
                    return;
                case 4:
                    for (T writeString : val) {
                        writeString(writeString);
                    }
                    return;
                case 5:
                    for (T writeStrongBinder : val) {
                        writeStrongBinder(writeStrongBinder);
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public <T> void writeArray(T[] val, int fieldId) {
        setOutputField(fieldId);
        writeArray(val);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x003f, code lost:
        if (r3 >= r2) goto L_0x000a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0041, code lost:
        writeParcelable((android.os.Parcelable) r1[r3]);
        r3 = r3 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0050, code lost:
        if (r3 >= r2) goto L_0x000a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0052, code lost:
        writeVersionedParcelable((androidx.versionedparcelable.VersionedParcelable) r1[r3]);
        r3 = r3 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0061, code lost:
        if (r3 >= r2) goto L_0x000a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0063, code lost:
        writeSerializable((java.io.Serializable) r1[r3]);
        r3 = r3 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0072, code lost:
        if (r3 >= r2) goto L_0x000a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0074, code lost:
        writeStrongBinder((android.os.IBinder) r1[r3]);
        r3 = r3 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x002e, code lost:
        if (r3 >= r2) goto L_0x000a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0030, code lost:
        writeString((java.lang.String) r1[r3]);
        r3 = r3 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> void writeArray(T[] r9) {
        /*
            r8 = this;
            r0 = r8
            r1 = r9
            r5 = r1
            if (r5 != 0) goto L_0x000b
            r5 = r0
            r6 = -1
            r5.writeInt(r6)
        L_0x000a:
            return
        L_0x000b:
            r5 = r1
            int r5 = r5.length
            r2 = r5
            r5 = 0
            r3 = r5
            r5 = r0
            r6 = r2
            r5.writeInt(r6)
            r5 = r2
            if (r5 <= 0) goto L_0x002b
            r5 = r0
            r6 = r1
            r7 = 0
            r6 = r6[r7]
            int r5 = r5.getType(r6)
            r4 = r5
            r5 = r0
            r6 = r4
            r5.writeInt(r6)
            r5 = r4
            switch(r5) {
                case 1: goto L_0x004e;
                case 2: goto L_0x003d;
                case 3: goto L_0x005f;
                case 4: goto L_0x002c;
                case 5: goto L_0x0070;
                default: goto L_0x002b;
            }
        L_0x002b:
            goto L_0x000a
        L_0x002c:
            r5 = r3
            r6 = r2
            if (r5 >= r6) goto L_0x002b
            r5 = r0
            r6 = r1
            r7 = r3
            r6 = r6[r7]
            java.lang.String r6 = (java.lang.String) r6
            r5.writeString(r6)
            int r3 = r3 + 1
            goto L_0x002c
        L_0x003d:
            r5 = r3
            r6 = r2
            if (r5 >= r6) goto L_0x002b
            r5 = r0
            r6 = r1
            r7 = r3
            r6 = r6[r7]
            android.os.Parcelable r6 = (android.os.Parcelable) r6
            r5.writeParcelable(r6)
            int r3 = r3 + 1
            goto L_0x003d
        L_0x004e:
            r5 = r3
            r6 = r2
            if (r5 >= r6) goto L_0x002b
            r5 = r0
            r6 = r1
            r7 = r3
            r6 = r6[r7]
            androidx.versionedparcelable.VersionedParcelable r6 = (androidx.versionedparcelable.VersionedParcelable) r6
            r5.writeVersionedParcelable(r6)
            int r3 = r3 + 1
            goto L_0x004e
        L_0x005f:
            r5 = r3
            r6 = r2
            if (r5 >= r6) goto L_0x002b
            r5 = r0
            r6 = r1
            r7 = r3
            r6 = r6[r7]
            java.io.Serializable r6 = (java.io.Serializable) r6
            r5.writeSerializable(r6)
            int r3 = r3 + 1
            goto L_0x005f
        L_0x0070:
            r5 = r3
            r6 = r2
            if (r5 >= r6) goto L_0x002b
            r5 = r0
            r6 = r1
            r7 = r3
            r6 = r6[r7]
            android.os.IBinder r6 = (android.os.IBinder) r6
            r5.writeStrongBinder(r6)
            int r3 = r3 + 1
            goto L_0x0070
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.versionedparcelable.VersionedParcel.writeArray(java.lang.Object[]):void");
    }

    private <T> int getType(T t) {
        Throwable th;
        StringBuilder sb;
        T t2 = t;
        if (t2 instanceof String) {
            return 4;
        }
        if (t2 instanceof Parcelable) {
            return 2;
        }
        if (t2 instanceof VersionedParcelable) {
            return 1;
        }
        if (t2 instanceof Serializable) {
            return 3;
        }
        if (t2 instanceof IBinder) {
            return 5;
        }
        Throwable th2 = th;
        new StringBuilder();
        new IllegalArgumentException(sb.append(t2.getClass().getName()).append(" cannot be VersionedParcelled").toString());
        throw th2;
    }

    public void writeVersionedParcelable(VersionedParcelable p, int fieldId) {
        setOutputField(fieldId);
        writeVersionedParcelable(p);
    }

    /* access modifiers changed from: protected */
    public void writeVersionedParcelable(VersionedParcelable versionedParcelable) {
        VersionedParcelable p = versionedParcelable;
        if (p == null) {
            writeString((String) null);
            return;
        }
        writeVersionedParcelableCreator(p);
        VersionedParcel subParcel = createSubParcel();
        writeToParcel(p, subParcel);
        subParcel.closeField();
    }

    private void writeVersionedParcelableCreator(VersionedParcelable versionedParcelable) {
        Throwable th;
        StringBuilder sb;
        VersionedParcelable p = versionedParcelable;
        try {
            writeString(findParcelClass((Class<? extends VersionedParcelable>) p.getClass()).getName());
        } catch (ClassNotFoundException e) {
            ClassNotFoundException e2 = e;
            Throwable th2 = th;
            new StringBuilder();
            new RuntimeException(sb.append(p.getClass().getSimpleName()).append(" does not have a Parcelizer").toString(), e2);
            throw th2;
        }
    }

    public void writeSerializable(Serializable s, int fieldId) {
        setOutputField(fieldId);
        writeSerializable(s);
    }

    private void writeSerializable(Serializable serializable) {
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        StringBuilder sb;
        ObjectOutputStream objectOutputStream;
        Serializable s = serializable;
        if (s == null) {
            writeString((String) null);
            return;
        }
        String name = s.getClass().getName();
        writeString(name);
        new ByteArrayOutputStream();
        ByteArrayOutputStream baos = byteArrayOutputStream;
        try {
            new ObjectOutputStream(baos);
            ObjectOutputStream oos = objectOutputStream;
            oos.writeObject(s);
            oos.close();
            writeByteArray(baos.toByteArray());
        } catch (IOException e) {
            IOException ioe = e;
            Throwable th2 = th;
            new StringBuilder();
            new RuntimeException(sb.append("VersionedParcelable encountered IOException writing serializable object (name = ").append(name).append(")").toString(), ioe);
            throw th2;
        }
    }

    public void writeException(Exception exc, int fieldId) {
        Throwable th;
        Exception e = exc;
        setOutputField(fieldId);
        if (e == null) {
            writeNoException();
            return;
        }
        int code = 0;
        if ((e instanceof Parcelable) && e.getClass().getClassLoader() == Parcelable.class.getClassLoader()) {
            code = -9;
        } else if (e instanceof SecurityException) {
            code = -1;
        } else if (e instanceof BadParcelableException) {
            code = -2;
        } else if (e instanceof IllegalArgumentException) {
            code = -3;
        } else if (e instanceof NullPointerException) {
            code = -4;
        } else if (e instanceof IllegalStateException) {
            code = -5;
        } else if (e instanceof NetworkOnMainThreadException) {
            code = -6;
        } else if (e instanceof UnsupportedOperationException) {
            code = -7;
        }
        writeInt(code);
        if (code != 0) {
            writeString(e.getMessage());
            switch (code) {
                case -9:
                    writeParcelable((Parcelable) e);
                    return;
                default:
                    return;
            }
        } else if (e instanceof RuntimeException) {
            throw ((RuntimeException) e);
        } else {
            Throwable th2 = th;
            new RuntimeException(e);
            throw th2;
        }
    }

    /* access modifiers changed from: protected */
    public void writeNoException() {
        writeInt(0);
    }

    public Exception readException(Exception exc, int fieldId) {
        Exception def = exc;
        if (!readField(fieldId)) {
            return def;
        }
        int code = readExceptionCode();
        if (code == 0) {
            return def;
        }
        return readException(code, readString());
    }

    private int readExceptionCode() {
        return readInt();
    }

    private Exception readException(int code, String msg) {
        return createException(code, msg);
    }

    @NonNull
    protected static Throwable getRootCause(@NonNull Throwable th) {
        Throwable t = th;
        while (t.getCause() != null) {
            t = t.getCause();
        }
        return t;
    }

    private Exception createException(int i, String str) {
        Exception exc;
        Exception exc2;
        Exception exc3;
        Exception exc4;
        Exception exc5;
        Exception exc6;
        Exception exc7;
        Exception exc8;
        StringBuilder sb;
        int code = i;
        String msg = str;
        switch (code) {
            case -9:
                return (Exception) readParcelable();
            case -7:
                new UnsupportedOperationException(msg);
                return exc;
            case -6:
                new NetworkOnMainThreadException();
                return exc2;
            case -5:
                new IllegalStateException(msg);
                return exc3;
            case -4:
                new NullPointerException(msg);
                return exc4;
            case -3:
                new IllegalArgumentException(msg);
                return exc5;
            case -2:
                new BadParcelableException(msg);
                return exc6;
            case -1:
                new SecurityException(msg);
                return exc7;
            default:
                new StringBuilder();
                new RuntimeException(sb.append("Unknown exception code: ").append(code).append(" msg ").append(msg).toString());
                return exc8;
        }
    }

    public byte readByte(byte b, int fieldId) {
        byte def = b;
        if (!readField(fieldId)) {
            return def;
        }
        return (byte) (readInt() & 255);
    }

    @RequiresApi(api = 21)
    public Size readSize(Size size, int fieldId) {
        Size size2;
        Size def = size;
        if (!readField(fieldId)) {
            return def;
        }
        if (!readBoolean()) {
            return null;
        }
        new Size(readInt(), readInt());
        return size2;
    }

    @RequiresApi(api = 21)
    public SizeF readSizeF(SizeF sizeF, int fieldId) {
        SizeF sizeF2;
        SizeF def = sizeF;
        if (!readField(fieldId)) {
            return def;
        }
        if (!readBoolean()) {
            return null;
        }
        new SizeF(readFloat(), readFloat());
        return sizeF2;
    }

    public SparseBooleanArray readSparseBooleanArray(SparseBooleanArray sparseBooleanArray, int fieldId) {
        SparseBooleanArray sparseBooleanArray2;
        SparseBooleanArray def = sparseBooleanArray;
        if (!readField(fieldId)) {
            return def;
        }
        int n = readInt();
        if (n < 0) {
            return null;
        }
        new SparseBooleanArray(n);
        SparseBooleanArray sa = sparseBooleanArray2;
        for (int i = 0; i < n; i++) {
            sa.put(readInt(), readBoolean());
        }
        return sa;
    }

    public <T> Set<T> readSet(Set<T> set, int i) {
        Collection collection;
        Set<T> def = set;
        int fieldId = i;
        if (!readField(fieldId)) {
            return def;
        }
        new C1643ArraySet();
        return (Set) readCollection(fieldId, collection);
    }

    public <T> List<T> readList(List<T> list, int i) {
        Collection collection;
        List<T> def = list;
        int fieldId = i;
        if (!readField(fieldId)) {
            return def;
        }
        new ArrayList();
        return (List) readCollection(fieldId, collection);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private <T, S extends java.util.Collection<T>> S readCollection(int r8, S r9) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r9
            r5 = r0
            int r5 = r5.readInt()
            r3 = r5
            r5 = r3
            if (r5 >= 0) goto L_0x000f
            r5 = 0
            r0 = r5
        L_0x000e:
            return r0
        L_0x000f:
            r5 = r3
            if (r5 == 0) goto L_0x0022
            r5 = r0
            int r5 = r5.readInt()
            r4 = r5
            r5 = r3
            if (r5 >= 0) goto L_0x001e
            r5 = 0
            r0 = r5
            goto L_0x000e
        L_0x001e:
            r5 = r4
            switch(r5) {
                case 1: goto L_0x0045;
                case 2: goto L_0x0035;
                case 3: goto L_0x0055;
                case 4: goto L_0x0025;
                case 5: goto L_0x0065;
                default: goto L_0x0022;
            }
        L_0x0022:
            r5 = r2
            r0 = r5
            goto L_0x000e
        L_0x0025:
            r5 = r3
            if (r5 <= 0) goto L_0x0022
            r5 = r2
            r6 = r0
            java.lang.String r6 = r6.readString()
            boolean r5 = r5.add(r6)
            int r3 = r3 + -1
            goto L_0x0025
        L_0x0035:
            r5 = r3
            if (r5 <= 0) goto L_0x0022
            r5 = r2
            r6 = r0
            android.os.Parcelable r6 = r6.readParcelable()
            boolean r5 = r5.add(r6)
            int r3 = r3 + -1
            goto L_0x0035
        L_0x0045:
            r5 = r3
            if (r5 <= 0) goto L_0x0022
            r5 = r2
            r6 = r0
            androidx.versionedparcelable.VersionedParcelable r6 = r6.readVersionedParcelable()
            boolean r5 = r5.add(r6)
            int r3 = r3 + -1
            goto L_0x0045
        L_0x0055:
            r5 = r3
            if (r5 <= 0) goto L_0x0022
            r5 = r2
            r6 = r0
            java.io.Serializable r6 = r6.readSerializable()
            boolean r5 = r5.add(r6)
            int r3 = r3 + -1
            goto L_0x0055
        L_0x0065:
            r5 = r3
            if (r5 <= 0) goto L_0x0022
            r5 = r2
            r6 = r0
            android.os.IBinder r6 = r6.readStrongBinder()
            boolean r5 = r5.add(r6)
            int r3 = r3 + -1
            goto L_0x0065
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.versionedparcelable.VersionedParcel.readCollection(int, java.util.Collection):java.util.Collection");
    }

    public <T> T[] readArray(T[] tArr, int fieldId) {
        T[] def = tArr;
        if (!readField(fieldId)) {
            return def;
        }
        return readArray(def);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> T[] readArray(T[] r10) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r5 = r0
            int r5 = r5.readInt()
            r2 = r5
            r5 = r2
            if (r5 >= 0) goto L_0x000e
            r5 = 0
            r0 = r5
        L_0x000d:
            return r0
        L_0x000e:
            java.util.ArrayList r5 = new java.util.ArrayList
            r8 = r5
            r5 = r8
            r6 = r8
            r7 = r2
            r6.<init>(r7)
            r3 = r5
            r5 = r2
            if (r5 == 0) goto L_0x002b
            r5 = r0
            int r5 = r5.readInt()
            r4 = r5
            r5 = r2
            if (r5 >= 0) goto L_0x0027
            r5 = 0
            r0 = r5
            goto L_0x000d
        L_0x0027:
            r5 = r4
            switch(r5) {
                case 1: goto L_0x0053;
                case 2: goto L_0x0043;
                case 3: goto L_0x0063;
                case 4: goto L_0x0033;
                case 5: goto L_0x0073;
                default: goto L_0x002b;
            }
        L_0x002b:
            r5 = r3
            r6 = r1
            java.lang.Object[] r5 = r5.toArray(r6)
            r0 = r5
            goto L_0x000d
        L_0x0033:
            r5 = r2
            if (r5 <= 0) goto L_0x002b
            r5 = r3
            r6 = r0
            java.lang.String r6 = r6.readString()
            boolean r5 = r5.add(r6)
            int r2 = r2 + -1
            goto L_0x0033
        L_0x0043:
            r5 = r2
            if (r5 <= 0) goto L_0x002b
            r5 = r3
            r6 = r0
            android.os.Parcelable r6 = r6.readParcelable()
            boolean r5 = r5.add(r6)
            int r2 = r2 + -1
            goto L_0x0043
        L_0x0053:
            r5 = r2
            if (r5 <= 0) goto L_0x002b
            r5 = r3
            r6 = r0
            androidx.versionedparcelable.VersionedParcelable r6 = r6.readVersionedParcelable()
            boolean r5 = r5.add(r6)
            int r2 = r2 + -1
            goto L_0x0053
        L_0x0063:
            r5 = r2
            if (r5 <= 0) goto L_0x002b
            r5 = r3
            r6 = r0
            java.io.Serializable r6 = r6.readSerializable()
            boolean r5 = r5.add(r6)
            int r2 = r2 + -1
            goto L_0x0063
        L_0x0073:
            r5 = r2
            if (r5 <= 0) goto L_0x002b
            r5 = r3
            r6 = r0
            android.os.IBinder r6 = r6.readStrongBinder()
            boolean r5 = r5.add(r6)
            int r2 = r2 + -1
            goto L_0x0073
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.versionedparcelable.VersionedParcel.readArray(java.lang.Object[]):java.lang.Object[]");
    }

    public <T extends VersionedParcelable> T readVersionedParcelable(T t, int fieldId) {
        VersionedParcel def = t;
        if (!readField(fieldId)) {
            return def;
        }
        return readVersionedParcelable();
    }

    /* access modifiers changed from: protected */
    public <T extends VersionedParcelable> T readVersionedParcelable() {
        String name = readString();
        if (name == null) {
            return null;
        }
        return readFromParcel(name, createSubParcel());
    }

    /* access modifiers changed from: protected */
    public Serializable readSerializable() {
        ByteArrayInputStream bais;
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        StringBuilder sb2;
        ObjectInputStream ois;
        String name = readString();
        if (name == null) {
            return null;
        }
        new ByteArrayInputStream(readByteArray());
        try {
            new ObjectInputStream(this, bais) {
                final /* synthetic */ VersionedParcel this$0;

                {
                    this.this$0 = this$0;
                }

                /* access modifiers changed from: protected */
                public Class<?> resolveClass(ObjectStreamClass objectStreamClass) throws IOException, ClassNotFoundException {
                    ObjectStreamClass osClass = objectStreamClass;
                    Class<?> c = Class.forName(osClass.getName(), false, getClass().getClassLoader());
                    if (c != null) {
                        return c;
                    }
                    return super.resolveClass(osClass);
                }
            };
            return (Serializable) ois.readObject();
        } catch (IOException e) {
            IOException ioe = e;
            Throwable th3 = th2;
            new StringBuilder();
            new RuntimeException(sb2.append("VersionedParcelable encountered IOException reading a Serializable object (name = ").append(name).append(")").toString(), ioe);
            throw th3;
        } catch (ClassNotFoundException e2) {
            ClassNotFoundException cnfe = e2;
            Throwable th4 = th;
            new StringBuilder();
            new RuntimeException(sb.append("VersionedParcelable encountered ClassNotFoundException reading a Serializable object (name = ").append(name).append(")").toString(), cnfe);
            throw th4;
        }
    }

    protected static <T extends VersionedParcelable> T readFromParcel(String parcelCls, VersionedParcel versionedParcel) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        VersionedParcel versionedParcel2 = versionedParcel;
        try {
            return (VersionedParcelable) Class.forName(parcelCls, true, VersionedParcel.class.getClassLoader()).getDeclaredMethod("read", new Class[]{VersionedParcel.class}).invoke((Object) null, new Object[]{versionedParcel2});
        } catch (IllegalAccessException e) {
            IllegalAccessException e2 = e;
            Throwable th5 = th4;
            new RuntimeException("VersionedParcel encountered IllegalAccessException", e2);
            throw th5;
        } catch (InvocationTargetException e3) {
            InvocationTargetException e4 = e3;
            if (e4.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e4.getCause());
            }
            Throwable th6 = th3;
            new RuntimeException("VersionedParcel encountered InvocationTargetException", e4);
            throw th6;
        } catch (NoSuchMethodException e5) {
            NoSuchMethodException e6 = e5;
            Throwable th7 = th2;
            new RuntimeException("VersionedParcel encountered NoSuchMethodException", e6);
            throw th7;
        } catch (ClassNotFoundException e7) {
            ClassNotFoundException e8 = e7;
            Throwable th8 = th;
            new RuntimeException("VersionedParcel encountered ClassNotFoundException", e8);
            throw th8;
        }
    }

    protected static <T extends VersionedParcelable> void writeToParcel(T t, VersionedParcel versionedParcel) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        Throwable th4;
        T val = t;
        VersionedParcel versionedParcel2 = versionedParcel;
        try {
            Class[] clsArr = new Class[2];
            clsArr[0] = val.getClass();
            Class[] clsArr2 = clsArr;
            clsArr2[1] = VersionedParcel.class;
            Method declaredMethod = findParcelClass(val).getDeclaredMethod("write", clsArr2);
            Object[] objArr = new Object[2];
            objArr[0] = val;
            Object[] objArr2 = objArr;
            objArr2[1] = versionedParcel2;
            Object invoke = declaredMethod.invoke((Object) null, objArr2);
        } catch (IllegalAccessException e) {
            IllegalAccessException e2 = e;
            Throwable th5 = th4;
            new RuntimeException("VersionedParcel encountered IllegalAccessException", e2);
            throw th5;
        } catch (InvocationTargetException e3) {
            InvocationTargetException e4 = e3;
            if (e4.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e4.getCause());
            }
            Throwable th6 = th3;
            new RuntimeException("VersionedParcel encountered InvocationTargetException", e4);
            throw th6;
        } catch (NoSuchMethodException e5) {
            NoSuchMethodException e6 = e5;
            Throwable th7 = th2;
            new RuntimeException("VersionedParcel encountered NoSuchMethodException", e6);
            throw th7;
        } catch (ClassNotFoundException e7) {
            ClassNotFoundException e8 = e7;
            Throwable th8 = th;
            new RuntimeException("VersionedParcel encountered ClassNotFoundException", e8);
            throw th8;
        }
    }

    private static <T extends VersionedParcelable> Class findParcelClass(T val) throws ClassNotFoundException {
        return findParcelClass((Class<? extends VersionedParcelable>) val.getClass());
    }

    private static Class findParcelClass(Class<? extends VersionedParcelable> cls) throws ClassNotFoundException {
        Class<? extends VersionedParcelable> cls2 = cls;
        Object[] objArr = new Object[2];
        objArr[0] = cls2.getPackage().getName();
        Object[] objArr2 = objArr;
        objArr2[1] = cls2.getSimpleName();
        return Class.forName(String.format("%s.%sParcelizer", objArr2), false, cls2.getClassLoader());
    }

    public static class ParcelException extends RuntimeException {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ParcelException(Throwable source) {
            super(source);
        }
    }
}

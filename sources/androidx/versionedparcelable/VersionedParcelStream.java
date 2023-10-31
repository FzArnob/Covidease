package androidx.versionedparcelable;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcelable;
import android.support.annotation.RestrictTo;
import android.util.SparseArray;
import androidx.versionedparcelable.VersionedParcel;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Set;
import org.shaded.apache.http.protocol.HTTP;

@RestrictTo({RestrictTo.Scope.LIBRARY})
class VersionedParcelStream extends VersionedParcel {
    private static final int TYPE_BOOLEAN = 5;
    private static final int TYPE_BOOLEAN_ARRAY = 6;
    private static final int TYPE_DOUBLE = 7;
    private static final int TYPE_DOUBLE_ARRAY = 8;
    private static final int TYPE_FLOAT = 13;
    private static final int TYPE_FLOAT_ARRAY = 14;
    private static final int TYPE_INT = 9;
    private static final int TYPE_INT_ARRAY = 10;
    private static final int TYPE_LONG = 11;
    private static final int TYPE_LONG_ARRAY = 12;
    private static final int TYPE_NULL = 0;
    private static final int TYPE_STRING = 3;
    private static final int TYPE_STRING_ARRAY = 4;
    private static final int TYPE_SUB_BUNDLE = 1;
    private static final int TYPE_SUB_PERSISTABLE_BUNDLE = 2;
    private static final Charset UTF_16 = Charset.forName(HTTP.UTF_16);
    private final SparseArray<InputBuffer> mCachedFields;
    private DataInputStream mCurrentInput;
    private DataOutputStream mCurrentOutput;
    private FieldBuffer mFieldBuffer;
    private boolean mIgnoreParcelables;
    private final DataInputStream mMasterInput;
    private final DataOutputStream mMasterOutput;

    public VersionedParcelStream(InputStream inputStream, OutputStream outputStream) {
        SparseArray<InputBuffer> sparseArray;
        DataInputStream dataInputStream;
        DataOutputStream dataOutputStream;
        DataOutputStream dataOutputStream2;
        DataInputStream dataInputStream2;
        InputStream input = inputStream;
        OutputStream output = outputStream;
        new SparseArray<>();
        this.mCachedFields = sparseArray;
        if (input != null) {
            dataInputStream = dataInputStream2;
            new DataInputStream(input);
        } else {
            dataInputStream = null;
        }
        this.mMasterInput = dataInputStream;
        if (output != null) {
            dataOutputStream = dataOutputStream2;
            new DataOutputStream(output);
        } else {
            dataOutputStream = null;
        }
        this.mMasterOutput = dataOutputStream;
        this.mCurrentInput = this.mMasterInput;
        this.mCurrentOutput = this.mMasterOutput;
    }

    public boolean isStream() {
        return true;
    }

    public void setSerializationFlags(boolean allowSerialization, boolean z) {
        Throwable th;
        boolean ignoreParcelables = z;
        if (!allowSerialization) {
            Throwable th2 = th;
            new RuntimeException("Serialization of this object is not allowed");
            throw th2;
        }
        this.mIgnoreParcelables = ignoreParcelables;
    }

    public void closeField() {
        Throwable th;
        if (this.mFieldBuffer != null) {
            try {
                if (this.mFieldBuffer.mOutput.size() != 0) {
                    this.mFieldBuffer.flushField();
                }
                this.mFieldBuffer = null;
            } catch (IOException e) {
                IOException e2 = e;
                Throwable th2 = th;
                new VersionedParcel.ParcelException(e2);
                throw th2;
            }
        }
    }

    /* access modifiers changed from: protected */
    public VersionedParcel createSubParcel() {
        VersionedParcelStream versionedParcelStream;
        new VersionedParcelStream(this.mCurrentInput, this.mCurrentOutput);
        return versionedParcelStream;
    }

    public boolean readField(int i) {
        InputBuffer inputBuffer;
        int fieldId = i;
        InputBuffer buffer = this.mCachedFields.get(fieldId);
        if (buffer != null) {
            this.mCachedFields.remove(fieldId);
            this.mCurrentInput = buffer.mInputStream;
            return true;
        }
        while (true) {
            int fieldInfo = this.mMasterInput.readInt();
            int size = fieldInfo & 65535;
            if (size == 65535) {
                size = this.mMasterInput.readInt();
            }
            new InputBuffer((fieldInfo >> 16) & 65535, size, this.mMasterInput);
            InputBuffer buffer2 = inputBuffer;
            if (buffer2.mFieldId == fieldId) {
                this.mCurrentInput = buffer2.mInputStream;
                return true;
            }
            try {
                this.mCachedFields.put(buffer2.mFieldId, buffer2);
            } catch (IOException e) {
                IOException iOException = e;
                return false;
            }
        }
    }

    public void setOutputField(int fieldId) {
        FieldBuffer fieldBuffer;
        closeField();
        new FieldBuffer(fieldId, this.mMasterOutput);
        this.mFieldBuffer = fieldBuffer;
        this.mCurrentOutput = this.mFieldBuffer.mDataStream;
    }

    public void writeByteArray(byte[] bArr) {
        Throwable th;
        byte[] b = bArr;
        if (b != null) {
            try {
                this.mCurrentOutput.writeInt(b.length);
                this.mCurrentOutput.write(b);
            } catch (IOException e) {
                IOException e2 = e;
                Throwable th2 = th;
                new VersionedParcel.ParcelException(e2);
                throw th2;
            }
        } else {
            this.mCurrentOutput.writeInt(-1);
        }
    }

    public void writeByteArray(byte[] bArr, int i, int i2) {
        Throwable th;
        byte[] b = bArr;
        int offset = i;
        int len = i2;
        if (b != null) {
            try {
                this.mCurrentOutput.writeInt(len);
                this.mCurrentOutput.write(b, offset, len);
            } catch (IOException e) {
                IOException e2 = e;
                Throwable th2 = th;
                new VersionedParcel.ParcelException(e2);
                throw th2;
            }
        } else {
            this.mCurrentOutput.writeInt(-1);
        }
    }

    public void writeInt(int val) {
        Throwable th;
        try {
            this.mCurrentOutput.writeInt(val);
        } catch (IOException e) {
            IOException e2 = e;
            Throwable th2 = th;
            new VersionedParcel.ParcelException(e2);
            throw th2;
        }
    }

    public void writeLong(long val) {
        Throwable th;
        try {
            this.mCurrentOutput.writeLong(val);
        } catch (IOException e) {
            IOException e2 = e;
            Throwable th2 = th;
            new VersionedParcel.ParcelException(e2);
            throw th2;
        }
    }

    public void writeFloat(float val) {
        Throwable th;
        try {
            this.mCurrentOutput.writeFloat(val);
        } catch (IOException e) {
            IOException e2 = e;
            Throwable th2 = th;
            new VersionedParcel.ParcelException(e2);
            throw th2;
        }
    }

    public void writeDouble(double val) {
        Throwable th;
        try {
            this.mCurrentOutput.writeDouble(val);
        } catch (IOException e) {
            IOException e2 = e;
            Throwable th2 = th;
            new VersionedParcel.ParcelException(e2);
            throw th2;
        }
    }

    public void writeString(String str) {
        Throwable th;
        String val = str;
        if (val != null) {
            try {
                byte[] bytes = val.getBytes(UTF_16);
                this.mCurrentOutput.writeInt(bytes.length);
                this.mCurrentOutput.write(bytes);
            } catch (IOException e) {
                IOException e2 = e;
                Throwable th2 = th;
                new VersionedParcel.ParcelException(e2);
                throw th2;
            }
        } else {
            this.mCurrentOutput.writeInt(-1);
        }
    }

    public void writeBoolean(boolean val) {
        Throwable th;
        try {
            this.mCurrentOutput.writeBoolean(val);
        } catch (IOException e) {
            IOException e2 = e;
            Throwable th2 = th;
            new VersionedParcel.ParcelException(e2);
            throw th2;
        }
    }

    public void writeStrongBinder(IBinder iBinder) {
        Throwable th;
        IBinder iBinder2 = iBinder;
        if (!this.mIgnoreParcelables) {
            Throwable th2 = th;
            new RuntimeException("Binders cannot be written to an OutputStream");
            throw th2;
        }
    }

    public void writeParcelable(Parcelable parcelable) {
        Throwable th;
        Parcelable parcelable2 = parcelable;
        if (!this.mIgnoreParcelables) {
            Throwable th2 = th;
            new RuntimeException("Parcelables cannot be written to an OutputStream");
            throw th2;
        }
    }

    public void writeStrongInterface(IInterface iInterface) {
        Throwable th;
        IInterface iInterface2 = iInterface;
        if (!this.mIgnoreParcelables) {
            Throwable th2 = th;
            new RuntimeException("Binders cannot be written to an OutputStream");
            throw th2;
        }
    }

    public IBinder readStrongBinder() {
        return null;
    }

    public <T extends Parcelable> T readParcelable() {
        return null;
    }

    public int readInt() {
        Throwable th;
        try {
            return this.mCurrentInput.readInt();
        } catch (IOException e) {
            IOException e2 = e;
            Throwable th2 = th;
            new VersionedParcel.ParcelException(e2);
            throw th2;
        }
    }

    public long readLong() {
        Throwable th;
        try {
            return this.mCurrentInput.readLong();
        } catch (IOException e) {
            IOException e2 = e;
            Throwable th2 = th;
            new VersionedParcel.ParcelException(e2);
            throw th2;
        }
    }

    public float readFloat() {
        Throwable th;
        try {
            return this.mCurrentInput.readFloat();
        } catch (IOException e) {
            IOException e2 = e;
            Throwable th2 = th;
            new VersionedParcel.ParcelException(e2);
            throw th2;
        }
    }

    public double readDouble() {
        Throwable th;
        try {
            return this.mCurrentInput.readDouble();
        } catch (IOException e) {
            IOException e2 = e;
            Throwable th2 = th;
            new VersionedParcel.ParcelException(e2);
            throw th2;
        }
    }

    public String readString() {
        Throwable th;
        String str;
        try {
            int len = this.mCurrentInput.readInt();
            if (len <= 0) {
                return null;
            }
            byte[] bytes = new byte[len];
            this.mCurrentInput.readFully(bytes);
            String str2 = str;
            new String(bytes, UTF_16);
            return str2;
        } catch (IOException e) {
            IOException e2 = e;
            Throwable th2 = th;
            new VersionedParcel.ParcelException(e2);
            throw th2;
        }
    }

    public byte[] readByteArray() {
        Throwable th;
        try {
            int len = this.mCurrentInput.readInt();
            if (len <= 0) {
                return null;
            }
            byte[] bytes = new byte[len];
            this.mCurrentInput.readFully(bytes);
            return bytes;
        } catch (IOException e) {
            IOException e2 = e;
            Throwable th2 = th;
            new VersionedParcel.ParcelException(e2);
            throw th2;
        }
    }

    public boolean readBoolean() {
        Throwable th;
        try {
            return this.mCurrentInput.readBoolean();
        } catch (IOException e) {
            IOException e2 = e;
            Throwable th2 = th;
            new VersionedParcel.ParcelException(e2);
            throw th2;
        }
    }

    public void writeBundle(Bundle bundle) {
        Throwable th;
        Bundle val = bundle;
        if (val != null) {
            try {
                Set<String> keys = val.keySet();
                this.mCurrentOutput.writeInt(keys.size());
                for (String key : keys) {
                    writeString(key);
                    writeObject(val.get(key));
                }
            } catch (IOException e) {
                IOException e2 = e;
                Throwable th2 = th;
                new VersionedParcel.ParcelException(e2);
                throw th2;
            }
        } else {
            this.mCurrentOutput.writeInt(-1);
        }
    }

    public Bundle readBundle() {
        Bundle bundle;
        int size = readInt();
        if (size < 0) {
            return null;
        }
        new Bundle();
        Bundle b = bundle;
        for (int i = 0; i < size; i++) {
            readObject(readInt(), readString(), b);
        }
        return b;
    }

    private void writeObject(Object obj) {
        Throwable th;
        StringBuilder sb;
        Object o = obj;
        if (o == null) {
            writeInt(0);
        } else if (o instanceof Bundle) {
            writeInt(1);
            writeBundle((Bundle) o);
        } else if (o instanceof String) {
            writeInt(3);
            writeString((String) o);
        } else if (o instanceof String[]) {
            writeInt(4);
            writeArray((String[]) o);
        } else if (o instanceof Boolean) {
            writeInt(5);
            writeBoolean(((Boolean) o).booleanValue());
        } else if (o instanceof boolean[]) {
            writeInt(6);
            writeBooleanArray((boolean[]) o);
        } else if (o instanceof Double) {
            writeInt(7);
            writeDouble(((Double) o).doubleValue());
        } else if (o instanceof double[]) {
            writeInt(8);
            writeDoubleArray((double[]) o);
        } else if (o instanceof Integer) {
            writeInt(9);
            writeInt(((Integer) o).intValue());
        } else if (o instanceof int[]) {
            writeInt(10);
            writeIntArray((int[]) o);
        } else if (o instanceof Long) {
            writeInt(11);
            writeLong(((Long) o).longValue());
        } else if (o instanceof long[]) {
            writeInt(12);
            writeLongArray((long[]) o);
        } else if (o instanceof Float) {
            writeInt(13);
            writeFloat(((Float) o).floatValue());
        } else if (o instanceof float[]) {
            writeInt(14);
            writeFloatArray((float[]) o);
        } else {
            Throwable th2 = th;
            new StringBuilder();
            new IllegalArgumentException(sb.append("Unsupported type ").append(o.getClass()).toString());
            throw th2;
        }
    }

    private void readObject(int i, String str, Bundle bundle) {
        Throwable th;
        StringBuilder sb;
        int type = i;
        String key = str;
        Bundle b = bundle;
        switch (type) {
            case 0:
                b.putParcelable(key, (Parcelable) null);
                return;
            case 1:
                b.putBundle(key, readBundle());
                return;
            case 2:
                b.putBundle(key, readBundle());
                return;
            case 3:
                b.putString(key, readString());
                return;
            case 4:
                b.putStringArray(key, (String[]) readArray(new String[0]));
                return;
            case 5:
                b.putBoolean(key, readBoolean());
                return;
            case 6:
                b.putBooleanArray(key, readBooleanArray());
                return;
            case 7:
                b.putDouble(key, readDouble());
                return;
            case 8:
                b.putDoubleArray(key, readDoubleArray());
                return;
            case 9:
                b.putInt(key, readInt());
                return;
            case 10:
                b.putIntArray(key, readIntArray());
                return;
            case 11:
                b.putLong(key, readLong());
                return;
            case 12:
                b.putLongArray(key, readLongArray());
                return;
            case 13:
                b.putFloat(key, readFloat());
                return;
            case 14:
                b.putFloatArray(key, readFloatArray());
                return;
            default:
                Throwable th2 = th;
                new StringBuilder();
                new RuntimeException(sb.append("Unknown type ").append(type).toString());
                throw th2;
        }
    }

    private static class FieldBuffer {
        final DataOutputStream mDataStream;
        private final int mFieldId;
        final ByteArrayOutputStream mOutput;
        private final DataOutputStream mTarget;

        FieldBuffer(int fieldId, DataOutputStream target) {
            ByteArrayOutputStream byteArrayOutputStream;
            DataOutputStream dataOutputStream;
            new ByteArrayOutputStream();
            this.mOutput = byteArrayOutputStream;
            new DataOutputStream(this.mOutput);
            this.mDataStream = dataOutputStream;
            this.mFieldId = fieldId;
            this.mTarget = target;
        }

        /* access modifiers changed from: package-private */
        public void flushField() throws IOException {
            this.mDataStream.flush();
            int size = this.mOutput.size();
            this.mTarget.writeInt((this.mFieldId << 16) | (size >= 65535 ? 65535 : size));
            if (size >= 65535) {
                this.mTarget.writeInt(size);
            }
            this.mOutput.writeTo(this.mTarget);
        }
    }

    private static class InputBuffer {
        final int mFieldId;
        final DataInputStream mInputStream;
        private final int mSize;

        InputBuffer(int fieldId, int size, DataInputStream inputStream) throws IOException {
            DataInputStream dataInputStream;
            InputStream inputStream2;
            this.mSize = size;
            this.mFieldId = fieldId;
            byte[] data = new byte[this.mSize];
            inputStream.readFully(data);
            new ByteArrayInputStream(data);
            new DataInputStream(inputStream2);
            this.mInputStream = dataInputStream;
        }
    }
}

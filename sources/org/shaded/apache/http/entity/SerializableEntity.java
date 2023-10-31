package org.shaded.apache.http.entity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class SerializableEntity extends AbstractHttpEntity {
    private Serializable objRef;
    private byte[] objSer;

    public SerializableEntity(Serializable serializable, boolean z) throws IOException {
        Throwable th;
        Serializable ser = serializable;
        boolean bufferize = z;
        if (ser == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Source object may not be null");
            throw th2;
        } else if (bufferize) {
            createBytes(ser);
        } else {
            this.objRef = ser;
        }
    }

    private void createBytes(Serializable ser) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream;
        ObjectOutputStream objectOutputStream;
        new ByteArrayOutputStream();
        ByteArrayOutputStream baos = byteArrayOutputStream;
        new ObjectOutputStream(baos);
        ObjectOutputStream out = objectOutputStream;
        out.writeObject(ser);
        out.flush();
        byte[] byteArray = baos.toByteArray();
        this.objSer = byteArray;
    }

    public InputStream getContent() throws IOException, IllegalStateException {
        InputStream inputStream;
        if (this.objSer == null) {
            createBytes(this.objRef);
        }
        new ByteArrayInputStream(this.objSer);
        return inputStream;
    }

    public long getContentLength() {
        if (this.objSer == null) {
            return -1;
        }
        return (long) this.objSer.length;
    }

    public boolean isRepeatable() {
        return true;
    }

    public boolean isStreaming() {
        return this.objSer == null;
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        ObjectOutputStream objectOutputStream;
        Throwable th;
        OutputStream outstream = outputStream;
        if (outstream == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Output stream may not be null");
            throw th2;
        } else if (this.objSer == null) {
            new ObjectOutputStream(outstream);
            ObjectOutputStream out = objectOutputStream;
            out.writeObject(this.objRef);
            out.flush();
        } else {
            outstream.write(this.objSer);
            outstream.flush();
        }
    }
}

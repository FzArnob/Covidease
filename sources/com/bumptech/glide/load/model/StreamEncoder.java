package com.bumptech.glide.load.model;

import android.util.Log;
import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.util.ByteArrayPool;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamEncoder implements Encoder<InputStream> {
    private static final String TAG = "StreamEncoder";

    public StreamEncoder() {
    }

    public boolean encode(InputStream inputStream, OutputStream outputStream) {
        InputStream data = inputStream;
        OutputStream os = outputStream;
        byte[] buffer = ByteArrayPool.get().getBytes();
        while (true) {
            try {
                int read = data.read(buffer);
                int read2 = read;
                if (read != -1) {
                    os.write(buffer, 0, read2);
                } else {
                    boolean releaseBytes = ByteArrayPool.get().releaseBytes(buffer);
                    return true;
                }
            } catch (IOException e) {
                IOException e2 = e;
                if (Log.isLoggable(TAG, 3)) {
                    int d = Log.d(TAG, "Failed to encode data onto the OutputStream", e2);
                }
                boolean releaseBytes2 = ByteArrayPool.get().releaseBytes(buffer);
                return false;
            } catch (Throwable th) {
                Throwable th2 = th;
                boolean releaseBytes3 = ByteArrayPool.get().releaseBytes(buffer);
                throw th2;
            }
        }
    }

    public String getId() {
        return "";
    }
}

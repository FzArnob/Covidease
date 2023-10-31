package kawa.standard;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class char_ready_p {
    public char_ready_p() {
    }

    public static boolean ready(Object obj) {
        Throwable th;
        Object arg1 = obj;
        try {
            if (arg1 instanceof InputStream) {
                return ((InputStream) arg1).available() > 0;
            } else if (arg1 instanceof Reader) {
                return ((Reader) arg1).ready();
            } else {
                Throwable th2 = th;
                new ClassCastException("invalid argument to char-ready?");
                throw th2;
            }
        } catch (IOException e) {
            IOException iOException = e;
            return false;
        }
    }
}

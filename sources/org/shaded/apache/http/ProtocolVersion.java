package org.shaded.apache.http;

import com.shaded.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.Serializable;
import org.shaded.apache.http.util.CharArrayBuffer;

public class ProtocolVersion implements Serializable, Cloneable {
    private static final long serialVersionUID = 8950662842175091068L;
    protected final int major;
    protected final int minor;
    protected final String protocol;

    public ProtocolVersion(String str, int i, int i2) {
        Throwable th;
        Throwable th2;
        Throwable th3;
        String protocol2 = str;
        int major2 = i;
        int minor2 = i2;
        if (protocol2 == null) {
            Throwable th4 = th3;
            new IllegalArgumentException("Protocol name must not be null.");
            throw th4;
        } else if (major2 < 0) {
            Throwable th5 = th2;
            new IllegalArgumentException("Protocol major version number must not be negative.");
            throw th5;
        } else if (minor2 < 0) {
            Throwable th6 = th;
            new IllegalArgumentException("Protocol minor version number may not be negative");
            throw th6;
        } else {
            this.protocol = protocol2;
            this.major = major2;
            this.minor = minor2;
        }
    }

    public final String getProtocol() {
        return this.protocol;
    }

    public final int getMajor() {
        return this.major;
    }

    public final int getMinor() {
        return this.minor;
    }

    public ProtocolVersion forVersion(int i, int i2) {
        ProtocolVersion protocolVersion;
        int major2 = i;
        int minor2 = i2;
        if (major2 == this.major && minor2 == this.minor) {
            return this;
        }
        new ProtocolVersion(this.protocol, major2, minor2);
        return protocolVersion;
    }

    public final int hashCode() {
        return (this.protocol.hashCode() ^ (this.major * 100000)) ^ this.minor;
    }

    public final boolean equals(Object obj) {
        Object obj2 = obj;
        if (this == obj2) {
            return true;
        }
        if (!(obj2 instanceof ProtocolVersion)) {
            return false;
        }
        ProtocolVersion that = (ProtocolVersion) obj2;
        return this.protocol.equals(that.protocol) && this.major == that.major && this.minor == that.minor;
    }

    public boolean isComparable(ProtocolVersion protocolVersion) {
        ProtocolVersion that = protocolVersion;
        return that != null && this.protocol.equals(that.protocol);
    }

    public int compareToVersion(ProtocolVersion protocolVersion) {
        Throwable th;
        StringBuffer stringBuffer;
        Throwable th2;
        ProtocolVersion that = protocolVersion;
        if (that == null) {
            Throwable th3 = th2;
            new IllegalArgumentException("Protocol version must not be null.");
            throw th3;
        } else if (!this.protocol.equals(that.protocol)) {
            Throwable th4 = th;
            new StringBuffer();
            new IllegalArgumentException(stringBuffer.append("Versions for different protocols cannot be compared. ").append(this).append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).append(that).toString());
            throw th4;
        } else {
            int delta = getMajor() - that.getMajor();
            if (delta == 0) {
                delta = getMinor() - that.getMinor();
            }
            return delta;
        }
    }

    public final boolean greaterEquals(ProtocolVersion protocolVersion) {
        ProtocolVersion version = protocolVersion;
        return isComparable(version) && compareToVersion(version) >= 0;
    }

    public final boolean lessEquals(ProtocolVersion protocolVersion) {
        ProtocolVersion version = protocolVersion;
        return isComparable(version) && compareToVersion(version) <= 0;
    }

    public String toString() {
        CharArrayBuffer charArrayBuffer;
        new CharArrayBuffer(16);
        CharArrayBuffer buffer = charArrayBuffer;
        buffer.append(this.protocol);
        buffer.append('/');
        buffer.append(Integer.toString(this.major));
        buffer.append('.');
        buffer.append(Integer.toString(this.minor));
        return buffer.toString();
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

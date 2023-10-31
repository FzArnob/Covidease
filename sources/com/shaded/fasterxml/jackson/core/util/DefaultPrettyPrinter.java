package com.shaded.fasterxml.jackson.core.util;

import com.shaded.fasterxml.jackson.core.JsonGenerationException;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.core.PrettyPrinter;
import com.shaded.fasterxml.jackson.core.SerializableString;
import com.shaded.fasterxml.jackson.core.p005io.SerializedString;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;

public class DefaultPrettyPrinter implements PrettyPrinter, Instantiatable<DefaultPrettyPrinter>, Serializable {
    public static final SerializedString DEFAULT_ROOT_VALUE_SEPARATOR;
    private static final long serialVersionUID = -5512586643324525213L;
    protected Indenter _arrayIndenter;
    protected transient int _nesting;
    protected Indenter _objectIndenter;
    protected final SerializableString _rootSeparator;
    protected boolean _spacesInObjectEntries;

    public interface Indenter {
        boolean isInline();

        void writeIndentation(JsonGenerator jsonGenerator, int i) throws IOException, JsonGenerationException;
    }

    static {
        SerializedString serializedString;
        new SerializedString(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        DEFAULT_ROOT_VALUE_SEPARATOR = serializedString;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public DefaultPrettyPrinter() {
        this((SerializableString) DEFAULT_ROOT_VALUE_SEPARATOR);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DefaultPrettyPrinter(java.lang.String r8) {
        /*
            r7 = this;
            r0 = r7
            r1 = r8
            r2 = r0
            r3 = r1
            if (r3 != 0) goto L_0x000b
            r3 = 0
        L_0x0007:
            r2.<init>((com.shaded.fasterxml.jackson.core.SerializableString) r3)
            return
        L_0x000b:
            com.shaded.fasterxml.jackson.core.io.SerializedString r3 = new com.shaded.fasterxml.jackson.core.io.SerializedString
            r6 = r3
            r3 = r6
            r4 = r6
            r5 = r1
            r4.<init>(r5)
            goto L_0x0007
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.core.util.DefaultPrettyPrinter.<init>(java.lang.String):void");
    }

    public DefaultPrettyPrinter(SerializableString serializableString) {
        this._arrayIndenter = FixedSpaceIndenter.instance;
        this._objectIndenter = Lf2SpacesIndenter.instance;
        this._spacesInObjectEntries = true;
        this._nesting = 0;
        this._rootSeparator = serializableString;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DefaultPrettyPrinter(com.shaded.fasterxml.jackson.core.util.DefaultPrettyPrinter r6) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r2 = r0
            r3 = r1
            r4 = r1
            com.shaded.fasterxml.jackson.core.SerializableString r4 = r4._rootSeparator
            r2.<init>(r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.core.util.DefaultPrettyPrinter.<init>(com.shaded.fasterxml.jackson.core.util.DefaultPrettyPrinter):void");
    }

    public DefaultPrettyPrinter(DefaultPrettyPrinter defaultPrettyPrinter, SerializableString serializableString) {
        DefaultPrettyPrinter defaultPrettyPrinter2 = defaultPrettyPrinter;
        this._arrayIndenter = FixedSpaceIndenter.instance;
        this._objectIndenter = Lf2SpacesIndenter.instance;
        this._spacesInObjectEntries = true;
        this._nesting = 0;
        this._arrayIndenter = defaultPrettyPrinter2._arrayIndenter;
        this._objectIndenter = defaultPrettyPrinter2._objectIndenter;
        this._spacesInObjectEntries = defaultPrettyPrinter2._spacesInObjectEntries;
        this._nesting = defaultPrettyPrinter2._nesting;
        this._rootSeparator = serializableString;
    }

    public DefaultPrettyPrinter withRootSeparator(SerializableString serializableString) {
        DefaultPrettyPrinter defaultPrettyPrinter;
        SerializableString serializableString2 = serializableString;
        if (this._rootSeparator == serializableString2 || (serializableString2 != null && serializableString2.equals(this._rootSeparator))) {
            return this;
        }
        new DefaultPrettyPrinter(this, serializableString2);
        return defaultPrettyPrinter;
    }

    public void indentArraysWith(Indenter indenter) {
        Indenter indenter2 = indenter;
        this._arrayIndenter = indenter2 == null ? NopIndenter.instance : indenter2;
    }

    public void indentObjectsWith(Indenter indenter) {
        Indenter indenter2 = indenter;
        this._objectIndenter = indenter2 == null ? NopIndenter.instance : indenter2;
    }

    public void spacesInObjectEntries(boolean z) {
        boolean z2 = z;
        this._spacesInObjectEntries = z2;
    }

    public DefaultPrettyPrinter createInstance() {
        DefaultPrettyPrinter defaultPrettyPrinter;
        new DefaultPrettyPrinter(this);
        return defaultPrettyPrinter;
    }

    public void writeRootValueSeparator(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException {
        JsonGenerator jsonGenerator2 = jsonGenerator;
        if (this._rootSeparator != null) {
            jsonGenerator2.writeRaw(this._rootSeparator);
        }
    }

    public void writeStartObject(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException {
        jsonGenerator.writeRaw('{');
        if (!this._objectIndenter.isInline()) {
            this._nesting++;
        }
    }

    public void beforeObjectEntries(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException {
        this._objectIndenter.writeIndentation(jsonGenerator, this._nesting);
    }

    public void writeObjectFieldValueSeparator(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException {
        JsonGenerator jsonGenerator2 = jsonGenerator;
        if (this._spacesInObjectEntries) {
            jsonGenerator2.writeRaw(" : ");
        } else {
            jsonGenerator2.writeRaw(':');
        }
    }

    public void writeObjectEntrySeparator(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException {
        JsonGenerator jsonGenerator2 = jsonGenerator;
        jsonGenerator2.writeRaw(',');
        this._objectIndenter.writeIndentation(jsonGenerator2, this._nesting);
    }

    public void writeEndObject(JsonGenerator jsonGenerator, int i) throws IOException, JsonGenerationException {
        JsonGenerator jsonGenerator2 = jsonGenerator;
        int i2 = i;
        if (!this._objectIndenter.isInline()) {
            this._nesting--;
        }
        if (i2 > 0) {
            this._objectIndenter.writeIndentation(jsonGenerator2, this._nesting);
        } else {
            jsonGenerator2.writeRaw(' ');
        }
        jsonGenerator2.writeRaw('}');
    }

    public void writeStartArray(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException {
        JsonGenerator jsonGenerator2 = jsonGenerator;
        if (!this._arrayIndenter.isInline()) {
            this._nesting++;
        }
        jsonGenerator2.writeRaw('[');
    }

    public void beforeArrayValues(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException {
        this._arrayIndenter.writeIndentation(jsonGenerator, this._nesting);
    }

    public void writeArrayValueSeparator(JsonGenerator jsonGenerator) throws IOException, JsonGenerationException {
        JsonGenerator jsonGenerator2 = jsonGenerator;
        jsonGenerator2.writeRaw(',');
        this._arrayIndenter.writeIndentation(jsonGenerator2, this._nesting);
    }

    public void writeEndArray(JsonGenerator jsonGenerator, int i) throws IOException, JsonGenerationException {
        JsonGenerator jsonGenerator2 = jsonGenerator;
        int i2 = i;
        if (!this._arrayIndenter.isInline()) {
            this._nesting--;
        }
        if (i2 > 0) {
            this._arrayIndenter.writeIndentation(jsonGenerator2, this._nesting);
        } else {
            jsonGenerator2.writeRaw(' ');
        }
        jsonGenerator2.writeRaw(']');
    }

    public static class NopIndenter implements Indenter, Serializable {
        public static final NopIndenter instance;

        public NopIndenter() {
        }

        static {
            NopIndenter nopIndenter;
            new NopIndenter();
            instance = nopIndenter;
        }

        public void writeIndentation(JsonGenerator jsonGenerator, int i) throws IOException, JsonGenerationException {
        }

        public boolean isInline() {
            return true;
        }
    }

    public static class FixedSpaceIndenter extends NopIndenter {
        public static final FixedSpaceIndenter instance;

        public FixedSpaceIndenter() {
        }

        static {
            FixedSpaceIndenter fixedSpaceIndenter;
            new FixedSpaceIndenter();
            instance = fixedSpaceIndenter;
        }

        public void writeIndentation(JsonGenerator jsonGenerator, int i) throws IOException, JsonGenerationException {
            int i2 = i;
            jsonGenerator.writeRaw(' ');
        }

        public boolean isInline() {
            return true;
        }
    }

    public static class Lf2SpacesIndenter extends NopIndenter {
        static final char[] SPACES = new char[64];
        static final int SPACE_COUNT = 64;
        private static final String SYS_LF;
        public static final Lf2SpacesIndenter instance;

        public Lf2SpacesIndenter() {
        }

        static {
            Lf2SpacesIndenter lf2SpacesIndenter;
            new Lf2SpacesIndenter();
            instance = lf2SpacesIndenter;
            String str = null;
            try {
                str = System.getProperty("line.separator");
            } catch (Throwable th) {
                Throwable th2 = th;
            }
            SYS_LF = str == null ? "\n" : str;
            Arrays.fill(SPACES, ' ');
        }

        public boolean isInline() {
            return false;
        }

        public void writeIndentation(JsonGenerator jsonGenerator, int i) throws IOException, JsonGenerationException {
            JsonGenerator jsonGenerator2 = jsonGenerator;
            int i2 = i;
            jsonGenerator2.writeRaw(SYS_LF);
            if (i2 > 0) {
                int i3 = i2 + i2;
                while (true) {
                    int i4 = i3;
                    if (i4 > 64) {
                        jsonGenerator2.writeRaw(SPACES, 0, 64);
                        i3 = i4 - SPACES.length;
                    } else {
                        jsonGenerator2.writeRaw(SPACES, 0, i4);
                        return;
                    }
                }
            }
        }
    }
}

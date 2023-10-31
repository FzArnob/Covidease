package com.shaded.fasterxml.jackson.core.json;

import com.shaded.fasterxml.jackson.core.JsonGenerationException;
import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.core.ObjectCodec;
import com.shaded.fasterxml.jackson.core.SerializableString;
import com.shaded.fasterxml.jackson.core.Version;
import com.shaded.fasterxml.jackson.core.base.GeneratorBase;
import com.shaded.fasterxml.jackson.core.p005io.CharTypes;
import com.shaded.fasterxml.jackson.core.p005io.CharacterEscapes;
import com.shaded.fasterxml.jackson.core.p005io.IOContext;
import com.shaded.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.shaded.fasterxml.jackson.core.util.VersionUtil;
import java.io.IOException;

public abstract class JsonGeneratorImpl extends GeneratorBase {
    protected static final int[] sOutputEscapes = CharTypes.get7BitOutputEscapes();
    protected CharacterEscapes _characterEscapes;
    protected final IOContext _ioContext;
    protected int _maximumNonEscapedChar;
    protected int[] _outputEscapes = sOutputEscapes;
    protected SerializableString _rootValueSeparator = DefaultPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public JsonGeneratorImpl(IOContext iOContext, int i, ObjectCodec objectCodec) {
        super(i, objectCodec);
        this._ioContext = iOContext;
        if (isEnabled(JsonGenerator.Feature.ESCAPE_NON_ASCII)) {
            JsonGenerator highestNonEscapedChar = setHighestNonEscapedChar(127);
        }
    }

    public JsonGenerator setHighestNonEscapedChar(int i) {
        int i2 = i;
        this._maximumNonEscapedChar = i2 < 0 ? 0 : i2;
        return this;
    }

    public int getHighestEscapedChar() {
        return this._maximumNonEscapedChar;
    }

    public JsonGenerator setCharacterEscapes(CharacterEscapes characterEscapes) {
        CharacterEscapes characterEscapes2 = characterEscapes;
        this._characterEscapes = characterEscapes2;
        if (characterEscapes2 == null) {
            this._outputEscapes = sOutputEscapes;
        } else {
            this._outputEscapes = characterEscapes2.getEscapeCodesForAscii();
        }
        return this;
    }

    public CharacterEscapes getCharacterEscapes() {
        return this._characterEscapes;
    }

    public JsonGenerator setRootValueSeparator(SerializableString serializableString) {
        this._rootValueSeparator = serializableString;
        return this;
    }

    public Version version() {
        return VersionUtil.versionFor(getClass());
    }

    public final void writeStringField(String str, String str2) throws IOException, JsonGenerationException {
        writeFieldName(str);
        writeString(str2);
    }
}

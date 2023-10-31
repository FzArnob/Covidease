package com.shaded.fasterxml.jackson.databind;

import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;

public abstract class KeyDeserializer {
    public abstract Object deserializeKey(String str, DeserializationContext deserializationContext) throws IOException, JsonProcessingException;

    public KeyDeserializer() {
    }

    public static abstract class None extends KeyDeserializer {
        public None() {
        }
    }
}

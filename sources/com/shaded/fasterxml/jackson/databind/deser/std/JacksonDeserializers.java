package com.shaded.fasterxml.jackson.databind.deser.std;

import com.shaded.fasterxml.jackson.core.JsonLocation;
import com.shaded.fasterxml.jackson.core.JsonParser;
import com.shaded.fasterxml.jackson.core.JsonProcessingException;
import com.shaded.fasterxml.jackson.core.JsonToken;
import com.shaded.fasterxml.jackson.databind.BeanDescription;
import com.shaded.fasterxml.jackson.databind.DeserializationConfig;
import com.shaded.fasterxml.jackson.databind.DeserializationContext;
import com.shaded.fasterxml.jackson.databind.JavaType;
import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.PropertyName;
import com.shaded.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.shaded.fasterxml.jackson.databind.deser.CreatorProperty;
import com.shaded.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.shaded.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.shaded.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.shaded.fasterxml.jackson.databind.util.Annotations;
import com.shaded.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;

public class JacksonDeserializers {
    public JacksonDeserializers() {
    }

    @Deprecated
    public static StdDeserializer<?>[] all() {
        StdDeserializer<?>[] stdDeserializerArr = new StdDeserializer[2];
        stdDeserializerArr[0] = JavaTypeDeserializer.instance;
        StdDeserializer<?>[] stdDeserializerArr2 = stdDeserializerArr;
        StdDeserializer<?>[] stdDeserializerArr3 = stdDeserializerArr2;
        stdDeserializerArr2[1] = TokenBufferDeserializer.instance;
        return stdDeserializerArr3;
    }

    public static JsonDeserializer<?> find(Class<?> cls) {
        Class<?> cls2 = cls;
        if (cls2 == TokenBuffer.class) {
            return TokenBufferDeserializer.instance;
        }
        if (JavaType.class.isAssignableFrom(cls2)) {
            return JavaTypeDeserializer.instance;
        }
        return null;
    }

    public static ValueInstantiator findValueInstantiator(DeserializationConfig deserializationConfig, BeanDescription beanDescription) {
        DeserializationConfig deserializationConfig2 = deserializationConfig;
        if (beanDescription.getBeanClass() == JsonLocation.class) {
            return JsonLocationInstantiator.instance;
        }
        return null;
    }

    public static class JavaTypeDeserializer extends StdScalarDeserializer<JavaType> {
        public static final JavaTypeDeserializer instance;

        static {
            JavaTypeDeserializer javaTypeDeserializer;
            new JavaTypeDeserializer();
            instance = javaTypeDeserializer;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public JavaTypeDeserializer() {
            super((Class<?>) JavaType.class);
        }

        public JavaType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            JsonParser jsonParser2 = jsonParser;
            DeserializationContext deserializationContext2 = deserializationContext;
            JsonToken currentToken = jsonParser2.getCurrentToken();
            if (currentToken == JsonToken.VALUE_STRING) {
                String trim = jsonParser2.getText().trim();
                if (trim.length() == 0) {
                    return (JavaType) getEmptyValue();
                }
                return deserializationContext2.getTypeFactory().constructFromCanonical(trim);
            } else if (currentToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
                return (JavaType) jsonParser2.getEmbeddedObject();
            } else {
                throw deserializationContext2.mappingException((Class<?>) this._valueClass);
            }
        }
    }

    public static class JsonLocationInstantiator extends ValueInstantiator {
        public static final JsonLocationInstantiator instance;

        public JsonLocationInstantiator() {
        }

        static {
            JsonLocationInstantiator jsonLocationInstantiator;
            new JsonLocationInstantiator();
            instance = jsonLocationInstantiator;
        }

        public String getValueTypeDesc() {
            return JsonLocation.class.getName();
        }

        public boolean canCreateFromObjectWith() {
            return true;
        }

        public CreatorProperty[] getFromObjectArguments(DeserializationConfig deserializationConfig) {
            DeserializationConfig deserializationConfig2 = deserializationConfig;
            JavaType constructType = deserializationConfig2.constructType((Class<?>) Integer.TYPE);
            JavaType constructType2 = deserializationConfig2.constructType((Class<?>) Long.TYPE);
            CreatorProperty[] creatorPropertyArr = new CreatorProperty[5];
            creatorPropertyArr[0] = creatorProp("sourceRef", deserializationConfig2.constructType((Class<?>) Object.class), 0);
            CreatorProperty[] creatorPropertyArr2 = creatorPropertyArr;
            creatorPropertyArr2[1] = creatorProp("byteOffset", constructType2, 1);
            CreatorProperty[] creatorPropertyArr3 = creatorPropertyArr2;
            creatorPropertyArr3[2] = creatorProp("charOffset", constructType2, 2);
            CreatorProperty[] creatorPropertyArr4 = creatorPropertyArr3;
            creatorPropertyArr4[3] = creatorProp("lineNr", constructType, 3);
            CreatorProperty[] creatorPropertyArr5 = creatorPropertyArr4;
            creatorPropertyArr5[4] = creatorProp("columnNr", constructType, 4);
            return creatorPropertyArr5;
        }

        private static CreatorProperty creatorProp(String str, JavaType javaType, int i) {
            CreatorProperty creatorProperty;
            new CreatorProperty(str, javaType, (PropertyName) null, (TypeDeserializer) null, (Annotations) null, (AnnotatedParameter) null, i, (Object) null, true);
            return creatorProperty;
        }

        public Object createFromObjectWith(DeserializationContext deserializationContext, Object[] objArr) {
            Object obj;
            DeserializationContext deserializationContext2 = deserializationContext;
            Object[] objArr2 = objArr;
            new JsonLocation(objArr2[0], _long(objArr2[1]), _long(objArr2[2]), _int(objArr2[3]), _int(objArr2[4]));
            return obj;
        }

        private static final long _long(Object obj) {
            Object obj2 = obj;
            return obj2 == null ? 0 : ((Number) obj2).longValue();
        }

        private static final int _int(Object obj) {
            Object obj2 = obj;
            return obj2 == null ? 0 : ((Number) obj2).intValue();
        }
    }

    @JacksonStdImpl
    public static class TokenBufferDeserializer extends StdScalarDeserializer<TokenBuffer> {
        public static final TokenBufferDeserializer instance;

        static {
            TokenBufferDeserializer tokenBufferDeserializer;
            new TokenBufferDeserializer();
            instance = tokenBufferDeserializer;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public TokenBufferDeserializer() {
            super((Class<?>) TokenBuffer.class);
        }

        public TokenBuffer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            TokenBuffer tokenBuffer;
            JsonParser jsonParser2 = jsonParser;
            DeserializationContext deserializationContext2 = deserializationContext;
            new TokenBuffer(jsonParser2.getCodec());
            TokenBuffer tokenBuffer2 = tokenBuffer;
            tokenBuffer2.copyCurrentStructure(jsonParser2);
            return tokenBuffer2;
        }
    }
}

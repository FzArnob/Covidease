package com.firebase.tubesock;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.util.ArrayList;
import java.util.List;

class MessageBuilderFactory {

    interface Builder {
        boolean appendBytes(byte[] bArr);

        WebSocketMessage toMessage();
    }

    MessageBuilderFactory() {
    }

    static class BinaryBuilder implements Builder {
        private int pendingByteCount = 0;
        private List<byte[]> pendingBytes;

        BinaryBuilder() {
            List<byte[]> list;
            new ArrayList();
            this.pendingBytes = list;
        }

        public boolean appendBytes(byte[] bArr) {
            byte[] bytes = bArr;
            boolean add = this.pendingBytes.add(bytes);
            this.pendingByteCount += bytes.length;
            return true;
        }

        public WebSocketMessage toMessage() {
            WebSocketMessage webSocketMessage;
            byte[] payload = new byte[this.pendingByteCount];
            int offset = 0;
            for (int i = 0; i < this.pendingBytes.size(); i++) {
                byte[] segment = this.pendingBytes.get(i);
                System.arraycopy(segment, 0, payload, offset, segment.length);
                offset += segment.length;
            }
            new WebSocketMessage(payload);
            return webSocketMessage;
        }
    }

    static class TextBuilder implements Builder {
        private static ThreadLocal<CharsetDecoder> localDecoder;
        private static ThreadLocal<CharsetEncoder> localEncoder;
        private StringBuilder builder;
        private ByteBuffer carryOver;

        static {
            ThreadLocal<CharsetDecoder> threadLocal;
            ThreadLocal<CharsetEncoder> threadLocal2;
            new ThreadLocal<CharsetDecoder>() {
                /* access modifiers changed from: protected */
                public CharsetDecoder initialValue() {
                    CharsetDecoder decoder = Charset.forName("UTF8").newDecoder();
                    CharsetDecoder onMalformedInput = decoder.onMalformedInput(CodingErrorAction.REPORT);
                    CharsetDecoder onUnmappableCharacter = decoder.onUnmappableCharacter(CodingErrorAction.REPORT);
                    return decoder;
                }
            };
            localDecoder = threadLocal;
            new ThreadLocal<CharsetEncoder>() {
                /* access modifiers changed from: protected */
                public CharsetEncoder initialValue() {
                    CharsetEncoder encoder = Charset.forName("UTF8").newEncoder();
                    CharsetEncoder onMalformedInput = encoder.onMalformedInput(CodingErrorAction.REPORT);
                    CharsetEncoder onUnmappableCharacter = encoder.onUnmappableCharacter(CodingErrorAction.REPORT);
                    return encoder;
                }
            };
            localEncoder = threadLocal2;
        }

        TextBuilder() {
            StringBuilder sb;
            new StringBuilder();
            this.builder = sb;
        }

        public boolean appendBytes(byte[] bytes) {
            String nextFrame = decodeString(bytes);
            if (nextFrame == null) {
                return false;
            }
            StringBuilder append = this.builder.append(nextFrame);
            return true;
        }

        public WebSocketMessage toMessage() {
            WebSocketMessage webSocketMessage;
            if (this.carryOver != null) {
                return null;
            }
            new WebSocketMessage(this.builder.toString());
            return webSocketMessage;
        }

        private String decodeString(byte[] bytes) {
            try {
                return localDecoder.get().decode(ByteBuffer.wrap(bytes)).toString();
            } catch (CharacterCodingException e) {
                CharacterCodingException characterCodingException = e;
                return null;
            }
        }

        private String decodeStringStreaming(byte[] bytes) {
            try {
                ByteBuffer input = getBuffer(bytes);
                int bufSize = (int) (((float) input.remaining()) * localDecoder.get().averageCharsPerByte());
                CharBuffer output = CharBuffer.allocate(bufSize);
                while (true) {
                    CoderResult result = localDecoder.get().decode(input, output, false);
                    if (result.isError()) {
                        return null;
                    }
                    if (result.isUnderflow()) {
                        if (input.remaining() > 0) {
                            this.carryOver = input;
                        }
                        ByteBuffer encode = localEncoder.get().encode(CharBuffer.wrap(output));
                        Buffer flip = output.flip();
                        return output.toString();
                    } else if (result.isOverflow()) {
                        bufSize = (2 * bufSize) + 1;
                        CharBuffer o = CharBuffer.allocate(bufSize);
                        Buffer flip2 = output.flip();
                        CharBuffer put = o.put(output);
                        output = o;
                    }
                }
            } catch (CharacterCodingException e) {
                CharacterCodingException characterCodingException = e;
                return null;
            }
        }

        private ByteBuffer getBuffer(byte[] bArr) {
            byte[] bytes = bArr;
            if (this.carryOver == null) {
                return ByteBuffer.wrap(bytes);
            }
            ByteBuffer buffer = ByteBuffer.allocate(bytes.length + this.carryOver.remaining());
            ByteBuffer put = buffer.put(this.carryOver);
            this.carryOver = null;
            ByteBuffer put2 = buffer.put(bytes);
            Buffer flip = buffer.flip();
            return buffer;
        }
    }

    static Builder builder(byte opcode) {
        Builder builder;
        Builder builder2;
        if (opcode == 2) {
            new BinaryBuilder();
            return builder2;
        }
        new TextBuilder();
        return builder;
    }
}

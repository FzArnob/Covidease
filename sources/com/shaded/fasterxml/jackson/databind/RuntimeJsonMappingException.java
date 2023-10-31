package com.shaded.fasterxml.jackson.databind;

public class RuntimeJsonMappingException extends RuntimeException {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RuntimeJsonMappingException(JsonMappingException jsonMappingException) {
        super(jsonMappingException);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RuntimeJsonMappingException(String str) {
        super(str);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RuntimeJsonMappingException(String str, JsonMappingException jsonMappingException) {
        super(str, jsonMappingException);
    }
}

package com.shaded.fasterxml.jackson.core;

import com.shaded.fasterxml.jackson.core.JsonParser;
import java.util.Iterator;

public interface TreeNode {
    JsonToken asToken();

    Iterator<String> fieldNames();

    TreeNode get(int i);

    TreeNode get(String str);

    boolean isArray();

    boolean isContainerNode();

    boolean isMissingNode();

    boolean isObject();

    boolean isValueNode();

    JsonParser.NumberType numberType();

    TreeNode path(int i);

    TreeNode path(String str);

    int size();

    JsonParser traverse();

    JsonParser traverse(ObjectCodec objectCodec);
}

package com.shaded.fasterxml.jackson.databind.jsonschema;

import com.shaded.fasterxml.jackson.annotation.JsonCreator;
import com.shaded.fasterxml.jackson.annotation.JsonValue;
import com.shaded.fasterxml.jackson.databind.JsonNode;
import com.shaded.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.shaded.fasterxml.jackson.databind.node.ObjectNode;

@Deprecated
public class JsonSchema {
    private final ObjectNode schema;

    @JsonCreator
    public JsonSchema(ObjectNode objectNode) {
        this.schema = objectNode;
    }

    @JsonValue
    public ObjectNode getSchemaNode() {
        return this.schema;
    }

    public String toString() {
        return this.schema.toString();
    }

    public int hashCode() {
        return this.schema.hashCode();
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r6) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r3 = r1
            r4 = r0
            if (r3 != r4) goto L_0x0009
            r3 = 1
            r0 = r3
        L_0x0008:
            return r0
        L_0x0009:
            r3 = r1
            if (r3 != 0) goto L_0x000f
            r3 = 0
            r0 = r3
            goto L_0x0008
        L_0x000f:
            r3 = r1
            boolean r3 = r3 instanceof com.shaded.fasterxml.jackson.databind.jsonschema.JsonSchema
            if (r3 != 0) goto L_0x0017
            r3 = 0
            r0 = r3
            goto L_0x0008
        L_0x0017:
            r3 = r1
            com.shaded.fasterxml.jackson.databind.jsonschema.JsonSchema r3 = (com.shaded.fasterxml.jackson.databind.jsonschema.JsonSchema) r3
            r2 = r3
            r3 = r0
            com.shaded.fasterxml.jackson.databind.node.ObjectNode r3 = r3.schema
            if (r3 != 0) goto L_0x002a
            r3 = r2
            com.shaded.fasterxml.jackson.databind.node.ObjectNode r3 = r3.schema
            if (r3 != 0) goto L_0x0028
            r3 = 1
        L_0x0026:
            r0 = r3
            goto L_0x0008
        L_0x0028:
            r3 = 0
            goto L_0x0026
        L_0x002a:
            r3 = r0
            com.shaded.fasterxml.jackson.databind.node.ObjectNode r3 = r3.schema
            r4 = r2
            com.shaded.fasterxml.jackson.databind.node.ObjectNode r4 = r4.schema
            boolean r3 = r3.equals(r4)
            r0 = r3
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.jsonschema.JsonSchema.equals(java.lang.Object):boolean");
    }

    public static JsonNode getDefaultSchemaNode() {
        ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
        ObjectNode put = objectNode.put("type", "any");
        return objectNode;
    }
}

package com.shaded.fasterxml.jackson.databind.deser.std;

import com.shaded.fasterxml.jackson.databind.JsonDeserializer;
import com.shaded.fasterxml.jackson.databind.deser.BeanDeserializer;
import com.shaded.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import com.shaded.fasterxml.jackson.databind.util.NameTransformer;

public class ThrowableDeserializer extends BeanDeserializer {
    protected static final String PROP_NAME_MESSAGE = "message";
    private static final long serialVersionUID = 1;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ThrowableDeserializer(BeanDeserializer beanDeserializer) {
        super(beanDeserializer);
        this._vanillaProcessing = false;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected ThrowableDeserializer(BeanDeserializer beanDeserializer, NameTransformer nameTransformer) {
        super((BeanDeserializerBase) beanDeserializer, nameTransformer);
    }

    public JsonDeserializer<Object> unwrappingDeserializer(NameTransformer nameTransformer) {
        JsonDeserializer<Object> jsonDeserializer;
        NameTransformer nameTransformer2 = nameTransformer;
        if (getClass() != ThrowableDeserializer.class) {
            return this;
        }
        new ThrowableDeserializer(this, nameTransformer2);
        return jsonDeserializer;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v76, resolved type: com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty[]} */
    /* JADX WARNING: type inference failed for: r12v82, types: [java.lang.Object[]] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object deserializeFromObject(com.shaded.fasterxml.jackson.core.JsonParser r19, com.shaded.fasterxml.jackson.databind.DeserializationContext r20) throws java.io.IOException, com.shaded.fasterxml.jackson.core.JsonProcessingException {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r2 = r20
            r12 = r0
            com.shaded.fasterxml.jackson.databind.deser.impl.PropertyBasedCreator r12 = r12._propertyBasedCreator
            if (r12 == 0) goto L_0x0014
            r12 = r0
            r13 = r1
            r14 = r2
            java.lang.Object r12 = r12._deserializeUsingPropertyBased(r13, r14)
            r0 = r12
        L_0x0013:
            return r0
        L_0x0014:
            r12 = r0
            com.shaded.fasterxml.jackson.databind.JsonDeserializer r12 = r12._delegateDeserializer
            if (r12 == 0) goto L_0x002d
            r12 = r0
            com.shaded.fasterxml.jackson.databind.deser.ValueInstantiator r12 = r12._valueInstantiator
            r13 = r2
            r14 = r0
            com.shaded.fasterxml.jackson.databind.JsonDeserializer r14 = r14._delegateDeserializer
            r15 = r1
            r16 = r2
            java.lang.Object r14 = r14.deserialize(r15, r16)
            java.lang.Object r12 = r12.createUsingDelegate(r13, r14)
            r0 = r12
            goto L_0x0013
        L_0x002d:
            r12 = r0
            com.shaded.fasterxml.jackson.databind.JavaType r12 = r12._beanType
            boolean r12 = r12.isAbstract()
            if (r12 == 0) goto L_0x0060
            r12 = r1
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r17 = r13
            r13 = r17
            r14 = r17
            r14.<init>()
            java.lang.String r14 = "Can not instantiate abstract type "
            java.lang.StringBuilder r13 = r13.append(r14)
            r14 = r0
            com.shaded.fasterxml.jackson.databind.JavaType r14 = r14._beanType
            java.lang.StringBuilder r13 = r13.append(r14)
            java.lang.String r14 = " (need to add/enable type information?)"
            java.lang.StringBuilder r13 = r13.append(r14)
            java.lang.String r13 = r13.toString()
            com.shaded.fasterxml.jackson.databind.JsonMappingException r12 = com.shaded.fasterxml.jackson.databind.JsonMappingException.from(r12, r13)
            throw r12
        L_0x0060:
            r12 = r0
            com.shaded.fasterxml.jackson.databind.deser.ValueInstantiator r12 = r12._valueInstantiator
            boolean r12 = r12.canCreateFromString()
            r3 = r12
            r12 = r0
            com.shaded.fasterxml.jackson.databind.deser.ValueInstantiator r12 = r12._valueInstantiator
            boolean r12 = r12.canCreateUsingDefault()
            r4 = r12
            r12 = r3
            if (r12 != 0) goto L_0x00a6
            r12 = r4
            if (r12 != 0) goto L_0x00a6
            com.shaded.fasterxml.jackson.databind.JsonMappingException r12 = new com.shaded.fasterxml.jackson.databind.JsonMappingException
            r17 = r12
            r12 = r17
            r13 = r17
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r17 = r14
            r14 = r17
            r15 = r17
            r15.<init>()
            java.lang.String r15 = "Can not deserialize Throwable of type "
            java.lang.StringBuilder r14 = r14.append(r15)
            r15 = r0
            com.shaded.fasterxml.jackson.databind.JavaType r15 = r15._beanType
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.String r15 = " without having a default contructor, a single-String-arg constructor; or explicit @JsonCreator"
            java.lang.StringBuilder r14 = r14.append(r15)
            java.lang.String r14 = r14.toString()
            r13.<init>(r14)
            throw r12
        L_0x00a6:
            r12 = 0
            r5 = r12
            r12 = 0
            r6 = r12
            r12 = 0
            r7 = r12
        L_0x00ac:
            r12 = r1
            com.shaded.fasterxml.jackson.core.JsonToken r12 = r12.getCurrentToken()
            com.shaded.fasterxml.jackson.core.JsonToken r13 = com.shaded.fasterxml.jackson.core.JsonToken.END_OBJECT
            if (r12 == r13) goto L_0x0176
            r12 = r1
            java.lang.String r12 = r12.getCurrentName()
            r8 = r12
            r12 = r0
            com.shaded.fasterxml.jackson.databind.deser.impl.BeanPropertyMap r12 = r12._beanProperties
            r13 = r8
            com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty r12 = r12.find(r13)
            r9 = r12
            r12 = r1
            com.shaded.fasterxml.jackson.core.JsonToken r12 = r12.nextToken()
            r12 = r9
            if (r12 == 0) goto L_0x0103
            r12 = r5
            if (r12 == 0) goto L_0x00dc
            r12 = r9
            r13 = r1
            r14 = r2
            r15 = r5
            r12.deserializeAndSet(r13, r14, r15)
        L_0x00d6:
            r12 = r1
            com.shaded.fasterxml.jackson.core.JsonToken r12 = r12.nextToken()
            goto L_0x00ac
        L_0x00dc:
            r12 = r6
            if (r12 != 0) goto L_0x00ed
            r12 = r0
            com.shaded.fasterxml.jackson.databind.deser.impl.BeanPropertyMap r12 = r12._beanProperties
            int r12 = r12.size()
            r10 = r12
            r12 = r10
            r13 = r10
            int r12 = r12 + r13
            java.lang.Object[] r12 = new java.lang.Object[r12]
            r6 = r12
        L_0x00ed:
            r12 = r6
            r13 = r7
            int r7 = r7 + 1
            r14 = r9
            r12[r13] = r14
            r12 = r6
            r13 = r7
            int r7 = r7 + 1
            r14 = r9
            r15 = r1
            r16 = r2
            java.lang.Object r14 = r14.deserialize(r15, r16)
            r12[r13] = r14
            goto L_0x00d6
        L_0x0103:
            java.lang.String r12 = "message"
            r13 = r8
            boolean r12 = r12.equals(r13)
            if (r12 == 0) goto L_0x0143
            r12 = r3
            if (r12 == 0) goto L_0x0143
            r12 = r0
            com.shaded.fasterxml.jackson.databind.deser.ValueInstantiator r12 = r12._valueInstantiator
            r13 = r2
            r14 = r1
            java.lang.String r14 = r14.getText()
            java.lang.Object r12 = r12.createFromString(r13, r14)
            r5 = r12
            r12 = r6
            if (r12 == 0) goto L_0x00d6
            r12 = 0
            r10 = r12
            r12 = r7
            r11 = r12
        L_0x0125:
            r12 = r10
            r13 = r11
            if (r12 >= r13) goto L_0x0140
            r12 = r6
            r13 = r10
            r12 = r12[r13]
            com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty r12 = (com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty) r12
            r9 = r12
            r12 = r9
            r13 = r5
            r14 = r6
            r15 = r10
            r16 = 1
            int r15 = r15 + 1
            r14 = r14[r15]
            r12.set(r13, r14)
            int r10 = r10 + 2
            goto L_0x0125
        L_0x0140:
            r12 = 0
            r6 = r12
            goto L_0x00d6
        L_0x0143:
            r12 = r0
            java.util.HashSet r12 = r12._ignorableProps
            if (r12 == 0) goto L_0x0159
            r12 = r0
            java.util.HashSet r12 = r12._ignorableProps
            r13 = r8
            boolean r12 = r12.contains(r13)
            if (r12 == 0) goto L_0x0159
            r12 = r1
            com.shaded.fasterxml.jackson.core.JsonParser r12 = r12.skipChildren()
            goto L_0x00d6
        L_0x0159:
            r12 = r0
            com.shaded.fasterxml.jackson.databind.deser.SettableAnyProperty r12 = r12._anySetter
            if (r12 == 0) goto L_0x016b
            r12 = r0
            com.shaded.fasterxml.jackson.databind.deser.SettableAnyProperty r12 = r12._anySetter
            r13 = r1
            r14 = r2
            r15 = r5
            r16 = r8
            r12.deserializeAndSet(r13, r14, r15, r16)
            goto L_0x00d6
        L_0x016b:
            r12 = r0
            r13 = r1
            r14 = r2
            r15 = r5
            r16 = r8
            r12.handleUnknownProperty(r13, r14, r15, r16)
            goto L_0x00d6
        L_0x0176:
            r12 = r5
            if (r12 != 0) goto L_0x01b2
            r12 = r3
            if (r12 == 0) goto L_0x01a8
            r12 = r0
            com.shaded.fasterxml.jackson.databind.deser.ValueInstantiator r12 = r12._valueInstantiator
            r13 = r2
            r14 = 0
            java.lang.Object r12 = r12.createFromString(r13, r14)
            r5 = r12
        L_0x0186:
            r12 = r6
            if (r12 == 0) goto L_0x01b2
            r12 = 0
            r8 = r12
            r12 = r7
            r9 = r12
        L_0x018d:
            r12 = r8
            r13 = r9
            if (r12 >= r13) goto L_0x01b2
            r12 = r6
            r13 = r8
            r12 = r12[r13]
            com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty r12 = (com.shaded.fasterxml.jackson.databind.deser.SettableBeanProperty) r12
            r10 = r12
            r12 = r10
            r13 = r5
            r14 = r6
            r15 = r8
            r16 = 1
            int r15 = r15 + 1
            r14 = r14[r15]
            r12.set(r13, r14)
            int r8 = r8 + 2
            goto L_0x018d
        L_0x01a8:
            r12 = r0
            com.shaded.fasterxml.jackson.databind.deser.ValueInstantiator r12 = r12._valueInstantiator
            r13 = r2
            java.lang.Object r12 = r12.createUsingDefault(r13)
            r5 = r12
            goto L_0x0186
        L_0x01b2:
            r12 = r5
            r0 = r12
            goto L_0x0013
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.deser.std.ThrowableDeserializer.deserializeFromObject(com.shaded.fasterxml.jackson.core.JsonParser, com.shaded.fasterxml.jackson.databind.DeserializationContext):java.lang.Object");
    }
}

package com.shaded.fasterxml.jackson.databind.cfg;

import com.shaded.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.shaded.fasterxml.jackson.databind.ser.Serializers;
import com.shaded.fasterxml.jackson.databind.util.ArrayBuilders;
import java.io.Serializable;

public final class SerializerFactoryConfig implements Serializable {
    protected static final BeanSerializerModifier[] NO_MODIFIERS = new BeanSerializerModifier[0];
    protected static final Serializers[] NO_SERIALIZERS = new Serializers[0];
    private static final long serialVersionUID = 1;
    protected final Serializers[] _additionalKeySerializers;
    protected final Serializers[] _additionalSerializers;
    protected final BeanSerializerModifier[] _modifiers;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public SerializerFactoryConfig() {
        this((Serializers[]) null, (Serializers[]) null, (BeanSerializerModifier[]) null);
    }

    protected SerializerFactoryConfig(Serializers[] serializersArr, Serializers[] serializersArr2, BeanSerializerModifier[] beanSerializerModifierArr) {
        Serializers[] serializersArr3 = serializersArr;
        Serializers[] serializersArr4 = serializersArr2;
        BeanSerializerModifier[] beanSerializerModifierArr2 = beanSerializerModifierArr;
        this._additionalSerializers = serializersArr3 == null ? NO_SERIALIZERS : serializersArr3;
        this._additionalKeySerializers = serializersArr4 == null ? NO_SERIALIZERS : serializersArr4;
        this._modifiers = beanSerializerModifierArr2 == null ? NO_MODIFIERS : beanSerializerModifierArr2;
    }

    public SerializerFactoryConfig withAdditionalSerializers(Serializers serializers) {
        SerializerFactoryConfig serializerFactoryConfig;
        Throwable th;
        Serializers serializers2 = serializers;
        if (serializers2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Can not pass null Serializers");
            throw th2;
        }
        new SerializerFactoryConfig((Serializers[]) ArrayBuilders.insertInListNoDup(this._additionalSerializers, serializers2), this._additionalKeySerializers, this._modifiers);
        return serializerFactoryConfig;
    }

    public SerializerFactoryConfig withAdditionalKeySerializers(Serializers serializers) {
        SerializerFactoryConfig serializerFactoryConfig;
        Throwable th;
        Serializers serializers2 = serializers;
        if (serializers2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Can not pass null Serializers");
            throw th2;
        }
        new SerializerFactoryConfig(this._additionalSerializers, (Serializers[]) ArrayBuilders.insertInListNoDup(this._additionalKeySerializers, serializers2), this._modifiers);
        return serializerFactoryConfig;
    }

    public SerializerFactoryConfig withSerializerModifier(BeanSerializerModifier beanSerializerModifier) {
        SerializerFactoryConfig serializerFactoryConfig;
        Throwable th;
        BeanSerializerModifier beanSerializerModifier2 = beanSerializerModifier;
        if (beanSerializerModifier2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Can not pass null modifier");
            throw th2;
        }
        new SerializerFactoryConfig(this._additionalSerializers, this._additionalKeySerializers, (BeanSerializerModifier[]) ArrayBuilders.insertInListNoDup(this._modifiers, beanSerializerModifier2));
        return serializerFactoryConfig;
    }

    public boolean hasSerializers() {
        return this._additionalSerializers.length > 0;
    }

    public boolean hasKeySerializers() {
        return this._additionalKeySerializers.length > 0;
    }

    public boolean hasSerializerModifiers() {
        return this._modifiers.length > 0;
    }

    public Iterable<Serializers> serializers() {
        return ArrayBuilders.arrayAsIterable(this._additionalSerializers);
    }

    public Iterable<Serializers> keySerializers() {
        return ArrayBuilders.arrayAsIterable(this._additionalKeySerializers);
    }

    public Iterable<BeanSerializerModifier> serializerModifiers() {
        return ArrayBuilders.arrayAsIterable(this._modifiers);
    }
}

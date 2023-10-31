package com.shaded.fasterxml.jackson.databind.cfg;

import com.shaded.fasterxml.jackson.databind.AbstractTypeResolver;
import com.shaded.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.shaded.fasterxml.jackson.databind.deser.Deserializers;
import com.shaded.fasterxml.jackson.databind.deser.KeyDeserializers;
import com.shaded.fasterxml.jackson.databind.deser.ValueInstantiators;
import com.shaded.fasterxml.jackson.databind.deser.std.StdKeyDeserializers;
import com.shaded.fasterxml.jackson.databind.util.ArrayBuilders;
import java.io.Serializable;

public class DeserializerFactoryConfig implements Serializable {
    protected static final KeyDeserializers[] DEFAULT_KEY_DESERIALIZERS;
    protected static final AbstractTypeResolver[] NO_ABSTRACT_TYPE_RESOLVERS = new AbstractTypeResolver[0];
    protected static final Deserializers[] NO_DESERIALIZERS = new Deserializers[0];
    protected static final BeanDeserializerModifier[] NO_MODIFIERS = new BeanDeserializerModifier[0];
    protected static final ValueInstantiators[] NO_VALUE_INSTANTIATORS = new ValueInstantiators[0];
    private static final long serialVersionUID = 3683541151102256824L;
    protected final AbstractTypeResolver[] _abstractTypeResolvers;
    protected final Deserializers[] _additionalDeserializers;
    protected final KeyDeserializers[] _additionalKeyDeserializers;
    protected final BeanDeserializerModifier[] _modifiers;
    protected final ValueInstantiators[] _valueInstantiators;

    static {
        KeyDeserializers keyDeserializers;
        new StdKeyDeserializers();
        DEFAULT_KEY_DESERIALIZERS = new KeyDeserializers[]{keyDeserializers};
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public DeserializerFactoryConfig() {
        this((Deserializers[]) null, (KeyDeserializers[]) null, (BeanDeserializerModifier[]) null, (AbstractTypeResolver[]) null, (ValueInstantiators[]) null);
    }

    protected DeserializerFactoryConfig(Deserializers[] deserializersArr, KeyDeserializers[] keyDeserializersArr, BeanDeserializerModifier[] beanDeserializerModifierArr, AbstractTypeResolver[] abstractTypeResolverArr, ValueInstantiators[] valueInstantiatorsArr) {
        Deserializers[] deserializersArr2 = deserializersArr;
        KeyDeserializers[] keyDeserializersArr2 = keyDeserializersArr;
        BeanDeserializerModifier[] beanDeserializerModifierArr2 = beanDeserializerModifierArr;
        AbstractTypeResolver[] abstractTypeResolverArr2 = abstractTypeResolverArr;
        ValueInstantiators[] valueInstantiatorsArr2 = valueInstantiatorsArr;
        this._additionalDeserializers = deserializersArr2 == null ? NO_DESERIALIZERS : deserializersArr2;
        this._additionalKeyDeserializers = keyDeserializersArr2 == null ? DEFAULT_KEY_DESERIALIZERS : keyDeserializersArr2;
        this._modifiers = beanDeserializerModifierArr2 == null ? NO_MODIFIERS : beanDeserializerModifierArr2;
        this._abstractTypeResolvers = abstractTypeResolverArr2 == null ? NO_ABSTRACT_TYPE_RESOLVERS : abstractTypeResolverArr2;
        this._valueInstantiators = valueInstantiatorsArr2 == null ? NO_VALUE_INSTANTIATORS : valueInstantiatorsArr2;
    }

    public DeserializerFactoryConfig withAdditionalDeserializers(Deserializers deserializers) {
        DeserializerFactoryConfig deserializerFactoryConfig;
        Throwable th;
        Deserializers deserializers2 = deserializers;
        if (deserializers2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Can not pass null Deserializers");
            throw th2;
        }
        new DeserializerFactoryConfig((Deserializers[]) ArrayBuilders.insertInListNoDup(this._additionalDeserializers, deserializers2), this._additionalKeyDeserializers, this._modifiers, this._abstractTypeResolvers, this._valueInstantiators);
        return deserializerFactoryConfig;
    }

    public DeserializerFactoryConfig withAdditionalKeyDeserializers(KeyDeserializers keyDeserializers) {
        DeserializerFactoryConfig deserializerFactoryConfig;
        Throwable th;
        KeyDeserializers keyDeserializers2 = keyDeserializers;
        if (keyDeserializers2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Can not pass null KeyDeserializers");
            throw th2;
        }
        new DeserializerFactoryConfig(this._additionalDeserializers, (KeyDeserializers[]) ArrayBuilders.insertInListNoDup(this._additionalKeyDeserializers, keyDeserializers2), this._modifiers, this._abstractTypeResolvers, this._valueInstantiators);
        return deserializerFactoryConfig;
    }

    public DeserializerFactoryConfig withDeserializerModifier(BeanDeserializerModifier beanDeserializerModifier) {
        DeserializerFactoryConfig deserializerFactoryConfig;
        Throwable th;
        BeanDeserializerModifier beanDeserializerModifier2 = beanDeserializerModifier;
        if (beanDeserializerModifier2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Can not pass null modifier");
            throw th2;
        }
        new DeserializerFactoryConfig(this._additionalDeserializers, this._additionalKeyDeserializers, (BeanDeserializerModifier[]) ArrayBuilders.insertInListNoDup(this._modifiers, beanDeserializerModifier2), this._abstractTypeResolvers, this._valueInstantiators);
        return deserializerFactoryConfig;
    }

    public DeserializerFactoryConfig withAbstractTypeResolver(AbstractTypeResolver abstractTypeResolver) {
        DeserializerFactoryConfig deserializerFactoryConfig;
        Throwable th;
        AbstractTypeResolver abstractTypeResolver2 = abstractTypeResolver;
        if (abstractTypeResolver2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Can not pass null resolver");
            throw th2;
        }
        new DeserializerFactoryConfig(this._additionalDeserializers, this._additionalKeyDeserializers, this._modifiers, (AbstractTypeResolver[]) ArrayBuilders.insertInListNoDup(this._abstractTypeResolvers, abstractTypeResolver2), this._valueInstantiators);
        return deserializerFactoryConfig;
    }

    public DeserializerFactoryConfig withValueInstantiators(ValueInstantiators valueInstantiators) {
        DeserializerFactoryConfig deserializerFactoryConfig;
        Throwable th;
        ValueInstantiators valueInstantiators2 = valueInstantiators;
        if (valueInstantiators2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("Can not pass null resolver");
            throw th2;
        }
        new DeserializerFactoryConfig(this._additionalDeserializers, this._additionalKeyDeserializers, this._modifiers, this._abstractTypeResolvers, (ValueInstantiators[]) ArrayBuilders.insertInListNoDup(this._valueInstantiators, valueInstantiators2));
        return deserializerFactoryConfig;
    }

    public boolean hasDeserializers() {
        return this._additionalDeserializers.length > 0;
    }

    public boolean hasKeyDeserializers() {
        return this._additionalKeyDeserializers.length > 0;
    }

    public boolean hasDeserializerModifiers() {
        return this._modifiers.length > 0;
    }

    public boolean hasAbstractTypeResolvers() {
        return this._abstractTypeResolvers.length > 0;
    }

    public boolean hasValueInstantiators() {
        return this._valueInstantiators.length > 0;
    }

    public Iterable<Deserializers> deserializers() {
        return ArrayBuilders.arrayAsIterable(this._additionalDeserializers);
    }

    public Iterable<KeyDeserializers> keyDeserializers() {
        return ArrayBuilders.arrayAsIterable(this._additionalKeyDeserializers);
    }

    public Iterable<BeanDeserializerModifier> deserializerModifiers() {
        return ArrayBuilders.arrayAsIterable(this._modifiers);
    }

    public Iterable<AbstractTypeResolver> abstractTypeResolvers() {
        return ArrayBuilders.arrayAsIterable(this._abstractTypeResolvers);
    }

    public Iterable<ValueInstantiators> valueInstantiators() {
        return ArrayBuilders.arrayAsIterable(this._valueInstantiators);
    }
}

package com.shaded.fasterxml.jackson.databind.ser.impl;

import com.shaded.fasterxml.jackson.core.JsonGenerator;
import com.shaded.fasterxml.jackson.databind.JsonSerializer;
import com.shaded.fasterxml.jackson.databind.SerializerProvider;
import com.shaded.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.shaded.fasterxml.jackson.databind.util.NameTransformer;

public abstract class FilteredBeanPropertyWriter {
    public FilteredBeanPropertyWriter() {
    }

    public static BeanPropertyWriter constructViewBased(BeanPropertyWriter beanPropertyWriter, Class<?>[] clsArr) {
        BeanPropertyWriter beanPropertyWriter2;
        BeanPropertyWriter beanPropertyWriter3;
        BeanPropertyWriter beanPropertyWriter4 = beanPropertyWriter;
        Class<?>[] clsArr2 = clsArr;
        if (clsArr2.length == 1) {
            new SingleView(beanPropertyWriter4, clsArr2[0]);
            return beanPropertyWriter3;
        }
        new MultiView(beanPropertyWriter4, clsArr2);
        return beanPropertyWriter2;
    }

    private static final class SingleView extends BeanPropertyWriter {
        protected final BeanPropertyWriter _delegate;
        protected final Class<?> _view;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        protected SingleView(com.shaded.fasterxml.jackson.databind.ser.BeanPropertyWriter r6, java.lang.Class<?> r7) {
            /*
                r5 = this;
                r0 = r5
                r1 = r6
                r2 = r7
                r3 = r0
                r4 = r1
                r3.<init>(r4)
                r3 = r0
                r4 = r1
                r3._delegate = r4
                r3 = r0
                r4 = r2
                r3._view = r4
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.ser.impl.FilteredBeanPropertyWriter.SingleView.<init>(com.shaded.fasterxml.jackson.databind.ser.BeanPropertyWriter, java.lang.Class):void");
        }

        public SingleView rename(NameTransformer nameTransformer) {
            SingleView singleView;
            new SingleView(this._delegate.rename(nameTransformer), this._view);
            return singleView;
        }

        public void assignSerializer(JsonSerializer<Object> jsonSerializer) {
            this._delegate.assignSerializer(jsonSerializer);
        }

        public void assignNullSerializer(JsonSerializer<Object> jsonSerializer) {
            this._delegate.assignNullSerializer(jsonSerializer);
        }

        public void serializeAsField(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws Exception {
            Object obj2 = obj;
            JsonGenerator jsonGenerator2 = jsonGenerator;
            SerializerProvider serializerProvider2 = serializerProvider;
            Class<?> activeView = serializerProvider2.getActiveView();
            if (activeView == null || this._view.isAssignableFrom(activeView)) {
                this._delegate.serializeAsField(obj2, jsonGenerator2, serializerProvider2);
            }
        }

        public void serializeAsColumn(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws Exception {
            Object obj2 = obj;
            JsonGenerator jsonGenerator2 = jsonGenerator;
            SerializerProvider serializerProvider2 = serializerProvider;
            Class<?> activeView = serializerProvider2.getActiveView();
            if (activeView == null || this._view.isAssignableFrom(activeView)) {
                this._delegate.serializeAsColumn(obj2, jsonGenerator2, serializerProvider2);
            } else {
                this._delegate.serializeAsPlaceholder(obj2, jsonGenerator2, serializerProvider2);
            }
        }
    }

    private static final class MultiView extends BeanPropertyWriter {
        protected final BeanPropertyWriter _delegate;
        protected final Class<?>[] _views;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        protected MultiView(com.shaded.fasterxml.jackson.databind.ser.BeanPropertyWriter r6, java.lang.Class<?>[] r7) {
            /*
                r5 = this;
                r0 = r5
                r1 = r6
                r2 = r7
                r3 = r0
                r4 = r1
                r3.<init>(r4)
                r3 = r0
                r4 = r1
                r3._delegate = r4
                r3 = r0
                r4 = r2
                r3._views = r4
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.shaded.fasterxml.jackson.databind.ser.impl.FilteredBeanPropertyWriter.MultiView.<init>(com.shaded.fasterxml.jackson.databind.ser.BeanPropertyWriter, java.lang.Class[]):void");
        }

        public MultiView rename(NameTransformer nameTransformer) {
            MultiView multiView;
            new MultiView(this._delegate.rename(nameTransformer), this._views);
            return multiView;
        }

        public void assignSerializer(JsonSerializer<Object> jsonSerializer) {
            this._delegate.assignSerializer(jsonSerializer);
        }

        public void assignNullSerializer(JsonSerializer<Object> jsonSerializer) {
            this._delegate.assignNullSerializer(jsonSerializer);
        }

        public void serializeAsField(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws Exception {
            Object obj2 = obj;
            JsonGenerator jsonGenerator2 = jsonGenerator;
            SerializerProvider serializerProvider2 = serializerProvider;
            Class<?> activeView = serializerProvider2.getActiveView();
            if (activeView != null) {
                int i = 0;
                int length = this._views.length;
                while (i < length && !this._views[i].isAssignableFrom(activeView)) {
                    i++;
                }
                if (i == length) {
                    return;
                }
            }
            this._delegate.serializeAsField(obj2, jsonGenerator2, serializerProvider2);
        }

        public void serializeAsColumn(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws Exception {
            Object obj2 = obj;
            JsonGenerator jsonGenerator2 = jsonGenerator;
            SerializerProvider serializerProvider2 = serializerProvider;
            Class<?> activeView = serializerProvider2.getActiveView();
            if (activeView != null) {
                int i = 0;
                int length = this._views.length;
                while (i < length && !this._views[i].isAssignableFrom(activeView)) {
                    i++;
                }
                if (i == length) {
                    this._delegate.serializeAsPlaceholder(obj2, jsonGenerator2, serializerProvider2);
                    return;
                }
            }
            this._delegate.serializeAsColumn(obj2, jsonGenerator2, serializerProvider2);
        }
    }
}

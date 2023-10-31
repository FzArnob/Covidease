package org.shaded.apache.http.protocol;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.shaded.apache.http.HttpException;
import org.shaded.apache.http.HttpRequest;
import org.shaded.apache.http.HttpRequestInterceptor;
import org.shaded.apache.http.HttpResponse;
import org.shaded.apache.http.HttpResponseInterceptor;

public final class BasicHttpProcessor implements HttpProcessor, HttpRequestInterceptorList, HttpResponseInterceptorList, Cloneable {
    protected final List requestInterceptors;
    protected final List responseInterceptors;

    public BasicHttpProcessor() {
        List list;
        List list2;
        new ArrayList();
        this.requestInterceptors = list;
        new ArrayList();
        this.responseInterceptors = list2;
    }

    public void addRequestInterceptor(HttpRequestInterceptor httpRequestInterceptor) {
        HttpRequestInterceptor itcp = httpRequestInterceptor;
        if (itcp != null) {
            boolean add = this.requestInterceptors.add(itcp);
        }
    }

    public void addRequestInterceptor(HttpRequestInterceptor httpRequestInterceptor, int i) {
        HttpRequestInterceptor itcp = httpRequestInterceptor;
        int index = i;
        if (itcp != null) {
            this.requestInterceptors.add(index, itcp);
        }
    }

    public void addResponseInterceptor(HttpResponseInterceptor httpResponseInterceptor, int i) {
        HttpResponseInterceptor itcp = httpResponseInterceptor;
        int index = i;
        if (itcp != null) {
            this.responseInterceptors.add(index, itcp);
        }
    }

    public void removeRequestInterceptorByClass(Class cls) {
        Class clazz = cls;
        Iterator it = this.requestInterceptors.iterator();
        while (it.hasNext()) {
            if (it.next().getClass().equals(clazz)) {
                it.remove();
            }
        }
    }

    public void removeResponseInterceptorByClass(Class cls) {
        Class clazz = cls;
        Iterator it = this.responseInterceptors.iterator();
        while (it.hasNext()) {
            if (it.next().getClass().equals(clazz)) {
                it.remove();
            }
        }
    }

    public final void addInterceptor(HttpRequestInterceptor interceptor) {
        addRequestInterceptor(interceptor);
    }

    public final void addInterceptor(HttpRequestInterceptor interceptor, int index) {
        addRequestInterceptor(interceptor, index);
    }

    public int getRequestInterceptorCount() {
        return this.requestInterceptors.size();
    }

    public HttpRequestInterceptor getRequestInterceptor(int i) {
        int index = i;
        if (index < 0 || index >= this.requestInterceptors.size()) {
            return null;
        }
        return (HttpRequestInterceptor) this.requestInterceptors.get(index);
    }

    public void clearRequestInterceptors() {
        this.requestInterceptors.clear();
    }

    public void addResponseInterceptor(HttpResponseInterceptor httpResponseInterceptor) {
        HttpResponseInterceptor itcp = httpResponseInterceptor;
        if (itcp != null) {
            boolean add = this.responseInterceptors.add(itcp);
        }
    }

    public final void addInterceptor(HttpResponseInterceptor interceptor) {
        addResponseInterceptor(interceptor);
    }

    public final void addInterceptor(HttpResponseInterceptor interceptor, int index) {
        addResponseInterceptor(interceptor, index);
    }

    public int getResponseInterceptorCount() {
        return this.responseInterceptors.size();
    }

    public HttpResponseInterceptor getResponseInterceptor(int i) {
        int index = i;
        if (index < 0 || index >= this.responseInterceptors.size()) {
            return null;
        }
        return (HttpResponseInterceptor) this.responseInterceptors.get(index);
    }

    public void clearResponseInterceptors() {
        this.responseInterceptors.clear();
    }

    public void setInterceptors(List list) {
        Throwable th;
        List list2 = list;
        if (list2 == null) {
            Throwable th2 = th;
            new IllegalArgumentException("List must not be null.");
            throw th2;
        }
        this.requestInterceptors.clear();
        this.responseInterceptors.clear();
        for (int i = 0; i < list2.size(); i++) {
            Object obj = list2.get(i);
            if (obj instanceof HttpRequestInterceptor) {
                addInterceptor((HttpRequestInterceptor) obj);
            }
            if (obj instanceof HttpResponseInterceptor) {
                addInterceptor((HttpResponseInterceptor) obj);
            }
        }
    }

    public void clearInterceptors() {
        clearRequestInterceptors();
        clearResponseInterceptors();
    }

    public void process(HttpRequest httpRequest, HttpContext httpContext) throws IOException, HttpException {
        HttpRequest request = httpRequest;
        HttpContext context = httpContext;
        for (int i = 0; i < this.requestInterceptors.size(); i++) {
            ((HttpRequestInterceptor) this.requestInterceptors.get(i)).process(request, context);
        }
    }

    public void process(HttpResponse httpResponse, HttpContext httpContext) throws IOException, HttpException {
        HttpResponse response = httpResponse;
        HttpContext context = httpContext;
        for (int i = 0; i < this.responseInterceptors.size(); i++) {
            ((HttpResponseInterceptor) this.responseInterceptors.get(i)).process(response, context);
        }
    }

    /* access modifiers changed from: protected */
    public void copyInterceptors(BasicHttpProcessor basicHttpProcessor) {
        BasicHttpProcessor target = basicHttpProcessor;
        target.requestInterceptors.clear();
        boolean addAll = target.requestInterceptors.addAll(this.requestInterceptors);
        target.responseInterceptors.clear();
        boolean addAll2 = target.responseInterceptors.addAll(this.responseInterceptors);
    }

    public BasicHttpProcessor copy() {
        BasicHttpProcessor basicHttpProcessor;
        new BasicHttpProcessor();
        BasicHttpProcessor clone = basicHttpProcessor;
        copyInterceptors(clone);
        return clone;
    }

    public Object clone() throws CloneNotSupportedException {
        BasicHttpProcessor clone = (BasicHttpProcessor) super.clone();
        copyInterceptors(clone);
        return clone;
    }
}

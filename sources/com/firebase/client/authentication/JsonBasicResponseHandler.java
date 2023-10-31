package com.firebase.client.authentication;

import com.firebase.client.utilities.encoding.JsonHelpers;
import com.shaded.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import org.shaded.apache.http.HttpEntity;
import org.shaded.apache.http.HttpResponse;
import org.shaded.apache.http.client.ResponseHandler;

class JsonBasicResponseHandler implements ResponseHandler<Map<String, Object>> {
    JsonBasicResponseHandler() {
    }

    /* JADX INFO: finally extract failed */
    public Map<String, Object> handleResponse(HttpResponse httpResponse) throws IOException {
        TypeReference typeReference;
        HttpResponse response = httpResponse;
        if (response == null) {
            return null;
        }
        HttpEntity entity = response.getEntity();
        if (entity == null) {
            return null;
        }
        InputStream is = entity.getContent();
        try {
            new TypeReference<Map<String, Object>>(this) {
                final /* synthetic */ JsonBasicResponseHandler this$0;

                {
                    this.this$0 = r5;
                }
            };
            Map<String, Object> map = (Map) JsonHelpers.getMapper().readValue(is, typeReference);
            is.close();
            return map;
        } catch (Throwable th) {
            Throwable th2 = th;
            is.close();
            throw th2;
        }
    }
}

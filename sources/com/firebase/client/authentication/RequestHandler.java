package com.firebase.client.authentication;

import java.io.IOException;
import java.util.Map;

interface RequestHandler {
    void onError(IOException iOException);

    void onResult(Map<String, Object> map);
}

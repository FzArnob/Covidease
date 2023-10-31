package com.google.appinventor.components.runtime.util;

import android.util.Log;
import com.google.appinventor.components.runtime.CloudDB;
import java.util.List;
import org.json.JSONException;
import redis.clients.jedis.JedisPubSub;

public class CloudDBJedisListener extends JedisPubSub {
    private static String LOG_TAG = "CloudDB";
    public CloudDB cloudDB;
    private Thread hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = Thread.currentThread();

    public CloudDBJedisListener(CloudDB cloudDB2) {
        this.cloudDB = cloudDB2;
    }

    public void onSubscribe(String str, int i) {
    }

    public void onMessage(String str, String str2) {
        StringBuilder sb;
        String str3 = str;
        try {
            List list = (List) JsonUtil.getObjectFromJson(str2, false);
            String str4 = (String) list.get(0);
            for (Object next : (List) list.get(1)) {
                Object obj = next;
                String jsonRepresentationIfValueFileName = JsonUtil.getJsonRepresentationIfValueFileName(next);
                String str5 = jsonRepresentationIfValueFileName;
                if (jsonRepresentationIfValueFileName == null) {
                    this.cloudDB.DataChanged(str4, obj);
                } else {
                    this.cloudDB.DataChanged(str4, str5);
                }
            }
        } catch (JSONException e) {
            JSONException jSONException = e;
            int e2 = Log.e(LOG_TAG, "onMessage: JSONException", jSONException);
            CloudDB cloudDB2 = this.cloudDB;
            new StringBuilder("System Error: ");
            cloudDB2.CloudDBError(sb.append(jSONException.getMessage()).toString());
        }
    }

    public void terminate() {
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.interrupt();
    }
}

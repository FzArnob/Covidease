package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import com.google.appinventor.components.runtime.util.CloudDBJedisListener;
import com.google.appinventor.components.runtime.util.FileUtil;
import com.google.appinventor.components.runtime.util.JsonUtil;
import com.google.appinventor.components.runtime.util.YailList;
import java.io.File;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import org.json.JSONArray;
import org.json.JSONException;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisDataException;
import redis.clients.jedis.exceptions.JedisException;
import redis.clients.jedis.exceptions.JedisNoScriptException;

@DesignerComponent(category = ComponentCategory.EXPERIMENTAL, description = "Non-visible component allowing you to store data on a Internet connected database server (using Redis software). This allows the users of your App to share data with each other. By default data will be stored in a server maintained by MIT, however you can setup and run your own server. Set the \"RedisServer\" property and \"RedisPort\" Property to access your own server.", designerHelpDescription = "Non-visible component that communicates with CloudDB server to store and retrieve information.", iconName = "images/cloudDB.png", nonVisible = true, version = 1)
@UsesLibraries(libraries = "jedis.jar")
@UsesPermissions(permissionNames = "android.permission.INTERNET,android.permission.ACCESS_NETWORK_STATE")
public final class CloudDB extends AndroidNonvisibleComponent implements Component, OnClearListener, OnDestroyListener {
    private static final String APPEND_SCRIPT = "local key = KEYS[1];local toAppend = cjson.decode(ARGV[1]);local project = ARGV[2];local currentValue = redis.call('get', project .. \":\" .. key);local newTable;local subTable = {};local subTable1 = {};if (currentValue == false) then   newTable = {};else   newTable = cjson.decode(currentValue);  if not (type(newTable) == 'table') then     return error('You can only append to a list');  end end table.insert(newTable, toAppend);local newValue = cjson.encode(newTable);redis.call('set', project .. \":\" .. key, newValue);table.insert(subTable1, newValue);table.insert(subTable, key);table.insert(subTable, subTable1);redis.call(\"publish\", project, cjson.encode(subTable));return newValue;";
    private static final String APPEND_SCRIPT_SHA1 = "d6cc0f65b29878589f00564d52c8654967e9bcf8";
    private static final String COMODO_ROOT = "-----BEGIN CERTIFICATE-----\nMIIENjCCAx6gAwIBAgIBATANBgkqhkiG9w0BAQUFADBvMQswCQYDVQQGEwJTRTEU\nMBIGA1UEChMLQWRkVHJ1c3QgQUIxJjAkBgNVBAsTHUFkZFRydXN0IEV4dGVybmFs\nIFRUUCBOZXR3b3JrMSIwIAYDVQQDExlBZGRUcnVzdCBFeHRlcm5hbCBDQSBSb290\nMB4XDTAwMDUzMDEwNDgzOFoXDTIwMDUzMDEwNDgzOFowbzELMAkGA1UEBhMCU0Ux\nFDASBgNVBAoTC0FkZFRydXN0IEFCMSYwJAYDVQQLEx1BZGRUcnVzdCBFeHRlcm5h\nbCBUVFAgTmV0d29yazEiMCAGA1UEAxMZQWRkVHJ1c3QgRXh0ZXJuYWwgQ0EgUm9v\ndDCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBALf3GjPm8gAELTngTlvt\nH7xsD821+iO2zt6bETOXpClMfZOfvUq8k+0DGuOPz+VtUFrWlymUWoCwSXrbLpX9\nuMq/NzgtHj6RQa1wVsfwTz/oMp50ysiQVOnGXw94nZpAPA6sYapeFI+eh6FqUNzX\nmk6vBbOmcZSccbNQYArHE504B4YCqOmoaSYYkKtMsE8jqzpPhNjfzp/haW+710LX\na0Tkx63ubUFfclpxCDezeWWkWaCUN/cALw3CknLa0Dhy2xSoRcRdKn23tNbE7qzN\nE0S3ySvdQwAl+mG5aWpYIxG3pzOPVnVZ9c0p10a3CitlttNCbxWyuHv77+ldU9U0\nWicCAwEAAaOB3DCB2TAdBgNVHQ4EFgQUrb2YejS0Jvf6xCZU7wO94CTLVBowCwYD\nVR0PBAQDAgEGMA8GA1UdEwEB/wQFMAMBAf8wgZkGA1UdIwSBkTCBjoAUrb2YejS0\nJvf6xCZU7wO94CTLVBqhc6RxMG8xCzAJBgNVBAYTAlNFMRQwEgYDVQQKEwtBZGRU\ncnVzdCBBQjEmMCQGA1UECxMdQWRkVHJ1c3QgRXh0ZXJuYWwgVFRQIE5ldHdvcmsx\nIjAgBgNVBAMTGUFkZFRydXN0IEV4dGVybmFsIENBIFJvb3SCAQEwDQYJKoZIhvcN\nAQEFBQADggEBALCb4IUlwtYj4g+WBpKdQZic2YR5gdkeWxQHIzZlj7DYd7usQWxH\nYINRsPkyPef89iYTx4AWpb9a/IfPeHmJIZriTAcKhjW88t5RxNKWt9x+Tu5w/Rw5\n6wwCURQtjr0W4MHfRnXnJK3s9EK0hZNwEGe6nQY1ShjTK3rMUUKhemPR5ruhxSvC\nNr4TDea9Y355e6cJDUCrat2PisP29owaQgVR1EX1n6diIWgVIEM8med8vSTYqZEX\nc4g/VhsxOBi0cQ+azcgOno4uG+GMmIPLHzHxREzGBHNJdmAPx/i9F4BrLunMTA5a\nmnkPIAou1Z5jJh5VkpTYghdae9C8x49OhgQ=\n-----END CERTIFICATE-----\n";
    private static final String COMODO_USRTRUST = "-----BEGIN CERTIFICATE-----\nMIIFdzCCBF+gAwIBAgIQE+oocFv07O0MNmMJgGFDNjANBgkqhkiG9w0BAQwFADBv\nMQswCQYDVQQGEwJTRTEUMBIGA1UEChMLQWRkVHJ1c3QgQUIxJjAkBgNVBAsTHUFk\nZFRydXN0IEV4dGVybmFsIFRUUCBOZXR3b3JrMSIwIAYDVQQDExlBZGRUcnVzdCBF\neHRlcm5hbCBDQSBSb290MB4XDTAwMDUzMDEwNDgzOFoXDTIwMDUzMDEwNDgzOFow\ngYgxCzAJBgNVBAYTAlVTMRMwEQYDVQQIEwpOZXcgSmVyc2V5MRQwEgYDVQQHEwtK\nZXJzZXkgQ2l0eTEeMBwGA1UEChMVVGhlIFVTRVJUUlVTVCBOZXR3b3JrMS4wLAYD\nVQQDEyVVU0VSVHJ1c3QgUlNBIENlcnRpZmljYXRpb24gQXV0aG9yaXR5MIICIjAN\nBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAgBJlFzYOw9sIs9CsVw127c0n00yt\nUINh4qogTQktZAnczomfzD2p7PbPwdzx07HWezcoEStH2jnGvDoZtF+mvX2do2NC\ntnbyqTsrkfjib9DsFiCQCT7i6HTJGLSR1GJk23+jBvGIGGqQIjy8/hPwhxR79uQf\njtTkUcYRZ0YIUcuGFFQ/vDP+fmyc/xadGL1RjjWmp2bIcmfbIWax1Jt4A8BQOujM\n8Ny8nkz+rwWWNR9XWrf/zvk9tyy29lTdyOcSOk2uTIq3XJq0tyA9yn8iNK5+O2hm\nAUTnAU5GU5szYPeUvlM3kHND8zLDU+/bqv50TmnHa4xgk97Exwzf4TKuzJM7UXiV\nZ4vuPVb+DNBpDxsP8yUmazNt925H+nND5X4OpWaxKXwyhGNVicQNwZNUMBkTrNN9\nN6frXTpsNVzbQdcS2qlJC9/YgIoJk2KOtWbPJYjNhLixP6Q5D9kCnusSTJV882sF\nqV4Wg8y4Z+LoE53MW4LTTLPtW//e5XOsIzstAL81VXQJSdhJWBp/kjbmUZIO8yZ9\nHE0XvMnsQybQv0FfQKlERPSZ51eHnlAfV1SoPv10Yy+xUGUJ5lhCLkMaTLTwJUdZ\n+gQek9QmRkpQgbLevni3/GcV4clXhB4PY9bpYrrWX1Uu6lzGKAgEJTm4Diup8kyX\nHAc/DVL17e8vgg8CAwEAAaOB9DCB8TAfBgNVHSMEGDAWgBStvZh6NLQm9/rEJlTv\nA73gJMtUGjAdBgNVHQ4EFgQUU3m/WqorSs9UgOHYm8Cd8rIDZsswDgYDVR0PAQH/\nBAQDAgGGMA8GA1UdEwEB/wQFMAMBAf8wEQYDVR0gBAowCDAGBgRVHSAAMEQGA1Ud\nHwQ9MDswOaA3oDWGM2h0dHA6Ly9jcmwudXNlcnRydXN0LmNvbS9BZGRUcnVzdEV4\ndGVybmFsQ0FSb290LmNybDA1BggrBgEFBQcBAQQpMCcwJQYIKwYBBQUHMAGGGWh0\ndHA6Ly9vY3NwLnVzZXJ0cnVzdC5jb20wDQYJKoZIhvcNAQEMBQADggEBAJNl9jeD\nlQ9ew4IcH9Z35zyKwKoJ8OkLJvHgwmp1ocd5yblSYMgpEg7wrQPWCcR23+WmgZWn\nRtqCV6mVksW2jwMibDN3wXsyF24HzloUQToFJBv2FAY7qCUkDrvMKnXduXBBP3zQ\nYzYhBx9G/2CkkeFnvN4ffhkUyWNnkepnB2u0j4vAbkN9w6GAbLIevFOFfdyQoaS8\nLe9Gclc1Bb+7RrtubTeZtv8jkpHGbkD4jylW6l/VXxRTrPBPYer3IsynVgviuDQf\nJtl7GQVoP7o81DgGotPmjw7jtHFtQELFhLRAlSv0ZaBIefYdgWOWnU914Ph85I6p\n0fKtirOMxyHNwu8=\n-----END CERTIFICATE-----\n";
    private static final boolean DEBUG = false;
    private static final String DST_ROOT_X3 = "-----BEGIN CERTIFICATE-----\nMIIDSjCCAjKgAwIBAgIQRK+wgNajJ7qJMDmGLvhAazANBgkqhkiG9w0BAQUFADA/\nMSQwIgYDVQQKExtEaWdpdGFsIFNpZ25hdHVyZSBUcnVzdCBDby4xFzAVBgNVBAMT\nDkRTVCBSb290IENBIFgzMB4XDTAwMDkzMDIxMTIxOVoXDTIxMDkzMDE0MDExNVow\nPzEkMCIGA1UEChMbRGlnaXRhbCBTaWduYXR1cmUgVHJ1c3QgQ28uMRcwFQYDVQQD\nEw5EU1QgUm9vdCBDQSBYMzCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEB\nAN+v6ZdQCINXtMxiZfaQguzH0yxrMMpb7NnDfcdAwRgUi+DoM3ZJKuM/IUmTrE4O\nrz5Iy2Xu/NMhD2XSKtkyj4zl93ewEnu1lcCJo6m67XMuegwGMoOifooUMM0RoOEq\nOLl5CjH9UL2AZd+3UWODyOKIYepLYYHsUmu5ouJLGiifSKOeDNoJjj4XLh7dIN9b\nxiqKqy69cK3FCxolkHRyxXtqqzTWMIn/5WgTe1QLyNau7Fqckh49ZLOMxt+/yUFw\n7BZy1SbsOFU5Q9D8/RhcQPGX69Wam40dutolucbY38EVAjqr2m7xPi71XAicPNaD\naeQQmxkqtilX4+U9m5/wAl0CAwEAAaNCMEAwDwYDVR0TAQH/BAUwAwEB/zAOBgNV\nHQ8BAf8EBAMCAQYwHQYDVR0OBBYEFMSnsaR7LHH62+FLkHX/xBVghYkQMA0GCSqG\nSIb3DQEBBQUAA4IBAQCjGiybFwBcqR7uKGY3Or+Dxz9LwwmglSBd49lZRNI+DT69\nikugdB/OEIKcdBodfpga3csTS7MgROSR6cz8faXbauX+5v3gTt23ADq1cEmv8uXr\nAvHRAosZy5Q6XkjEGB5YGV8eAlrwDPGxrancWYaLbumR9YbK+rlmM6pZW87ipxZz\nR8srzJmwN0jP41ZL9c8PDHIyh8bwRLtTcm1D9SZImlJnt1ir/md2cXjbDaJWFBM5\nJDGFoqgCWjBH4d1QB7wCCZAA62RjYJsWvIjJEubSfZGL+T0yjWW06XyxV3bqxbYo\nOb8VZRzI9neWagqNdwvYkQsEjgfbKbYK7p2CNTUQ\n-----END CERTIFICATE-----\n";
    private static final String LOG_TAG = "CloudDB";
    private static final String POP_FIRST_SCRIPT = "local key = KEYS[1];local project = ARGV[1];local currentValue = redis.call('get', project .. \":\" .. key);local decodedValue = cjson.decode(currentValue);local subTable = {};local subTable1 = {};if (type(decodedValue) == 'table') then   local removedValue = table.remove(decodedValue, 1);  local newValue = cjson.encode(decodedValue);  redis.call('set', project .. \":\" .. key, newValue);  table.insert(subTable, key);  table.insert(subTable1, newValue);  table.insert(subTable, subTable1);  redis.call(\"publish\", project, cjson.encode(subTable));  return cjson.encode(removedValue);else   return error('You can only remove elements from a list');end";
    private static final String POP_FIRST_SCRIPT_SHA1 = "ed4cb4717d157f447848fe03524da24e461028e1";
    private static final String SET_SUB_SCRIPT = "local key = KEYS[1];local value = ARGV[1];local topublish = cjson.decode(ARGV[2]);local project = ARGV[3];local newtable = {};table.insert(newtable, key);table.insert(newtable, topublish);redis.call(\"publish\", project, cjson.encode(newtable));return redis.call('set', project .. \":\" .. key, value);";
    private static final String SET_SUB_SCRIPT_SHA1 = "765978e4c340012f50733280368a0ccc4a14dfb7";
    private Jedis INSTANCE = null;
    private SSLSocketFactory SslSockFactory = null;
    private final Activity activity;
    /* access modifiers changed from: private */
    public Handler androidUIHandler;
    /* access modifiers changed from: private */
    public volatile ExecutorService background = Executors.newSingleThreadExecutor();

    /* renamed from: cm */
    private ConnectivityManager f48cm;
    /* access modifiers changed from: private */
    public volatile CloudDBJedisListener currentListener;
    /* access modifiers changed from: private */
    public volatile boolean dead = false;
    private String defaultRedisServer = null;
    private boolean importProject = false;
    private boolean isPublic = false;
    private volatile boolean listenerRunning = false;
    /* access modifiers changed from: private */
    public String projectID = "";
    private volatile int redisPort;
    private volatile String redisServer = "DEFAULT";
    /* access modifiers changed from: private */
    public volatile boolean shutdown = false;
    /* access modifiers changed from: private */
    public final List<C0624a> storeQueue;
    private String token = "";
    private boolean useDefault = true;
    private volatile boolean useSSL = true;

    static /* synthetic */ CloudDBJedisListener access$002(CloudDB cloudDB, CloudDBJedisListener cloudDBJedisListener) {
        CloudDBJedisListener cloudDBJedisListener2 = cloudDBJedisListener;
        CloudDBJedisListener cloudDBJedisListener3 = cloudDBJedisListener2;
        cloudDB.currentListener = cloudDBJedisListener3;
        return cloudDBJedisListener2;
    }

    static /* synthetic */ boolean access$202(CloudDB cloudDB, boolean z) {
        boolean z2 = z;
        boolean z3 = z2;
        cloudDB.listenerRunning = z3;
        return z2;
    }

    static /* synthetic */ ExecutorService access$902(CloudDB cloudDB, ExecutorService executorService) {
        ExecutorService executorService2 = executorService;
        ExecutorService executorService3 = executorService2;
        cloudDB.background = executorService3;
        return executorService2;
    }

    /* renamed from: com.google.appinventor.components.runtime.CloudDB$a */
    static class C0624a {
        String KZERK4FJjgj00YJ12FkBt8g0XGe7fRlOujzm0QMQzA20gzGqez6qkZCId3aKJaes;
        JSONArray hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        C0624a(String str, JSONArray jSONArray) {
            this.KZERK4FJjgj00YJ12FkBt8g0XGe7fRlOujzm0QMQzA20gzGqez6qkZCId3aKJaes = str;
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = jSONArray;
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public CloudDB(com.google.appinventor.components.runtime.ComponentContainer r7) {
        /*
            r6 = this;
            r0 = r6
            r1 = r7
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            r3 = 0
            r2.importProject = r3
            r2 = r0
            java.lang.String r3 = ""
            r2.projectID = r3
            r2 = r0
            java.lang.String r3 = ""
            r2.token = r3
            r2 = r0
            r3 = 0
            r2.isPublic = r3
            r2 = r0
            r3 = 0
            r2.dead = r3
            r2 = r0
            r3 = 0
            r2.defaultRedisServer = r3
            r2 = r0
            r3 = 1
            r2.useDefault = r3
            r2 = r0
            r3 = 0
            r2.INSTANCE = r3
            r2 = r0
            java.lang.String r3 = "DEFAULT"
            r2.redisServer = r3
            r2 = r0
            r3 = 1
            r2.useSSL = r3
            r2 = r0
            r3 = 0
            r2.shutdown = r3
            r2 = r0
            r3 = 0
            r2.SslSockFactory = r3
            r2 = r0
            r3 = 0
            r2.listenerRunning = r3
            r2 = r0
            java.util.concurrent.ExecutorService r3 = java.util.concurrent.Executors.newSingleThreadExecutor()
            r2.background = r3
            r2 = r0
            java.util.ArrayList r3 = new java.util.ArrayList
            r5 = r3
            r3 = r5
            r4 = r5
            r4.<init>()
            java.util.List r3 = java.util.Collections.synchronizedList(r3)
            r2.storeQueue = r3
            r2 = r0
            android.os.Handler r3 = new android.os.Handler
            r5 = r3
            r3 = r5
            r4 = r5
            r4.<init>()
            r2.androidUIHandler = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.activity = r3
            r2 = r0
            java.lang.String r3 = ""
            r2.projectID = r3
            r2 = r0
            java.lang.String r3 = ""
            r2.token = r3
            r2 = r0
            r3 = 6381(0x18ed, float:8.942E-42)
            r2.redisPort = r3
            r2 = r0
            r5 = r2
            r2 = r5
            r3 = r5
            com.google.appinventor.components.runtime.Form r3 = r3.form
            android.app.Activity r3 = r3.$context()
            java.lang.String r4 = "connectivity"
            java.lang.Object r3 = r3.getSystemService(r4)
            android.net.ConnectivityManager r3 = (android.net.ConnectivityManager) r3
            r2.f48cm = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.CloudDB.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    public final void Initialize() {
        if (this.currentListener == null) {
            startListener();
        }
        this.form.registerForOnClear(this);
        this.form.registerForOnDestroy(this);
    }

    private void stopListener() {
        if (this.currentListener != null) {
            this.currentListener.terminate();
            this.currentListener = null;
            this.listenerRunning = false;
        }
    }

    public final void onClear() {
        this.shutdown = true;
        flushJedis(false);
    }

    public final void onDestroy() {
        onClear();
    }

    /* access modifiers changed from: private */
    public synchronized void startListener() {
        Thread thread;
        synchronized (this) {
            if (!this.listenerRunning) {
                this.listenerRunning = true;
                new Thread(this) {
                    private /* synthetic */ CloudDB hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                    {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                    }

                    public final void run() {
                        CloudDBJedisListener cloudDBJedisListener;
                        Jedis jedis = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getJedis(true);
                        Jedis jedis2 = jedis;
                        if (jedis != null) {
                            try {
                                new CloudDBJedisListener(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
                                CloudDBJedisListener access$002 = CloudDB.access$002(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, cloudDBJedisListener);
                                jedis2.subscribe(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.currentListener, new String[]{this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.projectID});
                            } catch (Exception e) {
                                int e2 = Log.e(CloudDB.LOG_TAG, "Error in listener thread", e);
                                try {
                                    jedis2.close();
                                } catch (Exception e3) {
                                }
                                try {
                                    Thread.sleep(3000);
                                } catch (InterruptedException e4) {
                                }
                            }
                        } else {
                            try {
                                Thread.sleep(3000);
                            } catch (InterruptedException e5) {
                            }
                        }
                        boolean access$202 = CloudDB.access$202(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, false);
                        if (!this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.dead && !this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.shutdown) {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.startListener();
                        }
                    }
                };
                thread.start();
            }
        }
    }

    @DesignerProperty(defaultValue = "DEFAULT", editorType = "string")
    public final void RedisServer(String str) {
        String str2 = str;
        if (!str2.equals("DEFAULT")) {
            this.useDefault = false;
            if (!str2.equals(this.redisServer)) {
                this.redisServer = str2;
                flushJedis(true);
            }
        } else if (!this.useDefault) {
            this.useDefault = true;
            if (this.defaultRedisServer != null) {
                this.redisServer = this.defaultRedisServer;
            }
            flushJedis(true);
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The Redis Server to use to store data. A setting of \"DEFAULT\" means that the MIT server will be used.")
    public final String RedisServer() {
        if (this.redisServer.equals(this.defaultRedisServer)) {
            return "DEFAULT";
        }
        return this.redisServer;
    }

    @DesignerProperty(editorType = "string")
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The Default Redis Server to use.", userVisible = false)
    public final void DefaultRedisServer(String str) {
        String str2 = str;
        this.defaultRedisServer = str2;
        if (this.useDefault) {
            this.redisServer = str2;
        }
    }

    @DesignerProperty(defaultValue = "6381", editorType = "integer")
    public final void RedisPort(int i) {
        int i2 = i;
        if (i2 != this.redisPort) {
            this.redisPort = i2;
            flushJedis(true);
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The Redis Server port to use. Defaults to 6381")
    public final int RedisPort() {
        return this.redisPort;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Gets the ProjectID for this CloudDB project.")
    public final String ProjectID() {
        checkProjectIDNotBlank();
        return this.projectID;
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    public final void ProjectID(String str) {
        Throwable th;
        String str2 = str;
        if (!this.projectID.equals(str2)) {
            this.projectID = str2;
        }
        if (this.projectID.equals("")) {
            Throwable th2 = th;
            new RuntimeException("CloudDB ProjectID property cannot be blank.");
            throw th2;
        }
    }

    @DesignerProperty(defaultValue = "", editorType = "string")
    public final void Token(String str) {
        Throwable th;
        String str2 = str;
        if (!this.token.equals(str2)) {
            this.token = str2;
        }
        if (this.token.equals("")) {
            Throwable th2 = th;
            new RuntimeException("CloudDB Token property cannot be blank.");
            throw th2;
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "This field contains the authentication token used to login to the backed Redis server. For the \"DEFAULT\" server, do not edit this value, the system will fill it in for you. A system administrator may also provide a special value to you which can be used to share data between multiple projects from multiple people. If using your own Redis server, set a password in the server's config and enter it here.", userVisible = false)
    public final String Token() {
        checkProjectIDNotBlank();
        return this.token;
    }

    @DesignerProperty(defaultValue = "True", editorType = "boolean")
    public final void UseSSL(boolean z) {
        boolean z2 = z;
        if (this.useSSL != z2) {
            this.useSSL = z2;
            flushJedis(true);
        }
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Set to true to use SSL to talk to CloudDB/Redis server. This should be set to True for the \"DEFAULT\" server.", userVisible = false)
    public final boolean UseSSL() {
        return this.useSSL;
    }

    @SimpleFunction(description = "Store a value at a tag.")
    public final void StoreValue(String str, Object obj) {
        String str2;
        JSONArray jSONArray;
        Throwable th;
        Object obj2;
        Runnable runnable;
        Throwable th2;
        String str3 = str;
        Object obj3 = obj;
        checkProjectIDNotBlank();
        NetworkInfo activeNetworkInfo = this.f48cm.getActiveNetworkInfo();
        boolean z = activeNetworkInfo != null && activeNetworkInfo.isConnected();
        if (obj3 != null) {
            try {
                String obj4 = obj3.toString();
                String str4 = obj4;
                if (obj4.startsWith("file:///") || str4.startsWith("/storage")) {
                    str2 = JsonUtil.getJsonRepresentation(readFile(str4));
                } else {
                    str2 = JsonUtil.getJsonRepresentation(obj3);
                }
            } catch (JSONException e) {
                Throwable th3 = th2;
                new YailRuntimeError("Value failed to convert to JSON.", "JSON Creation Error.");
                throw th3;
            }
        } else {
            str2 = "";
        }
        if (z) {
            List<C0624a> list = this.storeQueue;
            List<C0624a> list2 = list;
            synchronized (list) {
                boolean z2 = false;
                try {
                    if (this.storeQueue.size() == 0) {
                        z2 = true;
                    }
                    JSONArray jSONArray2 = jSONArray;
                    new JSONArray();
                    JSONArray jSONArray3 = jSONArray2;
                    JSONArray put = jSONArray3.put(0, str2);
                    new C0624a(str3, jSONArray3);
                    boolean add = this.storeQueue.add(obj2);
                    if (z2) {
                        new Runnable(this) {
                            private /* synthetic */ CloudDB hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                            {
                                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                            }

                            public final void run() {
                                List list;
                                C0624a aVar;
                                StringBuilder sb;
                                StringBuilder sb2;
                                JSONArray jSONArray = null;
                                String str = null;
                                String str2 = null;
                                while (true) {
                                    try {
                                        List access$600 = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.storeQueue;
                                        list = access$600;
                                        synchronized (access$600) {
                                            if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.storeQueue.size() == 0) {
                                                aVar = null;
                                            } else {
                                                aVar = (C0624a) this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.storeQueue.remove(0);
                                            }
                                            if (aVar == null) {
                                                break;
                                            }
                                            String str3 = aVar.KZERK4FJjgj00YJ12FkBt8g0XGe7fRlOujzm0QMQzA20gzGqez6qkZCId3aKJaes;
                                            JSONArray jSONArray2 = aVar.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                                            if (str == null) {
                                                str = str3;
                                                jSONArray = jSONArray2;
                                                str2 = jSONArray2.getString(0);
                                            } else if (str.equals(str3)) {
                                                str2 = jSONArray2.getString(0);
                                                JSONArray put = jSONArray.put(str2);
                                            } else {
                                                try {
                                                    String jSONArray3 = jSONArray.toString();
                                                    CloudDB cloudDB = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                                                    String[] strArr = new String[4];
                                                    strArr[0] = str;
                                                    String[] strArr2 = strArr;
                                                    strArr2[1] = str2;
                                                    String[] strArr3 = strArr2;
                                                    strArr3[2] = jSONArray3;
                                                    String[] strArr4 = strArr3;
                                                    strArr4[3] = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.projectID;
                                                    Object jEval = cloudDB.jEval(CloudDB.SET_SUB_SCRIPT, CloudDB.SET_SUB_SCRIPT_SHA1, 1, strArr4);
                                                    str = str3;
                                                    jSONArray = jSONArray2;
                                                    str2 = jSONArray2.getString(0);
                                                } catch (JedisException e) {
                                                    JedisException jedisException = e;
                                                    CloudDB cloudDB2 = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                                                    new StringBuilder();
                                                    cloudDB2.CloudDBError(sb2.append(jedisException.getMessage()).toString());
                                                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.flushJedis(true);
                                                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.storeQueue.clear();
                                                    return;
                                                }
                                            }
                                        }
                                    } catch (Exception e2) {
                                        int e3 = Log.e(CloudDB.LOG_TAG, "Exception in store worker!", e2);
                                        return;
                                    } catch (Throwable th) {
                                        Throwable th2 = th;
                                        List list2 = list;
                                        throw th2;
                                    }
                                }
                                if (str != null) {
                                    try {
                                        String jSONArray4 = jSONArray.toString();
                                        CloudDB cloudDB3 = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                                        String[] strArr5 = new String[4];
                                        strArr5[0] = str;
                                        String[] strArr6 = strArr5;
                                        strArr6[1] = str2;
                                        String[] strArr7 = strArr6;
                                        strArr7[2] = jSONArray4;
                                        String[] strArr8 = strArr7;
                                        strArr8[3] = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.projectID;
                                        Object jEval2 = cloudDB3.jEval(CloudDB.SET_SUB_SCRIPT, CloudDB.SET_SUB_SCRIPT_SHA1, 1, strArr8);
                                    } catch (JedisException e4) {
                                        JedisException jedisException2 = e4;
                                        CloudDB cloudDB4 = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                                        new StringBuilder();
                                        cloudDB4.CloudDBError(sb.append(jedisException2.getMessage()).toString());
                                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.flushJedis(true);
                                    }
                                }
                            }
                        };
                        Future<?> submit = this.background.submit(runnable);
                    }
                } catch (JSONException e2) {
                    Throwable th4 = th;
                    new YailRuntimeError("JSON Error putting value.", "value is not convertable");
                    throw th4;
                } catch (Throwable th5) {
                    Throwable th6 = th5;
                    List<C0624a> list3 = list2;
                    throw th6;
                }
            }
        } else {
            CloudDBError("Cannot store values off-line.");
        }
    }

    @SimpleFunction(description = "Get the Value for a tag, doesn't return the value but will cause a GotValue event to fire when the value is looked up.")
    public final void GetValue(String str, Object obj) {
        AtomicReference atomicReference;
        Runnable runnable;
        String str2 = str;
        Object obj2 = obj;
        checkProjectIDNotBlank();
        new AtomicReference();
        AtomicReference atomicReference2 = atomicReference;
        NetworkInfo activeNetworkInfo = this.f48cm.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            final String str3 = str2;
            final AtomicReference atomicReference3 = atomicReference2;
            final Object obj3 = obj2;
            new Runnable(this) {
                final /* synthetic */ CloudDB hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r8;
                }

                public final void run() {
                    StringBuilder sb;
                    StringBuilder sb2;
                    StringBuilder sb3;
                    StringBuilder sb4;
                    Runnable runnable;
                    Jedis jedis = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getJedis();
                    try {
                        new StringBuilder();
                        String str = jedis.get(sb4.append(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.projectID).append(":").append(str3).toString());
                        String str2 = str;
                        if (str != null) {
                            String jsonRepresentationIfValueFileName = JsonUtil.getJsonRepresentationIfValueFileName(str2);
                            String str3 = jsonRepresentationIfValueFileName;
                            if (jsonRepresentationIfValueFileName != null) {
                                atomicReference3.set(str3);
                            } else {
                                atomicReference3.set(str2);
                            }
                        } else {
                            atomicReference3.set(JsonUtil.getJsonRepresentation(obj3));
                        }
                        new Runnable(this) {
                            private /* synthetic */ C06185 hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                            {
                                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                            }

                            public final void run() {
                                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.GotValue(str3, atomicReference3.get());
                            }
                        };
                        boolean post = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.androidUIHandler.post(runnable);
                    } catch (JSONException e) {
                        CloudDB cloudDB = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                        new StringBuilder("JSON conversion error for ");
                        cloudDB.CloudDBError(sb3.append(str3).toString());
                    } catch (NullPointerException e2) {
                        CloudDB cloudDB2 = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                        new StringBuilder("System Error getting tag ");
                        cloudDB2.CloudDBError(sb2.append(str3).toString());
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.flushJedis(true);
                    } catch (JedisException e3) {
                        JedisException jedisException = e3;
                        int e4 = Log.e(CloudDB.LOG_TAG, "Exception in GetValue", jedisException);
                        CloudDB cloudDB3 = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                        new StringBuilder();
                        cloudDB3.CloudDBError(sb.append(jedisException.getMessage()).toString());
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.flushJedis(true);
                    }
                }
            };
            Future<?> submit = this.background.submit(runnable);
            return;
        }
        CloudDBError("Cannot fetch variables while off-line.");
    }

    @SimpleFunction(description = "returns True if we are on the network and will likely be able to connect to the CloudDB server.")
    public final boolean CloudConnected() {
        NetworkInfo activeNetworkInfo = this.f48cm.getActiveNetworkInfo();
        NetworkInfo networkInfo = activeNetworkInfo;
        if (activeNetworkInfo == null || !networkInfo.isConnected()) {
            return false;
        }
        return true;
    }

    @SimpleEvent(description = "Event triggered by the \"RemoveFirstFromList\" function. The argument \"value\" is the object that was the first in the list, and which is now removed.")
    public final void FirstRemoved(Object obj) {
        Runnable runnable;
        Object obj2 = obj;
        checkProjectIDNotBlank();
        if (obj2 != null) {
            try {
                if (obj2 instanceof String) {
                    obj2 = JsonUtil.getObjectFromJson((String) obj2, true);
                }
            } catch (JSONException e) {
                int e2 = Log.e(LOG_TAG, "error while converting to JSON...", e);
                return;
            }
        }
        final Object obj3 = obj2;
        new Runnable(this) {
            private /* synthetic */ CloudDB hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
            }

            public final void run() {
                boolean dispatchEvent = EventDispatcher.dispatchEvent(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "FirstRemoved", obj3);
            }
        };
        boolean post = this.androidUIHandler.post(runnable);
    }

    @SimpleFunction(description = "Return the first element of a list and atomically remove it. If two devices use this function simultaneously, one will get the first element and the the other will get the second element, or an error if there is no available element. When the element is available, the \"FirstRemoved\" event will be triggered.")
    public final void RemoveFirstFromList(String str) {
        Runnable runnable;
        checkProjectIDNotBlank();
        final String str2 = str;
        new Runnable(this) {
            private /* synthetic */ CloudDB hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
            }

            public final void run() {
                StringBuilder sb;
                Jedis jedis = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getJedis();
                try {
                    CloudDB cloudDB = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                    CloudDB cloudDB2 = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                    String[] strArr = new String[2];
                    strArr[0] = str2;
                    String[] strArr2 = strArr;
                    strArr2[1] = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.projectID;
                    cloudDB.FirstRemoved(cloudDB2.jEval(CloudDB.POP_FIRST_SCRIPT, CloudDB.POP_FIRST_SCRIPT_SHA1, 1, strArr2));
                } catch (JedisException e) {
                    JedisException jedisException = e;
                    CloudDB cloudDB3 = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                    new StringBuilder();
                    cloudDB3.CloudDBError(sb.append(jedisException.getMessage()).toString());
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.flushJedis(true);
                }
            }
        };
        Future<?> submit = this.background.submit(runnable);
    }

    /* JADX WARNING: type inference failed for: r10v0 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    @com.google.appinventor.components.annotations.SimpleFunction(description = "Append a value to the end of a list atomically. If two devices use this function simultaneously, both will be appended and no data lost.")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void AppendValueToList(java.lang.String r12, java.lang.Object r13) {
        /*
            r11 = this;
            r0 = r11
            r1 = r12
            r2 = r13
            r4 = r0
            r4.checkProjectIDNotBlank()
            java.lang.Object r4 = new java.lang.Object
            r10 = r4
            r4 = r10
            r5 = r10
            r5.<init>()
            r3 = r4
            r4 = r2
            if (r4 == 0) goto L_0x0019
            r4 = r2
            java.lang.String r4 = com.google.appinventor.components.runtime.util.JsonUtil.getJsonRepresentation(r4)     // Catch:{ JSONException -> 0x0030 }
            r3 = r4
        L_0x0019:
            r4 = r3
            java.lang.String r4 = (java.lang.String) r4
            r2 = r4
            r4 = r0
            java.util.concurrent.ExecutorService r4 = r4.background
            com.google.appinventor.components.runtime.CloudDB$8 r5 = new com.google.appinventor.components.runtime.CloudDB$8
            r10 = r5
            r5 = r10
            r6 = r10
            r7 = r0
            r8 = r1
            r9 = r2
            r6.<init>(r7, r8, r9)
            java.util.concurrent.Future r4 = r4.submit(r5)
            return
        L_0x0030:
            r4 = move-exception
            com.google.appinventor.components.runtime.errors.YailRuntimeError r4 = new com.google.appinventor.components.runtime.errors.YailRuntimeError
            r10 = r4
            r4 = r10
            r5 = r10
            java.lang.String r6 = "Value failed to convert to JSON."
            java.lang.String r7 = "JSON Creation Error."
            r5.<init>(r6, r7)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.CloudDB.AppendValueToList(java.lang.String, java.lang.Object):void");
    }

    @SimpleEvent
    public final void GotValue(String str, Object obj) {
        Throwable th;
        StringBuilder sb;
        String str2 = str;
        Object obj2 = obj;
        checkProjectIDNotBlank();
        if (obj2 == null) {
            new StringBuilder("Trouble getting ");
            CloudDBError(sb.append(str2).append(" from the server.").toString());
            return;
        }
        if (obj2 != null) {
            try {
                if (obj2 instanceof String) {
                    obj2 = JsonUtil.getObjectFromJson((String) obj2, true);
                }
            } catch (JSONException e) {
                Throwable th2 = th;
                new YailRuntimeError("Value failed to convert from JSON.", "JSON Retrieval Error.");
                throw th2;
            }
        }
        Object[] objArr = new Object[2];
        objArr[0] = str2;
        Object[] objArr2 = objArr;
        objArr2[1] = obj2;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "GotValue", objArr2);
    }

    @SimpleFunction(description = "Remove the tag from CloudDB")
    public final void ClearTag(String str) {
        Runnable runnable;
        checkProjectIDNotBlank();
        final String str2 = str;
        new Runnable(this) {
            private /* synthetic */ CloudDB hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
            }

            public final void run() {
                StringBuilder sb;
                StringBuilder sb2;
                try {
                    Jedis jedis = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getJedis();
                    new StringBuilder();
                    Long del = jedis.del(sb2.append(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.projectID).append(":").append(str2).toString());
                } catch (Exception e) {
                    Exception exc = e;
                    CloudDB cloudDB = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                    new StringBuilder();
                    cloudDB.CloudDBError(sb.append(exc.getMessage()).toString());
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.flushJedis(true);
                }
            }
        };
        Future<?> submit = this.background.submit(runnable);
    }

    @SimpleFunction(description = "Get the list of tags for this application. When complete a \"TagList\" event will be triggered with the list of known tags.")
    public final void GetTagList() {
        Runnable runnable;
        checkProjectIDNotBlank();
        NetworkInfo activeNetworkInfo = this.f48cm.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            new Runnable(this) {
                final /* synthetic */ CloudDB hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                }

                public final void run() {
                    StringBuilder sb;
                    StringBuilder sb2;
                    List list;
                    Runnable runnable;
                    StringBuilder sb3;
                    Jedis jedis = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getJedis();
                    try {
                        new StringBuilder();
                        new ArrayList(jedis.keys(sb2.append(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.projectID).append(":*").toString()));
                        List list2 = list;
                        for (int i = 0; i < list2.size(); i++) {
                            new StringBuilder();
                            Object obj = list2.set(i, ((String) list2.get(i)).substring(sb3.append(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.projectID).append(":").toString().length()));
                        }
                        final List list3 = list2;
                        new Runnable(this) {
                            private /* synthetic */ C061210 hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                            {
                                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
                            }

                            public final void run() {
                                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.TagList(list3);
                            }
                        };
                        boolean post = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.androidUIHandler.post(runnable);
                    } catch (JedisException e) {
                        JedisException jedisException = e;
                        CloudDB cloudDB = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                        new StringBuilder();
                        cloudDB.CloudDBError(sb.append(jedisException.getMessage()).toString());
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.flushJedis(true);
                    }
                }
            };
            Future<?> submit = this.background.submit(runnable);
            return;
        }
        CloudDBError("Not connected to the Internet, cannot list tags");
    }

    @SimpleEvent(description = "Event triggered when we have received the list of known tags. Used with the \"GetTagList\" Function.")
    public final void TagList(List<String> list) {
        checkProjectIDNotBlank();
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "TagList", list);
    }

    @SimpleEvent
    public final void DataChanged(String str, Object obj) {
        Runnable runnable;
        Throwable th;
        String str2 = str;
        Object obj2 = obj;
        Object obj3 = "";
        if (obj2 != null) {
            try {
                if (obj2 instanceof String) {
                    obj3 = JsonUtil.getObjectFromJson((String) obj2, true);
                }
            } catch (JSONException e) {
                Throwable th2 = th;
                new YailRuntimeError("Value failed to convert from JSON.", "JSON Retrieval Error.");
                throw th2;
            }
        }
        final String str3 = str2;
        final Object obj4 = obj3;
        new Runnable(this) {
            private /* synthetic */ CloudDB hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7;
            }

            public final void run() {
                CloudDB cloudDB = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                Object[] objArr = new Object[2];
                objArr[0] = str3;
                Object[] objArr2 = objArr;
                objArr2[1] = obj4;
                boolean dispatchEvent = EventDispatcher.dispatchEvent(cloudDB, "DataChanged", objArr2);
            }
        };
        boolean post = this.androidUIHandler.post(runnable);
    }

    @SimpleEvent(description = "Indicates that an error occurred while communicating with the CloudDB Redis server.")
    public final void CloudDBError(String str) {
        Runnable runnable;
        String str2 = str;
        int e = Log.e(LOG_TAG, str2);
        final String str3 = str2;
        new Runnable(this) {
            private /* synthetic */ CloudDB hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
            }

            public final void run() {
                Notifier notifier;
                StringBuilder sb;
                if (!EventDispatcher.dispatchEvent(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "CloudDBError", str3)) {
                    new Notifier(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.form);
                    new StringBuilder("CloudDBError: ");
                    notifier.ShowAlert(sb.append(str3).toString());
                }
            }
        };
        boolean post = this.androidUIHandler.post(runnable);
    }

    private void checkProjectIDNotBlank() {
        Throwable th;
        Throwable th2;
        if (this.projectID.equals("")) {
            Throwable th3 = th2;
            new RuntimeException("CloudDB ProjectID property cannot be blank.");
            throw th3;
        } else if (this.token.equals("")) {
            Throwable th4 = th;
            new RuntimeException("CloudDB Token property cannot be blank");
            throw th4;
        }
    }

    public final Jedis getJedis(boolean z) {
        StringBuilder sb;
        StringBuilder sb2;
        Jedis jedis;
        Jedis jedis2;
        Jedis jedis3;
        boolean z2 = z;
        if (this.dead) {
            return null;
        }
        try {
            if (this.useSSL) {
                ensureSslSockFactory();
                new Jedis(this.redisServer, this.redisPort, true, this.SslSockFactory, (SSLParameters) null, (HostnameVerifier) null);
                jedis2 = jedis3;
            } else {
                new Jedis(this.redisServer, this.redisPort, false);
                jedis2 = jedis;
            }
            if (this.token.substring(0, 1).equals("%")) {
                String auth = jedis2.auth(this.token.substring(1));
            } else {
                String auth2 = jedis2.auth(this.token);
            }
            return jedis2;
        } catch (JedisConnectionException e) {
            JedisConnectionException jedisConnectionException = e;
            int e2 = Log.e(LOG_TAG, "in getJedis()", jedisConnectionException);
            new StringBuilder();
            CloudDBError(sb2.append(jedisConnectionException.getMessage()).toString());
            return null;
        } catch (JedisDataException e3) {
            JedisDataException jedisDataException = e3;
            int e4 = Log.e(LOG_TAG, "in getJedis()", jedisDataException);
            new StringBuilder();
            CloudDBError(sb.append(jedisDataException.getMessage()).append(" CloudDB disabled, restart to re-enable.").toString());
            this.dead = true;
            return null;
        }
    }

    public final synchronized Jedis getJedis() {
        Jedis jedis;
        synchronized (this) {
            if (this.INSTANCE == null) {
                this.INSTANCE = getJedis(true);
            }
            jedis = this.INSTANCE;
        }
        return jedis;
    }

    /* access modifiers changed from: private */
    public void flushJedis(boolean z) {
        Runnable runnable;
        boolean z2 = z;
        if (this.INSTANCE != null) {
            try {
                this.INSTANCE.close();
            } catch (Exception e) {
            }
            this.INSTANCE = null;
            new Runnable(this) {
                private /* synthetic */ CloudDB hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                }

                public final void run() {
                    List<Runnable> shutdownNow = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.background.shutdownNow();
                    ExecutorService access$902 = CloudDB.access$902(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, Executors.newSingleThreadExecutor());
                }
            };
            boolean post = this.androidUIHandler.post(runnable);
            stopListener();
            if (z2) {
                startListener();
            }
        }
    }

    private YailList readFile(String str) {
        Throwable th;
        StringBuilder sb;
        Throwable th2;
        String str2 = str;
        String str3 = str2;
        try {
            if (str2.startsWith("file://")) {
                str2 = str2.substring(7);
            }
            if (!str2.startsWith("/")) {
                Throwable th3 = th2;
                new YailRuntimeError("Invalid fileName, was ".concat(String.valueOf(str3)), "ReadFrom");
                throw th3;
            }
            String fileExtension = getFileExtension(str2);
            String encodeToString = Base64.encodeToString(FileUtil.readFile(str2), 0);
            Object[] objArr = new Object[2];
            Object[] objArr2 = objArr;
            objArr[0] = ".".concat(String.valueOf(fileExtension));
            objArr2[1] = encodeToString;
            return YailList.makeList(objArr2);
        } catch (Exception e) {
            Exception exc = e;
            Throwable th4 = th;
            new StringBuilder();
            new YailRuntimeError(sb.append(exc.getMessage()).toString(), "Read");
            throw th4;
        }
    }

    private String getFileExtension(String str) {
        File file;
        new File(str);
        String name = file.getName();
        String str2 = name;
        int lastIndexOf = name.lastIndexOf(".");
        return lastIndexOf == -1 ? "" : str2.substring(lastIndexOf + 1);
    }

    public final ExecutorService getBackground() {
        return this.background;
    }

    public final Object jEval(String str, String str2, int i, String... strArr) throws JedisException {
        String str3 = str;
        int i2 = i;
        String[] strArr2 = strArr;
        Jedis jedis = getJedis();
        try {
            return jedis.evalsha(str2, i2, strArr2);
        } catch (JedisNoScriptException e) {
            return jedis.eval(str3, i2, strArr2);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v13, resolved type: java.io.ByteArrayInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v18, resolved type: java.io.ByteArrayInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v23, resolved type: java.io.ByteArrayInputStream} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void ensureSslSockFactory() {
        /*
            r17 = this;
            r0 = r17
            r15 = r17
            monitor-enter(r15)
            r10 = r0
            javax.net.ssl.SSLSocketFactory r10 = r10.SslSockFactory     // Catch:{ all -> 0x013d }
            if (r10 == 0) goto L_0x000c
        L_0x000a:
            monitor-exit(r15)
            return
        L_0x000c:
            java.lang.String r10 = "X.509"
            java.security.cert.CertificateFactory r10 = java.security.cert.CertificateFactory.getInstance(r10)     // Catch:{ Exception -> 0x0109 }
            r1 = r10
            java.io.ByteArrayInputStream r10 = new java.io.ByteArrayInputStream     // Catch:{ Exception -> 0x0109 }
            r16 = r10
            r10 = r16
            r11 = r16
            java.lang.String r12 = "-----BEGIN CERTIFICATE-----\nMIIENjCCAx6gAwIBAgIBATANBgkqhkiG9w0BAQUFADBvMQswCQYDVQQGEwJTRTEU\nMBIGA1UEChMLQWRkVHJ1c3QgQUIxJjAkBgNVBAsTHUFkZFRydXN0IEV4dGVybmFs\nIFRUUCBOZXR3b3JrMSIwIAYDVQQDExlBZGRUcnVzdCBFeHRlcm5hbCBDQSBSb290\nMB4XDTAwMDUzMDEwNDgzOFoXDTIwMDUzMDEwNDgzOFowbzELMAkGA1UEBhMCU0Ux\nFDASBgNVBAoTC0FkZFRydXN0IEFCMSYwJAYDVQQLEx1BZGRUcnVzdCBFeHRlcm5h\nbCBUVFAgTmV0d29yazEiMCAGA1UEAxMZQWRkVHJ1c3QgRXh0ZXJuYWwgQ0EgUm9v\ndDCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBALf3GjPm8gAELTngTlvt\nH7xsD821+iO2zt6bETOXpClMfZOfvUq8k+0DGuOPz+VtUFrWlymUWoCwSXrbLpX9\nuMq/NzgtHj6RQa1wVsfwTz/oMp50ysiQVOnGXw94nZpAPA6sYapeFI+eh6FqUNzX\nmk6vBbOmcZSccbNQYArHE504B4YCqOmoaSYYkKtMsE8jqzpPhNjfzp/haW+710LX\na0Tkx63ubUFfclpxCDezeWWkWaCUN/cALw3CknLa0Dhy2xSoRcRdKn23tNbE7qzN\nE0S3ySvdQwAl+mG5aWpYIxG3pzOPVnVZ9c0p10a3CitlttNCbxWyuHv77+ldU9U0\nWicCAwEAAaOB3DCB2TAdBgNVHQ4EFgQUrb2YejS0Jvf6xCZU7wO94CTLVBowCwYD\nVR0PBAQDAgEGMA8GA1UdEwEB/wQFMAMBAf8wgZkGA1UdIwSBkTCBjoAUrb2YejS0\nJvf6xCZU7wO94CTLVBqhc6RxMG8xCzAJBgNVBAYTAlNFMRQwEgYDVQQKEwtBZGRU\ncnVzdCBBQjEmMCQGA1UECxMdQWRkVHJ1c3QgRXh0ZXJuYWwgVFRQIE5ldHdvcmsx\nIjAgBgNVBAMTGUFkZFRydXN0IEV4dGVybmFsIENBIFJvb3SCAQEwDQYJKoZIhvcN\nAQEFBQADggEBALCb4IUlwtYj4g+WBpKdQZic2YR5gdkeWxQHIzZlj7DYd7usQWxH\nYINRsPkyPef89iYTx4AWpb9a/IfPeHmJIZriTAcKhjW88t5RxNKWt9x+Tu5w/Rw5\n6wwCURQtjr0W4MHfRnXnJK3s9EK0hZNwEGe6nQY1ShjTK3rMUUKhemPR5ruhxSvC\nNr4TDea9Y355e6cJDUCrat2PisP29owaQgVR1EX1n6diIWgVIEM8med8vSTYqZEX\nc4g/VhsxOBi0cQ+azcgOno4uG+GMmIPLHzHxREzGBHNJdmAPx/i9F4BrLunMTA5a\nmnkPIAou1Z5jJh5VkpTYghdae9C8x49OhgQ=\n-----END CERTIFICATE-----\n"
            java.lang.String r13 = "UTF-8"
            byte[] r12 = r12.getBytes(r13)     // Catch:{ Exception -> 0x0109 }
            r11.<init>(r12)     // Catch:{ Exception -> 0x0109 }
            r2 = r10
            r10 = r1
            r11 = r2
            java.security.cert.Certificate r10 = r10.generateCertificate(r11)     // Catch:{ Exception -> 0x0109 }
            r3 = r10
            r10 = r2
            r10.close()     // Catch:{ Exception -> 0x0109 }
            java.io.ByteArrayInputStream r10 = new java.io.ByteArrayInputStream     // Catch:{ Exception -> 0x0109 }
            r16 = r10
            r10 = r16
            r11 = r16
            java.lang.String r12 = "-----BEGIN CERTIFICATE-----\nMIIFdzCCBF+gAwIBAgIQE+oocFv07O0MNmMJgGFDNjANBgkqhkiG9w0BAQwFADBv\nMQswCQYDVQQGEwJTRTEUMBIGA1UEChMLQWRkVHJ1c3QgQUIxJjAkBgNVBAsTHUFk\nZFRydXN0IEV4dGVybmFsIFRUUCBOZXR3b3JrMSIwIAYDVQQDExlBZGRUcnVzdCBF\neHRlcm5hbCBDQSBSb290MB4XDTAwMDUzMDEwNDgzOFoXDTIwMDUzMDEwNDgzOFow\ngYgxCzAJBgNVBAYTAlVTMRMwEQYDVQQIEwpOZXcgSmVyc2V5MRQwEgYDVQQHEwtK\nZXJzZXkgQ2l0eTEeMBwGA1UEChMVVGhlIFVTRVJUUlVTVCBOZXR3b3JrMS4wLAYD\nVQQDEyVVU0VSVHJ1c3QgUlNBIENlcnRpZmljYXRpb24gQXV0aG9yaXR5MIICIjAN\nBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAgBJlFzYOw9sIs9CsVw127c0n00yt\nUINh4qogTQktZAnczomfzD2p7PbPwdzx07HWezcoEStH2jnGvDoZtF+mvX2do2NC\ntnbyqTsrkfjib9DsFiCQCT7i6HTJGLSR1GJk23+jBvGIGGqQIjy8/hPwhxR79uQf\njtTkUcYRZ0YIUcuGFFQ/vDP+fmyc/xadGL1RjjWmp2bIcmfbIWax1Jt4A8BQOujM\n8Ny8nkz+rwWWNR9XWrf/zvk9tyy29lTdyOcSOk2uTIq3XJq0tyA9yn8iNK5+O2hm\nAUTnAU5GU5szYPeUvlM3kHND8zLDU+/bqv50TmnHa4xgk97Exwzf4TKuzJM7UXiV\nZ4vuPVb+DNBpDxsP8yUmazNt925H+nND5X4OpWaxKXwyhGNVicQNwZNUMBkTrNN9\nN6frXTpsNVzbQdcS2qlJC9/YgIoJk2KOtWbPJYjNhLixP6Q5D9kCnusSTJV882sF\nqV4Wg8y4Z+LoE53MW4LTTLPtW//e5XOsIzstAL81VXQJSdhJWBp/kjbmUZIO8yZ9\nHE0XvMnsQybQv0FfQKlERPSZ51eHnlAfV1SoPv10Yy+xUGUJ5lhCLkMaTLTwJUdZ\n+gQek9QmRkpQgbLevni3/GcV4clXhB4PY9bpYrrWX1Uu6lzGKAgEJTm4Diup8kyX\nHAc/DVL17e8vgg8CAwEAAaOB9DCB8TAfBgNVHSMEGDAWgBStvZh6NLQm9/rEJlTv\nA73gJMtUGjAdBgNVHQ4EFgQUU3m/WqorSs9UgOHYm8Cd8rIDZsswDgYDVR0PAQH/\nBAQDAgGGMA8GA1UdEwEB/wQFMAMBAf8wEQYDVR0gBAowCDAGBgRVHSAAMEQGA1Ud\nHwQ9MDswOaA3oDWGM2h0dHA6Ly9jcmwudXNlcnRydXN0LmNvbS9BZGRUcnVzdEV4\ndGVybmFsQ0FSb290LmNybDA1BggrBgEFBQcBAQQpMCcwJQYIKwYBBQUHMAGGGWh0\ndHA6Ly9vY3NwLnVzZXJ0cnVzdC5jb20wDQYJKoZIhvcNAQEMBQADggEBAJNl9jeD\nlQ9ew4IcH9Z35zyKwKoJ8OkLJvHgwmp1ocd5yblSYMgpEg7wrQPWCcR23+WmgZWn\nRtqCV6mVksW2jwMibDN3wXsyF24HzloUQToFJBv2FAY7qCUkDrvMKnXduXBBP3zQ\nYzYhBx9G/2CkkeFnvN4ffhkUyWNnkepnB2u0j4vAbkN9w6GAbLIevFOFfdyQoaS8\nLe9Gclc1Bb+7RrtubTeZtv8jkpHGbkD4jylW6l/VXxRTrPBPYer3IsynVgviuDQf\nJtl7GQVoP7o81DgGotPmjw7jtHFtQELFhLRAlSv0ZaBIefYdgWOWnU914Ph85I6p\n0fKtirOMxyHNwu8=\n-----END CERTIFICATE-----\n"
            java.lang.String r13 = "UTF-8"
            byte[] r12 = r12.getBytes(r13)     // Catch:{ Exception -> 0x0109 }
            r11.<init>(r12)     // Catch:{ Exception -> 0x0109 }
            r2 = r10
            r10 = r1
            r11 = r2
            java.security.cert.Certificate r10 = r10.generateCertificate(r11)     // Catch:{ Exception -> 0x0109 }
            r4 = r10
            r10 = r2
            r10.close()     // Catch:{ Exception -> 0x0109 }
            java.io.ByteArrayInputStream r10 = new java.io.ByteArrayInputStream     // Catch:{ Exception -> 0x0109 }
            r16 = r10
            r10 = r16
            r11 = r16
            java.lang.String r12 = "-----BEGIN CERTIFICATE-----\nMIIDSjCCAjKgAwIBAgIQRK+wgNajJ7qJMDmGLvhAazANBgkqhkiG9w0BAQUFADA/\nMSQwIgYDVQQKExtEaWdpdGFsIFNpZ25hdHVyZSBUcnVzdCBDby4xFzAVBgNVBAMT\nDkRTVCBSb290IENBIFgzMB4XDTAwMDkzMDIxMTIxOVoXDTIxMDkzMDE0MDExNVow\nPzEkMCIGA1UEChMbRGlnaXRhbCBTaWduYXR1cmUgVHJ1c3QgQ28uMRcwFQYDVQQD\nEw5EU1QgUm9vdCBDQSBYMzCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEB\nAN+v6ZdQCINXtMxiZfaQguzH0yxrMMpb7NnDfcdAwRgUi+DoM3ZJKuM/IUmTrE4O\nrz5Iy2Xu/NMhD2XSKtkyj4zl93ewEnu1lcCJo6m67XMuegwGMoOifooUMM0RoOEq\nOLl5CjH9UL2AZd+3UWODyOKIYepLYYHsUmu5ouJLGiifSKOeDNoJjj4XLh7dIN9b\nxiqKqy69cK3FCxolkHRyxXtqqzTWMIn/5WgTe1QLyNau7Fqckh49ZLOMxt+/yUFw\n7BZy1SbsOFU5Q9D8/RhcQPGX69Wam40dutolucbY38EVAjqr2m7xPi71XAicPNaD\naeQQmxkqtilX4+U9m5/wAl0CAwEAAaNCMEAwDwYDVR0TAQH/BAUwAwEB/zAOBgNV\nHQ8BAf8EBAMCAQYwHQYDVR0OBBYEFMSnsaR7LHH62+FLkHX/xBVghYkQMA0GCSqG\nSIb3DQEBBQUAA4IBAQCjGiybFwBcqR7uKGY3Or+Dxz9LwwmglSBd49lZRNI+DT69\nikugdB/OEIKcdBodfpga3csTS7MgROSR6cz8faXbauX+5v3gTt23ADq1cEmv8uXr\nAvHRAosZy5Q6XkjEGB5YGV8eAlrwDPGxrancWYaLbumR9YbK+rlmM6pZW87ipxZz\nR8srzJmwN0jP41ZL9c8PDHIyh8bwRLtTcm1D9SZImlJnt1ir/md2cXjbDaJWFBM5\nJDGFoqgCWjBH4d1QB7wCCZAA62RjYJsWvIjJEubSfZGL+T0yjWW06XyxV3bqxbYo\nOb8VZRzI9neWagqNdwvYkQsEjgfbKbYK7p2CNTUQ\n-----END CERTIFICATE-----\n"
            java.lang.String r13 = "UTF-8"
            byte[] r12 = r12.getBytes(r13)     // Catch:{ Exception -> 0x0109 }
            r11.<init>(r12)     // Catch:{ Exception -> 0x0109 }
            r2 = r10
            r10 = r1
            r11 = r2
            java.security.cert.Certificate r10 = r10.generateCertificate(r11)     // Catch:{ Exception -> 0x0109 }
            r1 = r10
            r10 = r2
            r10.close()     // Catch:{ Exception -> 0x0109 }
            java.lang.String r10 = java.security.KeyStore.getDefaultType()     // Catch:{ Exception -> 0x0109 }
            java.security.KeyStore r10 = java.security.KeyStore.getInstance(r10)     // Catch:{ Exception -> 0x0109 }
            r16 = r10
            r10 = r16
            r11 = r16
            r2 = r11
            r11 = 0
            r12 = 0
            r10.load(r11, r12)     // Catch:{ Exception -> 0x0109 }
            r10 = 1
            r5 = r10
            r10 = r0
            java.security.cert.X509Certificate[] r10 = r10.getSystemCertificates()     // Catch:{ Exception -> 0x0109 }
            r16 = r10
            r10 = r16
            r11 = r16
            r6 = r11
            int r10 = r10.length     // Catch:{ Exception -> 0x0109 }
            r7 = r10
            r10 = 0
            r8 = r10
        L_0x009d:
            r10 = r8
            r11 = r7
            if (r10 >= r11) goto L_0x00bc
            r10 = r6
            r11 = r8
            r10 = r10[r11]     // Catch:{ Exception -> 0x0109 }
            r9 = r10
            r10 = r2
            java.lang.String r11 = "root"
            r12 = r5
            java.lang.String r12 = java.lang.String.valueOf(r12)     // Catch:{ Exception -> 0x0109 }
            java.lang.String r11 = r11.concat(r12)     // Catch:{ Exception -> 0x0109 }
            r12 = r9
            r10.setCertificateEntry(r11, r12)     // Catch:{ Exception -> 0x0109 }
            int r5 = r5 + 1
            int r8 = r8 + 1
            goto L_0x009d
        L_0x00bc:
            r10 = r2
            java.lang.String r11 = "comodo"
            r12 = r3
            r10.setCertificateEntry(r11, r12)     // Catch:{ Exception -> 0x0109 }
            r10 = r2
            java.lang.String r11 = "inter"
            r12 = r4
            r10.setCertificateEntry(r11, r12)     // Catch:{ Exception -> 0x0109 }
            r10 = r2
            java.lang.String r11 = "dstx3"
            r12 = r1
            r10.setCertificateEntry(r11, r12)     // Catch:{ Exception -> 0x0109 }
            java.lang.String r10 = javax.net.ssl.TrustManagerFactory.getDefaultAlgorithm()     // Catch:{ Exception -> 0x0109 }
            javax.net.ssl.TrustManagerFactory r10 = javax.net.ssl.TrustManagerFactory.getInstance(r10)     // Catch:{ Exception -> 0x0109 }
            r16 = r10
            r10 = r16
            r11 = r16
            r6 = r11
            r11 = r2
            r10.init(r11)     // Catch:{ Exception -> 0x0109 }
            java.lang.String r10 = "TLS"
            javax.net.ssl.SSLContext r10 = javax.net.ssl.SSLContext.getInstance(r10)     // Catch:{ Exception -> 0x0109 }
            r16 = r10
            r10 = r16
            r11 = r16
            r7 = r11
            r11 = 0
            r12 = r6
            javax.net.ssl.TrustManager[] r12 = r12.getTrustManagers()     // Catch:{ Exception -> 0x0109 }
            r13 = 0
            r10.init(r11, r12, r13)     // Catch:{ Exception -> 0x0109 }
            r10 = r0
            r11 = r7
            javax.net.ssl.SSLSocketFactory r11 = r11.getSocketFactory()     // Catch:{ Exception -> 0x0109 }
            r10.SslSockFactory = r11     // Catch:{ Exception -> 0x0109 }
            goto L_0x000a
        L_0x0109:
            r10 = move-exception
            r1 = r10
            java.lang.String r10 = "CloudDB"
            java.lang.String r11 = "Could not setup SSL Trust Store for CloudDB"
            r12 = r1
            int r10 = android.util.Log.e(r10, r11, r12)     // Catch:{ all -> 0x013d }
            com.google.appinventor.components.runtime.errors.YailRuntimeError r10 = new com.google.appinventor.components.runtime.errors.YailRuntimeError     // Catch:{ all -> 0x013d }
            r16 = r10
            r10 = r16
            r11 = r16
            java.lang.String r12 = "Could Not setup SSL Trust Store for CloudDB: "
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x013d }
            r16 = r13
            r13 = r16
            r14 = r16
            r14.<init>()     // Catch:{ all -> 0x013d }
            r14 = r1
            java.lang.String r14 = r14.getMessage()     // Catch:{ all -> 0x013d }
            java.lang.StringBuilder r13 = r13.append(r14)     // Catch:{ all -> 0x013d }
            java.lang.String r13 = r13.toString()     // Catch:{ all -> 0x013d }
            r11.<init>(r12, r13)     // Catch:{ all -> 0x013d }
            throw r10     // Catch:{ all -> 0x013d }
        L_0x013d:
            r0 = move-exception
            monitor-exit(r15)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.CloudDB.ensureSslSockFactory():void");
    }

    private X509Certificate[] getSystemCertificates() {
        try {
            TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            instance.init((KeyStore) null);
            return ((X509TrustManager) instance.getTrustManagers()[0]).getAcceptedIssuers();
        } catch (Exception e) {
            int e2 = Log.e(LOG_TAG, "Getting System Certificates", e);
            return new X509Certificate[0];
        }
    }
}

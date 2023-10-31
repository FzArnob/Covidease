package com.google.appinventor.components.runtime.util;

import android.content.Context;
import android.util.Log;
import com.google.appinventor.components.runtime.ReplForm;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeSet;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.webrtc.DataChannel;
import org.webrtc.IceCandidate;
import org.webrtc.MediaConstraints;
import org.webrtc.MediaStream;
import org.webrtc.PeerConnection;
import org.webrtc.PeerConnectionFactory;
import org.webrtc.RtpReceiver;
import org.webrtc.SdpObserver;
import org.webrtc.SessionDescription;

public class WebRTCNativeMgr {
    private static final boolean DEBUG = true;
    private static final String LOG_TAG = "KodularWebRTC";
    /* access modifiers changed from: private */
    public static final CharsetDecoder utf8Decoder = Charset.forName("UTF-8").newDecoder();
    private DataChannel dataChannel;
    DataChannel.Observer dataObserver;
    /* access modifiers changed from: private */
    public boolean first = true;
    /* access modifiers changed from: private */
    public ReplForm form;
    private volatile boolean haveLocalDescription = false;
    private boolean haveOffer = false;
    private List<PeerConnection.IceServer> iceServers;
    private volatile boolean keepPolling = true;
    PeerConnection.Observer observer;
    /* access modifiers changed from: private */
    public PeerConnection peerConnection;
    /* access modifiers changed from: private */
    public String rCode;
    /* access modifiers changed from: private */
    public Random random;
    private String rendezvousServer;
    /* access modifiers changed from: private */
    public String rendezvousServer2;
    SdpObserver sdpObserver;
    /* access modifiers changed from: private */
    public TreeSet<String> seenNonces;
    Timer timer;

    static /* synthetic */ boolean access$1002(WebRTCNativeMgr webRTCNativeMgr, boolean z) {
        boolean z2 = z;
        boolean z3 = z2;
        webRTCNativeMgr.first = z3;
        return z2;
    }

    static /* synthetic */ boolean access$102(WebRTCNativeMgr webRTCNativeMgr, boolean z) {
        boolean z2 = z;
        boolean z3 = z2;
        webRTCNativeMgr.haveLocalDescription = z3;
        return z2;
    }

    static /* synthetic */ DataChannel access$302(WebRTCNativeMgr webRTCNativeMgr, DataChannel dataChannel2) {
        DataChannel dataChannel3 = dataChannel2;
        DataChannel dataChannel4 = dataChannel3;
        webRTCNativeMgr.dataChannel = dataChannel4;
        return dataChannel3;
    }

    static /* synthetic */ boolean access$402(WebRTCNativeMgr webRTCNativeMgr, boolean z) {
        boolean z2 = z;
        boolean z3 = z2;
        webRTCNativeMgr.keepPolling = z3;
        return z2;
    }

    public WebRTCNativeMgr(String str, String str2) {
        TreeSet<String> treeSet;
        Random random2;
        List<PeerConnection.IceServer> list;
        Timer timer2;
        SdpObserver sdpObserver2;
        PeerConnection.Observer observer2;
        DataChannel.Observer observer3;
        JSONObject jSONObject;
        List<PeerConnection.IceServer> list2;
        StringBuilder sb;
        String str3 = str2;
        new TreeSet<>();
        this.seenNonces = treeSet;
        new Random();
        this.random = random2;
        this.dataChannel = null;
        this.rendezvousServer = null;
        this.rendezvousServer2 = null;
        new ArrayList();
        this.iceServers = list;
        new Timer();
        this.timer = timer2;
        new SdpObserver(this) {
            private /* synthetic */ WebRTCNativeMgr B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

            {
                this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = r5;
            }

            public final void onCreateFailure(String str) {
                int d = Log.d(WebRTCNativeMgr.LOG_TAG, "onCreateFailure: ".concat(String.valueOf(str)));
            }

            public final void onCreateSuccess(SessionDescription sessionDescription) {
                StringBuilder sb;
                StringBuilder sb2;
                JSONObject jSONObject;
                JSONObject jSONObject2;
                SessionDescription sessionDescription2 = sessionDescription;
                try {
                    new StringBuilder("sdp.type = ");
                    int d = Log.d(WebRTCNativeMgr.LOG_TAG, sb.append(sessionDescription2.type.canonicalForm()).toString());
                    new StringBuilder("sdp.description = ");
                    int d2 = Log.d(WebRTCNativeMgr.LOG_TAG, sb2.append(sessionDescription2.description).toString());
                    new DataChannel.Init();
                    if (sessionDescription2.type == SessionDescription.Type.OFFER) {
                        int d3 = Log.d(WebRTCNativeMgr.LOG_TAG, "Got offer, about to set remote description (again?)");
                        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.peerConnection.setRemoteDescription(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.sdpObserver, sessionDescription2);
                    } else if (sessionDescription2.type == SessionDescription.Type.ANSWER) {
                        int d4 = Log.d(WebRTCNativeMgr.LOG_TAG, "onCreateSuccess: type = ANSWER");
                        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.peerConnection.setLocalDescription(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.sdpObserver, sessionDescription2);
                        boolean access$102 = WebRTCNativeMgr.access$102(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T, true);
                        new JSONObject();
                        JSONObject jSONObject3 = jSONObject;
                        JSONObject jSONObject4 = jSONObject3;
                        JSONObject put = jSONObject3.put("type", "answer");
                        JSONObject put2 = jSONObject4.put("sdp", sessionDescription2.description);
                        new JSONObject();
                        JSONObject jSONObject5 = jSONObject2;
                        JSONObject put3 = jSONObject5.put("offer", jSONObject4);
                        this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.sendRendezvous(jSONObject5);
                    }
                } catch (Exception e) {
                    int e2 = Log.e(WebRTCNativeMgr.LOG_TAG, "Exception during onCreateSuccess", e);
                }
            }

            public final void onSetFailure(String str) {
            }

            public final void onSetSuccess() {
            }
        };
        this.sdpObserver = sdpObserver2;
        new PeerConnection.Observer(this) {
            private /* synthetic */ WebRTCNativeMgr B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

            {
                this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = r5;
            }

            public final void onAddStream(MediaStream mediaStream) {
            }

            public final void onAddTrack(RtpReceiver rtpReceiver, MediaStream[] mediaStreamArr) {
            }

            public final void onDataChannel(DataChannel dataChannel) {
                DataChannel dataChannel2 = dataChannel;
                int d = Log.d(WebRTCNativeMgr.LOG_TAG, "Have Data Channel!");
                int d2 = Log.d(WebRTCNativeMgr.LOG_TAG, "v5");
                DataChannel access$302 = WebRTCNativeMgr.access$302(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T, dataChannel2);
                dataChannel2.registerObserver(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.dataObserver);
                boolean access$402 = WebRTCNativeMgr.access$402(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T, false);
                this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.timer.cancel();
                int d3 = Log.d(WebRTCNativeMgr.LOG_TAG, "Poller() Canceled");
                this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.seenNonces.clear();
            }

            public final void onIceCandidate(IceCandidate iceCandidate) {
                StringBuilder sb;
                StringBuilder sb2;
                JSONObject jSONObject;
                JSONObject jSONObject2;
                IceCandidate iceCandidate2 = iceCandidate;
                try {
                    new StringBuilder("IceCandidate = ");
                    int d = Log.d(WebRTCNativeMgr.LOG_TAG, sb.append(iceCandidate2.toString()).toString());
                    if (iceCandidate2.sdp == null) {
                        int d2 = Log.d(WebRTCNativeMgr.LOG_TAG, "IceCandidate is null");
                    } else {
                        new StringBuilder("IceCandidateSDP = ");
                        int d3 = Log.d(WebRTCNativeMgr.LOG_TAG, sb2.append(iceCandidate2.sdp).toString());
                    }
                    new JSONObject();
                    JSONObject jSONObject3 = jSONObject;
                    JSONObject jSONObject4 = jSONObject3;
                    JSONObject put = jSONObject3.put("nonce", this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.random.nextInt(100000));
                    new JSONObject();
                    JSONObject jSONObject5 = jSONObject2;
                    JSONObject jSONObject6 = jSONObject5;
                    JSONObject put2 = jSONObject5.put("candidate", iceCandidate2.sdp);
                    JSONObject put3 = jSONObject6.put("sdpMLineIndex", iceCandidate2.sdpMLineIndex);
                    JSONObject put4 = jSONObject6.put("sdpMid", iceCandidate2.sdpMid);
                    JSONObject put5 = jSONObject4.put("candidate", jSONObject6);
                    this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.sendRendezvous(jSONObject4);
                } catch (Exception e) {
                    int e2 = Log.e(WebRTCNativeMgr.LOG_TAG, "Exception during onIceCandidate", e);
                }
            }

            public final void onIceCandidatesRemoved(IceCandidate[] iceCandidateArr) {
            }

            public final void onIceConnectionChange(PeerConnection.IceConnectionState iceConnectionState) {
            }

            public final void onIceConnectionReceivingChange(boolean z) {
            }

            public final void onIceGatheringChange(PeerConnection.IceGatheringState iceGatheringState) {
                int d = Log.d(WebRTCNativeMgr.LOG_TAG, "onIceGatheringChange: iceGatheringState = ".concat(String.valueOf(iceGatheringState)));
            }

            public final void onRemoveStream(MediaStream mediaStream) {
            }

            public final void onRenegotiationNeeded() {
            }

            public final void onSignalingChange(PeerConnection.SignalingState signalingState) {
                int d = Log.d(WebRTCNativeMgr.LOG_TAG, "onSignalingChange: signalingState = ".concat(String.valueOf(signalingState)));
            }
        };
        this.observer = observer2;
        new DataChannel.Observer(this) {
            private /* synthetic */ WebRTCNativeMgr B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

            {
                this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = r5;
            }

            public final void onBufferedAmountChange(long j) {
            }

            public final void onMessage(DataChannel.Buffer buffer) {
                try {
                    String charBuffer = WebRTCNativeMgr.utf8Decoder.decode(buffer.data).toString();
                    int d = Log.d(WebRTCNativeMgr.LOG_TAG, "onMessage: received: ".concat(String.valueOf(charBuffer)));
                    this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.form.evalScheme(charBuffer);
                } catch (CharacterCodingException e) {
                    int e2 = Log.e(WebRTCNativeMgr.LOG_TAG, "onMessage decoder error", e);
                }
            }

            public final void onStateChange() {
            }
        };
        this.dataObserver = observer3;
        this.rendezvousServer = str;
        try {
            new JSONObject((str3.isEmpty() || str3.startsWith("OK")) ? "{\"rendezvous2\" : \"rendezvous.creator.kodular.io\",\"iceservers\" : [{ \"server\" : \"turn:turn.creator.kodular.io:3478\",\"username\" : \"kodular\",\"password\" : \"yxUJKuyWzUW6R94uz96jZnAvBpzJSavb\"}]}" : str3);
            JSONObject jSONObject2 = jSONObject;
            this.rendezvousServer2 = jSONObject2.getString("rendezvous2");
            JSONArray jSONArray = jSONObject2.getJSONArray("iceservers");
            new ArrayList(jSONArray.length());
            this.iceServers = list2;
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject3 = jSONArray.getJSONObject(i);
                JSONObject jSONObject4 = jSONObject3;
                PeerConnection.IceServer.Builder builder = PeerConnection.IceServer.builder(jSONObject3.getString("server"));
                new StringBuilder("Adding iceServer = ");
                int d = Log.d(LOG_TAG, sb.append(jSONObject4.getString("server")).toString());
                if (jSONObject4.has("username")) {
                    PeerConnection.IceServer.Builder username = builder.setUsername(jSONObject4.getString("username"));
                }
                if (jSONObject4.has("password")) {
                    PeerConnection.IceServer.Builder password = builder.setPassword(jSONObject4.getString("password"));
                }
                boolean add = this.iceServers.add(builder.createIceServer());
            }
        } catch (JSONException e) {
            int e2 = Log.e(LOG_TAG, "parsing iceServers:", e);
        }
    }

    public void initiate(ReplForm replForm, Context context, String str) {
        PeerConnectionFactory.Options options;
        PeerConnectionFactory peerConnectionFactory;
        PeerConnection.RTCConfiguration rTCConfiguration;
        MediaConstraints mediaConstraints;
        TimerTask timerTask;
        this.form = replForm;
        this.rCode = str;
        PeerConnectionFactory.initializeAndroidGlobals(context, false);
        new PeerConnectionFactory.Options();
        new PeerConnectionFactory(options);
        PeerConnectionFactory peerConnectionFactory2 = peerConnectionFactory;
        new PeerConnection.RTCConfiguration(this.iceServers);
        PeerConnection.RTCConfiguration rTCConfiguration2 = rTCConfiguration;
        rTCConfiguration2.continualGatheringPolicy = PeerConnection.ContinualGatheringPolicy.GATHER_CONTINUALLY;
        new MediaConstraints();
        this.peerConnection = peerConnectionFactory2.createPeerConnection(rTCConfiguration2, mediaConstraints, this.observer);
        new TimerTask(this) {
            private /* synthetic */ WebRTCNativeMgr B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

            {
                this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = r5;
            }

            public final void run() {
                this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.Poller();
            }
        };
        this.timer.schedule(timerTask, 0, 1000);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void Poller() {
        /*
            r14 = this;
            r0 = r14
            r7 = r0
            boolean r7 = r7.keepPolling     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            if (r7 != 0) goto L_0x0007
        L_0x0006:
            return
        L_0x0007:
            java.lang.String r7 = "KodularWebRTC"
            java.lang.String r8 = "Poller() Called"
            int r7 = android.util.Log.d(r7, r8)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            java.lang.String r7 = "KodularWebRTC"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            r13 = r8
            r8 = r13
            r9 = r13
            java.lang.String r10 = "Poller: rendezvousServer2 = "
            r9.<init>(r10)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            r9 = r0
            java.lang.String r9 = r9.rendezvousServer2     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            java.lang.String r8 = r8.toString()     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            int r7 = android.util.Log.d(r7, r8)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            org.apache.http.impl.client.DefaultHttpClient r7 = new org.apache.http.impl.client.DefaultHttpClient     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            r13 = r7
            r7 = r13
            r8 = r13
            r8.<init>()     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            r1 = r7
            org.apache.http.client.methods.HttpGet r7 = new org.apache.http.client.methods.HttpGet     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            r13 = r7
            r7 = r13
            r8 = r13
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            r13 = r9
            r9 = r13
            r10 = r13
            java.lang.String r11 = "https://"
            r10.<init>(r11)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            r10 = r0
            java.lang.String r10 = r10.rendezvousServer     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            java.lang.String r10 = "/rendezvous2/"
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            r10 = r0
            java.lang.String r10 = r10.rCode     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            java.lang.String r10 = "-s"
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            java.lang.String r9 = r9.toString()     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            r8.<init>(r9)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            r2 = r7
            r7 = r1
            r8 = r2
            org.apache.http.HttpResponse r7 = r7.execute(r8)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            r1 = r7
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            r13 = r7
            r7 = r13
            r8 = r13
            r8.<init>()     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            r2 = r7
            r7 = 0
            r3 = r7
            java.io.BufferedReader r7 = new java.io.BufferedReader     // Catch:{ all -> 0x00be }
            r13 = r7
            r7 = r13
            r8 = r13
            java.io.InputStreamReader r9 = new java.io.InputStreamReader     // Catch:{ all -> 0x00be }
            r13 = r9
            r9 = r13
            r10 = r13
            r11 = r1
            org.apache.http.HttpEntity r11 = r11.getEntity()     // Catch:{ all -> 0x00be }
            java.io.InputStream r11 = r11.getContent()     // Catch:{ all -> 0x00be }
            r10.<init>(r11)     // Catch:{ all -> 0x00be }
            r8.<init>(r9)     // Catch:{ all -> 0x00be }
            r3 = r7
        L_0x0097:
            r7 = r3
            java.lang.String r7 = r7.readLine()     // Catch:{ all -> 0x00be }
            r13 = r7
            r7 = r13
            r8 = r13
            r1 = r8
            if (r7 == 0) goto L_0x00a9
            r7 = r2
            r8 = r1
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch:{ all -> 0x00be }
            goto L_0x0097
        L_0x00a9:
            r7 = r3
            r7.close()     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            r7 = r0
            boolean r7 = r7.keepPolling     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            if (r7 != 0) goto L_0x00ed
            java.lang.String r7 = "KodularWebRTC"
            java.lang.String r8 = "keepPolling is false, we're done!"
            int r7 = android.util.Log.d(r7, r8)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            goto L_0x0006
        L_0x00be:
            r7 = move-exception
            r1 = r7
            r7 = r3
            if (r7 == 0) goto L_0x00c7
            r7 = r3
            r7.close()     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
        L_0x00c7:
            r7 = r1
            throw r7     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
        L_0x00c9:
            r7 = move-exception
            r1 = r7
            java.lang.String r7 = "KodularWebRTC"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r13 = r8
            r8 = r13
            r9 = r13
            java.lang.String r10 = "Caught IOException: "
            r9.<init>(r10)
            r9 = r1
            java.lang.String r9 = r9.toString()
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r8 = r8.toString()
            r9 = r1
            int r7 = android.util.Log.e(r7, r8, r9)
            goto L_0x0006
        L_0x00ed:
            r7 = r2
            java.lang.String r7 = r7.toString()     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            r1 = r7
            java.lang.String r7 = "KodularWebRTC"
            java.lang.String r8 = "response = "
            r9 = r1
            java.lang.String r9 = java.lang.String.valueOf(r9)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            java.lang.String r8 = r8.concat(r9)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            int r7 = android.util.Log.d(r7, r8)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            r7 = r1
            java.lang.String r8 = ""
            boolean r7 = r7.equals(r8)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            if (r7 == 0) goto L_0x011c
            java.lang.String r7 = "KodularWebRTC"
            java.lang.String r8 = "Received an empty response"
            int r7 = android.util.Log.d(r7, r8)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            goto L_0x0006
        L_0x011c:
            org.json.JSONArray r7 = new org.json.JSONArray     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            r13 = r7
            r7 = r13
            r8 = r13
            r9 = r1
            r8.<init>(r9)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            r1 = r7
            java.lang.String r7 = "KodularWebRTC"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            r13 = r8
            r8 = r13
            r9 = r13
            java.lang.String r10 = "jsonArray.length() = "
            r9.<init>(r10)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            r9 = r1
            int r9 = r9.length()     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            java.lang.String r8 = r8.toString()     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            int r7 = android.util.Log.d(r7, r8)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            r7 = 0
            r2 = r7
        L_0x0147:
            r7 = r2
            r8 = r1
            int r8 = r8.length()     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            if (r7 >= r8) goto L_0x02f0
            java.lang.String r7 = "KodularWebRTC"
            java.lang.String r8 = "i = "
            r9 = r2
            java.lang.String r9 = java.lang.String.valueOf(r9)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            java.lang.String r8 = r8.concat(r9)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            int r7 = android.util.Log.d(r7, r8)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            java.lang.String r7 = "KodularWebRTC"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            r13 = r8
            r8 = r13
            r9 = r13
            java.lang.String r10 = "element = "
            r9.<init>(r10)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            r9 = r1
            r10 = r2
            java.lang.String r9 = r9.optString(r10)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            java.lang.String r8 = r8.toString()     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            int r7 = android.util.Log.d(r7, r8)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            r7 = r1
            r8 = r2
            java.lang.Object r7 = r7.get(r8)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            org.json.JSONObject r7 = (org.json.JSONObject) r7     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            r3 = r7
            r7 = r0
            boolean r7 = r7.haveOffer     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            if (r7 != 0) goto L_0x022f
            r7 = r3
            java.lang.String r8 = "offer"
            boolean r7 = r7.has(r8)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            if (r7 != 0) goto L_0x019d
            int r2 = r2 + 1
            goto L_0x0147
        L_0x019d:
            r7 = r3
            java.lang.String r8 = "offer"
            java.lang.Object r7 = r7.get(r8)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            org.json.JSONObject r7 = (org.json.JSONObject) r7     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            r13 = r7
            r7 = r13
            r8 = r13
            r4 = r8
            java.lang.String r8 = "sdp"
            java.lang.String r7 = r7.optString(r8)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            r3 = r7
            r7 = r4
            java.lang.String r8 = "type"
            java.lang.String r7 = r7.optString(r8)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            r5 = r7
            r7 = r0
            r8 = 1
            r7.haveOffer = r8     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            java.lang.String r7 = "KodularWebRTC"
            java.lang.String r8 = "sdb = "
            r9 = r3
            java.lang.String r9 = java.lang.String.valueOf(r9)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            java.lang.String r8 = r8.concat(r9)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            int r7 = android.util.Log.d(r7, r8)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            java.lang.String r7 = "KodularWebRTC"
            java.lang.String r8 = "type = "
            r9 = r5
            java.lang.String r9 = java.lang.String.valueOf(r9)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            java.lang.String r8 = r8.concat(r9)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            int r7 = android.util.Log.d(r7, r8)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            java.lang.String r7 = "KodularWebRTC"
            java.lang.String r8 = "About to set remote offer"
            int r7 = android.util.Log.d(r7, r8)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            java.lang.String r7 = "KodularWebRTC"
            java.lang.String r8 = "Got offer, about to set remote description (maincode)"
            int r7 = android.util.Log.d(r7, r8)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            r7 = r0
            org.webrtc.PeerConnection r7 = r7.peerConnection     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            r8 = r0
            org.webrtc.SdpObserver r8 = r8.sdpObserver     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            org.webrtc.SessionDescription r9 = new org.webrtc.SessionDescription     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            r13 = r9
            r9 = r13
            r10 = r13
            org.webrtc.SessionDescription$Type r11 = org.webrtc.SessionDescription.Type.OFFER     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            r12 = r3
            r10.<init>(r11, r12)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            r7.setRemoteDescription(r8, r9)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            r7 = r0
            org.webrtc.PeerConnection r7 = r7.peerConnection     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            r8 = r0
            org.webrtc.SdpObserver r8 = r8.sdpObserver     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            org.webrtc.MediaConstraints r9 = new org.webrtc.MediaConstraints     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            r13 = r9
            r9 = r13
            r10 = r13
            r10.<init>()     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            r7.createAnswer(r8, r9)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            java.lang.String r7 = "KodularWebRTC"
            java.lang.String r8 = "createAnswer returned"
            int r7 = android.util.Log.d(r7, r8)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            r7 = -1
            r2 = r7
        L_0x022b:
            int r2 = r2 + 1
            goto L_0x0147
        L_0x022f:
            r7 = r3
            java.lang.String r8 = "nonce"
            boolean r7 = r7.has(r8)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            if (r7 == 0) goto L_0x022b
            r7 = r0
            boolean r7 = r7.haveLocalDescription     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            if (r7 != 0) goto L_0x024a
            java.lang.String r7 = "KodularWebRTC"
            java.lang.String r8 = "Incoming candidate before local description set, punting"
            int r7 = android.util.Log.d(r7, r8)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            goto L_0x0006
        L_0x024a:
            r7 = r3
            java.lang.String r8 = "offer"
            boolean r7 = r7.has(r8)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            if (r7 == 0) goto L_0x0262
            int r2 = r2 + 1
            java.lang.String r7 = "KodularWebRTC"
            java.lang.String r8 = "skipping offer, already processed"
            int r7 = android.util.Log.d(r7, r8)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            goto L_0x0147
        L_0x0262:
            r7 = r3
            java.lang.String r8 = "candidate"
            boolean r7 = r7.isNull(r8)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            if (r7 == 0) goto L_0x0270
            int r2 = r2 + 1
            goto L_0x0147
        L_0x0270:
            r7 = r3
            java.lang.String r8 = "nonce"
            java.lang.String r7 = r7.optString(r8)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            r4 = r7
            r7 = r3
            java.lang.String r8 = "candidate"
            java.lang.Object r7 = r7.get(r8)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            org.json.JSONObject r7 = (org.json.JSONObject) r7     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            r13 = r7
            r7 = r13
            r8 = r13
            r3 = r8
            java.lang.String r8 = "candidate"
            java.lang.String r7 = r7.optString(r8)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            r5 = r7
            r7 = r3
            java.lang.String r8 = "sdpMid"
            java.lang.String r7 = r7.optString(r8)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            r6 = r7
            r7 = r3
            java.lang.String r8 = "sdpMLineIndex"
            int r7 = r7.optInt(r8)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            r3 = r7
            r7 = r0
            java.util.TreeSet<java.lang.String> r7 = r7.seenNonces     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            r8 = r4
            boolean r7 = r7.contains(r8)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            if (r7 != 0) goto L_0x022b
            r7 = r0
            java.util.TreeSet<java.lang.String> r7 = r7.seenNonces     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            r8 = r4
            boolean r7 = r7.add(r8)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            java.lang.String r7 = "KodularWebRTC"
            java.lang.String r8 = "new nonce, about to add candidate!"
            int r7 = android.util.Log.d(r7, r8)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            java.lang.String r7 = "KodularWebRTC"
            java.lang.String r8 = "candidate = "
            r9 = r5
            java.lang.String r9 = java.lang.String.valueOf(r9)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            java.lang.String r8 = r8.concat(r9)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            int r7 = android.util.Log.d(r7, r8)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            org.webrtc.IceCandidate r7 = new org.webrtc.IceCandidate     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            r13 = r7
            r7 = r13
            r8 = r13
            r9 = r6
            r10 = r3
            r11 = r5
            r8.<init>(r9, r10, r11)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            r3 = r7
            r7 = r0
            org.webrtc.PeerConnection r7 = r7.peerConnection     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            r8 = r3
            boolean r7 = r7.addIceCandidate(r8)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            java.lang.String r7 = "KodularWebRTC"
            java.lang.String r8 = "addIceCandidate returned"
            int r7 = android.util.Log.d(r7, r8)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            goto L_0x022b
        L_0x02f0:
            java.lang.String r7 = "KodularWebRTC"
            java.lang.String r8 = "exited loop"
            int r7 = android.util.Log.d(r7, r8)     // Catch:{ IOException -> 0x00c9, JSONException -> 0x02fc, Exception -> 0x0320 }
            goto L_0x0006
        L_0x02fc:
            r7 = move-exception
            r1 = r7
            java.lang.String r7 = "KodularWebRTC"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r13 = r8
            r8 = r13
            r9 = r13
            java.lang.String r10 = "Caught JSONException: "
            r9.<init>(r10)
            r9 = r1
            java.lang.String r9 = r9.toString()
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r8 = r8.toString()
            r9 = r1
            int r7 = android.util.Log.e(r7, r8, r9)
            goto L_0x0006
        L_0x0320:
            r7 = move-exception
            r1 = r7
            java.lang.String r7 = "KodularWebRTC"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r13 = r8
            r8 = r13
            r9 = r13
            java.lang.String r10 = "Caught Exception: "
            r9.<init>(r10)
            r9 = r1
            java.lang.String r9 = r9.toString()
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r8 = r8.toString()
            r9 = r1
            int r7 = android.util.Log.e(r7, r8, r9)
            goto L_0x0006
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.util.WebRTCNativeMgr.Poller():void");
    }

    /* access modifiers changed from: private */
    public void sendRendezvous(JSONObject jSONObject) {
        Runnable runnable;
        final JSONObject jSONObject2 = jSONObject;
        new Runnable(this) {
            private /* synthetic */ WebRTCNativeMgr B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

            {
                this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = r6;
            }

            public final void run() {
                StringBuilder sb;
                HttpClient httpClient;
                HttpPost httpPost;
                StringBuilder sb2;
                StringBuilder sb3;
                HttpEntity httpEntity;
                try {
                    JSONObject put = jSONObject2.put("first", this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.first);
                    JSONObject put2 = jSONObject2.put("webrtc", true);
                    JSONObject jSONObject = jSONObject2;
                    new StringBuilder();
                    JSONObject put3 = jSONObject.put("key", sb.append(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.rCode).append("-r").toString());
                    if (this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.first) {
                        boolean access$1002 = WebRTCNativeMgr.access$1002(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T, false);
                        JSONObject put4 = jSONObject2.put("apiversion", SdkLevel.getLevel());
                    }
                    new DefaultHttpClient();
                    HttpClient httpClient2 = httpClient;
                    HttpPost httpPost2 = httpPost;
                    new StringBuilder("https://");
                    new HttpPost(sb2.append(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.rendezvousServer2).append("/rendezvous2/").toString());
                    HttpPost httpPost3 = httpPost2;
                    try {
                        new StringBuilder("About to send = ");
                        int d = Log.d(WebRTCNativeMgr.LOG_TAG, sb3.append(jSONObject2.toString()).toString());
                        new StringEntity(jSONObject2.toString());
                        httpPost3.setEntity(httpEntity);
                        HttpResponse execute = httpClient2.execute(httpPost3);
                    } catch (IOException e) {
                        int e2 = Log.e(WebRTCNativeMgr.LOG_TAG, "sendRedezvous IOException", e);
                    }
                } catch (Exception e3) {
                    int e4 = Log.e(WebRTCNativeMgr.LOG_TAG, "Exception in sendRendezvous", e3);
                }
            }
        };
        AsynchUtil.runAsynchronously(runnable);
    }

    public void send(String str) {
        DataChannel.Buffer buffer;
        String str2 = str;
        try {
            if (this.dataChannel == null) {
                int w = Log.w(LOG_TAG, "No Data Channel in Send");
                return;
            }
            new DataChannel.Buffer(ByteBuffer.wrap(str2.getBytes("UTF-8")), false);
            boolean send = this.dataChannel.send(buffer);
        } catch (UnsupportedEncodingException e) {
            int e2 = Log.e(LOG_TAG, "While encoding data to send to companion", e);
        }
    }
}

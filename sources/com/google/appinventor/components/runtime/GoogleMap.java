package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PointOfInterest;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.TileProvider;
import com.google.android.gms.maps.model.UrlTileProvider;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.GoogleMapStyleOptions;
import com.google.appinventor.components.runtime.util.JsonUtil;
import com.google.appinventor.components.runtime.util.OnInitializeListener;
import com.google.appinventor.components.runtime.util.YailList;
import gnu.math.DFloNum;
import gnu.math.IntNum;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@DesignerComponent(category = ComponentCategory.GOOGLE, description = "Visible component that show information on Google map.", helpUrl = "https://docs.kodular.io/components/google/google-maps/", iconName = "images/gMap.png", version = 4)
@UsesLibraries(libraries = "play-services-base.jar, play-services-base.aar,play-services-basement.jar, play-services-basement.aar,play-services-location.jar, play-services-location.aar,play-services-maps.jar, play-services-maps.aar,play-services-places-placereport.aar, play-services-places-placereport.jar,play-services-tasks.jar, play-services-tasks.aar,arch-core-runtime.jar, arch-core-runtime.aar,gson.jar")
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.WRITE_EXTERNAL_STORAGE, android.permission.ACCESS_NETWORK_STATE, android.permission.INTERNET, android.permission.ACCESS_COARSE_LOCATION, android.permission.ACCESS_FINE_LOCATION, com.google.android.providers.gsf.permission.READ_GSERVICES")
public class GoogleMap extends AndroidViewComponent implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener, GoogleMap.OnCameraChangeListener, GoogleMap.OnInfoWindowClickListener, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener, GoogleMap.OnMarkerClickListener, GoogleMap.OnMarkerDragListener, GoogleMap.OnPoiClickListener, OnMapReadyCallback, OnPauseListener, OnResumeListener, OnInitializeListener {
    private static final AtomicInteger B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    public static final double RADIUS_OF_EARTH_METERS = 6371009.0d;
    private static final LocationRequest hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = LocationRequest.create().setInterval(5000).setFastestInterval(16).setPriority(100);

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private static final AtomicInteger f414hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private static final AtomicInteger vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq;
    private static final AtomicInteger wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;
    private boolean AGGRF5jTQInc5pUFaFXws4RnGLG0yIcZMIkVuaCJtvs6ytQWDb3uUe5MrjxIRxbt;

    /* renamed from: B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T  reason: collision with other field name */
    private Float f415B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;

    /* renamed from: B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T  reason: collision with other field name */
    private HashMap<Marker, Integer> f416B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
    private float Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB;
    private boolean HBxetIRrPp5wFSU0fYFDhqCxdFt0fBh3kY2YtziyusOgRcjzKRD45EhStHcUhuz0;
    private int I5ykmeJTgzyaiiAtvMLW8fRCQIZ9OEp56mK3swW1OQk12Icvz43SQQl0b809z20Q;
    private boolean KmWMzrb8pAMl652ZF8qeuTq7pG7ZDLw36UnzkrmNxV5DvooPwgtf6GDSAEiAO9jG;
    private int PJTNEFub7t830GnsJOreZR2G4QGerhYk5JzZTaNM1rn2OjCmiPf1GPWMoDtnF4UY;
    private int PW8gZZwgOCTWcD2kHUmkv6AgL0mFh4d5ZW9zStIiy43FwfJpRxeUshErA0Pq6Vc5;
    private int PnDqXmvCFreSiRVUA0g9XAstwDhIMcbRjkGmb4HpU47gHx12PSApiyWiV1UWvlkR;
    private String SHSZV5sTay8ykRHafXU624sH0bmI6VeYHAe3FtLV8LV4djzabBBIyQGaIvRsAwmk;

    /* renamed from: SHSZV5sTay8ykRHafXU624sH0bmI6VeYHAe3FtLV8LV4djzabBBIyQGaIvRsAwmk  reason: collision with other field name */
    private boolean f417SHSZV5sTay8ykRHafXU624sH0bmI6VeYHAe3FtLV8LV4djzabBBIyQGaIvRsAwmk = false;
    private boolean SzducuiMqWJn2qdHTwu7wT0uVCkk0nPPJy3Na6C8gzyeOLBidRCWmYhIkQdo5gq3 = false;
    private boolean UCfQaCFj2ASJbfyESztjl1SvQCaE7JCSj517yoZD3j82N9syinSTFfGgPUXIenmO = false;
    private String XkTExsgDjzL7UIaxQorrmSTr7jZbDxCmXSVfTpg7zAbwFxeOpcI2x1hAyx12QFiS;

    /* renamed from: XkTExsgDjzL7UIaxQorrmSTr7jZbDxCmXSVfTpg7zAbwFxeOpcI2x1hAyx12QFiS  reason: collision with other field name */
    private boolean f418XkTExsgDjzL7UIaxQorrmSTr7jZbDxCmXSVfTpg7zAbwFxeOpcI2x1hAyx12QFiS = false;
    private boolean YPNDRC6guNtR9Y1y2H2jw2eNOGhWZENW1YRifRdfZVF6tZ3Hhm9vVsLmqobDf9o1 = false;
    private boolean ZYL9KAfl6ZZzM9RsykyXLexYTPR8S0eQ9Guil6cW84HmbyBTkvTBFTgEwGE4p6T = true;
    private final Handler androidUIHandler;
    private String eQeQhiSmlElGLbwx31fXfv53XsxBlAuCtzOUdhRtXefyKZkKyhRMzOCKSg7WR08;

    /* renamed from: eQeQhiSmlElGLbwx31fXfv53XsxBlAuCtzOUdhRtXefyKZkKyhRMzOCKSg7WR08  reason: collision with other field name */
    private boolean f419eQeQhiSmlElGLbwx31fXfv53XsxBlAuCtzOUdhRtXefyKZkKyhRMzOCKSg7WR08 = true;
    private boolean fSI6lxX8qEfUYa0M3qSNEhqEY7tqyd89UewYfJ8WSYLJpTsAFdRvTVg7ORBsMzG8;
    private final Form form;
    private HashMap<Polygon, Integer> hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO;
    private boolean havePermission;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private final Activity f420hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private Bundle f421hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private LinearLayout f422hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private GoogleApiClient f423hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private com.google.android.gms.maps.GoogleMap f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private SupportMapFragment f425hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private UiSettings f426hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private LatLng f427hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private Float f428hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private List<C0730a> l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j;
    private final String muMCPHZoR5LDcgNaOoyYPcH1tjqU9LHoHV7Yzgex9Dj2uE6xbcPRtlEMsWbJpsbN;

    /* renamed from: muMCPHZoR5LDcgNaOoyYPcH1tjqU9LHoHV7Yzgex9Dj2uE6xbcPRtlEMsWbJpsbN  reason: collision with other field name */
    private boolean f429muMCPHZoR5LDcgNaOoyYPcH1tjqU9LHoHV7Yzgex9Dj2uE6xbcPRtlEMsWbJpsbN = true;
    private int opkAxsBiNe4U2WnUIj3h2psrvR65Yw7R3K1A4XT1tnHgd80YyvpOyialIyu2UQYR;
    private int vB3WjEtL56PUGm0spJ96S19MI1O4vPR6yju8tUYcKrC4atk0AV5GbVcHQNB7BlIK = 1;

    /* renamed from: vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq  reason: collision with other field name */
    private final HashMap<Integer, Polyline> f430vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq;

    /* renamed from: wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou  reason: collision with other field name */
    private YailList f431wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;

    /* renamed from: wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou  reason: collision with other field name */
    private Float f432wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;

    /* renamed from: wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou  reason: collision with other field name */
    private HashMap<Object, Integer> f433wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;
    private boolean xaqHe1zmXpFIxCQ2Rqyod5yEaSTeb7CYPqkTrZecvYFjGogfgX23htIKbmu7xARG;

    static {
        AtomicInteger atomicInteger;
        AtomicInteger atomicInteger2;
        AtomicInteger atomicInteger3;
        AtomicInteger atomicInteger4;
        new AtomicInteger(1);
        f414hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = atomicInteger;
        new AtomicInteger(1);
        B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = atomicInteger2;
        new AtomicInteger(1);
        wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = atomicInteger3;
        new AtomicInteger(1);
        vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = atomicInteger4;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public GoogleMap(com.google.appinventor.components.runtime.ComponentContainer r12) throws java.io.IOException {
        /*
            r11 = this;
            r0 = r11
            r1 = r12
            r4 = r0
            r5 = r1
            r4.<init>(r5)
            r4 = r0
            java.util.HashMap r5 = new java.util.HashMap
            r10 = r5
            r5 = r10
            r6 = r10
            r6.<init>()
            r4.f416B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = r5
            r4 = r0
            r5 = 1
            r4.vB3WjEtL56PUGm0spJ96S19MI1O4vPR6yju8tUYcKrC4atk0AV5GbVcHQNB7BlIK = r5
            r4 = r0
            r5 = 0
            r4.YPNDRC6guNtR9Y1y2H2jw2eNOGhWZENW1YRifRdfZVF6tZ3Hhm9vVsLmqobDf9o1 = r5
            r4 = r0
            r5 = 0
            r4.UCfQaCFj2ASJbfyESztjl1SvQCaE7JCSj517yoZD3j82N9syinSTFfGgPUXIenmO = r5
            r4 = r0
            r5 = 1
            r4.ZYL9KAfl6ZZzM9RsykyXLexYTPR8S0eQ9Guil6cW84HmbyBTkvTBFTgEwGE4p6T = r5
            r4 = r0
            r5 = 1
            r4.f429muMCPHZoR5LDcgNaOoyYPcH1tjqU9LHoHV7Yzgex9Dj2uE6xbcPRtlEMsWbJpsbN = r5
            r4 = r0
            r5 = 0
            r4.f418XkTExsgDjzL7UIaxQorrmSTr7jZbDxCmXSVfTpg7zAbwFxeOpcI2x1hAyx12QFiS = r5
            r4 = r0
            r5 = 1
            r4.f419eQeQhiSmlElGLbwx31fXfv53XsxBlAuCtzOUdhRtXefyKZkKyhRMzOCKSg7WR08 = r5
            r4 = r0
            r5 = 0
            r4.f417SHSZV5sTay8ykRHafXU624sH0bmI6VeYHAe3FtLV8LV4djzabBBIyQGaIvRsAwmk = r5
            r4 = r0
            r5 = 0
            r4.SzducuiMqWJn2qdHTwu7wT0uVCkk0nPPJy3Na6C8gzyeOLBidRCWmYhIkQdo5gq3 = r5
            r4 = r0
            r10 = r4
            r4 = r10
            r5 = r10
            java.lang.String r5 = r5.SHSZV5sTay8ykRHafXU624sH0bmI6VeYHAe3FtLV8LV4djzabBBIyQGaIvRsAwmk
            r4.eQeQhiSmlElGLbwx31fXfv53XsxBlAuCtzOUdhRtXefyKZkKyhRMzOCKSg7WR08 = r5
            r4 = r0
            java.lang.String r5 = "standard"
            r4.SHSZV5sTay8ykRHafXU624sH0bmI6VeYHAe3FtLV8LV4djzabBBIyQGaIvRsAwmk = r5
            r4 = r0
            r5 = 0
            r4.HBxetIRrPp5wFSU0fYFDhqCxdFt0fBh3kY2YtziyusOgRcjzKRD45EhStHcUhuz0 = r5
            r4 = r0
            r5 = -14575886(0xffffffffff2196f2, float:-2.1478946E38)
            r4.PJTNEFub7t830GnsJOreZR2G4QGerhYk5JzZTaNM1rn2OjCmiPf1GPWMoDtnF4UY = r5
            r4 = r0
            r5 = 0
            r4.KmWMzrb8pAMl652ZF8qeuTq7pG7ZDLw36UnzkrmNxV5DvooPwgtf6GDSAEiAO9jG = r5
            r4 = r0
            r5 = 0
            r4.xaqHe1zmXpFIxCQ2Rqyod5yEaSTeb7CYPqkTrZecvYFjGogfgX23htIKbmu7xARG = r5
            r4 = r0
            r5 = 0
            r4.AGGRF5jTQInc5pUFaFXws4RnGLG0yIcZMIkVuaCJtvs6ytQWDb3uUe5MrjxIRxbt = r5
            r4 = r0
            r5 = 0
            r4.fSI6lxX8qEfUYa0M3qSNEhqEY7tqyd89UewYfJ8WSYLJpTsAFdRvTVg7ORBsMzG8 = r5
            r4 = r0
            java.util.HashMap r5 = new java.util.HashMap
            r10 = r5
            r5 = r10
            r6 = r10
            r6.<init>()
            r4.f433wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = r5
            r4 = r0
            java.util.ArrayList r5 = new java.util.ArrayList
            r10 = r5
            r5 = r10
            r6 = r10
            r7 = 1
            r6.<init>(r7)
            r4.l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j = r5
            r4 = r0
            java.util.HashMap r5 = new java.util.HashMap
            r10 = r5
            r5 = r10
            r6 = r10
            r6.<init>()
            r4.f430vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq = r5
            r4 = r0
            java.util.HashMap r5 = new java.util.HashMap
            r10 = r5
            r5 = r10
            r6 = r10
            r6.<init>()
            r4.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO = r5
            r4 = r0
            r5 = 1073741824(0x40000000, float:2.0)
            r4.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB = r5
            r4 = r0
            r5 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r4.opkAxsBiNe4U2WnUIj3h2psrvR65Yw7R3K1A4XT1tnHgd80YyvpOyialIyu2UQYR = r5
            r4 = r0
            r5 = 0
            r4.PW8gZZwgOCTWcD2kHUmkv6AgL0mFh4d5ZW9zStIiy43FwfJpRxeUshErA0Pq6Vc5 = r5
            r4 = r0
            r5 = 20
            r4.PnDqXmvCFreSiRVUA0g9XAstwDhIMcbRjkGmb4HpU47gHx12PSApiyWiV1UWvlkR = r5
            r4 = r0
            r10 = r4
            r4 = r10
            r5 = r10
            int r5 = r5.PnDqXmvCFreSiRVUA0g9XAstwDhIMcbRjkGmb4HpU47gHx12PSApiyWiV1UWvlkR
            r6 = 3
            float[] r6 = new float[r6]
            r6 = {0, 1065353216, 1065353216} // fill-array
            int r5 = android.graphics.Color.HSVToColor(r5, r6)
            r4.I5ykmeJTgzyaiiAtvMLW8fRCQIZ9OEp56mK3swW1OQk12Icvz43SQQl0b809z20Q = r5
            r4 = r0
            android.os.Handler r5 = new android.os.Handler
            r10 = r5
            r5 = r10
            r6 = r10
            r6.<init>()
            r4.androidUIHandler = r5
            r4 = r0
            r5 = 0
            r4.havePermission = r5
            java.lang.String r4 = "GoogleMap"
            java.lang.String r5 = "In the constructor of GoogleMap"
            int r4 = android.util.Log.i(r4, r5)
            r4 = r0
            r5 = r1
            android.app.Activity r5 = r5.$context()
            r4.f420hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5
            r4 = r0
            r5 = r1
            com.google.appinventor.components.runtime.Form r5 = r5.$form()
            r4.form = r5
            r4 = r0
            r10 = r4
            r4 = r10
            r5 = r10
            com.google.appinventor.components.runtime.Form r5 = r5.form
            android.os.Bundle r5 = r5.getOnCreateBundle()
            r4.f421hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5
            java.lang.String r4 = "GoogleMap"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r10 = r5
            r5 = r10
            r6 = r10
            java.lang.String r7 = "savedInstanceState in GM: "
            r6.<init>(r7)
            r6 = r0
            android.os.Bundle r6 = r6.f421hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            int r4 = android.util.Log.i(r4, r5)
            r4 = r0
            android.widget.LinearLayout r5 = new android.widget.LinearLayout
            r10 = r5
            r5 = r10
            r6 = r10
            r7 = r0
            android.app.Activity r7 = r7.f420hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r6.<init>(r7)
            r4.f422hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5
            r4 = r0
            android.widget.LinearLayout r4 = r4.f422hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            int r5 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME()
            r4.setId(r5)
            r4 = r0
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r10 = r5
            r5 = r10
            r6 = r10
            java.lang.String r7 = "map_"
            r6.<init>(r7)
            long r6 = java.lang.System.currentTimeMillis()
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            r4.muMCPHZoR5LDcgNaOoyYPcH1tjqU9LHoHV7Yzgex9Dj2uE6xbcPRtlEMsWbJpsbN = r5
            java.lang.String r4 = "GoogleMap"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r10 = r5
            r5 = r10
            r6 = r10
            java.lang.String r7 = "map_tag:"
            r6.<init>(r7)
            r6 = r0
            java.lang.String r6 = r6.muMCPHZoR5LDcgNaOoyYPcH1tjqU9LHoHV7Yzgex9Dj2uE6xbcPRtlEMsWbJpsbN
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            int r4 = android.util.Log.i(r4, r5)
            r4 = r0
            r10 = r4
            r4 = r10
            r5 = r10
            r2 = r5
            android.app.Activity r4 = r4.f420hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            int r4 = com.google.android.gms.common.GooglePlayServicesUtil.isGooglePlayServicesAvailable(r4)
            r3 = r4
            java.lang.String r4 = "GoogleMap"
            java.lang.String r5 = "googlePlayServicesAvailable:"
            r6 = r3
            java.lang.String r6 = java.lang.String.valueOf(r6)
            java.lang.String r5 = r5.concat(r6)
            int r4 = android.util.Log.i(r4, r5)
            r4 = r3
            switch(r4) {
                case 1: goto L_0x020d;
                case 2: goto L_0x021e;
                case 3: goto L_0x022f;
                case 4: goto L_0x0174;
                case 5: goto L_0x0174;
                case 6: goto L_0x0174;
                case 7: goto L_0x0174;
                case 8: goto L_0x0174;
                case 9: goto L_0x0240;
                default: goto L_0x0174;
            }
        L_0x0174:
            r4 = r0
            r2 = r4
            r4 = r2
            android.app.Activity r4 = r4.f420hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME     // Catch:{ NameNotFoundException -> 0x0251 }
            android.content.pm.PackageManager r4 = r4.getPackageManager()     // Catch:{ NameNotFoundException -> 0x0251 }
            java.lang.String r5 = "com.google.android.apps.maps"
            r6 = 0
            android.content.pm.ApplicationInfo r4 = r4.getApplicationInfo(r5, r6)     // Catch:{ NameNotFoundException -> 0x0251 }
        L_0x0185:
            r4 = r0
            r10 = r4
            r4 = r10
            r5 = r10
            com.google.appinventor.components.runtime.Form r5 = r5.form
            android.support.v4.app.FragmentManager r5 = r5.getSupportFragmentManager()
            r6 = r0
            java.lang.String r6 = r6.muMCPHZoR5LDcgNaOoyYPcH1tjqU9LHoHV7Yzgex9Dj2uE6xbcPRtlEMsWbJpsbN
            android.support.v4.app.Fragment r5 = r5.findFragmentByTag(r6)
            com.google.android.gms.maps.SupportMapFragment r5 = (com.google.android.gms.maps.SupportMapFragment) r5
            r4.f425hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5
            r4 = r0
            com.google.android.gms.maps.SupportMapFragment r4 = r4.f425hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            if (r4 != 0) goto L_0x01dd
            java.lang.String r4 = "GoogleMap"
            java.lang.String r5 = "mMapFragment is null."
            int r4 = android.util.Log.i(r4, r5)
            r4 = r0
            com.google.android.gms.maps.SupportMapFragment r5 = com.google.android.gms.maps.SupportMapFragment.newInstance()
            r4.f425hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5
            r4 = r0
            com.google.appinventor.components.runtime.Form r4 = r4.form
            android.support.v4.app.FragmentManager r4 = r4.getSupportFragmentManager()
            android.support.v4.app.FragmentTransaction r4 = r4.beginTransaction()
            r2 = r4
            java.lang.String r4 = "GoogleMap"
            java.lang.String r5 = "here before adding fragment"
            int r4 = android.util.Log.i(r4, r5)
            r4 = r2
            r5 = r0
            android.widget.LinearLayout r5 = r5.f422hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            int r5 = r5.getId()
            r6 = r0
            com.google.android.gms.maps.SupportMapFragment r6 = r6.f425hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r7 = r0
            java.lang.String r7 = r7.muMCPHZoR5LDcgNaOoyYPcH1tjqU9LHoHV7Yzgex9Dj2uE6xbcPRtlEMsWbJpsbN
            android.support.v4.app.FragmentTransaction r4 = r4.replace(r5, r6, r7)
            r4 = r2
            int r4 = r4.commit()
        L_0x01dd:
            r4 = r0
            r4.Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB()
            r4 = r1
            r5 = r0
            r4.$add(r5)
            r4 = r0
            r5 = -2
            r4.Width(r5)
            r4 = r0
            r5 = -2
            r4.Height(r5)
            r4 = r0
            com.google.appinventor.components.runtime.Form r4 = r4.form
            r5 = r0
            r4.registerForOnInitialize(r5)
            r4 = r0
            com.google.appinventor.components.runtime.Form r4 = r4.form
            r5 = r0
            r4.registerForOnResume(r5)
            r4 = r0
            com.google.appinventor.components.runtime.Form r4 = r4.form
            r5 = r0
            r4.registerForOnResume(r5)
            r4 = r0
            com.google.appinventor.components.runtime.Form r4 = r4.form
            r5 = r0
            r4.registerForOnPause(r5)
            return
        L_0x020d:
            r4 = r2
            com.google.appinventor.components.runtime.Form r4 = r4.form
            r5 = r2
            java.lang.String r6 = "checkGooglePlayServiceSDK"
            r7 = 12011(0x2eeb, float:1.6831E-41)
            r8 = 0
            java.lang.Object[] r8 = new java.lang.Object[r8]
            r4.dispatchErrorOccurredEvent(r5, r6, r7, r8)
            goto L_0x0174
        L_0x021e:
            r4 = r2
            com.google.appinventor.components.runtime.Form r4 = r4.form
            r5 = r2
            java.lang.String r6 = "checkGooglePlayServiceSDK"
            r7 = 12016(0x2ef0, float:1.6838E-41)
            r8 = 0
            java.lang.Object[] r8 = new java.lang.Object[r8]
            r4.dispatchErrorOccurredEvent(r5, r6, r7, r8)
            goto L_0x0174
        L_0x022f:
            r4 = r2
            com.google.appinventor.components.runtime.Form r4 = r4.form
            r5 = r2
            java.lang.String r6 = "checkGooglePlayServiceSDK"
            r7 = 12017(0x2ef1, float:1.684E-41)
            r8 = 0
            java.lang.Object[] r8 = new java.lang.Object[r8]
            r4.dispatchErrorOccurredEvent(r5, r6, r7, r8)
            goto L_0x0174
        L_0x0240:
            r4 = r2
            com.google.appinventor.components.runtime.Form r4 = r4.form
            r5 = r2
            java.lang.String r6 = "checkGooglePlayServiceSDK"
            r7 = 12018(0x2ef2, float:1.6841E-41)
            r8 = 0
            java.lang.Object[] r8 = new java.lang.Object[r8]
            r4.dispatchErrorOccurredEvent(r5, r6, r7, r8)
            goto L_0x0174
        L_0x0251:
            r4 = move-exception
            r4 = r2
            com.google.appinventor.components.runtime.Form r4 = r4.form
            r5 = r2
            java.lang.String r6 = "checkGoogleMapInstalled"
            r7 = 12010(0x2eea, float:1.683E-41)
            r8 = 0
            java.lang.Object[] r8 = new java.lang.Object[r8]
            r4.dispatchErrorOccurredEvent(r5, r6, r7, r8)
            goto L_0x0185
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.GoogleMap.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    private static int hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME() {
        int i;
        int i2;
        do {
            int i3 = wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.get();
            i = i3;
            int i4 = i3 + 1;
            i2 = i4;
            if (i4 > 16777215) {
                i2 = 1;
            }
        } while (!wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.compareAndSet(i, i2));
        return i;
    }

    @SimpleProperty
    public void Width(int i) {
        int i2 = i;
        if (i2 == -1) {
            i2 = -2;
        }
        super.Width(i2);
    }

    @SimpleProperty
    public void Height(int i) {
        int i2 = i;
        if (i2 == -1) {
            i2 = -2;
        }
        super.Height(i2);
    }

    private void Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB() {
        if (this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            this.HBxetIRrPp5wFSU0fYFDhqCxdFt0fBh3kY2YtziyusOgRcjzKRD45EhStHcUhuz0 = false;
            int i = Log.i("GoogleMap", "setUpMapIfNeeded. mMap is null");
            this.f425hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getMapAsync(this);
            return;
        }
        this.HBxetIRrPp5wFSU0fYFDhqCxdFt0fBh3kY2YtziyusOgRcjzKRD45EhStHcUhuz0 = true;
    }

    public void onMapReady(com.google.android.gms.maps.GoogleMap googleMap) {
        CameraPosition.Builder builder;
        int i = Log.i("GoogleMap", "Yes, we have a google map...");
        this.HBxetIRrPp5wFSU0fYFDhqCxdFt0fBh3kY2YtziyusOgRcjzKRD45EhStHcUhuz0 = true;
        this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = googleMap;
        new CameraPosition.Builder(this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getCameraPosition());
        CameraPosition.Builder builder2 = builder;
        if (this.f427hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            CameraPosition.Builder target = builder2.target(this.f427hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        }
        if (this.f428hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            CameraPosition.Builder zoom = builder2.zoom(this.f428hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.floatValue());
        }
        if (this.f432wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou != null) {
            CameraPosition.Builder tilt = builder2.tilt(this.f432wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.floatValue());
        }
        if (this.f415B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T != null) {
            CameraPosition.Builder bearing = builder2.bearing(this.f415B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.floatValue());
        }
        this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.moveCamera(CameraUpdateFactory.newCameraPosition(builder2.build()));
        if (this.XkTExsgDjzL7UIaxQorrmSTr7jZbDxCmXSVfTpg7zAbwFxeOpcI2x1hAyx12QFiS != null) {
            Style(this.XkTExsgDjzL7UIaxQorrmSTr7jZbDxCmXSVfTpg7zAbwFxeOpcI2x1hAyx12QFiS);
        } else if (this.eQeQhiSmlElGLbwx31fXfv53XsxBlAuCtzOUdhRtXefyKZkKyhRMzOCKSg7WR08 != this.SHSZV5sTay8ykRHafXU624sH0bmI6VeYHAe3FtLV8LV4djzabBBIyQGaIvRsAwmk) {
            Theme(this.eQeQhiSmlElGLbwx31fXfv53XsxBlAuCtzOUdhRtXefyKZkKyhRMzOCKSg7WR08);
        }
        int i2 = Log.i("GoogleMap", "in setUpMap()");
        this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setOnMarkerClickListener(this);
        this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setOnInfoWindowClickListener(this);
        this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setOnMarkerDragListener(this);
        this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setOnPoiClickListener(this);
        this.f426hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getUiSettings();
        this.f426hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setCompassEnabled(this.UCfQaCFj2ASJbfyESztjl1SvQCaE7JCSj517yoZD3j82N9syinSTFfGgPUXIenmO);
        this.f426hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setRotateGesturesEnabled(this.ZYL9KAfl6ZZzM9RsykyXLexYTPR8S0eQ9Guil6cW84HmbyBTkvTBFTgEwGE4p6T);
        this.f426hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setScrollGesturesEnabled(this.f429muMCPHZoR5LDcgNaOoyYPcH1tjqU9LHoHV7Yzgex9Dj2uE6xbcPRtlEMsWbJpsbN);
        this.f426hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setZoomControlsEnabled(this.f418XkTExsgDjzL7UIaxQorrmSTr7jZbDxCmXSVfTpg7zAbwFxeOpcI2x1hAyx12QFiS);
        this.f426hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setZoomGesturesEnabled(this.f419eQeQhiSmlElGLbwx31fXfv53XsxBlAuCtzOUdhRtXefyKZkKyhRMzOCKSg7WR08);
        MapIsReady();
    }

    private void sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt() {
        GoogleApiClient.Builder builder;
        if (this.f423hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            GoogleApiClient.Builder builder2 = builder;
            new GoogleApiClient.Builder(this.f420hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, this, this);
            this.f423hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = builder2.addApi(LocationServices.API).addConnectionCallbacks(this).addOnConnectionFailedListener(this).build();
        }
    }

    @SimpleFunction(description = "Enables/disables the compass widget on the map's ui. Call this only after event \"MapIsReady\" is received")
    public void EnableCompass(boolean z) {
        boolean z2 = z;
        if (this.HBxetIRrPp5wFSU0fYFDhqCxdFt0fBh3kY2YtziyusOgRcjzKRD45EhStHcUhuz0) {
            this.UCfQaCFj2ASJbfyESztjl1SvQCaE7JCSj517yoZD3j82N9syinSTFfGgPUXIenmO = z2;
            this.f426hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setCompassEnabled(z2);
        }
    }

    @SimpleProperty(description = "Indicates whether the compass widget is currently enabled in the map ui")
    public boolean CompassEnabled() {
        return this.f426hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isCompassEnabled();
    }

    @SimpleFunction(description = "Enables/disables the capability to rotate a map on the ui. Call this only after the event \"MapIsReady\" is received.")
    public void EnableRotate(boolean z) {
        boolean z2 = z;
        if (this.HBxetIRrPp5wFSU0fYFDhqCxdFt0fBh3kY2YtziyusOgRcjzKRD45EhStHcUhuz0) {
            this.ZYL9KAfl6ZZzM9RsykyXLexYTPR8S0eQ9Guil6cW84HmbyBTkvTBFTgEwGE4p6T = z2;
            this.f426hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setRotateGesturesEnabled(z2);
        }
    }

    @SimpleProperty(description = "Indicates whether the capability to rotate a map on the ui is currently enabled")
    public boolean RotateEnabled() {
        return this.f426hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isRotateGesturesEnabled();
    }

    @SimpleFunction(description = "Enables/disables the capability to scroll a map on the ui. Call this only after the event \"MapIsReady\" is received")
    public void EnableScroll(boolean z) {
        boolean z2 = z;
        if (this.HBxetIRrPp5wFSU0fYFDhqCxdFt0fBh3kY2YtziyusOgRcjzKRD45EhStHcUhuz0) {
            this.f429muMCPHZoR5LDcgNaOoyYPcH1tjqU9LHoHV7Yzgex9Dj2uE6xbcPRtlEMsWbJpsbN = z2;
            this.f426hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setScrollGesturesEnabled(z2);
        }
    }

    @SimpleProperty(description = "Indicates whether the capability to scroll a map on the ui is currently enabled")
    public boolean ScrollEnabled() {
        return this.f426hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isScrollGesturesEnabled();
    }

    @SimpleFunction(description = "Enables/disables the zoom widget on the map's ui. Call this only after the event \"MapIsReady\" is received")
    public void EnableZoomControl(boolean z) {
        boolean z2 = z;
        this.f418XkTExsgDjzL7UIaxQorrmSTr7jZbDxCmXSVfTpg7zAbwFxeOpcI2x1hAyx12QFiS = z2;
        this.f426hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setZoomControlsEnabled(z2);
    }

    @SimpleProperty(description = "Indicates whether the zoom widget on the map ui is currently enabled")
    public boolean ZoomControlEnabled() {
        return this.f426hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isZoomControlsEnabled();
    }

    @SimpleFunction(description = "Enables/disables zoom gesture on the map ui. Call this only after the event  \"MapIsReady\" is received. ")
    public void EnableZoomGesture(boolean z) {
        boolean z2 = z;
        if (this.HBxetIRrPp5wFSU0fYFDhqCxdFt0fBh3kY2YtziyusOgRcjzKRD45EhStHcUhuz0) {
            this.f419eQeQhiSmlElGLbwx31fXfv53XsxBlAuCtzOUdhRtXefyKZkKyhRMzOCKSg7WR08 = z2;
            this.f426hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setZoomGesturesEnabled(z2);
        }
    }

    @SimpleProperty(description = "Indicates whether the zoom gesture is currently enabled")
    public boolean ZoomGestureEnabled() {
        return this.f426hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isZoomGesturesEnabled();
    }

    @SimpleEvent(description = "Indicates that the map has been rendered and ready for adding markers or changing other settings. Please add or updating markers within this event")
    public void MapIsReady() {
        int i = Log.i("GoogleMap", "Map is ready for adding markers and other setting");
        if (this.HBxetIRrPp5wFSU0fYFDhqCxdFt0fBh3kY2YtziyusOgRcjzKRD45EhStHcUhuz0 && this.f417SHSZV5sTay8ykRHafXU624sH0bmI6VeYHAe3FtLV8LV4djzabBBIyQGaIvRsAwmk && !this.SzducuiMqWJn2qdHTwu7wT0uVCkk0nPPJy3Na6C8gzyeOLBidRCWmYhIkQdo5gq3) {
            this.SzducuiMqWJn2qdHTwu7wT0uVCkk0nPPJy3Na6C8gzyeOLBidRCWmYhIkQdo5gq3 = true;
            int i2 = Log.i("GoogleMap", "Map is ready for adding markers and other setting");
            boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "MapIsReady", new Object[0]);
        }
    }

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other method in class */
    private Object m60hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(int i) {
        int i2 = i;
        Object hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f433wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou, Integer.valueOf(i2));
        Object obj = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2;
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 != null) {
            return obj;
        }
        this.form.dispatchErrorOccurredEvent(this, "getCircleIfExisted", ErrorMessages.ERROR_GOOGLE_MAP_CIRCLE_NOT_EXIST, Integer.toString(i2));
        return null;
    }

    @SimpleFunction(description = "Remove a circle for the map. Returns true if successfully removed, false if the circle does not exist with the specified id")
    public boolean RemoveCircle(int i) {
        Object hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f433wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou, Integer.valueOf(i));
        Object obj = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2;
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 == null) {
            return false;
        }
        if (obj instanceof C0730a) {
            C0730a aVar = (C0730a) obj;
            C0730a aVar2 = aVar;
            aVar.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.remove();
            aVar2.f436hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.remove();
            aVar2.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.remove();
            boolean remove = this.l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j.remove(obj);
        }
        if (obj instanceof Circle) {
            ((Circle) obj).remove();
        }
        Integer remove2 = this.f433wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.remove(obj);
        return true;
    }

    @SimpleFunction(description = "Set the property of an existing circle. Properties include: \"alpha\"(number, value ranging from 0~255), \"color\" (nimber, hue value ranging 0~360), \"radius\"(number in meters)")
    public void UpdateCircle(int i, String str, Object obj) {
        StringBuilder sb;
        MarkerOptions markerOptions;
        int i2 = i;
        String str2 = str;
        Object obj2 = obj;
        if (this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            new StringBuilder("inputs: ");
            int i3 = Log.i("GoogleMap", sb.append(i2).append(",").append(str2).append(", ").append(obj2).toString());
            float[] fArr = new float[3];
            Object hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(i2);
            Circle circle = null;
            if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 != null) {
                if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 instanceof C0730a) {
                    circle = ((C0730a) hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2).hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                }
                if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 instanceof Circle) {
                    circle = (Circle) hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2;
                }
                try {
                    Float valueOf = Float.valueOf(Float.parseFloat(obj2.toString()));
                    if (str2.equals("alpha")) {
                        Color.colorToHSV(circle.getFillColor(), fArr);
                        circle.setFillColor(Color.HSVToColor(valueOf.intValue(), fArr));
                    }
                    if (str2.equals(PropertyTypeConstants.PROPERTY_TYPE_COLOR)) {
                        int alpha = Color.alpha(circle.getFillColor());
                        float[] fArr2 = new float[3];
                        fArr2[0] = valueOf.floatValue();
                        float[] fArr3 = fArr2;
                        fArr3[1] = 1.0f;
                        float[] fArr4 = fArr3;
                        fArr4[2] = 1.0f;
                        circle.setFillColor(Color.HSVToColor(alpha, fArr4));
                    }
                    if (str2.equals("radius")) {
                        circle.setRadius((double) valueOf.floatValue());
                        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 instanceof C0730a) {
                            Marker marker = ((C0730a) hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2).f436hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                            ((C0730a) hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2).B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.remove();
                            com.google.android.gms.maps.GoogleMap googleMap = this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                            new MarkerOptions();
                            ((C0730a) hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2).B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = googleMap.addMarker(markerOptions.position(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(marker.getPosition(), (double) valueOf.floatValue())).draggable(true).icon(BitmapDescriptorFactory.defaultMarker(210.0f)));
                        }
                    }
                } catch (NumberFormatException e) {
                    this.form.dispatchErrorOccurredEvent(this, "UpdateCircle", ErrorMessages.ERROR_GOOGLE_MAP_INVALID_INPUT, obj2.toString());
                }
            } else {
                this.form.dispatchErrorOccurredEvent(this, "UpdateCircle", ErrorMessages.ERROR_GOOGLE_MAP_CIRCLE_NOT_EXIST, Integer.valueOf(i2));
            }
        }
    }

    @SimpleFunction(description = "Get all circles Ids. A short cut to get all the references for the eixisting circles")
    public YailList GetAllCircleIDs() {
        return YailList.makeList((Collection) this.f433wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.values());
    }

    @SimpleEvent(description = "Event been raised after the action of moving a draggable circle is finished. Possible a user drag the center of the circle or drag the radius marker of the circle")
    public void FinishedDraggingCircle(int i, double d, double d2, double d3) {
        Runnable runnable;
        final int i2 = i;
        final double d4 = d;
        final double d5 = d2;
        final double d6 = d3;
        new Runnable(this) {
            private /* synthetic */ GoogleMap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r14;
            }

            public final void run() {
                StringBuilder sb;
                new StringBuilder("a draggable circle:");
                int i = Log.i("GoogleMap", sb.append(i2).append("finished been dragged").toString());
                GoogleMap googleMap = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                Object[] objArr = new Object[4];
                objArr[0] = Integer.valueOf(i2);
                Object[] objArr2 = objArr;
                objArr2[1] = Double.valueOf(d4);
                Object[] objArr3 = objArr2;
                objArr3[2] = Double.valueOf(d5);
                Object[] objArr4 = objArr3;
                objArr4[3] = Double.valueOf(d6);
                boolean dispatchEvent = EventDispatcher.dispatchEvent(googleMap, "FinishedDraggingCircle", objArr4);
            }
        };
        this.f420hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.runOnUiThread(runnable);
    }

    public View getView() {
        return this.f422hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    public void onResume() {
        int i = Log.i("GoogleMap", "in onResume...Google Map redraw");
        if (this.YPNDRC6guNtR9Y1y2H2jw2eNOGhWZENW1YRifRdfZVF6tZ3Hhm9vVsLmqobDf9o1 && this.f423hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt();
            this.f423hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.connect();
        }
        Co1rSUoyBoOJ9n0NOgrpxvTYmRPklnETeodUBiQlSBK151C8PCv1Q1Pl4SUa1qxB();
    }

    public void onInitialize() {
        this.f417SHSZV5sTay8ykRHafXU624sH0bmI6VeYHAe3FtLV8LV4djzabBBIyQGaIvRsAwmk = true;
        MapIsReady();
    }

    @SimpleFunction(description = "Enable or disable my location widget control for Google Map. One can call GetMyLocation() to obtain the current location after enable this.\"")
    public void EnableMyLocation(boolean z) {
        Runnable runnable;
        boolean z2 = z;
        if (!this.havePermission) {
            final boolean z3 = z2;
            new Runnable(this) {
                final /* synthetic */ GoogleMap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
                }

                public final void run() {
                    PermissionResultHandler permissionResultHandler;
                    new PermissionResultHandler(this) {
                        private /* synthetic */ C07256 hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                        {
                            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                        }

                        public final void HandlePermissionResponse(String str, boolean z) {
                            String str2 = str;
                            boolean z2 = z;
                            if (z2) {
                                boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = GoogleMap.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, true);
                                GoogleMap.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, z3);
                                return;
                            }
                            boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME3 = GoogleMap.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, false);
                            GoogleMap.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, z2);
                            GoogleMap.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).dispatchPermissionDeniedEvent((Component) this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "EnableMyLocation", "android.permission.ACCESS_FINE_LOCATION");
                        }
                    };
                    GoogleMap.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).askPermission("android.permission.ACCESS_FINE_LOCATION", permissionResultHandler);
                }
            };
            boolean post = this.androidUIHandler.post(runnable);
            return;
        }
        B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(z2);
    }

    private void B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(boolean z) {
        StringBuilder sb;
        boolean z2 = z;
        int i = Log.i("GoogleMap", "@EnableMyLocation:".concat(String.valueOf(z2)));
        if (this.YPNDRC6guNtR9Y1y2H2jw2eNOGhWZENW1YRifRdfZVF6tZ3Hhm9vVsLmqobDf9o1 != z2) {
            this.YPNDRC6guNtR9Y1y2H2jw2eNOGhWZENW1YRifRdfZVF6tZ3Hhm9vVsLmqobDf9o1 = z2;
        }
        if (this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            try {
                this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setMyLocationEnabled(z2);
                if (z2) {
                    sSQuIBUVrx5rcdxHqHgqC6bPLuuDnxnBF7e7BJeOxmxr54l6ArzFZvC3SGBTuUdt();
                    this.f423hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.connect();
                    return;
                }
                this.f423hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.disconnect();
            } catch (PermissionException e) {
                this.form.dispatchPermissionDeniedEvent((Component) this, "EnableMyLocation", e);
            } catch (Exception e2) {
                new StringBuilder();
                int e3 = Log.e("GoogleMap", sb.append(e2.getMessage()).toString());
            }
        }
    }

    @SimpleProperty(description = "Indicates whether my locaiton UI control is currently enabled for the Google map.")
    public boolean MyLocationEnabled() {
        return this.YPNDRC6guNtR9Y1y2H2jw2eNOGhWZENW1YRifRdfZVF6tZ3Hhm9vVsLmqobDf9o1;
    }

    @SimpleFunction(description = "Get current location using Google Map Service. Return a YailList with first item beingthe latitude, the second item being the longitude, and last time being the accuracy of the reading.")
    public YailList GetMyLocation() {
        ArrayList arrayList;
        new ArrayList();
        ArrayList arrayList2 = arrayList;
        if (this.f423hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null && this.f423hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isConnected()) {
            int i = Log.i("GoogleMap", "client is connected");
            Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(this.f423hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
            boolean add = arrayList2.add(Double.valueOf(lastLocation.getLatitude()));
            boolean add2 = arrayList2.add(Double.valueOf(lastLocation.getLongitude()));
            boolean add3 = arrayList2.add(Float.valueOf(lastLocation.getAccuracy()));
        }
        return YailList.makeList((List) arrayList2);
    }

    @SimpleFunction(description = "Set the layer of Google map. Default layer is \"normal\", other choices including \"hybrid\",\"satellite\", and \"terrain\" ")
    public void SetMapType(String str) {
        StringBuilder sb;
        String str2 = str;
        String str3 = str2;
        boolean z = true;
        switch (str3.hashCode()) {
            case -1579103941:
                if (str3.equals("satellite")) {
                    z = true;
                    break;
                }
                break;
            case -1423437003:
                if (str3.equals("terrain")) {
                    z = true;
                    break;
                }
                break;
            case -1202757124:
                if (str3.equals("hybrid")) {
                    z = true;
                    break;
                }
                break;
            case -1039745817:
                if (str3.equals("normal")) {
                    z = false;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
                this.vB3WjEtL56PUGm0spJ96S19MI1O4vPR6yju8tUYcKrC4atk0AV5GbVcHQNB7BlIK = 1;
                break;
            case true:
                this.vB3WjEtL56PUGm0spJ96S19MI1O4vPR6yju8tUYcKrC4atk0AV5GbVcHQNB7BlIK = 4;
                break;
            case true:
                this.vB3WjEtL56PUGm0spJ96S19MI1O4vPR6yju8tUYcKrC4atk0AV5GbVcHQNB7BlIK = 2;
                break;
            case true:
                this.vB3WjEtL56PUGm0spJ96S19MI1O4vPR6yju8tUYcKrC4atk0AV5GbVcHQNB7BlIK = 3;
                break;
            default:
                int i = Log.i("GoogleMap", "Error setting layer with name ".concat(String.valueOf(str2)));
                new StringBuilder();
                this.form.dispatchErrorOccurredEvent(this, "SetMapType", ErrorMessages.ERROR_GOOGLE_MAP_INVALID_INPUT, sb.append(str2).append(" is not the correct type").toString());
                break;
        }
        if (this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setMapType(this.vB3WjEtL56PUGm0spJ96S19MI1O4vPR6yju8tUYcKrC4atk0AV5GbVcHQNB7BlIK);
        }
    }

    @SimpleFunction(description = "Enable/Disable to listen to map's click event")
    public void EnableMapClickListener(boolean z) {
        boolean z2 = z;
        int i = Log.i("GoogleMap", "@EnableMapClickListener:".concat(String.valueOf(z2)));
        if (this.xaqHe1zmXpFIxCQ2Rqyod5yEaSTeb7CYPqkTrZecvYFjGogfgX23htIKbmu7xARG != z2) {
            this.xaqHe1zmXpFIxCQ2Rqyod5yEaSTeb7CYPqkTrZecvYFjGogfgX23htIKbmu7xARG = z2;
        }
        if (this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            int i2 = Log.i("GoogleMap", "enable map listener?: ".concat(String.valueOf(z2)));
            this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setOnMapClickListener(z2 ? this : null);
        }
    }

    @SimpleProperty(description = "Indicates if the mapClick event listener is currently enabled")
    public boolean MapClickListenerEnabled() {
        return this.xaqHe1zmXpFIxCQ2Rqyod5yEaSTeb7CYPqkTrZecvYFjGogfgX23htIKbmu7xARG;
    }

    @SimpleFunction(description = "Enable/disable to listen to map's long click event")
    public void EnableMapLongClickListener(boolean z) {
        boolean z2 = z;
        int i = Log.i("GoogleMap", "@EnableMapLongClickListener:".concat(String.valueOf(z2)));
        if (this.AGGRF5jTQInc5pUFaFXws4RnGLG0yIcZMIkVuaCJtvs6ytQWDb3uUe5MrjxIRxbt != z2) {
            this.AGGRF5jTQInc5pUFaFXws4RnGLG0yIcZMIkVuaCJtvs6ytQWDb3uUe5MrjxIRxbt = z2;
        }
        if (this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            int i2 = Log.i("GoogleMap", "enable long click listener?:".concat(String.valueOf(z2)));
            this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setOnMapLongClickListener(z2 ? this : null);
        }
    }

    @SimpleProperty(description = "Indicates if the map longClick listener is currently enabled")
    public boolean MapLongClickListenerEnabled() {
        return this.AGGRF5jTQInc5pUFaFXws4RnGLG0yIcZMIkVuaCJtvs6ytQWDb3uUe5MrjxIRxbt;
    }

    @SimpleFunction(description = "Enable/Disable to listen to map's camera position changed event")
    public void EnableMapCameraPosChangeListener(boolean z) {
        boolean z2 = z;
        int i = Log.i("GoogleMap", "@EnableMapCameraPosChangeListener:".concat(String.valueOf(z2)));
        if (this.fSI6lxX8qEfUYa0M3qSNEhqEY7tqyd89UewYfJ8WSYLJpTsAFdRvTVg7ORBsMzG8 != z2) {
            this.fSI6lxX8qEfUYa0M3qSNEhqEY7tqyd89UewYfJ8WSYLJpTsAFdRvTVg7ORBsMzG8 = z2;
        }
        if (this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            int i2 = Log.i("GoogleMap", "enable cameraChangedListener?:".concat(String.valueOf(z2)));
            this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setOnCameraChangeListener(z2 ? this : null);
        }
    }

    @SimpleProperty(description = "Indicates if the map camera's position changed listener is currently enabled")
    public boolean MapCameraChangedListenerEnabled() {
        return this.fSI6lxX8qEfUYa0M3qSNEhqEY7tqyd89UewYfJ8WSYLJpTsAFdRvTVg7ORBsMzG8;
    }

    @SimpleProperty(description = "Indicates the current map type")
    public String MapType() {
        switch (this.vB3WjEtL56PUGm0spJ96S19MI1O4vPR6yju8tUYcKrC4atk0AV5GbVcHQNB7BlIK) {
            case 1:
                return "normal";
            case 2:
                return "satellite";
            case 3:
                return "terrain";
            case 4:
                return "hybrid";
            default:
                return null;
        }
    }

    @SimpleFunction(description = "Adding a list of YailLists for markers. The representation of a maker in the inner YailList is composed of: lat(double) [required], long(double) [required], Color, title(String), snippet(String), draggable(boolean). Return a list of unqiue ids for the added  markers. Note that the markers ids are not meant to persist after  the app is closed, but for temporary references to the markers within the program only. Return an empty list if any error happen in the input")
    public YailList AddMarkers(YailList yailList) {
        ArrayList arrayList;
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        StringBuilder sb4;
        StringBuilder sb5;
        StringBuilder sb6;
        StringBuilder sb7;
        StringBuilder sb8;
        StringBuilder sb9;
        StringBuilder sb10;
        float[] fArr = new float[3];
        new ArrayList();
        ArrayList arrayList2 = arrayList;
        Object[] array = yailList.toArray();
        Object[] objArr = array;
        int length = array.length;
        for (int i = 0; i < length; i++) {
            Object obj = objArr[i];
            boolean z = true;
            if (obj instanceof YailList) {
                int i2 = Log.i("GoogleMap", "interior YailLiat");
                if (((YailList) obj).size() < 2) {
                    z = false;
                }
                Object object = ((YailList) obj).getObject(0);
                Object object2 = ((YailList) obj).getObject(1);
                new StringBuilder("Type: ");
                int i3 = Log.i("GoogleMap", sb.append(object.getClass()).toString());
                new StringBuilder("Type: ");
                int i4 = Log.i("GoogleMap", sb2.append(object2.getClass()).toString());
                Double valueOf = Double.valueOf(0.0d);
                Double valueOf2 = Double.valueOf(0.0d);
                if (!(object instanceof DFloNum) || !(object2 instanceof DFloNum)) {
                    z = false;
                } else {
                    valueOf = Double.valueOf(((DFloNum) object).doubleValue());
                    valueOf2 = Double.valueOf(((DFloNum) object2).doubleValue());
                }
                if (valueOf.doubleValue() < -90.0d || valueOf.doubleValue() > 90.0d || valueOf2.doubleValue() < -180.0d || valueOf2.doubleValue() > 180.0d) {
                    z = false;
                }
                int i5 = this.PJTNEFub7t830GnsJOreZR2G4QGerhYk5JzZTaNM1rn2OjCmiPf1GPWMoDtnF4UY;
                String str = "";
                String str2 = "";
                boolean z2 = false;
                if (((YailList) obj).size() >= 3) {
                    Object object3 = ((YailList) obj).getObject(2);
                    new StringBuilder("Type: ");
                    int i6 = Log.i("GoogleMap", sb9.append(object3.getClass()).toString());
                    new StringBuilder("Value: ");
                    int i7 = Log.i("GoogleMap", sb10.append(object3.toString()).toString());
                    if (object3 instanceof IntNum) {
                        i5 = ((IntNum) object3).intValue();
                    } else {
                        z = false;
                    }
                }
                if (((YailList) obj).size() >= 4) {
                    Object object4 = ((YailList) obj).getObject(3);
                    new StringBuilder("Type: ");
                    int i8 = Log.i("GoogleMap", sb7.append(object4.getClass()).toString());
                    new StringBuilder("Value: ");
                    int i9 = Log.i("GoogleMap", sb8.append(object4.toString()).toString());
                    str = object4.toString();
                }
                if (((YailList) obj).size() >= 5) {
                    Object object5 = ((YailList) obj).getObject(4);
                    new StringBuilder("Type: ");
                    int i10 = Log.i("GoogleMap", sb5.append(object5.getClass()).toString());
                    new StringBuilder("Value: ");
                    int i11 = Log.i("GoogleMap", sb6.append(object5.toString()).toString());
                    str2 = object5.toString();
                }
                if (((YailList) obj).size() >= 6) {
                    Object object6 = ((YailList) obj).getObject(5);
                    new StringBuilder("Type: ");
                    int i12 = Log.i("GoogleMap", sb3.append(object6.getClass()).toString());
                    new StringBuilder("Value: ");
                    int i13 = Log.i("GoogleMap", sb4.append(object6.toString()).toString());
                    if (object6 instanceof Boolean) {
                        z2 = ((Boolean) object6).booleanValue();
                    } else {
                        z = false;
                    }
                }
                Color.colorToHSV(i5, fArr);
                if (z) {
                    int incrementAndGet = vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq.incrementAndGet();
                    boolean add = arrayList2.add(Integer.valueOf(incrementAndGet));
                    int hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(valueOf, valueOf2, incrementAndGet, fArr[0], str, str2, z2);
                }
            } else {
                this.form.dispatchErrorOccurredEvent(this, "AddMarkers", ErrorMessages.ERROR_GOOGLE_MAP_INVALID_INPUT, "marker is not represented as list");
            }
        }
        return YailList.makeList((List) arrayList2);
    }

    private int hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Double d, Double d2, int i, float f, String str, String str2, boolean z) {
        LatLng latLng;
        MarkerOptions markerOptions;
        int i2 = i;
        String str3 = str;
        String str4 = str2;
        boolean z2 = z;
        int i3 = Log.i("GoogleMap", "@addMarkerToMap");
        new LatLng(d.doubleValue(), d2.doubleValue());
        com.google.android.gms.maps.GoogleMap googleMap = this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        new MarkerOptions();
        Marker addMarker = googleMap.addMarker(markerOptions.position(latLng).icon(BitmapDescriptorFactory.defaultMarker(f)));
        if (!str3.isEmpty()) {
            addMarker.setTitle(str3);
        }
        if (!str4.isEmpty()) {
            addMarker.setSnippet(str4);
        }
        addMarker.setDraggable(z2);
        Integer put = this.f416B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.put(addMarker, Integer.valueOf(i2));
        return i2;
    }

    @SimpleFunction(description = "Add a list of markers composed of name-value pairs. Name fields for a marker are: \"lat\" (type double) [required], \"lng\"(type double) [required], \"color\"(type int)[in hue value ranging from 0~360], \"title\"(type String), \"snippet\"(type String), \"draggable\"(type boolean)")
    public YailList GetMarkers() {
        return this.f431wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x01f9 A[Catch:{ JsonSyntaxException -> 0x035d }] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x022a A[Catch:{ JsonSyntaxException -> 0x035d }] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x023a A[Catch:{ JsonSyntaxException -> 0x035d }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x024a A[Catch:{ JsonSyntaxException -> 0x035d }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0252 A[Catch:{ JsonSyntaxException -> 0x035d }] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x02d7  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x02eb A[Catch:{ JsonSyntaxException -> 0x035d }] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x02fa A[Catch:{ JsonSyntaxException -> 0x035d }] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0309 A[Catch:{ JsonSyntaxException -> 0x035d }] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0071 A[SYNTHETIC] */
    @com.google.appinventor.components.annotations.SimpleFunction(description = "Adding a list of markers that are represented as JsonArray.  The inner JsonObject represents a markerand is composed of name-value pairs. Name fields for a marker are: \"lat\" (type double) [required], \"lng\"(type double) [required], \"color\"(type int)[in hue value ranging from 0~360], \"title\"(type String), \"snippet\"(type String), \"draggable\"(type boolean)")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void AddMarkersFromJson(java.lang.String r26) {
        /*
            r25 = this;
            r2 = r25
            r3 = r26
            java.util.ArrayList r16 = new java.util.ArrayList
            r24 = r16
            r16 = r24
            r17 = r24
            r17.<init>()
            r4 = r16
            com.google.gson.JsonParser r16 = new com.google.gson.JsonParser
            r24 = r16
            r16 = r24
            r17 = r24
            r17.<init>()
            r5 = r16
            r16 = 3
            r0 = r16
            float[] r0 = new float[r0]
            r16 = r0
            r6 = r16
            r16 = r5
            r17 = r3
            com.google.gson.JsonElement r16 = r16.parse((java.lang.String) r17)     // Catch:{ JsonSyntaxException -> 0x035d }
            r24 = r16
            r16 = r24
            r17 = r24
            r5 = r17
            boolean r16 = r16.isJsonArray()     // Catch:{ JsonSyntaxException -> 0x035d }
            if (r16 == 0) goto L_0x0327
            r16 = r5
            com.google.gson.JsonArray r16 = r16.getAsJsonArray()     // Catch:{ JsonSyntaxException -> 0x035d }
            r5 = r16
            java.lang.String r16 = "GoogleMap"
            java.lang.StringBuilder r17 = new java.lang.StringBuilder     // Catch:{ JsonSyntaxException -> 0x035d }
            r24 = r17
            r17 = r24
            r18 = r24
            java.lang.String r19 = "It's a JsonArry: "
            r18.<init>(r19)     // Catch:{ JsonSyntaxException -> 0x035d }
            r18 = r5
            java.lang.String r18 = r18.toString()     // Catch:{ JsonSyntaxException -> 0x035d }
            java.lang.StringBuilder r17 = r17.append(r18)     // Catch:{ JsonSyntaxException -> 0x035d }
            java.lang.String r17 = r17.toString()     // Catch:{ JsonSyntaxException -> 0x035d }
            int r16 = android.util.Log.i(r16, r17)     // Catch:{ JsonSyntaxException -> 0x035d }
            r16 = r5
            java.util.Iterator r16 = r16.iterator()     // Catch:{ JsonSyntaxException -> 0x035d }
            r5 = r16
        L_0x0071:
            r16 = r5
            boolean r16 = r16.hasNext()     // Catch:{ JsonSyntaxException -> 0x035d }
            if (r16 == 0) goto L_0x0318
            r16 = r5
            java.lang.Object r16 = r16.next()     // Catch:{ JsonSyntaxException -> 0x035d }
            com.google.gson.JsonElement r16 = (com.google.gson.JsonElement) r16     // Catch:{ JsonSyntaxException -> 0x035d }
            r7 = r16
            r16 = 1
            r8 = r16
            r16 = r7
            boolean r16 = r16.isJsonObject()     // Catch:{ JsonSyntaxException -> 0x035d }
            if (r16 == 0) goto L_0x02ae
            r16 = r7
            com.google.gson.JsonObject r16 = r16.getAsJsonObject()     // Catch:{ JsonSyntaxException -> 0x035d }
            r24 = r16
            r16 = r24
            r17 = r24
            r7 = r17
            java.lang.String r17 = "lat"
            com.google.gson.JsonElement r16 = r16.get(r17)     // Catch:{ JsonSyntaxException -> 0x035d }
            if (r16 == 0) goto L_0x00b1
            r16 = r7
            java.lang.String r17 = "lng"
            com.google.gson.JsonElement r16 = r16.get(r17)     // Catch:{ JsonSyntaxException -> 0x035d }
            if (r16 != 0) goto L_0x00b2
        L_0x00b1:
            goto L_0x0071
        L_0x00b2:
            r16 = r7
            java.lang.String r17 = "lat"
            com.google.gson.JsonElement r16 = r16.get(r17)     // Catch:{ JsonSyntaxException -> 0x035d }
            com.google.gson.JsonPrimitive r16 = (com.google.gson.JsonPrimitive) r16     // Catch:{ JsonSyntaxException -> 0x035d }
            r9 = r16
            r16 = r7
            java.lang.String r17 = "lng"
            com.google.gson.JsonElement r16 = r16.get(r17)     // Catch:{ JsonSyntaxException -> 0x035d }
            com.google.gson.JsonPrimitive r16 = (com.google.gson.JsonPrimitive) r16     // Catch:{ JsonSyntaxException -> 0x035d }
            r10 = r16
            r16 = 0
            r12 = r16
            r16 = 0
            r14 = r16
            r16 = r9
            boolean r16 = r16.isString()     // Catch:{ NumberFormatException -> 0x02d0 }
            if (r16 == 0) goto L_0x02b0
            r16 = r10
            boolean r16 = r16.isString()     // Catch:{ NumberFormatException -> 0x02d0 }
            if (r16 == 0) goto L_0x02b0
            java.lang.String r16 = "GoogleMap"
            java.lang.StringBuilder r17 = new java.lang.StringBuilder     // Catch:{ NumberFormatException -> 0x02d0 }
            r24 = r17
            r17 = r24
            r18 = r24
            java.lang.String r19 = "jpLat:"
            r18.<init>(r19)     // Catch:{ NumberFormatException -> 0x02d0 }
            r18 = r9
            java.lang.String r18 = r18.toString()     // Catch:{ NumberFormatException -> 0x02d0 }
            java.lang.StringBuilder r17 = r17.append(r18)     // Catch:{ NumberFormatException -> 0x02d0 }
            java.lang.String r17 = r17.toString()     // Catch:{ NumberFormatException -> 0x02d0 }
            int r16 = android.util.Log.i(r16, r17)     // Catch:{ NumberFormatException -> 0x02d0 }
            java.lang.String r16 = "GoogleMap"
            java.lang.StringBuilder r17 = new java.lang.StringBuilder     // Catch:{ NumberFormatException -> 0x02d0 }
            r24 = r17
            r17 = r24
            r18 = r24
            java.lang.String r19 = "jpLng:"
            r18.<init>(r19)     // Catch:{ NumberFormatException -> 0x02d0 }
            r18 = r10
            java.lang.String r18 = r18.toString()     // Catch:{ NumberFormatException -> 0x02d0 }
            java.lang.StringBuilder r17 = r17.append(r18)     // Catch:{ NumberFormatException -> 0x02d0 }
            java.lang.String r17 = r17.toString()     // Catch:{ NumberFormatException -> 0x02d0 }
            int r16 = android.util.Log.i(r16, r17)     // Catch:{ NumberFormatException -> 0x02d0 }
            r16 = r9
            java.lang.String r16 = r16.getAsString()     // Catch:{ NumberFormatException -> 0x02d0 }
            java.lang.Double r16 = java.lang.Double.valueOf(r16)     // Catch:{ NumberFormatException -> 0x02d0 }
            double r16 = r16.doubleValue()     // Catch:{ NumberFormatException -> 0x02d0 }
            r12 = r16
            java.lang.Double r16 = new java.lang.Double     // Catch:{ NumberFormatException -> 0x02d0 }
            r24 = r16
            r16 = r24
            r17 = r24
            r18 = r10
            java.lang.String r18 = r18.getAsString()     // Catch:{ NumberFormatException -> 0x02d0 }
            r17.<init>(r18)     // Catch:{ NumberFormatException -> 0x02d0 }
            double r16 = r16.doubleValue()     // Catch:{ NumberFormatException -> 0x02d0 }
            r14 = r16
            java.lang.String r16 = "GoogleMap"
            java.lang.StringBuilder r17 = new java.lang.StringBuilder     // Catch:{ NumberFormatException -> 0x02d0 }
            r24 = r17
            r17 = r24
            r18 = r24
            java.lang.String r19 = "convert to double:"
            r18.<init>(r19)     // Catch:{ NumberFormatException -> 0x02d0 }
            r18 = r12
            java.lang.StringBuilder r17 = r17.append(r18)     // Catch:{ NumberFormatException -> 0x02d0 }
            java.lang.String r18 = ","
            java.lang.StringBuilder r17 = r17.append(r18)     // Catch:{ NumberFormatException -> 0x02d0 }
            r18 = r14
            java.lang.StringBuilder r17 = r17.append(r18)     // Catch:{ NumberFormatException -> 0x02d0 }
            java.lang.String r17 = r17.toString()     // Catch:{ NumberFormatException -> 0x02d0 }
            int r16 = android.util.Log.i(r16, r17)     // Catch:{ NumberFormatException -> 0x02d0 }
        L_0x017d:
            r16 = r12
            r18 = -4587338432941916160(0xc056800000000000, double:-90.0)
            int r16 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1))
            if (r16 < 0) goto L_0x01a9
            r16 = r12
            r18 = 4636033603912859648(0x4056800000000000, double:90.0)
            int r16 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1))
            if (r16 > 0) goto L_0x01a9
            r16 = r14
            r18 = -4582834833314545664(0xc066800000000000, double:-180.0)
            int r16 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1))
            if (r16 < 0) goto L_0x01a9
            r16 = r14
            r18 = 4640537203540230144(0x4066800000000000, double:180.0)
            int r16 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1))
            if (r16 <= 0) goto L_0x01d9
        L_0x01a9:
            java.lang.String r16 = "GoogleMap"
            java.lang.StringBuilder r17 = new java.lang.StringBuilder     // Catch:{ JsonSyntaxException -> 0x035d }
            r24 = r17
            r17 = r24
            r18 = r24
            java.lang.String r19 = "Lat/Lng wrong range:"
            r18.<init>(r19)     // Catch:{ JsonSyntaxException -> 0x035d }
            r18 = r12
            java.lang.StringBuilder r17 = r17.append(r18)     // Catch:{ JsonSyntaxException -> 0x035d }
            java.lang.String r18 = ","
            java.lang.StringBuilder r17 = r17.append(r18)     // Catch:{ JsonSyntaxException -> 0x035d }
            r18 = r14
            java.lang.StringBuilder r17 = r17.append(r18)     // Catch:{ JsonSyntaxException -> 0x035d }
            java.lang.String r17 = r17.toString()     // Catch:{ JsonSyntaxException -> 0x035d }
            int r16 = android.util.Log.i(r16, r17)     // Catch:{ JsonSyntaxException -> 0x035d }
            r16 = 0
            r8 = r16
        L_0x01d9:
            r16 = r2
            r0 = r16
            int r0 = r0.PJTNEFub7t830GnsJOreZR2G4QGerhYk5JzZTaNM1rn2OjCmiPf1GPWMoDtnF4UY     // Catch:{ JsonSyntaxException -> 0x035d }
            r16 = r0
            r17 = r6
            android.graphics.Color.colorToHSV(r16, r17)     // Catch:{ JsonSyntaxException -> 0x035d }
            r16 = r6
            r17 = 0
            r16 = r16[r17]     // Catch:{ JsonSyntaxException -> 0x035d }
            r9 = r16
            r16 = r7
            java.lang.String r17 = "color"
            com.google.gson.JsonElement r16 = r16.get(r17)     // Catch:{ JsonSyntaxException -> 0x035d }
            if (r16 != 0) goto L_0x02d7
            r16 = r9
        L_0x01fb:
            r24 = r16
            r16 = r24
            r17 = r24
            r9 = r17
            r17 = 0
            int r16 = (r16 > r17 ? 1 : (r16 == r17 ? 0 : -1))
            if (r16 < 0) goto L_0x0211
            r16 = r9
            r17 = 1135869952(0x43b40000, float:360.0)
            int r16 = (r16 > r17 ? 1 : (r16 == r17 ? 0 : -1))
            if (r16 <= 0) goto L_0x021f
        L_0x0211:
            java.lang.String r16 = "GoogleMap"
            java.lang.String r17 = "Wrong color"
            int r16 = android.util.Log.i(r16, r17)     // Catch:{ JsonSyntaxException -> 0x035d }
            r16 = 0
            r8 = r16
        L_0x021f:
            r16 = r7
            java.lang.String r17 = "title"
            com.google.gson.JsonElement r16 = r16.get(r17)     // Catch:{ JsonSyntaxException -> 0x035d }
            if (r16 != 0) goto L_0x02eb
            java.lang.String r16 = ""
        L_0x022d:
            r10 = r16
            r16 = r7
            java.lang.String r17 = "snippet"
            com.google.gson.JsonElement r16 = r16.get(r17)     // Catch:{ JsonSyntaxException -> 0x035d }
            if (r16 != 0) goto L_0x02fa
            java.lang.String r16 = ""
        L_0x023d:
            r11 = r16
            r16 = r7
            java.lang.String r17 = "draggable"
            com.google.gson.JsonElement r16 = r16.get(r17)     // Catch:{ JsonSyntaxException -> 0x035d }
            if (r16 != 0) goto L_0x0309
            r16 = 0
        L_0x024c:
            r7 = r16
            r16 = r8
            if (r16 == 0) goto L_0x02ae
            java.lang.String r16 = "GoogleMap"
            java.lang.StringBuilder r17 = new java.lang.StringBuilder     // Catch:{ JsonSyntaxException -> 0x035d }
            r24 = r17
            r17 = r24
            r18 = r24
            java.lang.String r19 = "Adding marker"
            r18.<init>(r19)     // Catch:{ JsonSyntaxException -> 0x035d }
            r18 = r12
            java.lang.StringBuilder r17 = r17.append(r18)     // Catch:{ JsonSyntaxException -> 0x035d }
            java.lang.String r18 = ","
            java.lang.StringBuilder r17 = r17.append(r18)     // Catch:{ JsonSyntaxException -> 0x035d }
            r18 = r14
            java.lang.StringBuilder r17 = r17.append(r18)     // Catch:{ JsonSyntaxException -> 0x035d }
            java.lang.String r17 = r17.toString()     // Catch:{ JsonSyntaxException -> 0x035d }
            int r16 = android.util.Log.i(r16, r17)     // Catch:{ JsonSyntaxException -> 0x035d }
            java.util.concurrent.atomic.AtomicInteger r16 = vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq     // Catch:{ JsonSyntaxException -> 0x035d }
            int r16 = r16.incrementAndGet()     // Catch:{ JsonSyntaxException -> 0x035d }
            r8 = r16
            r16 = r4
            r17 = r8
            java.lang.Integer r17 = java.lang.Integer.valueOf(r17)     // Catch:{ JsonSyntaxException -> 0x035d }
            boolean r16 = r16.add(r17)     // Catch:{ JsonSyntaxException -> 0x035d }
            r16 = r2
            r17 = r12
            java.lang.Double r17 = java.lang.Double.valueOf(r17)     // Catch:{ JsonSyntaxException -> 0x035d }
            r18 = r14
            java.lang.Double r18 = java.lang.Double.valueOf(r18)     // Catch:{ JsonSyntaxException -> 0x035d }
            r19 = r8
            r20 = r9
            r21 = r10
            r22 = r11
            r23 = r7
            int r16 = r16.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(r17, r18, r19, r20, r21, r22, r23)     // Catch:{ JsonSyntaxException -> 0x035d }
        L_0x02ae:
            goto L_0x0071
        L_0x02b0:
            r16 = r7
            java.lang.String r17 = "lat"
            com.google.gson.JsonElement r16 = r16.get(r17)     // Catch:{ NumberFormatException -> 0x02d0 }
            double r16 = r16.getAsDouble()     // Catch:{ NumberFormatException -> 0x02d0 }
            r12 = r16
            r16 = r7
            java.lang.String r17 = "lng"
            com.google.gson.JsonElement r16 = r16.get(r17)     // Catch:{ NumberFormatException -> 0x02d0 }
            double r16 = r16.getAsDouble()     // Catch:{ NumberFormatException -> 0x02d0 }
            r14 = r16
            goto L_0x017d
        L_0x02d0:
            r16 = move-exception
            r16 = 0
            r8 = r16
            goto L_0x017d
        L_0x02d7:
            r16 = r7
            java.lang.String r17 = "color"
            com.google.gson.JsonElement r16 = r16.get(r17)     // Catch:{ JsonSyntaxException -> 0x035d }
            int r16 = r16.getAsInt()     // Catch:{ JsonSyntaxException -> 0x035d }
            r0 = r16
            float r0 = (float) r0     // Catch:{ JsonSyntaxException -> 0x035d }
            r16 = r0
            goto L_0x01fb
        L_0x02eb:
            r16 = r7
            java.lang.String r17 = "title"
            com.google.gson.JsonElement r16 = r16.get(r17)     // Catch:{ JsonSyntaxException -> 0x035d }
            java.lang.String r16 = r16.getAsString()     // Catch:{ JsonSyntaxException -> 0x035d }
            goto L_0x022d
        L_0x02fa:
            r16 = r7
            java.lang.String r17 = "snippet"
            com.google.gson.JsonElement r16 = r16.get(r17)     // Catch:{ JsonSyntaxException -> 0x035d }
            java.lang.String r16 = r16.getAsString()     // Catch:{ JsonSyntaxException -> 0x035d }
            goto L_0x023d
        L_0x0309:
            r16 = r7
            java.lang.String r17 = "draggable"
            com.google.gson.JsonElement r16 = r16.get(r17)     // Catch:{ JsonSyntaxException -> 0x035d }
            boolean r16 = r16.getAsBoolean()     // Catch:{ JsonSyntaxException -> 0x035d }
            goto L_0x024c
        L_0x0318:
            r16 = r2
            r17 = r4
            com.google.appinventor.components.runtime.util.YailList r17 = com.google.appinventor.components.runtime.util.YailList.makeList((java.util.List) r17)
            r0 = r17
            r1 = r16
            r1.f431wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = r0
            return
        L_0x0327:
            r16 = r2
            r0 = r16
            com.google.appinventor.components.runtime.Form r0 = r0.form     // Catch:{ JsonSyntaxException -> 0x035d }
            r16 = r0
            r17 = r2
            java.lang.String r18 = "AddMarkersFromJson"
            r19 = 12012(0x2eec, float:1.6832E-41)
            r20 = 1
            r0 = r20
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ JsonSyntaxException -> 0x035d }
            r20 = r0
            r24 = r20
            r20 = r24
            r21 = r24
            r22 = 0
            java.lang.String r23 = "markers needs to be represented as JsonArray"
            r21[r22] = r23     // Catch:{ JsonSyntaxException -> 0x035d }
            r16.dispatchErrorOccurredEvent(r17, r18, r19, r20)     // Catch:{ JsonSyntaxException -> 0x035d }
            r16 = r2
            r17 = r4
            com.google.appinventor.components.runtime.util.YailList r17 = com.google.appinventor.components.runtime.util.YailList.makeList((java.util.List) r17)     // Catch:{ JsonSyntaxException -> 0x035d }
            r0 = r17
            r1 = r16
            r1.f431wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = r0     // Catch:{ JsonSyntaxException -> 0x035d }
            goto L_0x0318
        L_0x035d:
            r16 = move-exception
            r16 = r2
            r0 = r16
            com.google.appinventor.components.runtime.Form r0 = r0.form
            r16 = r0
            r17 = r2
            java.lang.String r18 = "AddMarkersFromJson"
            r19 = 12014(0x2eee, float:1.6835E-41)
            r20 = 1
            r0 = r20
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r20 = r0
            r24 = r20
            r20 = r24
            r21 = r24
            r22 = 0
            r23 = r3
            r21[r22] = r23
            r16.dispatchErrorOccurredEvent(r17, r18, r19, r20)
            r16 = r2
            r17 = r4
            com.google.appinventor.components.runtime.util.YailList r17 = com.google.appinventor.components.runtime.util.YailList.makeList((java.util.List) r17)
            r0 = r17
            r1 = r16
            r1.f431wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = r0
            goto L_0x0318
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.GoogleMap.AddMarkersFromJson(java.lang.String):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v84, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r27v8, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v93, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r27v11, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    @com.google.appinventor.components.annotations.SimpleFunction(description = "Adding a list of YailList for markers. The inner YailList represents a marker and is composed of lat(Double) [required], long(Double) [required], color(int)[in hue value ranging from 0-360], title(String), snippet(String), draggable(boolean). Return a list of unique ids for the markers that are added")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.appinventor.components.runtime.util.YailList AddMarkersHue(com.google.appinventor.components.runtime.util.YailList r29) {
        /*
            r28 = this;
            r3 = r28
            r4 = r29
            java.util.ArrayList r18 = new java.util.ArrayList
            r27 = r18
            r18 = r27
            r19 = r27
            r19.<init>()
            r5 = r18
            r18 = r4
            java.lang.Object[] r18 = r18.toArray()
            r27 = r18
            r18 = r27
            r19 = r27
            r4 = r19
            r0 = r18
            int r0 = r0.length
            r18 = r0
            r6 = r18
            r18 = 0
            r7 = r18
        L_0x002a:
            r18 = r7
            r19 = r6
            r0 = r18
            r1 = r19
            if (r0 >= r1) goto L_0x0477
            r18 = r4
            r19 = r7
            r18 = r18[r19]
            r8 = r18
            r18 = 1
            r9 = r18
            r18 = r8
            r0 = r18
            boolean r0 = r0 instanceof com.google.appinventor.components.runtime.util.YailList
            r18 = r0
            if (r18 == 0) goto L_0x0447
            java.lang.String r18 = "GoogleMap"
            java.lang.String r19 = "Interior YailLiat"
            int r18 = android.util.Log.i(r18, r19)
            r18 = r8
            com.google.appinventor.components.runtime.util.YailList r18 = (com.google.appinventor.components.runtime.util.YailList) r18
            int r18 = r18.size()
            r19 = 2
            r0 = r18
            r1 = r19
            if (r0 >= r1) goto L_0x008f
            r18 = r3
            r0 = r18
            com.google.appinventor.components.runtime.Form r0 = r0.form
            r18 = r0
            r19 = r3
            java.lang.String r20 = "AddMarkers"
            r21 = 12012(0x2eec, float:1.6832E-41)
            r22 = 1
            r0 = r22
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r22 = r0
            r27 = r22
            r22 = r27
            r23 = r27
            r24 = 0
            java.lang.String r25 = "Need more than 2 inputs"
            r23[r24] = r25
            r18.dispatchErrorOccurredEvent(r19, r20, r21, r22)
            r18 = 0
            r9 = r18
        L_0x008f:
            r18 = r8
            com.google.appinventor.components.runtime.util.YailList r18 = (com.google.appinventor.components.runtime.util.YailList) r18
            r19 = 0
            java.lang.Object r18 = r18.getObject(r19)
            r10 = r18
            r18 = r8
            com.google.appinventor.components.runtime.util.YailList r18 = (com.google.appinventor.components.runtime.util.YailList) r18
            r19 = 1
            java.lang.Object r18 = r18.getObject(r19)
            r11 = r18
            java.lang.String r18 = "GoogleMap"
            java.lang.StringBuilder r19 = new java.lang.StringBuilder
            r27 = r19
            r19 = r27
            r20 = r27
            java.lang.String r21 = "Type: "
            r20.<init>(r21)
            r20 = r10
            java.lang.Class r20 = r20.getClass()
            java.lang.StringBuilder r19 = r19.append(r20)
            java.lang.String r19 = r19.toString()
            int r18 = android.util.Log.i(r18, r19)
            java.lang.String r18 = "GoogleMap"
            java.lang.StringBuilder r19 = new java.lang.StringBuilder
            r27 = r19
            r19 = r27
            r20 = r27
            java.lang.String r21 = "Type: "
            r20.<init>(r21)
            r20 = r11
            java.lang.Class r20 = r20.getClass()
            java.lang.StringBuilder r19 = r19.append(r20)
            java.lang.String r19 = r19.toString()
            int r18 = android.util.Log.i(r18, r19)
            r18 = 0
            java.lang.Double r18 = java.lang.Double.valueOf(r18)
            r12 = r18
            r18 = 0
            java.lang.Double r18 = java.lang.Double.valueOf(r18)
            r13 = r18
            r18 = r10
            r0 = r18
            boolean r0 = r0 instanceof gnu.math.DFloNum
            r18 = r0
            if (r18 == 0) goto L_0x0111
            r18 = r11
            r0 = r18
            boolean r0 = r0 instanceof gnu.math.DFloNum
            r18 = r0
            if (r18 != 0) goto L_0x03b2
        L_0x0111:
            r18 = r3
            r0 = r18
            com.google.appinventor.components.runtime.Form r0 = r0.form
            r18 = r0
            r19 = r3
            java.lang.String r20 = "AddMarkersHue"
            r21 = 12012(0x2eec, float:1.6832E-41)
            r22 = 1
            r0 = r22
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r22 = r0
            r27 = r22
            r22 = r27
            r23 = r27
            r24 = 0
            java.lang.String r25 = "Not a number for latitude or longitude"
            r23[r24] = r25
            r18.dispatchErrorOccurredEvent(r19, r20, r21, r22)
            r18 = 0
            r9 = r18
        L_0x013c:
            r18 = r12
            double r18 = r18.doubleValue()
            r20 = -4587338432941916160(0xc056800000000000, double:-90.0)
            int r18 = (r18 > r20 ? 1 : (r18 == r20 ? 0 : -1))
            if (r18 < 0) goto L_0x0178
            r18 = r12
            double r18 = r18.doubleValue()
            r20 = 4636033603912859648(0x4056800000000000, double:90.0)
            int r18 = (r18 > r20 ? 1 : (r18 == r20 ? 0 : -1))
            if (r18 > 0) goto L_0x0178
            r18 = r13
            double r18 = r18.doubleValue()
            r20 = -4582834833314545664(0xc066800000000000, double:-180.0)
            int r18 = (r18 > r20 ? 1 : (r18 == r20 ? 0 : -1))
            if (r18 < 0) goto L_0x0178
            r18 = r13
            double r18 = r18.doubleValue()
            r20 = 4640537203540230144(0x4066800000000000, double:180.0)
            int r18 = (r18 > r20 ? 1 : (r18 == r20 ? 0 : -1))
            if (r18 <= 0) goto L_0x01a3
        L_0x0178:
            r18 = 0
            r9 = r18
            r18 = r3
            r0 = r18
            com.google.appinventor.components.runtime.Form r0 = r0.form
            r18 = r0
            r19 = r3
            java.lang.String r20 = "AddMarkers"
            r21 = 12012(0x2eec, float:1.6832E-41)
            r22 = 1
            r0 = r22
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r22 = r0
            r27 = r22
            r22 = r27
            r23 = r27
            r24 = 0
            java.lang.String r25 = "Range for the latitude or longitude is wrong"
            r23[r24] = r25
            r18.dispatchErrorOccurredEvent(r19, r20, r21, r22)
        L_0x01a3:
            java.util.concurrent.atomic.AtomicInteger r18 = vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq
            int r18 = r18.incrementAndGet()
            java.lang.Integer r18 = java.lang.Integer.valueOf(r18)
            r10 = r18
            r18 = 1131413504(0x43700000, float:240.0)
            r11 = r18
            java.lang.String r18 = ""
            r14 = r18
            java.lang.String r18 = ""
            r15 = r18
            r18 = 0
            r16 = r18
            r18 = r8
            com.google.appinventor.components.runtime.util.YailList r18 = (com.google.appinventor.components.runtime.util.YailList) r18
            int r18 = r18.size()
            r19 = 3
            r0 = r18
            r1 = r19
            if (r0 < r1) goto L_0x023c
            r18 = r8
            com.google.appinventor.components.runtime.util.YailList r18 = (com.google.appinventor.components.runtime.util.YailList) r18
            r19 = 2
            java.lang.Object r18 = r18.getObject(r19)
            r17 = r18
            java.lang.String r18 = "GoogleMap"
            java.lang.StringBuilder r19 = new java.lang.StringBuilder
            r27 = r19
            r19 = r27
            r20 = r27
            java.lang.String r21 = "Type: "
            r20.<init>(r21)
            r20 = r17
            java.lang.Class r20 = r20.getClass()
            java.lang.StringBuilder r19 = r19.append(r20)
            java.lang.String r19 = r19.toString()
            int r18 = android.util.Log.i(r18, r19)
            java.lang.String r18 = "GoogleMap"
            java.lang.StringBuilder r19 = new java.lang.StringBuilder
            r27 = r19
            r19 = r27
            r20 = r27
            java.lang.String r21 = "Value: "
            r20.<init>(r21)
            r20 = r17
            java.lang.String r20 = r20.toString()
            java.lang.StringBuilder r19 = r19.append(r20)
            java.lang.String r19 = r19.toString()
            int r18 = android.util.Log.i(r18, r19)
            r18 = r17
            r0 = r18
            boolean r0 = r0 instanceof gnu.math.IntNum
            r18 = r0
            if (r18 == 0) goto L_0x03d0
            r18 = r17
            gnu.math.IntNum r18 = (gnu.math.IntNum) r18
            int r18 = r18.intValue()
            r0 = r18
            float r0 = (float) r0
            r18 = r0
            r11 = r18
        L_0x023c:
            r18 = r8
            com.google.appinventor.components.runtime.util.YailList r18 = (com.google.appinventor.components.runtime.util.YailList) r18
            int r18 = r18.size()
            r19 = 4
            r0 = r18
            r1 = r19
            if (r0 < r1) goto L_0x02a8
            r18 = r8
            com.google.appinventor.components.runtime.util.YailList r18 = (com.google.appinventor.components.runtime.util.YailList) r18
            r19 = 3
            java.lang.Object r18 = r18.getObject(r19)
            r27 = r18
            r18 = r27
            r19 = r27
            r17 = r19
            java.lang.String r18 = (java.lang.String) r18
            r14 = r18
            java.lang.String r18 = "GoogleMap"
            java.lang.StringBuilder r19 = new java.lang.StringBuilder
            r27 = r19
            r19 = r27
            r20 = r27
            java.lang.String r21 = "Type: "
            r20.<init>(r21)
            r20 = r17
            java.lang.Class r20 = r20.getClass()
            java.lang.StringBuilder r19 = r19.append(r20)
            java.lang.String r19 = r19.toString()
            int r18 = android.util.Log.i(r18, r19)
            java.lang.String r18 = "GoogleMap"
            java.lang.StringBuilder r19 = new java.lang.StringBuilder
            r27 = r19
            r19 = r27
            r20 = r27
            java.lang.String r21 = "Value: "
            r20.<init>(r21)
            r20 = r17
            java.lang.String r20 = r20.toString()
            java.lang.StringBuilder r19 = r19.append(r20)
            java.lang.String r19 = r19.toString()
            int r18 = android.util.Log.i(r18, r19)
        L_0x02a8:
            r18 = r8
            com.google.appinventor.components.runtime.util.YailList r18 = (com.google.appinventor.components.runtime.util.YailList) r18
            int r18 = r18.size()
            r19 = 5
            r0 = r18
            r1 = r19
            if (r0 < r1) goto L_0x0314
            r18 = r8
            com.google.appinventor.components.runtime.util.YailList r18 = (com.google.appinventor.components.runtime.util.YailList) r18
            r19 = 4
            java.lang.Object r18 = r18.getObject(r19)
            r27 = r18
            r18 = r27
            r19 = r27
            r17 = r19
            java.lang.String r18 = (java.lang.String) r18
            r15 = r18
            java.lang.String r18 = "GoogleMap"
            java.lang.StringBuilder r19 = new java.lang.StringBuilder
            r27 = r19
            r19 = r27
            r20 = r27
            java.lang.String r21 = "Type: "
            r20.<init>(r21)
            r20 = r17
            java.lang.Class r20 = r20.getClass()
            java.lang.StringBuilder r19 = r19.append(r20)
            java.lang.String r19 = r19.toString()
            int r18 = android.util.Log.i(r18, r19)
            java.lang.String r18 = "GoogleMap"
            java.lang.StringBuilder r19 = new java.lang.StringBuilder
            r27 = r19
            r19 = r27
            r20 = r27
            java.lang.String r21 = "Value: "
            r20.<init>(r21)
            r20 = r17
            java.lang.String r20 = r20.toString()
            java.lang.StringBuilder r19 = r19.append(r20)
            java.lang.String r19 = r19.toString()
            int r18 = android.util.Log.i(r18, r19)
        L_0x0314:
            r18 = r8
            com.google.appinventor.components.runtime.util.YailList r18 = (com.google.appinventor.components.runtime.util.YailList) r18
            int r18 = r18.size()
            r19 = 6
            r0 = r18
            r1 = r19
            if (r0 < r1) goto L_0x038a
            r18 = r8
            com.google.appinventor.components.runtime.util.YailList r18 = (com.google.appinventor.components.runtime.util.YailList) r18
            r19 = 5
            java.lang.Object r18 = r18.getObject(r19)
            r17 = r18
            java.lang.String r18 = "GoogleMap"
            java.lang.StringBuilder r19 = new java.lang.StringBuilder
            r27 = r19
            r19 = r27
            r20 = r27
            java.lang.String r21 = "Type: "
            r20.<init>(r21)
            r20 = r17
            java.lang.Class r20 = r20.getClass()
            java.lang.StringBuilder r19 = r19.append(r20)
            java.lang.String r19 = r19.toString()
            int r18 = android.util.Log.i(r18, r19)
            java.lang.String r18 = "GoogleMap"
            java.lang.StringBuilder r19 = new java.lang.StringBuilder
            r27 = r19
            r19 = r27
            r20 = r27
            java.lang.String r21 = "Value: "
            r20.<init>(r21)
            r20 = r17
            java.lang.String r20 = r20.toString()
            java.lang.StringBuilder r19 = r19.append(r20)
            java.lang.String r19 = r19.toString()
            int r18 = android.util.Log.i(r18, r19)
            r18 = r17
            r0 = r18
            boolean r0 = r0 instanceof java.lang.Boolean
            r18 = r0
            if (r18 == 0) goto L_0x041a
            r18 = r17
            java.lang.Boolean r18 = (java.lang.Boolean) r18
            boolean r18 = r18.booleanValue()
            r16 = r18
        L_0x038a:
            r18 = r9
            if (r18 == 0) goto L_0x03ae
            r18 = r5
            r19 = r10
            boolean r18 = r18.add(r19)
            r18 = r3
            r19 = r12
            r20 = r13
            r21 = r10
            int r21 = r21.intValue()
            r22 = r11
            r23 = r14
            r24 = r15
            r25 = r16
            int r18 = r18.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(r19, r20, r21, r22, r23, r24, r25)
        L_0x03ae:
            int r7 = r7 + 1
            goto L_0x002a
        L_0x03b2:
            r18 = r10
            gnu.math.DFloNum r18 = (gnu.math.DFloNum) r18
            double r18 = r18.doubleValue()
            java.lang.Double r18 = java.lang.Double.valueOf(r18)
            r12 = r18
            r18 = r11
            gnu.math.DFloNum r18 = (gnu.math.DFloNum) r18
            double r18 = r18.doubleValue()
            java.lang.Double r18 = java.lang.Double.valueOf(r18)
            r13 = r18
            goto L_0x013c
        L_0x03d0:
            r18 = 0
            r9 = r18
            r18 = r3
            r0 = r18
            com.google.appinventor.components.runtime.Form r0 = r0.form
            r18 = r0
            r19 = r3
            java.lang.String r20 = "AddMarkersHue"
            r21 = 12012(0x2eec, float:1.6832E-41)
            r22 = 1
            r0 = r22
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r22 = r0
            r27 = r22
            r22 = r27
            r23 = r27
            r24 = 0
            java.lang.StringBuilder r25 = new java.lang.StringBuilder
            r27 = r25
            r25 = r27
            r26 = r27
            r26.<init>()
            r26 = r17
            java.lang.String r26 = r26.toString()
            java.lang.StringBuilder r25 = r25.append(r26)
            java.lang.String r26 = " is not a number"
            java.lang.StringBuilder r25 = r25.append(r26)
            java.lang.String r25 = r25.toString()
            r23[r24] = r25
            r18.dispatchErrorOccurredEvent(r19, r20, r21, r22)
            goto L_0x023c
        L_0x041a:
            r18 = r3
            r0 = r18
            com.google.appinventor.components.runtime.Form r0 = r0.form
            r18 = r0
            r19 = r3
            java.lang.String r20 = "AddMarkers"
            r21 = 12012(0x2eec, float:1.6832E-41)
            r22 = 1
            r0 = r22
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r22 = r0
            r27 = r22
            r22 = r27
            r23 = r27
            r24 = 0
            java.lang.String r25 = "marker not as a list"
            r23[r24] = r25
            r18.dispatchErrorOccurredEvent(r19, r20, r21, r22)
            r18 = 0
            r9 = r18
            goto L_0x038a
        L_0x0447:
            r18 = r3
            r0 = r18
            com.google.appinventor.components.runtime.Form r0 = r0.form
            r18 = r0
            r19 = r3
            java.lang.String r20 = "AddMarkersHue"
            r21 = 12012(0x2eec, float:1.6832E-41)
            r22 = 1
            r0 = r22
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r22 = r0
            r27 = r22
            r22 = r27
            r23 = r27
            r24 = 0
            java.lang.String r25 = "Marker is not represented as list"
            r23[r24] = r25
            r18.dispatchErrorOccurredEvent(r19, r20, r21, r22)
            r18 = r5
            com.google.appinventor.components.runtime.util.YailList r18 = com.google.appinventor.components.runtime.util.YailList.makeList((java.util.List) r18)
            r3 = r18
        L_0x0476:
            return r3
        L_0x0477:
            r18 = r5
            com.google.appinventor.components.runtime.util.YailList r18 = com.google.appinventor.components.runtime.util.YailList.makeList((java.util.List) r18)
            r3 = r18
            goto L_0x0476
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.GoogleMap.AddMarkersHue(com.google.appinventor.components.runtime.util.YailList):com.google.appinventor.components.runtime.util.YailList");
    }

    @SimpleFunction(description = "Set the property of a marker, note that the marker has to be added first or else will throw an exception! Properties include: \"color\"(hue value ranging from 0~360), \"title\", \"snippet\", \"draggable\"(give either true or false as the value).")
    public void UpdateMarker(int i, String str, Object obj) {
        Float f;
        Float f2;
        int i2 = i;
        String str2 = str;
        Object obj2 = obj;
        String trim = str2.trim();
        String trim2 = obj2.toString().trim();
        int i3 = Log.i("GoogleMap", "@UpdateMarker");
        int i4 = Log.i("GoogleMap", "markerId:".concat(String.valueOf(i2)));
        int i5 = Log.i("GoogleMap", "prop:".concat(String.valueOf(str2)));
        int i6 = Log.i("GoogleMap", "value:".concat(String.valueOf(obj2)));
        Marker hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(i2);
        int i7 = Log.i("GoogleMap", "marker?:".concat(String.valueOf(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2)));
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 != null) {
            if (trim.equals(PropertyTypeConstants.PROPERTY_TYPE_COLOR)) {
                int i8 = Log.i("GoogleMap", "we are changing color");
                new Float(trim2);
                Float f3 = f;
                Float f4 = f3;
                if (f3.floatValue() < 0.0f || f4.floatValue() > 360.0f) {
                    this.form.dispatchErrorOccurredEvent(this, "UpdateMarker", ErrorMessages.ERROR_GOOGLE_MAP_INVALID_INPUT, f4.toString());
                } else {
                    new Float(trim2);
                    hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.setIcon(BitmapDescriptorFactory.defaultMarker(f2.floatValue()));
                }
            }
            if (trim.equals("title")) {
                int i9 = Log.i("GoogleMap", "we are changing title");
                hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.setTitle(trim2);
            }
            if (trim.equals("snippet")) {
                int i10 = Log.i("GoogleMap", "we are changing snippet");
                hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.setSnippet(trim2);
            }
            if (trim.equals("draggable")) {
                int i11 = Log.i("GoogleMap", "we are changing draggable");
                hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.setDraggable(Boolean.valueOf(trim2).booleanValue());
            }
        }
    }

    @SimpleFunction(description = "Get all the existing markers's Ids")
    public YailList GetAllMarkerID() {
        return YailList.makeList((Collection) this.f416B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.values());
    }

    private Marker hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(int i) {
        int i2 = i;
        Marker marker = (Marker) hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f416B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T, Integer.valueOf(i2));
        Marker marker2 = marker;
        if (marker == null) {
            this.form.dispatchErrorOccurredEvent(this, "getMarkerIfExisted", ErrorMessages.ERROR_GOOGLE_MAP_MARKER_NOT_EXIST, Integer.toString(i2));
        }
        return marker2;
    }

    @SimpleFunction(description = "Remove a marker from the map")
    public void RemoveMarker(int i) {
        Marker hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(i);
        Marker marker = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2;
        if (hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 != null) {
            Integer remove = this.f416B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.remove(marker);
            marker.remove();
        }
    }

    public void onMarkerDrag(Marker marker) {
        Marker marker2 = marker;
        int i = Log.i("GoogleMap", "Dragging M:".concat(String.valueOf(marker2)));
        Integer num = this.f416B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.get(marker2);
        Integer num2 = num;
        if (num != null) {
            LatLng position = marker2.getPosition();
            OnMarkerDrag(num2.intValue(), position.latitude, position.longitude);
        }
        for (C0730a next : this.l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j) {
            C0730a aVar = next;
            if (next.f436hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.equals(marker2) || aVar.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.equals(marker2)) {
                boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = aVar.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(marker2);
            }
        }
    }

    public void onMarkerDragEnd(Marker marker) {
        Marker marker2 = marker;
        Integer num = this.f416B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.get(marker2);
        Integer num2 = num;
        if (num != null) {
            LatLng position = marker2.getPosition();
            OnMarkerDragEnd(num2.intValue(), position.latitude, position.longitude);
        }
        for (C0730a next : this.l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j) {
            C0730a aVar = next;
            if (next.f436hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.equals(marker2) || aVar.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.equals(marker2)) {
                boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = aVar.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(marker2);
                int intValue = this.f433wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.get(aVar).intValue();
                LatLng position2 = aVar.f436hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getPosition();
                FinishedDraggingCircle(intValue, position2.latitude, position2.longitude, aVar.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou);
            }
        }
    }

    public void onMarkerDragStart(Marker marker) {
        Marker marker2 = marker;
        Integer num = this.f416B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.get(marker2);
        Integer num2 = num;
        if (num != null) {
            LatLng position = marker2.getPosition();
            OnMarkerDragStart(num2.intValue(), position.latitude, position.longitude);
        }
        for (C0730a next : this.l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j) {
            C0730a aVar = next;
            if (next.f436hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.equals(marker2) || aVar.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.equals(marker2)) {
                boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = aVar.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(marker2);
            }
        }
    }

    @SimpleEvent(description = "When a marker starts been dragged")
    public void OnMarkerDragStart(int i, double d, double d2) {
        Runnable runnable;
        final int i2 = i;
        final double d3 = d;
        final double d4 = d2;
        new Runnable(this) {
            private /* synthetic */ GoogleMap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r12;
            }

            public final void run() {
                StringBuilder sb;
                new StringBuilder("a marker:");
                int i = Log.i("GoogleMap", sb.append(i2).append("starts been dragged").toString());
                GoogleMap googleMap = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                Object[] objArr = new Object[3];
                objArr[0] = Integer.valueOf(i2);
                Object[] objArr2 = objArr;
                objArr2[1] = Double.valueOf(d3);
                Object[] objArr3 = objArr2;
                objArr3[2] = Double.valueOf(d4);
                boolean dispatchEvent = EventDispatcher.dispatchEvent(googleMap, "OnMarkerDragStart", objArr3);
            }
        };
        this.f420hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.runOnUiThread(runnable);
    }

    @SimpleEvent(description = "When a marker is been dragged")
    public void OnMarkerDrag(int i, double d, double d2) {
        Runnable runnable;
        final int i2 = i;
        final double d3 = d;
        final double d4 = d2;
        new Runnable(this) {
            private /* synthetic */ GoogleMap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r12;
            }

            public final void run() {
                StringBuilder sb;
                new StringBuilder("a marker:");
                int i = Log.i("GoogleMap", sb.append(i2).append("is been dragged").toString());
                GoogleMap googleMap = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                Object[] objArr = new Object[3];
                objArr[0] = Integer.valueOf(i2);
                Object[] objArr2 = objArr;
                objArr2[1] = Double.valueOf(d3);
                Object[] objArr3 = objArr2;
                objArr3[2] = Double.valueOf(d4);
                boolean dispatchEvent = EventDispatcher.dispatchEvent(googleMap, "OnMarkerDrag", objArr3);
            }
        };
        this.f420hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.runOnUiThread(runnable);
    }

    @SimpleEvent(description = "When the user drags a marker and finish the action, returning marker's id and it's latest position")
    public void OnMarkerDragEnd(int i, double d, double d2) {
        Runnable runnable;
        final int i2 = i;
        final double d3 = d;
        final double d4 = d2;
        new Runnable(this) {
            private /* synthetic */ GoogleMap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r12;
            }

            public final void run() {
                StringBuilder sb;
                new StringBuilder("a marker:");
                int i = Log.i("GoogleMap", sb.append(i2).append("finishes been dragged").toString());
                GoogleMap googleMap = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                Object[] objArr = new Object[3];
                objArr[0] = Integer.valueOf(i2);
                Object[] objArr2 = objArr;
                objArr2[1] = Double.valueOf(d3);
                Object[] objArr3 = objArr2;
                objArr3[2] = Double.valueOf(d4);
                boolean dispatchEvent = EventDispatcher.dispatchEvent(googleMap, "OnMarkerDragEnd", objArr3);
            }
        };
        this.f420hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.runOnUiThread(runnable);
    }

    @SimpleEvent(description = "When a marker is clicked")
    public void OnMarkerClick(int i, double d, double d2) {
        Runnable runnable;
        final int i2 = i;
        final double d3 = d;
        final double d4 = d2;
        new Runnable(this) {
            private /* synthetic */ GoogleMap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r12;
            }

            public final void run() {
                StringBuilder sb;
                new StringBuilder("a marker:");
                int i = Log.i("GoogleMap", sb.append(i2).append("is clicked").toString());
                GoogleMap googleMap = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                Object[] objArr = new Object[3];
                objArr[0] = Integer.valueOf(i2);
                Object[] objArr2 = objArr;
                objArr2[1] = Double.valueOf(d3);
                Object[] objArr3 = objArr2;
                objArr3[2] = Double.valueOf(d4);
                boolean dispatchEvent = EventDispatcher.dispatchEvent(googleMap, "OnMarkerClick", objArr3);
            }
        };
        this.f420hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.runOnUiThread(runnable);
    }

    @SimpleEvent(description = "When the marker's infowindow is clicked, returning marker's id")
    public void InfoWindowClicked(int i) {
        Runnable runnable;
        final int i2 = i;
        new Runnable(this) {
            private /* synthetic */ GoogleMap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
            }

            public final void run() {
                StringBuilder sb;
                new StringBuilder("A marker: ");
                int i = Log.i("GoogleMap", sb.append(i2).append(" its info window is clicked").toString());
                boolean dispatchEvent = EventDispatcher.dispatchEvent(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, "InfoWindowClicked", Integer.valueOf(i2));
            }
        };
        this.f420hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.runOnUiThread(runnable);
    }

    public void onInfoWindowClick(Marker marker) {
        InfoWindowClicked(this.f416B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.get(marker).intValue());
    }

    public boolean onMarkerClick(Marker marker) {
        Marker marker2 = marker;
        Integer num = this.f416B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.get(marker2);
        LatLng position = marker2.getPosition();
        OnMarkerClick(num.intValue(), position.latitude, position.longitude);
        return false;
    }

    private static <T, E> T hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Map<T, E> map, E e) {
        E e2 = e;
        for (Map.Entry next : map.entrySet()) {
            if (e2.equals(next.getValue())) {
                return next.getKey();
            }
        }
        return null;
    }

    public void onCameraChange(CameraPosition cameraPosition) {
        CameraPosition cameraPosition2 = cameraPosition;
        CameraPositionChanged(Double.valueOf(cameraPosition2.target.latitude).doubleValue(), Double.valueOf(cameraPosition2.target.longitude).doubleValue(), Float.valueOf(cameraPosition2.bearing).floatValue(), Float.valueOf(cameraPosition2.tilt).floatValue(), Float.valueOf(cameraPosition2.zoom).floatValue());
    }

    @SimpleEvent(description = "Called after the camera position of a map has changed.")
    public void CameraPositionChanged(double d, double d2, float f, float f2, float f3) {
        Runnable runnable;
        final double d3 = d;
        final double d4 = d2;
        final float f4 = f;
        final float f5 = f2;
        final float f6 = f3;
        new Runnable(this) {
            private /* synthetic */ GoogleMap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r13;
            }

            public final void run() {
                StringBuilder sb;
                new StringBuilder("Camera's position has changed:");
                int i = Log.i("GoogleMap", sb.append(d3).append(", ").append(d4).append(", ").append(f4).append(",").append(f5).append(", ").append(f6).toString());
                GoogleMap googleMap = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                Object[] objArr = new Object[5];
                objArr[0] = Double.valueOf(d3);
                Object[] objArr2 = objArr;
                objArr2[1] = Double.valueOf(d4);
                Object[] objArr3 = objArr2;
                objArr3[2] = Float.valueOf(f4);
                Object[] objArr4 = objArr3;
                objArr4[3] = Float.valueOf(f5);
                Object[] objArr5 = objArr4;
                objArr5[4] = Float.valueOf(f6);
                boolean dispatchEvent = EventDispatcher.dispatchEvent(googleMap, "CameraPositionChanged", objArr5);
            }
        };
        this.f420hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.runOnUiThread(runnable);
    }

    public void onMapLongClick(LatLng latLng) {
        LatLng latLng2 = latLng;
        OnMapLongClick(latLng2.latitude, latLng2.longitude);
    }

    @SimpleEvent(description = "Called when the user makes a long-press gesture on the map")
    public void OnMapLongClick(double d, double d2) {
        Runnable runnable;
        final double d3 = d;
        final double d4 = d2;
        new Runnable(this) {
            private /* synthetic */ GoogleMap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r11;
            }

            public final void run() {
                StringBuilder sb;
                new StringBuilder("Map is longclicked at:");
                int i = Log.i("GoogleMap", sb.append(d3).append(", ").append(d4).toString());
                GoogleMap googleMap = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                Object[] objArr = new Object[2];
                objArr[0] = Double.valueOf(d3);
                Object[] objArr2 = objArr;
                objArr2[1] = Double.valueOf(d4);
                boolean dispatchEvent = EventDispatcher.dispatchEvent(googleMap, "OnMapLongClick", objArr2);
            }
        };
        this.f420hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.runOnUiThread(runnable);
    }

    public void onMapClick(LatLng latLng) {
        LatLng latLng2 = latLng;
        int i = Log.i("GoogleMap", "receive google maps's onMapClick");
        OnMapClick(latLng2.latitude, latLng2.longitude);
    }

    @SimpleEvent(description = "Called when the user makes a tap gesture on the map")
    public void OnMapClick(double d, double d2) {
        Runnable runnable;
        final double d3 = d;
        final double d4 = d2;
        new Runnable(this) {
            private /* synthetic */ GoogleMap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r11;
            }

            public final void run() {
                StringBuilder sb;
                new StringBuilder("map is clicked at:");
                int i = Log.i("GoogleMap", sb.append(d3).append(", ").append(d4).toString());
                GoogleMap googleMap = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                Object[] objArr = new Object[2];
                objArr[0] = Double.valueOf(d3);
                Object[] objArr2 = objArr;
                objArr2[1] = Double.valueOf(d4);
                boolean dispatchEvent = EventDispatcher.dispatchEvent(googleMap, "OnMapClick", objArr2);
            }
        };
        this.f420hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.runOnUiThread(runnable);
    }

    @SimpleFunction(description = "Move the map's camera to the specified position and zoom level")
    public void MoveCamera(double d, double d2, float f) {
        LatLng latLng;
        LatLng latLng2;
        double d3 = d;
        double d4 = d2;
        float f2 = f;
        if (this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            com.google.android.gms.maps.GoogleMap googleMap = this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            new LatLng(d3, d4);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, f2));
            this.f428hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = Float.valueOf(f2);
            new LatLng(d3, d4);
            this.f427hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = latLng2;
        }
    }

    @SimpleFunction(description = "Transforms the camera such that the specified latitude/longitude bounds are centered on screen at the greatest possible zoom level. Need to specify both latitudes and longitudes for both northeast location and southwest location of the bounding box")
    public void BoundCamera(double d, double d2, double d3, double d4) {
        LatLng latLng;
        LatLng latLng2;
        LatLngBounds latLngBounds;
        double d5 = d;
        double d6 = d2;
        double d7 = d3;
        double d8 = d4;
        if (this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            new LatLng(d5, d6);
            new LatLng(d7, d8);
            new LatLngBounds(latLng, latLng2);
            this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, 0));
        }
    }

    /* renamed from: com.google.appinventor.components.runtime.GoogleMap$a */
    class C0730a {
        Marker B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T;
        final Circle hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
        final Marker f436hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
        private /* synthetic */ GoogleMap f437hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        double wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou;

        public C0730a(GoogleMap googleMap, LatLng latLng, double d, float f, int i, int i2) {
            MarkerOptions markerOptions;
            MarkerOptions markerOptions2;
            CircleOptions circleOptions;
            GoogleMap googleMap2 = googleMap;
            LatLng latLng2 = latLng;
            double d2 = d;
            this.f437hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = googleMap2;
            this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = d2;
            com.google.android.gms.maps.GoogleMap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = GoogleMap.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(googleMap2);
            new MarkerOptions();
            this.f436hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.addMarker(markerOptions.position(latLng2).draggable(true));
            com.google.android.gms.maps.GoogleMap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME3 = GoogleMap.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(googleMap2);
            new MarkerOptions();
            this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME3.addMarker(markerOptions2.position(GoogleMap.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(latLng2, d2)).draggable(true).icon(BitmapDescriptorFactory.defaultMarker(210.0f)));
            com.google.android.gms.maps.GoogleMap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME4 = GoogleMap.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(googleMap2);
            new CircleOptions();
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME4.addCircle(circleOptions.center(latLng2).radius(d2).strokeWidth(f).strokeColor(i).fillColor(i2));
        }

        public final boolean hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(Marker marker) {
            Marker marker2 = marker;
            if (marker2.equals(this.f436hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME)) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setCenter(marker2.getPosition());
                this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.setPosition(GoogleMap.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(marker2.getPosition(), this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou));
                return true;
            } else if (!marker2.equals(this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T)) {
                return false;
            } else {
                this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = GoogleMap.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.f436hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getPosition(), this.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.getPosition());
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setRadius(this.wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou);
                return true;
            }
        }
    }

    /* access modifiers changed from: private */
    public static LatLng hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(LatLng latLng, double d) {
        LatLng latLng2;
        LatLng latLng3 = latLng;
        new LatLng(latLng3.latitude, latLng3.longitude + (Math.toDegrees(d / 6371009.0d) / Math.cos(Math.toRadians(latLng3.latitude))));
        return latLng2;
    }

    public void onConnectionFailed(ConnectionResult connectionResult) {
    }

    public void onConnected(Bundle bundle) {
        Bundle bundle2 = bundle;
        int i = Log.i("GoogleMap", "onConnected to location listener.....");
        PendingResult<Status> requestLocationUpdates = LocationServices.FusedLocationApi.requestLocationUpdates(this.f423hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, (LocationListener) this);
    }

    public void onConnectionSuspended(int i) {
    }

    public void onDisconnected() {
    }

    public void onPause() {
        int i = Log.i("GoogleMap", "OnPause, remote LocationClient");
        if (this.f423hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            int i2 = Log.i("GoogleMap", "before location client disconnect");
            this.f423hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.disconnect();
        }
    }

    public void onLocationChanged(Location location) {
        Location location2 = location;
        OnLocationChanged(location2.getLatitude(), location2.getLongitude());
    }

    @SimpleEvent(description = "Triggers this event when user location has changed. Only works when EnableMylocation is set to true")
    public void OnLocationChanged(double d, double d2) {
        Runnable runnable;
        final double d3 = d;
        final double d4 = d2;
        new Runnable(this) {
            private /* synthetic */ GoogleMap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r11;
            }

            public final void run() {
                StringBuilder sb;
                new StringBuilder("location changed");
                int i = Log.i("GoogleMap", sb.append(d3).append(d4).toString());
                GoogleMap googleMap = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                Object[] objArr = new Object[2];
                objArr[0] = Double.valueOf(d3);
                Object[] objArr2 = objArr;
                objArr2[1] = Double.valueOf(d4);
                boolean dispatchEvent = EventDispatcher.dispatchEvent(googleMap, "OnLocationChanged", objArr2);
            }
        };
        this.f420hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.runOnUiThread(runnable);
    }

    @SimpleFunction(description = "A Polygon is an enclosed shape that can be used to mark areas on the map.")
    public void addPolygon(double d, double d2, double d3, double d4) {
        PolygonOptions polygonOptions;
        LatLng latLng;
        LatLng latLng2;
        LatLng latLng3;
        LatLng latLng4;
        LatLng latLng5;
        double d5 = d;
        double d6 = d2;
        double d7 = d3;
        double d8 = d4;
        if (this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            PolygonOptions polygonOptions2 = polygonOptions;
            new PolygonOptions();
            LatLng[] latLngArr = new LatLng[5];
            new LatLng(d5, d8);
            latLngArr[0] = latLng;
            LatLng[] latLngArr2 = latLngArr;
            new LatLng(d6, d8);
            latLngArr2[1] = latLng2;
            LatLng[] latLngArr3 = latLngArr2;
            new LatLng(d6, d7);
            latLngArr3[2] = latLng3;
            LatLng[] latLngArr4 = latLngArr3;
            new LatLng(d5, d7);
            latLngArr4[3] = latLng4;
            LatLng[] latLngArr5 = latLngArr4;
            new LatLng(d5, d8);
            latLngArr5[4] = latLng5;
            Integer put = this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.put(this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addPolygon(polygonOptions2.add(latLngArr5)), 1);
        }
    }

    @SimpleFunction(description = "Clear all Polygons.")
    public void clearAllPolygons() {
        for (Polygon remove : this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.keySet()) {
            remove.remove();
        }
    }

    @SimpleFunction(description = "Draw central square.")
    public void drawCentralSquare() {
        StringBuilder sb;
        PolygonOptions polygonOptions;
        LatLng latLng;
        LatLng latLng2;
        LatLng latLng3;
        LatLng latLng4;
        LatLng latLng5;
        if (this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            LatLngBounds latLngBounds = this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getProjection().getVisibleRegion().latLngBounds;
            LatLng latLng6 = latLngBounds.northeast;
            LatLng latLng7 = latLngBounds.southwest;
            double d = latLng6.latitude;
            double d2 = latLng6.latitude;
            double d3 = latLng7.latitude;
            double d4 = latLng7.longitude;
            double d5 = this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getCameraPosition().target.latitude;
            double d6 = this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getCameraPosition().target.longitude;
            double d7 = (d5 - d3) * 0.5d;
            double d8 = (d6 - d4) * 0.5d;
            new StringBuilder("[{lat:");
            AddMarkersFromJson(sb.append(d5).append(",lng:").append(d6).append("}]").toString());
            PolygonOptions polygonOptions2 = polygonOptions;
            new PolygonOptions();
            LatLng[] latLngArr = new LatLng[5];
            new LatLng(d5 + d7, d6 + d8);
            latLngArr[0] = latLng;
            LatLng[] latLngArr2 = latLngArr;
            new LatLng(d5 - d7, d6 + d8);
            latLngArr2[1] = latLng2;
            LatLng[] latLngArr3 = latLngArr2;
            new LatLng(d5 - d7, d6 - d8);
            latLngArr3[2] = latLng3;
            LatLng[] latLngArr4 = latLngArr3;
            new LatLng(d5 + d7, d6 - d8);
            latLngArr4[3] = latLng4;
            LatLng[] latLngArr5 = latLngArr4;
            new LatLng(d5 + d7, d6 + d8);
            latLngArr5[4] = latLng5;
            Integer put = this.hR11jdqaRrvBRiBFd4KN6gI7d8MNQVP5Yc7fufDZjGGTeTxaualejjrhiR1Iz2xO.put(this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addPolygon(polygonOptions2.add(latLngArr5)), 1);
        }
    }

    @SimpleFunction(description = "Get bounding box.")
    public String getBoundingBox(double d, double d2, double d3) {
        StringBuilder sb;
        double radians = Math.toRadians(d);
        double radians2 = Math.toRadians(d2);
        double d4 = d3 * 1000.0d;
        double cos = 4.0680631590769E13d * Math.cos(radians);
        double sin = 4.040829980355529E13d * Math.sin(radians);
        double cos2 = 6378137.0d * Math.cos(radians);
        double d5 = cos;
        double d6 = d5 * d5;
        double d7 = sin;
        double d8 = d6 + (d7 * d7);
        double d9 = cos2;
        double d10 = d9 * d9;
        double sin2 = 6356752.3d * Math.sin(radians);
        double sqrt = Math.sqrt(d8 / (d10 + (sin2 * sin2)));
        double d11 = sqrt;
        double cos3 = sqrt * Math.cos(radians);
        double d12 = radians - (d4 / d11);
        double d13 = radians + (d4 / d11);
        double d14 = radians2 - (d4 / cos3);
        double d15 = radians2 + (d4 / cos3);
        new StringBuilder();
        return sb.append(Math.toDegrees(d12)).append(",").append(Math.toDegrees(d14)).append(",").append(Math.toDegrees(d13)).append(",").append(Math.toDegrees(d15)).toString();
    }

    @SimpleFunction(description = "Add overlay.")
    public void addOverlay() {
        LatLng latLng;
        GroundOverlayOptions groundOverlayOptions;
        if (this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            new LatLng(40.714086d, -74.228697d);
            new GroundOverlayOptions();
            GroundOverlay addGroundOverlay = this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addGroundOverlay(groundOverlayOptions.position(latLng, 8600.0f, 6500.0f));
        }
    }

    @SimpleFunction(description = "Add title overlay.")
    public void addTileOverlay() {
        TileProvider tileProvider;
        TileOverlayOptions tileOverlayOptions;
        if (this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            new UrlTileProvider(this) {
                private /* synthetic */ GoogleMap hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r6;
                }

                public final URL getTileUrl(int i, int i2, int i3) {
                    boolean z;
                    Throwable th;
                    URL url;
                    int i4 = i3;
                    Object[] objArr = new Object[3];
                    objArr[0] = Integer.valueOf(i4);
                    Object[] objArr2 = objArr;
                    objArr2[1] = Integer.valueOf(i);
                    Object[] objArr3 = objArr2;
                    objArr3[2] = Integer.valueOf(i2);
                    String format = String.format("http://my.image.server/images/%d/%d/%d.png", objArr3);
                    int i5 = i4;
                    int i6 = i5;
                    if (i5 < 12 || i6 > 16) {
                        z = false;
                    } else {
                        z = true;
                    }
                    if (!z) {
                        return null;
                    }
                    try {
                        URL url2 = url;
                        new URL(format);
                        return url2;
                    } catch (MalformedURLException e) {
                        MalformedURLException malformedURLException = e;
                        Throwable th2 = th;
                        new AssertionError(malformedURLException);
                        throw th2;
                    }
                }
            };
            com.google.android.gms.maps.GoogleMap googleMap = this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            new TileOverlayOptions();
            TileOverlay addTileOverlay = googleMap.addTileOverlay(tileOverlayOptions.tileProvider(tileProvider));
        }
    }

    @SimpleFunction(description = "Get map center. If a error occures the output will be '-999'.")
    public String getMapCenter() {
        if (this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            return this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getCameraPosition().target.toString();
        }
        return "-999";
    }

    @DesignerProperty(defaultValue = "15", editorType = "non_negative_float")
    @SimpleProperty(description = "Move the map's camera to the specified zoom level.")
    public void CameraZoomLevel(float f) {
        float f2 = f;
        if (this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.moveCamera(CameraUpdateFactory.zoomTo(f2));
            return;
        }
        this.f428hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = Float.valueOf(f2);
    }

    @SimpleProperty(description = "Gets the current zoom level of the map's camera.")
    public float CameraZoomLevel() {
        if (this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            return this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getCameraPosition().zoom;
        }
        return Float.NaN;
    }

    @DesignerProperty(defaultValue = "standard", editorType = "google_map_theme")
    @SimpleProperty(description = "Sets the theme of the map. The choices are \"standard\"(default), \"silver\", \"retro\", \"dark\", \"night\", \"aubergine\", \"vintage\", \"kodular\" and \"roads-only\".")
    public void Theme(String str) {
        MapStyleOptions mapStyleOptions;
        StringBuilder sb;
        String str2 = str;
        if (str2 == null) {
            return;
        }
        if (this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            this.eQeQhiSmlElGLbwx31fXfv53XsxBlAuCtzOUdhRtXefyKZkKyhRMzOCKSg7WR08 = str2;
        } else if (!GoogleMapStyleOptions.JSON_BY_THEME.containsKey(str2)) {
            this.form.dispatchErrorOccurredEvent(this, "SetTheme", ErrorMessages.ERROR_GOOGLE_MAP_INVALID_THEME, str2);
        } else {
            new MapStyleOptions(GoogleMapStyleOptions.JSON_BY_THEME.get(str2));
            if (this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setMapStyle(mapStyleOptions)) {
                this.SHSZV5sTay8ykRHafXU624sH0bmI6VeYHAe3FtLV8LV4djzabBBIyQGaIvRsAwmk = str2;
                return;
            }
            new StringBuilder();
            this.form.dispatchErrorOccurredEvent(this, "SetStyle", ErrorMessages.ERROR_GOOGLE_MAP_INVALID_STYLE_JSON, sb.append(str2).append("(theme)").toString());
        }
    }

    @SimpleProperty(description = "Gets the theme of the map. The choices are \"standard\"(default), \"silver\", \"retro\", \"dark\", \"night\", \"aubergine\", \"vintage\", \"kodular\" and \"roads-only\". Returns \"custom\" if the style of the map has been set from json.")
    public String Theme() {
        return this.SHSZV5sTay8ykRHafXU624sH0bmI6VeYHAe3FtLV8LV4djzabBBIyQGaIvRsAwmk;
    }

    @SimpleProperty(description = "Sets the style of the map from json. Just use a text field and paste there the json data. Create a custom map style at https://mapstyle.withgoogle.com/. Set the theme to \"standard\" to clear the style json.")
    public void Style(String str) {
        MapStyleOptions mapStyleOptions;
        String str2 = str;
        if (this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME == null) {
            this.XkTExsgDjzL7UIaxQorrmSTr7jZbDxCmXSVfTpg7zAbwFxeOpcI2x1hAyx12QFiS = str2;
            return;
        }
        new MapStyleOptions(str2);
        if (this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setMapStyle(mapStyleOptions)) {
            this.SHSZV5sTay8ykRHafXU624sH0bmI6VeYHAe3FtLV8LV4djzabBBIyQGaIvRsAwmk = GoogleMapStyleOptions.THEME_CUSTOM;
            return;
        }
        this.form.dispatchErrorOccurredEvent(this, "SetStyle", ErrorMessages.ERROR_GOOGLE_MAP_INVALID_STYLE_JSON, str2);
    }

    @DesignerProperty(defaultValue = "0", editorType = "non_negative_float")
    @SimpleProperty(description = "Move the map's camera to the specified tilt, the angle (in degrees) from the nadir (directly facing the Earth). Must be a value between 0.0 and 90.0")
    public void CameraAngle(float f) {
        CameraPosition.Builder builder;
        float f2 = f;
        if (f2 >= 0.0f && f2 <= 90.0f) {
            if (this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
                com.google.android.gms.maps.GoogleMap googleMap = this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
                new CameraPosition.Builder(this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getCameraPosition());
                googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(builder.tilt(f2).build()));
                return;
            }
            this.f432wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou = Float.valueOf(f2);
        }
    }

    @SimpleProperty(description = "Gets the current tilt, the angle (in degrees) from the nadir (directly facing the Earth), of the map's camera.")
    public float CameraAngle() {
        if (this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            return this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getCameraPosition().tilt;
        }
        return Float.NaN;
    }

    @DesignerProperty(defaultValue = "0", editorType = "non_negative_float")
    @SimpleProperty(description = "Move the map's camera to the specified bearing, the direction that the camera is pointing in (in degrees clockwise from north).")
    public void CameraRotation(float f) {
        CameraPosition.Builder builder;
        float f2 = f;
        if (this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            com.google.android.gms.maps.GoogleMap googleMap = this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            new CameraPosition.Builder(this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getCameraPosition());
            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(builder.bearing(f2).build()));
            return;
        }
        this.f415B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T = Float.valueOf(f2);
    }

    @SimpleProperty(description = "Gets the current bearing, the direction that the camera is pointing in (in degrees clockwise from north), of the map's camera.")
    public float CameraRotation() {
        if (this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME != null) {
            return this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getCameraPosition().bearing;
        }
        return Float.NaN;
    }

    @Deprecated
    @SimpleFunction(description = "Deprecated block! Don't use this anymore. Use instead 'Camera Zoom Level'.")
    public float getZoomLevelInfo() {
        return CameraZoomLevel();
    }

    @SimpleEvent(description = "This event will be invoked when a user clicks on a point of interest. This can be a shop, coffee-bar or else.")
    public void OnPointOfInterestClick(double d, double d2, String str, String str2) {
        Object[] objArr = new Object[4];
        objArr[0] = Double.valueOf(d);
        Object[] objArr2 = objArr;
        objArr2[1] = Double.valueOf(d2);
        Object[] objArr3 = objArr2;
        objArr3[2] = str;
        Object[] objArr4 = objArr3;
        objArr4[3] = str2;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "OnPointOfInterestClick", objArr4);
    }

    public void onPoiClick(PointOfInterest pointOfInterest) {
        PointOfInterest pointOfInterest2 = pointOfInterest;
        if (pointOfInterest2 != null) {
            OnPointOfInterestClick(pointOfInterest2.latLng.latitude, pointOfInterest2.latLng.longitude, pointOfInterest2.name, pointOfInterest2.placeId);
        }
    }

    private static List<LatLng> hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(YailList yailList) {
        List<LatLng> list;
        Object obj;
        YailList yailList2 = yailList;
        new ArrayList();
        List<LatLng> list2 = list;
        for (int i = 0; i < yailList2.size(); i++) {
            YailList yailList3 = (YailList) yailList2.getObject(i);
            new LatLng(((Number) yailList3.getObject(0)).doubleValue(), ((Number) yailList3.getObject(1)).doubleValue());
            boolean add = list2.add(obj);
        }
        return list2;
    }

    @SimpleFunction(description = "Convert a JsonArray of points (lat, lng pairs) to a list.")
    public YailList GetPointsFromJson(String str) {
        List list;
        String str2 = str;
        try {
            new ArrayList();
            List list2 = list;
            for (List makeList : (List) JsonUtil.getObjectFromJson(str2)) {
                boolean add = list2.add(YailList.makeList(makeList));
            }
            return YailList.makeList(list2);
        } catch (Exception e) {
            return null;
        }
    }

    @SimpleFunction(description = "This block will return the unique id of the new added polyline. Create a new polyline on the map. Use for 'points' a list of lat, lng pairs. A integer for the 'width' (in pixel) and a valid color for the 'color' parameter.")
    public int AddPolyline(YailList yailList, float f, int i) {
        PolylineOptions polylineOptions;
        com.google.android.gms.maps.GoogleMap googleMap = this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        new PolylineOptions();
        Polyline addPolyline = googleMap.addPolyline(polylineOptions.addAll(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(yailList)).width(f).color(i));
        int incrementAndGet = B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T.incrementAndGet();
        Polyline put = this.f430vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq.put(Integer.valueOf(incrementAndGet), addPolyline);
        return incrementAndGet;
    }

    @SimpleFunction(description = "Use this block to remove a polyline from the map. It will return true if it was successful.")
    public boolean RemovePolyline(int i) {
        Polyline remove = this.f430vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq.remove(Integer.valueOf(i));
        Polyline polyline = remove;
        if (remove == null) {
            return false;
        }
        polyline.remove();
        return true;
    }

    @SimpleFunction(description = "Update any polyline with the given id. You can change the property values for 'width' (in pixel), 'color' or 'points (a list of lat, lng pairs).")
    public void UpdatePolyline(int i, String str, Object obj) {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        String str2 = str;
        Object obj2 = obj;
        Polyline polyline = this.f430vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq.get(Integer.valueOf(i));
        Polyline polyline2 = polyline;
        if (polyline != null) {
            boolean z = true;
            switch (str2.hashCode()) {
                case -982754077:
                    if (str2.equals("points")) {
                        z = false;
                        break;
                    }
                    break;
                case 94842723:
                    if (str2.equals(PropertyTypeConstants.PROPERTY_TYPE_COLOR)) {
                        z = true;
                        break;
                    }
                    break;
                case 113126854:
                    if (str2.equals("width")) {
                        z = true;
                        break;
                    }
                    break;
            }
            switch (z) {
                case false:
                    try {
                        polyline2.setPoints(hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((YailList) obj2));
                        return;
                    } catch (Exception e) {
                        new StringBuilder();
                        int e2 = Log.e("GoogleMap", sb3.append(e.getMessage()).toString());
                        return;
                    }
                case true:
                    try {
                        Float valueOf = Float.valueOf(((Number) obj2).floatValue());
                        Float f = valueOf;
                        if (valueOf != null) {
                            polyline2.setWidth(f.floatValue());
                            return;
                        }
                        return;
                    } catch (Exception e3) {
                        new StringBuilder();
                        int e4 = Log.e("GoogleMap", sb2.append(e3.getMessage()).toString());
                        return;
                    }
                case true:
                    try {
                        Integer valueOf2 = Integer.valueOf(((Number) obj2).intValue());
                        Integer num = valueOf2;
                        if (valueOf2 != null) {
                            polyline2.setColor(num.intValue());
                            return;
                        }
                        return;
                    } catch (Exception e5) {
                        new StringBuilder();
                        int e6 = Log.e("GoogleMap", sb.append(e5.getMessage()).toString());
                        return;
                    }
                default:
                    return;
            }
        }
    }

    @SimpleFunction(description = "This will return a list with all available polyline id's.")
    public YailList GetAllPolylineIds() {
        return YailList.makeList((Set) this.f430vSp02fkBXgM8EI0gm0rKWXHQ6wdQINJBQuAtCR15YU8g4XNqVKV8r32SYxkQYxkq.keySet());
    }

    @SimpleFunction(description = "Create a circle overlay on the map UI with specified latitude and longitude for center. \"hue\" (min 0, max 360) and \"alpha\" (min 0, max 255) are used to set color and transparency level of the circle, \"strokeWidth\" and \"strokeColor\" are for the perimeter of the circle. Returning a unique id of the circle for future reference to events raised by moving this circle. If the circle isset to be draggable, two default markers will appear on the map: one in the center of the circle, another on the perimeter.")
    public int AddCircle(double d, double d2, double d3, int i, float f, float f2, int i2, boolean z) {
        CircleOptions circleOptions;
        LatLng latLng;
        Object obj;
        LatLng latLng2;
        double d4 = d;
        double d5 = d2;
        double d6 = d3;
        float f3 = f2;
        int i3 = i2;
        int incrementAndGet = f414hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.incrementAndGet();
        float[] fArr = new float[3];
        fArr[0] = f;
        float[] fArr2 = fArr;
        fArr2[1] = 1.0f;
        float[] fArr3 = fArr2;
        fArr3[2] = 1.0f;
        int HSVToColor = Color.HSVToColor(i, fArr3);
        if (z) {
            new LatLng(d4, d5);
            new C0730a(this, latLng2, d6, f3, i3, HSVToColor);
            Object obj2 = obj;
            boolean add = this.l557Ll1q9gLt3cEfyQaLTgWgvIutpQIPuKKLXX2glk42j33zjQ1xoBSDeCkOGk6j.add(obj2);
            Integer put = this.f433wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.put(obj2, Integer.valueOf(incrementAndGet));
        } else {
            com.google.android.gms.maps.GoogleMap googleMap = this.f424hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
            new CircleOptions();
            new LatLng(d4, d5);
            Integer put2 = this.f433wq07duYRO6iFAgWM70EZOSvbCMKs1QznMRJKrct0XuHOBYqCk3XqOKtSBGIpDou.put(googleMap.addCircle(circleOptions.center(latLng).radius(d6).strokeWidth(f3).strokeColor(i3).fillColor(HSVToColor)), Integer.valueOf(incrementAndGet));
        }
        return incrementAndGet;
    }

    static /* synthetic */ double hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(LatLng latLng, LatLng latLng2) {
        LatLng latLng3 = latLng;
        LatLng latLng4 = latLng2;
        float[] fArr = new float[1];
        Location.distanceBetween(latLng3.latitude, latLng3.longitude, latLng4.latitude, latLng4.longitude, fArr);
        return (double) fArr[0];
    }
}

package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import com.android.billingclient.api.AcknowledgePurchaseParams;
import com.android.billingclient.api.AcknowledgePurchaseResponseListener;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ConsumeParams;
import com.android.billingclient.api.ConsumeResponseListener;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchaseHistoryRecord;
import com.android.billingclient.api.PurchaseHistoryResponseListener;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.OnInitializeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@DesignerComponent(category = ComponentCategory.MONETIZATION_GENERAL, description = "In-App Purchases component by Pavitra.", helpUrl = "https://docs.kodular.io/components/monetization/in-app-billing/", iconName = "images/billing.png", nonVisible = true, version = 1)
@UsesLibraries(libraries = "play-billing.aar, play-billing.jar")
@SimpleObject
@UsesPermissions(permissionNames = "com.android.vending.BILLING, android.permission.INTERNET")
public class Billing extends AndroidNonvisibleComponent implements BillingClientStateListener, PurchasesUpdatedListener, OnResumeListener, OnInitializeListener {
    private static final String LOG_TAG = "KodularBilling";
    private Activity activity;
    /* access modifiers changed from: private */
    public BillingClient billingClient;
    private Context context;
    /* access modifiers changed from: private */
    public HashMap<String, SkuDetails> skuDetailsCache;
    private boolean testMode = false;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Billing(com.google.appinventor.components.runtime.ComponentContainer r7) {
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
            r2.testMode = r3
            r2 = r0
            java.util.HashMap r3 = new java.util.HashMap
            r5 = r3
            r3 = r5
            r4 = r5
            r4.<init>()
            r2.skuDetailsCache = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.activity = r3
            r2 = r1
            com.google.appinventor.components.runtime.Form r2 = r2.$form()
            r3 = r0
            r2.registerForOnInitialize(r3)
            r2 = r1
            com.google.appinventor.components.runtime.Form r2 = r2.$form()
            r3 = r0
            r2.registerForOnResume(r3)
            r2 = r0
            r5 = r2
            r2 = r5
            r3 = r5
            android.content.Context r3 = r3.context
            com.android.billingclient.api.BillingClient$Builder r3 = com.android.billingclient.api.BillingClient.newBuilder(r3)
            r4 = r0
            com.android.billingclient.api.BillingClient$Builder r3 = r3.setListener(r4)
            com.android.billingclient.api.BillingClient$Builder r3 = r3.enablePendingPurchases()
            com.android.billingclient.api.BillingClient r3 = r3.build()
            r2.billingClient = r3
            r2 = r0
            com.android.billingclient.api.BillingClient r2 = r2.billingClient
            r3 = r0
            r2.startConnection(r3)
            java.lang.String r2 = "KodularBilling"
            java.lang.String r3 = "Billing Created"
            int r2 = android.util.Log.d(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.Billing.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleFunction(description = "Load Owned Purchases from Google.")
    public void LoadOwnedPurchases() {
        List list;
        List list2;
        PurchaseHistoryResponseListener purchaseHistoryResponseListener;
        new ArrayList();
        new ArrayList();
        final List list3 = list;
        final List list4 = list2;
        new PurchaseHistoryResponseListener(this) {
            final /* synthetic */ Billing hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r7;
            }

            public final void onPurchaseHistoryResponse(BillingResult billingResult, List<PurchaseHistoryRecord> list) {
                PurchaseHistoryResponseListener purchaseHistoryResponseListener;
                List<PurchaseHistoryRecord> list2 = list;
                if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isSuccessful(billingResult) && list2 != null) {
                    for (PurchaseHistoryRecord sku : list2) {
                        boolean add = list3.add(sku.getSku());
                    }
                }
                new PurchaseHistoryResponseListener(this) {
                    private /* synthetic */ C05821 hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                    {
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                    }

                    public final void onPurchaseHistoryResponse(BillingResult billingResult, List<PurchaseHistoryRecord> list) {
                        List<PurchaseHistoryRecord> list2 = list;
                        if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isSuccessful(billingResult) && list2 != null) {
                            for (PurchaseHistoryRecord sku : list2) {
                                boolean add = list4.add(sku.getSku());
                            }
                        }
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.GotOwnedPurchases(list3, list4);
                    }
                };
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.billingClient.queryPurchaseHistoryAsync("subs", purchaseHistoryResponseListener);
            }
        };
        this.billingClient.queryPurchaseHistoryAsync("inapp", purchaseHistoryResponseListener);
        int d = Log.d(LOG_TAG, "Load Owned Purchases called");
    }

    @SimpleFunction(description = "Purchase a product with the given product id.")
    public void Purchase(String str) {
        SkuDetailsResponseListener skuDetailsResponseListener;
        String str2 = str;
        if (this.testMode) {
            str2 = "android.test.purchased";
        }
        int d = Log.d(LOG_TAG, "Purchase: ".concat(String.valueOf(str2)));
        if (this.skuDetailsCache.containsKey(str2)) {
            startBillingFlow(this.skuDetailsCache.get(str2));
            return;
        }
        new SkuDetailsResponseListener(this) {
            private /* synthetic */ Billing hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void onSkuDetailsResponse(BillingResult billingResult, List<SkuDetails> list) {
                BillingResult billingResult2 = billingResult;
                List<SkuDetails> list2 = list;
                if (list2 != null && list2.size() > 0) {
                    SkuDetails skuDetails = list2.get(0);
                    Object put = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.skuDetailsCache.put(skuDetails.getSku(), skuDetails);
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.startBillingFlow(skuDetails);
                }
            }
        };
        this.billingClient.querySkuDetailsAsync(getSkuParams(str2, "inapp"), skuDetailsResponseListener);
    }

    @SimpleFunction(description = "Returns true if the product with the specific id is purchased.")
    public boolean IsPurchased(String str) {
        String str2 = str;
        Purchase.PurchasesResult queryPurchases = this.billingClient.queryPurchases("inapp");
        if (isSuccessful(queryPurchases)) {
            for (Purchase sku : queryPurchases.getPurchasesList()) {
                if (sku.getSku().equals(str2)) {
                    return true;
                }
            }
        }
        return false;
    }

    @SimpleFunction(description = "Get product details from the specific product id.")
    public void ProductDetails(String str) {
        SkuDetailsResponseListener skuDetailsResponseListener;
        String str2 = str;
        if (this.skuDetailsCache.containsKey(str2)) {
            SkuDetails skuDetails = this.skuDetailsCache.get(str2);
            GotProductDetails(true, skuDetails.getSku(), skuDetails.getTitle(), skuDetails.getDescription(), skuDetails.getPriceCurrencyCode(), skuDetails.getPrice());
            return;
        }
        new SkuDetailsResponseListener(this) {
            private /* synthetic */ Billing hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void onSkuDetailsResponse(BillingResult billingResult, List<SkuDetails> list) {
                BillingResult billingResult2 = billingResult;
                List<SkuDetails> list2 = list;
                if (list2 == null || list2.size() <= 0) {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.GotProductDetails(false, "", "", "", "", "");
                    return;
                }
                SkuDetails skuDetails = list2.get(0);
                Object put = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.skuDetailsCache.put(skuDetails.getSku(), skuDetails);
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.GotProductDetails(true, skuDetails.getSku(), skuDetails.getTitle(), skuDetails.getDescription(), skuDetails.getPriceCurrencyCode(), skuDetails.getPrice());
            }
        };
        this.billingClient.querySkuDetailsAsync(getSkuParams(str2, "inapp"), skuDetailsResponseListener);
        int d = Log.d(LOG_TAG, "ProductDetails: ".concat(String.valueOf(str2)));
    }

    @SimpleFunction(description = "Subscribe a product with the given product id.")
    public void Subscribe(String str) {
        SkuDetailsResponseListener skuDetailsResponseListener;
        String str2 = str;
        if (this.testMode) {
            str2 = "android.test.purchased";
        }
        if (isFeatureSupported("subscriptions")) {
            int d = Log.d(LOG_TAG, "Subscribe: ".concat(String.valueOf(str2)));
            if (this.skuDetailsCache.containsKey(str2)) {
                startBillingFlow(this.skuDetailsCache.get(str2));
                return;
            }
            new SkuDetailsResponseListener(this) {
                private /* synthetic */ Billing hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                }

                public final void onSkuDetailsResponse(BillingResult billingResult, List<SkuDetails> list) {
                    BillingResult billingResult2 = billingResult;
                    List<SkuDetails> list2 = list;
                    if (list2 != null && list2.size() > 0) {
                        SkuDetails skuDetails = list2.get(0);
                        Object put = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.skuDetailsCache.put(skuDetails.getSku(), skuDetails);
                        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.startBillingFlow(skuDetails);
                    }
                }
            };
            this.billingClient.querySkuDetailsAsync(getSkuParams(str2, "subs"), skuDetailsResponseListener);
        }
    }

    @SimpleFunction(description = "Returns true if the product is subscribed.")
    public boolean IsSubscribed(String str) {
        String str2 = str;
        Purchase.PurchasesResult queryPurchases = this.billingClient.queryPurchases("subs");
        if (isSuccessful(queryPurchases)) {
            for (Purchase sku : queryPurchases.getPurchasesList()) {
                if (sku.getSku().equals(str2)) {
                    return true;
                }
            }
        }
        return false;
    }

    @SimpleFunction(description = "Get subscription details from the given id.")
    public void SubscriptionDetails(String str) {
        SkuDetailsResponseListener skuDetailsResponseListener;
        String str2 = str;
        if (this.skuDetailsCache.containsKey(str2)) {
            SkuDetails skuDetails = this.skuDetailsCache.get(str2);
            GotSubscriptionDetails(true, skuDetails.getType().equals("subs"), skuDetails.getSku(), skuDetails.getTitle(), skuDetails.getDescription(), skuDetails.getPriceCurrencyCode(), skuDetails.getPrice());
            return;
        }
        new SkuDetailsResponseListener(this) {
            private /* synthetic */ Billing hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void onSkuDetailsResponse(BillingResult billingResult, List<SkuDetails> list) {
                BillingResult billingResult2 = billingResult;
                List<SkuDetails> list2 = list;
                if (list2 == null || list2.size() <= 0) {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.GotSubscriptionDetails(false, false, "", "", "", "", "");
                    return;
                }
                SkuDetails skuDetails = list2.get(0);
                Object put = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.skuDetailsCache.put(skuDetails.getSku(), skuDetails);
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.GotSubscriptionDetails(true, true, skuDetails.getSku(), skuDetails.getTitle(), skuDetails.getDescription(), skuDetails.getPriceCurrencyCode(), skuDetails.getPrice());
            }
        };
        this.billingClient.querySkuDetailsAsync(getSkuParams(str2, "subs"), skuDetailsResponseListener);
        int d = Log.d(LOG_TAG, "SubscriptionDetails: ".concat(String.valueOf(str2)));
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether In-app billing service is ready to purchase.")
    public boolean ReadyToPurchase() {
        return this.billingClient.isReady();
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Check Play Market services availability.")
    public boolean IsIabServiceAvailable() {
        return this.billingClient.isReady();
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Is subscription update supported.")
    public boolean IsSubscriptionUpdateSupported() {
        return isFeatureSupported("subscriptionsUpdate");
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Is one time purchase supported.")
    public boolean IsOneTimePurchaseSupported() {
        return true;
    }

    @DesignerProperty(defaultValue = "false", editorType = "boolean")
    @SimpleProperty
    public void TestMode(boolean z) {
        boolean z2 = z;
        this.testMode = z2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether it is testing mode enabled or not.")
    public boolean TestMode() {
        return this.testMode;
    }

    @SimpleEvent(description = "Error occurred event.")
    public void ErrorOccurred(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "ErrorOccurred", str);
    }

    @SimpleEvent(description = "After purchase event.")
    public void AfterPurchase(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AfterPurchase", str);
    }

    @SimpleEvent(description = "Got Product Details")
    public void GotProductDetails(boolean z, String str, String str2, String str3, String str4, String str5) {
        Object[] objArr = new Object[6];
        objArr[0] = Boolean.valueOf(z);
        Object[] objArr2 = objArr;
        objArr2[1] = str;
        Object[] objArr3 = objArr2;
        objArr3[2] = str2;
        Object[] objArr4 = objArr3;
        objArr4[3] = str3;
        Object[] objArr5 = objArr4;
        objArr5[4] = str4;
        Object[] objArr6 = objArr5;
        objArr6[5] = str5;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "GotProductDetails", objArr6);
    }

    @SimpleEvent(description = "Got Subscription Details")
    public void GotSubscriptionDetails(boolean z, boolean z2, String str, String str2, String str3, String str4, String str5) {
        Object[] objArr = new Object[7];
        objArr[0] = Boolean.valueOf(z);
        Object[] objArr2 = objArr;
        objArr2[1] = Boolean.valueOf(z2);
        Object[] objArr3 = objArr2;
        objArr3[2] = str;
        Object[] objArr4 = objArr3;
        objArr4[3] = str2;
        Object[] objArr5 = objArr4;
        objArr5[4] = str3;
        Object[] objArr6 = objArr5;
        objArr6[5] = str4;
        Object[] objArr7 = objArr6;
        objArr7[6] = str5;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "GotSubscriptionDetails", objArr7);
    }

    @SimpleEvent(description = "Got Owned Purchases")
    public void GotOwnedPurchases(List<String> list, List<String> list2) {
        Object[] objArr = new Object[2];
        objArr[0] = list;
        Object[] objArr2 = objArr;
        objArr2[1] = list2;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "GotOwnedPurchases", objArr2);
    }

    public void onBillingSetupFinished(BillingResult billingResult) {
        if (isSuccessful(billingResult)) {
            loadProducts();
        }
    }

    public void onBillingServiceDisconnected() {
    }

    public void onPurchasesUpdated(BillingResult billingResult, @Nullable List<Purchase> list) {
        List<Purchase> list2 = list;
        if (isSuccessful(billingResult) && list2 != null) {
            for (Purchase handlePurchase : list2) {
                handlePurchase(handlePurchase);
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean isSuccessful(BillingResult billingResult) {
        return billingResult.getResponseCode() == 0;
    }

    private boolean isSuccessful(Purchase.PurchasesResult purchasesResult) {
        return purchasesResult.getResponseCode() == 0;
    }

    private boolean isFeatureSupported(String str) {
        return isSuccessful(this.billingClient.isFeatureSupported(str));
    }

    private SkuDetailsParams getSkuParams(String str, String str2) {
        List list;
        String str3 = str2;
        new ArrayList();
        List list2 = list;
        List list3 = list2;
        boolean add = list2.add(str);
        SkuDetailsParams.Builder newBuilder = SkuDetailsParams.newBuilder();
        SkuDetailsParams.Builder builder = newBuilder;
        SkuDetailsParams.Builder type = newBuilder.setSkusList(list3).setType("inapp");
        return builder.build();
    }

    /* access modifiers changed from: private */
    public void startBillingFlow(SkuDetails skuDetails) {
        BillingResult launchBillingFlow = this.billingClient.launchBillingFlow(this.activity, BillingFlowParams.newBuilder().setSkuDetails(skuDetails).build());
    }

    private void consumePurchase(String str, String str2) {
        ConsumeResponseListener consumeResponseListener;
        ConsumeParams build = ConsumeParams.newBuilder().setPurchaseToken(str).setDeveloperPayload(str2).build();
        new ConsumeResponseListener(this) {
            private /* synthetic */ Billing hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void onConsumeResponse(BillingResult billingResult, String str) {
            }
        };
        this.billingClient.consumeAsync(build, consumeResponseListener);
    }

    private void handlePurchase(Purchase purchase) {
        AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener;
        Purchase purchase2 = purchase;
        if (purchase2.getPurchaseState() == 1 && !purchase2.isAcknowledged()) {
            AcknowledgePurchaseParams build = AcknowledgePurchaseParams.newBuilder().setPurchaseToken(purchase2.getPurchaseToken()).setDeveloperPayload(purchase2.getDeveloperPayload()).build();
            new AcknowledgePurchaseResponseListener(this) {
                private /* synthetic */ Billing hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                }

                public final void onAcknowledgePurchaseResponse(BillingResult billingResult) {
                }
            };
            this.billingClient.acknowledgePurchase(build, acknowledgePurchaseResponseListener);
            AfterPurchase(purchase2.getSku());
        }
    }

    private void loadProducts() {
        SkuDetailsResponseListener skuDetailsResponseListener;
        SkuDetailsResponseListener skuDetailsResponseListener2;
        new SkuDetailsResponseListener(this) {
            private /* synthetic */ Billing hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void onSkuDetailsResponse(BillingResult billingResult, List<SkuDetails> list) {
                List<SkuDetails> list2 = list;
                if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isSuccessful(billingResult) && list2 != null) {
                    for (SkuDetails next : list2) {
                        Object put = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.skuDetailsCache.put(next.getSku(), next);
                    }
                }
            }
        };
        this.billingClient.querySkuDetailsAsync(SkuDetailsParams.newBuilder().setType("inapp").build(), skuDetailsResponseListener);
        new SkuDetailsResponseListener(this) {
            private /* synthetic */ Billing hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

            {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
            }

            public final void onSkuDetailsResponse(BillingResult billingResult, List<SkuDetails> list) {
                List<SkuDetails> list2 = list;
                if (this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.isSuccessful(billingResult) && list2 != null) {
                    for (SkuDetails next : list2) {
                        Object put = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.skuDetailsCache.put(next.getSku(), next);
                    }
                }
            }
        };
        this.billingClient.querySkuDetailsAsync(SkuDetailsParams.newBuilder().setType("subs").build(), skuDetailsResponseListener2);
    }

    @Deprecated
    @SimpleFunction(description = "Do not use this block anymore. This block is deprecated and does nothing and will be removed in the future!")
    public void Initialize(String str, String str2) {
    }

    @Deprecated
    @SimpleFunction(description = "Do not use this block anymore. This block is deprecated and does nothing and will be removed in the future!")
    public void Consume(String str) {
    }

    @Deprecated
    @SimpleFunction(description = "Do not use this block anymore. This block is deprecated and does nothing and will be removed in the future!")
    public void UpdateSubscription(String str) {
    }

    @DesignerProperty(defaultValue = "true", editorType = "boolean")
    @Deprecated
    @SimpleProperty
    public void SuppressToast(boolean z) {
    }

    @Deprecated
    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Do not use this block anymore. This block is deprecated and does nothing and will be removed in the future!", userVisible = false)
    public boolean SuppressToast() {
        return true;
    }

    public void onInitialize() {
        Purchase.PurchasesResult queryPurchases = this.billingClient.queryPurchases("inapp");
        Purchase.PurchasesResult queryPurchases2 = this.billingClient.queryPurchases("subs");
    }

    public void onResume() {
        Purchase.PurchasesResult queryPurchases = this.billingClient.queryPurchases("inapp");
        Purchase.PurchasesResult queryPurchases2 = this.billingClient.queryPurchases("subs");
    }
}

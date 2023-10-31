package com.google.android.gms.common;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;

public final class AccountPicker {
    private AccountPicker() {
    }

    public static Intent newChooseAccountIntent(Account account, ArrayList<Account> arrayList, String[] strArr, boolean z, String str, String str2, String[] strArr2, Bundle bundle) {
        Intent intent;
        ArrayList<Account> arrayList2 = arrayList;
        ArrayList<Account> arrayList3 = arrayList2;
        ArrayList<Account> arrayList4 = arrayList2;
        ArrayList<Account> arrayList5 = arrayList4;
        new Intent();
        Intent intent2 = intent;
        Preconditions.checkArgument(true, "We only support hostedDomain filter for account chip styled account picker");
        Intent action = intent2.setAction("com.google.android.gms.common.account.CHOOSE_ACCOUNT");
        Intent intent3 = intent2.setPackage("com.google.android.gms");
        Intent putExtra = intent2.putExtra("allowableAccounts", arrayList4);
        Intent putExtra2 = intent2.putExtra("allowableAccountTypes", strArr);
        Intent putExtra3 = intent2.putExtra("addAccountOptions", bundle);
        Intent putExtra4 = intent2.putExtra("selectedAccount", account);
        Intent putExtra5 = intent2.putExtra("alwaysPromptForAccount", z);
        Intent putExtra6 = intent2.putExtra("descriptionTextOverride", str);
        Intent putExtra7 = intent2.putExtra("authTokenType", str2);
        Intent putExtra8 = intent2.putExtra("addAccountRequiredFeatures", strArr2);
        Intent putExtra9 = intent2.putExtra("setGmsCoreAccount", false);
        Intent putExtra10 = intent2.putExtra("overrideTheme", 0);
        Intent putExtra11 = intent2.putExtra("overrideCustomTheme", 0);
        Intent putExtra12 = intent2.putExtra("hostedDomainFilter", (String) null);
        return intent2;
    }
}

package com.google.appinventor.components.runtime;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.p003v7.app.ActionBar;
import android.support.p003v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.google.appinventor.components.runtime.util.AnimationUtil;
import com.google.appinventor.components.runtime.util.KodularHelper;

public class ListPickerActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    static int Zv9msgDgBftU4SA7C2ygCk7MYKz0i3cazgjcHvHHF7brwk6qR9wS1wUER4Y8ppMY;
    private static int backgroundColor;
    private static int fSI6lxX8qEfUYa0M3qSNEhqEY7tqyd89UewYfJ8WSYLJpTsAFdRvTVg7ORBsMzG8;
    private static int titleBarColor;
    private EditText hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private ListView f461hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    C0827a f462hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private String z819s2db3SwWOaVhKbPTp947sGRXlCsEqH9IfB6VLe6W07abBod2oRho8IvcelHj = "";

    public ListPickerActivity() {
    }

    public void onCreate(Bundle bundle) {
        LinearLayout linearLayout;
        ListView listView;
        C0827a aVar;
        EditText editText;
        TextWatcher textWatcher;
        super.onCreate(bundle);
        new LinearLayout(this);
        LinearLayout linearLayout2 = linearLayout;
        LinearLayout linearLayout3 = linearLayout2;
        linearLayout2.setOrientation(1);
        Intent intent = getIntent();
        Intent intent2 = intent;
        if (intent.hasExtra(ListPicker.nKfZQ1laYcwrzNEumUwCbmi2kaHgg3eE1c9SDtYVLTkiuRTWxcP8Pqqx3AbL5ow)) {
            this.z819s2db3SwWOaVhKbPTp947sGRXlCsEqH9IfB6VLe6W07abBod2oRho8IvcelHj = intent2.getStringExtra(ListPicker.nKfZQ1laYcwrzNEumUwCbmi2kaHgg3eE1c9SDtYVLTkiuRTWxcP8Pqqx3AbL5ow);
        }
        if (intent2.hasExtra(ListPicker.cOmDbOC978RubVhXjun4VkHg9OxeO5ZzRCTQEv8rZa8E7YdcVv7aSE4TjAXwfuwN)) {
            titleBarColor = intent2.getIntExtra(ListPicker.cOmDbOC978RubVhXjun4VkHg9OxeO5ZzRCTQEv8rZa8E7YdcVv7aSE4TjAXwfuwN, -16537101);
            ActionBar supportActionBar = getSupportActionBar();
            ColorDrawable colorDrawable = r9;
            ColorDrawable colorDrawable2 = new ColorDrawable(titleBarColor != 0 ? titleBarColor : -1);
            supportActionBar.setBackgroundDrawable(colorDrawable);
        }
        if (intent2.hasExtra(ListPicker.O8YFlD3Safgd5vkxHRoLznZm2if21MG0IxIn5jepRi6FBTeulibRFlvEXsnDANgS)) {
            fSI6lxX8qEfUYa0M3qSNEhqEY7tqyd89UewYfJ8WSYLJpTsAFdRvTVg7ORBsMzG8 = intent2.getIntExtra(ListPicker.O8YFlD3Safgd5vkxHRoLznZm2if21MG0IxIn5jepRi6FBTeulibRFlvEXsnDANgS, -14575886);
            if (Build.VERSION.SDK_INT >= 21) {
                KodularHelper.setStatusBarColor(this, fSI6lxX8qEfUYa0M3qSNEhqEY7tqyd89UewYfJ8WSYLJpTsAFdRvTVg7ORBsMzG8);
            }
        }
        if (intent2.hasExtra(ListPicker.seAn8f9TucJq5ZQrZ6xvA6wzyVfqYrHR0kVGH9g6Rg5gdXevmQRBQv2iJqrzi5Rz)) {
            String lowerCase = intent2.getStringExtra(ListPicker.seAn8f9TucJq5ZQrZ6xvA6wzyVfqYrHR0kVGH9g6Rg5gdXevmQRBQv2iJqrzi5Rz).toLowerCase();
            String str = lowerCase;
            if (lowerCase.equals("portrait")) {
                setRequestedOrientation(1);
            } else if (str.equals("landscape")) {
                setRequestedOrientation(0);
            }
        }
        if (intent2.hasExtra(ListPicker.YgDmThmc2Ht6J8LKfKFuGtMLp2AoFjdDlIONA2izriJdICsKCPKMX3dYEj8K1z4k)) {
            setTitle(intent2.getStringExtra(ListPicker.YgDmThmc2Ht6J8LKfKFuGtMLp2AoFjdDlIONA2izriJdICsKCPKMX3dYEj8K1z4k));
        }
        if (intent2.hasExtra(ListPicker.OFXnSk7pjyu5TDlQcCs0Ee2Ss8ceD26gQ4XJfzIMtdlcKhGXQ2j7Mh9NsuvjNyC)) {
            if (intent2.getBooleanExtra(ListPicker.OFXnSk7pjyu5TDlQcCs0Ee2Ss8ceD26gQ4XJfzIMtdlcKhGXQ2j7Mh9NsuvjNyC, true)) {
                getSupportActionBar().show();
            } else {
                getSupportActionBar().hide();
            }
        }
        if (intent2.hasExtra(ListPicker.gKFqoeV0mIepwKqPzQqyF42NDV4lXNBYlbBqvrWypn3hvG8Ace2UniGxwzdDn1SZ)) {
            if (intent2.getBooleanExtra(ListPicker.gKFqoeV0mIepwKqPzQqyF42NDV4lXNBYlbBqvrWypn3hvG8Ace2UniGxwzdDn1SZ, true)) {
                getWindow().addFlags(2048);
                getWindow().clearFlags(1024);
            } else {
                getWindow().addFlags(1024);
                getWindow().clearFlags(2048);
            }
        }
        if (intent2.hasExtra(ListPicker.x3f9w7ebWg4JdbY2pEu0Z0lXjvABueY447WcywG8LgLVE2M0xoLkgBxoCJuK6Oc)) {
            String[] stringArrayExtra = getIntent().getStringArrayExtra(ListPicker.x3f9w7ebWg4JdbY2pEu0Z0lXjvABueY447WcywG8LgLVE2M0xoLkgBxoCJuK6Oc);
            new ListView(this);
            this.f461hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = listView;
            this.f461hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setOnItemClickListener(this);
            this.f461hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setScrollingCacheEnabled(false);
            Zv9msgDgBftU4SA7C2ygCk7MYKz0i3cazgjcHvHHF7brwk6qR9wS1wUER4Y8ppMY = intent2.getIntExtra(ListPicker.q2q4oDuUajVwr2T7b6DILrrBhrCqmElgSd3ODKsAFi8uEX2COWatdRT7gov0FlS5, -1);
            backgroundColor = intent2.getIntExtra(ListPicker.LVnP8NaPYXRgwZHgDK7PHMVEgcKwsNvZv2AHicCDg6yGIfguFikZkwwgr0dhWzJE, -16777216);
            linearLayout3.setBackgroundColor(backgroundColor);
            new C0827a(this, stringArrayExtra);
            this.f462hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = aVar;
            this.f461hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setAdapter(this.f462hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
            String stringExtra = intent2.getStringExtra(ListPicker.iA1fsPSZHTCVXA414XY2edBmEFVpo23ZLLJ3ntPGDoZ3Lc1O8L7tucf7fjSxdGWm);
            new EditText(this);
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = editText;
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setSingleLine(true);
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setWidth(-2);
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setPadding(10, 10, 10, 10);
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setHint("Search list...");
            if (stringExtra == null || !stringExtra.equalsIgnoreCase("true")) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setVisibility(8);
            }
            new TextWatcher(this) {
                private /* synthetic */ ListPickerActivity hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

                {
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r5;
                }

                public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    int i4 = i;
                    int i5 = i2;
                    int i6 = i3;
                    this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.f462hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getFilter().filter(charSequence);
                }

                public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                public final void afterTextChanged(Editable editable) {
                }
            };
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.addTextChangedListener(textWatcher);
        } else {
            setResult(0);
            finish();
            AnimationUtil.ApplyCloseScreenAnimation(this, this.z819s2db3SwWOaVhKbPTp947sGRXlCsEqH9IfB6VLe6W07abBod2oRho8IvcelHj);
        }
        linearLayout3.addView(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        linearLayout3.addView(this.f461hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
        setContentView((View) linearLayout3);
        linearLayout3.requestLayout();
        boolean hideSoftInputFromWindow = ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        getWindow().setSoftInputMode(3);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v0, resolved type: android.widget.AdapterView<?>} */
    /* JADX WARNING: type inference failed for: r6v1, types: [android.widget.Adapter] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onItemClick(android.widget.AdapterView<?> r13, android.view.View r14, int r15, long r16) {
        /*
            r12 = this;
            r0 = r12
            r1 = r13
            r2 = r14
            r3 = r15
            r4 = r16
            r6 = r1
            android.widget.Adapter r6 = r6.getAdapter()
            r7 = r3
            java.lang.Object r6 = r6.getItem(r7)
            java.lang.String r6 = (java.lang.String) r6
            r1 = r6
            android.content.Intent r6 = new android.content.Intent
            r10 = r6
            r6 = r10
            r7 = r10
            r7.<init>()
            r10 = r6
            r6 = r10
            r7 = r10
            r2 = r7
            java.lang.String r7 = com.google.appinventor.components.runtime.ListPicker.UrHg3UsNYt3X05ajxF1PWwC50ZBDolbLcJ2ocoWwvC2Xge7OsQI3Tkbaki1SJsz5
            r8 = r1
            android.content.Intent r6 = r6.putExtra(r7, r8)
            r6 = r2
            java.lang.String r7 = com.google.appinventor.components.runtime.ListPicker.AVN1jEMUjULIMlY3UvkBgLtaEKU1Kh7f4RsRo8tJ6i96580YKtIBKDpaBPwG4gsl
            r8 = r3
            r9 = 1
            int r8 = r8 + 1
            android.content.Intent r6 = r6.putExtra(r7, r8)
            r6 = r0
            r7 = r1
            r6.z819s2db3SwWOaVhKbPTp947sGRXlCsEqH9IfB6VLe6W07abBod2oRho8IvcelHj = r7
            r6 = r0
            r7 = -1
            r8 = r2
            r6.setResult(r7, r8)
            r6 = r0
            r6.finish()
            r6 = r0
            r10 = r6
            r6 = r10
            r7 = r10
            java.lang.String r7 = r7.z819s2db3SwWOaVhKbPTp947sGRXlCsEqH9IfB6VLe6W07abBod2oRho8IvcelHj
            com.google.appinventor.components.runtime.util.AnimationUtil.ApplyCloseScreenAnimation(r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.ListPickerActivity.onItemClick(android.widget.AdapterView, android.view.View, int, long):void");
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        int i2 = i;
        KeyEvent keyEvent2 = keyEvent;
        if (i2 != 4) {
            return super.onKeyDown(i2, keyEvent2);
        }
        boolean onKeyDown = super.onKeyDown(i2, keyEvent2);
        AnimationUtil.ApplyCloseScreenAnimation(this, this.z819s2db3SwWOaVhKbPTp947sGRXlCsEqH9IfB6VLe6W07abBod2oRho8IvcelHj);
        return onKeyDown;
    }

    /* renamed from: com.google.appinventor.components.runtime.ListPickerActivity$a */
    static class C0827a extends ArrayAdapter<String> {
        private final Context hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public C0827a(android.content.Context r8, java.lang.String[] r9) {
            /*
                r7 = this;
                r0 = r7
                r1 = r8
                r2 = r9
                r3 = r0
                r4 = r1
                r5 = 17367040(0x1090000, float:2.5162926E-38)
                r6 = r2
                r3.<init>(r4, r5, r6)
                r3 = r0
                r4 = r1
                r3.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r4
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.ListPickerActivity.C0827a.<init>(android.content.Context, java.lang.String[]):void");
        }

        public final long getItemId(int i) {
            return (long) ((String) getItem(i)).hashCode();
        }

        public final View getView(int i, View view, ViewGroup viewGroup) {
            int i2 = i;
            ViewGroup viewGroup2 = viewGroup;
            TextView textView = (TextView) view;
            TextView textView2 = textView;
            if (textView == null) {
                textView2 = (TextView) LayoutInflater.from(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).inflate(17367043, viewGroup2, false);
            }
            textView2.setText((CharSequence) getItem(i2));
            textView2.setTextColor(ListPickerActivity.Zv9msgDgBftU4SA7C2ygCk7MYKz0i3cazgjcHvHHF7brwk6qR9wS1wUER4Y8ppMY);
            return textView2;
        }
    }
}

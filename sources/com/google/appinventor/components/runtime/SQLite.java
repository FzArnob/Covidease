package com.google.appinventor.components.runtime;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.YailList;
import java.util.ArrayList;
import java.util.List;

@SimpleObject
@DesignerComponent(category = ComponentCategory.STORAGE, description = "Tool developed by Carlos Pedroza to access the application's SQlite database.", helpUrl = "https://docs.kodular.io/components/storage/sqlite/", iconName = "images/SQLite.png", nonVisible = true, version = 2)
public class SQLite extends AndroidNonvisibleComponent implements Component {
    /* access modifiers changed from: private */
    public static String iz2AUXs4pv4EMA73duiR1R3OfWItF7gDqk3oMKKRO3MIyuqvZdoefifHvTvEAhn = "DATABASE.sqlite";
    private String[] F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho;
    private boolean G7Lxwqvaa0zcPMEYmgjECqcap18lY2TYRMrZOi1cD4z0oU809LnjVuckWEr78wl;
    private String[] LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn;
    private boolean TcZoKXUijRhvOFm1gZ0ppEfXWxECqlUNNvncSPIfT3ZrTDcozo05OAb21mkMXciT;
    private ComponentContainer container;
    private Context context;
    private String gUtdvgLwReW6eQihrPDf1Gr7OXNm8PrZovE9AMcvRbNvJBLZZT49Ja1NneDzYHk;
    private SQLiteDatabase hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    private boolean pixa37in9tdgjNQb2jpfjxTaBGtatW1GnEvNM302mGQljyvLBwDosTGoRPcRGYok;
    private int q8iygSjgpTZ52A5K8GXhnvUs2CX4iXd4uIVtwvououVquIzlzfVOfWIR3CjSHG4 = 0;
    private int symWhrqAyHWXMObHLoQEIMlJvqdZFvcp7UyC2VmDxP3CgSs0pkdkxz6qiaDBzrEK = 1;

    static /* synthetic */ int hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(SQLite sQLite, int i) {
        int i2 = i;
        int i3 = i2;
        sQLite.q8iygSjgpTZ52A5K8GXhnvUs2CX4iXd4uIVtwvououVquIzlzfVOfWIR3CjSHG4 = i3;
        return i2;
    }

    /* renamed from: com.google.appinventor.components.runtime.SQLite$b */
    class C1011b extends AsyncTask<Void, Void, Void> {
        private boolean Qo6hTp4u7pOnq5WtKfNdQM86FuI5n4weAQCyrgAeimDlOrP2dP7U8KFwjrDIHyb;
        private /* synthetic */ SQLite hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        private C1011b(SQLite sQLite) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = sQLite;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ C1011b(SQLite sQLite, byte b) {
            this(sQLite);
            byte b2 = b;
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            Object[] objArr2 = objArr;
            return B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T();
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            Object obj2 = obj;
            if (!SQLite.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME)) {
                Toast.makeText(SQLite.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME), "Done", 0).show();
            }
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.AfterExecution(this.Qo6hTp4u7pOnq5WtKfNdQM86FuI5n4weAQCyrgAeimDlOrP2dP7U8KFwjrDIHyb);
            if (!this.Qo6hTp4u7pOnq5WtKfNdQM86FuI5n4weAQCyrgAeimDlOrP2dP7U8KFwjrDIHyb) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.ErrorOccurred("Invalid SQL Statement");
            }
        }

        private Void B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T() {
            C1010a aVar;
            new C1010a(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
            SQLiteDatabase writableDatabase = aVar.getWritableDatabase();
            SQLiteDatabase sQLiteDatabase = writableDatabase;
            writableDatabase.beginTransaction();
            try {
                if (SQLite.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME)) {
                    sQLiteDatabase.execSQL(SQLite.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME));
                } else {
                    for (int i = 0; i < SQLite.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME).length; i++) {
                        sQLiteDatabase.execSQL(SQLite.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME)[i]);
                    }
                }
                this.Qo6hTp4u7pOnq5WtKfNdQM86FuI5n4weAQCyrgAeimDlOrP2dP7U8KFwjrDIHyb = true;
                sQLiteDatabase.setTransactionSuccessful();
                sQLiteDatabase.endTransaction();
            } catch (SQLException e) {
                this.Qo6hTp4u7pOnq5WtKfNdQM86FuI5n4weAQCyrgAeimDlOrP2dP7U8KFwjrDIHyb = false;
                sQLiteDatabase.endTransaction();
            } catch (Throwable th) {
                Throwable th2 = th;
                sQLiteDatabase.endTransaction();
                throw th2;
            }
            sQLiteDatabase.close();
            return null;
        }
    }

    /* renamed from: com.google.appinventor.components.runtime.SQLite$c */
    class C1012c extends AsyncTask<Void, Void, Void> {
        private boolean Qo6hTp4u7pOnq5WtKfNdQM86FuI5n4weAQCyrgAeimDlOrP2dP7U8KFwjrDIHyb;
        private /* synthetic */ SQLite hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
        private int nrk4qDbuonwjAUi7HPDytbtabfk6KLkf1OM4Z1o9O6dUiFPBfNVpTU1RRHKJKimM;
        private List zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5;

        private C1012c(SQLite sQLite) {
            this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = sQLite;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ C1012c(SQLite sQLite, byte b) {
            this(sQLite);
            byte b2 = b;
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            Object[] objArr2 = objArr;
            return B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T();
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            Object obj2 = obj;
            if (!SQLite.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME)) {
                Toast.makeText(SQLite.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME), "Done", 0).show();
            }
            if (this.Qo6hTp4u7pOnq5WtKfNdQM86FuI5n4weAQCyrgAeimDlOrP2dP7U8KFwjrDIHyb) {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.AfterQuery(this.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5, this.nrk4qDbuonwjAUi7HPDytbtabfk6KLkf1OM4Z1o9O6dUiFPBfNVpTU1RRHKJKimM);
            } else {
                this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.ErrorOccurred("Invalid SQL Statement");
            }
        }

        private Void B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T() {
            C1010a aVar;
            new C1010a(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME);
            SQLiteDatabase readableDatabase = aVar.getReadableDatabase();
            SQLiteDatabase sQLiteDatabase = readableDatabase;
            readableDatabase.beginTransaction();
            try {
                Cursor rawQuery = sQLiteDatabase.rawQuery(SQLite.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME), SQLite.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME));
                this.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5 = this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(rawQuery);
                this.nrk4qDbuonwjAUi7HPDytbtabfk6KLkf1OM4Z1o9O6dUiFPBfNVpTU1RRHKJKimM = this.zWdiPvy9THbs14kS37O202JH95jn5GzRAXQdLdM0hhw5e6hZSAshBMBb0bJkJXW5.size();
                this.Qo6hTp4u7pOnq5WtKfNdQM86FuI5n4weAQCyrgAeimDlOrP2dP7U8KFwjrDIHyb = true;
                sQLiteDatabase.setTransactionSuccessful();
                sQLiteDatabase.endTransaction();
            } catch (SQLException e) {
                this.Qo6hTp4u7pOnq5WtKfNdQM86FuI5n4weAQCyrgAeimDlOrP2dP7U8KFwjrDIHyb = false;
                sQLiteDatabase.endTransaction();
            } catch (Throwable th) {
                Throwable th2 = th;
                sQLiteDatabase.endTransaction();
                throw th2;
            }
            sQLiteDatabase.close();
            return null;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v0, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v40, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v48, resolved type: java.util.ArrayList} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(android.database.Cursor r11) {
        /*
            r10 = this;
            r0 = r10
            r1 = r11
            java.util.ArrayList r6 = new java.util.ArrayList
            r9 = r6
            r6 = r9
            r7 = r9
            r7.<init>()
            r3 = r6
            r6 = r1
            java.lang.String[] r6 = r6.getColumnNames()     // Catch:{ SQLException -> 0x0083 }
            r9 = r6
            r6 = r9
            r7 = r9
            r2 = r7
            int r6 = r6.length     // Catch:{ SQLException -> 0x0083 }
            r7 = 1
            if (r6 <= r7) goto L_0x008e
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ SQLException -> 0x0083 }
            r9 = r6
            r6 = r9
            r7 = r9
            r7.<init>()     // Catch:{ SQLException -> 0x0083 }
            r3 = r6
            r6 = r0
            boolean r6 = r6.pixa37in9tdgjNQb2jpfjxTaBGtatW1GnEvNM302mGQljyvLBwDosTGoRPcRGYok     // Catch:{ SQLException -> 0x0083 }
            if (r6 == 0) goto L_0x0048
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ SQLException -> 0x0083 }
            r9 = r6
            r6 = r9
            r7 = r9
            r7.<init>()     // Catch:{ SQLException -> 0x0083 }
            r4 = r6
            r6 = 0
            r5 = r6
        L_0x0031:
            r6 = r5
            r7 = r2
            int r7 = r7.length     // Catch:{ SQLException -> 0x0083 }
            if (r6 >= r7) goto L_0x0042
            r6 = r4
            r7 = r2
            r8 = r5
            r7 = r7[r8]     // Catch:{ SQLException -> 0x0083 }
            boolean r6 = r6.add(r7)     // Catch:{ SQLException -> 0x0083 }
            int r5 = r5 + 1
            goto L_0x0031
        L_0x0042:
            r6 = r3
            r7 = r4
            boolean r6 = r6.add(r7)     // Catch:{ SQLException -> 0x0083 }
        L_0x0048:
            r6 = r1
            boolean r6 = r6.moveToNext()     // Catch:{ all -> 0x007b }
            if (r6 == 0) goto L_0x0074
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ all -> 0x007b }
            r9 = r6
            r6 = r9
            r7 = r9
            r7.<init>()     // Catch:{ all -> 0x007b }
            r4 = r6
            r6 = 0
            r5 = r6
        L_0x005a:
            r6 = r5
            r7 = r2
            int r7 = r7.length     // Catch:{ all -> 0x007b }
            if (r6 >= r7) goto L_0x006d
            r6 = r4
            r7 = r1
            r8 = r5
            java.lang.String r7 = r7.getString(r8)     // Catch:{ all -> 0x007b }
            boolean r6 = r6.add(r7)     // Catch:{ all -> 0x007b }
            int r5 = r5 + 1
            goto L_0x005a
        L_0x006d:
            r6 = r3
            r7 = r4
            boolean r6 = r6.add(r7)     // Catch:{ all -> 0x007b }
            goto L_0x0048
        L_0x0074:
            r6 = r1
            r6.close()     // Catch:{ SQLException -> 0x0083 }
        L_0x0078:
            r6 = r3
            r0 = r6
        L_0x007a:
            return r0
        L_0x007b:
            r6 = move-exception
            r2 = r6
            r6 = r1
            r6.close()     // Catch:{ SQLException -> 0x0083 }
            r6 = r2
            throw r6     // Catch:{ SQLException -> 0x0083 }
        L_0x0083:
            r6 = move-exception
            r6 = r0
            java.lang.String r7 = "Error during managing the cursor"
            r6.ErrorOccurred(r7)
            r6 = r3
            r0 = r6
            goto L_0x007a
        L_0x008e:
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ SQLException -> 0x0083 }
            r9 = r6
            r6 = r9
            r7 = r9
            r7.<init>()     // Catch:{ SQLException -> 0x0083 }
            r3 = r6
            r6 = r0
            boolean r6 = r6.pixa37in9tdgjNQb2jpfjxTaBGtatW1GnEvNM302mGQljyvLBwDosTGoRPcRGYok     // Catch:{ SQLException -> 0x0083 }
            if (r6 == 0) goto L_0x00a5
            r6 = r3
            r7 = r2
            r8 = 0
            r7 = r7[r8]     // Catch:{ SQLException -> 0x0083 }
            boolean r6 = r6.add(r7)     // Catch:{ SQLException -> 0x0083 }
        L_0x00a5:
            r6 = r1
            boolean r6 = r6.moveToNext()     // Catch:{ all -> 0x00bd }
            if (r6 == 0) goto L_0x00b8
            r6 = r3
            r7 = r1
            r8 = 0
            java.lang.String r7 = r7.getString(r8)     // Catch:{ all -> 0x00bd }
            boolean r6 = r6.add(r7)     // Catch:{ all -> 0x00bd }
            goto L_0x00a5
        L_0x00b8:
            r6 = r1
            r6.close()     // Catch:{ SQLException -> 0x0083 }
            goto L_0x0078
        L_0x00bd:
            r6 = move-exception
            r2 = r6
            r6 = r1
            r6.close()     // Catch:{ SQLException -> 0x0083 }
            r6 = r2
            throw r6     // Catch:{ SQLException -> 0x0083 }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.SQLite.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(android.database.Cursor):java.util.List");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SQLite(com.google.appinventor.components.runtime.ComponentContainer r5) {
        /*
            r4 = this;
            r0 = r4
            r1 = r5
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            r3 = 1
            r2.symWhrqAyHWXMObHLoQEIMlJvqdZFvcp7UyC2VmDxP3CgSs0pkdkxz6qiaDBzrEK = r3
            r2 = r0
            r3 = 0
            r2.q8iygSjgpTZ52A5K8GXhnvUs2CX4iXd4uIVtwvououVquIzlzfVOfWIR3CjSHG4 = r3
            r2 = r0
            r3 = r1
            r2.container = r3
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.context = r3
            java.lang.String r2 = "SQLite"
            java.lang.String r3 = "SQLite created"
            int r2 = android.util.Log.d(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.SQLite.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns whether the header row should be returned in the result of a Select statement.")
    public boolean ReturnHeader() {
        return this.pixa37in9tdgjNQb2jpfjxTaBGtatW1GnEvNM302mGQljyvLBwDosTGoRPcRGYok;
    }

    @DesignerProperty(defaultValue = "false", editorType = "boolean")
    @SimpleProperty
    public void ReturnHeader(boolean z) {
        boolean z2 = z;
        this.pixa37in9tdgjNQb2jpfjxTaBGtatW1GnEvNM302mGQljyvLBwDosTGoRPcRGYok = z2;
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns whether Success Toast should be suppressed.")
    public boolean SuppressToast() {
        return this.TcZoKXUijRhvOFm1gZ0ppEfXWxECqlUNNvncSPIfT3ZrTDcozo05OAb21mkMXciT;
    }

    @DesignerProperty(defaultValue = "false", editorType = "boolean")
    @SimpleProperty
    public void SuppressToast(boolean z) {
        boolean z2 = z;
        this.TcZoKXUijRhvOFm1gZ0ppEfXWxECqlUNNvncSPIfT3ZrTDcozo05OAb21mkMXciT = z2;
    }

    @SimpleFunction(description = "Returns the path to the database")
    public String GetPath() {
        C1010a aVar;
        new C1010a(this);
        this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = aVar.getReadableDatabase();
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getPath();
    }

    @SimpleFunction(description = "Clears the database to version 1. Use only while developing, this shouldn't be use on production.")
    public void ClearDatabase() {
        boolean deleteDatabase = this.context.deleteDatabase(iz2AUXs4pv4EMA73duiR1R3OfWItF7gDqk3oMKKRO3MIyuqvZdoefifHvTvEAhn);
        Toast.makeText(this.context, "Database cleared", 0).show();
    }

    @SimpleFunction(description = "Execute a Single SQL Statement asynchronously and returns whether the transaction was successful in the AfterExecution Event Handler. Use it when returned data isn't needed. Parameter: 1) String sql.")
    public void SingleSQL(String str) {
        C1011b bVar;
        try {
            this.gUtdvgLwReW6eQihrPDf1Gr7OXNm8PrZovE9AMcvRbNvJBLZZT49Ja1NneDzYHk = str;
            this.G7Lxwqvaa0zcPMEYmgjECqcap18lY2TYRMrZOi1cD4z0oU809LnjVuckWEr78wl = true;
            new C1011b(this, (byte) 0);
            AsyncTask execute = bVar.execute(new Void[0]);
        } catch (Exception e) {
            ErrorOccurred("Something went wrong SingleSQL");
        }
    }

    @SimpleFunction(description = "Execute Multiple SQL Statement asynchronously and returns whether the transaction was successful in the AfterExecution Event Handler. Use it when returned data isn't needed. Parameter: 1 ) List of SQL.")
    public void MultipleSQL(YailList yailList) {
        C1011b bVar;
        try {
            this.F0SK4gPRNmAI2jCyU6DpJpRxlfo5Y8j9ZujjeLuDeDzReJBeSNN2RZtCnkXv1dho = yailList.toStringArray();
            this.G7Lxwqvaa0zcPMEYmgjECqcap18lY2TYRMrZOi1cD4z0oU809LnjVuckWEr78wl = false;
            new C1011b(this, (byte) 0);
            AsyncTask execute = bVar.execute(new Void[0]);
        } catch (Exception e) {
            ErrorOccurred("Something went wrong MultipleSQL");
        }
    }

    @SimpleEvent
    public void AfterExecution(boolean z) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AfterExecution", Boolean.valueOf(z));
    }

    @SimpleFunction(description = "Executes the provided rawQuery Statement asynchronously. Returns a YailList with the selected data and number of records in the AfterQuery Event. Parameter: 1) String sql. 2) YailList selectionArgs: List with the arguments that will replace '?' in where clause in the query, to prevent SQL injections")
    public void RawQuery(String str, YailList yailList) {
        C1012c cVar;
        String str2 = str;
        try {
            this.LYVRHQlR5uMq9RmVQLgPQwQp4HVKuBDt7Jnpu0jTztYClgnk53NSpkUmjjPPbYn = yailList.toStringArray();
            this.gUtdvgLwReW6eQihrPDf1Gr7OXNm8PrZovE9AMcvRbNvJBLZZT49Ja1NneDzYHk = str2;
            new C1012c(this, (byte) 0);
            AsyncTask execute = cVar.execute(new Void[0]);
        } catch (Exception e) {
            ErrorOccurred("Something went wrong RawQuery");
        }
    }

    @SimpleEvent
    public void AfterQuery(List list, int i) {
        Object[] objArr = new Object[2];
        objArr[0] = list;
        Object[] objArr2 = objArr;
        objArr2[1] = Integer.valueOf(i);
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "AfterQuery", objArr2);
    }

    @SimpleFunction(description = "Executes pre-compiled DELETE statement with specified parameters. Parameters: 1) String table - Name of the table. 2) String whereClause - Optional WHERE clause to apply when deleting (Example: 'ID = ?'), pasing an empty a string will delete all rows. 3) List whereArgs - List with arguments for the WHERE clause. These arguments will be replaced by '?' in the whereClause. Returns the number of rows affected if a whereClause is passed in, 0 otherwise.")
    public int Delete(String str, String str2, YailList yailList) {
        C1010a aVar;
        String str3 = str;
        String str4 = str2;
        YailList yailList2 = yailList;
        try {
            new C1010a(this);
            SQLiteDatabase writableDatabase = aVar.getWritableDatabase();
            int delete = writableDatabase.delete(str3, str4.equals("") ? null : str4, yailList2.toStringArray());
            writableDatabase.close();
            return delete;
        } catch (SQLException e) {
            ErrorOccurred("Something went wrong deleting");
            return -1;
        }
    }

    @SimpleFunction(description = "Executes pre-compiled INSERT statement with specified parameters. Parameters: 1) String table - Name of the table. 2) YailList columns - List with the columns that will contain the data to be inserted in the database. 3) YailList values - List with the data to be inserted in the database. Returns the row ID of the newly inserted row, or -1 if an error occurred.")
    public long Insert(String str, YailList yailList, YailList yailList2) {
        C1010a aVar;
        ContentValues contentValues;
        String str2 = str;
        YailList yailList3 = yailList;
        YailList yailList4 = yailList2;
        try {
            new C1010a(this);
            SQLiteDatabase writableDatabase = aVar.getWritableDatabase();
            String[] stringArray = yailList3.toStringArray();
            String[] stringArray2 = yailList4.toStringArray();
            new ContentValues();
            ContentValues contentValues2 = contentValues;
            for (int i = 0; i < stringArray.length; i++) {
                contentValues2.put(stringArray[i], stringArray2[i]);
            }
            long insert = writableDatabase.insert(str2, (String) null, contentValues2);
            writableDatabase.close();
            return insert;
        } catch (SQLException e) {
            ErrorOccurred("Something went wrong inserting");
            return -1;
        }
    }

    @SimpleFunction(description = "Executes pre-compiled REPLACE OR INSERT INTO statement with specified parameters. Parameters: 1) String table - Name of the table. 2) YailList columns - List with the columns that will contain the data to be replaced in the database. 3) YailList values - List with the data to be replaced in the database. Returns the row ID of the newly replaced row, or -1 if an error occurred.")
    public long Replace(String str, YailList yailList, YailList yailList2) {
        C1010a aVar;
        ContentValues contentValues;
        String str2 = str;
        YailList yailList3 = yailList;
        YailList yailList4 = yailList2;
        try {
            new C1010a(this);
            SQLiteDatabase writableDatabase = aVar.getWritableDatabase();
            String[] stringArray = yailList3.toStringArray();
            String[] stringArray2 = yailList4.toStringArray();
            new ContentValues();
            ContentValues contentValues2 = contentValues;
            for (int i = 0; i < stringArray.length; i++) {
                contentValues2.put(stringArray[i], stringArray2[i]);
            }
            long replace = writableDatabase.replace(str2, (String) null, contentValues2);
            writableDatabase.close();
            return replace;
        } catch (SQLException e) {
            ErrorOccurred("Something went wrong replacing");
            return -1;
        }
    }

    @SimpleFunction(description = "Executes pre-compiled UPDATE statement with specified parameters. Parameters: 1) String table - Name of the table. 2) YailList columns - List with the columns that will contain the data to be inserted in the database. 3) YailList values - List with the data to be inserted in the database. 4) String whereClause - optional WHERE clause to apply when updating, leave an empty string to update all rows. Include ?s, which will be updated by the values from whereArgs. 5) YailList whereArgs - List with the columns that will contain the data to be updated in the database. Returns the row ID of the newly inserted row, or -1 if an error occurred.")
    public int Update(String str, YailList yailList, YailList yailList2, String str2, YailList yailList3) {
        C1010a aVar;
        ContentValues contentValues;
        String str3 = str;
        YailList yailList4 = yailList;
        YailList yailList5 = yailList2;
        String str4 = str2;
        YailList yailList6 = yailList3;
        try {
            new C1010a(this);
            SQLiteDatabase writableDatabase = aVar.getWritableDatabase();
            String[] stringArray = yailList4.toStringArray();
            String[] stringArray2 = yailList5.toStringArray();
            new ContentValues();
            ContentValues contentValues2 = contentValues;
            for (int i = 0; i < stringArray.length; i++) {
                contentValues2.put(stringArray[i], stringArray2[i]);
            }
            int update = writableDatabase.update(str3, contentValues2, str4.equals("") ? null : str4, yailList6.toStringArray());
            writableDatabase.close();
            return update;
        } catch (SQLException e) {
            ErrorOccurred("Something went wrong updating");
            return -1;
        }
    }

    @SimpleFunction(description = "Executes pre-compiled QUERY statement with specified parameters. Parameters: 1) String table: Name of the table. 2) YailList columns: List of which columns to return, passing an empty list will return all columns. 3) String selection: Filter declaring which rows to return, formatted as an SQL WHERE clause, passing an empty string will return all rows. 4) YailList selectionArgs: List with the arguments that will replace onto '?' in the selection filter. 5) String groupBy: A filter declaring how to group rows, formatted as an SQL GROUP BY clause (excluding the GROUP BY itself), passing an empty string will cause the row to not be grouped. 6) String having: A filter declare which row groups to include if row grouping is being used, passing an empty string will cause all row groups to be included. 7) String orderBy: How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself), passing an empty string will use the default sort order (unordered). 8) String limit: Limits the number of rows returned by the query, formatted as LIMIT clause, passing an empty string denotes no LIMIT clause. The result query is available in the AfterQuery event handler")
    public void Query(String str, YailList yailList, String str2, YailList yailList2, String str3, String str4, String str5, String str6) {
        C1010a aVar;
        List list;
        boolean z;
        String str7 = str;
        String str8 = str2;
        String str9 = str3;
        String str10 = str4;
        String str11 = str5;
        String str12 = str6;
        new C1010a(this);
        SQLiteDatabase readableDatabase = aVar.getReadableDatabase();
        String[] stringArray = yailList.toStringArray();
        String[] stringArray2 = yailList2.toStringArray();
        String str13 = str8.equals("") ? null : str8;
        String str14 = str9.equals("") ? null : str9;
        String str15 = str10.equals("") ? null : str10;
        String str16 = str11.equals("") ? null : str11;
        String str17 = str12.equals("") ? null : str12;
        new ArrayList();
        List list2 = list;
        int i = 0;
        readableDatabase.beginTransaction();
        try {
            List hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(readableDatabase.query(str7, stringArray, str13, stringArray2, str14, str15, str16, str17));
            list2 = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2;
            i = hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2.size();
            z = true;
            readableDatabase.setTransactionSuccessful();
            readableDatabase.endTransaction();
        } catch (SQLException e) {
            z = false;
            readableDatabase.endTransaction();
        } catch (Throwable th) {
            Throwable th2 = th;
            readableDatabase.endTransaction();
            throw th2;
        }
        readableDatabase.close();
        if (!this.TcZoKXUijRhvOFm1gZ0ppEfXWxECqlUNNvncSPIfT3ZrTDcozo05OAb21mkMXciT) {
            Toast.makeText(this.context, "Done", 0).show();
        }
        if (z) {
            AfterQuery(list2, i);
        } else {
            ErrorOccurred("Something went wrong querying");
        }
    }

    @SimpleEvent
    public void ErrorOccurred(String str) {
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "ErrorOccurred", str);
    }

    @SimpleFunction(description = "Used to run any valid SQLite query and return results in same block.", userVisible = true)
    public YailList RunQuery(String str) {
        C1010a aVar;
        YailList yailList;
        new C1010a(this);
        C1010a aVar2 = aVar;
        String trim = str.trim();
        String str2 = trim;
        if (trim.toLowerCase().startsWith("select") || str2.toLowerCase().startsWith("explain")) {
            yailList = aVar2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(str2);
        } else {
            yailList = aVar2.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(str2);
        }
        return yailList;
    }

    @SimpleFunction(description = "Used to drop / delete table from database. Please note that this event will DELETE any data you may have on the table and will then delete table from the database. After this operation is completed, it cannot be undone!", userVisible = true)
    public YailList DropTable(String str) {
        return RunQuery("drop table if exists ".concat(String.valueOf(str)));
    }

    @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Returns the number of rows affected after a raw SQL has been executed using SQL Query.")
    public int RowsAffected() {
        return this.q8iygSjgpTZ52A5K8GXhnvUs2CX4iXd4uIVtwvououVquIzlzfVOfWIR3CjSHG4;
    }

    @SimpleFunction(description = "Used to retrieve list of existing tables from the database", userVisible = true)
    public YailList DisplayTables() {
        C1010a aVar;
        new C1010a(this);
        return aVar.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME("SELECT tbl_name FROM sqlite_master WHERE type='table' and tbl_name != 'android_metadata'");
    }

    public static String toCsvRow(String[] strArr) {
        StringBuilder sb;
        String[] strArr2 = strArr;
        String str = ",";
        new StringBuilder();
        StringBuilder sb2 = sb;
        int length = strArr2.length;
        for (int i = 0; i < length; i++) {
            StringBuilder append = sb2.append(strArr2[i]).append(str);
        }
        String sb3 = sb2.toString();
        String str2 = sb3;
        if (sb3.endsWith(str)) {
            str2 = str2.substring(0, str2.length() - 1);
        }
        return str2;
    }

    /* renamed from: com.google.appinventor.components.runtime.SQLite$a */
    class C1010a extends SQLiteOpenHelper {
        private /* synthetic */ SQLite hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public C1010a(com.google.appinventor.components.runtime.SQLite r8) {
            /*
                r7 = this;
                r0 = r7
                r1 = r8
                r2 = r0
                r3 = r1
                r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
                r2 = r0
                r3 = r1
                android.content.Context r3 = com.google.appinventor.components.runtime.SQLite.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((com.google.appinventor.components.runtime.SQLite) r3)
                java.lang.String r4 = com.google.appinventor.components.runtime.SQLite.iz2AUXs4pv4EMA73duiR1R3OfWItF7gDqk3oMKKRO3MIyuqvZdoefifHvTvEAhn
                r5 = 0
                r6 = r1
                int r6 = com.google.appinventor.components.runtime.SQLite.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME((com.google.appinventor.components.runtime.SQLite) r6)
                r2.<init>(r3, r4, r5, r6)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.SQLite.C1010a.<init>(com.google.appinventor.components.runtime.SQLite):void");
        }

        public final void onCreate(SQLiteDatabase sQLiteDatabase) {
            SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
            if (!SQLite.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME)) {
                Toast.makeText(SQLite.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME), "Database created", 0).show();
            }
        }

        public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
            int i3 = i;
            int i4 = i2;
            if (!SQLite.B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME)) {
                Toast.makeText(SQLite.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME), "Database upgraded", 0).show();
            }
        }

        /* access modifiers changed from: private */
        public YailList hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(String str) {
            List list;
            StringBuilder sb;
            StringBuilder sb2;
            new ArrayList();
            List list2 = list;
            String str2 = ",";
            SQLiteDatabase readableDatabase = getReadableDatabase();
            try {
                Cursor rawQuery = readableDatabase.rawQuery(str, (String[]) null);
                Cursor cursor = rawQuery;
                if (rawQuery != null) {
                    if (cursor.moveToFirst()) {
                        boolean add = list2.add(SQLite.toCsvRow(cursor.getColumnNames()));
                        do {
                            new StringBuilder("");
                            StringBuilder sb3 = sb2;
                            for (int i = 0; i < cursor.getColumnCount(); i++) {
                                StringBuilder append = sb3.append(cursor.getString(i)).append(str2);
                            }
                            String sb4 = sb3.toString();
                            String str3 = sb4;
                            boolean add2 = list2.add(sb4.endsWith(str2) ? str3.substring(0, str3.length() - 1) : str3);
                        } while (cursor.moveToNext());
                    }
                    cursor.close();
                }
                hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(readableDatabase);
            } catch (Exception e) {
                new StringBuilder("Error executing query. Error was: ");
                int d = Log.d("SQLite", sb.append(e.getMessage()).toString());
            }
            readableDatabase.close();
            int releaseMemory = SQLiteDatabase.releaseMemory();
            return YailList.makeList(list2);
        }

        private void hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(SQLiteDatabase sQLiteDatabase) {
            int hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME2 = SQLite.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, 0);
            Cursor rawQuery = sQLiteDatabase.rawQuery("select total_changes()", (String[]) null);
            Cursor cursor = rawQuery;
            if (rawQuery != null) {
                if (cursor.moveToFirst()) {
                    int hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME3 = SQLite.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME, Integer.parseInt(cursor.getString(0)));
                }
                cursor.close();
            }
        }

        /* access modifiers changed from: private */
        public YailList B8WBXPBCF2jGfUDZZU2zV5EYdqbUBu0lAZ0THCEqYyuE8VACR9dY7rDnwBIqh64T(String str) {
            List list;
            StringBuilder sb;
            String str2 = str;
            new ArrayList();
            List list2 = list;
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                SQLiteDatabase sQLiteDatabase = writableDatabase;
                writableDatabase.execSQL(str2);
                hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME(sQLiteDatabase);
                sQLiteDatabase.close();
                boolean add = list2.add("true");
            } catch (SQLException e) {
                new StringBuilder("Error executing query. Error is: ");
                int d = Log.d("SQLite", sb.append(e.getMessage()).toString());
                boolean add2 = list2.add("false");
            }
            return YailList.makeList(list2);
        }
    }
}

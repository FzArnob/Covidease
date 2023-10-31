package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.view.View;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.ViewUtil;
import java.io.PrintStream;

@SimpleObject
@DesignerComponent(category = ComponentCategory.LAYOUT_GENERAL, description = "<p>A formatting element in which to place components that should be displayed in tabular form.</p>", version = 1)
public class TableArrangement extends AndroidViewComponent implements Component, ComponentContainer {
    private final Activity hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* renamed from: hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME  reason: collision with other field name */
    private final TableLayout f530hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public TableArrangement(com.google.appinventor.components.runtime.ComponentContainer r10) {
        /*
            r9 = this;
            r0 = r9
            r1 = r10
            r2 = r0
            r3 = r1
            r2.<init>(r3)
            r2 = r0
            r3 = r1
            android.app.Activity r3 = r3.$context()
            r2.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r0
            com.google.appinventor.components.runtime.TableLayout r3 = new com.google.appinventor.components.runtime.TableLayout
            r8 = r3
            r3 = r8
            r4 = r8
            r5 = r0
            android.app.Activity r5 = r5.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME
            r6 = 2
            r7 = 2
            r4.<init>(r5, r6, r7)
            r2.f530hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME = r3
            r2 = r1
            r3 = r0
            r2.$add(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.appinventor.components.runtime.TableArrangement.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleProperty(userVisible = false)
    public int Columns() {
        return this.f530hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getNumColumns();
    }

    @DesignerProperty(defaultValue = "2", editorType = "non_negative_integer")
    @SimpleProperty(userVisible = false)
    public void Columns(int i) {
        this.f530hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setNumColumns(i);
    }

    @SimpleProperty(userVisible = false)
    public int Rows() {
        return this.f530hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getNumRows();
    }

    @DesignerProperty(defaultValue = "2", editorType = "non_negative_integer")
    @SimpleProperty(userVisible = false)
    public void Rows(int i) {
        this.f530hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.setNumRows(i);
    }

    public Activity $context() {
        return this.hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME;
    }

    public Form $form() {
        return this.container.$form();
    }

    public void $add(AndroidViewComponent androidViewComponent) {
        this.f530hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.add(androidViewComponent);
    }

    public void setChildWidth(AndroidViewComponent androidViewComponent, int i) {
        StringBuilder sb;
        StringBuilder sb2;
        AndroidViewComponent androidViewComponent2 = androidViewComponent;
        int i2 = i;
        PrintStream printStream = System.err;
        new StringBuilder("TableArrangement.setChildWidth: width = ");
        printStream.println(sb.append(i2).append(" component = ").append(androidViewComponent2).toString());
        if (i2 <= -1000) {
            int Width = this.container.$form().Width();
            int i3 = Width;
            if (Width <= -1000 || i3 > 0) {
                PrintStream printStream2 = System.err;
                new StringBuilder("%%TableArrangement.setChildWidth(): width = ");
                printStream2.println(sb2.append(i2).append(" parent Width = ").append(i3).append(" child = ").append(androidViewComponent2).toString());
                i2 = (i3 * (-(i2 + 1000))) / 100;
            } else {
                i2 = -1;
            }
        }
        androidViewComponent2.setLastWidth(i2);
        ViewUtil.setChildWidthForTableLayout(androidViewComponent2.getView(), i2);
    }

    public void setChildHeight(AndroidViewComponent androidViewComponent, int i) {
        AndroidViewComponent androidViewComponent2 = androidViewComponent;
        int i2 = i;
        if (i2 <= -1000) {
            int Height = this.container.$form().Height();
            int i3 = Height;
            if (Height <= -1000 || i3 > 0) {
                i2 = (i3 * (-(i2 + 1000))) / 100;
            } else {
                i2 = -1;
            }
        }
        androidViewComponent2.setLastHeight(i2);
        ViewUtil.setChildHeightForTableLayout(androidViewComponent2.getView(), i2);
    }

    public View getView() {
        return this.f530hxYOFxFjLpN1maJuWNxUV40nExCGxsxkDPOTgtzMu4zlZCQb3bPlKsXo1SYJg6ME.getLayoutManager();
    }
}

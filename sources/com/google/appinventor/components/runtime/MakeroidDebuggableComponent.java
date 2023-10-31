package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.common.ComponentCategory;

@SimpleObject
@DesignerComponent(category = ComponentCategory.INTERNAL, description = "This component makes the app debuggable", iconName = "images/debug.png", nonVisible = true, version = 1)
public class MakeroidDebuggableComponent extends AndroidNonvisibleComponent {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MakeroidDebuggableComponent(ComponentContainer componentContainer) {
        super(componentContainer.$form());
    }
}

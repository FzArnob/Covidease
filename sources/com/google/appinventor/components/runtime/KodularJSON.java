package com.google.appinventor.components.runtime;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.common.ComponentCategory;
import org.json.JSONArray;
import org.json.JSONObject;

@SimpleObject
@DesignerComponent(category = ComponentCategory.INTERNAL, description = "This is a non-visible component to edit or get data from JSON", iconName = "images/json.png", nonVisible = true, version = 1)
public class KodularJSON extends AndroidNonvisibleComponent {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public KodularJSON(ComponentContainer componentContainer) {
        super(componentContainer.$form());
    }

    @SimpleFunction(description = "Get text from a JSON object.")
    public String GetTextFromJSONObject(String str, String str2, String str3) {
        StringBuilder sb;
        JSONObject jSONObject;
        String str4 = str2;
        String str5 = str3;
        try {
            new JSONObject(str);
            return jSONObject.getString(str4);
        } catch (Exception e) {
            new StringBuilder();
            ErrorOccurred("GetTextFromJSONObject", sb.append(e.getMessage()).toString());
            return str5;
        }
    }

    @SimpleFunction(description = "Get a number from a JSON Object.")
    public double GetNumberFromJSONObject(String str, String str2, double d) {
        StringBuilder sb;
        JSONObject jSONObject;
        String str3 = str2;
        double d2 = d;
        try {
            new JSONObject(str);
            return jSONObject.getDouble(str3);
        } catch (Exception e) {
            new StringBuilder();
            ErrorOccurred("GetNumberFromJSONObject", sb.append(e.getMessage()).toString());
            return d2;
        }
    }

    @SimpleFunction(description = "Get a true or false value from a JSON Object.")
    public boolean GetBooleanFromJSONObject(String str, String str2, boolean z) {
        StringBuilder sb;
        JSONObject jSONObject;
        String str3 = str2;
        boolean z2 = z;
        try {
            new JSONObject(str);
            return jSONObject.getBoolean(str3);
        } catch (Exception e) {
            new StringBuilder();
            ErrorOccurred("GetBooleanFromJSONObject", sb.append(e.getMessage()).toString());
            return z2;
        }
    }

    @SimpleFunction(description = "Get a JSON Object from a JSON Object.")
    public String GetJSONObjectFromJSONObject(String str, String str2, String str3) {
        StringBuilder sb;
        JSONObject jSONObject;
        String str4 = str2;
        String str5 = str3;
        try {
            new JSONObject(str);
            return jSONObject.getJSONObject(str4).toString();
        } catch (Exception e) {
            new StringBuilder();
            ErrorOccurred("GetJSONObjectFromJSONObject", sb.append(e.getMessage()).toString());
            return str5;
        }
    }

    @SimpleFunction(description = "Get a JSON Array from a JSON Object.")
    public String GetJSONArrayFromJSONObject(String str, String str2, String str3) {
        StringBuilder sb;
        JSONObject jSONObject;
        String str4 = str2;
        String str5 = str3;
        try {
            new JSONObject(str);
            return jSONObject.getJSONArray(str4).toString();
        } catch (Exception e) {
            new StringBuilder();
            ErrorOccurred("GetJSONArrayFromJSONObject", sb.append(e.getMessage()).toString());
            return str5;
        }
    }

    @SimpleFunction(description = "Add a JSON Object to a JSON Object.")
    public String AddJSONObjectToJSONObject(String str, String str2, String str3, String str4) {
        StringBuilder sb;
        JSONObject jSONObject;
        Object obj;
        String str5 = str2;
        String str6 = str3;
        String str7 = str4;
        try {
            new JSONObject(str);
            JSONObject jSONObject2 = jSONObject;
            new JSONObject(str5);
            return jSONObject2.put(str6, obj).toString();
        } catch (Exception e) {
            new StringBuilder();
            ErrorOccurred("AddJSONObjectToJSONObject", sb.append(e.getMessage()).toString());
            return str7;
        }
    }

    @SimpleFunction(description = "Add Text to a JSON Object.")
    public String AddTextToJSONObject(String str, String str2, String str3, String str4) {
        StringBuilder sb;
        JSONObject jSONObject;
        String str5 = str2;
        String str6 = str3;
        String str7 = str4;
        try {
            new JSONObject(str);
            return jSONObject.put(str6, str5).toString();
        } catch (Exception e) {
            new StringBuilder();
            ErrorOccurred("AddTextToJSONObject", sb.append(e.getMessage()).toString());
            return str7;
        }
    }

    @SimpleFunction(description = "Add a Number to a JSON Object.")
    public String AddNumberToJSONObject(String str, double d, String str2, String str3) {
        StringBuilder sb;
        JSONObject jSONObject;
        double d2 = d;
        String str4 = str2;
        String str5 = str3;
        try {
            new JSONObject(str);
            return jSONObject.put(str4, d2).toString();
        } catch (Exception e) {
            new StringBuilder();
            ErrorOccurred("AddNumberToJSONObject", sb.append(e.getMessage()).toString());
            return str5;
        }
    }

    @SimpleFunction(description = "Add True or False to a JSON Object.")
    public String AddBooleanToJSONObject(String str, boolean z, String str2, String str3) {
        StringBuilder sb;
        JSONObject jSONObject;
        boolean z2 = z;
        String str4 = str2;
        String str5 = str3;
        try {
            new JSONObject(str);
            return jSONObject.put(str4, z2).toString();
        } catch (Exception e) {
            new StringBuilder();
            ErrorOccurred("AddBooleanToJSONObject", sb.append(e.getMessage()).toString());
            return str5;
        }
    }

    @SimpleFunction(description = "Get a JSON Object from a JSON Array.")
    public String GetJSONObjectFromJSONAray(String str, int i, String str2) {
        StringBuilder sb;
        JSONArray jSONArray;
        int i2 = i;
        String str3 = str2;
        try {
            new JSONArray(str);
            return jSONArray.getJSONObject(i2).toString();
        } catch (Exception e) {
            new StringBuilder();
            ErrorOccurred("GetJSONObjectFromJSONAray", sb.append(e.getMessage()).toString());
            return str3;
        }
    }

    @SimpleFunction(description = "Get Text from a JSON Array.")
    public String GetTextFromJSONAray(String str, int i, String str2) {
        StringBuilder sb;
        JSONArray jSONArray;
        int i2 = i;
        String str3 = str2;
        try {
            new JSONArray(str);
            return jSONArray.getString(i2).toString();
        } catch (Exception e) {
            new StringBuilder();
            ErrorOccurred("GetTextFromJSONAray", sb.append(e.getMessage()).toString());
            return str3;
        }
    }

    @SimpleFunction(description = "Get a Number from a JSON Array.")
    public double GetNumberFromJSONAray(String str, int i, double d) {
        StringBuilder sb;
        JSONArray jSONArray;
        int i2 = i;
        double d2 = d;
        try {
            new JSONArray(str);
            return jSONArray.getDouble(i2);
        } catch (Exception e) {
            new StringBuilder();
            ErrorOccurred("GetNumberFromJSONAray", sb.append(e.getMessage()).toString());
            return d2;
        }
    }

    @SimpleFunction(description = "Get a True or False from a JSON Array.")
    public boolean GetBooleanFromJSONAray(String str, int i, boolean z) {
        StringBuilder sb;
        JSONArray jSONArray;
        int i2 = i;
        boolean z2 = z;
        try {
            new JSONArray(str);
            return jSONArray.getBoolean(i2);
        } catch (Exception e) {
            new StringBuilder();
            ErrorOccurred("GetBooleanFromJSONAray", sb.append(e.getMessage()).toString());
            return z2;
        }
    }

    @SimpleFunction(description = "Get the length of a JSON Array.")
    public int GetLengthOfJSONArray(String str, int i) {
        StringBuilder sb;
        JSONArray jSONArray;
        int i2 = i;
        try {
            new JSONArray(str);
            return jSONArray.length();
        } catch (Exception e) {
            new StringBuilder();
            ErrorOccurred("GetLengthOfJSONArray", sb.append(e.getMessage()).toString());
            return i2;
        }
    }

    @SimpleFunction(description = "Add a JSON Array to a JSON Array.")
    public String AddJSONArrayToJSONArray(String str, String str2, String str3) {
        StringBuilder sb;
        JSONArray jSONArray;
        Object obj;
        String str4 = str2;
        String str5 = str3;
        try {
            new JSONArray(str);
            JSONArray jSONArray2 = jSONArray;
            new JSONArray(str4);
            return jSONArray2.put(obj).toString();
        } catch (Exception e) {
            new StringBuilder();
            ErrorOccurred("AddJSONArrayToJSONArray", sb.append(e.getMessage()).toString());
            return str5;
        }
    }

    @SimpleFunction(description = "Add a JSON Object to a JSON Array.")
    public String AddJSONObjectToJSONArray(String str, String str2, String str3) {
        StringBuilder sb;
        JSONArray jSONArray;
        Object obj;
        String str4 = str2;
        String str5 = str3;
        try {
            new JSONArray(str);
            JSONArray jSONArray2 = jSONArray;
            new JSONObject(str4);
            return jSONArray2.put(obj).toString();
        } catch (Exception e) {
            new StringBuilder();
            ErrorOccurred("AddJSONObjectToJSONArray", sb.append(e.getMessage()).toString());
            return str5;
        }
    }

    @SimpleFunction(description = "Add Text to a JSON Array.")
    public String AddTextToJSONArray(String str, String str2, String str3) {
        StringBuilder sb;
        JSONArray jSONArray;
        String str4 = str2;
        String str5 = str3;
        try {
            new JSONArray(str);
            return jSONArray.put(str4).toString();
        } catch (Exception e) {
            new StringBuilder();
            ErrorOccurred("AddTextToJSONArray", sb.append(e.getMessage()).toString());
            return str5;
        }
    }

    @SimpleFunction(description = "Add a Number to a JSON Array.")
    public String AddNumberToJSONArray(String str, double d, String str2) {
        StringBuilder sb;
        JSONArray jSONArray;
        double d2 = d;
        String str3 = str2;
        try {
            new JSONArray(str);
            return jSONArray.put(d2).toString();
        } catch (Exception e) {
            new StringBuilder();
            ErrorOccurred("AddNumberToJSONArray", sb.append(e.getMessage()).toString());
            return str3;
        }
    }

    @SimpleFunction(description = "Add True or False to a JSON Array.")
    public String AddBooleanToJSONArray(String str, boolean z, String str2) {
        StringBuilder sb;
        JSONArray jSONArray;
        boolean z2 = z;
        String str3 = str2;
        try {
            new JSONArray(str);
            return jSONArray.put(z2).toString();
        } catch (Exception e) {
            new StringBuilder();
            ErrorOccurred("AddBooleanToJSONArray", sb.append(e.getMessage()).toString());
            return str3;
        }
    }

    @SimpleEvent(description = "Triggers when there is a JSON error.")
    public void ErrorOccurred(String str, String str2) {
        Object[] objArr = new Object[2];
        objArr[0] = str;
        Object[] objArr2 = objArr;
        objArr2[1] = str2;
        boolean dispatchEvent = EventDispatcher.dispatchEvent(this, "ErrorOccurred", objArr2);
    }
}

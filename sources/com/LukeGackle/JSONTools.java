package com.LukeGackle;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

@SimpleObject(external = true)
@DesignerComponent(category = ComponentCategory.EXTENSION, description = "A collection of methods for reading JSON Arrays and JSON Objects with methods to Parse JSON and easily read JSON files. Developed by Luke Gackle.", iconName = "https://1.bp.blogspot.com/-d-xyqbKFyAY/WSDvpMEG-tI/AAAAAAABYTk/I9gjYEgABZYxjwi2pzmlqbvQg6eMJhSeQCLcB/s1600/ExtensionsIcons.png", nonVisible = true, version = 3)
public class JSONTools extends AndroidNonvisibleComponent implements Component {
    private static final int CURRENT_ARRAY = 3;
    private static final int CURRENT_OBJECT = 2;
    public static final String DEVELOPER = "Luke Gackle";
    private static final String LOG_TAG = "JSONToolsExtension-3-Luke Gackle";
    private static final int ROOT = 1;
    private static final int ROOT_TYPE_ARRAY = 1;
    private static final int ROOT_TYPE_OBJECT = 2;
    public static final int VERSION = 3;
    private ComponentContainer container;
    private JSONArray currentArray;
    private JSONObject currentObject;
    private int currentPosition;
    private JSONArray rootArray;
    private JSONObject rootObject;
    private int rootType;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public JSONTools(com.google.appinventor.components.runtime.ComponentContainer r5) {
        /*
            r4 = this;
            r0 = r4
            r1 = r5
            r2 = r0
            r3 = r1
            com.google.appinventor.components.runtime.Form r3 = r3.$form()
            r2.<init>(r3)
            r2 = r0
            r3 = r1
            r2.container = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.LukeGackle.JSONTools.<init>(com.google.appinventor.components.runtime.ComponentContainer):void");
    }

    @SimpleFunction(description = "Parse JSON takes a String and internally creates a JSON Object or Array. ParseJSON also internally establishes this Array or Object as the root.")
    public void ParseJSON(String str) throws JSONException {
        Throwable th;
        JSONTokener jSONTokener;
        JSONArray jSONArray;
        JSONObject jSONObject;
        String JSONString = str;
        try {
            new JSONTokener(JSONString);
            Object json = jSONTokener.nextValue();
            if (json instanceof JSONObject) {
                new JSONObject(JSONString);
                this.rootObject = jSONObject;
                this.rootType = 2;
            } else if (json instanceof JSONArray) {
                new JSONArray(JSONString);
                this.rootArray = jSONArray;
                this.rootType = 1;
            }
            this.currentPosition = 1;
        } catch (Exception e) {
            Exception exc = e;
            Throwable th2 = th;
            new YailRuntimeError("Argument must be valid JSON", "Check your JSON and try again.");
            throw th2;
        }
    }

    @SimpleFunction(description = "Use this method to open a JSON Object denoted by curly braces. This opens a JSON Object from the parsed JSON String. To open an Object inside of the current Array or Object use the \"OpenSubJSONObject\" block.")
    public void OpenJSONObject(String str) throws JSONException {
        String JSONObjectName = str;
        if (this.rootType == 1) {
            this.currentObject = this.rootArray.getJSONObject(0).getJSONObject(JSONObjectName);
        } else if (this.rootType == 2) {
            this.currentObject = this.rootObject.getJSONObject(JSONObjectName);
        }
        this.currentPosition = 2;
    }

    @SimpleFunction(description = "Use this method to open a JSON Array denoted by square brackets. This opens a JSON Array from the parsed JSON String. To open an Array inside of the current Array or Object use the \"OpenSubJSONArray\" block.")
    public void OpenJSONArray(String str) throws JSONException {
        String JSONArrayName = str;
        if (this.rootType == 1) {
            this.currentArray = this.rootArray.getJSONObject(0).getJSONArray(JSONArrayName);
        } else if (this.rootType == 2) {
            this.currentArray = this.rootObject.getJSONArray(JSONArrayName);
        }
        this.currentPosition = 3;
    }

    @SimpleFunction(description = "Opens a sub array inside the current JSON array or Object. If the current object is a JSON array it will open the sub array at the specified index. If the current object is a JSON Object then the index does nothing. NOTE: If you use this method inside a for loop note that it will affect the method \"GetCurrentArrayLength\", be sure to set the array back to the desired array using the block \"OpenJSONArray\".")
    public void OpenSubJSONArray(String str, int i) throws JSONException {
        String JSONArrayName = str;
        int index = i;
        if (this.currentPosition == 3) {
            this.currentArray = this.currentArray.getJSONObject(index - 1).getJSONArray(JSONArrayName);
        } else if (this.currentPosition == 2) {
            this.currentArray = this.currentObject.getJSONArray(JSONArrayName);
        }
        if (this.currentPosition != 1) {
            this.currentPosition = 3;
        }
    }

    @SimpleFunction(description = "Opens a JSON Object inside of the current JSON Array or Object. If current position is ROOT use OpenJSONObject instead.")
    public void OpenSubJSONObject(String str) throws JSONException {
        String JSONObjectName = str;
        if (this.currentPosition == 3) {
            this.currentObject = this.currentArray.getJSONObject(0).getJSONObject(JSONObjectName);
        } else if (this.currentPosition == 2) {
            this.currentObject = this.currentObject.getJSONObject(JSONObjectName);
        }
        if (this.currentPosition != 1) {
            this.currentPosition = 2;
        }
    }

    @SimpleFunction(description = "Retrieves the String value for the given attribute for the first occurance in the current JSON Object or Array. If the current JSON type is Array the index defaults to 1(The first item in the array) If you need to get the value from other indexes use the method \"GetStringInArray\".")
    public String GetStringValue(String str) throws JSONException {
        String attributeName = str;
        if (this.currentPosition == 1) {
            if (this.rootType == 1) {
                return this.rootArray.getJSONObject(0).getString(attributeName).toString();
            }
            if (this.rootType == 2) {
                return this.rootObject.getString(attributeName).toString();
            }
        } else if (this.currentPosition == 3) {
            return this.currentArray.getJSONObject(0).getString(attributeName).toString();
        } else {
            if (this.currentPosition == 2) {
                return this.currentObject.getString(attributeName).toString();
            }
        }
        return "";
    }

    @SimpleFunction(description = "Retrieves the int value for the given attribute for the first occurance in the current JSON Object or Array. If the current JSON type is Array the index defaults to 1(The first item in the array) If you need to get the value from other indexes use the method \"GetStringInArray\".")
    public int GetIntValue(String str) throws JSONException {
        String attributeName = str;
        if (this.currentPosition == 1) {
            if (this.rootType == 1) {
                return this.rootArray.getJSONObject(0).getInt(attributeName);
            }
            if (this.rootType == 2) {
                return this.rootObject.getInt(attributeName);
            }
        } else if (this.currentPosition == 3) {
            return this.currentArray.getJSONObject(0).getInt(attributeName);
        } else {
            if (this.currentPosition == 2) {
                return this.currentObject.getInt(attributeName);
            }
        }
        return -1;
    }

    @SimpleFunction(description = "Retrieves the int value for the given attribute for the first occurance in the current JSON Object or Array. If the current JSON type is Array the index defaults to 1(The first item in the array) If you need to get the value from other indexes use the method \"GetStringInArray\".")
    public boolean GetBooleanValue(String str) throws JSONException {
        String attributeName = str;
        if (this.currentPosition == 1) {
            if (this.rootType == 1) {
                return this.rootArray.getJSONObject(0).getBoolean(attributeName);
            }
            if (this.rootType == 2) {
                return this.rootObject.getBoolean(attributeName);
            }
        } else if (this.currentPosition == 3) {
            return this.currentArray.getJSONObject(0).getBoolean(attributeName);
        } else {
            if (this.currentPosition == 2) {
                return this.currentObject.getBoolean(attributeName);
            }
        }
        return false;
    }

    @SimpleFunction(description = "Retrieves the value for the given attribute for the given index in the JSON array. To bring this inline with App Inventor conventions, the index starts at 1.")
    public int GetIntInArray(int i) throws JSONException {
        int index = i;
        if (index <= 0 || index > GetCurrentArrayLength() + 1) {
            return -1;
        }
        try {
            if (this.currentPosition == 1) {
                if (this.rootType == 1) {
                    return this.rootArray.getInt(index - 1);
                }
            } else if (this.currentPosition == 3) {
                return this.currentArray.getInt(index - 1);
            }
        } catch (JSONException e) {
            JSONException jSONException = e;
        }
        return -1;
    }

    @SimpleFunction(description = "Retrieves the value for the given attribute for the given index in the JSON array. To bring this inline with App Inventor conventions, the index starts at 1.")
    public String GetStringInArray(String str, int i) throws JSONException {
        String JSONArrayAttribute = str;
        int index = i;
        if (index <= 0 || index > GetCurrentArrayLength() + 1) {
            return "";
        }
        try {
            if (this.currentPosition == 1) {
                if (this.rootType == 1) {
                    return this.rootArray.getJSONObject(index - 1).getString(JSONArrayAttribute).toString();
                }
            } else if (this.currentPosition == 3) {
                return this.currentArray.getJSONObject(index - 1).getString(JSONArrayAttribute).toString();
            }
        } catch (JSONException e) {
            JSONException jSONException = e;
        }
        return "";
    }

    @SimpleFunction(description = "Retrieves the value at the given index in the current JSON array. This method is for use when the elements in the array dont have a name to reference. To bring this inline with App Inventor conventions, the index starts at 1.")
    public String GetStringInArrayByIndex(int i) throws JSONException {
        int index = i;
        if (index <= 0 || index > GetCurrentArrayLength() + 1) {
            return "";
        }
        try {
            if (this.currentPosition == 1) {
                if (this.rootType == 1) {
                    return this.rootArray.getString(index).toString();
                }
            } else if (this.currentPosition == 3) {
                return this.currentArray.getString(index).toString();
            }
        } catch (JSONException e) {
            JSONException jSONException = e;
        }
        return "";
    }

    @SimpleFunction(description = "This method can be used in cases where the JSON Object does not have a name to reference it, you can use the index parameter to specify at which index in the array the desired object is. You can then use the regular methods to get the attributes inside the object. JSON Arrays cannot be placed inside objects without a name and as such there is no method for getting an array by index.")
    public void OpenObjectInArrayByIndex(int i) throws JSONException {
        int index = i;
        if (index > 0 && index <= GetCurrentArrayLength() + 1) {
            try {
                if (this.currentPosition == 1) {
                    if (this.rootType == 1) {
                        this.currentObject = this.rootArray.getJSONObject(index - 1);
                        this.currentPosition = 2;
                    }
                } else if (this.currentPosition == 3) {
                    this.currentObject = this.currentArray.getJSONObject(index - 1);
                    this.currentPosition = 2;
                }
            } catch (JSONException e) {
                JSONException jSONException = e;
            }
        }
    }

    @SimpleFunction(description = "Internally sets the current position to the \"root\" JSON, in other words the JSON Array or Object that was originally parsed.")
    public void SetCurrentPositionToRoot() {
        this.currentPosition = 1;
    }

    @SimpleFunction(description = "Returns the value for the given attribute, in the given sub array, without resetting the current location.")
    public String GetStringInSubJSONArray(int index, String JSONArrayName, String attributeName) throws JSONException {
        return this.currentArray.getJSONObject(index - 1).getJSONArray(JSONArrayName).getJSONObject(0).getString(attributeName).toString();
    }

    @SimpleFunction(description = "Returns the value for the given attribute, in the given sub array or sub object without resetting the current location, for JSON objects, if the current location is inside an object then index does nothing.")
    public String GetStringInSubJSONObject(int i, String str, String str2) throws JSONException {
        Throwable th;
        int index = i;
        String JSONObjectName = str;
        String attributeName = str2;
        if (this.currentPosition == 1) {
            if (this.rootType != 2) {
                return this.rootArray.getJSONObject(index - 1).getJSONObject(JSONObjectName).getString(attributeName).toString();
            }
            Throwable th2 = th;
            new YailRuntimeError("Current position cannot be root", "Use OpenJSONObject method.");
            throw th2;
        } else if (this.currentPosition == 3) {
            return this.currentArray.getJSONObject(index - 1).getJSONObject(JSONObjectName).getString(attributeName).toString();
        } else {
            if (this.currentPosition == 2) {
                return this.currentObject.getJSONObject(JSONObjectName).getString(attributeName).toString();
            }
            return "";
        }
    }

    @SimpleFunction(description = "Returns the length of the current JSON Array, if the JSON type is Object, this method returns -1.")
    public int GetCurrentArrayLength() throws JSONException {
        if (this.currentPosition == 1) {
            if (this.rootType == 1) {
                return this.rootArray.length();
            }
        } else if (this.currentPosition == 3) {
            return this.currentArray.length();
        }
        return -1;
    }
}

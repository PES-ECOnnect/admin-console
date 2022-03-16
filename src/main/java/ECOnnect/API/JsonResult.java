package ECOnnect.API;

import com.google.gson.*;

public class JsonResult {
    private JsonObject _jsonObject;
    
    public JsonResult(JsonElement jsonElement) {
        this._jsonObject = jsonElement.getAsJsonObject();
    }
    
    public String getAttribute(String attrName) {
        JsonElement element = _jsonObject.get(attrName);
        if (element == null) return null;
        return element.getAsString();
    }
}
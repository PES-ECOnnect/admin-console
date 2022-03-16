package ECOnnect.API;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import com.google.gson.*;

import ECOnnect.API.Exceptions.ApiException;
import ECOnnect.API.Exceptions.InvalidTokenApiException;
import ECOnnect.API.HttpClient.HttpClient;

public abstract class Service {
    // Reference to the HttpClient used to communicate with the API
    private static HttpClient httpClient;
    // Store the secret token and insert it into the request headers
    private static String adminToken = null;
    // Gson object used to serialize and deserialize JSON
    private Gson gson = new Gson();
    
    // Call once from ServiceFactory
    public static void setHttpClient(HttpClient client) {
        httpClient = client;
    }
    
    // Instantiate only from ServiceFactory, after setting the HttpClient
    Service() {
        if (httpClient == null) {
            throw new RuntimeException("HttpClient not set");
        }
    }
    
    // Update the admin token, called from AdminLoginService
    protected static void setAdminToken(String token) {
        if (token == null) throw new IllegalArgumentException("Token cannot be null");
        if (adminToken != null) throw new IllegalStateException("Token already set");
        adminToken = token;
    }
    // Invalidate the admin token, called from AdminLogoutService
    protected static void deleteAdminToken() {
        if (adminToken == null) throw new IllegalStateException("Session token was already deleted");
        adminToken = null;
    }
    
    // Generic GET request
    protected JsonResult get(String path, Map<String,String> params) throws ApiException {
        String url = ApiConstants.BASE_URL + path;
        addTokenToRequest(params);
        
        String result = null;
        try {
            result = httpClient.get(url, params);
        }
        catch (IOException e) {
            throw new RuntimeException("Error executing GET on " + url + ": " + e.getMessage());
        }
        return parseResult(result);
    }
    
    // Generic POST request
    protected JsonResult post(String path, Map<String,String> params, Object content) throws ApiException {
        String url = ApiConstants.BASE_URL + path;
        addTokenToRequest(params);
        
        String result = null;
        try {
            result = httpClient.post(url, params, gson.toJson(content));
        }
        catch (IOException e) {
            throw new RuntimeException("Error executing POST on " + url + ": " + e.getMessage());
        }
        return parseResult(result);
    }
    
    
    private void addTokenToRequest(Map<String,String> params) {
        if (!needsToken()) return;
        if (adminToken == null) {
            throw new IllegalStateException("Admin token not set");
        }
        if (params == null) {
            params = new TreeMap<>();
        }
        params.put(ApiConstants.TOKEN, adminToken);
    }
    protected abstract boolean needsToken();
    
    private JsonResult parseResult(String result) throws ApiException {
        if (result == null) return null;
        
        
        JsonResult json = null;
        try {
            json = new JsonResult(JsonParser.parseString(result));
        }
        catch (JsonSyntaxException | IllegalStateException e) {
            throw new RuntimeException("Invalid JSON response from server:\n" + result);
        }
        String error = json.getAttribute(ApiConstants.ERROR_ATTR_NAME);
        
        if (error != null) {
            if (error == ApiConstants.ERROR_INVALID_TOKEN) {
                deleteAdminToken();
                throw new InvalidTokenApiException();
            }
            throw new ApiException(error);
        }
        return json;
    }
}
package ECOnnect.API;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import com.google.gson.Gson;

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
        if (adminToken == null) throw new IllegalStateException("Token was already deleted");
        adminToken = null;
    }
    
    // Generic GET request
    protected String get(String path, Map<String,String> params) {
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
    protected String post(String path, Map<String,String> params, Object content) {
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
        params.put(ApiConstants.ADMIN_TOKEN, adminToken);
    }
    protected abstract boolean needsToken();
    
    private String parseResult(String result) {
        if (result == null) return null;
        
        // Todo: parse JSON
        // todo: if token is no longer valid, logout
        return result;
    }
}

package ECOnnect.API;

import java.util.TreeMap;

public class AdminLoginService extends Service {
    
    // Only allow instantiating from ServiceFactory
    AdminLoginService() {}
    
    public boolean login(String username, String password) {
        
        // Add parameters
        TreeMap<String, String> params = new TreeMap<>();
        params.put(ApiConstants.ADMIN_LOGIN_NAME, username);
        params.put(ApiConstants.ADMIN_LOGIN_PASSWORD, password);
        
        // Call API
        // String result = get(ApiConstants.LOGIN_PATH, params);
        // TODO: Once API is implemented, uncomment the line above and remove the line below
        String result = "";
        
        String token = extractToken(result);
        if (token == null) return false;
                
        super.setAdminToken(token);
        return true;
    }
    
    private String extractToken(String result) {
        // TODO: once API is implemented, implement this method
        return "abc";
    }
    
    protected boolean needsToken() {
        return false;
    }
}

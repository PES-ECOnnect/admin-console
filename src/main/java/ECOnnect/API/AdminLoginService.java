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
        JsonResult result = get(ApiConstants.LOGIN_PATH, params);
        
        String token = result.getAttribute(ApiConstants.RET_TOKEN);
        if (token == null) return false;
                
        super.setAdminToken(token);
        return true;
    }
    
    protected boolean needsToken() {
        // The API calls in this method don't need an adminToken
        return false;
    }
}

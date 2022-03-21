package ECOnnect.API;

import java.util.TreeMap;

import ECOnnect.API.Exceptions.ApiException;

public class AdminLoginService extends Service {
    
    // Only allow instantiating from ServiceFactory
    AdminLoginService() {}
    
    // Sets the user token, throws an exception if an error occurs or the user is not admin
    public void login(String email, String password) {
        
        // Add parameters
        TreeMap<String, String> params = new TreeMap<>();
        params.put(ApiConstants.ADMIN_LOGIN_NAME, email);
        params.put(ApiConstants.ADMIN_LOGIN_PASSWORD, password);
        
        // Call API
        super.needsToken = false;
        JsonResult result;
        
        try {
            result = get(ApiConstants.LOGIN_PATH, params);
        }
        catch (ApiException e) {
            switch (e.getErrorCode()) {
                case ApiConstants.ERROR_USER_NOT_FOUND:
                    throw new RuntimeException("No account found for this email");
                case ApiConstants.ERROR_WRONG_PASSWORD:
                    throw new RuntimeException("Incorrect password for this email");
                default:
                    throw e;
            }
        }
        
        String token = result.getAttribute(ApiConstants.RET_TOKEN);
        if (token == null) {
            // This should never happen, the API should always return a token or an error
            throwInvalidResponseError(result, ApiConstants.RET_TOKEN);
        }
        
        super.setToken(token);
        
        checkIsAdmin();
    }
    
    // Throws an exception if the logged in user is not an admin
    private void checkIsAdmin() {
        // If an exception is thrown, assume the user is not an admin
        String isAdmin = "false";
        
        try {
            // Call API (no parameters needed)
            super.needsToken = true;
            JsonResult result = get(ApiConstants.IS_ADMIN_PATH, null);
            isAdmin = result.getAttribute(ApiConstants.RET_ISADMIN);
        
            if (isAdmin == null) {
                // This should never happen, the API should always return 'true' or 'false'
                throwInvalidResponseError(result, ApiConstants.RET_ISADMIN);
            }
            if (!isAdmin.equals("true")) {
                throw new RuntimeException("This user is not an admin");
            }
        }
        catch (Exception e) {
            // If an exception is thrown, the user is not an admin
            logout();
            throw e;
        }
    }
    
    // Invalidates the user token, throws an exception if an error occurs
    public void logout() {       
        try {
            // Call API to invalidate in server (no parameters needed)
            super.needsToken = true;
            get(ApiConstants.LOGOUT_PATH, null);
        }
        finally {
            // Delete local token whether or not the API call succeeded
            super.deleteAdminToken();
        }
    }
}

package ECOnnect.API;

public class AdminLogoutService extends Service {

    // Only allow instantiating from ServiceFactory
    AdminLogoutService() {}

    public void logout() {       
        try {
            // Call API to invalidate in server (no parameters needed)
            get(ApiConstants.LOGOUT_PATH, null);
        }
        finally {
            // Delete local token whether or not the API call succeeded
            super.deleteAdminToken();
        }
    }
    
    protected boolean needsToken() {
        // The API calls in this method need an adminToken as one of the parameters
        return true;
    }
}

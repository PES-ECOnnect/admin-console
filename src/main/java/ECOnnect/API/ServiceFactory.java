package ECOnnect.API;

import ECOnnect.API.HttpClient.OkHttpAdapter;

public class ServiceFactory {
    // Singleton
    private static ServiceFactory instance = null;
    private ServiceFactory() {}
    public static ServiceFactory getInstance() {
        if (instance == null) {
            instance = new ServiceFactory();
            // Use OkHttp library
            Service.setHttpClient(new OkHttpAdapter());
        }
        return instance;
    }
    
    
    // Admin Login
    private static AdminLoginService adminLoginService = null;
    public AdminLoginService getAdminLoginService() {
        if (adminLoginService == null) {
            adminLoginService = new AdminLoginService();
        }
        return adminLoginService;
    }
    
    
    // Admin Logout
    private static AdminLogoutService adminLogoutService = null;
    public AdminLogoutService getAdminLogoutService() {
        if (adminLogoutService == null) {
            adminLogoutService = new AdminLogoutService();
        }
        return adminLogoutService;
    }
}

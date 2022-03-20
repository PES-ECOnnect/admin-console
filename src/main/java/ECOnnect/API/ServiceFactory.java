package ECOnnect.API;

import ECOnnect.API.HttpClient.OkHttpAdapter;
import ECOnnect.API.HttpClient.StubHttpClient;

public class ServiceFactory {
    // Singleton
    private static ServiceFactory instance = null;
    private ServiceFactory() {}
    public static ServiceFactory getInstance() {
        if (instance == null) {
            instance = new ServiceFactory();
            // Use OkHttp library
            Service.injectHttpClient(new OkHttpAdapter());
            
            // TODO: Remove this once the backend works
            Service.injectHttpClient(new StubHttpClient());
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

}

package ECOnnect.API;

import ECOnnect.API.HttpClient.OkHttpAdapter;
import ECOnnect.API.HttpClient.StubHttpClient;

public class ServiceFactory {
    // Singleton
    private static ServiceFactory _instance = null;
    private ServiceFactory() {}
    public static ServiceFactory getInstance() {
        if (_instance == null) {
            _instance = new ServiceFactory();
            // Use OkHttp library
            Service.injectHttpClient(new OkHttpAdapter());
            
            // TODO: Remove this once the backend works
            Service.injectHttpClient(new StubHttpClient());
        }
        return _instance;
    }
    
    
    // Admin Login
    private static AdminLoginService _adminLoginService = null;
    public AdminLoginService getAdminLoginService() {
        if (_adminLoginService == null) {
            _adminLoginService = new AdminLoginService();
        }
        return _adminLoginService;
    }

    
    // Product Types
    private static ProductTypesService _productTypesService = null;
    public ProductTypesService getProductTypesService() {
        if (_productTypesService == null) {
            _productTypesService = new ProductTypesService();
        }
        return _productTypesService;
    }
    
    // Products
    private static ProductService _productService = null;
    public ProductService getProductService() {
        if (_productService == null) {
            _productService = new ProductService();
        }
        return _productService;
    }
}

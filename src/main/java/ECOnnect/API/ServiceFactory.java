package ECOnnect.API;

import ECOnnect.API.HttpClient.OkHttpAdapter;
import ECOnnect.API.HttpClient.StubHttpClient;
import ECOnnect.API.ImageUpload.ImageService;

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
            // Service.injectHttpClient(new StubHttpClient());
        }
        return _instance;
    }
    
    
    // Image upload
    private static ImageService _imageService = null;
    public ImageService getImageService() {
        if (_imageService == null) {
            _imageService = new ImageService();
        }
        return _imageService;
    }
    
    // Admin Login
    private static LoginService _adminLoginService = null;
    public LoginService getLoginService() {
        if (_adminLoginService == null) {
            _adminLoginService = new LoginService();
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
    
    // Companies
    private static CompanyService _companyService = null;
    public CompanyService getCompanyService() {
        if (_companyService == null) {
            _companyService = new CompanyService();
        }
        return _companyService;
    }
    
    // Forum
    private static ForumService _forumService = null;
    public ForumService getForumService() {
        if (_forumService == null) {
            _forumService = new ForumService();
        }
        return _forumService;
    }
}

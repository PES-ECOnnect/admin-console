package ECOnnect.API;

// Namespace for storing all the API constants together.
public interface ApiConstants {
    
    // PATHS
    public final String BASE_URL_DEV = "http://127.0.0.1:5000";
    public final String BASE_URL_DEPLOY = "https://pes-econnect.herokuapp.com";
    public final String BASE_URL = BASE_URL_DEPLOY;
    
    public final String LOGIN_PATH = "/account/login";
    public final String LOGOUT_PATH = "/account/logout";
    public final String IS_ADMIN_PATH = "/account/isadmin";
    public final String TYPES_PATH = "/products/types";
    public final String PRODUCTS_PATH = "/products";
    public final String COMPANIES_PATH = "/companies";
    public final String COMPANY_QUESTIONS_PATH = "/companies/questions";
    
    
    // PARAMETERS
    
    // General
    public final String TOKEN = "token";
    
    // Login
    public final String ADMIN_LOGIN_NAME = "email";
    public final String ADMIN_LOGIN_PASSWORD = "password";
    
    // Get/create product types
    public final String PRODUCT_TYPES_NAME = "name";
    public final String PRODUCT_TYPES_QUESTIONS = "questions";
    
    // Get/create products
    public final String PRODUCT_NAME = "name";
    public final String PRODUCT_MANUFACTURER = "manufacturer";
    public final String PRODUCT_IMAGE_URL = "imageURL";
    public final String PRODUCT_TYPE = "type";
    
    // Get/create companies
    public final String COMPANY_NAME = "name";
    public final String COMPANY_IMAGE_URL = "imageURL";
    public final String COMPANY_LOCATION_LAT = "lat";
    public final String COMPANY_LOCATION_LON = "lon";
    
    
    // RETURN VALUES
    
    public final String RET_TOKEN = "token";
    public final String RET_STATUS = "status";
    public final String STATUS_OK = "success";
    public final String RET_ERROR = "error";
    public final String RET_RESULT = "result";
    
    
    // ERRORS
    
    // General
    public final String ERROR_INVALID_TOKEN = "ERROR_INVALID_TOKEN";
    
    // Login
    public final String ERROR_USER_NOT_FOUND = "ERROR_USER_NOT_FOUND";
    public final String ERROR_WRONG_PASSWORD = "ERROR_USER_INCORRECT_PASSWORD";
    
    // Create product type
    public final String ERROR_TYPE_EXISTS = "ERROR_TYPE_EXISTS";
    
    // Get/create products
    public final String ERROR_TYPE_NOT_EXISTS = "ERROR_TYPE_NOT_EXISTS";
    public final String ERROR_PRODUCT_EXISTS = "ERROR_PRODUCT_EXISTS";
    
    // Get/create companies
    public final String ERROR_COMPANY_EXISTS = "ERROR_COMPANY_EXISTS";
    
}

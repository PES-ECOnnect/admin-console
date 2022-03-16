package ECOnnect.API;

// Namespace for storing all the API constants togehter.
public interface ApiConstants {
    // PATHS
    public final String BASE_URL = "https://pes-econnect.herokuapp.com";
    public final String LOGIN_PATH = "/admin/login";
    public final String LOGOUT_PATH = "/admin/logout";
    // Todo: add paths
    
    // PARAMETERS
    public final String TOKEN = "token";
    
    public final String ADMIN_LOGIN_NAME = "name";
    public final String ADMIN_LOGIN_PASSWORD = "password";
    
    
    // RETURN VALUES
    public final String RET_TOKEN = "token";
    
    
    // ERRORS
    public final String ERROR_ATTR_NAME = "ERROR";
    public final String ERROR_INVALID_TOKEN = "INVALID_TOKEN";
}

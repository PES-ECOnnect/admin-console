package ECOnnect.UI.Login;

import ECOnnect.API.AdminLoginService;
import ECOnnect.API.ServiceFactory;

public class LoginModel {
    
    boolean validate(String username, String password) {
        // Perform local validation
        username = username.trim();
        if (username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        /*
        AdminLoginService loginSv = ServiceFactory.getInstance().getAdminLoginService();
        if (!loginSv.login(username, password)) {
            throw new IllegalArgumentException("Invalid username or password");
        }
        
         */
        return true;
    }
}

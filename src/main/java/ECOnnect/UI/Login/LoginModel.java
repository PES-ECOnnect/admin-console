package ECOnnect.UI.Login;

import ECOnnect.API.AdminLoginService;
import ECOnnect.API.ServiceFactory;

public class LoginModel {
    
    boolean validate(String email, String password) {
        // Perform local validation
        email = email.trim();
        if (email.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        
        AdminLoginService loginSv = ServiceFactory.getInstance().getAdminLoginService();
        loginSv.login(email, password);
        
        return true;
    }
}

package ECOnnect.UI.MainMenu;

import ECOnnect.API.AdminLoginService;
import ECOnnect.API.ServiceFactory;

public class MainMenuModel {
    public void logout() {
        AdminLoginService logoutSv = ServiceFactory.getInstance().getAdminLoginService();
        logoutSv.logout();
    }
}

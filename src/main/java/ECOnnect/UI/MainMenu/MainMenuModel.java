package ECOnnect.UI.MainMenu;

import ECOnnect.API.AdminLogoutService;
import ECOnnect.API.ServiceFactory;

public class MainMenuModel {
    public void logout() {
        AdminLogoutService logoutSv = ServiceFactory.getInstance().getAdminLogoutService();
        logoutSv.logout();
    }
}

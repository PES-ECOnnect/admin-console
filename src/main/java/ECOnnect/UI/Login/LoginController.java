package ECOnnect.UI.Login;

import java.awt.event.*;

import ECOnnect.UI.ScreenManager;
import ECOnnect.UI.Interfaces.*;

public class LoginController implements Controller {
    
    private LoginView view = new LoginView(this);
    private LoginModel model = new LoginModel();
    
    public ActionListener loginButton() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = view.getUsernameText();
                String password = view.getPasswordText();
                
                try {
                    model.validate(username, password);
                }
                catch (Exception ex) {
                    view.displayError("There has been an error:\n" + ex.getMessage());
                    return;
                }
                
                // Todo: Replace with next screen
                ScreenManager.getInstance().show(ScreenManager.MAIN_MENU_SCREEN);
            }
        };
    }
    
    public View getView() {
        return view;
    }
}

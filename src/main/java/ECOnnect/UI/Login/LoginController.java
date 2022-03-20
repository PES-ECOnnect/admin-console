package ECOnnect.UI.Login;

import java.awt.event.*;

import ECOnnect.UI.ScreenManager;
import ECOnnect.UI.Interfaces.*;
import ECOnnect.UI.Utilities.ExecutionThread;

public class LoginController implements Controller {
    
    private LoginView view = new LoginView(this);
    private LoginModel model = new LoginModel();
    
    public ActionListener loginButton() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = view.getUsernameText();
                String password = view.getPasswordText();
                view.enableInput(false);
                
                // This could take some time, invoke in a non-UI thread
                ExecutionThread.nonUI(() -> {
                    try {
                        model.validate(email, password);
                    }
                    catch (Exception ex) {
                        ExecutionThread.UI(() -> {
                            view.enableInput(true);
                            view.displayError("There has been an error:\n" + ex.getMessage());
                        });
                        return;
                    }
                    // Return to UI thread
                    ExecutionThread.UI(() -> {
                        ScreenManager.getInstance().show(ScreenManager.MAIN_MENU_SCREEN);
                    });
                });
            }
        };
    }
    
    public View getView() {
        return view;
    }
}

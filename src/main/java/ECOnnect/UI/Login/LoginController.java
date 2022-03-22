package ECOnnect.UI.Login;

import java.awt.event.*;

import ECOnnect.UI.ScreenManager;
import ECOnnect.UI.Interfaces.*;
import ECOnnect.UI.Utilities.ExecutionThread;

public class LoginController implements Controller {
    
    private final LoginView _view = new LoginView(this);
    private final LoginModel _model = new LoginModel();
    
    public ActionListener loginButton() {
        return (ActionEvent e) -> {
            String email = _view.getUsernameText();
            String password = _view.getPasswordText();
            _view.enableInput(false);
            
            // This could take some time, invoke in a non-UI thread
            ExecutionThread.nonUI(() -> {
                try {
                    _model.validate(email, password);
                }
                catch (Exception ex) {
                    ExecutionThread.UI(() -> {
                        _view.enableInput(true);
                        _view.displayError("There has been an error:\n" + ex.getMessage());
                    });
                    return;
                }
                // Return to UI thread
                ExecutionThread.UI(() -> {
                    ScreenManager.getInstance().show(ScreenManager.MAIN_MENU_SCREEN);
                });
            });
        };
    }
    
    public View getView() {
        return _view;
    }
}

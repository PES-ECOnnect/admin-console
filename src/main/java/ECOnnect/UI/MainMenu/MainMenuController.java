package ECOnnect.UI.MainMenu;

import javax.swing.*;
import javax.swing.event.*;

import ECOnnect.UI.ScreenManager;
import ECOnnect.UI.Interfaces.Controller;
import ECOnnect.UI.Interfaces.View;

public class MainMenuController implements Controller {
    private final MainMenuView _view = new MainMenuView(this);
    private final MainMenuModel _model = new MainMenuModel();
    
    ChangeListener tabListener() {
        return (ChangeEvent changeEvent) -> {
            JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
            int index = sourceTabbedPane.getSelectedIndex();
            String title = sourceTabbedPane.getTitleAt(index);
            
            ScreenManager.getInstance().updateTitle(title);
            // Special case for the logout tab
            if (title.equals("Logout")) {
                logout();
                ScreenManager.getInstance().show(ScreenManager.LOGIN_SCREEN);
            }
        };
    }
    
    private void logout() {
        try {
            _model.logout();
        }
        catch (Exception e) {
            _view.displayWarning("Failed to logout due to an error:\n" + e.getMessage() + "\nReturning to login screen...");
        }
    }
    
    public View getView() {
        return _view;
    }
}

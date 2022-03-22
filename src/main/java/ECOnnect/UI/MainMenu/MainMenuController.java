package ECOnnect.UI.MainMenu;

import javax.swing.*;
import javax.swing.event.*;

import ECOnnect.UI.ScreenManager;
import ECOnnect.UI.Interfaces.Controller;
import ECOnnect.UI.Interfaces.View;

public class MainMenuController implements Controller {
    private final MainMenuView _view = new MainMenuView(this);
    private final MainMenuModel _model = new MainMenuModel();
    
    private static int _currentTabIndex = 0;
    
    ChangeListener tabListener() {
        return (ChangeEvent changeEvent) -> {
            JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
            _currentTabIndex = sourceTabbedPane.getSelectedIndex();
            String title = sourceTabbedPane.getTitleAt(_currentTabIndex);
            
            ScreenManager.getInstance().updateTitle(title);
            // Special case for the logout tab
            if (title.equals("Logout")) {
                _currentTabIndex = 0;
                logout();
                ScreenManager.getInstance().show(ScreenManager.LOGIN_SCREEN);
            }
        };
    }
    
    int getCurrentTabIndex() {
        return _currentTabIndex;
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

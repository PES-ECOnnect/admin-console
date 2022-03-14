package ECOnnect.UI.MainMenu;

import javax.swing.*;
import javax.swing.event.*;

import ECOnnect.UI.ScreenManager;
import ECOnnect.UI.Interfaces.Controller;
import ECOnnect.UI.Interfaces.View;

public class MainMenuController implements Controller {
    private MainMenuView view = new MainMenuView(this);
    private MainMenuModel model = new MainMenuModel();
    
    ChangeListener tabListener() {
        return new ChangeListener() {
            public void stateChanged(ChangeEvent changeEvent) {
                JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
                int index = sourceTabbedPane.getSelectedIndex();
                String title = sourceTabbedPane.getTitleAt(index);
                
                ScreenManager.getInstance().updateTitle(title);
                // Special case for the logout tab
                if (title.equals("Logout")) {
                    logout();
                    ScreenManager.getInstance().show(ScreenManager.LOGIN_SCREEN);
                }
            }
        };
    }
    
    private void logout() {
        try {
            model.logout();
        }
        catch (Exception e) {
            view.displayWarning("Failed to logout due to an error:\n" + e.getMessage() + "\nReturning to login screen...");
        }
    }
    
    public View getView() {
        return view;
    }
}

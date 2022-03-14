package ECOnnect.UI.Interfaces;

import javax.swing.JPanel;

public abstract class Screen {
    
    Controller controller;
    View view = null;
    
    public Screen(Controller controller) {
        this.controller = controller;
    }
    
    public JPanel getPanel() {
        if (view == null) {
            view = controller.getView();
        }
        return view.getPanel();
    }
    
    // Called after the screen has been initialized and painted
    public void postInit() {
        if (view != null) {
            view.postInit();
        }
    }
    
    public abstract String getTitle();
}

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
    
    public abstract String getTitle();
}

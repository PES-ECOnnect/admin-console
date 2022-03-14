package ECOnnect.UI.Interfaces;

import javax.swing.*;

public abstract class View {
    protected JPanel panel;
    
    public View() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    }
    
    public JPanel getPanel() {
        return panel;
    }
    
    // Called after the view has been initialized and painted
    public void postInit() {
        // Do nothing by default
    }
    
    public void displayError(String error) {
        JOptionPane.showMessageDialog(panel, error, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public void displayWarning(String warning) {
        JOptionPane.showMessageDialog(panel, warning, "Warning", JOptionPane.WARNING_MESSAGE);
    }
    
    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(panel, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }
}

package ECOnnect.UI.Interfaces;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public abstract class View {
    protected JPanel panel = new JPanel();
    
    
    public JPanel getPanel() {
        return panel;
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

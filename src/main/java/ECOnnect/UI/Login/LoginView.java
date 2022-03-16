package ECOnnect.UI.Login;

import java.awt.*;
import javax.swing.*;

import ECOnnect.UI.Interfaces.View;
import ECOnnect.UI.Utilities.HorizontalBox;

public class LoginView extends View {
    
    private LoginController ctrl;
    
    // Components
    private JTextField usernameText = new JTextField(20);
    private JPasswordField passwordText = new JPasswordField(20);
    private JButton loginButton = new JButton("Login");
    
    LoginView(LoginController ctrl) {
        this.ctrl = ctrl;
        setUp();
    }
    
    // Build the GUI
    private void setUp() {
        panel.add(Box.createVerticalGlue());
        
        usernameText.setMaximumSize(new Dimension(100, 30));
        panel.add(HorizontalBox.create(new JLabel("Username: "), usernameText));
        // Pressing enter will select the password field
        usernameText.addActionListener(e -> passwordText.requestFocus());
        
        panel.add(Box.createVerticalStrut(10));
                
        passwordText.setMaximumSize(new Dimension(100, 30));
        panel.add(HorizontalBox.create(new JLabel("Password: "), passwordText));
        // Pressing enter will trigger the login button
        passwordText.addActionListener(ctrl.loginButton());
        
        panel.add(Box.createVerticalStrut(10));
        
        loginButton.addActionListener(ctrl.loginButton());
        panel.add(loginButton);
        
        panel.add(Box.createVerticalGlue());
    }
    
    String getUsernameText() {
        return usernameText.getText();
    }
    
    String getPasswordText() {
        return new String(passwordText.getPassword());
    }
    
    void enableInput(boolean enabled) {
        usernameText.setEnabled(enabled);
        passwordText.setEnabled(enabled);
        loginButton.setEnabled(enabled);
    }
}

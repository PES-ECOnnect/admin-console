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
    
    LoginView(LoginController ctrl) {
        this.ctrl = ctrl;
        setUp();
    }
    
    // Build the GUI
    private void setUp() {
        panel.add(Box.createVerticalGlue());
        
        usernameText.setMaximumSize(new Dimension(100, 30));
        panel.add(HorizontalBox.create(new JLabel("Username: "), usernameText));
        
        panel.add(Box.createVerticalStrut(10));
                
        passwordText.setMaximumSize(new Dimension(100, 30));
        panel.add(HorizontalBox.create(new JLabel("Password: "), passwordText));
        
        panel.add(Box.createVerticalStrut(10));
        
        JButton loginButton = new JButton("Login");
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
}

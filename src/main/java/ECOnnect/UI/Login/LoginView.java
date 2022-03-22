package ECOnnect.UI.Login;

import java.awt.*;
import javax.swing.*;

import ECOnnect.UI.Interfaces.View;
import ECOnnect.UI.Utilities.HorizontalBox;

public class LoginView extends View {
    
    private final LoginController _ctrl;
    private final JTextField _usernameText = new JTextField(20);
    private final JPasswordField _passwordText = new JPasswordField(20);
    private final JButton _loginButton = new JButton("Login");
    
    LoginView(LoginController ctrl) {
        this._ctrl = ctrl;
        setUp();
    }
    
    // Build the GUI
    private void setUp() {
        panel.add(Box.createVerticalGlue());
        
        _usernameText.setMaximumSize(new Dimension(100, 30));
        panel.add(HorizontalBox.create(new JLabel("Username: "), _usernameText));
        // Pressing enter will select the password field
        _usernameText.addActionListener(e -> _passwordText.requestFocus());
        
        panel.add(Box.createVerticalStrut(10));
                
        _passwordText.setMaximumSize(new Dimension(100, 30));
        panel.add(HorizontalBox.create(new JLabel("Password: "), _passwordText));
        // Pressing enter will trigger the login button
        _passwordText.addActionListener(_ctrl.loginButton());
        
        panel.add(Box.createVerticalStrut(10));
        
        _loginButton.addActionListener(_ctrl.loginButton());
        panel.add(_loginButton);
        
        panel.add(Box.createVerticalGlue());
    }
    
    String getUsernameText() {
        return _usernameText.getText();
    }
    
    String getPasswordText() {
        return new String(_passwordText.getPassword());
    }
    
    void enableInput(boolean enabled) {
        _usernameText.setEnabled(enabled);
        _passwordText.setEnabled(enabled);
        _loginButton.setEnabled(enabled);
    }
}

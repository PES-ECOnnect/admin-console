package ECOnnect.UI;

import java.util.ArrayList;

import javax.swing.*;

import ECOnnect.UI.Interfaces.Screen;
import ECOnnect.UI.Login.LoginScreen;

import java.awt.*;
import java.lang.reflect.Modifier;

public class ScreenManager {
    // Singleton
    private static ScreenManager instance;
    private ScreenManager() {
    }
    public static ScreenManager getInstance() {
        if (instance == null) {
            instance = new ScreenManager();
        }
        return instance;
    }
    
    private final int MIN_SCREEN_HEIGHT = 720;
    private final int MIN_SCREEN_WIDTH = MIN_SCREEN_HEIGHT * 16 / 9;
    private final int TASKBAR_HEIGHT = 48;
    
    private JFrame frame = new JFrame();;
    
    public void init() {
        // Fixed size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(MIN_SCREEN_WIDTH, MIN_SCREEN_HEIGHT-TASKBAR_HEIGHT);
        frame.setResizable(false);
        
        // Center the window
        frame.setLocationRelativeTo(null);
        
        // Make the window visible
        frame.setVisible(true);
        
        // Set icon
        setIcon();
        
        // Default content
        show(LoginScreen.class);
    }
    
    public void show(Class<? extends Screen> screenClass) {
        Screen s = null;
        
        if (Modifier.isAbstract(screenClass.getModifiers())) {
            throw new IllegalArgumentException("Cannot show an abstract Screen");
        }
        
        try {
            s = screenClass.getConstructor().newInstance();
        } catch (java.lang.ReflectiveOperationException e) {
            throw new Error("Could not instantiate Screen: " + screenClass.getName());
        }
        
        frame.setContentPane(s.getPanel());
        frame.setTitle("ECOnnect Admin Console - " + s.getTitle());
        frame.revalidate();
        frame.repaint();
    }
    
    
    private void setIcon() {
        try {
            ArrayList<Image> icons = new ArrayList<Image>();
            icons.add(getIconScale("16"));
            icons.add(getIconScale("32"));
            icons.add(getIconScale("64"));
            icons.add(getIconScale("128"));
            frame.setIconImages(icons);
        }
        catch (NullPointerException e) {
            System.err.println("Warning: Failed to load icon");
        }
    }
    private Image getIconScale(String size) {
        String name = "icon/" + size + ".png";
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(name));
        return icon.getImage();
    }
}

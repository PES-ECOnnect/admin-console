package ECOnnect.UI.Utilities.CustomComponents;

import java.awt.*;

import javax.swing.*;

import ECOnnect.UI.Utilities.HorizontalBox;

public abstract class ItemListElement extends JPanel {
    
    public static final Dimension DEFAULT_ELEMENT_SIZE = new Dimension(150, 35);
    public static final Dimension DEFAULT_SIZE = new Dimension(Integer.MAX_VALUE, DEFAULT_ELEMENT_SIZE.height);
    
    // Call this method AFTER the constructor of the subclass
    protected void init() {
        super.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        super.setMaximumSize(DEFAULT_SIZE);
        super.setMinimumSize(DEFAULT_SIZE);
        super.setPreferredSize(DEFAULT_SIZE);
        HorizontalBox.HEIGHT = DEFAULT_ELEMENT_SIZE.height;
        
        Component[] rowComponents = getRowComponents();
        
        for (Component component : rowComponents) {
            component.setMaximumSize(DEFAULT_ELEMENT_SIZE);
            component.setMinimumSize(DEFAULT_ELEMENT_SIZE);
            super.add(component);
        }
    }
    
    protected abstract Component[] getRowComponents();
    
    // By default, an item is not selectable
    public boolean isSelected() {
        return false;
    }
    
    // By default, an item is not selectable
    public void uncheck() {
        // Do nothing
    }
}

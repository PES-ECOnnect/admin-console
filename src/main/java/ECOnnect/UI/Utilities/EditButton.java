package ECOnnect.UI.Utilities;

import java.awt.Insets;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class EditButton extends JButton {
    
    private static URL _EDIT_ICON = EditButton.class.getResource("/images/edit.png");
    
    public EditButton() {
        super(new ImageIcon(_EDIT_ICON));
        super.setMargin(new Insets(2, 2, 2, 2));
    }
}

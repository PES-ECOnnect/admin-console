package ECOnnect.UI.Utilities;

import java.awt.Insets;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ImageButton extends JButton {
    public ImageButton(String imagePath) {
        final URL url = ImageButton.class.getResource("/images/edit.png");
        final ImageIcon imageIcon = new ImageIcon(url);
        super.setIcon(imageIcon);
        super.setMargin(new Insets(2, 2, 2, 2));
    }
}

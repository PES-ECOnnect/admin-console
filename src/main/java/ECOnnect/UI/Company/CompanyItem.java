package ECOnnect.UI.Company;

import ECOnnect.UI.ScreenManager;
import ECOnnect.UI.Product.ImageDetail.ImageDetailScreen;
import ECOnnect.UI.Utilities.ImageLoader;
import ECOnnect.UI.Utilities.ItemListElement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CompanyItem extends ItemListElement {
    private final String _name;
    private final String _location;
    private final String _imageUrl;
    private final String _avgRating;
    private final JCheckBox _deleteCheckbox = new JCheckBox();

    public CompanyItem(String name, String imageUrl, float avgRating, double lat, double lon) {
        this._name = name;
        this._location = lat + ", " + lon;
        this._imageUrl = imageUrl;
        this._avgRating = Double.toString(avgRating);

        super.init();
    }

    @Override
    public String getName() {
        return _name;
    }

    public static String[] getHeaderNames(){return new String[]{"Name", "Location", "Thumbnail", "Full image", "Avg. Rating", "Select for delete"};}

    protected Component[] getRowComponents(){
        JTextField nameField = new JTextField(_name);
        nameField.setEditable(false);
        JTextField locationField = new JTextField(_location);
        locationField.setEditable(false);
        JLabel thumbnail = new JLabel();
        thumbnail.setHorizontalAlignment(SwingConstants.CENTER);
        JButton imageButton = new JButton("View");
        imageButton.addActionListener(imageButtonListener());
        JTextField avgRatingField = new JTextField(_avgRating);
        avgRatingField.setEditable(false);
        
        // Callback for image loading
        ImageLoader.loadAsync(_imageUrl, new ImageLoader.ImageLoaderCallback() {
            @Override
            public void imageLoaded(ImageIcon image) {
                ImageIcon scaledImage = ImageLoader.scale(image, -1, DEFAULT_SIZE.height);
                thumbnail.setIcon(scaledImage);
            }
            @Override
            public void couldNotLoad() {
                thumbnail.setText(_imageUrl);
            }
        });
        

        return new Component[] {
            nameField,
            locationField,
            thumbnail,
            imageButton,
            avgRatingField,
            _deleteCheckbox
        };
    }
    
    private ActionListener imageButtonListener() {
        return (ActionEvent e) -> {
            ScreenManager.getInstance().show(ImageDetailScreen.class, _imageUrl, ScreenManager.MAIN_MENU_SCREEN);
        };
    }

    @Override
    public boolean isSelected() {
        return _deleteCheckbox.isSelected();
    }

    @Override
    public void uncheck() {
        _deleteCheckbox.setSelected(false);
    }
}

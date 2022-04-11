package ECOnnect.UI.Company;

import ECOnnect.UI.Utilities.ImageLoader;
import ECOnnect.UI.Utilities.ItemListElement;

import javax.swing.*;
import java.awt.*;

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

    public static String[] getHeaderNames(){return new String[]{"Name", "Location", "Image", "Avg. Rating", "Select for delete"};}

    protected Component[] getRowComponents(){
        JTextField nameField = new JTextField(_name);
        nameField.setEditable(false);
        JTextField locationField = new JTextField(_location);
        locationField.setEditable(false);
        JLabel imageUrlField = new JLabel();
        JTextField avgRatingField = new JTextField(_avgRating);
        avgRatingField.setEditable(false);
        
        // Callback for image loading
        ImageLoader.loadAsync(_imageUrl, new ImageLoader.ImageLoaderCallback() {
            @Override
            public void imageLoaded(ImageIcon image) {
                ImageIcon scaledImage = ImageLoader.scale(image, -1, DEFAULT_SIZE.height);
                imageUrlField.setIcon(scaledImage);
            }
            @Override
            public void couldNotLoad() {
                imageUrlField.setText(_imageUrl);
                System.err.println("Warning: Could not load company icon " + _imageUrl);
            }
        });
        

        return new Component[] {
            nameField,
            locationField,
            imageUrlField,
            avgRatingField,
            _deleteCheckbox
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

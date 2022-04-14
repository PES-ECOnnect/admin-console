package ECOnnect.UI.Product;

import ECOnnect.UI.ScreenManager;
import ECOnnect.UI.Product.ImageDetail.ImageDetailScreen;
import ECOnnect.UI.Utilities.ImageLoader;
import ECOnnect.UI.Utilities.ItemListElement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ProductItem extends ItemListElement {
    private final String _name;
    private final String _manufacturer;
    private final String _imageUrl;
    private final String _avgRating;
    private final JCheckBox _deleteCheckBox = new JCheckBox();

    public ProductItem(String name, String manufacturer, String imageUrl, float avgRating) {
        this._name = name;
        this._manufacturer = manufacturer;
        this._imageUrl = imageUrl;
        this._avgRating = Double.toString(avgRating);
        
        super.init();
    }

    public static String[] getHeaderNames(){return new String[] {"Name", "Manufacturer", "Avg. Rating", "Thumbnail", "Full image", "Select for delete"};}

    protected Component[] getRowComponents() {
        JTextField nameField = new JTextField(_name);
        nameField.setEditable(false);
        
        JTextField manufacturerField = new JTextField(_manufacturer);
        manufacturerField.setEditable(false);
        
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

        return new Component[]{
            nameField,
            manufacturerField,
            avgRatingField,
            thumbnail,
            imageButton,
            _deleteCheckBox
        };
    }
    
    private ActionListener imageButtonListener() {
        return (ActionEvent e) -> {
            ScreenManager.getInstance().show(ImageDetailScreen.class, _imageUrl, ScreenManager.PRODUCT_SCREEN);
        };
    }

    @Override
    public boolean isSelected() {
        return _deleteCheckBox.isSelected();
    }

    @Override
    public void uncheck() {
        _deleteCheckBox.setSelected(false);
    }
}

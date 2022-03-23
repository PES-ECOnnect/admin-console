package ECOnnect.UI.Product;

import ECOnnect.UI.Utilities.ItemListElement;

import javax.swing.*;
import java.awt.*;

public class ProductItem extends ItemListElement {
    private final String _name;
    private final String _manufacturer;
    private final String _imageUrl;
    private final String _type;
    private final String _avgRating;
    private final JCheckBox _deleteCheckBox = new JCheckBox();

    public ProductItem(String name, String manufacturer, String imageUrl, float avgRating, String type) {
        this._name = name;
        this._manufacturer = manufacturer;
        this._imageUrl = imageUrl;
        this._type = type;
        this._avgRating = Double.toString(avgRating);
        
        super.init();
    }

    public static String[] getHeaderNames(){return new String[] {"Name", "Manufacturer", "Image URL", "Avg. Rating", "Type", "Select for delete"};}

    protected Component[] getRowComponents() {
        JTextField nameField = new JTextField(_name);
        nameField.setEditable(false);
        JTextField manufacturerField = new JTextField(_manufacturer);
        manufacturerField.setEditable(false);
        JTextField imageUrlField = new JTextField(_imageUrl);
        imageUrlField.setEditable(false);
        JTextField typeField = new JTextField(_type);
        typeField.setEditable(false);
        JTextField avgRatingField = new JTextField(_avgRating);
        avgRatingField.setEditable(false);

        return new Component[]{
            nameField,
            manufacturerField,
            imageUrlField,
            avgRatingField,
            typeField,
            _deleteCheckBox
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

package ECOnnect.UI.Company;

import ECOnnect.UI.Utilities.ItemListElement;

import javax.swing.*;
import java.awt.*;

public class CompanyItem extends ItemListElement {
    private final String _name;
    private final String _location;
    private final String _imageUrl;
    private final JCheckBox _deleteCheckbox = new JCheckBox();

    public CompanyItem(String name, String location, String imageUrl){
        this._name = name;
        this._location = location;
        this._imageUrl = imageUrl;

        super.init();
    }

    @Override
    public String getName() {
        return _name;
    }

    public static String[] getHeaderNames(){return new String[]{"name", "location","image URL", "select for delete"};}

    protected Component[] getRowComponents(){
        JTextField nameField = new JTextField(_name);
        nameField.setEditable(false);
        JTextField locationField = new JTextField(_location);
        locationField.setEditable(false);
        JTextField imageUrlField = new JTextField(_imageUrl);
        imageUrlField.setEditable(false);

        return new Component[] {
            nameField,
            locationField,
            imageUrlField,
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

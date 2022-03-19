package ECOnnect.UI.Product;

import ECOnnect.UI.Utilities.ItemListElement;

import javax.swing.*;
import java.awt.*;

public class ProductItem extends ItemListElement {
    private String name;
    private String manufacturer;
    private String imageUrl;
    private String type;
    private JCheckBox deleteCheckBox = new JCheckBox();

    public ProductItem(String name, String manufacturer, String imageUrl, String type) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.imageUrl = imageUrl;
        this.type = type;

        super.init();
    }

    public static String[] getHeaderNames(){return new String[] {"Name", "Manufacturer", "image URL", "type","Select for delete"};}

    protected Component[] getRowComponents() {
        JTextField nameField = new JTextField(name);
        nameField.setEditable(false);
        JTextField manufacturerField = new JTextField(manufacturer);
        manufacturerField.setEditable(false);
        JTextField imageUrlField = new JTextField(imageUrl);
        imageUrlField.setEditable(false);
        JTextField typeField = new JTextField(type);
        typeField.setEditable(false);

        return new Component[]{
                nameField,
                manufacturerField,
                imageUrlField,
                typeField,
                deleteCheckBox
        };
    }

    @Override
    public boolean isSelected() {
        return deleteCheckBox.isSelected();
    }

    @Override
    public void uncheck() {
        deleteCheckBox.setSelected(false);
    }
}

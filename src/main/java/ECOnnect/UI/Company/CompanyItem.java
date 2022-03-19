package ECOnnect.UI.Company;

import ECOnnect.UI.Utilities.ItemListElement;

import javax.swing.*;
import java.awt.*;

public class CompanyItem extends ItemListElement {
    private String name;
    private String location;
    private String imageUrl;
    private JCheckBox deleteCheckbox = new JCheckBox();

    public CompanyItem(String name, String location, String imageUrl){
        this.name = name;
        this.location = location;
        this.imageUrl = imageUrl;

        super.init();
    }

    @Override
    public String getName() {
        return name;
    }

    public static String[] getHeaderNames(){return new String[]{"name", "location","image URL", "select for delete"};}

    protected Component[] getRowComponents(){
        JTextField nameField = new JTextField(name);
        nameField.setEditable(false);
        JTextField locationField = new JTextField(location);
        locationField.setEditable(false);
        JTextField imageUrlField = new JTextField(imageUrl);
        imageUrlField.setEditable(false);

        return new Component[] {
                nameField,
                locationField,
                imageUrlField,
                deleteCheckbox
        };
    }

    @Override
    public boolean isSelected() {
        return deleteCheckbox.isSelected();
    }

    @Override
    public void uncheck() {
        deleteCheckbox.setSelected(false);
    }
}

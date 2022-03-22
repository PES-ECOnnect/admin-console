package ECOnnect.UI.NewProduct;

import ECOnnect.UI.Interfaces.View;
import ECOnnect.UI.Utilities.HorizontalBox;

import javax.swing.*;
import java.awt.*;

public class NewProductView extends View {
    private final NewProductController _ctrl;

    private final JTextField _nameTxt = new JTextField(20);
    private final JTextField _manufacturerTxt = new JTextField(20);
    private final JTextField _imageUrlTxt = new JTextField(20);
    private final JTextField _typeTxt = new JTextField(20);

    // Coponents

    public NewProductView(NewProductController ctrl){
        this._ctrl = ctrl;
        setUp();
    }

    private void setUp() {
        // weird spaces in labels are for better UI aesthetic
        panel.add(Box.createVerticalGlue());

        _nameTxt.setMaximumSize(new Dimension(100,30));
        panel.add(HorizontalBox.create(new JLabel("Product name: "), _nameTxt));

        panel.add(Box.createVerticalStrut(10));

        _typeTxt.setMaximumSize(new Dimension(100,30));
        panel.add(HorizontalBox.create(new JLabel("Product type:  "), _typeTxt));

        panel.add(Box.createVerticalStrut(10));

        _manufacturerTxt.setMaximumSize(new Dimension(100, 30));
        panel.add(HorizontalBox.create(new JLabel("Manufacturer: "), _manufacturerTxt));

        panel.add(Box.createVerticalStrut(10));

        _imageUrlTxt.setMaximumSize(new Dimension(100,30));
        panel.add(HorizontalBox.create(new JLabel("Image Url:        "), _imageUrlTxt));

        panel.add(Box.createVerticalStrut(10));

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(_ctrl.saveButton());
        panel.add(HorizontalBox.create(saveButton));

        panel.add(Box.createVerticalStrut(10));

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(_ctrl.cancelButton());
        panel.add(HorizontalBox.create(cancelButton));

        panel.add(Box.createVerticalGlue());
    }

    public String getNameText(){return _nameTxt.getText();}
    public String getManufacturerText(){return _manufacturerTxt.getText();}
    public String getImageUrlText(){return _imageUrlTxt.getText();}
    public String getTypeText(){return _typeTxt.getText();}

}

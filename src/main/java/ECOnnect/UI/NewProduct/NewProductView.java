package ECOnnect.UI.NewProduct;

import ECOnnect.UI.Interfaces.View;
import ECOnnect.UI.Utilities.HorizontalBox;

import javax.swing.*;
import java.awt.*;

public class NewProductView extends View {
    private NewProductController ctrl;

    private JTextField nameTxt = new JTextField(20);
    private JTextField manufacturerTxt = new JTextField(20);
    private JTextField imageUrlTxt = new JTextField(20);
    private JTextField typeTxt = new JTextField(20);

    // Coponents

    public NewProductView(NewProductController ctrl){
        this.ctrl = ctrl;
        setUp();
    }

    private void setUp() {
        // weird spaces in labels are for better UI aesthetic
        panel.add(Box.createVerticalGlue());

        nameTxt.setMaximumSize(new Dimension(100,30));
        panel.add(HorizontalBox.create(new JLabel("Product name: "), nameTxt));

        panel.add(Box.createVerticalStrut(10));

        typeTxt.setMaximumSize(new Dimension(100,30));
        panel.add(HorizontalBox.create(new JLabel("Product type:  "), typeTxt));

        panel.add(Box.createVerticalStrut(10));

        manufacturerTxt.setMaximumSize(new Dimension(100, 30));
        panel.add(HorizontalBox.create(new JLabel("Manufacturer: "), manufacturerTxt));

        panel.add(Box.createVerticalStrut(10));

        imageUrlTxt.setMaximumSize(new Dimension(100,30));
        panel.add(HorizontalBox.create(new JLabel("Image Url:        "), imageUrlTxt));

        panel.add(Box.createVerticalStrut(10));

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(ctrl.saveButton());
        panel.add(HorizontalBox.create(saveButton));

        panel.add(Box.createVerticalStrut(10));

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(ctrl.cancelButton());
        panel.add(HorizontalBox.create(cancelButton));

        panel.add(Box.createVerticalGlue());
    }

    public String getNameText(){return nameTxt.getText();}
    public String getManufacturerText(){return manufacturerTxt.getText();}
    public String getImageUrlText(){return imageUrlTxt.getText();}
    public String getTypeText(){return typeTxt.getText();}

}

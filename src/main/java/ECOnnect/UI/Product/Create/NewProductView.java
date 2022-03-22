package ECOnnect.UI.Product.Create;

import ECOnnect.UI.Interfaces.View;
import ECOnnect.UI.Utilities.HorizontalBox;

import javax.swing.*;
import java.awt.*;

public class NewProductView extends View {
    private final NewProductController _ctrl;

    private final JTextField _nameTxt = new JTextField(20);
    private final JTextField _manufacturerTxt = new JTextField(20);
    private final JTextField _imageUrlTxt = new JTextField(20);
    private final JLabel _title = new JLabel("Create new Product");

    // Coponents

    public NewProductView(NewProductController ctrl){
        this._ctrl = ctrl;
        setUp();
    }

    private void setUp() {
        panel.add(Box.createVerticalGlue());
        
        // Set title alignment to center and increase font size
        _title.setHorizontalAlignment(JLabel.CENTER);
        _title.setFont(_title.getFont().deriveFont(24.0f));
        panel.add(HorizontalBox.create(_title));
        
        panel.add(Box.createVerticalStrut(50));

        _nameTxt.setMaximumSize(new Dimension(100,30));
        JLabel nameLbl = new JLabel("Product name:");
        nameLbl.setPreferredSize(new Dimension(100,30));
        panel.add(HorizontalBox.create(nameLbl, _nameTxt));

        panel.add(Box.createVerticalStrut(10));

        _manufacturerTxt.setMaximumSize(new Dimension(100, 30));
        JLabel manufacturerLbl = new JLabel("Manufacturer:");
        manufacturerLbl.setPreferredSize(new Dimension(100, 30));
        panel.add(HorizontalBox.create(manufacturerLbl, _manufacturerTxt));

        panel.add(Box.createVerticalStrut(10));

        _imageUrlTxt.setMaximumSize(new Dimension(100,30));
        JLabel imageUrlLbl = new JLabel("Image URL:");
        imageUrlLbl.setPreferredSize(new Dimension(100,30));
        panel.add(HorizontalBox.create(imageUrlLbl, _imageUrlTxt));

        panel.add(Box.createVerticalStrut(10));

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(_ctrl.saveButton());
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(_ctrl.cancelButton());
        panel.add(HorizontalBox.create(cancelButton, saveButton));

        panel.add(Box.createVerticalGlue());
    }
    
    @Override
    public void postInit() {
        _title.setText("Create new Product of type '" + _ctrl.getType() + "'");
    }

    public String getNameText(){return _nameTxt.getText();}
    public String getManufacturerText(){return _manufacturerTxt.getText();}
    public String getImageUrlText(){return _imageUrlTxt.getText();}

}

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
    private final JLabel _title = new JLabel("", JLabel.CENTER);
    
    private final Dimension _dim = new Dimension(110, 30);

    // Coponents

    public NewProductView(NewProductController ctrl){
        this._ctrl = ctrl;
        setUp();
    }

    private void setUp() {
        panel.add(Box.createVerticalGlue());
        
        // Increase title font size
        _title.setFont(_title.getFont().deriveFont(24.0f));
        panel.add(HorizontalBox.create(_title));
        
        panel.add(Box.createVerticalStrut(50));

        JLabel nameLbl = new JLabel("Product name:", JLabel.RIGHT);
        nameLbl.setPreferredSize(_dim);
        _nameTxt.setMaximumSize(_dim);
        panel.add(HorizontalBox.create(nameLbl, _nameTxt));
        // Pressing enter will select the next text field
        _nameTxt.addActionListener(e -> _manufacturerTxt.requestFocus());

        panel.add(Box.createVerticalStrut(10));

        JLabel manufacturerLbl = new JLabel("Manufacturer:", JLabel.RIGHT);
        manufacturerLbl.setPreferredSize(_dim);
        _manufacturerTxt.setMaximumSize(_dim);
        panel.add(HorizontalBox.create(manufacturerLbl, _manufacturerTxt));
        // Pressing enter will select the next text field
        _manufacturerTxt.addActionListener(e -> _imageUrlTxt.requestFocus());

        panel.add(Box.createVerticalStrut(10));

        JLabel imageUrlLbl = new JLabel("Image URL:", JLabel.RIGHT);
        imageUrlLbl.setPreferredSize(_dim);
        _imageUrlTxt.setMaximumSize(_dim);
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
